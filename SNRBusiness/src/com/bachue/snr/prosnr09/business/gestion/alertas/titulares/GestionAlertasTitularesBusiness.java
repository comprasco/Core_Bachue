package com.bachue.snr.prosnr09.business.gestion.alertas.titulares;

import co.gov.supernotariado.www.schemas.bachue.cb.gestionalertastitulares.actualizarcontactoalerta.v1.TipoEntradaActualizarContactoAlerta;
import co.gov.supernotariado.www.schemas.bachue.cb.gestionalertastitulares.actualizarcontactoalerta.v1.TipoEntradaActualizarContactoAlertaTipoDocumento;
import co.gov.supernotariado.www.schemas.bachue.cb.gestionalertastitulares.actualizarcontactoalerta.v1.TipoSalidaActualizarContactoAlerta;
import co.gov.supernotariado.www.schemas.bachue.cb.gestionalertastitulares.actualizarcontactoalerta.v1.TipoSalidaActualizarContactoAlertaCodigoMensaje;
import co.gov.supernotariado.www.schemas.bachue.cb.gestionalertastitulares.consultaralerta.v1.TipoEntradaConsultarAlerta;
import co.gov.supernotariado.www.schemas.bachue.cb.gestionalertastitulares.consultaralerta.v1.TipoEntradaConsultarAlertaTipoDocumento;
import co.gov.supernotariado.www.schemas.bachue.cb.gestionalertastitulares.consultaralerta.v1.TipoSalidaConsultarAlerta;
import co.gov.supernotariado.www.schemas.bachue.cb.gestionalertastitulares.consultaralerta.v1.TipoSalidaConsultarAlertaCodigoMensaje;
import co.gov.supernotariado.www.schemas.bachue.cb.gestionalertastitulares.consultaralerta.v1.TiposAlertas;
import co.gov.supernotariado.www.schemas.bachue.cb.gestionalertastitulares.consultartarifaalertastitulares.v1.TipoEntradaConsultarTarifaAlertasTitulares;
import co.gov.supernotariado.www.schemas.bachue.cb.gestionalertastitulares.consultartarifaalertastitulares.v1.TipoSalidaConsultarTarifaAlertasTitulares;
import co.gov.supernotariado.www.schemas.bachue.cb.gestionalertastitulares.consultartarifaalertastitulares.v1.TipoSalidaConsultarTarifaAlertasTitularesCodigoMensaje;
import co.gov.supernotariado.www.schemas.bachue.cb.gestionalertastitulares.consultartarifaalertastitulares.v1.TiposTarifas;
import co.gov.supernotariado.www.schemas.bachue.cb.gestionalertastitulares.inactivaralerta.v1.TipoEntradaInactivarAlerta;
import co.gov.supernotariado.www.schemas.bachue.cb.gestionalertastitulares.inactivaralerta.v1.TipoEntradaInactivarAlertaTipoDocumento;
import co.gov.supernotariado.www.schemas.bachue.cb.gestionalertastitulares.inactivaralerta.v1.TipoSalidaInactivarAlerta;
import co.gov.supernotariado.www.schemas.bachue.cb.gestionalertastitulares.inactivaralerta.v1.TipoSalidaInactivarAlertaCodigoMensaje;
import co.gov.supernotariado.www.schemas.bachue.cb.gestionalertastitulares.validarsolicitudalertaindividual.v1.TipoEntradaValidarSolicitudAlertaIndividual;
import co.gov.supernotariado.www.schemas.bachue.cb.gestionalertastitulares.validarsolicitudalertaindividual.v1.TipoSalidaValidarSolicitudAlertaIndividual;
import co.gov.supernotariado.www.schemas.bachue.cb.gestionalertastitulares.validarsolicitudalertaindividual.v1.TipoSalidaValidarSolicitudAlertaIndividualCodigoMensaje;
import co.gov.supernotariado.www.schemas.bachue.cb.gestionalertastitulares.validarsolicitudalertamasiva.v1.AlertasMensajeEstado;
import co.gov.supernotariado.www.schemas.bachue.cb.gestionalertastitulares.validarsolicitudalertamasiva.v1.TipoEntradaValidarSolicitudAlertaMasiva;
import co.gov.supernotariado.www.schemas.bachue.cb.gestionalertastitulares.validarsolicitudalertamasiva.v1.TipoEntradaValidarSolicitudAlertaMasivaTipoDocumento;
import co.gov.supernotariado.www.schemas.bachue.cb.gestionalertastitulares.validarsolicitudalertamasiva.v1.TipoSalidaValidarSolicitudAlertaMasiva;
import co.gov.supernotariado.www.schemas.bachue.cb.gestionalertastitulares.validarsolicitudalertamasiva.v1.TipoSalidaValidarSolicitudAlertaMasivaCodigoMensaje;

import com.b2bsg.common.dataAccess2.DAOManager;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.logging.LoggerHandler;

import com.b2bsg.common.util.CollectionUtils;
import com.b2bsg.common.util.NumericUtils;
import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.business.BaseBusiness;

import com.bachue.snr.prosnr01.common.constants.CodigoAlertaCommon;
import com.bachue.snr.prosnr01.common.constants.ConstanteCommon;
import com.bachue.snr.prosnr01.common.constants.ErrorKeys;
import com.bachue.snr.prosnr01.common.constants.EstadoCommon;
import com.bachue.snr.prosnr01.common.constants.IdentificadoresCommon;
import com.bachue.snr.prosnr01.common.constants.MessagesKeys;
import com.bachue.snr.prosnr01.common.constants.ProyectosCommon;
import com.bachue.snr.prosnr01.common.constants.SistemaOrigenCommon;
import com.bachue.snr.prosnr01.common.utils.ExpresionRegular;

import com.bachue.snr.prosnr01.dao.DaoCreator;
import com.bachue.snr.prosnr01.dao.DaoManagerFactory;
import com.bachue.snr.prosnr01.dao.acc.AlertaTitularDAO;
import com.bachue.snr.prosnr01.dao.acc.SolicitudDAO;
import com.bachue.snr.prosnr01.dao.bng.PredioRegistroDAO;
import com.bachue.snr.prosnr01.dao.pgn.CirculoRegistralDao;
import com.bachue.snr.prosnr01.dao.pgn.DepartamentoDAO;
import com.bachue.snr.prosnr01.dao.pgn.MunicipioDAO;
import com.bachue.snr.prosnr01.dao.pgn.ZonaRegistralDAO;
import com.bachue.snr.prosnr01.dao.util.UtilDAO;

import com.bachue.snr.prosnr01.model.Matricula;
import com.bachue.snr.prosnr01.model.sdb.acc.AlertaTitular;
import com.bachue.snr.prosnr01.model.sdb.acc.Persona;
import com.bachue.snr.prosnr01.model.sdb.acc.PersonaTelefono;
import com.bachue.snr.prosnr01.model.sdb.acc.Solicitud;
import com.bachue.snr.prosnr01.model.sdb.bng.PredioRegistro;
import com.bachue.snr.prosnr01.model.sdb.col.InteresadoDocumentoTipo;
import com.bachue.snr.prosnr01.model.sdb.pgn.CirculoRegistral;
import com.bachue.snr.prosnr01.model.sdb.pgn.Departamento;
import com.bachue.snr.prosnr01.model.sdb.pgn.Municipio;
import com.bachue.snr.prosnr01.model.sdb.pgn.ZonaRegistral;
import com.bachue.snr.prosnr01.model.sdb.png.TarifaAlerta;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Scanner;


/**
 * Clase que contiene todos la logica relacionada con la gestion de alertas a los titulares
 *
 * @author garias
 */
public class GestionAlertasTitularesBusiness extends BaseBusiness
{
	/** Constante clh_LOGGER. */
	private static final LoggerHandler clh_LOGGER = (LoggerHandler)com.bachue.snr.prosnr01.common.utils.LoggingHelper
			.getLogger(GestionAlertasTitularesBusiness.class, ProyectosCommon.GESTION_ALERTA_TITULARES_09);

	/**
	 * Actualiza el correo electrónico y el teléfono de los medios a notificar para todas las alertas vigentes asociadas
	 * a un tipo y número de documento.
	 *
	 * @param ateaca_param Objeto contenedor de los datos de la persona y datos de contacto a actualizar
	 * @param as_userId id del usuario que ejecuta la acción
	 * @param as_remoteIp Dirección IP del cliente que ejecta la acción
	 * @return devuelve el valor de TipoSalidaActualizarContactoAlerta
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see TipoSalidaActualizarContactoAlerta
	 */
	public synchronized TipoSalidaActualizarContactoAlerta actualizarContactoAlerta(
	    TipoEntradaActualizarContactoAlerta ateaca_param, String as_userId, String as_remoteIp
	)
	    throws B2BException
	{
		TipoSalidaActualizarContactoAlerta              ltsaca_response;
		TipoSalidaActualizarContactoAlertaCodigoMensaje ltsacacm_codigoMensaje;
		String                                          ls_descripcionMensaje;
		DAOManager                                      ldm_manager;

		ltsaca_response            = new TipoSalidaActualizarContactoAlerta();
		ltsacacm_codigoMensaje     = null;
		ls_descripcionMensaje      = null;
		ldm_manager                = DaoManagerFactory.getDAOManager();

		try
		{
			if(ateaca_param != null)
			{
				if(validarModuloGisCorreo(ateaca_param.getModulo()))
				{
					String                    ls_correoElectronico;
					String                    ls_numeroCelular;
					Collection<AlertaTitular> lcat_alertas;
					AlertaTitularDAO          latd_alertaTitularDAO;
					SolicitudDAO              lsd_solicitudDAO;

					latd_alertaTitularDAO     = DaoCreator.getAlertaTitularDAO(ldm_manager);
					lsd_solicitudDAO          = DaoCreator.getSolicitudDAO(ldm_manager);

					{
						String ls_tipoDocumento;
						String ls_numDocumento;

						ls_tipoDocumento         = null;
						ls_numDocumento          = ateaca_param.getNumeroDocumento();
						ls_correoElectronico     = ateaca_param.getCorreoElectronico();
						ls_numeroCelular         = ateaca_param.getNumeroCelular();

						{
							TipoEntradaActualizarContactoAlertaTipoDocumento lteacatd_tipoDocumento;

							lteacatd_tipoDocumento = ateaca_param.getTipoDocumento();

							if(lteacatd_tipoDocumento != null)
								ls_tipoDocumento = lteacatd_tipoDocumento.getValue();
						}

						if(!validarTipoDocumentoPersona(ldm_manager, ls_tipoDocumento))
						{
							ltsacacm_codigoMensaje = TipoSalidaActualizarContactoAlertaCodigoMensaje.value4;

							throw new B2BException(ErrorKeys.ERROR_TIPO_DOCUMENTO_NO_VALIDO);
						}

						if(!StringUtils.isValidString(ls_numDocumento))
						{
							ltsacacm_codigoMensaje = TipoSalidaActualizarContactoAlertaCodigoMensaje.value5;

							throw new B2BException(ErrorKeys.ERROR_NUMERO_DOC_SOLICITANTE_NO_VALIDO);
						}

						if(
						    !StringUtils.isValidString(ls_correoElectronico)
							    || !ExpresionRegular.getExpresionRegular().esCorreoElectronico(ls_correoElectronico)
						)
						{
							ltsacacm_codigoMensaje = TipoSalidaActualizarContactoAlertaCodigoMensaje.value6;

							throw new B2BException(ErrorKeys.ERROR_ATTR_USUARIO_E5);
						}

						validarTelefono(
						    ldm_manager,
						    new PersonaTelefono(IdentificadoresCommon.INDICATIVO_PAIS_DEFAULT, ls_numeroCelular), false
						);
					}

					{
						AlertaTitular lat_alertaTmp;

						lat_alertaTmp = new AlertaTitular(ateaca_param);

						lat_alertaTmp.setEstadoAlerta(EstadoCommon.ACTIVA);
						lat_alertaTmp.setFechaFinAlerta(new Date());

						lcat_alertas = latd_alertaTitularDAO.findByDatosPersona(lat_alertaTmp);
					}

					if(CollectionUtils.isValidCollection(lcat_alertas))
					{
						for(AlertaTitular lat_alerta : lcat_alertas)
						{
							if(lat_alerta != null)
							{
								Solicitud ls_solicitud;

								ls_solicitud = lsd_solicitudDAO.findById(lat_alerta.getIdSolicitud());

								if(ls_solicitud != null)
								{
									String ls_idPersona;

									ls_idPersona = ls_solicitud.getIdPersona();

									{
										long ll_idTelefono;

										ll_idTelefono = insertarActualizarTelefono(
											    ldm_manager, ls_idPersona, ls_solicitud.getIdTelefono(), EstadoCommon.M,
											    ls_numeroCelular, as_userId, as_remoteIp
											);

										if(ll_idTelefono > 0)
										{
											ls_solicitud.setIdTelefono(StringUtils.getString(ll_idTelefono));
											ls_solicitud.setIdUsuarioModificacion(as_userId);
											ls_solicitud.setIpModificacion(as_remoteIp);

											lsd_solicitudDAO.update(ls_solicitud);
										}
									}

									{
										long ll_idCorreo;

										ll_idCorreo = insertarActualizarCorreoElectronico(
											    ldm_manager, ls_idPersona, ls_solicitud.getIdCorreoElectronico(),
											    ls_correoElectronico, as_userId, as_remoteIp
											);

										if(ll_idCorreo > 0)
										{
											ls_solicitud.setIdCorreoElectronico(StringUtils.getString(ll_idCorreo));
											ls_solicitud.setIdUsuarioModificacion(as_userId);
											ls_solicitud.setIpModificacion(as_remoteIp);

											lsd_solicitudDAO.update(ls_solicitud);
										}
									}
								}
								else
								{
									ltsacacm_codigoMensaje = TipoSalidaActualizarContactoAlertaCodigoMensaje.value11;

									throw new B2BException(ErrorKeys.ERROR_ID_SOLICITUD_VALIDO);
								}
							}
						}

						ltsacacm_codigoMensaje     = TipoSalidaActualizarContactoAlertaCodigoMensaje.value1;
						ls_descripcionMensaje      = addMessage(MessagesKeys.OK);
					}
					else
					{
						ltsacacm_codigoMensaje = TipoSalidaActualizarContactoAlertaCodigoMensaje.value10;

						throw new B2BException(ErrorKeys.ERROR_CONSULTA_SIN_RESULTADOS);
					}
				}
				else
				{
					ltsacacm_codigoMensaje = TipoSalidaActualizarContactoAlertaCodigoMensaje.value3;

					throw new B2BException(ErrorKeys.ERROR_CANAL_ORIGEN_NO_VALIDO);
				}
			}
			else
			{
				ltsacacm_codigoMensaje = TipoSalidaActualizarContactoAlertaCodigoMensaje.value2;

				throw new B2BException(ErrorKeys.ERROR_PARAMETROS_NO_VALIDOS);
			}
		}
		catch(B2BException lb2be_e)
		{
			String ls_key;

			ls_key = StringUtils.getStringNotNull(lb2be_e.getMessageKey());

			ldm_manager.setRollbackOnly();

			switch(ls_key)
			{
				case ErrorKeys.DEBE_DIGITAR_TELEFONO_MOVIL:
					ltsacacm_codigoMensaje = TipoSalidaActualizarContactoAlertaCodigoMensaje.value7;

					break;

				case ErrorKeys.DEBE_DIGITAR_TELEFONO_MOVIL_VALIDO:
					ltsacacm_codigoMensaje = TipoSalidaActualizarContactoAlertaCodigoMensaje.value8;

					break;

				case ErrorKeys.ERROR_TELEFONO_MOVIL_MALFORMADO:
					ltsacacm_codigoMensaje = TipoSalidaActualizarContactoAlertaCodigoMensaje.value9;

					break;

				default:
					break;
			}

			if(ltsacacm_codigoMensaje == null)
				ltsacacm_codigoMensaje = TipoSalidaActualizarContactoAlertaCodigoMensaje.value12;

			ls_descripcionMensaje = addMessage(ls_key, true);

			clh_LOGGER.error("actualizarContactoAlerta", lb2be_e);
		}
		finally
		{
			ldm_manager.close();
		}

		ltsaca_response.setCodigoMensaje(ltsacacm_codigoMensaje);
		ltsaca_response.setDescripcionMensaje(ls_descripcionMensaje);

		return ltsaca_response;
	}

	/**
	 * Consulta todas las alertas inscritas para un usuario.
	 *
	 * @param ateca_param Objeto contenedor de los parametros a utilizar como filtro en la consulta
	 * @return Arreglo de tipos alertas resultante de la consulta
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see TipoSalidaConsultarAlerta
	 */
	public synchronized TipoSalidaConsultarAlerta consultarAlerta(TipoEntradaConsultarAlerta ateca_param)
	    throws B2BException
	{
		DAOManager                             ldm_manager;
		TipoSalidaConsultarAlerta              ltsca_return;
		TipoSalidaConsultarAlertaCodigoMensaje ltscacm_codigo;
		TiposAlertas[]                         ltaa_tiposAlertas;
		String                                 ls_descripcionMensaje;
		Object[]                               loa_args;

		ldm_manager               = DaoManagerFactory.getDAOManager();
		ltsca_return              = new TipoSalidaConsultarAlerta();
		ltscacm_codigo            = null;
		ltaa_tiposAlertas         = new TiposAlertas[1];
		ls_descripcionMensaje     = null;
		loa_args                  = new Object[1];

		try
		{
			if(ateca_param != null)
			{
				if(validarModuloGisCorreo(ateca_param.getModulo()))
				{
					{
						String ls_tipoDocumento;
						String ls_numDocumento;

						ls_tipoDocumento     = null;
						ls_numDocumento      = ateca_param.getNumeroDocumento();

						{
							TipoEntradaConsultarAlertaTipoDocumento ltecatd_tipoDoc;

							ltecatd_tipoDoc = ateca_param.getTipoDocumento();

							if(ltecatd_tipoDoc != null)
								ls_tipoDocumento = ltecatd_tipoDoc.getValue();
						}

						if(!validarTipoDocumentoPersona(ldm_manager, ls_tipoDocumento))
						{
							ltscacm_codigo = TipoSalidaConsultarAlertaCodigoMensaje.value5;

							throw new B2BException(ErrorKeys.ERROR_TIPO_DOCUMENTO_NO_VALIDO);
						}

						if(!StringUtils.isValidString(ls_numDocumento))
						{
							ltscacm_codigo = TipoSalidaConsultarAlertaCodigoMensaje.value6;

							throw new B2BException(ErrorKeys.ERROR_NUMERO_DOC_SOLICITANTE_NO_VALIDO);
						}
					}

					Collection<AlertaTitular> lcat_alertas;

					lcat_alertas = DaoCreator.getAlertaTitularDAO(ldm_manager)
							                     .findByDatosPersona(new AlertaTitular(ateca_param));

					if(CollectionUtils.isValidCollection(lcat_alertas))
					{
						ltaa_tiposAlertas = new TiposAlertas[lcat_alertas.size()];

						int                 li_cont;
						CirculoRegistralDao lcrd_circuloRegistralDAO;
						PredioRegistroDAO   lprd_predioRegistroDAO;
						ZonaRegistralDAO    lzrd_zonaRegistralDAO;
						DepartamentoDAO     ldd_departamentoDAO;
						MunicipioDAO        lmd_municipioDAO;

						li_cont                      = 0;
						lcrd_circuloRegistralDAO     = DaoCreator.getCirculoRegistralDAO(ldm_manager);
						lprd_predioRegistroDAO       = DaoCreator.getPredioRegistroDAO(ldm_manager);
						lzrd_zonaRegistralDAO        = DaoCreator.getZonaRegistralDAO(ldm_manager);
						ldd_departamentoDAO          = DaoCreator.getDepartamentoDAO(ldm_manager);
						lmd_municipioDAO             = DaoCreator.getMunicipioDAO(ldm_manager);

						for(AlertaTitular lat_data : lcat_alertas)
						{
							if(lat_data != null)
							{
								TiposAlertas lta_alerta;
								String       ls_idCirculo;
								String       ls_idMatricula;
								String       ls_circuloMatr;

								lta_alerta         = new TiposAlertas();
								ls_idCirculo       = lat_data.getIdCirculo();
								ls_idMatricula     = StringUtils.getStringNotNull(lat_data.getIdMatricula());
								ls_circuloMatr     = ls_idCirculo + IdentificadoresCommon.SIMBOLO_GUION
									+ ls_idMatricula;

								lta_alerta.setIdentificadorAlerta(lat_data.getIdAlertaTitular());
								lta_alerta.setNumeroMatricula(ls_idMatricula);
								lta_alerta.setOrip(ls_idCirculo);

								{
									String ls_estadoAlerta;

									ls_estadoAlerta = lat_data.getEstadoAlerta();

									if(StringUtils.isValidString(ls_estadoAlerta))
										lta_alerta.setEstado(ls_estadoAlerta);
									else
									{
										ltscacm_codigo     = TipoSalidaConsultarAlertaCodigoMensaje.value10;

										loa_args[0] = ls_circuloMatr;

										throw new B2BException(ErrorKeys.ERROR_SIN_ESTADO_ALERTA, loa_args);
									}
								}

								{
									CirculoRegistral lcr_circuloRegistral;

									lcr_circuloRegistral = lcrd_circuloRegistralDAO.findById(ls_idCirculo);

									if(lcr_circuloRegistral != null)
										lta_alerta.setNombreOrip(lcr_circuloRegistral.getNombre());
									else
									{
										ltscacm_codigo     = TipoSalidaConsultarAlertaCodigoMensaje.value9;

										loa_args[0] = ls_idCirculo;

										throw new B2BException(ErrorKeys.ERROR_CIRCULO_NO_EXISTE, loa_args);
									}
								}

								{
									PredioRegistro lpr_predioRegistro;

									lpr_predioRegistro = lprd_predioRegistroDAO.findByCirculoMatricula(
										    ls_idCirculo, NumericUtils.getLong(ls_idMatricula)
										);

									if(lpr_predioRegistro != null)
									{
										ZonaRegistral lzr_zonaRegistral;

										lzr_zonaRegistral = lzrd_zonaRegistralDAO.findById(
											    lpr_predioRegistro.getIdZonaRegistral()
											);

										if(lzr_zonaRegistral != null)
										{
											String ls_idPais;
											String ls_idDepartamento;

											ls_idPais             = lzr_zonaRegistral.getIdPais();
											ls_idDepartamento     = lzr_zonaRegistral.getIdDepartamento();

											{
												Departamento ld_departamento;

												ld_departamento = ldd_departamentoDAO.findById(
													    ls_idPais, ls_idDepartamento
													);

												if(ld_departamento != null)
													lta_alerta.setDepartamento(ld_departamento.getNombre());
												else
												{
													ltscacm_codigo     = TipoSalidaConsultarAlertaCodigoMensaje.value12;

													loa_args[0] = ls_idCirculo;

													throw new B2BException(
													    ErrorKeys.ERROR_DEPARTAMENTO_OFICINA_ORIGEN_NO_EXISTE, loa_args
													);
												}
											}

											{
												Municipio lm_municipio;

												lm_municipio = lmd_municipioDAO.findById(
													    ls_idPais, ls_idDepartamento, lzr_zonaRegistral.getIdMunicipio()
													);

												if(lm_municipio != null)
													lta_alerta.setMunicipio(lm_municipio.getNombre());
												else
												{
													ltscacm_codigo     = TipoSalidaConsultarAlertaCodigoMensaje.value11;

													loa_args[0] = ls_idCirculo;

													throw new B2BException(
													    ErrorKeys.ERROR_MUNICIPIO_OFICINA_ORIGEN_NO_EXISTE, loa_args
													);
												}
											}
										}
										else
										{
											ltscacm_codigo     = TipoSalidaConsultarAlertaCodigoMensaje.value9;

											loa_args[0]     = ls_idCirculo;
											loa_args[1]     = ls_idMatricula;

											throw new B2BException(ErrorKeys.ERROR_ZONA_REGISTRAL_NO_EXISTE, loa_args);
										}
									}
									else
									{
										ltscacm_codigo     = TipoSalidaConsultarAlertaCodigoMensaje.value9;

										loa_args[0]     = ls_idCirculo;
										loa_args[1]     = ls_idMatricula;

										throw new B2BException(ErrorKeys.ERROR_PREDIO_REGISTRO_NO_EXISTE, loa_args);
									}
								}

								{
									Date ld_fechaVigencia;

									ld_fechaVigencia = lat_data.getFechaFinAlerta();

									if(ld_fechaVigencia != null)
										lta_alerta.setFechaVigencia(ld_fechaVigencia.toString());
									else
									{
										ltscacm_codigo     = TipoSalidaConsultarAlertaCodigoMensaje.value13;

										loa_args[0] = ls_circuloMatr;

										throw new B2BException(
										    ErrorKeys.ERROR_FECHA_VIGENCIA_ALERTA_NO_VALIDA, loa_args
										);
									}
								}

								{
									String ls_direccion;

									ls_direccion = consultarDireccionCirculoMatricula(
										    ls_idCirculo, ls_idMatricula, ldm_manager
										);

									if(StringUtils.isValidString(ls_direccion))
										lta_alerta.setDireccion(ls_direccion);
									else
									{
										ltscacm_codigo     = TipoSalidaConsultarAlertaCodigoMensaje.value14;

										loa_args[0] = ls_idCirculo + IdentificadoresCommon.SIMBOLO_GUION
											+ ls_idMatricula;

										throw new B2BException(ErrorKeys.DIRECCION_PREDIO_NO_ENCONTRADA, loa_args);
									}
								}

								ltaa_tiposAlertas[li_cont++] = lta_alerta;
							}
						}

						ltscacm_codigo     = TipoSalidaConsultarAlertaCodigoMensaje.value1;

						ls_descripcionMensaje = addMessage(MessagesKeys.OK);
					}
					else
					{
						ltscacm_codigo = TipoSalidaConsultarAlertaCodigoMensaje.value7;

						throw new B2BException(ErrorKeys.ERROR_CONSULTA_SIN_RESULTADOS);
					}
				}
				else
				{
					ltscacm_codigo = TipoSalidaConsultarAlertaCodigoMensaje.value4;

					throw new B2BException(ErrorKeys.ERROR_CANAL_ORIGEN_NO_VALIDO);
				}
			}
			else
			{
				ltscacm_codigo = TipoSalidaConsultarAlertaCodigoMensaje.value3;

				throw new B2BException(ErrorKeys.ERROR_PARAMETROS_NO_VALIDOS);
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("consultarAlerta", lb2be_e);

			if(ltscacm_codigo == null)
				ltscacm_codigo = TipoSalidaConsultarAlertaCodigoMensaje.value8;

			ls_descripcionMensaje     = addMessage(lb2be_e.getMessageKey(), loa_args, true);

			ltaa_tiposAlertas = new TiposAlertas[1];
		}
		catch(Exception le_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("consultarAlerta", le_e);

			ltscacm_codigo     = TipoSalidaConsultarAlertaCodigoMensaje.value8;

			ls_descripcionMensaje     = le_e.getMessage();

			ltaa_tiposAlertas = new TiposAlertas[1];
		}
		finally
		{
			ldm_manager.close();
		}

		ltsca_return.setAlertas(ltaa_tiposAlertas);
		ltsca_return.setCodigoMensaje(ltscacm_codigo);
		ltsca_return.setDescripcionMensaje(ls_descripcionMensaje);

		return ltsca_return;
	}

	/**
	 * Metodo que permite obtener la tarifa de las alertas de titulares de derechos.
	 *
	 * @param atectat_request Argumento de tipo <code>TipoEntradaConsultarTarifaAlertasTitulares</code> que contiene
	 * los criterios necesarios para realizar la consulta de tarifas.
	 * @param adm_manager Conexión a la base de datos
	 * @return Objeto de tipo <code>TipoSalidaConsultarTarifaAlertasTitulares</code> que contiene las tarifas consultadas,
	 * el código y mensaje de respuesta de la operación.
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see TipoSalidaConsultarTarifaAlertasTitulares
	 */
	public synchronized TipoSalidaConsultarTarifaAlertasTitulares consultarTarifaAlertas(
	    TipoEntradaConsultarTarifaAlertasTitulares atectat_request, DAOManager adm_manager
	)
	    throws B2BException
	{
		TipoSalidaConsultarTarifaAlertasTitulares              ltsctat_return;
		TiposTarifas[]                                         ltta_tiposTarifas;
		TipoSalidaConsultarTarifaAlertasTitularesCodigoMensaje ltsctatcm_codigo;
		String                                                 ls_descripcionMensaje;

		ltsctat_return            = new TipoSalidaConsultarTarifaAlertasTitulares();
		ltta_tiposTarifas         = new TiposTarifas[1];
		ltsctatcm_codigo          = null;
		ls_descripcionMensaje     = null;

		try
		{
			if(atectat_request != null)
			{
				String  ls_cantidadAlertasNuevas;
				String  ls_tipoDocumento;
				String  ls_numeroDocumento;
				boolean lb_tarifasInscritas;

				ls_cantidadAlertasNuevas     = atectat_request.getCantidadAlertasNuevas();
				ls_tipoDocumento             = atectat_request.getTipoDocumento();
				ls_numeroDocumento           = atectat_request.getNumeroDocumento();
				lb_tarifasInscritas          = StringUtils.isValidString(ls_cantidadAlertasNuevas)
						&& StringUtils.isValidString(ls_tipoDocumento) && StringUtils.isValidString(ls_numeroDocumento);

				{
					String ls_modulo;

					ls_modulo = atectat_request.getModulo();

					if(!validarModuloGisCorreo(ls_modulo))
					{
						ltsctatcm_codigo = TipoSalidaConsultarTarifaAlertasTitularesCodigoMensaje.value4;

						throw new B2BException(addMessage(ErrorKeys.ERROR_MODULO_NO_VALIDO, true));
					}
				}

				if(lb_tarifasInscritas)
				{
					if(StringUtils.isValidString(ls_cantidadAlertasNuevas))
					{
						long ll_cantidadAlertasNuevas;

						ll_cantidadAlertasNuevas = NumericUtils.getLong(ls_cantidadAlertasNuevas, -1L);

						if(ll_cantidadAlertasNuevas < 0)
						{
							ltsctatcm_codigo = TipoSalidaConsultarTarifaAlertasTitularesCodigoMensaje.value5;

							throw new B2BException(addMessage(ErrorKeys.ERROR_CANTIDAD_ALERTAS_NUEVAS_NO_VALIDA, true));
						}
					}

					if(!validarTipoDocumentoPersona(adm_manager, ls_tipoDocumento))
					{
						Object[] loa_object;

						loa_object     = new String[1];

						loa_object[0]     = ls_tipoDocumento;

						ltsctatcm_codigo = TipoSalidaConsultarTarifaAlertasTitularesCodigoMensaje.value6;

						throw new B2BException(
						    addMessage(ErrorKeys.ERROR_TIPO_DOCUMENTO_NO_PARAMETRIZADO, loa_object, true)
						);
					}

					ls_numeroDocumento = atectat_request.getNumeroDocumento();

					{
						long ll_numeroDocumento;

						ll_numeroDocumento = NumericUtils.getLong(ls_numeroDocumento, -1L);

						if(ll_numeroDocumento < 0)
						{
							ltsctatcm_codigo = TipoSalidaConsultarTarifaAlertasTitularesCodigoMensaje.value7;

							throw new B2BException(addMessage(ErrorKeys.ERROR_DOCUMENTO_NO_VALIDO, true));
						}
					}

					{
						Persona             lp_persona;
						Collection<Persona> lcp_personas;

						lp_persona = new Persona();
						lp_persona.setIdDocumentoTipo(ls_tipoDocumento);
						lp_persona.setNumeroDocumento(ls_numeroDocumento);

						lcp_personas = DaoCreator.getPersonaDAO(adm_manager).findByDocument(lp_persona);

						if(!CollectionUtils.isValidCollection(lcp_personas))
						{
							StringBuilder lsb_sb;
							Object[]      loa_object;

							lsb_sb         = new StringBuilder();
							loa_object     = new String[1];

							lsb_sb.append(ls_tipoDocumento);
							lsb_sb.append(" - ");
							lsb_sb.append(ls_numeroDocumento);

							loa_object[0]     = lsb_sb.toString();

							ltsctatcm_codigo = TipoSalidaConsultarTarifaAlertasTitularesCodigoMensaje.value8;

							throw new B2BException(
							    addMessage(ErrorKeys.ERROR_NUMERO_Y_TIPO_DOCUMENTO_NO_REGISTRADO, loa_object, true)
							);
						}

						{
							Collection<AlertaTitular> lcat_alertasTitular;
							int                       li_cantidadAlertasInscritas;
							int                       li_alertasInscritas;

							lcat_alertasTitular     = DaoCreator.getAlertaTitularDAO(adm_manager)
									                                .findByDatosPersonaActivasEInactivas(
									    new AlertaTitular(atectat_request)
									);

							li_cantidadAlertasInscritas = 0;

							if(CollectionUtils.isValidCollection(lcat_alertasTitular))
								li_cantidadAlertasInscritas = lcat_alertasTitular.size();

							li_alertasInscritas = li_cantidadAlertasInscritas
								+ NumericUtils.getInt(ls_cantidadAlertasNuevas);

							{
								TarifaAlerta lta_tarifaAlerta;

								lta_tarifaAlerta = DaoCreator.getTarifaAlertaDAO(adm_manager)
										                         .buscarRango(li_alertasInscritas);

								if(lta_tarifaAlerta == null)
								{
									Object[] loa_object;

									loa_object     = new String[1];

									loa_object[0]     = StringUtils.getString(li_alertasInscritas);

									ltsctatcm_codigo = TipoSalidaConsultarTarifaAlertasTitularesCodigoMensaje.value9;

									throw new B2BException(
									    addMessage(ErrorKeys.ERROR_SIN_RANGO_PARA_CANTIDAD_ALERTAS, loa_object, true)
									);
								}

								ltsctat_return.setAlertasInscrtitas(StringUtils.getString(li_alertasInscritas));
								ltsctat_return.setTarifaNuevasAlertas(
								    StringUtils.getString(
								        lta_tarifaAlerta.getValorTarifa() * NumericUtils.getInt(
									            ls_cantidadAlertasNuevas
									        )
								    )
								);
							}
						}
					}
				}

				{
					Collection<TarifaAlerta> lcta_tarifaAlerta;

					lcta_tarifaAlerta = DaoCreator.getTarifaAlertaDAO(adm_manager).findAll();

					if(CollectionUtils.isValidCollection(lcta_tarifaAlerta))
					{
						int li_contador;

						li_contador           = 0;
						ltta_tiposTarifas     = new TiposTarifas[lcta_tarifaAlerta.size()];

						for(TarifaAlerta lta_iterador : lcta_tarifaAlerta)
						{
							if(lta_iterador != null)
							{
								TiposTarifas  ltt_tiposTarifas;
								StringBuilder lsb_sb;
								long          ll_cantidadFinalMatriculas;

								ltt_tiposTarifas               = new TiposTarifas();
								lsb_sb                         = new StringBuilder();
								ll_cantidadFinalMatriculas     = lta_iterador.getCantidadFinalMatriculas();

								if(ll_cantidadFinalMatriculas != 9999999999999999L)
								{
									lsb_sb.append(addMessage(MessagesKeys.ENTRE));
									lsb_sb.append(" ");
									lsb_sb.append(lta_iterador.getCantidadInicialMatriculas());
									lsb_sb.append(" ");
									lsb_sb.append(addMessage(MessagesKeys.Y));
									lsb_sb.append(" ");
									lsb_sb.append(ll_cantidadFinalMatriculas);
								}
								else
								{
									lsb_sb.append(addMessage(MessagesKeys.MAS_DE));
									lsb_sb.append(" ");
									lsb_sb.append(lta_iterador.getCantidadInicialMatriculas());
								}

								lsb_sb.append(" ");
								lsb_sb.append(addMessage(MessagesKeys.MATRICULAS_MINUSCULA));

								ltt_tiposTarifas.setRango(lsb_sb.toString());
								ltt_tiposTarifas.setValor(StringUtils.getString(lta_iterador.getValorTarifa()));

								ltta_tiposTarifas[li_contador] = ltt_tiposTarifas;
							}

							li_contador++;
						}
					}
				}

				ltsctatcm_codigo     = TipoSalidaConsultarTarifaAlertasTitularesCodigoMensaje.value1;

				ls_descripcionMensaje = addMessage(MessagesKeys.OK);
			}
			else
			{
				ltsctatcm_codigo = TipoSalidaConsultarTarifaAlertasTitularesCodigoMensaje.value2;

				throw new B2BException(addMessage(ErrorKeys.ERROR_PARAMETROS_NO_VALIDOS, true));
			}
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("consultarTarifaAlertasTitulares", lb2be_e);

			if(ltsctatcm_codigo == null)
				ltsctatcm_codigo = TipoSalidaConsultarTarifaAlertasTitularesCodigoMensaje.value10;

			ls_descripcionMensaje = lb2be_e.getMessage();
		}
		catch(Exception le_e)
		{
			clh_LOGGER.error("consultarTarifaAlertasTitulares", le_e);

			ltsctatcm_codigo          = TipoSalidaConsultarTarifaAlertasTitularesCodigoMensaje.value10;
			ls_descripcionMensaje     = le_e.getMessage();
		}

		ltsctat_return.setTarifas(ltta_tiposTarifas);
		ltsctat_return.setCodigoMensaje(ltsctatcm_codigo);
		ltsctat_return.setDescripcionMensaje(ls_descripcionMensaje);

		return ltsctat_return;
	}

	/**
	 * Metodo que permite obtener la tarifa de las alertas de titulares de derechos.
	 *
	 * @param atectat_request Argumento de tipo <code>TipoEntradaConsultarTarifaAlertasTitulares</code> que contiene
	 * los criterios necesarios para realizar la consulta de tarifas.
	 * @return Objeto de tipo <code>TipoSalidaConsultarTarifaAlertasTitulares</code> que contiene las tarifas consultadas,
	 * el código y mensaje de respuesta de la operación.
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see TipoSalidaConsultarTarifaAlertasTitulares
	 */
	public synchronized TipoSalidaConsultarTarifaAlertasTitulares consultarTarifaAlertasTitulares(
	    TipoEntradaConsultarTarifaAlertasTitulares atectat_request
	)
	    throws B2BException
	{
		DAOManager                                ldm_manager;
		TipoSalidaConsultarTarifaAlertasTitulares ltsctat_return;

		ldm_manager        = DaoManagerFactory.getDAOManager();
		ltsctat_return     = new TipoSalidaConsultarTarifaAlertasTitulares();

		try
		{
			ltsctat_return = consultarTarifaAlertas(atectat_request, ldm_manager);
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("consultarTarifaAlertasTitulares", lb2be_e);
		}
		catch(Exception le_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("consultarTarifaAlertasTitulares", le_e);
		}
		finally
		{
			ldm_manager.close();
		}

		return ltsctat_return;
	}

	/**
	 * Inactiva una alerta que se encuentre en estado activo segun parametros de entrada.
	 *
	 * @param ateia_param Objeto contenedor de los parametros autilizar en la busqueda de la alerta
	 * @param as_userId correspondiente al valor del tipo de objeto String
	 * @param as_remoteIp correspondiente al valor del tipo de objeto String
	 * @return devuelve el valor de TipoSalidaInactivarAlerta
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see TipoSalidaInactivarAlerta
	 */
	public synchronized TipoSalidaInactivarAlerta inactivarAlerta(
	    TipoEntradaInactivarAlerta ateia_param, String as_userId, String as_remoteIp
	)
	    throws B2BException
	{
		DAOManager                             ldm_manager;
		TipoSalidaInactivarAlerta              ltsia_return;
		TipoSalidaInactivarAlertaCodigoMensaje ltsiacm_codigo;
		String                                 ls_descripcionMensaje;

		ldm_manager               = DaoManagerFactory.getDAOManager();
		ltsia_return              = new TipoSalidaInactivarAlerta();
		ltsiacm_codigo            = null;
		ls_descripcionMensaje     = null;

		try
		{
			if(ateia_param != null)
			{
				if(validarModuloGisCorreo(ateia_param.getModulo()))
				{
					{
						String ls_identificadorAlerta;
						String ls_tipoDocumento;
						String ls_numeroDocumento;

						ls_identificadorAlerta     = ateia_param.getIdentificadorAlerta();
						ls_tipoDocumento           = null;
						ls_numeroDocumento         = ateia_param.getNumeroDocumento();

						{
							TipoEntradaInactivarAlertaTipoDocumento lteiatd_tipoDocumento;

							lteiatd_tipoDocumento = ateia_param.getTipoDocumento();

							if(lteiatd_tipoDocumento != null)
								ls_tipoDocumento = lteiatd_tipoDocumento.getValue();
						}

						if(!StringUtils.isValidString(ls_identificadorAlerta))
						{
							ltsiacm_codigo = TipoSalidaInactivarAlertaCodigoMensaje.value7;

							throw new B2BException(ErrorKeys.ERROR_IDENTIFICADOR_ALERTA);
						}

						if(!validarTipoDocumentoPersona(ldm_manager, ls_tipoDocumento))
						{
							ltsiacm_codigo = TipoSalidaInactivarAlertaCodigoMensaje.value4;

							throw new B2BException(ErrorKeys.ERROR_TIPO_DOCUMENTO_NO_VALIDO);
						}

						if(!StringUtils.isValidString(ls_numeroDocumento))
						{
							ltsiacm_codigo = TipoSalidaInactivarAlertaCodigoMensaje.value5;

							throw new B2BException(ErrorKeys.ERROR_NUMERO_DOC_SOLICITANTE_NO_VALIDO);
						}
					}

					AlertaTitularDAO          latd_alertaTitularDAO;
					Collection<AlertaTitular> lcat_alertas;

					latd_alertaTitularDAO = DaoCreator.getAlertaTitularDAO(ldm_manager);

					{
						AlertaTitular lat_alertaTmp;

						lat_alertaTmp = new AlertaTitular(ateia_param);

						lat_alertaTmp.setEstadoAlerta(EstadoCommon.ACTIVA);
						lat_alertaTmp.setFechaFinAlerta(new Date());

						lcat_alertas = latd_alertaTitularDAO.findByDatosPersona(lat_alertaTmp);
					}

					if(CollectionUtils.isValidCollection(lcat_alertas))
					{
						for(AlertaTitular lat_alerta : lcat_alertas)
						{
							if(lat_alerta != null)
							{
								lat_alerta.setEstadoAlerta(EstadoCommon.INACTIVA);
								lat_alerta.setIdUsuarioModificacion(as_userId);
								lat_alerta.setIpModificacion(as_remoteIp);

								latd_alertaTitularDAO.updateEstado(lat_alerta);
							}
						}

						ltsiacm_codigo     = TipoSalidaInactivarAlertaCodigoMensaje.value1;

						ls_descripcionMensaje = addMessage(MessagesKeys.OK);
					}
					else
					{
						ltsiacm_codigo = TipoSalidaInactivarAlertaCodigoMensaje.value6;

						throw new B2BException(ErrorKeys.ERROR_CONSULTA_SIN_RESULTADOS);
					}
				}
				else
				{
					ltsiacm_codigo = TipoSalidaInactivarAlertaCodigoMensaje.value3;

					throw new B2BException(ErrorKeys.ERROR_CANAL_ORIGEN_NO_VALIDO);
				}
			}
			else
			{
				ltsiacm_codigo = TipoSalidaInactivarAlertaCodigoMensaje.value2;

				throw new B2BException(ErrorKeys.ERROR_PARAMETROS_NO_VALIDOS);
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("inactivarAlerta", lb2be_e);

			if(ltsiacm_codigo == null)
				ltsiacm_codigo = TipoSalidaInactivarAlertaCodigoMensaje.value8;

			ls_descripcionMensaje = addMessage(lb2be_e.getMessageKey(), true);
		}
		catch(Exception le_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("inactivarAlerta", le_e);

			ltsiacm_codigo     = TipoSalidaInactivarAlertaCodigoMensaje.value8;

			ls_descripcionMensaje = le_e.getMessage();
		}
		finally
		{
			ldm_manager.close();
		}

		ltsia_return.setCodigoMensaje(ltsiacm_codigo);
		ltsia_return.setDescripcionMensaje(ls_descripcionMensaje);

		return ltsia_return;
	}

	/**
	 * Metodo que permite verificar de manera previa las condiciones para la solicitud de la alerta de titular individual.
	 *
	 * @param aat_parametros Argumento de tipo <code>AlertaTitular</code> que contiene
	 * los criterios necesarios para realizar la validación individual.
	 * @param adm_manager Argumento de tipo <code>DaoManager</code> que permite la conexión a la base de datos.
	 * @return Objeto de tipo <code>AlertaTitular</code> que contiene el código y mensaje de
	 * respuesta de la operación.
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see AlertaTitular
	 */
	public synchronized AlertaTitular validarAlertaIndividual(AlertaTitular aat_parametros, DAOManager adm_manager)
	    throws B2BException
	{
		try
		{
			if(aat_parametros != null)
			{
				String ls_orip;
				String ls_tipoDocumento;
				String ls_numeroDocumento;
				long   ll_numeroMatricula;

				ls_tipoDocumento       = null;
				ls_numeroDocumento     = null;

				if(!validarModuloGisCorreo(aat_parametros.getModulo()))
				{
					aat_parametros.setCodigo(CodigoAlertaCommon.CODIGO_463);

					throw new B2BException(addMessage(ErrorKeys.ERROR_MODULO_NO_VALIDO, true));
				}

				ls_orip = aat_parametros.getIdCirculo();

				if(!StringUtils.isValidString(ls_orip))
				{
					aat_parametros.setCodigo(CodigoAlertaCommon.CODIGO_470);

					throw new B2BException(addMessage(ErrorKeys.ERROR_ORIP_NO_VALIDA, true));
				}

				{
					CirculoRegistral lcr_circuloRegistral;

					lcr_circuloRegistral = DaoCreator.getCirculoRegistralDAO(adm_manager).findById(ls_orip);

					if(lcr_circuloRegistral == null)
					{
						Object[] loa_object;

						loa_object     = new String[1];

						loa_object[0] = ls_orip;

						aat_parametros.setCodigo(CodigoAlertaCommon.CODIGO_471);

						throw new B2BException(addMessage(ErrorKeys.ERROR_ORIP_NO_PARAMETRIZADA, loa_object, true));
					}
				}

				ll_numeroMatricula = NumericUtils.getLong(aat_parametros.getIdMatricula(), -1L);

				if(ll_numeroMatricula < 0)
				{
					aat_parametros.setCodigo(CodigoAlertaCommon.CODIGO_472);

					throw new B2BException(addMessage(ErrorKeys.ERROR_NUMERO_MATRICULA_NO_VALIDO, true));
				}

				{
					PredioRegistro lpr_predioRegistro;

					lpr_predioRegistro = DaoCreator.getPredioRegistroDAO(adm_manager)
							                           .findByCirculoMatricula(ls_orip, ll_numeroMatricula);

					if(lpr_predioRegistro == null)
					{
						StringBuilder lsb_sb;
						Object[]      loa_object;

						lsb_sb         = new StringBuilder();
						loa_object     = new String[1];

						lsb_sb.append(ls_orip);
						lsb_sb.append(" - ");
						lsb_sb.append(ll_numeroMatricula);

						loa_object[0] = lsb_sb.toString();

						aat_parametros.setCodigo(CodigoAlertaCommon.CODIGO_473);

						throw new B2BException(
						    addMessage(ErrorKeys.ERROR_MATRICULA_Y_CIRCULO_NO_REGISTRADOS, loa_object, true)
						);
					}

					{
						String ls_predioInconsistente;

						ls_predioInconsistente = lpr_predioRegistro.getPredioInconsistente();

						if(
						    StringUtils.isValidString(ls_predioInconsistente)
							    && ls_predioInconsistente.equalsIgnoreCase(EstadoCommon.S)
						)
						{
							StringBuilder lsb_sb;
							Object[]      loa_object;

							lsb_sb         = new StringBuilder();
							loa_object     = new String[1];

							lsb_sb.append(ls_orip);
							lsb_sb.append(" - ");
							lsb_sb.append(ll_numeroMatricula);

							loa_object[0] = lsb_sb.toString();

							aat_parametros.setCodigo(CodigoAlertaCommon.CODIGO_474);

							throw new B2BException(
							    addMessage(ErrorKeys.ERROR_MATRICULA_PREDIO_INCONSISTENTE, loa_object, true)
							);
						}
					}
				}

				{
					Persona lp_persona;

					lp_persona = aat_parametros.getPersona();

					if(lp_persona != null)
					{
						ls_tipoDocumento       = lp_persona.getIdDocumentoTipo();
						ls_numeroDocumento     = lp_persona.getNumeroDocumento();

						if(!StringUtils.isValidString(ls_tipoDocumento))
						{
							aat_parametros.setCodigo(CodigoAlertaCommon.CODIGO_455);

							throw new B2BException(addMessage(ErrorKeys.ERROR_TIPO_DOCUMENTO_NO_VALIDO, true));
						}
					}
				}

				if(!validarTipoDocumentoPersona(adm_manager, ls_tipoDocumento))
				{
					Object[] loa_object;

					loa_object     = new String[1];

					loa_object[0] = ls_tipoDocumento;

					aat_parametros.setCodigo(CodigoAlertaCommon.CODIGO_465);

					throw new B2BException(
					    addMessage(ErrorKeys.ERROR_TIPO_DOCUMENTO_NO_PARAMETRIZADO, loa_object, true)
					);
				}

				{
					long ll_numeroDocumento;

					ll_numeroDocumento = NumericUtils.getLong(ls_numeroDocumento, -1L);

					if(ll_numeroDocumento < 0)
					{
						aat_parametros.setCodigo(CodigoAlertaCommon.CODIGO_466);

						throw new B2BException(addMessage(ErrorKeys.ERROR_DOCUMENTO_NO_VALIDO, true));
					}
				}

				{
					Persona             lp_persona;
					Collection<Persona> lcp_personas;

					lp_persona = new Persona();
					lp_persona.setIdDocumentoTipo(ls_tipoDocumento);
					lp_persona.setNumeroDocumento(ls_numeroDocumento);

					lcp_personas = DaoCreator.getPersonaDAO(adm_manager).findByDocument(lp_persona);

					if(!CollectionUtils.isValidCollection(lcp_personas))
					{
						StringBuilder lsb_sb;
						Object[]      loa_object;

						lsb_sb         = new StringBuilder();
						loa_object     = new String[1];

						lsb_sb.append(ls_tipoDocumento);
						lsb_sb.append(" - ");
						lsb_sb.append(ls_numeroDocumento);

						loa_object[0] = lsb_sb.toString();

						aat_parametros.setCodigo(CodigoAlertaCommon.CODIGO_467);

						throw new B2BException(
						    addMessage(ErrorKeys.ERROR_NUMERO_Y_TIPO_DOCUMENTO_NO_REGISTRADO, loa_object, true)
						);
					}
					else
					{
						Iterator<Persona> lip_iterator;
						UtilDAO           lud_DAO;
						boolean           lb_titularDerecho;

						lip_iterator          = lcp_personas.iterator();
						lud_DAO               = DaoCreator.getUtilDAO(adm_manager);
						lb_titularDerecho     = false;

						while(lip_iterator.hasNext() && !lb_titularDerecho)
						{
							Persona lp_personaIterada;

							lp_personaIterada = lip_iterator.next();

							if(lp_personaIterada != null)
							{
								String ls_respuestaFuncion;

								ls_respuestaFuncion     = lud_DAO.funcionVerificaPropiedad(
									    lp_personaIterada.getIdPersona(), ls_orip, ll_numeroMatricula
									);

								lb_titularDerecho = StringUtils.isValidString(ls_respuestaFuncion)
										&& ls_respuestaFuncion.equalsIgnoreCase(EstadoCommon.S);
							}
						}

						if(!lb_titularDerecho)
						{
							aat_parametros.setCodigo(CodigoAlertaCommon.CODIGO_452);

							throw new B2BException(
							    addMessage(ErrorKeys.ERROR_SOLICITANTE_NO_INSCRITO_COMO_TITULAR, true)
							);
						}
					}

					{
						Collection<AlertaTitular> lcat_alertasTitular;

						aat_parametros.setEstadoAlerta(EstadoCommon.ACTIVA);

						lcat_alertasTitular = DaoCreator.getAlertaTitularDAO(adm_manager)
								                            .findByDatosPersona(aat_parametros);

						if(CollectionUtils.isValidCollection(lcat_alertasTitular))
						{
							aat_parametros.setCodigo(CodigoAlertaCommon.CODIGO_469);

							throw new B2BException(addMessage(ErrorKeys.ERROR_SOLICITANTE_TIENE_ALERTA, true));
						}
					}
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			aat_parametros.setDescripcionMensaje(lb2be_e.getMessageKey());
		}

		return aat_parametros;
	}

	/**
	 * Metodo que permite verificar de manera previa las condiciones para la solicitud de la alerta de titular individual.
	 *
	 * @param atevsai_request Argumento de tipo <code>TipoEntradaValidarSolicitudAlertaIndividual</code> que contiene
	 * los criterios necesarios para realizar la validación individual.
	 * @return Objeto de tipo <code>TipoSalidaValidarSolicitudAlertaIndividual</code> que contiene el código y mensaje de
	 * respuesta de la operación.
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see TipoSalidaValidarSolicitudAlertaIndividual
	 */
	public synchronized TipoSalidaValidarSolicitudAlertaIndividual validarSolicitudAlertaIndividual(
	    TipoEntradaValidarSolicitudAlertaIndividual atevsai_request
	)
	    throws B2BException
	{
		DAOManager                                              ldm_manager;
		TipoSalidaValidarSolicitudAlertaIndividual              ltsvsai_return;
		TipoSalidaValidarSolicitudAlertaIndividualCodigoMensaje ltsvsaicm_codigo;
		String                                                  ls_idDepartamento;
		String                                                  ls_idMunicipio;
		String                                                  ls_direccion;
		String                                                  ls_descripcionMensaje;

		ldm_manager               = DaoManagerFactory.getDAOManager();
		ltsvsai_return            = new TipoSalidaValidarSolicitudAlertaIndividual();
		ltsvsaicm_codigo          = null;
		ls_idDepartamento         = null;
		ls_idMunicipio            = null;
		ls_direccion              = null;
		ls_descripcionMensaje     = null;

		try
		{
			if(atevsai_request != null)
			{
				AlertaTitular lat_alertaTitular;
				String        ls_idCirculo;
				long          ll_idMatricula;

				lat_alertaTitular     = new AlertaTitular(atevsai_request);
				ls_idCirculo          = lat_alertaTitular.getIdCirculo();
				ll_idMatricula        = NumericUtils.getLong(lat_alertaTitular.getIdMatricula());

				lat_alertaTitular = validarAlertaIndividual(lat_alertaTitular, ldm_manager);

				if(lat_alertaTitular != null)
				{
					String ls_codigo;
					String ls_descripcion;

					ls_codigo          = lat_alertaTitular.getCodigo();
					ls_descripcion     = lat_alertaTitular.getDescripcionMensaje();

					if(StringUtils.isValidString(ls_codigo) && StringUtils.isValidString(ls_descripcion))
					{
						switch(ls_codigo)
						{
							case CodigoAlertaCommon.CODIGO_452:
								ltsvsaicm_codigo = TipoSalidaValidarSolicitudAlertaIndividualCodigoMensaje.value2;

								break;

							case CodigoAlertaCommon.CODIGO_453:
								ltsvsaicm_codigo = TipoSalidaValidarSolicitudAlertaIndividualCodigoMensaje.value3;

								break;

							case CodigoAlertaCommon.CODIGO_454:
								ltsvsaicm_codigo = TipoSalidaValidarSolicitudAlertaIndividualCodigoMensaje.value4;

								break;

							case CodigoAlertaCommon.CODIGO_455:
								ltsvsaicm_codigo = TipoSalidaValidarSolicitudAlertaIndividualCodigoMensaje.value5;

								break;

							case CodigoAlertaCommon.CODIGO_463:
								ltsvsaicm_codigo = TipoSalidaValidarSolicitudAlertaIndividualCodigoMensaje.value6;

								break;

							case CodigoAlertaCommon.CODIGO_465:
								ltsvsaicm_codigo = TipoSalidaValidarSolicitudAlertaIndividualCodigoMensaje.value7;

								break;

							case CodigoAlertaCommon.CODIGO_466:
								ltsvsaicm_codigo = TipoSalidaValidarSolicitudAlertaIndividualCodigoMensaje.value8;

								break;

							case CodigoAlertaCommon.CODIGO_467:
								ltsvsaicm_codigo = TipoSalidaValidarSolicitudAlertaIndividualCodigoMensaje.value9;

								break;

							case CodigoAlertaCommon.CODIGO_469:
								ltsvsaicm_codigo = TipoSalidaValidarSolicitudAlertaIndividualCodigoMensaje.value10;

								break;

							case CodigoAlertaCommon.CODIGO_470:
								ltsvsaicm_codigo = TipoSalidaValidarSolicitudAlertaIndividualCodigoMensaje.value11;

								break;

							case CodigoAlertaCommon.CODIGO_471:
								ltsvsaicm_codigo = TipoSalidaValidarSolicitudAlertaIndividualCodigoMensaje.value12;

								break;

							case CodigoAlertaCommon.CODIGO_472:
								ltsvsaicm_codigo = TipoSalidaValidarSolicitudAlertaIndividualCodigoMensaje.value13;

								break;

							case CodigoAlertaCommon.CODIGO_473:
								ltsvsaicm_codigo = TipoSalidaValidarSolicitudAlertaIndividualCodigoMensaje.value14;

								break;

							case CodigoAlertaCommon.CODIGO_474:
								ltsvsaicm_codigo = TipoSalidaValidarSolicitudAlertaIndividualCodigoMensaje.value15;

								break;

							default:
								break;
						}

						throw new B2BException(ls_descripcion);
					}
				}

				if(StringUtils.isValidString(ls_idCirculo) && (ll_idMatricula > 0))
				{
					{
						PredioRegistro lpr_predioRegistro;

						lpr_predioRegistro = DaoCreator.getPredioRegistroDAO(ldm_manager)
								                           .findByCirculoMatricula(ls_idCirculo, ll_idMatricula);

						if(lpr_predioRegistro != null)
						{
							String ls_idZonaRegistral;

							ls_idZonaRegistral = lpr_predioRegistro.getIdZonaRegistral();

							if(StringUtils.isValidString(ls_idZonaRegistral))
							{
								ZonaRegistral lzr_zonaRegistral;

								lzr_zonaRegistral = DaoCreator.getZonaRegistralDAO(ldm_manager)
										                          .findById(ls_idZonaRegistral);

								if(lzr_zonaRegistral != null)
								{
									ls_idDepartamento     = lzr_zonaRegistral.getIdDepartamento();
									ls_idMunicipio        = lzr_zonaRegistral.getIdMunicipio();
								}
							}
						}
					}

					ls_direccion = consultarDireccionCirculoMatricula(
						    ls_idCirculo, StringUtils.getString(ll_idMatricula), ldm_manager
						);
				}

				ltsvsaicm_codigo          = TipoSalidaValidarSolicitudAlertaIndividualCodigoMensaje.value1;
				ls_descripcionMensaje     = addMessage(MessagesKeys.OK);
			}
			else
			{
				ltsvsaicm_codigo = TipoSalidaValidarSolicitudAlertaIndividualCodigoMensaje.value3;

				throw new B2BException(ErrorKeys.ERROR_PARAMETROS_NO_VALIDOS);
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("validarSolicitudAlertaIndividual", lb2be_e);

			if(ltsvsaicm_codigo == null)
				ltsvsaicm_codigo = TipoSalidaValidarSolicitudAlertaIndividualCodigoMensaje.value16;

			ls_descripcionMensaje = lb2be_e.getMessageKey();
		}
		catch(Exception le_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("validarSolicitudAlertaIndividual", le_e);

			ltsvsaicm_codigo          = TipoSalidaValidarSolicitudAlertaIndividualCodigoMensaje.value16;
			ls_descripcionMensaje     = le_e.getMessage();
		}
		finally
		{
			ldm_manager.close();
		}

		ltsvsai_return.setDepartamento(StringUtils.isValidString(ls_idDepartamento) ? ls_idDepartamento : new String());
		ltsvsai_return.setCiudad(StringUtils.isValidString(ls_idMunicipio) ? ls_idMunicipio : new String());
		ltsvsai_return.setDireccion(StringUtils.isValidString(ls_direccion) ? ls_direccion : new String());
		ltsvsai_return.setCodigoMensaje(ltsvsaicm_codigo);
		ltsvsai_return.setDescripcionMensaje(ls_descripcionMensaje);

		return ltsvsai_return;
	}

	/**
	 * Valida alertas asociadas a un tipo y número de documento de forma masiva.
	 *
	 * @param atevsam_param Objeto contenedor de los parametros a utilizar como filtro en la busqueda
	 * @return TipoSalidaValidarSolicitudAlertaMasiva con el archivo resultante de la consulta
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see TipoSalidaValidarSolicitudAlertaMasiva
	 */
	public synchronized TipoSalidaValidarSolicitudAlertaMasiva validarSolicitudAlertaMasiva(
	    TipoEntradaValidarSolicitudAlertaMasiva atevsam_param
	)
	    throws B2BException
	{
		TipoSalidaValidarSolicitudAlertaMasivaCodigoMensaje ltsvsamcm_codigo;
		String                                              ls_descripcionMensaje;
		DAOManager                                          ldm_manager;
		StringBuilder                                       lsb_encabezado;
		StringBuilder                                       lsb_cadena;

		ltsvsamcm_codigo     = TipoSalidaValidarSolicitudAlertaMasivaCodigoMensaje.value15;
		ldm_manager          = DaoManagerFactory.getDAOManager();
		lsb_encabezado       = null;
		lsb_cadena           = null;

		try
		{
			if(atevsam_param != null)
			{
				String ls_modulo;

				ls_modulo = atevsam_param.getModulo();

				if(validarModuloGisCorreo(ls_modulo))
				{
					Persona lp_persona;

					{
						String ls_tipoDocumento;
						String ls_numeroDocumento;

						ls_tipoDocumento       = null;
						ls_numeroDocumento     = atevsam_param.getNumeroDocumento();

						{
							TipoEntradaValidarSolicitudAlertaMasivaTipoDocumento ltevsamtd_tipoDocumento;

							ltevsamtd_tipoDocumento = atevsam_param.getTipoDocumento();

							if(ltevsamtd_tipoDocumento != null)
								ls_tipoDocumento = ltevsamtd_tipoDocumento.getValue();
						}

						if(!validarTipoDocumentoPersona(ldm_manager, ls_tipoDocumento))
						{
							ltsvsamcm_codigo = TipoSalidaValidarSolicitudAlertaMasivaCodigoMensaje.value4;

							throw new B2BException(addMessage(ErrorKeys.ERROR_TIPO_DOCUMENTO_NO_VALIDO, true));
						}

						if(!StringUtils.isValidString(ls_numeroDocumento))
						{
							ltsvsamcm_codigo = TipoSalidaValidarSolicitudAlertaMasivaCodigoMensaje.value7;

							throw new B2BException(addMessage(ErrorKeys.ERROR_NUMERO_DOC_SOLICITANTE_NO_VALIDO, true));
						}

						lp_persona = new Persona(ls_tipoDocumento, ls_numeroDocumento);
					}

					Map<String, AlertasMensajeEstado> lmscat_matriculasPorCodigo;

					lmscat_matriculasPorCodigo = new LinkedHashMap<String, AlertasMensajeEstado>();

					lmscat_matriculasPorCodigo.put(
					    ConstanteCommon.EXITOSA,
					    new AlertasMensajeEstado(
					        new LinkedList<AlertaTitular>(), addMessage(MessagesKeys.MATRICULAS_EXITOSAS)
					    )
					);
					lmscat_matriculasPorCodigo.put(
					    CodigoAlertaCommon.CODIGO_452,
					    new AlertasMensajeEstado(
					        new LinkedList<AlertaTitular>(), addMessage(MessagesKeys.SOLICITANTE_NO_TITULAR)
					    )
					);
					lmscat_matriculasPorCodigo.put(
					    CodigoAlertaCommon.CODIGO_455,
					    new AlertasMensajeEstado(
					        new LinkedList<AlertaTitular>(), addMessage(MessagesKeys.TIPO_DOC_INVALIDO)
					    )
					);
					lmscat_matriculasPorCodigo.put(
					    CodigoAlertaCommon.CODIGO_463,
					    new AlertasMensajeEstado(
					        new LinkedList<AlertaTitular>(), addMessage(MessagesKeys.MODULO_NO_VALIDO)
					    )
					);
					lmscat_matriculasPorCodigo.put(
					    CodigoAlertaCommon.CODIGO_465,
					    new AlertasMensajeEstado(
					        new LinkedList<AlertaTitular>(), addMessage(MessagesKeys.TIPO_DOC_NO_PARAMETRIZADO)
					    )
					);
					lmscat_matriculasPorCodigo.put(
					    CodigoAlertaCommon.CODIGO_466,
					    new AlertasMensajeEstado(
					        new LinkedList<AlertaTitular>(), addMessage(MessagesKeys.DOC_NO_VALIDO)
					    )
					);
					lmscat_matriculasPorCodigo.put(
					    CodigoAlertaCommon.CODIGO_467,
					    new AlertasMensajeEstado(
					        new LinkedList<AlertaTitular>(), addMessage(MessagesKeys.DOC_TIPO_DOC_NO_REGISTRADOS)
					    )
					);
					lmscat_matriculasPorCodigo.put(
					    CodigoAlertaCommon.CODIGO_469,
					    new AlertasMensajeEstado(
					        new LinkedList<AlertaTitular>(), addMessage(MessagesKeys.ALERTAS_VIGENTES)
					    )
					);
					lmscat_matriculasPorCodigo.put(
					    CodigoAlertaCommon.CODIGO_470,
					    new AlertasMensajeEstado(
					        new LinkedList<AlertaTitular>(), addMessage(MessagesKeys.ORIP_NO_VALIDA)
					    )
					);
					lmscat_matriculasPorCodigo.put(
					    CodigoAlertaCommon.CODIGO_471,
					    new AlertasMensajeEstado(
					        new LinkedList<AlertaTitular>(), addMessage(MessagesKeys.ORIP_NO_PARAMETRIZADA)
					    )
					);
					lmscat_matriculasPorCodigo.put(
					    CodigoAlertaCommon.CODIGO_472,
					    new AlertasMensajeEstado(
					        new LinkedList<AlertaTitular>(), addMessage(MessagesKeys.MATRICULA_NO_VALIDA)
					    )
					);
					lmscat_matriculasPorCodigo.put(
					    CodigoAlertaCommon.CODIGO_473,
					    new AlertasMensajeEstado(
					        new LinkedList<AlertaTitular>(), addMessage(MessagesKeys.MATRICULAS_NO_EXISTENTES)
					    )
					);
					lmscat_matriculasPorCodigo.put(
					    CodigoAlertaCommon.CODIGO_474,
					    new AlertasMensajeEstado(
					        new LinkedList<AlertaTitular>(), addMessage(MessagesKeys.PREDIOS_INCONSISTENTES)
					    )
					);

					{
						byte[]             lba_archivo;
						String             ls_idCirculo;
						String             ls_rangoInicial;
						String             ls_rangoFinal;
						Collection<String> lcs_archivo;
						boolean            lb_archivoValido;
						boolean            lb_idCirculoRangoValido;
						boolean            lb_rangoInicialValido;
						boolean            lb_rangoFinalValido;

						lba_archivo                 = atevsam_param.getArchivo();
						ls_idCirculo                = atevsam_param.getOripSecuencia();
						ls_rangoInicial             = atevsam_param.getMatriculaInicial();
						ls_rangoFinal               = atevsam_param.getMatriculaFinal();
						lb_archivoValido            = (lba_archivo != null) && (lba_archivo.length > 0);
						lb_idCirculoRangoValido     = StringUtils.isValidString(ls_idCirculo);
						lb_rangoInicialValido       = StringUtils.isValidString(ls_rangoInicial);
						lb_rangoFinalValido         = StringUtils.isValidString(ls_rangoFinal);

						if(lb_archivoValido && lb_idCirculoRangoValido && lb_rangoInicialValido && lb_rangoFinalValido)
						{
							ltsvsamcm_codigo = TipoSalidaValidarSolicitudAlertaMasivaCodigoMensaje.value17;

							throw new B2BException(addMessage(ErrorKeys.ERROR_ARCHIVO_Y_RANGO_ENCONTRADO, true));
						}
						else if(lb_archivoValido)
						{
							boolean lb_finalizo;

							lcs_archivo     = new ArrayList<String>();

							lb_finalizo = false;

							//FORMATO Comentar try con recursos antes de formatear
							try(Scanner ls_scanner = new Scanner(new String(lba_archivo)))
							{
							    while(ls_scanner.hasNextLine())
							    {
							        String ls_linea;
							
							        ls_linea = ls_scanner.nextLine();
							
							        if(StringUtils.isValidString(ls_linea))
							            lcs_archivo.add(ls_linea);
							    }
							
							    lb_finalizo = true;
							}
							catch(IllegalStateException lise_ise)
							{
							    if(!lb_finalizo)
							        throw new B2BException(ErrorKeys.CONTAINER_ERROR, lise_ise);
							}
							catch(NoSuchElementException lnsee_nsee)
							{
							    throw new B2BException(ErrorKeys.CONTAINER_ERROR, lnsee_nsee);
							}
							
							if(CollectionUtils.isValidCollection(lcs_archivo))
							{
								for(String ls_linea : lcs_archivo)
								{
									if(StringUtils.isValidString(ls_linea))
									{
										Matricula lm_matricula;

										lm_matricula = Matricula.getMatricula(
											    ls_linea, IdentificadoresCommon.SIMBOLO_PUNTO_COMA_TXT, 1, 0
											);

										if(lm_matricula != null)
											guardarAlertaTitular(
											    ldm_manager, ls_modulo, lm_matricula.getCirculo(),
											    StringUtils.getString(lm_matricula.getMatricula()), lp_persona,
											    lmscat_matriculasPorCodigo
											);
									}
								}
							}
						}
						else if(lb_idCirculoRangoValido && lb_rangoInicialValido && lb_rangoFinalValido)
						{
							long ll_matriculaInicial;
							long ll_matriculaFinal;

							ll_matriculaInicial     = NumericUtils.getLong(ls_rangoInicial);
							ll_matriculaFinal       = NumericUtils.getLong(ls_rangoFinal);

							if(ll_matriculaInicial > ll_matriculaFinal)
								throw new B2BException(addMessage(ErrorKeys.ERROR_MATRICULA_RANGOS, true));

							for(long ll_cont = ll_matriculaInicial; ll_cont <= ll_matriculaFinal; ll_cont++)
								guardarAlertaTitular(
								    ldm_manager, ls_modulo, ls_idCirculo, StringUtils.getString(ll_cont), lp_persona,
								    lmscat_matriculasPorCodigo
								);
						}
						else if(!lb_rangoInicialValido || !lb_rangoFinalValido)
						{
							ltsvsamcm_codigo = TipoSalidaValidarSolicitudAlertaMasivaCodigoMensaje.value16;

							throw new B2BException(
							    addMessage(ErrorKeys.ERROR_SIN_RANGO_PARA_CANTIDAD_ALERTAS_NO_PARAM, true)
							);
						}
						else if(!lb_idCirculoRangoValido)
						{
							ltsvsamcm_codigo = TipoSalidaValidarSolicitudAlertaMasivaCodigoMensaje.value16;

							throw new B2BException(addMessage(ErrorKeys.ERROR_SIN_CIRCULO_ORIGEN, true));
						}
						else
						{
							ltsvsamcm_codigo = TipoSalidaValidarSolicitudAlertaMasivaCodigoMensaje.value3;

							throw new B2BException(addMessage(ErrorKeys.ERROR_PARAMETROS_NO_VALIDOS, true));
						}

						{
							lsb_encabezado     = new StringBuilder();
							lsb_cadena         = new StringBuilder();

							for(Map.Entry<String, AlertasMensajeEstado> lmescat_iterador : lmscat_matriculasPorCodigo
								    .entrySet())
							{
								if(lmescat_iterador != null)
								{
									AlertasMensajeEstado lame_alertaMensajeEstado;

									lame_alertaMensajeEstado = lmescat_iterador.getValue();

									if(lame_alertaMensajeEstado != null)
										llenarArchivoSalida(
										    lame_alertaMensajeEstado.getAlertas(), lame_alertaMensajeEstado.getMensaje(),
										    lmescat_iterador.getKey(), lsb_encabezado, lsb_cadena, ldm_manager
										);
								}
							}
						}
					}
				}
				else
				{
					ltsvsamcm_codigo = TipoSalidaValidarSolicitudAlertaMasivaCodigoMensaje.value5;

					throw new B2BException(addMessage(ErrorKeys.ERROR_MODULO_NO_VALIDO, true));
				}
			}
			else
			{
				ltsvsamcm_codigo = TipoSalidaValidarSolicitudAlertaMasivaCodigoMensaje.value3;

				throw new B2BException(addMessage(ErrorKeys.ERROR_PARAMETROS_NO_VALIDOS, true));
			}

			ltsvsamcm_codigo          = TipoSalidaValidarSolicitudAlertaMasivaCodigoMensaje.value1;
			ls_descripcionMensaje     = getMessages().getString(MessagesKeys.OK);
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("validarSolicitudAlertaMasiva", lb2be_e);

			if(ltsvsamcm_codigo == null)
				ltsvsamcm_codigo = TipoSalidaValidarSolicitudAlertaMasivaCodigoMensaje.value15;

			ls_descripcionMensaje = lb2be_e.getMessageKey();
		}
		catch(Exception le_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("validarSolicitudAlertaMasiva", le_e);

			ltsvsamcm_codigo     = TipoSalidaValidarSolicitudAlertaMasivaCodigoMensaje.value15;

			ls_descripcionMensaje = le_e.getMessage();
		}
		finally
		{
			ldm_manager.close();
		}

		{
			StringBuilder lsb_cadenaFinal;

			lsb_cadenaFinal = null;

			if((lsb_encabezado != null) && (lsb_cadena != null))
			{
				lsb_cadenaFinal = new StringBuilder();

				lsb_cadenaFinal.append(lsb_encabezado.toString());
				lsb_cadenaFinal.append(lsb_cadena.toString());
			}

			return new TipoSalidaValidarSolicitudAlertaMasiva(
			    (lsb_cadenaFinal != null) ? lsb_cadenaFinal.toString().getBytes() : new byte[1], ltsvsamcm_codigo,
			    ls_descripcionMensaje
			);
		}
	}

	/**
	 * Registra en una cadena de caracteres los datos de un tipo de matrícula consultado.
	 *
	 * @param asb_stringBuilder Objeto en donde se va a almacenar la información
	 * @param as_nombreEncabezado nombre del tipo de matrícula consultada
	 * @param as_puntoYComa identificador del simbolo punto y coma
	 * @param ai_numeroMatr número de matrículas a registrar en el encabezado
	 * @param as_saltoLinea identificador del salto de linea para separar de los demas tipos de matrícula
	 * @param ab_encabezadoDetalle true para indicar si es el encabezado principal o false para el encabezado del detalle
	 */
	private void escribirEncabezadoTipoMatricula(
	    StringBuilder asb_stringBuilder, String as_nombreEncabezado, String as_puntoYComa, int ai_numeroMatr,
	    String as_saltoLinea, boolean ab_encabezadoDetalle
	)
	{
		if(
		    (asb_stringBuilder != null) && StringUtils.isValidString(as_nombreEncabezado)
			    && StringUtils.isValidString(as_puntoYComa) && (ai_numeroMatr >= 0)
		)
		{
			asb_stringBuilder.append(as_nombreEncabezado);
			asb_stringBuilder.append(as_puntoYComa);

			if(ab_encabezadoDetalle)
			{
				asb_stringBuilder.append(ai_numeroMatr);
				asb_stringBuilder.append(as_puntoYComa);
			}

			asb_stringBuilder.append(as_saltoLinea);
		}
	}

	/**
	 * Alamcena en una colección final la alerta asociada a una matrícula a consultar.
	 *
	 * @param adm_manager Conexión a la base de datos
	 * @param as_modulo Id del modulo desde donde se realiza la petición
	 * @param as_idCirculo id del círculo al cual pertenece la matrícula a consultar
	 * @param as_idMatricula id de la matrícula a consultar
	 * @param ap_persona Datos de la persona asociada a la alerta
	 * @param amscat_alertasAlmacenadas Objeto contenedor de las alertas verificadas
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	private void guardarAlertaTitular(
	    DAOManager adm_manager, String as_modulo, String as_idCirculo, String as_idMatricula, Persona ap_persona,
	    Map<String, AlertasMensajeEstado> amscat_alertasAlmacenadas
	)
	    throws B2BException
	{
		if(amscat_alertasAlmacenadas != null)
		{
			AlertaTitular lat_tmp;

			lat_tmp = validarAlertaIndividual(
				    new AlertaTitular(as_modulo, as_idCirculo, as_idMatricula, ap_persona), adm_manager
				);

			if(lat_tmp != null)
			{
				String ls_codigo;

				ls_codigo = lat_tmp.getCodigo();

				if(!StringUtils.isValidString(ls_codigo))
					ls_codigo = ConstanteCommon.EXITOSA;

				{
					AlertasMensajeEstado lame_alertaEstado;

					lame_alertaEstado = amscat_alertasAlmacenadas.get(ls_codigo);

					if(lame_alertaEstado != null)
					{
						Collection<AlertaTitular> lcat_alertasTmp;

						lcat_alertasTmp = lame_alertaEstado.getAlertas();

						if(lcat_alertasTmp != null)
							lcat_alertasTmp.add(lat_tmp);
					}
				}
			}
		}
	}

	/**
	 * Metodo encargado de llenar una cadena de salida para llenar el archivo de salida del servicio ValidarSolicitudAlertaTitulosMasiva.
	 *
	 * @param acat_alertaTitular Argumento de tipo <code>AlertaTitular</code> que contiene los datos a ingresar en la cadena.
	 * @param as_nombre Argumento de tipo <code>String</code> que contiene el nombre del encabezado de los registros a llenar.
	 * @param as_codigo Argumento de tipo <code>String</code> que contiene el código del encabezado de los registros a llenar.
	 * @param asb_encabezado correspondiente al valor del tipo de objeto StringBuilder
	 * @param asb_cadena Argumento de tipo <code>StringBuilder</code>, cadena a llenar.
	 * @param adm_manager Argumento de tipo <code>DAOManager</code> que permite la conexión a la base de datos.
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	private void llenarArchivoSalida(
	    Collection<AlertaTitular> acat_alertaTitular, String as_nombre, String as_codigo, StringBuilder asb_encabezado,
	    StringBuilder asb_cadena, DAOManager adm_manager
	)
	    throws B2BException
	{
		if(asb_cadena != null)
		{
			if(
			    CollectionUtils.isValidCollection(acat_alertaTitular) && StringUtils.isValidString(as_nombre)
				    && StringUtils.isValidString(as_codigo) && (asb_encabezado != null)
			)
			{
				String ls_puntoYComa;
				String ls_saltoLinea;
				int    li_tamanioColeccion;

				ls_puntoYComa           = IdentificadoresCommon.SIMBOLO_PUNTO_COMA_TXT;
				ls_saltoLinea           = IdentificadoresCommon.SALTO_LINEA;
				li_tamanioColeccion     = acat_alertaTitular.size();

				escribirEncabezadoTipoMatricula(
				    asb_encabezado, as_nombre, ls_puntoYComa, li_tamanioColeccion, ls_saltoLinea, true
				);

				escribirEncabezadoTipoMatricula(
				    asb_cadena, as_nombre, ls_puntoYComa, li_tamanioColeccion, ls_saltoLinea, false
				);

				for(AlertaTitular lat_data : acat_alertaTitular)
				{
					if(lat_data != null)
					{
						String ls_idCirculo;
						String ls_idMatricula;

						ls_idCirculo       = lat_data.getIdCirculo();
						ls_idMatricula     = lat_data.getIdMatricula();

						asb_cadena.append(ls_idCirculo);
						asb_cadena.append(IdentificadoresCommon.SIMBOLO_GUION);
						asb_cadena.append(ls_idMatricula);
						asb_cadena.append(ls_puntoYComa);

						if(
						    StringUtils.isValidString(as_codigo)
							    && (as_codigo.equalsIgnoreCase(CodigoAlertaCommon.CODIGO_469)
							    || as_codigo.equalsIgnoreCase(ConstanteCommon.EXITOSA))
						)
						{
							String ls_direccion;

							ls_direccion = consultarDireccionCirculoMatricula(
								    ls_idCirculo, ls_idMatricula, adm_manager
								);

							if(StringUtils.isValidString(ls_direccion))
							{
								asb_cadena.append(ls_direccion);
								asb_cadena.append(ls_puntoYComa);
							}
						}

						asb_cadena.append(ls_saltoLinea);
					}
				}
			}
			else
				asb_cadena.append("");
		}
	}

	/**
	 * Valida si el modulo ingresado es correcto.
	 *
	 * @param as_modulo Objeto contenedor del modulo ingresado
	 * @return verdadero, si se cumple la condicion, de lo contario retorna falso
	 */
	private boolean validarModuloGisCorreo(String as_modulo)
	{
		return StringUtils.isValidString(as_modulo)
			&& (as_modulo.equals(SistemaOrigenCommon.SEDE_ELECTRONICA) || as_modulo.equals(SistemaOrigenCommon.GIS));
	}

	/**
	 * Valida la existencia de un tipo de documento persona en el sistema.
	 *
	 * @param adm_manager Conexión a la base de datos
	 * @param as_idTipoDocumento Objeto contenedor del tipo documento a validar
	 * @return true si el tipo documento ingresado es valido y existe en el sistema
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	private boolean validarTipoDocumentoPersona(DAOManager adm_manager, String as_idTipoDocumento)
	    throws B2BException
	{
		boolean lb_valido;

		lb_valido = false;

		if(StringUtils.isValidString(as_idTipoDocumento))
		{
			InteresadoDocumentoTipo lidt_interesadoDocumentoTipo;

			lidt_interesadoDocumentoTipo     = DaoCreator.getInteresadoDocumentoTipoDAO(adm_manager)
					                                         .findById(as_idTipoDocumento);

			lb_valido = lidt_interesadoDocumentoTipo != null;
		}

		return lb_valido;
	}
}
