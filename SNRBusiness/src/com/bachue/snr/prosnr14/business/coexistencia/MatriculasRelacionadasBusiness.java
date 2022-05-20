package com.bachue.snr.prosnr14.business.coexistencia;

import co.gov.supernotariado.www.services.bachue.cb.matriculasrelacionadas.consultarMatriculasDerivadas.v1.TipoEntradaDatosMatriculasDerivadas;
import co.gov.supernotariado.www.services.bachue.cb.matriculasrelacionadas.consultarMatriculasDerivadas.v1.TipoMatriculaDerivadas;
import co.gov.supernotariado.www.services.bachue.cb.matriculasrelacionadas.consultarMatriculasDerivadas.v1.TipoSalidaDatosMatriculasDerivadas;
import co.gov.supernotariado.www.services.bachue.cb.matriculasrelacionadas.consultarMatriculasMatriz.v1.TipoEntradaDatosMatriculasMatriz;
import co.gov.supernotariado.www.services.bachue.cb.matriculasrelacionadas.consultarMatriculasMatriz.v1.TipoMatriculaMatriz;
import co.gov.supernotariado.www.services.bachue.cb.matriculasrelacionadas.consultarMatriculasMatriz.v1.TipoSalidaDatosMatriculasMatriz;

import com.b2bsg.common.dataAccess2.DAOManager;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.logging.LoggerHandler;

import com.b2bsg.common.util.CollectionUtils;
import com.b2bsg.common.util.NumericUtils;
import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.common.constants.CodigoMensajeCommon;
import com.bachue.snr.prosnr01.common.constants.IdentificadoresCommon;
import com.bachue.snr.prosnr01.common.constants.ProyectosCommon;

import com.bachue.snr.prosnr01.dao.DaoCreator;
import com.bachue.snr.prosnr01.dao.DaoManagerFactory;

import com.bachue.snr.prosnr01.model.predio.Predio;
import com.bachue.snr.prosnr01.model.sdb.bng.PredioRegistro;
import com.bachue.snr.prosnr01.model.sdb.bng.PredioSegregado;

import com.bachue.snr.prosnr14.common.constants.ErrorKeys;

import com.bachue.snr.prosnr14.model.coexistencia.CoexistenciaResponse;

import java.math.BigInteger;

import java.util.Collection;
import java.util.Map;


/**
 * Clase que contiene todos las propiedades MatriculasRelacionadasBusiness.
 *
 * @author  Carlos Calderón
 * Fecha de Creacion: 9/03/2020
 */
public class MatriculasRelacionadasBusiness extends CoexistenciaBusiness
{
	/** Constante clh_LOGGER. */
	private static final LoggerHandler clh_LOGGER = (LoggerHandler)com.bachue.snr.prosnr01.common.utils.LoggingHelper
			.getLogger(MatriculasRelacionadasBusiness.class, ProyectosCommon.COEXISTENCIA_14);

	/**
	 * Consultar matriculas matriz.
	 *
	 * @param atedmm_param de atedmm param
	 * @param as_userId de as user id
	 * @param as_remoteIp de as remote ip
	 * @return el valor de tipo salida datos matriculas matriz
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public synchronized TipoSalidaDatosMatriculasMatriz consultarMatriculasMatriz(
	    TipoEntradaDatosMatriculasMatriz atedmm_param, String as_userId, String as_remoteIp
	)
	    throws B2BException
	{
		TipoSalidaDatosMatriculasMatriz ltsdmm_return;
		DAOManager                      ldm_manager;
		BigInteger                      lbi_codigoMensaje;
		String                          ls_descripcionMensaje;

		ltsdmm_return             = null;
		ldm_manager               = DaoManagerFactory.getDAOManager();
		lbi_codigoMensaje         = BigInteger.valueOf(CodigoMensajeCommon.CODIGO_200);
		ls_descripcionMensaje     = null;

		try
		{
			if(atedmm_param == null)
				throw new B2BException(addErrorCX(ErrorKeys.ERROR_OPERACION));

			Predio lp_datos;

			lp_datos = new Predio(atedmm_param);

			if(
			    validarTipoIdentificacionPredio(lp_datos, ProyectosCommon.COEXISTENCIA_14)
				    && validarNumeroIdentificacionPredio(lp_datos, ProyectosCommon.COEXISTENCIA_14)
			)
			{
				if(lp_datos.isFolioMatricula())
					validarMatriculaCoexistenciaNumerica(lp_datos.getNumeroIdentificacionPredio());

				{
					PredioRegistro lpr_predioRegistro;

					lpr_predioRegistro = consultarInformacionMatricula(
						    lp_datos, ProyectosCommon.COEXISTENCIA_14, ldm_manager
						);

					if(lpr_predioRegistro != null)
					{
						Collection<PredioSegregado> lcps_cllPredioSegregado;

						lcps_cllPredioSegregado = DaoCreator.getPredioSegregadoDAO(ldm_manager)
								                                .findAllByCirculo1Matricula1(
								    lpr_predioRegistro.getIdCirculo(),
								    NumericUtils.getLongWrapper(lpr_predioRegistro.getIdMatricula())
								);

						if(CollectionUtils.isValidCollection(lcps_cllPredioSegregado))
						{
							CoexistenciaResponse        lcr_response;
							Collection<PredioSegregado> lcps_cllFiltrada;
							String                      ls_mensajeConvenioCirculos;

							{
								lcr_response = new CoexistenciaResponse(lp_datos);

								lcr_response.setCllPredioSegregado(lcps_cllPredioSegregado);

								lcr_response     = filtrarConsulta(
									    lcr_response,
									    com.bachue.snr.prosnr14.common.constants.IdentificadoresCommon.PREDIO_SEGREGADO,
									    ldm_manager
									);

								lcps_cllFiltrada               = lcr_response.getCllPredioSegregado();
								ls_mensajeConvenioCirculos     = lcr_response.getMensajeConvenioCirculos();
							}

							if(CollectionUtils.isValidCollection(lcps_cllFiltrada))
							{
								TipoMatriculaMatriz[] ltmm_tipoMatriculaRaiz;
								int                   li_count;

								ltmm_tipoMatriculaRaiz     = new TipoMatriculaMatriz[lcps_cllFiltrada.size()];
								li_count                   = 0;

								for(PredioSegregado lps_tmp : lcps_cllFiltrada)
								{
									if(lps_tmp != null)
									{
										ltmm_tipoMatriculaRaiz[li_count] = new TipoMatriculaMatriz(
											    StringUtils.getString(lps_tmp.getIdMatricula()), lps_tmp.getIdCirculo()
											);

										li_count++;
									}
								}

								{
									String     ls_mensajeFinal;
									BigInteger lbi_codigoFinal;

									ls_mensajeFinal     = StringUtils.isValidString(ls_mensajeConvenioCirculos)
										? ls_mensajeConvenioCirculos : "OK";
									lbi_codigoFinal     = StringUtils.isValidString(ls_mensajeConvenioCirculos)
										? BigInteger.valueOf(CodigoMensajeCommon.CODIGO_201) : lbi_codigoMensaje;

									ltsdmm_return = new TipoSalidaDatosMatriculasMatriz(
										    ltmm_tipoMatriculaRaiz, lbi_codigoFinal, ls_mensajeFinal
										);
								}
							}
							else
								throw new B2BException(addErrorCX(ErrorKeys.ERROR_CONSULTA_RESULTADOS));
						}
						else
							throw new B2BException(addErrorCX(ErrorKeys.ERROR_CONSULTA_RESULTADOS));
					}
					else
						throw new B2BException(addErrorCX(ErrorKeys.ERROR_CONSULTA_RESULTADOS));
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();
			clh_LOGGER.error("consultarMatriculasMatriz", lb2be_e);

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
			clh_LOGGER.error("consultarMatriculasMatriz", le_e);

			lbi_codigoMensaje         = BigInteger.valueOf(CodigoMensajeCommon.CODIGO_500);
			ls_descripcionMensaje     = le_e.getMessage();
		}
		finally
		{
			ldm_manager.close();
		}

		if(ltsdmm_return == null)
		{
			TipoMatriculaMatriz[] ltmm_tipoMatriculaRaiz;

			ltmm_tipoMatriculaRaiz     = new TipoMatriculaMatriz[1];

			ltmm_tipoMatriculaRaiz[0]     = new TipoMatriculaMatriz("", "");

			ltsdmm_return = new TipoSalidaDatosMatriculasMatriz(
				    ltmm_tipoMatriculaRaiz, lbi_codigoMensaje, ls_descripcionMensaje
				);
		}

		return ltsdmm_return;
	}

	/**
	 * Consultar matriculas derivadas.
	 *
	 * @param atedmd_param de atedmm param
	 * @param as_userId de as user id
	 * @param as_remoteIp de as remote ip
	 * @return el valor de tipo salida datos matriculas matriz
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public synchronized TipoSalidaDatosMatriculasDerivadas consultarMatriculasDerivadas(
	    TipoEntradaDatosMatriculasDerivadas atedmd_param, String as_userId, String as_remoteIp
	)
	    throws B2BException
	{
		TipoSalidaDatosMatriculasDerivadas ltsdmd_return;
		DAOManager                         ldm_manager;
		BigInteger                         lbi_codigoMensaje;
		String                             ls_descripcionMensaje;

		ltsdmd_return             = null;
		ldm_manager               = DaoManagerFactory.getDAOManager();
		lbi_codigoMensaje         = BigInteger.valueOf(CodigoMensajeCommon.CODIGO_200);
		ls_descripcionMensaje     = null;

		try
		{
			if(atedmd_param == null)
				throw new B2BException(addErrorCX(ErrorKeys.ERROR_OPERACION));

			Predio lp_datos;

			lp_datos = new Predio(atedmd_param);

			if(
			    validarTipoIdentificacionPredio(lp_datos, ProyectosCommon.COEXISTENCIA_14)
				    && validarNumeroIdentificacionPredio(lp_datos, ProyectosCommon.COEXISTENCIA_14)
			)
			{
				if(lp_datos.isFolioMatricula())
					validarMatriculaCoexistenciaNumerica(lp_datos.getNumeroIdentificacionPredio());

				{
					PredioRegistro lpr_predioRegistro;

					lpr_predioRegistro = consultarInformacionMatricula(
						    lp_datos, ProyectosCommon.COEXISTENCIA_14, ldm_manager
						);

					if(lpr_predioRegistro != null)
					{
						String ls_nir;

						{
							ls_nir = DaoCreator.getSolicitudDAO(ldm_manager)
									               .findNirByIdTurno(lpr_predioRegistro.getRadicacion());

							if(!StringUtils.isValidString(ls_nir))
								ls_nir = "";
						}

						Collection<PredioSegregado> lcps_cllPredioSegregado;

						lcps_cllPredioSegregado = DaoCreator.getPredioSegregadoDAO(ldm_manager)
								                                .findAllByCirculoMatricula(
								    lpr_predioRegistro.getIdCirculo(), lpr_predioRegistro.getIdMatricula()
								);

						if(CollectionUtils.isValidCollection(lcps_cllPredioSegregado))
						{
							CoexistenciaResponse        lcr_response;
							Collection<PredioSegregado> lcps_cllFiltrada;
							String                      ls_mensajeConvenioCirculos;

							{
								lcr_response = new CoexistenciaResponse(lp_datos);

								lcr_response.setCllPredioSegregado(lcps_cllPredioSegregado);

								lcr_response     = filtrarConsulta(
									    lcr_response,
									    com.bachue.snr.prosnr14.common.constants.IdentificadoresCommon.PREDIO_SEGREGADO,
									    ldm_manager
									);

								lcps_cllFiltrada               = lcr_response.getCllPredioSegregado();
								ls_mensajeConvenioCirculos     = lcr_response.getMensajeConvenioCirculos();
							}

							if(CollectionUtils.isValidCollection(lcps_cllFiltrada))
							{
								TipoMatriculaDerivadas[] ltmd_tipoMatriculaDerivada;
								int                      li_count;

								ltmd_tipoMatriculaDerivada     = new TipoMatriculaDerivadas[lcps_cllFiltrada.size()];
								li_count                       = 0;

								for(PredioSegregado lps_tmp : lcps_cllFiltrada)
								{
									if(lps_tmp != null)
									{
										String ls_idCirculo;
										long   ll_idMatricula;
										String ls_direccion;
										Long   ll_idMatricula1;

										ll_idMatricula1     = lps_tmp.getIdMatricula1();

										ls_idCirculo = lps_tmp.getIdCirculo1();

										if(NumericUtils.isValidLong(ll_idMatricula1))
										{
											ll_idMatricula     = NumericUtils.getLong(ll_idMatricula1);
											ls_direccion       = consultarDireccionCirculoMatricula(
												    ls_idCirculo, String.valueOf(ll_idMatricula), ldm_manager
												);

											ltmd_tipoMatriculaDerivada[li_count] = new TipoMatriculaDerivadas(
												    StringUtils.getString(lps_tmp.getIdMatricula1()),
												    lps_tmp.getIdCirculo1(), ls_direccion, ls_nir,
												    lpr_predioRegistro.getRadicacion(),
												    String.valueOf(lps_tmp.getIdAnotacion1())
												);

											li_count++;
										}
									}
								}

								{
									String     ls_mensajeFinal;
									BigInteger lbi_codigoFinal;

									ls_mensajeFinal     = StringUtils.isValidString(ls_mensajeConvenioCirculos)
										? ls_mensajeConvenioCirculos : "OK";
									lbi_codigoFinal     = StringUtils.isValidString(ls_mensajeConvenioCirculos)
										? BigInteger.valueOf(201) : lbi_codigoMensaje;

									ltsdmd_return = new TipoSalidaDatosMatriculasDerivadas(
										    ltmd_tipoMatriculaDerivada, lbi_codigoFinal, ls_mensajeFinal
										);
								}
							}
							else
								throw new B2BException(addErrorCX(ErrorKeys.ERROR_CONSULTA_RESULTADOS));
						}
						else
							throw new B2BException(addErrorCX(ErrorKeys.ERROR_CONSULTA_RESULTADOS));
					}
					else
						throw new B2BException(addErrorCX(ErrorKeys.ERROR_CONSULTA_RESULTADOS));
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();
			clh_LOGGER.error("consultarMatriculasDerivadas", lb2be_e);

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
			clh_LOGGER.error("consultarMatriculasDerivadas", le_e);

			lbi_codigoMensaje         = BigInteger.valueOf(CodigoMensajeCommon.CODIGO_500);
			ls_descripcionMensaje     = le_e.getMessage();
		}
		finally
		{
			ldm_manager.close();
		}

		if(ltsdmd_return == null)
		{
			TipoMatriculaDerivadas[] ltmd_tipoMatriculaDerivada;

			ltmd_tipoMatriculaDerivada     = new TipoMatriculaDerivadas[1];

			ltmd_tipoMatriculaDerivada[0]     = new TipoMatriculaDerivadas("", "", "", "", "", "");

			ltsdmd_return = new TipoSalidaDatosMatriculasDerivadas(
				    ltmd_tipoMatriculaDerivada, lbi_codigoMensaje, ls_descripcionMensaje
				);
		}

		return ltsdmd_return;
	}
}
