package com.bachue.snr.prosnr16.business.cyn.motor.recepcion;

import co.gov.supernotariado.www.schemas.bachue.cn.mensajero.consultarenvio.v1.TipoDocumentos;
import co.gov.supernotariado.www.schemas.bachue.cn.mensajero.consultarenvio.v1.TipoEntradaConsultarEnvio;
import co.gov.supernotariado.www.schemas.bachue.cn.mensajero.consultarenvio.v1.TipoMensaje;
import co.gov.supernotariado.www.schemas.bachue.cn.mensajero.consultarenvio.v1.TipoSalidaConsultarEnvio;
import co.gov.supernotariado.www.schemas.bachue.cn.mensajero.enviarmensaje.v1.TipoDestinatarioEMI;
import co.gov.supernotariado.www.schemas.bachue.cn.mensajero.enviarmensaje.v1.TipoDocumento;
import co.gov.supernotariado.www.schemas.bachue.cn.mensajero.enviarmensaje.v1.TipoEntradaEnviarMensaje;
import co.gov.supernotariado.www.schemas.bachue.cn.mensajero.enviarmensaje.v1.TipoSalidaEnviarMensaje;
import co.gov.supernotariado.www.schemas.bachue.cn.mensajero.enviarmensaje.v1.TipoVariableEMI;

import com.b2bsg.common.dataAccess2.DAOManager;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.logging.LoggerHandler;

import com.b2bsg.common.util.CollectionUtils;
import com.b2bsg.common.util.NumericUtils;
import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.business.BaseBusiness;

import com.bachue.snr.prosnr01.common.constants.CodigoMensajeCommon;
import com.bachue.snr.prosnr01.common.constants.DescripcionMensajeCommon;
import com.bachue.snr.prosnr01.common.constants.IdentificadoresCommon;
import com.bachue.snr.prosnr01.common.constants.ProyectosCommon;
import com.bachue.snr.prosnr01.common.utils.ExpresionRegular;

import com.bachue.snr.prosnr01.dao.DaoCreator;
import com.bachue.snr.prosnr01.dao.DaoManagerFactory;
import com.bachue.snr.prosnr01.dao.acc.MensajeAdjuntoDAO;
import com.bachue.snr.prosnr01.dao.acc.MensajeDAO;
import com.bachue.snr.prosnr01.dao.acc.ParametrosMensajeDAO;

import com.bachue.snr.prosnr16.common.constants.CanalCommon;
import com.bachue.snr.prosnr16.common.constants.ClasificacionCommon;
import com.bachue.snr.prosnr16.common.constants.ConstanteCommon;
import com.bachue.snr.prosnr16.common.constants.ErrorKeys;
import com.bachue.snr.prosnr16.common.constants.EstadoCommon;
import com.bachue.snr.prosnr16.common.constants.TipoIdentificacionCommon;
import com.bachue.snr.prosnr16.common.constants.TipoParametroCommon;

import com.bachue.snr.prosnr16.model.sdb.acc.Mensaje;
import com.bachue.snr.prosnr16.model.sdb.acc.MensajeAdjunto;
import com.bachue.snr.prosnr16.model.sdb.acc.ParametrosMensaje;
import com.bachue.snr.prosnr16.model.sdb.pgn.Canal;
import com.bachue.snr.prosnr16.model.sdb.pgn.Origen;
import com.bachue.snr.prosnr16.model.sdb.pgn.Plantilla;

import java.math.BigInteger;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;


/**
 * Clase que contiene todos las propiedades MensajeroBussines.
 *
 * @author  Julián David Vaca Rodriguez
 * Fecha de Creacion: 25/03/2020
 */
public class MotorRecepcionBusiness extends BaseBusiness
{
	/** Constante clh_LOGGER. */
	private static final LoggerHandler clh_LOGGER = (LoggerHandler)com.bachue.snr.prosnr01.common.utils.LoggingHelper
			.getLogger(MotorRecepcionBusiness.class, ProyectosCommon.COMUNICACIONES_Y_NOTIFICACIONES_16);

	/**
	 * El canal originador se encarga de enviar la información necesaria hacia el componente para notificar o comunicar al destinatario.
	 *
	 * @param atecdr_entrada de TipoEntradaConsultarEnvio
	 * @param as_userId Argumento de tipo <code>String</code> que contiene el usuario que realiza la operación.
	 * @param as_localIpAddress Argumento de tipo <code>String</code> que contiene la ip local desde donde se realiza la operación.
	 * @param as_remoteIpAddress Argumento de tipo <code>String</code> que contiene la ip remota desde donde se realiza la operación.
	 * @return el valor de tipo salida consultar envio
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public synchronized TipoSalidaConsultarEnvio consultarEnvio(
	    TipoEntradaConsultarEnvio atecdr_entrada, String as_userId, String as_localIpAddress, String as_remoteIpAddress
	)
	    throws B2BException
	{
		String                   ls_descripcionMensaje;
		DAOManager               ldm_manager;
		TipoSalidaConsultarEnvio ltsce_sce;
		BigInteger               lbi_codigoMensaje;

		ls_descripcionMensaje     = DescripcionMensajeCommon.EXITO;
		ldm_manager               = DaoManagerFactory.getDAOManagerCYN();
		ltsce_sce                 = null;
		lbi_codigoMensaje         = BigInteger.valueOf(CodigoMensajeCommon.CODIGO_200);

		try
		{
			if(atecdr_entrada != null)
			{
				Date       ld_fechaInicio;
				Date       ld_fechaFin;
				String     ls_tipoIdentificador;
				String     ls_identificador;
				String     ls_pagina;
				String     ls_correoElectronico;
				String     ls_telefono;
				boolean    lb_tipoIdentificadorTurno;
				MensajeDAO lmd_DAO;

				ls_tipoIdentificador          = StringUtils.getString(atecdr_entrada.getTipoID());
				ls_identificador              = StringUtils.getString(atecdr_entrada.getIdentificador());
				ls_pagina                     = StringUtils.getString(atecdr_entrada.getPagina());
				ls_correoElectronico          = StringUtils.getString(
					    atecdr_entrada.getDireccionCorreoElectronicoDestinatario()
					);
				ls_telefono                   = null;
				lb_tipoIdentificadorTurno     = false;
				lmd_DAO                       = DaoCreator.getMensajeDAO(ldm_manager);

				{
					ld_fechaInicio     = obtenerDateDesdeCalendar(atecdr_entrada.getFechaInicial());
					ld_fechaFin        = obtenerDateDesdeCalendar(atecdr_entrada.getFechaFinal());

					if(!((ld_fechaInicio != null) && ld_fechaInicio.before(new Date())))
						throw new B2BException(addErrorCYN(ErrorKeys.ERROR_FECHA_INICIAL_INVALIDA));

					if(!((ld_fechaFin != null) && ld_fechaFin.after(ld_fechaInicio)))
						throw new B2BException(addErrorCYN(ErrorKeys.ERROR_FECHA_FINAL_INVALIDA));
				}

				if(
				    StringUtils.isValidString(ls_tipoIdentificador)
					    && !(ls_tipoIdentificador.equals(TipoIdentificacionCommon.NIR)
					    || ls_tipoIdentificador.equals(TipoIdentificacionCommon.TURNO))
				)
					throw new B2BException(addErrorCYN(ErrorKeys.ERROR_TIPO_IDENTIFICADOR_INVALIDO));

				if(StringUtils.isValidString(ls_identificador) && !(StringUtils.isValidString(ls_tipoIdentificador)))
					throw new B2BException(addErrorCYN(ErrorKeys.ERROR_TIPO_IDENTIFICADOR_INVALIDO));

				if(StringUtils.isValidString(ls_tipoIdentificador) && !(StringUtils.isValidString(ls_identificador)))
					throw new B2BException(addErrorCYN(ErrorKeys.ERROR_IDENTIFICADOR_INVALIDO));

				if(StringUtils.isValidString(ls_tipoIdentificador))
				{
					lb_tipoIdentificadorTurno = ls_tipoIdentificador.equals(TipoIdentificacionCommon.TURNO);

					int li_contador;

					li_contador = lmd_DAO.findCountNirOIdTurno(lb_tipoIdentificadorTurno, ls_identificador);

					if(li_contador <= 0)
						throw new B2BException(addErrorCYN(ErrorKeys.ERROR_IDENTIFICADOR_INVALIDO));
				}

				if(!StringUtils.isValidString(ls_pagina) || !StringUtils.isNumeric(ls_pagina))
					throw new B2BException(addErrorCYN(ErrorKeys.ERROR_NUMERO_PAGINA_INVALIDO));

				if(!StringUtils.isValidString(ls_correoElectronico) || !validarCorreoElectronico(ls_correoElectronico))
					throw new B2BException(addErrorCYN(ErrorKeys.ERROR_CORREO_INVALIDO));

				{
					BigInteger lbi_telefono;
					boolean    lb_telefonoValido;

					lbi_telefono          = atecdr_entrada.getNumeroTelefonoDestinatario();
					lb_telefonoValido     = false;

					if(NumericUtils.isValidBigInteger(lbi_telefono))
					{
						ls_telefono     = lbi_telefono.toString();

						lb_telefonoValido = StringUtils.isValidString(ls_telefono)
								&& ExpresionRegular.getExpresionRegular().esTelefono(ls_telefono);
					}

					if(!lb_telefonoValido)
						throw new B2BException(addErrorCYN(ErrorKeys.ERROR_TELEFONO_INVALIDO));
				}

				{
					List<Object> llo_resultadosPaginados;
					int          li_numeroRegistrosPorPagina;

					li_numeroRegistrosPorPagina = 0;

					{
						long ll_entero;

						ll_entero = DaoCreator.getConstantesDAO(ldm_manager)
								                  .findEnteroById(ConstanteCommon.NUMERO_REGISTROS_POR_PAGINA);

						if(ll_entero > NumericUtils.DEFAULT_LONG_VALUE)
							li_numeroRegistrosPorPagina = NumericUtils.getInt(ll_entero);
						else
						{
							Object[] loa_messageArgs;

							loa_messageArgs        = new String[1];
							loa_messageArgs[0]     = ConstanteCommon.NUMERO_REGISTROS_POR_PAGINA;

							throw new B2BException(addErrorCYN(ErrorKeys.ERROR_SIN_CONSTANTE, loa_messageArgs));
						}
					}

					llo_resultadosPaginados = DaoCreator.getMensajeDAO(ldm_manager)
							                                .findConsultarMensajes(
							    ld_fechaInicio, ld_fechaFin, lb_tipoIdentificadorTurno, ls_identificador,
							    ls_correoElectronico, ls_telefono, NumericUtils.getInt(ls_pagina),
							    li_numeroRegistrosPorPagina
							);

					if(CollectionUtils.isValidCollection(llo_resultadosPaginados))
					{
						BigInteger    lbi_numeroRegistros;
						TipoMensaje[] ltscelm_listaMensajes;

						lbi_numeroRegistros       = BigInteger.ZERO;
						ltscelm_listaMensajes     = null;

						if(llo_resultadosPaginados.size() > 1)
						{
							Collection<Mensaje> lcm_mensajes;

							lbi_numeroRegistros     = (BigInteger)llo_resultadosPaginados.get(0);
							lcm_mensajes            = (Collection<Mensaje>)llo_resultadosPaginados.get(1);

							if(CollectionUtils.isValidCollection(lcm_mensajes))
							{
								int li_contador;

								ltscelm_listaMensajes     = new TipoMensaje[lcm_mensajes.size()];
								li_contador               = 0;

								for(Mensaje lm_actual : lcm_mensajes)
								{
									if(lm_actual != null)
									{
										Plantilla lp_plantilla;
										String    ls_idMensaje;
										String    ls_clasificacion;
										String    ls_canal;

										ls_clasificacion     = StringUtils.getStringNotNull(
											    lm_actual.getClasificacion()
											);
										ls_canal             = StringUtils.getStringNotNull(lm_actual.getIdCanal());
										ls_idMensaje         = lm_actual.getIdMensaje();
										lp_plantilla         = DaoCreator.getPlantillaDAO(ldm_manager)
												                             .findById(lm_actual.getIdPlantilla());

										if(lp_plantilla != null)
										{
											TipoDocumentos[]           ltd_documentos;
											Collection<MensajeAdjunto> lcma_mensajeAdjunto;

											ltd_documentos     = null;

											lcma_mensajeAdjunto = DaoCreator.getMensajeAdjuntoDAO(ldm_manager)
													                            .findAllByIdMensaje(ls_idMensaje);
											llenarAsuntoCuerpoPlantilla(
											    ls_idMensaje,
											    ls_clasificacion.equalsIgnoreCase(ClasificacionCommon.NOTIFICACION),
											    lp_plantilla, ldm_manager
											);

											if(CollectionUtils.isValidCollection(lcma_mensajeAdjunto))
											{
												int li_contadorDocumentos;

												li_contadorDocumentos     = 0;
												ltd_documentos            = new TipoDocumentos[lcma_mensajeAdjunto.size()];

												for(MensajeAdjunto lma_tmp : lcma_mensajeAdjunto)
												{
													if(lma_tmp != null)
														ltd_documentos[li_contadorDocumentos++] = new TipoDocumentos(
															    lma_tmp.getDId(), lma_tmp.getDocName()
															);
												}
											}

											ltscelm_listaMensajes[li_contador++] = new TipoMensaje(
												    lm_actual.getNir(), lm_actual.getIdTurno(), lm_actual.getIdCirculo(),
												    lm_actual.getIdentificadorEE(), lm_actual.getFechaEnvio(),
												    lp_plantilla.getAsunto(), lp_plantilla.getCuerpo(),
												    ls_canal.equalsIgnoreCase(CanalCommon.ELECTRONICO)
												    ? lm_actual.getCorreoElectronico() : lm_actual.getNumeroCelular(),
												    ltd_documentos
												);
										}
									}
								}
							}
						}
						else
							throw new B2BException(addErrorCYN(ErrorKeys.ERROR_SIN_REGISTROS_INVALIDO));

						ltsce_sce = new TipoSalidaConsultarEnvio(
							    lbi_numeroRegistros, ltscelm_listaMensajes, lbi_codigoMensaje, ls_descripcionMensaje
							);
					}
					else
						throw new B2BException(addErrorCYN(ErrorKeys.ERROR_SIN_REGISTROS_INVALIDO));
				}
			}
			else
				throw new B2BException(addErrorCYN(ErrorKeys.ERROR_OPERACION_NO_EXITOSA));
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();
			clh_LOGGER.error("consultarEnvio", lb2be_e);

			Map<String, String> lmss_codigoMensaje;

			lmss_codigoMensaje     = obtenerErrorServicios(lb2be_e.getMessageKey());
			ltsce_sce              = null;

			lbi_codigoMensaje         = NumericUtils.getBigInteger(
				    lmss_codigoMensaje.get(IdentificadoresCommon.CODIGO_MENSAJE)
				);
			ls_descripcionMensaje     = lmss_codigoMensaje.get(IdentificadoresCommon.DESCRIPCION_MENSAJE);
		}
		catch(Exception le_e)
		{
			ldm_manager.setRollbackOnly();
			clh_LOGGER.error("consultarEnvio", le_e);

			ltsce_sce                 = null;
			lbi_codigoMensaje         = BigInteger.valueOf(CodigoMensajeCommon.CODIGO_500);
			ls_descripcionMensaje     = le_e.getMessage();
		}
		finally
		{
			ldm_manager.close();
		}

		if(ltsce_sce == null)
			ltsce_sce = new TipoSalidaConsultarEnvio(lbi_codigoMensaje, ls_descripcionMensaje);

		return ltsce_sce;
	}

	/**
	 * El canal originador se encarga de enviar la información necesaria hacia el componente para notificar o comunicar al destinatario.
	 *
	 * @param ateem_entrada correspondiente al valor de ateem entrada
	 * @param as_userId Argumento de tipo <code>String</code> que contiene el usuario que realiza la operación.
	 * @param as_localIpAddress Argumento de tipo <code>String</code> que contiene la ip local desde donde se realiza la operación.
	 * @param as_remoteIpAddress Argumento de tipo <code>String</code> que contiene la ip remota desde donde se realiza la operación.
	 * @return el valor de tipo salida enviar mensaje
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public synchronized TipoSalidaEnviarMensaje enviarMensaje(
	    TipoEntradaEnviarMensaje ateem_entrada, String as_userId, String as_localIpAddress, String as_remoteIpAddress
	)
	    throws B2BException
	{
		String                  ls_descripcionMensaje;
		DAOManager              ldm_manager;
		TipoSalidaEnviarMensaje ltsem_sem;
		BigInteger              lbi_codigoMensaje;

		ls_descripcionMensaje     = DescripcionMensajeCommon.EXITO;
		ldm_manager               = DaoManagerFactory.getDAOManagerCYN();
		ltsem_sem                 = null;
		lbi_codigoMensaje         = BigInteger.valueOf(CodigoMensajeCommon.CODIGO_200);

		try
		{
			if(ateem_entrada != null)
			{
				Mensaje                       lm_recepcion;
				Collection<MensajeAdjunto>    lcma_adjuntosRecepcion;
				Collection<ParametrosMensaje> lcpm_parametrosRecepcion;
				String                        ls_canalNombre;
				String                        ls_clasificacion;
				String                        ls_canal;

				ls_clasificacion             = StringUtils.getStringUpperCase(ateem_entrada.getClasificacion());
				ls_canal                     = StringUtils.getStringUpperCase(ateem_entrada.getCanal());
				lm_recepcion                 = new Mensaje();
				ls_canalNombre               = null;
				lcma_adjuntosRecepcion       = null;
				lcpm_parametrosRecepcion     = null;

				{
					String ls_tmp;

					ls_tmp = ateem_entrada.getModulo();

					if(StringUtils.isValidString(ls_tmp))
					{
						Origen lo_origen;

						lo_origen = DaoCreator.getOrigenDAO(ldm_manager).findById(ls_tmp);

						if(lo_origen != null)
							lm_recepcion.setIdOrigen(ls_tmp);
						else
							throw new B2BException(addErrorCYN(ErrorKeys.ERROR_MODULO_ENCONTRADO_NO_EXISTE));
					}
					else
						throw new B2BException(addErrorCYN(ErrorKeys.ERROR_MODULO_INVALIDO));
				}

				{
					if(StringUtils.isValidString(ls_clasificacion))
					{
						if(
						    ls_clasificacion.equalsIgnoreCase(ClasificacionCommon.COMUNICACION)
							    || ls_clasificacion.equalsIgnoreCase(ClasificacionCommon.NOTIFICACION)
						)
							lm_recepcion.setClasificacion(ls_clasificacion);
						else
							throw new B2BException(addErrorCYN(ErrorKeys.ERROR_CLASIFICACION_INVALIDO));
					}
					else
						throw new B2BException(addErrorCYN(ErrorKeys.ERROR_CLASIFICACION_NO_VALIDA));

					if(StringUtils.isValidString(ls_canal))
					{
						Canal lc_canal;

						lc_canal = DaoCreator.getCanalDAO(ldm_manager).findById(ls_canal);

						if(lc_canal != null)
						{
							ls_canalNombre = lc_canal.getNombre();

							if(ls_clasificacion.equalsIgnoreCase(ClasificacionCommon.COMUNICACION))
								lm_recepcion.setIdCanal(ls_canal);
							else
							{
								if(!ls_canal.equalsIgnoreCase(CanalCommon.SMS))
									lm_recepcion.setIdCanal(ls_canal);
								else
								{
									Object[] loa_messageArgs;

									loa_messageArgs        = new String[2];
									loa_messageArgs[0]     = ls_clasificacion;
									loa_messageArgs[1]     = ls_canalNombre;

									throw new B2BException(
									    addErrorCYN(ErrorKeys.ERROR_CLASIFICACION_CANAL_NO_VALIDA, loa_messageArgs)
									);
								}
							}
						}
						else
							throw new B2BException(addErrorCYN(ErrorKeys.ERROR_CANAL_ENCONTRADO_NO_EXISTE));
					}
					else
						throw new B2BException(addErrorCYN(ErrorKeys.ERROR_CANAL_INVALIDO));
				}

				{
					String ls_tmp;

					ls_tmp = ateem_entrada.getIdentificadorEE();

					if(StringUtils.isValidString(ls_tmp))
						lm_recepcion.setIdentificadorEE(ls_tmp);
					else if(
					    StringUtils.isValidString(ls_canal)
						    && ((ls_clasificacion.equalsIgnoreCase(ClasificacionCommon.COMUNICACION)
						    && ls_canal.equalsIgnoreCase(CanalCommon.FISICO))
						    || (!ls_clasificacion.equalsIgnoreCase(ClasificacionCommon.COMUNICACION)
						    && !ls_canal.equalsIgnoreCase(CanalCommon.SMS)))
					)
						throw new B2BException(addErrorCYN(ErrorKeys.ERROR_IDENTIFICADOR_EE_INVALIDO));
				}

				{
					String ls_tmp;

					ls_tmp = ateem_entrada.getOrip();

					if(StringUtils.isValidString(ls_tmp))
						lm_recepcion.setIdCirculo(ls_tmp);
				}

				{
					String ls_tmp;

					ls_tmp = ateem_entrada.getNir();

					if(StringUtils.isValidString(ls_tmp))
						lm_recepcion.setNir(ls_tmp);
				}

				{
					String ls_tmp;

					ls_tmp = ateem_entrada.getTurno();

					if(StringUtils.isValidString(ls_tmp))
						lm_recepcion.setIdTurno(ls_tmp);
				}

				{
					TipoDestinatarioEMI ltd_destinatario;

					ltd_destinatario = ateem_entrada.getDestinatario();

					if(ltd_destinatario != null)
					{
						String     ls_fisico;
						String     ls_electronico;
						BigInteger lbi_telefono;

						ls_fisico          = ltd_destinatario.getDireccionCorreoFisicoDestinatario();
						ls_electronico     = ltd_destinatario.getDireccionCorreoElectronicoDestinatario();
						lbi_telefono       = ltd_destinatario.getNumeroTelefonoDestinatario();

						if(StringUtils.isValidString(ls_canal))
						{
							switch(ls_canal)
							{
								case CanalCommon.FISICO:
								{
									if(
									    StringUtils.isValidString(ls_fisico)
										    && (!StringUtils.isValidString(ls_electronico))
										    && (!NumericUtils.isValidBigInteger(lbi_telefono))
									)
										lm_recepcion.setDireccionCorrespondencia(ls_fisico);
									else
									{
										Object[] loa_messageArgs;

										loa_messageArgs        = new String[1];
										loa_messageArgs[0]     = ls_canalNombre;

										throw new B2BException(
										    addErrorCYN(
										        ErrorKeys.ERROR_CANAL_DIRECCION_FISICA_INVALIDO, loa_messageArgs
										    )
										);
									}

									break;
								}

								case CanalCommon.ELECTRONICO:
								{
									if(
									    (!StringUtils.isValidString(ls_fisico))
										    && (StringUtils.isValidString(ls_electronico))
										    && (!NumericUtils.isValidBigInteger(lbi_telefono))
									)
									{
										if(validarCorreoElectronico(ls_electronico))
											lm_recepcion.setCorreoElectronico(ls_electronico);
										else
											throw new B2BException(
											    addErrorCYN(ErrorKeys.ERROR_FORMATO_CORREO_INVALIDO)
											);
									}
									else
									{
										Object[] loa_messageArgs;

										loa_messageArgs        = new String[1];
										loa_messageArgs[0]     = ls_canalNombre;

										throw new B2BException(
										    addErrorCYN(
										        ErrorKeys.ERROR_CANAL_CORREO_ELECTRONICO_INVALIDO, loa_messageArgs
										    )
										);
									}

									break;
								}

								case CanalCommon.SMS:
								{
									if(
									    (!StringUtils.isValidString(ls_fisico))
										    && (!StringUtils.isValidString(ls_electronico))
										    && (NumericUtils.isValidBigInteger(lbi_telefono))
									)
									{
										String ls_telefono;

										ls_telefono = lbi_telefono.toString();

										if(
										    ExpresionRegular.getExpresionRegular().esTelefono(ls_telefono)
											    && (ls_telefono.length() == 10)
										)
											lm_recepcion.setNumeroCelular(ls_telefono);
										else
											throw new B2BException(addErrorCYN(ErrorKeys.ERROR_FORMATO_NUMERO_CELULAR));
									}
									else
									{
										Object[] loa_messageArgs;

										loa_messageArgs        = new String[1];
										loa_messageArgs[0]     = ls_canalNombre;

										throw new B2BException(
										    addErrorCYN(ErrorKeys.ERROR_CANAL_NUMERO_CELUAR_INVALIDO, loa_messageArgs)
										);
									}

									break;
								}

								default:
									break;
							}
						}
					}
					else
						throw new B2BException(addErrorCYN(ErrorKeys.ERROR_DESTINATARIO_INVALIDO));
				}

				{
					boolean         lb_validarDocumentos;
					TipoDocumento[] ltd_documentos;

					ltd_documentos           = ateem_entrada.getDocumentos();
					lb_validarDocumentos     = (StringUtils.isValidString(ls_canal)
							&& StringUtils.isValidString(ls_clasificacion)
							&& ls_canal.equalsIgnoreCase(CanalCommon.ELECTRONICO)
							&& ls_clasificacion.equalsIgnoreCase(ClasificacionCommon.NOTIFICACION));

					if(CollectionUtils.isValidCollection(ltd_documentos))
					{
						int li_contador;

						lcma_adjuntosRecepcion     = new ArrayList<MensajeAdjunto>();
						li_contador                = 0;

						for(TipoDocumento ltd_actual : ltd_documentos)
						{
							if(ltd_actual != null)
							{
								BigInteger lbi_dID;
								String     ls_docName;

								lbi_dID        = ltd_actual.getDID();
								ls_docName     = StringUtils.getString(ltd_actual.getDDocName());
								li_contador++;

								if(
								    NumericUtils.isValidBigInteger(lbi_dID, BigInteger.valueOf(1L))
									    && StringUtils.isValidString(ls_docName)
								)
									lcma_adjuntosRecepcion.add(new MensajeAdjunto(lbi_dID.toString(), ls_docName));
								else
								{
									Object[] loa_messageArgs;

									loa_messageArgs        = new String[1];
									loa_messageArgs[0]     = String.valueOf(li_contador);

									throw new B2BException(
									    addErrorCYN(ErrorKeys.ERROR_ADJUNTO_INVALIDO, loa_messageArgs)
									);
								}
							}
						}
					}
					else if(lb_validarDocumentos)
					{
						Object[] loa_messageArgs;

						loa_messageArgs        = new String[2];
						loa_messageArgs[0]     = ls_canalNombre;
						loa_messageArgs[1]     = ls_clasificacion;

						throw new B2BException(
						    addErrorCYN(ErrorKeys.ERROR_CANAL_CLASIFICACION_ADJUNTO_OBLIGATORIOS, loa_messageArgs)
						);
					}
				}

				{
					TipoVariableEMI[] ltva_variables;

					ltva_variables = ateem_entrada.getVariables();

					if(CollectionUtils.isValidCollection(ltva_variables))
					{
						int li_contador;

						li_contador                  = 1;
						lcpm_parametrosRecepcion     = new ArrayList<ParametrosMensaje>();

						for(TipoVariableEMI ltv_actual : ltva_variables)
						{
							if(ltv_actual != null)
							{
								String ls_codigo;
								String ls_criterio;
								String ls_tipo;

								ls_codigo       = StringUtils.getString(ltv_actual.getCodigo());
								ls_criterio     = StringUtils.getString(ltv_actual.getCriterio());
								ls_tipo         = StringUtils.getString(ltv_actual.getTipo());

								if(!StringUtils.isValidString(ls_codigo))
								{
									Object[] loa_messageArgs;

									loa_messageArgs        = new String[1];
									loa_messageArgs[0]     = String.valueOf(li_contador);

									throw new B2BException(
									    addErrorCYN(ErrorKeys.ERROR_POSICION_CODIGO_INVALIDO, loa_messageArgs)
									);
								}

								if(!StringUtils.isValidString(ls_criterio))
								{
									Object[] loa_messageArgs;

									loa_messageArgs        = new String[1];
									loa_messageArgs[0]     = String.valueOf(li_contador);

									throw new B2BException(
									    addErrorCYN(ErrorKeys.ERROR_POSICION_CRITERIO_INVALIDO, loa_messageArgs)
									);
								}

								if(
								    !StringUtils.isValidString(ls_tipo)
									    || !(ls_tipo.equalsIgnoreCase(TipoParametroCommon.ASUNTO)
									    || ls_tipo.equalsIgnoreCase(TipoParametroCommon.CUERPO))
								)
								{
									Object[] loa_messageArgs;

									loa_messageArgs        = new String[1];
									loa_messageArgs[0]     = String.valueOf(li_contador);

									throw new B2BException(
									    addErrorCYN(ErrorKeys.ERROR_POSICION_TIPO_INVALIDO, loa_messageArgs)
									);
								}

								lcpm_parametrosRecepcion.add(new ParametrosMensaje(ls_codigo, ls_criterio, ls_tipo));
								li_contador++;
							}
						}
					}
					else if(StringUtils.isValidString(ls_canal) && !ls_canal.equalsIgnoreCase(CanalCommon.FISICO))
						throw new B2BException(addErrorCYN(ErrorKeys.ERROR_NO_VARIABLES_VALIDAS));
				}

				{
					String ls_tmp;

					ls_tmp = ateem_entrada.getPlantilla();

					if(StringUtils.isValidString(ls_tmp))
					{
						Plantilla lp_plantilla;

						lp_plantilla = DaoCreator.getPlantillaDAO(ldm_manager).findById(ls_tmp);

						if(lp_plantilla != null)
							lm_recepcion.setIdPlantilla(ls_tmp);
						else
							throw new B2BException(addErrorCYN(ErrorKeys.ERROR_PLANTILLA_ENCONTRADO_NO_EXISTE));
					}
					else if(StringUtils.isValidString(ls_canal) && !ls_canal.equalsIgnoreCase(CanalCommon.FISICO))
						throw new B2BException(addErrorCYN(ErrorKeys.ERROR_PLANTILLA_INVALIDO));
				}

				{
					String ls_idMensaje;

					ls_idMensaje = null;

					lm_recepcion.setIdEstado(EstadoCommon.RECIBIDO);
					lm_recepcion.setIdUsuarioCreacion(as_userId);
					lm_recepcion.setIpCreacion(as_remoteIpAddress);

					lm_recepcion = DaoCreator.getMensajeDAO(ldm_manager).insert(lm_recepcion);

					if(lm_recepcion != null)
					{
						MensajeAdjuntoDAO    lmad_DAO;
						ParametrosMensajeDAO lpmd_DAO;

						lmad_DAO     = DaoCreator.getMensajeAdjuntoDAO(ldm_manager);
						lpmd_DAO     = DaoCreator.getParametrosMensajeDAO(ldm_manager);

						ls_idMensaje = lm_recepcion.getIdMensaje();

						if(StringUtils.isValidString(ls_idMensaje))
						{
							if(CollectionUtils.isValidCollection(lcma_adjuntosRecepcion))
							{
								int li_contador;

								li_contador = 1;

								for(MensajeAdjunto lma_actual : lcma_adjuntosRecepcion)
								{
									if(lma_actual != null)
									{
										lma_actual.setIdMensaje(ls_idMensaje);
										lma_actual.setIdAdjunto(String.valueOf(li_contador++));
										lma_actual.setIdUsuarioCreacion(as_userId);
										lma_actual.setIpCreacion(as_remoteIpAddress);

										lmad_DAO.insert(lma_actual);
									}
								}
							}

							if(CollectionUtils.isValidCollection(lcpm_parametrosRecepcion))
							{
								int li_contador;

								li_contador = 1;

								for(ParametrosMensaje lpm_actual : lcpm_parametrosRecepcion)
								{
									if(lpm_actual != null)
									{
										lpm_actual.setIdMensaje(ls_idMensaje);
										lpm_actual.setIdParametro(String.valueOf(li_contador++));
										lpm_actual.setIdUsuarioCreacion(as_userId);
										lpm_actual.setIpCreacion(as_remoteIpAddress);

										lpmd_DAO.insert(lpm_actual);
									}
								}
							}

							ltsem_sem = new TipoSalidaEnviarMensaje(
								    obtenerCalendarDesdeDate(new Date()), ls_idMensaje, lbi_codigoMensaje,
								    ls_descripcionMensaje
								);
						}
					}
				}
			}
			else
				throw new B2BException(addErrorCYN(ErrorKeys.ERROR_OPERACION_NO_EXITOSA));
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();
			clh_LOGGER.error("enviarMensaje", lb2be_e);

			Map<String, String> lmss_codigoMensaje;

			lmss_codigoMensaje     = obtenerErrorServicios(lb2be_e.getMessageKey());

			lbi_codigoMensaje         = NumericUtils.getBigInteger(
				    lmss_codigoMensaje.get(IdentificadoresCommon.CODIGO_MENSAJE)
				);
			ls_descripcionMensaje     = lmss_codigoMensaje.get(IdentificadoresCommon.DESCRIPCION_MENSAJE);
		}
		catch(Exception le_e)
		{
			ldm_manager.setRollbackOnly();
			clh_LOGGER.error("enviarMensaje", le_e);

			lbi_codigoMensaje         = BigInteger.valueOf(CodigoMensajeCommon.CODIGO_500);
			ls_descripcionMensaje     = le_e.getMessage();
		}
		finally
		{
			ldm_manager.close();
		}

		if(ltsem_sem == null)
			ltsem_sem = new TipoSalidaEnviarMensaje(null, null, lbi_codigoMensaje, ls_descripcionMensaje);

		return ltsem_sem;
	}
}
