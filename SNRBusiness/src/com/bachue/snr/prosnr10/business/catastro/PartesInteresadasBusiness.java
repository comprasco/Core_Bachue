package com.bachue.snr.prosnr10.business.catastro;

import co.gov.supernotariado.www.schemas.bachue.ee.partesinteresadas.consultarpartesinteresadas.v1.TipoEntradaConsultarPartesInteresadas;
import co.gov.supernotariado.www.schemas.bachue.ee.partesinteresadas.consultarpartesinteresadas.v1.TipoSalidaConsultarPartesInteresadas;
import co.gov.supernotariado.www.schemas.bachue.ee.partesinteresadas.consultarpartesinteresadas.v1.TipoSalidaConsultarPartesInteresadasInteresadosInteresado;

import com.b2bsg.common.dataAccess2.DAOManager;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.logging.LoggerHandler;

import com.b2bsg.common.util.CollectionUtils;
import com.b2bsg.common.util.NumericUtils;

import com.bachue.snr.prosnr01.common.constants.CodigoMensajeCommon;
import com.bachue.snr.prosnr01.common.constants.DescripcionMensajeCommon;
import com.bachue.snr.prosnr01.common.constants.IdentificadoresCommon;
import com.bachue.snr.prosnr01.common.constants.ProyectosCommon;

import com.bachue.snr.prosnr01.dao.DaoManagerFactory;

import com.bachue.snr.prosnr01.model.predio.Predio;
import com.bachue.snr.prosnr01.model.sdb.bng.AnotacionPredio;
import com.bachue.snr.prosnr01.model.sdb.bng.PredioRegistro;

import com.bachue.snr.prosnr10.common.constants.ErrorKeys;

import com.bachue.snr.prosnr10.model.catastro.AnotacionCatastro;
import com.bachue.snr.prosnr10.model.catastro.IntervinienteCatastro;
import com.bachue.snr.prosnr10.model.catastro.PropietarioCatastro;

import java.math.BigInteger;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;


/**
 * Clase que contiene todos las propiedades PartesInteresadasBussines.
 *
 * @author  Julián David Vaca Rodriguez
 * Fecha de Creacion: 4/03/2020
 */
public class PartesInteresadasBusiness extends CatastroBusiness
{
	/** Constante clh_LOGGER. */
	private static final LoggerHandler clh_LOGGER = (LoggerHandler)com.bachue.snr.prosnr01.common.utils.LoggingHelper
			.getLogger(PartesInteresadasBusiness.class, ProyectosCommon.CATASTRO_10);

	/**
	 * Permite consultar la información asociada a las partes interesadas sobre una propiedad inmueble
	 * actuales o históricos con Folio de Matrícula Inmobiliaria.
	 *
	 * @param atecpi_entrada de atecpi entrada
	 * @param as_userId de as user id
	 * @param as_remoteIp de as remote ip
	 * @return el valor de tipo salida consultar partes interesadas
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public synchronized TipoSalidaConsultarPartesInteresadas consultarPartesInteresadas(
	    TipoEntradaConsultarPartesInteresadas atecpi_entrada, String as_userId, String as_remoteIp
	)
	    throws B2BException
	{
		DAOManager                           ldm_manager;
		TipoSalidaConsultarPartesInteresadas ltscpi_respuesta;
		BigInteger                           lbi_codigoMensaje;
		String                               ls_descripcionMensaje;

		ldm_manager               = DaoManagerFactory.getDAOManager();
		ltscpi_respuesta          = null;
		lbi_codigoMensaje         = BigInteger.valueOf(CodigoMensajeCommon.CODIGO_200);
		ls_descripcionMensaje     = DescripcionMensajeCommon.EXITO;

		try
		{
			if(atecpi_entrada == null)
				throw new B2BException(addErrorCTO(ErrorKeys.ERROR_PARAMETROS_NO_VALIDOS_CODIGO));

			Predio lp_datos;

			lp_datos = new Predio(atecpi_entrada);

			if(validarTipoIdentificacionPredio(lp_datos) && validarNumeroIdentificacionPredio(lp_datos))
			{
				PredioRegistro lpr_predioRegistro;

				lpr_predioRegistro = consultarInformacionMatricula(lp_datos, ldm_manager);

				if(lpr_predioRegistro != null)
				{
					long   ll_idMatricula;
					String ls_idCirculo;

					obtenerInfoZonaRegistralPredio(lp_datos, lpr_predioRegistro.getIdZonaRegistral(), ldm_manager);

					ltscpi_respuesta     = new TipoSalidaConsultarPartesInteresadas();
					ls_idCirculo         = lpr_predioRegistro.getIdCirculo();
					ll_idMatricula       = lpr_predioRegistro.getIdMatricula();

					ltscpi_respuesta.setCodDepartamento(lp_datos.getCodDepartamento());
					ltscpi_respuesta.setCodMunicipio(lp_datos.getCodMunicipio());
					ltscpi_respuesta.setCodCirculoRegistral(lp_datos.getCodCirculoRegistral());
					ltscpi_respuesta.setNumMatriculaInmobiliaria(String.valueOf(lpr_predioRegistro.getIdMatricula()));

					consultarListaInteresados(
					    ltscpi_respuesta,
					    consultarAnotacionesCirculoMatricula(
					        ls_idCirculo, NumericUtils.getLongWrapper(ll_idMatricula), ldm_manager
					    ), ldm_manager
					);

					ltscpi_respuesta.setCodigoMensaje(lbi_codigoMensaje);
					ltscpi_respuesta.setDescripcionMensaje(ls_descripcionMensaje);
				}
				else
					throw new B2BException(addErrorCTO(ErrorKeys.ERROR_NUMERO_ID_CATASTRAL_NO_EXISTENTE_CODIGO));
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();
			clh_LOGGER.error("consultarPartesInteresadas", lb2be_e);
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
			clh_LOGGER.error("consultarPartesInteresadas", le_e);
			ltscpi_respuesta     = null;

			lbi_codigoMensaje         = BigInteger.valueOf(CodigoMensajeCommon.CODIGO_500);
			ls_descripcionMensaje     = le_e.getMessage();
		}
		finally
		{
			ldm_manager.close();
		}

		if(ltscpi_respuesta == null)
			ltscpi_respuesta = new TipoSalidaConsultarPartesInteresadas(
				    null, null, null, null, null, lbi_codigoMensaje, ls_descripcionMensaje
				);

		return ltscpi_respuesta;
	}

	/**
	 * Consultar lista interesados.
	 *
	 * @param atscpi_respuesta de atscpi respuesta
	 * @param lcap_anotacionPredio de lcap anotacion predio
	 * @param adm_manager de adm manager
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	private synchronized void consultarListaInteresados(
	    TipoSalidaConsultarPartesInteresadas atscpi_respuesta, Collection<AnotacionPredio> lcap_anotacionPredio,
	    DAOManager adm_manager
	)
	    throws B2BException
	{
		try
		{
			if((atscpi_respuesta != null) && CollectionUtils.isValidCollection(lcap_anotacionPredio))
			{
				Collection<AnotacionCatastro> lcap_anotaciones;
				int                           li_contadorIntervinientes;
				int                           li_sizeIntervinientes;
				li_contadorIntervinientes     = 0;
				li_sizeIntervinientes         = 0;

				lcap_anotaciones = new ArrayList<AnotacionCatastro>();

				for(AnotacionPredio lap_anotacionPredio : lcap_anotacionPredio)
				{
					if(lap_anotacionPredio != null)
					{
						AnotacionCatastro lac_ac;

						lac_ac = obtenerDatosAnotacionIntervinientes(lap_anotacionPredio, false, adm_manager);

						if(lac_ac != null)
						{
							Collection<PropietarioCatastro> lcic_cic;

							lcic_cic                  = lac_ac.getIntervinientesCatastro();
							li_sizeIntervinientes     = (CollectionUtils.isValidCollection(lcic_cic)
								? (li_sizeIntervinientes + lcic_cic.size()) : li_sizeIntervinientes);

							lcap_anotaciones.add(lac_ac);
						}
					}
				}

				if(CollectionUtils.isValidCollection(lcap_anotaciones))
				{
					TipoSalidaConsultarPartesInteresadasInteresadosInteresado[] ltscpii_interesados;

					ltscpii_interesados = new TipoSalidaConsultarPartesInteresadasInteresadosInteresado[li_sizeIntervinientes];

					for(AnotacionCatastro lac_actual : lcap_anotaciones)
					{
						if(lac_actual != null)
						{
							Collection<PropietarioCatastro> lcic_cic;

							lcic_cic = lac_actual.getIntervinientesCatastro();

							if(CollectionUtils.isValidCollection(lcic_cic))
							{
								for(IntervinienteCatastro lic_actual : lcic_cic)
								{
									if(lic_actual != null)
										ltscpii_interesados[li_contadorIntervinientes++] = new TipoSalidaConsultarPartesInteresadasInteresadosInteresado(
											    lic_actual.getTipoPersona(), lic_actual.getTipoDocumentoPersona(),
											    lic_actual.getNumDocumentoPersona(), lic_actual.getPrimerNombre(),
											    lic_actual.getSegundoNombre(), lic_actual.getPrimerApellido(),
											    lic_actual.getSegundoApellido(), lic_actual.getRazonSocial(),
											    lic_actual.getTipoParteInteresada()
											);
								}
							}
						}
					}

					atscpi_respuesta.setInteresados(ltscpii_interesados);
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("consultarListaInteresados", lb2be_e);
			throw lb2be_e;
		}
	}
}
