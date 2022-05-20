package com.bachue.snr.prosnr01.model.sdb.pgn;

import com.bachue.snr.prosnr01.model.auditoria.Auditoria;

import java.io.Serializable;

import java.math.BigInteger;

import java.util.Date;


/**
 * Logica de modelo Constantes siendo una abstracción de la tabla SDB_PGN_CONSTANTES en la base de datos.
 *
 * @author Heiner Castañeda
 */
public class Constantes extends Auditoria implements Serializable
{
	/** Constante TIPO_AUTOMATICA. */
	public static final String TIPO_AUTOMATICA = "A";

	/** Constante TIPO_MANUAL. */
	public static final String TIPO_MANUAL = "M";

	/** Constante serialVersionUID. */
	private static final long serialVersionUID = -831036522436957070L;

	/** Propiedad ibi entero. */
	private BigInteger ibi_entero;

	/** Propiedad id fecha. */
	private Date id_fecha;

	/** Propiedad ir real. */
	private Double ir_real;

	/** Propiedad is activo. */
	private String is_activo;

	/** Propiedad is caracter. */
	private String is_caracter;

	/** Propiedad is descripcion. */
	private String is_descripcion;

	/** Propiedad is id constante. */
	private String is_idConstante;

	/** Propiedad is id proceso. */
	private String is_idProceso;

	/** Propiedad is texto. */
	private String is_texto;

	/** Propiedad is tipo. */
	private String is_tipo;

	/** Propiedad is tipo archivo. */
	private String is_tipoArchivo;

	/** Propiedad ib imagenes. */
	private byte[] ib_imagenes;

	/**
	 * Constructor por defecto.
	 */
	public Constantes()
	{
	}

	/**
	 * Constructor que recibe como parametro el id de la constante.
	 *
	 * @param as_s de as s
	 */
	public Constantes(String as_s)
	{
		setIdConstante(as_s);
	}

	/**
	 * @param as_activo Modifica el valor de la propiedad activo
	 */
	public void setActivo(String as_activo)
	{
		is_activo = as_activo;
	}

	/**
	 * @return Retorna el valor de la propiedad activo
	 */
	public String getActivo()
	{
		return is_activo;
	}

	/**
	 * Modifica el valor de Caracter.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setCaracter(String as_s)
	{
		this.is_caracter = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor caracter.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getCaracter()
	{
		return is_caracter;
	}

	/**
	 * Modifica el valor de Descripcion.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setDescripcion(String as_s)
	{
		this.is_descripcion = as_s;
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
	 * Modifica el valor de Entero.
	 *
	 * @param abi_bi asigna el valor a la propiedad
	 */
	public void setEntero(BigInteger abi_bi)
	{
		this.ibi_entero = abi_bi;
	}

	/**
	 * Retorna Objeto o variable de valor entero.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public BigInteger getEntero()
	{
		return ibi_entero;
	}

	/**
	 * Modifica el valor de Fecha.
	 *
	 * @param ad_d asigna el valor a la propiedad
	 */
	public void setFecha(Date ad_d)
	{
		this.id_fecha = ad_d;
	}

	/**
	 * Retorna Objeto o variable de valor fecha.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public Date getFecha()
	{
		return id_fecha;
	}

	/**
	 * Modifica el valor de IdConstante.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setIdConstante(String as_s)
	{
		this.is_idConstante = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor id constante.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getIdConstante()
	{
		return is_idConstante;
	}

	/**
	 * Modifica el valor de IdProceso.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setIdProceso(String as_s)
	{
		this.is_idProceso = as_s;
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
	 * Modifica el valor de Imagenes.
	 *
	 * @param ab_b asigna el valor a la propiedad
	 */
	public void setImagenes(byte[] ab_b)
	{
		this.ib_imagenes = ab_b;
	}

	/**
	 * Retorna Objeto o variable de valor imagenes.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public byte[] getImagenes()
	{
		return ib_imagenes;
	}

	/**
	 * Modifica el valor de Real.
	 *
	 * @param ar_r asigna el valor a la propiedad
	 */
	public void setReal(Double ar_r)
	{
		this.ir_real = ar_r;
	}

	/**
	 * Retorna Objeto o variable de valor real.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public Double getReal()
	{
		return ir_real;
	}

	/**
	 * Modifica el valor de Texto.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setTexto(String as_s)
	{
		this.is_texto = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor texto.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getTexto()
	{
		return is_texto;
	}

	/**
	 * Modifica el valor de Tipo.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setTipo(String as_s)
	{
		this.is_tipo = as_s;
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
	 * Modifica el valor de TipoArchivo.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setTipoArchivo(String as_s)
	{
		this.is_tipoArchivo = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor tipo archivo.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getTipoArchivo()
	{
		return is_tipoArchivo;
	}
}
