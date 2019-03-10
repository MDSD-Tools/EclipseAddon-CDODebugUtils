package tools.mdsd.cdo.debug.variablesview;

import org.eclipse.debug.core.DebugException;
import org.eclipse.debug.core.ILaunch;
import org.eclipse.debug.core.model.IDebugTarget;
import org.eclipse.debug.core.model.IValue;
import org.eclipse.debug.core.model.IVariable;

public class CDOObjectStringValue implements IValue {

    private String value;

    public CDOObjectStringValue(String value) {
        this.value = value;
    }

    @Override
    public String getModelIdentifier() {
        return null;
    }

    @Override
    public IDebugTarget getDebugTarget() {
        return null;
    }

    @Override
    public ILaunch getLaunch() {
        return null;
    }

    @Override
    public <T> T getAdapter(Class<T> adapter) {
        return null;
    }

    @Override
    public String getReferenceTypeName() throws DebugException {
        return null;
    }

    @Override
    public String getValueString() throws DebugException {
        return this.value;
    }

    @Override
    public boolean isAllocated() throws DebugException {
        return false;
    }

    @Override
    public IVariable[] getVariables() throws DebugException {
        return new IVariable[0];
    }

    @Override
    public boolean hasVariables() throws DebugException {
        return false;
    }

}
