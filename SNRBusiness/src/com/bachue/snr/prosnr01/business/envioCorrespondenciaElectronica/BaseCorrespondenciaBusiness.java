package com.bachue.snr.prosnr01.business.envioCorrespondenciaElectronica;

import co.gov.supernotariado.www.schemas.bachue.cn.mensajero.enviarmensaje.v1.TipoDestinatarioEMI;
import co.gov.supernotariado.www.schemas.bachue.cn.mensajero.enviarmensaje.v1.TipoDocumento;
import co.gov.supernotariado.www.schemas.bachue.cn.mensajero.enviarmensaje.v1.TipoEntradaEnviarMensaje;
import co.gov.supernotariado.www.schemas.bachue.cn.mensajero.enviarmensaje.v1.TipoSalidaEnviarMensaje;
import co.gov.supernotariado.www.schemas.bachue.cn.mensajero.enviarmensaje.v1.TipoVariableEMI;

import com.b2bsg.common.dataAccess2.DAOManager;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.logging.LoggerHandler;

import com.b2bsg.common.util.BooleanUtils;
import com.b2bsg.common.util.CollectionUtils;
import com.b2bsg.common.util.DateUtils;
import com.b2bsg.common.util.NumericUtils;
import com.b2bsg.common.util.StringUtils;

import com.bachue.prosnr16.integracion.cliente.cyn.mensajero.ClienteEnviarMensaje;

import com.bachue.snr.prosnr01.business.BaseBusiness;
import com.bachue.snr.prosnr01.business.utilidades.PDFGenerator;

import com.bachue.snr.prosnr01.common.constants.ConstanteCommon;
import com.bachue.snr.prosnr01.common.constants.ErrorKeys;
import com.bachue.snr.prosnr01.common.constants.EstadoCommon;
import com.bachue.snr.prosnr01.common.constants.EtapaCommon;
import com.bachue.snr.prosnr01.common.constants.FormatoFechaCommon;
import com.bachue.snr.prosnr01.common.constants.IdentificadoresCommon;
import com.bachue.snr.prosnr01.common.constants.MedioNotificarCommon;
import com.bachue.snr.prosnr01.common.constants.MotivoTramiteCommon;
import com.bachue.snr.prosnr01.common.constants.ProcesoCommon;
import com.bachue.snr.prosnr01.common.constants.TipoDocumentalCommon;

import com.bachue.snr.prosnr01.dao.DaoCreator;
import com.bachue.snr.prosnr01.dao.acc.DocumentosSalidaDAO;
import com.bachue.snr.prosnr01.dao.acc.SolicitudDAO;
import com.bachue.snr.prosnr01.dao.acc.TurnoHistoriaDAO;
import com.bachue.snr.prosnr01.dao.pgn.ConstantesDAO;

import com.bachue.snr.prosnr01.model.notificaciones.PersonaNotificacion;
import com.bachue.snr.prosnr01.model.sdb.acc.DocumentosSalida;
import com.bachue.snr.prosnr01.model.sdb.acc.Persona;
import com.bachue.snr.prosnr01.model.sdb.acc.PersonaDireccion;
import com.bachue.snr.prosnr01.model.sdb.acc.Solicitud;
import com.bachue.snr.prosnr01.model.sdb.acc.SolicitudAsociada;
import com.bachue.snr.prosnr01.model.sdb.acc.SolicitudMatricula;
import com.bachue.snr.prosnr01.model.sdb.acc.Subproceso;
import com.bachue.snr.prosnr01.model.sdb.acc.Turno;
import com.bachue.snr.prosnr01.model.sdb.acc.TurnoDerivado;
import com.bachue.snr.prosnr01.model.sdb.acc.TurnoHistoria;
import com.bachue.snr.prosnr01.model.sdb.bng.DireccionPredio;
import com.bachue.snr.prosnr01.model.sdb.pgn.CirculoRegistral;
import com.bachue.snr.prosnr01.model.sdb.pgn.Constantes;
import com.bachue.snr.prosnr01.model.sdb.pgn.Departamento;
import com.bachue.snr.prosnr01.model.sdb.pgn.Municipio;
import com.bachue.snr.prosnr01.model.sdb.pgn.OficinaOrigen;
import com.bachue.snr.prosnr01.model.sdb.pgn.Pais;
import com.bachue.snr.prosnr01.model.sdb.pgn.PlantillaNotificacion;

import com.bachue.snr.prosnr16.common.constants.CanalCommon;
import com.bachue.snr.prosnr16.common.constants.ClasificacionCommon;
import com.bachue.snr.prosnr16.common.constants.CodigoVariablePlantillaCommon;
import com.bachue.snr.prosnr16.common.constants.TipoParametroCommon;

import java.math.BigDecimal;
import java.math.BigInteger;

import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;


/**
 * Clase que contiene todos las propiedades BaseCorrespondenciaBusiness.
 *
 * @author  Manuel Blanco
 */
public class BaseCorrespondenciaBusiness extends BaseBusiness
{
	/** Constante clh_LOGGER. */
	private static final LoggerHandler clh_LOGGER = (LoggerHandler)com.bachue.snr.prosnr01.common.utils.LoggingHelper
			.getLogger(BaseCorrespondenciaBusiness.class);

	/**
	 * Enviar documento mensajero.
	 *
	 * @param ath_parametros de ath parametros
	 * @param ldm_manager de ldm manager
	 * @param as_userId de as user id
	 * @param as_remoteIp de as remote ip
	 * @param as_endpoint de as endpoint
	 * @return retorna <code>true</code> si se cumple la condicion, de lo contario retorna <code>false</code>
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	protected synchronized boolean enviarDocumentoMensajero(
	    TurnoHistoria ath_parametros, DAOManager ldm_manager, String as_userId, String as_remoteIp, String as_endpoint
	)
	    throws B2BException
	{
		boolean lb_error;


		lb_error = false;

		try
		{
			if(ath_parametros == null)
				throw new B2BException(ErrorKeys.TURNO_HISTORIA_INVALIDO);

			boolean                      lb_canalElectronico;
			boolean                      lb_canalFisico;
			boolean                      lb_canalSMS;
			boolean                      lb_comunicadoEntrega;
			boolean                      lb_proceso3;
			boolean                      lb_proceso5;
			boolean                      lb_proceso6;
			boolean                      lb_proceso37;
			boolean                      lb_proceso38;
			boolean                      lb_proceso39;
			boolean                      lb_proceso47o48;
			long                         ll_idEtapa;
			Collection<DocumentosSalida> lcds_documentos;
			DocumentosSalidaDAO          ldsd_DAO;
			DocumentosSalida             lds_documento;
			Map<String, TipoVariableEMI> lmstvemi_tags;
			String                       ls_proceso;
			String                       ls_procesoVinculado;
			String                       ls_subProceso;
			String                       ls_idTurno;
			String                       ls_idSolicitud;
			TurnoHistoriaDAO             lthd_DAO;
			TipoEntradaEnviarMensaje     lteem_mensaje;
			Solicitud                    ls_solicitud;
			Solicitud                    ls_solicitudVinculada;
			Turno                        lt_turno;
			String                       ls_idCirculo;
			SolicitudDAO                 lsd_DAO;

			lb_canalElectronico       = false;
			lb_canalFisico            = false;
			lb_canalSMS               = false;
			lb_comunicadoEntrega      = false;
			lb_proceso3               = false;
			lb_proceso5               = false;
			lb_proceso6               = false;
			lb_proceso38              = false;
			lb_proceso37              = false;
			lb_proceso39              = false;
			lb_proceso47o48           = false;
			ls_solicitudVinculada     = null;
			lds_documento             = null;
			ls_procesoVinculado       = null;
			ls_idCirculo              = null;
			lsd_DAO                   = DaoCreator.getSolicitudDAO(ldm_manager);
			ldsd_DAO                  = DaoCreator.getDocumentosSalidaDAO(ldm_manager);
			lthd_DAO                  = DaoCreator.getTurnoHistoriaDAO(ldm_manager);
			lteem_mensaje             = new TipoEntradaEnviarMensaje();
			lcds_documentos           = new LinkedList<DocumentosSalida>();
			lmstvemi_tags             = new HashMap<String, TipoVariableEMI>();
			ll_idEtapa                = NumericUtils.DEFAULT_LONG_VALUE;
			ls_solicitud              = lsd_DAO.findById(ath_parametros.getIdSolicitud());
			lt_turno                  = DaoCreator.getTurnoDAO(ldm_manager).findById(ath_parametros.getIdTurno());

			if(lt_turno == null)
				throw new B2BException(ErrorKeys.TURNO_NO_EXISTE);
			else
			{
				ls_idTurno       = lt_turno.getIdTurno();
				ls_idCirculo     = lt_turno.getIdCirculo();
			}

			if(ls_solicitud == null)
				throw new B2BException(ErrorKeys.ERROR_SIN_INFORMACION_ASOCIADA_AL_TURNO);
			else
				ls_idSolicitud = ls_solicitud.getIdSolicitud();

			{
				Collection<Solicitud> lcs_solicitudesAsociadas;

				lcs_solicitudesAsociadas = obtenerSolicitudesVinculadas(ls_idSolicitud, false, ldm_manager);

				if(CollectionUtils.isValidCollection(lcs_solicitudesAsociadas))
				{
					Iterator<Solicitud> lis_iterator;

					lis_iterator = lcs_solicitudesAsociadas.iterator();

					while(lis_iterator.hasNext() && !lb_proceso47o48)
					{
						Solicitud ls_actual;

						ls_actual = lis_iterator.next();

						if(ls_actual != null)
						{
							String ls_proceso47o48;

							ls_proceso47o48 = ls_actual.getIdProceso();

							if(StringUtils.isValidString(ls_proceso47o48))
								lb_proceso47o48 = (ls_proceso47o48.equalsIgnoreCase(ProcesoCommon.ID_PROCESO_47)
										|| ls_proceso47o48.equalsIgnoreCase(ProcesoCommon.ID_PROCESO_48));

							ls_solicitudVinculada = lb_proceso47o48 ? ls_actual : null;
						}
					}
				}
			}

			if(ls_solicitudVinculada != null)
				ls_procesoVinculado = ls_solicitudVinculada.getIdProceso();

			{
				BigDecimal lbd_idEtapa;

				lbd_idEtapa = ath_parametros.getIdEtapa();

				if(NumericUtils.isValidBigDecimal(lbd_idEtapa))
					ll_idEtapa = lbd_idEtapa.longValue();
				else
					throw new B2BException(ErrorKeys.ERROR_ETAPA_INVALIDA);
			}

			{
				boolean lb_etapa206;
				boolean lb_etapa230;
				boolean lb_conNotaDevolutiva;
				String  ls_medioNotificar;
				String  ls_nir;

				ls_medioNotificar        = StringUtils.getStringNotNull(ls_solicitud.getIdAutorizacionNotificacion());
				ls_proceso               = StringUtils.getStringNotNull(ls_solicitud.getIdProceso());
				ls_subProceso            = StringUtils.getStringNotNull(ls_solicitud.getIdSubproceso());
				lb_proceso3              = ls_proceso.equals(ProcesoCommon.ID_PROCESO_3);
				lb_proceso5              = ls_proceso.equals(ProcesoCommon.ID_PROCESO_5);
				lb_proceso6              = ls_proceso.equals(ProcesoCommon.ID_PROCESO_6);
				lb_proceso37             = ls_proceso.equals(ProcesoCommon.ID_PROCESO_37);
				lb_proceso38             = ls_proceso.equals(ProcesoCommon.ID_PROCESO_38);
				lb_proceso39             = ls_proceso.equals(ProcesoCommon.ID_PROCESO_39);
				lb_etapa206              = ll_idEtapa == EtapaCommon.ID_ETAPA_ENTREGA_CORREO_ELECTRONICO_NOTA_DEVOLUTIVA;
				lb_etapa230              = ll_idEtapa == EtapaCommon.ENVIO_AVISO;
				lb_conNotaDevolutiva     = lb_etapa206 || lb_etapa230;
				ls_nir                   = ls_solicitud.getNir();
				lb_canalSMS              = false;
				lb_comunicadoEntrega     = ((ll_idEtapa == EtapaCommon.ENTREGA_DOCUMENTOS_CORRESPONDENCIA)
						|| (ll_idEtapa == EtapaCommon.ENTREGA_DOCUMENTOS_CORREO_ELECTRONICO));

				if(lb_proceso3 && !lb_comunicadoEntrega)
				{
					if((ll_idEtapa == EtapaCommon.ENTREGA_Y_ENVIO_POR_CORREO_ELECTRONICO) || lb_etapa206)
						lb_canalElectronico = true;
					else if((ll_idEtapa == EtapaCommon.ID_ETAPA_ENTREGA_POR_CORRESPONDENCIA) || lb_etapa230)
						lb_canalFisico = true;
				}
				else
				{
					if(ls_medioNotificar.equals(MedioNotificarCommon.CORREO_ELECTRONICO))
						lb_canalElectronico = true;
					else if(
					    ls_medioNotificar.equals(MedioNotificarCommon.DIRECCION_CORRESPONDENCIA)
						    || ls_medioNotificar.equals(MedioNotificarCommon.DIRECCION_RESIDENCIA)
						    || ls_medioNotificar.equals(MedioNotificarCommon.ORIP)
					)
						lb_canalFisico = true;
				}

				lmstvemi_tags.put(
				    (TipoParametroCommon.ASUNTO + IdentificadoresCommon.SIMBOLO_GUION_ESPACIOS
				    + CodigoVariablePlantillaCommon.TAG_NIR),
				    new TipoVariableEMI(TipoParametroCommon.ASUNTO, CodigoVariablePlantillaCommon.TAG_NIR, ls_nir)
				);

				lmstvemi_tags.put(
				    (CodigoVariablePlantillaCommon.TAG_NIR),
				    new TipoVariableEMI(TipoParametroCommon.CUERPO, CodigoVariablePlantillaCommon.TAG_NIR, ls_nir)
				);

				if(StringUtils.isValidString(ls_proceso))
				{
					String ls_idSubProceso;

					ls_idSubProceso = ls_solicitud.getIdSubproceso();

					if(StringUtils.isValidString(ls_idSubProceso))
					{
						Subproceso ls_subProcesoObj;

						ls_subProcesoObj = DaoCreator.getSubprocesoDAO(ldm_manager).findById(
							    ls_proceso, ls_idSubProceso
							);

						if(ls_subProcesoObj != null)
						{
							String ls_nombre;

							ls_nombre = ls_subProcesoObj.getNombre();

							if(StringUtils.isValidString(ls_nombre))
								lmstvemi_tags.put(
								    (CodigoVariablePlantillaCommon.TAG_SUBPROCESO),
								    new TipoVariableEMI(
								        TipoParametroCommon.CUERPO, CodigoVariablePlantillaCommon.TAG_SUBPROCESO,
								        ls_nombre
								    )
								);
						}
					}
				}

				lteem_mensaje.setNir(ls_nir);

				if(lb_comunicadoEntrega)
					lmstvemi_tags.put(
					    (TipoParametroCommon.ASUNTO + IdentificadoresCommon.SIMBOLO_GUION_ESPACIOS
					    + CodigoVariablePlantillaCommon.TAG_TURNO),
					    new TipoVariableEMI(
					        TipoParametroCommon.ASUNTO, CodigoVariablePlantillaCommon.TAG_TURNO, ls_idTurno
					    )
					);

				if(lb_comunicadoEntrega || lb_etapa206 || lb_etapa230 || (!lb_comunicadoEntrega && lb_proceso3))
				{
					String ls_idDocumentoSalida;

					ls_idDocumentoSalida = ath_parametros.getIdDocumentoSalida();

					if(ls_idDocumentoSalida != null)
						lds_documento = ldsd_DAO.findById(NumericUtils.getLong(ls_idDocumentoSalida));
				}
				else if(lb_proceso6)
				{
					TurnoHistoria lth_th;

					lth_th = lthd_DAO.findMaxByIdEtapaIdTurno(
						    EtapaCommon.GENERACION_CITATORIO_Y_ACTA_NOTIFICACION, ls_idTurno
						);

					if(lth_th != null)
					{
						String ls_idDocumentoSalida;

						ls_idDocumentoSalida = lth_th.getIdDocumentoSalida();

						if(ls_idDocumentoSalida != null)
							lds_documento = ldsd_DAO.findById(NumericUtils.getLong(ls_idDocumentoSalida));
					}
					else
						lds_documento = ldsd_DAO.findLastByIdSolicitudTipo(
							    ls_idSolicitud, TipoDocumentalCommon.ACTA_NOTIFICACION, true
							);
				}

				if(lds_documento != null)
				{
					String     ls_correoElectronico;
					String     ls_correoFisico;
					BigInteger lbi_numeroTelefono;

					ls_correoElectronico     = null;
					ls_correoFisico          = null;
					lbi_numeroTelefono       = null;

//					if((lcds_documentos != null) && (lcds_documentos.size() == NumericUtils.DEFAULT_INT_VALUE))
//						lcds_documentos.add(lds_documento);
					if(
					    (lb_comunicadoEntrega && (ll_idEtapa == EtapaCommon.ENTREGA_DOCUMENTOS_CORRESPONDENCIA))
						    || (!lb_comunicadoEntrega && !lb_canalSMS)
					)
						lteem_mensaje.setIdentificadorEE(lds_documento.getReferenciaSalida());

					if(lb_canalElectronico)
					{
						if(!lb_comunicadoEntrega)
						{
							ls_correoElectronico = lds_documento.getCorreoElectronico();

							if(lb_proceso3)
							{
								String ls_destinatario;

								ls_destinatario = lds_documento.getDestinatario();

								if(ls_destinatario != null)
									lmstvemi_tags.put(
									    CodigoVariablePlantillaCommon.TAG_DATOS_DESTINATARIO,
									    new TipoVariableEMI(
									        TipoParametroCommon.CUERPO,
									        CodigoVariablePlantillaCommon.TAG_DATOS_DESTINATARIO, ls_destinatario
									    )
									);

								{
									Date ld_fechaResolucion;

									ld_fechaResolucion = lds_documento.getFechaResolucion();

									if(ld_fechaResolucion != null)
										lmstvemi_tags.put(
										    CodigoVariablePlantillaCommon.TAG_FECHA_RESOLUCION,
										    new TipoVariableEMI(
										        TipoParametroCommon.CUERPO,
										        CodigoVariablePlantillaCommon.TAG_FECHA_RESOLUCION,
										        StringUtils.getString(
										            ld_fechaResolucion, FormatoFechaCommon.DIA_MES_ANIO
										        )
										    )
										);
								}
							}
						}
						else
						{
							String ls_idCorreo;
							String ls_interesado;

							ls_idCorreo       = ls_solicitud.getIdCorreoElectronico();
							ls_interesado     = ls_solicitud.getIdPersona();

							if(StringUtils.isValidString(ls_idCorreo) && StringUtils.isValidString(ls_interesado))
							{
								String ls_correo;

								ls_correo = obtenerCorreoPersona(ls_interesado, ls_idCorreo, ldm_manager);

								if(ls_correo != null)
									ls_correoElectronico = ls_correo;
								else
									throw new B2BException(ErrorKeys.ERROR_NO_EXISTEN_DATOS_CORREO_ELECTRONICO);

								{
									Persona lp_persona;

									lp_persona = DaoCreator.getPersonaDAO(ldm_manager).findById(ls_interesado);

									if(lp_persona != null)
									{
										String ls_nombre;
										String ls_documento;

										ls_nombre        = lp_persona.getNombreCompleto();
										ls_documento     = lp_persona.getNumeroDocumento();

										if(StringUtils.isValidString(ls_nombre))
										{
											lmstvemi_tags.put(
											    CodigoVariablePlantillaCommon.TAG_INTERVINIENTE,
											    new TipoVariableEMI(
											        TipoParametroCommon.CUERPO,
											        CodigoVariablePlantillaCommon.TAG_INTERVINIENTE, ls_nombre
											    )
											);

											lmstvemi_tags.put(
											    CodigoVariablePlantillaCommon.TAG_TITULAR_DE_DERECHOS,
											    new TipoVariableEMI(
											        TipoParametroCommon.CUERPO,
											        CodigoVariablePlantillaCommon.TAG_TITULAR_DE_DERECHOS, ls_nombre
											    )
											);
										}

										if(StringUtils.isValidString(ls_documento))
											lmstvemi_tags.put(
											    CodigoVariablePlantillaCommon.TAG_NUMERO_DOCUMENTO,
											    new TipoVariableEMI(
											        TipoParametroCommon.CUERPO,
											        CodigoVariablePlantillaCommon.TAG_NUMERO_DOCUMENTO, ls_nombre
											    )
											);
									}
								}

								{
									Calendar lc_fecha;

									lc_fecha = Calendar.getInstance();

									{
										int li_dia;

										li_dia = lc_fecha.get(Calendar.DAY_OF_MONTH);

										{
											String ls_dia;

											ls_dia = NumericUtils.convertirLetras(li_dia, false);

											if(StringUtils.isValidString(ls_dia))
												lmstvemi_tags.put(
												    CodigoVariablePlantillaCommon.TAG_DIAS,
												    new TipoVariableEMI(
												        TipoParametroCommon.CUERPO,
												        CodigoVariablePlantillaCommon.TAG_DIAS, ls_dia
												    )
												);
										}
									}

									{
										int    li_mes;
										String ls_mes;

										li_mes     = lc_fecha.get(Calendar.MONTH);
										ls_mes     = DateUtils.getMes(li_mes + 1);

										if(StringUtils.isValidString(ls_mes))
											lmstvemi_tags.put(
											    CodigoVariablePlantillaCommon.TAG_MES,
											    new TipoVariableEMI(
											        TipoParametroCommon.CUERPO, CodigoVariablePlantillaCommon.TAG_MES,
											        ls_mes
											    )
											);
									}

									{
										Date   ld_fecha;
										String ls_fechaTxt;

										ld_fecha        = new Date();
										ls_fechaTxt     = DateUtils.convertirLetrasLarga(ld_fecha);

										if(StringUtils.isValidString(ls_fechaTxt))
											lmstvemi_tags.put(
											    CodigoVariablePlantillaCommon.TAG_FECHA_LARGA,
											    new TipoVariableEMI(
											        TipoParametroCommon.CUERPO,
											        CodigoVariablePlantillaCommon.TAG_FECHA_LARGA, ls_fechaTxt
											    )
											);
									}
								}

								if(StringUtils.isValidString(ls_idCirculo))
								{
									CirculoRegistral lcr_circuloRegistral;

									lcr_circuloRegistral = DaoCreator.getCirculoRegistralDAO(ldm_manager)
											                             .findById(ls_idCirculo);

									if(lcr_circuloRegistral != null)
									{
										String ls_nombreCirculoTurno;

										ls_nombreCirculoTurno = lcr_circuloRegistral.getNombre();

										if(StringUtils.isValidString(ls_nombreCirculoTurno))
										{
											lmstvemi_tags.put(
											    CodigoVariablePlantillaCommon.TAG_NOMBRE_ORIP,
											    new TipoVariableEMI(
											        TipoParametroCommon.CUERPO,
											        CodigoVariablePlantillaCommon.TAG_NOMBRE_ORIP, ls_nombreCirculoTurno
											    )
											);
											lmstvemi_tags.put(
											    CodigoVariablePlantillaCommon.TAG_NOMBRE_ORIP_TURNO,
											    new TipoVariableEMI(
											        TipoParametroCommon.CUERPO,
											        CodigoVariablePlantillaCommon.TAG_NOMBRE_ORIP_TURNO,
											        ls_nombreCirculoTurno
											    )
											);
										}

										{
											String ls_tipoOficina;

											ls_tipoOficina = lcr_circuloRegistral.getTipoOficina();

											if(StringUtils.isValidString(ls_tipoOficina))
											{
												String ls_descripcionTipo;

												if(ls_tipoOficina.equalsIgnoreCase(EstadoCommon.P))
													ls_descripcionTipo = "PRINCIPAL";
												else if(ls_tipoOficina.equalsIgnoreCase(EstadoCommon.S))
													ls_descripcionTipo = "SECCIONAL";
												else
													ls_descripcionTipo = null;

												if(ls_descripcionTipo != null)
													lmstvemi_tags.put(
													    CodigoVariablePlantillaCommon.TAG_PRINCIPAL_SECCIONAL,
													    new TipoVariableEMI(
													        TipoParametroCommon.CUERPO,
													        CodigoVariablePlantillaCommon.TAG_PRINCIPAL_SECCIONAL,
													        ls_descripcionTipo
													    )
													);
											}
										}

										{
											OficinaOrigen loo_oficina;

											loo_oficina = DaoCreator.getOficinaOrigenDAO(ldm_manager)
													                    .findByIdCirculo(ls_idCirculo);

											if(loo_oficina != null)
											{
												{
													String ls_nombre;

													ls_nombre = loo_oficina.getNombre();

													if(StringUtils.isValidString(ls_nombre))
														lmstvemi_tags.put(
														    CodigoVariablePlantillaCommon.TAG_OFICINA_ORIGEN,
														    new TipoVariableEMI(
														        TipoParametroCommon.CUERPO,
														        CodigoVariablePlantillaCommon.TAG_OFICINA_ORIGEN,
														        ls_nombre
														    )
														);
												}

												{
													String ls_idPais;
													String ls_idDepartamento;
													String ls_idMunicipio;

													ls_idPais             = loo_oficina.getIdPais();
													ls_idDepartamento     = loo_oficina.getIdDepartamento();
													ls_idMunicipio        = loo_oficina.getIdMunicipio();

													if(
													    StringUtils.isValidString(ls_idDepartamento)
														    && StringUtils.isValidString(ls_idPais)
													)
													{
														Departamento ld_departamento;

														ld_departamento = DaoCreator.getDepartamentoDAO(ldm_manager)
																                        .findById(
																    ls_idPais, ls_idDepartamento
																);

														if(
														    (ld_departamento != null)
															    && StringUtils.isValidString(ls_idMunicipio)
														)
														{
															Municipio lm_municipio;

															lm_municipio = DaoCreator.getMunicipioDAO(ldm_manager)
																	                     .findById(
																	    ls_idPais, ls_idDepartamento, ls_idMunicipio
																	);

															if(lm_municipio != null)
															{
																StringBuilder lsb_builder;

																lsb_builder = new StringBuilder(
																	    ld_departamento.getNombre()
																	);

																lsb_builder.append(
																    IdentificadoresCommon.SIMBOLO_GUION_ESPACIOS
																);
																lsb_builder.append(lm_municipio.getNombre());

																lmstvemi_tags.put(
																    CodigoVariablePlantillaCommon.TAG_CIUDAD,
																    new TipoVariableEMI(
																        TipoParametroCommon.CUERPO,
																        CodigoVariablePlantillaCommon.TAG_CIUDAD,
																        lsb_builder.toString()
																    )
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
							else
								throw new B2BException(ErrorKeys.ERROR_NO_EXISTEN_DATOS_CORREO_ELECTRONICO);
						}
					}
					else if(lb_canalFisico)
					{
						if(!lb_comunicadoEntrega)
							ls_correoFisico = obtenerDatosDestinatario(lds_documento, ldm_manager);
						else
						{
							boolean lb_documentoComunicado;
							String  ls_idDireccion;
							String  ls_interesado;

							lb_documentoComunicado     = false;
							ls_idDireccion             = null;
							ls_interesado              = null;

							if(lb_proceso6 || lb_proceso37 || lb_proceso5)
							{
								boolean lb_art19;

								lb_art19 = false;

								if(!lb_proceso37 && !lb_proceso5)
								{
									TurnoHistoria lth_turno190;

									lth_turno190 = lthd_DAO.findMaxByIdEtapaIdTurno(
										    EtapaCommon.ID_ETAPA_APROBACION, ls_idTurno
										);

									if(lth_turno190 != null)
									{
										Long ll_idMotivo190;
										long ll_idMotivo190Primitivo;

										ll_idMotivo190Primitivo     = NumericUtils.DEFAULT_LONG_VALUE;
										ll_idMotivo190              = lth_turno190.getIdMotivo();

										if(NumericUtils.isValidLong(ll_idMotivo190))
											ll_idMotivo190Primitivo = NumericUtils.getLong(ll_idMotivo190);

										lb_art19 = ((ll_idMotivo190Primitivo == MotivoTramiteCommon.SUSPENSION_ARTICULO_19_DIRECCION_RESIDENCIA_)
												|| (ll_idMotivo190Primitivo == MotivoTramiteCommon.SUSPENSION_ARTICULO_19_DIRECCION_CORRESPONDENCIA));
									}
								}

								if(lb_art19)
								{
									SolicitudAsociada lsa_sa;

									lsa_sa = DaoCreator.getSolicitudAsociadaDAO(ldm_manager)
											               .findByIdSolicitud(
											    new SolicitudAsociada(ls_idSolicitud, false)
											);

									if(lsa_sa != null)
									{
										Solicitud ls_solicitud2;

										ls_solicitud2 = DaoCreator.getSolicitudDAO(ldm_manager)
												                      .findById(lsa_sa.getIdSolicitud1());

										if(ls_solicitud2 != null)
										{
											if(lb_art19)
											{
												ls_idDireccion     = ls_solicitud2.getIdDireccion();
												ls_interesado      = ls_solicitud2.getIdPersona();
											}

											lmstvemi_tags.put(
											    (TipoParametroCommon.ASUNTO
											    + IdentificadoresCommon.SIMBOLO_GUION_ESPACIOS
											    + CodigoVariablePlantillaCommon.TAG_NIR),
											    new TipoVariableEMI(
											        TipoParametroCommon.ASUNTO, CodigoVariablePlantillaCommon.TAG_NIR,
											        ls_solicitud2.getNir()
											    )
											);
											lmstvemi_tags.put(
											    (CodigoVariablePlantillaCommon.TAG_NIR),
											    new TipoVariableEMI(
											        TipoParametroCommon.CUERPO, CodigoVariablePlantillaCommon.TAG_NIR,
											        ls_solicitud2.getNir()
											    )
											);
										}
									}
								}
								else
									lb_documentoComunicado = true;
							}
							else
							{
								ls_idDireccion     = ls_solicitud.getIdDireccion();
								ls_interesado      = ls_solicitud.getIdPersona();
							}

							if(StringUtils.isValidString(ls_idDireccion) && StringUtils.isValidString(ls_interesado))
							{
								PersonaDireccion lpd_direccion;

								lpd_direccion = DaoCreator.getPersonaDireccionDAO(ldm_manager)
										                      .findById(ls_interesado, ls_idDireccion);

								if(lpd_direccion != null)
									ls_correoFisico = obtenerDatosDestinatario(
										    new DocumentosSalida(
										        ls_idSolicitud, lpd_direccion.getIdPais(),
										        lpd_direccion.getIdDepartamento(), lpd_direccion.getIdMunicipio(),
										        lpd_direccion.getDireccion()
										    ), ldm_manager
										);
								else
									throw new B2BException(ErrorKeys.ERROR_NO_EXISTEN_DATOS_DIRECCION);
							}
							else if(lb_documentoComunicado)
								ls_correoFisico = obtenerDatosDestinatario(lds_documento, ldm_manager);
							else
								throw new B2BException(ErrorKeys.ERROR_NO_EXISTEN_DATOS_DIRECCION);
						}
					}
					else if(lb_canalSMS)
						lbi_numeroTelefono = BigInteger.valueOf(0);

					// TODO Descomentar lógica
					if(!lb_canalSMS)
					{
//						if(lb_conNotaDevolutiva && (!lb_proceso3 && !lb_comunicadoEntrega))
//						{
//							DocumentosSalida lds_notaDevolutiva;
//
//							lds_notaDevolutiva = ldsd_DAO.findLastByIdSolicitudTipo(
//								    ls_idSolicitud, TipoDocumentalCommon.NOTA_DEVOLUTIVA, true
//								);
//
//							if(lds_notaDevolutiva != null)
//								lcds_documentos.add(lds_notaDevolutiva);
//							else
//								throw new B2BException(ErrorKeys.ERROR_DOCUMENTO_SALIDA_NO_EXISTE);
//						}
//						else if(lb_proceso3 && !lb_comunicadoEntrega)
//						{
//							TurnoHistoria lth_turno175;
//
//							lth_turno175 = lthd_DAO.findMaxByIdEtapaIdTurno(
//								    EtapaCommon.ID_ETAPA_SUSTANCIADOR_ACTUACIONES_ADMINISTRATIVAS, ls_idTurno
//								);
//
//							if(lth_turno175 != null)
//							{
//								DocumentosSalida lds_autoResolucion;
//
//								lds_autoResolucion = ldsd_DAO.findLastAutoResolucion(lth_turno175.getIdTurnoHistoria());
//
//								if(lds_autoResolucion != null)
//									lcds_documentos.add(lds_autoResolucion);
//								else
//									throw new B2BException(ErrorKeys.ERROR_TURNO_SIN_RESOLUCION_AUTO);
//							}
//							else
//								throw new B2BException(ErrorKeys.TURNO_HISTORIA_INVALIDO);
//						}
//						else if(lb_proceso6)
//						{
//							if(lb_comunicadoEntrega)
//							{
//								Collection<TurnoDerivado> lctd_turnosDerivados;
//
//								lctd_turnosDerivados = DaoCreator.getTurnoDerivadoDAO(ldm_manager)
//										                             .findByIdTurnoPadre(new TurnoDerivado(ls_idTurno));
//
//								if(CollectionUtils.isValidCollection(lctd_turnosDerivados))
//								{
//									for(TurnoDerivado ltd_turno : lctd_turnosDerivados)
//									{
//										if((ltd_turno != null) && !ltd_turno.isIndVinculado())
//										{
//											Collection<DocumentosSalida> lcds_docsDerivados;
//
//											lcds_docsDerivados = ldsd_DAO.findByIdTurnoEstadoA(
//												    ltd_turno.getIdTurnoHijo(), true
//												);
//
//											if(CollectionUtils.isValidCollection(lcds_docsDerivados))
//											{
//												for(DocumentosSalida lds_iterador : lcds_docsDerivados)
//												{
//													if(lds_iterador != null)
//														lcds_documentos.add(lds_iterador);
//												}
//											}
//										}
//									}
//								}
//							}
//							else if(
//							    lb_proceso47o48 && (ls_solicitudVinculada != null)
//								    && !(ll_idEtapa == EtapaCommon.ENTREGA_Y_ENVIO_POR_CORREO_ELECTRONICO)
//							)
//							{
//								TurnoHistoria lth_turno410o430;
//
//								lth_turno410o430 = lthd_DAO.findMaxByIdEtapaIdTurno(
//									    ls_procesoVinculado.equalsIgnoreCase(ProcesoCommon.ID_PROCESO_47)
//									    ? EtapaCommon.ID_ETAPA_ANALISIS_DE_RECURSOS_RESOLUCION_DE_RECURSOS
//									    : EtapaCommon.ID_ETAPA_430, ls_idTurno
//									);
//
//								if(lth_turno410o430 == null)
//									lth_turno410o430 = lthd_DAO.findMaxByIdEtapaIdTurno(
//										    EtapaCommon.ID_ETAPA_ASIGNACION_DE_TURNOS_MANUAL_RECHAZO_DE_RECURSOS,
//										    ls_idTurno
//										);
//
//								if(lth_turno410o430 != null)
//								{
//									DocumentosSalida lds_autoResolucion;
//
//									lds_autoResolucion = ldsd_DAO.findLastAutoResolucion(
//										    lth_turno410o430.getIdTurnoHistoria(), true
//										);
//
//									if(lds_autoResolucion != null)
//										lcds_documentos.add(lds_autoResolucion);
//									else
//										throw new B2BException(ErrorKeys.ERROR_TURNO_SIN_RESOLUCION_AUTO);
//								}
//								else
//									throw new B2BException(ErrorKeys.TURNO_HISTORIA_INVALIDO);
//							}
//						}
//						else if(lb_comunicadoEntrega && lb_proceso38)
//						{
//							Collection<DocumentosSalida> lcds_docsActivos;
//
//							lcds_docsActivos = ldsd_DAO.findByIdTurnoEstadoA(ls_idTurno, true);
//
//							if(CollectionUtils.isValidCollection(lcds_docsActivos))
//							{
//								for(DocumentosSalida lds_iterador : lcds_docsActivos)
//								{
//									if(lds_iterador != null)
//										lcds_documentos.add(lds_iterador);
//								}
//							}
//						}
						if(lb_proceso6)
						{
							if(lb_comunicadoEntrega)
							{
								Collection<TurnoDerivado> lctd_turnosDerivados;

								lctd_turnosDerivados = DaoCreator.getTurnoDerivadoDAO(ldm_manager)
										                             .findByIdTurnoPadre(new TurnoDerivado(ls_idTurno));

								if(CollectionUtils.isValidCollection(lctd_turnosDerivados))
								{
									for(TurnoDerivado ltd_turno : lctd_turnosDerivados)
									{
										if((ltd_turno != null) && !ltd_turno.isIndVinculado())
										{
											Collection<DocumentosSalida> lcds_docsDerivados;

											lcds_docsDerivados = ldsd_DAO.findByIdTurnoEstadoA(
												    ltd_turno.getIdTurnoHijo(), true
												);

											if(CollectionUtils.isValidCollection(lcds_docsDerivados))
											{
												for(DocumentosSalida lds_iterador : lcds_docsDerivados)
												{
													if(lds_iterador != null)
														lcds_documentos.add(lds_iterador);
												}
											}
										}
									}
								}
							}
						}

						{
							Collection<DocumentosSalida> lcds_docsActivos;

							lcds_docsActivos = ldsd_DAO.findByIdTurnoExcluyente(ls_idTurno, true);

							if(CollectionUtils.isValidCollection(lcds_docsActivos))
							{
								for(DocumentosSalida lds_iterador : lcds_docsActivos)
								{
									if(lds_iterador != null)
										lcds_documentos.add(lds_iterador);
								}
							}
						}

						if((lcds_documentos != null) && (lcds_documentos.size() > NumericUtils.DEFAULT_INT_VALUE))
						{
							TipoDocumento[] ltda_tipoDocumentos;
							int             li_contador;

							ltda_tipoDocumentos     = new TipoDocumento[lcds_documentos.size()];
							li_contador             = 0;

							for(DocumentosSalida lds_actual : lcds_documentos)
							{
								if(lds_actual != null)
									ltda_tipoDocumentos[li_contador++] = new TipoDocumento(
										    NumericUtils.getBigInteger(lds_actual.getIdEcm()),
										    lds_actual.getIdNombreDocumento()
										);
							}

							lteem_mensaje.setDocumentos(ltda_tipoDocumentos);
						}
					}

					lteem_mensaje.setDestinatario(
					    new TipoDestinatarioEMI(ls_correoElectronico, ls_correoFisico, lbi_numeroTelefono)
					);
				}
				else
				{
					Object[] loa_args = new String[3];

					loa_args[0]     = IdentificadoresCommon.ETAPA_TXT + String.valueOf(ll_idEtapa);
					loa_args[1]     = String.valueOf(ath_parametros.getIdTurnoHistoria());
					loa_args[2]     = ls_idSolicitud;

					throw new B2BException(ErrorKeys.ERROR_DOCUMENTO_SALIDA_REGISTROS, loa_args);
				}
			}

			{
				ConstantesDAO lcd_DAO;
				Constantes    lc_constante;

				lcd_DAO          = DaoCreator.getConstantesDAO(ldm_manager);
				lc_constante     = lcd_DAO.findById(ConstanteCommon.ID_COMPONENTE_BACHUE_DEFAULT);

				if(lc_constante != null)
					lteem_mensaje.setModulo(lc_constante.getCaracter());

				{
					String ls_caracter;

					ls_caracter = lcd_DAO.findString(ConstanteCommon.TAG_URL_PAGINA_PRICIPAL_SEDE_ELECTRONICA);

					if(StringUtils.isValidString(ls_caracter))
						lmstvemi_tags.put(
						    CodigoVariablePlantillaCommon.TAG_URL_PAGINA_PRICIPAL_SEDE_ELECTRONICA,
						    new TipoVariableEMI(
						        TipoParametroCommon.CUERPO,
						        CodigoVariablePlantillaCommon.TAG_URL_PAGINA_PRICIPAL_SEDE_ELECTRONICA, ls_caracter
						    )
						);
					else
					{
						Object[] loa_args;

						loa_args        = new String[1];
						loa_args[0]     = ConstanteCommon.TAG_URL_PAGINA_PRICIPAL_SEDE_ELECTRONICA;

						throw new B2BException(ErrorKeys.ERROR_SIN_CONSTANTE, loa_args);
					}
				}
			}

			lteem_mensaje.setClasificacion(
			    lb_comunicadoEntrega ? ClasificacionCommon.COMUNICACION : ClasificacionCommon.NOTIFICACION
			);
			lteem_mensaje.setCanal(
			    lb_canalElectronico ? CanalCommon.ELECTRONICO : (lb_canalFisico ? CanalCommon.FISICO : CanalCommon.SMS)
			);

			{
				lmstvemi_tags.put(
				    CodigoVariablePlantillaCommon.TAG_TURNO,
				    new TipoVariableEMI(
				        TipoParametroCommon.CUERPO, CodigoVariablePlantillaCommon.TAG_TURNO, ls_idTurno
				    )
				);
				lteem_mensaje.setTurno(ls_idTurno);

				if(lb_canalElectronico)
				{
					String ls_expediente;

					ls_expediente = lt_turno.getExpediente();

					if(StringUtils.isValidString(ls_expediente))
						lmstvemi_tags.put(
						    CodigoVariablePlantillaCommon.TAG_NUMERO_EXPEDIENTE,
						    new TipoVariableEMI(
						        TipoParametroCommon.CUERPO, CodigoVariablePlantillaCommon.TAG_NUMERO_EXPEDIENTE,
						        ls_expediente
						    )
						);
				}

				{
					lteem_mensaje.setOrip(ls_idCirculo);

					if(lb_canalElectronico || lb_canalSMS)
					{
						OficinaOrigen loo_oficinaOrigen;

						loo_oficinaOrigen = DaoCreator.getOficinaOrigenDAO(ldm_manager).findByIdCirculo(ls_idCirculo);

						if(loo_oficinaOrigen == null)
							throw new B2BException(ErrorKeys.NO_OFICINA_ORIGEN);

						lmstvemi_tags.put(
						    CodigoVariablePlantillaCommon.TAG_OFICINA_ORIGEN,
						    new TipoVariableEMI(
						        TipoParametroCommon.CUERPO, CodigoVariablePlantillaCommon.TAG_OFICINA_ORIGEN,
						        loo_oficinaOrigen.getNombre()
						    )
						);
					}
				}
			}

			{
				SolicitudAsociada lsa_asociada;

				lsa_asociada = DaoCreator.getSolicitudAsociadaDAO(ldm_manager)
						                     .findByIdSolicitud(new SolicitudAsociada(ls_idSolicitud, false));

				if(lsa_asociada != null)
				{
					String ls_idSolicitudAsociada;

					ls_idSolicitudAsociada = lsa_asociada.getIdSolicitud1();

					if(StringUtils.isValidString(ls_idSolicitudAsociada))
					{
						Solicitud ls_solicitudAsociada;

						ls_solicitudAsociada = lsd_DAO.findById(ls_idSolicitudAsociada);

						if(ls_solicitudAsociada != null)
						{
							String ls_nirAsociado;

							ls_nirAsociado = ls_solicitudAsociada.getNir();

							if(StringUtils.isValidString(ls_nirAsociado))
								lmstvemi_tags.put(
								    CodigoVariablePlantillaCommon.TAG_NIR_RECEPCION,
								    new TipoVariableEMI(
								        TipoParametroCommon.CUERPO, CodigoVariablePlantillaCommon.TAG_NIR_RECEPCION,
								        ls_nirAsociado
								    )
								);
						}
					}
				}
			}

			{
				String ls_matriculas;

				ls_matriculas = obtenerMatriculasBySolicitudCirculo(
					    lt_turno.getIdSolicitud(), lt_turno.getIdCirculo(), ldm_manager
					);

				if(StringUtils.isValidString(ls_matriculas))
					lmstvemi_tags.put(
					    CodigoVariablePlantillaCommon.TAG_MATRICULAS,
					    new TipoVariableEMI(
					        TipoParametroCommon.CUERPO, CodigoVariablePlantillaCommon.TAG_MATRICULAS, ls_matriculas
					    )
					);
			}

			if(lb_comunicadoEntrega)
			{
				Subproceso lsp_subProceso;

				lsp_subProceso = DaoCreator.getSubprocesoDAO(ldm_manager).findById(ls_proceso, ls_subProceso);

				if(lsp_subProceso != null)
					lmstvemi_tags.put(
					    CodigoVariablePlantillaCommon.TAG_SUB_PROCESO,
					    new TipoVariableEMI(
					        TipoParametroCommon.CUERPO, CodigoVariablePlantillaCommon.TAG_SUB_PROCESO,
					        lsp_subProceso.getNombre()
					    )
					);
			}

			if(lb_canalElectronico || lb_canalSMS)
			{
				if(StringUtils.isValidString(ls_proceso))
				{
					long                  ll_idMotivo;
					long                  ll_idEtapaPlantilla;
					Long                  lL_idMotivo;
					String                ls_idPlantilla;
					PlantillaNotificacion lpn_pn;
					TurnoHistoria         lth_th;

					ll_idMotivo             = -1L;
					ll_idEtapaPlantilla     = -1L;
					lL_idMotivo             = null;
					ls_idPlantilla          = null;
					lpn_pn                  = null;
					lth_th                  = null;

					if(lb_proceso6 && !lb_comunicadoEntrega)
					{
						if(lb_proceso47o48 && (ls_solicitudVinculada != null))
						{
							if(ls_procesoVinculado.equals(ProcesoCommon.ID_PROCESO_47))
								lth_th = lthd_DAO.findMaxByIdEtapaIdTurno(
									    EtapaCommon.ID_ETAPA_REVISION_DE_RECURSOS, ls_idTurno
									);
							else if(ls_procesoVinculado.equals(ProcesoCommon.ID_PROCESO_48))
								lth_th = lthd_DAO.findMaxByIdEtapaIdTurno(EtapaCommon.ID_ETAPA_470, ls_idTurno);
						}
						else
							ls_idPlantilla = "1";
					}
					else if(lb_proceso3 && !lb_comunicadoEntrega)
					{
						lth_th = lthd_DAO.findMaxByIdEtapaIdTurno(
							    EtapaCommon.ID_ETAPA_SUSTANCIADOR_ACTUACIONES_ADMINISTRATIVAS, ls_idTurno
							);

						if(lth_th != null)
						{
							lL_idMotivo = lth_th.getIdMotivo();

							if(lL_idMotivo != null)
								ll_idMotivo = NumericUtils.getLong(lL_idMotivo, -1L);

							if(ll_idMotivo == MotivoTramiteCommon.RESOLUCION_ACLARATORIA_DE_LA_DECISION)
							{
								DocumentosSalida lds_resolucionAclaratoria;
								DocumentosSalida lds_resolucionDesicion;
								TurnoHistoria    lth_175;

								lds_resolucionDesicion        = null;
								lth_175                       = lthd_DAO.findMaxByIdEtapaIdTurnoIdMotivo(
									    EtapaCommon.ID_ETAPA_SUSTANCIADOR_ACTUACIONES_ADMINISTRATIVAS, ls_idTurno,
									    MotivoTramiteCommon.RESOLUCION_DE_LA_DECISION
									);
								lds_resolucionAclaratoria     = ldsd_DAO.findLastAutoResolucion(
									    lth_th.getIdTurnoHistoria()
									);

								if(lth_175 != null)
									lds_resolucionDesicion = ldsd_DAO.findLastAutoResolucion(
										    lth_175.getIdTurnoHistoria()
										);

								if(lds_resolucionAclaratoria != null)
								{
									Date   ld_fechaResolucion;
									Long   ll_consecutivoResolucion;
									String ls_destinatario;

									ls_destinatario              = lds_resolucionAclaratoria.getDestinatario();
									ld_fechaResolucion           = lds_resolucionAclaratoria.getFechaResolucion();
									ll_consecutivoResolucion     = lds_resolucionAclaratoria.getConsecutivoResolucion();

									if(ls_destinatario != null)
										lmstvemi_tags.put(
										    CodigoVariablePlantillaCommon.TAG_DATOS_DESTINATARIO,
										    new TipoVariableEMI(
										        TipoParametroCommon.ASUNTO,
										        CodigoVariablePlantillaCommon.TAG_DATOS_DESTINATARIO, ls_destinatario
										    )
										);

									if(ld_fechaResolucion != null)
										lmstvemi_tags.put(
										    CodigoVariablePlantillaCommon.TAG_FECHA_RESOLUCION,
										    new TipoVariableEMI(
										        TipoParametroCommon.ASUNTO,
										        CodigoVariablePlantillaCommon.TAG_FECHA_RESOLUCION,
										        StringUtils.getString(
										            ld_fechaResolucion, FormatoFechaCommon.DIA_MES_ANIO
										        )
										    )
										);

									if(ll_consecutivoResolucion != null)
										lmstvemi_tags.put(
										    CodigoVariablePlantillaCommon.TAG_NUMERO_RESOLUCION,
										    new TipoVariableEMI(
										        TipoParametroCommon.ASUNTO,
										        CodigoVariablePlantillaCommon.TAG_NUMERO_RESOLUCION,
										        ll_consecutivoResolucion.toString()
										    )
										);
								}

								if(lds_resolucionDesicion != null)
								{
									Date ld_fechaResolucion;
									Long ll_consecutivoResolucion;

									ld_fechaResolucion           = lds_resolucionDesicion.getFechaResolucion();
									ll_consecutivoResolucion     = lds_resolucionDesicion.getConsecutivoResolucion();

									if(ld_fechaResolucion != null)
										lmstvemi_tags.put(
										    CodigoVariablePlantillaCommon.TAG_FECHA_RESOL_ANTERIOR,
										    new TipoVariableEMI(
										        TipoParametroCommon.ASUNTO,
										        CodigoVariablePlantillaCommon.TAG_FECHA_RESOL_ANTERIOR,
										        StringUtils.getString(
										            ld_fechaResolucion, FormatoFechaCommon.DIA_MES_ANIO
										        )
										    )
										);

									if(ll_consecutivoResolucion != null)
										lmstvemi_tags.put(
										    CodigoVariablePlantillaCommon.TAG_NUMERO_RESOL_ANTERIOR,
										    new TipoVariableEMI(
										        TipoParametroCommon.ASUNTO,
										        CodigoVariablePlantillaCommon.TAG_NUMERO_RESOL_ANTERIOR,
										        ll_consecutivoResolucion.toString()
										    )
										);
								}
							}
						}
						else
							throw new B2BException(ErrorKeys.TURNO_HISTORIA_INVALIDO);
					}
					else if(lb_comunicadoEntrega)
					{
						if(ls_proceso.equals(ProcesoCommon.ID_PROCESO_1))
						{
							if(
							    ls_subProceso.equals(ProcesoCommon.ID_SUBPROCESO_1)
								    || ls_subProceso.equals(ProcesoCommon.ID_SUBPROCESO_3)
								    || ls_subProceso.equals(ProcesoCommon.ID_SUBPROCESO_4)
								    || ls_subProceso.equals(ProcesoCommon.ID_SUBPROCESO_15)
								    || ls_subProceso.equals(ProcesoCommon.ID_SUBPROCESO_18)
							)
								lth_th = lthd_DAO.findMaxByIdEtapaIdTurno(EtapaCommon.ID_ETAPA_300, ls_idTurno);
							else if(
							    ls_subProceso.equals(ProcesoCommon.ID_SUBPROCESO_CERTIFICADO_AMPLIACION)
								    || ls_subProceso.equals(ProcesoCommon.ID_SUBPROCESO_CERTIFICADO_PERTENENCIA)
								    || ls_subProceso.equals(ProcesoCommon.ID_SUBPROCESO_CERTIFICADO_SERVIDUMBRE)
								    || ls_subProceso.equals(ProcesoCommon.ID_SUBPROCESO_12)
								    || ls_subProceso.equals(
								        ProcesoCommon.ID_SUBPROCESO_CERTIFICADO_CLARIFICACION_TITULOS
								    ) || ls_subProceso.equals(ProcesoCommon.ID_SUBPROCESO_14)
							)
								lth_th = lthd_DAO.findMaxByIdEtapaIdTurno(EtapaCommon.ID_ETAPA_APROBACION, ls_idTurno);
						}
						else if(ls_proceso.equals(ProcesoCommon.ID_PROCESO_2))
						{
							if(BooleanUtils.getBooleanValue(ls_solicitud.getEntidadExenta()))
								lth_th = lthd_DAO.findMaxByIdEtapaIdTurno(
									    EtapaCommon.ID_ETAPA_41_ANALISTA_SOLICITUD_CONSULTAS_EXENTAS, ls_idTurno
									);
							else
								lth_th = lthd_DAO.findMaxByIdEtapaIdTurno(
									    EtapaCommon.ID_ETAPA_40_GENERACION_CONSULTAS_INMEDIATOS, ls_idTurno
									);
						}
						else if(lb_proceso3)
							lth_th = lthd_DAO.findMaxByIdEtapaIdTurno(
								    EtapaCommon.ID_ETAPA_APROBACION_CORRECCIONES, ls_idTurno
								);
						else if(lb_proceso5 || lb_proceso6 || lb_proceso37 || lb_proceso38 || lb_proceso39)
							lth_th = lthd_DAO.findMaxByIdEtapaIdTurno(EtapaCommon.ID_ETAPA_APROBACION, ls_idTurno);
						else if(ls_proceso.equals(ProcesoCommon.ID_PROCESO_44))
							lth_th = lthd_DAO.findMaxByIdEtapaIdTurno(
								    EtapaCommon.ID_ETAPA_APROBACION_ANTIGUO_SISTEMA, ls_idTurno
								);
					}

					if(lth_th != null)
					{
						BigDecimal lbd_idEtapa;

						lL_idMotivo     = lth_th.getIdMotivo();
						lbd_idEtapa     = lth_th.getIdEtapa();

						if(NumericUtils.isValidBigDecimal(lbd_idEtapa))
							ll_idEtapaPlantilla = lbd_idEtapa.longValue();

						if(lL_idMotivo != null)
							ll_idMotivo = NumericUtils.getLong(lL_idMotivo);

						lpn_pn = consultarPlantillaNotificacion(
							    ls_proceso, ll_idEtapaPlantilla, ll_idMotivo, ldm_manager
							);

						if(lpn_pn != null)
							ls_idPlantilla = lpn_pn.getIdPlantilla();
					}

					if(StringUtils.isValidString(ls_idPlantilla))
						lteem_mensaje.setPlantilla(ls_idPlantilla);
					else
					{
						Object[] loa_args = new String[3];

						loa_args[0]     = ls_proceso;
						loa_args[1]     = String.valueOf(ll_idEtapaPlantilla);
						loa_args[2]     = String.valueOf(ll_idMotivo);

						throw new B2BException(ErrorKeys.ERROR_PLANTILLA_NOTIFICACION_NO_PARAMETRIZADA, loa_args);
					}

					if((lmstvemi_tags != null) && (lmstvemi_tags.size() > NumericUtils.DEFAULT_INT_VALUE))
					{
						TipoVariableEMI[] ltveemi_variables;
						int               li_contador;

						ltveemi_variables     = new TipoVariableEMI[lmstvemi_tags.size()];
						li_contador           = NumericUtils.DEFAULT_INT_VALUE;

						for(Map.Entry<String, TipoVariableEMI> lmstv_actual : lmstvemi_tags.entrySet())
						{
							if(lmstv_actual != null)
								ltveemi_variables[li_contador++] = lmstv_actual.getValue();
						}

						lteem_mensaje.setVariables(ltveemi_variables);
					}
				}
			}

			{
				TipoSalidaEnviarMensaje ltsem_respuesta;

				ltsem_respuesta = ClienteEnviarMensaje.enviarMensaje(lteem_mensaje, as_endpoint);

				if(ltsem_respuesta != null)
				{
					Date ld_fechaRespuesta;

					ld_fechaRespuesta = obtenerDateDesdeCalendar(ltsem_respuesta.getFechaRecepcion());

					if(CollectionUtils.isValidCollection(lcds_documentos))
					{
						for(DocumentosSalida lds_actual : lcds_documentos)
						{
							if(lds_actual != null)
							{
								lds_actual.setFechaRespuestaMensaje(ld_fechaRespuesta);
								lds_actual.setIdMensaje(ltsem_respuesta.getIdentificadorGenerado());
								lds_actual.setIdUsuarioModificacion(as_userId);
								lds_actual.setIpModificacion(as_remoteIp);

								ldsd_DAO.updateIdMensaje(lds_actual);
							}
						}
					}

					ath_parametros.setIdMensaje(ltsem_respuesta.getIdentificadorGenerado());
					ath_parametros.setIdDocumentoSalida(String.valueOf(lds_documento.getIdDocumentoSalida()));
					ath_parametros.setFechaRespuestaMensaje(ld_fechaRespuesta);
					ath_parametros.setIdUsuarioModificacion(as_userId);
					ath_parametros.setIpModificacion(as_remoteIp);

					lthd_DAO.updateIdDocumentoSalida(ath_parametros);
					lthd_DAO.updateFechaRespuestaMensaje(ath_parametros);
				}
				else
					throw new B2BException(ErrorKeys.ERROR_NO_SE_ENCONTRO_RESPUESTA_VALIDA);
			}
		}
		catch(B2BException lb2be_e)
		{
			lb_error = true;
			clh_LOGGER.error("enviarDocumentoMensajero", lb2be_e);
			throw lb2be_e;
		}

		return lb_error;
	}

	/**
	 * Generar documento citatorio resol.
	 *
	 * @param ath_turnoHistoria de ath turno historia
	 * @param ads_documento175 de ads documento 175
	 * @param ads_documento de ads documento
	 * @param as_nombrePlantilla de as nombre plantilla
	 * @param al_numeroResolucion de al numero resolucion
	 * @param ad_fechaResolucion de ad fecha resolucion
	 * @param as_userId de as user id
	 * @param as_remoteIp de as remote ip
	 * @param adm_manager de adm manager
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	protected synchronized void generarDocumentoCitatorioResol(
	    TurnoHistoria ath_turnoHistoria, DocumentosSalida ads_documento175, DocumentosSalida ads_documento,
	    String as_nombrePlantilla, Long al_numeroResolucion, Date ad_fechaResolucion, String as_userId,
	    String as_remoteIp, DAOManager adm_manager
	)
	    throws B2BException
	{
		generarDocumentoCitatorioResol(
		    ath_turnoHistoria, ads_documento175, ads_documento, as_nombrePlantilla, al_numeroResolucion,
		    ad_fechaResolucion, false, as_userId, as_remoteIp, adm_manager
		);
	}

	/**
	 * Generar documento citatorio resol.
	 *
	 * @param ath_turnoHistoria de ath turno historia
	 * @param ads_documento175 de ads documento 175
	 * @param ads_documento de ads documento
	 * @param as_nombrePlantilla de as nombre plantilla
	 * @param al_numeroResolucion de al numero resolucion
	 * @param ad_fechaResolucion de ad fecha resolucion
	 * @param as_userId de as user id
	 * @param as_remoteIp de as remote ip
	 * @param adm_manager de adm manager
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	protected synchronized void generarDocumentoCitatorioResol(
	    TurnoHistoria ath_turnoHistoria, DocumentosSalida ads_documento175, DocumentosSalida ads_documento,
	    String as_nombrePlantilla, Long al_numeroResolucion, Date ad_fechaResolucion, boolean ab_firmaMasiva,
	    String as_userId, String as_remoteIp, DAOManager adm_manager
	)
	    throws B2BException
	{
		if(ath_turnoHistoria == null)
			throw new B2BException(ErrorKeys.TURNO_HISTORIA_INVALIDO);

		if(ads_documento == null)
			throw new B2BException(ErrorKeys.ERROR_DOCUMENTO_SALIDA_NO_EXISTE);

		String ls_plantilla;

		ls_plantilla = obtenerPlantillaDeConstante(adm_manager, as_nombrePlantilla);

		if(!StringUtils.isValidString(ls_plantilla))
			throw new B2BException(ErrorKeys.ERROR_PLANTILLA);

		ls_plantilla     = resolverTagsBasicos(
			    ath_turnoHistoria, ads_documento, ls_plantilla, as_userId, as_remoteIp, adm_manager
			);

		ls_plantilla     = reemplazarTagEnPlantilla(
			    ls_plantilla, "<TAG_NUMERO_RESOLUCION>", StringUtils.getString(al_numeroResolucion)
			);
		ls_plantilla     = reemplazarTagEnPlantilla(ls_plantilla, "<TAG_FECHA_RESOLUCION>", ad_fechaResolucion);

		{
			Turno lt_turno;

			lt_turno = DaoCreator.getTurnoDAO(adm_manager).findById(ath_turnoHistoria.getIdTurno());

			if(lt_turno != null)
			{
				String ls_tag;

				ls_tag = "<TAG_MATRICULAS>";

				if(ls_plantilla.contains(ls_tag))
				{
					Collection<SolicitudMatricula> lcsm_matriculas;

					lcsm_matriculas = DaoCreator.getSolicitudMatriculaDAO(adm_manager)
							                        .findByIdSolicitudCirculo(
							    ath_turnoHistoria.getIdSolicitud(), lt_turno.getIdCirculo()
							);

					if(CollectionUtils.isValidCollection(lcsm_matriculas))
					{
						Iterator<SolicitudMatricula> lism_iterador;
						StringBuilder                lsb_matriculas;

						lism_iterador      = lcsm_matriculas.iterator();
						lsb_matriculas     = new StringBuilder();

						while(lism_iterador.hasNext())
						{
							SolicitudMatricula lsm_matricula;

							lsm_matricula = lism_iterador.next();

							if(lsm_matricula != null)
							{
								lsb_matriculas.append(lsm_matricula.getIdCirculo());
								lsb_matriculas.append(IdentificadoresCommon.SIMBOLO_GUION);
								lsb_matriculas.append(lsm_matricula.getIdMatricula());

								if(lism_iterador.hasNext())
									lsb_matriculas.append(IdentificadoresCommon.SIMBOLO_COMA);
							}
						}

						ls_plantilla = reemplazarTagEnPlantilla(ls_plantilla, ls_tag, lsb_matriculas.toString());
					}
				}

				ls_plantilla = reemplazarTagEnPlantilla(
					    ls_plantilla, "<TAG_NUMERO_EXPEDIENTE>", lt_turno.getExpediente()
					);
			}
		}

		if(ads_documento175 != null)
		{
			String ls_idPais;
			String ls_idDepartamento;

			ls_idPais             = ads_documento175.getIdPais();
			ls_idDepartamento     = ads_documento175.getIdDepartamento();

			{
				Departamento ld_departamento;

				ld_departamento = DaoCreator.getDepartamentoDAO(adm_manager).findById(ls_idPais, ls_idDepartamento);

				if(ld_departamento != null)
					ls_plantilla = reemplazarTagEnPlantilla(
						    ls_plantilla, "<TAG_DEPAR_INTERESADO>", ld_departamento.getNombre()
						);
			}

			{
				Municipio lm_municipio;

				lm_municipio = DaoCreator.getMunicipioDAO(adm_manager)
						                     .findById(ls_idPais, ls_idDepartamento, ads_documento175.getIdMunicipio());

				if(lm_municipio != null)
					ls_plantilla = reemplazarTagEnPlantilla(
						    ls_plantilla, "<TAG_MUNICIPIO_INTERESADO>", lm_municipio.getNombre()
						);
			}

			ls_plantilla     = reemplazarTagEnPlantilla(
				    ls_plantilla, "<TAG_NOMBRE_INTERESADO>", ads_documento175.getDestinatario()
				);
			ls_plantilla     = reemplazarTagEnPlantilla(
				    ls_plantilla, "<TAG_DIR_INTERESADO>", ads_documento175.getDireccion()
				);
			ls_plantilla     = reemplazarTagEnPlantilla(
				    ls_plantilla, "<TAG_CORREO_ELECTRONICO>", ads_documento175.getCorreoElectronico()
				);
			ls_plantilla     = reemplazarTagEnPlantilla(
				    ls_plantilla, "<TAG_PUBLICACION_AVISO_WEB>", ads_documento175.getFechaPublicacionAvisoWeb()
				);
			ls_plantilla     = reemplazarTagEnPlantilla(
				    ls_plantilla, "<TAG_DESFIJACION_AVISO_WEB>", ads_documento175.getFechaDesfijacionAviso()
				);
		}

		{
			Map<String, String> lmss_datos;

			lmss_datos     = finalizarPlantilla(ls_plantilla, null, adm_manager);

			ls_plantilla = lmss_datos.get(IdentificadoresCommon.PLANTILLA);
		}

		{
			byte[] lba_archivoPdf;

			lba_archivoPdf = new PDFGenerator().convertirRTFToPDF(ls_plantilla.getBytes(), adm_manager);

			if(lba_archivoPdf == null)
				throw new B2BException(ErrorKeys.ERROR_GENERANDO_PLANTILLA);

			if(ab_firmaMasiva)
			{
				byte[]     lba_grafo;
				Constantes lc_usuarioFirma;
				int        li_incrX = 0;
				int        li_incrY = 0;

				lba_grafo           = null;
				lc_usuarioFirma     = new Constantes();

				lc_usuarioFirma.setIdConstante(ConstanteCommon.USUARIO_FIRMA_SUSPENSION);

				lc_usuarioFirma = DaoCreator.getConstantesDAO(adm_manager).findByIdWithImage(lc_usuarioFirma);

				if(lc_usuarioFirma != null)
				{
					lba_grafo     = lc_usuarioFirma.getImagenes();
					li_incrX      = NumericUtils.getInt(lc_usuarioFirma.getEntero());
					li_incrY      = NumericUtils.getInt(lc_usuarioFirma.getReal());
				}

				lba_archivoPdf = getFirmaMasivaBusiness()
						                 .reemplazarBookmarsFirma(lba_archivoPdf, lba_grafo, li_incrX, li_incrY);
			}

			{
				long ll_idDocumentoSalida;

				ll_idDocumentoSalida = insertarDocumentoSalida(
					    lba_archivoPdf, ads_documento, as_userId, as_remoteIp, adm_manager
					);

				ath_turnoHistoria.setIdDocumentoSalida(StringUtils.getString(ll_idDocumentoSalida));
				ath_turnoHistoria.setIdUsuarioModificacion(as_userId);
				ath_turnoHistoria.setIpModificacion(as_remoteIp);

				DaoCreator.getTurnoHistoriaDAO(adm_manager).updateIdDocumentoSalida(ath_turnoHistoria);

				ads_documento.setIdDocumentoSalida(ll_idDocumentoSalida);
			}
		}
	}

	/**
	 * Generar documento notificacion.
	 *
	 * @param ath_turnoHistoria de ath turno historia
	 * @param as_userId de as user id
	 * @param ab_repositorioFinal de ab repositorio final
	 * @param as_remoteIp de as remote ip
	 * @param adm_manager de adm manager
	 * @return el valor de documentos salida
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	protected synchronized DocumentosSalida generarDocumentoNotificacion(
	    TurnoHistoria ath_turnoHistoria, String as_userId, boolean ab_repositorioFinal, String as_remoteIp,
	    DAOManager adm_manager
	)
	    throws B2BException
	{
		return generarDocumentoNotificacion(
		    ath_turnoHistoria, as_userId, ab_repositorioFinal, as_remoteIp, adm_manager, false
		);
	}

	/**
	 * Generar documento notificacion.
	 *
	 * @param ath_turnoHistoria de ath turno historia
	 * @param as_userId de as user id
	 * @param ab_repositorioFinal de ab repositorio final
	 * @param as_remoteIp de as remote ip
	 * @param adm_manager de adm manager
	 * @param ab_recursos correspondiente al valor de recursos
	 * @return el valor de documentos salida
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	protected synchronized DocumentosSalida generarDocumentoNotificacion(
	    TurnoHistoria ath_turnoHistoria, String as_userId, boolean ab_repositorioFinal, String as_remoteIp,
	    DAOManager adm_manager, boolean ab_recursos
	)
	    throws B2BException
	{
		DocumentosSalida    lds_documento;
		DocumentosSalidaDAO ldsd_DAO;

		lds_documento     = null;
		ldsd_DAO          = DaoCreator.getDocumentosSalidaDAO(adm_manager);

		if(ath_turnoHistoria != null)
		{
			Solicitud    ls_solicitud;
			SolicitudDAO lsd_DAO;

			lsd_DAO          = DaoCreator.getSolicitudDAO(adm_manager);
			ls_solicitud     = lsd_DAO.findById(ath_turnoHistoria.getIdSolicitud());

			if(ls_solicitud != null)
			{
				boolean          lb_procesoCorrecciones;
				boolean          lb_notificacion;
				boolean          lb_etapa206;
				boolean          lb_etapa230;
				boolean          lb_etapa232;
				TurnoHistoriaDAO lthd_DAO;
				String           ls_idProceso;

				lthd_DAO                   = DaoCreator.getTurnoHistoriaDAO(adm_manager);
				ls_idProceso               = StringUtils.getStringNotNull(ls_solicitud.getIdProceso());
				lb_notificacion            = false;
				lb_procesoCorrecciones     = ls_idProceso.equals(ProcesoCommon.ID_PROCESO_3);

				{
					BigDecimal lbd_idEtapa;

					lbd_idEtapa = ath_turnoHistoria.getIdEtapa();

					if(NumericUtils.isValidBigDecimal(lbd_idEtapa))
					{
						long ll_idEtapa;

						ll_idEtapa     = lbd_idEtapa.longValue();

						lb_etapa206     = ll_idEtapa == EtapaCommon.ID_ETAPA_ENTREGA_CORREO_ELECTRONICO_NOTA_DEVOLUTIVA;
						lb_etapa230     = ll_idEtapa == EtapaCommon.ENVIO_AVISO;
						lb_etapa232     = ll_idEtapa == EtapaCommon.ID_ETAPA_FIJACION_AVISOS_OFICINA_ORIGEN;
					}
					else
						throw new B2BException(ErrorKeys.ERROR_ETAPA_INVALIDA);
				}

				if(lb_procesoCorrecciones && !ab_recursos)
				{
					DocumentosSalida lds_documentoInfoPersona;

					lds_documentoInfoPersona = ldsd_DAO.findById(
						    NumericUtils.getLong(ath_turnoHistoria.getIdDocumentoSalida())
						);

					if(lds_documentoInfoPersona != null)
					{
						DireccionPredio  ldp_direccion;
						DocumentosSalida lds_documento175;
						String           ls_idTurno;

						ldp_direccion        = null;
						lds_documento175     = null;
						ls_idTurno           = ath_turnoHistoria.getIdTurno();

						if(StringUtils.isValidString(ls_idTurno) && lb_etapa206)
						{
							TurnoHistoria lth_max175;

							lth_max175     = lthd_DAO.findMaxByIdEtapaIdTurno(
								    EtapaCommon.ID_ETAPA_SUSTANCIADOR_ACTUACIONES_ADMINISTRATIVAS, ls_idTurno
								);

							lds_documento175 = ldsd_DAO.buscarporIdTurnoHistoriaTipoAutoResolucion(
								    lth_max175.getIdTurnoHistoria()
								);
						}

						{
							PersonaNotificacion lpn_persona;

							lpn_persona = DaoCreator.getPersonaNotificacionDAO(adm_manager)
									                    .findById(lds_documentoInfoPersona.getIdPersonaNotificacion());

							if(lpn_persona != null)
							{
								String ls_idMedioNotificar;

								ls_idMedioNotificar     = StringUtils.getStringNotNull(
									    lpn_persona.getIdAutorizacionNotificacion()
									);

								lb_notificacion = ls_idMedioNotificar.equals(MedioNotificarCommon.CORREO_ELECTRONICO);
							}
						}

						lds_documento = generarDocumentoSalidaBase(
							    ath_turnoHistoria, ls_solicitud, lb_notificacion, ab_repositorioFinal, adm_manager
							);

						agregarTipoDocumentalEnDocumentoSalida(
						    lds_documento, lb_etapa230, lb_etapa232, lb_notificacion, lb_etapa206
						);

						lds_documento.setDestinatario(lds_documentoInfoPersona.getDestinatario());
						lds_documento.setDireccion(lds_documentoInfoPersona.getDireccion());
						lds_documento.setIdPais(lds_documentoInfoPersona.getIdPais());
						lds_documento.setIdDepartamento(lds_documentoInfoPersona.getIdDepartamento());
						lds_documento.setIdMunicipio(lds_documentoInfoPersona.getIdMunicipio());
						lds_documento.setCorreoElectronico(lds_documentoInfoPersona.getCorreoElectronico());
						lds_documento.setIdPersonaNotificacion(lds_documentoInfoPersona.getIdPersonaNotificacion());

						if(lb_etapa206 && (lds_documento175 != null))
						{
							lds_documento.setConsecutivoResolucion(lds_documento175.getConsecutivoResolucion());
							lds_documento.setFechaResolucion(lds_documento175.getFechaResolucion());
						}

						if(!lb_notificacion)
						{
							ldp_direccion = new DireccionPredio();

							ldp_direccion.setDireccion(lds_documento.getDireccion());
							ldp_direccion.setIdPais(lds_documento.getIdPais());
							ldp_direccion.setIdDepartamento(lds_documento.getIdDepartamento());
							ldp_direccion.setIdMunicipio(lds_documento.getIdMunicipio());
						}

						String ls_nombrePlantilla;

						if(lb_etapa230)
							ls_nombrePlantilla = ConstanteCommon.PLANTILLA_ENVIO_AVISO_RESOL;
						else if(lb_etapa206)
							ls_nombrePlantilla = ConstanteCommon.PLANTILLA_ACTA_NOTIFICACION_CORREO;
						else
							ls_nombrePlantilla = ConstanteCommon.PLANTILLA_FIJACION_AVISO;

						if(!lb_etapa206)
							generarDocumentoCitatorioResol(
							    ath_turnoHistoria, lds_documentoInfoPersona, lds_documento, ls_nombrePlantilla,
							    lds_documentoInfoPersona.getConsecutivoResolucion(),
							    lds_documentoInfoPersona.getFechaResolucion(), true, as_userId, as_remoteIp, adm_manager
							);
						else
							generarDocumentoCitatorio(
							    ath_turnoHistoria, ls_solicitud, ldp_direccion, lds_documento, ls_nombrePlantilla,
							    as_userId, as_remoteIp, adm_manager
							);
					}
					else
						throw new B2BException(ErrorKeys.ERROR_TURNO_HISTORIA_SIN_DOCUMENTO);
				}
				else
				{
					boolean               lb_etapaAnterior190;
					boolean               lb_etapaAnterior220;
					boolean               lb_proceso48;
					boolean               lb_proceso47o48;
					boolean               lb_notificacion47o48;
					DireccionPredio       ldp_direccion;
					Collection<Solicitud> lcs_solicitudesAsociadas;
					Solicitud             ls_vinculadoRecuros;
					String                ls_idMedioNotificar;
					TurnoHistoria         lth_etapaAnterior;

					ldp_direccion                = null;
					ls_idMedioNotificar          = null;
					ls_vinculadoRecuros          = null;
					lb_proceso48                 = false;
					lb_proceso47o48              = false;
					lb_notificacion47o48         = false;
					lb_etapaAnterior190          = false;
					lb_etapaAnterior220          = false;
					lth_etapaAnterior            = lthd_DAO.findByIdAnterior(ath_turnoHistoria, true);
					lcs_solicitudesAsociadas     = obtenerSolicitudesVinculadas(
						    ls_solicitud.getIdSolicitud(), false, adm_manager
						);

					if(lth_etapaAnterior != null)
					{
						long ll_idEtapa;

						ll_idEtapa     = NumericUtils.getLong(lth_etapaAnterior.getIdEtapa());

						lb_etapaAnterior190     = (ll_idEtapa == EtapaCommon.ID_ETAPA_APROBACION);
						lb_etapaAnterior220     = (ll_idEtapa == EtapaCommon.ID_ETAPA_ESPERA_POR_ACUSE_CORREO_ELECTRONICO);
					}

					if(CollectionUtils.isValidCollection(lcs_solicitudesAsociadas))
					{
						Iterator<Solicitud> lis_iterator;

						lis_iterator = lcs_solicitudesAsociadas.iterator();

						while(lis_iterator.hasNext() && !lb_proceso47o48)
						{
							Solicitud ls_actual;

							ls_actual = lis_iterator.next();

							if(ls_actual != null)
							{
								String ls_procesoVinculado;

								ls_vinculadoRecuros     = ls_actual;
								ls_procesoVinculado     = ls_actual.getIdProceso();

								if(StringUtils.isValidString(ls_procesoVinculado))
								{
									lb_proceso47o48 = (ls_procesoVinculado.equalsIgnoreCase(
										    ProcesoCommon.ID_PROCESO_47
										) || ls_procesoVinculado.equalsIgnoreCase(ProcesoCommon.ID_PROCESO_48));

									if(lb_proceso47o48)
										lb_proceso48 = ls_procesoVinculado.equalsIgnoreCase(
											    ProcesoCommon.ID_PROCESO_48
											);
								}

								if(lb_proceso47o48)
									lb_notificacion47o48 = StringUtils.getStringNotNull(
										    ls_actual.getIdAutorizacionNotificacion()
										).equalsIgnoreCase(MedioNotificarCommon.CORREO_ELECTRONICO);
							}
						}
					}

					if(
					    (lb_etapaAnterior190 || lb_etapaAnterior220)
						    && (ls_idProceso.equalsIgnoreCase(ProcesoCommon.ID_PROCESO_6)
						    || ls_idProceso.equalsIgnoreCase(ProcesoCommon.ID_PROCESO_3)) && lb_proceso47o48
						    && (ls_vinculadoRecuros != null)
					)
					{
						DocumentosSalida lds_dsRecursos;

						ls_solicitud.setIdPersona(ls_vinculadoRecuros.getIdPersona());
						ls_solicitud.setIdDireccion(ls_vinculadoRecuros.getIdDireccion());
						ls_solicitud.setIdCorreoElectronico(ls_vinculadoRecuros.getIdCorreoElectronico());

						ls_idMedioNotificar = ls_vinculadoRecuros.getIdAutorizacionNotificacion();

						if(!StringUtils.isValidString(ls_idMedioNotificar))
						{
							ls_idMedioNotificar = MedioNotificarCommon.DIRECCION_RESIDENCIA;

							lsd_DAO.updateMedioNotificarSolicitud(
							    ls_idMedioNotificar, ls_vinculadoRecuros.getIdSolicitud(), as_userId, as_remoteIp
							);
						}

						lb_notificacion     = ls_idMedioNotificar.equals(MedioNotificarCommon.CORREO_ELECTRONICO);

						lds_dsRecursos = generarDocumentoSalidaBase(
							    ath_turnoHistoria, ls_solicitud, lb_notificacion, ab_repositorioFinal, adm_manager
							);

						agregarTipoDocumentalEnDocumentoSalida(
						    lds_dsRecursos, lb_etapa230, lb_etapa232, lb_notificacion, lb_etapa206
						);

						if(!lb_notificacion)
						{
							ldp_direccion = new DireccionPredio();

							ldp_direccion.setDireccion(lds_dsRecursos.getDireccion());
							ldp_direccion.setIdPais(lds_dsRecursos.getIdPais());
							ldp_direccion.setIdDepartamento(lds_dsRecursos.getIdDepartamento());
							ldp_direccion.setIdMunicipio(lds_dsRecursos.getIdMunicipio());
						}

						generarDocumentoCitatorio(
						    ath_turnoHistoria, ls_vinculadoRecuros, ldp_direccion, lds_dsRecursos,
						    (lb_notificacion
						    ? ((!lb_etapa206) ? ConstanteCommon.PLANTILLA_ACTA_NOTIFICACION_RECURSO_CORREO
						                      : ConstanteCommon.PLANTILLA_ACTA_NOTIFICACION_PERSONAL_RECURSO)
						    : ConstanteCommon.PLANTILLA_CITATORIO_NOTIFICACION_RECURSO), as_userId, as_remoteIp,
						    adm_manager, null, lb_proceso48
						);
					}
					else
					{
						ls_idMedioNotificar = StringUtils.getStringNotNull(
							    ls_solicitud.getIdAutorizacionNotificacion()
							);

						if(lb_proceso47o48)
							lb_notificacion = lb_notificacion47o48;
						else
							lb_notificacion = ls_idMedioNotificar.equals(MedioNotificarCommon.CORREO_ELECTRONICO);

						lds_documento = generarDocumentoSalidaBase(
							    ath_turnoHistoria, ls_solicitud, lb_notificacion, ab_repositorioFinal, adm_manager
							);

						agregarTipoDocumentalEnDocumentoSalida(
						    lds_documento, lb_etapa230, lb_etapa232, lb_notificacion, lb_etapa206
						);

						if(!lb_notificacion)
						{
							ldp_direccion = new DireccionPredio();

							ldp_direccion.setDireccion(lds_documento.getDireccion());
							ldp_direccion.setIdPais(lds_documento.getIdPais());
							ldp_direccion.setIdDepartamento(lds_documento.getIdDepartamento());
							ldp_direccion.setIdMunicipio(lds_documento.getIdMunicipio());
						}

						{
							String ls_nombrePlantilla;

							if(lb_etapa230)
								ls_nombrePlantilla = ConstanteCommon.PLANTILLA_NOTIFICACION_AVISO;
							else if(lb_etapa232)
								ls_nombrePlantilla = ConstanteCommon.PLANTILLA_FIJACION_AVISO;
							else if(lb_etapa206)
								ls_nombrePlantilla = ConstanteCommon.PLANTILLA_ACTA_NOTIFICACION_CORREO;
							else
								ls_nombrePlantilla = lb_notificacion ? ConstanteCommon.PLANTILLA_ACTA_NOTIFICACION
									                                 : ConstanteCommon.PLANTILLA_CITATORIO_NOTIFICACION;

							generarDocumentoCitatorio(
							    ath_turnoHistoria, ls_solicitud, ldp_direccion, lds_documento, ls_nombrePlantilla,
							    as_userId, as_remoteIp, adm_manager
							);
						}
					}
				}
			}
			else
				throw new B2BException(ErrorKeys.NO_SE_ENCONTRO_SOLICITUD);
		}

		return lds_documento;
	}

	/**
	 * Consultar Plantilla notificacion.
	 *
	 * @param as_idProceso de as id proceso
	 * @param al_idEtapa de al id etapa
	 * @param al_idMotivo de al id motivo
	 * @param adm_manager de adm manager
	 * @return el valor de plantilla notificacion
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	private synchronized PlantillaNotificacion consultarPlantillaNotificacion(
	    String as_idProceso, long al_idEtapa, long al_idMotivo, DAOManager adm_manager
	)
	    throws B2BException
	{
		PlantillaNotificacion lpn_return;

		lpn_return = null;

		try
		{
			if(
			    StringUtils.isValidString(as_idProceso) && (al_idEtapa > NumericUtils.DEFAULT_LONG_VALUE)
				    && (al_idMotivo > NumericUtils.DEFAULT_LONG_VALUE)
			)
				lpn_return = DaoCreator.getPlantillaNotificacionDAO(adm_manager)
						                   .findById(as_idProceso, al_idEtapa, al_idMotivo);
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("ConsultarPLantillaNotificacion", lb2be_e);
			throw lb2be_e;
		}

		return lpn_return;
	}

	/**
	 * Método de consulta con la base de datos para obtener los datos de l detinatario
	 * @param ads_documento con el parametro del documento salida
	 * @param adm_manager  con el manager de la transacción
	 * @return
	 * @throws B2BException
	 */
	private synchronized String obtenerDatosDestinatario(DocumentosSalida ads_documento, DAOManager adm_manager)
	    throws B2BException
	{
		StringBuilder lsb_destinatario;
		lsb_destinatario = null;

		try
		{
			Departamento ld_departamento;
			Municipio    lm_municipio;
			Pais         lp_pais;
			String       ls_idPais;
			String       ls_idDepartamento;
			String       ls_idMunicipio;

			ls_idPais             = ads_documento.getIdPais();
			ls_idDepartamento     = ads_documento.getIdDepartamento();
			ls_idMunicipio        = ads_documento.getIdMunicipio();

			lp_pais             = DaoCreator.getPaisDAO(adm_manager).findById(ls_idPais);
			ld_departamento     = DaoCreator.getDepartamentoDAO(adm_manager).findById(ls_idPais, ls_idDepartamento);
			lm_municipio        = DaoCreator.getMunicipioDAO(adm_manager)
					                            .findById(ls_idPais, ls_idDepartamento, ls_idMunicipio);

			lsb_destinatario = new StringBuilder();

			lsb_destinatario.append(ads_documento.getDireccion());

			if(lp_pais != null)
			{
				lsb_destinatario.append(IdentificadoresCommon.SIMBOLO_COMA_TXT);
				lsb_destinatario.append(lp_pais.getNombre());

				if(ld_departamento != null)
				{
					lsb_destinatario.append(IdentificadoresCommon.SIMBOLO_COMA_TXT);
					lsb_destinatario.append(ld_departamento.getNombre());

					if(lm_municipio != null)
					{
						lsb_destinatario.append(IdentificadoresCommon.SIMBOLO_COMA_TXT);
						lsb_destinatario.append(lm_municipio.getNombre());
					}
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			throw lb2be_e;
		}

		return (lsb_destinatario != null) ? lsb_destinatario.toString() : null;
	}
}
