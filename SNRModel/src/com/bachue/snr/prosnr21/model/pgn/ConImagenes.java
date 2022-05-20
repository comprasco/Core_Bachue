package com.bachue.snr.prosnr21.model.pgn;

import com.bachue.snr.prosnr01.model.auditoria.Auditoria;


/**
 * Clase que representa un registro en la vista SDB_CON_IMAGENES del módulo de conciliaciones.
 *
 * @author Gabriel Arias
 */
public class ConImagenes extends Auditoria implements java.io.Serializable
{
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 8473576000282222608L;

	/** Propiedad is codigo verificacion. */
	private String is_codigoVerificacion;

	/** Propiedad is estado. */
	private String is_estado;

	/** Propiedad is id gestor. */
	private String is_idGestor;

	/** Propiedad is id turno. */
	private String is_idTurno;

	/** Propiedad is tipo archivo. */
	private String is_tipoArchivo;

	/** Propiedad ib imagen blob. */
	private byte[] ib_imagenBlob;

	/** Propiedad is genera QR. */
	private boolean ib_generaQR;

	/** Propiedad il id imagen. */
	private long il_idImagen;

	/** Propiedad il tamano. */
	private Long il_tamano;

	/**
	 * Modifica el valor de CodigoVerificacion.
	 *
	 * @param as_codigoVerificacion de as codigo verificacion
	 */
	public void setCodigoVerificacion(String as_codigoVerificacion)
	{
		this.is_codigoVerificacion = as_codigoVerificacion;
	}

	/**
	 * Retorna Objeto o variable de valor codigo verificacion.
	 *
	 * @return el valor de codigo verificacion
	 */
	public String getCodigoVerificacion()
	{
		return is_codigoVerificacion;
	}

	/**
	 * Modifica el valor de Estado.
	 *
	 * @param as_estado de as estado
	 */
	public void setEstado(String as_estado)
	{
		this.is_estado = as_estado;
	}

	/**
	 * Retorna Objeto o variable de valor estado.
	 *
	 * @return el valor de estado
	 */
	public String getEstado()
	{
		return is_estado;
	}

	/**
	 * Modifica el valor de GeneraQR.
	 *
	 * @param ab_generaQR de ab genera QR
	 */
	public void setGeneraQR(boolean ab_generaQR)
	{
		this.ib_generaQR = ab_generaQR;
	}

	/**
	 * Valida la propiedad genera QR.
	 *
	 * @return retorna <code>true</code> si se cumple la condicion, de lo contario retorna <code>false</code> en genera QR
	 */
	public boolean isGeneraQR()
	{
		return ib_generaQR;
	}

	/**
	 * Modifica el valor de IdGestor.
	 *
	 * @param as_idGestor de as id gestor
	 */
	public void setIdGestor(String as_idGestor)
	{
		this.is_idGestor = as_idGestor;
	}

	/**
	 * Retorna Objeto o variable de valor id gestor.
	 *
	 * @return el valor de id gestor
	 */
	public String getIdGestor()
	{
		return is_idGestor;
	}

	/**
	 * Modifica el valor de IdImagen.
	 *
	 * @param al_idImagen de al id imagen
	 */
	public void setIdImagen(long al_idImagen)
	{
		this.il_idImagen = al_idImagen;
	}

	/**
	 * Retorna Objeto o variable de valor id imagen.
	 *
	 * @return el valor de id imagen
	 */
	public long getIdImagen()
	{
		return il_idImagen;
	}

	/**
	 * Modifica el valor de IdTurno.
	 *
	 * @param as_idTurno de as id turno
	 */
	public void setIdTurno(String as_idTurno)
	{
		this.is_idTurno = as_idTurno;
	}

	/**
	 * Retorna Objeto o variable de valor id turno.
	 *
	 * @return el valor de id turno
	 */
	public String getIdTurno()
	{
		return is_idTurno;
	}

	/**
	 * Modifica el valor de ImagenBlob.
	 *
	 * @param ab_imagenBlob de ab imagen blob
	 */
	public void setImagenBlob(byte[] ab_imagenBlob)
	{
		this.ib_imagenBlob = ab_imagenBlob;
	}

	/**
	 * Retorna Objeto o variable de valor imagen blob.
	 *
	 * @return el valor de imagen blob
	 */
	public byte[] getImagenBlob()
	{
		return ib_imagenBlob;
	}

	/**
	 * Modifica el valor de Tamano.
	 *
	 * @param al_tamano de al tamano
	 */
	public void setTamano(Long al_tamano)
	{
		this.il_tamano = al_tamano;
	}

	/**
	 * Retorna Objeto o variable de valor tamano.
	 *
	 * @return el valor de tamano
	 */
	public Long getTamano()
	{
		return il_tamano;
	}

	/**
	 * Modifica el valor de TipoArchivo.
	 *
	 * @param as_tipoArchivo de as tipo archivo
	 */
	public void setTipoArchivo(String as_tipoArchivo)
	{
		this.is_tipoArchivo = as_tipoArchivo;
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
}
