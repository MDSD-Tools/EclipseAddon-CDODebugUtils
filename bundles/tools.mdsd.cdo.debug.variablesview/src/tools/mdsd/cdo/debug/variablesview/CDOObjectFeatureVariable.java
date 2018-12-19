package tools.mdsd.cdo.debug.variablesview;

import org.eclipse.core.runtime.Status;
import org.eclipse.debug.core.DebugException;
import org.eclipse.debug.core.ILaunch;
import org.eclipse.debug.core.model.IDebugTarget;
import org.eclipse.debug.core.model.IValue;
import org.eclipse.debug.core.model.IVariable;

public class CDOObjectFeatureVariable implements IVariable {

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
