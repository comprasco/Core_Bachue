package com.bachue.snr.prosnr21.model.pgn;

import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.model.auditoria.Auditoria;

import com.bachue.snr.prosnr21.common.constants.EstadoCommon;

import java.util.Date;


/**
 * Clase que representa un registro en la vista SDB_CON_INCONSISTENCIA del módulo de conciliaciones.
 *
 * @author Gabriel Arias
 */
public class ConInconsistencia extends Auditoria implements java.io.Serializable
{
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -121467198896248843L;

	/** The fecha solucion. */
	private Date id_fechaSolucion;

	/** The id bitacora. */
	private Long il_idBitacora;

	/** The id registro. */
	private Long il_idRegistro;

	/** Propiedad is banco. */
	private String is_banco;

	/** The codigo inconsistencia. */
	private String is_codigoInconsistencia;

	/** The columna. */
	private String is_columna;

	/** Propiedad is corte. */
	private String is_corte;

	/** Propiedad is cuenta. */
	private String is_cuenta;

	/** The estado. */
	private String is_estado;

	/** The id archivo. */
	private String is_idArchivo;

	/** The id cuenta. */
	private String is_idCuenta;

	/** The id inconsistencia. */
	private String is_idInconsistencia;

	/** The id periodo corte. */
	private String is_idPeriodoCorte;

	/** Propiedad is mensaje. */
	private String is_mensaje;

	/** Propiedad is observaciones. */
	private String is_observaciones;

	/** Propiedad is periodo. */
	private String is_periodo;

	/** Propiedad is tipo. */
	private String is_tipo;

	/** Propiedad is usuario Solucion. */
	private String is_usuarioSolucion;

	/** Propiedad is valor Solucion. */
	private String is_valorSolucion;

	/**
	 * Instantiates a new con inconsistencia.
	 *
	 * @param as_idUsuario the as id usuario creacion
	 * @param as_ip the as ip creacion
	 * @param as_estado the as estado
	 */
	public ConInconsistencia(String as_idUsuario, String as_ip, String as_estado)
	{
		setIdUsuarioCreacion(as_idUsuario);
		setIdUsuarioModificacion(as_idUsuario);
		setIpCreacion(as_ip);
		setIpModificacion(as_ip);
		setEstado(as_estado);
	}

	/**
	 * Instantiates a new con inconsistencia.
	 */
	public ConInconsistencia()
	{
	}

	/**
	 * Instantiates a new con inconsistencia.
	 *
	 * @param aci_param the aci param
	 */
	public ConInconsistencia(ConInconsistencia aci_param)
	{
		setIdUsuarioCreacion(aci_param.getIdUsuarioCreacion());
		setIpCreacion(aci_param.getIpCreacion());
		setIdUsuarioModificacion(aci_param.getIdUsuarioCreacion());
		setIpModificacion(aci_param.getIpCreacion());

		setEstado(aci_param.getEstado());
		setIdPeriodoCorte(aci_param.getIdPeriodoCorte());
		setIdCuenta(aci_param.getIdCuenta());
		setIdArchivo(aci_param.getIdArchivo());
	}

	/**
	 * Checks if is activo.
	 *
	 * @return true, if is activo
	 */
	public boolean isActivo()
	{
		String ls_estado;

		ls_estado = getEstado();

		return StringUtils.isValidString(ls_estado) && ls_estado.equalsIgnoreCase(EstadoCommon.ACTIVO);
	}

	/**
	 * Sets the banco.
	 *
	 * @param as_banco the new banco
	 */
	public void setBanco(String as_banco)
	{
		is_banco = as_banco;
	}

	/**
	 * Gets the banco.
	 *
	 * @return the banco
	 */
	public String getBanco()
	{
		return is_banco;
	}

	/**
	 * Sets the codigo inconsistencia.
	 *
	 * @param as_codigoInconsistencia Modifica el valor de la propiedad codigoInconsistencia
	 */
	public void setCodigoInconsistencia(String as_codigoInconsistencia)
	{
		is_codigoInconsistencia = as_codigoInconsistencia;
	}

	/**
	 * Gets the codigo inconsistencia.
	 *
	 * @return Retorna el valor de la propiedad codigoInconsistencia
	 */
	public String getCodigoInconsistencia()
	{
		return is_codigoInconsistencia;
	}

	/**
	 * Sets the columna.
	 *
	 * @param as_columna Modifica el valor de la propiedad columna
	 */
	public void setColumna(String as_columna)
	{
		is_columna = as_columna;
	}

	/**
	 * Gets the columna.
	 *
	 * @return Retorna el valor de la propiedad columna
	 */
	public String getColumna()
	{
		return is_columna;
	}

	/**
	 * Sets the corte.
	 *
	 * @param as_corte the new corte
	 */
	public void setCorte(String as_corte)
	{
		is_corte = as_corte;
	}

	/**
	 * Gets the corte.
	 *
	 * @return the corte
	 */
	public String getCorte()
	{
		return is_corte;
	}

	/**
	 * Sets the cuenta.
	 *
	 * @param as_cuenta the new cuenta
	 */
	public void setCuenta(String as_cuenta)
	{
		is_cuenta = as_cuenta;
	}

	/**
	 * Gets the cuenta.
	 *
	 * @return the cuenta
	 */
	public String getCuenta()
	{
		return is_cuenta;
	}

	/**
	 * Sets the estado.
	 *
	 * @param as_estado Modifica el valor de la propiedad estado
	 */
	public void setEstado(String as_estado)
	{
		is_estado = as_estado;
	}

	/**
	 * Gets the estado.
	 *
	 * @return Retorna el valor de la propiedad estado
	 */
	public String getEstado()
	{
		return is_estado;
	}

	/**
	 * Sets the fecha solucion.
	 *
	 * @param ad_fechaSolucion Modifica el valor de la propiedad fechaSolucion
	 */
	public void setFechaSolucion(Date ad_fechaSolucion)
	{
		id_fechaSolucion = ad_fechaSolucion;
	}

	/**
	 * Gets the fecha solucion.
	 *
	 * @return Retorna el valor de la propiedad fechaSolucion
	 */
	public Date getFechaSolucion()
	{
		return id_fechaSolucion;
	}

	/**
	 * Sets the id archivo.
	 *
	 * @param as_idArchivo Modifica el valor de la propiedad idArchivo
	 */
	public void setIdArchivo(String as_idArchivo)
	{
		is_idArchivo = as_idArchivo;
	}

	/**
	 * Gets the id archivo.
	 *
	 * @return Retorna el valor de la propiedad idArchivo
	 */
	public String getIdArchivo()
	{
		return is_idArchivo;
	}

	/**
	 * Sets the id bitacora.
	 *
	 * @param al_idBitacora Modifica el valor de la propiedad idBitacora
	 */
	public void setIdBitacora(Long al_idBitacora)
	{
		il_idBitacora = al_idBitacora;
	}

	/**
	 * Gets the id bitacora.
	 *
	 * @return Retorna el valor de la propiedad idBitacora
	 */
	public Long getIdBitacora()
	{
		return il_idBitacora;
	}

	/**
	 * Sets the id cuenta.
	 *
	 * @param as_idCuenta Modifica el valor de la propiedad idCuenta
	 */
	public void setIdCuenta(String as_idCuenta)
	{
		is_idCuenta = as_idCuenta;
	}

	/**
	 * Gets the id cuenta.
	 *
	 * @return Retorna el valor de la propiedad idCuenta
	 */
	public String getIdCuenta()
	{
		return is_idCuenta;
	}

	/**
	 * Sets the id inconsistencia.
	 *
	 * @param as_idInconsistencia Modifica el valor de la propiedad idInconsistencia
	 */
	public void setIdInconsistencia(String as_idInconsistencia)
	{
		is_idInconsistencia = as_idInconsistencia;
	}

	/**
	 * Gets the id inconsistencia.
	 *
	 * @return Retorna el valor de la propiedad idInconsistencia
	 */
	public String getIdInconsistencia()
	{
		return is_idInconsistencia;
	}

	/**
	 * Sets the id periodo corte.
	 *
	 * @param as_idPeriodoCorte Modifica el valor de la propiedad idPeriodoCorte
	 */
	public void setIdPeriodoCorte(String as_idPeriodoCorte)
	{
		is_idPeriodoCorte = as_idPeriodoCorte;
	}

	/**
	 * Gets the id periodo corte.
	 *
	 * @return Retorna el valor de la propiedad idPeriodoCorte
	 */
	public String getIdPeriodoCorte()
	{
		return is_idPeriodoCorte;
	}

	/**
	 * Sets the id registro.
	 *
	 * @param al_idRegistro Modifica el valor de la propiedad idRegistro
	 */
	public void setIdRegistro(Long al_idRegistro)
	{
		il_idRegistro = al_idRegistro;
	}

	/**
	 * Gets the id registro.
	 *
	 * @return Retorna el valor de la propiedad idRegistro
	 */
	public Long getIdRegistro()
	{
		return il_idRegistro;
	}

	/**
	 * Modifica el valor de Mensaje.
	 *
	 * @param as_mensaje de mensaje
	 */
	public void setMensaje(String as_mensaje)
	{
		is_mensaje = as_mensaje;
	}

	/**
	 * Retorna Objeto o variable de valor mensaje.
	 *
	 * @return el valor de mensaje
	 */
	public String getMensaje()
	{
		return is_mensaje;
	}

	/**
	 * Modifica el valor de Observaciones.
	 *
	 * @param as_observaciones de observaciones
	 */
	public void setObservaciones(String as_observaciones)
	{
		is_observaciones = as_observaciones;
	}

	/**
	 * Retorna Objeto o variable de valor observaciones.
	 *
	 * @return el valor de observaciones
	 */
	public String getObservaciones()
	{
		return is_observaciones;
	}

	/**
	 * Sets the periodo.
	 *
	 * @param as_periodo the new periodo
	 */
	public void setPeriodo(String as_periodo)
	{
		is_periodo = as_periodo;
	}

	/**
	 * Gets the periodo.
	 *
	 * @return the periodo
	 */
	public String getPeriodo()
	{
		return is_periodo;
	}

	/**
	 * Modifica el valor de Tipo.
	 *
	 * @param as_tipo de tipo
	 */
	public void setTipo(String as_tipo)
	{
		is_tipo = as_tipo;
	}

	/**
	 * Retorna Objeto o variable de valor tipo.
	 *
	 * @return el valor de tipo
	 */
	public String getTipo()
	{
		return is_tipo;
	}

	/**
	 * Sets the usuario solucion.
	 *
	 * @param as_usuarioSolucion Modifica el valor de la propiedad usuarioSolucion
	 */
	public void setUsuarioSolucion(String as_usuarioSolucion)
	{
		is_usuarioSolucion = as_usuarioSolucion;
	}

	/**
	 * Gets the usuario solucion.
	 *
	 * @return Retorna el valor de la propiedad usuarioSolucion
	 */
	public String getUsuarioSolucion()
	{
		return is_usuarioSolucion;
	}

	/**
	 * Sets the valor solucion.
	 *
	 * @param as_valorSolucion Modifica el valor de la propiedad valorSolucion
	 */
	public void setValorSolucion(String as_valorSolucion)
	{
		is_valorSolucion = as_valorSolucion;
	}

	/**
	 * Gets the valor solucion.
	 *
	 * @return Retorna el valor de la propiedad valorSolucion
	 */
	public String getValorSolucion()
	{
		return is_valorSolucion;
	}
}
