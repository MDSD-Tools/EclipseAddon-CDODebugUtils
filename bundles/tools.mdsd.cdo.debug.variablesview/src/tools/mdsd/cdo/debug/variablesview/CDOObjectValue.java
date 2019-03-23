package tools.mdsd.cdo.debug.variablesview;

import static tools.mdsd.cdo.debug.variablesview.LambdaExceptionUtil.wrapFn;
import static tools.mdsd.cdo.debug.variablesview.LambdaExceptionUtil.wrapIntFn;
import static tools.mdsd.cdo.debug.variablesview.ValueUtil.findField;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.eclipse.debug.core.DebugException;
import org.eclipse.debug.core.ILaunch;
import org.eclipse.debug.core.model.IDebugTarget;
import org.eclipse.debug.core.model.IValue;
import org.eclipse.debug.core.model.IVariable;

public class CDOObjectValue implements IValue {

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
        List<CDOObjectFeatureVariable> result = new ArrayList<>();
        result.addAll(createObjectFeatureVariables(allFeaturesData));
        findField(realValue, "eFlags")
            .map(wrapFn(variable -> new CDOObjectFeatureVariable("eFlags", variable.getValue())))
            .ifPresent(result::add);
        findField(realValue, "eStorage")
            .map(wrapFn(variable -> new CDOObjectFeatureVariable("eStorage", variable.getValue())))
            .ifPresent(result::add);
        findField(realValue, "revision", "classInfo", "eClass", "eContainer")
            .map(wrapFn(variable -> new CDOObjectFeatureVariable("eContainer", variable.getValue())))
            .ifPresent(result::add);
        return result.toArray(new IVariable[0]);
    }

    private List<CDOObjectFeatureVariable> createObjectFeatureVariables(IValue allFeaturesDataValue)
        throws DebugException {
        IVariable[] allFeatures = allFeaturesDataValue.getVariables();
        return IntStream
            .range(0, allFeatures.length)
            .mapToObj(wrapIntFn(i -> {
                IVariable variable = allFeatures[i];
                String name = findField(variable.getValue(), "name").get()
                    .getValue()
                    .getValueString();
                IValue value = getFeatureValue(i);
                return new CDOObjectFeatureVariable(name, value);
            }))
            .collect(Collectors.toList());
    }

    private IValue getFeatureValue(int i) throws DebugException {
        if (findField(realValue, "viewAndState", "state", "name").get().getValue().getValueString()
            .equals("TRANSIENT")) {
            return getTransientFeatureValue(i);
        } else {
            if (isPersistentFeature(i)) {
                return getPersistentFeatureValue(i);
            }
            return getTransientFeatureValue(i);
        }
    }

    private IValue getTransientFeatureValue(int i) throws DebugException {
        IVariable[] transientIndices = findField(realValue, "revision", "classInfo",
            "transientFeatureIndices")
                .get().getValue().getVariables();
        IVariable[] settings = findField(realValue, "eSettings").get().getValue().getVariables();
        int settingsIndex = Integer.parseInt(transientIndices[i].getValue().getValueString());
        if (settingsIndex < settings.length) {
            return settings[settingsIndex].getValue();
        } else {
            return new CDOObjectStringValue("null");
        }
    }

    private IValue getPersistentFeatureValue(int i) throws DebugException {
        IVariable[] persistentIndices = findField(realValue, "revision", "classInfo",
            "persistentFeatureIndices")
                .get().getValue().getVariables();
        IVariable[] revisionValues = getRevisionValues();
        int persistentIndex = Integer.parseInt(persistentIndices[i].getValue().getValueString());
        if (persistentIndex < revisionValues.length) {
            return revisionValues[persistentIndex].getValue();
        } else {
            return new CDOObjectStringValue("UNAVAILABLE");
        }
    }

    private IVariable[] getRevisionValues() throws DebugException {
        try {
            return findField(realValue, "revision", "values").get().getValue().getVariables();
        } catch (NoSuchElementException e) {
            return new IVariable[0];
        }

    }

    private boolean isPersistentFeature(int i) throws DebugException {
        IVariable[] persistentIndices = findField(realValue, "revision", "classInfo",
            "persistentFeatureIndices")
                .get().getValue().getVariables();
        return Integer.parseInt(persistentIndices[i].getValue().getValueString()) > -1;
    }

    @Override
    public boolean hasVariables() throws DebugException {
        return realValue.hasVariables();
    }

}
