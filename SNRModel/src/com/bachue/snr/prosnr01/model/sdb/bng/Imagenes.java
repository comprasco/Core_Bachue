package com.bachue.snr.prosnr01.model.sdb.bng;

import com.bachue.snr.prosnr01.model.auditoria.Auditoria;

import java.io.Serializable;



/**
 * Clase modelo que contiene todos los atributos de Imagenes.
 *
 * @author mblanco
 */
public class Imagenes extends Auditoria implements Serializable
{
	
	/** Constante serialVersionUID. */
	private static final long serialVersionUID        = 1927079653437780807L;
	
	/** Propiedad il tamano. */
	private Long              il_tamano;
	
	/** Propiedad is codigo verificacion. */
	private String            is_codigoVerificacion;
	
	/** Propiedad is id turno. */
	private String            is_idTurno;
	
	/** Propiedad is nombre archivo. */
	private String            is_nombreArchivo;
	
	/** Propiedad is nombre tipo documental. */
	private String            is_nombreTipoDocumental;
	
	/** Propiedad is observaciones. */
	private String            is_observaciones;
	
	/** Propiedad is tipo archivo. */
	private String            is_tipoArchivo;
	
	/** Propiedad is tipo documental. */
	private String            is_tipoDocumental;
	
	/** Propiedad ib imagen blob. */
	private byte[]            ib_imagenBlob;
	
	/** Propiedad ib genera QR. */
	private boolean           ib_generaQR;
	
	/** Propiedad ii contador. */
	private int               ii_contador;
	
	/** Propiedad il id imagen. */
	private long              il_idImagen;

	/**
	 * Constructor por defecto.
	 */
	public Imagenes()
	{
	}

	/**
	 * Constructor que recibe como parametro el id de la imagen.
	 *
	 * @param al_idImagen id de la imagen
	 */
	public Imagenes(long al_idImagen)
	{
		setIdImagen(al_idImagen);
	}

	/**
	 * Modifica el valor de CodigoVerificacion.
	 *
	 * @param codigoVerificacion asigna el valor a la propiedad
	 */
	public void setCodigoVerificacion(String codigoVerificacion)
	{
		this.is_codigoVerificacion                    = codigoVerificacion;
	}

	/**
	 * Retorna Objeto o variable de valor codigo verificacion.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getCodigoVerificacion()
	{
		return is_codigoVerificacion;
	}

	/**
	 * Modifica el valor de Contador.
	 *
	 * @param contador asigna el valor a la propiedad
	 */
	public void setContador(int contador)
	{
		this.ii_contador = contador;
	}

	/**
	 * Retorna Objeto o variable de valor contador.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public int getContador()
	{
		return ii_contador;
	}

	/**
	 * Modifica el valor de GeneraQR.
	 *
	 * @param generaQR asigna el valor a la propiedad
	 */
	public void setGeneraQR(boolean generaQR)
	{
		this.ib_generaQR = generaQR;
	}

	/**
	 * Valida la propiedad genera QR.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public boolean isGeneraQR()
	{
		return ib_generaQR;
	}

	/**
	 * Modifica el valor de IdImagen.
	 *
	 * @param idImagen asigna el valor a la propiedad
	 */
	public void setIdImagen(long idImagen)
	{
		this.il_idImagen = idImagen;
	}

	/**
	 * Retorna Objeto o variable de valor id imagen.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public long getIdImagen()
	{
		return il_idImagen;
	}

	/**
	 * Modifica el valor de IdTurno.
	 *
	 * @param idTurno asigna el valor a la propiedad
	 */
	public void setIdTurno(String idTurno)
	{
		this.is_idTurno = idTurno;
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
	 * Modifica el valor de ImagenBlob.
	 *
	 * @param imagenBlob asigna el valor a la propiedad
	 */
	public void setImagenBlob(byte[] imagenBlob)
	{
		this.ib_imagenBlob = imagenBlob;
	}

	/**
	 * Retorna Objeto o variable de valor imagen blob.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public byte[] getImagenBlob()
	{
		return ib_imagenBlob;
	}

	/**
	 * Modifica el valor de NombreArchivo.
	 *
	 * @param nombreArchivo asigna el valor a la propiedad
	 */
	public void setNombreArchivo(String nombreArchivo)
	{
		this.is_nombreArchivo = nombreArchivo;
	}

	/**
	 * Retorna Objeto o variable de valor nombre archivo.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getNombreArchivo()
	{
		return is_nombreArchivo;
	}

	/**
	 * Modifica el valor de NombreTipoDocumental.
	 *
	 * @param nombreTipoDocumental asigna el valor a la propiedad
	 */
	public void setNombreTipoDocumental(String nombreTipoDocumental)
	{
		this.is_nombreTipoDocumental = nombreTipoDocumental;
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
	 * Modifica el valor de Observaciones.
	 *
	 * @param observaciones asigna el valor a la propiedad
	 */
	public void setObservaciones(String observaciones)
	{
		this.is_observaciones = observaciones;
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
	 * Modifica el valor de Tamano.
	 *
	 * @param tamano asigna el valor a la propiedad
	 */
	public void setTamano(Long tamano)
	{
		this.il_tamano = tamano;
	}

	/**
	 * Retorna Objeto o variable de valor tamano.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public Long getTamano()
	{
		return il_tamano;
	}

	/**
	 * Modifica el valor de TipoArchivo.
	 *
	 * @param tipoArchivo asigna el valor a la propiedad
	 */
	public void setTipoArchivo(String tipoArchivo)
	{
		this.is_tipoArchivo = tipoArchivo;
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

	/**
	 * Modifica el valor de TipoDocumental.
	 *
	 * @param tipoDocumental asigna el valor a la propiedad
	 */
	public void setTipoDocumental(String tipoDocumental)
	{
		this.is_tipoDocumental = tipoDocumental;
	}

	/**
	 * Retorna Objeto o variable de valor tipo documental.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getTipoDocumental()
	{
		return is_tipoDocumental;
	}
}
