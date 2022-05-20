package com.bachue.snr.prosnr14.web.services.delegate;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.logging.LoggerHandler;

import com.bachue.snr.prosnr01.common.constants.ProyectosCommon;

import com.bachue.snr.prosnr14.ejb.session.stateless.coexistencia.CertificadoTradicionPDFRemote;

import java.io.Serializable;


/**
 * Clase que contiene todos las propiedades CertificadoTradicionPDFDelegado.
 *
 * @author  Carlos Calderón
 * Fecha de Creacion: 12/03/2020
 */
public class CertificadoTradicionPDFDelegado implements Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = -5189975159269790952L;

	/** Constante JNDI NAME. */
	private static final String JNDI_NAME = "jndi.certificadoTradicionPDF";

	/** Constante clh LOGGER. */
	private static final LoggerHandler clh_LOGGER = (LoggerHandler)com.bachue.snr.prosnr01.common.utils.LoggingHelper
			.getLogger(CertificadoTradicionPDFDelegado.class, ProyectosCommon.COEXISTENCIA_14);

	/** Propiedad ictpdfr remote. */
	private CertificadoTradicionPDFRemote ictpdfr_remote;

	/**
	 * Instancia un nuevo objeto certificado tradicion PDF delegado.
	 */
	public CertificadoTradicionPDFDelegado()
	{
		ictpdfr_remote = null;
	}

	/**
	 * Retorna Objeto o variable de valor remote.
	 *
	 * @return el valor de remote
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public CertificadoTradicionPDFRemote getRemote()
	    throws B2BException
	{
		if(ictpdfr_remote == null)
		{
			try
			{
				ictpdfr_remote = (CertificadoTradicionPDFRemote)com.b2bsg.common.serviceLocator.ServiceLocator.getServiceLocator()
						                                                                                          .getConfigRegisteredObject(
						    JNDI_NAME, false
						);
			}
			catch(Exception le_e)
			{
				clh_LOGGER.error("getRemote", le_e);
				ictpdfr_remote = null;
			}
		}

		return ictpdfr_remote;
	}
}
