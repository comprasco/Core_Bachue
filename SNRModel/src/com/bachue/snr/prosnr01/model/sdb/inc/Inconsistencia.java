package com.bachue.snr.prosnr01.model.sdb.inc;

import com.bachue.snr.prosnr01.model.auditoria.Auditoria;

import java.io.Serializable;



/**
 * Clase modelo que contiene todos los atributos de la tabla SDB_INC_INCONSISTENCIA.
 *
 * @author Julian Vaca
 */
public class Inconsistencia extends Auditoria implements Serializable
{
	
	/** Constante serialVersionUID. */
	private static final long serialVersionUID        = 7710283104005719859L;
	
	/** Propiedad is campo. */
	private String            is_campo;
	
	/** Propiedad is id tipo inconsistencia. */
	private String            is_idTipoInconsistencia;
	
	/** Propiedad is id usuario. */
	private String            is_idUsuario;
	
	/** Propiedad is row id. */
	private String            is_rowId;
	
	/** Propiedad is tabla. */
	private String            is_tabla;
	
	/** Propiedad il id ejecucion. */
	private long              il_idEjecucion;
	
	/** Propiedad il id modulo. */
	private long              il_idModulo;

	/**
	 * Modifica el valor de Campo.
	 *
	 * @param campo asigna el valor a la propiedad
	 */
	public void setCampo(String campo)
	{
		this.is_campo                                 = campo;
	}

	/**
	 * Retorna Objeto o variable de valor campo.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getCampo()
	{
		return is_campo;
	}

	/**
	 * Modifica el valor de IdEjecucion.
	 *
	 * @param idEjecucion asigna el valor a la propiedad
	 */
	public void setIdEjecucion(long idEjecucion)
	{
		this.il_idEjecucion = idEjecucion;
	}

	/**
	 * Retorna Objeto o variable de valor id ejecucion.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public long getIdEjecucion()
	{
		return il_idEjecucion;
	}

	/**
	 * Modifica el valor de IdModulo.
	 *
	 * @param idModulo asigna el valor a la propiedad
	 */
	public void setIdModulo(long idModulo)
	{
		this.il_idModulo = idModulo;
	}

	/**
	 * Retorna Objeto o variable de valor id modulo.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public long getIdModulo()
	{
		return il_idModulo;
	}

	/**
	 * Modifica el valor de IdTipoInconsistencia.
	 *
	 * @param idTipoInconsistencia asigna el valor a la propiedad
	 */
	public void setIdTipoInconsistencia(String idTipoInconsistencia)
	{
		this.is_idTipoInconsistencia = idTipoInconsistencia;
	}

	/**
	 * Retorna Objeto o variable de valor id tipo inconsistencia.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getIdTipoInconsistencia()
	{
		return is_idTipoInconsistencia;
	}

	/**
	 * Modifica el valor de IdUsuario.
	 *
	 * @param idUsuario asigna el valor a la propiedad
	 */
	public void setIdUsuario(String idUsuario)
	{
		this.is_idUsuario = idUsuario;
	}

	/**
	 * Retorna Objeto o variable de valor id usuario.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getIdUsuario()
	{
		return is_idUsuario;
	}

	/**
	 * Modifica el valor de RowId.
	 *
	 * @param rowId asigna el valor a la propiedad
	 */
	public void setRowId(String rowId)
	{
		this.is_rowId = rowId;
	}

	/**
	 * Retorna Objeto o variable de valor row id.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getRowId()
	{
		return is_rowId;
	}

	/**
	 * Modifica el valor de Tabla.
	 *
	 * @param tabla asigna el valor a la propiedad
	 */
	public void setTabla(String tabla)
	{
		this.is_tabla = tabla;
	}

	/**
	 * Retorna Objeto o variable de valor tabla.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getTabla()
	{
		return is_tabla;
	}
}
