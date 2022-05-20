package com.bachue.snr.prosnr18.business.solicitud.copias;

import co.gov.supernotariado.www.schemas.bachue.ee.solicituddecopias.consultarsolicitud.v1.TipoEntradaConsultarSolicitud;
import co.gov.supernotariado.www.schemas.bachue.ee.solicituddecopias.consultarsolicitud.v1.TipoSalidaConsultarSolicitud;
import co.gov.supernotariado.www.schemas.bachue.ee.solicituddecopias.consultarsolicitud.v1.TipoSalidaConsultarSolicitudCodigo;
import co.gov.supernotariado.www.schemas.bachue.ee.solicituddecopias.ingresosolicitud.v1.TipoEntradaIngresoSolicitud;
import co.gov.supernotariado.www.schemas.bachue.ee.solicituddecopias.ingresosolicitud.v1.TipoEntradaIngresoSolicitudListaDocumentosDocumentoSGD;
import co.gov.supernotariado.www.schemas.bachue.ee.solicituddecopias.ingresosolicitud.v1.TipoSalidaIngresoSolicitud;
import co.gov.supernotariado.www.schemas.bachue.ee.solicituddecopias.ingresosolicitud.v1.TipoSalidaIngresoSolicitudCodigo;

import com.b2bsg.common.dataAccess2.DAOManager;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.logging.LoggerHandler;

import com.b2bsg.common.util.CollectionUtils;
import com.b2bsg.common.util.NumericUtils;
import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.business.BaseBusiness;
import com.bachue.snr.prosnr01.business.consulta.SGD.ConsultaSGDBusiness;

import com.bachue.snr.prosnr01.common.constants.EstadoCommon;
import com.bachue.snr.prosnr01.common.constants.MessagesKeys;
import com.bachue.snr.prosnr01.common.constants.ProyectosCommon;
import com.bachue.snr.prosnr01.common.constants.RepositorioCommon;

import com.bachue.snr.prosnr01.dao.DaoCreator;
import com.bachue.snr.prosnr01.dao.DaoManagerFactory;
import com.bachue.snr.prosnr01.dao.copias.SolicitudCopiasDAO;
import com.bachue.snr.prosnr01.dao.pgn.ConstantesDAO;

import com.bachue.snr.prosnr01.model.copias.SolicitudCopias;
import com.bachue.snr.prosnr01.model.integration.OWCC.DocumentoOWCC;
import com.bachue.snr.prosnr01.model.sdb.acc.Liquidacion;
import com.bachue.snr.prosnr01.model.sdb.acc.Solicitud;
import com.bachue.snr.prosnr01.model.sdb.pgn.Constantes;
import com.bachue.snr.prosnr01.model.sdb.pgn.TipoDocumental;
import com.bachue.snr.prosnr01.model.sdb.pgn.TipoEstadoLiquidacion;

import com.bachue.snr.prosnr18.common.constants.ConstanteCommon;
import com.bachue.snr.prosnr18.common.constants.ErrorKeys;

import java.math.BigInteger;

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;


/**
 * Clase que contiene todos las funcionalidades para solicitud de copias
 *
 * @author Gabriel Arias
 */
public class SolicitudCopiasBusiness extends BaseBusiness
{
	/** Constante clh_LOGGER. */
	private static final LoggerHandler clh_LOGGER = (LoggerHandler)com.bachue.snr.prosnr01.common.utils.LoggingHelper
			.getLogger(SolicitudCopiasBusiness.class, ProyectosCommon.SOLICITUD_DE_COPIAS_18);
	private ConsultaSGDBusiness icsgd_business;

	/**
	 * Método encargado de realizar la operación consultarSolicitud.
	 *
	 * @param atecs_entrada Objeto que contiene la información de entrada.
	 * @param as_userId Variable que contiene el id del usuario.
	 * @param as_remoteIp Variable que contiene la ip del usuario.
	 * @return Objeto que contiene la información de salida.
	 * @throws B2BException
	 */
	public synchronized TipoSalidaConsultarSolicitud consultarSolicitud(
	    TipoEntradaConsultarSolicitud atecs_entrada, String as_userId, String as_remoteIp
	)
	    throws B2BException
	{
		DAOManager                         ldm_manager;
		String                             ls_descripcionMensaje;
		TipoSalidaConsultarSolicitudCodigo ltscsc_codigo;
		TipoSalidaConsultarSolicitud       ltscs_return;

		ldm_manager               = DaoManagerFactory.getDAOManager();
		ls_descripcionMensaje     = addMessage(MessagesKeys.OK);
		ltscsc_codigo             = TipoSalidaConsultarSolicitudCodigo.value1;
		ltscs_return              = new TipoSalidaConsultarSolicitud();

		try
		{
			if(atecs_entrada != null)
			{
				String ls_nir;

				ls_nir = atecs_entrada.getNIR();

				if(StringUtils.isValidString(ls_nir))
				{
					Solicitud ls_solicitud;

					ls_solicitud = DaoCreator.getSolicitudDAO(ldm_manager).findByNir(ls_nir);

					if(ls_solicitud != null)
					{
						Liquidacion ll_liquidacion;

						ll_liquidacion = DaoCreator.getAccLiquidacionDAO(ldm_manager)
								                       .findByIdSolicitudOne(ls_solicitud.getIdSolicitud());

						if(ll_liquidacion != null)
						{
							String ls_idTipoEstadoLiquidacion;

							ls_idTipoEstadoLiquidacion = ll_liquidacion.getIdTipoEstadoLiquidacion();

							if(StringUtils.isValidString(ls_idTipoEstadoLiquidacion))
							{
								TipoEstadoLiquidacion ltel_data;

								ltel_data = new TipoEstadoLiquidacion();

								ltel_data.setIdTipoEstadoLiquidacion(ls_idTipoEstadoLiquidacion);

								ltel_data = DaoCreator.getTipoEstadoLiquidacionDAO(ldm_manager).findById(ltel_data);

								if(ltel_data != null)
									ltscs_return.setEstado(ltel_data.getDescripcionEstadoLiquidacion());
							}

							ltscs_return.setValorPago(StringUtils.getString(ll_liquidacion.getValorTotal()));
							ltscs_return.setReferenciaPago(ll_liquidacion.getNumeroReferencia());
							ltscs_return.setFechaLiquidacion(ll_liquidacion.getFechaLiquidacion());
							ltscs_return.setFechaVencimientoPago(ll_liquidacion.getFechaVencimiento());
						}
						else
						{
							Object[] loa_arg;

							loa_arg           = new String[1];
							loa_arg[0]        = ls_nir;
							ltscsc_codigo     = TipoSalidaConsultarSolicitudCodigo.value2;

							throw new B2BException(ErrorKeys.ERROR_LIQUIDACION_DATA);
						}
					}
					else
					{
						Object[] loa_arg;

						loa_arg           = new String[1];
						loa_arg[0]        = ls_nir;
						ltscsc_codigo     = TipoSalidaConsultarSolicitudCodigo.value2;

						throw new B2BException(ErrorKeys.ERROR_SOLICITUD);
					}
				}
				else
				{
					ltscsc_codigo = TipoSalidaConsultarSolicitudCodigo.value2;
					throw new B2BException(ErrorKeys.ERROR_NIR);
				}
			}
			else
			{
				ltscsc_codigo = TipoSalidaConsultarSolicitudCodigo.value2;
				throw new B2BException(ErrorKeys.ERROR_PARAMETROS_NO_VALIDOS);
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			ls_descripcionMensaje = addErrorSDC(lb2be_e.getMessageKey());

			clh_LOGGER.error("consultarSolicitud", lb2be_e);
		}
		finally
		{
			ldm_manager.close();
		}

		ltscs_return.setMensaje(ls_descripcionMensaje);
		ltscs_return.setCodigo(ltscsc_codigo);

		return ltscs_return;
	}

	/**
	 * Método encargado de realizar la operacion ingresoSolicitud.
	 *
	 * @param ateis_data Objeto que contiene la información de entrada.
	 * @param as_userId Variable que contiene el id del usuario.
	 * @param as_remoteIp Variable que contiene la ip del usuario.
	 * @return Objeto que contiene la información de salida.
	 * @throws Exception
	 */
	public synchronized TipoSalidaIngresoSolicitud ingresoSolicitud(
	    TipoEntradaIngresoSolicitud ateis_data, String as_userId, String as_remoteIp
	)
	    throws B2BException
	{
		DAOManager ldm_manager;

		String     ls_identificadorCopiasSE;
		String     ls_descripcionMensaje;
		TipoSalidaIngresoSolicitudCodigo ltsisc_codigo;
		TipoSalidaIngresoSolicitud ltsis_return;

		ldm_manager                  = DaoManagerFactory.getDAOManager();
		ls_identificadorCopiasSE     = "";
		ls_descripcionMensaje        = addMessage(MessagesKeys.OK);
		ltsisc_codigo                = TipoSalidaIngresoSolicitudCodigo.value1;
		ltsis_return                 = new TipoSalidaIngresoSolicitud();

		try
		{
			if(ateis_data != null)
			{
				TipoEntradaIngresoSolicitudListaDocumentosDocumentoSGD[] lteisldda_docs;

				lteisldda_docs = ateis_data.getListaDocumentos();

				if(CollectionUtils.isValidCollection(lteisldda_docs))
				{
					{
						DAOManager ldm_consecutivo;

						ldm_consecutivo = DaoManagerFactory.getDAOManager();

						try
						{
							Constantes    lc_constantes;
							ConstantesDAO lcd_DAO;
							String        ls_constante;
							BigInteger    lbi_consecutivo;
							boolean       lb_insert;

							lcd_DAO           = DaoCreator.getConstantesDAO(ldm_consecutivo);
							ls_constante      = ConstanteCommon.IDENTIFICADOR_COPIAS_SE;
							lc_constantes     = lcd_DAO.findById(ls_constante);

							if(lc_constantes != null)
							{
								lb_insert           = false;
								lbi_consecutivo     = lc_constantes.getEntero();

								lc_constantes.setIdUsuarioModificacion(as_userId);
								lc_constantes.setIpModificacion(as_remoteIp);
							}
							else
							{
								lc_constantes       = new Constantes();
								lb_insert           = true;
								lbi_consecutivo     = null;

								lc_constantes.setIdConstante(ls_constante);
								lc_constantes.setIdUsuarioCreacion(as_userId);
								lc_constantes.setIpCreacion(as_remoteIp);
							}

							if(lbi_consecutivo == null)
								lbi_consecutivo = BigInteger.ZERO;

							lbi_consecutivo              = lbi_consecutivo.add(BigInteger.ONE);
							ls_identificadorCopiasSE     = StringUtils.getString(lbi_consecutivo);

							lc_constantes.setEntero(lbi_consecutivo);

							lcd_DAO.insertOrUpdate(lc_constantes, lb_insert);
						}
						catch(B2BException lb2be_e)
						{
							ldm_consecutivo.setRollbackOnly();

							ls_descripcionMensaje = addErrorSDC(lb2be_e.getMessageKey());

							clh_LOGGER.error("ingresoSolicitud", lb2be_e);
						}
						finally
						{
							ldm_consecutivo.close();
						}
					}

					if(StringUtils.isValidString(ls_identificadorCopiasSE))
					{
						Collection<TipoEntradaIngresoSolicitudListaDocumentosDocumentoSGD> lcteisldd_documentos;
						SolicitudCopiasDAO                                                 lsc_DAO;

						lcteisldd_documentos     = Arrays.asList(lteisldda_docs);
						lsc_DAO                  = DaoCreator.getSolicitudCopiasDAO(ldm_manager);

						if(CollectionUtils.isValidCollection(lcteisldd_documentos))
						{
							for(TipoEntradaIngresoSolicitudListaDocumentosDocumentoSGD lteisldd_iterador : lcteisldd_documentos)
							{
								if(lteisldd_iterador != null)
								{
									String ls_dId;
									String ls_docName;
									String ls_tipologiaBachue;
									String ls_idTipoDocumental;

									ls_dId                  = lteisldd_iterador.getDId();
									ls_docName              = lteisldd_iterador.getDocName();
									ls_tipologiaBachue      = lteisldd_iterador.getTipoDocumental();
									ls_idTipoDocumental     = null;

									if(!StringUtils.isValidString(ls_dId))
									{
										ltsisc_codigo = TipoSalidaIngresoSolicitudCodigo.value2;
										throw new B2BException(ErrorKeys.ERROR_D_ID);
									}

									if(!StringUtils.isValidString(ls_docName))
									{
										ltsisc_codigo = TipoSalidaIngresoSolicitudCodigo.value2;
										throw new B2BException(ErrorKeys.ERROR_DOC_NAME);
									}

									if(!StringUtils.isValidString(ls_tipologiaBachue))
									{
										ltsisc_codigo = TipoSalidaIngresoSolicitudCodigo.value2;
										throw new B2BException(ErrorKeys.ERROR_TIPO_DOCUMENTAL);
									}

									Collection<DocumentoOWCC> lcdowcc_documento;
									DocumentoOWCC             ldowcc_documento;

									ldowcc_documento = new DocumentoOWCC();

									ldowcc_documento.setiD(ls_dId);
									ldowcc_documento.setDocName(ls_docName);

									{
										TipoDocumental ltd_tipoDocumental;

										ltd_tipoDocumental = DaoCreator.getTipoDocumentalDAO(ldm_manager)
												                           .findByTipologiasBachue(ls_tipologiaBachue);

										if(ltd_tipoDocumental != null)
											ls_idTipoDocumental = ltd_tipoDocumental.getIdTipoDocumental();
										else
										{
											ltsisc_codigo = TipoSalidaIngresoSolicitudCodigo.value2;
											throw new B2BException(ErrorKeys.ERROR_TIPO_DOCUMENTAL);
										}
									}

									ldowcc_documento.setDocType(ls_tipologiaBachue);

									lcdowcc_documento = getConsultaSGDBusiness().consultaSGD(ldowcc_documento);

									if(CollectionUtils.isValidCollection(lcdowcc_documento))
									{
										Iterator<DocumentoOWCC> lidowcc_iterator;

										lidowcc_iterator = lcdowcc_documento.iterator();

										if(lidowcc_iterator.hasNext())
										{
											ldowcc_documento = lidowcc_iterator.next();

											if(ldowcc_documento != null)
											{
												SolicitudCopias lsc_solicitudCopias;

												lsc_solicitudCopias = new SolicitudCopias();

												lsc_solicitudCopias.setIdTipoDocumental(ls_idTipoDocumental);
												lsc_solicitudCopias.setDocNameOriginal(ls_docName);
												lsc_solicitudCopias.setIdEcmOriginal(ls_dId);
												lsc_solicitudCopias.setNumeroCopias(NumericUtils.getLongWrapper(1L));
												lsc_solicitudCopias.setNumeroFolios(
												    NumericUtils.getLongWrapper(ldowcc_documento.getnumeroFolios())
												);
												lsc_solicitudCopias.setRepositorio(RepositorioCommon.FINAL);
												lsc_solicitudCopias.setActivo(EstadoCommon.S);
												lsc_solicitudCopias.setIdUsuarioCreacion(as_userId);
												lsc_solicitudCopias.setIpCreacion(as_remoteIp);
												lsc_solicitudCopias.setIdentificadorCopiasSE(
												    NumericUtils.getLongWrapper(ls_identificadorCopiasSE)
												);

												lsc_DAO.insertOrUpdate(lsc_solicitudCopias, true);
											}
											else
											{
												ltsisc_codigo = TipoSalidaIngresoSolicitudCodigo.value2;
												throw new B2BException(ErrorKeys.ERROR_DOCUMENTO_SALIDA);
											}
										}
										else
										{
											ltsisc_codigo = TipoSalidaIngresoSolicitudCodigo.value2;
											throw new B2BException(ErrorKeys.ERROR_DOCUMENTO_SALIDA);
										}
									}
									else
									{
										ltsisc_codigo = TipoSalidaIngresoSolicitudCodigo.value2;
										throw new B2BException(ErrorKeys.ERROR_DOCUMENTO_SALIDA);
									}
								}
								else
								{
									ltsisc_codigo = TipoSalidaIngresoSolicitudCodigo.value2;
									throw new B2BException(ErrorKeys.ERROR_PARAMETROS_NO_VALIDOS);
								}
							}
						}
						else
						{
							ltsisc_codigo = TipoSalidaIngresoSolicitudCodigo.value2;
							throw new B2BException(ErrorKeys.ERROR_PARAMETROS_NO_VALIDOS);
						}
					}
					else
					{
						ltsisc_codigo = TipoSalidaIngresoSolicitudCodigo.value2;
						throw new B2BException(ErrorKeys.ERROR_IDENTIFICADOR_COPIAS_SE);
					}
				}
				else
				{
					ltsisc_codigo = TipoSalidaIngresoSolicitudCodigo.value2;
					throw new B2BException(ErrorKeys.ERROR_PARAMETROS_NO_VALIDOS);
				}
			}
			else
			{
				ltsisc_codigo = TipoSalidaIngresoSolicitudCodigo.value2;
				throw new B2BException(ErrorKeys.ERROR_PARAMETROS_NO_VALIDOS);
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			ls_identificadorCopiasSE     = "";
			ls_descripcionMensaje        = addErrorSDC(lb2be_e.getMessageKey());

			clh_LOGGER.error("ingresoSolicitud", lb2be_e);
		}
		finally
		{
			ldm_manager.close();
		}

		ltsis_return.setCodigo(ltsisc_codigo);
		ltsis_return.setMensaje(ls_descripcionMensaje);
		ltsis_return.setIdSolicitudCopias(ls_identificadorCopiasSE);

		return ltsis_return;
	}

	/**
	 * Método de obtención de consulta SGD business
	 * @return de tipo ConsultaSGDBusiness con el valor de la propiedad
	 */
	protected ConsultaSGDBusiness getConsultaSGDBusiness()
	{
		if(icsgd_business == null)
			icsgd_business = new ConsultaSGDBusiness();

		return icsgd_business;
	}
}
