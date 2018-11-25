package tools.mdsd.cdo.debug.variablesview.tests;

import org.eclipse.debug.core.DebugException;
import org.eclipse.debug.core.model.IValue;
import org.eclipse.debug.core.model.IVariable;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;

import static org.junit.jupiter.api.Assertions.assertTrue;

import tools.mdsd.cdo.debug.variablesview.CDOObjectLogicalStructureType;

class CDOObjectLogicalStructureTypeTest {

	@Test
	void testProvidesLogicalStructureReturnsTrueForExpectedValueConfiguration() throws DebugException {
		IValue value = mock(IValue.class);
		IVariable[] variables = {
				mockVariableWithName("eSettings"),
				mockViewAndStateVariable(),
				mockRevisionVariable()
		};
		when(value.getVariables()).thenReturn(variables);
		assertTrue(new CDOObjectLogicalStructureType().providesLogicalStructure(value));
	}

	private IVariable mockViewAndStateVariable() throws DebugException {
		IVariable viewAndStateVariable = mockVariableWithName("viewAndState");
		IVariable stateVariable = mockVariableWithName("state");
		IVariable nameVariable = mockVariableWithName("name");
		IValue stateValue = mock(IValue.class);
		IValue viewAndStateValue = mock(IValue.class);
		when(stateValue.getVariables()).thenReturn(new IVariable[] { nameVariable });
		IValue transientValue = mockStringValue("TRANSIENT");
		when(nameVariable.getValue()).thenReturn(transientValue);
		when(stateVariable.getValue()).thenReturn(stateValue);
		when(viewAndStateValue.getVariables()).thenReturn(new IVariable[] { stateVariable });
		when(viewAndStateVariable.getValue()).thenReturn(viewAndStateValue);
		return viewAndStateVariable;
	}

	private IVariable mockRevisionVariable() throws DebugException {
		IValue revisionValue = mock(IValue.class);
		IVariable classInfoVariable = mockVariableWithName("classInfo");
		when(revisionValue.getVariables()).thenReturn(new IVariable[] { classInfoVariable });
		IVariable revisionVariable = mockVariableWithName("revision");
		when(revisionVariable.getValue()).thenReturn(revisionValue);
		return revisionVariable;
	}

	private IVariable mockVariableWithName(String name) throws DebugException {
		IVariable variable = mock(IVariable.class);
		when(variable.getName()).thenReturn(name);
		return variable;
	}

	private IValue mockStringValue(String value) throws DebugException {
		IValue transientValue = mock(IValue.class);
		when(transientValue.getValueString()).thenReturn(value);
		return transientValue;
	}

}
