package com.bachue.snr.prosnr01.model.sdb.acc;

import com.bachue.snr.prosnr01.model.auditoria.Auditoria;

import java.io.Serializable;

import java.math.BigDecimal;

import java.util.Date;


/**
 * Clase para la abstracción de la tabla SDB_ACC_CUENTA_CUPO.
 *
 * @author Manuel Blanco
 */
public class CuentaCupo extends Auditoria implements Serializable
{
	/**  Constante serialVersionUID. */
	private static final long serialVersionUID = 1882928111315207144L;

	/**  Propiedad ibd_montoSolicitado. */
	private BigDecimal ibd_montoSolicitado;

	/**  Propiedad ibd_saldo. */
	private BigDecimal ibd_saldo;

	/**  Propiedad ibd_valorMaximo. */
	private BigDecimal ibd_valorMaximo;

	/**  Propiedad ibd_valorMinimo. */
	private BigDecimal ibd_valorMinimo;

	/** Propiedad id fecha solicitud. */
	private Date id_fechaSolicitud;

	/**  Propiedad is_codigo. */
	private String is_codigo;

	/**  Propiedad is_descripcionFrecuenciaRecargas. */
	private String is_descripcionFrecuenciaRecargas;

	/**  Propiedad is_descripcionNecesidadDeCuentaCupo. */
	private String is_descripcionNecesidadDeCuentaCupo;

	/**  Propiedad is_descripcionOrigenRecursos. */
	private String is_descripcionOrigenRecursos;

	/**  Propiedad is_estado. */
	private String is_estado;

	/**  Propiedad is_idCuentaCupo. */
	private String is_idCuentaCupo;

	/**  Propiedad is_idEntidadExterna. */
	private String is_idEntidadExterna;

	/**  Propiedad is_idSolicitud. */
	private String is_idSolicitud;

	/**  Propiedad is_migrado. */
	private String is_migrado;

	/**  Propiedad is_representanteLegalOcupacionProfesion. */
	private String is_representanteLegalOcupacionProfesion;

	/**
	 * Retorna el valor de is_idCuentaCupo.
	 *
	 * @return el valor de is_idCuentaCupo
	 */
	public String getIdCuentaCupo()
	{
		return is_idCuentaCupo;
	}

	/**
	 * Modifica el valor de is_idCuentaCupo.
	 *
	 * @param as_s asigna el valor a la propiedad is_idCuentaCupo
	 */
	public void setIdCuentaCupo(String as_s)
	{
		is_idCuentaCupo = as_s;
	}

	/**
	 * Retorna el valor de is_idEntidadExterna.
	 *
	 * @return el valor de is_idEntidadExterna
	 */
	public String getIdEntidadExterna()
	{
		return is_idEntidadExterna;
	}

	/**
	 * Modifica el valor de is_idEntidadExterna.
	 *
	 * @param as_s asigna el valor a la propiedad is_idEntidadExterna
	 */
	public void setIdEntidadExterna(String as_s)
	{
		is_idEntidadExterna = as_s;
	}

	/**
	 * Retorna el valor de is_codigo.
	 *
	 * @return el valor de is_codigo
	 */
	public String getCodigo()
	{
		return is_codigo;
	}

	/**
	 * Modifica el valor de is_codigo.
	 *
	 * @param as_s asigna el valor a la propiedad is_codigo
	 */
	public void setCodigo(String as_s)
	{
		is_codigo = as_s;
	}

	/**
	 * Retorna el valor de ibd_valorMaximo.
	 *
	 * @return el valor de ibd_valorMaximo
	 */
	public BigDecimal getValorMaximo()
	{
		return ibd_valorMaximo;
	}

	/**
	 * Modifica el valor de ibd_valorMaximo.
	 *
	 * @param abd_bd asigna el valor a la propiedad ibd_valorMaximo
	 */
	public void setValorMaximo(BigDecimal abd_bd)
	{
		ibd_valorMaximo = abd_bd;
	}

	/**
	 * Retorna el valor de ibd_valorMinimo.
	 *
	 * @return el valor de ibd_valorMinimo
	 */
	public BigDecimal getValorMinimo()
	{
		return ibd_valorMinimo;
	}

	/**
	 * Modifica el valor de ibd_valorMinimo.
	 *
	 * @param abd_bd asigna el valor a la propiedad ibd_valorMinimo
	 */
	public void setValorMinimo(BigDecimal abd_bd)
	{
		ibd_valorMinimo = abd_bd;
	}

	/**
	 * Retorna el valor de ibd_saldo.
	 *
	 * @return el valor de ibd_saldo
	 */
	public BigDecimal getSaldo()
	{
		return ibd_saldo;
	}

	/**
	 * Modifica el valor de ibd_saldo.
	 *
	 * @param abd_bd asigna el valor a la propiedad ibd_saldo
	 */
	public void setSaldo(BigDecimal abd_bd)
	{
		ibd_saldo = abd_bd;
	}

	/**
	 * Retorna el valor de is_migrado.
	 *
	 * @return el valor de is_migrado
	 */
	public String getMigrado()
	{
		return is_migrado;
	}

	/**
	 * Modifica el valor de is_migrado.
	 *
	 * @param as_s asigna el valor a la propiedad is_migrado
	 */
	public void setMigrado(String as_s)
	{
		is_migrado = as_s;
	}

	/**
	 * Retorna el valor de is_estado.
	 *
	 * @return el valor de is_estado
	 */
	public String getEstado()
	{
		return is_estado;
	}

	/**
	 * Modifica el valor de is_estado.
	 *
	 * @param as_s asigna el valor a la propiedad is_estado
	 */
	public void setEstado(String as_s)
	{
		is_estado = as_s;
	}

	/**
	 * Retorna el valor de is_idSolicitud.
	 *
	 * @return el valor de is_idSolicitud
	 */
	public String getIdSolicitud()
	{
		return is_idSolicitud;
	}

	/**
	 * Modifica el valor de is_estado.
	 *
	 * @param as_s asigna el valor a la propiedad is_idSolicitud
	 */
	public void setIdSolicitud(String as_s)
	{
		is_idSolicitud = as_s;
	}

	/**
	 * Retorna el valor de ibd_montoSolicitado.
	 *
	 * @return el valor de ibd_montoSolicitado
	 */
	public BigDecimal getMontoSolicitado()
	{
		return ibd_montoSolicitado;
	}

	/**
	 * Modifica el valor de is_estado.
	 *
	 * @param abd_bd asigna el valor a la propiedad ibd_montoSolicitado
	 */
	public void setMontoSolicitado(BigDecimal abd_bd)
	{
		ibd_montoSolicitado = abd_bd;
	}

	/**
	 * Retorna el valor de is_descripcionFrecuenciaRecargas.
	 *
	 * @return el valor de is_descripcionFrecuenciaRecargas
	 */
	public String getDescripcionFrecuenciaRecargas()
	{
		return is_descripcionFrecuenciaRecargas;
	}

	/**
	 * Modifica el valor de is_estado.
	 *
	 * @param as_s asigna el valor a la propiedad is_descripcionFrecuenciaRecargas
	 */
	public void setDescripcionFrecuenciaRecargas(String as_s)
	{
		is_descripcionFrecuenciaRecargas = as_s;
	}

	/**
	 * Retorna el valor de is_descripcionNecesidadDeCuentaCupo.
	 *
	 * @return el valor de is_descripcionNecesidadDeCuentaCupo
	 */
	public String getDescripcionNecesidadDeCuentaCupo()
	{
		return is_descripcionNecesidadDeCuentaCupo;
	}

	/**
	 * Modifica el valor de is_estado.
	 *
	 * @param as_s asigna el valor a la propiedad is_descripcionNecesidadDeCuentaCupo
	 */
	public void setDescripcionNecesidadDeCuentaCupo(String as_s)
	{
		is_descripcionNecesidadDeCuentaCupo = as_s;
	}

	/**
	 * Retorna el valor de is_descripcionOrigenRecursos.
	 *
	 * @return el valor de is_descripcionOrigenRecursos
	 */
	public String getDescripcionOrigenRecursos()
	{
		return is_descripcionOrigenRecursos;
	}

	/**
	 * Modifica el valor de is_estado.
	 *
	 * @param as_s asigna el valor a la propiedad is_descripcionOrigenRecursos
	 */
	public void setDescripcionOrigenRecursos(String as_s)
	{
		is_descripcionOrigenRecursos = as_s;
	}

	/**
	 * Retorna el valor de is_representanteLegalOcupacionProfesion.
	 *
	 * @return el valor de is_representanteLegalOcupacionProfesion
	 */
	public String getRepresentanteLegalOcupacionProfesion()
	{
		return is_representanteLegalOcupacionProfesion;
	}

	/**
	 * Modifica el valor de is_estado.
	 *
	 * @param as_s asigna el valor a la propiedad is_representanteLegalOcupacionProfesion
	 */
	public void setRepresentanteLegalOcupacionProfesion(String as_s)
	{
		is_representanteLegalOcupacionProfesion = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor fecha solicitud.
	 *
	 * @return Retorna el valor de la propiedad fechaSolicitud
	 */
	public Date getFechaSolicitud()
	{
		return id_fechaSolicitud;
	}

	/**
	 * Modifica el valor de FechaSolicitud.
	 *
	 * @param ad_d Modifica el valor de la propiedad fechaSolicitud
	 */
	public void setFechaSolicitud(Date ad_d)
	{
		id_fechaSolicitud = ad_d;
	}
}
