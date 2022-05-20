package com.bachue.snr.prosnr17.business.solicitud.correccion;

import co.gov.supernotariado.www.schemas.bachue.ee.solicituddecorreccion.ingresosolicitud.v1.TipoEntradaIngresoSolicitud;
import co.gov.supernotariado.www.schemas.bachue.ee.solicituddecorreccion.ingresosolicitud.v1.TipoEntradaIngresoSolicitudInteresado;
import co.gov.supernotariado.www.schemas.bachue.ee.solicituddecorreccion.ingresosolicitud.v1.TipoEntradaIngresoSolicitudInteresadoTipoDocumentoPersona;
import co.gov.supernotariado.www.schemas.bachue.ee.solicituddecorreccion.ingresosolicitud.v1.TipoEntradaIngresoSolicitudMatriculasMatricula;
import co.gov.supernotariado.www.schemas.bachue.ee.solicituddecorreccion.ingresosolicitud.v1.TipoEntradaIngresoSolicitudMatriculasMatriculaDatosCorregirDatoCorregir;
import co.gov.supernotariado.www.schemas.bachue.ee.solicituddecorreccion.ingresosolicitud.v1.TipoSalidaIngresoSolicitud;
import co.gov.supernotariado.www.schemas.bachue.ee.solicituddecorreccion.ingresosolicitud.v1.TipoSalidaIngresoSolicitudCodigo;
import co.gov.supernotariado.www.schemas.bachue.ee.solicituddecorreccion.obtenercausalescorreccion.v1.TipoObtenerCausalesCorreccion;
import co.gov.supernotariado.www.schemas.bachue.ee.solicituddecorreccion.obtenercausalescorreccion.v1.TipoSalidaObtenerCausalesCorreccion;
import co.gov.supernotariado.www.schemas.bachue.ee.solicituddecorreccion.obtenercausalescorreccion.v1.TipoSalidaObtenerCausalesCorreccionCodigo;
import co.gov.supernotariado.www.schemas.bachue.ee.solicituddecorreccion.obtenercausalescorreccion.v1.TipoSalidaObtenerCausalesCorreccionListaCausalesCausal;

import com.b2bsg.common.dataAccess2.DAOManager;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.logging.LoggerHandler;

import com.b2bsg.common.util.CollectionUtils;
import com.b2bsg.common.util.NumericUtils;
import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.business.BaseBusiness;

import com.bachue.snr.prosnr01.common.constants.CalidadSolicitanteCommon;
import com.bachue.snr.prosnr01.common.constants.EstadoCommon;
import com.bachue.snr.prosnr01.common.constants.ExtensionCommon;
import com.bachue.snr.prosnr01.common.constants.IdentificadoresCommon;
import com.bachue.snr.prosnr01.common.constants.MedioNotificarCommon;
import com.bachue.snr.prosnr01.common.constants.MessagesKeys;
import com.bachue.snr.prosnr01.common.constants.ProcesoCommon;
import com.bachue.snr.prosnr01.common.constants.ProyectosCommon;
import com.bachue.snr.prosnr01.common.constants.RepositorioCommon;
import com.bachue.snr.prosnr01.common.constants.TipoArchivoCommon;
import com.bachue.snr.prosnr01.common.constants.TipoDocumentalCommon;
import com.bachue.snr.prosnr01.common.constants.TipoPersonaCommon;
import com.bachue.snr.prosnr01.common.constants.TipoRecepcionCommon;

import com.bachue.snr.prosnr01.dao.DaoCreator;
import com.bachue.snr.prosnr01.dao.DaoManagerFactory;
import com.bachue.snr.prosnr01.dao.acc.AccCompletitudDocumentalDAO;
import com.bachue.snr.prosnr01.dao.acc.PersonaCorreoElectronicoDAO;
import com.bachue.snr.prosnr01.dao.acc.PersonaDAO;
import com.bachue.snr.prosnr01.dao.acc.SolicitudCorreccionDAO;
import com.bachue.snr.prosnr01.dao.acc.SolicitudDAO;
import com.bachue.snr.prosnr01.dao.acc.SolicitudMatriculaDAO;
import com.bachue.snr.prosnr01.dao.bng.PredioRegistroDAO;
import com.bachue.snr.prosnr01.dao.pgn.CausalCorreccionDAO;
import com.bachue.snr.prosnr01.dao.proc.ProcedimientosDAO;

import com.bachue.snr.prosnr01.model.registro.Registro;
import com.bachue.snr.prosnr01.model.sdb.acc.AccCompletitudDocumental;
import com.bachue.snr.prosnr01.model.sdb.acc.DocumentosSalida;
import com.bachue.snr.prosnr01.model.sdb.acc.Persona;
import com.bachue.snr.prosnr01.model.sdb.acc.PersonaCorreoElectronico;
import com.bachue.snr.prosnr01.model.sdb.acc.Solicitud;
import com.bachue.snr.prosnr01.model.sdb.acc.SolicitudCorreccion;
import com.bachue.snr.prosnr01.model.sdb.acc.SolicitudMatricula;
import com.bachue.snr.prosnr01.model.sdb.acc.TurnoHistoria;
import com.bachue.snr.prosnr01.model.sdb.bng.Imagenes;
import com.bachue.snr.prosnr01.model.sdb.bng.PredioRegistro;
import com.bachue.snr.prosnr01.model.sdb.pgn.CirculoRegistral;

import com.bachue.snr.prosnr17.common.constants.ErrorKeys;

import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.Map;


/**
 * Clase que contiene todos las funcionalidades para solicitud de corrección
 *
 * @author Gabriel Arias
 */
public class SolicitudCorreccionBusiness extends BaseBusiness
{
	/** Constante clh_LOGGER. */
	private static final LoggerHandler clh_LOGGER = (LoggerHandler)com.bachue.snr.prosnr01.common.utils.LoggingHelper
			.getLogger(SolicitudCorreccionBusiness.class, ProyectosCommon.SOLICITUD_DE_CORRECCION_17);

	/**
	 *
	 * @param atocc_data Objeto que contiene la información de entrada.
	 * @param as_userId Variable que contiene el id del usuario.
	 * @param as_remoteIp Variable que contiene la ip del usuario.
	 * @return Objeto que contiene la información de salida.
	 * @throws B2BException
	 */
	public synchronized TipoSalidaObtenerCausalesCorreccion consultarDatosRegistrales(
	    TipoObtenerCausalesCorreccion atocc_data, String as_userId, String as_remoteIp
	)
	    throws B2BException
	{
		DAOManager                                               ldm_manager;
		TipoSalidaObtenerCausalesCorreccionListaCausalesCausal[] ltsocclcc_data;
		String                                                   ls_descripcionMensaje;
		TipoSalidaObtenerCausalesCorreccionCodigo                ltsoccc_codigo;
		TipoSalidaObtenerCausalesCorreccion                      ltsocc_return;

		ldm_manager               = DaoManagerFactory.getDAOManager();
		ltsocclcc_data            = new TipoSalidaObtenerCausalesCorreccionListaCausalesCausal[0];
		ls_descripcionMensaje     = addMessage(MessagesKeys.OK);
		ltsoccc_codigo            = TipoSalidaObtenerCausalesCorreccionCodigo.value1;
		ltsocc_return             = new TipoSalidaObtenerCausalesCorreccion();

		try
		{
			if(atocc_data != null)
			{
				Collection<TipoSalidaObtenerCausalesCorreccionListaCausalesCausal> lctsocclcc_data;

				lctsocclcc_data = DaoCreator.getCausalCorreccionDAO(ldm_manager).findAllData();

				if(CollectionUtils.isValidCollection(lctsocclcc_data))
					ltsocclcc_data = lctsocclcc_data.toArray(
						    new TipoSalidaObtenerCausalesCorreccionListaCausalesCausal[lctsocclcc_data.size()]
						);
				else
				{
					ltsoccc_codigo = TipoSalidaObtenerCausalesCorreccionCodigo.value2;
					throw new B2BException(ErrorKeys.ERROR_NO_REGISTROS);
				}
			}
			else
			{
				ltsoccc_codigo = TipoSalidaObtenerCausalesCorreccionCodigo.value3;
				throw new B2BException(ErrorKeys.ERROR_PARAMETROS_NO_VALIDOS);
			}
		}
		catch(B2BException lb2be_e)
		{
			ls_descripcionMensaje = addErrorSC(lb2be_e.getMessageKey());

			clh_LOGGER.error("consultarDatosRegistrales", lb2be_e);
		}
		finally
		{
			ldm_manager.close();
		}

		ltsocc_return.setCodigo(ltsoccc_codigo);
		ltsocc_return.setMensaje(ls_descripcionMensaje);
		ltsocc_return.setListaCausales(ltsocclcc_data);

		return ltsocc_return;
	}

	/**
	 *
	 * @param ateis_data Objeto que contiene la información de entrada.
	 * @param as_userId Variable que contiene el id del usuario.
	 * @param as_remoteIp Variable que contiene la ip del usuario.
	 * @return Objeto que contiene la información de salida.
	 * @throws Exception
	 */
	public synchronized TipoSalidaIngresoSolicitud ingresoSolicitud(
	    TipoEntradaIngresoSolicitud ateis_data, String as_userId, String as_remoteIp
	)
	    throws Exception
	{
		DAOManager                       ldm_manager;
		String                           ls_nir;
		String                           ls_descripcionMensaje;
		TipoSalidaIngresoSolicitudCodigo ltsisc_codigo;
		TipoSalidaIngresoSolicitud       ltsis_return;

		ldm_manager               = DaoManagerFactory.getDAOManager();
		ls_nir                    = "";
		ls_descripcionMensaje     = addMessage(MessagesKeys.OK);
		ltsisc_codigo             = TipoSalidaIngresoSolicitudCodigo.value1;
		ltsis_return              = new TipoSalidaIngresoSolicitud();

		try
		{
			if(ateis_data != null)
			{
				TipoEntradaIngresoSolicitudInteresado[] lteisia_data;

				lteisia_data = ateis_data.getInteresado();

				if(CollectionUtils.isValidCollection(lteisia_data))
				{
					Solicitud                             ls_solicitud;
					String                                ls_idSolicitud;
					TipoEntradaIngresoSolicitudInteresado lteisi_iteresado;

					ls_solicitud         = new Solicitud();
					ls_idSolicitud       = null;
					lteisi_iteresado     = ateis_data.getInteresado(0);

					if(lteisi_iteresado != null)
					{
						Persona                                                   lp_persona;
						PersonaDAO                                                lp_DAO;
						PersonaCorreoElectronicoDAO                               lpc_DAO;
						SolicitudDAO                                              ls_DAO;
						String                                                    ls_tipoDocumentoPersona;
						String                                                    ls_numDocumentoPersona;
						String                                                    ls_primerApellido;
						String                                                    ls_segundoApellido;
						String                                                    ls_primerNombre;
						String                                                    ls_segundoNombre;
						String                                                    ls_codGenero;
						String                                                    ls_email;
						String                                                    ls_observacion;
						String                                                    ls_idPersona;
						String                                                    ls_idCorreo;
						TipoEntradaIngresoSolicitudInteresadoTipoDocumentoPersona lo_temp;

						lp_DAO                      = DaoCreator.getPersonaDAO(ldm_manager);
						lpc_DAO                     = DaoCreator.getPersonaCorreoElectronicoDAO(ldm_manager);
						ls_DAO                      = DaoCreator.getSolicitudDAO(ldm_manager);
						ls_tipoDocumentoPersona     = null;
						lo_temp                     = lteisi_iteresado.getTipoDocumentoPersona();
						ls_numDocumentoPersona      = lteisi_iteresado.getNumDocumentoPersona();
						ls_primerApellido           = lteisi_iteresado.getPrimerApellido();
						ls_segundoApellido          = lteisi_iteresado.getSegundoApellido();
						ls_primerNombre             = lteisi_iteresado.getPrimerNombre();
						ls_segundoNombre            = lteisi_iteresado.getSegundoNombre();
						ls_codGenero                = lteisi_iteresado.getCodGenero();
						ls_email                    = lteisi_iteresado.getEmail();
						ls_observacion              = ateis_data.getObservacion();
						ls_idPersona                = null;
						ls_idCorreo                 = null;

						if(lo_temp != null)
						{
							ls_tipoDocumentoPersona = lo_temp.getValue();

							if(!StringUtils.isValidString(ls_tipoDocumentoPersona))
							{
								ltsisc_codigo = TipoSalidaIngresoSolicitudCodigo.value2;
								throw new B2BException(ErrorKeys.ERROR_TIPO_DOCUMENTO);
							}
						}
						else
						{
							ltsisc_codigo = TipoSalidaIngresoSolicitudCodigo.value2;
							throw new B2BException(ErrorKeys.ERROR_TIPO_DOCUMENTO);
						}

						if(!StringUtils.isValidString(ls_numDocumentoPersona))
						{
							ltsisc_codigo = TipoSalidaIngresoSolicitudCodigo.value2;
							throw new B2BException(ErrorKeys.ERROR_NUMERO_DOCUMENTO);
						}

						if(!StringUtils.isValidString(ls_primerApellido))
						{
							ltsisc_codigo = TipoSalidaIngresoSolicitudCodigo.value2;
							throw new B2BException(ErrorKeys.ERROR_PRIMER_APELLIDO);
						}

						if(!StringUtils.isValidString(ls_primerNombre))
						{
							ltsisc_codigo = TipoSalidaIngresoSolicitudCodigo.value2;
							throw new B2BException(ErrorKeys.ERROR_PRIMER_NOMBRE);
						}

						if(
						    !(StringUtils.isValidString(ls_codGenero)
							    && (ls_codGenero.equalsIgnoreCase(IdentificadoresCommon.M)
							    || ls_codGenero.equalsIgnoreCase(IdentificadoresCommon.O)
							    || ls_codGenero.equalsIgnoreCase(IdentificadoresCommon.N)
							    || ls_codGenero.equalsIgnoreCase(IdentificadoresCommon.F)))
						)
						{
							ltsisc_codigo = TipoSalidaIngresoSolicitudCodigo.value2;
							throw new B2BException(ErrorKeys.ERROR_CODIGO_GENERO);
						}

						if(!validarCorreoElectronico(ls_email))
						{
							ltsisc_codigo = TipoSalidaIngresoSolicitudCodigo.value2;
							throw new B2BException(ErrorKeys.ERROR_EMAIL);
						}

						if(!StringUtils.isValidString(ls_observacion))
						{
							ltsisc_codigo = TipoSalidaIngresoSolicitudCodigo.value2;
							throw new B2BException(ErrorKeys.ERROR_OBSERVACION);
						}

						lp_persona = lp_DAO.findByData(
							    ls_primerNombre, ls_segundoNombre, ls_primerApellido, ls_segundoApellido,
							    ls_tipoDocumentoPersona, ls_numDocumentoPersona, ls_codGenero
							);

						if(lp_persona == null)
						{
							lp_persona = new Persona();

							lp_persona.setPrimerNombre(ls_primerNombre);
							lp_persona.setSegundoNombre(ls_segundoNombre);
							lp_persona.setPrimerApellido(ls_primerApellido);
							lp_persona.setSegundoApellido(ls_segundoApellido);
							lp_persona.setIdDocumentoTipo(ls_tipoDocumentoPersona);
							lp_persona.setNumeroDocumento(ls_numDocumentoPersona);
							lp_persona.setIdNaturalGenero(ls_codGenero);
							lp_persona.setIdTipoPersona(TipoPersonaCommon.NATURAL);
							lp_persona.setIdPais(IdentificadoresCommon.INDICATIVO_PAIS_DEFAULT);
							lp_persona.setIdUsuarioCreacion(as_userId);
							lp_persona.setIpCreacion(as_remoteIp);

							lp_persona = lp_DAO.insertOrUpdate(lp_persona, true);

							if(lp_persona != null)
							{
								long                     ll_idCorreo;
								PersonaCorreoElectronico lpc_correo;

								lpc_correo       = new PersonaCorreoElectronico();
								ls_idPersona     = lp_persona.getIdPersona();

								lpc_correo.setIdPersona(ls_idPersona);
								lpc_correo.setCorreoElectronico(ls_email);
								lpc_correo.setIdUsuarioCreacion(as_userId);
								lpc_correo.setIpCreacion(as_remoteIp);
								lpc_correo.setFechaDesde(new Date());

								ll_idCorreo = lpc_DAO.insertOrUpdate(lpc_correo, true);

								if(ll_idCorreo > 0L)
									ls_idCorreo = StringUtils.getString(ll_idCorreo);
								else
								{
									ltsisc_codigo = TipoSalidaIngresoSolicitudCodigo.value2;
									throw new B2BException(ErrorKeys.ERROR_CORREO_INSERT);
								}
							}
							else
							{
								ltsisc_codigo = TipoSalidaIngresoSolicitudCodigo.value2;
								throw new B2BException(ErrorKeys.ERROR_PERSONA_INSERT);
							}
						}
						else
						{
							PersonaCorreoElectronico lpc_correo;

							ls_idPersona     = lp_persona.getIdPersona();
							lpc_correo       = lpc_DAO.findByIdPersonaCorreoMax(ls_idPersona, ls_email);

							if(lpc_correo != null)
								ls_idCorreo = lpc_correo.getIdCorreoElectronico();
							else
							{
								long ll_idCorreo;

								lpc_correo       = new PersonaCorreoElectronico();
								ls_idPersona     = lp_persona.getIdPersona();

								lpc_correo.setIdPersona(ls_idPersona);
								lpc_correo.setCorreoElectronico(ls_email);
								lpc_correo.setIdUsuarioCreacion(as_userId);
								lpc_correo.setIpCreacion(as_remoteIp);

								ll_idCorreo = lpc_DAO.insertOrUpdate(lpc_correo, true);

								if(ll_idCorreo > 0L)
									ls_idCorreo = StringUtils.getString(ll_idCorreo);
								else
								{
									ltsisc_codigo = TipoSalidaIngresoSolicitudCodigo.value2;
									throw new B2BException(ErrorKeys.ERROR_CORREO_INSERT);
								}
							}
						}

						ls_nir = crearNir(as_userId, as_remoteIp, ldm_manager);

						if(StringUtils.isValidString(ls_nir))
						{
							int li_secuencia;

							li_secuencia = ls_DAO.findSecuence();

							if(li_secuencia > 0)
							{
								ls_idSolicitud = StringUtils.getString(li_secuencia);

								ls_solicitud.setIdSolicitud(ls_idSolicitud);
								ls_solicitud.setIdProceso(ProcesoCommon.ID_PROCESO_3);
								ls_solicitud.setIdSubproceso(ProcesoCommon.ID_SUBPROCESO_1);
								ls_solicitud.setFechaSolicitud(new Date());
								ls_solicitud.setIdPersona(ls_idPersona);
								ls_solicitud.setDescripcion(ls_observacion);
								ls_solicitud.setIdTipoRecepcion(TipoRecepcionCommon.SEDE_ELECTRONICA);
								ls_solicitud.setIdCalidadSolicitante(CalidadSolicitanteCommon.INTERESADO);
								ls_solicitud.setParticipaInterviniente(EstadoCommon.S);
								ls_solicitud.setDerechoPeticion(EstadoCommon.N);
								ls_solicitud.setDigitalizada(EstadoCommon.N);
								ls_solicitud.setIdAutorizacionComunicacion(MedioNotificarCommon.CORREO_ELECTRONICO);
								ls_solicitud.setIdAutorizacionNotificacion(MedioNotificarCommon.CORREO_ELECTRONICO);
								ls_solicitud.setIdCorreoElectronico(ls_idCorreo);
								ls_solicitud.setIdCorreoElectronicoComunicacion(ls_idCorreo);
								ls_solicitud.setNir(ls_nir);
								ls_solicitud.setIdUsuarioCreacion(as_userId);
								ls_solicitud.setIpCreacion(as_remoteIp);

								ls_DAO.insertOrUpdate(ls_solicitud, true);
							}
							else
							{
								ltsisc_codigo = TipoSalidaIngresoSolicitudCodigo.value2;
								throw new B2BException(ErrorKeys.ERROR_SOLICITUD_INSERT);
							}
						}
						else
						{
							ltsisc_codigo = TipoSalidaIngresoSolicitudCodigo.value2;
							throw new B2BException(ErrorKeys.ERROR_GENERANDO_NIR);
						}
					}

					if(StringUtils.isValidString(ls_idSolicitud) && StringUtils.isValidString(ls_nir))
					{
						TipoEntradaIngresoSolicitudMatriculasMatricula[] lteismma_data;

						lteismma_data = ateis_data.getMatriculas();

						if(CollectionUtils.isValidCollection(lteismma_data))
						{
							Collection<TipoEntradaIngresoSolicitudMatriculasMatricula> lcteismm_matriculas;

							lcteismm_matriculas = Arrays.asList(lteismma_data);

							if(CollectionUtils.isValidCollection(lcteismm_matriculas))
							{
								CausalCorreccionDAO    lcc_DAO;
								PredioRegistroDAO      lpr_DAO;
								SolicitudCorreccionDAO lsc_DAO;
								SolicitudMatriculaDAO  lsm_DAO;

								lcc_DAO     = DaoCreator.getCausalCorreccionDAO(ldm_manager);
								lpr_DAO     = DaoCreator.getPredioRegistroDAO(ldm_manager);
								lsc_DAO     = DaoCreator.getSolicitudCorreccionDAO(ldm_manager);
								lsm_DAO     = DaoCreator.getSolicitudMatriculaDAO(ldm_manager);

								for(TipoEntradaIngresoSolicitudMatriculasMatricula lteismm_iterador : lcteismm_matriculas)
								{
									if(lteismm_iterador != null)
									{
										String ls_idCirculo;
										long   ll_idMatricula;

										ls_idCirculo       = lteismm_iterador.getCodCirculoRegistral();
										ll_idMatricula     = NumericUtils.getLong(
											    lteismm_iterador.getNumMatriculaInmobiliaria()
											);

										if(StringUtils.isValidString(ls_idCirculo))
										{
											CirculoRegistral lcr_circuloRegistral;

											lcr_circuloRegistral = DaoCreator.getCirculoRegistralDAO(ldm_manager)
													                             .findById(ls_idCirculo);

											if(lcr_circuloRegistral == null)
											{
												ltsisc_codigo = TipoSalidaIngresoSolicitudCodigo.value2;
												throw new B2BException(ErrorKeys.ERROR_CIRCULO_NO_VALIDO);
											}
										}
										else
										{
											ltsisc_codigo = TipoSalidaIngresoSolicitudCodigo.value2;
											throw new B2BException(ErrorKeys.ERROR_CIRCULO_NO_VALIDO);
										}

										if(ll_idMatricula <= 0)
										{
											ltsisc_codigo = TipoSalidaIngresoSolicitudCodigo.value2;
											throw new B2BException(ErrorKeys.ERROR_MATRICULA_NO_VALIDA);
										}

										{
											PredioRegistro lpr_predio;

											lpr_predio = new PredioRegistro();

											lpr_predio.setValidMatricula(true);
											lpr_predio.setIdCirculo(ls_idCirculo);
											lpr_predio.setIdMatricula(NumericUtils.getLong(ll_idMatricula));

											lpr_predio = lpr_DAO.findById(lpr_predio);

											if(lpr_predio != null)
											{
												TipoEntradaIngresoSolicitudMatriculasMatriculaDatosCorregirDatoCorregir[] lteismmdcdca_data;

												lteismmdcdca_data = lteismm_iterador.getDatosCorregir();

												if(CollectionUtils.isValidCollection(lteismmdcdca_data))
												{
													Collection<TipoEntradaIngresoSolicitudMatriculasMatriculaDatosCorregirDatoCorregir> lcteismmdcdc_correcciones;
													Map<String, SolicitudCorreccion>                                                    lmsc_data;
													Map<String, SolicitudCorreccion>                                                    lmsc_dataInsert;

													lcteismmdcdc_correcciones     = Arrays.asList(lteismmdcdca_data);
													lmsc_data                     = lcc_DAO.findAllActivoMap();
													lmsc_dataInsert               = lcc_DAO.findAllActivoMap();

													if(
													    CollectionUtils.isValidCollection(lcteismmdcdc_correcciones)
														    && CollectionUtils.isValidCollection(lmsc_data)
														    && CollectionUtils.isValidCollection(lmsc_dataInsert)
													)
													{
														for(TipoEntradaIngresoSolicitudMatriculasMatriculaDatosCorregirDatoCorregir lteismmdcdc_iterador : lcteismmdcdc_correcciones)
														{
															if(lteismmdcdc_iterador != null)
															{
																String ls_idCausalCorreccion;
																String ls_grupoCausal;
																String ls_observacion;

																ls_idCausalCorreccion     = lteismmdcdc_iterador
																		.getIdCausalCorreccion();
																ls_grupoCausal            = lteismmdcdc_iterador
																		.getIdGrupoCausal();
																ls_observacion            = lteismmdcdc_iterador
																		.getObservacion();

																if(!StringUtils.isValidString(ls_idCausalCorreccion))
																{
																	ltsisc_codigo = TipoSalidaIngresoSolicitudCodigo.value2;
																	throw new B2BException(
																	    ErrorKeys.ERROR_CAUSAL_CORRECCION
																	);
																}

																if(!StringUtils.isValidString(ls_grupoCausal))
																{
																	ltsisc_codigo = TipoSalidaIngresoSolicitudCodigo.value2;
																	throw new B2BException(
																	    ErrorKeys.ERROR_GRUPO_CAUSAL
																	);
																}

																if(!StringUtils.isValidString(ls_observacion))
																{
																	ltsisc_codigo = TipoSalidaIngresoSolicitudCodigo.value2;
																	throw new B2BException(ErrorKeys.ERROR_OBSERVACION);
																}

																if(
																    lmsc_data.containsKey(ls_idCausalCorreccion)
																	    && lmsc_dataInsert.containsKey(
																	        ls_idCausalCorreccion
																	    )
																)
																{
																	SolicitudCorreccion lsc_data;

																	lsc_data = lmsc_data.get(ls_idCausalCorreccion);

																	if(lsc_data != null)
																	{
																		String ls_grupoCausalConsultado;

																		ls_grupoCausalConsultado = lsc_data
																				.getGrupoCausal();

																		if(
																		    StringUtils.isValidString(
																			        ls_grupoCausalConsultado
																			    )
																			    && ls_grupoCausalConsultado
																			    .equalsIgnoreCase(ls_grupoCausal)
																		)
																		{
																			lsc_data.setSeleccionado(EstadoCommon.S);
																			lsc_data.setObservacion(ls_observacion);

																			lmsc_dataInsert.replace(
																			    ls_idCausalCorreccion, lsc_data
																			);
																		}
																		else
																		{
																			ltsisc_codigo = TipoSalidaIngresoSolicitudCodigo.value2;
																			throw new B2BException(
																			    ErrorKeys.ERROR_GRUPO_CAUSAL
																			);
																		}
																	}
																}
																else
																{
																	ltsisc_codigo = TipoSalidaIngresoSolicitudCodigo.value2;
																	throw new B2BException(
																	    ErrorKeys.ERROR_CAUSAL_CORRECCION
																	);
																}
															}
															else
															{
																ltsisc_codigo = TipoSalidaIngresoSolicitudCodigo.value2;
																throw new B2BException(
																    ErrorKeys.ERROR_PARAMETROS_NO_VALIDOS
																);
															}
														}

														for(Map.Entry<String, SolicitudCorreccion> lmsc_iterador : lmsc_dataInsert
															    .entrySet())
														{
															if(lmsc_iterador != null)
															{
																SolicitudCorreccion lsc_insert;

																lsc_insert = lmsc_iterador.getValue();

																if(lsc_insert != null)
																{
																	lsc_insert.setIdSolicitud(ls_idSolicitud);
																	lsc_insert.setIdCirculo(ls_idCirculo);
																	lsc_insert.setIdMatricula(
																	    NumericUtils.getBigInteger(ll_idMatricula)
																	);
																	lsc_insert.setIdCausalCorreccion(
																	    NumericUtils.getBigInteger(
																	        lmsc_iterador.getKey()
																	    )
																	);
																	lsc_insert.setIdUsuarioCreacion(as_userId);
																	lsc_insert.setIpCreacion(as_remoteIp);

																	lsc_DAO.insertOrUpdate(lsc_insert, true);
																}
																else
																{
																	ltsisc_codigo = TipoSalidaIngresoSolicitudCodigo.value2;
																	throw new B2BException(
																	    ErrorKeys.ERROR_PARAMETROS_NO_VALIDOS
																	);
																}
															}
															else
															{
																ltsisc_codigo = TipoSalidaIngresoSolicitudCodigo.value2;
																throw new B2BException(
																    ErrorKeys.ERROR_PARAMETROS_NO_VALIDOS
																);
															}
														}
													}
													else
													{
														ltsisc_codigo = TipoSalidaIngresoSolicitudCodigo.value2;
														throw new B2BException(ErrorKeys.ERROR_PARAMETROS_NO_VALIDOS);
													}
												}
												else
												{
													ltsisc_codigo = TipoSalidaIngresoSolicitudCodigo.value2;
													throw new B2BException(ErrorKeys.ERROR_PARAMETROS_NO_VALIDOS);
												}
											}
											else
											{
												ltsisc_codigo = TipoSalidaIngresoSolicitudCodigo.value2;
												throw new B2BException(ErrorKeys.ERROR_MATRICULA_NO_VALIDA);
											}
										}

										if(
										    StringUtils.isValidString(ls_idSolicitud)
											    && StringUtils.isValidString(ls_idSolicitud)
											    && StringUtils.isValidString(ls_idCirculo)
										)
										{
											SolicitudMatricula ls_solicitudMatricula;
											ls_solicitudMatricula = new SolicitudMatricula();
											ls_solicitudMatricula.setIdSolicitud(ls_idSolicitud);
											ls_solicitudMatricula.setIdCirculo(ls_idCirculo);
											ls_solicitudMatricula.setIdMatricula(NumericUtils.getLong(ll_idMatricula));
											ls_solicitudMatricula.setIdUsuarioCreacion(as_userId);
											ls_solicitudMatricula.setExpedirCertificado("N");
											ls_solicitudMatricula.setIpCreacion(as_remoteIp);

											lsm_DAO.insertOrUpdate(ls_solicitudMatricula, true);
										}
									}
								}
							}
						}
					}
					else
					{
						ltsisc_codigo = TipoSalidaIngresoSolicitudCodigo.value2;
						throw new B2BException(ErrorKeys.ERROR_GENERANDO_NIR);
					}

					{
						Collection<AccCompletitudDocumental> lccd_data;

						lccd_data = getReferenceBusiness()
								            .findAllTipoCompletitudProceso(ls_idSolicitud, ProcesoCommon.ID_PROCESO_3);

						if(CollectionUtils.isValidCollection(lccd_data))
						{
							AccCompletitudDocumentalDAO lcd_DAO;

							lcd_DAO = DaoCreator.getAccCompletitudDocumentalDAO(ldm_manager);

							for(AccCompletitudDocumental lcd_iterador : lccd_data)
							{
								if(lcd_iterador != null)
								{
									lcd_iterador.setIdSolicitud(ls_idSolicitud);
									lcd_iterador.setObligatorio(EstadoCommon.S);
									lcd_iterador.setObligatorioTipoDocumental(EstadoCommon.S);
									lcd_iterador.setMedioRecepcion("DIGITAL");
									lcd_iterador.setIdUsuarioCreacion(as_userId);
									lcd_iterador.setIpCreacion(as_remoteIp);
									lcd_iterador.setDigitalizado(EstadoCommon.N);

									lcd_DAO.insert(lcd_iterador);
								}
								else
								{
									ltsisc_codigo = TipoSalidaIngresoSolicitudCodigo.value2;
									throw new B2BException(ErrorKeys.ERROR_PARAMETROS_NO_VALIDOS);
								}
							}
						}
						else
						{
							ltsisc_codigo = TipoSalidaIngresoSolicitudCodigo.value2;
							throw new B2BException(ErrorKeys.ERROR_PARAMETROS_NO_VALIDOS);
						}
					}

					{
						byte[]   lba_pdf;
						Registro lr_registro;

						lr_registro = new Registro();

						lr_registro.setSolicitud(ls_solicitud);

						lba_pdf = getRegistroBusiness().crearPdfCorrecciones(lr_registro, as_userId, ldm_manager);

						if(lba_pdf != null)
						{
							Imagenes         li_imagenes;
							DocumentosSalida lds_documentosSalida;
							long             ll_secuencia;

							li_imagenes              = new Imagenes();
							lds_documentosSalida     = new DocumentosSalida();

							li_imagenes.setImagenBlob(lba_pdf);

							li_imagenes.setTipoArchivo(ExtensionCommon.PDF_MAYUS);
							li_imagenes.setIdUsuarioCreacion(as_userId);
							li_imagenes.setIpCreacion(as_remoteIp);

							ll_secuencia = DaoCreator.getImagenesDAO(ldm_manager).insertOrUpdate(li_imagenes, true);

							if(ll_secuencia <= 0)
							{
								ltsisc_codigo = TipoSalidaIngresoSolicitudCodigo.value2;
								throw new B2BException(ErrorKeys.ERROR_IMAGEN_INSERT);
							}

							lds_documentosSalida.setIdImagen(NumericUtils.getLongWrapper(ll_secuencia));
							lds_documentosSalida.setTipo(TipoArchivoCommon.SOLICITUD_CORRECCION);
							lds_documentosSalida.setEstado(EstadoCommon.ACTIVO);
							lds_documentosSalida.setIdSolicitud(ls_solicitud.getIdSolicitud());
							lds_documentosSalida.setIdTipoDocumental(TipoDocumentalCommon.RESUMEN_DE_LA_SOLICITUD);
							lds_documentosSalida.setRepositorio(RepositorioCommon.FINAL);
							lds_documentosSalida.setIdUsuarioCreacion(as_userId);
							lds_documentosSalida.setIpCreacion(as_remoteIp);

							DaoCreator.getDocumentosSalidaDAO(ldm_manager).insertOrUpdate(lds_documentosSalida, true);
						}
						else
						{
							ltsisc_codigo = TipoSalidaIngresoSolicitudCodigo.value2;
							throw new B2BException(ErrorKeys.ERROR_GENERANDO_PDF);
						}

						{
							ProcedimientosDAO lp_procedimiento;
							lp_procedimiento = DaoCreator.getProcedimientosDAO(ldm_manager);

							TurnoHistoria lth_turnoHistoria;
							lth_turnoHistoria = new TurnoHistoria();
							lth_turnoHistoria.setIdSolicitud(ls_solicitud.getIdSolicitud());
							lth_turnoHistoria.setIdUsuarioModificacion(as_userId);
							lth_turnoHistoria.setIpModificacion(as_remoteIp);
							lp_procedimiento.spCreateStage(lth_turnoHistoria);
						}
					}
				}
			}
			else
			{
				ltsisc_codigo = TipoSalidaIngresoSolicitudCodigo.value2;
				throw new B2BException(ErrorKeys.ERROR_PARAMETROS_NO_VALIDOS);
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			ls_nir                    = "";
			ls_descripcionMensaje     = addErrorSC(lb2be_e.getMessageKey());

			clh_LOGGER.error("ingresoSolicitud", lb2be_e);
		}
		finally
		{
			ldm_manager.close();
		}

		ltsis_return.setCodigo(ltsisc_codigo);
		ltsis_return.setMensaje(ls_descripcionMensaje);
		ltsis_return.setNIR(ls_nir);

		return ltsis_return;
	}
}
