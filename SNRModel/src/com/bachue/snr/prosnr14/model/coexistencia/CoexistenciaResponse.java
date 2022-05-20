package com.bachue.snr.prosnr14.model.coexistencia;

import com.bachue.snr.prosnr01.model.predio.Predio;
import com.bachue.snr.prosnr01.model.sdb.bng.PredioRegistro;
import com.bachue.snr.prosnr01.model.sdb.bng.PredioSegregado;
import com.bachue.snr.prosnr01.model.sdb.bng.Propietario;

import com.bachue.snr.prosnr10.model.catastro.PropietarioCatastro;

import java.io.Serializable;

import java.util.Collection;



/**
 * Clase que contiene todos las propiedades CoexistenciaResponse.
 *
 * @author  Carlos Calderón
 * Fecha de Creacion: 28/05/2020
 */
public class CoexistenciaResponse implements Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = -2273565921682747394L;

	/** Propiedad icp cll propietario. */
	private Collection<Propietario> icp_cllPropietario;

	/** Propiedad icpc cll propietario catastro. */
	private Collection<PropietarioCatastro> icpc_cllPropietarioCatastro;

	/** Propiedad icpr cll predio registro. */
	private Collection<PredioRegistro> icpr_cllPredioRegistro;

	/** Propiedad cll predio segregado. */
	private Collection<PredioSegregado> icps_cllPredioSegregado;

	/** Propiedad ip predio. */
	private Predio ip_predio;

	/** Propiedad mensaje convenio circulos. */
	private String is_mensajeConvenioCirculos;

	/**
	 * Instancia un nuevo objeto coexistencia response.
	 *
	 * @param ap_p de ap p
	 */
	public CoexistenciaResponse(Predio ap_p)
	{
		ip_predio = ap_p;
	}

	/**
	 * Instancia un nuevo objeto coexistencia response.
	 */
	public CoexistenciaResponse()
	{
	}

	/**
	 * Retorna Objeto o variable de valor mensaje convenio circulos.
	 *
	 * @return Retorna el valor de la propiedad mensajeConvenioCirculos
	 */
	public String getMensajeConvenioCirculos()
	{
		return is_mensajeConvenioCirculos;
	}

	/**
	 * Modifica el valor de MensajeConvenioCirculos.
	 *
	 * @param as_s de mensaje convenio circulos
	 */
	public void setMensajeConvenioCirculos(String as_s)
	{
		is_mensajeConvenioCirculos = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor cll predio segregado.
	 *
	 * @return Retorna el valor de la propiedad cllPredioSegregado
	 */
	public Collection<PredioSegregado> getCllPredioSegregado()
	{
		return icps_cllPredioSegregado;
	}

	/**
	 * Modifica el valor de CllPredioSegregado.
	 *
	 * @param acps_cps de cll predio segregado
	 */
	public void setCllPredioSegregado(Collection<PredioSegregado> acps_cps)
	{
		icps_cllPredioSegregado = acps_cps;
	}

	/**
	 * Retorna Objeto o variable de valor ip predio.
	 *
	 * @return Retorna el valor de la propiedad ip_predio
	 */
	public Predio getPredio()
	{
		return ip_predio;
	}

	/**
	 * Modifica el valor de Ip_predio.
	 *
	 * @param ap_p de ip predio
	 */
	public void setPredio(Predio ap_p)
	{
		ip_predio = ap_p;
	}

	/**
	 * Retorna Objeto o variable de valor cll propietario catastro.
	 *
	 * @return Retorna el valor de la propiedad cllPropietarioCatastro
	 */
	public Collection<PropietarioCatastro> getCllPropietarioCatastro()
	{
		return icpc_cllPropietarioCatastro;
	}

	/**
	 * Modifica el valor de CllPropietarioCatastro.
	 *
	 * @param acpc_cpc de acpc cpc
	 */
	public void setCllPropietarioCatastro(Collection<PropietarioCatastro> acpc_cpc)
	{
		icpc_cllPropietarioCatastro = acpc_cpc;
	}

	/**
	 * Retorna Objeto o variable de valor cll predio registro.
	 *
	 * @return Retorna el valor de la propiedad cllPredioRegistro
	 */
	public Collection<PredioRegistro> getCllPredioRegistro()
	{
		return icpr_cllPredioRegistro;
	}

	/**
	 * Modifica el valor de CllPredioRegistro.
	 *
	 * @param acpr_cpr de acpr cpr
	 */
	public void setCllPredioRegistro(Collection<PredioRegistro> acpr_cpr)
	{
		icpr_cllPredioRegistro = acpr_cpr;
	}

	/**
	 * Retorna Objeto o variable de valor cll propietario.
	 *
	 * @return Retorna el valor de la propiedad cllPropietario
	 */
	public Collection<Propietario> getCllPropietario()
	{
		return icp_cllPropietario;
	}

	/**
	 * Modifica el valor de CllPropietario.
	 *
	 * @param acp_cp de acp cp
	 */
	public void setCllPropietario(Collection<Propietario> acp_cp)
	{
		icp_cllPropietario = acp_cp;
	}
}
