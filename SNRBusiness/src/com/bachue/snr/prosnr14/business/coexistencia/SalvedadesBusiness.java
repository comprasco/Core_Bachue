package com.bachue.snr.prosnr14.business.coexistencia;

import co.gov.supernotariado.www.services.bachue.cb.salvedades.consultarSalvedades.v1.TipoEntradaSalvedades;
import co.gov.supernotariado.www.services.bachue.cb.salvedades.consultarSalvedades.v1.TipoSalidaSalvedades;
import co.gov.supernotariado.www.services.bachue.cb.salvedades.consultarSalvedades.v1.TipoSalvedad;

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
import com.bachue.snr.prosnr01.model.sdb.bng.SalvedadAnotacion;

import com.bachue.snr.prosnr14.common.constants.ErrorKeys;

import com.bachue.snr.prosnr14.model.coexistencia.CoexistenciaResponse;

import java.math.BigInteger;

import java.util.Collection;
import java.util.Map;


/**
 * Clase que contiene todos las propiedades SalvedadesBusiness.
 *
 * @author  Carlos Calderón
 * Fecha de Creacion: 11/03/2020
 */
public class SalvedadesBusiness extends CoexistenciaBusiness
{
	/** Constante clh_LOGGER. */
	private static final LoggerHandler clh_LOGGER = (LoggerHandler)com.bachue.snr.prosnr01.common.utils.LoggingHelper
			.getLogger(SalvedadesBusiness.class, ProyectosCommon.COEXISTENCIA_14);

	/**
	 * Consultar salvedades.
	 *
	 * @param ates_entrada de ates entrada
	 * @param as_userId de as user id
	 * @param as_remoteIp de as remote ip
	 * @return el valor de tipo salida salvedades
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public synchronized TipoSalidaSalvedades consultarSalvedades(
	    TipoEntradaSalvedades ates_entrada, String as_userId, String as_remoteIp
	)
	    throws B2BException
	{
		TipoSalidaSalvedades ltss_salida;
		DAOManager           ldm_manager;
		BigInteger           lbi_codigoMensaje;
		String               ls_descripcionMensaje;

		ltss_salida               = null;
		ldm_manager               = DaoManagerFactory.getDAOManager();
		lbi_codigoMensaje         = BigInteger.valueOf(CodigoMensajeCommon.CODIGO_200);
		ls_descripcionMensaje     = null;

		try
		{
			if(ates_entrada == null)
				throw new B2BException(addErrorCX(ErrorKeys.ERROR_OPERACION));

			Predio lp_datos;

			lp_datos = new Predio(ates_entrada);

			if(
			    validarTipoIdentificacionPredio(lp_datos, ProyectosCommon.COEXISTENCIA_14)
				    && validarNumeroIdentificacionPredio(lp_datos, ProyectosCommon.COEXISTENCIA_14)
			)
			{
				if(lp_datos.isFolioMatricula())
					validarMatriculaCoexistenciaNumerica(lp_datos.getNumeroIdentificacionPredio());

				CoexistenciaResponse lcr_response;

				lcr_response = new CoexistenciaResponse(lp_datos);

				filtrarConsulta(
				    lcr_response, com.bachue.snr.prosnr14.common.constants.IdentificadoresCommon.VALIDACION_UNICA,
				    ldm_manager
				);

				{
					PredioRegistro lpr_predioRegistro;

					lpr_predioRegistro = consultarInformacionMatricula(
						    lp_datos, ProyectosCommon.COEXISTENCIA_14, ldm_manager
						);

					if(lpr_predioRegistro != null)
					{
						Collection<SalvedadAnotacion> lcsa_cllSalvedadAnotacion;

						lcsa_cllSalvedadAnotacion = DaoCreator.getSalvedadAnotacionDAO(ldm_manager)
								                                  .findByCirculoMatricula(
								    lpr_predioRegistro.getIdCirculo(),
								    NumericUtils.getLongWrapper(lpr_predioRegistro.getIdMatricula())
								);

						if(CollectionUtils.isValidCollection(lcsa_cllSalvedadAnotacion))
						{
							String ls_nir;

							{
								ls_nir = DaoCreator.getSolicitudDAO(ldm_manager)
										               .findNirByIdTurno(lpr_predioRegistro.getRadicacion());

								if(!StringUtils.isValidString(ls_nir))
									ls_nir = "";
							}

							TipoSalvedad[] lts_tipoSalvedad;
							int            li_count;

							lts_tipoSalvedad     = new TipoSalvedad[lcsa_cllSalvedadAnotacion.size()];
							li_count             = 0;

							for(SalvedadAnotacion lsa_tmp : lcsa_cllSalvedadAnotacion)
							{
								if(lsa_tmp != null)
								{
									lts_tipoSalvedad[li_count] = new TipoSalvedad(
										    String.valueOf(lsa_tmp.getIdAnotacion()),
										    String.valueOf(lsa_tmp.getIdSalvedadAnotacion()), ls_nir,
										    lsa_tmp.getRadicacion(),
										    obtenerCalendarDesdeDate(lsa_tmp.getFechaRegistro()),
										    lsa_tmp.getDescripcion()
										);

									li_count++;
								}
							}

							ltss_salida = new TipoSalidaSalvedades(lts_tipoSalvedad, lbi_codigoMensaje, "OK");
						}
						else
							throw new B2BException(addErrorCX(ErrorKeys.ERROR_CONSULTA_RESULTADOS));
					}
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();
			clh_LOGGER.error("consultarSalvedades", lb2be_e);

			Map<String, String> lmss_codigoMensaje;

			lmss_codigoMensaje     = obtenerErrorServicios(lb2be_e.getMessageKey());

			lbi_codigoMensaje         = NumericUtils.getBigInteger(
				    lmss_codigoMensaje.get(IdentificadoresCommon.CODIGO_MENSAJE)
				);
			ls_descripcionMensaje     = lmss_codigoMensaje.get(IdentificadoresCommon.DESCRIPCION_MENSAJE);

			ltss_salida = null;
		}
		catch(Exception le_e)
		{
			ldm_manager.setRollbackOnly();
			clh_LOGGER.error("consultarSalvedades", le_e);

			lbi_codigoMensaje         = BigInteger.valueOf(CodigoMensajeCommon.CODIGO_500);
			ls_descripcionMensaje     = le_e.getMessage();

			ltss_salida = null;
		}
		finally
		{
			ldm_manager.close();
		}

		if(ltss_salida == null)
			ltss_salida = new TipoSalidaSalvedades(null, lbi_codigoMensaje, ls_descripcionMensaje);

		return ltss_salida;
	}
}
