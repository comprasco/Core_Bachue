package com.bachue.snr.prosnr15.business.consulta.datos.registrales;

import co.gov.supernotariado.www.schemas.bachue.cb.consultadatosregistrales.consultardatosregistrales.v1.Anotacion;
import co.gov.supernotariado.www.schemas.bachue.cb.consultadatosregistrales.consultardatosregistrales.v1.Interviniente;
import co.gov.supernotariado.www.schemas.bachue.cb.consultadatosregistrales.consultardatosregistrales.v1.Matricula;
import co.gov.supernotariado.www.schemas.bachue.cb.consultadatosregistrales.consultardatosregistrales.v1.TipoEntradaConsultarDatosRegistrales;
import co.gov.supernotariado.www.schemas.bachue.cb.consultadatosregistrales.consultardatosregistrales.v1.TipoSalidaConsultarDatosRegistrales;
import co.gov.supernotariado.www.schemas.bachue.cb.consultadatosregistrales.consultardatosregistrales.v1.TipoSalidaConsultarDatosRegistralesCodigoMensaje;
import co.gov.supernotariado.www.schemas.bachue.cb.consultadatosregistrales.consultardatosregistrales.v1.Titular;

import com.b2bsg.common.dataAccess2.DAOManager;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.logging.LoggerHandler;

import com.b2bsg.common.util.CollectionUtils;
import com.b2bsg.common.util.NumericUtils;
import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.business.BaseBusiness;

import com.bachue.snr.prosnr01.common.constants.ConstanteCommon;
import com.bachue.snr.prosnr01.common.constants.MessagesKeys;
import com.bachue.snr.prosnr01.common.constants.ProyectosCommon;
import com.bachue.snr.prosnr01.common.constants.SistemaOrigenCommon;

import com.bachue.snr.prosnr01.dao.DaoCreator;
import com.bachue.snr.prosnr01.dao.DaoManagerFactory;
import com.bachue.snr.prosnr01.dao.acc.PersonaDAO;
import com.bachue.snr.prosnr01.dao.bng.AnotacionPredioCiudadanoDAO;
import com.bachue.snr.prosnr01.dao.bng.AnotacionPredioDAO;
import com.bachue.snr.prosnr01.dao.bng.PredioRegistroDAO;
import com.bachue.snr.prosnr01.dao.bng.PropietarioDAO;

import com.bachue.snr.prosnr01.model.registro.Direccion;
import com.bachue.snr.prosnr01.model.sdb.acc.Persona;
import com.bachue.snr.prosnr01.model.sdb.bng.PredioRegistro;

import com.bachue.snr.prosnr15.common.constants.ErrorKeys;
import com.bachue.snr.prosnr15.common.constants.IdentificadoresCommon;

import com.google.gson.Gson;

import com.google.gson.internal.LinkedTreeMap;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;


/**
 * Clase que contiene todos las funcionalidades para consultar datos registrales
 *
 * @author Gabriel Arias
 */
public class ConsultaDatosRegistralesBusiness extends BaseBusiness
{
	/** Constante clh_LOGGER. */
	private static final LoggerHandler clh_LOGGER = (LoggerHandler)com.bachue.snr.prosnr01.common.utils.LoggingHelper
			.getLogger(ConsultaDatosRegistralesBusiness.class, ProyectosCommon.CONSULTA_DATOS_REGISTRALES_15);

	/**
	 *
	 * @param atecdr_entrada
	 * @param as_userId
	 * @param as_localIpAddress
	 * @param as_remoteIpAddress
	 * @return
	 * @throws B2BException
	 */
	public synchronized TipoSalidaConsultarDatosRegistrales consultarDatosRegistrales(
	    TipoEntradaConsultarDatosRegistrales atecdr_entrada, String as_userId, String as_localIpAddress,
	    String                               as_remoteIpAddress
	)
	    throws B2BException
	{
		DAOManager                                       ldm_manager;
		Matricula[]                                      lma_matriculas;
		String                                           ls_descripcionMensaje;
		String                                           ls_cantidadRegistros;
		TipoSalidaConsultarDatosRegistralesCodigoMensaje ltscdrcm_codigo;
		TipoSalidaConsultarDatosRegistrales              ltscdr_return;

		ldm_manager               = DaoManagerFactory.getDAOManager();
		lma_matriculas            = new Matricula[0];
		ls_descripcionMensaje     = addMessage(MessagesKeys.OK);
		ls_cantidadRegistros      = "0";
		ltscdrcm_codigo           = TipoSalidaConsultarDatosRegistralesCodigoMensaje.value1;
		ltscdr_return             = new TipoSalidaConsultarDatosRegistrales();

		try
		{
			if(atecdr_entrada != null)
			{
				Collection<PredioRegistro> lcpr_predios;
				PredioRegistroDAO          lpr_DAO;
				PersonaDAO                 lp_DAO;
				String                     ls_criterioBusqueda;
				String                     ls_modulo;
				String                     ls_valorCriterioBusqueda;

				lcpr_predios                 = null;
				lpr_DAO                      = DaoCreator.getPredioRegistroDAO(ldm_manager);
				lp_DAO                       = DaoCreator.getPersonaDAO(ldm_manager);
				ls_criterioBusqueda          = atecdr_entrada.getCriterioBusqueda();
				ls_modulo                    = atecdr_entrada.getModulo();
				ls_valorCriterioBusqueda     = atecdr_entrada.getValorCriterioBusqueda();

				if(StringUtils.isValidString(ls_modulo))
				{
					if(
					    !(ls_modulo.equalsIgnoreCase(SistemaOrigenCommon.SEDE_ELECTRONICA)
						    || ls_modulo.equalsIgnoreCase(SistemaOrigenCommon.GIS))
					)
					{
						ltscdrcm_codigo = TipoSalidaConsultarDatosRegistralesCodigoMensaje.value2;
						throw new B2BException(ErrorKeys.ERROR_MODULO_NO_PERMITIDO);
					}
				}
				else
				{
					ltscdrcm_codigo = TipoSalidaConsultarDatosRegistralesCodigoMensaje.value2;
					throw new B2BException(ErrorKeys.ERROR_MODULO_NO_VALIDO);
				}

				if(StringUtils.isValidString(ls_criterioBusqueda))
				{
					Map<String, String> lmss_criteriosBusqueda;

					lmss_criteriosBusqueda = generarCodigos(ConstanteCommon.CRITERIOS_BUSQUEDA_CDR, ldm_manager);

					if(CollectionUtils.isValidCollection(lmss_criteriosBusqueda))
					{
						if(!lmss_criteriosBusqueda.containsKey(ls_criterioBusqueda))
						{
							ltscdrcm_codigo = TipoSalidaConsultarDatosRegistralesCodigoMensaje.value2;
							throw new B2BException(ErrorKeys.ERROR_CRITERIO_NO_PERMITIDO);
						}
					}
					else
					{
						ltscdrcm_codigo = TipoSalidaConsultarDatosRegistralesCodigoMensaje.value2;
						throw new B2BException(ErrorKeys.ERROR_CRITERIOS);
					}
				}
				else
				{
					ltscdrcm_codigo = TipoSalidaConsultarDatosRegistralesCodigoMensaje.value2;
					throw new B2BException(ErrorKeys.ERROR_CRITERIO_NO_VALIDO);
				}

				if(StringUtils.isValidString(ls_valorCriterioBusqueda))
				{
					if(
					    ls_criterioBusqueda.equalsIgnoreCase(IdentificadoresCommon.NOMBRES)
						    || ls_criterioBusqueda.equalsIgnoreCase(IdentificadoresCommon.DIRECCION)
					)
					{
						Object lo_object;

						lo_object = new Gson().fromJson(atecdr_entrada.getValorCriterioBusqueda(), Object.class);

						if((lo_object == null) || !(lo_object instanceof Map<?, ?>))
						{
							ltscdrcm_codigo = TipoSalidaConsultarDatosRegistralesCodigoMensaje.value2;
							throw new B2BException(ErrorKeys.ERROR_VALOR_CRITERIO_FORMATO);
						}
					}
				}
				else
				{
					ltscdrcm_codigo = TipoSalidaConsultarDatosRegistralesCodigoMensaje.value2;
					throw new B2BException(ErrorKeys.ERROR_VALOR_CRITERIO_NO_VALIDO);
				}

				if(
				    ls_criterioBusqueda.equalsIgnoreCase(IdentificadoresCommon.CEDULA_CATASTRAL)
					    || ls_criterioBusqueda.equalsIgnoreCase(IdentificadoresCommon.CHIP)
					    || ls_criterioBusqueda.equalsIgnoreCase(IdentificadoresCommon.NUPRE)
					    || ls_criterioBusqueda.equalsIgnoreCase(IdentificadoresCommon.MATRICULA)
				)
				{
					lcpr_predios = new ArrayList<PredioRegistro>();

					if(
					    ls_criterioBusqueda.equalsIgnoreCase(IdentificadoresCommon.CEDULA_CATASTRAL)
						    || ls_criterioBusqueda.equalsIgnoreCase(IdentificadoresCommon.CHIP)
					)
						lcpr_predios = lpr_DAO.findByNumeroPredialCollection(ls_valorCriterioBusqueda);
					else if(ls_criterioBusqueda.equalsIgnoreCase(IdentificadoresCommon.NUPRE))
						lcpr_predios = lpr_DAO.findByNupreCollection(ls_valorCriterioBusqueda);
					else if(ls_criterioBusqueda.equalsIgnoreCase(IdentificadoresCommon.MATRICULA))
						lcpr_predios = lpr_DAO.findByIdCirculoIdMatricula(
							    getIdCirculo(ls_valorCriterioBusqueda), getIdMatricula(ls_valorCriterioBusqueda)
							);
				}
				else if(
				    ls_criterioBusqueda.contains(IdentificadoresCommon.TIPO_IDENTIFICACION)
					    || ls_criterioBusqueda.equalsIgnoreCase(IdentificadoresCommon.NOMBRES)
				)
				{
					Collection<Persona> lcp_personas;

					lcp_personas = null;

					if(ls_criterioBusqueda.equalsIgnoreCase(IdentificadoresCommon.NOMBRES))
					{
						Object lo_object;

						lo_object = new Gson().fromJson(atecdr_entrada.getValorCriterioBusqueda(), Object.class);

						if((lo_object != null) && (lo_object instanceof Map<?, ?>))
						{
							LinkedTreeMap<String, String> lltm_datos;

							lltm_datos = (LinkedTreeMap<String, String>)lo_object;

							if(CollectionUtils.isValidCollection(lltm_datos))
							{
								String ls_razonSocial;

								ls_razonSocial = lltm_datos.containsKey(IdentificadoresCommon.RAZONSOCIAL)
									? lltm_datos.get(IdentificadoresCommon.RAZONSOCIAL) : null;

								if(StringUtils.isValidString(ls_razonSocial))
									lcp_personas = lp_DAO.findByRazonSocial(ls_razonSocial);
								else
								{
									String ls_primerNombre;
									String ls_segundoNombre;
									String ls_primerApellido;
									String ls_segundoApellido;

									ls_primerNombre        = lltm_datos.containsKey(IdentificadoresCommon.PRIMERNOMBRE)
										? lltm_datos.get(IdentificadoresCommon.PRIMERNOMBRE) : null;
									ls_segundoNombre       = lltm_datos.containsKey(
										    IdentificadoresCommon.SEGUNDONOMBRE
										) ? lltm_datos.get(IdentificadoresCommon.SEGUNDONOMBRE) : null;
									ls_primerApellido      = lltm_datos.containsKey(
										    IdentificadoresCommon.PRIMERAPELLIDO
										) ? lltm_datos.get(IdentificadoresCommon.PRIMERAPELLIDO) : null;
									ls_segundoApellido     = lltm_datos.containsKey(
										    IdentificadoresCommon.SEGUNDOAPELLIDO
										) ? lltm_datos.get(IdentificadoresCommon.SEGUNDOAPELLIDO) : null;
									lcp_personas           = lp_DAO.findByNombres(
										    ls_primerNombre, ls_segundoNombre, ls_primerApellido, ls_segundoApellido
										);
								}
							}
						}
					}
					else
					{
						String ls_tipoIdentificacion;

						ls_tipoIdentificacion = null;

						switch(ls_criterioBusqueda)
						{
							case IdentificadoresCommon.TIPO_IDENTIFICACION_CEDULA:
								ls_tipoIdentificacion = com.bachue.snr.prosnr01.common.constants.IdentificadoresCommon.CC;

								break;

							case IdentificadoresCommon.TIPO_IDENTIFICACION_NIT:
								ls_tipoIdentificacion = com.bachue.snr.prosnr01.common.constants.IdentificadoresCommon.NIT;

								break;

							case IdentificadoresCommon.TIPO_IDENTIFICACION_CEDULA_EXT:
								ls_tipoIdentificacion = com.bachue.snr.prosnr01.common.constants.IdentificadoresCommon.CE;

								break;

							case IdentificadoresCommon.TIPO_IDENTIFICACION_PASS:
								ls_tipoIdentificacion = com.bachue.snr.prosnr01.common.constants.IdentificadoresCommon.PS;

								break;

							case IdentificadoresCommon.TIPO_IDENTIFICACION_TI:
								ls_tipoIdentificacion = com.bachue.snr.prosnr01.common.constants.IdentificadoresCommon.TI;

								break;

							case IdentificadoresCommon.TIPO_IDENTIFICACION_NUIP:
								ls_tipoIdentificacion = com.bachue.snr.prosnr01.common.constants.IdentificadoresCommon.NU;

								break;

							default:
								break;
						}

						if(StringUtils.isValidString(ls_tipoIdentificacion))
							lcp_personas = lp_DAO.findByDocumentoAndTipoDocumento(
								    ls_valorCriterioBusqueda, ls_tipoIdentificacion
								);
						else
						{
							ltscdrcm_codigo = TipoSalidaConsultarDatosRegistralesCodigoMensaje.value2;
							throw new B2BException(ErrorKeys.ERROR_TIPO_DOCUMENTO_NO_VALIDO);
						}
					}

					if(CollectionUtils.isValidCollection(lcp_personas))
					{
						lcpr_predios = new ArrayList<PredioRegistro>();

						for(Persona lp_iterador : lcp_personas)
						{
							if(lp_iterador != null)
							{
								String ls_idPersona;

								ls_idPersona = lp_iterador.getIdPersona();

								if(StringUtils.isValidString(ls_idPersona))
								{
									Collection<PredioRegistro> lcpr_temp;

									lcpr_temp = lpr_DAO.findByPersonaPropietario(ls_idPersona);

									if(CollectionUtils.isValidCollection(lcpr_temp))
										lcpr_predios.addAll(lcpr_temp);
								}
							}
						}
					}
				}
				else if(ls_criterioBusqueda.equalsIgnoreCase(IdentificadoresCommon.DIRECCION))
				{
					Object lo_object;

					lo_object = new Gson().fromJson(atecdr_entrada.getValorCriterioBusqueda(), Object.class);

					if((lo_object != null) && (lo_object instanceof Map<?, ?>))
					{
						LinkedTreeMap<String, String> lltm_datos;

						lltm_datos = (LinkedTreeMap<String, String>)lo_object;

						if(CollectionUtils.isValidCollection(lltm_datos))
						{
							Direccion ld_direccion;
							String    ls_departamento;
							String    ls_municipio;
							String    ls_vereda;
							String    ls_idTipoEjePrincipal;
							String    ls_datoEjePrincipal;
							String    ls_idComplementoEjePrincipal;
							String    ls_coordenadaEjePrincipal;
							String    ls_idTipoEjeSecundario;
							String    ls_datoEjeSecundario;
							String    ls_idComplementoEjeSecundario;
							String    ls_coordenadaEjeSecundario;
							String    ls_idComplementoDireccion;
							String    ls_complementoDireccion;
							String    ls_direccionCompleta;

							ld_direccion                      = new Direccion();
							ls_departamento                   = lltm_datos.containsKey(
								    IdentificadoresCommon.DEPARTAMENTO
								) ? lltm_datos.get(IdentificadoresCommon.DEPARTAMENTO) : null;
							ls_municipio                      = lltm_datos.containsKey(IdentificadoresCommon.MUNICIPIO)
								? lltm_datos.get(IdentificadoresCommon.MUNICIPIO) : null;
							ls_vereda                         = lltm_datos.containsKey(IdentificadoresCommon.VEREDA)
								? lltm_datos.get(IdentificadoresCommon.VEREDA) : null;
							ls_idTipoEjePrincipal             = lltm_datos.containsKey(
								    IdentificadoresCommon.ID_TIPO_EJE_PRINCIPAL
								) ? lltm_datos.get(IdentificadoresCommon.ID_TIPO_EJE_PRINCIPAL) : null;
							ls_datoEjePrincipal               = lltm_datos.containsKey(
								    IdentificadoresCommon.DATO_EJE_PRINCIPAL
								) ? lltm_datos.get(IdentificadoresCommon.DATO_EJE_PRINCIPAL) : null;
							ls_idComplementoEjePrincipal      = lltm_datos.containsKey(
								    IdentificadoresCommon.ID_COMPLEMENTO_EJE_PRINCIPAL
								) ? lltm_datos.get(IdentificadoresCommon.ID_COMPLEMENTO_EJE_PRINCIPAL) : null;
							ls_coordenadaEjePrincipal         = lltm_datos.containsKey(
								    IdentificadoresCommon.COORDENADA_EJE_PRINCIPAL
								) ? lltm_datos.get(IdentificadoresCommon.COORDENADA_EJE_PRINCIPAL) : null;
							ls_idTipoEjeSecundario            = lltm_datos.containsKey(
								    IdentificadoresCommon.ID_TIPO_EJE_SECUNDARIO
								) ? lltm_datos.get(IdentificadoresCommon.ID_TIPO_EJE_SECUNDARIO) : null;
							ls_datoEjeSecundario              = lltm_datos.containsKey(
								    IdentificadoresCommon.DATO_EJE_SECUNDARIO
								) ? lltm_datos.get(IdentificadoresCommon.DATO_EJE_SECUNDARIO) : null;
							ls_idComplementoEjeSecundario     = lltm_datos.containsKey(
								    IdentificadoresCommon.ID_COMPLEMENTO_EJE_SECUNDARIO
								) ? lltm_datos.get(IdentificadoresCommon.ID_COMPLEMENTO_EJE_SECUNDARIO) : null;
							ls_coordenadaEjeSecundario        = lltm_datos.containsKey(
								    IdentificadoresCommon.COORDENADA_EJE_SECUNDARIO
								) ? lltm_datos.get(IdentificadoresCommon.COORDENADA_EJE_SECUNDARIO) : null;
							ls_idComplementoDireccion         = lltm_datos.containsKey(
								    IdentificadoresCommon.ID_COMPLEMENTO_DIRECCION
								) ? lltm_datos.get(IdentificadoresCommon.ID_COMPLEMENTO_DIRECCION) : null;
							ls_complementoDireccion           = lltm_datos.containsKey(
								    IdentificadoresCommon.COMPLEMENTO_DIRECCION
								) ? lltm_datos.get(IdentificadoresCommon.COMPLEMENTO_DIRECCION) : null;
							ls_direccionCompleta              = lltm_datos.containsKey(
								    IdentificadoresCommon.DIRECCION_COMPLETA
								) ? lltm_datos.get(IdentificadoresCommon.DIRECCION_COMPLETA) : null;

							if(!StringUtils.isValidString(ls_direccionCompleta))
							{
								if(!StringUtils.isValidString(ls_departamento))
								{
									ltscdrcm_codigo = TipoSalidaConsultarDatosRegistralesCodigoMensaje.value2;
									throw new B2BException(ErrorKeys.ERROR_DEPARTAMENTO_NO_VALIDO);
								}

								if(!StringUtils.isValidString(ls_municipio))
								{
									ltscdrcm_codigo = TipoSalidaConsultarDatosRegistralesCodigoMensaje.value2;
									throw new B2BException(ErrorKeys.ERROR_MUNICIPIO_NO_VALIDO);
								}

								if(!StringUtils.isValidString(ls_idTipoEjePrincipal))
								{
									ltscdrcm_codigo = TipoSalidaConsultarDatosRegistralesCodigoMensaje.value2;
									throw new B2BException(ErrorKeys.ERROR_TIPO_EJE_NO_VALIDO);
								}

								if(!StringUtils.isValidString(ls_datoEjePrincipal))
								{
									ltscdrcm_codigo = TipoSalidaConsultarDatosRegistralesCodigoMensaje.value2;
									throw new B2BException(ErrorKeys.ERROR_DATO_EJE_NO_VALIDO);
								}
							}

							ld_direccion.setIdPais(
							    com.bachue.snr.prosnr01.common.constants.IdentificadoresCommon.INDICATIVO_PAIS_DEFAULT
							);
							ld_direccion.setIdDepartamento(ls_departamento);
							ld_direccion.setIdMunicipio(ls_municipio);
							ld_direccion.setIdVereda(ls_vereda);
							ld_direccion.setIdTipoEjePrincipal(ls_idTipoEjePrincipal);
							ld_direccion.setDatoEjePrincipal(ls_datoEjePrincipal);
							ld_direccion.setIdComplementoEjePrincipal(ls_idComplementoEjePrincipal);
							ld_direccion.setIdCoordenadaEjePrincipal(ls_coordenadaEjePrincipal);
							ld_direccion.setIdTipoEjeSecundario(ls_idTipoEjeSecundario);
							ld_direccion.setDatoEjeSecundario(ls_datoEjeSecundario);
							ld_direccion.setIdComplemento1EjeSecundario(ls_idComplementoEjeSecundario);
							ld_direccion.setIdCoordenada1EjeSecundario(ls_coordenadaEjeSecundario);
							ld_direccion.setIdComplementoDireccion(ls_idComplementoDireccion);
							ld_direccion.setComplementoDireccion(ls_complementoDireccion);
							ld_direccion.setDireccion(ls_direccionCompleta);

							lcpr_predios = lpr_DAO.findByDatosRegistralesDireccion(ld_direccion);
						}
					}
				}

				if(CollectionUtils.isValidCollection(lcpr_predios))
				{
					AnotacionPredioDAO          lap_DAO;
					AnotacionPredioCiudadanoDAO lapc_DAO;
					Collection<Matricula>       lcm_matriculas;
					Iterator<PredioRegistro>    lipr_iterator;
					int                         li_cantidadRegistros;
					PropietarioDAO              lpd_propietarioDAO;

					lap_DAO                  = DaoCreator.getAnotacionPredioDAO(ldm_manager);
					lapc_DAO                 = DaoCreator.getAnotacionPredioCiudadanoDAO(ldm_manager);
					lipr_iterator            = lcpr_predios.iterator();
					li_cantidadRegistros     = lcpr_predios.size();
					lcm_matriculas           = new ArrayList<Matricula>();
					ls_cantidadRegistros     = StringUtils.getString(li_cantidadRegistros);
					lpd_propietarioDAO       = DaoCreator.getPropietarioDAO(ldm_manager);

					while(lipr_iterator.hasNext())
					{
						PredioRegistro lpr_iterador;

						lpr_iterador = lipr_iterator.next();

						if(lpr_iterador != null)
						{
							Long   ll_idMatricula;
							String ls_idCirculo;

							ll_idMatricula     = NumericUtils.getLongWrapper(lpr_iterador.getIdMatricula());
							ls_idCirculo       = lpr_iterador.getIdCirculo();

							if(NumericUtils.isValidLong(ll_idMatricula) && StringUtils.isValidString(ls_idCirculo))
							{
								Matricula lm_matricula;

								lm_matricula = lpr_DAO.consultaDatosRegistrales(ls_idCirculo, ll_idMatricula);

								if(lm_matricula != null)
								{
									Anotacion[]           laa_anotaciones;
									Collection<Anotacion> lca_anotacionesTemp;
									Collection<Titular>   lct_titulares;
									String                ls_numero;
									Titular[]             lta_titulares;

									laa_anotaciones         = new Anotacion[0];
									lca_anotacionesTemp     = lap_DAO.consultaDatosRegistrales(
										    ls_idCirculo, ll_idMatricula
										);
									lct_titulares           = lpd_propietarioDAO.findDataByIdCirculoMatriculaActivo(
										    ls_idCirculo, ll_idMatricula
										);
									ls_numero               = "0";
									lta_titulares           = new Titular[0];

									if(CollectionUtils.isValidCollection(lct_titulares))
										lta_titulares = lct_titulares.toArray(new Titular[lct_titulares.size()]);

									if(CollectionUtils.isValidCollection(lca_anotacionesTemp))
									{
										Iterator<Anotacion> lia_iterator;

										lia_iterator     = lca_anotacionesTemp.iterator();
										ls_numero        = StringUtils.getString(lca_anotacionesTemp.size());

										while(lia_iterator.hasNext())
										{
											Anotacion la_iterador;

											la_iterador = lia_iterator.next();

											if(la_iterador != null)
											{
												Collection<Interviniente> lci_intervinientesTemp;
												Interviniente[]           lia_intervinientes;

												lci_intervinientesTemp     = lapc_DAO.consultaDatosRegistrales(
													    ls_idCirculo, ll_idMatricula,
													    NumericUtils.getLongWrapper(
													        la_iterador.getConsecutivoAnotacion()
													    )
													);
												lia_intervinientes         = new Interviniente[0];

												if(CollectionUtils.isValidCollection(lci_intervinientesTemp))
													lia_intervinientes = lci_intervinientesTemp.toArray(
														    new Interviniente[lci_intervinientesTemp.size()]
														);

												la_iterador.setIntervinientes(lia_intervinientes);
											}
										}

										laa_anotaciones = lca_anotacionesTemp.toArray(
											    new Anotacion[lca_anotacionesTemp.size()]
											);
									}

									lm_matricula.setNumero(ls_numero);
									lm_matricula.setAnotaciones(laa_anotaciones);
									lm_matricula.setTitulares(lta_titulares);

									lcm_matriculas.add(lm_matricula);
								}
								else
									li_cantidadRegistros--;
							}
							else
								li_cantidadRegistros--;
						}
						else
							li_cantidadRegistros--;
					}

					if(CollectionUtils.isValidCollection(lcm_matriculas))
					{
						ls_cantidadRegistros     = StringUtils.getString(li_cantidadRegistros);
						lma_matriculas           = lcm_matriculas.toArray(new Matricula[lcm_matriculas.size()]);
					}
					else
					{
						ltscdrcm_codigo          = TipoSalidaConsultarDatosRegistralesCodigoMensaje.value2;
						ls_cantidadRegistros     = "0";

						throw new B2BException(ErrorKeys.NO_SE_ENCONTRARON_REGISTROS);
					}
				}
				else
				{
					ltscdrcm_codigo = TipoSalidaConsultarDatosRegistralesCodigoMensaje.value2;
					throw new B2BException(ErrorKeys.NO_SE_ENCONTRARON_REGISTROS);
				}
			}
			else
			{
				ltscdrcm_codigo = TipoSalidaConsultarDatosRegistralesCodigoMensaje.value2;
				throw new B2BException(ErrorKeys.ERROR_PARAMETROS_NO_VALIDOS);
			}
		}
		catch(B2BException lb2be_e)
		{
			ls_descripcionMensaje = addErrorCDR(lb2be_e.getMessageKey());

			clh_LOGGER.error("consultarDatosRegistrales", lb2be_e);
		}
		finally
		{
			ldm_manager.close();
		}

		ltscdr_return.setCantidadRegistros(ls_cantidadRegistros);
		ltscdr_return.setMatriculas(lma_matriculas);
		ltscdr_return.setCodigoMensaje(ltscdrcm_codigo);
		ltscdr_return.setDescripcionMensaje(ls_descripcionMensaje);

		return ltscdr_return;
	}
}
