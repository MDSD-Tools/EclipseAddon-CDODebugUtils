package tools.mdsd.cdo.debug.variablesview;

import static tools.mdsd.cdo.debug.variablesview.LambdaExceptionUtil.wrapFn;
import static tools.mdsd.cdo.debug.variablesview.ValueUtil.findField;

import java.util.Objects;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.debug.core.DebugException;
import org.eclipse.debug.core.model.ILogicalStructureTypeDelegate;
import org.eclipse.debug.core.model.IValue;

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

}
