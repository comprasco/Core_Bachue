package com.bachue.snr.prosnr01.model.sdb.acc;

import com.bachue.snr.prosnr01.model.auditoria.Auditoria;

import java.io.Serializable;

import java.util.Collection;
import java.util.Date;



/**
 * Abstraccion de tabla de Subproceso.
 *
 * @author Manuel Blanco
 */
public class AccSalvedadPredio extends Auditoria implements Serializable
{
	
	/** Constante serialVersionUID. */
	private static final long              serialVersionUID       = 2601965384224239492L;
	
	/** Propiedad icsm matriculas. */
	private Collection<SolicitudMatricula> icsm_matriculas;
	
	/** Propiedad fecha registro. */
	private Date                           fechaRegistro;
	
	/** Propiedad il id causal. */
	private Long                           il_idCausal;
	
	/** Propiedad il id matricula. */
	private Long                           il_idMatricula;
	
	/** Propiedad il id turno historia. */
	private Long                           il_idTurnoHistoria;
	
	/** Propiedad il descripcion. */
	private String                         il_descripcion;
	
	/** Propiedad il id circulo. */
	private String                         il_idCirculo;
	
	/** Propiedad il id salvedad predio. */
	private String                         il_idSalvedadPredio;
	
	/** Propiedad il id turno. */
	private String                         il_idTurno;
	
	/** Propiedad il radicacion. */
	private String                         il_radicacion;
	
	/** Propiedad is cambio estado. */
	private String                         is_cambioEstado;
	
	/** Propiedad is copiar. */
	private String                         is_copiar;
	
	/** Propiedad is copiar seleccionadas. */
	private String                         is_copiarSeleccionadas;
	
	/** Propiedad is justificacion. */
	private String                         is_justificacion;
	
	/** Propiedad is nombre. */
	private String                         is_nombre;
	
	/** Propiedad ib cierre folio. */
	private boolean                        ib_cierreFolio;

	/**
	 * Modifica el valor de CambioEstado.
	 *
	 * @param as_s de as s
	 */
	public void setCambioEstado(String as_s)
	{
		is_cambioEstado                                           = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor cambio estado.
	 *
	 * @return Retorna el valor de la propiedad cambioEstado
	 */
	public String getCambioEstado()
	{
		return is_cambioEstado;
	}

	/**
	 * Modifica el valor de CierreFolio.
	 *
	 * @param ab_b de ab b
	 */
	public void setCierreFolio(boolean ab_b)
	{
		ib_cierreFolio = ab_b;
	}

	/**
	 * Valida la propiedad cierre folio.
	 *
	 * @return Retorna el valor de la propiedad cierreFolio
	 */
	public boolean isCierreFolio()
	{
		return ib_cierreFolio;
	}

	/**
	 * Modifica el valor de Copiar.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setCopiar(String as_s)
	{
		is_copiar = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor copiar.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getCopiar()
	{
		return is_copiar;
	}

	/**
	 * Modifica el valor de CopiarSeleccionadas.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setCopiarSeleccionadas(String as_s)
	{
		is_copiarSeleccionadas = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor copiar seleccionadas.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getCopiarSeleccionadas()
	{
		return is_copiarSeleccionadas;
	}

	/**
	 * Modifica el valor de Descripcion.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setDescripcion(String as_s)
	{
		il_descripcion = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor descripcion.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getDescripcion()
	{
		return il_descripcion;
	}

	/**
	 * Modifica el valor de FechaRegistro.
	 *
	 * @param ad_d asigna el valor a la propiedad
	 */
	public void setFechaRegistro(Date ad_d)
	{
		fechaRegistro = ad_d;
	}

	/**
	 * Retorna Objeto o variable de valor fecha registro.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public Date getFechaRegistro()
	{
		return fechaRegistro;
	}

	/**
	 * Modifica el valor de IdCausal.
	 *
	 * @param al_l asigna el valor a la propiedad
	 */
	public void setIdCausal(Long al_l)
	{
		il_idCausal = al_l;
	}

	/**
	 * Retorna Objeto o variable de valor id causal.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public Long getIdCausal()
	{
		return il_idCausal;
	}

	/**
	 * Modifica el valor de IdCirculo.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setIdCirculo(String as_s)
	{
		il_idCirculo = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor id circulo.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getIdCirculo()
	{
		return il_idCirculo;
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
	 * Modifica el valor de IdSalvedadPredio.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setIdSalvedadPredio(String as_s)
	{
		il_idSalvedadPredio = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor id salvedad predio.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getIdSalvedadPredio()
	{
		return il_idSalvedadPredio;
	}

	/**
	 * Modifica el valor de IdTurno.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setIdTurno(String as_s)
	{
		il_idTurno = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor id turno.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getIdTurno()
	{
		return il_idTurno;
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
	 * Modifica el valor de Justificacion.
	 *
	 * @param as_s de as s
	 */
	public void setJustificacion(String as_s)
	{
		is_justificacion = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor justificacion.
	 *
	 * @return Retorna el valor de la propiedad justificacion
	 */
	public String getJustificacion()
	{
		return is_justificacion;
	}

	/**
	 * Modifica el valor de Matriculas.
	 *
	 * @param acsm_csm asigna el valor a la propiedad
	 */
	public void setMatriculas(Collection<SolicitudMatricula> acsm_csm)
	{
		icsm_matriculas = acsm_csm;
	}

	/**
	 * Retorna Objeto o variable de valor matriculas.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public Collection<SolicitudMatricula> getMatriculas()
	{
		return icsm_matriculas;
	}

	/**
	 * Modifica el valor de Nombre.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setNombre(String as_s)
	{
		is_nombre = as_s;
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
	 * Modifica el valor de Radicacion.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setRadicacion(String as_s)
	{
		il_radicacion = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor radicacion.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getRadicacion()
	{
		return il_radicacion;
	}
}
