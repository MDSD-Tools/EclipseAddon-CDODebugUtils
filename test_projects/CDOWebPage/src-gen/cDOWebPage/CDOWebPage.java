/**
 */
package cDOWebPage;

import org.eclipse.emf.cdo.CDOObject;
import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>CDO Web Page</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link cDOWebPage.CDOWebPage#getTitle <em>Title</em>}</li>
 *   <li>{@link cDOWebPage.CDOWebPage#getMultivaluedAttribute <em>Multivalued Attribute</em>}</li>
 *   <li>{@link cDOWebPage.CDOWebPage#getTransientAttribute <em>Transient Attribute</em>}</li>
 * </ul>
 *
 * @see cDOWebPage.CDOWebPagePackage#getCDOWebPage()
 * @model
 * @extends CDOObject
 * @generated
 */
public interface CDOWebPage extends CDOObject {
	/**
	 * Returns the value of the '<em><b>Title</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Title</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Title</em>' attribute.
	 * @see #setTitle(String)
	 * @see cDOWebPage.CDOWebPagePackage#getCDOWebPage_Title()
	 * @model
	 * @generated
	 */
	String getTitle();

	/**
	 * Sets the value of the '{@link cDOWebPage.CDOWebPage#getTitle <em>Title</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Title</em>' attribute.
	 * @see #getTitle()
	 * @generated
	 */
	void setTitle(String value);

	/**
	 * Returns the value of the '<em><b>Multivalued Attribute</b></em>' attribute list.
	 * The list contents are of type {@link java.lang.String}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Multivalued Attribute</em>' attribute list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Multivalued Attribute</em>' attribute list.
	 * @see cDOWebPage.CDOWebPagePackage#getCDOWebPage_MultivaluedAttribute()
	 * @model
	 * @generated
	 */
	EList<String> getMultivaluedAttribute();

	/**
	 * Returns the value of the '<em><b>Transient Attribute</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Transient Attribute</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Transient Attribute</em>' attribute.
	 * @see #setTransientAttribute(String)
	 * @see cDOWebPage.CDOWebPagePackage#getCDOWebPage_TransientAttribute()
	 * @model transient="true"
	 * @generated
	 */
	String getTransientAttribute();

	/**
	 * Sets the value of the '{@link cDOWebPage.CDOWebPage#getTransientAttribute <em>Transient Attribute</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Transient Attribute</em>' attribute.
	 * @see #getTransientAttribute()
	 * @generated
	 */
	void setTransientAttribute(String value);

} // CDOWebPage
