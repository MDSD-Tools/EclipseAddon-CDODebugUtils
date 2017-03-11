package de.cooperateproject.eclipse.cdodebugutils.variablesview;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.Status;
import org.eclipse.debug.core.DebugException;
import org.eclipse.debug.core.ILaunch;
import org.eclipse.debug.core.model.IDebugTarget;
import org.eclipse.debug.core.model.ILogicalStructureTypeDelegate;
import org.eclipse.debug.core.model.IValue;
import org.eclipse.debug.core.model.IVariable;

public class CDOObjectLogicalStructureType implements ILogicalStructureTypeDelegate {

    @Override
    public boolean providesLogicalStructure(IValue value) {
        try {
            return containsESettings(value) && containsRevision(value) && containsValidViewAndState(value);
        } catch (DebugException e) {
            return false;
        }
    }

    @Override
    public IValue getLogicalStructure(IValue value) throws CoreException {
        return new CDOObjectValue(value);
    }

    private static boolean containsValidViewAndState(IValue value) throws DebugException {
        Optional<IVariable> viewAndStateField = findField(value, "viewAndState");
        if (!viewAndStateField.isPresent()) {
            return false;
        }
        Optional<IVariable> stateField = findField(viewAndStateField.get().getValue(), "state");
        if (!stateField.isPresent()) {
            return false;
        }
        Optional<IVariable> stateNameField = findField(stateField.get().getValue(), "name");
        if (!stateNameField.isPresent()) {
            return false;
        }
        String stateName = stateNameField.get().getValue().getValueString();
        return "TRANSIENT".equals(stateName);
    }

    private static boolean containsRevision(IValue value) throws DebugException {
        Optional<IVariable> revisionField = findField(value, "revision");
        if (!revisionField.isPresent()) {
            return false;
        }
        Optional<IVariable> classInfoField = findField(revisionField.get().getValue(), "classInfo");
        return classInfoField.isPresent();
    }

    private static boolean containsESettings(IValue value) throws DebugException {
        Optional<IVariable> eSettingsField = findField(value, "eSettings");
        return eSettingsField.isPresent();
    }

    private static Optional<IVariable> findField(IValue value, String fieldName) throws DebugException {
        for (IVariable var : value.getVariables()) {
            if (fieldName.equals(var.getName())) {
                return Optional.of(var);
            }
        }
        return Optional.empty();
    }

    private static class CDOObjectValue implements IValue {

        private final IValue realValue;

        public CDOObjectValue(IValue realValue) {
            this.realValue = realValue;
        }

        @Override
        public <T> T getAdapter(Class<T> adapter) {
            return realValue.getAdapter(adapter);
        }

        @Override
        public String getReferenceTypeName() throws DebugException {
            return realValue.getReferenceTypeName();
        }

        @Override
        public String getModelIdentifier() {
            return realValue.getModelIdentifier();
        }

        @Override
        public IDebugTarget getDebugTarget() {
            return realValue.getDebugTarget();
        }

        @Override
        public String getValueString() throws DebugException {
            return realValue.getValueString();
        }

        @Override
        public ILaunch getLaunch() {
            return realValue.getLaunch();
        }

        @Override
        public boolean isAllocated() throws DebugException {
            return realValue.isAllocated();
        }

        @Override
        public IVariable[] getVariables() throws DebugException {

            Optional<IVariable> revision = findField(realValue, "revision");

            IValue revisionValue = revision.get().getValue();
            Optional<IVariable> classInfoField = findField(revisionValue, "classInfo");

            IValue classInfoValue = classInfoField.get().getValue();
            Optional<IVariable> eClassField = findField(classInfoValue, "eClass");

            IValue eClassValue = eClassField.get().getValue();
            Optional<IVariable> eAllStructuralFeaturesField = findField(eClassValue, "eAllStructuralFeatures");

            IValue eAllStructuralFeaturesValue = eAllStructuralFeaturesField.get().getValue();
            Optional<IVariable> allFeaturesDataField = findField(eAllStructuralFeaturesValue, "data");

            Map<Integer, String> featureIndexToName = new HashMap<>();
            IValue allFeaturesDataValue = allFeaturesDataField.get().getValue();
            IVariable[] allFeaturesDataVariables = allFeaturesDataValue.getVariables();
            for (int i = 0; i < allFeaturesDataVariables.length; ++i) {
                IVariable featureVariable = allFeaturesDataVariables[i];
                IValue featureValue = featureVariable.getValue();
                Optional<IVariable> nameField = findField(featureValue, "name");
                IValue nameValue = nameField.get().getValue();
                String nameValueString = nameValue.getValueString();
                featureIndexToName.put(i, nameValueString);
            }

            Optional<IVariable> transientIndicesField = findField(classInfoValue, "transientFeatureIndices");
            IValue transientIndicesValue = transientIndicesField.get().getValue();

            Map<Integer, Integer> transientIndexToFeatureIndex = new HashMap<>();
            IVariable[] transientIndicesVariables = transientIndicesValue.getVariables();
            for (int i = 0; i < transientIndicesVariables.length; ++i) {
                IVariable transientIndexVariable = transientIndicesVariables[i];
                IValue transientIndexValue = transientIndexVariable.getValue();
                String transientIndexString = transientIndexValue.getValueString();
                int transientIndex = Integer.parseInt(transientIndexString);
                transientIndexToFeatureIndex.put(transientIndex, i);
            }

            Optional<IVariable> settings = findField(realValue, "eSettings");
            IValue settingsValue = settings.get().getValue();
            IVariable[] settingVars = settingsValue.getVariables();

            List<IVariable> result = new ArrayList<>();
            for (int i = 0; i < settingVars.length; ++i) {
                Integer featureIndex = transientIndexToFeatureIndex.getOrDefault(i, -1);
                if (featureIndex < 0) {
                    continue;
                }
                String featureName = featureIndexToName.get(featureIndex);
                IValue featureValue = settingVars[i].getValue();
                result.add(new CDOObjectFeatureVariable(featureName, featureValue));
            }

            return result.toArray(new IVariable[0]);
        }

        @Override
        public boolean hasVariables() throws DebugException {
            return realValue.hasVariables();
        }

    }

    private static class CDOObjectFeatureVariable implements IVariable {

        private final IValue featureValue;
        private final String featureName;

        public CDOObjectFeatureVariable(String featureName, IValue featureValue) {
            this.featureName = featureName;
            this.featureValue = featureValue;
        }

        @Override
        public String getModelIdentifier() {
            return featureValue.getModelIdentifier();
        }

        @Override
        public IDebugTarget getDebugTarget() {
            return featureValue.getDebugTarget();
        }

        @Override
        public ILaunch getLaunch() {
            return featureValue.getLaunch();
        }

        @Override
        public <T> T getAdapter(Class<T> adapter) {
            // TODO Auto-generated method stub
            return null;
        }

        @Override
        public void setValue(String expression) throws DebugException {
            throw new DebugException(Status.CANCEL_STATUS);
        }

        @Override
        public void setValue(IValue value) throws DebugException {
            throw new DebugException(Status.CANCEL_STATUS);
        }

        @Override
        public boolean supportsValueModification() {
            return false;
        }

        @Override
        public boolean verifyValue(String expression) throws DebugException {
            // TODO Auto-generated method stub
            return false;
        }

        @Override
        public boolean verifyValue(IValue value) throws DebugException {
            // TODO Auto-generated method stub
            return false;
        }

        @Override
        public IValue getValue() throws DebugException {
            return featureValue;
        }

        @Override
        public String getName() throws DebugException {
            return featureName;
        }

        @Override
        public String getReferenceTypeName() throws DebugException {
            return featureValue.getReferenceTypeName();
        }

        @Override
        public boolean hasValueChanged() throws DebugException {
            // TODO Auto-generated method stub
            return false;
        }

    }

}
