package com.bachue.snr.prosnr01.model.sdb.aut;

import com.bachue.snr.prosnr01.model.auditoria.Auditoria;
import com.bachue.snr.prosnr01.model.sdb.pgn.Etapa;

import java.io.Serializable;

import java.util.Collection;
import java.util.Date;



/**
 * Clase modelo que contiene todos los atributos de la tabla SDB_AUT_OPCION.
 *
 * @author Julian Vaca
 */
public class Opcion extends Auditoria implements Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = -4655467011936688825L;

	/** Propiedad ice etapa source. */
	private Collection<Etapa> ice_etapaSource;

	/** Propiedad ice etapa target. */
	private Collection<Etapa> ice_etapaTarget;

	/** Propiedad id fecha desde. */
	private Date id_fechaDesde;

	/** Propiedad id fecha hasta. */
	private Date id_fechaHasta;

	/** Propiedad is activo. */
	private String is_activo;

	/** Propiedad is descripcion. */
	private String is_descripcion;

	/** Propiedad is id componente. */
	private String is_idComponente;

	/** Propiedad is id opcion. */
	private String is_idOpcion;

	/** Propiedad is id opcion padre. */
	private String is_idOpcionPadre;

	/** Propiedad is nombre opcion padre. */
	private String is_nombreOpcionPadre;

	/** Propiedad is opcion. */
	private String is_opcion;
	
	/** Propiedad is opcion frecuente. */
	private String is_opcionFrecuente;

	/** Propiedad is tipo. */
	private String is_tipo;

	/** Propiedad is url. */
	private String is_url;
	
	/** Propiedad is url catedra. */
	private String is_urlCatedra;

	/** Propiedad is ventana nueva. */
	private String is_ventanaNueva;

	/**
	 * Modifica el valor de Activo.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setActivo(String as_s)
	{
		is_activo = as_s;
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
	 * Modifica el valor de Descripcion.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setDescripcion(String as_s)
	{
		is_descripcion = as_s;
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
	 * Get etapa source.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public Collection<Etapa> getEtapaSource()
	{
		return ice_etapaSource;
	}

	/**
	 * Modifica el valor de etapa source.
	 *
	 * @param ace_ce de ace ce
	 */
	public void setEtapaSource(Collection<Etapa> ace_ce)
	{
		ice_etapaSource = ace_ce;
	}

	/**
	 * Get etapa target.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public Collection<Etapa> getEtapaTarget()
	{
		return ice_etapaTarget;
	}

	/**
	 * Modifica el valor de etapa target.
	 *
	 * @param ace_ce de ace ce
	 */
	public void setEtapaTarget(Collection<Etapa> ace_ce)
	{
		ice_etapaTarget = ace_ce;
	}

	/**
	 * Modifica el valor de FechaDesde.
	 *
	 * @param ad_d asigna el valor a la propiedad
	 */
	public void setFechaDesde(Date ad_d)
	{
		id_fechaDesde = ad_d;
	}

	/**
	 * Retorna Objeto o variable de valor fecha desde.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public Date getFechaDesde()
	{
		return id_fechaDesde;
	}

	/**
	 * Modifica el valor de FechaHasta.
	 *
	 * @param ad_d asigna el valor a la propiedad
	 */
	public void setFechaHasta(Date ad_d)
	{
		id_fechaHasta = ad_d;
	}

	/**
	 * Retorna Objeto o variable de valor fecha hasta.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public Date getFechaHasta()
	{
		return id_fechaHasta;
	}

	/**
	 * Modifica el valor de Id componente.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setIdComponente(String as_s)
	{
		is_idComponente = as_s;
	}

	/**
	 * Get id componente.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getIdComponente()
	{
		return is_idComponente;
	}

	/**
	 * Modifica el valor de IdOpcion.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setIdOpcion(String as_s)
	{
		is_idOpcion = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor id opcion.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getIdOpcion()
	{
		return is_idOpcion;
	}

	/**
	 * Modifica el valor de Opcion.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setOpcion(String as_s)
	{
		is_opcion = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor opcion.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getOpcion()
	{
		return is_opcion;
	}
	
	/**
	 * Modifica el valor de OpcionFrecuente.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setOpcionFrecuente(String as_s)
	{
		is_opcionFrecuente = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor opcion frecuente.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getOpcionFrecuente()
	{
		return is_opcionFrecuente;
	}

	/**
	 * Modifica el valor de NombreOpcionPadre.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setNombreOpcionPadre(String as_s)
	{
		is_nombreOpcionPadre = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor nombre opcion padre.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getNombreOpcionPadre()
	{
		return is_nombreOpcionPadre;
	}

	/**
	 * Modifica el valor de IdOpcionPadre.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setIdOpcionPadre(String as_s)
	{
		is_idOpcionPadre = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor id opcion padre.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getIdOpcionPadre()
	{
		return is_idOpcionPadre;
	}

	/**
	 * Modifica el valor de Tipo.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setTipo(String as_s)
	{
		is_tipo = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor tipo.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getTipo()
	{
		return is_tipo;
	}

	/**
	 * Modifica el valor de Url.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setUrl(String as_s)
	{
		is_url = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor url.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getUrl()
	{
		return is_url;
	}
	
	/**
	 * Modifica el valor de UrlCatedra.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setUrlCatedra(String as_s)
	{
		is_urlCatedra = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor url catedra.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getUrlCatedra()
	{
		return is_urlCatedra;
	}


	/**
	 * Retorna Objeto o variable de valor ventana nueva.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getVentanaNueva()
	{
		return is_ventanaNueva;
	}

	/**
	 * Modifica el valor de VentanaNueva.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setVentanaNueva(String as_s)
	{
		is_ventanaNueva = as_s;
	}
}
