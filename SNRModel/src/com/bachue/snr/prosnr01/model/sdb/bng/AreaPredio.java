package com.bachue.snr.prosnr01.model.sdb.bng;

import com.bachue.snr.prosnr01.model.auditoria.Auditoria;

import java.io.Serializable;

import java.math.BigDecimal;

import java.util.Collection;


/**
 * Clase modelo que contiene todos los atributos de la tabla SDB_BNG_AREA_PREDIO.
 *
 * @author asantos
 */
public class AreaPredio extends Auditoria implements Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 1025364768704981812L;

	/** Propiedad ibd area construida. */
	private BigDecimal ibd_areaConstruida;

	/** Propiedad ibd area predio. */
	private BigDecimal ibd_areaPredio;

	/** Propiedad ibd area privada construida. */
	private BigDecimal ibd_areaPrivadaConstruida;

	/** Propiedad ibd coeficiente. */
	private BigDecimal ibd_coeficiente;

	/** Propiedad ibd id anotacion. */
	private BigDecimal ibd_idAnotacion;

	/** Propiedad ibd orden. */
	private BigDecimal ibd_orden;

	/** Propiedad icap matriculas informacion. */
	private Collection<AreaPredio> icap_matriculasInformacion;

	/** Propiedad is complemento direccion. */
	private String is_complementoDireccion;

	/** Propiedad is description. */
	private String is_description;

	/** Propiedad is id circulo. */
	private String is_idCirculo;

	/** Propiedad is id direccion predio. */
	private String is_idDireccionPredio;

	/** Propiedad is id predio registro. */
	private String is_idPredioRegistro;

	/** Propiedad is informacion origen. */
	private String is_informacionOrigen;

	/** Propiedad is nombre area construida. */
	private String is_nombreAreaConstruida;

	/** Propiedad is nombre area privada. */
	private String is_nombreAreaPrivada;

	/** Propiedad is nombre area terreno. */
	private String is_nombreAreaTerreno;

	/** Propiedad is nombre predio. */
	private String is_nombrePredio;

	/** Propiedad ib revisado. */
	private boolean ib_revisado;

	/** Propiedad ib revisado digitador. */
	private boolean ib_revisadoDigitador;

	/** Propiedad il id area. */
	private long il_idArea;

	/** Propiedad il id matricula. */
	private long il_idMatricula;

	/**
	 * Modifica el valor de AreaConstruida.
	 *
	 * @param ibd_bd asigna el valor a la propiedad
	 */
	public void setAreaConstruida(BigDecimal ibd_bd)
	{
		this.ibd_areaConstruida = ibd_bd;
	}

	/**
	 * Retorna Objeto o variable de valor area construida.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public BigDecimal getAreaConstruida()
	{
		return ibd_areaConstruida;
	}

	/**
	 * Modifica el valor de AreaPredio.
	 *
	 * @param abd_bd asigna el valor a la propiedad
	 */
	public void setAreaPredio(BigDecimal abd_bd)
	{
		this.ibd_areaPredio = abd_bd;
	}

	/**
	 * Retorna Objeto o variable de valor area predio.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public BigDecimal getAreaPredio()
	{
		return ibd_areaPredio;
	}

	/**
	 * Modifica el valor de AreaPrivadaConstruida.
	 *
	 * @param abd_bd asigna el valor a la propiedad
	 */
	public void setAreaPrivadaConstruida(BigDecimal abd_bd)
	{
		this.ibd_areaPrivadaConstruida = abd_bd;
	}

	/**
	 * Retorna Objeto o variable de valor area privada construida.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public BigDecimal getAreaPrivadaConstruida()
	{
		return ibd_areaPrivadaConstruida;
	}

	/**
	 * Modifica el valor de Coeficiente.
	 *
	 * @param abd_bd asigna el valor a la propiedad
	 */
	public void setCoeficiente(BigDecimal abd_bd)
	{
		this.ibd_coeficiente = abd_bd;
	}

	/**
	 * Retorna Objeto o variable de valor coeficiente.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public BigDecimal getCoeficiente()
	{
		return ibd_coeficiente;
	}

	/**
	 * Modifica el valor de ComplementoDireccion.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setComplementoDireccion(String as_s)
	{
		this.is_complementoDireccion = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor complemento direccion.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getComplementoDireccion()
	{
		return is_complementoDireccion;
	}

	/**
	 * Modifica el valor de Description.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setDescription(String as_s)
	{
		this.is_description = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor description.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getDescription()
	{
		return is_description;
	}

	/**
	 * Modifica el valor de IdAnotacion.
	 *
	 * @param abd_bd asigna el valor a la propiedad
	 */
	public void setIdAnotacion(BigDecimal abd_bd)
	{
		this.ibd_idAnotacion = abd_bd;
	}

	/**
	 * Retorna Objeto o variable de valor id anotacion.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public BigDecimal getIdAnotacion()
	{
		return ibd_idAnotacion;
	}

	/**
	 * Modifica el valor de IdArea.
	 *
	 * @param al_l asigna el valor a la propiedad
	 */
	public void setIdArea(long al_l)
	{
		this.il_idArea = al_l;
	}

	/**
	 * Retorna Objeto o variable de valor id area.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public long getIdArea()
	{
		return il_idArea;
	}

	/**
	 * Modifica el valor de IdCirculo.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setIdCirculo(String as_s)
	{
		this.is_idCirculo = as_s;
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
	 * Modifica el valor de IdDireccionPredio.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setIdDireccionPredio(String as_s)
	{
		this.is_idDireccionPredio = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor id direccion predio.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getIdDireccionPredio()
	{
		return is_idDireccionPredio;
	}

	/**
	 * Modifica el valor de IdMatricula.
	 *
	 * @param al_l asigna el valor a la propiedad
	 */
	public void setIdMatricula(long al_l)
	{
		this.il_idMatricula = al_l;
	}

	/**
	 * Retorna Objeto o variable de valor id matricula.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public long getIdMatricula()
	{
		return il_idMatricula;
	}

	/**
	 * Modifica el valor de IdPredioRegistro.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setIdPredioRegistro(String as_s)
	{
		this.is_idPredioRegistro = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor id predio registro.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getIdPredioRegistro()
	{
		return is_idPredioRegistro;
	}

	/**
	 * Modifica el valor de InformacionOrigen.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setInformacionOrigen(String as_s)
	{
		this.is_informacionOrigen = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor informacion origen.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getInformacionOrigen()
	{
		return is_informacionOrigen;
	}

	/**
	 * Modifica el valor de MatriculasInformacion.
	 *
	 * @param acap_ac asigna el valor a la propiedad
	 */
	public void setMatriculasInformacion(Collection<AreaPredio> acap_ac)
	{
		this.icap_matriculasInformacion = acap_ac;
	}

	/**
	 * Retorna Objeto o variable de valor matriculas informacion.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public Collection<AreaPredio> getMatriculasInformacion()
	{
		return icap_matriculasInformacion;
	}

	/**
	 * Modifica el valor de NombreAreaConstruida.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setNombreAreaConstruida(String as_s)
	{
		this.is_nombreAreaConstruida = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor nombre area construida.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getNombreAreaConstruida()
	{
		return is_nombreAreaConstruida;
	}

	/**
	 * Modifica el valor de NombreAreaPrivada.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setNombreAreaPrivada(String as_s)
	{
		this.is_nombreAreaPrivada = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor nombre area privada.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getNombreAreaPrivada()
	{
		return is_nombreAreaPrivada;
	}

	/**
	 * Modifica el valor de NombreAreaTerreno.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setNombreAreaTerreno(String as_s)
	{
		this.is_nombreAreaTerreno = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor nombre area terreno.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getNombreAreaTerreno()
	{
		return is_nombreAreaTerreno;
	}

	/**
	 * Modifica el valor de NombrePredio.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setNombrePredio(String as_s)
	{
		this.is_nombrePredio = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor nombre predio.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getNombrePredio()
	{
		return is_nombrePredio;
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
	 * Modifica el valor de Revisado.
	 *
	 * @param ab_b asigna el valor a la propiedad
	 */
	public void setRevisado(boolean ab_b)
	{
		this.ib_revisado = ab_b;
	}

	/**
	 * Valida la propiedad revisado.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public boolean isRevisado()
	{
		return ib_revisado;
	}

	/**
	 * Modifica el valor de RevisadoDigitador.
	 *
	 * @param ab_b asigna el valor a la propiedad
	 */
	public void setRevisadoDigitador(boolean ab_b)
	{
		this.ib_revisadoDigitador = ab_b;
	}

	/**
	 * Valida la propiedad revisado digitador.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public boolean isRevisadoDigitador()
	{
		return ib_revisadoDigitador;
	}
}
