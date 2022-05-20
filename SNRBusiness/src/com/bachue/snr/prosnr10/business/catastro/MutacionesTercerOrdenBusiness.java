package com.bachue.snr.prosnr10.business.catastro;

import co.gov.supernotariado.www.schemas.bachue.cb.mutacionesTercerOrden.consultaCambioTercerOrden.v1.TipoEntradaConsultaCambioTercerOrden;
import co.gov.supernotariado.www.schemas.bachue.cb.mutacionesTercerOrden.consultaCambioTercerOrden.v1.TipoSalidaConsultaCambioTercerOrden;
import co.gov.supernotariado.www.schemas.bachue.cb.mutacionesTercerOrden.consultaCambioTercerOrden.v1.TipoSalidaConsultaCambioTercerOrdenAnotacionesPredioAnotacionPredio;

import com.b2bsg.common.dataAccess2.DAOManager;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.logging.LoggerHandler;

import com.b2bsg.common.util.NumericUtils;

import com.bachue.snr.prosnr01.common.constants.CodigoMensajeCommon;
import com.bachue.snr.prosnr01.common.constants.ConstanteCommon;
import com.bachue.snr.prosnr01.common.constants.DescripcionMensajeCommon;
import com.bachue.snr.prosnr01.common.constants.IdentificadoresCommon;
import com.bachue.snr.prosnr01.common.constants.ProyectosCommon;
import com.bachue.snr.prosnr01.common.utils.ListadoCodigosConstantes;

import com.bachue.snr.prosnr01.dao.DaoCreator;
import com.bachue.snr.prosnr01.dao.DaoManagerFactory;
import com.bachue.snr.prosnr01.dao.bng.AnotacionPredioDAO;

import com.bachue.snr.prosnr01.model.predio.Predio;
import com.bachue.snr.prosnr01.model.sdb.bng.AnotacionPredio;
import com.bachue.snr.prosnr01.model.sdb.bng.PredioRegistro;
import com.bachue.snr.prosnr01.model.sdb.col.TipoUsoSuelo;
import com.bachue.snr.prosnr01.model.sdb.pgn.Constantes;

import com.bachue.snr.prosnr10.common.constants.ErrorKeys;

import java.math.BigInteger;

import java.util.Calendar;
import java.util.Collection;
import java.util.Map;


/**
 * Clase para el manejo de reglas de negocio del servicio mutaciones tercer orden.
 *
 * @author Manuel Blanco
 */
public class MutacionesTercerOrdenBusiness extends CatastroBusiness
{
	/** Constante clh_LOGGER. */
	private static final LoggerHandler clh_LOGGER = (LoggerHandler)com.bachue.snr.prosnr01.common.utils.LoggingHelper
			.getLogger(MutacionesTercerOrdenBusiness.class, ProyectosCommon.CATASTRO_10);

	/**
	 * Consulta un cambio de tercer orden para un predio.
	 *
	 * @param ateccto_param de ateccto param
	 * @param as_userId Id del usuario que ejecuta la acción
	 * @param as_remoteIp Dirección IP del cliente que ejecuta la acción
	 * @return TipoSalidaConsultaCambioTercerOrden con el resultado de la consulta
	 * @throws B2BException Si ocurre un error en la consulta
	 */
	public synchronized TipoSalidaConsultaCambioTercerOrden consultaCambioTercerOrden(
	    TipoEntradaConsultaCambioTercerOrden ateccto_param, String as_userId, String as_remoteIp
	)
	    throws B2BException
	{
		TipoSalidaConsultaCambioTercerOrden ltsccto_return;
		DAOManager                          ldm_manager;
		BigInteger                          lbi_codigoMensaje;
		String                              ls_descripcionMensaje;

		ltsccto_return            = null;
		ldm_manager               = DaoManagerFactory.getDAOManager();
		lbi_codigoMensaje         = BigInteger.valueOf(CodigoMensajeCommon.CODIGO_200);
		ls_descripcionMensaje     = DescripcionMensajeCommon.EXITO;

		try
		{
			if(ateccto_param == null)
				throw new B2BException(addErrorCTO(ErrorKeys.ERROR_PARAMETROS_NO_VALIDOS));

			Predio         lp_datos;
			PredioRegistro lpr_predioRegistro;

			lp_datos = new Predio(ateccto_param);

			if(validarTipoIdentificacionPredio(lp_datos) && validarNumeroIdentificacionPredio(lp_datos))
			{
				lpr_predioRegistro = consultarInformacionMatricula(
					    lp_datos, ProyectosCommon.CATASTRO_10, ldm_manager, true
					);

				if(lpr_predioRegistro != null)
				{
					if(!validarTerceraMutacionEnPredio(lp_datos, lpr_predioRegistro, ldm_manager))
						throw new B2BException(addErrorCTO(ErrorKeys.ERROR_SIN_INFO_MUTACION));

					ltsccto_return = new TipoSalidaConsultaCambioTercerOrden();

					ltsccto_return.setCodDepartamento(lp_datos.getCodDepartamento());
					ltsccto_return.setCodMunicipio(lp_datos.getCodMunicipio());
					ltsccto_return.setCodCirculoRegistral(lp_datos.getCodCirculoRegistral());
					ltsccto_return.setNumIdentificacionPredio(lpr_predioRegistro.getNumeroPredial());
					ltsccto_return.setTipoPredio(lpr_predioRegistro.getIdTipoPredio());
					ltsccto_return.setDescripcionMensaje("OK");
					ltsccto_return.setCodMensaje(lbi_codigoMensaje);

					{
						TipoSalidaConsultaCambioTercerOrdenAnotacionesPredioAnotacionPredio[] ltapcctoo_anotaciones;

						ltapcctoo_anotaciones     = new TipoSalidaConsultaCambioTercerOrdenAnotacionesPredioAnotacionPredio[1];

						ltapcctoo_anotaciones[0] = new TipoSalidaConsultaCambioTercerOrdenAnotacionesPredioAnotacionPredio(
							    lp_datos.getComentario(), lp_datos.getFechaAnotacion(),
							    lp_datos.getCodNaturalezaJuridica()
							);

						ltsccto_return.setAnotacionesPredio(ltapcctoo_anotaciones);
					}
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();
			clh_LOGGER.error("consultaCambioTercerOrden", lb2be_e);

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
			clh_LOGGER.error("consultaCambioTercerOrden", le_e);

			lbi_codigoMensaje         = BigInteger.valueOf(CodigoMensajeCommon.CODIGO_500);
			ls_descripcionMensaje     = le_e.getMessage();
		}
		finally
		{
			ldm_manager.close();
		}

		if(ltsccto_return == null)
		{
			TipoSalidaConsultaCambioTercerOrdenAnotacionesPredioAnotacionPredio[] ltapcctoo_anotaciones;

			ltapcctoo_anotaciones     = new TipoSalidaConsultaCambioTercerOrdenAnotacionesPredioAnotacionPredio[1];

			ltapcctoo_anotaciones[0]     = new TipoSalidaConsultaCambioTercerOrdenAnotacionesPredioAnotacionPredio(
				    "", Calendar.getInstance(), ""
				);
			ltsccto_return               = new TipoSalidaConsultaCambioTercerOrden(
				    "", "", "", "", "", ltapcctoo_anotaciones, lbi_codigoMensaje, ls_descripcionMensaje
				);
		}

		return ltsccto_return;
	}

	/**
	 * Verifica si un predio cumple con las reglas de la mutación de tercer orden.
	 *
	 * @param ac_datos Objeto contenedor de la información recibida para la consulta
	 * @param apr_predio Objeto contenedor de la información del predio
	 * @param adm_manager Conexión a la base de datos
	 * @return true si la validación fue exitosa
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	private synchronized boolean validarTerceraMutacionEnPredio(
	    Predio ac_datos, PredioRegistro apr_predio, DAOManager adm_manager
	)
	    throws B2BException
	{
		boolean lb_return;

		lb_return = false;

		if((ac_datos != null) && (apr_predio != null))
		{
			AnotacionPredioDAO lapd_anotacionPredioDAO;
			String             ls_idCirculo;
			Long               ll_idMatricula;
			Long               ll_maxIdAnotacion;

			lapd_anotacionPredioDAO     = DaoCreator.getAnotacionPredioDAO(adm_manager);
			ls_idCirculo                = apr_predio.getIdCirculo();
			ll_idMatricula              = NumericUtils.getLongWrapper(apr_predio.getIdMatricula());

			{
				Constantes         lc_actosConstruccion;
				Collection<String> lcs_codigosActo;

				lc_actosConstruccion     = DaoCreator.getConstantesDAO(adm_manager)
						                                 .findById(
						    ConstanteCommon.CODIGOS_ACTOS_CONSTRUCCIONES_DEMOLICIONES
						);
				lcs_codigosActo          = null;

				if(lc_actosConstruccion != null)
					lcs_codigosActo = ListadoCodigosConstantes.generarCodigosCollection(
						    lc_actosConstruccion.getCaracter()
						);

				ll_maxIdAnotacion = lapd_anotacionPredioDAO.findByMutacionTercerOrden(
					    ls_idCirculo, ll_idMatricula, lcs_codigosActo
					);
			}

			if(NumericUtils.isValidLong(ll_maxIdAnotacion))
			{
				lb_return = true;

				obtenerInfoZonaRegistralPredio(ac_datos, apr_predio.getIdZonaRegistral(), adm_manager);

				AnotacionPredio lap_anotacion;

				lap_anotacion = lapd_anotacionPredioDAO.findById(ls_idCirculo, ll_idMatricula, ll_maxIdAnotacion);

				if(lap_anotacion != null)
				{
					ac_datos.setComentario(lap_anotacion.getComentario());
					ac_datos.setFechaAnotacion(obtenerCalendarDesdeDate(lap_anotacion.getFechaCreacion()));
					ac_datos.setCodNaturalezaJuridica(lap_anotacion.getIdNaturalezaJuridica());

					TipoUsoSuelo ltus_tipoUsoSuelo;

					ltus_tipoUsoSuelo = DaoCreator.getTipoUsoSueloDAO(adm_manager)
							                          .findById(apr_predio.getIdTipoUsoSuelo());

					if(ltus_tipoUsoSuelo != null)
						ac_datos.setTipoConstruccion(ltus_tipoUsoSuelo.getDescription());
				}
			}
		}

		return lb_return;
	}
}
