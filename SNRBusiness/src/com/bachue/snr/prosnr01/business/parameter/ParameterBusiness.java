package com.bachue.snr.prosnr01.business.parameter;

import com.b2bsg.common.dataAccess2.DAOManager;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.file.ZipEntryUtil;
import com.b2bsg.common.file.ZipUtils;

import com.b2bsg.common.logging.LoggerHandler;

import com.b2bsg.common.util.CollectionUtils;
import com.b2bsg.common.util.DateUtils;
import com.b2bsg.common.util.NumericUtils;
import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.business.BaseBusiness;

import com.bachue.snr.prosnr01.common.constants.ConstanteCommon;
import com.bachue.snr.prosnr01.common.constants.ErrorKeys;
import com.bachue.snr.prosnr01.common.constants.EstadoCommon;
import com.bachue.snr.prosnr01.common.constants.EtapaCommon;
import com.bachue.snr.prosnr01.common.constants.ExtensionCommon;
import com.bachue.snr.prosnr01.common.constants.IdentificadoresCommon;
import com.bachue.snr.prosnr01.common.constants.ProcesoCommon;
import com.bachue.snr.prosnr01.common.constants.TipoPersonaCommon;
import com.bachue.snr.prosnr01.common.utils.ExpresionRegular;
import com.bachue.snr.prosnr01.common.utils.ListadoCodigosConstantes;

import com.bachue.snr.prosnr01.dao.DaoCreator;
import com.bachue.snr.prosnr01.dao.DaoManagerFactory;
import com.bachue.snr.prosnr01.dao.acc.AccCompletitudDocumentalDAO;
import com.bachue.snr.prosnr01.dao.acc.AccConvenioCirculoRegistralDAO;
import com.bachue.snr.prosnr01.dao.acc.AccConvenioZonaRegistralDAO;
import com.bachue.snr.prosnr01.dao.acc.AccEntidadExternaConvenioDAO;
import com.bachue.snr.prosnr01.dao.acc.AccEntidadExternaDAO;
import com.bachue.snr.prosnr01.dao.acc.AccEntidadExternaPersonaDAO;
import com.bachue.snr.prosnr01.dao.acc.DesbordeDAO;
import com.bachue.snr.prosnr01.dao.acc.PersonaCorreoElectronicoDAO;
import com.bachue.snr.prosnr01.dao.acc.PersonaDAO;
import com.bachue.snr.prosnr01.dao.acc.ProcesoDAO;
import com.bachue.snr.prosnr01.dao.acc.SalarioMinimoDAO;
import com.bachue.snr.prosnr01.dao.acc.SolicitudDAO;
import com.bachue.snr.prosnr01.dao.acc.SubprocesoDAO;
import com.bachue.snr.prosnr01.dao.acc.SubprocesoVersionDAO;
import com.bachue.snr.prosnr01.dao.acc.TipoActoDAO;
import com.bachue.snr.prosnr01.dao.acc.TipoAdquisicionDAO;
import com.bachue.snr.prosnr01.dao.acc.TipoCausalDAO;
import com.bachue.snr.prosnr01.dao.acc.TipoEstadoSolicitudDAO;
import com.bachue.snr.prosnr01.dao.acc.TipoPagoDAO;
import com.bachue.snr.prosnr01.dao.acc.TipoRecepcionDAO;
import com.bachue.snr.prosnr01.dao.acc.TipoTarifaRegistralDAO;
import com.bachue.snr.prosnr01.dao.aut.ComponentesDAO;
import com.bachue.snr.prosnr01.dao.aut.OpcionDAO;
import com.bachue.snr.prosnr01.dao.aut.OpcionEtapaDAO;
import com.bachue.snr.prosnr01.dao.aut.RolDAO;
import com.bachue.snr.prosnr01.dao.aut.RolOpcionDAO;
import com.bachue.snr.prosnr01.dao.aut.UsuarioCirculoDAO;
import com.bachue.snr.prosnr01.dao.aut.UsuarioDAO;
import com.bachue.snr.prosnr01.dao.col.DominioRrrDAO;
import com.bachue.snr.prosnr01.dao.col.EstadoNupreDAO;
import com.bachue.snr.prosnr01.dao.col.EstadoRegistroDAO;
import com.bachue.snr.prosnr01.dao.col.InteresadoDocumentoTipoDAO;
import com.bachue.snr.prosnr01.dao.col.InteresadoNaturalGeneroDAO;
import com.bachue.snr.prosnr01.dao.col.PredioTipoDAO;
import com.bachue.snr.prosnr01.dao.col.TipoAreaDAO;
import com.bachue.snr.prosnr01.dao.col.TipoCanalOrigenDAO;
import com.bachue.snr.prosnr01.dao.col.TipoEstadoLiquidacionDAO;
import com.bachue.snr.prosnr01.dao.col.TipoRrrDAO;
import com.bachue.snr.prosnr01.dao.pgn.AlertaNaturalezaJuridicaDAO;
import com.bachue.snr.prosnr01.dao.pgn.CanalOrigenServicioDAO;
import com.bachue.snr.prosnr01.dao.pgn.CausalCorreccionDAO;
import com.bachue.snr.prosnr01.dao.pgn.CirculoRegistralDao;
import com.bachue.snr.prosnr01.dao.pgn.ConstantesDAO;
import com.bachue.snr.prosnr01.dao.pgn.DepartamentoDAO;
import com.bachue.snr.prosnr01.dao.pgn.EtapaDAO;
import com.bachue.snr.prosnr01.dao.pgn.FasesDAO;
import com.bachue.snr.prosnr01.dao.pgn.LibroAntiguoSistemaDao;
import com.bachue.snr.prosnr01.dao.pgn.LineaProduccionDAO;
import com.bachue.snr.prosnr01.dao.pgn.MedioRecaudoDAO;
import com.bachue.snr.prosnr01.dao.pgn.MensajeRespuestaDAO;
import com.bachue.snr.prosnr01.dao.pgn.MotivoTramiteDAO;
import com.bachue.snr.prosnr01.dao.pgn.MunicipioDAO;
import com.bachue.snr.prosnr01.dao.pgn.OficinaOrigenDAO;
import com.bachue.snr.prosnr01.dao.pgn.PaisDAO;
import com.bachue.snr.prosnr01.dao.pgn.PlantillaDAO;
import com.bachue.snr.prosnr01.dao.pgn.ReportesDAO;
import com.bachue.snr.prosnr01.dao.pgn.TagPlantillaResolucionDAO;
import com.bachue.snr.prosnr01.dao.pgn.TarifaFijaDAO;
import com.bachue.snr.prosnr01.dao.pgn.TipoActoCondicionDAO;
import com.bachue.snr.prosnr01.dao.pgn.TipoActoEjecutoriaDAO;
import com.bachue.snr.prosnr01.dao.pgn.TipoDecisionRecursoDAO;
import com.bachue.snr.prosnr01.dao.pgn.TipoDocumentoPublicoDAO;
import com.bachue.snr.prosnr01.dao.pgn.TipoEntidadDAO;
import com.bachue.snr.prosnr01.dao.pgn.TipoOficinaDAO;
import com.bachue.snr.prosnr01.dao.pgn.TipoPersonaDAO;
import com.bachue.snr.prosnr01.dao.pgn.TipoRecursoDAO;
import com.bachue.snr.prosnr01.dao.pgn.TipoRequiereMatriculaDAO;
import com.bachue.snr.prosnr01.dao.pgn.TipoTestamentoDAO;
import com.bachue.snr.prosnr01.dao.pgn.UsuarioLineaDAO;
import com.bachue.snr.prosnr01.dao.pgn.VeredaDAO;
import com.bachue.snr.prosnr01.dao.png.CirculoOrigenDestinoDAO;
import com.bachue.snr.prosnr01.dao.png.ComunidadesEtnicasDAO;
import com.bachue.snr.prosnr01.dao.png.DominioNaturalezaJuridicaDAO;
import com.bachue.snr.prosnr01.dao.png.EstadoAnotacionDAO;
import com.bachue.snr.prosnr01.dao.png.EstadoPredioDao;
import com.bachue.snr.prosnr01.dao.png.GrupoNaturalezaJuridicaDAO;
import com.bachue.snr.prosnr01.dao.png.TarifaAlertaDAO;
import com.bachue.snr.prosnr01.dao.png.TipoAnotacionDAO;
import com.bachue.snr.prosnr01.dao.png.TipoEjeDAO;
import com.bachue.snr.prosnr01.dao.png.UsuarioEtapaDAO;
import com.bachue.snr.prosnr01.dao.proc.ProcedimientosDAO;

import com.bachue.snr.prosnr01.model.numeracion.NumeracionOficiosCirculo;
import com.bachue.snr.prosnr01.model.registro.Archivo;
import com.bachue.snr.prosnr01.model.registro.TipoDocumentalActo;
import com.bachue.snr.prosnr01.model.sdb.acc.AccCompletitudDocumental;
import com.bachue.snr.prosnr01.model.sdb.acc.AccConvenioCirculoRegistral;
import com.bachue.snr.prosnr01.model.sdb.acc.AccConvenioZonaRegistral;
import com.bachue.snr.prosnr01.model.sdb.acc.AccEntidadExterna;
import com.bachue.snr.prosnr01.model.sdb.acc.AccEntidadExternaConvenio;
import com.bachue.snr.prosnr01.model.sdb.acc.AccEntidadExternaPersona;
import com.bachue.snr.prosnr01.model.sdb.acc.AlertaTramite;
import com.bachue.snr.prosnr01.model.sdb.acc.BitacoraProceso;
import com.bachue.snr.prosnr01.model.sdb.acc.CalidadSolicitante;
import com.bachue.snr.prosnr01.model.sdb.acc.CausalAprobacionDevolucion;
import com.bachue.snr.prosnr01.model.sdb.acc.Desborde;
import com.bachue.snr.prosnr01.model.sdb.acc.DetalleAntSistema;
import com.bachue.snr.prosnr01.model.sdb.acc.NotaDevolutiva;
import com.bachue.snr.prosnr01.model.sdb.acc.Persona;
import com.bachue.snr.prosnr01.model.sdb.acc.PersonaCorreoElectronico;
import com.bachue.snr.prosnr01.model.sdb.acc.Proceso;
import com.bachue.snr.prosnr01.model.sdb.acc.SalarioMinimo;
import com.bachue.snr.prosnr01.model.sdb.acc.Solicitud;
import com.bachue.snr.prosnr01.model.sdb.acc.SolicitudAsociada;
import com.bachue.snr.prosnr01.model.sdb.acc.Subproceso;
import com.bachue.snr.prosnr01.model.sdb.acc.SubprocesoVersion;
import com.bachue.snr.prosnr01.model.sdb.acc.TipoAdquisicion;
import com.bachue.snr.prosnr01.model.sdb.acc.TipoCausal;
import com.bachue.snr.prosnr01.model.sdb.acc.TipoEstadoSolicitud;
import com.bachue.snr.prosnr01.model.sdb.acc.TipoPago;
import com.bachue.snr.prosnr01.model.sdb.acc.TipoRecepcion;
import com.bachue.snr.prosnr01.model.sdb.acc.TipoTarifaRegistral;
import com.bachue.snr.prosnr01.model.sdb.acc.TipoTarjetaApoderado;
import com.bachue.snr.prosnr01.model.sdb.aut.Componente;
import com.bachue.snr.prosnr01.model.sdb.aut.Opcion;
import com.bachue.snr.prosnr01.model.sdb.aut.OpcionEtapa;
import com.bachue.snr.prosnr01.model.sdb.aut.Rol;
import com.bachue.snr.prosnr01.model.sdb.aut.RolOpcion;
import com.bachue.snr.prosnr01.model.sdb.aut.Usuario;
import com.bachue.snr.prosnr01.model.sdb.aut.UsuarioCirculo;
import com.bachue.snr.prosnr01.model.sdb.aut.UsuarioRol;
import com.bachue.snr.prosnr01.model.sdb.bng.Documento;
import com.bachue.snr.prosnr01.model.sdb.col.DominioRrr;
import com.bachue.snr.prosnr01.model.sdb.col.InteresadoDocumentoTipo;
import com.bachue.snr.prosnr01.model.sdb.col.InteresadoNaturalGenero;
import com.bachue.snr.prosnr01.model.sdb.col.ParteInteresada;
import com.bachue.snr.prosnr01.model.sdb.col.PredioTipo;
import com.bachue.snr.prosnr01.model.sdb.col.TipoRrr;
import com.bachue.snr.prosnr01.model.sdb.col.TipoUsoSuelo;
import com.bachue.snr.prosnr01.model.sdb.pgn.ActividadEconomica;
import com.bachue.snr.prosnr01.model.sdb.pgn.AlertaNaturalezaJuridica;
import com.bachue.snr.prosnr01.model.sdb.pgn.CamposCertificado;
import com.bachue.snr.prosnr01.model.sdb.pgn.CamposConsulta;
import com.bachue.snr.prosnr01.model.sdb.pgn.CamposCorreccion;
import com.bachue.snr.prosnr01.model.sdb.pgn.Catastro;
import com.bachue.snr.prosnr01.model.sdb.pgn.CausalCorreccion;
import com.bachue.snr.prosnr01.model.sdb.pgn.CausalMayorValor;
import com.bachue.snr.prosnr01.model.sdb.pgn.CausalNegacionCopias;
import com.bachue.snr.prosnr01.model.sdb.pgn.CausalRechazoRecurso;
import com.bachue.snr.prosnr01.model.sdb.pgn.CausalReimpresion;
import com.bachue.snr.prosnr01.model.sdb.pgn.CirculoActAdmin;
import com.bachue.snr.prosnr01.model.sdb.pgn.CirculoCatastro;
import com.bachue.snr.prosnr01.model.sdb.pgn.CirculoFestivo;
import com.bachue.snr.prosnr01.model.sdb.pgn.CirculoRegistral;
import com.bachue.snr.prosnr01.model.sdb.pgn.CondicionTarifa;
import com.bachue.snr.prosnr01.model.sdb.pgn.Constantes;
import com.bachue.snr.prosnr01.model.sdb.pgn.Consultas;
import com.bachue.snr.prosnr01.model.sdb.pgn.Departamento;
import com.bachue.snr.prosnr01.model.sdb.pgn.DependenciaSNR;
import com.bachue.snr.prosnr01.model.sdb.pgn.DetalleProcesoConsulta;
import com.bachue.snr.prosnr01.model.sdb.pgn.EstadoActividad;
import com.bachue.snr.prosnr01.model.sdb.pgn.EstadoNupre;
import com.bachue.snr.prosnr01.model.sdb.pgn.EstadoRegistro;
import com.bachue.snr.prosnr01.model.sdb.pgn.Etapa;
import com.bachue.snr.prosnr01.model.sdb.pgn.Fases;
import com.bachue.snr.prosnr01.model.sdb.pgn.FestivoNacional;
import com.bachue.snr.prosnr01.model.sdb.pgn.Gobernacion;
import com.bachue.snr.prosnr01.model.sdb.pgn.InstanciaConsulta;
import com.bachue.snr.prosnr01.model.sdb.pgn.LibroAntiguoSistema;
import com.bachue.snr.prosnr01.model.sdb.pgn.LibroTestamento;
import com.bachue.snr.prosnr01.model.sdb.pgn.LineaProduccion;
import com.bachue.snr.prosnr01.model.sdb.pgn.MedidaArea;
import com.bachue.snr.prosnr01.model.sdb.pgn.MotivoTramite;
import com.bachue.snr.prosnr01.model.sdb.pgn.Municipio;
import com.bachue.snr.prosnr01.model.sdb.pgn.OficinaOrigen;
import com.bachue.snr.prosnr01.model.sdb.pgn.Pais;
import com.bachue.snr.prosnr01.model.sdb.pgn.PlantillaComunicacion;
import com.bachue.snr.prosnr01.model.sdb.pgn.PlantillaNotificacion;
import com.bachue.snr.prosnr01.model.sdb.pgn.ProcesoAutomatico;
import com.bachue.snr.prosnr01.model.sdb.pgn.ProcesoConsulta;
import com.bachue.snr.prosnr01.model.sdb.pgn.RangoCuantia;
import com.bachue.snr.prosnr01.model.sdb.pgn.Regional;
import com.bachue.snr.prosnr01.model.sdb.pgn.Reportes;
import com.bachue.snr.prosnr01.model.sdb.pgn.ResultadoConsulta;
import com.bachue.snr.prosnr01.model.sdb.pgn.TagPlantillaResolucion;
import com.bachue.snr.prosnr01.model.sdb.pgn.TarifaFija;
import com.bachue.snr.prosnr01.model.sdb.pgn.TipoActo;
import com.bachue.snr.prosnr01.model.sdb.pgn.TipoActoCondicion;
import com.bachue.snr.prosnr01.model.sdb.pgn.TipoActoEjecutoria;
import com.bachue.snr.prosnr01.model.sdb.pgn.TipoActoProhibicion;
import com.bachue.snr.prosnr01.model.sdb.pgn.TipoArea;
import com.bachue.snr.prosnr01.model.sdb.pgn.TipoCanalOrigen;
import com.bachue.snr.prosnr01.model.sdb.pgn.TipoCriterioBusquedaPGN;
import com.bachue.snr.prosnr01.model.sdb.pgn.TipoDecisionRecurso;
import com.bachue.snr.prosnr01.model.sdb.pgn.TipoDocumental;
import com.bachue.snr.prosnr01.model.sdb.pgn.TipoDocumentoPublico;
import com.bachue.snr.prosnr01.model.sdb.pgn.TipoEntidad;
import com.bachue.snr.prosnr01.model.sdb.pgn.TipoEstadoLiquidacion;
import com.bachue.snr.prosnr01.model.sdb.pgn.TipoIntegracionGobernacion;
import com.bachue.snr.prosnr01.model.sdb.pgn.TipoOficina;
import com.bachue.snr.prosnr01.model.sdb.pgn.TipoOperacion;
import com.bachue.snr.prosnr01.model.sdb.pgn.TipoPersona;
import com.bachue.snr.prosnr01.model.sdb.pgn.TipoRecurso;
import com.bachue.snr.prosnr01.model.sdb.pgn.TipoRequiereMatricula;
import com.bachue.snr.prosnr01.model.sdb.pgn.TipoTestamento;
import com.bachue.snr.prosnr01.model.sdb.pgn.UnidadTiempoVencimiento;
import com.bachue.snr.prosnr01.model.sdb.pgn.UsuarioLinea;
import com.bachue.snr.prosnr01.model.sdb.pgn.Vereda;
import com.bachue.snr.prosnr01.model.sdb.pgn.ZonaRegistral;
import com.bachue.snr.prosnr01.model.sdb.png.CirculoOrigenDestino;
import com.bachue.snr.prosnr01.model.sdb.png.ComunidadesEtnicas;
import com.bachue.snr.prosnr01.model.sdb.png.Coordenada;
import com.bachue.snr.prosnr01.model.sdb.png.DominioNaturalezaJuridica;
import com.bachue.snr.prosnr01.model.sdb.png.EntidadesAlerta;
import com.bachue.snr.prosnr01.model.sdb.png.EstadoAnotacion;
import com.bachue.snr.prosnr01.model.sdb.png.EstadoPredio;
import com.bachue.snr.prosnr01.model.sdb.png.GrupoNaturalezaJuridica;
import com.bachue.snr.prosnr01.model.sdb.png.LetraEje;
import com.bachue.snr.prosnr01.model.sdb.png.NaturalezaJuridica;
import com.bachue.snr.prosnr01.model.sdb.png.ProcesoCanal;
import com.bachue.snr.prosnr01.model.sdb.png.TarifaAlerta;
import com.bachue.snr.prosnr01.model.sdb.png.TipoAnotacion;
import com.bachue.snr.prosnr01.model.sdb.png.TipoEje;
import com.bachue.snr.prosnr01.model.sdb.png.TipoNotificacion;
import com.bachue.snr.prosnr01.model.sdb.png.UsuarioEtapa;

import com.bachue.snr.prosnr02.model.pgn.ReglaNegocio;
import com.bachue.snr.prosnr02.model.pgn.TopologiaRegla;

import com.bachue.snr.prosnr04.dao.pgn.EntidadRecaudadoraDAO;
import com.bachue.snr.prosnr04.dao.pgn.PuntoRecaudoDAO;
import com.bachue.snr.prosnr04.dao.pgn.PuntoRecaudoTipoRecaudoDAO;
import com.bachue.snr.prosnr04.dao.pgn.SucursalCanalOrigenServicioDAO;
import com.bachue.snr.prosnr04.dao.pgn.TipoRecaudoDAO;

import com.bachue.snr.prosnr04.model.pgn.CanalOrigenServicio;
import com.bachue.snr.prosnr04.model.pgn.EntidadRecaudadora;
import com.bachue.snr.prosnr04.model.pgn.MedioRecaudo;
import com.bachue.snr.prosnr04.model.pgn.MensajeRespuesta;
import com.bachue.snr.prosnr04.model.pgn.PuntoRecaudo;
import com.bachue.snr.prosnr04.model.pgn.PuntoRecaudoTipoRecaudo;
import com.bachue.snr.prosnr04.model.pgn.SucursalCanalOrigenServicio;
import com.bachue.snr.prosnr04.model.pgn.TipoRecaudo;

import com.bachue.snr.prosnr16.model.sdb.pgn.Canal;
import com.bachue.snr.prosnr16.model.sdb.pgn.Estados;
import com.bachue.snr.prosnr16.model.sdb.pgn.Origen;
import com.bachue.snr.prosnr16.model.sdb.pgn.Plantilla;
import com.bachue.snr.prosnr16.model.sdb.pgn.Reintentos;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import java.math.BigDecimal;

import java.nio.file.Files;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;


/**
 * Clase para el manejo del negocio de las tablas parametricas en la base de datos.
 *
 * @author Heiner Castañeda
 */
public class ParameterBusiness extends BaseBusiness
{
	/** Constante clh_LOGGER. */
	private static final LoggerHandler clh_LOGGER = (LoggerHandler)com.bachue.snr.prosnr01.common.utils.LoggingHelper
			.getLogger(ParameterBusiness.class);

	/**
	 * Retorna el valor de caracter constante.
	 *
	 * @param as_constante correspondiente al valor del tipo de objeto String
	 * @return el valor de caracter constante
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public synchronized Map<String, String> getCaracterConstante(String as_constante)
	    throws B2BException
	{
		Map<String, String> lmss_return;
		DAOManager          ldm_manager;

		lmss_return     = null;
		ldm_manager     = DaoManagerFactory.getDAOManager();

		try
		{
			if(StringUtils.isValidString(as_constante))
				lmss_return = generarCodigos(as_constante, ldm_manager);
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("getCaracterConstante", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lmss_return;
	}

	/**
	 * Método encargado de consultar los circulos destino para un circulo origen especifico.
	 *
	 * @param as_idCirculoDestino Corresponde al id del circulo origen.
	 * @return Mapa que contiene los resultados de la consulta.
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public synchronized Map<String, String> getCirculosDestinoPorOrigen(String as_idCirculoDestino)
	    throws B2BException
	{
		Map<String, String> lmss_return;
		DAOManager          ldm_manager;

		lmss_return     = null;
		ldm_manager     = DaoManagerFactory.getDAOManager();

		try
		{
			if(StringUtils.isValidString(as_idCirculoDestino))
				lmss_return = DaoCreator.getCirculoOrigenDestinoDAO(ldm_manager)
						                    .findByIdCirculoOrigen(as_idCirculoDestino);
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("getCirculosDestinoPorOrigen", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lmss_return;
	}

	/**
	 * Accion click archivo.
	 *
	 * @param aa_archivo de aa archivo
	 * @return el valor de collection
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public synchronized Collection<Archivo> accionClickArchivo(Archivo aa_archivo)
	    throws B2BException
	{
		Collection<Archivo> lca_return;
		DAOManager          ldm_manager;

		lca_return      = null;
		ldm_manager     = DaoManagerFactory.getDAOManager();

		if(aa_archivo != null)
		{
			try
			{
				String ls_rutaLog;

				ls_rutaLog = aa_archivo.getUrl();

				if(StringUtils.isValidString(ls_rutaLog))
				{
					File lf_archivo = new File(ls_rutaLog);

					if(lf_archivo != null)
					{
						if(lf_archivo.isDirectory())
							lca_return = cargarListaArchivos(ls_rutaLog);
					}
				}
			}
			catch(Exception lb2be_e)
			{
				ldm_manager.setRollbackOnly();

				clh_LOGGER.error("cargarListaArchivos", lb2be_e);
			}
			finally
			{
				ldm_manager.close();
			}
		}

		return lca_return;
	}

	/**
	 * Accion regresar log.
	 *
	 * @param aa_archivo de aa archivo
	 * @return el valor de collection
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public synchronized Collection<Archivo> accionRegresarLog(Archivo aa_archivo)
	    throws B2BException
	{
		Collection<Archivo> lca_return;
		DAOManager          ldm_manager;

		lca_return      = null;
		ldm_manager     = DaoManagerFactory.getDAOManager();

		if(aa_archivo != null)
		{
			try
			{
				String ls_rutaLog;

				ls_rutaLog = aa_archivo.getUrl();

				if(StringUtils.isValidString(ls_rutaLog))
				{
					File lf_archivo = new File(ls_rutaLog);

					if(lf_archivo != null)
					{
						String ls_rutaPapa;

						ls_rutaPapa = lf_archivo.getParent();

						if(StringUtils.isValidString(ls_rutaPapa))
						{
							String ls_caracter;

							ls_caracter = DaoCreator.getConstantesDAO(ldm_manager).findString(ConstanteCommon.RUTA_LOG);

							if(
							    StringUtils.isValidString(ls_caracter)
								    && ((ls_caracter.length() - 1) <= ls_rutaPapa.length())
							)
							{
								File lf_fileTemp;

								lf_fileTemp = new File(ls_rutaPapa);

								if(lf_fileTemp != null)
								{
									if(lf_archivo.isDirectory())
										lca_return = cargarListaArchivos(ls_rutaPapa);
								}
							}
							else
							{
								if(lf_archivo.isDirectory())
									lca_return = cargarListaArchivos(ls_rutaLog);
							}
						}
					}
				}
			}
			catch(Exception lb2be_e)
			{
				ldm_manager.setRollbackOnly();

				clh_LOGGER.error("cargarListaArchivos", lb2be_e);
			}
			finally
			{
				ldm_manager.close();
			}
		}

		return lca_return;
	}

	/**
	 * Méotodo que retorna una colección con todos los documentos aportados de una solicitud.
	 *
	 * @param as_idSolicitud Argumento de tipo <code>String</code> que contiene el id de la solicitud.
	 * @return Retorna un objeto de tipo <code>Collection</code> que contiene los resultados que coincidieron con los criterios de búsqueda.
	 * @throws B2BException Señala que se ha generado una excepción.
	 */
	public synchronized Collection<AccCompletitudDocumental> buscarDocumentosAportados(String as_idSolicitud)
	    throws B2BException
	{
		DAOManager                           ldm_manager;
		Collection<AccCompletitudDocumental> lcacd_datos;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		lcacd_datos     = null;

		try
		{
			if(StringUtils.isValidString(as_idSolicitud))
			{
				Collection<SolicitudAsociada> lcs_solicitudAsociada;

				lcs_solicitudAsociada = DaoCreator.getSolicitudAsociadaDAO(ldm_manager)
						                              .findAllByIdSolicitud(as_idSolicitud, false);

				if(CollectionUtils.isValidCollection(lcs_solicitudAsociada))
				{
					Collection<AccCompletitudDocumental> lcacd_completitudDocumental;
					SolicitudDAO                         lsd_DAO;
					AccCompletitudDocumentalDAO          lcd_DAO;

					lcacd_completitudDocumental     = new ArrayList<AccCompletitudDocumental>();
					lsd_DAO                         = DaoCreator.getSolicitudDAO(ldm_manager);
					lcd_DAO                         = DaoCreator.getAccCompletitudDocumentalDAO(ldm_manager);

					for(SolicitudAsociada lsa_iterador : lcs_solicitudAsociada)
					{
						if(lsa_iterador != null)
						{
							String ls_idSolicitudAsociada;

							ls_idSolicitudAsociada = lsa_iterador.getIdSolicitud1();

							if(StringUtils.isValidString(ls_idSolicitudAsociada))
							{
								Solicitud ls_solicitud;

								ls_solicitud = lsd_DAO.findById(ls_idSolicitudAsociada);

								if(ls_solicitud != null)
								{
									String ls_idProceso;

									ls_idProceso = ls_solicitud.getIdProceso();

									if(
									    StringUtils.isValidString(ls_idProceso)
										    && (ls_idProceso.equalsIgnoreCase(ProcesoCommon.ID_PROCESO_47)
										    || ls_idProceso.equalsIgnoreCase(ProcesoCommon.ID_PROCESO_48))
									)
									{
										Collection<AccCompletitudDocumental> lcacd_completitud;

										lcacd_completitud = lcd_DAO.findAllByIdSolicitud(ls_solicitud.getIdSolicitud());

										if(CollectionUtils.isValidCollection(lcacd_completitud))
										{
											for(AccCompletitudDocumental lacd_iterador : lcacd_completitud)
											{
												if(lacd_iterador != null)
												{
													String  ls_obligatorio;
													boolean lb_seleccionado;

													ls_obligatorio = lacd_iterador.getObligatorio();

													if(ls_obligatorio != null)
													{
														if(ls_obligatorio.equalsIgnoreCase("S"))
															lb_seleccionado = true;
														else
															lb_seleccionado = false;
													}
													else
														lb_seleccionado = false;

													lacd_iterador.setSeleccionado(lb_seleccionado);
												}
											}

											lcacd_completitudDocumental.addAll(lcacd_completitud);
										}
									}
								}
							}
						}
					}

					if(CollectionUtils.isValidCollection(lcacd_completitudDocumental))
						lcacd_datos = lcacd_completitudDocumental;
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("buscarDocumentosAportados", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lcacd_datos;
	}

	/**
	 * Cargar lista archivos.
	 *
	 * @return el valor de collection
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public synchronized Collection<Archivo> cargarListaArchivos()
	    throws B2BException
	{
		Collection<Archivo> lca_return;
		DAOManager          ldm_manager;

		lca_return      = null;
		ldm_manager     = DaoManagerFactory.getDAOManager();

		try
		{
			String ls_rutaLog;

			ls_rutaLog = DaoCreator.getConstantesDAO(ldm_manager).findString(ConstanteCommon.RUTA_LOG);

			if(StringUtils.isValidString(ls_rutaLog))
				lca_return = cargarListaArchivos(ls_rutaLog);
		}
		catch(Exception lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("cargarListaArchivos", lb2be_e);
		}
		finally
		{
			ldm_manager.close();
		}

		return lca_return;
	}

	/**
	 * Descargar log.
	 *
	 * @param aa_archivo de aa archivo
	 * @return el valor de byte[]
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public synchronized byte[] descargarLog(Archivo aa_archivo)
	    throws B2BException
	{
		byte[]     lba_return;
		DAOManager ldm_manager;

		lba_return      = null;
		ldm_manager     = DaoManagerFactory.getDAOManager();

		if(aa_archivo != null)
		{
			try
			{
				String ls_rutaLog;

				ls_rutaLog = aa_archivo.getUrl();

				if(StringUtils.isValidString(ls_rutaLog))
				{
					File lf_archivo = new File(ls_rutaLog);

					if(lf_archivo != null)
						lba_return = Files.readAllBytes(lf_archivo.toPath());
				}
			}
			catch(Exception lb2be_e)
			{
				ldm_manager.setRollbackOnly();

				clh_LOGGER.error("descargarLog", lb2be_e);
			}
			finally
			{
				ldm_manager.close();
			}
		}

		return lba_return;
	}

	/**
	 * Método para realizar transacciones con la base de datos para el procedimiento nocturno.
	 *
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public synchronized void ejecutarProcNocturno()
	    throws B2BException
	{
		DAOManager ldm_manager;
		ldm_manager = DaoManagerFactory.getDAOManager();

		try
		{
			ProcedimientosDAO lpdao_pdao;

			lpdao_pdao = DaoCreator.getProcedimientosDAO(ldm_manager);

			lpdao_pdao.ejecutarTareaNocturna();
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("ejecutarProcNocturno", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}
	}

	/**
	 *  Método de transacciones con la base de datos para filtrar los registros de la tabla SDB_ACC_BITACORA_PROCESO.
	 *
	 * @param as_descripcion correspondiente al valor del tipo de objeto String
	 * @param ad_fechaDesde correspondiente al valor del tipo de objeto Date
	 * @param ld_fechaHasta correspondiente al valor del tipo de objeto Date
	 * @param ab_b correspondiente al valor del tipo de objeto boolean
	 * @return devuelve el valor de Collection
	 * @throws B2BException Señala que se ha producido una excepción
	 *
	 */
	public synchronized Collection<BitacoraProceso> filtroBitacoraProceso(
	    String as_descripcion, Date ad_fechaDesde, Date ld_fechaHasta, boolean ab_b
	)
	    throws B2BException
	{
		DAOManager                  ldm_manager;
		Collection<BitacoraProceso> lcr_datos;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		lcr_datos       = null;

		try
		{
			lcr_datos = DaoCreator.getBitacoraProcesoDAO(ldm_manager)
					                  .filtroFecha(as_descripcion, ad_fechaDesde, ld_fechaHasta, ab_b);
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("filtroBitacoraProceso", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lcr_datos;
	}

	/**
	 * Método para la transaccion con la base de datos para traer todos los
	 * registros existentes por ID en la tabla SDB_ACC_ENTIDAD_EXTERNA.
	 *
	 * @param aee_aee de aee aee
	 * @return devuelve el valor de AccEntidadExterna
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see Canal
	 */
	public synchronized AccEntidadExterna findAccEntidadExternaById(AccEntidadExterna aee_aee)
	    throws B2BException
	{
		DAOManager        ldm_manager;
		AccEntidadExterna laee_datos;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		laee_datos      = null;

		try
		{
			String ls_idEntidadExterna;

			ls_idEntidadExterna = aee_aee.getIdEntidadExterna();

			if(StringUtils.isValidString(ls_idEntidadExterna))
				laee_datos = DaoCreator.getAccEntidadExternaDAO(ldm_manager)
						                   .findByIdAccEntidadExterna(ls_idEntidadExterna);
		}

		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findAccEntidadExternaById", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return laee_datos;
	}

	/**
	 * Método para la transaccion con la base de datos para traer todos los
	 * registros existentes por ID en la tabla SDB_ACC_ENTIDAD_EXTERNA_CONVENIO.
	 *
	 * @param aaeec_aeec de aaeec aeec
	 * @return devuelve el valor de AccEntidadExterna
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see Canal
	 */
	public synchronized AccEntidadExternaConvenio findAccEntidadExternaConvenioById(
	    AccEntidadExternaConvenio aaeec_aeec
	)
	    throws B2BException
	{
		DAOManager                ldm_manager;
		AccEntidadExternaConvenio laeec_datos;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		laeec_datos     = null;

		try
		{
			if(aaeec_aeec != null)
			{
				laeec_datos = DaoCreator.getAccEntidadExternaConvenioDAO(ldm_manager).findById(aaeec_aeec);

				if(laeec_datos != null)
				{
					Collection<AccConvenioZonaRegistral> lcaczr_convenioZonaRegistral;
					AccConvenioZonaRegistral             laczr_convenioZona;

					laczr_convenioZona = new AccConvenioZonaRegistral();

					laczr_convenioZona.setIdEntidadExterna(laeec_datos.getIdEntidadExterna());
					laczr_convenioZona.setNumeroConvenio(laeec_datos.getNumeroConvenio());

					lcaczr_convenioZonaRegistral = DaoCreator.getAccConvenioZonaRegistralDAO(ldm_manager)
							                                     .findByIdActivo(laczr_convenioZona);

					if(CollectionUtils.isValidCollection(lcaczr_convenioZonaRegistral))
					{
						Collection<ZonaRegistral> lczr_zonaRegistral;

						lczr_zonaRegistral = new ArrayList<ZonaRegistral>();

						for(AccConvenioZonaRegistral lc_temp : lcaczr_convenioZonaRegistral)
						{
							if(lc_temp != null)
							{
								String ls_idZonaRegistral;

								ls_idZonaRegistral = lc_temp.getIdZonaRegistral();

								if(StringUtils.isValidString(ls_idZonaRegistral))
								{
									Collection<ZonaRegistral> lczr_zonaRegistralAsociadas;

									lczr_zonaRegistralAsociadas = findAllZonaRegistralesActivos(
										    ls_idZonaRegistral, false
										);

									if(CollectionUtils.isValidCollection(lczr_zonaRegistralAsociadas))
									{
										for(ZonaRegistral lcz_temp : lczr_zonaRegistralAsociadas)
										{
											if(lcz_temp != null)
												lczr_zonaRegistral.add(lcz_temp);
										}
									}
								}
							}
						}

						laeec_datos.setZonaRegistral(lczr_zonaRegistral);
					}
				}
			}
		}

		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findAccEntidadExternaConvenioById", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return laeec_datos;
	}

	/**
	 * Método para la transaccion con la base de datos para traer todos los
	 * registros existentes por ID en la tabla SDB_PGN_ACTIVIDAD_ECONOMICA.
	 *
	 * @param aae_param contenedor de la informacion con el identificador a consultar
	 * @return devuelve el valor de ActividadEconomica
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see Canal
	 */
	public synchronized ActividadEconomica findActividadEconomicaById(ActividadEconomica aae_param)
	    throws B2BException
	{
		ActividadEconomica laae_datos;

		laae_datos = null;

		if(aae_param != null)
		{
			DAOManager ldm_manager;

			ldm_manager = DaoManagerFactory.getDAOManager();

			try
			{
				laae_datos = DaoCreator.getActividadEconomicaDAO(ldm_manager).findById(aae_param);
			}

			catch(B2BException lb2be_e)
			{
				ldm_manager.setRollbackOnly();

				clh_LOGGER.error("findActividadEconomicaById", lb2be_e);

				throw lb2be_e;
			}
			finally
			{
				ldm_manager.close();
			}
		}

		return laae_datos;
	}

	/**
	 * Realiza la busqueda en la tabla SDB_PGN_TAG_PLANTILLA_RESOLUCION mediante
	 * plantilla y tag.
	 *
	 * @param as_idActuacionesAdministrativas de as id actuaciones administrativas
	 * @return retorna el objeto a consultar
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public synchronized TagPlantillaResolucion findActuacionesAdministrativasById(
	    String as_idActuacionesAdministrativas
	)
	    throws B2BException
	{
		TagPlantillaResolucion laa_datos;

		laa_datos = null;

		{
			DAOManager ldm_manager;

			ldm_manager = DaoManagerFactory.getDAOManager();

			try
			{
				if(StringUtils.isValidString(as_idActuacionesAdministrativas))
					laa_datos = DaoCreator.getTagPlantillaResolucionDAO(ldm_manager)
							                  .findById(as_idActuacionesAdministrativas);
			}

			catch(B2BException lb2be_e)
			{
				ldm_manager.setRollbackOnly();

				clh_LOGGER.error("findActuacionesAdministrativasById", lb2be_e);

				throw lb2be_e;
			}
			finally
			{
				ldm_manager.close();
			}
		}

		return laa_datos;
	}

	/**
	 * Retorna el valor del objeto de AlertaNaturalezaJuridica.
	 *
	 * @param acad_cad Objeto de clase AlertaNaturalezaJuridica con el cual se realiza la busqueda en la tabla ALERTA_NATURALEZA_JURIDICA
	 * @return Objeto de clase AlertaNaturalezaJuridica
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see AlertaNaturalezaJuridica
	 */
	public synchronized AlertaNaturalezaJuridica findAlertaNaturalezaJuridicaById(AlertaNaturalezaJuridica acad_cad)
	    throws B2BException
	{
		DAOManager               ldm_manager;
		AlertaNaturalezaJuridica lcr_datos;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		lcr_datos       = null;

		try
		{
			if(acad_cad != null)
			{
				Long   ll_version;
				String ls_id;

				ll_version     = acad_cad.getVersion();
				ls_id          = acad_cad.getIdNaturalezaJuridica();

				if(ll_version != null)
					lcr_datos = DaoCreator.getAlertaNaturalezaJuridicaDAO(ldm_manager)
							                  .findById(ls_id, NumericUtils.getLong(ll_version));
			}
		}

		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findMediddaAreaById", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lcr_datos;
	}

	/**
	 * Retorna el valor del objeto de AlertaTramite.
	 *
	 * @param acad_cad correspondiente al valor del tipo de objeto AlertaTramite
	 * @return devuelve el valor de AlertaTramite
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see AlertaTramite
	 */
	public synchronized AlertaTramite findAlertaTramiteById(AlertaTramite acad_cad)
	    throws B2BException
	{
		DAOManager    ldm_manager;
		AlertaTramite lcr_datos;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		lcr_datos       = null;

		try
		{
			lcr_datos = DaoCreator.getAlertaTramiteDAO(ldm_manager).findById(acad_cad);
		}

		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findAlertaTramiteById", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lcr_datos;
	}

	/**
	 * Método para encontrar todos los registros de la base de datos para la tabla
	 * SDB_ACC_ENTIDAD_EXTERNA_CONVENIO.
	 *
	 * @param ab_accion correspondiente al valor del tipo de objeto boolean
	 * @return devuelve el valor de Collection
	 * @throws B2BException Señala que se ha producido una excepción
	 *
	 */
	public synchronized Collection<AccEntidadExternaConvenio> findAllAccEntidadExternaConvenio(boolean ab_accion)
	    throws B2BException
	{
		DAOManager                            ldm_manager;
		Collection<AccEntidadExternaConvenio> lcaeec_datos;

		ldm_manager      = DaoManagerFactory.getDAOManager();
		lcaeec_datos     = null;

		try
		{
			lcaeec_datos = DaoCreator.getAccEntidadExternaConvenioDAO(ldm_manager).findAll(ab_accion);
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findAllAccEntidadExternaConvenio", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lcaeec_datos;
	}

	/**
	 * Método para encontrar todos los registros de la base de datos para la tabla
	 * SDB_ACC_ENTIDAD_EXTERNA.
	 *
	 * @return devuelve el valor de Collection
	 * @throws B2BException Señala que se ha producido una excepción
	 *
	 */
	public synchronized Collection<AccEntidadExterna> findAllAccEntidadExternas()
	    throws B2BException
	{
		DAOManager                    ldm_manager;
		Collection<AccEntidadExterna> lcaee_datos;
		TipoEntidadDAO                lted_tipoEntidadDAO;
		DepartamentoDAO               ldd_departamentoDAO;
		MunicipioDAO                  lmd_municipioDAO;
		PaisDAO                       lpd_paisDAO;
		PersonaDAO                    lpd_personaDAO;
		AccEntidadExternaPersonaDAO   laeepd_entidadExternaPersonaDAO;

		ldm_manager                         = DaoManagerFactory.getDAOManager();
		lcaee_datos                         = null;
		lted_tipoEntidadDAO                 = DaoCreator.getTipoEntidadDAO(ldm_manager);
		ldd_departamentoDAO                 = DaoCreator.getDepartamentoDAO(ldm_manager);
		lmd_municipioDAO                    = DaoCreator.getMunicipioDAO(ldm_manager);
		lpd_paisDAO                         = DaoCreator.getPaisDAO(ldm_manager);
		lpd_personaDAO                      = DaoCreator.getPersonaDAO(ldm_manager);
		laeepd_entidadExternaPersonaDAO     = DaoCreator.getAccEntidadExternaPersonaDAO(ldm_manager);

		try
		{
			lcaee_datos = DaoCreator.getAccEntidadExternaDAO(ldm_manager).findAllAccEntidad();

			if(CollectionUtils.isValidCollection(lcaee_datos))
			{
				for(AccEntidadExterna lc_temp : lcaee_datos)
				{
					if(lc_temp != null)
					{
						TipoEntidad lte_tipoEntidad;

						lte_tipoEntidad = lted_tipoEntidadDAO.findById(lc_temp.getIdTipoEntidad());

						if(lte_tipoEntidad != null)
							lc_temp.setNombreTipoEntidad(lte_tipoEntidad.getNombre());

						String ls_idDepartamento;
						String ls_idMunicipio;
						String ls_idPais;

						ls_idDepartamento     = lc_temp.getIdDepartamento();
						ls_idMunicipio        = lc_temp.getIdMunicipio();
						ls_idPais             = lc_temp.getIdPais();

						if(StringUtils.isValidString(ls_idPais))
						{
							Pais lp_pais;

							lp_pais = lpd_paisDAO.findById(ls_idPais);

							if(lp_pais != null)
								lc_temp.setNombrePais(lp_pais.getNombre());

							if(StringUtils.isValidString(ls_idDepartamento))
							{
								Departamento ld_deparmento;

								ld_deparmento = new Departamento();

								ld_deparmento.setIdDepartamento(ls_idDepartamento);
								ld_deparmento.setIdPais(ls_idPais);

								ld_deparmento = ldd_departamentoDAO.findById(ld_deparmento);

								if(ld_deparmento != null)
									lc_temp.setNombreDepartamento(ld_deparmento.getNombre());

								if(StringUtils.isValidString(ls_idMunicipio))
								{
									Municipio lm_municipio;

									lm_municipio = new Municipio();

									lm_municipio.setIdDepartamento(ls_idDepartamento);
									lm_municipio.setIdPais(ls_idPais);
									lm_municipio.setIdMunicipio(ls_idMunicipio);

									lm_municipio = lmd_municipioDAO.findById(lm_municipio);

									if(lm_municipio != null)
										lc_temp.setNombreMunicipio(lm_municipio.getNombre());
								}
							}
						}

						{
							String ls_idRepresentanteLegal;

							ls_idRepresentanteLegal = lc_temp.getIdRepresentanteLegal();

							if(StringUtils.isValidString(ls_idRepresentanteLegal))
							{
								AccEntidadExternaPersona laeep_entidadExternaPersona;

								laeep_entidadExternaPersona = laeepd_entidadExternaPersonaDAO
										.findByIdEntidadExternaPersona(ls_idRepresentanteLegal);

								if(laeep_entidadExternaPersona != null)
								{
									Persona lp_persona;

									lp_persona = lpd_personaDAO.findById(laeep_entidadExternaPersona.getIdPersona());

									if(lp_persona != null)
										lc_temp.setNombreRepresentanteLegal(
										    lp_persona.getPrimerNombre() + " " + lp_persona.getPrimerApellido()
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
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findAllAccEntidadExternas", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lcaee_datos;
	}

	/**
	 * Método para encontrar todos los registros de la base de datos para la tabla
	 * SDB_PGN_ACTIVIDAD_ECONOMICA.
	 *
	 * @return devuelve el valor de Collection
	 * @throws B2BException Señala que se ha producido una excepción
	 *
	 */
	public synchronized Collection<ActividadEconomica> findAllActividadEconomica()
	    throws B2BException
	{
		DAOManager                     ldm_manager;
		Collection<ActividadEconomica> lcae_datos;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		lcae_datos      = null;

		try
		{
			lcae_datos = DaoCreator.getActividadEconomicaDAO(ldm_manager).findAll();
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findAllActividadEconomica", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lcae_datos;
	}

	/**
	 * Método para encontrar todos los registros de la base de datos para la tabla
	 * SDB_PGN_ACTIVIDAD_ECONOMICA que se encuentren en estado activo.
	 *
	 * @return devuelve el valor de Collection
	 * @throws B2BException Señala que se ha producido una excepción
	 *
	 */
	public synchronized Collection<ActividadEconomica> findAllActividadEconomicaActivo()
	    throws B2BException
	{
		DAOManager                     ldm_manager;
		Collection<ActividadEconomica> lcae_datos;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		lcae_datos      = null;

		try
		{
			lcae_datos = DaoCreator.getActividadEconomicaDAO(ldm_manager).findAllActivo();
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findAllActividadEconomicaActivo", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lcae_datos;
	}

	/**
	 * Método para encontrar todos los registros de la base de datos para la tabla
	 * SDB_PGN_TAG_PLANTILLA_RESOLUCION.
	 *
	 * @return el valor de collection
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public synchronized Collection<TagPlantillaResolucion> findAllActuacionesAdministrativas()
	    throws B2BException
	{
		DAOManager                         ldm_manager;
		Collection<TagPlantillaResolucion> lcaa_datos;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		lcaa_datos      = null;

		try
		{
			lcaa_datos = DaoCreator.getTagPlantillaResolucionDAO(ldm_manager).findAll();
		}

		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findAllActuacionesAdministrativas", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lcaa_datos;
	}

	/**
	 * Realiza la busqueda en la tabla SDB_PGN_TAG_PLANTILLA_RESOLUCION mediante
	 * plantilla y tag.
	 *
	 * @param as_plantilla            parametro de busqueda
	 * @param as_tag            parametro de busqueda
	 * @return retorna el objeto a consultar
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public synchronized TagPlantillaResolucion findAllActuacionesAdministrativasByIdPlantillaTag(
	    String as_plantilla, String as_tag
	)
	    throws B2BException
	{
		TagPlantillaResolucion laa_datos;

		laa_datos = null;

		{
			DAOManager ldm_manager;

			ldm_manager = DaoManagerFactory.getDAOManager();

			try
			{
				if(StringUtils.isValidString(as_plantilla) && StringUtils.isValidString(as_tag))
					laa_datos = DaoCreator.getTagPlantillaResolucionDAO(ldm_manager)
							                  .findByIdPlantillaTag(as_plantilla, as_tag);
			}

			catch(B2BException lb2be_e)
			{
				ldm_manager.setRollbackOnly();

				clh_LOGGER.error("findAllTipoActoProhibicionByIdPlantillaTag", lb2be_e);

				throw lb2be_e;
			}
			finally
			{
				ldm_manager.close();
			}
		}

		return laa_datos;
	}

	/**
	 * Método para actualizar la versión del tipo acto basado en su ID.
	 *
	 * @param as_idTipoActoProhibicion de as id tipo acto prohibicion
	 * @return TipoActo con los datos solicitados
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public synchronized TipoActo findAllActualizarVersionById(String as_idTipoActoProhibicion)
	    throws B2BException
	{
		DAOManager ldm_manager;
		TipoActo   lcc_datos;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		lcc_datos       = null;

		try
		{
			if(StringUtils.isValidString(as_idTipoActoProhibicion))
				lcc_datos = DaoCreator.getTipoActoDAO(ldm_manager).findById(as_idTipoActoProhibicion);
		}

		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findAllTipoActoProhibicionById", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lcc_datos;
	}

	/**
	 * Método para encontrar todos los registros de la base de datos para la tabla
	 * SDB_AUT_COMPONENTE.
	 *
	 * @return el valor de collection
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public synchronized Collection<Componente> findAllAdministracionComponentes()
	    throws B2BException
	{
		DAOManager             ldm_manager;
		Collection<Componente> lcac_datos;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		lcac_datos      = null;

		try
		{
			lcac_datos = DaoCreator.getComponentesDAO(ldm_manager).findAll();
		}

		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findAllAdministracionComponentes", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lcac_datos;
	}

/**
 * Find all administracion componentes by id.
 *
 * @param aac_ac de aac ac
 * @return Objeto de clase AdministracionComponentes
 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
 */
	public synchronized Componente findAllAdministracionComponentesById(Componente aac_ac)
	    throws B2BException
	{
		DAOManager ldm_manager;
		Componente lac_datos;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		lac_datos       = null;

		try
		{
			if(aac_ac != null)
				lac_datos = DaoCreator.getComponentesDAO(ldm_manager).findById(aac_ac.getIdComponente());
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findAllAdministracionComponentesById", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lac_datos;
	}

	/**
	 * Método para encontrar todos los registros de la base de datos para la tabla
	 * SDB_AUT_COMPONENTE.
	 *
	 * @param as_rol de as rol
	 * @return el valor de collection
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public synchronized Collection<Componente> findAllAdministracionComponentesPermisos(String as_rol)
	    throws B2BException
	{
		DAOManager             ldm_manager;
		Collection<Componente> lcac_datos;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		lcac_datos      = null;

		try
		{
			lcac_datos = DaoCreator.getComponentesDAO(ldm_manager).findAllActivo();

			if(CollectionUtils.isValidCollection(lcac_datos))
			{
				OpcionDAO    lod_opcionDAO;
				RolOpcionDAO lrod_rolOpcionDAO;

				lod_opcionDAO         = DaoCreator.getOpcionDAO(ldm_manager);
				lrod_rolOpcionDAO     = DaoCreator.getRolOpcionDAO(ldm_manager);

				for(Componente lc_temp : lcac_datos)
				{
					if(lc_temp != null)
					{
						Collection<Opcion> lco_opcion;
						Collection<Opcion> lco_source;
						Collection<Opcion> lco_target;

						lco_target     = new ArrayList<Opcion>();
						lco_source     = new ArrayList<Opcion>();
						lco_opcion     = lod_opcionDAO.findByComponente(lc_temp.getIdComponente());

						if(CollectionUtils.isValidCollection(lco_opcion))
						{
							for(Opcion lcc_temp : lco_opcion)
							{
								if(lcc_temp != null)
								{
									String ls_opcion;

									ls_opcion = lcc_temp.getIdOpcion();

									if(StringUtils.isValidString(ls_opcion) && StringUtils.isValidString(as_rol))
									{
										RolOpcion lro_rolOpcion;

										lro_rolOpcion = lrod_rolOpcionDAO.findRolOpcionById(as_rol, ls_opcion);

										if((lro_rolOpcion != null) && lro_rolOpcion.getActivo().equals(EstadoCommon.S))
											lco_target.add(lcc_temp);
										else
											lco_source.add(lcc_temp);
									}
								}
							}

							lc_temp.setOpcionesRol(lco_source);
							lc_temp.setOpcionesRolTarget(lco_target);
						}
					}
				}
			}
		}

		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findAllAdministracionComponentesPermisos", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lcac_datos;
	}

	/**
	 * Retorna el valor del objeto de Collection.
	 *
	 * @return devuelve el valor de Collection
	 * @throws B2BException Señala que se ha producido una excepción
	 *
	 */
	public synchronized Collection<AlertaNaturalezaJuridica> findAllAlertaNaturalezaJuridica()
	    throws B2BException
	{
		DAOManager                           ldm_manager;
		Collection<AlertaNaturalezaJuridica> lcanj_datos;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		lcanj_datos     = null;

		try
		{
			lcanj_datos = DaoCreator.getAlertaNaturalezaJuridicaDAO(ldm_manager).findAll();
		}

		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findAllAlertaNaturalezaJuridica", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lcanj_datos;
	}

	/**
	 * Retorna el valor del objeto de Collection.
	 *
	 * @return devuelve el valor de Collection
	 * @throws B2BException Señala que se ha producido una excepción
	 *
	 */
	public synchronized Collection<AlertaTramite> findAllAlertaTramite()
	    throws B2BException
	{
		DAOManager                ldm_manager;
		Collection<AlertaTramite> lcr_datos;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		lcr_datos       = null;

		try
		{
			lcr_datos = DaoCreator.getAlertaTramiteDAO(ldm_manager).findAll(false);
		}

		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findAllAlertaTramite", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lcr_datos;
	}

	/**
	 * Método para consultar todos los campos criterio de busqueda con un tipo criterio de b´suqueda especifíco.
	 *
	 * @param as_tipoCriterioBusqueda de as tipo criterio busqueda
	 * @return una colleccionde campos Consulta con los datos solicitados
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public synchronized Collection<CamposConsulta> findAllCampoCriterioBusquedaByTipoCriterioBusqueda(
	    String as_tipoCriterioBusqueda
	)
	    throws B2BException
	{
		DAOManager                 ldm_manager;
		Collection<CamposConsulta> lcc_datos;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		lcc_datos       = null;

		try
		{
			if(StringUtils.isValidString(as_tipoCriterioBusqueda))
				lcc_datos = DaoCreator.getCampoCriterioBusquedaDAO(ldm_manager)
						                  .findAllByTipoCriterioBusqueda(as_tipoCriterioBusqueda);
		}

		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findAllCampoCriterioBusquedaByTipoCriterioBusqueda", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lcc_datos;
	}

	/**
	 * Método para encontrar todos los registros de la base de datos para la tabla
	 * SDB_PGN_CAMPOS_CERTIFICADO.
	 *
	 * @return devuelve el valor de Collection
	 * @throws B2BException Señala que se ha producido una excepción
	 *
	 */
	public synchronized Collection<CamposCertificado> findAllCamposCertificado()
	    throws B2BException
	{
		DAOManager                    ldm_manager;
		Collection<CamposCertificado> lccc_datos;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		lccc_datos      = null;

		try
		{
			lccc_datos = DaoCreator.getCamposCertificadoDAO(ldm_manager).findAll();
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findAllCamposCertificado", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lccc_datos;
	}

	/**
	 * Retorna el valor del objeto de Collection.
	 *
	 * @return devuelve el valor de Collection
	 * @throws B2BException Señala que se ha producido una excepción
	 *
	 */
	public synchronized Collection<CamposConsulta> findAllCamposConsulta()
	    throws B2BException
	{
		DAOManager                 ldm_manager;
		Collection<CamposConsulta> lcr_datos;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		lcr_datos       = null;

		try
		{
			lcr_datos = DaoCreator.getCamposConsultaDAO(ldm_manager).findAll(false);
		}

		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findAllCamposConsulta", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lcr_datos;
	}

	/**
	 * Método para encontrar todos los registros de la base de datos para la tabla
	 * SDB_PGN_CAMPOS_CORRECCION.
	 *
	 * @return devuelve el valor de Collection
	 * @throws B2BException Señala que se ha producido una excepción
	 *
	 */
	public synchronized Collection<CamposCorreccion> findAllCamposCorreccion()
	    throws B2BException
	{
		DAOManager                   ldm_manager;
		Collection<CamposCorreccion> lccc_datos;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		lccc_datos      = null;

		try
		{
			lccc_datos = DaoCreator.getCamposCorreccionDAO(ldm_manager).findAll();

			if(CollectionUtils.isValidCollection(lccc_datos))
			{
				CausalCorreccionDAO lccd_causalCorreccionDAO;

				lccd_causalCorreccionDAO = DaoCreator.getCausalCorreccionDAO(ldm_manager);

				for(CamposCorreccion lcc_tmp : lccc_datos)
				{
					if(lcc_tmp != null)
					{
						Long ll_idCausalCorreccion;

						ll_idCausalCorreccion = lcc_tmp.getIdCausalCorreccion();

						if(NumericUtils.isValidLong(ll_idCausalCorreccion))
						{
							CausalCorreccion lcc_causalCorreccion;

							lcc_causalCorreccion = lccd_causalCorreccionDAO.findById(ll_idCausalCorreccion);

							if(lcc_causalCorreccion != null)
								lcc_tmp.setNombreCausal(lcc_causalCorreccion.getNombre());
						}
					}
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findAllCamposCorreccion", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lccc_datos;
	}

	/**
	 * Método para consultar todos los registros de CamposCriterioBusqueda.
	 *
	 * @return Collection<CamposConsulta> Colección de CamposConsulta que contiene
	 *         los criterios de búsqueda
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public synchronized Collection<CamposConsulta> findAllCamposCriterioBusqueda()
	    throws B2BException
	{
		DAOManager                 ldm_manager;
		Collection<CamposConsulta> lcc_datos;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		lcc_datos       = null;

		try
		{
			lcc_datos = DaoCreator.getCampoCriterioBusquedaDAO(ldm_manager).findAll();
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findAllCamposCriterioBusqueda", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lcc_datos;
	}

	/**
	 * Método para encontrar todos los registros de la base de datos para la tabla
	 * SDB_PGN_CANAL.
	 *
	 * @param ab_accion correspondiente al valor del tipo de objeto boolean
	 * @return devuelve el valor de Collection
	 * @throws B2BException Señala que se ha producido una excepción
	 *
	 */
	public synchronized Collection<Canal> findAllCanal(boolean ab_accion)
	    throws B2BException
	{
		DAOManager        ldm_manager;
		Collection<Canal> lcc_datos;

		ldm_manager     = DaoManagerFactory.getDAOManagerCYN();
		lcc_datos       = null;

		try
		{
			lcc_datos = DaoCreator.getCanalDAO(ldm_manager).findAll(ab_accion);
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findAllCanal", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lcc_datos;
	}

	/**
	 * Método para encontrar todos los registros de la base de datos para la tabla
	 * SDB_PGN_CANAL_ORIGEN_SERVICIO.
	 *
	 * @return devuelve el valor de Collection
	 * @throws B2BException Señala que se ha producido una excepción
	 *
	 */
	public synchronized Collection<CanalOrigenServicio> findAllCanalOrigenServicio()
	    throws B2BException
	{
		DAOManager                      ldm_manager;
		Collection<CanalOrigenServicio> lcos_datos;

		ldm_manager     = DaoManagerFactory.getDAOManagerNPA();
		lcos_datos      = null;

		try
		{
			lcos_datos = DaoCreator.getCanalOrigenServicioDAO(ldm_manager).findAll();
		}

		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findAllCanalOrigenServicio", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lcos_datos;
	}

	/**
	 * Método para encontrar todos los registros de la base de datos para la tabla
	 * SDB_PGN_CATASTRO.
	 *
	 * @return devuelve el valor de Collection
	 * @throws B2BException Señala que se ha producido una excepción
	 *
	 */
	public synchronized Collection<Catastro> findAllCatastro()
	    throws B2BException
	{
		DAOManager           ldm_manager;
		Collection<Catastro> lcc_datos;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		lcc_datos       = null;

		try
		{
			lcc_datos = DaoCreator.getCatastroDAO(ldm_manager).findAll();
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findAllCatastro", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lcc_datos;
	}

	/**
	 * Método para encontrar todos los registros de la base de datos para la tabla SDB_PGN_CAUSAL_APROBACION_DEVOLUCION.
	 *
	 * @return devuelve el valor de Collection
	 * @throws B2BException Señala que se ha producido una excepción
	 *
	 */
	public synchronized Collection<CausalAprobacionDevolucion> findAllCausalAprobacionDevolucion()
	    throws B2BException
	{
		DAOManager                             ldm_manager;
		Collection<CausalAprobacionDevolucion> lcr_datos;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		lcr_datos       = null;

		try
		{
			lcr_datos = DaoCreator.getCausalAprobacionDevolucionDAO(ldm_manager).findAll(false);
		}

		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findAllCausalAprobacionDevolucion", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lcr_datos;
	}

	/**
	 * Método para encontrar todos los registros de la base de datos para la tabla SDB_PGN_CAUSAL_CORRECCION.
	 *
	 * @return devuelve el valor de Collection
	 * @throws B2BException Señala que se ha producido una excepción
	 *
	 */
	public synchronized Collection<CausalCorreccion> findAllCausalCorreccion()
	    throws B2BException
	{
		DAOManager                   ldm_manager;
		Collection<CausalCorreccion> lcr_datos;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		lcr_datos       = null;

		try
		{
			lcr_datos = DaoCreator.getCausalCorreccionDAO(ldm_manager).findAll();
		}

		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findAllCausalCorreccion", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lcr_datos;
	}

	/**
	 * Método para consultar todos los causaler de mayor valor.
	 *
	 * @return una coleccion de causalMayorValor con todos los registros
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public synchronized Collection<CausalMayorValor> findAllCausalMayorValor()
	    throws B2BException
	{
		DAOManager                   ldm_manager;
		Collection<CausalMayorValor> lccmv_datos;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		lccmv_datos     = null;

		try
		{
			lccmv_datos = DaoCreator.getCausalMayorValorDAO(ldm_manager).findAll();
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findAllCausalMayor", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lccmv_datos;
	}

	/**
	 * Método para encontrar a todo causalMayorValor correspondiente a su ID.
	 *
	 * @param as_idCausalMayorValor de as id causal mayor valor
	 * @return CausalMayorValor con el registro solicitado
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public synchronized CausalMayorValor findAllCausalMayorValorById(long as_idCausalMayorValor)
	    throws B2BException
	{
		DAOManager       ldm_manager;
		CausalMayorValor lcc_datos;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		lcc_datos       = null;

		try
		{
			lcc_datos = DaoCreator.getCausalMayorValorDAO(ldm_manager).findById(as_idCausalMayorValor);
		}

		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findAllCausalMayorValorById", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lcc_datos;
	}

	/**
	 * Método de transaciones con la base de datos con el fin de encontar todos los registros
	 * de la tabla SDB_PGN_CAUSAL_NEGACION_COPIAS.
	 *
	 * @return devuelve el valor de Collection
	 * @throws B2BException Señala que se ha producido una excepción
	 *
	 */
	public synchronized Collection<CausalNegacionCopias> findAllCausalNegacionCopias()
	    throws B2BException
	{
		DAOManager                       ldm_manager;
		Collection<CausalNegacionCopias> lcr_datos;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		lcr_datos       = null;

		try
		{
			lcr_datos = DaoCreator.getCausalNegacionCopiasDAO(ldm_manager).findAll();
		}

		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findAllCausalNegacionCopias", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lcr_datos;
	}

	/**
	 * Método para consultar todos los causal negacion copias correspondiente a el ID.
	 *
	 * @param as_idCausalNegacion de as id causal negacion
	 * @return el valor de causal negacion copias
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public synchronized CausalNegacionCopias findAllCausalNegacionCopiasById(String as_idCausalNegacion)
	    throws B2BException
	{
		DAOManager           ldm_manager;
		CausalNegacionCopias lcc_datos;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		lcc_datos       = null;

		try
		{
			if(StringUtils.isValidString(as_idCausalNegacion))
				lcc_datos = DaoCreator.getCausalNegacionCopiasDAO(ldm_manager).findById(as_idCausalNegacion);
		}

		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findAllCausalNegacionCopiasById", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lcc_datos;
	}

	/**
	 * Método para encontrar todos los registros de la base de datos para la tabla SDB_ACC_ENTIDAD_EXTERNA.
	 *
	 * @return devuelve el valor de Collection
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public synchronized Collection<CausalRechazoRecurso> findAllCausalRechazoRecurso()
	    throws B2BException
	{
		DAOManager                       ldm_manager;
		Collection<CausalRechazoRecurso> lcrr_datos;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		lcrr_datos      = null;

		try
		{
			lcrr_datos = DaoCreator.getCausalRechazoRecursoDAO(ldm_manager).findAll();
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findAllCausalRechazoRecurso", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lcrr_datos;
	}

	/**
	 * Método para encontrar todos los registros de la base de datos para la tabla
	 * SDB_PGN_CAUSAL_REIMPRESION.
	 *
	 * @return el valor de collection
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public synchronized Collection<CausalReimpresion> findAllCausalReimpresion()
	    throws B2BException
	{
		DAOManager                    ldm_manager;
		Collection<CausalReimpresion> lccr_datos;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		lccr_datos      = null;

		try
		{
			lccr_datos = DaoCreator.getCausalReimpresionDAO(ldm_manager).findAll();
		}

		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findAllCausalReimpresion", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lccr_datos;
	}

	/**
	 * Método para encontrar todos los registros de la base de datos para la tabla
	 * SDB_PGN_CIRCULO_ACT_ADMIN.
	 *
	 * @return devuelve el valor de Collection
	 * @throws B2BException Señala que se ha producido una excepción
	 *
	 */
	public synchronized Collection<CirculoActAdmin> findAllCirculoActAdmin()
	    throws B2BException
	{
		DAOManager                  ldm_manager;
		Collection<CirculoActAdmin> lccaa_datos;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		lccaa_datos     = null;

		try
		{
			lccaa_datos = DaoCreator.getCirculoActAdminDAO(ldm_manager).findAll();
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findAllCirculoActAdmin", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lccaa_datos;
	}

	/**
	 * Método para encontrar todos los registros de la base de datos para la tabla
	 * SDB_PGN_CIRCULO_CATASTRO.
	 *
	 * @return devuelve el valor de Collection
	 * @throws B2BException Señala que se ha producido una excepción
	 *
	 */
	public synchronized Collection<CirculoCatastro> findAllCirculoCatastro()
	    throws B2BException
	{
		DAOManager                  ldm_manager;
		Collection<CirculoCatastro> lccc_datos;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		lccc_datos      = null;

		try
		{
			lccc_datos = DaoCreator.getCirculoCatastroDAO(ldm_manager).findAll();
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findAllCirculoCatastro", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lccc_datos;
	}

	/**
	 * Retorna el valor del objeto de Collection.
	 *
	 * @return devuelve el valor de Collection
	 * @throws B2BException Señala que se ha producido una excepción
	 *
	 */
	public synchronized Collection<CirculoFestivo> findAllCirculoFestivo()
	    throws B2BException
	{
		DAOManager                 ldm_manager;
		Collection<CirculoFestivo> lcr_datos;
		CirculoRegistralDao        lcrd_circuloRegistralDAO;

		ldm_manager                  = DaoManagerFactory.getDAOManager();
		lcrd_circuloRegistralDAO     = DaoCreator.getCirculoRegistralDAO(ldm_manager);
		lcr_datos                    = null;

		try
		{
			lcr_datos = DaoCreator.getCirculoFestivoDAO(ldm_manager).findAll(false);

			if(CollectionUtils.isValidCollection(lcr_datos))
			{
				for(CirculoFestivo lc_temp : lcr_datos)
				{
					if(lc_temp != null)
					{
						String ls_idCirculo;

						ls_idCirculo = lc_temp.getIdCirculo();

						if(StringUtils.isValidString(ls_idCirculo))
						{
							CirculoRegistral lcr_circulo;

							lcr_circulo = lcrd_circuloRegistralDAO.findById(ls_idCirculo);

							if(lcr_circulo != null)
								lc_temp.setNombreCirculo(lcr_circulo.getNombre());
						}
					}
				}
			}
		}

		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findAllCirculoFestivo", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lcr_datos;
	}

	/**
	 * Método para encontrar todos los registros de la base de datos para la tabla
	 * SDB_PNG_CIRCULO_ORIGEN_DESTINO.
	 *
	 * @return el valor de collection
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public synchronized Collection<CirculoOrigenDestino> findAllCirculoOrigenDestino()
	    throws B2BException
	{
		DAOManager                       ldm_manager;
		Collection<CirculoOrigenDestino> lccod_datos;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		lccod_datos     = null;

		try
		{
			lccod_datos = DaoCreator.getCirculoOrigenDestinoDAO(ldm_manager).findAll(false);
		}

		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findAllCirculoOrigenDestino", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lccod_datos;
	}

	/**
	 * Método para realizar transaccciones con la base de datos para encontrar todos los registros que se encuentren
	 * activos.
	 *
	 * @param ab_b indica si se debe encontrar solo los circulos activos o todos los registros
	 * @return devuelve el valor de Collection
	 * @throws B2BException Señala que se ha producido una excepción
	 *
	 */
	public synchronized Collection<CirculoRegistral> findAllCirculos(boolean ab_b)
	    throws B2BException
	{
		DAOManager                   ldm_manager;
		Collection<CirculoRegistral> lcc_datos;
		Collection<CirculoRegistral> lcc_return;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		lcc_return      = new LinkedList<CirculoRegistral>();
		lcc_datos       = null;

		try
		{
			if(ab_b)
				lcc_datos = DaoCreator.getCirculoRegistralDAO(ldm_manager).findAllActivo(ab_b);
			else
				lcc_datos = DaoCreator.getCirculoRegistralDAO(ldm_manager).findAllCR();

			if(!CollectionUtils.isValidCollection(lcc_datos))
				lcc_datos = new LinkedList<CirculoRegistral>();

			OficinaOrigenDAO loo_DAO;
			loo_DAO = DaoCreator.getOficinaOrigenDAO(ldm_manager);

			DepartamentoDAO ld_DAO;
			ld_DAO = DaoCreator.getDepartamentoDAO(ldm_manager);

			MunicipioDAO lm_DAO;
			lm_DAO = DaoCreator.getMunicipioDAO(ldm_manager);

			for(CirculoRegistral lcr_circulo : lcc_datos)
			{
				if(lcr_circulo != null)
				{
					OficinaOrigen loo_oficina;

					loo_oficina = new OficinaOrigen();

					loo_oficina.setIdOficinaOrigen(lcr_circulo.getIdOficinaOrigen());
					loo_oficina.setVersion(lcr_circulo.getVersion());

					loo_oficina = loo_DAO.findById(loo_oficina);

					if(loo_oficina == null)
						loo_oficina = new OficinaOrigen();

					String ls_idPais;
					String ls_idDepartamento;
					String ls_idMunicipio;

					ls_idPais             = loo_oficina.getIdPais();
					ls_idDepartamento     = loo_oficina.getIdDepartamento();
					ls_idMunicipio        = loo_oficina.getIdMunicipio();

					Departamento ld_departamento;
					ld_departamento       = new Departamento();

					ld_departamento.setIdPais(ls_idPais);
					ld_departamento.setIdDepartamento(ls_idDepartamento);
					ld_departamento = ld_DAO.findById(ld_departamento);

					if(ld_departamento == null)
						ld_departamento = new Departamento();

					Municipio lm_municipio;
					lm_municipio = new Municipio();

					lm_municipio.setIdPais(ls_idPais);
					lm_municipio.setIdDepartamento(ls_idDepartamento);
					lm_municipio.setIdMunicipio(ls_idMunicipio);
					lm_municipio = lm_DAO.findById(lm_municipio);

					if(lm_municipio == null)
						lm_municipio = new Municipio();

					String ls_depMun;
					ls_depMun = "";

					String ls_nombreDepartamento;
					String ls_nombreMunicipio;

					ls_nombreDepartamento     = ld_departamento.getNombre();
					ls_nombreMunicipio        = lm_municipio.getNombre();

					if(
					    StringUtils.isValidString(ls_nombreDepartamento)
						    || StringUtils.isValidString(ls_nombreMunicipio)
					)
						ls_depMun = ls_nombreDepartamento + ", " + ls_nombreMunicipio;

					lcr_circulo.setNombreOficinaOrigen(ls_depMun);

					lcc_return.add(lcr_circulo);
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findAllCirculos", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lcc_return;
	}

	/**
	 * Método para la transaccion con la base de datos para traer todos los registros existentes
	 * en la tabla SDB_AUT_COMPONENTE.
	 *
	 * @return el valor de collection
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public synchronized Collection<Componente> findAllComponenteActivo()
	    throws B2BException
	{
		DAOManager             ldm_manager;
		Collection<Componente> lcc_datos;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		lcc_datos       = null;

		try
		{
			lcc_datos = DaoCreator.getComponentesDAO(ldm_manager).findAllActivo();
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findAllComponenteActivo", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lcc_datos;
	}

	/**
	 * Método para encontrar todos los registros de la base de datos para la tabla
	 * SDB_PNG_COMUNIDADES_ETNICAS.
	 *
	 * @return devuelve el valor de Collection
	 * @throws B2BException Señala que se ha producido una excepción
	 *
	 */
	public synchronized Collection<ComunidadesEtnicas> findAllComunidadesEtnicas()
	    throws B2BException
	{
		DAOManager                     ldm_manager;
		Collection<ComunidadesEtnicas> lcce_datos;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		lcce_datos      = null;

		try
		{
			lcce_datos = DaoCreator.getComunidadesEtnicasDAO(ldm_manager).findAll();
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findAllComunidadesEtnicas", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lcce_datos;
	}

	/**
	 * Retorna el valor del objeto de Collection.
	 *
	 * @return devuelve el valor de Collection
	 * @throws B2BException Señala que se ha producido una excepción
	 *
	 */
	public synchronized Collection<CondicionTarifa> findAllCondicionTarifa()
	    throws B2BException
	{
		DAOManager                  ldm_manager;
		Collection<CondicionTarifa> lcr_datos;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		lcr_datos       = null;

		try
		{
			lcr_datos = DaoCreator.getCondicionTarifaDAO(ldm_manager).findAll(false);
		}

		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findAllCondicionTarifa", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lcr_datos;
	}

	/**
	 * Método para realizar transacciones para encontrar todos los registros de constantes.
	 *
	 * @return devuelve el valor de Collection
	 * @throws B2BException Señala que se ha producido una excepción
	 *
	 */
	public synchronized Collection<Constantes> findAllConstants()
	    throws B2BException
	{
		DAOManager             ldm_manager;
		Collection<Constantes> lcc_datos;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		lcc_datos       = null;

		try
		{
			lcc_datos = DaoCreator.getConstantesDAO(ldm_manager).findAll();
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findAllConstants", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lcc_datos;
	}

	/**
	 * Método para realizar transacciones para encontrar todos los registros de constantes.
	 *
	 * @return devuelve el valor de Collection
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public synchronized Collection<Constantes> findAllConstantsCYN()
	    throws B2BException
	{
		DAOManager             ldm_manager;
		Collection<Constantes> lcc_datos;

		ldm_manager     = DaoManagerFactory.getDAOManagerCYN();
		lcc_datos       = null;

		try
		{
			lcc_datos = DaoCreator.getConstantesDAO(ldm_manager).findAll();
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findAllConstantsCYN", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lcc_datos;
	}

	/**
	 * Retorna el valor del objeto de Collection.
	 *
	 * @return devuelve el valor de Collection
	 * @throws B2BException Señala que se ha producido una excepción
	 *
	 */
	public synchronized Collection<Consultas> findAllConsultas()
	    throws B2BException
	{
		DAOManager            ldm_manager;
		Collection<Consultas> lcr_datos;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		lcr_datos       = null;

		try
		{
			lcr_datos = DaoCreator.getConsultasPgnDAO(ldm_manager).findAll();
		}

		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findAllConsultas", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lcr_datos;
	}

	/**
	 * Método para encontrar todos los registros de la base de datos para la tabla
	 * SDB_PNG_COORDENADA.
	 *
	 * @return el valor de collection
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public synchronized Collection<Coordenada> findAllCoordenada()
	    throws B2BException
	{
		DAOManager             ldm_manager;
		Collection<Coordenada> lcc_datos;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		lcc_datos       = null;

		try
		{
			lcc_datos = DaoCreator.getCoordenadaDAO(ldm_manager).findAll();
		}

		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findAllCoordenada", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lcc_datos;
	}

	/**
	 * Método para la transaccion con la base de datos para traer todos los registros existentes
	 * en la tabla SDB_PGN_COORDENADA.
	 *
	 * @return el valor de collection
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public synchronized Collection<Coordenada> findAllCoordenadaActivo()
	    throws B2BException
	{
		DAOManager             ldm_manager;
		Collection<Coordenada> lcc_datos;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		lcc_datos       = null;

		try
		{
			lcc_datos = DaoCreator.getCoordenadaDAO(ldm_manager).findAllActivo();
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findAllCoordenadaActivo", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lcc_datos;
	}

	/**
	 * Método para la transaccion con la base de datos para traer todos los registros existentes
	 * en la tabla SDB_PGN_DEPARTAMENTO.
	 *
	 * @return devuelve el valor de Collection
	 * @throws B2BException Señala que se ha producido una excepción
	 *
	 */
	public synchronized Collection<Departamento> findAllDepartamentos()
	    throws B2BException
	{
		DAOManager               ldm_manager;
		Collection<Departamento> lcd_datos;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		lcd_datos       = null;

		try
		{
			lcd_datos = DaoCreator.getDepartamentoDAO(ldm_manager).findAllD();

			if(CollectionUtils.isValidCollection(lcd_datos))
			{
				Collection<Departamento> lcd_departamentosCol;
				Collection<Departamento> lcd_departamentosMun;

				lcd_departamentosCol     = new ArrayList<Departamento>();
				lcd_departamentosMun     = new ArrayList<Departamento>();

				for(Departamento lc_temp : lcd_datos)
				{
					if(lc_temp != null)
					{
						String ls_idPais;

						ls_idPais = lc_temp.getIdPais();

						if(StringUtils.isValidString(ls_idPais))
						{
							if(ls_idPais.equalsIgnoreCase(IdentificadoresCommon.INDICATIVO_PAIS_DEFAULT))
								lcd_departamentosCol.add(lc_temp);
							else
								lcd_departamentosMun.add(lc_temp);
						}
					}
				}

				if(
				    CollectionUtils.isValidCollection(lcd_departamentosCol)
					    && CollectionUtils.isValidCollection(lcd_departamentosMun)
				)
				{
					for(Departamento ld_temp : lcd_departamentosMun)
					{
						if(ld_temp != null)
							lcd_departamentosCol.add(ld_temp);
					}

					lcd_datos = lcd_departamentosCol;
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findAllDepartamentos", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lcd_datos;
	}

	/**
	 * Método para encontrar todos los registros de la base de datos para la tabla
	 * SDB_PGN_DEPENDENCIA_SNR.
	 *
	 * @return devuelve el valor de Collection
	 * @throws B2BException Señala que se ha producido una excepción
	 *
	 */
	public synchronized Collection<DependenciaSNR> findAllDependenciaSNR()
	    throws B2BException
	{
		DAOManager                 ldm_manager;
		Collection<DependenciaSNR> lcdsnr_datos;

		ldm_manager      = DaoManagerFactory.getDAOManager();
		lcdsnr_datos     = null;

		try
		{
			lcdsnr_datos = DaoCreator.getDependenciaSNRDAO(ldm_manager).findAll();
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findAllDependenciaSNR", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lcdsnr_datos;
	}

	/**
	 * Método para la transaccion con la base de datos para traer todos los registros existentes
	 * en la tabla SDB_ACC_DESBORDE.
	 *
	 * @return devuelve el valor de Collection
	 * @throws B2BException Señala que se ha producido una excepción
	 *
	 */
	public synchronized Collection<Desborde> findAllDesbordes()
	    throws B2BException
	{
		DAOManager           ldm_manager;
		Collection<Desborde> lcc_return;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		lcc_return      = null;

		try
		{
			lcc_return = DaoCreator.getDesbordeDAO(ldm_manager).findAll();
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findAllDesbordes", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lcc_return;
	}

	/**
	 * Método para encontrar todos los registros de la base de datos para la tabla
	 * SDB_PGN_DETALLE_PROCESO_CONSULTA.
	 *
	 * @return el valor de collection
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public synchronized Collection<DetalleProcesoConsulta> findAllDetalleProcesoConsulta()
	    throws B2BException
	{
		DAOManager                         ldm_manager;
		Collection<DetalleProcesoConsulta> lcutv_datos;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		lcutv_datos     = null;

		try
		{
			lcutv_datos = DaoCreator.getDetalleProcesoConsultaDAO(ldm_manager).findAll();
		}

		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findAllDetalleProcesoConsulta", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lcutv_datos;
	}

	/**
	 * Método para la transaccion con la base de datos para traer todos los registros existentes
	 * en la tabla SDB_COL_DOMINIO_RRR.
	 *
	 * @return devuelve el valor de Collection
	 * @throws B2BException Señala que se ha producido una excepción
	 *
	 */
	public synchronized Collection<DominioRrr> findAllDominioRRR()
	    throws B2BException
	{
		DAOManager             ldm_manager;
		Collection<DominioRrr> lcc_datos;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		lcc_datos       = null;

		try
		{
			lcc_datos = DaoCreator.getDominioRrrDAO(ldm_manager).findAll();
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findAllDominioRRR", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lcc_datos;
	}

	/**
	 * Método de transacciones con la base de datos para encontrar todos los registros encontrados en la tabla SDB_PNG_DOMINIO_NATURALEZA_JURIDICA.
	 *
	 * @return devuelve el valor de Collection
	 * @throws B2BException Señala que se ha producido una excepción
	 *
	 */
	public synchronized Collection<DominioNaturalezaJuridica> findAllDominiosNaturalezaJuridica()
	    throws B2BException
	{
		DAOManager                            ldm_manager;
		Collection<DominioNaturalezaJuridica> lcc_return;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		lcc_return      = null;

		try
		{
			lcc_return = DaoCreator.getDominioNaturalezaJuridicaDAO(ldm_manager).findAll();
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findAllDominiosNaturalezaJuridica", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lcc_return;
	}

	/**
	 * Método para encontrar todos los registros de la base de datos para la tabla
	 * SDB_PGN_ENTIDAD_RECAUDADORA.
	 *
	 * @return devuelve el valor de Collection
	 * @throws B2BException Señala que se ha producido una excepción
	 *
	 */
	public synchronized Collection<EntidadRecaudadora> findAllEntidadRecaudadora()
	    throws B2BException
	{
		DAOManager                     ldm_manager;
		Collection<EntidadRecaudadora> lcer_datos;

		ldm_manager     = DaoManagerFactory.getDAOManagerNPA();
		lcer_datos      = null;

		try
		{
			lcer_datos = DaoCreator.getEntidadRecaudadoraDAO(ldm_manager).findAll();
		}

		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findAllEntidadRecaudadora", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lcer_datos;
	}

	/**
	 * Método para encontrar todos los registros de la base de datos para la tabla
	 * SDB_PNG_ENTIDADES_ALERTA.
	 *
	 * @return devuelve el valor de Collection
	 * @throws B2BException Señala que se ha producido una excepción
	 *
	 */
	public synchronized Collection<EntidadesAlerta> findAllEntidadesAlerta()
	    throws B2BException
	{
		DAOManager                  ldm_manager;
		Collection<EntidadesAlerta> lcea_datos;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		lcea_datos      = null;

		try
		{
			lcea_datos = DaoCreator.getEntidadesAlertaDAO(ldm_manager).findAll();
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findAllEntidadesAlerta", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lcea_datos;
	}

	/**
	 * Método para la transaccion con la base de datos para traer todos los registros existentes
	 * en la tabla SDB_PGN_ESTADO_ACTIVIDAD.
	 *
	 * @param ab_b correspondiente al valor del tipo de objeto boolean
	 * @return devuelve el valor de Collection
	 * @throws B2BException Señala que se ha producido una excepción
	 *
	 */
	public synchronized Collection<EstadoActividad> findAllEstadoActividad(boolean ab_b)
	    throws B2BException
	{
		DAOManager                  ldm_manager;
		Collection<EstadoActividad> lcc_datos;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		lcc_datos       = null;

		try
		{
			lcc_datos = DaoCreator.getEstadoActividadDAO(ldm_manager).findAll(ab_b);
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findAllProcesosAutomaticos", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lcc_datos;
	}

	/**
	 * Método para encontrar todos los registros de la base de datos para la tabla
	 * SDB_PNG_ESTADO_ANOTACION.
	 *
	 * @return devuelve el valor de Collection
	 * @throws B2BException Señala que se ha producido una excepción
	 *
	 */
	public synchronized Collection<EstadoAnotacion> findAllEstadoAnotacion()
	    throws B2BException
	{
		DAOManager                  ldm_manager;
		Collection<EstadoAnotacion> lea_datos;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		lea_datos       = null;

		try
		{
			lea_datos = DaoCreator.getEstadoAnotacionDAO(ldm_manager).findAll();
		}

		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findAllEstadoAnotacion", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lea_datos;
	}

	/**
	 * Método para encontrar todos los registros de la base de datos para la tabla
	 * SDB_PNG_ESTADO_NUPRE.
	 *
	 * @return devuelve el valor de Collection
	 * @throws B2BException Señala que se ha producido una excepción
	 *
	 */
	public synchronized Collection<EstadoNupre> findAllEstadoNupre()
	    throws B2BException
	{
		DAOManager              ldm_manager;
		Collection<EstadoNupre> len_datos;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		len_datos       = null;

		try
		{
			len_datos = DaoCreator.getEstadoNupreDAO(ldm_manager).findAll();
		}

		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findAllEstadoNupre", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return len_datos;
	}

	/**
	 * Método para encontrar todos los registros de la base de datos para la tabla
	 * SDB_PNG_ESTADO_PREDIO.
	 *
	 * @return devuelve el valor de Collection
	 * @throws B2BException Señala que se ha producido una excepción
	 *
	 */
	public synchronized Collection<EstadoPredio> findAllEstadoPredio()
	    throws B2BException
	{
		DAOManager               ldm_manager;
		Collection<EstadoPredio> lcep_datos;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		lcep_datos      = null;

		try
		{
			lcep_datos = DaoCreator.getEstadoPredioDao(ldm_manager).findAll();
		}

		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findAllEstadoPredio", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lcep_datos;
	}

	/**
	 * Método para encontrar todos los registros de la base de datos para la tabla
	 * SDB_PNG_ESTADO_REGISTRO.
	 *
	 * @return devuelve el valor de Collection
	 * @throws B2BException Señala que se ha producido una excepción
	 *
	 */
	public synchronized Collection<EstadoRegistro> findAllEstadoRegistro()
	    throws B2BException
	{
		DAOManager                 ldm_manager;
		Collection<EstadoRegistro> ler_datos;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		ler_datos       = null;

		try
		{
			ler_datos = DaoCreator.getEstadoRegistroDAO(ldm_manager).findAll();
		}

		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findAllEstadoRegistro", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return ler_datos;
	}

	/**
	 * Método para encontrar todos los registros de la base de datos para la tabla
	 * SDB_PGN_ESTADOS.
	 *
	 * @return devuelve el valor de Collection
	 * @throws B2BException Señala que se ha producido una excepción
	 *
	 */
	public synchronized Collection<Estados> findAllEstados()
	    throws B2BException
	{
		DAOManager          ldm_manager;
		Collection<Estados> lcc_datos;

		ldm_manager     = DaoManagerFactory.getDAOManagerCYN();
		lcc_datos       = null;

		try
		{
			lcc_datos = DaoCreator.getEstadosDAO(ldm_manager).findAll();
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findAllEstados", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lcc_datos;
	}

	/**
	 * Método para encontrar todos los registros de la base de datos para la tabla
	 * SDB_AUT_OPCION_ETAPA asociados a un idOpcion.
	 *
	 * @param ao_opcion de ao opcion
	 * @return el valor de opcion
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public synchronized Opcion findAllEtapaOpciones(Opcion ao_opcion)
	    throws B2BException
	{
		DAOManager ldm_manager;
		Opcion     lo_datos;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		lo_datos        = null;

		try
		{
			if(ao_opcion != null)
			{
				String         ls_idOpcion;
				OpcionEtapaDAO loed_opcionEtapaDAO;
				EtapaDAO       led_etapaDAO;

				loed_opcionEtapaDAO     = DaoCreator.getOpcionEtapaDAO(ldm_manager);
				led_etapaDAO            = DaoCreator.getEtapaDAO(ldm_manager);
				ls_idOpcion             = ao_opcion.getIdOpcion();

				Collection<Etapa> lce_etapas;
				Collection<Etapa> lce_source;
				Collection<Etapa> lce_target;

				lce_source              = new ArrayList<Etapa>();
				lce_target              = new ArrayList<Etapa>();
				lce_etapas              = led_etapaDAO.findAllActivo(true);

				if(CollectionUtils.isValidCollection(lce_etapas) && StringUtils.isValidString(ls_idOpcion))
				{
					for(Etapa lc_temp : lce_etapas)
					{
						if(lc_temp != null)
						{
							long ls_idEtapa;

							ls_idEtapa = lc_temp.getIdEtapa();

							if(StringUtils.isValidString(ls_idOpcion) && (ls_idEtapa > 0))
							{
								OpcionEtapa loe_opcionEtapa;

								loe_opcionEtapa = loed_opcionEtapaDAO.findOpcionEtapaById(ls_idEtapa, ls_idOpcion);

								if((loe_opcionEtapa != null))
									lce_target.add(lc_temp);
								else
									lce_source.add(lc_temp);
							}
						}
					}

					ao_opcion.setEtapaSource(lce_source);
					ao_opcion.setEtapaTarget(lce_target);
					lo_datos = ao_opcion;
				}
			}
		}

		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findAllEtapaOpciones", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lo_datos;
	}

	/**
	 * Método de transacciones con la base de datos para encontrar todos los registros
	 *  de la base de datos correspondiente a la tabla SDB_PGN_ETAPA.
	 *
	 * @return devuelve el valor de Collection
	 * @throws B2BException Señala que se ha producido una excepción
	 *
	 */
	public synchronized Collection<Etapa> findAllEtapas()
	    throws B2BException
	{
		DAOManager        ldm_manager;
		Collection<Etapa> lce_return;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		lce_return      = new LinkedList<Etapa>();

		try
		{
			lce_return = DaoCreator.getEtapaDAO(ldm_manager).findAllEtapas();
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findAllEtapas", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lce_return;
	}

	/**
	 * Método de transacciones con la base de datos para traer todos los registros de la tabla SDB_PGN_FASES.
	 *
	 * @return devuelve el valor de Collection
	 * @throws B2BException Señala que se ha producido una excepción
	 *
	 */
	public synchronized Collection<Fases> findAllFases()
	    throws B2BException
	{
		DAOManager        ldm_manager;
		Collection<Fases> lcf_return;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		lcf_return      = null;

		try
		{
			lcf_return = DaoCreator.getFasesDAO(ldm_manager).findAll();
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findAllFases", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lcf_return;
	}

	/**
	 * Método para la transaccion con la base de datos para traer todos los registros existentes
	 * en la tabla SDB_PGN_FESTIVO_NACIONAL.
	 *
	 * @param ab_b correspondiente al valor del tipo de objeto boolean
	 * @return devuelve el valor de Collection
	 * @throws B2BException Señala que se ha producido una excepción
	 *
	 */
	public synchronized Collection<FestivoNacional> findAllFestivoNacional(boolean ab_b)
	    throws B2BException
	{
		DAOManager                  ldm_manager;
		Collection<FestivoNacional> lcc_datos;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		lcc_datos       = null;

		try
		{
			lcc_datos = DaoCreator.getFestivoNacionalDAO(ldm_manager).findAll(ab_b);
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findAllFestivoNacional", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lcc_datos;
	}

	/**
	 * Método para la transaccion con la base de datos para traer todos los registros existentes
	 * en la tabla SDB_PGN_GOBERNACION.
	 *
	 * @param ab_b correspondiente al valor del tipo de objeto boolean
	 * @return devuelve el valor de Collection
	 * @throws B2BException Señala que se ha producido una excepción
	 *
	 */
	public synchronized Collection<Gobernacion> findAllGobernacion(boolean ab_b)
	    throws B2BException
	{
		DAOManager              ldm_manager;
		Collection<Gobernacion> lcg_datos;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		lcg_datos       = null;

		try
		{
			lcg_datos = DaoCreator.getGobernacionDAO(ldm_manager).findAll(ab_b);
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findAllGobernacion", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lcg_datos;
	}

	/**
	 * Método para encontrar todos los registros de la base de datos para la tabla
	 * SDB_PNG_GRUPO_NATURALEZA_JURIDICA.
	 *
	 * @return devuelve el valor de Collection
	 * @throws B2BException Señala que se ha producido una excepción
	 *
	 */
	public synchronized Collection<GrupoNaturalezaJuridica> findAllGrupoNaturalezaJuridica()
	    throws B2BException
	{
		DAOManager                          ldm_manager;
		Collection<GrupoNaturalezaJuridica> lgnj_datos;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		lgnj_datos      = null;

		try
		{
			lgnj_datos = DaoCreator.getGrupoNaturalezaJuridicaDAO(ldm_manager).findAll();
		}

		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findAllGrupoNaturalezaJuridica", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lgnj_datos;
	}

	/**
	 * Método para la transaccion con la base de datos para traer todos los registros existentes
	 * en la tabla SDB_PGN_INSTANCIA_CONSULTA.
	 *
	 * @param ab_b correspondiente al valor del tipo de objeto boolean
	 * @return devuelve el valor de Collection
	 * @throws B2BException Señala que se ha producido una excepción
	 *
	 */
	public synchronized Collection<InstanciaConsulta> findAllInstanciaConsulta(boolean ab_b)
	    throws B2BException
	{
		DAOManager                    ldm_manager;
		Collection<InstanciaConsulta> lcc_datos;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		lcc_datos       = null;

		try
		{
			lcc_datos = DaoCreator.getInstanciaConsultaDAO(ldm_manager).findAll(ab_b);
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findAllInstanciaConsulta", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lcc_datos;
	}

	/**
	 * Método de transacciones con la base de datos para traer todos los registros de la tabla SDB_COL_INTERESADO_DOCUMENTO_TIPO.
	 *
	 * @return devuelve el valor de Collection
	 * @throws B2BException Señala que se ha producido una excepción
	 *
	 */
	public synchronized Collection<InteresadoDocumentoTipo> findAllInteresadoDocumentoTipos()
	    throws B2BException
	{
		DAOManager                          ldm_manager;
		Collection<InteresadoDocumentoTipo> lcc_return;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		lcc_return      = null;

		try
		{
			lcc_return = DaoCreator.getInteresadoDocumentoTipoDAO(ldm_manager).findAll();
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findAllInteresadoDocumentoTipos", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lcc_return;
	}

	/**
	 * Método de transaciones con la base de datos con el fin de encontar todos los registros
	 * de la tabla SDB_COL_INTERESADO_NATURAL_GENERO.
	 *
	 * @return devuelve el valor de Collection
	 * @throws B2BException Señala que se ha producido una excepción
	 *
	 */
	public synchronized Collection<InteresadoNaturalGenero> findAllInteresadoNaturalGenero()
	    throws B2BException
	{
		DAOManager                          ldm_manager;
		Collection<InteresadoNaturalGenero> lcing_datos;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		lcing_datos     = null;

		try
		{
			lcing_datos = DaoCreator.getInteresadoNaturalGeneroDAO(ldm_manager).findAll(false);
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findAllInteresadoNaturalGenero", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lcing_datos;
	}

	/**
	 * Método para encontrar todos los registros de la base de datos para la tabla
	 * SDB_PGN_LETRA_EJE.
	 *
	 * @return el valor de collection
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public synchronized Collection<LetraEje> findAllLetraEje()
	    throws B2BException
	{
		DAOManager           ldm_manager;
		Collection<LetraEje> lcle_datos;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		lcle_datos      = null;

		try
		{
			lcle_datos = DaoCreator.getLetraEjeDAO(ldm_manager).findAll();
		}

		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findAllLetraEje", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lcle_datos;
	}

	/**
	 * Método para la transaccion con la base de datos para traer todos los registros existentes
	 * en la tabla SDB_PGN_LETRA_EJE.
	 *
	 * @return el valor de collection
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public synchronized Collection<LetraEje> findAllLetraEjeActivo()
	    throws B2BException
	{
		DAOManager           ldm_manager;
		Collection<LetraEje> lcle_datos;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		lcle_datos      = null;

		try
		{
			lcle_datos = DaoCreator.getLetraEjeDAO(ldm_manager).findAllActivo();
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findAllLetraEjeActivo", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lcle_datos;
	}

	/**
	 * Método para la transaccion con la base de datos para traer todos los registros existentes
	 * en la tabla SDB_PGN_LIBRO_ANT_SISTEMA.
	 *
	 * @param ab_b correspondiente al valor del tipo de objeto boolean
	 * @return devuelve el valor de Collection
	 * @throws B2BException Señala que se ha producido una excepción
	 *
	 */
	public synchronized Collection<LibroAntiguoSistema> findAllLibroAntiguoSistema(boolean ab_b)
	    throws B2BException
	{
		DAOManager                      ldm_manager;
		Collection<LibroAntiguoSistema> lcc_datos;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		lcc_datos       = null;

		try
		{
			lcc_datos = DaoCreator.getLibroAntiguoSistemaDAO(ldm_manager).findAll(ab_b);
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findAllLibroAntiguoSistema", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lcc_datos;
	}

	/**
	 * Método para encontrar todos los registros de la base de datos para la tabla
	 * SDB_PGN_LIBRO_TESTAMENTO.
	 *
	 * @return devuelve el valor de Collection
	 * @throws B2BException Señala que se ha producido una excepción
	 *
	 */
	public synchronized Collection<LibroTestamento> findAllLibroTestamento()
	    throws B2BException
	{
		DAOManager                  ldm_manager;
		Collection<LibroTestamento> lclt_datos;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		lclt_datos      = null;

		try
		{
			lclt_datos = DaoCreator.getLibroTestamentoDAO(ldm_manager).findAll();

			if(CollectionUtils.isValidCollection(lclt_datos))
			{
				CirculoRegistralDao    lcrd_circuloRegistralDAO;
				LibroAntiguoSistemaDao llasd_libroAntiguoSistemaDAO;

				lcrd_circuloRegistralDAO         = DaoCreator.getCirculoRegistralDAO(ldm_manager);
				llasd_libroAntiguoSistemaDAO     = DaoCreator.getLibroAntiguoSistemaDAO(ldm_manager);

				for(LibroTestamento lt_temp : lclt_datos)
				{
					if(lt_temp != null)
					{
						String ls_idCirculo;
						Long   ll_libroAntiguoSistema;

						ls_idCirculo               = lt_temp.getIdCirculo();
						ll_libroAntiguoSistema     = lt_temp.getLibroAntSistema();

						if(StringUtils.isValidString(ls_idCirculo))
						{
							CirculoRegistral lcr_circulo;

							lcr_circulo = lcrd_circuloRegistralDAO.findById(ls_idCirculo);

							if(lcr_circulo != null)
								lt_temp.setNombreCirculo(lcr_circulo.getNombre());
						}

						if(NumericUtils.isValidLong(ll_libroAntiguoSistema))
						{
							LibroAntiguoSistema llas_libro;

							llas_libro = llasd_libroAntiguoSistemaDAO.findById(
								    NumericUtils.getLong(ll_libroAntiguoSistema)
								);

							if(llas_libro != null)
								lt_temp.setNombreLibroAntSistema(llas_libro.getNombre());
						}
					}
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findAllLibroTestamento", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lclt_datos;
	}

	/**
	 * Método de transaciones con la base de datos con el fin de encontar todos los registros
	 * de la tabla SDB_PGN_LINEA_PRODUCCION.
	 *
	 * @return devuelve el valor de Collection
	 * @throws B2BException Señala que se ha producido una excepción
	 *
	 */
	public synchronized Collection<LineaProduccion> findAllLineasProduccion()
	    throws B2BException
	{
		DAOManager                  ldm_manager;
		Collection<LineaProduccion> lclp_datos;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		lclp_datos      = null;

		try
		{
			lclp_datos = DaoCreator.getLineaProduccionDAO(ldm_manager).findAll();
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findAllLineasProduccion", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lclp_datos;
	}

	/**
	 * Método para encontrar todos los registros de la base de datos para la tabla SDB_PGN_MEDIDA_AREA.
	 *
	 * @return devuelve el valor de Collection
	 * @throws B2BException Señala que se ha producido una excepción
	 *
	 */
	public synchronized Collection<MedidaArea> findAllMedidaArea()
	    throws B2BException
	{
		DAOManager             ldm_manager;
		Collection<MedidaArea> lcr_datos;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		lcr_datos       = null;

		try
		{
			lcr_datos = DaoCreator.getMedidaAreaDAO(ldm_manager).findAll(false);
		}

		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findAllMedidaArea", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lcr_datos;
	}

	/**
	 * Método para encontrar todos los registros de la base de datos para la tabla
	 * SDB_PGN_MEDIO_RECAUDO.
	 *
	 * @return devuelve el valor de Collection
	 * @throws B2BException Señala que se ha producido una excepción
	 *
	 */
	public synchronized Collection<MedioRecaudo> findAllMedioRecaudo()
	    throws B2BException
	{
		DAOManager               ldm_manager;
		Collection<MedioRecaudo> lmr_datos;

		ldm_manager     = DaoManagerFactory.getDAOManagerNPA();
		lmr_datos       = null;

		try
		{
			lmr_datos = DaoCreator.getMedioRecaudoDAO(ldm_manager).findAll();
		}

		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findAllMedioRecaudo", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lmr_datos;
	}

	/**
	 * Método para encontrar todos los registros de la base de datos para la tabla
	 * SDB_PGN_MENSAJE_RESPUESTA.
	 *
	 * @return devuelve el valor de Collection
	 * @throws B2BException Señala que se ha producido una excepción
	 *
	 */
	public synchronized Collection<MensajeRespuesta> findAllMensajeRespuesta()
	    throws B2BException
	{
		DAOManager                   ldm_manager;
		Collection<MensajeRespuesta> lmr_datos;

		ldm_manager     = DaoManagerFactory.getDAOManagerNPA();
		lmr_datos       = null;

		try
		{
			lmr_datos = DaoCreator.getMensajeRespuestaDAO(ldm_manager).findAll();
		}

		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findAllMensajeRespuesta", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lmr_datos;
	}

	/**
	 * Método para encontrar todos los registros de la base de datos para la tabla SDB_PGN_MOTIVO_TRAMITE.
	 *
	 * @return devuelve el valor de Collection
	 * @throws B2BException Señala que se ha producido una excepción
	 *
	 */
	public synchronized Collection<MotivoTramite> findAllMotivoTramite()
	    throws B2BException
	{
		DAOManager                ldm_manager;
		Collection<MotivoTramite> lcr_datos;

		ldm_manager     = DaoManagerFactory.getDAOManagerWorkflow();
		lcr_datos       = null;

		try
		{
			lcr_datos = DaoCreator.getMotivoTramiteDAO(ldm_manager).findAll();
		}

		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findAllMotivoTramite", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lcr_datos;
	}

	/**
	 * Método de transaciones con la base de datos con el fin de encontar todos los registros
	 * de la tabla SDB_PGN_MUNICIPIO.
	 *
	 * @return devuelve el valor de Collection
	 * @throws B2BException Señala que se ha producido una excepción
	 *
	 */
	public synchronized Collection<Municipio> findAllMunicipios()
	    throws B2BException
	{
		DAOManager            ldm_manager;
		Collection<Municipio> lcc_datos;
		PaisDAO               lpd_paisDAO;
		DepartamentoDAO       ldd_departamentoDAO;

		ldm_manager             = DaoManagerFactory.getDAOManager();
		ldd_departamentoDAO     = DaoCreator.getDepartamentoDAO(ldm_manager);
		lpd_paisDAO             = DaoCreator.getPaisDAO(ldm_manager);
		lcc_datos               = null;

		try
		{
			lcc_datos = DaoCreator.getMunicipioDAO(ldm_manager).findAll();

			if(CollectionUtils.isValidCollection(lcc_datos))
			{
				for(Municipio lc_temp : lcc_datos)
				{
					if(lc_temp != null)
					{
						String ls_idDepartamento;
						String ls_idPais;

						ls_idDepartamento     = lc_temp.getIdDepartamento();
						ls_idPais             = lc_temp.getIdPais();

						if(StringUtils.isValidString(ls_idPais))
						{
							Pais lp_pais;

							lp_pais = lpd_paisDAO.findById(ls_idPais);

							if(lp_pais != null)
								lc_temp.setNombrePaisPantalla(lp_pais.getNombre());

							if(StringUtils.isValidString(ls_idDepartamento))
							{
								Departamento ld_deparmento;

								ld_deparmento = new Departamento();

								ld_deparmento.setIdDepartamento(ls_idDepartamento);
								ld_deparmento.setIdPais(ls_idPais);

								ld_deparmento = ldd_departamentoDAO.findById(ld_deparmento);

								if(ld_deparmento != null)
									lc_temp.setNombreDepartamentoPantalla(ld_deparmento.getNombre());
							}
						}
					}
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findAllMunicipios", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lcc_datos;
	}

	/**
	 * Método para encontrar todos los registros de la base de datos para la tabla
	 * SDB_PGN_NUMERACION_OFICIOS_CIRCULO.
	 *
	 * @return devuelve el valor de Collection
	 * @throws B2BException Señala que se ha producido una excepción
	 *
	 */
	public synchronized Collection<NumeracionOficiosCirculo> findAllNumeracionOficiosCirculo()
	    throws B2BException
	{
		DAOManager                           ldm_manager;
		Collection<NumeracionOficiosCirculo> lcnoc_datos;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		lcnoc_datos     = null;

		try
		{
			lcnoc_datos = DaoCreator.getNumeracionOficiosCirculoDAO(ldm_manager).findAll();
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findAllNumeracionOficiosCirculo", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lcnoc_datos;
	}

	/**
	 * Método para encontrar todos los registros de la base de datos para la tabla SDB_PGN_OFICINA_ORIGEN.
	 *
	 * @return devuelve el valor de Collection
	 * @throws B2BException Señala que se ha producido una excepción
	 *
	 */
	public synchronized Collection<OficinaOrigen> findAllOficinaOrigen()
	    throws B2BException
	{
		DAOManager                ldm_manager;
		Collection<OficinaOrigen> lcr_datos;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		lcr_datos       = null;

		try
		{
			lcr_datos = DaoCreator.getOficinaOrigenDAO(ldm_manager).findAllOO();
		}

		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findAllOficinaOrigen", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lcr_datos;
	}

	/**
	 * Método de consulta de datos de Opcion.
	 *
	 * @param ab_b de tipo boolean
	 * @return una colección de datos de Opcion
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public synchronized Collection<Opcion> findAllOpcion(boolean ab_b)
	    throws B2BException
	{
		DAOManager         ldm_manager;
		Collection<Opcion> lcos_datos;
		OpcionDAO          lod_opcionDAO;

		ldm_manager       = DaoManagerFactory.getDAOManager();
		lcos_datos        = null;
		lod_opcionDAO     = DaoCreator.getOpcionDAO(ldm_manager);

		try
		{
			lcos_datos = lod_opcionDAO.findAll(true);

			if(CollectionUtils.isValidCollection(lcos_datos) && ab_b)
			{
				for(Opcion lc_temp : lcos_datos)
				{
					if(lc_temp != null)
					{
						Opcion lo_opcionPadre;

						lo_opcionPadre = lod_opcionDAO.findById(lc_temp.getIdOpcionPadre());

						if(lo_opcionPadre != null)
						{
							String ls_nombreOpcionPadre;

							ls_nombreOpcionPadre = lo_opcionPadre.getOpcion();

							if(StringUtils.isValidString(ls_nombreOpcionPadre))
								lc_temp.setNombreOpcionPadre(ls_nombreOpcionPadre);
						}
					}
				}
			}
		}

		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findAllOpcion", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lcos_datos;
	}

	/**
	 * Método de consulta de datos de Opcion.
	 *
	 * @return una colección de datos de Opcion
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public synchronized Collection<Opcion> findAllOpcion()
	    throws B2BException
	{
		DAOManager         ldm_manager;
		Collection<Opcion> lcos_datos;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		lcos_datos      = null;

		try
		{
			lcos_datos = DaoCreator.getOpcionDAO(ldm_manager).findAll();
		}

		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findAllOpcion", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lcos_datos;
	}

	/**
	 * Método para encontrar todos los registros de la base de datos para la tabla SDB_AUT_OPCION_ETAPA.
	 *
	 * @return devuelve el valor de Collection
	 * @throws B2BException Señala que se ha producido una excepción
	 *
	 */
	public synchronized Collection<OpcionEtapa> findAllOpcionEtapa()
	    throws B2BException
	{
		DAOManager              ldm_manager;
		Collection<OpcionEtapa> lcoe_datos;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		lcoe_datos      = null;

		try
		{
			lcoe_datos = DaoCreator.getOpcionEtapaDAO(ldm_manager).findAll();
		}

		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findAllOpcionEtapa", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lcoe_datos;
	}

	/**
	 * Método para encontrar todos los registros de la base de datos para la tabla
	 * SDB_PGN_ORIGEN.
	 *
	 * @return devuelve el valor de Collection
	 * @throws B2BException Señala que se ha producido una excepción
	 *
	 */
	public synchronized Collection<Origen> findAllOrigen()
	    throws B2BException
	{
		DAOManager         ldm_manager;
		Collection<Origen> lco_datos;

		ldm_manager     = DaoManagerFactory.getDAOManagerCYN();
		lco_datos       = null;

		try
		{
			lco_datos = DaoCreator.getOrigenDAO(ldm_manager).findAll();
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findAllOrigen", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lco_datos;
	}

	/**
	 * Método de transaciones con la base de datos con el fin de encontar todos los registros
	 * de la tabla SDB_PGN_PAIS.
	 *
	 * @return devuelve el valor de Collection
	 * @throws B2BException Señala que se ha producido una excepción
	 *
	 */
	public synchronized Collection<Pais> findAllPaises()
	    throws B2BException
	{
		DAOManager       ldm_manager;
		Collection<Pais> lcc_datos;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		lcc_datos       = null;

		try
		{
			lcc_datos = DaoCreator.getPaisDAO(ldm_manager).findAll(false);
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findAllPaises", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lcc_datos;
	}

	/**
	 * Método de transaciones con la base de datos con el fin de encontar todos los registros
	 * de la tabla SDB_COL_PARTE_INTERESADA.
	 *
	 * @return devuelve el valor de Collection
	 * @throws B2BException Señala que se ha producido una excepción
	 *
	 */
	public synchronized Collection<ParteInteresada> findAllParteInteresadas()
	    throws B2BException
	{
		DAOManager                  ldm_manager;
		Collection<ParteInteresada> lcc_datos;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		lcc_datos       = null;

		try
		{
			lcc_datos = DaoCreator.getParteInteresadaDAO(ldm_manager).findAll();
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findAllParteInteresadas", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lcc_datos;
	}

	/**
	 * Método para encontrar todos los registros de la base de datos para la tabla
	 * SDB_PGN_PLANTILLA.
	 *
	 * @return devuelve el valor de Collection
	 * @throws B2BException Señala que se ha producido una excepción
	 *
	 */
	public synchronized Collection<Plantilla> findAllPlantilla()
	    throws B2BException
	{
		DAOManager            ldm_manager;
		Collection<Plantilla> lcp_datos;

		ldm_manager     = DaoManagerFactory.getDAOManagerCYN();
		lcp_datos       = null;

		try
		{
			lcp_datos = DaoCreator.getPlantillaDAO(ldm_manager).findAll();
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findAllPlantilla", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lcp_datos;
	}

	/**
	 * Método para encontrar todos los registros de la base de datos para plantillas comunicación.
	 *
	 * @return Colección de tipo <code>PlantillaComunicacion</code> que contiene los resultados de la busqueda.
	 * @throws B2BException  Objeto de tipo <code>B2BException</code>, se produce cuando se encuentra algun error controlado.
	 */
	public synchronized Collection<PlantillaComunicacion> findAllPlantillaComunicacion()
	    throws B2BException
	{
		DAOManager                        ldm_manager;
		DAOManager                        ldm_managerCYN;
		Collection<PlantillaComunicacion> lcpc_datos;

		ldm_manager        = DaoManagerFactory.getDAOManager();
		ldm_managerCYN     = DaoManagerFactory.getDAOManagerCYN();
		lcpc_datos         = null;

		try
		{
			lcpc_datos = DaoCreator.getPlantillaComunicacionDAO(ldm_manager).findAll();

			if(CollectionUtils.isValidCollection(lcpc_datos))
			{
				ProcesoDAO       lpd_DAO;
				EtapaDAO         led_DAO;
				MotivoTramiteDAO lmtd_DAO;
				PlantillaDAO     lpld_DAO;
				TipoRecepcionDAO ltrd_DAO;
				SubprocesoDAO    lspd_DAO;

				lpd_DAO      = DaoCreator.getProcesoDAO(ldm_manager);
				led_DAO      = DaoCreator.getEtapaDAO(ldm_manager);
				lmtd_DAO     = DaoCreator.getMotivoTramiteDAO(ldm_manager);
				lpld_DAO     = DaoCreator.getPlantillaDAO(ldm_managerCYN);
				ltrd_DAO     = DaoCreator.getTipoRecepcionDAO(ldm_manager);
				lspd_DAO     = DaoCreator.getSubprocesoDAO(ldm_manager);

				for(PlantillaComunicacion lpc_iterador : lcpc_datos)
				{
					if(lpc_iterador != null)
					{
						long   ll_idEtapaAnterior;
						String ls_idProceso;

						ll_idEtapaAnterior     = lpc_iterador.getIdEtapaAnterior();
						ls_idProceso           = lpc_iterador.getIdProceso();

						{
							Plantilla lp_plantilla;

							lp_plantilla = lpld_DAO.findById(lpc_iterador.getIdPlantilla());

							if(lp_plantilla != null)
								lpc_iterador.setNombrePlantilla(lp_plantilla.getComentario());
						}

						{
							Proceso lp_proceso;

							lp_proceso = lpd_DAO.findById(ls_idProceso);

							if(lp_proceso != null)
								lpc_iterador.setNombreProceso(lp_proceso.getNombre());
						}

						if(ll_idEtapaAnterior > 0L)
						{
							Etapa le_etapa;

							le_etapa = led_DAO.findById(ll_idEtapaAnterior);

							if(le_etapa != null)
							{
								StringBuilder lsb_sb;

								lsb_sb = new StringBuilder();

								lsb_sb.append(ll_idEtapaAnterior);
								lsb_sb.append(" - ");
								lsb_sb.append(le_etapa.getNombre());

								lpc_iterador.setNombreEtapa(lsb_sb.toString());
							}
						}

						{
							long ll_idEtapaActual;

							ll_idEtapaActual = lpc_iterador.getIdEtapaActual();

							if(ll_idEtapaActual > 0L)
							{
								Etapa le_etapa;

								le_etapa = led_DAO.findById(ll_idEtapaActual);

								if(le_etapa != null)
									lpc_iterador.setNombreEtapaActual(le_etapa.getNombre());
							}
						}

						{
							MotivoTramite lmt_motivo;
							long          ll_idMotivo;

							ll_idMotivo = lpc_iterador.getIdMotivo();

							if((ll_idEtapaAnterior > 0L) && (ll_idMotivo > 0L))
							{
								lmt_motivo = lmtd_DAO.findById(ll_idEtapaAnterior, ll_idMotivo);

								if(lmt_motivo != null)
								{
									StringBuilder lsb_sb;

									lsb_sb = new StringBuilder();

									lsb_sb.append(ll_idMotivo);
									lsb_sb.append(" - ");
									lsb_sb.append(lmt_motivo.getDescripcion());

									lpc_iterador.setNombreMotivo(lsb_sb.toString());
								}
							}
						}

						{
							TipoRecepcion ltr_autorizacion;

							ltr_autorizacion = ltrd_DAO.findById(lpc_iterador.getIdAutorizacionComunicacion());

							if(ltr_autorizacion != null)
								lpc_iterador.setNombreAutorizacionComunicacion(ltr_autorizacion.getNombre());
						}

						{
							String ls_canal;

							ls_canal = lpc_iterador.getCanal();

							if(StringUtils.isValidString(ls_canal))
							{
								TipoRecepcion ltr_canal;

								ltr_canal = ltrd_DAO.findById(ls_canal);

								if(ltr_canal != null)
									lpc_iterador.setNombreCanal(ltr_canal.getNombre());
							}
						}

						{
							String ls_idSubproceso;

							ls_idSubproceso = lpc_iterador.getIdSubProceso();

							if(StringUtils.isValidString(ls_idSubproceso))
							{
								Subproceso lsp_subproceso;

								lsp_subproceso = lspd_DAO.findById(ls_idProceso, lpc_iterador.getIdSubProceso());

								if(lsp_subproceso != null)
								{
									StringBuilder lsb_sb;

									lsb_sb = new StringBuilder();

									lsb_sb.append(ls_idSubproceso);
									lsb_sb.append(" - ");
									lsb_sb.append(lsp_subproceso.getNombre());

									lpc_iterador.setNombreSubProceso(lsb_sb.toString());
								}
							}
						}

						{
							String ls_activo;

							ls_activo = lpc_iterador.getActivo();

							if(StringUtils.isValidString(ls_activo))
								lpc_iterador.setNombreActivo(ls_activo.contains(EstadoCommon.S));
						}
					}
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();
			ldm_managerCYN.setRollbackOnly();

			clh_LOGGER.error("findAllPlantillaComunicacion", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
			ldm_managerCYN.close();
		}

		return lcpc_datos;
	}

	/**
	 * Método para encontrar todos los registros de la base de datos para plantillas notificación.
	 *
	 * @return Colección de tipo <code>PlantillaNotificacion</code> que contiene los resultados de la busqueda.
	 * @throws B2BException  Objeto de tipo <code>B2BException</code>, se produce cuando se encuentra algun error controlado.
	 */
	public synchronized Collection<PlantillaNotificacion> findAllPlantillaNotificacion()
	    throws B2BException
	{
		DAOManager                        ldm_manager;
		DAOManager                        ldm_managerCYN;
		Collection<PlantillaNotificacion> lcpn_datos;

		ldm_manager        = DaoManagerFactory.getDAOManager();
		ldm_managerCYN     = DaoManagerFactory.getDAOManagerCYN();
		lcpn_datos         = null;

		try
		{
			lcpn_datos = DaoCreator.getPlantillaNotificacionDAO(ldm_manager).findAll();

			if(CollectionUtils.isValidCollection(lcpn_datos))
			{
				ProcesoDAO       lpd_DAO;
				EtapaDAO         led_DAO;
				MotivoTramiteDAO lmtd_DAO;
				PlantillaDAO     lpld_DAO;

				lpd_DAO      = DaoCreator.getProcesoDAO(ldm_manager);
				led_DAO      = DaoCreator.getEtapaDAO(ldm_manager);
				lmtd_DAO     = DaoCreator.getMotivoTramiteDAO(ldm_manager);
				lpld_DAO     = DaoCreator.getPlantillaDAO(ldm_managerCYN);

				for(PlantillaNotificacion lpn_iterador : lcpn_datos)
				{
					if(lpn_iterador != null)
					{
						{
							Proceso lp_proceso;

							lp_proceso = lpd_DAO.findById(lpn_iterador.getIdProceso());

							if(lp_proceso != null)
								lpn_iterador.setNombreProceso(lp_proceso.getNombre());
						}

						{
							Etapa le_etapa;

							le_etapa = led_DAO.findById(lpn_iterador.getIdEtapaAnterior());

							if(le_etapa != null)
								lpn_iterador.setNombreEtapa(le_etapa.getNombre());
						}

						{
							MotivoTramite lmt_motivo;

							lmt_motivo = lmtd_DAO.findById(
								    lpn_iterador.getIdEtapaAnterior(), lpn_iterador.getIdMotivo()
								);

							if(lmt_motivo != null)
								lpn_iterador.setNombreMotivo(lmt_motivo.getDescripcion());
						}

						{
							Plantilla lp_plantilla;

							lp_plantilla = lpld_DAO.findById(lpn_iterador.getIdPlantilla());

							if(lp_plantilla != null)
								lpn_iterador.setNombrePlantilla(lp_plantilla.getComentario());
						}

						{
							String ls_activo;

							ls_activo = lpn_iterador.getActivo();

							if(ls_activo != null)
								lpn_iterador.setNombreActivo(ls_activo.contains("S"));
						}
					}
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findAllPlantillaNotificacion", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lcpn_datos;
	}

	/**
	 * Método de consulta para obtener todos los datos de Proceso canal.
	 *
	 * @return Colleccion  de Proceso Canal con todos los datos de la tabla
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public synchronized Collection<ProcesoCanal> findAllProcesoCanal()
	    throws B2BException
	{
		DAOManager               ldm_manager;
		Collection<ProcesoCanal> lcanj_datos;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		lcanj_datos     = null;

		try
		{
			lcanj_datos = DaoCreator.getProcesoCanalDAO(ldm_manager).findAll();
		}

		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findAllProcesoCanal", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lcanj_datos;
	}

	/**
	 * Método para encontrar los datos de Proceso Canal por su identificador.
	 *
	 * @param ls_idProceso de ls id proceso
	 * @param ls_idSubProceso de ls id sub proceso
	 * @param ls_idTipoCanalOrigen de ls id tipo canal origen
	 * @return ProcesoCanal con el registro solicitado
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public synchronized ProcesoCanal findAllProcesoCanalById(
	    String ls_idProceso, String ls_idSubProceso, String ls_idTipoCanalOrigen
	)
	    throws B2BException
	{
		DAOManager   ldm_manager;
		ProcesoCanal lcc_datos;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		lcc_datos       = null;

		try
		{
			lcc_datos = DaoCreator.getProcesoCanalDAO(ldm_manager)
					                  .findById(ls_idProceso, ls_idSubProceso, ls_idTipoCanalOrigen);
		}

		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findAllProcesoCanalById", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lcc_datos;
	}

	/**
	 * Método para encontrar todos los registros de la base de datos para la tabla
	 * SDB_PGN_PROCESO_CONSULTA.
	 *
	 * @return el valor de collection
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public synchronized Collection<ProcesoConsulta> findAllProcesoConsulta()
	    throws B2BException
	{
		DAOManager                  ldm_manager;
		Collection<ProcesoConsulta> lcpc_datos;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		lcpc_datos      = null;

		try
		{
			lcpc_datos = DaoCreator.getProcesoConsultaDAO(ldm_manager).findAll();
		}

		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findAllProcesoConsulta", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lcpc_datos;
	}

	/**
	 * Método para la transaccion con la base de datos para traer todos los registros existentes
	 * en la tabla SDB_PGN_PROCESO_AUTOMATICO.
	 *
	 * @param ab_b correspondiente al valor del tipo de objeto boolean
	 * @return devuelve el valor de Collection
	 * @throws B2BException Señala que se ha producido una excepción
	 *
	 */
	public synchronized Collection<ProcesoAutomatico> findAllProcesosAutomaticos(boolean ab_b)
	    throws B2BException
	{
		DAOManager                    ldm_manager;
		Collection<ProcesoAutomatico> lcc_datos;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		lcc_datos       = null;

		try
		{
			lcc_datos = DaoCreator.getProcesoAutomaticoDAO(ldm_manager).findAll(ab_b);
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findAllProcesosAutomaticos", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lcc_datos;
	}

	/**
	 * Método para encontrar todos los registros de la base de datos para la tabla
	 * SDB_PGN_PUNTO_RECAUDO.
	 *
	 * @return devuelve el valor de Collection
	 * @throws B2BException Señala que se ha producido una excepción
	 *
	 */
	public synchronized Collection<PuntoRecaudo> findAllPuntoRecaudo()
	    throws B2BException
	{
		DAOManager               ldm_managerNPA;
		DAOManager               ldm_manager;
		Collection<PuntoRecaudo> lcpr_datos;

		ldm_managerNPA     = DaoManagerFactory.getDAOManagerNPA();
		ldm_manager        = DaoManagerFactory.getDAOManager();
		lcpr_datos         = null;

		try
		{
			lcpr_datos = DaoCreator.getPuntoRecaudoDAO(ldm_managerNPA).findAll();

			if(CollectionUtils.isValidCollection(lcpr_datos))
			{
				PaisDAO               lpd_paisDAO;
				DepartamentoDAO       ldd_departamentoDAO;
				MunicipioDAO          lmd_municipioDAO;
				EntidadRecaudadoraDAO lerd_entidadRecaudadoraDAO;
				MedioRecaudoDAO       lmrd_medioRecaudoDAO;

				ldd_departamentoDAO            = DaoCreator.getDepartamentoDAO(ldm_manager);
				lmd_municipioDAO               = DaoCreator.getMunicipioDAO(ldm_manager);
				lpd_paisDAO                    = DaoCreator.getPaisDAO(ldm_manager);
				lerd_entidadRecaudadoraDAO     = DaoCreator.getEntidadRecaudadoraDAO(ldm_managerNPA);
				lmrd_medioRecaudoDAO           = DaoCreator.getMedioRecaudoDAO(ldm_managerNPA);

				for(PuntoRecaudo lpr_temp : lcpr_datos)
				{
					if(lpr_temp != null)
					{
						String ls_idDepartamento;
						String ls_idMunicipio;
						String ls_idPais;
						String ls_idEntidadRecaudadora;
						String ls_idMedioRecaudo;

						ls_idDepartamento           = lpr_temp.getIdDepartamento();
						ls_idMunicipio              = lpr_temp.getIdMunicipio();
						ls_idPais                   = lpr_temp.getIdPais();
						ls_idEntidadRecaudadora     = lpr_temp.getIdEntidadRecaudadora();
						ls_idMedioRecaudo           = lpr_temp.getIdMedioRecaudo();

						if(StringUtils.isValidString(ls_idPais))
						{
							Pais lp_pais;

							lp_pais = lpd_paisDAO.findById(ls_idPais);

							if(lp_pais != null)
								lpr_temp.setNombrePais(lp_pais.getNombre());

							if(StringUtils.isValidString(ls_idDepartamento))
							{
								Departamento ld_deparmento;

								ld_deparmento = new Departamento();

								ld_deparmento.setIdDepartamento(ls_idDepartamento);
								ld_deparmento.setIdPais(ls_idPais);

								ld_deparmento = ldd_departamentoDAO.findById(ld_deparmento);

								if(ld_deparmento != null)
									lpr_temp.setNombreDepartamento(ld_deparmento.getNombre());

								if(StringUtils.isValidString(ls_idMunicipio))
								{
									Municipio lm_municipio;

									lm_municipio = new Municipio();

									lm_municipio.setIdDepartamento(ls_idDepartamento);
									lm_municipio.setIdPais(ls_idPais);
									lm_municipio.setIdMunicipio(ls_idMunicipio);

									lm_municipio = lmd_municipioDAO.findById(lm_municipio);

									if(lm_municipio != null)
										lpr_temp.setNombreMunicipio(lm_municipio.getNombre());
								}
							}
						}

						if(StringUtils.isValidString(ls_idEntidadRecaudadora))
						{
							EntidadRecaudadora ler_entidadRecaudadora;

							ler_entidadRecaudadora = lerd_entidadRecaudadoraDAO.findById(ls_idEntidadRecaudadora);

							if(ler_entidadRecaudadora != null)
								lpr_temp.setNombreEntidadRecaudadora(
								    ler_entidadRecaudadora.getNombreEntidadRecaudadora()
								);
						}

						if(StringUtils.isValidString(ls_idMedioRecaudo))
						{
							MedioRecaudo lmr_medioRecaudo;

							lmr_medioRecaudo = lmrd_medioRecaudoDAO.findById(ls_idMedioRecaudo);

							if(lmr_medioRecaudo != null)
								lpr_temp.setNombreMedioRecaudo(lmr_medioRecaudo.getNombreMedioRecaudo());
						}
					}
				}
			}
		}

		catch(B2BException lb2be_e)
		{
			ldm_managerNPA.setRollbackOnly();
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findAllPuntoRecaudo", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_managerNPA.close();
			ldm_manager.close();
		}

		return lcpr_datos;
	}

	/**
	 * Método para encontrar todos los registros de la base de datos para la tabla
	 * SDB_PGN_PUNTO_RECAUDO_TIPO_RECAUDO.
	 *
	 * @return devuelve el valor de Collection
	 * @throws B2BException Señala que se ha producido una excepción
	 *
	 */
	public synchronized Collection<PuntoRecaudoTipoRecaudo> findAllPuntoRecaudoTipoRecaudo()
	    throws B2BException
	{
		DAOManager                          ldm_manager;
		Collection<PuntoRecaudoTipoRecaudo> lcprtr_datos;

		ldm_manager      = DaoManagerFactory.getDAOManagerNPA();
		lcprtr_datos     = null;

		try
		{
			lcprtr_datos = DaoCreator.getPuntoRecaudoTipoRecaudoDAO(ldm_manager).findAll();
		}

		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findAllPuntoRecaudoTipoRecaudo", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lcprtr_datos;
	}

	/**
	 * Método para encontrar todos los registros de la base de datos para la tabla
	 * SDB_PGN_RANGO_CUANTIA.
	 *
	 * @return devuelve el valor de Collection
	 * @throws B2BException Señala que se ha producido una excepción
	 *
	 */
	public synchronized Collection<RangoCuantia> findAllRangoCuantia()
	    throws B2BException
	{
		DAOManager               ldm_manager;
		Collection<RangoCuantia> lcr_datos;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		lcr_datos       = null;

		try
		{
			lcr_datos = DaoCreator.getRangoCuantiaDAO(ldm_manager).findAll();
		}

		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findAllRangoCuantia", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lcr_datos;
	}

	/**
	 * Método de transaciones con la base de datos con el fin de encontar todos los registros
	 * de la tabla SDB_PGN_REGIONAL.
	 *
	 * @return devuelve el valor de Collection
	 * @throws B2BException Señala que se ha producido una excepción
	 *
	 */
	public synchronized Collection<Regional> findAllRegionales()
	    throws B2BException
	{
		DAOManager           ldm_manager;
		Collection<Regional> lcc_datos;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		lcc_datos       = null;

		try
		{
			lcc_datos = DaoCreator.getRegionalDAO(ldm_manager).findAll();
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findAllRegionales", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lcc_datos;
	}

	/**
	 * Método para encontrar todos los registros de la base de datos para reglas negocio.
	 *
	 * @return Colección de tipo <code>ReglaNegocio</code> que contiene los resultados de la busqueda.
	 * @throws B2BException  Objeto de tipo <code>B2BException</code>, se produce cuando se encuentra algun error controlado.
	 */
	public synchronized Collection<ReglaNegocio> findAllReglaNegocio()
	    throws B2BException
	{
		DAOManager               ldm_manager;
		Collection<ReglaNegocio> lcrn_reglas;

		ldm_manager     = DaoManagerFactory.getDAOManagerWorkflow();
		lcrn_reglas     = new LinkedList<ReglaNegocio>();

		try
		{
			lcrn_reglas = DaoCreator.getReglaNegocioDAO(ldm_manager).findAll();
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findAllReglaNegocio", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lcrn_reglas;
	}

	/**
	 * Método para encontrar todos los registros de la base de datos para la tabla
	 * SDB_PGN_REINTENTOS.
	 *
	 * @return devuelve el valor de Collection
	 * @throws B2BException Señala que se ha producido una excepción
	 *
	 */
	public synchronized Collection<Reintentos> findAllReintentos()
	    throws B2BException
	{
		DAOManager             ldm_manager;
		Collection<Reintentos> lcr_datos;

		ldm_manager     = DaoManagerFactory.getDAOManagerCYN();
		lcr_datos       = null;

		try
		{
			lcr_datos = DaoCreator.getReintentosDAO(ldm_manager).findAll();
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findAllReintentos", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lcr_datos;
	}

	/**
	 * Método para encontrar todos los registros de la base de datos para la tabla
	 * SDB_PGN_REPORTES.
	 *
	 * @return devuelve el valor de Collection
	 * @throws B2BException Señala que se ha producido una excepción
	 *
	 */
	public synchronized Collection<Reportes> findAllReportes()
	    throws B2BException
	{
		DAOManager           ldm_manager;
		Collection<Reportes> lcr_datos;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		lcr_datos       = null;

		try
		{
			lcr_datos = DaoCreator.getReportesDAO(ldm_manager).findAll();
		}

		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findAllReportes", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lcr_datos;
	}

	/**
	 * Método para consultar todos los campos de la tabla SDB_AUT_ROL_OPCION.
	 *
	 * @return el valor de collection
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public synchronized Collection<RolOpcion> findAllRolOpcion()
	    throws B2BException
	{
		DAOManager            ldm_manager;
		Collection<RolOpcion> lcro_datos;
		RolDAO                lrd_rolDAO;
		OpcionDAO             lod_opcionDAO;

		ldm_manager       = DaoManagerFactory.getDAOManager();
		lcro_datos        = null;
		lrd_rolDAO        = DaoCreator.getRolDAO(ldm_manager);
		lod_opcionDAO     = DaoCreator.getOpcionDAO(ldm_manager);

		try
		{
			lcro_datos = DaoCreator.getRolOpcionDAO(ldm_manager).findAll();

			if(CollectionUtils.isValidCollection(lcro_datos))
			{
				for(RolOpcion lc_temp : lcro_datos)
				{
					if(lc_temp != null)
					{
						Rol    lr_rolNombre;
						Opcion lo_opcionNombre;

						lr_rolNombre        = lrd_rolDAO.findById(lc_temp.getIdRol(), false);
						lo_opcionNombre     = lod_opcionDAO.findById(lc_temp.getIdOpcion());

						if((lo_opcionNombre != null) && (lr_rolNombre != null))
						{
							String ls_nombreOpcionPantalla;
							String ls_nombreRolPantalla;

							ls_nombreOpcionPantalla     = lo_opcionNombre.getOpcion();
							ls_nombreRolPantalla        = lr_rolNombre.getNombre();

							if(
							    StringUtils.isValidString(ls_nombreOpcionPantalla)
								    && StringUtils.isValidString(ls_nombreRolPantalla)
							)
							{
								lc_temp.setNombreOpcion(ls_nombreOpcionPantalla);
								lc_temp.setNombreRol(ls_nombreRolPantalla);
							}
						}
					}
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findAllRolOpcion", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lcro_datos;
	}

	/**
	 * Método de transaciones con la base de datos con el fin de encontar todos los registros
	 * de la tabla SDB_AUT_ROL.
	 *
	 * @param ab_b indica si se desea traer los registros de roles que se encuentren activos(true ,false )
	 * @return devuelve el valor de Collection
	 * @throws B2BException Señala que se ha producido una excepción
	 *
	 */
	public synchronized Collection<Rol> findAllRols(boolean ab_b)
	    throws B2BException
	{
		DAOManager      ldm_manager;
		Collection<Rol> lcr_datos;
		RolDAO          lrd_rolDAO;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		lrd_rolDAO      = DaoCreator.getRolDAO(ldm_manager);

		lcr_datos = null;

		try
		{
			lcr_datos = lrd_rolDAO.findAll(ab_b);

			if(CollectionUtils.isValidCollection(lcr_datos))
			{
				ComponentesDAO lcd_componentesDAO;

				lcd_componentesDAO = DaoCreator.getComponentesDAO(ldm_manager);

				for(Rol lc_temp : lcr_datos)
				{
					if(lc_temp != null)
					{
						Componente lc_componente;

						lc_componente = lcd_componentesDAO.findById(lc_temp.getIdComponente());

						if(lc_componente != null)
						{
							String ls_nombreComponente;

							ls_nombreComponente = lc_componente.getNombre();

							if(StringUtils.isValidString(ls_nombreComponente))
								lc_temp.setNombreComponente(ls_nombreComponente);
						}
					}
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findAllRols", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lcr_datos;
	}

	/**
	 * Método para realizar transacciones con la base de datos con el fin de encontrar todas las
	 * imagenes con formato rtf.
	 *
	 * @return devuelve el valor de byte[]
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see byte[]
	 */
	public synchronized byte[] findAllRtf()
	    throws B2BException
	{
		DAOManager ldm_mananger;
		byte[]     lba_zipFinal;

		ldm_mananger     = DaoManagerFactory.getDAOManager();
		lba_zipFinal     = null;

		try
		{
			Collection<Constantes> lcc_constantes;

			lcc_constantes = DaoCreator.getConstantesDAO(ldm_mananger).findAllRTF();

			if(CollectionUtils.isValidCollection(lcc_constantes))
			{
				Collection<ZipEntryUtil> lczeu_zip;

				lczeu_zip = new ArrayList<ZipEntryUtil>();

				for(Constantes lc_temp : lcc_constantes)
				{
					if(lc_temp != null)
					{
						String ls_tipoArchivo;

						ls_tipoArchivo = lc_temp.getTipoArchivo();

						if(StringUtils.isValidString(ls_tipoArchivo))
						{
							byte[] lba_imagenes;

							lba_imagenes = lc_temp.getImagenes();

							if(lba_imagenes != null)
							{
								byte[] lba_imagenBlob;

								lba_imagenBlob = lc_temp.getImagenes();

								if(lba_imagenBlob != null)
								{
									ZipEntryUtil lzeu_pdf;

									lzeu_pdf = new ZipEntryUtil(
										    lc_temp.getIdConstante() + lc_temp.getTipoArchivo(),
										    new ByteArrayInputStream(lba_imagenBlob)
										);
									lczeu_zip.add(lzeu_pdf);
								}
							}
						}
					}
				}

				if(CollectionUtils.isValidCollection(lczeu_zip))
					lba_zipFinal = ZipUtils.generateZip(lczeu_zip);
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_mananger.setRollbackOnly();
		}
		finally
		{
			ldm_mananger.close();
		}

		return lba_zipFinal;
	}

	/**
	 * Método para encontrar todos los registros de la base de datos para la tabla
	 * SDB_PGN_SUCURSAL_CANAL_ORIGEN_SERVICIO.
	 *
	 * @return devuelve el valor de Collection
	 * @throws B2BException Señala que se ha producido una excepción
	 *
	 */
	public synchronized Collection<SucursalCanalOrigenServicio> findAllSucursalCanalOrigenServicio()
	    throws B2BException
	{
		DAOManager                              ldm_manager;
		Collection<SucursalCanalOrigenServicio> lcscos_datos;

		ldm_manager      = DaoManagerFactory.getDAOManagerNPA();
		lcscos_datos     = null;

		try
		{
			lcscos_datos = DaoCreator.getSucursalCanalOrigenServicioDAO(ldm_manager).findAll();
		}

		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findAllSucursalCanalOrigenServicio", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lcscos_datos;
	}

	/**
	 * Método para encontrar todos los registros de la base de datos para la tabla
	 * SDB_PNG_TARIFA_ALERTA en estado activo.
	 *
	 * @return lcr_datos con los datos consultados
	 * @throws B2BException Señala que se ha producido una excepción
	 *
	 */
	public synchronized Collection<TarifaAlerta> findAllTarifaAlertaActivo()
	    throws B2BException
	{
		DAOManager               ldm_manager;
		Collection<TarifaAlerta> lctpa_datos;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		lctpa_datos     = null;

		try
		{
			lctpa_datos = DaoCreator.getTarifaAlertaDAO(ldm_manager).findAllActivo();
		}

		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findAllTarifaAlertaActivo", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lctpa_datos;
	}

	/**
	 * Método para encontrar todos los registros de la base de datos para la tabla
	 * SDB_PGN_TARIFA_FIJA.
	 *
	 * @return devuelve el valor de Collection
	 * @throws B2BException Señala que se ha producido una excepción
	 *
	 */
	public synchronized Collection<TarifaFija> findAllTarifaFija()
	    throws B2BException
	{
		DAOManager             ldm_manager;
		Collection<TarifaFija> lcr_datos;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		lcr_datos       = null;

		try
		{
			lcr_datos = DaoCreator.getTarifaFijaDAO(ldm_manager).findAll();
		}

		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findAllTarifaFija", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lcr_datos;
	}

	/**
	 * Método para encontrar todos los registros de la base de datos para la tabla
	 * SDB_PGN_TIPO_ACTO_CONDICION.
	 *
	 * @return devuelve el valor de Collection
	 * @throws B2BException Señala que se ha producido una excepción
	 *
	 */
	public synchronized Collection<TipoActoCondicion> findAllTipoActoCondicion()
	    throws B2BException
	{
		DAOManager                    ldm_manager;
		Collection<TipoActoCondicion> lcr_datos;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		lcr_datos       = null;

		try
		{
			lcr_datos = DaoCreator.getTipoActoCondicionDAO(ldm_manager).findAll();
		}

		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findAllTipoActoCondicion", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lcr_datos;
	}

	/**
	 * Método para encontrar todos los registros de la base de datos para la tabla
	 * SDB_PGN_TIPO_ACTO_EJECUTORIA.
	 *
	 * @return devuelve el valor de Collection
	 * @throws B2BException Señala que se ha producido una excepción
	 *
	 */
	public synchronized Collection<TipoActoEjecutoria> findAllTipoActoEjecutoria()
	    throws B2BException
	{
		DAOManager                     ldm_manager;
		Collection<TipoActoEjecutoria> lcr_datos;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		lcr_datos       = null;

		try
		{
			lcr_datos = DaoCreator.getTipoActoEjecutoriaDAO(ldm_manager).findAll();

			if(CollectionUtils.isValidCollection(lcr_datos))
			{
				TipoDocumentoPublicoDAO ltdpd_tipoDocumentoPublicoDAO;

				ltdpd_tipoDocumentoPublicoDAO = DaoCreator.getTipoDocumentoPublicoDAO(ldm_manager);

				for(TipoActoEjecutoria ltae_temp : lcr_datos)
				{
					if(ltae_temp != null)
					{
						String ls_idTipoDocumento;

						ls_idTipoDocumento = ltae_temp.getIdTipoDocumento();

						if(StringUtils.isValidString(ls_idTipoDocumento))
						{
							TipoDocumentoPublico ltdp_tipoDocumento;

							ltdp_tipoDocumento = ltdpd_tipoDocumentoPublicoDAO.findById(ls_idTipoDocumento);

							if(ltdp_tipoDocumento != null)
								ltae_temp.setNombreTipoDocumento(ltdp_tipoDocumento.getNombre());
						}
					}
				}
			}
		}

		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findAllTipoActoEjecutoria", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lcr_datos;
	}

	/**
	 * Método de consulta para obtener todos los datos de Tipo Acto Prohibición.
	 *
	 * @return una Colección de tipo Acto Prohibición con los datos solicitados
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public synchronized Collection<TipoActoProhibicion> findAllTipoActoProhibicion()
	    throws B2BException
	{
		DAOManager                      ldm_manager;
		Collection<TipoActoProhibicion> lcc_datos;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		lcc_datos       = null;

		try
		{
			lcc_datos = DaoCreator.getTipoActoProhibicionDAO(ldm_manager).findAll();
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findAllTipoActoProhibicion", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lcc_datos;
	}

	/**
	 * Método para consultar todos los TipoActoProhibicion correspondiente a el ID.
	 *
	 * @param as_idTipoActoProhibicion de as id tipo acto prohibicion
	 * @return el valor de tipo acto prohibicion
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public synchronized TipoActoProhibicion findAllTipoActoProhibicionById(String as_idTipoActoProhibicion)
	    throws B2BException
	{
		DAOManager          ldm_manager;
		TipoActoProhibicion lcc_datos;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		lcc_datos       = null;

		try
		{
			if(StringUtils.isValidString(as_idTipoActoProhibicion))
				lcc_datos = DaoCreator.getTipoActoProhibicionDAO(ldm_manager).findById(as_idTipoActoProhibicion);
		}

		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findAllTipoActoProhibicionById", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lcc_datos;
	}

	/**
	 * Retorna el valor del objeto de Collection.
	 *
	 * @param as_s correspondiente al valor del tipo de objeto String
	 * @return devuelve el valor de Collection
	 * @throws B2BException Señala que se ha producido una excepción
	 *
	 */
	public synchronized Collection<TipoAdquisicion> findAllTipoAdquisicion(String as_s)
	    throws B2BException
	{
		DAOManager                  ldm_manager;
		Collection<TipoAdquisicion> lcr_datos;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		lcr_datos       = null;

		try
		{
			lcr_datos = DaoCreator.getTipoAdquisicionDAO(ldm_manager).findAll(as_s);
		}

		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findAllTipoAdquisicion", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lcr_datos;
	}

	/**
	 * Retorna el valor del objeto de Collection.
	 *
	 * @return devuelve el valor de Collection
	 * @throws B2BException Señala que se ha producido una excepción
	 *
	 */
	public synchronized Collection<TipoAdquisicion> findAllTipoAdquisicion()
	    throws B2BException
	{
		DAOManager                  ldm_manager;
		Collection<TipoAdquisicion> lcr_datos;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		lcr_datos       = null;

		try
		{
			lcr_datos = DaoCreator.getTipoAdquisicionDAO(ldm_manager).findAll();
		}

		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findAllTipoAdquisicion", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lcr_datos;
	}

	/**
	 * Método para encontrar todos los registros de la base de datos para la tabla
	 * SDB_PGN_TIPO_AREA.
	 *
	 * @return devuelve el valor de Collection
	 * @throws B2BException Señala que se ha producido una excepción
	 *
	 */
	public synchronized Collection<TipoArea> findAllTipoArea()
	    throws B2BException
	{
		DAOManager           ldm_manager;
		Collection<TipoArea> lcta_datos;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		lcta_datos      = null;

		try
		{
			lcta_datos = DaoCreator.getTipoAreaDAO(ldm_manager).findAll();
		}

		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findAllTipoArea", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lcta_datos;
	}

	/**
	 * Método para encontrar todos los registros de la base de datos para la tabla
	 * SDB_PGN_TIPO_CANAL_ORIGEN.
	 *
	 * @return devuelve el valor de Collection
	 * @throws B2BException Señala que se ha producido una excepción
	 *
	 */
	public synchronized Collection<TipoCanalOrigen> findAllTipoCanalOrigen()
	    throws B2BException
	{
		DAOManager                  ldm_manager;
		Collection<TipoCanalOrigen> ltco_datos;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		ltco_datos      = null;

		try
		{
			ltco_datos = DaoCreator.getTipoCanalOrigenDAO(ldm_manager).findAll();
		}

		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findAllTipoCanalOrigen", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return ltco_datos;
	}

	/**
	 * Método para encontrar todos los registros de la base de datos para la tabla
	 * SDB_PGN_TIPO_CRITERIO_BUSQUEDA.
	 *
	 * @return devuelve el valor de Collection
	 * @throws B2BException Señala que se ha producido una excepción
	 *
	 */
	public synchronized Collection<TipoCriterioBusquedaPGN> findAllTipoCriterioBusquedaPGN()
	    throws B2BException
	{
		DAOManager                          ldm_manager;
		Collection<TipoCriterioBusquedaPGN> lcae_datos;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		lcae_datos      = null;

		try
		{
			lcae_datos = DaoCreator.getTipoCriterioBusquedaPGNDAO(ldm_manager).findAll();
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findAllTipoCriterioBusquedaPGN", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lcae_datos;
	}

	/**
	 * Método para encontrar todos los registros de la base de datos para la tabla
	 * SDB_PGN_TIPO_DECISION_RECURSO.
	 *
	 * @return devuelve el valor de Collection
	 * @throws B2BException Señala que se ha producido una excepción
	 *
	 */
	public synchronized Collection<TipoDecisionRecurso> findAllTipoDecisionRecurso()
	    throws B2BException
	{
		DAOManager                      ldm_manager;
		Collection<TipoDecisionRecurso> lcr_datos;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		lcr_datos       = null;

		try
		{
			lcr_datos = DaoCreator.getTipoDecisionRecursoDAO(ldm_manager).findAll();
		}

		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findAllTipoDecisionRecurso", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lcr_datos;
	}

	/**
	 * Método para consultar la tabla Tipo Documental.
	 *
	 * @return el valor de collection
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public synchronized Collection<TipoDocumental> findAllTipoDocumental()
	    throws B2BException
	{
		DAOManager                 ldm_manager;
		Collection<TipoDocumental> lcc_datos;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		lcc_datos       = null;

		try
		{
			lcc_datos = DaoCreator.getTipoDocumentalDAO(ldm_manager).findAll();
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findAllTipoDocumental", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lcc_datos;
	}

	/**
	 * Método de  consulta para obtener todos los datos de Tipo Documental Acto.
	 *
	 * @return una colección de TipoDocumentalActo Con los datos solicitados
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public synchronized Collection<TipoDocumentalActo> findAllTipoDocumentalActo()
	    throws B2BException
	{
		DAOManager                     ldm_manager;
		Collection<TipoDocumentalActo> lcc_datos;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		lcc_datos       = null;

		try
		{
			lcc_datos = DaoCreator.getTipoDocumentalActoDAO(ldm_manager).findAll();
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findAllTipoDocumentalActo", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lcc_datos;
	}

	/**
	 * Método para obtener los datos de Tipo Documental Acto por su identificador.
	 *
	 * @param as_idTipoDocumentalActo de as id tipo documental acto
	 * @return tipoDcoumentalActo con el registro de la consulta
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public synchronized TipoDocumentalActo findAllTipoDocumentalActoById(long as_idTipoDocumentalActo)
	    throws B2BException
	{
		DAOManager         ldm_manager;
		TipoDocumentalActo lcc_datos;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		lcc_datos       = null;

		try
		{
			lcc_datos = DaoCreator.getTipoDocumentalActoDAO(ldm_manager).findById(as_idTipoDocumentalActo);
		}

		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findAllTipoDocumentalActoById", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lcc_datos;
	}

	/**
	 * Método para consultar todos los Tipo Documental correspondiente a el ID.
	 *
	 * @param as_idTipoDocumental de as id tipo documental
	 * @return el valor de tipo documental
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public synchronized TipoDocumental findAllTipoDocumentalById(String as_idTipoDocumental)
	    throws B2BException
	{
		DAOManager     ldm_manager;
		TipoDocumental lcc_datos;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		lcc_datos       = null;

		try
		{
			if(StringUtils.isValidString(as_idTipoDocumental))
				lcc_datos = DaoCreator.getTipoDocumentalDAO(ldm_manager).findById(as_idTipoDocumental);
		}

		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findAllTipoDocumentalById", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lcc_datos;
	}

	/**
	 * Método de transaciones con la base de datos con el fin de encontar todos los registros
	 * de la tabla SDB_PGN_TIPO_DOCUMENTO_PUBLICO.
	 *
	 * @return devuelve el valor de Collection
	 * @throws B2BException Señala que se ha producido una excepción
	 *
	 */
	public synchronized Collection<TipoDocumentoPublico> findAllTipoDocumentoPublico()
	    throws B2BException
	{
		DAOManager                       ldm_manager;
		Collection<TipoDocumentoPublico> lcr_datos;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		lcr_datos       = null;

		try
		{
			lcr_datos = DaoCreator.getTipoDocumentoPublicoDAO(ldm_manager).findAll();
		}

		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findAllTipoDocumentoPublico", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lcr_datos;
	}

	/**
	 * Retorna el valor del objeto de Collection.
	 *
	 * @param as_s correspondiente al valor del tipo de objeto String
	 * @return devuelve el valor de Collection
	 * @throws B2BException Señala que se ha producido una excepción
	 *
	 */
	public synchronized Collection<TipoDocumentoPublico> findAllTipoDocumentoPublico(String as_s)
	    throws B2BException
	{
		DAOManager                       ldm_manager;
		Collection<TipoDocumentoPublico> lcr_datos;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		lcr_datos       = null;

		try
		{
			lcr_datos = DaoCreator.getTipoDocumentoPublicoDAO(ldm_manager).findAll(as_s);
		}

		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findAllTipoDocumentoPublico", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lcr_datos;
	}

	/**
	 * Método para encontrar todos los registros de la base de datos para la tabla
	 * SDB_PGN_TIPO_ESTADO_LIQUIDACION.
	 *
	 * @return devuelve el valor de Collection
	 * @throws B2BException Señala que se ha producido una excepción
	 *
	 */
	public synchronized Collection<TipoEstadoLiquidacion> findAllTipoEstadoLiquidacion()
	    throws B2BException
	{
		DAOManager                        ldm_manager;
		Collection<TipoEstadoLiquidacion> ltel_datos;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		ltel_datos      = null;

		try
		{
			ltel_datos = DaoCreator.getTipoEstadoLiquidacionDAO(ldm_manager).findAll();
		}

		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findAllTipoEstadoLiquidacion", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return ltel_datos;
	}

	/**
	 * Método para la transaccion con la base de datos para traer todos los registros existentes
	 * en la tabla SDB_PGN_DEPARTAMENTO.
	 *
	 * @param ab_b correspondiente al valor del tipo de objeto boolean
	 * @return devuelve el valor de Collection
	 * @throws B2BException Señala que se ha producido una excepción
	 *
	 */
	public synchronized Collection<TipoIntegracionGobernacion> findAllTipoIntegracionGobernacion(boolean ab_b)
	    throws B2BException
	{
		DAOManager                             ldm_manager;
		Collection<TipoIntegracionGobernacion> lcc_datos;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		lcc_datos       = null;

		try
		{
			lcc_datos = DaoCreator.getTipoIntegracionGobernacionDAO(ldm_manager).findAll(ab_b);
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findAllTipoIntegracionGobernacion", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lcc_datos;
	}

	/**
	 * Método para encontrar todos los registros de la base de datos para la tabla
	 * SDB_PGN_TIPO_OPERACION.
	 *
	 * @return devuelve el valor de Collection
	 * @throws B2BException Señala que se ha producido una excepción
	 *
	 */
	public synchronized Collection<TipoOperacion> findAllTipoOperacion()
	    throws B2BException
	{
		DAOManager                ldm_manager;
		Collection<TipoOperacion> lcto_datos;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		lcto_datos      = null;

		try
		{
			lcto_datos = DaoCreator.getTipoOperacionDAO(ldm_manager).findAll(false);
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findAllTipoOperacion", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lcto_datos;
	}

	/**
	 * Método de transaciones con la base de datos con el fin de encontar todos los registros
	 * de la tabla SDB_ACC_PERSONA.
	 *
	 * @return devuelve el valor de Collection
	 * @throws B2BException Señala que se ha producido una excepción
	 *
	 */
	public synchronized Collection<TipoPersona> findAllTipoPersona()
	    throws B2BException
	{
		DAOManager              ldm_manager;
		Collection<TipoPersona> lctp_datos;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		lctp_datos      = null;

		try
		{
			lctp_datos = DaoCreator.getTipoPersonaDAO(ldm_manager).findAll();
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findAllTipoPersona", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lctp_datos;
	}

	/**
	 * Método de transaciones con la base de datos con el fin de encontar todos los registros
	 * de la tabla SDB_PGN_TIPO_PERSONA.
	 *
	 * @return devuelve el valor de Collection
	 * @throws B2BException Señala que se ha producido una excepción
	 *
	 */
	public synchronized Collection<PredioTipo> findAllTipoPredio()
	    throws B2BException
	{
		DAOManager             ldm_manager;
		Collection<PredioTipo> lpt_datos;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		lpt_datos       = null;

		try
		{
			lpt_datos = DaoCreator.getPredioTipoDao(ldm_manager).findAll(false);
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findAllTipoPredio", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lpt_datos;
	}

	/**
	 * Método de transacciones con la base de datos para encontrar todos los registros de la tabla SDB_COL_DOMINIO_RRR.
	 *
	 * @return devuelve el valor de Collection
	 * @throws B2BException Señala que se ha producido una excepción
	 *
	 */
	public synchronized Collection<TipoRrr> findAllTipoRRR()
	    throws B2BException
	{
		DAOManager          ldm_manager;
		Collection<TipoRrr> lctr_datos;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		lctr_datos      = null;

		try
		{
			lctr_datos = DaoCreator.getTipoRrrDAO(ldm_manager).findAll(false);
		}

		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findAllTipoRRR", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lctr_datos;
	}

	/**
	 * Método para encontrar todos los registros de la base de datos para la tabla
	 * SDB_PGN_TIPO_RECAUDO.
	 *
	 * @return devuelve el valor de Collection
	 * @throws B2BException Señala que se ha producido una excepción
	 *
	 */
	public synchronized Collection<TipoRecaudo> findAllTipoRecaudo()
	    throws B2BException
	{
		DAOManager              ldm_manager;
		Collection<TipoRecaudo> lcta_datos;

		ldm_manager     = DaoManagerFactory.getDAOManagerNPA();
		lcta_datos      = null;

		try
		{
			lcta_datos = DaoCreator.getTipoRecaudoDAO(ldm_manager).findAll();
		}

		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findAllTipoRecaudo", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lcta_datos;
	}

	/**
	 * Método de transacciones con la base de datos para encontrar todos los registros de la tabla SDB_ACC_TIPO_RECEPCION.
	 *
	 * @return devuelve el valor de Collection
	 * @throws B2BException Señala que se ha producido una excepción
	 *
	 */
	public synchronized Collection<TipoRecepcion> findAllTipoRecepcion()
	    throws B2BException
	{
		DAOManager                ldm_manager;
		Collection<TipoRecepcion> lcr_datos;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		lcr_datos       = null;

		try
		{
			lcr_datos = DaoCreator.getTipoRecepcionDAO(ldm_manager).findAll();
		}

		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findAllTipoRecepcion", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lcr_datos;
	}

	/**
	      * Método de transacciones con la base de datos para encontrar todos los registros de la tabla SDB_ACC_TIPO_RECEPCION ordenados por nombre.
	 *
	 * @return devuelve el valor de Collection
	 * @throws B2BException Señala que se ha producido una excepción
	 *
	 */
	public synchronized Collection<TipoRecepcion> findAllTipoRecepcionOrdenado()
	    throws B2BException
	{
		DAOManager                ldm_manager;
		Collection<TipoRecepcion> lcr_datos;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		lcr_datos       = null;

		try
		{
			lcr_datos = DaoCreator.getTipoRecepcionDAO(ldm_manager).findAllOrdenado();
		}

		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findAllTipoRecepcionOrdenado", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lcr_datos;
	}

	/**
	 * Método para encontrar todos los registros de la base de datos para la tabla
	 * SDB_PGN_TIPO_RECURSO.
	 *
	 * @return devuelve el valor de Collection
	 * @throws B2BException Señala que se ha producido una excepción
	 *
	 */
	public synchronized Collection<TipoRecurso> findAllTipoRecurso()
	    throws B2BException
	{
		DAOManager              ldm_manager;
		Collection<TipoRecurso> lcr_datos;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		lcr_datos       = null;

		try
		{
			lcr_datos = DaoCreator.getTipoRecursoDAO(ldm_manager).findAll();
		}

		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findAllTipoRecurso", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lcr_datos;
	}

	/**
	 * Método para traer de la base de datos todos lo registros de la tabla SDB_PGN_TIPO_RECURSO con ACTIVO igual al argumento enviado.
	 *
	 * @param as_activo Argumento de tipo <code>String</code> que contiene el criterio necesario para realizar la consulta.
	 * @return devuelve el valor del objeto collection de TipoRecurso.
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see TipoRecurso
	 */
	public synchronized Collection<TipoRecurso> findAllTipoRecursoByActivo(String as_activo)
	    throws B2BException
	{
		DAOManager              ldm_manager;
		Collection<TipoRecurso> lcr_datos;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		lcr_datos       = null;

		try
		{
			lcr_datos = DaoCreator.getTipoRecursoDAO(ldm_manager).findAllByActivo(as_activo);
		}

		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findAllTipoRecursoByActivo", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lcr_datos;
	}

	/**
	 * Método para encontrar todos los registros de la base de datos para la tabla
	 * SDB_PGN_TIPO_REQUIERE_MATRICULA.
	 *
	 * @return devuelve el valor de Collection
	 * @throws B2BException Señala que se ha producido una excepción
	 *
	 */
	public synchronized Collection<TipoRequiereMatricula> findAllTipoRequiereMatricula()
	    throws B2BException
	{
		DAOManager                        ldm_manager;
		Collection<TipoRequiereMatricula> ltrm_datos;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		ltrm_datos      = null;

		try
		{
			ltrm_datos = DaoCreator.getTipoRequiereMatriculaDAO(ldm_manager).findAll(false);
		}

		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findAllTipoRequiereMatricula", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return ltrm_datos;
	}

	/**
	 *  Método de transacciones con la base de datos para encontrar todos los registros de la tabla SDB_ACC_TIPO_TARJETA_APODERADO.
	 *
	 * @return devuelve el valor de Collection
	 * @throws B2BException Señala que se ha producido una excepción
	 *
	 */
	public synchronized Collection<TipoTarjetaApoderado> findAllTipoTarjetaApoderados()
	    throws B2BException
	{
		DAOManager                       ldm_manager;
		Collection<TipoTarjetaApoderado> lcr_datos;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		lcr_datos       = null;

		try
		{
			lcr_datos = DaoCreator.getTipoTarjetaApoderadoDAO(ldm_manager).findAll();
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findAllTipoTarjetaApoderados", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lcr_datos;
	}

	/**
	 *  Método de transacciones con la base de datos para encontrar todos los registros de la tabla SDB_COL_TIPO_USO_SUELO.
	 *
	 * @return devuelve el valor de Collection
	 * @throws B2BException Señala que se ha producido una excepción
	 *
	 */
	public synchronized Collection<TipoUsoSuelo> findAllTipoUsoSuelo()
	    throws B2BException
	{
		DAOManager               ldm_manager;
		Collection<TipoUsoSuelo> lcr_datos;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		lcr_datos       = null;

		try
		{
			lcr_datos = DaoCreator.getTipoUsoSueloDAO(ldm_manager).findAll(false);
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findAllTipoUsoSuelo", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lcr_datos;
	}

	/**
	 * Método de transacciones con la base de datos para encontrar todos los registros de la tabla SDB_PGN_TIPO_ACTO.
	 *
	 * @return devuelve el valor de Collection
	 * @throws B2BException Señala que se ha producido una excepción
	 *
	 */
	public synchronized Collection<TipoActo> findAllTiposActo()
	    throws B2BException
	{
		DAOManager           ldm_manager;
		Collection<TipoActo> lcc_return;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		lcc_return      = null;

		try
		{
			lcc_return = DaoCreator.getTipoActoDAO(ldm_manager).findAllTiposActo();
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findAllTiposActo", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lcc_return;
	}

	/**
	 * Método de transacciones con la base de datos para encontrar todos los registros de la tabla SDB_PGN_TIPO_ACTO.
	 *
	 * @return devuelve el valor de Collection
	 * @throws B2BException Señala que se ha producido una excepción
	 *
	 */
	public synchronized Collection<TipoActo> findAllTiposActoAplicaDesborde()
	    throws B2BException
	{
		DAOManager           ldm_manager;
		Collection<TipoActo> lcc_return;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		lcc_return      = null;

		try
		{
			lcc_return = DaoCreator.getTipoActoDAO(ldm_manager).findAllTiposActoAplicaDesborde();
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findAllTiposActoAplicaDesborde", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lcc_return;
	}

	/**
	 * Método de transacciones con la base de datos para encontrar todos los registros de la tabla.
	 *
	 * @return devuelve el valor de Collection
	 * @throws B2BException Señala que se ha producido una excepción
	 *
	 */
	public synchronized Collection<TipoAnotacion> findAllTiposAnotacion()
	    throws B2BException
	{
		DAOManager                ldm_manager;
		Collection<TipoAnotacion> lclp_datos;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		lclp_datos      = null;

		try
		{
			lclp_datos = DaoCreator.getTipoAnotacionDAO(ldm_manager).findAll();
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findAllTiposAnotacion", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lclp_datos;
	}

	/**
	 * Método de transacciones con la base de datos para encontrar todos los registros de la tabla SDB_ACC_TIPO_CAUSAL.
	 *
	 * @return devuelve el valor de Collection
	 * @throws B2BException Señala que se ha producido una excepción
	 *
	 */
	public synchronized Collection<TipoCausal> findAllTiposCausal()
	    throws B2BException
	{
		DAOManager             ldm_manager;
		Collection<TipoCausal> lctc_return;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		lctc_return     = null;

		try
		{
			lctc_return = DaoCreator.getTipoCausalDAO(ldm_manager).findAllTiposCausales();
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findAllTiposCausal", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lctc_return;
	}

	/**
	 * Método de transacciones con la base de datos para encontrar todos los registros de la tabla SDB_PNG_TIPO_EJE.
	 *
	 * @return devuelve el valor de Collection
	 * @throws B2BException Señala que se ha producido una excepción
	 *
	 */
	public synchronized Collection<TipoEje> findAllTiposEje()
	    throws B2BException
	{
		DAOManager          ldm_manager;
		Collection<TipoEje> lcte_return;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		lcte_return     = null;

		try
		{
			lcte_return = DaoCreator.getTipoEjeDAO(ldm_manager).findAll();
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findAllTiposEje", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lcte_return;
	}

	/**
	 * Método de transacciones con la base de datos para encontrar todos los registros de la tabla SDB_ACC_TIPO_ESTADO_SOLICITUD.
	 *
	 * @return devuelve el valor de Collection
	 * @throws B2BException Señala que se ha producido una excepción
	 *
	 */
	public synchronized Collection<TipoEstadoSolicitud> findAllTiposEstadoSolicitud()
	    throws B2BException
	{
		DAOManager                      ldm_manager;
		Collection<TipoEstadoSolicitud> lclp_datos;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		lclp_datos      = null;

		try
		{
			lclp_datos = DaoCreator.getTipoEstadoSolicitudDAO(ldm_manager).findAll();
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findAllTiposEstadoSolicitud", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lclp_datos;
	}

	/**
	 * Método de transacciones con la base de datos para encontrar todos los registros de la tabla SDB_PGN_TIPO_TESTAMENTO.
	 *
	 * @return devuelve el valor de Collection
	 * @throws B2BException Señala que se ha producido una excepción
	 *
	 */
	public synchronized Collection<TipoTestamento> findAllTiposTestamento()
	    throws B2BException
	{
		DAOManager                 ldm_manager;
		Collection<TipoTestamento> lclp_datos;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		lclp_datos      = null;

		try
		{
			lclp_datos = DaoCreator.getTipoTestamentoDAO(ldm_manager).findAll();
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findAllTiposTestamento", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lclp_datos;
	}

	/**
	 * Método para encontrar todos los registros de la base de datos para topologías reglas.
	 *
	 * @return Colección de tipo <code>TopologiaRegla</code> que contiene los resultados de la busqueda.
	 * @throws B2BException  Objeto de tipo <code>B2BException</code>, se produce cuando se encuentra algun error controlado.
	 */
	public synchronized Collection<TopologiaRegla> findAllTopologiaRegla()
	    throws B2BException
	{
		DAOManager                 ldm_manager;
		Collection<TopologiaRegla> lcrn_reglas;

		ldm_manager     = DaoManagerFactory.getDAOManagerWorkflow();
		lcrn_reglas     = new LinkedList<TopologiaRegla>();

		try
		{
			lcrn_reglas = DaoCreator.getTopologiaReglaDAO(ldm_manager).findAll();
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findAllTopologiaRegla", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lcrn_reglas;
	}

	/**
	 * Método para encontrar todos los registros de la base de datos para la tabla
	 * SDB_PGN_UNIDAD_TIEMPO_VENCIMIENTO.
	 *
	 * @return el valor de collection
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public synchronized Collection<UnidadTiempoVencimiento> findAllUnidadTiempoVencimiento()
	    throws B2BException
	{
		DAOManager                          ldm_manager;
		Collection<UnidadTiempoVencimiento> lcutv_datos;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		lcutv_datos     = null;

		try
		{
			lcutv_datos = DaoCreator.getUnidadTiempoVencimientoDAO(ldm_manager).findAll();
		}

		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findAllUnidadTiempoVencimiento", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lcutv_datos;
	}

	/**
	 * Método de transacciones con la base de datos para encontrar todos los registros de la tabla  SDB_AUT_USUARIO_CIRCULO.
	 *
	 * @return devuelve el valor de Collection
	 * @throws B2BException Señala que se ha producido una excepción
	 *
	 */
	public synchronized Collection<UsuarioCirculo> findAllUsuarioCirculos()
	    throws B2BException
	{
		DAOManager                 ldm_manager;
		Collection<UsuarioCirculo> lcuc_datos;
		Collection<UsuarioCirculo> lcuc_return;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		lcuc_datos      = null;

		try
		{
			lcuc_datos = DaoCreator.getUsuarioCirculoDAO(ldm_manager).findAll();

			if(!CollectionUtils.isValidCollection(lcuc_datos))
				throw new B2BException(ErrorKeys.ERROR_DATOS_CONSULTA);

			lcuc_return = new LinkedList<UsuarioCirculo>();

			UsuarioDAO lu_DAO;
			lu_DAO = DaoCreator.getUsuarioDAO(ldm_manager);

			CirculoRegistralDao lcr_DAO;
			lcr_DAO = DaoCreator.getCirculoRegistralDAO(ldm_manager);

			for(UsuarioCirculo luc_registro : lcuc_datos)
			{
				if(luc_registro != null)
				{
					Usuario lu_userTemp;
					lu_userTemp     = luc_registro.getUsuario();

					lu_userTemp = lu_DAO.findById(lu_userTemp);

					luc_registro.setUsuario(lu_userTemp);

					CirculoRegistral lcr_circuloTemp;
					lcr_circuloTemp     = luc_registro.getCirculo();

					lcr_circuloTemp = lcr_DAO.findById(lcr_circuloTemp);

					luc_registro.setCirculo(lcr_circuloTemp);

					lcuc_return.add(luc_registro);
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findAllUsuarioCirculos", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lcuc_return;
	}

	/**
	 * Método de transacciones con la base de datos para encontrar todos los registros de la tabla SDB_PNG_USUARIO_ETAPA.
	 *
	 * @return devuelve el valor de Collection
	 * @throws B2BException Señala que se ha producido una excepción
	 *
	 */
	public synchronized Collection<UsuarioEtapa> findAllUsuarioEtapas()
	    throws B2BException
	{
		DAOManager               ldm_manager;
		Collection<UsuarioEtapa> lcuc_datos;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		lcuc_datos      = null;

		try
		{
			lcuc_datos = DaoCreator.getUsuarioEtapaDAO(ldm_manager).findAll();
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findAllUsuarioEtapas", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lcuc_datos;
	}

	/**
	 * Método de transacciones con la base de datos para encontrar todos los registros de la tabla SDB_PGN_USUARIO_LINEA.
	 *
	 * @return devuelve el valor de Collection
	 * @throws B2BException Señala que se ha producido una excepción
	 *
	 */
	public synchronized Collection<UsuarioLinea> findAllUsuarioLineas()
	    throws B2BException
	{
		DAOManager               ldm_manager;
		Collection<UsuarioLinea> lcul_datos;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		lcul_datos      = null;

		try
		{
			lcul_datos = DaoCreator.getUsuarioLineaDAO(ldm_manager).findAll();
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findAllUsuarioLineas", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lcul_datos;
	}

	/**
	 * Método de transacciones con la base de datos para encontrar todos los registros de la tabla SDB_PGN_VEREDA.
	 *
	 * @return devuelve el valor de Collection
	 * @throws B2BException Señala que se ha producido una excepción
	 *
	 */
	public synchronized Collection<Vereda> findAllVeredas()
	    throws B2BException
	{
		DAOManager ldm_manager;

		Collection<Vereda> lcc_datos;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		lcc_datos       = null;

		try
		{
			lcc_datos = DaoCreator.getVeredaDAO(ldm_manager).findAll();
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findAllVeredas", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lcc_datos;
	}

	/**
	 * Método de transacciones con la base de datos para encontrar todos los registros de la tabla SDB_PGN_ZONA_REGISTRAL.
	 *
	 * @return devuelve el valor de Collection
	 * @throws B2BException Señala que se ha producido una excepción
	 *
	 */
	public synchronized Collection<ZonaRegistral> findAllZonaRegistrales()
	    throws B2BException
	{
		DAOManager                ldm_manager;
		CirculoRegistralDao       lcrd_circuloRegistralDAO;
		PaisDAO                   lpd_paisDAO;
		DepartamentoDAO           ldd_departamentoDAO;
		MunicipioDAO              lmd_municipioDAO;
		VeredaDAO                 lvd_veredaDAO;
		Collection<ZonaRegistral> lcc_datos;

		ldm_manager                  = DaoManagerFactory.getDAOManager();
		lcrd_circuloRegistralDAO     = DaoCreator.getCirculoRegistralDAO(ldm_manager);
		ldd_departamentoDAO          = DaoCreator.getDepartamentoDAO(ldm_manager);
		lmd_municipioDAO             = DaoCreator.getMunicipioDAO(ldm_manager);
		lpd_paisDAO                  = DaoCreator.getPaisDAO(ldm_manager);
		lvd_veredaDAO                = DaoCreator.getVeredaDAO(ldm_manager);
		lcc_datos                    = null;

		try
		{
			lcc_datos = DaoCreator.getZonaRegistralDAO(ldm_manager).findAll(false);

			if(CollectionUtils.isValidCollection(lcc_datos))
			{
				for(ZonaRegistral lc_temp : lcc_datos)
				{
					if(lc_temp != null)
					{
						String ls_idDepartamento;
						String ls_idMunicipio;
						String ls_idPais;
						String ls_idVereda;
						String ls_idcirculo;

						ls_idDepartamento     = lc_temp.getIdDepartamento();
						ls_idMunicipio        = lc_temp.getIdMunicipio();
						ls_idPais             = lc_temp.getIdPais();
						ls_idVereda           = lc_temp.getIdVereda();
						ls_idcirculo          = lc_temp.getIdCirculo();

						if(StringUtils.isValidString(ls_idcirculo))
						{
							CirculoRegistral lcr_circulo;

							lcr_circulo = lcrd_circuloRegistralDAO.findById(ls_idcirculo);

							if(lcr_circulo != null)
								lc_temp.setNombreCirculo(lcr_circulo.getNombre());

							if(StringUtils.isValidString(ls_idPais))
							{
								Pais lp_pais;

								lp_pais = lpd_paisDAO.findById(ls_idPais);

								if(lp_pais != null)
									lc_temp.setNombrePais(lp_pais.getNombre());

								if(StringUtils.isValidString(ls_idDepartamento))
								{
									Departamento ld_deparmento;

									ld_deparmento = new Departamento();

									ld_deparmento.setIdDepartamento(ls_idDepartamento);
									ld_deparmento.setIdPais(ls_idPais);

									ld_deparmento = ldd_departamentoDAO.findById(ld_deparmento);

									if(ld_deparmento != null)
										lc_temp.setNombreDepartamento(ld_deparmento.getNombre());

									if(StringUtils.isValidString(ls_idMunicipio))
									{
										Municipio lm_municipio;

										lm_municipio = new Municipio();

										lm_municipio.setIdDepartamento(ls_idDepartamento);
										lm_municipio.setIdPais(ls_idPais);
										lm_municipio.setIdMunicipio(ls_idMunicipio);

										lm_municipio = lmd_municipioDAO.findById(lm_municipio);

										if(lm_municipio != null)
											lc_temp.setNombreMunicipio(lm_municipio.getNombre());

										if(StringUtils.isValidString(ls_idVereda))
										{
											Vereda lv_vereda;

											lv_vereda = new Vereda();

											lv_vereda.setIdDepartamento(ls_idDepartamento);
											lv_vereda.setIdPais(ls_idPais);
											lv_vereda.setIdMunicipio(ls_idMunicipio);
											lv_vereda.setIdVereda(ls_idVereda);

											lv_vereda = lvd_veredaDAO.findById(lv_vereda);

											if(lv_vereda != null)
												lc_temp.setNombreVereda(lv_vereda.getNombre());
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

			clh_LOGGER.error("findAllZonaRegistrales", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lcc_datos;
	}

	/**
	 * Método de transacciones con la base de datos para encontrar todos los registros activos de la tabla SDB_PGN_ZONA_REGISTRAL.
	 *
	 * @param as_param de as param
	 * @param ab_zona de ab zona
	 * @return devuelve el valor de Collection
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public synchronized Collection<ZonaRegistral> findAllZonaRegistralesActivos(String as_param, boolean ab_zona)
	    throws B2BException
	{
		DAOManager                ldm_manager;
		Collection<ZonaRegistral> lcc_datos;
		DepartamentoDAO           ldd_departamentoDAO;
		MunicipioDAO              lmd_municipioDAO;
		PaisDAO                   lpd_paisDAO;
		VeredaDAO                 lvd_veredaDAO;
		CirculoRegistralDao       lcrd_circuloRegistralDAO;

		ldm_manager                  = DaoManagerFactory.getDAOManager();
		lcc_datos                    = null;
		ldd_departamentoDAO          = DaoCreator.getDepartamentoDAO(ldm_manager);
		lmd_municipioDAO             = DaoCreator.getMunicipioDAO(ldm_manager);
		lpd_paisDAO                  = DaoCreator.getPaisDAO(ldm_manager);
		lvd_veredaDAO                = DaoCreator.getVeredaDAO(ldm_manager);
		lcrd_circuloRegistralDAO     = DaoCreator.getCirculoRegistralDAO(ldm_manager);

		try
		{
			if(StringUtils.isValidString(as_param))
			{
				CirculoRegistral lcr_circulo;
				ZonaRegistral    lzr_zonaRegistral;

				lcr_circulo           = null;
				lzr_zonaRegistral     = null;

				if(ab_zona)
				{
					lcr_circulo     = lcrd_circuloRegistralDAO.findById(as_param);
					lcc_datos       = DaoCreator.getZonaRegistralDAO(ldm_manager).findByCirculo(as_param);
				}
				else
				{
					lzr_zonaRegistral     = DaoCreator.getZonaRegistralDAO(ldm_manager).findById(as_param);
					lcc_datos             = new ArrayList<ZonaRegistral>();

					if(lzr_zonaRegistral != null)
					{
						lcr_circulo = lcrd_circuloRegistralDAO.findById(lzr_zonaRegistral.getIdCirculo());
						lcc_datos.add(lzr_zonaRegistral);
					}
				}

				if(CollectionUtils.isValidCollection(lcc_datos) && (lcr_circulo != null))
				{
					for(ZonaRegistral lc_temp : lcc_datos)
					{
						if(lc_temp != null)
						{
							String ls_idDepartamento;
							String ls_idMunicipio;
							String ls_idPais;
							String ls_idVereda;

							ls_idDepartamento     = lc_temp.getIdDepartamento();
							ls_idMunicipio        = lc_temp.getIdMunicipio();
							ls_idPais             = lc_temp.getIdPais();
							ls_idVereda           = lc_temp.getIdVereda();

							lc_temp.setNombreCirculo(lcr_circulo.getNombre());

							if(StringUtils.isValidString(ls_idPais))
							{
								Pais lp_pais;

								lp_pais = lpd_paisDAO.findById(ls_idPais);

								if(lp_pais != null)
									lc_temp.setNombrePais(lp_pais.getNombre());

								if(StringUtils.isValidString(ls_idDepartamento))
								{
									Departamento ld_deparmento;

									ld_deparmento = new Departamento();

									ld_deparmento.setIdDepartamento(ls_idDepartamento);
									ld_deparmento.setIdPais(ls_idPais);

									ld_deparmento = ldd_departamentoDAO.findById(ld_deparmento);

									if(ld_deparmento != null)
										lc_temp.setNombreDepartamento(ld_deparmento.getNombre());

									if(StringUtils.isValidString(ls_idMunicipio))
									{
										Municipio lm_municipio;

										lm_municipio = new Municipio();

										lm_municipio.setIdDepartamento(ls_idDepartamento);
										lm_municipio.setIdPais(ls_idPais);
										lm_municipio.setIdMunicipio(ls_idMunicipio);

										lm_municipio = lmd_municipioDAO.findById(lm_municipio);

										if(lm_municipio != null)
											lc_temp.setNombreMunicipio(lm_municipio.getNombre());

										if(StringUtils.isValidString(ls_idVereda))
										{
											Vereda lv_vereda;

											lv_vereda = new Vereda();

											lv_vereda.setIdDepartamento(ls_idDepartamento);
											lv_vereda.setIdPais(ls_idPais);
											lv_vereda.setIdMunicipio(ls_idMunicipio);
											lv_vereda.setIdVereda(ls_idVereda);

											lv_vereda = lvd_veredaDAO.findById(lv_vereda);

											if(lv_vereda != null)
												lc_temp.setNombreVereda(lv_vereda.getNombre());
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

			clh_LOGGER.error("findAllZonaRegistralesActivos", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lcc_datos;
	}

	/**
	 * Método para encontrar las zonas asociadas a una collection de ZonaRegistral de la tabla SDB_PGN_ZONA_REGISTRAL.
	 *
	 * @param aczr_czr de aczr czr
	 * @return devuelve el valor de Collection
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public synchronized Collection<ZonaRegistral> findAllZonaRegistralesAsociadas(Collection<ZonaRegistral> aczr_czr)
	    throws B2BException
	{
		Collection<ZonaRegistral> lczr_datos;

		lczr_datos = new ArrayList<ZonaRegistral>();

		try
		{
			if(CollectionUtils.isValidCollection(aczr_czr))
			{
				String ls_cambio;

				ls_cambio = aczr_czr.toString();

				if(StringUtils.isValidString(ls_cambio))
				{
					String ls_llaves;
					ls_llaves     = ls_cambio.replace(
						    IdentificadoresCommon.LLAVE_INICIAL, IdentificadoresCommon.DATO_INVALIDO
						);
					ls_llaves     = ls_llaves.replace(
						    IdentificadoresCommon.LLAVE_FINAL, IdentificadoresCommon.DATO_INVALIDO
						);
					ls_llaves     = ls_llaves.replace(
						    IdentificadoresCommon.ESPACIO_VACIO, IdentificadoresCommon.DATO_INVALIDO
						);

					if(StringUtils.isValidString(ls_llaves))
					{
						Collection<String> lcs_zonas;

						lcs_zonas = ListadoCodigosConstantes.generarCodigosCollection(ls_llaves);

						if(CollectionUtils.isValidCollection(lcs_zonas))
						{
							Collection<ZonaRegistral> lczr_zonaRegistral;

							lczr_zonaRegistral = new ArrayList<ZonaRegistral>();

							for(String ls_temp : lcs_zonas)
							{
								if(StringUtils.isValidString(ls_temp))
								{
									lczr_zonaRegistral = findAllZonaRegistralesActivos(ls_temp, false);

									if(CollectionUtils.isValidCollection(lczr_zonaRegistral))
									{
										for(ZonaRegistral lzr_temp : lczr_zonaRegistral)
										{
											if(lzr_temp != null)
												lczr_datos.add(lzr_temp);
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
			clh_LOGGER.error("findAllZonaRegistralesAsociadas", lb2be_e);

			throw lb2be_e;
		}

		return lczr_datos;
	}

	/**
	 * Find by id documento version.
	 *
	 * @param as_idDocumento de as id documento
	 * @param al_versionDoc de al version doc
	 * @return el valor de documento
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public synchronized Documento findByIdDocumentoVersion(String as_idDocumento, Long al_versionDoc)
	    throws B2BException
	{
		DAOManager ldm_manager;
		Documento  ld_documento;

		ldm_manager      = DaoManagerFactory.getDAOManager();
		ld_documento     = null;

		try
		{
			if(StringUtils.isValidString(as_idDocumento) && NumericUtils.isValidLong(al_versionDoc))
				ld_documento = DaoCreator.getDocumentoDAO(ldm_manager)
						                     .findByIdDocumentoVersion(as_idDocumento, al_versionDoc);
		}

		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findByIdDocumentoVersion", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return ld_documento;
	}

	/**
	 * Método para encontrar todos los registros de la base de datos para la tabla
	 * SDB_ACC_CALIDAD_SOLICITANTE.
	 *
	 * @return el valor de collection
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public synchronized Collection<CalidadSolicitante> findCalidadSolicitante()
	    throws B2BException
	{
		DAOManager                     ldm_manager;
		Collection<CalidadSolicitante> lccs_datos;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		lccs_datos      = null;

		try
		{
			lccs_datos = DaoCreator.getCalidadSolicitanteDAO(ldm_manager).findAll(true);
		}

		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findAllCalidadSolicitante", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lccs_datos;
	}

	/**
	 * Find calidad solicitante by id.
	 *
	 * @param accs_cs Objeto de clase CalidadSolicitante con el cual se realiza la busqueda en la tabla SDB_ACC_CALIDAD_SOLICITANTE
	 * @return Objeto de clase CalidadSolicitante
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public synchronized CalidadSolicitante findCalidadSolicitanteById(CalidadSolicitante accs_cs)
	    throws B2BException
	{
		DAOManager         ldm_manager;
		CalidadSolicitante lccs_datos;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		lccs_datos      = null;

		try
		{
			lccs_datos = DaoCreator.getCalidadSolicitanteDAO(ldm_manager).findById(accs_cs);
		}

		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findCalidadSolicitanteById", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lccs_datos;
	}

	/**
	 * Find campo criterio busqueda by id.
	 *
	 * @param as_tipoCriterioBusqueda            atributo String que contiene el tipoCriterioBusqueda
	 * @param as_campoCriterioBusqueda            atributo string que contiene el campoCriteriobusqueda
	 * @return CamposConsulta con la información consultada
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public synchronized CamposConsulta findCampoCriterioBusquedaById(
	    String as_tipoCriterioBusqueda, String as_campoCriterioBusqueda
	)
	    throws B2BException
	{
		DAOManager     ldm_manager;
		CamposConsulta lcc_datos;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		lcc_datos       = null;

		try
		{
			if(
			    StringUtils.isValidString(as_tipoCriterioBusqueda)
				    && StringUtils.isValidString(as_campoCriterioBusqueda)
			)
				lcc_datos = DaoCreator.getCampoCriterioBusquedaDAO(ldm_manager)
						                  .findById(as_tipoCriterioBusqueda, as_campoCriterioBusqueda);
		}

		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findCampoCriterioBusquedaById", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lcc_datos;
	}

	/**
	 * Método para la transaccion con la base de datos para traer todos los
	 * registros existentes por ID en la tabla SDB_PGN_CAMPOS_CERTIFICADO.
	 *
	 * @param acc_param contenedor de la informacion con el identificador a consultar
	 * @return devuelve el valor de CamposCertificado
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see Canal
	 */
	public synchronized CamposCertificado findCamposCertificadoById(CamposCertificado acc_param)
	    throws B2BException
	{
		CamposCertificado lacc_datos;

		lacc_datos = null;

		if(acc_param != null)
		{
			DAOManager ldm_manager;

			ldm_manager = DaoManagerFactory.getDAOManager();

			try
			{
				lacc_datos = DaoCreator.getCamposCertificadoDAO(ldm_manager).findById(acc_param);
			}

			catch(B2BException lb2be_e)
			{
				ldm_manager.setRollbackOnly();

				clh_LOGGER.error("findCamposCertificadoById", lb2be_e);

				throw lb2be_e;
			}
			finally
			{
				ldm_manager.close();
			}
		}

		return lacc_datos;
	}

	/**
	 * Retorna el valor del objeto de CamposConsulta.
	 *
	 * @param acad_cad correspondiente al valor del tipo de objeto CamposConsulta
	 * @return devuelve el valor de CamposConsulta
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see CamposConsulta
	 */
	public synchronized CamposConsulta findCamposConsultaById(CamposConsulta acad_cad)
	    throws B2BException
	{
		DAOManager     ldm_manager;
		CamposConsulta lcr_datos;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		lcr_datos       = null;

		try
		{
			lcr_datos = DaoCreator.getCamposConsultaDAO(ldm_manager).findById(acad_cad);
		}

		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findCamposConsultaById", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lcr_datos;
	}

	/**
	 * Método para la transaccion con la base de datos para traer todos los
	 * registros existentes por ID en la tabla SDB_PGN_CAMPOS_CORRECCION.
	 *
	 * @param acc_param contenedor de la informacion con el identificador a consultar
	 * @return devuelve el valor de CamposCorreccion
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see Canal
	 */
	public synchronized CamposCorreccion findCamposCorreccionById(CamposCorreccion acc_param)
	    throws B2BException
	{
		CamposCorreccion lacc_datos;

		lacc_datos = null;

		if(acc_param != null)
		{
			DAOManager ldm_manager;

			ldm_manager = DaoManagerFactory.getDAOManager();

			try
			{
				lacc_datos = DaoCreator.getCamposCorreccionDAO(ldm_manager).findById(acc_param);
			}

			catch(B2BException lb2be_e)
			{
				ldm_manager.setRollbackOnly();

				clh_LOGGER.error("findCamposCorreccionById", lb2be_e);

				throw lb2be_e;
			}
			finally
			{
				ldm_manager.close();
			}
		}

		return lacc_datos;
	}

	/**
	 * Método para la transaccion con la base de datos para traer todos los
	 * registros existentes por ID en la tabla SDB_PGN_CANAL.
	 *
	 * @param ac_c correspondiente al valor del tipo de objeto Canal
	 * @return devuelve el valor de Canal
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see Canal
	 */
	public synchronized Canal findCanalById(Canal ac_c)
	    throws B2BException
	{
		DAOManager ldm_manager;
		Canal      lc_datos;

		ldm_manager     = DaoManagerFactory.getDAOManagerCYN();
		lc_datos        = null;

		try
		{
			lc_datos = DaoCreator.getCanalDAO(ldm_manager).findById(ac_c);
		}

		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findCanalById", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lc_datos;
	}

	/**
	 * Método para la transaccion con la base de datos para traer todos los
	 * registros existentes por ID en la tabla SDB_PGN_CANAL_ORIGEN_SERVICIO.
	 *
	 * @param as_s correspondiente al valor del tipo de objeto String
	 * @return devuelve el valor de CanalOrigenServicio
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see CanalOrigenServicio
	 */
	public synchronized CanalOrigenServicio findCanalOrigenServicioById(String as_s)
	    throws B2BException
	{
		DAOManager          ldm_manager;
		CanalOrigenServicio lcos_datos;

		ldm_manager     = DaoManagerFactory.getDAOManagerNPA();
		lcos_datos      = null;

		try
		{
			lcos_datos = DaoCreator.getCanalOrigenServicioDAO(ldm_manager).findById(as_s);
		}

		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findCanalOrigenServicioById", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lcos_datos;
	}

	/**
	 * Método para la transaccion con la base de datos para traer todos los
	 * registros existentes por ID en la tabla SDB_PGN_CATASTRO.
	 *
	 * @param ac_param contenedor de la informacion con el identificador a consultar
	 * @return devuelve el valor de Catastro
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see Catastro
	 */
	public synchronized Catastro findCatastroById(Catastro ac_param)
	    throws B2BException
	{
		Catastro lc_datos;

		lc_datos = null;

		if(ac_param != null)
		{
			DAOManager ldm_manager;

			ldm_manager = DaoManagerFactory.getDAOManager();

			try
			{
				String ls_idCatastro;

				ls_idCatastro = ac_param.getIdCatastro();

				if(StringUtils.isValidString(ls_idCatastro))
					lc_datos = DaoCreator.getCatastroDAO(ldm_manager).findById(ls_idCatastro);
			}

			catch(B2BException lb2be_e)
			{
				ldm_manager.setRollbackOnly();

				clh_LOGGER.error("findCatastroById", lb2be_e);

				throw lb2be_e;
			}
			finally
			{
				ldm_manager.close();
			}
		}

		return lc_datos;
	}

	/**
	 * Método para realizar transacciones con la base de datos para encontrar todos los registros
	 * de Causal Aprobación Devolución que coincidan con un id específico.
	 *
	 * @param acad_cad representa el Causal Aprobación Devolución de donde se va a tomar el id_circulo para realizar la consulta
	 * @return devuelve el valor de CausalAprobacionDevolucion
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see CausalAprobacionDevolucion
	 */
	public synchronized CausalAprobacionDevolucion findCausalAprobacionDevolucionById(
	    CausalAprobacionDevolucion acad_cad
	)
	    throws B2BException
	{
		DAOManager                 ldm_manager;
		CausalAprobacionDevolucion lcr_datos;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		lcr_datos       = null;

		try
		{
			lcr_datos = DaoCreator.getCausalAprobacionDevolucionDAO(ldm_manager).findById(acad_cad);
		}

		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findCausalAprobacionDevolucionById", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lcr_datos;
	}

	/**
	 * Método para realizar transacciones con la base de datos para encontrar todos los registros
	 * de Causal Corrección que coincidan con un id específico.
	 *
	 * @param acad_cad representa el Causal Corrección de donde se va a tomar el id_circulo para realizar la consulta
	 * @return devuelve el valor de CausalCorreccion
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see CausalCorreccion
	 */
	public synchronized CausalCorreccion findCausalCorreccionById(CausalCorreccion acad_cad)
	    throws B2BException
	{
		DAOManager       ldm_manager;
		CausalCorreccion lcr_datos;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		lcr_datos       = null;

		try
		{
			lcr_datos = DaoCreator.getCausalCorreccionDAO(ldm_manager).findById(acad_cad);
		}

		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findCausalCorreccionById", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lcr_datos;
	}

	/**
	 * Método para realizar transacciones con la base de datos para encontrar todos los registros
	 * de Causal Rechazo Recurso que coincidan con un id específico.
	 *
	 * @param acrr_crr representa el Causal Rechazo Recurso de donde se va a tomar el id para realizar la consulta
	 * @return devuelve el valor de CausalRechazoRecurso
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see CausalRechazoRecurso
	 */
	public synchronized CausalRechazoRecurso findCausalRechazoRecursoById(CausalRechazoRecurso acrr_crr)
	    throws B2BException
	{
		DAOManager           ldm_manager;
		CausalRechazoRecurso lcrr_datos;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		lcrr_datos      = null;

		try
		{
			if(acrr_crr != null)
			{
				String ls_idCausalRechazo;

				ls_idCausalRechazo = acrr_crr.getIdCausalRechazoRecurso();

				if(StringUtils.isValidString(ls_idCausalRechazo))
					lcrr_datos = DaoCreator.getCausalRechazoRecursoDAO(ldm_manager).findById(ls_idCausalRechazo);
			}
		}

		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findCausalRechazoRecursoById", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lcrr_datos;
	}

	/**
	 * Find causal reimpresion by id.
	 *
	 * @param accr_cr Objeto de clase CausalReimpresion con el cual se realiza la busqueda en la tabla SDB_PGN_CAUSAL_REIMPRESION
	 * @return Objeto de clase CausalReimpresion
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public synchronized CausalReimpresion findCausalReimpresionById(CausalReimpresion accr_cr)
	    throws B2BException
	{
		DAOManager        ldm_manager;
		CausalReimpresion lccr_datos;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		lccr_datos      = null;

		try
		{
			lccr_datos = DaoCreator.getCausalReimpresionDAO(ldm_manager).findById(accr_cr);
		}

		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findCausalReimpresionById", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lccr_datos;
	}

	/**
	 * Metodo que se encarga de consultar todos los registros de causales mayor valor con un estado en particular.
	 *
	 * @param as_estado Argumento que determina el estado a consultar, puede ser S o N
	 * @return Colección de datos que cumple con las condiciones enviadas como argumento.
	 * @throws B2BException Señala que se ha producido una excepción
	 *
	 */
	public synchronized Collection<CausalMayorValor> findCausalesMayorValorPorEstado(String as_estado)
	    throws B2BException
	{
		DAOManager                   ldm_manager;
		Collection<CausalMayorValor> lcr_datos;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		lcr_datos       = null;

		try
		{
			lcr_datos = DaoCreator.getCausalMayorValorDAO(ldm_manager).findAllByState(as_estado);
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findCausalesMayorValorPorEstado", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lcr_datos;
	}

	/**
	 * Método para la transaccion con la base de datos para traer todos los
	 * registros existentes por ID en la tabla SDB_PGN_CIRCULO_ACT_ADMIN.
	 *
	 * @param acaa_param contenedor de la informacion con el identificador a consultar
	 * @return devuelve el valor de ActividadEconomica
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see CirculoActAdmin
	 */
	public synchronized CirculoActAdmin findCirculoActAdminById(CirculoActAdmin acaa_param)
	    throws B2BException
	{
		CirculoActAdmin lcaa_datos;

		lcaa_datos = null;

		if(acaa_param != null)
		{
			DAOManager ldm_manager;

			ldm_manager = DaoManagerFactory.getDAOManager();

			try
			{
				lcaa_datos = DaoCreator.getCirculoActAdminDAO(ldm_manager).findById(acaa_param);
			}

			catch(B2BException lb2be_e)
			{
				ldm_manager.setRollbackOnly();

				clh_LOGGER.error("findCirculoActAdminById", lb2be_e);

				throw lb2be_e;
			}
			finally
			{
				ldm_manager.close();
			}
		}

		return lcaa_datos;
	}

	/**
	 * Método para la transaccion con la base de datos para traer todos los
	 * registros existentes por ID en la tabla SDB_PGN_CIRCULO_CATASTRO.
	 *
	 * @param acc_param contenedor de la informacion con el identificador a consultar
	 * @return devuelve el valor de CirculoCatastro
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see CirculoCatastro
	 */
	public synchronized CirculoCatastro findCirculoCatastroById(CirculoCatastro acc_param)
	    throws B2BException
	{
		CirculoCatastro lcc_datos;

		lcc_datos = null;

		if(acc_param != null)
		{
			DAOManager ldm_manager;

			ldm_manager = DaoManagerFactory.getDAOManager();

			try
			{
				String ls_idCirculo;
				String ls_idCatastro;

				ls_idCirculo      = acc_param.getIdCirculo();
				ls_idCatastro     = acc_param.getIdCatastro();

				if(StringUtils.isValidString(ls_idCatastro) && StringUtils.isValidString(ls_idCirculo))
					lcc_datos = DaoCreator.getCirculoCatastroDAO(ldm_manager).findById(ls_idCirculo, ls_idCatastro);
			}

			catch(B2BException lb2be_e)
			{
				ldm_manager.setRollbackOnly();

				clh_LOGGER.error("findCirculoCatastroById", lb2be_e);

				throw lb2be_e;
			}
			finally
			{
				ldm_manager.close();
			}
		}

		return lcc_datos;
	}

	/**
	 * Retorna el valor del objeto de CirculoFestivo.
	 *
	 * @param as_s correspondiente al valor del tipo de objeto String
	 * @return devuelve el valor de CirculoFestivo
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see CirculoFestivo
	 */
	public synchronized CirculoFestivo findCirculoFestivoById(String as_s)
	    throws B2BException
	{
		DAOManager     ldm_manager;
		CirculoFestivo lcr_datos;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		lcr_datos       = null;

		try
		{
			lcr_datos = DaoCreator.getCirculoFestivoDAO(ldm_manager).findById(as_s);
		}

		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findCirculoFestivoById", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lcr_datos;
	}

	/**
	 * Find circulo origen destino by id.
	 *
	 * @param acod_cod de acod cod
	 * @return el valor de circulo origen destino
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public synchronized CirculoOrigenDestino findCirculoOrigenDestinoById(CirculoOrigenDestino acod_cod)
	    throws B2BException
	{
		DAOManager           ldm_manager;
		CirculoOrigenDestino lcod_datos;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		lcod_datos      = null;

		try
		{
			if(acod_cod != null)
			{
				String ls_idCirculoOrigen;
				String ls_idCirculoDestino;

				ls_idCirculoOrigen      = acod_cod.getIdCirculoOrigen();
				ls_idCirculoDestino     = acod_cod.getIdCirculoDestino();

				if(StringUtils.isValidString(ls_idCirculoOrigen) && StringUtils.isValidString(ls_idCirculoDestino))
					lcod_datos = DaoCreator.getCirculoOrigenDestinoDAO(ldm_manager)
							                   .findById(ls_idCirculoOrigen, ls_idCirculoDestino);
			}
		}

		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findCirculoOrigenDestinoById", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lcod_datos;
	}

	/**
	 * Método para realizar transacciones con la base de datos para encontrar todos los registros
	 * de Circulo registral que coincidan con un id especifico.
	 *
	 * @param acr_circulo representa el circulo de donde se va a tomar el id_circulo para realizar la consulta
	 * @return devuelve el valor de CirculoRegistral
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see CirculoRegistral
	 */
	public synchronized CirculoRegistral findCirculoRegistralById(CirculoRegistral acr_circulo)
	    throws B2BException
	{
		DAOManager       ldm_manager;
		CirculoRegistral lcc_datos;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		lcc_datos       = null;

		try
		{
			lcc_datos = DaoCreator.getCirculoRegistralDAO(ldm_manager).findById(acr_circulo);
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findCirculoRegistralById", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lcc_datos;
	}

	/**
	 * Método para realizar transacciones con la base de datos para encontrar todos los registros
	 * de Circulo registral que coincidan con un id especifico.
	 *
	 * @param auc_usuarioCirculo correspondiente al valor del tipo de objeto UsuarioCirculo
	 * @return devuelve el valor de Collection
	 * @throws B2BException Señala que se ha producido una excepción
	 *
	 */
	public synchronized Collection<UsuarioCirculo> findCirculosPorUsuario(UsuarioCirculo auc_usuarioCirculo)
	    throws B2BException
	{
		DAOManager                 ldm_manager;
		Collection<UsuarioCirculo> lcuc_datos;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		lcuc_datos      = null;

		try
		{
			lcuc_datos = DaoCreator.getUsuarioCirculoDAO(ldm_manager).findByUsuario(auc_usuarioCirculo);

			if(!CollectionUtils.isValidCollection(lcuc_datos))
				lcuc_datos = new LinkedList<UsuarioCirculo>();

			UsuarioDAO lu_DAO;
			lu_DAO = DaoCreator.getUsuarioDAO(ldm_manager);

			CirculoRegistralDao lcr_DAO;
			lcr_DAO = DaoCreator.getCirculoRegistralDAO(ldm_manager);

			for(UsuarioCirculo luc_registro : lcuc_datos)
			{
				if(luc_registro != null)
				{
					{
						Usuario lu_userTemp;

						lu_userTemp = luc_registro.getUsuario();

						luc_registro.setUsuario(lu_DAO.findById(lu_userTemp));
					}

					{
						CirculoRegistral lcr_circuloTemp;

						lcr_circuloTemp = new CirculoRegistral();
						lcr_circuloTemp.setIdCirculo(luc_registro.getIdCirculo());

						luc_registro.setCirculo(lcr_DAO.findById(lcr_circuloTemp));
					}
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findCirculosPorUsuario", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lcuc_datos;
	}

	/**
	 * Retorna el valor del objeto de Collection.
	 *
	 * @param auc_usuarioCirculo correspondiente al valor del tipo de objeto UsuarioCirculo
	 * @return devuelve el valor de Collection
	 * @throws B2BException Señala que se ha producido una excepción
	 *
	 */
	public synchronized Collection<UsuarioCirculo> findCirculosPorUsuarioActivo(UsuarioCirculo auc_usuarioCirculo)
	    throws B2BException
	{
		DAOManager                 ldm_manager;
		Collection<UsuarioCirculo> lcuc_datos;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		lcuc_datos      = null;

		try
		{
			lcuc_datos = DaoCreator.getUsuarioCirculoDAO(ldm_manager).findByUsuarioActive(auc_usuarioCirculo);

			if(!CollectionUtils.isValidCollection(lcuc_datos))
				lcuc_datos = new LinkedList<UsuarioCirculo>();

			UsuarioDAO lu_DAO;
			lu_DAO = DaoCreator.getUsuarioDAO(ldm_manager);

			CirculoRegistralDao lcr_DAO;
			lcr_DAO = DaoCreator.getCirculoRegistralDAO(ldm_manager);

			for(UsuarioCirculo luc_registro : lcuc_datos)
			{
				if(luc_registro != null)
				{
					{
						String lu_userTemp;

						lu_userTemp = luc_registro.getIdUsuario();

						luc_registro.setUsuario(lu_DAO.findById(new Usuario(lu_userTemp)));
					}

					{
						String lcr_circuloTemp;

						lcr_circuloTemp = luc_registro.getIdCirculo();

						luc_registro.setCirculo(lcr_DAO.findById(new CirculoRegistral(lcr_circuloTemp, null)));
					}
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findCirculosPorUsuarioActivo", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lcuc_datos;
	}

	/**
	 * Retorna el valor del objeto de ComunidadesEtnicas.
	 *
	 * @param ai_i correspondiente al valor del tipo de objeto int
	 * @return devuelve el valor de CondicionTarifa
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see CondicionTarifa
	 */
	public synchronized ComunidadesEtnicas findComunidadesEtnicasById(int ai_i)
	    throws B2BException
	{
		DAOManager         ldm_manager;
		ComunidadesEtnicas lcce_datos;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		lcce_datos      = null;

		try
		{
			lcce_datos = DaoCreator.getComunidadesEtnicasDAO(ldm_manager).findById(ai_i);
		}

		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findComunidadesEtnicasById", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lcce_datos;
	}

	/**
	 * Retorna el valor del objeto de CondicionTarifa.
	 *
	 * @param as_s correspondiente al valor del tipo de objeto String
	 * @return devuelve el valor de CondicionTarifa
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see CondicionTarifa
	 */
	public synchronized CondicionTarifa findCondicionTarifaById(String as_s)
	    throws B2BException
	{
		DAOManager      ldm_manager;
		CondicionTarifa lcr_datos;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		lcr_datos       = null;

		try
		{
			lcr_datos = DaoCreator.getCondicionTarifaDAO(ldm_manager).findById(as_s);
		}

		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findCondicionTarifaById", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lcr_datos;
	}

	/**
	 * Método para realizar transacciones con la base de datos para encontrar todos los registros
	 * de Constantes que coincidan con un id especifico.
	 *
	 * @param ac_parametros correspondiente al valor del tipo de objeto Constantes
	 * @return devuelve el valor de Constantes
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see Constantes
	 */
	public synchronized Constantes findConstantById(Constantes ac_parametros)
	    throws B2BException
	{
		DAOManager ldm_manager;
		Constantes lc_datos;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		lc_datos        = null;

		try
		{
			lc_datos = DaoCreator.getConstantesDAO(ldm_manager).findById(ac_parametros);
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findConstantById", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lc_datos;
	}

	/**
	 * Método para realizar transacciones con la base de datos para encontrar todos los registros
	 * de Constantes que coincidan con un id especifico.
	 * @param ac_parametros correspondiente al valor del tipo de objeto Constantes
	 * @return devuelve el valor de Constantes
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see Constantes
	 */
	public synchronized Constantes findConstantByIdCYN(Constantes ac_parametros)
	    throws B2BException
	{
		DAOManager ldm_manager;
		Constantes lc_datos;

		ldm_manager     = DaoManagerFactory.getDAOManagerCYN();
		lc_datos        = null;

		try
		{
			lc_datos = DaoCreator.getConstantesDAO(ldm_manager).findById(ac_parametros);
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findConstantByIdCYN", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lc_datos;
	}

	/**
	 * Método de transacciones con la base de datos par aencontrar todos los registros de la tabla SDB_PGN_CONSULTAS
	 * que coincidan con un id consulta específico.
	 *
	 * @param ac_consultas correspondiente al valor del tipo de objeto Consultas
	 * @return devuelve el valor de Consultas
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see Consultas
	 */
	public synchronized Consultas findConsultasById(Consultas ac_consultas)
	    throws B2BException
	{
		DAOManager ldm_manager;
		Consultas  lcr_datos;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		lcr_datos       = null;

		try
		{
			lcr_datos = DaoCreator.getConsultasPgnDAO(ldm_manager).findById(ac_consultas);
		}

		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findConsultasById", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lcr_datos;
	}

	/**
	 * Método de transacciones con la base de datos par aencontrar todos los registros de la tabla SDB_ACC_ENTIDAD_EXTERNA_CONVENIOS
	 * que coincidan con un id consulta específico.
	 *
	 * @param as_numeroConvenio de as numero convenio
	 * @param as_nombreConvenio de as nombre convenio
	 * @return devuelve el valor de Collection de AccEntidadExternaConvenio
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public synchronized Collection<AccEntidadExternaConvenio> findConvenios(
	    String as_numeroConvenio, String as_nombreConvenio
	)
	    throws B2BException
	{
		DAOManager                            ldm_manager;
		Collection<AccEntidadExternaConvenio> lcaeec_datos;
		AccEntidadExternaConvenioDAO          laeecd_entidadExternaConvenioDAO;
		AccEntidadExternaPersonaDAO           laeepd_entidadExternaPersonaDAO;
		AccEntidadExternaDAO                  laeed_entidadExternaDAO;

		ldm_manager                          = DaoManagerFactory.getDAOManager();
		laeecd_entidadExternaConvenioDAO     = DaoCreator.getAccEntidadExternaConvenioDAO(ldm_manager);
		laeed_entidadExternaDAO              = DaoCreator.getAccEntidadExternaDAO(ldm_manager);
		laeepd_entidadExternaPersonaDAO      = DaoCreator.getAccEntidadExternaPersonaDAO(ldm_manager);
		lcaeec_datos                         = null;

		try
		{
			if(StringUtils.isValidString(as_numeroConvenio))
				lcaeec_datos = laeecd_entidadExternaConvenioDAO.findByNumeroConvenio(as_numeroConvenio);

			else if(StringUtils.isValidString(as_nombreConvenio))
			{
				String ls_like;

				ls_like = as_nombreConvenio + "%";

				if(StringUtils.isValidString(ls_like))
					lcaeec_datos = laeecd_entidadExternaConvenioDAO.findByNombreEntidad(ls_like);
			}

			else
				lcaeec_datos = laeecd_entidadExternaConvenioDAO.findAll(false);

			if(CollectionUtils.isValidCollection(lcaeec_datos))
			{
				for(AccEntidadExternaConvenio lc_temp : lcaeec_datos)
				{
					if(lc_temp != null)
					{
						AccEntidadExterna laee_entidadExterna;

						laee_entidadExterna = laeed_entidadExternaDAO.findByIdAccEntidadExterna(
							    lc_temp.getIdEntidadExterna()
							);

						if(laee_entidadExterna != null)
							lc_temp.setNombreEntidad(laee_entidadExterna.getNombre());

						{
							Collection<AccEntidadExternaPersona> lcaeep_entidadExternaPersona;

							lcaeep_entidadExternaPersona = laeepd_entidadExternaPersonaDAO.findByIdEntidadExterna(
								    lc_temp.getIdEntidadExterna(), false
								);

							if(CollectionUtils.isValidCollection(lcaeep_entidadExternaPersona))
							{
								for(AccEntidadExternaPersona laee_temp : lcaeep_entidadExternaPersona)
								{
									if(laee_temp != null)
									{
										String ls_usuario;
										String ls_representanteLegal;

										ls_usuario                = laee_temp.getUsuario();
										ls_representanteLegal     = laee_temp.getRepresentanteLegal();

										if(
										    StringUtils.isValidString(ls_usuario)
											    && (StringUtils.isValidString(ls_representanteLegal)
											    && ls_representanteLegal.equalsIgnoreCase(EstadoCommon.S))
										)
											lc_temp.setUsuarioAsignado(ls_usuario);
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

			clh_LOGGER.error("findConvenios", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lcaeec_datos;
	}

	/**
	 * Método para realizar transacciones con la base de datos con el fin de encontrar todos los registros
	 * que coincidan con un id_coordenada especifico.
	 *
	 * @param as_s representa la coordenada de donde se extrae el id_coordenada para su posterior consulta
	 * @return lcr_datos Collection de Coordenada
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public synchronized Coordenada findCoordenadaById(String as_s)
	    throws B2BException
	{
		DAOManager ldm_manager;
		Coordenada lcr_datos;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		lcr_datos       = null;

		try
		{
			lcr_datos = DaoCreator.getCoordenadaDAO(ldm_manager).findById(as_s);
		}

		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findCoordenadaById", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lcr_datos;
	}

	/**
	 * Método para realizar transacciones con la base de datos con el fin de encontrar todos los registros
	 * que coincidan con un id_departamento especifico.
	 *
	 * @param ac_parametros representa el departamento de donde se extrae el id_departamento para su posterior consulta
	 * @return devuelve el valor de Departamento
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see Departamento
	 */
	public synchronized Departamento findDepartamentoById(Departamento ac_parametros)
	    throws B2BException
	{
		DAOManager   ldm_manager;
		Departamento lc_datos;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		lc_datos        = null;

		try
		{
			lc_datos = DaoCreator.getDepartamentoDAO(ldm_manager).findById(ac_parametros);
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findDepartamentoById", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lc_datos;
	}

	/**
	 * Método para la transaccion con la base de datos para traer todos los
	 * registros existentes por ID en la tabla SDB_PGN_DEPENDENCIA_SNR.
	 *
	 * @param adsnr_param contenedor de la informacion con el identificador a consultar
	 * @return devuelve el valor de DependenciaSNR
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see Canal
	 */
	public synchronized DependenciaSNR findDependenciaSNRById(DependenciaSNR adsnr_param)
	    throws B2BException
	{
		DependenciaSNR ldsnr_datos;

		ldsnr_datos = null;

		if(adsnr_param != null)
		{
			DAOManager ldm_manager;

			ldm_manager = DaoManagerFactory.getDAOManager();

			try
			{
				String ls_idDepencia;

				ls_idDepencia = adsnr_param.getIdDependencia();

				if(StringUtils.isValidString(ls_idDepencia))
					ldsnr_datos = DaoCreator.getDependenciaSNRDAO(ldm_manager).findById(ls_idDepencia);
			}

			catch(B2BException lb2be_e)
			{
				ldm_manager.setRollbackOnly();

				clh_LOGGER.error("findDependenciaSNRById", lb2be_e);

				throw lb2be_e;
			}
			finally
			{
				ldm_manager.close();
			}
		}

		return ldsnr_datos;
	}

	/**
	 * Retorna el valor del objeto de DetalleAntSistema.
	 *
	 * @param as_idDetalleAntSistema correspondiente al valor de tipo String detalleAntSistema
	 * @param as_idDatosAntSistema correspondiente al valor de tipo String as_idDatosAntSistema
	 * @return devuelve el valor de DetalleAntSistema
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see DetalleAntSistema
	 */
	public synchronized DetalleAntSistema findDetalleAntSistemaByDetalleYDatosAntSis(
	    String as_idDetalleAntSistema, String as_idDatosAntSistema
	)
	    throws B2BException
	{
		DAOManager        ldm_manager;
		DetalleAntSistema ldas_datos;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		ldas_datos      = null;

		try
		{
			ldas_datos = DaoCreator.getDetalleAntSistemaDAO(ldm_manager)
					                   .findByDetalleYDatosAntSis(as_idDetalleAntSistema, as_idDatosAntSistema);
		}

		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findDetalleAntSistemaByDetalleYDatosAntSis", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return ldas_datos;
	}

	/**
	 * Find detalle proceso consulta by id.
	 *
	 * @param acdpc_dpc Objeto de clase DetalleProcesoConsulta con el cual se realiza la busqueda en la tabla SDB_PGN_DETALLE_PROCESO_CONSULTA
	 * @return Objeto de clase DetalleProcesoConsulta
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public synchronized DetalleProcesoConsulta findDetalleProcesoConsultaById(DetalleProcesoConsulta acdpc_dpc)
	    throws B2BException
	{
		DAOManager             ldm_manager;
		DetalleProcesoConsulta lcdpc_datos;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		lcdpc_datos     = null;

		try
		{
			lcdpc_datos = DaoCreator.getDetalleProcesoConsultaDAO(ldm_manager).findById(acdpc_dpc);
		}

		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findDetalleProcesoConsultaById", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lcdpc_datos;
	}

	/**
	 * Método para en encontrar DominioRRR en la tabla SDB_COL_DOMINIO_RRR con un ID_DOMINIO_RRR específico.
	 *
	 * @param adr_parametros correspondiente al valor del tipo de objeto DominioRrr
	 * @return devuelve el valor de DominioRrr
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see DominioRrr
	 */
	public synchronized DominioRrr findDominioRRRById(DominioRrr adr_parametros)
	    throws B2BException
	{
		DAOManager ldm_manager;
		DominioRrr ldr_return;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		ldr_return      = null;

		try
		{
			ldr_return = DaoCreator.getDominioRrrDAO(ldm_manager).findById(adr_parametros);
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findDominioRRRById", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return ldr_return;
	}

	/**
	 * Método para la transaccion con la base de datos para traer todos los
	 * registros existentes por ID en la tabla SDB_PGN_ENTIDAD_RECAUDADORA.
	 *
	 * @param atr_tr correspondiente al valor del tipo de objeto EntidadRecaudadora
	 * @return devuelve el valor de EntidadRecaudadora
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see EntidadRecaudadora
	 */
	public synchronized EntidadRecaudadora findEntidadRecaudadoraById(EntidadRecaudadora atr_tr)
	    throws B2BException
	{
		DAOManager         ldm_manager;
		EntidadRecaudadora ltr_datos;

		ldm_manager     = DaoManagerFactory.getDAOManagerNPA();
		ltr_datos       = null;

		try
		{
			ltr_datos = DaoCreator.getEntidadRecaudadoraDAO(ldm_manager).findById(atr_tr);
		}

		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findEntidadRecaudadoraById", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return ltr_datos;
	}

	/**
	 * Método para la transaccion con la base de datos para traer todos los
	 * registros existentes por ID en la tabla SDB_PNG_ENTIDADES_ALERTA.
	 *
	 * @param aea_param contenedor de la informacion con el identificador a consultar
	 * @return devuelve el valor de EntidadesAlerta
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see Canal
	 */
	public synchronized EntidadesAlerta findEntidadesAlertaById(EntidadesAlerta aea_param)
	    throws B2BException
	{
		EntidadesAlerta lea_datos;

		lea_datos = null;

		if(aea_param != null)
		{
			DAOManager ldm_manager;

			ldm_manager = DaoManagerFactory.getDAOManager();

			try
			{
				long ll_idEntidad;

				ll_idEntidad = aea_param.getIdEntidad();

				if(ll_idEntidad > 0L)
					lea_datos = DaoCreator.getEntidadesAlertaDAO(ldm_manager).findById(ll_idEntidad);
			}

			catch(B2BException lb2be_e)
			{
				ldm_manager.setRollbackOnly();

				clh_LOGGER.error("findEntidadesAlertaById", lb2be_e);

				throw lb2be_e;
			}
			finally
			{
				ldm_manager.close();
			}
		}

		return lea_datos;
	}

	/**
	 * Método para la transaccion con la base de datos para traer todos los
	 * registros existentes por ID en la tabla SDB_PNG_ESTADO_ANOTACION.
	 *
	 * @param aea_ea correspondiente al valor del tipo de objeto EstadoAnotacion
	 * @return devuelve el valor de EstadoAnotacion
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see EstadoAnotacion
	 */
	public synchronized EstadoAnotacion findEstadoAnotacionById(EstadoAnotacion aea_ea)
	    throws B2BException
	{
		DAOManager      ldm_manager;
		EstadoAnotacion lea_datos;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		lea_datos       = null;

		try
		{
			lea_datos = DaoCreator.getEstadoAnotacionDAO(ldm_manager).findById(aea_ea);
		}

		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findEstadoAnotacionById", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lea_datos;
	}

	/**
	 * Método para la transaccion con la base de datos para traer todos los
	 * registros existentes por ID en la tabla SDB_PNG_ESTADO_NUPRE.
	 *
	 * @param aen_en correspondiente al valor del tipo de objeto EstadoNupre
	 * @return devuelve el valor de EstadoNupre
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see EstadoNupre
	 */
	public synchronized EstadoNupre findEstadoNupreById(EstadoNupre aen_en)
	    throws B2BException
	{
		DAOManager  ldm_manager;
		EstadoNupre len_datos;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		len_datos       = null;

		try
		{
			len_datos = DaoCreator.getEstadoNupreDAO(ldm_manager).findById(aen_en);
		}

		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findEstadoNupreById", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return len_datos;
	}

	/**
	 * Método para la transaccion con la base de datos para traer todos los
	 * registros existentes por ID en la tabla SDB_PNG_ESTADO_PREDIO.
	 *
	 * @param aep_ep correspondiente al valor del tipo de objeto EstadoPredio
	 * @return devuelve el valor de EstadoPredio
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see EstadoPredio
	 */
	public synchronized EstadoPredio findEstadoPredioById(EstadoPredio aep_ep)
	    throws B2BException
	{
		DAOManager   ldm_manager;
		EstadoPredio lep_datos;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		lep_datos       = null;

		try
		{
			if(aep_ep != null)
				lep_datos = DaoCreator.getEstadoPredioDao(ldm_manager).findById(aep_ep);
		}

		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findEstadoPredioById", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lep_datos;
	}

	/**
	 * Método para la transaccion con la base de datos para traer todos los
	 * registros existentes por ID en la tabla SDB_PNG_ESTADO_REGISTRO.
	 *
	 * @param aer_er correspondiente al valor del tipo de objeto EstadoRegistro
	 * @return devuelve el valor de EstadoRegistro
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see EstadoRegistro
	 */
	public synchronized EstadoRegistro findEstadoRegistroById(EstadoRegistro aer_er)
	    throws B2BException
	{
		DAOManager     ldm_manager;
		EstadoRegistro ler_datos;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		ler_datos       = null;

		try
		{
			ler_datos = DaoCreator.getEstadoRegistroDAO(ldm_manager).findById(aer_er);
		}

		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findEstadoRegistroById", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return ler_datos;
	}

	/**
	 * Método para la transaccion con la base de datos para traer todos los
	 * registros existentes por ID en la tabla SDB_PGN_ESTADOS.
	 *
	 * @param ae_e correspondiente al valor del tipo de objeto Estados
	 * @return devuelve el valor de Estados
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see Estados
	 */
	public synchronized Estados findEstadosById(Estados ae_e)
	    throws B2BException
	{
		DAOManager ldm_manager;
		Estados    le_datos;

		ldm_manager     = DaoManagerFactory.getDAOManagerCYN();
		le_datos        = null;

		try
		{
			le_datos = DaoCreator.getEstadosDAO(ldm_manager).findById(ae_e);
		}

		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findEstadosById", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return le_datos;
	}

	/**
	 *  Método de transacciones con la base de datos para consultar los registros de la tabla SDB_PGN_ETAPA.
	 *
	 * @param ae_etapa correspondiente al valor del tipo de objeto Etapa
	 * @return Objeto de tipo Etapa le_etapa
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see Etapa
	 */
	public synchronized Etapa findEtapaById(Etapa ae_etapa)
	    throws B2BException
	{
		DAOManager ldm_manager;
		Etapa      le_etapa;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		le_etapa        = null;

		try
		{
			le_etapa = DaoCreator.getEtapaDAO(ldm_manager).findById(ae_etapa);
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findEtapaById", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return le_etapa;
	}

	/**
	 * Método para encontrar todos los registros de la base de datos para la tabla SDB_PGN_MOTIVO_TRAMITE
	 * que coincidan con un id específico.
	 *
	 * @return devuelve el valor de Collection
	 * @throws B2BException Señala que se ha producido una excepción
	 *
	 */
	public synchronized Collection<MotivoTramite> findEtapaById()
	    throws B2BException
	{
		DAOManager                ldm_manager;
		Collection<MotivoTramite> lcr_datos;

		ldm_manager     = DaoManagerFactory.getDAOManagerWorkflow();
		lcr_datos       = null;

		try
		{
			lcr_datos = DaoCreator.getMotivoTramiteDAO(ldm_manager).findEtapaById();
		}

		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findEtapaById", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lcr_datos;
	}

	/**
	 * Método de transacciones con la base de datos para  encontrar UsuarioEtapa en la tabla SDB_PNG_USUARIO_ETAPA con un ID_USUARIO específico.
	 *
	 * @param aue_usuarioEtapa objeto al cual se le extrae el ID_USUARIO para realizar la consulta en la base de datos
	 * @return devuelve el valor de Collection
	 * @throws B2BException Señala que se ha producido una excepción
	 *
	 */
	public synchronized Collection<UsuarioEtapa> findEtapasPorUsuario(UsuarioEtapa aue_usuarioEtapa)
	    throws B2BException
	{
		DAOManager               ldm_manager;
		Collection<UsuarioEtapa> lcue_datos;
		Collection<UsuarioEtapa> lcue_return;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		lcue_datos      = null;

		try
		{
			lcue_datos = DaoCreator.getUsuarioEtapaDAO(ldm_manager).findByUsuario(aue_usuarioEtapa);

			if(!CollectionUtils.isValidCollection(lcue_datos))
				lcue_datos = new LinkedList<UsuarioEtapa>();

			lcue_return = new LinkedList<UsuarioEtapa>();

			UsuarioDAO lu_DAO;
			lu_DAO = DaoCreator.getUsuarioDAO(ldm_manager);

			EtapaDAO lce_DAO;
			lce_DAO = DaoCreator.getEtapaDAO(ldm_manager);

			for(UsuarioEtapa lue_registro : lcue_datos)
			{
				if(lue_registro != null)
				{
					Usuario lu_userTemp;
					lu_userTemp     = lue_registro.getUsuario();

					lu_userTemp = lu_DAO.findById(lu_userTemp);

					lue_registro.setUsuario(lu_userTemp);

					Etapa le_etapaTemp;
					le_etapaTemp     = lue_registro.getEtapa();

					le_etapaTemp = lce_DAO.findById(le_etapaTemp);

					lue_registro.setEtapa(le_etapaTemp);

					lcue_return.add(lue_registro);
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findEtapassPorUsuario", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lcue_datos;
	}

	/**
	 * Retorna el valor del objeto de Collection.
	 *
	 * @param aue_usuarioEtapa correspondiente al valor del tipo de objeto UsuarioEtapa
	 * @return devuelve el valor de Collection
	 * @throws B2BException Señala que se ha producido una excepción
	 *
	 */
	public synchronized Collection<UsuarioEtapa> findEtapasPorUsuarioActivo(UsuarioEtapa aue_usuarioEtapa)
	    throws B2BException
	{
		DAOManager               ldm_manager;
		Collection<UsuarioEtapa> lcue_datos;
		Collection<UsuarioEtapa> lcue_return;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		lcue_datos      = null;

		try
		{
			lcue_datos = DaoCreator.getUsuarioEtapaDAO(ldm_manager).findByUsuarioActive(aue_usuarioEtapa);

			if(!CollectionUtils.isValidCollection(lcue_datos))
				lcue_datos = new LinkedList<UsuarioEtapa>();

			lcue_return = new LinkedList<UsuarioEtapa>();

			UsuarioDAO lu_DAO;
			lu_DAO = DaoCreator.getUsuarioDAO(ldm_manager);

			EtapaDAO lce_DAO;
			lce_DAO = DaoCreator.getEtapaDAO(ldm_manager);

			for(UsuarioEtapa lue_registro : lcue_datos)
			{
				if(lue_registro != null)
				{
					Usuario lu_userTemp;
					lu_userTemp     = lue_registro.getUsuario();

					lu_userTemp = lu_DAO.findById(lu_userTemp);

					lue_registro.setUsuario(lu_userTemp);

					Etapa le_etapaTemp;
					le_etapaTemp     = lue_registro.getEtapa();

					le_etapaTemp = lce_DAO.findById(le_etapaTemp);

					lue_registro.setEtapa(le_etapaTemp);

					lcue_return.add(lue_registro);
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findEtapasPorUsuarioActivo", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lcue_datos;
	}

	/**
	 * Método para realizar transacciones con la base de datos con el fin de encontrar todos los registros
	 * que coincidan con un id_proceso_automatico especifico.
	 *
	 * @param ac_parametros representa el proceso automatico de donde se extrae el id_proceso_automatico para su posterior consulta
	 * @return devuelve el valor de FestivoNacional
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see FestivoNacional
	 */
	public synchronized FestivoNacional findFestivoNacionalById(FestivoNacional ac_parametros)
	    throws B2BException
	{
		DAOManager      ldm_manager;
		FestivoNacional lc_datos;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		lc_datos        = null;

		try
		{
			lc_datos = DaoCreator.getFestivoNacionalDAO(ldm_manager).findById(ac_parametros);
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findFestivoNacionalById", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lc_datos;
	}

	/**
	 * Método para realizar transacciones con la base de datos con el fin de encontrar todos los registros
	 * que coincidan con un id_gobernacion especifico.
	 *
	 * @param ac_parametros representa el proceso automatico de donde se extrae el id_gobernacion para su posterior consulta
	 * @return devuelve el valor de Gobernacion
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see Gobernacion
	 */
	public synchronized Gobernacion findGobernacionById(Gobernacion ac_parametros)
	    throws B2BException
	{
		DAOManager  ldm_manager;
		Gobernacion lc_datos;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		lc_datos        = null;

		try
		{
			lc_datos = DaoCreator.getGobernacionDAO(ldm_manager).findById(ac_parametros);
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findGobernacionById", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lc_datos;
	}

	/**
	 * Método para la transaccion con la base de datos para traer todos los
	 * registros existentes por ID en la tabla SDB_PNG_GRUPO_NATURALEZA_JURIDICA.
	 *
	 * @param agnj_gnj Argumento de tipo <code>GrupoNaturalezaJuridica</code> correspondiente al valor del tipo de objeto GrupoNaturalezaJuridica.
	 * @return devuelve Variable de tipo <code>GrupoNaturalezaJuridica</code> que contiene el valor de GrupoNaturalezaJuridica.
	 * @throws B2BException Señala que se ha producido una excepción.
	 * @see GrupoNaturalezaJuridica
	 */
	public synchronized GrupoNaturalezaJuridica findGrupoNaturalezaJuridicaById(GrupoNaturalezaJuridica agnj_gnj)
	    throws B2BException
	{
		DAOManager              ldm_manager;
		GrupoNaturalezaJuridica lgnj_datos;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		lgnj_datos      = null;

		try
		{
			lgnj_datos = DaoCreator.getGrupoNaturalezaJuridicaDAO(ldm_manager).findById(agnj_gnj);
		}

		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findGrupoNaturalezaJuridicaById", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lgnj_datos;
	}

	/**
	 * Método para realizar transacciones con la base de datos con el fin de encontrar todas las
	 * imagenes con un id_constante especifico.
	 *
	 * @param ac_parametros representa el objeto de tipo constantes para encontrar su imagen
	 * @return devuelve el valor de Constantes
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see Constantes
	 */
	public synchronized Constantes findImageById(Constantes ac_parametros)
	    throws B2BException
	{
		DAOManager ldm_manager;
		Constantes lc_datos;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		lc_datos        = null;

		try
		{
			lc_datos = DaoCreator.getConstantesDAO(ldm_manager).findImagen(ac_parametros);

			if(lc_datos == null)
				throw new B2BException(ErrorKeys.NO_SE_ENCONTRARON_REGISTROS);

			if(lc_datos.getImagenes() == null)
				throw new B2BException(ErrorKeys.NO_SE_ENCONTRARO_IMAGEN);
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findImageById", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lc_datos;
	}

	/**
	 * Método para realizar transacciones con la base de datos con el fin de encontrar todos los registros
	 * que coincidan con un id instancia e id campo especifico.
	 *
	 * @param ac_parametros representa el proceso automatico de donde se extrae el id_instancia e id_campo para su posterior consulta
	 * @return devuelve el valor de InstanciaConsulta
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see InstanciaConsulta
	 */
	public synchronized InstanciaConsulta findInstanciaConsultaById(InstanciaConsulta ac_parametros)
	    throws B2BException
	{
		DAOManager        ldm_manager;
		InstanciaConsulta lc_datos;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		lc_datos        = null;

		try
		{
			lc_datos = DaoCreator.getInstanciaConsultaDAO(ldm_manager).findById(ac_parametros);
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findInstanciaConsultaById", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lc_datos;
	}

	/**
	 * Find interesado documento tipo by id.
	 *
	 * @param acidt_cidt Objeto de clase InteresadoDocumentoTipo con el cual se realiza la busqueda en la tabla SDB_COL_INTERESADO_DOCUMENTO_TIPO
	 * @return Objeto de clase InteresadoDocumentoTipo
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public synchronized InteresadoDocumentoTipo findInteresadoDocumentoTipoById(InteresadoDocumentoTipo acidt_cidt)
	    throws B2BException
	{
		DAOManager              ldm_manager;
		InteresadoDocumentoTipo lcidt_datos;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		lcidt_datos     = null;

		try
		{
			lcidt_datos = DaoCreator.getInteresadoDocumentoTipoDAO(ldm_manager).findById(acidt_cidt);
		}

		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findInteresadoDocumentoTipoById", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lcidt_datos;
	}

	/**
	 * Método de transacciones con la base de datos para encontrar todos los registros que coincidan con un ID_NATURAL_GENERO especifico
	 * de la tabla SDB_COL_INTERESADO_NATURAL_GENERO.
	 *
	 * @param atp_parametros correspondiente al valor del tipo de objeto InteresadoNaturalGenero
	 * @return devuelve el valor de InteresadoNaturalGenero
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see InteresadoNaturalGenero
	 */
	public synchronized InteresadoNaturalGenero findInteresadoNaturalGeneroById(InteresadoNaturalGenero atp_parametros)
	    throws B2BException
	{
		DAOManager              ldm_manager;
		InteresadoNaturalGenero ltp_datos;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		ltp_datos       = null;

		try
		{
			ltp_datos = DaoCreator.getInteresadoNaturalGeneroDAO(ldm_manager).findById(atp_parametros);
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findInteresadoNaturalGeneroById", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return ltp_datos;
	}

	/**
	 * Método de transacciones con la base de datos para encontrar todos los registros que coincidan con un ID_LETRA especifico
	 * de la tabla SDB_PNG_LETRA_EJE.
	 *
	 * @param as_idLetra de as id letra
	 * @return el valor de letra eje
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public synchronized LetraEje findLetraEjeById(String as_idLetra)
	    throws B2BException
	{
		DAOManager ldm_manager;
		LetraEje   lle_datos;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		lle_datos       = null;

		try
		{
			lle_datos = DaoCreator.getLetraEjeDAO(ldm_manager).findById(as_idLetra);
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findLetraEjeById", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lle_datos;
	}

	/**
	 * Método para realizar transacciones con la base de datos con el fin de encontrar todos los registros
	 * que coincidan con un id_libroantiguoSistema especifico.
	 *
	 * @param ac_parametros representa el libro antiguo sistema de donde se extrae el id_libroantiguoSistema para su posterior consulta
	 * @return devuelve el valor de LibroAntiguoSistema
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see LibroAntiguoSistema
	 */
	public synchronized LibroAntiguoSistema findLibroAntiguoSistemaById(LibroAntiguoSistema ac_parametros)
	    throws B2BException
	{
		DAOManager          ldm_manager;
		LibroAntiguoSistema lc_datos;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		lc_datos        = null;

		try
		{
			lc_datos = DaoCreator.getLibroAntiguoSistemaDAO(ldm_manager).findById(ac_parametros);
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findLibroAntiguoSistemaById", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lc_datos;
	}

	/**
	 * Método para la transaccion con la base de datos para traer todos los
	 * registros existentes por ID en la tabla SDB_PGN_LIBRO_TESTAMENTO.
	 *
	 * @param alt_param contenedor de la informacion con el identificador a consultar
	 * @return devuelve el valor de LibroTestamento
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see Canal
	 */
	public synchronized LibroTestamento findLibroTestamentoById(LibroTestamento alt_param)
	    throws B2BException
	{
		LibroTestamento llt_datos;

		llt_datos = null;

		if(alt_param != null)
		{
			DAOManager ldm_manager;

			ldm_manager = DaoManagerFactory.getDAOManager();

			try
			{
				llt_datos = DaoCreator.getLibroTestamentoDAO(ldm_manager).findById(alt_param);
			}

			catch(B2BException lb2be_e)
			{
				ldm_manager.setRollbackOnly();

				clh_LOGGER.error("findLibroTestamentoById", lb2be_e);

				throw lb2be_e;
			}
			finally
			{
				ldm_manager.close();
			}
		}

		return llt_datos;
	}

	/**
	 * Método de transacciones con la base de datos para encontrar todos los registros que coincidan con un ID_LINEA_PRODUCCION especifico
	 * de la tabla SDB_PGN_LINEA_PRODUCCION.
	 *
	 * @param alp_parametros objeto del cual se le extrae el ID_LINEA_PRODUCCION para realizar la consulta en la base de datos
	 * @return devuelve el valor de LineaProduccion
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see LineaProduccion
	 */
	public synchronized LineaProduccion findLineaProduccionById(LineaProduccion alp_parametros)
	    throws B2BException
	{
		DAOManager      ldm_manager;
		LineaProduccion llp_datos;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		llp_datos       = null;

		try
		{
			llp_datos = DaoCreator.getLineaProduccionDAO(ldm_manager).findById(alp_parametros);
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findLineaProduccionById", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return llp_datos;
	}

	/**
	 * Método de transacciones con la base de datos para encontrar todos los registros que coincidan con un ID_USUARIO especifico
	 * de la tabla SDB_PGN_USUARIO_LINEA.
	 *
	 * @param aul_usuarioLinea objeto del cual se le extrae el ID_USUARIO para la consulta en la base de datos
	 * @return devuelve el valor de Collection
	 * @throws B2BException Señala que se ha producido una excepción
	 *
	 */
	public synchronized Collection<UsuarioLinea> findLineasPorUsuario(UsuarioLinea aul_usuarioLinea)
	    throws B2BException
	{
		DAOManager               ldm_manager;
		Collection<UsuarioLinea> lcul_datos;
		Collection<UsuarioLinea> lcul_return;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		lcul_datos      = null;

		try
		{
			lcul_datos = DaoCreator.getUsuarioLineaDAO(ldm_manager).findByUsuario(aul_usuarioLinea);

			if(!CollectionUtils.isValidCollection(lcul_datos))
				lcul_datos = new LinkedList<UsuarioLinea>();

			lcul_return = new LinkedList<UsuarioLinea>();

			UsuarioDAO lu_DAO;
			lu_DAO = DaoCreator.getUsuarioDAO(ldm_manager);

			LineaProduccionDAO llp_DAO;
			llp_DAO = DaoCreator.getLineaProduccionDAO(ldm_manager);

			for(UsuarioLinea lul_registro : lcul_datos)
			{
				if(lul_registro != null)
				{
					Usuario lu_userTemp;
					lu_userTemp     = lul_registro.getUsuario();

					lu_userTemp = lu_DAO.findById(lu_userTemp);

					lul_registro.setUsuario(lu_userTemp);

					LineaProduccion le_lineaTemp;
					le_lineaTemp     = lul_registro.getLineaProduccion();

					le_lineaTemp = llp_DAO.findById(le_lineaTemp);

					lul_registro.setLineaProduccion(le_lineaTemp);

					lcul_return.add(lul_registro);
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findLineasPorUsuario", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lcul_return;
	}

	/**
	 * Retorna el valor del objeto de Collection.
	 *
	 * @param aul_usuarioLinea correspondiente al valor del tipo de objeto UsuarioLinea
	 * @return devuelve el valor de Collection
	 * @throws B2BException Señala que se ha producido una excepción
	 *
	 */
	public synchronized Collection<UsuarioLinea> findLineasPorUsuarioActivo(UsuarioLinea aul_usuarioLinea)
	    throws B2BException
	{
		DAOManager               ldm_manager;
		Collection<UsuarioLinea> lcul_datos;
		Collection<UsuarioLinea> lcul_return;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		lcul_datos      = null;

		try
		{
			lcul_datos = DaoCreator.getUsuarioLineaDAO(ldm_manager).findByUsuarioActive(aul_usuarioLinea);

			if(!CollectionUtils.isValidCollection(lcul_datos))
				lcul_datos = new LinkedList<UsuarioLinea>();

			lcul_return = new LinkedList<UsuarioLinea>();

			UsuarioDAO lu_DAO;
			lu_DAO = DaoCreator.getUsuarioDAO(ldm_manager);

			LineaProduccionDAO llp_DAO;
			llp_DAO = DaoCreator.getLineaProduccionDAO(ldm_manager);

			for(UsuarioLinea lul_registro : lcul_datos)
			{
				if(lul_registro != null)
				{
					Usuario lu_userTemp;
					lu_userTemp     = lul_registro.getUsuario();

					lu_userTemp = lu_DAO.findById(lu_userTemp);

					lul_registro.setUsuario(lu_userTemp);

					LineaProduccion le_lineaTemp;
					le_lineaTemp     = lul_registro.getLineaProduccion();

					le_lineaTemp = llp_DAO.findById(le_lineaTemp);

					lul_registro.setLineaProduccion(le_lineaTemp);

					lcul_return.add(lul_registro);
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findLineasPorUsuarioActivo", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lcul_return;
	}

	/**
	 * Retorna el valor del objeto de MedidaArea.
	 *
	 * @param ama_ma correspondiente al valor del tipo de objeto MedidaArea
	 * @return devuelve el valor de MedidaArea
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see MedidaArea
	 */
	public synchronized MedidaArea findMedidaAreaByCodigo(MedidaArea ama_ma)
	    throws B2BException
	{
		DAOManager ldm_manager;
		MedidaArea lma_datos;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		lma_datos       = null;

		try
		{
			if(ama_ma != null)
				lma_datos = DaoCreator.getMedidaAreaDAO(ldm_manager).findByCodigo(ama_ma);
		}

		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findMedidaAreaByCodigo", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lma_datos;
	}

	/**
	 * Retorna el valor del objeto de MedidaArea.
	 *
	 * @param as_param correspondiente al valor del tipo de objeto String
	 * @return devuelve el valor de MedidaArea
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see MedidaArea
	 */
	public synchronized MedidaArea findMedidaAreaById(String as_param)
	    throws B2BException
	{
		DAOManager ldm_manager;
		MedidaArea lma_datos;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		lma_datos       = null;

		try
		{
			if(StringUtils.isValidString(as_param))
				lma_datos = DaoCreator.getMedidaAreaDAO(ldm_manager).findById(as_param);
		}

		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findMedidaAreaById", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lma_datos;
	}

	/**
	 * Método para la transaccion con la base de datos para traer todos los
	 * registros existentes por ID en la tabla SDB_PGN_MEDIO_RECAUDO.
	 *
	 * @param as_s correspondiente al valor del tipo de objeto String
	 * @return devuelve el valor de MedioRecaudo
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see MedioRecaudo
	 */
	public synchronized MedioRecaudo findMedioRecaudoById(String as_s)
	    throws B2BException
	{
		DAOManager   ldm_manager;
		MedioRecaudo lmr_datos;

		ldm_manager     = DaoManagerFactory.getDAOManagerNPA();
		lmr_datos       = null;

		try
		{
			lmr_datos = DaoCreator.getMedioRecaudoDAO(ldm_manager).findById(as_s);
		}

		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findMedioRecaudoById", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lmr_datos;
	}

	/**
	 * Método para la transaccion con la base de datos para traer todos los
	 * registros existentes por ID en la tabla SDB_PGN_MENSAJE_RESPUESTA.
	 *
	 * @param as_s correspondiente al valor del tipo de objeto String
	 * @return devuelve el valor de MensajeRespuesta
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see MensajeRespuesta
	 */
	public synchronized MensajeRespuesta findMensajeRespuestaById(String as_s)
	    throws B2BException
	{
		DAOManager       ldm_manager;
		MensajeRespuesta lmr_datos;

		ldm_manager     = DaoManagerFactory.getDAOManagerNPA();
		lmr_datos       = null;

		try
		{
			lmr_datos = DaoCreator.getMensajeRespuestaDAO(ldm_manager).findById(as_s);
		}

		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findMensajeRespuestaById", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lmr_datos;
	}

	/**
	 * Retorna el valor del objeto de MotivoTramite.
	 *
	 * @param acad_cad correspondiente al valor del tipo de objeto MotivoTramite
	 * @return devuelve el valor de MotivoTramite
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see MotivoTramite
	 */
	public synchronized MotivoTramite findMotivoTramiteById(MotivoTramite acad_cad)
	    throws B2BException
	{
		DAOManager    ldm_manager;
		MotivoTramite lcr_datos;

		ldm_manager     = DaoManagerFactory.getDAOManagerWorkflow();
		lcr_datos       = null;

		try
		{
			lcr_datos = DaoCreator.getMotivoTramiteDAO(ldm_manager).findById(acad_cad);
		}

		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findMotivoTramiteById", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lcr_datos;
	}

	/**
	 * Retorna el valor del objeto de MotivoTramite.
	 *
	 * @param as_idMotivoTramite correspondiente al valor del id motivo trámite
	 * @return devuelve el valor de MotivoTramite
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public synchronized MotivoTramite findMotivoTramiteById(String as_idMotivoTramite)
	    throws B2BException
	{
		DAOManager    ldm_manager;
		MotivoTramite lcr_datos;

		ldm_manager     = DaoManagerFactory.getDAOManagerWorkflow();
		lcr_datos       = null;

		try
		{
			lcr_datos = DaoCreator.getMotivoTramiteDAO(ldm_manager).findById(as_idMotivoTramite);
		}

		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findMotivoTramiteById", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lcr_datos;
	}

	/**
	 * Método de transacciones con la base de datos para encontrar todos los registros que coincidan con un ID_MUNICIPIO especifico
	 * de la tabla SDB_PGN_MUNICIPIO.
	 *
	 * @param ac_parametros objeto del cual se extrae el ID_MUNICIPIO para realizar la consulta en la base de datos
	 * @return devuelve el valor de Municipio
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see Municipio
	 */
	public synchronized Municipio findMunicipioById(Municipio ac_parametros)
	    throws B2BException
	{
		DAOManager ldm_manager;
		Municipio  lc_datos;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		lc_datos        = null;

		try
		{
			lc_datos = DaoCreator.getMunicipioDAO(ldm_manager).findById(ac_parametros);
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findMunicipioById", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lc_datos;
	}

	/**
	 * Método para encontrar todos los registros de la base de datos para la tabla
	 * SDB_PNG_NATURALEZA_JURIDICA.
	 *
	 * @return el valor de collection
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public synchronized Collection<NaturalezaJuridica> findNaturalezaJuridica()
	    throws B2BException
	{
		DAOManager                     ldm_manager;
		Collection<NaturalezaJuridica> lcnj_datos;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		lcnj_datos      = null;

		try
		{
			lcnj_datos = DaoCreator.getNaturalezaJuridicaDAO(ldm_manager).findAll();
		}

		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findAllNaturalezaJuridica", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lcnj_datos;
	}

	/**
	 * Find naturaleza juridica by id.
	 *
	 * @param acnj_nj Objeto de clase NaturalezaJuridica con el cual se realiza la busqueda en la tabla SDB_PNG_NATURALEZA_JURIDICA
	 * @return Objeto de clase NaturalezaJuridica
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public synchronized NaturalezaJuridica findNaturalezaJuridicaById(NaturalezaJuridica acnj_nj)
	    throws B2BException
	{
		DAOManager         ldm_manager;
		NaturalezaJuridica lcnj_datos;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		lcnj_datos      = null;

		try
		{
			lcnj_datos = DaoCreator.getNaturalezaJuridicaDAO(ldm_manager).findByIdGeneral(acnj_nj);
		}

		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findNaturalezaJuridicaById", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lcnj_datos;
	}

	/**
	 * Retorna el valor del objeto de NotaDevolutiva.
	 *
	 * @param acad_cad correspondiente al valor del tipo de objeto NotaDevolutiva
	 * @return devuelve el valor de NotaDevolutiva
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see NotaDevolutiva
	 */
	public synchronized NotaDevolutiva findNotaDevolutivaById(NotaDevolutiva acad_cad)
	    throws B2BException
	{
		DAOManager     ldm_manager;
		NotaDevolutiva lcr_datos;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		lcr_datos       = null;

		try
		{
			lcr_datos = DaoCreator.getNotaDevolutivaDAO(ldm_manager).findById(acad_cad);
		}

		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findNotaDevolutivaById", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lcr_datos;
	}

	/**
	 * Retorna el valor del objeto de Collection.
	 *
	 * @param acad_cad correspondiente al valor del tipo de objeto NotaDevolutiva
	 * @return devuelve el valor de Collection
	 * @throws B2BException Señala que se ha producido una excepción
	 *
	 */
	public synchronized Collection<NotaDevolutiva> findNotaDevolutivaByTurno(NotaDevolutiva acad_cad)
	    throws B2BException
	{
		DAOManager                 ldm_manager;
		Collection<NotaDevolutiva> lcr_datos;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		lcr_datos       = null;

		try
		{
			lcr_datos = DaoCreator.getNotaDevolutivaDAO(ldm_manager).findByTurno(acad_cad);
		}

		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findNotaDevolutivaByTurno", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lcr_datos;
	}

	/**
	 * Método para la transaccion con la base de datos para traer todos los
	 * registros existentes por ID en la tabla SDB_PGN_NUMERACION_OFICIOS_CIRCULO.
	 *
	 * @param anoc_param contenedor de la informacion con el identificador a consultar
	 * @return devuelve el valor de NumeracionOficiosCirculo
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public synchronized NumeracionOficiosCirculo findNumeracionOficiosCirculoById(NumeracionOficiosCirculo anoc_param)
	    throws B2BException
	{
		NumeracionOficiosCirculo lnoc_datos;

		lnoc_datos = null;

		if(anoc_param != null)
		{
			DAOManager ldm_manager;

			ldm_manager = DaoManagerFactory.getDAOManager();

			try
			{
				lnoc_datos = DaoCreator.getNumeracionOficiosCirculoDAO(ldm_manager).findById(anoc_param);
			}

			catch(B2BException lb2be_e)
			{
				ldm_manager.setRollbackOnly();

				clh_LOGGER.error("findNumeracionOficiosCirculoById", lb2be_e);

				throw lb2be_e;
			}
			finally
			{
				ldm_manager.close();
			}
		}

		return lnoc_datos;
	}

	/**
	 * Retorna el valor del objeto de OficinaOrigen.
	 *
	 * @param acad_cad correspondiente al valor del tipo de objeto OficinaOrigen
	 * @return devuelve el valor de OficinaOrigen
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see OficinaOrigen
	 */
	public synchronized OficinaOrigen findOficinaOrigenById(OficinaOrigen acad_cad)
	    throws B2BException
	{
		DAOManager    ldm_manager;
		OficinaOrigen lcr_datos;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		lcr_datos       = null;

		try
		{
			if(acad_cad != null)
				lcr_datos = buscarOficinaOrigenPorId(acad_cad.getIdOficinaOrigen(), ldm_manager);
		}

		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findOficinaOrigenById", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lcr_datos;
	}

	/**
	 * Método para consultar todos los Opcion correspondiente a el ID.
	 *
	 * @param as_idOpcion de as id opcion
	 * @return el valor de opcion
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public synchronized Opcion findOpcionById(String as_idOpcion)
	    throws B2BException
	{
		DAOManager ldm_manager;
		Opcion     lcc_datos;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		lcc_datos       = null;

		try
		{
			if(StringUtils.isValidString(as_idOpcion))
				lcc_datos = DaoCreator.getOpcionDAO(ldm_manager).findById(as_idOpcion);
		}

		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findOpcionById", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lcc_datos;
	}

	/**
	 * Método para consultar la OpcionEtapa correspondiente a el ID.
	 *
	 * @param as_idEtapa de as id etapa
	 * @param as_idOpcion de as id opcion
	 * @return el valor de opcion etapa
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public synchronized OpcionEtapa findOpcionEtapaById(long as_idEtapa, String as_idOpcion)
	    throws B2BException
	{
		DAOManager  ldm_manager;
		OpcionEtapa lo_datos;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		lo_datos        = null;

		try
		{
			if(StringUtils.isValidString(as_idOpcion) && (as_idEtapa > 0))
				lo_datos = DaoCreator.getOpcionEtapaDAO(ldm_manager).findOpcionEtapaById(as_idEtapa, as_idOpcion);
		}

		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findOpcionEtapaById", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lo_datos;
	}

	/**
	 * Método para la transaccion con la base de datos para traer todos los
	 * registros existentes por ID en la tabla SDB_PGN_ORIGEN.
	 *
	 * @param ao_o correspondiente al valor del tipo de objeto Origen
	 * @return devuelve el valor de Origen
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see Estados
	 */
	public synchronized Origen findOrigenById(Origen ao_o)
	    throws B2BException
	{
		DAOManager ldm_manager;
		Origen     lo_datos;

		ldm_manager     = DaoManagerFactory.getDAOManagerCYN();
		lo_datos        = null;

		try
		{
			lo_datos = DaoCreator.getOrigenDAO(ldm_manager).findById(ao_o);
		}

		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findOrigenById", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lo_datos;
	}

	/**
	 * Método de transacciones con la base de datos para encontrar todos los registros que coincidan con un ID_PAIS especifico
	 * de la tabla SDB_PGN_PAIS.
	 *
	 * @param ac_parametros objeto del cual se extrae el ID_PAIS para realizar la consulta en la base de datos
	 * @return devuelve el valor de Pais
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see Pais
	 */
	public synchronized Pais findPaisById(Pais ac_parametros)
	    throws B2BException
	{
		DAOManager ldm_manager;
		Pais       lc_datos;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		lc_datos        = null;

		try
		{
			lc_datos = DaoCreator.getPaisDAO(ldm_manager).findById(ac_parametros);
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findPaisById", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lc_datos;
	}

	/**
	 * Método de transacciones con la base de datos para encontrar todos los registros que coincidan con un ID_PARTE_INTERESADA especifico
	 * de la tabla SDB_COL_PARTE_INTERESADA.
	 *
	 * @param ac_parametros objeto del cual se extrae el ID_PARTE_INTERESADA para realizar la consulta en la base de datos
	 * @return devuelve el valor de ParteInteresada
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see ParteInteresada
	 */
	public synchronized ParteInteresada findParteInteresadaById(ParteInteresada ac_parametros)
	    throws B2BException
	{
		DAOManager      ldm_manager;
		ParteInteresada lc_datos;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		lc_datos        = null;

		try
		{
			lc_datos = DaoCreator.getParteInteresadaDAO(ldm_manager).findById(ac_parametros);
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findParteInteresadaById", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lc_datos;
	}

	/**
	 * Método para la transaccion con la base de datos para traer todos los
	 * registros existentes por ID en la tabla SDB_ACC_ENTIDAD_EXTERNA_PERSONA.
	 *
	 * @param aaee_entidadExterna correspondiente al valor del tipo de objeto AccEntidadExterna
	 * @return devuelve el valor de Collection de Persona
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public synchronized Collection<Persona> findPersonaByEntidad(AccEntidadExterna aaee_entidadExterna)
	    throws B2BException
	{
		DAOManager                  ldm_manager;
		Collection<Persona>         lcp_datos;
		PersonaDAO                  lpd_personaDAO;
		PersonaCorreoElectronicoDAO lpced_personaCorreoDAO;

		ldm_manager                = DaoManagerFactory.getDAOManager();
		lpd_personaDAO             = DaoCreator.getPersonaDAO(ldm_manager);
		lpced_personaCorreoDAO     = DaoCreator.getPersonaCorreoElectronicoDAO(ldm_manager);
		lcp_datos                  = new ArrayList<Persona>();

		try
		{
			if(aaee_entidadExterna != null)
			{
				String ls_idEntidadExterna;

				ls_idEntidadExterna = aaee_entidadExterna.getIdEntidadExterna();

				if(StringUtils.isValidString(ls_idEntidadExterna))
				{
					Collection<AccEntidadExternaPersona> lcaeep_entidadExternaPersona;

					lcaeep_entidadExternaPersona = DaoCreator.getAccEntidadExternaPersonaDAO(ldm_manager)
							                                     .findByIdEntidadExterna(ls_idEntidadExterna, true);

					if(CollectionUtils.isValidCollection(lcaeep_entidadExternaPersona))
					{
						for(AccEntidadExternaPersona laeep_temp : lcaeep_entidadExternaPersona)
						{
							if(laeep_temp != null)
							{
								String ls_idPersona;

								ls_idPersona = laeep_temp.getIdPersona();

								if(StringUtils.isValidString(ls_idPersona))
								{
									Persona lp_persona;

									lp_persona = lpd_personaDAO.findById(ls_idPersona);

									if(lp_persona != null)
									{
										String ls_representanteLegal;

										ls_representanteLegal = laeep_temp.getRepresentanteLegal();

										if(
										    StringUtils.isValidString(ls_representanteLegal)
											    && ls_representanteLegal.equalsIgnoreCase(EstadoCommon.S)
										)
											lp_persona.setRepresentanteLegal(EstadoCommon.S);
										else
											lp_persona.setRepresentanteLegal(EstadoCommon.N);

										String ls_correoElectronico;

										ls_correoElectronico = lp_persona.getCorreoElectronico();

										if(!StringUtils.isValidString(ls_correoElectronico))
										{
											PersonaCorreoElectronico lpce_personaCorreo;

											lpce_personaCorreo = lpced_personaCorreoDAO.findMaxByIdPersona(
												    ls_idPersona
												);

											if(lpce_personaCorreo != null)
											{
												String ls_correoPersona;

												ls_correoPersona = lpce_personaCorreo.getCorreoElectronico();

												if(StringUtils.isValidString(ls_correoPersona))
													lp_persona.setCorreoElectronico(ls_correoPersona);
											}
										}

										lcp_datos.add(lp_persona);
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

			clh_LOGGER.error("findPersonaByEntidad", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lcp_datos;
	}

	/**
	 * Método para la transaccion con la base de datos para traer todos los
	 * registros existentes por ID en la tabla SDB_ACC_PERSONA_CORREO_ELECTRONICO.
	 *
	 * @param as_idPersona correspondiente al valor del tipo de objeto String
	 * @return devuelve el valor de PersonaCorreoElectronico
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public synchronized PersonaCorreoElectronico findPersonaCorreoById(String as_idPersona)
	    throws B2BException
	{
		DAOManager               ldm_manager;
		PersonaCorreoElectronico lpce_datos;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		lpce_datos      = null;

		try
		{
			if(StringUtils.isValidString(as_idPersona))
				lpce_datos = DaoCreator.getPersonaCorreoElectronicoDAO(ldm_manager).findMaxByIdPersona(as_idPersona);
		}

		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findPersonaCorreoById", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lpce_datos;
	}

	/**
	 * Método para la transaccion con la base de datos para traer todos los
	 * registros existentes por ID en la tabla SDB_PGN_PLANTILLA.
	 *
	 * @param ap_p correspondiente al valor del tipo de objeto Plantilla
	 * @return devuelve el valor de Plantilla
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see Estados
	 */
	public synchronized Plantilla findPlantillaById(Plantilla ap_p)
	    throws B2BException
	{
		DAOManager ldm_manager;
		Plantilla  lp_datos;

		ldm_manager     = DaoManagerFactory.getDAOManagerCYN();
		lp_datos        = null;

		try
		{
			lp_datos = DaoCreator.getPlantillaDAO(ldm_manager).findById(ap_p);
		}

		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findPlantillaById", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lp_datos;
	}

	/**
	 * Retorna el valor del objeto de PlantillaComunicacion.
	 *
	 * @param apc_plantilla correspondiente al valor del tipo de objeto PlantillaComunicacion
	 * @return devuelve el valor de PlantillaComunicacion
	 * @throws B2BException  Objeto de tipo <code>B2BException</code>, se produce cuando se encuentra algun error controlado.
	 */
	public synchronized PlantillaComunicacion findPlantillaComunicacionById(PlantillaComunicacion apc_plantilla)
	    throws B2BException
	{
		DAOManager            ldm_manager;
		PlantillaComunicacion lpc_datos;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		lpc_datos       = null;

		try
		{
			lpc_datos = DaoCreator.getPlantillaComunicacionDAO(ldm_manager).findByIdPlantillaComunicacion(
				    apc_plantilla
				);
		}

		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findPlantillaComunicacionById", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lpc_datos;
	}

	/**
	 * Retorna el valor del objeto de PlantillaNotificacion.
	 *
	 * @param apn_plantilla correspondiente al valor del tipo de objeto PlantillaNotificacion
	 * @return devuelve el valor de PlantillaNotificacion
	 * @throws B2BException  Objeto de tipo <code>B2BException</code>, se produce cuando se encuentra algun error controlado.
	 */
	public synchronized PlantillaNotificacion findPlantillaNotificacionById(PlantillaNotificacion apn_plantilla)
	    throws B2BException
	{
		DAOManager            ldm_manager;
		PlantillaNotificacion lpn_datos;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		lpn_datos       = null;

		try
		{
			lpn_datos = DaoCreator.getPlantillaNotificacionDAO(ldm_manager)
					                  .findByIds(
					    apn_plantilla.getIdProceso(), apn_plantilla.getIdEtapaAnterior(), apn_plantilla.getIdMotivo(),
					    apn_plantilla.getIdPlantilla()
					);
		}

		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findPlantillaNotificacionById", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lpn_datos;
	}

	/**
	 * Método para realizar transacciones con la base de datos con el fin de encontrar todos los registros
	 * que coincidan con un id_proceso_automatico especifico.
	 *
	 * @param ac_parametros representa el proceso automatico de donde se extrae el id_proceso_automatico para su posterior consulta
	 * @return devuelve el valor de ProcesoAutomatico
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see ProcesoAutomatico
	 */
	public synchronized ProcesoAutomatico findProcesoAutomaticoById(ProcesoAutomatico ac_parametros)
	    throws B2BException
	{
		DAOManager        ldm_manager;
		ProcesoAutomatico lc_datos;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		lc_datos        = null;

		try
		{
			lc_datos = DaoCreator.getProcesoAutomaticoDAO(ldm_manager).findById(ac_parametros);
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findProcesoAutomaticoById", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lc_datos;
	}

	/**
	 * Método para realizar transacciones con la base de datos con el fin de encontrar todos los registros
	 * que coincidan con un id estado actividad específico.
	 *
	 * @param ac_parametros representa el proceso automatico de donde se extrae el id estado actividad para su posterior consulta
	 * @return devuelve el valor de EstadoActividad
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see EstadoActividad
	 */
	public synchronized EstadoActividad findProcesoAutomaticoById(EstadoActividad ac_parametros)
	    throws B2BException
	{
		DAOManager      ldm_manager;
		EstadoActividad lc_datos;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		lc_datos        = null;

		try
		{
			lc_datos = DaoCreator.getEstadoActividadDAO(ldm_manager).findById(ac_parametros);
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findProcesoAutomaticoById", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lc_datos;
	}

	/**
	 * Método para la transaccion con la base de datos para traer todos los
	 * registros existentes por ID en la tabla SDB_ACC_PROCESO.
	 *
	 * @param ap_p correspondiente al valor del tipo de objeto Proceso
	 * @return devuelve el valor de Proceso
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see Proceso
	 */
	public synchronized Proceso findProcesoById(Proceso ap_p)
	    throws B2BException
	{
		DAOManager ldm_manager;
		Proceso    lcr_datos;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		lcr_datos       = null;

		try
		{
			if(ap_p != null)
				lcr_datos = DaoCreator.getProcesoDAO(ldm_manager).findById(ap_p);
		}

		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findProcesoById", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lcr_datos;
	}

	/**
	 * Find proceso consulta by id.
	 *
	 * @param acpc_cpc de acpc cpc
	 * @return Objeto de clase ProcesoConsulta
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public synchronized ProcesoConsulta findProcesoConsultaById(ProcesoConsulta acpc_cpc)
	    throws B2BException
	{
		DAOManager      ldm_manager;
		ProcesoConsulta lcpc_datos;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		lcpc_datos      = null;

		try
		{
			lcpc_datos = DaoCreator.getProcesoConsultaDAO(ldm_manager).findById(acpc_cpc);
		}

		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findProcesoConsultaById", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lcpc_datos;
	}

	/**
	 * Método para la transaccion con la base de datos para traer todos los
	 * registros existentes por ID en la tabla SDB_PGN_PUNTO_RECAUDO.
	 *
	 * @param as_s correspondiente al valor del tipo de objeto String
	 * @return devuelve el valor de PuntoRecaudo
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see PuntoRecaudo
	 */
	public synchronized PuntoRecaudo findPuntoRecaudoById(String as_s)
	    throws B2BException
	{
		DAOManager   ldm_manager;
		PuntoRecaudo lpr_datos;

		ldm_manager     = DaoManagerFactory.getDAOManagerNPA();
		lpr_datos       = null;

		try
		{
			lpr_datos = DaoCreator.getPuntoRecaudoDAO(ldm_manager).findById(as_s);
		}

		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findPuntoRecaudoById", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lpr_datos;
	}

	/**
	 * Método para la transaccion con la base de datos para traer todos los
	 * registros existentes por ID en la tabla SDB_PGN_TIPO_RECAUDO.
	 *
	 * @param atr_tr correspondiente al valor del tipo de objeto PuntoRecaudoTipoRecaudo
	 * @return devuelve el valor de PuntoRecaudoTipoRecaudo
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see PuntoRecaudoTipoRecaudo
	 */
	public synchronized PuntoRecaudoTipoRecaudo findPuntoRecaudoTipoRecaudoById(PuntoRecaudoTipoRecaudo atr_tr)
	    throws B2BException
	{
		DAOManager              ldm_manager;
		PuntoRecaudoTipoRecaudo ltr_datos;

		ldm_manager     = DaoManagerFactory.getDAOManagerNPA();
		ltr_datos       = null;

		try
		{
			ltr_datos = DaoCreator.getPuntoRecaudoTipoRecaudoDAO(ldm_manager).findById(atr_tr);
		}

		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findPuntoRecaudoTipoRecaudoById", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return ltr_datos;
	}

	/**
	 * Método para la transaccion con la base de datos para traer todos los
	 * registros existentes por ID en la tabla SDB_PGN_RANGO_CUANTIA.
	 *
	 * @param acad_cad correspondiente al valor del tipo de objeto RangoCuantia
	 * @return devuelve el valor de RangoCuantia
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see RangoCuantia
	 */
	public synchronized RangoCuantia findRangoCuantiaById(RangoCuantia acad_cad)
	    throws B2BException
	{
		DAOManager   ldm_manager;
		RangoCuantia lcr_datos;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		lcr_datos       = null;

		try
		{
			lcr_datos = DaoCreator.getRangoCuantiaDAO(ldm_manager).findById(acad_cad);
		}

		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findRangoCuantiaById", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lcr_datos;
	}

	/**
	 * Método de transacciones con la base de datos para encontrar todos los registros que coincidan con un ID_REGIONAL especifico
	 * de la tabla SDB_PGN_REGIONAL.
	 *
	 * @param ac_parametros correspondiente al valor del tipo de objeto Regional
	 * @return devuelve el valor de Regional
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see Regional
	 */
	public synchronized Regional findRegionalById(Regional ac_parametros)
	    throws B2BException
	{
		DAOManager ldm_manager;
		Regional   lc_datos;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		lc_datos        = null;

		try
		{
			lc_datos = DaoCreator.getRegionalDAO(ldm_manager).findById(ac_parametros);
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findRegionalById", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lc_datos;
	}

	/**
	 * Retorna el valor del objeto de regla negocio.
	 *
	 * @param arn_regla correspondiente al valor del tipo de objeto regla negocio
	 * @return devuelve el valor de regla negocio
	 * @throws B2BException  Objeto de tipo <code>B2BException</code>, se produce cuando se encuentra algun error controlado.
	 */
	public synchronized ReglaNegocio findReglaNegocioById(ReglaNegocio arn_regla)
	    throws B2BException
	{
		DAOManager   ldm_manager;
		ReglaNegocio lrn_datos;

		ldm_manager     = DaoManagerFactory.getDAOManagerWorkflow();
		lrn_datos       = null;

		try
		{
			if(arn_regla != null)
			{
				String ls_id;

				ls_id         = arn_regla.getIdReglaNegocio();
				lrn_datos     = DaoCreator.getReglaNegocioDAO(ldm_manager).findById(ls_id);
			}
		}

		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findReglaNegocioById", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lrn_datos;
	}

	/**
	 * Método para la transaccion con la base de datos para traer todos los
	 * registros existentes por ID en la tabla SDB_PGN_REINTENTOS.
	 *
	 * @param ar_r correspondiente al valor del tipo de objeto Reintentos
	 * @return devuelve el valor de Reintentos
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see Estados
	 */
	public synchronized Reintentos findReintentosById(Reintentos ar_r)
	    throws B2BException
	{
		DAOManager ldm_manager;
		Reintentos lr_datos;

		ldm_manager     = DaoManagerFactory.getDAOManagerCYN();
		lr_datos        = null;

		try
		{
			lr_datos = DaoCreator.getReintentosDAO(ldm_manager).findById(ar_r);
		}

		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findReintentosById", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lr_datos;
	}

	/**
	 * Método para la transaccion con la base de datos para traer todos los
	 * registros existentes por ID en la tabla SDB_PGN_REPORTES.
	 *
	 * @param acad_cad correspondiente al valor del tipo de objeto Reportes
	 * @return devuelve el valor de Reportes
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see Reportes
	 */
	public synchronized Reportes findReportesById(Reportes acad_cad)
	    throws B2BException
	{
		DAOManager ldm_manager;
		Reportes   lcr_datos;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		lcr_datos       = null;

		try
		{
			lcr_datos = DaoCreator.getReportesDAO(ldm_manager).findById(acad_cad);
		}

		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findReportesById", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lcr_datos;
	}

	/**
	 * Método para la transaccion con la base de datos para traer todos los
	 * registros existentes por ID en la tabla SDB_PGN_RESULTADO_CONSULTA.
	 *
	 * @param arc_rc correspondiente al valor del tipo de objeto ResultadoConsulta
	 * @return devuelve el valor de ResultadoConsulta
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see Estados
	 */
	public synchronized ResultadoConsulta findResultadoConsultaById(ResultadoConsulta arc_rc)
	    throws B2BException
	{
		DAOManager        ldm_manager;
		ResultadoConsulta lrc_datos;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		lrc_datos       = null;

		try
		{
			lrc_datos = DaoCreator.getResultadoConsultaDAO(ldm_manager).findById(arc_rc);
		}

		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findResultadoConsultaById", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lrc_datos;
	}

	/**
	 * Método de transacciones con la base de datos para encontrar todos los registros que coincidan con un ID_ROL especifico
	 * de la tabla SDB_AUT_ROL.
	 *
	 * @param ar_parametros correspondiente al valor del tipo de objeto Rol
	 * @return devuelve el valor de Rol
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see Rol
	 */
	public synchronized Rol findRolById(Rol ar_parametros)
	    throws B2BException
	{
		DAOManager ldm_manager;
		Rol        lr_datos;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		lr_datos        = null;

		try
		{
			lr_datos = DaoCreator.getRolDAO(ldm_manager).findById(ar_parametros);
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findRolsById", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lr_datos;
	}

	/**
	 * Find rol opcion by id.
	 *
	 * @param as_idRol de as id rol
	 * @param as_idOpcion de as id opcion
	 * @return el valor de rol opcion
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public synchronized RolOpcion findRolOpcionById(String as_idRol, String as_idOpcion)
	    throws B2BException
	{
		DAOManager ldm_manager;
		RolOpcion  lcc_datos;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		lcc_datos       = null;

		try
		{
			if(StringUtils.isValidString(as_idRol) && StringUtils.isValidString(as_idOpcion))
				lcc_datos = DaoCreator.getRolOpcionDAO(ldm_manager).findRolOpcionById(as_idRol, as_idOpcion);
		}

		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findRolOpcionById", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lcc_datos;
	}

	/**
	 * Retorna el valor del objeto de SalarioMinimo.
	 *
	 * @param aottf_data correspondiente al valor del tipo de objeto SalarioMinimo
	 * @param ab_b correspondiente al valor del tipo de objeto boolean
	 * @param as_idUsuario correspondiente al valor del tipo de objeto String
	 * @param as_localIp correspondiente al valor del tipo de objeto String
	 * @param as_remoteIp correspondiente al valor del tipo de objeto String
	 * @return devuelve el valor de SalarioMinimo
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see SalarioMinimo
	 */
	public synchronized SalarioMinimo findSalarioMinimo(
	    SalarioMinimo aottf_data, boolean ab_b, String as_idUsuario, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		DAOManager    ldm_manager;
		SalarioMinimo lcc_datos;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		lcc_datos       = null;

		try
		{
			if(aottf_data != null)
				lcc_datos = DaoCreator.getSalarioMinimoDAO(ldm_manager).findById(aottf_data, ab_b);
			else
				lcc_datos = DaoCreator.getSalarioMinimoDAO(ldm_manager).findById(null, ab_b);
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findSalarioMinimo", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lcc_datos;
	}

	/**
	 * Find sub proceso by id.
	 *
	 * @param acsp_csp Objeto de clase Subproceso con el cual se realiza la busqueda en la tabla SDB_ACC_SUBPROCESO
	 * @return Objeto de clase Subproceso
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public synchronized Subproceso findSubProcesoById(Subproceso acsp_csp)
	    throws B2BException
	{
		DAOManager ldm_manager;
		Subproceso lcsp_datos;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		lcsp_datos      = null;

		try
		{
			lcsp_datos = DaoCreator.getSubprocesoDAO(ldm_manager).findById(acsp_csp);
		}

		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findSubProcesoById", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lcsp_datos;
	}

	/**
	 * Find sub proceso version by id.
	 *
	 * @param acspv_cspv de acspv cspv
	 * @return Objeto de clase SubprocesoVersion
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public synchronized SubprocesoVersion findSubProcesoVersionById(SubprocesoVersion acspv_cspv)
	    throws B2BException
	{
		DAOManager        ldm_manager;
		SubprocesoVersion lcspv_datos;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		lcspv_datos     = null;

		try
		{
			if(acspv_cspv != null)
			{
				String ls_idProceso;
				String ls_idSubProceso;
				Long   ll_versionSubProceso;

				ls_idProceso             = acspv_cspv.getIdProceso();
				ls_idSubProceso          = acspv_cspv.getIdSubproceso();
				ll_versionSubProceso     = acspv_cspv.getVersionSubproceso();

				if(
				    StringUtils.isValidString(ls_idSubProceso) && StringUtils.isValidString(ls_idProceso)
					    && NumericUtils.isValidLong(ll_versionSubProceso)
				)
					lcspv_datos = DaoCreator.getSubprocesoVersionDAO(ldm_manager)
							                    .findById(ls_idProceso, ls_idSubProceso, ll_versionSubProceso);
			}
		}

		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findSubProcesoVersionById", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lcspv_datos;
	}

	/**
	 * Método de transaciones con la base de datos con el fin de encontar todos los registros
	 * SDB_ACC_SUBPROCESO.
	 *
	 * @param ab_b indica si se desea traer los registros de roles que se encuentren activos(true ,false )
	 * @return el valor de collection
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public synchronized Collection<Subproceso> findSubprocesos(boolean ab_b)
	    throws B2BException
	{
		DAOManager             ldm_manager;
		Collection<Subproceso> lcsp_datos;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		lcsp_datos      = null;

		try
		{
			lcsp_datos = DaoCreator.getSubprocesoDAO(ldm_manager).findAll(ab_b);
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findAllSubprocesos", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lcsp_datos;
	}

	/**
	 * Método para la transaccion con la base de datos para traer todos los
	 * registros existentes por ID en la tabla SDB_PGN_SUCURSAL_CANAL_ORIGEN_SERVICIO.
	 *
	 * @param atr_tr correspondiente al valor del tipo de objeto SucursalCanalOrigenServicio
	 * @return devuelve el valor de SucursalCanalOrigenServicio
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see SucursalCanalOrigenServicio
	 */
	public synchronized SucursalCanalOrigenServicio findSucursalCanalOrigenServicioById(
	    SucursalCanalOrigenServicio atr_tr
	)
	    throws B2BException
	{
		DAOManager                  ldm_manager;
		SucursalCanalOrigenServicio ltr_datos;

		ldm_manager     = DaoManagerFactory.getDAOManagerNPA();
		ltr_datos       = null;

		try
		{
			ltr_datos = DaoCreator.getSucursalCanalOrigenServicioDAO(ldm_manager).findById(atr_tr);
		}

		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findSucursalCanalOrigenServicioById", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return ltr_datos;
	}

	/**
	 * Método para la transaccion con la base de datos para traer todos los
	 * registros existentes por ID en la tabla SDB_PGN_TARIFA_FIJA.
	 *
	 * @param acad_cad correspondiente al valor del tipo de objeto TarifaFija
	 * @return devuelve el valor de TarifaFija
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see TarifaFija
	 */
	public synchronized TarifaFija findTarifaFijaById(TarifaFija acad_cad)
	    throws B2BException
	{
		DAOManager ldm_manager;
		TarifaFija lcr_datos;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		lcr_datos       = null;

		try
		{
			lcr_datos = DaoCreator.getTarifaFijaDAO(ldm_manager).findById(acad_cad);
		}

		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findTarifaFijaById", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lcr_datos;
	}

	/**
	 * Método para buscar todos los tipos actos basaddos en su ID linea de produccion.
	 *
	 * @param as_arg de as arg
	 * @return una coleccion de tipo acto con la consulta.
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public synchronized Collection<TipoActo> findTipoActoByLineaProduccion(String as_arg)
	    throws B2BException
	{
		Collection<TipoActo> lcta_return;

		lcta_return = null;

		if(StringUtils.isValidString(as_arg))
		{
			DAOManager ldm_manager;

			ldm_manager = DaoManagerFactory.getDAOManager();

			try
			{
				lcta_return = DaoCreator.getTipoActoDAO(ldm_manager).findTipoActoByLineaProduccion(as_arg);
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
		}

		return lcta_return;
	}

	/**
	 * Método para la transaccion con la base de datos para traer todos los
	 * registros existentes por ID en la tabla SDB_PGN_TIPO_ACTO_EJECUTORIA.
	 *
	 * @param acad_cad correspondiente al valor del tipo de objeto TipoActoCondicion
	 * @return devuelve el valor de TipoActoCondicion
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see TipoActoCondicion
	 */
	public synchronized TipoActoCondicion findTipoActoCondicionById(TipoActoCondicion acad_cad)
	    throws B2BException
	{
		DAOManager        ldm_manager;
		TipoActoCondicion lcr_datos;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		lcr_datos       = null;

		try
		{
			lcr_datos = DaoCreator.getTipoActoCondicionDAO(ldm_manager).findById(acad_cad);
		}

		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findTipoActoCondicionById", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lcr_datos;
	}

	/**
	 * Método para la transaccion con la base de datos para traer todos los
	 * registros existentes por ID en la tabla SDB_PGN_TIPO_ACTO_EJECUTORIA.
	 *
	 * @param acad_cad correspondiente al valor del tipo de objeto TipoActoEjecutoria
	 * @return devuelve el valor de TipoActoEjecutoria
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see TipoActoEjecutoria
	 */
	public synchronized TipoActoEjecutoria findTipoActoEjecutoriaById(TipoActoEjecutoria acad_cad)
	    throws B2BException
	{
		DAOManager         ldm_manager;
		TipoActoEjecutoria lcr_datos;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		lcr_datos       = null;

		try
		{
			lcr_datos = DaoCreator.getTipoActoEjecutoriaDAO(ldm_manager).findById(acad_cad);
		}

		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findTipoActoEjecutoriaById", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lcr_datos;
	}

	/**
	 * Método para la transaccion con la base de datos para traer todos los
	 * registros existentes por ID en la tabla SDB_PGN_TIPO_AREA.
	 *
	 * @param ata_ta correspondiente al valor del tipo de objeto TipoArea
	 * @return devuelve el valor de TipoArea
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see TipoArea
	 */
	public synchronized TipoArea findTipoAreaById(TipoArea ata_ta)
	    throws B2BException
	{
		DAOManager ldm_manager;
		TipoArea   lta_datos;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		lta_datos       = null;

		try
		{
			lta_datos = DaoCreator.getTipoAreaDAO(ldm_manager).findById(ata_ta);
		}

		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findTipoAreaById", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lta_datos;
	}

	/**
	 * Método para la transaccion con la base de datos para traer todos los
	 * registros existentes por ID en la tabla SDB_PGN_TIPO_CANAL_ORIGEN.
	 *
	 * @param atco_tco correspondiente al valor del tipo de objeto TipoCanalOrigen
	 * @return devuelve el valor de TipoCanalOrigen
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see TipoCanalOrigen
	 */
	public synchronized TipoCanalOrigen findTipoCanalOrigenById(TipoCanalOrigen atco_tco)
	    throws B2BException
	{
		DAOManager      ldm_manager;
		TipoCanalOrigen lta_datos;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		lta_datos       = null;

		try
		{
			lta_datos = DaoCreator.getTipoCanalOrigenDAO(ldm_manager).findById(atco_tco);
		}

		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findTipoCanalOrigenById", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lta_datos;
	}

	/**
	 * Método para la transaccion con la base de datos para traer todos los
	 * registros existentes por ID en la tabla SDB_PGN_TIPO_CRITERIO_BUSQUEDA.
	 *
	 * @param atcb_param contenedor de la informacion con el identificador a consultar
	 * @return devuelve el valor de TipoCriterioBusquedaPGN
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see TipoCriterioBusquedaPGN
	 */
	public synchronized TipoCriterioBusquedaPGN findTipoCriterioBusquedaPGNById(TipoCriterioBusquedaPGN atcb_param)
	    throws B2BException
	{
		TipoCriterioBusquedaPGN ltcb_datos;

		ltcb_datos = null;

		if(atcb_param != null)
		{
			DAOManager ldm_manager;

			ldm_manager = DaoManagerFactory.getDAOManager();

			try
			{
				ltcb_datos = DaoCreator.getTipoCriterioBusquedaPGNDAO(ldm_manager).findById(atcb_param);
			}

			catch(B2BException lb2be_e)
			{
				ldm_manager.setRollbackOnly();

				clh_LOGGER.error("findTipoCriterioBusquedaPGNById", lb2be_e);

				throw lb2be_e;
			}
			finally
			{
				ldm_manager.close();
			}
		}

		return ltcb_datos;
	}

	/**
	 * Método para la transaccion con la base de datos para traer todos los
	 * registros existentes por ID en la tabla SDB_PGN_TIPO_DECISION_RECURSO.
	 *
	 * @param acad_cad correspondiente al valor del tipo de objeto TipoDecisionRecurso
	 * @return devuelve el valor de TipoDecisionRecurso
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see TipoDecisionRecurso
	 */
	public synchronized TipoDecisionRecurso findTipoDecisionRecursoById(TipoDecisionRecurso acad_cad)
	    throws B2BException
	{
		DAOManager          ldm_manager;
		TipoDecisionRecurso lcr_datos;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		lcr_datos       = null;

		try
		{
			lcr_datos = DaoCreator.getTipoDecisionRecursoDAO(ldm_manager).findById(acad_cad);
		}

		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findTipoDecisionRecursoById", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lcr_datos;
	}

	/**
	 * Método para realizar transacciones con la base de datos con el fin de encontrar todos los registros
	 * que coincidan con un id_tipo_eje especifico.
	 *
	 * @param as_s representa la coordenada de donde se extrae el id_tipo_eje para su posterior consulta
	 * @return lcr_datos Collection de Tipo Eje
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public synchronized TipoEje findTipoEjeById(String as_s)
	    throws B2BException
	{
		DAOManager ldm_manager;
		TipoEje    lcr_datos;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		lcr_datos       = null;

		try
		{
			lcr_datos = DaoCreator.getTipoEjeDAO(ldm_manager).findById(as_s);
		}

		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findTipoEjeById", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lcr_datos;
	}

	/**
	 * Retorna el valor del objeto de TipoEntidad.
	 *
	 * @param aottf_data correspondiente al valor del tipo de objeto TipoEntidad
	 * @param ab_b correspondiente al valor del tipo de objeto boolean
	 * @param as_idUsuario correspondiente al valor del tipo de objeto String
	 * @param as_localIp correspondiente al valor del tipo de objeto String
	 * @param as_remoteIp correspondiente al valor del tipo de objeto String
	 * @return devuelve el valor de TipoEntidad
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see TipoEntidad
	 */
	public synchronized TipoEntidad findTipoEntidad(
	    TipoEntidad aottf_data, boolean ab_b, String as_idUsuario, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		DAOManager              ldm_manager;
		TipoEntidad             lcc_datos;
		Collection<TipoEntidad> lccte_datos;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		lcc_datos       = new TipoEntidad();

		try
		{
			if(aottf_data == null)
			{
				lccte_datos = DaoCreator.getTipoEntidadDAO(ldm_manager).findAll();

				if(CollectionUtils.isValidCollection(lccte_datos))
					lcc_datos.setInfoAll(lccte_datos);
			}
			else
				lcc_datos = DaoCreator.getTipoEntidadDAO(ldm_manager).findById(aottf_data);
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findTipoEntidad", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lcc_datos;
	}

	/**
	 * Método para la transaccion con la base de datos para traer todos los
	 * registros existentes por ID en la tabla SDB_PGN_TIPO_AREA.
	 *
	 * @param atel_tel correspondiente al valor del tipo de objeto TipoEstadoLiquidacion
	 * @return devuelve el valor de TipoEstadoLiquidacion
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see TipoEstadoLiquidacion
	 */
	public synchronized TipoEstadoLiquidacion findTipoEstadoLiquidacionById(TipoEstadoLiquidacion atel_tel)
	    throws B2BException
	{
		DAOManager            ldm_manager;
		TipoEstadoLiquidacion ltel_datos;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		ltel_datos      = null;

		try
		{
			ltel_datos = DaoCreator.getTipoEstadoLiquidacionDAO(ldm_manager).findById(atel_tel);
		}

		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findTipoEstadoLiquidacionById", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return ltel_datos;
	}

	/**
	 * Método para la transaccion con la base de datos para traer todos los
	 * registros existentes por ID en la tabla SDB_ACC_TIPO_ESTADO_SOLICITUD.
	 *
	 * @param ates_param de ates param
	 * @return devuelve el valor de TipoEstadoSolicitud
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see Canal
	 */
	public synchronized TipoEstadoSolicitud findTipoEstadoSolicitudById(TipoEstadoSolicitud ates_param)
	    throws B2BException
	{
		TipoEstadoSolicitud ltes_datos;

		ltes_datos = null;

		if(ates_param != null)
		{
			DAOManager ldm_manager;

			ldm_manager = DaoManagerFactory.getDAOManager();

			try
			{
				ltes_datos = DaoCreator.getTipoEstadoSolicitudDAO(ldm_manager).findById(ates_param);
			}

			catch(B2BException lb2be_e)
			{
				ldm_manager.setRollbackOnly();

				clh_LOGGER.error("findTipoEstadoSolicitudById", lb2be_e);

				throw lb2be_e;
			}
			finally
			{
				ldm_manager.close();
			}
		}

		return ltes_datos;
	}

	/**
	 * Método para realizar transacciones con la base de datos con el fin de encontrar todos los registros
	 * que coincidan con un id_proceso_automatico especifico.
	 *
	 * @param ac_parametros representa el proceso automatico de donde se extrae el id_proceso_automatico para su posterior consulta
	 * @return devuelve el valor de TipoIntegracionGobernacion
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see TipoIntegracionGobernacion
	 */
	public synchronized TipoIntegracionGobernacion findTipoIntegracionGobernacionById(
	    TipoIntegracionGobernacion ac_parametros
	)
	    throws B2BException
	{
		DAOManager                 ldm_manager;
		TipoIntegracionGobernacion lc_datos;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		lc_datos        = null;

		try
		{
			lc_datos = DaoCreator.getTipoIntegracionGobernacionDAO(ldm_manager).findById(ac_parametros);
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findTipoIntegracionGobernacionById", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lc_datos;
	}

	/**
	 * Método para encontrar todos los registros de la base de datos para la tabla
	 * SDB_PNG_TIPO_NOTIFICACION.
	 *
	 * @return el valor de collection
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public synchronized Collection<TipoNotificacion> findTipoNotificacion()
	    throws B2BException
	{
		DAOManager                   ldm_manager;
		Collection<TipoNotificacion> lctn_datos;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		lctn_datos      = null;

		try
		{
			lctn_datos = DaoCreator.getTipoNotificacionDAO(ldm_manager).findAll();
		}

		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findAllTipoNotificacion", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lctn_datos;
	}

	/**
	 * Find tipo notificacion by id.
	 *
	 * @param actn_tn de actn tn
	 * @return Objeto de clase TipoNotificacion
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public synchronized TipoNotificacion findTipoNotificacionById(TipoNotificacion actn_tn)
	    throws B2BException
	{
		DAOManager       ldm_manager;
		TipoNotificacion lctn_datos;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		lctn_datos      = null;

		try
		{
			lctn_datos = DaoCreator.getTipoNotificacionDAO(ldm_manager).findById(actn_tn);
		}

		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findTipoNotificacionById", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lctn_datos;
	}

	/**
	 * Retorna el valor del objeto de TipoOficina.
	 *
	 * @param aottf_data correspondiente al valor del tipo de objeto TipoOficina
	 * @param ab_b correspondiente al valor del tipo de objeto boolean
	 * @param as_idUsuario correspondiente al valor del tipo de objeto String
	 * @param as_localIp correspondiente al valor del tipo de objeto String
	 * @param as_remoteIp correspondiente al valor del tipo de objeto String
	 * @return devuelve el valor de TipoOficina
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see TipoOficina
	 */
	public synchronized TipoOficina findTipoOficina(
	    TipoOficina aottf_data, boolean ab_b, String as_idUsuario, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		DAOManager              ldm_manager;
		TipoOficina             lcc_datos;
		Collection<TipoOficina> lcctp_datos;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		lcc_datos       = new TipoOficina();

		try
		{
			if(aottf_data != null)
				lcc_datos = DaoCreator.getTipoOficinaDAO(ldm_manager).findById(aottf_data);
			else
			{
				lcctp_datos = DaoCreator.getTipoOficinaDAO(ldm_manager).findAll(false);

				if(CollectionUtils.isValidCollection(lcctp_datos))
					lcc_datos.setInfoAll(lcctp_datos);
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findTipoOficina", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lcc_datos;
	}

	/**
	 * Método para la transaccion con la base de datos para traer todos los
	 * registros existentes por ID en la tabla SDB_PGN_TIPO_OPERACION.
	 *
	 * @param ato_param contenedor de la informacion con el identificador a consultar
	 * @return devuelve el valor de TipoOperacion
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see TipoOperacion
	 */
	public synchronized TipoOperacion findTipoOperacionById(TipoOperacion ato_param)
	    throws B2BException
	{
		TipoOperacion lto_datos;

		lto_datos = null;

		if(ato_param != null)
		{
			DAOManager ldm_manager;

			ldm_manager = DaoManagerFactory.getDAOManager();

			try
			{
				String ls_idTipoOperacion;

				ls_idTipoOperacion = ato_param.getIdTipoOperacion();

				if(StringUtils.isValidString(ls_idTipoOperacion))
					lto_datos = DaoCreator.getTipoOperacionDAO(ldm_manager).findById(ls_idTipoOperacion);
			}

			catch(B2BException lb2be_e)
			{
				ldm_manager.setRollbackOnly();

				clh_LOGGER.error("findTipoOperacionById", lb2be_e);

				throw lb2be_e;
			}
			finally
			{
				ldm_manager.close();
			}
		}

		return lto_datos;
	}

	/**
	 * Retorna el valor del objeto de TipoPersona.
	 *
	 * @param atp_parametros correspondiente al valor del tipo de objeto TipoPersona
	 * @return devuelve el valor de TipoPersona
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see TipoPersona
	 */
	public synchronized TipoPersona findTipoPersonaById(TipoPersona atp_parametros)
	    throws B2BException
	{
		DAOManager  ldm_manager;
		TipoPersona ltp_datos;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		ltp_datos       = null;

		try
		{
			ltp_datos = DaoCreator.getTipoPersonaDAO(ldm_manager).findById(atp_parametros);
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findTipoPersonaById", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return ltp_datos;
	}

	/**
	 * Retorna el valor del objeto de PredioTipo.
	 *
	 * @param apt_parametros correspondiente al valor del tipo de objeto PredioTipo
	 * @return devuelve el valor de PredioTipo
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see PredioTipo
	 */
	public synchronized PredioTipo findTipoPredioById(PredioTipo apt_parametros)
	    throws B2BException
	{
		DAOManager ldm_manager;
		PredioTipo lpt_datos;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		lpt_datos       = null;

		try
		{
			lpt_datos = DaoCreator.getPredioTipoDao(ldm_manager).findById(apt_parametros);
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findTipoPredioById", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lpt_datos;
	}

	/**
	 * Método de transacciones con la base de datos para encontrar todos los registros que coincidan con un ID_DOMINIO_RRR específico.
	 *
	 * @param atr_parametros objeto del cual se extrae el ID_DOMINIO_RRR para obtener la consulta
	 * @return devuelve el valor de TipoRrr
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see TipoRrr
	 */
	public synchronized TipoRrr findTipoRRRById(TipoRrr atr_parametros)
	    throws B2BException
	{
		DAOManager ldm_manager;
		TipoRrr    ltr_datos;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		ltr_datos       = null;

		try
		{
			ltr_datos = DaoCreator.getTipoRrrDAO(ldm_manager).findById(atr_parametros);
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findTipoRRRById", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return ltr_datos;
	}

	/**
	 * Método para la transaccion con la base de datos para traer todos los
	 * registros existentes por ID en la tabla SDB_PGN_TIPO_RECAUDO.
	 *
	 * @param atr_tr correspondiente al valor del tipo de objeto TipoRecaudo
	 * @return devuelve el valor de TipoRecaudo
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see TipoRecaudo
	 */
	public synchronized TipoRecaudo findTipoRecaudoById(TipoRecaudo atr_tr)
	    throws B2BException
	{
		DAOManager  ldm_manager;
		TipoRecaudo ltr_datos;

		ldm_manager     = DaoManagerFactory.getDAOManagerNPA();
		ltr_datos       = null;

		try
		{
			ltr_datos = DaoCreator.getTipoRecaudoDAO(ldm_manager).findById(atr_tr);
		}

		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findTipoRecaudoById", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return ltr_datos;
	}

	/**
	 * Método de transacciones con la base de datos para encontrar todos los registros de la tabla SDB_ACC_TIPO_RECEPCION
	 * que coincidan con un ID_TIPO_RECEPCION especifico.
	 *
	 * @param ar_parametros Objeto del cual se extrae el ID_TIPO_RECEPCION para realizar la consulta
	 * @return devuelve el valor de TipoRecepcion
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see TipoRecepcion
	 */
	public synchronized TipoRecepcion findTipoRecepcionById(TipoRecepcion ar_parametros)
	    throws B2BException
	{
		DAOManager    ldm_manager;
		TipoRecepcion lr_datos;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		lr_datos        = null;

		try
		{
			lr_datos = DaoCreator.getTipoRecepcionDAO(ldm_manager).findById(ar_parametros);
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findTipoRecepcionById", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lr_datos;
	}

	/**
	 * Método para la transaccion con la base de datos para traer todos los
	 * registros existentes por ID en la tabla SDB_PGN_TIPO_RECURSO.
	 *
	 * @param acad_cad correspondiente al valor del tipo de objeto TipoRecurso
	 * @return devuelve el valor de TipoRecurso
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see TipoRecurso
	 */
	public synchronized TipoRecurso findTipoRecursoById(TipoRecurso acad_cad)
	    throws B2BException
	{
		DAOManager  ldm_manager;
		TipoRecurso lcr_datos;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		lcr_datos       = null;

		try
		{
			lcr_datos = DaoCreator.getTipoRecursoDAO(ldm_manager).findById(acad_cad);
		}

		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findTipoRecursoById", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lcr_datos;
	}

	/**
	 * Método para la transaccion con la base de datos para traer todos los
	 * registros existentes por ID en la tabla SDB_PGN_TIPO_REQUIERE_MATRICULA.
	 *
	 * @param atrm_trm correspondiente al valor del tipo de objeto TipoRequiereMatricula
	 * @return devuelve el valor de TipoRequiereMatricula
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see TipoRequiereMatricula
	 */
	public synchronized TipoRequiereMatricula findTipoRequiereMatriculaById(TipoRequiereMatricula atrm_trm)
	    throws B2BException
	{
		DAOManager            ldm_manager;
		TipoRequiereMatricula ltrm_datos;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		ltrm_datos      = null;

		try
		{
			if(atrm_trm != null)
				ltrm_datos = DaoCreator.getTipoRequiereMatriculaDAO(ldm_manager).findById(atrm_trm);
		}

		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findTipoRequiereMatriculaById", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return ltrm_datos;
	}

	/**
	 * Método de transacciones con la base de datos para encontrar todos los registros de la tabla SDB_ACC_TIPO_TARJETA_APODERADO
	 * que coincidan con un ID_TIPO_TARJETA_APODERADO especifico.
	 *
	 * @param ar_parametros Objeto del cual se extrae el ID_TIPO_TARJETA_APODERADO para realizar la consulta
	 * @return devuelve el valor de TipoTarjetaApoderado
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see TipoTarjetaApoderado
	 */
	public synchronized TipoTarjetaApoderado findTipoTarjetaApoderadoById(TipoTarjetaApoderado ar_parametros)
	    throws B2BException
	{
		DAOManager           ldm_manager;
		TipoTarjetaApoderado lr_datos;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		lr_datos        = null;

		try
		{
			lr_datos = DaoCreator.getTipoTarjetaApoderadoDAO(ldm_manager).findById(ar_parametros);
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findTipoTarjetaApoderadoById", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lr_datos;
	}

	/**
	 * Método de transacciones con la base de datos para encontrar todos los registros de la tabla SDB_COL_TIPO_USO_SUELO
	 * que coincidan con un ID_TIPO_USO_SUELO especifico.
	 *
	 * @param atus_parametros Objeto del cual se extrae el ID_TIPO_USO_SUELO para realizar la consulta
	 * @return devuelve el valor de TipoUsoSuelo
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see TipoUsoSuelo
	 */
	public synchronized TipoUsoSuelo findTipoUsoPredioById(TipoUsoSuelo atus_parametros)
	    throws B2BException
	{
		DAOManager   ldm_manager;
		TipoUsoSuelo ltus_datos;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		ltus_datos      = null;

		try
		{
			ltus_datos = DaoCreator.getTipoUsoSueloDAO(ldm_manager).findById(atus_parametros);
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findTipoUsoPredioById", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return ltus_datos;
	}

	/**
	 * Método de transacciones con la base de datos para encontrar todos los registros de la tabla SDB_COL_TIPO_USO_SUELO.
	 *
	 * @param aottf_data objeto que se estrae el id para realizar la consualta en al base de datos
	 * @param ab_b indica si se debe traer los registros con un ID_TIPO_ADQUISICION especifico
	 * @param as_idUsuario usuario que realiza la consulta
	 * @param as_localIp ip del servidor de aplicaciones
	 * @param as_remoteIp ip de donde se invoca el metodo
	 * @return devuelve el valor de TipoAdquisicion
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see TipoAdquisicion
	 */
	public synchronized TipoAdquisicion findTiposAdquisicion(
	    TipoAdquisicion aottf_data, boolean ab_b, String as_idUsuario, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		DAOManager      ldm_manager;
		TipoAdquisicion lcc_datos;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		lcc_datos       = null;

		try
		{
			if(aottf_data != null)
				lcc_datos = DaoCreator.getTipoAdquisicionDAO(ldm_manager).findById(aottf_data, ab_b);
			else
				lcc_datos = DaoCreator.getTipoAdquisicionDAO(ldm_manager).findById(null, ab_b);
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findTiposAdquisicion", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lcc_datos;
	}

	/**
	 * Método de transacciones con la base de datos para encontrar todos los registros de la tabla SDB_ACC_TIPO_PAGO.
	 *
	 * @param aottf_data objeto del cual se extrae el id para realizar la consulta en la base de datos
	 * @param ab_b indica si se aplica el filtro de ID_TIPO_PAGO
	 * @param as_idUsuario usuario que realiza la consulta
	 * @param as_localIp ip del servidor
	 * @param as_remoteIp ip del usuario que realiza la consulta
	 * @return devuelve el valor de TipoPago
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see TipoPago
	 */
	public synchronized TipoPago findTiposPagos(
	    TipoPago aottf_data, boolean ab_b, String as_idUsuario, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		DAOManager ldm_manager;
		TipoPago   lcc_datos;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		lcc_datos       = null;

		try
		{
			if(aottf_data != null)
				lcc_datos = DaoCreator.getTipoPagoDao(ldm_manager).findById(aottf_data, ab_b);
			else
				lcc_datos = DaoCreator.getTipoPagoDao(ldm_manager).findById(null, ab_b);
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findTiposPagos", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lcc_datos;
	}

	/**
	 * Método de transacciones con la base de datos para encontrar todos los registros de la tabla SDB_ACC_TIPO_TARIFA_REGISTRAL.
	 *
	 * @param aottf_data objeto del cual se le extrae el id para realizar la consulta
	 * @param ab_b indica si se filtra la  consutla con SDB_ACC_TIPO_TARIFA_REGISTRAL
	 * @param as_idUsuario usuario que ejecuta la acción de buscar en la base de datos
	 * @param as_localIp ip del servidor
	 * @param as_remoteIp ip del usuario que ejecuta la acción
	 * @return devuelve el valor de TipoTarifaRegistral
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see TipoTarifaRegistral
	 */
	public synchronized TipoTarifaRegistral findTiposTarifas(
	    TipoTarifaRegistral aottf_data, boolean ab_b, String as_idUsuario, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		DAOManager          ldm_manager;
		TipoTarifaRegistral lcc_datos;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		lcc_datos       = null;

		try
		{
			if(aottf_data != null)
				lcc_datos = DaoCreator.getTipoTarifaRegistralDao(ldm_manager).findById(aottf_data, ab_b);
			else
				lcc_datos = DaoCreator.getTipoTarifaRegistralDao(ldm_manager).findById(null, ab_b);
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findTiposTarifas", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lcc_datos;
	}

	/**
	 * Find unidad tiempo vencimiento by id.
	 *
	 * @param acutv_utv Objeto de clase UnidadTiempoVencimiento con el cual se realiza la busqueda en la tabla SDB_PGN_UNIDAD_TIEMPO_VENCIMIENTO
	 * @return Objeto de clase UnidadTiempoVencimiento
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public synchronized UnidadTiempoVencimiento findUnidadTiempoVencimientoById(UnidadTiempoVencimiento acutv_utv)
	    throws B2BException
	{
		DAOManager              ldm_manager;
		UnidadTiempoVencimiento lcutv_datos;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		lcutv_datos     = null;

		try
		{
			lcutv_datos = DaoCreator.getUnidadTiempoVencimientoDAO(ldm_manager).findById(acutv_utv);
		}

		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findUnidadTiempoVencimientoById", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lcutv_datos;
	}

	/**
	 * Método para realizar transacciones con la capa de datos para encontrar usuarios que
	 * coincidan con un tipo de documento especifico.
	 *
	 * @param au_parametros correspondiente al valor del tipo de objeto Usuario
	 * @return devuelve el valor de Usuario
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see Usuario
	 */
	public synchronized Usuario findUsuarioByTipoDoc(Usuario au_parametros)
	    throws B2BException
	{
		DAOManager ldm_manager;
		Usuario    lu_datos;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		lu_datos        = null;

		try
		{
			lu_datos = DaoCreator.getUsuarioDAO(ldm_manager).findByTipoDoc(au_parametros);
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findUsuarioByTipoDoc", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lu_datos;
	}

	/**
	 * Método para la construccion del usuario para una persona de la tabla SDB_ACC_ENTIDAD_EXTERNA_PERSONA.
	 *
	 * @param ap_persona correspondiente al valor del tipo de objeto Persona
	 * @param acp_cp correspondiente al valor del tipo de objeto  Collection de Persona
	 * @param as_idEntidad correspondiente al valor del tipo de objeto String
	 * @return devuelve el valor de String
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public synchronized String findUsuarioPersona(Persona ap_persona, Collection<Persona> acp_cp, String as_idEntidad)
	    throws B2BException
	{
		DAOManager ldm_manager;
		String     ls_datos;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		ls_datos        = null;

		try
		{
			if(ap_persona != null)
			{
				String ls_primerNombre;
				String ls_primerApellido;

				ls_primerNombre       = ap_persona.getPrimerNombre();
				ls_primerApellido     = ap_persona.getPrimerApellido();

				if(StringUtils.isValidString(ls_primerNombre) && StringUtils.isValidString(ls_primerApellido))
				{
					String ls_primeraLetra;

					ls_primeraLetra = ls_primerNombre.substring(0, 1);

					if(StringUtils.isValidString(ls_primeraLetra))
					{
						String ls_usuario;
						int    li_tamUsuario;
						int    li_consecutivo;

						ls_usuario         = ls_primeraLetra + ls_primerApellido;
						li_tamUsuario      = ls_usuario.length();
						li_consecutivo     = 1;

						if(StringUtils.isValidString(as_idEntidad))
						{
							Collection<AccEntidadExternaPersona> lcaeep_entidadExternaPersona;

							lcaeep_entidadExternaPersona = DaoCreator.getAccEntidadExternaPersonaDAO(ldm_manager)
									                                     .findByIdEntidadExterna(as_idEntidad, false);

							if(CollectionUtils.isValidCollection(lcaeep_entidadExternaPersona))
							{
								for(AccEntidadExternaPersona le_temp : lcaeep_entidadExternaPersona)
								{
									if(le_temp != null)
									{
										String ls_usuarioPersona;

										ls_usuarioPersona = le_temp.getUsuario();

										if(StringUtils.isValidString(ls_usuarioPersona))
										{
											String ls_usuarioLista;

											ls_usuarioLista = ls_usuarioPersona.substring(0, li_tamUsuario);

											if(
											    StringUtils.isValidString(ls_usuarioLista)
												    && ls_usuarioLista.equalsIgnoreCase(ls_usuario)
											)
											{
												String ls_consecutivo;

												ls_consecutivo = ls_usuarioPersona.substring(
													    li_tamUsuario, ls_usuarioPersona.length()
													);

												if(StringUtils.isValidString(ls_consecutivo))
												{
													int li_secuencia;

													li_secuencia = (NumericUtils.getInt(ls_consecutivo));

													if(li_secuencia >= li_consecutivo)
														li_consecutivo = (li_secuencia + 1);
												}
											}
										}
									}
								}
							}
						}

						if(CollectionUtils.isValidCollection(acp_cp))
						{
							for(Persona lc_temp : acp_cp)
							{
								if(lc_temp != null)
								{
									String ls_usuarioPersona;

									ls_usuarioPersona = lc_temp.getUsuario();

									if(StringUtils.isValidString(ls_usuarioPersona))
									{
										String ls_primerNombreUsuarioLista;
										String ls_primerApellidoUsuarioLista;

										ls_primerNombreUsuarioLista       = lc_temp.getPrimerNombre();
										ls_primerApellidoUsuarioLista     = lc_temp.getPrimerApellido();

										if(
										    StringUtils.isValidString(ls_primerNombreUsuarioLista)
											    && StringUtils.isValidString(ls_primerApellidoUsuarioLista)
										)
										{
											String ls_primeraLetraUsuario;

											ls_primeraLetraUsuario = ls_primerNombreUsuarioLista.substring(0, 1);

											if(StringUtils.isValidString(ls_primeraLetraUsuario))
											{
												String ls_usuarioTamLista;

												ls_usuarioTamLista     = ls_primeraLetraUsuario
													+ ls_primerApellidoUsuarioLista;
												li_tamUsuario          = ls_usuarioTamLista.length();
											}

											String ls_usuarioLista;

											ls_usuarioLista = ls_usuarioPersona.substring(0, li_tamUsuario);

											if(
											    StringUtils.isValidString(ls_usuarioLista)
												    && ls_usuarioLista.equalsIgnoreCase(ls_usuario)
											)
											{
												String ls_consecutivo;

												ls_consecutivo = ls_usuarioPersona.substring(
													    li_tamUsuario, ls_usuarioPersona.length()
													);

												if(StringUtils.isValidString(ls_consecutivo))
												{
													int li_secuencia;

													li_secuencia = (NumericUtils.getInt(ls_consecutivo));

													if(li_secuencia >= li_consecutivo)
														li_consecutivo = (li_secuencia + 1);
												}
											}
										}
									}
								}
							}
						}

						ls_datos = ls_usuario + StringUtils.getString(li_consecutivo);
					}
				}
			}
		}

		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findUsuarioPersona", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return ls_datos;
	}

	/**
	 * Consulta todos los registros asociados a un id rol, id linea produccion y
	 * activo especificos.
	 *
	 * @param al_idEtapa de al id etapa
	 * @param as_idLineaProduccion            Argumento de tipo <code>String</code> que contiene el id linea
	 *            producción.
	 * @param as_idCirculo de as id circulo
	 * @param as_idRol de as id rol
	 * @return colección resultante de la búsqueda
	 * @throws B2BException             si ocurre un error en base de datos
	 */
	public synchronized Collection<UsuarioRol> findUsuarioRolByRolLineaProduccion(
	    long al_idEtapa, String as_idLineaProduccion, String as_idCirculo, String as_idRol
	)
	    throws B2BException
	{
		Collection<UsuarioRol> lcur_return;
		DAOManager             ldm_manager;

		lcur_return     = null;
		ldm_manager     = DaoManagerFactory.getDAOManager();

		try
		{
			if(StringUtils.isValidString(as_idLineaProduccion))
			{
				Constantes lc_constantes;
				boolean    lb_etapa415;
				boolean    lb_etapa460;

				lc_constantes     = null;
				lb_etapa415       = al_idEtapa == EtapaCommon.ID_ETAPA_ASIGNACION_DE_TURNOS_MANUAL_RECHAZO_DE_RECURSOS;
				lb_etapa460       = al_idEtapa == EtapaCommon.ID_ETAPA_460;

				if(!lb_etapa415 && !lb_etapa460)
					lc_constantes = DaoCreator.getConstantesDAO(ldm_manager)
							                      .findById((ConstanteCommon.ROL_SUSTANCIADOR_ACTUACIONES_ADMIN));

				if((lc_constantes != null) || StringUtils.isValidString(as_idRol))
				{
					long ll_idMotivo;

					ll_idMotivo = calcularMotivoPorLinea(al_idEtapa, as_idLineaProduccion);

					if(ll_idMotivo > 0)
					{
						MotivoTramite lmt_motivoTramite;

						lmt_motivoTramite = new MotivoTramite();

						lmt_motivoTramite.setIdEtapaOrigen(
						    (lb_etapa415 || lb_etapa460) ? al_idEtapa
						                                 : EtapaCommon.ID_ETAPA_RESPONSABLE_ACTUACIONES_ADMINISTRATIVAS
						);
						lmt_motivoTramite.setIdMotivo(ll_idMotivo);

						lmt_motivoTramite = DaoCreator.getMotivoTramiteDAO(ldm_manager).findById(lmt_motivoTramite);

						if(lmt_motivoTramite != null)
							lcur_return = DaoCreator.getUsuarioRolDAO(ldm_manager)
									                    .findByRolLineaProduccion(
									    (lc_constantes != null) ? lc_constantes.getCaracter() : as_idRol,
									    NumericUtils.getLong(lmt_motivoTramite.getIdEtapaPosterior()), as_idCirculo,
									    as_idLineaProduccion, EstadoCommon.S
									);
					}
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findRolByLineaProduccion", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lcur_return;
	}

	/**
	 * Método de transacciones con la base de datos para encontrar todos los registros de la tabla SDB_PGN_VEREDA
	 * que coincida con un ID_VEREDA especifico.
	 *
	 * @param ac_parametros objeto del cual se extrae el ID_VEREDA para realizar la consulta en la base de datos
	 * @return devuelve el valor de Vereda
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see Vereda
	 */
	public synchronized Vereda findVeredaById(Vereda ac_parametros)
	    throws B2BException
	{
		DAOManager ldm_manager;
		Vereda     lc_datos;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		lc_datos        = null;

		try
		{
			lc_datos = DaoCreator.getVeredaDAO(ldm_manager).findById(ac_parametros);
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findVeredaById", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lc_datos;
	}

	/**
	 * Método de transacciones con la base de datos para encontrar todos los registros de la tabla SDB_PGN_ZONA_REGISTRAL
	 * que coincida con un ID_ZONA_REGISTRAL especifico.
	 *
	 * @param ac_parametros objeto del cual se extrae el ID_ZONA_REGISTRAL para realizar la consulta en la base de datos
	 * @return devuelve el valor de ZonaRegistral
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see ZonaRegistral
	 */
	public synchronized ZonaRegistral findZonaRegistralById(ZonaRegistral ac_parametros)
	    throws B2BException
	{
		DAOManager    ldm_manager;
		ZonaRegistral lc_datos;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		lc_datos        = null;

		try
		{
			lc_datos = DaoCreator.getZonaRegistralDAO(ldm_manager).findById(ac_parametros);
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findZonaRegistralById", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lc_datos;
	}

	/**
	 *  Método de transacciones con la base de datos para encontrar todos los registros de la tabla SDB_PGN_CONSTANTES.
	 *
	 * @param as_idConstante correspondiente al valor del tipo de objeto String
	 * @return devuelve el valor de Map
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see Map
	 */
	public synchronized Map<String, String> generarCodigos(String as_idConstante)
	    throws B2BException
	{
		DAOManager          ldm_manager;
		Map<String, String> lms_codigos;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		lms_codigos     = new HashMap<String, String>();

		try
		{
			lms_codigos = generarCodigos(as_idConstante, ldm_manager);
		}

		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("generarCodigos", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lms_codigos;
	}

	/**
	 * Método de transacciones de la base de datos para insertar o modificar el salario mínimo.
	 *
	 * @param aottf_data objeto a insertar o modificar
	 * @param as_idUsuario usuario que hace la modificación o inserción
	 * @param as_localIp ip del servidor de aplicaciones
	 * @param as_remoteIp ip del usuario que inserta o modifica
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public synchronized void gestionSalarioMinimo(
	    SalarioMinimo aottf_data, String as_idUsuario, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		DAOManager       ldm_manager;
		SalarioMinimoDAO lotp_dao;
		SalarioMinimo    losm_tmp;
		boolean          lb_b;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		lotp_dao        = DaoCreator.getSalarioMinimoDAO(ldm_manager);

		try
		{
			if(aottf_data != null)
			{
				{
					BigDecimal lbd_tmp;
					lbd_tmp = aottf_data.getValorSalario();

					if(!NumericUtils.isValidBigDecimal(lbd_tmp))
						throw new B2BException(ErrorKeys.ERROR_VALOR_NO_NUMERICO);
				}

				{
					String ls_s;
					ls_s = aottf_data.getVigencia();

					if(!StringUtils.isValidString(ls_s))
						throw new B2BException(ErrorKeys.ERROR_SIN_VIGENCIA);
				}

				lb_b = aottf_data.isAccion();

				aottf_data.setIpCreacion(as_remoteIp);

				if(lb_b)
				{
					{
						String ls_s;
						ls_s = aottf_data.getIdSalario();

						if(!StringUtils.isValidString(ls_s))
							throw new B2BException(ErrorKeys.ERROR_ID_SALARIO);
					}

					losm_tmp = lotp_dao.findById(aottf_data, true);

					if(CollectionUtils.isValidCollection(losm_tmp.getInfoAll()))
						throw new B2BException(ErrorKeys.ERROR_ID_SALARIO_EXISTENTE);

					lotp_dao.insertOrUpdate(aottf_data, lb_b);
				}
				else
					lotp_dao.insertOrUpdate(aottf_data, lb_b);
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("gestionSalarioMinimo", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}
	}

	/**
	 * Método de transacciones con la base de datos para insertar o modificar un TIPO_ADQUISICION.
	 *
	 * @param aottf_data objeto a modificar o actualizar
	 * @param as_idUsuario usuario que realiza la modificación o inserción
	 * @param as_localIp ip del servidor de aplicaciones
	 * @param as_remoteIp ip del usuario que realiza la modificación o inserción
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public synchronized void gestionTipoAdquisicion(
	    TipoAdquisicion aottf_data, String as_idUsuario, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		DAOManager         ldm_manager;
		TipoAdquisicionDAO lotp_dao;
		TipoAdquisicion    lotp_tmp;
		boolean            lb_b;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		lotp_dao        = DaoCreator.getTipoAdquisicionDAO(ldm_manager);
		lotp_tmp        = null;

		try
		{
			if(aottf_data != null)
			{
				lb_b = aottf_data.isAccion();

				aottf_data.setIdUsuarioCreacion(as_idUsuario);
				aottf_data.setIpCreacion(as_remoteIp);
				aottf_data.setIdUsuarioModificacion(as_idUsuario);
				aottf_data.setIpModificacion(as_remoteIp);

				if(lb_b)
				{
					lotp_tmp = lotp_dao.findById(aottf_data, true);

					if((lotp_tmp != null) && CollectionUtils.isValidCollection(lotp_tmp.getInfoAll()))
						throw new B2BException(ErrorKeys.REGISTRO_CREADO);

					lotp_dao.insertOrUpdate(aottf_data, lb_b);
				}
				else
					lotp_dao.insertOrUpdate(aottf_data, lb_b);
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("gestionTipoAdquisicion", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}
	}

	/**
	 * Método de transacciones para insertar o actualizar un TIPO_ENTIDAD.
	 *
	 * @param aottf_data objeto a actualizar o modificar
	 * @param as_idUsuario usuario que realiza la modificación o inserción
	 * @param as_localIp ip de l servidor de aplicaciones
	 * @param as_remoteIp ip del usuario que realiza la acción de modificar o insertar
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public synchronized void gestionTipoEntidad(
	    TipoEntidad aottf_data, String as_idUsuario, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		DAOManager     ldm_manager;
		TipoEntidadDAO lotp_dao;
		TipoEntidad    losm_tmp;
		boolean        lb_b;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		lotp_dao        = DaoCreator.getTipoEntidadDAO(ldm_manager);

		try
		{
			if(aottf_data != null)
			{
				{
					String ls_nombre;
					ls_nombre = aottf_data.getNombre();

					if(!StringUtils.isValidString(ls_nombre))
						throw new B2BException(ErrorKeys.DEBE_DIGITAR_NOMBRE);
				}

				{
					String ls_activo;
					ls_activo = aottf_data.getActivo();

					if(!StringUtils.isValidString(ls_activo))
						throw new B2BException(ErrorKeys.DEBE_SELECCIONAR_ACTIVO);
				}

				lb_b = aottf_data.isAccion();

				aottf_data.setIdUsuarioCreacion(as_idUsuario);
				aottf_data.setIpCreacion(as_remoteIp);
				aottf_data.setIdUsuarioModificacion(as_idUsuario);
				aottf_data.setIpModificacion(as_remoteIp);

				if(lb_b)
				{
					losm_tmp = lotp_dao.findById(aottf_data);

					if(losm_tmp != null)
						throw new B2BException(ErrorKeys.REGISTRO_CREADO);

					lotp_dao.insertOrUpdate(aottf_data, lb_b);
				}
				else
					lotp_dao.insertOrUpdate(aottf_data, lb_b);
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("gestionTipoEntidad", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}
	}

	/**
	 * Método de transacciones con la base de datos para insertar o actualizar un TIPO_OFICINA.
	 *
	 * @param aottf_data objeto a modificar o insertar
	 * @param as_idUsuario usuario que realiza la accion de crear o actualizar
	 * @param as_localIp ip del servidor de aplicaciones
	 * @param as_remoteIp ip ip del usuario que realiza la actualización o inserción
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public synchronized void gestionTipoOficina(
	    TipoOficina aottf_data, String as_idUsuario, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		DAOManager ldm_manager;

		ldm_manager = DaoManagerFactory.getDAOManager();

		try
		{
			if(aottf_data != null)
			{
				TipoOficinaDAO lotp_dao;
				boolean        lb_b;

				lotp_dao     = DaoCreator.getTipoOficinaDAO(ldm_manager);
				lb_b         = aottf_data.isAccion();

				aottf_data.setIdUsuarioCreacion(as_idUsuario);
				aottf_data.setIdUsuarioModificacion(as_idUsuario);
				aottf_data.setIpCreacion(as_remoteIp);
				aottf_data.setIpModificacion(as_remoteIp);

				if(lb_b)
				{
					{
						String ls_idNombre;

						ls_idNombre = aottf_data.getNombre();

						if(!StringUtils.isValidString(ls_idNombre))
							throw new B2BException(ErrorKeys.DEBE_DIGITAR_NOMBRE);
					}

					{
						String ls_activa;

						ls_activa = aottf_data.getActiva();

						if(!StringUtils.isValidString(ls_activa))
							throw new B2BException(ErrorKeys.DEBE_SELECCIONAR_ACTIVO);
					}

					{
						TipoOficina lotp_tmp;

						lotp_tmp     = null;

						lotp_tmp = lotp_dao.findById(aottf_data);

						if((lotp_tmp != null))
							throw new B2BException(ErrorKeys.REGISTRO_CREADO);
					}

					{
						String ls_tipoentidad;

						ls_tipoentidad = aottf_data.getIdTipoEntidad();

						if(StringUtils.isValidString(ls_tipoentidad))
						{
							TipoEntidad lte_te;

							lte_te = new TipoEntidad();

							lte_te.setIdTipoEntidad(ls_tipoentidad);

							lte_te = DaoCreator.getTipoEntidadDAO(ldm_manager).findById(lte_te);

							if(lte_te == null)
								throw new B2BException(ErrorKeys.ERROR_TIPO_ENTIDAD);
						}
					}

					lotp_dao.insertOrUpdate(aottf_data, lb_b);
				}
				else
					lotp_dao.insertOrUpdate(aottf_data, lb_b);
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("gestionTipoOficina", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}
	}

	/**
	 * Método de transacciones con la base de datos para insertar o actualiazar un TIPO_PAGO.
	 *
	 * @param aottf_data objeto a actualizar o modificar
	 * @param as_idUsuario usuario el cual realiza la accion de crear o modificar
	 * @param as_localIp ip del servidor de aplicaciones
	 * @param as_remoteIp ip del usuario que realiza las acciones
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public synchronized void gestionTipoPago(
	    TipoPago aottf_data, String as_idUsuario, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		DAOManager  ldm_manager;
		TipoPagoDAO lotp_dao;
		TipoPago    lotp_tmp;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		lotp_dao        = DaoCreator.getTipoPagoDao(ldm_manager);
		lotp_tmp        = null;

		try
		{
			if(aottf_data != null)
			{
				if(!StringUtils.isValidString(aottf_data.getDescripcion()))
					throw new B2BException("El campo descripcion es invalido.");

				aottf_data.setIdUsuarioCreacion(as_idUsuario);
				aottf_data.setIpCreacion(as_remoteIp);

				if(aottf_data.isAccion())
				{
					lotp_tmp = lotp_dao.findById(aottf_data, true);

					if((lotp_tmp != null) && CollectionUtils.isValidCollection(lotp_tmp.getInfoAll()))
						throw new B2BException("El registro ya se encuentra creado.");

					lotp_dao.insertTipoPago(aottf_data);
				}
				else
				{
					if(!StringUtils.isValidString(aottf_data.getIdTipoPago()))
						throw new B2BException("El campo id tipo pago es invalido.");

					aottf_data.setIdUsuarioModificacion(as_idUsuario);
					aottf_data.setIpModificacion(as_remoteIp);
					lotp_dao.modifyById(aottf_data);
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("gestionTipoPago", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}
	}

	/**
	 * Método de transaccciones con la base de datos para la insercion o modificacion de TIPO_TARIFA.
	 *
	 * @param aottf_data objeto a insertar o modificar
	 * @param ab_b indica si se va a insertar o modificar registros ( true inserta, false actualiza)
	 * @param as_idUsuario usuario que realiza la accion de insertar o modificar
	 * @param as_localIp ip del servidor de aplicaciones
	 * @param as_remoteIp ip del usuario que modifica o inserta en la base de datos
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public synchronized void gestionTiposTarifas(
	    TipoTarifaRegistral aottf_data, boolean ab_b, String as_idUsuario, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		DAOManager             ldm_manager;
		TipoTarifaRegistralDAO lottr_dao;
		TipoTarifaRegistral    lottr_ttr;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		lottr_dao       = DaoCreator.getTipoTarifaRegistralDao(ldm_manager);

		try
		{
			if(aottf_data != null)
			{
				aottf_data.setIdUsuarioCreacion(as_idUsuario);
				aottf_data.setVersion(NumericUtils.getLongWrapper(1L));

				{
					String ls_idTipoTarifa;
					ls_idTipoTarifa = aottf_data.getIdTipoTarifa();

					if(!StringUtils.isValidString(ls_idTipoTarifa))
						throw new B2BException(ErrorKeys.ERROR_CAMPO_TIPO_TARIFA);
				}

				{
					String ls_nombre;
					ls_nombre = aottf_data.getNombre();

					if(!StringUtils.isValidString(ls_nombre))
						throw new B2BException(ErrorKeys.DEBE_DIGITAR_NOMBRE);
				}

				{
					Date ld_fechaHasta;
					Date ld_fechaDesde;

					ld_fechaHasta     = aottf_data.getFechaHasta();
					ld_fechaDesde     = aottf_data.getFechaDesde();

					if(ld_fechaDesde == null)
					{
						Object[] aoa_messageArgs = new String[1];
						aoa_messageArgs[0] = " desde ";
						throw new B2BException(ErrorKeys.ERROR_CAMPO_FECHA_OBLIGATORIO, aoa_messageArgs);
					}

					if(ld_fechaHasta != null)
					{
						if(ld_fechaDesde.after(ld_fechaHasta))
						{
							Object[] aoa_messageArgs = new String[1];
							aoa_messageArgs[0] = " desde ";
							throw new B2BException(ErrorKeys.FECHA_SUPERIOR_ACTUAL, aoa_messageArgs);
						}
					}
				}

				{
					Double ld_porcentaje;
					ld_porcentaje = NumericUtils.getDoubleWrapper(aottf_data.getPorcentaje());

					if(ld_porcentaje == null)
						throw new B2BException(ErrorKeys.ERROR_VALOR_PORCENTAJE_NUMERICO);

					if((NumericUtils.getDouble(ld_porcentaje) == 0.0) || (NumericUtils.getDouble(ld_porcentaje) > 100))
						throw new B2BException(ErrorKeys.ERROR_RANGO_PORCENTAJE_INVALIDO);
				}

				{
					String ls_activo;

					ls_activo = aottf_data.getActivo();

					if(!StringUtils.isValidString(ls_activo))
						throw new B2BException(ErrorKeys.DEBE_SELECCIONAR_ACTIVO);
				}

				if(ab_b)
				{
					aottf_data.setValidTipoId(true);
					aottf_data.setValidversion(true);
					lottr_ttr = lottr_dao.findById(aottf_data, ab_b);

					if((lottr_ttr != null) && !CollectionUtils.isValidCollection(lottr_ttr.getInfoAll()))
						lottr_dao.insertTipoTarifa(aottf_data);
					else
						throw new B2BException(ErrorKeys.ID_TIPO_TARIFA_EXISTENTE);
				}

				else
					lottr_dao.modifyById(aottf_data);
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("gestionTiposTarifas", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}
	}

	/**
	 * Método de transacciones con la base de datos cone el fin de insertar o modificar en la base de datos para la tabla SDB_PGN_CANAL_ORIGEN_SERVICIO.
	 *
	 * @param acos_canalOrigenServicio objeto a actualizar o insertar
	 * @param ab_insert indica si se va a modificar o insertar dependiendo su valor(true inserta ,false modifica)
	 * @param as_idUsuario es el usuario que realiza la modificación o la inserción
	 * @param as_remoteIp es la ip de donde se ha realizado la acción de modificar o insertar
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public synchronized void insertUpdateCanalOrigenServicio(
	    CanalOrigenServicio acos_canalOrigenServicio, boolean ab_insert, String as_idUsuario, String as_remoteIp
	)
	    throws B2BException
	{
		DAOManager ldm_manager;
		ldm_manager = DaoManagerFactory.getDAOManagerNPA();

		try
		{
			if(acos_canalOrigenServicio != null)
			{
				CanalOrigenServicioDAO lcos_DAO;

				lcos_DAO = DaoCreator.getCanalOrigenServicioDAO(ldm_manager);

				if(ab_insert)
				{
					acos_canalOrigenServicio.setIdUsuarioCreacion(as_idUsuario);
					acos_canalOrigenServicio.setIpCreacion(as_remoteIp);

					lcos_DAO.insert(acos_canalOrigenServicio);
				}
				else
				{
					acos_canalOrigenServicio.setIdUsuarioModificacion(as_idUsuario);
					acos_canalOrigenServicio.setIpModificacion(as_remoteIp);

					lcos_DAO.update(acos_canalOrigenServicio);
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("insertUpdateCanalOrigenServicio", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}
	}

	/**
	 * Método para realizar transacciones con la base de datos para insertar o actualizar  registros
	 * de Circulo registral.
	 *
	 * @param acr_circulo es el objeto que se va a insertar o actualizar
	 * @param ab_insert indica si se debe insertar en la base de datos
	 * @param as_idUsuario indica el usuario que hace la actualizacion o la insercion
	 * @param as_remoteIp es la ip del cliente que esta realizando la actualizacion
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public synchronized void insertUpdateCirculo(
	    CirculoRegistral acr_circulo, boolean ab_insert, String as_idUsuario, String as_remoteIp
	)
	    throws B2BException
	{
		DAOManager ldm_manager;
		ldm_manager = DaoManagerFactory.getDAOManager();

		try
		{
			CirculoRegistralDao lcr_DAO;
			lcr_DAO = DaoCreator.getCirculoRegistralDAO(ldm_manager);

			if(acr_circulo == null)
				throw new B2BException(ErrorKeys.ERROR_INTERNO_SISTEMA);

			if(ab_insert)
			{
				{
					CirculoRegistral lcr_temp;

					lcr_temp = lcr_DAO.findById(acr_circulo);

					if(lcr_temp != null)
						throw new B2BException(ErrorKeys.ERROR_ID_CIRCULO_REPETIDO);
				}

				if(StringUtils.isValidString(acr_circulo.getIdRegional()))
				{
					Regional lr_r;

					lr_r = new Regional();

					lr_r.setIdRegional(acr_circulo.getIdRegional());

					lr_r = DaoCreator.getRegionalDAO(ldm_manager).findById(lr_r);

					if(lr_r == null)
						throw new B2BException(ErrorKeys.DEBE_DIGITAR_ID_REGIONAL);
				}

				acr_circulo.setIdUsuarioCreacion(as_idUsuario);
				acr_circulo.setIpCreacion(as_remoteIp);

				lcr_DAO.insertOrUpdate(acr_circulo, ab_insert);
			}
			else
			{
				acr_circulo.setIdUsuarioModificacion(as_idUsuario);
				acr_circulo.setIpModificacion(as_remoteIp);

				lcr_DAO.insertOrUpdate(acr_circulo, ab_insert);
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("insertUpdateCirculo", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}
	}

	/**
	 * Método de transacciones con la base de datos cone el fin de insertar o
	 * modificar en la base de datos para la tabla SDB_ACC_DESBORDE.
	 *
	 * @param acd_desborde correspondiente al valor del tipo de objeto Collection<Desborde>
	 * @param as_idUsuario            es el usuario que realiza la modificación o la inserción
	 * @param as_remoteIp            es la ip de donde se ha realizado la acción de modificar o
	 *            insertar
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public synchronized void insertUpdateDesbordes(
	    Collection<Desborde> acd_desborde, String as_idUsuario, String as_remoteIp
	)
	    throws B2BException
	{
		DAOManager ldm_manager;
		ldm_manager = DaoManagerFactory.getDAOManager();

		try
		{
			DesbordeDAO ld_DAO;
			ld_DAO = DaoCreator.getDesbordeDAO(ldm_manager);

			if(acd_desborde == null)
				throw new B2BException(ErrorKeys.ERROR_INTERNO_SISTEMA);

			for(Desborde ld_desborde : acd_desborde)
			{
				if((ld_desborde != null) && StringUtils.isValidString(ld_desborde.getHabilitada()))
				{
					if(!StringUtils.isValidString(ld_desborde.getObservaciones()))
					{
						CirculoRegistral lcr_circulo;
						lcr_circulo = new CirculoRegistral();

						lcr_circulo.setIdCirculo(ld_desborde.getIdCirculoDesborde());

						lcr_circulo = DaoCreator.getCirculoRegistralDAO(ldm_manager).findById(lcr_circulo);

						if(lcr_circulo != null)
						{
							Object[] loa_messageArgs = new String[1];
							loa_messageArgs[0] = lcr_circulo.getNombre();

							throw new B2BException(ErrorKeys.ERROR_SIN_OBSERVACIONES_DESBORDE, loa_messageArgs);
						}
						else
							throw new B2BException(ErrorKeys.ERROR_CIRCULO_NO_EXISTE);
					}

					Desborde ld_temp;

					ld_temp = ld_DAO.findById(ld_desborde);

					if(ld_temp != null)
					{
						ld_desborde.setIdUsuarioModificacion(as_idUsuario);
						ld_desborde.setIpModificacion(as_remoteIp);

						ld_DAO.insertOrUpdate(ld_desborde, false);
					}
					else
					{
						ld_desborde.setIdUsuarioCreacion(as_idUsuario);
						ld_desborde.setIpCreacion(as_remoteIp);

						ld_DAO.insertOrUpdate(ld_desborde, true);
					}
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("insertUpdateDesbordes", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}
	}

	/**
	 * Método de transaciones con la base de datos para insertar o actualizar registros de la tabla SDB_PNG_DOMINIO_NATURALEZA_JURIDICA.
	 *
	 * @param adnj_dominio objeto que se desea actualizar o insertar
	 * @param ab_insert indica si se desea insertar o modificar algun registro
	 * @param as_idUsuario es el usuario que realiza la insercion o la actualizacion
	 * @param as_remoteIp es la ip de donde se realiza el procedimiendo de insertar o actualizar
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public synchronized void insertUpdateDominioNaturalezaJuridica(
	    DominioNaturalezaJuridica adnj_dominio, boolean ab_insert, String as_idUsuario, String as_remoteIp
	)
	    throws B2BException
	{
		DAOManager ldm_manager;
		ldm_manager = DaoManagerFactory.getDAOManager();

		try
		{
			DominioNaturalezaJuridicaDAO lcr_DAO;
			lcr_DAO = DaoCreator.getDominioNaturalezaJuridicaDAO(ldm_manager);

			if(adnj_dominio == null)
				throw new B2BException(ErrorKeys.ERROR_INTERNO_SISTEMA);

			adnj_dominio.setIdUsuarioCreacion(as_idUsuario);
			adnj_dominio.setIpCreacion(as_remoteIp);
			adnj_dominio.setIdUsuarioModificacion(as_idUsuario);
			adnj_dominio.setIpModificacion(as_remoteIp);

			if(ab_insert)
			{
				DominioNaturalezaJuridica ldnj_temp;
				ldnj_temp     = new DominioNaturalezaJuridica();

				ldnj_temp = lcr_DAO.findById(adnj_dominio);

				if(ldnj_temp != null)
					throw new B2BException(ErrorKeys.ERROR_ID_DOMINIO_NATURALEZA_JURIDICA_REPETIDO);

				lcr_DAO.insertOrUpdate(adnj_dominio, true);
			}
			else
				lcr_DAO.insertOrUpdate(adnj_dominio, false);
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("insertUpdateDominioNaturalezaJuridica", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}
	}

	/**
	 * Método de transacciones con la base de datos cone el fin de insertar o modificar en la base de datos para la tabla SDB_PGN_ENTIDAD_RECAUDADORA.
	 *
	 * @param aer_param objeto a actualizar o insertar
	 * @param ab_insert indica si se va a modificar o insertar dependiendo su valor(true inserta ,false modifica)
	 * @param as_idUsuario es el usuario que realiza la modificación o la inserción
	 * @param as_remoteIp es la ip de donde se ha realizado la acción de modificar o insertar
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public synchronized void insertUpdateEntidadRecaudadora(
	    EntidadRecaudadora aer_param, boolean ab_insert, String as_idUsuario, String as_remoteIp
	)
	    throws B2BException
	{
		DAOManager ldm_manager;
		ldm_manager = DaoManagerFactory.getDAOManagerNPA();

		try
		{
			EntidadRecaudadoraDAO lerd_DAO;
			lerd_DAO = DaoCreator.getEntidadRecaudadoraDAO(ldm_manager);

			if(aer_param == null)
				throw new B2BException(ErrorKeys.ERROR_INTERNO_SISTEMA);

			aer_param.setIdUsuarioCreacion(as_idUsuario);
			aer_param.setIpCreacion(as_remoteIp);
			aer_param.setIdUsuarioModificacion(as_idUsuario);
			aer_param.setIpModificacion(as_remoteIp);

			if(ab_insert)
			{
				EntidadRecaudadora ler_temp;
				ler_temp     = new EntidadRecaudadora();

				ler_temp = lerd_DAO.findById(aer_param);

				if(ler_temp != null)
					throw new B2BException(
					    com.bachue.snr.prosnr21.common.constants.ErrorKeys.ERROR_CODIGO_ENTIDAD_RECAUDADORA_REPETIDO
					);

				lerd_DAO.insert(aer_param);
			}
			else
				lerd_DAO.update(aer_param);
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("insertUpdateEntidadRecaudadora", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}
	}

	/**
	 * Método de transacciones con la base de datos cone el fin de insertar o modificar en la base de datos para la tabla SDB_PNG_ESTADO_ANOTACION.
	 *
	 * @param aea_estadoAnotacion objeto a actualizar o insertar
	 * @param ab_insert indica si se va a modificar o insertar dependiendo su valor(true inserta ,false modifica)
	 * @param as_idUsuario es el usuario que realiza la modificación o la inserción
	 * @param as_remoteIp es la ip de donde se ha realizado la acción de modificar o insertar
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public synchronized void insertUpdateEstadoAnotacion(
	    EstadoAnotacion aea_estadoAnotacion, boolean ab_insert, String as_idUsuario, String as_remoteIp
	)
	    throws B2BException
	{
		DAOManager ldm_manager;
		ldm_manager = DaoManagerFactory.getDAOManager();

		try
		{
			EstadoAnotacionDAO lea_DAO;
			lea_DAO = DaoCreator.getEstadoAnotacionDAO(ldm_manager);

			if(aea_estadoAnotacion == null)
				throw new B2BException(ErrorKeys.ERROR_INTERNO_SISTEMA);

			aea_estadoAnotacion.setIdUsuarioCreacion(as_idUsuario);
			aea_estadoAnotacion.setIpCreacion(as_remoteIp);
			aea_estadoAnotacion.setIdUsuarioModificacion(as_idUsuario);
			aea_estadoAnotacion.setIpModificacion(as_remoteIp);

			if(ab_insert)
			{
				EstadoAnotacion leaj_temp;
				leaj_temp     = new EstadoAnotacion();

				leaj_temp = lea_DAO.findById(aea_estadoAnotacion);

				if(leaj_temp != null)
					throw new B2BException(ErrorKeys.ERROR_ID_ESTADO_ANOTACION_REPETIDO);

				lea_DAO.insertOrUpdate(aea_estadoAnotacion, true);
			}
			else
				lea_DAO.insertOrUpdate(aea_estadoAnotacion, false);
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("insertUpdateEstadoAnotacion", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}
	}

	/**
	 * Método de transacciones con la base de datos cone el fin de insertar o modificar en la base de datos para la tabla SDB_PGN_TIPO_AREA.
	 *
	 * @param aen_estadoNupre objeto a actualizar o insertar
	 * @param ab_insert indica si se va a modificar o insertar dependiendo su valor(true inserta ,false modifica)
	 * @param as_idUsuario es el usuario que realiza la modificación o la inserción
	 * @param as_remoteIp es la ip de donde se ha realizado la acción de modificar o insertar
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public synchronized void insertUpdateEstadoNupre(
	    EstadoNupre aen_estadoNupre, boolean ab_insert, String as_idUsuario, String as_remoteIp
	)
	    throws B2BException
	{
		DAOManager ldm_manager;
		ldm_manager = DaoManagerFactory.getDAOManager();

		try
		{
			EstadoNupreDAO len_DAO;
			len_DAO = DaoCreator.getEstadoNupreDAO(ldm_manager);

			if(aen_estadoNupre == null)
				throw new B2BException(ErrorKeys.ERROR_INTERNO_SISTEMA);

			aen_estadoNupre.setIdUsuarioCreacion(as_idUsuario);
			aen_estadoNupre.setIpCreacion(as_remoteIp);
			aen_estadoNupre.setIdUsuarioModificacion(as_idUsuario);
			aen_estadoNupre.setIpModificacion(as_remoteIp);

			if(ab_insert)
			{
				EstadoNupre len_temp;
				len_temp     = new EstadoNupre();

				len_temp = len_DAO.findById(aen_estadoNupre);

				if(len_temp != null)
					throw new B2BException(ErrorKeys.ERROR_ID_ESTADO_NUPRE_REPETIDO);

				len_DAO.insertOrUpdate(aen_estadoNupre, true);
			}
			else
				len_DAO.insertOrUpdate(aen_estadoNupre, false);
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("insertUpdateEstadoNupre", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}
	}

	/**
	 * Método de transacciones con la base de datos cone el fin de insertar o modificar en la base de datos para la tabla SDB_PGN_ESTADO_PREDIO.
	 *
	 * @param aep_estadoPredio objeto a actualizar o insertar
	 * @param ab_insert indica si se va a modificar o insertar dependiendo su valor(true inserta ,false modifica)
	 * @param as_idUsuario es el usuario que realiza la modificación o la inserción
	 * @param as_remoteIp es la ip de donde se ha realizado la acción de modificar o insertar
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public synchronized void insertUpdateEstadoPredio(
	    EstadoPredio aep_estadoPredio, boolean ab_insert, String as_idUsuario, String as_remoteIp
	)
	    throws B2BException
	{
		DAOManager ldm_manager;
		ldm_manager = DaoManagerFactory.getDAOManager();

		try
		{
			EstadoPredioDao lep_DAO;
			lep_DAO = DaoCreator.getEstadoPredioDao(ldm_manager);

			if(aep_estadoPredio == null)
				throw new B2BException(ErrorKeys.ERROR_INTERNO_SISTEMA);

			aep_estadoPredio.setIdUsuarioCreacion(as_idUsuario);
			aep_estadoPredio.setIpCreacion(as_remoteIp);
			aep_estadoPredio.setIdUsuarioModificacion(as_idUsuario);
			aep_estadoPredio.setIpModificacion(as_remoteIp);

			if(ab_insert)
			{
				EstadoPredio lep_temp;
				lep_temp     = new EstadoPredio();

				lep_temp = lep_DAO.findById(aep_estadoPredio);

				if(lep_temp != null)
					throw new B2BException(ErrorKeys.ERROR_ID_ESTADO_PREDIO_REPETIDO);

				lep_DAO.insertOrUpdate(aep_estadoPredio, true);
			}
			else
				lep_DAO.insertOrUpdate(aep_estadoPredio, false);
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("insertUpdateEstadoPredio", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}
	}

	/**
	 * Método de transacciones con la base de datos cone el fin de insertar o modificar en la base de datos para la tabla SDB_PNG_ESTADO_REGISTRO.
	 *
	 * @param aer_estadoRegistro objeto a actualizar o insertar
	 * @param ab_insert indica si se va a modificar o insertar dependiendo su valor(true inserta ,false modifica)
	 * @param as_idUsuario es el usuario que realiza la modificación o la inserción
	 * @param as_remoteIp es la ip de donde se ha realizado la acción de modificar o insertar
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public synchronized void insertUpdateEstadoRegistro(
	    EstadoRegistro aer_estadoRegistro, boolean ab_insert, String as_idUsuario, String as_remoteIp
	)
	    throws B2BException
	{
		DAOManager ldm_manager;
		ldm_manager = DaoManagerFactory.getDAOManager();

		try
		{
			EstadoRegistroDAO ler_DAO;
			ler_DAO = DaoCreator.getEstadoRegistroDAO(ldm_manager);

			if(aer_estadoRegistro == null)
				throw new B2BException(ErrorKeys.ERROR_INTERNO_SISTEMA);

			aer_estadoRegistro.setIdUsuarioCreacion(as_idUsuario);
			aer_estadoRegistro.setIpCreacion(as_remoteIp);
			aer_estadoRegistro.setIdUsuarioModificacion(as_idUsuario);
			aer_estadoRegistro.setIpModificacion(as_remoteIp);

			if(ab_insert)
			{
				EstadoRegistro ler_temp;
				ler_temp     = new EstadoRegistro();

				ler_temp = ler_DAO.findById(aer_estadoRegistro);

				if(ler_temp != null)
					throw new B2BException(ErrorKeys.ERROR_ID_ESTADO_REGISTRO_REPETIDO);

				ler_DAO.insertOrUpdate(aer_estadoRegistro, true);
			}
			else
				ler_DAO.insertOrUpdate(aer_estadoRegistro, false);
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("insertUpdateEstadoRegistro", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}
	}

	/**
	 * Método de transsacciones con la base de datos para insertar o actualizar en la tabla SDB_PGN_ETAPA.
	 *
	 * @param ae_etapa Objeto a insertar o actualizar
	 * @param ab_insert indica si se va a insertar o actualizar
	 * @param as_idUsuario usuario en sesion
	 * @param as_remoteIp ip del usuario que realiza la modificación o la inserción
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public synchronized void insertUpdateEtapa(
	    Etapa ae_etapa, boolean ab_insert, String as_idUsuario, String as_remoteIp
	)
	    throws B2BException
	{
		DAOManager ldm_manager;
		ldm_manager = DaoManagerFactory.getDAOManager();

		try
		{
			EtapaDAO le_DAO;
			le_DAO = DaoCreator.getEtapaDAO(ldm_manager);

			if(ae_etapa == null)
				throw new B2BException(ErrorKeys.ERROR_INTERNO_SISTEMA);

			if(ab_insert)
			{
				{
					Etapa le_temp;
					le_temp     = new Etapa();

					le_temp = le_DAO.findById(ae_etapa);

					if(le_temp != null)
						throw new B2BException(ErrorKeys.ERROR_ID_ETAPA_REPETIDO);
				}

				{
					Fases lf_f;

					lf_f = new Fases();

					lf_f.setIdFase(ae_etapa.getIdFase());

					lf_f = DaoCreator.getFasesDAO(ldm_manager).findById(lf_f);

					if(lf_f == null)
						throw new B2BException(ErrorKeys.ERROR_SIN_ID_FASE);
				}

				ae_etapa.setIdUsuarioCreacion(as_idUsuario);
				ae_etapa.setIpCreacion(as_remoteIp);

				le_DAO.insertOrUpdate(ae_etapa, ab_insert);
			}
			else
			{
				ae_etapa.setIdUsuarioModificacion(as_idUsuario);
				ae_etapa.setIpModificacion(as_remoteIp);

				le_DAO.insertOrUpdate(ae_etapa, ab_insert);
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("insertUpdateEtapa", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}
	}

	/**
	 * Método de transacciones con la base de datos cone el fin de insertar o modificar en la base de datos para la tabla SDB_PGN_FASES.
	 *
	 * @param af_fase  objeto de tipo fase el cual se va a modificar o insertar
	 * @param ab_insert indica si se va a insertar o modificar en la base de datos (true inserta, false modifica)
	 * @param as_idUsuario es el usuario que realiza la insercion o modificación
	 * @param as_remoteIp es la ip que pertenece al usuario que esta realizando la modificación o inserción
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public synchronized void insertUpdateFase(
	    Fases af_fase, boolean ab_insert, String as_idUsuario, String as_remoteIp
	)
	    throws B2BException
	{
		DAOManager ldm_manager;
		ldm_manager = DaoManagerFactory.getDAOManager();

		try
		{
			FasesDAO lf_DAO;
			lf_DAO = DaoCreator.getFasesDAO(ldm_manager);

			if(af_fase == null)
				throw new B2BException(ErrorKeys.ERROR_INTERNO_SISTEMA);

			if(ab_insert)
			{
				Fases lf_temp;
				lf_temp     = new Fases();

				lf_temp = lf_DAO.findById(af_fase);

				if(lf_temp != null)
					throw new B2BException(ErrorKeys.ERROR_ID_FASE_REPETIDO);

				af_fase.setIdUsuarioCreacion(as_idUsuario);
				af_fase.setIpCreacion(as_remoteIp);

				lf_DAO.insertOrUpdate(af_fase, true);
			}
			else
			{
				af_fase.setIdUsuarioModificacion(as_idUsuario);
				af_fase.setIpModificacion(as_remoteIp);

				lf_DAO.insertOrUpdate(af_fase, false);
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("insertUpdateFase", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}
	}

	/**
	 * Método de transacciones con la base de datos cone el fin de insertar o modificar en la base de datos para la tabla SDB_PNG_GRUPO_NATURALEZA_JURIDICA.
	 *
	 * @param agnj_grupoNaturalezaJuridica objeto a actualizar o insertar
	 * @param ab_insert indica si se va a modificar o insertar dependiendo su valor(true inserta ,false modifica)
	 * @param as_idUsuario es el usuario que realiza la modificación o la inserción
	 * @param as_remoteIp es la ip de donde se ha realizado la acción de modificar o insertar
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public synchronized void insertUpdateGrupoNaturalezaJuridica(
	    GrupoNaturalezaJuridica agnj_grupoNaturalezaJuridica, boolean ab_insert, String as_idUsuario, String as_remoteIp
	)
	    throws B2BException
	{
		DAOManager ldm_manager;
		ldm_manager = DaoManagerFactory.getDAOManager();

		try
		{
			GrupoNaturalezaJuridicaDAO lgnj_DAO;
			lgnj_DAO = DaoCreator.getGrupoNaturalezaJuridicaDAO(ldm_manager);

			if(agnj_grupoNaturalezaJuridica == null)
				throw new B2BException(ErrorKeys.ERROR_INTERNO_SISTEMA);

			agnj_grupoNaturalezaJuridica.setIdUsuarioCreacion(as_idUsuario);
			agnj_grupoNaturalezaJuridica.setIpCreacion(as_remoteIp);
			agnj_grupoNaturalezaJuridica.setIdUsuarioModificacion(as_idUsuario);
			agnj_grupoNaturalezaJuridica.setIpModificacion(as_remoteIp);

			if(ab_insert)
			{
				GrupoNaturalezaJuridica lgnj_temp;
				lgnj_temp     = new GrupoNaturalezaJuridica();

				lgnj_temp = lgnj_DAO.findById(agnj_grupoNaturalezaJuridica);

				if(lgnj_temp != null)
					throw new B2BException(ErrorKeys.ERROR_ID_GRUPO_NATURALEZA_JURIDICO_REPETIDO);

				lgnj_DAO.insertOrUpdate(agnj_grupoNaturalezaJuridica, true);
			}
			else
				lgnj_DAO.insertOrUpdate(agnj_grupoNaturalezaJuridica, false);
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("insertUpdateGrupoNaturalezaJuridica", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}
	}

	/**
	 * Método de transacciones con la base de datos cone el fin de insertar o modificar en la base de datos para la tabla SDB_COL_INTERESADO_DOCUMENTO_TIPO.
	 *
	 * @param aidt_intDocTipo objeto que se pretende modificar o insertar
	 * @param ab_insert indica si se desea insertar o actualizar (true inserta, false modifica)
	 * @param as_idUsuario usuario que realiza la modificacion o insercion
	 * @param as_remoteIp ip de donde se ejecuta la accion de modificar o insertar
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public synchronized void insertUpdateInteresadoDocumentoTipo(
	    InteresadoDocumentoTipo aidt_intDocTipo, boolean ab_insert, String as_idUsuario, String as_remoteIp
	)
	    throws B2BException
	{
		DAOManager ldm_manager;
		ldm_manager = DaoManagerFactory.getDAOManager();

		try
		{
			InteresadoDocumentoTipoDAO lidt_DAO;
			lidt_DAO = DaoCreator.getInteresadoDocumentoTipoDAO(ldm_manager);

			if(aidt_intDocTipo == null)
				throw new B2BException(ErrorKeys.ERROR_INTERNO_SISTEMA);

			if(ab_insert)
			{
				InteresadoDocumentoTipo lidt_temp;
				lidt_temp     = new InteresadoDocumentoTipo();

				lidt_temp = lidt_DAO.findById(aidt_intDocTipo);

				if(lidt_temp != null)
					throw new B2BException(ErrorKeys.ERROR_ID_INTERESADO_DOCUMENTO_TIPO_REPETIDO);

				aidt_intDocTipo.setIdUsuarioCreacion(as_idUsuario);
				aidt_intDocTipo.setIpCreacion(as_remoteIp);

				lidt_DAO.insertOrUpdate(aidt_intDocTipo, true);
			}
			else
			{
				aidt_intDocTipo.setIdUsuarioModificacion(as_idUsuario);
				aidt_intDocTipo.setIpModificacion(as_remoteIp);

				lidt_DAO.insertOrUpdate(aidt_intDocTipo, false);
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("insertUpdateInteresadoDocumentoTipo", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}
	}

	/**
	 * Método de transacciones con la base de datos cone el fin de insertar o modificar en la base de datos para la tabla SDB_PGN_MEDIO_RECAUDO.
	 *
	 * @param amr_medioRecaudo objeto a actualizar o insertar
	 * @param ab_insert indica si se va a modificar o insertar dependiendo su valor(true inserta ,false modifica)
	 * @param as_idUsuario es el usuario que realiza la modificación o la inserción
	 * @param as_remoteIp es la ip de donde se ha realizado la acción de modificar o insertar
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public synchronized void insertUpdateMedioRecaudo(
	    MedioRecaudo amr_medioRecaudo, boolean ab_insert, String as_idUsuario, String as_remoteIp
	)
	    throws B2BException
	{
		DAOManager ldm_manager;
		ldm_manager = DaoManagerFactory.getDAOManagerNPA();

		try
		{
			MedioRecaudoDAO lmr_DAO;
			lmr_DAO = DaoCreator.getMedioRecaudoDAO(ldm_manager);

			if(amr_medioRecaudo == null)
				throw new B2BException(ErrorKeys.ERROR_INTERNO_SISTEMA);

			amr_medioRecaudo.setIdUsuarioCreacion(as_idUsuario);
			amr_medioRecaudo.setIpCreacion(as_remoteIp);
			amr_medioRecaudo.setIdUsuarioModificacion(as_idUsuario);
			amr_medioRecaudo.setIpModificacion(as_remoteIp);

			if(ab_insert)
			{
				MedioRecaudo lmr_temp;
				lmr_temp     = new MedioRecaudo();

				lmr_temp = lmr_DAO.findById(amr_medioRecaudo);

				if(lmr_temp != null)
					throw new B2BException(ErrorKeys.ERROR_ID_MEDIO_RECAUDO_REPETIDO);

				lmr_DAO.insert(amr_medioRecaudo);
			}
			else
				lmr_DAO.update(amr_medioRecaudo);
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("insertUpdateMedioRecaudo", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}
	}

	/**
	 * Método de transacciones con la base de datos cone el fin de insertar o modificar en la base de datos para la tabla SDB_PGN_MENSAJE_RESPUESTA.
	 *
	 * @param amr_mensajeRespuesta objeto a actualizar o insertar
	 * @param ab_insert indica si se va a modificar o insertar dependiendo su valor(true inserta ,false modifica)
	 * @param as_idUsuario es el usuario que realiza la modificación o la inserción
	 * @param as_remoteIp es la ip de donde se ha realizado la acción de modificar o insertar
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public synchronized void insertUpdateMensajeRespuesta(
	    MensajeRespuesta amr_mensajeRespuesta, boolean ab_insert, String as_idUsuario, String as_remoteIp
	)
	    throws B2BException
	{
		DAOManager ldm_manager;
		ldm_manager = DaoManagerFactory.getDAOManagerNPA();

		try
		{
			MensajeRespuestaDAO lmr_DAO;
			lmr_DAO = DaoCreator.getMensajeRespuestaDAO(ldm_manager);

			if(amr_mensajeRespuesta == null)
				throw new B2BException(ErrorKeys.ERROR_INTERNO_SISTEMA);

			amr_mensajeRespuesta.setIdUsuarioCreacion(as_idUsuario);
			amr_mensajeRespuesta.setIpCreacion(as_remoteIp);
			amr_mensajeRespuesta.setIdUsuarioModificacion(as_idUsuario);
			amr_mensajeRespuesta.setIpModificacion(as_remoteIp);

			if(ab_insert)
			{
				MensajeRespuesta lmr_temp;
				lmr_temp     = new MensajeRespuesta();

				lmr_temp = lmr_DAO.findById(amr_mensajeRespuesta);

				if(lmr_temp != null)
					throw new B2BException(ErrorKeys.ERROR_CODIGO_MENSAJE_RESPUESTA_REPETIDO);

				lmr_DAO.insert(amr_mensajeRespuesta);
			}
			else
				lmr_DAO.update(amr_mensajeRespuesta);
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("insertUpdateMensajeRespuesta", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}
	}

	/**
	 * Método de transacciones con la base de datos cone el fin de insertar o modificar en la base de datos para la tabla SDB_PGN_PUNTO_RECAUDO.
	 *
	 * @param apr_puntoRecaudo objeto a actualizar o insertar
	 * @param ab_insert indica si se va a modificar o insertar dependiendo su valor(true inserta ,false modifica)
	 * @param as_idUsuario es el usuario que realiza la modificación o la inserción
	 * @param as_remoteIp es la ip de donde se ha realizado la acción de modificar o insertar
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public synchronized void insertUpdatePuntoRecaudo(
	    PuntoRecaudo apr_puntoRecaudo, boolean ab_insert, String as_idUsuario, String as_remoteIp
	)
	    throws B2BException
	{
		DAOManager ldm_manager;
		ldm_manager = DaoManagerFactory.getDAOManagerNPA();

		try
		{
			PuntoRecaudoDAO lpr_DAO;
			lpr_DAO = DaoCreator.getPuntoRecaudoDAO(ldm_manager);

			if(apr_puntoRecaudo == null)
				throw new B2BException(ErrorKeys.ERROR_INTERNO_SISTEMA);

			if(ab_insert)
			{
				apr_puntoRecaudo.setIdUsuarioCreacion(as_idUsuario);
				apr_puntoRecaudo.setIpCreacion(as_remoteIp);

				lpr_DAO.insert(apr_puntoRecaudo);
			}
			else
			{
				apr_puntoRecaudo.setIdUsuarioModificacion(as_idUsuario);
				apr_puntoRecaudo.setIpModificacion(as_remoteIp);

				lpr_DAO.update(apr_puntoRecaudo);
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("insertUpdatePuntoRecaudo", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}
	}

	/**
	 * Método de transacciones con la base de datos cone el fin de insertar o modificar en la base de datos para la tabla SDB_PGN_PUNTO_RECAUDO_TIPO_RECAUDO.
	 *
	 * @param aprtr_param objeto a actualizar o insertar
	 * @param ab_insert indica si se va a modificar o insertar dependiendo su valor(true inserta ,false modifica)
	 * @param as_idUsuario es el usuario que realiza la modificación o la inserción
	 * @param as_remoteIp es la ip de donde se ha realizado la acción de modificar o insertar
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public synchronized void insertUpdatePuntoRecaudoTipoRecaudo(
	    PuntoRecaudoTipoRecaudo aprtr_param, boolean ab_insert, String as_idUsuario, String as_remoteIp
	)
	    throws B2BException
	{
		DAOManager ldm_manager;
		ldm_manager = DaoManagerFactory.getDAOManagerNPA();

		try
		{
			PuntoRecaudoTipoRecaudoDAO lprtr_DAO;
			lprtr_DAO = DaoCreator.getPuntoRecaudoTipoRecaudoDAO(ldm_manager);

			if(aprtr_param == null)
				throw new B2BException(ErrorKeys.ERROR_INTERNO_SISTEMA);

			aprtr_param.setIdUsuarioCreacion(as_idUsuario);
			aprtr_param.setIpCreacion(as_remoteIp);
			aprtr_param.setIdUsuarioModificacion(as_idUsuario);
			aprtr_param.setIpModificacion(as_remoteIp);

			if(ab_insert)
			{
				PuntoRecaudoTipoRecaudo lprtr_temp;
				lprtr_temp     = new PuntoRecaudoTipoRecaudo();

				lprtr_temp = lprtr_DAO.findById(aprtr_param);

				if(lprtr_temp != null)
					throw new B2BException(ErrorKeys.ERROR_CODIGO_PUNTO_RECAUDO_REPETIDO);

				lprtr_DAO.insert(aprtr_param);
			}
			else
				lprtr_DAO.update(aprtr_param);
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("insertUpdatePuntoRecaudoTipoRecaudo", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}
	}

	/**
	 * Método de transacciones con la base de datos cone el fin de insertar o
	 * modificar en la base de datos para la tabla SDB_PGN_RANGO_CUANTIA.
	 *
	 * @param atr_parametros correspondiente al valor del tipo de objeto RangoCuantia
	 * @param ab_accion correspondiente al valor del tipo de objeto boolean
	 * @param as_userId correspondiente al valor del tipo de objeto String
	 * @param as_remoteIp            es la ip de donde se ha realizado la acción de modificar o
	 *            insertar
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public synchronized void insertUpdateRangoCuantia(
	    RangoCuantia atr_parametros, boolean ab_accion, String as_userId, String as_remoteIp
	)
	    throws B2BException
	{
		DAOManager ldm_manager;

		ldm_manager = DaoManagerFactory.getDAOManager();

		try
		{
			if(atr_parametros != null)
			{
				{
					Constantes lc_constante;

					lc_constante = new Constantes();

					lc_constante.setIdConstante(ConstanteCommon.CARACTERES_MAYOR_MENOR);

					lc_constante = DaoCreator.getConstantesDAO(ldm_manager).findById(lc_constante);

					if(lc_constante != null)
					{
						String ls_caracteres;

						ls_caracteres = lc_constante.getCaracter();

						if(StringUtils.isValidString(ls_caracteres))
						{
							String[] lsa_caracteres;

							lsa_caracteres = ls_caracteres.split(",");

							if(CollectionUtils.isValidCollection(lsa_caracteres))
							{
								boolean lb_operadorSup;
								boolean lb_operadorInf;
								String  ld_operadorInf;
								String  ld_operadorSup;

								lb_operadorSup     = false;
								lb_operadorInf     = false;
								ld_operadorInf     = atr_parametros.getOperadorInferior();
								ld_operadorSup     = atr_parametros.getOperadorSuperior();

								if(!StringUtils.isValidString(ld_operadorInf))
									throw new B2BException(ErrorKeys.OPERADOR_INFERIOR_NO_VALIDO);

								if(!StringUtils.isValidString(ld_operadorSup))
									throw new B2BException(ErrorKeys.OPERADOR_SUPERIOR_NO_VALIDO);

								for(String ls_caracter : lsa_caracteres)
								{
									if(ls_caracter.equals(ld_operadorInf))
										lb_operadorInf = true;

									if(ls_caracter.equals(ld_operadorSup))
										lb_operadorSup = true;
								}

								if(!lb_operadorInf)
									throw new B2BException(ErrorKeys.OPERADOR_INFERIOR_NO_VALIDO);

								if(!lb_operadorSup)
									throw new B2BException(ErrorKeys.OPERADOR_SUPERIOR_NO_VALIDO);
							}
						}
					}
				}

				if(ab_accion)
				{
					atr_parametros.setIdUsuarioCreacion(as_userId);
					atr_parametros.setIpCreacion(as_remoteIp);
				}
				else
				{
					atr_parametros.setIdUsuarioModificacion(as_userId);
					atr_parametros.setIpModificacion(as_remoteIp);
				}

				DaoCreator.getRangoCuantiaDAO(ldm_manager).insertOrUpdate(atr_parametros, ab_accion);
			}
		}

		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("insertUpdateRangoCuantia", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}
	}

	/**
	 * Método de transacciones con la base de datos cone el fin de insertar o
	 * modificar en la base de datos para la tabla SDB_PGN_REPORTES.
	 *
	 * @param ar_reportes            objeto a modificar o insertar en la base de datos
	 * @param ab_insert            indica si se va a modificar o insertar dependiendo su valor(true
	 *            inserta ,false modifica)
	 * @param as_idUsuario            es el usuario que realiza la modificación o la inserción
	 * @param as_remoteIp            es la ip de donde se ha realizado la acción de modificar o
	 *            insertar
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public synchronized void insertUpdateReportes(
	    Reportes ar_reportes, boolean ab_insert, String as_idUsuario, String as_remoteIp
	)
	    throws B2BException
	{
		DAOManager ldm_manager;
		ldm_manager = DaoManagerFactory.getDAOManager();

		try
		{
			ReportesDAO lr_DAO;
			lr_DAO = DaoCreator.getReportesDAO(ldm_manager);

			if(ar_reportes == null)
				throw new B2BException(ErrorKeys.ERROR_INTERNO_SISTEMA);

			if(ab_insert)
			{
				Reportes lidt_temp;
				lidt_temp     = new Reportes();

				lidt_temp = lr_DAO.findById(ar_reportes);

				if(lidt_temp != null)
					throw new B2BException(ErrorKeys.ERROR_ID_REPORTES);

				ar_reportes.setIdUsuarioCreacion(as_idUsuario);
				ar_reportes.setIpCreacion(as_remoteIp);

				lr_DAO.insertOrUpdate(ar_reportes, true);
			}
			else
			{
				ar_reportes.setIdUsuarioModificacion(as_idUsuario);
				ar_reportes.setIpModificacion(as_remoteIp);

				lr_DAO.insertOrUpdate(ar_reportes, false);
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("insertUpdateReportes", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}
	}

	/**
	 * Método de transacciones con la base de datos cone el fin de insertar o modificar en la base de datos para la tabla SDB_PGN_SUCURSAL_CANAL_ORIGEN_SERVICIO.
	 *
	 * @param ascos_param objeto a actualizar o insertar
	 * @param ab_insert indica si se va a modificar o insertar dependiendo su valor(true inserta ,false modifica)
	 * @param as_idUsuario es el usuario que realiza la modificación o la inserción
	 * @param as_remoteIp es la ip de donde se ha realizado la acción de modificar o insertar
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public synchronized void insertUpdateSucursalCanalOrigenServicio(
	    SucursalCanalOrigenServicio ascos_param, boolean ab_insert, String as_idUsuario, String as_remoteIp
	)
	    throws B2BException
	{
		DAOManager ldm_manager;
		ldm_manager = DaoManagerFactory.getDAOManagerNPA();

		try
		{
			SucursalCanalOrigenServicioDAO lscosd_DAO;
			lscosd_DAO = DaoCreator.getSucursalCanalOrigenServicioDAO(ldm_manager);

			if(ascos_param == null)
				throw new B2BException(ErrorKeys.ERROR_INTERNO_SISTEMA);

			ascos_param.setIdUsuarioCreacion(as_idUsuario);
			ascos_param.setIpCreacion(as_remoteIp);
			ascos_param.setIdUsuarioModificacion(as_idUsuario);
			ascos_param.setIpModificacion(as_remoteIp);

			if(ab_insert)
			{
				SucursalCanalOrigenServicio lscos_temp;
				lscos_temp     = new SucursalCanalOrigenServicio();

				lscos_temp = lscosd_DAO.findById(ascos_param);

				if(lscos_temp != null)
					throw new B2BException(ErrorKeys.ERROR_CODIGO_MENSAJE_RESPUESTA_REPETIDO);

				lscosd_DAO.insert(ascos_param);
			}
			else
				lscosd_DAO.update(ascos_param);
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("insertUpdateSucursalCanalOrigenServicio", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}
	}

	/**
	 * Método de transacciones con la base de datos cone el fin de insertar o
	 * modificar en la base de datos para la tabla SDB_PGN_TARIFA_FIJA.
	 *
	 * @param ar_tarifaFija correspondiente al valor del tipo de objeto TarifaFija
	 * @param ab_insert            indica si se va a modificar o insertar dependiendo su valor(true
	 *            inserta ,false modifica)
	 * @param as_idUsuario            es el usuario que realiza la modificación o la inserción
	 * @param as_remoteIp            es la ip de donde se ha realizado la acción de modificar o
	 *            insertar
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public synchronized void insertUpdateTarifaFija(
	    TarifaFija ar_tarifaFija, boolean ab_insert, String as_idUsuario, String as_remoteIp
	)
	    throws B2BException
	{
		DAOManager ldm_manager;
		ldm_manager = DaoManagerFactory.getDAOManager();

		try
		{
			TarifaFijaDAO lr_DAO;
			TipoActoDAO   lta_DAO;

			lr_DAO      = DaoCreator.getTarifaFijaDAO(ldm_manager);
			lta_DAO     = DaoCreator.getTipoActoDAO(ldm_manager);

			if(ar_tarifaFija == null)
				throw new B2BException(ErrorKeys.ERROR_INTERNO_SISTEMA);

			TipoActo lta_tipoActoValidacion;
			lta_tipoActoValidacion = lta_DAO.findTipoActoById(ar_tarifaFija.getIdTipoActo());

			if(lta_tipoActoValidacion != null)
			{
				String ls_version;
				ls_version = StringUtils.getString(lta_tipoActoValidacion.getVersion());

				if(
				    StringUtils.isValidString(ls_version)
					    && !ls_version.equals(StringUtils.getString(ar_tarifaFija.getVersionActo()))
				)
					throw new B2BException(ErrorKeys.ERROR_TIPO_ACTO_INEXISTENTE);
			}
			else
				throw new B2BException(ErrorKeys.ERROR_TIPO_ACTO_INEXISTENTE);

			if(ab_insert)
			{
				TarifaFija lidt_temp;

				lidt_temp     = new TarifaFija();
				lidt_temp     = lr_DAO.findById(ar_tarifaFija);

				if(lidt_temp != null)
					throw new B2BException(ErrorKeys.ERROR_ID_TARIFA_FIJA);

				ar_tarifaFija.setIdUsuarioCreacion(as_idUsuario);
				ar_tarifaFija.setIpCreacion(as_remoteIp);

				lr_DAO.insertOrUpdate(ar_tarifaFija, true);
			}
			else
			{
				ar_tarifaFija.setIdUsuarioModificacion(as_idUsuario);
				ar_tarifaFija.setIpModificacion(as_remoteIp);

				lr_DAO.insertOrUpdate(ar_tarifaFija, false);
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("insertUpdateTarifaFija", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}
	}

	/**
	 * Método de transacciones con la base de datos cone el fin de insertar o
	 * modificar en la base de datos para la tabla SDB_PGN_TIPO_ACTO_CONDICION.
	 *
	 * @param ar_tipoActoCondicion correspondiente al valor del tipo de objeto TipoActoCondicion
	 * @param ab_insert            indica si se va a modificar o insertar dependiendo su valor(true
	 *            inserta ,false modifica)
	 * @param as_idUsuario            es el usuario que realiza la modificación o la inserción
	 * @param as_remoteIp            es la ip de donde se ha realizado la acción de modificar o
	 *            insertar
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public synchronized void insertUpdateTipoActoCondicion(
	    TipoActoCondicion ar_tipoActoCondicion, boolean ab_insert, String as_idUsuario, String as_remoteIp
	)
	    throws B2BException
	{
		DAOManager ldm_manager;
		ldm_manager = DaoManagerFactory.getDAOManager();

		try
		{
			TipoActoCondicionDAO lr_DAO;
			lr_DAO = DaoCreator.getTipoActoCondicionDAO(ldm_manager);

			if(ar_tipoActoCondicion == null)
				throw new B2BException(ErrorKeys.ERROR_INTERNO_SISTEMA);

			if(ab_insert)
			{
				ar_tipoActoCondicion.setIdUsuarioCreacion(as_idUsuario);
				ar_tipoActoCondicion.setIpCreacion(as_remoteIp);

				lr_DAO.insertOrUpdate(ar_tipoActoCondicion, true);
			}
			else
			{
				ar_tipoActoCondicion.setIdUsuarioModificacion(as_idUsuario);
				ar_tipoActoCondicion.setIpModificacion(as_remoteIp);

				lr_DAO.insertOrUpdate(ar_tipoActoCondicion, false);
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("insertUpdateTipoActoCondicion", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}
	}

	/**
	 * Método de transacciones con la base de datos cone el fin de insertar o
	 * modificar en la base de datos para la tabla SDB_PGN_TIPO_ACTO_EJECUTORIA.
	 *
	 * @param ar_tipoActoEjecutoria correspondiente al valor del tipo de objeto TipoActoEjecutoria
	 * @param ab_insert            indica si se va a modificar o insertar dependiendo su valor(true
	 *            inserta ,false modifica)
	 * @param as_idUsuario            es el usuario que realiza la modificación o la inserción
	 * @param as_remoteIp            es la ip de donde se ha realizado la acción de modificar o
	 *            insertar
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public synchronized void insertUpdateTipoActoEjecutoria(
	    TipoActoEjecutoria ar_tipoActoEjecutoria, boolean ab_insert, String as_idUsuario, String as_remoteIp
	)
	    throws B2BException
	{
		DAOManager ldm_manager;
		ldm_manager = DaoManagerFactory.getDAOManager();

		try
		{
			if(ar_tipoActoEjecutoria != null)
			{
				TipoActoEjecutoriaDAO lr_DAO;

				lr_DAO = DaoCreator.getTipoActoEjecutoriaDAO(ldm_manager);

				if(ab_insert)
				{
					TipoActoEjecutoria ltae_tipoActo;

					ltae_tipoActo = new TipoActoEjecutoria();

					ltae_tipoActo.setIdTipoActo(ar_tipoActoEjecutoria.getIdTipoActo());
					ltae_tipoActo.setVersionActo(ar_tipoActoEjecutoria.getVersionActo());
					ltae_tipoActo.setIdTipoDocumento(ar_tipoActoEjecutoria.getIdTipoDocumento());

					ltae_tipoActo = lr_DAO.findById(ltae_tipoActo);

					if(ltae_tipoActo != null)
					{
						Integer li_version;

						li_version = ltae_tipoActo.getVersion();

						if(NumericUtils.isValidInteger(li_version))
						{
							int li_versionSum;

							li_versionSum = NumericUtils.getInt(li_version);

							ar_tipoActoEjecutoria.setVersion(NumericUtils.getInteger(li_versionSum + 1));
						}
					}
					else
						ar_tipoActoEjecutoria.setVersion(NumericUtils.getInteger(1));

					ar_tipoActoEjecutoria.setIdUsuarioCreacion(as_idUsuario);
					ar_tipoActoEjecutoria.setIpCreacion(as_remoteIp);

					lr_DAO.insertOrUpdate(ar_tipoActoEjecutoria, true);
				}
				else
				{
					ar_tipoActoEjecutoria.setIdUsuarioModificacion(as_idUsuario);
					ar_tipoActoEjecutoria.setIpModificacion(as_remoteIp);

					lr_DAO.insertOrUpdate(ar_tipoActoEjecutoria, false);
				}
			}
			else
				throw new B2BException(ErrorKeys.ERROR_INTERNO_SISTEMA);
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("insertUpdateTipoActoEjecutoria", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}
	}

	/**
	 * Método de transacciones con la base de datos cone el fin de insertar o modificar en la base de datos para la tabla SDB_PGN_TIPO_AREA.
	 *
	 * @param ata_tipoArea Argumento de tipo <code>TipoArea</code> que contiene el objeto a actualizar o insertar.
	 * @param ab_insert Argumento de tipo <code>Boolean</code> que indica si se va a modificar o insertar dependiendo su valor(true inserta ,false modifica)
	 * @param as_idUsuario Argumento de tipo <code>String</code> que contiene el usuario que realiza la modificación o la inserción
	 * @param as_remoteIp Argumento de tipo <code>String</code> que contiene la ip de donde se ha realizado la acción de modificar o insertar
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public synchronized void insertUpdateTipoArea(
	    TipoArea ata_tipoArea, boolean ab_insert, String as_idUsuario, String as_remoteIp
	)
	    throws B2BException
	{
		DAOManager ldm_manager;
		ldm_manager = DaoManagerFactory.getDAOManager();

		try
		{
			TipoAreaDAO lta_DAO;
			lta_DAO = DaoCreator.getTipoAreaDAO(ldm_manager);

			if(ata_tipoArea == null)
				throw new B2BException(ErrorKeys.ERROR_INTERNO_SISTEMA);

			if(ab_insert)
			{
				TipoArea lta_temp;
				lta_temp     = new TipoArea();

				lta_temp = lta_DAO.findById(ata_tipoArea);

				if(lta_temp != null)
					throw new B2BException(ErrorKeys.ERROR_ID_TIPO_AREA_REPETIDO);

				ata_tipoArea.setIdUsuarioCreacion(as_idUsuario);
				ata_tipoArea.setIpCreacion(as_remoteIp);

				lta_DAO.insertOrUpdate(ata_tipoArea, true);
			}
			else
			{
				ata_tipoArea.setIdUsuarioModificacion(as_idUsuario);
				ata_tipoArea.setIpModificacion(as_remoteIp);
				lta_DAO.insertOrUpdate(ata_tipoArea, false);
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("insertUpdateTipoArea", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}
	}

	/**
	 * Método de transacciones con la base de datos cone el fin de insertar o modificar en la base de datos para la tabla SDB_PGN_TIPO_CANAL_ORIGEN.
	 *
	 * @param atco_tipoCanalOrigen objeto a actualizar o insertar
	 * @param ab_insert indica si se va a modificar o insertar dependiendo su valor(true inserta ,false modifica)
	 * @param as_idUsuario es el usuario que realiza la modificación o la inserción
	 * @param as_remoteIp es la ip de donde se ha realizado la acción de modificar o insertar
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public synchronized void insertUpdateTipoCanalOrigen(
	    TipoCanalOrigen atco_tipoCanalOrigen, boolean ab_insert, String as_idUsuario, String as_remoteIp
	)
	    throws B2BException
	{
		DAOManager ldm_manager;
		ldm_manager = DaoManagerFactory.getDAOManager();

		try
		{
			TipoCanalOrigenDAO lta_DAO;
			lta_DAO = DaoCreator.getTipoCanalOrigenDAO(ldm_manager);

			if(atco_tipoCanalOrigen == null)
				throw new B2BException(ErrorKeys.ERROR_INTERNO_SISTEMA);

			if(ab_insert)
			{
				TipoCanalOrigen ltco_temp;
				ltco_temp     = new TipoCanalOrigen();

				ltco_temp = lta_DAO.findById(atco_tipoCanalOrigen);

				if(ltco_temp != null)
					throw new B2BException(ErrorKeys.ERROR_ID_TIPO_CANAL_ORIGEN_REPETIDO);

				atco_tipoCanalOrigen.setIdUsuarioCreacion(as_idUsuario);
				atco_tipoCanalOrigen.setIpCreacion(as_remoteIp);

				lta_DAO.insertOrUpdate(atco_tipoCanalOrigen, true);
			}
			else
			{
				atco_tipoCanalOrigen.setIdUsuarioModificacion(as_idUsuario);
				atco_tipoCanalOrigen.setIpModificacion(as_remoteIp);
				lta_DAO.insertOrUpdate(atco_tipoCanalOrigen, false);
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("insertUpdateTipoCanalOrigen", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}
	}

	/**
	 * Método de transacciones con la base de datos cone el fin de insertar o
	 * modificar en la base de datos para la tabla SDB_PGN_TIPO_DECISION_RECURSO.
	 *
	 * @param ar_tipoDecisionRecurso correspondiente al valor del tipo de objeto TipoDecisionRecurso
	 * @param ab_insert            indica si se va a modificar o insertar dependiendo su valor(true
	 *            inserta ,false modifica)
	 * @param as_idUsuario            es el usuario que realiza la modificación o la inserción
	 * @param as_remoteIp            es la ip de donde se ha realizado la acción de modificar o
	 *            insertar
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public synchronized void insertUpdateTipoDecisionRecurso(
	    TipoDecisionRecurso ar_tipoDecisionRecurso, boolean ab_insert, String as_idUsuario, String as_remoteIp
	)
	    throws B2BException
	{
		DAOManager ldm_manager;
		ldm_manager = DaoManagerFactory.getDAOManager();

		try
		{
			TipoDecisionRecursoDAO lr_DAO;
			lr_DAO = DaoCreator.getTipoDecisionRecursoDAO(ldm_manager);

			if(ar_tipoDecisionRecurso == null)
				throw new B2BException(ErrorKeys.ERROR_INTERNO_SISTEMA);

			ar_tipoDecisionRecurso.setIdUsuarioCreacion(as_idUsuario);
			ar_tipoDecisionRecurso.setIpCreacion(as_remoteIp);
			ar_tipoDecisionRecurso.setIdUsuarioModificacion(as_idUsuario);
			ar_tipoDecisionRecurso.setIpModificacion(as_remoteIp);

			if(ab_insert)
			{
				TipoDecisionRecurso lidt_temp;
				lidt_temp     = new TipoDecisionRecurso();

				lidt_temp = lr_DAO.findById(ar_tipoDecisionRecurso);

				if(lidt_temp != null)
					throw new B2BException(ErrorKeys.ERROR_ID_TIPO_DECISION_RECURSO);

				lr_DAO.insertOrUpdate(ar_tipoDecisionRecurso, true);
			}
			else
				lr_DAO.insertOrUpdate(ar_tipoDecisionRecurso, false);
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("insertUpdateTipoDecisionRecurso", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}
	}

	/**
	 * Método de transacciones con la base de datos cone el fin de insertar o modificar en la base de datos para la tabla SDB_PGN_TIPO_ESTADO_LIQUIDACION.
	 *
	 * @param atel_tipoEstadoLiquidacion objeto a actualizar o insertar
	 * @param ab_insert indica si se va a modificar o insertar dependiendo su valor(true inserta ,false modifica)
	 * @param as_idUsuario es el usuario que realiza la modificación o la inserción
	 * @param as_remoteIp es la ip de donde se ha realizado la acción de modificar o insertar
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public synchronized void insertUpdateTipoEstadoLiquidacion(
	    TipoEstadoLiquidacion atel_tipoEstadoLiquidacion, boolean ab_insert, String as_idUsuario, String as_remoteIp
	)
	    throws B2BException
	{
		DAOManager ldm_manager;
		ldm_manager = DaoManagerFactory.getDAOManager();

		try
		{
			TipoEstadoLiquidacionDAO ltel_DAO;
			ltel_DAO = DaoCreator.getTipoEstadoLiquidacionDAO(ldm_manager);

			if(atel_tipoEstadoLiquidacion != null)
			{
				if(ab_insert)
				{
					atel_tipoEstadoLiquidacion.setIdUsuarioCreacion(as_idUsuario);
					atel_tipoEstadoLiquidacion.setIpCreacion(as_remoteIp);
				}
				else
				{
					atel_tipoEstadoLiquidacion.setIdUsuarioModificacion(as_idUsuario);
					atel_tipoEstadoLiquidacion.setIpModificacion(as_remoteIp);
				}

				ltel_DAO.insertOrUpdate(atel_tipoEstadoLiquidacion, ab_insert);
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("insertUpdateTipoEstadoLiquidacion", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}
	}

	/**
	 * Método de transacciones con la base de datos cone el fin de insertar o modificar en la base de datos para la tabla SDB_PGN_TIPO_RECAUDO.
	 *
	 * @param atr_parametro objeto a actualizar o insertar
	 * @param ab_insert indica si se va a modificar o insertar dependiendo su valor(true inserta ,false modifica)
	 * @param as_idUsuario es el usuario que realiza la modificación o la inserción
	 * @param as_remoteIp es la ip de donde se ha realizado la acción de modificar o insertar
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public synchronized void insertUpdateTipoRecaudo(
	    TipoRecaudo atr_parametro, boolean ab_insert, String as_idUsuario, String as_remoteIp
	)
	    throws B2BException
	{
		DAOManager ldm_manager;
		ldm_manager = DaoManagerFactory.getDAOManagerNPA();

		try
		{
			TipoRecaudoDAO ltr_DAO;
			ltr_DAO = DaoCreator.getTipoRecaudoDAO(ldm_manager);

			if(atr_parametro == null)
				throw new B2BException(ErrorKeys.ERROR_INTERNO_SISTEMA);

			atr_parametro.setIdUsuarioCreacion(as_idUsuario);
			atr_parametro.setIpCreacion(as_remoteIp);
			atr_parametro.setIdUsuarioModificacion(as_idUsuario);
			atr_parametro.setIpModificacion(as_remoteIp);

			if(ab_insert)
			{
				TipoRecaudo lmr_temp;
				lmr_temp     = new TipoRecaudo();

				lmr_temp = ltr_DAO.findById(atr_parametro);

				if(lmr_temp != null)
					throw new B2BException(ErrorKeys.ERROR_CODIGO_TIPO_RECAUDO_REPETIDO);

				ltr_DAO.insert(atr_parametro);
			}
			else
				ltr_DAO.update(atr_parametro);
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("insertUpdateTipoRecaudo", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}
	}

	/**
	 * Método de transacciones con la base de datos cone el fin de insertar o
	 * modificar en la base de datos para la tabla SDB_PGN_TIPO_RECURSO.
	 *
	 * @param ar_tipoRecurso            objeto a modificar o insertar en la base de datos
	 * @param ab_insert            indica si se va a modificar o insertar dependiendo su valor(true
	 *            inserta ,false modifica)
	 * @param as_idUsuario            es el usuario que realiza la modificación o la inserción
	 * @param as_remoteIp            es la ip de donde se ha realizado la acción de modificar o
	 *            insertar
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public synchronized void insertUpdateTipoRecurso(
	    TipoRecurso ar_tipoRecurso, boolean ab_insert, String as_idUsuario, String as_remoteIp
	)
	    throws B2BException
	{
		DAOManager ldm_manager;
		ldm_manager = DaoManagerFactory.getDAOManager();

		try
		{
			TipoRecursoDAO lr_DAO;
			lr_DAO = DaoCreator.getTipoRecursoDAO(ldm_manager);

			if(ar_tipoRecurso == null)
				throw new B2BException(ErrorKeys.ERROR_INTERNO_SISTEMA);

			ar_tipoRecurso.setIdUsuarioCreacion(as_idUsuario);
			ar_tipoRecurso.setIpCreacion(as_remoteIp);
			ar_tipoRecurso.setIdUsuarioModificacion(as_idUsuario);
			ar_tipoRecurso.setIpModificacion(as_remoteIp);

			if(ab_insert)
			{
				TipoRecurso lidt_temp;
				lidt_temp     = new TipoRecurso();

				lidt_temp = lr_DAO.findById(ar_tipoRecurso);

				if(lidt_temp != null)
					throw new B2BException(ErrorKeys.ERROR_ID_TIPO_RECURSO);

				lr_DAO.insertOrUpdate(ar_tipoRecurso, true);
			}
			else
				lr_DAO.insertOrUpdate(ar_tipoRecurso, false);
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("insertUpdateTipoRecurso", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}
	}

	/**
	 * Método de transacciones con la base de datos cone el fin de insertar o modificar en la base de datos para la tabla SDB_PGN_TIPO_REQUIERE_MATRICULA.
	 *
	 * @param atrm_param objeto a actualizar o insertar
	 * @param ab_insert indica si se va a modificar o insertar dependiendo su valor(true inserta ,false modifica)
	 * @param as_idUsuario es el usuario que realiza la modificación o la inserción
	 * @param as_remoteIp es la ip de donde se ha realizado la acción de modificar o insertar
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public synchronized void insertUpdateTipoRequiereMatricula(
	    TipoRequiereMatricula atrm_param, boolean ab_insert, String as_idUsuario, String as_remoteIp
	)
	    throws B2BException
	{
		DAOManager ldm_manager;
		ldm_manager = DaoManagerFactory.getDAOManager();

		try
		{
			TipoRequiereMatriculaDAO ltrm_dao;
			ltrm_dao = DaoCreator.getTipoRequiereMatriculaDAO(ldm_manager);

			if(atrm_param == null)
				throw new B2BException(ErrorKeys.ERROR_INTERNO_SISTEMA);

			atrm_param.setIdUsuarioCreacion(as_idUsuario);
			atrm_param.setIpCreacion(as_remoteIp);
			atrm_param.setIdUsuarioModificacion(as_idUsuario);
			atrm_param.setIpModificacion(as_remoteIp);

			if(ab_insert)
			{
				TipoRequiereMatricula ltrm_temp;
				ltrm_temp     = new TipoRequiereMatricula();

				ltrm_temp = ltrm_dao.findById(atrm_param);

				if(ltrm_temp != null)
					throw new B2BException(ErrorKeys.ERROR_ID_ESTADO_PREDIO_REPETIDO);

				ltrm_dao.insertOrUpdate(atrm_param, true);
			}
			else
				ltrm_dao.insertOrUpdate(atrm_param, false);
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("insertUpdateTipoRequiereMatricula", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}
	}

	/**
	 * Método de transacciones con la base de datos cone el fin de insertar o modificar en la base de datos para la tabla SDB_ACC_ACTO.
	 *
	 * @param ata_tipoActo objeto a actualizar o insertar
	 * @param ab_insert indica si se va a modificar o insertar dependiendo su valor(true inserta ,false modifica)
	 * @param as_idUsuario es el usuario que realiza la modificación o la inserción
	 * @param as_remoteIp es la ip de donde se ha realizado la acción de modificar o insertar
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public synchronized void insertUpdateTiposActo(
	    TipoActo ata_tipoActo, boolean ab_insert, String as_idUsuario, String as_remoteIp
	)
	    throws B2BException
	{
		DAOManager ldm_manager;
		ldm_manager = DaoManagerFactory.getDAOManager();

		try
		{
			TipoActoDAO lta_DAO;
			lta_DAO = DaoCreator.getTipoActoDAO(ldm_manager);

			if(ata_tipoActo == null)
				throw new B2BException(ErrorKeys.ERROR_INTERNO_SISTEMA);

			ata_tipoActo.setIdUsuarioCreacion(as_idUsuario);
			ata_tipoActo.setIpCreacion(as_remoteIp);
			ata_tipoActo.setIdUsuarioModificacion(as_idUsuario);
			ata_tipoActo.setIpModificacion(as_remoteIp);

			if(ab_insert)
			{
				TipoActo lidt_temp;
				lidt_temp     = new TipoActo();

				lidt_temp = lta_DAO.findById(ata_tipoActo);

				if(lidt_temp != null)
					throw new B2BException(ErrorKeys.ERROR_ID_TIPO_ACTO_REPETIDO);

				lta_DAO.insertOrUpdate(ata_tipoActo, true);
			}
			else
				lta_DAO.insertOrUpdate(ata_tipoActo, false);
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("insertUpdateTiposActo", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}
	}

	/**
	 * Método de transacciones con la base de datos cone el fin de insertar o modificar en la base de datos para la tabla SDB_PNG_TIPO_ANOTACION.
	 *
	 * @param ata_tipoAnotacion objeto que se va a insertar o modificar
	 * @param ab_insert indica si se va a modificar o insertar dependiendo su valor(true inserta ,false modifica)
	 * @param as_idUsuario es el usuario que realiza la modificación o la inserción
	 * @param as_remoteIp es la ip de donde se ha realizado la acción de modificar o insertar
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public synchronized void insertUpdateTiposAnotacion(
	    TipoAnotacion ata_tipoAnotacion, boolean ab_insert, String as_idUsuario, String as_remoteIp
	)
	    throws B2BException
	{
		DAOManager ldm_manager;
		ldm_manager = DaoManagerFactory.getDAOManager();

		try
		{
			TipoAnotacionDAO lta_DAO;
			lta_DAO = DaoCreator.getTipoAnotacionDAO(ldm_manager);

			if(ata_tipoAnotacion == null)
				throw new B2BException(ErrorKeys.ERROR_INTERNO_SISTEMA);

			if(ab_insert)
			{
				ata_tipoAnotacion.setIdUsuarioCreacion(as_idUsuario);
				ata_tipoAnotacion.setIpCreacion(as_remoteIp);

				ata_tipoAnotacion.setIdUsuarioCreacion(as_idUsuario);
				lta_DAO.insertOrUpdate(ata_tipoAnotacion, true);
			}
			else
			{
				ata_tipoAnotacion.setIdUsuarioModificacion(as_idUsuario);
				ata_tipoAnotacion.setIpModificacion(as_remoteIp);

				lta_DAO.insertOrUpdate(ata_tipoAnotacion, false);
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("insertUpdateTiposAnotacion", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}
	}

	/**
	 * Método de transacciones con la base de datos cone el fin de insertar o modificar en la base de datos para la tabla SDB_ACC_TIPO_CAUSAL.
	 *
	 * @param atc_tipoCausal objeto a modificar o insertar en la base de datos
	 * @param ab_insert indica si se va a modificar o insertar dependiendo su valor(true inserta ,false modifica)
	 * @param as_idUsuario es el usuario que realiza la modificación o la inserción
	 * @param as_remoteIp es la ip de donde se ha realizado la acción de modificar o insertar
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public synchronized void insertUpdateTiposCausal(
	    TipoCausal atc_tipoCausal, boolean ab_insert, String as_idUsuario, String as_remoteIp
	)
	    throws B2BException
	{
		DAOManager ldm_manager;
		ldm_manager = DaoManagerFactory.getDAOManager();

		try
		{
			TipoCausalDAO ltc_DAO;
			ltc_DAO = DaoCreator.getTipoCausalDAO(ldm_manager);

			if(atc_tipoCausal == null)
				throw new B2BException(ErrorKeys.ERROR_INTERNO_SISTEMA);

			String ls_idSubproceso;
			ls_idSubproceso = atc_tipoCausal.getIdSubProceso();

			if(StringUtils.isValidString(ls_idSubproceso))
			{
				String ls_idProceso;
				ls_idProceso = atc_tipoCausal.getIdProceso();

				if(!StringUtils.isValidString(ls_idProceso))
					throw new B2BException(ErrorKeys.ERROR_SIN_ID_PROCESO);

				Subproceso ls_subproceso;
				ls_subproceso = new Subproceso();

				ls_subproceso.setIdProceso(ls_idProceso);
				ls_subproceso.setIdSubproceso(ls_idSubproceso);

				SubprocesoDAO ls_DAO;
				ls_DAO     = DaoCreator.getSubprocesoDAO(ldm_manager);

				ls_subproceso = ls_DAO.findById(ls_subproceso);

				if(ls_subproceso == null)
					throw new B2BException(ErrorKeys.ERROR_SIN_ID_PROCESO);
			}

			if(ab_insert)
			{
				TipoCausal ltc_temp;
				ltc_temp     = new TipoCausal();

				ltc_temp = ltc_DAO.findById(atc_tipoCausal);

				atc_tipoCausal.setIdUsuarioCreacion(as_idUsuario);
				atc_tipoCausal.setIpCreacion(as_remoteIp);

				if(ltc_temp != null)
					throw new B2BException(ErrorKeys.ERROR_ID_TIPO_CAUSAL_REPETIDO);

				ltc_DAO.insertOrUpdate(atc_tipoCausal, true);
			}
			else
			{
				atc_tipoCausal.setIdUsuarioModificacion(as_idUsuario);
				atc_tipoCausal.setIpModificacion(as_remoteIp);

				ltc_DAO.insertOrUpdate(atc_tipoCausal, false);
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("insertUpdateTiposCausal", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}
	}

	/**
	 * Método de transacciones con la base de datos cone el fin de insertar o modificar en la base de datos para la tabla SDB_PNG_TIPO_EJE.
	 *
	 * @param ate_tipoEje objeto a modificar o insertar en la base de datos
	 * @param ab_insert indica si se va a modificar o insertar dependiendo su valor(true inserta ,false modifica)
	 * @param as_idUsuario es el usuario que realiza la modificación o la inserción
	 * @param as_remoteIp es la ip de donde se ha realizado la acción de modificar o insertar
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public synchronized void insertUpdateTiposEje(
	    TipoEje ate_tipoEje, boolean ab_insert, String as_idUsuario, String as_remoteIp
	)
	    throws B2BException
	{
		DAOManager ldm_manager;
		ldm_manager = DaoManagerFactory.getDAOManager();

		try
		{
			TipoEjeDAO lte_DAO;
			lte_DAO = DaoCreator.getTipoEjeDAO(ldm_manager);

			if(ate_tipoEje == null)
				throw new B2BException(ErrorKeys.ERROR_INTERNO_SISTEMA);

			if(ab_insert)
			{
				TipoEje lte_temp;
				lte_temp     = new TipoEje();

				lte_temp = lte_DAO.findById(ate_tipoEje);

				if(lte_temp != null)
					throw new B2BException(ErrorKeys.ERROR_ID_TIPO_EJE_REPETIDO);

				ate_tipoEje.setIdUsuarioCreacion(as_idUsuario);
				ate_tipoEje.setIpCreacion(as_remoteIp);

				lte_DAO.insertOrUpdate(ate_tipoEje, true);
			}
			else
			{
				ate_tipoEje.setIdUsuarioModificacion(as_idUsuario);
				ate_tipoEje.setIpModificacion(as_remoteIp);

				lte_DAO.insertOrUpdate(ate_tipoEje, false);
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("insertUpdateTiposEje", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}
	}

	/**
	 * Método de transacciones con la base de datos cone el fin de insertar o modificar en la base de datos para la tabla SDB_ACC_TIPO_ESTADO_SOLICITUD.
	 *
	 * @param ates_tipoEstadoSolicitud objeto a insertar o a modificar
	 * @param ab_insert indica si se va a modificar o insertar dependiendo su valor(true inserta ,false modifica)
	 * @param as_idUsuario es el usuario que realiza la modificación o la inserción
	 * @param as_remoteIp es la ip de donde se ha realizado la acción de modificar o insertar
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public synchronized void insertUpdateTiposEstadoSolicitud(
	    TipoEstadoSolicitud ates_tipoEstadoSolicitud, boolean ab_insert, String as_idUsuario, String as_remoteIp
	)
	    throws B2BException
	{
		DAOManager ldm_manager;
		ldm_manager = DaoManagerFactory.getDAOManager();

		try
		{
			TipoEstadoSolicitudDAO ltt_DAO;
			ltt_DAO = DaoCreator.getTipoEstadoSolicitudDAO(ldm_manager);

			if(ates_tipoEstadoSolicitud == null)
				throw new B2BException(ErrorKeys.ERROR_INTERNO_SISTEMA);

			if(ab_insert)
			{
				ates_tipoEstadoSolicitud.setIdUsuarioCreacion(as_idUsuario);
				ates_tipoEstadoSolicitud.setIpCreacion(as_remoteIp);

				ates_tipoEstadoSolicitud.setIdUsuarioCreacion(as_idUsuario);
				ltt_DAO.insertOrUpdate(ates_tipoEstadoSolicitud, true);
			}
			else
			{
				ates_tipoEstadoSolicitud.setIdUsuarioModificacion(as_idUsuario);
				ates_tipoEstadoSolicitud.setIpModificacion(as_remoteIp);

				ltt_DAO.insertOrUpdate(ates_tipoEstadoSolicitud, false);
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("insertUpdateTiposEstadoSolicitud", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}
	}

	/**
	 * Método de transacciones con la base de datos cone el fin de insertar o modificar en la base de datos para la tabla SDB_PGN_TIPO_TESTAMENTO.
	 *
	 * @param att_tipoTestamento objeto a insertar o modificar
	 * @param ab_insert indica si se va a modificar o insertar dependiendo su valor(true inserta ,false modifica)
	 * @param as_idUsuario es el usuario que realiza la modificación o la inserción
	 * @param as_remoteIp es la ip de donde se ha realizado la acción de modificar o insertar
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public synchronized void insertUpdateTiposTestamento(
	    TipoTestamento att_tipoTestamento, boolean ab_insert, String as_idUsuario, String as_remoteIp
	)
	    throws B2BException
	{
		DAOManager ldm_manager;
		ldm_manager = DaoManagerFactory.getDAOManager();

		try
		{
			TipoTestamentoDAO ltt_DAO;
			ltt_DAO = DaoCreator.getTipoTestamentoDAO(ldm_manager);

			if(att_tipoTestamento == null)
				throw new B2BException(ErrorKeys.ERROR_INTERNO_SISTEMA);

			att_tipoTestamento.setIdUsuarioCreacion(as_idUsuario);
			att_tipoTestamento.setIpCreacion(as_remoteIp);
			att_tipoTestamento.setIdUsuarioModificacion(as_idUsuario);
			att_tipoTestamento.setIpModificacion(as_remoteIp);

			if(ab_insert)
			{
				att_tipoTestamento.setIdUsuarioCreacion(as_idUsuario);
				ltt_DAO.insertOrUpdate(att_tipoTestamento, true);
			}
			else
				ltt_DAO.insertOrUpdate(att_tipoTestamento, false);
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("insertUpdateTiposTestamento", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}
	}

	/**
	 * Método de transacciones con la base de datos cone el fin de insertar o modificar en la base de datos para la tabla SDB_AUT_USUARIO_CIRCULO.
	 *
	 * @param auc_usuarioCirculo objeto a insertar o modificar
	 * @param ab_insert indica si se va a modificar o insertar dependiendo su valor(true inserta ,false modifica)
	 * @param as_idUsuario es el usuario que realiza la modificación o la inserción
	 * @param as_remoteIp es la ip de donde se ha realizado la acción de modificar o insertar
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public synchronized void insertUpdateUsuarioCirculo(
	    UsuarioCirculo auc_usuarioCirculo, boolean ab_insert, String as_idUsuario, String as_remoteIp
	)
	    throws B2BException
	{
		DAOManager ldm_manager;
		ldm_manager = DaoManagerFactory.getDAOManager();

		try
		{
			UsuarioCirculoDAO luc_DAO;
			luc_DAO = DaoCreator.getUsuarioCirculoDAO(ldm_manager);

			if(auc_usuarioCirculo == null)
				throw new B2BException(ErrorKeys.ERROR_INTERNO_SISTEMA);

			if(ab_insert)
			{
				UsuarioCirculo luc_temp;
				int            li_cantidadRegistros;

				li_cantidadRegistros     = 0;
				luc_temp                 = new UsuarioCirculo();

				luc_temp = luc_DAO.findById(auc_usuarioCirculo);

				if(luc_temp != null)
				{
					Object[] loa_messageArgs = new String[1];
					loa_messageArgs[0] = auc_usuarioCirculo.getUsuario().getIdUsuario();

					throw new B2BException(ErrorKeys.ERROR_ID_USUARIO_CIRCULO_REPETIDO, loa_messageArgs);
				}

				auc_usuarioCirculo.setIdUsuarioCreacion(as_idUsuario);
				auc_usuarioCirculo.setIpCreacion(as_remoteIp);

				li_cantidadRegistros = luc_DAO.contadorRegistro(auc_usuarioCirculo);

				if(li_cantidadRegistros > 0)
					throw new B2BException(ErrorKeys.ERROR_CIRCULO_ASIGNADO);
				else
					luc_DAO.insertOrUpdate(auc_usuarioCirculo, true);
			}
			else
			{
				auc_usuarioCirculo.setIdUsuarioModificacion(as_idUsuario);
				auc_usuarioCirculo.setIpModificacion(as_remoteIp);

				luc_DAO.insertOrUpdate(auc_usuarioCirculo, false);
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("insertUpdateUsuarioCirculo", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}
	}

	/**
	 * Método de transacciones con la base de datos cone el fin de insertar o modificar en la base de datos para la tabla SDB_PGN_ETAPA.
	 *
	 * @param aue_usuarioEtapa objeto a insertar o modificar
	 * @param ab_insert indica si se va a modificar o insertar dependiendo su valor(true inserta ,false modifica)
	 * @param as_idUsuario es el usuario que realiza la modificación o la inserción
	 * @param as_remoteIp es la ip de donde se ha realizado la acción de modificar o insertar
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public synchronized void insertUpdateUsuarioEtapa(
	    UsuarioEtapa aue_usuarioEtapa, boolean ab_insert, String as_idUsuario, String as_remoteIp
	)
	    throws B2BException
	{
		DAOManager ldm_manager;
		ldm_manager = DaoManagerFactory.getDAOManager();

		try
		{
			UsuarioEtapaDAO lue_DAO;
			lue_DAO = DaoCreator.getUsuarioEtapaDAO(ldm_manager);

			if(aue_usuarioEtapa == null)
				throw new B2BException(ErrorKeys.ERROR_INTERNO_SISTEMA);

			if(ab_insert)
			{
				UsuarioEtapa lue_temp;
				lue_temp     = new UsuarioEtapa();

				lue_temp = lue_DAO.findById(aue_usuarioEtapa);

				if(lue_temp != null)
				{
					Object[] loa_messageArgs = new String[1];
					loa_messageArgs[0] = aue_usuarioEtapa.getUsuario().getIdUsuario();

					throw new B2BException(ErrorKeys.ERROR_ID_USUARIO_ETAPA_REPETIDO, loa_messageArgs);
				}

				aue_usuarioEtapa.setIdUsuarioCreacion(as_idUsuario);
				aue_usuarioEtapa.setIpCreacion(as_remoteIp);

				lue_DAO.insertOrUpdate(aue_usuarioEtapa, true);
			}
			else
			{
				aue_usuarioEtapa.setIdUsuarioModificacion(as_idUsuario);
				aue_usuarioEtapa.setIpModificacion(as_remoteIp);

				lue_DAO.insertOrUpdate(aue_usuarioEtapa, false);
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("insertUpdateUsuarioEtapa", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}
	}

	/**
	 * Método de transacciones con la base de datos cone el fin de insertar o modificar en la base de datos para la tabla SDB_PGN_USUARIO_LINEA.
	 *
	 * @param aul_usuarioLinea objeto a insertar o modificar
	 * @param ab_insert indica si se va a modificar o insertar dependiendo su valor(true inserta ,false modifica)
	 * @param as_idUsuario es el usuario que realiza la modificación o la inserción
	 * @param as_remoteIp es la ip de donde se ha realizado la acción de modificar o insertar
	 * @return devuelve el valor de String
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see String
	 */
	public synchronized String insertUpdateUsuarioLinea(
	    UsuarioLinea aul_usuarioLinea, boolean ab_insert, String as_idUsuario, String as_remoteIp
	)
	    throws B2BException
	{
		DAOManager ldm_manager;
		String     ls_return;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		ls_return       = null;

		try
		{
			UsuarioLineaDAO lul_DAO;
			lul_DAO = DaoCreator.getUsuarioLineaDAO(ldm_manager);

			if(aul_usuarioLinea == null)
				throw new B2BException(ErrorKeys.ERROR_INTERNO_SISTEMA);

			if(ab_insert)
			{
				UsuarioLinea lul_temp;
				lul_temp     = new UsuarioLinea();

				lul_temp = lul_DAO.findById(aul_usuarioLinea);

				if(lul_temp != null)
					ls_return = aul_usuarioLinea.getLineaProduccion().getNombre();
				else
				{
					aul_usuarioLinea.setIdUsuarioCreacion(as_idUsuario);
					aul_usuarioLinea.setIpCreacion(as_remoteIp);

					lul_DAO.insertOrUpdate(aul_usuarioLinea, true);
				}
			}
			else
			{
				aul_usuarioLinea.setIdUsuarioModificacion(as_idUsuario);
				aul_usuarioLinea.setIpModificacion(as_remoteIp);

				lul_DAO.insertOrUpdate(aul_usuarioLinea, false);
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("insertUpdateUsuarioLinea", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return ls_return;
	}

	/**
	 * Método para la transaccion con la base de datos con el fin de hacer inserciones o modificaciones de registros
	 * en la tabla SDB_ACC_ENTIDAD_EXTERNA.
	 *
	 * @param aaee_parametros correspondiente al valor del tipo de objeto Gobernacion
	 * @param ab_accion correspondiente al valor del tipo de objeto boolean
	 * @param as_usuario es el usuario que realiza la modificación o la inserción
	 * @param as_ipRemota es la ip de donde se ha realizado la acción de modificar o insertar
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public synchronized void salvarAccEntidadExterna(
	    AccEntidadExterna aaee_parametros, boolean ab_accion, String as_usuario, String as_ipRemota
	)
	    throws B2BException
	{
		DAOManager ldm_manager;
		ldm_manager = DaoManagerFactory.getDAOManager();

		try
		{
			if(aaee_parametros != null)
			{
				if(ab_accion)
				{
					aaee_parametros.setIdUsuarioCreacion(as_usuario);
					aaee_parametros.setIpCreacion(as_ipRemota);

					DaoCreator.getAccEntidadExternaDAO(ldm_manager).insert(aaee_parametros);
				}
				else
				{
					aaee_parametros.setIdUsuarioModificacion(as_usuario);
					aaee_parametros.setIpModificacion(as_ipRemota);

					DaoCreator.getAccEntidadExternaDAO(ldm_manager).update(aaee_parametros);
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("salvarAccEntidadExterna", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}
	}

	/**
	 * Método para la transaccion con la base de datos con el fin de hacer inserciones o modificaciones de registros
	 * en la tabla SDB_ACC_ENTIDAD_EXTERNA_CONVENIO.
	 *
	 * @param aaeec_parametros correspondiente al valor del tipo de objeto AccEntidadExternaConvenio
	 * @param lczr_zonaRegistral correspondiente al valor del tipo de objeto Collection de AccEntidadExternaConvenio
	 * @param ab_accion correspondiente al valor del tipo de objeto boolean
	 * @param as_usuario es el usuario que realiza la modificación o la inserción
	 * @param as_ipRemota es la ip de donde se ha realizado la acción de modificar o insertar
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public synchronized void salvarAccEntidadExternaConvenio(
	    AccEntidadExternaConvenio aaeec_parametros, Collection<ZonaRegistral> lczr_zonaRegistral, boolean ab_accion,
	    String as_usuario, String as_ipRemota
	)
	    throws B2BException
	{
		DAOManager                     ldm_manager;
		AccEntidadExternaConvenioDAO   laeecd_entidadExternaConvenioDAO;
		AccConvenioZonaRegistralDAO    lczrd_convenioZonaRegistralDAO;
		AccConvenioCirculoRegistralDAO lccrd_convenioCirculoRegistralDAO;

		ldm_manager                           = DaoManagerFactory.getDAOManager();
		laeecd_entidadExternaConvenioDAO      = DaoCreator.getAccEntidadExternaConvenioDAO(ldm_manager);
		lczrd_convenioZonaRegistralDAO        = DaoCreator.getAccConvenioZonaRegistralDAO(ldm_manager);
		lccrd_convenioCirculoRegistralDAO     = DaoCreator.getAccConvenioCirculoRegistralDAO(ldm_manager);

		try
		{
			if((aaeec_parametros != null))
			{
				aaeec_parametros.setIdUsuarioCreacion(as_usuario);
				aaeec_parametros.setIpCreacion(as_ipRemota);
				aaeec_parametros.setIdUsuarioModificacion(as_usuario);
				aaeec_parametros.setIpModificacion(as_ipRemota);

				if(ab_accion)
				{
					AccEntidadExternaConvenio laeec_entidadExternaConvenioExistente;

					laeec_entidadExternaConvenioExistente = laeecd_entidadExternaConvenioDAO.findByConvenioNumero(
						    aaeec_parametros.getNumeroConvenio()
						);

					if(laeec_entidadExternaConvenioExistente != null)
						throw new B2BException(ErrorKeys.ERROR_NUMERO_CONVENIO_REPETIDO);

					DaoCreator.getAccEntidadExternaConvenioDAO(ldm_manager).insert(aaeec_parametros);

					if(CollectionUtils.isValidCollection(lczr_zonaRegistral))
					{
						for(ZonaRegistral lc_temp : lczr_zonaRegistral)
						{
							if(lc_temp != null)
							{
								aaeec_parametros.setIdZonaRegistral(lc_temp.getIdZonaRegistral());
								aaeec_parametros.setIdCirculo(lc_temp.getIdCirculo());
								aaeec_parametros.setActivo(EstadoCommon.S);

								lczrd_convenioZonaRegistralDAO.insert(aaeec_parametros);
								lccrd_convenioCirculoRegistralDAO.insert(aaeec_parametros);
							}
						}
					}
				}
				else
				{
					DaoCreator.getAccEntidadExternaConvenioDAO(ldm_manager).update(aaeec_parametros);

					if(CollectionUtils.isValidCollection(lczr_zonaRegistral))
					{
						lczrd_convenioZonaRegistralDAO.updateInactivo(aaeec_parametros);
						lccrd_convenioCirculoRegistralDAO.updateInactivo(aaeec_parametros);

						for(ZonaRegistral lzr_temp : lczr_zonaRegistral)
						{
							if(lzr_temp != null)
							{
								aaeec_parametros.setIdZonaRegistral(lzr_temp.getIdZonaRegistral());
								aaeec_parametros.setIdCirculo(lzr_temp.getIdCirculo());

								AccConvenioZonaRegistral                laczr_zonaRegistral;
								AccConvenioCirculoRegistral             laccr_circuloRegistral;
								Collection<AccConvenioZonaRegistral>    lcaczr_czr;
								Collection<AccConvenioCirculoRegistral> lcaccr_ccr;

								laczr_zonaRegistral        = new AccConvenioZonaRegistral();
								laccr_circuloRegistral     = new AccConvenioCirculoRegistral();

								laczr_zonaRegistral.setIdZonaRegistral(lzr_temp.getIdZonaRegistral());
								laczr_zonaRegistral.setNumeroConvenio(aaeec_parametros.getNumeroConvenio());
								laccr_circuloRegistral.setIdCirculoRegistral(lzr_temp.getIdCirculo());
								laccr_circuloRegistral.setNumeroConvenio(aaeec_parametros.getNumeroConvenio());

								lcaczr_czr     = lczrd_convenioZonaRegistralDAO.findByIdZonaNumero(laczr_zonaRegistral);
								lcaccr_ccr     = lccrd_convenioCirculoRegistralDAO.findById(laccr_circuloRegistral);

								if(CollectionUtils.isValidCollection(lcaczr_czr))
									lczrd_convenioZonaRegistralDAO.updateActivo(aaeec_parametros);
								else
								{
									aaeec_parametros.setActivo(EstadoCommon.S);
									lczrd_convenioZonaRegistralDAO.insert(aaeec_parametros);
								}

								if(CollectionUtils.isValidCollection(lcaccr_ccr))
									lccrd_convenioCirculoRegistralDAO.updateActivo(aaeec_parametros);
								else
								{
									aaeec_parametros.setActivo(EstadoCommon.S);
									lccrd_convenioCirculoRegistralDAO.insert(aaeec_parametros);
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

			clh_LOGGER.error("salvarAccEntidadExternaConvenio", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}
	}

	/**
	 * Método para la transaccion con la base de datos con el fin de hacer inserciones o modificaciones de registros
	 * en la tabla SDB_PGN_ACTIVIDAD_ECONOMICA.
	 *
	 * @param aae_parametros correspondiente al valor del tipo de objeto ActividadEconomica
	 * @param ab_accion correspondiente al valor del tipo de objeto boolean
	 * @param as_usuario es el usuario que realiza la modificación o la inserción
	 * @param as_ipRemota es la ip de donde se ha realizado la acción de modificar o insertar
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public synchronized void salvarActividadEconomica(
	    ActividadEconomica aae_parametros, boolean ab_accion, String as_usuario, String as_ipRemota
	)
	    throws B2BException
	{
		DAOManager ldm_manager;

		ldm_manager = DaoManagerFactory.getDAOManager();

		try
		{
			if(aae_parametros != null)
			{
				if(ab_accion)
				{
					ActividadEconomica lae_actividadEconomica;

					lae_actividadEconomica = DaoCreator.getActividadEconomicaDAO(ldm_manager)
							                               .findById(aae_parametros.getIdActividadEconomica());

					if(lae_actividadEconomica != null)
						throw new B2BException(ErrorKeys.ERROR_ACTIVIDAD_ECONOMICA_REPETIDO);
					else
					{
						aae_parametros.setIdUsuarioCreacion(as_usuario);
						aae_parametros.setIpCreacion(as_ipRemota);

						DaoCreator.getActividadEconomicaDAO(ldm_manager).insert(aae_parametros);
					}
				}
				else
				{
					aae_parametros.setIdUsuarioModificacion(as_usuario);
					aae_parametros.setIpModificacion(as_ipRemota);

					DaoCreator.getActividadEconomicaDAO(ldm_manager).update(aae_parametros);
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("salvarActividadEconomica", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}
	}

	/**
	 * Método para salvar la insercion o modificacion para la tabla
	 * SDB_PGN_TAG_PLANTILLA_RESOLUCION.
	 *
	 * @param aaa_parametros            objeto que se desea salvar
	 * @param ab_accion            indica si se va a insertar o modificar
	 * @param as_userId            usuario en sesion que realiza alguna accion(modificar o insertar)
	 * @param as_remoteIp            ip del usuario en sesion
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public synchronized void salvarActuacionesAdministrativas(
	    TagPlantillaResolucion aaa_parametros, boolean ab_accion, String as_userId, String as_remoteIp
	)
	    throws B2BException
	{
		DAOManager ldm_manager;

		ldm_manager = DaoManagerFactory.getDAOManager();

		try
		{
			if(aaa_parametros == null)
				throw new B2BException(ErrorKeys.ERROR_PARAMETROS_NO_VALIDOS);

			{
				String ls_idPlantilla;

				ls_idPlantilla = aaa_parametros.getIdPlantilla();

				if(!StringUtils.isValidString(ls_idPlantilla))
					throw new B2BException(ErrorKeys.DEBE_DIGITAR_ID_PLANTILLA);
			}

			{
				String ls_texto;

				ls_texto = aaa_parametros.getTexto();

				if(!StringUtils.isValidString(ls_texto))
					throw new B2BException(ErrorKeys.DEBE_DIGITAR_TEXTO);
			}

			{
				String ls_tag;

				ls_tag = aaa_parametros.getTag();

				if(!StringUtils.isValidString(ls_tag))
					throw new B2BException(ErrorKeys.DEBE_DIGITAR_TAG);
			}

			if(ab_accion)
			{
				aaa_parametros.setIdUsuarioCreacion(as_userId);
				aaa_parametros.setIpCreacion(as_remoteIp);
			}
			else
			{
				aaa_parametros.setIdUsuarioModificacion(as_userId);
				aaa_parametros.setIpModificacion(as_remoteIp);
			}

			{
				TagPlantillaResolucionDAO laad_actuacionesAdministrativasDAO;

				laad_actuacionesAdministrativasDAO = DaoCreator.getTagPlantillaResolucionDAO(ldm_manager);

				if(ab_accion)
				{
					TagPlantillaResolucion iaa_datos;

					iaa_datos = laad_actuacionesAdministrativasDAO.findById(
						    aaa_parametros.getIdTagPlantillaResolucion()
						);

					if(iaa_datos != null)
						throw new B2BException(ErrorKeys.ERROR_ACTUACIONES_ADMINISTRATIVAS);

					laad_actuacionesAdministrativasDAO.insertOrUpdate(aaa_parametros, ab_accion);
				}
				else
					laad_actuacionesAdministrativasDAO.insertOrUpdate(aaa_parametros, ab_accion);
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("salvarActuacionesAdministrativas", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}
	}

	/**
	 * Método para salvar lo realizado ya sea una inserción o modificación de Administracion Componentes.
	 *
	 * @param aac_parametros            objeto a insertar o modificar
	 * @param ab_accion            indica si se desea insertar o modificar
	 * @param as_usuario            usuario que realiza la acción de salvar
	 * @param as_ip            ip de donde se invoca el metodo de salvar
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public synchronized void salvarAdministracionComponentes(
	    Componente aac_parametros, boolean ab_accion, String as_usuario, String as_ip
	)
	    throws B2BException
	{
		DAOManager     ldm_manager;
		ComponentesDAO lc_componenteDAO;

		ldm_manager          = DaoManagerFactory.getDAOManager();
		lc_componenteDAO     = DaoCreator.getComponentesDAO(ldm_manager);

		try
		{
			if(aac_parametros != null)
			{
				{
					String ls_nombre;

					ls_nombre = aac_parametros.getNombre();

					if(!StringUtils.isValidString(ls_nombre))
						throw new B2BException(ErrorKeys.DEBE_DIGITAR_NOMBRE);
				}

				{
					Date ls_fechaDesde;

					ls_fechaDesde = aac_parametros.getFechaDesde();

					if(!(ls_fechaDesde != null))
						throw new B2BException(ErrorKeys.ERROR_SIN_FECHA_DESDE);
				}

				{
					String ls_activo;

					ls_activo = aac_parametros.getActivo();

					if(!StringUtils.isValidString(ls_activo))
						throw new B2BException(ErrorKeys.DEBE_DIGITAR_ACTIVO);
				}

				if(ab_accion)
				{
					aac_parametros.setIdUsuarioCreacion(as_usuario);
					aac_parametros.setIpCreacion(as_ip);

					DaoCreator.getComponentesDAO(ldm_manager).insert(aac_parametros);
				}
				else
				{
					String ls_idComponente;

					ls_idComponente = aac_parametros.getIdComponente();

					if(StringUtils.isValidString(ls_idComponente))
					{
						Componente lc_componente;

						lc_componente = lc_componenteDAO.findById(ls_idComponente);

						if(lc_componente != null)
							aac_parametros.setImagen(lc_componente.getImagen());

						aac_parametros.setIdUsuarioModificacion(as_usuario);
						aac_parametros.setIpModificacion(as_ip);

						DaoCreator.getComponentesDAO(ldm_manager).update(aac_parametros);
					}
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("salvarAdministracionComponentes", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}
	}

	/**
	 * Salvar alerta naturaleza juridica.
	 *
	 * @param aanj_parametros correspondiente al valor del tipo de objeto AlertaNaturalezaJuridica
	 * @param ab_insert correspondiente al valor del tipo de objeto boolean
	 * @param as_userId correspondiente al valor del tipo de objeto String
	 * @param as_remoteIp correspondiente al valor del tipo de objeto String
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public synchronized void salvarAlertaNaturalezaJuridica(
	    AlertaNaturalezaJuridica aanj_parametros, boolean ab_insert, String as_userId, String as_remoteIp
	)
	    throws B2BException
	{
		DAOManager ldm_manager;
		ldm_manager = DaoManagerFactory.getDAOManager();

		try
		{
			AlertaNaturalezaJuridicaDAO lanj_DAO;

			lanj_DAO = DaoCreator.getAlertaNaturalezaJuridicaDAO(ldm_manager);

			if(aanj_parametros != null)
			{
				aanj_parametros.setIdUsuarioCreacion(as_userId);
				aanj_parametros.setIpCreacion(as_remoteIp);
				aanj_parametros.setIdUsuarioModificacion(as_userId);
				aanj_parametros.setIpModificacion(as_remoteIp);

				if(ab_insert)
				{
					AlertaNaturalezaJuridica lanj_tmp;
					Long                     ll_version;
					String                   ls_id;

					ll_version     = aanj_parametros.getVersion();
					ls_id          = aanj_parametros.getIdNaturalezaJuridica();

					lanj_tmp = lanj_DAO.findById(ls_id, NumericUtils.getLong(ll_version));

					if(lanj_tmp != null)
						throw new B2BException(ErrorKeys.ERROR_ID_DOMINIO_NATURALEZA_JURIDICA_REPETIDO);

					DaoCreator.getAlertaNaturalezaJuridicaDAO(ldm_manager).insertOrUpdate(aanj_parametros, ab_insert);
				}
				else
					DaoCreator.getAlertaNaturalezaJuridicaDAO(ldm_manager).insertOrUpdate(aanj_parametros, ab_insert);
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("salvarAlertaNaturalezaJuridica", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}
	}

	/**
	 * Método para salvar la insercion o modificacion para la tabla SDB_PGN_CAUSAL_APROBACION_DEVOLUCION.
	 *
	 * @param aat_parametros objeto que se desea salvar
	 * @param ab_accion indica si se va a insertar o modificar
	 * @param as_userId usuario en sesion que realiza alguna accion(modificar o insertar)
	 * @param as_remoteIp ip del usuario en sesion
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public synchronized void salvarAlertaTramite(
	    AlertaTramite aat_parametros, boolean ab_accion, String as_userId, String as_remoteIp
	)
	    throws B2BException
	{
		DAOManager ldm_manager;
		ldm_manager = DaoManagerFactory.getDAOManager();

		try
		{
			if(aat_parametros != null)
			{
				if(ab_accion)
				{
					aat_parametros.setIdUsuarioCreacion(as_userId);
					aat_parametros.setIpCreacion(as_remoteIp);
				}
				else
				{
					aat_parametros.setIdUsuarioModificacion(as_userId);
					aat_parametros.setIpModificacion(as_remoteIp);
				}

				AlertaTramite icad_datos;
				icad_datos = null;

				if(ab_accion)
				{
					icad_datos = DaoCreator.getAlertaTramiteDAO(ldm_manager).findById(aat_parametros);

					if(icad_datos == null)
						DaoCreator.getAlertaTramiteDAO(ldm_manager).insertOrUpdate(aat_parametros, ab_accion);
					else
						throw new B2BException(ErrorKeys.ERROR_ALERTA_TRAMITE_REPETIDO);
				}

				else
					DaoCreator.getAlertaTramiteDAO(ldm_manager).insertOrUpdate(aat_parametros, ab_accion);
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("salvarAlertaTramite", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}
	}

	/**
	 * Método para salvar la insercion o modificacion para la tabla SDB_ACC_CALIDAD_SOLICITANTE.
	 *
	 * @param acs_parametros objeto que se desea salvar
	 * @param ab_accion indica si se va a insertar o modificar
	 * @param as_userId usuario en sesion que realiza alguna accion(modificar o insertar)
	 * @param as_remoteIp ip del usuario en sesion
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public synchronized void salvarCalidadSolicitante(
	    CalidadSolicitante acs_parametros, boolean ab_accion, String as_userId, String as_remoteIp
	)
	    throws B2BException
	{
		DAOManager ldm_manager;
		ldm_manager = DaoManagerFactory.getDAOManager();

		try
		{
			if(acs_parametros != null)
			{
				if(ab_accion)
				{
					acs_parametros.setIdUsuarioCreacion(as_userId);
					acs_parametros.setIpCreacion(as_remoteIp);
				}
				else
				{
					acs_parametros.setIdUsuarioModificacion(as_userId);
					acs_parametros.setIpModificacion(as_remoteIp);
				}

				CalidadSolicitante ics_datos;
				ics_datos = null;

				if(ab_accion)
				{
					ics_datos = DaoCreator.getCalidadSolicitanteDAO(ldm_manager).findById(acs_parametros);

					if(ics_datos == null)
						DaoCreator.getCalidadSolicitanteDAO(ldm_manager).insertOrUpdate(acs_parametros, ab_accion);
					else
						throw new B2BException(ErrorKeys.ERROR_CALIDAD_SOLICITANTE_REPETIDO);
				}

				else
					DaoCreator.getCalidadSolicitanteDAO(ldm_manager).insertOrUpdate(acs_parametros, ab_accion);
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("salvarCalidadSolicitante", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}
	}

	/**
	 * Método para salvar lo realizado ya sea una inserción o modificación de Campo Critereio Busqueda.
	 *
	 * @param acc_parametros            objeto a insertar o modificar
	 * @param ab_accion            indica si se desea insertar o modificar
	 * @param as_usuario            usuario que realiza la acción de salvar
	 * @param as_ip            ip de donde se invoca el metodo de salvar
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public synchronized void salvarCampoCriterioBusqueda(
	    CamposConsulta acc_parametros, boolean ab_accion, String as_usuario, String as_ip
	)
	    throws B2BException
	{
		DAOManager ldm_manager;
		ldm_manager = DaoManagerFactory.getDAOManager();

		try
		{
			if(acc_parametros != null)
			{
				{
					String ls_idTipoCriterioBusqueda;

					ls_idTipoCriterioBusqueda = acc_parametros.getIdTipoCriterioBusqueda();

					if(!StringUtils.isValidString(ls_idTipoCriterioBusqueda))
						throw new B2BException(ErrorKeys.ERROR_SELECCIONE_TIPO_CRITERIO_BUSQUEDA);
				}

				{
					String ls_idCampoCriterioBusqueda;

					ls_idCampoCriterioBusqueda = acc_parametros.getIdCampoCriterioBusqueda();

					if(!StringUtils.isValidString(ls_idCampoCriterioBusqueda))
						throw new B2BException(ErrorKeys.ERROR_SIN_CAMPO_CRITERIO_BUSQUEDA);
				}

				{
					String ls_nombreCampo;

					ls_nombreCampo = acc_parametros.getNombreCampo();

					if(!StringUtils.isValidString(ls_nombreCampo))
						throw new B2BException(ErrorKeys.ERROR_SIN_NOMBRE_CAMPO);
				}

				{
					String ls_etiquetaCampo;

					ls_etiquetaCampo = acc_parametros.getEtiquetaCampo();

					if(!StringUtils.isValidString(ls_etiquetaCampo))
						throw new B2BException(ErrorKeys.ERROR_SIN_ETIQUETA_CAMPO);
				}

				{
					String ls_obligatorio;

					ls_obligatorio = acc_parametros.getObligatorio();

					if(!StringUtils.isValidString(ls_obligatorio))
						throw new B2BException(ErrorKeys.ERROR_SELECCIONAR_OBLIGATORIO);
				}

				{
					String ls_activo;

					ls_activo = acc_parametros.getActivo();

					if(!StringUtils.isValidString(ls_activo))
						throw new B2BException(ErrorKeys.ERROR_SIN_ACTIVO);
				}

				if(ab_accion)
				{
					acc_parametros.setIdUsuarioCreacion(as_usuario);
					acc_parametros.setIpCreacion(as_ip);

					DaoCreator.getCampoCriterioBusquedaDAO(ldm_manager).insert(acc_parametros);
				}
				else
				{
					acc_parametros.setIdUsuarioModificacion(as_usuario);
					acc_parametros.setIpModificacion(as_ip);

					DaoCreator.getCampoCriterioBusquedaDAO(ldm_manager).update(acc_parametros);
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("salvarCampoCriterioBusqueda", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}
	}

	/**
	 * Método para la transaccion con la base de datos con el fin de hacer inserciones o modificaciones de registros
	 * en la tabla SDB_PGN_CAMPOS_CERTIFICADO.
	 *
	 * @param acc_parametros correspondiente al valor del tipo de objeto CamposCertificado
	 * @param ab_accion correspondiente al valor del tipo de objeto boolean
	 * @param as_usuario es el usuario que realiza la modificación o la inserción
	 * @param as_ipRemota es la ip de donde se ha realizado la acción de modificar o insertar
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public synchronized void salvarCamposCertificado(
	    CamposCertificado acc_parametros, boolean ab_accion, String as_usuario, String as_ipRemota
	)
	    throws B2BException
	{
		DAOManager ldm_manager;

		ldm_manager = DaoManagerFactory.getDAOManager();

		try
		{
			if(acc_parametros != null)
			{
				if(ab_accion)
				{
					acc_parametros.setIdUsuarioCreacion(as_usuario);
					acc_parametros.setIpCreacion(as_ipRemota);

					DaoCreator.getCamposCertificadoDAO(ldm_manager).insert(acc_parametros);
				}
				else
				{
					acc_parametros.setIdUsuarioModificacion(as_usuario);
					acc_parametros.setIpModificacion(as_ipRemota);

					DaoCreator.getCamposCertificadoDAO(ldm_manager).update(acc_parametros);
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("salvarCamposCertificado", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}
	}

	/**
	 * Método para salvar la insercion o modificacion para la tabla SDB_PGN_CAMPOS_CONSULTA.
	 *
	 * @param acc_parametros objeto que se desea salvar
	 * @param ab_accion indica si se va a insertar o modificar
	 * @param as_userId usuario en sesion que realiza alguna accion(modificar o insertar)
	 * @param as_remoteIp ip del usuario en sesion
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public synchronized void salvarCamposConsulta(
	    CamposConsulta acc_parametros, boolean ab_accion, String as_userId, String as_remoteIp
	)
	    throws B2BException
	{
		DAOManager ldm_manager;
		ldm_manager = DaoManagerFactory.getDAOManager();

		try
		{
			if(acc_parametros != null)
			{
				acc_parametros.setIdUsuarioCreacion(as_userId);
				acc_parametros.setIpCreacion(as_remoteIp);
				acc_parametros.setIdUsuarioModificacion(as_userId);
				acc_parametros.setIpModificacion(as_remoteIp);

				if(ab_accion)
				{
					CamposConsulta icc_tmp;

					icc_tmp = DaoCreator.getCamposConsultaDAO(ldm_manager).findById(acc_parametros);

					if(icc_tmp == null)
						DaoCreator.getCamposConsultaDAO(ldm_manager).insertOrUpdate(acc_parametros, ab_accion);
					else
						throw new B2BException(ErrorKeys.ERROR_ID_CAUSAL_APROBACION_DEVOLUCION);
				}
				else
					DaoCreator.getCamposConsultaDAO(ldm_manager).insertOrUpdate(acc_parametros, ab_accion);
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("salvarCamposConsulta", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}
	}

	/**
	 * Método para la transaccion con la base de datos con el fin de hacer inserciones o modificaciones de registros
	 * en la tabla SDB_PGN_CAMPOS_CORRECCION.
	 *
	 * @param acc_parametros correspondiente al valor del tipo de objeto CamposCorreccion
	 * @param ab_accion correspondiente al valor del tipo de objeto boolean
	 * @param as_usuario es el usuario que realiza la modificación o la inserción
	 * @param as_ipRemota es la ip de donde se ha realizado la acción de modificar o insertar
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public synchronized void salvarCamposCorreccion(
	    CamposCorreccion acc_parametros, boolean ab_accion, String as_usuario, String as_ipRemota
	)
	    throws B2BException
	{
		DAOManager ldm_manager;

		ldm_manager = DaoManagerFactory.getDAOManager();

		try
		{
			if(acc_parametros != null)
			{
				if(ab_accion)
				{
					acc_parametros.setIdUsuarioCreacion(as_usuario);
					acc_parametros.setIpCreacion(as_ipRemota);

					{
						CamposCorreccion lcc_camposCorreccion;

						lcc_camposCorreccion = DaoCreator.getCamposCorreccionDAO(ldm_manager).findById(acc_parametros);

						if(lcc_camposCorreccion != null)
							throw new B2BException(ErrorKeys.ERROR_CAMPOS_CORRECCION_COMBINACION);
					}

					DaoCreator.getCamposCorreccionDAO(ldm_manager).insert(acc_parametros);
				}
				else
				{
					acc_parametros.setIdUsuarioModificacion(as_usuario);
					acc_parametros.setIpModificacion(as_ipRemota);

					DaoCreator.getCamposCorreccionDAO(ldm_manager).update(acc_parametros);
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("salvarCamposCorreccion", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}
	}

	/**
	 * Método para salvar la insercion o modificacion para la tabla SDB_PGN_CANAL.
	 *
	 * @param ac_parametros objeto que se desea salvar
	 * @param ab_insertar de ab insertar
	 * @param as_userId usuario en sesion que realiza alguna accion(modificar o insertar)
	 * @param as_remoteIp ip del usuario en sesion
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public synchronized void salvarCanal(
	    Canal ac_parametros, boolean ab_insertar, String as_userId, String as_remoteIp
	)
	    throws B2BException
	{
		DAOManager ldm_manager;
		ldm_manager = DaoManagerFactory.getDAOManagerCYN();

		try
		{
			if(ac_parametros != null)
			{
				if(ab_insertar)
				{
					ac_parametros.setIdUsuarioCreacion(as_userId);
					ac_parametros.setIpCreacion(as_remoteIp);

					DaoCreator.getCanalDAO(ldm_manager).insert(ac_parametros);
				}
				else
				{
					ac_parametros.setIdUsuarioModificacion(as_userId);
					ac_parametros.setIpModificacion(as_remoteIp);

					DaoCreator.getCanalDAO(ldm_manager).update(ac_parametros);
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("salvarCanal", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}
	}

	/**
	 * Método para la transaccion con la base de datos con el fin de hacer inserciones o modificaciones de registros
	 * en la tabla SDB_PGN_CATASTRO.
	 *
	 * @param ac_parametros correspondiente al valor del tipo de objeto Catastro
	 * @param ab_accion correspondiente al valor del tipo de objeto boolean
	 * @param as_usuario es el usuario que realiza la modificación o la inserción
	 * @param as_ipRemota es la ip de donde se ha realizado la acción de modificar o insertar
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public synchronized void salvarCatastro(
	    Catastro ac_parametros, boolean ab_accion, String as_usuario, String as_ipRemota
	)
	    throws B2BException
	{
		DAOManager ldm_manager;

		ldm_manager = DaoManagerFactory.getDAOManager();

		try
		{
			if(ac_parametros != null)
			{
				if(ab_accion)
				{
					ac_parametros.setIdUsuarioCreacion(as_usuario);
					ac_parametros.setIpCreacion(as_ipRemota);

					DaoCreator.getCatastroDAO(ldm_manager).insert(ac_parametros);
				}
				else
				{
					ac_parametros.setIdUsuarioModificacion(as_usuario);
					ac_parametros.setIpModificacion(as_ipRemota);

					DaoCreator.getCatastroDAO(ldm_manager).update(ac_parametros);
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("salvarCatastro", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}
	}

	/**
	 * Método para salvar la insercion o modificacion para la tabla SDB_PGN_CAUSAL_APROBACION_DEVOLUCION.
	 *
	 * @param atr_parametros objeto que se desea salvar
	 * @param ab_accion indica si se va a insertar o modificar
	 * @param as_userId usuario en sesion que realiza alguna accion(modificar o insertar)
	 * @param as_remoteIp ip del usuario en sesion
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public synchronized void salvarCausalAprobacionDevolucion(
	    CausalAprobacionDevolucion atr_parametros, boolean ab_accion, String as_userId, String as_remoteIp
	)
	    throws B2BException
	{
		DAOManager ldm_manager;
		ldm_manager = DaoManagerFactory.getDAOManager();

		try
		{
			if(atr_parametros != null)
			{
				if(ab_accion)
				{
					atr_parametros.setIdUsuarioCreacion(as_userId);
					atr_parametros.setIpCreacion(as_remoteIp);
				}
				else
				{
					atr_parametros.setIdUsuarioModificacion(as_userId);
					atr_parametros.setIpModificacion(as_remoteIp);
				}

				CausalAprobacionDevolucion icad_datos;
				icad_datos = null;

				if(ab_accion)
				{
					icad_datos = DaoCreator.getCausalAprobacionDevolucionDAO(ldm_manager).findById(atr_parametros);

					if(icad_datos == null)
						DaoCreator.getCausalAprobacionDevolucionDAO(ldm_manager)
							          .insertOrUpdate(atr_parametros, ab_accion);
					else
						throw new B2BException(ErrorKeys.ERROR_ID_CAUSAL_APROBACION_DEVOLUCION);
				}

				else
					DaoCreator.getCausalAprobacionDevolucionDAO(ldm_manager).insertOrUpdate(atr_parametros, ab_accion);
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("salvarCausalAprobacionDevolucion", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}
	}

	/**
	 * Método para salvar la insercion o modificacion para la tabla SDB_PGN_CAUSAL_CORRECCION.
	 *
	 * @param acc_parametros objeto que se desea salvar
	 * @param ab_accion indica si se va a insertar o modificar
	 * @param as_userId usuario en sesion que realiza alguna accion(modificar o insertar)
	 * @param as_remoteIp ip del usuario en sesion
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public synchronized void salvarCausalCorreccion(
	    CausalCorreccion acc_parametros, boolean ab_accion, String as_userId, String as_remoteIp
	)
	    throws B2BException
	{
		DAOManager ldm_manager;
		ldm_manager = DaoManagerFactory.getDAOManager();

		try
		{
			if(acc_parametros != null)
			{
				acc_parametros.setIdUsuarioCreacion(as_userId);
				acc_parametros.setIpCreacion(as_remoteIp);
				acc_parametros.setIdUsuarioModificacion(as_userId);
				acc_parametros.setIpModificacion(as_remoteIp);

				if(ab_accion)
				{
					CausalCorreccion lcc_tmp;

					lcc_tmp = DaoCreator.getCausalCorreccionDAO(ldm_manager).findById(acc_parametros);

					if(lcc_tmp == null)
						DaoCreator.getCausalCorreccionDAO(ldm_manager).insertOrUpdate(acc_parametros, ab_accion);
					else
						throw new B2BException(ErrorKeys.ERROR_ID_CAUSAL_CORRECCION);
				}

				else
					DaoCreator.getCausalCorreccionDAO(ldm_manager).insertOrUpdate(acc_parametros, ab_accion);
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("salvarCausalCorreccion", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}
	}

	/**
	 * Método de transacción con la base de datos para salvar en inserció o update de los datos de causal mayor valor.
	 * @param acmv_parametros con los parametros a guardar
	 * @param ab_accion con la acción de inserción o actualización
	 * @param as_usuario con el usuario de la transacción
	 * @param as_ip con la ip de la transacción
	 * @throws B2BException en caso de error
	 */
	public synchronized void salvarCausalMayorValor(
	    CausalMayorValor acmv_parametros, boolean ab_accion, String as_usuario, String as_ip
	)
	    throws B2BException
	{
		DAOManager ldm_manager;
		ldm_manager = DaoManagerFactory.getDAOManager();

		try
		{
			if(acmv_parametros != null)
			{
				{
					String ls_nombre;

					ls_nombre = acmv_parametros.getNombre();

					if(!StringUtils.isValidString(ls_nombre))
						throw new B2BException(ErrorKeys.DEBE_DIGITAR_NOMBRE);
				}

				if(ab_accion)
				{
					acmv_parametros.setIdUsuarioCreacion(as_usuario);
					acmv_parametros.setIpCreacion(as_ip);

					DaoCreator.getCausalMayorValorDAO(ldm_manager).insert(acmv_parametros);
				}
				else
				{
					acmv_parametros.setIdUsuarioModificacion(as_usuario);
					acmv_parametros.setIpModificacion(as_ip);

					DaoCreator.getCausalMayorValorDAO(ldm_manager).update(acmv_parametros);
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("salvarCausalMayorValor", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}
	}

	/**
	 * Método para salvar la insercion o modificacion para la tabla SDB_PGN_CAUSAL_NEGACION_COPIAS.
	 *
	 * @param acnc_parametros objeto que se desea salvar
	 * @param ab_accion indica si se va a insertar o modificar
	 * @param as_userId usuario en sesion que realiza alguna accion(modificar o insertar)
	 * @param as_remoteIp ip del usuario en sesion
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public synchronized void salvarCausalNegacionCopias(
	    CausalNegacionCopias acnc_parametros, boolean ab_accion, String as_userId, String as_remoteIp
	)
	    throws B2BException
	{
		DAOManager ldm_manager;
		ldm_manager = DaoManagerFactory.getDAOManager();

		try
		{
			if(acnc_parametros != null)
			{
				if(ab_accion)
				{
					acnc_parametros.setIdUsuarioCreacion(as_userId);
					acnc_parametros.setIpCreacion(as_remoteIp);
				}
				else
				{
					acnc_parametros.setIdUsuarioModificacion(as_userId);
					acnc_parametros.setIpModificacion(as_remoteIp);
				}

				CausalNegacionCopias icad_datos;
				icad_datos = null;

				if(ab_accion)
				{
					icad_datos = DaoCreator.getCausalNegacionCopiasDAO(ldm_manager).findById(acnc_parametros);

					if(icad_datos == null)
						DaoCreator.getCausalNegacionCopiasDAO(ldm_manager).insert(acnc_parametros);
					else
						throw new B2BException(ErrorKeys.ERROR_ID_CAUSAL_NEGACION_COPIAS);
				}

				else
					DaoCreator.getCausalNegacionCopiasDAO(ldm_manager).update(acnc_parametros);
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("salvarCausalNegacionCopias", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}
	}

	/**
	 * Método para salvar la insercion o modificacion para la tabla SDB_PGN_CAUSAL_RECHAZO_RECURSO.
	 *
	 * @param acrr_parametros objeto que se desea salvar
	 * @param ab_accion indica si se va a insertar o modificar
	 * @param as_userId usuario en sesion que realiza alguna accion(modificar o insertar)
	 * @param as_remoteIp ip del usuario en sesion
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public synchronized void salvarCausalRechazoRecurso(
	    CausalRechazoRecurso acrr_parametros, boolean ab_accion, String as_userId, String as_remoteIp
	)
	    throws B2BException
	{
		DAOManager ldm_manager;

		ldm_manager = DaoManagerFactory.getDAOManager();

		try
		{
			if(acrr_parametros != null)
			{
				acrr_parametros.setIdUsuarioCreacion(as_userId);
				acrr_parametros.setIpCreacion(as_remoteIp);
				acrr_parametros.setIdUsuarioModificacion(as_userId);
				acrr_parametros.setIpModificacion(as_remoteIp);

				if(ab_accion)
				{
					String ls_idCausalRechazo;

					ls_idCausalRechazo = acrr_parametros.getIdCausalRechazoRecurso();

					if(StringUtils.isValidString(ls_idCausalRechazo))
					{
						CausalRechazoRecurso lcrr_tmp;

						lcrr_tmp = DaoCreator.getCausalRechazoRecursoDAO(ldm_manager).findById(ls_idCausalRechazo);

						if(lcrr_tmp != null)
							throw new B2BException(ErrorKeys.ERROR_ID_CAUSAL_RECHAZO_RECURSO);
					}
					else
						throw new B2BException(ErrorKeys.ERROR_ID_CAUSAL_RECHAZO_RECURSO);
				}

				DaoCreator.getCausalRechazoRecursoDAO(ldm_manager).insertOrUpdate(acrr_parametros, ab_accion);
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("salvarCausalRechazoRecurso", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}
	}

	/**
	 * Método para salvar la insercion o modificacion para la tabla SDB_PGN_UNIDAD_TIEMPO_VENCIMIENTO.
	 *
	 * @param acr_parametros objeto que se desea salvar
	 * @param ab_accion indica si se va a insertar o modificar
	 * @param as_userId usuario en sesion que realiza alguna accion(modificar o insertar)
	 * @param as_remoteIp ip del usuario en sesion
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public synchronized void salvarCausalReimpresion(
	    CausalReimpresion acr_parametros, boolean ab_accion, String as_userId, String as_remoteIp
	)
	    throws B2BException
	{
		DAOManager ldm_manager;
		ldm_manager = DaoManagerFactory.getDAOManager();

		try
		{
			if(acr_parametros != null)
			{
				if(ab_accion)
				{
					acr_parametros.setIdUsuarioCreacion(as_userId);
					acr_parametros.setIpCreacion(as_remoteIp);
				}
				else
				{
					acr_parametros.setIdUsuarioModificacion(as_userId);
					acr_parametros.setIpModificacion(as_remoteIp);
				}

				CausalReimpresion icr_datos;
				icr_datos = null;

				if(ab_accion)
				{
					icr_datos = DaoCreator.getCausalReimpresionDAO(ldm_manager).findById(acr_parametros);

					if(icr_datos == null)
						DaoCreator.getCausalReimpresionDAO(ldm_manager).insertOrUpdate(acr_parametros, ab_accion);
					else
						throw new B2BException(ErrorKeys.ERROR_CAUSAL_REIMPRESION_REPETIDO);
				}

				else
					DaoCreator.getCausalReimpresionDAO(ldm_manager).insertOrUpdate(acr_parametros, ab_accion);
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("salvarCausalReimpresion", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}
	}

	/**
	 * Método para la transaccion con la base de datos con el fin de hacer inserciones o modificaciones de registros
	 * en la tabla SDB_PGN_CIRCULO_ACT_ADMIN.
	 *
	 * @param acaa_parametros correspondiente al valor del tipo de objeto CirculoActAdmin
	 * @param ab_accion correspondiente al valor del tipo de objeto boolean
	 * @param as_usuario es el usuario que realiza la modificación o la inserción
	 * @param as_ipRemota es la ip de donde se ha realizado la acción de modificar o insertar
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public synchronized void salvarCirculoActAdmin(
	    CirculoActAdmin acaa_parametros, boolean ab_accion, String as_usuario, String as_ipRemota
	)
	    throws B2BException
	{
		DAOManager ldm_manager;

		ldm_manager = DaoManagerFactory.getDAOManager();

		try
		{
			if(acaa_parametros != null)
			{
				if(ab_accion)
				{
					acaa_parametros.setIdUsuarioCreacion(as_usuario);
					acaa_parametros.setIpCreacion(as_ipRemota);
				}
				else
				{
					acaa_parametros.setIdUsuarioModificacion(as_usuario);
					acaa_parametros.setIpModificacion(as_ipRemota);
				}

				DaoCreator.getCirculoActAdminDAO(ldm_manager).insertOrUpdate(acaa_parametros, ab_accion);
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("salvarCirculoActAdmin", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}
	}

	/**
	 * Método para la transaccion con la base de datos con el fin de hacer inserciones o modificaciones de registros
	 * en la tabla SDB_PGN_CIRCULO_CATASTRO.
	 *
	 * @param acc_parametros correspondiente al valor del tipo de objeto CirculoCatastro
	 * @param ab_accion correspondiente al valor del tipo de objeto boolean
	 * @param as_usuario es el usuario que realiza la modificación o la inserción
	 * @param as_ipRemota es la ip de donde se ha realizado la acción de modificar o insertar
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public synchronized void salvarCirculoCatastro(
	    CirculoCatastro acc_parametros, boolean ab_accion, String as_usuario, String as_ipRemota
	)
	    throws B2BException
	{
		DAOManager ldm_manager;

		ldm_manager = DaoManagerFactory.getDAOManager();

		try
		{
			if(acc_parametros != null)
			{
				if(ab_accion)
				{
					acc_parametros.setIdUsuarioCreacion(as_usuario);
					acc_parametros.setIpCreacion(as_ipRemota);

					DaoCreator.getCirculoCatastroDAO(ldm_manager).insert(acc_parametros);
				}
				else
				{
					acc_parametros.setIdUsuarioModificacion(as_usuario);
					acc_parametros.setIpModificacion(as_ipRemota);

					DaoCreator.getCirculoCatastroDAO(ldm_manager).update(acc_parametros);
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("salvarCirculoCatastro", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}
	}

	/**
	 * Salvar circulo festivo.
	 *
	 * @param acf_parametros correspondiente al valor del tipo de objeto CirculoFestivo
	 * @param ab_accion correspondiente al valor del tipo de objeto boolean
	 * @param as_userId correspondiente al valor del tipo de objeto String
	 * @param as_remoteIp correspondiente al valor del tipo de objeto String
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public synchronized void salvarCirculoFestivo(
	    CirculoFestivo acf_parametros, boolean ab_accion, String as_userId, String as_remoteIp
	)
	    throws B2BException
	{
		DAOManager ldm_manager;
		ldm_manager = DaoManagerFactory.getDAOManager();

		try
		{
			if(acf_parametros != null)
			{
				acf_parametros.setIdUsuarioCreacion(as_userId);
				acf_parametros.setIpCreacion(as_remoteIp);
				acf_parametros.setIdUsuarioModificacion(as_userId);
				acf_parametros.setIpModificacion(as_remoteIp);

				if(ab_accion)
				{
					CirculoFestivo lcf_tmp;

					lcf_tmp = DaoCreator.getCirculoFestivoDAO(ldm_manager)
							                .findByIdAndFecha(acf_parametros.getIdCirculo(), acf_parametros.getFecha());

					if(lcf_tmp == null)
						DaoCreator.getCirculoFestivoDAO(ldm_manager).insertOrUpdate(acf_parametros, ab_accion);
					else
						throw new B2BException(ErrorKeys.ERROR_ID_MEDIDA_AREA);
				}
				else
					DaoCreator.getCirculoFestivoDAO(ldm_manager).insertOrUpdate(acf_parametros, ab_accion);
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("salvarCirculoFestivo", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}
	}

	/**
	 * Salvar circulo origen destino.
	 *
	 * @param lpc_parametros de lpc parametros
	 * @param ab_insertar de ab insertar
	 * @param as_userId de as user id
	 * @param as_remoteIpAddress de as remote ip address
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public synchronized void salvarCirculoOrigenDestino(
	    CirculoOrigenDestino lpc_parametros, boolean ab_insertar, String as_userId, String as_remoteIpAddress
	)
	    throws B2BException
	{
		if(lpc_parametros != null)
		{
			DAOManager ldm_manager;

			ldm_manager = DaoManagerFactory.getDAOManager();

			try
			{
				if(lpc_parametros != null)
				{
					String                  ls_validador;
					CirculoOrigenDestinoDAO lcod_DAO;

					lcod_DAO = DaoCreator.getCirculoOrigenDestinoDAO(ldm_manager);

					{
						ls_validador = lpc_parametros.getIdCirculoOrigen();

						if(!StringUtils.isValidString(ls_validador))
							throw new B2BException(ErrorKeys.ERROR_SELECCIONE_CIRCULO_ORIGEN);
					}

					{
						ls_validador = lpc_parametros.getIdCirculoDestino();

						if(!StringUtils.isValidString(ls_validador))
							throw new B2BException(ErrorKeys.ERROR_SELECCIONE_CIRCULO_DESTINO);
					}

					{
						ls_validador = lpc_parametros.getActivo();

						if(!StringUtils.isValidString(ls_validador))
							throw new B2BException(ErrorKeys.DEBE_SELECCIONAR_ACTIVO);
					}

					if(ab_insertar)
					{
						lpc_parametros.setIdUsuarioCreacion(as_userId);
						lpc_parametros.setIpCreacion(as_remoteIpAddress);
						lcod_DAO.insert(lpc_parametros);
					}
					else
					{
						lpc_parametros.setIdUsuarioModificacion(as_userId);
						lpc_parametros.setIpModificacion(as_remoteIpAddress);
						lcod_DAO.update(lpc_parametros);
					}
				}
			}
			catch(B2BException lb2be_e)
			{
				ldm_manager.setRollbackOnly();

				clh_LOGGER.error("salvarCirculoOrigenDestino", lb2be_e);

				throw lb2be_e;
			}
			finally
			{
				ldm_manager.close();
			}
		}
	}

	/**
	 * Metodo para insertar y actualizar los registros ComunidadesEtnicas ingresados en pantalla.
	 *
	 * @param ace_parametros Collection<ComunidadesEtnicas> Colección con objetos a actualizar o modificar
	 * @param as_userId correspondiente al valor del tipo de objeto String
	 * @param as_remoteIp correspondiente al valor del tipo de objeto String
	 * @throws B2BException Señala que se ha producido una excepción
	 *
	 */
	public synchronized void salvarComunidadesEtnicas(
	    Collection<ComunidadesEtnicas> ace_parametros, String as_userId, String as_remoteIp
	)
	    throws B2BException
	{
		DAOManager ldm_manager;

		ldm_manager = DaoManagerFactory.getDAOManager();

		try
		{
			ComunidadesEtnicasDAO          lced_comunidadesEtnicasDAO;
			Collection<ComunidadesEtnicas> lcce_comunidadesEtnicasOriginal;

			lced_comunidadesEtnicasDAO          = DaoCreator.getComunidadesEtnicasDAO(ldm_manager);
			lcce_comunidadesEtnicasOriginal     = lced_comunidadesEtnicasDAO.findAll();

			if(CollectionUtils.isValidCollection(ace_parametros))
			{
				ComunidadesEtnicas lce_comunidadesEtnicas;

				lce_comunidadesEtnicas = null;

				if(CollectionUtils.isValidCollection(lcce_comunidadesEtnicasOriginal))
				{
					int li_tam;
					int li_tamOriginal;

					li_tam             = ace_parametros.size();
					li_tamOriginal     = lcce_comunidadesEtnicasOriginal.size();

					if(li_tamOriginal > li_tam)
					{
						for(int li_count = li_tam; li_count < li_tamOriginal; li_count++)
						{
							int li_eliminar;

							li_eliminar     = li_count + 1;

							lce_comunidadesEtnicas = lced_comunidadesEtnicasDAO.findById(li_eliminar);

							if(lce_comunidadesEtnicas != null)
								lced_comunidadesEtnicasDAO.delete(li_eliminar);
						}
					}
				}

				for(ComunidadesEtnicas lce_temp : ace_parametros)
				{
					if(lce_temp != null)
					{
						lce_comunidadesEtnicas = lced_comunidadesEtnicasDAO.findById(lce_temp.getIdComunidad());

						if(lce_comunidadesEtnicas != null)
						{
							lce_temp.setIdUsuarioModificacion(as_userId);
							lce_temp.setIpModificacion(as_remoteIp);

							lced_comunidadesEtnicasDAO.update(lce_temp);
						}
						else
						{
							lce_temp.setIdUsuarioCreacion(as_userId);
							lce_temp.setIpCreacion(as_remoteIp);

							lced_comunidadesEtnicasDAO.insert(lce_temp);
						}
					}
				}
			}
			else
			{
				if(CollectionUtils.isValidCollection(lcce_comunidadesEtnicasOriginal))
				{
					for(ComunidadesEtnicas lce_tempEliminar : lcce_comunidadesEtnicasOriginal)
					{
						if(lce_tempEliminar != null)
						{
							int li_idComunidad;

							li_idComunidad = lce_tempEliminar.getIdComunidad();

							if(li_idComunidad > 0)
								lced_comunidadesEtnicasDAO.delete(li_idComunidad);
						}
					}
				}
			}
		}

		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("salvarComunidadesEtnicas", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}
	}

	/**
	 * Salvar condicion tarifa.
	 *
	 * @param act_parametros correspondiente al valor del tipo de objeto CondicionTarifa
	 * @param ab_accion correspondiente al valor del tipo de objeto boolean
	 * @param as_userId correspondiente al valor del tipo de objeto String
	 * @param as_remoteIp correspondiente al valor del tipo de objeto String
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public synchronized void salvarCondicionTarifa(
	    CondicionTarifa act_parametros, boolean ab_accion, String as_userId, String as_remoteIp
	)
	    throws B2BException
	{
		DAOManager ldm_manager;
		ldm_manager = DaoManagerFactory.getDAOManager();

		try
		{
			if(act_parametros != null)
			{
				act_parametros.setIdUsuarioCreacion(as_userId);
				act_parametros.setIpCreacion(as_remoteIp);
				act_parametros.setIdUsuarioModificacion(as_userId);
				act_parametros.setIpModificacion(as_remoteIp);

				if(ab_accion)
				{
					CondicionTarifa lct_tmp;

					lct_tmp = DaoCreator.getCondicionTarifaDAO(ldm_manager).findById(act_parametros);

					if(lct_tmp == null)
						DaoCreator.getCondicionTarifaDAO(ldm_manager).insertOrUpdate(act_parametros, ab_accion);
					else
						throw new B2BException(ErrorKeys.ERROR_ID_MEDIDA_AREA);
				}

				else
					DaoCreator.getCondicionTarifaDAO(ldm_manager).insertOrUpdate(act_parametros, ab_accion);
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("salvarCondicionTarifa", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}
	}

	/**
	 * Método para realizar transacciones con la base de datos para salvar todos los registros
	 * de Constantes ya sean modificaciones o inserciones.
	 *
	 * @param ac_parametros Representa el objeto el cual se va a insertar o modificar
	 * @param ab_accion indica si se va a insertar o modificar en la base de datos
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public synchronized void salvarConstantes(Constantes ac_parametros, boolean ab_accion)
	    throws B2BException
	{
		DAOManager    ldm_manager;
		ConstantesDAO lcd_constantesDao;
		ldm_manager           = DaoManagerFactory.getDAOManager();
		lcd_constantesDao     = DaoCreator.getConstantesDAO(ldm_manager);

		try
		{
			if(ac_parametros != null)
			{
				if(ab_accion)
				{
					Constantes lc_constante_temp;
					lc_constante_temp = lcd_constantesDao.findById(ac_parametros);

					if(lc_constante_temp != null)
					{
						Object[] loa_messageArgs = new String[1];

						loa_messageArgs[0] = lc_constante_temp.getIdConstante();

						throw new B2BException(ErrorKeys.CONSTANTE_EXISTENTE, loa_messageArgs);
					}
				}

				{
					String ls_idConstante;
					ls_idConstante = ac_parametros.getIdConstante();

					if(!StringUtils.isValidString(ls_idConstante))
						throw new B2BException(ErrorKeys.DEBE_DIGITAR_ID_CONSTANTE);
				}

				{
					String ls_tipoArchivo;

					ls_tipoArchivo = ac_parametros.getTipoArchivo();

					if(
					    StringUtils.isValidString(ls_tipoArchivo)
						    && ls_tipoArchivo.equalsIgnoreCase(IdentificadoresCommon.RTF)
					)
					{
						String ls_imagen;

						ls_imagen = StringUtils.cleanBrokenTagsOfDocument(new String(ac_parametros.getImagenes()));

						if(StringUtils.isValidString(ls_imagen))
							ac_parametros.setImagenes(ls_imagen.getBytes());
					}
				}

				lcd_constantesDao.insertOrUpdate(ac_parametros, ab_accion);
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("salvarConstantes", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}
	}

	/**
	 * Método para realizar transacciones con la base de datos para salvar todos los registros
	 * de Constantes ya sean modificaciones o inserciones.
	 *
	 * @param ac_parametros Representa el objeto el cual se va a insertar o modificar
	 * @param ab_accion indica si se va a insertar o modificar en la base de datos
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public synchronized void salvarConstantesCYN(Constantes ac_parametros, boolean ab_accion)
	    throws B2BException
	{
		DAOManager    ldm_manager;
		ConstantesDAO lcd_constantesDao;
		ldm_manager           = DaoManagerFactory.getDAOManagerCYN();
		lcd_constantesDao     = DaoCreator.getConstantesDAO(ldm_manager);

		try
		{
			if(ac_parametros != null)
			{
				if(ab_accion)
				{
					Constantes lc_constante_temp;
					lc_constante_temp = lcd_constantesDao.findById(ac_parametros);

					if(lc_constante_temp != null)
					{
						Object[] loa_messageArgs = new String[1];

						loa_messageArgs[0] = lc_constante_temp.getIdConstante();

						throw new B2BException(ErrorKeys.CONSTANTE_EXISTENTE, loa_messageArgs);
					}
				}

				{
					String ls_idConstante;
					ls_idConstante = ac_parametros.getIdConstante();

					if(!StringUtils.isValidString(ls_idConstante))
						throw new B2BException(ErrorKeys.DEBE_DIGITAR_ID_CONSTANTE);
				}

				lcd_constantesDao.insertOrUpdate(ac_parametros, ab_accion);
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("salvarConstantesCYN", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}
	}

	/**
	 * Salvar consultas.
	 *
	 * @param ac_parametros correspondiente al valor del tipo de objeto Consultas
	 * @param ab_accion correspondiente al valor del tipo de objeto boolean
	 * @param as_userId correspondiente al valor del tipo de objeto String
	 * @param as_remoteIp correspondiente al valor del tipo de objeto String
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public synchronized void salvarConsultas(
	    Consultas ac_parametros, boolean ab_accion, String as_userId, String as_remoteIp
	)
	    throws B2BException
	{
		DAOManager ldm_manager;
		ldm_manager = DaoManagerFactory.getDAOManager();

		try
		{
			if(ac_parametros != null)
			{
				ac_parametros.setIdUsuarioCreacion(as_userId);
				ac_parametros.setIpCreacion(as_remoteIp);
				ac_parametros.setIdUsuarioModificacion(as_userId);
				ac_parametros.setIpModificacion(as_remoteIp);

				if(ab_accion)
				{
					Consultas lc_tmp;

					lc_tmp = DaoCreator.getConsultasPgnDAO(ldm_manager).findById(ac_parametros);

					if(lc_tmp == null)
						DaoCreator.getConsultasPgnDAO(ldm_manager).insertOrUpdate(ac_parametros, ab_accion);
					else
						throw new B2BException(ErrorKeys.ERROR_ID_CONSULTA);
				}

				else
					DaoCreator.getConsultasPgnDAO(ldm_manager).insertOrUpdate(ac_parametros, ab_accion);
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("salvarConsultas", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}
	}

	/**
	 * Método para la transaccion con la base de datos con el fin de hacer inserciones o modificaciones de registros
	 * en la tabla SDB_PNG_COORDENADA.
	 *
	 * @param ac_parametros de ac parametros
	 * @param ab_accion de ab accion
	 * @param as_userId de as user id
	 * @param as_remoteIp de as remote ip
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public synchronized void salvarCoordenada(
	    Coordenada ac_parametros, boolean ab_accion, String as_userId, String as_remoteIp
	)
	    throws B2BException
	{
		DAOManager ldm_manager;
		ldm_manager = DaoManagerFactory.getDAOManager();

		try
		{
			if(ac_parametros != null)
			{
				ac_parametros.setIdUsuarioCreacion(as_userId);
				ac_parametros.setIpCreacion(as_remoteIp);
				ac_parametros.setIdUsuarioModificacion(as_userId);
				ac_parametros.setIpModificacion(as_remoteIp);

				if(ab_accion)
				{
					Coordenada lc_tmp;

					lc_tmp = DaoCreator.getCoordenadaDAO(ldm_manager).findById(ac_parametros);

					if(lc_tmp == null)
						DaoCreator.getCoordenadaDAO(ldm_manager).insertOrUpdate(ac_parametros, ab_accion);
					else
						throw new B2BException(ErrorKeys.ERROR_ID_COORDENADA);
				}

				else
					DaoCreator.getCoordenadaDAO(ldm_manager).insertOrUpdate(ac_parametros, ab_accion);
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("salvarCoordenada", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}
	}

	/**
	 * Método para la transaccion con la base de datos con el fin de hacer inserciones o modificaciones de registros
	 * en la tabla SDB_PGN_DEPARTAMENTO.
	 *
	 * @param ad_parametros correspondiente al valor del tipo de objeto Departamento
	 * @param ab_accion correspondiente al valor del tipo de objeto boolean
	 * @param as_usuario es el usuario que realiza la modificación o la inserción
	 * @param as_ipRemota es la ip de donde se ha realizado la acción de modificar o insertar
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public synchronized void salvarDepartamento(
	    Departamento ad_parametros, boolean ab_accion, String as_usuario, String as_ipRemota
	)
	    throws B2BException
	{
		DAOManager ldm_manager;
		ldm_manager = DaoManagerFactory.getDAOManager();

		try
		{
			if(ad_parametros != null)
			{
				{
					String ls_idPais;
					ls_idPais = ad_parametros.getIdPais();

					if(!StringUtils.isValidString(ls_idPais))
						throw new B2BException(ErrorKeys.DEBE_DIGITAR_ID_PAIS);
				}

				{
					String ls_idDepartamento;
					ls_idDepartamento = ad_parametros.getIdDepartamento();

					if(!StringUtils.isValidString(ls_idDepartamento))
						throw new B2BException(ErrorKeys.DEBE_DIGITAR_ID_DEPARTAMENTO);
				}

				{
					String ls_nombre;
					ls_nombre = ad_parametros.getNombre();

					if(!StringUtils.isValidString(ls_nombre))
						throw new B2BException(ErrorKeys.DEBE_DIGITAR_NOMBRE);
				}

				{
					String ls_indicativoTelefonico;
					ls_indicativoTelefonico = ad_parametros.getIndicativoTelefonico();

					if(!StringUtils.isValidString(ls_indicativoTelefonico))
						throw new B2BException(ErrorKeys.DEBE_DIGITAR_INDICATIVO_TELEFONICO);
				}

				{
					String ls_activo;
					ls_activo = ad_parametros.getActivo();

					if(!StringUtils.isValidString(ls_activo))
						throw new B2BException(ErrorKeys.DEBE_DIGITAR_ACTIVO);
				}

				ad_parametros.setIdUsuarioCreacion(as_usuario);
				ad_parametros.setIpCreacion(as_ipRemota);
				ad_parametros.setIdUsuarioModificacion(as_usuario);
				ad_parametros.setIpModificacion(as_ipRemota);

				if(ab_accion)
				{
					Departamento ld_tmp;

					ld_tmp = DaoCreator.getDepartamentoDAO(ldm_manager).findById(ad_parametros);

					if(ld_tmp == null)
						DaoCreator.getDepartamentoDAO(ldm_manager).insertOrUpdate(ad_parametros, ab_accion);
					else
						throw new B2BException(ErrorKeys.ERROR_ID_DEPARTAMENTO_EXISTE);
				}
				else
					DaoCreator.getDepartamentoDAO(ldm_manager).insertOrUpdate(ad_parametros, ab_accion);
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("salvarDepartamento", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}
	}

	/**
	 * Método para la transaccion con la base de datos con el fin de hacer inserciones o modificaciones de registros
	 * en la tabla SDB_PGN_DEPENDENCIA_SNR.
	 *
	 * @param adsnr_parametros correspondiente al valor del tipo de objeto DependenciaSNR
	 * @param ab_accion correspondiente al valor del tipo de objeto boolean
	 * @param as_usuario es el usuario que realiza la modificación o la inserción
	 * @param as_ipRemota es la ip de donde se ha realizado la acción de modificar o insertar
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public synchronized void salvarDependenciaSNR(
	    DependenciaSNR adsnr_parametros, boolean ab_accion, String as_usuario, String as_ipRemota
	)
	    throws B2BException
	{
		DAOManager ldm_manager;

		ldm_manager = DaoManagerFactory.getDAOManager();

		try
		{
			if(adsnr_parametros != null)
			{
				if(ab_accion)
				{
					adsnr_parametros.setIdUsuarioCreacion(as_usuario);
					adsnr_parametros.setIpCreacion(as_ipRemota);

					DaoCreator.getDependenciaSNRDAO(ldm_manager).insert(adsnr_parametros);
				}
				else
				{
					adsnr_parametros.setIdUsuarioModificacion(as_usuario);
					adsnr_parametros.setIpModificacion(as_ipRemota);

					DaoCreator.getDependenciaSNRDAO(ldm_manager).update(adsnr_parametros);
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("salvarDependenciaSNR", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}
	}

	/**
	 * Método para salvar la insercion o modificacion para la tabla SDB_PGN_DETALLE_PROCESO_CONSULTA.
	 *
	 * @param adpc_parametros objeto que se desea salvar
	 * @param ab_accion indica si se va a insertar o modificar
	 * @param as_userId usuario en sesion que realiza alguna accion(modificar o insertar)
	 * @param as_remoteIp ip del usuario en sesion
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public synchronized void salvarDetalleProcesoConsulta(
	    DetalleProcesoConsulta adpc_parametros, boolean ab_accion, String as_userId, String as_remoteIp
	)
	    throws B2BException
	{
		DAOManager ldm_manager;
		ldm_manager = DaoManagerFactory.getDAOManager();

		try
		{
			if(adpc_parametros != null)
			{
				if(ab_accion)
				{
					adpc_parametros.setIdUsuarioCreacion(as_userId);
					adpc_parametros.setIpCreacion(as_remoteIp);
				}
				else
				{
					adpc_parametros.setIdUsuarioModificacion(as_userId);
					adpc_parametros.setIpModificacion(as_remoteIp);
				}

				DetalleProcesoConsulta idpc_datos;
				idpc_datos = null;

				if(ab_accion)
				{
					idpc_datos = DaoCreator.getDetalleProcesoConsultaDAO(ldm_manager).findById(adpc_parametros);

					if(idpc_datos == null)
						DaoCreator.getDetalleProcesoConsultaDAO(ldm_manager).insertOrUpdate(adpc_parametros, ab_accion);
					else
						throw new B2BException(ErrorKeys.ERROR_DETALLE_PROCESO_CONSULTA);
				}

				else
					DaoCreator.getDetalleProcesoConsultaDAO(ldm_manager).insertOrUpdate(adpc_parametros, ab_accion);
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("salvarDetalleProcesoConsulta", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}
	}

	/**
	 * Método para salvar el DominioRRR ya sea para insertar o modificar en la tabla SDB_COL_DOMINIO_RRR.
	 *
	 * @param ldr_parametros objeto a modificar o insertar
	 * @param ab_insertar indica si se desea insertar o actualizar
	 * @param as_userId es el usuario que inserta o modifica registros
	 * @param as_ipRemote el al i p del usuario que esta haciendo la insercion o la actualizacion
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public synchronized void salvarDominioRRR(
	    DominioRrr ldr_parametros, boolean ab_insertar, String as_userId, String as_ipRemote
	)
	    throws B2BException
	{
		DAOManager ldm_manager;
		ldm_manager = DaoManagerFactory.getDAOManager();

		try
		{
			if(ldr_parametros != null)
			{
				DominioRrrDAO ldr_DAO;

				ldr_DAO = DaoCreator.getDominioRrrDAO(ldm_manager);

				{
					String ls_idTipoRRR;
					ls_idTipoRRR = ldr_parametros.getIdTipoRrr();

					if(!StringUtils.isValidString(ls_idTipoRRR))
						throw new B2BException(ErrorKeys.DEBE_DIGITAR_TIPO_RRR);
				}

				{
					String ls_descripcion;
					ls_descripcion = ldr_parametros.getDescripcion();

					if(!StringUtils.isValidString(ls_descripcion))
						throw new B2BException(ErrorKeys.DEBE_DIGITAR_DESCRIPCION);
				}

				{
					String ls_activo;
					ls_activo = ldr_parametros.getActivo();

					if(!StringUtils.isValidString(ls_activo))
						throw new B2BException(ErrorKeys.DEBE_DIGITAR_ACTIVO);
				}

				ldr_parametros.setIdUsuarioCreacion(as_userId);
				ldr_parametros.setIpCreacion(as_ipRemote);
				ldr_parametros.setIdUsuarioModificacion(as_userId);
				ldr_parametros.setIpModificacion(as_ipRemote);

				ldr_DAO.insertOrUpdate(ldr_parametros, ab_insertar);
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("salvarDominioRRR", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}
	}

	/**
	 * Método para realizar la insercion para las tablas SDB_ACC_ENTIDAD_EXTERNA Y SDB_ACC_ENTIDAD_EXTERNA_PERSONA.
	 *
	 * @param aaee_parametros de aaee parametros
	 * @param ap_parametros objeto Collection de Persona que se desea salvar
	 * @param as_userId usuario en sesion que realiza la insercion
	 * @param as_remoteIp ip del usuario en sesion
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public synchronized void salvarEntidades(
	    AccEntidadExterna aaee_parametros, Collection<Persona> ap_parametros, String as_userId, String as_remoteIp
	)
	    throws B2BException
	{
		DAOManager                  ldm_manager;
		PersonaDAO                  lpd_personaDAO;
		PersonaCorreoElectronicoDAO lpced_personaCorreoDAO;
		AccEntidadExternaDAO        laeed_entidadExternaDAO;
		AccEntidadExternaPersonaDAO laeepd_entidadExternaPersonaDAO;

		ldm_manager                         = DaoManagerFactory.getDAOManager();
		lpd_personaDAO                      = DaoCreator.getPersonaDAO(ldm_manager);
		lpced_personaCorreoDAO              = DaoCreator.getPersonaCorreoElectronicoDAO(ldm_manager);
		laeed_entidadExternaDAO             = DaoCreator.getAccEntidadExternaDAO(ldm_manager);
		laeepd_entidadExternaPersonaDAO     = DaoCreator.getAccEntidadExternaPersonaDAO(ldm_manager);

		try
		{
			if(aaee_parametros != null)
			{
				aaee_parametros.setIdUsuarioCreacion(as_userId);
				aaee_parametros.setIpCreacion(as_remoteIp);

				long    ll_idEntidad;
				boolean lb_representante;

				ll_idEntidad         = laeed_entidadExternaDAO.insert(aaee_parametros);
				lb_representante     = false;

				if(ll_idEntidad > 0)
				{
					if(CollectionUtils.isValidCollection(ap_parametros))
					{
						for(Persona lp_temp : ap_parametros)
						{
							if(lp_temp != null)
							{
								String  ls_idPersona;
								Persona lp_persona;

								ls_idPersona     = lp_temp.getIdPersona();
								lp_persona       = null;

								if(StringUtils.isValidString(ls_idPersona))
									lp_persona = lp_temp;
								else
								{
									lp_temp.setIdUsuarioCreacion(as_userId);
									lp_temp.setIpCreacion(as_remoteIp);
									lp_temp.setIdEntidadExterna(StringUtils.getString(ll_idEntidad));

									lp_persona = lpd_personaDAO.insertOrUpdate(lp_temp, true);

									if(lp_persona != null)
									{
										PersonaCorreoElectronico lpce_personaCorreo;

										lpce_personaCorreo = new PersonaCorreoElectronico();

										lpce_personaCorreo.setIdPersona(lp_persona.getIdPersona());
										lpce_personaCorreo.setCorreoElectronico(lp_persona.getCorreoElectronico());
										lpce_personaCorreo.setIdUsuarioCreacion(as_userId);
										lpce_personaCorreo.setIpCreacion(as_remoteIp);
										lpce_personaCorreo.setFechaDesde(new Date());

										lpced_personaCorreoDAO.insertOrUpdate(lpce_personaCorreo, true);
									}
								}

								{
									AccEntidadExternaPersona laeep_entidadExternaPersona;
									boolean                  lb_persona;

									laeep_entidadExternaPersona     = new AccEntidadExternaPersona();
									lb_persona                      = lp_persona != null;

									{
										String ls_representanteLegal;

										ls_representanteLegal = lb_persona ? lp_persona.getRepresentanteLegal() : null;

										if(StringUtils.isValidString(ls_representanteLegal))
										{
											lb_representante = ls_representanteLegal.equalsIgnoreCase(EstadoCommon.S);
											laeep_entidadExternaPersona.setRepresentanteLegal(ls_representanteLegal);
										}
										else
										{
											laeep_entidadExternaPersona.setRepresentanteLegal(EstadoCommon.N);
											lb_representante = false;
										}
									}

									laeep_entidadExternaPersona.setIdEntidadExterna(
									    StringUtils.getString(ll_idEntidad)
									);
									laeep_entidadExternaPersona.setUsuario(lp_temp.getUsuario());
									laeep_entidadExternaPersona.setIdUsuarioCreacion(as_userId);
									laeep_entidadExternaPersona.setIpCreacion(as_remoteIp);

									if(lb_persona)
										laeep_entidadExternaPersona.setIdPersona(lp_persona.getIdPersona());

									laeepd_entidadExternaPersonaDAO.insert(laeep_entidadExternaPersona);

									if(lb_persona)
									{
										lp_persona.setIdEntidadExterna(StringUtils.getString(ll_idEntidad));
										lp_persona.setIdUsuarioModificacion(as_userId);
										lp_persona.setIpModificacion(as_remoteIp);
									}

									lp_persona = lpd_personaDAO.insertOrUpdate(lp_persona, false);
								}

								if(lb_representante)
								{
									AccEntidadExternaPersona laeep_entidadPersona;

									laeep_entidadPersona = laeepd_entidadExternaPersonaDAO.findById(
										    StringUtils.getString(ll_idEntidad), lp_persona.getIdPersona()
										);

									if(laeep_entidadPersona != null)
									{
										String ls_idEntidadExternaPersona;

										ls_idEntidadExternaPersona = laeep_entidadPersona.getIdEntidadExternaPersona();

										if(StringUtils.isValidString(ls_idEntidadExternaPersona))
										{
											AccEntidadExterna laee_entidadExterna;

											laee_entidadExterna = laeed_entidadExternaDAO.findByIdAccEntidadExterna(
												    StringUtils.getString(ll_idEntidad)
												);

											if(laee_entidadExterna != null)
											{
												laee_entidadExterna.setIdRepresentanteLegal(ls_idEntidadExternaPersona);

												laeed_entidadExternaDAO.update(laee_entidadExterna);
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

			clh_LOGGER.error("salvarEntidades", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}
	}

	/**
	 * Método para la transaccion con la base de datos con el fin de hacer inserciones o modificaciones de registros
	 * en la tabla SDB_PNG_ENTIDADES_ALERTA.
	 *
	 * @param aea_parametros correspondiente al valor del tipo de objeto EntidadesAlerta
	 * @param ab_accion correspondiente al valor del tipo de objeto boolean
	 * @param as_usuario es el usuario que realiza la modificación o la inserción
	 * @param as_ipRemota es la ip de donde se ha realizado la acción de modificar o insertar
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public synchronized void salvarEntidadesAlerta(
	    EntidadesAlerta aea_parametros, boolean ab_accion, String as_usuario, String as_ipRemota
	)
	    throws B2BException
	{
		DAOManager ldm_manager;

		ldm_manager = DaoManagerFactory.getDAOManager();

		try
		{
			if(aea_parametros != null)
			{
				if(ab_accion)
				{
					aea_parametros.setIdUsuarioCreacion(as_usuario);
					aea_parametros.setIpCreacion(as_ipRemota);

					DaoCreator.getEntidadesAlertaDAO(ldm_manager).insert(aea_parametros);
				}
				else
				{
					aea_parametros.setIdUsuarioModificacion(as_usuario);
					aea_parametros.setIpModificacion(as_ipRemota);

					DaoCreator.getEntidadesAlertaDAO(ldm_manager).update(aea_parametros);
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("salvarEntidadesAlerta", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}
	}

	/**
	 * Método para realizar la modificacion para las tablas SDB_ACC_ENTIDAD_EXTERNA Y SDB_ACC_ENTIDAD_EXTERNA_PERSONA.
	 *
	 * @param aaee_parametros Argumento de tipo <code>AccEntidadExterna</code> que contiene la entidad externa de la operación.
	 * @param ap_parametros Argumento de tipo <code>Collection</code> de Persona que contiene la información que se desea salvar.
	 * @param as_userId Argumento de tipo <code>String</code> que contiene el usuario en sesion que realiza la insercion.
	 * @param as_remoteIp Argumento de tipo <code>String</code> que contiene la ip del usuario en sesion.
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public synchronized void salvarEntidadesModificadas(
	    AccEntidadExterna aaee_parametros, Collection<Persona> ap_parametros, String as_userId, String as_remoteIp
	)
	    throws B2BException
	{
		DAOManager                  ldm_manager;
		PersonaDAO                  lpd_personaDAO;
		PersonaCorreoElectronicoDAO lpced_personaCorreoDAO;
		AccEntidadExternaPersonaDAO laeepd_entidadExternaPersonaDAO;
		AccEntidadExternaDAO        laeed_entidadExternaDAO;

		ldm_manager                         = DaoManagerFactory.getDAOManager();
		lpd_personaDAO                      = DaoCreator.getPersonaDAO(ldm_manager);
		lpced_personaCorreoDAO              = DaoCreator.getPersonaCorreoElectronicoDAO(ldm_manager);
		laeepd_entidadExternaPersonaDAO     = DaoCreator.getAccEntidadExternaPersonaDAO(ldm_manager);
		laeed_entidadExternaDAO             = DaoCreator.getAccEntidadExternaDAO(ldm_manager);

		try
		{
			if(aaee_parametros != null)
			{
				boolean lb_representante;

				lb_representante = false;

				aaee_parametros.setIdUsuarioModificacion(as_userId);
				aaee_parametros.setIpModificacion(as_remoteIp);

				laeed_entidadExternaDAO.update(aaee_parametros);
				laeepd_entidadExternaPersonaDAO.updateInactivo(
				    as_remoteIp, as_userId, aaee_parametros.getIdEntidadExterna()
				);

				if(CollectionUtils.isValidCollection(ap_parametros))
				{
					for(Persona lp_temp : ap_parametros)
					{
						if(lp_temp != null)
						{
							String ls_idPersona;

							ls_idPersona = lp_temp.getIdPersona();

							lp_temp.setIdUsuarioCreacion(as_userId);
							lp_temp.setIpCreacion(as_remoteIp);
							lp_temp.setIdUsuarioModificacion(as_userId);
							lp_temp.setIpModificacion(as_remoteIp);

							if(StringUtils.isValidString(ls_idPersona))
							{
								PersonaCorreoElectronico lpce_personaCorreo;

								lpce_personaCorreo = lpced_personaCorreoDAO.findMaxByIdPersona(ls_idPersona);

								if(lpce_personaCorreo != null)
								{
									lpce_personaCorreo.setCorreoElectronico(lp_temp.getCorreoElectronico());

									lpced_personaCorreoDAO.insertOrUpdate(lpce_personaCorreo, false);
								}

								else
								{
									PersonaCorreoElectronico lpce_personaCorreoElectronico;

									lpce_personaCorreoElectronico = new PersonaCorreoElectronico();

									lpce_personaCorreoElectronico.setIdPersona(lp_temp.getIdPersona());
									lpce_personaCorreoElectronico.setCorreoElectronico(lp_temp.getCorreoElectronico());
									lpce_personaCorreoElectronico.setIdUsuarioCreacion(as_userId);
									lpce_personaCorreoElectronico.setIpCreacion(as_remoteIp);
									lpce_personaCorreoElectronico.setFechaDesde(new Date());

									lpced_personaCorreoDAO.insertOrUpdate(lpce_personaCorreoElectronico, true);
								}

								lpd_personaDAO.insertOrUpdate(lp_temp, false);

								{
									AccEntidadExternaPersona laeep_entidadExternaPersona;
									AccEntidadExternaPersona laeep_entidadExternaPersonaExiste;

									laeep_entidadExternaPersona           = new AccEntidadExternaPersona();
									laeep_entidadExternaPersonaExiste     = laeepd_entidadExternaPersonaDAO.findById(
										    aaee_parametros.getIdEntidadExterna(), ls_idPersona
										);

									{
										String ls_representanteLegal;

										ls_representanteLegal = lp_temp.getRepresentanteLegal();

										if(StringUtils.isValidString(ls_representanteLegal))
										{
											lb_representante = ls_representanteLegal.equalsIgnoreCase(EstadoCommon.S);
											laeep_entidadExternaPersona.setRepresentanteLegal(ls_representanteLegal);
										}
										else
										{
											laeep_entidadExternaPersona.setRepresentanteLegal(EstadoCommon.N);
											lb_representante = false;
										}

										laeep_entidadExternaPersona.setRepresentanteLegal(ls_representanteLegal);
									}

									laeep_entidadExternaPersona.setIdPersona(ls_idPersona);
									laeep_entidadExternaPersona.setIdEntidadExterna(
									    aaee_parametros.getIdEntidadExterna()
									);

									if(laeep_entidadExternaPersonaExiste != null)
									{
										laeep_entidadExternaPersona.setIpModificacion(as_remoteIp);
										laeep_entidadExternaPersona.setIdUsuarioModificacion(as_userId);

										laeepd_entidadExternaPersonaDAO.updateActivo(laeep_entidadExternaPersona);
									}
									else
									{
										laeep_entidadExternaPersona.setUsuario(lp_temp.getUsuario());
										laeep_entidadExternaPersona.setIdUsuarioCreacion(as_userId);
										laeep_entidadExternaPersona.setIpCreacion(as_remoteIp);
										laeep_entidadExternaPersona.setActivo(EstadoCommon.S);

										laeepd_entidadExternaPersonaDAO.insert(laeep_entidadExternaPersona);
									}
								}
							}

							else
							{
								lp_temp.setIdUsuarioCreacion(as_userId);
								lp_temp.setIpCreacion(as_remoteIp);

								Persona lp_persona;

								lp_persona = lpd_personaDAO.insertOrUpdate(lp_temp, true);

								if(lp_persona != null)
								{
									PersonaCorreoElectronico lpce_personaCorreoElectronicoInsertar;

									lpce_personaCorreoElectronicoInsertar     = new PersonaCorreoElectronico();

									ls_idPersona = lp_persona.getIdPersona();

									lpce_personaCorreoElectronicoInsertar.setIdPersona(ls_idPersona);
									lpce_personaCorreoElectronicoInsertar.setCorreoElectronico(
									    lp_persona.getCorreoElectronico()
									);
									lpce_personaCorreoElectronicoInsertar.setIdUsuarioCreacion(as_userId);
									lpce_personaCorreoElectronicoInsertar.setIpCreacion(as_remoteIp);
									lpce_personaCorreoElectronicoInsertar.setFechaDesde(new Date());

									lpced_personaCorreoDAO.insertOrUpdate(lpce_personaCorreoElectronicoInsertar, true);

									{
										AccEntidadExternaPersona laeep_entidadExternaPersona;

										laeep_entidadExternaPersona = new AccEntidadExternaPersona();

										{
											String ls_representanteLegal;

											ls_representanteLegal = lp_temp.getRepresentanteLegal();

											if(StringUtils.isValidString(ls_representanteLegal))
											{
												lb_representante = ls_representanteLegal.equalsIgnoreCase(
													    EstadoCommon.S
													);
												laeep_entidadExternaPersona.setRepresentanteLegal(
												    ls_representanteLegal
												);
											}
											else
											{
												laeep_entidadExternaPersona.setRepresentanteLegal(EstadoCommon.N);
												lb_representante = false;
											}
										}

										laeep_entidadExternaPersona.setIdEntidadExterna(
										    aaee_parametros.getIdEntidadExterna()
										);
										laeep_entidadExternaPersona.setIdPersona(lp_persona.getIdPersona());
										laeep_entidadExternaPersona.setIdUsuarioCreacion(as_userId);
										laeep_entidadExternaPersona.setUsuario(lp_temp.getUsuario());
										laeep_entidadExternaPersona.setIpCreacion(as_remoteIp);

										laeepd_entidadExternaPersonaDAO.insert(laeep_entidadExternaPersona);
									}
								}
							}

							{
								lp_temp.setIdEntidadExterna(
								    StringUtils.getString(aaee_parametros.getIdEntidadExterna())
								);
								lp_temp.setIdUsuarioModificacion(as_userId);
								lp_temp.setIpModificacion(as_remoteIp);

								lp_temp = lpd_personaDAO.insertOrUpdate(lp_temp, false);
							}

							if(lb_representante)
							{
								if(StringUtils.isValidString(ls_idPersona))
								{
									AccEntidadExternaPersona laeep_entidadPersona;

									laeep_entidadPersona = laeepd_entidadExternaPersonaDAO.findById(
										    aaee_parametros.getIdEntidadExterna(), ls_idPersona
										);

									if(laeep_entidadPersona != null)
									{
										String ls_idEntidadExternaPersona;
										String ls_idEntidadExterna;

										ls_idEntidadExternaPersona     = laeep_entidadPersona.getIdEntidadExternaPersona();
										ls_idEntidadExterna            = laeep_entidadPersona.getIdEntidadExterna();

										if(
										    StringUtils.isValidString(ls_idEntidadExternaPersona)
											    && StringUtils.isValidString(ls_idEntidadExterna)
										)
										{
											AccEntidadExterna laee_entidadExterna;

											laee_entidadExterna = laeed_entidadExternaDAO.findByIdAccEntidadExterna(
												    ls_idEntidadExterna
												);

											if(laee_entidadExterna != null)
											{
												laee_entidadExterna.setIdRepresentanteLegal(ls_idEntidadExternaPersona);

												laeed_entidadExternaDAO.update(laee_entidadExterna);
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

			clh_LOGGER.error("salvarEntidadesModificadas", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}
	}

	/**
	 * Método para la transaccion con la base de datos con el fin de hacer inserciones o modificaciones de registros
	 * en la tabla SDB_PGN_ESTADO_ACTIVIDAD.
	 *
	 * @param ac_parametros Argumento de tipo <code>EstadoActividad</code> correspondiente al valor del tipo de objeto EstadoActividad.
	 * @param ab_accion  Argumento de tipo <code>boolean</code> correspondiente al valor del tipo de objeto boolean.
	 * @param as_usuario Argumento de tipo <code>String</code> que contiene el usuario que realiza la modificación o la inserción.
	 * @param as_ipRemota Argumento de tipo <code>String</code> que contiene la ip de donde se ha realizado la acción de modificar o insertar.
	 * @throws B2BException Señala que se ha producido una excepción.
	 */
	public synchronized void salvarEstadoActividad(
	    EstadoActividad ac_parametros, boolean ab_accion, String as_usuario, String as_ipRemota
	)
	    throws B2BException
	{
		DAOManager ldm_manager;
		ldm_manager = DaoManagerFactory.getDAOManager();

		try
		{
			if(ac_parametros != null)
			{
				{
					String ls_estadoActividad;
					ls_estadoActividad = ac_parametros.getIdEstadoActividad();

					if(!StringUtils.isValidString(ls_estadoActividad))
						throw new B2BException(ErrorKeys.DEBE_DIGITAR_ESTADO_ACTIVIDAD);
				}

				{
					String ls_descripcion;
					ls_descripcion = ac_parametros.getDescripcion();

					if(!StringUtils.isValidString(ls_descripcion))
						throw new B2BException(ErrorKeys.DEBE_DIGITAR_DESCRIPCION);
				}

				{
					String ls_activo;
					ls_activo = ac_parametros.getEstado();

					if(!StringUtils.isValidString(ls_activo))
						throw new B2BException(ErrorKeys.DEBE_DIGITAR_ACTIVO);
				}

				ac_parametros.setIdUsuarioCreacion(as_usuario);
				ac_parametros.setIpCreacion(as_ipRemota);
				ac_parametros.setIdUsuarioModificacion(as_usuario);
				ac_parametros.setIpModificacion(as_ipRemota);

				EstadoActividad lpa_tmp;
				lpa_tmp = DaoCreator.getEstadoActividadDAO(ldm_manager).findById(ac_parametros);

				if(ab_accion)
				{
					if(lpa_tmp == null)
						DaoCreator.getEstadoActividadDAO(ldm_manager).insertOrUpdate(ac_parametros, ab_accion);
					else
						throw new B2BException(ErrorKeys.ERROR_ID_ESTADO_ACTIVIDAD);
				}
				else
					DaoCreator.getEstadoActividadDAO(ldm_manager).insertOrUpdate(ac_parametros, ab_accion);
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("salvarEstadoActividad", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}
	}

	/**
	 * Método para salvar la insercion o modificacion para la tabla SDB_PGN_ESTADOS.
	 *
	 * @param ae_parametros objeto que se desea salvar
	 * @param ab_insertar de ab insertar
	 * @param as_userId usuario en sesion que realiza alguna accion(modificar o insertar)
	 * @param as_remoteIp ip del usuario en sesion
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public synchronized void salvarEstados(
	    Estados ae_parametros, boolean ab_insertar, String as_userId, String as_remoteIp
	)
	    throws B2BException
	{
		DAOManager ldm_manager;
		ldm_manager = DaoManagerFactory.getDAOManagerCYN();

		try
		{
			if(ae_parametros != null)
			{
				if(ab_insertar)
				{
					ae_parametros.setIdUsuarioCreacion(as_userId);
					ae_parametros.setIpCreacion(as_remoteIp);

					DaoCreator.getEstadosDAO(ldm_manager).insert(ae_parametros);
				}
				else
				{
					ae_parametros.setIdUsuarioModificacion(as_userId);
					ae_parametros.setIpModificacion(as_remoteIp);

					DaoCreator.getEstadosDAO(ldm_manager).update(ae_parametros);
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("salvarEstados", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}
	}

	/**
	 * Método para la transaccion con la base de datos con el fin de hacer inserciones o modificaciones de registros
	 * en la tabla SDB_PGN_FESTIVO_NACIONAL.
	 *
	 * @param afn_parametros correspondiente al valor del tipo de objeto FestivoNacional
	 * @param ab_accion correspondiente al valor del tipo de objeto boolean
	 * @param as_usuario es el usuario que realiza la modificación o la inserción
	 * @param as_ipRemota es la ip de donde se ha realizado la acción de modificar o insertar
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public synchronized void salvarFestivoNacional(
	    FestivoNacional afn_parametros, boolean ab_accion, String as_usuario, String as_ipRemota
	)
	    throws B2BException
	{
		DAOManager ldm_manager;
		ldm_manager = DaoManagerFactory.getDAOManager();

		try
		{
			if(afn_parametros != null)
			{
				{
					Date ld_fecha;
					ld_fecha = afn_parametros.getFecha();

					if(ld_fecha == null)
						throw new B2BException(ErrorKeys.ERROR_CAMPO_FECHA_OBLIGATORIO);
				}

				afn_parametros.setIpCreacion(as_ipRemota);
				afn_parametros.setIpModificacion(as_ipRemota);

				FestivoNacional lfn_tmp;
				lfn_tmp = DaoCreator.getFestivoNacionalDAO(ldm_manager).findById(afn_parametros);

				if(ab_accion)
				{
					if(lfn_tmp == null)
						DaoCreator.getFestivoNacionalDAO(ldm_manager).insertOrUpdate(afn_parametros, ab_accion);
					else
						throw new B2BException(ErrorKeys.ERROR_ID_FESTIVO_NACIONAL);
				}
				else
					DaoCreator.getFestivoNacionalDAO(ldm_manager).insertOrUpdate(afn_parametros, ab_accion);
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("salvarFestivoNacional", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}
	}

	/**
	 * Método para la transaccion con la base de datos con el fin de hacer inserciones o modificaciones de registros
	 * en la tabla SDB_PGN_GOBERNACION.
	 *
	 * @param ag_parametros correspondiente al valor del tipo de objeto Gobernacion
	 * @param ab_accion correspondiente al valor del tipo de objeto boolean
	 * @param as_usuario es el usuario que realiza la modificación o la inserción
	 * @param as_ipRemota es la ip de donde se ha realizado la acción de modificar o insertar
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public synchronized void salvarGobernacion(
	    Gobernacion ag_parametros, boolean ab_accion, String as_usuario, String as_ipRemota
	)
	    throws B2BException
	{
		DAOManager ldm_manager;
		ldm_manager = DaoManagerFactory.getDAOManager();

		try
		{
			if(ag_parametros != null)
			{
				if(ab_accion)
				{
					ag_parametros.setIdUsuarioCreacion(as_usuario);
					ag_parametros.setIpCreacion(as_ipRemota);
				}
				else
				{
					ag_parametros.setIdUsuarioModificacion(as_usuario);
					ag_parametros.setIpModificacion(as_ipRemota);
				}

				DaoCreator.getGobernacionDAO(ldm_manager).insertOrUpdate(ag_parametros, ab_accion);
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("salvarGobernacion", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}
	}

	/**
	 * Método para la transaccion con la base de datos con el fin de hacer inserciones o modificaciones de registros
	 * en la tabla SDB_PGN_INSTANCIA_CONSULTA.
	 *
	 * @param aic_parametros de aic parametros
	 * @param ab_accion correspondiente al valor del tipo de objeto boolean
	 * @param as_usuario es el usuario que realiza la modificación o la inserción
	 * @param as_ipRemota es la ip de donde se ha realizado la acción de modificar o insertar
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public synchronized void salvarInstanciaConsulta(
	    InstanciaConsulta aic_parametros, boolean ab_accion, String as_usuario, String as_ipRemota
	)
	    throws B2BException
	{
		DAOManager ldm_manager;
		ldm_manager = DaoManagerFactory.getDAOManager();

		try
		{
			if(aic_parametros != null)
			{
				if(ab_accion)
				{
					aic_parametros.setIdUsuarioCreacion(as_usuario);
					aic_parametros.setIpCreacion(as_ipRemota);
				}
				else
				{
					aic_parametros.setIdUsuarioModificacion(as_usuario);
					aic_parametros.setIpModificacion(as_ipRemota);
				}

				DaoCreator.getInstanciaConsultaDAO(ldm_manager).insertOrUpdate(aic_parametros, ab_accion);
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("salvarInstanciaConsulta", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}
	}

	/**
	 * Método para salvar lo realizado ya sea una inserción o modificación de la tabla SDB_COL_INTERESADO_NATURAL_GENERO.
	 *
	 * @param aing_parametros objeto a insertar o modificar
	 * @param ab_accion indica si se inserta o se modifica
	 * @param as_usuario usuario que realiza la modificación o la inserción
	 * @param as_ipRemota ip de donde se esta realizando una accón ya sea de insertar o modificar
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public synchronized void salvarInteresadoNaturalGenero(
	    InteresadoNaturalGenero aing_parametros, boolean ab_accion, String as_usuario, String as_ipRemota
	)
	    throws B2BException
	{
		DAOManager ldm_manager;
		ldm_manager = DaoManagerFactory.getDAOManager();

		try
		{
			if(aing_parametros != null)
			{
				InteresadoNaturalGeneroDAO ling_DAO;
				ling_DAO = DaoCreator.getInteresadoNaturalGeneroDAO(ldm_manager);

				{
					String ls_idTipoPersona;
					ls_idTipoPersona = aing_parametros.getIdNaturalGenero();

					if(!StringUtils.isValidString(ls_idTipoPersona))
						throw new B2BException(ErrorKeys.DEBE_DIGITAR_ID_TIPO_PERSONA);
				}

				{
					String ls_descripcion;
					ls_descripcion = aing_parametros.getDescripcion();

					if(!StringUtils.isValidString(ls_descripcion))
						throw new B2BException(ErrorKeys.DEBE_DIGITAR_DESCRIPCION);
				}

				if(ab_accion)
				{
					InteresadoNaturalGenero ltp_tipoPersona;
					ltp_tipoPersona = ling_DAO.findById(aing_parametros);

					if(ltp_tipoPersona != null)
					{
						Object[] loa_messageArgs;
						loa_messageArgs     = new String[1];

						loa_messageArgs[0] = ltp_tipoPersona.getIdNaturalGenero();

						throw new B2BException(ErrorKeys.ERROR_TIPO_PERSONA_EXISTE, loa_messageArgs);
					}

					aing_parametros.setIdUsuarioCreacion(as_usuario);
					aing_parametros.setIpCreacion(as_ipRemota);
				}
				else
				{
					aing_parametros.setIdUsuarioModificacion(as_usuario);
					aing_parametros.setIpModificacion(as_ipRemota);
				}

				ling_DAO.insertOrUpdate(aing_parametros, ab_accion);
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("salvarInteresadoNaturalGenero", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}
	}

	/**
	 * Método para la transaccion con la base de datos con el fin de hacer inserciones o modificaciones de registros
	 * en la tabla SDB_PNG_LETRA_EJE.
	 *
	 * @param ale_parametros de ale parametros
	 * @param ab_accion de ab accion
	 * @param as_userId de as user id
	 * @param as_remoteIp de as remote ip
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public synchronized void salvarLetraEje(
	    LetraEje ale_parametros, boolean ab_accion, String as_userId, String as_remoteIp
	)
	    throws B2BException
	{
		DAOManager ldm_manager;
		ldm_manager = DaoManagerFactory.getDAOManager();

		try
		{
			if(ale_parametros != null)
			{
				ale_parametros.setIdUsuarioCreacion(as_userId);
				ale_parametros.setIpCreacion(as_remoteIp);
				ale_parametros.setIdUsuarioModificacion(as_userId);
				ale_parametros.setIpModificacion(as_remoteIp);

				if(ab_accion)
				{
					LetraEje lle_tmp;

					lle_tmp = DaoCreator.getLetraEjeDAO(ldm_manager).findById(ale_parametros);

					if(lle_tmp == null)
						DaoCreator.getLetraEjeDAO(ldm_manager).insertOrUpdate(ale_parametros, ab_accion);
					else
						throw new B2BException(ErrorKeys.ERROR_ID_LETRA);
				}

				else
					DaoCreator.getLetraEjeDAO(ldm_manager).insertOrUpdate(ale_parametros, ab_accion);
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("salvarLetraEje", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}
	}

	/**
	 * Método para la transaccion con la base de datos con el fin de hacer inserciones o modificaciones de registros
	 * en la tabla SDB_PGN_LIBRO_ANT_SISTEMA.
	 *
	 * @param ac_parametros correspondiente al valor del tipo de objeto LibroAntiguoSistema
	 * @param ab_accion correspondiente al valor del tipo de objeto boolean
	 * @param as_usuario es el usuario que realiza la modificación o la inserción
	 * @param as_ipRemota es la ip de donde se ha realizado la acción de modificar o insertar
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public synchronized void salvarLibroAntiguoSistema(
	    LibroAntiguoSistema ac_parametros, boolean ab_accion, String as_usuario, String as_ipRemota
	)
	    throws B2BException
	{
		DAOManager ldm_manager;
		ldm_manager = DaoManagerFactory.getDAOManager();

		try
		{
			if(ac_parametros != null)
			{
				{
					long ll_idLibro;

					ll_idLibro = ac_parametros.getIdLibroAntiguoSistema();

					if(!NumericUtils.isValidLong(NumericUtils.getLongWrapper(ll_idLibro)) || (ll_idLibro == 0L))
						throw new B2BException(ErrorKeys.DEBE_SELECCIONAR_ID_LIBRO_ANTIGUO_SISTEMA);
				}

				{
					String ls_nombre;

					ls_nombre = ac_parametros.getNombre();

					if(!StringUtils.isValidString(ls_nombre))
						throw new B2BException(ErrorKeys.DEBE_DIGITAR_NOMBRE);
				}

				{
					String ls_activo;

					ls_activo = ac_parametros.getActivo();

					if(!StringUtils.isValidString(ls_activo))
						throw new B2BException(ErrorKeys.DEBE_SELECCIONAR_ACTIVO);
				}

				ac_parametros.setIpCreacion(as_ipRemota);
				ac_parametros.setIpModificacion(as_ipRemota);

				LibroAntiguoSistema llas_tmp;
				llas_tmp = DaoCreator.getLibroAntiguoSistemaDAO(ldm_manager).findById(ac_parametros);

				if(ab_accion)
				{
					if(llas_tmp == null)
						DaoCreator.getLibroAntiguoSistemaDAO(ldm_manager).insertOrUpdate(ac_parametros, ab_accion);
					else
						throw new B2BException(ErrorKeys.ERROR_ID_LIBRO_ANTIGUO_SISTEMA);
				}
				else
					DaoCreator.getLibroAntiguoSistemaDAO(ldm_manager).insertOrUpdate(ac_parametros, ab_accion);
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("salvarLibroAntiguoSistema", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}
	}

	/**
	 * Método para la transaccion con la base de datos con el fin de hacer inserciones o modificaciones de registros
	 * en la tabla SDB_PGN_LIBRO_TESTAMENTO.
	 *
	 * @param alt_parametros correspondiente al valor del tipo de objeto LibroTestamento
	 * @param ab_accion correspondiente al valor del tipo de objeto boolean
	 * @param as_usuario es el usuario que realiza la modificación o la inserción
	 * @param as_ipRemota es la ip de donde se ha realizado la acción de modificar o insertar
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public synchronized void salvarLibroTestamento(
	    LibroTestamento alt_parametros, boolean ab_accion, String as_usuario, String as_ipRemota
	)
	    throws B2BException
	{
		DAOManager ldm_manager;

		ldm_manager = DaoManagerFactory.getDAOManager();

		try
		{
			if(alt_parametros != null)
			{
				if(ab_accion)
				{
					alt_parametros.setIdUsuarioCreacion(as_usuario);
					alt_parametros.setIpCreacion(as_ipRemota);

					DaoCreator.getLibroTestamentoDAO(ldm_manager).insert(alt_parametros);
				}
				else
				{
					alt_parametros.setIdUsuarioModificacion(as_usuario);
					alt_parametros.setIpModificacion(as_ipRemota);

					DaoCreator.getLibroTestamentoDAO(ldm_manager).update(alt_parametros, true);
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("salvarLibroTestamento", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}
	}

	/**
	 * Método para salvar lo realizado ya sea una inserción o modificación de la tabla SDB_PGN_LINEA_PRODUCCION.
	 *
	 * @param alp_parametros objeto a insertar o modificar
	 * @param ab_accion indica si se desea insertar o modificar
	 * @param as_usuario usuario que realiza la acción de salvar
	 * @param as_ipRemota ip de donde se invoca el metodo de salvar
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public synchronized void salvarLineaProduccion(
	    LineaProduccion alp_parametros, boolean ab_accion, String as_usuario, String as_ipRemota
	)
	    throws B2BException
	{
		DAOManager         ldm_manager;
		LineaProduccionDAO llpd_DAO;
		TipoActoDAO        ltad_DAO;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		llpd_DAO        = DaoCreator.getLineaProduccionDAO(ldm_manager);
		ltad_DAO        = DaoCreator.getTipoActoDAO(ldm_manager);

		try
		{
			if(alp_parametros != null)
			{
				{
					String ls_nombre;
					ls_nombre = alp_parametros.getNombre();

					if(!StringUtils.isValidString(ls_nombre))
						throw new B2BException(ErrorKeys.DEBE_DIGITAR_NOMBRE_LINEA_PRODUCCION);
				}

				{
					long ll_peso;
					ll_peso = alp_parametros.getPeso();

					if(ll_peso < 0)
						throw new B2BException(ErrorKeys.DEBE_DIGITAR_PESO_LINEA_PRODUCCION);
				}

				{
					long ll_equivalenciaTurno;
					ll_equivalenciaTurno = alp_parametros.getEquivalenciaTurno();

					if(ll_equivalenciaTurno < 0)
						throw new B2BException(ErrorKeys.DEBE_DIGITAR_EQUIVALENCIA_TURNO_LINEA_PRODUCCION);
				}

				if(ab_accion)
				{
					alp_parametros.setIdUsuarioCreacion(as_usuario);
					alp_parametros.setIpCreacion(as_ipRemota);
				}
				else
				{
					alp_parametros.setIdUsuarioModificacion(as_usuario);
					alp_parametros.setIpModificacion(as_ipRemota);
				}

				alp_parametros = llpd_DAO.insertOrUpdate(alp_parametros, ab_accion);

				Collection<TipoActo> lcta_tipoActo;

				lcta_tipoActo = alp_parametros.getTiposActos();

				if(CollectionUtils.isValidCollection(lcta_tipoActo))
				{
					for(TipoActo lta_tmp : lcta_tipoActo)
					{
						if(lta_tmp != null)
						{
							if(lta_tmp.isActoSelected())
							{
								if(StringUtils.isValidString(lta_tmp.getLineaProduccion()))
								{
									LineaProduccion llp_linea;
									Object[]        loa_arg;

									loa_arg       = new String[1];
									llp_linea     = new LineaProduccion();
									llp_linea.setIdLineaProduccion(lta_tmp.getLineaProduccion());
									llp_linea      = llpd_DAO.findById(llp_linea);
									loa_arg[0]     = llp_linea.getNombre();

									throw new B2BException(ErrorKeys.ERROR_YA_TIENE_LINEA_PRODUCCION_ASOCIADA, loa_arg);
								}
								else
								{
									lta_tmp.setLineaProduccion(alp_parametros.getIdLineaProduccion());
									ltad_DAO.insertOrUpdate(lta_tmp, false);
								}
							}
							else
							{
								if(!ab_accion && StringUtils.isValidString(lta_tmp.getLineaProduccion()))
								{
									if(lta_tmp.getLineaProduccion().equals(alp_parametros.getIdLineaProduccion()))
									{
										lta_tmp.setLineaProduccion(null);
										ltad_DAO.insertOrUpdate(lta_tmp, false);
									}
									else
									{
										LineaProduccion llp_linea;
										Object[]        loa_arg;

										loa_arg       = new String[1];
										llp_linea     = new LineaProduccion();
										llp_linea.setIdLineaProduccion(lta_tmp.getLineaProduccion());

										llp_linea     = llpd_DAO.findById(llp_linea);

										loa_arg[0] = llp_linea.getNombre();

										throw new B2BException(
										    ErrorKeys.ERROR_YA_TIENE_LINEA_PRODUCCION_ASOCIADA, loa_arg
										);
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

			clh_LOGGER.error("salvarLineaProduccion", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}
	}

	/**
	 * Salvar medida area.
	 *
	 * @param atr_parametros correspondiente al valor del tipo de objeto MedidaArea
	 * @param ab_accion correspondiente al valor del tipo de objeto boolean
	 * @param as_userId correspondiente al valor del tipo de objeto String
	 * @param as_remoteIp correspondiente al valor del tipo de objeto String
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public synchronized void salvarMedidaArea(
	    MedidaArea atr_parametros, boolean ab_accion, String as_userId, String as_remoteIp
	)
	    throws B2BException
	{
		DAOManager ldm_manager;
		ldm_manager = DaoManagerFactory.getDAOManager();

		try
		{
			if(atr_parametros != null)
			{
				if(ab_accion)
				{
					atr_parametros.setIdUsuarioCreacion(as_userId);
					atr_parametros.setIpCreacion(as_remoteIp);
				}
				else
				{
					atr_parametros.setIdUsuarioModificacion(as_userId);
					atr_parametros.setIpModificacion(as_remoteIp);
				}

				MedidaArea ima_datos;
				ima_datos = null;

				if(ab_accion)
				{
					ima_datos = DaoCreator.getMedidaAreaDAO(ldm_manager).findById(atr_parametros);

					if(ima_datos == null)
						DaoCreator.getMedidaAreaDAO(ldm_manager).insertOrUpdate(atr_parametros, ab_accion);
					else
						throw new B2BException(ErrorKeys.ERROR_ID_MEDIDA_AREA);
				}

				else
					DaoCreator.getMedidaAreaDAO(ldm_manager).insertOrUpdate(atr_parametros, ab_accion);
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("salvarMedidaArea", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}
	}

	/**
	 * Salvar motivo tramite.
	 *
	 * @param atr_parametros correspondiente al valor del tipo de objeto MotivoTramite
	 * @param ab_accion correspondiente al valor del tipo de objeto boolean
	 * @param as_userId correspondiente al valor del tipo de objeto String
	 * @param as_remoteIp correspondiente al valor del tipo de objeto String
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public synchronized void salvarMotivoTramite(
	    MotivoTramite atr_parametros, boolean ab_accion, String as_userId, String as_remoteIp
	)
	    throws B2BException
	{
		DAOManager ldm_manager;

		ldm_manager = DaoManagerFactory.getDAOManagerWorkflow();

		try
		{
			if(atr_parametros != null)
			{
				if(ab_accion)
				{
					atr_parametros.setIdUsuarioCreacion(as_userId);
					atr_parametros.setIpCreacion(as_remoteIp);
				}
				else
				{
					atr_parametros.setIdUsuarioModificacion(as_userId);
					atr_parametros.setIpModificacion(as_remoteIp);
				}

				{
					MotivoTramite    icad_datos;
					MotivoTramiteDAO lmtDAO;

					icad_datos     = null;
					lmtDAO         = DaoCreator.getMotivoTramiteDAO(ldm_manager);

					if(ab_accion)
					{
						icad_datos = lmtDAO.findById(atr_parametros);

						if(icad_datos == null)
							lmtDAO.insertOrUpdate(atr_parametros, ab_accion);
						else
							throw new B2BException(ErrorKeys.ERROR_MOTIVO_TRAMITE);
					}

					else
						lmtDAO.insertOrUpdate(atr_parametros, ab_accion);
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("salvarMotivoTramite", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}
	}

	/**
	 * Método para salvar lo realizado ya sea una inserción o modificación de la tabla SDB_PGN_MUNICIPIO.
	 *
	 * @param ac_parametros objeto a insertar o modificar
	 * @param ab_accion indica si se desea insertar o modificar
	 * @param as_usuario usuario que realiza la acción de salvar
	 * @param as_ipRemota ip de donde se invoca el metodo de salvar
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public synchronized void salvarMunicipio(
	    Municipio ac_parametros, boolean ab_accion, String as_usuario, String as_ipRemota
	)
	    throws B2BException
	{
		DAOManager ldm_manager;
		ldm_manager = DaoManagerFactory.getDAOManager();

		try
		{
			if(ac_parametros != null)
			{
				{
					String ls_idPais;
					ls_idPais = ac_parametros.getIdPais();

					if(!StringUtils.isValidString(ls_idPais))
						throw new B2BException(ErrorKeys.DEBE_DIGITAR_ID_PAIS);
				}

				{
					String ls_idDepartamento;
					ls_idDepartamento = ac_parametros.getIdDepartamento();

					if(!StringUtils.isValidString(ls_idDepartamento))
						throw new B2BException(ErrorKeys.DEBE_DIGITAR_ID_DEPARTAMENTO);
				}

				{
					String ls_idMunicipio;
					ls_idMunicipio = ac_parametros.getIdMunicipio();

					if(!StringUtils.isValidString(ls_idMunicipio))
						throw new B2BException(ErrorKeys.DEBE_DIGITAR_ID_MUNICIPIO);
				}

				{
					String ls_nombre;
					ls_nombre = ac_parametros.getNombre();

					if(!StringUtils.isValidString(ls_nombre))
						throw new B2BException(ErrorKeys.DEBE_DIGITAR_NOMBRE);
				}

				{
					String ls_nombre;
					ls_nombre = ac_parametros.getImplementadoNupre();

					if(!StringUtils.isValidString(ls_nombre))
						throw new B2BException(ErrorKeys.DEBE_DIGITAR_IMPLEMENTADO_NUPRE);
				}

				{
					String ls_activo;
					ls_activo = ac_parametros.getActivo();

					if(!StringUtils.isValidString(ls_activo))
						throw new B2BException(ErrorKeys.DEBE_DIGITAR_ACTIVO);
				}

				Municipio lm_tmp;
				lm_tmp = DaoCreator.getMunicipioDAO(ldm_manager).findById(ac_parametros);

				if(ab_accion)
				{
					if(lm_tmp == null)
					{
						ac_parametros.setIpCreacion(as_ipRemota);
						ac_parametros.setIdUsuarioCreacion(as_usuario);
						DaoCreator.getMunicipioDAO(ldm_manager).insertOrUpdate(ac_parametros, ab_accion);
					}
					else
						throw new B2BException(ErrorKeys.ERROR_ID_MUNICIPIO_EXISTE);
				}
				else
				{
					ac_parametros.setIpModificacion(as_ipRemota);
					ac_parametros.setIdUsuarioModificacion(as_usuario);
					DaoCreator.getMunicipioDAO(ldm_manager).insertOrUpdate(ac_parametros, ab_accion);
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("salvarMunicipio", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}
	}

	/**
	 * Método para salvar la insercion o modificacion para la tabla SDB_PNG_NATURALEZA_JURIDICA.
	 *
	 * @param anj_parametros objeto que se desea salvar
	 * @param ab_accion indica si se va a insertar o modificar
	 * @param as_userId usuario en sesion que realiza alguna accion(modificar o insertar)
	 * @param as_remoteIp ip del usuario en sesion
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public synchronized void salvarNaturalezaJuridica(
	    NaturalezaJuridica anj_parametros, boolean ab_accion, String as_userId, String as_remoteIp
	)
	    throws B2BException
	{
		DAOManager ldm_manager;
		ldm_manager = DaoManagerFactory.getDAOManager();

		try
		{
			if(anj_parametros != null)
			{
				if(ab_accion)
				{
					anj_parametros.setIdUsuarioCreacion(as_userId);
					anj_parametros.setIpCreacion(as_remoteIp);
				}
				else
				{
					anj_parametros.setIdUsuarioModificacion(as_userId);
					anj_parametros.setIpModificacion(as_remoteIp);
				}

				NaturalezaJuridica inj_datos;
				inj_datos = null;

				if(ab_accion)
				{
					inj_datos = DaoCreator.getNaturalezaJuridicaDAO(ldm_manager).findById(anj_parametros);

					if(inj_datos == null)
						DaoCreator.getNaturalezaJuridicaDAO(ldm_manager).insertOrUpdate(anj_parametros, ab_accion);
					else
						throw new B2BException(ErrorKeys.ERROR_CALIDAD_SOLICITANTE_REPETIDO);
				}

				else
					DaoCreator.getNaturalezaJuridicaDAO(ldm_manager).insertOrUpdate(anj_parametros, ab_accion);
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("salvarNaturalezaJuridica", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}
	}

	/**
	 * Método para la transaccion con la base de datos con el fin de hacer inserciones o modificaciones de registros
	 * en la tabla SDB_PGN_NUMERACION_OFICIOS_CIRCULO.
	 *
	 * @param anoc_parametros correspondiente al valor del tipo de objeto NumeracionOficiosCirculo
	 * @param ab_accion correspondiente al valor del tipo de objeto boolean
	 * @param as_usuario es el usuario que realiza la modificación o la inserción
	 * @param as_ipRemota es la ip de donde se ha realizado la acción de modificar o insertar
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public synchronized void salvarNumeracionOficiosCirculo(
	    NumeracionOficiosCirculo anoc_parametros, boolean ab_accion, String as_usuario, String as_ipRemota
	)
	    throws B2BException
	{
		DAOManager ldm_manager;

		ldm_manager = DaoManagerFactory.getDAOManager();

		try
		{
			if(anoc_parametros != null)
			{
				if(ab_accion)
				{
					anoc_parametros.setIdUsuarioCreacion(as_usuario);
					anoc_parametros.setIpCreacion(as_ipRemota);

					DaoCreator.getNumeracionOficiosCirculoDAO(ldm_manager).insert(anoc_parametros);
				}
				else
				{
					anoc_parametros.setIdUsuarioModificacion(as_usuario);
					anoc_parametros.setIpModificacion(as_ipRemota);

					DaoCreator.getNumeracionOficiosCirculoDAO(ldm_manager).update(anoc_parametros);
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("salvarNumeracionOficiosCirculo", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}
	}

	/**
	 * Salvar oficina origen.
	 *
	 * @param atr_parametros correspondiente al valor del tipo de objeto OficinaOrigen
	 * @param ab_accion correspondiente al valor del tipo de objeto boolean
	 * @param as_userId correspondiente al valor del tipo de objeto String
	 * @param as_remoteIp correspondiente al valor del tipo de objeto String
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public synchronized void salvarOficinaOrigen(
	    OficinaOrigen atr_parametros, boolean ab_accion, String as_userId, String as_remoteIp
	)
	    throws B2BException
	{
		DAOManager ldm_manager;

		ldm_manager = DaoManagerFactory.getDAOManager();

		try
		{
			if(atr_parametros != null)
			{
				OficinaOrigenDAO lood_DAO;

				lood_DAO = DaoCreator.getOficinaOrigenDAO(ldm_manager);

				if(ab_accion)
				{
					atr_parametros.setIdUsuarioCreacion(as_userId);
					atr_parametros.setIpCreacion(as_remoteIp);

					lood_DAO.insertOrUpdate(atr_parametros, ab_accion);
				}
				else
				{
					atr_parametros.setIdUsuarioModificacion(as_userId);
					atr_parametros.setIpModificacion(as_remoteIp);

					lood_DAO.insertOrUpdate(atr_parametros, ab_accion);
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("salvarOficinaOrigen", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}
	}

	/**
	 * Método para salvar lo realizado ya sea una inserción de Opción Etapa.
	 *
	 * @param aoe_parametros de aoe parametros
	 * @param as_usuario            usuario que realiza la acción de salvar
	 * @param as_ip            ip de donde se invoca el metodo de salvar
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public synchronized void salvarOpcionEtapa(OpcionEtapa aoe_parametros, String as_usuario, String as_ip)
	    throws B2BException
	{
		DAOManager ldm_manager;
		ldm_manager = DaoManagerFactory.getDAOManager();

		try
		{
			if(aoe_parametros != null)
			{
				{
					long ll_idEtapa;

					ll_idEtapa = aoe_parametros.getIdEtapa();

					if(ll_idEtapa <= 0)
						throw new B2BException(ErrorKeys.DEBE_SELECCIONAR_ETAPA);
				}

				{
					String ls_idOpcion;

					ls_idOpcion = aoe_parametros.getIdOpcion();

					if(!StringUtils.isValidString(ls_idOpcion))
						throw new B2BException(ErrorKeys.ERROR_SELECCIONE_OPCION);
				}

				aoe_parametros.setIdUsuarioCreacion(as_usuario);
				aoe_parametros.setIpCreacion(as_ip);

				DaoCreator.getOpcionEtapaDAO(ldm_manager).insert(aoe_parametros);
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("salvarOpcionEtapa", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}
	}

	/**
	 * Método para salvar la inserción de la
	 * tabla SDB_AUT_OPCION.
	 *
	 * @param ao_parametros            objeto a insertar o modificar
	 * @param as_usuario            usuario que realiza la acción de salvar
	 * @param as_remoteIp            ip de donde se invoca el metodo de salvar
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public synchronized void salvarOpcionInsertar(Opcion ao_parametros, String as_usuario, String as_remoteIp)
	    throws B2BException
	{
		DAOManager ldm_manager;
		ldm_manager = DaoManagerFactory.getDAOManager();

		try
		{
			if(ao_parametros != null)
			{
				String ls_validador;

				{
					ls_validador = ao_parametros.getOpcion();

					if(!StringUtils.isValidString(ls_validador))
						throw new B2BException(ErrorKeys.DEBE_DIGITAR_OPCION);
				}

				{
					ls_validador = ao_parametros.getDescripcion();

					if(!StringUtils.isValidString(ls_validador))
						throw new B2BException(ErrorKeys.DEBE_DIGITAR_DESCRIPCION);
				}

				{
					ls_validador = ao_parametros.getTipo();

					if(!StringUtils.isValidString(ls_validador))
						throw new B2BException(ErrorKeys.ERROR_DEBE_ELEGIR_TIPO);
				}

				{
					Date ld_fechaDesde;
					ld_fechaDesde = ao_parametros.getFechaDesde();

					if(!(ld_fechaDesde != null))
						throw new B2BException(ErrorKeys.ERROR_FECHA_DESDE);
				}

				{
					ls_validador = ao_parametros.getActivo();

					if(!StringUtils.isValidString(ls_validador))
						throw new B2BException(ErrorKeys.DEBE_DIGITAR_ACTIVO);
				}

				ao_parametros.setIdUsuarioCreacion(as_usuario);
				ao_parametros.setIpCreacion(as_remoteIp);

				DaoCreator.getOpcionDAO(ldm_manager).insert(ao_parametros);
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("salvarOpcionInsertar", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}
	}

	/**
	 * Método para salvar la modificación de la
	 * tabla SDB_AUT_OPCION.
	 *
	 * @param ao_parametros            objeto a insertar o modificar
	 * @param ace_opcionEtapa            collection a insertar o eliminar
	 * @param as_usuario            usuario que realiza la acción de salvar
	 * @param as_remoteIp            ip de donde se invoca el metodo de salvar
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public synchronized void salvarOpcionModificar(
	    Opcion ao_parametros, Collection<Etapa> ace_opcionEtapa, String as_usuario, String as_remoteIp
	)
	    throws B2BException
	{
		DAOManager ldm_manager;
		ldm_manager = DaoManagerFactory.getDAOManager();

		try
		{
			if(ao_parametros != null)
			{
				String ls_validador;

				{
					ls_validador = ao_parametros.getOpcion();

					if(!StringUtils.isValidString(ls_validador))
						throw new B2BException(ErrorKeys.DEBE_DIGITAR_OPCION);
				}

				{
					ls_validador = ao_parametros.getDescripcion();

					if(!StringUtils.isValidString(ls_validador))
						throw new B2BException(ErrorKeys.DEBE_DIGITAR_DESCRIPCION);
				}

				{
					ls_validador = ao_parametros.getTipo();

					if(!StringUtils.isValidString(ls_validador))
						throw new B2BException(ErrorKeys.ERROR_DEBE_ELEGIR_TIPO);
				}

				{
					Date ld_fechaDesde;
					ld_fechaDesde = ao_parametros.getFechaDesde();

					if(!(ld_fechaDesde != null))
						throw new B2BException(ErrorKeys.ERROR_FECHA_DESDE);
				}

				{
					ls_validador = ao_parametros.getActivo();

					if(!StringUtils.isValidString(ls_validador))
						throw new B2BException(ErrorKeys.DEBE_DIGITAR_ACTIVO);
				}

				ao_parametros.setIdUsuarioModificacion(as_usuario);
				ao_parametros.setIpModificacion(as_remoteIp);

				DaoCreator.getOpcionDAO(ldm_manager).update(ao_parametros);
				salvarOpcionEtapas(ldm_manager, ao_parametros, ace_opcionEtapa, as_remoteIp, as_usuario);
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("salvarOpcionInsertar", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}
	}

	/**
	 * Método para salvar la insercion o modificacion para la tabla SDB_PGN_ORIGEN.
	 *
	 * @param ao_parametros objeto que se desea salvar
	 * @param ab_insertar de ab insertar
	 * @param as_userId usuario en sesion que realiza alguna accion(modificar o insertar)
	 * @param as_remoteIp ip del usuario en sesion
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public synchronized void salvarOrigen(
	    Origen ao_parametros, boolean ab_insertar, String as_userId, String as_remoteIp
	)
	    throws B2BException
	{
		DAOManager ldm_manager;
		ldm_manager = DaoManagerFactory.getDAOManagerCYN();

		try
		{
			if(ao_parametros != null)
			{
				if(ab_insertar)
				{
					ao_parametros.setIdUsuarioCreacion(as_userId);
					ao_parametros.setIpCreacion(as_remoteIp);

					DaoCreator.getOrigenDAO(ldm_manager).insert(ao_parametros);
				}
				else
				{
					ao_parametros.setIdUsuarioModificacion(as_userId);
					ao_parametros.setIpModificacion(as_remoteIp);

					DaoCreator.getOrigenDAO(ldm_manager).update(ao_parametros);
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("salvarOrigen", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}
	}

	/**
	 * Método para salvar lo realizado ya sea una inserción o modificación de la tabla SDB_PGN_PAIS.
	 *
	 * @param ac_parametros objeto a insertar o modificar
	 * @param ab_accion indica si se desea insertar o modificar
	 * @param as_usuario usuario que realiza la acción de salvar
	 * @param as_ipRemota ip de donde se invoca el metodo de salvar
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public synchronized void salvarPais(Pais ac_parametros, boolean ab_accion, String as_usuario, String as_ipRemota)
	    throws B2BException
	{
		DAOManager ldm_manager;
		ldm_manager = DaoManagerFactory.getDAOManager();

		try
		{
			if(ac_parametros != null)
			{
				{
					String ls_idPais;
					ls_idPais = ac_parametros.getIdPais();

					if(!StringUtils.isValidString(ls_idPais))
						throw new B2BException(ErrorKeys.DEBE_DIGITAR_ID_PAIS);
				}

				{
					String ls_nombre;
					ls_nombre = ac_parametros.getNombre();

					if(!StringUtils.isValidString(ls_nombre))
						throw new B2BException(ErrorKeys.DEBE_DIGITAR_NOMBRE);
				}

				{
					String ls_activo;
					ls_activo = ac_parametros.getActivo();

					if(!StringUtils.isValidString(ls_activo))
						throw new B2BException(ErrorKeys.DEBE_DIGITAR_ACTIVO);
				}

				Pais lp_tmp;
				lp_tmp = DaoCreator.getPaisDAO(ldm_manager).findById(ac_parametros);

				if(ab_accion)
				{
					if(lp_tmp == null)
					{
						ac_parametros.setIdUsuarioCreacion(as_usuario);
						ac_parametros.setIpCreacion(as_ipRemota);
						DaoCreator.getPaisDAO(ldm_manager).insertOrUpdate(ac_parametros, ab_accion);
					}
					else
						throw new B2BException(ErrorKeys.ERROR_ID_PAIS_EXISTE);
				}
				else
				{
					ac_parametros.setIdUsuarioModificacion(as_usuario);
					ac_parametros.setIpModificacion(as_ipRemota);
					DaoCreator.getPaisDAO(ldm_manager).insertOrUpdate(ac_parametros, ab_accion);
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("salvarPais", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}
	}

	/**
	 * Método para salvar lo realizado ya sea una inserción o modificación de la tabla SDB_COL_PARTE_INTERESADA.
	 *
	 * @param ac_parametros objeto a insertar o modificar
	 * @param ab_accion indica si se desea insertar o modificar
	 * @param as_usuario usuario que realiza la acción de salvar
	 * @param as_ipRemota ip de donde se invoca el metodo de salvar
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public synchronized void salvarParteInteresada(
	    ParteInteresada ac_parametros, boolean ab_accion, String as_usuario, String as_ipRemota
	)
	    throws B2BException
	{
		DAOManager ldm_manager;
		ldm_manager = DaoManagerFactory.getDAOManager();

		try
		{
			if(ac_parametros != null)
			{
				{
					String ls_Ilicode;
					ls_Ilicode = ac_parametros.getIlicode();

					if(!StringUtils.isValidString(ls_Ilicode))
						throw new B2BException(ErrorKeys.DEBE_DIGITAR_ILICODE);
				}

				{
					String ls_descripcion;
					ls_descripcion = ac_parametros.getDescripcion();

					if(!StringUtils.isValidString(ls_descripcion))
						throw new B2BException(ErrorKeys.DEBE_DIGITAR_DESCRIPCION);
				}

				{
					String ls_activo;
					ls_activo = ac_parametros.getActivo();

					if(!StringUtils.isValidString(ls_activo))
						throw new B2BException(ErrorKeys.DEBE_DIGITAR_ACTIVO);
				}

				ac_parametros.setIpCreacion(as_ipRemota);
				ac_parametros.setIdUsuarioCreacion(as_usuario);
				ac_parametros.setIpModificacion(as_ipRemota);
				ac_parametros.setIdUsuarioModificacion(as_usuario);

				ParteInteresada lp_tmp;
				lp_tmp = DaoCreator.getParteInteresadaDAO(ldm_manager).findById(ac_parametros);

				if(ab_accion)
				{
					if(lp_tmp == null)
						DaoCreator.getParteInteresadaDAO(ldm_manager).insertOrUpdate(ac_parametros, ab_accion);
					else
						throw new B2BException(ErrorKeys.ERROR_ID_PARTE_INTERESADA_EXISTE);
				}
				else
					DaoCreator.getParteInteresadaDAO(ldm_manager).insertOrUpdate(ac_parametros, ab_accion);
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("salvarParteInteresada", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}
	}

	/**
	 * Método para salvar la insercion o modificacion para la tabla SDB_PGN_PLANTILLA.
	 *
	 * @param ap_parametros objeto que se desea salvar
	 * @param ab_insertar de ab insertar
	 * @param as_userId usuario en sesion que realiza alguna accion(modificar o insertar)
	 * @param as_remoteIp ip del usuario en sesion
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public synchronized void salvarPlantilla(
	    Plantilla ap_parametros, boolean ab_insertar, String as_userId, String as_remoteIp
	)
	    throws B2BException
	{
		DAOManager ldm_manager;
		ldm_manager = DaoManagerFactory.getDAOManagerCYN();

		try
		{
			if(ap_parametros != null)
			{
				if(ab_insertar)
				{
					ap_parametros.setIdUsuarioCreacion(as_userId);
					ap_parametros.setIpCreacion(as_remoteIp);

					DaoCreator.getPlantillaDAO(ldm_manager).insert(ap_parametros);
				}
				else
				{
					ap_parametros.setIdUsuarioModificacion(as_userId);
					ap_parametros.setIpModificacion(as_remoteIp);

					DaoCreator.getPlantillaDAO(ldm_manager).update(ap_parametros);
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("salvarPlantilla", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}
	}

	/**
	 * Método para salvar la inserción o modificación para la tabla SDB_PGN_PLANTILLA_COMUNICACION.
	 *
	 * @param apc_parametros objeto de tipo <code>PlantillaComunicacion</code> que se desea salvar
	 * @param ab_insertar variable de tipo <code>boolean</code> que indica si la operacion es una inserción
	 * @param as_userId usuario en sesión que realiza alguna accion(modificar o insertar)
	 * @param as_remoteIp ip del usuario en sesión
	 * @throws B2BException Objeto de tipo <code>B2BException</code>, se produce cuando se encuentra algun error controlado.
	 */
	public synchronized void salvarPlantillaComunicacion(
	    PlantillaComunicacion apc_parametros, boolean ab_insertar, String as_userId, String as_remoteIp
	)
	    throws B2BException
	{
		DAOManager ldm_manager;
		ldm_manager = DaoManagerFactory.getDAOManager();

		try
		{
			if(apc_parametros != null)
			{
				{
					String ls_idProceso;

					ls_idProceso = apc_parametros.getIdProceso();

					if(!StringUtils.isValidString(ls_idProceso))
						throw new B2BException(ErrorKeys.ERROR_DEBE_SELECCIONAR_ID_PROCESO);
				}

				{
					String ls_idPlantilla;

					ls_idPlantilla = apc_parametros.getIdPlantilla();

					if(!StringUtils.isValidString(ls_idPlantilla))
						throw new B2BException(ErrorKeys.ERROR_DEBE_SELECCIONAR_ID_PLANTILLA);
				}

				{
					long ll_idEtapaActual;

					ll_idEtapaActual = apc_parametros.getIdEtapaActual();

					if(ll_idEtapaActual <= 0)
						throw new B2BException(ErrorKeys.ERROR_DEBE_SELECCIONAR_ID_ETAPA_ACTUAL);
				}

				{
					String ls_idAutorizacion;

					ls_idAutorizacion = apc_parametros.getIdAutorizacionComunicacion();

					if(!StringUtils.isValidString(ls_idAutorizacion))
						throw new B2BException(ErrorKeys.ERROR_DEBE_SELECCIONAR_ID_AUTORIZACION_COMUNICACION);
				}

				if(ab_insertar)
				{
					apc_parametros.setIdUsuarioCreacion(as_userId);
					apc_parametros.setIpCreacion(as_remoteIp);

					DaoCreator.getPlantillaComunicacionDAO(ldm_manager).insertComplete(apc_parametros);
				}
				else
				{
					apc_parametros.setIdUsuarioModificacion(as_userId);
					apc_parametros.setIpModificacion(as_remoteIp);

					DaoCreator.getPlantillaComunicacionDAO(ldm_manager).updateComplete(apc_parametros);
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("salvarPlantillaComunicacion", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}
	}

	/**
	 * Método para salvar la insercion o modificacion para la tabla SDB_PGN_PLANTILLA_NOTIFICACION.
	 *
	 * @param apn_parametros objeto de tipo <code>PlantillaNotificacion</code> que se desea salvar
	 * @param ab_insertar variable de tipo <code>boolean</code> que indica si la operacion es una inserción
	 * @param as_userId usuario en sesión que realiza alguna accion(modificar o insertar)
	 * @param as_remoteIp ip del usuario en sesión
	 * @throws B2BException Objeto de tipo <code>B2BException</code>, se produce cuando se encuentra algun error controlado.
	 */
	public synchronized void salvarPlantillaNotificacion(
	    PlantillaNotificacion apn_parametros, boolean ab_insertar, String as_userId, String as_remoteIp
	)
	    throws B2BException
	{
		DAOManager ldm_manager;
		ldm_manager = DaoManagerFactory.getDAOManager();

		try
		{
			if(apn_parametros != null)
			{
				{
					String ls_idProceso;

					ls_idProceso = apn_parametros.getIdProceso();

					if(!StringUtils.isValidString(ls_idProceso))
						throw new B2BException(ErrorKeys.ERROR_DEBE_SELECCIONAR_ID_PROCESO);
				}

				{
					long ll_idEtapaAnterior;

					ll_idEtapaAnterior = apn_parametros.getIdEtapaAnterior();

					if(ll_idEtapaAnterior <= 0)
						throw new B2BException(ErrorKeys.ERROR_DEBE_SELECCIONAR_ID_ETAPA_ACTUAL);
				}

				{
					long ll_idMotivo;

					ll_idMotivo = apn_parametros.getIdMotivo();

					if(ll_idMotivo <= 0)
						throw new B2BException(ErrorKeys.ERROR_DEBE_SELECCIONAR_ID_MOTIVO);
				}

				{
					String ls_idPlantilla;

					ls_idPlantilla = apn_parametros.getIdPlantilla();

					if(!StringUtils.isValidString(ls_idPlantilla))
						throw new B2BException(ErrorKeys.ERROR_DEBE_SELECCIONAR_ID_PLANTILLA);
				}

				{
					String ls_clasificacion;

					ls_clasificacion = apn_parametros.getClasificacion();

					if(!StringUtils.isValidString(ls_clasificacion))
						throw new B2BException(ErrorKeys.DEBE_SELECCIONAR_CLASIFICACION);
				}

				if(ab_insertar)
				{
					apn_parametros.setIdUsuarioCreacion(as_userId);
					apn_parametros.setIpCreacion(as_remoteIp);

					DaoCreator.getPlantillaNotificacionDAO(ldm_manager).insert(apn_parametros);
				}
				else
				{
					apn_parametros.setIdUsuarioModificacion(as_userId);
					apn_parametros.setIpModificacion(as_remoteIp);

					DaoCreator.getPlantillaNotificacionDAO(ldm_manager).update(apn_parametros);
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("salvarPlantillaNotificacion", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}
	}

	/**
	 * Método para guardar los cambios o la inserción de un nuevo o modificado proceso.
	 *
	 * @param ap_parametros de ap parametros
	 * @param ab_accion de ab accion
	 * @param as_userId de as user id
	 * @param as_remoteIp de as remote ip
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public synchronized void salvarProceso(
	    Proceso ap_parametros, boolean ab_accion, String as_userId, String as_remoteIp
	)
	    throws B2BException
	{
		DAOManager ldm_manager;
		ldm_manager = DaoManagerFactory.getDAOManager();

		try
		{
			if(ap_parametros != null)
			{
				{
					String ls_idProceso;

					ls_idProceso = ap_parametros.getIdProceso();

					if(!StringUtils.isValidString(ls_idProceso))
						throw new B2BException(ErrorKeys.ID_PROCESO_INVALIDO);
				}

				{
					String ls_nombre;

					ls_nombre = ap_parametros.getNombre();

					if(!StringUtils.isValidString(ls_nombre))
						throw new B2BException(ErrorKeys.DEBE_DIGITAR_NOMBRE);
				}

				{
					String ls_activo;

					ls_activo = ap_parametros.getActivo();

					if(!StringUtils.isValidString(ls_activo))
						throw new B2BException(ErrorKeys.DEBE_DIGITAR_ACTIVO);
				}

				ap_parametros.setIdUsuarioCreacion(as_userId);
				ap_parametros.setIpCreacion(as_remoteIp);
				ap_parametros.setIdUsuarioModificacion(as_userId);
				ap_parametros.setIpModificacion(as_remoteIp);

				if(ab_accion)
				{
					Proceso lc_tmp;

					lc_tmp = DaoCreator.getProcesoDAO(ldm_manager).findById(ap_parametros);

					if(lc_tmp == null)
						DaoCreator.getProcesoDAO(ldm_manager).insertOrUpdate(ap_parametros, ab_accion);
					else
						throw new B2BException(ErrorKeys.ID_PROCESO_INVALIDO);
				}

				else
					DaoCreator.getProcesoDAO(ldm_manager).insertOrUpdate(ap_parametros, ab_accion);
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("salvarProceso", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}
	}

	/**
	 * Método para la transaccion con la base de datos con el fin de hacer inserciones o modificaciones de registros
	 * en la tabla SDB_PGN_PROCESO_AUTOMATICO.
	 *
	 * @param ac_parametros correspondiente al valor del tipo de objeto ProcesoAutomatico
	 * @param ab_accion correspondiente al valor del tipo de objeto boolean
	 * @param as_usuario es el usuario que realiza la modificación o la inserción
	 * @param as_ipRemota es la ip de donde se ha realizado la acción de modificar o insertar
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public synchronized void salvarProcesoAutomatico(
	    ProcesoAutomatico ac_parametros, boolean ab_accion, String as_usuario, String as_ipRemota
	)
	    throws B2BException
	{
		DAOManager ldm_manager;
		ldm_manager = DaoManagerFactory.getDAOManager();

		try
		{
			if(ac_parametros != null)
			{
				{
					String ls_nombreProceso;
					ls_nombreProceso = ac_parametros.getNombreProceso();

					if(!StringUtils.isValidString(ls_nombreProceso))
						throw new B2BException(ErrorKeys.DEBE_DIGITAR_NOMBRE_PROCESO);
				}

				{
					String ls_descripcion;
					ls_descripcion = ac_parametros.getDescripcion();

					if(!StringUtils.isValidString(ls_descripcion))
						throw new B2BException(ErrorKeys.DEBE_DIGITAR_DESCRIPCION);
				}

				{
					String ls_nombre;
					ls_nombre = ac_parametros.getNombreProcAlmacenado();

					if(!StringUtils.isValidString(ls_nombre))
						throw new B2BException(ErrorKeys.DEBE_DIGITAR_NOMBRE_PROC_ALMACENADO);
				}

				{
					String ls_activo;
					ls_activo = ac_parametros.getEstado();

					if(!StringUtils.isValidString(ls_activo))
						throw new B2BException(ErrorKeys.DEBE_DIGITAR_ACTIVO);
				}

				ac_parametros.setIpCreacion(as_ipRemota);
				ac_parametros.setIpModificacion(as_ipRemota);

				ProcesoAutomatico lpa_tmp;
				lpa_tmp = DaoCreator.getProcesoAutomaticoDAO(ldm_manager).findById(ac_parametros);

				if(ab_accion)
				{
					if(lpa_tmp == null)
						DaoCreator.getProcesoAutomaticoDAO(ldm_manager).insertOrUpdate(ac_parametros, ab_accion);
					else
						throw new B2BException(ErrorKeys.ERROR_ID_PROCESO_AUTOMATICO);
				}
				else
					DaoCreator.getProcesoAutomaticoDAO(ldm_manager).insertOrUpdate(ac_parametros, ab_accion);
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("salvarProcesoAutomatico", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}
	}

	/**
	 * Método para guardar la inserción o modificación de datos de proceso Canal.
	 *
	 * @param apco_parametros de apco parametros
	 * @param ab_accion de ab accion
	 * @param as_usuario de as usuario
	 * @param as_ip de as ip
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public synchronized void salvarProcesoCanal(
	    ProcesoCanal apco_parametros, boolean ab_accion, String as_usuario, String as_ip
	)
	    throws B2BException
	{
		DAOManager ldm_manager;
		ldm_manager = DaoManagerFactory.getDAOManager();

		try
		{
			if(apco_parametros != null)
			{
				{
					String ls_idTipoCanalOrigen;

					ls_idTipoCanalOrigen = apco_parametros.getIdTipoCanalOrigen();

					if(!StringUtils.isValidString(ls_idTipoCanalOrigen))
						throw new B2BException(ErrorKeys.ERROR_DEBE_ELEGIR_TIPO_CANAL_ORIGEN);
				}

				{
					String ls_idProceso;

					ls_idProceso = apco_parametros.getIdProceso();

					if(!StringUtils.isValidString(ls_idProceso))
						throw new B2BException(ErrorKeys.DEBE_SELECCIONAR_PROCESO);
				}

				{
					String ls_idSubProceso;

					ls_idSubProceso = apco_parametros.getIdSubProceso();

					if(!StringUtils.isValidString(ls_idSubProceso))
						throw new B2BException(ErrorKeys.DEBE_SELECCIONAR_SUBPROCESO);
				}

				{
					String ls_activo;

					ls_activo = apco_parametros.getActivo();

					if(!StringUtils.isValidString(ls_activo))
						throw new B2BException(ErrorKeys.DEBE_DIGITAR_ACTIVO);
				}

				if(ab_accion)
				{
					apco_parametros.setIdUsuarioCreacion(as_usuario);
					apco_parametros.setIpCreacion(as_ip);

					DaoCreator.getProcesoCanalDAO(ldm_manager).insert(apco_parametros);
				}
				else
				{
					apco_parametros.setIdUsuarioModificacion(as_usuario);
					apco_parametros.setIpModificacion(as_ip);

					DaoCreator.getProcesoCanalDAO(ldm_manager).update(apco_parametros);
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("salvarProcesoCanal", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}
	}

	/**
	 * Método para salvar la insercion o modificacion para la tabla SDB_PGN_PROCESO_CONSULTA.
	 *
	 * @param apc_parametros objeto que se desea salvar
	 * @param ab_accion indica si se va a insertar o modificar
	 * @param as_userId usuario en sesion que realiza alguna accion(modificar o insertar)
	 * @param as_remoteIp ip del usuario en sesion
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public synchronized void salvarProcesoConsulta(
	    ProcesoConsulta apc_parametros, boolean ab_accion, String as_userId, String as_remoteIp
	)
	    throws B2BException
	{
		DAOManager ldm_manager;
		ldm_manager = DaoManagerFactory.getDAOManager();

		try
		{
			if(apc_parametros != null)
			{
				if(ab_accion)
				{
					apc_parametros.setIdUsuarioCreacion(as_userId);
					apc_parametros.setIpCreacion(as_remoteIp);
				}
				else
				{
					apc_parametros.setIdUsuarioModificacion(as_userId);
					apc_parametros.setIpModificacion(as_remoteIp);
				}

				ProcesoConsulta ipc_datos;
				ipc_datos = null;

				if(ab_accion)
				{
					ipc_datos = DaoCreator.getProcesoConsultaDAO(ldm_manager).findById(apc_parametros);

					if(ipc_datos == null)
						DaoCreator.getProcesoConsultaDAO(ldm_manager).insertOrUpdate(apc_parametros, ab_accion);
					else
						throw new B2BException(ErrorKeys.ERROR_PROCESO_CONSULTA_REPETIDO);
				}

				else
					DaoCreator.getProcesoConsultaDAO(ldm_manager).insertOrUpdate(apc_parametros, ab_accion);
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("salvarProcesoConsulta", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}
	}

	/**
	 * Método para salvar lo realizado ya sea una inserción o modificación de la tabla SDB_COL_PARTE_INTERESADA.
	 *
	 * @param ac_parametros  objeto a insertar o modificar
	 * @param ab_accion indica si se desea insertar o modificar
	 * @param as_usuario usuario que realiza la acción de salvar
	 * @param as_ipRemota ip de donde se invoca el metodo de salvar
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public synchronized void salvarRegional(
	    Regional ac_parametros, boolean ab_accion, String as_usuario, String as_ipRemota
	)
	    throws B2BException
	{
		DAOManager ldm_manager;
		ldm_manager = DaoManagerFactory.getDAOManager();

		try
		{
			if(ac_parametros != null)
			{
				{
					String ls_nombre;
					ls_nombre = ac_parametros.getNombre();

					if(!StringUtils.isValidString(ls_nombre))
						throw new B2BException(ErrorKeys.DEBE_DIGITAR_NOMBRE);
				}

				{
					String ls_activo;
					ls_activo = ac_parametros.getActivo();

					if(!StringUtils.isValidString(ls_activo))
						throw new B2BException(ErrorKeys.DEBE_DIGITAR_ACTIVO);
				}

				ac_parametros.setIpCreacion(as_ipRemota);
				ac_parametros.setIpModificacion(as_ipRemota);

				Regional lp_tmp;
				lp_tmp = DaoCreator.getRegionalDAO(ldm_manager).findById(ac_parametros);

				if(ab_accion)
				{
					if(lp_tmp == null)
						DaoCreator.getRegionalDAO(ldm_manager).insertOrUpdate(ac_parametros, ab_accion);
					else
						throw new B2BException(ErrorKeys.ERROR_ID_REGIONAL_EXISTE);
				}
				else
					DaoCreator.getRegionalDAO(ldm_manager).insertOrUpdate(ac_parametros, ab_accion);
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("salvarRegional", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}
	}

	/**
	 * Método para salvar la insercion o modificacion para la tabla SDB_PGN_REGLA_NEGOCIO
	 *
	 * @param arn_parametros objeto de tipo <code>ReglaNegocio</code> que se desea salvar
	 * @param ab_insertar variable de tipo <code>boolean</code> que indica si la operacion es una inserción
	 * @param as_userId usuario en sesión que realiza alguna accion(modificar o insertar)
	 * @param as_remoteIp ip del usuario en sesión
	 * @throws B2BException Objeto de tipo <code>B2BException</code>, se produce cuando se encuentra algun error controlado.
	 */
	public synchronized void salvarReglaNegocio(
	    ReglaNegocio arn_parametros, boolean ab_insertar, String as_userId, String as_remoteIp
	)
	    throws B2BException
	{
		DAOManager ldm_manager;
		ldm_manager = DaoManagerFactory.getDAOManagerWorkflow();

		try
		{
			if(arn_parametros != null)
			{
				{
					String ls_nombre;

					ls_nombre = arn_parametros.getNombre();

					if(!StringUtils.isValidString(ls_nombre))
						throw new B2BException(ErrorKeys.DEBE_DIGITAR_NOMBRE);
				}

				{
					String ls_descripcion;

					ls_descripcion = arn_parametros.getDescripcion();

					if(!StringUtils.isValidString(ls_descripcion))
						throw new B2BException(ErrorKeys.ERROR_DESCRIPCION);
				}

				{
					String ls_nombreProcedimiento;

					ls_nombreProcedimiento = arn_parametros.getNombreProcedimiento();

					if(!StringUtils.isValidString(ls_nombreProcedimiento))
						throw new B2BException(ErrorKeys.DEBE_DIGITAR_NOMBRE_PROCEDIMIENTO);
				}

				{
					String ls_idTopologia;

					ls_idTopologia = arn_parametros.getIdTopologiaRegla();

					if(!StringUtils.isValidString(ls_idTopologia))
						throw new B2BException(ErrorKeys.DEBE_SELECCIONAR_TOPOLOGIA_REGLA);
				}

				if(ab_insertar)
				{
					arn_parametros.setIdUsuarioCreacion(as_userId);
					arn_parametros.setIpCreacion(as_remoteIp);

					DaoCreator.getReglaNegocioDAO(ldm_manager).insert(arn_parametros);
				}
				else
				{
					arn_parametros.setIdUsuarioModificacion(as_userId);
					arn_parametros.setIpModificacion(as_remoteIp);

					DaoCreator.getReglaNegocioDAO(ldm_manager).update(arn_parametros);
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("salvarReglaNegocio", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}
	}

	/**
	 * Método para salvar la insercion o modificacion para la tabla SDB_PGN_REINTENTOS.
	 *
	 * @param ar_parametros objeto que se desea salvar
	 * @param ab_insertar de ab insertar
	 * @param as_userId usuario en sesion que realiza alguna accion(modificar o insertar)
	 * @param as_remoteIp ip del usuario en sesion
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public synchronized void salvarReintentos(
	    Reintentos ar_parametros, boolean ab_insertar, String as_userId, String as_remoteIp
	)
	    throws B2BException
	{
		DAOManager ldm_manager;
		ldm_manager = DaoManagerFactory.getDAOManagerCYN();

		try
		{
			if(ar_parametros != null)
			{
				if(ab_insertar)
				{
					Reintentos lr_reintentos;

					lr_reintentos = DaoCreator.getReintentosDAO(ldm_manager).findById(ar_parametros);

					if(lr_reintentos != null)
						throw new B2BException(ErrorKeys.ERROR_REINTENTOS_REPETIDO);

					else
					{
						ar_parametros.setIdUsuarioCreacion(as_userId);
						ar_parametros.setIpCreacion(as_remoteIp);

						DaoCreator.getReintentosDAO(ldm_manager).insert(ar_parametros);
					}
				}
				else
				{
					ar_parametros.setIdUsuarioModificacion(as_userId);
					ar_parametros.setIpModificacion(as_remoteIp);

					DaoCreator.getReintentosDAO(ldm_manager).update(ar_parametros);
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("salvarReintentos", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}
	}

	/**
	 * Método para la transaccion con la base de datos con el fin de hacer inserciones o modificaciones de registros
	 * en la tabla SDB_PGN_RESULTADO_CONSULTA.
	 *
	 * @param arc_parametros correspondiente al valor del tipo de objeto ResultadoConsulta
	 * @param ab_accion correspondiente al valor del tipo de objeto boolean
	 * @param as_usuario es el usuario que realiza la modificación o la inserción
	 * @param as_ipRemota es la ip de donde se ha realizado la acción de modificar o insertar
	 * @throws B2BException Señala que se ha producido una excepción
	 * @throws IOException Señala que se ha producido una excepción de E / S.
	 */
	public synchronized void salvarResultadoConsulta(
	    ResultadoConsulta arc_parametros, boolean ab_accion, String as_usuario, String as_ipRemota
	)
	    throws B2BException, IOException
	{
		DAOManager ldm_manager;

		ldm_manager = DaoManagerFactory.getDAOManager();

		try
		{
			if(arc_parametros != null)
			{
				if(ab_accion)
				{
					arc_parametros.setIdUsuarioCreacion(as_usuario);
					arc_parametros.setIpCreacion(as_ipRemota);

					DaoCreator.getResultadoConsultaDAO(ldm_manager).insert(arc_parametros);
				}
				else
				{
					arc_parametros.setIdUsuarioModificacion(as_usuario);
					arc_parametros.setIpModificacion(as_ipRemota);

					DaoCreator.getResultadoConsultaDAO(ldm_manager).update(arc_parametros);
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("salvarResultadoConsulta", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}
	}

	/**
	 * Método para salvar lo realizado ya sea una inserción o modificación de la tabla SDB_COL_PARTE_INTERESADA.
	 *
	 * @param ar_parametros objeto a insertar o modificar
	 * @param ab_accion indica si se desea insertar o modificar
	 * @param as_userId usuario que realiza la acción de salvar
	 * @param as_remoteIp ip de donde se invoca el metodo de salvar
	 * @return Rol
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public synchronized Rol salvarRol(Rol ar_parametros, boolean ab_accion, String as_userId, String as_remoteIp)
	    throws B2BException
	{
		DAOManager ldm_manager;
		ldm_manager = DaoManagerFactory.getDAOManager();

		try
		{
			if(ar_parametros != null)
			{
				{
					String ls_nombre;
					ls_nombre = ar_parametros.getNombre();

					if(!StringUtils.isValidString(ls_nombre))
						throw new B2BException(ErrorKeys.DEBE_DIGITAR_NOMBRE);
				}

				{
					Date ld_fechaHasta;
					Date ld_fechaDesde;

					ld_fechaHasta     = ar_parametros.getFechaHasta();
					ld_fechaDesde     = ar_parametros.getFechaDesde();

					if(ld_fechaDesde == null)
					{
						Object[] aoa_messageArgs = new String[1];
						aoa_messageArgs[0] = " desde ";
						throw new B2BException(ErrorKeys.ERROR_CAMPO_FECHA_OBLIGATORIO, aoa_messageArgs);
					}

					if(ld_fechaHasta != null)
					{
						if(ld_fechaDesde.after(ld_fechaHasta))
						{
							Object[] aoa_messageArgs = new String[1];
							aoa_messageArgs[0] = " desde ";
							throw new B2BException(ErrorKeys.FECHA_SUPERIOR_ACTUAL, aoa_messageArgs);
						}
					}
				}

				{
					String ls_activo;
					ls_activo = ar_parametros.getActivo();

					if(!StringUtils.isValidString(ls_activo))
						throw new B2BException(ErrorKeys.DEBE_DIGITAR_ACTIVO);
				}

				{
					String ls_idComponente;
					ls_idComponente = ar_parametros.getIdComponente();

					if(!StringUtils.isValidString(ls_idComponente))
						throw new B2BException(ErrorKeys.DEBE_SELECCIONAR_COMPONENTE);
				}

				if(ab_accion)
				{
					ar_parametros.setIdUsuarioCreacion(as_userId);
					ar_parametros.setIpCreacion(as_remoteIp);
				}
				else
				{
					ar_parametros.setIdUsuarioModificacion(as_userId);
					ar_parametros.setIpModificacion(as_remoteIp);
				}

				ar_parametros = DaoCreator.getRolDAO(ldm_manager).insertOrUpdate(ar_parametros, ab_accion);
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("salvarRol", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return ar_parametros;
	}

	/**
	 * Método para salvar lo realizado ya sea una inserción o modificación de Rol Opción.
	 *
	 * @param aro_parametros            objeto a insertar o modificar
	 * @param ab_accion            indica si se desea insertar o modificar
	 * @param as_usuario            usuario que realiza la acción de salvar
	 * @param as_ip            ip de donde se invoca el metodo de salvar
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public synchronized void salvarRolOpcion(
	    RolOpcion aro_parametros, boolean ab_accion, String as_usuario, String as_ip
	)
	    throws B2BException
	{
		DAOManager ldm_manager;
		ldm_manager = DaoManagerFactory.getDAOManager();

		try
		{
			if(aro_parametros != null)
			{
				{
					String ls_idRol;

					ls_idRol = aro_parametros.getIdRol();

					if(!StringUtils.isValidString(ls_idRol))
						throw new B2BException(ErrorKeys.DEBE_SELECCIONAR_ROL);
				}

				{
					String ls_idOpcion;

					ls_idOpcion = aro_parametros.getIdOpcion();

					if(!StringUtils.isValidString(ls_idOpcion))
						throw new B2BException(ErrorKeys.ERROR_SELECCIONE_OPCION);
				}

				{
					Date ls_fechaDesde;

					ls_fechaDesde = aro_parametros.getFechaDesde();

					if(!(ls_fechaDesde != null))
						throw new B2BException(ErrorKeys.ERROR_SIN_FECHA_DESDE);
				}

				{
					String ls_activo;

					ls_activo = aro_parametros.getActivo();

					if(!StringUtils.isValidString(ls_activo))
						throw new B2BException(ErrorKeys.DEBE_DIGITAR_ACTIVO);
				}

				if(ab_accion)
				{
					aro_parametros.setIdUsuarioCreacion(as_usuario);
					aro_parametros.setIpCreacion(as_ip);

					DaoCreator.getRolOpcionDAO(ldm_manager).insert(aro_parametros);
				}
				else
				{
					aro_parametros.setIdUsuarioModificacion(as_usuario);
					aro_parametros.setIpModificacion(as_ip);

					DaoCreator.getRolOpcionDAO(ldm_manager).update(aro_parametros);
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("salvarRolOpcion", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}
	}

	/**
	 * Método para salvar lo realizado ya sea una inserción o modificación de Rol Opción.
	 *
	 * @param as_rol            objeto a insertar o modificar
	 * @param acc_parametros            objeto a desea insertar o modificar
	 * @param as_usuario            usuario que realiza la acción de salvar
	 * @param as_ip            ip de donde se invoca el metodo de salvar
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public synchronized void salvarRolOpcionComponente(
	    String as_rol, Collection<Componente> acc_parametros, String as_usuario, String as_ip
	)
	    throws B2BException
	{
		DAOManager ldm_manager;
		ldm_manager = DaoManagerFactory.getDAOManager();

		try
		{
			if(StringUtils.isValidString(as_rol))
			{
				RolOpcionDAO lrod_rolOpcionDAO;
				OpcionDAO    lod_opcionDAO;
				RolDAO       lrd_rolDAO;
				RolOpcion    lro_rolOpcion;
				Date         ld_fechaActual;

				lod_opcionDAO         = DaoCreator.getOpcionDAO(ldm_manager);
				lrod_rolOpcionDAO     = DaoCreator.getRolOpcionDAO(ldm_manager);
				lrd_rolDAO            = DaoCreator.getRolDAO(ldm_manager);
				ld_fechaActual        = DateUtils.getTimestamp();

				if(ld_fechaActual != null)
				{
					lro_rolOpcion = new RolOpcion(as_rol, null, as_ip, as_usuario, ld_fechaActual, null);

					lrod_rolOpcionDAO.updateInactivo(lro_rolOpcion);
				}

				if(CollectionUtils.isValidCollection(acc_parametros))
				{
					for(Componente lc_temp : acc_parametros)
					{
						if(lc_temp != null)
						{
							Collection<Opcion> lco_opcion;

							lco_opcion = lc_temp.getOpcionesRolTarget();

							if(CollectionUtils.isValidCollection(lco_opcion))
							{
								String ls_cambio;

								ls_cambio = lco_opcion.toString();

								if(StringUtils.isValidString(ls_cambio))
								{
									String ls_llaves;

									ls_llaves     = ls_cambio.replace(
										    IdentificadoresCommon.LLAVE_INICIAL, IdentificadoresCommon.DATO_INVALIDO
										);
									ls_llaves     = ls_llaves.replace(
										    IdentificadoresCommon.LLAVE_FINAL, IdentificadoresCommon.DATO_INVALIDO
										);
									ls_llaves     = ls_llaves.replace(
										    IdentificadoresCommon.ESPACIO_VACIO, IdentificadoresCommon.DATO_INVALIDO
										);

									if(StringUtils.isValidString(ls_llaves))
									{
										Collection<String> lcs_targetOpcion;
										lcs_targetOpcion = ListadoCodigosConstantes.generarCodigosCollection(ls_llaves);

										if(CollectionUtils.isValidCollection(lcs_targetOpcion))
										{
											for(String ls_temp : lcs_targetOpcion)
											{
												if(StringUtils.isValidString(ls_temp))
													salvarRolOpcionComponentePadre(
													    as_rol, ls_temp, as_usuario, as_ip, lrod_rolOpcionDAO,
													    lod_opcionDAO
													);
											}
										}
									}
								}
							}
						}
					}

					Rol lr_rol;

					lr_rol = lrd_rolDAO.findById(as_rol, false);

					if(lr_rol != null)
					{
						lr_rol.setIdUsuarioModificacion(as_usuario);
						lr_rol.setIpModificacion(as_ip);

						lrd_rolDAO.insertOrUpdate(lr_rol, false);
					}
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("salvarRolOpcionComponente", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}
	}

	/**
	 * Método para salvar la insercion o modificacion para la tabla SDB_ACC_SUBPROCESO.
	 *
	 * @param asp_parametros objeto que se desea salvar
	 * @param ab_accion indica si se va a insertar o modificar
	 * @param as_userId usuario en sesion que realiza alguna accion(modificar o insertar)
	 * @param as_remoteIp ip del usuario en sesion
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public synchronized void salvarSubproceso(
	    Subproceso asp_parametros, boolean ab_accion, String as_userId, String as_remoteIp
	)
	    throws B2BException
	{
		DAOManager ldm_manager;
		ldm_manager = DaoManagerFactory.getDAOManager();

		try
		{
			if(asp_parametros != null)
			{
				if(ab_accion)
				{
					asp_parametros.setIdUsuarioCreacion(as_userId);
					asp_parametros.setIpCreacion(as_remoteIp);
				}
				else
				{
					asp_parametros.setIdUsuarioModificacion(as_userId);
					asp_parametros.setIpModificacion(as_remoteIp);
				}

				Subproceso isp_datos;
				isp_datos = null;

				if(ab_accion)
				{
					isp_datos = DaoCreator.getSubprocesoDAO(ldm_manager).findById(asp_parametros);

					if(isp_datos == null)
						DaoCreator.getSubprocesoDAO(ldm_manager).insertOrUpdate(asp_parametros, ab_accion);
					else
						throw new B2BException(ErrorKeys.ERROR_SUBPROCESO_REPETIDO);
				}

				else
					DaoCreator.getSubprocesoDAO(ldm_manager).insertOrUpdate(asp_parametros, ab_accion);
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("salvarSubProceso", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}
	}

	/**
	 * Método para salvar la insercion o modificacion para la tabla SDB_ACC_SUBPROCESO_VERSION.
	 *
	 * @param asp_parametros objeto que se desea salvar
	 * @param ab_accion indica si se va a insertar o modificar
	 * @param as_userId usuario en sesion que realiza alguna accion(modificar o insertar)
	 * @param as_remoteIp ip del usuario en sesion
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public synchronized void salvarSubprocesoVersion(
	    SubprocesoVersion asp_parametros, boolean ab_accion, String as_userId, String as_remoteIp
	)
	    throws B2BException
	{
		DAOManager ldm_manager;
		ldm_manager = DaoManagerFactory.getDAOManager();

		try
		{
			if(asp_parametros != null)
			{
				if(ab_accion)
				{
					asp_parametros.setIdUsuarioCreacion(as_userId);
					asp_parametros.setIpCreacion(as_remoteIp);
				}
				else
				{
					asp_parametros.setIdUsuarioModificacion(as_userId);
					asp_parametros.setIpModificacion(as_remoteIp);
				}

				{
					String ls_idProceso;
					String ls_idSubproceso;
					Long   ll_versionSubproceso;

					ls_idProceso             = asp_parametros.getIdProceso();
					ls_idSubproceso          = asp_parametros.getIdSubproceso();
					ll_versionSubproceso     = asp_parametros.getVersionSubproceso();

					if(
					    StringUtils.isValidString(ls_idProceso) && StringUtils.isValidString(ls_idSubproceso)
						    && NumericUtils.isValidLong(ll_versionSubproceso)
					)
					{
						SubprocesoVersion lspv_datos;
						lspv_datos = null;

						SubprocesoVersionDAO lsvDAO = DaoCreator.getSubprocesoVersionDAO(ldm_manager);

						if(ab_accion)
						{
							lspv_datos = lsvDAO.findById(ls_idProceso, ls_idSubproceso, ll_versionSubproceso, true);

							if(lspv_datos == null)
								lsvDAO.insertOrUpdate(asp_parametros, ab_accion);
							else
								throw new B2BException(ErrorKeys.ERROR_SUBPROCESO_REPETIDO);
						}

						else
							lsvDAO.insertOrUpdate(asp_parametros, ab_accion);
					}
					else
						throw new B2BException(ErrorKeys.ERROR_INTERNO_SISTEMA);
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("salvarSubProceso", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}
	}

	/**
	 * Metodo para insertar y actualizar los registros TarifaAlerta ingresados en pantalla.
	 *
	 * @param acta_parametros Collection<TarifaAlerta> Colección con objetos a actualizar o modificar
	 * @param as_userId correspondiente al valor del tipo de objeto String
	 * @param as_remoteIp correspondiente al valor del tipo de objeto String
	 * @throws B2BException Señala que se ha producido una excepción
	 *
	 */
	public synchronized void salvarTarifaAlerta(
	    Collection<TarifaAlerta> acta_parametros, String as_userId, String as_remoteIp
	)
	    throws B2BException
	{
		DAOManager ldm_manager;

		ldm_manager = DaoManagerFactory.getDAOManager();

		try
		{
			if(CollectionUtils.isValidCollection(acta_parametros))
			{
				TarifaAlertaDAO lta_DAO;
				long            ll_version;

				lta_DAO        = DaoCreator.getTarifaAlertaDAO(ldm_manager);
				ll_version     = 0;

				Collection<TarifaAlerta> lctaa_tarifaAlertaActual;

				lctaa_tarifaAlertaActual = lta_DAO.findAll(true);

				if(CollectionUtils.isValidCollection(lctaa_tarifaAlertaActual))
				{
					for(TarifaAlerta lta_temp : lctaa_tarifaAlertaActual)
					{
						if(lta_temp != null)
						{
							lta_temp.setActivo(EstadoCommon.N);
							lta_temp.setIdUsuarioModificacion(as_userId);
							lta_temp.setIpModificacion(as_remoteIp);
							lta_DAO.updateIdTarifaVersion(lta_temp);

							ll_version = lta_temp.getVersion();
						}
					}
				}

				ll_version++;

				for(TarifaAlerta lta_temp : acta_parametros)
				{
					if(lta_temp != null)
					{
						lta_temp.setActivo(EstadoCommon.S);
						lta_temp.setVersion(ll_version);
						lta_temp.setIdUsuarioCreacion(as_userId);
						lta_temp.setIpCreacion(as_remoteIp);
						lta_DAO.insert(lta_temp);
					}
				}
			}
		}

		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("salvarTarifaAlerta", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}
	}

	/**
	 * Salvar tipo acto prohibicion.
	 *
	 * @param atd_parametros de atd parametros
	 * @param ab_accion de ab accion
	 * @param as_usuario de as usuario
	 * @param as_ip de as ip
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public synchronized void salvarTipoActoProhibicion(
	    TipoActoProhibicion atd_parametros, boolean ab_accion, String as_usuario, String as_ip
	)
	    throws B2BException
	{
		DAOManager ldm_manager;
		ldm_manager = DaoManagerFactory.getDAOManager();

		try
		{
			if(atd_parametros != null)
			{
				{
					String ls_tipoActo;

					ls_tipoActo = atd_parametros.getIdTipoActo();

					if(!StringUtils.isValidString(ls_tipoActo))
						throw new B2BException(ErrorKeys.ERROR_TIPO_ACTO_INVALIDO);
				}

				{
					String ls_versionActo;

					ls_versionActo = atd_parametros.getVersionActo();

					if(!StringUtils.isValidString(ls_versionActo))
						throw new B2BException(ErrorKeys.ERROR_SIN_VERSION_ACTO);
				}

				{
					Long ls_tiempoVencimiento;

					ls_tiempoVencimiento = atd_parametros.getTiempoVencimiento();

					if(!NumericUtils.isValidLong(ls_tiempoVencimiento))
						throw new B2BException(ErrorKeys.DEBE_ELEGIR_TIEMPO_VENCIMIENTO);
				}

				{
					String ls_idUnidadTiempo;

					ls_idUnidadTiempo = atd_parametros.getIdUnidadTiempo();

					if(!StringUtils.isValidString(ls_idUnidadTiempo))
						throw new B2BException(ErrorKeys.DEBE_ELEGIR_UNIDAD_TIEMPO);
				}

				{
					String ls_cancelacionAutomatica;

					ls_cancelacionAutomatica = atd_parametros.getCancelacionAutomatica();

					if(!StringUtils.isValidString(ls_cancelacionAutomatica))
						throw new B2BException(ErrorKeys.DEBE_ELEGIR_CANCELACION_AUTOMATICA);
				}

				if(ab_accion)
				{
					atd_parametros.setIdUsuarioCreacion(as_usuario);
					atd_parametros.setIpCreacion(as_ip);

					DaoCreator.getTipoActoProhibicionDAO(ldm_manager).insert(atd_parametros);
				}
				else
				{
					atd_parametros.setIdUsuarioModificacion(as_usuario);
					atd_parametros.setIpModificacion(as_ip);

					DaoCreator.getTipoActoProhibicionDAO(ldm_manager).update(atd_parametros);
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("salvarTipoActoProhibicion", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}
	}

	/**
	 * Salvar tipo adquisicion.
	 *
	 * @param atr_parametros correspondiente al valor del tipo de objeto TipoAdquisicion
	 * @param ab_accion correspondiente al valor del tipo de objeto boolean
	 * @param as_userId correspondiente al valor del tipo de objeto String
	 * @param as_remoteIp correspondiente al valor del tipo de objeto String
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public synchronized void salvarTipoAdquisicion(
	    TipoAdquisicion atr_parametros, boolean ab_accion, String as_userId, String as_remoteIp
	)
	    throws B2BException
	{
		DAOManager ldm_manager;
		ldm_manager = DaoManagerFactory.getDAOManager();

		try
		{
			if(atr_parametros != null)
			{
				atr_parametros.setIdUsuarioCreacion(as_userId);
				atr_parametros.setIpCreacion(as_remoteIp);
				atr_parametros.setIdUsuarioModificacion(as_userId);
				atr_parametros.setIpModificacion(as_remoteIp);

				DaoCreator.getTipoAdquisicionDAO(ldm_manager).insertOrUpdate(atr_parametros, ab_accion);
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("salvarTipoAdquisicion", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}
	}

	/**
	 * Método para la transaccion con la base de datos con el fin de hacer inserciones o modificaciones de registros
	 * en la tabla SDB_PGN_TIPO_CRITERIO_BUSQUEDA.
	 *
	 * @param atcb_parametros correspondiente al valor del tipo de objeto TipoCriterioBusquedaPGN
	 * @param ab_accion correspondiente al valor del tipo de objeto boolean
	 * @param as_usuario es el usuario que realiza la modificación o la inserción
	 * @param as_ipRemota es la ip de donde se ha realizado la acción de modificar o insertar
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public synchronized void salvarTipoCriterioBusquedaPGN(
	    TipoCriterioBusquedaPGN atcb_parametros, boolean ab_accion, String as_usuario, String as_ipRemota
	)
	    throws B2BException
	{
		DAOManager ldm_manager;

		ldm_manager = DaoManagerFactory.getDAOManager();

		try
		{
			if(atcb_parametros != null)
			{
				if(ab_accion)
				{
					atcb_parametros.setIdUsuarioCreacion(as_usuario);
					atcb_parametros.setIpCreacion(as_ipRemota);

					DaoCreator.getTipoCriterioBusquedaPGNDAO(ldm_manager).insert(atcb_parametros);
				}
				else
				{
					atcb_parametros.setIdUsuarioModificacion(as_usuario);
					atcb_parametros.setIpModificacion(as_ipRemota);

					DaoCreator.getTipoCriterioBusquedaPGNDAO(ldm_manager).update(atcb_parametros);
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("salvarTipoCriterioBusquedaPGN", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}
	}

	/**
	 * Método para salvar lo realizado ya sea una inserción o modificación de la
	 * tabla ID_TIPO_DOCUMENTAL.
	 *
	 * @param atd_parametros            objeto a insertar o modificar
	 * @param ab_accion            indica si se desea insertar o modificar
	 * @param as_usuario            usuario que realiza la acción de salvar
	 * @param as_ip            ip de donde se invoca el metodo de salvar
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public synchronized void salvarTipoDocumental(
	    TipoDocumental atd_parametros, boolean ab_accion, String as_usuario, String as_ip
	)
	    throws B2BException
	{
		DAOManager ldm_manager;
		ldm_manager = DaoManagerFactory.getDAOManager();

		try
		{
			if(atd_parametros != null)
			{
				{
					String ls_nombre;

					ls_nombre = atd_parametros.getNombre();

					if(!StringUtils.isValidString(ls_nombre))
						throw new B2BException(ErrorKeys.DEBE_DIGITAR_NOMBRE);
				}

				{
					String ls_activo;

					ls_activo = atd_parametros.getActivo();

					if(!StringUtils.isValidString(ls_activo))
						throw new B2BException(ErrorKeys.DEBE_DIGITAR_ACTIVO);
				}

				if(ab_accion)
				{
					atd_parametros.setIdUsuarioCreacion(as_usuario);
					atd_parametros.setIpCreacion(as_ip);

					DaoCreator.getTipoDocumentalDAO(ldm_manager).insert(atd_parametros);
				}
				else
				{
					atd_parametros.setIdUsuarioModificacion(as_usuario);
					atd_parametros.setIpModificacion(as_ip);

					DaoCreator.getTipoDocumentalDAO(ldm_manager).update(atd_parametros);
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("salvarTipoDocumental", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}
	}

	/**
	 * Método para salvar lo realizado ya sea una inserción o modificación de Tipo Documental Acto.
	 *
	 * @param atd_parametros            objeto a insertar o modificar
	 * @param ab_accion            indica si se desea insertar o modificar
	 * @param as_usuario            usuario que realiza la acción de salvar
	 * @param as_ip            ip de donde se invoca el metodo de salvar
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public synchronized void salvarTipoDocumentalActo(
	    TipoDocumentalActo atd_parametros, boolean ab_accion, String as_usuario, String as_ip
	)
	    throws B2BException
	{
		DAOManager ldm_manager;
		ldm_manager = DaoManagerFactory.getDAOManager();

		try
		{
			if(atd_parametros != null)
			{
				{
					String ls_tipoDocumental;

					ls_tipoDocumental = atd_parametros.getIdTipoDocumental();

					if(!StringUtils.isValidString(ls_tipoDocumental))
						throw new B2BException(ErrorKeys.DEBE_SELECCIONAR_TIPO_DOCUMENTAL);
				}

				{
					String ls_obligatorioo;

					ls_obligatorioo = atd_parametros.getObligatorio();

					if(!StringUtils.isValidString(ls_obligatorioo))
						throw new B2BException(ErrorKeys.DEBE_SELECCIONAR_OBLIGATORIO);
				}

				if(ab_accion)
				{
					atd_parametros.setIdUsuarioCreacion(as_usuario);
					atd_parametros.setIpCreacion(as_ip);

					DaoCreator.getTipoDocumentalActoDAO(ldm_manager).insert(atd_parametros);
				}
				else
				{
					atd_parametros.setIdUsuarioModificacion(as_usuario);
					atd_parametros.setIpModificacion(as_ip);

					DaoCreator.getTipoDocumentalActoDAO(ldm_manager).update(atd_parametros);
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("salvarTipoDocumentalActo", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}
	}

	/**
	 * Método para salvar lo realizado ya sea una inserción o modificación de la tabla SDB_PGN_TIPO_DOCUMENTO_PUBLICO.
	 *
	 * @param atr_parametros objeto a insertar o modificar
	 * @param ab_accion indica si se desea insertar o modificar
	 * @param as_userId usuario que realiza la acción de salvar
	 * @param as_remoteIp ip de donde se invoca el metodo de salvar
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public synchronized void salvarTipoDocumentoPublico(
	    TipoDocumentoPublico atr_parametros, boolean ab_accion, String as_userId, String as_remoteIp
	)
	    throws B2BException
	{
		DAOManager ldm_manager;
		ldm_manager = DaoManagerFactory.getDAOManager();

		try
		{
			if(atr_parametros != null)
			{
				atr_parametros.setIdUsuarioCreacion(as_userId);
				atr_parametros.setIpCreacion(as_remoteIp);
				atr_parametros.setIdUsuarioModificacion(as_userId);
				atr_parametros.setIpModificacion(as_remoteIp);

				DaoCreator.getTipoDocumentoPublicoDAO(ldm_manager).insertOrUpdate(atr_parametros, ab_accion);
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("salvarTipoDocumentoPublico", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}
	}

	/**
	 * Método para la transaccion con la base de datos con el fin de hacer inserciones o modificaciones de registros
	 * en la tabla SDB_PGN_GOBERNACION.
	 *
	 * @param ac_parametros correspondiente al valor del tipo de objeto TipoIntegracionGobernacion
	 * @param ab_accion correspondiente al valor del tipo de objeto boolean
	 * @param as_usuario es el usuario que realiza la modificación o la inserción
	 * @param as_ipRemota es la ip de donde se ha realizado la acción de modificar o insertar
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public synchronized void salvarTipoIntegracionGobernacion(
	    TipoIntegracionGobernacion ac_parametros, boolean ab_accion, String as_usuario, String as_ipRemota
	)
	    throws B2BException
	{
		DAOManager ldm_manager;

		ldm_manager = DaoManagerFactory.getDAOManager();

		try
		{
			if(ac_parametros != null)
			{
				{
					String ls_idTipoIntegracion;

					ls_idTipoIntegracion = ac_parametros.getIdTipoIntegracion();

					if(!StringUtils.isValidString(ls_idTipoIntegracion))
						throw new B2BException(ErrorKeys.DEBE_DIGITAR_ID_TIPO_INTEGRACION);
				}

				{
					String ls_descripcion;

					ls_descripcion = ac_parametros.getDescripcion();

					if(!StringUtils.isValidString(ls_descripcion))
						throw new B2BException(ErrorKeys.DEBE_DIGITAR_DESCRIPCION);
				}

				{
					String ls_activo;

					ls_activo = ac_parametros.getActivo();

					if(!StringUtils.isValidString(ls_activo))
						throw new B2BException(ErrorKeys.DEBE_DIGITAR_ACTIVO);
				}

				TipoIntegracionGobernacion ltig_tmp;

				ltig_tmp = DaoCreator.getTipoIntegracionGobernacionDAO(ldm_manager).findById(ac_parametros);

				ac_parametros.setIdUsuarioCreacion(as_usuario);
				ac_parametros.setIpCreacion(as_ipRemota);
				ac_parametros.setIdUsuarioModificacion(as_usuario);
				ac_parametros.setIpModificacion(as_ipRemota);

				if(ab_accion)
				{
					if(ltig_tmp == null)
						DaoCreator.getTipoIntegracionGobernacionDAO(ldm_manager).insertOrUpdate(
						    ac_parametros, ab_accion
						);
					else
						throw new B2BException(ErrorKeys.ERROR_ID_TIPO_INTEGRACION);
				}
				else
					DaoCreator.getTipoIntegracionGobernacionDAO(ldm_manager).insertOrUpdate(ac_parametros, ab_accion);
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("salvarTipoIntegracionGobernacion", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}
	}

	/**
	 * Método para salvar la insercion o modificacion para la tabla SDB_PNG_TIPO_NOTIFICACION.
	 *
	 * @param atn_parametros objeto que se desea salvar
	 * @param ab_accion indica si se va a insertar o modificar
	 * @param as_userId usuario en sesion que realiza alguna accion(modificar o insertar)
	 * @param as_remoteIp ip del usuario en sesion
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public synchronized void salvarTipoNotificacion(
	    TipoNotificacion atn_parametros, boolean ab_accion, String as_userId, String as_remoteIp
	)
	    throws B2BException
	{
		DAOManager ldm_manager;
		ldm_manager = DaoManagerFactory.getDAOManager();

		try
		{
			if(atn_parametros != null)
			{
				if(ab_accion)
				{
					atn_parametros.setIdUsuarioCreacion(as_userId);
					atn_parametros.setIpCreacion(as_remoteIp);
				}
				else
				{
					atn_parametros.setIdUsuarioModificacion(as_userId);
					atn_parametros.setIpModificacion(as_remoteIp);
				}

				TipoNotificacion itn_datos;
				itn_datos = null;

				if(ab_accion)
				{
					itn_datos = DaoCreator.getTipoNotificacionDAO(ldm_manager).findById(atn_parametros);

					if(itn_datos == null)
						DaoCreator.getTipoNotificacionDAO(ldm_manager).insertOrUpdate(atn_parametros, ab_accion);
					else
						throw new B2BException(ErrorKeys.ERROR_TIPO_NOTIFICACION_REPETIDO);
				}

				else
					DaoCreator.getTipoNotificacionDAO(ldm_manager).insertOrUpdate(atn_parametros, ab_accion);
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("salvarTipoNotificacion", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}
	}

	/**
	 * Método para la transaccion con la base de datos con el fin de hacer inserciones o modificaciones de registros
	 * en la tabla SDB_PGN_TIPO_OPERACION.
	 *
	 * @param ato_parametros correspondiente al valor del tipo de objeto TipoOperacion
	 * @param ab_accion correspondiente al valor del tipo de objeto boolean
	 * @param as_usuario es el usuario que realiza la modificación o la inserción
	 * @param as_ipRemota es la ip de donde se ha realizado la acción de modificar o insertar
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public synchronized void salvarTipoOperacion(
	    TipoOperacion ato_parametros, boolean ab_accion, String as_usuario, String as_ipRemota
	)
	    throws B2BException
	{
		DAOManager ldm_manager;

		ldm_manager = DaoManagerFactory.getDAOManager();

		try
		{
			if(ato_parametros != null)
			{
				if(ab_accion)
				{
					ato_parametros.setIdUsuarioCreacion(as_usuario);
					ato_parametros.setIpCreacion(as_ipRemota);

					DaoCreator.getTipoOperacionDAO(ldm_manager).insert(ato_parametros);
				}
				else
				{
					ato_parametros.setIdUsuarioModificacion(as_usuario);
					ato_parametros.setIpModificacion(as_ipRemota);

					DaoCreator.getTipoOperacionDAO(ldm_manager).update(ato_parametros);
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("salvarTipoOperacion", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}
	}

	/**
	 * Método para salvar lo realizado ya sea una inserción o modificación de la tabla SDB_PGN_TIPO_PERSONA.
	 *
	 * @param atp_parametros objeto a insertar o modificar
	 * @param ab_accion indica si se desea insertar o modificar
	 * @param as_usuario usuario que realiza la acción de salvar
	 * @param as_ipRemota ip de donde se invoca el metodo de salvar
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public synchronized void salvarTipoPersona(
	    TipoPersona atp_parametros, boolean ab_accion, String as_usuario, String as_ipRemota
	)
	    throws B2BException
	{
		DAOManager ldm_manager;
		ldm_manager = DaoManagerFactory.getDAOManager();

		try
		{
			if(atp_parametros != null)
			{
				TipoPersonaDAO ltpd_DAO;
				ltpd_DAO = DaoCreator.getTipoPersonaDAO(ldm_manager);

				{
					String ls_idTipoPersona;
					ls_idTipoPersona = atp_parametros.getIdTipoPersona();

					if(!StringUtils.isValidString(ls_idTipoPersona))
						throw new B2BException(ErrorKeys.DEBE_DIGITAR_ID_TIPO_PERSONA);
				}

				{
					String ls_descripcion;
					ls_descripcion = atp_parametros.getDescripcion();

					if(!StringUtils.isValidString(ls_descripcion))
						throw new B2BException(ErrorKeys.DEBE_DIGITAR_DESCRIPCION);
				}

				if(ab_accion)
				{
					TipoPersona ltp_tipoPersona;
					ltp_tipoPersona = ltpd_DAO.findById(atp_parametros);

					if(ltp_tipoPersona != null)
					{
						Object[] loa_messageArgs;
						loa_messageArgs     = new String[1];

						loa_messageArgs[0] = ltp_tipoPersona.getIdTipoPersona();

						throw new B2BException(ErrorKeys.ERROR_TIPO_PERSONA_EXISTE, loa_messageArgs);
					}
				}

				atp_parametros.setIdUsuarioCreacion(as_usuario);
				atp_parametros.setIdUsuarioModificacion(as_usuario);
				atp_parametros.setIpCreacion(as_ipRemota);
				atp_parametros.setIpModificacion(as_ipRemota);

				ltpd_DAO.insertOrUpdate(atp_parametros, ab_accion);
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("salvarTipoPersona", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}
	}

	/**
	 * Método para salvar lo realizado ya sea una inserción o modificación de la tabla SDB_COL_PREDIO_TIPO.
	 *
	 * @param apt_parametros objeto a insertar o modificar
	 * @param ab_accion indica si se desea insertar o modificar
	 * @param as_usuario usuario que realiza la acción de salvar
	 * @param as_ip ip de donde se invoca el metodo de salvar
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public synchronized void salvarTipoPredio(
	    PredioTipo apt_parametros, boolean ab_accion, String as_usuario, String as_ip
	)
	    throws B2BException
	{
		DAOManager ldm_manager;
		ldm_manager = DaoManagerFactory.getDAOManager();

		try
		{
			if(apt_parametros != null)
			{
				PredioTipoDAO lpt_DAO;

				lpt_DAO = DaoCreator.getPredioTipoDao(ldm_manager);

				if(ab_accion)
				{
					apt_parametros.setIdUsuarioCreacion(as_usuario);
					apt_parametros.setIpCreacion(as_ip);
				}
				else
				{
					apt_parametros.setIdUsuarioModificacion(as_usuario);
					apt_parametros.setIpModificacion(as_ip);
				}

				lpt_DAO.insertOrUpdate(apt_parametros, ab_accion);
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("salvarTipoPredio", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}
	}

	/**
	 * Método para salvar lo realizado ya sea una inserción o modificación de la tabla SDB_COL_TIPO_RRR.
	 *
	 * @param atr_parametros objeto a insertar o modificar
	 * @param ib_query indica si se desea insertar o modificar
	 * @param as_userId usuario que realiza la acción de salvar
	 * @param as_ipRemote ip de donde se invoca el metodo de salvar
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public synchronized void salvarTipoRRR(
	    TipoRrr atr_parametros, boolean ib_query, String as_userId, String as_ipRemote
	)
	    throws B2BException
	{
		DAOManager ldm_manager;
		ldm_manager = DaoManagerFactory.getDAOManager();

		try
		{
			if(atr_parametros != null)
			{
				TipoRrrDAO ltr_DAO;

				ltr_DAO = DaoCreator.getTipoRrrDAO(ldm_manager);

				{
					String ls_nombre;
					ls_nombre = atr_parametros.getIlicode();

					if(!StringUtils.isValidString(ls_nombre))
						throw new B2BException(ErrorKeys.DEBE_DIGITAR_NOMBRE);
				}

				{
					String ls_descripcion;
					ls_descripcion = atr_parametros.getDescripcion();

					if(!StringUtils.isValidString(ls_descripcion))
						throw new B2BException(ErrorKeys.DEBE_DIGITAR_DESCRIPCION);
				}

				{
					String ls_activo;
					ls_activo = atr_parametros.getActivo();

					if(!StringUtils.isValidString(ls_activo))
						throw new B2BException(ErrorKeys.DEBE_DIGITAR_ACTIVO);
				}

				atr_parametros.setIdUsuarioCreacion(as_userId);
				atr_parametros.setIpCreacion(as_ipRemote);
				atr_parametros.setIdUsuarioModificacion(as_userId);
				atr_parametros.setIpModificacion(as_ipRemote);

				ltr_DAO.insertOrUpdate(atr_parametros, ib_query);
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("salvarTipoRRR", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}
	}

	/**
	 * Método para salvar lo realizado ya sea una inserción o modificación de la tabla SDB_ACC_TIPO_RECEPCION.
	 *
	 * @param atr_parametros objeto a insertar o modificar
	 * @param ab_accion indica si se desea insertar o modificar
	 * @param as_userId usuario que realiza la acción de salvar
	 * @param as_remoteIp ip de donde se invoca el metodo de salvar
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public synchronized void salvarTipoRecepcion(
	    TipoRecepcion atr_parametros, boolean ab_accion, String as_userId, String as_remoteIp
	)
	    throws B2BException
	{
		DAOManager ldm_manager;
		ldm_manager = DaoManagerFactory.getDAOManager();

		try
		{
			if(atr_parametros != null)
			{
				if(ab_accion)
				{
					atr_parametros.setIdUsuarioCreacion(as_userId);
					atr_parametros.setIpCreacion(as_remoteIp);
				}
				else
				{
					atr_parametros.setIdUsuarioModificacion(as_userId);
					atr_parametros.setIpModificacion(as_remoteIp);
				}

				DaoCreator.getTipoRecepcionDAO(ldm_manager).insertOrUpdate(atr_parametros, ab_accion);
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("salvarTipoRecepcion", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}
	}

	/**
	 * Método para salvar lo realizado ya sea una inserción o modificación de la tabla SDB_ACC_TIPO_TARJETA_APODERADO.
	 *
	 * @param atr_parametros objeto a insertar o modificar
	 * @param ab_accion indica si se desea insertar o modificar
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public synchronized void salvarTipoTarjetaApoderado(TipoTarjetaApoderado atr_parametros, boolean ab_accion)
	    throws B2BException
	{
		DAOManager ldm_manager;
		ldm_manager = DaoManagerFactory.getDAOManager();

		try
		{
			if(atr_parametros != null)
			{
				{
					String ls_nombre;
					ls_nombre = atr_parametros.getDescripcion();

					if(!StringUtils.isValidString(ls_nombre))
						throw new B2BException(ErrorKeys.DEBE_DIGITAR_DESCRIPCION);
				}

				{
					String ls_habilitadoNotificacion;
					ls_habilitadoNotificacion = atr_parametros.getActivo();

					if(!StringUtils.isValidString(ls_habilitadoNotificacion))
						throw new B2BException(ErrorKeys.DEBE_DIGITAR_ACTIVO);
				}

				DaoCreator.getTipoTarjetaApoderadoDAO(ldm_manager).insertOrUpdate(atr_parametros, ab_accion);
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("salvarTipoTarjetaApoderado", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}
	}

	/**
	 * Método para salvar lo realizado ya sea una inserción o modificación de la tabla SDB_COL_TIPO_USO_SUELO.
	 *
	 * @param atus_parametros  objeto a insertar o modificar
	 * @param ab_accion  indica si se desea insertar o modificar
	 * @param as_usuario  usuario que realiza la acción de salvar
	 * @param as_ip ip de donde se invoca el metodo de salvar
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public synchronized void salvarTipoUsoSuelo(
	    TipoUsoSuelo atus_parametros, boolean ab_accion, String as_usuario, String as_ip
	)
	    throws B2BException
	{
		DAOManager ldm_manager;
		ldm_manager = DaoManagerFactory.getDAOManager();

		try
		{
			if(atus_parametros != null)
			{
				{
					String ls_nombre;
					ls_nombre = atus_parametros.getIlicode();

					if(!StringUtils.isValidString(ls_nombre))
						throw new B2BException(ErrorKeys.DEBE_DIGITAR_NOMBRE);
				}

				{
					String ls_descripcion;
					ls_descripcion = atus_parametros.getDescription();

					if(!StringUtils.isValidString(ls_descripcion))
						throw new B2BException(ErrorKeys.DEBE_DIGITAR_DESCRIPCION);
				}

				{
					String ls_activo;
					ls_activo = atus_parametros.getActivo();

					if(!StringUtils.isValidString(ls_activo))
						throw new B2BException(ErrorKeys.DEBE_DIGITAR_ACTIVO);
				}

				atus_parametros.setIdUsuarioCreacion(as_usuario);
				atus_parametros.setIpCreacion(as_ip);
				atus_parametros.setIdUsuarioModificacion(as_usuario);
				atus_parametros.setIpModificacion(as_ip);

				DaoCreator.getTipoUsoSueloDAO(ldm_manager).insertOrUpdate(atus_parametros, ab_accion);
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("salvarTipoUsoSuelo", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}
	}

	/**
	 * Método para salvar la insercion o modificacion para la tabla SDB_PGN_UNIDAD_TIEMPO_VENCIMIENTO.
	 *
	 * @param autv_parametros objeto que se desea salvar
	 * @param ab_accion indica si se va a insertar o modificar
	 * @param as_userId usuario en sesion que realiza alguna accion(modificar o insertar)
	 * @param as_remoteIp ip del usuario en sesion
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public synchronized void salvarUnidadTiempoVencimiento(
	    UnidadTiempoVencimiento autv_parametros, boolean ab_accion, String as_userId, String as_remoteIp
	)
	    throws B2BException
	{
		DAOManager ldm_manager;
		ldm_manager = DaoManagerFactory.getDAOManager();

		try
		{
			if(autv_parametros != null)
			{
				if(ab_accion)
				{
					autv_parametros.setIdUsuarioCreacion(as_userId);
					autv_parametros.setIpCreacion(as_remoteIp);
				}
				else
				{
					autv_parametros.setIdUsuarioModificacion(as_userId);
					autv_parametros.setIpModificacion(as_remoteIp);
				}

				UnidadTiempoVencimiento iutv_datos;
				iutv_datos = null;

				if(ab_accion)
				{
					iutv_datos = DaoCreator.getUnidadTiempoVencimientoDAO(ldm_manager).findById(autv_parametros);

					if(iutv_datos == null)
						DaoCreator.getUnidadTiempoVencimientoDAO(ldm_manager).insertOrUpdate(
						    autv_parametros, ab_accion
						);
					else
						throw new B2BException(ErrorKeys.ERROR_UNIDAD_TIEMPO_VENCIMIENTO);
				}

				else
					DaoCreator.getUnidadTiempoVencimientoDAO(ldm_manager).insertOrUpdate(autv_parametros, ab_accion);
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("salvarUnidadTiempoVencimiento", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}
	}

	/**
	 * Método para salvar lo realizado ya sea una inserción o modificación de la tabla SDB_PGN_VEREDA.
	 *
	 * @param ac_parametros  objeto a insertar o modificar
	 * @param ab_accion indica si se desea insertar o modificar
	 * @param as_usuario usuario que realiza la acción de salvar
	 * @param as_ipRemota ip de donde se invoca el metodo de salvar
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public synchronized void salvarVereda(
	    Vereda ac_parametros, boolean ab_accion, String as_usuario, String as_ipRemota
	)
	    throws B2BException
	{
		DAOManager ldm_manager;
		ldm_manager = DaoManagerFactory.getDAOManager();

		try
		{
			if(ac_parametros != null)
			{
				if(ab_accion)
				{
					ac_parametros.setIdUsuarioCreacion(as_usuario);
					ac_parametros.setIpCreacion(as_ipRemota);
				}

				else
				{
					ac_parametros.setIdUsuarioModificacion(as_usuario);
					ac_parametros.setIpModificacion(as_ipRemota);
				}

				DaoCreator.getVeredaDAO(ldm_manager).insertOrUpdate(ac_parametros, ab_accion);
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("salvarVereda", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}
	}

	/**
	 * Método para salvar lo realizado ya sea una inserción o modificación de la tabla SDB_PGN_ZONA_REGISTRAL.
	 *
	 * @param ac_parametros objeto a insertar o modificar
	 * @param ab_accion indica si se desea insertar o modificar
	 * @param as_usuario usuario que realiza la acción de salvar
	 * @param as_ipRemota ip de donde se invoca el metodo de salvar
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public synchronized void salvarZonaRegistral(
	    ZonaRegistral ac_parametros, boolean ab_accion, String as_usuario, String as_ipRemota
	)
	    throws B2BException
	{
		DAOManager ldm_manager;
		ldm_manager = DaoManagerFactory.getDAOManager();

		try
		{
			if(ac_parametros != null)
			{
				if(ab_accion)
				{
					ac_parametros.setIdUsuarioCreacion(as_usuario);
					ac_parametros.setIpCreacion(as_ipRemota);
				}
				else
				{
					ac_parametros.setIdUsuarioModificacion(as_usuario);
					ac_parametros.setIpModificacion(as_ipRemota);
				}

				DaoCreator.getZonaRegistralDAO(ldm_manager).insertOrUpdate(ac_parametros, ab_accion);
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("salvarZonaRegistral", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}
	}

	/**
	 * Método encargado de validar el excel de cargue masivo para persona entidad.
	 *
	 * @param aba_archivo Archivo excel a validar
	 * @param as_nombreFile String con el nombre del archivo
	 * @param as_userID String con el id del usuario
	 * @param as_ipLocal String con la IP local
	 * @return Collection<Persona> con las personas validadas
	 * @throws B2BException Señala que se ha producido una excepción
	 * @throws IOException Señala que se ha producido una excepción de E / S.
	 */
	public synchronized Collection<Persona> validarExcelPersonaEntidad(
	    byte[] aba_archivo, String as_nombreFile, String as_userID, String as_ipLocal
	)
	    throws B2BException, IOException
	{
		DAOManager                 ldm_manager;
		Collection<Persona>        lcp_personaIngresados;
		InteresadoDocumentoTipoDAO lidtd_interesadoDocumentoTipoDAO;

		ldm_manager                          = DaoManagerFactory.getDAOManager();
		lcp_personaIngresados                = new ArrayList<Persona>();
		lidtd_interesadoDocumentoTipoDAO     = DaoCreator.getInteresadoDocumentoTipoDAO(ldm_manager);

		try
		{
			Sheet    lsh_hoja;
			Workbook lw_libro;

			if(StringUtils.isValidString(as_nombreFile))
			{
				InputStream lis_archivoExcel;

				lis_archivoExcel = new ByteArrayInputStream(aba_archivo);

				if(as_nombreFile.toUpperCase().endsWith(ExtensionCommon.XLS_PUNTO))
					lw_libro = new HSSFWorkbook(lis_archivoExcel);
				else
					lw_libro = new XSSFWorkbook(lis_archivoExcel);

				lsh_hoja = lw_libro.getSheetAt(0);
			}
			else
				throw new B2BException(ErrorKeys.ARCHIVO_NOMBRE);

			if(lsh_hoja != null)
			{
				int li_ultimaFila;

				li_ultimaFila = lsh_hoja.getLastRowNum();

				if(li_ultimaFila <= 1000)
				{
					int ll_numcol;

					ll_numcol = 7;

					for(int ii_fila = 0; ii_fila <= li_ultimaFila; ii_fila++)
					{
						if(ii_fila != 0)
						{
							String        ls_temp;
							Persona       lp_persona;
							Row           lr_filaActual;
							StringBuilder lsb_mensaje;

							lp_persona        = new Persona();
							lr_filaActual     = lsh_hoja.getRow(ii_fila);
							lsb_mensaje       = new StringBuilder();

							for(int li_celda = 0; li_celda < ll_numcol; li_celda++)
							{
								try
								{
									if(li_celda == 0)
									{
										ls_temp = validarStringCeldaExcel(
											    lr_filaActual, ii_fila, li_celda, "Tipo de documento", true
											);

										InteresadoDocumentoTipo lidt_interesadoDocumentoTipo;

										lidt_interesadoDocumentoTipo = lidtd_interesadoDocumentoTipoDAO.findById(
											    ls_temp
											);

										if(lidt_interesadoDocumentoTipo != null)
											lp_persona.setIdDocumentoTipo(
											    lidt_interesadoDocumentoTipo.getIdDocumentoTipo()
											);

										else
											throw new B2BException(ErrorKeys.ERROR_ATTR_USUARIO_E1);
									}

									else if(li_celda == 1)
									{
										String ls_numDocumento;

										ls_numDocumento = validarStringCeldaExcel(
											    lr_filaActual, ii_fila, li_celda, "Numero de documento", true
											);

										if(StringUtils.isValidString(ls_numDocumento))
										{
											lp_persona.setNumeroDocumento(ls_numDocumento);

											if(ls_numDocumento.length() > 15)
												throw new B2BException(ErrorKeys.ERROR_NIT_SOBREPASA);
											else if(
											    ls_numDocumento.contains(IdentificadoresCommon.SIMBOLO_COMA_TXT)
												    || ls_numDocumento.contains(IdentificadoresCommon.PUNTO)
											)
												throw new B2BException(ErrorKeys.ERROR_NIT_INGRESADO_PUNTOS_COMAS);
										}
										else
											throw new B2BException(ErrorKeys.ERROR_DOCUMENTO_NO_VALIDO);
									}

									else if(li_celda == 2)
									{
										ls_temp = validarStringCeldaExcel(
											    lr_filaActual, ii_fila, li_celda, "Correo electronico", true
											);

										if(StringUtils.isValidString(ls_temp))
										{
											lp_persona.setCorreoElectronico(ls_temp);

											if(!ExpresionRegular.getExpresionRegular().esCorreoElectronico(ls_temp))
												throw new B2BException(ErrorKeys.ERROR_ATTR_USUARIO_E5);
										}
										else
											throw new B2BException(ErrorKeys.ERROR_ATTR_USUARIO_E5);
									}

									else if(li_celda == 3)
									{
										ls_temp = validarStringCeldaExcel(
											    lr_filaActual, ii_fila, li_celda, "Primer Nombre", true
											);

										if(StringUtils.isValidString(ls_temp))
											lp_persona.setPrimerNombre(ls_temp);
										else
											throw new B2BException(ErrorKeys.ERROR_PRIMER_NOMBRE_NO_VALIDO);
									}

									else if(li_celda == 4)
									{
										ls_temp = validarStringCeldaExcel(
											    lr_filaActual, ii_fila, li_celda, "Segundo Nombre", false
											);

										lp_persona.setSegundoNombre(StringUtils.isValidString(ls_temp) ? ls_temp : "");
									}

									else if(li_celda == 5)
									{
										ls_temp = validarStringCeldaExcel(
											    lr_filaActual, ii_fila, li_celda, "Primer Apellido", true
											);

										if(StringUtils.isValidString(ls_temp))
											lp_persona.setPrimerApellido(ls_temp);
										else
											throw new B2BException(ErrorKeys.ERROR_PRIMER_APELLIDO_NO_VALIDO);
									}

									else if(li_celda == 6)
									{
										ls_temp = validarStringCeldaExcel(
											    lr_filaActual, ii_fila, li_celda, "Segundo Apellido", false
											);

										lp_persona.setSegundoApellido(
										    StringUtils.isValidString(ls_temp) ? ls_temp : ""
										);
									}
								}
								catch(B2BException ab2be_e)
								{
									lp_persona.setErrorCargue(true);
									lsb_mensaje.append(ab2be_e.getMessage());
								}
							}

							if(StringUtils.isValidString(lsb_mensaje.toString()))
								lp_persona.setErrorPersonaMasiva(lsb_mensaje.toString());

							String ls_usuario;

							ls_usuario = findUsuarioPersona(lp_persona, lcp_personaIngresados, null);

							if(StringUtils.isValidString(ls_usuario))
							{
								lp_persona.setUsuario(ls_usuario);
								lp_persona.setUsuarioCargue(ls_usuario);
								lp_persona.setIdTipoPersona(TipoPersonaCommon.NATURAL);
								lp_persona.setNombreTipoPersona("NATURAL");
							}

							lcp_personaIngresados.add(lp_persona);
						}
					}
				}
				else
					throw new B2BException(ErrorKeys.ERROR_LIMITE_REGISTROS);
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("validarExcelIntervinientesMasivos", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		if(lcp_personaIngresados.isEmpty())
			lcp_personaIngresados = null;

		return lcp_personaIngresados;
	}

	/**
	 * Validar tipo documento.
	 *
	 * @param as_tipoDocumento correspondiente al valor del tipo de objeto String
	 * @return verdadero, si se cumple la condicion, de lo contario retorna falso
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public synchronized boolean validarTipoDocumento(String as_tipoDocumento)
	    throws B2BException
	{
		DAOManager ldm_manager;
		boolean    lb_tipoDocumentoValido;

		ldm_manager                = DaoManagerFactory.getDAOManager();
		lb_tipoDocumentoValido     = false;

		try
		{
			int li_resultadoCount;
			li_resultadoCount = DaoCreator.getTipoDocumentoPublicoDAO(ldm_manager).validarTipoDocumento(
				    as_tipoDocumento
				);

			if(li_resultadoCount > 0)
				lb_tipoDocumentoValido = true;
		}

		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("validarTipoDocumento", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lb_tipoDocumentoValido;
	}

	/**
	 * Cargar lista archivos.
	 *
	 * @param as_url de as url
	 * @return el valor de collection
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	private synchronized Collection<Archivo> cargarListaArchivos(String as_url)
	    throws B2BException
	{
		Collection<Archivo> lca_return;

		lca_return = null;

		try
		{
			if(StringUtils.isValidString(as_url))
			{
				File lf_archivo = new File(as_url);

				if(lf_archivo != null)
				{
					if(lf_archivo.isDirectory())
					{
						String[] lsa_archivos;

						lsa_archivos = lf_archivo.list();

						if(CollectionUtils.isValidCollection(lsa_archivos))
						{
							lca_return = new ArrayList<Archivo>();

							{
								Archivo laa_archivo;

								laa_archivo = new Archivo();

								laa_archivo.setNombre("Regresar...");
								laa_archivo.setUrl(as_url);
								laa_archivo.setRegresar(true);

								lca_return.add(laa_archivo);
							}

							for(String ls_iterator : lsa_archivos)
							{
								if(StringUtils.isValidString(ls_iterator))
								{
									File lf_archivoTemp;

									lf_archivoTemp = new File(
										    as_url + IdentificadoresCommon.SIMBOLO_SLASH + ls_iterator
										);

									if(lf_archivoTemp != null)
									{
										Archivo la_archivo;

										la_archivo = new Archivo();

										la_archivo.setNombre(ls_iterator);
										la_archivo.setFechaModificacion(new Date(lf_archivoTemp.lastModified()));
										la_archivo.setUrl(lf_archivoTemp.getAbsolutePath());
										lca_return.add(la_archivo);
									}
								}
							}
						}
					}
				}
			}
		}
		catch(Exception lb2be_e)
		{
			clh_LOGGER.error("cargarListaArchivos", lb2be_e);
		}

		return lca_return;
	}

	/**
	 * Método para salvar una inserción o borrar un registro de la tabla Opcion Etapa.
	 *
	 * @param adm_manager          de la consulta
	 * @param ao_parametros            objeto a insertar o modificar
	 * @param ace_opcionEtapa            objeto a desea insertar o modificar
	 * @param as_ip            ip de donde se invoca el metodo de salvar
	 * @param as_idUsuario de as id usuario
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	private synchronized void salvarOpcionEtapas(
	    DAOManager adm_manager, Opcion ao_parametros, Collection<Etapa> ace_opcionEtapa, String as_ip,
	    String as_idUsuario
	)
	    throws B2BException
	{
		try
		{
			if((ao_parametros != null))
			{
				OpcionEtapaDAO loed_opcionEtapaDAO;
				String         ls_idOpcion;

				loed_opcionEtapaDAO     = DaoCreator.getOpcionEtapaDAO(adm_manager);
				ls_idOpcion             = ao_parametros.getIdOpcion();

				if(StringUtils.isValidString(ls_idOpcion))
				{
					{
						Collection<OpcionEtapa> lcoe_opcionEtapaSource;

						lcoe_opcionEtapaSource = loed_opcionEtapaDAO.findByIdOpcion(ls_idOpcion);

						if(CollectionUtils.isValidCollection(lcoe_opcionEtapaSource))
							loed_opcionEtapaDAO.delete(ls_idOpcion);
					}

					String ls_cambio;

					ls_cambio = ace_opcionEtapa.toString();

					if(StringUtils.isValidString(ls_cambio))
					{
						String ls_llaves;
						ls_llaves     = ls_cambio.replace(
							    IdentificadoresCommon.LLAVE_INICIAL, IdentificadoresCommon.DATO_INVALIDO
							);
						ls_llaves     = ls_llaves.replace(
							    IdentificadoresCommon.LLAVE_FINAL, IdentificadoresCommon.DATO_INVALIDO
							);
						ls_llaves     = ls_llaves.replace(
							    IdentificadoresCommon.ESPACIO_VACIO, IdentificadoresCommon.DATO_INVALIDO
							);

						if(StringUtils.isValidString(ls_llaves))
						{
							Collection<String> lcs_targetOpcion;
							lcs_targetOpcion = ListadoCodigosConstantes.generarCodigosCollection(ls_llaves);

							if(CollectionUtils.isValidCollection(lcs_targetOpcion))
							{
								for(String ls_temp : lcs_targetOpcion)
								{
									if(StringUtils.isValidString(ls_temp))
										loed_opcionEtapaDAO.insert(
										    new OpcionEtapa(
										        NumericUtils.getLong(ls_temp), ls_idOpcion, as_ip, as_idUsuario
										    )
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
			adm_manager.setRollbackOnly();

			clh_LOGGER.error("salvarOpcionEtapas", lb2be_e);

			throw lb2be_e;
		}
	}

	/**
	 * Salvar Rol Opcion Componente Padre.
	 *
	 * @param as_rol correspondiente al valor del tipo de objeto string
	 * @param as_opcion correspondiente al valor del tipo de objeto string
	 * @param as_idUsuario correspondiente al valor del tipo de objeto string
	 * @param as_ipUsuario correspondiente al valor del tipo de objeto string
	 * @param arod_rolOpcionDAO de arod rol opcion DAO
	 * @param aod_opcionDAO de aod opcion DAO
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	private void salvarRolOpcionComponentePadre(
	    String as_rol, String as_opcion, String as_idUsuario, String as_ipUsuario, RolOpcionDAO arod_rolOpcionDAO,
	    OpcionDAO aod_opcionDAO
	)
	    throws B2BException
	{
		try
		{
			Opcion lo_opcion;

			lo_opcion = aod_opcionDAO.findById(as_opcion);

			if(lo_opcion != null)
			{
				String ls_opcionPadre;

				ls_opcionPadre = lo_opcion.getIdOpcionPadre();

				if(StringUtils.isValidString(ls_opcionPadre))
					salvarRolOpcionComponentePadre(
					    as_rol, ls_opcionPadre, as_idUsuario, as_ipUsuario, arod_rolOpcionDAO, aod_opcionDAO
					);

				RolOpcion lro_rolOpcion;
				Date      ld_date;

				ld_date           = DateUtils.getTimestamp();
				lro_rolOpcion     = arod_rolOpcionDAO.findRolOpcionById(as_rol, as_opcion);

				if(lro_rolOpcion == null)
				{
					if(ld_date != null)
						arod_rolOpcionDAO.insert(
						    new RolOpcion(as_rol, as_opcion, as_ipUsuario, as_idUsuario, ld_date, EstadoCommon.S)
						);
				}
				else
				{
					String ls_activoRolOpcion;

					ls_activoRolOpcion = lro_rolOpcion.getActivo();

					if(
					    StringUtils.isValidString(ls_activoRolOpcion)
						    && ls_activoRolOpcion.equalsIgnoreCase(EstadoCommon.N)
					)
					{
						lro_rolOpcion.setFechaModificacion(ld_date);
						lro_rolOpcion.setIpCreacion(as_ipUsuario);
						lro_rolOpcion.setIdUsuarioCreacion(as_idUsuario);

						arod_rolOpcionDAO.updateActivo(lro_rolOpcion);
					}
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			throw lb2be_e;
		}
	}

	/**
	 * Retorna el valor del objeto de String al validar la celda.
	 *
	 * @param ar_registro correspondiente al valor del tipo de objeto Row
	 * @param ai_fila correspondiente al valor del tipo de objeto int
	 * @param ai_celda correspondiente al valor del tipo de objeto int
	 * @param as_nombreCelda correspondiente al valor del tipo de objeto String
	 * @param ab_requerido correspondiente al valor del tipo de objeto boolean
	 * @return devuelve el valor de String
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	private synchronized String validarStringCeldaExcel(
	    Row ar_registro, int ai_fila, int ai_celda, String as_nombreCelda, boolean ab_requerido
	)
	    throws B2BException
	{
		String ls_tmp;

		ls_tmp = null;

		if(ar_registro != null)
		{
			Cell lc_tmp;

			lc_tmp = ar_registro.getCell(ai_celda);

			if(lc_tmp != null)
			{
				if(lc_tmp.getCellType() == Cell.CELL_TYPE_NUMERIC)
					ls_tmp = StringUtils.getString(lc_tmp.getNumericCellValue());
				else if(lc_tmp.getCellType() == Cell.CELL_TYPE_STRING)
					ls_tmp = StringUtils.getString(lc_tmp.getStringCellValue());

				if(!StringUtils.isValidString(ls_tmp) && ab_requerido)
					throw new B2BException(as_nombreCelda + " sin registros.");
			}
			else if(ab_requerido)
				throw new B2BException(as_nombreCelda + " sin registros.");
		}
		else
			throw new B2BException("Sin Registros");

		return ls_tmp;
	}
}
