/**
 */
package cDOWebPage;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;

/**
 * <!-- begin-user-doc -->
 * The <b>Package</b> for the model.
 * It contains accessors for the meta objects to represent
 * <ul>
 *   <li>each class,</li>
 *   <li>each feature of each class,</li>
 *   <li>each operation of each class,</li>
 *   <li>each enum,</li>
 *   <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc -->
 * @see cDOWebPage.CDOWebPageFactory
 * @model kind="package"
 * @generated
 */
public interface CDOWebPagePackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "cDOWebPage";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http://www.example.org/cDOWebPage";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "cDOWebPage";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	CDOWebPagePackage eINSTANCE = cDOWebPage.impl.CDOWebPagePackageImpl.init();

	/**
	 * The meta object id for the '{@link cDOWebPage.impl.CDOWebPageImpl <em>CDO Web Page</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see cDOWebPage.impl.CDOWebPageImpl
	 * @see cDOWebPage.impl.CDOWebPagePackageImpl#getCDOWebPage()
	 * @generated
	 */
	int CDO_WEB_PAGE = 0;

	/**
	 * The feature id for the '<em><b>Title</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CDO_WEB_PAGE__TITLE = 0;

	/**
	 * The feature id for the '<em><b>Multivalued Attribute</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CDO_WEB_PAGE__MULTIVALUED_ATTRIBUTE = 1;

	/**
	 * The feature id for the '<em><b>Transient Attribute</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CDO_WEB_PAGE__TRANSIENT_ATTRIBUTE = 2;

	/**
	 * The number of structural features of the '<em>CDO Web Page</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CDO_WEB_PAGE_FEATURE_COUNT = 3;

	/**
	 * The number of operations of the '<em>CDO Web Page</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CDO_WEB_PAGE_OPERATION_COUNT = 0;

	/**
	 * Returns the meta object for class '{@link cDOWebPage.CDOWebPage <em>CDO Web Page</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>CDO Web Page</em>'.
	 * @see cDOWebPage.CDOWebPage
	 * @generated
	 */
	EClass getCDOWebPage();

	/**
	 * Returns the meta object for the attribute '{@link cDOWebPage.CDOWebPage#getTitle <em>Title</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Title</em>'.
	 * @see cDOWebPage.CDOWebPage#getTitle()
	 * @see #getCDOWebPage()
	 * @generated
	 */
	EAttribute getCDOWebPage_Title();

	/**
	 * Returns the meta object for the attribute list '{@link cDOWebPage.CDOWebPage#getMultivaluedAttribute <em>Multivalued Attribute</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Multivalued Attribute</em>'.
	 * @see cDOWebPage.CDOWebPage#getMultivaluedAttribute()
	 * @see #getCDOWebPage()
	 * @generated
	 */
	EAttribute getCDOWebPage_MultivaluedAttribute();

	/**
	 * Returns the meta object for the attribute '{@link cDOWebPage.CDOWebPage#getTransientAttribute <em>Transient Attribute</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Transient Attribute</em>'.
	 * @see cDOWebPage.CDOWebPage#getTransientAttribute()
	 * @see #getCDOWebPage()
	 * @generated
	 */
	EAttribute getCDOWebPage_TransientAttribute();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	CDOWebPageFactory getCDOWebPageFactory();

	/**
	 * <!-- begin-user-doc -->
	 * Defines literals for the meta objects that represent
	 * <ul>
	 *   <li>each class,</li>
	 *   <li>each feature of each class,</li>
	 *   <li>each operation of each class,</li>
	 *   <li>each enum,</li>
	 *   <li>and each data type</li>
	 * </ul>
	 * <!-- end-user-doc -->
	 * @generated
	 */
	interface Literals {
		/**
		 * The meta object literal for the '{@link cDOWebPage.impl.CDOWebPageImpl <em>CDO Web Page</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see cDOWebPage.impl.CDOWebPageImpl
		 * @see cDOWebPage.impl.CDOWebPagePackageImpl#getCDOWebPage()
		 * @generated
		 */
		EClass CDO_WEB_PAGE = eINSTANCE.getCDOWebPage();

		/**
		 * The meta object literal for the '<em><b>Title</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CDO_WEB_PAGE__TITLE = eINSTANCE.getCDOWebPage_Title();

		/**
		 * The meta object literal for the '<em><b>Multivalued Attribute</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CDO_WEB_PAGE__MULTIVALUED_ATTRIBUTE = eINSTANCE.getCDOWebPage_MultivaluedAttribute();

		/**
		 * The meta object literal for the '<em><b>Transient Attribute</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CDO_WEB_PAGE__TRANSIENT_ATTRIBUTE = eINSTANCE.getCDOWebPage_TransientAttribute();

	}

} //CDOWebPagePackage
