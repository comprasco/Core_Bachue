package com.bachue.snr.prosnr10.model.catastro;

import java.io.Serializable;



/**
 * Clase que contiene todos las propiedades IntervinienteCatastro.
 *
 * @author  Julián David Vaca Rodriguez
 * Fecha de Creacion: 15/03/2020
 */
public class PropietarioCatastro extends IntervinienteCatastro implements Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 2273732196792162285L;

	/** Propiedad is DRR. */
	private String is_DRR;

	/** Propiedad porcentaje participacion. */
	private String is_porcentajeParticipacion;

	/** Propiedad is propietario. */
	private String is_propietario;

	/**
	 * Instancia un nuevo objeto interviniente catastro.
	 */
	public PropietarioCatastro()
	{
	}

	/**
	 * Instancia un nuevo objeto propietario catastro.
	 *
	 * @param as_tipoPersona de as tipo persona
	 * @param as_tipoDocumentoPersona de as tipo documento persona
	 * @param as_numDocumentoPersona de as num documento persona
	 * @param as_primerNombre de as primer nombre
	 * @param as_segundoNombre de as segundo nombre
	 * @param as_primerApellido de as primer apellido
	 * @param as_segundoApellido de as segundo apellido
	 * @param as_razonSocial de as razon social
	 * @param as_rolInterviniente de as rol interviniente
	 * @param as_tipoParteInteresada de as tipo parte interesada
	 * @param as_drr de as drr
	 * @param as_porcentajeParticipacion de as porcentaje participacion
	 * @param as_idNaturalGenero de as id natural genero
	 * @param as_propietario de as propietario
	 */
	public PropietarioCatastro(
	    String as_tipoPersona, String as_tipoDocumentoPersona, String as_numDocumentoPersona, String as_primerNombre,
	    String as_segundoNombre, String as_primerApellido, String as_segundoApellido, String as_razonSocial,
	    String as_rolInterviniente, String as_tipoParteInteresada, String as_drr, String as_porcentajeParticipacion,
	    String as_idNaturalGenero, String as_propietario
	)
	{
		super(
		    as_tipoPersona, as_tipoDocumentoPersona, as_numDocumentoPersona, as_primerNombre, as_segundoNombre,
		    as_primerApellido, as_segundoApellido, as_razonSocial, as_rolInterviniente, as_tipoParteInteresada,
		    as_idNaturalGenero
		);
		is_DRR                         = as_drr;
		is_porcentajeParticipacion     = as_porcentajeParticipacion;
		is_propietario                 = as_propietario;
	}

	/**
	 * Modifica el valor de DRR.
	 *
	 * @param as_s de as s
	 */
	public void setDRR(String as_s)
	{
		is_DRR = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor drr.
	 *
	 * @return el valor de drr
	 */
	public String getDRR()
	{
		return is_DRR;
	}

	/**
	 * Modifica el valor de PorcentajeParticipacion.
	 *
	 * @param as_s de as s
	 */
	public void setPorcentajeParticipacion(String as_s)
	{
		is_porcentajeParticipacion = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor porcentaje participacion.
	 *
	 * @return el valor de porcentaje participacion
	 */
	public String getPorcentajeParticipacion()
	{
		return is_porcentajeParticipacion;
	}

	/**
	 * Retorna Objeto o variable de valor propietario.
	 *
	 * @return Retorna el valor de la propiedad propietario
	 */
	public String getPropietario()
	{
		return is_propietario;
	}

	/**
	 * Modifica el valor de Propietario.
	 *
	 * @param as_s de as s
	 */
	public void setPropietario(String as_s)
	{
		is_propietario = as_s;
	}
}
