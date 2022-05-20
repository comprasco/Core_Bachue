package com.bachue.snr.prosnr01.model.sdb.acc;

import com.bachue.snr.prosnr01.model.auditoria.Auditoria;

import java.io.Serializable;

import java.math.BigInteger;

import java.util.Date;


/**
 * Clase modelo que contiene todos los atributos de la tabla SDB_ACC_COMPLETITUD_DOCUMENTAL.
 *
 * @author mblanco
 */
public class AccCompletitudDocumental extends Auditoria implements Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 2076593026881373048L;

	/** Propiedad ibi continuar registro. */
	private BigInteger ibi_continuarRegistro;

	/** Propiedad ibi id documento salida. */
	private BigInteger ibi_idDocumentoSalida;

	/** Propiedad ibi numero pago. */
	private BigInteger ibi_numeroPago;

	/** Propiedad id_fechaDigitalizacion. */
	private Date id_fechaDigitalizacion;

	/** Propiedad is digitalizado. */
	private String is_digitalizado;

	/** Propiedad is entregado. */
	private String is_entregado;

	/** Propiedad is id acto. */
	private String is_idActo;

	/** Propiedad is id completitud. */
	private String is_idCompletitud;

	/** Propiedad is_idEcm. */
	private String is_idEcm;

	/** Propiedad is_idNombreDocumento. */
	private String is_idNombreDocumento;

	/** Propiedad is id proceso. */
	private String is_idProceso;

	/** Propiedad is id solicitud. */
	private String is_idSolicitud;

	/** Propiedad is_idSolicitudPrincipal. */
	private String is_idSolicitudPrincipal;

	/** Propiedad is id solicitud vinculada. */
	private String is_idSolicitudVinculada;

	/** Propiedad is id subproceso. */
	private String is_idSubproceso;

	/** Propiedad is id tipo acto. */
	private String is_idTipoActo;

	/** Propiedad is id tipo documental. */
	private String is_idTipoDocumental;

	/** Propiedad is id turno. */
	private String is_idTurno;

	/** Propiedad is id turno certificado correcciones. */
	private String is_idTurnoCertificadoCorrecciones;

	/** Propiedad is medio recepcion. */
	private String is_medioRecepcion;

	/** Propiedad is nombre documental. */
	private String is_nombreDocumental;

	/** Propiedad is nombre tipo documental. */
	private String is_nombreTipoDocumental;

	/** Propiedad is obligatorio. */
	private String is_obligatorio;

	/** Propiedad is obligatorio tipo documental. */
	private String is_obligatorioTipoDocumental;

	/** Propiedad is observaciones. */
	private String is_observaciones;

	/** Propiedad is observaciones continuacion. */
	private String is_observacionesContinuacion;

	/** Propiedad is observaciones recepcion. */
	private String is_observacionesRecepcion;

	/** Propiedad ib agregar turno. */
	private boolean ib_agregarTurno;

	/** Propiedad ib campo rojo observacion. */
	private boolean ib_campoRojoObservacion;

	/** Propiedad ib campo rojo observacion recepcion. */
	private boolean ib_campoRojoObservacionRecepcion;

	/** Propiedad ib no editable. */
	private boolean ib_noEditable;

	/** Propiedad ib seleccionado. */
	private boolean ib_seleccionado;

	/**
	 * Modifica el valor de AgregarTurno.
	 *
	 * @param ab_b asigna el valor a la propiedad
	 */
	public void setAgregarTurno(boolean ab_b)
	{
		ib_agregarTurno = ab_b;
	}

	/**
	 * Valida la propiedad agregar turno.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public boolean isAgregarTurno()
	{
		return ib_agregarTurno;
	}

	/**
	 * Modifica el valor de CampoRojoObservacion.
	 *
	 * @param ab_b asigna el valor a la propiedad
	 */
	public void setCampoRojoObservacion(boolean ab_b)
	{
		ib_campoRojoObservacion = ab_b;
	}

	/**
	 * Valida la propiedad campo rojo observacion.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public boolean isCampoRojoObservacion()
	{
		return ib_campoRojoObservacion;
	}

	/**
	 * Modifica el valor de CampoRojoObservacionRecepcion.
	 *
	 * @param ab_b asigna el valor a la propiedad
	 */
	public void setCampoRojoObservacionRecepcion(boolean ab_b)
	{
		ib_campoRojoObservacionRecepcion = ab_b;
	}

	/**
	 * Valida la propiedad campo rojo observacion recepcion.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public boolean isCampoRojoObservacionRecepcion()
	{
		return ib_campoRojoObservacionRecepcion;
	}

	/**
	 * Modifica el valor de ContinuarRegistro.
	 *
	 * @param abi_bi asigna el valor a la propiedad
	 */
	public void setContinuarRegistro(BigInteger abi_bi)
	{
		ibi_continuarRegistro = abi_bi;
	}

	/**
	 * Retorna Objeto o variable de valor continuar registro.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public BigInteger getContinuarRegistro()
	{
		return ibi_continuarRegistro;
	}

	/**
	 * Modifica el valor de Digitalizado.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setDigitalizado(String as_s)
	{
		is_digitalizado = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor digitalizado.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getDigitalizado()
	{
		return is_digitalizado;
	}

	/**
	 * Modifica el valor de Entregado.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setEntregado(String as_s)
	{
		is_entregado = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor entregado.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getEntregado()
	{
		return is_entregado;
	}

	/**
	 * Método que modifica el valor de la propiedad.
	 *
	 * @param ad_d modifica el valor de la propiedad.
	 */
	public void setFechaDigitalizacion(Date ad_d)
	{
		id_fechaDigitalizacion = ad_d;
	}

	/**
	 * Método que retorna el valor de la propiedad.
	 *
	 * @return retorna el valor de la propiedad.
	 */
	public Date getFechaDigitalizacion()
	{
		return id_fechaDigitalizacion;
	}

	/**
	 * Modifica el valor de IdActo.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setIdActo(String as_s)
	{
		is_idActo = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor id acto.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getIdActo()
	{
		return is_idActo;
	}

	/**
	 * Modifica el valor de IdCompletitud.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setIdCompletitud(String as_s)
	{
		is_idCompletitud = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor id completitud.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getIdCompletitud()
	{
		return is_idCompletitud;
	}

	/**
	 * Modifica el valor de IdDocumentoSalida.
	 *
	 * @param abi_bi asigna el valor a la propiedad
	 */
	public void setIdDocumentoSalida(BigInteger abi_bi)
	{
		ibi_idDocumentoSalida = abi_bi;
	}

	/**
	 * Retorna Objeto o variable de valor id documento salida.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public BigInteger getIdDocumentoSalida()
	{
		return ibi_idDocumentoSalida;
	}

	/**
	 * Método que modifica el valor de la propiedad.
	 *
	 * @param ad_d modifica el valor de la propiedad.
	 */
	public void setIdEcm(String as_s)
	{
		is_idEcm = as_s;
	}

	/**
	 * Método que retorna el valor de la propiedad.
	 *
	 * @return retorna el valor de la propiedad.
	 */
	public String getIdEcm()
	{
		return is_idEcm;
	}

	/**
	 * Método que modifica el valor de la propiedad.
	 *
	 * @param ad_d modifica el valor de la propiedad.
	 */
	public void setIdNombreDocumento(String as_s)
	{
		is_idNombreDocumento = as_s;
	}

	/**
	 * Método que retorna el valor de la propiedad.
	 *
	 * @return retorna el valor de la propiedad.
	 */
	public String getIdNombreDocumento()
	{
		return is_idNombreDocumento;
	}

	/**
	 * Modifica el valor de IdProceso.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setIdProceso(String as_s)
	{
		is_idProceso = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor id proceso.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getIdProceso()
	{
		return is_idProceso;
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
	 * Método que modifica el valor de la propiedad.
	 *
	 * @param ad_d modifica el valor de la propiedad.
	 */
	public void setIdSolicitudPrincipal(String as_s)
	{
		is_idSolicitudPrincipal = as_s;
	}

	/**
	 * Método que retorna el valor de la propiedad.
	 *
	 * @return retorna el valor de la propiedad.
	 */
	public String getIdSolicitudPrincipal()
	{
		return is_idSolicitudPrincipal;
	}

	/**
	 * Modifica el valor de IdSolicitudVinculada.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setIdSolicitudVinculada(String as_s)
	{
		is_idSolicitudVinculada = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor id solicitud vinculada.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getIdSolicitudVinculada()
	{
		return is_idSolicitudVinculada;
	}

	/**
	 * Modifica el valor de IdSubproceso.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setIdSubproceso(String as_s)
	{
		is_idSubproceso = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor id subproceso.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getIdSubproceso()
	{
		return is_idSubproceso;
	}

	/**
	 * Modifica el valor de IdTipoActo.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setIdTipoActo(String as_s)
	{
		is_idTipoActo = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor id tipo acto.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getIdTipoActo()
	{
		return is_idTipoActo;
	}

	/**
	 * Modifica el valor de IdTipoDocumental.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setIdTipoDocumental(String as_s)
	{
		is_idTipoDocumental = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor id tipo documental.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getIdTipoDocumental()
	{
		return is_idTipoDocumental;
	}

	/**
	 * Modifica el valor de IdTurno.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setIdTurno(String as_s)
	{
		is_idTurno = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor id turno.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getIdTurno()
	{
		return is_idTurno;
	}

	/**
	 * Modifica el valor de IdTurnoCertificadoCorrecciones.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setIdTurnoCertificadoCorrecciones(String as_s)
	{
		is_idTurnoCertificadoCorrecciones = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor id turno certificado correcciones.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getIdTurnoCertificadoCorrecciones()
	{
		return is_idTurnoCertificadoCorrecciones;
	}

	/**
	 * Modifica el valor de MedioRecepcion.
	 *
	 * @param as_medioRecepcion asigna el valor a la propiedad
	 */
	public void setMedioRecepcion(String as_medioRecepcion)
	{
		is_medioRecepcion = as_medioRecepcion;
	}

	/**
	 * Retorna Objeto o variable de valor medio recepcion.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getMedioRecepcion()
	{
		return is_medioRecepcion;
	}

	/**
	 * Modifica el valor de NoEditable.
	 *
	 * @param ab_b asigna el valor a la propiedad
	 */
	public void setNoEditable(boolean ab_b)
	{
		ib_noEditable = ab_b;
	}

	/**
	 * Valida la propiedad no editable.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public boolean isNoEditable()
	{
		return ib_noEditable;
	}

	/**
	 * Modifica el valor de NombreDocumental.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setNombreDocumental(String as_s)
	{
		is_nombreDocumental = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor nombre documental.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getNombreDocumental()
	{
		return is_nombreDocumental;
	}

	/**
	 * Modifica el valor de NombreTipoDocumental.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setNombreTipoDocumental(String as_s)
	{
		is_nombreTipoDocumental = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor nombre tipo documental.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getNombreTipoDocumental()
	{
		return is_nombreTipoDocumental;
	}

	/**
	 * Modifica el valor de NumeroPago.
	 *
	 * @param abi_bi asigna el valor a la propiedad
	 */
	public void setNumeroPago(BigInteger abi_bi)
	{
		ibi_numeroPago = abi_bi;
	}

	/**
	 * Retorna Objeto o variable de valor numero pago.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public BigInteger getNumeroPago()
	{
		return ibi_numeroPago;
	}

	/**
	 * Modifica el valor de Obligatorio.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setObligatorio(String as_s)
	{
		is_obligatorio = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor obligatorio.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getObligatorio()
	{
		return is_obligatorio;
	}

	/**
	 * Modifica el valor de ObligatorioTipoDocumental.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setObligatorioTipoDocumental(String as_s)
	{
		is_obligatorioTipoDocumental = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor obligatorio tipo documental.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getObligatorioTipoDocumental()
	{
		return is_obligatorioTipoDocumental;
	}

	/**
	 * Modifica el valor de Observaciones.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setObservaciones(String as_s)
	{
		is_observaciones = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor observaciones.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getObservaciones()
	{
		return is_observaciones;
	}

	/**
	 * Modifica el valor de ObservacionesContinuacion.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setObservacionesContinuacion(String as_s)
	{
		is_observacionesContinuacion = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor observaciones continuacion.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getObservacionesContinuacion()
	{
		return is_observacionesContinuacion;
	}

	/**
	 * Modifica el valor de ObservacionesRecepcion.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setObservacionesRecepcion(String as_s)
	{
		is_observacionesRecepcion = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor observaciones recepcion.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getObservacionesRecepcion()
	{
		return is_observacionesRecepcion;
	}

	/**
	 * Modifica el valor de Seleccionado.
	 *
	 * @param ab_b asigna el valor a la propiedad
	 */
	public void setSeleccionado(boolean ab_b)
	{
		ib_seleccionado = ab_b;
	}

	/**
	 * Valida la propiedad seleccionado.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public boolean isSeleccionado()
	{
		return ib_seleccionado;
	}
}
