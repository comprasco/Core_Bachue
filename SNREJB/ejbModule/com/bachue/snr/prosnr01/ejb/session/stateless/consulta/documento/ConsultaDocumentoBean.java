package com.bachue.snr.prosnr01.ejb.session.stateless.consulta.documento;

import com.b2bsg.common.exception.B2BException;

import com.bachue.snr.prosnr01.business.consulta.documento.ConsultaDocumentoBusiness;

import com.bachue.snr.prosnr01.ejb.session.stateless.Logger;
import com.bachue.snr.prosnr01.ejb.session.stateless.consulta.documento.ConsultaDocumentoRemote;

import com.bachue.snr.prosnr01.model.consulta.documento.ConsultaDocumento;
import com.bachue.snr.prosnr01.model.consulta.trazabilidad.Trazabilidad;

import org.perf4j.StopWatch;

import java.util.Collection;


/**
 * Clase que contiene todos las propiedades ConsultaDocumentoBean.
 *
 * @author Julian Vaca
 */
@javax.ejb.Stateless(name = "consultaDocumento", mappedName = "consultaDocumentoMappedName")
@javax.ejb.TransactionManagement(javax.ejb.TransactionManagementType.BEAN)
public class ConsultaDocumentoBean implements ConsultaDocumentoRemote
{
	/** Propiedad icd business. */
	private ConsultaDocumentoBusiness icd_business;

	/** {@inheritdoc} */
	public Collection<ConsultaDocumento> findConsultaDocumento(ConsultaDocumento acd_parametros)
	    throws B2BException
	{
		Collection<ConsultaDocumento> lccd_consultaDocumento;

		StopWatch                     lsw_watch;
		lsw_watch     = Logger.getNewStopWatch();

		lccd_consultaDocumento = getConsultaDocumentoBusiness().findConsultaDocumento(acd_parametros);

		Logger.log(
		    lsw_watch, "ConsultaDocumentoBean", "findConsultaDocumento", null, null, null, lccd_consultaDocumento
		);

		return lccd_consultaDocumento;
	}

	/** {@inheritdoc} */
	public Collection<Trazabilidad> findTrazabilidad(Trazabilidad at_parametros)
	    throws B2BException
	{
		StopWatch                lsw_watch;
		Collection<Trazabilidad> lct_datos;

		lsw_watch     = Logger.getNewStopWatch();

		lct_datos = getConsultaDocumentoBusiness().findTrazabilidad(at_parametros);

		Logger.log(lsw_watch, "ConsultaTrazabilidadBean", "findTrazabilidad", null, null, null, lct_datos);

		return lct_datos;
	}

	/**
	 * Retorna el valor de consulta documento business.
	 *
	 * @return el valor de consulta documento business
	 */
	private ConsultaDocumentoBusiness getConsultaDocumentoBusiness()
	{
		if(icd_business == null)
			icd_business = new ConsultaDocumentoBusiness();

		return icd_business;
	}
}
