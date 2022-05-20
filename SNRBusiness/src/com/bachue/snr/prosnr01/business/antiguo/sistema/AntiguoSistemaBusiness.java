package com.bachue.snr.prosnr01.business.antiguo.sistema;

import com.b2bsg.common.dataAccess2.DAOManager;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.logging.LoggerHandler;

import com.b2bsg.common.util.BooleanUtils;
import com.b2bsg.common.util.CollectionUtils;
import com.b2bsg.common.util.DateUtils;
import com.b2bsg.common.util.NumericUtils;
import com.b2bsg.common.util.StringUtils;

import com.bachue.prosnr01.integracion.cliente.arcgis.json.ClienteJSON;
import com.bachue.prosnr01.integracion.cliente.arcgis.json.ImagenPdf;

import com.bachue.snr.prosnr01.business.MarcaAgua;
import com.bachue.snr.prosnr01.business.integracion.EnvioGestorDocumentalBusiness;
import com.bachue.snr.prosnr01.business.utilidades.PDFGenerator;

import com.bachue.snr.prosnr01.common.constants.AccionAntSistemaCommon;
import com.bachue.snr.prosnr01.common.constants.ConstanteCommon;
import com.bachue.snr.prosnr01.common.constants.ErrorKeys;
import com.bachue.snr.prosnr01.common.constants.EstadoCommon;
import com.bachue.snr.prosnr01.common.constants.EtapaCommon;
import com.bachue.snr.prosnr01.common.constants.ExtensionCommon;
import com.bachue.snr.prosnr01.common.constants.FormatoFechaCommon;
import com.bachue.snr.prosnr01.common.constants.IdentificadoresCommon;
import com.bachue.snr.prosnr01.common.constants.LibroAntSistemaCommon;
import com.bachue.snr.prosnr01.common.constants.MotivoTramiteCommon;
import com.bachue.snr.prosnr01.common.constants.ProcesoCommon;
import com.bachue.snr.prosnr01.common.constants.RepositorioCommon;
import com.bachue.snr.prosnr01.common.constants.RolCommon;
import com.bachue.snr.prosnr01.common.constants.SubProcesoCommon;
import com.bachue.snr.prosnr01.common.constants.TipoArchivoCommon;
import com.bachue.snr.prosnr01.common.constants.TipoAreaCommon;
import com.bachue.snr.prosnr01.common.constants.TipoComplementacionCommon;
import com.bachue.snr.prosnr01.common.constants.TipoDocumentalCommon;
import com.bachue.snr.prosnr01.common.utils.ListadoCodigosConstantes;

import com.bachue.snr.prosnr01.dao.DaoCreator;
import com.bachue.snr.prosnr01.dao.DaoManagerFactory;
import com.bachue.snr.prosnr01.dao.acc.AccAreaPredioDAO;
import com.bachue.snr.prosnr01.dao.acc.AccComplementacionPredioDAO;
import com.bachue.snr.prosnr01.dao.acc.AccConsultaCriteriosAntSistemaDAO;
import com.bachue.snr.prosnr01.dao.acc.AccDetalleAreaPredioDAO;
import com.bachue.snr.prosnr01.dao.acc.AccLinderoPredioDAO;
import com.bachue.snr.prosnr01.dao.acc.AccPredioRegistroDAO;
import com.bachue.snr.prosnr01.dao.acc.AccPredioSegregadoDAO;
import com.bachue.snr.prosnr01.dao.acc.ActoDAO;
import com.bachue.snr.prosnr01.dao.acc.AnotacionCancelacionDAO;
import com.bachue.snr.prosnr01.dao.acc.AnotacionPredioCiudadanoDAO;
import com.bachue.snr.prosnr01.dao.acc.AnotacionPredioDAO;
import com.bachue.snr.prosnr01.dao.acc.BitacoraProcesoDAO;
import com.bachue.snr.prosnr01.dao.acc.CausalDevolucionDAO;
import com.bachue.snr.prosnr01.dao.acc.CompletitudComplementacionDAO;
import com.bachue.snr.prosnr01.dao.acc.DatosAntSistemaDAO;
import com.bachue.snr.prosnr01.dao.acc.DetalleAntSistemaDAO;
import com.bachue.snr.prosnr01.dao.acc.DireccionPredioAccDAO;
import com.bachue.snr.prosnr01.dao.acc.DocumentosSalidaDAO;
import com.bachue.snr.prosnr01.dao.acc.PersonaDAO;
import com.bachue.snr.prosnr01.dao.acc.ProcesoDAO;
import com.bachue.snr.prosnr01.dao.acc.SolicitudDAO;
import com.bachue.snr.prosnr01.dao.acc.SolicitudIntervinienteDAO;
import com.bachue.snr.prosnr01.dao.acc.SolicitudMatriculaActoDAO;
import com.bachue.snr.prosnr01.dao.acc.SolicitudMatriculaDAO;
import com.bachue.snr.prosnr01.dao.acc.SubprocesoDAO;
import com.bachue.snr.prosnr01.dao.acc.TurnoDAO;
import com.bachue.snr.prosnr01.dao.acc.TurnoHistoriaDAO;
import com.bachue.snr.prosnr01.dao.antiguo.sistema.AntiguoSistemaDAO;
import com.bachue.snr.prosnr01.dao.bgn.DocumentoDAO;
import com.bachue.snr.prosnr01.dao.bgn.ImagenesDAO;
import com.bachue.snr.prosnr01.dao.bng.ComplementacionPredioDAO;
import com.bachue.snr.prosnr01.dao.bng.PredioRegistroDAO;
import com.bachue.snr.prosnr01.dao.consultas.ConsultaPorCriteriosDAO;
import com.bachue.snr.prosnr01.dao.pgn.CirculoRegistralDao;
import com.bachue.snr.prosnr01.dao.pgn.ConstantesDAO;
import com.bachue.snr.prosnr01.dao.pgn.LibroAntiguoSistemaDao;
import com.bachue.snr.prosnr01.dao.pgn.MedidaAreaDAO;
import com.bachue.snr.prosnr01.dao.pgn.MotivoTramiteDAO;
import com.bachue.snr.prosnr01.dao.pgn.OficinaOrigenDAO;
import com.bachue.snr.prosnr01.dao.pgn.ZonaRegistralDAO;
import com.bachue.snr.prosnr01.dao.png.NaturalezaJuridicaDAO;
import com.bachue.snr.prosnr01.dao.proc.ProcedimientosDAO;
import com.bachue.snr.prosnr01.dao.util.ConsultasUtilidades;
import com.bachue.snr.prosnr01.dao.util.UtilDAO;

import com.bachue.snr.prosnr01.model.antiguoSistema.AmpliacionHistoriaRegistral;
import com.bachue.snr.prosnr01.model.antiguoSistema.Anotacion;
import com.bachue.snr.prosnr01.model.antiguoSistema.AntiguoSistemaData;
import com.bachue.snr.prosnr01.model.antiguoSistema.Apertura;
import com.bachue.snr.prosnr01.model.antiguoSistema.AreaPredio;
import com.bachue.snr.prosnr01.model.antiguoSistema.CausalDevolucion;
import com.bachue.snr.prosnr01.model.antiguoSistema.Complementacion;
import com.bachue.snr.prosnr01.model.antiguoSistema.ConsultaDatosDocumento;
import com.bachue.snr.prosnr01.model.antiguoSistema.DataConsultaAntSistema;
import com.bachue.snr.prosnr01.model.antiguoSistema.DataConsultaDatosDocumento;
import com.bachue.snr.prosnr01.model.antiguoSistema.DataConsultaPorCriterio;
import com.bachue.snr.prosnr01.model.antiguoSistema.DatosBasicos;
import com.bachue.snr.prosnr01.model.antiguoSistema.DireccionDelPredio;
import com.bachue.snr.prosnr01.model.antiguoSistema.DocumentoData;
import com.bachue.snr.prosnr01.model.antiguoSistema.MatriculaBase;
import com.bachue.snr.prosnr01.model.antiguoSistema.UbicacionZonaRegistral;
import com.bachue.snr.prosnr01.model.calificacion.RegistroCalificacion;
import com.bachue.snr.prosnr01.model.calificacion.TramiteCantidad;
import com.bachue.snr.prosnr01.model.numeracion.NumeracionOficiosCirculo;
import com.bachue.snr.prosnr01.model.numeracion.NumeracionResolucionCirculo;
import com.bachue.snr.prosnr01.model.sdb.acc.AccAreaPredio;
import com.bachue.snr.prosnr01.model.sdb.acc.AccComplementacionPredio;
import com.bachue.snr.prosnr01.model.sdb.acc.AccConsultaCriterioAntiguoSistema;
import com.bachue.snr.prosnr01.model.sdb.acc.AccLinderoPredio;
import com.bachue.snr.prosnr01.model.sdb.acc.AccPredioRegistro;
import com.bachue.snr.prosnr01.model.sdb.acc.Acto;
import com.bachue.snr.prosnr01.model.sdb.acc.AnotacionCancelacion;
import com.bachue.snr.prosnr01.model.sdb.acc.AnotacionPredio;
import com.bachue.snr.prosnr01.model.sdb.acc.AnotacionPredioCiudadano;
import com.bachue.snr.prosnr01.model.sdb.acc.CompletitudComplementacion;
import com.bachue.snr.prosnr01.model.sdb.acc.DatosAntSistema;
import com.bachue.snr.prosnr01.model.sdb.acc.DetalleAntSistema;
import com.bachue.snr.prosnr01.model.sdb.acc.DetalleAntSistemaUI;
import com.bachue.snr.prosnr01.model.sdb.acc.DetalleAreaPredio;
import com.bachue.snr.prosnr01.model.sdb.acc.DireccionPredioAcc;
import com.bachue.snr.prosnr01.model.sdb.acc.DocumentosSalida;
import com.bachue.snr.prosnr01.model.sdb.acc.OficiosTexto;
import com.bachue.snr.prosnr01.model.sdb.acc.Persona;
import com.bachue.snr.prosnr01.model.sdb.acc.PersonaCorreoElectronico;
import com.bachue.snr.prosnr01.model.sdb.acc.PersonaDireccion;
import com.bachue.snr.prosnr01.model.sdb.acc.PersonaTelefono;
import com.bachue.snr.prosnr01.model.sdb.acc.PredioSegregado;
import com.bachue.snr.prosnr01.model.sdb.acc.Proceso;
import com.bachue.snr.prosnr01.model.sdb.acc.Solicitud;
import com.bachue.snr.prosnr01.model.sdb.acc.SolicitudInterviniente;
import com.bachue.snr.prosnr01.model.sdb.acc.SolicitudMatricula;
import com.bachue.snr.prosnr01.model.sdb.acc.SolicitudMatriculaActo;
import com.bachue.snr.prosnr01.model.sdb.acc.Subproceso;
import com.bachue.snr.prosnr01.model.sdb.acc.TipoCausal;
import com.bachue.snr.prosnr01.model.sdb.acc.Turno;
import com.bachue.snr.prosnr01.model.sdb.acc.TurnoDerivado;
import com.bachue.snr.prosnr01.model.sdb.acc.TurnoHistoria;
import com.bachue.snr.prosnr01.model.sdb.bng.ComplementacionPredio;
import com.bachue.snr.prosnr01.model.sdb.bng.DireccionPredio;
import com.bachue.snr.prosnr01.model.sdb.bng.Documento;
import com.bachue.snr.prosnr01.model.sdb.bng.Imagenes;
import com.bachue.snr.prosnr01.model.sdb.bng.PredioRegistro;
import com.bachue.snr.prosnr01.model.sdb.col.ParteInteresada;
import com.bachue.snr.prosnr01.model.sdb.col.PredioTipo;
import com.bachue.snr.prosnr01.model.sdb.col.TipoUsoSuelo;
import com.bachue.snr.prosnr01.model.sdb.pgn.CirculoRegistral;
import com.bachue.snr.prosnr01.model.sdb.pgn.Constantes;
import com.bachue.snr.prosnr01.model.sdb.pgn.Departamento;
import com.bachue.snr.prosnr01.model.sdb.pgn.Etapa;
import com.bachue.snr.prosnr01.model.sdb.pgn.LibroAntiguoSistema;
import com.bachue.snr.prosnr01.model.sdb.pgn.MedidaArea;
import com.bachue.snr.prosnr01.model.sdb.pgn.MotivoTramite;
import com.bachue.snr.prosnr01.model.sdb.pgn.Municipio;
import com.bachue.snr.prosnr01.model.sdb.pgn.OficinaOrigen;
import com.bachue.snr.prosnr01.model.sdb.pgn.Pais;
import com.bachue.snr.prosnr01.model.sdb.pgn.TagPlantillaResolucion;
import com.bachue.snr.prosnr01.model.sdb.pgn.TipoActo;
import com.bachue.snr.prosnr01.model.sdb.pgn.TipoDocumental;
import com.bachue.snr.prosnr01.model.sdb.pgn.TipoDocumentoPublico;
import com.bachue.snr.prosnr01.model.sdb.pgn.TipoEntidad;
import com.bachue.snr.prosnr01.model.sdb.pgn.TipoOficina;
import com.bachue.snr.prosnr01.model.sdb.pgn.Vereda;
import com.bachue.snr.prosnr01.model.sdb.pgn.ZonaRegistral;
import com.bachue.snr.prosnr01.model.sdb.png.EstadoPredio;
import com.bachue.snr.prosnr01.model.sdb.png.GrupoNaturalezaJuridica;
import com.bachue.snr.prosnr01.model.sdb.png.NaturalezaJuridica;
import com.bachue.snr.prosnr01.model.sdb.png.TipoEje;
import com.bachue.snr.prosnr01.model.ui.FechaVenceTerminosUI;

import java.io.InputStream;

import java.math.BigDecimal;
import java.math.BigInteger;

import java.net.URL;

import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Map;


/**
 * Clase que contiene los métodos de negocio para la fase de antiguo sistema,
 * por ejemplo crear, asociar , grabar matriculas, reporte de resultados entre otros.
 *
 * @author Gabriel Arias.
 *
 */
public class AntiguoSistemaBusiness extends EnvioGestorDocumentalBusiness
{
	/** Constante clh_LOGGER. */
	private static final LoggerHandler clh_LOGGER = (LoggerHandler)com.bachue.snr.prosnr01.common.utils.LoggingHelper
			.getLogger(AntiguoSistemaBusiness.class);

	/**
	 * Salvar completitud complementacion.
	 *
	 * @param cc_parametros correspondiente al valor de cc parametros
	 * @param as_userId correspondiente al valor de as user id
	 * @param as_remoteIp correspondiente al valor de as remote ip
	 * @return retorna <code>true</code> si se cumple la condicion, de lo contario retorna <code>false</code>
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public synchronized void actualizarCompletitudComplementacion(
	    CompletitudComplementacion cc_parametros, boolean ab_definitvo102, String as_userId, String as_remoteIp
	)
	    throws B2BException
	{
		DAOManager ldm_manager;

		ldm_manager = DaoManagerFactory.getDAOManager();

		if(cc_parametros != null)
		{
			try
			{
				cc_parametros.setRespuestaBusqueda(cc_parametros.getRespuestaBusqueda());
				cc_parametros.setObservacionesNoHallazgo(cc_parametros.getObservacionesNoHallazgo());
				cc_parametros.setInformacionComplementacion(cc_parametros.getInformacionComplementacion());
				cc_parametros.setIdUsuarioModificacion(as_userId);
				cc_parametros.setIpModificacion(as_userId);

				DaoCreator.getCompletitudComplementacionDAO(ldm_manager).update(cc_parametros);

				if(ab_definitvo102)
				{
					ComplementacionPredio lccp_complementacionPredio;
					lccp_complementacionPredio = new ComplementacionPredio();
					lccp_complementacionPredio.setIdComplementacion(
					    StringUtils.getString(cc_parametros.getIdComplementacion())
					);

					lccp_complementacionPredio.setComplementacion(
					    StringUtils.isValidString(cc_parametros.getObservacionesNoHallazgo())
					    ? cc_parametros.getObservacionesNoHallazgo() : cc_parametros.getInformacionComplementacion()
					);
					DaoCreator.getComplementacionPredioDAO(ldm_manager).updateById(lccp_complementacionPredio);
				}
			}
			catch(B2BException lb2be_e)
			{
				ldm_manager.setRollbackOnly();

				clh_LOGGER.error("salvarCompletitudComplementacion", lb2be_e);

				throw lb2be_e;
			}
			finally
			{
				ldm_manager.close();
			}
		}
	}

	/**
	 * Método  encargado de actualizar la dirección del predio para un id dirección predio determinado.
	 *
	 * @param adb_db Objeto que contiene los datos necesarios para actualizar la tabla SDB_ACC_DIRECCION_PREDIO.
	 * @param as_userId  Variable de tipo String que contiene el usuario que realiza la acción.
	 * @param as_remoteIp Variable de tipo String que contiene la dirección ip remota desde donde seque realiza la acción.
	 * @return  Retorna  un objeto de tipo DireccionDelPredio que coincide con los parametros de entrada.
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see DireccionDelPredio
	 */
	public synchronized DireccionDelPredio actualizarDireccionPredio(
	    DireccionDelPredio adb_db, String as_userId, String as_remoteIp
	)
	    throws B2BException
	{
		DAOManager         ldm_manager;
		DireccionDelPredio lddp_return;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		lddp_return     = null;

		try
		{
			DireccionPredioAcc    ldpa_dir;
			DireccionPredioAccDAO lddpDAO_ddp;

			lddpDAO_ddp     = DaoCreator.getDireccionPredioAccDAO(ldm_manager);
			ldpa_dir        = lddpDAO_ddp.findById(adb_db.getDireccionPredio());

			if(ldpa_dir != null)
			{
				adb_db.getDireccionPredio().setIdUsuarioModificacion(as_userId);
				adb_db.getDireccionPredio().setIpModificacion(as_remoteIp);
				lddpDAO_ddp.updateById(adb_db.getDireccionPredio(), true);

				lddp_return = adb_db;
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("actualizarDireccionPredio", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lddp_return;
	}

	/**
	 * Metodo encargado de validar y actualizar los datos del documento.
	 *
	 * @param ad_documento Argumento de tipo <code>Documento</code>
	 * que contiene los datos a validar.
	 * @param ab_salvarAnotacion correspondiente al valor del tipo de objeto boolean
	 * @param adm_manager Argumento de tipo <code>DaoManager</code>
	 * que permite acceder a los metodos y sentencias sql de bgn_documento.
	 * @param as_userId  Argumento de tipo <code>String</code> que contiene
	 * el usuario que realiza la acción.
	 * @param as_remoteIp Argumento de tipo <code>String</code> que contiene
	 * la dirección ip desde donde se realiza la acción.
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public synchronized void actualizarOCrearDocumentoAnotacion(
	    Documento ad_documento, boolean ab_salvarAnotacion, DAOManager adm_manager, String as_userId, String as_remoteIp
	)
	    throws B2BException
	{
		try
		{
			if(ad_documento != null)
			{
				{
					String ls_tmp;
					ls_tmp = ad_documento.getIdTipoDocumento();

					if(!StringUtils.isValidString(ls_tmp))
						throw new B2BException(ErrorKeys.TIPO_DOCUMENTO_INVALIDO);
				}

				{
					String ls_tmp;
					ls_tmp = ad_documento.getNumero();

					if(!StringUtils.isValidString(ls_tmp))
						throw new B2BException(ErrorKeys.DOCUMENTO_INVALIDO);
				}

				{
					Date ld_tmp;
					ld_tmp = ad_documento.getFechaDocumento();

					if(ld_tmp == null)
						throw new B2BException(ErrorKeys.DEBE_DIGITAR_FECHA_DOC);
					else
					{
						Date ld_fechaActual;
						ld_fechaActual = new Date();

						if(ld_tmp.after(ld_fechaActual))
							throw new B2BException(ErrorKeys.FECHA_SUPERIOR_ACTUAL);
					}
				}

				{
					String ls_tmp;
					ls_tmp = ad_documento.getIdTipoOficina();

					if(!StringUtils.isValidString(ls_tmp))
						throw new B2BException(ErrorKeys.DEBE_SELECCIONAR_TIPO_OFICINA);
				}

				{
					String ls_tmp;
					ls_tmp = ad_documento.getIdPais();

					if(!StringUtils.isValidString(ls_tmp))
						throw new B2BException(ErrorKeys.DEBE_SELECCIONAR_PAIS);
				}

				{
					String ls_tmp;
					ls_tmp = ad_documento.getIdDepartamento();

					if(!StringUtils.isValidString(ls_tmp))
						throw new B2BException(ErrorKeys.DEBE_DIGITAR_DEPARTAMENTO);
				}

				{
					String ls_tmp;
					ls_tmp = ad_documento.getIdMunicipio();

					if(!StringUtils.isValidString(ls_tmp))
						throw new B2BException(ErrorKeys.DEBE_SELECCIONAR_MUNICIPIO);
				}

				{
					DocumentoDAO       ldd_DAO;
					AnotacionPredioDAO lapd_DAO;

					Documento       ld_tmp;
					AnotacionPredio lap_anotacionPredio;

					ldd_DAO      = DaoCreator.getDocumentoDAO(adm_manager);
					lapd_DAO     = DaoCreator.getAccAnotacionPredioDAO(adm_manager);

					{
						String ls_tmp;
						ls_tmp = ad_documento.getIdOficinaOrigen();

						if(!StringUtils.isValidString(ls_tmp))
							throw new B2BException(ErrorKeys.DEBE_SELECCIONAR_OFICINA_ORIGEN);

						ad_documento.setVersion(obtenerVersionMaximaOficinaOrigen(ls_tmp, adm_manager));
					}

					ld_tmp                  = ldd_DAO.consultaDocumento(ad_documento);
					lap_anotacionPredio     = ad_documento.getAnotacionPredio();

					if(ld_tmp != null)
					{
						String ls_idDocumento;

						ls_idDocumento = ld_tmp.getIdDocumento();

						if(!StringUtils.isValidString(ls_idDocumento))
							throw new B2BException(ErrorKeys.ERROR_SIN_ID_DOCUMENTO);

						ad_documento.setIdDocumento(ls_idDocumento);

						{
							Long ll_versionDocumento;
							ll_versionDocumento = ad_documento.getVersionDocumento();

							if(NumericUtils.isValidLong(ll_versionDocumento))
								ad_documento.setVersionDocumento(ll_versionDocumento);
							else
								ad_documento.setVersionDocumento(NumericUtils.getLongWrapper(1L));
						}

						ad_documento.setIdUsuarioModificacion(as_userId);
						ad_documento.setIpModificacion(as_remoteIp);

						ldd_DAO.insertOrUpdate(ad_documento, false);
					}
					else
					{
						int li_secuencia;
						li_secuencia = ldd_DAO.findSecuencia();

						if(li_secuencia > 0)
						{
							ad_documento.setIdDocumento(StringUtils.getString(li_secuencia));
							ad_documento.setVersionDocumento(NumericUtils.getLongWrapper(1L));
						}
						else
							throw new B2BException(ErrorKeys.BGN_DOCUMENTO_INSERT);

						ad_documento.setIdUsuarioCreacion(as_userId);
						ad_documento.setIpCreacion(as_remoteIp);

						ldd_DAO.insertOrUpdate(ad_documento, true);
					}

					if(
					    ab_salvarAnotacion && (lap_anotacionPredio != null)
						    && StringUtils.isValidString(ad_documento.getIdDocumento())
					)
					{
						lap_anotacionPredio.setIdDocumento(ad_documento.getIdDocumento());

						lapd_DAO.modificiarAnotacionesPredio(lap_anotacionPredio, false);
					}

					{
						boolean lb_copiarDocumento;

						lb_copiarDocumento = ad_documento.isCopiarDocumento();

						if(lb_copiarDocumento)
						{
							Collection<SolicitudMatricula> lcsm_matriculas;

							lcsm_matriculas = ad_documento.getMatriculasMasivasDocumento();

							if(CollectionUtils.isValidCollection(lcsm_matriculas))
							{
								boolean lb_copiarDocumentoSeleccionadas;

								lb_copiarDocumentoSeleccionadas = ad_documento.isCopiarDocumentoSeleccionadas();

								for(SolicitudMatricula lsm_iterador : lcsm_matriculas)
								{
									if(lsm_iterador != null)
									{
										boolean lb_seleccionado;

										lb_seleccionado = lb_copiarDocumentoSeleccionadas
											? lsm_iterador.isSeleccionado() : lb_copiarDocumentoSeleccionadas;

										if(lb_seleccionado)
										{
											if(
											    (lap_anotacionPredio != null)
												    && StringUtils.isValidString(lap_anotacionPredio.getRadicacion())
											)
											{
												String ls_idNaturalezaJuridica;

												ls_idNaturalezaJuridica = lap_anotacionPredio.getIdNaturalezaJuridica();

												lap_anotacionPredio.setIdCirculo(lsm_iterador.getIdCirculo());
												lap_anotacionPredio.setIdMatricula(
												    NumericUtils.getLongWrapper(lsm_iterador.getIdMatricula())
												);

												if(
												    StringUtils.isValidString(ls_idNaturalezaJuridica)
													    && ls_idNaturalezaJuridica.contains("-")
												)
												{
													String[] lsa_tmp;

													lsa_tmp = ls_idNaturalezaJuridica.split("-");

													if(
													    CollectionUtils.isValidCollection(lcsm_matriculas)
														    && (lsa_tmp.length > 1)
													)
														ls_idNaturalezaJuridica = lsa_tmp[0];
												}

												lap_anotacionPredio.setIdNaturalezaJuridica(ls_idNaturalezaJuridica);

												lapd_DAO.modificiarDocumentoCopiaFolio(lap_anotacionPredio);
											}
											else
												throw new B2BException(ErrorKeys.ERROR_DATOS_DE_COPIA_INVALIDOS);
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
			clh_LOGGER.error("actualizarOCrearDocumentoAnotacion", lb2be_e);
		}
	}

	/**
	 * Método encargado de salvar el proceso de Antiguo Sistema, para el flujo de actualizar.
	 *
	 * @param adas_datosAntSistema bjeto que contiene los datos de antiguo sistema.
	 * @param as_idTurnoHistoria  Variable de tipo String que contiene el id turno historia para un proceso determinado.
	 * @param as_observaciones Variable de tipo String que contiene las observaciones del usuario para el turno historia que está en proceso.
	 * @param as_userId Variable de tipo String que contiene el id del usuario que está realizando el proceso.
	 * @param as_remoteIp Variable de tipo String que contiene la ip del usuario que está realizando el proceso.
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public synchronized void antSistemaSalvarActualizar101(
	    DatosAntSistema adas_datosAntSistema, String as_idTurnoHistoria, String as_observaciones, String as_userId,
	    String as_remoteIp
	)
	    throws B2BException
	{
		DAOManager ldm_manager;

		ldm_manager = DaoManagerFactory.getDAOManager();

		try
		{
			if((adas_datosAntSistema != null) && StringUtils.isValidString(as_idTurnoHistoria))
			{
				Long             ll_idTurnoHistoria;
				TurnoHistoria    lth_turnoHistoria;
				TurnoHistoriaDAO lth_DAO;

				ll_idTurnoHistoria     = NumericUtils.getLongWrapper(as_idTurnoHistoria);
				lth_turnoHistoria      = new TurnoHistoria();
				lth_DAO                = DaoCreator.getTurnoHistoriaDAO(ldm_manager);

				lth_turnoHistoria.setIdTurnoHistoria(ll_idTurnoHistoria);

				lth_turnoHistoria = lth_DAO.findById(lth_turnoHistoria);

				if(lth_turnoHistoria != null)
				{
					AccConsultaCriterioAntiguoSistema lccas_consultaCriterios;
					AccConsultaCriteriosAntSistemaDAO lccas_DAO;
					DatosAntSistemaDAO                ldas_datosAntSistema;
					int                               li_secuencia;
					String                            ls_idDantosAntSistema;
					UtilDAO                           lud_DAO;

					lccas_consultaCriterios     = new AccConsultaCriterioAntiguoSistema();
					lccas_DAO                   = DaoCreator.getAccConsultaCriteriosAntSistemaDAO(ldm_manager);
					lud_DAO                     = DaoCreator.getUtilDAO(ldm_manager);
					li_secuencia                = lud_DAO.findSecuence(
						    ConsultasUtilidades.CS_ACC_DATOS_ANT_SISTEMA_SEC
						);

					if(li_secuencia > 0)
					{
						ls_idDantosAntSistema     = StringUtils.getString(li_secuencia);
						ldas_datosAntSistema      = DaoCreator.getDatosAntSistemaDAO(ldm_manager);

						adas_datosAntSistema.setIdDatosAntSistema(ls_idDantosAntSistema);
						adas_datosAntSistema.setIdUsuario(as_userId);
						adas_datosAntSistema.setIpCreacion(as_remoteIp);

						ldas_datosAntSistema.insertOrUpdate(adas_datosAntSistema, true);

						lccas_consultaCriterios.setIdTurnoHistoria(ll_idTurnoHistoria);

						lccas_consultaCriterios = lccas_DAO.findByIdTurnoHistoria(lccas_consultaCriterios);

						if(lccas_consultaCriterios != null)
						{
							lccas_consultaCriterios.setIdDatosAntSistema(ls_idDantosAntSistema);
							lccas_consultaCriterios.setIdUsuarioModificacion(as_userId);
							lccas_consultaCriterios.setIpModificacion(as_remoteIp);

							lccas_DAO.update(lccas_consultaCriterios);
						}
						else
						{
							int li_secuencia2;

							lccas_consultaCriterios     = new AccConsultaCriterioAntiguoSistema();
							li_secuencia2               = lccas_DAO.findSecuencia();

							if(li_secuencia2 > 0)
							{
								lccas_consultaCriterios.setIdCriterioAntSistema(StringUtils.getString(li_secuencia2));
								lccas_consultaCriterios.setIdTurnoHistoria(ll_idTurnoHistoria);
								lccas_consultaCriterios.setIdDatosAntSistema(ls_idDantosAntSistema);
								lccas_consultaCriterios.setIdUsuarioCreacion(as_userId);
								lccas_consultaCriterios.setIpCreacion(as_remoteIp);

								lccas_DAO.insert(lccas_consultaCriterios);
							}
						}
					}
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("antSistemaSalvarActualizar101", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}
	}

	/**
	 * Método encargado de salvar el proceso de antiguo sistema en etapa 101 cuando el flujo es asociar matrícula.
	 *
	 * @param acsma_datosBandejaActosInsertar Colección de matrículas actos a asociar en el proceso.
	 * @param acsm_datosBandejaPrediosInsertar Colección de matrículas a asociar en el proceso.
	 * @param as_userId Variable de tipo String que contiene el id del usuario que está realizando el proceso.
	 * @param as_ipRemote Variable de tipo String que contiene el id del servidor desde el cual se está realizando el proceso.
	 * @param as_idTurnoHistoria Variable de tipo String que contiene el id turno historia para un proceso determinado.
	 * @param as_observaciones Variable de tipo String que contiene las observaciones del usuario para el turno historia que está en proceso.
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public synchronized void antSistemaSalvarAsociarMatricula(
	    Collection<SolicitudMatriculaActo> acsma_datosBandejaActosInsertar,
	    Collection<SolicitudMatricula>     acsm_datosBandejaPrediosInsertar, String as_userId, String as_ipRemote,
	    String                             as_idTurnoHistoria, String as_observaciones
	)
	    throws B2BException
	{
		DAOManager ldm_manager;

		ldm_manager = DaoManagerFactory.getDAOManager();

		try
		{
			if(
			    CollectionUtils.isValidCollection(acsm_datosBandejaPrediosInsertar)
				    && StringUtils.isValidString(as_idTurnoHistoria)
			)
				salvarAsociarMatricula(
				    acsm_datosBandejaPrediosInsertar, acsma_datosBandejaActosInsertar, ldm_manager, as_userId,
				    as_ipRemote, as_idTurnoHistoria
				);
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("antSistemaSalvarAsociarMatricula", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}
	}

	/**
	 * Método encargado de salvar el proceso de antiguo sistema cuando el flujo es asociar matrícula individual.
	 *
	 * @param acdcpc_data Colección de matrículas que se van a asociar al proceso.
	 * @param as_userId Variable de tipo String que contiene el id del usuario que está realizando el proceso.
	 * @param as_ipRemote Variable de tipo String que contiene la ip del usuario que está realizando el proceso.
	 * @param as_turnoHistoria Variable de tipo String que contiene el id turno historia para un proceso determinado.
	 * @param as_observaciones Variable de tipo String que contiene las observaciones del usuario para el turno historia que está en proceso.
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public synchronized void antSistemaSalvarAsociarMatriculaIndividual(
	    Collection<DataConsultaPorCriterio> acdcpc_data, String as_userId, String as_ipRemote, String as_turnoHistoria,
	    String as_observaciones
	)
	    throws B2BException
	{
		DAOManager ldm_manager;

		ldm_manager = DaoManagerFactory.getDAOManager();

		try
		{
			if(CollectionUtils.isValidCollection(acdcpc_data) && StringUtils.isValidString(as_turnoHistoria))
			{
				TurnoHistoria    lth_turnoHistoria;
				TurnoHistoriaDAO lth_DAO;

				lth_turnoHistoria     = new TurnoHistoria();
				lth_DAO               = DaoCreator.getTurnoHistoriaDAO(ldm_manager);

				lth_turnoHistoria.setIdTurnoHistoria(NumericUtils.getLongWrapper(as_turnoHistoria));

				lth_turnoHistoria = lth_DAO.findById(lth_turnoHistoria);

				if(lth_turnoHistoria != null)
				{
					String ls_idSolicitud;

					ls_idSolicitud = lth_turnoHistoria.getIdSolicitud();

					if(StringUtils.isValidString(ls_idSolicitud))
					{
						Solicitud    ls_solicitud;
						SolicitudDAO ls_DAO;

						ls_solicitud     = new Solicitud();
						ls_DAO           = DaoCreator.getSolicitudDAO(ldm_manager);

						ls_solicitud.setIdSolicitud(ls_idSolicitud);

						ls_solicitud = ls_DAO.findById(ls_solicitud);

						if(ls_solicitud != null)
						{
							Acto                      la_acto;
							ActoDAO                   la_DAO;
							boolean                   lb_actos;
							boolean                   lb_save;
							Collection<Acto>          lca_actos;
							SolicitudMatriculaDAO     lsm_DAO;
							SolicitudMatriculaActoDAO lsma_DAO;

							la_acto      = new Acto();
							la_DAO       = DaoCreator.getActoDAO(ldm_manager);
							lb_actos     = false;
							lb_save      = false;
							lsm_DAO      = DaoCreator.getSolicitudMatriculaDAO(ldm_manager);
							lsma_DAO     = DaoCreator.getSolicitudMatriculaActoDAO(ldm_manager);

							la_acto.setIdSolicitud(ls_idSolicitud);

							lca_actos = la_DAO.findByIdSolicitud(la_acto);

							if(CollectionUtils.isValidCollection(lca_actos))
								lb_actos = true;

							for(DataConsultaPorCriterio ldcpc_iterador : acdcpc_data)
							{
								if(ldcpc_iterador != null)
								{
									if(ldcpc_iterador.isAsociarMatricula())
									{
										Long   ll_idMatricula;
										String ls_idCirculo;

										ll_idMatricula     = ldcpc_iterador.getIdMatricula();
										ls_idCirculo       = ldcpc_iterador.getIdCirculo();

										if(
										    StringUtils.isValidString(ls_idCirculo)
											    && NumericUtils.isValidLong(ll_idMatricula)
										)
										{
											SolicitudMatricula lsm_solicitudMatricula;
											SolicitudMatricula lsm_solicitudMatriculaTemp;

											lsm_solicitudMatricula = new SolicitudMatricula();

											lsm_solicitudMatricula.setIdCirculo(ls_idCirculo);
											lsm_solicitudMatricula.setIdMatricula(NumericUtils.getLong(ll_idMatricula));
											lsm_solicitudMatricula.setIdSolicitud(ls_idSolicitud);

											lsm_solicitudMatriculaTemp = lsm_DAO.findById(lsm_solicitudMatricula);

											if(lsm_solicitudMatriculaTemp != null)
											{
												lsm_solicitudMatricula.setExpedirCertificado(
												    lsm_solicitudMatriculaTemp.getExpedirCertificado()
												);
												lsm_solicitudMatricula.setIdUsuarioModificacion(as_userId);
												lsm_solicitudMatricula.setIpModificacion(as_ipRemote);
												lsm_DAO.insertOrUpdate(lsm_solicitudMatricula, false);
											}
											else
											{
												lsm_solicitudMatricula.setEstado(EstadoCommon.A);
												lsm_solicitudMatricula.setExpedirCertificado(EstadoCommon.N);
												lsm_solicitudMatricula.setCantidadCertificados(
												    NumericUtils.getBigDecimal(0D)
												);
												lsm_solicitudMatricula.setIdUsuarioCreacion(as_userId);
												lsm_solicitudMatricula.setIpCreacion(as_ipRemote);
												lsm_DAO.insertOrUpdate(lsm_solicitudMatricula, true);
											}

											lb_save = true;

											if(lb_actos)
											{
												SolicitudMatriculaActo lsma_matriculaActo;

												lsma_matriculaActo = new SolicitudMatriculaActo();

												lsma_matriculaActo.setIdSolicitud(ls_idSolicitud);
												lsma_matriculaActo.setIdCirculo(ls_idCirculo);
												lsma_matriculaActo.setIdMatricula(NumericUtils.getLong(ll_idMatricula));
												lsma_matriculaActo.setIdTurno(lth_turnoHistoria.getIdTurno());
												lsma_matriculaActo.setIdUsuarioCreacion(as_userId);
												lsma_matriculaActo.setIpCreacion(as_ipRemote);

												for(Acto la_iterador : lca_actos)
												{
													if(la_iterador != null)
													{
														boolean lb_asociar;
														String  ls_activoPrecalificacion;

														lb_asociar                   = false;
														ls_activoPrecalificacion     = la_iterador
																.getActivoPrecalificacion();

														if(StringUtils.isValidString(ls_activoPrecalificacion))
														{
															if(
															    ls_activoPrecalificacion.equalsIgnoreCase(
																        EstadoCommon.S
																    )
															)
																lb_asociar = true;
														}
														else
															lb_asociar = true;

														if(lb_asociar)
														{
															lsma_matriculaActo.setIdActo(la_iterador.getIdActo());

															lsma_DAO.insertOrUpdate(lsma_matriculaActo, true);
														}
													}
												}
											}

											{
												PredioRegistro    lpr_pr;
												PredioRegistroDAO lpr_DAO;

												lpr_pr      = new PredioRegistro();
												lpr_DAO     = DaoCreator.getPredioRegistroDAO(ldm_manager);

												lpr_pr.setIdCirculo(ls_idCirculo);
												lpr_pr.setIdMatricula(NumericUtils.getLong(ll_idMatricula));

												lpr_pr = lpr_DAO.findById(lpr_pr);

												if(lpr_pr != null)
												{
													lpr_pr.setTurnoBloqueo(lth_turnoHistoria.getIdTurno());

													lpr_pr.setIdUsuarioModificacion(as_userId);
													lpr_pr.setIpModificacion(as_ipRemote);

													lpr_DAO.insertOrUpdate(lpr_pr, false);
												}
											}
										}
										else
											throw new B2BException(ErrorKeys.MATRICULA_INMOBILIARIA_INVALIDA_FORMATO);
									}
								}
								else
									throw new B2BException(ErrorKeys.ERROR_ASOCIAR_MATRICULA);
							}

							if(lb_save)
							{
								MotivoTramite    lmt_motivo;
								MotivoTramiteDAO lmtd_DAO;
								long             ll_idMotivo;

								lmt_motivo      = new MotivoTramite();
								lmtd_DAO        = DaoCreator.getMotivoTramiteDAO(ldm_manager);
								ll_idMotivo     = MotivoTramiteCommon.ASOCIAR_MATRICULA_DESDE_ANTIGUO_SISTEMA;

								lmt_motivo.setIdEtapaOrigen(EtapaCommon.ID_ETAPA_ANTIGUO_SISTEMA);
								lmt_motivo.setIdMotivo(ll_idMotivo);

								lmt_motivo = lmtd_DAO.findById(lmt_motivo);

								if(lmt_motivo != null)
								{
									if(lth_turnoHistoria != null)
									{
										lth_turnoHistoria.setEstadoActividad(EstadoCommon.TERMINADA);
										lth_turnoHistoria.setMotivo(lmt_motivo.getDescripcion());
										lth_turnoHistoria.setIdMotivo(
										    NumericUtils.getLongWrapper(lmt_motivo.getIdMotivo())
										);
										lth_turnoHistoria.setObservaciones(as_observaciones);
										lth_turnoHistoria.setIdUsuarioModificacion(as_userId);
										lth_turnoHistoria.setIpModificacion(as_ipRemote);

										lth_DAO.insertOrUpdate(lth_turnoHistoria, false);

										DaoCreator.getProcedimientosDAO(ldm_manager).spCreateStage(lth_turnoHistoria);
									}
								}
							}
							else
								throw new B2BException(ErrorKeys.ERROR_ASOCIAR_MATRICULA);
						}
						else
							throw new B2BException(ErrorKeys.NO_SE_ENCONTRO_SOLICITUD);
					}
					else
						throw new B2BException(ErrorKeys.NO_SE_ENCONTRO_SOLICITUD);
				}
				else
					throw new B2BException(ErrorKeys.NO_SE_ENCONTRO_TURNO_HISTORIA);
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("antSistemaSalvarAsociarMatriculaIndividual", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}
	}

	/**
	 * Método encargado de salvar el proceso de antiguo sistema en la etapa 101 cuando el flujo es asociar matrícula con base a la busqueda de antiguo sistema.
	 *
	 * @param amsb_archivos Colección que contiene los documentos asociados al informe de búsqueda.
	 * @param ad_documento Documento salida que se va a guardar.
	 * @param acdcpc_data Datos de antiguo sistema usados para realizar la consulta manual.
	 * @param as_userId Variable de tipo String que contiene el id del usuario que está realizando el proceso.
	 * @param as_localIpAddress Variable de tipo String que contiene la ip del usuario que está realizando el proceso.
	 * @param as_ipRemote Variable de tipo String que contiene la ip del servidor desde el cual se está realizando el proceso.
	 * @param as_observaciones Variable de tipo String que contiene las observaciones del usuario para el turno historia que está en proceso.
	 * @param as_idTurnoHistoria Variable de tipo String que contiene el id turno historia para un proceso determinado.
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public synchronized void antSistemaSalvarAsociarMatriculaIndividual101(
	    Map<String, byte[]> amsb_archivos, DocumentosSalida ad_documento,
	    Collection<DataConsultaPorCriterio> acdcpc_data, String as_userId, String as_localIpAddress, String as_ipRemote,
	    String as_observaciones, String as_idTurnoHistoria
	)
	    throws B2BException
	{
		DAOManager ldm_manager;

		ldm_manager = DaoManagerFactory.getDAOManager();

		try
		{
			if(CollectionUtils.isValidCollection(acdcpc_data) && StringUtils.isValidString(as_idTurnoHistoria))
			{
				TurnoHistoria    lth_turnoHistoria;
				TurnoHistoriaDAO lth_DAO;

				lth_turnoHistoria     = new TurnoHistoria();
				lth_DAO               = DaoCreator.getTurnoHistoriaDAO(ldm_manager);

				lth_turnoHistoria.setIdTurnoHistoria(NumericUtils.getLongWrapper(as_idTurnoHistoria));

				lth_turnoHistoria = lth_DAO.findById(lth_turnoHistoria);

				if(lth_turnoHistoria != null)
				{
					String ls_idSolicitud;

					ls_idSolicitud = lth_turnoHistoria.getIdSolicitud();

					if(StringUtils.isValidString(ls_idSolicitud))
					{
						Solicitud    ls_solicitud;
						SolicitudDAO ls_DAO;

						ls_solicitud     = new Solicitud();
						ls_DAO           = DaoCreator.getSolicitudDAO(ldm_manager);

						ls_solicitud.setIdSolicitud(ls_idSolicitud);

						ls_solicitud = ls_DAO.findById(ls_solicitud);

						if(ls_solicitud != null)
						{
							Acto                      la_acto;
							ActoDAO                   la_DAO;
							boolean                   lb_actos;
							boolean                   lb_save;
							Collection<Acto>          lca_actos;
							SolicitudMatriculaDAO     lsm_DAO;
							SolicitudMatriculaActoDAO lsma_DAO;

							la_acto      = new Acto();
							la_DAO       = DaoCreator.getActoDAO(ldm_manager);
							lb_actos     = false;
							lb_save      = false;
							lsm_DAO      = DaoCreator.getSolicitudMatriculaDAO(ldm_manager);
							lsma_DAO     = DaoCreator.getSolicitudMatriculaActoDAO(ldm_manager);

							la_acto.setIdSolicitud(ls_idSolicitud);

							lca_actos = la_DAO.findByIdSolicitud(la_acto);

							if(CollectionUtils.isValidCollection(lca_actos))
								lb_actos = true;

							for(DataConsultaPorCriterio ldcpc_iterador : acdcpc_data)
							{
								if(ldcpc_iterador != null)
								{
									if(ldcpc_iterador.isAsociarMatricula())
									{
										Long   ll_idMatricula;
										String ls_idCirculo;

										ll_idMatricula     = ldcpc_iterador.getIdMatricula();
										ls_idCirculo       = ldcpc_iterador.getIdCirculo();

										if(
										    StringUtils.isValidString(ls_idCirculo)
											    && NumericUtils.isValidLong(ll_idMatricula)
										)
										{
											SolicitudMatricula lsm_solicitudMatricula;
											SolicitudMatricula lsm_solicitudMatriculaTemp;

											lsm_solicitudMatricula = new SolicitudMatricula();

											lsm_solicitudMatricula.setIdCirculo(ls_idCirculo);
											lsm_solicitudMatricula.setIdMatricula(NumericUtils.getLong(ll_idMatricula));
											lsm_solicitudMatricula.setIdSolicitud(ls_idSolicitud);

											lsm_solicitudMatriculaTemp = lsm_DAO.findById(lsm_solicitudMatricula);

											if(lsm_solicitudMatriculaTemp != null)
											{
												lsm_solicitudMatricula.setIdUsuarioModificacion(as_userId);
												lsm_solicitudMatricula.setIpModificacion(as_localIpAddress);
												lsm_DAO.insertOrUpdate(lsm_solicitudMatricula, false);
											}
											else
											{
												lsm_solicitudMatricula.setEstado(EstadoCommon.A);
												lsm_solicitudMatricula.setExpedirCertificado(EstadoCommon.N);
												lsm_solicitudMatricula.setCantidadCertificados(
												    NumericUtils.getBigDecimal(0D)
												);
												lsm_solicitudMatricula.setIdUsuarioCreacion(as_userId);
												lsm_solicitudMatricula.setIpCreacion(as_localIpAddress);
												lsm_DAO.insertOrUpdate(lsm_solicitudMatricula, true);
											}

											lb_save = true;

											if(lb_actos)
											{
												SolicitudMatriculaActo lsma_matriculaActo;

												lsma_matriculaActo = new SolicitudMatriculaActo();

												lsma_matriculaActo.setIdSolicitud(ls_idSolicitud);
												lsma_matriculaActo.setIdCirculo(ls_idCirculo);
												lsma_matriculaActo.setIdMatricula(NumericUtils.getLong(ll_idMatricula));
												lsma_matriculaActo.setIdTurno(lth_turnoHistoria.getIdTurno());
												lsma_matriculaActo.setIdUsuarioCreacion(as_userId);
												lsma_matriculaActo.setIpCreacion(as_localIpAddress);

												for(Acto la_iterador : lca_actos)
												{
													boolean lb_asociar;
													String  ls_activoPrecalificacion;

													lb_asociar                   = false;
													ls_activoPrecalificacion     = la_iterador.getActivoPrecalificacion();

													if(StringUtils.isValidString(ls_activoPrecalificacion))
													{
														if(ls_activoPrecalificacion.equalsIgnoreCase(EstadoCommon.S))
															lb_asociar = true;
													}
													else
														lb_asociar = true;

													if(lb_asociar)
													{
														lsma_matriculaActo.setIdActo(la_iterador.getIdActo());

														lsma_DAO.insertOrUpdate(lsma_matriculaActo, true);
													}
												}
											}

											{
												PredioRegistro    lpr_pr;
												PredioRegistroDAO lpr_DAO;

												lpr_pr      = new PredioRegistro();
												lpr_DAO     = DaoCreator.getPredioRegistroDAO(ldm_manager);

												lpr_pr.setIdCirculo(ls_idCirculo);
												lpr_pr.setIdMatricula(NumericUtils.getLong(ll_idMatricula));

												lpr_pr = lpr_DAO.findById(lpr_pr);

												if(lpr_pr != null)
												{
													lpr_pr.setTurnoBloqueo(lth_turnoHistoria.getIdTurno());

													lpr_pr.setIdUsuarioModificacion(as_userId);
													lpr_pr.setIpModificacion(as_ipRemote);

													lpr_DAO.insertOrUpdate(lpr_pr, false);
												}
											}
										}
										else
											throw new B2BException(ErrorKeys.MATRICULA_INMOBILIARIA_INVALIDA_FORMATO);
									}
								}
								else
									throw new B2BException(ErrorKeys.ERROR_ASOCIAR_MATRICULA);
							}

							if(CollectionUtils.isValidCollection(amsb_archivos))
								guardarDocumentosPdf(
								    ad_documento, amsb_archivos, ldm_manager, as_userId, as_localIpAddress, as_ipRemote
								);

							if(lb_save)
							{
								MotivoTramite    lmt_motivo;
								MotivoTramiteDAO lmtd_DAO;
								long             ll_idMotivo;

								lmt_motivo      = new MotivoTramite();
								lmtd_DAO        = DaoCreator.getMotivoTramiteDAO(ldm_manager);
								ll_idMotivo     = MotivoTramiteCommon.ASOCIAR_MATRICULA_PARA_CALIFICACION;

								lmt_motivo.setIdEtapaOrigen(EtapaCommon.ID_ETAPA_CALIFICACION_ANTIGUO_SISTEMA);
								lmt_motivo.setIdMotivo(ll_idMotivo);

								lmt_motivo = lmtd_DAO.findById(lmt_motivo);

								if(lmt_motivo != null)
								{
									if(lth_turnoHistoria != null)
									{
										lth_turnoHistoria.setEstadoActividad(EstadoCommon.TERMINADA);
										lth_turnoHistoria.setMotivo(lmt_motivo.getDescripcion());
										lth_turnoHistoria.setIdMotivo(
										    NumericUtils.getLongWrapper(lmt_motivo.getIdMotivo())
										);
										lth_turnoHistoria.setObservaciones(as_observaciones);
										lth_turnoHistoria.setIdUsuarioModificacion(as_userId);
										lth_turnoHistoria.setIpModificacion(as_ipRemote);

										lth_DAO.insertOrUpdate(lth_turnoHistoria, false);

										DaoCreator.getProcedimientosDAO(ldm_manager).spCreateStage(lth_turnoHistoria);
									}
								}
							}
							else
								throw new B2BException(ErrorKeys.ERROR_ASOCIAR_MATRICULA);
						}
						else
							throw new B2BException(ErrorKeys.NO_SE_ENCONTRO_SOLICITUD);
					}
					else
						throw new B2BException(ErrorKeys.NO_SE_ENCONTRO_SOLICITUD);
				}
				else
					throw new B2BException(ErrorKeys.NO_SE_ENCONTRO_TURNO_HISTORIA);
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("antSistemaSalvarAsociarMatriculaIndividual101", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}
	}

	/**
	 * Método encargado de salvar el proceso de antiguo sistema cuando el flujo es crear matrículas.
	 *
	 * @param lcapr_capr Colección de matrículas que se van a crear.
	 * @param as_userId Variable de tipo String que contiene el id del usuario que está realizando el proceso.
	 * @param as_localIpAddress Variable de tipo String que contiene la ip del usuario que está realizando el proceso.
	 * @param as_ipRemote Variable de tipo String que contiene la ip del servidor en el cual se está realizando el proceso.
	 * @param as_idTurnoHistoria Variable de tipo String que contiene el id turno historia para un proceso determinado.
	 * @param as_observaciones Variable de tipo String que contiene las observaciones del usuario para el turno historia que está en proceso.
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public synchronized void antSistemaSalvarCrear(
	    Collection<AccPredioRegistro> lcapr_capr, String as_userId, String as_localIpAddress, String as_ipRemote,
	    String as_idTurnoHistoria, String as_observaciones
	)
	    throws B2BException
	{
		DAOManager ldm_manager;

		ldm_manager = DaoManagerFactory.getDAOManager();

		try
		{
			if(CollectionUtils.isValidCollection(lcapr_capr) && StringUtils.isValidString(as_idTurnoHistoria))
			{
				MotivoTramite    lmt_motivo;
				MotivoTramiteDAO lmtd_DAO;
				long             ll_idMotivo;

				lmt_motivo      = new MotivoTramite();
				lmtd_DAO        = DaoCreator.getMotivoTramiteDAO(ldm_manager);
				ll_idMotivo     = MotivoTramiteCommon.CREAR_MATRICULA_DESDE_ANTIGUO_SISTEMA;

				lmt_motivo.setIdEtapaOrigen(EtapaCommon.ID_ETAPA_ANTIGUO_SISTEMA);
				lmt_motivo.setIdMotivo(ll_idMotivo);

				lmt_motivo = lmtd_DAO.findById(lmt_motivo);

				if(lmt_motivo != null)
				{
					TurnoHistoria    lth_turnoHistoria;
					TurnoHistoriaDAO lth_DAO;

					lth_turnoHistoria     = new TurnoHistoria();
					lth_DAO               = DaoCreator.getTurnoHistoriaDAO(ldm_manager);

					lth_turnoHistoria.setIdTurnoHistoria(NumericUtils.getLongWrapper(as_idTurnoHistoria));

					lth_turnoHistoria = lth_DAO.findById(lth_turnoHistoria);

					if(lth_turnoHistoria != null)
					{
						lth_turnoHistoria.setEstadoActividad(EstadoCommon.TERMINADA);
						lth_turnoHistoria.setMotivo(lmt_motivo.getDescripcion());
						lth_turnoHistoria.setIdMotivo(NumericUtils.getLongWrapper(lmt_motivo.getIdMotivo()));
						lth_turnoHistoria.setObservaciones(as_observaciones);
						lth_turnoHistoria.setIdUsuarioModificacion(as_userId);
						lth_turnoHistoria.setIpModificacion(as_ipRemote);

						lth_DAO.insertOrUpdate(lth_turnoHistoria, false);

						DaoCreator.getProcedimientosDAO(ldm_manager).spCreateStage(lth_turnoHistoria);
					}
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("antSistemaSalvarCrear", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}
	}

	/**
	 * Método encargado de salvar el proceso de antiguo sistema cuando el flujo es crear matrículas y la etapa es 101.
	 *
	 * @param amsb_archivos Colección que contiene los documentos asociados al informe de búsqueda.
	 * @param ad_documento Documento salida que se va a guardar.
	 * @param acapr_capr Colección que contiene las matrículas creadas.
	 * @param as_userId Variable de tipo String que contiene el id del usuario que está realizando el proceso.
	 * @param as_localIpAddress Variable de tipo String que contiene la ip del usuario que está realizando el proceso.
	 * @param as_ipRemote Variable de tipo String que contiene la ip del servidor desde el cual se está realizando el proceso.
	 * @param as_observaciones Variable de tipo String que contiene las observaciones del usuario para el turno historia que está en proceso.
	 * @param as_idTurnoHistoria Variable de tipo String que contiene el id turno historia para un proceso determinado.
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public synchronized void antSistemaSalvarCrear101(
	    Map<String, byte[]> amsb_archivos, DocumentosSalida ad_documento, Collection<AccPredioRegistro> acapr_capr,
	    String as_userId, String as_localIpAddress, String as_ipRemote, String as_observaciones,
	    String as_idTurnoHistoria
	)
	    throws B2BException
	{
		DAOManager ldm_manager;

		ldm_manager = DaoManagerFactory.getDAOManager();

		try
		{
			if(CollectionUtils.isValidCollection(acapr_capr) && StringUtils.isValidString(as_idTurnoHistoria))
			{
				if(CollectionUtils.isValidCollection(amsb_archivos))
					guardarDocumentosPdf(
					    ad_documento, amsb_archivos, ldm_manager, as_userId, as_localIpAddress, as_ipRemote
					);

				MotivoTramite    lmt_motivo;
				MotivoTramiteDAO lmtd_DAO;
				long             ll_idMotivo;

				lmt_motivo      = new MotivoTramite();
				lmtd_DAO        = DaoCreator.getMotivoTramiteDAO(ldm_manager);
				ll_idMotivo     = MotivoTramiteCommon.CREAR_MATRICULA_DESDE_ANTIGUO_SISTEMA_101;

				lmt_motivo.setIdEtapaOrigen(EtapaCommon.ID_ETAPA_CALIFICACION_ANTIGUO_SISTEMA);
				lmt_motivo.setIdMotivo(ll_idMotivo);

				lmt_motivo = lmtd_DAO.findById(lmt_motivo);

				if(lmt_motivo != null)
				{
					TurnoHistoria    lth_turnoHistoria;
					TurnoHistoriaDAO lth_DAO;

					lth_turnoHistoria     = new TurnoHistoria();
					lth_DAO               = DaoCreator.getTurnoHistoriaDAO(ldm_manager);

					lth_turnoHistoria.setIdTurnoHistoria(NumericUtils.getLongWrapper(as_idTurnoHistoria));

					lth_turnoHistoria = lth_DAO.findById(lth_turnoHistoria);

					if(lth_turnoHistoria != null)
					{
						lth_turnoHistoria.setEstadoActividad(EstadoCommon.TERMINADA);
						lth_turnoHistoria.setMotivo(lmt_motivo.getDescripcion());
						lth_turnoHistoria.setIdMotivo(NumericUtils.getLongWrapper(lmt_motivo.getIdMotivo()));
						lth_turnoHistoria.setObservaciones(as_observaciones);
						lth_turnoHistoria.setIdUsuarioModificacion(as_userId);
						lth_turnoHistoria.setIpModificacion(as_ipRemote);

						lth_DAO.insertOrUpdate(lth_turnoHistoria, false);

						DaoCreator.getProcedimientosDAO(ldm_manager).spCreateStage(lth_turnoHistoria);
					}
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("antSistemaSalvarCrear101", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}
	}

	/**
	 * Método encargado de salvar la solicitud rechazada en antiguo sistema.
	 *
	 * @param atc_tiposCausales Objeto que contiene los tipo causales que se usaron para generar el rechazo de la solicitud.
	 * @param as_idTurnoHistoria Variable de tipo String que contiene el id turno historia para un proceso determinado.
	 * @param aahr_ampliacionHistoriaRegistral Objeto para validar si se desea hacer ampliación registral en el proceso.
	 * @param as_userId Variable de tipo String que contiene el id del usuario que está realizando el proceso.
	 * @param as_localIpAddress Variable de tipo String que contiene la ip del usuario que está realizando el proceso.
	 * @param as_ipRemote Variable de tipo String que contiene la ip del servidor en el cual se está realizando el proceso.
	 * @param as_observaciones Variable de tipo String que contiene las observaciones del usuario para el turno historia que está en proceso.
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public synchronized void antSistemaSalvarRechazar(
	    TipoCausal atc_tiposCausales, String as_idTurnoHistoria,
	    AmpliacionHistoriaRegistral aahr_ampliacionHistoriaRegistral, String as_userId, String as_localIpAddress,
	    String as_ipRemote, String as_observaciones
	)
	    throws B2BException
	{
		DAOManager ldm_manager;

		ldm_manager = DaoManagerFactory.getDAOManager();

		try
		{
			if(
			    (atc_tiposCausales != null) && (aahr_ampliacionHistoriaRegistral != null)
				    && StringUtils.isValidString(as_idTurnoHistoria)
			)
			{
				MotivoTramite    lmt_motivo;
				MotivoTramiteDAO lmtd_DAO;
				long             ll_idMotivo;

				lmt_motivo      = new MotivoTramite();
				lmtd_DAO        = DaoCreator.getMotivoTramiteDAO(ldm_manager);
				ll_idMotivo     = MotivoTramiteCommon.RECHAZAR_SOLICITUD_DESDE_ANTIGUO_SISTEMA;

				lmt_motivo.setIdEtapaOrigen(EtapaCommon.ID_ETAPA_ANTIGUO_SISTEMA);
				lmt_motivo.setIdMotivo(ll_idMotivo);

				lmt_motivo = lmtd_DAO.findById(lmt_motivo);

				if(lmt_motivo != null)
				{
					TurnoHistoria    lth_turnoHistoria;
					TurnoHistoriaDAO lth_DAO;

					lth_turnoHistoria     = new TurnoHistoria();
					lth_DAO               = DaoCreator.getTurnoHistoriaDAO(ldm_manager);

					lth_turnoHistoria.setIdTurnoHistoria(NumericUtils.getLongWrapper(as_idTurnoHistoria));

					lth_turnoHistoria = lth_DAO.findById(lth_turnoHistoria);

					if(lth_turnoHistoria != null)
					{
						lth_turnoHistoria.setEstadoActividad(EstadoCommon.TERMINADA);
						lth_turnoHistoria.setMotivo(lmt_motivo.getDescripcion());
						lth_turnoHistoria.setIdMotivo(NumericUtils.getLongWrapper(lmt_motivo.getIdMotivo()));
						lth_turnoHistoria.setObservaciones(as_observaciones);
						lth_turnoHistoria.setIdUsuarioModificacion(as_userId);
						lth_turnoHistoria.setIpModificacion(as_ipRemote);

						lth_DAO.insertOrUpdate(lth_turnoHistoria, false);

						DaoCreator.getProcedimientosDAO(ldm_manager).spCreateStage(lth_turnoHistoria);
					}
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("antSistemaSalvarCrear", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}
	}

	/**
	 * Método encargado de salvar el proceso de antiguo sistema para la etapa 101 cuando el flujo es realizar informe de búsqueda.
	 *
	 * @param amsb_archivos Colección que contiene los documentos asociados al informe de búsqueda.
	 * @param as_idTurnoHistoria Variable de tipo String que contiene el id turno historia para un proceso determinado.
	 * @param ad_documento Documento que se va a salvar.
	 * @param as_userId Variable de tipo String que contiene el id del usuario que está realizando el proceso.
	 * @param as_localIpAddress Variable de tipo String que contiene la ip del usuario que está realizando el proceso.
	 * @param as_ipRemote Variable de tipo String que contiene la ip del servidor desde el cual se está realizando el proceso.
	 * @param as_observaciones Variable de tipo String que contiene las observaciones del usuario para el turno historia que está en proceso.
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public synchronized void antsistemaSalvarInformeBusqueda(
	    Map<String, byte[]> amsb_archivos, String as_idTurnoHistoria, DocumentosSalida ad_documento, String as_userId,
	    String as_localIpAddress, String as_ipRemote, String as_observaciones
	)
	    throws B2BException
	{
		DAOManager ldm_manager;

		ldm_manager = DaoManagerFactory.getDAOManager();

		try
		{
			guardarDocumentosPdf(ad_documento, amsb_archivos, ldm_manager, as_userId, as_localIpAddress, as_ipRemote);

			MotivoTramite    lmt_motivo;
			MotivoTramiteDAO lmtd_DAO;
			long             ll_idMotivo;

			lmt_motivo      = new MotivoTramite();
			lmtd_DAO        = DaoCreator.getMotivoTramiteDAO(ldm_manager);
			ll_idMotivo     = MotivoTramiteCommon.INFORME_DE_BUSQUEDA_PARA_CALIFICACION;

			lmt_motivo.setIdEtapaOrigen(EtapaCommon.ID_ETAPA_CALIFICACION_ANTIGUO_SISTEMA);
			lmt_motivo.setIdMotivo(ll_idMotivo);

			lmt_motivo = lmtd_DAO.findById(lmt_motivo);

			if(lmt_motivo != null)
			{
				TurnoHistoria    lth_turnoHistoria;
				TurnoHistoriaDAO lth_DAO;

				lth_turnoHistoria     = new TurnoHistoria();
				lth_DAO               = DaoCreator.getTurnoHistoriaDAO(ldm_manager);

				lth_turnoHistoria.setIdTurnoHistoria(NumericUtils.getLongWrapper(as_idTurnoHistoria));

				lth_turnoHistoria = lth_DAO.findById(lth_turnoHistoria);

				if(lth_turnoHistoria != null)
				{
					lth_turnoHistoria.setEstadoActividad(EstadoCommon.TERMINADA);
					lth_turnoHistoria.setMotivo(lmt_motivo.getDescripcion());
					lth_turnoHistoria.setIdMotivo(NumericUtils.getLongWrapper(lmt_motivo.getIdMotivo()));
					lth_turnoHistoria.setObservaciones(as_observaciones);
					lth_turnoHistoria.setIdUsuarioModificacion(as_userId);
					lth_turnoHistoria.setIpModificacion(as_ipRemote);

					lth_DAO.insertOrUpdate(lth_turnoHistoria, false);

					DaoCreator.getProcedimientosDAO(ldm_manager).spCreateStage(lth_turnoHistoria);
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("antsistemaSalvarInformeBusqueda", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}
	}

	/**
	 *
	 * @param adb_db
	 * @param as_userId
	 * @param as_remoteIp
	 * @return
	 * @throws B2BException
	 */
	public synchronized CompletitudComplementacion buscarCompletitudComplementacionByTurno(
	    CompletitudComplementacion adb_db, String as_userId, String as_remoteIp
	)
	    throws B2BException
	{
		DAOManager ldm_manager;

		ldm_manager = DaoManagerFactory.getDAOManager();

		try
		{
			if(adb_db != null)
				adb_db = DaoCreator.getCompletitudComplementacionDAO(ldm_manager).findByTurno(adb_db);
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("buscarCompletitudComplementacionByTurno", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return adb_db;
	}

	/**
	 * Metodo encargado de buscar todos los detalles de la tabla SDB_ACC_DETALLE_ANT_SISTEMA por cada uno de los detalles
	 * enviados como parametro.
	 *
	 * @param adas_parametros Objeto que contiene los id_datos_ant_sistema necesarios para consultar sus detalles.
	 * @return Collección de datos que contiene los detalles de la tabla SDB_ACC_DETALLE_ANT_SISTEMA.
	 * @throws B2BException Señala que se ha producido una excepción
	 *
	 */
	public synchronized Collection<DetalleAntSistemaUI> buscarTodosDetallesPorSolicitud(
	    DatosAntSistema adas_parametros
	)
	    throws B2BException
	{
		Collection<DetalleAntSistemaUI> lcdasu_detalles;

		lcdasu_detalles = new LinkedList<DetalleAntSistemaUI>();

		if(adas_parametros != null)
		{
			DAOManager ldm_manager;

			ldm_manager = DaoManagerFactory.getDAOManager();

			try
			{
				DetalleAntSistemaDAO ldasd_DAO;

				ldasd_DAO = DaoCreator.getDetalleAntSistemaDAO(ldm_manager);

				if(adas_parametros != null)
				{
					DetalleAntSistema             ldas_detalle;
					Collection<DetalleAntSistema> lcdas_tmp;

					ldas_detalle = new DetalleAntSistema();
					ldas_detalle.setIdDatosAntSistema(adas_parametros.getIdDatosAntSistema());

					lcdas_tmp = ldasd_DAO.findByDatosAntSis(ldas_detalle);

					if(CollectionUtils.isValidCollection(lcdas_tmp))
					{
						DatosAntSistemaDAO     ldas_DAO;
						LibroAntiguoSistemaDao llas_DAO;

						ldas_DAO     = DaoCreator.getDatosAntSistemaDAO(ldm_manager);
						llas_DAO     = DaoCreator.getLibroAntiguoSistemaDAO(ldm_manager);

						for(DetalleAntSistema ldas_iteradorDetalle : lcdas_tmp)
						{
							if(ldas_iteradorDetalle != null)
							{
								{
									LibroAntiguoSistema las_libro;

									las_libro = new LibroAntiguoSistema();

									las_libro.setIdLibroAntiguoSistema(
									    NumericUtils.getLong(ldas_iteradorDetalle.getIdLibroAntSistema())
									);

									las_libro = llas_DAO.findById(las_libro);

									if(las_libro != null)
										ldas_iteradorDetalle.setNombreLibro(las_libro.getNombre());
								}

								{
									DetalleAntSistemaUI           ldasu_detalleUI;
									Collection<DetalleAntSistema> lcdas_detalles;

									ldas_iteradorDetalle.setIdCirculo(adas_parametros.getIdCirculo());

									ldasu_detalleUI     = new DetalleAntSistemaUI(ldas_iteradorDetalle);
									lcdas_detalles      = ldasd_DAO.findAllByDatosDetalle(ldas_iteradorDetalle);

									if(CollectionUtils.isValidCollection(lcdas_detalles))
									{
										Collection<DatosAntSistema> lcdas_datos;

										lcdas_datos = new ArrayList<DatosAntSistema>();

										for(DetalleAntSistema ldas_datos : lcdas_detalles)
										{
											if(ldas_datos != null)
											{
												DatosAntSistema ldas_antSistema;

												ldas_antSistema = new DatosAntSistema();
												ldas_antSistema.setIdDatosAntSistema(ldas_datos.getIdDatosAntSistema());

												ldas_antSistema = ldas_DAO.findById(ldas_antSistema);

												if(ldas_antSistema != null)
												{
													String ls_idCirculo;
													Long   ll_idMatricula;

													ls_idCirculo       = ldas_antSistema.getIdCirculo();
													ll_idMatricula     = ldas_antSistema.getIdMatricula();

													if(
													    StringUtils.isValidString(ls_idCirculo)
														    && NumericUtils.isValidLong(ll_idMatricula)
													)
														lcdas_datos.add(ldas_antSistema);
												}
											}
										}

										if(CollectionUtils.isValidCollection(lcdas_datos))
											ldasu_detalleUI.setMatriculas(lcdas_datos);
										else
											ldasu_detalleUI.setMatriculas(null);
									}

									lcdasu_detalles.add(ldasu_detalleUI);
								}
							}
						}
					}
				}
			}
			catch(B2BException lb2be_e)
			{
				ldm_manager.setRollbackOnly();

				clh_LOGGER.error("buscarTodosDetallesPorSolicitud", lb2be_e);

				throw lb2be_e;
			}
			finally
			{
				ldm_manager.close();
			}
		}

		if(lcdasu_detalles.isEmpty())
			lcdasu_detalles = null;

		return lcdasu_detalles;
	}

	/**
	 * Método encargado de consultar la complementacion para un predio determinado.
	 *
	 * @param as_idTurno Variable de tipo String que contiene el id turno para un proceso determinado.
	 * @param as_idTH Variable de tipo Long que contiene el id turno historia para un proceso determinado.
	 * @return  Retorna  un objeto de tipo AccComplementacionPredio que coincide con los parametros de entrada.
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see AccComplementacionPredio
	 */
	public synchronized AccComplementacionPredio cargarComplementacionPredio(String as_idTurno, Long as_idTH)
	    throws B2BException
	{
		DAOManager                 ldm_manager;
		AccComplementacionPredio   lacp_cPredio;
		CompletitudComplementacion lcc_complementacion;
		String                     ls_idTurno;
		Long                       ls_idTurnoHistoria;

		ls_idTurno              = as_idTurno;
		ls_idTurnoHistoria      = as_idTH;
		ldm_manager             = DaoManagerFactory.getDAOManager();
		lacp_cPredio            = new AccComplementacionPredio();
		lcc_complementacion     = new CompletitudComplementacion();

		if(StringUtils.isValidString(ls_idTurno))
			lcc_complementacion.setIdTurno(as_idTurno);
		else
			throw new B2BException(ErrorKeys.ERROR_SIN_ID_TURNO);

		if(NumericUtils.isValidLong(ls_idTurnoHistoria))
			lcc_complementacion.setIdTurnoHistoria(ls_idTurnoHistoria);
		else
			throw new B2BException(ErrorKeys.NO_SE_ENCONTRO_ID_TURNO_HISTORIA);

		try
		{
			lcc_complementacion = DaoCreator.getCompletitudComplementacionDAO(ldm_manager)
					                            .findByTurnoTH(lcc_complementacion);

			if(lcc_complementacion == null)
				throw new B2BException(ErrorKeys.NO_SE_ENCONTRARON_REGISTROS_COMPLEMENTACION);

			Long ls_idComplementacion;
			ls_idComplementacion = lcc_complementacion.getIdComplementacion();

			if(!NumericUtils.isValidLong(ls_idComplementacion))
				throw new B2BException(ErrorKeys.NO_SE_ENCONTRO_ID_COMPLEMENTACION);

			lacp_cPredio.setIdComplementacion(ls_idComplementacion);

			lacp_cPredio = DaoCreator.getAccComplementacionPredioDAO(ldm_manager).findById(lacp_cPredio);

			if(lacp_cPredio == null)
				throw new B2BException(ErrorKeys.NO_SE_ENCONTRARON_REGISTROS_COMPLEMENTACION);
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("cargarComplementacionPredio", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lacp_cPredio;
	}

	/**
	 * Método que se encarga de verificar si ya se han creado matrículas en Ant Sistema para el turno actual.
	 *
	 * @param as_idTurnoHistoria Variable de tipo String la cual indica el turno historia actual del proceso.
	 * @param as_idDatosAnteSistema Variable de tipo String la cual indica el id datos ant sistema.
	 * @return Variable de tipo booleana la cual si ya se han creado matrículas en Ant Sistema para el turno actual.
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public synchronized boolean cargarCrearMatricula(String as_idTurnoHistoria, String as_idDatosAnteSistema)
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
				Long             ll_idTurnoHistoria;
				TurnoHistoria    lth_turnoHistoria;
				TurnoHistoriaDAO lth_DAO;

				ll_idTurnoHistoria     = NumericUtils.getLongWrapper(as_idTurnoHistoria);
				lth_turnoHistoria      = new TurnoHistoria();
				lth_DAO                = DaoCreator.getTurnoHistoriaDAO(ldm_manager);

				lth_turnoHistoria.setIdTurnoHistoria(ll_idTurnoHistoria);

				lth_turnoHistoria = lth_DAO.findById(lth_turnoHistoria);

				if(lth_turnoHistoria != null)
				{
					AccPredioRegistro             lapr_predioRegistro;
					Collection<AccPredioRegistro> lcapr_predios;

					lapr_predioRegistro = new AccPredioRegistro();

					lapr_predioRegistro.setIdTurno(lth_turnoHistoria.getIdTurno());
					lapr_predioRegistro.setIdDatosAntSistema(as_idDatosAnteSistema);

					lcapr_predios     = DaoCreator.getAccPredioRegistroDAO(ldm_manager)
							                          .findByTurnoAntSistema(lapr_predioRegistro, true);
					lb_return         = CollectionUtils.isValidCollection(lcapr_predios);
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("cargarCrearMatricula", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lb_return;
	}

	/**
	 * Metodo que consulta todos los detalles de la tabla SDB_ACC_DATOS_ANT_SISTEMA que cumplan con los parametros enviados.
	 *
	 * @param adasu_parametros Objeto que contiene los datos necesarios para realizar la consulta detallada en la tabla SDB_ACC_DATOS_ANT_SISTEMA.
	 * @return Colección de detalles de la tabla SDB_ACC_DATOS_ANT_SISTEMA.
	 * @throws B2BException Señala que se ha producido una excepción
	 *
	 */
	public synchronized Collection<DetalleAntSistemaUI> consultaDetalleAntSistema(DetalleAntSistemaUI adasu_parametros)
	    throws B2BException
	{
		Collection<DetalleAntSistemaUI> lcdasu_retorno;
		DAOManager                      ldm_manager;

		lcdasu_retorno     = new ArrayList<DetalleAntSistemaUI>();
		ldm_manager        = DaoManagerFactory.getDAOManager();

		try
		{
			if(adasu_parametros != null)
			{
				String           ls_idCirculo;
				CirculoRegistral lcr_cr;

				lcr_cr     = adasu_parametros.getCirculoRegistral();

				ls_idCirculo = (lcr_cr != null) ? lcr_cr.getIdCirculo() : null;

				{
					Long ll_libro;

					ll_libro = adasu_parametros.getIdLibroAntSistema();

					if(!NumericUtils.isValidLong(ll_libro))
						throw new B2BException(ErrorKeys.DEBE_SELECCIONAR_LIBRO);
				}

				{
					String ls_tomo;

					ls_tomo = adasu_parametros.getTomo();

					if(!StringUtils.isValidString(ls_tomo))
						throw new B2BException(ErrorKeys.DEBE_SELECCIONAR_TOMO);
				}

				{
					String ls_tipoPartida;

					ls_tipoPartida = adasu_parametros.getPartida();

					if(!StringUtils.isValidString(ls_tipoPartida))
						throw new B2BException(ErrorKeys.DEBE_DIGITAR_TIPO_DE_PARTIDA);

					Long ll_numeroPartida;

					ll_numeroPartida = adasu_parametros.getNumeroPartida();

					if(!NumericUtils.isValidLong(ll_numeroPartida))
						throw new B2BException(ErrorKeys.DEBE_DIGITAR_NUMERO_DE_PARTIDA);

					long ll_numeroPartidaC;

					ll_numeroPartidaC = NumericUtils.getLong(ll_numeroPartida);

					if(
					    (ls_tipoPartida.equalsIgnoreCase(EstadoCommon.PAR) && ((ll_numeroPartidaC % 2) != 0))
						    || (ls_tipoPartida.equalsIgnoreCase(EstadoCommon.IMPAR) && ((ll_numeroPartidaC % 2) == 0))
					)
						throw new B2BException(ErrorKeys.NUMERO_PARTIDA_INVALIDO);
				}

				Collection<DetalleAntSistema> lcdas_detalles;

				lcdas_detalles = DaoCreator.getDetalleAntSistemaDAO(ldm_manager).findAllByDatosDetalle(
					    adasu_parametros
					);

				if(CollectionUtils.isValidCollection(lcdas_detalles))
				{
					LibroAntiguoSistemaDao llasd_DAO;
					DatosAntSistemaDAO     ldasd_DAO;
					DatosAntSistema        ldas_datosAntSistema;
					String                 ls_idProceso;

					llasd_DAO                = DaoCreator.getLibroAntiguoSistemaDAO(ldm_manager);
					ldasd_DAO                = DaoCreator.getDatosAntSistemaDAO(ldm_manager);
					ldas_datosAntSistema     = adasu_parametros.getDatosAntSistema();
					ls_idProceso             = StringUtils.getStringNotNull(adasu_parametros.getIdProceso());

					for(DetalleAntSistema ldas_detalle : lcdas_detalles)
					{
						if(ldas_detalle != null)
						{
							Collection<DatosAntSistema> ldas_datosConMatriculas;

							ldas_datosConMatriculas = new LinkedList<DatosAntSistema>();

							{
								LibroAntiguoSistema llas_libro;
								StringBuilder       lsb_nombrePredioDetalle;

								llas_libro     = llasd_DAO.findById(
									    NumericUtils.getLong(ldas_detalle.getIdLibroAntSistema())
									);

								lsb_nombrePredioDetalle = new StringBuilder();

								if(llas_libro != null)
									ldas_detalle.setNombreLibro(llas_libro.getNombre());

								lsb_nombrePredioDetalle.append(
								    IdentificadoresCommon.PREDIO_MIN + " " + IdentificadoresCommon.SIMBOLO_NUMERAL
								    + ldas_datosAntSistema.getConsecutivoPredioAntSistema() + " "
								    + ldas_datosAntSistema.getNombrePredio()
								);

								ldas_detalle.setNombrePredio(lsb_nombrePredioDetalle.toString());
							}

							DatosAntSistema ldas_antSistema;

							ldas_antSistema = ldasd_DAO.findById(ldas_detalle.getIdDatosAntSistema());

							if(ldas_antSistema != null)
							{
								{
									StringBuilder lsb_nombrePredioDetalle;

									lsb_nombrePredioDetalle = new StringBuilder();

									lsb_nombrePredioDetalle.append(
									    IdentificadoresCommon.PREDIO_MIN + " " + IdentificadoresCommon.SIMBOLO_NUMERAL
									    + ldas_antSistema.getConsecutivoPredioAntSistema() + " "
									    + ldas_antSistema.getNombrePredio()
									);

									ldas_detalle.setNombrePredio(lsb_nombrePredioDetalle.toString());
								}

								DetalleAntSistemaUI ldasu_detalleUI;

								ldasu_detalleUI = new DetalleAntSistemaUI(ldas_detalle);

								{
									String ls_idCirculoTmp;
									Long   ll_idMatricula;

									ls_idCirculoTmp     = ldas_antSistema.getIdCirculo();
									ll_idMatricula      = ldas_antSistema.getIdMatricula();

									if(
									    (StringUtils.isValidString(ls_idCirculoTmp)
										    && NumericUtils.isValidLong(ll_idMatricula))
										    && (StringUtils.isValidString(ls_idCirculo)
										    && (ls_idCirculo.equalsIgnoreCase(ls_idCirculoTmp)
										    || ls_idProceso.equalsIgnoreCase(ProcesoCommon.ID_PROCESO_5)))
									)
									{
										PredioRegistro lpr_predioRegistro;
										lpr_predioRegistro = DaoCreator.getPredioRegistroDAO(ldm_manager)
												                           .findByCirculoMatricula(
												    ls_idCirculo, NumericUtils.getLong(ll_idMatricula)
												);

										if(lpr_predioRegistro != null)
										{
											String ls_predioInconsistente;
											ls_predioInconsistente = StringUtils.getStringNotNull(
												    lpr_predioRegistro.getPredioInconsistente()
												);

											if(ls_predioInconsistente.equalsIgnoreCase(EstadoCommon.S))
												ldas_antSistema.setEsPredioInconsistente(true);
										}

										ldas_datosConMatriculas.add(ldas_antSistema);

										ldasu_detalleUI.setMatriculas(ldas_datosConMatriculas);
									}
								}

								lcdasu_retorno.add(ldasu_detalleUI);
							}
						}
					}
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("consultaDetalleAntSistema", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		if(lcdasu_retorno.isEmpty())
			lcdasu_retorno = null;

		return lcdasu_retorno;
	}

	/**
	 * Metodo encargado de consultar el id anotación máximo.
	 *
	 * @param aap_anotacionPredio Argumento de tipo <code>AnotacionPredio</code> que contiene los criterios de búsqueda.
	 * @return Variable de tipo int que contiene el max id anotación.
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see int
	 */
	public synchronized int consultarMaxIdAnotacion(AnotacionPredio aap_anotacionPredio)
	    throws B2BException
	{
		DAOManager ldm_manager;
		int        li_max;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		li_max          = -1;

		try
		{
			if(aap_anotacionPredio != null)
			{
				TurnoHistoria lth_turnoHistoria;

				lth_turnoHistoria = DaoCreator.getTurnoHistoriaDAO(ldm_manager)
						                          .findById(
						    NumericUtils.getLongWrapper(aap_anotacionPredio.getIdTurnoHistoria())
						);

				if(lth_turnoHistoria != null)
				{
					aap_anotacionPredio.setIdTurno(lth_turnoHistoria.getIdTurno());

					li_max = DaoCreator.getAccAnotacionPredioDAO(ldm_manager)
							               .consultarMaxIdAnotacion(aap_anotacionPredio);
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("consultarMaxIdAnotacion", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return li_max;
	}

	/**
	 * Metodo encargado de consultar el orden máximo.
	 *
	 * @param aap_anotacionPredio Argumento de tipo <code>AnotacionPredio</code> que contiene los criterios de búsqueda.
	 * @return Variable de tipo int que contiene el max orden.
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see int
	 */
	public synchronized int consultarMaxOrden(AnotacionPredio aap_anotacionPredio)
	    throws B2BException
	{
		DAOManager ldm_manager;
		int        li_max;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		li_max          = -1;

		try
		{
			if(aap_anotacionPredio != null)
			{
				TurnoHistoria lth_turnoHistoria;

				lth_turnoHistoria = DaoCreator.getTurnoHistoriaDAO(ldm_manager)
						                          .findById(
						    NumericUtils.getLongWrapper(aap_anotacionPredio.getIdTurnoHistoria())
						);

				if(lth_turnoHistoria != null)
				{
					aap_anotacionPredio.setIdTurno(lth_turnoHistoria.getIdTurno());

					li_max = DaoCreator.getAccAnotacionPredioDAO(ldm_manager).consultarMaxOrden(aap_anotacionPredio);
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("consultarMaxOrden", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return li_max;
	}

	/**
	 * Metodo encargado de crear en la tabla SDB_ACC_detalle_ANT_SISTEMA.
	 *
	 * @param adasu_parametros Objeto que contiene los datos necesarios para realizar la inserción en la tabla SDB_ACC_detalle_ANT_SISTEMA.
	 * @param as_userId Usuario de sesión que está realizando la creación.
	 * @param as_remoteIp Ip desde la cual se está realizando la creación.
	 * @return Objeto que contiene el detalle con el que se crea el registro.
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see DetalleAntSistemaUI
	 */
	public synchronized DetalleAntSistemaUI crearDetalleAntSistema(
	    DetalleAntSistemaUI adasu_parametros, String as_userId, String as_remoteIp
	)
	    throws B2BException
	{
		DetalleAntSistemaUI ldasu_detalle;
		DAOManager          ldm_manager;

		ldasu_detalle     = null;
		ldm_manager       = DaoManagerFactory.getDAOManager();

		try
		{
			if(
			    (adasu_parametros != null) && StringUtils.isValidString(as_userId)
				    && StringUtils.isValidString(as_remoteIp)
			)
			{
				DatosAntSistema ldas_datos;

				ldas_datos = adasu_parametros.getDatosAntSistema();

				if(ldas_datos != null)
				{
					DetalleAntSistema             ldas_detalle;
					Collection<DetalleAntSistema> lcdas_detalles;

					lcdas_detalles = DaoCreator.getDetalleAntSistemaDAO(ldm_manager)
							                       .findAllByDatosDetalle(adasu_parametros);

					if(!CollectionUtils.isValidCollection(lcdas_detalles))
					{
						ldas_detalle = crearDetalleAntSistema(
							    adasu_parametros, ldas_datos, ldm_manager, as_userId, as_remoteIp
							);

						if(ldas_detalle != null)
						{
							Collection<DetalleAntSistema> lcdas_detallesN;

							ldasu_detalle = new DetalleAntSistemaUI(ldas_detalle);

							ldasu_detalle.setIdCirculo(ldas_datos.getIdCirculo());

							lcdas_detallesN = DaoCreator.getDetalleAntSistemaDAO(ldm_manager)
									                        .findAllByDatosDetalle(ldasu_detalle);

							if(CollectionUtils.isValidCollection(lcdas_detallesN))
							{
								Collection<DatosAntSistema> lcdas_datos;

								lcdas_datos = new ArrayList<DatosAntSistema>();

								for(DetalleAntSistema ldas_iterador : lcdas_detallesN)
								{
									if(ldas_iterador != null)
									{
										DatosAntSistema ldas_antSistema;

										ldas_antSistema = new DatosAntSistema();
										ldas_antSistema.setIdDatosAntSistema(ldas_iterador.getIdDatosAntSistema());

										ldas_antSistema = DaoCreator.getDatosAntSistemaDAO(ldm_manager)
												                        .findById(ldas_antSistema);

										lcdas_datos.add(ldas_antSistema);
									}
								}

								Collection<DatosAntSistema> ldas_datosConMatriculas;
								ldas_datosConMatriculas = new LinkedList<DatosAntSistema>();

								if(CollectionUtils.isValidCollection(lcdas_datos))
								{
									for(DatosAntSistema ldas_iterador : lcdas_datos)
									{
										if(ldas_iterador != null)
										{
											String ls_idCirculo;
											Long   ll_idMatricula;

											ls_idCirculo       = ldas_iterador.getIdCirculo();
											ll_idMatricula     = ldas_iterador.getIdMatricula();

											if(
											    StringUtils.isValidString(ls_idCirculo)
												    && NumericUtils.isValidLong(ll_idMatricula)
											)
												ldas_datosConMatriculas.add(ldas_iterador);
										}
									}
								}

								if(ldas_datosConMatriculas.isEmpty())
									ldasu_detalle.setMatriculas(null);
								else
									ldasu_detalle.setMatriculas(ldas_datosConMatriculas);
							}
						}
					}
					else
						throw new B2BException(ErrorKeys.YA_EXISTE_DETALLE);
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("crearDetalleAntSistema", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return ldasu_detalle;
	}

	/**
	 * Método encargado de eliminar  un registro determinado de la tabla SDB_ACC_PREDIO_SEGREGADO .
	 *
	 * @param aps_param  Objeto que contiene los datos necesarios para ejecutar la sentencia delete sobre la tabla SDB_ACC_PREDIO_SEGREGADO.
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public synchronized void deleteByCirculoMatriculaTurno(PredioSegregado aps_param)
	    throws B2BException
	{
		DAOManager ldm_manager;

		ldm_manager = DaoManagerFactory.getDAOManager();

		try
		{
			DaoCreator.getAccPredioSegregadoDAO(ldm_manager).deleteByCirculoMatriculaTurno(aps_param);
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("deleteByCirculoMatriculaTurno", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}
	}

	/**
	 * Método encargado de eliminar  un registro determinado de la tabla SDB_ACC_DIRECCION_PREDIO .
	 *
	 * @param aps_param Objeto que contiene los datos necesarios para ejecutar la sentencia delete sobre la tabla SDB_ACC_DIRECCION_PREDIO.
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public synchronized void deleteById(DireccionPredioAcc aps_param)
	    throws B2BException
	{
		DAOManager ldm_manager;

		ldm_manager = DaoManagerFactory.getDAOManager();

		try
		{
			DaoCreator.getDireccionPredioAccDAO(ldm_manager).deleteById(aps_param);
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("deleteById", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}
	}

	/**
	 * Elimina todos los intervinientes de una anotación, ademas del registro de la anotación. Posterior a esto reorganiza
	 * el orden de las demas anotaciones asociadas al turno en trámite
	 *
	 * @param aap_anotacionPredio Objeto contenedor de la información a eliminar de la base de datos
	 * @param as_userId Id del usuario que ejecuta la acción
	 * @param as_remoteIp Dirección del cliente donde se ejecuta la acción
	 * @return devuelve el valor de Collection
	 * @throws B2BException Señala que se ha producido una excepción
	 *
	 */
	public synchronized Collection<AnotacionPredio> eliminarAnotacioPredio(
	    AnotacionPredio aap_anotacionPredio, String as_userId, String as_remoteIp
	)
	    throws B2BException
	{
		DAOManager                  ldm_manager;
		Collection<AnotacionPredio> lcap_anotaciones;

		ldm_manager          = DaoManagerFactory.getDAOManager();
		lcap_anotaciones     = null;

		try
		{
			if(aap_anotacionPredio == null)
				throw new B2BException(ErrorKeys.ERROR_INTERNO_SISTEMA);

			String             ls_idAnotacionPredio;
			AnotacionPredioDAO lapd_anotacionPredioDAO;

			ls_idAnotacionPredio        = aap_anotacionPredio.getIdAnotacionPredio();
			lapd_anotacionPredioDAO     = DaoCreator.getAccAnotacionPredioDAO(ldm_manager);

			DaoCreator.getAccAnotacionPredioCiudadanoDAO(ldm_manager).deleteIntervinientes(ls_idAnotacionPredio);

			lapd_anotacionPredioDAO.deleteById(ls_idAnotacionPredio);

			lcap_anotaciones = lapd_anotacionPredioDAO.findByTurnoHistoriaCirculoMatricula(aap_anotacionPredio, true);

			if(CollectionUtils.isValidCollection(lcap_anotaciones))
			{
				long ll_cont;

				ll_cont = 1;

				for(AnotacionPredio lap_anotacionTemp : lcap_anotaciones)
				{
					if(lap_anotacionTemp != null)
					{
						lap_anotacionTemp.setIdAnotacion(NumericUtils.getLongWrapper(ll_cont));
						lap_anotacionTemp.setOrden(NumericUtils.getBigDecimal(ll_cont));
						lap_anotacionTemp.setIdUsuarioModificacion(as_userId);
						lap_anotacionTemp.setIpModificacion(as_remoteIp);

						lapd_anotacionPredioDAO.updateOrdenIdAnotacion(lap_anotacionTemp);

						ll_cont++;
					}
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("eliminarAnotacioPredio", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lcap_anotaciones;
	}

	/**
	 * Metodo encargado de eliminar un registro de SDB_ACC_DETALLE_ANT_SISTEMA por sus respectivas llaves.
	 *
	 * @param adasu_parametros objeto contenedor de parametros necesarios para efectuar la sentencia.
	 * @param as_userId Variable de tipo String que contiene el usuario que realiza la acción.
	 * @param ls_localIpAddress Variable de tipo String que contiene la dirección ip local desde donde seque realiza la acción.
	 * @param ls_remoteIpAddress Variable de tipo String que contiene la dirección ip remota desde donde seque realiza la acción.
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public synchronized void eliminarDetalleAntSistema(
	    DetalleAntSistemaUI adasu_parametros, String as_userId, String ls_localIpAddress, String ls_remoteIpAddress
	)
	    throws B2BException
	{
		DAOManager ldm_manager;

		ldm_manager = DaoManagerFactory.getDAOManager();

		try
		{
			if(adasu_parametros != null)
				DaoCreator.getDetalleAntSistemaDAO(ldm_manager).deleteByIdDetalleAntSistemaUI(adasu_parametros);
			else
				throw new B2BException(ErrorKeys.DETALLE_NO_APTO_PARA_ELIMINAR);
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("eliminarDetalleAntSistema", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}
	}

	/**
	 * Inactiva los datos de la matrícula asociada a un predio y actualiza el campo acción del predio.
	 *
	 * @param adas_datos Objeto contenedor de la información de la matrícula a inactivar ademas del id
	 * del predio
	 * @param as_userId Id del usuario que ejecuta la acción
	 * @param as_remoteIp Dirección IP del cliente que ejectua la acción
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public synchronized void eliminarMatriculasAsociadas(
	    DatosAntSistema adas_datos, String as_userId, String as_remoteIp
	)
	    throws B2BException
	{
		DAOManager ldm_manager;
		boolean    lb_encontroDatos;

		ldm_manager          = DaoManagerFactory.getDAOManager();
		lb_encontroDatos     = false;

		try
		{
			if(adas_datos == null)
				throw new B2BException(ErrorKeys.ERROR_SIN_DATOS_ANT_SISTEMA);

			long     ll_idMatricula;
			String   ls_matricula;
			String   ls_idSolicitud;
			String   ls_idCirculo;
			String   ls_matriculaAsociada;
			String[] lsa_datos;

			ls_matriculaAsociada     = adas_datos.getMatriculasAsociadas();
			ls_matricula             = null;

			if(StringUtils.isValidString(ls_matriculaAsociada))
			{
				lsa_datos        = ls_matriculaAsociada.split("-");
				ls_matricula     = lsa_datos[1];
			}

			ll_idMatricula = NumericUtils.getLong(adas_datos.getIdMatricula());

			if((ll_idMatricula <= 0) && StringUtils.isValidString(ls_matricula))
				ll_idMatricula = NumericUtils.getLong(ls_matricula);

			ls_idSolicitud     = adas_datos.getIdSolicitud();
			ls_idCirculo       = adas_datos.getIdCirculo();

			{
				SolicitudMatriculaActoDAO          lsmad_solMatActoDAO;
				Collection<SolicitudMatriculaActo> lcsma_matriculas;

				lsmad_solMatActoDAO     = DaoCreator.getSolicitudMatriculaActoDAO(ldm_manager);

				lcsma_matriculas = lsmad_solMatActoDAO.findAllByIdSolicitud(
					    ls_idSolicitud, ls_idCirculo, ll_idMatricula, false, true
					);

				if(CollectionUtils.isValidCollection(lcsma_matriculas))
				{
					for(SolicitudMatriculaActo lsma_iterador : lcsma_matriculas)
					{
						if((lsma_iterador != null) && (ll_idMatricula <= 0))
							ll_idMatricula = lsma_iterador.getIdMatricula();
					}

					SolicitudMatriculaActo lsma_data;
					lsma_data = new SolicitudMatriculaActo();
					lsma_data.setIdCirculo(ls_idCirculo);
					lsma_data.setIdMatricula(ll_idMatricula);
					lsma_data.setIdSolicitud(ls_idSolicitud);

					lsmad_solMatActoDAO.deleteBySolicitudCirculoMatricula(lsma_data);

					lb_encontroDatos = true;
				}
			}

			{
				SolicitudMatricula    lsm_solicitudMtr;
				SolicitudMatriculaDAO lsmd_solMatDAO;

				lsmd_solMatDAO     = DaoCreator.getSolicitudMatriculaDAO(ldm_manager);

				lsm_solicitudMtr = lsmd_solMatDAO.findById(ls_idSolicitud, ls_idCirculo, ll_idMatricula, true);

				if(lsm_solicitudMtr != null)
				{
					lsmd_solMatDAO.deleteBySolicitudCirculoMatricula(lsm_solicitudMtr);

					lb_encontroDatos = true;
				}
			}

			{
				DatosAntSistemaDAO ldasd_datosAntSisDAO;
				DatosAntSistema    ldas_datosAntSis;

				ldasd_datosAntSisDAO     = DaoCreator.getDatosAntSistemaDAO(ldm_manager);

				ldas_datosAntSis = ldasd_datosAntSisDAO.findById(adas_datos);

				if((ldas_datosAntSis != null) && lb_encontroDatos)
				{
					String ls_accion;

					ls_accion = StringUtils.getStringNotNull(ldas_datosAntSis.getAccion());

					if(ls_accion.equalsIgnoreCase(AccionAntSistemaCommon.ASOCIAR_MATRICULA_E_INFORME))
						ldas_datosAntSis.setAccion(AccionAntSistemaCommon.INFORME_RESULTADOS);
					else
						ldas_datosAntSis.setAccion(null);

					ldas_datosAntSis.setIdMatricula(null);
					ldas_datosAntSis.setIdUsuarioModificacion(as_userId);
					ldas_datosAntSis.setIpModificacion(as_remoteIp);

					ldasd_datosAntSisDAO.insertUpdate(ldas_datosAntSis, false);
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("eliminarMatriculasAsociadas", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}
	}

	/**
	 * Invoca al procedimiento almacenado proc elimina matricula an sistema.
	 *
	 * @param as_idTurno id del turno desde el cual se invoca el procedimiento
	 * @param as_idDatosAntSistema id del predio de antiguo sistema, si existe
	 * @param as_userId id del usuario que ejecuta la acción
	 * @param as_remoteIp dirección IP del cliente que ejecuta la acción
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public synchronized void eliminarMatriculasCreadas(
	    String as_idTurno, String as_idDatosAntSistema, String as_userId, String as_remoteIp
	)
	    throws B2BException
	{
		DAOManager ldm_manager;

		ldm_manager = DaoManagerFactory.getDAOManager();

		try
		{
			if(!StringUtils.isValidString(as_idTurno))
				throw new B2BException(ErrorKeys.TURNO_INVALIDO);

			if(!StringUtils.isValidString(as_idDatosAntSistema))
				throw new B2BException(ErrorKeys.ERROR_SIN_DATOS_ANT_SISTEMA);

			DaoCreator.getProcedimientosDAO(ldm_manager)
				          .procEliminaMatriculaAntSistema(as_idTurno, as_idDatosAntSistema, as_userId, as_remoteIp);

			{
				DatosAntSistemaDAO ldasd_datosAntSistemaDAO;
				DatosAntSistema    ldas_datosAnt;

				ldasd_datosAntSistemaDAO     = DaoCreator.getDatosAntSistemaDAO(ldm_manager);
				ldas_datosAnt                = ldasd_datosAntSistemaDAO.findById(as_idDatosAntSistema);

				if(ldas_datosAnt != null)
				{
					String ls_accion;

					ls_accion = StringUtils.getStringNotNull(ldas_datosAnt.getAccion());

					if(ls_accion.equals(AccionAntSistemaCommon.CREAR_MATRICULA_E_INFORME))
						ldas_datosAnt.setAccion(AccionAntSistemaCommon.INFORME_RESULTADOS);
					else
						ldas_datosAnt.setAccion(null);

					ldas_datosAnt.setIdUsuarioModificacion(as_userId);
					ldas_datosAnt.setIpModificacion(as_remoteIp);

					ldasd_datosAntSistemaDAO.updateAccionAntSistema(ldas_datosAnt);
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("eliminarMatriculasCreadas", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}
	}

	/**
	 * Método encargado de enviar al responsable de aprobacion firma libro ant sistema.
	 *
	 * @param ath_turnoHistoria Objeto que contiene la información para enviar al responsable de firma libro ant sistema.
	 * @param as_userId Variable de tipo String que contiene el id del usuario que está realizando el trámite.
	 * @param as_remoteIp Variable de tipo String que contiene la ip del usuario que está realizando el trámite.
	 * @param al_motivo Variable de tipo long que contiene la decision seleccionada.
	 * @throws B2BException Se genera cuando se presenta una excepción.
	 */
	public synchronized void enviarResponsableAntSistemaFirma(
	    TurnoHistoria ath_turnoHistoria, String as_userId, String as_remoteIp, long al_motivo
	)
	    throws B2BException
	{
		DAOManager ldm_manager;

		ldm_manager = DaoManagerFactory.getDAOManager();

		try
		{
			if((ath_turnoHistoria != null) && (al_motivo > 0))
			{
				{
					OficiosTexto lot_oficiosTexto;

					lot_oficiosTexto = new OficiosTexto();

					lot_oficiosTexto.setIdTurnoHistoria(ath_turnoHistoria.getIdTurnoHistoria());
					lot_oficiosTexto.setConsideracion(ath_turnoHistoria.getConsideracionesAntiguoSistema());
					lot_oficiosTexto.setIdUsuarioCreacion(as_userId);
					lot_oficiosTexto.setIpCreacion(as_remoteIp);

					DaoCreator.getOficiosTextoDAO(ldm_manager).insertOrUpdate(lot_oficiosTexto, true);
				}

				{
					MotivoTramite lmt_motivoTramite;

					lmt_motivoTramite = new MotivoTramite();

					lmt_motivoTramite.setIdMotivo(al_motivo);
					lmt_motivoTramite.setIdEtapaOrigen(EtapaCommon.ID_ETAPA_AUTORIZACION_FIRMA_LIBROS_A_S);

					terminarTurnoHistoriaYCrearEtapa(
					    ath_turnoHistoria, ldm_manager, lmt_motivoTramite, as_userId, as_remoteIp,
					    EstadoCommon.TERMINADA
					);
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("enviarResponsableAntSistemaFirma", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}
	}

	/**
	 * Método encargado de consultar un registro determinado de la tabla SDB_ACC_DIRECCION_PREDIO .
	 *
	 * @param adp_param correspondiente al valor del tipo de objeto DireccionPredioAcc
	 * @return Retorna  un objeto de tipo DireccionPredioAcc que coincide con los parametros de entrada.
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see DireccionPredioAcc
	 */
	public synchronized DireccionPredioAcc findAccDireccionById(DireccionPredioAcc adp_param)
	    throws B2BException
	{
		DAOManager         ldm_manager;
		DireccionPredioAcc ldp_data;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		ldp_data        = null;

		try
		{
			ldp_data = DaoCreator.getDireccionPredioAccDAO(ldm_manager).findById(adp_param);
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findAccDireccionById", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return ldp_data;
	}

	/**
	 * Método encargado de consultar un registro determinado de la tabla SDB_ACC_PREDIO_REGISTRO.
	 *
	 * @param aapr_predioRegistro  Objeto que contiene los datos necesarios para ejecutar la sentencia select sobre la tabla SDB_ACC_PREDIO_REGISTRO.
	 * @return Retorna  un objeto de tipo AccPredioRegistro que coincide con los parametros de entrada.
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see AccPredioRegistro
	 */
	public synchronized AccPredioRegistro findAccPredioRegistroByTurnoHistoria(AccPredioRegistro aapr_predioRegistro)
	    throws B2BException
	{
		DAOManager        ldm_manager;
		AccPredioRegistro lc_data;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		lc_data         = null;

		try
		{
			if(aapr_predioRegistro != null)
				lc_data = DaoCreator.getAccPredioRegistroDAO(ldm_manager).findByTurnoHistoria(aapr_predioRegistro);
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findAccPredioRegistroByTurnoHistoria", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lc_data;
	}

	/**
	 * Método encargado de retornar un registro con los nombres del municipio, departamento, círculo, tipo predio  y país.
	 *
	 * @param as_userId Variable de tipo String que contiene el usuario que realiza la acción.
	 * @param ldas_datosAntSistema  Objeto que contiene los datos necesarios para realizar consultas con los ids de los campos municipio, departamento, círculo, tipo predio  y país.
	 * @return Retorna  un objeto de tipo AntiguoSistemaData que coincide con los parametros de entrada.
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see AntiguoSistemaData
	 */
	public synchronized AntiguoSistemaData findAntiguoSistemaData(
	    String as_userId, DatosAntSistema ldas_datosAntSistema
	)
	    throws B2BException
	{
		DAOManager         ldm_manager;
		AntiguoSistemaData lasd_data;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		lasd_data       = null;

		try
		{
			if(ldas_datosAntSistema != null)
			{
				lasd_data = new AntiguoSistemaData();

				String ls_idTipoPredio;
				String ls_idCirculo;
				String ls_idTurno;
				String ls_idPais;
				String ls_idDepartamento;
				String ls_idMunicipio;
				String ls_partida;
				String ls_nombrePredio;
				String ls_adquisicionPredio;
				Long   ll_libro;
				Long   ll_tomo;
				Long   ll_folio;
				Long   ll_anio;

				ls_idTipoPredio          = ldas_datosAntSistema.getIdTipoPredio();
				ls_idCirculo             = ldas_datosAntSistema.getIdCirculo();
				ls_idTurno               = ldas_datosAntSistema.getIdTurno();
				ls_idPais                = ldas_datosAntSistema.getIdPais();
				ls_idDepartamento        = ldas_datosAntSistema.getIdDepartamento();
				ls_idMunicipio           = ldas_datosAntSistema.getIdMunicipio();
				ls_partida               = ldas_datosAntSistema.getPartida();
				ll_libro                 = ldas_datosAntSistema.getIdLibroAntSistema();
				ll_tomo                  = ldas_datosAntSistema.getTomo();
				ll_folio                 = ldas_datosAntSistema.getFolio();
				ll_anio                  = ldas_datosAntSistema.getAnio();
				ls_nombrePredio          = ldas_datosAntSistema.getNombrePredio();
				ls_adquisicionPredio     = ldas_datosAntSistema.getAdquisicionPredio();

				if(StringUtils.isValidString(ls_idCirculo))
				{
					CirculoRegistral lcr_tmp;

					lcr_tmp = new CirculoRegistral();
					lcr_tmp.setIdCirculo(ldas_datosAntSistema.getIdCirculo());

					lcr_tmp = DaoCreator.getCirculoRegistralDAO(ldm_manager).findById(lcr_tmp);

					if(lcr_tmp != null)
					{
						String ls_nombre;
						ls_nombre = lcr_tmp.getNombre();

						if(StringUtils.isValidString(ls_nombre))
							lasd_data.setCirculoRegistral(ls_nombre);
					}
				}

				if(StringUtils.isValidString(ls_idTipoPredio))
				{
					PredioTipo lpt_tmp;

					lpt_tmp = new PredioTipo();
					lpt_tmp.setIdTipoPredio(ldas_datosAntSistema.getIdTipoPredio());

					lpt_tmp = DaoCreator.getPredioTipoDao(ldm_manager).findById(lpt_tmp);

					if(lpt_tmp != null)
					{
						String ls_descripcion;
						ls_descripcion = lpt_tmp.getDescripcion();

						if(StringUtils.isValidString(ls_descripcion))
							lasd_data.setTipoPredio(ls_descripcion);
					}
				}

				if(StringUtils.isValidString(ls_nombrePredio))
					lasd_data.setNombrePredio(ls_nombrePredio);

				if(StringUtils.isValidString(ls_idPais))
				{
					Pais lp_pais;

					lp_pais = new Pais();

					lp_pais.setIdPais(ls_idPais);

					lp_pais = DaoCreator.getPaisDAO(ldm_manager).findById(lp_pais);

					if(lp_pais != null)
					{
						String ls_nombrePais;
						ls_nombrePais = lp_pais.getNombre();

						if(StringUtils.isValidString(ls_nombrePais))
							lasd_data.setPais(ls_nombrePais);
					}

					if(StringUtils.isValidString(ls_idDepartamento))
					{
						Departamento ld_departamento;

						ld_departamento = new Departamento();

						ld_departamento.setIdPais(ls_idPais);
						ld_departamento.setIdDepartamento(ls_idDepartamento);

						ld_departamento = DaoCreator.getDepartamentoDAO(ldm_manager).findById(ld_departamento);

						if(ld_departamento != null)
						{
							String ls_nombreDepartamento;
							ls_nombreDepartamento = ld_departamento.getNombre();

							if(StringUtils.isValidString(ls_nombreDepartamento))
								lasd_data.setDepartamento(ls_nombreDepartamento);
						}

						if(StringUtils.isValidString(ls_idMunicipio))
						{
							Municipio lm_municipio;

							lm_municipio = new Municipio();

							lm_municipio.setIdPais(ls_idPais);
							lm_municipio.setIdDepartamento(ls_idDepartamento);
							lm_municipio.setIdMunicipio(ls_idMunicipio);

							lm_municipio = DaoCreator.getMunicipioDAO(ldm_manager).findById(lm_municipio);

							if(lm_municipio != null)
							{
								String ls_nombreMun;
								ls_nombreMun = lm_municipio.getNombre();

								if(StringUtils.isValidString(ls_nombreMun))
									lasd_data.setMunicipio(ls_nombreMun);
							}
						}
					}
				}

				if(NumericUtils.isValidLong(ll_libro))
				{
					LibroAntiguoSistema llas_libro;

					llas_libro = new LibroAntiguoSistema();

					lasd_data.setLibro(ll_libro);
					llas_libro.setIdLibroAntiguoSistema(NumericUtils.getLong(ll_libro));

					llas_libro = DaoCreator.getLibroAntiguoSistemaDAO(ldm_manager).findById(llas_libro);

					if(llas_libro != null)
					{
						String ls_nombre;

						ls_nombre = StringUtils.isValidString(llas_libro.getNombre()) ? llas_libro.getNombre()
							                                                          : StringUtils.getString(ll_libro);

						lasd_data.setNombreLibro(ls_nombre);
					}
				}

				if(NumericUtils.isValidLong(ll_tomo))
					lasd_data.setTomo(ll_tomo);

				if(NumericUtils.isValidLong(ll_folio))
					lasd_data.setFolio(ll_folio);

				if(NumericUtils.isValidLong(ll_anio))
					lasd_data.setAnio(ll_anio);

				if(StringUtils.isValidString(ls_partida))
					lasd_data.setPartida(ls_partida);

				if(StringUtils.isValidString(ls_adquisicionPredio))
					lasd_data.setAdquisicionPredio(ls_adquisicionPredio);

				if(StringUtils.isValidString(ls_idTurno))
				{
					TurnoHistoria lth_turnoHistoria;

					lth_turnoHistoria = DaoCreator.getTurnoHistoriaDAO(ldm_manager).findByIdTurno(ls_idTurno);

					if(lth_turnoHistoria != null)
						lasd_data.setObservacionesFirmaLibro(lth_turnoHistoria.getObservacionesNoTramite());
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findAntiguoSistema", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lasd_data;
	}

	/**
	 * Método encargado de consultar un registro determinado de la tabla SDB_ACC_PREDIO_SEGREGADO.
	 *
	 * @param aps_param   Objeto que contiene los datos necesarios para ejecutar la sentencia select sobre la tabla SDB_ACC_PREDIO_SEGREGADO.
	 * @return Retorna  un objeto de tipo PredioSegregado que coincide con los parametros de entrada.
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see PredioSegregado
	 */
	public synchronized PredioSegregado findByCirculoMatriculaTurno(PredioSegregado aps_param)
	    throws B2BException
	{
		DAOManager      ldm_manager;
		PredioSegregado lps_data;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		lps_data        = null;

		try
		{
			lps_data = DaoCreator.getAccPredioSegregadoDAO(ldm_manager).findByCirculoMatriculaTurno(aps_param);
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findByCirculoMatriculaTurno", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lps_data;
	}

	/**
	 * Metodo encargado de consultar los registros de anotacion predio que coincidan con los criterios de búsqueda.
	 *
	 * @param aap_anotacionPredio correspondiente al valor del tipo de objeto AnotacionPredio
	 * @return Objeto de tipo <code>AnotacionPredio</code> que contiene los resultados que coincidieron con los criterios de búsqueda.
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see AnotacionPredio
	 */
	public synchronized AnotacionPredio findByCirculoMatriculaTurnoAnotacion(AnotacionPredio aap_anotacionPredio)
	    throws B2BException
	{
		DAOManager      ldm_manager;
		AnotacionPredio lap_anotacionPredio;

		ldm_manager             = DaoManagerFactory.getDAOManager();
		lap_anotacionPredio     = null;

		try
		{
			if(aap_anotacionPredio != null)
			{
				TurnoHistoria lth_turnoHistoria;

				lth_turnoHistoria = DaoCreator.getTurnoHistoriaDAO(ldm_manager)
						                          .findById(
						    NumericUtils.getLongWrapper(aap_anotacionPredio.getIdTurnoHistoria())
						);

				if(lth_turnoHistoria != null)
				{
					aap_anotacionPredio.setIdTurno(lth_turnoHistoria.getIdTurno());

					lap_anotacionPredio = DaoCreator.getAccAnotacionPredioDAO(ldm_manager)
							                            .findByCirculoMatriculaTurnoAnotacion(aap_anotacionPredio);
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findByCirculoMatriculaTurnoAnotacion", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lap_anotacionPredio;
	}

	/**
	 * Método encargado de consultar una colección de  registros  con unos parametros de entrada determinados en la tabla SDB_ACC_DATOS_ANT_SISTEMA.
	 *
	 * @param adas_datosAntSis Objeto que contiene los datos necesarios para ejecutar la sentencia select sobre la tabla SDB_ACC_DATOS_ANT_SISTEMA.
	 * @return Retorna  una colección de datos de tipo  DataConsultaAntSistema que coincide con los parametros de entrada.
	 * @throws B2BException Señala que se ha producido una excepción
	 *
	 */
	public synchronized Collection<DataConsultaAntSistema> findByDatosAntSistema(DatosAntSistema adas_datosAntSis)
	    throws B2BException
	{
		DAOManager                         ldm_manager;
		Collection<DataConsultaAntSistema> ll_result;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		ll_result       = new ArrayList<DataConsultaAntSistema>();

		try
		{
			DatosAntSistema ldas_das;

			ldas_das = adas_datosAntSis;

			if(ldas_das != null)
			{
				boolean lb_validarLibro;
				String  ls_adquisicion;
				String  ls_idCirculo;
				Long    ll_libro;

				lb_validarLibro     = true;
				ls_adquisicion      = ldas_das.getAdquisicionPredio();
				ls_idCirculo        = ldas_das.getIdCirculo();
				ll_libro            = ldas_das.getIdLibroAntSistema();

				if(StringUtils.isValidString(ls_adquisicion))
				{
					if(ls_adquisicion.equalsIgnoreCase("Datos del predio"))
						lb_validarLibro = false;
				}

				if(!StringUtils.isValidString(ls_idCirculo))
					throw new B2BException(ErrorKeys.DEBE_SELECCIONAR_CIRCULO_REGISTRAL);

				if(!NumericUtils.isValidLong(ll_libro) && lb_validarLibro)
					throw new B2BException(ErrorKeys.DEBE_SELECCIONAR_LIBRO);

				Collection<DatosAntSistema> lcdas_antSistema;
				lcdas_antSistema     = new ArrayList<DatosAntSistema>();

				lcdas_antSistema = DaoCreator.getDatosAntSistemaDAO(ldm_manager).findByDataAntSistema(adas_datosAntSis);

				Collection<DataConsultaPorCriterio> lcdcpc_dataCriteriosFinal;
				String                              ls_idCirculoTemp;

				lcdcpc_dataCriteriosFinal     = new ArrayList<DataConsultaPorCriterio>();
				ls_idCirculoTemp              = adas_datosAntSis.getIdCirculo();

				if(CollectionUtils.isValidCollection(lcdas_antSistema))
				{
					for(DatosAntSistema ldas_iterador : lcdas_antSistema)
					{
						Collection<DataConsultaPorCriterio> lcdcpc_dataCriterios;
						DataConsultaAntSistema              ldcas_datos;
						String                              ls_idDatosAntSistema;
						Solicitud                           ls_solicitud;

						ldcas_datos              = new DataConsultaAntSistema();
						ls_idDatosAntSistema     = ldas_iterador.getIdDatosAntSistema();
						lcdcpc_dataCriterios     = DaoCreator.getConsultaPorCriteriosDAO(ldm_manager)
								                                 .findByDatosAntSistema(
								    ls_idCirculoTemp, ls_idDatosAntSistema
								);

						if(CollectionUtils.isValidCollection(lcdcpc_dataCriterios))
						{
							LinkedHashMap<String, DataConsultaPorCriterio> llhm_data;

							llhm_data = new LinkedHashMap<String, DataConsultaPorCriterio>();

							for(DataConsultaPorCriterio ldcpc_iterador : lcdcpc_dataCriterios)
							{
								Long   ll_idMatriculaIterador;
								String ls_idCirculoIterador;

								ll_idMatriculaIterador     = ldcpc_iterador.getIdMatricula();
								ls_idCirculoIterador       = ldcpc_iterador.getIdCirculo();

								if(
								    StringUtils.isValidString(ls_idCirculoIterador)
									    && NumericUtils.isValidLong(ll_idMatriculaIterador)
								)
								{
									String ls_matriculaCompleta;

									ls_matriculaCompleta = ls_idCirculoIterador + "-" + ll_idMatriculaIterador;

									if(llhm_data.containsKey(ls_matriculaCompleta))
									{
										DataConsultaPorCriterio ldcpc_temp;

										ldcpc_temp = llhm_data.get(ls_matriculaCompleta);

										if(ldcpc_temp != null)
										{
											String ls_anotaciones;
											String ls_anotacion;

											ls_anotaciones     = ldcpc_temp.getIdAnotacion();
											ls_anotacion       = ldcpc_iterador.getIdAnotacion();

											ls_anotaciones += ("," + ls_anotacion);

											ldcpc_temp.setIdAnotacion(ls_anotaciones);
										}
									}
									else
										llhm_data.put(ls_matriculaCompleta, ldcpc_iterador);
								}
							}

							for(Map.Entry<String, DataConsultaPorCriterio> entry : llhm_data.entrySet())
								lcdcpc_dataCriteriosFinal.add(entry.getValue());
						}

						ldcas_datos.setDatosAntSistema(ldas_iterador);
						ldcas_datos.setDataConsultaPorCriterio(lcdcpc_dataCriteriosFinal);

						ls_solicitud = DaoCreator.getSolicitudDAO(ldm_manager)
								                     .findByIdDatosAntSistema(ls_idDatosAntSistema);

						if(ls_solicitud != null)
							ldcas_datos.setSolicitud(DaoCreator.getSolicitudDAO(ldm_manager).findById(ls_solicitud));

						ll_result.add(ldcas_datos);
					}
				}

//				if(!CollectionUtils.isValidCollection(ll_result))
//					throw new B2BException(ErrorKeys.NO_SE_ENCONTRARON_REGISTROS_CONSULTA);
			}
		}

		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findByDatosAntSistema", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return ll_result;
	}

	/**
	 * Método encargado de consultar una colección de  registros de documentos  para un circulo registral ,documento  y id solicitud determinados.
	 *
	 * @param acdd_data  Objeto que contiene los datos necesarios para ejecutar la sentencia select sobre la tabla SDB_BGN_DOCUMENTO.
	 * @return Retorna  una colección de datos de tipo  DataConsultaDatosDocumento que coincide con los parametros de entrada.
	 * @throws B2BException Señala que se ha producido una excepción
	 *
	 */
	public synchronized Collection<DataConsultaDatosDocumento> findByDatosDocumento(ConsultaDatosDocumento acdd_data)
	    throws B2BException
	{
		DAOManager                             ldm_manager;
		Collection<DataConsultaDatosDocumento> ll_result;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		ll_result       = new ArrayList<DataConsultaDatosDocumento>();

		try
		{
			CirculoRegistral lcr_circuloRegistral;
			Date             ld_fechaDocumento;
			OficinaOrigen    loo_oficinaOrigen;
			String           ls_idTipoDocumento;
			String           ls_numero;

			ls_idTipoDocumento     = null;
			ls_numero              = null;
			ld_fechaDocumento      = null;

			if(acdd_data != null)
			{
				Documento ld_documento;

				lcr_circuloRegistral     = acdd_data.getCirculoRegistral();
				ld_documento             = acdd_data.getDocumento();
				loo_oficinaOrigen        = acdd_data.getOficinaOrigen();

				if(lcr_circuloRegistral != null)
				{
					String ls_idCirculo;
					ls_idCirculo = lcr_circuloRegistral.getIdCirculo();

					if(!StringUtils.isValidString(ls_idCirculo))
						throw new B2BException(ErrorKeys.DEBE_SELECCIONAR_CIRCULO_REGISTRAL);
				}

				if(ld_documento != null)
				{
					ls_idTipoDocumento     = ld_documento.getIdTipoDocumento();
					ls_numero              = ld_documento.getNumero();
					ld_fechaDocumento      = ld_documento.getFechaDocumento();

					if(!StringUtils.isValidString(ls_idTipoDocumento))
						throw new B2BException(ErrorKeys.DEBE_SELECCIONAR_TIPO_DOC);

					if(!StringUtils.isValidString(ls_numero))
						throw new B2BException(ErrorKeys.DOCUMENTO_INVALIDO);

					if(ld_fechaDocumento == null)
						throw new B2BException(ErrorKeys.DEBE_DIGITAR_FECHA_DOC);
				}
				else
					throw new B2BException(ErrorKeys.ERROR_INFORMACION_DOC);
			}
			else
				throw new B2BException(ErrorKeys.ERROR_DATOS_CONSULTA);

			Documento    ld_documento;
			DocumentoDAO ldd_documentoDAO;

			ld_documento         = new Documento();
			ldd_documentoDAO     = DaoCreator.getDocumentoDAO(ldm_manager);

			ld_documento.setFechaDocumento(ld_fechaDocumento);
			ld_documento.setNumero(ls_numero);
			ld_documento.setIdTipoDocumento(ls_idTipoDocumento);

			loo_oficinaOrigen = acdd_data.getOficinaOrigen();

			if(loo_oficinaOrigen != null)
			{
				ld_documento.setIdTipoOficina(loo_oficinaOrigen.getIdTipoOficina());
				ld_documento.setIdOficinaOrigen(loo_oficinaOrigen.getIdOficinaOrigen());
			}

			ld_documento     = ldd_documentoDAO.consultaDocumento(ld_documento);

			lcr_circuloRegistral = acdd_data.getCirculoRegistral();

			if(lcr_circuloRegistral != null)
			{
				String ls_idCirculoTemp;
				ls_idCirculoTemp = lcr_circuloRegistral.getIdCirculo();

				if(ld_documento != null)
				{
					Collection<DataConsultaPorCriterio> lcdcpc_dataCriterios;
					Collection<DataConsultaPorCriterio> lcdcpc_dataCriteriosFinal;
					ConsultaPorCriteriosDAO             lcpc_DAO;
					DataConsultaDatosDocumento          ldccdc_datos;
					SolicitudDAO                        ls_DAO;
					String                              ls_idDocumento;

					lcpc_DAO                      = DaoCreator.getConsultaPorCriteriosDAO(ldm_manager);
					ldccdc_datos                  = new DataConsultaDatosDocumento();
					ls_DAO                        = DaoCreator.getSolicitudDAO(ldm_manager);
					ls_idDocumento                = ld_documento.getIdDocumento();
					lcdcpc_dataCriterios          = lcpc_DAO.findByDatosDocumento(ls_idCirculoTemp, ls_idDocumento);
					lcdcpc_dataCriteriosFinal     = new ArrayList<DataConsultaPorCriterio>();

					ldccdc_datos.setDocumento(ld_documento);

					if(CollectionUtils.isValidCollection(lcdcpc_dataCriterios))
					{
						LinkedHashMap<String, DataConsultaPorCriterio> llhm_data;

						llhm_data = new LinkedHashMap<String, DataConsultaPorCriterio>();

						for(DataConsultaPorCriterio ldcpc_iterador : lcdcpc_dataCriterios)
						{
							Long   ll_idMatriculaIterador;
							String ls_idCirculoIterador;

							ll_idMatriculaIterador     = ldcpc_iterador.getIdMatricula();
							ls_idCirculoIterador       = ldcpc_iterador.getIdCirculo();

							if(
							    StringUtils.isValidString(ls_idCirculoIterador)
								    && NumericUtils.isValidLong(ll_idMatriculaIterador)
							)
							{
								String ls_matriculaCompleta;

								ls_matriculaCompleta = ls_idCirculoIterador + "-" + ll_idMatriculaIterador;

								if(llhm_data.containsKey(ls_matriculaCompleta))
								{
									DataConsultaPorCriterio ldcpc_temp;

									ldcpc_temp = llhm_data.get(ls_matriculaCompleta);

									if(ldcpc_temp != null)
									{
										String ls_anotaciones;
										String ls_anotacion;

										ls_anotaciones     = ldcpc_temp.getIdAnotacion();
										ls_anotacion       = ldcpc_iterador.getIdAnotacion();

										ls_anotaciones += ("," + ls_anotacion);

										ldcpc_temp.setIdAnotacion(ls_anotaciones);
									}
								}
								else
									llhm_data.put(ls_matriculaCompleta, ldcpc_iterador);
							}
						}

						for(Map.Entry<String, DataConsultaPorCriterio> entry : llhm_data.entrySet())
							lcdcpc_dataCriteriosFinal.add(entry.getValue());
					}

					ldccdc_datos.setDataConsultaPorCriterio(lcdcpc_dataCriteriosFinal);

					Solicitud ls_solicitud;
					ls_solicitud = ls_DAO.findByIdDocumento(ls_idDocumento);

					if(ls_solicitud != null)
						ldccdc_datos.setSolicitud(ls_DAO.findById(ls_solicitud));

					ll_result.add(ldccdc_datos);
				}
			}

			if(!CollectionUtils.isValidCollection(ll_result))
				throw new B2BException(ErrorKeys.NO_SE_ENCONTRARON_REGISTROS_CONSULTA);
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findByDatosDocumento", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return ll_result;
	}

	/**
	 * Find codigo nombre circulo destino.
	 *
	 * @return el valor de circulo registral
	 * @throws B2BException
	 */
	public CirculoRegistral findCodigoNombreCirculoDestino(String as_idCirculoDestino)
	    throws B2BException
	{
		DAOManager       ldm_manager;
		CirculoRegistral lcr_result;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		lcr_result      = new CirculoRegistral();

		try
		{
			CirculoRegistral lcr_circuloRegistral;

			if(StringUtils.isValidString(as_idCirculoDestino))
			{
				lcr_circuloRegistral = DaoCreator.getCirculoRegistralDAO(ldm_manager).findById(as_idCirculoDestino);

				if(lcr_circuloRegistral != null)
					lcr_result = lcr_circuloRegistral;
			}
			else
				throw new B2BException(ErrorKeys.ERROR_DATOS_CONSULTA);
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findByDatosDocumento", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lcr_result;
	}

	/**
	 * Método encargado de consultar un registro con unos parametros de entrada determinados en la tabla SDB_BNG_COMPLEMENTACION_PREDIO.
	 *
	 * @param ac_complementacion Objeto que contiene los datos necesarios para ejecutar la sentencia select sobre la tabla SDB_BNG_COMPLEMENTACION_PREDIO.
	 * @return Retorna  un objeto  de tipo  ComplementacionPredio que coincide con los parametros de entrada.
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see ComplementacionPredio
	 */
	public synchronized ComplementacionPredio findComplementacion(Complementacion ac_complementacion)
	    throws B2BException
	{
		return findComplementacion(ac_complementacion, false);
	}

	/**
	 * Método encargado de consultar un registro con unos parametros de entrada determinados en la tabla SDB_BNG_COMPLEMENTACION_PREDIO.
	 *
	 * @param ac_complementacion Objeto que contiene los datos necesarios para ejecutar la sentencia select sobre la tabla SDB_BNG_COMPLEMENTACION_PREDIO.
	 * @param ab_complementacionDesdeDigitador bandera que indica si el proceso se invoca desde la etapa de dig. masivo cambiando el flujo para este proceso
	 * @return Retorna  un objeto  de tipo  ComplementacionPredio que coincide con los parametros de entrada.
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see ComplementacionPredio
	 */
	public synchronized ComplementacionPredio findComplementacion(
	    Complementacion ac_complementacion, boolean ab_complementacionDesdeDigitador
	)
	    throws B2BException
	{
		DAOManager            ldm_manager;
		ComplementacionPredio lcp_return;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		lcp_return      = null;

		try
		{
			if(ac_complementacion != null)
			{
				String ls_idCirculo;
				Long   ll_idMatricula;

				ls_idCirculo       = ac_complementacion.getIdCirculo();
				ll_idMatricula     = ac_complementacion.getIdMatricula();

				if(!NumericUtils.isValidLong(ll_idMatricula))
					throw new B2BException(ErrorKeys.ERROR_MATRICULA_INVALIDA);

				if(!StringUtils.isValidString(ls_idCirculo))
					throw new B2BException(ErrorKeys.CIRCULO_REGISTRAL_SELECCIONADO_INVALIDO);

				PredioRegistro lpr_predioRegistro;

				lpr_predioRegistro = new PredioRegistro();

				lpr_predioRegistro.setIdCirculo(ls_idCirculo);
				lpr_predioRegistro.setIdMatricula(NumericUtils.getLong(ll_idMatricula));

				lpr_predioRegistro = DaoCreator.getPredioRegistroDAO(ldm_manager)
						                           .findByCirculoMatricula(lpr_predioRegistro);

				if(lpr_predioRegistro != null)
				{
					String ls_predioDefinitivo;

					ls_predioDefinitivo = lpr_predioRegistro.getPredioDefinitivo();

					if(
					    StringUtils.isValidString(ls_predioDefinitivo)
						    && ls_predioDefinitivo.equalsIgnoreCase(EstadoCommon.D)
					)
					{
						lcp_return = new ComplementacionPredio();

						lcp_return.setIdComplementacion(String.valueOf(lpr_predioRegistro.getIdComplementacion()));

						lcp_return = DaoCreator.getComplementacionPredioDAO(ldm_manager).findById(lcp_return);

						if(lcp_return == null)
							throw new B2BException(ErrorKeys.NO_SE_ENCONTRARON_REGISTROS_CONSULTA);

						if(!StringUtils.isValidString(lcp_return.getComplementacion()))
							throw new B2BException(ErrorKeys.NO_SE_ENCONTRARON_REGISTROS_COMPLEMENTACION);
					}
					else
					{
						Object[] aoa_messageArgs = new String[1];
						aoa_messageArgs[0] = ls_idCirculo + "-" + ll_idMatricula;
						throw new B2BException(ErrorKeys.ERROR_PREDIO_DEFINITIVO, aoa_messageArgs);
					}
				}
				else if(!ab_complementacionDesdeDigitador)
					throw new B2BException(ErrorKeys.NO_SE_ENCONTRARON_REGISTROS_CONSULTA);
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findComplementacion", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lcp_return;
	}

	/**
	 * Método encargado de consultar  un registro con unos parametros de entrada determinados en la tabla SDB_ACC_DATOS_ANT_SISTEMA.
	 *
	 *  ID_DATOS_ANT_SISTEMA
	 *
	 * @param al_idTurnoHistoria Variable de tipo Long que contiene un id turno historia determinado.
	 * @return  Retorna  un objeto  de tipo  DatosAntSistema que coincide con los parametros de entrada.
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see DatosAntSistema
	 */
	public synchronized DatosAntSistema findDatosAntSistema(Long al_idTurnoHistoria)
	    throws B2BException
	{
		DatosAntSistema ldas_data;
		DAOManager      ldm_manager;

		ldas_data       = null;
		ldm_manager     = DaoManagerFactory.getDAOManager();

		try
		{
			TurnoHistoria lth_turnoHistoria;

			lth_turnoHistoria = new TurnoHistoria();
			lth_turnoHistoria.setIdTurnoHistoria(al_idTurnoHistoria);
			lth_turnoHistoria = DaoCreator.getTurnoHistoriaDAO(ldm_manager).findById(lth_turnoHistoria);

			if(lth_turnoHistoria != null)
			{
				String ls_idSolicitud;

				ls_idSolicitud = lth_turnoHistoria.getIdSolicitud();

				if(StringUtils.isValidString(ls_idSolicitud))
				{
					Solicitud ls_solicitud;

					ls_solicitud = new Solicitud();
					ls_solicitud.setIdSolicitud(ls_idSolicitud);
					ls_solicitud = DaoCreator.getSolicitudDAO(ldm_manager).findById(ls_solicitud);

					if(ls_solicitud != null)
					{
						String ls_idDatosAntSistema;

						ls_idDatosAntSistema = ls_solicitud.getIdDatosAntSistema();

						if(StringUtils.isValidString(ls_idDatosAntSistema))
						{
							DatosAntSistema ldas_datosAntSistema;

							ldas_datosAntSistema = new DatosAntSistema();
							ldas_datosAntSistema.setIdDatosAntSistema(ls_idDatosAntSistema);
							ldas_datosAntSistema = DaoCreator.getDatosAntSistemaDAO(ldm_manager)
									                             .findById(ldas_datosAntSistema);

							if(ldas_datosAntSistema != null)
								ldas_data = ldas_datosAntSistema;
						}
					}
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findDatosPredioByTurno", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return ldas_data;
	}

	/**
	 * Método encargado de consultar  un registro con unos parametros de entrada determinados en la tabla SDB_PGN_OFICINA_ORIGEN.
	 *
	 * @param al_idTurnoHistoria Variable de tipo Long que contiene un id turno historia determinado.
	 * @return Retorna  un objeto  de tipo  ConsultaDatosDocumento que coincide con los parametros de entrada.
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see ConsultaDatosDocumento
	 */
	public synchronized ConsultaDatosDocumento findDatosDocumento(Long al_idTurnoHistoria)
	    throws B2BException
	{
		ConsultaDatosDocumento ld_data;
		DAOManager             ldm_manager;

		ld_data         = new ConsultaDatosDocumento();
		ldm_manager     = DaoManagerFactory.getDAOManager();

		try
		{
			TurnoHistoria lth_turnoHistoria;

			lth_turnoHistoria = new TurnoHistoria();
			lth_turnoHistoria.setIdTurnoHistoria(al_idTurnoHistoria);
			lth_turnoHistoria = DaoCreator.getTurnoHistoriaDAO(ldm_manager).findById(lth_turnoHistoria);

			if(lth_turnoHistoria != null)
			{
				String ls_idSolicitud;

				ls_idSolicitud = lth_turnoHistoria.getIdSolicitud();

				if(StringUtils.isValidString(ls_idSolicitud))
				{
					Solicitud ls_solicitud;

					ls_solicitud = new Solicitud();
					ls_solicitud.setIdSolicitud(ls_idSolicitud);
					ls_solicitud = DaoCreator.getSolicitudDAO(ldm_manager).findById(ls_solicitud);

					if(ls_solicitud != null)
					{
						ld_data.setSolicitud(ls_solicitud);

						String ls_idDocumento;

						ls_idDocumento = ls_solicitud.getIdDocumento();

						if(StringUtils.isValidString(ls_idDocumento))
						{
							Documento ld_datosDocumento;

							ld_datosDocumento = new Documento();
							ld_datosDocumento.setIdDocumento(ls_idDocumento);
							ld_datosDocumento = DaoCreator.getDocumentoDAO(ldm_manager).findById(ld_datosDocumento);

							if(ld_datosDocumento != null)
							{
								String     ls_idOficinaOrigen;
								BigDecimal lbd_version;

								ls_idOficinaOrigen     = ld_datosDocumento.getIdOficinaOrigen();
								lbd_version            = ld_datosDocumento.getVersion();

								ld_data.setDocumento(ld_datosDocumento);

								if(StringUtils.isValidString(ls_idOficinaOrigen) && (lbd_version != null))
								{
									OficinaOrigen loo_oficinaOrigen;

									loo_oficinaOrigen = new OficinaOrigen();
									loo_oficinaOrigen.setIdOficinaOrigen(ls_idOficinaOrigen);
									loo_oficinaOrigen.setVersion(lbd_version);

									loo_oficinaOrigen = DaoCreator.getOficinaOrigenDAO(ldm_manager)
											                          .findById(loo_oficinaOrigen);

									if(loo_oficinaOrigen != null)
										ld_data.setOficinaOrigen(loo_oficinaOrigen);
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

			clh_LOGGER.error("findDatosPredioByTurno", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return ld_data;
	}

	/**
	 * Método encargado de consultar  un registro con unos parametros de entrada determinados en la tabla SDB_ACC_DATOS_ANT_SISTEMA.
	 *
	 * @param as_userId  Variable de tipo String que contiene el usuario que realiza la acción.
	 * @param as_idTurno Variable de tipo String que contiene un id turno determinado.
	 * @param as_section  Variable de tipo String que contiene el tipo de consulta a ejecutar.
	 * @return Retorna una lista de LinkedHashMap que coincide con los parametros de entrada.
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see List
	 */
	public synchronized List<Map<String, Object>> findDatosPredioByTurno(
	    String as_userId, String as_idTurno, String as_section
	)
	    throws B2BException
	{
		DAOManager                ldm_manager;
		List<Map<String, Object>> ll_result;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		ll_result       = null;

		try
		{
			Map<Integer, Object> llhm_lhmpCriterios;

			llhm_lhmpCriterios = new LinkedHashMap<Integer, Object>();
			llhm_lhmpCriterios.put(NumericUtils.getInteger(1), as_idTurno);

			ll_result = DaoCreator.getAntiguoSistemaDAO(ldm_manager).getCustonQuery(as_section, llhm_lhmpCriterios);
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findDatosPredioByTurno", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return ll_result;
	}

	/**
	 * Método encargado de cargar la bandeja de consulta de antiguo sistema.
	 *
	 * @param as_userId Variable de tipo String que contiene el usuario que realiza la acción.
	 * @param al_idEtapa Variable de tipo Long que contiene un id etapa determinado.
	 * @param as_idTurno Variable de tipo String que contiene un id turno determinado.
	 * @param as_nir Variable de tipo String que contiene un nir determinado.
	 * @return  Retorna una lista de LinkedHashMap que coincide con los parametros de entrada.
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see List
	 */
	public synchronized List<Map<String, Object>> findDetailInbox(
	    String as_userId, Long al_idEtapa, String as_idTurno, String as_nir
	)
	    throws B2BException
	{
		DAOManager                ldm_manager;
		List<Map<String, Object>> ll_result;
		String                    ls_idSolicitud;
		Solicitud                 ls_Solicitud = new Solicitud();

		ldm_manager        = DaoManagerFactory.getDAOManager();
		ll_result          = null;
		ls_idSolicitud     = null;

		try
		{
			Map<Integer, Object> llhm_lhmpCriterios;

			llhm_lhmpCriterios = new LinkedHashMap<Integer, Object>();
			llhm_lhmpCriterios.put(NumericUtils.getInteger(1), al_idEtapa);
			llhm_lhmpCriterios.put(NumericUtils.getInteger(2), as_userId);

			if(StringUtils.isValidString(as_nir))
			{
				ls_Solicitud.setNir(as_nir);
				ls_Solicitud = DaoCreator.getConsultaTrazabilidadDAO(ldm_manager).findByNIR(ls_Solicitud);

				if(ls_Solicitud != null)
					ls_idSolicitud = ls_Solicitud.getIdSolicitud();
			}

			ll_result = DaoCreator.getAntiguoSistemaDAO(ldm_manager)
					                  .getCustonQueryDetail(as_idTurno, ls_idSolicitud, llhm_lhmpCriterios);

			if(ll_result != null)
			{
				for(Map<String, Object> lotc_tmp : ll_result)
				{
					if(lotc_tmp != null)
					{
						String        ls_idTurnoHistoria;
						TurnoHistoria lth_turnoHistoria;

						ls_idTurnoHistoria     = StringUtils.getString(lotc_tmp.get("ID_TURNO_HISTORIA"));
						lth_turnoHistoria      = new TurnoHistoria();

						if(StringUtils.isValidString(ls_idTurnoHistoria))
						{
							lth_turnoHistoria.setIdTurnoHistoria(NumericUtils.getLongWrapper(ls_idTurnoHistoria));
							lth_turnoHistoria = DaoCreator.getTurnoHistoriaDAO(ldm_manager).findById(lth_turnoHistoria);

							if(lth_turnoHistoria != null)
							{
								TurnoHistoria lth_tmp;
								lth_tmp = new TurnoHistoria();

								lth_tmp.setIdTurnoHistoria(
								    NumericUtils.getLongWrapper(lth_turnoHistoria.getIdAnterior())
								);

								lth_tmp = DaoCreator.getTurnoHistoriaDAO(ldm_manager).findById(lth_tmp);

								if(lth_tmp != null)
								{
									if(NumericUtils.getLong(lth_tmp.getIdEtapa()) == EtapaCommon.ID_ETAPA_REANOTACION)
									{
										TurnoHistoria lth_temp;
										lth_temp = new TurnoHistoria();

										lth_temp.setIdTurnoHistoria(
										    NumericUtils.getLongWrapper(lth_tmp.getIdAnterior())
										);

										lth_temp = DaoCreator.getTurnoHistoriaDAO(ldm_manager).findById(lth_temp);
									}
								}

								{
									FechaVenceTerminosUI lfvtui_fechaVencimiento;
									long                 ll_tiempoVencimiento;

									lfvtui_fechaVencimiento = new FechaVenceTerminosUI();

									lfvtui_fechaVencimiento.setFechaInicial(lth_turnoHistoria.getFechaCreacion());
									lfvtui_fechaVencimiento.setFechaFinal(lth_turnoHistoria.getFechaVencimiento());
									lfvtui_fechaVencimiento.setTipoCalendario(EstadoCommon.H);
									lfvtui_fechaVencimiento.setIdCirculo(lth_turnoHistoria.getIdCirculoUsuario());

									ll_tiempoVencimiento = DaoCreator.getUtilDAO(ldm_manager)
											                             .funcionCalcularDiasFechas(
											    lfvtui_fechaVencimiento
											);

									lotc_tmp.put("FECHA_VENCIMIENTO", lth_turnoHistoria.getFechaVencimiento());
									lotc_tmp.put(
									    "ESTADO_VENCIMIENTO", NumericUtils.getLongWrapper(ll_tiempoVencimiento)
									);

									if(lth_turnoHistoria.getFechaVencimiento() != null)
									{
										if(ll_tiempoVencimiento <= 0)
											lotc_tmp.put("ROJO", "TRUE");
										else
											lotc_tmp.put("ROJO", "FALSE");
									}
								}
							}
						}

						//lc_dataFinal.add(lotc_tmp);
					}
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findDetailInbox", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return ll_result;
	}

	/**
	 * Metodo encargado de consulta el detalle  de un documento con unos datos de entrada determinados.
	 *
	 * @param as_userId Variable de tipo String que contiene el usuario que realiza la acción.
	 * @param acdd_consultaDatosDocumento Objeto que contiene los datos necesarios para realizar las consultas sobres las tablas SDB_PGN_TIPO_DOCUMENTO_PUBLICO,SDB_PGN_OFICINA_ORIGEN y SDB_ACC_SOLICITUD
	 * @return Retorna  un objeto  de tipo  DocumentoData que coincide con los parametros de entrada.
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see DocumentoData
	 */
	public synchronized DocumentoData findDocumentoData(
	    String as_userId, ConsultaDatosDocumento acdd_consultaDatosDocumento
	)
	    throws B2BException
	{
		DocumentoData ldd_documentoData;
		DAOManager    ldm_manager;

		ldd_documentoData     = null;
		ldm_manager           = DaoManagerFactory.getDAOManager();

		try
		{
			if(acdd_consultaDatosDocumento != null)
			{
				ldd_documentoData = new DocumentoData();

				Documento ld_documento;
				ld_documento = acdd_consultaDatosDocumento.getDocumento();

				if(ld_documento != null)
					ld_documento = DaoCreator.getDocumentoDAO(ldm_manager).findById(ld_documento);

				OficinaOrigen loo_oficinaOrigen;

				loo_oficinaOrigen = acdd_consultaDatosDocumento.getOficinaOrigen();

				if(loo_oficinaOrigen != null)
					loo_oficinaOrigen = buscarOficinaOrigenPorId(loo_oficinaOrigen.getIdOficinaOrigen(), ldm_manager);

				Solicitud ls_solicitud;
				ls_solicitud = acdd_consultaDatosDocumento.getSolicitud();

				if(ls_solicitud != null)
					ls_solicitud = DaoCreator.getSolicitudDAO(ldm_manager).findById(ls_solicitud);

				if(ld_documento != null)
				{
					String ls_idTipoDocumento;
					ls_idTipoDocumento = ld_documento.getIdTipoDocumento();

					if(StringUtils.isValidString(ls_idTipoDocumento))
					{
						TipoDocumentoPublico ltdp_tipoDocumentoPublico;

						ltdp_tipoDocumentoPublico = new TipoDocumentoPublico();
						ltdp_tipoDocumentoPublico.setIdTipoDocumento(ls_idTipoDocumento);
						ltdp_tipoDocumentoPublico = DaoCreator.getTipoDocumentoPublicoDAO(ldm_manager)
								                                  .findById(ltdp_tipoDocumentoPublico);

						if(ltdp_tipoDocumentoPublico != null)
						{
							String ls_nombre;
							ls_nombre = ltdp_tipoDocumentoPublico.getNombre();

							if(StringUtils.isValidString(ls_nombre))
								ldd_documentoData.setTipoDocumento(ls_nombre);
						}
					}

					String ls_numeroDocumento;
					ls_numeroDocumento = ld_documento.getNumero();

					if(StringUtils.isValidString(ls_numeroDocumento))
						ldd_documentoData.setNumeroDocumento(ls_numeroDocumento);

					Date ld_fechaDocumento;
					ld_fechaDocumento = ld_documento.getFechaDocumento();

					if(ld_fechaDocumento != null)
						ldd_documentoData.setFechaDocumento(ld_fechaDocumento);
				}

				if(ls_solicitud != null)
				{
					String ls_exenta;
					ls_exenta = ls_solicitud.getEntidadExenta();

					if(StringUtils.isValidString(ls_exenta))
						ldd_documentoData.setEntidadExenta(ls_exenta);
				}

				if(loo_oficinaOrigen != null)
				{
					String ls_oficinaOrigen;
					ls_oficinaOrigen = loo_oficinaOrigen.getNombre();

					if(StringUtils.isValidString(ls_oficinaOrigen))
						ldd_documentoData.setOficinaOrigen(ls_oficinaOrigen);

					String ls_idTipoOficina;
					ls_idTipoOficina = loo_oficinaOrigen.getIdTipoOficina();

					if(StringUtils.isValidString(ls_idTipoOficina))
					{
						TipoOficina lto_tipoOficina;

						lto_tipoOficina = new TipoOficina();
						lto_tipoOficina.setIdTipoOficina(ls_idTipoOficina);
						lto_tipoOficina = DaoCreator.getTipoOficinaDAO(ldm_manager).findById(lto_tipoOficina);

						if(lto_tipoOficina != null)
						{
							String ls_nombre;
							ls_nombre = lto_tipoOficina.getNombre();

							if(StringUtils.isValidString(ls_nombre))
								ldd_documentoData.setTipoOficina(ls_nombre);
						}
					}

					String ls_idTipoEntidad;
					ls_idTipoEntidad = loo_oficinaOrigen.getIdTipoEntidad();

					if(StringUtils.isValidString(ls_idTipoEntidad))
					{
						TipoEntidad lte_tipoEntidad;

						lte_tipoEntidad = new TipoEntidad();
						lte_tipoEntidad.setIdTipoEntidad(ls_idTipoEntidad);
						lte_tipoEntidad = DaoCreator.getTipoEntidadDAO(ldm_manager).findById(lte_tipoEntidad);

						if(lte_tipoEntidad != null)
						{
							String ls_nombre;
							ls_nombre = lte_tipoEntidad.getNombre();

							if(StringUtils.isValidString(ls_nombre))
								ldd_documentoData.setTipoEntidad(ls_nombre);
						}
					}

					String ls_idPais;
					ls_idPais = loo_oficinaOrigen.getIdPais();

					if(StringUtils.isValidString(ls_idPais))
					{
						Pais lp_pais;

						lp_pais = new Pais();
						lp_pais.setIdPais(ls_idPais);
						lp_pais = DaoCreator.getPaisDAO(ldm_manager).findById(lp_pais);

						if(lp_pais != null)
						{
							String ls_nombrePais;
							ls_nombrePais = lp_pais.getNombre();

							if(StringUtils.isValidString(ls_nombrePais))
								ldd_documentoData.setPais(ls_nombrePais);

							String ls_idDepartamento;
							ls_idDepartamento = loo_oficinaOrigen.getIdDepartamento();

							if(StringUtils.isValidString(ls_idDepartamento))
							{
								Departamento ld_departamento;

								ld_departamento = new Departamento();
								ld_departamento.setIdPais(lp_pais.getIdPais());
								ld_departamento.setIdDepartamento(ls_idDepartamento);
								ld_departamento = DaoCreator.getDepartamentoDAO(ldm_manager).findById(ld_departamento);

								if(ld_departamento != null)
								{
									String ls_nombreDepartamento;
									ls_nombreDepartamento = ld_departamento.getNombre();

									if(StringUtils.isValidString(ls_nombreDepartamento))
										ldd_documentoData.setDepartamento(ls_nombreDepartamento);

									String ls_idMunicipio;
									ls_idMunicipio = loo_oficinaOrigen.getIdMunicipio();

									if(StringUtils.isValidString(ls_idMunicipio))
									{
										Municipio lm_municipio;

										lm_municipio = new Municipio();
										lm_municipio.setIdPais(lp_pais.getIdPais());
										lm_municipio.setIdDepartamento(ld_departamento.getIdDepartamento());
										lm_municipio.setIdMunicipio(ls_idMunicipio);
										lm_municipio = DaoCreator.getMunicipioDAO(ldm_manager).findById(lm_municipio);

										if(lm_municipio != null)
										{
											String ls_nombreMunicipio;
											ls_nombreMunicipio = lm_municipio.getNombre();

											if(StringUtils.isValidString(ls_nombreMunicipio))
												ldd_documentoData.setMunicipio(ls_nombreMunicipio);
										}
									}
								}
							}
						}
					}
				}

				String ls_turnoPadre;
				ls_turnoPadre = acdd_consultaDatosDocumento.getIdTurno();

				if(acdd_consultaDatosDocumento.isIndVinculacion() && StringUtils.isValidString(ls_turnoPadre))
				{
					TurnoDerivado             ltd_turnosDerivado;
					Collection<TurnoDerivado> lctd_turnoDerivados;

					ltd_turnosDerivado = new TurnoDerivado();

					ltd_turnosDerivado.setIdTurnoPadre(ls_turnoPadre);

					lctd_turnoDerivados = DaoCreator.getTurnoDerivadoDAO(ldm_manager)
							                            .findByIdTurnoPadre(ltd_turnosDerivado);

					if(CollectionUtils.isValidCollection(lctd_turnoDerivados))
					{
						Collection<Documento> lcd_cd;
						Documento             ld_d;

						lcd_cd = new ArrayList<Documento>();

						for(TurnoDerivado ltd_tmp : lctd_turnoDerivados)
						{
							if(ltd_tmp != null)
							{
								if(ltd_tmp.isIndVinculado())
								{
									ld_d = new Documento();

									ld_d.setTipoTurno(IdentificadoresCommon.TURNO_PRINCIPAL);
									ld_d.setIdTurno(ltd_tmp.getIdTurnoPadre());

									lcd_cd.add(ld_d);

									ld_d = new Documento();

									ld_d.setTipoTurno(IdentificadoresCommon.TURNO_VINCULADO);
									ld_d.setIdTurno(ltd_tmp.getIdTurnoHijo());

									lcd_cd.add(ld_d);
								}
							}
						}

						if(CollectionUtils.isValidCollection(lcd_cd))
							ldd_documentoData.setListadoDocumentos(lcd_cd);
					}
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findDocumentoData", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return ldd_documentoData;
	}

	/**
	 * Metodo encargado de consulta el detalle  de turno con unos datos de entrada determinados.
	 *
	 * @param at_turno Objeto de tipo Turno que contiene los datos necesarios para realizar la consulta.
	 * @return  Retorna  un objeto  de tipo  Turno que coincide con los parametros de entrada.
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see Turno
	 */
	public synchronized Turno findIdCirculoOrigen(Turno at_turno)
	    throws B2BException
	{
		DAOManager ldm_manager;
		Turno      lc_data;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		lc_data         = null;

		try
		{
			lc_data = DaoCreator.getTurnoDAO(ldm_manager).findById(at_turno);
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findIdCirculoOrigen", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lc_data;
	}

	/**
	 * Método encargado de consultar la cantidad de datos tramitados para un usuario determinado.
	 *
	 * @param as_userId Variable de tipo String que contiene el usuario que realiza la acción.
	 * @param as_idTurno Variable de tipo String que contiene un id turno determinado.
	 * @param as_nir Variable de tipo String que contiene un nir determinado.
	 * @return  Retorna  una colección de datos   de tipo  TramiteCantidad que coincide con los parametros de entrada.
	 * @throws B2BException Señala que se ha producido una excepción
	 *
	 */
	public synchronized Collection<TramiteCantidad> findInboxByUserId(
	    String as_userId, String as_idTurno, String as_nir, long al_etapa
	)
	    throws B2BException
	{
		Collection<Etapa>           lc_data;
		Collection<TramiteCantidad> lc_result;
		DAOManager                  ldm_manager;

		lc_data         = null;
		lc_result       = null;
		ldm_manager     = DaoManagerFactory.getDAOManager();

		try
		{
			lc_result     = new ArrayList<TramiteCantidad>();
			lc_data       = findAntiguoSistema(as_userId, al_etapa);

			if(CollectionUtils.isValidCollection(lc_data))
			{
				for(Etapa le_etapaActual : lc_data)
				{
					if(le_etapaActual != null)
					{
						LinkedList<LinkedHashMap<String, Object>> ll_data;
						LinkedHashMap<Integer, Object>            llhmio_criterios;
						String                                    ls_idSolicitud;
						AntiguoSistemaDAO                         laso_dao;

						llhmio_criterios = new LinkedHashMap<Integer, Object>();

						llhmio_criterios.put(
						    NumericUtils.getInteger(1), NumericUtils.getLongWrapper(le_etapaActual.getIdEtapa())
						);

						llhmio_criterios.put(NumericUtils.getInteger(2), as_userId);

						ls_idSolicitud     = null;
						laso_dao           = DaoCreator.getAntiguoSistemaDAO(ldm_manager);

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

						ll_data = laso_dao.getCustonQueryIbox(as_idTurno, ls_idSolicitud, llhmio_criterios);

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
									        NumericUtils.getLongWrapper(le_etapaActual.getIdEtapa()),
									        le_etapaActual.getNombre()
									    )
									);
								}
							}
						}
					}
				}
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
	 * Método encargado de consultar los documentos ingresados en informe de búsqueda.
	 *
	 * @param ads_documentoSalida Objeto que contiene la data de ant sistema y el turno para realizar la consulta.
	 * @return Colección que contiene la data consultada.
	 * @throws B2BException Señala que se ha producido una excepción
	 *
	 */
	public synchronized Collection<DocumentosSalida> findInformesBusqueda(DocumentosSalida ads_documentoSalida)
	    throws B2BException
	{
		Collection<DocumentosSalida> lci_return;
		DAOManager                   ldm_manager;

		lci_return      = null;
		ldm_manager     = DaoManagerFactory.getDAOManager();

		try
		{
			if(ads_documentoSalida != null)
				lci_return = DaoCreator.getDocumentosSalidaDAO(ldm_manager)
						                   .findByIdTurnoHistoriaAntSistema(ads_documentoSalida);
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();
			clh_LOGGER.error("findInformesBusqueda", lb2be_e);
			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lci_return;
	}

	/**
	 * Método encargado de consultar las matrículas asociadas al proceso de antiguo sistema.
	 *
	 * @param as_idDatosAntSistemaActual Variable de tipo String que contiene el id de los datos de antiguo sistema
	 * @param as_idTurno Variable de tipo String que contiene el id del turno en tramite
	 * @return Mapa que contiene las matrículas y la colección de solicitud matrícula acto de la misma
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see Map
	 */
	public synchronized Map<String, Collection<SolicitudMatriculaActo>> findMatriculasSolicitudAntSistema(
	    String as_idDatosAntSistemaActual, String as_idTurno
	)
	    throws B2BException
	{
		Map<String, Collection<SolicitudMatriculaActo>> lcsma_return;
		DAOManager                                      ldm_manager;

		lcsma_return     = null;
		ldm_manager      = DaoManagerFactory.getDAOManager();

		try
		{
			if(StringUtils.isValidString(as_idDatosAntSistemaActual) && StringUtils.isValidString(as_idTurno))
			{
				Collection<SolicitudMatricula> lcsm_matriculas;

				lcsma_return        = new HashMap<String, Collection<SolicitudMatriculaActo>>();
				lcsm_matriculas     = DaoCreator.getSolicitudMatriculaDAO(ldm_manager)
						                            .findMatriculasByturnoAntSistema(
						    as_idTurno, as_idDatosAntSistemaActual
						);

				if(CollectionUtils.isValidCollection(lcsm_matriculas))
				{
					Iterator<SolicitudMatricula> lism_iterator;

					lism_iterator = lcsm_matriculas.iterator();

					while(lism_iterator.hasNext())
					{
						SolicitudMatricula lsm_iterador;

						lsm_iterador = lism_iterator.next();

						if(lsm_iterador != null)
						{
							String ls_idCirculo;
							long   ll_idMatricula;

							ls_idCirculo       = lsm_iterador.getIdCirculo();
							ll_idMatricula     = lsm_iterador.getIdMatricula();

							if(StringUtils.isValidString(ls_idCirculo) && (ll_idMatricula > 0))
							{
								Collection<SolicitudMatriculaActo> lcsma_actos;
								SolicitudMatriculaActo             lsma_matriculaActo;

								lsma_matriculaActo = new SolicitudMatriculaActo();

								lsma_matriculaActo.setIdCirculo(ls_idCirculo);
								lsma_matriculaActo.setIdMatricula(ll_idMatricula);
								lsma_matriculaActo.setIdSolicitud(lsm_iterador.getIdSolicitud());

								lcsma_actos = DaoCreator.getSolicitudMatriculaActoDAO(ldm_manager)
										                    .findAllByIdSolicitud(lsma_matriculaActo, true);

								if(CollectionUtils.isValidCollection(lcsma_actos))
									lcsma_return.put(ls_idCirculo + "-" + ll_idMatricula, lcsma_actos);
								else
									lcsma_return.put(ls_idCirculo + "-" + ll_idMatricula, null);
							}
						}
					}
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findMatriculasSolicitudAntSistema", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lcsma_return;
	}

	/**
	 * Método encargado de consultar el detalle de un registro determinado de la tabla SDB_PNG_NATURALEZA_JURIDICA .
	 *
	 * @param anj_param Objeto de tipo NaturalezaJuridica que contiene los datos necesarios para realizar la consulta.
	 * @return Retorna  un objeto  de tipo  NaturalezaJuridica que coincide con los parametros de entrada.
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see NaturalezaJuridica
	 */
	public synchronized NaturalezaJuridica findNaturalezajuridicaById(NaturalezaJuridica anj_param)
	    throws B2BException
	{
		DAOManager         ldm_manager;
		NaturalezaJuridica lnj_data;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		lnj_data        = null;

		try
		{
			if(anj_param != null)
			{
				NaturalezaJuridicaDAO lnj_DAO;

				lnj_DAO       = DaoCreator.getNaturalezaJuridicaDAO(ldm_manager);
				anj_param     = lnj_DAO.findByIdMaxVersion(anj_param);
				lnj_data      = lnj_DAO.findById(anj_param);
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findNaturalezajuridicaById", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lnj_data;
	}

	/**
	 * Método encargado de consultar las observaciones del turno historia anterior (calificación) para ant sistema.
	 *
	 * @param as_idTurnoHistoria variable de tipo String que contiene el id turno historia del proceso actual
	 * @return Variable de tipo String que contiene las observaciones del calificador (etapa anterior)
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see String
	 */
	public synchronized String findObservacionesCalificador(String as_idTurnoHistoria)
	    throws B2BException
	{
		DAOManager ldm_manager;
		String     ls_return;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		ls_return       = null;

		try
		{
			if(StringUtils.isValidString(as_idTurnoHistoria))
			{
				Long ll_idTurnoHistoria;

				ll_idTurnoHistoria = NumericUtils.getLongWrapper(as_idTurnoHistoria);

				if(NumericUtils.isValidLong(ll_idTurnoHistoria))
				{
					TurnoHistoria    lth_turnoHistoria;
					TurnoHistoriaDAO lth_DAO;

					lth_turnoHistoria     = new TurnoHistoria();
					lth_DAO               = DaoCreator.getTurnoHistoriaDAO(ldm_manager);

					lth_turnoHistoria.setIdTurnoHistoria(ll_idTurnoHistoria);

					lth_turnoHistoria = lth_DAO.findById(lth_turnoHistoria);

					if(lth_turnoHistoria != null)
					{
						BigDecimal lbd_idTurnoHistoriaAnterior;

						lbd_idTurnoHistoriaAnterior = lth_turnoHistoria.getIdAnterior();

						if(NumericUtils.isValidBigDecimal(lbd_idTurnoHistoriaAnterior))
						{
							Long ll_idTurnoHistoriaAnterior;

							ll_idTurnoHistoriaAnterior = NumericUtils.getLongWrapper(lbd_idTurnoHistoriaAnterior);

							if(NumericUtils.isValidLong(ll_idTurnoHistoriaAnterior))
							{
								TurnoHistoria lth_turnoHistoriaAnterior;

								lth_turnoHistoriaAnterior = new TurnoHistoria();

								lth_turnoHistoriaAnterior.setIdTurnoHistoria(ll_idTurnoHistoriaAnterior);

								lth_turnoHistoriaAnterior = lth_DAO.findById(lth_turnoHistoriaAnterior);

								if(lth_turnoHistoriaAnterior != null)
									ls_return = lth_turnoHistoriaAnterior.getObservaciones();
							}
						}
					}
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findObservacionesCalificador", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return ls_return;
	}

	/**
	 * Método encargado de consultar el detalle de un registro determinado de la tabla SDB_BNG_PREDIO_REGISTRO .
	 *
	 * @param aa_parametros Objeto de tipo Anotacion que contiene los datos necesarios para realizar la consulta.
	 * @param as_userId Variable de tipo String que contiene el usuario que realiza la acción.
	 * @return  Retorna  un objeto  de tipo  Anotacion que coincide con los parametros de entrada.
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see Anotacion
	 */
	public synchronized Anotacion findPredioRegistroById(Anotacion aa_parametros, String as_userId)
	    throws B2BException
	{
		DAOManager ldm_manager;
		Anotacion  la_datos;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		la_datos        = null;

		try
		{
			if(aa_parametros != null)
			{
				PredioRegistro lpr_predioRegistro;
				lpr_predioRegistro = aa_parametros.getPredioRegistro();

				if(lpr_predioRegistro != null)
				{
					String ls_circulo;
					long   ll_matricula;

					ll_matricula     = lpr_predioRegistro.getIdMatricula();
					ls_circulo       = lpr_predioRegistro.getIdCirculo();

					if(!StringUtils.isValidString(ls_circulo) || (ll_matricula <= 0))
					{
						Object[] aoa_messageArgs = new String[1];
						aoa_messageArgs[0] = ls_circulo + "-" + ll_matricula;
						throw new B2BException(ErrorKeys.MATRICULA_INMOBILIARIA_INVALIDA, aoa_messageArgs);
					}

					lpr_predioRegistro.setValidMatricula(true);

					lpr_predioRegistro = DaoCreator.getPredioRegistroDAO(ldm_manager).findById(lpr_predioRegistro);

					if(lpr_predioRegistro == null)
					{
						Object[] aoa_messageArgs = new String[1];
						aoa_messageArgs[0] = ls_circulo + "-" + ll_matricula;
						throw new B2BException(ErrorKeys.MATRICULA_INMOBILIARIA_NO_ENCONTRADA, aoa_messageArgs);
					}

					{
						DireccionPredio ldp_direccionPredio;
						ldp_direccionPredio = new DireccionPredio();

						ldp_direccionPredio.setIdCirculo(ls_circulo);
						ldp_direccionPredio.setIdMatricula(NumericUtils.getLongWrapper(ll_matricula));

						ldp_direccionPredio = DaoCreator.getDireccionPredioDAO(ldm_manager).findById(
							    ldp_direccionPredio
							);

						if(ldp_direccionPredio == null)
						{
							Object[] aoa_messageArgs = new String[1];
							aoa_messageArgs[0] = ls_circulo + "-" + ll_matricula;
							throw new B2BException(ErrorKeys.DIRECCION_PREDIO_NO_ENCONTRADA, aoa_messageArgs);
						}

						la_datos = new Anotacion();

						la_datos.setPredioRegistro(lpr_predioRegistro);
						la_datos.setDireccionPredio(ldp_direccionPredio);
					}
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findDireccionPredioById", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return la_datos;
	}

	/**
	 * Método encargado de consultar los procesos que se realizaron en el trámite de antiguo sistema.
	 *
	 * @param as_idTurno Variable de tipo String que contiene el id del turno en trámite
	 * @param as_idDatosAntSistemaActual Variable de tipo String que contiene el id de los datos de antiguo sistema
	 * @return Mapa que contiene el nombre de los procesos que se tramitaron en antiguo sistema
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see Map
	 */
	public synchronized Map<String, Boolean> findProcesoAntiguoSistema(
	    String as_idTurno, String as_idDatosAntSistemaActual, String as_idTurnoHistoria
	)
	    throws B2BException
	{
		DAOManager           ldm_manager;
		Map<String, Boolean> lmsb_return;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		lmsb_return     = null;

		try
		{
			lmsb_return = findProcesoAntiguoSistema(
				    as_idTurno, as_idDatosAntSistemaActual, as_idTurnoHistoria, ldm_manager
				);
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findProcesoAntiguoSistema", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lmsb_return;
	}

	/**
	 * Método encargado de consultar el detalle de un registro determinado de la tabla SDB_PGN_TIPO_ACTO.
	 *
	 * @param aps_param Objeto de tipo TipoActo que contiene los datos necesarios para realizar la consulta.
	 * @return Retorna  un objeto  de tipo  Anotacion que coincide con los parametros de entrada.
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see TipoActo
	 */
	public synchronized TipoActo findTipoActoById(TipoActo aps_param)
	    throws B2BException
	{
		DAOManager ldm_manager;
		TipoActo   lta_tipoActo;

		ldm_manager      = DaoManagerFactory.getDAOManager();
		lta_tipoActo     = null;

		try
		{
			String ls_idTipoActo;
			ls_idTipoActo = aps_param.getIdTipoActo();

			if(ls_idTipoActo != null)
				lta_tipoActo = DaoCreator.getTipoActoDAO(ldm_manager).findById(aps_param);
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findTipoActoById", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lta_tipoActo;
	}

	/**
	 * Método encargado de consultar el detalle de un registro determinado de la tabla SDB_ACC_TURNO_HISTORIA.
	 *
	 * @param ath_param Objeto de tipo TurnoHistoria que contiene los datos necesarios para realizar la consulta.
	 * @return Retorna  un objeto  de tipo  TurnoHistoria que coincide con los parametros de entrada.
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see TurnoHistoria
	 */
	public synchronized TurnoHistoria findTurnoHistoriaById(TurnoHistoria ath_param)
	    throws B2BException
	{
		DAOManager    ldm_manager;
		TurnoHistoria lc_data;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		lc_data         = null;

		try
		{
			if(ath_param != null)
			{
				lc_data = DaoCreator.getTurnoHistoriaDAO(ldm_manager).findById(ath_param);

				if(lc_data != null)
				{
					String  ls_idSolicitud;
					String  ls_idTurno;
					boolean lb_validarFechaEtapa;

					ls_idSolicitud           = lc_data.getIdSolicitud();
					ls_idTurno               = lc_data.getIdTurno();
					lb_validarFechaEtapa     = ath_param.isFechaEtapaValida();

					if(StringUtils.isValidString(ls_idSolicitud))
					{
						Solicitud los_tmp;

						los_tmp = new Solicitud();
						los_tmp.setIdSolicitud(ls_idSolicitud);

						los_tmp = DaoCreator.getSolicitudDAO(ldm_manager).findById(los_tmp);

						if(
						    (los_tmp != null)
							    && StringUtils.getStringNotNull(los_tmp.getIdSubproceso())
							                      .equalsIgnoreCase(ProcesoCommon.ID_SUBPROCESO_4)
						)
							lc_data.setDetalleRepConstancia(true);
					}

					if(StringUtils.isValidString(ls_idTurno))
					{
						Turno lt_turno;

						lt_turno = new Turno();

						lt_turno.setIdTurno(ls_idTurno);

						lt_turno = DaoCreator.getTurnoDAO(ldm_manager).findById(lt_turno);

						if(lt_turno != null)
							lc_data.setIdCirculo(lt_turno.getIdCirculo());
					}

					if(lb_validarFechaEtapa)
					{
						Date ld_fechaInicial;

						ld_fechaInicial = lc_data.getFechaInicial();

						if(ld_fechaInicial == null)
							DaoCreator.getTurnoHistoriaDAO(ldm_manager).updateFechaInicial(lc_data);
					}
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findTurnoHistoriaById", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lc_data;
	}

	/**
	 * Método encargado de buscar zona registral con base a un id circulo registral.
	 *
	 * @param azr_zonaRegistral Objeto que contiene la información de la zona registral a consultar.
	 * @return Zona registral consultada.
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see ZonaRegistral
	 */
	public synchronized ZonaRegistral findZonaRegistralCirculo(ZonaRegistral azr_zonaRegistral)
	    throws B2BException
	{
		DAOManager ldm_manager;

		ldm_manager = DaoManagerFactory.getDAOManager();

		try
		{
			if(azr_zonaRegistral != null)
			{
				ZonaRegistralDAO lzr_DAO;

				lzr_DAO     = DaoCreator.getZonaRegistralDAO(ldm_manager);

				azr_zonaRegistral = lzr_DAO.findByIdCirculo(azr_zonaRegistral);
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findZonaRegistralCirculo", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return azr_zonaRegistral;
	}

	/**
	 * Firmar libro ant sistema.
	 *
	 * @param as_idDatosAntSistema correspondiente al valor de as id datos ant sistema
	 * @param as_userId correspondiente al valor de as user id
	 * @param as_remoteId correspondiente al valor de as remote Id
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public synchronized void firmarLibroAntSistema(String as_idDatosAntSistema, String as_userId, String as_remoteIp)
	    throws B2BException
	{
		DAOManager ldm_manager;
		ldm_manager = DaoManagerFactory.getDAOManager();

		try
		{
			DatosAntSistemaDAO ldasd_DAO;
			DatosAntSistema    ldas_tmp;

			ldasd_DAO     = DaoCreator.getDatosAntSistemaDAO(ldm_manager);
			ldas_tmp      = new DatosAntSistema();

			ldas_tmp.setIdDatosAntSistema(as_idDatosAntSistema);

			ldas_tmp = ldasd_DAO.findById(ldas_tmp);

			if(ldas_tmp != null)
				ldasd_DAO.updateActivoRequiereFirmaLibro(ldas_tmp);
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("firmarLibroAntSistema", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}
	}

	/**
	 *  Método encargado de generar archivo en formato pdf  utilizando  las plantillas  PLANTILLA_NOTA_RECHAZO_ANTIGUO_SISTEMA y PLANTILLA_NOTA_DEVOLUTIVA.
	 *
	 * @param aotc_tc  Objeto que contiene los tipos de causales a incluir en la plantilla.
	 * @param as_turnoH Variable de tipo String que contiene un id turno historia determinado.
	 * @param as_idUsuario Variable de tipo String que contiene el usuario que realiza la acción.
	 * @param aahr_ahr Objeto  que contiene los datos de ofina origen y/o destino.
	 * @param as_remoteIp Variable de tipo String que contiene la dirección ip remota desde donde seque realiza la acción.
	 * @param ab_firmaMasiva Variable de tipo boolean , si es true inserta firma masiva en la plantilla de lo contrario no lo hace.
	 * @return Arreglo de bytes que contiene la plantilla generada.
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see byte[]
	 */
	public synchronized byte[] generarArchivoRechazo(
	    TipoCausal aotc_tc, String as_turnoH, String as_idUsuario, AmpliacionHistoriaRegistral aahr_ahr,
	    String as_remoteIp, boolean ab_firmaMasiva
	)
	    throws B2BException
	{
		int                         li_validatorDevolucion;
		int                         li_validatorRechazo;
		DAOManager                  ldm_manager;
		StringBuilder               lsb_causales;
		String                      ls_observaciones;
		String                      ls_constante;
		byte[]                      lba_plantilla;
		Constantes                  loc_datos;
		Constantes                  loc_plantilla;
		byte[]                      lba_notaDevolutiva;
		String                      ls_plantilla;
		Collection<TipoCausal>      lctc_causales;
		List<TipoCausal>            lltc_causalesParaGuardar;
		ProcesoDAO                  lpDAO_p;
		SubprocesoDAO               lspDAO_sp;
		TurnoHistoriaDAO            lothd_thd;
		TurnoHistoria               loth_dataFile;
		TurnoHistoria               loth_detalle;
		boolean                     lb_motivo;
		Proceso                     lp_proc;
		Subproceso                  lsp_subp;
		AmpliacionHistoriaRegistral lahr_historiaReg;

		li_validatorDevolucion       = 0;
		li_validatorRechazo          = 0;
		lba_notaDevolutiva           = null;
		lp_proc                      = null;
		lsp_subp                     = null;
		ldm_manager                  = DaoManagerFactory.getDAOManager();
		lothd_thd                    = DaoCreator.getTurnoHistoriaDAO(ldm_manager);
		lpDAO_p                      = DaoCreator.getProcesoDAO(ldm_manager);
		lspDAO_sp                    = DaoCreator.getSubprocesoDAO(ldm_manager);
		lahr_historiaReg             = aahr_ahr;
		lltc_causalesParaGuardar     = new ArrayList<TipoCausal>();

		if((aotc_tc != null) && (aotc_tc.getListTiposCausales() != null))
		{
			for(TipoCausal ltc_seleccion : aotc_tc.getListTiposCausales())
			{
				boolean lb_encontro;

				lb_encontro = false;

				if(
				    ltc_seleccion.isSeleccionado()
					    && ltc_seleccion.getPlantilla()
					                        .equalsIgnoreCase(ConstanteCommon.PLANTILLA_NOTA_RECHAZO_ANTIGUO_SISTEMA)
				)
				{
					li_validatorDevolucion = 1;
					lltc_causalesParaGuardar.add(ltc_seleccion);

					lb_encontro = true;
				}
				else if(
				    ltc_seleccion.isSeleccionado()
					    && ltc_seleccion.getPlantilla().equalsIgnoreCase(ConstanteCommon.PLANTILLA_NOTA_DEVOLUTIVA)
				)
				{
					li_validatorRechazo = 1;
					lltc_causalesParaGuardar.add(ltc_seleccion);

					lb_encontro = true;
				}

				if(lb_encontro)
				{
					String ls_proceso;
					String ls_subproceso;

					ls_proceso        = ltc_seleccion.getIdProceso();
					ls_subproceso     = ltc_seleccion.getIdSubProceso();

					if((lsp_subp == null) && (lp_proc == null))
					{
						lsp_subp     = new Subproceso();
						lp_proc      = new Proceso();

						if(StringUtils.isValidString(ls_proceso))
						{
							lsp_subp.setIdProceso(ls_proceso);

							if(StringUtils.isValidString(ls_subproceso))
							{
								lp_proc.setIdProceso(ls_proceso);

								lp_proc = lpDAO_p.findById(lp_proc);

								lsp_subp.setIdSubproceso(ls_subproceso);

								lsp_subp = lspDAO_sp.findById(lsp_subp);
							}
						}
					}

					lb_encontro = false;
				}
			}

			if(
			    ((li_validatorDevolucion == 1) && (li_validatorRechazo != 1))
				    || ((li_validatorDevolucion != 1) && (li_validatorRechazo == 1))
			)
			{
				if(li_validatorDevolucion == 0)
					lb_motivo = true;
				else
					lb_motivo = false;

				loc_datos         = new Constantes();
				loc_plantilla     = new Constantes();
				lsb_causales      = new StringBuilder();
				ls_constante      = (lb_motivo ? ConstanteCommon.PLANTILLA_NOTA_DEVOLUTIVA
					                           : ConstanteCommon.PLANTILLA_NOTA_RECHAZO_ANTIGUO_SISTEMA);
				loc_datos.setIdConstante(ls_constante);
				loth_detalle = new TurnoHistoria();

				try
				{
					if(aotc_tc != null)
					{
						loc_plantilla = DaoCreator.getConstantesDAO(ldm_manager).findImagen(loc_datos);

						if(loc_plantilla != null)
						{
							lba_plantilla = loc_plantilla.getImagenes();

							if(lba_plantilla != null)
							{
								ls_observaciones     = aotc_tc.getObservaciones();
								ls_plantilla         = new String(lba_plantilla);
								lctc_causales        = aotc_tc.getListTiposCausales();

								String     ls_fecha;
								String     ls_hora;
								String     ls_turnoDerivado;
								String     ls_turno;
								Long       ll_turnoHist;
								Date       ld_fechaActual;
								DateFormat lsf_dateFormat;
								DateFormat lsf_formatTime;

								ld_fechaActual       = new Date();
								lsf_dateFormat       = new SimpleDateFormat(FormatoFechaCommon.DIA_MES_ANIO);
								lsf_formatTime       = new SimpleDateFormat("HH:mm:ss");
								ls_fecha             = lsf_dateFormat.format(ld_fechaActual);
								ls_hora              = lsf_formatTime.format(ld_fechaActual);
								ll_turnoHist         = NumericUtils.getLongWrapper(as_turnoH);

								if(StringUtils.isValidString(ls_plantilla))
								{
									loth_detalle.setIdTurnoHistoria(ll_turnoHist);
									loth_detalle = lothd_thd.findById(loth_detalle);

									if(loth_detalle == null)
										throw new B2BException(ErrorKeys.ERROR_SIN_AMPLIACION_HISTORIA_REGISTRAL);

									Calendar            lc_fecha;
									Map<String, String> lmss_datos;

									lmss_datos     = null;
									lc_fecha       = Calendar.getInstance();

									if(!lb_motivo)
									{
										CompletitudComplementacionDAO lccDAO_cc;
										lccDAO_cc = DaoCreator.getCompletitudComplementacionDAO(ldm_manager);

										CompletitudComplementacion lcc_completitud;
										lcc_completitud = new CompletitudComplementacion();

										lcc_completitud.setIdTurno(loth_detalle.getIdTurno());
										lcc_completitud.setIdTurnoHistoria(loth_detalle.getIdTurnoHistoria());

										lcc_completitud = lccDAO_cc.findByTurno(lcc_completitud);

										if(lcc_completitud == null)
											throw new B2BException(ErrorKeys.ERROR_SIN_AMPLIACION_HISTORIA_REGISTRAL);

										String ls_idCirculoOrigen;
										String ls_idCirculoDestino;

										if(lahr_historiaReg == null)
											throw new B2BException(ErrorKeys.ERROR_SIN_AMPLIACION_HISTORIA_REGISTRAL);

										ls_idCirculoOrigen      = lcc_completitud.getIdCirculoRegistralDestino();
										ls_idCirculoDestino     = loth_detalle.getIdCirculoUsuario();

										CirculoRegistral lcr_circuloRegistralOrigen;
										CirculoRegistral lcr_circuloRegistralDestino;

										lcr_circuloRegistralOrigen = new CirculoRegistral();
										lcr_circuloRegistralDestino = new CirculoRegistral();

										lcr_circuloRegistralOrigen.setIdCirculo(ls_idCirculoOrigen);
										lcr_circuloRegistralDestino.setIdCirculo(ls_idCirculoDestino);

										lcr_circuloRegistralOrigen      = DaoCreator.getCirculoRegistralDAO(
											    ldm_manager
											).findById(lcr_circuloRegistralOrigen);
										lcr_circuloRegistralDestino     = DaoCreator.getCirculoRegistralDAO(
											    ldm_manager
											).findById(lcr_circuloRegistralDestino);

										if(lcr_circuloRegistralOrigen == null)
											throw new B2BException(ErrorKeys.NO_SE_GENERO_CERTIFICADO_AMPLIACION);

										if(lcr_circuloRegistralDestino == null)
											throw new B2BException(ErrorKeys.NO_SE_GENERO_CERTIFICADO_AMPLIACION);

										String ls_idOficinaOrigen;
										String ls_idOficinaDestino;

										BigDecimal lbd_versionOrigen;
										BigDecimal lbd_versionDestino;

										ls_idOficinaOrigen      = lcr_circuloRegistralOrigen.getIdOficinaOrigen();
										ls_idOficinaDestino     = lcr_circuloRegistralDestino.getIdOficinaOrigen();

										lbd_versionOrigen      = lcr_circuloRegistralOrigen.getVersion();
										lbd_versionDestino     = lcr_circuloRegistralDestino.getVersion();

										if(!StringUtils.isValidString(ls_idOficinaOrigen))
											ls_idOficinaOrigen = new String();

										if(!StringUtils.isValidString(ls_idOficinaDestino))
											ls_idOficinaDestino = new String();

										String ls_nombreOficinaOrigen;
										String ls_direccionOficinaOrigen;

										OficinaOrigen loo_oficinaOrigen;
										OficinaOrigen loo_oficinaDestino;

										ls_nombreOficinaOrigen        = null;
										ls_direccionOficinaOrigen     = null;

										loo_oficinaOrigen = new OficinaOrigen();
										loo_oficinaOrigen.setIdOficinaOrigen(ls_idOficinaOrigen);
										loo_oficinaOrigen.setVersion(lbd_versionOrigen);

										loo_oficinaDestino = new OficinaOrigen();
										loo_oficinaDestino.setIdOficinaOrigen(ls_idOficinaDestino);
										loo_oficinaDestino.setVersion(lbd_versionDestino);

										{
											OficinaOrigenDAO lood_DAO;

											lood_DAO     = DaoCreator.getOficinaOrigenDAO(ldm_manager);

											loo_oficinaOrigen      = lood_DAO.findById(loo_oficinaOrigen);
											loo_oficinaDestino     = lood_DAO.findById(loo_oficinaDestino);
										}

										if(loo_oficinaOrigen == null)
											loo_oficinaOrigen = new OficinaOrigen();

										if(loo_oficinaDestino == null)
											loo_oficinaDestino = new OficinaOrigen();

										Pais lp_pais;

										lp_pais = new Pais();
										lp_pais.setIdPais(loo_oficinaOrigen.getIdPais());

										lp_pais = DaoCreator.getPaisDAO(ldm_manager).findById(lp_pais);

										if(lp_pais == null)
											lp_pais = new Pais();

										Pais lp_paisDestino;

										lp_paisDestino = new Pais();
										lp_paisDestino.setIdPais(loo_oficinaDestino.getIdPais());

										lp_paisDestino = DaoCreator.getPaisDAO(ldm_manager).findById(lp_paisDestino);

										if(lp_paisDestino == null)
											lp_paisDestino = new Pais();

										Departamento ld_departamento;

										ld_departamento = new Departamento();
										ld_departamento.setIdPais(lp_pais.getIdPais());
										ld_departamento.setIdDepartamento(loo_oficinaOrigen.getIdDepartamento());

										ld_departamento = DaoCreator.getDepartamentoDAO(ldm_manager)
												                        .findById(ld_departamento);

										if(ld_departamento == null)
											ld_departamento = new Departamento();

										Departamento ld_departamentoDestino;

										ld_departamentoDestino = new Departamento();
										ld_departamentoDestino.setIdPais(lp_pais.getIdPais());
										ld_departamentoDestino.setIdDepartamento(
										    loo_oficinaDestino.getIdDepartamento()
										);

										ld_departamentoDestino = DaoCreator.getDepartamentoDAO(ldm_manager)
												                               .findById(ld_departamentoDestino);

										if(ld_departamentoDestino == null)
											ld_departamentoDestino = new Departamento();

										Municipio lm_municipio;

										lm_municipio = new Municipio();
										lm_municipio.setIdMunicipio(loo_oficinaOrigen.getIdMunicipio());
										lm_municipio.setIdDepartamento(ld_departamento.getIdDepartamento());
										lm_municipio.setIdPais(lp_pais.getIdPais());

										lm_municipio = DaoCreator.getMunicipioDAO(ldm_manager).findById(lm_municipio);

										if(lm_municipio == null)
											lm_municipio = new Municipio();

										Municipio lm_municipioDestino;

										lm_municipioDestino = new Municipio();
										lm_municipioDestino.setIdMunicipio(loo_oficinaDestino.getIdMunicipio());
										lm_municipioDestino.setIdDepartamento(
										    ld_departamentoDestino.getIdDepartamento()
										);
										lm_municipioDestino.setIdPais(lp_pais.getIdPais());

										lm_municipioDestino = DaoCreator.getMunicipioDAO(ldm_manager)
												                            .findById(lm_municipioDestino);

										if(lm_municipioDestino == null)
											lm_municipioDestino = new Municipio();

										if(ls_plantilla.contains("<TAG_DIRECCION_OFICINA_ORIGEN>"))
										{
											ls_direccionOficinaOrigen = loo_oficinaOrigen.getDireccion();

											if(StringUtils.isValidString(ls_direccionOficinaOrigen))
												ls_plantilla = ls_plantilla.replace(
													    "<TAG_DIRECCION_OFICINA_ORIGEN>", ls_direccionOficinaOrigen
													);
											else
												ls_plantilla = ls_plantilla.replace(
													    "<TAG_DIRECCION_OFICINA_ORIGEN>",
													    ConstanteCommon.SIN_INFORMACION
													);
										}

										if(ls_plantilla.contains("<TAG_OFICINA_ORIGEN>"))
										{
											ls_nombreOficinaOrigen = loo_oficinaOrigen.getNombre();

											if(StringUtils.isValidString(ls_nombreOficinaOrigen))
												ls_plantilla = ls_plantilla.replace(
													    "<TAG_OFICINA_ORIGEN>", ls_nombreOficinaOrigen
													);
											else
												ls_plantilla = ls_plantilla.replace(
													    "<TAG_OFICINA_ORIGEN>", ConstanteCommon.SIN_INFORMACION
													);
										}

										if(ls_plantilla.contains("<DEPARTAMENTO_MUN_ORIGEN>"))
										{
											String ls_nombreMunicipio;
											ls_nombreMunicipio = lm_municipio.getNombre();

											if(StringUtils.isValidString(ls_nombreMunicipio))
												ls_plantilla = ls_plantilla.replace(
													    "<DEPARTAMENTO_MUN_ORIGEN>", ls_nombreMunicipio
													);
											else
												ls_plantilla = ls_plantilla.replace(
													    "<DEPARTAMENTO_MUN_ORIGEN>", ConstanteCommon.SIN_INFORMACION
													);
										}

										if(ls_plantilla.contains("<TAG_MUN_OFI_DESTINO>"))
										{
											String ls_nombreMunicipioDestino;
											ls_nombreMunicipioDestino = lm_municipioDestino.getNombre();

											if(StringUtils.isValidString(ls_nombreMunicipioDestino))
												ls_plantilla = ls_plantilla.replace(
													    "<TAG_MUN_OFI_DESTINO>", ls_nombreMunicipioDestino
													);
											else
												ls_plantilla = ls_plantilla.replace(
													    "<TAG_MUN_OFI_DESTINO>", ConstanteCommon.SIN_INFORMACION
													);
										}

										if(ls_plantilla.contains("<DEPARTAMENTO_OFI_ORIGEN>"))
										{
											String ls_nombreDepartamento;
											ls_nombreDepartamento = ld_departamento.getNombre();

											if(StringUtils.isValidString(ls_nombreDepartamento))
												ls_plantilla = ls_plantilla.replace(
													    "<DEPARTAMENTO_OFI_ORIGEN>", ls_nombreDepartamento
													);
											else
												ls_plantilla = ls_plantilla.replace(
													    "<DEPARTAMENTO_OFI_ORIGEN>", ConstanteCommon.SIN_INFORMACION
													);
										}
									}

									if(loth_detalle != null)
									{
										ls_turno          = loth_detalle.getIdTurno();
										loth_dataFile     = DaoCreator.getNotaDevolutivaDAO(ldm_manager)
												                          .dataNotaDevolutiva(ls_turno);

										if(loth_dataFile != null)
										{
											if(CollectionUtils.isValidCollection(lctc_causales))
											{
												int li_numerador;
												li_numerador = 1;

												for(TipoCausal lotc_otc : lctc_causales)
												{
													if(lotc_otc.isSeleccionado())
														lsb_causales.append(
														    "{\\pard \\s3\\f1\\sb200\\cf1{" + li_numerador++ + ")} "
														    + lotc_otc.getNombre() + "\\par}"
														);
												}
											}

											if(ls_plantilla.contains("<TAG_CIRCULO_REGISTRAL>"))
											{
												String ls_circulo;
												ls_circulo = loth_dataFile.getNombreCirculo();

												if(StringUtils.isValidString(ls_circulo))
													ls_plantilla = ls_plantilla.replace(
														    "<TAG_CIRCULO_REGISTRAL>", ls_circulo
														);
											}

											if(ls_plantilla.contains("<TAG_FECHA>"))
											{
												if(StringUtils.isValidString(ls_fecha))
													ls_plantilla = ls_plantilla.replace("<TAG_FECHA>", ls_fecha);
											}

											if(ls_plantilla.contains("<TAG_HORA>"))
											{
												if(StringUtils.isValidString(ls_hora))
													ls_plantilla = ls_plantilla.replace("<TAG_HORA>", ls_hora);
											}

											if(ls_plantilla.contains("<TAG_DIAS>"))
											{
												int li_dia;

												li_dia = lc_fecha.get(Calendar.DAY_OF_MONTH);

												if(NumericUtils.isValidInteger(NumericUtils.getInteger(li_dia)))
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

											if(ls_plantilla.contains("<TAG_ANIO>"))
											{
												int li_anio;

												li_anio = lc_fecha.get(Calendar.YEAR);

												if(NumericUtils.isValidInteger(NumericUtils.getInteger(li_anio)))
													ls_plantilla = ls_plantilla.replace(
														    "<TAG_ANIO>", StringUtils.getString(li_anio)
														);
											}

											if(ls_plantilla.contains("<ID_PROCESO>"))
											{
												String ls_proc;
												ls_proc = lp_proc.getNombre();

												if(StringUtils.isValidString(ls_proc))
													ls_plantilla = ls_plantilla.replace("<ID_PROCESO>", ls_proc);
											}

											if(ls_plantilla.contains("<ID_SUBPROCESO>"))
											{
												String ls_subProc;
												ls_subProc = lsp_subp.getNombre();

												if(StringUtils.isValidString(ls_subProc))
													ls_plantilla = ls_plantilla.replace("<ID_SUBPROCESO>", ls_subProc);
											}

											if(ls_plantilla.contains("<TAG_USUARIO>"))
											{
												if(StringUtils.isValidString(as_idUsuario))
													ls_plantilla = ls_plantilla.replace("<TAG_USUARIO>", as_idUsuario);
											}

											if(ls_plantilla.contains("<TAG_ID_DOCUMENTO>"))
											{
												String ls_tipoArchivo;
												ls_tipoArchivo = loth_dataFile.getNombreTipoDoc();

												if(StringUtils.isValidString(ls_tipoArchivo))
													ls_plantilla = ls_plantilla.replace(
														    "<TAG_ID_DOCUMENTO>", ls_tipoArchivo
														);
											}

											if(ls_plantilla.contains("<TAG_NO_DOCUMENTO>"))
											{
												Long ls_numDoc;
												ls_numDoc = loth_dataFile.getNumeroDoc();

												if(NumericUtils.isValidLong(ls_numDoc))
													ls_plantilla = ls_plantilla.replace(
														    "<TAG_NO_DOCUMENTO>", ls_numDoc.toString()
														);
											}

											if(ls_plantilla.contains("<TAG_FECHA_DOCUMENTO>"))
											{
												Date ld_fechaDocumento;
												ld_fechaDocumento = loth_dataFile.getFechaDocumento();

												if(ld_fechaDocumento != null)
												{
													ls_fecha     = lsf_dateFormat.format(ld_fechaDocumento);

													ls_plantilla = ls_plantilla.replace(
														    "<TAG_FECHA_DOCUMENTO>", ls_fecha
														);
												}
											}

											if(ls_plantilla.contains("<TAG_ENTIDAD_DOCUMENTO>"))
											{
												String ls_oficinaOrigen;
												ls_oficinaOrigen = loth_dataFile.getOficinaOrigen();

												if(StringUtils.isValidString(ls_oficinaOrigen))
													ls_plantilla = ls_plantilla.replace(
														    "<TAG_ENTIDAD_DOCUMENTO>", ls_oficinaOrigen
														);
											}

											if(ls_plantilla.contains("<TAG_ID_NIR>"))
											{
												String ls_nir;
												ls_nir = loth_dataFile.getNir();

												if(StringUtils.isValidString(ls_nir))
													ls_plantilla = ls_plantilla.replace("<TAG_ID_NIR>", ls_nir);
											}

											if(ls_plantilla.contains("<TAG_TURNO>"))
												ls_plantilla = ls_plantilla.replace("<TAG_TURNO>", ls_turno);

											if(ls_plantilla.contains("<TAG_MATRICULA>"))
											{
												Collection<TurnoHistoria> loth_th;
												StringBuilder             lsb_matriculas;
												String                    ls_matriculas;
												lsb_matriculas     = new StringBuilder();
												loth_th            = lothd_thd.findMatriculas(ll_turnoHist);

												if(CollectionUtils.isValidCollection(loth_th))
												{
													for(TurnoHistoria loth_tmp : loth_th)
														lsb_matriculas = lsb_matriculas.append(
															    loth_tmp.getMotivo() + ","
															);

													ls_matriculas = lsb_matriculas.toString();

													if(StringUtils.isValidString(ls_matriculas))
														ls_plantilla = ls_plantilla.replace(
															    "<TAG_MATRICULA>", ls_matriculas
															);
												}
												else
													ls_plantilla = ls_plantilla.replace(
														    "<TAG_MATRICULA>", ConstanteCommon.SIN_INFORMACION
														);
											}

											if(ls_plantilla.contains("<TURNO_DERIVADO>"))
											{
												ls_turnoDerivado = lothd_thd.findTurnoDerivado(ls_turno);

												if(StringUtils.isValidString(ls_turnoDerivado))
													ls_plantilla = ls_plantilla.replace(
														    "<TURNO_DERIVADO>", ls_turnoDerivado
														);
												else
													ls_plantilla = ls_plantilla.replace("<TURNO_DERIVADO>", " ");
											}

											if(ls_plantilla.contains("<TAG_CAUSALES>"))
											{
												if(lsb_causales != null)
													ls_plantilla = ls_plantilla.replace(
														    "<TAG_CAUSALES>", lsb_causales.toString()
														);
											}

											if(ls_plantilla.contains("<TAG_OBSERVACIONES>"))
											{
												if(StringUtils.isValidString(ls_observaciones))
													ls_plantilla = ls_plantilla.replace(
														    "<TAG_OBSERVACIONES>", ls_observaciones
														);
												else
													ls_plantilla = ls_plantilla.replace("<TAG_OBSERVACIONES>", "");
											}

											if(ls_plantilla.contains("<TAG_ID_DEPARTAMENTO_PAIS>"))
											{
												/**
												 * Falta resolver tag
												 */
											}

											{
												Constantes lc_usuarioFirma;
												String     ls_tagUsuarioFirma;
												int        li_incrX = 0;
												int        li_incrY = 0;

												lc_usuarioFirma     = new Constantes();
												ls_tagUsuarioFirma  = "<TAG_USUARIO_FIRMA_SUSPENSION>";

												lc_usuarioFirma.setIdConstante(
												    ConstanteCommon.USUARIO_FIRMA_SUSPENSION
												);

												lc_usuarioFirma     = DaoCreator.getConstantesDAO(ldm_manager)
														                            .findByIdWithImage(lc_usuarioFirma);

												ls_plantilla     = getFirmaMasivaBusiness()
														                   .reemplazarUsuarioFirmaCargo(
														    ls_plantilla, lc_usuarioFirma, ls_tagUsuarioFirma,
														    "<TAG_CARGO_FIRMA_SUSPENSION>"
														);

												lmss_datos             = finalizarPlantilla(
													    ls_plantilla, ll_turnoHist, ldm_manager
													);
												ls_plantilla           = lmss_datos.get(
													    IdentificadoresCommon.PLANTILLA
													);
												lba_notaDevolutiva     = new PDFGenerator().convertirRTFToPDF(
													    ls_plantilla.getBytes(), ldm_manager
													);

												if(lba_notaDevolutiva == null)
													throw new B2BException(
													    ErrorKeys.ERROR_GENERANDO_ARCHIVO_RECHAZO_SOLICITUD
													);

												if(ab_firmaMasiva)
												{
													byte[] lba_grafo;

													lba_grafo = null;

													if(lc_usuarioFirma != null)
													{
														lba_grafo     = lc_usuarioFirma.getImagenes();
														li_incrX      = NumericUtils.getInt(
															    lc_usuarioFirma.getEntero()
															);
														li_incrY      = NumericUtils.getInt(lc_usuarioFirma.getReal());
													}

													lba_notaDevolutiva = getFirmaMasivaBusiness()
															                     .reemplazarBookmarsFirma(
															    lba_notaDevolutiva, lba_grafo, li_incrX, li_incrY
															);
												}
											}

											{
												CausalDevolucion             lcd_causalTemp;
												CausalDevolucionDAO          lcdDAO_lcd;
												Collection<CausalDevolucion> lccd_causalesDevolucion;

												lcd_causalTemp     = new CausalDevolucion();
												lcdDAO_lcd         = DaoCreator.getCausalDevolucionDAO(ldm_manager);

												lcd_causalTemp.setIdTurno(ls_turno);
												lcd_causalTemp.setIdTurnoHistoria(NumericUtils.getLong(ll_turnoHist));

												lccd_causalesDevolucion = lcdDAO_lcd.findByTurnoTH(lcd_causalTemp);

												if(CollectionUtils.isValidCollection(lccd_causalesDevolucion))
												{
													for(CausalDevolucion datos : lccd_causalesDevolucion)
														lcdDAO_lcd.DeleteById(datos);
												}

												if(CollectionUtils.isValidCollection(lltc_causalesParaGuardar))
												{
													for(TipoCausal ltc_tipos : lltc_causalesParaGuardar)
													{
														int li_secuenciaCausal;

														li_secuenciaCausal = lcdDAO_lcd.findSecuenceCausalDevolucion();

														if(li_secuenciaCausal <= 0)
															throw new B2BException(ErrorKeys.SIN_SECUENCIA_PERSONA);

														if(ltc_tipos == null)
															throw new B2BException(
															    ErrorKeys.NO_SE_GENERO_CERTIFICADO_AMPLIACION
															);

														lcd_causalTemp.setIdCausalDevolucion(
														    StringUtils.getString(li_secuenciaCausal)
														);
														lcd_causalTemp.setIdCausal(ltc_tipos.getIdTipoCausal());
														lcd_causalTemp.setVersion(ltc_tipos.getVersion());
														lcd_causalTemp.setIdUsuarioCreacion(as_idUsuario);
														lcd_causalTemp.setIpCreacion(as_remoteIp);

														lcdDAO_lcd.insert(lcd_causalTemp);
													}
												}
											}

											{
												Imagenes li_imagen;
												long     ll_idImagen;

												li_imagen = new Imagenes();

												li_imagen.setImagenBlob(lba_notaDevolutiva);
												li_imagen.setTipoArchivo(ExtensionCommon.PDF_MAYUS);
												li_imagen.setTamano(
												    NumericUtils.getLongWrapper(lba_notaDevolutiva.length)
												);
												li_imagen.setIdUsuarioCreacion(as_idUsuario);
												li_imagen.setIpCreacion(as_remoteIp);
												li_imagen.setCodigoVerificacion(
												    lmss_datos.get(IdentificadoresCommon.CODIGO_VERIFICACION)
												);

												ll_idImagen = DaoCreator.getImagenesDAO(ldm_manager)
														                    .insertOrUpdate(li_imagen, true);

												if(ll_idImagen > 0)
												{
													DocumentosSalida    lds_documentoInsert;
													DocumentosSalidaDAO lds_DAO;
													String              ls_tipo;

													lds_documentoInsert     = new DocumentosSalida();
													lds_DAO                 = DaoCreator.getDocumentosSalidaDAO(
														    ldm_manager
														);
													ls_tipo                 = lb_motivo
														? TipoArchivoCommon.NOTA_DEVOLUTIVA
														: TipoArchivoCommon.NOTA_RECHAZO;

													{
														Collection<DocumentosSalida> lcds_documento;
														DocumentosSalida             lds_documento;

														lds_documento = new DocumentosSalida();

														lds_documento.setIdTurnoHistoria(
														    NumericUtils.getInteger(as_turnoH)
														);
														lds_documento.setTipo(ls_tipo);

														lcds_documento = lds_DAO.findByIdTurnoHistoriaTipo(
															    lds_documento
															);

														if(CollectionUtils.isValidCollection(lcds_documento))
														{
															for(DocumentosSalida lds_iterador : lcds_documento)
															{
																if(lds_iterador != null)
																	lds_documento = lds_iterador;
															}

															lds_DAO.deleteByIdImagen(lds_documento);
														}
													}

													lds_documentoInsert.setIdTurnoHistoria(
													    NumericUtils.getInteger(ll_turnoHist)
													);
													lds_documentoInsert.setIdSolicitud(loth_detalle.getIdSolicitud());
													lds_documentoInsert.setIdTurno(ls_turno);
													lds_documentoInsert.setIdImagen(
													    NumericUtils.getLongWrapper(ll_idImagen)
													);
													lds_documentoInsert.setTipo(ls_tipo);

													if(ls_tipo.equalsIgnoreCase(TipoArchivoCommon.NOTA_DEVOLUTIVA))
														lds_documentoInsert.setIdTipoDocumental(
														    TipoDocumentalCommon.NOTA_DEVOLUTIVA
														);

													/* Falta definir tipo documental para RECHAZO_SOLICITUD
													 else if(ls_tipo.equalsIgnoreCase(TipoArchivoCommon.NOTA_RECHAZO))
													    lds_documentoInsert.setIdTipoDocumental(TipoDocumentalCommon.);*/
													lds_documentoInsert.setRepositorio(
													    ab_firmaMasiva ? RepositorioCommon.FINAL
													                   : RepositorioCommon.TEMPORAL
													);

													lds_documentoInsert.setEstado(EstadoCommon.ACTIVO);
													lds_documentoInsert.setIdUsuarioCreacion(as_idUsuario);
													lds_documentoInsert.setIpCreacion(as_remoteIp);

													lds_DAO.insertOrUpdate(lds_documentoInsert, true);
												}
												else
													throw new B2BException(ErrorKeys.SIN_SECUENCIA_IMAGENES);
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

					clh_LOGGER.error("generarArchivoRechazo", lb2be_e);

					throw lb2be_e;
				}
				finally
				{
					ldm_manager.close();
				}
			}
		}

		if((li_validatorRechazo == 1) && (li_validatorDevolucion == 1))
			return null;

		return lba_notaDevolutiva;
	}

	/**
	 * Método encargado de generar archivo en formato pdf  utilizando  las plantillas  PLANTILLA_CREACION_ANTIGUO_SISTEMA.
	 *
	 * @param asm_parametros Objeto de tipo SolicitudMatricula que contiene los datos necesarios para buscar la información a incluir en la plantilla.
	 * @param ab_firmaMasiva Variable de tipo boolean , si es true inserta firma masiva en la plantilla de lo contrario no lo hace.
	 * @param adm_manager Objeto de tipo DAOManager utilizado para crear la conexión hacia la base de datos
	 * @param as_usuario Variable de tipo String que contiene el usuario que realiza la acción.
	 * @param as_ipRemote Variable de tipo String que contiene la ip desde la cual se realiza la acción.
	 * @return Arreglo de bytes que contiene la plantilla generada.
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see byte[]
	 */
	public synchronized byte[] generarConsultaCreacionGrabacionMatricula(
	    SolicitudMatricula asm_parametros, boolean ab_firmaMasiva, DAOManager adm_manager, String as_usuario,
	    String as_ipRemote
	)
	    throws B2BException
	{
		byte[] lba_archivo;
		lba_archivo = null;

		try
		{
			if(asm_parametros != null)
			{
				String     ls_constante;
				Constantes lc_constante;
				String     ls_sinInformacion;

				byte[] lba_plantilla;

				ls_constante     = ConstanteCommon.PLANTILLA_CREACION_ANTIGUO_SISTEMA;

				ls_sinInformacion     = "SIN INFORMACIÓN";

				lc_constante = new Constantes();
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
					String              ls_plantilla;
					String              ls_idCirculo;
					String              ls_tag;
					long                ll_idMatricula;
					TurnoHistoria       lth_turnoHistoria;

					lmss_datos            = null;
					ls_plantilla          = new String(lba_plantilla);
					ls_idCirculo          = asm_parametros.getIdCirculo();
					ls_tag                = null;
					ll_idMatricula        = asm_parametros.getIdMatricula();
					lth_turnoHistoria     = asm_parametros.getTurnoHistoria();

					if(StringUtils.isValidString(ls_idCirculo) && (ll_idMatricula > 0))
					{
						CirculoRegistral lcr_circuloRegistral;

						lcr_circuloRegistral = new CirculoRegistral();
						lcr_circuloRegistral.setIdCirculo(ls_idCirculo);

						lcr_circuloRegistral = DaoCreator.getCirculoRegistralDAO(adm_manager)
								                             .findById(lcr_circuloRegistral);

						if(lcr_circuloRegistral != null)
						{
							String ls_tipoOficina;
							ls_tipoOficina = lcr_circuloRegistral.getTipoOficina();

							if(StringUtils.isValidString(ls_tipoOficina))
							{
								if(ls_tipoOficina.equalsIgnoreCase(EstadoCommon.P))
									ls_tipoOficina = "PRINCIPAL";
								else if(ls_tipoOficina.equalsIgnoreCase(EstadoCommon.S))
									ls_tipoOficina = "SECCIONAL";

								ls_tag = "<TAG_PRINCIPAL_SECCIONAL>";

								if(ls_plantilla.contains(ls_tag))
									ls_plantilla = ls_plantilla.replace(ls_tag, ls_tipoOficina);
							}

							ls_tag = "<TAG_NOMBRE_CIRCULO_REGISTRAL>";

							if(ls_plantilla.contains(ls_tag))
							{
								String ls_destinatario;
								ls_destinatario = lcr_circuloRegistral.getNombre();

								if(StringUtils.isValidString(ls_destinatario))
									ls_plantilla = ls_plantilla.replace(ls_tag, ls_destinatario);
							}
						}

						AccPredioRegistro lapr_predioTemp;
						String            ls_idDatosAntSistema;

						lapr_predioTemp          = new AccPredioRegistro();
						ls_idDatosAntSistema     = null;

						lapr_predioTemp.setIdCirculo(ls_idCirculo);
						lapr_predioTemp.setIdMatricula(NumericUtils.getLongWrapper(ll_idMatricula));

						lapr_predioTemp = DaoCreator.getAccPredioRegistroDAO(adm_manager)
								                        .findByCirculoMatricula(lapr_predioTemp);

						if(lapr_predioTemp != null)
							ls_idDatosAntSistema = lapr_predioTemp.getIdDatosAntSistema();

						{
							if(StringUtils.isValidString(ls_idDatosAntSistema))
							{
								{
									ls_tag = "<TAG_DATOS_ANTIGUO_SISTEMA>";

									String ls_infoDatosAnt;

									ls_infoDatosAnt = escribirDatosAntSistema(adm_manager, ls_idDatosAntSistema);

									if(ls_plantilla.contains(ls_tag) && StringUtils.isValidString(ls_infoDatosAnt))
										ls_plantilla = ls_plantilla.replace(ls_tag, ls_infoDatosAnt);
								}

								ls_tag = "<TAG_DETALLE_ANTIGUO_SISTEMA>";

								if(ls_plantilla.contains(ls_tag))
								{
									String ls_infoDetalleAnt;

									ls_infoDetalleAnt = escribirDetalleAntSistema(adm_manager, ls_idDatosAntSistema);

									if(StringUtils.isValidString(ls_infoDetalleAnt))
										ls_plantilla = ls_plantilla.replace(ls_tag, ls_infoDetalleAnt);
									else
										ls_plantilla = ls_plantilla.replace(ls_tag, " ");
								}
							}
						}

						if(lth_turnoHistoria != null)
						{
							Solicitud lso_solicitud;
							lso_solicitud = new Solicitud();

							lso_solicitud.setIdSolicitud(lth_turnoHistoria.getIdSolicitud());

							lso_solicitud = DaoCreator.getSolicitudDAO(adm_manager).findById(lso_solicitud);

							if(lso_solicitud != null)
							{
								ls_tag = "<ID_NIR>";

								if(ls_plantilla.contains(ls_tag))
									ls_plantilla = ls_plantilla.replace(ls_tag, lso_solicitud.getNir());
							}
						}

						ls_tag = "<ID_CIRCULO_REGISTRAL>";

						if(ls_plantilla.contains(ls_tag))
							ls_plantilla = ls_plantilla.replace(ls_tag, ls_idCirculo);

						ls_tag = "<ID_MATRICULA>";

						if(ls_plantilla.contains(ls_tag))
							ls_plantilla = ls_plantilla.replace(ls_tag, StringUtils.getString(ll_idMatricula));

						ls_tag = "<TAG_FECHA_HORA>";

						if(ls_plantilla.contains(ls_tag))
						{
							Date ld_fechaActual;
							ld_fechaActual     = new Date();

							ls_plantilla = ls_plantilla.replace(
								    ls_tag, StringUtils.getString(ld_fechaActual, "dd/MM/yyyy HH:mm")
								);
						}

						{
							AccPredioRegistro lpr_predioRegistro;
							lpr_predioRegistro = new AccPredioRegistro();

							lpr_predioRegistro.setIdCirculo(ls_idCirculo);
							lpr_predioRegistro.setIdMatricula(NumericUtils.getLongWrapper(ll_idMatricula));

							lpr_predioRegistro = DaoCreator.getAccPredioRegistroDAO(adm_manager)
									                           .findByCirculoMatricula(lpr_predioRegistro);

							if(lpr_predioRegistro != null)
							{
								ZonaRegistral lzr_zonaRegistral;
								lzr_zonaRegistral = new ZonaRegistral();

								lzr_zonaRegistral.setIdZonaRegistral(lpr_predioRegistro.getIdZonaRegistral());

								lzr_zonaRegistral = DaoCreator.getZonaRegistralDAO(adm_manager)
										                          .findById(lzr_zonaRegistral);

								if(lzr_zonaRegistral != null)
								{
									String ls_pais;
									String ls_departamento;
									String ls_municipio;
									String ls_vereda;

									ls_pais             = lzr_zonaRegistral.getIdPais();
									ls_departamento     = lzr_zonaRegistral.getIdDepartamento();
									ls_municipio        = lzr_zonaRegistral.getIdMunicipio();
									ls_vereda           = lzr_zonaRegistral.getIdVereda();

									if(
									    StringUtils.isValidString(ls_pais)
										    && StringUtils.isValidString(ls_departamento)
										    && StringUtils.isValidString(ls_municipio)
										    && StringUtils.isValidString(ls_vereda)
									)
									{
										Departamento ld_departamento;
										Municipio    lm_municipio;
										Vereda       lv_vereda;

										ld_departamento     = new Departamento();
										lm_municipio        = new Municipio();
										lv_vereda           = new Vereda();

										ld_departamento.setIdPais(ls_pais);
										ld_departamento.setIdDepartamento(ls_departamento);

										ld_departamento = DaoCreator.getDepartamentoDAO(adm_manager)
												                        .findById(ld_departamento);

										if(ld_departamento != null)
										{
											String ls_nombreDepartamento;
											ls_nombreDepartamento     = ld_departamento.getNombre();
											ls_tag                    = "<TAG_DEPTO>";

											if(StringUtils.isValidString(ls_nombreDepartamento))
												ls_plantilla = ls_plantilla.replace(ls_tag, ls_nombreDepartamento);
										}

										lm_municipio.setIdPais(ls_pais);
										lm_municipio.setIdDepartamento(ls_departamento);
										lm_municipio.setIdMunicipio(ls_municipio);

										lm_municipio = DaoCreator.getMunicipioDAO(adm_manager).findById(lm_municipio);

										if(lm_municipio != null)
										{
											String ls_nombreMun;
											ls_nombreMun     = lm_municipio.getNombre();
											ls_tag           = "<TAG_MUNICIPIO>";

											if(StringUtils.isValidString(ls_nombreMun))
												ls_plantilla = ls_plantilla.replace(ls_tag, ls_nombreMun);
										}

										lv_vereda.setIdPais(ls_pais);
										lv_vereda.setIdDepartamento(ls_departamento);
										lv_vereda.setIdMunicipio(ls_municipio);
										lv_vereda.setIdVereda(ls_vereda);

										lv_vereda = DaoCreator.getVeredaDAO(adm_manager).findById(lv_vereda);

										if(lv_vereda != null)
										{
											String ls_nombreVereda;
											ls_nombreVereda     = lv_vereda.getNombre();
											ls_tag              = "<TAG_VEREDA>";

											if(StringUtils.isValidString(ls_nombreVereda))
												ls_plantilla = ls_plantilla.replace(ls_tag, ls_nombreVereda);
										}
									}
								}

								ls_tag = "<TAG_FECHA_APERTURA_FOLIO>";

								if(ls_plantilla.contains(ls_tag))
								{
									Date ld_fechaApertura;
									ld_fechaApertura = lpr_predioRegistro.getFechaApertura();

									if(ld_fechaApertura != null)
										ls_plantilla = ls_plantilla.replace(
											    ls_tag, StringUtils.getString(ld_fechaApertura, "dd/MM/yyyy HH:mm")
											);
								}

								ls_tag = "<TAG_RADICADO>";

								if(ls_plantilla.contains(ls_tag))
								{
									String ls_radicacion;
									ls_radicacion = lpr_predioRegistro.getRadicacion();

									if(StringUtils.isValidString(ls_radicacion))
										ls_plantilla = ls_plantilla.replace(ls_tag, ls_radicacion);
								}

								ls_tag = "<TAG_CHIP>";

								if(ls_plantilla.contains(ls_tag))
								{
									String ls_tmp;
									ls_tmp = lpr_predioRegistro.getNumeroPredialAnt();

									if(StringUtils.isValidString(ls_tmp))
										ls_plantilla = ls_plantilla.replace(ls_tag, ls_tmp);
								}

								ls_tag = "<TAG_NUPRE>";

								if(ls_plantilla.contains(ls_tag))
								{
									String ls_tmp;
									ls_tmp = lpr_predioRegistro.getNupre();

									if(StringUtils.isValidString(ls_tmp))
										ls_plantilla = ls_plantilla.replace(ls_tag, ls_tmp);
								}

								ls_tag = "<TAG_USO>";

								if(ls_plantilla.contains(ls_tag))
								{
									String ls_tmp;
									ls_tmp = lpr_predioRegistro.getIdTipoUsoSuelo();

									if(StringUtils.isValidString(ls_tmp))
									{
										TipoUsoSuelo ltus_tipoUsoSuelo;
										ltus_tipoUsoSuelo = new TipoUsoSuelo();

										ltus_tipoUsoSuelo.setIdTipoUsoSuelo(ls_tmp);

										ltus_tipoUsoSuelo = DaoCreator.getTipoUsoSueloDAO(adm_manager)
												                          .findById(ltus_tipoUsoSuelo);

										if(ltus_tipoUsoSuelo != null)
											ls_plantilla = ls_plantilla.replace(
												    ls_tag, ltus_tipoUsoSuelo.getDescription()
												);
									}
								}

								{
									String ls_idDocumento;
									ls_idDocumento = lpr_predioRegistro.getIdDocumento();

									if(StringUtils.isValidString(ls_idDocumento))
									{
										Documento ld_documento;
										ld_documento = new Documento();

										ld_documento.setIdDocumento(ls_idDocumento);

										ld_documento = DaoCreator.getDocumentoDAO(adm_manager).findById(ld_documento);

										if(ld_documento != null)
										{
											ls_tag = "<TAG_ID_TIPO_DOCUMENTO>";

											if(ls_plantilla.contains(ls_tag))
											{
												String ls_tipoDocumento;
												ls_tipoDocumento = ld_documento.getIdTipoDocumento();

												if(StringUtils.isValidString(ls_tipoDocumento))
												{
													TipoDocumentoPublico ltdp_tipoDocPublico;
													ltdp_tipoDocPublico = new TipoDocumentoPublico();

													ltdp_tipoDocPublico.setIdTipoDocumento(
													    ld_documento.getIdTipoDocumento()
													);

													ltdp_tipoDocPublico = DaoCreator.getTipoDocumentoPublicoDAO(
														    adm_manager
														).findById(ltdp_tipoDocPublico);

													if(ltdp_tipoDocPublico != null)
													{
														String ls_nombre;
														ls_nombre = ltdp_tipoDocPublico.getNombre();

														if(StringUtils.isValidString(ls_nombre))
															ls_plantilla = ls_plantilla.replace(ls_tag, ls_nombre);
													}
												}
												else
													ls_plantilla = ls_plantilla.replace(ls_tag, ls_sinInformacion);
											}

											ls_tag = "<TAG_NUMERO_DOCUMENTO>";

											if(ls_plantilla.contains(ls_tag))
											{
												String ls_documento;
												ls_documento = ld_documento.getNumero();

												if(StringUtils.isValidString(ls_documento))
													ls_plantilla = ls_plantilla.replace(ls_tag, ls_documento);
												else
													ls_plantilla = ls_plantilla.replace(ls_tag, ls_sinInformacion);
											}

											ls_tag = "<TAG_FECHA_DOCUMENTO>";

											if(ls_plantilla.contains(ls_tag))
											{
												String ls_fechaDocumento;
												ls_fechaDocumento = StringUtils.getString(
													    ld_documento.getFechaDocumento(),
													    FormatoFechaCommon.DIA_MES_ANIO
													);

												if(StringUtils.isValidString(ls_fechaDocumento))
													ls_plantilla = ls_plantilla.replace(ls_tag, ls_fechaDocumento);
												else
													ls_plantilla = ls_plantilla.replace(ls_tag, ls_sinInformacion);
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
														ls_plantilla = ls_plantilla.replace(
															    ls_tag, ls_munOficinaOrigen
															);
												}
											}

											{
												OficinaOrigen loo_oficinaOrigen;
												loo_oficinaOrigen = new OficinaOrigen();

												loo_oficinaOrigen.setIdOficinaOrigen(ld_documento.getIdOficinaOrigen());
												loo_oficinaOrigen.setVersion(ld_documento.getVersion());

												loo_oficinaOrigen = DaoCreator.getOficinaOrigenDAO(adm_manager)
														                          .findById(loo_oficinaOrigen);

												if(loo_oficinaOrigen != null)
												{
													String ls_pais;
													String ls_departamento;
													String ls_nombreOficinaOrigen;

													ls_pais                    = loo_oficinaOrigen.getIdPais();
													ls_departamento            = loo_oficinaOrigen.getIdDepartamento();
													ls_nombreOficinaOrigen     = loo_oficinaOrigen.getNombre();

													ls_tag = "<TAG_OFICINA_ORIGEN>";

													if(
													    ls_plantilla.contains(ls_tag)
														    && StringUtils.isValidString(ls_nombreOficinaOrigen)
													)
														ls_plantilla = ls_plantilla.replace(
															    ls_tag, ls_nombreOficinaOrigen
															);

													if(
													    StringUtils.isValidString(ls_pais)
														    && StringUtils.isValidString(ls_departamento)
													)
													{
														Departamento ld_departamento;

														ld_departamento = new Departamento();

														ld_departamento.setIdPais(ls_pais);
														ld_departamento.setIdDepartamento(ls_departamento);

														ld_departamento = DaoCreator.getDepartamentoDAO(adm_manager)
																                        .findById(ld_departamento);

														if(ld_departamento != null)
														{
															String ls_depOficinaOrigen;
															ls_depOficinaOrigen     = ld_departamento.getNombre();

															ls_tag = "<TAG_DEP_OFI_ORIGEN>";

															if(
															    ls_plantilla.contains(ls_tag)
																    && StringUtils.isValidString(ls_depOficinaOrigen)
															)
																ls_plantilla = ls_plantilla.replace(
																	    ls_tag, ls_depOficinaOrigen
																	);
														}
													}
												}
											}
										}
									}
									else
									{
										ls_tag = "<TAG_ID_TIPO_DOCUMENTO>";

										if(ls_plantilla.contains(ls_tag))
											ls_plantilla = ls_plantilla.replace(ls_tag, ls_sinInformacion);

										ls_tag = "<TAG_NUMERO_DOCUMENTO>";

										if(ls_plantilla.contains(ls_tag))
											ls_plantilla = ls_plantilla.replace(ls_tag, ls_sinInformacion);

										ls_tag = "<TAG_FECHA_DOCUMENTO>";

										if(ls_plantilla.contains(ls_tag))
											ls_plantilla = ls_plantilla.replace(ls_tag, ls_sinInformacion);
									}
								}

								ls_tag = "<TAG_CODIGO_CATASTRAL>";

								if(ls_plantilla.contains(ls_tag))
								{
									String ls_codigoCatastral;
									ls_codigoCatastral = lpr_predioRegistro.getNumeroPredial();

									if(StringUtils.isValidString(ls_codigoCatastral))
										ls_plantilla = ls_plantilla.replace(ls_tag, ls_codigoCatastral);
								}

								ls_tag = "<ID_ESTADO_PREDIO>";

								if(ls_plantilla.contains(ls_tag))
								{
									String ls_idEstadoPredio;
									ls_idEstadoPredio = lpr_predioRegistro.getIdEstadoPredio();

									if(StringUtils.isValidString(ls_idEstadoPredio))
									{
										EstadoPredio lep_estadoPredio;
										lep_estadoPredio = new EstadoPredio();

										lep_estadoPredio.setIdEstadoPredio(ls_idEstadoPredio);

										lep_estadoPredio = DaoCreator.getEstadoPredioDao(adm_manager)
												                         .findById(lep_estadoPredio);

										if(lep_estadoPredio != null)
											ls_plantilla = ls_plantilla.replace(ls_tag, lep_estadoPredio.getNombre());
									}
								}

								{
									ComplementacionPredio lcp_complementacionPredio;
									lcp_complementacionPredio = new ComplementacionPredio();

									lcp_complementacionPredio.setIdComplementacion(
									    NumericUtils.isValidLong(lpr_predioRegistro.getIdComplementacion())
									    ? lpr_predioRegistro.getIdComplementacion().toString() : "0"
									);

									lcp_complementacionPredio = DaoCreator.getComplementacionPredioDAO(adm_manager)
											                                  .findById(lcp_complementacionPredio);

									if(lcp_complementacionPredio != null)
									{
										ls_tag = "<TAG_COMPLEMENTACION>";

										if(ls_plantilla.contains(ls_tag))
										{
											String ls_complementacion;
											ls_complementacion = lcp_complementacionPredio.getComplementacion();

											if(StringUtils.isValidString(ls_complementacion))
												ls_plantilla = ls_plantilla.replace(ls_tag, ls_complementacion);
										}
									}
									else
									{
										ls_tag = "<TAG_COMPLEMENTACION>";

										if(ls_plantilla.contains(ls_tag))
											ls_plantilla = ls_plantilla.replace(ls_tag, ls_sinInformacion);
									}
								}

								{
									PredioTipo lpt_predioTipo;
									lpt_predioTipo = new PredioTipo();

									lpt_predioTipo.setIdTipoPredio(lpr_predioRegistro.getIdTipoPredio());

									lpt_predioTipo = DaoCreator.getPredioTipoDao(adm_manager).findById(lpt_predioTipo);

									if(lpt_predioTipo != null)
									{
										ls_tag = "<TAG_ID_TIPO_PREDIO>";

										if(ls_plantilla.contains(ls_tag))
										{
											String ls_tipoPredio;
											ls_tipoPredio = lpt_predioTipo.getDescripcion();

											if(StringUtils.isValidString(ls_tipoPredio))
												ls_plantilla = ls_plantilla.replace(ls_tag, ls_tipoPredio);
										}
									}
								}

								{
									ls_tag = "<TAG_IMAGEN_LINDEROS>";

									if(ls_plantilla.contains(ls_tag))
									{
										String ls_codigoCatastral;
										ls_codigoCatastral = lpr_predioRegistro.getNumeroPredial();

										if(StringUtils.isValidString(ls_codigoCatastral))
										{
											ConstantesDAO lcd_DAO;
											String        ls_urlImagen;
											String        ls_urlEncontrar;
											String        ls_arcgisEndpointLocal;
											String        ls_arcgisEndpointOnline;
											String        ls_urlExportar;

											lcd_DAO     = DaoCreator.getConstantesDAO(adm_manager);

											ls_urlEncontrar             = lcd_DAO.findString(
												    ConstanteCommon.ARCGIS_ENCONTRAR_URL
												);
											ls_arcgisEndpointLocal      = lcd_DAO.findString(
												    ConstanteCommon.ARCGIS_ENDPOINT_LOCAL
												);
											ls_arcgisEndpointOnline     = lcd_DAO.findString(
												    ConstanteCommon.ARCGIS_ENDPOINT_ARCGISONLINE
												);
											ls_urlExportar              = lcd_DAO.findString(
												    ConstanteCommon.ARCGIS_EXPORTAR_MAPA_URL
												);

											ls_urlImagen = ClienteJSON.getMap(
												    ls_codigoCatastral, ls_urlEncontrar, ls_urlExportar,
												    ls_arcgisEndpointLocal, ls_arcgisEndpointOnline
												);

											if(StringUtils.isValidString(ls_urlImagen))
											{
												InputStream lis_imagen;
												lis_imagen     = new URL(ls_urlImagen).openStream();

												ls_plantilla = ls_plantilla.replace(ls_tag, ImagenPdf.test(lis_imagen));

												lis_imagen.close();
											}
											else
												ls_plantilla = ls_plantilla.replace(
													    ls_tag,
													    addMessage(ErrorKeys.ERROR_INFORMACION_CATASTRAL_MAPA, true)
													);
										}
										else
											ls_plantilla = ls_plantilla.replace(
												    ls_tag, addMessage(
												        ErrorKeys.ERROR_INFORMACION_CATASTRAL_MAPA, true
												    )
												);
									}
								}
							}
						}

						{
							AccLinderoPredio llp_linderoPredio;
							llp_linderoPredio = new AccLinderoPredio();

							llp_linderoPredio.setIdCirculo(ls_idCirculo);
							llp_linderoPredio.setIdMatricula(NumericUtils.getLongWrapper(ll_idMatricula));

							llp_linderoPredio = DaoCreator.getAccLinderoPredioDAO(adm_manager)
									                          .findByCirculoMatricula(llp_linderoPredio);

							if(llp_linderoPredio != null)
							{
								ls_tag = "<TAG_DECRIPCION_LINDEROS>";

								if(ls_plantilla.contains(ls_tag))
								{
									String ls_lindero;
									ls_lindero = llp_linderoPredio.getLindero();

									if(StringUtils.isValidString(ls_lindero))
										ls_plantilla = ls_plantilla.replace(ls_tag, ls_lindero);
								}
							}
						}

						{
							DireccionPredioAcc             ldp_direccionPredio;
							Collection<DireccionPredioAcc> lcdp_direccionPredio;

							ldp_direccionPredio = new DireccionPredioAcc();

							ldp_direccionPredio.setIdCirculo(ls_idCirculo);
							ldp_direccionPredio.setIdMatricula(NumericUtils.getLongWrapper(ll_idMatricula));

							lcdp_direccionPredio = DaoCreator.getDireccionPredioAccDAO(adm_manager)
									                             .findByIdCirculoMatricula(ldp_direccionPredio);

							if(CollectionUtils.isValidCollection(lcdp_direccionPredio))
							{
								StringBuilder lsb_direcciones;

								lsb_direcciones = new StringBuilder();

								for(DireccionPredioAcc ldp_iterador : lcdp_direccionPredio)
								{
									if(ldp_iterador != null)
									{
										lsb_direcciones.append("{\\pard ");
										lsb_direcciones.append(ldp_iterador.getIdDireccion() + ") ");
										lsb_direcciones.append(ldp_iterador.getDireccion());
										lsb_direcciones.append("\\par}");
									}
								}

								ls_tag = "<TAG_DIRECCION_PREDIO>";

								if(ls_plantilla.contains(ls_tag))
									ls_plantilla = ls_plantilla.replace(ls_tag, lsb_direcciones.toString());
							}
						}

						{
							AnotacionPredio             lap_anotacionPredio;
							Collection<AnotacionPredio> lcap_anotacionPredio;

							lap_anotacionPredio = new AnotacionPredio();

							lap_anotacionPredio.setIdCirculo(ls_idCirculo);
							lap_anotacionPredio.setIdMatricula(NumericUtils.getLongWrapper(ll_idMatricula));

							lcap_anotacionPredio = DaoCreator.getAccAnotacionPredioDAO(adm_manager)
									                             .findByCirculoMatricula(lap_anotacionPredio);

							if(CollectionUtils.isValidCollection(lcap_anotacionPredio))
							{
								StringBuilder lsb_anotaciones;
								Integer       li_idAnotacion;

								lsb_anotaciones     = new StringBuilder();
								li_idAnotacion      = null;

								for(AnotacionPredio lap_iterador : lcap_anotacionPredio)
								{
									if(lap_iterador != null)
									{
										li_idAnotacion = NumericUtils.getInteger(lap_iterador.getIdAnotacion());

										lsb_anotaciones.append("{\\pard ");
										lsb_anotaciones.append(
										    "_______________________________________________________________________________________________________________________"
										);
										lsb_anotaciones.append("\\par}");

										lsb_anotaciones.append("{\\pard \\par}");

										lsb_anotaciones.append("{\\pard ");

										lsb_anotaciones.append(
										    "{\\b ANOTACIÓN: Nro. " + String.format("%03d", li_idAnotacion)
										);
										lsb_anotaciones.append(
										    " Fecha: } "
										    + StringUtils.getString(lap_iterador.getFechaRadicacion(), "dd-MM-yyyy")
										);
										lsb_anotaciones.append(" {\\b Radicación: }" + lap_iterador.getRadicacion());
										lsb_anotaciones.append("\\par}");

										lsb_anotaciones.append("{\\pard ");

										{
											Documento ld_documento;
											ld_documento = new Documento();

											ld_documento.setIdDocumento(lap_iterador.getIdDocumento());
											ld_documento.setVersionDocumento(
											    NumericUtils.getLongWrapper(lap_iterador.getVersionDocumento())
											);

											ld_documento = DaoCreator.getDocumentoDAO(adm_manager)
													                     .findByIdDocumentoVersion(ld_documento);

											if(ld_documento != null)
											{
												TipoDocumentoPublico ltdp_tipoDocPublico;
												ltdp_tipoDocPublico = new TipoDocumentoPublico();

												ltdp_tipoDocPublico.setIdTipoDocumento(
												    ld_documento.getIdTipoDocumento()
												);

												ltdp_tipoDocPublico = DaoCreator.getTipoDocumentoPublicoDAO(
													    adm_manager
													).findById(ltdp_tipoDocPublico);

												if(ltdp_tipoDocPublico != null)
													lsb_anotaciones.append("Doc: " + ltdp_tipoDocPublico.getNombre());

												lsb_anotaciones.append(" " + ld_documento.getNumero() + " DE FECHA ");
												lsb_anotaciones.append(
												    StringUtils.getString(
												        ld_documento.getFechaDocumento(), "dd-MM-yyyy"
												    )
												);

												{
													OficinaOrigen loo_oficinaOrigen;
													loo_oficinaOrigen = new OficinaOrigen();

													loo_oficinaOrigen.setIdOficinaOrigen(
													    ld_documento.getIdOficinaOrigen()
													);
													loo_oficinaOrigen.setVersion(ld_documento.getVersion());

													loo_oficinaOrigen = DaoCreator.getOficinaOrigenDAO(adm_manager)
															                          .findById(loo_oficinaOrigen);

													if(loo_oficinaOrigen != null)
													{
														String ls_pais;
														String ls_departamento;
														String ls_municipio;

														ls_pais             = loo_oficinaOrigen.getIdPais();
														ls_departamento     = loo_oficinaOrigen.getIdDepartamento();
														ls_municipio        = loo_oficinaOrigen.getIdMunicipio();

														lsb_anotaciones.append(" DE ");

														lsb_anotaciones.append(" " + loo_oficinaOrigen.getNombre());

														if(
														    StringUtils.isValidString(ls_pais)
															    && StringUtils.isValidString(ls_departamento)
															    && StringUtils.isValidString(ls_municipio)
														)
														{
															Municipio lm_municipio;
															lm_municipio = new Municipio();

															lm_municipio.setIdPais(ls_pais);
															lm_municipio.setIdDepartamento(ls_departamento);
															lm_municipio.setIdMunicipio(ls_municipio);

															lm_municipio = DaoCreator.getMunicipioDAO(adm_manager)
																	                     .findById(lm_municipio);

															if(lm_municipio != null)
																lsb_anotaciones.append(" " + lm_municipio.getNombre());
														}
													}
													else
													{
														String ls_comentario;
														ls_comentario = ld_documento.getComentario();

														if(StringUtils.isValidString(ls_comentario))
															lsb_anotaciones.append(" " + ls_comentario);
														else
															lsb_anotaciones.append(" " + ls_sinInformacion);
													}
												}
											}
										}

										{
											BigDecimal lbd_valor;
											lbd_valor = lap_iterador.getValor();

											lsb_anotaciones.append("{\\tab VALOR ACTO: $");

											if(lbd_valor != null)
											{
												NumberFormat lnf_numbreFormat;

												lnf_numbreFormat = NumberFormat.getNumberInstance(Locale.getDefault());

												String ls_valor;
												ls_valor = lnf_numbreFormat.format(lbd_valor);

												lsb_anotaciones.append(ls_valor);
											}

											lsb_anotaciones.append("}");
										}

										lsb_anotaciones.append("\\par}");

										lsb_anotaciones.append("{\\pard ");

										{
											String ls_idDatosAntSistemaAnotacion;
											String ls_idDetalleAntSistema;

											ls_idDatosAntSistemaAnotacion     = lap_iterador.getIdDatosAntSistema();
											ls_idDetalleAntSistema            = lap_iterador.getIdDetalleAntSistema();

											if(
											    StringUtils.isValidString(ls_idDatosAntSistemaAnotacion)
												    && StringUtils.isValidString(ls_idDetalleAntSistema)
											)
											{
												DetalleAntSistema ldas_detalleAntSistema;

												ldas_detalleAntSistema = new DetalleAntSistema();

												ldas_detalleAntSistema.setIdDatosAntSistema(
												    ls_idDatosAntSistemaAnotacion
												);
												ldas_detalleAntSistema.setIdDetalleAntSistema(ls_idDetalleAntSistema);

												ldas_detalleAntSistema = DaoCreator.getDetalleAntSistemaDAO(
													    adm_manager
													).findByDetalleYDatosAntSis(ldas_detalleAntSistema);

												if(ldas_detalleAntSistema != null)
												{
													lsb_anotaciones.append("DATOS ANTIGUO SISTEMA:");

													{
														Long ll_idLibro;

														ll_idLibro = ldas_detalleAntSistema.getIdLibroAntSistema();

														if(NumericUtils.isValidLong(ll_idLibro))
														{
															LibroAntiguoSistema llas_libro;

															llas_libro = new LibroAntiguoSistema();

															llas_libro.setIdLibroAntiguoSistema(
															    NumericUtils.getLong(ll_idLibro)
															);

															llas_libro = DaoCreator.getLibroAntiguoSistemaDAO(
																    adm_manager
																).findById(llas_libro);

															if(llas_libro != null)
																lsb_anotaciones.append(
																    "\\tab LIBRO: " + llas_libro.getNombre()
																);
														}
													}

													{
														String ls_tomo;

														ls_tomo = ldas_detalleAntSistema.getTomo();

														if(StringUtils.isValidString(ls_tomo))
															lsb_anotaciones.append("\\tab TOMO: " + ls_tomo);
													}

													{
														String ls_folio;

														ls_folio = ldas_detalleAntSistema.getFolio();

														if(StringUtils.isValidString(ls_folio))
															lsb_anotaciones.append("\\tab FOLIO: " + ls_folio);
													}

													{
														String ls_partida;

														ls_partida = ldas_detalleAntSistema.getPartida();

														if(StringUtils.isValidString(ls_partida))
															lsb_anotaciones.append("\\tab PARTIDA: " + ls_partida);
													}

													{
														Long ll_partida;

														ll_partida = ldas_detalleAntSistema.getNumeroPartida();

														if(NumericUtils.isValidLong(ll_partida))
															lsb_anotaciones.append(
															    "\\tab NÚMERO DE PARTIDA: " + ll_partida
															);
													}

													{
														String ls_anio;

														ls_anio = ldas_detalleAntSistema.getAnio();

														if(StringUtils.isValidString(ls_anio))
															lsb_anotaciones.append("\\tab AÑO: " + ls_anio);
													}

													lsb_anotaciones.append("\\par}");

													lsb_anotaciones.append("{\\pard ");
												}
											}
										}

										{
											NaturalezaJuridica lnj_naturaleza;
											lnj_naturaleza = new NaturalezaJuridica();

											lnj_naturaleza.setIdNaturalezaJuridica(
											    lap_iterador.getIdNaturalezaJuridica()
											);
											lnj_naturaleza.setVersion(lap_iterador.getVersion());

											lnj_naturaleza = DaoCreator.getNaturalezaJuridicaDAO(adm_manager)
													                       .findById(lnj_naturaleza);

											if(lnj_naturaleza != null)
											{
												GrupoNaturalezaJuridica lgnj_grupoNatJur;
												lgnj_grupoNatJur = new GrupoNaturalezaJuridica();

												lgnj_grupoNatJur.setIdGrupoNatJuridica(
												    lnj_naturaleza.getIdGrupoNatJur()
												);

												lgnj_grupoNatJur = DaoCreator.getGrupoNaturalezaJuridicaDAO(
													    adm_manager
													).findById(lgnj_grupoNatJur);

												if(lgnj_grupoNatJur != null)
													lsb_anotaciones.append(
													    "ESPECIFICACIÓN: " + lgnj_grupoNatJur.getNombre() + ": "
													);
												else
													lsb_anotaciones.append(ls_sinInformacion);

												{
													String ls_idNatJuridica;
													ls_idNatJuridica = lnj_naturaleza.getIdNaturalezaJuridica();

													if(StringUtils.isValidString(ls_idNatJuridica))
														lsb_anotaciones.append(ls_idNatJuridica + " ");
												}

												{
													String ls_nombreNatJuridica;
													ls_nombreNatJuridica = lnj_naturaleza.getNombre();

													if(StringUtils.isValidString(ls_nombreNatJuridica))
														lsb_anotaciones.append(ls_nombreNatJuridica);
												}

												{
													String ls_comentario;
													ls_comentario = lap_iterador.getComentario();

													if(StringUtils.isValidString(ls_comentario))
														lsb_anotaciones.append(" " + ls_comentario);
												}
											}
										}

										lsb_anotaciones.append("\\par}");

										lsb_anotaciones.append("{\\pard ");
										lsb_anotaciones.append(
										    "{\\b PERSONAS QUE INTERVINIENEN EN EL ACTO (X-Titular de derecho real de dominio, I-Titular de dominio incompleto)}"
										);

										lsb_anotaciones.append("\\par}");

										lsb_anotaciones.append("{\\pard \\par}");

										{
											AnotacionPredioCiudadano             lapc_anotacionPredCiu;
											Collection<AnotacionPredioCiudadano> lcapc_intervinientes;

											lapc_anotacionPredCiu = new AnotacionPredioCiudadano();

											lapc_anotacionPredCiu.setIdCirculo(ls_idCirculo);
											lapc_anotacionPredCiu.setIdMatricula(ll_idMatricula);
											lapc_anotacionPredCiu.setIdAnotacion(NumericUtils.getLong(li_idAnotacion));

											lcapc_intervinientes = DaoCreator.getAccAnotacionPredioCiudadanoDAO(
												    adm_manager
												).findByCirculoMatricula(lapc_anotacionPredCiu);

											if(CollectionUtils.isValidCollection(lcapc_intervinientes))
											{
												String ls_infoInterviniente;
												lsb_anotaciones.append("{\\rtf1\\ansi\\deff0 ");

												{
													String ls_titulosInterviniente;

													ls_titulosInterviniente     = null;

													ls_titulosInterviniente     = " \\trowd\\cellx800\\cellx4000\\cellx3000\\cellx1000\\cellx500\\cellx3000\\intbl\\fs15{\\fonttbl {\\f0 sans-serif;}} "
														+ "Rol" + "\\cell\\intbl\\fs15{\\fonttbl {\\f0 sans-serif;}}  "
														+ "Nombre"
														+ "\\cell\\intbl\\fs15{\\fonttbl {\\f0 sans-serif;}}  "
														+ "Identificación"
														+ "\\cell\\intbl\\fs15{\\fonttbl {\\f0 sans-serif;}}  "
														+ "Propietario"
														+ "\\cell\\intbl\\fs15{\\fonttbl {\\f0 sans-serif;}}  " + "%"
														+ "\\cell\\intbl\\fs15{\\fonttbl {\\f0 sans-serif;}} "
														+ "Calidad Interviniente" + "\\cell\\row ";

													lsb_anotaciones = lsb_anotaciones.append(ls_titulosInterviniente);
												}

												for(AnotacionPredioCiudadano lapc_iterador : lcapc_intervinientes)
												{
													if(lapc_iterador != null)
													{
														String  ls_rol;
														String  ls_nombrePersona;
														String  ls_tipoDocumento;
														String  ls_numeroDocumento;
														String  ls_propietario;
														String  ls_porcentaje;
														String  ls_descripcion;
														Persona lp_persona;

														ls_rol         = lapc_iterador.getRolPersona();
														lp_persona     = new Persona();
														lp_persona.setIdPersona(lapc_iterador.getIdPersona());
														lp_persona             = DaoCreator.getPersonaDAO(adm_manager)
																                               .findById(lp_persona);
														ls_nombrePersona       = "";
														ls_tipoDocumento       = "";
														ls_numeroDocumento     = "";
														ls_descripcion         = "";

														if(lp_persona != null)
														{
															ls_tipoDocumento = lp_persona.getIdDocumentoTipo();

															String ls_tipoPersona;
															ls_tipoPersona = StringUtils.getStringNotNull(
																    lp_persona.getIdTipoPersona()
																);

															if(StringUtils.isValidString(ls_tipoDocumento))
															{
																if(
																    ls_tipoDocumento.equalsIgnoreCase(
																	        IdentificadoresCommon.NIT
																	    ) && ls_tipoPersona.equals(EstadoCommon.J)
																)
																	ls_nombrePersona = lp_persona.getRazonSocial();
																else
																{
																	String ls_primerNombre;
																	String ls_segundoNombre;
																	String ls_primerApellido;
																	String ls_segundoApellido;

																	ls_primerNombre        = lp_persona.getPrimerNombre();
																	ls_segundoNombre       = lp_persona.getSegundoNombre();
																	ls_primerApellido      = lp_persona
																			.getPrimerApellido();
																	ls_segundoApellido     = lp_persona
																			.getSegundoApellido();

																	ls_primerNombre     = StringUtils.isValidString(
																		    ls_primerNombre
																		) ? ls_primerNombre : "";

																	ls_segundoNombre     = StringUtils.isValidString(
																		    ls_segundoNombre
																		) ? (" " + ls_segundoNombre) : "";

																	ls_primerApellido     = StringUtils.isValidString(
																		    ls_primerApellido
																		) ? (" " + ls_primerApellido) : "";

																	ls_segundoApellido     = StringUtils.isValidString(
																		    ls_segundoApellido
																		) ? (" " + ls_segundoApellido) : "";

																	ls_nombrePersona = ls_primerNombre + " "
																		+ ls_segundoNombre + " " + ls_primerApellido
																		+ " " + ls_segundoApellido;

																	if(
																	    ls_tipoDocumento.equalsIgnoreCase("SE")
																		    && ls_tipoPersona.equals("I")
																	)
																		ls_nombrePersona += lp_persona.getRazonSocial();
																}

																ls_numeroDocumento = lp_persona.getNumeroDocumento();
															}
														}

														{
															ls_propietario     = lapc_iterador.getPropietario();
															ls_porcentaje      = lapc_iterador
																	.getPorcentajeParticipacion();
														}

														{
															String ls_interesadaRrr;
															ls_interesadaRrr = lapc_iterador.getIdInteresadaRrr();

															if(StringUtils.isValidString(ls_interesadaRrr))
															{
																ParteInteresada lpi_parametros;
																lpi_parametros = new ParteInteresada();

																lpi_parametros.setIdParteInteresada(ls_interesadaRrr);

																lpi_parametros = DaoCreator.getParteInteresadaDAO(
																	    adm_manager
																	).findById(lpi_parametros);

																if(lpi_parametros != null)
																	ls_descripcion = lpi_parametros.getDescripcion();
															}
														}

														if(StringUtils.isValidString(ls_porcentaje))
															ls_porcentaje = ls_porcentaje + " %";

														ls_infoInterviniente = " \\trowd\\cellx800\\cellx4000\\cellx3000\\cellx1000\\cellx500\\cellx3000\\intbl\\fs15{\\fonttbl {\\f0 sans-serif;}} "
															+ ls_rol
															+ "\\cell\\intbl\\fs15{\\fonttbl {\\f0 sans-serif;}}  "
															+ StringUtils.getStringNotNull(ls_nombrePersona)
															+ "\\cell\\intbl\\fs15{\\fonttbl {\\f0 sans-serif;}}  "
															+ ls_tipoDocumento + "  #" + ls_numeroDocumento
															+ "\\cell\\intbl\\fs15{\\fonttbl {\\f0 sans-serif;}}  "
															+ StringUtils.getStringNotNull(ls_propietario)
															+ "\\cell\\intbl\\fs15{\\fonttbl {\\f0 sans-serif;}}  "
															+ StringUtils.getStringNotNull(ls_porcentaje)
															+ "\\cell\\intbl\\fs15{\\fonttbl {\\f0 sans-serif;}} "
															+ StringUtils.getStringNotNull(ls_descripcion)
															+ "\\cell\\row ";

														lsb_anotaciones.append(ls_infoInterviniente);
													}
												}

												lsb_anotaciones.append("}");
											}
										}

										{
											String ls_anotacionCancelada;
											ls_anotacionCancelada = lap_iterador.getAnotacionCancelada();

											if(StringUtils.isValidString(ls_anotacionCancelada))
											{
												lsb_anotaciones.append("{\\pard \\b");
												lsb_anotaciones.append("ANOTACIÓN QUE CANCELA: ");
												lsb_anotaciones.append("}");
												lsb_anotaciones.append(ls_anotacionCancelada);
												lsb_anotaciones.append("\\par}");
											}
										}
									}
								}

								ls_tag = "<TAG_ANOTACIONES>";

								lsb_anotaciones.append("{\\pard \\par}");
								lsb_anotaciones.append("{\\pard \\b");
								lsb_anotaciones.append(
								    " NRO TOTAL DE ANOTACIONES: *"
								    + StringUtils.getString(NumericUtils.getDouble(li_idAnotacion)) + "*"
								);
								lsb_anotaciones.append("\\par}");

								if(ls_plantilla.contains(ls_tag))
									ls_plantilla = ls_plantilla.replace(ls_tag, lsb_anotaciones.toString());
							}
						}

						{
							PredioSegregado             lps_predioSegregado;
							Collection<PredioSegregado> lcps_predioSegregado;

							lps_predioSegregado = new PredioSegregado();
							lps_predioSegregado.setIdCirculo1(ls_idCirculo);
							lps_predioSegregado.setIdMatricula1(ll_idMatricula);

							lcps_predioSegregado = DaoCreator.getAccPredioSegregadoDAO(adm_manager)
									                             .findAllByCirculo1Matricula1(lps_predioSegregado);

							if(CollectionUtils.isValidCollection(lcps_predioSegregado))
							{
								StringBuilder lsb_sb;
								lsb_sb = new StringBuilder();

								for(PredioSegregado lcps_iterador : lcps_predioSegregado)
								{
									if(lcps_iterador != null)
									{
										lsb_sb.append(lcps_iterador.getIdCirculo());
										lsb_sb.append(" - ");
										lsb_sb.append(lcps_iterador.getIdMatricula());
										lsb_sb.append("{\\pard \\par}");
									}
								}

								ls_tag = "<TAG_MATRICULAS_ABIERTAS_CON_BASE_EN>";

								if(ls_plantilla.contains(ls_tag))
									ls_plantilla = ls_plantilla.replace(ls_tag, lsb_sb.toString());
							}
						}

						{
							PredioSegregado             lps_predioSegregado;
							Collection<PredioSegregado> lcps_predioSegregado;

							lps_predioSegregado = new PredioSegregado();
							lps_predioSegregado.setIdCirculo(ls_idCirculo);
							lps_predioSegregado.setIdMatricula(ll_idMatricula);

							lcps_predioSegregado = DaoCreator.getAccPredioSegregadoDAO(adm_manager)
									                             .findAllByCirculoMatricula(lps_predioSegregado);

							if(CollectionUtils.isValidCollection(lcps_predioSegregado))
							{
								StringBuilder lsb_sb;
								lsb_sb = new StringBuilder();

								for(PredioSegregado lcps_iterador : lcps_predioSegregado)
								{
									if(lcps_iterador != null)
									{
										lsb_sb.append(lcps_iterador.getIdCirculo1());
										lsb_sb.append(" - ");
										lsb_sb.append(lcps_iterador.getIdMatricula1());
										lsb_sb.append("{\\pard \\par}");
									}
								}

								ls_tag = "<TAG_MATRICULAS_SEGREGADAS>";

								if(ls_plantilla.contains(ls_tag))
									ls_plantilla = ls_plantilla.replace(ls_tag, lsb_sb.toString());
							}
						}

						{
							AccAreaPredio lap_areaPredio;

							lap_areaPredio = new AccAreaPredio();

							lap_areaPredio.setIdCirculo(ls_idCirculo);
							lap_areaPredio.setIdMatricula(NumericUtils.getLongWrapper(ll_idMatricula));

							lap_areaPredio = DaoCreator.getAccAreaPredioDAO(adm_manager)
									                       .findByCirculoMatricula(lap_areaPredio);

							if(lap_areaPredio != null)
							{
								String ls_idAreaPredio;

								ls_idAreaPredio = lap_areaPredio.getIdArea();

								if(StringUtils.isValidString(ls_idAreaPredio))
								{
									AccDetalleAreaPredioDAO ldap_DAO;
									Long                    ll_idMatriculaLong;
									MedidaAreaDAO           lma_DAO;

									ldap_DAO               = DaoCreator.getAccDetalleAreaPredioDAO(adm_manager);
									ll_idMatriculaLong     = NumericUtils.getLongWrapper(ll_idMatricula);
									lma_DAO                = DaoCreator.getMedidaAreaDAO(adm_manager);

									{
										ls_tag = "<TAG_AREA_PREDIO>";

										if(ls_plantilla.contains(ls_tag))
										{
											Collection<DetalleAreaPredio> lcdap_areasTerreno;
											DetalleAreaPredio             ldap_area;
											StringBuilder                 lsb_sb;

											ldap_area     = new DetalleAreaPredio();
											lsb_sb        = new StringBuilder();

											ldap_area.setIdAreaPredio(StringUtils.getString(ls_idAreaPredio));
											ldap_area.setIdCirculo(ls_idCirculo);
											ldap_area.setIdMatricula(ll_idMatriculaLong);
											ldap_area.setIdTipoArea(TipoAreaCommon.TERRENO);

											lcdap_areasTerreno = ldap_DAO.findAllByIdAreaPredioTipo(ldap_area);

											if(CollectionUtils.isValidCollection(lcdap_areasTerreno))
											{
												Iterator<DetalleAreaPredio> lidap_iterator;

												lidap_iterator = lcdap_areasTerreno.iterator();

												while(lidap_iterator.hasNext())
												{
													DetalleAreaPredio ldap_detalleIterador;
													String            ls_separador;

													ldap_detalleIterador     = lidap_iterator.next();
													ls_separador             = lidap_iterator.hasNext() ? ", " : "";

													if(ldap_detalleIterador != null)
													{
														Double ld_area;

														ld_area = ldap_detalleIterador.getArea();

														if(NumericUtils.isValidDouble(ld_area))
														{
															String ls_unidadMedida;

															ls_unidadMedida = ldap_detalleIterador.getIdUnidadMedida();

															lsb_sb.append(StringUtils.getString(ld_area));

															if(StringUtils.isValidString(ls_unidadMedida))
															{
																MedidaArea lma_medidaArea;

																lma_medidaArea = lma_DAO.findById(ls_unidadMedida);

																if(lma_medidaArea != null)
																	lsb_sb.append(" " + lma_medidaArea.getCodigo());
															}

															lsb_sb.append(ls_separador);
														}
													}
												}
											}

											ls_plantilla = ls_plantilla.replace(ls_tag, lsb_sb.toString());
										}
									}

									{
										ls_tag = "<TAG_AREA_PRIVADA>";

										if(ls_plantilla.contains(ls_tag))
										{
											DetalleAreaPredio ldap_areaPrivada;
											DetalleAreaPredio ldap_area;
											StringBuilder     lsb_sb;

											ldap_area     = new DetalleAreaPredio();
											lsb_sb        = new StringBuilder();

											ldap_area.setIdAreaPredio(StringUtils.getString(ls_idAreaPredio));
											ldap_area.setIdCirculo(ls_idCirculo);
											ldap_area.setIdMatricula(ll_idMatriculaLong);
											ldap_area.setIdTipoArea(TipoAreaCommon.PRIVADA);

											ldap_areaPrivada = ldap_DAO.findByIdAreaPredioTipo(ldap_area);

											if(ldap_areaPrivada != null)
											{
												Double ld_area;

												ld_area = ldap_areaPrivada.getArea();

												if(NumericUtils.isValidDouble(ld_area))
												{
													String ls_unidadMedida;

													ls_unidadMedida = ldap_areaPrivada.getIdUnidadMedida();

													lsb_sb.append(StringUtils.getString(ld_area));

													if(StringUtils.isValidString(ls_unidadMedida))
													{
														MedidaArea lma_medidaArea;

														lma_medidaArea = lma_DAO.findById(ls_unidadMedida);

														if(lma_medidaArea != null)
															lsb_sb.append(" " + lma_medidaArea.getCodigo());
													}
												}
											}

											ls_plantilla = ls_plantilla.replace(ls_tag, lsb_sb.toString());
										}
									}

									{
										ls_tag = "<TAG_AREA_CONSTRUIDA>";

										if(ls_plantilla.contains(ls_tag))
										{
											DetalleAreaPredio ldap_areaConstruida;
											DetalleAreaPredio ldap_area;
											StringBuilder     lsb_sb;

											ldap_area     = new DetalleAreaPredio();
											lsb_sb        = new StringBuilder();

											ldap_area.setIdAreaPredio(StringUtils.getString(ls_idAreaPredio));
											ldap_area.setIdCirculo(ls_idCirculo);
											ldap_area.setIdMatricula(ll_idMatriculaLong);
											ldap_area.setIdTipoArea(TipoAreaCommon.CONSTRUIDA);

											ldap_areaConstruida = ldap_DAO.findByIdAreaPredioTipo(ldap_area);

											if(ldap_areaConstruida != null)
											{
												Double ld_area;

												ld_area = ldap_areaConstruida.getArea();

												if(NumericUtils.isValidDouble(ld_area))
												{
													String ls_unidadMedida;

													ls_unidadMedida = ldap_areaConstruida.getIdUnidadMedida();

													lsb_sb.append(StringUtils.getString(ld_area));

													if(StringUtils.isValidString(ls_unidadMedida))
													{
														MedidaArea lma_medidaArea;

														lma_medidaArea = lma_DAO.findById(ls_unidadMedida);

														if(lma_medidaArea != null)
															lsb_sb.append(" " + lma_medidaArea.getCodigo());
													}
												}
											}

											ls_plantilla = ls_plantilla.replace(ls_tag, lsb_sb.toString());
										}
									}

									{
										ls_tag = "<TAG_COEFICIENTE>";

										if(ls_plantilla.contains(ls_tag))
										{
											String ls_coeficiente;
											ls_coeficiente = StringUtils.getString(lap_areaPredio.getCoeficiente());

											if(StringUtils.isValidString(ls_coeficiente))
												ls_plantilla = ls_plantilla.replace(ls_tag, ls_coeficiente);
										}
									}
								}
							}
						}
					}

					{
						String ls_turnoCertificado;
						ls_turnoCertificado = asm_parametros.getIdTurnoCertificado();

						if(StringUtils.isValidString(ls_turnoCertificado))
						{
							{
								ls_tag = "<ID_TURNO>";

								if(ls_plantilla.contains(ls_tag))
									ls_plantilla = ls_plantilla.replace(ls_tag, ls_turnoCertificado);
							}

							{
								Turno lt_turno;
								lt_turno = new Turno();

								lt_turno.setIdTurno(ls_turnoCertificado);

								lt_turno = DaoCreator.getTurnoDAO(adm_manager).findById(lt_turno);

								if(lt_turno != null)
								{
									ls_tag = "<TAG_FECHA_TURNO>";

									if(ls_plantilla.contains(ls_tag))
										ls_plantilla = ls_plantilla.replace(
											    ls_tag,
											    StringUtils.getString(
											        lt_turno.getFechaCreacion(), FormatoFechaCommon.DIA_MES_ANIO
											    )
											);
								}
								else
								{
									ls_tag = "<TAG_FECHA_TURNO>";

									if(ls_plantilla.contains(ls_tag))
										ls_plantilla = ls_plantilla.replace(ls_tag, ls_sinInformacion);
								}
							}
						}
						else
						{
							ls_tag = "<ID_TURNO>";

							if(ls_plantilla.contains(ls_tag))
								ls_plantilla = ls_plantilla.replace(ls_tag, ls_sinInformacion);

							ls_tag = "<TAG_FECHA_TURNO>";

							if(ls_plantilla.contains(ls_tag))
								ls_plantilla = ls_plantilla.replace(ls_tag, ls_sinInformacion);
						}

						{
							Constantes lc_usuarioFirma;
							String     ls_tagUsuarioFirma;
							int        li_incrX = 0;
							int        li_incrY = 0;

							lc_usuarioFirma     = new Constantes();
							ls_tagUsuarioFirma  = "<TAG_USUARIO_FIRMA_SUSPENSION>";

							lc_usuarioFirma.setIdConstante(ConstanteCommon.USUARIO_FIRMA_SUSPENSION);

							lc_usuarioFirma     = DaoCreator.getConstantesDAO(adm_manager)
									                            .findByIdWithImage(lc_usuarioFirma);

							ls_plantilla     = getFirmaMasivaBusiness()
									                   .reemplazarUsuarioFirmaCargo(
									    ls_plantilla, lc_usuarioFirma, ls_tagUsuarioFirma,
									    "<TAG_CARGO_FIRMA_SUSPENSION>"
									);

							lmss_datos       = finalizarPlantilla(
								    ls_plantilla, lth_turnoHistoria.getIdTurnoHistoria(), adm_manager
								);
							ls_plantilla     = lmss_datos.get(IdentificadoresCommon.PLANTILLA);
							lba_archivo      = new PDFGenerator().convertirRTFToPDF(
								    ls_plantilla.getBytes(), adm_manager
								);

							if(lba_archivo == null)
								throw new B2BException(ErrorKeys.NO_SE_GENERO_CERTIFICADO_TRADICION);

							if(ab_firmaMasiva)
							{
								byte[] lba_grafo;

								lba_grafo = null;

								if(lc_usuarioFirma != null)
								{
									lba_grafo     = lc_usuarioFirma.getImagenes();
									li_incrX      = NumericUtils.getInt(lc_usuarioFirma.getEntero());
									li_incrY      = NumericUtils.getInt(lc_usuarioFirma.getReal());
								}

								lba_archivo = getFirmaMasivaBusiness()
										              .reemplazarBookmarsFirma(
										    lba_archivo, lba_grafo, li_incrX, li_incrY
										);
							}
						}

						if(StringUtils.isValidString(ls_turnoCertificado))
						{
							ImagenesDAO         lid_DAO;
							DocumentosSalidaDAO ldsd_DAO;
							long                ll_idDocumentoSalida;
							long                ll_secuencia;
							String              ls_idSolicitud;

							boolean lb_existeImagen;

							Imagenes         li_imagenes;
							DocumentosSalida lds_documentosSalida;

							lid_DAO      = DaoCreator.getImagenesDAO(adm_manager);
							ldsd_DAO     = DaoCreator.getDocumentosSalidaDAO(adm_manager);

							li_imagenes              = new Imagenes();
							lds_documentosSalida     = new DocumentosSalida();
							ls_idSolicitud           = asm_parametros.getIdSolicitud();

							ll_idDocumentoSalida = 0;

							{
								DocumentosSalida lds_docTmp;
								lds_docTmp = new DocumentosSalida();

								lds_docTmp.setIdSolicitud(ls_idSolicitud);
								lds_docTmp.setIdTurno(ls_turnoCertificado);
								lds_docTmp.setTipo(TipoArchivoCommon.CREACION_MATRICULA);
								lds_docTmp.setEstado(EstadoCommon.ACTIVO);
								lds_docTmp.setReferenciaSalida(ls_idCirculo + "-" + ll_idMatricula);

								lds_docTmp = ldsd_DAO.findDocumentByTurnoTipoEstadoReferenciaSalida(lds_docTmp);

								if(lds_docTmp != null)
									ll_idDocumentoSalida = lds_docTmp.getIdDocumentoSalida();
							}

							lb_existeImagen = ll_idDocumentoSalida > 0;

							if(lb_existeImagen)
							{
								lds_documentosSalida.setIdDocumentoSalida(ll_idDocumentoSalida);

								lds_documentosSalida = ldsd_DAO.findById(lds_documentosSalida);

								if(lds_documentosSalida != null)
									li_imagenes.setIdImagen(NumericUtils.getInt(lds_documentosSalida.getIdImagen()));
							}

							li_imagenes.setTipoArchivo(ExtensionCommon.PDF_MAYUS);
							li_imagenes.setIdUsuarioCreacion(as_usuario);
							li_imagenes.setIdUsuarioModificacion(as_usuario);
							li_imagenes.setIpCreacion(as_ipRemote);
							li_imagenes.setIpModificacion(as_ipRemote);
							li_imagenes.setImagenBlob(lba_archivo);
							li_imagenes.setCodigoVerificacion(
							    lmss_datos.get(IdentificadoresCommon.CODIGO_VERIFICACION)
							);

							ll_secuencia = lid_DAO.insertOrUpdate(li_imagenes, !lb_existeImagen);

							if(lth_turnoHistoria != null)
								lds_documentosSalida.setIdTurnoHistoria(
								    NumericUtils.getInteger(lth_turnoHistoria.getIdTurnoHistoria())
								);

							lds_documentosSalida.setIdTurno(ls_turnoCertificado);

							if(!lb_existeImagen)
							{
								if(ll_secuencia <= 0)
									throw new B2BException(ErrorKeys.NO_INSERTO_IMAGENES);

								lds_documentosSalida.setIdSolicitud(ls_idSolicitud);
								lds_documentosSalida.setIdImagen(NumericUtils.getLongWrapper(ll_secuencia));
								lds_documentosSalida.setTipo(TipoArchivoCommon.CREACION_MATRICULA);
								lds_documentosSalida.setEstado(EstadoCommon.ACTIVO);
								lds_documentosSalida.setReferenciaSalida(ls_idCirculo + "-" + ll_idMatricula);
								lds_documentosSalida.setIdTipoDocumental(TipoDocumentalCommon.CREACION_MATRICULA);
								lds_documentosSalida.setIdUsuarioCreacion(as_usuario);
								lds_documentosSalida.setIdUsuarioModificacion(as_usuario);
								lds_documentosSalida.setIpCreacion(as_ipRemote);

								ldsd_DAO.insertOrUpdate(lds_documentosSalida, true);

								lds_documentosSalida.setEstado(EstadoCommon.INACTIVO);

								ldsd_DAO.updateEstado(lds_documentosSalida);
							}
							else
							{
								lds_documentosSalida.setIdUsuarioModificacion(as_usuario);
								lds_documentosSalida.setIpModificacion(as_ipRemote);

								ldsd_DAO.insertOrUpdate(lds_documentosSalida, false);

								lds_documentosSalida.setEstado(EstadoCommon.INACTIVO);

								ldsd_DAO.updateEstado(lds_documentosSalida);
							}
						}
					}
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("generarConsutaCreacionGrabacionMatricula", lb2be_e);

			throw lb2be_e;
		}
		catch(Exception le_e)
		{
			clh_LOGGER.error("generarConsutaCreacionGrabacionMatricula", le_e);

			throw new B2BException(le_e.getLocalizedMessage());
		}

		return lba_archivo;
	}

	/**
	 * Método de generación de los documentos de etapa 105, matriculas oficio antiguo sistema
	 * @param aot_oficioTextoData con los datos de para generar el documento
	 * @param as_userIdcon el usuario de la transacción
	 * @param as_remoteIp con la ip del usuario de la transacción
	 * @param al_motivo con motivo del turno
	 * @param adm_manager con el dao manager de la transacción
	 * @return
	 * @throws B2BException
	 */
	public synchronized byte[] generarDocumentoMatriculaOficioAS(
	    OficiosTexto aot_oficioTextoData, String as_userId, String as_remoteIp, long al_motivo
	)
	    throws B2BException
	{
		byte[]     lba_return;
		DAOManager ldm_manager;

		lba_return      = null;
		ldm_manager     = DaoManagerFactory.getDAOManager();

		try
		{
			if((aot_oficioTextoData != null) && (al_motivo > 0))
				lba_return = generarDocumentoMatriculaOficioAS(
					    aot_oficioTextoData, as_userId, as_remoteIp, al_motivo, true, false, ldm_manager
					);
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("generarDocumentoMatriculaOficioAS", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lba_return;
	}

	/**
	 * Método de generación de los documentos de etapa 105, matriculas oficio antiguo sistema
	 * @param aot_oficioTextoData con los datos de para generar el documento
	 * @param as_userIdcon el usuario de la transacción
	 * @param as_remoteIp con la ip del usuario de la transacción
	 * @param al_motivo con motivo del turno
	 * @param ab_salvar para determinar si se debe guardar en base de datos
	 * @param ab_firma para determinar si se debe firmar el documento
	 * @param adm_manager con el dao manager de la transacción
	 * @return
	 * @throws B2BException
	 */
	public synchronized byte[] generarDocumentoMatriculaOficioAS(
	    OficiosTexto aot_oficioTextoData, String as_userId, String as_remoteIp, long al_motivo, boolean ab_salvar,
	    boolean ab_firma, DAOManager adm_manager
	)
	    throws B2BException
	{
		byte[] lba_return;

		lba_return = null;

		if(aot_oficioTextoData != null)
		{
			try
			{
				TurnoHistoria lth_turnoHistoria;

				lth_turnoHistoria = DaoCreator.getTurnoHistoriaDAO(adm_manager)
						                          .findById(aot_oficioTextoData.getIdTurnoHistoria());

				if(lth_turnoHistoria != null)
				{
					Long   ll_idTurnoHistoria;
					String ls_idTurno;

					ll_idTurnoHistoria     = lth_turnoHistoria.getIdTurnoHistoria();
					ls_idTurno             = lth_turnoHistoria.getIdTurno();

					String ls_idPlantilla;

					if(StringUtils.isValidString(ls_idTurno) && NumericUtils.isValidLong(ll_idTurnoHistoria))
					{
						Turno lt_turno;

						lt_turno           = DaoCreator.getTurnoDAO(adm_manager).findById(ls_idTurno);

						if((lt_turno != null) && (al_motivo > 0))
						{
							byte[]                 lba_plantilla;
							TagPlantillaResolucion laa_actuacionesAdministrativas;
							String                 ls_tipoArchivo;
							String                 ls_tipoDocumental;

							laa_actuacionesAdministrativas = plantillaMatriculaOficioAS(adm_manager, al_motivo, true);

							if(laa_actuacionesAdministrativas == null)
							{
								Object[] loa_messageArgs = new String[1];

								loa_messageArgs[0] = StringUtils.getString(al_motivo);

								throw new B2BException(
								    ErrorKeys.ERROR_NO_SE_ENCONTRARON_DATOS_ID_MOTIVO, loa_messageArgs
								);
							}

							ls_idPlantilla        = laa_actuacionesAdministrativas.getIdPlantilla();
							ls_tipoArchivo        = laa_actuacionesAdministrativas.getTipoArchivo();
							ls_tipoDocumental     = laa_actuacionesAdministrativas.getTipoDocumental();
							lba_plantilla         = laa_actuacionesAdministrativas.getArchivo();

							if(lba_plantilla == null)
							{
								Object[] loa_messageArgs = new String[1];
								loa_messageArgs[0] = ls_idPlantilla;

								throw new B2BException(ErrorKeys.NO_IMAGEN_CONSTANTE, loa_messageArgs);
							}
							else
							{
								Constantes          lc_usuarioFirma;
								ConstantesDAO       lc_DAO;
								Date                ld_fechaResol;
								Date                ld_fechaOficio;
								DocumentosSalidaDAO lds_DAO;
								ImagenesDAO         li_DAO;
								Long                ll_NumeroResolucion;
								Map<String, String> lmss_datos;
								String              ls_idSolicitud;
								String              ls_idCirculo;
								String              ls_codigoVerificacion;
								String              ls_idCorrespondencia;
								String              ls_consecutivoOficio;
								CirculoRegistral    lcr_circuloRegistral;

								lc_usuarioFirma           = new Constantes();
								lc_DAO                    = DaoCreator.getConstantesDAO(adm_manager);
								ld_fechaResol             = null;
								ld_fechaOficio            = null;
								lds_DAO                   = DaoCreator.getDocumentosSalidaDAO(adm_manager);
								li_DAO                    = DaoCreator.getImagenesDAO(adm_manager);
								ll_NumeroResolucion       = null;
								lmss_datos                = null;
								ls_idSolicitud            = lt_turno.getIdSolicitud();
								ls_codigoVerificacion     = null;
								ls_idCorrespondencia      = null;
								ls_consecutivoOficio      = null;
								ls_idCirculo              = lt_turno.getIdCirculo();

								lc_usuarioFirma.setIdConstante(ConstanteCommon.USUARIO_FIRMA_SUSPENSION);
								lc_usuarioFirma     = lc_DAO.findByIdWithImage(lc_usuarioFirma);

								lcr_circuloRegistral = new CirculoRegistral();

								lcr_circuloRegistral.setIdCirculo(ls_idCirculo);

								lcr_circuloRegistral = DaoCreator.getCirculoRegistralDAO(adm_manager)
										                             .findById(lcr_circuloRegistral);

								if(!ab_firma)
								{
									String ls_tag;
									String ls_plantilla;

									ls_plantilla     = new String(lba_plantilla);

									ls_tag = "<TAG_TURNO>";

									if(ls_plantilla.contains(ls_tag))
										ls_plantilla = ls_plantilla.replace(ls_tag, ls_idTurno);

									String ls_consideraciones;
									ls_consideraciones     = null;
									ls_consideraciones     = aot_oficioTextoData.getConsideracion();

									ls_tag = "<TAG_CONSIDERACIONES_OFICIO>";

									if(ls_plantilla.contains(ls_tag) && StringUtils.isValidString(ls_consideraciones))
										ls_plantilla = ls_plantilla.replace(ls_tag, ls_consideraciones);

									aot_oficioTextoData.setIdUsuarioCreacion(as_userId);
									aot_oficioTextoData.setIpCreacion(as_remoteIp);

									DaoCreator.getOficiosTextoDAO(adm_manager).insertOrUpdate(
									    aot_oficioTextoData, true
									);

									ls_tag     = "<TAG_ID_SOLICITUD_MATRICULA>";

									ls_plantilla = resolverTagMatricula(
										    adm_manager, ls_plantilla, ls_tag, ls_idSolicitud, ls_idCirculo, false
										);

									{
										Solicitud ls_solicitud;

										ls_solicitud = DaoCreator.getSolicitudDAO(adm_manager)
												                     .findById(lth_turnoHistoria.getIdSolicitud());

										if(ls_solicitud != null)
											ls_plantilla = reemplazarTagsDocumento(
												    adm_manager, ls_solicitud, lt_turno, ls_plantilla
												);
									}

									if(StringUtils.isValidString(ls_idCirculo))
									{
										if(lcr_circuloRegistral != null)
										{
											String ls_nombre;

											ls_nombre = lcr_circuloRegistral.getNombre();

											if(StringUtils.isValidString(ls_nombre))
											{
												ls_tag = "<TAG_NOMBRE_ORIP>";

												if(ls_plantilla.contains(ls_tag))
													ls_plantilla = ls_plantilla.replace(ls_tag, ls_nombre);

												ls_tag = "<TAG_MUN_OFI_ORIGEN>";

												if(ls_plantilla.contains(ls_tag))
													ls_plantilla = ls_plantilla.replace(ls_tag, ls_nombre);

												ls_tag = "<TAG_DEPARTAMENTO>";

												if(ls_plantilla.contains(ls_tag))
												{
													String ls_idDepartamento;
													String ls_idPais;

													ls_idDepartamento     = lcr_circuloRegistral.getIdDepartamento();
													ls_idPais             = lcr_circuloRegistral.getIdPais();

													if(
													    StringUtils.isValidString(ls_idDepartamento)
														    && StringUtils.isValidString(ls_idPais)
													)
													{
														Departamento ld_departamento;

														ld_departamento = DaoCreator.getDepartamentoDAO(adm_manager)
																                        .findById(
																    ls_idPais, ls_idDepartamento
																);

														if(ld_departamento != null)
														{
															String ls_nombreDepartamento;

															ls_nombreDepartamento = ld_departamento.getNombre();

															if(StringUtils.isValidString(ls_nombreDepartamento))
																ls_plantilla = ls_plantilla.replace(
																	    ls_tag, ls_nombreDepartamento
																	);
														}
													}
												}
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

									{
										Solicitud ls_solicitud;

										ls_solicitud = DaoCreator.getSolicitudDAO(adm_manager).findById(ls_idSolicitud);

										if(ls_solicitud != null)
										{
											String ls_idPersona;

											ls_tag           = "<TAG_NIR>";
											ls_idPersona     = ls_solicitud.getIdPersona();

											if(ls_plantilla.contains(ls_tag))
											{
												String ls_nir;

												ls_nir = ls_solicitud.getNir();

												if(StringUtils.isValidString(ls_nir))
													ls_plantilla = ls_plantilla.replace(ls_tag, ls_nir);
											}

											if(StringUtils.isValidString(ls_idPersona))
											{
												Persona lp_persona;

												lp_persona = DaoCreator.getPersonaDAO(adm_manager).findById(
													    ls_idPersona
													);

												if(lp_persona != null)
												{
													String ls_tag1;
													String ls_tag2;

													ls_tag      = "<TAG_NOMBRE_INTERESADO>";
													ls_tag1     = "<TAG_DEPAR_INTERESADO>";
													ls_tag2     = "<TAG_MUNICIPIO_INTERESADO>";

													if(ls_plantilla.contains(ls_tag))
													{
														String ls_nombreCompleto;

														ls_nombreCompleto = lp_persona.getNombreCompleto();

														if(StringUtils.isValidString(ls_nombreCompleto))
															ls_plantilla = ls_plantilla.replace(
																    ls_tag, ls_nombreCompleto
																);
													}

													ls_tag = "<TAG_CORREO_ELECTRONICO>";

													if(ls_plantilla.contains(ls_tag))
													{
														String ls_idCorreo;

														ls_idCorreo = ls_solicitud.getIdCorreoElectronico();

														if(!StringUtils.isValidString(ls_idCorreo))
															ls_idCorreo = ls_solicitud
																	.getIdCorreoElectronicoComunicacion();

														if(StringUtils.isValidString(ls_idCorreo))
														{
															PersonaCorreoElectronico lpce_correo;

															lpce_correo = DaoCreator.getPersonaCorreoElectronicoDAO(
																    adm_manager
																).findById(ls_idPersona, ls_idCorreo);

															if(lpce_correo != null)
															{
																String ls_correo;

																ls_correo = lpce_correo.getCorreoElectronico();

																if(StringUtils.isValidString(ls_correo))
																	ls_plantilla = ls_plantilla.replace(
																		    ls_tag, ls_correo
																		);
															}
														}
													}

													ls_tag = "<TAG_DIR_INTERESADO>";

													if(
													    ls_plantilla.contains(ls_tag) || ls_plantilla.contains(ls_tag1)
														    || ls_plantilla.contains(ls_tag2)
													)
													{
														String ls_idDireccion;

														ls_idDireccion = ls_solicitud.getIdDireccion();

														if(!StringUtils.isValidString(ls_idDireccion))
															ls_idDireccion = ls_solicitud.getIdDireccionComunicacion();

														if(StringUtils.isValidString(ls_idDireccion))
														{
															PersonaDireccion lpd_direccion;

															lpd_direccion = DaoCreator.getPersonaDireccionDAO(
																    adm_manager
																).findById(ls_idPersona, ls_idDireccion);

															if(lpd_direccion != null)
															{
																String ls_direccion;
																String ls_idDepartamento;
																String ls_idPais;

																ls_direccion          = lpd_direccion.getDireccion();
																ls_idDepartamento     = lpd_direccion.getIdDepartamento();
																ls_idPais             = lpd_direccion.getIdPais();

																if(
																    StringUtils.isValidString(ls_direccion)
																	    && ls_plantilla.contains(ls_tag)
																)
																	ls_plantilla = ls_plantilla.replace(
																		    ls_tag, ls_direccion
																		);

																if(StringUtils.isValidString(ls_idDepartamento))
																{
																	Departamento ld_departamento;

																	ld_departamento = DaoCreator.getDepartamentoDAO(
																		    adm_manager
																		).findById(ls_idPais, ls_idDepartamento);

																	if(ld_departamento != null)
																	{
																		String ls_idMunicipio;
																		String ls_nombreDepartamento;

																		ls_idMunicipio            = lpd_direccion
																				.getIdMunicipio();
																		ls_nombreDepartamento     = ld_departamento
																				.getNombre();

																		if(
																		    StringUtils.isValidString(
																			        ls_nombreDepartamento
																			    ) && ls_plantilla.contains(ls_tag1)
																		)
																			ls_plantilla = ls_plantilla.replace(
																				    ls_tag1, ls_nombreDepartamento
																				);

																		if(ls_idMunicipio != null)
																		{
																			Municipio lm_municipio;

																			lm_municipio = DaoCreator.getMunicipioDAO(
																				    adm_manager
																				)
																					                     .findById(
																					    ls_idPais, ls_idDepartamento,
																					    ls_idMunicipio
																					);

																			if(lm_municipio != null)
																			{
																				String ls_nombreMunicipio;

																				ls_nombreMunicipio = lm_municipio
																						.getNombre();

																				if(
																				    StringUtils.isValidString(
																					        ls_nombreMunicipio
																					    )
																					    && ls_plantilla.contains(
																					        ls_tag2
																					    )
																				)
																					ls_plantilla = ls_plantilla.replace(
																						    ls_tag2, ls_nombreMunicipio
																						);
																			}
																		}
																	}
																}
															}
														}
													}

													ls_tag = "<TAG_TEL_INTERESADO>";

													if(ls_plantilla.contains(ls_tag))
													{
														String ls_idTel;

														ls_idTel = ls_solicitud.getIdTelefono();

														if(!StringUtils.isValidString(ls_idTel))
															ls_idTel = ls_solicitud.getIdTelefonoComunicacion();

														if(StringUtils.isValidString(ls_idTel))
														{
															PersonaTelefono lpt_telefono;

															lpt_telefono = DaoCreator.getPersonaTelefonoDAO(
																    adm_manager
																).findById(ls_idPersona, ls_idTel);

															if(lpt_telefono != null)
															{
																String ls_telefono;

																ls_telefono = lpt_telefono.getTelefono();

																if(
																    StringUtils.isValidString(ls_telefono)
																	    && ls_plantilla.contains(ls_tag)
																)
																	ls_plantilla = ls_plantilla.replace(
																		    ls_tag, ls_telefono
																		);
															}
														}
													}
												}
											}
										}
									}

									ls_tag = "<TAG_USUARIO_FIRMA_ACTUACIONES_ADMINISTRATIVAS>";

									if(ls_plantilla.contains(ls_tag))
										ls_plantilla = getFirmaMasivaBusiness()
												               .reemplazarUsuarioFirmaCargo(
												    ls_plantilla, lc_usuarioFirma, ls_tag,
												    "<TAG_CARGO_FIRMA_ACTUACIONES_ADMINISTRATIVAS>"
												);

									ls_tag = "<TAG_OFICIO>";

									if(ls_plantilla.contains(ls_tag))
										ls_plantilla = putCustomBookMark(
											    ls_plantilla, ls_tag, "OFICIO",
											    lc_DAO.findById(ConstanteCommon.TAG_OFICIO)
											);

									ls_tag = "<TAG_REFERENCIA_SALIDA>";

									if(ls_plantilla.contains(ls_tag))
										ls_plantilla = putCustomBookMark(
											    ls_plantilla, ls_tag, "REFERENCIA_SALIDA",
											    lc_DAO.findById(ConstanteCommon.TAG_REFERENCIA_SALIDA)
											);

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

									ls_tag = "<TAG_USUARIO>";

									if(ls_plantilla.contains(ls_tag))
										ls_plantilla = ls_plantilla.replace(ls_tag, as_userId);

									ls_tag = "<TAG_FECHA>";

									if(ls_plantilla.contains(ls_tag))
									{
										Date       ld_fechaActual = new Date();
										DateFormat lsf_dateFormat = new SimpleDateFormat(
											    FormatoFechaCommon.DIA_MES_ANIO
											);
										String     ls_fecha       = lsf_dateFormat.format(ld_fechaActual);
										ls_plantilla              = ls_plantilla.replace(ls_tag, ls_fecha);
									}

									ls_plantilla              = escribirTagFechaLarga(ls_plantilla);
									lmss_datos                = finalizarPlantilla(
										    ls_plantilla, ll_idTurnoHistoria, adm_manager
										);
									ls_plantilla              = lmss_datos.get(IdentificadoresCommon.PLANTILLA);
									ls_codigoVerificacion     = lmss_datos.get(
										    IdentificadoresCommon.CODIGO_VERIFICACION
										);

									lba_return = new PDFGenerator().convertirRTFToPDF(
										    ls_plantilla.getBytes(), adm_manager
										);

									if(lba_return == null)
										throw new B2BException(ErrorKeys.NO_SE_GENERO_ARCHIVO);
								}
								else
								{
									DocumentosSalida lds_documentoSalida;

									lds_documentoSalida = new DocumentosSalida();

									lds_documentoSalida.setIdSolicitud(ls_idSolicitud);
									lds_documentoSalida.setIdTurno(ls_idTurno);
									lds_documentoSalida.setTipo(ls_tipoArchivo);
									lds_documentoSalida.setEstado(EstadoCommon.ACTIVO);

									lds_documentoSalida = lds_DAO.findDocumentByTurnoTipoEstadoReferenciaSalida(
										    lds_documentoSalida, false
										);

									if(lds_documentoSalida != null)
									{
										Imagenes li_imagen;

										li_imagen = li_DAO.findById(
											    NumericUtils.getLong(lds_documentoSalida.getIdImagen())
											);

										if(li_imagen != null)
										{
											lba_return                = li_imagen.getImagenBlob();
											ls_codigoVerificacion     = li_imagen.getCodigoVerificacion();

											if(lba_return != null)
											{
												int li_fontSize;
												int li_temp;

												li_fontSize     = 12;
												li_temp         = 9;

												if(lc_usuarioFirma != null)
												{
													int li_incrX;
													int li_incrY;

													li_incrX     = NumericUtils.getInt(lc_usuarioFirma.getEntero());
													li_incrY     = NumericUtils.getInt(lc_usuarioFirma.getReal());

													lba_return = getFirmaMasivaBusiness()
															             .reemplazarBookmarsFirma(
															    lba_return, lc_usuarioFirma.getImagenes(), li_incrX,
															    li_incrY
															);
												}

												{
													NumeracionOficiosCirculo lnoc_numeracionOficios;

													lnoc_numeracionOficios = findNumeracionOficiosCirculo(
														    lth_turnoHistoria, adm_manager, as_userId, as_remoteIp
														);

													if(lnoc_numeracionOficios != null)
													{
														ls_consecutivoOficio = lnoc_numeracionOficios
																.getConsecutivoGenerado();

														if(StringUtils.isValidString(ls_consecutivoOficio))
														{
															ld_fechaOficio     = new Date();
															lba_return         = getFirmaMasivaBusiness()
																	                     .reemplazarBookmarsFirma(
																	    lba_return,
																	    MarcaAgua.crearImagenConTexto(
																	        ls_consecutivoOficio,
																	        ls_consecutivoOficio.length() * li_temp,
																	        li_fontSize, li_fontSize
																	    ), 0, 0, "OFICIO", false
																	);
															lba_return         = getFirmaMasivaBusiness()
																	                     .reemplazarBookmarsFirma(
																	    lba_return,
																	    MarcaAgua.crearImagenConTexto(
																	        ls_consecutivoOficio,
																	        ls_consecutivoOficio.length() * li_temp,
																	        li_fontSize, li_fontSize
																	    ), 0, 0, "NUM_OFI", false
																	);
														}
													}

													ls_idCorrespondencia = generarIdCorrespondencia(
														    lth_turnoHistoria, adm_manager, false
														);

													if(StringUtils.isValidString(ls_idCorrespondencia))
													{
														lba_return     = getFirmaMasivaBusiness()
																                 .reemplazarBookmarsFirma(
																    lba_return,
																    MarcaAgua.crearImagenConTexto(
																        ls_idCorrespondencia,
																        ls_idCorrespondencia.length() * li_temp,
																        li_fontSize, li_fontSize
																    ), 0, 0, "REFERENCIA_SALIDA", false
																);
														lba_return     = getFirmaMasivaBusiness()
																                 .reemplazarBookmarsFirma(
																    lba_return,
																    MarcaAgua.crearImagenConTexto(
																        ls_idCorrespondencia,
																        ls_idCorrespondencia.length() * li_temp,
																        li_fontSize, li_fontSize
																    ), 0, 0, "REF_SAL", false
																);
													}
												}
											}
										}
									}
								}

								if(ab_salvar)
								{
									Imagenes li_imagenes;
									long     ll_idImagenTemp;

									li_imagenes = new Imagenes();

									li_imagenes.setTipoArchivo(ExtensionCommon.PDF_MAYUS);
									li_imagenes.setIdUsuarioCreacion(as_userId);
									li_imagenes.setIpCreacion(as_remoteIp);
									li_imagenes.setImagenBlob(lba_return);
									li_imagenes.setCodigoVerificacion(ls_codigoVerificacion);

									ll_idImagenTemp = li_DAO.insertOrUpdate(li_imagenes, true);

									if(ll_idImagenTemp > 0)
									{
										DocumentosSalida lds_documentoSalida;
										Long             ll_idImagen;
										boolean          lb_b = false;

										lds_documentoSalida     = new DocumentosSalida();
										ll_idImagen             = NumericUtils.getLongWrapper(ll_idImagenTemp);

										lds_documentoSalida.setIdSolicitud(ls_idSolicitud);
										lds_documentoSalida.setIdTurno(ls_idTurno);
										lds_documentoSalida.setTipo(ls_tipoArchivo);
										lds_documentoSalida.setEstado(EstadoCommon.ACTIVO);

										lds_documentoSalida = lds_DAO.findDocumentByTurnoTipoEstadoReferenciaSalida(
											    lds_documentoSalida, false
											);

										if(lds_documentoSalida != null)
										{
											lds_documentoSalida.setIdImagen(ll_idImagen);
											lds_documentoSalida.setConsecutivoResolucion(ll_NumeroResolucion);
											lds_documentoSalida.setFechaResolucion(ld_fechaResol);
											lds_documentoSalida.setConsecutivoOficio(ls_consecutivoOficio);
											lds_documentoSalida.setFechaOficio(ld_fechaOficio);
											lds_documentoSalida.setReferenciaSalida(ls_idCorrespondencia);
											lds_documentoSalida.setIdEcm(null);
											lds_documentoSalida.setFechaEnvio(null);
											lds_documentoSalida.setIdNombreDocumento(null);
											lds_documentoSalida.setRepositorio(
											    ab_firma ? RepositorioCommon.FINAL : RepositorioCommon.TEMPORAL
											);
											lds_documentoSalida.setIdUsuarioModificacion(as_userId);
											lds_documentoSalida.setIpModificacion(as_remoteIp);
										}
										else
										{
											lds_documentoSalida = new DocumentosSalida();

											lds_documentoSalida.setIdTurno(ls_idTurno);
											lds_documentoSalida.setIdSolicitud(ls_idSolicitud);
											lds_documentoSalida.setIdImagen(ll_idImagen);
											lds_documentoSalida.setTipo(ls_tipoArchivo);
											lds_documentoSalida.setIdTipoDocumental(ls_tipoDocumental);
											lds_documentoSalida.setEstado(EstadoCommon.ACTIVO);
											lds_documentoSalida.setRepositorio(
											    ab_firma ? RepositorioCommon.FINAL : RepositorioCommon.TEMPORAL
											);
											lds_documentoSalida.setTipoArchivo(ExtensionCommon.PDF_MAYUS);
											lds_documentoSalida.setConsecutivoResolucion(ll_NumeroResolucion);
											lds_documentoSalida.setFechaResolucion(ld_fechaResol);
											lds_documentoSalida.setIdTurnoHistoria(
											    NumericUtils.getInteger(ll_idTurnoHistoria)
											);
											lds_documentoSalida.setConsecutivoOficio(ls_consecutivoOficio);
											lds_documentoSalida.setFechaOficio(ld_fechaOficio);
											lds_documentoSalida.setReferenciaSalida(ls_idCorrespondencia);
											lds_documentoSalida.setIdUsuarioCreacion(as_userId);
											lds_documentoSalida.setIpCreacion(as_remoteIp);
											lb_b = true;
										}

										lds_DAO.insertOrUpdate(lds_documentoSalida, lb_b);
									}
									else
										throw new B2BException(ErrorKeys.NO_SE_GENERO_ARCHIVO);
								}
							}
						}
					}
				}
			}
			catch(B2BException lb2be_e)
			{
				clh_LOGGER.error("generarDocumentoMatriculaOficioAS", lb2be_e);

				throw lb2be_e;
			}
		}

		return lba_return;
	}

	/**
	 * Método encargado de generar archivo en formato pdf  utilizando  las plantillas  PLANTILLA_RESOLUCION_AUTORIZACION_FIRMA_AS y PLANTILLA_NEGACION_AUTORIZACION_FIRMA_AS.
	 *
	 * @param ath_th  Objeto de tipo TurnoHistoria que contiene los datos necesarios para buscar la información a incluir en la plantilla.
	 * @param ab_cargar  Objeto de tipo boolean que contiene los datos necesarios para buscar la información a incluir en la plantilla.
	 * @param as_idUsuario Variable de tipo String que contiene el usuario que realiza la acción.
	 * @param as_localIp Variable de tipo String que contiene la dirección ip local  desde donde seque realiza la acción.
	 * @param as_remoteIp Variable de tipo String que contiene la dirección ip remota desde donde seque realiza la acción.
	 * @return  Arreglo de bytes que contiene la plantilla generada.
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see byte[]
	 */
	public synchronized byte[] generarPdfAutorizacionFirma(
	    TurnoHistoria ath_th, boolean ab_cargar, String as_idUsuario, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		byte[]     lba_archivo;
		DAOManager ldm_manager;

		lba_archivo     = null;
		ldm_manager     = DaoManagerFactory.getDAOManager();

		try
		{
			lba_archivo = generarPdfAutorizacionFirma(
				    ath_th, ab_cargar, false, as_idUsuario, as_localIp, as_remoteIp, ldm_manager
				);
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("generarPdfAutorizacionFirma", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lba_archivo;
	}

	/**
	 * Método encargado de generar archivo en formato pdf  utilizando  las plantillas  PLANTILLA_RESOLUCION_AUTORIZACION_FIRMA_AS y PLANTILLA_NEGACION_AUTORIZACION_FIRMA_AS.
	 *
	 * @param ath_th  Objeto de tipo TurnoHistoria que contiene los datos necesarios para buscar la información a incluir en la plantilla.
	 * @param ab_cargar  Objeto de tipo boolean que contiene los datos necesarios para buscar la información a incluir en la plantilla.
	 * @param ab_firma  Objeto de tipo boolean que valida si se deben llenar los datos de oficio.
	 * @param as_idUsuario Variable de tipo String que contiene el usuario que realiza la acción.
	 * @param as_localIp Variable de tipo String que contiene la dirección ip local  desde donde seque realiza la acción.
	 * @param as_remoteIp Variable de tipo String que contiene la dirección ip remota desde donde seque realiza la acción.
	 * @param adm_manager DAOManager que controla las transacciones de base de datos.
	 * @return  Arreglo de bytes que contiene la plantilla generada.
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see byte[]
	 */
	public synchronized byte[] generarPdfAutorizacionFirma(
	    TurnoHistoria ath_th, boolean ab_cargar, boolean ab_firma, String as_idUsuario, String as_localIp,
	    String as_remoteIp, DAOManager adm_manager
	)
	    throws B2BException
	{
		byte[]     lba_archivo;
		DAOManager ldm_bitacora;

		lba_archivo      = null;
		ldm_bitacora     = DaoManagerFactory.getDAOManager();

		try
		{
			if(ath_th != null)
			{
				long    ll_motivo;
				long    ll_idMotivo;
				boolean lb_motivo;

				ll_motivo       = NumericUtils.getLong(ath_th.getMotivoTramite());
				ll_idMotivo     = NumericUtils.getLong(ath_th.getIdMotivo());
				lb_motivo       = (ll_motivo > 0L)
					? (ll_motivo == MotivoTramiteCommon.GENERAR_RESOLUCION_AUTORIZACION_FIRMA_LIBROS_AS)
					: ((ll_idMotivo > 0L)
					? (ll_idMotivo == MotivoTramiteCommon.GENERAR_RESOLUCION_AUTORIZACION_FIRMA_LIBROS_AS) : false);

				if(!ab_cargar)
				{
					String              ls_plantilla;
					Map<String, String> lmss_datos;

					ls_plantilla     = null;
					lmss_datos       = null;

					{
						Constantes lc_constante;
						String     ls_constante;

						ls_constante     = lb_motivo ? ConstanteCommon.PLANTILLA_RESOLUCION_AUTORIZACION_FIRMA_AS
							                         : ConstanteCommon.PLANTILLA_NEGACION_AUTORIZACION_FIRMA_AS;
						lc_constante     = DaoCreator.getConstantesDAO(adm_manager).findByIdWithImage(ls_constante);

						if(lc_constante != null)
						{
							byte[] lba_plantilla;

							lba_plantilla = lc_constante.getImagenes();

							if(lba_plantilla == null)
								throw new B2BException(ErrorKeys.NO_IMAGEN_CONSTANTE);

							ls_plantilla = new String(lba_plantilla);
						}
						else
						{
							Object[] loa_args;

							loa_args     = new String[1];

							loa_args[0] = ls_constante;

							throw new B2BException(ErrorKeys.ERROR_SIN_CONSTANTE, loa_args);
						}
					}

					lmss_datos       = finalizarPlantilla(ls_plantilla, null, adm_manager);
					ls_plantilla     = lmss_datos.get(IdentificadoresCommon.PLANTILLA);

					lba_archivo = new PDFGenerator().convertirRTFToPDF(ls_plantilla.getBytes(), adm_manager);
				}
				else
				{
					lba_archivo = lb_motivo
						? generarResolucionAutorizacionFirma(
						    ath_th, as_idUsuario, as_localIp, as_remoteIp, adm_manager, lb_motivo
						)
						: generarNegacionAutorizacionFirma(
						    ath_th, ab_cargar, ab_firma, as_idUsuario, as_localIp, as_remoteIp, adm_manager
						);

					{
						DocumentosSalida lds_documentosSalida;
						Turno            lt_turno;

						lds_documentosSalida     = new DocumentosSalida();
						lt_turno                 = DaoCreator.getTurnoDAO(adm_manager).findById(ath_th.getIdTurno());

						if(lt_turno != null)
						{
							Solicitud ls_solicitud;

							ls_solicitud = DaoCreator.getSolicitudDAO(adm_manager).findById(lt_turno.getIdSolicitud());

							if(ls_solicitud != null)
							{
								String ls_idPersona;
								String ls_idDireccion;
								String ls_idCorreoElectronico;
								String ls_idTelefono;

								ls_idPersona               = ls_solicitud.getIdPersona();
								ls_idDireccion             = ls_solicitud.getIdDireccion();
								ls_idCorreoElectronico     = ls_solicitud.getIdCorreoElectronico();
								ls_idTelefono              = ls_solicitud.getIdTelefono();

								if(StringUtils.isValidString(ls_idPersona))
								{
									Persona lp_persona;

									lp_persona = DaoCreator.getPersonaDAO(adm_manager).findById(ls_idPersona);

									if(lp_persona != null)
									{
										String ls_nombreCompleto;
										String ls_primerNombre;
										String ls_segundoNombre;
										String ls_primerApellido;
										String ls_segundoApellido;

										ls_nombreCompleto      = IdentificadoresCommon.DATO_INVALIDO;
										ls_primerNombre        = lp_persona.getPrimerNombre();
										ls_segundoNombre       = lp_persona.getSegundoNombre();
										ls_primerApellido      = lp_persona.getPrimerApellido();
										ls_segundoApellido     = lp_persona.getSegundoApellido();

										if(StringUtils.isValidString(ls_primerNombre))
											ls_nombreCompleto = ls_nombreCompleto + ls_primerNombre
												+ IdentificadoresCommon.ESPACIO_VACIO;

										if(StringUtils.isValidString(ls_segundoNombre))
											ls_nombreCompleto = ls_nombreCompleto + ls_segundoNombre
												+ IdentificadoresCommon.ESPACIO_VACIO;

										if(StringUtils.isValidString(ls_primerApellido))
											ls_nombreCompleto = ls_nombreCompleto + ls_primerApellido
												+ IdentificadoresCommon.ESPACIO_VACIO;

										if(StringUtils.isValidString(ls_segundoApellido))
											ls_nombreCompleto = ls_nombreCompleto + ls_segundoApellido
												+ IdentificadoresCommon.ESPACIO_VACIO;

										lds_documentosSalida.setDestinatario(ls_nombreCompleto);
									}

									if(StringUtils.isValidString(ls_idDireccion))
									{
										PersonaDireccion lpd_personaDireccion;

										lpd_personaDireccion = DaoCreator.getPersonaDireccionDAO(adm_manager)
												                             .findById(ls_idPersona, ls_idDireccion);

										if(lpd_personaDireccion != null)
										{
											lds_documentosSalida.setDireccion(lpd_personaDireccion.getDireccion());
											lds_documentosSalida.setIdPais(
											    IdentificadoresCommon.INDICATIVO_PAIS_DEFAULT
											);
											lds_documentosSalida.setIdDepartamento(
											    lpd_personaDireccion.getIdDepartamento()
											);
											lds_documentosSalida.setIdMunicipio(lpd_personaDireccion.getIdMunicipio());
										}
									}

									if(StringUtils.isValidString(ls_idCorreoElectronico))
									{
										PersonaCorreoElectronico lpc_personaCorreo;

										lpc_personaCorreo = DaoCreator.getPersonaCorreoElectronicoDAO(adm_manager)
												                          .findById(
												    ls_idPersona, ls_idCorreoElectronico
												);

										if(lpc_personaCorreo != null)
											lds_documentosSalida.setCorreoElectronico(
											    lpc_personaCorreo.getCorreoElectronico()
											);
									}

									if(StringUtils.isValidString(ls_idTelefono))
									{
										PersonaTelefono lpt_personaTelefono;

										lpt_personaTelefono = DaoCreator.getPersonaTelefonoDAO(adm_manager)
												                            .findById(ls_idPersona, ls_idTelefono);

										if(lpt_personaTelefono != null)
											lds_documentosSalida.setTelefono(lpt_personaTelefono.getTelefono());
									}
								}

								if(lba_archivo != null)
								{
									Imagenes li_imagen;
									long     ll_idImagen;

									li_imagen = new Imagenes();

									li_imagen.setTipoArchivo(ExtensionCommon.PDF_MAYUS);
									li_imagen.setImagenBlob(lba_archivo);

									li_imagen.setIdUsuarioCreacion(as_idUsuario);
									li_imagen.setIpCreacion(as_remoteIp);
									ll_idImagen = NumericUtils.getLong(
										    DaoCreator.getImagenesDAO(adm_manager).insertOrUpdate(li_imagen, true)
										);
									lds_documentosSalida.setIdImagen(NumericUtils.getLongWrapper(ll_idImagen));
								}

								{
									lds_documentosSalida.setIdPais(IdentificadoresCommon.INDICATIVO_PAIS_DEFAULT);
									lds_documentosSalida.setTipoArchivo(IdentificadoresCommon.PDF_TXT);
									lds_documentosSalida.setIdTipoDocumental(
									    lb_motivo ? TipoDocumentalCommon.RESOLUCION : TipoDocumentalCommon.OFICIO
									);
									lds_documentosSalida.setTipo(
									    lb_motivo ? "RESOLUCION_FIRMA_AS" : "COMUNICADO_NEGACION_FIRMA"
									);
									lds_documentosSalida.setEstado(EstadoCommon.A);
									lds_documentosSalida.setDocumentoAutomatico(EstadoCommon.S);
									lds_documentosSalida.setRepositorio(RepositorioCommon.FINAL);
									lds_documentosSalida.setDocumentoFinal(EstadoCommon.S);
									lds_documentosSalida.setIdUsuarioCreacion(as_idUsuario);
									lds_documentosSalida.setIpCreacion(as_remoteIp);
									lds_documentosSalida.setIdTurnoHistoria(
									    NumericUtils.getInteger(ath_th.getIdTurnoHistoria())
									);

									if(lb_motivo)
									{
										lds_documentosSalida.setConsecutivoResolucion(
										    NumericUtils.getLongWrapper(
										        ath_th.getConsecutivoResolucionAntiguoSistema()
										    )
										);
										lds_documentosSalida.setFechaResolucion(new Date());
									}
									else
									{
										lds_documentosSalida.setConsecutivoOficio(
										    ath_th.getConsecutivoOficioAntiguoSistema()
										);
										lds_documentosSalida.setFechaOficio(new Date());
									}

									lds_documentosSalida.setIdTurno(lt_turno.getIdTurno());
									lds_documentosSalida.setIdSolicitud(ls_solicitud.getIdSolicitud());
								}

								if(lds_documentosSalida != null)
								{
									long                ll_idDocumentosSalida;
									DocumentosSalidaDAO ldsd_documentoSalidaDAO;

									ldsd_documentoSalidaDAO     = DaoCreator.getDocumentosSalidaDAO(adm_manager);
									ll_idDocumentosSalida       = ldsd_documentoSalidaDAO.insertOrUpdate(
										    lds_documentosSalida, true
										);

									if(ll_idDocumentosSalida > 0)
									{
										DocumentosSalida lds_documentoEnviar;

										lds_documentoEnviar = ldsd_documentoSalidaDAO.findById(ll_idDocumentosSalida);

										if(lds_documentoEnviar != null)
										{
											ConstantesDAO lcd_DAO;
											String        ls_endpoint;

											lcd_DAO         = DaoCreator.getConstantesDAO(adm_manager);
											ls_endpoint     = lcd_DAO.findString(
												    ConstanteCommon.JOB_ENVIO_GESTOR_DOCUMENTAL_ENDPOINT
												);

											if(StringUtils.isValidString(ls_endpoint))
											{
												BitacoraProcesoDAO lbpd_bitacora;

												lbpd_bitacora = DaoCreator.getBitacoraProcesoDAO(ldm_bitacora);

												ldm_bitacora.setAutoCommit(true);

												enviarGestorDocumental(
												    lds_documentoEnviar, lbpd_bitacora, ls_endpoint, as_idUsuario,
												    as_remoteIp, adm_manager
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
		catch(B2BException lb2be_e)
		{
			ldm_bitacora.setRollbackOnly();

			clh_LOGGER.error("generarPdfAutorizacionFirma", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_bitacora.close();
		}

		return lba_archivo;
	}

	/**
	 * Método encargado de generar archivo en formato pdf  utilizando  las plantillas  PLANTILLA_SOLICITUD_COMPLEMENTACION.
	 *
	 * @param aahr_parametros  Objeto de tipo AmpliacionHistoriaRegistral que contiene los datos necesarios para buscar la información a incluir en la plantilla.
	 * @param as_idUsuario Variable de tipo String que contiene el usuario que realiza la acción.
	 * @param as_localIp Variable de tipo String que contiene la dirección ip local  desde donde seque realiza la acción.
	 * @param as_remoteIp Variable de tipo String que contiene la dirección ip remota desde donde seque realiza la acción.
	 * @return  Arreglo de bytes que contiene la plantilla generada.
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see byte[]
	 */
	public synchronized byte[] generarPdfSolicitudComplementacion(
	    AmpliacionHistoriaRegistral aahr_parametros, String as_idUsuario, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		byte[]     lba_archivo;
		DAOManager ldm_manager;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		lba_archivo     = null;

		try
		{
			lba_archivo = generarSolicitudComplementacion(
				    aahr_parametros, ldm_manager, as_idUsuario, as_localIp, as_remoteIp, false, false
				);
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("accionSalvar", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lba_archivo;
	}

	/**
	 * Generar pdf solicitud complementacion.
	 *
	 * @param ath_th correspondiente al valor de ath th
	 * @param ab_cargar correspondiente al valor de ab cargar
	 * @param as_idUsuario correspondiente al valor de as id usuario
	 * @param as_localIp correspondiente al valor de as local ip
	 * @param as_remoteIp correspondiente al valor de as remote ip
	 * @return el valor de byte[]
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public synchronized byte[] generarPdfSolicitudComplementacionOfiDestino(
	    String ath_th, boolean ab_cargar, String as_idUsuario, String as_localIp
	)
	    throws B2BException
	{
		byte[]     lba_archivo;
		DAOManager ldm_manager;

		lba_archivo     = null;
		ldm_manager     = DaoManagerFactory.getDAOManager();

		try
		{
			lba_archivo = generarSolicitudComplementacionOfiDestino(
				    ath_th, ab_cargar, false, as_idUsuario, as_localIp, ldm_manager
				);
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("generarPdfSolicitudComplementacion", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lba_archivo;
	}

	/**
	 * Método encargado de generar archivo en formato pdf  utilizando  las plantillas  PLANTILLA_SOLICITUD_FIRMA_LIBRO_AS.
	 *
	 * @param adas_das  Objeto de tipo DatosAntSistema que contiene los datos necesarios para buscar la información a incluir en la plantilla.
	 * @param amso_mso  Objeto de tipo Map<String, Object> que contiene los datos necesarios para buscar la información a incluir en la plantilla.
	 * @param ab_vieneDeEtapaAntSis  Objeto de boolean que contiene los datos necesarios para buscar la información a incluir en la plantilla.
	 * @param as_idUsuario Variable de tipo String que contiene el usuario que realiza la acción.
	 * @param as_localIp Variable de tipo String que contiene la dirección ip local  desde donde seque realiza la acción.
	 * @param as_remoteIp Variable de tipo String que contiene la dirección ip remota desde donde seque realiza la acción.
	 * @return  Arreglo de bytes que contiene la plantilla generada.
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see byte[]
	 */
	public synchronized byte[] generarPdfSolicitudFirma(
	    DatosAntSistema adas_das, Map<String, Object> amso_mso, boolean ab_vieneDeEtapaAntSis, String as_idUsuario,
	    String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		byte[]     lba_archivo;
		DAOManager ldm_manager;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		lba_archivo     = null;

		try
		{
			lba_archivo = generarPdfSolicitudFirma(
				    adas_das, amso_mso, ab_vieneDeEtapaAntSis, as_idUsuario, as_localIp, as_remoteIp, ldm_manager
				);
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("generarPdfSolicitudFirma", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lba_archivo;
	}

	/**
	 * Método encargado de generar archivo en formato pdf  utilizando  las plantillas  PLANTILLA_SOLICITUD_FIRMA_LIBRO_AS.
	 *
	 * @param adas_das  Objeto de tipo DatosAntSistema que contiene los datos necesarios para buscar la información a incluir en la plantilla.
	 * @param amso_mso  Objeto de tipo Map<String, Object> que contiene los datos necesarios para buscar la información a incluir en la plantilla.
	 * @param ab_vieneDeEtapaAntSis  Objeto de boolean que contiene los datos necesarios para buscar la información a incluir en la plantilla.
	 * @param as_idUsuario Variable de tipo String que contiene el usuario que realiza la acción.
	 * @param as_localIp Variable de tipo String que contiene la dirección ip local  desde donde seque realiza la acción.
	 * @param as_remoteIp Variable de tipo String que contiene la dirección ip remota desde donde seque realiza la acción.
	 * @param adm_manager Conexión a la base de datos.
	 * @return Arreglo de bytes que contiene la plantilla generada.
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see byte[]
	 */
	public synchronized byte[] generarPdfSolicitudFirma(
	    DatosAntSistema adas_das, Map<String, Object> amso_mso, boolean ab_vieneDeEtapaAntSis, String as_idUsuario,
	    String as_localIp, String as_remoteIp, DAOManager adm_manager
	)
	    throws B2BException
	{
		byte[]     lba_archivo;
		DAOManager ldm_bitacora;

		ldm_bitacora     = DaoManagerFactory.getDAOManager();
		lba_archivo      = null;

		try
		{
			lba_archivo = generarSolicitudFirma(
				    adas_das, amso_mso, ab_vieneDeEtapaAntSis, as_idUsuario, as_localIp, as_remoteIp, adm_manager
				);

			{
				DocumentosSalida lds_documentosSalida;
				String           ls_idTurno;

				ls_idTurno               = StringUtils.getString(amso_mso.get(IdentificadoresCommon.ID_TURNO));
				lds_documentosSalida     = new DocumentosSalida();

				if(CollectionUtils.isValidCollection(amso_mso) && (adas_das != null))
				{
					String ls_idSolicitud;

					ls_idSolicitud = StringUtils.getString(amso_mso.get(IdentificadoresCommon.ID_SOLICITUD));

					if(StringUtils.isValidString(ls_idSolicitud))
					{
						Solicitud ls_solicitud;

						ls_solicitud = DaoCreator.getSolicitudDAO(adm_manager).findById(ls_idSolicitud);

						if(ls_solicitud != null)
						{
							String ls_idPersona;
							String ls_idDireccion;
							String ls_idCorreoElectronico;
							String ls_idTelefono;

							ls_idPersona               = ls_solicitud.getIdPersona();
							ls_idDireccion             = ls_solicitud.getIdDireccion();
							ls_idCorreoElectronico     = ls_solicitud.getIdCorreoElectronico();
							ls_idTelefono              = ls_solicitud.getIdTelefono();

							if(StringUtils.isValidString(ls_idPersona))
							{
								Persona lp_persona;

								lp_persona = DaoCreator.getPersonaDAO(adm_manager).findById(ls_idPersona);

								if(lp_persona != null)
								{
									String ls_nombreCompleto;
									String ls_primerNombre;
									String ls_segundoNombre;
									String ls_primerApellido;
									String ls_segundoApellido;

									ls_nombreCompleto      = IdentificadoresCommon.DATO_INVALIDO;
									ls_primerNombre        = lp_persona.getPrimerNombre();
									ls_segundoNombre       = lp_persona.getSegundoNombre();
									ls_primerApellido      = lp_persona.getPrimerApellido();
									ls_segundoApellido     = lp_persona.getSegundoApellido();

									if(StringUtils.isValidString(ls_primerNombre))
										ls_nombreCompleto = ls_nombreCompleto + ls_primerNombre
											+ IdentificadoresCommon.ESPACIO_VACIO;

									if(StringUtils.isValidString(ls_segundoNombre))
										ls_nombreCompleto = ls_nombreCompleto + ls_segundoNombre
											+ IdentificadoresCommon.ESPACIO_VACIO;

									if(StringUtils.isValidString(ls_primerApellido))
										ls_nombreCompleto = ls_nombreCompleto + ls_primerApellido
											+ IdentificadoresCommon.ESPACIO_VACIO;

									if(StringUtils.isValidString(ls_segundoApellido))
										ls_nombreCompleto = ls_nombreCompleto + ls_segundoApellido
											+ IdentificadoresCommon.ESPACIO_VACIO;

									lds_documentosSalida.setDestinatario(ls_nombreCompleto);
								}

								if(StringUtils.isValidString(ls_idDireccion))
								{
									PersonaDireccion lpd_personaDireccion;

									lpd_personaDireccion = DaoCreator.getPersonaDireccionDAO(adm_manager)
											                             .findById(ls_idPersona, ls_idDireccion);

									if(lpd_personaDireccion != null)
									{
										lds_documentosSalida.setDireccion(lpd_personaDireccion.getDireccion());
										lds_documentosSalida.setIdPais(IdentificadoresCommon.INDICATIVO_PAIS_DEFAULT);
										lds_documentosSalida.setIdDepartamento(
										    lpd_personaDireccion.getIdDepartamento()
										);
										lds_documentosSalida.setIdMunicipio(lpd_personaDireccion.getIdMunicipio());
									}
								}

								if(StringUtils.isValidString(ls_idCorreoElectronico))
								{
									PersonaCorreoElectronico lpc_personaCorreo;

									lpc_personaCorreo = DaoCreator.getPersonaCorreoElectronicoDAO(adm_manager)
											                          .findById(ls_idPersona, ls_idCorreoElectronico);

									if(lpc_personaCorreo != null)
										lds_documentosSalida.setCorreoElectronico(
										    lpc_personaCorreo.getCorreoElectronico()
										);
								}

								if(StringUtils.isValidString(ls_idTelefono))
								{
									PersonaTelefono lpt_personaTelefono;

									lpt_personaTelefono = DaoCreator.getPersonaTelefonoDAO(adm_manager)
											                            .findById(ls_idPersona, ls_idTelefono);

									if(lpt_personaTelefono != null)
										lds_documentosSalida.setTelefono(lpt_personaTelefono.getTelefono());
								}
							}

							if(lba_archivo != null)
							{
								Imagenes li_imagen;
								long     ll_idImagen;

								li_imagen = new Imagenes();

								li_imagen.setTipoArchivo(ExtensionCommon.PDF_MAYUS);
								li_imagen.setImagenBlob(lba_archivo);

								li_imagen.setIdUsuarioCreacion(as_idUsuario);
								li_imagen.setIpCreacion(as_remoteIp);
								ll_idImagen = NumericUtils.getLong(
									    DaoCreator.getImagenesDAO(adm_manager).insertOrUpdate(li_imagen, true)
									);
								lds_documentosSalida.setIdImagen(NumericUtils.getLongWrapper(ll_idImagen));
							}

							{
								lds_documentosSalida.setTipoArchivo(IdentificadoresCommon.PDF_TXT);
								lds_documentosSalida.setIdTipoDocumental(TipoDocumentalCommon.OFICIO);
								lds_documentosSalida.setTipo(TipoArchivoCommon.COMUNICADO_FIRMA_AS);
								lds_documentosSalida.setEstado(EstadoCommon.A);
								lds_documentosSalida.setDocumentoAutomatico(EstadoCommon.S);
								lds_documentosSalida.setIdDatosAntSistema(adas_das.getIdDatosAntSistema());
								lds_documentosSalida.setRepositorio(RepositorioCommon.TEMPORAL);
								lds_documentosSalida.setDocumentoFinal(EstadoCommon.N);
								lds_documentosSalida.setIdUsuarioCreacion(as_idUsuario);
								lds_documentosSalida.setIpCreacion(as_remoteIp);
								lds_documentosSalida.setIdTurno(ls_idTurno);
								lds_documentosSalida.setIdSolicitud(ls_idSolicitud);
							}

							if(lds_documentosSalida != null)
							{
								long                ll_idDocumentosSalida;
								DocumentosSalidaDAO ldsd_documentoSalidaDAO;

								ldsd_documentoSalidaDAO     = DaoCreator.getDocumentosSalidaDAO(adm_manager);
								ll_idDocumentosSalida       = ldsd_documentoSalidaDAO.insertOrUpdate(
									    lds_documentosSalida, true
									);

								if(ll_idDocumentosSalida > 0)
								{
									DocumentosSalida lds_documentoEnviar;

									lds_documentoEnviar = ldsd_documentoSalidaDAO.findById(ll_idDocumentosSalida);

									if(lds_documentoEnviar != null)
									{
										ConstantesDAO lcd_DAO;
										String        ls_endpoint;

										lcd_DAO         = DaoCreator.getConstantesDAO(adm_manager);
										ls_endpoint     = lcd_DAO.findString(
											    ConstanteCommon.JOB_ENVIO_GESTOR_DOCUMENTAL_ENDPOINT
											);

										if(StringUtils.isValidString(ls_endpoint))
										{
											BitacoraProcesoDAO lbpd_bitacora;

											lbpd_bitacora = DaoCreator.getBitacoraProcesoDAO(ldm_bitacora);

											ldm_bitacora.setAutoCommit(true);

											enviarGestorDocumental(
											    lds_documentoEnviar, lbpd_bitacora, ls_endpoint, as_idUsuario,
											    as_remoteIp, adm_manager
											);
										}
									}
								}
							}
						}
					}

					if(StringUtils.isValidString(ls_idTurno))
					{
						TurnoHistoria    lth_turnoHistoria;
						TurnoHistoriaDAO lthd_turnoHistoriaDAO;

						lthd_turnoHistoriaDAO     = DaoCreator.getTurnoHistoriaDAO(adm_manager);
						lth_turnoHistoria         = lthd_turnoHistoriaDAO.findByIdTurno(ls_idTurno);

						if(lth_turnoHistoria != null)
						{
							lth_turnoHistoria.setObservacionesNoTramite(adas_das.getObservacionesFirmaLibro());
							lth_turnoHistoria.setIdUsuarioModificacion(as_idUsuario);
							lth_turnoHistoria.setIpModificacion(as_remoteIp);

							lthd_turnoHistoriaDAO.updateObservacionesNoTramite(lth_turnoHistoria);
						}
					}
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_bitacora.setRollbackOnly();

			clh_LOGGER.error("generarPdfSolicitudFirma", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_bitacora.close();
		}

		return lba_archivo;
	}

	/**
	 * Metodo encargado de guardar los datos a actualizar de datos anotación.
	 *
	 * @param lap_anotacionPredio Argumento de tipo <code>AnotacionPredio</code> que contiene los datos a guardar.
	 * @param as_userId Argumento de tipo <code>String</code> que contiene el usuario que realiza la operación.
	 * @param as_remoteIp Argumento de tipo <code>String</code> que contiene el la ip desde donde se realiza la operación.
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public synchronized void guardarDatosAnotacion(
	    AnotacionPredio lap_anotacionPredio, String as_userId, String as_remoteIp
	)
	    throws B2BException
	{
		DAOManager ldm_manager;

		ldm_manager = DaoManagerFactory.getDAOManager();

		try
		{
			if(lap_anotacionPredio != null)
			{
				validarDatosAnotacion(lap_anotacionPredio);

				{
					AnotacionPredioDAO lapd_DAO;
					AnotacionPredio    lap_tmp;

					lapd_DAO     = DaoCreator.getAccAnotacionPredioDAO(ldm_manager);
					lap_tmp      = lapd_DAO.findByCirculoMatriculaAnotacion(lap_anotacionPredio);

					if(lap_tmp != null)
					{
						lap_tmp.setFechaRadicacion(lap_anotacionPredio.getFechaRadicacion());
						lap_tmp.setRadicacion(lap_anotacionPredio.getRadicacion());
						lap_tmp.setIdEstadoAnotacion(lap_anotacionPredio.getIdEstadoAnotacion());

						lap_tmp.setIdUsuarioModificacion(as_userId);
						lap_tmp.setIpModificacion(as_remoteIp);

						lapd_DAO.updateAnotacion(lap_tmp);
					}
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("guardarDatosAnotacion", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}
	}

	/**
	 * Metodo encargado de guardar los datos a actualizar de datos ant sistema.
	 *
	 * @param aap_anotacionPredio Argumento de tipo <code>AnotacionPredio</code> que contiene los datos a validar.
	 * @param adas_detalleAntSistema Argumento de tipo <code>DetalleAntSistema</code> que contiene los datos a guardar.
	 * @param aa_anotacion Argumento de tipo <code>Anotacion</code> que contiene los datos a validar.
	 * @param aa_iterador Argumento de tipo <code>Anotacion</code> que contiene los datos a validar.
	 * @param as_userId Argumento de tipo <code>String</code> que contiene el usuario que realiza la operación.
	 * @param as_remoteIp Argumento de tipo <code>String</code> que contiene el la ip desde donde se realiza la operación.
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public synchronized void guardarDatosAntSistema(
	    AnotacionPredio aap_anotacionPredio, DetalleAntSistema adas_detalleAntSistema, Anotacion aa_anotacion,
	    Anotacion aa_iterador, String as_userId, String as_remoteIp
	)
	    throws B2BException
	{
		DAOManager ldm_manager;

		ldm_manager = DaoManagerFactory.getDAOManager();

		try
		{
			if(aap_anotacionPredio != null)
			{
				AnotacionPredio lap_tmp;

				lap_tmp = validarDetalleAntSistema(adas_detalleAntSistema, aa_anotacion, aa_iterador);

				if(lap_tmp != null)
				{
					AnotacionPredioDAO lapd_DAO;

					String             ls_IdDatosAntSistema;
					String             ls_IdDetalleAntSistema;

					ls_IdDatosAntSistema       = lap_tmp.getIdDatosAntSistema();
					ls_IdDetalleAntSistema     = lap_tmp.getIdDetalleAntSistema();

					lapd_DAO     = DaoCreator.getAccAnotacionPredioDAO(ldm_manager);
					lap_tmp      = lapd_DAO.findByCirculoMatriculaAnotacion(aap_anotacionPredio);

					if(lap_tmp != null)
					{
						lap_tmp.setIdDatosAntSistema(ls_IdDatosAntSistema);
						lap_tmp.setIdDetalleAntSistema(ls_IdDetalleAntSistema);

						lap_tmp.setIdUsuarioModificacion(as_userId);
						lap_tmp.setIpModificacion(as_remoteIp);

						lapd_DAO.updateAnotacion(lap_tmp);
					}
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("guardarDatosAnotacion", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}
	}

	/**
	 * Metodo encargado de guardar los datos a actualizar de datos documento anotación.
	 *
	 * @param ad_documento Argumento de tipo <code>Documento</code> que contiene los datos a guardar.
	 * @param as_userId Argumento de tipo <code>String</code> que contiene el usuario que realiza la operación.
	 * @param as_remoteIp Argumento de tipo <code>String</code> que contiene el la ip desde donde se realiza la operación.
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public synchronized void guardarDatosDocumentoAnotacion(
	    Documento ad_documento, String as_userId, String as_remoteIp
	)
	    throws B2BException
	{
		DAOManager ldm_manager;

		ldm_manager = DaoManagerFactory.getDAOManager();

		try
		{
			if(ad_documento != null)
				actualizarOCrearDocumentoAnotacion(ad_documento, true, ldm_manager, as_userId, as_remoteIp);
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("guardarDatosDocumentoAnotacion", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}
	}

	/**
	 * Metodo encargado de guardar los datos a actualizar de datos anotación.
	 *
	 * @param aap_anotacionPredio Argumento de tipo <code>AnotacionPredio</code> que contiene los datos a guardar.
	 * @param al_idAnotacion correspondiente al valor del tipo de objeto Long
	 * @param aa_anotacion correspondiente al valor del tipo de objeto Anotacion
	 * @param ath_turnoHistoria correspondiente al valor del tipo de objeto TurnoHistoria
	 * @param as_userId Argumento de tipo <code>String</code> que contiene el usuario que realiza la operación.
	 * @param as_remoteIp Argumento de tipo <code>String</code> que contiene el la ip desde donde se realiza la operación.
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public synchronized void guardarDatosEspecificacionAnotacion(
	    AnotacionPredio aap_anotacionPredio, Long al_idAnotacion, Anotacion aa_anotacion,
	    TurnoHistoria ath_turnoHistoria, String as_userId, String as_remoteIp
	)
	    throws B2BException
	{
		DAOManager ldm_manager;

		ldm_manager = DaoManagerFactory.getDAOManager();

		try
		{
			if((aap_anotacionPredio != null) && (ath_turnoHistoria != null))
			{
				ath_turnoHistoria = DaoCreator.getTurnoHistoriaDAO(ldm_manager).findById(ath_turnoHistoria);

				if(ath_turnoHistoria != null)
				{
					AnotacionCancelacionDAO lacd_DAO;
					NaturalezaJuridicaDAO   lnjd_DAO;
					AnotacionPredioDAO      lapd_DAO;

					lacd_DAO     = DaoCreator.getAnotacionCancelacionDAO(ldm_manager);
					lnjd_DAO     = DaoCreator.getNaturalezaJuridicaDAO(ldm_manager);
					lapd_DAO     = DaoCreator.getAccAnotacionPredioDAO(ldm_manager);

					{
						AnotacionPredio lap_tmp;

						lap_tmp = validarYCrearAnotacionCancelacion(
							    aap_anotacionPredio, NumericUtils.getLong(al_idAnotacion), lacd_DAO, lnjd_DAO, lapd_DAO,
							    aa_anotacion, ath_turnoHistoria, as_userId, as_remoteIp
							);

						if(lap_tmp != null)
						{
							aap_anotacionPredio.setIdNaturalezaJuridica(lap_tmp.getIdNaturalezaJuridica());
							aap_anotacionPredio.setVersion(lap_tmp.getVersion());

							lap_tmp = lapd_DAO.findByCirculoMatriculaAnotacion(aap_anotacionPredio);

							if(lap_tmp != null)
							{
								lap_tmp.setEspecificacion(aap_anotacionPredio.getEspecificacion());
								lap_tmp.setIdNaturalezaJuridica(aap_anotacionPredio.getIdNaturalezaJuridica());
								lap_tmp.setVersion(aap_anotacionPredio.getVersion());
								lap_tmp.setComentario(aap_anotacionPredio.getComentario());
								lap_tmp.setValor(aap_anotacionPredio.getValor());

								lap_tmp.setIdUsuarioModificacion(as_userId);
								lap_tmp.setIpModificacion(as_remoteIp);

								lapd_DAO.updateAnotacion(lap_tmp);
							}
						}
					}
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("guardarDatosAnotacion", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}
	}

	/**
	 * Método encargado de salvar el informe de busqueda en antiguo sistema.
	 *
	 * @param as_observaciones correspondiente al valor del tipo de objeto String
	 * @param amsb_archivos Colección que contiene los documentos asociados al informe de búsqueda.
	 * @param ad_documento Documento salida el cual se va a guardar.
	 * @param as_userId Variable de tipo String que contiene el id del usuario que está realizando el proceso.
	 * @param as_localIpAddress Variable de tipo String que contiene la ip del servidor.
	 * @param as_ipRemote Variable de tipo String que contiene la ip del usuario que está realizando el proceso.
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public synchronized void guardarInformeDeBusqueda(
	    String as_observaciones, Map<String, byte[]> amsb_archivos, DocumentosSalida ad_documento, String as_userId,
	    String as_localIpAddress, String as_ipRemote
	)
	    throws B2BException
	{
		DAOManager ldm_manager;

		ldm_manager = DaoManagerFactory.getDAOManager();

		try
		{
			if(ad_documento == null)
				throw new B2BException(ErrorKeys.ERROR_INTERNO_SISTEMA);

			if(CollectionUtils.isValidCollection(amsb_archivos))
				guardarDocumentosPdf(
				    ad_documento, amsb_archivos, ldm_manager, as_userId, as_localIpAddress, as_ipRemote
				);

			{
				DatosAntSistema    ldas_datosTemp;
				DatosAntSistemaDAO ldasd_datosAntDAO;
				String             ls_idDatosAntSistema;

				ldas_datosTemp           = new DatosAntSistema();
				ldasd_datosAntDAO        = DaoCreator.getDatosAntSistemaDAO(ldm_manager);
				ls_idDatosAntSistema     = ad_documento.getIdDatosAntSistema();

				ldas_datosTemp.setIdDatosAntSistema(ls_idDatosAntSistema);

				ldas_datosTemp = ldasd_datosAntDAO.findById(ldas_datosTemp);

				if(ldas_datosTemp != null)
				{
					String ls_accion;

					ls_accion = ldas_datosTemp.getAccion();

					if(StringUtils.isValidString(ls_accion))
					{
						if(ls_accion.equalsIgnoreCase(AccionAntSistemaCommon.ASOCIAR_MATRICULAS))
							ldas_datosTemp.setAccion(AccionAntSistemaCommon.ASOCIAR_MATRICULA_E_INFORME);
						else if(ls_accion.equalsIgnoreCase(AccionAntSistemaCommon.CREAR_MATRICULAS))
							ldas_datosTemp.setAccion(AccionAntSistemaCommon.CREAR_MATRICULA_E_INFORME);
					}
					else
						ldas_datosTemp.setAccion(AccionAntSistemaCommon.INFORME_RESULTADOS);

					ldas_datosTemp.setRevisadoAntSistema(EstadoCommon.S);
					ldas_datosTemp.setIdUsuarioModificacion(as_userId);
					ldas_datosTemp.setIpModificacion(as_ipRemote);

					ldasd_datosAntDAO.updateAccionAntSistema(ldas_datosTemp);
					ldasd_datosAntDAO.updateRevisionAntSistema(ldas_datosTemp);
				}
			}

			{
				TurnoHistoriaDAO lthd_turnoHistoriaDAO;
				TurnoHistoria    lth_temp;

				lthd_turnoHistoriaDAO     = DaoCreator.getTurnoHistoriaDAO(ldm_manager);
				lth_temp                  = new TurnoHistoria();

				lth_temp.setIdTurnoHistoria(NumericUtils.getLongWrapper(ad_documento.getIdTurnoHistoria()));

				lth_temp = lthd_turnoHistoriaDAO.findById(lth_temp);

				if(lth_temp == null)
					throw new B2BException(ErrorKeys.NO_SE_ENCONTRO_ID_TURNO_HISTORIA);

				if(StringUtils.isValidString(as_observaciones) && (as_observaciones.length() > 4000))
					throw new B2BException(ErrorKeys.ERROR_OBSERVACIONES_TAM_4000);

				lth_temp.setObservaciones(as_observaciones);
				lth_temp.setIdUsuarioModificacion(as_userId);
				lth_temp.setIpModificacion(as_ipRemote);

				lthd_turnoHistoriaDAO.updateObservaciones(lth_temp);
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("guardarInformeDeBusqueda", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}
	}

	/**
	 * Metodo encargado de guardar los datos a actualizar de datos de los intervinientes de la anotación.
	 *
	 * @param aa_anotacion Argumento de tipo <code>Anotacion</code> que contiene los datos a guardar.
	 * @param aa_anotacionPredioCiudadano Argumento de tipo <code>AnotacionPredioCiudadano</code> que contiene los datos a guardar.
	 * @param as_idSolicitud Argumento de tipo <code>String</code> que contiene el id solicitud.
	 * @param as_idAnotacionPredio Argumento de tipo <code>String</code> que contiene el id anotacion predio.
	 * @param as_userId Argumento de tipo <code>String</code> que contiene el usuario que realiza la operación.
	 * @param as_remoteIp Argumento de tipo <code>String</code> que contiene el la ip desde donde se realiza la operación.
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public synchronized void guardarIntervinientesAnotacion(
	    Anotacion aa_anotacion, AnotacionPredioCiudadano aa_anotacionPredioCiudadano, String as_idSolicitud,
	    String as_idAnotacionPredio, String as_userId, String as_remoteIp
	)
	    throws B2BException
	{
		DAOManager ldm_manager;

		ldm_manager = DaoManagerFactory.getDAOManager();

		try
		{
			if(aa_anotacion != null)
			{
				PersonaDAO                  lpd_DAO;
				AnotacionPredioCiudadanoDAO lapcd_DAO;
				ConstantesDAO               lcd_DAO;
				SolicitudIntervinienteDAO   lsid_DAO;
				SolicitudDAO                lsd_DAO;

				lpd_DAO       = DaoCreator.getPersonaDAO(ldm_manager);
				lapcd_DAO     = DaoCreator.getAccAnotacionPredioCiudadanoDAO(ldm_manager);
				lcd_DAO       = DaoCreator.getConstantesDAO(ldm_manager);
				lsid_DAO      = DaoCreator.getSolicitudIntervinienteDAO(ldm_manager);
				lsd_DAO       = DaoCreator.getSolicitudDAO(ldm_manager);

				validarActualizarOCrearIntervinientes(
				    ldm_manager, aa_anotacion, aa_anotacionPredioCiudadano, lapcd_DAO, lpd_DAO, lcd_DAO, lsid_DAO,
				    lsd_DAO, as_idSolicitud, as_idAnotacionPredio, as_userId, as_remoteIp
				);
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("guardarIntervinientesAnotacion", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}
	}

	/**
	 * Inactiva los documentos asociados a un informe de resultados para un turno específico.
	 *
	 * @param as_idTurno id del turno al cual se van a inactivar los documentos
	 * @param as_idDatosAntSistema id del predio al cual se le inactivan los documentos asociados a informe de resultados
	 * @param as_userId Id del usuario que ejecuta la acción
	 * @param as_remoteIp Dirección IP del cliente que ejecuta la acción
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public synchronized void inactivarInformeResultados(
	    String as_idTurno, String as_idDatosAntSistema, String as_userId, String as_remoteIp
	)
	    throws B2BException
	{
		DAOManager ldm_manager;

		ldm_manager = DaoManagerFactory.getDAOManager();

		try
		{
			if(!StringUtils.isValidString(as_idTurno))
				throw new B2BException(ErrorKeys.TURNO_INVALIDO);

			Constantes lc_constante;

			lc_constante = DaoCreator.getConstantesDAO(ldm_manager)
					                     .findById(ConstanteCommon.TIPO_DOCUMENTAL_INFORME_BUSQUEDA);

			if(lc_constante != null)
			{
				TipoDocumental ltd_tipoDoc;

				ltd_tipoDoc = new TipoDocumental();

				ltd_tipoDoc.setIdTipoDocumental(lc_constante.getCaracter());

				ltd_tipoDoc = DaoCreator.getTipoDocumentalDAO(ldm_manager).findById(ltd_tipoDoc);

				if(ltd_tipoDoc != null)
				{
					Collection<DocumentosSalida> lcds_documentos;
					DocumentosSalida             lds_tmp;
					DocumentosSalidaDAO          ldsd_documentosSalidaDAO;

					lds_tmp                      = new DocumentosSalida();
					ldsd_documentosSalidaDAO     = DaoCreator.getDocumentosSalidaDAO(ldm_manager);

					lds_tmp.setIdTurno(as_idTurno);
					lds_tmp.setTipo(ltd_tipoDoc.getNombre());

					lcds_documentos = ldsd_documentosSalidaDAO.findByIdTurnoTipo(lds_tmp);

					if(CollectionUtils.isValidCollection(lcds_documentos))
					{
						ImagenesDAO li_DAO;

						li_DAO = DaoCreator.getImagenesDAO(ldm_manager);

						for(DocumentosSalida lds_data : lcds_documentos)
						{
							if(lds_data != null)
							{
								lds_data.setEstado(EstadoCommon.INACTIVO);
								lds_data.setIdUsuarioModificacion(as_userId);
								lds_data.setIpModificacion(as_remoteIp);

								ldsd_documentosSalidaDAO.insertOrUpdate(lds_data, false);
								li_DAO.eliminarCodigoVerificacion(lds_data.getIdImagen());
							}
						}

						DatosAntSistema    ldas_datosAnt;
						DatosAntSistemaDAO ldasd_datosAntDAO;

						ldas_datosAnt         = new DatosAntSistema();
						ldasd_datosAntDAO     = DaoCreator.getDatosAntSistemaDAO(ldm_manager);

						ldas_datosAnt.setIdDatosAntSistema(as_idDatosAntSistema);

						ldas_datosAnt = ldasd_datosAntDAO.findById(ldas_datosAnt);

						if(ldas_datosAnt != null)
						{
							String ls_accion;

							ls_accion = StringUtils.getStringNotNull(ldas_datosAnt.getAccion());

							if(ls_accion.equals(AccionAntSistemaCommon.ASOCIAR_MATRICULA_E_INFORME))
								ldas_datosAnt.setAccion(AccionAntSistemaCommon.ASOCIAR_MATRICULAS);
							else if(ls_accion.equals(AccionAntSistemaCommon.CREAR_MATRICULA_E_INFORME))
								ldas_datosAnt.setAccion(AccionAntSistemaCommon.CREAR_MATRICULAS);
							else
								ldas_datosAnt.setAccion(null);

							ldas_datosAnt.setIdUsuarioModificacion(as_userId);
							ldas_datosAnt.setIpModificacion(as_remoteIp);

							ldasd_datosAntDAO.updateAccionAntSistema(ldas_datosAnt);
						}
					}
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("inactivarInformeResultados", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}
	}

	/**
	 * Método encargado de guardar información de antiguo sistema asociandolo con un id solicitud determinado.
	 *
	 * @param adas_temp Objeto de tipo DatosAntSistema que contiene la información necesario para insertar en la tabla SDB_ACC_DATOS_ANT_SISTEMA.
	 * @param as_idTurnoHistoria Variable de tipo String que contiene el id turno historia determinado.
	 * @param as_observaciones Variable de tipo String que contiene las observaciones para el turno historia.
	 * @param as_userId Variable de tipo String que contiene el id del usuario que está realizando el proceso.
	 * @param as_remoteIp Variable de tipo String que contiene la ip del usuario que está realizando el proceso.
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public synchronized void insertUpdateDatosAntSistemaSolicitud(
	    DatosAntSistema adas_temp, String as_idTurnoHistoria, String as_observaciones, String as_userId,
	    String as_remoteIp
	)
	    throws B2BException
	{
		DAOManager ldm_manager;

		ldm_manager = DaoManagerFactory.getDAOManager();

		try
		{
			if((adas_temp != null) && StringUtils.isValidString(as_idTurnoHistoria))
			{
				DatosAntSistemaDAO ldas_DAO;
				int                li_secuencia;
				String             ls_idDatosAntSistema;
				TurnoHistoria      lth_turnoHistoria;
				TurnoHistoriaDAO   lth_DAO;
				UtilDAO            lud_DAO;

				ldas_DAO                 = DaoCreator.getDatosAntSistemaDAO(ldm_manager);
				lud_DAO                  = DaoCreator.getUtilDAO(ldm_manager);
				li_secuencia             = lud_DAO.findSecuence(ConsultasUtilidades.CS_ACC_DATOS_ANT_SISTEMA_SEC);
				lth_turnoHistoria        = new TurnoHistoria();
				lth_DAO                  = DaoCreator.getTurnoHistoriaDAO(ldm_manager);
				ls_idDatosAntSistema     = StringUtils.getString(li_secuencia);

				adas_temp.setIdDatosAntSistema(ls_idDatosAntSistema);
				adas_temp.setAdquisicionPredio("Antes de 1978");
				adas_temp.setIdUsuarioCreacion(as_userId);
				adas_temp.setIpCreacion(as_remoteIp);

				ldas_DAO.insertOrUpdate(adas_temp, true);

				lth_turnoHistoria.setIdTurnoHistoria(NumericUtils.getLongWrapper(as_idTurnoHistoria));

				lth_turnoHistoria = lth_DAO.findById(lth_turnoHistoria);

				if(lth_turnoHistoria != null)
				{
					String ls_idSolicitud;

					ls_idSolicitud = lth_turnoHistoria.getIdSolicitud();

					lth_turnoHistoria.setObservaciones(as_observaciones);

					if(StringUtils.isValidString(ls_idSolicitud))
					{
						Solicitud    ls_solicitud;
						SolicitudDAO ls_DAO;

						ls_solicitud     = new Solicitud();
						ls_DAO           = DaoCreator.getSolicitudDAO(ldm_manager);

						ls_solicitud.setIdSolicitud(ls_idSolicitud);

						ls_solicitud = ls_DAO.findById(ls_solicitud);

						if(ls_solicitud != null)
						{
							ls_solicitud.setIdDatosAntSistema(ls_idDatosAntSistema);
							ls_solicitud.setIdUsuarioModificacion(as_userId);
							ls_solicitud.setIpModificacion(as_remoteIp);

							ls_DAO.updataDatosAntSistema(ls_solicitud);
						}
					}
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("insertUpdateDatosAntSistemaSolicitud", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}
	}

	/**
	 * Busca los datos de antiguo sistema que esten relacionados a un id turno específica.
	 *
	 * @param as_idTurno id turno que se va a utilizar como filtro en la consulta
	 * @return Colección de datos ant sistema resultantes de la consulta
	 * @throws B2BException Señala que se ha producido una excepción
	 *
	 */
	public synchronized Collection<DatosAntSistema> obtenerDatosAntSistema(String as_idTurno)
	    throws B2BException
	{
		DAOManager                  ldm_manager;
		Collection<DatosAntSistema> lcdas_return;

		ldm_manager      = DaoManagerFactory.getDAOManager();
		lcdas_return     = null;

		try
		{
			lcdas_return = obtenerDatosAntSistema(ldm_manager, as_idTurno, false);
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("obtenerDatosAntSistema", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lcdas_return;
	}

	/**
	 * Busca los datos de antiguo sistema que esten relacionados a un id turno específica.
	 *
	 * @param adm_manager correspondiente al valor del tipo de objeto DAOManager
	 * @param as_idTurno id turno que se va a utilizar como filtro en la consulta
	 * @param ab_observacionesNoTramite correspondiente al valor del tipo de objeto boolean
	 * @return Colección de datos ant sistema resultantes de la consulta
	 * @throws B2BException Señala que se ha producido una excepción
	 *
	 */
	public Collection<DatosAntSistema> obtenerDatosAntSistema(
	    DAOManager adm_manager, String as_idTurno, boolean ab_observacionesNoTramite
	)
	    throws B2BException
	{
		Collection<DatosAntSistema> lcdas_return;

		lcdas_return = null;

		if(StringUtils.isValidString(as_idTurno))
		{
			Turno lt_turno;
			lt_turno = new Turno();

			lt_turno.setIdTurno(as_idTurno);

			lt_turno = DaoCreator.getTurnoDAO(adm_manager).findById(lt_turno);

			if(lt_turno == null)
				throw new B2BException(ErrorKeys.ERROR_SIN_ID_TURNO);

			lcdas_return = DaoCreator.getDatosAntSistemaDAO(adm_manager)
					                     .findByIdSolicitudCirculo(lt_turno.getIdSolicitud(), lt_turno.getIdCirculo());
		}
		else
			throw new B2BException(ErrorKeys.ERROR_SIN_ID_TURNO);

		if(CollectionUtils.isValidCollection(lcdas_return))
		{
			AccPredioRegistroDAO  laprd_predioRegDAO;
			SolicitudMatriculaDAO lsmd_solicitudMatriculaDAO;

			laprd_predioRegDAO             = DaoCreator.getAccPredioRegistroDAO(adm_manager);
			lsmd_solicitudMatriculaDAO     = DaoCreator.getSolicitudMatriculaDAO(adm_manager);

			for(DatosAntSistema ldas_data : lcdas_return)
			{
				if(ldas_data != null)
				{
					{
						String ls_revisado;

						ls_revisado = StringUtils.getStringNotNull(ldas_data.getRevisadoAntSistema());

						if(ls_revisado.equals(EstadoCommon.S))
							ldas_data.setRevisadoAntStr(EstadoCommon.REVISADO);
						else if(ls_revisado.equals(EstadoCommon.D))
							ldas_data.setRevisadoAntStr(EstadoCommon.DEFINITIVO);
						else
							ldas_data.setRevisadoAntStr(EstadoCommon.SIN_REVISAR);
					}

					{
						String ls_accion;

						ls_accion = ldas_data.getAccion();

						if(StringUtils.isValidString(ls_accion))
						{
							boolean       lb_creacion;
							boolean       lb_asociacion;
							StringBuilder lsb_matriculas;
							String        ls_idDatosAnt;

							lb_creacion        = false;
							lb_asociacion      = false;
							lsb_matriculas     = new StringBuilder();
							ls_idDatosAnt      = ldas_data.getIdDatosAntSistema();

							if(ls_accion.equalsIgnoreCase(AccionAntSistemaCommon.CREAR_MATRICULAS))
							{
								ldas_data.setAccionDescripcion(
								    ab_observacionesNoTramite ? AccionAntSistemaCommon.DESCRIPCION_OBSERVACIONES_C
								                              : AccionAntSistemaCommon.DESCRIPCION_C
								);

								lb_creacion = true;
							}
							else if(ls_accion.equalsIgnoreCase(AccionAntSistemaCommon.ASOCIAR_MATRICULAS))
							{
								ldas_data.setAccionDescripcion(
								    ab_observacionesNoTramite ? AccionAntSistemaCommon.DESCRIPCION_OBSERVACIONES_A
								                              : AccionAntSistemaCommon.DESCRIPCION_A
								);

								lb_asociacion = true;
							}
							else if(ls_accion.equalsIgnoreCase(AccionAntSistemaCommon.INFORME_RESULTADOS))
								ldas_data.setAccionDescripcion(AccionAntSistemaCommon.DESCRIPCION_I);
							else if(ls_accion.equalsIgnoreCase(AccionAntSistemaCommon.CREAR_MATRICULA_E_INFORME))
							{
								ldas_data.setAccionDescripcion(
								    ab_observacionesNoTramite ? AccionAntSistemaCommon.DESCRIPCION_OBSERVACIONES_CI
								                              : AccionAntSistemaCommon.DESCRIPCION_CI
								);

								lb_creacion = true;
							}
							else if(ls_accion.equalsIgnoreCase(AccionAntSistemaCommon.ASOCIAR_MATRICULA_E_INFORME))
							{
								ldas_data.setAccionDescripcion(
								    ab_observacionesNoTramite ? AccionAntSistemaCommon.DESCRIPCION_OBSERVACIONES_AI
								                              : AccionAntSistemaCommon.DESCRIPCION_AI
								);

								lb_asociacion = true;
							}

							if(lb_creacion)
							{
								AccPredioRegistro             lapr_predioRegistro;
								Collection<AccPredioRegistro> lcapr_matriculas;

								lapr_predioRegistro = new AccPredioRegistro();

								lapr_predioRegistro.setIdTurno(as_idTurno);
								lapr_predioRegistro.setIdDatosAntSistema(ls_idDatosAnt);

								lcapr_matriculas = laprd_predioRegDAO.findByTurnoAntSistema(lapr_predioRegistro, false);

								if(CollectionUtils.isValidCollection(lcapr_matriculas))
								{
									Iterator<AccPredioRegistro> liapr_iterador;

									liapr_iterador = lcapr_matriculas.iterator();

									while(liapr_iterador.hasNext())
									{
										AccPredioRegistro lapr_temp;

										lapr_temp = liapr_iterador.next();

										if(lapr_temp != null)
										{
											lsb_matriculas.append(lapr_temp.getIdCirculo());
											lsb_matriculas.append("-");
											lsb_matriculas.append(lapr_temp.getIdMatricula());

											if(liapr_iterador.hasNext())
												lsb_matriculas.append(", ");
										}
									}
								}
							}
							else if(lb_asociacion)
							{
								Collection<SolicitudMatricula> lcsm_matriculas;

								lcsm_matriculas = lsmd_solicitudMatriculaDAO.findMatriculasByturnoAntSistema(
									    as_idTurno, ls_idDatosAnt
									);

								if(CollectionUtils.isValidCollection(lcsm_matriculas))
								{
									Iterator<SolicitudMatricula> lism_iterador;

									lism_iterador = lcsm_matriculas.iterator();

									while(lism_iterador.hasNext())
									{
										SolicitudMatricula lsm_temp;

										lsm_temp = lism_iterador.next();

										if(lsm_temp != null)
										{
											lsb_matriculas.append(lsm_temp.getIdCirculo());
											lsb_matriculas.append("-");
											lsb_matriculas.append(lsm_temp.getIdMatricula());

											if(lism_iterador.hasNext())
												lsb_matriculas.append(", ");
										}
									}
								}
							}

							ldas_data.setMatriculasAsociadas(lsb_matriculas.toString());
						}
					}
				}
			}
		}

		return lcdas_return;
	}

	/**
	 * Busca los detalles de antiguo sistema que esten relacionados a un id datos ant sistema específico.
	 *
	 * @param adas_datos correspondiente al valor del tipo de objeto DatosAntSistema
	 * @return Colección de detalles ant sistema resultantes de la consulta
	 * @throws B2BException Señala que se ha producido una excepción
	 *
	 */
	public synchronized Collection<DetalleAntSistema> obtenerDetallesAntSistema(DatosAntSistema adas_datos)
	    throws B2BException
	{
		DAOManager                    ldm_manager;
		Collection<DetalleAntSistema> lcdas_return;

		ldm_manager      = DaoManagerFactory.getDAOManager();
		lcdas_return     = new LinkedList<DetalleAntSistema>();

		try
		{
			if(adas_datos == null)
				throw new B2BException(ErrorKeys.ERROR_INTERNO_SISTEMA);

			DetalleAntSistema ldas_detalleAnt;
			ldas_detalleAnt = new DetalleAntSistema();

			ldas_detalleAnt.setIdDatosAntSistema(adas_datos.getIdDatosAntSistema());

			lcdas_return = DaoCreator.getDetalleAntSistemaDAO(ldm_manager).findByDatosAntSis(ldas_detalleAnt);

			if(CollectionUtils.isValidCollection(lcdas_return))
			{
				LibroAntiguoSistemaDao las_DAO;
				las_DAO = DaoCreator.getLibroAntiguoSistemaDAO(ldm_manager);

				for(DetalleAntSistema ldas_detalle : lcdas_return)
				{
					if(ldas_detalle != null)
					{
						LibroAntiguoSistema las_libro;
						las_libro = new LibroAntiguoSistema();

						StringBuilder lsb_nombrePredioDetalle;

						las_libro.setIdLibroAntiguoSistema(NumericUtils.getLong(ldas_detalle.getIdLibroAntSistema()));

						las_libro                   = las_DAO.findById(las_libro);
						lsb_nombrePredioDetalle     = new StringBuilder();

						if(las_libro != null)
							ldas_detalle.setNombreLibro(las_libro.getNombre());

						lsb_nombrePredioDetalle.append(
						    IdentificadoresCommon.PREDIO_MIN + " " + IdentificadoresCommon.SIMBOLO_NUMERAL
						    + adas_datos.getConsecutivoPredioAntSistema() + " " + adas_datos.getNombrePredio()
						);

						ldas_detalle.setNombrePredio(lsb_nombrePredioDetalle.toString());
					}
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("obtenerDetallesAntSistema", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lcdas_return;
	}

	/**
	 * Busca el documento relacionado a un detalle de antiguo sistema por su id y versión.
	 *
	 * @param adas_detalle objeto contenedor del id y versión del documento para ser utilizados en
	 * el filtro de la consulta
	 * @return Objeto documento resultante de la consulta
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see Documento
	 */
	public synchronized Documento obtenerDocumentoAntSistema(DetalleAntSistema adas_detalle)
	    throws B2BException
	{
		DAOManager ldm_manager;
		Documento  ld_return;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		ld_return       = null;

		try
		{
			if(adas_detalle == null)
				throw new B2BException(ErrorKeys.ERROR_INTERNO_SISTEMA);

			Documento ld_documento;

			ld_documento = new Documento();

			ld_documento.setIdDocumento(adas_detalle.getIdDocumentoTradicion());
			ld_documento.setVersionDocumento(NumericUtils.getLongWrapper(adas_detalle.getVersionDocumentoTradicion()));

			ld_return = DaoCreator.getDocumentoDAO(ldm_manager).findByIdDocumentoVersion(ld_documento);

			if(ld_return != null)
			{
				{
					TipoDocumentoPublico lcidt_datos;

					lcidt_datos = new TipoDocumentoPublico();

					lcidt_datos.setIdTipoDocumento(ld_return.getIdTipoDocumento());

					lcidt_datos = DaoCreator.getTipoDocumentoPublicoDAO(ldm_manager).findById(lcidt_datos);

					if(lcidt_datos == null)
						lcidt_datos = new TipoDocumentoPublico(IdentificadoresCommon.SIN_INFORMACION);

					ld_return.setTipoDocumento(lcidt_datos);
				}

				{
					TipoOficina lto_datos;

					lto_datos = new TipoOficina();

					lto_datos.setIdTipoOficina(ld_return.getIdTipoOficina());

					lto_datos = DaoCreator.getTipoOficinaDAO(ldm_manager).findById(lto_datos);

					if(lto_datos == null)
						lto_datos = new TipoOficina(IdentificadoresCommon.SIN_INFORMACION);

					ld_return.setTipoOficina(lto_datos);
				}

				{
					String ls_pais;
					String ls_departemento;
					String ls_municipio;

					ls_pais             = StringUtils.getStringNotNull(ld_return.getIdPais());
					ls_departemento     = StringUtils.getStringNotNull(ld_return.getIdDepartamento());
					ls_municipio        = StringUtils.getStringNotNull(ld_return.getIdMunicipio());

					{
						Pais lp_datos;

						lp_datos = new Pais();

						lp_datos.setIdPais(ls_pais);

						lp_datos = DaoCreator.getPaisDAO(ldm_manager).findById(lp_datos);

						if(lp_datos == null)
							lp_datos = new Pais(IdentificadoresCommon.SIN_INFORMACION);

						ld_return.setPais(lp_datos);
					}

					{
						Departamento ld_departamento;

						ld_departamento = new Departamento();

						ld_departamento.setIdPais(ls_pais);
						ld_departamento.setIdDepartamento(ls_departemento);

						ld_departamento = DaoCreator.getDepartamentoDAO(ldm_manager).findById(ld_departamento);

						if(ld_departamento == null)
							ld_departamento = new Departamento(IdentificadoresCommon.SIN_INFORMACION);

						ld_return.setDepartamento(ld_departamento);
					}

					{
						Municipio lm_municipio;

						lm_municipio = new Municipio();

						lm_municipio.setIdPais(ls_pais);
						lm_municipio.setIdDepartamento(ls_departemento);
						lm_municipio.setIdMunicipio(ls_municipio);

						lm_municipio = DaoCreator.getMunicipioDAO(ldm_manager).findById(lm_municipio);

						if(lm_municipio == null)
							lm_municipio = new Municipio(IdentificadoresCommon.SIN_INFORMACION);

						ld_return.setMunicipio(lm_municipio);
					}

					{
						OficinaOrigen loo_oficina;

						loo_oficina = new OficinaOrigen();

						loo_oficina.setIdOficinaOrigen(ld_return.getIdOficinaOrigen());
						loo_oficina.setVersion(ld_return.getVersion());

						loo_oficina = DaoCreator.getOficinaOrigenDAO(ldm_manager).findById(loo_oficina);

						if(loo_oficina == null)
							loo_oficina = new OficinaOrigen(IdentificadoresCommon.SIN_INFORMACION);

						ld_return.setOficinaOrigen(loo_oficina);
					}
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("obtenerDocumentoAntSistema", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return ld_return;
	}

	/**
	 * Obtener ultima matricula creada.
	 *
	 * @param ath_turnoHistoria correspondiente al valor de ath turno historia
	 * @param as_userId correspondiente al valor de as user id
	 * @param as_remoteIp correspondiente al valor de as remote ip
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public synchronized Collection<AccPredioRegistro> obtenerUltimaMatriculaCreada(
	    TurnoHistoria ath_turnoHistoria, String as_userId, String as_remoteIp
	)
	    throws B2BException
	{
		Collection<AccPredioRegistro> lcapr_Predio;
		DAOManager                    ldm_manager;

		ldm_manager      = DaoManagerFactory.getDAOManager();
		lcapr_Predio     = new ArrayList<AccPredioRegistro>();

		try
		{
			if(ath_turnoHistoria != null)
			{
				ath_turnoHistoria = DaoCreator.getTurnoHistoriaDAO(ldm_manager).findById(ath_turnoHistoria);

				if(ath_turnoHistoria != null)
				{
					Collection<AccPredioRegistro> lcapr_predios;

					lcapr_predios = DaoCreator.getAccPredioRegistroDAO(ldm_manager)
							                      .findAllByTurnoHistoriaOrderByFechaCreacion(
							    ath_turnoHistoria.getIdTurnoHistoria()
							);

					if(CollectionUtils.isValidCollection(lcapr_predios))
					{
						ath_turnoHistoria.setIdUsuarioModificacion(as_userId);
						ath_turnoHistoria.setIpModificacion(as_remoteIp);

						lcapr_Predio = lcapr_predios;
					}
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("obtenerUltimaMatriculaCreada", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lcapr_Predio;
	}

	/**
	 * Método para la búsqueda de plantilla para etapaa 105, matriculas oficio desde AS
	 * @param adm_manager con el dao manager de laa transacción
	 * @param al_idMotivo con el id motivo de la plantilla a generar
	 * @param ab_consultarConstante para consultar la existencia de la constante y validar.
	 * @return de tipo tag plantilla resolucion con los valores de la plantilla a consultar
	 * @throws B2BException
	 */
	public TagPlantillaResolucion plantillaMatriculaOficioAS(
	    DAOManager adm_manager, long al_idMotivo, boolean ab_consultarConstante
	)
	    throws B2BException
	{
		TagPlantillaResolucion ltpr_matriculaOficioAS;

		ltpr_matriculaOficioAS = null;

		try
		{
			String ls_idPlantilla;
			String ls_tipoArchivo;
			String ls_tipoDocumental;

			ls_idPlantilla        = null;
			ls_tipoArchivo        = null;
			ls_tipoDocumental     = null;

			if(al_idMotivo == MotivoTramiteCommon.NEGAR_SOLICITUD_CREACION_MATRICULA_OFICIO)
			{
				ls_idPlantilla        = ConstanteCommon.PLANTILLA_NEGACION_CREACION_MATRICULA_OFICIO;
				ls_tipoArchivo        = TipoArchivoCommon.OFICIO_NEGACION_MATRICULA_OFICIO;
				ls_tipoDocumental     = TipoDocumentalCommon.OFICIO;
			}
			else if(al_idMotivo == MotivoTramiteCommon.NEGAR_SOLICITUD_CREACION_MATRICULA_YA_EXISTENTE_OFICIO)
			{
				ls_idPlantilla        = ConstanteCommon.PLANTILLA_NEGACION_CREACION_MATRICULA_OFICIO_YA_EXISTENTE;
				ls_tipoArchivo        = TipoArchivoCommon.OFICIO_NEGACION_MATRICULA_OFICIO_YA_EXISTENTE;
				ls_tipoDocumental     = TipoDocumentalCommon.OFICIO;
			}

			ltpr_matriculaOficioAS = new TagPlantillaResolucion();

			ltpr_matriculaOficioAS.setIdPlantilla(ls_idPlantilla);
			ltpr_matriculaOficioAS.setTipoArchivo(ls_tipoArchivo);
			ltpr_matriculaOficioAS.setTipoDocumental(ls_tipoDocumental);

			if(ab_consultarConstante)
			{
				Constantes lc_constante;

				lc_constante = new Constantes();

				lc_constante.setIdConstante(ls_idPlantilla);

				lc_constante = DaoCreator.getConstantesDAO(adm_manager).findImagen(lc_constante);

				if(lc_constante == null)
				{
					Object[] loa_messageArgs = new String[1];

					loa_messageArgs[0] = ls_idPlantilla;

					throw new B2BException(ErrorKeys.ERROR_SIN_CONSTANTE, loa_messageArgs);
				}

				ltpr_matriculaOficioAS.setArchivo(lc_constante.getImagenes());
			}
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("plantillaMatriculaOficioAS", lb2be_e);

			throw lb2be_e;
		}

		return ltpr_matriculaOficioAS;
	}

	/**
	 * Metodo encargado de reordenar anotaciones.
	 *
	 * @param aa_anotacion Argumento de tipo <code>Anotacion</code> que contiene las anotaciones a reordenar.
	 * @param as_userId Argumento de tipo <code>String</code> que contiene el id del usuario que realiza la operación.
	 * @param as_remoteIp Argumento de tipo <code>String</code> que contiene la ip desde donde se realiza la operación.
	 * @return Objeto de tipo <code>Anotacion</code> que contiene las anotaciones reordenada.
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see Anotacion
	 */
	public synchronized Anotacion reordenarAnotaciones(Anotacion aa_anotacion, String as_userId, String as_remoteIp)
	    throws B2BException
	{
		DAOManager ldm_manager;

		ldm_manager = DaoManagerFactory.getDAOManager();

		try
		{
			if(aa_anotacion != null)
			{
				Collection<Anotacion> lca_anotacionesAgregadas;

				lca_anotacionesAgregadas = aa_anotacion.getAnotacionesAgregadas();

				if(CollectionUtils.isValidCollection(lca_anotacionesAgregadas))
				{
					AnotacionPredioDAO lapd_DAO;
					Collection<Long>   lcl_orden;
					int                li_fila;

					lapd_DAO      = DaoCreator.getAccAnotacionPredioDAO(ldm_manager);
					lcl_orden     = null;
					li_fila       = 1;

					for(Anotacion la_iterador : lca_anotacionesAgregadas)
					{
						if(la_iterador != null)
						{
							AnotacionPredio lap_anotacionPredio;

							lap_anotacionPredio = la_iterador.getAnotacionPredio();

							if(lap_anotacionPredio != null)
							{
								String ls_idAnotacionPredio;

								ls_idAnotacionPredio = lap_anotacionPredio.getIdAnotacionPredio();

								if(StringUtils.isValidString(ls_idAnotacionPredio))
								{
									AnotacionPredio lap_anotacionPredioEncontrada;

									lap_anotacionPredioEncontrada = lapd_DAO.findById(ls_idAnotacionPredio);

									if(lap_anotacionPredioEncontrada != null)
									{
										BigDecimal lbd_orden;
										Long       ll_orden;

										lbd_orden     = lap_anotacionPredio.getOrden();
										ll_orden      = NumericUtils.getLongWrapper(lbd_orden);

										if(lbd_orden != null)
										{
											{
												String ls_idCirculo;
												String ls_idMatricula;
												Long   ll_ordenMaxima;

												ls_idCirculo       = lap_anotacionPredio.getIdCirculo();
												ls_idMatricula     = StringUtils.getString(
													    lap_anotacionPredio.getIdMatricula()
													);

												ll_ordenMaxima = lapd_DAO.findMaxOrdenByCirculoMatricula(
													    ls_idCirculo, ls_idMatricula
													);

												if(
												    (ll_ordenMaxima != null)
													    && (ll_orden.compareTo(ll_ordenMaxima) > 0L)
												)
												{
													Object[] loa_messageArgs;

													loa_messageArgs        = new String[2];
													loa_messageArgs[0]     = StringUtils.getString(ll_orden);
													loa_messageArgs[1]     = StringUtils.getString(ll_ordenMaxima);

													throw new B2BException(
													    ErrorKeys.ERROR_ORDEN_SUPERIOR_AL_PERMITIDO, loa_messageArgs
													);
												}
											}

											{
												if(!CollectionUtils.isValidCollection(lcl_orden))
													lcl_orden = new ArrayList<Long>();

												Iterator<Long> lil_iterador;
												boolean        lb_repetido;
												Long           ll_ordenAgregado;

												lil_iterador         = lcl_orden.iterator();
												lb_repetido          = false;
												ll_ordenAgregado     = null;

												while(lil_iterador.hasNext() && !lb_repetido)
												{
													ll_ordenAgregado = lil_iterador.next();

													if(ll_ordenAgregado != null)
														lb_repetido = ll_ordenAgregado.compareTo(ll_orden) == 0L;
												}

												if(lb_repetido)
												{
													Object[] loa_messageArgs;

													loa_messageArgs        = new String[1];
													loa_messageArgs[0]     = StringUtils.getString(ll_ordenAgregado);

													throw new B2BException(
													    ErrorKeys.ERROR_ORDEN_REPETIDO, loa_messageArgs
													);
												}

												lcl_orden.add(ll_orden);
											}

											lap_anotacionPredioEncontrada.setOrden(lbd_orden);
											lap_anotacionPredioEncontrada.setIdUsuarioModificacion(as_userId);
											lap_anotacionPredioEncontrada.setIpModificacion(as_remoteIp);

											lapd_DAO.updateAnotacion(lap_anotacionPredioEncontrada, false);
										}
										else
										{
											Object[] loa_messageArgs;

											loa_messageArgs        = new String[1];
											loa_messageArgs[0]     = StringUtils.getString(li_fila);

											throw new B2BException(ErrorKeys.ERROR_ORDEN_INVALIDO, loa_messageArgs);
										}
									}
									else
										throw new B2BException(ErrorKeys.ERROR_SIN_DATOS_ID_ANOTACION_PREDIO);
								}
								else
									throw new B2BException(ErrorKeys.ERROR_SIN_ID_ANOTACION_PREDIO);
							}
							else
								throw new B2BException(ErrorKeys.ERROR_SIN_DATOS_ANOTACION_PREDIO);

							li_fila++;
						}
					}
				}
				else
					throw new B2BException(ErrorKeys.ERROR_SIN_ANOTACIONES_AGREGADAS);
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("reordenarAnotaciones", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return aa_anotacion;
	}

	/**
	 * Método encargado de asociar matriculas seleccionadas en pantalla, crear detalles para un predio determinado de antSistema
	 * y marcar el predio como revisado en 'S'.
	 *
	 * @param adas_datosAntSistema Objeto Datos Antiguo Sistema (Predio) seleccionado por pantalla.
	 * @param acdasui_detallesSeleccionados Objeto con detalles de Antiguo Sistema seleccionados en pantalla.
	 * @param acsm_csm Objeto con las matriculas seleccionadas en patalla.
	 * @param as_turnoHistoria Variable con el id_turno_historia del caso que se esta trabajando.
	 * @param as_turno Variable con el id_turno del caso que se esta trabajando.
	 * @param as_userId Variable de tipo String que contiene el usuario que realiza la acción.
	 * @param as_remoteIp Variable de tipo String que contiene la dirección ip remota desde donde seque realiza la acción.
	 * @return Variable de tipo boolean que marca la operación como exitosa.
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public synchronized boolean revisadoAntSistema(
	    DatosAntSistema adas_datosAntSistema, Collection<DetalleAntSistemaUI> acdasui_detallesSeleccionados,
	    Collection<SolicitudMatricula> acsm_csm, String as_turnoHistoria, String as_turno, String as_userId,
	    String as_remoteIp
	)
	    throws B2BException
	{
		boolean    lb_return;
		DAOManager ldm_manager;

		lb_return       = false;
		ldm_manager     = DaoManagerFactory.getDAOManager();

		try
		{
			if(adas_datosAntSistema != null)
			{
				String ls_idDatosAntSistema;
				Turno  lt_turno;

				ls_idDatosAntSistema     = adas_datosAntSistema.getIdDatosAntSistema();
				lt_turno                 = DaoCreator.getTurnoDAO(ldm_manager).findById(as_turno);

				if(lt_turno != null)
				{
					Solicitud ls_solicitud;
					String    ls_idProceso;

					ls_solicitud     = new Solicitud();
					ls_idProceso     = null;

					ls_solicitud.setIdSolicitud(lt_turno.getIdSolicitud());

					ls_solicitud = DaoCreator.getSolicitudDAO(ldm_manager).findById(ls_solicitud);

					if(ls_solicitud != null)
					{
						ls_idProceso = ls_solicitud.getIdProceso();

						String  ls_idSolicitud;
						boolean lb_asociarMatriculas;

						ls_idSolicitud           = ls_solicitud.getIdSolicitud();
						lb_asociarMatriculas     = false;

						if(StringUtils.isValidString(ls_idProceso))
						{
							if(CollectionUtils.isValidCollection(acsm_csm))
							{
								Acto                         la_tmp;
								ActoDAO                      lad_DAO;
								boolean                      lb_validActos;
								Collection<Acto>             lca_actosSolicitud;
								Iterator<SolicitudMatricula> lism_ism;
								SolicitudMatriculaDAO        lsm_DAO;
								SolicitudMatriculaActoDAO    lsma_DAO;

								la_tmp       = new Acto();
								lism_ism     = acsm_csm.iterator();
								lad_DAO      = DaoCreator.getActoDAO(ldm_manager);
								lsm_DAO      = DaoCreator.getSolicitudMatriculaDAO(ldm_manager);
								lsma_DAO     = DaoCreator.getSolicitudMatriculaActoDAO(ldm_manager);

								la_tmp.setIdSolicitud(ls_idSolicitud);
								la_tmp.setIdCirculo(lt_turno.getIdCirculo());

								lca_actosSolicitud     = lad_DAO.findByIdSolicitudCirculo(la_tmp);
								lb_validActos          = CollectionUtils.isValidCollection(lca_actosSolicitud);

								while(lism_ism.hasNext())
								{
									SolicitudMatricula lsm_actual;

									lsm_actual = lism_ism.next();

									if(lsm_actual != null)
									{
										String ls_circuloSolicitudMatricula;
										long   ll_matriculaSolicitudMatricula;

										ls_circuloSolicitudMatricula       = lsm_actual.getIdCirculo();
										ll_matriculaSolicitudMatricula     = lsm_actual.getIdMatricula();

										{
											SolicitudMatricula lsm_tmp;

											lsm_tmp = new SolicitudMatricula();

											lsm_tmp.setIdCirculo(ls_circuloSolicitudMatricula);
											lsm_tmp.setIdMatricula(ll_matriculaSolicitudMatricula);
											lsm_tmp.setIdSolicitud(ls_idSolicitud);

											lsm_tmp = lsm_DAO.findById(lsm_tmp);

											if(lsm_tmp == null)
											{
												lsm_actual.setIdSolicitud(ls_idSolicitud);
												lsm_actual.setIdUsuarioCreacion(as_userId);

												cargarTurnoCertificado(lsm_actual, ls_idDatosAntSistema, ldm_manager);

												lsm_actual.setIpCreacion(as_remoteIp);
												lsm_actual.setCementerio(EstadoCommon.NO);

												lsm_DAO.insertOrUpdate(lsm_actual, true);

												if(lb_validActos)
												{
													SolicitudMatriculaActo lsma_matriculaActo;
													Iterator<Acto>         lia_iterator;

													lsma_matriculaActo     = new SolicitudMatriculaActo();
													lia_iterator           = lca_actosSolicitud.iterator();

													lsma_matriculaActo.setIdSolicitud(ls_idSolicitud);
													lsma_matriculaActo.setIdCirculo(ls_circuloSolicitudMatricula);
													lsma_matriculaActo.setIdMatricula(ll_matriculaSolicitudMatricula);
													lsma_matriculaActo.setIdTurno(as_turno);
													lsma_matriculaActo.setEstado(EstadoCommon.A);
													lsma_matriculaActo.setIdUsuarioCreacion(as_userId);
													lsma_matriculaActo.setIpCreacion(as_remoteIp);

													while(lia_iterator.hasNext())
													{
														Acto la_iterador;
														la_iterador = lia_iterator.next();

														if(la_iterador != null)
														{
															String ls_activoPrecalificacion;

															ls_activoPrecalificacion = StringUtils.getStringNotNull(
																    la_iterador.getActivoPrecalificacion()
																);

															if(
															    !ls_activoPrecalificacion.equalsIgnoreCase(
																        EstadoCommon.N
																    )
															)
															{
																lsma_matriculaActo.setIdActo(la_iterador.getIdActo());

																lsma_DAO.insertOrUpdate(lsma_matriculaActo, true);

																if(!lb_asociarMatriculas)
																	lb_asociarMatriculas = true;
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

						{
							DatosAntSistemaDAO ldasd_DAO;

							ldasd_DAO = DaoCreator.getDatosAntSistemaDAO(ldm_manager);

							if(StringUtils.isValidString(ls_idDatosAntSistema))
							{
								DatosAntSistema ldas_tmp;

								ldas_tmp = new DatosAntSistema();

								ldas_tmp.setIdDatosAntSistema(ls_idDatosAntSistema);

								ldas_tmp = ldasd_DAO.findById(ldas_tmp);

								if(ldas_tmp != null)
								{
									if(CollectionUtils.isValidCollection(acdasui_detallesSeleccionados))
									{
										Iterator<DetalleAntSistemaUI> lidasui_idasui;

										lidasui_idasui = acdasui_detallesSeleccionados.iterator();

										while(lidasui_idasui.hasNext())
										{
											DetalleAntSistemaUI ldasui_iterator;

											ldasui_iterator = lidasui_idasui.next();

											if(ldasui_iterator != null)
											{
												if(
												    !CollectionUtils.isValidCollection(
													        DaoCreator.getDetalleAntSistemaDAO(ldm_manager)
													                      .findAllByDatosDetalle(
													            ldasui_iterator, ldas_tmp
													        )
													    )
												)
													crearDetalleAntSistema(
													    ldasui_iterator, ldas_tmp, ldm_manager, as_userId, as_remoteIp
													);
											}
										}
									}
									else
										throw new B2BException(ErrorKeys.DEBE_SELECCIONAR_DETALLE);

									{
										String  ls_revisadoAntSistema;
										boolean lb_justificacionFirma;

										ls_revisadoAntSistema     = ldas_tmp.getRevisadoAntSistema();
										lb_justificacionFirma     = adas_datosAntSistema.isJustificacionFirma();

										ldas_tmp.setIdUsuarioModificacion(as_userId);
										ldas_tmp.setIpModificacion(as_remoteIp);

										if(!BooleanUtils.getBooleanValue(ls_revisadoAntSistema))
										{
											ldas_tmp.setRevisadoAntSistema(EstadoCommon.S);

											ldasd_DAO.updateRevisionAntSistema(ldas_tmp);

											lb_return = true;
										}

										if(lb_asociarMatriculas)
										{
											ldas_tmp.setAccion(AccionAntSistemaCommon.ASOCIAR_MATRICULAS);

											ldasd_DAO.updateAccionAntSistema(ldas_tmp);
										}

										if(lb_justificacionFirma)
											ldasd_DAO.updateActivoRequiereFirmaLibro(ldas_tmp);
										else
											ldasd_DAO.updateInactivoRequiereFirmaLibro(ldas_tmp);
									}
								}

								else
									throw new B2BException(ErrorKeys.ERROR_SIN_SELECCION_PREDIO);
							}
							else
								throw new B2BException(ErrorKeys.ERROR_SIN_SELECCION_PREDIO);
						}
					}
				}
			}
			else
				throw new B2BException(ErrorKeys.ERROR_SIN_SELECCION_PREDIO);
		}
		catch(B2BException lb2be_e)
		{
			lb_return = false;

			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("RevisadoAntSistema", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lb_return;
	}

	/**
	 * Método encargado de completar el proceso de revisión de un predio de antiguo sistema.
	 *
	 * @param adas_datosAntSistema Objeto que contiene la información de antiguo sistema para salvar
	 * @param as_turnoHistoria Variable de tipo String que contiene el id del turno historia en proceso
	 * @param as_turno Variable de tipo String que contiene el id del turno en trámite
	 * @param as_userId Variable de tipo String que contiene el id del usuario qeu está realizando el trámite
	 * @param as_remoteIp Variable de tipo String que contiene la ip del usuario que está realizando el trámite
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public synchronized void revisadoAntSistemaDefinitivo(
	    DatosAntSistema adas_datosAntSistema, String as_turnoHistoria, String as_turno, String as_userId,
	    String as_remoteIp
	)
	    throws B2BException
	{
		DAOManager ldm_manager;

		ldm_manager = DaoManagerFactory.getDAOManager();

		try
		{
			if(adas_datosAntSistema != null)
			{
				DatosAntSistemaDAO ldas_DAO;
				ProcedimientosDAO  lpd_DAO;
				String             ls_solicitarComplementacion;

				ldas_DAO                        = DaoCreator.getDatosAntSistemaDAO(ldm_manager);
				lpd_DAO                         = DaoCreator.getProcedimientosDAO(ldm_manager);
				ls_solicitarComplementacion     = adas_datosAntSistema.getSolicitarComplementacion();

				adas_datosAntSistema = ldas_DAO.findById(adas_datosAntSistema);

				if(adas_datosAntSistema != null)
				{
					adas_datosAntSistema.setSolicitarComplementacion(ls_solicitarComplementacion);
					ldas_DAO.updateComplementacionAntSistema(adas_datosAntSistema);

					String ls_revisadoAntSistema;

					ls_revisadoAntSistema = adas_datosAntSistema.getRevisadoAntSistema();

					if(BooleanUtils.getBooleanValue(ls_revisadoAntSistema))
					{
						adas_datosAntSistema.setRevisadoAntSistema(EstadoCommon.D);
						adas_datosAntSistema.setIdUsuarioModificacion(as_userId);
						adas_datosAntSistema.setIpModificacion(as_remoteIp);

						ldas_DAO.updateRevisionAntSistema(adas_datosAntSistema);
						lpd_DAO.procVerificaBitacoraBloqueo(as_turnoHistoria, as_userId, as_remoteIp);
					}
					else
						throw new B2BException(ErrorKeys.ERROR_ANT_SISTEMA_DEFINITIVO_SALVAR);
				}
			}
			else
				throw new B2BException(ErrorKeys.ERROR_ANT_SISTEMA_DEFINITIVO_SALVAR);
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("revisadoAntSistemaDefinitivo", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}
	}

	/**
	 * Método encargado de guardar toda la información de las anotaciones en el proceso de antiguo sistema.
	 *
	 * @param aa_anotacion  Objeto de tipo Anotacion que contiene toda la información de las anotaciones a insertar o modificar.
	 * @param as_userId Variable de tipo String que contiene el usuario que realiza la acción.
	 * @param as_remoteIp Variable de tipo String que contiene la dirección ip remota desde donde seque realiza la acción.
	 * @return Retorna  un objeto  de tipo  Anotacion que coincide con los parametros de entrada.
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see Anotacion
	 */
	public synchronized Anotacion salvarAnotaciones(Anotacion aa_anotacion, String as_userId, String as_remoteIp)
	    throws B2BException
	{
		DAOManager ldm_manager;
		ldm_manager = DaoManagerFactory.getDAOManager();

		try
		{
			if(aa_anotacion != null)
			{
				Collection<Anotacion> lca_anotacionesAgregadas;
				TurnoHistoria         lth_turnoHistoria;
				AccPredioRegistro     lapr_accPredioRegistro;
				boolean               lb_anotacionIndividual;

				lca_anotacionesAgregadas     = aa_anotacion.getAnotacionesAgregadas();
				lth_turnoHistoria            = aa_anotacion.getTurnoHistoria();
				lapr_accPredioRegistro       = aa_anotacion.getAccPredioRegistro();
				lb_anotacionIndividual       = aa_anotacion.isAnotacionIndividual();

				if(CollectionUtils.isValidCollection(lca_anotacionesAgregadas) && (lth_turnoHistoria != null))
				{
					TurnoHistoriaDAO lthd_DAO;
					lthd_DAO     = DaoCreator.getTurnoHistoriaDAO(ldm_manager);

					lth_turnoHistoria = lthd_DAO.findById(lth_turnoHistoria);

					if((lth_turnoHistoria != null) && (lapr_accPredioRegistro != null))
					{
						Collection<AnotacionPredio>  lcap_anotaciones;
						String                       ls_idSolicitud;
						String                       ls_idTurno;
						String                       ls_idCirculo;
						Long                         ll_idMatricula;
						Long                         ll_idTurnoHistoria;
						long                         ll_idMatriculaP;
						long                         ll_idTurnoHistoriaP;
						Map<String, AnotacionPredio> lmsa_anotaciones;

						AnotacionPredioDAO          lapd_DAO;
						NaturalezaJuridicaDAO       lnjd_DAO;
						AnotacionCancelacionDAO     lacd_DAO;
						PersonaDAO                  lpd_DAO;
						AnotacionPredioCiudadanoDAO lapcd_DAO;
						AccPredioSegregadoDAO       lapsd_DAO;
						ConstantesDAO               lcd_DAO;
						SolicitudIntervinienteDAO   lsid_DAO;
						SolicitudDAO                lsd_DAO;

						ls_idSolicitud          = lth_turnoHistoria.getIdSolicitud();
						ls_idTurno              = lth_turnoHistoria.getIdTurno();
						ll_idTurnoHistoria      = lth_turnoHistoria.getIdTurnoHistoria();
						ll_idTurnoHistoriaP     = NumericUtils.getLong(ll_idTurnoHistoria);
						ls_idCirculo            = lapr_accPredioRegistro.getIdCirculo();
						ll_idMatricula          = lapr_accPredioRegistro.getIdMatricula();
						lmsa_anotaciones        = new HashMap<String, AnotacionPredio>();

						lapd_DAO      = DaoCreator.getAccAnotacionPredioDAO(ldm_manager);
						lnjd_DAO      = DaoCreator.getNaturalezaJuridicaDAO(ldm_manager);
						lacd_DAO      = DaoCreator.getAnotacionCancelacionDAO(ldm_manager);
						lpd_DAO       = DaoCreator.getPersonaDAO(ldm_manager);
						lapcd_DAO     = DaoCreator.getAccAnotacionPredioCiudadanoDAO(ldm_manager);
						lapsd_DAO     = DaoCreator.getAccPredioSegregadoDAO(ldm_manager);
						lcd_DAO       = DaoCreator.getConstantesDAO(ldm_manager);
						lsid_DAO      = DaoCreator.getSolicitudIntervinienteDAO(ldm_manager);
						lsd_DAO       = DaoCreator.getSolicitudDAO(ldm_manager);

						if(!StringUtils.isValidString(ls_idSolicitud))
						{
							Object[] loa_messageArgs = new String[1];
							loa_messageArgs[0] = StringUtils.getString(ll_idTurnoHistoria);
							throw new B2BException(ErrorKeys.NO_SE_ENCONTRO_SOLICITUD_TURNO_HISTORIA, loa_messageArgs);
						}

						if(!StringUtils.isValidString(ls_idTurno))
						{
							Object[] loa_messageArgs = new String[1];
							loa_messageArgs[0] = StringUtils.getString(ll_idTurnoHistoria);
							throw new B2BException(ErrorKeys.NO_SE_ENCONTRO_TURNO_TURNO_HISTORIA, loa_messageArgs);
						}

						if(!StringUtils.isValidString(ls_idCirculo))
							throw new B2BException(ErrorKeys.ERROR_NO_CIRCULO_REGISTRAL);

						if(!NumericUtils.isValidLong(ll_idMatricula))
							throw new B2BException(ErrorKeys.ERROR_NO_MATRICULA);

						{
							AnotacionPredio lap_anotacionPredio;

							lap_anotacionPredio = new AnotacionPredio();

							lap_anotacionPredio.setIdCirculo(ls_idCirculo);
							lap_anotacionPredio.setIdMatricula(ll_idMatricula);
							lap_anotacionPredio.setIdTurnoHistoria(ll_idTurnoHistoriaP);

							lcap_anotaciones = lapd_DAO.findByTurnoHistoriaCirculoMatricula(lap_anotacionPredio);
						}

						ll_idMatriculaP = NumericUtils.getLong(ll_idMatricula);

						if(aa_anotacion.isBorrar() && CollectionUtils.isValidCollection(lcap_anotaciones))
						{
							Iterator<AnotacionPredio> liap_iterator;

							liap_iterator = lcap_anotaciones.iterator();

							while(liap_iterator.hasNext())
							{
								AnotacionPredio lap_iterador;

								lap_iterador = liap_iterator.next();

								if(lap_iterador != null)
								{
									String ls_idAnotacionPredio;

									ls_idAnotacionPredio = lap_iterador.getIdAnotacionPredio();

									if(StringUtils.isValidString(ls_idAnotacionPredio))
									{
										if(!lmsa_anotaciones.containsKey(ls_idAnotacionPredio))
										{
											lapcd_DAO.deleteIntervinientes(ls_idAnotacionPredio);
											lapd_DAO.deleteById(lap_iterador);
										}
									}
								}
							}
						}

						for(Anotacion la_iterador : lca_anotacionesAgregadas)
						{
							if(la_iterador != null)
							{
								AnotacionPredio   lap_anotacionPredio;
								DetalleAntSistema ldas_detalleAntSistema;
								Documento         ld_documento;
								long              ll_idAnotacion;
								String            ls_idAnotacionPredio;

								lap_anotacionPredio        = la_iterador.getAnotacionPredio();
								ldas_detalleAntSistema     = la_iterador.getDetalleAntSistema();
								ld_documento               = la_iterador.getDocumento();
								ll_idAnotacion             = la_iterador.getIdAnotacion();
								ls_idAnotacionPredio       = null;

								validarDatosAnotacion(lap_anotacionPredio);
								actualizarOCrearDocumentoAnotacion(
								    ld_documento, false, ldm_manager, as_userId, as_remoteIp
								);

								{
									AnotacionPredio lap_tmp;

									lap_tmp = validarDetalleAntSistema(
										    ldas_detalleAntSistema, aa_anotacion, la_iterador
										);

									if(lap_tmp != null)
									{
										lap_anotacionPredio.setIdDatosAntSistema(lap_tmp.getIdDatosAntSistema());
										lap_anotacionPredio.setIdDetalleAntSistema(lap_tmp.getIdDetalleAntSistema());
									}
								}

								if(lap_anotacionPredio != null)
								{
									lap_anotacionPredio.setIdCirculo(ls_idCirculo);
									lap_anotacionPredio.setIdMatricula(NumericUtils.getLongWrapper(ll_idMatriculaP));
									lap_anotacionPredio.setValidarAnotacionACancelar(true);

									{
										AnotacionPredio lap_tmp;

										lap_tmp = validarYCrearAnotacionCancelacion(
											    lap_anotacionPredio, ll_idAnotacion, lacd_DAO, lnjd_DAO, lapd_DAO,
											    aa_anotacion, lth_turnoHistoria, as_userId, as_remoteIp
											);

										if(lap_tmp != null)
										{
											lap_anotacionPredio.setIdNaturalezaJuridica(
											    lap_tmp.getIdNaturalezaJuridica()
											);
											lap_anotacionPredio.setVersion(lap_tmp.getVersion());
											lap_anotacionPredio.setAnotacionCancelada(lap_tmp.getAnotacionCancelada());
										}
									}

									{
										Long ll_idAnotacionOrden;
										ll_idAnotacionOrden = lap_anotacionPredio.getIdAnotacion();

										if(ll_idAnotacionOrden != null)
										{
											Double ld_tmp;
											ld_tmp = NumericUtils.getDoubleWrapper(ll_idAnotacionOrden);
											lap_anotacionPredio.setOrden(NumericUtils.getBigDecimal(ld_tmp));
										}
									}

									lap_anotacionPredio.setIdSolicitud(ls_idSolicitud);
									lap_anotacionPredio.setIdTurno(ls_idTurno);
									lap_anotacionPredio.setIdTurnoHistoria(ll_idTurnoHistoriaP);
									lap_anotacionPredio.setIdDocumento(ld_documento.getIdDocumento());
									lap_anotacionPredio.setVersionDocumento(
									    NumericUtils.getLong(ld_documento.getVersionDocumento())
									);

									{
										Turno lt_t;

										lt_t = new Turno();

										lt_t.setIdTurno(ls_idTurno);

										lt_t = DaoCreator.getTurnoDAO(ldm_manager).findById(lt_t);

										if(lt_t != null)
											lap_anotacionPredio.setFechaRegistro(lt_t.getFechaCreacion());
									}

									lap_anotacionPredio.setIdEstadoRegistro(EstadoCommon.T);

									{
										AnotacionPredio lap_tmp;

										lap_tmp = lapd_DAO.findByCirculoMatriculaAnotacion(lap_anotacionPredio);

										lap_anotacionPredio.setIdUsuarioCreacion(as_userId);
										lap_anotacionPredio.setIpCreacion(as_remoteIp);
										lap_anotacionPredio.setIdUsuarioModificacion(as_userId);
										lap_anotacionPredio.setIpModificacion(as_remoteIp);

										if((lap_tmp != null) && !lb_anotacionIndividual)
										{
											lap_anotacionPredio.setIdAnotacionPredio(lap_tmp.getIdAnotacionPredio());

											lapd_DAO.updateAnotacion(lap_anotacionPredio);
										}
										else
											lap_anotacionPredio = lapd_DAO.insert(lap_anotacionPredio);
									}

									if(lap_anotacionPredio != null)
									{
										ls_idAnotacionPredio = lap_anotacionPredio.getIdAnotacionPredio();

										if(StringUtils.isValidString(ls_idAnotacionPredio))
											lmsa_anotaciones.put(ls_idAnotacionPredio, lap_anotacionPredio);

										if(lb_anotacionIndividual)
											aa_anotacion.setAnotacionPredio(lap_anotacionPredio);
									}
								}

								{
									AnotacionPredioCiudadano lapc_anotacionPredioCiudadano;

									lapc_anotacionPredioCiudadano = new AnotacionPredioCiudadano();

									lapc_anotacionPredioCiudadano.setIdCirculo(ls_idCirculo);
									lapc_anotacionPredioCiudadano.setIdMatricula(ll_idMatriculaP);
									lapc_anotacionPredioCiudadano.setIdAnotacion(ll_idAnotacion);
									lapc_anotacionPredioCiudadano.setIdTurno(ls_idTurno);

									la_iterador.setAnotacionIndividual(lb_anotacionIndividual);

									validarActualizarOCrearIntervinientes(
									    ldm_manager, la_iterador, lapc_anotacionPredioCiudadano, lapcd_DAO, lpd_DAO,
									    lcd_DAO, lsid_DAO, lsd_DAO, ls_idSolicitud, ls_idAnotacionPredio, as_userId,
									    as_remoteIp
									);
								}

								{
									Collection<Anotacion> lca_matriculasSegregadas;

									lca_matriculasSegregadas = la_iterador.getMatriculasSegregadas();

									if(
									    CollectionUtils.isValidCollection(lca_matriculasSegregadas)
										    && !aa_anotacion.isTemporal()
									)
									{
										{
											PredioSegregado lps_delete;

											lps_delete = new PredioSegregado();

											lps_delete.setIdCirculo(ls_idCirculo);
											lps_delete.setIdMatricula(ll_idMatriculaP);
											lps_delete.setIdAnotacion(NumericUtils.getLongWrapper(ll_idAnotacion));
											lps_delete.setIdTurno(ls_idTurno);

											lapsd_DAO.deleteByCirculoMatriculaAnotacionTurno(lps_delete);
										}

										for(Anotacion la_matriculas : lca_matriculasSegregadas)
										{
											if(la_matriculas != null)
											{
												PredioRegistro lpr_predioRegistro;
												lpr_predioRegistro = la_matriculas.getPredioRegistro();

												if(lpr_predioRegistro != null)
												{
													String ls_idCirculoSegregada;
													long   ll_idMatriculaSegregada;

													ls_idCirculoSegregada       = lpr_predioRegistro.getIdCirculo();
													ll_idMatriculaSegregada     = lpr_predioRegistro.getIdMatricula();

													{
														PredioSegregado lps_predioSegregado;
														lps_predioSegregado = new PredioSegregado();

														lps_predioSegregado.setIdCirculo(ls_idCirculo);
														lps_predioSegregado.setIdMatricula(ll_idMatriculaP);

														lps_predioSegregado.setIdCirculo1(ls_idCirculoSegregada);
														lps_predioSegregado.setIdMatricula1(ll_idMatriculaSegregada);
														lps_predioSegregado.setIdAnotacion(
														    NumericUtils.getLongWrapper(ll_idAnotacion)
														);

														lps_predioSegregado.setIdTurno(ls_idTurno);
														lps_predioSegregado.setIdTurnoHistoria(ll_idTurnoHistoriaP);

														lps_predioSegregado.setIdUsuarioCreacion(as_userId);
														lps_predioSegregado.setIpCreacion(as_remoteIp);

														lapsd_DAO.insert(lps_predioSegregado);
													}
												}
											}
										}
									}
								}
							}
						}

						if(
						    CollectionUtils.isValidCollection(lmsa_anotaciones)
							    && CollectionUtils.isValidCollection(lcap_anotaciones) && !lb_anotacionIndividual
							    && !aa_anotacion.isTemporal()
						)
						{
							Iterator<AnotacionPredio> liap_iterator;

							liap_iterator = lcap_anotaciones.iterator();

							while(liap_iterator.hasNext())
							{
								AnotacionPredio lap_iterador;

								lap_iterador = liap_iterator.next();

								if(lap_iterador != null)
								{
									String ls_idAnotacionPredio;

									ls_idAnotacionPredio = lap_iterador.getIdAnotacionPredio();

									if(StringUtils.isValidString(ls_idAnotacionPredio))
									{
										if(!lmsa_anotaciones.containsKey(ls_idAnotacionPredio))
										{
											lapcd_DAO.deleteIntervinientes(ls_idAnotacionPredio);
											lapd_DAO.deleteById(lap_iterador);
										}
									}
								}
							}
						}

						if(!aa_anotacion.isTemporal())
						{
							SolicitudMatricula lsm_parametros;
							byte[]             lba_archivo;

							lsm_parametros = new SolicitudMatricula();
							lsm_parametros.setIdCirculo(ls_idCirculo);
							lsm_parametros.setIdMatricula(ll_idMatriculaP);
							lsm_parametros.setIdSolicitud(ls_idSolicitud);
							lsm_parametros.setIdTurnoCertificado(ls_idTurno);
							lsm_parametros.setTurnoHistoria(lth_turnoHistoria);

							lba_archivo = generarConsultaCreacionGrabacionMatricula(
								    lsm_parametros, false, ldm_manager, as_userId, as_remoteIp
								);

							if(lba_archivo != null)
								aa_anotacion.setArchivoGenerado(lba_archivo);
						}
					}
				}
				else
					throw new B2BException(ErrorKeys.ERROR_SIN_ANOTACIONES_AGREGADAS);
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("salvarAnotaciones", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return aa_anotacion;
	}

	/**
	 * Método encargado de guardar toda la información referentes a las áreas de predio.
	 *
	 * @param apr_accPredio Objeto de tipo AccPredioRegistro que contiene los datos necesarios para realizar la consulta.
	 * @param aap_areaPredio Objeto de tipo AreaPredio que contiene los datos necesarios para inserción.
	 * @param as_userId Variable de tipo String que contiene el usuario que realiza la acción.
	 * @param as_ipAddress Variable de tipo String que contiene la dirección ip remota desde donde seque realiza la acción.
	 * @param as_localIpAddress Variable de tipo String que contiene la dirección ip local desde donde seque realiza la acción.
	 * @return Retorna  un objeto  de tipo  AccPredioRegistro que coincide con los parametros de entrada.
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see AccPredioRegistro
	 */
	public synchronized AccPredioRegistro salvarAreaPredio(
	    AccPredioRegistro apr_accPredio, AreaPredio aap_areaPredio, String as_userId, String as_ipAddress,
	    String as_localIpAddress
	)
	    throws B2BException
	{
		AccPredioRegistro lpr_predio;
		DAOManager        ldm_manager;

		lpr_predio      = apr_accPredio;
		ldm_manager     = DaoManagerFactory.getDAOManager();

		try
		{
			if(lpr_predio != null)
			{
				if(aap_areaPredio != null)
				{
					{
						Double ld_tmp;

						ld_tmp = NumericUtils.getDoubleWrapper(aap_areaPredio.getAreaDelTerreno());

						if(NumericUtils.isValidDouble(ld_tmp))
						{
							double ld_temp;

							ld_temp = NumericUtils.getDouble(ld_tmp);

							if(ld_temp <= 0D)
								throw new B2BException(ErrorKeys.ERROR_AREA_DEL_TERRENO);
						}
						else
							throw new B2BException(ErrorKeys.ERROR_AREA_DEL_TERRENO);
					}

					{
						String ls_tmp;

						ls_tmp = aap_areaPredio.getAreaPrivada();

						if(StringUtils.isValidString(ls_tmp))
						{
							Double ld_tmp;

							ld_tmp = NumericUtils.getDoubleWrapper(ls_tmp);

							if(NumericUtils.isValidDouble(ld_tmp))
							{
								double ld_temp;

								ld_temp = NumericUtils.getDouble(ld_tmp);

								if(ld_temp <= 0D)
									throw new B2BException(ErrorKeys.ERROR_AREA_PRIVADA_CONSTRUIDA);
							}
						}
					}

					{
						String ls_tmp;

						ls_tmp = aap_areaPredio.getAreaConstruida();

						if(StringUtils.isValidString(ls_tmp))
						{
							Double ld_tmp;

							ld_tmp = NumericUtils.getDoubleWrapper(ls_tmp);

							if(NumericUtils.isValidDouble(ld_tmp))
							{
								double ld_temp;

								ld_temp = NumericUtils.getDouble(ld_tmp);

								if(ld_temp <= 0D)
									throw new B2BException(ErrorKeys.ERROR_AREA_CONSTRUIDA);
							}
						}
					}

					{
						String ls_tmp;

						ls_tmp = aap_areaPredio.getCoeficiente();

						if(StringUtils.isValidString(ls_tmp))
						{
							Double ld_tmp;

							ld_tmp = NumericUtils.getDoubleWrapper(ls_tmp);

							if(NumericUtils.isValidDouble(ld_tmp))
							{
								double ld_temp;

								ld_temp = NumericUtils.getDouble(ld_tmp);

								if(ld_temp > 100D)
									throw new B2BException(ErrorKeys.ERROR_COEFICIENTE);
								else if(ld_temp <= 0D)
									throw new B2BException(ErrorKeys.ERROR_COEFICIENTE);
							}
						}
					}

					{
						String ls_tmp;

						ls_tmp = aap_areaPredio.getUsoDelPredio();

						if(!StringUtils.isValidString(ls_tmp))
							throw new B2BException(ErrorKeys.USO_DEL_PREDIO_INVALIDO);
					}

					AccAreaPredio    lap_areaPredio;
					AccAreaPredioDAO laap_DAO;
					String           ls_idArea;

					lap_areaPredio     = new AccAreaPredio();
					laap_DAO           = DaoCreator.getAccAreaPredioDAO(ldm_manager);
					ls_idArea          = aap_areaPredio.getIdArea();

					if(StringUtils.isValidString(ls_idArea))
					{
						lap_areaPredio.setIdArea(ls_idArea);

						lap_areaPredio = laap_DAO.findById(lap_areaPredio);
					}
					else
					{
						lap_areaPredio.setIdCirculo(lpr_predio.getIdCirculo());
						lap_areaPredio.setIdMatricula(lpr_predio.getIdMatricula());

						lap_areaPredio = laap_DAO.findByCirculoMatricula(lap_areaPredio);
					}

					if(lap_areaPredio == null)
					{
						int li_secuencia;

						li_secuencia = laap_DAO.findSecuencia();

						if(li_secuencia > 0)
						{
							lap_areaPredio = new AccAreaPredio();

							lap_areaPredio.setIdArea(StringUtils.getString(li_secuencia));
							lap_areaPredio.setIdTurnoHistoria(aap_areaPredio.getIdTurnoHistoria());
							lap_areaPredio.setIdCirculo(lpr_predio.getIdCirculo());
							lap_areaPredio.setIdMatricula(lpr_predio.getIdMatricula());
							lap_areaPredio.setIdAnotacion(NumericUtils.getLongWrapper(NumericUtils.DEFAULT_LONG_VALUE));
							lap_areaPredio.setAreaPredio(
							    StringUtils.isValidString(aap_areaPredio.getAreaDelTerreno())
							    ? NumericUtils.getDoubleWrapper(aap_areaPredio.getAreaDelTerreno()) : null
							);
							lap_areaPredio.setAreaConstruida(
							    StringUtils.isValidString(aap_areaPredio.getAreaConstruida())
							    ? NumericUtils.getDoubleWrapper(aap_areaPredio.getAreaConstruida()) : null
							);
							lap_areaPredio.setAreaPrivadaConstruida(
							    StringUtils.isValidString(aap_areaPredio.getAreaPrivada())
							    ? NumericUtils.getDoubleWrapper(aap_areaPredio.getAreaPrivada()) : null
							);
							lap_areaPredio.setCoeficiente(
							    StringUtils.isValidString(aap_areaPredio.getCoeficiente())
							    ? NumericUtils.getDoubleWrapper(aap_areaPredio.getCoeficiente()) : null
							);
							lap_areaPredio.setIdUsuarioCreacion(as_userId);
							lap_areaPredio.setIpCreacion(as_ipAddress);
							lap_areaPredio.setIdTurno(lpr_predio.getIdTurno());

							laap_DAO.insert(lap_areaPredio);
						}
						else
							throw new B2BException(ErrorKeys.ACC_AREA_PREDIO_SEQUENCE);
					}
					else
					{
						lap_areaPredio.setIdAnotacion(NumericUtils.getLongWrapper(NumericUtils.DEFAULT_LONG_VALUE));
						lap_areaPredio.setAreaPredio(
						    StringUtils.isValidString(aap_areaPredio.getAreaDelTerreno())
						    ? NumericUtils.getDoubleWrapper(aap_areaPredio.getAreaDelTerreno()) : null
						);
						lap_areaPredio.setAreaConstruida(
						    StringUtils.isValidString(aap_areaPredio.getAreaConstruida())
						    ? NumericUtils.getDoubleWrapper(aap_areaPredio.getAreaConstruida()) : null
						);
						lap_areaPredio.setAreaPrivadaConstruida(
						    StringUtils.isValidString(aap_areaPredio.getAreaPrivada())
						    ? NumericUtils.getDoubleWrapper(aap_areaPredio.getAreaPrivada()) : null
						);
						lap_areaPredio.setCoeficiente(
						    StringUtils.isValidString(aap_areaPredio.getCoeficiente())
						    ? NumericUtils.getDoubleWrapper(aap_areaPredio.getCoeficiente()) : null
						);
						lap_areaPredio.setIdUsuarioModificacion(as_userId);
						lap_areaPredio.setIpModificacion(as_ipAddress);

						laap_DAO.updateById(lap_areaPredio);
					}

					{
						AccPredioRegistroDAO lpr_DAO;
						String               ls_idPredioRegistro;

						lpr_DAO                 = DaoCreator.getAccPredioRegistroDAO(ldm_manager);
						ls_idPredioRegistro     = lpr_predio.getIdPredioRegistro();

						if(StringUtils.isValidString(ls_idPredioRegistro))
							lpr_predio = lpr_DAO.findById(apr_accPredio);
						else
							lpr_predio = lpr_DAO.findByCirculoMatricula(apr_accPredio);

						if(lpr_predio != null)
						{
							lpr_predio.setIdTipoUsoSuelo(aap_areaPredio.getUsoDelPredio());
							lpr_predio.setIdUsuarioModificacion(as_userId);
							lpr_predio.setIpModificacion(as_ipAddress);

							lpr_DAO.updateById(lpr_predio);
						}
						else
							throw new B2BException(ErrorKeys.ACC_PREDIO_REGISTRO);
					}
				}
			}
			else
				throw new B2BException(ErrorKeys.ACC_PREDIO_REGISTRO);
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("salvarAreaPredio", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lpr_predio;
	}

	/**
	 * Método encargado de guardar toda la información referentes a los linderos y complementación de un predio determinado.
	 *
	 * @param alp_linderoPredio Objeto de tipo AccLinderoPredio que contiene los datos necesarios para realizar la inserción.
	 * @param aacp_complementacionPredio Objeto de tipo AccComplementacionPredio que contiene los datos necesarios para realizar la inserción.
	 * @param ab_accionNueva correspondiente al valor del tipo de objeto boolean
	 * @param acp_complementacionPredio Objeto de tipo ComplementacionPredio que contiene los datos necesarios para realizar la inserción.
	 * @param as_userId Id del usuario que ejecuta la acción
	 * @param as_remoteIp Dirección IP del cliente que ejecuta la acción
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public synchronized void salvarCabidaYLinderos(
	    AccLinderoPredio alp_linderoPredio, AccComplementacionPredio aacp_complementacionPredio, boolean ab_accionNueva,
	    ComplementacionPredio acp_complementacionPredio, String as_userId, String as_remoteIp
	)
	    throws B2BException
	{
		DAOManager ldm_manager;

		ldm_manager = DaoManagerFactory.getDAOManager();

		try
		{
			if((alp_linderoPredio != null) && (aacp_complementacionPredio != null))
			{
				String  ls_lindero;
				String  ls_complementacion;
				boolean lb_devolucionDigitadorMasivo;
				boolean lb_linderoNuevo;

				ls_lindero                       = alp_linderoPredio.getLindero();
				ls_complementacion               = aacp_complementacionPredio.getComplementacion();
				lb_devolucionDigitadorMasivo     = aacp_complementacionPredio.isDevolucionDigitadorMasivo();
				lb_linderoNuevo                  = false;

				if(!StringUtils.isValidString(ls_lindero))
				{
					lb_linderoNuevo = alp_linderoPredio.isLinderoNuevo();

					if(!lb_linderoNuevo)
						throw new B2BException(ErrorKeys.ERROR_LINDERO_INVALIDO);
				}

				if(lb_devolucionDigitadorMasivo)
				{
					if(StringUtils.isValidString(ls_complementacion))
					{
						if(ls_complementacion.trim().length() < 100)
							throw new B2BException(ErrorKeys.ERROR_COMPLEMENTACION_TAM);
					}
					else
						throw new B2BException(ErrorKeys.ERROR_COMPLEMENTACION_INVALIDO);
				}

				if(!lb_linderoNuevo)
				{
					AccLinderoPredioDAO lalp_DAO;
					AccLinderoPredio    lalp_lindero;
					String              ls_idLinderoPredio;

					lalp_DAO         = DaoCreator.getAccLinderoPredioDAO(ldm_manager);
					lalp_lindero     = null;

					ls_idLinderoPredio = lalp_DAO.findMaxIdLindero(alp_linderoPredio, false);

					if(StringUtils.isValidString(ls_idLinderoPredio))
					{
						alp_linderoPredio.setIdLinderoPredio(ls_idLinderoPredio);

						lalp_lindero = lalp_DAO.findById(alp_linderoPredio);
					}
					else
					{
						ls_idLinderoPredio = lalp_DAO.findMaxIdLindero(alp_linderoPredio, true);

						alp_linderoPredio.setIdLinderoPredio(ls_idLinderoPredio);

						lalp_lindero = lalp_DAO.findById(alp_linderoPredio);
					}

					if(lalp_lindero == null)
					{
						int li_secuencia;

						li_secuencia = lalp_DAO.findSecuencia();

						if(li_secuencia > 0)
						{
							lalp_lindero = new AccLinderoPredio();

							lalp_lindero.setIdLinderoPredio(StringUtils.getString(li_secuencia));
							lalp_lindero.setIdTurnoHistoria(alp_linderoPredio.getIdTurnoHistoria());
							lalp_lindero.setIdCirculo(alp_linderoPredio.getIdCirculo());
							lalp_lindero.setIdMatricula(alp_linderoPredio.getIdMatricula());
							lalp_lindero.setLindero(ls_lindero);
							lalp_lindero.setIdUsuarioCreacion(as_userId);
							lalp_lindero.setIpCreacion(as_remoteIp);
							lalp_lindero.setIdTurno(alp_linderoPredio.getIdTurno());

							lalp_DAO.insert(lalp_lindero);
						}
						else
							throw new B2BException(ErrorKeys.LINDERO_PREDIO_INSERT);
					}
					else
					{
						lalp_lindero.setLindero(ls_lindero);
						lalp_lindero.setIdUsuarioModificacion(as_userId);
						lalp_lindero.setIpModificacion(as_remoteIp);

						lalp_DAO.updateById(lalp_lindero, true);
					}
				}

				{
					if((aacp_complementacionPredio != null) && lb_devolucionDigitadorMasivo)
					{
						AccPredioRegistro    lapr_predio;
						AccPredioRegistroDAO lapr_DAO;

						lapr_predio     = new AccPredioRegistro();
						lapr_DAO        = DaoCreator.getAccPredioRegistroDAO(ldm_manager);

						lapr_predio.setIdCirculo(alp_linderoPredio.getIdCirculo());
						lapr_predio.setIdMatricula(alp_linderoPredio.getIdMatricula());

						lapr_predio = lapr_DAO.findByCirculoMatricula(lapr_predio);

						if(lapr_predio != null)
						{
							AccComplementacionPredioDAO lacp_DAO;
							ComplementacionPredioDAO    lcp_DAO;
							Long                        ll_idComplementacionPredio;
							String                      ls_idComplementacionPredio;

							lacp_DAO                       = DaoCreator.getAccComplementacionPredioDAO(ldm_manager);
							lcp_DAO                        = DaoCreator.getComplementacionPredioDAO(ldm_manager);
							ll_idComplementacionPredio     = lapr_predio.getIdComplementacion();

							if(NumericUtils.isValidLong(ll_idComplementacionPredio))
							{
								if(ab_accionNueva)
								{
									int li_secuenciaBng;
									li_secuenciaBng = lcp_DAO.findSecuence();

									if(li_secuenciaBng > 0)
									{
										acp_complementacionPredio.setIdComplementacion(String.valueOf(li_secuenciaBng));
										acp_complementacionPredio.setIdUsuarioCreacion(as_userId);
										acp_complementacionPredio.setIpCreacion(as_remoteIp);

										lcp_DAO.insert(acp_complementacionPredio);
									}
									else
										throw new B2BException(ErrorKeys.COMPLEMENTACION_PREDIO_INSERT);

									int li_secuenciaAcc;

									li_secuenciaAcc = lacp_DAO.findSecuence();

									if(li_secuenciaAcc > 0)
									{
										aacp_complementacionPredio.setIdComplementacion(
										    NumericUtils.getLongWrapper(li_secuenciaAcc)
										);
										aacp_complementacionPredio.setIdComplementacionOriginal(
										    NumericUtils.getLongWrapper(
										        acp_complementacionPredio.getIdComplementacion()
										    )
										);
										aacp_complementacionPredio.setTipoComplementacion(TipoComplementacionCommon.N);
										aacp_complementacionPredio.setIdUsuarioCreacion(as_userId);
										aacp_complementacionPredio.setIpCreacion(as_remoteIp);
										aacp_complementacionPredio.setIdTurno(alp_linderoPredio.getIdTurno());

										lacp_DAO.insert(aacp_complementacionPredio);
									}
									else
										throw new B2BException(ErrorKeys.COMPLEMENTACION_PREDIO_INSERT);
								}
								else
								{
									ComplementacionPredio lcp_complementacion;

									lcp_complementacion            = new ComplementacionPredio();
									ls_idComplementacionPredio     = StringUtils.getString(ll_idComplementacionPredio);

									lcp_complementacion.setIdComplementacion(ls_idComplementacionPredio);

									lcp_complementacion = lcp_DAO.findById(lcp_complementacion);

									if(lcp_complementacion != null)
									{
										lcp_complementacion.setComplementacion(
										    aacp_complementacionPredio.getComplementacion()
										);
										lcp_complementacion.setIdUsuarioModificacion(as_userId);
										lcp_complementacion.setIpModificacion(as_remoteIp);

										aacp_complementacionPredio.setIdComplementacionOriginal(
										    ll_idComplementacionPredio
										);

										lcp_DAO.updateById(lcp_complementacion);

										AccComplementacionPredio lacp_complementacionPredio;

										lacp_complementacionPredio = new AccComplementacionPredio();

										lacp_complementacionPredio.setIdComplementacionOriginal(
										    ll_idComplementacionPredio
										);
										lacp_complementacionPredio.setIdTurno(lapr_predio.getIdTurno());

										lacp_complementacionPredio = lacp_DAO.findByIdOriginal(
											    lacp_complementacionPredio
											);

										if(lacp_complementacionPredio != null)
										{
											lacp_complementacionPredio.setComplementacion(
											    aacp_complementacionPredio.getComplementacion()
											);
											lacp_complementacionPredio.setIdUsuarioModificacion(
											    alp_linderoPredio.getIdUsuarioModificacion()
											);
											lacp_complementacionPredio.setIpModificacion(
											    alp_linderoPredio.getIpModificacion()
											);

											lacp_DAO.update(lacp_complementacionPredio);
										}
									}
								}
							}
							else
							{
								if(ab_accionNueva)
								{
									int li_secuenciaBng;

									li_secuenciaBng = lcp_DAO.findSecuence();

									if(li_secuenciaBng > 0)
									{
										acp_complementacionPredio.setIdComplementacion(String.valueOf(li_secuenciaBng));
										acp_complementacionPredio.setIdUsuarioCreacion(as_userId);
										acp_complementacionPredio.setIpCreacion(as_remoteIp);

										lcp_DAO.insert(acp_complementacionPredio);
									}
									else
										throw new B2BException(ErrorKeys.COMPLEMENTACION_PREDIO_INSERT);
								}
								else
								{
									ComplementacionPredio lcp_complementacionPredio;

									lcp_complementacionPredio = lcp_DAO.findById(acp_complementacionPredio);

									if(lcp_complementacionPredio != null)
									{
										lcp_complementacionPredio.setComplementacion(
										    aacp_complementacionPredio.getComplementacion()
										);
										lcp_complementacionPredio.setIdUsuarioModificacion(as_userId);
										lcp_complementacionPredio.setIpModificacion(as_remoteIp);

										lcp_DAO.updateById(lcp_complementacionPredio);
									}
								}

								int    li_secuenciaAcc;
								String ls_tipoComplementacion;

								li_secuenciaAcc            = lacp_DAO.findSecuence();
								ls_tipoComplementacion     = ab_accionNueva ? TipoComplementacionCommon.N
									                                        : TipoComplementacionCommon.C;

								if(li_secuenciaAcc > 0)
								{
									aacp_complementacionPredio.setIdComplementacion(
									    NumericUtils.getLongWrapper(li_secuenciaAcc)
									);
									aacp_complementacionPredio.setIdComplementacionOriginal(
									    NumericUtils.getLongWrapper(acp_complementacionPredio.getIdComplementacion())
									);
									aacp_complementacionPredio.setTipoComplementacion(ls_tipoComplementacion);
									aacp_complementacionPredio.setIdUsuarioCreacion(as_userId);
									aacp_complementacionPredio.setIpCreacion(as_remoteIp);

									lacp_DAO.insert(aacp_complementacionPredio);
								}
								else
									throw new B2BException(ErrorKeys.COMPLEMENTACION_PREDIO_INSERT);
							}

							lapr_predio.setIdComplementacion(aacp_complementacionPredio.getIdComplementacionOriginal());
							lapr_predio.setIdUsuarioModificacion(alp_linderoPredio.getIdUsuarioModificacion());
							lapr_predio.setIpModificacion(alp_linderoPredio.getIpModificacion());

							lapr_DAO.updateById(lapr_predio);
						}
						else
							throw new B2BException(ErrorKeys.ACC_PREDIO_REGISTRO_INSERT);
					}
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("salvarCabidaYLinderos", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}
	}

	/**
	 * Salvar completitud complementacion.
	 *
	 * @param cc_parametros correspondiente al valor de cc parametros
	 * @param as_userId correspondiente al valor de as user id
	 * @param as_remoteIp correspondiente al valor de as remote ip
	 * @return retorna <code>true</code> si se cumple la condicion, de lo contario retorna <code>false</code>
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public synchronized boolean salvarCompletitudComplementacion(
	    CompletitudComplementacion cc_parametros, String as_circuloDestino, String as_userId, String as_remoteIp
	)
	    throws B2BException
	{
		DAOManager                    ldm_manager;
		CompletitudComplementacion    lcc_complementacion;
		TurnoHistoria                 th_complementacion;
		AccPredioRegistro             lapr_accPredioRegistro;
		boolean                       ab_return;
		Collection<AccPredioRegistro> lcapr_Predio;

		ldm_manager                = DaoManagerFactory.getDAOManager();
		lcc_complementacion        = new CompletitudComplementacion();
		th_complementacion         = new TurnoHistoria();
		lapr_accPredioRegistro     = new AccPredioRegistro();
		ab_return                  = false;
		lcapr_Predio               = new ArrayList<AccPredioRegistro>();

		if(cc_parametros != null)
		{
			try
			{
				th_complementacion.setIdTurnoHistoria(cc_parametros.getIdTurnoHistoria());
				th_complementacion = DaoCreator.getTurnoHistoriaDAO(ldm_manager).findById(th_complementacion);

				if(th_complementacion != null)
				{
					th_complementacion.setObservacionesNoTramite(cc_parametros.getJjustificacion());
					DaoCreator.getTurnoHistoriaDAO(ldm_manager).updateObservacionesNoTramite(th_complementacion);

					lapr_accPredioRegistro.setIdCirculo(cc_parametros.getIdCirculoRegistralDestino());

					lcapr_Predio = obtenerUltimaMatriculaCreada(th_complementacion, as_userId, as_remoteIp);

					if(lcapr_Predio != null)
					{
						int primero;

						primero = 0;

						for(AccPredioRegistro iapr_iterador : lcapr_Predio)
						{
							if((iapr_iterador != null) && (primero < 1))
							{
								lapr_accPredioRegistro.setIdMatricula(iapr_iterador.getIdMatricula());
								primero++;
							}
						}
					}

					lapr_accPredioRegistro = DaoCreator.getAccPredioRegistroDAO(ldm_manager)
							                               .findByCirculoMatricula(lapr_accPredioRegistro);

					if(lapr_accPredioRegistro != null)
						lcc_complementacion.setIdComplementacion(lapr_accPredioRegistro.getIdComplementacion());

					lcc_complementacion.setIdTurno(cc_parametros.getIdTurno());
					lcc_complementacion.setIdTurnoHistoria(cc_parametros.getIdTurnoHistoria());
					lcc_complementacion.setJustificacion(cc_parametros.getJjustificacion());
					lcc_complementacion.setIdCirculoRegistralDestino(as_circuloDestino);
					lcc_complementacion.setIdUsuarioCreacion(as_userId);

					DaoCreator.getCompletitudComplementacionDAO(ldm_manager).insertWithJustificacion(
					    lcc_complementacion
					);
					ab_return = true;
				}
			}
			catch(B2BException lb2be_e)
			{
				ldm_manager.setRollbackOnly();

				clh_LOGGER.error("salvarCompletitudComplementacion", lb2be_e);

				throw lb2be_e;
			}
			finally
			{
				ldm_manager.close();
			}
		}

		return ab_return;
	}

	/**
	 * Método encargado de guardar toda la información referente a los datos básicos de apertura del predio.
	 *
	 * @param adb_db Objeto de tipo DatosBasicos que contiene los datos necesarios para realizar la inserción y/o modificación.
	 * @param as_userId Variable de tipo String que contiene el usuario que realiza la acción.
	 * @param as_remoteIp Variable de tipo String que contiene la ip desde la cual se realiza la acción.
	 * @return Retorna  un objeto  de tipo  DatosBasicos que coincide con los parametros de entrada.
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see DatosBasicos
	 */
	public synchronized DatosBasicos salvarDatosBasicos(DatosBasicos adb_db, String as_userId, String as_remoteIp)
	    throws B2BException
	{
		DAOManager ldm_manager;

		ldm_manager = DaoManagerFactory.getDAOManager();

		try
		{
			if(adb_db != null)
			{
				adb_db     = salvarDatosBasicosUbicacion(adb_db, as_userId, as_remoteIp, ldm_manager);
				adb_db     = salvarDatosBasicosApertura(adb_db, as_userId, as_remoteIp, ldm_manager);
				adb_db     = salvarDatosBasicosMatriculas(adb_db, as_userId, as_remoteIp, ldm_manager);
				adb_db     = salvarDatosBasicosCatastral(adb_db, as_userId, as_remoteIp, ldm_manager);
			}
			else
				throw new B2BException(ErrorKeys.DEBE_DILIGENCIAR_DATOS_BASICOS);
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("salvarDatosBasicos", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return adb_db;
	}

	/**
	 * Método encargado de guardar la información del modulo de Apertura de datos básicos.
	 *
	 * @param adb_db Objeto que contiene la información a guardar.
	 * @param as_userId Variable de tipo String que contiene el id del usuario.
	 * @param as_remoteIp Variable de tipo String que contiene la ip del usuario
	 * @return Objeto que contiene la información guardada.
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see DatosBasicos
	 */
	public synchronized DatosBasicos salvarDatosBasicosApertura(
	    DatosBasicos adb_db, String as_userId, String as_remoteIp
	)
	    throws B2BException
	{
		DAOManager ldm_manager;

		ldm_manager = DaoManagerFactory.getDAOManager();

		try
		{
			if(adb_db != null)
				adb_db = salvarDatosBasicosApertura(adb_db, as_userId, as_remoteIp, ldm_manager);
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("salvarDatosBasicosApertura", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return adb_db;
	}

	/**
	 * Método encargado de guardar la información del modulo de Información catastral de datos básicos.
	 *
	 * @param adb_db Objeto que contiene la información a guardar.
	 * @param as_userId Variable de tipo String que contiene el id del usuario.
	 * @param as_remoteIp Variable de tipo String que contiene la ip del usuario
	 * @return Objeto que contiene la información guardada.
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see DatosBasicos
	 */
	public synchronized DatosBasicos salvarDatosBasicosCatastral(
	    DatosBasicos adb_db, String as_userId, String as_remoteIp
	)
	    throws B2BException
	{
		DAOManager ldm_manager;

		ldm_manager = DaoManagerFactory.getDAOManager();

		try
		{
			if(adb_db != null)
				adb_db = salvarDatosBasicosCatastral(adb_db, as_userId, as_remoteIp, ldm_manager);
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("salvarDatosBasicosCatastral", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return adb_db;
	}

	/**
	 * Método encargado de guardar la información del modulo de Matrículas con base de datos básicos.
	 *
	 * @param adb_db Objeto que contiene la información a guardar.
	 * @param as_userId Variable de tipo String que contiene el id del usuario.
	 * @param as_remoteIp Variable de tipo String que contiene la ip del usuario
	 * @return Objeto que contiene la información guardada.
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see DatosBasicos
	 */
	public synchronized DatosBasicos salvarDatosBasicosMatriculas(
	    DatosBasicos adb_db, String as_userId, String as_remoteIp
	)
	    throws B2BException
	{
		DAOManager ldm_manager;

		ldm_manager = DaoManagerFactory.getDAOManager();

		try
		{
			if(adb_db != null)
				adb_db = salvarDatosBasicosMatriculas(adb_db, as_userId, as_remoteIp, ldm_manager);
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("salvarDatosBasicosMatriculas", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return adb_db;
	}

	/**
	 * Método encargado de guardar la información del modulo de Ubicacion de datos básicos.
	 *
	 * @param adb_db Objeto que contiene la información a guardar.
	 * @param as_userId Variable de tipo String que contiene el id del usuario.
	 * @param as_remoteIp Variable de tipo String que contiene la ip del usuario
	 * @return Objeto que contiene la información guardada.
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see DatosBasicos
	 */
	public synchronized DatosBasicos salvarDatosBasicosUbicacion(
	    DatosBasicos adb_db, String as_userId, String as_remoteIp
	)
	    throws B2BException
	{
		DAOManager ldm_manager;

		ldm_manager = DaoManagerFactory.getDAOManager();

		try
		{
			if(adb_db != null)
				adb_db = salvarDatosBasicosUbicacion(adb_db, as_userId, as_remoteIp, ldm_manager);
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("salvarDatosBasicosUbicacion", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return adb_db;
	}

	/**
	 * Método encargado de guardar toda la información referente a las direcciones de un predio.
	 *
	 * @param aapm_predio Objeto que contiene la información a guardar.
	 * @param as_userId Variable de tipo String que contiene el usuario que realiza la acción.
	 * @param as_ipRemote Variable de tipo String que contiene la ip del usuario que realiza la acción.
	 * @return Colección de datos de tipo DireccionDelPredio que coincide con los parametros de entrada.
	 * @throws B2BException Señala que se ha producido una excepción
	 *
	 */
	public synchronized Collection<DireccionDelPredio> salvarDireccionPredio(
	    AccPredioRegistro aapm_predio, String as_userId, String as_ipRemote
	)
	    throws B2BException
	{
		DAOManager                     ldm_manager;
		Collection<DireccionDelPredio> lcdp_return;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		lcdp_return     = null;

		try
		{
			if(aapm_predio != null)
				salvarDireccionPredio(aapm_predio, as_userId, as_ipRemote, ldm_manager);
			else
				throw new B2BException(ErrorKeys.DIRECCION_PREDIO_GUARDAR);
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("salvarDireccionPredio", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lcdp_return;
	}

	/**
	 * Método encargado de guardar toda la información referente a las direcciones de un predio.
	 *
	 * @param acddp_db colección de datos de tipo DireccionDelPredio que contiene los parámetros  necesarios para insertar   en la tabla SDB_ACC_DIRECCION_PREDIO
	 * @param as_turnoHistoria Variable de tipo String que contiene el id turno historia determinado.
	 * @param as_userId Variable de tipo String que contiene el usuario que realiza la acción.
	 * @param aapm_predio objeto de tipo  AccPredioRegistro que contiene los datos necesarios para validar la existencia  de una dirección a insertar.
	 * @param as_ipRemote correspondiente al valor del tipo de objeto String
	 * @return Retorna  una colección de datos   de tipo  DireccionDelPredio que coincide con los parametros de entrada.
	 * @throws B2BException Señala que se ha producido una excepción
	 *
	 */
	public synchronized Collection<DireccionDelPredio> salvarDireccionPredio(
	    Collection<DireccionDelPredio> acddp_db, String as_turnoHistoria, String as_userId,
	    AccPredioRegistro aapm_predio, String as_ipRemote
	)
	    throws B2BException
	{
		DAOManager                     ldm_manager;
		Collection<DireccionDelPredio> lcdpa_cdpa;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		lcdpa_cdpa      = null;

		try
		{
			if(aapm_predio != null)
			{
				aapm_predio.setDireccionesPredio(acddp_db);
				aapm_predio.setIdTurnoHistoria(NumericUtils.getLongWrapper(as_turnoHistoria));

				lcdpa_cdpa = salvarDireccionPredio(aapm_predio, as_userId, as_ipRemote, ldm_manager);
			}
			else
				throw new B2BException(ErrorKeys.DIRECCION_PREDIO_GUARDAR);
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("salvarDireccionPredio", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lcdpa_cdpa;
	}

	/**
	 * Guarda el proceso de antiguo sistema y avanza el caso a una nueva etapa.
	 *
	 * @param as_idTurnoHistoria id turno historia del proceso en tramite
	 * @param as_userId id del usuario que ejecuta la acción
	 * @param as_remoteIpAddress Dirección IP del cliente que ejecuta la acción
	 * @param as_observaciones observaciones del tramite
	 * @return true si el proceso se completo de manera exitosa
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public synchronized boolean salvarProcesoAntSistema(
	    String as_idTurnoHistoria, String as_userId, String as_remoteIpAddress, String as_observaciones
	)
	    throws B2BException
	{
		boolean lb_return;

		lb_return = false;

		try
		{
			lb_return = salvarProcesoAntSistema(
				    as_idTurnoHistoria, as_userId, as_remoteIpAddress, as_observaciones, false
				);
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("salvarProcesoAntSistema", lb2be_e);

			throw lb2be_e;
		}

		return lb_return;
	}

	/**
	 * Salvar proceso ant sistema.
	 *
	 * @param as_idTurnoHistoria correspondiente al valor de as id turno historia
	 * @param as_userId correspondiente al valor de as user id
	 * @param as_remoteIpAddress correspondiente al valor de as remote ip address
	 * @param as_observaciones correspondiente al valor de as observaciones
	 * @param ab_solicitaComplementacionOtroCirculo correspondiente al valor de ab solicita complementacion otro circulo
	 * @return retorna <code>true</code> si se cumple la condicion, de lo contario retorna <code>false</code>
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public synchronized boolean salvarProcesoAntSistema(
	    String as_idTurnoHistoria, String as_userId, String as_remoteIpAddress, String as_observaciones,
	    boolean ab_solicitaComplementacionOtroCirculo
	)
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
				TurnoHistoria    lth_turnoHistoriAnterior;

				lth_turnoHistoria            = new TurnoHistoria();
				lth_DAO                      = DaoCreator.getTurnoHistoriaDAO(ldm_manager);
				lth_turnoHistoriAnterior     = new TurnoHistoria();

				lth_turnoHistoria.setIdTurnoHistoria(NumericUtils.getLongWrapper(as_idTurnoHistoria));

				lth_turnoHistoria = lth_DAO.findById(lth_turnoHistoria);

				if(lth_turnoHistoria != null)
				{
					String ls_idTurno;
					Long   ll_turnoAnterior;

					ls_idTurno           = lth_turnoHistoria.getIdTurno();
					ll_turnoAnterior     = NumericUtils.getLongWrapper(lth_turnoHistoria.getIdAnterior());

					lth_turnoHistoriAnterior.setIdTurnoHistoria(ll_turnoAnterior);

					lth_turnoHistoriAnterior = lth_DAO.findById(lth_turnoHistoriAnterior);

					if(lth_turnoHistoriAnterior == null)
						throw new B2BException(ErrorKeys.NO_SE_ENCONTRO_TURNO_HISTORIA);

					Long ll_idEtapaTurnoHistoriaAnt;
					Long ll_idMotivoTurnoHistoriaAnt;

					ll_idEtapaTurnoHistoriaAnt      = NumericUtils.getLongWrapper(
						    lth_turnoHistoriAnterior.getIdEtapa()
						);
					ll_idMotivoTurnoHistoriaAnt     = lth_turnoHistoriAnterior.getIdMotivo();

					if(StringUtils.isValidString(ls_idTurno))
					{
						Turno lt_turno;
						long  ll_idMotivo;

						lt_turno        = DaoCreator.getTurnoDAO(ldm_manager).findById(ls_idTurno);
						ll_idMotivo     = 0;

						if(lt_turno != null)
						{
							Collection<DatosAntSistema> lcdas_data;
							long                        ll_idEtapa;
							String                      ls_idProceso;
							String                      ls_idSubProceso;

							lcdas_data          = obtenerDatosAntSistema(ldm_manager, ls_idTurno, true);
							ll_idEtapa          = NumericUtils.getLong(lth_turnoHistoria.getIdEtapa());
							ls_idProceso        = StringUtils.getStringNotNull(lth_turnoHistoria.getIdProceso());
							ls_idSubProceso     = StringUtils.getStringNotNull(lth_turnoHistoria.getIdSubproceso());

							if(CollectionUtils.isValidCollection(lcdas_data))
							{
								boolean                   lb_continuar;
								Iterator<DatosAntSistema> lidas_iterator;

								lb_continuar       = true;
								lidas_iterator     = lcdas_data.iterator();

								while(lidas_iterator.hasNext() && lb_continuar)
								{
									DatosAntSistema ldas_iterador;

									ldas_iterador = lidas_iterator.next();

									if(ldas_iterador != null)
									{
										Map<String, Boolean> lmsb_motivos;
										String               ls_requiereFirma;
										boolean              lb_justificacionFirma;

										ls_requiereFirma          = ldas_iterador.getRequiereFirmaLibro();
										lb_justificacionFirma     = StringUtils.isValidString(ls_requiereFirma)
												&& ls_requiereFirma.equalsIgnoreCase(EstadoCommon.S);
										lmsb_motivos              = findProcesoAntiguoSistema(
											    ls_idTurno, ldas_iterador.getIdDatosAntSistema(), as_idTurnoHistoria
											);

										if(CollectionUtils.isValidCollection(lmsb_motivos))
										{
											if(lmsb_motivos.containsKey(IdentificadoresCommon.CREAR_MATRICULA))
											{
												if(ll_idEtapa == EtapaCommon.ID_ETAPA_CALIFICACION_ANTIGUO_SISTEMA)
												{
													if(ls_idProceso.equals(ProcesoCommon.ID_PROCESO_1))
													{
														if(
														    (ls_idSubProceso.equals(
															        SubProcesoCommon.CERTIFICADO_ANTIGUO_SISTEMA
															    )
															    || ls_idSubProceso.equals(
															        SubProcesoCommon.CERTIFICADO_AMPLIACION
															    )
															    || ls_idSubProceso.equals(
															        SubProcesoCommon.CERTIFICADO_DE_SERVIDUMBRE
															    )
															    || ls_idSubProceso.equals(
															        SubProcesoCommon.CERTIFICADO_PERTENENCIA
															    )) && ab_solicitaComplementacionOtroCirculo
														)
															ll_idMotivo = MotivoTramiteCommon.INFORME_DE_BUSQUEDA_PARA_CORRECCIONES;
														else if(
														    (ls_idSubProceso.equals(
															        SubProcesoCommon.CERTIFICADO_ANTIGUO_SISTEMA
															    )
															    || ls_idSubProceso.equals(
															        SubProcesoCommon.CERTIFICADO_AMPLIACION
															    )
															    || ls_idSubProceso.equals(
															        SubProcesoCommon.CERTIFICADO_DE_SERVIDUMBRE
															    )
															    || ls_idSubProceso.equals(
															        SubProcesoCommon.CERTIFICADO_PERTENENCIA
															    ))
															    && (ll_idEtapaTurnoHistoriaAnt.longValue() == EtapaCommon.SOLICITAR_COMPLEMENTACION_OTRO_CICRCULO)
															    && (ll_idMotivoTurnoHistoriaAnt.longValue() == MotivoTramiteCommon.APROBADO_CIRCULO_DESTINO)
														)
															ll_idMotivo = MotivoTramiteCommon.COMPLEMETACION_APROBADA_CIRCULO_REGISTRAL_DESTINO_ENVIO_APROBACION_CIRCULO_REGISTRAL_ORIGEN;
														else
															ll_idMotivo = lb_justificacionFirma
																? MotivoTramiteCommon.CREAR_MATRICULA_DESDE_ANTIGUO_SISTEMA_101_FIRMA
																: MotivoTramiteCommon.CREAR_MATRICULA_PARA_CERTIFICADOS;
													}
													else if(ls_idProceso.equals(ProcesoCommon.ID_PROCESO_3))
														ll_idMotivo = lb_justificacionFirma
															? MotivoTramiteCommon.CREAR_MATRICULA_DESDE_ANTIGUO_SISTEMA_101_FIRMA
															: MotivoTramiteCommon.CREAR_MATRICULA_PARA_CORRECCIONES;
													else
														ll_idMotivo = lb_justificacionFirma
															? MotivoTramiteCommon.CREAR_MATRICULA_DESDE_ANTIGUO_SISTEMA_101_FIRMA
															: MotivoTramiteCommon.CREAR_MATRICULA_DESDE_ANTIGUO_SISTEMA_101;
												}
												else
													ll_idMotivo = lb_justificacionFirma
														? MotivoTramiteCommon.CREAR_MATRICULA_DESDE_ANTIGUO_SISTEMA_FIRMA
														: MotivoTramiteCommon.CREAR_MATRICULA_DESDE_ANTIGUO_SISTEMA;
											}
											else if(lmsb_motivos.containsKey(IdentificadoresCommon.ASOCIAR_MATRICULA))
												if(ll_idEtapa == EtapaCommon.ID_ETAPA_CALIFICACION_ANTIGUO_SISTEMA)
												{
													if(ls_idProceso.equals(ProcesoCommon.ID_PROCESO_1))
														ll_idMotivo = lb_justificacionFirma
															? MotivoTramiteCommon.ASOCIAR_MATRICULA_PARA_CALIFICACION_FIRMA
															: MotivoTramiteCommon.ASOCIAR_MATRICULA_PARA_CERTIFICADOS;
													else if(ls_idProceso.equals(ProcesoCommon.ID_PROCESO_3))
														ll_idMotivo = lb_justificacionFirma
															? MotivoTramiteCommon.ASOCIAR_MATRICULA_PARA_CALIFICACION_FIRMA
															: MotivoTramiteCommon.ASOCIAR_MATRICULA_PARA_CORRECCIONES;
													else
														ll_idMotivo = lb_justificacionFirma
															? MotivoTramiteCommon.ASOCIAR_MATRICULA_PARA_CALIFICACION_FIRMA
															: MotivoTramiteCommon.ASOCIAR_MATRICULA_PARA_CALIFICACION;
												}
												else
													ll_idMotivo = lb_justificacionFirma
														? MotivoTramiteCommon.ASOCIAR_MATRICULA_DESDE_ANTIGUO_SISTEMA_FIRMA
														: MotivoTramiteCommon.ASOCIAR_MATRICULA_DESDE_ANTIGUO_SISTEMA;
											else if(
											    lmsb_motivos.containsKey(IdentificadoresCommon.INFORME_BUSQUEDA)
												    || StringUtils.isValidString(as_observaciones)
											)
											{
												if(ab_solicitaComplementacionOtroCirculo)
													ll_idMotivo = MotivoTramiteCommon.INFORME_DE_BUSQUEDA_PARA_CORRECCIONES;
												else
													ll_idMotivo = seleccionarMotivoTramiteInforme(
														    ll_idEtapa, ls_idProceso, lb_justificacionFirma
														);
											}
										}
										else if(
										    (ll_idEtapa == EtapaCommon.ID_ETAPA_CALIFICACION_ANTIGUO_SISTEMA)
											    || (ll_idEtapa == EtapaCommon.ID_ETAPA_ANTIGUO_SISTEMA)
										)
											ll_idMotivo = seleccionarMotivoTramiteInforme(
												    ll_idEtapa, ls_idProceso, lb_justificacionFirma
												);

										if(lb_justificacionFirma)
											lb_continuar = false;
									}
								}
							}
							else
							{
								String           ls_idConstante;
								Constantes       lc_constante;
								TipoDocumental   ltd_tipoDoc;
								DocumentosSalida lds_docs;

								ls_idConstante     = ConstanteCommon.TIPO_DOCUMENTAL_INFORME_BUSQUEDA;
								lc_constante       = DaoCreator.getConstantesDAO(ldm_manager).findById(ls_idConstante);

								if(lc_constante == null)
								{
									Object[] loa_messageArgs;

									loa_messageArgs        = new String[1];
									loa_messageArgs[0]     = ls_idConstante;

									throw new B2BException(ErrorKeys.ERROR_SIN_CONSTANTE, loa_messageArgs);
								}

								ltd_tipoDoc = DaoCreator.getTipoDocumentalDAO(ldm_manager)
										                    .findById(lc_constante.getCaracter());

								if(ltd_tipoDoc == null)
									throw new B2BException(ErrorKeys.ERROR_SIN_TIPO_DOCUMENTAL);

								lds_docs = new DocumentosSalida();

								lds_docs.setIdSolicitud(lt_turno.getIdSolicitud());
								lds_docs.setIdTurnoHistoria(
								    NumericUtils.getInteger(lth_turnoHistoria.getIdTurnoHistoria())
								);
								lds_docs.setTipo(ltd_tipoDoc.getNombre());
								lds_docs.setEstado(EstadoCommon.ENTREGA);

								lds_docs = DaoCreator.getDocumentosSalidaDAO(ldm_manager)
										                 .findDocumentByTurnoHistoriaTipoEstado(lds_docs);

								if((lds_docs != null) || StringUtils.isValidString(as_observaciones))
									ll_idMotivo = seleccionarMotivoTramiteInforme(ll_idEtapa, ls_idProceso, false);
							}

							if(ll_idMotivo > 0)
							{
								if(CollectionUtils.isValidCollection(lcdas_data))
								{
									StringBuilder lsb_observacionesNoTramite;

									lsb_observacionesNoTramite = new StringBuilder();

									for(DatosAntSistema ldas_dato : lcdas_data)
									{
										if(ldas_dato != null)
										{
											String ls_matriculas;

											ls_matriculas = ldas_dato.getMatriculasAsociadas();

											lsb_observacionesNoTramite.append(ldas_dato.getAccionDescripcion());

											if(StringUtils.isValidString(ls_matriculas))
												lsb_observacionesNoTramite.append(ls_matriculas);

											lsb_observacionesNoTramite.append(IdentificadoresCommon.PUNTO);
											lsb_observacionesNoTramite.append(IdentificadoresCommon.ESPACIO_VACIO);
										}
									}

									lth_turnoHistoria.setObservacionesNoTramite(lsb_observacionesNoTramite.toString());
								}
								else
									lth_turnoHistoria.setObservacionesNoTramite(AccionAntSistemaCommon.DESCRIPCION_I);

								terminarTurnoHistoriaYCrearEtapa(
								    lth_turnoHistoria, ldm_manager, new MotivoTramite(ll_idEtapa, ll_idMotivo),
								    as_userId, as_remoteIpAddress, EstadoCommon.TERMINADA
								);

								lb_return = true;
							}
						}
					}
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("salvarProcesoAntSistema", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lb_return;
	}

	/**
	 * Método de terminación de etapa
	 * @param al_etapa con la etapa actual
	 * @param as_idTurnoHistoria con el turno historia de la transacción
	 * @param al_motivo con el motivo de la etapa
	 * @param as_userId con el usuario de la transacción
	 * @param as_remoteIp con ip del usuario de la transacción
	 * @throws B2BException
	 */
	public void terminarTurnoHistoria(
	    long al_etapa, String as_idTurnoHistoria, long al_motivo, String as_userId, String as_remoteIp
	)
	    throws B2BException
	{
		DAOManager ldm_manager;

		ldm_manager = DaoManagerFactory.getDAOManager();

		try
		{
			TurnoHistoria lth_turnoHistoria;
			MotivoTramite lmt_motivoTramite;
			long          ll_idEtapa;

			lth_turnoHistoria     = new TurnoHistoria();
			lmt_motivoTramite     = new MotivoTramite();
			ll_idEtapa            = al_etapa;

			lth_turnoHistoria.setIdTurnoHistoria(NumericUtils.getLongWrapper(as_idTurnoHistoria));

			lmt_motivoTramite.setIdMotivo(al_motivo);
			lmt_motivoTramite.setIdEtapaOrigen(ll_idEtapa);

			terminarTurnoHistoriaYCrearEtapa(
			    lth_turnoHistoria, ldm_manager, lmt_motivoTramite, as_userId, as_remoteIp, EstadoCommon.TERMINADA
			);
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("terminarTurnoHistoria", lb2be_e);
			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}
	}

	/**
	 * Método  encargado de actualizar la fecha inicial para un id turno historia respectivo.
	 *
	 * @param ath_th Objeto de tipo TurnoHistoria que contiene el id turno historia requerido para ejecutar sentencia SQL.
	 * @param as_userId   Variable de tipo String que contiene el usuario que realiza la acción.
	 * @param as_localIp  Variable de tipo String que contiene la dirección ip local  desde donde seque realiza la acción.
	 * @param as_remoteIp Variable de tipo String que contiene la dirección ip remota desde donde seque realiza la acción.
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public synchronized void updateFechaInicial(
	    TurnoHistoria ath_th, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		DAOManager ldm_manager;

		ldm_manager = DaoManagerFactory.getDAOManager();

		try
		{
			if(ath_th != null)
			{
				ath_th.setIdUsuarioModificacion(as_userId);
				ath_th.setIpModificacion(as_remoteIp);

				DaoCreator.getTurnoHistoriaDAO(ldm_manager).updateFechaInicial(ath_th);
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("updateFechaInicial", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}
	}

	/**
	 * Método para validar si el acto/documento es de ejecutoria.
	 *
	 * @param as_tipoDocumento correspondiente al valor del tipo de objeto String
	 * @return verdadero, si se cumple la condicion, de lo contario retorna falso
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public synchronized boolean validarActoDocumentoEjecutoria(String as_tipoDocumento)
	    throws B2BException
	{
		boolean    lb_return;
		DAOManager ldm_manager;

		lb_return       = false;
		ldm_manager     = DaoManagerFactory.getDAOManager();

		try
		{
			if(StringUtils.isValidString(as_tipoDocumento))
			{
				Constantes lc_constante;

				lc_constante = new Constantes();

				lc_constante.setIdConstante(ConstanteCommon.DOCUMENTO_PUBLICO_EJECUTORIA);

				lc_constante = DaoCreator.getConstantesDAO(ldm_manager).findById(lc_constante);

				if(lc_constante != null)
				{
					String ls_caracter;

					ls_caracter = lc_constante.getCaracter();

					if(StringUtils.isValidString(ls_caracter))
					{
						Map<String, String> lmss_listado;

						lmss_listado = ListadoCodigosConstantes.generarCodigos(ls_caracter);

						if(CollectionUtils.isValidCollection(lmss_listado))
							lb_return = lmss_listado.containsKey(as_tipoDocumento);
					}
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("validarActoDocumentoEjecutoria", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lb_return;
	}

	/**
	 * Metodo encargado de validar los datos a actualizar de datos anotación.
	 *
	 * @param la_iterador correspondiente al valor del tipo de objeto Anotacion
	 * @param aapc_anotacionPredioCiudadano correspondiente al valor del tipo de objeto AnotacionPredioCiudadano
	 * @param lapcd_DAO correspondiente al valor del tipo de objeto AnotacionPredioCiudadanoDAO
	 * @param lpd_DAO correspondiente al valor del tipo de objeto PersonaDAO
	 * @param acd_DAO correspondiente al valor del tipo de objeto ConstantesDAO
	 * @param asid_DAO correspondiente al valor del tipo de objeto SolicitudIntervinienteDAO
	 * @param asd_DAO correspondiente al valor del tipo de objeto SolicitudDAO
	 * @param as_idSolicitud correspondiente al valor del tipo de objeto String
	 * @param ls_idAnotacionPredio correspondiente al valor del tipo de objeto String
	 * @param as_userId correspondiente al valor del tipo de objeto String
	 * @param as_remoteIp correspondiente al valor del tipo de objeto String
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public synchronized void validarActualizarOCrearIntervinientes(
	    DAOManager adm_manager, Anotacion la_iterador, AnotacionPredioCiudadano aapc_anotacionPredioCiudadano,
	    AnotacionPredioCiudadanoDAO lapcd_DAO, PersonaDAO lpd_DAO, ConstantesDAO acd_DAO,
	    SolicitudIntervinienteDAO asid_DAO, SolicitudDAO asd_DAO, String as_idSolicitud, String ls_idAnotacionPredio,
	    String as_userId, String as_remoteIp
	)
	    throws B2BException
	{
		try
		{
			if(la_iterador != null)
			{
				Collection<Anotacion> lca_intervinientes;

				lca_intervinientes = la_iterador.getIntervinientesAgregados();

				if(CollectionUtils.isValidCollection(lca_intervinientes))
				{
					AnotacionPredioCiudadano              lapc_arg;
					Collection<AnotacionPredioCiudadano>  lcapc_ciudadanos;
					Map<String, AnotacionPredioCiudadano> lmapc_ciudadanos;

					lapc_arg             = new AnotacionPredioCiudadano();
					lmapc_ciudadanos     = new HashMap<String, AnotacionPredioCiudadano>();

					lapc_arg.setIdAnotacionPredio(ls_idAnotacionPredio);

					lcapc_ciudadanos = lapcd_DAO.findByIdAnotacion(lapc_arg);

					for(Anotacion la_interviniente : lca_intervinientes)
					{
						if(la_interviniente != null)
						{
							Persona                lp_persona;
							SolicitudInterviniente lsi_solicitudInterviniente;

							lp_persona                     = la_interviniente.getPersona();
							lsi_solicitudInterviniente     = la_interviniente.getSolicitudInterviniente();

							if(lp_persona != null)
							{
								String  ls_tipoPersona;
								String  ls_tipoDocumento;
								boolean lb_anotacionIndividual;

								ls_tipoPersona             = lp_persona.getIdTipoPersona();
								ls_tipoDocumento           = lp_persona.getIdDocumentoTipo();
								lb_anotacionIndividual     = la_iterador.isAnotacionIndividual();

								if(!StringUtils.isValidString(ls_tipoPersona))
									throw new B2BException(ErrorKeys.DEBE_SELECCIONAR_TIPO_PERSONA);

								if(!StringUtils.isValidString(ls_tipoDocumento))
									throw new B2BException(ErrorKeys.DEBE_SELECCIONAR_TIPO_DOC);

								{
									String ls_documento;
									ls_documento = lp_persona.getNumeroDocumento();

									if(!StringUtils.isValidString(ls_documento))
										throw new B2BException(ErrorKeys.DOCUMENTO_INVALIDO);
								}

								if(ls_tipoPersona.equalsIgnoreCase(EstadoCommon.N))
								{
									if(
									    !ls_tipoDocumento.equalsIgnoreCase(IdentificadoresCommon.NIT)
										    && !ls_tipoDocumento.equalsIgnoreCase("-1")
									)
									{
										if(!lb_anotacionIndividual)
										{
											String ls_nacionalidad;
											ls_nacionalidad = lp_persona.getIdPais();

											if(!StringUtils.isValidString(ls_nacionalidad))
												throw new B2BException(ErrorKeys.DEBE_SELECCIONAR_NACIONALIDAD);
										}

										{
											String ls_primerNombre;
											ls_primerNombre = lp_persona.getPrimerNombre();

											if(!StringUtils.isValidString(ls_primerNombre))
												throw new B2BException(ErrorKeys.DEBE_DIGITAR_PRIMER_NOMBRE);
										}

										{
											String ls_primerApellido;
											ls_primerApellido = lp_persona.getPrimerApellido();

											if(!StringUtils.isValidString(ls_primerApellido))
												throw new B2BException(ErrorKeys.DEBE_DIGITAR_PRIMER_APELLIDO);
										}

										if(lsi_solicitudInterviniente != null)
										{
											String ls_rol;
											ls_rol = lsi_solicitudInterviniente.getRolPersona();

											if(!StringUtils.isValidString(ls_rol))
												throw new B2BException(ErrorKeys.DEBE_SELECCIONAR_ROL);
										}
									}
									else
										throw new B2BException(ErrorKeys.DEBE_SELECCIONAR_NATURAL);
								}

								if(ls_tipoPersona.equalsIgnoreCase(EstadoCommon.J))
								{
									if(ls_tipoDocumento.equalsIgnoreCase(IdentificadoresCommon.NIT))
									{
										if(!lb_anotacionIndividual)
										{
											String ls_nacionalidad;
											ls_nacionalidad = lp_persona.getIdPais();

											if(!StringUtils.isValidString(ls_nacionalidad))
												throw new B2BException(ErrorKeys.DEBE_SELECCIONAR_NACIONALIDAD);
										}

										if(lsi_solicitudInterviniente != null)
										{
											String ls_rol;
											ls_rol = lsi_solicitudInterviniente.getRolPersona();

											if(!StringUtils.isValidString(ls_rol))
												throw new B2BException(ErrorKeys.DEBE_SELECCIONAR_ROL);
										}

										{
											String ls_razonSocial;
											ls_razonSocial = lp_persona.getRazonSocial();

											if(!StringUtils.isValidString(ls_razonSocial))
												throw new B2BException(ErrorKeys.DEBE_DIGITAR_RAZON_SOCIAL);
										}
									}
									else
										throw new B2BException(ErrorKeys.DEBE_SELECCIONAR_JURIDICO);
								}

								{
									String ls_idPersona;

									ls_idPersona = null;

									if(lp_persona != null)
									{
										if(
										    StringUtils.isValidString(lp_persona.getIdDocumentoTipo())
											    && lp_persona.getIdDocumentoTipo().equalsIgnoreCase(EstadoCommon.SE)
										)
										{
											Constantes lc_constante;

											lc_constante = new Constantes();

											lc_constante.setIdConstante(
											    ConstanteCommon.CONSECUTIVO_TIPO_DOCUMENTO_SECUENCIA
											);

											lc_constante = acd_DAO.findById(lc_constante);

											if(lc_constante != null)
											{
												BigInteger lbi_secuencia;

												lbi_secuencia = lc_constante.getEntero().add(BigInteger.valueOf(1));

												lc_constante.setEntero(lbi_secuencia);

												acd_DAO.insertOrUpdate(lc_constante, false);

												lp_persona.setNumeroDocumento(StringUtils.getString(lbi_secuencia));
											}
										}

										if(lb_anotacionIndividual)
											lp_persona.setIdPais(IdentificadoresCommon.INDICATIVO_PAIS_DEFAULT);

										String ls_idFlagPersona;

										ls_idFlagPersona = marcarFlagPersona(
											    adm_manager, lp_persona, as_userId, as_remoteIp
											);

										if(StringUtils.isValidString(ls_idFlagPersona))
											lpd_DAO.findById(lp_persona);

										{
											Persona lp_temp;

											lp_temp = lpd_DAO.findDataCalificador(lp_persona);

											if(lp_temp != null)
											{
												lp_temp     = lpd_DAO.findById(lp_temp);

												ls_idPersona = ls_idFlagPersona;
											}
										}
									}

									{
										AnotacionPredioCiudadano lapc_anotacionPredioCiudadano;

										lapc_anotacionPredioCiudadano = la_interviniente.getAnotacionPredioCiudadano();

										if(
										    (lapc_anotacionPredioCiudadano != null)
											    && (aapc_anotacionPredioCiudadano != null)
										)
										{
											lapc_anotacionPredioCiudadano.setIdAnotacionPredio(ls_idAnotacionPredio);
											lapc_anotacionPredioCiudadano.setIdCirculo(
											    aapc_anotacionPredioCiudadano.getIdCirculo()
											);
											lapc_anotacionPredioCiudadano.setIdMatricula(
											    aapc_anotacionPredioCiudadano.getIdMatricula()
											);
											lapc_anotacionPredioCiudadano.setIdAnotacion(
											    aapc_anotacionPredioCiudadano.getIdAnotacion()
											);
											lapc_anotacionPredioCiudadano.setIdPersona(ls_idPersona);

											{
												String ls_rolPersona;
												String ls_de;
												String ls_a;
												String ls_testador;

												ls_rolPersona     = lsi_solicitudInterviniente.getRolPersona();
												ls_de             = RolCommon.DE;
												ls_a              = RolCommon.A;
												ls_testador       = RolCommon.TESTADOR;

												if(!StringUtils.isValidString(ls_rolPersona))
													lapc_anotacionPredioCiudadano.setRolPersona(RolCommon.A);
												else if(
												    !ls_rolPersona.equalsIgnoreCase(ls_de)
													    && !ls_rolPersona.equalsIgnoreCase(ls_a)
													    && !ls_rolPersona.equalsIgnoreCase(ls_testador)
												)
												{
													Object[] loa_object;
													int      li_contador;

													loa_object      = new String[4];
													li_contador     = 0;

													loa_object[li_contador++]     = ls_rolPersona;
													loa_object[li_contador++]     = ls_de;
													loa_object[li_contador++]     = ls_a;
													loa_object[li_contador++]     = ls_testador;

													throw new B2BException(
													    ErrorKeys.ERROR_ROL_PERSONA_INVALIDO, loa_object
													);
												}

												lapc_anotacionPredioCiudadano.setRolPersona(ls_rolPersona);
											}

											lapc_anotacionPredioCiudadano.setIdTurno(
											    aapc_anotacionPredioCiudadano.getIdTurno()
											);

											lapc_anotacionPredioCiudadano.setIdUsuarioCreacion(as_userId);
											lapc_anotacionPredioCiudadano.setIpCreacion(as_remoteIp);
											lapc_anotacionPredioCiudadano.setIdUsuarioModificacion(as_userId);
											lapc_anotacionPredioCiudadano.setIpModificacion(as_remoteIp);

											{
												AnotacionPredioCiudadano lapc_tmp;

												lapc_tmp = lapcd_DAO.findByAnotPredioCirMatAnotPerRol(
													    lapc_anotacionPredioCiudadano
													);

												if(lapc_tmp != null)
												{
													lapc_anotacionPredioCiudadano.setIdAnotacionPredioCiudadano(
													    lapc_tmp.getIdAnotacionPredioCiudadano()
													);

													lapcd_DAO.modificarCiudadano(lapc_anotacionPredioCiudadano);
												}
												else
													lapcd_DAO.insert(lapc_anotacionPredioCiudadano);
											}

											lmapc_ciudadanos.put(
											    lapc_anotacionPredioCiudadano.getIdAnotacionPredioCiudadano(),
											    lapc_anotacionPredioCiudadano
											);
										}
									}
								}
							}
						}
					}

					if(
					    CollectionUtils.isValidCollection(lcapc_ciudadanos)
						    && CollectionUtils.isValidCollection(lmapc_ciudadanos)
					)
					{
						for(AnotacionPredioCiudadano lapc_iterador : lcapc_ciudadanos)
						{
							if(lapc_iterador != null)
							{
								String ls_idAnotacionCiudadano;

								ls_idAnotacionCiudadano = lapc_iterador.getIdAnotacionPredioCiudadano();

								if(
								    StringUtils.isValidString(ls_idAnotacionCiudadano)
									    && !lmapc_ciudadanos.containsKey(ls_idAnotacionCiudadano)
								)
									lapcd_DAO.deleteById(ls_idAnotacionCiudadano);
							}
						}
					}
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("validarActualizarOCrearIntervinientes", lb2be_e);
			throw lb2be_e;
		}
	}

	/**
	 * Metodo encargado de validar los datos a actualizar de datos anotación.
	 *
	 * @param lap_anotacionPredio Argumento de tipo <code>AnotacionPredio</code>
	 * que contiene los datos a validar.
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public synchronized void validarDatosAnotacion(AnotacionPredio lap_anotacionPredio)
	    throws B2BException
	{
		try
		{
			if(lap_anotacionPredio != null)
			{
				{
					long ll_numeroAnotacion;
					ll_numeroAnotacion = NumericUtils.getLong(lap_anotacionPredio.getIdAnotacion());

					if(ll_numeroAnotacion <= 0)
						throw new B2BException(ErrorKeys.DEBE_DIGITAR_ANOTACION);
				}

				{
					Date ld_fechaRadicacion;
					ld_fechaRadicacion = lap_anotacionPredio.getFechaRadicacion();

					if(ld_fechaRadicacion == null)
						throw new B2BException(ErrorKeys.DEBE_DIGITAR_FECHA_ANOTACION);
				}

				{
					String ls_radicacion;
					ls_radicacion = lap_anotacionPredio.getRadicacion();

					if(!StringUtils.isValidString(ls_radicacion))
						throw new B2BException(ErrorKeys.DEBE_DIGITAR_NUMERO_RADICACION);
				}

				{
					String ls_estadoAnotacion;
					ls_estadoAnotacion = lap_anotacionPredio.getIdEstadoAnotacion();

					if(!StringUtils.isValidString(ls_estadoAnotacion))
						throw new B2BException(ErrorKeys.DEBE_SELECCIONAR_ESTADO_ANOTACION);
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("validarDatosAnotacion", lb2be_e);
			throw lb2be_e;
		}
	}

	/**
	 * Método encargado de validar si los datos de antiguo sistema en pantalla son los mismos de la solicitud.
	 *
	 * @param adas_temp Objeto que contiene los datos de antiguo sistema que están en pantalla.
	 * @param as_idTurnoHistoria Variable de tipo String que contiene el id turno historia para un proceso determinado.
	 * @return Variable de tipo booleana que indica si los datos son iguales.
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public synchronized boolean validarDatosAntSistemaIguales(DatosAntSistema adas_temp, String as_idTurnoHistoria)
	    throws B2BException
	{
		boolean    lb_return;
		DAOManager ldm_manager;

		lb_return       = false;
		ldm_manager     = DaoManagerFactory.getDAOManager();

		try
		{
			if((adas_temp != null) && StringUtils.isValidString(as_idTurnoHistoria))
			{
				TurnoHistoria    lth_turnoHistoria;
				TurnoHistoriaDAO lth_DAO;

				lth_turnoHistoria     = new TurnoHistoria();
				lth_DAO               = DaoCreator.getTurnoHistoriaDAO(ldm_manager);

				lth_turnoHistoria.setIdTurnoHistoria(NumericUtils.getLongWrapper(as_idTurnoHistoria));

				lth_turnoHistoria = lth_DAO.findById(lth_turnoHistoria);

				if(lth_turnoHistoria != null)
				{
					String ls_idSolicitud;

					ls_idSolicitud = lth_turnoHistoria.getIdSolicitud();

					if(StringUtils.isValidString(ls_idSolicitud))
					{
						Solicitud    ls_solicitud;
						SolicitudDAO ls_DAO;

						ls_solicitud     = new Solicitud();
						ls_DAO           = DaoCreator.getSolicitudDAO(ldm_manager);

						ls_solicitud.setIdSolicitud(ls_idSolicitud);

						ls_solicitud = ls_DAO.findById(ls_solicitud);

						if(ls_solicitud != null)
						{
							String ls_idDatosAntSistema;

							ls_idDatosAntSistema = ls_solicitud.getIdDatosAntSistema();

							if(StringUtils.isValidString(ls_idDatosAntSistema))
							{
								DatosAntSistema    ldas_datosAntSistema;
								DatosAntSistemaDAO ldas_DAO;

								ldas_datosAntSistema     = new DatosAntSistema();
								ldas_DAO                 = DaoCreator.getDatosAntSistemaDAO(ldm_manager);

								ldas_datosAntSistema.setIdDatosAntSistema(ls_idDatosAntSistema);

								ldas_datosAntSistema = ldas_DAO.findById(ldas_datosAntSistema);

								if(ldas_datosAntSistema != null)
								{
									int                      li_count;
									HashMap<String, Boolean> lmh_validaciones;

									li_count             = 1;
									lmh_validaciones     = new HashMap<String, Boolean>();

									{
										boolean lb_add;
										boolean lb_validacion;
										boolean lb_validacionData;
										String  ls_validacion;
										String  ls_validacionData;

										lb_add                = false;
										ls_validacion         = adas_temp.getAdquisicionPredio();
										ls_validacionData     = ldas_datosAntSistema.getAdquisicionPredio();
										lb_validacion         = StringUtils.isValidString(ls_validacion);
										lb_validacionData     = StringUtils.isValidString(ls_validacionData);

										if(lb_validacion && lb_validacionData)
										{
											if(ls_validacion.equalsIgnoreCase(ls_validacionData))
												lb_add = true;
										}
										else if(!lb_validacion && !lb_validacionData)
											lb_add = true;

										lmh_validaciones.put(StringUtils.getString(li_count++), new Boolean(lb_add));
									}

									{
										boolean lb_add;
										boolean lb_validacion;
										boolean lb_validacionData;
										String  ls_validacion;
										String  ls_validacionData;

										lb_add                = false;
										ls_validacion         = adas_temp.getIdTipoPredio();
										ls_validacionData     = ldas_datosAntSistema.getIdTipoPredio();
										lb_validacion         = StringUtils.isValidString(ls_validacion);
										lb_validacionData     = StringUtils.isValidString(ls_validacionData);

										if(lb_validacion && lb_validacionData)
										{
											if(ls_validacion.equalsIgnoreCase(ls_validacionData))
												lb_add = true;
										}
										else if(!lb_validacion && !lb_validacionData)
											lb_add = true;

										lmh_validaciones.put(StringUtils.getString(li_count++), new Boolean(lb_add));
									}

									{
										boolean lb_add;
										boolean lb_validacion;
										boolean lb_validacionData;
										String  ls_validacion;
										String  ls_validacionData;

										lb_add                = false;
										ls_validacion         = adas_temp.getNombrePredio();
										ls_validacionData     = ldas_datosAntSistema.getNombrePredio();
										lb_validacion         = StringUtils.isValidString(ls_validacion);
										lb_validacionData     = StringUtils.isValidString(ls_validacionData);

										if(lb_validacion && lb_validacionData)
										{
											if(ls_validacion.equalsIgnoreCase(ls_validacionData))
												lb_add = true;
										}
										else if(!lb_validacion && !lb_validacionData)
											lb_add = true;

										lmh_validaciones.put(StringUtils.getString(li_count++), new Boolean(lb_add));
									}

									{
										boolean lb_add;
										boolean lb_validacion;
										boolean lb_validacionData;
										String  ls_validacion;
										String  ls_validacionData;

										lb_add                = false;
										ls_validacion         = adas_temp.getIdCirculo();
										ls_validacionData     = ldas_datosAntSistema.getIdCirculo();
										lb_validacion         = StringUtils.isValidString(ls_validacion);
										lb_validacionData     = StringUtils.isValidString(ls_validacionData);

										if(lb_validacion && lb_validacionData)
										{
											if(ls_validacion.equalsIgnoreCase(ls_validacionData))
												lb_add = true;
										}
										else if(!lb_validacion && !lb_validacionData)
											lb_add = true;

										lmh_validaciones.put(StringUtils.getString(li_count++), new Boolean(lb_add));
									}

									{
										boolean lb_add;
										boolean lb_validacion;
										boolean lb_validacionData;
										String  ls_validacion;
										String  ls_validacionData;

										lb_add                = false;
										ls_validacion         = adas_temp.getIdPais();
										ls_validacionData     = ldas_datosAntSistema.getIdPais();
										lb_validacion         = StringUtils.isValidString(ls_validacion);
										lb_validacionData     = StringUtils.isValidString(ls_validacionData);

										if(lb_validacion && lb_validacionData)
										{
											if(ls_validacion.equalsIgnoreCase(ls_validacionData))
												lb_add = true;
										}
										else if(!lb_validacion && !lb_validacionData)
											lb_add = true;

										lmh_validaciones.put(StringUtils.getString(li_count++), new Boolean(lb_add));
									}

									{
										boolean lb_add;
										boolean lb_validacion;
										boolean lb_validacionData;
										String  ls_validacion;
										String  ls_validacionData;

										lb_add                = false;
										ls_validacion         = adas_temp.getIdDepartamento();
										ls_validacionData     = ldas_datosAntSistema.getIdDepartamento();
										lb_validacion         = StringUtils.isValidString(ls_validacion);
										lb_validacionData     = StringUtils.isValidString(ls_validacionData);

										if(lb_validacion && lb_validacionData)
										{
											if(ls_validacion.equalsIgnoreCase(ls_validacionData))
												lb_add = true;
										}
										else if(!lb_validacion && !lb_validacionData)
											lb_add = true;

										lmh_validaciones.put(StringUtils.getString(li_count++), new Boolean(lb_add));
									}

									{
										boolean lb_add;
										boolean lb_validacion;
										boolean lb_validacionData;
										String  ls_validacion;
										String  ls_validacionData;

										lb_add                = false;
										ls_validacion         = adas_temp.getIdMunicipio();
										ls_validacionData     = ldas_datosAntSistema.getIdMunicipio();
										lb_validacion         = StringUtils.isValidString(ls_validacion);
										lb_validacionData     = StringUtils.isValidString(ls_validacionData);

										if(lb_validacion && lb_validacionData)
										{
											if(ls_validacion.equalsIgnoreCase(ls_validacionData))
												lb_add = true;
										}
										else if(!lb_validacion && !lb_validacionData)
											lb_add = true;

										lmh_validaciones.put(StringUtils.getString(li_count++), new Boolean(lb_add));
									}

									{
										boolean lb_add;
										boolean lb_validacion;
										boolean lb_validacionData;
										Long    ll_validacion;
										Long    ll_validacionData;

										lb_add                = false;
										ll_validacion         = adas_temp.getIdLibroAntSistema();
										ll_validacionData     = ldas_datosAntSistema.getIdLibroAntSistema();
										lb_validacion         = NumericUtils.isValidLong(ll_validacion);
										lb_validacionData     = NumericUtils.isValidLong(ll_validacionData);

										if(lb_validacion && lb_validacionData)
										{
											if(ll_validacion.compareTo(ll_validacionData) == 0)
												lb_add = true;
										}
										else if(!lb_validacion && !lb_validacionData)
											lb_add = true;

										lmh_validaciones.put(StringUtils.getString(li_count++), new Boolean(lb_add));
									}

									{
										boolean lb_add;
										boolean lb_validacion;
										boolean lb_validacionData;
										Long    ll_validacion;
										Long    ll_validacionData;

										lb_add                = false;
										ll_validacion         = adas_temp.getTomo();
										ll_validacionData     = ldas_datosAntSistema.getTomo();
										lb_validacion         = NumericUtils.isValidLong(ll_validacion);
										lb_validacionData     = NumericUtils.isValidLong(ll_validacionData);

										if(lb_validacion && lb_validacionData)
										{
											if(ll_validacion.compareTo(ll_validacionData) == 0)
												lb_add = true;
										}
										else if(!lb_validacion && !lb_validacionData)
											lb_add = true;

										lmh_validaciones.put(StringUtils.getString(li_count++), new Boolean(lb_add));
									}

									{
										boolean lb_add;
										boolean lb_validacion;
										boolean lb_validacionData;
										Long    ll_validacion;
										Long    ll_validacionData;

										lb_add                = false;
										ll_validacion         = adas_temp.getFolio();
										ll_validacionData     = ldas_datosAntSistema.getFolio();
										lb_validacion         = NumericUtils.isValidLong(ll_validacion);
										lb_validacionData     = NumericUtils.isValidLong(ll_validacionData);

										if(lb_validacion && lb_validacionData)
										{
											if(ll_validacion.compareTo(ll_validacionData) == 0)
												lb_add = true;
										}
										else if(!lb_validacion && !lb_validacionData)
											lb_add = true;

										lmh_validaciones.put(StringUtils.getString(li_count++), new Boolean(lb_add));
									}

									{
										boolean lb_add;
										boolean lb_validacion;
										boolean lb_validacionData;
										String  ls_validacion;
										String  ls_validacionData;

										lb_add                = false;
										ls_validacion         = adas_temp.getPartida();
										ls_validacionData     = ldas_datosAntSistema.getPartida();
										lb_validacion         = StringUtils.isValidString(ls_validacion);
										lb_validacionData     = StringUtils.isValidString(ls_validacionData);

										if(lb_validacion && lb_validacionData)
										{
											if(ls_validacion.equalsIgnoreCase(ls_validacionData))
												lb_add = true;
										}
										else if(!lb_validacion && !lb_validacionData)
											lb_add = true;

										lmh_validaciones.put(StringUtils.getString(li_count++), new Boolean(lb_add));
									}

									{
										boolean lb_add;
										boolean lb_validacion;
										boolean lb_validacionData;
										Long    ll_validacion;
										Long    ll_validacionData;

										lb_add                = false;
										ll_validacion         = adas_temp.getAnio();
										ll_validacionData     = ldas_datosAntSistema.getAnio();
										lb_validacion         = NumericUtils.isValidLong(ll_validacion);
										lb_validacionData     = NumericUtils.isValidLong(ll_validacionData);

										if(lb_validacion && lb_validacionData)
										{
											if(ll_validacion.compareTo(ll_validacionData) == 0)
												lb_add = true;
										}
										else if(!lb_validacion && !lb_validacionData)
											lb_add = true;

										lmh_validaciones.put(StringUtils.getString(li_count++), new Boolean(lb_add));
									}

									if(!lmh_validaciones.containsValue(Boolean.FALSE))
										lb_return = true;
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

			clh_LOGGER.error("validarDatosAntSistemaIguales", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lb_return;
	}

	/**
	 * Metodo encargado de validar los datos a actualizar de datos anotación.
	 *
	 * @param ldas_detalleAntSistema Argumento de tipo <code>AnotacionPredio</code>
	 * que contiene los datos a validar.
	 * @param aa_anotacion correspondiente al valor del tipo de objeto Anotacion
	 * @param aa_iterador correspondiente al valor del tipo de objeto Anotacion
	 * @return devuelve el valor de AnotacionPredio
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see AnotacionPredio
	 */
	public synchronized AnotacionPredio validarDetalleAntSistema(
	    DetalleAntSistema ldas_detalleAntSistema, Anotacion aa_anotacion, Anotacion aa_iterador
	)
	    throws B2BException
	{
		AnotacionPredio lap_anotacionPredio;

		lap_anotacionPredio = null;

		try
		{
			if(ldas_detalleAntSistema != null)
			{
				String ls_idDatosAntSistema;
				String ls_idDetalleAntSistema;

				ls_idDatosAntSistema       = ldas_detalleAntSistema.getIdDatosAntSistema();
				ls_idDetalleAntSistema     = ldas_detalleAntSistema.getIdDetalleAntSistema();

				if(StringUtils.isValidString(ls_idDetalleAntSistema) && StringUtils.isValidString(ls_idDatosAntSistema))
				{
					lap_anotacionPredio = new AnotacionPredio();

					lap_anotacionPredio.setIdDatosAntSistema(ls_idDatosAntSistema);
					lap_anotacionPredio.setIdDetalleAntSistema(ls_idDetalleAntSistema);
				}
				else if(!aa_anotacion.isBloqueo())
					throw new B2BException(ErrorKeys.ERROR_SELECCIONAR_DETALLE_ANT_SISTEMA);
			}
			else
			{
				if(
				    ((aa_iterador != null) && !aa_iterador.isBloqueo())
					    || ((aa_anotacion != null) && !aa_anotacion.isBloqueo())
				)
					throw new B2BException(ErrorKeys.ERROR_SELECCIONAR_DETALLE_ANT_SISTEMA);
			}
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("validarDetalleAntSistema", lb2be_e);
		}

		return lap_anotacionPredio;
	}

	/**
	 * Método encargado de validar si ya se han generado los docuemntos de crear matrícula para el proceso.
	 *
	 * @param acapr_capr Colección que contiene las matrículas a validar.
	 * @param as_idTurno Variable de tipo String que contiene el id turno para un proceso determinado.
	 * @return Mapa que contiene variable booleanas para valdiar si se generó el documento para las matrículas del proceso.
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see HashMap
	 */
	public synchronized Map<String, Boolean> validarDocuentosCrearMatricula(
	    Collection<AccPredioRegistro> acapr_capr, String as_idTurno
	)
	    throws B2BException
	{
		DAOManager           ldm_manager;
		Map<String, Boolean> lhm_return;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		lhm_return      = null;

		try
		{
			if(CollectionUtils.isValidCollection(acapr_capr) && StringUtils.isValidString(as_idTurno))
			{
				Turno    lt_turno;
				TurnoDAO lt_DAO;

				lt_turno     = new Turno();
				lt_DAO       = DaoCreator.getTurnoDAO(ldm_manager);

				lt_turno.setIdTurno(as_idTurno);

				lt_turno = lt_DAO.findById(lt_turno);

				if(lt_turno != null)
				{
					DocumentosSalida    lds_documentoSalida;
					DocumentosSalidaDAO lds_DAO;

					lds_documentoSalida     = new DocumentosSalida();
					lds_DAO                 = DaoCreator.getDocumentosSalidaDAO(ldm_manager);
					lhm_return              = new HashMap<String, Boolean>();

					lds_documentoSalida.setIdTurno(as_idTurno);
					lds_documentoSalida.setIdSolicitud(lt_turno.getIdSolicitud());
					lds_documentoSalida.setEstado(EstadoCommon.ACTIVO);
					lds_documentoSalida.setTipo(TipoArchivoCommon.CREACION_MATRICULA);

					for(AccPredioRegistro lapr_iterador : acapr_capr)
					{
						if(lapr_iterador != null)
						{
							String ls_matricula;

							ls_matricula = lapr_iterador.getIdCirculo() + "-" + lapr_iterador.getIdMatricula();

							if(StringUtils.isValidString(ls_matricula))
							{
								DocumentosSalida lds_temp;

								lds_documentoSalida.setReferenciaSalida(ls_matricula);

								lds_temp = lds_DAO.findDocumentByTurnoTipoEstadoReferenciaSalida(lds_documentoSalida);

								lhm_return.put(ls_matricula, new Boolean(lds_temp != null));
							}
						}
					}
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("validarDocuentosCrearMatricula", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lhm_return;
	}

	/**
	 * Verifica si el turno el proceso es una devolución de aprobacion de antiguo sistema.
	 *
	 * @param as_turnoHistoria id turno histroia del turno en proceso
	 * @return true si es una devolución
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public synchronized boolean validarSiEsDevolucion(String as_turnoHistoria)
	    throws B2BException
	{
		DAOManager ldm_manager;
		boolean    lb_esDevolucion;

		ldm_manager         = DaoManagerFactory.getDAOManager();
		lb_esDevolucion     = false;

		try
		{
			if(!StringUtils.isValidString(as_turnoHistoria))
				throw new B2BException(ErrorKeys.NO_SE_ENCONTRO_ID_TURNO_HISTORIA);

			TurnoHistoria    lth_turnoHistoria;
			TurnoHistoriaDAO lthd_turnoHistoriaDAO;

			lth_turnoHistoria         = new TurnoHistoria();
			lthd_turnoHistoriaDAO     = DaoCreator.getTurnoHistoriaDAO(ldm_manager);

			lth_turnoHistoria.setIdTurnoHistoria(NumericUtils.getLongWrapper(as_turnoHistoria));

			lth_turnoHistoria = lthd_turnoHistoriaDAO.findById(lth_turnoHistoria);

			if(lth_turnoHistoria != null)
			{
				BigDecimal lbd_idTHAnterior;

				lbd_idTHAnterior = lth_turnoHistoria.getIdAnterior();

				if(NumericUtils.isValidBigDecimal(lbd_idTHAnterior))
				{
					TurnoHistoria lth_turnoHistAnterior;

					lth_turnoHistAnterior = new TurnoHistoria();

					lth_turnoHistAnterior.setIdTurnoHistoria(NumericUtils.getLongWrapper(lbd_idTHAnterior));

					lth_turnoHistAnterior = lthd_turnoHistoriaDAO.findById(lth_turnoHistAnterior);

					if(lth_turnoHistAnterior != null)
					{
						BigDecimal lbd_idEtapa;

						lbd_idEtapa = lth_turnoHistAnterior.getIdEtapa();

						if(
						    NumericUtils.isValidBigDecimal(lbd_idEtapa)
							    && (lbd_idEtapa.longValue() == EtapaCommon.ID_ETAPA_MAYOR_VALOR)
						)
							lb_esDevolucion = true;
					}
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("validarSiEsDevolucion", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lb_esDevolucion;
	}

	/**
	 * Metodo encargado de validar los datos a actualizar de datos anotación.
	 *
	 * @param aap_anotacionPredio Argumento de tipo <code>AnotacionPredio</code> que contiene los datos a validar.
	 * @param al_idAnotacion Argumento de tipo <code>long</code> que contiene el id anotación.
	 * @param lacd_DAO Argumento de tipo <code>AnotacionCancelacionDAO</code> que
	 * @param lnjd_DAO correspondiente al valor del tipo de objeto NaturalezaJuridicaDAO
	 * @param lapd_DAO correspondiente al valor del tipo de objeto AnotacionPredioDAO
	 * @param aa_anotacion correspondiente al valor del tipo de objeto Anotacion
	 * @param ath_turnoHistoria correspondiente al valor del tipo de objeto TurnoHistoria
	 * @param as_userId correspondiente al valor del tipo de objeto String
	 * @param as_remoteIp correspondiente al valor del tipo de objeto String
	 * @return devuelve el valor de AnotacionPredio
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see AnotacionPredio
	 */
	public synchronized AnotacionPredio validarYCrearAnotacionCancelacion(
	    AnotacionPredio aap_anotacionPredio, long al_idAnotacion, AnotacionCancelacionDAO lacd_DAO,
	    NaturalezaJuridicaDAO lnjd_DAO, AnotacionPredioDAO lapd_DAO, Anotacion aa_anotacion,
	    TurnoHistoria ath_turnoHistoria, String as_userId, String as_remoteIp
	)
	    throws B2BException
	{
		AnotacionPredio lap_return;

		lap_return = null;

		try
		{
			if(aap_anotacionPredio != null)
			{
				String ls_tmp;
				String ls_idCirculo;
				Long   lL_idMatricula;

				ls_tmp             = aap_anotacionPredio.getIdNaturalezaJuridica();
				ls_idCirculo       = aap_anotacionPredio.getIdCirculo();
				lL_idMatricula     = aap_anotacionPredio.getIdMatricula();

				if(!StringUtils.isValidString(ls_tmp))
					throw new B2BException(ErrorKeys.ERROR_TIPO_ACTO_INVALIDO);

				String[] lsa_datos;

				lsa_datos = ls_tmp.split("-");

				if((lsa_datos != null) && (lsa_datos.length > 1))
				{
					String ls_idNaturalezaJuridica;
					String ls_version;

					ls_idNaturalezaJuridica     = lsa_datos[0];
					ls_version                  = lsa_datos[1];

					if(StringUtils.isValidString(ls_idNaturalezaJuridica) && StringUtils.isValidString(ls_version))
					{
						lap_return = new AnotacionPredio();

						lap_return.setIdNaturalezaJuridica(ls_idNaturalezaJuridica);
						lap_return.setVersion(NumericUtils.getLong(ls_version));

						{
							AnotacionCancelacion ac_param;

							ac_param = new AnotacionCancelacion();

							ac_param.setIdCirculo(ls_idCirculo);
							ac_param.setIdMatricula(NumericUtils.getLong(lL_idMatricula));
							ac_param.setIdAnotacion(NumericUtils.getLongWrapper(al_idAnotacion));
							ac_param.setIdTurno(ath_turnoHistoria.getIdTurno());

							ac_param = lacd_DAO.findByCirculoMatriculaAnotacinoTurno(ac_param);

							if(ac_param != null)
								lacd_DAO.deleteByCirculoMatriculaAnotacionTruno(ac_param, true);
						}

						NaturalezaJuridica lnj_parametros;
						lnj_parametros = new NaturalezaJuridica();

						lnj_parametros.setIdNaturalezaJuridica(ls_idNaturalezaJuridica);
						lnj_parametros.setVersion(NumericUtils.getLong(ls_version));

						lnj_parametros = lnjd_DAO.findById(lnj_parametros);

						if(lnj_parametros != null)
						{
							String                           ls_grupo;
							Collection<RegistroCalificacion> lcrc_anotacionesACancelar;

							ls_grupo                      = lnj_parametros.getIdGrupoNatJur();
							lcrc_anotacionesACancelar     = aap_anotacionPredio.getAnotacionesACancelar();

							if(
							    StringUtils.isValidString(ls_grupo)
								    && (ls_grupo.contains("700") || ls_grupo.contains("800"))
							)
							{
								int li_contador;

								li_contador = 0;

								if(CollectionUtils.isValidCollection(lcrc_anotacionesACancelar))
								{
									for(RegistroCalificacion lrc_iterador : lcrc_anotacionesACancelar)
									{
										if((lrc_iterador != null) && lrc_iterador.isRevision())
										{
											Long            ll_idAnotacion1;
											AnotacionPredio lap_tmp;

											ll_idAnotacion1     = lrc_iterador.getIdAnotacion();
											lap_tmp             = new AnotacionPredio();

											lap_tmp.setIdCirculo(ls_idCirculo);
											lap_tmp.setIdMatricula(lL_idMatricula);
											lap_tmp.setIdAnotacion(ll_idAnotacion1);

											lap_tmp = lapd_DAO.findByCirculoMatriculaAnotacion(lap_tmp);

											if(lap_tmp == null)
											{
												Object[] loa_messageArgs = new String[3];
												loa_messageArgs[0]     = StringUtils.getString(ll_idAnotacion1);
												loa_messageArgs[1]     = ls_idCirculo;
												loa_messageArgs[2]     = StringUtils.getString(lL_idMatricula);
												throw new B2BException(
												    ErrorKeys.ERROR_SIN_ANOTACION_MATRICULA, loa_messageArgs
												);
											}

											{
												NaturalezaJuridica lnj_cancela;
												lnj_cancela = new NaturalezaJuridica();

												lnj_cancela.setIdNaturalezaJuridica(lap_tmp.getIdNaturalezaJuridica());
												lnj_cancela.setVersion(NumericUtils.getLong(lap_tmp.getVersion()));

												lnj_cancela = lnjd_DAO.findById(lnj_cancela);

												if(lnj_cancela != null)
												{
													{
														String ls_grupoCancela;
														ls_grupoCancela = lnj_cancela.getIdGrupoNatJur();

														if(
														    !StringUtils.isValidString(ls_grupoCancela)
															    || (!ls_grupoCancela.contains("200")
															    && !ls_grupoCancela.contains("400"))
														)
															throw new B2BException(
															    ErrorKeys.ERROR_ANOTACION_SIN_GRAVAMEN
															);
													}

													{
														AnotacionCancelacion lac_anotacionCancelacion;

														lac_anotacionCancelacion = new AnotacionCancelacion();

														lac_anotacionCancelacion.setIdTurnoHistoria(
														    NumericUtils.getLong(
														        ath_turnoHistoria.getIdTurnoHistoria()
														    )
														);
														lac_anotacionCancelacion.setIdCirculo(ls_idCirculo);
														lac_anotacionCancelacion.setIdMatricula(
														    NumericUtils.getLong(lL_idMatricula)
														);
														lac_anotacionCancelacion.setIdAnotacion(
														    aap_anotacionPredio.getIdAnotacion()
														);
														lac_anotacionCancelacion.setIdAnotacion1(ll_idAnotacion1);

														lac_anotacionCancelacion.setIdUsuarioCreacion(as_userId);
														lac_anotacionCancelacion.setIpCreacion(as_remoteIp);

														lacd_DAO.insert(lac_anotacionCancelacion);
													}

													lap_tmp.setAnotacionCancelada(EstadoCommon.S);
													lap_tmp.setIdUsuarioModificacion(as_userId);
													lap_tmp.setIpModificacion(as_remoteIp);

													lapd_DAO.updateAnotacion(lap_tmp);
												}
											}

											li_contador++;
										}
									}
								}

								if(li_contador == 0)
									throw new B2BException(ErrorKeys.ERROR_SIN_SELECCION_ACTO_A_CANCELAR);
							}
						}
					}
				}

				{
					String ls_especificacion;

					ls_especificacion = aap_anotacionPredio.getEspecificacion();

					if(!StringUtils.isValidString(ls_especificacion))
						throw new B2BException(ErrorKeys.DEBE_DIGITAR_DESCRIPCION);
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("validarDatosAnotacion", lb2be_e);
			throw lb2be_e;
		}

		return lap_return;
	}

	/**
	 * Obtiene el turno certificado para una matrícula.
	 *
	 * @param asm_matricula Objeto contenedor de la matrícula
	 * @param as_idDatosAntSistema identificador unico del predio
	 * @param adm_manager Conexión a la base de datos
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	private void cargarTurnoCertificado(
	    SolicitudMatricula asm_matricula, String as_idDatosAntSistema, DAOManager adm_manager
	)
	    throws B2BException
	{
		if((asm_matricula != null) && StringUtils.isValidString(as_idDatosAntSistema))
		{
			Turno  lt_turnoCertificado;
			String ls_expedirCertificado;

			lt_turnoCertificado = DaoCreator.getTurnoDAO(adm_manager).findByIdDatosAntSistema(as_idDatosAntSistema);

			if(lt_turnoCertificado != null)
			{
				asm_matricula.setIdTurnoCertificado(lt_turnoCertificado.getIdTurno());

				ls_expedirCertificado = EstadoCommon.S;
			}
			else
				ls_expedirCertificado = EstadoCommon.N;

			asm_matricula.setExpedirCertificado(ls_expedirCertificado);
		}
	}

	/**
	 * Metodo encargado de crear en la tabla SDB_ACC_detalle_ANT_SISTEMA.
	 *
	 * @param adas_detalle correspondiente al valor del tipo de objeto DetalleAntSistema
	 * @param adaas_datos Objeto que contiene el id_datos_ant_sistema para realizar la inserción.
	 * @param adm_manager Objeto usado para crear la transacción de base de datos y la conexión a la misma.
	 * @param as_userId Usuario de sesión que está realizando la creación.
	 * @param as_remoteIp Ip desde la cual se está realizando la creación.
	 * @return Objeto que contiene el detalle con el que se crea el registro.
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see DetalleAntSistema
	 */
	private DetalleAntSistema crearDetalleAntSistema(
	    DetalleAntSistema adas_detalle, DatosAntSistema adaas_datos, DAOManager adm_manager, String as_userId,
	    String as_remoteIp
	)
	    throws B2BException
	{
		if(
		    (adas_detalle != null) && (adaas_datos != null) && (adm_manager != null)
			    && StringUtils.isValidString(as_userId) && StringUtils.isValidString(as_remoteIp)
		)
		{
			try
			{
				Solicitud       ls_solicitud;
				DatosAntSistema ldas_datosAnt;
				ldas_datosAnt = DaoCreator.getDatosAntSistemaDAO(adm_manager)
						                      .findById(adaas_datos.getIdDatosAntSistema());

				if(ldas_datosAnt == null)
					throw new B2BException(ErrorKeys.ERROR_SIN_DATOS_ANT_SISTEMA);

				ls_solicitud = DaoCreator.getSolicitudDAO(adm_manager).findById(adaas_datos.getIdSolicitud());

				Long ll_libro;

				{
					ll_libro = adas_detalle.getIdLibroAntSistema();

					if(!NumericUtils.isValidLong(ll_libro))
						throw new B2BException(ErrorKeys.DEBE_SELECCIONAR_LIBRO);
				}

				{
					String ls_tomo;

					ls_tomo = adas_detalle.getTomo();

					if(!StringUtils.isValidString(ls_tomo))
						throw new B2BException(ErrorKeys.DEBE_SELECCIONAR_TOMO);
				}

				{
					Long ll_numeroPartida;

					ll_numeroPartida = adas_detalle.getNumeroPartida();

					if(!NumericUtils.isValidLong(ll_numeroPartida))
						throw new B2BException(ErrorKeys.DEBE_DIGITAR_NUMERO_DE_PARTIDA);
				}

				{
					DetalleAntSistemaDAO          ldasd_DAO;
					Collection<DetalleAntSistema> lcdas_detallesDatos;
					int                           li_secuencia;

					ldasd_DAO = DaoCreator.getDetalleAntSistemaDAO(adm_manager);
					adas_detalle.setIdDatosAntSistema(adaas_datos.getIdDatosAntSistema());

					lcdas_detallesDatos     = ldasd_DAO.findByDatosAntSis(adas_detalle);
					li_secuencia            = 1;

					if(CollectionUtils.isValidCollection(lcdas_detallesDatos))
					{
						Iterator<DetalleAntSistema> lidas_idas;
						int                         li_idDetalleSalto;
						boolean                     lb_break;

						lidas_idas            = lcdas_detallesDatos.iterator();
						li_idDetalleSalto     = 0;
						lb_break              = false;

						while(lidas_idas.hasNext() && !lb_break)
						{
							DetalleAntSistema ldas_actual;

							ldas_actual = lidas_idas.next();

							if(ldas_actual != null)
							{
								int li_idDetalleAntSistema;

								li_idDetalleAntSistema = NumericUtils.getInt(ldas_actual.getIdDetalleAntSistema());

								if((li_idDetalleSalto + 1) == li_idDetalleAntSistema)
									li_idDetalleSalto = li_idDetalleAntSistema;
								else
									lb_break = true;
							}
						}

						li_secuencia = ++li_idDetalleSalto;
					}

					adas_detalle.setIdDetalleAntSistema(StringUtils.getString(li_secuencia));
					adas_detalle.setIdUsuarioCreacion(as_userId);
					adas_detalle.setIpCreacion(as_remoteIp);

					ldasd_DAO.insertOrUpdate(adas_detalle, true);

					{
						LibroAntiguoSistema las_libro;

						las_libro = new LibroAntiguoSistema();

						las_libro.setIdLibroAntiguoSistema(NumericUtils.getLong(adas_detalle.getIdLibroAntSistema()));

						las_libro = DaoCreator.getLibroAntiguoSistemaDAO(adm_manager).findById(las_libro);

						if(las_libro != null)
							adas_detalle.setNombreLibro(las_libro.getNombre());
					}

					if(NumericUtils.getLong(ll_libro) == NumericUtils.getLong(LibroAntSistemaCommon.LIBRO_SEGUNDO))
					{
						Documento ld_documentoTmp;

						{
							Documento ld_docValidaciones;

							ld_docValidaciones = adas_detalle.getDocumentoConsulta();

							if(ld_docValidaciones == null)
								throw new B2BException(ErrorKeys.ERROR_SIN_ID_DOCUMENTO);

							if(!StringUtils.isValidString(ld_docValidaciones.getIdTipoDocumento()))
								throw new B2BException(ErrorKeys.DEBE_SELECCIONAR_TIPO_DOC);

							if(!StringUtils.isValidString(ld_docValidaciones.getNumero()))
								throw new B2BException(ErrorKeys.DEBE_DIGITAR_NUMERO_DE_DOC);

							if(ld_docValidaciones.getFechaDocumento() == null)
								throw new B2BException(ErrorKeys.DEBE_DIGITAR_NUMERO_DE_DOC);

							if(!StringUtils.isValidString(ld_docValidaciones.getIdTipoOficina()))
								throw new B2BException(ErrorKeys.DEBE_SELECCIONAR_TIPO_OFICINA);

							if(!StringUtils.isValidString(ld_docValidaciones.getIdPais()))
								throw new B2BException(ErrorKeys.DEBE_SELECCIONAR_PAIS);

							if(!StringUtils.isValidString(ld_docValidaciones.getIdDepartamento()))
								throw new B2BException(ErrorKeys.DEBE_SELECCIONAR_DEPARTAMENTO);

							if(!StringUtils.isValidString(ld_docValidaciones.getIdMunicipio()))
								throw new B2BException(ErrorKeys.DEBE_SELECCIONAR_MUNICIPIO);

							if(!StringUtils.isValidString(ld_docValidaciones.getOficinaOrigen().getIdOficinaOrigen()))
								throw new B2BException(ErrorKeys.DEBE_SELECCIONAR_OFICINA_ORIGEN);

							ld_documentoTmp = salvarDocumento(
								    ld_docValidaciones, ls_solicitud, as_userId, as_remoteIp, adm_manager
								);
						}

						if((ld_documentoTmp != null))
						{
							String ls_idDocumento;
							Long   ll_versionDoc;

							ls_idDocumento     = ld_documentoTmp.getIdDocumento();
							ll_versionDoc      = ld_documentoTmp.getVersionDocumento();

							if(!StringUtils.isValidString(ls_idDocumento) || !NumericUtils.isValidLong(ll_versionDoc))
								throw new B2BException(ErrorKeys.ERROR_SIN_ID_DOCUMENTO);

							adas_detalle.setDocumento(ld_documentoTmp);
							adas_detalle.setIdDocumentoTradicion(ls_idDocumento);
							adas_detalle.setVersionDocumentoTradicion(StringUtils.getString(ll_versionDoc));
						}
						else
							throw new B2BException(ErrorKeys.ERROR_SIN_ID_DOCUMENTO);
					}
				}
			}
			catch(B2BException lb2be_e)
			{
				clh_LOGGER.error("crearDetalleAntSistema", lb2be_e);

				throw lb2be_e;
			}
		}

		return adas_detalle;
	}

	/**
	 * Método encargado de listar las etapas que se encuentran entre 100 y 104.
	 *
	 * @param as_userId Variable de tipo String que contiene el usuario que realiza la acción.
	 * @param al_etapa variable ded tipo long con la etapade AS a consultar
	 * @return  Retorna  una colección de datos   de tipo  Etapa que coincide con los parametros de entrada.
	 * @throws B2BException Señala que se ha producido una excepción
	 *
	 */
	private Collection<Etapa> findAntiguoSistema(String as_userId, long al_etapa)
	    throws B2BException
	{
		DAOManager        ldm_manager;
		Collection<Etapa> lc_data;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		lc_data         = null;

		try
		{
			lc_data = DaoCreator.getEtapaDAO(ldm_manager).findAntiguoSistema(al_etapa);
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findAntiguoSistema", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lc_data;
	}

	/**
	 * Método encargado de consultar los procesos que se realizaron en el trámite de antiguo sistema.
	 *
	 * @param as_idTurno Variable de tipo String que contiene el id del turno en trámite
	 * @param as_idDatosAntSistemaActual Variable de tipo String que contiene el id de los datos de antiguo sistema
	 * @param adm_manager DaoManager encargado de realizar la conexión con la base de datos
	 * @return Mapa que contiene el nombre de los procesos que se tramitaron en antiguo sistema
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see Map
	 */
	private synchronized Map<String, Boolean> findProcesoAntiguoSistema(
	    String as_idTurno, String as_idDatosAntSistemaActual, String as_idTurnoHistoria, DAOManager adm_manager
	)
	    throws B2BException
	{
		Map<String, Boolean> lmsb_return;

		lmsb_return = null;

		if(StringUtils.isValidString(as_idTurno) && StringUtils.isValidString(as_idDatosAntSistemaActual))
		{
			lmsb_return = new HashMap<String, Boolean>();

			{
				AccPredioRegistro             lapr_predio;
				Collection<AccPredioRegistro> lcapr_predios;

				lapr_predio = new AccPredioRegistro();

				lapr_predio.setIdTurno(as_idTurno);
				lapr_predio.setIdDatosAntSistema(as_idDatosAntSistemaActual);

				lcapr_predios = DaoCreator.getAccPredioRegistroDAO(adm_manager).findByTurnoAntSistema(
					    lapr_predio, true
					);

				if(CollectionUtils.isValidCollection(lcapr_predios))
					lmsb_return.put(IdentificadoresCommon.CREAR_MATRICULA, Boolean.TRUE);
			}

			{
				Collection<SolicitudMatricula> lcsm_matriculas;

				lcsm_matriculas = DaoCreator.getSolicitudMatriculaDAO(adm_manager)
						                        .findMatriculasByturnoAntSistema(
						    as_idTurno, as_idDatosAntSistemaActual, true
						);

				if(CollectionUtils.isValidCollection(lcsm_matriculas))
					lmsb_return.put(IdentificadoresCommon.ASOCIAR_MATRICULA, Boolean.TRUE);
			}

			{
				Collection<DocumentosSalida> lcds_documentos;
				DocumentosSalida             lds_documentoSalida;

				lds_documentoSalida = new DocumentosSalida();

				lds_documentoSalida.setIdDatosAntSistema(as_idDatosAntSistemaActual);
				lds_documentoSalida.setIdTurnoHistoria(NumericUtils.getInteger(as_idTurnoHistoria));

				lcds_documentos = DaoCreator.getDocumentosSalidaDAO(adm_manager)
						                        .findByIdTurnoHistoriaAntSistema(lds_documentoSalida);

				if(CollectionUtils.isValidCollection(lcds_documentos))
					lmsb_return.put(IdentificadoresCommon.INFORME_BUSQUEDA, Boolean.TRUE);
			}
		}

		return lmsb_return;
	}

	/**
	 * Método encargado de generar archivo en formato pdf  utilizando  las plantillas  PLANTILLA_NEGACION_AUTORIZACION_FIRMA_AS.
	 *
	 * @param ath_parametros  Objeto de tipo TurnoHistoria que contiene los datos necesarios para buscar la información a incluir en la plantilla.
	 * @param ab_cargar  Objeto de tipo boolean que contiene los datos necesarios para buscar la información a incluir en la plantilla.
	 * @param as_idUsuario Variable de tipo String que contiene el usuario que realiza la acción.
	 * @param as_localIp Variable de tipo String que contiene la dirección ip local  desde donde seque realiza la acción.
	 * @param as_remoteIp Variable de tipo String que contiene la dirección ip remota desde donde seque realiza la acción.
	 * @param adm_manager Variable de tipo DAOManager contenedora de la conexion a la base de datos.
	 * @return  Arreglo de bytes que contiene la plantilla generada.
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see byte[]
	 */
	private byte[] generarNegacionAutorizacionFirma(
	    TurnoHistoria ath_parametros, boolean ab_cargar, boolean ab_firma, String as_idUsuario, String as_localIp,
	    String as_remoteIp, DAOManager adm_manager
	)
	    throws B2BException
	{
		byte[] lba_return;

		lba_return = null;

		try
		{
			String              ls_plantilla;
			Map<String, String> lmss_datos;

			ls_plantilla     = null;
			lmss_datos       = null;

			{
				Constantes lc_constante;

				lc_constante = DaoCreator.getConstantesDAO(adm_manager)
						                     .findByIdWithImage(
						    ConstanteCommon.PLANTILLA_NEGACION_AUTORIZACION_FIRMA_AS
						);

				if(lc_constante != null)
				{
					byte[] lba_plantilla;

					lba_plantilla = lc_constante.getImagenes();

					if(lba_plantilla == null)
						throw new B2BException(ErrorKeys.NO_IMAGEN_CONSTANTE);

					ls_plantilla = new String(lba_plantilla);
				}
				else
				{
					Object[] loa_args;

					loa_args     = new String[1];

					loa_args[0] = ConstanteCommon.PLANTILLA_NEGACION_AUTORIZACION_FIRMA_AS;

					throw new B2BException(ErrorKeys.ERROR_SIN_CONSTANTE, loa_args);
				}
			}

			if(StringUtils.isValidString(ls_plantilla) && (ath_parametros != null))
			{
				String ls_tag;

				ls_tag = null;

				if(!ab_cargar || ab_firma)
				{
					ls_tag = "<TAG_OFICIO>";

					if(ls_plantilla.contains(ls_tag))
					{
						NumeracionOficiosCirculo lnoc_numeracionOficios;

						lnoc_numeracionOficios = findNumeracionOficiosCirculo(
							    ath_parametros, adm_manager, as_idUsuario, as_remoteIp
							);

						if(lnoc_numeracionOficios != null)
						{
							String ls_consecutivoOficio;

							ls_consecutivoOficio = lnoc_numeracionOficios.getConsecutivoGenerado();

							ath_parametros.setConsecutivoOficioAntiguoSistema(ls_consecutivoOficio);

							ls_plantilla = ls_plantilla.replace(ls_tag, ls_consecutivoOficio);
						}
					}

					ls_tag = "<TAG_REFERENCIA_SALIDA>";

					if(ls_plantilla.contains(ls_tag))
					{
						String ls_referenciaSalida;

						ls_referenciaSalida     = generarIdCorrespondencia(ath_parametros, adm_manager, false);

						ls_plantilla = reemplazarTagEnPlantilla(ls_plantilla, ls_tag, ls_referenciaSalida);
					}
				}

				{
					ls_tag = "<TAG_MUN_OFI_ORIGEN>";

					if(ls_plantilla.contains(ls_tag))
					{
						Turno lt_turno;

						lt_turno = DaoCreator.getTurnoDAO(adm_manager).findById(ath_parametros.getIdTurno());

						if(lt_turno != null)
						{
							Municipio lm_municipio;

							lm_municipio = DaoCreator.getMunicipioDAO(adm_manager)
									                     .findByIdCirculo(lt_turno.getIdCirculo());

							if(lm_municipio != null)
							{
								String ls_munOficinaOrigen;

								ls_munOficinaOrigen = lm_municipio.getNombre();

								if(StringUtils.isValidString(ls_munOficinaOrigen))
									ls_plantilla = ls_plantilla.replace(ls_tag, ls_munOficinaOrigen);
							}

							{
								ls_tag = "<TAG_TURNO>";

								if(ls_plantilla.contains(ls_tag))
									ls_plantilla = ls_plantilla.replace(ls_tag, lt_turno.getIdTurno());
							}

							{
								ls_tag = "<TAG_NIR>";

								if(ls_plantilla.contains(ls_tag))
								{
									Solicitud ls_solicitud;

									ls_solicitud = DaoCreator.getSolicitudDAO(adm_manager)
											                     .findById(lt_turno.getIdSolicitud());

									if(ls_solicitud != null)
									{
										String ls_nir;

										ls_nir = ls_solicitud.getNir();

										if(StringUtils.isValidString(ls_nir))
											ls_plantilla = ls_plantilla.replace(ls_tag, ls_nir);
									}
								}
							}

							{
								CirculoRegistral lcr_circuloRegistral;

								lcr_circuloRegistral = DaoCreator.getCirculoRegistralDAO(adm_manager)
										                             .findById(lt_turno.getIdCirculo());

								if(lcr_circuloRegistral != null)
								{
									String ls_tipoOficina;

									ls_tipoOficina = lcr_circuloRegistral.getTipoOficina();

									if(StringUtils.isValidString(ls_tipoOficina))
									{
										if(ls_tipoOficina.equalsIgnoreCase(EstadoCommon.P))
											ls_tipoOficina = IdentificadoresCommon.PRINCIPAL;
										else if(ls_tipoOficina.equalsIgnoreCase(EstadoCommon.S))
											ls_tipoOficina = IdentificadoresCommon.SECCIONAL;

										ls_tag = "<TAG_PRINCIPAL_SECCIONAL>";

										if(ls_plantilla.contains(ls_tag))
											ls_plantilla = ls_plantilla.replace(ls_tag, ls_tipoOficina);
									}

									ls_tag = "<TAG_NOMBRE_CIRCULO_REGISTRAL>";

									if(ls_plantilla.contains(ls_tag))
									{
										String ls_destinatario;

										ls_destinatario = lcr_circuloRegistral.getNombre();

										if(StringUtils.isValidString(ls_destinatario))
											ls_plantilla = ls_plantilla.replace(ls_tag, ls_destinatario);
									}
								}
							}
						}
					}
				}

				{
					ls_tag = "<TAG_FECHA_LARGA>";

					if(ls_plantilla.contains(ls_tag))
					{
						Date   ld_fecha;
						String ls_fechaActual;

						ld_fecha           = new Date();
						ls_fechaActual     = DateUtils.convertirLetrasLarga(ld_fecha);

						if(StringUtils.isValidString(ls_fechaActual))
							ls_plantilla = ls_plantilla.replace(ls_tag, ls_fechaActual.toUpperCase());
					}
				}

				{
					ls_tag = "<TAG_CONSIDERACIONES>";

					if(ls_plantilla.contains(ls_tag))
					{
						String ls_consideraciones;

						ls_consideraciones = null;

						if(ab_firma)
						{
							TurnoHistoria lth_temp;

							lth_temp = DaoCreator.getTurnoHistoriaDAO(adm_manager)
									                 .findByIdTurnoEtapa(
									    ath_parametros.getIdTurno(), EtapaCommon.ID_ETAPA_AUTORIZACION_FIRMA_LIBROS_A_S
									);

							if(lth_temp != null)
							{
								OficiosTexto lot_data;

								lot_data = DaoCreator.getOficiosTextoDAO(adm_manager)
										                 .findByTurnoHistoria(lth_temp.getIdTurnoHistoria());

								if(lot_data != null)
									ls_consideraciones = lot_data.getConsideracion();
							}
						}
						else
							ls_consideraciones = ath_parametros.getConsideracionesAntiguoSistema();

						if(StringUtils.isValidString(ls_consideraciones))
							ls_plantilla = ls_plantilla.replace(ls_tag, ls_consideraciones);
					}
				}

				{
					ls_tag = "<TAG_USUARIO>";

					if(ls_plantilla.contains(ls_tag) && StringUtils.isValidString(as_idUsuario))
						ls_plantilla = ls_plantilla.replace(ls_tag, as_idUsuario);
				}

				lmss_datos       = finalizarPlantilla(ls_plantilla, null, adm_manager);
				ls_plantilla     = lmss_datos.get(IdentificadoresCommon.PLANTILLA);

				lba_return = new PDFGenerator().convertirRTFToPDF(ls_plantilla.getBytes(), adm_manager);
			}
			else
				throw new B2BException(ErrorKeys.ERROR_PLANTILLA);

			if(lba_return == null)
				throw new B2BException(ErrorKeys.ERROR_GENERANDO_PLANTILLA);
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("generarNegacionAutorizacionFirma", lb2be_e);

			throw lb2be_e;
		}

		return lba_return;
	}

	/**
	 * Método encargado de generar archivo en formato pdf  utilizando  las plantillas  PLANTILLA_RESOLUCION_AUTORIZACION_FIRMA_AS.
	 *
	 * @param ath_parametros  Objeto de tipo TurnoHistoria que contiene los datos necesarios para buscar la información a incluir en la plantilla.
	 * @param as_idUsuario Variable de tipo String que contiene el usuario que realiza la acción.
	 * @param as_localIp Variable de tipo String que contiene la dirección ip local  desde donde seque realiza la acción.
	 * @param as_remoteIp Variable de tipo String que contiene la dirección ip remota desde donde seque realiza la acción.
	 * @param adm_manager Variable de tipo DAOManager contenedora de la conexion a la base de datos.
	 * @return  Arreglo de bytes que contiene la plantilla generada.
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see byte[]
	 */
	private byte[] generarResolucionAutorizacionFirma(
	    TurnoHistoria ath_parametros, String as_idUsuario, String as_localIp, String as_remoteIp, DAOManager adm_manager
	)
	    throws B2BException
	{
		return generarResolucionAutorizacionFirma(
		    ath_parametros, as_idUsuario, as_localIp, as_remoteIp, adm_manager, false
		);
	}

	/**
	 * Método encargado de generar archivo en formato pdf  utilizando  las plantillas  PLANTILLA_RESOLUCION_AUTORIZACION_FIRMA_AS.
	 *
	 * @param ath_parametros  Objeto de tipo TurnoHistoria que contiene los datos necesarios para buscar la información a incluir en la plantilla.
	 * @param as_idUsuario Variable de tipo String que contiene el usuario que realiza la acción.
	 * @param as_localIp Variable de tipo String que contiene la dirección ip local  desde donde seque realiza la acción.
	 * @param as_remoteIp Variable de tipo String que contiene la dirección ip remota desde donde seque realiza la acción.
	 * @param adm_manager Variable de tipo DAOManager contenedora de la conexion a la base de datos.
	 * @return  Arreglo de bytes que contiene la plantilla generada.
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see byte[]
	 */
	private byte[] generarResolucionAutorizacionFirma(
	    TurnoHistoria ath_parametros, String as_idUsuario, String as_localIp, String as_remoteIp, DAOManager adm_manager,
	    boolean ab_firmaMasiva
	)
	    throws B2BException
	{
		byte[] lba_return;

		lba_return = null;

		try
		{
			String              ls_plantilla;
			Map<String, String> lmss_datos;

			ls_plantilla     = null;
			lmss_datos       = null;

			{
				Constantes lc_constante;

				lc_constante = DaoCreator.getConstantesDAO(adm_manager)
						                     .findByIdWithImage(
						    ConstanteCommon.PLANTILLA_RESOLUCION_AUTORIZACION_FIRMA_AS
						);

				if(lc_constante != null)
				{
					byte[] lba_plantilla;

					lba_plantilla = lc_constante.getImagenes();

					if(lba_plantilla == null)
						throw new B2BException(ErrorKeys.NO_IMAGEN_CONSTANTE);

					ls_plantilla = new String(lba_plantilla);
				}
				else
				{
					Object[] loa_args;

					loa_args     = new String[1];

					loa_args[0] = ConstanteCommon.PLANTILLA_RESOLUCION_AUTORIZACION_FIRMA_AS;

					throw new B2BException(ErrorKeys.ERROR_SIN_CONSTANTE, loa_args);
				}
			}

			if(StringUtils.isValidString(ls_plantilla) && (ath_parametros != null))
			{
				String ls_tag;
				Date   ld_fechaOficio;

				ld_fechaOficio     = null;
				ls_tag             = null;

				{
					ls_tag = "<TAG_RESOLUCION>";

					NumeracionResolucionCirculo lnrc_numeracionCirculo;
					String                      ls_resolucion;

					ls_resolucion              = null;
					lnrc_numeracionCirculo     = findNumeracionResolucionCirculo(
						    ath_parametros, adm_manager, as_idUsuario, as_remoteIp
						);

					if(lnrc_numeracionCirculo != null)
					{
						ls_resolucion = lnrc_numeracionCirculo.getConsecutivogenerado();
						ath_parametros.setConsecutivoResolucionAntiguoSistema(ls_resolucion);
					}

					if(ls_plantilla.contains(ls_tag) && (lnrc_numeracionCirculo != null))
						ls_plantilla = ls_plantilla.replace(ls_tag, ls_resolucion);
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

				{
					ls_tag = "<TAG_OFICIO>";

					if(ls_plantilla.contains(ls_tag))
					{
						NumeracionOficiosCirculo lnoc_numeracionOficios;
						String                   ls_consecutivoOficio;

						lnoc_numeracionOficios     = findNumeracionOficiosCirculo(
							    ath_parametros, adm_manager, as_idUsuario, as_remoteIp
							);
						ls_consecutivoOficio       = null;

						if(lnoc_numeracionOficios != null)
						{
							ls_consecutivoOficio     = lnoc_numeracionOficios.getConsecutivoGenerado();

							ls_plantilla     = ls_plantilla.replace(ls_tag, ls_consecutivoOficio);

							ld_fechaOficio = new Date();
						}
					}
				}

				{
					ls_tag = "<TAG_FECHA_OFICIO>";

					if(ls_plantilla.contains(ls_tag) && (ld_fechaOficio != null))
					{
						String ls_fechaOficio;

						ls_fechaOficio     = DateUtils.convertirLetrasLarga(ld_fechaOficio);
						ls_plantilla       = ls_plantilla.replace(ls_tag, ls_fechaOficio.toUpperCase());
					}
				}

				{
					ls_tag = "<TAG_NOMBRE_ORIP>";

					if(ls_plantilla.contains(ls_tag))
					{
						Turno lt_turno;

						lt_turno = DaoCreator.getTurnoDAO(adm_manager).findById(ath_parametros.getIdTurno());

						if(lt_turno != null)
						{
							CirculoRegistral lcr_circuloRegistral;

							lcr_circuloRegistral = DaoCreator.getCirculoRegistralDAO(adm_manager)
									                             .findById(lt_turno.getIdCirculo());

							if(lcr_circuloRegistral != null)
							{
								String ls_nombreCirculo;

								ls_nombreCirculo = lcr_circuloRegistral.getNombre();

								if(StringUtils.isValidString(ls_nombreCirculo))
									ls_plantilla = ls_plantilla.replace(ls_tag, ls_nombreCirculo);
							}

							{
								ls_tag = "<TAG_TURNO>";

								if(ls_plantilla.contains(ls_tag))
								{
									String ls_idTurno;

									ls_idTurno = lt_turno.getIdTurno();

									if(StringUtils.isValidString(ls_idTurno))
										ls_plantilla = ls_plantilla.replace(ls_tag, ls_idTurno);
								}
							}
						}
					}
				}

				{
					ls_tag = "<TAG_CONSIDERACIONES>";

					if(ls_plantilla.contains(ls_tag))
					{
						String ls_consideraciones;

						ls_consideraciones = ath_parametros.getConsideracionesAntiguoSistema();

						if(StringUtils.isValidString(ls_consideraciones))
							ls_plantilla = ls_plantilla.replace(ls_tag, ls_consideraciones);
					}
				}

				{
					ls_tag = "<TAG_USUARIO>";

					if(ls_plantilla.contains(ls_tag) && StringUtils.isValidString(as_idUsuario))
						ls_plantilla = ls_plantilla.replace(ls_tag, as_idUsuario);
				}

				Constantes lc_usuarioFirma;
				String     ls_tagUsuarioFirma;
				int        li_incrX = 0;
				int        li_incrY = 0;

				lc_usuarioFirma     = new Constantes();
				ls_tagUsuarioFirma  = "<TAG_USUARIO_FIRMA>";

				lc_usuarioFirma.setIdConstante(ConstanteCommon.USUARIO_FIRMA_SUSPENSION);

				lc_usuarioFirma     = DaoCreator.getConstantesDAO(adm_manager).findByIdWithImage(lc_usuarioFirma);
				ls_plantilla        = getFirmaMasivaBusiness()
						                      .reemplazarUsuarioFirmaCargo(
						    ls_plantilla, lc_usuarioFirma, ls_tagUsuarioFirma, "<TAG_USUARIO_FIRMA_CARGO>"
						);

				lmss_datos       = finalizarPlantilla(ls_plantilla, null, adm_manager);
				ls_plantilla     = lmss_datos.get(IdentificadoresCommon.PLANTILLA);

				lba_return = new PDFGenerator().convertirRTFToPDF(ls_plantilla.getBytes(), adm_manager);

				if(ab_firmaMasiva)
				{
					byte[] lba_grafo;

					lba_grafo = null;

					if(lc_usuarioFirma != null)
					{
						lba_grafo     = lc_usuarioFirma.getImagenes();
						li_incrX      = NumericUtils.getInt(lc_usuarioFirma.getEntero());
						li_incrY      = NumericUtils.getInt(lc_usuarioFirma.getReal());
					}

					lba_return = getFirmaMasivaBusiness()
							             .reemplazarBookmarsFirma(lba_return, lba_grafo, li_incrX, li_incrY);
				}
			}
			else
				throw new B2BException(ErrorKeys.ERROR_PLANTILLA);

			if(lba_return == null)
				throw new B2BException(ErrorKeys.ERROR_GENERANDO_PLANTILLA);
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("generarResolucionAutorizacionFirma", lb2be_e);

			throw lb2be_e;
		}

		return lba_return;
	}

	/**
	 * Método encargado de generar archivo en formato pdf  utilizando  las plantillas  PLANTILLA_SOLICITUD_COMPLEMENTACION.
	 *
	 * @param aahr_parametros Objeto de tipo AmpliacionHistoriaRegistral que contiene los datos necesarios para buscar la información a incluir en la plantilla.
	 * @param adm_manager Objeto de tipo DAOManager utilizado para crear la conexión hacia la base de datos
	 * @param as_idUsuario Variable de tipo String que contiene el usuario que realiza la acción.
	 * @param as_localIp Variable de tipo String que contiene la dirección ip local  desde donde seque realiza la acción.
	 * @param as_remoteIp Variable de tipo String que contiene la dirección ip remota desde donde seque realiza la acción.
	 * @param ab_save Variable de tipo boolean, si es true genera e insertar documento de salida y guarda información de la complementación.
	 * @param ab_firmaMasiva Variable de tipo boolean , si es true inserta firma masiva en la plantilla de lo contrario no lo hace.
	 * @return Arreglo de bytes que contiene la plantilla generada.
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see byte[]
	 */
	private byte[] generarSolicitudComplementacion(
	    AmpliacionHistoriaRegistral aahr_parametros, DAOManager adm_manager, String as_idUsuario, String as_localIp,
	    String as_remoteIp, boolean ab_save, boolean ab_firmaMasiva
	)
	    throws B2BException
	{
		byte[] lba_archivo;

		lba_archivo = null;

		try
		{
			if(aahr_parametros != null)
			{
				String     ls_constante;
				Constantes lc_constante;
				byte[]     lba_plantilla;

				ls_constante     = ConstanteCommon.PLANTILLA_SOLICITUD_COMPLEMENTACION;
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
					Long                ll_idTurnoHistoria;
					String              ls_idCirculoDestino;
					String              ls_idCirculoOrigen;
					String              ls_idSolicitud;
					String              ls_idTurno;
					String              ls_idUsuario;
					String              ls_matriculaCreadaGrabada;
					String              ls_plantilla;
					String              ls_tag;

					lmss_datos                    = null;
					ll_idTurnoHistoria            = aahr_parametros.getIdTurnoHistoria();
					ls_idCirculoDestino           = aahr_parametros.getIdCirculoDestino();
					ls_idCirculoOrigen            = aahr_parametros.getIdCirculoOrigen();
					ls_idSolicitud                = aahr_parametros.getIdSolicitud();
					ls_idTurno                    = aahr_parametros.getIdTurno();
					ls_idUsuario                  = aahr_parametros.getIdUsurio();
					ls_matriculaCreadaGrabada     = aahr_parametros.getDataMatriculaCreadaGrabada();
					ls_plantilla                  = new String(lba_plantilla);
					ls_tag                        = null;

					if(!StringUtils.isValidString(ls_idCirculoOrigen))
						throw new B2BException(ErrorKeys.ERROR_CIRCULO_ORIGEN);

					if(!StringUtils.isValidString(ls_idCirculoDestino))
						throw new B2BException(ErrorKeys.ERROR_CIRCULO_DESTINO);

					if(ls_idCirculoOrigen.equalsIgnoreCase(ls_idCirculoDestino))
						throw new B2BException(ErrorKeys.ERROR_CIRCULOS_IGUALES);

					{
						CirculoRegistral lcr_circuloRegistral;

						lcr_circuloRegistral = new CirculoRegistral();
						lcr_circuloRegistral.setIdCirculo(ls_idCirculoOrigen);

						lcr_circuloRegistral = DaoCreator.getCirculoRegistralDAO(adm_manager)
								                             .findById(lcr_circuloRegistral);

						if(lcr_circuloRegistral == null)
							throw new B2BException(ErrorKeys.NO_SE_GENERO_CERTIFICADO_AMPLIACION);

						String     ls_idOficinaOrigen;
						BigDecimal lbd_version;

						ls_idOficinaOrigen     = lcr_circuloRegistral.getIdOficinaOrigen();
						lbd_version            = lcr_circuloRegistral.getVersion();

						String ls_idCirculo;

						ls_tag                 = "<TAG_MUN_OFI_ORIGEN>";
						ls_idCirculo           = lcr_circuloRegistral.getIdCirculo();

						if(ls_plantilla.contains(ls_tag) && StringUtils.isValidString(ls_idCirculo))
						{
							Municipio lm_municipio;

							lm_municipio     = new Municipio();

							lm_municipio = DaoCreator.getMunicipioDAO(adm_manager).findByIdCirculo(ls_idCirculo);

							if(lm_municipio != null)
							{
								String ls_munOficinaOrigen;
								ls_munOficinaOrigen = lm_municipio.getNombre();

								if(ls_plantilla.contains(ls_tag) && StringUtils.isValidString(ls_munOficinaOrigen))
									ls_plantilla = ls_plantilla.replace(ls_tag, ls_munOficinaOrigen);
							}
							else
								throw new B2BException(ErrorKeys.NO_SE_GENERO_CERTIFICADO_AMPLIACION);
						}

						if(StringUtils.isValidString(ls_idOficinaOrigen) && (lbd_version != null))
						{
							OficinaOrigen loo_oficinaOrigen;
							String        ls_nombreOficinaOrigen;

							loo_oficinaOrigen = new OficinaOrigen();
							loo_oficinaOrigen.setIdOficinaOrigen(ls_idOficinaOrigen);
							loo_oficinaOrigen.setVersion(lbd_version);

							loo_oficinaOrigen = DaoCreator.getOficinaOrigenDAO(adm_manager).findById(loo_oficinaOrigen);

							if(loo_oficinaOrigen != null)
							{
								ls_nombreOficinaOrigen = loo_oficinaOrigen.getNombre();

								if(StringUtils.isValidString(ls_nombreOficinaOrigen))
								{
									ls_tag = "<TAG_ID_OFI_ORIGEN>";

									if(ls_plantilla.contains(ls_tag))
										ls_plantilla = ls_plantilla.replace(ls_tag, ls_nombreOficinaOrigen);

									Pais lp_pais;

									lp_pais = new Pais();
									lp_pais.setIdPais(loo_oficinaOrigen.getIdPais());

									lp_pais = DaoCreator.getPaisDAO(adm_manager).findById(lp_pais);

									if(lp_pais == null)
										throw new B2BException(ErrorKeys.NO_SE_GENERO_CERTIFICADO_AMPLIACION);

									Departamento ld_departamento;

									ld_departamento = new Departamento();
									ld_departamento.setIdPais(lp_pais.getIdPais());
									ld_departamento.setIdDepartamento(loo_oficinaOrigen.getIdDepartamento());

									ld_departamento = DaoCreator.getDepartamentoDAO(adm_manager)
											                        .findById(ld_departamento);

									if(ld_departamento == null)
										throw new B2BException(ErrorKeys.NO_SE_GENERO_CERTIFICADO_AMPLIACION);
								}
							}
						}
					}

					{
						Calendar lc_calendar;
						Date     ls_date;
						String   ls_dia;
						String   ls_mes;
						String   ls_anio;
						int      li_mes;

						ls_date         = new Date();
						lc_calendar     = Calendar.getInstance();
						lc_calendar.setTime(ls_date);

						ls_dia      = StringUtils.getString(ls_date, "dd");
						li_mes      = lc_calendar.get(Calendar.MONTH);
						ls_mes      = DateUtils.getMes(li_mes + 1);
						ls_anio     = StringUtils.getString(ls_date, "yyyy");

						if(StringUtils.isValidString(ls_dia))
						{
							ls_tag = "<TAG_DIAS>";

							if(ls_plantilla.contains(ls_tag))
								ls_plantilla = ls_plantilla.replace(ls_tag, ls_dia);
						}

						if(StringUtils.isValidString(ls_mes))
						{
							ls_tag = "<TAG_MES_LETRAS>";

							if(ls_plantilla.contains(ls_tag))
								ls_plantilla = ls_plantilla.replace(ls_tag, ls_mes);
						}

						if(StringUtils.isValidString(ls_anio))
						{
							ls_tag = "<TAG_ANIO>";

							if(ls_plantilla.contains(ls_tag))
								ls_plantilla = ls_plantilla.replace(ls_tag, ls_anio);
						}
					}

					{
						CirculoRegistral lcr_circuloRegistral;

						lcr_circuloRegistral = new CirculoRegistral();
						lcr_circuloRegistral.setIdCirculo(ls_idCirculoDestino);

						lcr_circuloRegistral = DaoCreator.getCirculoRegistralDAO(adm_manager)
								                             .findById(lcr_circuloRegistral);

						if(lcr_circuloRegistral == null)
							throw new B2BException(ErrorKeys.NO_SE_GENERO_CERTIFICADO_AMPLIACION);

						String     ls_idOficinaOrigen;
						BigDecimal lbd_version;

						ls_idOficinaOrigen     = lcr_circuloRegistral.getIdOficinaOrigen();
						lbd_version            = lcr_circuloRegistral.getVersion();

						if(StringUtils.isValidString(ls_idOficinaOrigen) && (lbd_version != null))
						{
							String        ls_nombreOficinaOrigen;
							String        ls_direccionOficinaOrigen;
							OficinaOrigen loo_oficinaOrigen;

							ls_nombreOficinaOrigen        = null;
							ls_direccionOficinaOrigen     = null;
							loo_oficinaOrigen             = new OficinaOrigen();
							loo_oficinaOrigen.setIdOficinaOrigen(ls_idOficinaOrigen);
							loo_oficinaOrigen.setVersion(lbd_version);

							loo_oficinaOrigen = DaoCreator.getOficinaOrigenDAO(adm_manager).findById(loo_oficinaOrigen);

							if(loo_oficinaOrigen != null)
							{
								ls_nombreOficinaOrigen     = loo_oficinaOrigen.getNombre();

								ls_tag = "<TAG_ID_OFI_DESTINO>";

								if(StringUtils.isValidString(ls_nombreOficinaOrigen))
								{
									if(ls_plantilla.contains(ls_tag))
										ls_plantilla = ls_plantilla.replace(ls_tag, ls_nombreOficinaOrigen);
								}

								ls_direccionOficinaOrigen     = loo_oficinaOrigen.getDireccion();

								ls_tag = "<TAG_DIR_OFI_DESTINO>";

								if(StringUtils.isValidString(ls_direccionOficinaOrigen))
								{
									if(ls_plantilla.contains(ls_tag))
										ls_plantilla = saltoDeCarroDespues(
											    ls_plantilla, ls_tag, ls_direccionOficinaOrigen
											);
								}

								Pais lp_pais;

								lp_pais = new Pais();
								lp_pais.setIdPais(loo_oficinaOrigen.getIdPais());

								lp_pais = DaoCreator.getPaisDAO(adm_manager).findById(lp_pais);

								if(lp_pais == null)
									throw new B2BException(ErrorKeys.NO_SE_GENERO_CERTIFICADO_AMPLIACION);

								Departamento ld_departamento;

								ld_departamento = new Departamento();
								ld_departamento.setIdPais(lp_pais.getIdPais());
								ld_departamento.setIdDepartamento(loo_oficinaOrigen.getIdDepartamento());

								ld_departamento = DaoCreator.getDepartamentoDAO(adm_manager).findById(ld_departamento);

								if(ld_departamento == null)
									throw new B2BException(ErrorKeys.NO_SE_GENERO_CERTIFICADO_AMPLIACION);

								ls_tag = "<TAG_DEP_OFI_DESTINO>";

								if(ls_plantilla.contains(ls_tag))
									ls_plantilla = ls_plantilla.replace(ls_tag, ld_departamento.getNombre() + ", ");

								Municipio lm_municipio;

								lm_municipio = new Municipio();
								lm_municipio.setIdMunicipio(loo_oficinaOrigen.getIdMunicipio());
								lm_municipio.setIdDepartamento(ld_departamento.getIdDepartamento());
								lm_municipio.setIdPais(lp_pais.getIdPais());

								lm_municipio = DaoCreator.getMunicipioDAO(adm_manager).findById(lm_municipio);

								if(lm_municipio == null)
									throw new B2BException(ErrorKeys.NO_SE_GENERO_CERTIFICADO_AMPLIACION);

								ls_tag = "<TAG_MUN_OFI_DESTINO>";

								if(ls_plantilla.contains(ls_tag))
									ls_plantilla = ls_plantilla.replace(ls_tag, lm_municipio.getNombre());
							}
						}
					}

					{
						if(StringUtils.isValidString(ls_idTurno))
						{
							ls_tag = "<TAG_ACC_TUR_ID_TURNO>";

							if(ls_plantilla.contains(ls_tag))
								ls_plantilla = ls_plantilla.replace(ls_tag, ls_idTurno);
						}
					}

					{
						List<Map<String, Object>> lll_dataAntiguoSistema;
						lll_dataAntiguoSistema = aahr_parametros.getInfoAntiguoSistema();

						if(CollectionUtils.isValidCollection(lll_dataAntiguoSistema))
						{
							Map<String, Object> lhm_dataAntiguoSistema;
							lhm_dataAntiguoSistema = lll_dataAntiguoSistema.get(0);

							if(CollectionUtils.isValidCollection(lhm_dataAntiguoSistema))
							{
								{
									BigDecimal lbd_libro;
									lbd_libro = (BigDecimal)lhm_dataAntiguoSistema.get("ID_LIBRO_ANT_SISTEMA");

									if(NumericUtils.isValidBigDecimal(lbd_libro))
									{
										ls_tag = "<TAG_LIBRO>";

										String ls_libro;
										Long   ll_libro;

										ll_libro     = NumericUtils.getLongWrapper(lbd_libro);
										ls_libro     = StringUtils.getString(NumericUtils.getDouble(ll_libro));

										if(ls_plantilla.contains(ls_tag))
											ls_plantilla = ls_plantilla.replace(ls_tag, ls_libro);
									}
								}

								{
									BigDecimal lbd_tomo;
									lbd_tomo = (BigDecimal)lhm_dataAntiguoSistema.get("TOMO");

									if(NumericUtils.isValidBigDecimal(lbd_tomo))
									{
										ls_tag = "<TAG_TOMO>";

										String ls_tomo;
										Long   ll_tomo;

										ll_tomo     = NumericUtils.getLongWrapper(lbd_tomo);
										ls_tomo     = StringUtils.getString(NumericUtils.getDouble(ll_tomo));

										if(ls_plantilla.contains(ls_tag))
											ls_plantilla = ls_plantilla.replace(ls_tag, ls_tomo);
									}
								}

								{
									BigDecimal lbd_folio;
									lbd_folio = (BigDecimal)lhm_dataAntiguoSistema.get("FOLIO");

									if(NumericUtils.isValidBigDecimal(lbd_folio))
									{
										ls_tag = "<TAG_FOLIO>";

										String ls_folio;
										Long   ll_folio;

										ll_folio     = NumericUtils.getLongWrapper(lbd_folio);
										ls_folio     = StringUtils.getString(NumericUtils.getDouble(ll_folio));

										if(ls_plantilla.contains(ls_tag))
											ls_plantilla = ls_plantilla.replace(ls_tag, ls_folio);
									}
								}

								{
									String ls_partida;
									ls_partida = (String)lhm_dataAntiguoSistema.get("PARTIDA");

									if(StringUtils.isValidString(ls_partida))
									{
										ls_tag = "<TAG_PARTIDA>";

										if(ls_plantilla.contains(ls_tag))
											ls_plantilla = ls_plantilla.replace(ls_tag, ls_partida);
									}
								}

								{
									BigDecimal lbd_anio;
									lbd_anio = (BigDecimal)lhm_dataAntiguoSistema.get("ANIO");

									if(NumericUtils.isValidBigDecimal(lbd_anio))
									{
										ls_tag = "<TAG_ID_ANIO>";

										String ls_anio;
										Long   ll_anio;

										ll_anio     = NumericUtils.getLongWrapper(lbd_anio);
										ls_anio     = StringUtils.getString(NumericUtils.getDouble(ll_anio));

										if(ls_plantilla.contains(ls_tag))
											ls_plantilla = ls_plantilla.replace(ls_tag, ls_anio);
									}
								}
							}
						}
					}

					{
						List<Map<String, Object>> lll_documento;
						lll_documento = aahr_parametros.getDocumentos();

						if(CollectionUtils.isValidCollection(lll_documento))
						{
							Map<String, Object> lhm_dataDocumento;
							lhm_dataDocumento = lll_documento.get(0);

							if(CollectionUtils.isValidCollection(lhm_dataDocumento))
							{
								{
									String ls_tipoDocumento;
									ls_tipoDocumento = (String)lhm_dataDocumento.get("TIPO_DOCUMENTO");

									if(StringUtils.isValidString(ls_tipoDocumento))
									{
										ls_tag = "<TAG_TIPO_DOCUMENTO>";

										if(ls_plantilla.contains(ls_tag))
											ls_plantilla = ls_plantilla.replace(ls_tag, ls_tipoDocumento);
									}
								}

								{
									String ls_numeroDocumento;
									ls_numeroDocumento = (String)lhm_dataDocumento.get("NUMERO");

									if(StringUtils.isValidString(ls_numeroDocumento))
									{
										ls_tag = "<TAG_NUMERO_DOCUMENTO>";

										if(ls_plantilla.contains(ls_tag))
											ls_plantilla = ls_plantilla.replace(ls_tag, ls_numeroDocumento);
									}
								}

								{
									Date ld_fechaDocumento;

									ld_fechaDocumento     = null;
									ld_fechaDocumento     = (Date)lhm_dataDocumento.get("FECHA_DOCUMENTO");

									if(ld_fechaDocumento != null)
									{
										String ls_fechaDocumento;
										ls_fechaDocumento = StringUtils.getString(ld_fechaDocumento, "dd//MM/yyyy");

										if(StringUtils.isValidString(ls_fechaDocumento))
										{
											ls_tag = "<TAG_FECHA_DOCUMENTO>";

											if(ls_plantilla.contains(ls_tag))
												ls_plantilla = ls_plantilla.replace(ls_tag, ls_fechaDocumento);
										}
									}
								}

								{
									String ls_oficinaOrigen;
									ls_oficinaOrigen = (String)lhm_dataDocumento.get("NOMBRE_OFICINA");

									if(StringUtils.isValidString(ls_oficinaOrigen))
									{
										ls_tag = "<TAG_OFICINA_ORIGEN_DOCUMENTO>";

										if(ls_plantilla.contains(ls_tag))
											ls_plantilla = ls_plantilla.replace(ls_tag, ls_oficinaOrigen);
									}
								}

								{
									String ls_ciudadDocumento;
									ls_ciudadDocumento = (String)lhm_dataDocumento.get("MUNICIPIO");

									if(StringUtils.isValidString(ls_ciudadDocumento))
									{
										ls_tag = "<TAG_CIUDAD_DOCUMENTO>";

										if(ls_plantilla.contains(ls_tag))
											ls_plantilla = ls_plantilla.replace(ls_tag, ls_ciudadDocumento);
									}
								}
							}
						}
					}

					{
						if(StringUtils.isValidString(ls_idUsuario))
						{
							ls_tag = "<TAG_USUARIO>";

							if(ls_plantilla.contains(ls_tag))
								ls_plantilla = ls_plantilla.replace(ls_tag, ls_idUsuario);
						}
					}

					{
						if(StringUtils.isValidString(ls_matriculaCreadaGrabada))
						{
							AccPredioRegistro lapr_accPredioRegistro;
							lapr_accPredioRegistro = aahr_parametros.getAccPredioRegistro();

							if(lapr_accPredioRegistro != null)
							{
								String ls_idZonaRegsitral;
								String ls_idTipoPredio;
								String ls_idCirculo;
								Long   ll_idMatricula;

								ls_idZonaRegsitral     = lapr_accPredioRegistro.getIdZonaRegistral();
								ls_idTipoPredio        = lapr_accPredioRegistro.getIdTipoPredio();
								ls_idCirculo           = lapr_accPredioRegistro.getIdCirculo();
								ll_idMatricula         = lapr_accPredioRegistro.getIdMatricula();

								if(StringUtils.isValidString(ls_idCirculo) && NumericUtils.isValidLong(ll_idMatricula))
								{
									ls_tag = "<TAG_MATRICULA>";

									if(ls_plantilla.contains(ls_tag))
									{
										String ls_matricula;
										ls_matricula     = ls_idCirculo + " - " + ll_idMatricula;

										ls_plantilla = ls_plantilla.replace(ls_tag, ls_matricula);
									}

									DireccionPredioAcc    ldpa_direccionPredio;
									DireccionPredioAccDAO ldpaDAO_direccionPredioAccDAO;

									ldpa_direccionPredio              = new DireccionPredioAcc();
									ldpaDAO_direccionPredioAccDAO     = DaoCreator.getDireccionPredioAccDAO(
										    adm_manager
										);

									ldpa_direccionPredio.setIdMatricula(ll_idMatricula);
									ldpa_direccionPredio.setIdCirculo(ls_idCirculo);

									ldpa_direccionPredio = ldpaDAO_direccionPredioAccDAO.findLastByIdCirculoMatricula(
										    ldpa_direccionPredio
										);

									if(ldpa_direccionPredio != null)
									{
										StringBuilder lsb_direccionCompleta;
										String        ls_direccionCompleta;

										lsb_direccionCompleta     = new StringBuilder();
										ls_direccionCompleta      = null;

										{
											String ls_tipoEje;
											ls_tipoEje = ldpa_direccionPredio.getIdTipoEjePrincipal();

											if(StringUtils.isValidString(ls_tipoEje))
											{
												TipoEje lte_tmp;
												lte_tmp = new TipoEje();
												lte_tmp.setIdTipoEje(ls_tipoEje);

												lte_tmp = DaoCreator.getTipoEjeDAO(adm_manager).findById(lte_tmp);

												if(lte_tmp != null)
													lsb_direccionCompleta.append(
													    StringUtils.getStringNotNull(lte_tmp.getNombre()) + " "
													);
											}
										}

										lsb_direccionCompleta.append(
										    StringUtils.getStringNotNull(ldpa_direccionPredio.getDatoEjePrincipal())
										    + " "
										);

										{
											String ls_tipoEje1;
											ls_tipoEje1 = ldpa_direccionPredio.getIdTipoEjeSecundario();

											if(StringUtils.isValidString(ls_tipoEje1))
											{
												TipoEje lte_tmp;
												lte_tmp = new TipoEje();
												lte_tmp.setIdTipoEje(ls_tipoEje1);

												lte_tmp = DaoCreator.getTipoEjeDAO(adm_manager).findById(lte_tmp);

												if(lte_tmp != null)
													lsb_direccionCompleta.append(
													    StringUtils.getStringNotNull(lte_tmp.getNombre()) + " "
													);
											}
										}

										lsb_direccionCompleta.append(
										    StringUtils.getStringNotNull(ldpa_direccionPredio.getDatoEjeSecundario())
										    + " "
										);
										lsb_direccionCompleta.append(
										    StringUtils.getStringNotNull(
										        ldpa_direccionPredio.getComplementoDireccion()
										    ) + " "
										);

										{
											String ls_tmp;
											ls_tmp = lsb_direccionCompleta.toString();

											if(StringUtils.isValidString(ls_tmp))
											{
												ls_direccionCompleta     = ls_tmp.trim();

												ls_tag = "<TAG_DIRECCION_PREDIO>";

												if(ls_plantilla.contains(ls_tag))
													ls_plantilla = ls_plantilla.replace(ls_tag, ls_direccionCompleta);
											}
										}
									}
								}

								if(StringUtils.isValidString(ls_idTipoPredio))
								{
									PredioTipo lpt_predioTipo;
									lpt_predioTipo = new PredioTipo();
									lpt_predioTipo.setIdTipoPredio(ls_idTipoPredio);
									lpt_predioTipo = DaoCreator.getPredioTipoDao(adm_manager).findById(lpt_predioTipo);

									if(lpt_predioTipo != null)
									{
										String ls_nombrePredioTipo;
										ls_nombrePredioTipo = lpt_predioTipo.getIlicode();

										if(StringUtils.isValidString(ls_nombrePredioTipo))
										{
											ls_tag = "<TAG_TIPO_PREDIO>";

											if(ls_plantilla.contains(ls_tag))
												ls_plantilla = ls_plantilla.replace(ls_tag, ls_nombrePredioTipo);
										}
									}
								}

								if(StringUtils.isValidString(ls_idZonaRegsitral))
								{
									ZonaRegistral lzr_registral;

									lzr_registral = new ZonaRegistral();
									lzr_registral.setIdZonaRegistral(ls_idZonaRegsitral);
									lzr_registral = DaoCreator.getZonaRegistralDAO(adm_manager).findById(lzr_registral);

									if(lzr_registral != null)
									{
										String ls_idPais;
										String ls_idDepartamento;
										String ls_municipio;

										ls_idPais             = lzr_registral.getIdPais();
										ls_idDepartamento     = lzr_registral.getIdDepartamento();
										ls_municipio          = lzr_registral.getIdMunicipio();

										if(StringUtils.isValidString(ls_idPais))
										{
											Pais lp_pais;
											lp_pais = new Pais();
											lp_pais.setIdPais(ls_idPais);
											lp_pais = DaoCreator.getPaisDAO(adm_manager).findById(lp_pais);

											if(lp_pais != null)
											{
												Departamento ld_departamento;
												ld_departamento = new Departamento();
												ld_departamento.setIdPais(lp_pais.getIdPais());
												ld_departamento.setIdDepartamento(ls_idDepartamento);
												ld_departamento = DaoCreator.getDepartamentoDAO(adm_manager)
														                        .findById(ld_departamento);

												if(ld_departamento != null)
												{
													Municipio lm_municipio;
													lm_municipio = new Municipio();
													lm_municipio.setIdPais(ld_departamento.getIdPais());
													lm_municipio.setIdDepartamento(ld_departamento.getIdDepartamento());
													lm_municipio.setIdMunicipio(ls_municipio);
													lm_municipio = DaoCreator.getMunicipioDAO(adm_manager)
															                     .findById(lm_municipio);

													if(lm_municipio != null)
													{
														String ls_nombreMunicipio;
														ls_nombreMunicipio = lm_municipio.getNombre();

														if(StringUtils.isValidString(ls_nombreMunicipio))
														{
															ls_tag = "<TAG_MUNICIPIO>";

															if(ls_plantilla.contains(ls_tag))
																ls_plantilla = ls_plantilla.replace(
																	    ls_tag, ls_nombreMunicipio
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

					lmss_datos       = finalizarPlantilla(ls_plantilla, ll_idTurnoHistoria, adm_manager);
					ls_plantilla     = lmss_datos.get(IdentificadoresCommon.PLANTILLA);

					{
						Constantes lc_usuarioFirma;
						String     ls_tagUsuarioFirma;
						int        li_incrX = 0;
						int        li_incrY = 0;

						lc_usuarioFirma     = new Constantes();
						ls_tagUsuarioFirma  = "<TAG_USUARIO_FIRMA_SUSPENSION>";

						lc_usuarioFirma.setIdConstante(ConstanteCommon.USUARIO_FIRMA_SUSPENSION);

						lc_usuarioFirma     = DaoCreator.getConstantesDAO(adm_manager).findByIdWithImage(
							    lc_usuarioFirma
							);

						ls_plantilla     = getFirmaMasivaBusiness()
								                   .reemplazarUsuarioFirmaCargo(
								    ls_plantilla, lc_usuarioFirma, ls_tagUsuarioFirma, "<TAG_CARGO_FIRMA_SUSPENSION>"
								);
						lmss_datos       = finalizarPlantilla(ls_plantilla, ll_idTurnoHistoria, adm_manager);
						ls_plantilla     = lmss_datos.get(IdentificadoresCommon.PLANTILLA);
						lba_archivo      = new PDFGenerator().convertirRTFToPDF(ls_plantilla.getBytes(), adm_manager);

						if(lba_archivo == null)
							throw new B2BException(ErrorKeys.NO_SE_GENERO_CERTIFICADO_AMPLIACION);

						if(ab_firmaMasiva)
						{
							byte[] lba_grafo;

							lba_grafo = null;

							if(lc_usuarioFirma != null)
							{
								lba_grafo     = lc_usuarioFirma.getImagenes();
								li_incrX      = NumericUtils.getInt(lc_usuarioFirma.getEntero());
								li_incrY      = NumericUtils.getInt(lc_usuarioFirma.getReal());
							}

							lba_archivo = getFirmaMasivaBusiness()
									              .reemplazarBookmarsFirma(lba_archivo, lba_grafo, li_incrX, li_incrY);
						}
					}

					if(ab_save)
					{
						CompletitudComplementacion lcc_completitudComplementacion;
						CompletitudComplementacion lcc_completitudComplementacionAux;

						lcc_completitudComplementacion        = new CompletitudComplementacion();
						lcc_completitudComplementacionAux     = null;

						lcc_completitudComplementacion.setIdCirculoRegistralDestino(ls_idCirculoDestino);
						lcc_completitudComplementacion.setIdTurno(ls_idTurno);
						lcc_completitudComplementacion.setIdTurnoHistoria(ll_idTurnoHistoria);
						lcc_completitudComplementacion.setIdUsuarioCreacion(as_idUsuario);
						lcc_completitudComplementacion.setIpCreacion(as_remoteIp);

						if(StringUtils.isValidString(ls_matriculaCreadaGrabada))
						{
							AccPredioRegistro lapr_accPredioRegistro;

							lapr_accPredioRegistro     = aahr_parametros.getAccPredioRegistro();
							lapr_accPredioRegistro     = DaoCreator.getAccPredioRegistroDAO(adm_manager)
									                                   .findByCirculoMatricula(lapr_accPredioRegistro);

							lcc_completitudComplementacionAux = DaoCreator.getCompletitudComplementacionDAO(
								    adm_manager
								).findById(lcc_completitudComplementacion);

							if(lapr_accPredioRegistro != null)
							{
								Long ll_idComplementacion;

								ll_idComplementacion = lapr_accPredioRegistro.getIdComplementacion();

								if(NumericUtils.isValidLong(ll_idComplementacion))
									lcc_completitudComplementacion.setIdComplementacion(ll_idComplementacion);
								else
									lcc_completitudComplementacionAux = DaoCreator.getCompletitudComplementacionDAO(
										    adm_manager
										).findByIdNoComplementacion(lcc_completitudComplementacion);
							}
						}
						else
							lcc_completitudComplementacionAux = DaoCreator.getCompletitudComplementacionDAO(
								    adm_manager
								).findByIdNoComplementacion(lcc_completitudComplementacion);

						if(lcc_completitudComplementacionAux == null)
							DaoCreator.getCompletitudComplementacionDAO(adm_manager)
								          .insert(lcc_completitudComplementacion);
						else
						{
							lcc_completitudComplementacion.setIdUsuarioModificacion(ls_idUsuario);
							DaoCreator.getCompletitudComplementacionDAO(adm_manager)
								          .update(lcc_completitudComplementacion);
						}

						if(StringUtils.isValidString(ls_idTurno))
						{
							ImagenesDAO         lid_DAO;
							DocumentosSalidaDAO ldsd_DAO;
							long                ll_idDocumentoSalida;
							long                ll_secuencia;

							boolean lb_existeImagen;

							Imagenes         li_imagenes;
							DocumentosSalida lds_documentosSalida;

							lid_DAO      = DaoCreator.getImagenesDAO(adm_manager);
							ldsd_DAO     = DaoCreator.getDocumentosSalidaDAO(adm_manager);

							li_imagenes              = new Imagenes();
							lds_documentosSalida     = new DocumentosSalida();

							ll_idDocumentoSalida = 0;

							{
								DocumentosSalida lds_docTmp;
								lds_docTmp = new DocumentosSalida();

								lds_docTmp.setIdSolicitud(ls_idSolicitud);
								lds_docTmp.setIdTurno(ls_idTurno);
								lds_docTmp.setTipo(TipoArchivoCommon.SOLICITUD_AMPLIACION);
								lds_docTmp.setEstado(EstadoCommon.ACTIVO);

								lds_docTmp = ldsd_DAO.findDocumentByTurnoTipoEstado(lds_docTmp);

								if(lds_docTmp != null)
									ll_idDocumentoSalida = lds_docTmp.getIdDocumentoSalida();
							}

							lb_existeImagen = ll_idDocumentoSalida > 0;

							if(lb_existeImagen)
							{
								lds_documentosSalida.setIdDocumentoSalida(ll_idDocumentoSalida);

								lds_documentosSalida = ldsd_DAO.findById(lds_documentosSalida);

								if(lds_documentosSalida != null)
									li_imagenes.setIdImagen(NumericUtils.getInt(lds_documentosSalida.getIdImagen()));
							}

							li_imagenes.setTipoArchivo(ExtensionCommon.PDF_MAYUS);
							li_imagenes.setIdUsuarioCreacion(ls_idUsuario);
							li_imagenes.setIdUsuarioModificacion(ls_idUsuario);
							li_imagenes.setIpCreacion(as_remoteIp);
							li_imagenes.setIpModificacion(as_remoteIp);
							li_imagenes.setImagenBlob(lba_archivo);
							li_imagenes.setCodigoVerificacion(
							    lmss_datos.get(IdentificadoresCommon.CODIGO_VERIFICACION)
							);

							ll_secuencia = lid_DAO.insertOrUpdate(li_imagenes, !lb_existeImagen);

							if(!lb_existeImagen)
							{
								if(ll_secuencia <= 0)
									throw new B2BException(ErrorKeys.NO_INSERTO_IMAGENES);

								lds_documentosSalida.setIdTurno(ls_idTurno);
								lds_documentosSalida.setIdSolicitud(ls_idSolicitud);

								lds_documentosSalida.setIdImagen(NumericUtils.getLongWrapper(ll_secuencia));
								lds_documentosSalida.setTipo(TipoArchivoCommon.SOLICITUD_AMPLIACION);
								lds_documentosSalida.setEstado(EstadoCommon.ACTIVO);

								{
									TurnoHistoria lth_turnoHistoria;

									lth_turnoHistoria = new TurnoHistoria();

									lth_turnoHistoria.setIdTurnoHistoria(ll_idTurnoHistoria);
									lth_turnoHistoria = DaoCreator.getTurnoHistoriaDAO(adm_manager)
											                          .findById(lth_turnoHistoria);

									if(lth_turnoHistoria != null)
										lds_documentosSalida.setIdTurnoHistoria(
										    NumericUtils.getInteger(lth_turnoHistoria.getIdTurnoHistoria())
										);
								}

								lds_documentosSalida.setIdTipoDocumental(TipoDocumentalCommon.RESUMEN_DE_LA_SOLICITUD);

								lds_documentosSalida.setIdUsuarioCreacion(ls_idUsuario);
								lds_documentosSalida.setIpCreacion(as_remoteIp);

								ldsd_DAO.insertOrUpdate(lds_documentosSalida, true);
							}
						}
					}
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("generarSolicitudComplementacion", lb2be_e);

			throw lb2be_e;
		}

		return lba_archivo;
	}

	/**
	 * Generar solicitud complementacion ofi destino.
	 *
	 * @param ath_parametros correspondiente al valor de ath parametros
	 * @param ab_cargar correspondiente al valor de ab cargar
	 * @param ab_firma correspondiente al valor de ab firma
	 * @param as_idUsuario correspondiente al valor de as id usuario
	 * @param as_localIp correspondiente al valor de as local ip
	 * @param adm_manager correspondiente al valor de adm manager
	 * @return el valor de byte[]
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	private byte[] generarSolicitudComplementacionOfiDestino(
	    String ath_parametros, boolean ab_cargar, boolean ab_firma, String as_idUsuario, String as_localIp,
	    DAOManager adm_manager
	)
	    throws B2BException
	{
		byte[] lba_return;
		String ls_sinInformacion;

		lba_return            = null;
		ls_sinInformacion     = "SIN INFORMACIÓN";

		try
		{
			String              ls_plantilla;
			Map<String, String> lmss_datos;
			Calendar            lc_fecha;

			ls_plantilla     = null;
			lmss_datos       = null;
			lc_fecha         = Calendar.getInstance();

			TurnoHistoria lth_parametros;
			lth_parametros   = DaoCreator.getTurnoHistoriaDAO(adm_manager)
					                         .findById(NumericUtils.getLongWrapper(ath_parametros));

			if(lth_parametros != null)
			{
				{
					CompletitudComplementacion lcc_complementacion102;
					lcc_complementacion102 = new CompletitudComplementacion();

					lcc_complementacion102.setIdTurno(lth_parametros.getIdTurno());
					lcc_complementacion102 = DaoCreator.getCompletitudComplementacionDAO(adm_manager)
							                               .findByTurno(lcc_complementacion102);

					String ls_nombrePlantilla;
					ls_nombrePlantilla = null;

					long ll_idEtapa;

					ll_idEtapa = NumericUtils.getLong(lth_parametros.getIdEtapa());

					if(ll_idEtapa != EtapaCommon.ID_ETAPA_BUSCADOR_AS_CIRCULO_DESTINO)
						ls_nombrePlantilla = ConstanteCommon.PLANTILLA_SOLICITUD_COMPLEMENTACION_OFI_DESTINO;
					else
					{
						String ls_hallazgo;
						ls_hallazgo = lcc_complementacion102.getRespuestaBusqueda();

						if(StringUtils.isValidString(ls_hallazgo))
						{
							if(ls_hallazgo.equalsIgnoreCase(EstadoCommon.SI_TXT))
								ls_nombrePlantilla = ConstanteCommon.PLANTILLA_CERTIFICACION_HALLAZGO_COMPLEMENTACION_OFI_DESTINO;
							else
								ls_nombrePlantilla = ConstanteCommon.PLANTILLA_CERTIFICACION_NO_HALLAZGO_COMPLEMENTACION_OFI_DESTINO;
						}
					}

					Constantes lc_constante;

					lc_constante = DaoCreator.getConstantesDAO(adm_manager).findByIdWithImage(ls_nombrePlantilla);

					if(lc_constante != null)
					{
						byte[] lba_plantilla;

						lba_plantilla = lc_constante.getImagenes();

						if(lba_plantilla == null)
							throw new B2BException(ErrorKeys.NO_IMAGEN_CONSTANTE);

						ls_plantilla = new String(lba_plantilla);
					}
					else
					{
						Object[] loa_args;

						loa_args     = new String[1];

						loa_args[0] = ConstanteCommon.PLANTILLA_SOLICITUD_COMPLEMENTACION_OFI_DESTINO;

						throw new B2BException(ErrorKeys.ERROR_SIN_CONSTANTE, loa_args);
					}

					if(StringUtils.isValidString(ls_plantilla) && (lth_parametros != null))
					{
						String ls_tag;

						ls_tag = null;

						if(ab_firma)
						{
							ls_tag = "<TAG_OFICIO>";

							if(ls_plantilla.contains(ls_tag))
							{
								NumeracionOficiosCirculo lnoc_numeracionOficios;

								lnoc_numeracionOficios = findNumeracionOficiosCirculo(
									    lth_parametros, adm_manager, as_idUsuario, as_localIp
									);

								if(lnoc_numeracionOficios != null)
								{
									String ls_consecutivoOficio;

									ls_consecutivoOficio = lnoc_numeracionOficios.getConsecutivoGenerado();

									lth_parametros.setConsecutivoOficioAntiguoSistema(ls_consecutivoOficio);

									ls_plantilla = ls_plantilla.replace(ls_tag, ls_consecutivoOficio);
								}
							}

							ls_tag = "<TAG_REFERENCIA_SALIDA>";

							if(ls_plantilla.contains(ls_tag))
							{
								String ls_referenciaSalida;

								ls_referenciaSalida     = generarIdCorrespondencia(lth_parametros, adm_manager, false);

								ls_plantilla = reemplazarTagEnPlantilla(ls_plantilla, ls_tag, ls_referenciaSalida);
							}
						}

						{
							ls_tag = "<TAG_FECHA_LARGA>";

							if(ls_plantilla.contains(ls_tag))
							{
								Date   ld_fecha;
								String ls_fechaActual;

								ld_fecha           = new Date();
								ls_fechaActual     = DateUtils.convertirLetrasLarga(ld_fecha);

								if(StringUtils.isValidString(ls_fechaActual))
									ls_plantilla = ls_plantilla.replace(ls_tag, ls_fechaActual.toUpperCase());
							}
						}

						{
							ls_tag = "<TAG_COMPLEMENTACION_CIRCULO_DESTINO>";

							if(ls_plantilla.contains(ls_tag))
							{
								String ls_complementacion;

								ls_complementacion = lcc_complementacion102.getInformacionComplementacion();

								if(StringUtils.isValidString(ls_complementacion))
									ls_plantilla = ls_plantilla.replace(ls_tag, ls_complementacion.toUpperCase());
							}
						}

						{
							ls_tag = "<TAG_OBSERVACIONES_CIRCULO_DESTINO>";

							if(ls_plantilla.contains(ls_tag))
							{
								String ls_complementacion;

								ls_complementacion = lcc_complementacion102.getObservacionesNoHallazgo();

								if(StringUtils.isValidString(ls_complementacion))
									ls_plantilla = ls_plantilla.replace(ls_tag, ls_complementacion.toUpperCase());
							}
						}

						{
							ls_tag = "<TAG_CIUDAD>";

							if(ls_plantilla.contains(ls_tag))
							{
								Turno lt_turno;

								lt_turno = DaoCreator.getTurnoDAO(adm_manager).findById(lth_parametros.getIdTurno());

								if(lt_turno != null)
								{
									Municipio lm_municipio;

									lm_municipio = DaoCreator.getMunicipioDAO(adm_manager)
											                     .findByIdCirculo(lt_turno.getIdCirculo());

									if(lm_municipio != null)
									{
										String ls_munOficinaOrigen;

										ls_munOficinaOrigen = lm_municipio.getNombre();

										if(StringUtils.isValidString(ls_munOficinaOrigen))
											ls_plantilla = ls_plantilla.replace(ls_tag, ls_munOficinaOrigen);
									}

									{
										ls_tag = "<TAG_TURNO>";

										if(ls_plantilla.contains(ls_tag))
											ls_plantilla = ls_plantilla.replace(ls_tag, lt_turno.getIdTurno());
									}

									{
										ls_tag = "<TAG_NIR>";

										if(ls_plantilla.contains(ls_tag))
										{
											Solicitud ls_solicitud;

											ls_solicitud = DaoCreator.getSolicitudDAO(adm_manager)
													                     .findById(lt_turno.getIdSolicitud());

											if(ls_solicitud != null)
											{
												String ls_nir;

												ls_nir = ls_solicitud.getNir();

												if(StringUtils.isValidString(ls_nir))
													ls_plantilla = ls_plantilla.replace(ls_tag, ls_nir);
											}
										}
									}
								}

								{
									DatosAntSistema adas_datosAntSistemaPlantilla;
									String          ls_reemplazo;

									adas_datosAntSistemaPlantilla = DaoCreator.getDatosAntSistemaDAO(adm_manager)
											                                      .findByIdSolicitudOne(
											    lt_turno.getIdSolicitud()
											);

									if(adas_datosAntSistemaPlantilla != null)
									{
										DetalleAntSistema ldas_detalleAntSistema;

										ldas_detalleAntSistema = DaoCreator.getDetalleAntSistemaDAO(adm_manager)
												                               .findOneByDatosAntSis(
												    adas_datosAntSistemaPlantilla.getIdDatosAntSistema()
												);

										if(ldas_detalleAntSistema != null)
										{
											{
												ls_tag           = "<TAG_LIBRO>";
												ls_reemplazo     = StringUtils.getString(
													    ldas_detalleAntSistema.getNombreLibro()
													);

												if(
												    ls_plantilla.contains(ls_tag)
													    && StringUtils.isValidString(ls_reemplazo)
												)
													ls_plantilla = ls_plantilla.replace(ls_tag, ls_reemplazo);
												else
													ls_plantilla = ls_plantilla.replace(ls_tag, ls_sinInformacion);
											}

											{
												ls_tag           = "<TAG_TOMO>";
												ls_reemplazo     = StringUtils.getString(
													    ldas_detalleAntSistema.getTomo()
													);

												if(
												    ls_plantilla.contains(ls_tag)
													    && StringUtils.isValidString(ls_reemplazo)
												)
													ls_plantilla = ls_plantilla.replace(ls_tag, ls_reemplazo);
												else
													ls_plantilla = ls_plantilla.replace(ls_tag, ls_sinInformacion);
											}

											{
												ls_tag           = "<TAG_FOLIO>";
												ls_reemplazo     = StringUtils.getString(
													    ldas_detalleAntSistema.getFolio()
													);

												if(
												    ls_plantilla.contains(ls_tag)
													    && StringUtils.isValidString(ls_reemplazo)
												)
													ls_plantilla = ls_plantilla.replace(ls_tag, ls_reemplazo);
												else
													ls_plantilla = ls_plantilla.replace(ls_tag, ls_sinInformacion);
											}

											{
												ls_tag           = "<TAG_NUMERO_PARTIDA>";
												ls_reemplazo     = StringUtils.getString(
													    ldas_detalleAntSistema.getPartida()
													);

												if(
												    ls_plantilla.contains(ls_tag)
													    && StringUtils.isValidString(ls_reemplazo)
												)
													ls_plantilla = ls_plantilla.replace(ls_tag, ls_reemplazo);
												else
													ls_plantilla = ls_plantilla.replace(ls_tag, ls_sinInformacion);
											}

											{
												ls_tag = "<TAG_NOMBRE_PREDIO>";

												if(ls_plantilla.contains(ls_tag))
													ls_plantilla = ls_plantilla.replace(
														    ls_tag, adas_datosAntSistemaPlantilla.getNombrePredio()
														);
											}

											String ls_idPais;
											String ls_idMunicipio;
											String ls_idDepartamento;

											ls_idPais             = adas_datosAntSistemaPlantilla.getIdPais();
											ls_idMunicipio        = adas_datosAntSistemaPlantilla.getIdMunicipio();
											ls_idDepartamento     = adas_datosAntSistemaPlantilla.getIdDepartamento();

											if(
											    StringUtils.isValidString(ls_idMunicipio)
												    && StringUtils.isValidString(ls_idDepartamento)
											)
											{
												{
													Municipio lm_municipio;

													lm_municipio     = new Municipio();

													lm_municipio = DaoCreator.getMunicipioDAO(adm_manager)
															                     .findById(
															    ls_idPais, ls_idDepartamento, ls_idMunicipio
															);

													if(lm_municipio != null)
													{
														ls_tag = "<TAG_MUNICIPIO>";

														if(ls_plantilla.contains(ls_tag))
															ls_plantilla = ls_plantilla.replace(
																    ls_tag, lm_municipio.getNombre()
																);
													}
													else
													{
														ls_tag           = "<TAG_MUNICIPIO>";
														ls_plantilla     = ls_plantilla.replace(
															    ls_tag, ls_sinInformacion
															);
													}
												}
											}
										}
									}
								}

								{
									if(ls_plantilla.contains("<TAG_ANIO>"))
									{
										int li_anio;

										li_anio = lc_fecha.get(Calendar.YEAR);

										if(NumericUtils.isValidInteger(NumericUtils.getInteger(li_anio)))
											ls_plantilla = ls_plantilla.replace(
												    "<TAG_ANIO>", StringUtils.getString(li_anio)
												);
									}
								}

								{
									AccPredioRegistro aapr_predioRegistroPlantilla;

									String            ls_idDocumento;

									aapr_predioRegistroPlantilla     = DaoCreator.getAccPredioRegistroDAO(adm_manager)
											                                         .findByIdTurno(
											    lt_turno.getIdTurno(), false
											);
									ls_idDocumento                   = null;

									if(aapr_predioRegistroPlantilla != null)
										ls_idDocumento = aapr_predioRegistroPlantilla.getIdDocumento();

									if(StringUtils.isValidString(ls_idDocumento))
									{
										Documento ld_documento;
										ld_documento = new Documento();

										ld_documento.setIdDocumento(ls_idDocumento);

										ld_documento = DaoCreator.getDocumentoDAO(adm_manager).findById(ld_documento);

										if(ld_documento != null)
										{
											String ls_idOficinaOrigen;

											ls_idOficinaOrigen     = ld_documento.getIdOficinaOrigen();

											ls_tag = "<TAG_TIPO_DE_DOCUMENTO>";

											if(ls_plantilla.contains(ls_tag))
											{
												String ls_tipoDocumento;
												ls_tipoDocumento = ld_documento.getIdTipoDocumento();

												if(StringUtils.isValidString(ls_tipoDocumento))
												{
													TipoDocumentoPublico ltdp_tipoDocPublico;
													ltdp_tipoDocPublico = new TipoDocumentoPublico();

													ltdp_tipoDocPublico.setIdTipoDocumento(
													    ld_documento.getIdTipoDocumento()
													);

													ltdp_tipoDocPublico = DaoCreator.getTipoDocumentoPublicoDAO(
														    adm_manager
														).findById(ltdp_tipoDocPublico);

													if(ltdp_tipoDocPublico != null)
													{
														String ls_nombre;
														ls_nombre = ltdp_tipoDocPublico.getNombre();

														if(StringUtils.isValidString(ls_nombre))
															ls_plantilla = ls_plantilla.replace(ls_tag, ls_nombre);
													}
												}
												else
													ls_plantilla = ls_plantilla.replace(ls_tag, ls_sinInformacion);
											}

											ls_tag = "<TAG_NUMERO_DE_DOCUMENTO>";

											if(ls_plantilla.contains(ls_tag))
											{
												String ls_documento;
												ls_documento = ld_documento.getNumero();

												if(StringUtils.isValidString(ls_documento))
													ls_plantilla = ls_plantilla.replace(ls_tag, ls_documento);
												else
													ls_plantilla = ls_plantilla.replace(ls_tag, ls_sinInformacion);
											}

											ls_tag = "<TAG_FECHA_DEL_DOCUMENTO>";

											if(ls_plantilla.contains(ls_tag))
											{
												String ls_fechaDocumento;
												ls_fechaDocumento = StringUtils.getString(
													    ld_documento.getFechaDocumento(),
													    FormatoFechaCommon.DIA_MES_ANIO
													);

												if(StringUtils.isValidString(ls_fechaDocumento))
													ls_plantilla = ls_plantilla.replace(ls_tag, ls_fechaDocumento);
												else
													ls_plantilla = ls_plantilla.replace(ls_tag, ls_sinInformacion);
											}

											if(StringUtils.isValidString(ls_idOficinaOrigen))
											{
												OficinaOrigen loo_oficinaOrigen;

												loo_oficinaOrigen     = new OficinaOrigen();

												loo_oficinaOrigen     = DaoCreator.getOficinaOrigenDAO(adm_manager)
														                              .findByIdOnly(ls_idOficinaOrigen);

												ls_tag = "<TAG_OFICINA_DE_ORIGEN>";

												if(ls_plantilla.contains(ls_tag) && (loo_oficinaOrigen != null))
												{
													String ls_nombre;

													ls_nombre = loo_oficinaOrigen.getNombre();

													if(StringUtils.isValidString(ls_nombre))
														ls_plantilla = ls_plantilla.replace(
															    ls_tag, loo_oficinaOrigen.getNombre()
															);
												}
											}
											else
											{
												ls_tag = "<TAG_OFICINA_DE_ORIGEN>";

												if(ls_plantilla.contains(ls_tag))
													ls_plantilla = ls_plantilla.replace(ls_tag, ls_sinInformacion);
											}
										}
									}
									else
									{
										ls_tag = "<TAG_TIPO_DE_DOCUMENTO>";

										if(ls_plantilla.contains(ls_tag))
											ls_plantilla = ls_plantilla.replace(ls_tag, ls_sinInformacion);

										ls_tag = "<TAG_NUMERO_DE_DOCUMENTO>";

										if(ls_plantilla.contains(ls_tag))
											ls_plantilla = ls_plantilla.replace(ls_tag, ls_sinInformacion);

										ls_tag = "<TAG_FECHA_DEL_DOCUMENTO>";

										if(ls_plantilla.contains(ls_tag))
											ls_plantilla = ls_plantilla.replace(ls_tag, ls_sinInformacion);
									}
								}

								{
									CirculoRegistral lcr_circuloRegistral;

									lcr_circuloRegistral = DaoCreator.getCirculoRegistralDAO(adm_manager)
											                             .findById(lt_turno.getIdCirculo());

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
									}

									Subproceso ls_subproceso;

									ls_subproceso = DaoCreator.getSubprocesoDAO(adm_manager)
											                      .findById(
											    lt_turno.getIdProceso(), lt_turno.getIdSubProceso()
											);

									if(ls_subproceso != null)
									{
										ls_tag = "<TAG_TIPO_CERTIFICADO>";

										if(ls_plantilla.contains(ls_tag))
										{
											String ls_nombre;

											ls_nombre = ls_subproceso.getNombre();

											if(StringUtils.isValidString(ls_nombre))
												ls_plantilla = ls_plantilla.replace(ls_tag, ls_nombre);
										}
									}

									String ls_tipoOficina;

									ls_tipoOficina = lcr_circuloRegistral.getTipoOficina();

									if(StringUtils.isValidString(ls_tipoOficina))
									{
										if(ls_tipoOficina.equalsIgnoreCase(EstadoCommon.P))
											ls_tipoOficina = IdentificadoresCommon.PRINCIPAL;
										else if(ls_tipoOficina.equalsIgnoreCase(EstadoCommon.S))
											ls_tipoOficina = IdentificadoresCommon.SECCIONAL;

										ls_tag = "<TAG_PRINCIPAL_SECCIONAL>";

										if(ls_plantilla.contains(ls_tag))
											ls_plantilla = ls_plantilla.replace(ls_tag, ls_tipoOficina);
									}

									{
										ls_tag = "<TAG_USUARIO>";

										if(ls_plantilla.contains(ls_tag) && StringUtils.isValidString(as_idUsuario))
											ls_plantilla = ls_plantilla.replace(ls_tag, as_idUsuario);
									}

									{
										Constantes lc_usuarioFirma;
										String     ls_tagUsuarioFirma;

										lc_usuarioFirma        = new Constantes();
										ls_tagUsuarioFirma     = "<TAG_USUARIO_FIRMA_REGISTRADOR_CIRCULO_ORIGEN>";

										lc_usuarioFirma.setIdConstante(ConstanteCommon.USUARIO_FIRMA_SUSPENSION);

										lc_usuarioFirma     = DaoCreator.getConstantesDAO(adm_manager)
												                            .findByIdWithImage(lc_usuarioFirma);

										ls_plantilla = getFirmaMasivaBusiness()
												               .reemplazarUsuarioFirmaCargo(
												    ls_plantilla, lc_usuarioFirma, ls_tagUsuarioFirma,
												    "<TAG_USUARIO_FIRMA_REGISTRADOR_CIRCULO_ORIGEN>"
												);
									}

									lmss_datos       = finalizarPlantilla(ls_plantilla, null, adm_manager);
									ls_plantilla     = lmss_datos.get(IdentificadoresCommon.PLANTILLA);
									lba_return       = new PDFGenerator().convertirRTFToPDF(
										    ls_plantilla.getBytes(), adm_manager
										);

									{
										Imagenes li_imagen;
										long     ll_idImagen;

										li_imagen = new Imagenes();

										li_imagen.setImagenBlob(lba_return);
										li_imagen.setTipoArchivo(ExtensionCommon.PDF_MAYUS);
										li_imagen.setTamano(NumericUtils.getLongWrapper(lba_return.length));
										li_imagen.setIdUsuarioCreacion(as_idUsuario);
										li_imagen.setIpCreacion(as_localIp);
										li_imagen.setCodigoVerificacion(
										    lmss_datos.get(IdentificadoresCommon.CODIGO_VERIFICACION)
										);

										ll_idImagen = DaoCreator.getImagenesDAO(adm_manager)
												                    .insertOrUpdate(li_imagen, true);

										if(ll_idImagen > 0)
										{
											DocumentosSalida    lds_documentoInsert;
											DocumentosSalidaDAO lds_DAO;
											String              ls_tipo;

											lds_documentoInsert     = new DocumentosSalida();
											lds_DAO                 = DaoCreator.getDocumentosSalidaDAO(adm_manager);
											ls_tipo                 = TipoArchivoCommon.COMUNICADO;

											lds_documentoInsert.setIdTurnoHistoria(
											    NumericUtils.getInteger(lth_parametros.getIdTurnoHistoria())
											);
											lds_documentoInsert.setIdSolicitud(lth_parametros.getIdSolicitud());
											lds_documentoInsert.setIdTurno(lth_parametros.getIdTurno());
											lds_documentoInsert.setIdImagen(NumericUtils.getLongWrapper(ll_idImagen));
											lds_documentoInsert.setTipo(ls_tipo);

											lds_documentoInsert.setIdTipoDocumental(TipoDocumentalCommon.OFICIO);

											/* Falta definir tipo documental para RECHAZO_SOLICITUD
											 else if(ls_tipo.equalsIgnoreCase(TipoArchivoCommon.NOTA_RECHAZO))
											    lds_documentoInsert.setIdTipoDocumental(TipoDocumentalCommon.);*/
											lds_documentoInsert.setRepositorio(
											    ab_firma ? RepositorioCommon.FINAL : RepositorioCommon.TEMPORAL
											);

											lds_documentoInsert.setEstado(EstadoCommon.ACTIVO);
											lds_documentoInsert.setIdUsuarioCreacion(as_idUsuario);
											lds_documentoInsert.setIpCreacion(as_localIp);
											lds_documentoInsert.setFechaEnvio(null);
											lds_documentoInsert.setIdNombreDocumento(null);

											lds_DAO.insertOrUpdate(lds_documentoInsert, true);
										}
										else
											throw new B2BException(ErrorKeys.SIN_SECUENCIA_IMAGENES);
									}
								}
							}
							else
								throw new B2BException(ErrorKeys.ERROR_PLANTILLA);
						}
					}
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("generarSolicitudComplementacion", lb2be_e);

			throw lb2be_e;
		}

		return lba_return;
	}

	/**
	 * Método encargado de generar archivo en formato pdf  utilizando  las plantillas  PLANTILLA_SOLICITUD_FIRMA_LIBRO_AS.
	 *
	 * @param adas_das  Objeto de tipo DatosAntSistema que contiene los datos necesarios para buscar la información a incluir en la plantilla.
	 * @param amso_mso  Objeto de tipo Map<String, Object> que contiene los datos necesarios para buscar la información a incluir en la plantilla.
	 * @param ab_vieneDeEtapaAntSis  Objeto de boolean que contiene los datos necesarios para buscar la información a incluir en la plantilla.
	 * @param as_idUsuario Variable de tipo String que contiene el usuario que realiza la acción.
	 * @param as_localIp Variable de tipo String que contiene la dirección ip local  desde donde seque realiza la acción.
	 * @param as_remoteIp Variable de tipo String que contiene la dirección ip remota desde donde seque realiza la acción.
	 * @param adm_manager Variable de tipo DAOManager contenedora de la conexion a la base de datos.
	 * @return  Arreglo de bytes que contiene la plantilla generada.
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see byte[]
	 */
	private byte[] generarSolicitudFirma(
	    DatosAntSistema adas_das, Map<String, Object> amso_mso, boolean ab_vieneDeEtapaAntSis, String as_idUsuario,
	    String as_localIp, String as_remoteIp, DAOManager adm_manager
	)
	    throws B2BException
	{
		byte[] lba_return;

		lba_return = null;

		try
		{
			String              ls_plantilla;
			Map<String, String> lmss_datos;

			ls_plantilla     = null;
			lmss_datos       = null;

			{
				Constantes lc_constante;

				lc_constante = DaoCreator.getConstantesDAO(adm_manager)
						                     .findByIdWithImage(ConstanteCommon.PLANTILLA_SOLICITUD_FIRMA_LIBRO_AS);

				if(lc_constante != null)
				{
					byte[] lba_plantilla;

					lba_plantilla = lc_constante.getImagenes();

					if(lba_plantilla == null)
						throw new B2BException(ErrorKeys.NO_IMAGEN_CONSTANTE);

					ls_plantilla = new String(lba_plantilla);
				}
				else
				{
					Object[] loa_args;

					loa_args     = new String[1];

					loa_args[0] = ConstanteCommon.PLANTILLA_SOLICITUD_FIRMA_LIBRO_AS;

					throw new B2BException(ErrorKeys.ERROR_SIN_CONSTANTE, loa_args);
				}
			}

			if(
			    StringUtils.isValidString(ls_plantilla) && (adas_das != null)
				    && CollectionUtils.isValidCollection(amso_mso)
			)
			{
				String ls_tag;
				String ls_idTurno;

				ls_tag         = null;
				ls_idTurno     = StringUtils.getString(amso_mso.get(IdentificadoresCommon.ID_TURNO));

				if(StringUtils.isValidString(ls_idTurno))
				{
					Turno lt_turno;

					lt_turno = DaoCreator.getTurnoDAO(adm_manager).findById(ls_idTurno);

					if(lt_turno != null)
					{
						String ls_idCirculo;

						ls_idCirculo = lt_turno.getIdCirculo();

						if(StringUtils.isValidString(ls_idCirculo))
						{
							TurnoHistoria lth_turnoHistoria;

							lth_turnoHistoria = DaoCreator.getTurnoHistoriaDAO(adm_manager).findByIdTurno(ls_idTurno);

							if(!ab_vieneDeEtapaAntSis)
							{
								ls_tag = "<TAG_OFICIO>";

								if(ls_plantilla.contains(ls_tag))
								{
									NumeracionOficiosCirculo lnoc_numeracionOficios;

									lnoc_numeracionOficios = findNumeracionOficiosCirculo(
										    lth_turnoHistoria, adm_manager, as_idUsuario, as_remoteIp
										);

									if(lnoc_numeracionOficios != null)
									{
										String ls_consecutivoOficio;

										ls_consecutivoOficio     = lnoc_numeracionOficios.getConsecutivoGenerado();
										ls_plantilla             = ls_plantilla.replace(ls_tag, ls_consecutivoOficio);
									}
								}

								ls_tag = "<TAG_REFERENCIA_SALIDA>";

								if(ls_plantilla.contains(ls_tag))
								{
									String ls_referenciaSalida;

									ls_referenciaSalida     = generarIdCorrespondencia(
										    lth_turnoHistoria, adm_manager, false
										);

									ls_plantilla = reemplazarTagEnPlantilla(ls_plantilla, ls_tag, ls_referenciaSalida);
								}
							}

							{
								ls_tag = "<TAG_MUN_OFI_ORIGEN>";

								if(ls_plantilla.contains(ls_tag))
								{
									Municipio lm_municipio;

									lm_municipio = DaoCreator.getMunicipioDAO(adm_manager).findByIdCirculo(
										    ls_idCirculo
										);

									if(lm_municipio != null)
									{
										String ls_munOficinaOrigen;

										ls_munOficinaOrigen = lm_municipio.getNombre();

										if(StringUtils.isValidString(ls_munOficinaOrigen))
										{
											ls_plantilla     = ls_plantilla.replace(ls_tag, ls_munOficinaOrigen);

											ls_tag = "<TAG MUNICIPIO>";

											if(ls_plantilla.contains(ls_tag))
												ls_plantilla = ls_plantilla.replace(ls_tag, ls_munOficinaOrigen);
										}
									}
								}
							}

							{
								ls_tag = "<TAG_FECHA_LARGA>";

								if(ls_plantilla.contains(ls_tag))
								{
									Date   ld_fecha;
									String ls_fechaActual;

									ld_fecha           = new Date();
									ls_fechaActual     = DateUtils.convertirLetrasLarga(ld_fecha);

									if(StringUtils.isValidString(ls_fechaActual))
										ls_plantilla = ls_plantilla.replace(ls_tag, ls_fechaActual.toUpperCase());
								}
							}

							{
								ls_tag = "<TAG_TURNO>";

								if(ls_plantilla.contains(ls_tag))
									ls_plantilla = ls_plantilla.replace(ls_tag, ls_idTurno);
							}

							{
								ls_tag = "<TAG_NIR>";

								if(ls_plantilla.contains(ls_tag))
								{
									String ls_nir;

									ls_nir = StringUtils.getString(amso_mso.get(IdentificadoresCommon.NIR));

									if(StringUtils.isValidString(ls_nir))
										ls_plantilla = ls_plantilla.replace(ls_tag, ls_nir);
								}
							}

							{
								ls_tag = "<TAG TIPO_PREDIO>";

								if(ls_plantilla.contains(ls_tag))
								{
									String ls_idTipoPredio;

									ls_idTipoPredio = adas_das.getIdTipoPredio();

									if(StringUtils.isValidString(ls_idTipoPredio))
									{
										PredioTipo lpd_predioTipo;

										lpd_predioTipo = DaoCreator.getPredioTipoDao(adm_manager)
												                       .findById(ls_idTipoPredio);

										if(lpd_predioTipo != null)
										{
											String ls_descripcion;

											ls_descripcion = lpd_predioTipo.getDescripcion();

											if(StringUtils.isValidString(ls_descripcion))
												ls_plantilla = ls_plantilla.replace(ls_tag, ls_descripcion);
										}
									}
								}
							}

							{
								ls_tag = "<TAG NOMBRE_PREDIO>";

								if(ls_plantilla.contains(ls_tag))
								{
									String ls_nombrePredio;

									ls_nombrePredio = adas_das.getNombrePredio();

									if(StringUtils.isValidString(ls_nombrePredio))
										ls_plantilla = ls_plantilla.replace(ls_tag, ls_nombrePredio);
								}
							}

							{
								ls_tag = "<TAG_DATOS_ANTIGUO_SISTEMA>";

								if(ls_plantilla.contains(ls_tag))
								{
									String ls_idDatosAntSistema;

									ls_idDatosAntSistema = adas_das.getIdDatosAntSistema();

									if(StringUtils.isValidString(ls_idDatosAntSistema))
									{
										String ls_infoDatosAnt;

										ls_infoDatosAnt = escribirDatosAntSistema(adm_manager, ls_idDatosAntSistema);

										if(StringUtils.isValidString(ls_infoDatosAnt))
											ls_plantilla = ls_plantilla.replace(ls_tag, ls_infoDatosAnt);
									}
								}
							}

							{
								ls_tag = "<TAG_PRINCIPAL_SECCIONAL>";

								if(ls_plantilla.contains(ls_tag))
								{
									CirculoRegistral lcr_circuloRegistral;

									lcr_circuloRegistral = DaoCreator.getCirculoRegistralDAO(adm_manager)
											                             .findById(ls_idCirculo);

									if(lcr_circuloRegistral != null)
									{
										String ls_tipoOficina;
										String ls_nombre;

										ls_tipoOficina     = lcr_circuloRegistral.getTipoOficina();
										ls_nombre          = lcr_circuloRegistral.getNombre();

										if(StringUtils.isValidString(ls_tipoOficina))
										{
											if(ls_tipoOficina.equalsIgnoreCase(EstadoCommon.P))
												ls_tipoOficina = IdentificadoresCommon.PRINCIPAL;
											else if(ls_tipoOficina.equalsIgnoreCase(EstadoCommon.S))
												ls_tipoOficina = IdentificadoresCommon.SECCIONAL;

											ls_plantilla = ls_plantilla.replace(ls_tag, ls_tipoOficina);
										}

										if(StringUtils.isValidString(ls_nombre))
										{
											ls_tag = "<TAG_NOMBRE_CIRCULO_REGISTRAL>";

											if(ls_plantilla.contains(ls_tag))
												ls_plantilla = ls_plantilla.replace(ls_tag, ls_nombre);
										}
									}
								}
							}

							{
								ls_tag = "<TAG_USUARIO>";

								if(ls_plantilla.contains(ls_tag) && StringUtils.isValidString(as_idUsuario))
									ls_plantilla = ls_plantilla.replace(ls_tag, as_idUsuario);
							}

							lmss_datos       = finalizarPlantilla(ls_plantilla, null, adm_manager);
							ls_plantilla     = lmss_datos.get(IdentificadoresCommon.PLANTILLA);

							lba_return = new PDFGenerator().convertirRTFToPDF(ls_plantilla.getBytes(), adm_manager);
						}
					}
				}
			}
			else
				throw new B2BException(ErrorKeys.ERROR_PLANTILLA);

			if(lba_return == null)
				throw new B2BException(ErrorKeys.ERROR_GENERANDO_PLANTILLA);
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("generarSolicitudFirma", lb2be_e);

			throw lb2be_e;
		}

		return lba_return;
	}

	/**
	 *  Método encargado de generar archivo en formato pdf  utilizando  la plantilla  TIPO_DOCUMENTAL_INFORME_BUSQUEDA.
	 *
	 * @param ads_ds Objeto que contiene los datos necesarios para realizar la inserción de los documentos que se insertaran en el proceso de reportes de resultados
	 * @param amsb_arg  Colección que contiene los archivos a insertar en el proceso de reportes de resultados.
	 * @param adm_manager Objeto de tipo DAOManager utilizado para crear la conexión hacia la base de datos
	 * @param as_idUsuario Variable de tipo String que contiene el usuario que realiza la acción.
	 * @param as_localIp Variable de tipo String que contiene la dirección ip local desde donde seque realiza la acción.
	 * @param as_remoteIp Variable de tipo String que contiene la dirección ip remota desde donde seque realiza la acción.
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	private void guardarDocumentosPdf(
	    DocumentosSalida ads_ds, Map<String, byte[]> amsb_arg, DAOManager adm_manager, String as_idUsuario,
	    String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		try
		{
			if(ads_ds != null)
			{
				String         ls_idConstante;
				Constantes     lc_constante;
				TipoDocumental ltd_tipoDoc;

				ls_idConstante     = ConstanteCommon.TIPO_DOCUMENTAL_INFORME_BUSQUEDA;
				lc_constante       = DaoCreator.getConstantesDAO(adm_manager).findById(ls_idConstante);
				ltd_tipoDoc        = new TipoDocumental();

				if(lc_constante == null)
				{
					Object[] loa_messageArgs;

					loa_messageArgs        = new String[1];
					loa_messageArgs[0]     = ls_idConstante;

					throw new B2BException(ErrorKeys.ERROR_SIN_CONSTANTE, loa_messageArgs);
				}

				ltd_tipoDoc.setIdTipoDocumental(lc_constante.getCaracter());

				ltd_tipoDoc = DaoCreator.getTipoDocumentalDAO(adm_manager).findById(ltd_tipoDoc);

				if(CollectionUtils.isValidCollection(amsb_arg) && (ltd_tipoDoc != null))
				{
					String              ls_idDatosAntSistema;
					ImagenesDAO         lid_imagenes;
					DocumentosSalidaDAO ldsd_documentos;
					String              ls_idSolicitud;
					String              ls_idTurno;
					TurnoHistoria       lth_turnoDatos;

					ls_idDatosAntSistema     = ads_ds.getIdDatosAntSistema();
					lid_imagenes             = DaoCreator.getImagenesDAO(adm_manager);
					ldsd_documentos          = DaoCreator.getDocumentosSalidaDAO(adm_manager);
					lth_turnoDatos           = null;

					{
						lth_turnoDatos = new TurnoHistoria();

						lth_turnoDatos.setIdTurnoHistoria(NumericUtils.getLongWrapper(ads_ds.getIdTurnoHistoria()));

						lth_turnoDatos = DaoCreator.getTurnoHistoriaDAO(adm_manager).findById(lth_turnoDatos);

						if(lth_turnoDatos == null)
							throw new B2BException(ErrorKeys.NO_SE_ENCONTRO_ID_TURNO_HISTORIA);

						ls_idSolicitud     = lth_turnoDatos.getIdSolicitud();
						ls_idTurno         = lth_turnoDatos.getIdTurno();
					}

					ads_ds.setTipoArchivo(ExtensionCommon.PDF_MAYUS);
					ads_ds.setIdSolicitud(ls_idSolicitud);
					ads_ds.setIdTurno(ls_idTurno);
					ads_ds.setIdTipoDocumental(ltd_tipoDoc.getIdTipoDocumental());
					ads_ds.setIdDatosAntSistema(ls_idDatosAntSistema);
					ads_ds.setTipo(ltd_tipoDoc.getNombre());
					ads_ds.setEstado(EstadoCommon.E);
					ads_ds.setIpCreacion(as_remoteIp);

					for(Map.Entry<String, byte[]> lmlb_iterador : amsb_arg.entrySet())
					{
						if(lmlb_iterador != null)
						{
							byte[] lba_imagen;

							lba_imagen = lmlb_iterador.getValue();

							if(lba_imagen != null)
							{
								Imagenes li_datosImagen;
								long     ll_secuenciaImagenes;

								li_datosImagen = new Imagenes();

								li_datosImagen.setImagenBlob(lba_imagen);
								li_datosImagen.setTipoArchivo(ExtensionCommon.PDF_MAYUS);
								li_datosImagen.setIdUsuarioCreacion(as_idUsuario);
								li_datosImagen.setIpCreacion(as_remoteIp);

								ll_secuenciaImagenes = lid_imagenes.insertOrUpdate(li_datosImagen, true);

								if(ll_secuenciaImagenes <= 0L)
									throw new B2BException(ErrorKeys.SIN_SECUENCIA_IMAGENES);

								ads_ds.setIdImagen(NumericUtils.getLongWrapper(ll_secuenciaImagenes));

								ldsd_documentos.insertOrUpdate(ads_ds, true);
							}
							else
								throw new B2BException(ErrorKeys.ERROR_CARGUE_ARCHIVOS_PDF);
						}
						else
							throw new B2BException(ErrorKeys.ERROR_CARGUE_ARCHIVOS_PDF);
					}
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("guardarDocumentosPdf", lb2be_e);

			throw lb2be_e;
		}
	}

	/**
	 *  Método  encargado de ejecutar todo lo referente al proceso de asociar matricula de antiguo sistema.
	 *
	 * @param acsm_csm Colección de datos de tipo SolicitudMatricula que contiene los parámetros  necesarios para insertar o actualizar en la tabla SDB_ACC_SOLICITUD_MATRICULA.
	 * @param acsma_sma Colección de datos de tipo SolicitudMatriculaActo que contiene los parámetros  necesarios para insertar o actualizar en la tabla SDB_ACC_SOLICITUD_MATRICULA_ACTO.
	 * @param adm_manager Objeto de tipo DAOManager utilizado para realizar la conexion a la base de datos.
	 * @param as_userId  Variable de tipo String que contiene el usuario que realiza la acción.
	 * @param as_ipRemota Variable de tipo String que contiene la dirección ip local  desde donde seque realiza la acción.
	 * @param as_idTurnoHistoria correspondiente al valor del tipo de objeto String
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	private void salvarAsociarMatricula(
	    Collection<SolicitudMatricula> acsm_csm, Collection<SolicitudMatriculaActo> acsma_sma, DAOManager adm_manager,
	    String as_userId, String as_ipRemota, String as_idTurnoHistoria
	)
	    throws B2BException
	{
		Object[] loa_messageArgs = new String[2];

		try
		{
			if(CollectionUtils.isValidCollection(acsm_csm))
			{
				SolicitudMatriculaDAO     lsm_DAO;
				SolicitudMatriculaActoDAO lsma_DAO;
				TurnoHistoria             ath_turnoHistoria;
				TurnoHistoriaDAO          lth_DAO;
				DatosAntSistemaDAO        ldas_DAO;

				lsm_DAO               = DaoCreator.getSolicitudMatriculaDAO(adm_manager);
				lsma_DAO              = DaoCreator.getSolicitudMatriculaActoDAO(adm_manager);
				lth_DAO               = DaoCreator.getTurnoHistoriaDAO(adm_manager);
				ldas_DAO              = DaoCreator.getDatosAntSistemaDAO(adm_manager);
				ath_turnoHistoria     = lth_DAO.findById(NumericUtils.getLongWrapper(as_idTurnoHistoria));

				for(SolicitudMatricula lsm_actual : acsm_csm)
				{
					if(lsm_actual != null)
					{
						long   ll_matricula;
						String ls_circuloRegistral;
						String ls_idSolicitud;
						String ls_estado;
						String ls_idDatosAntSistema;
						Long   ll_cantidadCertificados;

						ll_matricula                = lsm_actual.getIdMatricula();
						ls_circuloRegistral         = lsm_actual.getIdCirculo();
						ls_idSolicitud              = lsm_actual.getIdSolicitud();
						ls_estado                   = lsm_actual.getEstado();
						ls_idDatosAntSistema        = lsm_actual.getIdDatosAntSistema();
						ll_cantidadCertificados     = NumericUtils.getLongWrapper(0L);

						loa_messageArgs[0]     = ls_circuloRegistral;
						loa_messageArgs[1]     = StringUtils.getString(ll_matricula);

						{
							DatosAntSistema ldas_datos;

							ldas_datos = ldas_DAO.findById(ls_idDatosAntSistema);

							if(ldas_datos != null)
							{
								String ls_accion;

								ls_accion = ldas_datos.getAccion();

								{
									Long ll_cantidadCertTmp;

									ll_cantidadCertTmp = ldas_datos.getCantidadCertificados();

									if(NumericUtils.isValidLong(ll_cantidadCertTmp))
										ll_cantidadCertificados = ll_cantidadCertTmp;
								}

								if(StringUtils.isValidString(ls_accion))
								{
									if(ls_accion.equalsIgnoreCase(AccionAntSistemaCommon.INFORME_RESULTADOS))
										ldas_datos.setAccion(AccionAntSistemaCommon.ASOCIAR_MATRICULA_E_INFORME);
								}
								else
									ldas_datos.setAccion(AccionAntSistemaCommon.ASOCIAR_MATRICULAS);

								ldas_datos.setIdMatricula(NumericUtils.getLongWrapper(ll_matricula));
								ldas_datos.setIpModificacion(as_ipRemota);
								ldas_datos.setIdUsuarioModificacion(as_userId);

								ldas_DAO.insertOrUpdate(ldas_datos, false);
							}
						}

						if(
						    StringUtils.isValidString(ls_circuloRegistral)
							    && NumericUtils.isValidLong(NumericUtils.getLongWrapper(ll_matricula), 1)
							    && NumericUtils.isValidLong(NumericUtils.getLongWrapper(ls_idSolicitud), 1)
						)
						{
							{
								SolicitudMatricula lsm_matriculaInsert;

								lsm_matriculaInsert = new SolicitudMatricula();

								lsm_matriculaInsert.setEstado(ls_estado);
								lsm_matriculaInsert.setIdSolicitud(lsm_actual.getIdSolicitud());
								lsm_matriculaInsert.setIdMatricula(ll_matricula);
								lsm_matriculaInsert.setIdCirculo(ls_circuloRegistral);

								cargarTurnoCertificado(lsm_matriculaInsert, ls_idDatosAntSistema, adm_manager);

								lsm_matriculaInsert.setCantidadCertificados(
								    NumericUtils.getBigDecimal(NumericUtils.getLong(ll_cantidadCertificados))
								);
								lsm_matriculaInsert.setEstado(lsm_actual.getEstado());
								lsm_matriculaInsert.setIdDatosAntSistema(ls_idDatosAntSistema);

								{
									SolicitudMatricula lsm_matriculaExist;

									lsm_matriculaExist = lsm_DAO.findById(lsm_matriculaInsert);

									if(lsm_matriculaExist != null)
									{
										lsm_matriculaInsert.setIdUsuarioModificacion(as_userId);
										lsm_matriculaInsert.setIpModificacion(as_ipRemota);

										lsm_DAO.insertOrUpdate(lsm_matriculaInsert, false);
									}
									else
									{
										lsm_matriculaInsert.setIpCreacion(as_ipRemota);
										lsm_matriculaInsert.setIdUsuarioCreacion(as_userId);

										lsm_DAO.insertOrUpdate(lsm_matriculaInsert, true);
									}
								}
							}

							{
								PredioRegistro    lpr_pr;
								PredioRegistroDAO lpr_DAO;

								lpr_pr      = new PredioRegistro();
								lpr_DAO     = DaoCreator.getPredioRegistroDAO(adm_manager);

								lpr_pr.setIdCirculo(ls_circuloRegistral);
								lpr_pr.setIdMatricula(ll_matricula);

								lpr_pr = lpr_DAO.findById(lpr_pr);

								if(lpr_pr != null)
								{
									if(!StringUtils.isValidString(lpr_pr.getTurnoBloqueo()))
									{
										if(ls_estado.equalsIgnoreCase(EstadoCommon.A) && (ath_turnoHistoria != null))
											lpr_pr.setTurnoBloqueo(ath_turnoHistoria.getIdTurno());
										else
											lpr_pr.setTurnoBloqueo(null);
									}

									lpr_pr.setIdUsuarioModificacion(as_userId);
									lpr_pr.setIpModificacion(as_ipRemota);

									lpr_DAO.insertOrUpdate(lpr_pr, false);
								}
							}
						}
						else
							throw new B2BException(ErrorKeys.ERROR_MATRICULA_NO_VALIDA, loa_messageArgs);
					}
				}

				{
					Collection<SolicitudMatriculaActo> lcsma_matriculasActos;

					lcsma_matriculasActos = new ArrayList<SolicitudMatriculaActo>(acsma_sma);

					if(CollectionUtils.isValidCollection(lcsma_matriculasActos))
					{
						for(SolicitudMatriculaActo lsma_actual : lcsma_matriculasActos)
						{
							if(lsma_actual != null)
							{
								SolicitudMatriculaActo lsma_matriculaActoExists;

								lsma_matriculaActoExists = lsma_DAO.findById(lsma_actual);

								if(lsma_matriculaActoExists != null)
								{
									lsma_actual.setIdUsuarioModificacion(as_userId);
									lsma_actual.setIpModificacion(as_ipRemota);

									lsma_DAO.insertOrUpdate(lsma_actual, false);
								}
								else
								{
									lsma_actual.setIdUsuarioModificacion(as_userId);
									lsma_actual.setIpCreacion(as_ipRemota);

									lsma_DAO.insertOrUpdate(lsma_actual, true);
								}
							}
						}
					}
				}
			}
			else
				throw new B2BException(ErrorKeys.ERROR_SIN_PREDIOS_CON_ACTOS);
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("salvarAsociarMatricula", lb2be_e);

			throw lb2be_e;
		}
	}

	/**
	 * Método encargado de guardar la información del modulo de Apertura de datos básicos.
	 *
	 * @param adb_db Objeto que contiene la información a guardar;
	 * @param as_userId Variable de tipo String que contiene el id del usuario.
	 * @param as_remoteIp Variable de tipo String que contiene la ip del usuario
	 * @param adm_manager DAOManager que controla las transacciones.
	 * @return Objeto que contiene la información guardada.
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see DatosBasicos
	 */
	private synchronized DatosBasicos salvarDatosBasicosApertura(
	    DatosBasicos adb_db, String as_userId, String as_remoteIp, DAOManager adm_manager
	)
	    throws B2BException
	{
		try
		{
			if(adb_db != null)
			{
				Apertura la_apertura;

				la_apertura = adb_db.getApertura();

				if(la_apertura != null)
				{
					Documento ld_documentoApertura;

					ld_documentoApertura = la_apertura.getDocumento();

					if(ld_documentoApertura != null)
					{
						Date   ld_fechaApertura;
						String ls_radicacion;
						String ls_mensaje;

						ld_fechaApertura     = la_apertura.getFechaApertura();
						ls_radicacion        = la_apertura.getRadicacion();
						ls_mensaje           = null;

						{
							if(ld_fechaApertura == null)
								throw new B2BException(ErrorKeys.ERROR_FECHA_APERTURA);

							if(ld_fechaApertura.after(new Date()))
							{
								Object[] aoa_messageArgs = new String[1];
								aoa_messageArgs[0] = " de apertura ";
								throw new B2BException(ErrorKeys.FECHA_SUPERIOR_ACTUAL, aoa_messageArgs);
							}
						}

						{
							if(!StringUtils.isValidString(ls_radicacion))
								throw new B2BException(ErrorKeys.NO_SE_ENCONTRO_RADICADO_APERTURA);
						}

						{
							Date   ld_fechaDocumento;
							String ls_idPais;
							String ls_idDepartamento;
							String ls_idMunicipio;
							String ls_idOficinaOrigen;

							ld_fechaDocumento      = ld_documentoApertura.getFechaDocumento();
							ls_idPais              = la_apertura.getIdPais();
							ls_idDepartamento      = la_apertura.getIdDepartamento();
							ls_idMunicipio         = la_apertura.getIdMunicipio();
							ls_idOficinaOrigen     = la_apertura.getIdOficinaOrigen();

							if(!StringUtils.isValidString(ls_idPais))
								throw new B2BException(ErrorKeys.DEBE_SELECCIONAR_PAIS);

							if(ld_documentoApertura != null)
							{
								if(ld_documentoApertura.getIdTipoDocumento() == null)
									throw new B2BException(ErrorKeys.DEBE_SELECCIONAR_TIPO);

								if(!StringUtils.isValidString(ld_documentoApertura.getNumero()))
									throw new B2BException(ErrorKeys.DOCUMENTO_INVALIDO);

								if(ld_fechaDocumento == null)
								{
									Object[] aoa_messageArgs = new String[1];
									aoa_messageArgs[0] = " Documento ";
									throw new B2BException(ErrorKeys.ERROR_CAMPO_FECHA_OBLIGATORIO, aoa_messageArgs);
								}
								else
								{
									Date ld_d;
									int  li_meses;

									ld_d         = new Date();
									li_meses     = -2;

									if(!ls_idPais.equalsIgnoreCase(IdentificadoresCommon.INDICATIVO_PAIS_DEFAULT))
										li_meses = -3;

									Calendar lc_calendar = Calendar.getInstance();
									lc_calendar.setTime(ld_d);
									lc_calendar.add(Calendar.MONTH, li_meses);
									ld_d = lc_calendar.getTime();

									if(ld_fechaDocumento.before(ld_d))
										ls_mensaje = "El documento que se pretende registrar  ha superado  el  termino de dos meses para su  radicación y/o calificación.";
								}

								if(ld_fechaDocumento.after(new Date()))
								{
									Object[] aoa_messageArgs = new String[1];
									aoa_messageArgs[0] = " del documento ";
									throw new B2BException(ErrorKeys.FECHA_SUPERIOR_ACTUAL, aoa_messageArgs);
								}
							}

							if(!StringUtils.isValidString(ls_idDepartamento))
							{
								Object[] aoa_messageArgs = new String[1];
								aoa_messageArgs[0] = " un Departamento ";
								throw new B2BException(ErrorKeys.ERROR_SIN_SELECCION_COMBOS, aoa_messageArgs);
							}

							if(!StringUtils.isValidString(ls_idMunicipio))
							{
								Object[] aoa_messageArgs = new String[1];
								aoa_messageArgs[0] = " un municipio ";
								throw new B2BException(ErrorKeys.ERROR_SIN_SELECCION_COMBOS, aoa_messageArgs);
							}

							if(!StringUtils.isValidString(ls_idOficinaOrigen))
							{
								Object[] aoa_messageArgs = new String[1];
								aoa_messageArgs[0] = " la oficina origen ";
								throw new B2BException(ErrorKeys.ERROR_SIN_SELECCION_COMBOS, aoa_messageArgs);
							}

							ld_documentoApertura.setFechaDocumento(ld_fechaDocumento);
							ld_documentoApertura.setIdPais(ls_idPais);
							ld_documentoApertura.setIdDepartamento(ls_idDepartamento);
							ld_documentoApertura.setIdMunicipio(ls_idMunicipio);
							ld_documentoApertura.setIdOficinaOrigen(ls_idOficinaOrigen);
							ld_documentoApertura.setVersion(
							    obtenerVersionMaximaOficinaOrigen(ls_idOficinaOrigen, adm_manager)
							);

							ld_documentoApertura.setVersionDocumento(NumericUtils.getLongWrapper(1L));
							ld_documentoApertura.setIdUsuarioCreacion(as_userId);
						}

						{
							DocumentoDAO ldd_DAO;
							Documento    ld_documento;

							ldd_DAO          = DaoCreator.getDocumentoDAO(adm_manager);
							ld_documento     = ldd_DAO.consultaDocumento(ld_documentoApertura);

							if(ld_documento == null)
							{
								int li_secuencia;
								li_secuencia = DaoCreator.getUtilDAO(adm_manager)
										                     .findSecuence(ConsultasUtilidades.CS_BGN_DOCUMENTO_SEC);

								if(li_secuencia > 0)
								{
									Integer li_tmp;
									li_tmp = NumericUtils.getInteger(li_secuencia);

									if(li_tmp != null)
									{
										ld_documento = new Documento();

										ld_documento.setIdDocumento(li_tmp.toString());
										ld_documento.setVersionDocumento(NumericUtils.getLongWrapper(1L));
										ld_documento.setNumero(ld_documentoApertura.getNumero());
										ld_documento.setIdTipoDocumento(ld_documentoApertura.getIdTipoDocumento());
										ld_documento.setFechaDocumento(ld_documentoApertura.getFechaDocumento());
										ld_documento.setIdTipoOficina(ld_documentoApertura.getIdTipoOficina());

										{
											String ls_idOficinaOrigen;

											ls_idOficinaOrigen = ld_documentoApertura.getIdOficinaOrigen();

											ld_documento.setIdOficinaOrigen(ls_idOficinaOrigen);
											ld_documento.setVersion(
											    obtenerVersionMaximaOficinaOrigen(ls_idOficinaOrigen, adm_manager)
											);
										}

										ld_documento.setIdUsuarioCreacion(as_userId);
										ld_documento.setIpCreacion(as_remoteIp);

										ldd_DAO.insertOrUpdate(ld_documento, true);
									}
								}
							}
							else
							{
								String ls_idOficinaOrigen;

								ls_idOficinaOrigen = ld_documentoApertura.getIdOficinaOrigen();

								ld_documento.setIdOficinaOrigen(ls_idOficinaOrigen);
								ld_documento.setVersion(
								    obtenerVersionMaximaOficinaOrigen(ls_idOficinaOrigen, adm_manager)
								);
								ld_documento.setIdTipoOficina(ld_documentoApertura.getIdTipoOficina());
								ld_documento.setIdUsuarioModificacion(as_userId);
								ld_documento.setIpModificacion(as_remoteIp);

								ldd_DAO.insertOrUpdate(ld_documento, false);
							}

							ld_documentoApertura = ldd_DAO.consultaDocumento(ld_documentoApertura);

							if(ld_documentoApertura != null)
							{
								AccPredioRegistro    lapr_actual;
								AccPredioRegistroDAO lapr_DAO;

								lapr_actual     = adb_db.getAccPredioRegistro();
								lapr_DAO        = DaoCreator.getAccPredioRegistroDAO(adm_manager);

								lapr_actual = lapr_DAO.findById(lapr_actual);

								if(lapr_actual != null)
								{
									lapr_actual.setIdDocumento(ld_documentoApertura.getIdDocumento());

									{
										long ll_versionDocumento;

										ll_versionDocumento = NumericUtils.getLong(
											    ld_documentoApertura.getVersionDocumento()
											);

										lapr_actual.setVersionDocumento(
										    NumericUtils.getBigDecimal(ll_versionDocumento)
										);
									}

									lapr_actual.setFechaApertura(ld_fechaApertura);
									lapr_actual.setRadicacion(ls_radicacion);
									lapr_actual.setIdUsuarioModificacion(as_userId);

									lapr_DAO.updateById(lapr_actual);

									lapr_actual = lapr_DAO.findById(lapr_actual);

									if(lapr_actual != null)
										adb_db.setAccPredioRegistro(lapr_actual);
								}
							}
						}

						la_apertura.setDocumento(ld_documentoApertura);

						if(StringUtils.isValidString(ls_mensaje))
							adb_db.setMensaje(ls_mensaje);

						adb_db.setApertura(la_apertura);
					}
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("salvarDatosBasicosApertura", lb2be_e);
			throw lb2be_e;
		}

		return adb_db;
	}

	/**
	 * Método encargado de guardar la información del modulo de Información catastral de datos básicos.
	 *
	 * @param adb_db Objeto que contiene la información a guardar;
	 * @param as_userId Variable de tipo String que contiene el id del usuario.
	 * @param as_remoteIp Variable de tipo String que contiene la ip del usuario
	 * @param adm_manager DAOManager que controla las transacciones.
	 * @return Objeto que contiene la información guardada.
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see DatosBasicos
	 */
	private synchronized DatosBasicos salvarDatosBasicosCatastral(
	    DatosBasicos adb_db, String as_userId, String as_remoteIp, DAOManager adm_manager
	)
	    throws B2BException
	{
		try
		{
			if(adb_db != null)
			{
				AccPredioRegistro    lapr_actual;
				AccPredioRegistroDAO lapr_DAO;
				String               ls_codigoCatastral;
				String               ls_codigoCatastralAnterior;

				lapr_actual                    = adb_db.getAccPredioRegistro();
				lapr_DAO                       = DaoCreator.getAccPredioRegistroDAO(adm_manager);
				ls_codigoCatastral             = adb_db.getCodigoCatastral();
				ls_codigoCatastralAnterior     = adb_db.getCodigoCatastralAnterior();

				lapr_actual = lapr_DAO.findById(lapr_actual);

				if(lapr_actual != null)
				{
					lapr_actual.setNumeroPredial(ls_codigoCatastral);
					lapr_actual.setNumeroPredialAnt(ls_codigoCatastralAnterior);
					lapr_actual.setIdUsuarioModificacion(as_userId);

					lapr_DAO.updateById(lapr_actual);

					lapr_actual = lapr_DAO.findById(lapr_actual);

					if(lapr_actual != null)
						adb_db.setAccPredioRegistro(lapr_actual);
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("salvarDatosBasicosCatastral", lb2be_e);
			throw lb2be_e;
		}

		return adb_db;
	}

	/**
	 * Método encargado de guardar la información del modulo de Matrículas con base de datos básicos.
	 *
	 * @param adb_db Objeto que contiene la información a guardar;
	 * @param as_userId Variable de tipo String que contiene el id del usuario.
	 * @param as_remoteIp Variable de tipo String que contiene la ip del usuario
	 * @param adm_manager DAOManager que controla las transacciones.
	 * @return Objeto que contiene la información guardada.
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see DatosBasicos
	 */
	private synchronized DatosBasicos salvarDatosBasicosMatriculas(
	    DatosBasicos adb_db, String as_userId, String as_remoteIp, DAOManager adm_manager
	)
	    throws B2BException
	{
		try
		{
			if(adb_db != null)
			{
				MatriculaBase lmb_matriculaBase;

				lmb_matriculaBase = adb_db.getMatriculaBase();

				if(lmb_matriculaBase != null)
				{
					Collection<DireccionPredio> lcdp_cdp;
					AccPredioSegregadoDAO       laps_DAO;

					laps_DAO     = DaoCreator.getAccPredioSegregadoDAO(adm_manager);
					lcdp_cdp     = new ArrayList<DireccionPredio>();

					lcdp_cdp = lmb_matriculaBase.getDireccionPredio();

					if(CollectionUtils.isValidCollection(lcdp_cdp))
					{
						AccPredioRegistro lapr_actual;

						lapr_actual = adb_db.getAccPredioRegistro();

						if(lapr_actual != null)
						{
							for(DireccionPredio ldp_actual : lcdp_cdp)
							{
								if(ldp_actual != null)
								{
									PredioSegregado lps_ps;

									lps_ps = new PredioSegregado();

									lps_ps.setIdCirculo(ldp_actual.getIdCirculo());
									lps_ps.setIdMatricula(NumericUtils.getLong(ldp_actual.getIdMatricula()));
									lps_ps.setIdTurno(lapr_actual.getIdTurno());

									lps_ps = laps_DAO.findByCirculoMatriculaTurno(lps_ps);

									if(lps_ps == null)
									{
										int li_secuencia;

										lps_ps           = new PredioSegregado();
										li_secuencia     = laps_DAO.findSecuencia();

										if(li_secuencia > 0)
										{
											lps_ps.setIdPredioSegregado(StringUtils.getString(li_secuencia));
											lps_ps.setIdTurnoHistoria(
											    NumericUtils.getLong(lapr_actual.getIdTurnoHistoria())
											);
											lps_ps.setIdCirculo(ldp_actual.getIdCirculo());
											lps_ps.setIdMatricula(NumericUtils.getLong(ldp_actual.getIdMatricula()));
											lps_ps.setIdCirculo1(lapr_actual.getIdCirculo());
											lps_ps.setIdMatricula1(NumericUtils.getLong(lapr_actual.getIdMatricula()));
											lps_ps.setIdTurno(lapr_actual.getIdTurno());
											lps_ps.setIdUsuarioCreacion(as_userId);
											lps_ps.setIpCreacion(as_remoteIp);

											laps_DAO.insert(lps_ps);
										}
										else
											throw new B2BException(ErrorKeys.ACC_PREDIO_SEGREGADO_SECUENCE);
									}
								}
							}
						}
						else
							throw new B2BException(ErrorKeys.ACC_PREDIO_REGISTRO_INSERT);
					}
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("salvarDatosBasicosMatriculas", lb2be_e);
			throw lb2be_e;
		}

		return adb_db;
	}

	/**
	 * Método encargado de guardar la información del modulo de Ubicacion de datos básicos.
	 *
	 * @param adb_db Objeto que contiene la información a guardar;
	 * @param as_userId Variable de tipo String que contiene el id del usuario.
	 * @param as_remoteIp Variable de tipo String que contiene la ip del usuario
	 * @param adm_manager DAOManager que controla las transacciones.
	 * @return Objeto que contiene la información guardada.
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see DatosBasicos
	 */
	private synchronized DatosBasicos salvarDatosBasicosUbicacion(
	    DatosBasicos adb_db, String as_userId, String as_remoteIp, DAOManager adm_manager
	)
	    throws B2BException
	{
		try
		{
			if(adb_db != null)
			{
				AccPredioRegistroDAO   lapr_DAO;
				UbicacionZonaRegistral luzr_ubicacion;

				lapr_DAO           = DaoCreator.getAccPredioRegistroDAO(adm_manager);
				luzr_ubicacion     = adb_db.getUbicacion();

				if(luzr_ubicacion != null)
				{
					CirculoRegistral ls_circuloUbicacion;

					ls_circuloUbicacion = DaoCreator.getCirculoRegistralDAO(adm_manager)
							                            .findById(luzr_ubicacion.getCirculoRegistral());

					if(ls_circuloUbicacion != null)
					{
						String           ls_idCirculo;
						String           ls_idPais;
						String           ls_idDepartamento;
						String           ls_idMunicipio;
						String           ls_idVereda;
						String           ls_nupre;
						ZonaRegistralDAO lzrDAO_DAO;

						ls_idCirculo          = ls_circuloUbicacion.getIdCirculo();
						ls_idPais             = luzr_ubicacion.getIdPais();
						ls_idDepartamento     = luzr_ubicacion.getDepartamento().getIdDepartamento();
						ls_idMunicipio        = luzr_ubicacion.getMunicipio().getIdMunicipio();
						ls_idVereda           = luzr_ubicacion.getVereda().getIdVereda();
						ls_nupre              = luzr_ubicacion.getNupre();
						lzrDAO_DAO            = DaoCreator.getZonaRegistralDAO(adm_manager);

						if(!StringUtils.isValidString(ls_idCirculo))
							throw new B2BException(ErrorKeys.CIRCULO_REGISTRAL_UBICACION_SELECCIONADO_INVALIDO);

						if(!StringUtils.isValidString(ls_idPais))
							throw new B2BException(ErrorKeys.DEBE_SELECCIONAR_PAIS);

						if(!StringUtils.isValidString(ls_idDepartamento))
							throw new B2BException(ErrorKeys.DEBE_SELECCIONAR_DEP_UBICACION);

						if(!StringUtils.isValidString(ls_idMunicipio))
							throw new B2BException(ErrorKeys.DEBE_SELECCIONAR_MUN_UBICACION);

						if(!StringUtils.isValidString(ls_idVereda))
							throw new B2BException(ErrorKeys.DEBE_SELECCIONAR_VER_UBICACION);

						{
							ZonaRegistral lzr_zonaRegistral;

							lzr_zonaRegistral = new ZonaRegistral();

							lzr_zonaRegistral.setIdCirculo(ls_idCirculo);
							lzr_zonaRegistral.setIdPais(ls_idPais);
							lzr_zonaRegistral.setIdDepartamento(ls_idDepartamento);
							lzr_zonaRegistral.setIdMunicipio(ls_idMunicipio);
							lzr_zonaRegistral.setIdVereda(ls_idVereda);

							lzr_zonaRegistral = lzrDAO_DAO.findByKeys(lzr_zonaRegistral);

							if(lzr_zonaRegistral == null)
							{
								lzr_zonaRegistral = new ZonaRegistral();

								lzr_zonaRegistral.setIdCirculo(ls_idCirculo);
								lzr_zonaRegistral.setIdPais(ls_idPais);
								lzr_zonaRegistral.setIdDepartamento(ls_idDepartamento);
								lzr_zonaRegistral.setIdMunicipio(ls_idMunicipio);
								lzr_zonaRegistral.setIdVereda(ls_idVereda);
								lzr_zonaRegistral.setIdUsuarioCreacion(as_userId);
								lzr_zonaRegistral.setIpCreacion(as_remoteIp);
								lzr_zonaRegistral.setActivo(EstadoCommon.S);

								lzr_zonaRegistral = lzrDAO_DAO.insertOrUpdate(lzr_zonaRegistral, true);
							}

							luzr_ubicacion.setZonaRegistral(lzr_zonaRegistral);
						}
						// CREACION DE ACC_PREDIO_REGISTRO
						{
							AccPredioRegistro  lapr_new;
							DatosAntSistema    ldas_datosAntSistema;
							PredioRegistro     lpr_predioRegistro;
							String             ls_nombrePredio;
							String             ls_idDatosAnt;
							DatosAntSistemaDAO ldasd_datosAntDAO;

							ldas_datosAntSistema     = null;
							lapr_new                 = adb_db.getAccPredioRegistro();
							ls_idDatosAnt            = adb_db.getIdDatosAntSistema();
							lpr_predioRegistro       = adb_db.getPredioRegistro();
							ldasd_datosAntDAO        = DaoCreator.getDatosAntSistemaDAO(adm_manager);

							if(lapr_new == null)
								throw new B2BException(ErrorKeys.ERROR_INTERNO_SISTEMA);

							ls_nombrePredio     = lapr_new.getNombrePredio();

							ldas_datosAntSistema = ldasd_datosAntDAO.findById(ls_idDatosAnt);

							if(ldas_datosAntSistema != null)
							{
								ldas_datosAntSistema.setIdDatosAntSistema(ls_idDatosAnt);
								ldas_datosAntSistema.setNombrePredio(ls_nombrePredio);
								ldas_datosAntSistema.setIpModificacion(as_remoteIp);
								ldas_datosAntSistema.setIdUsuarioModificacion(as_userId);

								ldasd_datosAntDAO.updateNombrePredioAntSistema(ldas_datosAntSistema);
							}

							if(!StringUtils.isValidString(lapr_new.getIdPredioRegistro()))
							{
								ConstantesDAO lcd_DAO;
								Constantes    lc_constantes;
								int           li_anio;
								Calendar      lc_calendar;
								String        ls_constante;
								BigInteger    lbi_entero;

								lc_calendar       = Calendar.getInstance();
								lc_constantes     = new Constantes();
								lcd_DAO           = DaoCreator.getConstantesDAO(adm_manager);
								lbi_entero        = null;

								li_anio          = lc_calendar.get(Calendar.YEAR);
								ls_constante     = "CONSECUTIVO_" + ls_idCirculo
									+ IdentificadoresCommon.SIMBOLO_GUION_BAJO + li_anio;

								lc_constantes.setIdConstante(ls_constante);

								lc_constantes = lcd_DAO.findById(lc_constantes);

								if(lc_constantes != null)
								{
									BigInteger lbi_constanteMatricula;

									lbi_constanteMatricula = lc_constantes.getEntero();

									if(NumericUtils.isValidBigInteger(lbi_constanteMatricula))
									{
										try
										{
											lbi_entero = NumericUtils.getBigInteger(
												    StringUtils.getString((lbi_constanteMatricula.intValue() + 1))
												);

											lc_constantes.setEntero(lbi_entero);
											lc_constantes.setIdUsuarioModificacion(as_userId);
											lc_constantes.setIpModificacion(as_remoteIp);

											lcd_DAO.insertOrUpdate(lc_constantes, false);
										}
										catch(Exception e)
										{
											lbi_entero = null;
										}
									}
								}
								else
								{
									lc_constantes     = new Constantes();

									lbi_entero = new BigInteger(
										    StringUtils.getString(ConstanteCommon.MATRICULA_DUMMY_DEFAULT)
										);

									lc_constantes.setIdConstante(
									    "CONSECUTIVO_" + ls_idCirculo + IdentificadoresCommon.SIMBOLO_GUION_BAJO
									    + li_anio
									);
									lc_constantes.setEntero(lbi_entero);
									lc_constantes.setIdUsuarioCreacion(as_userId);
									lc_constantes.setIpCreacion(as_remoteIp);

									lcd_DAO.insertOrUpdate(lc_constantes, true);
								}

								if(NumericUtils.isValidBigInteger(lbi_entero, new BigInteger("1")))
								{
									lbi_entero = new BigInteger(li_anio + "" + lbi_entero);

									{
										int li_secuencia;

										li_secuencia = lapr_DAO.findSecuencia();

										if(li_secuencia > 0)
										{
											DatosAntSistema ldas_datosTemp;

											ldas_datosTemp = new DatosAntSistema();

											ldas_datosTemp.setIdDatosAntSistema(ls_idDatosAnt);

											lapr_new.setIdPredioRegistro(StringUtils.getString(li_secuencia));
											lapr_new.setIdCirculo(ls_idCirculo);
											lapr_new.setIdMatricula(NumericUtils.getLongWrapper(lbi_entero));
											lapr_new.setIdTurno(adb_db.getTurno().getIdTurno());
											lapr_new.setIdTurnoHistoria(adb_db.getTurnoHistoria().getIdTurnoHistoria());
											lapr_new.setIdZonaRegistral(
											    luzr_ubicacion.getZonaRegistral().getIdZonaRegistral()
											);
											lapr_new.setNupre(ls_nupre);
											lapr_new.setPredioDefinitivo(EstadoCommon.PREDIO_DEFINITIVO_TEMPORAL);
											lapr_new.setTurnoBloqueo(adb_db.getTurno().getIdTurno());
											lapr_new.setIdTipoUsoSuelo(lpr_predioRegistro.getIdTipoUsoSuelo());
											lapr_new.setIdTipoPredio(lpr_predioRegistro.getIdTipoPredio());

											if(ldas_datosAntSistema != null)
												lapr_new.setIdTipoPredio(ldas_datosAntSistema.getIdTipoPredio());
											else
												lapr_new.setIdTipoPredio(EstadoCommon.R);

											lapr_new.setIdUsuarioCreacion(as_userId);
											lapr_new.setIpCreacion(as_remoteIp);
											lapr_new.setIdDatosAntSistema(ls_idDatosAnt);

											lapr_DAO.insert(lapr_new);

											ldas_datosTemp = ldasd_datosAntDAO.findById(ldas_datosTemp);

											if(ldas_datosTemp != null)
											{
												String ls_accion;

												ls_accion = ldas_datosTemp.getAccion();

												if(StringUtils.isValidString(ls_accion))
												{
													if(
													    ls_accion.equalsIgnoreCase(
														        AccionAntSistemaCommon.INFORME_RESULTADOS
														    )
													)
														ldas_datosTemp.setAccion(
														    AccionAntSistemaCommon.CREAR_MATRICULA_E_INFORME
														);
												}
												else
													ldas_datosTemp.setAccion(AccionAntSistemaCommon.CREAR_MATRICULAS);

												ldas_datosTemp.setIdUsuarioModificacion(as_userId);
												ldas_datosTemp.setIpModificacion(as_remoteIp);

												ldasd_datosAntDAO.updateAccionAntSistema(ldas_datosTemp);
											}
										}
										else
											throw new B2BException(ErrorKeys.ACC_PREDIO_REGISTRO_INSERT);
									}
								}
								else
									throw new B2BException(ErrorKeys.ACC_PREDIO_REGISTRO_SECUENCE);
							}
							else
							{
								AccPredioRegistro lapr_actual;

								lapr_actual     = lapr_new;

								lapr_actual = lapr_DAO.findById(lapr_actual);

								if(lapr_actual != null)
								{
									lapr_actual.setNombrePredio(ls_nombrePredio);
									lapr_actual.setIdZonaRegistral(
									    luzr_ubicacion.getZonaRegistral().getIdZonaRegistral()
									);
									lapr_actual.setNupre(ls_nupre);
									lapr_actual.setIdUsuarioModificacion(as_userId);
									lapr_actual.setIdTipoUsoSuelo(lpr_predioRegistro.getIdTipoUsoSuelo());
									lapr_actual.setIdTipoPredio(lpr_predioRegistro.getIdTipoPredio());

									lapr_DAO.updateById(lapr_actual);
								}
							}

							lapr_new = lapr_DAO.findById(lapr_new);

							if(lapr_new != null)
								adb_db.setAccPredioRegistro(lapr_new);
						}
					}
					else
						throw new B2BException(ErrorKeys.UBICACION_CIRCULO_INVALIDO);

					{
						EstadoPredio lsp_estadoPredio;

						lsp_estadoPredio = luzr_ubicacion.getEstadoPredio();

						if(lsp_estadoPredio != null)
						{
							String ls_idestadoPredio;

							ls_idestadoPredio = lsp_estadoPredio.getIdEstadoPredio();

							if(StringUtils.isValidString(ls_idestadoPredio))
							{
								lsp_estadoPredio = DaoCreator.getEstadoPredioDao(adm_manager).findById(
									    lsp_estadoPredio
									);

								if(lsp_estadoPredio != null)
								{
									AccPredioRegistro lapr_actual;

									lapr_actual     = adb_db.getAccPredioRegistro();

									lapr_actual = lapr_DAO.findById(lapr_actual);

									if(lapr_actual != null)
									{
										lapr_actual.setIdEstadoPredio(lsp_estadoPredio.getIdEstadoPredio());
										lapr_actual.setIdUsuarioModificacion(as_userId);

										lapr_DAO.updateById(lapr_actual);

										lapr_actual = lapr_DAO.findById(lapr_actual);

										if(lapr_actual != null)
											adb_db.setAccPredioRegistro(lapr_actual);
									}

									luzr_ubicacion.setEstadoPredio(lsp_estadoPredio);
								}
								else
									throw new B2BException(ErrorKeys.DEBE_SELECCIONAR_ESTADO_PREDIO_UBICACION);
							}
							else
								throw new B2BException(ErrorKeys.DEBE_SELECCIONAR_ESTADO_PREDIO_UBICACION);
						}
					}
				}
			}
			else
				throw new B2BException(ErrorKeys.DEBE_DILIGENCIAR_DATOS_BASICOS);
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("salvarDatosBasicosUbicacion", lb2be_e);
			throw lb2be_e;
		}

		return adb_db;
	}

	/**
	 * Método encargado de guardar toda la información referente a las direcciones de un predio.
	 *
	 * @param aapm_predio Objeto que contiene la información a guardar.
	 * @param as_userId Variable de tipo String que contiene el usuario que realiza la acción.
	 * @param as_ipRemote Variable de tipo String que contiene la ip del usuario que realiza la acción.
	 * @param adm_manager DAOManager que controla las transacciones.
	 * @return Colección de datos de tipo DireccionDelPredio que coincide con los parametros de entrada.
	 * @throws B2BException Señala que se ha producido una excepción
	 *
	 */
	private synchronized Collection<DireccionDelPredio> salvarDireccionPredio(
	    AccPredioRegistro aapm_predio, String as_userId, String as_ipRemote, DAOManager adm_manager
	)
	    throws B2BException
	{
		Collection<DireccionDelPredio> lcdp_return;

		lcdp_return = null;

		try
		{
			if(aapm_predio != null)
			{
				Long ll_idTurnoHistoria;

				ll_idTurnoHistoria = aapm_predio.getIdTurnoHistoria();

				if(NumericUtils.isValidLong(ll_idTurnoHistoria))
				{
					TurnoHistoria lth_th;

					lth_th = DaoCreator.getTurnoHistoriaDAO(adm_manager).findById(ll_idTurnoHistoria);

					if(lth_th != null)
					{
						Collection<DireccionDelPredio> lcdp_direcciones;

						lcdp_direcciones = aapm_predio.getDireccionesPredio();

						if(CollectionUtils.isValidCollection(lcdp_direcciones))
						{
							DireccionPredioAccDAO ldp_DAO;

							ldp_DAO         = DaoCreator.getDireccionPredioAccDAO(adm_manager);
							lcdp_return     = new ArrayList<DireccionDelPredio>();

							for(DireccionDelPredio ldpa_actual : lcdp_direcciones)
							{
								if(ldpa_actual != null)
								{
									DireccionPredioAcc ldpa_direccionActual;
									DireccionPredioAcc ldpa_direccionInsert;

									ldpa_direccionActual     = ldpa_actual.getDireccionPredio();
									ldpa_direccionInsert     = ldp_DAO.findById(ldpa_direccionActual);

									if(ldpa_actual.isEliminar() && (ldpa_direccionInsert != null))
										ldp_DAO.deleteById(ldpa_direccionInsert);
									else
									{
										if(ldpa_direccionInsert == null)
										{
											int li_secuencia;

											li_secuencia = ldp_DAO.findSecuence();

											if(li_secuencia > 0)
											{
												ldpa_direccionInsert = ldpa_direccionActual.getDireccionPredioAcc();

												ldpa_direccionInsert.setIdDireccionPredio(
												    StringUtils.getString(li_secuencia)
												);
												ldpa_direccionInsert.setIdTurnoHistoria(lth_th.getIdTurnoHistoria());
												ldpa_direccionInsert.setIdCirculo(aapm_predio.getIdCirculo());
												ldpa_direccionInsert.setIdMatricula(aapm_predio.getIdMatricula());

												{
													int li_maxIdDireccion;

													li_maxIdDireccion = ldp_DAO.findMaxIdDireccion(
														    ldpa_direccionInsert
														);

													ldpa_direccionInsert.setIdDireccion(
													    StringUtils.getString(++li_maxIdDireccion)
													);
												}

												ldpa_direccionInsert.setIdUsuarioCreacion(as_userId);
												ldpa_direccionInsert.setIpCreacion(
												    ldpa_direccionActual.getIpCreacion()
												);
												ldpa_direccionInsert.setIdTurno(lth_th.getIdTurno());

												ldp_DAO.insert(ldpa_direccionInsert);
											}
											else
												throw new B2BException(ErrorKeys.DEBE_DILIGENCIAR_DIRECCION_PREDIO);
										}
									}

									{
										String ls_tipoPredio;

										ls_tipoPredio = ldpa_actual.getDatosAntiguoSistema().getIdTipoPredio();

										if(!StringUtils.isValidString(ls_tipoPredio))
											ls_tipoPredio = (ldpa_direccionActual != null)
												? ldpa_direccionActual.getIdTipoPredio() : null;

										if(StringUtils.isValidString(ls_tipoPredio))
										{
											AccPredioRegistro    lapr_predio;
											AccPredioRegistroDAO lapr_DAO;

											lapr_predio     = new AccPredioRegistro();
											lapr_DAO        = DaoCreator.getAccPredioRegistroDAO(adm_manager);

											lapr_predio.setIdCirculo(aapm_predio.getIdCirculo());
											lapr_predio.setIdMatricula(aapm_predio.getIdMatricula());

											lapr_predio = lapr_DAO.findByCirculoMatricula(lapr_predio);

											if(lapr_predio != null)
											{
												lapr_predio.setIdTipoPredio(ls_tipoPredio);
												lapr_predio.setIdUsuarioModificacion(as_userId);
												lapr_predio.setIpModificacion(as_ipRemote);

												lapr_DAO.updateById(lapr_predio);
											}
										}
										else
											throw new B2BException(ErrorKeys.ERROR_SIN_TIPO_PREDIO);
									}

									ldpa_direccionInsert = ldp_DAO.findById(ldpa_direccionInsert);

									ldpa_actual.setDireccionPredio(ldpa_direccionInsert);

									lcdp_return.add(ldpa_actual);
								}
							}
						}
						else
							throw new B2BException(ErrorKeys.DEBE_DILIGENCIAR_DIRECCION_PREDIO);
					}
				}
				else
					throw new B2BException(ErrorKeys.NO_SE_ENCONTRO_ID_TURNO_HISTORIA);
			}
			else
				throw new B2BException(ErrorKeys.DIRECCION_PREDIO_GUARDAR);
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("salvarDireccionPredio", lb2be_e);
			throw lb2be_e;
		}

		return lcdp_return;
	}

	/**
	 * Método encargado de salvar un documento en la tabla SDB_BGN_DOCUMENTO.
	 *
	 * @param ad_documento Objeto que contiene la información del documento que se desea insertar.
	 * @param aso_solicitud Variable de tipo String que contiene el id de la solicitud en tramite.
	 * @param as_userId Variable de tipo String que contiene el id del usuario que está realizando el proceso.
	 * @param as_ipRemota Variable de tipo String que contiene la ip del usuario que está realizando el proceso.
	 * @param adm_manager Objeto contenedor de la conexión a la base de datos
	 * @return Objeto que contiene la información del documento insertado.
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see Documento
	 */
	private Documento salvarDocumento(
	    Documento ad_documento, Solicitud aso_solicitud, String as_userId, String as_ipRemota, DAOManager adm_manager
	)
	    throws B2BException
	{
		Documento ld_documento;

		ld_documento = null;

		if(ad_documento != null)
		{
			DocumentoDAO ldd_DAO;
			boolean      lb_insert;
			lb_insert     = false;

			ldd_DAO          = DaoCreator.getDocumentoDAO(adm_manager);
			ld_documento     = ldd_DAO.consultaDocumento(ad_documento);

			if(ld_documento == null)
			{
				int li_secuencia;
				li_secuencia = DaoCreator.getUtilDAO(adm_manager).findSecuence(
					    ConsultasUtilidades.CS_BGN_DOCUMENTO_SEC
					);

				if(li_secuencia > 0)
				{
					Integer li_tmp;
					li_tmp = NumericUtils.getInteger(li_secuencia);

					if(li_tmp != null)
					{
						ld_documento = new Documento();

						ld_documento.setIdDocumento(li_tmp.toString());
						ld_documento.setVersionDocumento(NumericUtils.getLongWrapper(1L));
						ld_documento.setNumero(ad_documento.getNumero());
						ld_documento.setIdTipoDocumento(ad_documento.getIdTipoDocumento());
						ld_documento.setOficinaInternacional(EstadoCommon.N);
						ld_documento.setFechaDocumento(ad_documento.getFechaDocumento());
						ld_documento.setFechaEjecutoria(ad_documento.getFechaEjecutoria());
						ld_documento.setIdTipoOficina(ad_documento.getIdTipoOficina());
						ld_documento.setTipoEntidad(ad_documento.getTipoEntidad());

						{
							String ls_idOficinaOrigen;

							ls_idOficinaOrigen = ad_documento.getIdOficinaOrigen();

							if(StringUtils.isValidString(ls_idOficinaOrigen))
								ls_idOficinaOrigen = ad_documento.getOficinaOrigen().getIdOficinaOrigen();

							ld_documento.setIdOficinaOrigen(ls_idOficinaOrigen);
							ld_documento.setVersion(obtenerVersionMaximaOficinaOrigen(ls_idOficinaOrigen, adm_manager));
						}

						ld_documento.setIdUsuarioCreacion(as_userId);
						ld_documento.setIpCreacion(as_ipRemota);
						ld_documento.setIdPais(ad_documento.getIdPais());
						ld_documento.setIdDepartamento(ad_documento.getIdDepartamento());
						ld_documento.setIdMunicipio(ad_documento.getIdMunicipio());

						ldd_DAO.insertOrUpdate(ld_documento, true);
						lb_insert = true;
					}
				}
			}

			if(ld_documento != null)
			{
				if((ad_documento.getFechaEjecutoria() != null) && !lb_insert)
				{
					if(
					    !ad_documento.getFechaEjecutoria().before(ld_documento.getFechaEjecutoria())
						    && !ld_documento.getFechaEjecutoria().after(ad_documento.getFechaEjecutoria())
					)
					{
						ld_documento.setIdDocumento(ld_documento.getIdDocumento());
						ld_documento.setVersionDocumento(ld_documento.getVersionDocumento());
						ld_documento.setNumero(ld_documento.getNumero());
						ld_documento.setIdTipoDocumento(ld_documento.getIdTipoDocumento());

						ld_documento.setOficinaInternacional("N");
						ld_documento.setFechaDocumento(ld_documento.getFechaDocumento());
						ld_documento.setFechaEjecutoria(ad_documento.getFechaEjecutoria());
						ld_documento.setIdTipoOficina(ad_documento.getIdTipoOficina());

						{
							String ls_idOficinaOrigen;

							ls_idOficinaOrigen = ad_documento.getIdOficinaOrigen();

							ld_documento.setIdOficinaOrigen(ls_idOficinaOrigen);
							ld_documento.setVersion(obtenerVersionMaximaOficinaOrigen(ls_idOficinaOrigen, adm_manager));
						}

						ld_documento.setIdUsuarioCreacion(as_userId);
						ld_documento.setIpCreacion(as_ipRemota);
						ld_documento.setIdUsuarioModificacion(as_userId);
						ld_documento.setIpModificacion(as_ipRemota);

						ldd_DAO.insertOrUpdate(ld_documento, false);
					}
				}

				SolicitudDAO lsd_DAO;
				Solicitud    lso_solicitud;

				lsd_DAO           = DaoCreator.getSolicitudDAO(adm_manager);
				lso_solicitud     = lsd_DAO.findById(aso_solicitud);

				if(lso_solicitud != null)
				{
					lso_solicitud.setIdDocumento(ld_documento.getIdDocumento());
					lso_solicitud.setVersionDocumento(ld_documento.getVersionDocumento());
					lso_solicitud.setVersionDocumentoInicial(ld_documento.getVersionDocumento());
					lso_solicitud.setIdUsuarioModificacion(as_userId);
					lso_solicitud.setIpModificacion(as_ipRemota);

					lsd_DAO.update(lso_solicitud);
				}
			}
			else
				throw new B2BException(ErrorKeys.ERROR_SIN_ID_DOCUMENTO);
		}

		return ld_documento;
	}

	/**
	 * Selecciona un motivo tramite adecuado para el tipo de proceso en tramite.
	 *
	 * @param al_idEtapa id de la etapa del tramite
	 * @param as_idProceso id del proceso en tramite
	 * @param ab_justificacionFirma justificacion del documento firmado
	 * @return id del motivo adecuado para la etapa y proceso en tramite
	 */
	private long seleccionarMotivoTramiteInforme(long al_idEtapa, String as_idProceso, boolean ab_justificacionFirma)
	{
		long ll_idMotivo;

		ll_idMotivo = 0;

		if((al_idEtapa > 0) && StringUtils.isValidString(as_idProceso))
		{
			if(al_idEtapa == EtapaCommon.ID_ETAPA_CALIFICACION_ANTIGUO_SISTEMA)
			{
				if(as_idProceso.equals(ProcesoCommon.ID_PROCESO_1))
					ll_idMotivo = ab_justificacionFirma
						? MotivoTramiteCommon.INFORME_DE_BUSQUEDA_PARA_CALIFICACION_FIRMA
						: MotivoTramiteCommon.INFORME_DE_BUSQUEDA_PARA_CERTIFICADOS;
				else if(as_idProceso.equals(ProcesoCommon.ID_PROCESO_37))
					ll_idMotivo = ab_justificacionFirma
						? MotivoTramiteCommon.INFORME_DE_BUSQUEDA_PARA_CALIFICACION_FIRMA
						: MotivoTramiteCommon.INFORME_DE_BUSQUEDA_PARA_GRABACION;
				else if(as_idProceso.equals(ProcesoCommon.ID_PROCESO_3))
					ll_idMotivo = ab_justificacionFirma
						? MotivoTramiteCommon.INFORME_DE_BUSQUEDA_PARA_CALIFICACION_FIRMA
						: MotivoTramiteCommon.INFORME_DE_BUSQUEDA_PARA_CORRECCIONES;
				else
					ll_idMotivo = ab_justificacionFirma
						? MotivoTramiteCommon.INFORME_DE_BUSQUEDA_PARA_CALIFICACION_FIRMA
						: MotivoTramiteCommon.INFORME_DE_BUSQUEDA_PARA_CALIFICACION;
			}
			else
				ll_idMotivo = ab_justificacionFirma
					? MotivoTramiteCommon.INFORME_DE_BUSQUEDA_DESDE_ANTIGUO_SISTEMA_FIRMA
					: MotivoTramiteCommon.INFORME_DE_BUSQUEDA_DESDE_ANTIGUO_SISTEMA;
		}

		return ll_idMotivo;
	}
}
