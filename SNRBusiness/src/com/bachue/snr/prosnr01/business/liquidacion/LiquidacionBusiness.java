package com.bachue.snr.prosnr01.business.liquidacion;

import com.aspose.words.CellMerge;
import com.aspose.words.ControlChar;
import com.aspose.words.DocumentBuilder;
import com.aspose.words.ParagraphAlignment;
import com.aspose.words.PreferredWidth;
import com.aspose.words.SaveFormat;
import com.aspose.words.Table;
import com.aspose.words.TableAlignment;

import com.b2bsg.common.dataAccess2.DAOManager;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.logging.LoggerHandler;

import com.b2bsg.common.util.CollectionUtils;
import com.b2bsg.common.util.DateUtils;
import com.b2bsg.common.util.NumericUtils;
import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.business.calificacion.CalificacionBusiness;
import com.bachue.snr.prosnr01.business.consulta.recibo.liquidacion.ConsultaReciboLiquidacionBusiness;
import com.bachue.snr.prosnr01.business.firma.FirmaMasivaBusiness;
import com.bachue.snr.prosnr01.business.registro.RegistroBusiness;
import com.bachue.snr.prosnr01.business.utilidades.PDFGenerator;

import com.bachue.snr.prosnr01.common.constants.ConstanteCommon;
import com.bachue.snr.prosnr01.common.constants.ErrorKeys;
import com.bachue.snr.prosnr01.common.constants.EstadoCommon;
import com.bachue.snr.prosnr01.common.constants.ExtensionCommon;
import com.bachue.snr.prosnr01.common.constants.FormatoFechaCommon;
import com.bachue.snr.prosnr01.common.constants.IdentificadoresCommon;
import com.bachue.snr.prosnr01.common.constants.MedioNotificarCommon;
import com.bachue.snr.prosnr01.common.constants.ProcesoCommon;
import com.bachue.snr.prosnr01.common.constants.RepositorioCommon;
import com.bachue.snr.prosnr01.common.constants.TagCommon;
import com.bachue.snr.prosnr01.common.utils.Convertidor;
import com.bachue.snr.prosnr01.common.utils.ListadoCodigosConstantes;

import com.bachue.snr.prosnr01.dao.DaoCreator;
import com.bachue.snr.prosnr01.dao.DaoManagerFactory;
import com.bachue.snr.prosnr01.dao.acc.ActoDAO;
import com.bachue.snr.prosnr01.dao.acc.DocumentosSalidaDAO;
import com.bachue.snr.prosnr01.dao.acc.LiquidacionDAO;
import com.bachue.snr.prosnr01.dao.acc.LiquidacionItemCondicionDAO;
import com.bachue.snr.prosnr01.dao.acc.LiquidacionItemDAO;
import com.bachue.snr.prosnr01.dao.acc.PersonaDireccionDAO;
import com.bachue.snr.prosnr01.dao.acc.ProcesoDAO;
import com.bachue.snr.prosnr01.dao.acc.RegistroMayorValorDAO;
import com.bachue.snr.prosnr01.dao.acc.RegistroPagoExcesoDAO;
import com.bachue.snr.prosnr01.dao.acc.SolicitudDAO;
import com.bachue.snr.prosnr01.dao.acc.SolicitudMatriculaActoDAO;
import com.bachue.snr.prosnr01.dao.acc.SolicitudMatriculaDAO;
import com.bachue.snr.prosnr01.dao.acc.TipoActoDAO;
import com.bachue.snr.prosnr01.dao.acc.TurnoHistoriaDAO;
import com.bachue.snr.prosnr01.dao.bgn.ImagenesDAO;
import com.bachue.snr.prosnr01.dao.pgn.CirculoRegistralDao;

import com.bachue.snr.prosnr01.model.numeracion.NumeracionOficiosCirculo;
import com.bachue.snr.prosnr01.model.registro.Registro;
import com.bachue.snr.prosnr01.model.sdb.acc.Acto;
import com.bachue.snr.prosnr01.model.sdb.acc.DocumentosSalida;
import com.bachue.snr.prosnr01.model.sdb.acc.Liquidacion;
import com.bachue.snr.prosnr01.model.sdb.acc.LiquidacionItemCondicion;
import com.bachue.snr.prosnr01.model.sdb.acc.OficiosTexto;
import com.bachue.snr.prosnr01.model.sdb.acc.Persona;
import com.bachue.snr.prosnr01.model.sdb.acc.PersonaCorreoElectronico;
import com.bachue.snr.prosnr01.model.sdb.acc.PersonaDireccion;
import com.bachue.snr.prosnr01.model.sdb.acc.PersonaTelefono;
import com.bachue.snr.prosnr01.model.sdb.acc.Proceso;
import com.bachue.snr.prosnr01.model.sdb.acc.RegistroMayorValor;
import com.bachue.snr.prosnr01.model.sdb.acc.RegistroPagoExceso;
import com.bachue.snr.prosnr01.model.sdb.acc.Solicitud;
import com.bachue.snr.prosnr01.model.sdb.acc.SolicitudInterviniente;
import com.bachue.snr.prosnr01.model.sdb.acc.SolicitudMatricula;
import com.bachue.snr.prosnr01.model.sdb.acc.SolicitudMatriculaActo;
import com.bachue.snr.prosnr01.model.sdb.acc.Turno;
import com.bachue.snr.prosnr01.model.sdb.acc.TurnoHistoria;
import com.bachue.snr.prosnr01.model.sdb.bng.Documento;
import com.bachue.snr.prosnr01.model.sdb.bng.Imagenes;
import com.bachue.snr.prosnr01.model.sdb.pgn.CirculoRegistral;
import com.bachue.snr.prosnr01.model.sdb.pgn.Constantes;
import com.bachue.snr.prosnr01.model.sdb.pgn.Departamento;
import com.bachue.snr.prosnr01.model.sdb.pgn.Municipio;
import com.bachue.snr.prosnr01.model.sdb.pgn.TipoActo;
import com.bachue.snr.prosnr01.model.sdb.pgn.TipoDocumental;
import com.bachue.snr.prosnr01.model.sdb.pgn.TipoDocumentoPublico;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

import java.math.BigDecimal;
import java.math.BigInteger;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;


/**
 * Clase para el manejo del negocio de liquidacion para realizar y consultar liquidaciones.
 *
 * @author Nicolas Guaneme
 */
public class LiquidacionBusiness extends ConsultaReciboLiquidacionBusiness
{
	/** Constante clh_LOGGER. */
	private static final LoggerHandler clh_LOGGER = (LoggerHandler)com.bachue.snr.prosnr01.common.utils.LoggingHelper
			.getLogger(LiquidacionBusiness.class);

	/** Propiedad ifmb firma masiva business. */
	private static FirmaMasivaBusiness ifmb_firmaMasivaBusiness;

	/** Propiedad icb_calificacionBusiness. */
	private static CalificacionBusiness icb_calificacionBusiness;

	/** Propiedad lrb buss. */
	private RegistroBusiness lrb_buss;

	/** {@inheritdoc} */
	public RegistroBusiness getRegistroBusiness()
	{
		if(lrb_buss == null)
			lrb_buss = new RegistroBusiness();

		return lrb_buss;
	}

	/**
	 * Método para realizar transaccciones con la base de datos para encontrar las condiciones de la liquidación.
	 *
	 * @param ac_liquidacion correspondiente al valor del tipo de objeto Collection<Liquidacion>
	 * @param as_remoteIp Ip remota
	 * @param as_userId Id usuario
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public synchronized void actualizaCondiciones(
	    Collection<Liquidacion> ac_liquidacion, String as_userId, String as_remoteIp
	)
	    throws B2BException
	{
		if(CollectionUtils.isValidCollection(ac_liquidacion))
		{
			DAOManager ldm_manager;

			ldm_manager = DaoManagerFactory.getDAOManager();

			try
			{
				DaoCreator.getAccLiquidacionDAO(ldm_manager)
					          .actualizaCondiciones(ac_liquidacion, true, as_userId, as_remoteIp);
			}
			catch(B2BException lb2be_e)
			{
				ldm_manager.setRollbackOnly();

				clh_LOGGER.error("actualizaCondiciones", lb2be_e);

				throw lb2be_e;
			}
			finally
			{
				ldm_manager.close();
			}
		}
	}

	/**
	 * Metodo encargado de consultar todos los registros de LIQUIDACION_ITEM para un ID_SOLICITUD o ID_TURNO específico.
	 * @param al_liquidacion Argumento de tipo <code>Liquidacion</code> que contiene los criterios necesarios para realizar la consulta.
	 * @param ab_tipoRecibo Argumento de tipo <code>boolean</code> que determina cual tipo de recibo consultar.
	 * @return Colección de Liquidacion que contiene los resultados que coinciden con los criterios de consulta.
	 * @throws B2BException
	 */
	public synchronized Collection<Liquidacion> buscarDetalleLiquidacion(
	    Liquidacion al_liquidacion, boolean ab_tipoRecibo
	)
	    throws B2BException
	{
		Collection<Liquidacion> lcl_detalleLiquidacion;

		lcl_detalleLiquidacion = null;

		if(al_liquidacion != null)
		{
			DAOManager ldm_manager;

			ldm_manager = DaoManagerFactory.getDAOManager();

			try
			{
				lcl_detalleLiquidacion = DaoCreator.getAccLiquidacionDAO(ldm_manager)
						                               .detalleLiquidacion(al_liquidacion, ab_tipoRecibo);
			}
			catch(B2BException lb2be_e)
			{
				ldm_manager.setRollbackOnly();

				clh_LOGGER.error("buscarDetalleLiquidacion", lb2be_e);

				throw lb2be_e;
			}
			finally
			{
				ldm_manager.close();
			}
		}

		return lcl_detalleLiquidacion;
	}

	/**
	 * Metodo encargado de consultar todos los registros de LIQUIDACION_ITEM para un ID_LIQUIDACION específico.
	 *
	 * @param al_liquidacion Argumento de tipo <code>Liquidacion</code> que contiene los criterios necesarios para realizar la consulta.
	 * @return Colección de Liquidacion que contiene los resultados que coinciden con los criterios de consulta.
	 * @throws B2BException Señala que se ha producido una excepción
	 *
	 */
	public synchronized Collection<Liquidacion> buscarLiquidacionItem(Liquidacion al_liquidacion)
	    throws B2BException
	{
		Collection<Liquidacion> lcl_datos;
		DAOManager              ldm_manager;

		lcl_datos       = new ArrayList<Liquidacion>();
		ldm_manager     = DaoManagerFactory.getDAOManager();

		try
		{
			if(al_liquidacion != null)
			{
				boolean lb_idSolicitud;
				String  ls_idSolicitudEnviada;
				String  ls_idTurno;

				ls_idSolicitudEnviada     = al_liquidacion.getIdSolicitud();
				lb_idSolicitud            = StringUtils.isValidString(ls_idSolicitudEnviada);
				ls_idTurno                = al_liquidacion.getIdTurno();

				if(!StringUtils.isValidString(ls_idTurno) && !lb_idSolicitud)
					throw new B2BException(ErrorKeys.ERROR_SIN_ID_TURNO);

				{
					String ls_idSolicitud;

					ls_idSolicitud = lb_idSolicitud ? ls_idSolicitudEnviada
						                            : DaoCreator.getSolicitudDAO(ldm_manager)
							                                        .findIdSolicitudByIdTurno(ls_idTurno);

					if(!StringUtils.isValidString(ls_idSolicitud))
						throw new B2BException(ErrorKeys.ERROR_SIN_ID_SOLICITUD);

					{
						Liquidacion             ll_liquidacion;
						Collection<Liquidacion> lcl_liquidaciones;

						ll_liquidacion = new Liquidacion();

						ll_liquidacion.setIdSolicitud(ls_idSolicitud);
						ll_liquidacion.setConsecutivo(al_liquidacion.getConsecutivo());
						ll_liquidacion.setIdTipoMayorValor(al_liquidacion.getIdTipoMayorValor());

						lcl_liquidaciones = DaoCreator.getAccLiquidacionDAO(ldm_manager)
								                          .findByIdSolicitudConsecutivo(ll_liquidacion);

						if(CollectionUtils.isValidCollection(lcl_liquidaciones))
						{
							for(Liquidacion ll_iteradorLiquidacion : lcl_liquidaciones)
							{
								if(ll_iteradorLiquidacion != null)
								{
									Collection<Liquidacion> lcl_liquidacionItem;

									lcl_liquidacionItem = DaoCreator.getAccLiquidacionItemDAO(ldm_manager)
											                            .buscarLiquidacionItem(ll_iteradorLiquidacion);

									if(!CollectionUtils.isValidCollection(lcl_liquidacionItem))
										throw new B2BException(ErrorKeys.ERROR_SIN_DETALLE_LIQUIDACION);

									{
										ActoDAO             lad_DAO;
										CirculoRegistralDao lcrd_DAO;
										TipoActoDAO         ltad_DAO;
										ProcesoDAO          lpd_DAO;

										lad_DAO      = DaoCreator.getActoDAO(ldm_manager);
										lcrd_DAO     = DaoCreator.getCirculoRegistralDAO(ldm_manager);
										ltad_DAO     = DaoCreator.getTipoActoDAO(ldm_manager);
										lpd_DAO      = DaoCreator.getProcesoDAO(ldm_manager);

										for(Liquidacion ll_iteradorItem : lcl_liquidacionItem)
										{
											if(ll_iteradorItem != null)
											{
												if(al_liquidacion.isMayorValorModificado())
												{
													RegistroMayorValor lrmv_registroMayorValor;

													lrmv_registroMayorValor = new RegistroMayorValor();

													lrmv_registroMayorValor.setIdTurno(ls_idTurno);
													lrmv_registroMayorValor.setIdSolicitud(ls_idSolicitud);
													lrmv_registroMayorValor.setIdActo(ll_iteradorItem.getIdActo());

													lrmv_registroMayorValor = DaoCreator.getRegistroMayorValorDAO(
														    ldm_manager
														).findByTurnoActoSolicitud(lrmv_registroMayorValor);

													if((lrmv_registroMayorValor != null))
													{
														Acto la_acto;

														la_acto = new Acto();

														la_acto.setIdActo(lrmv_registroMayorValor.getIdActo());
														la_acto.setCuantia(lrmv_registroMayorValor.getCuantia());
														la_acto.setIndMayorValor(
														    lrmv_registroMayorValor.getIdTipoMayorValor()
														);
														la_acto.setValorAvaluo(
														    lrmv_registroMayorValor.getValorAvaluo()
														);
														la_acto.setCantidadActos(
														    NumericUtils.getBigDecimal(
														        NumericUtils.getLong(
														            lrmv_registroMayorValor.getCantidadActos()
														        )
														    )
														);

														ll_iteradorItem.setAccActo(la_acto);
													}
												}
												else
												{
													Acto la_acto;

													la_acto = lad_DAO.findById(ll_iteradorItem.getIdActo());

													if(la_acto != null)
													{
														la_acto.setIndMayorValor(null);
														ll_iteradorItem.setAccActo(la_acto);
													}
												}

												{
													CirculoRegistral lcr_circuloRegistral;

													lcr_circuloRegistral = lcrd_DAO.findById(
														    ll_iteradorItem.getIdCirculo()
														);

													ll_iteradorItem.setCirculoRegistral(lcr_circuloRegistral);
												}

												{
													TipoActo lta_tipoActo;

													lta_tipoActo = new TipoActo();

													lta_tipoActo.setIdTipoActo(ll_iteradorItem.getIdTipoActo());
													lta_tipoActo.setVersion(
													    NumericUtils.getLong(ll_iteradorItem.getVersion())
													);

													lta_tipoActo = ltad_DAO.findById(lta_tipoActo);

													ll_iteradorItem.setTipoActo(lta_tipoActo);
												}

												{
													Proceso lp_proceso;

													lp_proceso = new Proceso();

													lp_proceso.setIdProceso(ll_iteradorItem.getIdProceso());

													lp_proceso = lpd_DAO.findById(lp_proceso);

													ll_iteradorItem.setProceso(lp_proceso);
												}

												lcl_datos.add(ll_iteradorItem);
											}
										}
									}
								}
							}
						}
					}
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("buscarLiquidacionItem", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		if(lcl_datos.isEmpty())
			lcl_datos = null;

		return lcl_datos;
	}

	/**
	 * Método encargado de consultar los datos de pago en exceso.
	 *
	 * @param al_liquidacion Argumento de tipo <code>Liquidacion</code> que contiene los criterios necesarios para realizar la consulta.
	 * @return Retorna objeto de tipo <code>Liquidacion</code> que contiene los datos que coincidieron con los criterios de búsqueda.
	 * @throws B2BException Se genera cuando se propaga una excepción.
	 */
	public synchronized Liquidacion consultarDatosPagoExceso(Liquidacion al_liquidacion)
	    throws B2BException
	{
		DAOManager  ldm_manager;
		Liquidacion ll_liquidacion;

		ldm_manager        = DaoManagerFactory.getDAOManager();
		ll_liquidacion     = null;

		try
		{
			if(al_liquidacion != null)
			{
				ll_liquidacion = DaoCreator.getAccLiquidacionDAO(ldm_manager)
						                       .findByIdSolicitudOne(al_liquidacion.getIdSolicitud());

				if(ll_liquidacion != null)
				{
					Collection<RegistroPagoExceso> lcrpe_pagoExceso;

					lcrpe_pagoExceso = DaoCreator.getRegistroPagoExcesoDAO(ldm_manager)
							                         .findByIdLiquidacion(ll_liquidacion.getIdLiquidacion());

					if(CollectionUtils.isValidCollection(lcrpe_pagoExceso))
						ll_liquidacion.setRegistroPagoExceso(lcrpe_pagoExceso.iterator().next());
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("consultarDatosPagoExceso", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return ll_liquidacion;
	}

	/**
	 * Metodo encargado de consultar la imagen de un documento salida.
	 *
	 * @param ads_parametros Argumento de tipo <code>DocumentosSalida</code> que contiene los criterios necesarios para realizar la consulta.
	 * @return Objeto de tipo <code>Imagenes</code> que contiene los resultados que coinciden con los criterios de consulta.
	 * @throws B2BException Señala que se ha generado una excepción
	 *
	 */
	public synchronized Imagenes consultarImagenDocumento(DocumentosSalida ads_parametros)
	    throws B2BException
	{
		Imagenes   li_imagen;
		DAOManager ldm_manager;

		li_imagen       = null;
		ldm_manager     = DaoManagerFactory.getDAOManager();

		try
		{
			if(ads_parametros != null)
			{
				Collection<DocumentosSalida> lcds_documentosSalida;

				lcds_documentosSalida = DaoCreator.getDocumentosSalidaDAO(ldm_manager)
						                              .findAllDocumentByTurnoHistoriaTipoDocEstado(ads_parametros);

				if(CollectionUtils.isValidCollection(lcds_documentosSalida))
				{
					DocumentosSalida lds_documentosSalida;

					lds_documentosSalida = lcds_documentosSalida.iterator().next();

					if(lds_documentosSalida != null)
						li_imagen = DaoCreator.getImagenesDAO(ldm_manager)
								                  .findById(NumericUtils.getLong(lds_documentosSalida.getIdImagen()));
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("consultarImagenDocumento", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return li_imagen;
	}

	/**
	 * Método encargado de consultar los valores de registro mayor valor para un id turno.
	 *
	 * @param as_idTurno Argumento de tipo <code>String</code> que contiene el id_turno a consultar.
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public synchronized Collection<RegistroMayorValor> consultarRegistroMayorValor(String as_idTurno)
	    throws B2BException
	{
		Collection<RegistroMayorValor> lcrmv_registroMayorValor;

		lcrmv_registroMayorValor = null;

		if(StringUtils.isValidString(as_idTurno))
		{
			DAOManager ldm_manager;

			ldm_manager = DaoManagerFactory.getDAOManager();

			try
			{
				lcrmv_registroMayorValor = DaoCreator.getRegistroMayorValorDAO(ldm_manager).findByIdTurno(as_idTurno);
			}
			catch(B2BException lb2be_e)
			{
				ldm_manager.setRollbackOnly();

				clh_LOGGER.error("consultarRegistroMayorValor", lb2be_e);

				throw lb2be_e;
			}
			finally
			{
				ldm_manager.close();
			}
		}

		return lcrmv_registroMayorValor;
	}

	/**
	 * Metodo encargado de consultar el detalle de la liquidación para una solicitud.
	 *
	 * @param al_liquidacion Objeto de tipo Liquidacion que contiene el id_solicitud para realizar la consulta.
	 * @return Colección de datos que contiene el detalle de la liquidación para una solicitud.
	 * @throws B2BException Señala que se ha producido una excepción
	 *
	 */
	public synchronized Collection<Liquidacion> detalleLiquidacion(Liquidacion al_liquidacion)
	    throws B2BException
	{
		Collection<Liquidacion> lcl_detalle;
		DAOManager              ldm_manager;

		lcl_detalle     = null;
		ldm_manager     = DaoManagerFactory.getDAOManager();

		try
		{
			if(al_liquidacion != null)
			{
				String ls_idSolicitud;

				ls_idSolicitud = al_liquidacion.getIdSolicitud();

				if(!StringUtils.isValidString(ls_idSolicitud))
					throw new B2BException(ErrorKeys.ERROR_SIN_ID_SOLICITUD);

				Liquidacion ll_l;
				ll_l = new Liquidacion();
				ll_l.setIdSolicitud(ls_idSolicitud);

				lcl_detalle = DaoCreator.getAccLiquidacionDAO(ldm_manager).detalleLiquidacion(ll_l, true);

				if(!CollectionUtils.isValidCollection(lcl_detalle))
					throw new B2BException(ErrorKeys.ERROR_SIN_DETALLE_LIQUIDACION);
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("detalleLiquidacion", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lcl_detalle;
	}

	/**
	 * Método encargado de eliminar un acto enviado por argumento.
	 *
	 * @param as_idActo Argumento de tipo <code>String</code> que contiene el id acto a eliminar.
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public synchronized void eliminarActoMayorValor(String as_idActo)
	    throws B2BException
	{
		if(StringUtils.isValidString(as_idActo))
		{
			DAOManager ldm_manager;

			ldm_manager = DaoManagerFactory.getDAOManager();

			try
			{
				Liquidacion             ll_liquidacion;
				Collection<Liquidacion> lcl_liquidacionItem;
				LiquidacionItemDAO      llid_DAO;

				ll_liquidacion = new Liquidacion();
				ll_liquidacion.setIdActo(as_idActo);
				llid_DAO     = DaoCreator.getAccLiquidacionItemDAO(ldm_manager);

				lcl_liquidacionItem = llid_DAO.buscarLiquidacionItemByActo(ll_liquidacion);

				if(CollectionUtils.isValidCollection(lcl_liquidacionItem))
				{
					LiquidacionItemCondicionDAO llicd_DAO;

					llicd_DAO = DaoCreator.getLiquidacionItemCondicionDAO(ldm_manager);

					for(Liquidacion ll_iterador : lcl_liquidacionItem)
					{
						if(ll_iterador != null)
						{
							LiquidacionItemCondicion llic_liquidacionItemCondicion;

							llic_liquidacionItemCondicion = new LiquidacionItemCondicion();

							llic_liquidacionItemCondicion.setIdLiquidacion(ll_iterador.getIdLiquidacion());
							llic_liquidacionItemCondicion.setConsecutivo(ll_iterador.getConsecutivo());
							llic_liquidacionItemCondicion.setIdItem(NumericUtils.getLong(ll_iterador.getIdItem()));

							llicd_DAO.deleteLiquidacionItem(llic_liquidacionItemCondicion);
						}
					}
				}

				llid_DAO.eliminarItemPorActo(as_idActo);

				{
					SolicitudMatriculaActo lsma_parametros;

					lsma_parametros = new SolicitudMatriculaActo();

					lsma_parametros.setIdActo(as_idActo);

					DaoCreator.getSolicitudMatriculaActoDAO(ldm_manager).deleteByIdActo(lsma_parametros);
				}

				DaoCreator.getRegistroMayorValorDAO(ldm_manager).deleteByIdActo(as_idActo);

				DaoCreator.getActoDAO(ldm_manager).delete(as_idActo);
			}
			catch(B2BException lb2be_e)
			{
				ldm_manager.setRollbackOnly();

				clh_LOGGER.error("eliminarActoMayorValor", lb2be_e);

				throw lb2be_e;
			}
			finally
			{
				ldm_manager.close();
			}
		}
	}

	/**
	 * Metodo encargado de consultar los actos de un turno y sus valores en liquidación item.
	 *
	 * @param al_liquidacion Objeto de tipo Liquidacion que contiene los argumentos para realizar la consulta.
	 * @return Colección de datos que contiene los actos de un turno y sus valores en liquidación item.
	 * @throws B2BException Señala que se ha producido una excepción
	 *
	 */
	public synchronized Collection<Liquidacion> findActoLiquidacionItem(Liquidacion al_liquidacion)
	    throws B2BException
	{
		return findActoLiquidacionItem(al_liquidacion, false);
	}

	/**
	 * Metodo encargado de consultar los actos de un turno y sus valores en liquidación item.
	 *
	 * @param al_liquidacion Objeto de tipo Liquidacion que contiene los argumentos para realizar la consulta.
	 * @return Colección de datos que contiene los actos de un turno y sus valores en liquidación item.
	 * @throws B2BException Señala que se ha producido una excepción
	 *
	 */
	public synchronized Collection<Liquidacion> findActoLiquidacionItem(Liquidacion al_liquidacion, boolean ab_firma)
	    throws B2BException
	{
		Collection<Liquidacion> lcl_actosLiquidacion;
		DAOManager              ldm_manager;

		lcl_actosLiquidacion     = new ArrayList<Liquidacion>();
		ldm_manager              = DaoManagerFactory.getDAOManager();

		try
		{
			if(al_liquidacion != null)
			{
				String ls_idTurno;

				ls_idTurno = al_liquidacion.getIdTurno();

				if(StringUtils.isValidString(ls_idTurno))
				{
					Collection<com.bachue.snr.prosnr01.model.registro.Acto> lca_actos;
					LiquidacionItemDAO                                      llid_DAO;

					lca_actos     = DaoCreator.getActoDAO(ldm_manager).findActosByIdTurno(ls_idTurno);
					llid_DAO      = DaoCreator.getAccLiquidacionItemDAO(ldm_manager);

					if(CollectionUtils.isValidCollection(lca_actos))
					{
						RegistroMayorValorDAO lrmvd_DAO;

						lrmvd_DAO = DaoCreator.getRegistroMayorValorDAO(ldm_manager);

						for(com.bachue.snr.prosnr01.model.registro.Acto la_iterador : lca_actos)
						{
							if(la_iterador != null)
							{
								Liquidacion ll_liquidacion;
								String      ls_idActo;

								ll_liquidacion     = new Liquidacion();
								ls_idActo          = la_iterador.getIdActoDb();

								ll_liquidacion.setActo(la_iterador);

								{
									RegistroMayorValor lrmv_registroMayorValor;

									lrmv_registroMayorValor = new RegistroMayorValor();

									lrmv_registroMayorValor.setIdActo(ls_idActo);
									lrmv_registroMayorValor.setIdTurno(ls_idTurno);

									lrmv_registroMayorValor = lrmvd_DAO.findByTurnoActo(
										    lrmv_registroMayorValor, ab_firma
										);

									if(lrmv_registroMayorValor != null)
									{
										Liquidacion ll_liquidacionTmp;

										ll_liquidacionTmp = new Liquidacion();

										ll_liquidacionTmp.setIdLiquidacion(lrmv_registroMayorValor.getIdLiquidacion());
										ll_liquidacionTmp.setConsecutivo(
										    NumericUtils.getInt(lrmv_registroMayorValor.getConsecutivo())
										);
										ll_liquidacionTmp.setIdActo(ls_idActo);
										ll_liquidacionTmp.setIdTurno(ls_idTurno);

										ll_liquidacionTmp = llid_DAO.findByActoTurnoLiquidacionConsecutivo(
											    ll_liquidacionTmp
											);

										if(ll_liquidacionTmp != null)
										{
											ll_liquidacion.setValorMayorValor(ll_liquidacionTmp.getValorMayorValor());
											ll_liquidacion.setValorSaldoFavor(ll_liquidacionTmp.getValorSaldoFavor());
											ll_liquidacion.setValorTotal(ll_liquidacionTmp.getValorTotal());
											ll_liquidacion.setTotalMayorValor(ll_liquidacionTmp.getTotalMayorValor());
											ll_liquidacion.setCuantiaActo(
											    StringUtils.getString(lrmv_registroMayorValor.getCuantia())
											);
											ll_liquidacion.setValorAvaluo(lrmv_registroMayorValor.getValorAvaluo());
											ll_liquidacion.setCantidadActos(lrmv_registroMayorValor.getCantidadActos());
											ll_liquidacion.setMotivoMayorValor(
											    lrmv_registroMayorValor.getConceptoCobroMayorValor()
											);
										}

										lcl_actosLiquidacion.add(ll_liquidacion);
									}
								}
							}
						}
					}
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findActoLiquidacionItem", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		if(lcl_actosLiquidacion.isEmpty())
			lcl_actosLiquidacion = null;

		return lcl_actosLiquidacion;
	}

	/**
	 * Retorna el valor del objeto de Collection.
	 *
	 * @param as_solicitud correspondiente al valor del tipo de objeto Solicitud
	 * @return devuelve el valor de Collection
	 * @throws B2BException Señala que se ha producido una excepción
	 *
	 */
	public synchronized Collection<Liquidacion> findCondicionesLiquidacion(Solicitud as_solicitud)
	    throws B2BException
	{
		Collection<Liquidacion> acds_documentoSalida;
		DAOManager              ldm_manager;

		acds_documentoSalida     = null;
		ldm_manager              = DaoManagerFactory.getDAOManager();

		try
		{
			if(as_solicitud != null)
				acds_documentoSalida = DaoCreator.getAccLiquidacionDAO(ldm_manager)
						                             .findCondicionesLiquidacion(as_solicitud);
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findCondicionesLiquidacion", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return acds_documentoSalida;
	}

	/**
	 * Metodo encargado de generar el pdf de cuenta de cobro para mayor valor y de guardarlo en documentos salida.
	 *
	 * @param ath_turnoHistoria correspondiente al valor del tipo de objeto TurnoHistoria
	 * @param as_userId Argumento de tipo String que contiene el usuario que realiza la generación del documento.
	 * @param as_remoteIp Argumento de tipo String que contiene la ip de la maquina desde la cual se realiza la
	 * generación del documento.
	 * @return devuelve el valor de byte[]
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public synchronized byte[] generarPDFCobroMayorValor(
	    TurnoHistoria ath_turnoHistoria, String as_userId, String as_remoteIp
	)
	    throws B2BException
	{
		byte[] lba_archivo;

		lba_archivo = null;

		if(ath_turnoHistoria != null)
		{
			DAOManager ldm_manager;

			ldm_manager = DaoManagerFactory.getDAOManager();

			try
			{
				DocumentosSalida lds_documentosSalida;
				lds_documentosSalida     = new DocumentosSalida();
				lba_archivo              = generarPDFCobroMayorValor(
					    ath_turnoHistoria, ldm_manager, as_userId, as_remoteIp, lds_documentosSalida, false
					);

				if(lba_archivo != null)
					insertarImagenDocumentosSalida(
					    ldm_manager, ath_turnoHistoria, lba_archivo, ConstanteCommon.TIPO_DOCUMENTAL_COBRO_MV, as_userId,
					    as_remoteIp, lds_documentosSalida
					);
			}
			catch(B2BException lb2be_e)
			{
				ldm_manager.setRollbackOnly();

				clh_LOGGER.error("generarPDFCobroMayorValor", lb2be_e);

				throw lb2be_e;
			}
			catch(Exception lb2be_e)
			{
				ldm_manager.setRollbackOnly();

				clh_LOGGER.error("generarPDFCobroMayorValor", lb2be_e);

				throw new B2BException(ErrorKeys.CONTAINER_ERROR, lb2be_e);
			}
			finally
			{
				ldm_manager.close();
			}
		}

		return lba_archivo;
	}

	/**
	 * Genera pdf de liquidación y lo guarda en la base de datos.
	 *
	 * @param ath_parametros Objeto contenedor de la información que debe ir en el PDF
	 * @param adm_manager Objeto para manipular la conexión con la base de datos
	 * @return devuelve el valor de byte[]
	 * @throws Exception Señala que se ha producido una excepción
	 */
	public synchronized byte[] generarPDFCobroMayorValor(
	    TurnoHistoria ath_parametros, DAOManager adm_manager, String as_userId, String as_remoteIp,
	    DocumentosSalida ads_documentoSalida, boolean ab_firma
	)
	    throws Exception
	{
		byte[] lba_reciboLiquidacionItem;

		lba_reciboLiquidacionItem = null;

		if(ath_parametros != null)
		{
			TurnoHistoriaDAO lthd_thd;
			Constantes       lc_plantilla;

			lc_plantilla = new Constantes();
			lc_plantilla.setIdConstante(ConstanteCommon.PLANTILLA_COBRO_MAYOR_VALOR);
			lthd_thd     = DaoCreator.getTurnoHistoriaDAO(adm_manager);

			lc_plantilla = DaoCreator.getConstantesDAO(adm_manager).findImagen(lc_plantilla);

			if(lc_plantilla != null)
			{
				byte[] lba_plantilla;

				lba_plantilla = lc_plantilla.getImagenes();

				if(lba_plantilla != null)
				{
					String ls_plantilla;

					ls_plantilla = new String(lba_plantilla);

					if(StringUtils.isValidString(ls_plantilla))
					{
						TurnoHistoria lth_detalle;

						lth_detalle = lthd_thd.findById(ath_parametros);

						if(lth_detalle != null)
						{
							Map<String, String> lmss_datos;
							Constantes          lc_c;
							String              ls_tag;

							lmss_datos     = null;
							ls_tag         = null;

							{
								ls_tag = "<TAG_FECHA>";

								if(ls_plantilla.contains(ls_tag))
								{
									Date ld_fechaActual;
									ld_fechaActual     = new Date();

									ls_plantilla = ls_plantilla.replace(
										    ls_tag,
										    StringUtils.getString(ld_fechaActual, FormatoFechaCommon.DIA_MES_ANIO)
										);
								}
							}

							String ls_personaDireccionCompleta;
							String ls_departamentoNombre;
							String ls_municipioNombre;
							String ls_correoElectronico;

							ls_personaDireccionCompleta     = null;
							ls_departamentoNombre           = null;
							ls_municipioNombre              = null;
							ls_correoElectronico            = null;

							{
								ls_tag = "<TAG_INTERESADO>";

								if(ls_plantilla.contains(ls_tag))
								{
									SolicitudInterviniente lsi_solicitudInterviniente;
									lsi_solicitudInterviniente = new SolicitudInterviniente();
									lsi_solicitudInterviniente.setIdSolicitud(lth_detalle.getIdSolicitud());

									lsi_solicitudInterviniente = DaoCreator.getSolicitudIntervinienteDAO(adm_manager)
											                                   .findByIdSolicitudUltimoModificado(
											    lsi_solicitudInterviniente
											);

									if(lsi_solicitudInterviniente != null)
									{
										Persona lp_persona;

										lp_persona = new Persona();
										lp_persona.setIdPersona(lsi_solicitudInterviniente.getIdPersona());
										lsi_solicitudInterviniente.getIdPersona();

										lp_persona = DaoCreator.getPersonaDAO(adm_manager).findById(lp_persona);

										if(lp_persona != null)
										{
											String ls_personaNombre;
											ls_personaNombre = lp_persona.getNombreCompleto();

											if(StringUtils.isValidString(ls_personaNombre))
												ls_plantilla = ls_plantilla.replace(
													    ls_tag, StringUtils.getString(ls_personaNombre)
													);
											else
												ls_plantilla = ls_plantilla.replace(ls_tag, " ");

											String lsi_autorizacionNotificacion;
											lsi_autorizacionNotificacion = lsi_solicitudInterviniente
													.getIdAutorizacionNotificacion();

											if(StringUtils.isValidString(lsi_autorizacionNotificacion))
											{
												if(
												    lsi_autorizacionNotificacion.equalsIgnoreCase("1")
													    || lsi_autorizacionNotificacion.equalsIgnoreCase("4")
												)
												{
													PersonaCorreoElectronico lpc_personaCorreoElectronico;
													lpc_personaCorreoElectronico = new PersonaCorreoElectronico();
													lpc_personaCorreoElectronico.setIdPersona(
													    lsi_solicitudInterviniente.getIdPersona()
													);
													lpc_personaCorreoElectronico.setIdCorreoElectronico(
													    lsi_solicitudInterviniente.getIdCorreoElectronico()
													);

													lpc_personaCorreoElectronico = DaoCreator.getPersonaCorreoElectronicoDAO(
														    adm_manager
														).findById(lpc_personaCorreoElectronico);

													if(lpc_personaCorreoElectronico != null)
														ls_correoElectronico = lpc_personaCorreoElectronico
																.getCorreoElectronico();
												}
												else if(
												    lsi_autorizacionNotificacion.equalsIgnoreCase("2")
													    || lsi_autorizacionNotificacion.equalsIgnoreCase("3")
												)
												{
													PersonaDireccion lpd_personaDireccion;
													lpd_personaDireccion = new PersonaDireccion();
													lpd_personaDireccion.setIdPersona(
													    lsi_solicitudInterviniente.getIdPersona()
													);
													lpd_personaDireccion.setIdDireccion(
													    lsi_solicitudInterviniente.getIdDireccion()
													);

													lpd_personaDireccion = DaoCreator.getPersonaDireccionDAO(
														    adm_manager
														).findById(lpd_personaDireccion);

													if(lpd_personaDireccion != null)
													{
														Departamento ld_departamento;
														ld_departamento = new Departamento();

														ld_departamento.setIdPais(lpd_personaDireccion.getIdPais());
														ld_departamento.setIdDepartamento(
														    lpd_personaDireccion.getIdDepartamento()
														);

														ld_departamento = DaoCreator.getDepartamentoDAO(adm_manager)
																                        .findById(ld_departamento);

														if(ld_departamento != null)
															ls_departamentoNombre = ld_departamento.getNombre();

														Municipio lm_municipio;
														lm_municipio = new Municipio();

														lm_municipio.setIdPais(lpd_personaDireccion.getIdPais());
														lm_municipio.setIdDepartamento(
														    lpd_personaDireccion.getIdDepartamento()
														);
														lm_municipio.setIdMunicipio(
														    lpd_personaDireccion.getIdMunicipio()
														);

														lm_municipio = DaoCreator.getMunicipioDAO(adm_manager)
																                     .findById(lm_municipio);

														if(lm_municipio != null)
															ls_municipioNombre = lm_municipio.getNombre();

														lpd_personaDireccion = DaoCreator.getPersonaDireccionDAO(
															    adm_manager
															).findById(lpd_personaDireccion);

														if(lpd_personaDireccion != null)
															ls_personaDireccionCompleta = lpd_personaDireccion
																	.getDireccion();
													}
												}
											}
										}
									}
								}
							}

							{
								ls_tag = TagCommon.TAG_INTERESADO_MV;

								if(ls_plantilla.contains(ls_tag))
								{
									SolicitudInterviniente lsi_solicitudInterviniente;
									lsi_solicitudInterviniente = new SolicitudInterviniente();
									lsi_solicitudInterviniente.setIdSolicitud(lth_detalle.getIdSolicitud());

									lsi_solicitudInterviniente = DaoCreator.getSolicitudIntervinienteDAO(adm_manager)
											                                   .findByIdSolicitudUltimoModificado(
											    lsi_solicitudInterviniente
											);

									if(lsi_solicitudInterviniente != null)
									{
										Persona lp_persona;

										if(!ab_firma)
											lp_persona = ath_parametros.getPersona();
										else
										{
											lp_persona = new Persona();
											lp_persona.setIdPersona(lsi_solicitudInterviniente.getIdPersona());
											lsi_solicitudInterviniente.getIdPersona();

											lp_persona = DaoCreator.getPersonaDAO(adm_manager).findById(lp_persona);
										}

										if(lp_persona != null)
										{
											String ls_personaNombre;
											ls_personaNombre = lp_persona.getNombreCompleto();

											if(StringUtils.isValidString(ls_personaNombre))
												ls_plantilla = ls_plantilla.replace(
													    ls_tag, StringUtils.getString(ls_personaNombre)
													);
											else
												ls_plantilla = ls_plantilla.replace(ls_tag, " ");

											String lsi_autorizacionNotificacion;
											lsi_autorizacionNotificacion = lsi_solicitudInterviniente
													.getIdAutorizacionNotificacion();

											if(StringUtils.isValidString(lsi_autorizacionNotificacion))
											{
												if(
												    lsi_autorizacionNotificacion.equalsIgnoreCase("1")
													    || lsi_autorizacionNotificacion.equalsIgnoreCase("4")
												)
												{
													OficiosTexto lot_oficiosTexto;
													lot_oficiosTexto = DaoCreator.getOficiosTextoDAO(adm_manager)
															                         .findByIdSolicitud(
															    lth_detalle.getIdSolicitud()
															);

													if(lot_oficiosTexto != null)
													{
														PersonaCorreoElectronico lpc_personaCorreoElectronico;
														lpc_personaCorreoElectronico = new PersonaCorreoElectronico();
														lpc_personaCorreoElectronico.setIdPersona(
														    lot_oficiosTexto.getIdPersonaNotificar()
														);
														lpc_personaCorreoElectronico.setIdCorreoElectronico(
														    lsi_solicitudInterviniente.getIdCorreoElectronico()
														);

														lpc_personaCorreoElectronico = DaoCreator.getPersonaCorreoElectronicoDAO(
															    adm_manager
															).findById(lpc_personaCorreoElectronico);

														if(lpc_personaCorreoElectronico != null)
															ls_correoElectronico = lpc_personaCorreoElectronico
																	.getCorreoElectronico();
													}
												}
												else if(
												    lsi_autorizacionNotificacion.equalsIgnoreCase("2")
													    || lsi_autorizacionNotificacion.equalsIgnoreCase("3")
												)
												{
													PersonaDireccion lpd_personaDireccion;
													lpd_personaDireccion = new PersonaDireccion();
													lpd_personaDireccion.setIdPersona(
													    lsi_solicitudInterviniente.getIdPersona()
													);
													lpd_personaDireccion.setIdDireccion(
													    lsi_solicitudInterviniente.getIdDireccion()
													);

													lpd_personaDireccion = DaoCreator.getPersonaDireccionDAO(
														    adm_manager
														).findById(lpd_personaDireccion);

													if(lpd_personaDireccion != null)
													{
														Departamento ld_departamento;
														ld_departamento = new Departamento();

														ld_departamento.setIdPais(lpd_personaDireccion.getIdPais());
														ld_departamento.setIdDepartamento(
														    lpd_personaDireccion.getIdDepartamento()
														);

														ld_departamento = DaoCreator.getDepartamentoDAO(adm_manager)
																                        .findById(ld_departamento);

														if(ld_departamento != null)
															ls_departamentoNombre = ld_departamento.getNombre();

														Municipio lm_municipio;
														lm_municipio = new Municipio();

														lm_municipio.setIdPais(lpd_personaDireccion.getIdPais());
														lm_municipio.setIdDepartamento(
														    lpd_personaDireccion.getIdDepartamento()
														);
														lm_municipio.setIdMunicipio(
														    lpd_personaDireccion.getIdMunicipio()
														);

														lm_municipio = DaoCreator.getMunicipioDAO(adm_manager)
																                     .findById(lm_municipio);

														if(lm_municipio != null)
															ls_municipioNombre = lm_municipio.getNombre();

														lpd_personaDireccion = DaoCreator.getPersonaDireccionDAO(
															    adm_manager
															).findById(lpd_personaDireccion);

														if(lpd_personaDireccion != null)
															ls_personaDireccionCompleta = lpd_personaDireccion
																	.getDireccion();
													}
												}
											}
										}
									}
								}
							}

							{
								ls_tag = "<TAG_DIRECCION_INTERESADOS>";

								if(ls_plantilla.contains(ls_tag))
								{
									if(StringUtils.isValidString(ls_personaDireccionCompleta))
										ls_plantilla = saltoDeCarroDespues(
											    ls_plantilla, ls_tag, StringUtils.getString(
											        ls_personaDireccionCompleta
											    )
											);
									else
										ls_plantilla = ls_plantilla.replace(ls_tag, " ");
								}
							}

							{
								ls_tag = "<TAG_DETO>";

								if(ls_plantilla.contains(ls_tag) && ls_plantilla.contains("<TAG_MUNICIPIO>"))
								{
									if(
									    StringUtils.isValidString(ls_departamentoNombre)
										    && StringUtils.isValidString(ls_municipioNombre)
									)
										ls_plantilla = escribirDepartamentoMunicipioInteresado(
											    ls_plantilla, "<TAG_MUNICIPIO>", ls_municipioNombre, ls_tag,
											    ls_departamentoNombre
											);
								}

								else if(ls_plantilla.contains(ls_tag))
								{
									if(StringUtils.isValidString(ls_departamentoNombre))
										ls_plantilla = ls_plantilla.replace(
											    ls_tag, StringUtils.getString(ls_departamentoNombre)
											);
									else
										ls_plantilla = ls_plantilla.replace(ls_tag, " ");
								}

								else if(ls_plantilla.contains("<TAG_MUNICIPIO>"))
								{
									if(StringUtils.isValidString(ls_municipioNombre))
										ls_plantilla = ls_plantilla.replace(
											    "<TAG_MUNICIPIO>", StringUtils.getString(ls_municipioNombre)
											);
									else
										ls_plantilla = ls_plantilla.replace(ls_tag, " ");
								}
							}

							{
								ls_tag = "<TAG_CORREO_INTERESADO>";

								if(ls_plantilla.contains(ls_tag))
								{
									if(StringUtils.isValidString(ls_correoElectronico))
										ls_plantilla = ls_plantilla.replace(
											    ls_tag, StringUtils.getString(ls_correoElectronico)
											);
									else
										ls_plantilla = ls_plantilla.replace(ls_tag, " ");
								}
							}

							{
								ls_tag = "<TAG_ID_TURNO>";

								if(ls_plantilla.contains(ls_tag))
								{
									String ls_idTurno;
									ls_idTurno = lth_detalle.getIdTurno();

									if(StringUtils.isValidString(ls_idTurno))
										ls_plantilla = ls_plantilla.replace(ls_tag, StringUtils.getString(ls_idTurno));
									else
										ls_plantilla = ls_plantilla.replace(ls_tag, " ");
								}
							}

							{
								ls_tag = "<TAG_RESOLUCION_TARIFA_REGISTRAL>";

								if(ls_plantilla.contains(ls_tag))
								{
									lc_c = new Constantes();
									lc_c.setIdConstante(ConstanteCommon.RESOLUCION_TARIFA_REGISTRAL);

									lc_c = DaoCreator.getConstantesDAO(adm_manager).findById(lc_c);

									if(lc_c != null)
									{
										BigInteger lbi_entero;
										lbi_entero = lc_c.getEntero();

										if(NumericUtils.isValidBigInteger(lbi_entero))
											ls_plantilla = ls_plantilla.replace(
												    ls_tag, StringUtils.getString(lbi_entero)
												);
										else
											ls_plantilla = ls_plantilla.replace(ls_tag, " ");
									}
									else
										ls_plantilla = ls_plantilla.replace(ls_tag, " ");
								}
							}

							{
								ls_tag = "<TAG_FECHA_RESOLUCION_REGISTRAL>";

								if(ls_plantilla.contains(ls_tag))
								{
									lc_c = new Constantes();
									lc_c.setIdConstante(ConstanteCommon.FECHA_RESOLUCION_TARIFA_REGISTRAL);

									lc_c = DaoCreator.getConstantesDAO(adm_manager).findById(lc_c);

									if(lc_c != null)
									{
										String ls_fecha;
										ls_fecha = new SimpleDateFormat("dd-MM-yyyy").format(lc_c.getFecha());

										if(ls_fecha != null)
											ls_plantilla = ls_plantilla.replace(ls_tag, ls_fecha);
										else
											ls_plantilla = ls_plantilla.replace(ls_tag, " ");
									}
									else
										ls_plantilla = ls_plantilla.replace(ls_tag, " ");
								}
							}

							if(ab_firma)
							{
								String ls_consecutivoOficio;
								String ls_referenciaSalida;
								Date   ld_fechaOficio;

								ls_consecutivoOficio     = null;
								ls_referenciaSalida      = null;
								ld_fechaOficio           = null;

								{
									ls_tag = "<TAG_OFICIO>";

									if(ls_plantilla.contains(ls_tag))
									{
										NumeracionOficiosCirculo lnoc_numeracionOficios;
										lnoc_numeracionOficios = findNumeracionOficiosCirculo(
											    lth_detalle, adm_manager, as_userId, as_remoteIp, true
											);

										if(lnoc_numeracionOficios != null)
										{
											ls_consecutivoOficio = lnoc_numeracionOficios.getConsecutivoGenerado();

											if(StringUtils.isValidString(ls_consecutivoOficio))
											{
												ls_plantilla     = ls_plantilla.replace(ls_tag, ls_consecutivoOficio);

												ld_fechaOficio = new Date();
												ads_documentoSalida.setConsecutivoOficio(ls_consecutivoOficio);

												ads_documentoSalida.setFechaOficio(ld_fechaOficio);
											}
										}
									}
								}

								{
									ls_tag = "<TAG_REFERENCIA_SALIDA>";

									if(ls_plantilla.contains(ls_tag))
									{
										ls_referenciaSalida     = generarIdCorrespondencia(
											    lth_detalle, adm_manager, false
											);

										ls_plantilla = ls_plantilla.replace(ls_tag, ls_referenciaSalida);

										ads_documentoSalida.setReferenciaSalida(ls_referenciaSalida);
									}
								}
							}

							ls_tag = "<TAG_VALOR_MAYOR_VALOR>";

							Liquidacion ll_liquidacion;

							ll_liquidacion = new Liquidacion();

							if(ls_plantilla.contains(ls_tag))
							{
								String ls_idTurno;

								ls_idTurno = lth_detalle.getIdTurno();

								if(StringUtils.isValidString(ls_idTurno))
								{
									Collection<Liquidacion> lcl_liquidacion;

									ll_liquidacion.setIdTurno(ls_idTurno);

									lcl_liquidacion = findActoLiquidacionItem(ll_liquidacion);

									if(CollectionUtils.isValidCollection(lcl_liquidacion))
									{
										BigDecimal lbd_totalMayorValorTotal;

										lbd_totalMayorValorTotal = BigDecimal.ZERO;

										for(Liquidacion ll_liqTMP : lcl_liquidacion)
										{
											if(ll_liqTMP != null)
											{
												BigDecimal lbd_totalMayorValor;

												lbd_totalMayorValor = ll_liqTMP.getTotalMayorValor();
												ll_liquidacion.setMotivoMayorValor(ll_liqTMP.getMotivoMayorValor());

												if(NumericUtils.isValidBigDecimal(lbd_totalMayorValor))
													lbd_totalMayorValorTotal = lbd_totalMayorValorTotal.add(
														    lbd_totalMayorValor
														);
											}
										}

										{
											StringBuilder lsb_sb;
											String        ls_totalMayorValor;

											lsb_sb                 = new StringBuilder();
											ls_totalMayorValor     = conversionCientifica(lbd_totalMayorValorTotal);

											lsb_sb.append(IdentificadoresCommon.SIGNO_PESOS);
											lsb_sb.append(ls_totalMayorValor);
											lsb_sb.append(" (");
											lsb_sb.append(
											    NumericUtils.convertirLetras(
											        NumericUtils.getDouble(lbd_totalMayorValorTotal, 0D), true
											    )
											);
											lsb_sb.append(")");

											ls_plantilla = ls_plantilla.replace(ls_tag, lsb_sb.toString());
										}
									}
								}
							}

							ls_tag = "<TAG_MOTIVO_MAYOR_VALOR>";

							if(ls_plantilla.contains(ls_tag))
							{
								if(ll_liquidacion != null)
								{
									String ls_motivoMayorValor;
									ls_motivoMayorValor = ll_liquidacion.getMotivoMayorValor();

									if(StringUtils.isValidString(ls_motivoMayorValor))
										ls_plantilla = ls_plantilla.replace(
											    ls_tag, StringUtils.getString(ls_motivoMayorValor)
											);
									else
										ls_plantilla = ls_plantilla.replace(ls_tag, " ");
								}
							}

							{
								ls_tag = "<TAG_CIRCULO_REGISTRAL>";

								if(ls_plantilla.contains(ls_tag))
								{
									CirculoRegistral lcr_circuloRegistral;
									lcr_circuloRegistral = new CirculoRegistral();
									lcr_circuloRegistral.setIdCirculo(lth_detalle.getIdCirculoUsuario());

									lcr_circuloRegistral = DaoCreator.getCirculoRegistralDAO(adm_manager)
											                             .findById(lcr_circuloRegistral);

									if(lcr_circuloRegistral != null)
									{
										String ls_idCirculo;
										ls_idCirculo = lcr_circuloRegistral.getNombre();

										if(StringUtils.isValidString(ls_idCirculo))
											ls_plantilla = ls_plantilla.replace(
												    ls_tag, StringUtils.getString(ls_idCirculo)
												);
										else
											ls_plantilla = ls_plantilla.replace(ls_tag, " ");
									}
								}
							}

							{
								ls_tag = "<TAG_TABLA_MAYOR_VALOR>";

								if(ls_plantilla.contains(ls_tag))
								{
									int li_finalTag;

									li_finalTag = ls_plantilla.lastIndexOf(ls_tag);

									if(li_finalTag > 0)
									{
										String ls_textoMitadSuperior;
										String ls_textoMitadInferior;
										String ls_tagNew;

										ls_textoMitadSuperior     = ls_plantilla.substring(0, li_finalTag);
										ls_textoMitadInferior     = ls_plantilla.substring(
											    li_finalTag + ls_tag.length()
											);

										ls_tagNew     = "{\\*\\bkmkstart TABLA_MAYOR_VALOR}{\\*\\bkmkend TABLA_MAYOR_VALOR} \\line "
											+ ls_tag;

										ls_plantilla = ls_textoMitadSuperior + IdentificadoresCommon.ESPACIO_VACIO
											+ ls_tagNew + IdentificadoresCommon.ESPACIO_VACIO + ls_textoMitadInferior;
									}
								}
							}

							String     ls_tagUsuarioFirma;
							Constantes lc_usuarioFirma;

							int li_incrX = 0;
							int li_incrY = 0;

							ls_tagUsuarioFirma = null;
							lc_usuarioFirma = null;

							{
								ls_tagUsuarioFirma = "<TAG_USUARIO_FIRMA_SUSPENSION>";

								if(ls_plantilla.contains(ls_tagUsuarioFirma))
								{
									lc_usuarioFirma = new Constantes();

									lc_usuarioFirma.setIdConstante(ConstanteCommon.USUARIO_FIRMA_SUSPENSION);

									lc_usuarioFirma     = DaoCreator.getConstantesDAO(adm_manager)
											                            .findByIdWithImage(lc_usuarioFirma);

									ls_plantilla = getFirmaMasivaBusiness()
											               .reemplazarUsuarioFirmaCargo(
											    ls_plantilla, lc_usuarioFirma, ls_tagUsuarioFirma,
											    "<TAG_CARGO_FIRMA_SUSPENSION>"
											);
								}
							}

							lmss_datos       = finalizarPlantilla(
								    ls_plantilla, lth_detalle.getIdTurnoHistoria(), adm_manager
								);
							ls_plantilla     = lmss_datos.get(IdentificadoresCommon.PLANTILLA);

							{
								ByteArrayInputStream      lbais_docInStream;
								ByteArrayOutputStream     lbaos_docOutStream;
								Collection<Liquidacion>   lcl_liquidacion;
								com.aspose.words.Document ld_doc;
								DocumentBuilder           ldb_builder;
								Table                     lt_table;

								lbais_docInStream      = new ByteArrayInputStream(ls_plantilla.getBytes());
								lbaos_docOutStream     = new ByteArrayOutputStream();
								ld_doc                 = new com.aspose.words.Document(lbais_docInStream);
								ldb_builder            = new DocumentBuilder(ld_doc);

								ldb_builder.moveToBookmark("TABLA_MAYOR_VALOR");

								Liquidacion ll_liquidacionTMP;
								ll_liquidacionTMP = new Liquidacion();

								ll_liquidacionTMP.setIdTurno(lth_detalle.getIdTurno());

								lcl_liquidacion = findActoLiquidacionItem(ll_liquidacionTMP);

								if(CollectionUtils.isValidCollection(lcl_liquidacion))
								{
									int     li_tamanoLetra;
									long    ll_porcentajetablas;
									ActoDAO la_DAO;

									la_DAO                  = DaoCreator.getActoDAO(adm_manager);
									ll_porcentajetablas     = (long)10.0;
									li_tamanoLetra          = 8;
									lt_table                = ldb_builder.startTable();

									ldb_builder.insertCell();
									ldb_builder.getParagraphFormat().setAlignment(ParagraphAlignment.LEFT);
									ldb_builder.getCellFormat().setHorizontalMerge(CellMerge.NONE);
									ldb_builder.getFont().setUnderline(0);
									ldb_builder.getCellFormat()
										           .setPreferredWidth(PreferredWidth.fromPercent(ll_porcentajetablas));

									ldb_builder.getFont().setSize(li_tamanoLetra);
									ldb_builder.write("Acción");

									ldb_builder.insertCell();
									ldb_builder.getCellFormat().setHorizontalMerge(CellMerge.NONE);
									ldb_builder.getCellFormat()
										           .setPreferredWidth(PreferredWidth.fromPercent(ll_porcentajetablas));
									ldb_builder.getFont().setSize(li_tamanoLetra);
									ldb_builder.write("Código Acto - Descripción");

									ldb_builder.insertCell();
									ldb_builder.getCellFormat().setHorizontalMerge(CellMerge.NONE);
									ldb_builder.getCellFormat()
										           .setPreferredWidth(PreferredWidth.fromPercent(ll_porcentajetablas));
									ldb_builder.getFont().setSize(li_tamanoLetra);
									ldb_builder.write("¿Es un acto sin cuantía?");

									ldb_builder.insertCell();
									ldb_builder.getCellFormat().setHorizontalMerge(CellMerge.NONE);
									ldb_builder.getCellFormat()
										           .setPreferredWidth(PreferredWidth.fromPercent(ll_porcentajetablas));
									ldb_builder.getFont().setSize(li_tamanoLetra);
									ldb_builder.write("Tipo Tarifa");

									ldb_builder.insertCell();
									ldb_builder.getCellFormat().setHorizontalMerge(CellMerge.NONE);
									ldb_builder.getCellFormat()
										           .setPreferredWidth(PreferredWidth.fromPercent(ll_porcentajetablas));
									ldb_builder.getFont().setSize(li_tamanoLetra);
									ldb_builder.write("Cuantía acto");

									ldb_builder.insertCell();
									ldb_builder.getCellFormat().setHorizontalMerge(CellMerge.NONE);
									ldb_builder.getCellFormat()
										           .setPreferredWidth(PreferredWidth.fromPercent(ll_porcentajetablas));
									ldb_builder.getFont().setSize(li_tamanoLetra);
									ldb_builder.write("Valor avalúo");

									ldb_builder.insertCell();
									ldb_builder.getCellFormat().setHorizontalMerge(CellMerge.NONE);
									ldb_builder.getCellFormat()
										           .setPreferredWidth(PreferredWidth.fromPercent(ll_porcentajetablas));
									ldb_builder.getFont().setSize(li_tamanoLetra);
									ldb_builder.write("Cant. Actos");

									ldb_builder.insertCell();
									ldb_builder.getCellFormat().setHorizontalMerge(CellMerge.NONE);
									ldb_builder.getCellFormat()
										           .setPreferredWidth(PreferredWidth.fromPercent(ll_porcentajetablas));
									ldb_builder.getFont().setSize(li_tamanoLetra);
									ldb_builder.write("Mayor Valor");

									ldb_builder.endRow();

									for(Liquidacion ll_tmpLiquidacion : lcl_liquidacion)
									{
										com.bachue.snr.prosnr01.model.registro.Acto la_acto;

										la_acto = ll_tmpLiquidacion.getActo();

										if((ll_tmpLiquidacion != null) && (la_acto != null))
										{
											String ls_idActo;
											String ls_tipoActo;
											String ls_idTipoTarifa;

											ls_idActo           = la_acto.getIdActoDb();
											ls_tipoActo         = IdentificadoresCommon.DATO_INVALIDO;
											ls_idTipoTarifa     = null;

											if(StringUtils.isValidString(ls_idActo))
											{
												Acto la_actoDB;

												la_actoDB = la_DAO.findById(ls_idActo);

												if(la_actoDB != null)
												{
													ls_tipoActo         = la_actoDB.getIdTipoActo();
													ls_idTipoTarifa     = la_actoDB.getIdTipoTarifaRegistral();
												}
											}

											ldb_builder.insertCell();
											ldb_builder.getCellFormat().setHorizontalMerge(CellMerge.NONE);
											ldb_builder.getCellFormat()
												           .setPreferredWidth(
												    PreferredWidth.fromPercent(ll_porcentajetablas)
												);
											ldb_builder.getFont().setSize(li_tamanoLetra);
											ldb_builder.getFont().setBold(false);

											String ls_indicadorMayorValor;
											ls_indicadorMayorValor = la_acto.getIndMayorValor();

											if(StringUtils.isValidString(ls_indicadorMayorValor))
											{
												if(ls_indicadorMayorValor.equalsIgnoreCase(EstadoCommon.I))
													ldb_builder.write(IdentificadoresCommon.ACTO_AGREGADO);
												else if(ls_indicadorMayorValor.equalsIgnoreCase(EstadoCommon.M))
													ldb_builder.write(IdentificadoresCommon.ACTO_MODIFICADO);
											}

											ldb_builder.insertCell();
											ldb_builder.getCellFormat().setHorizontalMerge(CellMerge.NONE);
											ldb_builder.getCellFormat()
												           .setPreferredWidth(
												    PreferredWidth.fromPercent(ll_porcentajetablas)
												);
											ldb_builder.getFont().setSize(li_tamanoLetra);
											ldb_builder.getFont().setBold(false);
											ldb_builder.write(
											    StringUtils.getStringNotNull(ls_tipoActo)
											    + IdentificadoresCommon.SIMBOLO_GUION_ESPACIOS
											    + la_acto.getEspecificacion()
											);

											ldb_builder.insertCell();
											ldb_builder.getCellFormat().setHorizontalMerge(CellMerge.NONE);
											ldb_builder.getCellFormat()
												           .setPreferredWidth(
												    PreferredWidth.fromPercent(ll_porcentajetablas)
												);
											ldb_builder.getFont().setSize(li_tamanoLetra);
											ldb_builder.getFont().setBold(false);
											ldb_builder.write(
											    StringUtils.getStringNotNull(la_acto.getActoSinCuantia())
											);

											ldb_builder.insertCell();
											ldb_builder.getCellFormat().setHorizontalMerge(CellMerge.NONE);
											ldb_builder.getCellFormat()
												           .setPreferredWidth(
												    PreferredWidth.fromPercent(ll_porcentajetablas)
												);
											ldb_builder.getFont().setSize(li_tamanoLetra);
											ldb_builder.getFont().setBold(false);
											ldb_builder.write(StringUtils.getStringNotNull(ls_idTipoTarifa));

											ldb_builder.insertCell();
											ldb_builder.getCellFormat().setHorizontalMerge(CellMerge.NONE);
											ldb_builder.getCellFormat()
												           .setPreferredWidth(
												    PreferredWidth.fromPercent(ll_porcentajetablas)
												);
											ldb_builder.getFont().setSize(li_tamanoLetra);
											ldb_builder.getFont().setBold(false);
											ldb_builder.write(
											    "$"
											    + conversionCientifica(
											        NumericUtils.getBigDecimal(
											            NumericUtils.getDouble(ll_tmpLiquidacion.getCuantiaActo())
											        )
											    )
											);

											ldb_builder.insertCell();
											ldb_builder.getCellFormat().setHorizontalMerge(CellMerge.NONE);
											ldb_builder.getCellFormat()
												           .setPreferredWidth(
												    PreferredWidth.fromPercent(ll_porcentajetablas)
												);
											ldb_builder.getFont().setSize(li_tamanoLetra);
											ldb_builder.getFont().setBold(false);
											ldb_builder.write(
											    "$" + conversionCientifica(ll_tmpLiquidacion.getValorAvaluo())
											);

											ldb_builder.insertCell();
											ldb_builder.getCellFormat().setHorizontalMerge(CellMerge.NONE);
											ldb_builder.getCellFormat()
												           .setPreferredWidth(
												    PreferredWidth.fromPercent(ll_porcentajetablas)
												);
											ldb_builder.getFont().setSize(li_tamanoLetra);
											ldb_builder.getFont().setBold(false);
											ldb_builder.write(
											    StringUtils.getStringNotNull(
											        StringUtils.getString(ll_tmpLiquidacion.getCantidadActos())
											    )
											);

											ldb_builder.insertCell();
											ldb_builder.getCellFormat().setHorizontalMerge(CellMerge.NONE);
											ldb_builder.getCellFormat()
												           .setPreferredWidth(
												    PreferredWidth.fromPercent(ll_porcentajetablas)
												);
											ldb_builder.getFont().setSize(li_tamanoLetra);
											ldb_builder.getFont().setBold(false);
											ldb_builder.write(
											    "$" + conversionCientifica(ll_tmpLiquidacion.getTotalMayorValor())
											);

											ldb_builder.endRow();
										}
									}

									lt_table.setAlignment(TableAlignment.CENTER);
									ldb_builder.endTable();
								}

								ldb_builder.writeln(ControlChar.LINE_BREAK);

								ld_doc.save(lbaos_docOutStream, SaveFormat.DOC);

								byte[] docBytes = lbaos_docOutStream.toByteArray();

								lba_reciboLiquidacionItem = new PDFGenerator().convertirRTFToPDF(docBytes, adm_manager);

								if(lba_reciboLiquidacionItem == null)
									throw new B2BException(ErrorKeys.ERROR_GENERANDO_PDF_COBRO_MAYOR_VALOR);

								if(ab_firma)
								{
									byte[] lba_grafo;

									lba_grafo = null;

									if(lc_usuarioFirma != null)
									{
										lba_grafo     = lc_usuarioFirma.getImagenes();
										li_incrX      = NumericUtils.getInt(lc_usuarioFirma.getEntero());
										li_incrY      = NumericUtils.getInt(lc_usuarioFirma.getReal());
									}

									lba_reciboLiquidacionItem = getFirmaMasivaBusiness()
											                            .reemplazarBookmarsFirma(
											    lba_reciboLiquidacionItem, lba_grafo, li_incrX, li_incrY
											);
								}
							}
						}
					}
				}
			}
		}

		return lba_reciboLiquidacionItem;
	}

	/**
	 * Metodo encargado de generar el pdf de nota informativa para mayor valor y de guardarlo en documentos salida.
	 *
	 * @param ath_turnoHistoria correspondiente al valor del tipo de objeto TurnoHistoria
	 * @param as_userId Argumento de tipo String que contiene el usuario que realiza la generación del documento.
	 * @param as_remoteIp Argumento de tipo String que contiene la ip de la maquina desde la cual se realiza la
	 * generación del documento.
	 * @return devuelve el valor de byte[]
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public synchronized byte[] generarPDFNotaInformativaPorPagoEnExceso(
	    TurnoHistoria ath_turnoHistoria, String as_userId, String as_remoteIp
	)
	    throws B2BException
	{
		byte[] lba_archivo;

		lba_archivo = null;

		if(ath_turnoHistoria != null)
		{
			DAOManager ldm_manager;

			ldm_manager = DaoManagerFactory.getDAOManager();

			try
			{
				DocumentosSalida lds_documentosSalida;
				lds_documentosSalida     = new DocumentosSalida();
				lba_archivo              = generarPDFNotaInformativaPorPagoEnExceso(
					    ath_turnoHistoria, false, ldm_manager, lds_documentosSalida, as_userId, as_remoteIp
					);

				if((lba_archivo != null) && !ath_turnoHistoria.isNoGuardarNotaExcesoDePago())
					insertarImagenDocumentosSalida(
					    ldm_manager, ath_turnoHistoria, lba_archivo, ConstanteCommon.TIPO_DOCUMENTAL_NOTA_INFORMATIVA_MV,
					    as_userId, as_remoteIp, lds_documentosSalida
					);
			}
			catch(B2BException lb2be_e)
			{
				ldm_manager.setRollbackOnly();

				clh_LOGGER.error("generarPDFNotaInformativaPorPagoEnExceso", lb2be_e);

				throw lb2be_e;
			}
			catch(Exception lb2be_e)
			{
				ldm_manager.setRollbackOnly();

				clh_LOGGER.error("generarPDFNotaInformativaPorPagoEnExceso", lb2be_e);

				throw new B2BException(ErrorKeys.CONTAINER_ERROR, lb2be_e);
			}
			finally
			{
				ldm_manager.close();
			}
		}

		return lba_archivo;
	}

	/**
	 * Retorna el valor del objeto de byte[].
	 *
	 * @param ath_parametros correspondiente al valor del tipo de objeto TurnoHistoria
	 * @param ab_firma correspondiente al valor del tipo de objeto boolean
	 * @param adm_manager correspondiente al valor del tipo de objeto DAOManager
	 * @return devuelve el valor de byte[]
	 * @throws Exception Señala que se ha producido una excepción
	 */
	public synchronized byte[] generarPDFNotaInformativaPorPagoEnExceso(
	    TurnoHistoria ath_parametros, boolean ab_firma, DAOManager adm_manager, DocumentosSalida ads_documentoSalida,
	    String as_userId, String as_remoteIp
	)
	    throws B2BException
	{
		byte[] lba_notaInformativa;

		lba_notaInformativa = null;

		if(ath_parametros != null)
		{
			TurnoHistoriaDAO lthd_thd;
			Constantes       lc_plantilla;
			String           ls_constante;
			Constantes       lc_datos;

			ls_constante     = ConstanteCommon.PLANTILLA_NOTA_INFORMATIVA_PAGO_EN_EXCESO_MAYOR_V;
			lc_datos         = new Constantes();
			lc_plantilla     = new Constantes();
			lc_datos.setIdConstante(ls_constante);
			lthd_thd     = DaoCreator.getTurnoHistoriaDAO(adm_manager);

			lc_plantilla = DaoCreator.getConstantesDAO(adm_manager).findImagen(lc_datos);

			if(lc_plantilla != null)
			{
				byte[] lba_plantilla;

				lba_plantilla = lc_plantilla.getImagenes();

				if(lba_plantilla != null)
				{
					String ls_plantilla;

					ls_plantilla = new String(lba_plantilla);

					if(StringUtils.isValidString(ls_plantilla))
					{
						TurnoHistoria lth_detalle;

						lth_detalle = lthd_thd.findById(ath_parametros);

						if(lth_detalle != null)
						{
							Map<String, String> lmss_datos;
							String              ls_tag;
							CirculoRegistral    lcr_circulo;

							lmss_datos      = null;
							lcr_circulo     = new CirculoRegistral();
							ls_tag          = null;

							{
								ls_tag = "<TAG_ID_OFI_REGISTRO>";

								if(ls_plantilla.contains(ls_tag))
								{
									lcr_circulo.setIdCirculo(lth_detalle.getIdCirculoUsuario());

									lcr_circulo = DaoCreator.getCirculoRegistralDAO(adm_manager).findById(lcr_circulo);

									if(lcr_circulo != null)
									{
										String ls_nombreOficina;
										ls_nombreOficina = StringUtils.getStringNotNull(lcr_circulo.getNombre());

										if(StringUtils.isValidString(ls_nombreOficina))
											ls_plantilla = ls_plantilla.replace(
												    ls_tag, StringUtils.getString(ls_nombreOficina)
												);
										else
											ls_plantilla = ls_plantilla.replace(ls_tag, " ");
									}
								}
							}

							{
								String ls_idCirculo;

								ls_tag           = "<TAG_MUN_OFI_ORIGEN>";
								ls_idCirculo     = lcr_circulo.getIdCirculo();

								if(ls_plantilla.contains(ls_tag) && StringUtils.isValidString(ls_idCirculo))
								{
									Municipio lm_municipio;

									lm_municipio     = new Municipio();

									lm_municipio = DaoCreator.getMunicipioDAO(adm_manager).findByIdCirculo(
										    ls_idCirculo
										);

									if(lm_municipio != null)
									{
										String ls_munOficinaOrigen;
										ls_munOficinaOrigen = lm_municipio.getNombre();

										if(
										    ls_plantilla.contains(ls_tag)
											    && StringUtils.isValidString(ls_munOficinaOrigen)
										)
											ls_plantilla = ls_plantilla.replace(ls_tag, ls_munOficinaOrigen);
									}
								}
							}

							{
								ls_tag = "<TAG_FECHA_LARGA>";

								if(ls_plantilla.contains(ls_tag))
								{
									Date   ld_fecha;
									String ls_fechaActual;

									ld_fecha           = new Date();
									ls_fechaActual     = DateUtils.convertirLetrasLarga(ld_fecha);

									if(StringUtils.isValidString(ls_fechaActual))
										ls_plantilla = ls_plantilla.replace(ls_tag, ls_fechaActual.toUpperCase());
								}
							}

							{
								ls_tag = "<TAG_TOTAL_SALDO_VALOR>";

								Liquidacion ll_liquidacion;
								ll_liquidacion = new Liquidacion();

								if(ls_plantilla.contains(ls_tag))
								{
									String ls_idTurno;
									ls_idTurno = lth_detalle.getIdTurno();

									if(StringUtils.isValidString(ls_idTurno))
									{
										Collection<Liquidacion> lcl_liquidacion;
										boolean                 lb_anotacionesEliminadas;

										lb_anotacionesEliminadas = false;

										ll_liquidacion.setIdTurno(ls_idTurno);

										lcl_liquidacion = findActoLiquidacionItem(ll_liquidacion, ab_firma);

										if(!CollectionUtils.isValidCollection(lcl_liquidacion))
										{
											Collection<com.bachue.snr.prosnr01.model.sdb.acc.AnotacionPredio> lap_anotacionesPredio;
											lap_anotacionesPredio = DaoCreator.getAccAnotacionPredioDAO(adm_manager)
													                              .findAnotacionesInactivasByIdTurno(
													    ls_idTurno, false
													);

											if(CollectionUtils.isValidCollection(lap_anotacionesPredio))
											{
												for(com.bachue.snr.prosnr01.model.sdb.acc.AnotacionPredio lap_anotacion : lap_anotacionesPredio)
												{
													Liquidacion ll_liquidacionTMP;
													ll_liquidacionTMP = DaoCreator.getAccLiquidacionDAO(adm_manager)
															                          .findByIdSolicitudOne(
															    lth_detalle.getIdSolicitud()
															);

													if(ll_liquidacionTMP != null)
													{
														ll_liquidacionTMP.setIdCirculo(lap_anotacion.getIdCirculo());
														ll_liquidacionTMP.setIdTipoActo(
														    lap_anotacion.getIdNaturalezaJuridica()
														);
														ll_liquidacionTMP = DaoCreator.getAccLiquidacionItemDAO(
															    adm_manager
															).buscarLiquidacionItemTipoActo(ll_liquidacionTMP);

														if(!CollectionUtils.isValidCollection(lcl_liquidacion))
															lcl_liquidacion = new ArrayList<Liquidacion>();

														lcl_liquidacion.add(ll_liquidacionTMP);
													}
												}

												lb_anotacionesEliminadas = true;
											}
										}

										if(CollectionUtils.isValidCollection(lcl_liquidacion))
										{
											BigDecimal lbd_totalSaldoFavor;

											lbd_totalSaldoFavor = BigDecimal.ZERO;

											for(Liquidacion ll_liqTMP : lcl_liquidacion)
											{
												if(ll_liqTMP != null)
												{
													if(lb_anotacionesEliminadas)
														lbd_totalSaldoFavor = lbd_totalSaldoFavor.add(
															    NumericUtils.isValidBigDecimal(
															        ll_liqTMP.getValorSaldoFavor()
															    ) ? ll_liqTMP.getValorTotal() : BigDecimal.ZERO
															);
													else
														lbd_totalSaldoFavor = lbd_totalSaldoFavor.add(
															    NumericUtils.isValidBigDecimal(
															        ll_liqTMP.getValorSaldoFavor()
															    ) ? ll_liqTMP.getValorSaldoFavor() : BigDecimal.ZERO
															);
												}
											}

											{
												String ls_totalSaldoFavor;

												ls_totalSaldoFavor = conversionCientifica(lbd_totalSaldoFavor);

												if(StringUtils.isValidString(ls_totalSaldoFavor))
													ls_plantilla = ls_plantilla.replace(
														    ls_tag,
														    StringUtils.getString(
														        ls_totalSaldoFavor
														        + IdentificadoresCommon.ESPACIO_VACIO
														        + Convertidor.convertirLetras(
														            NumericUtils.getLong(lbd_totalSaldoFavor)
														        )
														    )
														);
												else
													ls_plantilla = ls_plantilla.replace(ls_tag, " ");
											}
										}
									}
								}
							}

							{
								ls_tag = "<TAG_BQN_DOC_ID_TIPO_DOC>";

								if(ls_plantilla.contains(ls_tag))
								{
									Solicitud ls_solicitud;
									ls_solicitud = new Solicitud();

									ls_solicitud.setIdSolicitud(lth_detalle.getIdSolicitud());

									ls_solicitud = DaoCreator.getSolicitudDAO(adm_manager).findById(ls_solicitud);

									if(ls_solicitud != null)
									{
										Documento ld_documento;
										ld_documento = new Documento();

										ld_documento.setIdDocumento(ls_solicitud.getIdDocumento());

										ld_documento = DaoCreator.getDocumentoDAO(adm_manager).findById(ld_documento);

										if(ld_documento != null)
										{
											TipoDocumentoPublico ltdp_tipoDocPublico;
											ltdp_tipoDocPublico = new TipoDocumentoPublico();

											ltdp_tipoDocPublico.setIdTipoDocumento(ld_documento.getIdTipoDocumento());

											ltdp_tipoDocPublico = DaoCreator.getTipoDocumentoPublicoDAO(adm_manager)
													                            .findById(ltdp_tipoDocPublico);

											if(ltdp_tipoDocPublico != null)
											{
												String ls_nombreTipoDoc;
												ls_nombreTipoDoc = ltdp_tipoDocPublico.getNombre();

												if(StringUtils.isValidString(ls_nombreTipoDoc))
													ls_plantilla = ls_plantilla.replace(
														    ls_tag, StringUtils.getString(ls_nombreTipoDoc)
														);
												else
													ls_plantilla = ls_plantilla.replace(ls_tag, " ");
											}

											String ls_tagNumero;
											ls_tagNumero = "<TAG_BQN_DOC_NUMERO>";

											if(ls_plantilla.contains(ls_tagNumero))
											{
												String ls_numeroDoc;
												ls_numeroDoc = ld_documento.getNumero();

												if(StringUtils.isValidString(ls_numeroDoc))
													ls_plantilla = ls_plantilla.replace(
														    ls_tagNumero, StringUtils.getString(ls_numeroDoc)
														);
												else
													ls_plantilla = ls_plantilla.replace(ls_tag, " ");
											}

											String ls_tagFecha;
											ls_tagFecha = "<TAG_BQN_DOC_FECHA>";

											if(ls_plantilla.contains(ls_tagFecha))
											{
												String ls_fecha;
												ls_fecha = StringUtils.getString(ld_documento.getFechaDocumento());

												if(StringUtils.isValidString(ls_fecha))
													ls_plantilla = ls_plantilla.replace(
														    ls_tagFecha, StringUtils.getString(ls_fecha)
														);
												else
													ls_plantilla = ls_plantilla.replace(ls_tag, " ");
											}
										}
									}
								}
							}

							{
								ls_tag = "<TAG_ACC_TUR_ID_TURNO>";

								if(ls_plantilla.contains(ls_tag))
								{
									String ls_idTurno;
									ls_idTurno = lth_detalle.getIdTurno();

									if(StringUtils.isValidString(ls_idTurno))
										ls_plantilla = ls_plantilla.replace(ls_tag, StringUtils.getString(ls_idTurno));
									else
										ls_plantilla = ls_plantilla.replace(ls_tag, " ");

									String ls_tagFechaTurno;
									ls_tagFechaTurno = "<TAG_FECHA_TURNO>";

									if(ls_plantilla.contains(ls_tagFechaTurno))
									{
										Turno lt_turnoTemp;
										lt_turnoTemp = new Turno();

										lt_turnoTemp.setIdTurno(ls_idTurno);

										lt_turnoTemp = DaoCreator.getTurnoDAO(adm_manager).findById(lt_turnoTemp);

										if(lt_turnoTemp != null)
										{
											String ls_fechaTurno;
											ls_fechaTurno = DateUtils.convertirLetrasLarga(
												    lt_turnoTemp.getFechaCreacion()
												);

											if(StringUtils.isValidString(ls_fechaTurno))
												ls_plantilla = ls_plantilla.replace(
													    ls_tagFechaTurno, StringUtils.getString(ls_fechaTurno)
													);
											else
												ls_plantilla = ls_plantilla.replace(ls_tagFechaTurno, " ");
										}
									}
								}
							}

							{
								ls_tag = "<TAG_MATRICULAS>";

								if(ls_plantilla.contains(ls_tag))
								{
									Turno lt_turno;
									lt_turno = new Turno();

									lt_turno.setIdTurno(lth_detalle.getIdTurno());

									lt_turno = DaoCreator.getTurnoDAO(adm_manager).findById(lt_turno);

									if(lt_turno != null)
									{
										SolicitudMatriculaActo             lsma_solMatActo;
										Collection<SolicitudMatriculaActo> lcsma_consulta;

										lsma_solMatActo = new SolicitudMatriculaActo();

										lsma_solMatActo.setIdSolicitud(lth_detalle.getIdSolicitud());
										lsma_solMatActo.setIdCirculo(lt_turno.getIdCirculo());

										lcsma_consulta = DaoCreator.getSolicitudMatriculaActoDAO(adm_manager)
												                       .findByIdSolicitudCirculo(lsma_solMatActo, true);

										if(CollectionUtils.isValidCollection(lcsma_consulta))
										{
											StringBuilder lsb_temp;
											lsb_temp = new StringBuilder();

											int li_cont;
											li_cont = 0;

											for(SolicitudMatriculaActo ls_mat : lcsma_consulta)
											{
												if(ls_mat != null)
												{
													if(li_cont == 0)
														lsb_temp.append(
														    ls_mat.getIdCirculo() + "-" + ls_mat.getIdMatricula()
														);
													else
													{
														lsb_temp.append(", ");
														lsb_temp.append(
														    ls_mat.getIdCirculo() + "-" + ls_mat.getIdMatricula()
														);
													}
												}
											}

											String ls_matriculasTMP;
											ls_matriculasTMP = lsb_temp.toString();

											if(StringUtils.isValidString(ls_matriculasTMP))
												ls_plantilla = ls_plantilla.replace(
													    ls_tag, StringUtils.getString(ls_matriculasTMP)
													);
											else
												ls_plantilla = ls_plantilla.replace(ls_tag, " ");
										}
									}
								}
							}

							{
								ls_tag = "<TAG_TURNO_DERIVADO>";

								if(ls_plantilla.contains(ls_tag))
								{
									String ls_turnoDerivado;
									ls_turnoDerivado = lthd_thd.findTurnoDerivado(lth_detalle.getIdTurno());

									if(StringUtils.isValidString(ls_turnoDerivado))
										ls_plantilla = ls_plantilla.replace(
											    ls_tag, StringUtils.getString(ls_turnoDerivado)
											);
									else
										ls_plantilla = ls_plantilla.replace(ls_tag, " ");
								}
							}

							ls_tag = "<TAG_OBSERVACIONES_TURNO_HISTORIA>";

							if(ls_plantilla.contains(ls_tag))
							{
								String ls_observaciones;
								ls_observaciones = StringUtils.isValidString(lth_detalle.getObservaciones())
									? lth_detalle.getObservaciones() : ath_parametros.getObservaciones();

								if(StringUtils.isValidString(ls_observaciones))
									ls_plantilla = ls_plantilla.replace(
										    ls_tag, StringUtils.getString(ls_observaciones)
										);
								else
									ls_plantilla = ls_plantilla.replace(ls_tag, " ");
							}

							ls_tag = "<TAG_CONCEPTO_PAGO_EXCESO>";

							if(ls_plantilla.contains(ls_tag))
							{
								RegistroPagoExceso lrpe_pagoExceso;

								lrpe_pagoExceso = DaoCreator.getRegistroPagoExcesoDAO(adm_manager)
										                        .findByIdTurno(lth_detalle.getIdTurno());

								if(lrpe_pagoExceso != null)
								{
									String ls_concepto;

									ls_concepto = lrpe_pagoExceso.getConceptoPagoExceso();

									if(StringUtils.isValidString(ls_concepto))
										ls_plantilla = ls_plantilla.replace(ls_tag, ls_concepto);
								}
							}

							{
								ls_tag = "<TAG_USUARIO>";

								if(ls_plantilla.contains(ls_tag))
								{
									String ls_idUsuario;
									ls_idUsuario = lth_detalle.getIdUsuario();

									if(StringUtils.isValidString(ls_idUsuario))
										ls_plantilla = ls_plantilla.replace(
											    ls_tag, StringUtils.getString(ls_idUsuario)
											);
									else
										ls_plantilla = ls_plantilla.replace(ls_tag, " ");
								}
							}

							{
								ls_tag = "<TAG_HORA>";

								if(ls_plantilla.contains(ls_tag))
								{
									Date       ld_fechaActual;
									String     ls_hora;
									DateFormat lsf_formatTime;
									ld_fechaActual     = new Date();
									lsf_formatTime     = new SimpleDateFormat("HH:mm:ss");
									ls_hora            = lsf_formatTime.format(ld_fechaActual);

									if(StringUtils.isValidString(ls_hora))
										ls_plantilla = ls_plantilla.replace(ls_tag, StringUtils.getString(ls_hora));
									else
										ls_plantilla = ls_plantilla.replace(ls_tag, " ");
								}
							}

							String     ls_tagUsuarioFirma;
							Constantes lc_usuarioFirma;

							ls_tagUsuarioFirma     = null;
							lc_usuarioFirma        = null;

							{
								ls_tagUsuarioFirma = "<TAG_USUARIO_FIRMA_SUSPENSION>";

								if(ls_plantilla.contains(ls_tagUsuarioFirma))
								{
									lc_usuarioFirma = new Constantes();

									lc_usuarioFirma.setIdConstante(ConstanteCommon.USUARIO_FIRMA_SUSPENSION);

									lc_usuarioFirma     = DaoCreator.getConstantesDAO(adm_manager)
											                            .findByIdWithImage(lc_usuarioFirma);

									ls_plantilla = getFirmaMasivaBusiness()
											               .reemplazarUsuarioFirmaCargo(
											    ls_plantilla, lc_usuarioFirma, ls_tagUsuarioFirma,
											    "<TAG_CARGO_FIRMA_SUSPENSION>"
											);
								}
							}

							if(ab_firma)
							{
								String ls_consecutivoOficio;
								String ls_referenciaSalida;
								Date   ld_fechaOficio;

								ls_consecutivoOficio     = null;
								ls_referenciaSalida      = null;
								ld_fechaOficio           = null;

								{
									ls_tag = "<TAG_OFICIO>";

									if(ls_plantilla.contains(ls_tag))
									{
										NumeracionOficiosCirculo lnoc_numeracionOficios;
										lnoc_numeracionOficios = findNumeracionOficiosCirculo(
											    ath_parametros, adm_manager, as_userId, as_remoteIp, true
											);

										if(lnoc_numeracionOficios != null)
										{
											ls_consecutivoOficio = lnoc_numeracionOficios.getConsecutivoGenerado();

											if(StringUtils.isValidString(ls_consecutivoOficio))
											{
												ls_plantilla     = ls_plantilla.replace(ls_tag, ls_consecutivoOficio);

												ld_fechaOficio = new Date();
												ads_documentoSalida.setConsecutivoOficio(ls_consecutivoOficio);

												ads_documentoSalida.setFechaOficio(ld_fechaOficio);
											}
										}
									}
								}

								{
									ls_tag = "<TAG_REFERENCIA_SALIDA>";

									if(ls_plantilla.contains(ls_tag))
									{
										ls_referenciaSalida     = generarIdCorrespondencia(
											    ath_parametros, adm_manager, false
											);

										ls_plantilla = ls_plantilla.replace(ls_tag, ls_referenciaSalida);

										ads_documentoSalida.setReferenciaSalida(ls_referenciaSalida);
									}
								}
							}

							lmss_datos       = finalizarPlantilla(
								    ls_plantilla, lth_detalle.getIdTurnoHistoria(), adm_manager
								);
							ls_plantilla     = lmss_datos.get(IdentificadoresCommon.PLANTILLA);

							if(StringUtils.isValidString(ls_plantilla))
							{
								lba_notaInformativa = new PDFGenerator().convertirRTFToPDF(
									    ls_plantilla.getBytes(), adm_manager
									);

								if(lba_notaInformativa == null)
									throw new B2BException(
									    ErrorKeys.ERROR_GENERANDO_PDF_NOTA_INFORMATIVA_POR_PAGO_EN_EXCESO
									);
							}

							if(ab_firma)
							{
								byte[] lba_grafo;

								lba_grafo = null;

								int li_incrX = 0;
								int li_incrY = 0;

								if(lc_usuarioFirma != null)
								{
									lba_grafo     = lc_usuarioFirma.getImagenes();
									li_incrX      = NumericUtils.getInt(lc_usuarioFirma.getEntero());
									li_incrY      = NumericUtils.getInt(lc_usuarioFirma.getReal());
								}

								lba_notaInformativa = getFirmaMasivaBusiness()
										                      .reemplazarBookmarsFirma(
										    lba_notaInformativa, lba_grafo, li_incrX, li_incrY
										);
							}
						}
					}
				}
			}
		}

		return lba_notaInformativa;
	}

	/**
	 * Metodo encargado de generar el pdf de cuenta de cobro para mayor valor y de guardarlo en documentos salida.
	 *
	 * @param adm_manager Argumento de tipo DaoManager que permite realiza la conexión a la base de datos.
	 * @param ath_turnoHistoria Argumento de tipo turno historia que contiene los datos necesarios para resolver el pdf.
	 * @param aba_archivo Argumento de tipo byte[] que contiene el archivo a guardar en documentos salida.
	 * @param as_tipoDocumental correspondiente al valor del tipo de objeto String
	 * @param as_userId Argumento de tipo String que contiene el usuario que realiza la generación del documento.
	 * @param as_remoteIp Argumento de tipo String que contiene la ip de la maquina desde la cual se realiza la
	 * generación del documento.
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public synchronized void insertarImagenDocumentosSalida(
	    DAOManager adm_manager, TurnoHistoria ath_turnoHistoria, byte[] aba_archivo, String as_tipoDocumental,
	    String as_userId, String as_remoteIp, DocumentosSalida ads_documentoSalida
	)
	    throws B2BException
	{
		try
		{
			if(aba_archivo != null)
			{
				String ls_tipo;
				String ls_tipoDocumental;
				String ls_estado;

				ls_tipo               = null;
				ls_tipoDocumental     = null;
				ls_estado             = EstadoCommon.A;

				{
					Constantes lc_constante;
					lc_constante = new Constantes();

					lc_constante.setIdConstante(as_tipoDocumental);

					lc_constante = DaoCreator.getConstantesDAO(adm_manager).findById(lc_constante);

					if(lc_constante != null)
					{
						TipoDocumental ltd_tipoDocumental;

						ltd_tipoDocumental = new TipoDocumental();

						ltd_tipoDocumental.setIdTipoDocumental(lc_constante.getCaracter());

						ltd_tipoDocumental = DaoCreator.getTipoDocumentalDAO(adm_manager).findById(ltd_tipoDocumental);

						if(ltd_tipoDocumental != null)
						{
							ls_tipoDocumental     = ltd_tipoDocumental.getIdTipoDocumental();
							ls_tipo               = ltd_tipoDocumental.getNombre();
						}
					}
					else
					{
						Object[] loa_messageArgs;

						loa_messageArgs        = new String[1];
						loa_messageArgs[0]     = as_tipoDocumental;

						throw new B2BException(ErrorKeys.ERROR_SIN_CONSTANTE, loa_messageArgs);
					}
				}

				if(ath_turnoHistoria != null)
				{
					DocumentosSalida    lds_documentosSalida;
					DocumentosSalidaDAO ldsd_DAO;
					String              ls_destinatario;
					TurnoHistoria       lth_turnoHistoria;
					TurnoHistoriaDAO    lth_turnoHistoriaDAO;
					String              ls_direccion;
					String              ls_idDepartamento;
					String              ls_idPais;
					String              ls_idMunicipio;
					String              ls_correoElectronico;
					String              ls_idTelefono;

					lds_documentosSalida     = new DocumentosSalida();
					ldsd_DAO                 = DaoCreator.getDocumentosSalidaDAO(adm_manager);
					lth_turnoHistoriaDAO     = DaoCreator.getTurnoHistoriaDAO(adm_manager);
					lth_turnoHistoria        = lth_turnoHistoriaDAO.findById(ath_turnoHistoria.getIdTurnoHistoria());
					ls_destinatario          = null;
					ls_direccion             = null;
					ls_idMunicipio           = null;
					ls_idDepartamento        = null;
					ls_idPais                = null;
					ls_correoElectronico     = null;
					ls_idTelefono            = null;

					if(lth_turnoHistoria != null)
					{
						if(ath_turnoHistoria.isNotaExcesoCalificacion())
						{
							SolicitudInterviniente ls_solicitudInterviniente;
							ls_solicitudInterviniente = new SolicitudInterviniente();

							ls_solicitudInterviniente.setIdSolicitud(lth_turnoHistoria.getIdSolicitud());

							ls_solicitudInterviniente = DaoCreator.getSolicitudIntervinienteDAO(adm_manager)
									                                  .findByIdSolicitudUltimo(
									    ls_solicitudInterviniente
									);

							if(ls_solicitudInterviniente != null)
							{
								Persona lp_persona;
								lp_persona = DaoCreator.getPersonaDAO(adm_manager)
										                   .findById(ls_solicitudInterviniente.getIdPersona());

								if(lp_persona != null)
								{
									String ls_tipoDocumento;

									ls_tipoDocumento = lp_persona.getIdDocumentoTipo();

									if(
									    StringUtils.isValidString(ls_tipoDocumento)
										    && ls_tipoDocumento.equalsIgnoreCase(IdentificadoresCommon.NIT)
									)
										ls_destinatario = lp_persona.getRazonSocial();
									else
										ls_destinatario = lp_persona.getNombreCompleto();

									String           ls_idPersona = lp_persona.getIdPersona();
									PersonaDireccion lpd_pd       = new PersonaDireccion();
									lpd_pd.setIdPersona(ls_idPersona);

									PersonaDireccionDAO lpdDAO = DaoCreator.getPersonaDireccionDAO(adm_manager);
									int                 li_i   = lpdDAO.findIdDireccion(lpd_pd);

									if(li_i > 0)
									{
										lpd_pd = lpdDAO.findById(ls_idPersona, String.valueOf(li_i));

										if(lpd_pd != null)
										{
											ls_direccion          = lpd_pd.getDireccion();
											ls_idDepartamento     = lpd_pd.getIdDepartamento();
											ls_idPais             = lpd_pd.getIdPais();
											ls_idMunicipio        = lpd_pd.getIdMunicipio();
										}
									}

									{
										PersonaCorreoElectronico lpce_pce = new PersonaCorreoElectronico();
										lpce_pce.setIdPersona(ls_idPersona);
										lpce_pce.setIdCorreoElectronico(
										    ls_solicitudInterviniente.getIdCorreoElectronico()
										);
										lpce_pce = DaoCreator.getPersonaCorreoElectronicoDAO(adm_manager)
												                 .findById(lpce_pce);

										if(lpce_pce != null)
											ls_correoElectronico = lpce_pce.getCorreoElectronico();
									}

									{
										PersonaTelefono lpt_pt = new PersonaTelefono();
										lpt_pt.setIdPersona(ls_idPersona);
										lpt_pt.setIdTelefono(ls_solicitudInterviniente.getIdTelefono());

										lpt_pt = DaoCreator.getPersonaTelefonoDAO(adm_manager).findById(lpt_pt);

										if(lpt_pt != null)
											ls_idTelefono = lpt_pt.getTelefono();
									}
								}
							}
						}
						else
						{
							Solicitud ls_solicitud;

							ls_solicitud = DaoCreator.getSolicitudDAO(adm_manager)
									                     .findById(lth_turnoHistoria.getIdSolicitud());

							if(ls_solicitud != null)
							{
								Persona lp_persona;

								lp_persona = DaoCreator.getPersonaDAO(adm_manager).findById(
									    ls_solicitud.getIdPersona()
									);

								if(lp_persona != null)
								{
									String ls_tipoDocumento;
									String ls_idPersona;

									ls_tipoDocumento     = lp_persona.getIdDocumentoTipo();
									ls_idPersona         = lp_persona.getIdPersona();

									if(
									    StringUtils.isValidString(ls_tipoDocumento)
										    && ls_tipoDocumento.equalsIgnoreCase(IdentificadoresCommon.NIT)
									)
										ls_destinatario = lp_persona.getRazonSocial();
									else
										ls_destinatario = lp_persona.getNombreCompleto();

									{
										String ls_medioNotificar;

										ls_medioNotificar = ls_solicitud.getIdAutorizacionNotificacion();

										if(StringUtils.isValidString(ls_medioNotificar))
										{
											if(
											    ls_medioNotificar.equalsIgnoreCase(
												        MedioNotificarCommon.CORREO_ELECTRONICO
												    )
											)
											{
												String ls_idCorreo;

												ls_idCorreo = ls_solicitud.getIdCorreoElectronico();

												if(StringUtils.isValidString(ls_idCorreo))
												{
													PersonaCorreoElectronico lpc_correo;

													lpc_correo = DaoCreator.getPersonaCorreoElectronicoDAO(adm_manager)
															                   .findById(ls_idPersona, ls_idCorreo);

													if(lpc_correo != null)
														ls_correoElectronico = lpc_correo.getCorreoElectronico();
												}
											}
											else if(
											    ls_medioNotificar.equalsIgnoreCase(
												        MedioNotificarCommon.DIRECCION_CORRESPONDENCIA
												    )
												    || ls_medioNotificar.equalsIgnoreCase(
												        MedioNotificarCommon.DIRECCION_RESIDENCIA
												    )
											)
											{
												String ls_idDireccion;

												ls_idDireccion = ls_solicitud.getIdDireccion();

												if(StringUtils.isValidString(ls_idDireccion))
												{
													PersonaDireccion ld_direccion;

													ld_direccion = DaoCreator.getPersonaDireccionDAO(adm_manager)
															                     .findById(
															    ls_idPersona, ls_idDireccion
															);

													if(ld_direccion != null)
													{
														ls_direccion          = ld_direccion.getDireccion();
														ls_idPais             = ld_direccion.getIdPais();
														ls_idDepartamento     = ld_direccion.getIdDepartamento();
														ls_idMunicipio        = ld_direccion.getIdMunicipio();
													}
												}
											}
											else if(ls_medioNotificar.equalsIgnoreCase(MedioNotificarCommon.ORIP))
											{
												String ls_idTelefonoNot;

												ls_idTelefonoNot = ls_solicitud.getIdTelefono();

												if(StringUtils.isValidString(ls_idTelefonoNot))
												{
													PersonaTelefono lpt_telefono;

													lpt_telefono = DaoCreator.getPersonaTelefonoDAO(adm_manager)
															                     .findById(
															    ls_idPersona, ls_idTelefonoNot
															);

													if(lpt_telefono != null)
														ls_idTelefono = lpt_telefono.getTelefono();
												}
											}
										}
									}
								}
							}
						}
					}

					lds_documentosSalida.setIdSolicitud(lth_turnoHistoria.getIdSolicitud());
					lds_documentosSalida.setIdTurnoHistoria(
					    NumericUtils.getInteger(lth_turnoHistoria.getIdTurnoHistoria())
					);

					lds_documentosSalida.setTipo(ls_tipo);
					lds_documentosSalida.setEstado(ls_estado);

					lds_documentosSalida = ldsd_DAO.findDocumentByTurnoHistoriaTipoEstado(lds_documentosSalida);

					if(lds_documentosSalida != null)
					{
						Long ll_idImagen;

						ll_idImagen = lds_documentosSalida.getIdImagen();

						if(ll_idImagen != null)
						{
							Imagenes    li_imagenes;
							ImagenesDAO lid_DAO;

							li_imagenes     = new Imagenes();
							lid_DAO         = DaoCreator.getImagenesDAO(adm_manager);

							li_imagenes.setIdImagen(NumericUtils.getInt(ll_idImagen));

							li_imagenes = lid_DAO.findById(li_imagenes);

							if(li_imagenes != null)
							{
								li_imagenes.setImagenBlob(aba_archivo);
								li_imagenes.setIdUsuarioModificacion(as_userId);
								li_imagenes.setIpModificacion(as_remoteIp);

								lid_DAO.insertOrUpdate(li_imagenes, false);
							}
						}
						else
							throw new B2BException(ErrorKeys.ERROR_SIN_ID_IMAGEN);

						if(StringUtils.isValidString(ls_destinatario))
							lds_documentosSalida.setDestinatario(ls_destinatario);

						if(
						    StringUtils.isValidString(ads_documentoSalida.getConsecutivoOficio())
							    && StringUtils.isValidString(ads_documentoSalida.getReferenciaSalida())
						)
						{
							lds_documentosSalida.setReferenciaSalida(ads_documentoSalida.getReferenciaSalida());
							lds_documentosSalida.setConsecutivoOficio(ads_documentoSalida.getConsecutivoOficio());
							lds_documentosSalida.setFechaOficio(ads_documentoSalida.getFechaOficio());
							lds_documentosSalida.setIdTurno(lth_turnoHistoria.getIdTurno());
							lds_documentosSalida.setIdSolicitud(lth_turnoHistoria.getIdSolicitud());
							lds_documentosSalida.setRepositorio(RepositorioCommon.FINAL);
						}

						if(
						    StringUtils.isValidString(ls_destinatario)
							    || (StringUtils.isValidString(ads_documentoSalida.getConsecutivoOficio())
							    && StringUtils.isValidString(ads_documentoSalida.getReferenciaSalida()))
						)
							ldsd_DAO.insertOrUpdate(lds_documentosSalida, false);
					}
					else
					{
						Imagenes li_imagenes;
						String   ls_tipoArchivo;
						long     ll_secuenciaImagenes;

						li_imagenes        = new Imagenes();
						ls_tipoArchivo     = ExtensionCommon.PDF_MAYUS;

						li_imagenes.setImagenBlob(aba_archivo);
						li_imagenes.setTipoArchivo(ls_tipoArchivo);
						li_imagenes.setIdUsuarioCreacion(as_userId);
						li_imagenes.setIpCreacion(as_remoteIp);

						ll_secuenciaImagenes = DaoCreator.getImagenesDAO(adm_manager).insertOrUpdate(li_imagenes, true);

						if(ll_secuenciaImagenes > 0)
						{
							ads_documentoSalida.setIdTurnoHistoria(
							    NumericUtils.getInteger(lth_turnoHistoria.getIdTurnoHistoria())
							);
							ads_documentoSalida.setIdSolicitud(lth_turnoHistoria.getIdSolicitud());
							ads_documentoSalida.setIdTurno(lth_turnoHistoria.getIdTurno());
							ads_documentoSalida.setIdImagen(NumericUtils.getLongWrapper(ll_secuenciaImagenes));
							ads_documentoSalida.setIdTipoDocumental(ls_tipoDocumental);
							ads_documentoSalida.setTipo(ls_tipo);
							ads_documentoSalida.setEstado(ls_estado);
							ads_documentoSalida.setDestinatario(ls_destinatario);
							ads_documentoSalida.setDireccion(ls_direccion);
							ads_documentoSalida.setIdDepartamento(ls_idDepartamento);
							ads_documentoSalida.setIdPais(ls_idPais);
							ads_documentoSalida.setIdMunicipio(ls_idMunicipio);
							ads_documentoSalida.setCorreoElectronico(ls_correoElectronico);
							ads_documentoSalida.setTelefono(ls_idTelefono);
							ads_documentoSalida.setIdUsuarioCreacion(as_userId);
							ads_documentoSalida.setIpCreacion(as_remoteIp);

							if(StringUtils.isValidString(ads_documentoSalida.getReferenciaSalida()))
								ads_documentoSalida.setRepositorio(RepositorioCommon.FINAL);

							ldsd_DAO.insertOrUpdate(ads_documentoSalida, true);
						}
					}
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			throw lb2be_e;
		}
	}

	/**
	 * Metodo encargado de ejecutar el procedimiento de liquidación.
	 *
	 * @param al_liquidacion Objeto de tipo Liquidacion que contiene los argumentos necesarios para ejecutar el procedimiento de liquidación.
	 * @param as_userId Argumento de tipo String que contiene el usuario que realizar la ejecución del metodo.
	 * @param as_remoteIp Argumento de tipo String que contiene la ip desde donde se realiza la ejecución del metodo.
	 * @return Objeto de tipo liquidación que contiene la respuesta del procedimiento de liquidación.
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see Liquidacion
	 */
	public synchronized Liquidacion liquidar(Liquidacion al_liquidacion, String as_userId, String as_remoteIp)
	    throws B2BException
	{
		DAOManager ldm_manager;

		ldm_manager = DaoManagerFactory.getDAOManager();

		try
		{
			if(al_liquidacion != null)
			{
				String ls_idSolicitud;
				String ls_idTurno;

				ls_idSolicitud     = al_liquidacion.getIdSolicitud();
				ls_idTurno         = al_liquidacion.getIdTurno();

				{
					Collection<SolicitudMatricula>     lcsma_solicitudMatricula;
					Collection<SolicitudMatriculaActo> lcsma_solicitudMatriculaActo;

					lcsma_solicitudMatricula         = al_liquidacion.getSolicitudMatricula();
					lcsma_solicitudMatriculaActo     = al_liquidacion.getSolicitudMatriculaActo();

					if(CollectionUtils.isValidCollection(lcsma_solicitudMatriculaActo))
						salvarAsociarMatricula(
						    lcsma_solicitudMatricula, lcsma_solicitudMatriculaActo, ldm_manager, as_userId, as_remoteIp
						);
				}

				{
					Collection<com.bachue.snr.prosnr01.model.registro.Acto> lca_actosRegistroMayorValor;
					Collection<com.bachue.snr.prosnr01.model.registro.Acto> lca_actosModificados;
					boolean                                                 lb_actosRegistrados;

					lca_actosRegistroMayorValor     = DaoCreator.getActoDAO(ldm_manager)
							                                        .findActosByIdTurnoIndMayorValor(
							    ls_idTurno, EstadoCommon.I
							);
					lca_actosModificados            = al_liquidacion.getActosRegistroMayorValor();
					lb_actosRegistrados             = CollectionUtils.isValidCollection(lca_actosRegistroMayorValor);

					if(!lb_actosRegistrados)
						lca_actosRegistroMayorValor = new ArrayList<com.bachue.snr.prosnr01.model.registro.Acto>();

					if(CollectionUtils.isValidCollection(lca_actosModificados))
					{
						ActoDAO                                                 lad_DAO;
						Collection<com.bachue.snr.prosnr01.model.registro.Acto> lca_actosHijosModificados;

						lca_actosHijosModificados     = null;
						lad_DAO                       = DaoCreator.getActoDAO(ldm_manager);

						for(com.bachue.snr.prosnr01.model.registro.Acto la_iterador : lca_actosModificados)
						{
							if(la_iterador != null)
							{
								Acto la_acto;

								la_acto = new Acto();

								la_acto.setIndMayorValor(EstadoCommon.M);
								la_acto.setIdUsuarioModificacion(as_userId);
								la_acto.setIpModificacion(as_remoteIp);
								la_acto.setIdActo(la_iterador.getIdActoDb());

								lad_DAO.updateIndMayorValor(la_acto);

								Collection<com.bachue.snr.prosnr01.model.registro.Acto> lca_actosHijos;
								lca_actosHijos = lad_DAO.findHijosById(la_iterador);

								if(CollectionUtils.isValidCollection(lca_actosHijos))
								{
									for(com.bachue.snr.prosnr01.model.registro.Acto la_iteradorTMP : lca_actosHijos)
									{
										if(la_iteradorTMP != null)
										{
											Acto la_actoHijo;

											la_actoHijo = new Acto();

											la_actoHijo.setIndMayorValor(EstadoCommon.M);
											la_actoHijo.setIdUsuarioModificacion(as_userId);
											la_actoHijo.setIpModificacion(as_remoteIp);
											la_actoHijo.setIdActo(la_iteradorTMP.getIdActoDb());

											lad_DAO.updateIndMayorValor(la_actoHijo);
										}
									}

									if(!CollectionUtils.isValidCollection(lca_actosHijosModificados))
										lca_actosHijosModificados = new ArrayList<com.bachue.snr.prosnr01.model.registro.Acto>();

									lca_actosHijosModificados.addAll(lca_actosHijos);
								}
							}
						}

						lca_actosRegistroMayorValor.addAll(lca_actosModificados);

						if(CollectionUtils.isValidCollection(lca_actosHijosModificados))
							lca_actosRegistroMayorValor.addAll(lca_actosHijosModificados);
					}

					lb_actosRegistrados = !lca_actosRegistroMayorValor.isEmpty();

					if(lb_actosRegistrados)
					{
						RegistroMayorValor lrmv_tmp;

						lrmv_tmp = al_liquidacion.getRegistroMayorValor();

						if(lrmv_tmp != null)
						{
							RegistroMayorValorDAO lrmvd_DAO;
							String                ls_concepto;

							ls_concepto = lrmv_tmp.getConceptoCobroMayorValor();

							if(!StringUtils.isValidString(ls_concepto))
								throw new B2BException(ErrorKeys.ERROR_SIN_CONCEPTO);

							lrmvd_DAO = DaoCreator.getRegistroMayorValorDAO(ldm_manager);

							{
								Collection<RegistroMayorValor> lcrmv_registroMayorValor;

								lcrmv_registroMayorValor = lrmvd_DAO.findByIdTurnoAndSolicitud(
									    ls_idSolicitud, ls_idTurno
									);

								if(CollectionUtils.isValidCollection(lcrmv_registroMayorValor))
								{
									LiquidacionDAO lld_DAO;

									lld_DAO = DaoCreator.getAccLiquidacionDAO(ldm_manager);

									for(RegistroMayorValor lrmv_iterador : lcrmv_registroMayorValor)
									{
										if(lrmv_iterador != null)
										{
											String ls_idLiquidacion;
											int    li_consecutivo;

											ls_idLiquidacion     = lrmv_iterador.getIdLiquidacion();
											li_consecutivo       = NumericUtils.getInt(lrmv_iterador.getConsecutivo());

											if(StringUtils.isValidString(ls_idLiquidacion) && (li_consecutivo > 0))
											{
												Liquidacion ll_liquidacion;

												ll_liquidacion = lld_DAO.findById(ls_idLiquidacion, li_consecutivo);

												if(ll_liquidacion != null)
												{
													String ls_pagada;

													ls_pagada = ll_liquidacion.getPagada();

													if(
													    !StringUtils.isValidString(ls_pagada)
														    || !ls_pagada.equalsIgnoreCase(EstadoCommon.S)
													)
														lrmvd_DAO.deleteById(lrmv_iterador.getIdRegistroMayorValor());
												}
											}
										}
									}
								}
							}

							for(com.bachue.snr.prosnr01.model.registro.Acto la_iterador : lca_actosRegistroMayorValor)
							{
								if(la_iterador != null)
								{
									RegistroMayorValor lrmv_registroMayorValor;
									RegistroMayorValor lrmv_registroMayorValorU;

									lrmv_registroMayorValor = new RegistroMayorValor();

									lrmv_registroMayorValor.setIdSolicitud(ls_idSolicitud);
									lrmv_registroMayorValor.setIdActo(la_iterador.getIdActoDb());
									lrmv_registroMayorValor.setIdTurno(ls_idTurno);

									lrmv_registroMayorValorU = lrmvd_DAO.findByTurnoActoSolicitud(
										    lrmv_registroMayorValor
										);

									if(lrmv_registroMayorValorU != null)
									{
										lrmv_registroMayorValorU.setConceptoCobroMayorValor(ls_concepto);
										lrmv_registroMayorValorU.setCantidadActos(
										    NumericUtils.getLongWrapper(la_iterador.getCantidadActos())
										);
										lrmv_registroMayorValorU.setCuantia(la_iterador.getCuantiaActo());
										lrmv_registroMayorValorU.setValorAvaluo(la_iterador.getValorAvaluo());
										lrmv_registroMayorValorU.setIdTipoAdquisicion(la_iterador.getTipoAdquisicion());
										lrmv_registroMayorValorU.setAreaActo(la_iterador.getAreaActo());
										lrmv_registroMayorValorU.setAreaTotal(la_iterador.getAreaTotal());
										lrmv_registroMayorValorU.setGarantiaHipotecaria(
										    la_iterador.getGarantiaHipotecaria()
										);
										lrmv_registroMayorValorU.setHijuelaPasivo(la_iterador.getHijuelaPasivo());
										lrmv_registroMayorValorU.setIdTipoTarifaRegistral(
										    la_iterador.getIdTipoTarifaRegistral()
										);
										lrmv_registroMayorValorU.setVersionTarifaRegistral(la_iterador.getVersion());
										lrmv_registroMayorValorU.setOrganismoInternacional(
										    la_iterador.getOrganismoInternacional()
										);
										lrmv_registroMayorValorU.setCantidadCertifAntSistema(
										    la_iterador.getCantidadCertifAntSistema()
										);
										lrmv_registroMayorValorU.setMadreCabeza(la_iterador.getMadreCabeza());
										lrmv_registroMayorValorU.setPorcentajeTransferencia(
										    la_iterador.getPorcentajeTransferencia()
										);
										lrmv_registroMayorValorU.setAreaTransferir(la_iterador.getAreaTransferir());
										lrmv_registroMayorValorU.setAreaTotalInmueble(
										    la_iterador.getAreaTotalInmueble()
										);
										lrmv_registroMayorValorU.setRespuestaLey1943(la_iterador.getRespuestaLey1943());
										lrmv_registroMayorValorU.setPeriodicidadCuantia(
										    la_iterador.getPeriodicidadCuantia()
										);
										lrmv_registroMayorValorU.setTiempoCanon(la_iterador.getTiempoCanon());
										lrmv_registroMayorValorU.setIdUsuarioModificacion(as_userId);
										lrmv_registroMayorValorU.setIpModificacion(as_remoteIp);

										lrmvd_DAO.updateDatosActoMayorValor(lrmv_registroMayorValorU);
									}
									else
									{
										lrmv_registroMayorValor.setConceptoCobroMayorValor(ls_concepto);
										lrmv_registroMayorValor.setIdTipoMayorValor(lrmv_tmp.getIdTipoMayorValor());
										lrmv_registroMayorValor.setEstadoMayorValor(lrmv_tmp.getEstadoMayorValor());
										lrmv_registroMayorValor.setCantidadActos(
										    NumericUtils.getLongWrapper(la_iterador.getCantidadActos())
										);
										lrmv_registroMayorValor.setCuantia(la_iterador.getCuantiaActo());
										lrmv_registroMayorValor.setValorAvaluo(la_iterador.getValorAvaluo());
										lrmv_registroMayorValor.setIdTipoAdquisicion(la_iterador.getTipoAdquisicion());
										lrmv_registroMayorValor.setAreaActo(la_iterador.getAreaActo());
										lrmv_registroMayorValor.setAreaTotal(la_iterador.getAreaTotal());
										lrmv_registroMayorValor.setGarantiaHipotecaria(
										    la_iterador.getGarantiaHipotecaria()
										);
										lrmv_registroMayorValor.setHijuelaPasivo(la_iterador.getHijuelaPasivo());
										lrmv_registroMayorValor.setIdTipoTarifaRegistral(
										    la_iterador.getIdTipoTarifaRegistral()
										);
										lrmv_registroMayorValor.setVersionTarifaRegistral(la_iterador.getVersion());
										lrmv_registroMayorValor.setOrganismoInternacional(
										    la_iterador.getOrganismoInternacional()
										);
										lrmv_registroMayorValor.setCantidadCertifAntSistema(
										    la_iterador.getCantidadCertifAntSistema()
										);
										lrmv_registroMayorValor.setMadreCabeza(la_iterador.getMadreCabeza());
										lrmv_registroMayorValor.setPorcentajeTransferencia(
										    la_iterador.getPorcentajeTransferencia()
										);
										lrmv_registroMayorValor.setAreaTransferir(la_iterador.getAreaTransferir());
										lrmv_registroMayorValor.setAreaTotalInmueble(
										    la_iterador.getAreaTotalInmueble()
										);
										lrmv_registroMayorValor.setRespuestaLey1943(la_iterador.getRespuestaLey1943());
										lrmv_registroMayorValor.setPeriodicidadCuantia(
										    la_iterador.getPeriodicidadCuantia()
										);
										lrmv_registroMayorValor.setTiempoCanon(la_iterador.getTiempoCanon());
										lrmv_registroMayorValor.setIdUsuarioCreacion(as_userId);
										lrmv_registroMayorValor.setIpCreacion(as_remoteIp);

										lrmvd_DAO.insert(lrmv_registroMayorValor);
									}
								}
							}
						}
					}
				}

				{
					Collection<Liquidacion> lcl_condiciones;

					lcl_condiciones = al_liquidacion.getCondiciones();

					if(CollectionUtils.isValidCollection(lcl_condiciones))
						actualizaCondiciones(lcl_condiciones, as_userId, as_remoteIp);
				}

				if(al_liquidacion.isLiquidar())
				{
					String ls_idSolicitudCorrecciones;
					String ls_idTurnoCorrecciones;

					ls_idSolicitudCorrecciones     = null;
					ls_idTurnoCorrecciones         = al_liquidacion.getIdTurnoCorrecciones();

					{
						Solicitud ls_solicitudCorrecciones;

						ls_solicitudCorrecciones = al_liquidacion.getSolicitud();

						if(ls_solicitudCorrecciones != null)
						{
							String ls_idTurnoAnt;

							ls_idTurnoAnt = ls_solicitudCorrecciones.getIdTurnoAnt();

							if(
							    StringUtils.isValidString(ls_idTurnoAnt)
								    && StringUtils.isValidString(ls_idTurnoCorrecciones)
							)
							{
								Turno lt_turnoCorrecciones;

								lt_turnoCorrecciones = DaoCreator.getTurnoDAO(ldm_manager)
										                             .findById(ls_idTurnoCorrecciones);

								if(lt_turnoCorrecciones != null)
								{
									SolicitudDAO lsd_DAO;

									lsd_DAO     = DaoCreator.getSolicitudDAO(ldm_manager);

									ls_solicitudCorrecciones = lsd_DAO.findById(lt_turnoCorrecciones.getIdSolicitud());

									if(ls_solicitudCorrecciones != null)
									{
										ls_solicitudCorrecciones.setIdTurnoAnt(ls_idTurnoAnt);
										ls_solicitudCorrecciones.setIdUsuarioModificacion(as_userId);
										ls_solicitudCorrecciones.setIpModificacion(as_remoteIp);

										lsd_DAO.update(ls_solicitudCorrecciones);

										ls_idSolicitudCorrecciones = ls_solicitudCorrecciones.getIdSolicitud();

										if(StringUtils.isValidString(ls_idSolicitudCorrecciones))
											al_liquidacion.setIdSolicitud(ls_idSolicitudCorrecciones);
									}
								}
							}
						}
					}

					al_liquidacion.setIdUsuarioCreacion(as_userId);
					al_liquidacion.setIpCreacion(as_remoteIp);

					al_liquidacion = DaoCreator.getProcedimientosDAO(ldm_manager).procLiquidacion(al_liquidacion);

					if(al_liquidacion != null)
					{
						String ls_codigoRespuesta;

						ls_codigoRespuesta = al_liquidacion.getCodigoRespuesta();

						if(StringUtils.isValidString(ls_codigoRespuesta))
						{
							int li_codigoRespuesta;

							li_codigoRespuesta = NumericUtils.getInt(ls_codigoRespuesta);

							if(li_codigoRespuesta < 0)
								throw new B2BException(al_liquidacion.getRespuestaLiquidacion());

							if(li_codigoRespuesta == 0)
							{
								BigDecimal lbd_totalSaldoAFavor;

								lbd_totalSaldoAFavor = al_liquidacion.getTotalSaldoFavor();

								if(NumericUtils.isValidBigDecimal(lbd_totalSaldoAFavor, BigDecimal.valueOf(1D)))
								{
									RegistroPagoExceso lrpe_registroPagoExceso;

									lrpe_registroPagoExceso = al_liquidacion.getRegistroPagoExceso();

									if(lrpe_registroPagoExceso != null)
									{
										Liquidacion ll_liquidacion;

										ll_liquidacion = DaoCreator.getAccLiquidacionDAO(ldm_manager)
												                       .findByIdSolicitudOne(
												    al_liquidacion.getIdSolicitud()
												);

										if(ll_liquidacion != null)
										{
											Collection<RegistroPagoExceso> lcrpe_pagoExceso;

											lcrpe_pagoExceso = DaoCreator.getRegistroPagoExcesoDAO(ldm_manager)
													                         .findByIdLiquidacion(
													    ll_liquidacion.getIdLiquidacion()
													);

											if(CollectionUtils.isValidCollection(lcrpe_pagoExceso))
											{
												String ls_conceptoPagoExceso;

												ls_conceptoPagoExceso = lrpe_registroPagoExceso.getConceptoPagoExceso();

												if(!StringUtils.isValidString(ls_conceptoPagoExceso))
													throw new B2BException(
													    ErrorKeys.ERROR_DEBE_DIGITAR_CONCEPTO_PAGO_EXCESO
													);

												{
													RegistroPagoExcesoDAO lrped_DAO;

													lrped_DAO = DaoCreator.getRegistroPagoExcesoDAO(ldm_manager);

													for(RegistroPagoExceso lrpe_iterador : lcrpe_pagoExceso)
													{
														if(lrpe_iterador != null)
														{
															lrpe_iterador.setConceptoPagoExceso(ls_conceptoPagoExceso);
															lrpe_iterador.setIdUsuarioModificacion(as_userId);
															lrpe_iterador.setIpModificacion(as_remoteIp);

															lrped_DAO.updateConcepto(lrpe_iterador);
														}
													}
												}
											}
										}
									}
								}

								{
									Registro lr_registro;

									lr_registro = new Registro();

									lr_registro.setIdUsuarioCreacion(as_userId);
									lr_registro.setIdCondicion(al_liquidacion.getIdCondicion());
									lr_registro.setTipoRecibo(IdentificadoresCommon.RECIBO_LIQUIDACION);
									lr_registro.setIdTipoMayorValor(al_liquidacion.getIdTipoMayorValor());
									lr_registro.setIdTurno(
									    StringUtils.isValidString(ls_idTurnoCorrecciones) ? ls_idTurnoCorrecciones
									                                                      : ls_idTurno
									);
									lr_registro.setIdTurnoHistoria(al_liquidacion.getIdTurnoHistoria());

									{
										Solicitud ls_solicitud;

										ls_solicitud = new Solicitud();

										ls_solicitud.setIdSolicitud(
										    StringUtils.isValidString(ls_idSolicitudCorrecciones)
										    ? ls_idSolicitudCorrecciones : ls_idSolicitud
										);

										lr_registro.setSolicitud(ls_solicitud);
									}

									lr_registro = getRegistroBusiness()
											              .generarReciboLiquidacion(
											    lr_registro, true, as_userId, null, null, ldm_manager
											);

									al_liquidacion.setRegistro(lr_registro);
								}
							}
						}
					}
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("liquidar", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return al_liquidacion;
	}

	/**
	 * Método para realizar transaccciones con la base de datos para realizar el proceso de liquidación.
	 *
	 * @param al_liquidacion Argumento de tipo Liquidacion que contiene los datos necesarios
	 * para realizar la liquidación.
	 * @param as_userId Argumento de tipo String que contiene el usuario que realiza la operación.
	 * @param as_remoteIp Argumento de tipo String que contiene el la ip desde donde se realiza la operación.
	 * @return Objeto de tipo Registro que contiene los archivos de la liquidación generados.
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see Registro
	 */
	public synchronized Registro procLiquidacion(Liquidacion al_liquidacion, String as_userId, String as_remoteIp)
	    throws B2BException
	{
		DAOManager ldm_manager;
		Registro   lr_parametros;

		lr_parametros     = new Registro();
		ldm_manager       = DaoManagerFactory.getDAOManager();

		try
		{
			if(al_liquidacion != null)
			{
				Solicitud ls_solicitud;

				ls_solicitud = al_liquidacion.getSolicitud();

				if(ls_solicitud != null)
				{
					String ls_idSolicitud;

					ls_idSolicitud = ls_solicitud.getIdSolicitud();

					if(StringUtils.isValidString(ls_idSolicitud))
					{
						SolicitudDAO lsd_DAO;
						Solicitud    ls_solicitudTmp;

						lsd_DAO             = DaoCreator.getSolicitudDAO(ldm_manager);
						ls_solicitudTmp     = lsd_DAO.findById(ls_idSolicitud);

						al_liquidacion.setIdSolicitud(ls_idSolicitud);

						if(ls_solicitudTmp != null)
						{
							Integer li_version;

							li_version = ls_solicitudTmp.getVersionSubProceso();

							if(!NumericUtils.isValidInteger(li_version) || (NumericUtils.getInt(li_version) <= 0))
								lsd_DAO.updateVersionSubproceso(
								    ls_solicitudTmp.getIdProceso(), ls_solicitudTmp.getIdSubproceso(),
								    ls_solicitudTmp.getIdSolicitud(), as_userId, as_remoteIp
								);
						}
					}
				}

				lr_parametros = procLiquidacion(al_liquidacion, ldm_manager, as_userId, as_remoteIp);

				if(al_liquidacion.isSimularLiquidacion())
				{
					al_liquidacion.setIdUsuarioModificacion(as_userId);
					al_liquidacion.setIpModificacion(as_remoteIp);

					DaoCreator.getAccLiquidacionDAO(ldm_manager).simularLiquidacion(al_liquidacion);
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("procLiquidacion", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lr_parametros;
	}

	/**
	 * Método para realizar transaccciones con la base de datos para realizar el proceso de liquidación.
	 *
	 * @param al_liquidacion Argumento de tipo Liquidacion que contiene los datos necesarios
	 * para realizar la liquidación.
	 * @param as_userId Argumento de tipo String que contiene el usuario que realiza la operación.
	 * @param as_remoteIp Argumento de tipo String que contiene el la ip desde donde se realiza la operación.
	 * @return Objeto de tipo Registro que contiene los archivos de la liquidación generados.
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see Registro
	 */
	public synchronized Registro procReLiquidacion(
	    Liquidacion al_liquidacion, Liquidacion al_data, String as_userId, String as_remoteIp
	)
	    throws B2BException
	{
		DAOManager ldm_manager;
		Registro   lr_parametros;

		lr_parametros     = new Registro();
		ldm_manager       = DaoManagerFactory.getDAOManager();

		try
		{
			if(al_liquidacion != null)
			{
				Solicitud ls_solicitud;
				String    ls_condicion;

				ls_solicitud     = al_liquidacion.getSolicitud();
				ls_condicion     = al_liquidacion.getIdCondicion();

				if(
				    !(StringUtils.isValidString(ls_condicion)
					    && ls_condicion.equalsIgnoreCase(IdentificadoresCommon.LIQUIDAR))
				)
					confirmarEliminarLiquidacion(al_data, as_userId, as_remoteIp, ldm_manager);

				if(ls_solicitud != null)
				{
					String ls_idSolicitud;

					ls_idSolicitud = ls_solicitud.getIdSolicitud();

					if(StringUtils.isValidString(ls_idSolicitud))
					{
						SolicitudDAO lsd_DAO;
						Solicitud    ls_solicitudTmp;

						lsd_DAO             = DaoCreator.getSolicitudDAO(ldm_manager);
						ls_solicitudTmp     = lsd_DAO.findById(ls_idSolicitud);

						if(ls_solicitudTmp != null)
						{
							Integer li_version;

							li_version = ls_solicitudTmp.getVersionSubProceso();

							if(!NumericUtils.isValidInteger(li_version) || (NumericUtils.getInt(li_version) <= 0))
								lsd_DAO.updateVersionSubproceso(
								    ls_solicitudTmp.getIdProceso(), ls_solicitudTmp.getIdSubproceso(),
								    ls_solicitudTmp.getIdSolicitud(), as_userId, as_remoteIp
								);
						}
					}
				}

				lr_parametros = procLiquidacion(al_liquidacion, ldm_manager, as_userId, as_remoteIp);
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("procReLiquidacion", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lr_parametros;
	}

	/**
	 * Método encargado de validar el turno ingresado para correcciones mayor valor.
	 *
	 * @param as_idTurno Argumento de tipo <code>String</code> que contiene el id turno a validar.
	 * @return Retorna variable de tipo <code>boolean</code> que determina si cumple con las validaciones para turno migrado.
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public synchronized boolean validacionesTurnoRegistroCorrecciones(String as_idTurno)
	    throws B2BException
	{
		DAOManager ldm_manager;
		boolean    lb_turnoMigrado;

		ldm_manager         = DaoManagerFactory.getDAOManager();
		lb_turnoMigrado     = false;

		try
		{
			if(!StringUtils.isValidString(as_idTurno))
				throw new B2BException(ErrorKeys.TURNO_INVALIDO);

			Turno lt_turno;

			lt_turno = DaoCreator.getTurnoDAO(ldm_manager).findById(as_idTurno);

			if(lt_turno == null)
			{
				Object[] loa_object;

				loa_object     = new String[1];

				loa_object[0] = as_idTurno;

				throw new B2BException(ErrorKeys.TURNO_NO_EXISTE, loa_object);
			}

			{
				String ls_idProceso;

				ls_idProceso = lt_turno.getIdProceso();

				if(
				    !StringUtils.isValidString(ls_idProceso)
					    || !ls_idProceso.equalsIgnoreCase(ProcesoCommon.ID_PROCESO_6)
				)
					throw new B2BException(ErrorKeys.TURNO_NO_PERTENECE_REGISTRO);
			}

			{
				Constantes lc_constante;

				lc_constante = DaoCreator.getConstantesDAO(ldm_manager)
						                     .findById(ConstanteCommon.ETAPAS_VALIDAR_TURNO_CORRECCIONES_MAYOR_VALOR);

				if(lc_constante != null)
				{
					Collection<String> lcs_etapas;

					lcs_etapas = ListadoCodigosConstantes.generarCodigosCollection(lc_constante.getCaracter());

					if(CollectionUtils.isValidCollection(lcs_etapas))
					{
						Iterator<String> lis_iterator;
						boolean          lb_encontrada;

						lis_iterator      = lcs_etapas.iterator();
						lb_encontrada     = false;

						while(lis_iterator.hasNext() && !lb_encontrada)
						{
							String ls_etapaActual;
							String ls_etapaActualTurno;

							ls_etapaActual          = lis_iterator.next();
							ls_etapaActualTurno     = StringUtils.getString(lt_turno.getIdEtapaActual());

							lb_encontrada = StringUtils.isValidString(ls_etapaActual)
									&& StringUtils.isValidString(ls_etapaActualTurno)
									&& ls_etapaActual.equalsIgnoreCase(ls_etapaActualTurno);
						}

						if(!lb_encontrada)
							throw new B2BException(ErrorKeys.TURNO_INGRESADO_EN_TRAMITE);
					}
				}
			}

			{
				//devolucion dinero
			}

			{
				String ls_migrado;

				ls_migrado = lt_turno.getMigrado();

				if(StringUtils.isValidString(ls_migrado) && ls_migrado.equalsIgnoreCase(EstadoCommon.S))
				{
					Collection<Liquidacion> lcl_liquidacion;

					lcl_liquidacion = DaoCreator.getAccLiquidacionDAO(ldm_manager).findByIdTurno(as_idTurno);

					if(!CollectionUtils.isValidCollection(lcl_liquidacion))
						throw new B2BException(ErrorKeys.ERROR_DEBE_REALIZAR_HOMOLOGACION_ACTOS_MAYOR_VALOR);
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("validacionesTurnoRegistroCorrecciones", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lb_turnoMigrado;
	}

	/**
	 * Método que permite extraer funcionalidades de la clase FirmaMasivaBusiness.
	 *
	 * @return el valor de firma masiva business
	 */
	protected CalificacionBusiness getCalificacionBusiness()
	{
		if(icb_calificacionBusiness == null)
			icb_calificacionBusiness = new CalificacionBusiness();

		return icb_calificacionBusiness;
	}

	/**
	 * Método que permite extraer funcionalidades de la clase FirmaMasivaBusiness.
	 *
	 * @return el valor de firma masiva business
	 */
	protected FirmaMasivaBusiness getFirmaMasivaBusiness()
	{
		if(ifmb_firmaMasivaBusiness == null)
			ifmb_firmaMasivaBusiness = new FirmaMasivaBusiness();

		return ifmb_firmaMasivaBusiness;
	}

	/**
	 * Metodo encargado de salvar los registros enviados en solicitud matricula acto.
	 *
	 * @param acsm_solicitudMatricula correspondiente al valor del tipo de objeto Collection<SolicitudMatricula>
	 * @param acsma_solicitudMatriculaActo Argumento de tipo Collection<SolicitudMatriculaActo> que
	 * contiene todos los registros a salvar en en solicitud matricula acto.
	 * @param adm_manager Argumento de tipo DAOManager que permite la conexión a la base de datos.
	 * @param as_userId Argumento de tipo String que contiene el usuario que realiza la operación.
	 * @param as_remoteIp Argumento de tipo String que contiene el la ip de la maquina desde la cual
	 * se realiza la operación.
	 * @throws B2BException Señala que se ha producido una excepción
	 */

	/**
	 * Método  encargado de ejecutar todo lo referente al proceso de asociar matricula.
	 * @param acsm_solicitudMatricula Colección de datos de tipo SolicitudMatricula que contiene los parámetros  necesarios para insertar o actualizar en la tabla SDB_ACC_SOLICITUD_MATRICULA.
	 * @param acsma_solicitudMatriculaActo Colección de datos de tipo SolicitudMatriculaActo que contiene los parámetros  necesarios para insertar o actualizar en la tabla SDB_ACC_SOLICITUD_MATRICULA_ACTO.
	 * @param adm_manager Objeto de tipo DAOManager utilizado para realizar la conexion a la base de datos.
	 * @param as_userId  Variable de tipo String que contiene el usuario que realiza la acción.
	 * @param as_remoteIp Variable de tipo String que contiene la dirección ip remota desde donde se realiza la acción
	 * @throws B2BException
	 */
	private void salvarAsociarMatricula(
	    Collection<SolicitudMatricula> acsm_solicitudMatricula,
	    Collection<SolicitudMatriculaActo> acsma_solicitudMatriculaActo, DAOManager adm_manager, String as_userId,
	    String as_remoteIp
	)
	    throws B2BException
	{
		Object[] loa_messageArgs = new String[2];

		try
		{
			if(CollectionUtils.isValidCollection(acsm_solicitudMatricula))
			{
				SolicitudMatriculaDAO     lsm_DAO;
				SolicitudMatriculaActoDAO lsma_DAO;

				lsm_DAO      = DaoCreator.getSolicitudMatriculaDAO(adm_manager);
				lsma_DAO     = DaoCreator.getSolicitudMatriculaActoDAO(adm_manager);

				for(SolicitudMatricula lsm_actual : acsm_solicitudMatricula)
				{
					if(lsm_actual != null)
					{
						long   ll_matricula;
						String ls_circuloRegistral;
						String ls_idSolicitud;
						String ls_estado;

						ll_matricula            = lsm_actual.getIdMatricula();
						ls_circuloRegistral     = lsm_actual.getIdCirculo();
						ls_idSolicitud          = lsm_actual.getIdSolicitud();
						ls_estado               = lsm_actual.getEstado();

						loa_messageArgs[0]     = ls_circuloRegistral;
						loa_messageArgs[1]     = StringUtils.getString(ll_matricula);

						if(
						    StringUtils.isValidString(ls_circuloRegistral)
							    && NumericUtils.isValidLong(NumericUtils.getLongWrapper(ll_matricula), 1)
							    && NumericUtils.isValidLong(NumericUtils.getLongWrapper(ls_idSolicitud), 1)
						)
						{
							SolicitudMatricula lsm_matriculaExist;
							SolicitudMatricula lsm_matriculaInsert;

							lsm_matriculaInsert = new SolicitudMatricula();

							lsm_matriculaInsert.setIdSolicitud(lsm_actual.getIdSolicitud());
							lsm_matriculaInsert.setIdMatricula(ll_matricula);
							lsm_matriculaInsert.setIdCirculo(ls_circuloRegistral);

							lsm_matriculaExist = lsm_DAO.findById(lsm_matriculaInsert);

							if(lsm_matriculaExist == null)
							{
								String ls_expedirCertificado;

								ls_expedirCertificado = lsm_actual.getExpedirCertificado();

								if(
								    StringUtils.isValidString(ls_expedirCertificado)
									    && ls_expedirCertificado.equalsIgnoreCase(EstadoCommon.S)
								)
									lsm_matriculaInsert.setIndMayorValor(EstadoCommon.M);

								lsm_matriculaInsert.setEstado(ls_estado);
								lsm_matriculaInsert.setExpedirCertificado(ls_expedirCertificado);
								lsm_matriculaInsert.setCantidadCertificados(lsm_actual.getCantidadCertificados());
								lsm_matriculaInsert.setIdUsuarioCreacion(lsm_actual.getIdUsuarioCreacion());
								lsm_matriculaInsert.setEstado(lsm_actual.getEstado());
								lsm_matriculaInsert.setIdDatosAntSistema(lsm_actual.getIdDatosAntSistema());
								lsm_matriculaInsert.setIdUsuarioCreacion(as_userId);
								lsm_matriculaInsert.setIpCreacion(as_remoteIp);
								lsm_DAO.insertOrUpdate(lsm_matriculaInsert, true);
							}
						}
						else
							throw new B2BException(ErrorKeys.ERROR_MATRICULA_NO_VALIDA, loa_messageArgs);
					}
				}

				{
					Collection<SolicitudMatriculaActo> lcsma_matriculasActos;

					lcsma_matriculasActos = new ArrayList<SolicitudMatriculaActo>(acsma_solicitudMatriculaActo);

					if(CollectionUtils.isValidCollection(lcsma_matriculasActos))
					{
						for(SolicitudMatriculaActo lsma_actual : lcsma_matriculasActos)
						{
							if(lsma_actual != null)
							{
								SolicitudMatriculaActo lsma_matriculaActoExists;

								lsma_matriculaActoExists = lsma_DAO.findById(lsma_actual);

								if(lsma_matriculaActoExists != null)
								{
									lsma_actual.setIdUsuarioModificacion(as_userId);
									lsma_actual.setIpModificacion(as_remoteIp);
									lsma_DAO.insertOrUpdate(lsma_actual, false);
								}
								else
								{
									lsma_actual.setIdUsuarioCreacion(as_userId);
									lsma_actual.setIpCreacion(as_remoteIp);
									lsma_DAO.insertOrUpdate(lsma_actual, true);
								}
							}
						}
					}
				}
			}
			else
				throw new B2BException(ErrorKeys.ERROR_SIN_PREDIOS_CON_ACTOS);
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("salvarAsociarMatricula", lb2be_e);

			throw lb2be_e;
		}
	}
}
