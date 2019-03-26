/**
 */
package cDOWebPage.impl;

import cDOWebPage.CDOWebPage;
import cDOWebPage.CDOWebPageFactory;
import cDOWebPage.CDOWebPagePackage;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.impl.EPackageImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class CDOWebPagePackageImpl extends EPackageImpl implements CDOWebPagePackage {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass cdoWebPageEClass = null;

	/**
	 * Creates an instance of the model <b>Package</b>, registered with
	 * {@link org.eclipse.emf.ecore.EPackage.Registry EPackage.Registry} by the package
	 * package URI value.
	 * <p>Note: the correct way to create the package is via the static
	 * factory method {@link #init init()}, which also performs
	 * initialization of the package, or returns the registered package,
	 * if one already exists.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.emf.ecore.EPackage.Registry
	 * @see cDOWebPage.CDOWebPagePackage#eNS_URI
	 * @see #init()
	 * @generated
	 */
	private CDOWebPagePackageImpl() {
		super(eNS_URI, CDOWebPageFactory.eINSTANCE);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static boolean isInited = false;

	/**
	 * Creates, registers, and initializes the <b>Package</b> for this model, and for any others upon which it depends.
	 *
	 * <p>This method is used to initialize {@link CDOWebPagePackage#eINSTANCE} when that field is accessed.
	 * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #eNS_URI
	 * @see #createPackageContents()
	 * @see #initializePackageContents()
	 * @generated
	 */
	public static CDOWebPagePackage init() {
		if (isInited)
			return (CDOWebPagePackage) EPackage.Registry.INSTANCE.getEPackage(CDOWebPagePackage.eNS_URI);

		// Obtain or create and register package
		Object registeredCDOWebPagePackage = EPackage.Registry.INSTANCE.get(eNS_URI);
		CDOWebPagePackageImpl theCDOWebPagePackage = registeredCDOWebPagePackage instanceof CDOWebPagePackageImpl
				? (CDOWebPagePackageImpl) registeredCDOWebPagePackage
				: new CDOWebPagePackageImpl();

		isInited = true;

		// Create package meta-data objects
		theCDOWebPagePackage.createPackageContents();

		// Initialize created meta-data
		theCDOWebPagePackage.initializePackageContents();

		// Mark meta-data to indicate it can't be changed
		theCDOWebPagePackage.freeze();

		// Update the registry and return the package
		EPackage.Registry.INSTANCE.put(CDOWebPagePackage.eNS_URI, theCDOWebPagePackage);
		return theCDOWebPagePackage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getCDOWebPage() {
		return cdoWebPageEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getCDOWebPage_Title() {
		return (EAttribute) cdoWebPageEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getCDOWebPage_MultivaluedAttribute() {
		return (EAttribute) cdoWebPageEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getCDOWebPage_TransientAttribute() {
		return (EAttribute) cdoWebPageEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public CDOWebPageFactory getCDOWebPageFactory() {
		return (CDOWebPageFactory) getEFactoryInstance();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private boolean isCreated = false;

	/**
	 * Creates the meta-model objects for the package.  This method is
	 * guarded to have no affect on any invocation but its first.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void createPackageContents() {
		if (isCreated)
			return;
		isCreated = true;

		// Create classes and their features
		cdoWebPageEClass = createEClass(CDO_WEB_PAGE);
		createEAttribute(cdoWebPageEClass, CDO_WEB_PAGE__TITLE);
		createEAttribute(cdoWebPageEClass, CDO_WEB_PAGE__MULTIVALUED_ATTRIBUTE);
		createEAttribute(cdoWebPageEClass, CDO_WEB_PAGE__TRANSIENT_ATTRIBUTE);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private boolean isInitialized = false;

	/**
	 * Complete the initialization of the package and its meta-model.  This
	 * method is guarded to have no affect on any invocation but its first.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void initializePackageContents() {
		if (isInitialized)
			return;
		isInitialized = true;

		// Initialize package
		setName(eNAME);
		setNsPrefix(eNS_PREFIX);
		setNsURI(eNS_URI);

		// Create type parameters

		// Set bounds for type parameters

		// Add supertypes to classes

		// Initialize classes, features, and operations; add parameters
		initEClass(cdoWebPageEClass, CDOWebPage.class, "CDOWebPage", !IS_ABSTRACT, !IS_INTERFACE,
				IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getCDOWebPage_Title(), ecorePackage.getEString(), "title", null, 0, 1, CDOWebPage.class,
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getCDOWebPage_MultivaluedAttribute(), ecorePackage.getEString(), "multivaluedAttribute", null, 0,
				-1, CDOWebPage.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
				!IS_DERIVED, IS_ORDERED);
		initEAttribute(getCDOWebPage_TransientAttribute(), ecorePackage.getEString(), "transientAttribute", null, 0, 1,
				CDOWebPage.class, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
				!IS_DERIVED, IS_ORDERED);

		// Create resource
		createResource(eNS_URI);
	}

} //CDOWebPagePackageImpl
