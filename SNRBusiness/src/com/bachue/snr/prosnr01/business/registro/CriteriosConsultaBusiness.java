package com.bachue.snr.prosnr01.business.registro;

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

import com.b2bsg.common.file.excel.ExcelUtils;

import com.b2bsg.common.logging.LoggerHandler;

import com.b2bsg.common.util.CollectionUtils;
import com.b2bsg.common.util.DateUtils;
import com.b2bsg.common.util.NumericUtils;
import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.business.BaseBusiness;
import com.bachue.snr.prosnr01.business.utilidades.PDFGenerator;

import com.bachue.snr.prosnr01.common.constants.CampoCriterioBusquedaCommon;
import com.bachue.snr.prosnr01.common.constants.ConstanteCommon;
import com.bachue.snr.prosnr01.common.constants.ErrorKeys;
import com.bachue.snr.prosnr01.common.constants.EstadoCommon;
import com.bachue.snr.prosnr01.common.constants.EtapaCommon;
import com.bachue.snr.prosnr01.common.constants.ExtensionCommon;
import com.bachue.snr.prosnr01.common.constants.FormatoFechaCommon;
import com.bachue.snr.prosnr01.common.constants.IdentificadoresCommon;
import com.bachue.snr.prosnr01.common.constants.MedioNotificarCommon;
import com.bachue.snr.prosnr01.common.constants.MessagesKeys;
import com.bachue.snr.prosnr01.common.constants.MotivoTramiteCommon;
import com.bachue.snr.prosnr01.common.constants.RepositorioCommon;
import com.bachue.snr.prosnr01.common.constants.TipoArchivoCommon;
import com.bachue.snr.prosnr01.common.constants.TipoCriterioBusquedaCommon;
import com.bachue.snr.prosnr01.common.constants.TipoDatoCommon;
import com.bachue.snr.prosnr01.common.constants.TipoDocumentalCommon;
import com.bachue.snr.prosnr01.common.constants.TipoEstadoSolicitudCommon;

import com.bachue.snr.prosnr01.dao.DaoCreator;
import com.bachue.snr.prosnr01.dao.DaoManagerFactory;
import com.bachue.snr.prosnr01.dao.acc.CriterioBusquedaDAO;
import com.bachue.snr.prosnr01.dao.acc.DetalleCriterioBusquedaDAO;
import com.bachue.snr.prosnr01.dao.acc.SolicitudDAO;
import com.bachue.snr.prosnr01.dao.bgn.ImagenesDAO;
import com.bachue.snr.prosnr01.dao.bng.AnotacionPredioDAO;
import com.bachue.snr.prosnr01.dao.col.InteresadoDocumentoTipoDAO;
import com.bachue.snr.prosnr01.dao.col.PredioTipoDAO;
import com.bachue.snr.prosnr01.dao.copias.SolicitudCopiasDAO;
import com.bachue.snr.prosnr01.dao.pgn.CampoCriterioBusquedaDAO;
import com.bachue.snr.prosnr01.dao.pgn.CirculoRegistralDao;
import com.bachue.snr.prosnr01.dao.pgn.ConstantesDAO;
import com.bachue.snr.prosnr01.dao.pgn.DepartamentoDAO;
import com.bachue.snr.prosnr01.dao.pgn.MunicipioDAO;
import com.bachue.snr.prosnr01.dao.pgn.OficinaOrigenDAO;
import com.bachue.snr.prosnr01.dao.pgn.PaisDAO;
import com.bachue.snr.prosnr01.dao.pgn.TipoCriterioBusquedaDAO;
import com.bachue.snr.prosnr01.dao.pgn.TipoDocumentoPublicoDAO;
import com.bachue.snr.prosnr01.dao.pgn.TipoOficinaDAO;
import com.bachue.snr.prosnr01.dao.pgn.VeredaDAO;
import com.bachue.snr.prosnr01.dao.png.CoordenadaDAO;
import com.bachue.snr.prosnr01.dao.png.LetraEjeDAO;
import com.bachue.snr.prosnr01.dao.png.TipoEjeDAO;
import com.bachue.snr.prosnr01.dao.util.ConsultasUtilidades;
import com.bachue.snr.prosnr01.dao.util.UtilDAO;

import com.bachue.snr.prosnr01.model.antiguoSistema.BuscarAntiguoSistema;
import com.bachue.snr.prosnr01.model.antiguoSistema.DataConsultaPorCriterio;
import com.bachue.snr.prosnr01.model.calificacion.ConsultaCriteriosCalificacionDocumento;
import com.bachue.snr.prosnr01.model.copias.DigitalizacionCopias;
import com.bachue.snr.prosnr01.model.copias.SolicitudCopias;
import com.bachue.snr.prosnr01.model.integration.OWCC.DocumentoOWCC;
import com.bachue.snr.prosnr01.model.registro.DataReproduccionConstancia;
import com.bachue.snr.prosnr01.model.registro.Registro;
import com.bachue.snr.prosnr01.model.sdb.acc.DatosAntSistema;
import com.bachue.snr.prosnr01.model.sdb.acc.DetalleAntSistemaUI;
import com.bachue.snr.prosnr01.model.sdb.acc.DocumentosSalida;
import com.bachue.snr.prosnr01.model.sdb.acc.Liquidacion;
import com.bachue.snr.prosnr01.model.sdb.acc.Persona;
import com.bachue.snr.prosnr01.model.sdb.acc.Proceso;
import com.bachue.snr.prosnr01.model.sdb.acc.Solicitud;
import com.bachue.snr.prosnr01.model.sdb.acc.Turno;
import com.bachue.snr.prosnr01.model.sdb.acc.TurnoHistoria;
import com.bachue.snr.prosnr01.model.sdb.bng.AnotacionPredio;
import com.bachue.snr.prosnr01.model.sdb.bng.AnotacionPredioDireccion;
import com.bachue.snr.prosnr01.model.sdb.bng.Documento;
import com.bachue.snr.prosnr01.model.sdb.bng.DocumentoConstancia;
import com.bachue.snr.prosnr01.model.sdb.bng.Imagenes;
import com.bachue.snr.prosnr01.model.sdb.bng.PredioRegistro;
import com.bachue.snr.prosnr01.model.sdb.col.InteresadoDocumentoTipo;
import com.bachue.snr.prosnr01.model.sdb.col.PredioTipo;
import com.bachue.snr.prosnr01.model.sdb.pgn.CamposConsulta;
import com.bachue.snr.prosnr01.model.sdb.pgn.CirculoRegistral;
import com.bachue.snr.prosnr01.model.sdb.pgn.Constantes;
import com.bachue.snr.prosnr01.model.sdb.pgn.CriteriosDeBusqueda;
import com.bachue.snr.prosnr01.model.sdb.pgn.Departamento;
import com.bachue.snr.prosnr01.model.sdb.pgn.MotivoTramite;
import com.bachue.snr.prosnr01.model.sdb.pgn.Municipio;
import com.bachue.snr.prosnr01.model.sdb.pgn.OficinaOrigen;
import com.bachue.snr.prosnr01.model.sdb.pgn.Pais;
import com.bachue.snr.prosnr01.model.sdb.pgn.TipoDocumental;
import com.bachue.snr.prosnr01.model.sdb.pgn.TipoDocumentoPublico;
import com.bachue.snr.prosnr01.model.sdb.pgn.TipoOficina;
import com.bachue.snr.prosnr01.model.sdb.pgn.Vereda;
import com.bachue.snr.prosnr01.model.sdb.png.Coordenada;
import com.bachue.snr.prosnr01.model.sdb.png.LetraEje;
import com.bachue.snr.prosnr01.model.sdb.png.TipoEje;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import java.math.BigDecimal;
import java.math.BigInteger;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;


/**
 * Maneja las transacciones y validaciones correspondientes al proceso de
 * consulta de indices.
 *
 * @author hcastaneda
 */
public class CriteriosConsultaBusiness extends BaseBusiness
{
	/** Constante clh_LOGGER. */
	private static final LoggerHandler clh_LOGGER = (LoggerHandler)com.bachue.snr.prosnr01.common.utils.LoggingHelper
			.getLogger(CriteriosConsultaBusiness.class);

	/**
	 * Metodo que se encarga de consultar el turno por id.
	 *
	 * @param as_idTurno Argumento de tipo <code>String</code> que contiene el id del turno a consultar.
	 * @return Retorna un objeto de tipo <code>Turno</code> que contiene los resultados que coincidieron con los criterios de búsqueda.
	 * @throws B2BException Señala que se ha generado una excepción.
	 */
	public synchronized Turno buscarTurnoPorId(String as_idTurno)
	    throws B2BException
	{
		Turno lt_turno;

		lt_turno = null;

		if(StringUtils.isValidString(as_idTurno))
		{
			DAOManager ldm_manager;

			ldm_manager = DaoManagerFactory.getDAOManager();

			try
			{
				lt_turno = DaoCreator.getTurnoDAO(ldm_manager).findById(as_idTurno);

				if(lt_turno == null)
					throw new B2BException(ErrorKeys.TURNO_INVALIDO);
			}
			catch(B2BException lb2be_e)
			{
				ldm_manager.setRollbackOnly();

				clh_LOGGER.error("buscarTurnoPorId", lb2be_e);

				throw lb2be_e;
			}
			finally
			{
				ldm_manager.close();
			}
		}

		return lt_turno;
	}

	/**
	 * Metodo de transacciones con la base de datos con el fin de encontrar los
	 * campos a presentar de acuerdo a los criterios de busqueda en la tabla
	 * SDB_ACC_CRITERIO_BUSQUEDA.
	 *
	 * @param as_s correspondiente al valor del tipo de objeto Solicitud
	 * @return devuelve el valor de Collection
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public synchronized Collection<CamposConsulta> camposPorCriterio(Solicitud as_s)
	    throws B2BException
	{
		Collection<CamposConsulta> lccc_camposCriterio;
		lccc_camposCriterio = null;

		if(as_s != null)
		{
			DAOManager ldm_manager;

			ldm_manager = DaoManagerFactory.getDAOManager();

			try
			{
				CriteriosDeBusqueda             lcb_cb;
				Collection<CriteriosDeBusqueda> lccb_cb;

				lcb_cb = new CriteriosDeBusqueda();
				lcb_cb.setIdSolicitud(as_s.getIdSolicitud());

				lccb_cb = DaoCreator.getCriterioBusquedaDAO(ldm_manager).buscarCriteriosPorsolicitud(lcb_cb);

				if(CollectionUtils.isValidCollection(lccb_cb))
				{
					CampoCriterioBusquedaDAO lccbd_DAO;
					TipoCriterioBusquedaDAO  ltcbd_DAO;

					lccc_camposCriterio     = new ArrayList<CamposConsulta>();
					lccbd_DAO               = DaoCreator.getCampoCriterioBusquedaDAO(ldm_manager);
					ltcbd_DAO               = DaoCreator.getTipoCriterioBusquedaDAO(ldm_manager);

					for(CriteriosDeBusqueda lcb_tmp : lccb_cb)
					{
						if(lcb_tmp != null)
						{
							String ls_tipoCriterio;

							ls_tipoCriterio = lcb_tmp.getIdTipoCriterio();

							if(StringUtils.isValidString(ls_tipoCriterio))
							{
								{
									CriteriosDeBusqueda lcdb_tipoCriterio;

									lcdb_tipoCriterio = ltcbd_DAO.findById(lcb_tmp);

									if(lcdb_tipoCriterio != null)
										lcb_tmp.setDescripcion(lcdb_tipoCriterio.getDescripcion());
								}

								{
									CamposConsulta             lcc_criterio;
									Collection<CamposConsulta> lccc_campos;

									lcc_criterio     = new CamposConsulta();
									lccc_campos      = new ArrayList<CamposConsulta>();

									lcc_criterio.setIdTipoCriterioBusqueda(ls_tipoCriterio);

									lccc_campos = lccbd_DAO.buscarCamposPorCriterio(lcc_criterio);

									if(CollectionUtils.isValidCollection(lccc_campos))
									{
										lcc_criterio.setCriteriosDeBusqueda(lcb_tmp);
										lcc_criterio.setDataCamposConsulta(lccc_campos);

										lccc_camposCriterio.add(lcc_criterio);
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

				clh_LOGGER.error("camposPorCriterio", lb2be_e);

				throw lb2be_e;
			}
			finally
			{
				ldm_manager.close();
			}
		}

		return lccc_camposCriterio;
	}

	/**
	 * Metodo encargado de cargar el campo dirección completa con la
	 * concatenación de campos digitados y seleccionados en pantalla.
	 *
	 * @param acc_panel Argumento de tipo CamposConsulta que contiene los
	 * campos a concatenar.
	 * @return Variable de tipo String que contiene la concatenación
	 * de campos digitados y seleccionados en pantalla.
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public String cargarDireccionCompleta(CamposConsulta acc_panel)
	    throws B2BException
	{
		StringBuilder lsb_direccionCompleta;

		lsb_direccionCompleta = new StringBuilder();

		if(acc_panel != null)
		{
			DAOManager ldm_manager;

			ldm_manager = DaoManagerFactory.getDAOManager();

			try
			{
				Collection<CamposConsulta> lccc_datosCamposConsulta;

				lccc_datosCamposConsulta = acc_panel.getDataCamposConsulta();

				if(CollectionUtils.isValidCollection(lccc_datosCamposConsulta))
				{
					String ls_idTipoDePredio;
					String ls_idPais;
					String ls_idDepartamento;
					String ls_idMunicipio;
					String ls_idVereda;
					String ls_nombreDelPredio;
					String ls_tipoDeEje;
					String ls_ejePrincipal;
					String ls_eje1;
					String ls_ejeSecundario;
					String ls_complementoDireccion;

					ls_idTipoDePredio           = null;
					ls_idPais                   = null;
					ls_idDepartamento           = null;
					ls_idMunicipio              = null;
					ls_idVereda                 = null;
					ls_nombreDelPredio          = null;
					ls_tipoDeEje                = null;
					ls_ejePrincipal             = null;
					ls_eje1                     = null;
					ls_ejeSecundario            = null;
					ls_complementoDireccion     = null;

					for(CamposConsulta lcc_campo : lccc_datosCamposConsulta)
					{
						if(lcc_campo != null)
						{
							String ls_idTipoCriterioBusqueda;

							ls_idTipoCriterioBusqueda = lcc_campo.getIdTipoCriterioBusqueda();

							if(StringUtils.isValidString(ls_idTipoCriterioBusqueda))
							{
								if(ls_idTipoCriterioBusqueda.equalsIgnoreCase(TipoCriterioBusquedaCommon.DIRECCION))
								{
									String ls_idCampoCriterioBusqueda;

									ls_idCampoCriterioBusqueda = lcc_campo.getIdCampoCriterioBusqueda();

									if(StringUtils.isValidString(ls_idCampoCriterioBusqueda))
									{
										if(
										    ls_idCampoCriterioBusqueda.equalsIgnoreCase(
											        CampoCriterioBusquedaCommon.DIRECCION_TIPO_DE_PREDIO
											    )
										)
											ls_idTipoDePredio = lcc_campo.getValorCampo();
										else if(
										    ls_idCampoCriterioBusqueda.equalsIgnoreCase(
											        CampoCriterioBusquedaCommon.DIRECCION_DEPARTAMENTO
											    )
										)
										{
											ls_idPais             = IdentificadoresCommon.INDICATIVO_PAIS_DEFAULT;
											ls_idDepartamento     = lcc_campo.getValorCampo();
										}
										else if(
										    ls_idCampoCriterioBusqueda.equalsIgnoreCase(
											        CampoCriterioBusquedaCommon.DIRECCION_MUNICIPIO
											    )
										)
											ls_idMunicipio = lcc_campo.getValorCampo();

										if(
										    ls_idCampoCriterioBusqueda.equalsIgnoreCase(
											        CampoCriterioBusquedaCommon.DIRECCION_VEREDA
											    )
										)
											ls_idVereda = lcc_campo.getValorCampo();

										if(
										    ls_idCampoCriterioBusqueda.equalsIgnoreCase(
											        CampoCriterioBusquedaCommon.DIRECCION_NOMBRE_DEL_PREDIO
											    )
										)
											ls_nombreDelPredio = lcc_campo.getValorCampo();

										if(
										    ls_idCampoCriterioBusqueda.equalsIgnoreCase(
											        CampoCriterioBusquedaCommon.DIRECCION_TIPO_DE_EJE_PRINCIPAL
											    )
										)
											ls_tipoDeEje = lcc_campo.getValorCampo();

										if(
										    ls_idCampoCriterioBusqueda.equalsIgnoreCase(
											        CampoCriterioBusquedaCommon.DIRECCION_EJE_PRINCIPAL
											    )
										)
											ls_ejePrincipal = lcc_campo.getValorCampo();

										if(
										    ls_idCampoCriterioBusqueda.equalsIgnoreCase(
											        CampoCriterioBusquedaCommon.DIRECCION_TIPO_EJE_SECUNDARIO
											    )
										)
											ls_eje1 = lcc_campo.getValorCampo();

										if(
										    ls_idCampoCriterioBusqueda.equalsIgnoreCase(
											        CampoCriterioBusquedaCommon.DIRECCION_EJE_SECUNDARIO
											    )
										)
											ls_ejeSecundario = lcc_campo.getValorCampo();

										if(
										    ls_idCampoCriterioBusqueda.equalsIgnoreCase(
											        CampoCriterioBusquedaCommon.DIRECCION_DESCRIPCION_COMPLEMENTO
											    )
										)
											ls_complementoDireccion = lcc_campo.getValorCampo();
									}
								}
							}
						}
					}

					if(StringUtils.isValidString(ls_idTipoDePredio))
					{
						PredioTipo lpt_predioTipo;

						lpt_predioTipo = new PredioTipo();

						lpt_predioTipo.setIdTipoPredio(ls_idTipoDePredio);

						lpt_predioTipo = DaoCreator.getPredioTipoDao(ldm_manager).findById(lpt_predioTipo);

						if(lpt_predioTipo != null)
							lsb_direccionCompleta.append(
							    StringUtils.getStringNotNull(lpt_predioTipo.getDescripcion()) + " "
							);
					}

					if(StringUtils.isValidString(ls_idPais) && StringUtils.isValidString(ls_idDepartamento))
					{
						Departamento ld_departamento;

						ld_departamento = new Departamento();

						ld_departamento.setIdPais(ls_idPais);
						ld_departamento.setIdDepartamento(ls_idDepartamento);

						ld_departamento = DaoCreator.getDepartamentoDAO(ldm_manager).findById(ld_departamento);

						if(ld_departamento != null)
							lsb_direccionCompleta.append(
							    StringUtils.getStringNotNull(ld_departamento.getNombre()) + " "
							);
					}

					if(
					    StringUtils.isValidString(ls_idPais) && StringUtils.isValidString(ls_idDepartamento)
						    && StringUtils.isValidString(ls_idMunicipio)
					)
					{
						Municipio lm_municipio;

						lm_municipio = new Municipio();

						lm_municipio.setIdPais(ls_idPais);
						lm_municipio.setIdDepartamento(ls_idDepartamento);
						lm_municipio.setIdMunicipio(ls_idMunicipio);

						lm_municipio = DaoCreator.getMunicipioDAO(ldm_manager).findById(lm_municipio);

						if(lm_municipio != null)
							lsb_direccionCompleta.append(StringUtils.getStringNotNull(lm_municipio.getNombre()) + " ");
					}

					if(
					    StringUtils.isValidString(ls_idPais) && StringUtils.isValidString(ls_idDepartamento)
						    && StringUtils.isValidString(ls_idMunicipio) && StringUtils.isValidString(ls_idVereda)
					)
					{
						Vereda lv_vereda;

						lv_vereda = new Vereda();

						lv_vereda.setIdPais(ls_idPais);
						lv_vereda.setIdDepartamento(ls_idDepartamento);
						lv_vereda.setIdMunicipio(ls_idMunicipio);
						lv_vereda.setIdVereda(ls_idVereda);

						lv_vereda = DaoCreator.getVeredaDAO(ldm_manager).findById(lv_vereda);

						if(lv_vereda != null)
							lsb_direccionCompleta.append(StringUtils.getStringNotNull(lv_vereda.getNombre()) + " ");
					}

					if(StringUtils.isValidString(ls_nombreDelPredio))
						lsb_direccionCompleta.append(StringUtils.getStringNotNull(ls_nombreDelPredio) + " ");

					if(StringUtils.isValidString(ls_tipoDeEje))
					{
						TipoEje lte_tipoEje;

						lte_tipoEje = new TipoEje();
						lte_tipoEje.setIdTipoEje(ls_tipoDeEje);

						lte_tipoEje = DaoCreator.getTipoEjeDAO(ldm_manager).findById(lte_tipoEje);

						if(lte_tipoEje != null)
							lsb_direccionCompleta.append(StringUtils.getStringNotNull(lte_tipoEje.getNombre()) + " ");
					}

					if(StringUtils.isValidString(ls_ejePrincipal))
						lsb_direccionCompleta.append(StringUtils.getStringNotNull(ls_ejePrincipal) + " ");

					if(StringUtils.isValidString(ls_eje1))
					{
						TipoEje lte_tipoEje;

						lte_tipoEje = new TipoEje();
						lte_tipoEje.setIdTipoEje(ls_eje1);

						lte_tipoEje = DaoCreator.getTipoEjeDAO(ldm_manager).findById(lte_tipoEje);

						if(lte_tipoEje != null)
							lsb_direccionCompleta.append(StringUtils.getStringNotNull(lte_tipoEje.getNombre()) + " ");
					}

					if(StringUtils.isValidString(ls_ejeSecundario))
						lsb_direccionCompleta.append(StringUtils.getStringNotNull(ls_ejeSecundario) + " ");

					if(StringUtils.isValidString(ls_complementoDireccion))
						lsb_direccionCompleta.append(StringUtils.getStringNotNull(ls_complementoDireccion) + " ");
				}
			}
			catch(B2BException lb2be_e)
			{
				ldm_manager.setRollbackOnly();

				clh_LOGGER.error("cargarDireccionCompleta", lb2be_e);

				throw lb2be_e;
			}
			finally
			{
				ldm_manager.close();
			}
		}

		return lsb_direccionCompleta.toString();
	}

	/**
	 * Retorna el valor del objeto de Collection para cargar los campos en excel
	 *
	 * @param accc_camposConsulta correspondiente al valor del tipo de objeto Collection<CamposConsulta>
	 * @param as_userID correspondiente al valor del tipo de objeto String
	 * @param as_remoteIp correspondiente al valor del tipo de objeto String
	 * @return devuelve el valor de Collection de CamposConsulta
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public synchronized Collection<CamposConsulta> cargarExcelCamposCriterios(
	    Collection<CamposConsulta> accc_camposConsulta, String as_userID, String as_remoteIp
	)
	    throws B2BException
	{
		DAOManager                 ldm_manager;
		Collection<CamposConsulta> lccc_archivosProcesados;

		ldm_manager                 = DaoManagerFactory.getDAOManager();
		lccc_archivosProcesados     = null;

		try
		{
			lccc_archivosProcesados = cargarExcelCamposCriterios(
				    ldm_manager, accc_camposConsulta, false, as_userID, as_remoteIp
				);
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("cargarExcelCamposCriterios", lb2be_e);
		}
		finally
		{
			ldm_manager.close();
		}

		return lccc_archivosProcesados;
	}

	/**
	 * Retorna el valor del objeto de Collection para cargar los campos en excel
	 *
	 * @param adm_manager Argumento de tipo <code>DAOManager</code> que permite la conexión a la base de datos.
	 * @param accc_camposConsulta correspondiente al valor del tipo de objeto Collection<CamposConsulta>
	 * @param ab_copias Argumento de tipo <code>boolean</code> que indica si es un proceso de copias <code>true</code> de la contrario <code>false</code>.
	 * @param as_userID correspondiente al valor del tipo de objeto String
	 * @param as_remoteIp correspondiente al valor del tipo de objeto String
	 * @return devuelve el valor de Collection de CamposConsulta
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public synchronized Collection<CamposConsulta> cargarExcelCamposCriterios(
	    DAOManager adm_manager, Collection<CamposConsulta> accc_camposConsulta, boolean ab_copias, String as_userID,
	    String as_remoteIp
	)
	    throws B2BException
	{
		Collection<CamposConsulta> lccc_archivosProcesados;

		lccc_archivosProcesados = new ArrayList<CamposConsulta>();

		try
		{
			if(CollectionUtils.isValidCollection(accc_camposConsulta))
			{
				for(CamposConsulta lccc_iterador : accc_camposConsulta)
				{
					if(lccc_iterador != null)
					{
						CriteriosDeBusqueda lcdb_criteriosDeBusqueda;
						boolean             lb_archivoConErrores;
						boolean             lb_errorNumeroColumnas;
						boolean             lb_archivoConRegistros;
						boolean             lb_archivoNoCoincideCantidad;
						byte[]              lba_archivoCargado;
						String              ls_idTipoCriterioBusqueda;

						lcdb_criteriosDeBusqueda         = lccc_iterador.getCriteriosDeBusqueda();
						lb_archivoConErrores             = false;
						lb_errorNumeroColumnas           = false;
						lb_archivoConRegistros           = false;
						lb_archivoNoCoincideCantidad     = false;
						lba_archivoCargado               = lccc_iterador.getArchivoCargue();
						ls_idTipoCriterioBusqueda        = lccc_iterador.getIdTipoCriterioBusqueda();

						if(lba_archivoCargado != null)
						{
							Workbook lw_libro;
							Sheet    lsh_hoja;

							{
								InputStream lis_archivoExcel;

								lis_archivoExcel     = new ByteArrayInputStream(lba_archivoCargado);

								lw_libro     = new XSSFWorkbook(lis_archivoExcel);

								lsh_hoja = lw_libro.getSheetAt(0);
							}

							if(lsh_hoja != null)
							{
								CampoCriterioBusquedaDAO   lccbd_DAO;
								TipoCriterioBusquedaDAO    ltcbd_DAO;
								DetalleCriterioBusquedaDAO ldcbd_DAO;

								Collection<CamposConsulta> lccc_datosAInsertar;
								Collection<DocumentoOWCC>  lcdo_documentosOWCC;
								int                        li_ultimaFila;
								int                        li_cantidadColumnas;
								int                        li_cantidadFilas;
								int                        li_cantidadConsultas;
								boolean                    lb_errorTitulos;

								lccbd_DAO     = DaoCreator.getCampoCriterioBusquedaDAO(adm_manager);
								ltcbd_DAO     = DaoCreator.getTipoCriterioBusquedaDAO(adm_manager);
								ldcbd_DAO     = DaoCreator.getDetalleCriterioBusquedaDAO(adm_manager);

								lccc_datosAInsertar      = new ArrayList<CamposConsulta>();
								lcdo_documentosOWCC      = new ArrayList<DocumentoOWCC>();
								li_ultimaFila            = lsh_hoja.getLastRowNum();
								li_cantidadColumnas      = 0;
								li_cantidadFilas         = 0;
								li_cantidadConsultas     = 0;
								lb_errorTitulos          = false;

								try
								{
									Collection<String> lcs_etiquetas;

									lcs_etiquetas = lccbd_DAO.getEtiquetaCampoByIdTipoCriterioBusqueda(
										    ls_idTipoCriterioBusqueda
										);

									if(CollectionUtils.isValidCollection(lcs_etiquetas))
										li_cantidadColumnas = lcs_etiquetas.size();

									ldcbd_DAO.deleteDetallesCriterio(lcdb_criteriosDeBusqueda);

									for(int li_fila = 0; (li_fila <= li_ultimaFila) && !lb_errorTitulos; li_fila++)
									{
										Row lr_filaActual;

										lr_filaActual = lsh_hoja.getRow(li_fila);

										if(ExcelUtils.isValidRow(lr_filaActual, li_cantidadColumnas))
										{
											CamposConsulta lcc_camposConsulta;
											int            li_lastCell;
											StringBuilder  lsb_mensaje;
											boolean        lb_filaTitulos;

											lcc_camposConsulta     = new CamposConsulta();
											li_lastCell            = NumericUtils.getInt(
												    lr_filaActual.getLastCellNum()
												);
											lsb_mensaje            = new StringBuilder();
											lb_filaTitulos         = li_fila == 0;

											if((lb_filaTitulos) && (li_cantidadColumnas != li_lastCell))
											{
												lb_errorNumeroColumnas = true;
												throw new B2BException(ErrorKeys.ERROR_CANTIDAD_COLUMNAS_INCORRECTA);
											}

											try
											{
												String[]                   lsa_etiquetas;
												Collection<CamposConsulta> lccc_campos;

												lsa_etiquetas     = new String[lcs_etiquetas.size()];
												lccc_campos       = new ArrayList<CamposConsulta>();
												lsa_etiquetas     = lcs_etiquetas.toArray(lsa_etiquetas);

												for(
												    int li_celda = 0;
													    (li_celda < li_cantidadColumnas) && !lb_errorTitulos;
													    li_celda++
												)
												{
													String ls_etiqueta;

													ls_etiqueta = lsa_etiquetas[li_celda];

													if(lb_filaTitulos)
													{
														String ls_titulo;

														ls_titulo = ExcelUtils.validarStringCeldaExcel(
															    lr_filaActual, li_fila + 1, li_celda, ls_etiqueta, true
															);

														if(
														    StringUtils.isValidString(ls_titulo)
															    && StringUtils.isValidString(ls_etiqueta)
														)
														{
															ls_titulo       = ls_titulo.toUpperCase();
															ls_etiqueta     = ls_etiqueta.toUpperCase();

															if(!ls_titulo.equalsIgnoreCase(ls_etiqueta))
															{
																lb_errorTitulos = true;
																throw new B2BException(
																    ErrorKeys.ERROR_TITULOS_NO_COINCIDEN_CON_ESTRUCTURA
																);
															}
														}
													}
													else
													{
														CamposConsulta lcc_campo;
														String         ls_campoCriterioBusqueda;

														lcc_campo                    = new CamposConsulta();
														ls_campoCriterioBusqueda     = StringUtils.getString(
															    li_celda + 1
															);

														lcc_campo.setIdTipoCriterioBusqueda(ls_idTipoCriterioBusqueda);
														lcc_campo.setIdCampoCriterioBusqueda(ls_campoCriterioBusqueda);

														lcc_campo = lccbd_DAO.buscarCamposPorTipoCampo(lcc_campo);

														if(lcc_campo == null)
															throw new B2BException(
															    ErrorKeys.NO_SE_ENCONTRARON_REGISTROS_PARA_TIPO_Y_CAMPO_CRITERIO
															);

														{
															String  ls_tipoCampo;
															boolean lb_date;

															ls_tipoCampo     = lcc_campo.getTipoCampo();
															lb_date          = StringUtils.isValidString(ls_tipoCampo)
																	&& ls_tipoCampo.equalsIgnoreCase(
																	    TipoDatoCommon.FECHA
																	);

															if(lb_date)
																lcc_campo.setValorCampoFecha(
																    ExcelUtils.validarDateCeldaExcel(
																        lr_filaActual, li_fila + 1, li_celda,
																        ls_etiqueta, false
																    )
																);
															else
																lcc_campo.setValorCampo(
																    ExcelUtils.validarStringCeldaExcel(
																        lr_filaActual, li_fila + 1, li_celda,
																        ls_etiqueta, false
																    )
																);
														}

														{
															boolean lb_validado;

															lb_validado = validarDetalleCriterioBusqueda(
																    lcc_campo, lccbd_DAO
																);

															if(lb_validado)
																lccc_campos.add(lcc_campo);
														}
													}
												}

												Collection<DocumentoOWCC> lcdwcc_documentos;
												lcdwcc_documentos = null;

												if(CollectionUtils.isValidCollection(lccc_campos))
												{
													lcc_camposConsulta.setDataCamposConsulta(lccc_campos);
													lcc_camposConsulta.setCriteriosDeBusqueda(lcdb_criteriosDeBusqueda);

													guardarCriteriosCampos(
													    lcc_camposConsulta, ab_copias, adm_manager, as_userID,
													    as_remoteIp
													);

													if(ab_copias)
													{
														lcdwcc_documentos = consultarDocumentosOWCCCriterios(
															    adm_manager, lccc_campos
															);

														if(CollectionUtils.isValidCollection(lcdwcc_documentos))
															lcdo_documentosOWCC.addAll(lcdwcc_documentos);
													}

													lccc_datosAInsertar.add(lcc_camposConsulta);
												}

												if(
												    (CollectionUtils.isValidCollection(lcdwcc_documentos) && ab_copias)
													    || !ab_copias
												)
												{
													if(!lb_filaTitulos)
														lsb_mensaje.append(
														    addMessage(MessagesKeys.REGISTRO_PROCESADO_CORRECTAMENTE)
														);
												}
												else if(!lb_filaTitulos && ab_copias)
													lsb_mensaje.append(
													    addMessage(
													        MessagesKeys.NO_SE_ENCONTRARON_DOCUMENTOS_ASOCIADOS_A_LA_BUSQUEDA
													    )
													);
											}
											catch(B2BException ab2be_e)
											{
												if(!lb_archivoConErrores)
													lb_archivoConErrores = true;

												lsb_mensaje.append(addMessage(ab2be_e.getMessageKey(), true));
											}

											{
												CellStyle lcs_estiloCelda;

												lcs_estiloCelda = lw_libro.createCellStyle();

												lcs_estiloCelda.setVerticalAlignment(CellStyle.VERTICAL_JUSTIFY);

												ExcelUtils.creaCelda(
												    lr_filaActual, li_cantidadColumnas, lsb_mensaje.toString(),
												    lcs_estiloCelda
												);

												lsh_hoja.autoSizeColumn(li_cantidadColumnas);
											}

											if(!lb_filaTitulos)
												li_cantidadFilas++;
										}
									}

									lb_archivoConRegistros = CollectionUtils.isValidCollection(lccc_datosAInsertar);

									if(
									    !lb_archivoConErrores && (lcdb_criteriosDeBusqueda != null) && !ab_copias
										    && lb_archivoConRegistros
									)
									{
										Collection<CriteriosDeBusqueda> lccdb_criteriosSolicitud;

										lccdb_criteriosSolicitud = DaoCreator.getCriterioBusquedaDAO(adm_manager)
												                                 .buscarCriteriosPorsolicitud(
												    lcdb_criteriosDeBusqueda
												);

										if(CollectionUtils.isValidCollection(lccdb_criteriosSolicitud))
										{
											CriteriosDeBusqueda lcdb_criterio;

											lcdb_criterio = lccdb_criteriosSolicitud.iterator().next();

											if(lcdb_criterio != null)
											{
												li_cantidadConsultas     = NumericUtils.getInt(
													    lcdb_criterio.getCantidadConsultas()
													);

												lb_archivoNoCoincideCantidad = li_cantidadConsultas != li_cantidadFilas;
											}
										}
									}

									if(lb_archivoConErrores || lb_archivoNoCoincideCantidad)
										throw new B2BException(MessagesKeys.ARCHIVO_NO_CARGADO_ERRORES);
								}
								catch(B2BException lb2be_e)
								{
									clh_LOGGER.error("camposPorCriterio", lb2be_e);
									lb_archivoConErrores = true;
								}

								{
									ByteArrayOutputStream lbaos_output;
									String                ls_mensaje;

									lbaos_output     = new ByteArrayOutputStream();
									ls_mensaje       = lb_errorNumeroColumnas
										? addMessage(ErrorKeys.ERROR_CANTIDAD_COLUMNAS_INCORRECTA, true)
										: ((lb_archivoConErrores || !lb_archivoConRegistros)
										? MessagesKeys.ARCHIVO_NO_CARGADO_ERRORES
										: MessagesKeys.ARCHIVO_CARGADO_CORRECTAMENTE);

									if(lb_archivoNoCoincideCantidad && (lcdb_criteriosDeBusqueda != null) && !ab_copias)
									{
										CriteriosDeBusqueda lcdb_tipoCriterio;

										lcdb_tipoCriterio = new CriteriosDeBusqueda();

										lcdb_tipoCriterio.setIdTipoCriterio(ls_idTipoCriterioBusqueda);

										lcdb_tipoCriterio = ltcbd_DAO.findById(lcdb_tipoCriterio);

										if(lcdb_tipoCriterio != null)
										{
											int      li_contador;
											Object[] loa_messageArgs;

											li_contador         = 0;
											loa_messageArgs     = new String[3];

											loa_messageArgs[li_contador++]     = lcdb_tipoCriterio.getDescripcion();
											loa_messageArgs[li_contador++]     = StringUtils.getString(
												    li_cantidadConsultas
												);
											loa_messageArgs[li_contador++]     = StringUtils.getString(
												    li_cantidadFilas
												);

											ls_mensaje = addMessage(
												    MessagesKeys.CANTIDAD_NO_COINCIDE_CON_DETALLE, loa_messageArgs,
												    false
												);
										}
									}

									ExcelUtils.write(lw_libro, lbaos_output);

									lccc_iterador.setArchivoCargue(
									    lb_errorNumeroColumnas ? null : lbaos_output.toByteArray()
									);
									lccc_iterador.setMensajeRespuestaCargue(ls_mensaje);
									lccc_iterador.setCargueCorrecto(!lb_errorNumeroColumnas);

									if(ab_copias)
										lccc_iterador.setDocumentosOWCC(lcdo_documentosOWCC);

									lccc_archivosProcesados.add(lccc_iterador);
								}
							}
						}
					}
				}
			}
		}
		catch(IOException lie_e)
		{
			clh_LOGGER.error("cargarExcelCamposCriterios", lie_e);

			throw new B2BException(ErrorKeys.CONTAINER_ERROR, lie_e);
		}

		if(lccc_archivosProcesados.isEmpty())
			lccc_archivosProcesados = null;

		return lccc_archivosProcesados;
	}

	/**
	 * Retorna el valor del objeto de Collection para cargar los campos en excel
	 *
	 * @param accc_camposConsulta correspondiente al valor del tipo de objeto Collection<CamposConsulta>
	 * @param ab_copias Argumento de tipo <code>boolean</code> que indica si es un proceso de copias <code>true</code> de la contrario <code>false</code>.
	 * @param as_userID correspondiente al valor del tipo de objeto String
	 * @param as_remoteIp correspondiente al valor del tipo de objeto String
	 * @return devuelve el valor de Collection de CamposConsulta
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public synchronized Collection<CamposConsulta> cargarExcelCamposCriteriosCopias(
	    Collection<CamposConsulta> accc_camposConsulta, boolean ab_copias, String as_userID, String as_remoteIp
	)
	    throws B2BException
	{
		DAOManager                 ldm_manager;
		Collection<CamposConsulta> lccc_archivosProcesados;

		ldm_manager                 = DaoManagerFactory.getDAOManager();
		lccc_archivosProcesados     = null;

		try
		{
			lccc_archivosProcesados = cargarExcelCamposCriterios(
				    ldm_manager, accc_camposConsulta, ab_copias, as_userID, as_remoteIp
				);
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("cargarExcelCamposCriteriosCopias", lb2be_e);
		}
		finally
		{
			ldm_manager.close();
		}

		return lccc_archivosProcesados;
	}

	/**
	 * Metodo encargado de cargar la información capturada en expedicón de copias para antiguo sistema en buscar antiguo sistema.
	 *
	 * @param al_idTurnoHistoria Argumento de tipo <code>Long</code> que contiene el id_turno_historia de buscar antiguo sistema.
	 * @return Objeto de <code>Long</code> que contiene los valores a cargar en buscar antiguo sistema.
	 * @throws B2BException Señala que se ha generado una excepción.
	 */
	public synchronized BuscarAntiguoSistema cargarInformacionAntiguoSistema(Long al_idTurnoHistoria)
	    throws B2BException
	{
		BuscarAntiguoSistema lbas_buscarAntiguoSistema;

		lbas_buscarAntiguoSistema = null;

		if(NumericUtils.isValidLong(al_idTurnoHistoria))
		{
			DAOManager ldm_manager;

			ldm_manager = DaoManagerFactory.getDAOManager();

			try
			{
				TurnoHistoria lth_turnoHistoria;

				lth_turnoHistoria = DaoCreator.getTurnoHistoriaDAO(ldm_manager).findById(al_idTurnoHistoria);

				if(lth_turnoHistoria != null)
				{
					String ls_idSolicitud;

					ls_idSolicitud = lth_turnoHistoria.getIdSolicitud();

					if(StringUtils.isValidString(ls_idSolicitud))
					{
						CriteriosDeBusqueda             lcb_criterioDeBusqueda;
						Collection<CriteriosDeBusqueda> lccb_criteriosDeBusqueda;

						lcb_criterioDeBusqueda = new CriteriosDeBusqueda();

						lcb_criterioDeBusqueda.setIdSolicitud(ls_idSolicitud);

						lccb_criteriosDeBusqueda = DaoCreator.getCriterioBusquedaDAO(ldm_manager)
								                                 .buscarCriteriosPorsolicitud(lcb_criterioDeBusqueda);

						if(CollectionUtils.isValidCollection(lccb_criteriosDeBusqueda))
						{
							DetalleCriterioBusquedaDAO             ldcbd_DAO;
							DetalleAntSistemaUI                    ldasu_detalleAntiguoSistema;
							ConsultaCriteriosCalificacionDocumento lcccd_consultaCriteriosDocumento;
							Documento                              ld_documento;

							ldcbd_DAO     = DaoCreator.getDetalleCriterioBusquedaDAO(ldm_manager);

							lbas_buscarAntiguoSistema            = new BuscarAntiguoSistema();
							ldasu_detalleAntiguoSistema          = new DetalleAntSistemaUI();
							lcccd_consultaCriteriosDocumento     = new ConsultaCriteriosCalificacionDocumento();
							ld_documento                         = new Documento();

							for(CriteriosDeBusqueda lcdb_iterador : lccb_criteriosDeBusqueda)
							{
								if(lcdb_iterador != null)
								{
									String ls_tipoCriterio;

									ls_tipoCriterio = lcdb_iterador.getIdTipoCriterio();

									if(StringUtils.isValidString(ls_tipoCriterio))
									{
										Collection<CriteriosDeBusqueda> lccdb_detalleCriterioBusqueda;

										lccdb_detalleCriterioBusqueda = ldcbd_DAO.consultarDetallesAgregados(
											    lcdb_iterador
											);

										if(CollectionUtils.isValidCollection(lccdb_detalleCriterioBusqueda))
										{
											for(CriteriosDeBusqueda lcdb_detalleIterador : lccdb_detalleCriterioBusqueda)
											{
												if(lcdb_detalleIterador != null)
												{
													String ls_campoCriterioBusqueda;
													String ls_valorCampo;
													Long   ll_valorCampo;

													ls_campoCriterioBusqueda     = lcdb_detalleIterador
															.getIdCampoCriterioBusqueda();
													ls_valorCampo                = lcdb_detalleIterador.getValorCampo();
													ll_valorCampo                = NumericUtils.getLongWrapper(
														    ls_valorCampo
														);

													if(StringUtils.isValidString(ls_campoCriterioBusqueda))
													{
														if(
														    ls_tipoCriterio.equalsIgnoreCase(
															        TipoCriterioBusquedaCommon.ANTIGUO_SISTEMA
															    )
														)
														{
															if(
															    ls_campoCriterioBusqueda.equalsIgnoreCase(
																        CampoCriterioBusquedaCommon.ANTIGUO_SISTEMA_LIBRO
																    )
															)
																ldasu_detalleAntiguoSistema.setIdLibroAntSistema(
																    ll_valorCampo
																);
															else if(
															    ls_campoCriterioBusqueda.equalsIgnoreCase(
																        CampoCriterioBusquedaCommon.ANTIGUO_SISTEMA_TOMO
																    )
															)
																ldasu_detalleAntiguoSistema.setTomo(ls_valorCampo);
															else if(
															    ls_campoCriterioBusqueda.equalsIgnoreCase(
																        CampoCriterioBusquedaCommon.ANTIGUO_SISTEMA_FOLIO
																    )
															)
																ldasu_detalleAntiguoSistema.setFolio(ls_valorCampo);
															else if(
															    ls_campoCriterioBusqueda.equalsIgnoreCase(
																        CampoCriterioBusquedaCommon.ANTIGUO_SISTEMA_TIPO_DE_PARTIDA
																    )
															)
																ldasu_detalleAntiguoSistema.setPartida(ls_valorCampo);
															else if(
															    ls_campoCriterioBusqueda.equalsIgnoreCase(
																        CampoCriterioBusquedaCommon.ANTIGUO_SISTEMA_NUMERO_PARTIDA
																    )
															)
																ldasu_detalleAntiguoSistema.setNumeroPartida(
																    ll_valorCampo
																);
															else if(
															    ls_campoCriterioBusqueda.equalsIgnoreCase(
																        CampoCriterioBusquedaCommon.ANTIGUO_SISTEMA_MUNICIPIO
																    )
															)
																ldasu_detalleAntiguoSistema.setIdMunicipioTomo(
																    ls_valorCampo
																);
															else if(
															    ls_campoCriterioBusqueda.equalsIgnoreCase(
																        CampoCriterioBusquedaCommon.ANTIGUO_SISTEMA_ANIO
																    )
															)
																ldasu_detalleAntiguoSistema.setAnio(ls_valorCampo);
															else if(
															    ls_campoCriterioBusqueda.equalsIgnoreCase(
																        CampoCriterioBusquedaCommon.ANTIGUO_SISTEMA_CIRCULO_REGISTRAL
																    )
															)
																ldasu_detalleAntiguoSistema.setIdCirculo(ls_valorCampo);
															else if(
															    ls_campoCriterioBusqueda.equalsIgnoreCase(
																        CampoCriterioBusquedaCommon.ANTIGUO_SISTEMA_TIPO_DE_PREDIO
																    )
															)
																ldasu_detalleAntiguoSistema.setTipoPredio(
																    ls_valorCampo
																);
														}
														else if(
														    ls_tipoCriterio.equalsIgnoreCase(
															        TipoCriterioBusquedaCommon.DOCUMENTO_ANTIGUO_SISTEMA
															    )
														)
														{
															if(
															    ls_campoCriterioBusqueda.equalsIgnoreCase(
																        CampoCriterioBusquedaCommon.DOCUMENTO_COPIAS_CIRCULO
																    )
															)
															{
																AnotacionPredio lap_anotacionPredio;

																lap_anotacionPredio = new AnotacionPredio();

																lap_anotacionPredio.setIdCirculo(ls_valorCampo);

																lcccd_consultaCriteriosDocumento.setAnotacionPredio(
																    lap_anotacionPredio
																);
															}
															else if(
															    ls_campoCriterioBusqueda.equalsIgnoreCase(
																        CampoCriterioBusquedaCommon.DOCUMENTO_COPIAS_TIPO_DOCUMENTO
																    )
															)
																ld_documento.setIdTipoDocumento(ls_valorCampo);
															else if(
															    ls_campoCriterioBusqueda.equalsIgnoreCase(
																        CampoCriterioBusquedaCommon.DOCUMENTO_COPIAS_NUMERO_DOCUMENTO
																    )
															)
																ld_documento.setNumero(ls_valorCampo);
															else if(
															    ls_campoCriterioBusqueda.equalsIgnoreCase(
																        CampoCriterioBusquedaCommon.DOCUMENTO_COPIAS_FECHA_DOCUMENTO
																    )
															)
																ld_documento.setFechaDocumento(
																    DateUtils.getDate(ls_valorCampo, "dd/MM/yyyy")
																);
															else if(
															    ls_campoCriterioBusqueda.equalsIgnoreCase(
																        CampoCriterioBusquedaCommon.DOCUMENTO_COPIAS_TIPO_DE_OFICINA
																    )
															)
																ld_documento.setIdTipoOficina(ls_valorCampo);
															else if(
															    ls_campoCriterioBusqueda.equalsIgnoreCase(
																        CampoCriterioBusquedaCommon.DOCUMENTO_COPIAS_PAIS
																    )
															)
																ld_documento.setIdPais(ls_valorCampo);
															else if(
															    ls_campoCriterioBusqueda.equalsIgnoreCase(
																        CampoCriterioBusquedaCommon.DOCUMENTO_COPIAS_DEPARTAMENTO
																    )
															)
																ld_documento.setIdDepartamento(ls_valorCampo);
															else if(
															    ls_campoCriterioBusqueda.equalsIgnoreCase(
																        CampoCriterioBusquedaCommon.DOCUMENTO_COPIAS_MUNICIPIO
																    )
															)
																ld_documento.setIdMunicipio(ls_valorCampo);
															else if(
															    ls_campoCriterioBusqueda.equalsIgnoreCase(
																        CampoCriterioBusquedaCommon.DOCUMENTO_COPIAS_OFICINA_DE_ORIGEN
																    )
															)
																ld_documento.setIdOficinaOrigen(ls_valorCampo);
														}
													}
												}
											}
										}
									}
								}
							}

							lcccd_consultaCriteriosDocumento.setDocumento(ld_documento);

							lbas_buscarAntiguoSistema.setConsultaCriteriosCalificacionDocumento(
							    lcccd_consultaCriteriosDocumento
							);
							lbas_buscarAntiguoSistema.setDetalleAntSistema(ldasu_detalleAntiguoSistema);
							lbas_buscarAntiguoSistema.setSolicitud(
							    DaoCreator.getSolicitudDAO(ldm_manager).findById(ls_idSolicitud)
							);
						}
					}
				}
			}
			catch(B2BException lb2be_e)
			{
				ldm_manager.setRollbackOnly();

				clh_LOGGER.error("cargarInformacionAntiguoSistema", lb2be_e);

				throw lb2be_e;
			}
			finally
			{
				ldm_manager.close();
			}
		}

		return lbas_buscarAntiguoSistema;
	}

	/**
	 * Metodo de transacciones con la base de datos con el fin de encontrar los campos a presentar de acuerdo a los criterios de busqueda en la tabla CRITERIO BUSQUEDA y DETALLE CRITERIO_BUSQUEDA.
	 *
	 * @param adc_parametros Argumento de tipo <code>DigitalizacionCopias</code> que contiene los criterios necesarios para realizar la consulta.
	 * @return Retorna la información cargada para digitalización de copias.
	 * @throws B2BException Señala que se ha generado una excepción
	 */
	public synchronized DigitalizacionCopias cargarInformacionDigitalizacionCopias(DigitalizacionCopias adc_parametros)
	    throws B2BException
	{
		DigitalizacionCopias ldc_digitalizacionCopias;

		ldc_digitalizacionCopias = null;

		if(adc_parametros != null)
		{
			DAOManager ldm_manager;

			ldm_manager = DaoManagerFactory.getDAOManager();

			try
			{
				Long ll_idTurnoHistoria;

				ll_idTurnoHistoria = adc_parametros.getIdTurnoHistoria();

				if(NumericUtils.isValidLong(ll_idTurnoHistoria))
				{
					TurnoHistoria lth_turnoHistoria;

					lth_turnoHistoria = DaoCreator.getTurnoHistoriaDAO(ldm_manager).findById(ll_idTurnoHistoria);

					if(lth_turnoHistoria != null)
					{
						String ls_idSolicitud;

						ls_idSolicitud = lth_turnoHistoria.getIdSolicitud();

						if(StringUtils.isValidString(ls_idSolicitud))
						{
							CriteriosDeBusqueda             lcb_criteriosDeBusqueda;
							Collection<CriteriosDeBusqueda> lccb_criterios;

							lcb_criteriosDeBusqueda = new CriteriosDeBusqueda();
							lcb_criteriosDeBusqueda.setIdSolicitud(ls_idSolicitud);

							lccb_criterios = DaoCreator.getCriterioBusquedaDAO(ldm_manager)
									                       .buscarCriteriosPorsolicitud(lcb_criteriosDeBusqueda);

							if(CollectionUtils.isValidCollection(lccb_criterios))
							{
								Collection<CamposConsulta> lcc_camposConsulta;
								CampoCriterioBusquedaDAO   lccbd_DAO;
								TipoCriterioBusquedaDAO    ltcbd_DAO;
								DetalleCriterioBusquedaDAO ldcbd_DAO;

								lcc_camposConsulta     = new ArrayList<CamposConsulta>();
								lccbd_DAO              = DaoCreator.getCampoCriterioBusquedaDAO(ldm_manager);
								ltcbd_DAO              = DaoCreator.getTipoCriterioBusquedaDAO(ldm_manager);
								ldcbd_DAO              = DaoCreator.getDetalleCriterioBusquedaDAO(ldm_manager);

								for(CriteriosDeBusqueda lcdb_iterador : lccb_criterios)
								{
									if(lcdb_iterador != null)
									{
										String ls_tipoCriterio;

										ls_tipoCriterio = lcdb_iterador.getIdTipoCriterio();

										if(StringUtils.isValidString(ls_tipoCriterio))
										{
											{
												CriteriosDeBusqueda lcdb_tipoCriterio;

												lcdb_tipoCriterio = ltcbd_DAO.findById(lcdb_iterador);

												if(lcdb_tipoCriterio != null)
													lcdb_iterador.setDescripcion(lcdb_tipoCriterio.getDescripcion());
											}

											{
												CamposConsulta             lcc_criterio;
												Collection<CamposConsulta> lccc_campos;

												lcc_criterio     = new CamposConsulta();
												lccc_campos      = new ArrayList<CamposConsulta>();

												lcc_criterio.setIdTipoCriterioBusqueda(ls_tipoCriterio);

												lccc_campos = lccbd_DAO.buscarCamposPorCriterio(lcc_criterio);

												if(CollectionUtils.isValidCollection(lccc_campos))
												{
													for(CamposConsulta lcc_iterador : lccc_campos)
													{
														if(lcc_iterador != null)
														{
															CriteriosDeBusqueda lcdb_dato;

															lcdb_iterador.setConsecutivoConsultaDetalle(1L);
															lcdb_iterador.setIdCampoCriterioBusqueda(
															    lcc_iterador.getIdCampoCriterioBusqueda()
															);

															lcdb_dato = ldcbd_DAO.buscarDetalleCampo(lcdb_iterador);

															if(lcdb_dato != null)
															{
																String ls_tipoDato;
																String ls_valorCampo;

																ls_tipoDato       = StringUtils.getStringNotNull(
																	    lcc_iterador.getTipoDato()
																	);
																ls_valorCampo     = StringUtils.getStringNotNull(
																	    lcdb_dato.getValorCampo()
																	);

																if(ls_tipoDato.equalsIgnoreCase(TipoDatoCommon.FECHA))
																	lcc_iterador.setValorCampoFecha(
																	    DateUtils.getDate(
																	        ls_valorCampo,
																	        FormatoFechaCommon.DIA_MES_ANIO
																	    )
																	);
																else
																	lcc_iterador.setValorCampo(
																	    lcdb_dato.getValorCampo()
																	);
															}
														}
													}

													lcc_criterio.setCriteriosDeBusqueda(lcdb_iterador);
													lcc_criterio.setDataCamposConsulta(lccc_campos);

													lcc_camposConsulta.add(lcc_criterio);
												}
											}
										}
									}
								}

								ldc_digitalizacionCopias = new DigitalizacionCopias();

								ldc_digitalizacionCopias.setCamposConsulta(lcc_camposConsulta);
								ldc_digitalizacionCopias.setSolicitud(
								    DaoCreator.getSolicitudDAO(ldm_manager).findById(ls_idSolicitud)
								);
							}
						}
					}
				}
			}
			catch(B2BException lb2be_e)
			{
				ldm_manager.setRollbackOnly();

				clh_LOGGER.error("cargarInformacionDigitalizacionCopias", lb2be_e);

				throw lb2be_e;
			}
			finally
			{
				ldm_manager.close();
			}
		}

		return ldc_digitalizacionCopias;
	}

	/**
	 * Metodo encargado de cargar el nir de un turno digitado por pantalla.
	 *
	 * @param acc_panel Argumento de tipo CamposConsulta que contiene los campos a concatenar.
	 * @return Variable de tipo <code>String</code> que contiene el nir de un turno digitado por pantalla.
	 * @throws B2BException Señala que se ha generado una excepción
	 */
	public String cargarNir(CamposConsulta acc_panel)
	    throws B2BException
	{
		String ls_nir;

		ls_nir = null;

		if(acc_panel != null)
		{
			DAOManager ldm_manager;

			ldm_manager = DaoManagerFactory.getDAOManager();

			try
			{
				Collection<CamposConsulta> lccc_datosCamposConsulta;

				lccc_datosCamposConsulta = acc_panel.getDataCamposConsulta();

				if(CollectionUtils.isValidCollection(lccc_datosCamposConsulta))
				{
					String ls_turno;

					ls_turno = null;

					for(CamposConsulta lcc_campo : lccc_datosCamposConsulta)
					{
						if(lcc_campo != null)
						{
							String ls_idTipoCriterioBusqueda;

							ls_idTipoCriterioBusqueda = lcc_campo.getIdTipoCriterioBusqueda();

							if(StringUtils.isValidString(ls_idTipoCriterioBusqueda))
							{
								if(ls_idTipoCriterioBusqueda.equalsIgnoreCase(TipoCriterioBusquedaCommon.TURNO))
								{
									String ls_idCampoCriterioBusqueda;

									ls_idCampoCriterioBusqueda = lcc_campo.getIdCampoCriterioBusqueda();

									if(
									    StringUtils.isValidString(ls_idCampoCriterioBusqueda)
										    && ls_idCampoCriterioBusqueda.equalsIgnoreCase(
										        CampoCriterioBusquedaCommon.TURNO_TURNO
										    )
									)
										ls_turno = lcc_campo.getValorCampo();
								}
							}
						}
					}

					if(StringUtils.isValidString(ls_turno))
					{
						Turno lt_turno;

						lt_turno = DaoCreator.getTurnoDAO(ldm_manager).findById(ls_turno);

						if(lt_turno != null)
						{
							String ls_idSolicitud;

							ls_idSolicitud = lt_turno.getIdSolicitud();

							if(StringUtils.isValidString(ls_idSolicitud))
							{
								Solicitud ls_solicitud;

								ls_solicitud = DaoCreator.getSolicitudDAO(ldm_manager).findById(ls_idSolicitud);

								if(ls_solicitud != null)
									ls_nir = ls_solicitud.getNir();
							}
						}
					}
				}
			}
			catch(B2BException lb2be_e)
			{
				ldm_manager.setRollbackOnly();

				clh_LOGGER.error("cargarNir", lb2be_e);

				throw lb2be_e;
			}
			finally
			{
				ldm_manager.close();
			}
		}

		return ls_nir;
	}

	/**
	 * Metodo encargado de consultar los detalles agregados desde pantalla de
	 * consultas.
	 *
	 * @param acdb_criteriosDeBusqueda            Argumento de tipo CriteriosDeBusqueda que contiene los criterios
	 *            para consultar.
	 * @return Colección de tipo CriteriosDeBusqueda que contiene los resultados que
	 *         coinciden con los criterios de búsqueda.
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see Collection
	 */
	public synchronized Collection<CriteriosDeBusqueda> consultarDetallesAgregados(
	    CriteriosDeBusqueda acdb_criteriosDeBusqueda
	)
	    throws B2BException
	{
		Collection<CriteriosDeBusqueda> lcdb_detallesAgregados;

		lcdb_detallesAgregados = null;

		if(acdb_criteriosDeBusqueda != null)
		{
			DAOManager ldm_manager;

			ldm_manager = DaoManagerFactory.getDAOManager();

			try
			{
				Collection<CriteriosDeBusqueda> lcdb_detallesConsultados;

				lcdb_detallesConsultados = DaoCreator.getDetalleCriterioBusquedaDAO(ldm_manager)
						                                 .consultarDetallesAgregados(acdb_criteriosDeBusqueda);

				if(CollectionUtils.isValidCollection(lcdb_detallesConsultados))
				{
					CampoCriterioBusquedaDAO lccbd_DAO;

					String                   ls_idPais;
					String                   ls_idDepartamento;
					String                   ls_idMunicipio;

					lccbd_DAO     = DaoCreator.getCampoCriterioBusquedaDAO(ldm_manager);

					ls_idPais             = null;
					ls_idDepartamento     = null;
					ls_idMunicipio        = null;

					lcdb_detallesAgregados = new ArrayList<CriteriosDeBusqueda>();

					for(CriteriosDeBusqueda lcb_iterador : lcdb_detallesConsultados)
					{
						if(lcb_iterador != null)
						{
							String ls_idTipoCriterioBusqueda;
							String ls_idCampoCriterioBusqueda;
							String ls_valorCampo;

							ls_idTipoCriterioBusqueda      = lcb_iterador.getIdTipoCriterio();
							ls_idCampoCriterioBusqueda     = lcb_iterador.getIdCampoCriterioBusqueda();
							ls_valorCampo                  = lcb_iterador.getValorCampo();

							if(
							    StringUtils.isValidString(ls_idTipoCriterioBusqueda)
								    && StringUtils.isValidString(ls_idCampoCriterioBusqueda)
							)
							{
								CamposConsulta lcc_camposConsulta;

								lcc_camposConsulta = new CamposConsulta();

								lcc_camposConsulta.setIdTipoCriterioBusqueda(ls_idTipoCriterioBusqueda);
								lcc_camposConsulta.setIdCampoCriterioBusqueda(ls_idCampoCriterioBusqueda);

								lcc_camposConsulta = lccbd_DAO.buscarCamposPorTipoCampo(lcc_camposConsulta);

								if(lcc_camposConsulta != null)
								{
									String[] lsa_datos;

									lsa_datos = validarCampoCriterioBusqueda(
										    ldm_manager, ls_idTipoCriterioBusqueda, ls_idCampoCriterioBusqueda,
										    ls_valorCampo, ls_idPais, ls_idDepartamento, ls_idMunicipio, false
										);

									if(lsa_datos != null)
									{
										if(
										    ls_idTipoCriterioBusqueda.equalsIgnoreCase(
											        TipoCriterioBusquedaCommon.DOCUMENTO
											    )
										)
										{
											if(
											    ls_idCampoCriterioBusqueda.equalsIgnoreCase(
												        CampoCriterioBusquedaCommon.DOCUMENTO_PAIS
												    )
											)
												ls_idPais = lsa_datos[0];
											else if(
											    ls_idCampoCriterioBusqueda.equalsIgnoreCase(
												        CampoCriterioBusquedaCommon.DOCUMENTO_DEPARTAMENTO
												    )
											)
												ls_idDepartamento = lsa_datos[1];
											else if(
											    ls_idCampoCriterioBusqueda.equalsIgnoreCase(
												        CampoCriterioBusquedaCommon.DOCUMENTO_MUNICIPIO
												    )
											)
												ls_idMunicipio = lsa_datos[2];
										}

										if(
										    ls_idTipoCriterioBusqueda.equalsIgnoreCase(
											        TipoCriterioBusquedaCommon.DIRECCION
											    )
										)
										{
											if(
											    ls_idCampoCriterioBusqueda.equalsIgnoreCase(
												        CampoCriterioBusquedaCommon.DIRECCION_DEPARTAMENTO
												    )
											)
											{
												ls_idPais             = IdentificadoresCommon.INDICATIVO_PAIS_DEFAULT;
												ls_idDepartamento     = lsa_datos[1];
											}
											else if(
											    ls_idCampoCriterioBusqueda.equalsIgnoreCase(
												        CampoCriterioBusquedaCommon.DIRECCION_MUNICIPIO
												    )
											)
												ls_idMunicipio = lsa_datos[2];
										}

										ls_valorCampo = lsa_datos[3];
									}

									lcb_iterador.setEtiquetaCampo(lcc_camposConsulta.getEtiquetaCampo());
									lcb_iterador.setValorCampo(ls_valorCampo);

									lcdb_detallesAgregados.add(lcb_iterador);
								}
							}
						}
					}
				}
			}
			catch(B2BException lb2be_e)
			{
				ldm_manager.setRollbackOnly();

				clh_LOGGER.error("consultarDetallesAgregados", lb2be_e);

				throw lb2be_e;
			}
			finally
			{
				ldm_manager.close();
			}
		}

		return lcdb_detallesAgregados;
	}

	/**
	 * Metodo encargado de consultar todos los documentos del OWCC de los criterios enviados como argumento.
	 *
	 * @param acc_camposConsulta Argumento de tipo <code>Collection</code> que contiene los criterios para realizar la búsqueda.
	 * @return <code>Collection</code> de <code>DocumentoOWCC</code> con todos los documentos consultados.
	 * @throws B2BException Señala que se ha generado una excepción
	 */
	public Collection<DocumentoOWCC> consultarDocumentosOWCCCriterios(
	    DAOManager adm_manager, Collection<CamposConsulta> acc_camposConsulta
	)
	    throws B2BException
	{
		Collection<DocumentoOWCC> lcdo_documentosOWCC;

		lcdo_documentosOWCC = null;

		try
		{
			if(CollectionUtils.isValidCollection(acc_camposConsulta))
			{
				DocumentoOWCC ldo_documentoOWCC;

				ldo_documentoOWCC = extraerCriteriosDocumentoOWCC(adm_manager, acc_camposConsulta);

				if(ldo_documentoOWCC != null)
					lcdo_documentosOWCC = consultarDocumentosOWCC(
						    adm_manager, ldo_documentoOWCC, RepositorioCommon.FINAL, false
						);
			}
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("consultarDocumentosOWCCCriterios", lb2be_e);

			throw lb2be_e;
		}

		return lcdo_documentosOWCC;
	}

	/**
	 * Metodo encargado de consultar documentos del OWCC para antiguo sistema.
	 *
	 * @param abas_parametros Argumento de tipo <code>BuscarAntiguoSistema</code> que contiene los criterios a consultar.
	 * @throws B2BException Señala que se ha generado una excepción
	 */
	public synchronized Collection<DocumentoOWCC> consultarOWCCAntiguoSistemaCopias(
	    BuscarAntiguoSistema abas_parametros
	)
	    throws B2BException
	{
		DAOManager                ldm_manager;
		Collection<DocumentoOWCC> lcdw_documentosOWCC;

		ldm_manager             = DaoManagerFactory.getDAOManager();
		lcdw_documentosOWCC     = null;

		try
		{
			if(abas_parametros != null)
			{
				DocumentoOWCC ldo_documentoOWCC;
				boolean       lb_encontrada;
				String        ls_idTipoCriterioBusqueda;

				ldo_documentoOWCC             = new DocumentoOWCC();
				lb_encontrada                 = false;
				ls_idTipoCriterioBusqueda     = StringUtils.getStringNotNull(
					    abas_parametros.getIdTipoCriterioBusqueda()
					);

				if(ls_idTipoCriterioBusqueda.equalsIgnoreCase(TipoCriterioBusquedaCommon.ANTIGUO_SISTEMA))
				{
					Collection<DatosAntSistema> lcdas_matriculas;

					lcdas_matriculas = abas_parametros.getMatriculas();

					if(CollectionUtils.isValidCollection(lcdas_matriculas))
					{
						Iterator<DatosAntSistema> lidas_iterator;

						lidas_iterator = lcdas_matriculas.iterator();

						while(lidas_iterator.hasNext() && !lb_encontrada)
						{
							DatosAntSistema ldas_matricula;

							ldas_matricula = lidas_iterator.next();

							if(ldas_matricula != null)
							{
								lb_encontrada = ldas_matricula.isMatriculaSeleccionada();

								if(lb_encontrada)
								{
									String ls_idCirculo;

									ls_idCirculo = ldas_matricula.getIdCirculo();

									if(StringUtils.isValidString(ls_idCirculo))
									{
										ldo_documentoOWCC.setIdOrip(ls_idCirculo);
										ldo_documentoOWCC.setMatriculas(
										    ls_idCirculo + IdentificadoresCommon.SIMBOLO_GUION
										    + ldas_matricula.getIdMatricula()
										);
									}
								}
							}
						}
					}
				}
				else if(
				    ls_idTipoCriterioBusqueda.equalsIgnoreCase(TipoCriterioBusquedaCommon.DOCUMENTO_ANTIGUO_SISTEMA)
				)
				{
					Collection<DataConsultaPorCriterio> lcdcpc_documentos;

					lcdcpc_documentos = abas_parametros.getDataConsultaPorDocumento();

					if(CollectionUtils.isValidCollection(lcdcpc_documentos))
					{
						Iterator<DataConsultaPorCriterio> lidcpc_iterator;

						lidcpc_iterator = lcdcpc_documentos.iterator();

						while(lidcpc_iterator.hasNext() && !lb_encontrada)
						{
							DataConsultaPorCriterio ldcpc_documento;

							ldcpc_documento = lidcpc_iterator.next();

							if(ldcpc_documento != null)
							{
								lb_encontrada = ldcpc_documento.isSeleccione();

								if(lb_encontrada)
								{
									String ls_idCirculo;

									ls_idCirculo = ldcpc_documento.getIdCirculo();

									if(StringUtils.isValidString(ls_idCirculo))
									{
										ldo_documentoOWCC.setIdOrip(ls_idCirculo);
										ldo_documentoOWCC.setMatriculas(
										    ls_idCirculo + IdentificadoresCommon.SIMBOLO_GUION
										    + ldcpc_documento.getIdMatricula()
										);
									}
								}
							}
						}
					}
				}

				if(lb_encontrada)
					lcdw_documentosOWCC = consultarDocumentosOWCC(
						    ldm_manager, ldo_documentoOWCC, RepositorioCommon.FINAL, false
						);
				else
					throw new B2BException(ErrorKeys.DEBE_SELECCIONAR_AL_MENOS_UNA_MATRICULA);
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("consultarOWCCAntiguoSistemaCopias", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lcdw_documentosOWCC;
	}

	/**
	 * Metodo encargado de crear etapa de digitalización y construir url de capture.
	 *
	 * @param abas_parametros Argumento de tipo <code>BuscarAntiguoSistema</code> que contiene los criterios a consultar.
	 * @param as_userId Argumento de tipo <code>String</code> que contiene el id del usuario que realiza la operación.
	 * @param as_remoteIp Argumento de tipo <code>String</code> que contiene la ip del usuario que realiza la operación.
	 *
	 * @throws B2BException Señala que se ha generado una excepción
	 */
	public synchronized String crearYObtenerUrlDigitalizacion(
	    BuscarAntiguoSistema abas_parametros, String as_userId, String as_remoteIp
	)
	    throws B2BException
	{
		DAOManager ldm_manager;
		String     ls_urlCapture;

		ldm_manager       = DaoManagerFactory.getDAOManager();
		ls_urlCapture     = null;

		try
		{
			if(abas_parametros != null)
			{
				Solicitud ls_solicitud;

				ls_solicitud = abas_parametros.getSolicitud();

				if(ls_solicitud != null)
				{
					String ls_nir;
					String ls_idTurno;

					ls_nir         = ls_solicitud.getNir();
					ls_idTurno     = abas_parametros.getIdTurno();

					if(StringUtils.isValidString(ls_nir) && StringUtils.isValidString(ls_idTurno))
					{
						boolean lb_etapaDigitalizacion;

						lb_etapaDigitalizacion = DaoCreator.getTurnoHistoriaDAO(ldm_manager)
								                               .tieneEtapa(
								    ls_idTurno, EtapaCommon.ID_ETAPA_DIGITALIZACION_CORE_BACHUE
								);

						if(lb_etapaDigitalizacion)
							throw new B2BException(ErrorKeys.ERROR_YA_EXISTE_ETAPA_DIGITALIZACION);

						{
							TurnoHistoria lth_turnoHistoria;
							MotivoTramite lmt_motivoTramite;

							lth_turnoHistoria     = new TurnoHistoria();
							lmt_motivoTramite     = new MotivoTramite();

							lth_turnoHistoria.setIdTurnoHistoria(abas_parametros.getIdTurnoHistoria());
							lmt_motivoTramite.setIdEtapaOrigen(EtapaCommon.ID_ETAPA_ANALISTA_DE_COPIAS);
							lmt_motivoTramite.setIdMotivo(MotivoTramiteCommon.ETAPA_350_DIGITALIZACION);

							terminarTurnoHistoriaYCrearEtapa(
							    lth_turnoHistoria, ldm_manager, lmt_motivoTramite, as_userId, as_remoteIp,
							    EstadoCommon.ASIGNADA
							);

							ls_urlCapture = getDigitalizacionBusiness()
									                .construirUrlCapture(ls_nir, ls_idTurno, ldm_manager);
						}
					}
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("crearYObtenerUrlDigitalizacion", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return ls_urlCapture;
	}

	/**
	 * Metodo encargado de eliminar detalles de criterios según criterios únicos.
	 *
	 * @param acdb_criteriosDeBusqueda            Argumento de tipo CriteriosDeBusqueda que contiene los criterios
	 *            necesarios para realizar la eliminación del detalle.
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public synchronized void deleteDetalle(CriteriosDeBusqueda acdb_criteriosDeBusqueda)
	    throws B2BException
	{
		if(acdb_criteriosDeBusqueda != null)
		{
			DAOManager ldm_manager;

			ldm_manager = DaoManagerFactory.getDAOManager();

			try
			{
				DaoCreator.getDetalleCriterioBusquedaDAO(ldm_manager).deleteDetalle(acdb_criteriosDeBusqueda);
			}
			catch(B2BException lb2be_e)
			{
				ldm_manager.setRollbackOnly();

				clh_LOGGER.error("deleteDetalle", lb2be_e);

				throw lb2be_e;
			}
			finally
			{
				ldm_manager.close();
			}
		}
	}

	/**
	 * Genera pdf resultado de consultas.
	 *
	 * @param as_idSolicitud Objeto contenedor de la información que debe ir en el PDF
	 * @param adm_manager Objeto para manipular la conexión con la base de datos
	 * @param as_userId correspondiente al valor del tipo de objeto String
	 * @return devuelve el valor de byte[]
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public byte[] generarPDFConsultasCriterios(String as_idSolicitud, DAOManager adm_manager, String as_userId)
	    throws B2BException
	{
		byte[] lba_pdf;

		lba_pdf = null;

		try
		{
			byte[]        lba_plantilla;
			Constantes    lc_plantilla;
			String        ls_plantilla;
			ConstantesDAO lc_DAO;

			lc_DAO           = DaoCreator.getConstantesDAO(adm_manager);
			lc_plantilla     = lc_DAO.findImagen(new Constantes(ConstanteCommon.PLANTILLA_RESUMEN_SOLICITUD_CONSULTAS));

			if(StringUtils.isValidString(as_idSolicitud) && (lc_plantilla != null))
			{
				String ls_idSolicitud;

				ls_idSolicitud     = as_idSolicitud;
				lba_plantilla      = lc_plantilla.getImagenes();

				if((lba_plantilla != null) && StringUtils.isValidString(ls_idSolicitud))
				{
					ls_plantilla = new String(lba_plantilla);

					if(StringUtils.isValidString(ls_plantilla) && StringUtils.isValidString(ls_idSolicitud))
					{
						String                          ls_tag;
						CriteriosDeBusqueda             lcdb_criteriosDeBusqueda;
						Collection<CriteriosDeBusqueda> lccb_criteriosBusquedaCollection;
						CriterioBusquedaDAO             lDAO_criterioBusquedaDAO;
						CampoCriterioBusquedaDAO        lDAO_campoCriterioBusquedaDAO;
						DetalleCriterioBusquedaDAO      lDAO_detalleCriterioBusquedaDAO;
						TipoCriterioBusquedaDAO         lDAO_tipoCriterioBusquedaDAO;
						PredioTipoDAO                   lDAO_predioTipoDAO;
						TipoEjeDAO                      lDAO_tipoEjeDAO;
						CirculoRegistralDao             lDAO_circuloRegistralDAO;
						InteresadoDocumentoTipoDAO      lDAO_interesadoDocumentoTipoDAO;
						LinkedHashMap<Integer, Object>  lhmpCriterios;
						UtilDAO                         lud_utilDao;
						MunicipioDAO                    lDAO_municipioDAO;
						PaisDAO                         lDAO_paisDAO;
						TipoOficinaDAO                  lDAO_tipoOficinaDAO;
						TipoDocumentoPublicoDAO         lDAO_tipoDocumentopublicoTipoDAO;
						DepartamentoDAO                 lDAO_departamentoDAO;
						List<Map<String, Object>>       llmso_datos;

						ls_tag                               = null;
						lDAO_criterioBusquedaDAO             = DaoCreator.getCriterioBusquedaDAO(adm_manager);
						lDAO_campoCriterioBusquedaDAO        = DaoCreator.getCampoCriterioBusquedaDAO(adm_manager);
						lDAO_detalleCriterioBusquedaDAO      = DaoCreator.getDetalleCriterioBusquedaDAO(adm_manager);
						lDAO_tipoCriterioBusquedaDAO         = DaoCreator.getTipoCriterioBusquedaDAO(adm_manager);
						lDAO_predioTipoDAO                   = DaoCreator.getPredioTipoDao(adm_manager);
						lDAO_tipoEjeDAO                      = DaoCreator.getTipoEjeDAO(adm_manager);
						lDAO_circuloRegistralDAO             = DaoCreator.getCirculoRegistralDAO(adm_manager);
						lDAO_interesadoDocumentoTipoDAO      = DaoCreator.getInteresadoDocumentoTipoDAO(adm_manager);
						lud_utilDao                          = DaoCreator.getUtilDAO(adm_manager);
						lDAO_municipioDAO                    = DaoCreator.getMunicipioDAO(adm_manager);
						lDAO_paisDAO                         = DaoCreator.getPaisDAO(adm_manager);
						lDAO_tipoOficinaDAO                  = DaoCreator.getTipoOficinaDAO(adm_manager);
						lDAO_tipoDocumentopublicoTipoDAO     = DaoCreator.getTipoDocumentoPublicoDAO(adm_manager);
						lDAO_departamentoDAO                 = DaoCreator.getDepartamentoDAO(adm_manager);
						lcdb_criteriosDeBusqueda             = new CriteriosDeBusqueda();
						lccb_criteriosBusquedaCollection     = new ArrayList<CriteriosDeBusqueda>();

						lcdb_criteriosDeBusqueda.setIdSolicitud(ls_idSolicitud);

						lccb_criteriosBusquedaCollection     = lDAO_criterioBusquedaDAO.buscarCriteriosPorsolicitud(
							    lcdb_criteriosDeBusqueda
							);

						lhmpCriterios = new LinkedHashMap<Integer, Object>();
						lhmpCriterios.put(new Integer(1), as_idSolicitud);
						llmso_datos = lud_utilDao.getCustonQuery(
							    ConsultasUtilidades.CS_CONSULTA_NIR_SOLICITUD, lhmpCriterios
							);

						if(CollectionUtils.isValidCollection(llmso_datos))
						{
							Iterator<Map<String, Object>> li_consultaNir;

							li_consultaNir = llmso_datos.iterator();

							if(li_consultaNir.hasNext())
							{
								Map<String, Object> llhm_actual;

								llhm_actual = li_consultaNir.next();

								if(llhm_actual != null)
								{
									{
										String ls_nir;

										ls_nir     = llhm_actual.get(IdentificadoresCommon.NIR).toString();

										ls_tag = "<TAG_NIR>";

										if(ls_plantilla.contains(ls_tag))
										{
											if(StringUtils.isValidString(ls_nir))
												ls_plantilla = ls_plantilla.replace(ls_tag, ls_nir);
										}
									}

									{
										String ls_fechaCreacion;
										String ls_formato;

										ls_fechaCreacion     = null;
										ls_formato           = FormatoFechaCommon.DIA_MES_ANIO;

										Object lo_fechaCreacion;

										lo_fechaCreacion     = llhm_actual.get("FECHA_CREACION");

										if(lo_fechaCreacion != null)
											ls_fechaCreacion = StringUtils.getString(
												    (Date)lo_fechaCreacion, ls_formato
												);

										ls_tag = "<TAG_FECHA_CREACION>";

										if(ls_plantilla.contains(ls_tag))
										{
											if(StringUtils.isValidString(ls_fechaCreacion))
												ls_plantilla = ls_plantilla.replace(ls_tag, ls_fechaCreacion);
										}
									}

									{
										String    ls_nombreProceso;
										Proceso   lp_p;
										Solicitud ls_s;

										ls_s = new Solicitud();

										ls_s.setIdSolicitud(as_idSolicitud);

										ls_s = DaoCreator.getSolicitudDAO(adm_manager).findById(ls_s);

										if(ls_s != null)
										{
											lp_p = new Proceso();
											lp_p.setIdProceso(ls_s.getIdProceso());

											lp_p = DaoCreator.getProcesoDAO(adm_manager).findById(lp_p);

											if(lp_p != null)
											{
												ls_nombreProceso     = lp_p.getNombre();

												ls_tag = "<TAG_PROCESO>";

												if(ls_plantilla.contains(ls_tag))
												{
													if(StringUtils.isValidString(ls_nombreProceso))
														ls_plantilla = ls_plantilla.replace(ls_tag, ls_nombreProceso);
												}
											}
										}
									}
								}
							}
						}

						{
							Solicitud ls_solicitudTMP;

							ls_solicitudTMP = DaoCreator.getSolicitudDAO(adm_manager).findById(ls_idSolicitud);

							if(ls_solicitudTMP != null)
							{
								Persona lp_persona;

								lp_persona = DaoCreator.getPersonaDAO(adm_manager)
										                   .findById(ls_solicitudTMP.getIdPersona());

								if(lp_persona != null)
								{
									String ls_nombre;
									String ls_numeroDocumento;

									ls_nombre              = lp_persona.getNombreCompleto();
									ls_numeroDocumento     = StringUtils.getStringNotNull(
										    lp_persona.getNumeroDocumento()
										);
									ls_tag                 = "<TAG_NOMBRE>";

									if(ls_plantilla.contains(ls_tag))
									{
										if(StringUtils.isValidString(ls_nombre))
											ls_plantilla = ls_plantilla.replace(ls_tag, ls_nombre);
									}

									ls_tag = "<TAG_NUMERO_DOCUMENTO>";

									if(ls_plantilla.contains(ls_tag))
									{
										if(StringUtils.isValidString(ls_numeroDocumento))
											ls_plantilla = ls_plantilla.replace(ls_tag, ls_numeroDocumento);
									}
								}
							}
						}

						{
							ls_tag = "<TAG_TABLAS_CONSULTAS>";

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

									ls_tagNew     = "{\\*\\bkmkstart TAG_TABLAS_CONSULTAS}{\\*\\bkmkend TAG_TABLAS_CONSULTAS} \\line "
										+ ls_tag;

									ls_plantilla = ls_textoMitadSuperior + IdentificadoresCommon.ESPACIO_VACIO
										+ ls_tagNew + IdentificadoresCommon.ESPACIO_VACIO + ls_textoMitadInferior;
								}
							}

							ls_plantilla = limpiarTags(ls_plantilla);
						}

						if(CollectionUtils.isValidCollection(lccb_criteriosBusquedaCollection))
						{
							ByteArrayInputStream      lbais_docInStream;
							ByteArrayOutputStream     lbaos_docOutStream;
							com.aspose.words.Document ld_doc;
							DocumentBuilder           ldb_builder;
							int                       li_tamanoLetra;
							long                      ll_porcentajetablas;

							lbais_docInStream       = new ByteArrayInputStream(ls_plantilla.getBytes());
							lbaos_docOutStream      = new ByteArrayOutputStream();
							ld_doc                  = new com.aspose.words.Document(lbais_docInStream);
							ldb_builder             = new DocumentBuilder(ld_doc);
							ll_porcentajetablas     = (long)10.0;
							li_tamanoLetra          = 8;

							ldb_builder.moveToBookmark("TAG_TABLAS_CONSULTAS");

							for(CriteriosDeBusqueda lcb_temp : lccb_criteriosBusquedaCollection)
							{
								if(lcb_temp != null)
								{
									String ls_idTipoCriterioBusqueda;
									Table  lt_table;

									ls_idTipoCriterioBusqueda     = lcb_temp.getIdTipoCriterio();
									lt_table                      = ldb_builder.startTable();

									if(StringUtils.isValidString(ls_idTipoCriterioBusqueda))
									{
										CamposConsulta      lcc_camposConsulta;
										Collection<String>  lcs_titulosColumnas;
										String              ls_tituloTabla;
										long                li_intentos;
										CriteriosDeBusqueda lcb_criteriosDeBusqueda;

										ls_tituloTabla          = null;
										lcc_camposConsulta      = new CamposConsulta();
										lcs_titulosColumnas     = new ArrayList<String>();

										lcc_camposConsulta.setIdTipoCriterioBusqueda(ls_idTipoCriterioBusqueda);
										lcb_temp.setIdTipoCriterio(ls_idTipoCriterioBusqueda);

										lcc_camposConsulta      = lDAO_campoCriterioBusquedaDAO
												.buscarTamanoDeTablaPorIdTipoCriterioBusqueda(lcc_camposConsulta);
										lcs_titulosColumnas     = lDAO_campoCriterioBusquedaDAO
												.getEtiquetaCampoByIdTipoCriterioBusqueda(ls_idTipoCriterioBusqueda);

										if((lcc_camposConsulta != null) && (lcc_camposConsulta.getTamanoDeTabla() < 0))
											throw new B2BException(ErrorKeys.DETALLE_CRITERIO_INVALIDO);

										li_intentos     = lDAO_detalleCriterioBusquedaDAO
												.findMaxConsecutivoConsultaBySolicitud(lcb_temp);

										lcb_criteriosDeBusqueda = lDAO_tipoCriterioBusquedaDAO.findById(lcb_temp);

										if(lcb_criteriosDeBusqueda != null)
										{
											if(StringUtils.isValidString(lcb_criteriosDeBusqueda.getDescripcion()))
												ls_tituloTabla = lcb_criteriosDeBusqueda.getDescripcion();
										}

										for(int li_i = 1; li_i <= li_intentos; li_i++)
										{
											Collection<Collection<CriteriosDeBusqueda>> lcccdb_coleccionMasivos;
											LinkedHashMap<String, String>               lhmss_mss;
											long                                        ll_maxConsecutivoCantidadReg;

											lcccdb_coleccionMasivos     = new ArrayList<Collection<CriteriosDeBusqueda>>();
											lhmss_mss                   = new LinkedHashMap<String, String>();

											ll_maxConsecutivoCantidadReg = lDAO_detalleCriterioBusquedaDAO
													.findMaxConsecutivoConsultaBySolicitud(lcb_temp);

											//Seccion títulos
											if(CollectionUtils.isValidCollection(lcs_titulosColumnas))
											{
												for(String ls_iterador : lcs_titulosColumnas)
												{
													if(StringUtils.isValidString(ls_iterador))
														lhmss_mss.put(ls_iterador, null);
												}
											}

											//Seccion contenido
											if(ll_maxConsecutivoCantidadReg > 0)
											{
												Collection<CriteriosDeBusqueda> lccb_ccb;

												lccb_ccb = new ArrayList<CriteriosDeBusqueda>();

												for(int i = 1; i <= ll_maxConsecutivoCantidadReg; i++)
												{
													lccb_ccb = lDAO_tipoCriterioBusquedaDAO.findInfoTabs(lcb_temp, i);

													if(CollectionUtils.isValidCollection(lccb_ccb))
														lcccdb_coleccionMasivos.add(lccb_ccb);
												}

												if(CollectionUtils.isValidCollection(lcccdb_coleccionMasivos))
												{
													Collection<Map<String, String>> lclhmss_coleccionDataFinal;

													lclhmss_coleccionDataFinal = new ArrayList<Map<String, String>>();

													for(Collection<CriteriosDeBusqueda> lccb_registroSelected : lcccdb_coleccionMasivos)
													{
														if(lccb_registroSelected != null)
														{
															String              ls_idPais;
															String              ls_idDepartamento;
															Map<String, String> lmss_tmp;

															ls_idPais             = null;
															ls_idDepartamento     = null;
															lmss_tmp              = new LinkedHashMap<String, String>(
																    lhmss_mss
																);

															for(CriteriosDeBusqueda lcb_detalle : lccb_registroSelected)
															{
																if(lcb_detalle != null)
																{
																	String ls_encabezadoColumna;
																	String ls_valorCampo;

																	ls_encabezadoColumna     = lcb_detalle
																			.getEtiquetaCampo();
																	ls_valorCampo            = lcb_detalle.getValorCampo();

																	if(
																	    StringUtils.isValidString(ls_encabezadoColumna)
																		    && StringUtils.isValidString(ls_valorCampo)
																	)
																	{
																		if(
																		    ls_idTipoCriterioBusqueda.equalsIgnoreCase(
																			        "1"
																			    )
																		)
																		{
																			if(
																			    ls_encabezadoColumna.equalsIgnoreCase(
																				        "TIPO DE DOCUMENTO"
																				    )
																			)
																			{
																				InteresadoDocumentoTipo lidt_tipoDeDocumento;

																				lidt_tipoDeDocumento = new InteresadoDocumentoTipo();

																				lidt_tipoDeDocumento.setIdDocumentoTipo(
																				    ls_valorCampo
																				);

																				lidt_tipoDeDocumento = lDAO_interesadoDocumentoTipoDAO
																						.findById(lidt_tipoDeDocumento);

																				if(lidt_tipoDeDocumento != null)
																					ls_valorCampo = lidt_tipoDeDocumento
																							.getDescripcion();
																			}
																		}
																		else if(
																		    ls_idTipoCriterioBusqueda.equalsIgnoreCase(
																			        "2"
																			    )
																		)
																		{
																			if(
																			    ls_encabezadoColumna.equalsIgnoreCase(
																				        "TIPO DE DOCUMENTO"
																				    )
																			)
																			{
																				TipoDocumentoPublico ltdp_tipoDocumentoPublico;

																				ltdp_tipoDocumentoPublico = new TipoDocumentoPublico();

																				ltdp_tipoDocumentoPublico
																					.setIdTipoDocumento(ls_valorCampo);

																				ltdp_tipoDocumentoPublico = lDAO_tipoDocumentopublicoTipoDAO
																						.findById(
																						    ltdp_tipoDocumentoPublico
																						);

																				if(ltdp_tipoDocumentoPublico != null)
																					ls_valorCampo = ltdp_tipoDocumentoPublico
																							.getNombre();
																			}
																			else if(
																			    ls_encabezadoColumna.equalsIgnoreCase(
																				        "TIPO DE OFICINA"
																				    )
																			)
																			{
																				TipoOficina ltp_tipoOficina;

																				ltp_tipoOficina = new TipoOficina();

																				ltp_tipoOficina.setIdTipoOficina(
																				    ls_valorCampo
																				);

																				ltp_tipoOficina = lDAO_tipoOficinaDAO
																						.findById(ltp_tipoOficina);

																				if(ltp_tipoOficina != null)
																					ls_valorCampo = ltp_tipoOficina
																							.getNombre();
																			}
																			else if(
																			    ls_encabezadoColumna.equalsIgnoreCase(
																				        "PAÍS"
																				    )
																			)
																			{
																				Pais lp_pais;

																				lp_pais     = new Pais();

																				ls_idPais = ls_valorCampo;
																				lp_pais.setIdPais(ls_valorCampo);

																				lp_pais = lDAO_paisDAO.findById(
																					    lp_pais
																					);

																				if(lp_pais != null)
																					ls_valorCampo = lp_pais.getNombre();
																			}
																			else if(
																			    ls_encabezadoColumna.equalsIgnoreCase(
																				        "DEPARTAMENTO"
																				    )
																			)
																			{
																				Departamento ld_departamento;

																				ld_departamento     = new Departamento();

																				ls_idDepartamento = ls_valorCampo;
																				ld_departamento.setIdPais(ls_idPais);
																				ld_departamento.setIdDepartamento(
																				    ls_valorCampo
																				);

																				ld_departamento = lDAO_departamentoDAO
																						.findById(ld_departamento);

																				if(ld_departamento != null)
																					ls_valorCampo = ld_departamento
																							.getNombre();
																			}
																			else if(
																			    ls_encabezadoColumna.equalsIgnoreCase(
																				        "MUNICIPIO"
																				    )
																			)
																			{
																				Municipio lm_municipio;

																				lm_municipio = new Municipio();

																				lm_municipio.setIdPais(ls_idPais);
																				lm_municipio.setIdDepartamento(
																				    ls_idDepartamento
																				);
																				lm_municipio.setIdMunicipio(
																				    ls_valorCampo
																				);

																				lm_municipio = lDAO_municipioDAO
																						.findById(lm_municipio);

																				if(lm_municipio != null)
																					ls_valorCampo = lm_municipio
																							.getNombre();
																			}
																			else if(
																			    ls_encabezadoColumna.equalsIgnoreCase(
																				        "OFICINA DE ORIGEN"
																				    )
																			)
																			{
																				OficinaOrigen loo_oficinaOrigen;

																				loo_oficinaOrigen = buscarOficinaOrigenPorId(
																					    ls_valorCampo, adm_manager
																					);

																				if(loo_oficinaOrigen != null)
																					ls_valorCampo = loo_oficinaOrigen
																							.getNombre();
																			}
																		}
																		else if(
																		    ls_idTipoCriterioBusqueda.equalsIgnoreCase(
																			        "3"
																			    )
																		)
																		{
																			if(
																			    ls_encabezadoColumna.equalsIgnoreCase(
																				        "TIPO DE PREDIO"
																				    )
																			)
																			{
																				PredioTipo lpt_predioTipo;

																				lpt_predioTipo = new PredioTipo();

																				lpt_predioTipo.setIdTipoPredio(
																				    ls_valorCampo
																				);

																				lpt_predioTipo = lDAO_predioTipoDAO
																						.findById(lpt_predioTipo);

																				if(lpt_predioTipo != null)
																					ls_valorCampo = lpt_predioTipo
																							.getDescripcion();
																			}
																			else if(
																			    ls_encabezadoColumna.equalsIgnoreCase(
																				        "TIPO DE EJE"
																				    )
																				    || ls_encabezadoColumna
																				    .equalsIgnoreCase("EJE 1")
																			)
																			{
																				TipoEje ltp_tipoEje;

																				ltp_tipoEje = new TipoEje();

																				ltp_tipoEje.setIdTipoEje(ls_valorCampo);

																				ltp_tipoEje = lDAO_tipoEjeDAO.findById(
																					    ltp_tipoEje
																					);

																				if(ltp_tipoEje != null)
																					ls_valorCampo = ltp_tipoEje
																							.getNombre();
																			}
																		}
																		else if(
																		    ls_idTipoCriterioBusqueda.equalsIgnoreCase(
																			        "5"
																			    )
																		)
																		{
																			if(
																			    ls_encabezadoColumna.equalsIgnoreCase(
																				        "CIRCULO REGISTRAL"
																				    )
																			)
																			{
																				CirculoRegistral lcr_circuloRegistral;

																				lcr_circuloRegistral = new CirculoRegistral();

																				lcr_circuloRegistral.setIdCirculo(
																				    ls_valorCampo
																				);

																				lcr_circuloRegistral = lDAO_circuloRegistralDAO
																						.findById(lcr_circuloRegistral);

																				if(lcr_circuloRegistral != null)
																				{
																					StringBuilder lsb_circulo;

																					lsb_circulo = new StringBuilder();

																					lsb_circulo.append(ls_valorCampo);
																					lsb_circulo.append(" - ");
																					lsb_circulo.append(
																					    lcr_circuloRegistral.getNombre()
																					);

																					ls_valorCampo = lsb_circulo.toString();
																				}
																			}
																		}

																		lmss_tmp.put(
																		    ls_encabezadoColumna, ls_valorCampo
																		);
																	}
																}
															}

															if(CollectionUtils.isValidCollection(lmss_tmp))
																lclhmss_coleccionDataFinal.add(lmss_tmp);
														}
													}

													if(CollectionUtils.isValidCollection(lclhmss_coleccionDataFinal))
														tablasDinamicas(
														    ldb_builder, lcs_titulosColumnas, ls_tituloTabla,
														    li_tamanoLetra, ll_porcentajetablas,
														    lclhmss_coleccionDataFinal
														);
												}
											}
										}

										lt_table.setAlignment(TableAlignment.CENTER);
										ldb_builder.endTable();
									}

									ldb_builder.writeln(ControlChar.LINE_BREAK);
								}
							}

							ld_doc.save(lbaos_docOutStream, SaveFormat.RTF);

							lba_pdf = new PDFGenerator().convertirRTFToPDF(
								    lbaos_docOutStream.toByteArray(), adm_manager
								);

							if(lba_pdf == null)
								throw new B2BException(ErrorKeys.ARCHIVO_GENERANDO_ARCHIVO_POR_SOLICITUD);
						}
					}
				}
			}
			else
				throw new B2BException(ErrorKeys.ARCHIVO_GENERANDO_ARCHIVO_POR_SOLICITUD);
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("generarPDFConsultasCriterios", lb2be_e);
			throw lb2be_e;
		}
		catch(Exception le_e)
		{
			clh_LOGGER.error("generarPDFConsultasCriterios", le_e);
			throw new B2BException(le_e.getMessage());
		}

		return lba_pdf;
	}

	/**
	 * Metodo de transacciones con la base de datos con el fin de insertar los tipos de criterios seleccionados
	 * en la tabla SDB_ACC_CRITERIO_BUSQUEDA.
	 *
	 * @param acb_criteriosDeBusqueda correspondiente al valor del tipo de objeto CriteriosDeBusqueda
	 * @param as_userId correspondiente al valor del tipo de objeto String
	 * @param as_ipRemota correspondiente al valor del tipo de objeto String
	 * @return devuelve el valor de CriteriosDeBusqueda
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see CriteriosDeBusqueda
	 */
	public synchronized CriteriosDeBusqueda guardarCriterios(
	    CriteriosDeBusqueda acb_criteriosDeBusqueda, String as_userId, String as_ipRemota
	)
	    throws B2BException
	{
		CriteriosDeBusqueda lccc_camposCriterio;

		lccc_camposCriterio = null;

		if(acb_criteriosDeBusqueda != null)
		{
			DAOManager ldm_manager;

			ldm_manager = DaoManagerFactory.getDAOManager();

			try
			{
				String  ls_idProcesoConsulta;
				String  ls_tipoConsulta;
				boolean lb_sinCantidad;
				boolean lb_copias;

				ls_idProcesoConsulta     = acb_criteriosDeBusqueda.getIdProcesoConsulta();
				ls_tipoConsulta          = acb_criteriosDeBusqueda.getTipoConsulta();
				lb_sinCantidad           = acb_criteriosDeBusqueda.isSinCantidad();
				lb_copias                = acb_criteriosDeBusqueda.isCopias();

				if(!StringUtils.isValidString(ls_tipoConsulta))
					throw new B2BException(ErrorKeys.DEBE_SELECCIONAR_TIPO_CONSULTA);

				if(!StringUtils.isValidString(ls_idProcesoConsulta))
					throw new B2BException(ErrorKeys.DEBE_SELECCIONAR_TIPO_BUSQUEDA);

				if(!lb_sinCantidad)
				{
					Long lL_cantidadConsultas;

					lL_cantidadConsultas = acb_criteriosDeBusqueda.getCantidadConsultas();

					if(!NumericUtils.isValidLong(lL_cantidadConsultas))
						throw new B2BException(ErrorKeys.DEBE_DIGITAR_CANTIDAD_CONSULTAS);

					{
						long ll_cantidadConsultas;

						ll_cantidadConsultas = NumericUtils.getLong(lL_cantidadConsultas);

						if(ls_tipoConsulta.equalsIgnoreCase(EstadoCommon.INDIVIDUAL) && (ll_cantidadConsultas > 10))
							throw new B2BException(ErrorKeys.ERROR_CANTIDAD_NO_CUMPLE_CON_INDIVIDUAL);

						if(ls_tipoConsulta.equalsIgnoreCase(EstadoCommon.MASIVO) && (ll_cantidadConsultas <= 10))
							throw new B2BException(ErrorKeys.ERROR_CANTIDAD_NO_CUMPLE_CON_MASIVO);
					}
				}

				if(!lb_copias)
				{
					boolean lb_nacional;

					lb_nacional = acb_criteriosDeBusqueda.isConsultaNacional();

					if(!lb_nacional)
					{
						String ls_regional;
						String ls_circuloRegistral;

						ls_regional             = acb_criteriosDeBusqueda.getRegional();
						ls_circuloRegistral     = acb_criteriosDeBusqueda.getIdCirculo();

						if(!StringUtils.isValidString(ls_regional) && !StringUtils.isValidString(ls_circuloRegistral))
							throw new B2BException(ErrorKeys.ERROR_NACIONAL_REGIONAL_REGISTRAL);

						if(!StringUtils.isValidString(ls_regional) && !StringUtils.isValidString(ls_circuloRegistral))
							throw new B2BException(ErrorKeys.DEBE_SELECCIONAR_REGIONAL);

						if(!StringUtils.isValidString(ls_circuloRegistral) && !StringUtils.isValidString(ls_regional))
							throw new B2BException(ErrorKeys.DEBE_SELECCIONAR_CIRCULO_REGISTRAL);
					}
				}

				{
					Solicitud ls_solicitud;

					ls_solicitud = acb_criteriosDeBusqueda.getDatosSolicitud();

					if(ls_solicitud != null)
					{
						String ls_idSolicitud;

						ls_idSolicitud = ls_solicitud.getIdSolicitud();

						if(StringUtils.isValidString(ls_idSolicitud))
						{
							CriterioBusquedaDAO             lcb_DAO;
							Collection<CriteriosDeBusqueda> lccb_criteriosDeBusqueda;

							lcb_DAO                      = DaoCreator.getCriterioBusquedaDAO(ldm_manager);
							lccb_criteriosDeBusqueda     = acb_criteriosDeBusqueda.getCriteriosSeleccionados();

							{
								CriteriosDeBusqueda lcdb_criterioDeBusqueda;

								lcdb_criterioDeBusqueda = new CriteriosDeBusqueda();
								lcdb_criterioDeBusqueda.setIdSolicitud(ls_idSolicitud);

								DaoCreator.getDetalleCriterioBusquedaDAO(ldm_manager)
									          .deleteByIdSolicitud(lcdb_criterioDeBusqueda);
								lcb_DAO.deleteCriteriosByIdSolicitud(lcdb_criterioDeBusqueda);
							}

							acb_criteriosDeBusqueda.setIdUsuarioCreacion(as_userId);
							acb_criteriosDeBusqueda.setIpCreacion(as_ipRemota);
							acb_criteriosDeBusqueda.setIdSolicitud(ls_idSolicitud);

							if(!lb_copias)
							{
								Solicitud    ls_solicitudUpdate;
								SolicitudDAO lsd_DAO;

								ls_solicitudUpdate     = new Solicitud();
								lsd_DAO                = DaoCreator.getSolicitudDAO(ldm_manager);

								ls_solicitudUpdate.setIdSolicitud(ls_idSolicitud);

								ls_solicitudUpdate = lsd_DAO.findById(ls_solicitudUpdate);

								if(ls_solicitudUpdate != null)
								{
									ls_solicitudUpdate.setIdSubproceso(ls_idProcesoConsulta);
									ls_solicitudUpdate.setIdUsuarioModificacion(as_userId);
									ls_solicitudUpdate.setIpModificacion(as_ipRemota);

									lsd_DAO.insertOrUpdate(ls_solicitudUpdate, false);
								}
							}

							{
								int     li_max;
								boolean lb_seleccionado;

								li_max              = lcb_DAO.maxConsecutivoConsulta(
									    ls_idSolicitud, ls_idProcesoConsulta
									);
								lb_seleccionado     = false;

								li_max++;

								if(CollectionUtils.isValidCollection(lccb_criteriosDeBusqueda))
								{
									boolean lb_nacional;

									lb_nacional = acb_criteriosDeBusqueda.isConsultaNacional();

									acb_criteriosDeBusqueda.setConsultaNacional(
									    lb_nacional ? EstadoCommon.S : EstadoCommon.N
									);

									for(CriteriosDeBusqueda lcb_iterador : lccb_criteriosDeBusqueda)
									{
										if((lcb_iterador != null) && lcb_iterador.isSeleccion())
										{
											acb_criteriosDeBusqueda.setIdTipoCriterio(lcb_iterador.getIdTipoCriterio());
											acb_criteriosDeBusqueda.setConsecutivo(li_max);

											lcb_DAO.insertarCriterio(acb_criteriosDeBusqueda);
											lb_seleccionado = true;
										}
									}
								}

								if(!lb_seleccionado)
									throw new B2BException(ErrorKeys.ERROR_DEBE_SELECCIONAR_MINIMO_UN_CRITERIO);
							}
						}
					}
				}
			}
			catch(B2BException lb2be_e)
			{
				ldm_manager.setRollbackOnly();

				clh_LOGGER.error("guardarCriterios", lb2be_e);

				throw lb2be_e;
			}
			finally
			{
				ldm_manager.close();
			}
		}

		return lccc_camposCriterio;
	}

	/**
	 * Metodo encargado de guardar los criterios seleccionados y llenados en
	 * pantalla de consultas.
	 *
	 * @param acc_camposConsulta            Argumento de tipo CamposConsulta que contiene los criterios a
	 *            guardar.
	 * @param as_userId            Argumento de tipo String que contiene el usuario que realiza la
	 *            operación.
	 * @param as_ipRemota            Argumento de tipo String que contiene el la ip desde donde se
	 *            realiza la operación.
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public synchronized void guardarCriteriosCampos(
	    CamposConsulta acc_camposConsulta, String as_userId, String as_ipRemota
	)
	    throws B2BException
	{
		DAOManager ldm_manager;

		ldm_manager = DaoManagerFactory.getDAOManager();

		try
		{
			guardarCriteriosCampos(acc_camposConsulta, ldm_manager, as_userId, as_ipRemota);
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("guardarCriteriosCampos", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}
	}

	/**
	 * Metodo encargado de guardar los criterios seleccionados y llenados en pantalla de consultas.
	 *
	 * @param acc_camposConsulta Argumento de tipo CamposConsulta que contiene los criterios a guardar.
	 * @param adm_manager Argumento de tipo DaoManager que permite la conexión a la base de datos.
	 * @param as_userId Argumento de tipo String que contiene el usuario que realiza la operación.
	 * @param as_ipRemota Argumento de tipo String que contiene el la ip desde donde se realiza la operación.
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public synchronized void guardarCriteriosCampos(
	    CamposConsulta acc_camposConsulta, DAOManager adm_manager, String as_userId, String as_ipRemota
	)
	    throws B2BException
	{
		guardarCriteriosCampos(acc_camposConsulta, false, adm_manager, as_userId, as_ipRemota);
	}

	/**
	 * Metodo encargado de guardar los criterios seleccionados y llenados en pantalla de consultas.
	 *
	 * @param acc_camposConsulta Argumento de tipo CamposConsulta que contiene los criterios a guardar.
	 * @param adm_manager Argumento de tipo DaoManager que permite la conexión a la base de datos.
	 * @param as_userId Argumento de tipo String que contiene el usuario que realiza la operación.
	 * @param as_ipRemota Argumento de tipo String que contiene el la ip desde donde se realiza la operación.
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public synchronized void guardarCriteriosCampos(
	    CamposConsulta acc_camposConsulta, boolean ab_copias, DAOManager adm_manager, String as_userId,
	    String as_ipRemota
	)
	    throws B2BException
	{
		if(acc_camposConsulta != null)
		{
			Collection<CamposConsulta> lccc_camposConsulta;
			CriteriosDeBusqueda        lcdb_criteriosDeBusqueda;

			lccc_camposConsulta          = acc_camposConsulta.getDataCamposConsulta();
			lcdb_criteriosDeBusqueda     = acc_camposConsulta.getCriteriosDeBusqueda();

			if(CollectionUtils.isValidCollection(lccc_camposConsulta) && (lcdb_criteriosDeBusqueda != null))
			{
				DetalleCriterioBusquedaDAO ldcbd_DAO;
				CampoCriterioBusquedaDAO   lccbd_DAO;
				long                       ll_maximo;
				boolean                    lb_existente;

				ldcbd_DAO        = DaoCreator.getDetalleCriterioBusquedaDAO(adm_manager);
				lccbd_DAO        = DaoCreator.getCampoCriterioBusquedaDAO(adm_manager);
				ll_maximo        = ldcbd_DAO.maxConsecutivoConsultaDetalle(lcdb_criteriosDeBusqueda);
				lb_existente     = ab_copias ? false : true;

				ll_maximo++;

				for(CamposConsulta lcc_iterador : lccc_camposConsulta)
				{
					if(lcc_iterador != null)
					{
						boolean lb_valido;
						String  ls_idTipoCriterioBusqueda;
						String  ls_idCampoCriterioBusqueda;

						lb_valido                      = validarDetalleCriterioBusqueda(lcc_iterador, lccbd_DAO);
						ls_idTipoCriterioBusqueda      = lcc_iterador.getIdTipoCriterioBusqueda();
						ls_idCampoCriterioBusqueda     = lcc_iterador.getIdCampoCriterioBusqueda();

						if(lb_valido)
						{
							CriteriosDeBusqueda lcdb_criterioAAgregar;

							lcdb_criterioAAgregar = new CriteriosDeBusqueda();

							lcdb_criterioAAgregar.setIdSolicitud(lcdb_criteriosDeBusqueda.getIdSolicitud());
							lcdb_criterioAAgregar.setConsecutivo(lcdb_criteriosDeBusqueda.getConsecutivo());
							lcdb_criterioAAgregar.setIdProcesoConsulta(lcdb_criteriosDeBusqueda.getIdProcesoConsulta());
							lcdb_criterioAAgregar.setIdTipoCriterio(ls_idTipoCriterioBusqueda);
							lcdb_criterioAAgregar.setConsecutivoConsultaDetalle(ll_maximo);
							lcdb_criterioAAgregar.setIdCampoCriterioBusqueda(ls_idCampoCriterioBusqueda);

							{
								String  ls_tipoCampo;
								String  ls_valorCampo;
								boolean lb_date;

								ls_tipoCampo     = lcc_iterador.getTipoCampo();
								lb_date          = StringUtils.isValidString(ls_tipoCampo)
										&& ls_tipoCampo.equalsIgnoreCase(TipoDatoCommon.FECHA);

								ls_valorCampo = lb_date
									? StringUtils.getString(
									    lcc_iterador.getValorCampoFecha(), FormatoFechaCommon.DIA_MES_ANIO
									) : lcc_iterador.getValorCampo();

								lcdb_criterioAAgregar.setValorCampo(ls_valorCampo);
							}

							if((ab_copias && !lb_existente) || (!ab_copias && lb_existente))
							{
								boolean lb_validarExistente;

								lb_validarExistente = validarDetalleExistente(
									    ls_idTipoCriterioBusqueda, ls_idCampoCriterioBusqueda
									);

								if(lb_validarExistente)
								{
									int li_contador;

									li_contador     = ldcbd_DAO.buscarDetalleExistente(lcdb_criterioAAgregar);

									lb_existente = li_contador > 0;
								}
							}

							lcdb_criterioAAgregar.setIdUsuarioCreacion(as_userId);
							lcdb_criterioAAgregar.setIpCreacion(as_ipRemota);

							ldcbd_DAO.insertarDetalleCriterios(lcdb_criterioAAgregar);
						}
					}
				}

				if(lb_existente)
					throw new B2BException(ErrorKeys.ERROR_CRITERIOS_DE_CONSULTA_EXISTENTES);
			}
		}
	}

	/**
	 * Metodo encargado de guardar los criterios seleccionados y consultar documentos del OWCC.
	 *
	 * @param accc_camposConsulta Argumento de tipo CamposConsulta que contiene los criterios a guardar.
	 * @param ab_guardarCriterios correspondiente al valor de guardar criterios
	 * @param as_userId Argumento de tipo String que contiene el usuario que realiza la operación.
	 * @param as_ipRemota Argumento de tipo String que contiene el la ip desde donde se realiza la operación.
	 * @return el valor de campos consulta
	 * @throws B2BException Señala que se ha generado una excepción
	 */
	public synchronized CamposConsulta guardarCriteriosYConsultarCopias(
	    Collection<CamposConsulta> accc_camposConsulta, Solicitud as_solicitud, boolean ab_guardarCriterios,
	    boolean ab_traerSolicitudCopias, String as_userId, String as_ipRemota
	)
	    throws B2BException
	{
		DAOManager     ldm_manager;
		CamposConsulta lcc_camposConsulta;

		ldm_manager            = DaoManagerFactory.getDAOManager();
		lcc_camposConsulta     = new CamposConsulta();

		try
		{
			if(CollectionUtils.isValidCollection(accc_camposConsulta))
			{
				boolean                   lb_antiguoSistema;
				DocumentoOWCC             ldo_documentoOWCC;
				Collection<DocumentoOWCC> lcdo_documentosOWCC;

				lb_antiguoSistema       = false;
				ldo_documentoOWCC       = null;
				lcdo_documentosOWCC     = null;

				for(CamposConsulta lcc_iterador : accc_camposConsulta)
				{
					if(lcc_iterador != null)
					{
						String                     ls_idTipoCriterioBusqueda;
						Collection<CamposConsulta> lccc_dataCamposConsulta;

						if(ab_guardarCriterios)
							guardarCriteriosCampos(lcc_iterador, true, ldm_manager, as_userId, as_ipRemota);

						ls_idTipoCriterioBusqueda     = lcc_iterador.getIdTipoCriterioBusqueda();
						lb_antiguoSistema             = StringUtils.isValidString(ls_idTipoCriterioBusqueda)
								&& (ls_idTipoCriterioBusqueda.equalsIgnoreCase(
								    TipoCriterioBusquedaCommon.ANTIGUO_SISTEMA
								)
								|| ls_idTipoCriterioBusqueda.equalsIgnoreCase(
								    TipoCriterioBusquedaCommon.DOCUMENTO_ANTIGUO_SISTEMA
								));
						lccc_dataCamposConsulta       = lcc_iterador.getDataCamposConsulta();

						lcdo_documentosOWCC = lb_antiguoSistema ? null
							                                    : consultarDocumentosOWCCCriterios(
							    ldm_manager, lccc_dataCamposConsulta
							);

						if(
						    !CollectionUtils.isValidCollection(lcdo_documentosOWCC)
							    && CollectionUtils.isValidCollection(lccc_dataCamposConsulta)
							    && StringUtils.isValidString(ls_idTipoCriterioBusqueda)
							    && ls_idTipoCriterioBusqueda.equalsIgnoreCase(
							        TipoCriterioBusquedaCommon.DOCUMENTO_COPIAS
							    )
						)
						{
							ldo_documentoOWCC = extraerCriteriosDocumentoOWCC(ldm_manager, lccc_dataCamposConsulta);

							if(ldo_documentoOWCC != null)
							{
								DocumentoConstancia ldc_documentoConstancia;

								ldc_documentoConstancia = new DocumentoConstancia();

								ldc_documentoConstancia.setIdTipoDocumento(ldo_documentoOWCC.getDocType());
								ldc_documentoConstancia.setNumero(ldo_documentoOWCC.getNumeroDoc());
								ldc_documentoConstancia.setFechaDocumento(ldo_documentoOWCC.getFechaDocumento());

								{
									Collection<DocumentoConstancia> lcdc_documentosConstancia;

									lcdc_documentosConstancia = DaoCreator.getDocumentoDAO(ldm_manager)
											                                  .consultaMaxDocConstancia(
											    ldc_documentoConstancia
											);

									if(CollectionUtils.isValidCollection(lcdc_documentosConstancia))
									{
										Collection<DataReproduccionConstancia> lcdrc_dataReproduccionConstancia;
										AnotacionPredioDAO                     lapd_DAO;
										String                                 ls_idCirculo;

										lcdrc_dataReproduccionConstancia     = new ArrayList<DataReproduccionConstancia>();
										lapd_DAO                             = DaoCreator.getAnotacionPredioDAO(
											    ldm_manager
											);
										ls_idCirculo                         = ldo_documentoOWCC.getIdOrip();

										for(DocumentoConstancia ldc_iterador : lcdc_documentosConstancia)
										{
											if(ldc_iterador != null)
											{
												DataReproduccionConstancia ldrc_dataReproduccionConstancia;

												ldrc_dataReproduccionConstancia = new DataReproduccionConstancia();

												ldrc_dataReproduccionConstancia.setDocumento(ldc_iterador);

												{
													AnotacionPredioDireccion lap_anotacionPredio;

													lap_anotacionPredio = new AnotacionPredioDireccion();

													lap_anotacionPredio.setIdCirculo(ls_idCirculo);

													ldrc_dataReproduccionConstancia.setAnotacionPredioDireccion(
													    lapd_DAO.findByRadicacion(lap_anotacionPredio, ldc_iterador)
													);
												}

												lcdrc_dataReproduccionConstancia.add(ldrc_dataReproduccionConstancia);
											}
										}

										lcc_camposConsulta.setDataReproduccionConstancia(
										    lcdrc_dataReproduccionConstancia
										);
									}
								}
							}
						}
						else if(lb_antiguoSistema)
							extraerCriteriosDocumentoOWCC(ldm_manager, lccc_dataCamposConsulta, ldo_documentoOWCC);
					}
				}

				if(lb_antiguoSistema && (ldo_documentoOWCC != null))
					lcdo_documentosOWCC = consultarDocumentosOWCC(
						    ldm_manager, ldo_documentoOWCC, RepositorioCommon.FINAL, false
						);

				if(ab_traerSolicitudCopias && (as_solicitud != null))
				{
					String             ls_idSolicitud;
					SolicitudCopiasDAO lscd_DAO;

					ls_idSolicitud     = as_solicitud.getIdSolicitud();
					lscd_DAO           = DaoCreator.getSolicitudCopiasDAO(ldm_manager);

					for(DocumentoOWCC ldo_iterador : lcdo_documentosOWCC)
					{
						if(ldo_iterador != null)
						{
							TipoDocumental ltd_tipoDocumental;

							ltd_tipoDocumental = ldo_iterador.getTipoDocumental();

							if(ltd_tipoDocumental != null)
							{
								String ls_idTipoDocumental;

								ls_idTipoDocumental = ltd_tipoDocumental.getIdTipoDocumental();

								if(StringUtils.isValidString(ls_idTipoDocumental))
								{
									SolicitudCopias lsc_solicitudCopias;

									lsc_solicitudCopias = lscd_DAO.findByIdSolicitudTipoDoc(
										    ls_idSolicitud, ls_idTipoDocumental
										);

									if(lsc_solicitudCopias != null)
										ldo_iterador.setNumeroCopias(lsc_solicitudCopias.getNumeroCopias());
								}
							}
						}
					}
				}

				lcc_camposConsulta.setDocumentosOWCC(lcdo_documentosOWCC);
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("guardarCriteriosYConsultarCopias", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lcc_camposConsulta;
	}

	/**
	 * Metodo encargado de guardar en solicitud copias y preliquidar copias para antiguo sistema.
	 *
	 * @param abas_parametros Argumento de tipo <code>BuscarAntiguoSistema</code> que contiene los criterios a guardar y liquidar.
	 * @param as_userId Argumento de tipo <code>String</code> que contiene el id del usuario que realiza la operación.
	 * @param as_remoteIp Argumento de tipo <code>String</code> que contiene la ip del usuario que realiza la operación.
	 *
	 * @throws B2BException Señala que se ha generado una excepción
	 */
	public synchronized void guardarSolicitudCopiasPreliquidar(
	    BuscarAntiguoSistema abas_parametros, String as_userId, String as_remoteIp
	)
	    throws B2BException
	{
		DAOManager ldm_manager;

		ldm_manager = DaoManagerFactory.getDAOManager();

		try
		{
			if(abas_parametros != null)
			{
				Collection<DocumentoOWCC> lcdo_documentosOWCC;

				lcdo_documentosOWCC = abas_parametros.getDocumentosOWCC();

				if(CollectionUtils.isValidCollection(lcdo_documentosOWCC))
				{
					TurnoHistoria lth_turnoHistoria;

					lth_turnoHistoria = DaoCreator.getTurnoHistoriaDAO(ldm_manager)
							                          .findById(abas_parametros.getIdTurnoHistoria());

					if(lth_turnoHistoria != null)
					{
						String ls_idSolicitud;

						ls_idSolicitud = lth_turnoHistoria.getIdSolicitud();

						if(StringUtils.isValidString(ls_idSolicitud))
						{
							Solicitud ls_solicitud;

							ls_solicitud = DaoCreator.getSolicitudDAO(ldm_manager).findById(ls_idSolicitud);

							if(ls_solicitud != null)
							{
								Long    ll_numeroCopias;
								boolean lb_validarNumeroFolios;

								ll_numeroCopias            = ls_solicitud.getNumeroCopias();
								lb_validarNumeroFolios     = abas_parametros.isValidarNumeroFolios();

								if(NumericUtils.isValidLong(ll_numeroCopias) || lb_validarNumeroFolios)
								{
									DaoCreator.getSolicitudCopiasDAO(ldm_manager).deleteByIdSolicitud(ls_idSolicitud);

									salvarSolicitudCopias(
									    ldm_manager, lcdo_documentosOWCC, ls_idSolicitud, ll_numeroCopias,
									    lb_validarNumeroFolios, as_userId, as_remoteIp
									);

									{
										Liquidacion ll_liquidacion;

										ll_liquidacion = new Liquidacion();

										ll_liquidacion.setSolicitud(ls_solicitud);
										ll_liquidacion.setIdCondicion(IdentificadoresCommon.PRELIQUIDAR);
										ll_liquidacion.setIdTurno(abas_parametros.getIdTurno());
										ll_liquidacion.setIdSolicitud(ls_idSolicitud);
										ll_liquidacion.setIdUsuarioCreacion(as_userId);
										ll_liquidacion.setIpCreacion(as_remoteIp);

										procLiquidacion(ll_liquidacion, ldm_manager, as_userId, as_remoteIp);
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

			clh_LOGGER.error("guardarSolicitudCopiasPreliquidar", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}
	}

	/**
	 * Metodo encargado de consultar el consecutivo máximo del detalle de criterios.
	 *
	 * @param acc_camposConsulta            Argumento de tipo CamposConsulta que contiene los criterios
	 *            necesarios para realizar la consulta.
	 * @return Variable de tipo long que contiene el consecutivo máximo de detalle
	 *         de criterios.
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public synchronized long maxConsecutivoConsultaDetalle(CamposConsulta acc_camposConsulta)
	    throws B2BException
	{
		long ll_maximo;

		ll_maximo = 0L;

		if(acc_camposConsulta != null)
		{
			DAOManager ldm_manager;

			ldm_manager = DaoManagerFactory.getDAOManager();

			try
			{
				CriteriosDeBusqueda lcdb_criteriosDeBusqueda;

				lcdb_criteriosDeBusqueda = acc_camposConsulta.getCriteriosDeBusqueda();

				if(lcdb_criteriosDeBusqueda != null)
					ll_maximo = DaoCreator.getDetalleCriterioBusquedaDAO(ldm_manager)
							                  .maxConsecutivoConsultaDetalle(lcdb_criteriosDeBusqueda);
			}
			catch(B2BException lb2be_e)
			{
				ldm_manager.setRollbackOnly();

				clh_LOGGER.error("maxConsecutivoConsultaDetalle", lb2be_e);

				throw lb2be_e;
			}
			finally
			{
				ldm_manager.close();
			}
		}

		return ll_maximo;
	}

	/**
	 * Metodo encargado de validar si la información capturada en expedicón de copias es diferente en buscar antiguo sistema.
	 *
	 * @param abas_parametros Argumento de tipo <code>BuscarAntiguoSistema</code> que contiene los criterios a validar.
	 * @param as_userId Argumento de tipo <code>String</code> que contiene el id del usuario que realiza la operación.
	 * @param as_remoteIp Argumento de tipo <code>String</code> que contiene la ip desde donde se realiza la operación.
	 * @throws B2BException Señala que se ha generado una excepción.
	 */
	public synchronized void modificarDatosAntiguoSistema(
	    BuscarAntiguoSistema abas_parametros, String as_userId, String as_remoteIp
	)
	    throws B2BException
	{
		if(abas_parametros != null)
		{
			DAOManager ldm_manager;

			ldm_manager = DaoManagerFactory.getDAOManager();

			try
			{
				Long ll_idTurnoHistoria;

				ll_idTurnoHistoria = abas_parametros.getIdTurnoHistoria();

				if(NumericUtils.isValidLong(ll_idTurnoHistoria))
				{
					TurnoHistoria lth_turnoHistoria;

					lth_turnoHistoria = DaoCreator.getTurnoHistoriaDAO(ldm_manager).findById(ll_idTurnoHistoria);

					if(lth_turnoHistoria != null)
					{
						String ls_idSolicitud;

						ls_idSolicitud = lth_turnoHistoria.getIdSolicitud();

						if(StringUtils.isValidString(ls_idSolicitud))
						{
							String ls_idTipoCriterioBusqueda;

							ls_idTipoCriterioBusqueda = abas_parametros.getIdTipoCriterioBusqueda();

							if(StringUtils.isValidString(ls_idTipoCriterioBusqueda))
							{
								CriteriosDeBusqueda             lcb_criterioDeBusqueda;
								Collection<CriteriosDeBusqueda> lccb_criteriosDeBusqueda;

								lcb_criterioDeBusqueda = new CriteriosDeBusqueda();

								lcb_criterioDeBusqueda.setIdSolicitud(ls_idSolicitud);
								lcb_criterioDeBusqueda.setIdTipoCriterio(ls_idTipoCriterioBusqueda);

								lccb_criteriosDeBusqueda = DaoCreator.getCriterioBusquedaDAO(ldm_manager)
										                                 .buscarCriteriosPorsolicitudTipoCriterio(
										    lcb_criterioDeBusqueda
										);

								if(CollectionUtils.isValidCollection(lccb_criteriosDeBusqueda))
								{
									CriteriosDeBusqueda lcdb_criterioBusqueda;

									lcdb_criterioBusqueda = lccb_criteriosDeBusqueda.iterator().next();

									if(lcdb_criterioBusqueda != null)
									{
										CamposConsulta             lcc_camposConsulta;
										Collection<CamposConsulta> lccc_camposCriterioBusqueda;

										lcc_camposConsulta = new CamposConsulta();

										lcc_camposConsulta.setIdTipoCriterioBusqueda(ls_idTipoCriterioBusqueda);

										lccc_camposCriterioBusqueda = DaoCreator.getCampoCriterioBusquedaDAO(
											    ldm_manager
											).buscarCamposPorCriterio(lcc_camposConsulta);

										if(CollectionUtils.isValidCollection(lccc_camposCriterioBusqueda))
										{
											DetalleCriterioBusquedaDAO             ldcbd_DAO;
											DetalleAntSistemaUI                    ldasu_detalleAntSistema;
											ConsultaCriteriosCalificacionDocumento lcccd_criteriosDocumento;
											Documento                              ld_documento;
											AnotacionPredio                        lap_anotacionPredio;

											ldcbd_DAO                    = DaoCreator.getDetalleCriterioBusquedaDAO(
												    ldm_manager
												);
											ldasu_detalleAntSistema      = abas_parametros.getDetalleAntSistema();
											lcccd_criteriosDocumento     = abas_parametros
													.getConsultaCriteriosCalificacionDocumento();
											ld_documento                 = (lcccd_criteriosDocumento != null)
												? lcccd_criteriosDocumento.getDocumento() : null;
											lap_anotacionPredio          = (lcccd_criteriosDocumento != null)
												? lcccd_criteriosDocumento.getAnotacionPredio() : null;

											for(CamposConsulta lcc_iterador : lccc_camposCriterioBusqueda)
											{
												if(lcc_iterador != null)
												{
													String ls_idCampoCriterioBusqueda;

													ls_idCampoCriterioBusqueda = lcc_iterador.getIdCampoCriterioBusqueda();

													if(StringUtils.isValidString(ls_idCampoCriterioBusqueda))
													{
														CriteriosDeBusqueda lcdb_criterioValor;
														String              ls_valor;
														int                 li_contador;

														lcdb_criterioValor     = new CriteriosDeBusqueda();
														ls_valor               = null;
														li_contador            = 0;

														if(
														    ls_idTipoCriterioBusqueda.equalsIgnoreCase(
															        TipoCriterioBusquedaCommon.ANTIGUO_SISTEMA
															    ) && (ldasu_detalleAntSistema != null)
														)
															ls_valor = ls_idCampoCriterioBusqueda.equalsIgnoreCase(
																    CampoCriterioBusquedaCommon.ANTIGUO_SISTEMA_LIBRO
																)
																? StringUtils.getString(
																    ldasu_detalleAntSistema.getIdLibroAntSistema()
																)
																: (ls_idCampoCriterioBusqueda.equalsIgnoreCase(
																    CampoCriterioBusquedaCommon.ANTIGUO_SISTEMA_TOMO
																) ? ldasu_detalleAntSistema.getTomo()
																  : (ls_idCampoCriterioBusqueda.equalsIgnoreCase(
																    CampoCriterioBusquedaCommon.ANTIGUO_SISTEMA_FOLIO
																) ? ldasu_detalleAntSistema.getFolio()
																  : (ls_idCampoCriterioBusqueda.equalsIgnoreCase(
																    CampoCriterioBusquedaCommon.ANTIGUO_SISTEMA_TIPO_DE_PARTIDA
																) ? ldasu_detalleAntSistema.getPartida()
																  : (ls_idCampoCriterioBusqueda.equalsIgnoreCase(
																    CampoCriterioBusquedaCommon.ANTIGUO_SISTEMA_NUMERO_PARTIDA
																)
																? StringUtils.getString(
																    ldasu_detalleAntSistema.getNumeroPartida()
																)
																: (ls_idCampoCriterioBusqueda.equalsIgnoreCase(
																    CampoCriterioBusquedaCommon.ANTIGUO_SISTEMA_MUNICIPIO
																) ? ldasu_detalleAntSistema.getIdMunicipioTomo()
																  : (ls_idCampoCriterioBusqueda.equalsIgnoreCase(
																    CampoCriterioBusquedaCommon.ANTIGUO_SISTEMA_ANIO
																) ? ldasu_detalleAntSistema.getAnio()
																  : (ls_idCampoCriterioBusqueda.equalsIgnoreCase(
																    CampoCriterioBusquedaCommon.ANTIGUO_SISTEMA_CIRCULO_REGISTRAL
																) ? ldasu_detalleAntSistema.getIdCirculo()
																  : (ls_idCampoCriterioBusqueda.equalsIgnoreCase(
																    CampoCriterioBusquedaCommon.ANTIGUO_SISTEMA_TIPO_DE_PREDIO
																) ? ldasu_detalleAntSistema.getTipoPredio() : null))))))));
														else if(
														    ls_idTipoCriterioBusqueda.equalsIgnoreCase(
															        TipoCriterioBusquedaCommon.DOCUMENTO_ANTIGUO_SISTEMA
															    ) && (ld_documento != null)
														)
															ls_valor = (ls_idCampoCriterioBusqueda.equalsIgnoreCase(
																    CampoCriterioBusquedaCommon.DOCUMENTO_COPIAS_CIRCULO
																) && (lap_anotacionPredio != null))
																? lap_anotacionPredio.getIdCirculo()
																: (ls_idCampoCriterioBusqueda.equalsIgnoreCase(
																    CampoCriterioBusquedaCommon.DOCUMENTO_COPIAS_TIPO_DOCUMENTO
																) ? ld_documento.getIdTipoDocumento()
																  : (ls_idCampoCriterioBusqueda.equalsIgnoreCase(
																    CampoCriterioBusquedaCommon.DOCUMENTO_COPIAS_NUMERO_DOCUMENTO
																) ? ld_documento.getNumero()
																  : (ls_idCampoCriterioBusqueda.equalsIgnoreCase(
																    CampoCriterioBusquedaCommon.DOCUMENTO_COPIAS_FECHA_DOCUMENTO
																)
																? StringUtils.getString(
																    ld_documento.getFechaDocumento(), "dd/MM/yyyy"
																)
																: (ls_idCampoCriterioBusqueda.equalsIgnoreCase(
																    CampoCriterioBusquedaCommon.DOCUMENTO_COPIAS_TIPO_DE_OFICINA
																) ? ld_documento.getIdTipoOficina()
																  : (ls_idCampoCriterioBusqueda.equalsIgnoreCase(
																    CampoCriterioBusquedaCommon.DOCUMENTO_COPIAS_PAIS
																) ? ld_documento.getIdPais()
																  : (ls_idCampoCriterioBusqueda.equalsIgnoreCase(
																    CampoCriterioBusquedaCommon.DOCUMENTO_COPIAS_DEPARTAMENTO
																) ? ld_documento.getIdDepartamento()
																  : (ls_idCampoCriterioBusqueda.equalsIgnoreCase(
																    CampoCriterioBusquedaCommon.DOCUMENTO_COPIAS_DEPARTAMENTO
																) ? ld_documento.getIdDepartamento()
																  : (ls_idCampoCriterioBusqueda.equalsIgnoreCase(
																    CampoCriterioBusquedaCommon.DOCUMENTO_COPIAS_MUNICIPIO
																) ? ld_documento.getIdMunicipio()
																  : (ls_idCampoCriterioBusqueda.equalsIgnoreCase(
																    CampoCriterioBusquedaCommon.DOCUMENTO_COPIAS_OFICINA_DE_ORIGEN
																) ? ld_documento.getIdOficinaOrigen() : null)))))))));

														lcdb_criterioValor.setIdSolicitud(
														    lcdb_criterioBusqueda.getIdSolicitud()
														);
														lcdb_criterioValor.setConsecutivo(
														    lcdb_criterioBusqueda.getConsecutivo()
														);
														lcdb_criterioValor.setConsecutivoConsultaDetalle(1L);
														lcdb_criterioValor.setIdProcesoConsulta(
														    lcdb_criterioBusqueda.getIdProcesoConsulta()
														);
														lcdb_criterioValor.setIdTipoCriterio(
														    lcdb_criterioBusqueda.getIdTipoCriterio()
														);
														lcdb_criterioValor.setIdCampoCriterioBusqueda(
														    ls_idCampoCriterioBusqueda
														);
														lcdb_criterioValor.setValorCampo(ls_valor);

														li_contador = ldcbd_DAO.buscarDetalle(lcdb_criterioValor);

														if(li_contador > 0)
														{
															lcdb_criterioValor.setIdUsuarioModificacion(as_userId);
															lcdb_criterioValor.setIpModificacion(as_remoteIp);

															ldcbd_DAO.actualizarValoresDetalleCriterio(
															    lcdb_criterioValor
															);
														}
														else
														{
															lcdb_criterioValor.setIdUsuarioCreacion(as_userId);
															lcdb_criterioValor.setIpCreacion(as_remoteIp);

															ldcbd_DAO.insertarDetalleCriterios(lcdb_criterioValor);
														}
													}
												}
											}
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

				clh_LOGGER.error("modificarDatosAntiguoSistema", lb2be_e);

				throw lb2be_e;
			}
			finally
			{
				ldm_manager.close();
			}
		}
	}

	/**
	 * Retorna el valor del objeto de CriteriosDeBusqueda.
	 *
	 * @param acdb_criteriosDeBusqueda correspondiente al valor del tipo de objeto CriteriosDeBusqueda
	 * @param as_usuario correspondiente al valor del tipo de objeto String
	 * @param as_ipRemote correspondiente al valor del tipo de objeto String
	 * @return devuelve el valor de CriteriosDeBusqueda
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see CriteriosDeBusqueda
	 */
	public synchronized CriteriosDeBusqueda terminarProcesoConsultas(
	    CriteriosDeBusqueda acdb_criteriosDeBusqueda, String as_usuario, String as_ipRemote
	)
	    throws B2BException
	{
		DAOManager ldm_manager;

		ldm_manager = DaoManagerFactory.getDAOManager();

		try
		{
			if(acdb_criteriosDeBusqueda != null)
			{
				RegistroBusiness lrb_registroBusiness;
				Solicitud        ls_solicitud;

				lrb_registroBusiness     = getRegistroBusiness();
				ls_solicitud             = acdb_criteriosDeBusqueda.getDatosSolicitud();
				ls_solicitud.setDescripcion(acdb_criteriosDeBusqueda.getObservaciones());

				if(ls_solicitud != null)
				{
					String ls_nir;
					String ls_idSolicitud;

					ls_idSolicitud     = StringUtils.getStringNotNull(ls_solicitud.getIdSolicitud());

					ls_nir = crearNirConsultas(as_usuario, as_ipRemote);

					if(StringUtils.isValidString(ls_nir))
					{
						Solicitud    lso_solicitud;
						SolicitudDAO lsd_DAO;

						lsd_DAO           = DaoCreator.getSolicitudDAO(ldm_manager);
						lso_solicitud     = lsd_DAO.findById(ls_solicitud);
						lso_solicitud.setDescripcion(ls_solicitud.getDescripcion());

						if(lso_solicitud != null)
						{
							lso_solicitud.setNir(ls_nir);
							lso_solicitud.setFechaSolicitud(new Date());

							lso_solicitud.setIdUsuarioModificacion(as_usuario);
							lso_solicitud.setIpModificacion(as_ipRemote);

							lsd_DAO.insertOrUpdate(lso_solicitud, false);

							acdb_criteriosDeBusqueda.setDatosSolicitud(lso_solicitud);
						}
					}
					else
						throw new B2BException(ErrorKeys.NO_GENERO_NIR);

					{
						boolean lb_copias;
						boolean lb_conDocumentosOWCC;

						lb_copias                = acdb_criteriosDeBusqueda.isCopias();
						lb_conDocumentosOWCC     = acdb_criteriosDeBusqueda.isConDocumentosOWCC();

						if(!lb_copias || lb_conDocumentosOWCC)
						{
							{
								Liquidacion ll_liquidacion;

								ll_liquidacion = new Liquidacion();

								ll_liquidacion.setSolicitud(ls_solicitud);
								ll_liquidacion.setIdCondicion(IdentificadoresCommon.LIQUIDAR);
								ll_liquidacion.setIdUsuarioCreacion(as_usuario);
								ll_liquidacion.setIpCreacion(as_usuario);

								procLiquidacion(ll_liquidacion, ldm_manager, as_usuario, as_ipRemote);
							}

							{
								Registro lr_parametros;

								lr_parametros = new Registro();

								lr_parametros.setIdUsuarioCreacion(as_usuario);
								lr_parametros.setSolicitud(ls_solicitud);
								lr_parametros.setIdCondicion(IdentificadoresCommon.LIQUIDAR);
								lr_parametros.setTipoRecibo(IdentificadoresCommon.RECIBO_LIQUIDACION);

								lr_parametros = lrb_registroBusiness.generarReciboLiquidacion(
									    lr_parametros, true, as_usuario, null, null, ldm_manager
									);

								if(lr_parametros != null)
									acdb_criteriosDeBusqueda.setPdfLiquidacion(lr_parametros.getPdf());
							}
						}

						{
							byte[]        lba_archivoNir;
							TurnoHistoria lth_turnoHistoria;

							lth_turnoHistoria     = new TurnoHistoria();
							lba_archivoNir        = generarPDFConsultasCriterios(
								    ls_idSolicitud, ldm_manager, as_usuario
								);

							lth_turnoHistoria.setIdSolicitud(ls_idSolicitud);
							lth_turnoHistoria.setIdUsuarioModificacion(as_usuario);
							lth_turnoHistoria.setIpModificacion(as_ipRemote);

							lth_turnoHistoria = DaoCreator.getProcedimientosDAO(ldm_manager)
									                          .spCreateStage(lth_turnoHistoria);

							if(lb_copias)
							{
								Solicitud ls_solicitudProc;

								ls_solicitudProc = new Solicitud();

								ls_solicitudProc.setIdSolicitud(ls_solicitud.getIdSolicitud());
								ls_solicitudProc.setIdUsuarioModificacion(as_usuario);
								ls_solicitudProc.setIpModificacion(as_ipRemote);

								ls_solicitudProc = DaoCreator.getSolicitudDAO(ldm_manager).findById(ls_solicitudProc);

								if(ls_solicitudProc != null)
								{
									String  ls_exenta;
									boolean lb_exenta;

									ls_exenta     = ls_solicitudProc.getEntidadExenta();
									lb_exenta     = StringUtils.isValidString(ls_exenta)
											&& ls_exenta.equalsIgnoreCase(EstadoCommon.S);

									if(!lb_exenta)
										DaoCreator.getProcedimientosDAO(ldm_manager)
											          .procLlaCrearEtapaTrg(
											    ls_solicitudProc, null, IdentificadoresCommon.COPIAS,
											    NumericUtils.getLongWrapper(EtapaCommon.VALIDACION_INFORMACION_EN_OWCC)
											);
								}
							}

							if(lba_archivoNir != null)
							{
								Imagenes         li_imagenes;
								ImagenesDAO      li_DAO;
								DocumentosSalida lds_documentosSalida;
								long             ll_secuencia;

								li_DAO                   = DaoCreator.getImagenesDAO(ldm_manager);
								li_imagenes              = new Imagenes();
								lds_documentosSalida     = new DocumentosSalida();

								li_imagenes.setImagenBlob(lba_archivoNir);

								li_imagenes.setTipoArchivo(ExtensionCommon.PDF_MAYUS);
								li_imagenes.setIdUsuarioCreacion(as_usuario);
								li_imagenes.setIpCreacion(as_ipRemote);
								li_imagenes.setIdTurno(lth_turnoHistoria.getIdTurno());
								li_imagenes.setCodigoVerificacion(
								    li_DAO.generarCodigoConvenio(lth_turnoHistoria.getIdTurnoHistoria())
								);

								ll_secuencia = li_DAO.insertOrUpdate(li_imagenes, true, true);

								if(ll_secuencia <= 0)
									throw new B2BException(ErrorKeys.NO_INSERTO_IMAGENES);

								lds_documentosSalida.setIdImagen(NumericUtils.getLongWrapper(ll_secuencia));
								lds_documentosSalida.setTipo(TipoArchivoCommon.SOLICITUD);
								lds_documentosSalida.setEstado(EstadoCommon.ACTIVO);
								lds_documentosSalida.setIdSolicitud(ls_solicitud.getIdSolicitud());
								lds_documentosSalida.setIdTipoDocumental(TipoDocumentalCommon.RESUMEN_DE_LA_SOLICITUD);
								lds_documentosSalida.setRepositorio(RepositorioCommon.FINAL);
								lds_documentosSalida.setIdUsuarioCreacion(as_usuario);
								lds_documentosSalida.setIpCreacion(as_ipRemote);

								DaoCreator.getDocumentosSalidaDAO(ldm_manager).insertOrUpdate(
								    lds_documentosSalida, true
								);

								acdb_criteriosDeBusqueda.setArchivoPdf(lba_archivoNir);
							}
						}
					}
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("terminarProcesoConsultas", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return acdb_criteriosDeBusqueda;
	}

	/**
	 * Metodo encargado de terminar etapa de buscar antiguo sistema y liquidar.
	 *
	 * @param abas_parametros Argumento de tipo <code>BuscarAntiguoSistema</code> que contiene los criterios a terminar y liquidar.
	 * @param as_usuario Argumento de tipo <code>String</code> que contiene el id del usuario que realiza la operación.
	 * @param as_ipRemote Argumento de tipo <code>String</code> que contiene la ip del usuario que realiza la operación.
	 * @throws B2BException Señala que se ha generado una excepción
	 */
	public synchronized void terminarProcesoCopiasAntSistema(
	    BuscarAntiguoSistema abas_parametros, String as_usuario, String as_ipRemote
	)
	    throws B2BException
	{
		DAOManager ldm_manager;

		ldm_manager = DaoManagerFactory.getDAOManager();

		try
		{
			if(abas_parametros != null)
			{
				Solicitud ls_solicitud;

				ls_solicitud = abas_parametros.getSolicitud();

				if(ls_solicitud != null)
				{
					{
						Liquidacion ll_liquidacion;

						ll_liquidacion = new Liquidacion();

						ll_liquidacion.setSolicitud(ls_solicitud);
						ll_liquidacion.setIdCondicion(IdentificadoresCommon.LIQUIDAR);
						ll_liquidacion.setIdUsuarioCreacion(as_usuario);
						ll_liquidacion.setIpCreacion(as_usuario);

						procLiquidacion(ll_liquidacion, ldm_manager, as_usuario, as_ipRemote);
					}

					{
						Registro lr_parametros;

						lr_parametros = new Registro();

						lr_parametros.setIdUsuarioCreacion(as_usuario);
						lr_parametros.setSolicitud(ls_solicitud);
						lr_parametros.setIdCondicion(IdentificadoresCommon.LIQUIDAR);
						lr_parametros.setTipoRecibo(IdentificadoresCommon.RECIBO_LIQUIDACION);

						getRegistroBusiness()
							    .generarReciboLiquidacion(lr_parametros, true, as_usuario, null, null, ldm_manager);
					}

					{
						TurnoHistoria lth_turnoHistoria;
						MotivoTramite lmt_motivoTramite;

						lth_turnoHistoria     = new TurnoHistoria();
						lmt_motivoTramite     = new MotivoTramite();

						lth_turnoHistoria.setIdTurnoHistoria(abas_parametros.getIdTurnoHistoria());
						lmt_motivoTramite.setIdEtapaOrigen(EtapaCommon.ID_ETAPA_ANALISTA_DE_COPIAS);

						{
							long ll_idMotivo;

							ll_idMotivo = abas_parametros.getIdMotivo();

							if(ll_idMotivo == 20L)
							{
								Solicitud ls_solicitudConsultada;

								ls_solicitudConsultada = DaoCreator.getSolicitudDAO(ldm_manager).findById(ls_solicitud);

								if(ls_solicitudConsultada != null)
								{
									String ls_medioANotificar;

									ls_medioANotificar = ls_solicitudConsultada.getIdAutorizacionNotificacion();

									if(StringUtils.isValidString(ls_medioANotificar))
									{
										if(ls_medioANotificar.equalsIgnoreCase(MedioNotificarCommon.CORREO_ELECTRONICO))
											ll_idMotivo = MotivoTramiteCommon.ETAPA_350_GENERAR_LIQUIDACION;
										else
											ll_idMotivo = MotivoTramiteCommon.GENERAR_LIQUIDACION_ENTREGA_ORIP;
									}
								}
							}

							lmt_motivoTramite.setIdMotivo(ll_idMotivo);
						}

						terminarTurnoHistoriaYCrearEtapa(
						    lth_turnoHistoria, ldm_manager, lmt_motivoTramite, as_usuario, as_ipRemote,
						    EstadoCommon.TERMINADA
						);
					}
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("terminarProcesoCopiasAntSistema", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}
	}

	/**
	 * Metodo encargado de validar si la información capturada en expedicón de copias es diferente en buscar antiguo sistema.
	 *
	 * @param abas_parametros Argumento de tipo <code>BuscarAntiguoSistema</code> que contiene los criterios a validar.
	 * @return Variable de tipo <code>boolean</code> que determina si se modificó algún valor o no.
	 * @throws B2BException Señala que se ha generado una excepción.
	 */
	public synchronized boolean validaModificacionDatosAntiguoSistema(BuscarAntiguoSistema abas_parametros)
	    throws B2BException
	{
		boolean lb_modificado;

		lb_modificado = false;

		if(abas_parametros != null)
		{
			DAOManager ldm_manager;

			ldm_manager = DaoManagerFactory.getDAOManager();

			try
			{
				Long ll_idTurnoHistoria;

				ll_idTurnoHistoria = abas_parametros.getIdTurnoHistoria();

				if(NumericUtils.isValidLong(ll_idTurnoHistoria))
				{
					TurnoHistoria lth_turnoHistoria;

					lth_turnoHistoria = DaoCreator.getTurnoHistoriaDAO(ldm_manager).findById(ll_idTurnoHistoria);

					if(lth_turnoHistoria != null)
					{
						String ls_idSolicitud;

						ls_idSolicitud = lth_turnoHistoria.getIdSolicitud();

						if(StringUtils.isValidString(ls_idSolicitud))
						{
							String ls_idTipoCriterioBusqueda;

							ls_idTipoCriterioBusqueda = abas_parametros.getIdTipoCriterioBusqueda();

							if(StringUtils.isValidString(ls_idTipoCriterioBusqueda))
							{
								CriteriosDeBusqueda             lcb_criterioDeBusqueda;
								Collection<CriteriosDeBusqueda> lccb_criteriosDeBusqueda;

								lcb_criterioDeBusqueda = new CriteriosDeBusqueda();

								lcb_criterioDeBusqueda.setIdSolicitud(ls_idSolicitud);
								lcb_criterioDeBusqueda.setIdTipoCriterio(ls_idTipoCriterioBusqueda);

								lccb_criteriosDeBusqueda = DaoCreator.getCriterioBusquedaDAO(ldm_manager)
										                                 .buscarCriteriosPorsolicitudTipoCriterio(
										    lcb_criterioDeBusqueda
										);

								if(CollectionUtils.isValidCollection(lccb_criteriosDeBusqueda))
								{
									CriteriosDeBusqueda lcdb_criterioBusqueda;

									lcdb_criterioBusqueda = lccb_criteriosDeBusqueda.iterator().next();

									if(lcdb_criterioBusqueda != null)
									{
										Collection<CriteriosDeBusqueda> lccdb_detalleCriterioBusqueda;

										lccdb_detalleCriterioBusqueda = DaoCreator.getDetalleCriterioBusquedaDAO(
											    ldm_manager
											).consultarDetallesAgregados(lcdb_criterioBusqueda);

										if(CollectionUtils.isValidCollection(lccdb_detalleCriterioBusqueda))
										{
											Iterator<CriteriosDeBusqueda> licdb_iterator;

											licdb_iterator = lccdb_detalleCriterioBusqueda.iterator();

											while(licdb_iterator.hasNext() && !lb_modificado)
											{
												CriteriosDeBusqueda lcdb_detalleIterador;

												lcdb_detalleIterador = licdb_iterator.next();

												if(lcdb_detalleIterador != null)
												{
													String ls_idCampoCriterioBusqueda;
													String ls_valorCampo;
													Long   ll_valorCampo;

													ls_idCampoCriterioBusqueda     = lcdb_detalleIterador
															.getIdCampoCriterioBusqueda();
													ls_valorCampo                  = StringUtils.getStringNotNull(
														    lcdb_detalleIterador.getValorCampo()
														);
													ll_valorCampo                  = NumericUtils.getLongWrapper(
														    ls_valorCampo
														);

													if(StringUtils.isValidString(ls_idCampoCriterioBusqueda))
													{
														if(
														    ls_idTipoCriterioBusqueda.equalsIgnoreCase(
															        TipoCriterioBusquedaCommon.ANTIGUO_SISTEMA
															    )
														)
														{
															DetalleAntSistemaUI ldasu_detalleAntSistema;

															ldasu_detalleAntSistema = abas_parametros
																	.getDetalleAntSistema();

															if(ldasu_detalleAntSistema != null)
															{
																if(
																    ls_idCampoCriterioBusqueda.equalsIgnoreCase(
																	        CampoCriterioBusquedaCommon.ANTIGUO_SISTEMA_LIBRO
																	    )
																)
																	lb_modificado = ll_valorCampo.compareTo(
																		    ldasu_detalleAntSistema.getIdLibroAntSistema()
																		) != 0L;
																else if(
																    ls_idCampoCriterioBusqueda.equalsIgnoreCase(
																	        CampoCriterioBusquedaCommon.ANTIGUO_SISTEMA_TOMO
																	    )
																)
																	lb_modificado = !ls_valorCampo.equalsIgnoreCase(
																		    StringUtils.getStringNotNull(
																		        ldasu_detalleAntSistema.getTomo()
																		    )
																		);
																else if(
																    ls_idCampoCriterioBusqueda.equalsIgnoreCase(
																	        CampoCriterioBusquedaCommon.ANTIGUO_SISTEMA_FOLIO
																	    )
																)
																	lb_modificado = !ls_valorCampo.equalsIgnoreCase(
																		    StringUtils.getStringNotNull(
																		        ldasu_detalleAntSistema.getFolio()
																		    )
																		);
																else if(
																    ls_idCampoCriterioBusqueda.equalsIgnoreCase(
																	        CampoCriterioBusquedaCommon.ANTIGUO_SISTEMA_TIPO_DE_PARTIDA
																	    )
																)
																	lb_modificado = !ls_valorCampo.equalsIgnoreCase(
																		    StringUtils.getStringNotNull(
																		        ldasu_detalleAntSistema.getPartida()
																		    )
																		);
																else if(
																    ls_idCampoCriterioBusqueda.equalsIgnoreCase(
																	        CampoCriterioBusquedaCommon.ANTIGUO_SISTEMA_NUMERO_PARTIDA
																	    )
																)
																	lb_modificado = ll_valorCampo.compareTo(
																		    ldasu_detalleAntSistema.getNumeroPartida()
																		) != 0L;
																else if(
																    ls_idCampoCriterioBusqueda.equalsIgnoreCase(
																	        CampoCriterioBusquedaCommon.ANTIGUO_SISTEMA_MUNICIPIO
																	    )
																)
																	lb_modificado = !ls_valorCampo.equalsIgnoreCase(
																		    StringUtils.getStringNotNull(
																		        ldasu_detalleAntSistema
																			        .getIdMunicipioTomo()
																		    )
																		);
																else if(
																    ls_idCampoCriterioBusqueda.equalsIgnoreCase(
																	        CampoCriterioBusquedaCommon.ANTIGUO_SISTEMA_ANIO
																	    )
																)
																	lb_modificado = !ls_valorCampo.equalsIgnoreCase(
																		    StringUtils.getStringNotNull(
																		        ldasu_detalleAntSistema.getAnio()
																		    )
																		);
																else if(
																    ls_idCampoCriterioBusqueda.equalsIgnoreCase(
																	        CampoCriterioBusquedaCommon.ANTIGUO_SISTEMA_CIRCULO_REGISTRAL
																	    )
																)
																	lb_modificado = !ls_valorCampo.equalsIgnoreCase(
																		    StringUtils.getStringNotNull(
																		        ldasu_detalleAntSistema.getIdCirculo()
																		    )
																		);
																else if(
																    ls_idCampoCriterioBusqueda.equalsIgnoreCase(
																	        CampoCriterioBusquedaCommon.ANTIGUO_SISTEMA_TIPO_DE_PREDIO
																	    )
																)
																	lb_modificado = !ls_valorCampo.equalsIgnoreCase(
																		    StringUtils.getStringNotNull(
																		        ldasu_detalleAntSistema.getTipoPredio()
																		    )
																		);
															}
														}
														else if(
														    ls_idTipoCriterioBusqueda.equalsIgnoreCase(
															        TipoCriterioBusquedaCommon.DOCUMENTO_ANTIGUO_SISTEMA
															    )
														)
														{
															ConsultaCriteriosCalificacionDocumento lcccd_criteriosDocumento;

															lcccd_criteriosDocumento = abas_parametros
																	.getConsultaCriteriosCalificacionDocumento();

															if(lcccd_criteriosDocumento != null)
															{
																Documento ld_documento;

																ld_documento = lcccd_criteriosDocumento.getDocumento();

																if(ld_documento != null)
																{
																	if(
																	    ls_idCampoCriterioBusqueda.equalsIgnoreCase(
																		        CampoCriterioBusquedaCommon.DOCUMENTO_COPIAS_CIRCULO
																		    )
																	)
																	{
																		AnotacionPredio lap_anotacionPredio;

																		lap_anotacionPredio = lcccd_criteriosDocumento
																				.getAnotacionPredio();

																		if(lap_anotacionPredio != null)
																			lb_modificado = !ls_valorCampo
																					.equalsIgnoreCase(
																					    StringUtils.getStringNotNull(
																					        lap_anotacionPredio
																					        .getIdCirculo()
																					    )
																					);
																	}
																	else if(
																	    ls_idCampoCriterioBusqueda.equalsIgnoreCase(
																		        CampoCriterioBusquedaCommon.DOCUMENTO_COPIAS_TIPO_DOCUMENTO
																		    )
																	)
																		lb_modificado = !ls_valorCampo.equalsIgnoreCase(
																			    StringUtils.getStringNotNull(
																			        ld_documento.getIdTipoDocumento()
																			    )
																			);
																	else if(
																	    ls_idCampoCriterioBusqueda.equalsIgnoreCase(
																		        CampoCriterioBusquedaCommon.DOCUMENTO_COPIAS_NUMERO_DOCUMENTO
																		    )
																	)
																		lb_modificado = !ls_valorCampo.equalsIgnoreCase(
																			    StringUtils.getStringNotNull(
																			        ld_documento.getNumero()
																			    )
																			);
																	else if(
																	    ls_idCampoCriterioBusqueda.equalsIgnoreCase(
																		        CampoCriterioBusquedaCommon.DOCUMENTO_COPIAS_FECHA_DOCUMENTO
																		    )
																	)
																		lb_modificado = !ls_valorCampo.equalsIgnoreCase(
																			    StringUtils.getString(
																			        ld_documento.getFechaDocumento(),
																			        "dd/MM/yyyy"
																			    )
																			);
																	else if(
																	    ls_idCampoCriterioBusqueda.equalsIgnoreCase(
																		        CampoCriterioBusquedaCommon.DOCUMENTO_COPIAS_TIPO_DE_OFICINA
																		    )
																	)
																		lb_modificado = !ls_valorCampo.equalsIgnoreCase(
																			    StringUtils.getStringNotNull(
																			        ld_documento.getIdTipoOficina()
																			    )
																			);
																	else if(
																	    ls_idCampoCriterioBusqueda.equalsIgnoreCase(
																		        CampoCriterioBusquedaCommon.DOCUMENTO_COPIAS_PAIS
																		    )
																	)
																		lb_modificado = !ls_valorCampo.equalsIgnoreCase(
																			    StringUtils.getStringNotNull(
																			        ld_documento.getIdPais()
																			    )
																			);
																	else if(
																	    ls_idCampoCriterioBusqueda.equalsIgnoreCase(
																		        CampoCriterioBusquedaCommon.DOCUMENTO_COPIAS_DEPARTAMENTO
																		    )
																	)
																		lb_modificado = !ls_valorCampo.equalsIgnoreCase(
																			    StringUtils.getStringNotNull(
																			        ld_documento.getIdDepartamento()
																			    )
																			);
																	else if(
																	    ls_idCampoCriterioBusqueda.equalsIgnoreCase(
																		        CampoCriterioBusquedaCommon.DOCUMENTO_COPIAS_MUNICIPIO
																		    )
																	)
																		lb_modificado = !ls_valorCampo.equalsIgnoreCase(
																			    StringUtils.getStringNotNull(
																			        ld_documento.getIdMunicipio()
																			    )
																			);
																	else if(
																	    ls_idCampoCriterioBusqueda.equalsIgnoreCase(
																		        CampoCriterioBusquedaCommon.DOCUMENTO_COPIAS_OFICINA_DE_ORIGEN
																		    )
																	)
																		lb_modificado = !ls_valorCampo.equalsIgnoreCase(
																			    StringUtils.getStringNotNull(
																			        ld_documento.getIdOficinaOrigen()
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
							}
						}
					}
				}
			}
			catch(B2BException lb2be_e)
			{
				ldm_manager.setRollbackOnly();

				clh_LOGGER.error("cargarInformacionAntiguoSistema", lb2be_e);

				throw lb2be_e;
			}
			finally
			{
				ldm_manager.close();
			}
		}

		return lb_modificado;
	}

	/**
	 * Metodo encargado de validar la cantidad de consultas y la cantidad de detalles cargados.
	 *
	 * @param acdb_criteriosDeBusqueda correspondiente al valor del tipo de objeto CriteriosDeBusqueda
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public synchronized void validarCantidadDetalleCriterioBusqueda(CriteriosDeBusqueda acdb_criteriosDeBusqueda)
	    throws B2BException
	{
		DAOManager ldm_manager;

		ldm_manager = DaoManagerFactory.getDAOManager();

		try
		{
			validarCantidadDetalleCriterioBusqueda(acdb_criteriosDeBusqueda, ldm_manager);
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("validarCantidadDetalleCriterioBusqueda", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}
	}

	/**
	 * Metodo encargado de validar la cantidad de consultas y la cantidad de detalles cargados.
	 *
	 * @param acc_camposConsulta Argumento de tipo CriteriosDeBusqueda que contiene la solicitud
	 * para consultar la cantidad de consultas y la cantidad de detalles cargados
	 * @param adm_manager Argumento de tipo DaoManager que permite la conexión a la base de datos.
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public synchronized void validarCantidadDetalleCriterioBusqueda(
	    CriteriosDeBusqueda acc_camposConsulta, DAOManager adm_manager
	)
	    throws B2BException
	{
		if(acc_camposConsulta != null)
		{
			Collection<CriteriosDeBusqueda> lccdb_criteriosDeBusqueda;

			lccdb_criteriosDeBusqueda = DaoCreator.getCriterioBusquedaDAO(adm_manager)
					                                  .buscarCriteriosPorsolicitud(acc_camposConsulta);

			if(CollectionUtils.isValidCollection(lccdb_criteriosDeBusqueda))
			{
				DetalleCriterioBusquedaDAO ldcbd_DAO;
				TipoCriterioBusquedaDAO    ltcbd_DAO;

				ldcbd_DAO     = DaoCreator.getDetalleCriterioBusquedaDAO(adm_manager);
				ltcbd_DAO     = DaoCreator.getTipoCriterioBusquedaDAO(adm_manager);

				for(CriteriosDeBusqueda lcdb_iterador : lccdb_criteriosDeBusqueda)
				{
					if(lcdb_iterador != null)
					{
						long ll_maximo;
						long ll_cantidadConsultas;

						ll_maximo                = ldcbd_DAO.findMaxConsecutivoConsultaBySolicitud(lcdb_iterador);
						ll_cantidadConsultas     = NumericUtils.getLong(lcdb_iterador.getCantidadConsultas());

						if(ll_maximo != ll_cantidadConsultas)
						{
							CriteriosDeBusqueda lcdb_tipoCriterio;

							lcdb_tipoCriterio = ltcbd_DAO.findById(lcdb_iterador);

							if(lcdb_tipoCriterio != null)
							{
								int      li_contador;
								Object[] loa_messageArgs;

								li_contador         = 0;
								loa_messageArgs     = new String[3];

								loa_messageArgs[li_contador++]     = lcdb_tipoCriterio.getDescripcion();
								loa_messageArgs[li_contador++]     = StringUtils.getString(ll_cantidadConsultas);
								loa_messageArgs[li_contador++]     = StringUtils.getString(ll_maximo);

								throw new B2BException(
								    ErrorKeys.ERROR_CANTIDAD_NO_COINCIDE_CON_DETALLE, loa_messageArgs
								);
							}
						}
					}
				}
			}
		}
	}

	/**
	 * Metodo encargado de validar si un tipo de criterio búsqueda existe en criterios de busqueda.
	 *
	 * @param al_idTurnoHistoria Argumento de tipo <code>Long</code> que contiene el id del turno historia a consultar.
	 * @return Retorna una variable de tipo <code>boolean</code> que determina si se encontraron resultados para la búsqueda.
	 * @throws B2BException Señala que se ha generado una excepción.
	 */
	public synchronized boolean validarCriterioPorTipoCriterio(
	    Long al_idTurnoHistoria, String as_idTipoCriterioBusqueda
	)
	    throws B2BException
	{
		boolean lb_criterioExiste;

		lb_criterioExiste = false;

		if(NumericUtils.isValidLong(al_idTurnoHistoria))
		{
			DAOManager ldm_manager;

			ldm_manager = DaoManagerFactory.getDAOManager();

			try
			{
				TurnoHistoria lth_turnoHistoria;

				lth_turnoHistoria = DaoCreator.getTurnoHistoriaDAO(ldm_manager).findById(al_idTurnoHistoria);

				if(lth_turnoHistoria != null)
				{
					String ls_idSolicitud;

					ls_idSolicitud = lth_turnoHistoria.getIdSolicitud();

					if(StringUtils.isValidString(ls_idSolicitud))
					{
						CriteriosDeBusqueda             lcdb_criterioDeBusqueda;
						Collection<CriteriosDeBusqueda> lccdb_criterios;

						lcdb_criterioDeBusqueda = new CriteriosDeBusqueda();

						lcdb_criterioDeBusqueda.setIdSolicitud(ls_idSolicitud);
						lcdb_criterioDeBusqueda.setIdTipoCriterio(as_idTipoCriterioBusqueda);

						lccdb_criterios     = DaoCreator.getCriterioBusquedaDAO(ldm_manager)
								                            .buscarCriteriosPorsolicitudTipoCriterio(
								    lcdb_criterioDeBusqueda
								);

						lb_criterioExiste = CollectionUtils.isValidCollection(lccdb_criterios);
					}
				}
			}
			catch(B2BException lb2be_e)
			{
				ldm_manager.setRollbackOnly();

				clh_LOGGER.error("validarCriterioPorTipoCriterio", lb2be_e);

				throw lb2be_e;
			}
			finally
			{
				ldm_manager.close();
			}
		}

		return lb_criterioExiste;
	}

	/**
	 * Retorna el valor del objeto de String de la creacion del Nir
	 *
	 * @param as_userId correspondiente al valor del tipo de objeto String
	 * @param as_remoteIp correspondiente al valor del tipo de objeto String
	 * @return devuelve el valor de String
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	private synchronized String crearNirConsultas(String as_userId, String as_remoteIp)
	    throws B2BException
	{
		DAOManager ldm_manager;
		String     ls_nir;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		ls_nir          = null;

		try
		{
			Constantes lc_constantes;
			String     ls_constante;
			int        li_anio;

			lc_constantes     = new Constantes();
			li_anio           = Calendar.getInstance().get(Calendar.YEAR);
			ls_constante      = "CONSECUTIVO_NIR_" + li_anio;

			{
				ConstantesDAO lcd_DAO;
				BigInteger    lbi_consecutivo;
				boolean       lb_insert;

				lcd_DAO           = DaoCreator.getConstantesDAO(ldm_manager);
				lc_constantes     = lcd_DAO.findById(ls_constante);

				if(lc_constantes != null)
				{
					lb_insert           = false;
					lbi_consecutivo     = lc_constantes.getEntero();

					lc_constantes.setIdUsuarioModificacion(as_userId);
					lc_constantes.setIpModificacion(as_remoteIp);
				}
				else
				{
					lc_constantes       = new Constantes();
					lb_insert           = true;
					lbi_consecutivo     = null;

					lc_constantes.setIdConstante(ls_constante);
					lc_constantes.setIdUsuarioCreacion(as_userId);
					lc_constantes.setIpCreacion(as_remoteIp);
				}

				if(lbi_consecutivo == null)
					lbi_consecutivo = new BigInteger("0");

				lbi_consecutivo = new BigInteger(StringUtils.getString(lbi_consecutivo.add(new BigInteger("1"))));

				lc_constantes.setEntero(lbi_consecutivo);

				lcd_DAO.insertOrUpdate(lc_constantes, lb_insert);

				ls_nir = "SNR" + li_anio + String.format("%09d", lbi_consecutivo);
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("crearNir", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return ls_nir;
	}

	/**
	 * Metodo encargado de extraer los criterios de busqueda para retornar un objeto de tipo <code>DocumentoOWCC</code>.
	 *
	 * @param adm_manager Argumento de tipo <code>DAOManager</code> que permite la conexión a la base de datos.
	 * @param acc_camposConsulta Argumento de tipo <code>Collection</code> que contiene los criterios de busqueda a extraer.
	 * @return Objeto de tipo <code>DocumentoOWCC</code> que contiene los datos extraidos.
	 * @throws B2BException Se genera cuando se presenta una excepción.
	 */
	private DocumentoOWCC extraerCriteriosDocumentoOWCC(
	    DAOManager adm_manager, Collection<CamposConsulta> acc_camposConsulta
	)
	    throws B2BException
	{
		return extraerCriteriosDocumentoOWCC(adm_manager, acc_camposConsulta, null);
	}

	/**
	 * Metodo encargado de extraer los criterios de busqueda para retornar un objeto de tipo <code>DocumentoOWCC</code>.
	 *
	 * @param adm_manager Argumento de tipo <code>DAOManager</code> que permite la conexión a la base de datos.
	 * @param acc_camposConsulta Argumento de tipo <code>Collection</code> que contiene los criterios de busqueda a extraer.
	 * @param ado_documentoCargado Argumento de tipo <code>DocumentoOWCC</code> que contiene los criterios de busqueda extraidos previamente.
	 * @return Objeto de tipo <code>DocumentoOWCC</code> que contiene los datos extraidos.
	 * @throws B2BException Se genera cuando se presenta una excepción.
	 */
	private DocumentoOWCC extraerCriteriosDocumentoOWCC(
	    DAOManager adm_manager, Collection<CamposConsulta> acc_camposConsulta, DocumentoOWCC ado_documentoCargado
	)
	    throws B2BException
	{
		DocumentoOWCC ldo_documentoOWCC;

		ldo_documentoOWCC = null;

		try
		{
			if(CollectionUtils.isValidCollection(acc_camposConsulta))
			{
				String ls_idPais;
				String ls_idDepartamento;
				String ls_idOficinaOrigen;
				String ls_idOrip;

				SolicitudDAO     lsd_DAO;
				PaisDAO          lpd_DAO;
				DepartamentoDAO  ldd_DAO;
				MunicipioDAO     lmd_DAO;
				OficinaOrigenDAO lood_DAO;

				ls_idPais              = null;
				ls_idDepartamento      = null;
				ls_idOficinaOrigen     = null;
				ls_idOrip              = null;

				lsd_DAO      = DaoCreator.getSolicitudDAO(adm_manager);
				lpd_DAO      = DaoCreator.getPaisDAO(adm_manager);
				ldd_DAO      = DaoCreator.getDepartamentoDAO(adm_manager);
				lmd_DAO      = DaoCreator.getMunicipioDAO(adm_manager);
				lood_DAO     = DaoCreator.getOficinaOrigenDAO(adm_manager);

				ldo_documentoOWCC = (ado_documentoCargado != null) ? ado_documentoCargado : new DocumentoOWCC();

				for(CamposConsulta lcc_iterador : acc_camposConsulta)
				{
					if(lcc_iterador != null)
					{
						String ls_idTipoCriterioBusqueda;
						String ls_idCampoCriterioBusqueda;
						String ls_tipoDato;
						String ls_valorCampo;

						boolean lb_fecha;

						ls_idTipoCriterioBusqueda      = lcc_iterador.getIdTipoCriterioBusqueda();
						ls_idCampoCriterioBusqueda     = lcc_iterador.getIdCampoCriterioBusqueda();
						ls_tipoDato                    = lcc_iterador.getTipoDato();

						lb_fecha     = StringUtils.isValidString(ls_tipoDato)
								&& ls_tipoDato.equalsIgnoreCase(TipoDatoCommon.FECHA);

						ls_valorCampo = lb_fecha
							? StringUtils.getString(lcc_iterador.getValorCampoFecha(), "dd/MM/yyyy")
							: lcc_iterador.getValorCampo();

						if(
						    StringUtils.isValidString(ls_idTipoCriterioBusqueda)
							    && StringUtils.isValidString(ls_idCampoCriterioBusqueda)
							    && StringUtils.isValidString(ls_valorCampo)
						)
						{
							if(
							    ls_idTipoCriterioBusqueda.equalsIgnoreCase(TipoCriterioBusquedaCommon.NIR)
								    && ls_idCampoCriterioBusqueda.equalsIgnoreCase(CampoCriterioBusquedaCommon.NIR_NIR)
							)
							{
								Solicitud ls_solicitud;

								ls_solicitud = new Solicitud();

								ls_solicitud.setNir(ls_valorCampo);

								ls_solicitud = lsd_DAO.findByNir(ls_solicitud);

								if(ls_solicitud != null)
								{
									Long ll_estadoSolicitud;

									ll_estadoSolicitud = ls_solicitud.getEstadoSolicitud();

									if(
									    !NumericUtils.isValidLong(ll_estadoSolicitud)
										    || ((ll_estadoSolicitud.compareTo(
										        Long.valueOf(TipoEstadoSolicitudCommon.EN_TRAMITE)
										    ) != 0)
										    && (ll_estadoSolicitud.compareTo(
										        Long.valueOf(TipoEstadoSolicitudCommon.FINALIZADO)
										    ) != 0))
									)
										throw new B2BException(ErrorKeys.ERROR_NIR_DEBE_ESTAR_TRAMITE_O_FINALIZADO);
								}
								else
									throw new B2BException(ErrorKeys.ERROR_NIR_INVALIDO);

								ldo_documentoOWCC.setNir(ls_valorCampo);
							}
							else if(ls_idTipoCriterioBusqueda.equalsIgnoreCase(TipoCriterioBusquedaCommon.TURNO))
							{
								if(ls_idCampoCriterioBusqueda.equalsIgnoreCase(CampoCriterioBusquedaCommon.TURNO_TURNO))
									ldo_documentoOWCC.setTurno(ls_valorCampo);

								if(ls_idCampoCriterioBusqueda.equalsIgnoreCase(CampoCriterioBusquedaCommon.TURNO_NIR))
									ldo_documentoOWCC.setNir(ls_valorCampo);
							}
							else if(
							    ls_idTipoCriterioBusqueda.equalsIgnoreCase(TipoCriterioBusquedaCommon.DOCUMENTO_COPIAS)
								    || ls_idTipoCriterioBusqueda.equalsIgnoreCase(
								        TipoCriterioBusquedaCommon.DOCUMENTO_ANTIGUO_SISTEMA
								    )
							)
							{
								if(
								    ls_idCampoCriterioBusqueda.equalsIgnoreCase(
									        CampoCriterioBusquedaCommon.DOCUMENTO_COPIAS_CIRCULO
									    )
								)
									ldo_documentoOWCC.setIdOrip(ls_valorCampo);

								if(
								    ls_idCampoCriterioBusqueda.equalsIgnoreCase(
									        CampoCriterioBusquedaCommon.DOCUMENTO_COPIAS_TIPO_DOCUMENTO
									    )
								)
									ldo_documentoOWCC.setDocType(ls_valorCampo);

								if(
								    ls_idCampoCriterioBusqueda.equalsIgnoreCase(
									        CampoCriterioBusquedaCommon.DOCUMENTO_COPIAS_NUMERO_DOCUMENTO
									    )
								)
									ldo_documentoOWCC.setNumeroDoc(ls_valorCampo);

								if(
								    ls_idCampoCriterioBusqueda.equalsIgnoreCase(
									        CampoCriterioBusquedaCommon.DOCUMENTO_COPIAS_FECHA_DOCUMENTO
									    )
								)
									ldo_documentoOWCC.setFechaDocumento(DateUtils.getDate(ls_valorCampo, "dd/MM/yyyy"));

								if(
								    ls_idCampoCriterioBusqueda.equalsIgnoreCase(
									        CampoCriterioBusquedaCommon.DOCUMENTO_COPIAS_TIPO_DE_OFICINA
									    )
								)
									ldo_documentoOWCC.setTipoOficina(ls_valorCampo);

								if(
								    ls_idCampoCriterioBusqueda.equalsIgnoreCase(
									        CampoCriterioBusquedaCommon.DOCUMENTO_COPIAS_PAIS
									    )
								)
								{
									Pais lp_pais;

									lp_pais = lpd_DAO.findById(ls_valorCampo);

									if(lp_pais != null)
									{
										ls_idPais = ls_valorCampo;
										ldo_documentoOWCC.setNombrePais(lp_pais.getNombre());
									}
								}

								if(
								    ls_idCampoCriterioBusqueda.equalsIgnoreCase(
									        CampoCriterioBusquedaCommon.DOCUMENTO_COPIAS_DEPARTAMENTO
									    )
								)
								{
									Departamento ld_departamento;

									ld_departamento = ldd_DAO.findById(ls_idPais, ls_valorCampo);

									if(ld_departamento != null)
									{
										ls_idDepartamento = ls_valorCampo;
										ldo_documentoOWCC.setNombreDepartamento(ld_departamento.getNombre());
									}
								}

								if(
								    ls_idCampoCriterioBusqueda.equalsIgnoreCase(
									        CampoCriterioBusquedaCommon.DOCUMENTO_COPIAS_MUNICIPIO
									    )
								)
								{
									Municipio lm_municipio;

									lm_municipio = lmd_DAO.findById(ls_idPais, ls_idDepartamento, ls_valorCampo);

									if(lm_municipio != null)
										ldo_documentoOWCC.setNombreMunicipio(lm_municipio.getNombre());
								}

								if(
								    ls_idCampoCriterioBusqueda.equalsIgnoreCase(
									        CampoCriterioBusquedaCommon.DOCUMENTO_COPIAS_OFICINA_DE_ORIGEN
									    )
								)
								{
									BigDecimal lbd_max;

									lbd_max = lood_DAO.findMaxVersion(ls_valorCampo);

									if(NumericUtils.isValidBigDecimal(lbd_max))
									{
										OficinaOrigen loo_oficinaOrigen;

										loo_oficinaOrigen = lood_DAO.findById(ls_valorCampo, lbd_max);

										if(loo_oficinaOrigen != null)
										{
											ls_idOficinaOrigen = ls_valorCampo;
											ldo_documentoOWCC.setEntidadOrigen(loo_oficinaOrigen.getNombre());
										}
									}
								}

								if(
								    ls_idCampoCriterioBusqueda.equalsIgnoreCase(
									        CampoCriterioBusquedaCommon.DOCUMENTO_COPIAS_VERSION_OFICINA_DE_ORIGEN
									    )
								)
								{
									OficinaOrigen loo_oficinaOrigen;

									loo_oficinaOrigen = lood_DAO.findById(
										    ls_idOficinaOrigen,
										    NumericUtils.getBigDecimal(NumericUtils.getDoubleWrapper(ls_valorCampo))
										);

									if(loo_oficinaOrigen != null)
										ldo_documentoOWCC.setEntidadOrigen(loo_oficinaOrigen.getNombre());
								}
							}
							else if(
							    ls_idTipoCriterioBusqueda.equalsIgnoreCase(TipoCriterioBusquedaCommon.MATRICULA_COPIAS)
							)
							{
								if(
								    ls_idCampoCriterioBusqueda.equalsIgnoreCase(
									        CampoCriterioBusquedaCommon.MATRICULA_COPIAS_CIRCULO
									    )
								)
								{
									ls_idOrip = ls_valorCampo;
									ldo_documentoOWCC.setIdOrip(ls_valorCampo);
								}

								if(
								    ls_idCampoCriterioBusqueda.equalsIgnoreCase(
									        CampoCriterioBusquedaCommon.MATRICULA_COPIAS_MATRICULA
									    )
								)
								{
									if(!StringUtils.isValidString(ls_idOrip))
										throw new B2BException(ErrorKeys.DEBE_SELECCIONAR_CIRCULO_REGISTRAL);

									if(!StringUtils.isValidString(ls_valorCampo))
										throw new B2BException(ErrorKeys.DEBE_DIGITAR_MATRICULA);

									{
										PredioRegistro lpr_predioRegistro;
										boolean        lb_errorEstado;

										lpr_predioRegistro     = DaoCreator.getPredioRegistroDAO(adm_manager)
												                               .findByCirculoMatricula(
												    ls_idOrip, NumericUtils.getLong(ls_valorCampo)
												);
										lb_errorEstado         = lpr_predioRegistro == null;

										if(!lb_errorEstado)
										{
											String ls_predioDefinitivo;

											ls_predioDefinitivo     = lpr_predioRegistro.getPredioDefinitivo();

											lb_errorEstado = !StringUtils.isValidString(ls_predioDefinitivo)
													|| !ls_predioDefinitivo.equalsIgnoreCase(EstadoCommon.D);
										}

										if(lb_errorEstado)
										{
											Object[] lsa_object;

											lsa_object        = new String[1];
											lsa_object[0]     = ls_idOrip + IdentificadoresCommon.SIMBOLO_GUION
												+ ls_valorCampo;

											throw new B2BException(ErrorKeys.PREDIO_DEFINITIVO_COPIAS, lsa_object);
										}

										{
											String ls_predioInconsistente;

											ls_predioInconsistente = lpr_predioRegistro.getPredioInconsistente();

											if(
											    StringUtils.isValidString(ls_predioInconsistente)
												    && ls_predioInconsistente.equals(EstadoCommon.S)
											)
											{
												Object[] lsa_object;

												lsa_object        = new String[1];
												lsa_object[0]     = ls_idOrip + IdentificadoresCommon.SIMBOLO_GUION
													+ ls_valorCampo;

												throw new B2BException(
												    ErrorKeys.MATRICULA_ESTADO_INCONSISTENTE, lsa_object
												);
											}
										}
									}

									ldo_documentoOWCC.setMatriculas(
									    ls_idOrip + IdentificadoresCommon.SIMBOLO_GUION + ls_valorCampo
									);
								}
							}
						}
					}
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			throw lb2be_e;
		}

		return ldo_documentoOWCC;
	}

	/**
	 * Tablas dinamicas.
	 *
	 * @param ldb_builder correspondiente al valor de builder
	 * @param lcs_titulosColumnas correspondiente al valor de titulos columnas
	 * @param ls_tituloTabla correspondiente al valor de titulo tabla
	 * @param li_tamanoLetra correspondiente al valor de tamano letra
	 * @param ll_porcentajetablas correspondiente al valor de porcentajetablas
	 * @param acmss_mss correspondiente al valor de valores finales
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	private void tablasDinamicas(
	    DocumentBuilder ldb_builder, Collection<String> lcs_titulosColumnas, String ls_tituloTabla, int li_tamanoLetra,
	    long ll_porcentajetablas, Collection<Map<String, String>> acmss_mss
	)
	    throws B2BException
	{
		try
		{
			{
				ldb_builder.insertCell();
				ldb_builder.getParagraphFormat().setAlignment(ParagraphAlignment.CENTER);
				ldb_builder.setBold(true);
				ldb_builder.getFont().setName("arial");
				ldb_builder.getFont().setUnderline(0);
				ldb_builder.getFont().setSize(li_tamanoLetra + 4);
				ldb_builder.getCellFormat().setPreferredWidth(PreferredWidth.fromPercent(ll_porcentajetablas));
				ldb_builder.getCellFormat().setHorizontalMerge(CellMerge.FIRST);
				ldb_builder.write(ls_tituloTabla);
			}

			if(CollectionUtils.isValidCollection(lcs_titulosColumnas))
			{
				for(int i = 0; i < lcs_titulosColumnas.size(); i++)
				{
					ldb_builder.insertCell();
					ldb_builder.getCellFormat().setHorizontalMerge(CellMerge.PREVIOUS);
				}

				ldb_builder.endRow();

				for(String ls_titulo : lcs_titulosColumnas)
				{
					if(StringUtils.isValidString(ls_titulo))
					{
						if(!ls_titulo.equalsIgnoreCase("NUPRE"))
						{
							ldb_builder.insertCell();
							ldb_builder.getParagraphFormat().setAlignment(ParagraphAlignment.CENTER);
							ldb_builder.setBold(true);
							ldb_builder.getFont().setName("arial");
							ldb_builder.getFont().setUnderline(0);
							ldb_builder.getFont().setSize(li_tamanoLetra + 1);
							ldb_builder.getCellFormat().setHorizontalMerge(CellMerge.NONE);
							ldb_builder.getCellFormat()
								           .setPreferredWidth(PreferredWidth.fromPercent(ll_porcentajetablas));
							ldb_builder.write(ls_titulo.toUpperCase());
						}
					}
				}

				ldb_builder.endRow();
			}

			if(CollectionUtils.isValidCollection(acmss_mss))
			{
				for(Map<String, String> llhmss_temp : acmss_mss)
				{
					if(llhmss_temp != null)
					{
						for(Map.Entry<String, String> lmess_entry : llhmss_temp.entrySet())
						{
							String ls_tmp;

							ls_tmp = lmess_entry.getValue();

							ldb_builder.insertCell();
							ldb_builder.getParagraphFormat().setAlignment(ParagraphAlignment.CENTER);
							ldb_builder.getFont().setName("arial");
							ldb_builder.getFont().setUnderline(0);
							ldb_builder.getFont().setSize(li_tamanoLetra);
							ldb_builder.getCellFormat().setHorizontalMerge(CellMerge.NONE);
							ldb_builder.getCellFormat()
								           .setPreferredWidth(PreferredWidth.fromPercent(ll_porcentajetablas));
							ldb_builder.write(StringUtils.isValidString(ls_tmp) ? ls_tmp : "");
						}
					}
				}

				ldb_builder.endRow();
			}
		}
		catch(Exception le_e)
		{
			clh_LOGGER.error("tablasDinamicas", le_e);

			throw new B2BException(le_e.getLocalizedMessage());
		}
	}

	/**
	 * Retorna el valor del objeto de String[] para validar los campos de los criterios de busqueda
	 *
	 * @param adm_manager correspondiente al valor del tipo de objeto DAOManager
	 * @param as_idTipoCriterioBusqueda correspondiente al valor del tipo de objeto String
	 * @param as_idCampoCriterioBusqueda correspondiente al valor del tipo de objeto String
	 * @param as_valorCampo correspondiente al valor del tipo de objeto String
	 * @param as_idPais correspondiente al valor del tipo de objeto String
	 * @param as_idDepartamento correspondiente al valor del tipo de objeto String
	 * @param as_idMunicipio correspondiente al valor del tipo de objeto String
	 * @param lb_validar correspondiente al valor del tipo de objeto boolean
	 * @return devuelve el valor de String[]
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	private String[] validarCampoCriterioBusqueda(
	    DAOManager adm_manager, String as_idTipoCriterioBusqueda, String as_idCampoCriterioBusqueda,
	    String as_valorCampo, String as_idPais, String as_idDepartamento, String as_idMunicipio, boolean lb_validar
	)
	    throws B2BException
	{
		String[]                   lsa_datos;
		String                     ls_valor;
		InteresadoDocumentoTipoDAO lidtd_DAO;
		TipoDocumentoPublicoDAO    ltdpd_DAO;
		TipoOficinaDAO             ltod_DAO;
		PaisDAO                    lpd_DAO;
		DepartamentoDAO            ldd_DAO;
		MunicipioDAO               lmd_DAO;
		VeredaDAO                  lvd_DAO;
		PredioTipoDAO              lptd_DAO;
		TipoEjeDAO                 lted_DAO;
		CirculoRegistralDao        lcrd_DAO;
		LetraEjeDAO                lle_DAO;
		CoordenadaDAO              lc_DAO;

		lsa_datos     = new String[4];
		ls_valor      = null;
		lidtd_DAO     = DaoCreator.getInteresadoDocumentoTipoDAO(adm_manager);
		ltdpd_DAO     = DaoCreator.getTipoDocumentoPublicoDAO(adm_manager);
		ltod_DAO      = DaoCreator.getTipoOficinaDAO(adm_manager);
		lpd_DAO       = DaoCreator.getPaisDAO(adm_manager);
		ldd_DAO       = DaoCreator.getDepartamentoDAO(adm_manager);
		lmd_DAO       = DaoCreator.getMunicipioDAO(adm_manager);
		lvd_DAO       = DaoCreator.getVeredaDAO(adm_manager);
		lptd_DAO      = DaoCreator.getPredioTipoDao(adm_manager);
		lted_DAO      = DaoCreator.getTipoEjeDAO(adm_manager);
		lcrd_DAO      = DaoCreator.getCirculoRegistralDAO(adm_manager);
		lle_DAO       = DaoCreator.getLetraEjeDAO(adm_manager);
		lc_DAO        = DaoCreator.getCoordenadaDAO(adm_manager);
		ls_valor      = as_valorCampo;

		if(
		    as_idTipoCriterioBusqueda.equalsIgnoreCase(TipoCriterioBusquedaCommon.IDENTIFICACION)
			    && as_idCampoCriterioBusqueda.equalsIgnoreCase(
			        CampoCriterioBusquedaCommon.IDENTIFICACION_TIPO_DE_DOCUMENTO
			    )
		)
		{
			InteresadoDocumentoTipo lidt_tipoDoc;

			lidt_tipoDoc = new InteresadoDocumentoTipo();

			lidt_tipoDoc.setIdDocumentoTipo(as_valorCampo);

			lidt_tipoDoc     = lidtd_DAO.findById(lidt_tipoDoc);

			ls_valor = (lidt_tipoDoc != null) ? lidt_tipoDoc.getDescripcion() : (lb_validar ? null : as_valorCampo);
		}
		else if(as_idTipoCriterioBusqueda.equalsIgnoreCase(TipoCriterioBusquedaCommon.DOCUMENTO))
		{
			if(as_idCampoCriterioBusqueda.equalsIgnoreCase(CampoCriterioBusquedaCommon.DOCUMENTO_TIPO_DE_DOCUMENTO))
			{
				TipoDocumentoPublico ltdp_tipoDocP;

				ltdp_tipoDocP = new TipoDocumentoPublico();

				ltdp_tipoDocP.setIdTipoDocumento(as_valorCampo);

				ltdp_tipoDocP     = ltdpd_DAO.findById(ltdp_tipoDocP);

				ls_valor = (ltdp_tipoDocP != null) ? ltdp_tipoDocP.getNombre() : (lb_validar ? null : as_valorCampo);
			}
			else if(as_idCampoCriterioBusqueda.equalsIgnoreCase(CampoCriterioBusquedaCommon.DOCUMENTO_TIPO_DE_OFICINA))
			{
				TipoOficina lto_tipoOficina;

				lto_tipoOficina = new TipoOficina();

				lto_tipoOficina.setIdTipoOficina(as_valorCampo);

				lto_tipoOficina     = ltod_DAO.findById(lto_tipoOficina);

				ls_valor = (lto_tipoOficina != null) ? lto_tipoOficina.getNombre() : (lb_validar ? null : as_valorCampo);
			}
			else if(as_idCampoCriterioBusqueda.equalsIgnoreCase(CampoCriterioBusquedaCommon.DOCUMENTO_PAIS))
			{
				Pais lp_pais;

				lp_pais = new Pais();

				lp_pais.setIdPais(as_valorCampo);
				lsa_datos[0]     = as_valorCampo;

				lp_pais     = lpd_DAO.findById(lp_pais);

				ls_valor = (lp_pais != null) ? lp_pais.getNombre() : (lb_validar ? null : as_valorCampo);
			}
			else if(as_idCampoCriterioBusqueda.equalsIgnoreCase(CampoCriterioBusquedaCommon.DOCUMENTO_DEPARTAMENTO))
			{
				Departamento ld_departamento;

				ld_departamento = new Departamento();

				ld_departamento.setIdPais(as_idPais);
				ld_departamento.setIdDepartamento(as_valorCampo);
				lsa_datos[1]     = as_valorCampo;

				ld_departamento     = ldd_DAO.findById(ld_departamento);

				ls_valor = (ld_departamento != null) ? ld_departamento.getNombre() : (lb_validar ? null : as_valorCampo);
			}
			else if(as_idCampoCriterioBusqueda.equalsIgnoreCase(CampoCriterioBusquedaCommon.DOCUMENTO_MUNICIPIO))
			{
				Municipio ld_departamento;

				ld_departamento = new Municipio();

				ld_departamento.setIdPais(as_idPais);
				ld_departamento.setIdDepartamento(as_idDepartamento);
				ld_departamento.setIdMunicipio(as_valorCampo);
				lsa_datos[2]     = as_valorCampo;

				ld_departamento     = lmd_DAO.findById(ld_departamento);

				ls_valor = (ld_departamento != null) ? ld_departamento.getNombre() : (lb_validar ? null : as_valorCampo);
			}
			else if(
			    as_idCampoCriterioBusqueda.equalsIgnoreCase(CampoCriterioBusquedaCommon.DOCUMENTO_OFICINA_DE_ORIGEN)
			)
			{
				OficinaOrigen loo_oficinaOrigen;

				loo_oficinaOrigen     = buscarOficinaOrigenPorId(ls_valor, adm_manager);

				ls_valor = (loo_oficinaOrigen != null) ? loo_oficinaOrigen.getNombre() : (lb_validar ? null
					                                                                                 : as_valorCampo);
			}
		}
		else if(as_idTipoCriterioBusqueda.equalsIgnoreCase(TipoCriterioBusquedaCommon.DIRECCION))
		{
			if(as_idCampoCriterioBusqueda.equalsIgnoreCase(CampoCriterioBusquedaCommon.DIRECCION_TIPO_DE_PREDIO))
			{
				PredioTipo lpt_predioTipo;

				lpt_predioTipo = new PredioTipo();

				lpt_predioTipo.setIdTipoPredio(as_valorCampo);

				lpt_predioTipo     = lptd_DAO.findById(lpt_predioTipo);

				ls_valor = (lpt_predioTipo != null) ? lpt_predioTipo.getDescripcion() : (lb_validar ? null : as_valorCampo);
			}
			else if(as_idCampoCriterioBusqueda.equalsIgnoreCase(CampoCriterioBusquedaCommon.DIRECCION_DEPARTAMENTO))
			{
				Departamento ld_departamento;

				ld_departamento     = new Departamento();

				as_idPais             = IdentificadoresCommon.INDICATIVO_PAIS_DEFAULT;
				as_idDepartamento     = as_valorCampo;

				ld_departamento.setIdPais(as_idPais);
				ld_departamento.setIdDepartamento(as_valorCampo);
				lsa_datos[1]     = as_valorCampo;

				ld_departamento     = ldd_DAO.findById(ld_departamento);

				ls_valor = (ld_departamento != null) ? ld_departamento.getNombre() : (lb_validar ? null : as_valorCampo);
			}
			else if(as_idCampoCriterioBusqueda.equalsIgnoreCase(CampoCriterioBusquedaCommon.DIRECCION_MUNICIPIO))
			{
				Municipio ld_departamento;

				ld_departamento = new Municipio();

				ld_departamento.setIdPais(as_idPais);
				ld_departamento.setIdDepartamento(as_idDepartamento);
				ld_departamento.setIdMunicipio(as_valorCampo);
				lsa_datos[2]     = as_valorCampo;

				ld_departamento     = lmd_DAO.findById(ld_departamento);

				ls_valor = (ld_departamento != null) ? ld_departamento.getNombre() : (lb_validar ? null : as_valorCampo);
			}
			else if(as_idCampoCriterioBusqueda.equalsIgnoreCase(CampoCriterioBusquedaCommon.DIRECCION_VEREDA))
			{
				Vereda lv_vereda;

				lv_vereda = new Vereda();

				lv_vereda.setIdPais(as_idPais);
				lv_vereda.setIdDepartamento(as_idDepartamento);
				lv_vereda.setIdMunicipio(as_idMunicipio);
				lv_vereda.setIdVereda(as_valorCampo);

				lv_vereda     = lvd_DAO.findById(lv_vereda);

				ls_valor = (lv_vereda != null) ? lv_vereda.getNombre() : (lb_validar ? null : as_valorCampo);
			}
			else if(
			    as_idCampoCriterioBusqueda.equalsIgnoreCase(
				        CampoCriterioBusquedaCommon.DIRECCION_TIPO_DE_EJE_PRINCIPAL
				    )
				    || as_idCampoCriterioBusqueda.equalsIgnoreCase(
				        CampoCriterioBusquedaCommon.DIRECCION_TIPO_EJE_SECUNDARIO
				    ) || as_idCampoCriterioBusqueda.equalsIgnoreCase(CampoCriterioBusquedaCommon.DIRECCION_COMPLEMENTO)
				    || as_idCampoCriterioBusqueda.equalsIgnoreCase(CampoCriterioBusquedaCommon.DIRECCION_COMPLEMENTO_1)
				    || as_idCampoCriterioBusqueda.equalsIgnoreCase(CampoCriterioBusquedaCommon.DIRECCION_COMPLEMENTO_2)
				    || as_idCampoCriterioBusqueda.equalsIgnoreCase(
				        CampoCriterioBusquedaCommon.DIRECCION_COMPLEMENTO_DIRECCION
				    )
			)
			{
				TipoEje lte_tipoEje;

				lte_tipoEje = new TipoEje();

				lte_tipoEje.setIdTipoEje(as_valorCampo);

				lte_tipoEje     = lted_DAO.findById(lte_tipoEje);

				ls_valor = (lte_tipoEje != null) ? lte_tipoEje.getNombre() : (lb_validar ? null : as_valorCampo);
			}
			else if(
			    as_idCampoCriterioBusqueda.equalsIgnoreCase(CampoCriterioBusquedaCommon.DIRECCION_LETRA)
				    || as_idCampoCriterioBusqueda.equalsIgnoreCase(CampoCriterioBusquedaCommon.DIRECCION_LETRA_1)
				    || as_idCampoCriterioBusqueda.equalsIgnoreCase(CampoCriterioBusquedaCommon.DIRECCION_LETRA_2)
			)
			{
				LetraEje ll_letra;

				ll_letra     = lle_DAO.findById(as_valorCampo);
				ls_valor     = (ll_letra != null) ? ll_letra.getLetra() : null;
			}
			else if(
			    as_idCampoCriterioBusqueda.equalsIgnoreCase(CampoCriterioBusquedaCommon.DIRECCION_COORDENADA)
				    || as_idCampoCriterioBusqueda.equalsIgnoreCase(CampoCriterioBusquedaCommon.DIRECCION_COORDENADA_1)
				    || as_idCampoCriterioBusqueda.equalsIgnoreCase(CampoCriterioBusquedaCommon.DIRECCION_COORDENADA_2)
			)
			{
				Coordenada lc_coordenada;

				lc_coordenada     = lc_DAO.findById(as_valorCampo);
				ls_valor          = (lc_coordenada != null) ? lc_coordenada.getNombre() : null;
			}
		}
		else if(as_idTipoCriterioBusqueda.equalsIgnoreCase(TipoCriterioBusquedaCommon.MATRICULA))
		{
			if(as_idCampoCriterioBusqueda.equalsIgnoreCase(CampoCriterioBusquedaCommon.MATRICULA_CIRCULO_REGISTRAL))
			{
				CirculoRegistral lcr_circuloRegistral;

				lcr_circuloRegistral = new CirculoRegistral();

				lcr_circuloRegistral.setIdCirculo(as_valorCampo);

				lcr_circuloRegistral     = lcrd_DAO.findById(lcr_circuloRegistral);

				ls_valor = (lcr_circuloRegistral != null) ? lcr_circuloRegistral.getNombre()
					                                      : (lb_validar ? null : as_valorCampo);
			}
		}

		if(lb_validar && !StringUtils.isValidString(ls_valor))
		{
			CamposConsulta lcc_camposConsulta;

			lcc_camposConsulta = new CamposConsulta();

			lcc_camposConsulta.setIdTipoCriterioBusqueda(as_idTipoCriterioBusqueda);
			lcc_camposConsulta.setIdCampoCriterioBusqueda(as_idCampoCriterioBusqueda);

			lcc_camposConsulta = DaoCreator.getCampoCriterioBusquedaDAO(adm_manager)
					                           .buscarCamposPorTipoCampo(lcc_camposConsulta);

			if(lcc_camposConsulta != null)
			{
				String ls_tipoDato;

				ls_tipoDato = lcc_camposConsulta.getTipoCampo();

				if(StringUtils.isValidString(ls_tipoDato) && ls_tipoDato.equalsIgnoreCase(TipoDatoCommon.MULTIVALOR))
				{
					Object[] loa_messageArgs;

					loa_messageArgs        = new String[1];
					loa_messageArgs[0]     = lcc_camposConsulta.getEtiquetaCampo();

					throw new B2BException(addMessage(ErrorKeys.ERROR_CAMPO_CRITERIO_NO_VALIDO, loa_messageArgs, true));
				}
			}
		}

		lsa_datos[3] = ls_valor;

		return lsa_datos;
	}

	/**
	 * Metodo encargado ed validar los datos que serán cargados en detalle criterio búsqueda.
	 *
	 * @param lcc_iterador Argumento de tipo CamposConsulta que contiene los datos a validar.
	 * @param lccbd_DAO Argumento de tipo CampoCriterioBusquedaDAO que contiene el
	 * DAO para realizar el tratamiento de sentencias SQL.
	 * @return Variable de tipo boolean que determina (true) si los datos ingresados son válidos
	 * para insertar en detalle criterio búsqueda y si no (false).
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	private boolean validarDetalleCriterioBusqueda(CamposConsulta lcc_iterador, CampoCriterioBusquedaDAO lccbd_DAO)
	    throws B2BException
	{
		CamposConsulta lcc_camposConsulta;
		String         ls_valorCampo;
		boolean        lb_valido;

		lcc_camposConsulta = lccbd_DAO.buscarCamposPorTipoCampo(lcc_iterador);

		{
			String  ls_tipoCampo;
			boolean lb_date;

			ls_tipoCampo     = lcc_iterador.getTipoCampo();
			lb_date          = StringUtils.isValidString(ls_tipoCampo)
					&& ls_tipoCampo.equalsIgnoreCase(TipoDatoCommon.FECHA);

			ls_valorCampo = lb_date
				? StringUtils.getString(lcc_iterador.getValorCampoFecha(), FormatoFechaCommon.DIA_MES_ANIO)
				: lcc_iterador.getValorCampo();
		}

		lb_valido = StringUtils.isValidString(ls_valorCampo);

		if(lcc_camposConsulta != null)
		{
			String ls_idObligatorio;

			ls_idObligatorio = lcc_camposConsulta.getObligatorio();

			if(
			    StringUtils.isValidString(ls_idObligatorio) && ls_idObligatorio.equalsIgnoreCase(EstadoCommon.S)
				    && !lb_valido
			)
			{
				Object[] loa_messageArgs;

				loa_messageArgs        = new String[1];
				loa_messageArgs[0]     = lcc_camposConsulta.getEtiquetaCampo();

				throw new B2BException(addMessage(ErrorKeys.ERROR_CAMPO_CRITERIO_OBLIGATORIO, loa_messageArgs, true));
			}
		}

		return lb_valido;
	}

	/**
	 * Metodo encargado de validar si es necesario verificar si un campo existe (true) de lo contrario (false).
	 *
	 * @param as_idTipoCriterioBusqueda Argumento de tipo String que contiene el tipo de criterio a validar.
	 * @param as_idCampoCriterioBusqueda Argumento de tipo String que contiene el campo a validar.
	 * @return Argumento de tipo boolean que indica si se debe validar la existencia del campo.
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	private boolean validarDetalleExistente(String as_idTipoCriterioBusqueda, String as_idCampoCriterioBusqueda)
	    throws B2BException
	{
		boolean lb_existente;

		lb_existente = false;

		if(
		    StringUtils.isValidString(as_idTipoCriterioBusqueda)
			    && StringUtils.isValidString(as_idCampoCriterioBusqueda)
		)
		{
			if(as_idTipoCriterioBusqueda.equalsIgnoreCase(TipoCriterioBusquedaCommon.IDENTIFICACION))
				lb_existente = as_idCampoCriterioBusqueda.equalsIgnoreCase(
					    CampoCriterioBusquedaCommon.IDENTIFICACION_TIPO_DE_DOCUMENTO
					)
						|| as_idCampoCriterioBusqueda.equalsIgnoreCase(
						    CampoCriterioBusquedaCommon.IDENTIFICACION_DOCUMENTO_DE_IDENTIDAD
						)
						|| as_idCampoCriterioBusqueda.equalsIgnoreCase(
						    CampoCriterioBusquedaCommon.IDENTIFICACION_PRIMER_NOMBRE
						)
						|| as_idCampoCriterioBusqueda.equalsIgnoreCase(
						    CampoCriterioBusquedaCommon.IDENTIFICACION_SEGUNDO_NOMBRE
						)
						|| as_idCampoCriterioBusqueda.equalsIgnoreCase(
						    CampoCriterioBusquedaCommon.IDENTIFICACION_PRIMER_APELLIDO
						)
						|| as_idCampoCriterioBusqueda.equalsIgnoreCase(
						    CampoCriterioBusquedaCommon.IDENTIFICACION_SEGUNDO_APELLIDO
						)
						|| as_idCampoCriterioBusqueda.equalsIgnoreCase(
						    CampoCriterioBusquedaCommon.IDENTIFICACION_RAZON_SOCIAL
						);
			else if(as_idTipoCriterioBusqueda.equalsIgnoreCase(TipoCriterioBusquedaCommon.DOCUMENTO))
				lb_existente = as_idCampoCriterioBusqueda.equalsIgnoreCase(
					    CampoCriterioBusquedaCommon.DOCUMENTO_TIPO_DE_DOCUMENTO
					)
						|| as_idCampoCriterioBusqueda.equalsIgnoreCase(
						    CampoCriterioBusquedaCommon.DOCUMENTO_NUMERO_DE_DOCUMENTO
						)
						|| as_idCampoCriterioBusqueda.equalsIgnoreCase(
						    CampoCriterioBusquedaCommon.DOCUMENTO_FECHA_DE_DOCUMENTO
						)
						|| as_idCampoCriterioBusqueda.equalsIgnoreCase(
						    CampoCriterioBusquedaCommon.DOCUMENTO_TIPO_DE_OFICINA
						) || as_idCampoCriterioBusqueda.equalsIgnoreCase(CampoCriterioBusquedaCommon.DOCUMENTO_PAIS)
						|| as_idCampoCriterioBusqueda.equalsIgnoreCase(
						    CampoCriterioBusquedaCommon.DOCUMENTO_DEPARTAMENTO
						)
						|| as_idCampoCriterioBusqueda.equalsIgnoreCase(CampoCriterioBusquedaCommon.DOCUMENTO_MUNICIPIO)
						|| as_idCampoCriterioBusqueda.equalsIgnoreCase(
						    CampoCriterioBusquedaCommon.DOCUMENTO_OFICINA_DE_ORIGEN
						);
			else if(as_idTipoCriterioBusqueda.equalsIgnoreCase(TipoCriterioBusquedaCommon.DIRECCION))
				lb_existente = as_idCampoCriterioBusqueda.equalsIgnoreCase(
					    CampoCriterioBusquedaCommon.DIRECCION_TIPO_DE_PREDIO
					)
						|| as_idCampoCriterioBusqueda.equalsIgnoreCase(
						    CampoCriterioBusquedaCommon.DIRECCION_DEPARTAMENTO
						)
						|| as_idCampoCriterioBusqueda.equalsIgnoreCase(CampoCriterioBusquedaCommon.DIRECCION_MUNICIPIO)
						|| as_idCampoCriterioBusqueda.equalsIgnoreCase(CampoCriterioBusquedaCommon.DIRECCION_VEREDA)
						|| as_idCampoCriterioBusqueda.equalsIgnoreCase(
						    CampoCriterioBusquedaCommon.DIRECCION_NOMBRE_DEL_PREDIO
						)
						|| as_idCampoCriterioBusqueda.equalsIgnoreCase(
						    CampoCriterioBusquedaCommon.DIRECCION_TIPO_DE_EJE_PRINCIPAL
						)
						|| as_idCampoCriterioBusqueda.equalsIgnoreCase(
						    CampoCriterioBusquedaCommon.DIRECCION_EJE_PRINCIPAL
						)
						|| as_idCampoCriterioBusqueda.equalsIgnoreCase(
						    CampoCriterioBusquedaCommon.DIRECCION_TIPO_EJE_SECUNDARIO
						)
						|| as_idCampoCriterioBusqueda.equalsIgnoreCase(
						    CampoCriterioBusquedaCommon.DIRECCION_EJE_SECUNDARIO
						)
						|| as_idCampoCriterioBusqueda.equalsIgnoreCase(
						    CampoCriterioBusquedaCommon.DIRECCION_DESCRIPCION_COMPLEMENTO
						);
			else if(as_idTipoCriterioBusqueda.equalsIgnoreCase(TipoCriterioBusquedaCommon.CEDULA_CATASTRAL))
				lb_existente = as_idCampoCriterioBusqueda.equalsIgnoreCase(
					    CampoCriterioBusquedaCommon.CEDULA_CATASTRAL_NUMERO_CATASTRAL
					)
						|| as_idCampoCriterioBusqueda.equalsIgnoreCase(
						    CampoCriterioBusquedaCommon.CEDULA_CATASTRAL_NUMERO_CATASTRAL_ANTERIOR
						);
			else if(as_idTipoCriterioBusqueda.equalsIgnoreCase(TipoCriterioBusquedaCommon.MATRICULA))
				lb_existente = as_idCampoCriterioBusqueda.equalsIgnoreCase(
					    CampoCriterioBusquedaCommon.MATRICULA_CIRCULO_REGISTRAL
					)
						|| as_idCampoCriterioBusqueda.equalsIgnoreCase(
						    CampoCriterioBusquedaCommon.MATRICULA_NUMERO_DE_MATRICULA
						);
			else if(as_idTipoCriterioBusqueda.equalsIgnoreCase(TipoCriterioBusquedaCommon.NUPRE))
				lb_existente = as_idCampoCriterioBusqueda.equalsIgnoreCase(CampoCriterioBusquedaCommon.NUPRE_NUPRE);
		}

		return lb_existente;
	}
}
