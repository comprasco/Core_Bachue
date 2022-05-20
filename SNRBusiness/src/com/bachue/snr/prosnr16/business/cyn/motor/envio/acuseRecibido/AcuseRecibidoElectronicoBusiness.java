package com.bachue.snr.prosnr16.business.cyn.motor.envio.acuseRecibido;

import com.b2bsg.common.dataAccess2.DAOManager;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.logging.LoggerHandler;

import com.b2bsg.common.mail.SendMail;

import com.b2bsg.common.util.BooleanUtils;
import com.b2bsg.common.util.CollectionUtils;
import com.b2bsg.common.util.DateUtils;
import com.b2bsg.common.util.NumericUtils;
import com.b2bsg.common.util.StringUtils;

import com.b2bsg.common.xml.DomHelper;

import com.bachue.prosnr16.integracion.cliente.cyn.envioAcuseRecibido.ClienteAcusarMensaje;

import com.bachue.snr.prosnr01.business.BaseBusiness;

import com.bachue.snr.prosnr01.common.constants.EstadoCommon;
import com.bachue.snr.prosnr01.common.constants.IdentificadoresCommon;
import com.bachue.snr.prosnr01.common.constants.ProyectosCommon;

import com.bachue.snr.prosnr01.dao.DaoCreator;
import com.bachue.snr.prosnr01.dao.DaoManagerFactory;
import com.bachue.snr.prosnr01.dao.acc.BitacoraProcesoDAO;
import com.bachue.snr.prosnr01.dao.acc.MensajeDAO;
import com.bachue.snr.prosnr01.dao.pgn.ConstantesDAO;

import com.bachue.snr.prosnr01.model.sdb.pgn.Constantes;

import com.bachue.snr.prosnr16.common.constants.CanalCommon;
import com.bachue.snr.prosnr16.common.constants.ClasificacionCommon;
import com.bachue.snr.prosnr16.common.constants.ConstanteCommon;
import com.bachue.snr.prosnr16.common.constants.ErrorKeys;

import com.bachue.snr.prosnr16.model.sdb.acc.Mensaje;
import com.bachue.snr.prosnr16.model.sdb.acc.MensajeAcuseDetalle;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import org.xml.sax.SAXException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

import java.security.SecureRandom;

import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import java.util.concurrent.ThreadLocalRandom;

import javax.mail.BodyPart;
import javax.mail.Flags;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Part;
import javax.mail.Session;
import javax.mail.Store;

import javax.mail.internet.MimeBodyPart;

import javax.xml.parsers.ParserConfigurationException;


/**
 * Clase que contiene todos las propiedades AcuseRecibidoElectronicoBusiness.
 *
 * @author  Carlos Calderón
 * Fecha de Creacion: 30/03/2020
 */
public class AcuseRecibidoElectronicoBusiness extends BaseBusiness
{
	/** Constante clh_LOGGER. */
	private static final LoggerHandler clh_LOGGER = (LoggerHandler)com.bachue.snr.prosnr01.common.utils.LoggingHelper
			.getLogger(AcuseRecibidoElectronicoBusiness.class, ProyectosCommon.COMUNICACIONES_Y_NOTIFICACIONES_16);
	private static final String MAIL_STORE_TYPE                = "mail.store.type";
	private static final String MAIL_IMAP_PASSWORD             = "mail.imap.password";
	private static final String MAIL_IMAP_USER                 = "mail.imap.user";
	private static final String cs_ELEMENTO_NETWORK_ID         = "network_id";
	private static final String cs_ELEMENTO_DESTINOS           = "destinations";
	private static final String cs_ELEMENTO_DESTINO            = "destination";
	private static final String cs_ELEMENTO_DIRECCION          = "address";
	private static final String cs_ELEMENTO_HORA_LOCAL_ENTREGA = "delivery_time_local";

	/**
	 * Acuse recibido electronico.
	 *
	 * @param as_remoteIp de as remote ip
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public synchronized void acuseRecibidoElectronico(String as_remoteIp)
	    throws B2BException
	{
		Collection<Mensaje> acm_mensajes;
		DAOManager          ldm_manager;

		acm_mensajes     = null;
		ldm_manager      = DaoManagerFactory.getDAOManagerCYN();

		try
		{
			Constantes lc_constant;

			lc_constant = DaoCreator.getConstantesDAO(ldm_manager)
					                    .findById(ConstanteCommon.JOB_ACUSE_RECIBIDO_ELECTRONICO_WS_INVOKE);

			if(lc_constant != null)
			{
				if(BooleanUtils.getBooleanValue(lc_constant.getCaracter()))
					acm_mensajes = DaoCreator.getMensajeDAO(ldm_manager)
							                     .findMensajesFilter(
							    com.bachue.snr.prosnr16.common.constants.EstadoCommon.ACUSADO_RECIBIDO,
							    CanalCommon.ELECTRONICO, true, ClasificacionCommon.NOTIFICACION
							);
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("acuseRecibidoElectronico", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		if(CollectionUtils.isValidCollection(acm_mensajes))
			acuseRecibidoElectronico(acm_mensajes, as_remoteIp);
	}

	/**
	 * Acuse recibido electronico.
	 *
	 * @param acm_mensajes de acm mensajes
	 * @param as_remoteIp de as remote ip
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public synchronized void acuseRecibidoElectronico(Collection<Mensaje> acm_mensajes, String as_remoteIp)
	    throws B2BException
	{
		boolean lb_alreadyProcessing;
		String  ls_constant;
		String  ls_userId;

		ls_constant     = ConstanteCommon.JOB_ACUSE_RECIBIDO_ELECTRONICO_BLOQUEO;
		ls_userId       = null;

		{
			DAOManager ldm_usuario;

			ldm_usuario = DaoManagerFactory.getDAOManagerCYN();

			try
			{
				ls_userId = getSystemUser(ConstanteCommon.USUARIO_PROCESOS_AUTOMATICOS, false, ldm_usuario);
			}
			catch(B2BException lb2be_e)
			{
				ldm_usuario.setRollbackOnly();

				clh_LOGGER.error("acuseRecibidoElectronico", lb2be_e);

				throw lb2be_e;
			}
			finally
			{
				ldm_usuario.close();
			}
		}

		{
			DAOManager ldm_processing;

			ldm_processing = DaoManagerFactory.getDAOManagerCYN();

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

					throw new B2BException(addErrorCYN(ErrorKeys.ERROR_SIN_CONSTANTE, loa_messageArgs));
				}
			}
			catch(B2BException lb2be_e)
			{
				ldm_processing.setRollbackOnly();

				clh_LOGGER.error("acuseRecibidoElectronico", lb2be_e);

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

				ldm_bitacora       = DaoManagerFactory.getDAOManagerCYN();
				ldm_constantes     = DaoManagerFactory.getDAOManagerCYN();

				try
				{
					ConstantesDAO lcd_constantes;
					String        ls_endpoint;

					lcd_constantes     = DaoCreator.getConstantesDAO(ldm_constantes);

					ls_endpoint = lcd_constantes.findString(ConstanteCommon.JOB_ACUSE_RECIBIDO_ELECTRONICO_ENDPOINT);

					if(CollectionUtils.isValidCollection(acm_mensajes))
					{
						BitacoraProcesoDAO lbpd_bitacora;

						lbpd_bitacora = DaoCreator.getBitacoraProcesoDAO(ldm_bitacora);

						ldm_bitacora.setAutoCommit(true);

						for(Mensaje lm_iterador : acm_mensajes)
						{
							if(lm_iterador != null)
							{
								try
								{
									acuseRecibidoElectronico(
									    lm_iterador, lbpd_bitacora, ls_endpoint, ls_userId, as_remoteIp
									);
								}
								catch(Exception le_e)
								{
									clh_LOGGER.error("acuseRecibidoElectronico", le_e);
								}
							}
						}
					}
				}
				catch(B2BException lb2be_e)
				{
					ldm_bitacora.setRollbackOnly();
					ldm_constantes.setRollbackOnly();

					clh_LOGGER.error("acuseRecibidoElectronico", lb2be_e);
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
			clh_LOGGER.error("acuseRecibidoElectronico", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			if(!lb_alreadyProcessing)
			{
				DAOManager ldm_processing;
				ldm_processing = DaoManagerFactory.getDAOManagerCYN();

				ldm_processing.setAutoCommit(true);

				try
				{
					DaoCreator.getConstantesDAO(ldm_processing).updateString(ls_constant, EstadoCommon.N, ls_userId);
				}
				catch(B2BException lb2be_e)
				{
					ldm_processing.setRollbackOnly();

					clh_LOGGER.error("acuseRecibidoElectronico", lb2be_e);

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
	 * Acuse recibido electronico.
	 *
	 * @param lm_iterador de Mensaje
	 * @param abpd_DAO de BitacoraProcesoDAO
	 * @param as_endpoint correspondiente al valor de endpoint
	 * @param as_username de nombre de usuario
	 * @param as_ipRemota de ip remota
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public synchronized void acuseRecibidoElectronico(
	    Mensaje lm_iterador, BitacoraProcesoDAO abpd_DAO, String as_endpoint, String as_username, String as_ipRemota
	)
	    throws B2BException
	{
		DAOManager ldm_manager;

		ldm_manager = DaoManagerFactory.getDAOManagerCYN();

		try
		{
			if(lm_iterador != null)
			{
				MensajeAcuseDetalle lmad_acuceDetalle;

				lmad_acuceDetalle = DaoCreator.getMensajeAcuseDetalleDAO(ldm_manager)
						                          .findByIdMensaje(lm_iterador.getIdMensaje());

				if(lmad_acuceDetalle != null)
				{
					{
						lm_iterador.setIdEstado(
						    com.bachue.snr.prosnr16.common.constants.EstadoCommon.ACUSADO_NOTIFICADO
						);
						lm_iterador.setIdUsuarioModificacion(as_username);
						lm_iterador.setIpModificacion(as_ipRemota);

						DaoCreator.getMensajeDAO(ldm_manager).update(lm_iterador);
					}

					ClienteAcusarMensaje.acusarMensaje(
					    lm_iterador.getIdMensaje(), lm_iterador.getIdMensaje(),
					    obtenerCalendarDesdeDate(lmad_acuceDetalle.getFechaAcuseDetalle()),
					    obtenerCalendarDesdeDate(lm_iterador.getFechaEnvio()), as_endpoint
					);
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();
			clh_LOGGER.error("acuseRecibidoElectronico", lb2be_e);

			escribirEnBitacoraProceso(
			    abpd_DAO, IdentificadoresCommon.ACUSE_RECIBIDO_ELECTRONICO, lb2be_e.getMessage(), as_username,
			    as_endpoint, (lm_iterador != null) ? StringUtils.getString(lm_iterador.getIdMensaje()) : null
			);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}
	}

	/**
	 * Validar acuse recibido electronico.
	 *
	 * @param as_remoteIp de as remote ip
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public synchronized void validarAcuseRecibidoElectronico(String as_remoteIp)
	    throws B2BException
	{
		Map<String, Map<String, Date>> lmsmsd_mensajes;
		DAOManager                     ldm_manager;

		lmsmsd_mensajes     = new HashMap<String, Map<String, Date>>();
		ldm_manager         = DaoManagerFactory.getDAOManagerCYN();

		try
		{
			ConstantesDAO lcd_constantes;

			lcd_constantes = DaoCreator.getConstantesDAO(ldm_manager);

			if(
			    BooleanUtils.getBooleanValue(
				        lcd_constantes.findString(ConstanteCommon.JOB_VALIDAR_ACUSE_RECIBIDO_ELECTRONICO_WS_INVOKE)
				    )
			)
			{
				String ls_jndiJavaMail;

				ls_jndiJavaMail = lcd_constantes.findString(
					    ConstanteCommon.JOB_VALIDAR_ACUSE_RECIBIDO_ELECTRONICO_JNDI
					);

				if(StringUtils.isValidString(ls_jndiJavaMail))
					lmsmsd_mensajes = verificarBandejaEntrada(ls_jndiJavaMail);
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("validarAcuseRecibidoElectronico", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		if(CollectionUtils.isValidCollection(lmsmsd_mensajes))
			validarAcuseRecibidoElectronico(lmsmsd_mensajes, as_remoteIp);
	}

	/**
	 * Validar Acuse recibido electronico.
	 *
	 * @param amsmsd_mensajes de acm mensajes
	 * @param as_remoteIp de as remote ip
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public synchronized void validarAcuseRecibidoElectronico(
	    Map<String, Map<String, Date>> amsmsd_mensajes, String as_remoteIp
	)
	    throws B2BException
	{
		boolean lb_alreadyProcessing;
		String  ls_constant;
		String  ls_userId;

		ls_constant     = ConstanteCommon.JOB_VALIDAR_ACUSE_RECIBIDO_ELECTRONICO_BLOQUEO;
		ls_userId       = null;

		{
			DAOManager ldm_usuario;

			ldm_usuario = DaoManagerFactory.getDAOManagerCYN();

			try
			{
				ls_userId = getSystemUser(ConstanteCommon.USUARIO_PROCESOS_AUTOMATICOS, false, ldm_usuario);
			}
			catch(B2BException lb2be_e)
			{
				ldm_usuario.setRollbackOnly();

				clh_LOGGER.error("validarAcuseRecibidoElectronico", lb2be_e);

				throw lb2be_e;
			}
			finally
			{
				ldm_usuario.close();
			}
		}

		{
			DAOManager ldm_processing;

			ldm_processing = DaoManagerFactory.getDAOManagerCYN();

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

					throw new B2BException(addErrorCYN(ErrorKeys.ERROR_SIN_CONSTANTE, loa_messageArgs));
				}
			}
			catch(B2BException lb2be_e)
			{
				ldm_processing.setRollbackOnly();

				clh_LOGGER.error("validarAcuseRecibidoElectronico", lb2be_e);

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

				ldm_bitacora       = DaoManagerFactory.getDAOManagerCYN();
				ldm_constantes     = DaoManagerFactory.getDAOManagerCYN();

				try
				{
					if(CollectionUtils.isValidCollection(amsmsd_mensajes))
					{
						BitacoraProcesoDAO lbpd_bitacora;

						lbpd_bitacora = DaoCreator.getBitacoraProcesoDAO(ldm_bitacora);

						ldm_bitacora.setAutoCommit(true);

						Collection<String> lcs_networkIds;

						lcs_networkIds = amsmsd_mensajes.keySet();

						if(CollectionUtils.isValidCollection(lcs_networkIds))
						{
							for(String ls_networkId : lcs_networkIds)
							{
								if(StringUtils.isValidString(ls_networkId))
								{
									Map<String, Date> lmsd_entregas;

									lmsd_entregas = amsmsd_mensajes.get(ls_networkId);

									try
									{
										validarAcuseRecibidoElectronico(
										    ls_networkId, lmsd_entregas, lbpd_bitacora, ls_userId, as_remoteIp
										);
									}
									catch(Exception le_e)
									{
										clh_LOGGER.error("validarAcuseRecibidoElectronico", le_e);
									}
								}
							}
						}
					}
				}
				catch(B2BException lb2be_e)
				{
					ldm_bitacora.setRollbackOnly();
					ldm_constantes.setRollbackOnly();

					clh_LOGGER.error("validarAcuseRecibidoElectronico", lb2be_e);
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
			clh_LOGGER.error("validarAcuseRecibidoElectronico", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			if(!lb_alreadyProcessing)
			{
				DAOManager ldm_processing;
				ldm_processing = DaoManagerFactory.getDAOManagerCYN();

				ldm_processing.setAutoCommit(true);

				try
				{
					DaoCreator.getConstantesDAO(ldm_processing).updateString(ls_constant, EstadoCommon.N, ls_userId);
				}
				catch(B2BException lb2be_e)
				{
					ldm_processing.setRollbackOnly();

					clh_LOGGER.error("validarAcuseRecibidoElectronico", lb2be_e);

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
	 * Validar acuse recibido electronico.
	 *
	 * @param ls_identificadorMensaje de Mensaje
	 * @param amsd_acuse correspondiente al valor de amsd acuse
	 * @param abpd_DAO de BitacoraProcesoDAO
	 * @param as_username de nombre de usuario
	 * @param as_ipRemota de ip remota
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public synchronized void validarAcuseRecibidoElectronico(
	    String ls_identificadorMensaje, Map<String, Date> amsd_acuse, BitacoraProcesoDAO abpd_DAO, String as_username,
	    String as_ipRemota
	)
	    throws B2BException
	{
		DAOManager ldm_manager;

		ldm_manager = DaoManagerFactory.getDAOManagerCYN();

		try
		{
			if(ls_identificadorMensaje != null)
			{
				MensajeDAO lmd_DAO;
				Mensaje    lm_mensaje;

				lmd_DAO        = DaoCreator.getMensajeDAO(ldm_manager);
				lm_mensaje     = lmd_DAO.findById(ls_identificadorMensaje, false, true);

				if(CollectionUtils.isValidCollection(amsd_acuse) && (lm_mensaje != null))
				{
					Collection<String> lcs_direcciones;

					lcs_direcciones = amsd_acuse.keySet();

					if(CollectionUtils.isValidCollection(lcs_direcciones))
					{
						Date             ld_entrega;
						Iterator<String> lis_iterator;
						boolean          ab_coincidencia;

						ld_entrega          = null;
						lis_iterator        = lcs_direcciones.iterator();
						ab_coincidencia     = false;

						while(lis_iterator.hasNext() && !ab_coincidencia)
						{
							String ls_direccion;

							ls_direccion = lis_iterator.next();

							if(StringUtils.isValidString(ls_direccion))
							{
								if(ls_direccion.equalsIgnoreCase(lm_mensaje.getCorreoElectronico()))
								{
									ab_coincidencia     = true;
									ld_entrega          = amsd_acuse.get(ls_direccion);
								}
							}
						}

						{
							MensajeAcuseDetalle lmad_acuse;

							lmad_acuse = new MensajeAcuseDetalle();

							lmad_acuse.setGuiaCorrespondenciaFisica(
							    StringUtils.getString(
							        new SecureRandom().nextInt(NumericUtils.getInt(new Date().getTime()))
							    )
							);
							lmad_acuse.setFechaAcuseDetalle(ld_entrega);
							lmad_acuse.setFechaEnvioCorrespondencia(ld_entrega);

							lmad_acuse.setIdMensaje(lm_mensaje.getIdMensaje());
							lmad_acuse.setIdUsuarioCreacion(as_username);
							lmad_acuse.setIpCreacion(as_ipRemota);

							DaoCreator.getMensajeAcuseDetalleDAO(ldm_manager).insert(lmad_acuse);

							lm_mensaje.setIdEstado(
							    com.bachue.snr.prosnr16.common.constants.EstadoCommon.ACUSADO_RECIBIDO
							);
							lm_mensaje.setIdUsuarioModificacion(as_username);
							lm_mensaje.setIpModificacion(as_ipRemota);

							lmd_DAO.update(lm_mensaje);
						}
					}
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();
			clh_LOGGER.error("validarAcuseRecibidoElectronico", lb2be_e);

			escribirEnBitacoraProceso(
			    abpd_DAO, IdentificadoresCommon.ACUSE_RECIBIDO_ELECTRONICO, lb2be_e.getMessage(), as_username,
			    as_ipRemota, ls_identificadorMensaje
			);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}
	}

	private String getValor(Node an_nodo)
	    throws ParserConfigurationException, SAXException, IOException
	{
		StringBuilder lsb_valor;

		lsb_valor = new StringBuilder();

		if(an_nodo != null)
		{
			NodeList lnl_listaNodos;

			lnl_listaNodos = an_nodo.getChildNodes();

			if(lnl_listaNodos != null)
			{
				for(int li_i = 0, li_longitud = lnl_listaNodos.getLength(); li_i < li_longitud; li_i++)
				{
					Node ln_nodo;

					ln_nodo = lnl_listaNodos.item(li_i);

					if(ln_nodo != null)
					{
						short ls_tipoNodo;

						ls_tipoNodo = ln_nodo.getNodeType();

						if(ls_tipoNodo == Node.TEXT_NODE)
							lsb_valor.append(ln_nodo.getNodeValue());
					}
				}
			}
		}

		return StringUtils.getString(lsb_valor.toString());
	}

	private Map<String, Map<String, Date>> verificarAdjunto(String as_xml)
	    throws ParserConfigurationException, SAXException, IOException
	{
		Map<String, Date> lmsd_entregas;
		String            ls_networkId;

		ls_networkId      = null;
		lmsd_entregas     = null;

		if(as_xml != null)
		{
			Document ld_xml;

			ld_xml = DomHelper.getDocument(as_xml);

			if(ld_xml != null)
			{
				Node ln_recibo;

				ln_recibo = ld_xml.getFirstChild();

				if(ln_recibo != null)
				{
					NodeList lnl_listaNodos;

					lnl_listaNodos = ln_recibo.getChildNodes();

					if(lnl_listaNodos != null)
					{
						for(
						    int li_i = 0, li_longitud = lnl_listaNodos.getLength(), li_ajustados = 0;
							    (li_i < li_longitud) && (li_ajustados < 2); li_i++
						)
						{
							Node ln_nodo;

							ln_nodo = lnl_listaNodos.item(li_i);

							if(ln_nodo != null)
							{
								short ls_tipoNodo;

								ls_tipoNodo = ln_nodo.getNodeType();

								if(ls_tipoNodo == Node.ELEMENT_NODE)
								{
									String ls_nombreNodo;

									ls_nombreNodo = ln_nodo.getNodeName();

									if(StringUtils.isValidString(ls_nombreNodo))
									{
										if(ls_nombreNodo.endsWith(cs_ELEMENTO_NETWORK_ID))
										{
											ls_networkId = getValor(ln_nodo);

											if(StringUtils.isValidString(ls_networkId))
												ls_networkId = ls_networkId.replaceAll("&amp;", "&")
														                       .replaceAll("&lt;", "<")
														                       .replaceAll("&gt;", ">");

											li_ajustados++;
										}
										else if(ls_nombreNodo.endsWith(cs_ELEMENTO_DESTINOS))
										{
											lmsd_entregas = verificarDestinos(ln_nodo);
											li_ajustados++;
										}
									}
								}
							}
						}
					}
				}
			}
		}

		{
			Map<String, Map<String, Date>> lmsmsd_entregas;

			if(CollectionUtils.isValidCollection(lmsd_entregas) && (ls_networkId != null))
			{
				lmsmsd_entregas = new HashMap<String, Map<String, Date>>();

				lmsmsd_entregas.put(ls_networkId, lmsd_entregas);
			}
			else
				lmsmsd_entregas = null;

			return lmsmsd_entregas;
		}
	}

	private Map<String, Map<String, Date>> verificarBandejaEntrada(String as_jndiJavaMail)
	{
		Map<String, Map<String, Date>> lmsmsd_mensajes;

		lmsmsd_mensajes = new HashMap<String, Map<String, Date>>();

		try
		{
			if(as_jndiJavaMail != null)
			{
				SendMail lse_emailPorEnviar;

				lse_emailPorEnviar = new SendMail(as_jndiJavaMail, false, false, false);

				if(lse_emailPorEnviar != null)
				{
					Session ls_sesion;

					ls_sesion = lse_emailPorEnviar.getSession();

					if(ls_sesion != null)
					{
						Store  ls_almacen;
						String ls_clave;
						String ls_usuario;

						ls_clave       = ls_sesion.getProperty(MAIL_IMAP_PASSWORD);
						ls_usuario     = ls_sesion.getProperty(MAIL_IMAP_USER);
						ls_almacen     = ls_sesion.getStore(ls_sesion.getProperty(MAIL_STORE_TYPE));

						if(ls_almacen != null)
						{
							Folder lf_bandejaEntrada;
							Folder lf_leidos;

							ls_almacen.connect(ls_usuario, ls_clave);

							lf_bandejaEntrada     = ls_almacen.getFolder("INBOX");
							lf_leidos             = ls_almacen.getFolder("READED");

							if(lf_bandejaEntrada != null)
							{
								Message[] lma_mensajes;

								lf_bandejaEntrada.open(Folder.READ_WRITE);

								lma_mensajes = lf_bandejaEntrada.getMessages();

								if(lma_mensajes != null)
								{
									int li_mensajes = lma_mensajes.length;

									for(int li_mensaje = 0; li_mensaje < li_mensajes; li_mensaje++)
									{
										Message lm_mensaje;

										lm_mensaje = lma_mensajes[li_mensaje];

										if(lm_mensaje != null)
										{
											Map<String, Map<String, Date>> lmsmsd_mensaje;

											lmsmsd_mensaje = verificarMensaje(lm_mensaje);

											if(CollectionUtils.isValidCollection(lmsmsd_mensaje))
												lmsmsd_mensajes.putAll(lmsmsd_mensaje);
										}
									}

									if(lf_leidos != null)
									{
										if(!lf_leidos.exists())
											lf_leidos.create(Folder.HOLDS_MESSAGES);

										lf_bandejaEntrada.copyMessages(lma_mensajes, lf_leidos);
									}

									for(int li_mensaje = 0; li_mensaje < li_mensajes; li_mensaje++)
									{
										Message lm_mensaje;

										lm_mensaje = lma_mensajes[li_mensaje];

										if(lm_mensaje != null)
											lm_mensaje.setFlag(Flags.Flag.DELETED, true);
									}
								}

								lf_bandejaEntrada.expunge();
								lf_bandejaEntrada.close(false);
							}

							ls_almacen.close();
						}
					}
				}
			}
		}
		catch(Exception le_e)
		{
			clh_LOGGER.error("verificarBandejaEntrada", le_e);
		}

		if(!CollectionUtils.isValidCollection(lmsmsd_mensajes))
			lmsmsd_mensajes = null;

		return lmsmsd_mensajes;
	}

	private Map<String, Date> verificarDestino(Node an_destino)
	    throws ParserConfigurationException, SAXException, IOException
	{
		Date   ld_horaLocalEntrega;
		String ls_direccion;

		ld_horaLocalEntrega     = null;
		ls_direccion            = null;

		if(an_destino != null)
		{
			NodeList lnl_listaNodos;

			lnl_listaNodos = an_destino.getChildNodes();

			if(lnl_listaNodos != null)
			{
				for(
				    int li_i = 0, li_longitud = lnl_listaNodos.getLength(), li_ajustados = 0;
					    (li_i < li_longitud) && (li_ajustados < 2); li_i++
				)
				{
					Node ln_nodo;

					ln_nodo = lnl_listaNodos.item(li_i);

					if(ln_nodo != null)
					{
						short ls_tipoNodo;

						ls_tipoNodo = ln_nodo.getNodeType();

						if(ls_tipoNodo == Node.ELEMENT_NODE)
						{
							String ls_nombreNodo;

							ls_nombreNodo = ln_nodo.getNodeName();

							if(StringUtils.isValidString(ls_nombreNodo))
							{
								if(ls_nombreNodo.endsWith(cs_ELEMENTO_DIRECCION))
								{
									ls_direccion = getValor(ln_nodo);
									li_ajustados++;
								}
								else if(ls_nombreNodo.endsWith(cs_ELEMENTO_HORA_LOCAL_ENTREGA))
								{
									ld_horaLocalEntrega = DateUtils.getDate(getValor(ln_nodo), "dd/MM/yyyy hh:mm:ss a");
									li_ajustados++;
								}
							}
						}
					}
				}
			}
		}

		{
			Map<String, Date> lmsd_entrega;

			if((ld_horaLocalEntrega != null) && (ls_direccion != null))
			{
				lmsd_entrega = new HashMap<String, Date>();

				lmsd_entrega.put(ls_direccion, ld_horaLocalEntrega);
			}
			else
				lmsd_entrega = null;

			return lmsd_entrega;
		}
	}

	private Map<String, Date> verificarDestinos(Node an_destinos)
	    throws ParserConfigurationException, SAXException, IOException
	{
		Map<String, Date> lmsd_entregas;

		lmsd_entregas = new HashMap<String, Date>();

		if(an_destinos != null)
		{
			NodeList lnl_listaNodos;

			lnl_listaNodos = an_destinos.getChildNodes();

			if(lnl_listaNodos != null)
			{
				for(int li_i = 0, li_longitud = lnl_listaNodos.getLength(); li_i < li_longitud; li_i++)
				{
					Node ln_nodo;

					ln_nodo = lnl_listaNodos.item(li_i);

					if(ln_nodo != null)
					{
						short ls_tipoNodo;

						ls_tipoNodo = ln_nodo.getNodeType();

						if(ls_tipoNodo == Node.ELEMENT_NODE)
						{
							String ls_nombreNodo;

							ls_nombreNodo = ln_nodo.getNodeName();

							if(StringUtils.isValidString(ls_nombreNodo))
							{
								if(ls_nombreNodo.endsWith(cs_ELEMENTO_DESTINO))
								{
									Map<String, Date> lmsd_entrega;

									lmsd_entrega = verificarDestino(ln_nodo);

									if(CollectionUtils.isValidCollection(lmsd_entrega))
										lmsd_entregas.putAll(lmsd_entrega);
								}
							}
						}
					}
				}
			}
		}

		if(lmsd_entregas.isEmpty())
			lmsd_entregas = null;

		return lmsd_entregas;
	}

	private Map<String, Map<String, Date>> verificarMensaje(Message am_mensaje)
	    throws MessagingException, IOException, ParserConfigurationException, SAXException
	{
		Map<String, Map<String, Date>> lmsmsd_entregas;

		lmsmsd_entregas = new HashMap<String, Map<String, Date>>();

		if(am_mensaje != null)
		{
			String ls_tipoContenido;

			ls_tipoContenido = am_mensaje.getContentType();

			if((ls_tipoContenido != null) && ls_tipoContenido.contains("multipart"))
			{
				Multipart lm_parte;

				lm_parte = (Multipart)am_mensaje.getContent();

				for(int li_parte = 0, li_partes = lm_parte.getCount(); li_parte < li_partes; li_parte++)
				{
					Map<String, Map<String, Date>> lmsmsd_adjunto;

					lmsmsd_adjunto = verificarParte(lm_parte.getBodyPart(li_parte));

					if(CollectionUtils.isValidCollection(lmsmsd_adjunto))
						lmsmsd_entregas.putAll(lmsmsd_adjunto);
				}
			}
		}

		if(lmsmsd_entregas.isEmpty())
			lmsmsd_entregas = null;

		return lmsmsd_entregas;
	}

	private Map<String, Map<String, Date>> verificarParte(BodyPart abp_parte)
	    throws MessagingException, IOException, ParserConfigurationException, SAXException
	{
		Map<String, Map<String, Date>> lmsmsd_entregas;

		lmsmsd_entregas = null;

		if(abp_parte != null)
			lmsmsd_entregas = verificarParteMime((abp_parte instanceof MimeBodyPart) ? (MimeBodyPart)abp_parte : null);

		return lmsmsd_entregas;
	}

	private Map<String, Map<String, Date>> verificarParteMime(MimeBodyPart ambp_parte)
	    throws MessagingException, IOException, ParserConfigurationException, SAXException
	{
		Map<String, Map<String, Date>> lmsmsd_entregas;

		lmsmsd_entregas = null;

		if((ambp_parte != null) && Part.ATTACHMENT.equalsIgnoreCase(ambp_parte.getDisposition()))
		{
			String ls_nombreArchivoAdjunto;

			ls_nombreArchivoAdjunto = ambp_parte.getFileName();

			if((ls_nombreArchivoAdjunto != null) && ls_nombreArchivoAdjunto.equals("DeliveryReceipt.xml"))
			{
				StringBuilder lsb_xml;
				Reader        lr_lector;

				lsb_xml       = new StringBuilder();
				lr_lector     = new BufferedReader(
					    new InputStreamReader(
					        ambp_parte.getInputStream(), Charset.forName(StandardCharsets.UTF_8.name())
					    )
					);

				{
					int li_caracter = 0;

					while((li_caracter = lr_lector.read()) != -1)
						lsb_xml.append((char)li_caracter);
				}

				lmsmsd_entregas = verificarAdjunto(lsb_xml.toString());
			}
		}

		return lmsmsd_entregas;
	}
}
