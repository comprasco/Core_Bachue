package com.bachue.snr.prosnr01.model.permisosCorrecciones;

import com.bachue.snr.prosnr01.common.constants.CausalCorreccionCommon;

import com.bachue.snr.prosnr01.model.sdb.acc.SolicitudCamposCorreccion;
import com.bachue.snr.prosnr01.model.sdb.bng.PredioSegregado;

import java.io.Serializable;

import java.util.Collection;
import java.util.Map;



/**
 * Class que contiene todos las propiedades PanelMatriculasAbiertas.
 *
 * @author garias
 */
public class PanelMatriculasAbiertas implements Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = -4823948322294409050L;

	/** Constante is_idCausal. */
	public static final String is_idCausal = CausalCorreccionCommon.MATRICULAS_ABIERTAS_CON_BASE_EN_LAS_SIGUIENTES_MATRICULAS;

	/** Propiedad icps matriculas. */
	private Collection<PredioSegregado> icps_matriculas;

	/** Propiedad imsscc checks. */
	private Map<String, SolicitudCamposCorreccion> imsscc_checks;

	/** Propiedad is copiar. */
	private String is_copiar;

	/** Propiedad is copiar seleccionadas. */
	private String is_copiarSeleccionadas;

	/** Propiedad is justificacion. */
	private String is_justificacion;

	/** Propiedad is salvedad. */
	private String is_salvedad;

	/** Propiedad ib agregar. */
	private boolean ib_agregar;

	/** Propiedad ib seleccionado. */
	private boolean ib_seleccionado;

	/**
	 * Modifica el valor de agregar.
	 *
	 * @param ab_b asigna el valor a la propiedad agregar
	 */
	public void setAgregar(boolean ab_b)
	{
		ib_agregar = ab_b;
	}

	/**
	 * Valida la propiedad agregar.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en agregar
	 */
	public boolean isAgregar()
	{
		return ib_agregar;
	}

	/**
	 * Sets the checks.
	 *
	 * @param amsps_mscc de amsps mscc
	 */
	public void setChecks(Map<String, SolicitudCamposCorreccion> amsps_mscc)
	{
		imsscc_checks = amsps_mscc;
	}

	/**
	 * Retorna el valor de checks.
	 *
	 * @return el valor de checks
	 */
	public Map<String, SolicitudCamposCorreccion> getChecks()
	{
		return imsscc_checks;
	}

	/**
	 * Modifica el valor de copiar.
	 *
	 * @param as_s asigna el valor a la propiedad copiar
	 */
	public void setCopiar(String as_s)
	{
		this.is_copiar = as_s;
	}

	/**
	 * Retorna el valor de copiar.
	 *
	 * @return el valor de copiar
	 */
	public String getCopiar()
	{
		return is_copiar;
	}

	/**
	 * Modifica el valor de copiar seleccionadas.
	 *
	 * @param as_s asigna el valor a la propiedad copiar seleccionadas
	 */
	public void setCopiarSeleccionadas(String as_s)
	{
		this.is_copiarSeleccionadas = as_s;
	}

	/**
	 * Retorna el valor de copiar seleccionadas.
	 *
	 * @return el valor de copiar seleccionadas
	 */
	public String getCopiarSeleccionadas()
	{
		return is_copiarSeleccionadas;
	}

	/**
	 * Modifica el valor de justificacion.
	 *
	 * @param as_s asigna el valor a la propiedad justificacion
	 */
	public void setJustificacion(String as_s)
	{
		is_justificacion = as_s;
	}

	/**
	 * Retorna el valor de justificacion.
	 *
	 * @return el valor de justificacion
	 */
	public String getJustificacion()
	{
		return is_justificacion;
	}

	/**
	 * Modifica el valor de matriculas.
	 *
	 * @param acps_cps asigna el valor a la propiedad matriculas
	 */
	public void setMatriculas(Collection<PredioSegregado> acps_cps)
	{
		icps_matriculas = acps_cps;
	}

	/**
	 * Retorna el valor de matriculas.
	 *
	 * @return el valor de matriculas
	 */
	public Collection<PredioSegregado> getMatriculas()
	{
		return icps_matriculas;
	}

	/**
	 * Modifica el valor de salvedad.
	 *
	 * @param as_s asigna el valor a la propiedad salvedad
	 */
	public void setSalvedad(String as_s)
	{
		is_salvedad = as_s;
	}

	/**
	 * Retorna el valor de salvedad.
	 *
	 * @return el valor de salvedad
	 */
	public String getSalvedad()
	{
		return is_salvedad;
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
}
