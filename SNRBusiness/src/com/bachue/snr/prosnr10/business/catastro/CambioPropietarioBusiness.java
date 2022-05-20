package com.bachue.snr.prosnr10.business.catastro;

import co.gov.supernotariado.www.schemas.bachue.ee.cambioPropietario.consultarCambioPropietario.v1.TipoAnotacionPredio;
import co.gov.supernotariado.www.schemas.bachue.ee.cambioPropietario.consultarCambioPropietario.v1.TipoEntradaConsultarCambioPropietario;
import co.gov.supernotariado.www.schemas.bachue.ee.cambioPropietario.consultarCambioPropietario.v1.TipoPropietario;
import co.gov.supernotariado.www.schemas.bachue.ee.cambioPropietario.consultarCambioPropietario.v1.TipoSalidaConsultarCambioPropietario;

import com.b2bsg.common.dataAccess2.DAOManager;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.logging.LoggerHandler;

import com.b2bsg.common.util.NumericUtils;

import com.bachue.snr.prosnr01.common.constants.CodigoMensajeCommon;
import com.bachue.snr.prosnr01.common.constants.DescripcionMensajeCommon;
import com.bachue.snr.prosnr01.common.constants.IdentificadoresCommon;
import com.bachue.snr.prosnr01.common.constants.ProyectosCommon;

import com.bachue.snr.prosnr01.dao.DaoCreator;
import com.bachue.snr.prosnr01.dao.DaoManagerFactory;

import com.bachue.snr.prosnr01.model.predio.Predio;
import com.bachue.snr.prosnr01.model.sdb.bng.AnotacionPredio;
import com.bachue.snr.prosnr01.model.sdb.bng.PredioRegistro;

import com.bachue.snr.prosnr10.common.constants.ErrorKeys;

import com.bachue.snr.prosnr10.model.catastro.AnotacionCatastro;
import com.bachue.snr.prosnr10.model.catastro.PropietarioCatastro;

import java.math.BigInteger;

import java.util.Collection;
import java.util.Map;


/**
 * Clase que contiene todos las propiedades CambioPropietarioBusiness.
 *
 * @author  Julián David Vaca Rodriguez
 * Fecha de Creacion: 15/03/2020
 */
public class CambioPropietarioBusiness extends CatastroBusiness
{
	/** Constante clh_LOGGER. */
	private static final LoggerHandler clh_LOGGER = (LoggerHandler)com.bachue.snr.prosnr01.common.utils.LoggingHelper
			.getLogger(CambioPropietarioBusiness.class, ProyectosCommon.CATASTRO_10);

	/**
	 * Permite consultar la mutación de primer orden por cambio de propietario o poseedor.
	 *
	 * @param atecp_entrada de atecp entrada
	 * @param as_userId de as user id
	 * @param as_remoteIp de as remote ip
	 * @return el valor de tipo salida consultar cambio propietario
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public synchronized TipoSalidaConsultarCambioPropietario consultarCambioPropietario(
	    TipoEntradaConsultarCambioPropietario atecp_entrada, String as_userId, String as_remoteIp
	)
	    throws B2BException
	{
		DAOManager                           ldm_manager;
		TipoSalidaConsultarCambioPropietario ltscpi_respuesta;
		BigInteger                           lbi_codigoMensaje;
		String                               ls_descripcionMensaje;

		ldm_manager               = DaoManagerFactory.getDAOManager();
		ltscpi_respuesta          = null;
		lbi_codigoMensaje         = BigInteger.valueOf(CodigoMensajeCommon.CODIGO_200);
		ls_descripcionMensaje     = DescripcionMensajeCommon.EXITO;

		try
		{
			if(atecp_entrada == null)
				throw new B2BException(addErrorCTO(ErrorKeys.ERROR_PARAMETROS_NO_VALIDOS_CODIGO));

			Predio lp_datos;

			lp_datos = new Predio(atecp_entrada);

			if(validarTipoIdentificacionPredio(lp_datos) && validarNumeroIdentificacionPredio(lp_datos))
			{
				PredioRegistro lpr_predioRegistro;

				lpr_predioRegistro = consultarInformacionMatricula(lp_datos, ldm_manager);

				if(lpr_predioRegistro != null)
				{
					AnotacionPredio lap_ap;
					long            ll_idMatricula;
					String          ls_idCirculo;

					obtenerInfoZonaRegistralPredio(lp_datos, lpr_predioRegistro.getIdZonaRegistral(), ldm_manager);

					ltscpi_respuesta     = new TipoSalidaConsultarCambioPropietario();
					ls_idCirculo         = lpr_predioRegistro.getIdCirculo();
					ll_idMatricula       = lpr_predioRegistro.getIdMatricula();
					lap_ap               = DaoCreator.getAnotacionPredioDAO(ldm_manager)
							                             .buscarUltimaAnotacionCambioPropietario(
							    ls_idCirculo, ll_idMatricula
							);

					ltscpi_respuesta.setCodDepartamento(lp_datos.getCodDepartamento());
					ltscpi_respuesta.setCodMunicipio(lp_datos.getCodMunicipio());
					ltscpi_respuesta.setCodCirculoRegistral(lp_datos.getCodCirculoRegistral());

					if(lap_ap != null)
					{
						AnotacionCatastro lac_ac;

						lac_ac = obtenerDatosAnotacionIntervinientes(lap_ap, true, true, ldm_manager);

						if(lac_ac != null)
						{
							TipoAnotacionPredio[] ltap_anotacionPredio;
							TipoPropietario[]     ltp_propietario;
							Double                ld_valorActo;

							ld_valorActo             = NumericUtils.getDoubleWrapper(lap_ap.getValor());
							ltap_anotacionPredio     = new TipoAnotacionPredio[1];
							ltp_propietario          = null;

							if(NumericUtils.isValidDouble(ld_valorActo, NumericUtils.DEFAULT_DOUBLE_VALUE, false))
								lac_ac.setValorActo(ld_valorActo);

							{
								Collection<PropietarioCatastro> lcpc_intervinientes;

								lcpc_intervinientes = lac_ac.getIntervinientesCatastro();

								if(
								    (lcpc_intervinientes != null)
									    && (lcpc_intervinientes.size() > NumericUtils.DEFAULT_INT_VALUE)
								)
								{
									int li_contador;

									ltp_propietario     = new TipoPropietario[lcpc_intervinientes.size()];
									li_contador         = NumericUtils.DEFAULT_INT_VALUE;

									for(PropietarioCatastro lpc_actual : lcpc_intervinientes)
									{
										if(lpc_actual != null)
											ltp_propietario[li_contador++] = new TipoPropietario(
												    lpc_actual.getTipoPersona(), lpc_actual.getTipoDocumentoPersona(),
												    lpc_actual.getNumDocumentoPersona(), lpc_actual.getDRR(),
												    lpc_actual.getPorcentajeParticipacion()
												);
									}
								}
							}

							ltap_anotacionPredio[0] = new TipoAnotacionPredio(
								    lac_ac.getComentario(), lac_ac.getFechaAnotacion(), lac_ac.getValorActo(),
								    lac_ac.getCodNaturalezaJuridicaFolio(), ltp_propietario
								);

							ltscpi_respuesta.setAnotacionesPredio(ltap_anotacionPredio);
						}
						else
							throw new B2BException(addErrorCTO(ErrorKeys.ERROR_SIN_INFO_MUTACION));
					}
					else
						throw new B2BException(addErrorCTO(ErrorKeys.ERROR_SIN_INFO_MUTACION));

					ltscpi_respuesta.setCodMensaje(lbi_codigoMensaje);
					ltscpi_respuesta.setDescripcionMensaje(ls_descripcionMensaje);
				}
				else
					throw new B2BException(addErrorCTO(ErrorKeys.ERROR_NUMERO_ID_CATASTRAL_NO_EXISTENTE_CODIGO));
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();
			clh_LOGGER.error("consultarCambioPropietario", lb2be_e);
			ltscpi_respuesta = null;

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
			clh_LOGGER.error("consultarCambioPropietario", le_e);
			ltscpi_respuesta     = null;

			lbi_codigoMensaje         = BigInteger.valueOf(CodigoMensajeCommon.CODIGO_500);
			ls_descripcionMensaje     = le_e.getMessage();
		}
		finally
		{
			ldm_manager.close();
		}

		if(ltscpi_respuesta == null)
			ltscpi_respuesta = new TipoSalidaConsultarCambioPropietario(
				    null, null, null, null, lbi_codigoMensaje, ls_descripcionMensaje
				);

		return ltscpi_respuesta;
	}
}
