/**
 */
package cDOWebPage.impl;

import cDOWebPage.*;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.impl.EFactoryImpl;

import org.eclipse.emf.ecore.plugin.EcorePlugin;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class CDOWebPageFactoryImpl extends EFactoryImpl implements CDOWebPageFactory {
	/**
	 * Creates the default factory implementation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static CDOWebPageFactory init() {
		try {
			CDOWebPageFactory theCDOWebPageFactory = (CDOWebPageFactory) EPackage.Registry.INSTANCE
					.getEFactory(CDOWebPagePackage.eNS_URI);
			if (theCDOWebPageFactory != null) {
				return theCDOWebPageFactory;
			}
		} catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new CDOWebPageFactoryImpl();
	}

	/**
	 * Creates an instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public CDOWebPageFactoryImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EObject create(EClass eClass) {
		switch (eClass.getClassifierID()) {
		case CDOWebPagePackage.CDO_WEB_PAGE:
			return (EObject) createCDOWebPage();
		default:
			throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public CDOWebPage createCDOWebPage() {
		CDOWebPageImpl cdoWebPage = new CDOWebPageImpl();
		return cdoWebPage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public CDOWebPagePackage getCDOWebPagePackage() {
		return (CDOWebPagePackage) getEPackage();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @deprecated
	 * @generated
	 */
	@Deprecated
	public static CDOWebPagePackage getPackage() {
		return CDOWebPagePackage.eINSTANCE;
	}

} //CDOWebPageFactoryImpl
