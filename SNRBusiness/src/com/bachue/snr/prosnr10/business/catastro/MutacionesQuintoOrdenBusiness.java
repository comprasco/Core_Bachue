package com.bachue.snr.prosnr10.business.catastro;

import co.gov.supernotariado.www.schemas.bachue.cb.mutacionesQuintoOrden.registrarCambioQuintoOrden.v1.TipoEntradaRegistrarCambioQuintoOrden;
import co.gov.supernotariado.www.schemas.bachue.cb.mutacionesQuintoOrden.registrarCambioQuintoOrden.v1.TipoEntradaRegistrarCambioQuintoOrdenPredio;
import co.gov.supernotariado.www.schemas.bachue.cb.mutacionesQuintoOrden.registrarCambioQuintoOrden.v1.TipoSalidaRegistrarCambioQuintoOrden;

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

import java.math.BigInteger;

import java.util.Map;


/**
 * Clase para el manejo de reglas de negocio del servicio mutaciones quinto orden.
 *
 * @author Carlos Calderon
 */
public class MutacionesQuintoOrdenBusiness extends CatastroBusiness
{
	/** Constante clh_LOGGER. */
	private static final LoggerHandler clh_LOGGER = (LoggerHandler)com.bachue.snr.prosnr01.common.utils.LoggingHelper
			.getLogger(MutacionesQuintoOrdenBusiness.class, ProyectosCommon.CATASTRO_10);

	/**
	 * Permite informar a Bachué los cambios que ocurren como consecuencia de la inscripción de predios o mejoras por
	 * edificaciones no declarados u omitidos durante la formación catastral o la actualización de la formación del catastro.
	 *
	 * @param atercqo_param de atercqo param
	 * @param as_userId de as user id
	 * @param as_remoteIp de as remote ip
	 * @return el valor de tipo salida registrar cambio quinto orden
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public synchronized TipoSalidaRegistrarCambioQuintoOrden registraCambioQuintoOrden(
	    TipoEntradaRegistrarCambioQuintoOrden atercqo_param, String as_userId, String as_remoteIp
	)
	    throws B2BException
	{
		DAOManager                           ldm_manager;
		BigInteger                           lbi_codigoMensaje;
		String                               ls_descripcionMensaje;
		PredioRegistro                       lpr_predioRegistro;
		TipoSalidaRegistrarCambioQuintoOrden ltsrcqo_return;

		ldm_manager               = DaoManagerFactory.getDAOManager();
		lbi_codigoMensaje         = BigInteger.valueOf(CodigoMensajeCommon.CODIGO_200);
		ls_descripcionMensaje     = DescripcionMensajeCommon.EXITO;
		lpr_predioRegistro        = null;
		ltsrcqo_return            = new TipoSalidaRegistrarCambioQuintoOrden();

		try
		{
			if(atercqo_param != null)
			{
				Predio lp_datos;

				lp_datos = new Predio(atercqo_param);

				if(validarTipoIdentificacionPredio(lp_datos) && validarNumeroIdentificacionPredio(lp_datos))
				{
					lpr_predioRegistro = consultarInformacionMatricula(
						    lp_datos, ProyectosCommon.CATASTRO_10, ldm_manager, true
						);

					if((lpr_predioRegistro != null))
					{
						String ls_codDepartamento;
						String ls_codMunicipio;

						ls_codDepartamento     = atercqo_param.getCodDepartamento();
						ls_codMunicipio        = atercqo_param.getCodMunicipio();

						validarPaisDepMun(
						    IdentificadoresCommon.INDICATIVO_PAIS_DEFAULT, ls_codDepartamento, ls_codMunicipio, true,
						    StringUtils.isValidString(ls_codDepartamento), StringUtils.isValidString(ls_codMunicipio),
						    ProyectosCommon.CATASTRO_10, ldm_manager
						);

						TipoEntradaRegistrarCambioQuintoOrdenPredio ltercqop_predio;

						ltercqop_predio = atercqo_param.getPredio();

						if(ltercqop_predio != null)
						{
							String ls_numeroPredialEntrada;

							ls_numeroPredialEntrada = ltercqop_predio.getNumPredial();

							if(StringUtils.isValidString(ls_numeroPredialEntrada))
							{
								String ls_numeroPredial;

								ls_numeroPredial = lpr_predioRegistro.getNumeroPredial();

								if(StringUtils.isValidString(ls_numeroPredial))
									lpr_predioRegistro.setNumeroPredialAnt(ls_numeroPredial);

								lpr_predioRegistro.setNumeroPredial(ls_numeroPredialEntrada);
								lpr_predioRegistro.setIdUsuarioModificacion(as_userId);
								lpr_predioRegistro.setIpModificacion(as_remoteIp);

								lpr_predioRegistro = DaoCreator.getPredioRegistroDAO(ldm_manager)
										                           .updateQuintoOrden(lpr_predioRegistro);
							}
							else
								throw new B2BException(addErrorCTO(ErrorKeys.ERROR_SIN_OBJETO_PREDIO_CODIGO));
						}
						else
							throw new B2BException(addErrorCTO(ErrorKeys.ERROR_SIN_OBJETO_PREDIO_CODIGO));

						//Errores 447 y 448 no parametrizados para propagación.
					}
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();
			clh_LOGGER.error("registrarCambioQuintoOrden", lb2be_e);

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
			clh_LOGGER.error("registrarCambioQuintoOrden", le_e);

			lbi_codigoMensaje         = BigInteger.valueOf(CodigoMensajeCommon.CODIGO_500);
			ls_descripcionMensaje     = le_e.getMessage();
		}
		finally
		{
			ldm_manager.close();
		}

		if(lpr_predioRegistro != null)
			ltsrcqo_return = new TipoSalidaRegistrarCambioQuintoOrden(
				    lpr_predioRegistro.getNumeroPredial(), lpr_predioRegistro.getIdTipoPredio(), lbi_codigoMensaje,
				    ls_descripcionMensaje
				);
		else
			ltsrcqo_return = new TipoSalidaRegistrarCambioQuintoOrden("", "", lbi_codigoMensaje, ls_descripcionMensaje);

		return ltsrcqo_return;
	}
}
