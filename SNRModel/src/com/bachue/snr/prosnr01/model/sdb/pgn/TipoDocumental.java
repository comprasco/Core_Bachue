package com.bachue.snr.prosnr01.model.sdb.pgn;

import java.io.Serializable;

import java.util.Collection;
import java.util.Date;


/**
 * Clase modelo que contiene todos los atributos de la tabla SDB_PGN_TIPO_DOCUMENTAL.
 *
 * @author Julian Vaca
 */
public class TipoDocumental extends com.bachue.snr.prosnr01.model.registro.TipoDocumental implements Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 886731204883478267L;

	/** Propiedad icc tipos doc. */
	private Collection<TipoDocumental> icc_tiposDoc;

	/** Propiedad is abreviatura. */
	private String is_abreviatura;

	/** Propiedad is activo. */
	private String is_activo;

	/** Propiedad is extension. */
	private String is_extension;

	/** Propiedad is id serie. */
	private String is_idSerie;

	/** Propiedad is id sub serie. */
	private String is_idSubSerie;

	/** Propiedad is id tabla retencion. */
	private String is_idTablaRetencion;

	/** Propiedad is id tipo documental. */
	private String is_idTipoDocumental;

	/** Propiedad is nombre. */
	private String is_nombre;

	/** Propiedad is tipologias bachue. */
	private String is_tipologiasBachue;

	/** Propiedad ic soporte electronico. */
	private char ic_soporteElectronico;

	/** Propiedad ic soporte fisico. */
	private char ic_soporteFisico;

	/**
	 * Constructor por defecto.
	 */
	public TipoDocumental()
	{
		is_extension              = null;
		is_idSerie                = null;
		is_idSubSerie             = null;
		is_idTablaRetencion       = null;
		is_idTipoDocumental       = null;
		is_nombre                 = null;
		ic_soporteElectronico     = 'N';
		ic_soporteFisico          = 'N';
	}

	/**
	 * Constructor con todas las propiedades del objeto para su creacion.
	 *
	 * @param id_fechaCreacion fecha de creacion
	 * @param is_extension extension
	 * @param is_idSerie id de la serie
	 * @param is_idSubSerie sub serie
	 * @param is_idTablaRetencion tabla de retencion
	 * @param is_idTipoDocumental tipo documental
	 * @param is_idUsuario id del usuario
	 * @param is_nombre nombre
	 * @param ic_soporteElectronico soporte electronico
	 * @param ic_soporteFisico soporte fisico
	 */
	public TipoDocumental(
	    Date id_fechaCreacion, String is_extension, String is_idSerie, String is_idSubSerie, String is_idTablaRetencion,
	    String is_idTipoDocumental, String is_idUsuario, String is_nombre, char ic_soporteElectronico,
	    char ic_soporteFisico
	)
	{
		super();
		super.setFechaCreacion(id_fechaCreacion);
		super.setIdUsuarioCreacion(is_idUsuario);
		this.is_extension              = is_extension;
		this.is_idSerie                = is_idSerie;
		this.is_idSubSerie             = is_idSubSerie;
		this.is_idTablaRetencion       = is_idTablaRetencion;
		this.is_idTipoDocumental       = is_idTipoDocumental;
		this.is_nombre                 = is_nombre;
		this.ic_soporteElectronico     = ic_soporteElectronico;
		this.ic_soporteFisico          = ic_soporteFisico;
	}

	/**
	 * Modifica el valor de Abreviatura.
	 *
	 * @param as_a de as a
	 */
	public void setAbreviatura(String as_a)
	{
		is_abreviatura = as_a;
	}

	/**
	 * Retorna Objeto o variable de valor abreviatura.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getAbreviatura()
	{
		return is_abreviatura;
	}

	/**
	 * Modifica el valor de Activo.
	 *
	 * @param as_a de as a
	 */
	public void setActivo(String as_a)
	{
		is_activo = as_a;
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
	 * Modifica el valor de tipos doc.
	 *
	 * @param actd_td asigna el valor a la propiedad tipos doc
	 */
	public void setTiposDoc(Collection<TipoDocumental> actd_td)
	{
		icc_tiposDoc = actd_td;
	}

	/**
	 * Retorna el valor de tipos doc.
	 *
	 * @return el valor de tipos doc
	 */
	public Collection<TipoDocumental> getTiposDoc()
	{
		return icc_tiposDoc;
	}

	/**
	 * Modifica el valor de Extension.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setExtension(String as_s)
	{
		this.is_extension = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor extension.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getExtension()
	{
		return is_extension;
	}

	/**
	 * Modifica el valor de IdSerie.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setIdSerie(String as_s)
	{
		this.is_idSerie = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor id serie.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getIdSerie()
	{
		return is_idSerie;
	}

	/**
	 * Modifica el valor de IdSubSerie.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setIdSubSerie(String as_s)
	{
		this.is_idSubSerie = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor id sub serie.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getIdSubSerie()
	{
		return is_idSubSerie;
	}

	/**
	 * Modifica el valor de IdTablaRetencion.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setIdTablaRetencion(String as_s)
	{
		this.is_idTablaRetencion = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor id tabla retencion.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getIdTablaRetencion()
	{
		return is_idTablaRetencion;
	}

	/**
	 * Modifica el valor de IdTipoDocumental.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setIdTipoDocumental(String as_s)
	{
		this.is_idTipoDocumental = as_s;
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
	 * Modifica el valor de Nombre.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setNombre(String as_s)
	{
		this.is_nombre = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor nombre.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getNombre()
	{
		return is_nombre;
	}

	/**
	 * Modifica el valor de SoporteElectronico.
	 *
	 * @param ac_c asigna el valor a la propiedad
	 */
	public void setSoporteElectronico(char ac_c)
	{
		this.ic_soporteElectronico = ac_c;
	}

	/**
	 * Retorna Objeto o variable de valor soporte electronico.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public char getSoporteElectronico()
	{
		return ic_soporteElectronico;
	}

	/**
	 * Modifica el valor de SoporteFisico.
	 *
	 * @param ac_c asigna el valor a la propiedad
	 */
	public void setSoporteFisico(char ac_c)
	{
		this.ic_soporteFisico = ac_c;
	}

	/**
	 * Retorna Objeto o variable de valor soporte fisico.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public char getSoporteFisico()
	{
		return ic_soporteFisico;
	}

	/**
	 * Modifica el valor de TipologiasBachue.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setTipologiasBachue(String as_s)
	{
		is_tipologiasBachue = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor tipologias bachue.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getTipologiasBachue()
	{
		return is_tipologiasBachue;
	}
}
