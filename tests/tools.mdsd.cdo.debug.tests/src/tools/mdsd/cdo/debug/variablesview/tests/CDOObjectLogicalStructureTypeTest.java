package tools.mdsd.cdo.debug.variablesview.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.debug.core.DebugException;
import org.eclipse.debug.core.model.IValue;
import org.eclipse.debug.core.model.IVariable;
import org.junit.jupiter.api.Test;

import tools.mdsd.cdo.debug.variablesview.CDOObjectLogicalStructureType;

class CDOObjectLogicalStructureTypeTest {

	@Test
	void testProvidesLogicalStructure() throws DebugException {
		IValue value = mockValueWithVariables(
			new IVariable[] {
				mockVariables(new String[] { "eSettings" }),
				mockVariables(new String[] { "viewAndState", "state", "name" }, mockStringValue("TRANSIENT")),
				mockVariables(new String[] { "revision", "classInfo" })
			});

		boolean providesLogicalStructure = new CDOObjectLogicalStructureType().providesLogicalStructure(value);

		assertTrue(providesLogicalStructure);
	}

	@Test
	void testProvidesLogicalStructureWithNonLiteralStateName() throws DebugException {
		IValue value = mockValueWithVariables(
			new IVariable[] {
				mockVariables(new String[] { "eSettings" }),
				mockVariables(new String[] { "viewAndState", "state", "name" },
					mockStringValue(new String("TRANSIENT"))),
				mockVariables(new String[] { "revision", "classInfo" })
			});

		boolean providesLogicalStructure = new CDOObjectLogicalStructureType().providesLogicalStructure(value);

		assertTrue(providesLogicalStructure);
	}

	@Test
	void testDoesNotProvideLogicalStructureWhenEsettingsMissing() throws DebugException {
		IValue value = mockValueWithVariables(
			new IVariable[] {
				mockVariables(new String[] { "viewAndState", "state", "name" }, mockStringValue("TRANSIENT")),
				mockVariables(new String[] { "revision", "classInfo" })
			});

		boolean providesLogicalStructure = new CDOObjectLogicalStructureType().providesLogicalStructure(value);

		assertFalse(providesLogicalStructure);
	}

	@Test
	void testDoesNotProvideLogicalStructureWhenViewAndStateMissing() throws DebugException {
		IValue value = mockValueWithVariables(
			new IVariable[] {
				mockVariables(new String[] { "eSettings" }),
				mockVariables(new String[] { "revision", "classInfo" })
			});

		boolean providesLogicalStructure = new CDOObjectLogicalStructureType().providesLogicalStructure(value);

		assertFalse(providesLogicalStructure);
	}

	@Test
	void testDoesNotProvideLogicalStructureWhenViewAndStateStateStateMissing() throws DebugException {
		IValue value = mockValueWithVariables(
			new IVariable[] {
				mockVariables(new String[] { "eSettings" }),
				mockVariables(new String[] { "viewAndState", "foo" }),
				mockVariables(new String[] { "revision", "classInfo" })
			});

		boolean providesLogicalStructure = new CDOObjectLogicalStructureType().providesLogicalStructure(value);

		assertFalse(providesLogicalStructure);
	}

	@Test
	void testDoesNotProvideLogicalStructureWhenViewAndStateStateNameMissing() throws DebugException {
		IValue value = mockValueWithVariables(
			new IVariable[] {
				mockVariables(new String[] { "eSettings" }),
				mockVariables(new String[] { "viewAndState", "state", "foo" }),
				mockVariables(new String[] { "revision", "classInfo" })
			});

		boolean providesLogicalStructure = new CDOObjectLogicalStructureType().providesLogicalStructure(value);

		assertFalse(providesLogicalStructure);
	}

	@Test
	void testDoesNotProvideLogicalStructureWhenADebugExceptionIsThrown() throws DebugException {
		IValue value = mock(IValue.class);
		when(value.getVariables()).thenThrow(DebugException.class);

		boolean providesLogicalStructure = new CDOObjectLogicalStructureType().providesLogicalStructure(value);

		assertFalse(providesLogicalStructure);
	}

	@Test
	void testDoesNotProvideLogicalStructureWhenClassInfoMissing() throws DebugException {
		IValue value = mockValueWithVariables(
			new IVariable[] {
				mockVariables(new String[] { "eSettings" }),
				mockVariables(new String[] { "viewAndState", "state", "name" }, mockStringValue("TRANSIENT"))
			});

		boolean providesLogicalStructure = new CDOObjectLogicalStructureType().providesLogicalStructure(value);

		assertFalse(providesLogicalStructure);
	}

	@Test
	void testGetLogicalStructureReturnsAMappedField() throws CoreException {
		IValue value = mockValueWithVariables(
			new IVariable[] {
				mockRevisionClassInfo(),
				mockVariables(new String[] { "eSettings" }, mockValueWithVariables(new IVariable[] {
					mockVariables(new String[] { "any" }, mockStringValue("bar"))
				}))
			});

		IValue logicalStructure = new CDOObjectLogicalStructureType().getLogicalStructure(value);
		IVariable[] variables = logicalStructure.getVariables();

		assertEquals("foo", variables[0].getName());
		assertEquals("bar", variables[0].getValue().getValueString());
	}

	@Test
	void testGetLogicalStructureReturnsEFlags() throws CoreException {
		IValue eFlags = mock(IValue.class);
		IValue value = mockValueWithVariables(
			new IVariable[] {
				mockRevisionClassInfo(),
				mockVariables(new String[] { "eFlags" }, eFlags),
				mockVariables(new String[] { "eSettings" }, mockValueWithVariables(new IVariable[] {
					mockVariables(new String[] { "any" }, mockStringValue("bar"))
				}))
			});

		IValue logicalStructure = new CDOObjectLogicalStructureType().getLogicalStructure(value);
		IVariable[] variables = logicalStructure.getVariables();

		assertEquals("eFlags", variables[1].getName());
		assertEquals(eFlags, variables[1].getValue());
	}

	@Test
	void testGetLogicalStructureReturnsEStorage() throws CoreException {
		IValue eStorage = mock(IValue.class);
		IValue value = mockValueWithVariables(
			new IVariable[] {
				mockVariables(
					new String[] { "revision", "classInfo" },
					mockValueWithVariables(
						new IVariable[] {
							mockVariables(
								new String[] { "eClass", "eAllStructuralFeatures", "data" },
								mockValueWithVariables(new IVariable[] {
									mockVariables(new String[] { "any" }, mockValueWithVariables(new IVariable[] {
										mockVariables(new String[] { "name" }, mockStringValue("foo"))
									})),
								})),
							mockVariables(new String[] { "transientFeatureIndices" },
								mockValueWithVariables(new IVariable[] {
									mockVariables(new String[] { "any" }, mockStringValue("0")),
								}))
						})),
				mockVariables(new String[] { "eStorage" }, eStorage),
				mockVariables(new String[] { "eSettings" }, mockValueWithVariables(new IVariable[] {
					mockVariables(new String[] { "any" }, mockStringValue("bar"))
				}))
			});

		IValue logicalStructure = new CDOObjectLogicalStructureType().getLogicalStructure(value);
		IVariable[] variables = logicalStructure.getVariables();

		assertEquals("eStorage", variables[1].getName());
		assertEquals(eStorage, variables[1].getValue());
	}

	@Test
	void testGetLogicalStructureReturnsEContainer() throws CoreException {
		IValue eContainer = mock(IValue.class);
		IValue value = mockValueWithVariables(
			new IVariable[] {
				mockVariables(
					new String[] { "revision", "classInfo" }, mockValueWithVariables(
						new IVariable[] {
							mockVariables(
								new String[] { "eClass" }, mockValueWithVariables(
									new IVariable[] {
										mockVariables(new String[] { "eAllStructuralFeatures", "data" },
											mockValueWithVariables(new IVariable[] {
												mockVariables(new String[] { "any" },
													mockValueWithVariables(new IVariable[] {
														mockVariables(new String[] { "name" }, mockStringValue("foo"))
													})),
											})),
										mockVariables(new String[] { "eContainer" }, eContainer)
									})),
							mockVariables(new String[] { "transientFeatureIndices" },
								mockValueWithVariables(new IVariable[] {
									mockVariables(new String[] { "any" }, mockStringValue("0")),
								}))
						})),
				mockVariables(new String[] { "eSettings" }, mockValueWithVariables(new IVariable[] {
					mockVariables(new String[] { "any" }, mockStringValue("bar"))
				}))
			});

		IValue logicalStructure = new CDOObjectLogicalStructureType().getLogicalStructure(value);
		IVariable[] variables = logicalStructure.getVariables();

		assertEquals("eContainer", variables[1].getName());
		assertEquals(eContainer, variables[1].getValue());
	}

	@Test
	void testGetLogicalStructureReturnsTwoMappedFieldsWithNamesOrderedAccordingToTransientFeatureIndices()
		throws CoreException {
		IValue value = mockValueWithVariables(
			new IVariable[] {
				mockVariables(
					new String[] { "revision", "classInfo" }, mockValueWithVariables(
						new IVariable[] {
							mockVariables(
								new String[] { "eClass", "eAllStructuralFeatures", "data" },
								mockValueWithVariables(new IVariable[] {
									mockVariables(new String[] { "any" }, mockValueWithVariables(new IVariable[] {
										mockVariables(new String[] { "name" }, mockStringValue("foo1"))
									})),
									mockVariables(new String[] { "any" }, mockValueWithVariables(new IVariable[] {
										mockVariables(new String[] { "name" }, mockStringValue("foo2"))
									})),
								})),
							mockVariables(new String[] { "transientFeatureIndices" },
								mockValueWithVariables(new IVariable[] {
									mockVariables(new String[] { "any" }, mockStringValue("1")),
									mockVariables(new String[] { "any" }, mockStringValue("0")),
								}))
						})),
				mockVariables(new String[] { "eSettings" }, mockValueWithVariables(new IVariable[] {
					mockVariables(new String[] { "any" }, mockStringValue("bar2")),
					mockVariables(new String[] { "any" }, mockStringValue("bar1"))
				}))
			});

		IValue logicalStructure = new CDOObjectLogicalStructureType().getLogicalStructure(value);
		IVariable[] variables = logicalStructure.getVariables();

		assertEquals("foo2", variables[0].getName());
		assertEquals("bar2", variables[0].getValue().getValueString());
		assertEquals("foo1", variables[1].getName());
		assertEquals("bar1", variables[1].getValue().getValueString());
	}

	@Test
	void testGetLogicalStructureSkipsFieldsWithoutTransientFeatureIndex() throws CoreException {
		IValue value = mockValueWithVariables(
			new IVariable[] {
				mockVariables(
					new String[] { "revision", "classInfo" }, mockValueWithVariables(
						new IVariable[] {
							mockVariables(
								new String[] { "eClass", "eAllStructuralFeatures", "data" },
								mockValueWithVariables(new IVariable[] {
									mockVariables(new String[] { "any" }, mockValueWithVariables(new IVariable[] {
										mockVariables(new String[] { "name" }, mockStringValue("foo"))
									}))
								})),
							mockVariables(new String[] { "transientFeatureIndices" },
								mockValueWithVariables(new IVariable[] {
									mockVariables(new String[] { "any" }, mockStringValue("5"))
								}))
						})),
				mockVariables(new String[] { "eSettings" }, mockValueWithVariables(new IVariable[] {
					mockVariables(new String[] { "any" }, mockStringValue("bar"))
				}))
			});

		IValue logicalStructure = new CDOObjectLogicalStructureType().getLogicalStructure(value);
		IVariable[] variables = logicalStructure.getVariables();

		assertEquals(0, variables.length);
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

	private IVariable mockRevisionClassInfo() throws DebugException {
		return mockVariables(
			new String[] { "revision", "classInfo" },
			mockValueWithVariables(
				new IVariable[] {
					mockVariables(
						new String[] { "eClass", "eAllStructuralFeatures", "data" },
						mockValueWithVariables(new IVariable[] {
							mockVariables(new String[] { "any" }, mockValueWithVariables(new IVariable[] {
								mockVariables(new String[] { "name" }, mockStringValue("foo"))
							})),
						})),
					mockVariables(new String[] { "transientFeatureIndices" },
						mockValueWithVariables(new IVariable[] {
							mockVariables(new String[] { "any" }, mockStringValue("0")),
						}))
				}));
	}

}
