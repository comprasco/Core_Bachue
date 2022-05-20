package com.bachue.snr.prosnr01.model.antiguoSistema;

import com.bachue.snr.prosnr01.model.auditoria.Auditoria;
import com.bachue.snr.prosnr01.model.sdb.acc.Solicitud;
import com.bachue.snr.prosnr01.model.sdb.bng.AnotacionPredio;
import com.bachue.snr.prosnr01.model.sdb.bng.Documento;

import java.io.Serializable;

import java.util.Collection;



/**
 * Class que contiene todos las propiedades DataConsultaDatosDocumento.
 *
 * @author Julian Vaca
 */
public class DataConsultaDatosDocumento extends Auditoria implements Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = -8870703469283754313L;

	/** Propiedad iap anotacion predio. */
	private AnotacionPredio iap_anotacionPredio;

	/** Propiedad icdcdc data consulta por criterio. */
	private Collection<DataConsultaPorCriterio> icdcdc_dataConsultaPorCriterio;

	/** Propiedad id documento. */
	private Documento id_documento;

	/** Propiedad il version. */
	private Long il_version;

	/** Propiedad is solicitud. */
	private Solicitud is_solicitud;

	/**
	 * Modifica el valor de anotacion predio.
	 *
	 * @param aap_ap asigna el valor a la propiedad anotacion predio
	 */
	public void setAnotacionPredio(AnotacionPredio aap_ap)
	{
		iap_anotacionPredio = aap_ap;
	}

	/**
	 * Retorna el valor de anotacion predio.
	 *
	 * @return el valor de anotacion predio
	 */
	public AnotacionPredio getAnotacionPredio()
	{
		return iap_anotacionPredio;
	}

	/**
	 * Modifica el valor de data consulta por criterio.
	 *
	 * @param ac_dcdc asigna el valor a la propiedad data consulta por criterio
	 */
	public void setDataConsultaPorCriterio(Collection<DataConsultaPorCriterio> ac_dcdc)
	{
		icdcdc_dataConsultaPorCriterio = ac_dcdc;
	}

	/**
	 * Retorna el valor de data consulta por criterio.
	 *
	 * @return el valor de data consulta por criterio
	 */
	public Collection<DataConsultaPorCriterio> getDataConsultaPorCriterio()
	{
		return icdcdc_dataConsultaPorCriterio;
	}

	/**
	 * Modifica el valor de documento.
	 *
	 * @param ad_d asigna el valor a la propiedad documento
	 */
	public void setDocumento(Documento ad_d)
	{
		id_documento = ad_d;
	}

	/**
	 * Retorna el valor de documento.
	 *
	 * @return el valor de documento
	 */
	public Documento getDocumento()
	{
		return id_documento;
	}

	/**
	 * Modifica el valor de solicitud.
	 *
	 * @param as_s asigna el valor a la propiedad solicitud
	 */
	public void setSolicitud(Solicitud as_s)
	{
		is_solicitud = as_s;
	}

	/**
	 * Retorna el valor de solicitud.
	 *
	 * @return el valor de solicitud
	 */
	public Solicitud getSolicitud()
	{
		return is_solicitud;
	}

	/**
	 * Modifica el valor de version.
	 *
	 * @param al_l asigna el valor a la propiedad version
	 */
	public void setVersion(Long al_l)
	{
		il_version = al_l;
	}

	/**
	 * Retorna el valor de version.
	 *
	 * @return el valor de version
	 */
	public Long getVersion()
	{
		return il_version;
	}
}
