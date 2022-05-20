package com.bachue.snr.prosnr01.model.permisosCorrecciones;

import com.bachue.snr.prosnr01.common.constants.CausalCorreccionCommon;

import com.bachue.snr.prosnr01.model.sdb.acc.MatriculaSegregacion;
import com.bachue.snr.prosnr01.model.sdb.acc.SolicitudCamposCorreccion;
import com.bachue.snr.prosnr01.model.sdb.bng.AreaPredio;
import com.bachue.snr.prosnr01.model.sdb.bng.PredioSegregado;

import java.io.Serializable;

import java.util.Collection;
import java.util.Map;



/**
 * Class que contiene todos las propiedades PanelMatriculasSegregacion.
 *
 * @author garias
 */
public class PanelMatriculasSegregacion implements Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 8522431527566806263L;

	/** Constante is_idCausal. */
	public static final String is_idCausal = CausalCorreccionCommon.MATRICULAS_SEGREGADAS;

	/** Propiedad icap matriculas informacion. */
	private Collection<AreaPredio> icap_matriculasInformacion;

	/** Propiedad icms matriculas segregacion. */
	private Collection<MatriculaSegregacion> icms_matriculasSegregacion;

	/** Propiedad icps matriculas. */
	private Collection<PredioSegregado> icps_matriculas;

	/** Propiedad il cantidad. */
	private Long il_cantidad;

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

	/** Propiedad ib agregar existente. */
	private boolean ib_agregarExistente;

	/** Propiedad ib agregar nueva. */
	private boolean ib_agregarNueva;

	/** Propiedad ib matriculas aperturadas. */
	private boolean ib_matriculasAperturadas;

	/** Propiedad ib segregacion masiva. */
	private boolean ib_segregacionMasiva;

	/** Propiedad ib seleccionado. */
	private boolean ib_seleccionado;

	/**
	 * Modifica el valor de agregar.
	 *
	 * @param ab_b asigna el valor a la propiedad agregar
	 */
	public void setAgregarExistente(boolean ab_b)
	{
		ib_agregarExistente = ab_b;
	}

	/**
	 * Valida la propiedad agregar.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en agregar
	 */
	public boolean isAgregarExistente()
	{
		return ib_agregarExistente;
	}

	/**
	 * Modifica el valor de AgregarNueva.
	 *
	 * @param ab_b de ab b
	 */
	public void setAgregarNueva(boolean ab_b)
	{
		ib_agregarNueva = ab_b;

		if(!ab_b)
			il_cantidad = null;
	}

	/**
	 * Valida la propiedad agregar nueva.
	 *
	 * @return Retorna el valor de la propiedad agregarNueva
	 */
	public boolean isAgregarNueva()
	{
		return ib_agregarNueva;
	}

	/**
	 * Modifica el valor de Cantidad.
	 *
	 * @param al_l de al l
	 */
	public void setCantidad(Long al_l)
	{
		il_cantidad = al_l;
	}

	/**
	 * Retorna Objeto o variable de valor cantidad.
	 *
	 * @return Retorna el valor de la propiedad cantidad
	 */
	public Long getCantidad()
	{
		return il_cantidad;
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
	 * Modifica el valor de MatriculasAperturadas.
	 *
	 * @param ab_b de ab b
	 */
	public void setMatriculasAperturadas(boolean ab_b)
	{
		ib_matriculasAperturadas = ab_b;
	}

	/**
	 * Valida la propiedad matriculas aperturadas.
	 *
	 * @return Retorna el valor de la propiedad matriculasAperturadas
	 */
	public boolean isMatriculasAperturadas()
	{
		return ib_matriculasAperturadas;
	}

	/**
	 * Modifica el valor de matriculas informacion.
	 *
	 * @param acap_ap asigna el valor a la propiedad matriculas informacion
	 */
	public void setMatriculasInformacion(Collection<AreaPredio> acap_ap)
	{
		icap_matriculasInformacion = acap_ap;
	}

	/**
	 * Retorna el valor de matriculas informacion.
	 *
	 * @return el valor de matriculas informacion
	 */
	public Collection<AreaPredio> getMatriculasInformacion()
	{
		return icap_matriculasInformacion;
	}

	/**
	 * Modifica el valor de MatriculasSegregacion.
	 *
	 * @param acms_cms de acms cms
	 */
	public void setMatriculasSegregacion(Collection<MatriculaSegregacion> acms_cms)
	{
		icms_matriculasSegregacion = acms_cms;
	}

	/**
	 * Retorna Objeto o variable de valor matriculas segregacion.
	 *
	 * @return Retorna el valor de la propiedad matriculasSegregacion
	 */
	public Collection<MatriculaSegregacion> getMatriculasSegregacion()
	{
		return icms_matriculasSegregacion;
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
	 * Modifica el valor de SegregacionMasiva.
	 *
	 * @param ab_b de ab b
	 */
	public void setSegregacionMasiva(boolean ab_b)
	{
		ib_segregacionMasiva = ab_b;
	}

	/**
	 * Valida la propiedad segregacion masiva.
	 *
	 * @return Retorna el valor de la propiedad segregacionMasiva
	 */
	public boolean isSegregacionMasiva()
	{
		return ib_segregacionMasiva;
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
