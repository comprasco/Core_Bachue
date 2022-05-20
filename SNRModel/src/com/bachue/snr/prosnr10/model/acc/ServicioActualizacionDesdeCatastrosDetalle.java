package com.bachue.snr.prosnr10.model.acc;

import com.bachue.snr.prosnr01.model.auditoria.Auditoria;

import java.io.Serializable;

import java.util.Date;



/**
 * Clase para manejo generalizado de datos de servicio actualizacion desde catastros detalle.
 *
 * @author Carlos Calderon
 */
public class ServicioActualizacionDesdeCatastrosDetalle extends Auditoria implements Serializable
{
	/** Propiedad Constant serialVersionUID. */
	private static final long serialVersionUID = -4400037413529905151L;

	/** Propiedad id fecha salvedad. */
	private Date id_fechaSalvedad;

	/** Propiedad il id matricula. */
	private Long il_idMatricula;

	/** Propiedad is comentario salvedad. */
	private String is_comentarioSalvedad;

	/** Propiedad is descripcion salvedad. */
	private String is_descripcionSalvedad;

	/** Propiedad is direccion predio. */
	private String is_direccionPredio;

	/** Propiedad is id circulo. */
	private String is_idCirculo;

	/** Propiedad is id salvedad. */
	private String is_idSalvedad;

	/** Propiedad is id salvedad detalle. */
	private String is_idSalvedadDetalle;

	/** Propiedad is numero identificacion catastral. */
	private String is_numeroIdentificacionCatastral;

	/** Propiedad is tipo identificacion preido. */
	private String is_tipoIdentificacionPreido;

	/**
	 * Retorna Objeto o variable de valor numero identificacion catastral.
	 *
	 * @return Retorna el valor de la propiedad numeroIdentificacionCatastral
	 */
	public String getNumeroIdentificacionCatastral()
	{
		return is_numeroIdentificacionCatastral;
	}

	/**
	 * Modifica el valor de NumeroIdentificacionCatastral.
	 *
	 * @param as_s de as s
	 */
	public void setNumeroIdentificacionCatastral(String as_s)
	{
		is_numeroIdentificacionCatastral = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor tipo identificacion preido.
	 *
	 * @return Retorna el valor de la propiedad tipoIdentificacionPreido
	 */
	public String getTipoIdentificacionPreido()
	{
		return is_tipoIdentificacionPreido;
	}

	/**
	 * Modifica el valor de TipoIdentificacionPreido.
	 *
	 * @param as_s de as s
	 */
	public void setTipoIdentificacionPreido(String as_s)
	{
		is_tipoIdentificacionPreido = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor comentario salvedad.
	 *
	 * @return Retorna el valor de la propiedad comentarioSalevdad
	 */
	public String getComentarioSalvedad()
	{
		return is_comentarioSalvedad;
	}

	/**
	 * Modifica el valor de ComentarioSalvedad.
	 *
	 * @param as_s de as s
	 */
	public void setComentarioSalvedad(String as_s)
	{
		is_comentarioSalvedad = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor descripcion salvedad.
	 *
	 * @return Retorna el valor de la propiedad descripcionSalvedad
	 */
	public String getDescripcionSalvedad()
	{
		return is_descripcionSalvedad;
	}

	/**
	 * Modifica el valor de DescripcionSalvedad.
	 *
	 * @param as_s de as s
	 */
	public void setDescripcionSalvedad(String as_s)
	{
		is_descripcionSalvedad = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor direccion predio.
	 *
	 * @return Retorna el valor de la propiedad direccionPredio
	 */
	public String getDireccionPredio()
	{
		return is_direccionPredio;
	}

	/**
	 * Modifica el valor de DireccionPredio.
	 *
	 * @param as_s de as s
	 */
	public void setDireccionPredio(String as_s)
	{
		is_direccionPredio = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor id circulo.
	 *
	 * @return Retorna el valor de la propiedad idCirculo
	 */
	public String getIdCirculo()
	{
		return is_idCirculo;
	}

	/**
	 * Modifica el valor de IdCirculo.
	 *
	 * @param as_s de as s
	 */
	public void setIdCirculo(String as_s)
	{
		is_idCirculo = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor id matricula.
	 *
	 * @return Retorna el valor de la propiedad idMatricula
	 */
	public Long getIdMatricula()
	{
		return il_idMatricula;
	}

	/**
	 * Modifica el valor de IdMatricula.
	 *
	 * @param al_l de al l
	 */
	public void setIdMatricula(Long al_l)
	{
		il_idMatricula = al_l;
	}

	/**
	 * Retorna Objeto o variable de valor fecha salvedad.
	 *
	 * @return Retorna el valor de la propiedad fechaSalvedad
	 */
	public Date getFechaSalvedad()
	{
		return id_fechaSalvedad;
	}

	/**
	 * Modifica el valor de FechaSalvedad.
	 *
	 * @param ad_d de ad d
	 */
	public void setFechaSalvedad(Date ad_d)
	{
		id_fechaSalvedad = ad_d;
	}

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
	 * Retorna Objeto o variable de valor id salvedad detalle.
	 *
	 * @return Retorna el valor de la propiedad idSalvedadDetalle
	 */
	public String getIdSalvedadDetalle()
	{
		return is_idSalvedadDetalle;
	}

	/**
	 * Modifica el valor de IdSalvedadDetalle.
	 *
	 * @param as_s de as s
	 */
	public void setIdSalvedadDetalle(String as_s)
	{
		is_idSalvedadDetalle = as_s;
	}
}
