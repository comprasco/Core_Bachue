package com.bachue.snr.prosnr01.model.reasignar;

import java.io.Serializable;

import java.math.BigDecimal;

import java.util.Date;


/**
 * Clase que contiene todos las propiedades ReasignarTurnos.
 *
 * @author ccalderon
 */
public class ReasignarTurnos implements Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = -7855496595370779786L;

	/** Propiedad ibd id turno historia. */
	private BigDecimal ibd_idTurnoHistoria;

	/** Propiedad id fecha creacion. */
	private Date id_fechaCreacion;

	/** Propiedad id fecha reasignacion. */
	private Date id_fechaReasignacion;

	/** Propiedad is descripcion respuesta. */
	private String is_descripcionRespuesta;

	/** Propiedad is_expediente. */
	private String is_expediente;

	/** Propiedad is_idCirculo. */
	private String is_idCirculo;

	/** Propiedad is_idRol. */
	private String is_idRol;

	/** Propiedad is_idTipoExpediente. */
	private String is_idTipoExpediente;

	/** Propiedad is id turno. */
	private String is_idTurno;

	/** Propiedad is id turno hijo. */
	private String is_idTurnoHijo;

	/** Propiedad is justificacion. */
	private String is_justificacion;

	/** Propiedad is nir. */
	private String is_nir;

	/** Propiedad is_observaciones. */
	private String is_observaciones;

	/** Propiedad is usuario actual. */
	private String is_usuarioActual;

	/** Propiedad is usuario anterior. */
	private String is_usuarioAnterior;

	/** Propiedad is usuario reasignacion. */
	private String is_usuarioReasignacion;

	/** Propiedad ib es predio inconsistente. */
	private boolean ib_esPredioInconsistente;

	/** Propiedad ib inhabilitado. */
	private boolean ib_inhabilitado;

	/** Propiedad ib seleccionado. */
	private boolean ib_seleccionado;

	/** Propiedad ii id orden turno. */
	private int ii_idOrdenTurno;

	/** Propiedad il_idEtapa. */
	private long il_idEtapa;

	/**
	 * Modifica el valor de descripcion respuesta.
	 *
	 * @param as_s asigna el valor a la propiedad descripcion respuesta
	 */
	public void setDescripcionRespuesta(String as_s)
	{
		is_descripcionRespuesta = as_s;
	}

	/**
	 * Retorna el valor de descripcion respuesta.
	 *
	 * @return el valor de descripcion respuesta
	 */
	public String getDescripcionRespuesta()
	{
		return is_descripcionRespuesta;
	}

	/**
	 * Modifica el valor de EsPredioInconsistente.
	 *
	 * @param ab_b de ab b
	 */
	public void setEsPredioInconsistente(boolean ab_b)
	{
		ib_esPredioInconsistente = ab_b;
	}

	/**
	 * Valida la propiedad es predio inconsistente.
	 *
	 * @return Retorna el valor de la propiedad esPredioInconsistente
	 */
	public boolean isEsPredioInconsistente()
	{
		return ib_esPredioInconsistente;
	}

	/**
	 * Modifica el valor de la propiedad.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setExpediente(String as_s)
	{
		is_expediente = as_s;
	}

	/**
	 * Retorna el valor de la propiedad.
	 *
	 * @return el valor de la propiedad
	 */
	public String getExpediente()
	{
		return is_expediente;
	}

	/**
	 * Modifica el valor de fecha creacion.
	 *
	 * @param ad_d asigna el valor a la propiedad fecha creacion
	 */
	public void setFechaCreacion(Date ad_d)
	{
		id_fechaCreacion = ad_d;
	}

	/**
	 * Retorna el valor de fecha creacion.
	 *
	 * @return el valor de fecha creacion
	 */
	public Date getFechaCreacion()
	{
		return id_fechaCreacion;
	}

	/**
	 * Modifica el valor de fecha reasignacion.
	 *
	 * @param ad_d asigna el valor a la propiedad fecha reasignacion
	 */
	public void setFechaReasignacion(Date ad_d)
	{
		id_fechaReasignacion = ad_d;
	}

	/**
	 * Retorna el valor de fecha reasignacion.
	 *
	 * @return el valor de fecha reasignacion
	 */
	public Date getFechaReasignacion()
	{
		return id_fechaReasignacion;
	}

	/**
	 * Modifica el valor de la propiedad.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setIdCirculo(String as_s)
	{
		is_idCirculo = as_s;
	}

	/**
	 * Retorna el valor de la propiedad.
	 *
	 * @return el valor de la propiedad
	 */
	public String getIdCirculo()
	{
		return is_idCirculo;
	}

	/**
	 * Modifica el valor de la propiedad.
	 *
	 * @param al_l asigna el valor a la propiedad
	 */
	public void setIdEtapa(long al_l)
	{
		il_idEtapa = al_l;
	}

	/**
	 * Retorna el valor de la propiedad.
	 *
	 * @return el valor de la propiedad
	 */
	public long getIdEtapa()
	{
		return il_idEtapa;
	}

	/**
	 * Modifica el valor de id orden turno.
	 *
	 * @param ai_i asigna el valor a la propiedad id orden turno
	 */
	public void setIdOrdenTurno(int ai_i)
	{
		ii_idOrdenTurno = ai_i;
	}

	/**
	 * Retorna el valor de id orden turno.
	 *
	 * @return el valor de id orden turno
	 */
	public int getIdOrdenTurno()
	{
		return ii_idOrdenTurno;
	}

	/**
	 * Modifica el valor de la propiedad.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setIdRol(String as_s)
	{
		is_idRol = as_s;
	}

	/**
	 * Retorna el valor de la propiedad.
	 *
	 * @return el valor de la propiedad
	 */
	public String getIdRol()
	{
		return is_idRol;
	}

	/**
	 * Modifica el valor de la propiedad.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setIdTipoExpediente(String as_s)
	{
		is_idTipoExpediente = as_s;
	}

	/**
	 * Retorna el valor de la propiedad.
	 *
	 * @return el valor de la propiedad
	 */
	public String getIdTipoExpediente()
	{
		return is_idTipoExpediente;
	}

	/**
	 * Modifica el valor de id turno.
	 *
	 * @param as_s asigna el valor a la propiedad id turno
	 */
	public void setIdTurno(String as_s)
	{
		is_idTurno = as_s;
	}

	/**
	 * Retorna el valor de id turno.
	 *
	 * @return el valor de id turno
	 */
	public String getIdTurno()
	{
		return is_idTurno;
	}

	/**
	 * Modifica el valor de id turno hijo.
	 *
	 * @param as_s asigna el valor a la propiedad id turno hijo
	 */
	public void setIdTurnoHijo(String as_s)
	{
		is_idTurnoHijo = as_s;
	}

	/**
	 * Retorna el valor de id turno hijo.
	 *
	 * @return el valor de id turno hijo
	 */
	public String getIdTurnoHijo()
	{
		return is_idTurnoHijo;
	}

	/**
	 * Modifica el valor de id turno historia.
	 *
	 * @param abd_bd asigna el valor a la propiedad id turno historia
	 */
	public void setIdTurnoHistoria(BigDecimal abd_bd)
	{
		ibd_idTurnoHistoria = abd_bd;
	}

	/**
	 * Retorna el valor de id turno historia.
	 *
	 * @return el valor de id turno historia
	 */
	public BigDecimal getIdTurnoHistoria()
	{
		return ibd_idTurnoHistoria;
	}

	/**
	 * Modifica el valor de inhabilitado.
	 *
	 * @param ab_b asigna el valor a la propiedad inhabilitado
	 */
	public void setInhabilitado(boolean ab_b)
	{
		ib_inhabilitado = ab_b;
	}

	/**
	 * Valida la propiedad inhabilitado.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en seleccionado
	 */
	public boolean isInhabilitado()
	{
		return ib_inhabilitado;
	}

	/**
	 * Modifica el valor de nir.
	 *
	 * @param as_s asigna el valor a la propiedad nir
	 */
	public void setNir(String as_s)
	{
		is_nir = as_s;
	}

	/**
	 * Retorna el valor de nir.
	 *
	 * @return el valor de nir
	 */
	public String getNir()
	{
		return is_nir;
	}

	/**
	 * Modifica el valor de la propiedad.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setObservaciones(String as_s)
	{
		is_observaciones = as_s;
	}

	/**
	 * Retorna el valor de la propiedad.
	 *
	 * @return el valor de la propiedad
	 */
	public String getObservaciones()
	{
		return is_observaciones;
	}

	/**
	 * Modifica el valor de seleccionado.
	 *
	 * @param ab_b asigna el valor a la propiedad seleccionado
	 */
	public void setSeleccionado(boolean ab_b)
	{
		ib_seleccionado = ab_b;
	}

	/**
	 * Valida la propiedad seleccionado.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en seleccionado
	 */
	public boolean isSeleccionado()
	{
		return ib_seleccionado;
	}

	/**
	 * Modifica el valor de usuario actual.
	 *
	 * @param as_s asigna el valor a la propiedad usuario actual
	 */
	public void setUsuarioActual(String as_s)
	{
		is_usuarioActual = as_s;
	}

	/**
	 * Retorna el valor de usuario actual.
	 *
	 * @return el valor de usuario actual
	 */
	public String getUsuarioActual()
	{
		return is_usuarioActual;
	}

	/**
	 * Modifica el valor de usuario anterior.
	 *
	 * @param as_s asigna el valor a la propiedad usuario anterior
	 */
	public void setUsuarioAnterior(String as_s)
	{
		is_usuarioAnterior = as_s;
	}

	/**
	 * Retorna el valor de usuario anterior.
	 *
	 * @return el valor de usuario anterior
	 */
	public String getUsuarioAnterior()
	{
		return is_usuarioAnterior;
	}

	/**
	 * Modifica el valor de usuario reasignacion.
	 *
	 * @param as_s asigna el valor a la propiedad usuario reasignacion
	 */
	public void setUsuarioReasignacion(String as_s)
	{
		is_usuarioReasignacion = as_s;
	}

	/**
	 * Retorna el valor de usuario reasignacion.
	 *
	 * @return el valor de usuario reasignacion
	 */
	public String getUsuarioReasignacion()
	{
		return is_usuarioReasignacion;
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
	 * Modifica el valor de Justificacion.
	 *
	 * @param as_s de as s
	 */
	public void setJustificacion(String as_s)
	{
		is_justificacion = as_s;
	}
}
