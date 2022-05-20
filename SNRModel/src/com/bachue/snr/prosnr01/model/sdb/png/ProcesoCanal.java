package com.bachue.snr.prosnr01.model.sdb.png;

import com.bachue.snr.prosnr01.model.auditoria.Auditoria;

import java.io.Serializable;
import java.util.Collection;



/**
 * Clase que contiene todos las propiedades ProcesoCanal.
 *
 * @author  Jorge Esteban Patiño Fonseca
 * Fecha de Creacion: 11/06/2020
 */
public class ProcesoCanal extends Auditoria implements Serializable
{
	
	/** Constante serialVersionUID. */
	private static final long serialVersionUID     = 5782727059118237000L;
	
	/** Propiedad icpc proceso canal. */
	private Collection<ProcesoCanal> icpc_procesoCanal;;
	
	/** Propiedad is id proceso. */
	private String            is_idProceso;
	
	/** Propiedad is id subproceso. */
	private String            is_idSubproceso;
	
	/** Propiedad is id tipo canal origen. */
	private String            is_idTipoCanalOrigen;
	
	/** Propiedad is activo. */
	private String            is_activo;

	/**
	 * Modifica el valor de IdProceso.
	 *
	 * @param as_s de as s
	 */
	public void setIdProceso(String as_s)
	{
		is_idProceso                               = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor id proceso.
	 *
	 * @return el valor de id proceso
	 */
	public String getIdProceso()
	{
		return is_idProceso;
	}

	/**
	 * Modifica el valor de IdSubProceso.
	 *
	 * @param as_s de as s
	 */
	public void setIdSubProceso(String as_s)
	{
		is_idSubproceso = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor id sub proceso.
	 *
	 * @return el valor de id sub proceso
	 */
	public String getIdSubProceso()
	{
		return is_idSubproceso;
	}

	/**
	 * Modifica el valor de IdTipoCanalOrigen.
	 *
	 * @param as_s de as s
	 */
	public void setIdTipoCanalOrigen(String as_s)
	{
		is_idTipoCanalOrigen = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor id tipo canal origen.
	 *
	 * @return el valor de id tipo canal origen
	 */
	public String getIdTipoCanalOrigen()
	{
		return is_idTipoCanalOrigen;
	}

	/**
	 * Retorna Objeto o variable de valor proceso canal.
	 *
	 * @return el valor de proceso canal
	 */
	public Collection<ProcesoCanal> getProcesoCanal() {
		return icpc_procesoCanal;
	}

	/**
	 * Modifica el valor de ProcesoCanal.
	 *
	 * @param acpc_pc de acpc pc
	 */
	public void setProcesoCanal(Collection<ProcesoCanal> acpc_pc) {
		icpc_procesoCanal = acpc_pc;
	}

	/**
	 * Retorna Objeto o variable de valor activo.
	 *
	 * @return el valor de activo
	 */
	public String getActivo() {
		return is_activo;
	}

	/**
	 * Modifica el valor de Activo.
	 *
	 * @param as_a de as a
	 */
	public void setActivo(String as_a) {
		is_activo = as_a;
	}
}
