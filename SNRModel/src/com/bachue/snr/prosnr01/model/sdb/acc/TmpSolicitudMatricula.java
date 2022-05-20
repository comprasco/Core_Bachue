package com.bachue.snr.prosnr01.model.sdb.acc;

import com.bachue.snr.prosnr01.common.constants.EstadoCommon;

import com.bachue.snr.prosnr01.model.auditoria.Auditoria;

import java.io.Serializable;

import java.math.BigDecimal;



/**
 * Clase modelo que contiene todos los atributos de la tabla SDB_ACC_TMP_SOLICITUD_MATRICULA.
 *
 * @author Julian Vaca
 */
public class TmpSolicitudMatricula extends Auditoria implements Serializable
{
	
	/** Constante serialVersionUID. */
	private static final long serialVersionUID         = 5508145431074162277L;
	
	/** Propiedad ibd cantidad certificados. */
	private BigDecimal        ibd_cantidadCertificados;
	
	/** Propiedad is accion. */
	private String            is_accion;
	
	/** Propiedad is expedir certificado. */
	private String            is_expedirCertificado;
	
	/** Propiedad is id circulo. */
	private String            is_idCirculo;
	
	/** Propiedad is id solicitud. */
	private String            is_idSolicitud;
	
	/** Propiedad is id turno certificado. */
	private String            is_idTurnoCertificado;
	
	/** Propiedad is id usuario. */
	private String            is_idUsuario;
	
	/** Propiedad ib es predio inconsistente. */
	private boolean           ib_esPredioInconsistente;
	
	/** Propiedad il id matricula. */
	private long              il_idMatricula;

	/**
	 * Constructor por defecto.
	 */
	public TmpSolicitudMatricula()
	{
	}

	/**
	 * Constructor que recibe como parametros la solicitud de matricula, la accion y el id del usuario.
	 *
	 * @param asma_sma solicitud matricula
	 * @param as_param accion
	 * @param as_userId usuario
	 */
	public TmpSolicitudMatricula(SolicitudMatricula asma_sma, String as_param, String as_userId)
	{
		is_accion                                      = as_param;
		is_idCirculo                                   = asma_sma.getIdCirculo();
		is_idSolicitud                                 = asma_sma.getIdSolicitud();
		is_idUsuario                                   = asma_sma.getIdUsuarioCreacion();
		il_idMatricula                                 = asma_sma.getIdMatricula();
		is_expedirCertificado                          = EstadoCommon.N;
		ibd_cantidadCertificados                       = new BigDecimal(0);
		is_idUsuario                                   = as_userId;
		super.setIdUsuarioCreacion(asma_sma.getIdUsuarioCreacion());
		super.setIdUsuarioModificacion(as_userId);
	}

	/**
	 * Modifica el valor de Accion.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setAccion(String as_s)
	{
		is_accion = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor accion.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getAccion()
	{
		return is_accion;
	}

	/**
	 * Modifica el valor de CantidadCertificados.
	 *
	 * @param abf_bf asigna el valor a la propiedad
	 */
	public void setCantidadCertificados(BigDecimal abf_bf)
	{
		ibd_cantidadCertificados = abf_bf;
	}

	/**
	 * Retorna Objeto o variable de valor cantidad certificados.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public BigDecimal getCantidadCertificados()
	{
		return ibd_cantidadCertificados;
	}

	/**
	 * Modifica el valor de EsPredioInconsistente.
	 *
	 * @param ab_b de ab b
	 */
	public void setEsPredioInconsistente(boolean ab_b)
	{
		ib_esPredioInconsistente = ab_b;
	}

	/**
	 * Valida la propiedad es predio inconsistente.
	 *
	 * @return Retorna el valor de la propiedad esPredioInconsistente
	 */
	public boolean isEsPredioInconsistente()
	{
		return ib_esPredioInconsistente;
	}

	/**
	 * Modifica el valor de ExpedirCertificado.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setExpedirCertificado(String as_s)
	{
		is_expedirCertificado = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor expedir certificado.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getExpedirCertificado()
	{
		return is_expedirCertificado;
	}

	/**
	 * Modifica el valor de IdCirculo.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setIdCirculo(String as_s)
	{
		is_idCirculo = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor id circulo.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getIdCirculo()
	{
		return is_idCirculo;
	}

	/**
	 * Modifica el valor de IdMatricula.
	 *
	 * @param al_l asigna el valor a la propiedad
	 */
	public void setIdMatricula(long al_l)
	{
		il_idMatricula = al_l;
	}

	/**
	 * Retorna Objeto o variable de valor id matricula.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public long getIdMatricula()
	{
		return il_idMatricula;
	}

	/**
	 * Modifica el valor de IdSolicitud.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setIdSolicitud(String as_s)
	{
		is_idSolicitud = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor id solicitud.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getIdSolicitud()
	{
		return is_idSolicitud;
	}

	/**
	 * Modifica el valor de IdTurnoCertificado.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setIdTurnoCertificado(String as_s)
	{
		is_idTurnoCertificado = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor id turno certificado.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getIdTurnoCertificado()
	{
		return is_idTurnoCertificado;
	}

	/**
	 * Modifica el valor de IdUsuario.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setIdUsuario(String as_s)
	{
		is_idUsuario = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor id usuario.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getIdUsuario()
	{
		return is_idUsuario;
	}
}
