package com.bachue.snr.prosnr01.model.calificacion;

import com.bachue.snr.prosnr01.model.antiguoSistema.Apertura;
import com.bachue.snr.prosnr01.model.auditoria.Auditoria;
import com.bachue.snr.prosnr01.model.sdb.acc.SolicitudMatricula;
import com.bachue.snr.prosnr01.model.sdb.acc.SolicitudMatriculaActo;
import com.bachue.snr.prosnr01.model.sdb.acc.TmpSolicitudMatricula;
import com.bachue.snr.prosnr01.model.sdb.acc.TmpSolicitudMatriculaActo;
import com.bachue.snr.prosnr01.model.sdb.bng.PredioRegistro;

import java.io.Serializable;

import java.util.Collection;



/**
 * Class que contiene todos las propiedades ConfrontacionCorrectiva.
 *
 * @author ccalderon
 */
public class ConfrontacionCorrectiva extends Auditoria implements Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = -5200055229090776864L;

	/** Propiedad ia apertura. */
	private Apertura ia_apertura;

	/** Propiedad ic data calificacion. */
	private Calificacion ic_dataCalificacion;

	/** Propiedad datos bandeja actos eliminarp. */
	private Collection<SolicitudMatriculaActo> datosBandejaActosEliminarp;

	/** Propiedad datos bandeja actos insertarp. */
	private Collection<SolicitudMatriculaActo> datosBandejaActosInsertarp;

	/** Propiedad datos bandeja predios eliminarp. */
	private Collection<SolicitudMatricula> datosBandejaPrediosEliminarp;

	/** Propiedad datos bandeja predios insertarp. */
	private Collection<SolicitudMatricula> datosBandejaPrediosInsertarp;

	/** Propiedad datos bandeja reabrir matriculap. */
	private Collection<PredioRegistro> datosBandejaReabrirMatriculap;

	/** Propiedad ictsm datos bandeja predios eliminar. */
	private Collection<TmpSolicitudMatricula> ictsm_datosBandejaPrediosEliminar;

	/** Propiedad ictsm datos bandeja predios insertar. */
	private Collection<TmpSolicitudMatricula> ictsm_datosBandejaPrediosInsertar;

	/** Propiedad ictsma datos bandeja actos eliminar. */
	private Collection<TmpSolicitudMatriculaActo> ictsma_datosBandejaActosEliminar;

	/** Propiedad ictsma datos bandeja actos insertar. */
	private Collection<TmpSolicitudMatriculaActo> ictsma_datosBandejaActosInsertar;

	/** Propiedad is id turno historia. */
	private Long is_idTurnoHistoria;

	/** Propiedad is justificacion. */
	private String is_justificacion;

	/** Propiedad is verifica folio cerrado. */
	private String is_verificaFolioCerrado;

	/**
	 * Instancia un nuevo objeto confrontacion correctiva.
	 */
	public ConfrontacionCorrectiva()
	{
	}

	/**
	 * Instancia un nuevo objeto confrontacion correctiva.
	 *
	 * @param actsm_datosBandejaPrediosInsertar de actsm datos bandeja predios insertar
	 * @param actsma_datosBandejaActosInsertar de actsma datos bandeja actos insertar
	 * @param actsm_datosBandejaPrediosEliminar de actsm datos bandeja predios eliminar
	 * @param actsma_datosBandejaActosEliminar de actsma datos bandeja actos eliminar
	 * @param ac_data de ac data
	 * @param as_observacionesVerificarFolio de as observaciones verificar folio
	 * @param al_turnoHistoria de al turno historia
	 */
	public ConfrontacionCorrectiva(
	    Collection<TmpSolicitudMatricula> actsm_datosBandejaPrediosInsertar,
	    Collection<TmpSolicitudMatriculaActo> actsma_datosBandejaActosInsertar,
	    Collection<TmpSolicitudMatricula> actsm_datosBandejaPrediosEliminar,
	    Collection<TmpSolicitudMatriculaActo> actsma_datosBandejaActosEliminar, Calificacion ac_data,
	    String as_observacionesVerificarFolio, Long al_turnoHistoria
	)
	{
		setDatosBandejaPrediosInsertar(actsm_datosBandejaPrediosInsertar);
		setDatosBandejaActosInsertar(actsma_datosBandejaActosInsertar);
		setDatosBandejaPrediosEliminar(actsm_datosBandejaPrediosEliminar);
		setDatosBandejaActosEliminar(actsma_datosBandejaActosEliminar);
		setDataCalificacion(ac_data);
		setVerificaFolioCerrado(as_observacionesVerificarFolio);
		setIdTurnoHistoria(al_turnoHistoria);
	}

	/**
	 * Instancia un nuevo objeto confrontacion correctiva.
	 *
	 * @param lcsm_datosBandejaPrediosInsertar de lcsm datos bandeja predios insertar
	 * @param lcsma_datosBandejaActosInsertar de lcsma datos bandeja actos insertar
	 * @param lcsm_datosBandejaPrediosEliminar de lcsm datos bandeja predios eliminar
	 * @param lcsma_datosBandejaActosEliminar de lcsma datos bandeja actos eliminar
	 * @param lcpr_datosBandejaReabrirMatricula de lcpr datos bandeja reabrir matricula
	 * @param lc_data de lc data
	 * @param la_apertura de la apertura
	 * @param as_justificacion de as justificacion
	 * @param al_turnoHistoria de al turno historia
	 */
	public ConfrontacionCorrectiva(
	    Collection<SolicitudMatricula> lcsm_datosBandejaPrediosInsertar,
	    Collection<SolicitudMatriculaActo> lcsma_datosBandejaActosInsertar,
	    Collection<SolicitudMatricula> lcsm_datosBandejaPrediosEliminar,
	    Collection<SolicitudMatriculaActo> lcsma_datosBandejaActosEliminar,
	    Collection<PredioRegistro> lcpr_datosBandejaReabrirMatricula, Calificacion lc_data, Apertura la_apertura,
	    String as_justificacion, Long al_turnoHistoria
	)
	{
		setDatosBandejaPrediosInsertarp(lcsm_datosBandejaPrediosInsertar);
		setDatosBandejaActosInsertarp(lcsma_datosBandejaActosInsertar);
		setDatosBandejaPrediosEliminarp(lcsm_datosBandejaPrediosEliminar);
		setDatosBandejaActosEliminarp(lcsma_datosBandejaActosEliminar);
		setDatosBandejaReabrirMatriculap(lcpr_datosBandejaReabrirMatricula);
		setDataCalificacion(lc_data);
		setApertura(la_apertura);
		setJustificacion(as_justificacion);
		setIdTurnoHistoria(al_turnoHistoria);
	}

	/**
	 * Modifica el valor de apertura.
	 *
	 * @param aa_a asigna el valor a la propiedad apertura
	 */
	public void setApertura(Apertura aa_a)
	{
		ia_apertura = aa_a;
	}

	/**
	 * Retorna el valor de apertura.
	 *
	 * @return el valor de apertura
	 */
	public Apertura getApertura()
	{
		return ia_apertura;
	}

	/**
	 * Modifica el valor de data calificacion.
	 *
	 * @param ac_c asigna el valor a la propiedad data calificacion
	 */
	public void setDataCalificacion(Calificacion ac_c)
	{
		ic_dataCalificacion = ac_c;
	}

	/**
	 * Retorna el valor de data calificacion.
	 *
	 * @return el valor de data calificacion
	 */
	public Calificacion getDataCalificacion()
	{
		return ic_dataCalificacion;
	}

	/**
	 * Modifica el valor de datos bandeja actos eliminar.
	 *
	 * @param actsma_ctsma asigna el valor a la propiedad datos bandeja actos eliminar
	 */
	public void setDatosBandejaActosEliminar(Collection<TmpSolicitudMatriculaActo> actsma_ctsma)
	{
		ictsma_datosBandejaActosEliminar = actsma_ctsma;
	}

	/**
	 * Retorna el valor de datos bandeja actos eliminar.
	 *
	 * @return el valor de datos bandeja actos eliminar
	 */
	public Collection<TmpSolicitudMatriculaActo> getDatosBandejaActosEliminar()
	{
		return ictsma_datosBandejaActosEliminar;
	}

	/**
	 * Modifica el valor de datos bandeja actos eliminarp.
	 *
	 * @param acsma_csma asigna el valor a la propiedad datos bandeja actos eliminarp
	 */
	public void setDatosBandejaActosEliminarp(Collection<SolicitudMatriculaActo> acsma_csma)
	{
		datosBandejaActosEliminarp = acsma_csma;
	}

	/**
	 * Retorna el valor de datos bandeja actos eliminarp.
	 *
	 * @return el valor de datos bandeja actos eliminarp
	 */
	public Collection<SolicitudMatriculaActo> getDatosBandejaActosEliminarp()
	{
		return datosBandejaActosEliminarp;
	}

	/**
	 * Modifica el valor de datos bandeja actos insertar.
	 *
	 * @param actsma_ctsma asigna el valor a la propiedad datos bandeja actos insertar
	 */
	public void setDatosBandejaActosInsertar(Collection<TmpSolicitudMatriculaActo> actsma_ctsma)
	{
		ictsma_datosBandejaActosInsertar = actsma_ctsma;
	}

	/**
	 * Retorna el valor de datos bandeja actos insertar.
	 *
	 * @return el valor de datos bandeja actos insertar
	 */
	public Collection<TmpSolicitudMatriculaActo> getDatosBandejaActosInsertar()
	{
		return ictsma_datosBandejaActosInsertar;
	}

	/**
	 * Modifica el valor de datos bandeja actos insertarp.
	 *
	 * @param acsma_csma asigna el valor a la propiedad datos bandeja actos insertarp
	 */
	public void setDatosBandejaActosInsertarp(Collection<SolicitudMatriculaActo> acsma_csma)
	{
		datosBandejaActosInsertarp = acsma_csma;
	}

	/**
	 * Retorna el valor de datos bandeja actos insertarp.
	 *
	 * @return el valor de datos bandeja actos insertarp
	 */
	public Collection<SolicitudMatriculaActo> getDatosBandejaActosInsertarp()
	{
		return datosBandejaActosInsertarp;
	}

	/**
	 * Modifica el valor de datos bandeja predios eliminar.
	 *
	 * @param actsm_actsm asigna el valor a la propiedad datos bandeja predios eliminar
	 */
	public void setDatosBandejaPrediosEliminar(Collection<TmpSolicitudMatricula> actsm_actsm)
	{
		ictsm_datosBandejaPrediosEliminar = actsm_actsm;
	}

	/**
	 * Retorna el valor de datos bandeja predios eliminar.
	 *
	 * @return el valor de datos bandeja predios eliminar
	 */
	public Collection<TmpSolicitudMatricula> getDatosBandejaPrediosEliminar()
	{
		return ictsm_datosBandejaPrediosEliminar;
	}

	/**
	 * Modifica el valor de datos bandeja predios eliminarp.
	 *
	 * @param acsm_csm asigna el valor a la propiedad datos bandeja predios eliminarp
	 */
	public void setDatosBandejaPrediosEliminarp(Collection<SolicitudMatricula> acsm_csm)
	{
		datosBandejaPrediosEliminarp = acsm_csm;
	}

	/**
	 * Retorna el valor de datos bandeja predios eliminarp.
	 *
	 * @return el valor de datos bandeja predios eliminarp
	 */
	public Collection<SolicitudMatricula> getDatosBandejaPrediosEliminarp()
	{
		return datosBandejaPrediosEliminarp;
	}

	/**
	 * Modifica el valor de datos bandeja predios insertar.
	 *
	 * @param actsm_ctsm asigna el valor a la propiedad datos bandeja predios insertar
	 */
	public void setDatosBandejaPrediosInsertar(Collection<TmpSolicitudMatricula> actsm_ctsm)
	{
		ictsm_datosBandejaPrediosInsertar = actsm_ctsm;
	}

	/**
	 * Retorna el valor de datos bandeja predios insertar.
	 *
	 * @return el valor de datos bandeja predios insertar
	 */
	public Collection<TmpSolicitudMatricula> getDatosBandejaPrediosInsertar()
	{
		return ictsm_datosBandejaPrediosInsertar;
	}

	/**
	 * Modifica el valor de datos bandeja predios insertarp.
	 *
	 * @param acsm_csm asigna el valor a la propiedad datos bandeja predios insertarp
	 */
	public void setDatosBandejaPrediosInsertarp(Collection<SolicitudMatricula> acsm_csm)
	{
		datosBandejaPrediosInsertarp = acsm_csm;
	}

	/**
	 * Retorna el valor de datos bandeja predios insertarp.
	 *
	 * @return el valor de datos bandeja predios insertarp
	 */
	public Collection<SolicitudMatricula> getDatosBandejaPrediosInsertarp()
	{
		return datosBandejaPrediosInsertarp;
	}

	/**
	 * Modifica el valor de datos bandeja reabrir matriculap.
	 *
	 * @param acpr_cpr asigna el valor a la propiedad datos bandeja reabrir matriculap
	 */
	public void setDatosBandejaReabrirMatriculap(Collection<PredioRegistro> acpr_cpr)
	{
		datosBandejaReabrirMatriculap = acpr_cpr;
	}

	/**
	 * Retorna el valor de datos bandeja reabrir matriculap.
	 *
	 * @return el valor de datos bandeja reabrir matriculap
	 */
	public Collection<PredioRegistro> getDatosBandejaReabrirMatriculap()
	{
		return datosBandejaReabrirMatriculap;
	}

	/**
	 * Modifica el valor de id turno historia.
	 *
	 * @param as_s asigna el valor a la propiedad id turno historia
	 */
	public void setIdTurnoHistoria(Long as_s)
	{
		is_idTurnoHistoria = as_s;
	}

	/**
	 * Retorna el valor de id turno historia.
	 *
	 * @return el valor de id turno historia
	 */
	public Long getIdTurnoHistoria()
	{
		return is_idTurnoHistoria;
	}

	/**
	 * Modifica el valor de justificacion.
	 *
	 * @param as_s asigna el valor a la propiedad justificacion
	 */
	public void setJustificacion(String as_s)
	{
		is_justificacion = as_s;
	}

	/**
	 * Retorna el valor de justificacion.
	 *
	 * @return el valor de justificacion
	 */
	public String getJustificacion()
	{
		return is_justificacion;
	}

	/**
	 * Modifica el valor de verifica folio cerrado.
	 *
	 * @param as_s asigna el valor a la propiedad verifica folio cerrado
	 */
	public void setVerificaFolioCerrado(String as_s)
	{
		is_verificaFolioCerrado = as_s;
	}

	/**
	 * Retorna el valor de verifica folio cerrado.
	 *
	 * @return el valor de verifica folio cerrado
	 */
	public String getVerificaFolioCerrado()
	{
		return is_verificaFolioCerrado;
	}
}
