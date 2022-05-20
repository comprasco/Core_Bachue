package com.bachue.snr.prosnr01.web.bean.parameter;

import com.b2bsg.common.exception.B2BException;

import com.bachue.snr.prosnr01.ejb.session.stateless.parameter.ParameterRemote;

import com.bachue.snr.prosnr01.model.sdb.aut.Rol;

import com.bachue.snr.prosnr01.web.bean.BaseBean;

import java.io.Serializable;

import java.util.Collection;

import javax.ejb.EJB;

import javax.faces.bean.ManagedBean;


/**
 * Clase que contiene todos la funcionalidad de reasignar turno del Bean
 *
 * @author Julian Vaca
 */
@ManagedBean(name = "reasignarTurno")
public class ReasignarTurnoBean extends BaseBean implements Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = -2910212182914012585L;

	/** Propiedad ipr parameter remote. */
	@EJB
	private ParameterRemote ipr_parameterRemote;

	/** {@inheritdoc} */
	@Override
	public String getApplication()
	{
		return null;
	}

	/**
	 * Retorna el valor del objeto de Collection de Rol.
	 *
	 * @return devuelve el valor de Collection de Rol
	 */
	public Collection<Rol> findAllRols()
	{
		Collection<Rol> lcr_constantes;
		lcr_constantes = null;

		try
		{
			lcr_constantes = ipr_parameterRemote.findAllRols(true);
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
		}

		return lcr_constantes;
	}
}
