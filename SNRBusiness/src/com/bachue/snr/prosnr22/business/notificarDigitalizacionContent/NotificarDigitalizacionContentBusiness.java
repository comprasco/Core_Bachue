package com.bachue.snr.prosnr22.business.notificarDigitalizacionContent;

import co.gov.supernotariado.www.schemas.bachue.co.notificarDigitalizacionContent.notificarDigitalizacionContent.v1.TipoEntradaNotificarDigitalizacionContent;
import co.gov.supernotariado.www.schemas.bachue.co.notificarDigitalizacionContent.notificarDigitalizacionContent.v1.TipoSalidaNotificarDigitalizacionContent;

import com.b2bsg.common.dataAccess2.DAOManager;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.logging.LoggerHandler;

import com.b2bsg.common.util.NumericUtils;
import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.business.BaseBusiness;

import com.bachue.snr.prosnr01.common.constants.EstadoCommon;
import com.bachue.snr.prosnr01.common.constants.ProyectosCommon;
import com.bachue.snr.prosnr01.common.constants.SistemaOrigenCommon;

import com.bachue.snr.prosnr01.dao.DaoCreator;
import com.bachue.snr.prosnr01.dao.DaoManagerFactory;
import com.bachue.snr.prosnr01.dao.acc.AccCompletitudDocumentalDAO;

import com.bachue.snr.prosnr01.model.sdb.acc.AccCompletitudDocumental;
import com.bachue.snr.prosnr01.model.sdb.acc.Solicitud;
import com.bachue.snr.prosnr01.model.sdb.acc.Turno;
import com.bachue.snr.prosnr01.model.sdb.pgn.TipoDocumental;

import com.bachue.snr.prosnr22.common.constants.CodigoMensajeCommon;
import com.bachue.snr.prosnr22.common.constants.ErrorKeys;
import com.bachue.snr.prosnr22.common.constants.IdentificadoresCommon;
import com.bachue.snr.prosnr22.common.constants.MessagesKeys;

import java.math.BigInteger;

import java.util.Date;
import java.util.Map;


/**
 * Clase de lógica de negocio para el servicio Control Digitalización.
 * @author hcastaneda
 *
 */
public class NotificarDigitalizacionContentBusiness extends BaseBusiness
{
	private static final LoggerHandler clh_LOGGER = (LoggerHandler)com.bachue.snr.prosnr01.common.utils.LoggingHelper
			.getLogger(
			    NotificarDigitalizacionContentBusiness.class, ProyectosCommon.NOTIFICAR_DIGITALIZACION_CONTENT_22
			);

	/**
	 * Método encargado de notificar la digitalización de los documentos de un completitud documental.
	 *
	 * @param atendc_request Argumento de tipo <code>TipoEntradaNotificarDigitalizacionContent</code>
	 * que contiene los criterios necesarios para realizar la notificación de digitalización.
	 * @param as_userIdConstant Argumento de tipo <code>String</code> que contiene el usuario que realiza la petición.
	 * @param as_remoteIp Argumento de tipo <code>String</code> que contiene la ip remota desde donde se realiza la petición.
	 * @return Objeto de tipo <code>TipoSalidaNotificarDigitalizacionContent</code> que contiene el código y la descripción de la respuesta de la operación.
	 * @throws B2BException
	 */
	public synchronized TipoSalidaNotificarDigitalizacionContent notificarDigitalizacionContent(
	    TipoEntradaNotificarDigitalizacionContent atendc_request, String as_userIdConstant, String as_remoteIp
	)
	    throws B2BException
	{
		TipoSalidaNotificarDigitalizacionContent ltsnd_tipoSalidaNotificarDigitalizacionC;
		String                                   ls_descripcionMensaje;
		BigInteger                               lbi_codigoMensaje;
		DAOManager                               ldm_manager;

		ltsnd_tipoSalidaNotificarDigitalizacionC     = new TipoSalidaNotificarDigitalizacionContent();
		ls_descripcionMensaje                        = addMessageNDC(MessagesKeys.OK, false);
		lbi_codigoMensaje                            = BigInteger.valueOf(CodigoMensajeCommon.CODIGO_200);
		ldm_manager                                  = DaoManagerFactory.getDAOManager();

		try
		{
			if(atendc_request != null)
			{
				String ls_docType;

				ls_docType = null;

				{
					String ls_sistemaOrigen;

					ls_sistemaOrigen = atendc_request.getSistemaOrigen();

					if(
					    !StringUtils.isValidString(ls_sistemaOrigen)
						    || !ls_sistemaOrigen.equalsIgnoreCase(SistemaOrigenCommon.CORE)
					)
						throw new B2BException(ErrorKeys.ERROR_SISTEMA_ORIGEN_NO_PERMITIDO);
				}

				{
					String ls_nir;

					ls_nir = atendc_request.getIdentificadorTramite();

					if(!StringUtils.isValidString(ls_nir))
						throw new B2BException(ErrorKeys.ERROR_IDENTIFICADOR_TRAMITE_NO_VALIDO);

					{
						Solicitud ls_solicitud;

						ls_solicitud = DaoCreator.getSolicitudDAO(ldm_manager).findByNir(ls_nir);

						if(ls_solicitud == null)
							throw new B2BException(ErrorKeys.ERROR_NIR_NO_ENCONTRADO);
					}
				}

				{
					String ls_estado;

					ls_estado = atendc_request.getEstado();

					if(
					    !StringUtils.isValidString(ls_estado)
						    || (!ls_estado.equalsIgnoreCase(EstadoCommon.DIGITALIZADO))
					)
						throw new B2BException(ErrorKeys.ERROR_ESTADO_NO_VALIDO);
				}

				{
					String ls_docName;

					ls_docName = atendc_request.getDocName();

					if(!StringUtils.isValidString(ls_docName))
						throw new B2BException(ErrorKeys.ERROR_DOC_NAME_NO_VALIDO);

					{
						int li_tamano;

						li_tamano = 50;

						if(ls_docName.length() > li_tamano)
						{
							Object[] loa_object;

							loa_object     = new String[1];

							loa_object[0] = StringUtils.getString(li_tamano);

							throw new B2BException(addErrorNDC(ErrorKeys.ERROR_DOC_NAME_SUPERA_TAMANO, loa_object));
						}
					}
				}

				{
					BigInteger lbi_did;

					lbi_did = atendc_request.getDID();

					if(!NumericUtils.isValidBigInteger(lbi_did))
						throw new B2BException(ErrorKeys.ERROR_DID_NO_VALIDO);

					{
						String ls_did;
						int    li_tamano;

						ls_did        = StringUtils.getString(lbi_did);
						li_tamano     = 50;

						if(!StringUtils.isValidString(ls_did) || (ls_did.length() > li_tamano))
						{
							Object[] loa_object;

							loa_object     = new String[1];

							loa_object[0] = StringUtils.getString(li_tamano);

							throw new B2BException(addErrorNDC(ErrorKeys.ERROR_DID_SUPERA_TAMANO, loa_object));
						}
					}
				}

				{
					String ls_tipoDocumental;

					ls_tipoDocumental = atendc_request.getDDocType();

					if(!StringUtils.isValidString(ls_tipoDocumental))
						throw new B2BException(ErrorKeys.ERROR_TIPO_DOCUMENAL_NO_VALIDO);

					{
						TipoDocumental ltd_tipoDocumental;

						ltd_tipoDocumental = DaoCreator.getTipoDocumentalDAO(ldm_manager)
								                           .findByTipologiasBachue(ls_tipoDocumental);

						if(ltd_tipoDocumental == null)
							throw new B2BException(ErrorKeys.ERROR_TIPO_DOCUMENTAL_NO_ENCONTRADO);

						ls_docType = ltd_tipoDocumental.getIdTipoDocumental();
					}
				}

				{
					Date ld_fechaEnviado;

					ld_fechaEnviado = atendc_request.getFechaEnviado();

					if(ld_fechaEnviado == null)
						throw new B2BException(ErrorKeys.ERROR_FECHA_ENVIADO_NO_VALIDO);
				}

				{
					String ls_idTurno;

					ls_idTurno = atendc_request.getIdTurno();

					if(StringUtils.isValidString(ls_idTurno))
					{
						Turno lt_turno;

						lt_turno = DaoCreator.getTurnoDAO(ldm_manager).findById(ls_idTurno);

						if(lt_turno == null)
							throw new B2BException(ErrorKeys.ERROR_TURNO_NO_ENCONTRADO);
					}
				}

				{
					String ls_idCompletitud;

					ls_idCompletitud = atendc_request.getId_completitud();

					if(!StringUtils.isValidString(ls_idCompletitud))
						throw new B2BException(ErrorKeys.ERROR_ID_COMPLETITUD_NO_VALIDO);

					{
						AccCompletitudDocumentalDAO lacdd_DAO;
						AccCompletitudDocumental    lacd_completitudDocumental;

						lacdd_DAO                      = DaoCreator.getAccCompletitudDocumentalDAO(ldm_manager);
						lacd_completitudDocumental     = lacdd_DAO.findById(ls_idCompletitud);

						if(lacd_completitudDocumental == null)
							throw new B2BException(ErrorKeys.ERROR_ID_COMPLETITUD_NO_ENCONTRADO);

						{
							String ls_tipoDocumental;

							ls_docType            = StringUtils.getStringNotNull(ls_docType);
							ls_tipoDocumental     = StringUtils.getStringNotNull(
								    lacd_completitudDocumental.getIdTipoDocumental()
								);

							if(!ls_docType.equalsIgnoreCase(ls_tipoDocumental))
								throw new B2BException(ErrorKeys.ERROR_TIPO_DOCUMENAL_ENVIADO_NO_COINCIDE);
						}

						lacd_completitudDocumental.setDigitalizado(EstadoCommon.S);
						lacd_completitudDocumental.setIdEcm(StringUtils.getString(atendc_request.getDID()));
						lacd_completitudDocumental.setIdNombreDocumento(atendc_request.getDocName());
						lacd_completitudDocumental.setFechaDigitalizacion(atendc_request.getFechaEnviado());
						lacd_completitudDocumental.setIdUsuarioModificacion(as_userIdConstant);
						lacd_completitudDocumental.setIpModificacion(as_remoteIp);

						lacdd_DAO.update(lacd_completitudDocumental);
					}
				}
			}
			else
				throw new B2BException(ErrorKeys.ERROR_PARAMETROS_NO_VALIDOS);
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("notificarDigitalizacionContent", lb2be_e);

			Map<String, String> lmss_codigoMensaje;

			lmss_codigoMensaje     = obtenerErrorServicios(addErrorNDC(lb2be_e.getMessageKey()));

			lbi_codigoMensaje         = NumericUtils.getBigInteger(
				    lmss_codigoMensaje.get(IdentificadoresCommon.CODIGO_MENSAJE)
				);
			ls_descripcionMensaje     = lmss_codigoMensaje.get(IdentificadoresCommon.DESCRIPCION_MENSAJE);
		}
		catch(Exception le_e)
		{
			clh_LOGGER.error("notificarDigitalizacionContent", le_e);

			lbi_codigoMensaje         = BigInteger.valueOf(CodigoMensajeCommon.CODIGO_500);
			ls_descripcionMensaje     = le_e.getMessage();
		}
		finally
		{
			ldm_manager.close();
		}

		ltsnd_tipoSalidaNotificarDigitalizacionC.setCodigoMensaje(lbi_codigoMensaje);
		ltsnd_tipoSalidaNotificarDigitalizacionC.setDescripcionMensaje(ls_descripcionMensaje);

		return ltsnd_tipoSalidaNotificarDigitalizacionC;
	}
}
