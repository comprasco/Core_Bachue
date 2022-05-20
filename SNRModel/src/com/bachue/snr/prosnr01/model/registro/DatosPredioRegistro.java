package com.bachue.snr.prosnr01.model.registro;

import com.bachue.snr.prosnr01.model.sdb.acc.DatosAntSistema;
import com.bachue.snr.prosnr01.model.sdb.acc.MatriculaSegregacion;
import com.bachue.snr.prosnr01.model.sdb.acc.SolicitudMatricula;
import com.bachue.snr.prosnr01.model.sdb.acc.SolicitudMatriculaActo;

import java.io.Serializable;

import java.util.Collection;
import java.util.LinkedList;



/**
 * Clase que contiene todos las propiedades DatosPredioRegistro.
 *
 * @author mblanco
 */
public class DatosPredioRegistro implements Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 6648922629674452178L;

	/** Propiedad icdas datos ant sistema agregados. */
	private Collection<DatosAntSistema> icdas_datosAntSistemaAgregados;

	/** Propiedad icms matriculas segregacion. */
	private Collection<MatriculaSegregacion> icms_matriculasSegregacion;

	/** Propiedad ics id actos. */
	private Collection<String> ics_idActos;

	/** Propiedad icsm datos bandeja predios. */
	private Collection<SolicitudMatricula> icsm_datosBandejaPredios;

	/** Propiedad icsma datos bandeja actos. */
	private Collection<SolicitudMatriculaActo> icsma_datosBandejaActos;

	/** Propiedad ims matricula segregacion. */
	private MatriculaSegregacion ims_matriculaSegregacion;

	/** Propiedad is tipo requiere matricula. */
	private String is_tipoRequiereMatricula;

	/** Propiedad ib es tipo acto baldio. */
	private boolean ib_esTipoActoBaldio;

	/**
	 * Modifica el valor de datos ant sistema agregados.
	 *
	 * @param acdas_ccdas asigna el valor a la propiedad datos ant sistema agregados
	 */
	public void setDatosAntSistemaAgregados(Collection<DatosAntSistema> acdas_ccdas)
	{
		icdas_datosAntSistemaAgregados = acdas_ccdas;
	}

	/**
	 * Retorna el valor de datos ant sistema agregados.
	 *
	 * @return el valor de datos ant sistema agregados
	 */
	public Collection<DatosAntSistema> getDatosAntSistemaAgregados()
	{
		return icdas_datosAntSistemaAgregados;
	}

	/**
	 * Modifica el valor de datos bandeja actos.
	 *
	 * @param acsma_csma asigna el valor a la propiedad datos bandeja actos
	 */
	public void setDatosBandejaActos(Collection<SolicitudMatriculaActo> acsma_csma)
	{
		icsma_datosBandejaActos = acsma_csma;
	}

	/**
	 * Retorna el valor de datos bandeja actos.
	 *
	 * @return el valor de datos bandeja actos
	 */
	public Collection<SolicitudMatriculaActo> getDatosBandejaActos()
	{
		return icsma_datosBandejaActos;
	}

	/**
	 * Modifica el valor de datos bandeja predios.
	 *
	 * @param acsm_csm asigna el valor a la propiedad datos bandeja predios
	 */
	public void setDatosBandejaPredios(Collection<SolicitudMatricula> acsm_csm)
	{
		icsm_datosBandejaPredios = acsm_csm;
	}

	/**
	 * Retorna el valor de datos bandeja predios.
	 *
	 * @return el valor de datos bandeja predios
	 */
	public Collection<SolicitudMatricula> getDatosBandejaPredios()
	{
		return icsm_datosBandejaPredios;
	}

	/**
	 * Modifica el valor de es tipo acto baldio.
	 *
	 * @param ab_b asigna el valor a la propiedad es tipo acto baldio
	 */
	public void setEsTipoActoBaldio(boolean ab_b)
	{
		ib_esTipoActoBaldio = ab_b;
	}

	/**
	 * Valida la propiedad es tipo acto baldio.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en es tipo acto baldio
	 */
	public boolean isEsTipoActoBaldio()
	{
		return ib_esTipoActoBaldio;
	}

	/**
	 * Modifica el valor de id actos.
	 *
	 * @param acs_cs asigna el valor a la propiedad id actos
	 */
	public void setIdActos(Collection<String> acs_cs)
	{
		ics_idActos = acs_cs;
	}

	/**
	 * Retorna el valor de id actos.
	 *
	 * @return el valor de id actos
	 */
	public Collection<String> getIdActos()
	{
		if(ics_idActos == null)
			ics_idActos = new LinkedList<String>();

		return ics_idActos;
	}

	/**
	 * Modifica el valor de matricula segregacion.
	 *
	 * @param ams_ms asigna el valor a la propiedad matricula segregacion
	 */
	public void setMatriculaSegregacion(MatriculaSegregacion ams_ms)
	{
		ims_matriculaSegregacion = ams_ms;
	}

	/**
	 * Retorna el valor de matricula segregacion.
	 *
	 * @return el valor de matricula segregacion
	 */
	public MatriculaSegregacion getMatriculaSegregacion()
	{
		return ims_matriculaSegregacion;
	}

	/**
	 * Modifica el valor de matriculas segregacion.
	 *
	 * @param acms_cms asigna el valor a la propiedad matriculas segregacion
	 */
	public void setMatriculasSegregacion(Collection<MatriculaSegregacion> acms_cms)
	{
		icms_matriculasSegregacion = acms_cms;
	}

	/**
	 * Retorna el valor de matriculas segregacion.
	 *
	 * @return el valor de matriculas segregacion
	 */
	public Collection<MatriculaSegregacion> getMatriculasSegregacion()
	{
		return icms_matriculasSegregacion;
	}

	/**
	 * Modifica el valor de tipo requiere matricula.
	 *
	 * @param as_s asigna el valor a la propiedad tipo requiere matricula
	 */
	public void setTipoRequiereMatricula(String as_s)
	{
		is_tipoRequiereMatricula = as_s;
	}

	/**
	 * Retorna el valor de tipo requiere matricula.
	 *
	 * @return el valor de tipo requiere matricula
	 */
	public String getTipoRequiereMatricula()
	{
		return is_tipoRequiereMatricula;
	}
}
