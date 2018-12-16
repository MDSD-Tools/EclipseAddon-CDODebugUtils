package tools.mdsd.cdo.debug.variablesview.tests;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.debug.core.DebugException;
import org.eclipse.debug.core.model.IValue;
import org.eclipse.debug.core.model.IVariable;
import org.junit.jupiter.api.Test;

import tools.mdsd.cdo.debug.variablesview.CDOObjectLogicalStructureType;

class CDOObjectLogicalStructureTypeTest {

	@Test
	void testProvidesLogicalStructureReturnsTrueForExpectedValueConfiguration() throws DebugException {
		IValue value = mockValueWithVariables(
				new IVariable[] {
						mockVariables(new String[] { "eSettings" }),
						mockVariables(new String[] { "viewAndState", "state", "name" }, mockStringValue("TRANSIENT")),
						mockVariables(new String[] { "revision", "classInfo" })
				});
		assertTrue(new CDOObjectLogicalStructureType().providesLogicalStructure(value));
	}

	private IVariable mockVariables(String[] path) throws DebugException {
		return mockVariables(path, null);
	}

	private IVariable mockVariables(String[] path, IValue value) throws DebugException {
		List<IVariable> variables = new ArrayList<>();
		for (String name : path)
			variables.add(mockVariableWithName(name));
		for (int i = 0; i < variables.size(); i++) {
			if (i == variables.size() - 1 && value != null) {
				when(variables.get(i).getValue()).thenReturn(value);
			} else if (i < variables.size() - 1) {
				IValue mockValueWithVariable = mockValueWithVariables(new IVariable[] { variables.get(i + 1) });
				when(variables.get(i).getValue()).thenReturn(mockValueWithVariable);
			}
		}
		return variables.get(0);
	}

	private IVariable mockVariableWithName(String name) throws DebugException {
		IVariable variable = mock(IVariable.class);
		when(variable.getName()).thenReturn(name);
		return variable;
	}

	private IValue mockValueWithVariables(IVariable[] variables) throws DebugException {
		IValue revisionValue = mock(IValue.class);
		when(revisionValue.getVariables()).thenReturn(variables);
		return revisionValue;
	}

	private IValue mockStringValue(String value) throws DebugException {
		IValue transientValue = mock(IValue.class);
		when(transientValue.getValueString()).thenReturn(value);
		return transientValue;
	}

}
