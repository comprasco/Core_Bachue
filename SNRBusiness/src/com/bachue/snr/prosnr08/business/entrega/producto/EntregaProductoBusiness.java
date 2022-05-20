package com.bachue.snr.prosnr08.business.entrega.producto;

import co.gov.supernotariado.www.schemas.bachue.cb.entregaproducto.obtenerproducto.v1.TipoEntradaObtenerProducto;
import co.gov.supernotariado.www.schemas.bachue.cb.entregaproducto.obtenerrecibocaja.v1.TipoEntradaObtenerReciboCaja;
import co.gov.supernotariado.www.schemas.bachue.cb.entregaproducto.obtenerturnosrefpago.v1.TipoEntradaObtenerTurnosRefPago;
import co.gov.supernotariado.www.schemas.bachue.cb.entregaproducto.registrarentregaproducto.v1.TipoEntradaRegistrarEntregaProducto;
import co.gov.supernotariado.www.schemas.bachue.cb.entregaproducto.verificarproducto.v1.TipoEntradaVerificarProducto;

import com.b2bsg.common.dataAccess2.DAOManager;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.logging.LoggerHandler;

import com.b2bsg.common.util.BooleanUtils;
import com.b2bsg.common.util.CollectionUtils;
import com.b2bsg.common.util.NumericUtils;
import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.business.BaseBusiness;

import com.bachue.snr.prosnr01.common.constants.EstadoCommon;
import com.bachue.snr.prosnr01.common.constants.MotivoTramiteCommon;
import com.bachue.snr.prosnr01.common.constants.ProcedenciaCommon;
import com.bachue.snr.prosnr01.common.constants.ProcesoCommon;
import com.bachue.snr.prosnr01.common.constants.ProyectosCommon;
import com.bachue.snr.prosnr01.common.constants.TipoArchivoCommon;
import com.bachue.snr.prosnr01.common.constants.TipoDocumentalCommon;

import com.bachue.snr.prosnr01.dao.DaoCreator;
import com.bachue.snr.prosnr01.dao.DaoManagerFactory;
import com.bachue.snr.prosnr01.dao.acc.DocumentosSalidaDAO;

import com.bachue.snr.prosnr01.model.sdb.acc.DocumentosSalida;
import com.bachue.snr.prosnr01.model.sdb.acc.Liquidacion;
import com.bachue.snr.prosnr01.model.sdb.acc.Solicitud;
import com.bachue.snr.prosnr01.model.sdb.acc.Subproceso;
import com.bachue.snr.prosnr01.model.sdb.acc.SubprocesoVersion;
import com.bachue.snr.prosnr01.model.sdb.acc.Turno;
import com.bachue.snr.prosnr01.model.sdb.acc.TurnoHistoria;
import com.bachue.snr.prosnr01.model.sdb.bng.Imagenes;
import com.bachue.snr.prosnr01.model.sdb.pgn.MotivoTramite;

import com.bachue.snr.prosnr08.common.constants.ErrorKeys;

import java.time.LocalDateTime;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;


/**
 * Clase que contiene todos la logica para la funcionalidad de Entraga de productos
 *
 * @author jpatino
 */
public class EntregaProductoBusiness extends BaseBusiness
{
	/** Constante clh_LOGGER. */
	private static final LoggerHandler clh_LOGGER = (LoggerHandler)com.bachue.snr.prosnr01.common.utils.LoggingHelper
			.getLogger(EntregaProductoBusiness.class, ProyectosCommon.ENTREGAR_PRODUCTO_08);

	/**
	 * A través de la operación “ObtenerProducto” los canales pueden presentar el producto al usuario final,
	 * los canales envían la petición a través del BUS de servicios a Bachue en intervalos de 5 segundos esperando respuesta.
	 *
	 * @param ateop_entrada Objeto de tipo TipoEntradaObtenerProducto que contiene peticion realizada a bachué
	 * @param as_userId Objeto de tipo String que contiene el usuario que invoca la operacion(CORE_BACHUE)
	 * @param as_localIp Objeto de tipo String que contiene la ip local que recibe la petición.
	 * @param as_remoteIp Objeto de tipo String que contiene la ip remota desde donde se envia la petición.
	 * @return Objeto de tipo DocumentosSalida que contiene el Did y DocName solicitado
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see DocumentosSalida
	 */
	public synchronized DocumentosSalida obtenerProducto(
	    TipoEntradaObtenerProducto ateop_entrada, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		DAOManager       ldm_manager;
		DocumentosSalida lds_return;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		lds_return      = null;

		try
		{
			if(ateop_entrada != null)
			{
				String ls_codigoVerificacion;
				String ls_turno;
				String ls_numeroReferencia;
				int    li_indicador;

				ls_numeroReferencia       = StringUtils.getString(ateop_entrada.getReferenciaPago());
				ls_codigoVerificacion     = StringUtils.getString(ateop_entrada.getCodigoVerificacion());
				ls_turno                  = StringUtils.getString(ateop_entrada.getTurno());

				li_indicador = validarParametroObtenerProducto(
					    ls_turno, ls_codigoVerificacion, ls_numeroReferencia, NumericUtils.DEFAULT_INT_VALUE
					);

				if(li_indicador > NumericUtils.DEFAULT_INT_VALUE)
				{
					Collection<DocumentosSalida> lcds_cds;

					lcds_cds     = validarDocumentoConIndicador(
						    li_indicador, ls_turno, ls_codigoVerificacion, ls_numeroReferencia, ldm_manager
						);

					lds_return = verificarDocumentos(lcds_cds, false, ldm_manager);
				}
				else
					throw new B2BException(addErrorEP(ErrorKeys.ERROR_CIRTERIOS_DE_BUSQUEDA_INVALIDOS));
			}
			else
				throw new B2BException(addErrorEP(ErrorKeys.ERROR_PARAMETROS_NO_VALIDOS));
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("obtenerProducto", lb2be_e);

			ldm_manager.setRollbackOnly();

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lds_return;
	}

	/**
	 * A través de la operación “obtenerReciboCaja” se devuelve el recibo de caja generado después del pago de un servicio registral.
	 *
	 * @param ateorc_entrada correspondiente al valor del tipo de objeto TipoEntradaObtenerReciboCaja
	 * @param as_userId Objeto de tipo String que contiene el usuario que invoca la operacion(CORE_BACHUE)
	 * @param as_localIp Objeto de tipo String que contiene la ip local que recibe la petición.
	 * @param as_remoteIp Objeto de tipo String que contiene la ip remota desde donde se envia la petición.
	 * @return Objeto de tipo DocumentosSalida que contiene el Did y DocName solicitado
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see DocumentosSalida
	 */
	public synchronized DocumentosSalida obtenerReciboCaja(
	    TipoEntradaObtenerReciboCaja ateorc_entrada, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		DAOManager       ldm_manager;
		DocumentosSalida lds_return;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		lds_return      = null;

		try
		{
			if(ateorc_entrada != null)
			{
				String                       ls_NIR;
				String                       ls_numeroReferencia;
				Collection<DocumentosSalida> lcds_cds;
				DocumentosSalidaDAO          ldsd_DAO;

				lcds_cds     = new ArrayList<DocumentosSalida>();
				ldsd_DAO     = DaoCreator.getDocumentosSalidaDAO(ldm_manager);

				ls_numeroReferencia     = StringUtils.getString(ateorc_entrada.getReferenciaPago());
				ls_NIR                  = StringUtils.getString(ateorc_entrada.getNIR());

				if(StringUtils.isValidString(ls_numeroReferencia))
				{
					Collection<Turno> lct_ct;

					lct_ct = validarReferenciaPago(ls_numeroReferencia, false, ldm_manager, true);

					if(CollectionUtils.isValidCollection(lct_ct))
					{
						Iterator<Turno> lit_iterator;

						lit_iterator = lct_ct.iterator();

						if(lit_iterator.hasNext())
						{
							Turno lt_turno;

							lt_turno = lit_iterator.next();

							if(lt_turno != null)
							{
								DocumentosSalida lds_tmp;

								lds_tmp = new DocumentosSalida(
									    lt_turno.getIdSolicitud(), lt_turno.getIdTurno(), null, EstadoCommon.ACTIVO
									);

								lds_tmp.setTipo(TipoArchivoCommon.RECIBO_CAJA);
								lcds_cds.add(ldsd_DAO.findDocumentByTurnoTipoEstado(lds_tmp));
							}
						}
					}
				}
				else if(StringUtils.isValidString(ls_NIR))
				{
					Solicitud ls_solicitud;

					ls_solicitud = DaoCreator.getSolicitudDAO(ldm_manager).findByNir(new Solicitud(null, ls_NIR));

					if(ls_solicitud != null)
						lcds_cds = ldsd_DAO.findDocumentByTurnoTipoDocumentalEstado(
							    new DocumentosSalida(
							        ls_solicitud.getIdSolicitud(), null, TipoDocumentalCommon.RECIBO_CAJA,
							        EstadoCommon.ACTIVO
							    )
							);
				}
				else
					throw new B2BException(addErrorEP(ErrorKeys.ERROR_CIRTERIOS_DE_BUSQUEDA_INVALIDOS_RECIBO_CAJA));

				lds_return = verificarDocumentos(lcds_cds, true, ldm_manager);
			}
			else
				throw new B2BException(addErrorEP(ErrorKeys.ERROR_PARAMETROS_NO_VALIDOS));
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("obtenerReciboCaja", lb2be_e);

			ldm_manager.setRollbackOnly();

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lds_return;
	}

	/**
	 * La operación “ObtenerTurnoRefPago” se consume solo para las peticiones realizadas por referencia de pago y
	 * cuando estas tengan más de un turno asociado.
	 *
	 * @param ateotrp_entrada  Objeto de tipo TipoEntradaObtenerTurnosRefPago que contiene peticion realizada a bachué
	 * @param as_userId Objeto de tipo String que contiene el usuario que invoca la operacion (CORE_BACHUE)
	 * @param as_localIp Objeto de tipo String que contiene la ip local que recibe la petición.
	 * @param as_remoteIp Objeto de tipo String que contiene la ip remota desde donde se envia la petición.
	 * @return devuelve el valor de Collection
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public synchronized Collection<Turno> obtenerTurnosRefPago(
	    TipoEntradaObtenerTurnosRefPago ateotrp_entrada, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		DAOManager        ldm_manager;
		Collection<Turno> lct_return;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		lct_return      = new ArrayList<Turno>();

		try
		{
			if(ateotrp_entrada != null)
			{
				String ls_numeroReferencia;

				ls_numeroReferencia = StringUtils.getStringNotNull(ateotrp_entrada.getReferenciaPago());

				if(StringUtils.isValidString(ls_numeroReferencia))
					lct_return = validarReferenciaPago(ls_numeroReferencia, true, ldm_manager);
				else
					throw new B2BException(addErrorEP(ErrorKeys.ERROR_NUMERO_REFERENCIA_INGRESADO_NO_VALIDO));
			}
			else
				throw new B2BException(addErrorEP(ErrorKeys.ERROR_PARAMETROS_NO_VALIDOS));
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("obtenerTurnosRefPago", lb2be_e);

			ldm_manager.setRollbackOnly();

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		if(lct_return.isEmpty())
			lct_return = null;

		return lct_return;
	}

	/**
	 * A través de la operación “registrarEntregaProducto” informa a Bachue sobre la entrega al usuario del producto
	 * correspondiente, esta operación se expone desde Sede Electrónica y Kioskos  hacia Bachue para poder cerrar el NIR.
	 *
	 * @param aterep_entrada Objeto de tipo TipoEntradaRegistrarEntregaProducto que contiene peticion realizada a bachué
	 * @param as_userId Objeto de tipo String que contiene el usuario que invoca la operacion (CORE_BACHUE)
	 * @param as_localIp Objeto de tipo String que contiene la ip local que recibe la petición.
	 * @param as_remoteIp Objeto de tipo String que contiene la ip remota desde donde se envia la petición.
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public synchronized void registrarEntregaProducto(
	    TipoEntradaRegistrarEntregaProducto aterep_entrada, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		DAOManager ldm_manager;

		ldm_manager = DaoManagerFactory.getDAOManager();

		try
		{
			if(aterep_entrada != null)
			{
				String ls_codigoVerificacion;
				String ls_turno;
				String ls_numeroReferencia;
				String ls_userId;
				int    li_indicador;

				ls_userId                 = getSystemUser(as_userId, ldm_manager);
				ls_numeroReferencia       = StringUtils.getString(aterep_entrada.getReferenciaPago());
				ls_codigoVerificacion     = StringUtils.getString(aterep_entrada.getCodigoVerificacion());
				ls_turno                  = StringUtils.getString(aterep_entrada.getTurno());

				li_indicador = validarParametroObtenerProducto(
					    ls_turno, ls_codigoVerificacion, ls_numeroReferencia, NumericUtils.DEFAULT_INT_VALUE
					);

				if(li_indicador > NumericUtils.DEFAULT_INT_VALUE)
				{
					Collection<DocumentosSalida> lcds_cds;

					lcds_cds = validarDocumentoConIndicador(
						    li_indicador, ls_turno, ls_codigoVerificacion, ls_numeroReferencia, ldm_manager
						);

					if(CollectionUtils.isValidCollection(lcds_cds))
					{
						Iterator<DocumentosSalida> lids_ids;

						lids_ids = lcds_cds.iterator();

						if(lids_ids.hasNext())
						{
							DocumentosSalida lds_ds;

							lds_ds = lids_ids.next();

							if(lds_ds != null)
							{
								String ls_idTurno;
								Turno  lt_turno;

								ls_idTurno = lds_ds.getIdTurno();

								if(!StringUtils.isValidString(ls_idTurno))
									throw new B2BException(
									    addErrorEP(ErrorKeys.ERROR_DOCUMENTO_ENCONTRADO_NO_ASOCIADO_A_TURNO)
									);

								lt_turno = DaoCreator.getTurnoDAO(ldm_manager).findById(ls_idTurno);

								if(lt_turno != null)
								{
									Solicitud ls_solicitud;

									ls_solicitud = DaoCreator.getSolicitudDAO(ldm_manager)
											                     .findById(lt_turno.getIdSolicitud());

									if(ls_solicitud == null)
									{
										Object[] loa_messageArgs = new String[1];

										loa_messageArgs[0] = lt_turno.getIdSolicitud();

										throw new B2BException(
										    addErrorEP(ErrorKeys.ERROR_SOLICITUD_ID_SOLICITUD, loa_messageArgs)
										);
									}

									String ls_idProceso;

									ls_idProceso = ls_solicitud.getIdProceso();

									if(StringUtils.isValidString(ls_idProceso))
									{
										if(
										    !(ls_idProceso.equalsIgnoreCase(ProcesoCommon.ID_PROCESO_1)
											    || ls_idProceso.equalsIgnoreCase(ProcesoCommon.ID_PROCESO_2))
										)
											throw new B2BException(
											    addErrorEP(ErrorKeys.ERROR_TURNO_CODIGO_REFERENCIA_SIN_PRODUCTOS)
											);
										else
										{
											String ls_idProcedencia;

											ls_idProcedencia = ls_solicitud.getIdProcedencia();

											if(ls_idProceso.equalsIgnoreCase(ProcesoCommon.ID_PROCESO_1))
											{
												LocalDateTime lldt_fechaCreacion;

												lldt_fechaCreacion     = obtenerLocalDateTime(
													    lds_ds.getFechaCreacion()
													);

												lldt_fechaCreacion = lldt_fechaCreacion.plusDays(30L);

												if(LocalDateTime.now().isAfter(lldt_fechaCreacion))
													throw new B2BException(
													    addErrorEP(ErrorKeys.ERROR_DOCUMENTO_SUPERO_VIGENCIA)
													);
											}

											if(StringUtils.isValidString(ls_idProcedencia))
											{
												if(ls_idProcedencia.equalsIgnoreCase(ProcedenciaCommon.KIOSKO))
												{
													TurnoHistoria lth_th;

													lth_th = DaoCreator.getTurnoHistoriaDAO(ldm_manager)
															               .findByTurnoFaseEntrega(ls_idTurno);

													if(lth_th != null)
														terminarTurnoHistoriaYCrearEtapa(
														    lth_th, ldm_manager,
														    new MotivoTramite(
														        NumericUtils.getLong(lth_th.getIdEtapa()),
														        (ls_idProceso.equalsIgnoreCase(
														            ProcesoCommon.ID_PROCESO_1
														        )) ? MotivoTramiteCommon.ENTREGA_DE_CERTIFICADOS_KIOSKO
														           : MotivoTramiteCommon.ENTREGA_DE_CONSULTAS_KIOSKO
														    ), ls_userId, as_remoteIp, EstadoCommon.TERMINADA
														);
													else
														throw new B2BException(
														    addErrorEP(ErrorKeys.ERROR_CASO_NO_ESTA_EN_FASE_DE_ENTREGA)
														);
												}
												else
													throw new B2BException(
													    addErrorEP(ErrorKeys.ERROR_PROCEDENCIA_TURNO_NO_ES_KIOSKO)
													);
											}
										}
									}
									else
									{
										Object[] loa_messageArgs = new String[1];

										loa_messageArgs[0] = lt_turno.getIdTurno();

										throw new B2BException(
										    addErrorEP(
										        ErrorKeys.ERROR_NO_SE_ENCONTRO_PROCESO_VALIDO_PARA_TURNO,
										        loa_messageArgs
										    )
										);
									}
								}
							}
							else
								throw new B2BException(
								    addErrorEP(ErrorKeys.ERROR_NO_SE_ENCONTRO_INFORMACION_PARA_PARAMETROS_INGRESADOS)
								);
						}
					}
					else
						throw new B2BException(addErrorEP(ErrorKeys.ERROR_NO_SE_ENCONTRARON_DOCUMENTOS_ACTIVOS));
				}
				else
					throw new B2BException(addErrorEP(ErrorKeys.ERROR_CIRTERIOS_DE_BUSQUEDA_INVALIDOS));
			}
			else
				throw new B2BException(addErrorEP(ErrorKeys.ERROR_PARAMETROS_NO_VALIDOS));
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("registrarEntregaProducto", lb2be_e);

			ldm_manager.setRollbackOnly();

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}
	}

	/**
	 * A través de la operación “VerificarProducto” los interesados pueden hacer consultas a través del Bus de servicios
	 * hacia Bachue y conocer si existe un documento asociado a su consulta.
	 *
	 * @param atevp_entrada Objeto de tipo TipoEntradaVerificarProducto que contiene peticion realizada a bachué
	 * @param as_userId Objeto de tipo String que contiene el usuario que invoca la operacion (CORE_BACHUE)
	 * @param as_localIp Objeto de tipo String que contiene la ip local que recibe la petición.
	 * @param as_remoteIp Objeto de tipo String que contiene la ip remota desde donde se envia la petición.
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public synchronized void verificarProducto(
	    TipoEntradaVerificarProducto atevp_entrada, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		DAOManager ldm_manager;

		ldm_manager = DaoManagerFactory.getDAOManager();

		try
		{
			if(atevp_entrada != null)
			{
				String ls_codigoVerificacion;

				ls_codigoVerificacion = StringUtils.getStringNotNull(atevp_entrada.getCodigoVerificacion());

				if(StringUtils.isValidString(ls_codigoVerificacion))
				{
					Imagenes li_imagen;

					li_imagen = validarCodigoVerificacion(ls_codigoVerificacion, ldm_manager);

					if(li_imagen != null)
					{
						DocumentosSalida lds_ds;

						lds_ds = DaoCreator.getDocumentosSalidaDAO(ldm_manager)
								               .findByIdImagen(NumericUtils.getLongWrapper(li_imagen.getIdImagen()));

						if(lds_ds != null)
						{
							String ls_estadoImagen;

							ls_estadoImagen = StringUtils.getStringNotNull(lds_ds.getEstado());

							if(StringUtils.isValidString(ls_estadoImagen))
							{
								if(ls_estadoImagen.equalsIgnoreCase(EstadoCommon.ENTREGA))
									throw new B2BException(
									    addErrorEP(ErrorKeys.ERROR_CODIGO_VERIFICACION_YA_ENTREGADO)
									);

								if(ls_estadoImagen.equalsIgnoreCase(EstadoCommon.INACTIVO))
									throw new B2BException(addErrorEP(ErrorKeys.ERROR_CODIGO_VERIFICACION_YA_INACTIVO));
							}
						}
					}
					else
						throw new B2BException(addErrorEP(ErrorKeys.ERROR_DOCUMENTO_NO_AUTENTICO));
				}
				else
					throw new B2BException(addErrorEP(ErrorKeys.ERROR_CODIGO_VERIFICACION_NO_VALIDO));
			}
			else
				throw new B2BException(addErrorEP(ErrorKeys.ERROR_PARAMETROS_NO_VALIDOS));
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("verificarProducto", lb2be_e);

			ldm_manager.setRollbackOnly();

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}
	}

	/**
	 * Método encargado de validar el codigo verificacion enviado en la peticion.
	 *
	 * @param as_codigoVerificacion Objeto de tipo String que contiene el codigo verificacion a validar en Bachué.
	 * @param adm_manager objeto que contiene los resultados de las consultas en la base de datos
	 * @return devuelve el valor de Imagenes
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see Imagenes
	 */
	private synchronized Imagenes validarCodigoVerificacion(String as_codigoVerificacion, DAOManager adm_manager)
	    throws B2BException
	{
		Imagenes li_return;

		li_return = null;

		try
		{
			li_return = DaoCreator.getImagenesDAO(adm_manager).findByCodigoVerificacion(as_codigoVerificacion);

			if(li_return == null)
				throw new B2BException(addErrorEP(ErrorKeys.ERROR_DOCUMENTO_NO_AUTENTICO));
		}
		catch(B2BException lb2be_e)
		{
			throw lb2be_e;
		}

		return li_return;
	}

	/**
	 * Metodo encargado de verificar según el indicador si existen documentos pendientes de entrega para los parametros de busqueda.
	 *
	 * @param ai_indicador Variable de tipo int que retorna el número de la iteración según el valor válido
	 * @param as_turno Objeto de tipo String que contiene el id_turno a validar en Bachué.
	 * @param as_codigoVerificacion Objeto de tipo String que contiene el codigo verificacion a validar en Bachué.
	 * @param as_numeroReferencia Objeto de tipo String que contiene la referencia de pago a validar en Bachué.
	 * @param adm_manager correspondiente al valor del tipo de objeto DAOManager
	 * @return devuelve el valor de Collection
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see Collection
	 */
	private synchronized Collection<DocumentosSalida> validarDocumentoConIndicador(
	    int ai_indicador, String as_turno, String as_codigoVerificacion, String as_numeroReferencia,
	    DAOManager adm_manager
	)
	    throws B2BException
	{
		Collection<DocumentosSalida> lcds_return;

		lcds_return = new ArrayList<DocumentosSalida>();

		try
		{
			DocumentosSalidaDAO ldsd_DAO;

			ldsd_DAO = DaoCreator.getDocumentosSalidaDAO(adm_manager);

			if(ai_indicador == 1)
				lcds_return = ldsd_DAO.findDocumentByTurnoTipoDocumentalEstado(
					    new DocumentosSalida(null, as_turno, null, EstadoCommon.ACTIVO)
					);
			else if(ai_indicador == 2)
			{
				Imagenes li_imagen;

				li_imagen = validarCodigoVerificacion(as_codigoVerificacion, adm_manager);

				if(li_imagen != null)
				{
					DocumentosSalida lds_ds;

					lds_ds = ldsd_DAO.findByIdImagen(NumericUtils.getLongWrapper(li_imagen.getIdImagen()));

					if(lds_ds != null)
					{
						String ls_estadoImagen;

						ls_estadoImagen = StringUtils.getStringNotNull(lds_ds.getEstado());

						if(StringUtils.isValidString(ls_estadoImagen))
						{
							if(ls_estadoImagen.equalsIgnoreCase(EstadoCommon.ENTREGA))
								throw new B2BException(addErrorEP(ErrorKeys.ERROR_CODIGO_VERIFICACION_YA_ENTREGADO));

							if(ls_estadoImagen.equalsIgnoreCase(EstadoCommon.INACTIVO))
								throw new B2BException(addErrorEP(ErrorKeys.ERROR_CODIGO_VERIFICACION_YA_INACTIVO));
						}

						if(!BooleanUtils.getBooleanValue(lds_ds.getDocumentoFinal()))
							throw new B2BException(addErrorEP(ErrorKeys.ERROR_TURNO_CODIGO_REFERENCIA_SIN_PRODUCTOS));

						lcds_return.add(lds_ds);
					}
				}
				else
					throw new B2BException(addErrorEP(ErrorKeys.ERROR_DOCUMENTO_NO_AUTENTICO));
			}
			else
			{
				Collection<Turno> lct_ct;

				lct_ct = validarReferenciaPago(as_numeroReferencia, false, adm_manager);

				if(CollectionUtils.isValidCollection(lct_ct))
				{
					Iterator<Turno> lit_iterator;

					lit_iterator = lct_ct.iterator();

					if(lit_iterator.hasNext())
					{
						Turno lt_turno;

						lt_turno = lit_iterator.next();

						if(lt_turno != null)
							lcds_return = ldsd_DAO.findDocumentByTurnoTipoDocumentalEstado(
								    new DocumentosSalida(null, lt_turno.getIdTurno(), null, EstadoCommon.ACTIVO)
								);
					}
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			throw lb2be_e;
		}

		if((lcds_return != null) && lcds_return.isEmpty())
			lcds_return = null;

		return lcds_return;
	}

	/**
	 * Método encargado de válidar que solo uno de los tres valores ingresados sea válido.
	 *
	 * @param as_valor1 Objeto de tipo String que contiene valor 1 a válidar.
	 * @param as_valor2 Objeto de tipo String que contiene valor 2 a válidar.
	 * @param as_valor3 Objeto de tipo String que contiene valor 3 a válidar.
	 * @param ai_return Variable de tipo int que contiene el numero de iteraciones realizadas al metodo.
	 * @return Variable de tipo int que retorna el número de la iteración según el valor válido; si hay más de un valor válido retorna un valor negativo.
	 */
	private int validarParametroObtenerProducto(String as_valor1, String as_valor2, String as_valor3, int ai_return)
	{
		if(ai_return < 3)
		{
			if(StringUtils.isValidString(as_valor1))
			{
				if(!StringUtils.isValidString(as_valor2) && !StringUtils.isValidString(as_valor3))
					ai_return++;
				else
					ai_return = -1;
			}
			else
				ai_return = validarParametroObtenerProducto(as_valor2, as_valor3, as_valor1, ++ai_return);
		}
		else
			ai_return = -1;

		return ai_return;
	}

	/**
	 * Método encargado de validar la referencia de pago enviado en la peticion.
	 *
	 * @param as_numeroReferencia Objeto de tipo String que contiene la referencia de pago a validar en Bachué.
	 * @param ab_validarTamanioBusqueda Variable de tipo boolean donde si el true valida que la colleccion solo
	 * halla un turno asociado de lo contrario que la coleccionhalla más de un turno asociado
	 * @param adm_manager objeto que contiene los resultados de las consultas en la base de datos
	 * @return devuelve el valor de Collection
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	private synchronized Collection<Turno> validarReferenciaPago(
	    String as_numeroReferencia, boolean ab_validarTamanioBusqueda, DAOManager adm_manager
	)
	    throws B2BException
	{
		return validarReferenciaPago(as_numeroReferencia, ab_validarTamanioBusqueda, adm_manager, false);
	}

	/**
	 * Método encargado de validar la referencia de pago enviado en la peticion.
	 *
	 * @param as_numeroReferencia Objeto de tipo String que contiene la referencia de pago a validar en Bachué.
	 * @param ab_validarTamanioBusqueda Variable de tipo boolean donde si el true valida que la colleccion solo
	 * halla un turno asociado de lo contrario que la coleccionhalla más de un turno asociado
	 * @param adm_manager objeto que contiene los resultados de las consultas en la base de datos
	 * @param ab_obtenerReciboCaja Variable de tipo boolean donde si es TRUE valida valida la Obtencion del recibo de caja.
	 * @return devuelve el valor de Collection
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	private synchronized Collection<Turno> validarReferenciaPago(
	    String as_numeroReferencia, boolean ab_validarTamanioBusqueda, DAOManager adm_manager,
	    boolean ab_obtenerReciboCaja
	)
	    throws B2BException
	{
		Collection<Turno> lct_return;
		lct_return = new ArrayList<Turno>();

		try
		{
			Liquidacion ll_liquidacion;

			ll_liquidacion = DaoCreator.getAccLiquidacionDAO(adm_manager).findByNumeroReferencia(as_numeroReferencia);

			if(ll_liquidacion != null)
			{
				Solicitud ls_solicitud;

				ls_solicitud = DaoCreator.getSolicitudDAO(adm_manager).findById(ll_liquidacion.getIdSolicitud());

				if(ls_solicitud != null)
				{
					String ls_proceso;

					ls_proceso = ls_solicitud.getIdProceso();

					if(ab_obtenerReciboCaja)
					{
						Subproceso lsp_sp;

						lsp_sp = DaoCreator.getSubprocesoDAO(adm_manager)
								               .findById(new Subproceso(ls_proceso, ls_solicitud.getIdSubproceso()));

						if(lsp_sp != null)
						{
							if(StringUtils.isValidString(ls_proceso))
							{
								SubprocesoVersion lsv_subprocesoVersion;

								lsv_subprocesoVersion = DaoCreator.getSubprocesoVersionDAO(adm_manager)
										                              .findByIdMaxVersion(
										    lsp_sp.getIdProceso(), lsp_sp.getIdSubproceso()
										);

								if(lsv_subprocesoVersion != null)
								{
									boolean lb_error;

									lb_error = false;

									if(
									    !(ls_proceso.equalsIgnoreCase(ProcesoCommon.ID_PROCESO_1)
										    || ls_proceso.equalsIgnoreCase(ProcesoCommon.ID_PROCESO_2)
										    || ls_proceso.equalsIgnoreCase(ProcesoCommon.ID_PROCESO_5))
									)
										lb_error = true;

									if(!BooleanUtils.getBooleanValue(lsv_subprocesoVersion.getObtenerReciboCaja()))
										lb_error = true;

									if(lb_error)
									{
										Object[] loa_messageArgs = new String[2];

										loa_messageArgs[0]     = ls_proceso;
										loa_messageArgs[1]     = lsp_sp.getIdSubproceso();

										throw new B2BException(
										    addErrorEP(
										        ErrorKeys.ERROR_NO_SE_PUEDE_OBTENER_RECIBO_DE_CAJA, loa_messageArgs
										    )
										);
									}
								}
							}
							else
							{
								Object[] loa_messageArgs = new String[1];

								loa_messageArgs[0] = as_numeroReferencia;

								throw new B2BException(
								    addErrorEP(
								        ErrorKeys.ERROR_NO_SE_ENCONTRO_PROCESO_NUMERO_REFERENCIA, loa_messageArgs
								    )
								);
							}
						}
					}

					Collection<Turno> lct_turnos;

					lct_turnos = DaoCreator.getTurnoDAO(adm_manager)
							                   .findByIdSolicitud(new Turno(ls_solicitud.getIdSolicitud(), null));

					if(CollectionUtils.isValidCollection(lct_turnos))
					{
						DocumentosSalidaDAO ldsd_DAO;

						ldsd_DAO = DaoCreator.getDocumentosSalidaDAO(adm_manager);

						for(Turno lt_actual : lct_turnos)
						{
							if(lt_actual != null)
							{
								Collection<DocumentosSalida> lcds_cds;

								lcds_cds = ldsd_DAO.findDocumentByTurnoTipoDocumentalEstado(
									    new DocumentosSalida(
									        null, lt_actual.getIdTurno(),
									        ab_obtenerReciboCaja ? TipoDocumentalCommon.RECIBO_CAJA : null,
									        EstadoCommon.ACTIVO
									    )
									);

								if(CollectionUtils.isValidCollection(lcds_cds))
								{
									for(DocumentosSalida lds_actual : lcds_cds)
									{
										if(lds_actual != null)
										{
											if(
											    StringUtils.isValidString(ls_proceso)
												    && ls_proceso.equalsIgnoreCase(ProcesoCommon.ID_PROCESO_1)
											)
											{
												LocalDateTime lldt_fechaCreacion;

												lldt_fechaCreacion     = obtenerLocalDateTime(
													    lds_actual.getFechaCreacion()
													);

												lldt_fechaCreacion = lldt_fechaCreacion.plusDays(30L);

												if(LocalDateTime.now().isAfter(lldt_fechaCreacion))
													throw new B2BException(
													    addErrorEP(ErrorKeys.ERROR_DOCUMENTO_SUPERO_VIGENCIA)
													);
											}
										}
									}

									lct_return.add(lt_actual);
								}
							}
						}

						int li_unTurnoAsociado;

						li_unTurnoAsociado = lct_return.size();

						if(ab_validarTamanioBusqueda)
						{
							if(li_unTurnoAsociado == 1)
								throw new B2BException(
								    addErrorEP(ErrorKeys.ERROR_REFERENCIA_PAGO_SOLO_TIENE_UN_TURNO_ASOCIADO)
								);
						}
						else
						{
							if(li_unTurnoAsociado > 1)
								throw new B2BException(
								    addErrorEP(ErrorKeys.ERROR_REFERENCIA_PAGO_TIENE_MAS_DE_UN_TURNO_ASOCIADO)
								);
						}
					}
					else
					{
						Object[] loa_messageArgs = new String[1];

						loa_messageArgs[0] = ls_solicitud.getNir();

						throw new B2BException(
						    addErrorEP(ErrorKeys.ERROR_NO_ENCONTRARON_TURNOS_RELACIONADOS_AL_NIR, loa_messageArgs)
						);
					}
				}
				else
					throw new B2BException(addErrorEP(ErrorKeys.ERROR_REFERENCIA_PAGO_NO_TIENE_ASOCIADO_UNA_SOLICITUD));
			}
			else
				throw new B2BException(addErrorEP(ErrorKeys.ERROR_REFERENCIA_PAGO_NO_ENCONTRADO));
		}
		catch(B2BException lb2be_e)
		{
			throw lb2be_e;
		}

		return lct_return;
	}

	/**
	 * Método encargado de validar que los documentos encontrados coincidan con las reglas de negocio del Servicio Entrega Producto.
	 *
	 * @param acds_cds Objeto de tipo Collection<DocumentosSalida> con lista de documentos a validar.
	 * @param ab_reciboCaja Variable de tipo boolean, flag que idica si es un Recibo de caja <true> o no <false>.
	 * @param adm_manager Objeto de tipo DAOManager que maneja la tranccioón con la base de datos.
	 * @return Objeto de tipo DocumentosSalida que cumple con las validaciones y reglas de negocio del Servicio Entrega Producto.
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see DocumentosSalida
	 */
	private synchronized DocumentosSalida verificarDocumentos(
	    Collection<DocumentosSalida> acds_cds, boolean ab_reciboCaja, DAOManager adm_manager
	)
	    throws B2BException
	{
		DocumentosSalida lds_return;

		lds_return = null;

		try
		{
			if(CollectionUtils.isValidCollection(acds_cds))
			{
				Iterator<DocumentosSalida> lids_ids;

				lids_ids = acds_cds.iterator();

				if(lids_ids.hasNext())
				{
					DocumentosSalida lds_general;

					lds_general = lids_ids.next();

					if(lds_general != null)
					{
						String ls_idTurno;
						String ls_idProceso;
						Turno  lt_turno;

						ls_idTurno       = lds_general.getIdTurno();
						ls_idProceso     = null;

						if(!StringUtils.isValidString(ls_idTurno))
							throw new B2BException(
							    addErrorEP(ErrorKeys.ERROR_DOCUMENTO_ENCONTRADO_NO_ASOCIADO_A_TURNO)
							);

						lt_turno = DaoCreator.getTurnoDAO(adm_manager).findById(ls_idTurno);

						if(lt_turno != null)
							ls_idProceso = lt_turno.getIdProceso();
						else
							throw new B2BException(
							    addErrorEP(ErrorKeys.ERROR_DOCUMENTO_ENCONTRADO_NO_ASOCIADO_A_TURNO)
							);

						if(StringUtils.isValidString(ls_idProceso))
						{
							if(
							    (!ab_reciboCaja)
								    && !(ls_idProceso.equalsIgnoreCase(ProcesoCommon.ID_PROCESO_1)
								    || ls_idProceso.equalsIgnoreCase(ProcesoCommon.ID_PROCESO_2)
								    || ls_idProceso.equalsIgnoreCase(ProcesoCommon.ID_PROCESO_5))
							)
								throw new B2BException(
								    addErrorEP(ErrorKeys.ERROR_TURNO_CODIGO_REFERENCIA_SIN_PRODUCTOS)
								);

							{
								Collection<DocumentosSalida> lcds_response;

								lcds_response = DaoCreator.getDocumentosSalidaDAO(adm_manager)
										                      .findDocumentByTurnoTipoDocumentalEstado(
										    new DocumentosSalida(
										        lt_turno.getIdSolicitud(), lt_turno.getIdTurno(),
										        (ab_reciboCaja ? TipoDocumentalCommon.RECIBO_CAJA
										                           : (ls_idProceso.equalsIgnoreCase(
										            ProcesoCommon.ID_PROCESO_1
										        ) ? TipoDocumentalCommon.CERTIFICADO_TRADICION_LIBERTAD
										              : TipoDocumentalCommon.CONSULTAS)), EstadoCommon.ACTIVO
										    )
										);

								if(CollectionUtils.isValidCollection(lcds_response))
								{
									Iterator<DocumentosSalida> lids_iterator;

									lids_iterator = lcds_response.iterator();

									if(lids_iterator.hasNext())
									{
										DocumentosSalida lds_especifico;

										lds_especifico = lids_iterator.next();

										if(lds_especifico != null)
										{
											if(
											    (ls_idProceso.equalsIgnoreCase(ProcesoCommon.ID_PROCESO_1)
												    && !ab_reciboCaja) || ab_reciboCaja
											)
											{
												LocalDateTime lldt_fechaCreacion;

												lldt_fechaCreacion     = obtenerLocalDateTime(
													    lds_especifico.getFechaCreacion()
													);

												lldt_fechaCreacion = lldt_fechaCreacion.plusDays(30L);

												if(LocalDateTime.now().isAfter(lldt_fechaCreacion))
													throw new B2BException(
													    addErrorEP(ErrorKeys.ERROR_DOCUMENTO_SUPERO_VIGENCIA)
													);
											}

											if(lds_especifico.getFechaEnvio() == null)
												throw new B2BException(
												    addErrorEP(ErrorKeys.ERROR_DOCUMENTO_NO_ENVIADO_AL_OWCC)
												);

											{
												String ls_did;
												String ls_docName;

												ls_did         = lds_especifico.getIdEcm();
												ls_docName     = lds_especifico.getIdNombreDocumento();

												if(
												    StringUtils.isValidString(ls_did)
													    && StringUtils.isValidString(ls_docName)
												)
													lds_return = lds_especifico;
												else
													throw new B2BException(
													    addErrorEP(ErrorKeys.ERROR_DOCUMENTO_NO_ENVIADO_AL_OWCC)
													);
											}
										}
										else
											throw new B2BException(
											    addErrorEP(
											        ErrorKeys.ERROR_NO_SE_ENCONTRO_INFORMACION_PARA_PARAMETROS_INGRESADOS
											    )
											);
									}
								}
								else
									throw new B2BException(
									    addErrorEP(
									        ErrorKeys.ERROR_NO_SE_ENCONTRO_INFORMACION_PARA_PARAMETROS_INGRESADOS
									    )
									);
							}
						}
					}
					else
						throw new B2BException(
						    addErrorEP(ErrorKeys.ERROR_NO_SE_ENCONTRO_INFORMACION_PARA_PARAMETROS_INGRESADOS)
						);
				}
			}
			else
				throw new B2BException(addErrorEP(ErrorKeys.ERROR_DOCUMENTOS_NO_GENERADOS));
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("VerificarDocumentos", lb2be_e);

			throw lb2be_e;
		}

		return lds_return;
	}
}
