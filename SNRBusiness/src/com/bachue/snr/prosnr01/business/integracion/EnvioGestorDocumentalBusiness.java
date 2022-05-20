package com.bachue.snr.prosnr01.business.integracion;

import co.gov.supernotariado.www.schemas.bachue.co.enviodocumentos.enviardocumento.v1.TipoEntradaEnviarDocumento;
import co.gov.supernotariado.www.schemas.bachue.co.enviodocumentos.enviardocumento.v1.TipoEntradaEnviarDocumentoRepositorio;
import co.gov.supernotariado.www.schemas.bachue.co.enviodocumentos.enviardocumento.v1.TipoParametro;
import co.gov.supernotariado.www.schemas.bachue.co.enviodocumentos.enviardocumento.v1.TipoSalidaEnviarDocumento;

import co.gov.supernotariado.www.services.bachue.co.enviodocumentos.v1.SUT_CO_EnvioDocumentosProxy;
import co.gov.supernotariado.www.services.bachue.co.enviodocumentos.v1.SUT_CO_EnvioDocumentos_PortType;

import com.b2bsg.common.dataAccess2.DAOManager;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.logging.LoggerHandler;

import com.b2bsg.common.util.BooleanUtils;
import com.b2bsg.common.util.CollectionUtils;
import com.b2bsg.common.util.NumericUtils;
import com.b2bsg.common.util.StringUtils;

import com.bachue.prosnr01.integracion.cliente.owcc.ParametrosDocumento;

import com.bachue.snr.prosnr01.business.BaseBusiness;

import com.bachue.snr.prosnr01.common.constants.ConstanteCommon;
import com.bachue.snr.prosnr01.common.constants.ErrorKeys;
import com.bachue.snr.prosnr01.common.constants.EstadoCommon;
import com.bachue.snr.prosnr01.common.constants.ExtensionCommon;
import com.bachue.snr.prosnr01.common.constants.IdentificadoresCommon;
import com.bachue.snr.prosnr01.common.constants.MessagesKeys;
import com.bachue.snr.prosnr01.common.constants.SistemaOrigenCommon;

import com.bachue.snr.prosnr01.dao.DaoCreator;
import com.bachue.snr.prosnr01.dao.DaoManagerFactory;
import com.bachue.snr.prosnr01.dao.acc.BitacoraProcesoDAO;
import com.bachue.snr.prosnr01.dao.acc.SolicitudDAO;
import com.bachue.snr.prosnr01.dao.pgn.ConstantesDAO;

import com.bachue.snr.prosnr01.model.calificacion.RegistroCalificacion;
import com.bachue.snr.prosnr01.model.integration.OWCC.DocumentoOWCC;
import com.bachue.snr.prosnr01.model.sdb.acc.Acto;
import com.bachue.snr.prosnr01.model.sdb.acc.BitacoraProceso;
import com.bachue.snr.prosnr01.model.sdb.acc.DocumentosSalida;
import com.bachue.snr.prosnr01.model.sdb.acc.Persona;
import com.bachue.snr.prosnr01.model.sdb.acc.Proceso;
import com.bachue.snr.prosnr01.model.sdb.acc.Solicitud;
import com.bachue.snr.prosnr01.model.sdb.acc.SolicitudAsociada;
import com.bachue.snr.prosnr01.model.sdb.acc.Turno;
import com.bachue.snr.prosnr01.model.sdb.bng.Imagenes;
import com.bachue.snr.prosnr01.model.sdb.pgn.CirculoRegistral;
import com.bachue.snr.prosnr01.model.sdb.pgn.Constantes;
import com.bachue.snr.prosnr01.model.sdb.pgn.TipoDocumental;

import java.math.BigInteger;

import java.util.Collection;
import java.util.Date;
import java.util.Map;

import java.util.Map.Entry;


/**
 * Clase que contiene los métodos de negocio para el envió de imágenes al gestor documental.
 *
 * @author Heiner Castañeda.
 *
 */
public class EnvioGestorDocumentalBusiness extends BaseBusiness
{
	/** Constante clh_LOGGER. */
	private static final LoggerHandler clh_LOGGER = (LoggerHandler)com.bachue.snr.prosnr01.common.utils.LoggingHelper
			.getLogger(EnvioGestorDocumentalBusiness.class);

	/**
	 * Método encargado de consultar las imagenes con contenido valido en la tabla SDB_BGN_IMAGENES.
	 *
	 * @param as_remoteIp correspondiente al valor del tipo de objeto String
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public synchronized void enviarGestorDocumental(String as_remoteIp)
	    throws B2BException
	{
		Collection<DocumentosSalida> acds_documentoSalida;
		DAOManager                   ldm_manager;

		acds_documentoSalida     = null;
		ldm_manager              = DaoManagerFactory.getDAOManager();

		try
		{
			Constantes lc_constant;

			lc_constant = DaoCreator.getConstantesDAO(ldm_manager)
					                    .findById(ConstanteCommon.JOB_ENVIO_GESTOR_DOCUMENTAL_WS_INVOKE);

			if(lc_constant != null)
			{
				if(BooleanUtils.getBooleanValue(lc_constant.getCaracter()))
					acds_documentoSalida = DaoCreator.getDocumentosSalidaDAO(ldm_manager).findDocumentsEcm();
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("envioGestorDocumental", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		if(CollectionUtils.isValidCollection(acds_documentoSalida))
			enviarGestorDocumental(acds_documentoSalida, as_remoteIp);
	}

	/**
	 *  Método encargado de validar los documentos encontrados para enviarlos al gestor documental.
	 *
	 * @param acds_parametros Colección de datos de tipo DocumentosSalida que contiene los documentos que se enviaran al gestor documental.
	 * @param as_remoteIp correspondiente al valor del tipo de objeto String
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public synchronized void enviarGestorDocumental(Collection<DocumentosSalida> acds_parametros, String as_remoteIp)
	    throws B2BException
	{
		boolean lb_alreadyProcessing;
		String  ls_constant;
		String  ls_userId;

		ls_constant     = ConstanteCommon.JOB_ENVIO_GESTOR_DOCUMENTAL_BLOQUEO;
		ls_userId       = null;

		{
			DAOManager ldm_usuario;

			ldm_usuario = DaoManagerFactory.getDAOManager();

			try
			{
				ls_userId = getSystemUser(ConstanteCommon.USUARIO_PROCESOS_AUTOMATICOS, ldm_usuario);
			}
			catch(B2BException lb2be_e)
			{
				ldm_usuario.setRollbackOnly();

				clh_LOGGER.error("enviarGestorDocumental", lb2be_e);

				throw lb2be_e;
			}
			finally
			{
				ldm_usuario.close();
			}
		}

		{
			DAOManager ldm_processing;

			ldm_processing = DaoManagerFactory.getDAOManager();

			ldm_processing.setAutoCommit(true);

			try
			{
				ConstantesDAO lcd_constant;
				Constantes    lce_constant;

				lcd_constant     = DaoCreator.getConstantesDAO(ldm_processing);
				lce_constant     = lcd_constant.findById(ls_constant);

				if(lce_constant != null)
				{
					lb_alreadyProcessing = BooleanUtils.getBooleanValue(lce_constant.getCaracter());

					if(!lb_alreadyProcessing)
						lcd_constant.updateString(ls_constant, EstadoCommon.S, ls_userId);
				}
				else
				{
					Object[] loa_messageArgs;

					loa_messageArgs        = new String[1];
					loa_messageArgs[0]     = StringUtils.getString(ls_constant);

					throw new B2BException(ErrorKeys.ERROR_SIN_CONSTANTE, loa_messageArgs);
				}
			}
			catch(B2BException lb2be_e)
			{
				ldm_processing.setRollbackOnly();

				clh_LOGGER.error("enviarGestorDocumental", lb2be_e);

				throw lb2be_e;
			}
			finally
			{
				ldm_processing.close();
			}
		}

		try
		{
			if(!lb_alreadyProcessing)
			{
				DAOManager ldm_bitacora;
				DAOManager ldm_constantes;

				ldm_bitacora       = DaoManagerFactory.getDAOManager();
				ldm_constantes     = DaoManagerFactory.getDAOManager();

				try
				{
					ConstantesDAO lcd_constantes;
					String        ls_endpoint;

					lcd_constantes     = DaoCreator.getConstantesDAO(ldm_constantes);

					ls_endpoint = lcd_constantes.findString(ConstanteCommon.JOB_ENVIO_GESTOR_DOCUMENTAL_ENDPOINT);

					if(CollectionUtils.isValidCollection(acds_parametros))
					{
						BitacoraProcesoDAO lbpd_bitacora;

						lbpd_bitacora = DaoCreator.getBitacoraProcesoDAO(ldm_bitacora);

						ldm_bitacora.setAutoCommit(true);

						for(DocumentosSalida lds_iterador : acds_parametros)
						{
							if(lds_iterador != null)
							{
								try
								{
									enviarGestorDocumental(
									    lds_iterador, lbpd_bitacora, ls_endpoint, ls_userId, as_remoteIp
									);
								}
								catch(Exception le_e)
								{
									clh_LOGGER.error("enviarGestorDocumental", le_e);
								}
							}
						}
					}
				}
				catch(B2BException lb2be_e)
				{
					ldm_bitacora.setRollbackOnly();
					ldm_constantes.setRollbackOnly();

					clh_LOGGER.error("enviarGestorDocumental", lb2be_e);
				}
				finally
				{
					ldm_bitacora.close();
					ldm_constantes.close();
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("enviarGestorDocumental", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			if(!lb_alreadyProcessing)
			{
				DAOManager ldm_processing;
				ldm_processing = DaoManagerFactory.getDAOManager();

				ldm_processing.setAutoCommit(true);

				try
				{
					DaoCreator.getConstantesDAO(ldm_processing).updateString(ls_constant, EstadoCommon.N, ls_userId);
				}
				catch(B2BException lb2be_e)
				{
					ldm_processing.setRollbackOnly();

					clh_LOGGER.error("enviarGestorDocumental", lb2be_e);

					throw lb2be_e;
				}
				finally
				{
					ldm_processing.close();
				}
			}
		}
	}

	/**
	 * Método encargado de ejecutar web service para el envio de imagenes al gestor documental.
	 *
	 * @param ads_parametros  Objeto de tipo  DocumentosSalida que contiene datos de un documento determinado.
	 * @param abpd_DAO Objeto de tipo  BitacoraProcesoDAO utilizado para crear instancia de la clase BitacoraProcesoDAO.
	 * @param as_endpoint Variable de tipo String que contiene la dirección de punto final del servicio de destino para el envió  al gestor documental.
	 * @param as_username Variable de tipo String que contiene el usuario determinado para el envió  al gestor documental.
	 * @param as_ipRemota correspondiente al valor del tipo de objeto String
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public synchronized void enviarGestorDocumental(
	    DocumentosSalida ads_parametros, BitacoraProcesoDAO abpd_DAO, String as_endpoint, String as_username,
	    String as_ipRemota
	)
	    throws B2BException
	{
		DAOManager ldm_manager;

		ldm_manager = DaoManagerFactory.getDAOManager();

		try
		{
			enviarGestorDocumental(ads_parametros, abpd_DAO, as_endpoint, as_username, as_ipRemota, ldm_manager);
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();
			clh_LOGGER.error("enviarGestorDocumental", lb2be_e);
			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}
	}

	/**
	 * Método encargado de ejecutar web service para el envio de imagenes al gestor documental.
	 *
	 * @param ads_parametros  Objeto de tipo  DocumentosSalida que contiene datos de un documento determinado.
	 * @param abpd_DAO Objeto de tipo  BitacoraProcesoDAO utilizado para crear instancia de la clase BitacoraProcesoDAO.
	 * @param as_endpoint Variable de tipo String que contiene la dirección de punto final del servicio de destino para el envió  al gestor documental.
	 * @param as_username Variable de tipo String que contiene el usuario determinado para el envió  al gestor documental.
	 * @param as_ipRemota correspondiente al valor del tipo de objeto String
	 * @param adm_manager <code>DAOManager</code> que controla la transaccionalidad con la base de datos.
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public synchronized void enviarGestorDocumental(
	    DocumentosSalida ads_parametros, BitacoraProcesoDAO abpd_DAO, String as_endpoint, String as_username,
	    String as_ipRemota, DAOManager adm_manager
	)
	    throws B2BException
	{
		try
		{
			if(ads_parametros != null)
			{
				boolean         lb_error;
				long            ll_idImagen;
				long            ll_idDocumentoSalida;
				BitacoraProceso lbp_bitacora;

				lb_error                 = false;
				ll_idImagen              = NumericUtils.getLong(ads_parametros.getIdImagen());
				ll_idDocumentoSalida     = ads_parametros.getIdDocumentoSalida();
				lbp_bitacora             = new BitacoraProceso();

				lbp_bitacora.setProceso(IdentificadoresCommon.ENVIO_GESTOR);
				lbp_bitacora.setLlave1(StringUtils.getString(ll_idImagen));
				lbp_bitacora.setIdUsuarioCreacion(as_username);
				lbp_bitacora.setIpCreacion(as_ipRemota);

				if(ll_idImagen <= 0)
				{
					Object[] loa_arg;

					loa_arg        = new String[1];
					loa_arg[0]     = StringUtils.getString(ll_idDocumentoSalida);
					lb_error       = true;

					lbp_bitacora.setDescripcion(addMessage(ErrorKeys.ERROR_IMAGEN_SALIDA, loa_arg, true));

					abpd_DAO.insert(lbp_bitacora);
				}

				if(!lb_error)
				{
					Imagenes li_imagenes;

					li_imagenes = new Imagenes();

					li_imagenes.setIdImagen(NumericUtils.getInt(ll_idImagen));

					li_imagenes = DaoCreator.getImagenesDAO(adm_manager).findById(li_imagenes);

					if(li_imagenes == null)
					{
						Object[] loa_arg;

						loa_arg        = new String[1];
						loa_arg[0]     = StringUtils.getString(ll_idImagen);
						lb_error       = true;

						lbp_bitacora.setDescripcion(addMessage(ErrorKeys.ERROR_IMAGEN_SALIDA_ID_IMAGEN, loa_arg, true));
					}

					if(!lb_error)
					{
						SolicitudDAO ls_DAO;

						String       ls_idTurno;
						String       ls_idCirculo;
						String       ls_idSolicitud;
						String       ls_NIR;
						String       ls_proceso;
						Turno        lt_turno;

						Solicitud lso_solicitud;

						ls_DAO     = DaoCreator.getSolicitudDAO(adm_manager);

						ls_idTurno         = ads_parametros.getIdTurno();
						ls_idCirculo       = null;
						ls_idSolicitud     = ads_parametros.getIdSolicitud();
						ls_NIR             = null;
						ls_proceso         = null;

						if(!StringUtils.isValidString(ls_idTurno))
							ls_idTurno = EstadoCommon.NA;

						if(!StringUtils.isValidString(ls_idSolicitud))
						{
							Object[] loa_arg;

							loa_arg        = new String[1];
							loa_arg[0]     = StringUtils.getString(ll_idDocumentoSalida);
							lb_error       = true;

							lbp_bitacora.setDescripcion(
							    addMessage(ErrorKeys.ERROR_DOCUMENTO_SALIDA_ID_SOLICITUD, loa_arg, true)
							);
						}

						lt_turno     = DaoCreator.getTurnoDAO(adm_manager).findById(ls_idTurno);

						ls_idCirculo = (lt_turno != null) ? lt_turno.getIdCirculo() : EstadoCommon.NA;

						if(!StringUtils.isValidString(ls_idCirculo))
						{
							Object[] loa_arg;

							loa_arg        = new String[1];
							loa_arg[0]     = StringUtils.getString(ll_idDocumentoSalida);
							lb_error       = true;

							lbp_bitacora.setDescripcion(
							    addMessage(ErrorKeys.ERROR_DOCUMENTO_SALIDA_ID_CIRCULO, loa_arg, true)
							);
						}

						lso_solicitud = ls_DAO.findById(ls_idSolicitud);

						if(lso_solicitud == null)
						{
							Object[] loa_arg;

							loa_arg        = new String[1];
							loa_arg[0]     = ls_idSolicitud;
							lb_error       = true;

							lbp_bitacora.setDescripcion(
							    addMessage(ErrorKeys.ERROR_SOLICITUD_ID_SOLICITUD, loa_arg, true)
							);
						}

						if(!lb_error)
						{
							ls_NIR = lso_solicitud.getNir();

							if(!StringUtils.isValidString(ls_NIR))
							{
								Object[] loa_arg;

								loa_arg        = new String[1];
								loa_arg[0]     = ls_idSolicitud;
								lb_error       = true;

								lbp_bitacora.setDescripcion(
								    addMessage(ErrorKeys.ERROR_NIR_ID_SOLICITUD, loa_arg, true)
								);
							}

							{
								Proceso lp_proceso;

								lp_proceso = DaoCreator.getProcesoDAO(adm_manager)
										                   .findById(new Proceso(lso_solicitud.getIdProceso()));

								if(lp_proceso != null)
									ls_proceso = lp_proceso.getNombre();
								else
								{
									Object[] loa_arg;

									loa_arg        = new String[1];
									loa_arg[0]     = lso_solicitud.getIdProceso();
									lb_error       = true;

									lbp_bitacora.setDescripcion(addMessage(ErrorKeys.PROCESO_NO_EXISTE, loa_arg, true));
								}
							}

							try
							{
								String ls_tipologiaBachue;
								String ls_nombreTipoDocumental;
								String ls_nombreOrip;

								ls_tipologiaBachue          = null;
								ls_nombreTipoDocumental     = null;
								ls_nombreOrip               = EstadoCommon.NA;

								if(!lb_error && !ls_idCirculo.equalsIgnoreCase(EstadoCommon.NA))
								{
									CirculoRegistral lcr_cr;

									lcr_cr = DaoCreator.getCirculoRegistralDAO(adm_manager).findById(ls_idCirculo);

									if(lcr_cr == null)
									{
										Object[] loa_arg;

										loa_arg        = new String[1];
										loa_arg[0]     = ls_idCirculo;
										lb_error       = true;

										lbp_bitacora.setDescripcion(
										    addMessage(ErrorKeys.ERROR_ORIP_ID_CIRCULO, loa_arg, true)
										);
									}
									else
										ls_nombreOrip = lcr_cr.getNombre();
								}

								if(!lb_error)
								{
									String ls_idTipoDocumental;

									ls_idTipoDocumental = ads_parametros.getIdTipoDocumental();

									if(StringUtils.isValidString(ls_idTipoDocumental))
									{
										TipoDocumental ltd_td;

										ltd_td = DaoCreator.getTipoDocumentalDAO(adm_manager)
												               .findById(ls_idTipoDocumental);

										if(ltd_td == null)
										{
											Object[] loa_arg;

											loa_arg        = new String[1];
											loa_arg[0]     = ls_idTipoDocumental;
											lb_error       = true;

											lbp_bitacora.setDescripcion(
											    addMessage(ErrorKeys.ERROR_TIPO_DOCUMENTAL_CODIGO, loa_arg, true)
											);
										}
										else
										{
											ls_tipologiaBachue          = ltd_td.getTipologiasBachue();
											ls_nombreTipoDocumental     = ltd_td.getNombre();
										}
									}
									else
									{
										lb_error = true;
										lbp_bitacora.setDescripcion(
										    addMessage(ErrorKeys.ERROR_TIPO_DOCUMENTAL_VALIDO, true)
										);
									}
								}

								if(!lb_error)
								{
									SUT_CO_EnvioDocumentos_PortType lscedpt_interface;
									SUT_CO_EnvioDocumentosProxy     lscedp_proxy;

									lscedp_proxy          = new SUT_CO_EnvioDocumentosProxy(as_endpoint);
									lscedpt_interface     = lscedp_proxy.getSUT_CO_EnvioDocumentos_PortType();

									if(lscedpt_interface != null)
									{
										TipoEntradaEnviarDocumentoRepositorio lteedr_destino;
										TipoParametro[]                       lttp_parametros;
										DocumentoOWCC                         ldowcc_OWCC;

										String ls_docRegistrado;
										String ls_numeroDoc;
										Date   ld_fechaDocumento;
										String ls_entidadOrigen;
										String ls_nombrePais;
										String ls_nombreDepartamento;
										String ls_nombreMunicipio;
										String ls_tipoOficina;

										lttp_parametros     = null;
										lteedr_destino      = null;
										ldowcc_OWCC         = new DocumentoOWCC();

										ls_docRegistrado          = null;
										ls_numeroDoc              = null;
										ld_fechaDocumento         = null;
										ls_entidadOrigen          = null;
										ls_nombrePais             = null;
										ls_nombreDepartamento     = null;
										ls_nombreMunicipio        = null;
										ls_tipoOficina            = null;

										if(StringUtils.isValidString(ls_tipologiaBachue))
											ldowcc_OWCC.setDocType(ls_tipologiaBachue);

										if(StringUtils.isValidString(ls_nombreOrip))
											ldowcc_OWCC.setNombreOrip(ls_nombreOrip);

										if(StringUtils.isValidString(ls_NIR))
											ldowcc_OWCC.setNir(ls_NIR);

										{
											String ls_idDocumento;

											ls_idDocumento = lso_solicitud.getIdDocumento();

											if(StringUtils.isValidString(ls_idDocumento))
											{
												RegistroCalificacion lrc_detalleDocumento;

												lrc_detalleDocumento = DaoCreator.getDocumentoDAO(adm_manager)
														                             .detalleDocumento(
														    ls_idDocumento,
														    StringUtils.getString(lso_solicitud.getVersionDocumento())
														);

												if(lrc_detalleDocumento != null)
												{
													ls_docRegistrado          = StringUtils.getString(
														    lrc_detalleDocumento.getNombreTipoDoc()
														);
													ls_numeroDoc              = StringUtils.getString(
														    lrc_detalleDocumento.getCodDocumento()
														);
													ld_fechaDocumento         = lrc_detalleDocumento.getFechaDocumento();
													ls_entidadOrigen          = StringUtils.getString(
														    lrc_detalleDocumento.getNombreOficina()
														);
													ls_nombrePais             = StringUtils.getString(
														    lrc_detalleDocumento.getNombrePais()
														);
													ls_nombreDepartamento     = StringUtils.getString(
														    lrc_detalleDocumento.getNombreDepartamento()
														);
													ls_nombreMunicipio        = StringUtils.getString(
														    lrc_detalleDocumento.getNombreMunicipio()
														);
													ls_tipoOficina            = StringUtils.getString(
														    lrc_detalleDocumento.getNombreTipoOficina()
														);
												}
											}
										}

										if(StringUtils.isValidString(ls_proceso))
											ldowcc_OWCC.setProceso(ls_proceso);

										if(StringUtils.isValidString(ls_idCirculo))
											ldowcc_OWCC.setIdOrip(ls_idCirculo);

										if(StringUtils.isValidString(ls_idTurno))
											ldowcc_OWCC.setTurno(ls_idTurno);

										if(StringUtils.isValidString(ls_docRegistrado))
											ldowcc_OWCC.setDocRegistrado(ls_docRegistrado);

										if(StringUtils.isValidString(ls_numeroDoc))
											ldowcc_OWCC.setNumeroDoc(ls_numeroDoc);

										if(ld_fechaDocumento != null)
											ldowcc_OWCC.setFechaDocumento(ld_fechaDocumento);

										if(StringUtils.isValidString(ls_entidadOrigen))
											ldowcc_OWCC.setEntidadOrigen(ls_entidadOrigen);

										if(StringUtils.isValidString(ls_nombrePais))
											ldowcc_OWCC.setNombrePais(ls_nombrePais);

										if(StringUtils.isValidString(ls_nombreDepartamento))
											ldowcc_OWCC.setNombreDepartamento(ls_nombreDepartamento);

										if(StringUtils.isValidString(ls_nombreMunicipio))
											ldowcc_OWCC.setNombreMunicipio(ls_nombreMunicipio);

										if(StringUtils.isValidString(ls_tipoOficina))
											ldowcc_OWCC.setTipoOficina(ls_tipoOficina);

										{
											Collection<Persona> lcp_intervinientes;
											String              ls_documentosIntervinientes;
											String              ls_nombresintervinientes;

											lcp_intervinientes     = DaoCreator.getPersonaDAO(adm_manager)
													                               .buscarPersonasPorSolicitudInterviniente(
													    ls_idSolicitud
													);

											ls_documentosIntervinientes     = null;
											ls_nombresintervinientes        = null;

											if(CollectionUtils.isValidCollection(lcp_intervinientes))
											{
												for(Persona lp_temp : lcp_intervinientes)
												{
													if(lp_temp != null)
													{
														String ls_idDocumentoTipo;

														ls_idDocumentoTipo = lp_temp.getIdDocumentoTipo();

														if(StringUtils.isValidString(ls_idDocumentoTipo))
														{
															boolean lb_esNit;

															lb_esNit = false;

															if(
															    ls_idDocumentoTipo.equalsIgnoreCase(
																        IdentificadoresCommon.NIT
																    )
															)
																lb_esNit = true;

															if(!StringUtils.isValidString(ls_nombresintervinientes))
															{
																if(lb_esNit)
																	ls_nombresintervinientes = lp_temp.getRazonSocial();
																else
																	ls_nombresintervinientes = lp_temp.getNombreCompleto();
															}
															else
															{
																if(lb_esNit)
																	ls_nombresintervinientes = ls_nombresintervinientes
																		+ IdentificadoresCommon.SIMBOLO_PUNTO_COMA_TXT
																		+ lp_temp.getRazonSocial();
																else
																	ls_nombresintervinientes = ls_nombresintervinientes
																		+ IdentificadoresCommon.SIMBOLO_PUNTO_COMA_TXT
																		+ lp_temp.getNombreCompleto();
															}

															if(!StringUtils.isValidString(ls_documentosIntervinientes))
																ls_documentosIntervinientes = lp_temp.getNumeroDocumento();
															else
																ls_documentosIntervinientes = ls_documentosIntervinientes
																	+ IdentificadoresCommon.SIMBOLO_PUNTO_COMA_TXT
																	+ lp_temp.getNumeroDocumento();
														}
													}
												}
											}

											if(StringUtils.isValidString(ls_documentosIntervinientes))
												ldowcc_OWCC.setDocumentosIntervinientes(ls_documentosIntervinientes);

											if(StringUtils.isValidString(ls_nombresintervinientes))
												ldowcc_OWCC.setNombresIntervinientes(ls_nombresintervinientes);
										}

										{
											Collection<Acto> lca_actos;
											String           ls_tipoActo;

											lca_actos     = DaoCreator.getActoDAO(adm_manager)
													                      .findByIdSolicitudCirculo(
													    ls_idSolicitud, ls_idCirculo
													);

											ls_tipoActo = null;

											if(CollectionUtils.isValidCollection(lca_actos))
											{
												for(Acto la_tmp : lca_actos)
												{
													if(la_tmp != null)
													{
														if(!StringUtils.isValidString(ls_tipoActo))
															ls_tipoActo = la_tmp.getIdTipoActo();
														else
															ls_tipoActo = ls_tipoActo
																+ IdentificadoresCommon.SIMBOLO_PUNTO_COMA_TXT
																+ la_tmp.getIdTipoActo();
													}
												}

												ls_tipoActo = null;
											}

											if(StringUtils.isValidString(ls_tipoActo))
												ldowcc_OWCC.setTipoActo(ls_tipoActo);
										}

										{
											String ls_nirVinculado;

											ls_nirVinculado = obtenerNIRsVinculados(ls_idSolicitud, adm_manager);

											if(StringUtils.isValidString(ls_nirVinculado))
												ldowcc_OWCC.setNirVinculado(ls_nirVinculado);
										}

										{
											String ls_turnoVinculado;

											ls_turnoVinculado = obtenerTurnosVinculados(ls_idTurno, adm_manager);

											if(StringUtils.isValidString(ls_turnoVinculado))
												ldowcc_OWCC.setTurnoVinculado(ls_turnoVinculado);
										}

										if(lt_turno != null)
										{
											String ls_matriculas;

											ls_matriculas = obtenerMatriculasBySolicitudCirculo(
												    lt_turno.getIdSolicitud(), lt_turno.getIdCirculo(), adm_manager
												);

											if(StringUtils.isValidString(ls_matriculas))
												ldowcc_OWCC.setMatriculas(ls_matriculas);
										}

										{
											Map<String, String> lmss_parametros;

											lmss_parametros = ParametrosDocumento.generarParametrosBusquedaOWCC(
												    ldowcc_OWCC, false
												);

											if(CollectionUtils.isValidCollection(lmss_parametros))
											{
												int li_contador;

												li_contador         = 0;
												lttp_parametros     = new TipoParametro[lmss_parametros.size()];

												for(Entry<String, String> less_actual : lmss_parametros.entrySet())
												{
													if(less_actual != null)
														lttp_parametros[li_contador++] = new TipoParametro(
															    less_actual.getKey(), less_actual.getValue()
															);
												}
											}
										}

										{
											String ls_repositorio;

											ls_repositorio = ads_parametros.getRepositorio();

											if(StringUtils.isValidString(ls_repositorio))
											{
												if(
												    ls_repositorio.equalsIgnoreCase(
													        TipoEntradaEnviarDocumentoRepositorio._FINAL
													    )
												)
													lteedr_destino = TipoEntradaEnviarDocumentoRepositorio.FINAL;
												else if(
												    ls_repositorio.equalsIgnoreCase(
													        TipoEntradaEnviarDocumentoRepositorio._TEMPORAL
													    )
												)
													lteedr_destino = TipoEntradaEnviarDocumentoRepositorio.TEMPORAL;
											}

											if(lteedr_destino == null)
											{
												Object[] loa_arg;

												loa_arg        = new String[1];
												loa_arg[0]     = StringUtils.getString(ll_idDocumentoSalida);
												lb_error       = true;

												lbp_bitacora.setDescripcion(
												    addMessage(ErrorKeys.ERROR_REPOSITORIOO_NO_VALIDO, loa_arg, true)
												);

												abpd_DAO.insert(lbp_bitacora);
											}
										}

										{
											TipoSalidaEnviarDocumento ltsed_response;

											clh_LOGGER.error(
											    "enviarGestorDocumental",
											    "se inicia envio al gestor documental del documento salida "
											    + ads_parametros.getIdDocumentoSalida()
											);

											ltsed_response = lscedpt_interface.enviarDocumento(
												    new TipoEntradaEnviarDocumento(
												        SistemaOrigenCommon.CORE, lteedr_destino, lttp_parametros,
												        li_imagenes.getImagenBlob(),
												        ls_nombreTipoDocumental + ExtensionCommon.PDF_MAYUS_PUNTO
												    )
												);

											if(ltsed_response != null)
											{
												BigInteger lbi_codigoMensaje;

												lbi_codigoMensaje = ltsed_response.getCodigoMensaje();

												String ls_did;
												String ls_docName;

												ls_did         = ltsed_response.getDID();
												ls_docName     = ltsed_response.getDocName();

												clh_LOGGER.error(
												    "enviarGestorDocumental",
												    "se recibe respuesta con datos id_ecm: " + ls_did + " y doc_id: "
												    + ls_docName
												);

												if((lbi_codigoMensaje != null) && (lbi_codigoMensaje.intValue() == 200))
												{
													ads_parametros.setIdEcm(ls_did);
													ads_parametros.setIdNombreDocumento(ls_docName);
													ads_parametros.setIdUsuarioModificacion(as_username);
													ads_parametros.setIpModificacion(as_ipRemota);
													ads_parametros.setEstado(ads_parametros.getEstado());

													DaoCreator.getDocumentosSalidaDAO(adm_manager)
														          .actualizarEnvioExitoso(ads_parametros);

													//TODO se quita cuando se active consultaSGD
													//eliminarDocumentoAsociado(ll_idDocumentoSalida, ldm_manager);
												}
												else
												{
													lb_error = true;
													lbp_bitacora.setDescripcion(ltsed_response.getDescripcionMensaje());
												}
											}
											else
											{
												Object[] loa_arg;

												loa_arg        = new String[1];
												loa_arg[0]     = addMessage(MessagesKeys.ENVIAR_DOCUMENTO, true);
												lb_error       = true;

												lbp_bitacora.setDescripcion(
												    addMessage(ErrorKeys.ERROR_SIN_RESPUESTA_OPERACION, loa_arg, true)
												);
											}
										}
									}
									else
									{
										lb_error = true;
										lbp_bitacora.setDescripcion(
										    addMessage(ErrorKeys.ERROR_NO_SE_ENCONTRO_INTERFACE_SERVICIOS_VALIDA, true)
										);
									}
								}
							}
							catch(Exception le_e)
							{
								Object[] loa_arg;

								loa_arg        = new String[1];
								loa_arg[0]     = le_e.getMessage();
								lb_error       = true;

								lbp_bitacora.setDescripcion(addMessage(ErrorKeys.ERROR_INTEGRACION, loa_arg, true));
							}
						}
					}
				}

				if(lb_error)
				{
					ads_parametros.setIntentoEnvio(ads_parametros.getIntentoEnvio() + 1);
					ads_parametros.setIdUsuarioModificacion(as_username);
					ads_parametros.setIpModificacion(as_ipRemota);

					DaoCreator.getDocumentosSalidaDAO(adm_manager).actualizarEnvioFallido(ads_parametros);

					abpd_DAO.insert(lbp_bitacora);

					throw new B2BException(lbp_bitacora.getDescripcion());
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("enviarGestorDocumental", lb2be_e);

			throw lb2be_e;
		}
	}

	/**
	 * Método encargado de cargar los documentos de la solicitud.
	 *
	 * @param as_proceso de as proceso
	 * @param as_idSolicitud Variable que contiene el id de la solicitud.
	 * @param as_idUsuario Variable que contiene el id del usuario.
	 * @param as_ipRemota Variable que contiene la ip del usuario.
	 * @return Colección con los documentos consultados.
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public synchronized Collection<DocumentosSalida> cargarDocumentosSolicitud(
	    String as_proceso, String as_idSolicitud, String as_idUsuario, String as_ipRemota
	)
	    throws B2BException
	{
		Collection<DocumentosSalida> lcds_return;
		DAOManager                   ldm_manager;
		DAOManager                   ldm_bitacora;

		lcds_return      = null;
		ldm_manager      = DaoManagerFactory.getDAOManager();
		ldm_bitacora     = DaoManagerFactory.getDAOManager();

		try
		{
			if(StringUtils.isValidString(as_idSolicitud) && StringUtils.isValidString(as_proceso))
			{
				//Se utiliza metodo findByIdSolicitudEstadoImpresion en lugar de findByIdSolicitudEstadoImpresionTipo por mantis 2468
				lcds_return = DaoCreator.getDocumentosSalidaDAO(ldm_manager)
						                    .findByIdSolicitudEstadoImpresion(as_idSolicitud);

				{
					SolicitudAsociada lsa_solAsociada;

					lsa_solAsociada = DaoCreator.getSolicitudAsociadaDAO(ldm_manager).findByIdSol1(
						    as_idSolicitud, true
						);

					if(lsa_solAsociada != null)
					{
						String ls_idSolicitudAsociada;

						ls_idSolicitudAsociada = lsa_solAsociada.getIdSolicitud();

						if(StringUtils.isValidString(ls_idSolicitudAsociada))
						{
							Collection<DocumentosSalida> lcds_data;

							//Se utiliza metodo findByIdSolicitudEstadoImpresion en lugar de findByIdSolicitudEstadoImpresionTipo por mantis 2468
							lcds_data = DaoCreator.getDocumentosSalidaDAO(ldm_manager)
									                  .findByIdSolicitudEstadoImpresion(ls_idSolicitudAsociada);

							if(CollectionUtils.isValidCollection(lcds_data))
								lcds_return.addAll(lcds_data);
						}
					}
				}

				if(CollectionUtils.isValidCollection(lcds_return))
				{
					String ls_endpoint;

					ls_endpoint = DaoCreator.getConstantesDAO(ldm_manager)
							                    .findString(ConstanteCommon.JOB_ENVIO_GESTOR_DOCUMENTAL_ENDPOINT);

					if(StringUtils.isValidString(ls_endpoint))
					{
						BitacoraProcesoDAO lbpd_bitacora;

						lbpd_bitacora = DaoCreator.getBitacoraProcesoDAO(ldm_bitacora);

						ldm_bitacora.setAutoCommit(true);

						for(DocumentosSalida lds_iterador : lcds_return)
						{
							if(lds_iterador != null)
							{
								if(!lds_iterador.isEnviadoOwcc())
								{
									enviarGestorDocumental(
									    lds_iterador, lbpd_bitacora, ls_endpoint, as_idUsuario, as_ipRemota, ldm_manager
									);

									if(!lds_iterador.isEnviadoOwcc())
										throw new B2BException(ErrorKeys.ERROR_ENVIO_OWCC);
								}
							}
						}
					}
				}
				else
					throw new B2BException(ErrorKeys.ERROR_DOCUMENTOS_NO_ENCONTRADOS);
			}
			else
				throw new B2BException(ErrorKeys.ERROR_INTERNO_SISTEMA);
		}
		catch(B2BException lb2be_e)
		{
			ldm_bitacora.setRollbackOnly();
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("cargarDocumentosSolicitud", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
			ldm_bitacora.close();
		}

		return lcds_return;
	}
}
