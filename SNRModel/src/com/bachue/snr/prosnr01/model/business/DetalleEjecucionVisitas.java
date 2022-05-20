package com.bachue.snr.prosnr01.model.business;

import com.bachue.snr.prosnr01.model.auditoria.Auditoria;
import com.bachue.snr.prosnr01.model.sdb.acc.OficiosTexto;
import com.bachue.snr.prosnr01.model.sdb.pgn.TagPlantillaResolucion;

import java.io.Serializable;

import java.util.Collection;


/**
 * Clase que contiene todos las propiedades DetalleEjecucionVisitas.
 *
 * @author  Jorge Esteban Patiño Fonseca
 * Fecha de Creacion: 11/11/2021
 */
public class DetalleEjecucionVisitas extends Auditoria implements Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = -1264840985047737754L;

	/** Propiedad ictpr campos pantalla. */
	private Collection<TagPlantillaResolucion> ictpr_camposPantalla;

	/** Propiedad iot solucion tags. */
	private OficiosTexto iot_solucionTags;

	/** Propiedad is id constante. */
	private String is_idConstante;

	/** Propiedad is id solicitud visitas. */
	private String is_idSolicitudVisitas;

	/** Propiedad iba documento. */
	private byte[] iba_documento;

	/**
	 * Retorna Objeto o variable de valor campos pantalla.
	 *
	 * @return el valor de campos pantalla
	 */
	public Collection<TagPlantillaResolucion> getCamposPantalla()
	{
		return ictpr_camposPantalla;
	}

	/**
	 * Modifica el valor de CamposPantalla.
	 *
	 * @param actpr_ctpr de actpr ctpr
	 */
	public void setCamposPantalla(Collection<TagPlantillaResolucion> actpr_ctpr)
	{
		ictpr_camposPantalla = actpr_ctpr;
	}

	/**
	 * Retorna Objeto o variable de valor documento.
	 *
	 * @return el valor de documento
	 */
	public byte[] getDocumento()
	{
		return iba_documento;
	}

	/**
	 * Modifica el valor de Documento.
	 *
	 * @param aba_ba de aba ba
	 */
	public void setDocumento(byte[] aba_ba)
	{
		iba_documento = aba_ba;
	}

	/**
	 * Retorna Objeto o variable de valor solucion tags.
	 *
	 * @return el valor de solucion tags
	 */
	public OficiosTexto getSolucionTags()
	{
		return iot_solucionTags;
	}

	/**
	 * Modifica el valor de SolucionTags.
	 *
	 * @param aot_ot de aot ot
	 */
	public void setSolucionTags(OficiosTexto aot_ot)
	{
		iot_solucionTags = aot_ot;
	}

	/**
	 * Retorna Objeto o variable de valor id constante.
	 *
	 * @return el valor de id constante
	 */
	public String getIdConstante()
	{
		return is_idConstante;
	}

	/**
	 * Modifica el valor de IdConstante.
	 *
	 * @param as_s de as s
	 */
	public void setIdConstante(String as_s)
	{
		is_idConstante = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor id solicitud visitas.
	 *
	 * @return el valor de id solicitud visitas
	 */
	public String getIdSolicitudVisitas()
	{
		return is_idSolicitudVisitas;
	}

	/**
	 * Modifica el valor de IdSolicitudVisitas.
	 *
	 * @param as_s de as s
	 */
	public void setIdSolicitudVisitas(String as_s)
	{
		is_idSolicitudVisitas = as_s;
	}
}
