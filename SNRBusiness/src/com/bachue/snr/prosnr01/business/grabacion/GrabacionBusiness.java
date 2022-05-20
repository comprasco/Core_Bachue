package com.bachue.snr.prosnr01.business.grabacion;

import com.b2bsg.common.dataAccess2.DAOManager;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.logging.LoggerHandler;

import com.b2bsg.common.util.CollectionUtils;
import com.b2bsg.common.util.DateUtils;
import com.b2bsg.common.util.NumericUtils;
import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.business.BaseBusiness;
import com.bachue.snr.prosnr01.business.firma.FirmaMasivaBusiness;
import com.bachue.snr.prosnr01.business.utilidades.PDFGenerator;

import com.bachue.snr.prosnr01.common.constants.ConstanteCommon;
import com.bachue.snr.prosnr01.common.constants.ErrorKeys;
import com.bachue.snr.prosnr01.common.constants.EstadoCommon;
import com.bachue.snr.prosnr01.common.constants.EtapaCommon;
import com.bachue.snr.prosnr01.common.constants.ExtensionCommon;
import com.bachue.snr.prosnr01.common.constants.IdentificadoresCommon;
import com.bachue.snr.prosnr01.common.constants.MotivoTramiteCommon;
import com.bachue.snr.prosnr01.common.constants.RepositorioCommon;
import com.bachue.snr.prosnr01.common.constants.TipoArchivoCommon;
import com.bachue.snr.prosnr01.common.constants.TipoDocumentalCommon;
import com.bachue.snr.prosnr01.common.utils.ExpresionRegular;

import com.bachue.snr.prosnr01.dao.DaoCreator;
import com.bachue.snr.prosnr01.dao.DaoManagerFactory;
import com.bachue.snr.prosnr01.dao.acc.AccPredioRegistroDAO;
import com.bachue.snr.prosnr01.dao.acc.DocumentosSalidaDAO;
import com.bachue.snr.prosnr01.dao.acc.PersonaCorreoElectronicoDAO;
import com.bachue.snr.prosnr01.dao.acc.PersonaDAO;
import com.bachue.snr.prosnr01.dao.acc.PersonaDireccionDAO;
import com.bachue.snr.prosnr01.dao.acc.PersonaTelefonoDAO;
import com.bachue.snr.prosnr01.dao.acc.SolicitudDAO;
import com.bachue.snr.prosnr01.dao.acc.TurnoHistoriaDAO;
import com.bachue.snr.prosnr01.dao.antiguo.sistema.AntiguoSistemaDAO;
import com.bachue.snr.prosnr01.dao.bgn.ImagenesDAO;

import com.bachue.snr.prosnr01.model.antiguoSistema.Apertura;
import com.bachue.snr.prosnr01.model.antiguoSistema.DatosBasicos;
import com.bachue.snr.prosnr01.model.antiguoSistema.MatriculaBase;
import com.bachue.snr.prosnr01.model.antiguoSistema.UbicacionZonaRegistral;
import com.bachue.snr.prosnr01.model.calificacion.DatosDelInteresado;
import com.bachue.snr.prosnr01.model.calificacion.Suspension;
import com.bachue.snr.prosnr01.model.calificacion.TramiteCantidad;
import com.bachue.snr.prosnr01.model.numeracion.NumeracionOficiosCirculo;
import com.bachue.snr.prosnr01.model.numeracion.NumeracionResolucionCirculo;
import com.bachue.snr.prosnr01.model.sdb.acc.AccPredioRegistro;
import com.bachue.snr.prosnr01.model.sdb.acc.DatosAntSistema;
import com.bachue.snr.prosnr01.model.sdb.acc.DocumentosSalida;
import com.bachue.snr.prosnr01.model.sdb.acc.OficiosTexto;
import com.bachue.snr.prosnr01.model.sdb.acc.Persona;
import com.bachue.snr.prosnr01.model.sdb.acc.PersonaCorreoElectronico;
import com.bachue.snr.prosnr01.model.sdb.acc.PersonaDireccion;
import com.bachue.snr.prosnr01.model.sdb.acc.PersonaTelefono;
import com.bachue.snr.prosnr01.model.sdb.acc.Solicitud;
import com.bachue.snr.prosnr01.model.sdb.acc.Turno;
import com.bachue.snr.prosnr01.model.sdb.acc.TurnoHistoria;
import com.bachue.snr.prosnr01.model.sdb.bng.Documento;
import com.bachue.snr.prosnr01.model.sdb.bng.Imagenes;
import com.bachue.snr.prosnr01.model.sdb.bng.PredioRegistro;
import com.bachue.snr.prosnr01.model.sdb.pgn.CirculoRegistral;
import com.bachue.snr.prosnr01.model.sdb.pgn.Constantes;
import com.bachue.snr.prosnr01.model.sdb.pgn.Departamento;
import com.bachue.snr.prosnr01.model.sdb.pgn.Etapa;
import com.bachue.snr.prosnr01.model.sdb.pgn.MotivoTramite;
import com.bachue.snr.prosnr01.model.sdb.pgn.Municipio;
import com.bachue.snr.prosnr01.model.sdb.pgn.OficinaOrigen;
import com.bachue.snr.prosnr01.model.sdb.png.EstadoPredio;

import java.math.BigDecimal;

import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map;


/**
 * Clase que contiene los métodos de negocio  para la fase de grabación.
 *
 * @author Gabriel Arias.
 *
 */
public class GrabacionBusiness extends BaseBusiness
{
	/** Constante clh_LOGGER. */
	private static final LoggerHandler clh_LOGGER = (LoggerHandler)com.bachue.snr.prosnr01.common.utils.LoggingHelper
			.getLogger(GrabacionBusiness.class);

	/** Propiedad ifmb firma masiva business. */
	private static FirmaMasivaBusiness ifmb_firmaMasivaBusiness;

	/**
	 * Método encargado de consultar la justificación del documento generado.
	 *
	 * @param as_idTurno Variable de tipo String que contiene el id del turno.
	 * @param as_motivoTramite Variable de tipo String que contiene el motivo trámite.
	 * @return String que contiene la justificación encontrada.
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see OficiosTexto
	 */
	public synchronized OficiosTexto cargarOficioTexto(String as_idTurno, String as_motivoTramite)
	    throws B2BException
	{
		DAOManager   ldm_manager;
		OficiosTexto lot_return;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		lot_return      = null;

		try
		{
			if(StringUtils.isValidString(as_idTurno) && StringUtils.isValidString(as_motivoTramite))
			{
				String ls_tipoArchivo;

				ls_tipoArchivo = null;

				if(
				    as_motivoTramite.equalsIgnoreCase(
					        StringUtils.getString(MotivoTramiteCommon.GENERAR_RESOLUCION_DE_GRABACION)
					    )
				)
					ls_tipoArchivo = TipoArchivoCommon.RESOLUCION_GRABACION;
				else if(
				    as_motivoTramite.equalsIgnoreCase(
					        StringUtils.getString(MotivoTramiteCommon.NEGAR_SOLICITUD_DE_GRABACION)
					    )
				)
					ls_tipoArchivo = TipoArchivoCommon.NEGACION_GRABACION;

				if(StringUtils.isValidString(ls_tipoArchivo))
				{
					DocumentosSalida lds_documento;

					lds_documento = DaoCreator.getDocumentosSalidaDAO(ldm_manager)
							                      .findByIdTurnoTipo(as_idTurno, ls_tipoArchivo);

					if(lds_documento != null)
					{
						Integer li_idTurnoHistoria;

						li_idTurnoHistoria = lds_documento.getIdTurnoHistoria();

						if(NumericUtils.isValidInteger(li_idTurnoHistoria))
							lot_return = DaoCreator.getOficiosTextoDAO(ldm_manager)
									                   .findByTurnoHistoria(
									    NumericUtils.getLongWrapper(li_idTurnoHistoria)
									);
					}
				}
			}
			else
				throw new B2BException(ErrorKeys.ERROR_INTERNO_SISTEMA);
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("cargarOficioTexto", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lot_return;
	}

	/**
	 * Retorna el valor del objeto de DatosBasicos.
	 *
	 * @param ath_turnoHistoria correspondiente al valor del tipo de objeto TurnoHistoria
	 * @return devuelve el valor de DatosBasicos
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see DatosBasicos
	 */
	public synchronized DatosBasicos consultarDatosBasicos(TurnoHistoria ath_turnoHistoria)
	    throws B2BException
	{
		DAOManager   ldm_manager;
		DatosBasicos ldb_return;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		ldb_return      = null;

		try
		{
			DatosAntSistema ldas_return;

			ldas_return           = consultarPredioGrabar(ath_turnoHistoria, ldm_manager);
			ath_turnoHistoria     = DaoCreator.getTurnoHistoriaDAO(ldm_manager).findById(ath_turnoHistoria);

			if((ldas_return != null) && (ath_turnoHistoria != null))
			{
				String ls_idTurno;
				String ls_idSolicitud;

				ldb_return         = new DatosBasicos();
				ls_idTurno         = ath_turnoHistoria.getIdTurno();
				ls_idSolicitud     = ath_turnoHistoria.getIdSolicitud();

				ldb_return.setDatosAntSistema(ldas_return);

				if(StringUtils.isValidString(ls_idTurno) && StringUtils.isValidString(ls_idSolicitud))
				{
					Solicitud ls_solicitud;

					ls_solicitud = DaoCreator.getSolicitudDAO(ldm_manager).findById(ls_idSolicitud);

					if(ls_solicitud != null)
					{
						AccPredioRegistro lapr_predio;
						MatriculaBase     lmb_matriculaBase;
						String            ls_idCirculo;

						lapr_predio           = new AccPredioRegistro();
						lmb_matriculaBase     = new MatriculaBase();
						ls_idCirculo          = ldas_return.getIdCirculoGrabacion();

						lapr_predio.setIdCirculo(ls_idCirculo);
						lapr_predio.setIdMatricula(ldas_return.getIdMatriculaGrabacion());
						lapr_predio.setIdTurno(ls_idTurno);
						lmb_matriculaBase.setIdCirculo(ls_idCirculo);
						ldb_return.setMatriculaBase(lmb_matriculaBase);

						lapr_predio = DaoCreator.getAccPredioRegistroDAO(ldm_manager)
								                    .findByCirculoMatriculaTurno(lapr_predio);

						if(lapr_predio == null)
						{
							CirculoRegistral       lcr_circuloRegistral;
							UbicacionZonaRegistral luzr_ubicacion;

							lapr_predio              = new AccPredioRegistro();
							lcr_circuloRegistral     = new CirculoRegistral();
							luzr_ubicacion           = new UbicacionZonaRegistral();

							lapr_predio.setNombrePredio(ldas_return.getNombrePredio());
							lapr_predio.setIdTipoPredio(ldas_return.getIdTipoPredio());
							lcr_circuloRegistral.setIdCirculo(ldas_return.getIdCirculoGrabacion());

							lcr_circuloRegistral = DaoCreator.getCirculoRegistralDAO(ldm_manager)
									                             .findById(lcr_circuloRegistral);

							luzr_ubicacion.setCirculoRegistral(lcr_circuloRegistral);

							{
								Departamento ld_departamento;
								EstadoPredio lep_estadoPredio;
								Municipio    lm_municipio;
								String       ls_idPais;
								String       ls_idDepartamento;

								ld_departamento       = new Departamento();
								lep_estadoPredio      = new EstadoPredio();
								lm_municipio          = new Municipio();
								ls_idPais             = ldas_return.getIdPais();
								ls_idDepartamento     = ldas_return.getIdDepartamento();

								ld_departamento.setIdPais(ls_idPais);
								ld_departamento.setIdDepartamento(ls_idDepartamento);
								lm_municipio.setIdPais(ls_idPais);
								lm_municipio.setIdDepartamento(ls_idDepartamento);
								lm_municipio.setIdMunicipio(ldas_return.getIdMunicipio());

								ld_departamento     = DaoCreator.getDepartamentoDAO(ldm_manager)
										                            .findById(ld_departamento);
								lm_municipio        = DaoCreator.getMunicipioDAO(ldm_manager).findById(lm_municipio);

								luzr_ubicacion.setIdPais(ls_idPais);
								luzr_ubicacion.setDepartamento(ld_departamento);
								luzr_ubicacion.setMunicipio(lm_municipio);
								lep_estadoPredio.setIdEstadoPredio(EstadoCommon.S);
								luzr_ubicacion.setEstadoPredio(lep_estadoPredio);
							}

							ldb_return.setUbicacion(luzr_ubicacion);

							{
								Apertura  la_apertura;
								Documento ld_documento;

								la_apertura      = new Apertura();
								ld_documento     = new Documento();

								la_apertura.setFechaApertura(new Date());
								la_apertura.setRadicacion(ls_idTurno);

								ld_documento.setIdDocumento(ls_solicitud.getIdDocumento());

								ld_documento = DaoCreator.getDocumentoDAO(ldm_manager).findById(ld_documento);

								if(ld_documento != null)
								{
									OficinaOrigen loo_oficinaOrigen;
									String        ls_idOficinaOrigen;
									BigDecimal    lbd_version;

									loo_oficinaOrigen      = new OficinaOrigen();
									ls_idOficinaOrigen     = ld_documento.getIdOficinaOrigen();
									lbd_version            = ld_documento.getVersion();

									loo_oficinaOrigen.setIdOficinaOrigen(ls_idOficinaOrigen);
									loo_oficinaOrigen.setVersion(lbd_version);

									loo_oficinaOrigen = DaoCreator.getOficinaOrigenDAO(ldm_manager)
											                          .findById(loo_oficinaOrigen);

									if(loo_oficinaOrigen != null)
									{
										la_apertura.setIdTipoOficina(loo_oficinaOrigen.getIdTipoOficina());
										la_apertura.setIdPais(loo_oficinaOrigen.getIdPais());
										la_apertura.setIdDepartamento(loo_oficinaOrigen.getIdDepartamento());
										la_apertura.setIdMunicipio(loo_oficinaOrigen.getIdMunicipio());
									}

									la_apertura.setIdOficinaOrigen(ls_idOficinaOrigen);
									la_apertura.setVersion(lbd_version);
									la_apertura.setDocumento(ld_documento);
								}

								ldb_return.setApertura(la_apertura);
							}
						}

						ldb_return.setAccPredioRegistro(lapr_predio);
					}
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("consultarDatosBasicos", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return ldb_return;
	}

	/**
	 * Consultar existencia predio.
	 *
	 * @param as_idTurno correspondiente al valor del tipo de objeto String
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public synchronized void consultarExistenciaPredio(String as_idTurno)
	    throws B2BException
	{
		DAOManager ldm_manager;

		ldm_manager = DaoManagerFactory.getDAOManager();

		try
		{
			if(StringUtils.isValidString(as_idTurno))
			{
				Turno lt_turno;

				lt_turno = DaoCreator.getTurnoDAO(ldm_manager).findById(as_idTurno);

				if(lt_turno != null)
				{
					String ls_idSolicitud;

					ls_idSolicitud = lt_turno.getIdSolicitud();

					if(StringUtils.isValidString(ls_idSolicitud))
					{
						DatosAntSistema ldas_datosAntSistema;

						ldas_datosAntSistema = DaoCreator.getDatosAntSistemaDAO(ldm_manager)
								                             .findByIdSolicitudOne(ls_idSolicitud);

						if(ldas_datosAntSistema != null)
						{
							String ls_idCirculo;
							Long   ll_idMatricula;

							ls_idCirculo       = ldas_datosAntSistema.getIdCirculoGrabacion();
							ll_idMatricula     = ldas_datosAntSistema.getIdMatriculaGrabacion();

							if(StringUtils.isValidString(ls_idCirculo) && NumericUtils.isValidLong(ll_idMatricula))
							{
								PredioRegistro lpr_predio;

								lpr_predio = DaoCreator.getPredioRegistroDAO(ldm_manager)
										                   .findByCirculoMatricula(
										    ls_idCirculo, NumericUtils.getLong(ll_idMatricula)
										);

								if(lpr_predio != null)
								{
									Object[] loa_arg;

									loa_arg        = new String[1];
									loa_arg[0]     = ls_idCirculo + IdentificadoresCommon.SIMBOLO_GUION
										+ ll_idMatricula;

									throw new B2BException(ErrorKeys.PREDIO_DEFINITIVO_ANT_SISTEMA, loa_arg);
								}
							}
						}
					}
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("consultarExistenciaPredio", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}
	}

	/**
	 * Método encargado de consultar el interesado de una solicitud.
	 *
	 * @param as_idSolicitud Variable de tipo String que contiene el id de la solicitud.
	 * @param ab_fullData Variable de tipo boolean que valida si se deben consultar todos los datos.
	 * @return Objeto que contiene los datos del interesado consultado.
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see DatosDelInteresado
	 */
	public synchronized DatosDelInteresado consultarPersonaInteresado(String as_idSolicitud, boolean ab_fullData)
	    throws B2BException
	{
		DAOManager         ldm_manager;
		DatosDelInteresado lddi_return;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		lddi_return     = null;

		try
		{
			if(StringUtils.isValidString(as_idSolicitud))
			{
				Solicitud ls_solicitud;

				ls_solicitud = DaoCreator.getSolicitudDAO(ldm_manager).findById(as_idSolicitud);

				if(ls_solicitud != null)
				{
					String ls_idPersona;

					ls_idPersona = ls_solicitud.getIdPersona();

					if(StringUtils.isValidString(ls_idPersona))
					{
						lddi_return = new DatosDelInteresado();

						lddi_return.setPersona(DaoCreator.getPersonaDAO(ldm_manager).findById(ls_idPersona));

						if(ab_fullData)
						{
							String ls_idCorreo;
							String ls_idDireccion;
							String ls_idTelefono;

							ls_idCorreo        = ls_solicitud.getIdCorreoElectronico();
							ls_idDireccion     = ls_solicitud.getIdDireccion();
							ls_idTelefono      = ls_solicitud.getIdTelefono();

							if(StringUtils.isValidString(ls_idDireccion))
							{
								PersonaDireccion lpd_personaDireccion;

								lpd_personaDireccion = new PersonaDireccion();

								lpd_personaDireccion.setIdPersona(ls_idPersona);
								lpd_personaDireccion.setIdDireccion(ls_idDireccion);

								lpd_personaDireccion = DaoCreator.getPersonaDireccionDAO(ldm_manager)
										                             .findById(lpd_personaDireccion);

								if(lpd_personaDireccion != null)
								{
									String ls_tipoDireccion;

									ls_tipoDireccion = lpd_personaDireccion.getTipoDireccion();

									if(StringUtils.isValidString(ls_tipoDireccion))
									{
										if(ls_tipoDireccion.equalsIgnoreCase(EstadoCommon.C))
											lddi_return.setDireccionCorrespondencia(lpd_personaDireccion);
										else if(ls_tipoDireccion.equalsIgnoreCase(EstadoCommon.R))
											lddi_return.setDireccionResidencia(lpd_personaDireccion);

										lddi_return.setTipoDireccion(ls_tipoDireccion);
									}
								}
							}

							if(StringUtils.isValidString(ls_idCorreo))
							{
								PersonaCorreoElectronico lpce_personaCorreo;

								lpce_personaCorreo = new PersonaCorreoElectronico();

								lpce_personaCorreo.setIdPersona(ls_idPersona);
								lpce_personaCorreo.setIdCorreoElectronico(ls_idCorreo);

								lpce_personaCorreo = DaoCreator.getPersonaCorreoElectronicoDAO(ldm_manager)
										                           .findById(lpce_personaCorreo);

								if(lpce_personaCorreo != null)
								{
									lddi_return.setCorreoElectronico(lpce_personaCorreo);
									lddi_return.setDataCorreo(true);
								}
							}

							if(StringUtils.isValidString(ls_idTelefono))
							{
								PersonaTelefono lpt_personaTelefono;

								lpt_personaTelefono = new PersonaTelefono();

								lpt_personaTelefono.setIdPersona(ls_idPersona);
								lpt_personaTelefono.setIdTelefono(ls_idTelefono);

								lpt_personaTelefono = DaoCreator.getPersonaTelefonoDAO(ldm_manager)
										                            .findById(lpt_personaTelefono);

								if(lpt_personaTelefono != null)
								{
									String ls_tipoTelefono;

									ls_tipoTelefono = lpt_personaTelefono.getTipoTelefono();

									if(StringUtils.isValidString(ls_tipoTelefono))
									{
										if(ls_tipoTelefono.equalsIgnoreCase(EstadoCommon.F))
										{
											lddi_return.setTelefonoFijo(lpt_personaTelefono);
											lddi_return.setDataFijo(true);
										}
										else if(ls_tipoTelefono.equalsIgnoreCase(EstadoCommon.M))
										{
											lddi_return.setTelefonoMovil(lpt_personaTelefono);
											lddi_return.setDataMovil(true);
										}

										lddi_return.setTipoTelefono(ls_tipoTelefono);
									}
								}
							}
						}
					}
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("consultarPersonaInteresado", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lddi_return;
	}

	/**
	 * Método encargado de consultar los datos del predio a grabar.
	 *
	 * @param ath_turnoHistoria Objeto que contiene los datos para realizar la consulta.
	 * @return Objeto que contiene los datos del predio a grabar.
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see DatosAntSistema
	 */
	public synchronized DatosAntSistema consultarPredioGrabar(TurnoHistoria ath_turnoHistoria)
	    throws B2BException
	{
		DAOManager      ldm_manager;
		DatosAntSistema ldas_return;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		ldas_return     = null;

		try
		{
			ldas_return = consultarPredioGrabar(ath_turnoHistoria, ldm_manager);
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("consultarPredioGrabar", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return ldas_return;
	}

	/**
	 * Enviar aprobador 104.
	 *
	 * @param as_idTurnoHistoria correspondiente al valor del tipo de objeto String
	 * @param as_userId correspondiente al valor del tipo de objeto String
	 * @param as_ipRemote correspondiente al valor del tipo de objeto String
	 * @return verdadero, si se cumple la condicion, de lo contario retorna falso
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public synchronized boolean enviarAprobador104(String as_idTurnoHistoria, String as_userId, String as_ipRemote)
	    throws B2BException
	{
		boolean    lb_return;
		DAOManager ldm_manager;

		lb_return       = false;
		ldm_manager     = DaoManagerFactory.getDAOManager();

		try
		{
			if(StringUtils.isValidString(as_idTurnoHistoria))
			{
				TurnoHistoria    lth_turnoHistoria;
				TurnoHistoriaDAO lth_DAO;

				lth_turnoHistoria     = new TurnoHistoria();
				lth_DAO               = DaoCreator.getTurnoHistoriaDAO(ldm_manager);

				lth_turnoHistoria.setIdTurnoHistoria(NumericUtils.getLongWrapper(as_idTurnoHistoria));

				lth_turnoHistoria = lth_DAO.findById(lth_turnoHistoria);

				if(lth_turnoHistoria != null)
				{
					MotivoTramite lmt_motivo;

					lmt_motivo = new MotivoTramite();

					lmt_motivo.setIdEtapaOrigen(EtapaCommon.ID_ETAPA_EJECUCION_GRABACION_MATRICULAS);
					lmt_motivo.setIdMotivo(MotivoTramiteCommon.EJECUCION_GRABACION_MATRICULA);

					lmt_motivo = DaoCreator.getMotivoTramiteDAO(ldm_manager).findById(lmt_motivo);

					if(lmt_motivo != null)
					{
						lth_turnoHistoria.setEstadoActividad(EstadoCommon.TERMINADA);
						lth_turnoHistoria.setMotivo(lmt_motivo.getDescripcion());
						lth_turnoHistoria.setIdMotivo(NumericUtils.getLongWrapper(lmt_motivo.getIdMotivo()));
						lth_turnoHistoria.setIdUsuarioModificacion(as_userId);
						lth_turnoHistoria.setIpModificacion(as_ipRemote);

						lth_DAO.insertOrUpdate(lth_turnoHistoria, false);

						DaoCreator.getProcedimientosDAO(ldm_manager).spCreateStage(lth_turnoHistoria);

						lb_return = true;
					}
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("enviarAprobador104", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lb_return;
	}

	/**
	 * Método encargado de consultar la bandeja de grabación de matrículas etapa 104.
	 *
	 * @param as_userId Variable de tipo String que contiene el id del usuario que está realizando el trámite.
	 * @param as_idTurno Variable de tipo String que contiene el id turno filtro.
	 * @param as_nir Variable de tipo String que contiene el nir filtro.
	 * @return Colección que contiene los datos consultados.
	 * @throws B2BException Señala que se ha producido una excepción
	 *
	 */
	public synchronized Collection<TramiteCantidad> findInboxByUserId(
	    String as_userId, String as_idTurno, String as_nir
	)
	    throws B2BException
	{
		DAOManager                  ldm_manager;
		Collection<TramiteCantidad> lc_result;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		lc_result       = null;

		try
		{
			Etapa le_etapa;

			lc_result     = new ArrayList<TramiteCantidad>();
			le_etapa      = new Etapa();

			le_etapa.setIdEtapa(EtapaCommon.ID_ETAPA_EJECUCION_GRABACION_MATRICULAS);

			le_etapa = DaoCreator.getEtapaDAO(ldm_manager).findById(le_etapa);

			if(le_etapa != null)
			{
				LinkedList<LinkedHashMap<String, Object>> ll_data;
				LinkedHashMap<Integer, Object>            lhmpCriterios;
				String                                    ls_idSolicitud;

				lhmpCriterios      = new LinkedHashMap<Integer, Object>();
				ls_idSolicitud     = null;

				lhmpCriterios.put(new Integer(1), NumericUtils.getLongWrapper(le_etapa.getIdEtapa()));
				lhmpCriterios.put(new Integer(2), as_userId);

				AntiguoSistemaDAO li_du = DaoCreator.getAntiguoSistemaDAO(ldm_manager);

				if(StringUtils.isValidString(as_nir))
				{
					Solicitud ls_Solicitud = new Solicitud();

					ls_Solicitud.setNir(as_nir);

					ls_Solicitud = DaoCreator.getConsultaTrazabilidadDAO(ldm_manager).findByNIR(ls_Solicitud);

					if(ls_Solicitud != null)
						ls_idSolicitud = ls_Solicitud.getIdSolicitud();
					else
						ls_idSolicitud = EstadoCommon.NIR_NO_VALIDO;
				}

				ll_data = li_du.getCustonQueryIbox(as_idTurno, ls_idSolicitud, lhmpCriterios);

				if(CollectionUtils.isValidCollection(ll_data))
				{
					Iterator<LinkedHashMap<String, Object>> li_consulta;

					li_consulta = ll_data.iterator();

					while(li_consulta.hasNext())
					{
						LinkedHashMap<String, Object> llhm_actual;

						llhm_actual = li_consulta.next();

						if(llhm_actual != null)
						{
							String ls_cantidad;

							ls_cantidad = llhm_actual.get(IdentificadoresCommon.CANTIDAD).toString();

							lc_result.add(
							    new TramiteCantidad(
							        NumericUtils.getInteger(ls_cantidad),
							        NumericUtils.getLongWrapper(le_etapa.getIdEtapa()), le_etapa.getNombre()
							    )
							);
						}
					}
				}
				else
					lc_result.add(
					    new TramiteCantidad(
					        new Integer(0), NumericUtils.getLongWrapper(le_etapa.getIdEtapa()), le_etapa.getNombre()
					    )
					);
			}

			if(!CollectionUtils.isValidCollection(lc_result))
				lc_result = null;
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();
			clh_LOGGER.error("findInboxByUserId", lb2be_e);
			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lc_result;
	}

	/**
	 * Método encargado de generar el documento de Matrícula creada y salvar el documento.
	 *
	 * @param as_parametros Objeto que contiene la información para generar el pdf.
	 * @param as_userId Variable de tipo String que contiene el id del usuario que está realizando el trámite.
	 * @param as_remoteIp Variable de tipo String que contiene la ip del usuario que está realizando el trámite.
	 * @param ab_salvar Variable booleana que indica si se debe salvar el documento y la información.
	 * @param ab_firma Variable booleana que indica si se debe firmar el documento.
	 * @param adm_manager DaoManager que administra las transacciones.
	 * @return Variable de tipo byte array que contiene el documento generado.
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see byte[]
	 */
	public synchronized byte[] generarDocumentoMatriculaCreada(
	    Suspension as_parametros, String as_userId, String as_remoteIp, boolean ab_salvar, boolean ab_firma,
	    DAOManager adm_manager
	)
	    throws B2BException
	{
		byte[] lba_return;

		lba_return = null;

		if(as_parametros != null)
		{
			TurnoHistoria lth_turnoHistoria;

			lth_turnoHistoria     = as_parametros.getTurnoHistoria();

			lth_turnoHistoria = DaoCreator.getTurnoHistoriaDAO(adm_manager).findById(lth_turnoHistoria);

			if(lth_turnoHistoria != null)
			{
				Long   ll_idTurnoHistoria;
				String ls_idTurno;

				ll_idTurnoHistoria     = lth_turnoHistoria.getIdTurnoHistoria();
				ls_idTurno             = lth_turnoHistoria.getIdTurno();

				if(StringUtils.isValidString(ls_idTurno) && NumericUtils.isValidLong(ll_idTurnoHistoria))
				{
					Turno lt_turno;

					lt_turno = DaoCreator.getTurnoDAO(adm_manager).findById(ls_idTurno);

					if(lt_turno != null)
					{
						Constantes lc_constante;
						String     ls_constante;

						byte[] lba_plantilla;

						ls_constante     = ConstanteCommon.PLANTILLA_OFICIO_MATRICULA_CREADA;
						lc_constante     = new Constantes();

						lc_constante.setIdConstante(ls_constante);

						lc_constante = DaoCreator.getConstantesDAO(adm_manager).findImagen(lc_constante);

						if(lc_constante == null)
						{
							Object[] loa_messageArgs;

							loa_messageArgs        = new String[1];
							loa_messageArgs[0]     = ls_constante;

							throw new B2BException(ErrorKeys.ERROR_SIN_CONSTANTE, loa_messageArgs);
						}

						lba_plantilla = lc_constante.getImagenes();

						if(lba_plantilla == null)
						{
							Object[] loa_messageArgs = new String[1];
							loa_messageArgs[0] = ls_constante;

							throw new B2BException(ErrorKeys.NO_IMAGEN_CONSTANTE, loa_messageArgs);
						}
						else
						{
							Map<String, String> lmss_datos;
							String              ls_idCirculo;
							String              ls_idSolicitud;
							String              ls_plantilla;
							String              ls_tag;

							lmss_datos         = null;
							ls_idCirculo       = lt_turno.getIdCirculo();
							ls_idSolicitud     = lt_turno.getIdSolicitud();
							ls_plantilla       = new String(lba_plantilla);

							{
								ls_tag = "<TAG_ACC_TUR_ID_TURNO>";

								if(ls_plantilla.contains(ls_tag))
									ls_plantilla = ls_plantilla.replace(ls_tag, ls_idTurno);
							}

							if(StringUtils.isValidString(ls_idCirculo))
							{
								CirculoRegistral lcr_circuloRegistral;

								lcr_circuloRegistral = new CirculoRegistral();

								lcr_circuloRegistral.setIdCirculo(ls_idCirculo);

								lcr_circuloRegistral = DaoCreator.getCirculoRegistralDAO(adm_manager)
										                             .findById(lcr_circuloRegistral);

								if(lcr_circuloRegistral != null)
								{
									ls_tag = "<TAG_NOMBRE_ORIP>";

									if(ls_plantilla.contains(ls_tag))
									{
										String ls_nombre;

										ls_nombre = lcr_circuloRegistral.getNombre();

										if(StringUtils.isValidString(ls_nombre))
											ls_plantilla = ls_plantilla.replace(ls_tag, ls_nombre);
									}

									ls_tag = "<TAG_MUN_OFI_ORIGEN>";

									if(ls_plantilla.contains(ls_tag) && StringUtils.isValidString(ls_idCirculo))
									{
										Municipio lm_municipio;

										lm_municipio     = new Municipio();

										lm_municipio = DaoCreator.getMunicipioDAO(adm_manager)
												                     .findByIdCirculo(ls_idCirculo);

										if(lm_municipio != null)
										{
											String ls_munOficinaOrigen;
											ls_munOficinaOrigen = lm_municipio.getNombre();

											if(
											    ls_plantilla.contains(ls_tag)
												    && StringUtils.isValidString(ls_munOficinaOrigen)
											)
												ls_plantilla = ls_plantilla.replace(ls_tag, ls_munOficinaOrigen);
										}
									}
								}
							}

							String     ls_tagUsuarioFirma;
							Constantes lc_usuarioFirma;

							ls_tagUsuarioFirma     = null;
							lc_usuarioFirma        = null;

							{
								ls_tagUsuarioFirma = "<TAG_USUARIO_FIRMA_SUSPENSION>";

								if(ls_plantilla.contains(ls_tagUsuarioFirma))
								{
									lc_usuarioFirma = new Constantes();

									lc_usuarioFirma.setIdConstante(ConstanteCommon.USUARIO_FIRMA_SUSPENSION);

									lc_usuarioFirma     = DaoCreator.getConstantesDAO(adm_manager)
											                            .findByIdWithImage(lc_usuarioFirma);

									ls_plantilla = getFirmaMasivaBusiness()
											               .reemplazarUsuarioFirmaCargo(
											    ls_plantilla, lc_usuarioFirma, ls_tagUsuarioFirma,
											    "<TAG_CARGO_FIRMA_SUSPENSION>"
											);
								}
							}

							{
								ls_tag = "<TAG_USUARIO>";

								if(ls_plantilla.contains(ls_tag))
									ls_plantilla = ls_plantilla.replace(ls_tag, as_userId);
							}

							{
								ls_tag = "<TAG_FECHA_LARGA>";

								if(ls_plantilla.contains(ls_tag))
								{
									String ls_fecha;

									ls_fecha = DateUtils.convertirLetrasLarga(new Date());

									if(StringUtils.isValidString(ls_fecha))
										ls_plantilla = ls_plantilla.replace(ls_tag, StringUtils.getString(ls_fecha));
								}
							}

							{
								OficiosTexto lof_data;

								lof_data = new OficiosTexto();

								lof_data.setIdTurnoHistoria(ll_idTurnoHistoria);

								lof_data = DaoCreator.getOficiosTextoDAO(adm_manager).findByTurnoHistoria(lof_data);

								if(lof_data != null)
								{
									String ls_consideraciones;

									ls_consideraciones     = lof_data.getConsideracion();
									ls_tag                 = "<TAG_CONSIDERACIONES_OFICIO>";

									if(StringUtils.isValidString(ls_consideraciones) && ls_plantilla.contains(ls_tag))
										ls_plantilla = ls_plantilla.replace(
											    ls_tag, StringUtils.getString(ls_consideraciones)
											);
								}
							}

							{
								if(StringUtils.isValidString(ls_idSolicitud))
								{
									Solicitud ls_solicitud;

									ls_solicitud = DaoCreator.getSolicitudDAO(adm_manager).findById(ls_idSolicitud);

									if(ls_solicitud != null)
									{
										String ls_idPersona;

										ls_idPersona = ls_solicitud.getIdPersona();

										if(StringUtils.isValidString(ls_idPersona))
										{
											String ls_idDireccion;

											ls_idDireccion     = ls_solicitud.getIdDireccion();
											ls_tag             = "<TAG_NOMBRE_INTERESADO>";

											if(ls_plantilla.contains(ls_tag))
											{
												Persona lp_persona;

												lp_persona = DaoCreator.getPersonaDAO(adm_manager).findById(
													    ls_idPersona
													);

												if(lp_persona != null)
												{
													StringBuilder lsb_nombre;
													String        ls_nombre;
													String        ls_segundoApellido;
													String        ls_segundoNombre;

													lsb_nombre             = new StringBuilder();
													ls_segundoApellido     = lp_persona.getSegundoApellido();
													ls_segundoNombre       = lp_persona.getSegundoNombre();

													lsb_nombre.append(lp_persona.getPrimerNombre());

													if(StringUtils.isValidString(ls_segundoNombre))
														lsb_nombre.append(
														    IdentificadoresCommon.ESPACIO_VACIO + ls_segundoNombre
														);

													lsb_nombre.append(
													    IdentificadoresCommon.ESPACIO_VACIO
													    + lp_persona.getPrimerApellido()
													);

													if(StringUtils.isValidString(ls_segundoApellido))
														lsb_nombre.append(
														    IdentificadoresCommon.ESPACIO_VACIO + ls_segundoApellido
														);

													ls_nombre = lsb_nombre.toString();

													if(StringUtils.isValidString(ls_nombre))
														ls_plantilla = ls_plantilla.replace(ls_tag, ls_nombre);
												}
											}

											ls_tag = "<TAG_CORREO_ELECTRONICO>";

											if(ls_plantilla.contains(ls_tag))
											{
												String ls_idCorreo;

												ls_idCorreo = ls_solicitud.getIdCorreoElectronico();

												if(StringUtils.isValidString(ls_idCorreo))
												{
													PersonaCorreoElectronico lpce_correo;

													lpce_correo = new PersonaCorreoElectronico();

													lpce_correo.setIdPersona(ls_idPersona);
													lpce_correo.setIdCorreoElectronico(ls_idCorreo);

													lpce_correo = DaoCreator.getPersonaCorreoElectronicoDAO(
														    adm_manager
														).findById(lpce_correo);

													if(lpce_correo != null)
													{
														String ls_correo;

														ls_correo = lpce_correo.getCorreoElectronico();

														if(StringUtils.isValidString(ls_correo))
															ls_plantilla = saltoDeCarroDespues(
																    ls_plantilla, ls_tag, ls_correo
																);
													}
												}
											}

											if(StringUtils.isValidString(ls_idDireccion))
											{
												PersonaDireccion lpd_personaDireccion;

												lpd_personaDireccion = new PersonaDireccion();

												lpd_personaDireccion.setIdPersona(ls_idPersona);
												lpd_personaDireccion.setIdDireccion(ls_idDireccion);

												lpd_personaDireccion = DaoCreator.getPersonaDireccionDAO(adm_manager)
														                             .findById(lpd_personaDireccion);

												if(lpd_personaDireccion != null)
												{
													ls_tag = "<TAG_DIR_INTERESADO>";

													if(ls_plantilla.contains(ls_tag))
													{
														String ls_direccion;

														ls_direccion = lpd_personaDireccion.getDireccion();

														if(StringUtils.isValidString(ls_direccion))
															ls_plantilla = saltoDeCarroDespues(
																    ls_plantilla, ls_tag, ls_direccion
																);
													}

													ls_tag = "<TAG_DEPAR_INTERESADO>";

													if(
													    ls_plantilla.contains(ls_tag)
														    && ls_plantilla.contains("<TAG_MUNICIPIO_INTERESADO>")
													)
													{
														Departamento ld_departamento;
														Municipio    lm_municipio;

														ld_departamento     = new Departamento();
														lm_municipio        = new Municipio();

														ld_departamento.setIdPais(lpd_personaDireccion.getIdPais());
														ld_departamento.setIdDepartamento(
														    lpd_personaDireccion.getIdDepartamento()
														);
														lm_municipio.setIdPais(lpd_personaDireccion.getIdPais());
														lm_municipio.setIdDepartamento(
														    lpd_personaDireccion.getIdDepartamento()
														);
														lm_municipio.setIdMunicipio(
														    lpd_personaDireccion.getIdMunicipio()
														);

														lm_municipio     = DaoCreator.getMunicipioDAO(adm_manager)
																                         .findById(lm_municipio);

														ld_departamento = DaoCreator.getDepartamentoDAO(adm_manager)
																                        .findById(ld_departamento);

														if((ld_departamento != null) && (lm_municipio != null))
														{
															ld_departamento.setNombreDepartamento(
															    ld_departamento.getNombre()
															);

															if(
															    StringUtils.isValidString(
																        ld_departamento.getNombreDepartamento()
																    )
																    && StringUtils.isValidString(
																        lm_municipio.getNombre()
																    )
															)
																ls_plantilla = escribirDepartamentoMunicipioInteresado(
																	    ls_plantilla, "<TAG_MUNICIPIO_INTERESADO>",
																	    lm_municipio.getNombre(), ls_tag,
																	    ld_departamento.getNombreDepartamento()
																	);
														}
													}
													else if(ls_plantilla.contains(ls_tag))
													{
														Departamento ld_departamento;

														ld_departamento = new Departamento();

														ld_departamento.setIdPais(lpd_personaDireccion.getIdPais());
														ld_departamento.setIdDepartamento(
														    lpd_personaDireccion.getIdDepartamento()
														);

														ld_departamento = DaoCreator.getDepartamentoDAO(adm_manager)
																                        .findById(ld_departamento);

														if(ld_departamento != null)
														{
															String ls_nombre;

															ls_nombre = ld_departamento.getNombre();

															if(StringUtils.isValidString(ls_nombre))
																ls_plantilla = saltoDeCarroDespues(
																	    ls_plantilla, ls_tag, ls_nombre
																	);
														}
													}
													else
													{
														ls_tag = "<TAG_MUNICIPIO_INTERESADO>";

														if(ls_plantilla.contains(ls_tag))
														{
															Municipio lm_municipio;

															lm_municipio = new Municipio();

															lm_municipio.setIdPais(lpd_personaDireccion.getIdPais());
															lm_municipio.setIdDepartamento(
															    lpd_personaDireccion.getIdDepartamento()
															);
															lm_municipio.setIdMunicipio(
															    lpd_personaDireccion.getIdMunicipio()
															);

															lm_municipio = DaoCreator.getMunicipioDAO(adm_manager)
																	                     .findById(lm_municipio);

															if(lm_municipio != null)
															{
																String ls_nombre;

																ls_nombre = lm_municipio.getNombre();

																if(StringUtils.isValidString(ls_nombre))
																	ls_plantilla = saltoDeCarroDespues(
																		    ls_plantilla, ls_tag, ls_nombre
																		);
															}
														}
													}
												}
											}
										}
									}
								}
							}

							String ls_referenciaSalida;
							String ls_consecutivoOficio;
							String ls_consecutivoResolucion;
							Long   ll_consecutivoResolucion;

							ls_referenciaSalida          = null;
							ls_consecutivoOficio         = null;
							ls_consecutivoResolucion     = null;
							ll_consecutivoResolucion     = null;

							if(ab_firma)
							{
								ls_tag = "<TAG_OFICIO>";

								if(ls_plantilla.contains(ls_tag))
								{
									NumeracionOficiosCirculo lnoc_numeracionOficios;
									lnoc_numeracionOficios = findNumeracionOficiosCirculo(
										    lth_turnoHistoria, adm_manager, as_userId, as_remoteIp
										);

									if(lnoc_numeracionOficios != null)
									{
										ls_consecutivoOficio = lnoc_numeracionOficios.getConsecutivoGenerado();

										if(StringUtils.isValidString(ls_consecutivoOficio))
											ls_plantilla = ls_plantilla.replace(ls_tag, ls_consecutivoOficio);
									}
								}

								ls_tag = "<TAG_REFERENCIA_SALIDA>";

								if(ls_plantilla.contains(ls_tag))
								{
									ls_referenciaSalida = generarIdCorrespondencia(
										    lth_turnoHistoria, adm_manager, true
										);

									if(StringUtils.isValidString(ls_referenciaSalida))
										ls_plantilla = ls_plantilla.replace(ls_tag, ls_referenciaSalida);
								}

								{
									ls_tag = "<TAG_RESOLUCION>";

									if(ls_plantilla.contains(ls_tag))
									{
										DocumentosSalida ldsr_documentosSalidaResolucion;

										ldsr_documentosSalidaResolucion = DaoCreator.getDocumentosSalidaDAO(
											    adm_manager
											)
												                                        .findByIdTurnoTipo(
												    lth_turnoHistoria.getIdTurno(),
												    TipoArchivoCommon.RESOLUCION_GRABACION, true, null
												);

										if(ldsr_documentosSalidaResolucion != null)
										{
											ls_consecutivoResolucion     = String.format(
												    "%06d", ldsr_documentosSalidaResolucion.getConsecutivoResolucion()
												);
											ll_consecutivoResolucion     = ldsr_documentosSalidaResolucion
													.getConsecutivoResolucion();
										}

										if(StringUtils.isValidString(ls_consecutivoResolucion))
											ls_plantilla = ls_plantilla.replace(ls_tag, ls_consecutivoResolucion);
									}
								}

								{
									ls_tag = "<TAG_FECHA_RESOL>";

									if(ls_plantilla.contains(ls_tag))
									{
										Date             ld_date;
										SimpleDateFormat formatter;
										String           ls_date;

										ld_date       = new Date();
										formatter     = new SimpleDateFormat("dd/MM/yyyy");
										ls_date       = StringUtils.getStringNotNull(formatter.format(ld_date));

										if(StringUtils.isValidString(ls_date))
											ls_plantilla = ls_plantilla.replace(ls_tag, ls_date);
									}
								}
							}

							lmss_datos       = finalizarPlantilla(ls_plantilla, ll_idTurnoHistoria, adm_manager);
							ls_plantilla     = lmss_datos.get(IdentificadoresCommon.PLANTILLA);
							lba_return       = new PDFGenerator().convertirRTFToPDF(
								    ls_plantilla.getBytes(), adm_manager
								);

							if(lba_return == null)
								throw new B2BException(ErrorKeys.NO_SE_GENERO_ARCHIVO);

							if(ab_firma)
							{
								byte[] lba_grafo;

								lba_grafo = null;

								int li_incrX = 0;
								int li_incrY = 0;

								if(lc_usuarioFirma != null)
								{
									lba_grafo     = lc_usuarioFirma.getImagenes();
									li_incrX      = NumericUtils.getInt(lc_usuarioFirma.getEntero());
									li_incrY      = NumericUtils.getInt(lc_usuarioFirma.getReal());
								}

								lba_return = getFirmaMasivaBusiness()
										             .reemplazarBookmarsFirma(
										    lba_return, lba_grafo, li_incrX, li_incrY
										);
							}

							if(ab_salvar)
							{
								Imagenes    li_imagenes;
								ImagenesDAO li_DAO;
								long        ll_idImagenTemp;

								li_imagenes     = new Imagenes();
								li_DAO          = DaoCreator.getImagenesDAO(adm_manager);

								li_imagenes.setTipoArchivo(ExtensionCommon.PDF_MAYUS);
								li_imagenes.setIdUsuarioCreacion(as_userId);
								li_imagenes.setIpCreacion(as_remoteIp);
								li_imagenes.setImagenBlob(lba_return);
								li_imagenes.setCodigoVerificacion(
								    lmss_datos.get(IdentificadoresCommon.CODIGO_VERIFICACION)
								);

								ll_idImagenTemp = li_DAO.insertOrUpdate(li_imagenes, true);

								if(ll_idImagenTemp > 0)
								{
									DocumentosSalida    lds_documentoSalida;
									DocumentosSalidaDAO lds_DAO;
									Long                ll_idImagen;

									lds_documentoSalida     = new DocumentosSalida();
									lds_DAO                 = DaoCreator.getDocumentosSalidaDAO(adm_manager);
									ll_idImagen             = NumericUtils.getLongWrapper(ll_idImagenTemp);

									lds_documentoSalida.setIdSolicitud(ls_idSolicitud);
									lds_documentoSalida.setIdTurno(ls_idTurno);
									lds_documentoSalida.setTipo(TipoArchivoCommon.OFICIO_MATRICULA_GRABADA);
									lds_documentoSalida.setIdTipoDocumental(TipoDocumentalCommon.OFICIO);

									lds_documentoSalida.setRepositorio(
									    ab_firma ? RepositorioCommon.FINAL : RepositorioCommon.TEMPORAL
									);

									lds_documentoSalida.setTipoArchivo(ExtensionCommon.PDF_MAYUS);
									lds_documentoSalida.setEstado(EstadoCommon.ACTIVO);
									lds_documentoSalida.setReferenciaSalida(ls_idTurno);

									lds_documentoSalida = lds_DAO.findDocumentByTurnoTipoEstadoReferenciaSalida(
										    lds_documentoSalida
										);

									if(lds_documentoSalida != null)
									{
										lds_documentoSalida.setIdImagen(ll_idImagen);
										lds_documentoSalida.setIdUsuarioModificacion(as_userId);
										lds_documentoSalida.setIpModificacion(as_remoteIp);

										lds_DAO.updateImagen(lds_documentoSalida);
									}
									else
									{
										lds_documentoSalida = new DocumentosSalida();

										lds_documentoSalida.setIdTurno(ls_idTurno);
										lds_documentoSalida.setIdSolicitud(ls_idSolicitud);
										lds_documentoSalida.setIdImagen(ll_idImagen);
										lds_documentoSalida.setTipo(TipoArchivoCommon.OFICIO_MATRICULA_GRABADA);
										lds_documentoSalida.setIdTipoDocumental(TipoDocumentalCommon.OFICIO);
										lds_documentoSalida.setRepositorio(
										    ab_firma ? RepositorioCommon.FINAL : RepositorioCommon.TEMPORAL
										);
										lds_documentoSalida.setEstado(EstadoCommon.ACTIVO);
										lds_documentoSalida.setReferenciaSalida(ls_referenciaSalida);
										lds_documentoSalida.setConsecutivoOficio(ls_consecutivoOficio);
										lds_documentoSalida.setConsecutivoResolucion(ll_consecutivoResolucion);
										lds_documentoSalida.setFechaOficio(ab_firma ? new Date() : null);
										lds_documentoSalida.setFechaResolucion(ab_firma ? new Date() : null);
										lds_documentoSalida.setIdTurnoHistoria(
										    NumericUtils.getInteger(ll_idTurnoHistoria)
										);
										lds_documentoSalida.setIdUsuarioCreacion(as_userId);
										lds_documentoSalida.setIpCreacion(as_remoteIp);

										lds_DAO.insertOrUpdate(lds_documentoSalida, true);
									}
								}
								else
									throw new B2BException(ErrorKeys.NO_SE_GENERO_ARCHIVO);
							}
						}
					}
				}
			}
		}

		return lba_return;
	}

	/**
	 *  Método encargado de generar el documento de negación y salva la información del proceso.
	 *
	 * @param as_parametros Objeto que contiene la información para generar el pdf.
	 * @param as_userId Variable de tipo String que contiene el id del usuario que está realizando el trámite.
	 * @param as_remoteIp Variable de tipo String que contiene la ip del usuario que está realizando el trámite.
	 * @param ab_salvar Variable booleana que indica si se debe salvar el documento y la información.
	 * @param ab_firma Variable booleana que indica si se debe firmar el documento.
	 * @return Variable de tipo byte array que contiene el documento generado.
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see byte[]
	 */
	public synchronized byte[] generarDocumentoNegacion(
	    Suspension as_parametros, String as_userId, String as_remoteIp, boolean ab_salvar, boolean ab_firma
	)
	    throws B2BException
	{
		byte[]     lba_return;
		DAOManager ldm_manager;

		lba_return      = null;
		ldm_manager     = DaoManagerFactory.getDAOManager();

		try
		{
			lba_return = generarDocumentoNegacion(
				    as_parametros, as_userId, as_remoteIp, ab_salvar, ab_firma, ldm_manager
				);
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("generarDocumentoNegacion", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lba_return;
	}

	/**
	 * Método encargado de generar el documento de negación y salva la información del proceso.
	 *
	 * @param as_parametros Objeto que contiene la información para generar el pdf.
	 * @param as_userId Variable de tipo String que contiene el id del usuario que está realizando el trámite.
	 * @param as_remoteIp Variable de tipo String que contiene la ip del usuario que está realizando el trámite.
	 * @param ab_salvar Variable booleana que indica si se debe salvar el documento y la información.
	 * @param ab_firma Variable booleana que indica si se debe firmar el documento.
	 * @param adm_manager DaoManager que administra las transacciones.
	 * @return Variable de tipo byte array que contiene el documento generado.
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see byte[]
	 */
	public synchronized byte[] generarDocumentoNegacion(
	    Suspension as_parametros, String as_userId, String as_remoteIp, boolean ab_salvar, boolean ab_firma,
	    DAOManager adm_manager
	)
	    throws B2BException
	{
		byte[] lba_return;

		lba_return = null;

		if(as_parametros != null)
		{
			TurnoHistoria lth_turnoHistoria;

			lth_turnoHistoria     = as_parametros.getTurnoHistoria();

			lth_turnoHistoria = DaoCreator.getTurnoHistoriaDAO(adm_manager).findById(lth_turnoHistoria);

			if(lth_turnoHistoria != null)
			{
				Long   ll_idTurnoHistoria;
				String ls_idTurno;

				ll_idTurnoHistoria     = lth_turnoHistoria.getIdTurnoHistoria();
				ls_idTurno             = lth_turnoHistoria.getIdTurno();

				String ls_referenciaSalida;
				String ls_consecutivoOficio;

				ls_referenciaSalida    = null;
				ls_consecutivoOficio   = null;

				{
					Turno lt_turno;

					lt_turno = DaoCreator.getTurnoDAO(adm_manager).findById(ls_idTurno);

					if(lt_turno != null)
					{
						Constantes lc_constante;
						String     ls_constante;

						byte[] lba_plantilla;

						ls_constante     = ConstanteCommon.PLANTILLA_NEGACION_SOLICITUD_GRABACION;
						lc_constante     = new Constantes();

						lc_constante.setIdConstante(ls_constante);

						lc_constante = DaoCreator.getConstantesDAO(adm_manager).findImagen(lc_constante);

						if(lc_constante == null)
						{
							Object[] loa_messageArgs = new String[1];
							loa_messageArgs[0] = ls_constante;

							throw new B2BException(ErrorKeys.ERROR_SIN_CONSTANTE, loa_messageArgs);
						}

						lba_plantilla = lc_constante.getImagenes();

						if(lba_plantilla == null)
						{
							Object[] loa_messageArgs = new String[1];
							loa_messageArgs[0] = ls_constante;

							throw new B2BException(ErrorKeys.NO_IMAGEN_CONSTANTE, loa_messageArgs);
						}
						else
						{
							Map<String, String> lmss_datos;
							String              ls_idCirculo;
							String              ls_idSolicitud;
							String              ls_plantilla;
							String              ls_tag;

							lmss_datos         = null;
							ls_idCirculo       = lt_turno.getIdCirculo();
							ls_idSolicitud     = lt_turno.getIdSolicitud();
							ls_plantilla       = new String(lba_plantilla);

							{
								ls_tag = "<TAG_ACC_TUR_ID_TURNO>";

								if(ls_plantilla.contains(ls_tag))
									ls_plantilla = ls_plantilla.replace(ls_tag, ls_idTurno);
							}

							if(StringUtils.isValidString(ls_idCirculo))
							{
								CirculoRegistral lcr_circuloRegistral;

								lcr_circuloRegistral = new CirculoRegistral();

								lcr_circuloRegistral.setIdCirculo(ls_idCirculo);

								lcr_circuloRegistral = DaoCreator.getCirculoRegistralDAO(adm_manager)
										                             .findById(lcr_circuloRegistral);

								if(lcr_circuloRegistral != null)
								{
									ls_tag = "<TAG_NOMBRE_ORIP>";

									if(ls_plantilla.contains(ls_tag))
									{
										String ls_nombre;

										ls_nombre = lcr_circuloRegistral.getNombre();

										if(StringUtils.isValidString(ls_nombre))
											ls_plantilla = ls_plantilla.replace(ls_tag, ls_nombre);
									}

									ls_tag = "<TAG_MUN_OFI_ORIGEN>";

									if(ls_plantilla.contains(ls_tag) && StringUtils.isValidString(ls_idCirculo))
									{
										Municipio lm_municipio;

										lm_municipio     = new Municipio();

										lm_municipio = DaoCreator.getMunicipioDAO(adm_manager)
												                     .findByIdCirculo(ls_idCirculo);

										if(lm_municipio != null)
										{
											String ls_munOficinaOrigen;
											ls_munOficinaOrigen = lm_municipio.getNombre();

											if(
											    ls_plantilla.contains(ls_tag)
												    && StringUtils.isValidString(ls_munOficinaOrigen)
											)
												ls_plantilla = ls_plantilla.replace(ls_tag, ls_munOficinaOrigen);
										}
									}
								}
							}

							if(ab_firma)
							{
								ls_tag = "<TAG_OFICIO>";

								NumeracionOficiosCirculo lnoc_numeracionOficios;
								lnoc_numeracionOficios = findNumeracionOficiosCirculo(
									    lth_turnoHistoria, adm_manager, as_userId, as_remoteIp
									);

								if(lnoc_numeracionOficios != null)
								{
									ls_consecutivoOficio = lnoc_numeracionOficios.getConsecutivoGenerado();

									if(ls_plantilla.contains(ls_tag))
										ls_plantilla = ls_plantilla.replace(ls_tag, ls_consecutivoOficio);
								}

								ls_tag     = "<TAG_REFEENCIA_SALIDA>";

								ls_referenciaSalida = generarIdCorrespondencia(lth_turnoHistoria, adm_manager, true);

								if(ls_plantilla.contains(ls_tag))
									ls_plantilla = ls_plantilla.replace(ls_tag, ls_referenciaSalida);
							}

							String     ls_tagUsuarioFirma;
							Constantes lc_usuarioFirma;

							ls_tagUsuarioFirma     = null;
							lc_usuarioFirma        = null;

							{
								ls_tagUsuarioFirma = "<TAG_USUARIO_FIRMA_SUSPENSION>";

								if(ls_plantilla.contains(ls_tagUsuarioFirma))
								{
									lc_usuarioFirma = new Constantes();

									lc_usuarioFirma.setIdConstante(ConstanteCommon.USUARIO_FIRMA_SUSPENSION);

									lc_usuarioFirma     = DaoCreator.getConstantesDAO(adm_manager)
											                            .findByIdWithImage(lc_usuarioFirma);

									ls_plantilla = getFirmaMasivaBusiness()
											               .reemplazarUsuarioFirmaCargo(
											    ls_plantilla, lc_usuarioFirma, ls_tagUsuarioFirma,
											    "<TAG_CARGO_FIRMA_SUSPENSION>"
											);
								}
							}

							{
								ls_tag = "<TAG_USUARIO>";

								if(ls_plantilla.contains(ls_tag))
									ls_plantilla = ls_plantilla.replace(ls_tag, as_userId);
							}

							{
								ls_tag = "<TAG_FECHA_LARGA>";

								if(ls_plantilla.contains(ls_tag))
								{
									String ls_fecha;

									ls_fecha = DateUtils.convertirLetrasLarga(new Date());

									if(StringUtils.isValidString(ls_fecha))
										ls_plantilla = ls_plantilla.replace(ls_tag, StringUtils.getString(ls_fecha));
								}
							}

							ls_tag = "<TAG_CONSIDERACIONES_OFICIO>";

							if(ls_plantilla.contains(ls_tag))
							{
								String ls_consideraciones;

								ls_consideraciones = as_parametros.getConsideracion();

								if(ab_salvar && ab_firma)
								{
									OficiosTexto lof_data;

									lof_data = new OficiosTexto();

									lof_data.setIdTurnoHistoria(
									    NumericUtils.getLongWrapper(lth_turnoHistoria.getIdAnterior())
									);

									lof_data = DaoCreator.getOficiosTextoDAO(adm_manager).findByTurnoHistoria(lof_data);

									if(lof_data != null)
										ls_consideraciones = lof_data.getConsideracion();
								}

								if(StringUtils.isValidString(ls_consideraciones))
									ls_plantilla = ls_plantilla.replace(
										    ls_tag, StringUtils.getString(ls_consideraciones)
										);
							}

							{
								if(StringUtils.isValidString(ls_idSolicitud))
								{
									Solicitud ls_solicitud;

									ls_solicitud = DaoCreator.getSolicitudDAO(adm_manager).findById(ls_idSolicitud);

									if(ls_solicitud != null)
									{
										String ls_idPersona;

										ls_idPersona = ls_solicitud.getIdPersona();

										if(StringUtils.isValidString(ls_idPersona))
										{
											String ls_idDireccion;

											ls_idDireccion     = ls_solicitud.getIdDireccion();
											ls_tag             = "<TAG_NOMBRE_INTERESADO>";

											if(ls_plantilla.contains(ls_tag))
											{
												Persona lp_persona;

												lp_persona = DaoCreator.getPersonaDAO(adm_manager).findById(
													    ls_idPersona
													);

												if(lp_persona != null)
												{
													StringBuilder lsb_nombre;
													String        ls_nombre;
													String        ls_segundoApellido;
													String        ls_segundoNombre;

													lsb_nombre             = new StringBuilder();
													ls_segundoApellido     = lp_persona.getSegundoApellido();
													ls_segundoNombre       = lp_persona.getSegundoNombre();

													lsb_nombre.append(lp_persona.getPrimerNombre());

													if(StringUtils.isValidString(ls_segundoNombre))
														lsb_nombre.append(
														    IdentificadoresCommon.ESPACIO_VACIO + ls_segundoNombre
														);

													lsb_nombre.append(
													    IdentificadoresCommon.ESPACIO_VACIO
													    + lp_persona.getPrimerApellido()
													);

													if(StringUtils.isValidString(ls_segundoApellido))
														lsb_nombre.append(
														    IdentificadoresCommon.ESPACIO_VACIO + ls_segundoApellido
														);

													ls_nombre = lsb_nombre.toString();

													if(StringUtils.isValidString(ls_nombre))
														ls_plantilla = ls_plantilla.replace(ls_tag, ls_nombre);
												}
											}

											ls_tag = "<TAG_CORREO_ELECTRONICO>";

											if(ls_plantilla.contains(ls_tag))
											{
												String ls_idCorreo;

												ls_idCorreo = ls_solicitud.getIdCorreoElectronico();

												if(StringUtils.isValidString(ls_idCorreo))
												{
													PersonaCorreoElectronico lpce_correo;

													lpce_correo = new PersonaCorreoElectronico();

													lpce_correo.setIdPersona(ls_idPersona);
													lpce_correo.setIdCorreoElectronico(ls_idCorreo);

													lpce_correo = DaoCreator.getPersonaCorreoElectronicoDAO(
														    adm_manager
														).findById(lpce_correo);

													if(lpce_correo != null)
													{
														String ls_correo;

														ls_correo = lpce_correo.getCorreoElectronico();

														if(StringUtils.isValidString(ls_correo))
															ls_plantilla = saltoDeCarroDespues(
																    ls_plantilla, ls_tag, ls_correo
																);
													}
												}
											}

											if(StringUtils.isValidString(ls_idDireccion))
											{
												PersonaDireccion lpd_personaDireccion;

												lpd_personaDireccion = new PersonaDireccion();

												lpd_personaDireccion.setIdPersona(ls_idPersona);
												lpd_personaDireccion.setIdDireccion(ls_idDireccion);

												lpd_personaDireccion = DaoCreator.getPersonaDireccionDAO(adm_manager)
														                             .findById(lpd_personaDireccion);

												if(lpd_personaDireccion != null)
												{
													ls_tag = "<TAG_DIR_INTERESADO>";

													if(ls_plantilla.contains(ls_tag))
													{
														String ls_direccion;

														ls_direccion = lpd_personaDireccion.getDireccion();

														if(StringUtils.isValidString(ls_direccion))
															ls_plantilla = saltoDeCarroDespues(
																    ls_plantilla, ls_tag, ls_direccion
																);
													}

													ls_tag = "<TAG_DEPAR_INTERESADO>";

													if(
													    ls_plantilla.contains(ls_tag)
														    && ls_plantilla.contains("<TAG_MUNICIPIO_INTERESADO>")
													)
													{
														Departamento ld_departamento;
														Municipio    lm_municipio;

														ld_departamento     = new Departamento();
														lm_municipio        = new Municipio();

														ld_departamento.setIdPais(lpd_personaDireccion.getIdPais());
														ld_departamento.setIdDepartamento(
														    lpd_personaDireccion.getIdDepartamento()
														);

														ld_departamento = DaoCreator.getDepartamentoDAO(adm_manager)
																                        .findById(ld_departamento);

														lm_municipio.setIdPais(lpd_personaDireccion.getIdPais());
														lm_municipio.setIdDepartamento(
														    lpd_personaDireccion.getIdDepartamento()
														);
														lm_municipio.setIdMunicipio(
														    lpd_personaDireccion.getIdMunicipio()
														);

														lm_municipio = DaoCreator.getMunicipioDAO(adm_manager)
																                     .findById(lm_municipio);

														if((ld_departamento != null) && (lm_municipio != null))
														{
															String ls_nombre;
															ld_departamento.setNombreDepartamento(
															    ld_departamento.getNombre()
															);
															ls_nombre = ld_departamento.getNombreDepartamento();

															if(
															    StringUtils.isValidString(ls_nombre)
																    && StringUtils.isValidString(
																        lm_municipio.getNombre()
																    )
															)
																ls_plantilla = escribirDepartamentoMunicipioInteresado(
																	    ls_plantilla, "<TAG_MUNICIPIO_INTERESADO>",
																	    lm_municipio.getNombre(), ls_tag, ls_nombre
																	);
														}
													}
													else if(ls_plantilla.contains(ls_tag))
													{
														Departamento ld_departamento;

														ld_departamento = new Departamento();

														ld_departamento.setIdPais(lpd_personaDireccion.getIdPais());
														ld_departamento.setIdDepartamento(
														    lpd_personaDireccion.getIdDepartamento()
														);
														ld_departamento = DaoCreator.getDepartamentoDAO(adm_manager)
																                        .findById(ld_departamento);

														if(ld_departamento != null)
														{
															String ls_nombre;

															ls_nombre = ld_departamento.getNombre();

															if(StringUtils.isValidString(ls_nombre))
																ls_plantilla = saltoDeCarroDespues(
																	    ls_plantilla, ls_tag, ls_nombre
																	);
														}
													}

													else if(ls_plantilla.contains("<TAG_MUNICIPIO_INTERESADO>"))
													{
														Municipio lm_municipio;

														lm_municipio = new Municipio();

														lm_municipio.setIdPais(lpd_personaDireccion.getIdPais());
														lm_municipio.setIdDepartamento(
														    lpd_personaDireccion.getIdDepartamento()
														);
														lm_municipio.setIdMunicipio(
														    lpd_personaDireccion.getIdMunicipio()
														);

														lm_municipio = DaoCreator.getMunicipioDAO(adm_manager)
																                     .findById(lm_municipio);

														if(lm_municipio != null)
														{
															String ls_nombre;

															ls_nombre = lm_municipio.getNombre();

															if(StringUtils.isValidString(ls_nombre))
																ls_plantilla = saltoDeCarroDespues(
																	    ls_plantilla, "<TAG_MUNICIPIO_INTERESADO>",
																	    ls_nombre
																	);
														}
													}
												}
											}
										}
									}
								}
							}

							lmss_datos       = finalizarPlantilla(ls_plantilla, ll_idTurnoHistoria, adm_manager);
							ls_plantilla     = lmss_datos.get(IdentificadoresCommon.PLANTILLA);
							lba_return       = new PDFGenerator().convertirRTFToPDF(
								    ls_plantilla.getBytes(), adm_manager
								);

							if(lba_return == null)
								throw new B2BException(ErrorKeys.NO_SE_GENERO_ARCHIVO);

							if(ab_firma)
							{
								byte[] lba_grafo;

								lba_grafo = null;

								int li_incrX = 0;
								int li_incrY = 0;

								if(lc_usuarioFirma != null)
								{
									lba_grafo     = lc_usuarioFirma.getImagenes();
									li_incrX      = NumericUtils.getInt(lc_usuarioFirma.getEntero());
									li_incrY      = NumericUtils.getInt(lc_usuarioFirma.getReal());
								}

								lba_return = getFirmaMasivaBusiness()
										             .reemplazarBookmarsFirma(
										    lba_return, lba_grafo, li_incrX, li_incrY
										);
							}

							if(ab_salvar)
							{
								Imagenes    li_imagenes;
								ImagenesDAO li_DAO;
								long        ll_idImagenTemp;

								li_imagenes     = new Imagenes();
								li_DAO          = DaoCreator.getImagenesDAO(adm_manager);

								li_imagenes.setTipoArchivo(ExtensionCommon.PDF_MAYUS);
								li_imagenes.setIdUsuarioCreacion(as_userId);
								li_imagenes.setIpCreacion(as_remoteIp);
								li_imagenes.setImagenBlob(lba_return);
								li_imagenes.setCodigoVerificacion(
								    lmss_datos.get(IdentificadoresCommon.CODIGO_VERIFICACION)
								);

								ll_idImagenTemp = li_DAO.insertOrUpdate(li_imagenes, true);

								if(ll_idImagenTemp > 0)
								{
									DocumentosSalida    lds_documentoSalida;
									DocumentosSalidaDAO lds_DAO;
									Long                ll_idImagen;

									lds_documentoSalida     = new DocumentosSalida();
									lds_DAO                 = DaoCreator.getDocumentosSalidaDAO(adm_manager);
									ll_idImagen             = NumericUtils.getLongWrapper(ll_idImagenTemp);

									lds_documentoSalida.setIdSolicitud(ls_idSolicitud);
									lds_documentoSalida.setIdTurno(ls_idTurno);
									lds_documentoSalida.setTipo(TipoArchivoCommon.NEGACION_GRABACION);
									lds_documentoSalida.setTipoArchivo(ExtensionCommon.PDF_MAYUS);
									lds_documentoSalida.setEstado(EstadoCommon.ACTIVO);
									lds_documentoSalida.setReferenciaSalida(ls_referenciaSalida);

									lds_documentoSalida = lds_DAO.findDocumentByTurnoTipoEstadoReferenciaSalida(
										    lds_documentoSalida
										);

									if(lds_documentoSalida != null)
									{
										lds_documentoSalida.setIdImagen(ll_idImagen);
										lds_documentoSalida.setIdUsuarioModificacion(as_userId);
										lds_documentoSalida.setIpModificacion(as_remoteIp);

										lds_DAO.updateImagen(lds_documentoSalida);
									}
									else
									{
										lds_documentoSalida = new DocumentosSalida();

										lds_documentoSalida.setIdTurno(ls_idTurno);
										lds_documentoSalida.setIdSolicitud(ls_idSolicitud);
										lds_documentoSalida.setIdImagen(ll_idImagen);
										lds_documentoSalida.setTipo(TipoArchivoCommon.NEGACION_GRABACION);
										lds_documentoSalida.setIdTipoDocumental(TipoDocumentalCommon.COMUNICACION);
										lds_documentoSalida.setRepositorio(
										    ab_firma ? RepositorioCommon.FINAL : RepositorioCommon.TEMPORAL
										);
										lds_documentoSalida.setTipoArchivo(ExtensionCommon.PDF_MAYUS);
										lds_documentoSalida.setEstado(EstadoCommon.ACTIVO);
										lds_documentoSalida.setReferenciaSalida(ls_referenciaSalida);
										lds_documentoSalida.setConsecutivoOficio(ls_consecutivoOficio);
										lds_documentoSalida.setFechaOficio(ab_firma ? new Date() : null);
										lds_documentoSalida.setFechaResolucion(ab_firma ? new Date() : null);
										lds_documentoSalida.setIdTurnoHistoria(
										    NumericUtils.getInteger(ll_idTurnoHistoria)
										);
										lds_documentoSalida.setIdUsuarioCreacion(as_userId);
										lds_documentoSalida.setIpCreacion(as_remoteIp);

										lds_DAO.insertOrUpdate(lds_documentoSalida, true);
									}
								}
								else
									throw new B2BException(ErrorKeys.NO_SE_GENERO_ARCHIVO);
							}
						}
					}
				}
			}
		}

		return lba_return;
	}

	/**
	 *  Método encargado de generar el documento de resolución y salva la información del proceso.
	 *
	 * @param as_parametros Objeto que contiene la información para generar el pdf.
	 * @param as_userId Variable de tipo String que contiene el id del usuario que está realizando el trámite.
	 * @param as_remoteIp Variable de tipo String que contiene la ip del usuario que está realizando el trámite.
	 * @param ab_salvar Variable booleana que indica si se debe salvar el documento y la información.
	 * @param ab_firma Variable booleana que indica si se debe firmar el documento.
	 * @return Variable de tipo byte array que contiene el documento generado.
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see byte[]
	 */
	public byte[] generarDocumentoResolucion(
	    Suspension as_parametros, String as_userId, String as_remoteIp, boolean ab_salvar, boolean ab_firma
	)
	    throws B2BException
	{
		byte[]     lba_return;
		DAOManager ldm_manager;

		lba_return      = null;
		ldm_manager     = DaoManagerFactory.getDAOManager();

		try
		{
			lba_return = generarDocumentoResolucion(
				    as_parametros, as_userId, as_remoteIp, ab_salvar, ab_firma, ldm_manager
				);
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("generarDocumentoResolucion", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lba_return;
	}

	/**
	 * Método encargado de generar el documento de resolución y salva la información del proceso.
	 *
	 * @param as_parametros Objeto que contiene la información para generar el pdf.
	 * @param as_userId Variable de tipo String que contiene el id del usuario que está realizando el trámite.
	 * @param as_remoteIp Variable de tipo String que contiene la ip del usuario que está realizando el trámite.
	 * @param ab_salvar Variable booleana que indica si se debe salvar el documento y la información.
	 * @param ab_firma Variable booleana que indica si se debe firmar el documento.
	 * @param adm_manager DaoManager que administra las transacciones.
	 * @return Variable de tipo byte array que contiene el documento generado.
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see byte[]
	 */
	public byte[] generarDocumentoResolucion(
	    Suspension as_parametros, String as_userId, String as_remoteIp, boolean ab_salvar, boolean ab_firma,
	    DAOManager adm_manager
	)
	    throws B2BException
	{
		byte[] lba_return;

		lba_return = null;

		if(as_parametros != null)
		{
			TurnoHistoria lth_turnoHistoria;

			lth_turnoHistoria     = as_parametros.getTurnoHistoria();

			lth_turnoHistoria = DaoCreator.getTurnoHistoriaDAO(adm_manager).findById(lth_turnoHistoria);

			if(lth_turnoHistoria != null)
			{
				Long   ll_idTurnoHistoria;
				String ls_idTurno;

				ll_idTurnoHistoria     = lth_turnoHistoria.getIdTurnoHistoria();
				ls_idTurno             = lth_turnoHistoria.getIdTurno();

				if(StringUtils.isValidString(ls_idTurno) && NumericUtils.isValidLong(ll_idTurnoHistoria))
				{
					Turno lt_turno;

					lt_turno = DaoCreator.getTurnoDAO(adm_manager).findById(ls_idTurno);

					if(lt_turno != null)
					{
						Constantes lc_constante;
						String     ls_constante;

						byte[] lba_plantilla;

						ls_constante     = ConstanteCommon.PLANTILLA_APROBACION_RESOLUCION_GRABACION;
						lc_constante     = new Constantes();

						lc_constante.setIdConstante(ls_constante);

						lc_constante = DaoCreator.getConstantesDAO(adm_manager).findImagen(lc_constante);

						if(lc_constante == null)
						{
							Object[] loa_messageArgs = new String[1];
							loa_messageArgs[0] = ls_constante;

							throw new B2BException(ErrorKeys.ERROR_SIN_CONSTANTE, loa_messageArgs);
						}

						lba_plantilla = lc_constante.getImagenes();

						if(lba_plantilla == null)
						{
							Object[] loa_messageArgs = new String[1];
							loa_messageArgs[0] = ls_constante;

							throw new B2BException(ErrorKeys.NO_IMAGEN_CONSTANTE, loa_messageArgs);
						}
						else
						{
							Map<String, String> lmss_datos;
							DatosAntSistema     ldas_datosAntSistema;
							String              ls_idCirculo;
							String              ls_idSolicitud;
							String              ls_plantilla;
							String              ls_tag;

							lmss_datos               = null;
							ldas_datosAntSistema     = new DatosAntSistema();
							ls_idCirculo             = lt_turno.getIdCirculo();
							ls_idSolicitud           = lt_turno.getIdSolicitud();
							ls_plantilla             = new String(lba_plantilla);

							{
								ls_tag = "<TAG_ACC_TUR_ID_TURNO>";

								if(ls_plantilla.contains(ls_tag))
									ls_plantilla = ls_plantilla.replace(ls_tag, ls_idTurno);
							}

							if(StringUtils.isValidString(ls_idCirculo))
							{
								CirculoRegistral lcr_circuloRegistral;

								lcr_circuloRegistral = new CirculoRegistral();

								lcr_circuloRegistral.setIdCirculo(ls_idCirculo);

								lcr_circuloRegistral = DaoCreator.getCirculoRegistralDAO(adm_manager)
										                             .findById(lcr_circuloRegistral);

								if(lcr_circuloRegistral != null)
								{
									ls_tag = "<TAG_NOMBRE_ORIP>";

									if(ls_plantilla.contains(ls_tag))
									{
										String ls_nombre;

										ls_nombre = lcr_circuloRegistral.getNombre();

										if(StringUtils.isValidString(ls_nombre))
											ls_plantilla = ls_plantilla.replace(ls_tag, ls_nombre);
									}

									ls_tag = "<TAG_PRINCIPAL_SECCIONAL>";

									if(ls_plantilla.contains(ls_tag))
									{
										String ls_tipoOficina;

										ls_tipoOficina = lcr_circuloRegistral.getTipoOficina();

										if(StringUtils.isValidString(ls_tipoOficina))
										{
											if(ls_tipoOficina.equalsIgnoreCase(EstadoCommon.P))
												ls_tipoOficina = IdentificadoresCommon.PRINCIPAL;
											else if(ls_tipoOficina.equalsIgnoreCase(EstadoCommon.S))
												ls_tipoOficina = IdentificadoresCommon.SECCIONAL;

											ls_plantilla = ls_plantilla.replace(ls_tag, ls_tipoOficina);
										}
									}
								}
							}

							String     ls_tagUsuarioFirma;
							Constantes lc_usuarioFirma;

							ls_tagUsuarioFirma     = null;
							lc_usuarioFirma        = null;

							{
								ls_tagUsuarioFirma = "<TAG_USUARIO_FIRMA_SUSPENSION>";

								if(ls_plantilla.contains(ls_tagUsuarioFirma))
								{
									lc_usuarioFirma = new Constantes();

									lc_usuarioFirma.setIdConstante(ConstanteCommon.USUARIO_FIRMA_SUSPENSION);

									lc_usuarioFirma     = DaoCreator.getConstantesDAO(adm_manager)
											                            .findByIdWithImage(lc_usuarioFirma);

									ls_plantilla = getFirmaMasivaBusiness()
											               .reemplazarUsuarioFirmaCargo(
											    ls_plantilla, lc_usuarioFirma, ls_tagUsuarioFirma,
											    "<TAG_CARGO_FIRMA_SUSPENSION>"
											);
								}
							}

							{
								ls_tag = "<ID_USUARIO_ANALISTA_GRABACION>";

								if(ls_plantilla.contains(ls_tag))
									ls_plantilla = ls_plantilla.replace(ls_tag, as_userId);
							}

							{
								Calendar lc_fecha;

								lc_fecha = Calendar.getInstance();

								{
									int li_dia;

									li_dia = lc_fecha.get(Calendar.DAY_OF_MONTH);

									if(ls_plantilla.contains("<TAG_DIAS_LETRAS>"))
									{
										String ls_dia;

										ls_dia = NumericUtils.convertirLetras(li_dia, false);

										if(StringUtils.isValidString(ls_dia))
											ls_plantilla = ls_plantilla.replace("<TAG_DIAS_LETRAS>", ls_dia);
									}

									if(ls_plantilla.contains("<TAG_DIAS>") && (li_dia > 0))
										ls_plantilla = ls_plantilla.replace(
											    "<TAG_DIAS>", StringUtils.getString(li_dia)
											);
								}

								if(ls_plantilla.contains("<TAG_MES_LETRAS>"))
								{
									int    li_mes;
									String ls_mes;

									li_mes     = lc_fecha.get(Calendar.MONTH);
									ls_mes     = DateUtils.getMes(li_mes + 1);

									if(StringUtils.isValidString(ls_mes))
										ls_plantilla = ls_plantilla.replace("<TAG_MES_LETRAS>", ls_mes);
								}

								{
									int li_anio;

									li_anio = lc_fecha.get(Calendar.YEAR);

									if(ls_plantilla.contains("<TAG_ANIO_LETRAS>"))
									{
										String ls_anio;

										ls_anio = NumericUtils.convertirLetras(li_anio, false);

										if(StringUtils.isValidString(ls_anio))
											ls_plantilla = ls_plantilla.replace("<TAG_ANIO_LETRAS>", ls_anio);
									}

									if(ls_plantilla.contains("<TAG_ANIO>") && (li_anio > 0))
										ls_plantilla = ls_plantilla.replace(
											    "<TAG_ANIO>", StringUtils.getString(li_anio)
											);
								}
							}

							if(
							    ls_plantilla.contains("<TAG_PERTINENCIA>")
								    || ls_plantilla.contains("<TAG_NUMERO_ANOTACIONES_OFICIO>")
							)
							{
								Long   ll_numeroAnotaciones;
								String ls_pertenencia;

								ll_numeroAnotaciones     = as_parametros.getAnotaciones();
								ls_pertenencia           = as_parametros.getConsideracion();

								if(ab_salvar && ab_firma)
								{
									OficiosTexto lof_data;

									lof_data = new OficiosTexto();

									lof_data.setIdTurnoHistoria(
									    NumericUtils.getLongWrapper(lth_turnoHistoria.getIdAnterior())
									);

									lof_data = DaoCreator.getOficiosTextoDAO(adm_manager).findByTurnoHistoria(lof_data);

									if(lof_data != null)
									{
										ls_pertenencia           = lof_data.getPertinencia();
										ll_numeroAnotaciones     = lof_data.getNumeroAnotaciones();
									}
								}

								ls_tag = "<TAG_PERTINENCIA>";

								if(StringUtils.isValidString(ls_pertenencia) && ls_plantilla.contains(ls_tag))
									ls_plantilla = ls_plantilla.replace(ls_tag, StringUtils.getString(ls_pertenencia));

								ls_tag = "<TAG_NUMERO_ANOTACIONES_OFICIO>";

								if(NumericUtils.isValidLong(ll_numeroAnotaciones) && ls_plantilla.contains(ls_tag))
									ls_plantilla = ls_plantilla.replace(
										    ls_tag, StringUtils.getString(ll_numeroAnotaciones)
										);
							}

							if(StringUtils.isValidString(ls_idSolicitud))
							{
								ldas_datosAntSistema = DaoCreator.getDatosAntSistemaDAO(adm_manager)
										                             .findByIdSolicitudOne(ls_idSolicitud);

								if(ldas_datosAntSistema != null)
								{
									ls_tag = "<TAG_MATRICULA_GRABACION>";

									if(ls_plantilla.contains(ls_tag))
									{
										Long   ll_idMatriculaGrabacion;
										String ls_idCirculoGrabacion;

										ll_idMatriculaGrabacion     = ldas_datosAntSistema.getIdMatriculaGrabacion();
										ls_idCirculoGrabacion       = ldas_datosAntSistema.getIdCirculoGrabacion();

										if(
										    NumericUtils.isValidLong(ll_idMatriculaGrabacion)
											    && StringUtils.isValidString(ls_idCirculoGrabacion)
										)
											ls_plantilla = ls_plantilla.replace(
												    ls_tag,
												    ls_idCirculoGrabacion + IdentificadoresCommon.SIMBOLO_GUION
												    + ll_idMatriculaGrabacion
												);
									}
								}
							}

							String ls_referenciaSalida;
							String ls_consecutivoOficio;
							Long   ll_consecutivoResolucion;

							ls_referenciaSalida          = null;
							ls_consecutivoOficio         = null;
							ll_consecutivoResolucion     = null;

							if(ab_firma)
							{
								ls_tag = "<TAG_NUMERO_REFERENCIA>";

								NumeracionResolucionCirculo lnoc_numeracionOficios;
								lnoc_numeracionOficios = findNumeracionResolucionCirculo(
									    lth_turnoHistoria, adm_manager, as_userId, as_remoteIp
									);

								if(lnoc_numeracionOficios != null)
								{
									ls_consecutivoOficio         = lnoc_numeracionOficios.getConsecutivogenerado();
									ll_consecutivoResolucion     = lnoc_numeracionOficios.getConsecutivoResolucion();

									if(ls_plantilla.contains(ls_tag))
										ls_plantilla = ls_plantilla.replace(ls_tag, ls_consecutivoOficio);
								}

								{
									ls_tag     = "<TAG_REFERENCIA_SALIDA>";

									ls_referenciaSalida = generarIdCorrespondencia(
										    lth_turnoHistoria, adm_manager, true
										);

									if(ls_plantilla.contains(ls_tag))
										ls_plantilla = ls_plantilla.replace(ls_tag, ls_referenciaSalida);
								}

								{
									ls_tag = "<TAG_FECHA_RESOL>";

									Date             ld_date;
									SimpleDateFormat formatter;
									String           ls_date;

									ld_date       = new Date();
									formatter     = new SimpleDateFormat("dd/MM/yyyy");
									ls_date       = StringUtils.getStringNotNull(formatter.format(ld_date));

									if(ls_plantilla.contains(ls_tag))
										ls_plantilla = ls_plantilla.replace(ls_tag, ls_date);
								}
							}

							lmss_datos       = finalizarPlantilla(ls_plantilla, ll_idTurnoHistoria, adm_manager);
							ls_plantilla     = lmss_datos.get(IdentificadoresCommon.PLANTILLA);
							lba_return       = new PDFGenerator().convertirRTFToPDF(
								    ls_plantilla.getBytes(), adm_manager
								);

							if(lba_return == null)
								throw new B2BException(ErrorKeys.NO_SE_GENERO_ARCHIVO);

							if(ab_firma)
							{
								byte[] lba_grafo;

								lba_grafo = null;

								int li_incrX = 0;
								int li_incrY = 0;

								if(lc_usuarioFirma != null)
								{
									lba_grafo     = lc_usuarioFirma.getImagenes();
									li_incrX      = NumericUtils.getInt(lc_usuarioFirma.getEntero());
									li_incrY      = NumericUtils.getInt(lc_usuarioFirma.getReal());
								}

								lba_return = getFirmaMasivaBusiness()
										             .reemplazarBookmarsFirma(
										    lba_return, lba_grafo, li_incrX, li_incrY
										);
							}

							if(ab_salvar)
							{
								Imagenes    li_imagenes;
								ImagenesDAO li_DAO;
								long        ll_idImagenTemp;

								li_imagenes     = new Imagenes();
								li_DAO          = DaoCreator.getImagenesDAO(adm_manager);

								li_imagenes.setTipoArchivo(ExtensionCommon.PDF_MAYUS);
								li_imagenes.setIdUsuarioCreacion(as_userId);
								li_imagenes.setIpCreacion(as_remoteIp);
								li_imagenes.setImagenBlob(lba_return);
								li_imagenes.setCodigoVerificacion(
								    lmss_datos.get(IdentificadoresCommon.CODIGO_VERIFICACION)
								);

								ll_idImagenTemp = li_DAO.insertOrUpdate(li_imagenes, true);

								if(ll_idImagenTemp > 0)
								{
									DocumentosSalida    lds_documentoSalida;
									DocumentosSalidaDAO lds_DAO;
									Long                ll_idImagen;

									lds_documentoSalida     = new DocumentosSalida();
									lds_DAO                 = DaoCreator.getDocumentosSalidaDAO(adm_manager);
									ll_idImagen             = NumericUtils.getLongWrapper(ll_idImagenTemp);

									lds_documentoSalida.setIdSolicitud(ls_idSolicitud);
									lds_documentoSalida.setIdTurno(ls_idTurno);
									lds_documentoSalida.setTipo(TipoArchivoCommon.RESOLUCION_GRABACION);
									lds_documentoSalida.setIdTipoDocumental(TipoDocumentalCommon.RESOLUCION);
									lds_documentoSalida.setReferenciaSalida(ls_referenciaSalida);
									lds_documentoSalida.setConsecutivoResolucion(ll_consecutivoResolucion);
									lds_documentoSalida.setFechaResolucion(ab_firma ? new Date() : null);
									lds_documentoSalida.setRepositorio(
									    ab_firma ? RepositorioCommon.FINAL : RepositorioCommon.TEMPORAL
									);
									lds_documentoSalida.setTipoArchivo(ExtensionCommon.PDF_MAYUS);
									lds_documentoSalida.setEstado(EstadoCommon.ACTIVO);
									lds_documentoSalida.setReferenciaSalida(ls_referenciaSalida);

									lds_documentoSalida = lds_DAO.findDocumentByTurnoTipoEstadoReferenciaSalida(
										    lds_documentoSalida
										);

									if(lds_documentoSalida != null)
									{
										lds_documentoSalida.setIdImagen(ll_idImagen);
										lds_documentoSalida.setIdUsuarioModificacion(as_userId);
										lds_documentoSalida.setIpModificacion(as_remoteIp);

										lds_DAO.updateImagen(lds_documentoSalida);
									}
									else
									{
										lds_documentoSalida = new DocumentosSalida();

										lds_documentoSalida.setIdTurno(ls_idTurno);
										lds_documentoSalida.setIdSolicitud(ls_idSolicitud);
										lds_documentoSalida.setIdImagen(ll_idImagen);
										lds_documentoSalida.setTipo(TipoArchivoCommon.RESOLUCION_GRABACION);
										lds_documentoSalida.setIdTipoDocumental(TipoDocumentalCommon.RESOLUCION);
										lds_documentoSalida.setEstado(EstadoCommon.ACTIVO);
										lds_documentoSalida.setReferenciaSalida(ls_referenciaSalida);
										lds_documentoSalida.setRepositorio(
										    ab_firma ? RepositorioCommon.FINAL : RepositorioCommon.TEMPORAL
										);
										lds_documentoSalida.setConsecutivoResolucion(ll_consecutivoResolucion);
										lds_documentoSalida.setFechaResolucion(new Date());
										lds_documentoSalida.setIdTurnoHistoria(
										    NumericUtils.getInteger(ll_idTurnoHistoria)
										);
										lds_documentoSalida.setIdUsuarioCreacion(as_userId);
										lds_documentoSalida.setIpCreacion(as_remoteIp);

										lds_DAO.insertOrUpdate(lds_documentoSalida, true);
									}
								}
								else
									throw new B2BException(ErrorKeys.NO_SE_GENERO_ARCHIVO);
							}
						}
					}
				}
			}
		}

		return lba_return;
	}

	/**
	 * Retorna el valor del objeto de DatosBasicos.
	 *
	 * @param adb_datosBasicos correspondiente al valor del tipo de objeto DatosBasicos
	 * @param as_userId correspondiente al valor del tipo de objeto String
	 * @param as_remoteIp correspondiente al valor del tipo de objeto String
	 * @return devuelve el valor de DatosBasicos
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see DatosBasicos
	 */
	public synchronized DatosBasicos generarPredio(DatosBasicos adb_datosBasicos, String as_userId, String as_remoteIp)
	    throws B2BException
	{
		DAOManager ldm_manager;

		ldm_manager = DaoManagerFactory.getDAOManager();

		try
		{
			if(adb_datosBasicos != null)
			{
				TurnoHistoria lth_turnoHistoria;

				lth_turnoHistoria = DaoCreator.getTurnoHistoriaDAO(ldm_manager)
						                          .findById(adb_datosBasicos.getTurnoHistoria());

				if(lth_turnoHistoria != null)
				{
					DatosAntSistema ldas_datosAntSistema;

					ldas_datosAntSistema = consultarPredioGrabar(lth_turnoHistoria, ldm_manager);

					if(ldas_datosAntSistema != null)
					{
						AccPredioRegistro    lapr_predio;
						AccPredioRegistroDAO lapr_DAO;
						Long                 ll_idMatricula;
						PredioRegistro       lpr_predio;
						String               ls_idCirculo;
						String               ls_idTurno;

						lapr_predio        = new AccPredioRegistro();
						lapr_DAO           = DaoCreator.getAccPredioRegistroDAO(ldm_manager);
						ll_idMatricula     = ldas_datosAntSistema.getIdMatriculaGrabacion();
						lpr_predio         = new PredioRegistro();
						ls_idCirculo       = ldas_datosAntSistema.getIdCirculoGrabacion();
						ls_idTurno         = lth_turnoHistoria.getIdTurno();

						lpr_predio.setIdCirculo(ls_idCirculo);
						lpr_predio.setIdMatricula(NumericUtils.getLong(ll_idMatricula));

						lpr_predio = DaoCreator.getPredioRegistroDAO(ldm_manager).findByCirculoMatricula(lpr_predio);

						if(lpr_predio != null)
							throw new B2BException(ErrorKeys.ERROR_MATRICULA_EXISTE_ESTADO_DEFINITIVO);

						lapr_predio.setIdCirculo(ls_idCirculo);
						lapr_predio.setIdMatricula(ll_idMatricula);
						lapr_predio.setIdTurno(ls_idTurno);

						lapr_predio = lapr_DAO.findByCirculoMatriculaTurno(lapr_predio);

						if(lapr_predio == null)
						{
							int li_secuencia;

							lapr_predio      = new AccPredioRegistro();
							li_secuencia     = lapr_DAO.findSecuencia();

							if(li_secuencia > 0)
							{
								lapr_predio.setIdPredioRegistro(StringUtils.getString(li_secuencia));
								lapr_predio.setIdCirculo(ls_idCirculo);
								lapr_predio.setIdMatricula(ll_idMatricula);
								lapr_predio.setIdTurno(ls_idTurno);
								lapr_predio.setIdTurnoHistoria(lth_turnoHistoria.getIdTurnoHistoria());
								lapr_predio.setPredioDefinitivo(EstadoCommon.PREDIO_DEFINITIVO_TEMPORAL);
								lapr_predio.setTurnoBloqueo(ls_idTurno);
								lapr_predio.setIdUsuarioCreacion(as_userId);
								lapr_predio.setIpCreacion(as_remoteIp);
								lapr_predio.setNombrePredio(ldas_datosAntSistema.getNombrePredio());
								lapr_predio.setIdTipoPredio(ldas_datosAntSistema.getIdTipoPredio());
								lapr_predio.setIdDatosAntSistema(ldas_datosAntSistema.getIdDatosAntSistema());

								lapr_DAO.insert(lapr_predio);
								adb_datosBasicos.setAccPredioRegistro(lapr_predio);
								adb_datosBasicos.setMatriculaGrabada(true);
							}
							else
								throw new B2BException(ErrorKeys.ACC_PREDIO_REGISTRO_INSERT);
						}
					}
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("generarPredio", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return adb_datosBasicos;
	}

	/**
	 * Método encargado de generar la resolución;.
	 *
	 * @param as_parametros Objeto que contiene la información para salvar.
	 * @param as_userId Variable de tipo String que contiene el id del usuario que está realizando el trámite.
	 * @param as_remoteIp Variable de tipo String que contiene la ip del usuario que está realizando el trámite.
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public synchronized void generarResolucion(Suspension as_parametros, String as_userId, String as_remoteIp)
	    throws B2BException
	{
		DAOManager ldm_manager;

		ldm_manager = DaoManagerFactory.getDAOManager();

		try
		{
			if(as_parametros != null)
			{
				TurnoHistoria    lth_turnoHistoria;
				TurnoHistoriaDAO lth_DAO;

				lth_DAO     = DaoCreator.getTurnoHistoriaDAO(ldm_manager);

				lth_turnoHistoria = lth_DAO.findById(as_parametros.getTurnoHistoria());

				if(lth_turnoHistoria != null)
				{
					{
						OficiosTexto lot_oficioTexto;

						lot_oficioTexto = new OficiosTexto();

						lot_oficioTexto.setIdTurnoHistoria(lth_turnoHistoria.getIdTurnoHistoria());
						lot_oficioTexto.setPertinencia(as_parametros.getConsideracion());
						lot_oficioTexto.setNumeroAnotaciones(as_parametros.getAnotaciones());
						lot_oficioTexto.setIdUsuarioCreacion(as_userId);
						lot_oficioTexto.setIpCreacion(as_remoteIp);

						DaoCreator.getOficiosTextoDAO(ldm_manager).insertOrUpdate(lot_oficioTexto, true);
					}

					generarDocumentoResolucion(as_parametros, as_userId, as_remoteIp, true, false, ldm_manager);

					{
						MotivoTramite lmt_motivo;

						lmt_motivo = new MotivoTramite();

						lmt_motivo.setIdEtapaOrigen(EtapaCommon.ID_ETAPA_GRABACION_MATRICULAS);
						lmt_motivo.setIdMotivo(MotivoTramiteCommon.GENERAR_RESOLUCION_DE_GRABACION);

						lmt_motivo = DaoCreator.getMotivoTramiteDAO(ldm_manager).findById(lmt_motivo);

						if(lmt_motivo != null)
						{
							lth_turnoHistoria.setEstadoActividad(EstadoCommon.TERMINADA);
							lth_turnoHistoria.setMotivo(lmt_motivo.getDescripcion());
							lth_turnoHistoria.setIdMotivo(NumericUtils.getLongWrapper(lmt_motivo.getIdMotivo()));
							lth_turnoHistoria.setObservaciones(as_parametros.getObservacionesProcesoAnterior());
							lth_turnoHistoria.setIdUsuarioModificacion(as_userId);
							lth_turnoHistoria.setIpModificacion(as_remoteIp);

							lth_DAO.insertOrUpdate(lth_turnoHistoria, false);

							DaoCreator.getProcedimientosDAO(ldm_manager).spCreateStage(lth_turnoHistoria);
						}
					}
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("negarSolicitud", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}
	}

	/**
	 * Método encargado de negar la solicitud;.
	 *
	 * @param as_parametros Objeto que contiene la información para salvar.
	 * @param as_userId Variable de tipo String que contiene el id del usuario que está realizando el trámite.
	 * @param as_remoteIp Variable de tipo String que contiene la ip del usuario que está realizando el trámite.
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public synchronized void negarSolicitud(Suspension as_parametros, String as_userId, String as_remoteIp)
	    throws B2BException
	{
		DAOManager ldm_manager;

		ldm_manager = DaoManagerFactory.getDAOManager();

		try
		{
			if(as_parametros != null)
			{
				TurnoHistoria    lth_turnoHistoria;
				TurnoHistoriaDAO lth_DAO;

				lth_DAO     = DaoCreator.getTurnoHistoriaDAO(ldm_manager);

				lth_turnoHistoria = lth_DAO.findById(as_parametros.getTurnoHistoria());

				if(lth_turnoHistoria != null)
				{
					{
						OficiosTexto lot_oficioTexto;

						lot_oficioTexto = new OficiosTexto();

						lot_oficioTexto.setIdTurnoHistoria(lth_turnoHistoria.getIdTurnoHistoria());
						lot_oficioTexto.setConsideracion(as_parametros.getConsideracion());
						lot_oficioTexto.setIdUsuarioCreacion(as_userId);
						lot_oficioTexto.setIpCreacion(as_remoteIp);

						DaoCreator.getOficiosTextoDAO(ldm_manager).insertOrUpdate(lot_oficioTexto, true);
					}

					generarDocumentoNegacion(as_parametros, as_userId, as_remoteIp, true, false, ldm_manager);

					{
						long          ll_idEtapa;
						long          ll_idMotivo;
						MotivoTramite lmt_motivo;

						ll_idEtapa      = as_parametros.getEtapa();
						ll_idMotivo     = MotivoTramiteCommon.NEGAR_SOLICITUD_DE_GRABACION;
						lmt_motivo      = new MotivoTramite();

						if(ll_idEtapa == EtapaCommon.ID_ETAPA_EJECUCION_GRABACION_MATRICULAS)
							ll_idMotivo = MotivoTramiteCommon.NEGAR_SOLICITUD_DE_GRABACION_104;

						lmt_motivo.setIdEtapaOrigen(ll_idEtapa);
						lmt_motivo.setIdMotivo(ll_idMotivo);

						lmt_motivo = DaoCreator.getMotivoTramiteDAO(ldm_manager).findById(lmt_motivo);

						if(lmt_motivo != null)
						{
							lth_turnoHistoria.setEstadoActividad(EstadoCommon.TERMINADA);
							lth_turnoHistoria.setMotivo(lmt_motivo.getDescripcion());
							lth_turnoHistoria.setIdMotivo(NumericUtils.getLongWrapper(lmt_motivo.getIdMotivo()));
							lth_turnoHistoria.setObservaciones(as_parametros.getObservacionesProcesoAnterior());
							lth_turnoHistoria.setIdUsuarioModificacion(as_userId);
							lth_turnoHistoria.setIpModificacion(as_remoteIp);

							lth_DAO.insertOrUpdate(lth_turnoHistoria, false);

							DaoCreator.getProcedimientosDAO(ldm_manager).spCreateStage(lth_turnoHistoria);
						}
					}
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("negarSolicitud", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}
	}

	/**
	 * Método encargado de salvar la información de grabación de matrículas.
	 *
	 * @param as_parametros Objeto que contiene la información a guardar.
	 * @param as_userId Variable de tipo String que contiene el id del usuario que está relizando el trámite.
	 * @param as_remoteIp Variable de tipo String que contiene la ip del usuario que está realizando el trámite.
	 * @return verdadero, si se cumple la condicion, de lo contario retorna falso
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public boolean salvarGrabacionMatriculas(Suspension as_parametros, String as_userId, String as_remoteIp)
	    throws B2BException
	{
		boolean    lb_return;
		DAOManager ldm_manager;

		lb_return       = false;
		ldm_manager     = DaoManagerFactory.getDAOManager();

		try
		{
			lb_return = salvarGrabacionMatriculas(as_parametros, as_userId, as_remoteIp, ldm_manager);
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("salvarGrabacionMatriculas", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lb_return;
	}

	/**
	 * Método encargado de salvar la información de grabación de matrículas.
	 *
	 * @param as_parametros Objeto que contiene la información a guardar.
	 * @param as_userId Variable de tipo String que contiene el id del usuario que está relizando el trámite.
	 * @param as_remoteIp Variable de tipo String que contiene la ip del usuario que está realizando el trámite.
	 * @return verdadero, si se cumple la condicion, de lo contario retorna falso
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public boolean salvarGrabacionMatriculas(
	    Suspension as_parametros, String as_userId, String as_remoteIp, DAOManager adm_manager
	)
	    throws B2BException
	{
		boolean lb_return;

		lb_return = false;

		try
		{
			if(as_parametros != null)
			{
				TurnoHistoria    lth_turnoHistoria;
				TurnoHistoriaDAO lth_DAO;

				lth_DAO     = DaoCreator.getTurnoHistoriaDAO(adm_manager);

				lth_turnoHistoria = lth_DAO.findById(as_parametros.getTurnoHistoria());

				if(lth_turnoHistoria != null)
				{
					String ls_idSolicitud;

					ls_idSolicitud = lth_turnoHistoria.getIdSolicitud();

					if(StringUtils.isValidString(ls_idSolicitud))
					{
						Solicitud    ls_solicitud;
						SolicitudDAO ls_DAO;

						ls_DAO     = DaoCreator.getSolicitudDAO(adm_manager);

						ls_solicitud = ls_DAO.findById(ls_idSolicitud);

						if(ls_solicitud != null)
						{
							DatosDelInteresado lddi_data;

							lddi_data = as_parametros.getDatosDelInteresado();

							if(lddi_data != null)
							{
								boolean    lb_nuevaPersona;
								Persona    lp_persona;
								PersonaDAO lp_DAO;

								lb_nuevaPersona     = lddi_data.isEditarDatosBasicos();
								lp_DAO              = DaoCreator.getPersonaDAO(adm_manager);

								if(lb_nuevaPersona)
								{
									String ls_idFlagPersona;

									lp_persona     = lddi_data.getPersona();

									ls_idFlagPersona = marcarFlagPersona(
										    adm_manager, lp_persona, as_userId, as_remoteIp
										);

									if(StringUtils.isValidString(ls_idFlagPersona))
										lp_persona = lp_DAO.findById(ls_idFlagPersona);
								}
								else
									lp_persona = lp_DAO.findById(ls_solicitud.getIdPersona());

								if(lp_persona != null)
								{
									boolean lb_modContacto;
									boolean lb_modDirCorr;
									boolean lb_modDirResi;
									String  ls_idPersona;

									lb_modContacto     = lddi_data.isEditarDatosContacto();
									lb_modDirCorr      = lddi_data.isEditarDireccionCorrespondencia();
									lb_modDirResi      = lddi_data.isEditarDireccionResidencia();
									ls_idPersona       = lp_persona.getIdPersona();

									if(lb_modDirCorr || lb_modDirResi || lb_modContacto || lb_nuevaPersona)
									{
										boolean             lb_correo;
										boolean             lb_isDireccion;
										boolean             lb_isTelefono;
										String              ls_tipoDireccion;
										String              ls_tipoTelefono;
										PersonaDireccionDAO lpd_DAO;

										lb_correo            = lddi_data.isDataCorreo();
										ls_tipoDireccion     = lddi_data.getTipoDireccion();
										ls_tipoTelefono      = lddi_data.getTipoTelefono();
										lpd_DAO              = DaoCreator.getPersonaDireccionDAO(adm_manager);
										lb_isDireccion       = StringUtils.isValidString(ls_tipoDireccion);
										lb_isTelefono        = StringUtils.isValidString(ls_tipoTelefono);

										if(
										    lb_modDirCorr
											    || (lb_nuevaPersona && lb_isDireccion
											    && ls_tipoDireccion.equalsIgnoreCase(EstadoCommon.C))
										)
										{
											PersonaDireccion lpd_personaDireccion;

											lpd_personaDireccion = lddi_data.getDireccionCorrespondencia();

											if(lpd_personaDireccion != null)
											{
												long ll_idDireccion;

												{
													String ls_idTipoPredio;

													ls_idTipoPredio = lpd_personaDireccion.getIdTipoPredio();

													if(!StringUtils.isValidString(ls_idTipoPredio))
														throw new B2BException(ErrorKeys.ERROR_SIN_TIPO_PREDIO);
												}

												lpd_personaDireccion.setIdPersona(ls_idPersona);
												lpd_personaDireccion.setTipoDireccion(EstadoCommon.C);
												lpd_personaDireccion.setIdUsuarioCreacion(as_userId);
												lpd_personaDireccion.setIpCreacion(as_remoteIp);

												ll_idDireccion = lpd_DAO.insertOrUpdate(lpd_personaDireccion, true);

												if(lb_isDireccion && ls_tipoDireccion.equalsIgnoreCase(EstadoCommon.C))
													ls_solicitud.setIdDireccion(StringUtils.getString(ll_idDireccion));
											}
										}

										if(
										    lb_modDirResi
											    || (lb_nuevaPersona && lb_isDireccion
											    && ls_tipoDireccion.equalsIgnoreCase(EstadoCommon.R))
										)
										{
											PersonaDireccion lpd_personaDireccion;

											lpd_personaDireccion = lddi_data.getDireccionResidencia();

											if(lpd_personaDireccion != null)
											{
												long ll_idDireccion;

												{
													String ls_idTipoPredio;

													ls_idTipoPredio = lpd_personaDireccion.getIdTipoPredio();

													if(!StringUtils.isValidString(ls_idTipoPredio))
														throw new B2BException(ErrorKeys.ERROR_SIN_TIPO_PREDIO);
												}

												lpd_personaDireccion.setTipoDireccion(EstadoCommon.R);
												lpd_personaDireccion.setIdPersona(ls_idPersona);
												lpd_personaDireccion.setIdUsuarioCreacion(as_userId);
												lpd_personaDireccion.setIpCreacion(as_remoteIp);

												ll_idDireccion = lpd_DAO.insertOrUpdate(lpd_personaDireccion, true);

												if(lb_isDireccion && ls_tipoDireccion.equalsIgnoreCase(EstadoCommon.R))
													ls_solicitud.setIdDireccion(StringUtils.getString(ll_idDireccion));
											}
										}

										if(lb_modContacto || lb_nuevaPersona)
										{
											PersonaCorreoElectronico lpce_personaCorreo;
											PersonaTelefonoDAO       lpt_DAO;
											PersonaTelefono          lpt_personaFijo;
											PersonaTelefono          lpt_personaMovil;

											lpce_personaCorreo     = lddi_data.getCorreoElectronico();
											lpt_DAO                = DaoCreator.getPersonaTelefonoDAO(adm_manager);
											lpt_personaFijo        = lddi_data.getTelefonoFijo();
											lpt_personaMovil       = lddi_data.getTelefonoMovil();

											if(lpce_personaCorreo != null)
											{
												String ls_correo;

												ls_correo = lpce_personaCorreo.getCorreoElectronico();

												if(
												    StringUtils.isValidString(ls_correo)
													    && ExpresionRegular.getExpresionRegular()
													                           .esCorreoElectronico(ls_correo)
												)
												{
													long                        ll_idCorreo;
													PersonaCorreoElectronicoDAO lpce_DAO;

													lpce_DAO = DaoCreator.getPersonaCorreoElectronicoDAO(adm_manager);

													lpce_personaCorreo.setIdPersona(ls_idPersona);
													lpce_personaCorreo.setIdUsuarioCreacion(as_userId);
													lpce_personaCorreo.setIpCreacion(as_remoteIp);

													ll_idCorreo = lpce_DAO.insertOrUpdate(lpce_personaCorreo, true);

													if(lb_correo)
														ls_solicitud.setIdCorreoElectronico(
														    StringUtils.getString(ll_idCorreo)
														);
												}
											}

											if(lpt_personaFijo != null)
											{
												String ls_telefono;

												ls_telefono = lpt_personaFijo.getTelefono();

												if(StringUtils.isValidString(ls_telefono))
												{
													long ll_idTelefono;

													lpt_personaFijo.setTipoTelefono(EstadoCommon.F);
													lpt_personaFijo.setFechaDesde(new Date());
													lpt_personaFijo.setIdPersona(ls_idPersona);
													lpt_personaFijo.setIdUsuarioCreacion(as_userId);
													lpt_personaFijo.setIpCreacion(as_remoteIp);

													ll_idTelefono = lpt_DAO.insertOrUpdate(lpt_personaFijo, true);

													if(
													    lb_isTelefono
														    && ls_tipoTelefono.equalsIgnoreCase(EstadoCommon.F)
													)
														ls_solicitud.setIdTelefono(
														    StringUtils.getString(ll_idTelefono)
														);
												}
											}

											if(lpt_personaMovil != null)
											{
												String ls_telefono;

												ls_telefono = lpt_personaMovil.getTelefono();

												if(StringUtils.isValidString(ls_telefono))
												{
													long ll_idTelefono;

													lpt_personaMovil.setTipoTelefono(EstadoCommon.M);
													lpt_personaMovil.setFechaDesde(new Date());
													lpt_personaMovil.setIdPersona(ls_idPersona);
													lpt_personaMovil.setIdUsuarioCreacion(as_userId);
													lpt_personaMovil.setIpCreacion(as_remoteIp);

													ll_idTelefono = lpt_DAO.insertOrUpdate(lpt_personaMovil, true);

													if(
													    lb_isTelefono
														    && ls_tipoTelefono.equalsIgnoreCase(EstadoCommon.M)
													)
														ls_solicitud.setIdTelefono(
														    StringUtils.getString(ll_idTelefono)
														);
												}
											}
										}
									}

									ls_solicitud.setIdPersona(ls_idPersona);
									ls_solicitud.setIdUsuarioModificacion(as_userId);
									ls_solicitud.setIpModificacion(as_remoteIp);

									ls_DAO.insertOrUpdate(ls_solicitud, false);

									lb_return = true;
								}
								else
									throw new B2BException(ErrorKeys.PERSONA_INTERESADO_GUARDAR);
							}
							else
								throw new B2BException(ErrorKeys.PERSONA_INTERESADO_GUARDAR);
						}
					}
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("salvarGrabacionMatriculas", lb2be_e);

			throw lb2be_e;
		}

		return lb_return;
	}

	/**
	 * Verifíca que la matrícula asociada al proceso de grabación de matrículas exista en predio registro.
	 *
	 * @param as_idTurnoHistoria id del turno historia del turno en trámite
	 * @return true si la matrícula existe
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public synchronized boolean validarExistenciaMatricula(String as_idTurnoHistoria)
	    throws B2BException
	{
		boolean    lb_matriculaValida;
		DAOManager ldm_manager;

		lb_matriculaValida     = false;
		ldm_manager            = DaoManagerFactory.getDAOManager();

		try
		{
			if(StringUtils.isValidString(as_idTurnoHistoria))
			{
				TurnoHistoria   lth_turnoHist;
				DatosAntSistema ldas_datosAnt;

				lth_turnoHist = new TurnoHistoria();

				lth_turnoHist.setIdTurnoHistoria(NumericUtils.getLongWrapper(as_idTurnoHistoria));

				ldas_datosAnt = consultarPredioGrabar(lth_turnoHist, ldm_manager);

				if(ldas_datosAnt != null)
				{
					String ls_idCirculo;
					Long   ll_idMatricula;

					ls_idCirculo       = ldas_datosAnt.getIdCirculoGrabacion();
					ll_idMatricula     = ldas_datosAnt.getIdMatriculaGrabacion();

					if(StringUtils.isValidString(ls_idCirculo) && NumericUtils.isValidLong(ll_idMatricula))
					{
						PredioRegistro lpr_predio;

						lpr_predio = new PredioRegistro();

						lpr_predio.setIdCirculo(ls_idCirculo);
						lpr_predio.setIdMatricula(NumericUtils.getLong(ll_idMatricula));
						lpr_predio.setValidMatricula(true);

						lpr_predio = DaoCreator.getPredioRegistroDAO(ldm_manager).findById(lpr_predio);

						if(lpr_predio != null)
							lb_matriculaValida = true;
					}
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("validarExistenciaMatricula", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lb_matriculaValida;
	}

	/**
	 * Método encargado de validar si el predio a gabar existe en estado definitivo.
	 *
	 * @param ath_turnoHistoria Objeto que contiene la información para realizar la consulta.
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public synchronized void validarPredioGrabar(TurnoHistoria ath_turnoHistoria)
	    throws B2BException
	{
		DAOManager ldm_manager;

		ldm_manager = DaoManagerFactory.getDAOManager();

		try
		{
			if(ath_turnoHistoria != null)
			{
				ath_turnoHistoria = DaoCreator.getTurnoHistoriaDAO(ldm_manager).findById(ath_turnoHistoria);

				if(ath_turnoHistoria != null)
				{
					String ls_idSolicitud;

					ls_idSolicitud = ath_turnoHistoria.getIdSolicitud();

					if(StringUtils.isValidString(ls_idSolicitud))
					{
						DatosAntSistema ldas_datosAntSistema;

						ldas_datosAntSistema = DaoCreator.getDatosAntSistemaDAO(ldm_manager)
								                             .findByIdSolicitudOne(ls_idSolicitud);

						if(ldas_datosAntSistema != null)
						{
							Long   ll_idMatricula;
							String ls_idCirculo;

							ll_idMatricula     = ldas_datosAntSistema.getIdMatriculaGrabacion();
							ls_idCirculo       = ldas_datosAntSistema.getIdCirculoGrabacion();

							if(NumericUtils.isValidLong(ll_idMatricula) && StringUtils.isValidString(ls_idCirculo))
							{
								PredioRegistro lpr_predioRegistro;

								lpr_predioRegistro = new PredioRegistro();

								lpr_predioRegistro.setIdCirculo(ls_idCirculo);
								lpr_predioRegistro.setIdMatricula(NumericUtils.getLong(ll_idMatricula));

								lpr_predioRegistro = DaoCreator.getPredioRegistroDAO(ldm_manager)
										                           .findByCirculoMatricula(lpr_predioRegistro);

								if(lpr_predioRegistro != null)
								{
									Object[] loa_arg;

									loa_arg     = new String[1];

									loa_arg[0] = ls_idCirculo + IdentificadoresCommon.SIMBOLO_GUION + ll_idMatricula;

									throw new B2BException(ErrorKeys.ERROR_MATRICULA_EXISTE_ESTADO_DEFINITIVO, loa_arg);
								}
							}
						}
					}
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("validarPredioGrabar", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}
	}

	/**
	 * Método que permite extraer funcionalidades de la clase FirmaMasivaBusiness.
	 *
	 * @return el valor de firma masiva business
	 */
	protected FirmaMasivaBusiness getFirmaMasivaBusiness()
	{
		if(ifmb_firmaMasivaBusiness == null)
			ifmb_firmaMasivaBusiness = new FirmaMasivaBusiness();

		return ifmb_firmaMasivaBusiness;
	}

	/**
	 * Método encargado de consultar los datos del predio a grabar.
	 *
	 * @param ath_turnoHistoria Objeto que contiene los datos para realizar la consulta.
	 * @param adm_manager correspondiente al valor del tipo de objeto DAOManager
	 * @return Objeto que contiene los datos del predio a grabar.
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see DatosAntSistema
	 */
	private synchronized DatosAntSistema consultarPredioGrabar(TurnoHistoria ath_turnoHistoria, DAOManager adm_manager)
	    throws B2BException
	{
		DatosAntSistema ldas_return;

		ldas_return = null;

		if(ath_turnoHistoria != null)
		{
			ath_turnoHistoria = DaoCreator.getTurnoHistoriaDAO(adm_manager).findById(ath_turnoHistoria);

			if(ath_turnoHistoria != null)
			{
				String ls_idSolicitud;

				ls_idSolicitud = ath_turnoHistoria.getIdSolicitud();

				if(StringUtils.isValidString(ls_idSolicitud))
					ldas_return = DaoCreator.getDatosAntSistemaDAO(adm_manager).findByIdSolicitudOne(ls_idSolicitud);
			}
		}

		return ldas_return;
	}
}
