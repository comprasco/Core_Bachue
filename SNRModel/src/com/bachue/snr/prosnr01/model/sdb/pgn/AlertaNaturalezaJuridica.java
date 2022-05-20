package com.bachue.snr.prosnr01.model.sdb.pgn;

import com.bachue.snr.prosnr01.model.auditoria.Auditoria;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;



/**
 * Clase de abstracción de la base de datos para la tabla SDB_PGN_ALERTA_NATURALEZA_JURIDICA.
 *
 * @author Alejandro Santos
 */
public class AlertaNaturalezaJuridica extends Auditoria implements Serializable
{
	
	/** Constante serialVersionUID. */
	private static final long                    serialVersionUID        = -6230634836986340533L;
	
	/** Propiedad ibd orden. */
	private BigDecimal                               ibd_orden;
	
	/** Propiedad icanj all info. */
	private Collection<AlertaNaturalezaJuridica> icanj_allInfo;
	
	/** Propiedad il version. */
	private Long                                 il_version;
	
	/** Propiedad is activo. */
	private String                               is_activo;
	
	/** Propiedad is especificacion. */
	private String                               is_especificacion;
	
	/** Propiedad is id anotacion. */
	private String                               is_idAnotacion;
	
	/** Propiedad is id naturaleza juridica. */
	private String                               is_idNaturalezaJuridica;
	
	/** Propiedad is ip. */
	private String                               is_ip;
	
	/** Propiedad is nombre alerta. */
	private String                               is_nombreAlerta;
	
	/** Propiedad is nombre naturaleza. */
	private String                               is_nombreNaturaleza;
	
	/** Propiedad is usuario. */
	private String                               is_usuario;

	/**
	 * Modifica el valor de Activo.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setActivo(String as_s)
	{
		this.is_activo                                                   = as_s;
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
	 * Modifica el valor de AllInfo.
	 *
	 * @param ac_c asigna el valor a la propiedad
	 */
	public void setAllInfo(Collection<AlertaNaturalezaJuridica> ac_c)
	{
		this.icanj_allInfo = ac_c;
	}

	/**
	 * Retorna Objeto o variable de valor all info.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public Collection<AlertaNaturalezaJuridica> getAllInfo()
	{
		return icanj_allInfo;
	}

	/**
	 * Modifica el valor de Especificacion.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setEspecificacion(String as_s)
	{
		this.is_especificacion = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor especificacion.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getEspecificacion()
	{
		return is_especificacion;
	}

	/**
	 * Modifica el valor de IdAnotacion.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setIdAnotacion(String as_s)
	{
		this.is_idAnotacion = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor id anotacion.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getIdAnotacion()
	{
		return is_idAnotacion;
	}

	/**
	 * Modifica el valor de IdNaturalezaJuridica.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setIdNaturalezaJuridica(String as_s)
	{
		this.is_idNaturalezaJuridica = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor id naturaleza juridica.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getIdNaturalezaJuridica()
	{
		return is_idNaturalezaJuridica;
	}

	/**
	 * Modifica el valor de Ip.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setIp(String as_s)
	{
		this.is_ip = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor ip.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getIp()
	{
		return is_ip;
	}

	/**
	 * Modifica el valor de NombreAlerta.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setNombreAlerta(String as_s)
	{
		this.is_nombreAlerta = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor nombre alerta.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getNombreAlerta()
	{
		return is_nombreAlerta;
	}

	/**
	 * Modifica el valor de NombreNaturaleza.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setNombreNaturaleza(String as_s)
	{
		this.is_nombreNaturaleza = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor nombre naturaleza.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getNombreNaturaleza()
	{
		return is_nombreNaturaleza;
	}
	
	/**
	 * Modifica el valor de Orden.
	 *
	 * @param abd_bd asigna el valor a la propiedad
	 */
	public void setOrden(BigDecimal abd_bd)
	{
		this.ibd_orden = abd_bd;
	}

	/**
	 * Retorna Objeto o variable de valor orden.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public BigDecimal getOrden()
	{
		return ibd_orden;
	}

	/**
	 * Modifica el valor de Usuario.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setUsuario(String as_s)
	{
		this.is_usuario = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor usuario.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getUsuario()
	{
		return is_usuario;
	}

	/**
	 * Modifica el valor de Version.
	 *
	 * @param al_l asigna el valor a la propiedad
	 */
	public void setVersion(Long al_l)
	{
		this.il_version = al_l;
	}

	/**
	 * Retorna Objeto o variable de valor version.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public Long getVersion()
	{
		return il_version;
	}
}
