package com.bachue.snr.prosnr01.business.consulta.trazabilidad;

import co.gov.supernotariado.www.schemas.bachue.cb.consultatrazabilidad.consultaActosTurno.v1.TipoEntradaConsultaActosTurno;
import co.gov.supernotariado.www.schemas.bachue.cb.consultatrazabilidad.consultaActosTurno.v1.TipoSalidaConsultaActosTurno;
import co.gov.supernotariado.www.schemas.bachue.cb.consultatrazabilidad.consultaActosTurno.v1.TipoSalidaConsultaActosTurnoActosActo;
import co.gov.supernotariado.www.schemas.bachue.cb.consultatrazabilidad.consultaDetalleCertificado.v1.TipoEntradaConsultaDetalleCertificado;
import co.gov.supernotariado.www.schemas.bachue.cb.consultatrazabilidad.consultaDetalleCertificado.v1.TipoSalidaConsultaDetalleCertificado;
import co.gov.supernotariado.www.schemas.bachue.cb.consultatrazabilidad.consultaDetalleCertificado.v1.TipoSalidaConsultaDetalleCertificadoCertificadosCertificado;
import co.gov.supernotariado.www.schemas.bachue.cb.consultatrazabilidad.consultartrazabilidad.v1.TipoEntradaConsultarTrazabilidad;
import co.gov.supernotariado.www.schemas.bachue.cb.consultatrazabilidad.consultartrazabilidad.v1.TipoEntradaConsultarTrazabilidadCriterioBusqueda;

import com.b2bsg.common.dataAccess2.DAOManager;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.logging.LoggerHandler;

import com.b2bsg.common.util.CollectionUtils;
import com.b2bsg.common.util.NumericUtils;
import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.business.BaseBusiness;

import com.bachue.snr.prosnr01.common.constants.CodigoMensajeCommon;
import com.bachue.snr.prosnr01.common.constants.ErrorKeys;
import com.bachue.snr.prosnr01.common.constants.EstadoCommon;
import com.bachue.snr.prosnr01.common.constants.IdentificadoresCommon;
import com.bachue.snr.prosnr01.common.constants.ProcesoCommon;
import com.bachue.snr.prosnr01.common.constants.SistemaOrigenCommon;

import com.bachue.snr.prosnr01.dao.DaoCreator;
import com.bachue.snr.prosnr01.dao.DaoManagerFactory;
import com.bachue.snr.prosnr01.dao.acc.LiquidacionItemCertificadoDAO;
import com.bachue.snr.prosnr01.dao.acc.LiquidacionItemDAO;
import com.bachue.snr.prosnr01.dao.acc.SolicitudDAO;
import com.bachue.snr.prosnr01.dao.acc.SolicitudMatriculaDAO;
import com.bachue.snr.prosnr01.dao.acc.SubprocesoDAO;
import com.bachue.snr.prosnr01.dao.acc.TipoActoDAO;
import com.bachue.snr.prosnr01.dao.acc.TurnoDAO;
import com.bachue.snr.prosnr01.dao.bgn.DocumentoDAO;
import com.bachue.snr.prosnr01.dao.pgn.CirculoRegistralDao;

import com.bachue.snr.prosnr01.model.calificacion.RegistroCalificacion;
import com.bachue.snr.prosnr01.model.consulta.trazabilidad.ConsultaTrazabilidad;
import com.bachue.snr.prosnr01.model.consulta.trazabilidad.Trazabilidad;
import com.bachue.snr.prosnr01.model.sdb.acc.Acto;
import com.bachue.snr.prosnr01.model.sdb.acc.Liquidacion;
import com.bachue.snr.prosnr01.model.sdb.acc.LiquidacionItemCertificado;
import com.bachue.snr.prosnr01.model.sdb.acc.Solicitud;
import com.bachue.snr.prosnr01.model.sdb.acc.SolicitudMatricula;
import com.bachue.snr.prosnr01.model.sdb.acc.Subproceso;
import com.bachue.snr.prosnr01.model.sdb.acc.Turno;
import com.bachue.snr.prosnr01.model.sdb.acc.TurnoDerivado;
import com.bachue.snr.prosnr01.model.sdb.pgn.CirculoRegistral;
import com.bachue.snr.prosnr01.model.sdb.pgn.TipoActo;

import java.math.BigDecimal;
import java.math.BigInteger;

import java.rmi.RemoteException;

import java.util.Collection;
import java.util.Iterator;
import java.util.Map;


/**
 * Clase que contiene todos las funcionalidades para el servicio de consultar la trazabilidad
 *
 * @author hcastaneda
 */
public class ServiceConsultaTrazabilidadBusiness extends BaseBusiness
{
	/** Constante clh_LOGGER. */
	private static final LoggerHandler clh_LOGGER = (LoggerHandler)com.bachue.snr.prosnr01.common.utils.LoggingHelper
			.getLogger(ServiceConsultaTrazabilidadBusiness.class);

	/**
	 * Consulta actos turno.
	 *
	 * @param atecat_peticion correspondiente al valor de entrada
	 * @param as_userId Objeto de tipo String que contiene el usuario que realiza la peticion
	 * @param as_localIp Objeto de tipo String que contiene IP local que realiza la peticion
	 * @param as_remoteIp Objeto de tipo String que contiene IP remota que realiza la peticion
	 * @return el valor de co.gov.supernotariado.www.schemas.bachue.cb.consultatrazabilidad.consulta actos turno.v 1 . tipo salida consulta actos turno
	 * @throws RemoteException cuando se produce algun error en el proceso
	 */
	public synchronized TipoSalidaConsultaActosTurno consultaActosTurno(
	    TipoEntradaConsultaActosTurno atecat_peticion, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		DAOManager                   ldm_manager;
		TipoSalidaConsultaActosTurno ltscpl_response;
		String                       ls_descripcionMensaje;
		BigInteger                   lbi_codigoMensaje;

		ls_descripcionMensaje     = "OK";
		ltscpl_response           = null;
		lbi_codigoMensaje         = BigInteger.valueOf(CodigoMensajeCommon.CODIGO_200);
		ldm_manager               = DaoManagerFactory.getDAOManager();

		try
		{
			if(atecat_peticion != null)
			{
				String  ls_modulo;
				String  ls_criterioBusqueda;
				String  ls_valorCriterio;
				boolean lb_turno;

				ls_criterioBusqueda     = atecat_peticion.getCriterioBusqueda();
				ls_modulo               = atecat_peticion.getModulo();
				ls_valorCriterio        = atecat_peticion.getValorCriterioBusqueda();
				lb_turno                = false;

				if(StringUtils.isValidString(ls_modulo))
				{
					if(!ls_modulo.equalsIgnoreCase(SistemaOrigenCommon.SEDE_ELECTRONICA))
						throw new B2BException(addMessage(ErrorKeys.ERROR_MODULO_NO_PERMITIDO_CT, null, true, null));
				}
				else
					throw new B2BException(addMessage(ErrorKeys.ERROR_MODULO_NO_PERMITIDO_CT, null, true, null));

				if(StringUtils.isValidString(ls_criterioBusqueda))
				{
					lb_turno = ls_criterioBusqueda.equalsIgnoreCase(IdentificadoresCommon.TURNO);

					if(!lb_turno)
						throw new B2BException(addMessage(ErrorKeys.ERROR_CRITERIO_BUSQUEDA_NO_PERMITIDO_CT, null, true, null));
				}
				else
					throw new B2BException(addMessage(ErrorKeys.ERROR_CRITERIO_BUSQUEDA_NO_PERMITIDO_CT, null, true, null));

				if(!StringUtils.isValidString(ls_valorCriterio))
					throw new B2BException(addMessage(ErrorKeys.ERROR_VALOR_CRITERIO_BUSQUEDA_NO_VALIDO_CT, null, true, null));
				else if(!ls_valorCriterio.contains(IdentificadoresCommon.SIMBOLO_GUION))
					throw new B2BException(addMessage(ErrorKeys.ERROR_VALOR_CRITERIO_TURNO_FORMATO_NO_VALIDO_CT, null, true, null));

				{
					Solicitud ls_solicitud;
					String    ls_idSolicitud;
					String    ls_idCirculo;
					Turno     lt_turno;

					lt_turno = DaoCreator.getTurnoDAO(ldm_manager).findById(ls_valorCriterio);

					if(lt_turno == null)
						throw new B2BException(addMessage(ErrorKeys.ERROR_CONSULTA_SIN_RESULTADOS_CT, null, true, null));

					ls_idSolicitud     = lt_turno.getIdSolicitud();
					ls_idCirculo       = lt_turno.getIdCirculo();

					if(!StringUtils.isValidString(ls_idSolicitud))
						throw new B2BException(addMessage(ErrorKeys.ERROR_CONSULTA_SIN_RESULTADOS_CT, null, true, null));

					ls_solicitud = DaoCreator.getSolicitudDAO(ldm_manager).findById(ls_idSolicitud);

					if(ls_solicitud == null)
						throw new B2BException(addMessage(ErrorKeys.ERROR_CONSULTA_SIN_RESULTADOS_CT, null, true, null));

					{
						Collection<Acto> lca_actos;
						int              li_contador;

						lca_actos = DaoCreator.getActoDAO(ldm_manager)
								                  .findByIdSolicitudCirculo(ls_idSolicitud, ls_idCirculo);

						if(CollectionUtils.isValidCollection(lca_actos))
						{
							Liquidacion                             ll_liquidacion;
							LiquidacionItemDAO                      llid_DAO;
							TipoSalidaConsultaActosTurnoActosActo[] ltscataa_actos;
							TipoActoDAO                             ltas_DAO;
							String                                  ls_idLiquidacion;
							int                                     li_consecutivoLiquidacion;

							ll_liquidacion     = DaoCreator.getAccLiquidacionDAO(ldm_manager)
									                           .findByIdSolicitudOne(ls_idSolicitud);
							llid_DAO           = DaoCreator.getAccLiquidacionItemDAO(ldm_manager);
							ltscataa_actos     = new TipoSalidaConsultaActosTurnoActosActo[lca_actos.size()];
							ltas_DAO           = DaoCreator.getTipoActoDAO(ldm_manager);
							li_contador        = 0;

							if(ll_liquidacion == null)
								throw new B2BException(addMessage(ErrorKeys.ERROR_CONSULTA_SIN_RESULTADOS_CT, null, true, null));

							ls_idLiquidacion              = ll_liquidacion.getIdLiquidacion();
							li_consecutivoLiquidacion     = ll_liquidacion.getConsecutivo();

							for(Acto la_actual : lca_actos)
							{
								if(la_actual != null)
								{
									TipoActo lta_tipoActo;
									boolean  lb_actoConCuantia;
									boolean  lb_actoTarifExenta;
									String   ls_idActo;
									String   ls_cantidadActos;
									String   ls_valorCuantia;
									String   ls_valorAvaluo;
									String   ls_valorDerechos;
									String   ls_valorConservacionDocumental;
									String   ls_valorTotal;

									lb_actoConCuantia                  = false;
									lb_actoTarifExenta                 = false;
									ls_idActo                          = la_actual.getIdActo();
									ls_cantidadActos                   = null;
									ls_valorCuantia                    = null;
									ls_valorAvaluo                     = null;
									ls_valorDerechos                   = null;
									ls_valorConservacionDocumental     = null;
									ls_valorTotal                      = null;

									lta_tipoActo = ltas_DAO.findById(la_actual.getIdTipoActo());

									if(lta_tipoActo == null)
										throw new B2BException(addMessage(ErrorKeys.ERROR_CONSULTA_SIN_RESULTADOS_CT, null, true, null));

									{
										String ls_tmp;

										ls_tmp = lta_tipoActo.getActoSinCuantia();

										if(StringUtils.isValidString(ls_tmp))
											lb_actoConCuantia = !ls_tmp.equals(EstadoCommon.S);
									}

									{
										String ls_tmp;

										ls_tmp = lta_tipoActo.getTarifaExenta();

										if(StringUtils.isValidString(ls_tmp))
											lb_actoTarifExenta = ls_tmp.equals(EstadoCommon.S);
									}

									{
										BigDecimal lbd_tmp;

										lbd_tmp = la_actual.getCuantia();

										if(NumericUtils.isValidBigDecimal(lbd_tmp))
											ls_valorCuantia = lbd_tmp.toString();
									}

									{
										BigDecimal lbd_tmp;

										lbd_tmp = la_actual.getValorAvaluo();

										if(NumericUtils.isValidBigDecimal(lbd_tmp))
											ls_valorAvaluo = lbd_tmp.toString();
									}

									{
										BigDecimal lbd_tmp;

										lbd_tmp = la_actual.getCantidadActos();

										if(NumericUtils.isValidBigDecimal(lbd_tmp))
											ls_cantidadActos = lbd_tmp.toString();
									}

									{
										Liquidacion lli_liquidacionItem;

										lli_liquidacionItem = llid_DAO.findByActoCirculoLiquidacionConsecutivo(
											    new Liquidacion(
											        ls_idLiquidacion, li_consecutivoLiquidacion, ls_idCirculo, ls_idActo
											    )
											);

										if(lli_liquidacionItem != null)
										{
											{
												Double ld_tmp;

												ld_tmp = lli_liquidacionItem.getValorFinal();

												if(NumericUtils.isValidDouble(ld_tmp))
													ls_valorDerechos = ld_tmp.toString();
											}

											{
												Double ld_tmp;

												ld_tmp = lli_liquidacionItem.getValorConservDocumental();

												if(NumericUtils.isValidDouble(ld_tmp))
													ls_valorConservacionDocumental = ld_tmp.toString();
											}

											{
												BigDecimal lbd_tmp;

												lbd_tmp = lli_liquidacionItem.getValorTotal();

												if(NumericUtils.isValidBigDecimal(lbd_tmp))
													ls_valorTotal = lbd_tmp.toString();
											}
										}
									}

									ltscataa_actos[li_contador++] = new TipoSalidaConsultaActosTurnoActosActo(
										    ls_idActo, lta_tipoActo.getIdTipoActo(), lta_tipoActo.getNombre(),
										    StringUtils.getString(lb_actoConCuantia),
										    StringUtils.getString(lb_actoTarifExenta), ls_valorCuantia, ls_valorAvaluo,
										    ls_cantidadActos, ls_valorDerechos, ls_valorConservacionDocumental,
										    ls_valorTotal
										);
								}
							}

							ltscpl_response = new TipoSalidaConsultaActosTurno(
								    ls_solicitud.getNir(), ltscataa_actos, lbi_codigoMensaje.toString(),
								    ls_descripcionMensaje
								);
						}
						else
							throw new B2BException(addMessage(ErrorKeys.ERROR_CONSULTA_SIN_RESULTADOS_CT, null, true, null));
					}
				}
			}
			else
				throw new B2BException(ErrorKeys.ERROR_OPERACION_NO_EXITOSA);
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();
			clh_LOGGER.error("consultaActosTurno", lb2be_e);
			ltscpl_response = null;

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
			clh_LOGGER.error("consultaActosTurno", le_e);
			ltscpl_response     = null;

			lbi_codigoMensaje         = BigInteger.valueOf(CodigoMensajeCommon.CODIGO_500);
			ls_descripcionMensaje     = le_e.getMessage();
		}
		finally
		{
			ldm_manager.close();
		}

		{
			if(ltscpl_response == null)
			{
				TipoSalidaConsultaActosTurnoActosActo[] ltscataa_actos;

				ltscataa_actos      = new TipoSalidaConsultaActosTurnoActosActo[0];
				ltscpl_response     = new TipoSalidaConsultaActosTurno(
					    "", ltscataa_actos, lbi_codigoMensaje.toString(), ls_descripcionMensaje
					);
			}

			return ltscpl_response;
		}
	}

	/**
	 * Consulta detalle certificado.
	 *
	 * @param atecdc_peticion correspondiente al valor de entrada
	 * @param as_userId Objeto de tipo String que contiene el usuario que realiza la peticion
	 * @param as_localIp Objeto de tipo String que contiene IP local que realiza la peticion
	 * @param as_remoteIp Objeto de tipo String que contiene IP remota que realiza la peticion
	 * @return el valor de co.gov.supernotariado.www.schemas.bachue.cb.consultatrazabilidad.consulta detalle certificado.v 1 . tipo salida consulta detalle certificado
	 * @throws RemoteException cuando se produce algun error en el proceso
	 */
	public synchronized TipoSalidaConsultaDetalleCertificado consultaDetalleCertificado(
	    TipoEntradaConsultaDetalleCertificado atecdc_peticion, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		DAOManager                           ldm_manager;
		TipoSalidaConsultaDetalleCertificado ltscdc_response;
		String                               ls_descripcionMensaje;
		BigInteger                           lbi_codigoMensaje;

		ls_descripcionMensaje     = "OK";
		ltscdc_response           = null;
		lbi_codigoMensaje         = BigInteger.valueOf(CodigoMensajeCommon.CODIGO_200);
		ldm_manager               = DaoManagerFactory.getDAOManager();

		try
		{
			if(atecdc_peticion != null)
			{
				String  ls_modulo;
				String  ls_criterioBusqueda;
				String  ls_valorCriterio;
				boolean lb_turno;

				ls_criterioBusqueda     = atecdc_peticion.getCriterioBusqueda();
				ls_modulo               = atecdc_peticion.getModulo();
				ls_valorCriterio        = atecdc_peticion.getValorCriterioBusqueda();
				lb_turno                = false;

				if(StringUtils.isValidString(ls_modulo))
				{
					if(!ls_modulo.equalsIgnoreCase(SistemaOrigenCommon.SEDE_ELECTRONICA))
						throw new B2BException(addMessage(ErrorKeys.ERROR_MODULO_NO_PERMITIDO_CT, null, true, null));
				}
				else
					throw new B2BException(addMessage(ErrorKeys.ERROR_MODULO_NO_PERMITIDO_CT, null, true, null));

				if(StringUtils.isValidString(ls_criterioBusqueda))
				{
					lb_turno = ls_criterioBusqueda.equalsIgnoreCase(IdentificadoresCommon.TURNO);

					if(!lb_turno)
						throw new B2BException(addMessage(ErrorKeys.ERROR_CRITERIO_BUSQUEDA_NO_PERMITIDO_CT, null, true, null));
				}
				else
					throw new B2BException(addMessage(ErrorKeys.ERROR_CRITERIO_BUSQUEDA_NO_PERMITIDO_CT, null, true, null));

				if(!StringUtils.isValidString(ls_valorCriterio))
					throw new B2BException(addMessage(ErrorKeys.ERROR_VALOR_CRITERIO_BUSQUEDA_NO_VALIDO_CT, null, true, null));
				else if(!ls_valorCriterio.contains(IdentificadoresCommon.SIMBOLO_GUION))
					throw new B2BException(addMessage(ErrorKeys.ERROR_VALOR_CRITERIO_TURNO_FORMATO_NO_VALIDO_CT, null, true, null));

				{
					Solicitud ls_solicitud;
					String    ls_idSolicitud;
					String    ls_idTurno;
					Turno     lt_turno;
					TurnoDAO  ltd_DAO;

					ltd_DAO      = DaoCreator.getTurnoDAO(ldm_manager);
					lt_turno     = ltd_DAO.findById(ls_valorCriterio);

					if(lt_turno == null)
						throw new B2BException(addMessage(ErrorKeys.ERROR_CONSULTA_SIN_RESULTADOS_CT, null, true, null));

					ls_idSolicitud     = lt_turno.getIdSolicitud();
					ls_idTurno         = lt_turno.getIdTurno();

					if(!StringUtils.isValidString(ls_idSolicitud))
						throw new B2BException(addMessage(ErrorKeys.ERROR_CONSULTA_SIN_RESULTADOS_CT, null, true, null));

					ls_solicitud = DaoCreator.getSolicitudDAO(ldm_manager).findById(ls_idSolicitud);

					if(ls_solicitud == null)
						throw new B2BException(addMessage(ErrorKeys.ERROR_CONSULTA_SIN_RESULTADOS_CT, null, true, null));

					{
						Collection<TurnoDerivado> lctd_turnosDerivados;
						int                       li_contador;

						lctd_turnosDerivados = DaoCreator.getTurnoDerivadoDAO(ldm_manager)
								                             .findByIdPadreIdProcesoHijo(
								    ls_idTurno, ProcesoCommon.ID_PROCESO_1
								);

						if(CollectionUtils.isValidCollection(lctd_turnosDerivados))
						{
							TipoSalidaConsultaDetalleCertificadoCertificadosCertificado[] ltscdc_certificados;
							SubprocesoDAO                                                 lspd_DAO;
							LiquidacionItemCertificadoDAO                                 llicd_DAO;

							ltscdc_certificados     = new TipoSalidaConsultaDetalleCertificadoCertificadosCertificado[lctd_turnosDerivados
									.size()];
							li_contador             = 0;
							lspd_DAO                = DaoCreator.getSubprocesoDAO(ldm_manager);
							llicd_DAO               = DaoCreator.getLiquidacionItemCertificadoDAO(ldm_manager);

							for(TurnoDerivado ltd_actual : lctd_turnosDerivados)
							{
								if(ltd_actual != null)
								{
									LiquidacionItemCertificado llic_certificado;
									Turno                      lt_turnoderivado;
									String                     ls_idProceso;
									String                     ls_idSubProceso;
									String                     ls_nombreSubProceso;
									String                     ls_idMatricula;
									String                     ls_cantidadCertificados;
									String                     ls_valor;
									String                     ls_valorTotal;

									lt_turnoderivado = ltd_DAO.findById(ltd_actual.getIdTurnoHijo());

									if(lt_turnoderivado == null)
										throw new B2BException(addMessage(ErrorKeys.ERROR_CONSULTA_SIN_RESULTADOS_CT, null, true, null));

									llic_certificado            = llicd_DAO.findByIdTurnoCertificado(
										    lt_turnoderivado.getIdTurno()
										);
									ls_idProceso                = lt_turnoderivado.getIdProceso();
									ls_idSubProceso             = lt_turnoderivado.getIdSubProceso();
									ls_nombreSubProceso         = null;
									ls_idMatricula              = null;
									ls_cantidadCertificados     = null;
									ls_valor                    = null;
									ls_valorTotal               = null;

									if(llic_certificado == null)
										throw new B2BException(addMessage(ErrorKeys.ERROR_CONSULTA_SIN_RESULTADOS_CT, null, true, null));

									if(
									    StringUtils.isValidString(ls_idProceso)
										    && StringUtils.isValidString(ls_idSubProceso)
									)
									{
										Subproceso lsp_subProceso;

										lsp_subProceso = lspd_DAO.findById(ls_idProceso, ls_idSubProceso);

										if(lsp_subProceso != null)
											ls_nombreSubProceso = lsp_subProceso.getNombre();
									}

									{
										long ll_tmp;

										ll_tmp = llic_certificado.getIdMatricula();

										if(ll_tmp > NumericUtils.DEFAULT_LONG_VALUE)
											ls_idMatricula = Long.toString(ll_tmp);
									}

									{
										long ll_tmp;

										ll_tmp = llic_certificado.getCantidad();

										if(ll_tmp > NumericUtils.DEFAULT_LONG_VALUE)
											ls_cantidadCertificados = Long.toString(ll_tmp);
									}

									{
										long ll_tmp;

										ll_tmp = llic_certificado.getValor();

										if(ll_tmp > NumericUtils.DEFAULT_LONG_VALUE)
											ls_valor = Long.toString(ll_tmp);
									}

									{
										long ll_tmp;

										ll_tmp = llic_certificado.getValorFinal();

										if(ll_tmp > NumericUtils.DEFAULT_LONG_VALUE)
											ls_valorTotal = Long.toString(ll_tmp);
									}

									ltscdc_certificados[li_contador++] = new TipoSalidaConsultaDetalleCertificadoCertificadosCertificado(
										    ls_idProceso, ls_idSubProceso, ls_nombreSubProceso,
										    llic_certificado.getIdCirculo(), ls_idMatricula,
										    llic_certificado.getIdTarifaFija(), ls_cantidadCertificados, ls_valor,
										    ls_valorTotal
										);
								}
							}

							ltscdc_response = new TipoSalidaConsultaDetalleCertificado(
								    ls_solicitud.getNir(), ltscdc_certificados, lbi_codigoMensaje.toString(),
								    ls_descripcionMensaje
								);
						}
						else
							throw new B2BException(addMessage(ErrorKeys.ERROR_CONSULTA_SIN_RESULTADOS_CT, null, true, null));
					}
				}
			}
			else
				throw new B2BException(ErrorKeys.ERROR_OPERACION_NO_EXITOSA);
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();
			clh_LOGGER.error("consultaActosTurno", lb2be_e);
			ltscdc_response = null;

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
			clh_LOGGER.error("consultaActosTurno", le_e);
			ltscdc_response     = null;

			lbi_codigoMensaje         = BigInteger.valueOf(CodigoMensajeCommon.CODIGO_500);
			ls_descripcionMensaje     = le_e.getMessage();
		}
		finally
		{
			ldm_manager.close();
		}

		{
			if(ltscdc_response == null)
			{
				TipoSalidaConsultaDetalleCertificadoCertificadosCertificado[] ltscataa_actos;

				ltscataa_actos      = new TipoSalidaConsultaDetalleCertificadoCertificadosCertificado[0];
				ltscdc_response     = new TipoSalidaConsultaDetalleCertificado(
					    "", ltscataa_actos, lbi_codigoMensaje.toString(), ls_descripcionMensaje
					);
			}

			return ltscdc_response;
		}
	}

	/**
	 * Método encargado de consultar la trazabilidad de un NIR o Turno suministrados mediante WebServices.
	 *
	 * @param atect_entrada Objeto de tipo TipoEntradaConsultarTrazabilidad que contiene la peticion del servicio
	 * @param as_userId Objeto de tipo String que contiene el usuario que realiza la peticion
	 * @param as_localIp Objeto de tipo String que contiene IP local que realiza la peticion
	 * @param as_remoteIp Objeto de tipo String que contiene IP remota que realiza la peticion
	 * @return Objeto de tipo ConsultaTrazabilidad que contiene la respuesta a la peticion recibida
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see ConsultaTrazabilidad
	 */
	public synchronized ConsultaTrazabilidad consultarTrazabilidad(
	    TipoEntradaConsultarTrazabilidad atect_entrada, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		DAOManager           ldm_manager;
		ConsultaTrazabilidad lsct_return;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		lsct_return     = null;

		try
		{
			if(atect_entrada != null)
			{
				TipoEntradaConsultarTrazabilidadCriterioBusqueda ltectcb_criterioBusqueda;
				String                                           ls_modulo;
				String                                           ls_criterioBusqueda;
				String                                           ls_valorCriterio;
				boolean                                          lb_nir;
				boolean                                          lb_turno;

				ltectcb_criterioBusqueda     = atect_entrada.getCriterioBusqueda();
				ls_modulo                    = atect_entrada.getModulo();
				ls_valorCriterio             = atect_entrada.getValorCriterioBusqueda();
				ls_criterioBusqueda          = null;
				lb_nir                       = false;
				lb_turno                     = false;

				if(StringUtils.isValidString(ls_modulo))
				{
					if(!ls_modulo.equalsIgnoreCase(SistemaOrigenCommon.SEDE_ELECTRONICA))
						throw new B2BException(ErrorKeys.ERROR_MODULO_NO_PERMITIDO);
				}
				else
					throw new B2BException(ErrorKeys.ERROR_MODULO_NO_VALIDO);

				if(ltectcb_criterioBusqueda != null)
				{
					ls_criterioBusqueda = ltectcb_criterioBusqueda.getValue();

					if(StringUtils.isValidString(ls_criterioBusqueda))
					{
						lb_nir       = ls_criterioBusqueda.equalsIgnoreCase(IdentificadoresCommon.NIR);
						lb_turno     = ls_criterioBusqueda.equalsIgnoreCase(IdentificadoresCommon.TURNO);

						if(!lb_nir && !lb_turno)
							throw new B2BException(ErrorKeys.ERROR_CRITERIO_BUSQUEDA_NO_PERMITIDO);
					}
					else
						throw new B2BException(ErrorKeys.ERROR_DEBE_SELECCIONAR_MINIMO_UN_CRITERIO);
				}
				else
					throw new B2BException(ErrorKeys.ERROR_DEBE_SELECCIONAR_MINIMO_UN_CRITERIO);

				if(!StringUtils.isValidString(ls_valorCriterio))
					throw new B2BException(ErrorKeys.ERROR_VALOR_CRITERIO_BUSQUEDA_NO_VALIDO);

				Trazabilidad lt_trazabilidad;

				if(lb_nir)
					lt_trazabilidad = new Trazabilidad(new Solicitud(null, ls_valorCriterio));
				else if(lb_turno)
					lt_trazabilidad = new Trazabilidad(new Turno(ls_valorCriterio));
				else
					throw new B2BException(ErrorKeys.ERROR_CRITERIO_BUSQUEDA_NO_PERMITIDO);

				lsct_return = getConsultaTrazabilidadBusiness()
						              .cargarInfoBandejaConsultaTrazabilidad(lt_trazabilidad, false, ldm_manager);

				if(lsct_return != null)
				{
					Collection<Trazabilidad> lct_turnos;

					lct_turnos = lsct_return.getTrazabilidad();
					lsct_return.setNirPrincipal(
					    getConsultaTrazabilidadBusiness()
						        .findDataNirs(lsct_return.getSolicitud(), lsct_return.getTurnoSolicitud(), ldm_manager)
					);

					if(CollectionUtils.isValidCollection(lct_turnos))
					{
						Iterator<Trazabilidad> lit_iterator;

						lit_iterator = lct_turnos.iterator();

						if(lit_iterator != null)
						{
							CirculoRegistralDao   lcrd_DAO;
							DocumentoDAO          ldd_DAO;
							SolicitudMatriculaDAO lsmd_DAO;
							TurnoDAO              ltd_DAO;
							SolicitudDAO          lsd_DAO;

							lcrd_DAO     = DaoCreator.getCirculoRegistralDAO(ldm_manager);
							ldd_DAO      = DaoCreator.getDocumentoDAO(ldm_manager);
							lsmd_DAO     = DaoCreator.getSolicitudMatriculaDAO(ldm_manager);
							ltd_DAO      = DaoCreator.getTurnoDAO(ldm_manager);
							lsd_DAO      = DaoCreator.getSolicitudDAO(ldm_manager);

							while(lit_iterator.hasNext())
							{
								Trazabilidad lt_actual;

								lt_actual = lit_iterator.next();

								if(lt_actual != null)
								{
									Turno     lt_turnoActual;
									Solicitud ls_solicitudActual;

									lt_turnoActual         = lt_actual.getTurno();
									ls_solicitudActual     = lt_actual.getSolicitud();

									if(ls_solicitudActual != null)
									{
										ls_solicitudActual = lsd_DAO.findById(ls_solicitudActual);

										if(lt_turnoActual != null)
										{
											String ls_idTurno;

											ls_idTurno     = lt_turnoActual.getIdTurno();

											lt_turnoActual = ltd_DAO.findById(ls_idTurno);

											{
												CirculoRegistral lcr_circulo;

												lcr_circulo = lcrd_DAO.findById(lt_turnoActual.getIdCirculo());

												if(lcr_circulo != null)
													lt_actual.setCirculoRegistral(lcr_circulo);
											}

											{
												RegistroCalificacion lrc_datosDocumento;

												lrc_datosDocumento = ldd_DAO.detalleDocumento(
													    ls_solicitudActual.getIdDocumento(),
													    "" + ls_solicitudActual.getVersionDocumento()
													);

												if(lrc_datosDocumento != null)
													lt_actual.setDetalleDocumento(lrc_datosDocumento);
											}

											{
												Collection<SolicitudMatricula> lcsm_matriculasTurno;

												lcsm_matriculasTurno = lsmd_DAO.findMatriculasByturno(
													    ls_idTurno, false
													);

												if(CollectionUtils.isValidCollection(lcsm_matriculasTurno))
												{
													for(SolicitudMatricula lsm_actual : lcsm_matriculasTurno)
													{
														if(lsm_actual != null)
															lsmd_DAO.findAlertByIdMatricula(lsm_actual);
													}

													lt_actual.setMatriculasTurno(lcsm_matriculasTurno);
												}
											}

											{
												Collection<ConsultaTrazabilidad> lcct_trazabilidadTurno;

												lcct_trazabilidadTurno = getConsultaTrazabilidadBusiness()
														                         .findConsultaTraza(
														    lt_turnoActual, ldm_manager
														);

												if(CollectionUtils.isValidCollection(lcct_trazabilidadTurno))
													lt_actual.setTrazabilidad(lcct_trazabilidadTurno);
											}
										}
									}
								}
							}
						}
					}
				}
				else
					throw new B2BException(ErrorKeys.ERROR_NO_INFORMACION_RELACIONADA_AL_NIR_TURNO_INGRESADOS);
			}
			else
				throw new B2BException(ErrorKeys.ERROR_PARAMETROS_NO_VALIDOS);
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();
			clh_LOGGER.error("consultarTrazabilidad", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lsct_return;
	}
}
