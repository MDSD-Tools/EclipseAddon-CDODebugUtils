package tools.mdsd.cdo.debug.variablesview;

import static java.util.function.Function.identity;
import static tools.mdsd.cdo.debug.variablesview.LambdaExceptionUtil.wrapFn;
import static tools.mdsd.cdo.debug.variablesview.LambdaExceptionUtil.wrapIntFn;

import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

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
            return findField(value, "eSettings").isPresent()
                && findField(value, "revision", "classInfo").isPresent()
                && findField(value, "viewAndState", "state", "name")
                    .map(wrapFn(variable -> Objects.equals(variable.getValue().getValueString(), "TRANSIENT")))
                    .orElse(false);
        } catch (DebugException e) {
            return false;
        }
    }

    @Override
    public IValue getLogicalStructure(IValue value) throws CoreException {
        return new CDOObjectValue(value);
    }

    private static Optional<IVariable> findField(IValue value, String... path) throws DebugException {
        IValue val = value;
        for (int i = 0; i < path.length; i++) {
            for (IVariable variable : val.getVariables()) {
                if (path[i].equals(variable.getName())) {
                    val = variable.getValue();
                    if (i == path.length - 1) {
                        return Optional.of(variable);
                    }
                    break;
                }
            }
        }
        return Optional.empty();
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
            IValue allFeaturesData = findField(realValue,
                "revision", "classInfo", "eClass", "eAllStructuralFeatures", "data").get().getValue();
            IValue transientFeatureIndices = findField(realValue,
                "revision", "classInfo", "transientFeatureIndices").get().getValue();
            IVariable[] settingVars = findField(realValue, "eSettings").get().getValue().getVariables();
            Map<Integer, String> featureIndexToName = buildFeatureIndexToNameMap(allFeaturesData);
            Map<Integer, Integer> transientIndexToFeatureIndex = buildTransientIndexToFeatureIndexMap(
                transientFeatureIndices);
            return IntStream
                .range(0, settingVars.length)
                .filter(i -> transientIndexToFeatureIndex.get(i) != null)
                .mapToObj(wrapIntFn(i -> {
                    Integer featureIndex = transientIndexToFeatureIndex.get(i);
                    String featureName = featureIndexToName.get(featureIndex);
                    IValue featureValue = settingVars[i].getValue();
                    return new CDOObjectFeatureVariable(featureName, featureValue);
                }))
                .toArray(IVariable[]::new);
        }

        private Map<Integer, Integer> buildTransientIndexToFeatureIndexMap(IValue transientFeatureIndicesValue)
            throws DebugException {
            IVariable[] variables = transientFeatureIndicesValue.getVariables();
            return IntStream.range(0, variables.length).boxed()
                .collect(
                    Collectors.toMap(
                        wrapFn(i -> Integer.parseInt(variables[i].getValue().getValueString())),
                        identity()));
        }

        private Map<Integer, String> buildFeatureIndexToNameMap(IValue allFeaturesDataValue) throws DebugException {
            IVariable[] allFeaturesDataVariables = allFeaturesDataValue.getVariables();
            return IntStream.range(0, allFeaturesDataVariables.length).boxed()
                .collect(Collectors.toMap(
                    identity(),
                    wrapFn(i -> findField(allFeaturesDataVariables[i].getValue(), "name")
                        .get()
                        .getValue()
                        .getValueString())));
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
