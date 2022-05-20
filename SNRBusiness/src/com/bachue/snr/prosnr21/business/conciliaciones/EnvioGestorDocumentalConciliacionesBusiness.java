package com.bachue.snr.prosnr21.business.conciliaciones;

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

import com.bachue.snr.prosnr01.common.constants.ErrorKeys;
import com.bachue.snr.prosnr01.common.constants.EstadoCommon;
import com.bachue.snr.prosnr01.common.constants.ExtensionCommon;
import com.bachue.snr.prosnr01.common.constants.IdentificadoresCommon;
import com.bachue.snr.prosnr01.common.constants.MessagesKeys;
import com.bachue.snr.prosnr01.common.constants.RepositorioCommon;
import com.bachue.snr.prosnr01.common.constants.SistemaOrigenCommon;

import com.bachue.snr.prosnr01.dao.DaoCreator;
import com.bachue.snr.prosnr01.dao.DaoManagerFactory;
import com.bachue.snr.prosnr01.dao.acc.BitacoraProcesoDAO;
import com.bachue.snr.prosnr01.dao.pgn.ConstantesDAO;

import com.bachue.snr.prosnr01.model.integration.OWCC.DocumentoOWCC;
import com.bachue.snr.prosnr01.model.sdb.acc.BitacoraProceso;
import com.bachue.snr.prosnr01.model.sdb.pgn.Constantes;

import com.bachue.snr.prosnr21.common.constants.ConstanteCommon;

import com.bachue.snr.prosnr21.model.pgn.ConArchivo;
import com.bachue.snr.prosnr21.model.pgn.ConDocumentos;
import com.bachue.snr.prosnr21.model.pgn.ConImagenes;
import com.bachue.snr.prosnr21.model.pgn.EntidadRecaudadoraConciliacion;
import com.bachue.snr.prosnr21.model.pgn.EntidadRecaudadoraCuenta;

import java.math.BigDecimal;
import java.math.BigInteger;

import java.util.Collection;
import java.util.Map;

import java.util.Map.Entry;


/**
 * Clase que contiene los métodos de negocio para el envió de imágenes al gestor
 * documental.
 *
 * @author Duvan Beltran.
 *
 */
public class EnvioGestorDocumentalConciliacionesBusiness extends BaseBusiness
{
	/** Constante clh_LOGGER. */
	private static final LoggerHandler clh_LOGGER = (LoggerHandler)com.bachue.snr.prosnr01.common.utils.LoggingHelper
			.getLogger(EnvioGestorDocumentalConciliacionesBusiness.class);

	/**
	 * Enviar gestor documental.
	 *
	 * @param as_remoteIp the as remote ip
	 * @throws B2BException the b 2 B exception
	 */
	public synchronized void enviarGestorDocumental(String as_remoteIp)
	    throws B2BException
	{
		Collection<ConDocumentos> accd_conDocumentos;
		DAOManager                ldm_manager;

		accd_conDocumentos     = null;
		ldm_manager            = DaoManagerFactory.getDAOManagerConciliacion();

		try
		{
			Constantes lc_constant;

			lc_constant = DaoCreator.getConstantesDAO(ldm_manager)
					                    .findById(ConstanteCommon.JOB_ENVIO_GESTOR_DOCUMENTAL_WS_INVOKE);

			if(lc_constant != null)
			{
				if(BooleanUtils.getBooleanValue(lc_constant.getCaracter()))
					accd_conDocumentos = DaoCreator.getConDocumentosDAO(ldm_manager).findDocumentsEcm();
			}
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

		if(CollectionUtils.isValidCollection(accd_conDocumentos))
			enviarGestorDocumental(accd_conDocumentos, as_remoteIp);
	}

	/**
	 *  Método encargado de validar los documentos encontrados para enviarlos al gestor documental.
	 *
	 * @param acds_parametros Colección de datos de tipo DocumentosSalida que contiene los documentos que se enviaran al gestor documental.
	 * @param as_remoteIp correspondiente al valor del tipo de objeto String
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public synchronized void enviarGestorDocumental(Collection<ConDocumentos> acds_parametros, String as_remoteIp)
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

						for(ConDocumentos lds_iterador : acds_parametros)
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
	 * Método encargado de ejecutar web service para el envio de imagenes al gestor
	 * documental.
	 *
	 * @param ads_parametros Objeto de tipo ConDocumento que contiene datos de un
	 *                       documento determinado.
	 * @param abpd_DAO       Objeto de tipo BitacoraProcesoDAO utilizado para crear
	 *                       instancia de la clase BitacoraProcesoDAO.
	 * @param as_endpoint    Variable de tipo String que contiene la dirección de
	 *                       punto final del servicio de destino para el envió al
	 *                       gestor documental.
	 * @param as_username    Variable de tipo String que contiene el usuario
	 *                       determinado para el envió al gestor documental.
	 * @param as_ipRemota    correspondiente al valor del tipo de objeto String
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public synchronized void enviarGestorDocumental(
	    ConDocumentos ads_parametros, BitacoraProcesoDAO abpd_DAO, String as_endpoint, String as_username,
	    String as_ipRemota
	)
	    throws B2BException
	{
		DAOManager ldm_manager;

		ldm_manager = DaoManagerFactory.getDAOManagerConciliacion();

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
	 * Método encargado de ejecutar web service para el envio de imagenes al gestor
	 * documental.
	 *
	 * @param ads_parametros Objeto de tipo ConDocumento que contiene datos de un
	 *                       documento determinado.
	 * @param abpd_DAO       Objeto de tipo BitacoraProcesoDAO utilizado para crear
	 *                       instancia de la clase BitacoraProcesoDAO.
	 * @param as_endpoint    Variable de tipo String que contiene la dirección de
	 *                       punto final del servicio de destino para el envió al
	 *                       gestor documental.
	 * @param as_username    Variable de tipo String que contiene el usuario
	 *                       determinado para el envió al gestor documental.
	 * @param as_ipRemota    correspondiente al valor del tipo de objeto String
	 * @param adm_manager    <code>DAOManager</code> que controla la
	 *                       transaccionalidad con la base de datos.
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public synchronized void enviarGestorDocumental(
	    ConDocumentos ads_parametros, BitacoraProcesoDAO abpd_DAO, String as_endpoint, String as_username,
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
				String          ls_nombreTipoDocumental;
				ConstantesDAO   lcdao_dao;

				lb_error                    = false;
				ll_idImagen                 = NumericUtils.getLong(ads_parametros.getIdImagen());
				ll_idDocumentoSalida        = ads_parametros.getIdDocumentoSalida();
				lbp_bitacora                = new BitacoraProceso();
				ls_nombreTipoDocumental     = ads_parametros.getTipo();
				lcdao_dao                   = DaoCreator.getConstantesDAO(adm_manager);

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
					ConImagenes lci_imagenes;

					lci_imagenes = DaoCreator.getConImagenesDAO(adm_manager).findById(ll_idImagen);

					if(lci_imagenes == null)
					{
						Object[] loa_arg;

						loa_arg        = new String[1];
						loa_arg[0]     = StringUtils.getString(ll_idImagen);
						lb_error       = true;

						lbp_bitacora.setDescripcion(addMessage(ErrorKeys.ERROR_IMAGEN_SALIDA_ID_IMAGEN, loa_arg, true));
					}

					if(!lb_error)
					{
						try
						{
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

									lttp_parametros     = null;
									lteedr_destino      = null;
									ldowcc_OWCC         = new DocumentoOWCC();

									{
										EntidadRecaudadoraCuenta lerc_entidadRecaudadoraCuenta;

										lerc_entidadRecaudadoraCuenta = DaoCreator.getEntidadRecaudadoraCuentaDAO(
											    adm_manager
											).findById(ads_parametros.getIdCuenta());

										if(lerc_entidadRecaudadoraCuenta != null)
										{
											EntidadRecaudadoraConciliacion lerc_entidadRecaudadora;
											String                         ls_numeroCuenta;

											lerc_entidadRecaudadora     = DaoCreator.getEntidadRecaudadoraConciliacionDAO(
												    adm_manager
												).findById(lerc_entidadRecaudadoraCuenta.getIdEntidadRecaudadora());
											ls_numeroCuenta             = lerc_entidadRecaudadoraCuenta.getNumeroCuenta();

											if(StringUtils.isValidString(ls_numeroCuenta))
												ldowcc_OWCC.setNumeroCuenta(ls_numeroCuenta);

											if(lerc_entidadRecaudadora != null)
											{
												String ls_codigoBanco;
												String ls_nombreBanco;

												ls_codigoBanco     = lerc_entidadRecaudadora.getCodigoEntidadRecaudadora();
												ls_nombreBanco     = lerc_entidadRecaudadora.getNombreEntidadRecaudadora();

												if(StringUtils.isValidString(ls_codigoBanco))
													ldowcc_OWCC.setCodigoBanco(ls_codigoBanco);

												if(StringUtils.isValidString(ls_nombreBanco))
													ldowcc_OWCC.setNombreBanco(ls_nombreBanco);
											}
										}
									}

									{
										ConArchivo lsca_conArchivo;
										String     ls_periodoCorte;

										lsca_conArchivo     = DaoCreator.getConArchivoDAO(adm_manager)
												                            .findById(ads_parametros.getIdArchivo());
										ls_periodoCorte     = ads_parametros.getIdPeriodoCorte();

										if(StringUtils.isValidString(ls_periodoCorte))
											ldowcc_OWCC.setPeriodoCorte(NumericUtils.getLongWrapper(ls_periodoCorte));

										if(lsca_conArchivo != null)
										{
											if(!StringUtils.isValidString(ls_periodoCorte))
											{
												ls_periodoCorte = lsca_conArchivo.getIdPeriodoCorte();
												ldowcc_OWCC.setPeriodoCorte(
												    NumericUtils.getLongWrapper(ls_periodoCorte)
												);
											}

											BigDecimal lbd_version;
											lbd_version = lsca_conArchivo.getVersion();

											if(NumericUtils.isValidBigDecimal(lbd_version))
												ldowcc_OWCC.setVersion(StringUtils.getString(lbd_version));
										}
									}

									{
										String ls_comprobante;

										ls_comprobante = ads_parametros.getNumeroComprobanteSIIF();

										if(StringUtils.isValidString(ls_comprobante))
											ldowcc_OWCC.setComprobante(ls_comprobante);
									}

									{
										String ls_numeroSIIF;

										ls_numeroSIIF = ads_parametros.getNumeroSIIF();

										if(StringUtils.isValidString(ls_numeroSIIF))
											ldowcc_OWCC.setNumeroSIIF(ls_numeroSIIF);
									}

									{
										String ls_FTP;

										ls_FTP = obtenerConstanteCaracter(lcdao_dao, ConstanteCommon.SERVIDOR_SFTP_IP);

										if(StringUtils.isValidString(ls_FTP))
											ldowcc_OWCC.setRutaServidorFTP(ls_FTP);
									}
									
									{
										ldowcc_OWCC.setProceso("CONCILIACION");
									}

									{
										Map<String, String> lmss_parametros;

										lmss_parametros = ParametrosDocumento.generarParametrosBusquedaOWCC(
											    ldowcc_OWCC, false, true
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

										ltsed_response = lscedpt_interface.enviarDocumento(
											    new TipoEntradaEnviarDocumento(
											        SistemaOrigenCommon.CORE, lteedr_destino, lttp_parametros,
											        lci_imagenes.getImagenBlob(),
											        ls_nombreTipoDocumental + ExtensionCommon.PDF_MAYUS_PUNTO
											    )
											);

										if(ltsed_response != null)
										{
											BigInteger lbi_codigoMensaje;
											String     ls_did;
											String     ls_docName;

											lbi_codigoMensaje     = ltsed_response.getCodigoMensaje();
											ls_did                = ltsed_response.getDID();
											ls_docName            = ltsed_response.getDocName();

											if((lbi_codigoMensaje != null) && (lbi_codigoMensaje.intValue() == 200))
											{
												ads_parametros.setIdEcm(ls_did);
												ads_parametros.setIdNombreDocumento(ls_docName);
												ads_parametros.setIdUsuarioModificacion(as_username);
												ads_parametros.setIpModificacion(as_ipRemota);
												ads_parametros.setEstado(ads_parametros.getEstado());

												DaoCreator.getConDocumentosDAO(adm_manager)
													          .actualizarEnvioExitoso(ads_parametros);

												// TODO se quita cuando se active consultaSGD
												// eliminarDocumentoAsociado(ll_idDocumentoSalida, ldm_manager);
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

				if(lb_error)
				{
					ads_parametros.setIntentoEnvio(ads_parametros.getIntentoEnvio() + 1);
					ads_parametros.setIdUsuarioModificacion(as_username);
					ads_parametros.setIpModificacion(as_ipRemota);

					DaoCreator.getConDocumentosDAO(adm_manager).actualizarEnvioFallido(ads_parametros);

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
	 * Guardar documento.
	 *
	 * @param as_extension the as extension
	 * @param aba_documento the aba documento
	 * @param as_userId the as user id
	 * @param as_remoteIp the as remote ip
	 * @param adm_manager the adm manager
	 * @param abpd_bitacora the abpd bitacora
	 * @param as_endpoint the as endpoint
	 * @throws B2BException the b 2 B exception
	 */
	public void guardarDocumento(
	    String as_nombreDoc, String as_extension, byte[] aba_documento, String as_userId, String as_remoteIp,
	    DAOManager adm_manager, BitacoraProcesoDAO abpd_bitacora, String as_endpoint, String as_idCuenta
	)
	    throws B2BException
	{
		try
		{
			long          ll_idImagen;
			long          ll_idDocumento;
			ConDocumentos lcd_conDocumentos;
			ConImagenes   lci_conImagenes;

			lcd_conDocumentos     = new ConDocumentos();
			lci_conImagenes       = new ConImagenes();

			lci_conImagenes.setTipoArchivo(as_extension);
			lci_conImagenes.setEstado(com.bachue.snr.prosnr21.common.constants.EstadoCommon.ACTIVO);
			lci_conImagenes.setTamano(Long.valueOf(aba_documento.length));
			lci_conImagenes.setIdUsuarioCreacion(as_userId);
			lci_conImagenes.setIpCreacion(as_remoteIp);
			lci_conImagenes.setImagenBlob(aba_documento);

			ll_idImagen = DaoCreator.getConImagenesDAO(adm_manager).insertOrUpdate(lci_conImagenes, true);

			if(!NumericUtils.isValidLong(NumericUtils.getLongWrapper(ll_idImagen), 1))
				throw new B2BException(ErrorKeys.SIN_SECUENCIA_IMAGENES);

			lcd_conDocumentos.setIdImagen(NumericUtils.getLongWrapper(ll_idImagen));
			lcd_conDocumentos.setTipoArchivo(as_extension);
			lcd_conDocumentos.setTipo(as_nombreDoc);
			lcd_conDocumentos.setEstado(com.bachue.snr.prosnr21.common.constants.EstadoCommon.ACTIVO);
			lcd_conDocumentos.setGenerado(com.bachue.snr.prosnr21.common.constants.EstadoCommon.S);
			lcd_conDocumentos.setIdCuenta(as_idCuenta);
			lcd_conDocumentos.setRepositorio(RepositorioCommon.TEMPORAL);
			lcd_conDocumentos.setIdUsuarioCreacion(as_userId);
			lcd_conDocumentos.setIpCreacion(as_remoteIp);

			ll_idDocumento = DaoCreator.getConDocumentosDAO(adm_manager).insertOrUpdate(lcd_conDocumentos, true);

			lcd_conDocumentos.setIdDocumentoSalida(ll_idDocumento);

//			enviarGestorDocumental(lcd_conDocumentos, abpd_bitacora, as_endpoint, as_userId, as_remoteIp, adm_manager);
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("generarReportes", lb2be_e);

			throw lb2be_e;
		}
	}
}
