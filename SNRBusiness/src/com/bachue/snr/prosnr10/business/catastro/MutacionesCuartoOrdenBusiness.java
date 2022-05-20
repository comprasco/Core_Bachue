package com.bachue.snr.prosnr10.business.catastro;

import co.gov.supernotariado.www.schemas.bachue.ee.mutacionesCuartoOrden.registrarCambioCuartoOrden.v1.TipoEntradaRegistrarCambioCuartoOrden;
import co.gov.supernotariado.www.schemas.bachue.ee.mutacionesCuartoOrden.registrarCambioCuartoOrden.v1.TipoSalidaRegistrarCambioCuartoOrden;

import com.b2bsg.common.dataAccess2.DAOManager;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.logging.LoggerHandler;

import com.b2bsg.common.util.NumericUtils;
import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.common.constants.CodigoMensajeCommon;
import com.bachue.snr.prosnr01.common.constants.DescripcionMensajeCommon;
import com.bachue.snr.prosnr01.common.constants.IdentificadoresCommon;
import com.bachue.snr.prosnr01.common.constants.ProyectosCommon;

import com.bachue.snr.prosnr01.dao.DaoCreator;
import com.bachue.snr.prosnr01.dao.DaoManagerFactory;

import com.bachue.snr.prosnr01.model.predio.Predio;
import com.bachue.snr.prosnr01.model.sdb.bng.PredioRegistro;

import com.bachue.snr.prosnr10.common.constants.ErrorKeys;

import java.math.BigDecimal;
import java.math.BigInteger;

import java.time.LocalDateTime;

import java.util.Map;


/**
 * Clase para el manejo de reglas de negocio del servicio mutaciones tercer orden.
 *
 * @author Carlos Calderon
 */
public class MutacionesCuartoOrdenBusiness extends CatastroBusiness
{
	/** Constante clh_LOGGER. */
	private static final LoggerHandler clh_LOGGER = (LoggerHandler)com.bachue.snr.prosnr01.common.utils.LoggingHelper
			.getLogger(MutacionesCuartoOrdenBusiness.class, ProyectosCommon.CATASTRO_10);

	/**
	 * Permite informar a Bachué los cambios que ocurren como consecuencia de la inscripción de predios o mejoras por
	 * edificaciones no declarados u omitidos durante la formación catastral o la actualización de la formación del catastro.
	 *
	 * @param atercco_param de atercco param
	 * @param as_userId de as user id
	 * @param as_remoteIp de as remote ip
	 * @return de tipo salida registrar cambio cuarto orden
	 * @throws B2BException de b 2 B exception
	 */
	public synchronized TipoSalidaRegistrarCambioCuartoOrden registraCambioCuartoOrden(
	    TipoEntradaRegistrarCambioCuartoOrden atercco_param, String as_userId, String as_remoteIp
	)
	    throws B2BException
	{
		DAOManager ldm_manager;
		BigInteger lbi_codigoMensaje;
		String     ls_descripcionMensaje;

		ldm_manager               = DaoManagerFactory.getDAOManager();
		lbi_codigoMensaje         = BigInteger.valueOf(CodigoMensajeCommon.CODIGO_200);
		ls_descripcionMensaje     = DescripcionMensajeCommon.EXITO;

		try
		{
			if(atercco_param != null)
			{
				Predio lp_datos;

				lp_datos = new Predio(atercco_param);

				if(validarTipoIdentificacionPredio(lp_datos) && validarNumeroIdentificacionPredio(lp_datos))
				{
					PredioRegistro lpr_predioRegistro;

					lpr_predioRegistro = consultarInformacionMatricula(lp_datos, ldm_manager);

					if((lpr_predioRegistro != null))
					{
						{
							String ls_codDepartamento;
							String ls_codMunicipio;

							ls_codDepartamento     = atercco_param.getCodDepartamento();
							ls_codMunicipio        = atercco_param.getCodMunicipio();

							if(
							    !StringUtils.isValidString(ls_codDepartamento)
								    && StringUtils.isValidString(ls_codMunicipio)
							)
								throw new B2BException(
								    addErrorCTO(ErrorKeys.ERROR_CODIGO_DEPARTAMENTO_NO_VALIDO_CODIGO)
								);

							if(
							    StringUtils.isValidString(ls_codDepartamento)
								    && !StringUtils.isValidString(ls_codMunicipio)
							)
								throw new B2BException(addErrorCTO(ErrorKeys.ERROR_CODIGO_MUNICIPIO_NO_VALIDO_CODIGO));

							if(
							    StringUtils.isValidString(ls_codDepartamento)
								    && StringUtils.isValidString(ls_codMunicipio)
							)
							{
								validarPaisDepMun(
								    IdentificadoresCommon.INDICATIVO_PAIS_DEFAULT, ls_codDepartamento, ls_codMunicipio,
								    true, true, true, ProyectosCommon.CATASTRO_10, ldm_manager
								);

								obtenerInfoZonaRegistralPredio(
								    lp_datos, lpr_predioRegistro.getIdZonaRegistral(), ldm_manager
								);

								if(
								    !(ls_codDepartamento.equalsIgnoreCase(lp_datos.getCodDepartamento())
									    && ls_codMunicipio.equalsIgnoreCase(lp_datos.getCodMunicipio()))
								)
									throw new B2BException(
									    addErrorCTO(ErrorKeys.ERROR_MATRICULA_SIN_RELACION_DEP_MUN_INGRESADOS)
									);
							}
						}

						{
							LocalDateTime lldt_fechaAvaluo;

							lldt_fechaAvaluo = obtenerLocalDateTime(atercco_param.getFechaAvaluo());

							if(lldt_fechaAvaluo == null)
								throw new B2BException(addErrorCTO(ErrorKeys.ERROR_FECHA_NO_VALIDA_CODIGO));

							lpr_predioRegistro.setFechaAvaluoService(lldt_fechaAvaluo);
						}

						lpr_predioRegistro.setValorAvaluo(validarAvaluo(atercco_param));
						lpr_predioRegistro.setIdUsuarioModificacion(as_userId);
						lpr_predioRegistro.setIpModificacion(as_remoteIp);

						DaoCreator.getPredioRegistroDAO(ldm_manager).updateCuartoOrden(lpr_predioRegistro);
					}
				}
			}
			else
				throw new B2BException(addErrorCTO(ErrorKeys.ERROR_PARAMETROS_NO_VALIDOS_CODIGO));
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();
			clh_LOGGER.error("registrarCambioCuartoOrden", lb2be_e);

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
			clh_LOGGER.error("registrarCambioCuartoOrden", le_e);

			lbi_codigoMensaje         = BigInteger.valueOf(CodigoMensajeCommon.CODIGO_500);
			ls_descripcionMensaje     = le_e.getMessage();
		}
		finally
		{
			ldm_manager.close();
		}

		{
			TipoSalidaRegistrarCambioCuartoOrden ltsrcco_return;

			ltsrcco_return = new TipoSalidaRegistrarCambioCuartoOrden(
				    lbi_codigoMensaje.toString(), ls_descripcionMensaje
				);

			return ltsrcco_return;
		}
	}

	/**
	 * Validar avaluo.
	 *
	 * @param atercco_param de atercco param
	 * @return el valor de string
	 * @throws B2BException de b 2 B exception
	 */
	private synchronized BigDecimal validarAvaluo(TipoEntradaRegistrarCambioCuartoOrden atercco_param)
	    throws B2BException
	{
		BigDecimal lbd_valorAvaluo;

		lbd_valorAvaluo = null;

		try
		{
			if(atercco_param != null)
			{
				String ls_valorAvaluo;

				ls_valorAvaluo = atercco_param.getAvaluoPredio();

				if(StringUtils.isValidString(ls_valorAvaluo))
				{
					if(ls_valorAvaluo.length() <= 20)
					{
						BigDecimal lbd_test;

						lbd_test = NumericUtils.getBigDecimal(ls_valorAvaluo);

						if(!NumericUtils.isValidBigDecimal(lbd_test, BigDecimal.valueOf(0.1D)))
							throw new B2BException(addErrorCTO(ErrorKeys.ERROR_AVALUO_MAYOR_A_0_CODIGO));
						else
							lbd_valorAvaluo = lbd_test;
					}
					else
						throw new B2BException(addErrorCTO(ErrorKeys.ERROR_AVALUO_NO_VALIDO_CODIGO));
				}
				else
					throw new B2BException(addErrorCTO(ErrorKeys.ERROR_AVALUO_NO_VALIDO_CODIGO));
			}
			else
				throw new B2BException(addErrorCTO(ErrorKeys.ERROR_PARAMETROS_NO_VALIDOS_CODIGO));
		}
		catch(B2BException lb2be_e)
		{
			lbd_valorAvaluo = null;
			clh_LOGGER.error("validarAvaluo", lb2be_e);
			throw lb2be_e;
		}

		return lbd_valorAvaluo;
	}
}
