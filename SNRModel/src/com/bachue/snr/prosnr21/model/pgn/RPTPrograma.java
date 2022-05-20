package com.bachue.snr.prosnr21.model.pgn;

import com.bachue.snr.prosnr01.model.auditoria.Auditoria;

import java.io.Serializable;

import java.util.Date;


/**
 * Clase modelo que contiene todos los atributos de la tabla SDB_PGN_RPT_PROGRAMA.
 *
 * @author Kevin Pulido
 */
public class RPTPrograma extends Auditoria implements Serializable
{
	/**  Propiedad serialVersionUID. */
	private static final long serialVersionUID = -7762137253399267091L;

	/** Propiedad id fecha proxima ejecucion. */
	private Date id_fechaProximaEjecucion;

	/** Propiedad id fecha ultima ejecucion. */
	private Date id_fechaUltimaEjecucion;

	/** Propiedad is activo. */
	private String is_activo;

	/** Propiedad is expedientes. */
	private String is_expedientes;

	/** Propiedad is hora proceso. */
	private String is_horaProceso;

	/** Propiedad is id periodicidad. */
	private String is_idPeriodicidad;

	/** Propiedad is id reporte. */
	private String is_idReporte;

	/** Propiedad is nombre. */
	private String is_nombre;

	/** Propiedad is id periodicidad. */
	private String is_nombrePeriodicidad;

	/** Propiedad is observaciones. */
	private String is_observaciones;

	/** Propiedad is procedimiento. */
	private String is_procedimiento;

	/** Propiedad is resultado detalle. */
	private String is_resultadoDetalle;

	/** Propiedad is solicita CTA. */
	private String is_solicitaCTA;

	/** Propiedad is sql ejecucion. */
	private String is_sqlEjecucion;

	/** Propiedad is tipo archivo. */
	private String is_tipoArchivo;

	/** Propiedad is tipos documentales. */
	private String is_tiposDocumentales;

	/** Propiedad il hora programada. */
	private long il_horaProgramada;

	/** Propiedad il minutos programada. */
	private long il_minutosProgramada;

	/** Propiedad il resultado. */
	private long il_resultado;

	/**
	 * Modifica el valor de Activo.
	 *
	 * @param as_activo de as activo
	 */
	public void setActivo(String as_activo)
	{
		is_activo = as_activo;
	}

	/**
	 * Retorna Objeto o variable de valor activo.
	 *
	 * @return el valor de activo
	 */
	public String getActivo()
	{
		return is_activo;
	}

	/**
	 * Modifica el valor de Expedientes.
	 *
	 * @param as_expedientes de as expedientes
	 */
	public void setExpedientes(String as_expedientes)
	{
		is_expedientes = as_expedientes;
	}

	/**
	 * Retorna Objeto o variable de valor expedientes.
	 *
	 * @return el valor de expedientes
	 */
	public String getExpedientes()
	{
		return is_expedientes;
	}

	/**
	 * Modifica el valor de FechaProximaEjecucion.
	 *
	 * @param ad_fechaProximaEjecucion de ad fecha proxima ejecucion
	 */
	public void setFechaProximaEjecucion(Date ad_fechaProximaEjecucion)
	{
		id_fechaProximaEjecucion = ad_fechaProximaEjecucion;
	}

	/**
	 * Retorna Objeto o variable de valor fecha proxima ejecucion.
	 *
	 * @return el valor de fecha proxima ejecucion
	 */
	public Date getFechaProximaEjecucion()
	{
		return id_fechaProximaEjecucion;
	}

	/**
	 * Modifica el valor de FechaUltimaEjecucion.
	 *
	 * @param ad_fechaUltimaEjecucion de ad fecha ultima ejecucion
	 */
	public void setFechaUltimaEjecucion(Date ad_fechaUltimaEjecucion)
	{
		id_fechaUltimaEjecucion = ad_fechaUltimaEjecucion;
	}

	/**
	 * Retorna Objeto o variable de valor fecha ultima ejecucion.
	 *
	 * @return el valor de fecha ultima ejecucion
	 */
	public Date getFechaUltimaEjecucion()
	{
		return id_fechaUltimaEjecucion;
	}

	/**
	 * @param Modifica el valor de la propiedad horaProceso por horaProceso
	 */
	public void setHoraProceso(String as_s)
	{
		is_horaProceso = as_s;
	}

	/**
	 * @return Retorna el valor de la propiedad horaProceso
	 */
	public String getHoraProceso()
	{
		return is_horaProceso;
	}

	/**
	 * Modifica el valor de HoraProgramada.
	 *
	 * @param al_horaProgramada de al hora programada
	 */
	public void setHoraProgramada(long al_horaProgramada)
	{
		il_horaProgramada = al_horaProgramada;
	}

	/**
	 * Retorna Objeto o variable de valor hora programada.
	 *
	 * @return el valor de hora programada
	 */
	public long getHoraProgramada()
	{
		return il_horaProgramada;
	}

	/**
	 * Modifica el valor de IdPeriodicidad.
	 *
	 * @param as_idPeriodicidad de as id periodicidad
	 */
	public void setIdPeriodicidad(String as_idPeriodicidad)
	{
		is_idPeriodicidad = as_idPeriodicidad;
	}

	/**
	 * Retorna Objeto o variable de valor id periodicidad.
	 *
	 * @return el valor de id periodicidad
	 */
	public String getIdPeriodicidad()
	{
		return is_idPeriodicidad;
	}

	/**
	 * Modifica el valor de IdReporte.
	 *
	 * @param as_idReporte de as id reporte
	 */
	public void setIdReporte(String as_idReporte)
	{
		is_idReporte = as_idReporte;
	}

	/**
	 * Retorna Objeto o variable de valor id reporte.
	 *
	 * @return el valor de id reporte
	 */
	public String getIdReporte()
	{
		return is_idReporte;
	}

	/**
	 * Modifica el valor de MinutosProgramada.
	 *
	 * @param al_minutosProgramada de al minutos programada
	 */
	public void setMinutosProgramada(long al_minutosProgramada)
	{
		il_minutosProgramada = al_minutosProgramada;
	}

	/**
	 * Retorna Objeto o variable de valor minutos programada.
	 *
	 * @return el valor de minutos programada
	 */
	public long getMinutosProgramada()
	{
		return il_minutosProgramada;
	}

	/**
	 * Modifica el valor de Nombre.
	 *
	 * @param as_nombre de as nombre
	 */
	public void setNombre(String as_nombre)
	{
		is_nombre = as_nombre;
	}

	/**
	 * Retorna Objeto o variable de valor nombre.
	 *
	 * @return el valor de nombre
	 */
	public String getNombre()
	{
		return is_nombre;
	}

	/**
	 * Modifica el valor de NombrePeriodicidad.
	 *
	 * @param as_nombrePeriodicidad de as nombre periodicidad
	 */
	public void setNombrePeriodicidad(String as_nombrePeriodicidad)
	{
		is_nombrePeriodicidad = as_nombrePeriodicidad;
	}

	/**
	 * Retorna Objeto o variable de valor nombre periodicidad.
	 *
	 * @return el valor de nombre periodicidad
	 */
	public String getNombrePeriodicidad()
	{
		return is_nombrePeriodicidad;
	}

	/**
	 * Modifica el valor de Observaciones.
	 *
	 * @param as_observaciones de as observaciones
	 */
	public void setObservaciones(String as_observaciones)
	{
		is_observaciones = as_observaciones;
	}

	/**
	 * Retorna Objeto o variable de valor observaciones.
	 *
	 * @return el valor de observaciones
	 */
	public String getObservaciones()
	{
		return is_observaciones;
	}

	/**
	 * @param as_procedimiento Modifica el valor de la propiedad procedimiento
	 */
	public void setProcedimiento(String as_procedimiento)
	{
		is_procedimiento = as_procedimiento;
	}

	/**
	 * @return Retorna el valor de la propiedad procedimiento
	 */
	public String getProcedimiento()
	{
		return is_procedimiento;
	}

	/**
	 * Modifica el valor de Resultado.
	 *
	 * @param al_resultado de al resultado
	 */
	public void setResultado(long al_resultado)
	{
		il_resultado = al_resultado;
	}

	/**
	 * Retorna Objeto o variable de valor resultado.
	 *
	 * @return el valor de resultado
	 */
	public long getResultado()
	{
		return il_resultado;
	}

	/**
	 * Modifica el valor de ResultadoDetalle.
	 *
	 * @param as_resultadoDetalle de as resultado detalle
	 */
	public void setResultadoDetalle(String as_resultadoDetalle)
	{
		is_resultadoDetalle = as_resultadoDetalle;
	}

	/**
	 * Retorna Objeto o variable de valor resultado detalle.
	 *
	 * @return el valor de resultado detalle
	 */
	public String getResultadoDetalle()
	{
		return is_resultadoDetalle;
	}

	public void setSolicitaCTA(String as_s)
	{
		is_solicitaCTA = as_s;
	}

	public String getSolicitaCTA()
	{
		return is_solicitaCTA;
	}

	/**
	 * Modifica el valor de SqlEjecucion.
	 *
	 * @param as_sqlEjecucion de as sql ejecucion
	 */
	public void setSqlEjecucion(String as_sqlEjecucion)
	{
		is_sqlEjecucion = as_sqlEjecucion;
	}

	/**
	 * Retorna Objeto o variable de valor sql ejecucion.
	 *
	 * @return el valor de sql ejecucion
	 */
	public String getSqlEjecucion()
	{
		return is_sqlEjecucion;
	}

	/**
	 * Modifica el valor de TipoArchivo.
	 *
	 * @param as_tipoArchivo de as tipo archivo
	 */
	public void setTipoArchivo(String as_tipoArchivo)
	{
		is_tipoArchivo = as_tipoArchivo;
	}

	/**
	 * Retorna Objeto o variable de valor tipo archivo.
	 *
	 * @return el valor de tipo archivo
	 */
	public String getTipoArchivo()
	{
		return is_tipoArchivo;
	}

	/**
	 * Modifica el valor de TiposDocumentales.
	 *
	 * @param as_tiposDocumentales de as tipos documentales
	 */
	public void setTiposDocumentales(String as_tiposDocumentales)
	{
		is_tiposDocumentales = as_tiposDocumentales;
	}

	/**
	 * Retorna Objeto o variable de valor tipos documentales.
	 *
	 * @return el valor de tipos documentales
	 */
	public String getTiposDocumentales()
	{
		return is_tiposDocumentales;
	}
}
