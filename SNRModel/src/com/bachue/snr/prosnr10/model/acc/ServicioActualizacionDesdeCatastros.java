package com.bachue.snr.prosnr10.model.acc;

import com.bachue.snr.prosnr01.model.auditoria.Auditoria;

import java.io.Serializable;



/**
 * Clase para manejo generalizado de datos de servicio actualizacion desde catastros.
 *
 * @author Carlos Calderon
 */
public class ServicioActualizacionDesdeCatastros extends Auditoria implements Serializable
{
	/** Propiedad Constant serialVersionUID. */
	private static final long serialVersionUID = -8716312367549777443L;

	/** Propiedad is id salvedad. */
	private String is_idSalvedad;

	/** Propiedad is tipo salvedad. */
	private String is_tipoSalvedad;

	/** Propiedad ib documento soporte. */
	private byte[] ib_documentoSoporte;

	/**
	 * Retorna Objeto o variable de valor id salvedad.
	 *
	 * @return Retorna el valor de la propiedad idSalvedad
	 */
	public String getIdSalvedad()
	{
		return is_idSalvedad;
	}

	/**
	 * Modifica el valor de IdSalvedad.
	 *
	 * @param as_s de as s
	 */
	public void setIdSalvedad(String as_s)
	{
		is_idSalvedad = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor tipo salvedad.
	 *
	 * @return Retorna el valor de la propiedad tipoSalvedad
	 */
	public String getTipoSalvedad()
	{
		return is_tipoSalvedad;
	}

	/**
	 * Modifica el valor de TipoSalvedad.
	 *
	 * @param as_s de as s
	 */
	public void setTipoSalvedad(String as_s)
	{
		is_tipoSalvedad = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor documento soporte.
	 *
	 * @return Retorna el valor de la propiedad documentoSoporte
	 */
	public byte[] getDocumentoSoporte()
	{
		return ib_documentoSoporte;
	}

	/**
	 * Modifica el valor de DocumentoSoporte.
	 *
	 * @param ab_b de ab b
	 */
	public void setDocumentoSoporte(byte[] ab_b)
	{
		ib_documentoSoporte = ab_b;
	}
}
