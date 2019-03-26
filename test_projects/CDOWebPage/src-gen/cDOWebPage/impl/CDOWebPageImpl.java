/**
 */
package cDOWebPage.impl;

import cDOWebPage.CDOWebPage;
import cDOWebPage.CDOWebPagePackage;

import java.util.Collection;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.internal.cdo.CDOObjectImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>CDO Web Page</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link cDOWebPage.impl.CDOWebPageImpl#getTitle <em>Title</em>}</li>
 *   <li>{@link cDOWebPage.impl.CDOWebPageImpl#getMultivaluedAttribute <em>Multivalued Attribute</em>}</li>
 *   <li>{@link cDOWebPage.impl.CDOWebPageImpl#getTransientAttribute <em>Transient Attribute</em>}</li>
 * </ul>
 *
 * @generated
 */
public class CDOWebPageImpl extends CDOObjectImpl implements CDOWebPage {
	/**
	 * The default value of the '{@link #getTitle() <em>Title</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTitle()
	 * @generated
	 * @ordered
	 */
	protected static final String TITLE_EDEFAULT = null;

	/**
	 * The default value of the '{@link #getTransientAttribute() <em>Transient Attribute</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTransientAttribute()
	 * @generated
	 * @ordered
	 */
	protected static final String TRANSIENT_ATTRIBUTE_EDEFAULT = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected CDOWebPageImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return CDOWebPagePackage.Literals.CDO_WEB_PAGE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected int eStaticFeatureCount() {
		return 0;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getTitle() {
		return (String) eDynamicGet(CDOWebPagePackage.CDO_WEB_PAGE__TITLE,
				CDOWebPagePackage.Literals.CDO_WEB_PAGE__TITLE, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setTitle(String newTitle) {
		eDynamicSet(CDOWebPagePackage.CDO_WEB_PAGE__TITLE, CDOWebPagePackage.Literals.CDO_WEB_PAGE__TITLE, newTitle);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public EList<String> getMultivaluedAttribute() {
		return (EList<String>) eDynamicGet(CDOWebPagePackage.CDO_WEB_PAGE__MULTIVALUED_ATTRIBUTE,
				CDOWebPagePackage.Literals.CDO_WEB_PAGE__MULTIVALUED_ATTRIBUTE, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getTransientAttribute() {
		return (String) eDynamicGet(CDOWebPagePackage.CDO_WEB_PAGE__TRANSIENT_ATTRIBUTE,
				CDOWebPagePackage.Literals.CDO_WEB_PAGE__TRANSIENT_ATTRIBUTE, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setTransientAttribute(String newTransientAttribute) {
		eDynamicSet(CDOWebPagePackage.CDO_WEB_PAGE__TRANSIENT_ATTRIBUTE,
				CDOWebPagePackage.Literals.CDO_WEB_PAGE__TRANSIENT_ATTRIBUTE, newTransientAttribute);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
		case CDOWebPagePackage.CDO_WEB_PAGE__TITLE:
			return getTitle();
		case CDOWebPagePackage.CDO_WEB_PAGE__MULTIVALUED_ATTRIBUTE:
			return getMultivaluedAttribute();
		case CDOWebPagePackage.CDO_WEB_PAGE__TRANSIENT_ATTRIBUTE:
			return getTransientAttribute();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
		case CDOWebPagePackage.CDO_WEB_PAGE__TITLE:
			setTitle((String) newValue);
			return;
		case CDOWebPagePackage.CDO_WEB_PAGE__MULTIVALUED_ATTRIBUTE:
			getMultivaluedAttribute().clear();
			getMultivaluedAttribute().addAll((Collection<? extends String>) newValue);
			return;
		case CDOWebPagePackage.CDO_WEB_PAGE__TRANSIENT_ATTRIBUTE:
			setTransientAttribute((String) newValue);
			return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eUnset(int featureID) {
		switch (featureID) {
		case CDOWebPagePackage.CDO_WEB_PAGE__TITLE:
			setTitle(TITLE_EDEFAULT);
			return;
		case CDOWebPagePackage.CDO_WEB_PAGE__MULTIVALUED_ATTRIBUTE:
			getMultivaluedAttribute().clear();
			return;
		case CDOWebPagePackage.CDO_WEB_PAGE__TRANSIENT_ATTRIBUTE:
			setTransientAttribute(TRANSIENT_ATTRIBUTE_EDEFAULT);
			return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean eIsSet(int featureID) {
		switch (featureID) {
		case CDOWebPagePackage.CDO_WEB_PAGE__TITLE:
			return TITLE_EDEFAULT == null ? getTitle() != null : !TITLE_EDEFAULT.equals(getTitle());
		case CDOWebPagePackage.CDO_WEB_PAGE__MULTIVALUED_ATTRIBUTE:
			return !getMultivaluedAttribute().isEmpty();
		case CDOWebPagePackage.CDO_WEB_PAGE__TRANSIENT_ATTRIBUTE:
			return TRANSIENT_ATTRIBUTE_EDEFAULT == null ? getTransientAttribute() != null
					: !TRANSIENT_ATTRIBUTE_EDEFAULT.equals(getTransientAttribute());
		}
		return super.eIsSet(featureID);
	}

} //CDOWebPageImpl
