/**
 */
package cDOWebPage;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see cDOWebPage.CDOWebPagePackage
 * @generated
 */
public interface CDOWebPageFactory extends EFactory {
	/**
	 * The singleton instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	CDOWebPageFactory eINSTANCE = cDOWebPage.impl.CDOWebPageFactoryImpl.init();

	/**
	 * Returns a new object of class '<em>CDO Web Page</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>CDO Web Page</em>'.
	 * @generated
	 */
	CDOWebPage createCDOWebPage();

	/**
	 * Returns the package supported by this factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the package supported by this factory.
	 * @generated
	 */
	CDOWebPagePackage getCDOWebPagePackage();

} //CDOWebPageFactory
