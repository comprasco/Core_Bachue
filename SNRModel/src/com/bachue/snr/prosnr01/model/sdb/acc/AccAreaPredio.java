package com.bachue.snr.prosnr01.model.sdb.acc;

import com.bachue.snr.prosnr01.model.auditoria.Auditoria;

import java.io.Serializable;



/**
 * Clase modelo que contiene todos los atributos de la tabla SDB_ACC_AREA_PREDIO.
 *
 * @author garias
 */
public class AccAreaPredio extends Auditoria implements Serializable
{
	
	/** Constante serialVersionUID. */
	private static final long serialVersionUID         = 1889163101793696710L;
	
	/** Propiedad id area construida. */
	private Double            id_areaConstruida;
	
	/** Propiedad id area predio. */
	private Double            id_areaPredio;
	
	/** Propiedad id area privada construida. */
	private Double            id_areaPrivadaConstruida;
	
	/** Propiedad id coeficiente. */
	private Double            id_coeficiente;
	
	/** Propiedad il id anotacion. */
	private Long              il_idAnotacion;
	
	/** Propiedad il id matricula. */
	private Long              il_idMatricula;
	
	/** Propiedad il id turno historia. */
	private Long              il_idTurnoHistoria;
	
	/** Propiedad is coeficiente lectura. */
	private String            is_coeficienteLectura;
	
	/** Propiedad is id area. */
	private String            is_idArea;
	
	/** Propiedad is id circulo. */
	private String            is_idCirculo;
	
	/** Propiedad is id turno. */
	private String            is_idTurno;
	
	/** Propiedad is tipo suelo. */
	private String            is_tipoSuelo;
	
	/** Propiedad ib modificacion. */
	private boolean           ib_modificacion;

	/**
	 * Modifica el valor de AreaConstruida.
	 *
	 * @param ad_d asigna el valor a la propiedad
	 */
	public void setAreaConstruida(Double ad_d)
	{
		id_areaConstruida                              = ad_d;
	}

	/**
	 * Retorna Objeto o variable de valor area construida.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public Double getAreaConstruida()
	{
		return id_areaConstruida;
	}

	/**
	 * Modifica el valor de AreaPredio.
	 *
	 * @param ad_d asigna el valor a la propiedad
	 */
	public void setAreaPredio(Double ad_d)
	{
		id_areaPredio = ad_d;
	}

	/**
	 * Retorna Objeto o variable de valor area predio.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public Double getAreaPredio()
	{
		return id_areaPredio;
	}

	/**
	 * Modifica el valor de AreaPrivadaConstruida.
	 *
	 * @param ad_d asigna el valor a la propiedad
	 */
	public void setAreaPrivadaConstruida(Double ad_d)
	{
		id_areaPrivadaConstruida = ad_d;
	}

	/**
	 * Retorna Objeto o variable de valor area privada construida.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public Double getAreaPrivadaConstruida()
	{
		return id_areaPrivadaConstruida;
	}

	/**
	 * Modifica el valor de Coeficiente.
	 *
	 * @param ad_d asigna el valor a la propiedad
	 */
	public void setCoeficiente(Double ad_d)
	{
		id_coeficiente = ad_d;
	}

	/**
	 * Retorna Objeto o variable de valor coeficiente.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public Double getCoeficiente()
	{
		return id_coeficiente;
	}

	/**
	 * Modifica el valor de CoeficienteLectura.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setCoeficienteLectura(String as_s)
	{
		is_coeficienteLectura = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor coeficiente lectura.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getCoeficienteLectura()
	{
		return is_coeficienteLectura;
	}

	/**
	 * Modifica el valor de IdAnotacion.
	 *
	 * @param al_l asigna el valor a la propiedad
	 */
	public void setIdAnotacion(Long al_l)
	{
		il_idAnotacion = al_l;
	}

	/**
	 * Retorna Objeto o variable de valor id anotacion.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public Long getIdAnotacion()
	{
		return il_idAnotacion;
	}

	/**
	 * Modifica el valor de IdArea.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setIdArea(String as_s)
	{
		is_idArea = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor id area.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getIdArea()
	{
		return is_idArea;
	}

	/**
	 * Modifica el valor de IdCirculo.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setIdCirculo(String as_s)
	{
		is_idCirculo = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor id circulo.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getIdCirculo()
	{
		return is_idCirculo;
	}

	/**
	 * Modifica el valor de IdMatricula.
	 *
	 * @param al_l asigna el valor a la propiedad
	 */
	public void setIdMatricula(Long al_l)
	{
		il_idMatricula = al_l;
	}

	/**
	 * Retorna Objeto o variable de valor id matricula.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public Long getIdMatricula()
	{
		return il_idMatricula;
	}

	/**
	 * Modifica el valor de IdTurno.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setIdTurno(String as_s)
	{
		is_idTurno = as_s;
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
	 * Modifica el valor de IdTurnoHistoria.
	 *
	 * @param al_l asigna el valor a la propiedad
	 */
	public void setIdTurnoHistoria(Long al_l)
	{
		il_idTurnoHistoria = al_l;
	}

	/**
	 * Retorna Objeto o variable de valor id turno historia.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public Long getIdTurnoHistoria()
	{
		return il_idTurnoHistoria;
	}

	/**
	 * Modifica el valor de Modificacion.
	 *
	 * @param ab_b asigna el valor a la propiedad
	 */
	public void setModificacion(boolean ab_b)
	{
		ib_modificacion = ab_b;
	}

	/**
	 * Valida la propiedad modificacion.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public boolean isModificacion()
	{
		return ib_modificacion;
	}

	/**
	 * Modifica el valor de TipoSuelo.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setTipoSuelo(String as_s)
	{
		is_tipoSuelo = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor tipo suelo.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getTipoSuelo()
	{
		return is_tipoSuelo;
	}
}
