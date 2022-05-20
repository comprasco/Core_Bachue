package com.bachue.snr.prosnr01.model.sdb.acc;

import com.bachue.snr.prosnr01.model.auditoria.Auditoria;

import java.io.Serializable;

import java.util.Date;



/**
 * Clase modelo que contiene todos los atributos de la tabla SDB_ACC_REGISTRO_ANOTACION_PROHIBICION.
 *
 * @author Julian Vaca
 */
public class RegistroAnotacionProhibicion extends Auditoria implements Serializable
{
	
	/** Constante serialVersionUID. */
	private static final long serialVersionUID    = 4888567380051170749L;
	
	/** Propiedad id fecha vencimiento. */
	private Date              id_fechaVencimiento;
	
	/** Propiedad is activo. */
	private String            is_activo;
	
	/** Propiedad is error key. */
	private String            is_errorKey;
	
	/** Propiedad is id circulo. */
	private String            is_idCirculo;
	
	/** Propiedad is id solicitud. */
	private String            is_idSolicitud;
	
	/** Propiedad is id turno. */
	private String            is_idTurno;
	
	/** Propiedad is tipo adquisicion. */
	private String            is_tipoAdquisicion;
	
	/** Propiedad ioa message args. */
	private Object[]          ioa_messageArgs;
	
	/** Propiedad ib alerta. */
	private boolean           ib_alerta;
	
	/** Propiedad il id anotacion. */
	private long              il_idAnotacion;
	
	/** Propiedad il id matricula. */
	private long              il_idMatricula;

	/**
	 * Modifica el valor de Activo.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setActivo(String as_s)
	{
		is_activo                                 = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor activo.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getActivo()
	{
		return is_activo;
	}

	/**
	 * Modifica el valor de Alerta.
	 *
	 * @param ab_b asigna el valor a la propiedad
	 */
	public void setAlerta(boolean ab_b)
	{
		ib_alerta = ab_b;
	}

	/**
	 * Valida la propiedad alerta.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public boolean isAlerta()
	{
		return ib_alerta;
	}

	/**
	 * Modifica el valor de ErrorKey.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setErrorKey(String as_s)
	{
		is_errorKey = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor error key.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getErrorKey()
	{
		return is_errorKey;
	}

	/**
	 * Modifica el valor de FechaVencimiento.
	 *
	 * @param ad_d asigna el valor a la propiedad
	 */
	public void setFechaVencimiento(Date ad_d)
	{
		id_fechaVencimiento = ad_d;
	}

	/**
	 * Retorna Objeto o variable de valor fecha vencimiento.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public Date getFechaVencimiento()
	{
		return id_fechaVencimiento;
	}

	/**
	 * Modifica el valor de IdAnotacion.
	 *
	 * @param al_l de al l
	 */
	public void setIdAnotacion(long al_l)
	{
		il_idAnotacion = al_l;
	}

	/**
	 * Retorna Objeto o variable de valor id anotacion.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public long getIdAnotacion()
	{
		return il_idAnotacion;
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
	 * @param al_l de al l
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
	 * Modifica el valor de MessageArgs.
	 *
	 * @param aoa_oa asigna el valor a la propiedad
	 */
	public void setMessageArgs(Object[] aoa_oa)
	{
		ioa_messageArgs = aoa_oa;
	}

	/**
	 * Retorna Objeto o variable de valor message args.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public Object[] getMessageArgs()
	{
		return ioa_messageArgs;
	}

	/**
	 * Modifica el valor de TipoAdquisicion.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setTipoAdquisicion(String as_s)
	{
		is_tipoAdquisicion = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor tipo adquisicion.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getTipoAdquisicion()
	{
		return is_tipoAdquisicion;
	}
}
