/**
 * SBB_CB_CertificadoTradicionSOAP12BindingImpl.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */
package co.gov.supernotariado.www.services.bachue.cb.certificadotradicionpdf.v1;

import co.gov.supernotariado.www.services.bachue.cb.certificadotradicionpdf.obtenerPDF.v1.TipoEntradaCertificadoTradicionPDF;
import co.gov.supernotariado.www.services.bachue.cb.certificadotradicionpdf.obtenerPDF.v1.TipoSalidaCertificadoTradicionPDF;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.logging.LoggerHandler;

import com.bachue.snr.prosnr01.common.constants.ProyectosCommon;

import com.bachue.snr.prosnr04.web.services.BaseServices;

import java.rmi.RemoteException;


/**
 * Clase que contiene todos las propiedades SBB_CB_CertificadoTradicionSOAP12BindingImpl.
 *
 * @author  Carlos Calderón
 * Fecha de Creacion: 12/03/2020
 */
public class SBB_CB_CertificadoTradicionSOAP12BindingImpl extends BaseServices
    implements SBB_CB_CertificadoTradicion_PortType
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = -6773385654349874369L;

	/** Constante clh_LOGGER. */
	private static final LoggerHandler clh_LOGGER = (LoggerHandler)com.bachue.snr.prosnr01.common.utils.LoggingHelper
			.getLogger(SBB_CB_CertificadoTradicionSOAP12BindingImpl.class, ProyectosCommon.COEXISTENCIA_14);

	/**
	 * Obtener un PDF del Certificado de Tradición.
	 *
	 * @param atectpdf_entrada de atectpdf entrada
	 * @return el valor de tipo salida certificado tradicion PDF
	 * @throws RemoteException cuando se produce algun error en el proceso
	 */
	public TipoSalidaCertificadoTradicionPDF obtenerPDF(TipoEntradaCertificadoTradicionPDF atectpdf_entrada)
	    throws RemoteException
	{
		TipoSalidaCertificadoTradicionPDF ltsctpdf_salida;

		ltsctpdf_salida = new TipoSalidaCertificadoTradicionPDF();

		try
		{
			ltsctpdf_salida = getCertificadoTradicionPDFRemote()
					                  .obtenerPDF(
					    atectpdf_entrada, getUserId(), getLocalIpAddress(), getRemoteIpAddress()
					);
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("obtenerPDF", lb2be_e);
		}
		catch(Exception le_e)
		{
			clh_LOGGER.error("obtenerPDF", le_e);
		}

		return ltsctpdf_salida;
	}
}
