package com.bachue.snr.prosnr14.business.coexistencia;

import co.gov.supernotariado.www.services.bachue.cb.certificadotradicionpdf.obtenerPDF.v1.TipoEntradaCertificadoTradicionPDF;
import co.gov.supernotariado.www.services.bachue.cb.certificadotradicionpdf.obtenerPDF.v1.TipoEntradaCertificadoTradicionPDFTipoPDF;
import co.gov.supernotariado.www.services.bachue.cb.certificadotradicionpdf.obtenerPDF.v1.TipoSalidaCertificadoTradicionPDF;

import com.b2bsg.common.dataAccess2.DAOManager;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.logging.LoggerHandler;

import com.b2bsg.common.util.NumericUtils;
import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.common.constants.CodigoMensajeCommon;
import com.bachue.snr.prosnr01.common.constants.IdentificadoresCommon;
import com.bachue.snr.prosnr01.common.constants.ProyectosCommon;

import com.bachue.snr.prosnr01.dao.DaoManagerFactory;

import com.bachue.snr.prosnr01.model.predio.Predio;
import com.bachue.snr.prosnr01.model.sdb.acc.SolicitudMatricula;

import com.bachue.snr.prosnr14.common.constants.ErrorKeys;

import com.bachue.snr.prosnr14.model.coexistencia.CoexistenciaResponse;

import java.math.BigInteger;

import java.util.Map;


/**
 * Clase que contiene todos las propiedades CertificadoTradicionPDFBusiness.
 *
 * @author  Carlos Calderón
 * Fecha de Creacion: 13/03/2020
 */
public class CertificadoTradicionPDFBusiness extends CoexistenciaBusiness
{
	/** Constante clh_LOGGER. */
	private static final LoggerHandler clh_LOGGER = (LoggerHandler)com.bachue.snr.prosnr01.common.utils.LoggingHelper
			.getLogger(CertificadoTradicionPDFBusiness.class, ProyectosCommon.COEXISTENCIA_14);

	/**
	 * Obtener PDF.
	 *
	 * @param atectpdf_entrada de atectpdf entrada
	 * @param as_userId de as user id
	 * @param as_remoteIp de as remote ip
	 * @return el valor de tipo salida certificado tradicion PDF
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public synchronized TipoSalidaCertificadoTradicionPDF obtenerPDF(
	    TipoEntradaCertificadoTradicionPDF atectpdf_entrada, String as_userId, String as_remoteIp
	)
	    throws B2BException
	{
		TipoSalidaCertificadoTradicionPDF ltsctpdf_salida;
		DAOManager                        ldm_manager;
		BigInteger                        lbi_codigoMensaje;
		String                            ls_descripcionMensaje;

		ltsctpdf_salida           = null;
		ldm_manager               = DaoManagerFactory.getDAOManager();
		lbi_codigoMensaje         = BigInteger.valueOf(CodigoMensajeCommon.CODIGO_200);
		ls_descripcionMensaje     = null;

		try
		{
			if(atectpdf_entrada == null)
				throw new B2BException(addErrorCX(ErrorKeys.ERROR_OPERACION));

			Predio lp_predio;

			lp_predio = new Predio(atectpdf_entrada);

			if(
			    validarTipoIdentificacionPredio(lp_predio, ProyectosCommon.COEXISTENCIA_14)
				    && validarNumeroIdentificacionPredio(lp_predio, ProyectosCommon.COEXISTENCIA_14)
			)
			{
				CoexistenciaResponse lcr_response;

				lcr_response = new CoexistenciaResponse(lp_predio);

				filtrarConsulta(
				    lcr_response, com.bachue.snr.prosnr14.common.constants.IdentificadoresCommon.VALIDACION_UNICA,
				    ldm_manager
				);

				{
					TipoEntradaCertificadoTradicionPDFTipoPDF ltectpdftp_tipoPDF;

					ltectpdftp_tipoPDF = atectpdf_entrada.getTipoPDF();

					if(ltectpdftp_tipoPDF != null)
					{
						String ls_tipoPDF;

						ls_tipoPDF = ltectpdftp_tipoPDF.getValue();

						if(StringUtils.isValidString(ls_tipoPDF))
						{
							if(
							    !ls_tipoPDF.equals(TipoEntradaCertificadoTradicionPDFTipoPDF._normal)
								    && !ls_tipoPDF.equals(
								        TipoEntradaCertificadoTradicionPDFTipoPDF._PDFConsultaJuridica
								    )
							)
								throw new B2BException(addErrorCX(ErrorKeys.ERROR_TIPO_PDF));
						}
						else
							throw new B2BException(addErrorCX(ErrorKeys.ERROR_TIPO_PDF));
					}
					else
						throw new B2BException(addErrorCX(ErrorKeys.ERROR_TIPO_PDF));
				}

				{
					SolicitudMatricula lsm_solicitudMatricula;
					Long               ll_idMatricula;

					ll_idMatricula             = lp_predio.getIdMatricula();
					lsm_solicitudMatricula     = new SolicitudMatricula();

					lsm_solicitudMatricula.setIdCirculo(lp_predio.getIdCirculo());

					if(NumericUtils.isValidLong(ll_idMatricula))
						lsm_solicitudMatricula.setIdMatricula(NumericUtils.getLong(ll_idMatricula));

					{
						byte[] lb_certificadoTradicionLibertad;

						lb_certificadoTradicionLibertad = generarCertificadoTradicionLibertad(
							    lsm_solicitudMatricula, IdentificadoresCommon.OPERACION_CERTIFICADO_TRADICION_PDF, true,
							    ldm_manager, as_userId, as_remoteIp, true
							);

						if(lb_certificadoTradicionLibertad != null)
							ltsctpdf_salida = new TipoSalidaCertificadoTradicionPDF(
								    lb_certificadoTradicionLibertad, lbi_codigoMensaje, "OK"
								);
					}
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();
			clh_LOGGER.error("obtenerPDF", lb2be_e);

			Map<String, String> lmss_codigoMensaje;

			lmss_codigoMensaje     = obtenerErrorServicios(lb2be_e.getMessageKey());

			lbi_codigoMensaje         = NumericUtils.getBigInteger(
				    lmss_codigoMensaje.get(IdentificadoresCommon.CODIGO_MENSAJE)
				);
			ls_descripcionMensaje     = lmss_codigoMensaje.get(IdentificadoresCommon.DESCRIPCION_MENSAJE);

			ltsctpdf_salida = null;
		}
		catch(Exception le_e)
		{
			ldm_manager.setRollbackOnly();
			clh_LOGGER.error("obtenerPDF", le_e);

			lbi_codigoMensaje         = BigInteger.valueOf(CodigoMensajeCommon.CODIGO_500);
			ls_descripcionMensaje     = le_e.getMessage();

			ltsctpdf_salida = null;
		}
		finally
		{
			ldm_manager.close();
		}

		if(ltsctpdf_salida == null)
			ltsctpdf_salida = new TipoSalidaCertificadoTradicionPDF(null, lbi_codigoMensaje, ls_descripcionMensaje);

		return ltsctpdf_salida;
	}
}
