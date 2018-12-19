package tools.mdsd.cdo.debug.variablesview;

import java.util.Optional;

import org.eclipse.debug.core.DebugException;
import org.eclipse.debug.core.model.IValue;
import org.eclipse.debug.core.model.IVariable;

public class ValueUtil {

    public static Optional<IVariable> findField(IValue value, String... path) throws DebugException {
        IValue currentValue = value;
        for (int i = 0; i < path.length; i++) {
            for (IVariable variable : currentValue.getVariables()) {
                if (path[i].equals(variable.getName())) {
                    currentValue = variable.getValue();
                    if (i == path.length - 1) {
                        return Optional.of(variable);
                    }
                    break;
                }
            }
        }
        return Optional.empty();
    }

    public static Optional<IVariable> findField(IValue value, String fieldName) throws DebugException {
        for (IVariable var : value.getVariables()) {
            if (fieldName.equals(var.getName())) {
                return Optional.of(var);
            }
        }
        return Optional.empty();
    }

}
