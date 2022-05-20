package com.bachue.snr.prosnr01.model.sdb.acc;

import com.bachue.snr.prosnr01.model.auditoria.Auditoria;

import java.io.Serializable;

import java.util.Date;



/**
 * Clase modelo que contiene todos los atributos de la tabla SDB_ACC_BITACORA_PROCESO.
 *
 * @author Julian Vaca
 */
public class BitacoraProceso extends Auditoria implements Serializable
{
	
	/** Constante serialVersionUID. */
	private static final long serialVersionUID   = -5406227158974651510L;
	
	/** Propiedad id fecha solucion. */
	private Date              id_fechaSolucion;
	
	/** Propiedad is descripcion. */
	private String            is_descripcion;
	
	/** Propiedad is ejecucion. */
	private String            is_ejecucion;
	
	/** Propiedad is id etapa. */
	private String            is_idEtapa;
	
	/** Propiedad is id solicitud. */
	private String            is_idSolicitud;
	
	/** Propiedad is id turno. */
	private String            is_idTurno;
	
	/** Propiedad is id turno historia. */
	private String            is_idTurnoHistoria;
	
	/** Propiedad is llave 1. */
	private String            is_llave1;
	
	/** Propiedad is llave 2. */
	private String            is_llave2;
	
	/** Propiedad is llave 3. */
	private String            is_llave3;
	
	/** Propiedad is llave 4. */
	private String            is_llave4;
	
	/** Propiedad is llave 5. */
	private String            is_llave5;
	
	/** Propiedad is llave 6. */
	private String            is_llave6;
	
	/** Propiedad is nir. */
	private String            is_nir;
	
	/** Propiedad is proceso. */
	private String            is_proceso;
	
	/** Propiedad is solucionado. */
	private String            is_solucionado;
	
	/** Propiedad il id bitacora. */
	private long              il_idBitacora;

	/**
	 * Modifica el valor de Descripcion.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setDescripcion(String as_s)
	{
		is_descripcion                           = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor descripcion.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getDescripcion()
	{
		return is_descripcion;
	}

	/**
	 * Modifica el valor de Ejecucion.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setEjecucion(String as_s)
	{
		is_ejecucion = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor ejecucion.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getEjecucion()
	{
		return is_ejecucion;
	}

	/**
	 * Modifica el valor de FechaSolucion.
	 *
	 * @param ad_d asigna el valor a la propiedad
	 */
	public void setFechaSolucion(Date ad_d)
	{
		id_fechaSolucion = ad_d;
	}

	/**
	 * Retorna Objeto o variable de valor fecha solucion.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public Date getFechaSolucion()
	{
		return id_fechaSolucion;
	}

	/**
	 * Modifica el valor de IdBitacora.
	 *
	 * @param al_l asigna el valor a la propiedad
	 */
	public void setIdBitacora(long al_l)
	{
		il_idBitacora = al_l;
	}

	/**
	 * Retorna Objeto o variable de valor id bitacora.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public long getIdBitacora()
	{
		return il_idBitacora;
	}

	/**
	 * Modifica el valor de IdEtapa.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setIdEtapa(String as_s)
	{
		is_idEtapa = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor id etapa.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getIdEtapa()
	{
		return is_idEtapa;
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
	 * Modifica el valor de IdTurnoHistoria.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setIdTurnoHistoria(String as_s)
	{
		is_idTurnoHistoria = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor id turno historia.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getIdTurnoHistoria()
	{
		return is_idTurnoHistoria;
	}

	/**
	 * Modifica el valor de Llave1.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setLlave1(String as_s)
	{
		is_llave1 = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor llave 1.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getLlave1()
	{
		return is_llave1;
	}

	/**
	 * Modifica el valor de Llave2.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setLlave2(String as_s)
	{
		is_llave2 = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor llave 2.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getLlave2()
	{
		return is_llave2;
	}

	/**
	 * Modifica el valor de Llave3.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setLlave3(String as_s)
	{
		is_llave3 = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor llave 3.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getLlave3()
	{
		return is_llave3;
	}

	/**
	 * Modifica el valor de Llave4.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setLlave4(String as_s)
	{
		is_llave4 = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor llave 4.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getLlave4()
	{
		return is_llave4;
	}

	/**
	 * Modifica el valor de Llave5.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setLlave5(String as_s)
	{
		is_llave5 = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor llave 5.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getLlave5()
	{
		return is_llave5;
	}

	/**
	 * Modifica el valor de Llave6.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setLlave6(String as_s)
	{
		is_llave6 = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor llave 6.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getLlave6()
	{
		return is_llave6;
	}

	/**
	 * Modifica el valor de Nir.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setNir(String as_s)
	{
		is_nir = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor nir.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getNir()
	{
		return is_nir;
	}

	/**
	 * Modifica el valor de Proceso.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setProceso(String as_s)
	{
		is_proceso = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor proceso.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getProceso()
	{
		return is_proceso;
	}

	/**
	 * Modifica el valor de Solucionado.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setSolucionado(String as_s)
	{
		is_solucionado = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor solucionado.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getSolucionado()
	{
		return is_solucionado;
	}
}
