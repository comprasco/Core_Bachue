package com.bachue.snr.prosnr01.model.sdb.png;

import com.bachue.snr.prosnr01.model.auditoria.Auditoria;

import java.io.Serializable;



/**
 * Clase modelo que contiene todos los atributos de abstraccion de la tabla SDB_PNG_GRUPO_NATURALEZA_JURIDICA.
 *
 * @author nguaneme
 */
public class NaturalezaJuridica extends Auditoria implements Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = -2641239657611248223L;

	/** Propiedad is activo. */
	private String is_activo;

	/** Propiedad is actualizar lindero. */
	private String is_actualizarLindero;

	/** Propiedad is alerta titular. */
	private String is_alertaTitular;

	/** Propiedad is busqueda titular. */
	private String is_busquedaTitular;

	/** Propiedad is construir complementacion. */
	private String is_construirComplementacion;

	/** Propiedad is habilita secuencia. */
	private String is_habilitaSecuenciaString;

	/** Propiedad is habilitada calificacion. */
	private String is_habilitadaCalificacion;

	/** Propiedad is heredar anotacion. */
	private String is_heredarAnotacion;

	/** Propiedad is id dominio nat jur. */
	private String is_idDominioNatJur;

	/** Propiedad is id dominio rrr. */
	private String is_idDominioRrr;

	/** Propiedad is id grupo nat jur. */
	private String is_idGrupoNatJur;

	/** Propiedad is id naturaleza juridica. */
	private String is_idNaturalezaJuridica;

	/** Propiedad is id tipo rrr. */
	private String is_idtipoRrr;

	/** Propiedad is llave primaria. */
	private String is_llavePrimaria;

	/** Propiedad is nombre. */
	private String is_nombre;

	/** Propiedad is propietario. */
	private String is_propietario;

	/** Propiedad is requiere cierre folio. */
	private String is_requiereCierreFolioString;

	/** Propiedad is rol. */
	private String is_rol;

	/** Propiedad is sumatoria area. */
	private String is_sumatoriaArea;

	/** Propiedad is sumatoria coeficiente. */
	private String is_sumatoriaCoeficiente;

	/** Propiedad is validar area. */
	private String is_validarAreaString;

	/** Propiedad ib habilita secuencia. */
	private boolean ib_habilitaSecuencia;

	/** Propiedad ib requiere cierre folio. */
	private boolean ib_requiereCierreFolio;

	/** Propiedad ib validar area. */
	private boolean ib_validarArea;

	/** Propiedad il version. */
	private long il_version;

	/**
	 * Constructor por defecto.
	 */
	public NaturalezaJuridica()
	{
	}

	/**
	 * Constructor con argumento de naturaleza juridica.
	 *
	 * @param as_s argumento de naturaleza juridica
	 */
	public NaturalezaJuridica(String as_s)
	{
		is_idNaturalezaJuridica = as_s;
	}

	/**
	 * Modifica el valor de AlertaTitular.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setAlertaTitular(String as_s)
	{
		this.is_alertaTitular = as_s;
	}

	/**
	 * Modifica el valor de HabilitaSecuenciaString.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setHabilitaSecuenciaString(String as_s)
	{
		is_habilitaSecuenciaString = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor habilita secuencia string.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getHabilitaSecuenciaString()
	{
		return is_habilitaSecuenciaString;
	}

	/**
	 * Modifica el valor de HeredarAnotacion.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setHeredarAnotacion(String as_s)
	{
		is_heredarAnotacion = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor heredar anotacion.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getHeredarAnotacion()
	{
		return is_heredarAnotacion;
	}

	/**
	 * Modifica el valor de ValidarAreaString.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setValidarAreaString(String as_s)
	{
		is_validarAreaString = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor validar area string.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getValidarAreaString()
	{
		return is_validarAreaString;
	}

	/**
	 * Modifica el valor de RequiereCierreFolioString.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setRequiereCierreFolioString(String as_s)
	{
		is_requiereCierreFolioString = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor requiere cierre folio string.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getRequiereCierreFolioString()
	{
		return is_requiereCierreFolioString;
	}

	/**
	 * Modifica el valor de SumatoriaArea.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setSumatoriaArea(String as_s)
	{
		is_sumatoriaArea = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor sumatoria area.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getSumatoriaArea()
	{
		return is_sumatoriaArea;
	}

	/**
	 * Modifica el valor de SumatoriaCoeficiente.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setSumatoriaCoeficiente(String as_s)
	{
		is_sumatoriaCoeficiente = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor sumatoria coeficiente.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getSumatoriaCoeficiente()
	{
		return is_sumatoriaCoeficiente;
	}

	/**
	 * Modifica el valor de ActualizarLindero.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setActualizarLindero(String as_s)
	{
		is_actualizarLindero = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor actualizar lindero.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getActualizarLindero()
	{
		return is_actualizarLindero;
	}

	/**
	 * Modifica el valor de Propietario.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setPropietario(String as_s)
	{
		is_propietario = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor propietario.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getPropietario()
	{
		return is_propietario;
	}

	/**
	 * Modifica el valor de Rol.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setRol(String as_s)
	{
		is_rol = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor rol.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getRol()
	{
		return is_rol;
	}

	/**
	 * Modifica el valor de Activo.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setActivo(String as_s)
	{
		is_activo = as_s;
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
	 * Retorna Objeto o variable de valor alerta titular.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getAlertaTitular()
	{
		return is_alertaTitular;
	}

	/**
	 * Modifica el valor de BusquedaTitular.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setBusquedaTitular(String as_s)
	{
		this.is_busquedaTitular = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor busqueda titular.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getBusquedaTitular()
	{
		return is_busquedaTitular;
	}

	/**
	 * Modifica el valor de ConstruirComplementacion.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setConstruirComplementacion(String as_s)
	{
		this.is_construirComplementacion = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor construir complementacion.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getConstruirComplementacion()
	{
		return is_construirComplementacion;
	}

	/**
	 * Modifica el valor de HabilitaSecuencia.
	 *
	 * @param ab_b asigna el valor a la propiedad
	 */
	public void setHabilitaSecuencia(boolean ab_b)
	{
		this.ib_habilitaSecuencia = ab_b;
	}

	/**
	 * Valida la propiedad habilita secuencia.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public boolean isHabilitaSecuencia()
	{
		return ib_habilitaSecuencia;
	}

	/**
	 * Modifica el valor de HabilitadaCalificacion.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setHabilitadaCalificacion(String as_s)
	{
		this.is_habilitadaCalificacion = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor habilitada calificacion.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getHabilitadaCalificacion()
	{
		return is_habilitadaCalificacion;
	}

	/**
	 * Modifica el valor de IdDominioNatJur.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setIdDominioNatJur(String as_s)
	{
		this.is_idDominioNatJur = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor id dominio nat jur.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getIdDominioNatJur()
	{
		return is_idDominioNatJur;
	}

	/**
	 * Modifica el valor de IdGrupoNatJur.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setIdGrupoNatJur(String as_s)
	{
		this.is_idGrupoNatJur = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor id grupo nat jur.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getIdGrupoNatJur()
	{
		return is_idGrupoNatJur;
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
	 * Modifica el valor de IdtipoRrr.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setIdtipoRrr(String as_s)
	{
		this.is_idtipoRrr = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor idtipo rrr.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getIdtipoRrr()
	{
		return is_idtipoRrr;
	}

	/**
	 * Modifica el valor de LlavePrimaria.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setLlavePrimaria(String as_s)
	{
		this.is_llavePrimaria = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor llave primaria.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getLlavePrimaria()
	{
		return is_llavePrimaria;
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
	 * Modifica el valor de RequiereCierreFolio.
	 *
	 * @param ab_b asigna el valor a la propiedad
	 */
	public void setRequiereCierreFolio(boolean ab_b)
	{
		this.ib_requiereCierreFolio = ab_b;
	}

	/**
	 * Valida la propiedad requiere cierre folio.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public boolean isRequiereCierreFolio()
	{
		return ib_requiereCierreFolio;
	}

	/**
	 * Modifica el valor de ValidarArea.
	 *
	 * @param ab_b asigna el valor a la propiedad
	 */
	public void setValidarArea(boolean ab_b)
	{
		this.ib_validarArea = ab_b;
	}

	/**
	 * Valida la propiedad validar area.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public boolean isValidarArea()
	{
		return ib_validarArea;
	}

	/**
	 * Modifica el valor de Version.
	 *
	 * @param al_l asigna el valor a la propiedad
	 */
	public void setVersion(long al_l)
	{
		this.il_version = al_l;
	}

	/**
	 * Retorna Objeto o variable de valor version.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public long getVersion()
	{
		return il_version;
	}

	/**
	 * Retorna Objeto o variable de valor id dominio rrr.
	 *
	 * @return Retorna el valor de la propiedad idDominioRrr
	 */
	public String getIdDominioRrr()
	{
		return is_idDominioRrr;
	}

	/**
	 * Modifica el valor de IdDominioRrr.
	 *
	 * @param as_s de as s
	 */
	public void setIdDominioRrr(String as_s)
	{
		is_idDominioRrr = as_s;
	}
}
