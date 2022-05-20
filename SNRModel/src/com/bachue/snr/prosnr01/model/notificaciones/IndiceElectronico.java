package com.bachue.snr.prosnr01.model.notificaciones;

import com.bachue.snr.prosnr01.model.auditoria.Auditoria;
import com.bachue.snr.prosnr01.model.sdb.acc.DocumentosSalida;

import java.io.Serializable;



/**
 * Clase que contiene todos las propiedades IndiceElectronico.
 *
 * @author  Jorge Esteban Patiño Fonseca
 * Fecha de Creacion: 19/03/2020
 */
public class IndiceElectronico extends Auditoria implements Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 2116896135191487373L;

	/** Propiedad ids documento salida actualizar. */
	private DocumentosSalida ids_documentoSalidaActualizar;

	/** Propiedad il id turno historia anterior. */
	private Long il_idTurnoHistoriaAnterior;

	/** Propiedad il id turno historia nuevo. */
	private Long il_idTurnoHistoriaNuevo;

	/** Propiedad is job. */
	private String is_job;

	/**
	 * Instancia un nuevo objeto indice electronico.
	 */
	public IndiceElectronico()
	{
	}

	/**
	 * Instancia un nuevo objeto indice electronico.
	 *
	 * @param ads_documentoSalidaActualizar de ads documento salida actualizar
	 * @param al_idTurnoHistoriaAnterior de al id turno historia anterior
	 * @param al_idTurnoHistoriaNuevo de al id turno historia nuevo
	 * @param as_job de as job
	 */
	public IndiceElectronico(
	    DocumentosSalida ads_documentoSalidaActualizar, Long al_idTurnoHistoriaAnterior, Long al_idTurnoHistoriaNuevo,
	    String as_job
	)
	{
		ids_documentoSalidaActualizar     = ads_documentoSalidaActualizar;
		il_idTurnoHistoriaAnterior        = al_idTurnoHistoriaAnterior;
		il_idTurnoHistoriaNuevo           = al_idTurnoHistoriaNuevo;
		is_job                            = as_job;
	}

	/**
	 * Modifica el valor de DocumentoSalidaActualizar.
	 *
	 * @param ads_documentoSalidaActualizar de ads documento salida actualizar
	 */
	public void setDocumentoSalidaActualizar(DocumentosSalida ads_documentoSalidaActualizar)
	{
		ids_documentoSalidaActualizar = ads_documentoSalidaActualizar;
	}

	/**
	 * Retorna Objeto o variable de valor documento salida actualizar.
	 *
	 * @return el valor de documento salida actualizar
	 */
	public DocumentosSalida getDocumentoSalidaActualizar()
	{
		return ids_documentoSalidaActualizar;
	}

	/**
	 * Modifica el valor de IdTurnoHistoriaAnterior.
	 *
	 * @param al_idTurnoHistoriaAnterior de al id turno historia anterior
	 */
	public void setIdTurnoHistoriaAnterior(Long al_idTurnoHistoriaAnterior)
	{
		il_idTurnoHistoriaAnterior = al_idTurnoHistoriaAnterior;
	}

	/**
	 * Retorna Objeto o variable de valor id turno historia anterior.
	 *
	 * @return el valor de id turno historia anterior
	 */
	public Long getIdTurnoHistoriaAnterior()
	{
		return il_idTurnoHistoriaAnterior;
	}

	/**
	 * Modifica el valor de IdTurnoHistoriaNuevo.
	 *
	 * @param al_idTurnoHistoriaNuevo de al id turno historia nuevo
	 */
	public void setIdTurnoHistoriaNuevo(Long al_idTurnoHistoriaNuevo)
	{
		il_idTurnoHistoriaNuevo = al_idTurnoHistoriaNuevo;
	}

	/**
	 * Retorna Objeto o variable de valor id turno historia nuevo.
	 *
	 * @return el valor de id turno historia nuevo
	 */
	public Long getIdTurnoHistoriaNuevo()
	{
		return il_idTurnoHistoriaNuevo;
	}

	/**
	 * Modifica el valor de Job.
	 *
	 * @param as_job de as job
	 */
	public void setJob(String as_job)
	{
		is_job = as_job;
	}

	/**
	 * Retorna Objeto o variable de valor job.
	 *
	 * @return el valor de job
	 */
	public String getJob()
	{
		return is_job;
	}
}
