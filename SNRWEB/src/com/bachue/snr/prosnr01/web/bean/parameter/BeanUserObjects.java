package com.bachue.snr.prosnr01.web.bean.parameter;

import com.bachue.snr.prosnr01.model.objectDataBase.UserObjects;

import com.bachue.snr.prosnr01.web.bean.BaseBean;

import java.io.Serializable;

import java.util.Collection;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;


/**
 * Clase que contiene todos las propiedades y acciones de BeanUserObjects.
 *
 * @author Duvan Beltran
 */
@ManagedBean(name = "beanUserObjects")
@SessionScoped
public class BeanUserObjects extends BaseBean implements Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 8972493392938939736L;

	/** Propiedad icuo columnas tablas. */
	private Collection<UserObjects> icuo_columnasTabla;

	/** Propiedad is nombre tabla. */
	private String is_nombreTabla;

	/** Propiedad ic user objects. */
	private UserObjects ic_userObjects;

	/** {@inheritdoc} */
	@Override
	public String getApplication()
	{
		return null;
	}

	/**
	 * @param acuo_cuo asigna el valor a la propiedad
	 */
	public void setColumnasTabla(Collection<UserObjects> acuo_cuo)
	{
		icuo_columnasTabla = acuo_cuo;
	}

	/**
	 * @return devuelve el valor de la propiedad
	 */
	public Collection<UserObjects> getColumnasTabla()
	{
		return icuo_columnasTabla;
	}

	/**
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setNombreTabla(String as_s)
	{
		is_nombreTabla = as_s;
	}

	/**
	 * @return devuelve el valor de la propiedad
	 */
	public String getNombreTabla()
	{
		return is_nombreTabla;
	}

	/**
	 * @param ac_c asigna el valor a la propiedad
	 */
	public void setUserObjects(UserObjects ac_c)
	{
		ic_userObjects = ac_c;
	}

	/**
	 * @return devuelve el valor de la propiedad
	 */
	public UserObjects getUserObjects()
	{
		if(ic_userObjects == null)
			ic_userObjects = new UserObjects();

		return ic_userObjects;
	}
}
