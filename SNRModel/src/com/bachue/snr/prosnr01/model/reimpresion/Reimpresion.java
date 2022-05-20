package com.bachue.snr.prosnr01.model.reimpresion;

import com.bachue.snr.prosnr01.model.sdb.acc.DocumentosSalida;

import java.io.Serializable;

import java.util.Date;



/**
 * Clase que contiene todos las propiedades Reimpresion.
 *
 * @author ssanchez
 */
public class Reimpresion extends DocumentosSalida implements Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 6792478539403454856L;

	/** Propiedad id fecha asignacion. */
	private Date id_fechaAsignacion;

	/** Propiedad il id etapa actual. */
	private Long il_idEtapaActual;

	/** Propiedad il rango final. */
	private Long il_rangoFinal;

	/** Propiedad il rango inicial. */
	private Long il_rangoInicial;

	/** Propiedad is id etapa actual nombre. */
	private String is_idEtapaActualNombre;

	/** Propiedad is tipo documental. */
	private String is_idTipoDocumental;

	/** Propiedad is tipo documental nombre. */
	private String is_idTipoDocumentalNombre;

	/** Propiedad is id turno. */
	private String is_idTurno;

	/** Propiedad is motivo no tramite. */
	private String is_motivoNoTramite;

	/** Propiedad is nir. */
	private String is_nir;

	/** Propiedad il id documento salida reimpresion. */
	private long il_idDocumentoSalidaReimpresion;

	/**
	 * Instancia un nuevo objeto reimpresion recibos.
	 */
	public Reimpresion()
	{
	}

	/**
	 * Modifica el valor de id turno.
	 *
	 * @param as_s asigna el valor a la propiedad id turno
	 */
	public void setIdTurno(String as_s)
	{
		is_idTurno = as_s;
	}

	/**
	 * Retorna el valor de id turno.
	 *
	 * @return el valor de id turno
	 */
	public String getIdTurno()
	{
		return is_idTurno;
	}

	/**
	 * Modifica el valor de nir.
	 *
	 * @param as_s asigna el valor a la propiedad nir
	 */
	public void setNir(String as_s)
	{
		is_nir = as_s;
	}

	/**
	 * Retorna el valor de nir.
	 *
	 * @return el valor de nir
	 */
	public String getNir()
	{
		return is_nir;
	}

	/**
	 * Retorna el valor de fecha asignacion.
	 *
	 * @return el valor de fecha asignacion
	 */
	public Date getFechaAsignacion()
	{
		return id_fechaAsignacion;
	}

	/**
	 * Modifica el valor de fecha asignacion.
	 *
	 * @param ad_d asigna el valor a la propiedad fecha asignacion
	 */
	public void setFechaAsignacion(Date ad_d)
	{
		id_fechaAsignacion = ad_d;
	}

	/**
	 * Retorna el valor de tipo.
	 *
	 * @return el valor de tipo
	 */
	public String getTipo()
	{
		return is_idTipoDocumental;
	}

	/**
	 * Modifica el valor de tipo.
	 *
	 * @param as_s asigna el valor a la propiedad tipo
	 */
	public void setTipo(String as_s)
	{
		is_idTipoDocumental = as_s;
	}

	/**
	 * Retorna el valor de tipo documental nombre.
	 *
	 * @return el valor de tipo documental nombre
	 */
	public String getTipoDocumentalNombre()
	{
		return is_idTipoDocumentalNombre;
	}

	/**
	 * Modifica el valor de tipo documental nombre.
	 *
	 * @param as_s asigna el valor a la propiedad tipo documental nombre
	 */
	public void setTipoDocumentalNombre(String as_s)
	{
		is_idTipoDocumentalNombre = as_s;
	}

	/**
	 * Retorna el valor de id etapa actual nombre.
	 *
	 * @return el valor de id etapa actual nombre
	 */
	public String getIdEtapaActualNombre()
	{
		return is_idEtapaActualNombre;
	}

	/**
	 * Modifica el valor de id etapa actual nombre.
	 *
	 * @param as_s asigna el valor a la propiedad id etapa actual nombre
	 */
	public void setIdEtapaActualNombre(String as_s)
	{
		is_idEtapaActualNombre = as_s;
	}

	/**
	 * Retorna el valor de id documento salida.
	 *
	 * @return el valor de id documento salida
	 */
	public long getIdDocumentoSalidaReimpresion()
	{
		return il_idDocumentoSalidaReimpresion;
	}

	/**
	 * Modifica el valor de id documento salida.
	 *
	 * @param al_l asigna el valor a la propiedad id documento salida
	 */
	public void setIdDocumentoSalidaReimpresion(long al_l)
	{
		il_idDocumentoSalidaReimpresion = al_l;
	}

	/**
	 * Modifica el valor de MotivoNoTramite.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setMotivoNoTramite(String as_s)
	{
		is_motivoNoTramite = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor motivo no tramite.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getMotivoNoTramite()
	{
		return is_motivoNoTramite;
	}

	/**
	 * Retorna el valor de id etapa actual.
	 *
	 * @return el valor de id etapa actual
	 */
	public Long getIdEtapaActual()
	{
		return il_idEtapaActual;
	}

	/**
	 * Modifica el valor de id etapa actual.
	 *
	 * @param al_l asigna el valor a la propiedad id etapa actual
	 */
	public void setIdEtapaActual(Long al_l)
	{
		il_idEtapaActual = al_l;
	}

	/**
	 * Retorna el valor de rango inicial.
	 *
	 * @return el valor de rango inicial
	 */
	public Long getRangoInicial()
	{
		return il_rangoInicial;
	}

	/**
	 * Modifica el valor de rango inicial.
	 *
	 * @param al_l asigna el valor a la propiedad rango inicial
	 */
	public void setRangoInicial(Long al_l)
	{
		il_rangoInicial = al_l;
	}

	/**
	 * Retorna el valor de rango final.
	 *
	 * @return el valor de rango final
	 */
	public Long getRangoFinal()
	{
		return il_rangoFinal;
	}

	/**
	 * Modifica el valor de rango final.
	 *
	 * @param al_l asigna el valor a la propiedad rango final
	 */
	public void setRangoFinal(Long al_l)
	{
		il_rangoFinal = al_l;
	}
}
