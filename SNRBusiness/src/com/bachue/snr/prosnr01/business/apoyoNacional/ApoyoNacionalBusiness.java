package com.bachue.snr.prosnr01.business.apoyoNacional;

import com.aspose.words.CellMerge;
import com.aspose.words.ControlChar;
import com.aspose.words.DocumentBuilder;
import com.aspose.words.ParagraphAlignment;
import com.aspose.words.PreferredWidth;
import com.aspose.words.SaveFormat;
import com.aspose.words.Table;
import com.aspose.words.TableAlignment;

import com.b2bsg.common.dataAccess2.DAOManager;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.logging.LoggerHandler;

import com.b2bsg.common.util.CollectionUtils;
import com.b2bsg.common.util.NumericUtils;
import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.business.BaseBusiness;
import com.bachue.snr.prosnr01.business.aprobacion.AprobacionBusiness;
import com.bachue.snr.prosnr01.business.integracion.EnvioGestorDocumentalBusiness;
import com.bachue.snr.prosnr01.business.utilidades.PDFGenerator;

import com.bachue.snr.prosnr01.common.constants.CalidadSolicitanteCommon;
import com.bachue.snr.prosnr01.common.constants.ConstanteCommon;
import com.bachue.snr.prosnr01.common.constants.ErrorKeys;
import com.bachue.snr.prosnr01.common.constants.EstadoCommon;
import com.bachue.snr.prosnr01.common.constants.EtapaCommon;
import com.bachue.snr.prosnr01.common.constants.ExtensionCommon;
import com.bachue.snr.prosnr01.common.constants.IdentificadoresCommon;
import com.bachue.snr.prosnr01.common.constants.MedioNotificarCommon;
import com.bachue.snr.prosnr01.common.constants.MotivoTramiteCommon;
import com.bachue.snr.prosnr01.common.constants.ProcedenciaCommon;
import com.bachue.snr.prosnr01.common.constants.ProcesoCommon;
import com.bachue.snr.prosnr01.common.constants.RepositorioCommon;
import com.bachue.snr.prosnr01.common.constants.RolCommon;
import com.bachue.snr.prosnr01.common.constants.SubProcesoCommon;
import com.bachue.snr.prosnr01.common.constants.TipoArchivoCommon;
import com.bachue.snr.prosnr01.common.constants.TipoDocumentalCommon;
import com.bachue.snr.prosnr01.common.constants.TipoDocumentoPublicoCommon;
import com.bachue.snr.prosnr01.common.constants.TipoOficinaCommon;
import com.bachue.snr.prosnr01.common.constants.TipoRecepcionCommon;

import com.bachue.snr.prosnr01.dao.DaoCreator;
import com.bachue.snr.prosnr01.dao.DaoManagerFactory;
import com.bachue.snr.prosnr01.dao.acc.DocumentosSalidaDAO;
import com.bachue.snr.prosnr01.dao.acc.SolicitudDAO;
import com.bachue.snr.prosnr01.dao.acc.TurnoHistoriaDAO;
import com.bachue.snr.prosnr01.dao.bgn.ImagenesDAO;
import com.bachue.snr.prosnr01.dao.util.ConsultasUtilidades;

import com.bachue.snr.prosnr01.model.numeracion.NumeracionOficiosCirculo;
import com.bachue.snr.prosnr01.model.sdb.acc.DocumentosSalida;
import com.bachue.snr.prosnr01.model.sdb.acc.MensajeComunicacion;
import com.bachue.snr.prosnr01.model.sdb.acc.Persona;
import com.bachue.snr.prosnr01.model.sdb.acc.PersonaCorreoElectronico;
import com.bachue.snr.prosnr01.model.sdb.acc.Solicitud;
import com.bachue.snr.prosnr01.model.sdb.acc.SolicitudApoyoNacional;
import com.bachue.snr.prosnr01.model.sdb.acc.SolicitudAsociada;
import com.bachue.snr.prosnr01.model.sdb.acc.Turno;
import com.bachue.snr.prosnr01.model.sdb.acc.TurnoHistoria;
import com.bachue.snr.prosnr01.model.sdb.aut.Usuario;
import com.bachue.snr.prosnr01.model.sdb.aut.UsuarioRol;
import com.bachue.snr.prosnr01.model.sdb.bng.Documento;
import com.bachue.snr.prosnr01.model.sdb.bng.Imagenes;
import com.bachue.snr.prosnr01.model.sdb.pgn.CirculoRegistral;
import com.bachue.snr.prosnr01.model.sdb.pgn.Constantes;
import com.bachue.snr.prosnr01.model.sdb.pgn.MotivoTramite;
import com.bachue.snr.prosnr01.model.sdb.pgn.Municipio;
import com.bachue.snr.prosnr01.model.sdb.pgn.OficinaOrigen;
import com.bachue.snr.prosnr01.model.ui.SolicitudApoyoNacionalUI;

import com.bachue.snr.prosnr16.common.constants.CanalCommon;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

import java.math.BigDecimal;

import java.util.Collection;
import java.util.Date;


/**
 * Clase que contiene todas las propiedades ApoyoNacionalBusiness.
 *
 * @author  Luis Chacón
 * Fecha de Creacion: 3/09/2020
 */
public class ApoyoNacionalBusiness extends EnvioGestorDocumentalBusiness
{
	/** Constante clh_LOGGER. */
	private static final LoggerHandler clh_LOGGER = (LoggerHandler)com.bachue.snr.prosnr01.common.utils.LoggingHelper
			.getLogger(AprobacionBusiness.class);

	/**
	 * Consultar solicitudes apoyo nacional.
	 *
	 * @param as_idCirculo de as id circulo
	 * @return el valor de collection
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public synchronized Collection<Turno> consultarSolicitudesApoyoNacional(String as_idCirculo)
	    throws B2BException
	{
		DAOManager        ldm_manager;
		Collection<Turno> lc_turnosApoyoNacional;

		ldm_manager                = DaoManagerFactory.getDAOManager();
		lc_turnosApoyoNacional     = null;

		try
		{
			if(StringUtils.isValidString(as_idCirculo))
				lc_turnosApoyoNacional = DaoCreator.getTurnoDAO(ldm_manager).findAllApoyoNacionalByCirculo(
					    as_idCirculo
					);
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("consultarSolicitudesApoyoNacional", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lc_turnosApoyoNacional;
	}

	/**
	 * Enviar a direccion tecnica.
	 *
	 * @param asanui_solicitudApoyoNac de asanui solicitudApoyoNac
	 * @param as_userId de as userId
	 * @param as_remoteIp de as remoteIp
	 * @return el valor de collection
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public synchronized SolicitudApoyoNacionalUI enviarADireccionTecnica(
	    SolicitudApoyoNacionalUI asanui_solicitudApoyoNac, String as_userId, String as_remoteIp
	)
	    throws B2BException
	{
		DAOManager ldm_manager;

		ldm_manager = DaoManagerFactory.getDAOManager();

		try
		{
			if(asanui_solicitudApoyoNac != null)
			{
				TurnoHistoria lth_turnoHistoria;
				lth_turnoHistoria = asanui_solicitudApoyoNac.getTurnoHistoria();

				int li_secuencia;
				li_secuencia = DaoCreator.getUtilDAO(ldm_manager).findSecuence(
					    ConsultasUtilidades.CS_BGN_DOCUMENTO_SEC
					);

				if((li_secuencia > 0) && (lth_turnoHistoria != null))
				{
					Integer li_tmp;
					li_tmp = NumericUtils.getInteger(li_secuencia);

					if(li_tmp != null)
					{
						Documento        ld_documento;
						DocumentosSalida lds_documentoSalida;

						ld_documento            = new Documento();
						lds_documentoSalida     = new DocumentosSalida();

						lds_documentoSalida.setIdTipoDocumental(TipoDocumentalCommon.RESUMEN_DE_LA_SOLICITUD);
						lds_documentoSalida.setEstado(EstadoCommon.ACTIVO);
						lds_documentoSalida.setIdSolicitud(lth_turnoHistoria.getIdSolicitud());

						lds_documentoSalida = DaoCreator.getDocumentosSalidaDAO(ldm_manager)
								                            .findByIdSolicitudTipoDocYEstado(lds_documentoSalida);

						ld_documento.setIdDocumento(li_tmp.toString());
						ld_documento.setVersionDocumento(NumericUtils.getLongWrapper(1L));

						if(lds_documentoSalida != null)
							ld_documento.setNumero(lds_documentoSalida.getConsecutivoOficio());

						ld_documento.setIdTipoDocumento(TipoDocumentoPublicoCommon.OFICIO);
						ld_documento.setFechaDocumento(new Date());
						ld_documento.setIdTipoOficina(TipoOficinaCommon.OFICINA_REGISTRO);

						{
							CirculoRegistral lcr_circuloRegistral;

							lcr_circuloRegistral = DaoCreator.getCirculoRegistralDAO(ldm_manager)
									                             .findById(asanui_solicitudApoyoNac.getIdCirculo());

							if(lcr_circuloRegistral != null)
							{
								OficinaOrigen loo_oficinaOrigen;

								loo_oficinaOrigen = new OficinaOrigen();
								loo_oficinaOrigen.setIdOficinaOrigen(lcr_circuloRegistral.getIdOficinaOrigen());
								loo_oficinaOrigen.setVersion(lcr_circuloRegistral.getVersion());

								loo_oficinaOrigen = DaoCreator.getOficinaOrigenDAO(ldm_manager)
										                          .findById(loo_oficinaOrigen);

								if(loo_oficinaOrigen != null)
								{
									ld_documento.setIdPais(loo_oficinaOrigen.getIdPais());
									ld_documento.setIdMunicipio(loo_oficinaOrigen.getIdMunicipio());
									ld_documento.setIdDepartamento(loo_oficinaOrigen.getIdDepartamento());

									ld_documento.setIdOficinaOrigen(loo_oficinaOrigen.getIdOficinaOrigen());
									ld_documento.setVersion(loo_oficinaOrigen.getVersion());
								}
							}
						}

						ld_documento.setIdUsuarioCreacion(as_userId);
						ld_documento.setIpCreacion(as_remoteIp);

						DaoCreator.getDocumentoDAO(ldm_manager).insertOrUpdate(ld_documento, true);

						Solicitud ls_solicitud;
						ls_solicitud = DaoCreator.getSolicitudDAO(ldm_manager)
								                     .findById(lth_turnoHistoria.getIdSolicitud());

						if(ls_solicitud != null)
						{
							ls_solicitud.setIdDocumento(ld_documento.getIdDocumento());
							ls_solicitud.setVersionDocumento(ld_documento.getVersionDocumento());
							ls_solicitud.setVersionDocumentoInicial(ld_documento.getVersionDocumento());

							DaoCreator.getSolicitudDAO(ldm_manager).update(ls_solicitud);
						}
					}

					TurnoHistoria lth_tmp;
					lth_tmp = DaoCreator.getProcedimientosDAO(ldm_manager).spCreateStage(lth_turnoHistoria);

					MensajeComunicacion lmc_mensajeComunicacion;
					lmc_mensajeComunicacion = new MensajeComunicacion();

					lmc_mensajeComunicacion.setIdTurnoHistoria(
					    (lth_tmp != null) ? StringUtils.getString(lth_tmp.getIdTurnoHistoria()) : null
					);
					lmc_mensajeComunicacion.setIdOrigen("1");
					lmc_mensajeComunicacion.setIdCanal(CanalCommon.ELECTRONICO);
					lmc_mensajeComunicacion.setIdSolicitud(ProcesoCommon.ID_PROCESO_49);
					lmc_mensajeComunicacion.setIdPlantilla("202");

					UsuarioRol lur_usuarioRol;
					lur_usuarioRol = DaoCreator.getUsuarioRolDAO(ldm_manager)
							                       .findByIdRol(RolCommon.CB_ROL_DIRECTOR_TECNICO_REGISTRO);

					if(lur_usuarioRol != null)
					{
						Usuario lu_usuario;
						lu_usuario = new Usuario();

						lu_usuario.setIdUsuario(lur_usuarioRol.getIdUsuario());
						lu_usuario = DaoCreator.getUsuarioDAO(ldm_manager).findById(lu_usuario);

						if(lu_usuario != null)
							lmc_mensajeComunicacion.setCorreoElectronico(lu_usuario.getCorreoElectronicoCorporativo());
					}

					lmc_mensajeComunicacion.setIdUsuarioCreacion(as_userId);
					lmc_mensajeComunicacion.setIpCreacion(as_remoteIp);

					DaoCreator.getMensajeComunicacionDAO(ldm_manager).insertOrUpdate(lmc_mensajeComunicacion, true);

					Collection<Turno> lct_turnosApoyoNacional;

					lct_turnosApoyoNacional = asanui_solicitudApoyoNac.getTurnos();

					if(
					    CollectionUtils.isValidCollection(lct_turnosApoyoNacional)
						    && (lct_turnosApoyoNacional.size() >= 11)
					)
					{
						for(Turno lt_turno : lct_turnosApoyoNacional)
						{
							if(lt_turno != null)
							{
								SolicitudApoyoNacional lsan_solicitudApoyoNac;

								lsan_solicitudApoyoNac = new SolicitudApoyoNacional();

								lsan_solicitudApoyoNac.setIdSolicitud(lth_turnoHistoria.getIdSolicitud());
								lsan_solicitudApoyoNac.setIdCirculoSolicitante(asanui_solicitudApoyoNac.getIdCirculo());
								lsan_solicitudApoyoNac.setIdSolicitud(lth_turnoHistoria.getIdSolicitud());
								lsan_solicitudApoyoNac.setIdSolicitudRegistro(lt_turno.getIdSolicitud());
								lsan_solicitudApoyoNac.setIdTurnoRegistro(lt_turno.getIdTurno());
								lsan_solicitudApoyoNac.setIdTurnoHistoriaRegistro(
								    NumericUtils.getLong(lt_turno.getIdTurnoHistoria())
								);
								lsan_solicitudApoyoNac.setIdSolicitudRegistro(lt_turno.getIdSolicitud());

								lsan_solicitudApoyoNac.setIdUsuarioCreacion(as_userId);
								lsan_solicitudApoyoNac.setIpModificacion(as_remoteIp);

								DaoCreator.getSolicitudApoyoNacionalDAO(ldm_manager)
									          .insertOrUpdate(lsan_solicitudApoyoNac, true);
							}
						}
					}
					else
						throw new B2BException(ErrorKeys.ERROR_INTERNO_SISTEMA);
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("enviarADireccionTecnica", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return asanui_solicitudApoyoNac;
	}

	/**
	 * Consultar solicitudes apoyo nacional.
	 *
	 * @param as_nir de as nir
	 * @return el valor de collection
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public synchronized Collection<SolicitudApoyoNacional> findByNir(String as_nir)
	    throws B2BException
	{
		DAOManager                         ldm_manager;
		Collection<SolicitudApoyoNacional> lc_solicitudApoyoNacional;

		ldm_manager                   = DaoManagerFactory.getDAOManager();
		lc_solicitudApoyoNacional     = null;

		try
		{
			if(StringUtils.isValidString(as_nir))
			{
				lc_solicitudApoyoNacional = DaoCreator.getSolicitudApoyoNacionalDAO(ldm_manager).findByNIR(as_nir);

				if(CollectionUtils.isValidCollection(lc_solicitudApoyoNacional))
				{
					for(SolicitudApoyoNacional lsan_iterador : lc_solicitudApoyoNacional)
					{
						if(lsan_iterador != null)
						{
							long ll_etapa;

							ll_etapa = lsan_iterador.getEtapaActual();

							if(ll_etapa > 0)
								lsan_iterador.setFinalizada(ll_etapa >= EtapaCommon.ID_ETAPA_ENTREGA_ORIP_INSCRIPCION);

							{
								TurnoHistoria    lth_turnoHistoria;
								TurnoHistoriaDAO lthd_dao;

								lthd_dao              = DaoCreator.getTurnoHistoriaDAO(ldm_manager);
								lth_turnoHistoria     = lthd_dao.findById(
									    NumericUtils.getLongWrapper(lsan_iterador.getIdTurnoHistoriaRegistro())
									);

								if(lth_turnoHistoria != null)
								{
									BigDecimal lbd_turnoAnterior;

									lbd_turnoAnterior = lth_turnoHistoria.getIdAnterior();

									if(NumericUtils.isValidBigDecimal(lbd_turnoAnterior))
									{
										lth_turnoHistoria = lthd_dao.findById(
											    NumericUtils.getLongWrapper(lbd_turnoAnterior)
											);

										if(lth_turnoHistoria != null)
											lsan_iterador.setObservacion(lth_turnoHistoria.getObservaciones());
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

			clh_LOGGER.error("findByNir", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lc_solicitudApoyoNacional;
	}

	/**
	 * Genera pdf de reporte de solicitud apoyo nacional y lo guarda en la base de datos.
	 *
	 * @param ath_parametros de ath parametros
	 * @param adm_manager Objeto para manipular la conexión con la base de datos
	 * @param as_userId de as user id
	 * @param as_remoteIp de as remote ip
	 * @return devuelve el valor de byte[]
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public synchronized byte[] generarPDFReporteSoporteApoyoNacional(
	    SolicitudApoyoNacionalUI asanui_solicitudApoyoNac, DAOManager adm_manager, String as_userId, String as_remoteIp
	)
	    throws B2BException
	{
		byte[]        lba_solicitudApoyoNacional;
		TurnoHistoria lth_turnoHistoria;

		lth_turnoHistoria              = asanui_solicitudApoyoNac.getTurnoHistoria();
		lba_solicitudApoyoNacional     = null;

		try
		{
			if(lth_turnoHistoria != null)
			{
				Constantes lc_plantilla;
				lc_plantilla = new Constantes();

				lc_plantilla.setIdConstante(ConstanteCommon.PLANTILLA_REPORTE_SOPORTE_APOYO_NACIONAL);
				lc_plantilla = DaoCreator.getConstantesDAO(adm_manager).findImagen(lc_plantilla);

				if(lc_plantilla != null)
				{
					byte[] lba_plantilla;

					lba_plantilla = lc_plantilla.getImagenes();

					if(lba_plantilla != null)
					{
						String     ls_plantilla;
						String     ls_tag;
						String     ls_idOficinaOrigen;
						BigDecimal lbd_version;
						String     ls_consecutivoOficio;
						String     ls_idCirculo;

						ls_plantilla             = new String(lba_plantilla);
						ls_consecutivoOficio     = null;
						ls_idOficinaOrigen       = null;
						lbd_version              = NumericUtils.DEFAULT_BIG_DECIMAL_VALUE;
						ls_idCirculo             = lth_turnoHistoria.getIdCirculo();

						if(StringUtils.isValidString(ls_idCirculo))
						{
							ls_tag = "<TAG_NOMBRE_ORIP>";

							if(ls_plantilla.contains(ls_tag))
							{
								CirculoRegistral lcr_circuloRegistral;

								lcr_circuloRegistral = DaoCreator.getCirculoRegistralDAO(adm_manager)
										                             .findById(ls_idCirculo);

								if(lcr_circuloRegistral != null)
								{
									String ls_nombre;

									ls_nombre = lcr_circuloRegistral.getNombre();

									if(StringUtils.isValidString(ls_nombre))
										ls_plantilla = ls_plantilla.replace(ls_tag, ls_nombre);
								}

								ls_idOficinaOrigen     = lcr_circuloRegistral.getIdOficinaOrigen();
								lbd_version            = lcr_circuloRegistral.getVersion();

								ls_tag = "<TAG_MUN_OFI_ORIGEN>";

								if(
								    ls_plantilla.contains(ls_tag) && StringUtils.isValidString(ls_idOficinaOrigen)
									    && (lbd_version != null)
								)
								{
									OficinaOrigen loo_oficinaOrigen;

									loo_oficinaOrigen = new OficinaOrigen();
									loo_oficinaOrigen.setIdOficinaOrigen(ls_idOficinaOrigen);
									loo_oficinaOrigen.setVersion(lbd_version);

									loo_oficinaOrigen = DaoCreator.getOficinaOrigenDAO(adm_manager)
											                          .findById(loo_oficinaOrigen);

									if(loo_oficinaOrigen != null)
									{
										if(ls_plantilla.contains(ls_tag))
										{
											Municipio lm_municipio;

											lm_municipio     = new Municipio();

											lm_municipio = DaoCreator.getMunicipioDAO(adm_manager)
													                     .findById(
													    loo_oficinaOrigen.getIdPais(),
													    loo_oficinaOrigen.getIdDepartamento(),
													    loo_oficinaOrigen.getIdMunicipio()
													);

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
							}

							ls_tag = "<TAG_NIR>";

							if(ls_plantilla.contains(ls_tag))
							{
								Solicitud ls_solicitud;

								ls_solicitud = DaoCreator.getSolicitudDAO(adm_manager)
										                     .findById(lth_turnoHistoria.getIdSolicitud());

								if(ls_solicitud != null)
								{
									String ls_nir;

									ls_nir = ls_solicitud.getNir();

									if(StringUtils.isValidString(ls_nir))
										ls_plantilla = ls_plantilla.replace(ls_tag, ls_nir);

									ls_tag = "<TAG_FECHA_LARGA>";

									Date ld_fechaCreacion;

									ld_fechaCreacion = ls_solicitud.getFechaCreacion();

									if(ls_plantilla.contains(ls_tag))
									{
										if(ld_fechaCreacion != null)
											ls_plantilla = ls_plantilla.replace(
												    ls_tag, StringUtils.getString(ld_fechaCreacion)
												);
										else
											ls_plantilla = ls_plantilla.replace(ls_tag, "");
									}
								}
							}

							ls_tag = "<TAG_USUARIO>";

							if(ls_plantilla.contains(ls_tag))
								ls_plantilla = ls_plantilla.replace(ls_tag, as_userId);

							ls_tag = "<TAG_RELACION_TURNOS_APOYO_NACIONAL>";

							if(ls_plantilla.contains(ls_tag))
							{
								int li_finalTag;

								li_finalTag = ls_plantilla.lastIndexOf(ls_tag);

								if(li_finalTag > 0)
								{
									String ls_textoMitadSuperior;
									String ls_textoMitadInferior;
									String ls_tagNew;

									ls_textoMitadSuperior     = ls_plantilla.substring(0, li_finalTag);
									ls_textoMitadInferior     = ls_plantilla.substring(li_finalTag + ls_tag.length());

									ls_tagNew     = "{\\*\\bkmkstart TABLA_TURNOS_APOYO_NACIONAL}{\\*\\bkmkend TABLA_TURNOS_APOYO_NACIONAL} \\line "
										+ ls_tag;

									ls_plantilla = ls_textoMitadSuperior + IdentificadoresCommon.ESPACIO_VACIO
										+ ls_tagNew + IdentificadoresCommon.ESPACIO_VACIO + ls_textoMitadInferior;
								}
							}

							ls_plantilla = limpiarTags(ls_plantilla);

							{
								ByteArrayInputStream      lbais_docInStream;
								ByteArrayOutputStream     lbaos_docOutStream;
								Collection<Turno>         lct_turnos;
								com.aspose.words.Document ld_doc;
								DocumentBuilder           ldb_builder;
								Table                     lt_table;

								lbais_docInStream      = new ByteArrayInputStream(ls_plantilla.getBytes());
								lbaos_docOutStream     = new ByteArrayOutputStream();
								ld_doc                 = new com.aspose.words.Document(lbais_docInStream);
								ldb_builder            = new DocumentBuilder(ld_doc);

								ldb_builder.moveToBookmark("TABLA_TURNOS_APOYO_NACIONAL");

								lct_turnos = asanui_solicitudApoyoNac.getTurnos();

								if(CollectionUtils.isValidCollection(lct_turnos))
								{
									int  li_tamanoLetra;
									long ll_porcentajetablas;

									ll_porcentajetablas     = (long)16.6;
									li_tamanoLetra          = 8;
									lt_table                = ldb_builder.startTable();

									ldb_builder.insertCell();
									ldb_builder.getParagraphFormat().setAlignment(ParagraphAlignment.LEFT);
									ldb_builder.getCellFormat().setHorizontalMerge(CellMerge.NONE);
									ldb_builder.getFont().setUnderline(0);
									ldb_builder.getCellFormat()
										           .setPreferredWidth(PreferredWidth.fromPercent(ll_porcentajetablas));

									ldb_builder.getFont().setSize(li_tamanoLetra);
									ldb_builder.write("NIR");

									ldb_builder.insertCell();
									ldb_builder.getCellFormat().setHorizontalMerge(CellMerge.NONE);
									ldb_builder.getCellFormat()
										           .setPreferredWidth(PreferredWidth.fromPercent(ll_porcentajetablas));
									ldb_builder.getFont().setSize(li_tamanoLetra);
									ldb_builder.write("TURNO");

									ldb_builder.insertCell();
									ldb_builder.getCellFormat().setHorizontalMerge(CellMerge.NONE);
									ldb_builder.getCellFormat()
										           .setPreferredWidth(PreferredWidth.fromPercent(ll_porcentajetablas));
									ldb_builder.getFont().setSize(li_tamanoLetra);
									ldb_builder.write("FECHA TURNO");

									ldb_builder.insertCell();
									ldb_builder.getCellFormat().setHorizontalMerge(CellMerge.NONE);
									ldb_builder.getCellFormat()
										           .setPreferredWidth(PreferredWidth.fromPercent(ll_porcentajetablas));
									ldb_builder.getFont().setSize(li_tamanoLetra);
									ldb_builder.write("MATRICULAS");

									ldb_builder.insertCell();
									ldb_builder.getCellFormat().setHorizontalMerge(CellMerge.NONE);
									ldb_builder.getCellFormat()
										           .setPreferredWidth(PreferredWidth.fromPercent(ll_porcentajetablas));
									ldb_builder.getFont().setSize(li_tamanoLetra);
									ldb_builder.write("ACTOS");

									ldb_builder.insertCell();
									ldb_builder.getCellFormat().setHorizontalMerge(CellMerge.NONE);
									ldb_builder.getCellFormat()
										           .setPreferredWidth(PreferredWidth.fromPercent(ll_porcentajetablas));
									ldb_builder.getFont().setSize(li_tamanoLetra);
									ldb_builder.write("DOCUMENTO PUBLICO");

									ldb_builder.endRow();

									for(Turno ll_tmpTurno : lct_turnos)
									{
										ldb_builder.insertCell();
										ldb_builder.getCellFormat().setHorizontalMerge(CellMerge.NONE);
										ldb_builder.getCellFormat()
											           .setPreferredWidth(
											    PreferredWidth.fromPercent(ll_porcentajetablas)
											);
										ldb_builder.getFont().setSize(li_tamanoLetra);
										ldb_builder.getFont().setBold(false);
										ldb_builder.write(StringUtils.getStringNotNull(ll_tmpTurno.getNir()));

										ldb_builder.insertCell();
										ldb_builder.getCellFormat().setHorizontalMerge(CellMerge.NONE);
										ldb_builder.getCellFormat()
											           .setPreferredWidth(
											    PreferredWidth.fromPercent(ll_porcentajetablas)
											);
										ldb_builder.getFont().setSize(li_tamanoLetra);
										ldb_builder.getFont().setBold(false);
										ldb_builder.write(StringUtils.getStringNotNull(ll_tmpTurno.getIdTurno()));

										ldb_builder.insertCell();
										ldb_builder.getCellFormat().setHorizontalMerge(CellMerge.NONE);
										ldb_builder.getCellFormat()
											           .setPreferredWidth(
											    PreferredWidth.fromPercent(ll_porcentajetablas)
											);
										ldb_builder.getFont().setSize(li_tamanoLetra);
										ldb_builder.getFont().setBold(false);
										ldb_builder.write(
										    StringUtils.getStringNotNull(
										        StringUtils.getString(ll_tmpTurno.getFechaCreacion())
										    )
										);

										ldb_builder.insertCell();
										ldb_builder.getCellFormat().setHorizontalMerge(CellMerge.NONE);
										ldb_builder.getCellFormat()
											           .setPreferredWidth(
											    PreferredWidth.fromPercent(ll_porcentajetablas)
											);
										ldb_builder.getFont().setSize(li_tamanoLetra);
										ldb_builder.getFont().setBold(false);
										ldb_builder.write(
										    StringUtils.getStringNotNull(ll_tmpTurno.getMatriculasApoyoNacional())
										);

										ldb_builder.insertCell();
										ldb_builder.getCellFormat().setHorizontalMerge(CellMerge.NONE);
										ldb_builder.getCellFormat()
											           .setPreferredWidth(
											    PreferredWidth.fromPercent(ll_porcentajetablas)
											);
										ldb_builder.getFont().setSize(li_tamanoLetra);
										ldb_builder.getFont().setBold(false);
										ldb_builder.write(
										    StringUtils.getStringNotNull(ll_tmpTurno.getActosApoyoNacional())
										);

										ldb_builder.insertCell();
										ldb_builder.getCellFormat().setHorizontalMerge(CellMerge.NONE);
										ldb_builder.getCellFormat()
											           .setPreferredWidth(
											    PreferredWidth.fromPercent(ll_porcentajetablas)
											);
										ldb_builder.getFont().setSize(li_tamanoLetra);
										ldb_builder.getFont().setBold(false);
										ldb_builder.write(StringUtils.getStringNotNull(ll_tmpTurno.getInfoDocumento()));

										ldb_builder.endRow();
									}

									lt_table.setAlignment(TableAlignment.CENTER);
									ldb_builder.endTable();
								}

								ldb_builder.writeln(ControlChar.LINE_BREAK);

								ld_doc.save(lbaos_docOutStream, SaveFormat.DOC);

								byte[] docBytes = lbaos_docOutStream.toByteArray();

								lba_solicitudApoyoNacional = new PDFGenerator().convertirRTFToPDF(
									    docBytes, adm_manager
									);

								if(lba_solicitudApoyoNacional == null)
									throw new B2BException(ErrorKeys.NO_SE_GENERO_ARCHIVO);
							}

							Imagenes    li_imagenes;
							ImagenesDAO li_DAO;
							long        ll_idImagenTemp;

							li_imagenes     = new Imagenes();
							li_DAO          = DaoCreator.getImagenesDAO(adm_manager);

							li_imagenes.setTipoArchivo(ExtensionCommon.PDF_MAYUS);
							li_imagenes.setIdUsuarioCreacion(as_userId);
							li_imagenes.setIpCreacion(as_remoteIp);
							li_imagenes.setImagenBlob(lba_solicitudApoyoNacional);

							ll_idImagenTemp = li_DAO.insertOrUpdate(li_imagenes, true);

							if(ll_idImagenTemp > 0)
							{
								DocumentosSalida    lds_documentoSalida;
								DocumentosSalidaDAO lds_DAO;
								Long                ll_idImagen;

								lds_documentoSalida     = new DocumentosSalida();
								lds_DAO                 = DaoCreator.getDocumentosSalidaDAO(adm_manager);
								ll_idImagen             = NumericUtils.getLongWrapper(ll_idImagenTemp);

								lds_documentoSalida.setIdSolicitud(lth_turnoHistoria.getIdSolicitud());
								lds_documentoSalida.setRepositorio(RepositorioCommon.FINAL);
								lds_documentoSalida.setIdTipoDocumental(TipoDocumentalCommon.INFORMES);
								lds_documentoSalida.setConsecutivoOficio(ls_consecutivoOficio);
								lds_documentoSalida.setTipo(TipoArchivoCommon.REPORTE_SOPORTE_APOYO_NACIONAL);
								lds_documentoSalida.setTipoArchivo(ExtensionCommon.PDF_MAYUS);
								lds_documentoSalida.setEstado(EstadoCommon.ACTIVO);
								lds_documentoSalida.setIdImagen(ll_idImagen);
								lds_documentoSalida.setIdTurnoHistoria(
								    NumericUtils.getInteger(lth_turnoHistoria.getIdTurnoHistoria())
								);
								lds_documentoSalida.setIdUsuarioCreacion(as_userId);
								lds_documentoSalida.setIpCreacion(as_remoteIp);

								lds_DAO.insertOrUpdate(lds_documentoSalida, true);
							}
						}
					}
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("generarPDFReporteSoporteApoyoNacional", lb2be_e);

			throw lb2be_e;
		}
		catch(Exception lb2be_e)
		{
			clh_LOGGER.error("generarPDFReporteSoporteApoyoNacional", lb2be_e);

			throw new B2BException(ErrorKeys.CONTAINER_ERROR, lb2be_e);
		}

		return lba_solicitudApoyoNacional;
	}

	/**
	 * Genera pdf de solicitud apoyo nacional y lo guarda en la base de datos.
	 *
	 * @param ath_parametros de ath parametros
	 * @param adm_manager Objeto para manipular la conexión con la base de datos
	 * @param as_userId de as user id
	 * @param as_remoteIp de as remote ip
	 * @return devuelve el valor de byte[]
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public synchronized byte[] generarPDFSolicitudApoyoNacional(
	    TurnoHistoria ath_parametros, DAOManager adm_manager, String as_userId, String as_remoteIp
	)
	    throws B2BException
	{
		byte[] lba_solicitudApoyoNacional;

		lba_solicitudApoyoNacional = null;

		if(ath_parametros != null)
		{
			Constantes lc_plantilla;
			lc_plantilla = new Constantes();

			lc_plantilla.setIdConstante(ConstanteCommon.PLANTILLA_SOLICITUD_APOYO_NACIONAL);
			lc_plantilla = DaoCreator.getConstantesDAO(adm_manager).findImagen(lc_plantilla);

			if(lc_plantilla != null)
			{
				byte[] lba_plantilla;

				lba_plantilla = lc_plantilla.getImagenes();

				if(lba_plantilla != null)
				{
					String     ls_plantilla;
					String     ls_tag;
					String     ls_idOficinaOrigen;
					BigDecimal lbd_version;
					String     ls_consecutivoOficio;
					String     ls_idCirculo;

					ls_plantilla             = new String(lba_plantilla);
					ls_consecutivoOficio     = null;
					ls_idOficinaOrigen       = null;
					lbd_version              = NumericUtils.DEFAULT_BIG_DECIMAL_VALUE;
					ls_idCirculo             = ath_parametros.getIdCirculo();

					if(StringUtils.isValidString(ls_idCirculo))
					{
						ls_tag = "<TAG_NOMBRE_ORIP>";

						if(ls_plantilla.contains(ls_tag))
						{
							CirculoRegistral lcr_circuloRegistral;

							lcr_circuloRegistral = DaoCreator.getCirculoRegistralDAO(adm_manager).findById(
								    ls_idCirculo
								);

							if(lcr_circuloRegistral != null)
							{
								String ls_nombre;

								ls_nombre = lcr_circuloRegistral.getNombre();

								if(StringUtils.isValidString(ls_nombre))
									ls_plantilla = ls_plantilla.replace(ls_tag, ls_nombre);
							}

							ls_idOficinaOrigen     = lcr_circuloRegistral.getIdOficinaOrigen();
							lbd_version            = lcr_circuloRegistral.getVersion();

							ls_tag = "<TAG_ID_OFI_ORIGEN>";

							if(
							    ls_plantilla.contains(ls_tag) && StringUtils.isValidString(ls_idOficinaOrigen)
								    && (lbd_version != null)
							)
							{
								OficinaOrigen loo_oficinaOrigen;
								String        ls_nombreOficinaOrigen;

								loo_oficinaOrigen = new OficinaOrigen();
								loo_oficinaOrigen.setIdOficinaOrigen(ls_idOficinaOrigen);
								loo_oficinaOrigen.setVersion(lbd_version);

								loo_oficinaOrigen = DaoCreator.getOficinaOrigenDAO(adm_manager)
										                          .findById(loo_oficinaOrigen);

								if(loo_oficinaOrigen != null)
								{
									ls_nombreOficinaOrigen = loo_oficinaOrigen.getNombre();

									if(StringUtils.isValidString(ls_nombreOficinaOrigen))
										ls_plantilla = ls_plantilla.replace(ls_tag, ls_nombreOficinaOrigen);
								}
							}
						}

						ls_tag = "<TAG_OFICIO>";

						if(ls_plantilla.contains(ls_tag))
						{
							NumeracionOficiosCirculo lnoc_numeracionOficios;

							lnoc_numeracionOficios = findNumeracionOficiosCirculo(
								    ath_parametros, adm_manager, as_userId, as_remoteIp
								);

							if(lnoc_numeracionOficios != null)
							{
								ls_consecutivoOficio     = lnoc_numeracionOficios.getConsecutivoGenerado();

								ls_plantilla = ls_plantilla.replace(ls_tag, ls_consecutivoOficio);
							}
						}

						ls_tag = "<TAG_NIR>";

						if(ls_plantilla.contains(ls_tag))
						{
							Solicitud ls_solicitud;

							ls_solicitud = DaoCreator.getSolicitudDAO(adm_manager)
									                     .findById(ath_parametros.getIdSolicitud());

							if(ls_solicitud != null)
							{
								String ls_nir;

								ls_nir = ls_solicitud.getNir();

								if(StringUtils.isValidString(ls_nir))
									ls_plantilla = ls_plantilla.replace(ls_tag, ls_nir);

								ls_tag = "<TAG_FECHA_LARGA>";

								Date ld_fechaCreacion;

								ld_fechaCreacion = ls_solicitud.getFechaCreacion();

								if(ls_plantilla.contains(ls_tag))
								{
									if(ld_fechaCreacion != null)
										ls_plantilla = ls_plantilla.replace(
											    ls_tag, StringUtils.getString(ld_fechaCreacion)
											);
									else
										ls_plantilla = ls_plantilla.replace(ls_tag, "");
								}
							}
						}

						ls_tag = "<TAG_USUARIO>";

						if(ls_plantilla.contains(ls_tag))
							ls_plantilla = ls_plantilla.replace(ls_tag, as_userId);

						ls_plantilla = limpiarTags(ls_plantilla);

						if(StringUtils.isValidString(ls_plantilla))
						{
							lba_solicitudApoyoNacional = new PDFGenerator().convertirRTFToPDF(
								    ls_plantilla.getBytes(), adm_manager
								);

							if(lba_solicitudApoyoNacional == null)
								throw new B2BException(ErrorKeys.NO_SE_GENERO_ARCHIVO);
						}

						Imagenes    li_imagenes;
						ImagenesDAO li_DAO;
						long        ll_idImagenTemp;

						li_imagenes     = new Imagenes();
						li_DAO          = DaoCreator.getImagenesDAO(adm_manager);

						li_imagenes.setTipoArchivo(ExtensionCommon.PDF_MAYUS);
						li_imagenes.setIdUsuarioCreacion(as_userId);
						li_imagenes.setIpCreacion(as_remoteIp);
						li_imagenes.setImagenBlob(lba_solicitudApoyoNacional);

						ll_idImagenTemp = li_DAO.insertOrUpdate(li_imagenes, true);

						if(ll_idImagenTemp > 0)
						{
							DocumentosSalida    lds_documentoSalida;
							DocumentosSalidaDAO lds_DAO;
							Long                ll_idImagen;

							lds_documentoSalida     = new DocumentosSalida();
							lds_DAO                 = DaoCreator.getDocumentosSalidaDAO(adm_manager);
							ll_idImagen             = NumericUtils.getLongWrapper(ll_idImagenTemp);

							lds_documentoSalida.setIdSolicitud(ath_parametros.getIdSolicitud());
							lds_documentoSalida.setRepositorio(RepositorioCommon.FINAL);
							lds_documentoSalida.setIdTipoDocumental(TipoDocumentalCommon.RESUMEN_DE_LA_SOLICITUD);
							lds_documentoSalida.setConsecutivoOficio(ls_consecutivoOficio);
							lds_documentoSalida.setTipo(TipoArchivoCommon.SOLICITUD_APOYO_NACIONAL);
							lds_documentoSalida.setTipoArchivo(ExtensionCommon.PDF_MAYUS);
							lds_documentoSalida.setEstado(EstadoCommon.ACTIVO);
							lds_documentoSalida.setIdImagen(ll_idImagen);
							lds_documentoSalida.setIdTurnoHistoria(
							    NumericUtils.getInteger(ath_parametros.getIdTurnoHistoria())
							);
							lds_documentoSalida.setIdUsuarioCreacion(as_userId);
							lds_documentoSalida.setIpCreacion(as_remoteIp);

							lds_DAO.insertOrUpdate(lds_documentoSalida, true);
						}
					}
				}
			}
		}

		return lba_solicitudApoyoNacional;
	}

	/**
	 * Generar solicitud apoyo nacional.
	 *
	 * @param asanui_solicitudApoyoNac de lsanui solicitud apoyo nacional
	 * @param as_userId de as user id
	 * @param as_remoteIp de as remote ip
	 * @return el valor de solicitud apoyo nacional UI
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public synchronized SolicitudApoyoNacionalUI generarSolicitudApoyoNacional(
	    SolicitudApoyoNacionalUI asanui_solicitudApoyoNac, String as_userId, String as_remoteIp
	)
	    throws B2BException
	{
		DAOManager ldm_manager;

		ldm_manager = DaoManagerFactory.getDAOManager();

		try
		{
			if(asanui_solicitudApoyoNac != null)
			{
				String    ls_nir;
				Solicitud ls_solicitud;

				ls_nir           = crearNir(as_userId, as_remoteIp, ldm_manager);
				ls_solicitud     = null;

				if(StringUtils.isValidString(ls_nir))
				{
					asanui_solicitudApoyoNac.setNirProceso(ls_nir);

					SolicitudDAO lsd_DAO;
					int          li_secuencia;

					lsd_DAO          = DaoCreator.getSolicitudDAO(ldm_manager);
					ls_solicitud     = new Solicitud();

					li_secuencia = lsd_DAO.findSecuence();

					ls_solicitud.setIdSolicitud(StringUtils.getString(li_secuencia));
					ls_solicitud.setNir(ls_nir);
					ls_solicitud.setIdCirculoAnt(asanui_solicitudApoyoNac.getIdCirculo());
					ls_solicitud.setIdTipoRecepcion(TipoRecepcionCommon.ORIP_DE_ORIGEN);
					ls_solicitud.setIdAutorizacionComunicacion(MedioNotificarCommon.CORREO_ELECTRONICO);
					ls_solicitud.setIdAutorizacionNotificacion(MedioNotificarCommon.CORREO_ELECTRONICO);
					ls_solicitud.setFechaSolicitud(new Date());
					ls_solicitud.setDerechoPeticion(EstadoCommon.N);
					ls_solicitud.setParticipaInterviniente(EstadoCommon.N);
					ls_solicitud.setDigitalizada(EstadoCommon.N);
					ls_solicitud.setIdCalidadSolicitante(CalidadSolicitanteCommon.FUNCIONARIO);
					ls_solicitud.setIdProcedencia(ProcedenciaCommon.ENTIDADES);

					Usuario lu_usuario;
					lu_usuario = new Usuario();

					lu_usuario.setIdUsuario(as_userId);
					lu_usuario = DaoCreator.getUsuarioDAO(ldm_manager).findById(lu_usuario);

					if(lu_usuario != null)
					{
						Persona lp_persona;

						lp_persona = new Persona();

						lp_persona.setIdDocumentoTipo(lu_usuario.getIdDocumentoTipo());
						lp_persona.setNumeroDocumento(lu_usuario.getNumeroDocumento());

						lp_persona = DaoCreator.getPersonaDAO(ldm_manager).findByMaxIdDocNum(lp_persona);

						if(lp_persona != null)
							ls_solicitud.setIdPersona(lp_persona.getIdPersona());
						else
						{
							lp_persona = new Persona();

							lp_persona.setIdDocumentoTipo(lu_usuario.getIdDocumentoTipo());
							lp_persona.setNumeroDocumento(lu_usuario.getNumeroDocumento());
							lp_persona.setPrimerNombre(lu_usuario.getPrimerNombre());
							lp_persona.setSegundoNombre(lu_usuario.getSegundoNombre());
							lp_persona.setPrimerApellido(lu_usuario.getPrimerApellido());
							lp_persona.setSegundoApellido(lu_usuario.getSegundoApellido());
							lp_persona.setCorreoElectronico(lu_usuario.getCorreoElectronicoCorporativo());
							lp_persona.setIdPais(IdentificadoresCommon.INDICATIVO_PAIS_DEFAULT);
							lp_persona.setIdTipoPersona(IdentificadoresCommon.N);
							lp_persona.setOrigenBachue(IdentificadoresCommon.S);
							lp_persona.setIdUsuarioCreacion(as_userId);
							lp_persona.setIpCreacion(as_remoteIp);

							lp_persona = DaoCreator.getPersonaDAO(ldm_manager).insertOrUpdate(lp_persona, true);

							if(lp_persona != null)
								ls_solicitud.setIdPersona(lp_persona.getIdPersona());
						}

						int                      li_idCorreoElectronico;
						PersonaCorreoElectronico lpce_personaCorreoElectronico;

						lpce_personaCorreoElectronico = new PersonaCorreoElectronico();

						lpce_personaCorreoElectronico.setIdPersona(lp_persona.getIdPersona());

						li_idCorreoElectronico = DaoCreator.getPersonaCorreoElectronicoDAO(ldm_manager)
								                               .findIdCorreoElectronico(lpce_personaCorreoElectronico);

						if(li_idCorreoElectronico <= 0)
						{
							PersonaCorreoElectronico lpce_personaCorreo;

							lpce_personaCorreo = new PersonaCorreoElectronico();

							lpce_personaCorreo.setIdPersona(lp_persona.getIdPersona());
							lpce_personaCorreo.setCorreoElectronico(lu_usuario.getCorreoElectronico());
							lpce_personaCorreo.setFechaDesde(new Date());
							lpce_personaCorreo.setIdUsuarioCreacion(as_userId);
							lpce_personaCorreo.setIpCreacion(as_remoteIp);

							li_idCorreoElectronico = NumericUtils.getInt(
								    DaoCreator.getPersonaCorreoElectronicoDAO(ldm_manager)
									              .insertOrUpdate(lpce_personaCorreo, true)
								);
						}

						ls_solicitud.setIdCorreoElectronico(StringUtils.getString(li_idCorreoElectronico));
						ls_solicitud.setIdCorreoElectronicoComunicacion(StringUtils.getString(li_idCorreoElectronico));
					}

					ls_solicitud.setIdProceso(ProcesoCommon.ID_PROCESO_49);
					ls_solicitud.setIdSubproceso(SubProcesoCommon.SOLICITUD_APOYO_NACIONAL);
					ls_solicitud.setIdUsuarioCreacion(as_userId);
					ls_solicitud.setIpCreacion(as_remoteIp);

					lsd_DAO.insertOrUpdate(ls_solicitud, true);

					asanui_solicitudApoyoNac.setSolicitud(ls_solicitud);
				}
				else
					throw new B2BException(ErrorKeys.NO_GENERO_NIR);

				Collection<Turno> lct_turnosApoyoNacional;

				lct_turnosApoyoNacional = asanui_solicitudApoyoNac.getTurnos();

				if(CollectionUtils.isValidCollection(lct_turnosApoyoNacional) && (lct_turnosApoyoNacional.size() >= 11))
				{
					for(Turno lt_turno : lct_turnosApoyoNacional)
					{
						if(lt_turno != null)
						{
							Turno lt_tmp;
							lt_tmp = DaoCreator.getTurnoDAO(ldm_manager).findById(lt_turno.getIdTurno());

							if(lt_tmp != null)
							{
								lt_tmp.setSolicitudApoyo(IdentificadoresCommon.S);
								lt_tmp.setIdUsuarioModificacion(as_userId);
								lt_tmp.setIpModificacion(as_remoteIp);

								DaoCreator.getTurnoDAO(ldm_manager).actualizarSolicitudApoyo(lt_tmp);

								SolicitudAsociada lsa_solicitudNueva;

								lsa_solicitudNueva = new SolicitudAsociada();

								lsa_solicitudNueva.setIdSolicitud(lt_tmp.getIdSolicitud());
								lsa_solicitudNueva.setIdSolicitud1(ls_solicitud.getIdSolicitud());
								lsa_solicitudNueva.setIndVinculado(EstadoCommon.A);
								lsa_solicitudNueva.setIdUsuarioCreacion(as_userId);
								lsa_solicitudNueva.setIpCreacion(as_remoteIp);

								DaoCreator.getSolicitudAsociadaDAO(ldm_manager).insertOrUpdate(
								    lsa_solicitudNueva, true
								);
							}
						}
					}
				}
				else
					throw new B2BException(ErrorKeys.ERROR_INTERNO_SISTEMA);

				TurnoHistoria lth_turnoHistoria;

				lth_turnoHistoria = new TurnoHistoria();

				lth_turnoHistoria.setIdSolicitud(ls_solicitud.getIdSolicitud());
				lth_turnoHistoria.setIdCirculo(asanui_solicitudApoyoNac.getIdCirculo());
				lth_turnoHistoria.setIdUsuarioModificacion(as_userId);
				lth_turnoHistoria.setIpModificacion(as_remoteIp);

				asanui_solicitudApoyoNac.setTurnoHistoria(lth_turnoHistoria);

				generarPDFSolicitudApoyoNacional(lth_turnoHistoria, ldm_manager, as_userId, as_remoteIp);
				generarPDFReporteSoporteApoyoNacional(asanui_solicitudApoyoNac, ldm_manager, as_userId, as_remoteIp);
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("generarSolicitudApoyoNacional", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return asanui_solicitudApoyoNac;
	}

	/**
	 * Termina el proceso de solicitud apoyo nacional.
	 *
	 * @param as_idSolicitud de id de la solicitud
	 * @param as_userId de as user id
	 * @param as_localIp de as local ip
	 * @param as_remoteIp de as remote ip
	 * @return el valor de solicitud apoyo nacional UI
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void terminarSolicitudApoyoNacional(String as_idSolicitud, String as_userId, String as_remoteIp)
	    throws B2BException
	{
		if(StringUtils.isValidString(as_idSolicitud))
		{
			DAOManager ldm_manager;

			ldm_manager = DaoManagerFactory.getDAOManager();

			try
			{
				Turno lt_turno;

				lt_turno = new Turno();
				lt_turno.setIdSolicitud(as_idSolicitud);
				lt_turno.setIdProceso(ProcesoCommon.ID_PROCESO_49);
				lt_turno = DaoCreator.getTurnoDAO(ldm_manager).findByIdSolicitudProceso(lt_turno);

				if(lt_turno != null)
				{
					String ls_idTurno;

					ls_idTurno = lt_turno.getIdTurno();

					if(StringUtils.isValidString(ls_idTurno))
					{
						TurnoHistoria lth_turnoHistoria;

						lth_turnoHistoria = DaoCreator.getTurnoHistoriaDAO(ldm_manager)
								                          .findByIdTurno(lt_turno.getIdTurno());

						if(lth_turnoHistoria != null)
						{
							MotivoTramite lmt_motivoTramite;

							lmt_motivoTramite = new MotivoTramite(
								    MotivoTramiteCommon.APROBADO_SOLICITUD_APOYO_NACIONAL,
								    NumericUtils.getLong(lth_turnoHistoria.getIdEtapa())
								);

							terminarTurnoHistoriaYCrearEtapa(
							    lth_turnoHistoria, ldm_manager, lmt_motivoTramite, as_userId, as_remoteIp,
							    EstadoCommon.TERMINADA
							);
						}
					}
				}
			}
			catch(B2BException lb2be_e)
			{
				ldm_manager.setRollbackOnly();

				clh_LOGGER.error("terminarProcesoApoyoNacionalAprobacion", lb2be_e);

				throw lb2be_e;
			}
			finally
			{
				ldm_manager.close();
			}
		}
	}
}
