package com.bachue.snr.prosnr14.ejb.session.stateless.coexistencia;

import co.gov.supernotariado.www.services.bachue.cb.certificadotradicionpdf.obtenerPDF.v1.TipoEntradaCertificadoTradicionPDF;
import co.gov.supernotariado.www.services.bachue.cb.certificadotradicionpdf.obtenerPDF.v1.TipoSalidaCertificadoTradicionPDF;

import com.b2bsg.common.exception.B2BException;

import com.bachue.snr.prosnr01.ejb.session.stateless.Logger;

import com.bachue.snr.prosnr14.business.coexistencia.CertificadoTradicionPDFBusiness;

import org.perf4j.StopWatch;


/**
 * Clase que contiene todos las propiedades CertificadoTradicionPDFBean.
 *
 * @author  Carlos Calderón
 * Fecha de Creacion: 12/03/2020
 */
@javax.ejb.Stateless(name = "certificadoTradicionPDF", mappedName = "certificadoTradicionPDFMappedName")
@javax.ejb.TransactionManagement(javax.ejb.TransactionManagementType.BEAN)
public class CertificadoTradicionPDFBean implements CertificadoTradicionPDFRemote
{
	/** Propiedad ictpdfb business. */
	private CertificadoTradicionPDFBusiness ictpdfb_business;

	/**
	 * Retorna Objeto o variable de valor certificado tradicion PDF business.
	 *
	 * @return el valor de certificado tradicion PDF business
	 */
	public CertificadoTradicionPDFBusiness getCertificadoTradicionPDFBusiness()
	{
		if(ictpdfb_business == null)
			ictpdfb_business = new CertificadoTradicionPDFBusiness();

		return ictpdfb_business;
	}

	/** {@inheritdoc} */
	public TipoSalidaCertificadoTradicionPDF obtenerPDF(
	    TipoEntradaCertificadoTradicionPDF atectpdf_entrada, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch                         lsw_watch;
		TipoSalidaCertificadoTradicionPDF ltsctpdf_return;

		lsw_watch           = Logger.getNewStopWatch();
		ltsctpdf_return     = getCertificadoTradicionPDFBusiness().obtenerPDF(atectpdf_entrada, as_userId, as_remoteIp);

		Logger.log(lsw_watch, "CertificadoTradicionPDFBean", "obtenerPDF", as_userId, as_localIp, as_remoteIp, null);

		return ltsctpdf_return;
	}
}
