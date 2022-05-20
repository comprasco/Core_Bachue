package com.bachue.snr.prosnr01.business.notificarCatastro;

import co.gov.supernotariado.www.schemas.bachue.ce.recepcionNotificacionCatastro.notificarCambioCatastro.v1.TipoNumMatriculas;
import co.gov.supernotariado.www.schemas.bachue.ce.recepcionNotificacionCatastro.notificarCambioCatastro.v1.TipoORP;
import co.gov.supernotariado.www.schemas.bachue.ce.recepcionNotificacionCatastro.notificarCambioCatastro.v1.TipoSalidaNotificarCambioCatastro;

import com.b2bsg.common.dataAccess2.DAOManager;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.logging.LoggerHandler;

import com.b2bsg.common.util.BooleanUtils;
import com.b2bsg.common.util.CollectionUtils;
import com.b2bsg.common.util.NumericUtils;
import com.b2bsg.common.util.StringUtils;

import com.bachue.prosnr01.integracion.cliente.catastro.notificarCambioCatastro.ClienteNotificarCambioCatastro;

import com.bachue.snr.prosnr01.business.BaseBusiness;

import com.bachue.snr.prosnr01.common.constants.ConstanteCommon;
import com.bachue.snr.prosnr01.common.constants.ErrorKeys;
import com.bachue.snr.prosnr01.common.constants.EstadoCommon;
import com.bachue.snr.prosnr01.common.constants.FormatoFechaCommon;
import com.bachue.snr.prosnr01.common.constants.IdentificadoresCommon;
import com.bachue.snr.prosnr01.common.utils.ListadoCodigosConstantes;

import com.bachue.snr.prosnr01.dao.DaoCreator;
import com.bachue.snr.prosnr01.dao.DaoManagerFactory;
import com.bachue.snr.prosnr01.dao.acc.BitacoraProcesoDAO;
import com.bachue.snr.prosnr01.dao.bng.AnotacionPredioDAO;
import com.bachue.snr.prosnr01.dao.pgn.ConstantesDAO;

import com.bachue.snr.prosnr01.model.sdb.bng.AnotacionPredio;
import com.bachue.snr.prosnr01.model.sdb.pgn.Constantes;

import com.bachue.snr.prosnr10.common.constants.TipoOperacionCommon;

import com.bachue.snr.prosnr10.dao.acc.ServicioActualizacionHaciaCatastrosDAO;
import com.bachue.snr.prosnr10.dao.acc.ServicioActualizacionHaciaCatastrosDetalleDAO;

import com.bachue.snr.prosnr10.model.acc.ServicioActualizacionHaciaCatastros;
import com.bachue.snr.prosnr10.model.acc.ServicioActualizacionHaciaCatastrosDetalle;
import com.bachue.snr.prosnr10.model.catastro.NotificarCatastro;

import java.math.BigInteger;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import java.util.Map.Entry;

import java.util.Set;


/**
 * Clase que contiene todos las propiedades NotificarCatastroBusiness.
 *
 * @author  Carlos Calderón
 * Fecha de Creacion: 4/05/2020
 */
public class NotificarCatastroBusiness extends BaseBusiness
{
	/** Constante clh_LOGGER. */
	private static final LoggerHandler clh_LOGGER = (LoggerHandler)com.bachue.snr.prosnr01.common.utils.LoggingHelper
			.getLogger(NotificarCatastroBusiness.class);

	/**
	 * Notificar catastro.
	 *
	 * @param acap_parametros de lista de matriculas a notificar.
	 * @param as_idTipoOperacion de id tipo operacion
	 * @param as_remoteIp Argumento de tipo <code>String</code> que contiene la ip remota desde donde se realiza la operación.
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public synchronized void notificarCatastro(
	    Collection<AnotacionPredio> acap_parametros, String as_idCatastro, String as_idTipoOperacion,
	    String as_constanteBloqueo, String as_constanteEndPoint, String as_remoteIp
	)
	    throws B2BException
	{
		boolean lb_alreadyProcessing;
		String  ls_userId;

		ls_userId = null;

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

				clh_LOGGER.error("notificarCatastro", lb2be_e);

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
				lce_constant     = lcd_constant.findById(as_constanteBloqueo);

				if(lce_constant != null)
				{
					lb_alreadyProcessing = BooleanUtils.getBooleanValue(lce_constant.getCaracter());

					if(!lb_alreadyProcessing)
						lcd_constant.updateString(as_constanteBloqueo, EstadoCommon.S, ls_userId);
				}
				else
				{
					Object[] loa_messageArgs;

					loa_messageArgs        = new String[1];
					loa_messageArgs[0]     = StringUtils.getString(as_constanteBloqueo);

					throw new B2BException(ErrorKeys.ERROR_SIN_CONSTANTE, loa_messageArgs);
				}
			}
			catch(B2BException lb2be_e)
			{
				ldm_processing.setRollbackOnly();

				clh_LOGGER.error("notificarCatastro", lb2be_e);

				throw lb2be_e;
			}
			finally
			{
				ldm_processing.close();
			}
		}

		try
		{
			if(!lb_alreadyProcessing && StringUtils.isValidString(ls_userId))
			{
				DAOManager ldm_bitacora;
				DAOManager ldm_manager;

				ldm_bitacora     = DaoManagerFactory.getDAOManager();
				ldm_manager      = DaoManagerFactory.getDAOManager();

				try
				{
					if(CollectionUtils.isValidCollection(acap_parametros))
					{
						BitacoraProcesoDAO                            lbpd_bitacora;
						ConstantesDAO                                 lcd_constantes;
						Map<String, Set<String>>                      lmsss_listaMatriculasANotificar;
						NotificarCatastro                             anc_notificarCatastro;
						ServicioActualizacionHaciaCatastrosDetalleDAO lsahcdd_DAO;
						ServicioActualizacionHaciaCatastrosDAO        lsahcd_DAO;
						String                                        ls_endpoint;
						String                                        ls_codTransaccion;
						TipoSalidaNotificarCambioCatastro             ltsnca_respuesta;

						lcd_constantes                      = DaoCreator.getConstantesDAO(ldm_manager);
						lbpd_bitacora                       = DaoCreator.getBitacoraProcesoDAO(ldm_bitacora);
						lsahcd_DAO                          = DaoCreator.getServicioActualizacionHaciaCatastrosDAO(
							    ldm_manager
							);
						lsahcdd_DAO                         = DaoCreator
								.getServicioActualizacionHaciaCatastrosDetalleDAO(ldm_manager);
						lmsss_listaMatriculasANotificar     = new HashMap<String, Set<String>>();
						ls_endpoint                         = lcd_constantes.findString(as_constanteEndPoint);

						ldm_bitacora.setAutoCommit(true);

						ls_codTransaccion = lsahcd_DAO.insert(
							    new ServicioActualizacionHaciaCatastros(
							        null, as_idTipoOperacion, ls_userId, as_remoteIp
							    )
							);

						if(StringUtils.isValidString(ls_codTransaccion))
						{
							int li_idTransaccionDetalle;

							li_idTransaccionDetalle = lsahcdd_DAO.findMaxIdTransaccionDetalleByIdTransaccion(
								    ls_codTransaccion
								);

							for(AnotacionPredio ls_iterador : acap_parametros)
							{
								if(ls_iterador != null)
								{
									try
									{
										String ls_idCirculo;
										Long   ll_idMatricula;

										ls_idCirculo       = ls_iterador.getIdCirculo();
										ll_idMatricula     = ls_iterador.getIdMatricula();

										lsahcdd_DAO.insert(
										    new ServicioActualizacionHaciaCatastrosDetalle(
										        ls_codTransaccion, String.valueOf(++li_idTransaccionDetalle),
										        NumericUtils.getLong(ll_idMatricula), ls_idCirculo,
										        ls_iterador.getIdNaturalezaJuridica(), ls_iterador.getVersion(),
										        ls_userId, as_remoteIp
										    )
										);

										notificarCatastro(
										    ls_idCirculo, ll_idMatricula, lmsss_listaMatriculasANotificar, lbpd_bitacora,
										    ls_userId, as_remoteIp
										);
									}
									catch(B2BException lb2b_e)
									{
										clh_LOGGER.error("notificarCatastro", lb2b_e);

										escribirEnBitacoraProceso(
										    lbpd_bitacora, IdentificadoresCommon.NOTIFICACION_CATASTRO,
										    lb2b_e.getMessage(), ls_userId, as_remoteIp,
										    (ls_iterador != null)
										    ? (ls_iterador.getIdCirculo() + IdentificadoresCommon.SIMBOLO_GUION
										    + ls_iterador.getIdMatricula() + IdentificadoresCommon.SIMBOLO_GUION
										    + ls_iterador.getIdAnotacion()) : null
										);
									}
								}
							}
						}

						{
							ServicioActualizacionHaciaCatastros lsahc_transaccion;
							TipoORP[]                           ltoa_orips;

							ltoa_orips            = null;
							lsahc_transaccion     = lsahcd_DAO.buscarPorId(ls_codTransaccion);

							if(lsahc_transaccion == null)
								throw new B2BException(ErrorKeys.ERROR_TRANSACCION_NO_EXISTE);

							if(
							    (lmsss_listaMatriculasANotificar != null)
								    && (lmsss_listaMatriculasANotificar.size() > NumericUtils.DEFAULT_INT_VALUE)
							)
							{
								int li_contadorOrips;

								li_contadorOrips     = NumericUtils.DEFAULT_INT_VALUE;
								ltoa_orips           = new TipoORP[lmsss_listaMatriculasANotificar.size()];

								for(Entry<String, Set<String>> le_iterator : lmsss_listaMatriculasANotificar.entrySet())
								{
									if(le_iterator != null)
									{
										Set<String>         lss_matriculas;
										String              ls_idCirculo;
										TipoNumMatriculas[] ltm_matriculas;

										ls_idCirculo       = le_iterator.getKey();
										lss_matriculas     = le_iterator.getValue();
										ltm_matriculas     = null;

										if(
										    (lss_matriculas != null)
											    && (lss_matriculas.size() > NumericUtils.DEFAULT_INT_VALUE)
										)
										{
											int li_contadorMatriculas;

											li_contadorMatriculas     = NumericUtils.DEFAULT_INT_VALUE;
											ltm_matriculas            = new TipoNumMatriculas[lss_matriculas.size()];

											for(String ls_idMatricula : lss_matriculas)
											{
												if(StringUtils.isValidString(ls_idMatricula))
													ltm_matriculas[li_contadorMatriculas++] = new TipoNumMatriculas(
														    ls_idMatricula
														);
											}
										}

										ltoa_orips[li_contadorOrips++] = new TipoORP(ls_idCirculo, ltm_matriculas);
									}
								}
							}

							anc_notificarCatastro     = new NotificarCatastro(
								    as_idCatastro, ls_codTransaccion,
								    obtenerNombreTipoOperacionNotificarCatastro(as_idTipoOperacion, ldm_manager),
								    StringUtils.getString(
								        lsahc_transaccion.getFechaRegistro(),
								        FormatoFechaCommon.DIA_MES_ANIO_HORA_MINUTO_SEGUNDO
								    ), ltoa_orips
								);

							ltsnca_respuesta = ClienteNotificarCambioCatastro.notificarCambioCatastro(
								    anc_notificarCatastro, ls_endpoint
								);

							if(ltsnca_respuesta != null)
							{
								BigInteger lbi_codigoMensaje;

								lbi_codigoMensaje = ltsnca_respuesta.getCodMensaje();

								if((lbi_codigoMensaje != null) && (lbi_codigoMensaje.intValue() != 200))
									throw new B2BException(ltsnca_respuesta.getDescripcionMensaje());
							}
						}
					}
				}
				catch(B2BException lb2be_e)
				{
					ldm_bitacora.setRollbackOnly();
					ldm_manager.setRollbackOnly();

					clh_LOGGER.error("notificarCatastro", lb2be_e);
				}
				finally
				{
					ldm_bitacora.close();
					ldm_manager.close();
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("notificarCatastro", lb2be_e);

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
					DaoCreator.getConstantesDAO(ldm_processing)
						          .updateString(as_constanteBloqueo, EstadoCommon.N, ls_userId);
				}
				catch(B2BException lb2be_e)
				{
					ldm_processing.setRollbackOnly();

					clh_LOGGER.error("notificarCatastro", lb2be_e);

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
	 * Notificar catastro mutacion primer orden.
	 *
	 * @param as_remoteIp Argumento de tipo <code>String</code> que contiene la ip remota desde donde se realiza la operación.
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public synchronized void notificarCatastroPO(String as_remoteIp)
	    throws B2BException
	{
		notificarCatastros(
		    as_remoteIp, ConstanteCommon.JOB_NOTIFICAR_CATASTRO_MPO_WS_INVOKE,
		    ConstanteCommon.JOB_NOTIFICAR_CATASTRO_MPO_BLOQUEO, ConstanteCommon.JOB_NOTIFICAR_CATASTRO_MPO_ENDPOINT,
		    ConstanteCommon.JOB_NOTIFICAR_CATASTRO_MPO_ROWCOUNT, TipoOperacionCommon.CONSULTA_CAMBIO_PROPIETARIO
		);
	}

	/**
	 * Notificar catastro mutacion segundo orden con cambio propietario.
	 *
	 * @param as_remoteIp Argumento de tipo <code>String</code> que contiene la ip remota desde donde se realiza la operación.
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public synchronized void notificarCatastroSOCP(String as_remoteIp)
	    throws B2BException
	{
		notificarCatastros(
		    as_remoteIp, ConstanteCommon.JOB_NOTIFICAR_CATASTRO_MSOCP_WS_INVOKE,
		    ConstanteCommon.JOB_NOTIFICAR_CATASTRO_MSOCP_BLOQUEO, ConstanteCommon.JOB_NOTIFICAR_CATASTRO_MSOCP_ENDPOINT,
		    ConstanteCommon.JOB_NOTIFICAR_CATASTRO_MSOCP_ROWCOUNT,
		    TipoOperacionCommon.CONSULTA_SEGREGACION_CON_CAMBIO_PROPIETARIO
		);
	}

	/**
	 * Notificar catastro mutacion segundo orden sin cambio propietario.
	 *
	 * @param as_remoteIp Argumento de tipo <code>String</code> que contiene la ip remota desde donde se realiza la operación.
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public synchronized void notificarCatastroSOSP(String as_remoteIp)
	    throws B2BException
	{
		notificarCatastros(
		    as_remoteIp, ConstanteCommon.JOB_NOTIFICAR_CATASTRO_MSOSP_WS_INVOKE,
		    ConstanteCommon.JOB_NOTIFICAR_CATASTRO_MSOSP_BLOQUEO, ConstanteCommon.JOB_NOTIFICAR_CATASTRO_MSOSP_ENDPOINT,
		    ConstanteCommon.JOB_NOTIFICAR_CATASTRO_MSOSP_ROWCOUNT,
		    TipoOperacionCommon.CONSULTA_SEGREGACION_SIN_CAMBIO_PROPIETARIO
		);
	}

	/**
	 * Notificar catastro mutacion tercer orden.
	 *
	 * @param as_remoteIp Argumento de tipo <code>String</code> que contiene la ip remota desde donde se realiza la operación.
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public synchronized void notificarCatastroTO(String as_remoteIp)
	    throws B2BException
	{
		notificarCatastros(
		    as_remoteIp, ConstanteCommon.JOB_NOTIFICAR_CATASTRO_MTO_WS_INVOKE,
		    ConstanteCommon.JOB_NOTIFICAR_CATASTRO_MTO_BLOQUEO, ConstanteCommon.JOB_NOTIFICAR_CATASTRO_MTO_ENDPOINT,
		    ConstanteCommon.JOB_NOTIFICAR_CATASTRO_MTO_ROWCOUNT, TipoOperacionCommon.CONSULTA_CAMBIO_TERCER_ORDEN
		);
	}

	/**
	 * Notificar catastros.
	 *
	 * @param as_remoteIp correspondiente al valor de IP Remota
	 * @param as_wsInvoke correspondiente al valor de invocacion
	 * @param as_wsBloqueo correspondiente al valor de bloqueo
	 * @param as_wsEndpoint correspondiente al valor de endpoint
	 * @param as_wsRowCount correspondiente al valor de limite de registros por consulta a notificar
	 * @param as_tipoOperacion correspondiente al valor de tipo de mutacion a notificar
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	private synchronized void notificarCatastros(
	    String as_remoteIp, String as_wsInvoke, String as_wsBloqueo, String as_wsEndpoint, String as_wsRowCount,
	    String as_tipoOperacion
	)
	    throws B2BException
	{
		boolean                     lb_ejecutar;
		Collection<AnotacionPredio> lcap_predios;
		Collection<String>          lcs_catastros;

		lb_ejecutar       = true;
		lcap_predios      = null;
		lcs_catastros     = obtenerIdsCatastros();

		if(CollectionUtils.isValidCollection(lcs_catastros))
		{
			for(String ls_idCatastro : lcs_catastros)
			{
				if(StringUtils.isValidString(ls_idCatastro))
				{
					while(lb_ejecutar)
					{
						DAOManager ldm_manager;

						ldm_manager = DaoManagerFactory.getDAOManager();

						try
						{
							ConstantesDAO      lcd_DAO;
							AnotacionPredioDAO lapd_DAO;
							Constantes         lc_constant;

							lapd_DAO        = DaoCreator.getAnotacionPredioDAO(ldm_manager);
							lcd_DAO         = DaoCreator.getConstantesDAO(ldm_manager);
							lc_constant     = lcd_DAO.findById(as_wsInvoke);

							if(lc_constant != null)
							{
								if(BooleanUtils.getBooleanValue(lc_constant.getCaracter()))
								{
									long ll_rowcount;

									ll_rowcount = lcd_DAO.findEnteroById(as_wsRowCount);

									if(ll_rowcount > NumericUtils.DEFAULT_LONG_VALUE)
									{
										switch(as_tipoOperacion)
										{
											case TipoOperacionCommon.CONSULTA_CAMBIO_PROPIETARIO:
												lcap_predios = lapd_DAO.buscarUltimaAnotacionCambioPropietarioANotificar(
													    ll_rowcount, ls_idCatastro
													);

												break;

											case TipoOperacionCommon.CONSULTA_SEGREGACION_SIN_CAMBIO_PROPIETARIO:
												lcap_predios = lapd_DAO.findSegregacionAgregacionPredioANotificar(
													    ll_rowcount, false, ls_idCatastro
													);

												break;

											case TipoOperacionCommon.CONSULTA_SEGREGACION_CON_CAMBIO_PROPIETARIO:
												lcap_predios = lapd_DAO.findSegregacionAgregacionPredioANotificar(
													    ll_rowcount, true, ls_idCatastro
													);

												break;

											case TipoOperacionCommon.CONSULTA_CAMBIO_TERCER_ORDEN:

												Constantes         lc_actosConstruccion;
												Collection<String> lcs_codigosActo;

												lcs_codigosActo = null;
												lc_actosConstruccion = lcd_DAO.findById(
													    ConstanteCommon.CODIGOS_ACTOS_CONSTRUCCIONES_DEMOLICIONES
													);

												if(lc_actosConstruccion != null)
													lcs_codigosActo = ListadoCodigosConstantes.generarCodigosCollection(
														    lc_actosConstruccion.getCaracter()
														);

												if(
												    (ll_rowcount > NumericUtils.DEFAULT_LONG_VALUE)
													    && CollectionUtils.isValidCollection(lcs_codigosActo)
												)
													lcap_predios = lapd_DAO.findByMutacionTercerOrdenANotificar(
														    ll_rowcount, lcs_codigosActo, ls_idCatastro
														);

												break;

											default:
												lcap_predios = null;

												break;
										}
									}
								}
								else
									lb_ejecutar = false;
							}
						}
						catch(B2BException lb2be_e)
						{
							ldm_manager.setRollbackOnly();

							clh_LOGGER.error("notificarCatastros", lb2be_e);
						}
						finally
						{
							ldm_manager.close();
						}

						if(CollectionUtils.isValidCollection(lcap_predios))
							notificarCatastro(
							    lcap_predios, ls_idCatastro, as_tipoOperacion, as_wsBloqueo, as_wsEndpoint, as_remoteIp
							);
						else
							lb_ejecutar = false;
					}
				}
			}
		}
	}

	/**
	 * Obtener ids catastros.
	 *
	 * @return el valor de collection Strings
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	private synchronized Collection<String> obtenerIdsCatastros()
	    throws B2BException
	{
		DAOManager         ldm_manager;
		Collection<String> lcs_catastros;

		ldm_manager       = DaoManagerFactory.getDAOManager();
		lcs_catastros     = null;

		try
		{
			lcs_catastros = DaoCreator.getCatastroDAO(ldm_manager).buscarIdsCatastrosActivos();
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("obtenerIdsCatastros", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		if(!CollectionUtils.isValidCollection(lcs_catastros))
			lcs_catastros = null;

		return lcs_catastros;
	}
}
