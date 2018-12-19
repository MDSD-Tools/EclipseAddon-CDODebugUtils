package tools.mdsd.cdo.debug.variablesview;

import static java.util.function.Function.identity;
import static tools.mdsd.cdo.debug.variablesview.LambdaExceptionUtil.wrapFn;
import static tools.mdsd.cdo.debug.variablesview.LambdaExceptionUtil.wrapIntFn;
import static tools.mdsd.cdo.debug.variablesview.ValueUtil.findField;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
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
        IValue transientFeatureIndices = findField(realValue,
            "revision", "classInfo", "transientFeatureIndices").get().getValue();
        IVariable[] settingVars = findField(realValue, "eSettings").get().getValue().getVariables();
        Map<Integer, String> featureIndexToName = buildFeatureIndexToNameMap(allFeaturesData);
        Map<Integer, Integer> transientIndexToFeatureIndex = buildTransientIndexToFeatureIndexMap(
            transientFeatureIndices);
        List<CDOObjectFeatureVariable> result = new ArrayList<>();
        findField(realValue, "revision", "classInfo", "eClass", "eAllStructuralFeatures", "data")
            .map(wrapFn(variable -> new CDOObjectFeatureVariable("structuralFeatures", variable.getValue())))
            .ifPresent(result::add);
        findField(realValue, "revision", "classInfo", "eClass", "eAllReferences", "data")
            .map(wrapFn(variable -> new CDOObjectFeatureVariable("references", variable.getValue())))
            .ifPresent(result::add);
        IntStream
            .range(0, settingVars.length)
            .filter(i -> transientIndexToFeatureIndex.get(i) != null)
            .mapToObj(wrapIntFn(i -> {
                Integer featureIndex = transientIndexToFeatureIndex.get(i);
                String featureName = featureIndexToName.get(featureIndex);
                IValue featureValue = settingVars[i].getValue();
                return new CDOObjectFeatureVariable(featureName, featureValue);
            }))
            .forEachOrdered(result::add);
        return result.toArray(new IVariable[0]);
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
