package com.bachue.prosnr21.integracionConciliaciones.cliente.consultaCatalogos;

import co.gov.supernotariado.www.schemas.bachue.ci.consultacatalogos.consultarcatalogos.v1.TipoEntradaConsultarCatalogos;
import co.gov.supernotariado.www.schemas.bachue.ci.consultacatalogos.consultarcatalogos.v1.TipoSalidaConsultarCatalogos;

import co.gov.supernotariado.www.services.bachue.ci.consultacatalogos.v1.SDI_CI_ConsultaCatalogosProxy;
import co.gov.supernotariado.www.services.bachue.ci.consultacatalogos.v1.SDI_CI_ConsultaCatalogos_PortType;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.logging.LoggerHandler;

import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr21.common.constants.ConstanteCommon;
import com.bachue.snr.prosnr21.common.constants.ErrorKeys;


/**
 * Consumo de catalogos a través del servicio expuesto por el bus
 * @author dbeltran
 *
 */
public class ConsumoCatalogo
{
	/** Constante clh_LOGGER. */
	private static final LoggerHandler clh_LOGGER = (LoggerHandler)com.bachue.snr.prosnr01.common.utils.LoggingHelper
			.getLogger(ConsumoCatalogo.class);

	/**
	 * Consumo de la operacion de consulta de catalogos expuesta por el bus
	 * @param as_catalogo Catalogo que se quiere consultar
	 * @param as_modulo Modulo que hace la consulta
	 * @param as_parametro Parametro para filtro de resultados del catálogo
	 * @param as_url URL del endpoint donde se debe hacer el consumo
	 *
	 * @return Salida de la operación de consulta de catalogo, incluyendo codigos de error y registros de salida
	 */
	public static synchronized TipoSalidaConsultarCatalogos obtenerConsultaCatalogo(
	    String as_catalogo, String as_modulo, String as_parametro, String as_url
	)
	    throws B2BException
	{
		TipoSalidaConsultarCatalogos ltscc_respuesta;

		ltscc_respuesta = null;

		try
		{
			if(StringUtils.isValidString(as_url))
			{
				SDI_CI_ConsultaCatalogosProxy     lsdiciccp_proxy;
				SDI_CI_ConsultaCatalogos_PortType lsdiciccpy_interfaz;
				String                            ls_respuesta;
				TipoEntradaConsultarCatalogos     ltecc_peticion;

				lsdiciccp_proxy         = new SDI_CI_ConsultaCatalogosProxy(as_url);
				lsdiciccpy_interfaz     = lsdiciccp_proxy.getSDI_CI_ConsultaCatalogos_PortType();
				ls_respuesta            = null;

				if(lsdiciccpy_interfaz != null)
				{
					ltecc_peticion = new TipoEntradaConsultarCatalogos();

					ltecc_peticion.setCatalogo(as_catalogo);
					ltecc_peticion.setModulo(as_modulo);
					ltecc_peticion.setParametro((as_parametro != null) ? as_parametro : new String());

					try
					{
						ltscc_respuesta = lsdiciccpy_interfaz.consultarCatalogos(ltecc_peticion);

						if(ltscc_respuesta != null)
							ls_respuesta = ltscc_respuesta.getCodigoMensaje();
						else
							ls_respuesta = ConstanteCommon.SIN_RESPUESTA;
					}
					catch(Exception le_e)
					{
						ls_respuesta = le_e.getMessage();

						clh_LOGGER.error(le_e);
					}

					if((ls_respuesta == null) || !ls_respuesta.equals(ConstanteCommon.CODIGO_200))
					{
						Object[] loa_args = {ls_respuesta};

						throw new B2BException(ErrorKeys.ERROR_NO_SE_ENCONTRO_RESPUESTA_VALIDA, loa_args);
					}
				}
				else
					throw new B2BException(ErrorKeys.ERROR_INTERFAZ);
			}
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("obtenerConsultaCatalogo", lb2be_e);

			throw lb2be_e;
		}

		return ltscc_respuesta;
	}
}
