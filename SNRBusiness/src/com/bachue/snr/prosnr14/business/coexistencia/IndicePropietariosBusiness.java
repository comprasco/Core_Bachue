package com.bachue.snr.prosnr14.business.coexistencia;

import co.gov.supernotariado.www.services.bachue.cb.indicepropietarios.consultar.v1.InmuebleDTO;
import co.gov.supernotariado.www.services.bachue.cb.indicepropietarios.consultar.v1.PropietarioDTO;
import co.gov.supernotariado.www.services.bachue.cb.indicepropietarios.consultar.v1.TipoEntradaIndicePropietarios;
import co.gov.supernotariado.www.services.bachue.cb.indicepropietarios.consultar.v1.TipoSalidaIndicePropietarios;

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
import com.bachue.snr.prosnr01.model.sdb.acc.Persona;
import com.bachue.snr.prosnr01.model.sdb.bng.PredioRegistro;
import com.bachue.snr.prosnr01.model.sdb.bng.Propietario;
import com.bachue.snr.prosnr01.model.sdb.pgn.CirculoRegistral;
import com.bachue.snr.prosnr01.model.sdb.pgn.Departamento;
import com.bachue.snr.prosnr01.model.sdb.pgn.Municipio;

import com.bachue.snr.prosnr10.model.catastro.PropietarioCatastro;

import com.bachue.snr.prosnr14.common.constants.ErrorKeys;

import com.bachue.snr.prosnr14.model.coexistencia.CoexistenciaResponse;

import java.math.BigInteger;

import java.util.Collection;
import java.util.Map;


/**
 * Clase que contiene todos las propiedades IndicePropietariosBusiness.
 *
 * @author  Carlos Calderón
 * Fecha de Creacion: 16/03/2020
 */
public class IndicePropietariosBusiness extends CoexistenciaBusiness
{
	/** Constante clh_LOGGER. */
	private static final LoggerHandler clh_LOGGER = (LoggerHandler)com.bachue.snr.prosnr01.common.utils.LoggingHelper
			.getLogger(IndicePropietariosBusiness.class, ProyectosCommon.COEXISTENCIA_14);

	/**
	 * Consultar.
	 *
	 * @param ateip_entrada de ateip entrada
	 * @param as_userId de as user id
	 * @param as_remoteIp de as remote ip
	 * @return el valor de tipo salida indice propietarios
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public TipoSalidaIndicePropietarios consultar(
	    TipoEntradaIndicePropietarios ateip_entrada, String as_userId, String as_remoteIp
	)
	    throws B2BException
	{
		TipoSalidaIndicePropietarios ltsip_salida;
		DAOManager                   ldm_manager;
		BigInteger                   lbi_codigoMensaje;
		String                       ls_descripcionMensaje;

		ltsip_salida              = new TipoSalidaIndicePropietarios();
		ldm_manager               = DaoManagerFactory.getDAOManager();
		lbi_codigoMensaje         = BigInteger.valueOf(CodigoMensajeCommon.CODIGO_200);
		ls_descripcionMensaje     = null;

		try
		{
			if(ateip_entrada == null)
				throw new B2BException(addErrorCX(ErrorKeys.ERROR_OPERACION));

			if((ateip_entrada.getTipoDocumentoPersona() != null) && (ateip_entrada.getNumDocumentoPersona() == null))
				throw new B2BException(addErrorCX(ErrorKeys.ERROR_DOCUMENTO_PERSONA_INVALIDO));
			else if(
			    (ateip_entrada.getNumDocumentoPersona() != null) && (ateip_entrada.getTipoDocumentoPersona() == null)
			)
				throw new B2BException(addErrorCX(ErrorKeys.ERROR_TIPO_DOCUMENTO_INVALIDO));
			else
			{
				if(
				    (ateip_entrada.getNumNIT() == null)
					    && ((ateip_entrada.getNumDocumentoPersona() == null)
					    && (ateip_entrada.getTipoDocumentoPersona() == null))
				)
					throw new B2BException(addErrorCX(ErrorKeys.ERROR_DOCUMENTO_INVALIDO));
				else if(ateip_entrada.getNumNIT() == null)
					throw new B2BException(addErrorCX(ErrorKeys.ERROR_DOCUMENTO_NIT_INVALIDO));
			}

			Persona lp_persona;
			String  ls_filtroBusquedaPersona;

			lp_persona     = new Persona(ateip_entrada);

			ls_filtroBusquedaPersona = IdentificadoresCommon.TIPO_NUM_DOC;

			{
				String ls_tipoDocumento;

				ls_tipoDocumento = lp_persona.getTipoDocIdentidad();

				if(
				    ls_tipoDocumento.equalsIgnoreCase(IdentificadoresCommon.NIT)
					    && StringUtils.isValidString(lp_persona.getRazonSocial())
				)
					ls_filtroBusquedaPersona = IdentificadoresCommon.TIPO_NUM_DOC_NIT;
				else if(
				    !(ls_tipoDocumento.equalsIgnoreCase(IdentificadoresCommon.NIT))
					    && (StringUtils.isValidString(lp_persona.getPrimerNombre())
					    || StringUtils.isValidString(lp_persona.getSegundoNombre())
					    || StringUtils.isValidString(lp_persona.getPrimerApellido())
					    || StringUtils.isValidString(lp_persona.getSegundoApellido()))
				)
					ls_filtroBusquedaPersona = IdentificadoresCommon.TIPO_NUM_DOC_NOMBRES;
			}

			lp_persona = DaoCreator.getPersonaDAO(ldm_manager).findByPersonData(lp_persona, ls_filtroBusquedaPersona);

			if(lp_persona != null)
			{
				Collection<Propietario> lcp_cPropietarios;
				String                  ls_idPersona;
				Predio                  lp_datos;
				InmuebleDTO[]           lidto_datosInmueble;

				lp_datos                = new Predio(ateip_entrada);
				ls_idPersona            = lp_persona.getIdPersona();
				lcp_cPropietarios       = null;
				lidto_datosInmueble     = null;

				if(
				    (ateip_entrada.getNumIdentificacionPredio() != null)
					    && (ateip_entrada.getTipoIdentificacionPredio() != null)
				)
				{
					if(
					    validarTipoIdentificacionPredio(lp_datos, ProyectosCommon.COEXISTENCIA_14)
						    && validarNumeroIdentificacionPredio(lp_datos, ProyectosCommon.COEXISTENCIA_14)
					)
					{
						if(lp_datos.isFolioMatricula())
							validarMatriculaCoexistenciaNumerica(lp_datos.getNumeroIdentificacionPredio());

						PredioRegistro lpr_predioRegistro;

						lpr_predioRegistro = consultarInformacionMatricula(
							    lp_datos, ProyectosCommon.COEXISTENCIA_14, ldm_manager
							);

						if(lpr_predioRegistro != null)
							lcp_cPropietarios = DaoCreator.getPropietarioDAO(ldm_manager)
									                          .findByIdPersonaCirculoMatriculaActivo(
									    ls_idPersona, lpr_predioRegistro.getIdCirculo(),
									    NumericUtils.getLongWrapper(lpr_predioRegistro.getIdMatricula()), lp_datos
									);
						else
							throw new B2BException(addErrorCX(ErrorKeys.ERROR_CONSULTA_RESULTADOS));
					}
				}
				else
					lcp_cPropietarios = DaoCreator.getPropietarioDAO(ldm_manager)
							                          .findByIdPersonaActivo(ls_idPersona, lp_datos);

				if((lcp_cPropietarios != null) && (lcp_cPropietarios.size() > 0))
				{
					CoexistenciaResponse    lcr_response;
					Collection<Propietario> lcp_cllFiltrada;
					String                  ls_mensajeConvenioCirculos;

					lcr_response = new CoexistenciaResponse(lp_datos);

					lcr_response.setCllPropietario(lcp_cPropietarios);

					lcr_response     = filtrarConsulta(
						    lcr_response, com.bachue.snr.prosnr14.common.constants.IdentificadoresCommon.PROPIETARIO,
						    ldm_manager
						);

					lcp_cllFiltrada                = lcr_response.getCllPropietario();
					ls_mensajeConvenioCirculos     = lcr_response.getMensajeConvenioCirculos();

					if(CollectionUtils.isValidCollection(lcp_cllFiltrada))
					{
						int li_contador;

						li_contador             = 0;
						lidto_datosInmueble     = new InmuebleDTO[lcp_cllFiltrada.size()];

						for(Propietario lp_actual : lcp_cllFiltrada)
						{
							if(lp_actual != null)
							{
								PropietarioDTO[] lpdto_propietarios;
								PredioRegistro   lpr_predioRegistro;
								Predio           lp_inmueble;

								lpdto_propietarios     = null;
								lp_inmueble            = new Predio(
									    lp_actual.getIdCirculo(), lp_actual.getIdMatricula(), true
									);
								lpr_predioRegistro     = consultarInformacionMatricula(
									    lp_inmueble, ProyectosCommon.COEXISTENCIA_14, ldm_manager
									);

								obtenerInfoZonaRegistralPredio(
								    lp_inmueble, lpr_predioRegistro.getIdZonaRegistral(), ldm_manager
								);

								{
									Collection<PropietarioCatastro> lcp_propietario;

									lcp_propietario = DaoCreator.getPropietarioDAO(ldm_manager)
											                        .findPropietariosAnotacionPredio(
											    lpr_predioRegistro.getIdCirculo(), lpr_predioRegistro.getIdMatricula(),
											    0, true, true
											);

									if(CollectionUtils.isValidCollection(lcp_propietario))
									{
										int li_count;

										lpdto_propietarios     = new PropietarioDTO[lcp_propietario.size()];
										li_count               = 0;

										for(PropietarioCatastro lpc_temp : lcp_propietario)
										{
											if(lpc_temp != null)
											{
												String ls_idDocumentoTipo;

												ls_idDocumentoTipo = lpc_temp.getIdDocumentoTipo();

												if(StringUtils.isValidString(ls_idDocumentoTipo))
												{
													String ls_nombreCompleto;
													String ls_numeroDocumento;
													String ls_numNIT;

													ls_nombreCompleto      = null;
													ls_numeroDocumento     = null;
													ls_numNIT              = null;

													if(ls_idDocumentoTipo.equalsIgnoreCase(IdentificadoresCommon.NIT))
													{
														ls_nombreCompleto     = lpc_temp.getRazonSocial();
														ls_numNIT             = lpc_temp.getNumDocumentoPersona();
													}
													else
													{
														ls_numeroDocumento     = lpc_temp.getNumDocumentoPersona();
														ls_nombreCompleto      = lpc_temp.getNombrePersona();
													}

													lpdto_propietarios[li_count++] = new PropietarioDTO(
														    ls_numNIT, ls_numeroDocumento,
														    lpc_temp.getTipoDocumentoPersona(), ls_nombreCompleto
														);
												}
											}
										}
									}
								}

								String ls_nombreDepartamento;
								String ls_nombreMunicipio;
								String ls_nombreCirculo;

								ls_nombreCirculo          = null;
								ls_nombreMunicipio        = null;
								ls_nombreDepartamento     = null;

								{
									Departamento ld_departamento;

									ld_departamento = DaoCreator.getDepartamentoDAO(ldm_manager)
											                        .findById(
											    lp_inmueble.getIdPais(), lp_inmueble.getCodDepartamento()
											);

									if(ld_departamento != null)
										ls_nombreDepartamento = ld_departamento.getNombre();
								}

								{
									Municipio lm_municipio;

									lm_municipio = DaoCreator.getMunicipioDAO(ldm_manager)
											                     .findById(
											    lp_inmueble.getIdPais(), lp_inmueble.getCodDepartamento(),
											    lp_inmueble.getCodMunicipio()
											);

									if(lm_municipio != null)
										ls_nombreMunicipio = lm_municipio.getNombre();
								}

								{
									CirculoRegistral lcr_circuloRegistral;

									lcr_circuloRegistral = DaoCreator.getCirculoRegistralDAO(ldm_manager)
											                             .findById(lpr_predioRegistro.getIdCirculo());

									if(lcr_circuloRegistral != null)
										ls_nombreCirculo = lcr_circuloRegistral.getNombre();
								}

								lidto_datosInmueble[li_contador++] = new InmuebleDTO(
									    lp_inmueble.getCodCirculoRegistral(), lp_inmueble.getCodDepartamento(),
									    lp_inmueble.getCodMunicipio(),
									    consultarDireccionCirculoMatricula(
									        lpr_predioRegistro.getIdCirculo(),
									        String.valueOf(lpr_predioRegistro.getIdMatricula()), ldm_manager
									    ), String.valueOf(lpr_predioRegistro.getIdMatricula()),
									    lpr_predioRegistro.getNumeroPredial(), lpr_predioRegistro.getNupre(),
									    ls_nombreCirculo, ls_nombreDepartamento, ls_nombreMunicipio, lpdto_propietarios
									);
							}
						}

						{
							String     ls_mensajeFinal;
							BigInteger lbi_codigoFinal;

							ls_mensajeFinal     = StringUtils.isValidString(ls_mensajeConvenioCirculos)
								? ls_mensajeConvenioCirculos : "OK";
							lbi_codigoFinal     = StringUtils.isValidString(ls_mensajeConvenioCirculos)
								? BigInteger.valueOf(CodigoMensajeCommon.CODIGO_201) : lbi_codigoMensaje;

							ltsip_salida = new TipoSalidaIndicePropietarios(
								    Long.valueOf(lidto_datosInmueble.length), lidto_datosInmueble, lbi_codigoFinal,
								    ls_mensajeFinal
								);
						}
					}
					else if(StringUtils.isValidString(ls_mensajeConvenioCirculos))
						throw new B2BException(addErrorCX(ErrorKeys.ERROR_MATRICULAS_PROPIETARIOS_NO_DISPONIBLES));
					else
						throw new B2BException(addErrorCX(ErrorKeys.ERROR_CONSULTA_RESULTADOS));
				}
				else
					throw new B2BException(addErrorCX(ErrorKeys.ERROR_CONSULTA_RESULTADOS));
			}
			else
				throw new B2BException(addErrorCX(ErrorKeys.ERROR_CONSULTA_RESULTADOS));
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();
			clh_LOGGER.error("consultar", lb2be_e);

			Map<String, String> lmss_codigoMensaje;

			lmss_codigoMensaje     = obtenerErrorServicios(lb2be_e.getMessageKey());

			lbi_codigoMensaje         = NumericUtils.getBigInteger(
				    lmss_codigoMensaje.get(IdentificadoresCommon.CODIGO_MENSAJE)
				);
			ls_descripcionMensaje     = lmss_codigoMensaje.get(IdentificadoresCommon.DESCRIPCION_MENSAJE);
			ltsip_salida              = null;
		}
		catch(Exception le_e)
		{
			ldm_manager.setRollbackOnly();
			clh_LOGGER.error("consultar", le_e);

			lbi_codigoMensaje         = BigInteger.valueOf(CodigoMensajeCommon.CODIGO_500);
			ls_descripcionMensaje     = le_e.getMessage();
			ltsip_salida              = null;
		}
		finally
		{
			ldm_manager.close();
		}

		if(ltsip_salida == null)
			ltsip_salida = new TipoSalidaIndicePropietarios(null, null, lbi_codigoMensaje, ls_descripcionMensaje);

		return ltsip_salida;
	}
}
