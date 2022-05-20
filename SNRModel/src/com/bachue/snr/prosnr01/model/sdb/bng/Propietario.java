package com.bachue.snr.prosnr01.model.sdb.bng;

import com.bachue.snr.prosnr01.model.auditoria.Auditoria;

import java.io.Serializable;

import java.util.Date;



/**
 * Clase que contiene todos las propiedades Propietario.
 *
 * @author  Carlos Calderón
 * Fecha de Creacion: 18/03/2020
 */
public class Propietario extends Auditoria implements Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 240913295062755631L;

	/** Propiedad id fecha desde. */
	private Date id_fechaDesde;

	/** Propiedad id fecha hasta. */
	private Date id_fechaHasta;

	/** Propiedad id fecha inicio titularidad. */
	private Date id_fechaInicioTitularidad;

	/** Propiedad is estado. */
	private String is_estado;

	/** Propiedad is id circulo. */
	private String is_idCirculo;

	/** Propiedad is id estado predio. */
	private String is_idEstadoPredio;

	/** Propiedad is id naturaleza juridica. */
	private String is_idNaturalezaJuridica;

	/** Propiedad is id parte interesada. */
	private String is_idParteInteresada;

	/** Propiedad is id persona. */
	private String is_idPersona;

	/** Propiedad is id propietario. */
	private String is_idPropietario;

	/** Propiedad is id tipo titularidad. */
	private String is_idTipoTitularidad;

	/** Propiedad is nupre. */
	private String is_nupre;

	/** Propiedad il id anotacion. */
	private long il_idAnotacion;

	/** Propiedad il id anotacion hasta. */
	private long il_idAnotacionHasta;

	/** Propiedad il id matricula. */
	private long il_idMatricula;

	/** Propiedad is id persona. */
	private long il_orden;

	/** Propiedad il porcentaje participacion. */
	private long il_porcentajeParticipacion;

	/** Propiedad il version. */
	private long il_version;

	/**
	 * Constructor por defecto.
	 */
	public Propietario()
	{
	}

	/**
	 * Retorna Objeto o variable de valor id propietario.
	 *
	 * @return Retorna el valor de la propiedad idPropietario
	 */
	public String getIdPropietario()
	{
		return is_idPropietario;
	}

	/**
	 * Modifica el valor de IdPropietario.
	 *
	 * @param as_s de as s
	 */
	public void setIdPropietario(String as_s)
	{
		is_idPropietario = as_s;
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
	public long getIdMatricula()
	{
		return il_idMatricula;
	}

	/**
	 * Modifica el valor de IdMatricula.
	 *
	 * @param al_l de al l
	 */
	public void setIdMatricula(long al_l)
	{
		il_idMatricula = al_l;
	}

	/**
	 * Retorna Objeto o variable de valor id anotacion.
	 *
	 * @return Retorna el valor de la propiedad idAnotacion
	 */
	public long getIdAnotacion()
	{
		return il_idAnotacion;
	}

	/**
	 * Modifica el valor de IdAnotacion.
	 *
	 * @param al_l de al l
	 */
	public void setIdAnotacion(long al_l)
	{
		il_idAnotacion = al_l;
	}

	/**
	 * Retorna Objeto o variable de valor id naturaleza juridica.
	 *
	 * @return Retorna el valor de la propiedad idNaturalezaJuridica
	 */
	public String getIdNaturalezaJuridica()
	{
		return is_idNaturalezaJuridica;
	}

	/**
	 * Modifica el valor de IdNaturalezaJuridica.
	 *
	 * @param as_s de as s
	 */
	public void setIdNaturalezaJuridica(String as_s)
	{
		is_idNaturalezaJuridica = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor version.
	 *
	 * @return Retorna el valor de la propiedad version
	 */
	public long getVersion()
	{
		return il_version;
	}

	/**
	 * Modifica el valor de Version.
	 *
	 * @param al_l de al l
	 */
	public void setVersion(long al_l)
	{
		il_version = al_l;
	}

	/**
	 * Retorna Objeto o variable de valor id persona.
	 *
	 * @return Retorna el valor de la propiedad idPersona
	 */
	public String getIdPersona()
	{
		return is_idPersona;
	}

	/**
	 * Modifica el valor de IdPersona.
	 *
	 * @param as_s de as s
	 */
	public void setIdPersona(String as_s)
	{
		is_idPersona = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor porcentaje participacion.
	 *
	 * @return Retorna el valor de la propiedad porcentajeParticipacion
	 */
	public long getPorcentajeParticipacion()
	{
		return il_porcentajeParticipacion;
	}

	/**
	 * Modifica el valor de PorcentajeParticipacion.
	 *
	 * @param al_l de al l
	 */
	public void setPorcentajeParticipacion(long al_l)
	{
		il_porcentajeParticipacion = al_l;
	}

	/**
	 * Retorna Objeto o variable de valor id tipo titularidad.
	 *
	 * @return Retorna el valor de la propiedad idTipoTitularidad
	 */
	public String getIdTipoTitularidad()
	{
		return is_idTipoTitularidad;
	}

	/**
	 * Modifica el valor de IdTipoTitularidad.
	 *
	 * @param as_s de as s
	 */
	public void setIdTipoTitularidad(String as_s)
	{
		is_idTipoTitularidad = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor fecha inicio titularidad.
	 *
	 * @return Retorna el valor de la propiedad fechaInicioTitularidad
	 */
	public Date getFechaInicioTitularidad()
	{
		return id_fechaInicioTitularidad;
	}

	/**
	 * Modifica el valor de FechaInicioTitularidad.
	 *
	 * @param ad_d de ad d
	 */
	public void setFechaInicioTitularidad(Date ad_d)
	{
		id_fechaInicioTitularidad = ad_d;
	}

	/**
	 * Retorna Objeto o variable de valor fecha desde.
	 *
	 * @return Retorna el valor de la propiedad fechaDesde
	 */
	public Date getFechaDesde()
	{
		return id_fechaDesde;
	}

	/**
	 * Modifica el valor de FechaDesde.
	 *
	 * @param ad_d de ad d
	 */
	public void setFechaDesde(Date ad_d)
	{
		id_fechaDesde = ad_d;
	}

	/**
	 * Retorna Objeto o variable de valor fecha hasta.
	 *
	 * @return Retorna el valor de la propiedad fechaHasta
	 */
	public Date getFechaHasta()
	{
		return id_fechaHasta;
	}

	/**
	 * Modifica el valor de FechaHasta.
	 *
	 * @param ad_d de ad d
	 */
	public void setFechaHasta(Date ad_d)
	{
		id_fechaHasta = ad_d;
	}

	/**
	 * Retorna Objeto o variable de valor id anotacion hasta.
	 *
	 * @return Retorna el valor de la propiedad idAnotacionHasta
	 */
	public long getIdAnotacionHasta()
	{
		return il_idAnotacionHasta;
	}

	/**
	 * Modifica el valor de IdAnotacionHasta.
	 *
	 * @param al_l de al l
	 */
	public void setIdAnotacionHasta(long al_l)
	{
		il_idAnotacionHasta = al_l;
	}

	/**
	 * Retorna Objeto o variable de valor estado.
	 *
	 * @return Retorna el valor de la propiedad estado
	 */
	public String getEstado()
	{
		return is_estado;
	}

	/**
	 * Modifica el valor de Estado.
	 *
	 * @param as_s de as s
	 */
	public void setEstado(String as_s)
	{
		is_estado = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor id estado predio.
	 *
	 * @return Retorna el valor de la propiedad idEstadoPredio
	 */
	public String getIdEstadoPredio()
	{
		return is_idEstadoPredio;
	}

	/**
	 * Modifica el valor de IdEstadoPredio.
	 *
	 * @param as_s de as s
	 */
	public void setIdEstadoPredio(String as_s)
	{
		is_idEstadoPredio = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor id parte interesada.
	 *
	 * @return Retorna el valor de la propiedad idParteInteresada
	 */
	public String getIdParteInteresada()
	{
		return is_idParteInteresada;
	}

	/**
	 * Modifica el valor de IdParteInteresada.
	 *
	 * @param as_s de as s
	 */
	public void setIdParteInteresada(String as_s)
	{
		is_idParteInteresada = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor nupre.
	 *
	 * @return Retorna el valor de la propiedad nupre
	 */
	public String getNupre()
	{
		return is_nupre;
	}

	/**
	 * Modifica el valor de Nupre.
	 *
	 * @param as_s de as s
	 */
	public void setNupre(String as_s)
	{
		is_nupre = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor orden.
	 *
	 * @return Retorna el valor de la propiedad orden
	 */
	public long getOrden()
	{
		return il_orden;
	}

	/**
	 * Modifica el valor de Orden.
	 *
	 * @param al_l de al l
	 */
	public void setOrden(long al_l)
	{
		il_orden = al_l;
	}
}
