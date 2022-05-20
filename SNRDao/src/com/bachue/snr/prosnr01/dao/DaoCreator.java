package com.bachue.snr.prosnr01.dao;

import com.b2bsg.common.dataAccess2.DAOManager;

import com.b2bsg.common.exception.B2BException;

import com.bachue.snr.prosnr01.dao.acc.*;
import com.bachue.snr.prosnr01.dao.admHistoricosMisional.AdmLiquidacionDAO;
import com.bachue.snr.prosnr01.dao.antiguo.sistema.AntiguoSistemaDAO;
import com.bachue.snr.prosnr01.dao.aut.ComponentesDAO;
import com.bachue.snr.prosnr01.dao.aut.OpcionDAO;
import com.bachue.snr.prosnr01.dao.aut.OpcionEtapaDAO;
import com.bachue.snr.prosnr01.dao.aut.RolDAO;
import com.bachue.snr.prosnr01.dao.aut.RolOpcionDAO;
import com.bachue.snr.prosnr01.dao.aut.UsuarioCirculoDAO;
import com.bachue.snr.prosnr01.dao.aut.UsuarioDAO;
import com.bachue.snr.prosnr01.dao.aut.UsuarioRolDAO;
import com.bachue.snr.prosnr01.dao.bgn.AlertaGeneradaDAO;
import com.bachue.snr.prosnr01.dao.bgn.AlertaTierrasDAO;
import com.bachue.snr.prosnr01.dao.bgn.DocumentoDAO;
import com.bachue.snr.prosnr01.dao.bgn.ImagenesDAO;
import com.bachue.snr.prosnr01.dao.bng.AlertaMSGDAO;
import com.bachue.snr.prosnr01.dao.bng.AlertaTComunidadDAO;
import com.bachue.snr.prosnr01.dao.bng.AlertaTInactivacionDAO;
import com.bachue.snr.prosnr01.dao.bng.AlertaTPredioDAO;
import com.bachue.snr.prosnr01.dao.bng.AnotacionPredioCiudadanoDAO;
import com.bachue.snr.prosnr01.dao.bng.AnotacionPredioDAO;
import com.bachue.snr.prosnr01.dao.bng.AreaPredioDAO;
import com.bachue.snr.prosnr01.dao.bng.ComplementacionPredioDAO;
import com.bachue.snr.prosnr01.dao.bng.DetalleAreaPredioDAO;
import com.bachue.snr.prosnr01.dao.bng.DireccionPredioDAO;
import com.bachue.snr.prosnr01.dao.bng.LinderoPredioDAO;
import com.bachue.snr.prosnr01.dao.bng.PredioRegistroDAO;
import com.bachue.snr.prosnr01.dao.bng.PredioSegregadoDAO;
import com.bachue.snr.prosnr01.dao.bng.ProcAntSistemaATDAO;
import com.bachue.snr.prosnr01.dao.bng.PropietarioDAO;
import com.bachue.snr.prosnr01.dao.bng.SalvedadAnotacionDAO;
import com.bachue.snr.prosnr01.dao.bng.SalvedadPredioDAO;
import com.bachue.snr.prosnr01.dao.bng.SalvedadesDAO;
import com.bachue.snr.prosnr01.dao.col.DominioRrrDAO;
import com.bachue.snr.prosnr01.dao.col.EstadoNupreDAO;
import com.bachue.snr.prosnr01.dao.col.EstadoRegistroDAO;
import com.bachue.snr.prosnr01.dao.col.InteresadoDocumentoTipoDAO;
import com.bachue.snr.prosnr01.dao.col.InteresadoNaturalGeneroDAO;
import com.bachue.snr.prosnr01.dao.col.ParteInteresadaDAO;
import com.bachue.snr.prosnr01.dao.col.PredioTipoDAO;
import com.bachue.snr.prosnr01.dao.col.TipoAreaDAO;
import com.bachue.snr.prosnr01.dao.col.TipoCanalOrigenDAO;
import com.bachue.snr.prosnr01.dao.col.TipoEstadoLiquidacionDAO;
import com.bachue.snr.prosnr01.dao.col.TipoRrrDAO;
import com.bachue.snr.prosnr01.dao.col.TipoUsoSueloDAO;
import com.bachue.snr.prosnr01.dao.consulta.documento.ConsultaDocumentoDAO;
import com.bachue.snr.prosnr01.dao.consulta.estado.predio.ConsultaEstadoPredioDAO;
import com.bachue.snr.prosnr01.dao.consulta.reparto.calificacion.ConsultaRepartoCalificacionDAO;
import com.bachue.snr.prosnr01.dao.consulta.solicitud.ConsultaSolicitudDAO;
import com.bachue.snr.prosnr01.dao.consulta.trazabilidad.ConsultaTrazabilidadDAO;
import com.bachue.snr.prosnr01.dao.consultas.ConsultaPorCriteriosDAO;
import com.bachue.snr.prosnr01.dao.copias.SolicitudCopiasDAO;
import com.bachue.snr.prosnr01.dao.htr.EstadoPredioDAO;
import com.bachue.snr.prosnr01.dao.notificaciones.PersonaNotificacionDAO;
import com.bachue.snr.prosnr01.dao.pgn.ActividadEconomicaDAO;
import com.bachue.snr.prosnr01.dao.pgn.AlertaNaturalezaJuridicaDAO;
import com.bachue.snr.prosnr01.dao.pgn.CampoCriterioBusquedaDAO;
import com.bachue.snr.prosnr01.dao.pgn.CamposCertificadoDAO;
import com.bachue.snr.prosnr01.dao.pgn.CamposConsultaDAO;
import com.bachue.snr.prosnr01.dao.pgn.CanalDAO;
import com.bachue.snr.prosnr01.dao.pgn.CanalOrigenServicioDAO;
import com.bachue.snr.prosnr01.dao.pgn.CatastroDAO;
import com.bachue.snr.prosnr01.dao.pgn.CausalCorreccionDAO;
import com.bachue.snr.prosnr01.dao.pgn.CausalMayorValorDAO;
import com.bachue.snr.prosnr01.dao.pgn.CausalNegacionCopiasDAO;
import com.bachue.snr.prosnr01.dao.pgn.CausalRechazoRecursoDAO;
import com.bachue.snr.prosnr01.dao.pgn.CausalReimpresionDAO;
import com.bachue.snr.prosnr01.dao.pgn.CirculoActAdminDAO;
import com.bachue.snr.prosnr01.dao.pgn.CirculoCatastroDAO;
import com.bachue.snr.prosnr01.dao.pgn.CirculoFestivoDAO;
import com.bachue.snr.prosnr01.dao.pgn.CirculoRegistralDao;
import com.bachue.snr.prosnr01.dao.pgn.CondicionTarifaDAO;
import com.bachue.snr.prosnr01.dao.pgn.ConstantesDAO;
import com.bachue.snr.prosnr01.dao.pgn.DatosPlantillaDocumentoDAO;
import com.bachue.snr.prosnr01.dao.pgn.DepartamentoDAO;
import com.bachue.snr.prosnr01.dao.pgn.DependenciaSNRDAO;
import com.bachue.snr.prosnr01.dao.pgn.DetalleProcesoConsultaDAO;
import com.bachue.snr.prosnr01.dao.pgn.EstadosDAO;
import com.bachue.snr.prosnr01.dao.pgn.EtapaDAO;
import com.bachue.snr.prosnr01.dao.pgn.FasesDAO;
import com.bachue.snr.prosnr01.dao.pgn.FestivoNacionalDAO;
import com.bachue.snr.prosnr01.dao.pgn.GobernacionDAO;
import com.bachue.snr.prosnr01.dao.pgn.InstanciaConsultaDAO;
import com.bachue.snr.prosnr01.dao.pgn.LibroAntiguoSistemaDao;
import com.bachue.snr.prosnr01.dao.pgn.LibroTestamentoDAO;
import com.bachue.snr.prosnr01.dao.pgn.LineaProduccionDAO;
import com.bachue.snr.prosnr01.dao.pgn.MedidaAreaDAO;
import com.bachue.snr.prosnr01.dao.pgn.MedioRecaudoDAO;
import com.bachue.snr.prosnr01.dao.pgn.MensajeRespuestaDAO;
import com.bachue.snr.prosnr01.dao.pgn.MotivoTramiteDAO;
import com.bachue.snr.prosnr01.dao.pgn.MunicipioDAO;
import com.bachue.snr.prosnr01.dao.pgn.NumeracionOficiosCirculoDAO;
import com.bachue.snr.prosnr01.dao.pgn.NumeracionResolucionCirculoDAO;
import com.bachue.snr.prosnr01.dao.pgn.OficinaOrigenDAO;
import com.bachue.snr.prosnr01.dao.pgn.OrigenDAO;
import com.bachue.snr.prosnr01.dao.pgn.PaisDAO;
import com.bachue.snr.prosnr01.dao.pgn.PlantillaComunicacionDAO;
import com.bachue.snr.prosnr01.dao.pgn.PlantillaDAO;
import com.bachue.snr.prosnr01.dao.pgn.PlantillaNotificacionDAO;
import com.bachue.snr.prosnr01.dao.pgn.ProcesoAutomaticoDAO;
import com.bachue.snr.prosnr01.dao.pgn.ProcesoCanalDAO;
import com.bachue.snr.prosnr01.dao.pgn.ProcesoConsultaDAO;
import com.bachue.snr.prosnr01.dao.pgn.RegionalDAO;
import com.bachue.snr.prosnr01.dao.pgn.ReintentosDAO;
import com.bachue.snr.prosnr01.dao.pgn.ReportesDAO;
import com.bachue.snr.prosnr01.dao.pgn.ResultadoConsultaDAO;
import com.bachue.snr.prosnr01.dao.pgn.TagPlantillaResolucionDAO;
import com.bachue.snr.prosnr01.dao.pgn.TarifaFijaDAO;
import com.bachue.snr.prosnr01.dao.pgn.TipoActoCondicionDAO;
import com.bachue.snr.prosnr01.dao.pgn.TipoActoEjecutoriaDAO;
import com.bachue.snr.prosnr01.dao.pgn.TipoActoProhibicionDAO;
import com.bachue.snr.prosnr01.dao.pgn.TipoCriterioBusquedaDAO;
import com.bachue.snr.prosnr01.dao.pgn.TipoCriterioBusquedaPGNDAO;
import com.bachue.snr.prosnr01.dao.pgn.TipoDecisionRecursoDAO;
import com.bachue.snr.prosnr01.dao.pgn.TipoDocumentalDAO;
import com.bachue.snr.prosnr01.dao.pgn.TipoDocumentoPublicoDAO;
import com.bachue.snr.prosnr01.dao.pgn.TipoEntidadDAO;
import com.bachue.snr.prosnr01.dao.pgn.TipoIntegracionGobernacionDAO;
import com.bachue.snr.prosnr01.dao.pgn.TipoOficinaDAO;
import com.bachue.snr.prosnr01.dao.pgn.TipoOperacionDAO;
import com.bachue.snr.prosnr01.dao.pgn.TipoPersonaDAO;
import com.bachue.snr.prosnr01.dao.pgn.TipoRecursoDAO;
import com.bachue.snr.prosnr01.dao.pgn.TipoRequiereMatriculaDAO;
import com.bachue.snr.prosnr01.dao.pgn.TipoTestamentoDAO;
import com.bachue.snr.prosnr01.dao.pgn.TramiteVisitaDAO;
import com.bachue.snr.prosnr01.dao.pgn.TramiteVisitaRolDAO;
import com.bachue.snr.prosnr01.dao.pgn.TramiteVisitaTipoDAO;
import com.bachue.snr.prosnr01.dao.pgn.UbicacionSalvadoDAO;
import com.bachue.snr.prosnr01.dao.pgn.UnidadTiempoVencimientoDAO;
import com.bachue.snr.prosnr01.dao.pgn.UsuarioLineaDAO;
import com.bachue.snr.prosnr01.dao.pgn.VeredaDAO;
import com.bachue.snr.prosnr01.dao.pgn.ZonaRegistralDAO;
import com.bachue.snr.prosnr01.dao.png.CamposCorreccionDAO;
import com.bachue.snr.prosnr01.dao.png.CirculoOrigenDestinoDAO;
import com.bachue.snr.prosnr01.dao.png.ComunidadesEtnicasDAO;
import com.bachue.snr.prosnr01.dao.png.CoordenadaDAO;
import com.bachue.snr.prosnr01.dao.png.DominioNaturalezaJuridicaDAO;
import com.bachue.snr.prosnr01.dao.png.EntidadesAlertaDAO;
import com.bachue.snr.prosnr01.dao.png.EstadoActividadDAO;
import com.bachue.snr.prosnr01.dao.png.EstadoAnotacionDAO;
import com.bachue.snr.prosnr01.dao.png.EstadoPredioDao;
import com.bachue.snr.prosnr01.dao.png.GrupoNaturalezaJuridicaDAO;
import com.bachue.snr.prosnr01.dao.png.LetraEjeDAO;
import com.bachue.snr.prosnr01.dao.png.NaturalezaJuridicaDAO;
import com.bachue.snr.prosnr01.dao.png.TarifaAlertaDAO;
import com.bachue.snr.prosnr01.dao.png.TipoAnotacionDAO;
import com.bachue.snr.prosnr01.dao.png.TipoEjeDAO;
import com.bachue.snr.prosnr01.dao.png.TipoNotificacionDAO;
import com.bachue.snr.prosnr01.dao.png.UsuarioEtapaDAO;
import com.bachue.snr.prosnr01.dao.proc.ProcedimientosDAO;
import com.bachue.snr.prosnr01.dao.userObjects.UserObjectsDAO;
import com.bachue.snr.prosnr01.dao.util.ConsultasDAO;
import com.bachue.snr.prosnr01.dao.util.UtilDAO;
import com.bachue.snr.prosnr01.dao.view.CRPSCabeceraDAO;
import com.bachue.snr.prosnr01.dao.view.CRPSDetalleDAO;
import com.bachue.snr.prosnr01.dao.view.EntidadesEspecialesDAO;
import com.bachue.snr.prosnr01.dao.view.LiquidacionesHistoricosFolioDAO;
import com.bachue.snr.prosnr01.dao.view.LiquidacionesHistoricosSirDAO;
import com.bachue.snr.prosnr01.dao.view.PagosFolioDAO;
import com.bachue.snr.prosnr01.dao.view.PagosSirDAO;
import com.bachue.snr.prosnr01.dao.view.TrazabilidadTurnoFolioDAO;
import com.bachue.snr.prosnr01.dao.view.TrazabilidadTurnoFolioNotaDevolutivaDAO;
import com.bachue.snr.prosnr01.dao.view.TrazabilidadTurnoSirDAO;
import com.bachue.snr.prosnr01.dao.view.TrazabilidadTurnoSirNotaDevolutivaDAO;
import com.bachue.snr.prosnr01.dao.view.ViewDataReportDAO;

import com.bachue.snr.prosnr02.dao.acc.ProcesoTrabajoDAO;
import com.bachue.snr.prosnr02.dao.acc.SubProcesoTrabajoDAO;
import com.bachue.snr.prosnr02.dao.acc.SubProcesoVersionTrabajoDAO;
import com.bachue.snr.prosnr02.dao.pgn.EtapaTrabajoDAO;
import com.bachue.snr.prosnr02.dao.pgn.MotivoTramiteTrabajoDAO;
import com.bachue.snr.prosnr02.dao.pgn.ReglaNegocioDAO;
import com.bachue.snr.prosnr02.dao.pgn.TopologiaReglaDAO;

import com.bachue.snr.prosnr04.dao.acc.NotificacionPagoDAO;
import com.bachue.snr.prosnr04.dao.acc.NotificacionPagoRecibidaDAO;
import com.bachue.snr.prosnr04.dao.npa.DatosLiquidacionDAO;
import com.bachue.snr.prosnr04.dao.npa.RegistrarPagoDAO;
import com.bachue.snr.prosnr04.dao.npa.TipoCriterioDAO;
import com.bachue.snr.prosnr04.dao.npa.TipoServicioDAO;
import com.bachue.snr.prosnr04.dao.pgn.EntidadRecaudadoraDAO;
import com.bachue.snr.prosnr04.dao.pgn.PuntoRecaudoDAO;
import com.bachue.snr.prosnr04.dao.pgn.PuntoRecaudoTipoRecaudoDAO;
import com.bachue.snr.prosnr04.dao.pgn.SucursalCanalOrigenServicioDAO;
import com.bachue.snr.prosnr04.dao.pgn.TipoRecaudoDAO;

import com.bachue.snr.prosnr10.dao.acc.ServicioActualizacionDesdeCatastrosDAO;
import com.bachue.snr.prosnr10.dao.acc.ServicioActualizacionDesdeCatastrosDetalleDAO;
import com.bachue.snr.prosnr10.dao.acc.ServicioActualizacionHaciaCatastrosDAO;
import com.bachue.snr.prosnr10.dao.acc.ServicioActualizacionHaciaCatastrosDetalleDAO;

import com.bachue.snr.prosnr21.dao.conciliaciones.ArchivoConciliacionDAO;
import com.bachue.snr.prosnr21.dao.png.ACHDAO;
import com.bachue.snr.prosnr21.dao.png.AfectacionPrestacionServicioDAO;
import com.bachue.snr.prosnr21.dao.png.ArchivoDAO;
import com.bachue.snr.prosnr21.dao.png.ConArchivoDAO;
import com.bachue.snr.prosnr21.dao.png.ConDocumentosDAO;
import com.bachue.snr.prosnr21.dao.png.ConImagenesDAO;
import com.bachue.snr.prosnr21.dao.png.ConInconsistenciaDAO;
import com.bachue.snr.prosnr21.dao.png.ConPartidaADAO;
import com.bachue.snr.prosnr21.dao.png.ConPlantillaMensajeDAO;
import com.bachue.snr.prosnr21.dao.png.ConResumenDAO;
import com.bachue.snr.prosnr21.dao.png.ConSiifDetalleDAO;
import com.bachue.snr.prosnr21.dao.png.ConSiifMaestroDAO;
import com.bachue.snr.prosnr21.dao.png.CuentaAnalistaDAO;
import com.bachue.snr.prosnr21.dao.png.DRXCTotalBCODAO;
import com.bachue.snr.prosnr21.dao.png.DRXCTotalCTADAO;
import com.bachue.snr.prosnr21.dao.png.EntidadRecaudadoraConciliacionDAO;
import com.bachue.snr.prosnr21.dao.png.EntidadRecaudadoraCuentaDAO;
import com.bachue.snr.prosnr21.dao.png.ExpedienteDAO;
import com.bachue.snr.prosnr21.dao.png.ExtractoBancoMensualDAO;
import com.bachue.snr.prosnr21.dao.png.ExtractoDiarioDAO;
import com.bachue.snr.prosnr21.dao.png.ExtractoMensualDAO;
import com.bachue.snr.prosnr21.dao.png.IncAviAleMenNotDAO;
import com.bachue.snr.prosnr21.dao.png.MulticashCabeceraDAO;
import com.bachue.snr.prosnr21.dao.png.MulticashDetalleDAO;
import com.bachue.snr.prosnr21.dao.png.PeriodicidadDAO;
import com.bachue.snr.prosnr21.dao.png.PeriodoCorteDAO;
import com.bachue.snr.prosnr21.dao.png.ProcesoConciliacionDAO;
import com.bachue.snr.prosnr21.dao.png.RPTProgramaDAO;
import com.bachue.snr.prosnr21.dao.png.RubroDAO;
import com.bachue.snr.prosnr21.dao.png.RubroHomologacionDAO;
import com.bachue.snr.prosnr21.dao.png.SIIFCatalogoDAO;


/**
 * Clase que contiene todos las propiedades de DaoCreator para obtener los diferentes tipos de DAO a utilizar.
 *
 * @author dbeltran
 */
public class DaoCreator
{
	/**
	 * Retorna la clase encargada de las interacciones con base de datos para la tabla SDB_CON_ACH del módulo de conciliaciones.
	 *
	 * @param adm_manager Contexto transaccional al que se asociara el DAO retornado
	 * @return clase encargada de las interacciones con base de datos para la tabla SDB_CON_ACH del módulo de conciliaciones
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public static ACHDAO getACHDAO(DAOManager adm_manager)
	    throws B2BException
	{
		return (ACHDAO)adm_manager.getDAO(ACHDAO.class);
	}

	/**
	 * Retorna el valor de acc anotacion predio ciudadano DAO.
	 *
	 * @param adm_manager correspondiente al valor del tipo de objeto DAOManager
	 * @return el valor de acc anotacion predio ciudadano DAO
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public static com.bachue.snr.prosnr01.dao.acc.AnotacionPredioCiudadanoDAO getAccAnotacionPredioCiudadanoDAO(
	    DAOManager adm_manager
	)
	    throws B2BException
	{
		return (com.bachue.snr.prosnr01.dao.acc.AnotacionPredioCiudadanoDAO)adm_manager.getDAO(
		    com.bachue.snr.prosnr01.dao.acc.AnotacionPredioCiudadanoDAO.class
		);
	}

	/**
	 * Retorna el valor de acc anotacion predio DAO.
	 *
	 * @param adm_manager correspondiente al valor del tipo de objeto DAOManager
	 * @return el valor de acc anotacion predio DAO
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public static com.bachue.snr.prosnr01.dao.acc.AnotacionPredioDAO getAccAnotacionPredioDAO(DAOManager adm_manager)
	    throws B2BException
	{
		return (com.bachue.snr.prosnr01.dao.acc.AnotacionPredioDAO)adm_manager.getDAO(
		    com.bachue.snr.prosnr01.dao.acc.AnotacionPredioDAO.class
		);
	}

	/**
	 * Retorna el valor de acc area predio DAO.
	 *
	 * @param adm_manager correspondiente al valor del tipo de objeto DAOManager
	 * @return el valor de acc area predio DAO
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public static AccAreaPredioDAO getAccAreaPredioDAO(DAOManager adm_manager)
	    throws B2BException
	{
		return (AccAreaPredioDAO)adm_manager.getDAO(AccAreaPredioDAO.class);
	}

	/**
	 * Retorna el valor de getAccCausalRechazoRecursoDAO.
	 *
	 * @param adm_manager correspondiente al valor del tipo de objeto DAOManager
	 * @return el valor de getAccCausalRechazoRecursoDAO
	 * @throws B2BException Señala que se ha generado una excepción
	 */
	public static AccCausalRechazoRecursoDAO getAccCausalRechazoRecursoDAO(DAOManager adm_manager)
	    throws B2BException
	{
		return (AccCausalRechazoRecursoDAO)adm_manager.getDAO(AccCausalRechazoRecursoDAO.class);
	}

	/**
	 * Retorna el valor de acc complementacion predio DAO.
	 *
	 * @param adm_manager correspondiente al valor del tipo de objeto DAOManager
	 * @return el valor de acc complementacion predio DAO
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public static AccComplementacionPredioDAO getAccComplementacionPredioDAO(DAOManager adm_manager)
	    throws B2BException
	{
		return (AccComplementacionPredioDAO)adm_manager.getDAO(AccComplementacionPredioDAO.class);
	}

	/**
	 * Retorna el valor de acc completitud documental DAO.
	 *
	 * @param adm_manager correspondiente al valor del tipo de objeto DAOManager
	 * @return el valor de acc completitud documental DAO
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public static AccCompletitudDocumentalDAO getAccCompletitudDocumentalDAO(DAOManager adm_manager)
	    throws B2BException
	{
		return (AccCompletitudDocumentalDAO)adm_manager.getDAO(AccCompletitudDocumentalDAO.class);
	}

	/**
	 * Retorna el valor de acc consulta criterios ant sistema DAO.
	 *
	 * @param adm_manager correspondiente al valor del tipo de objeto DAOManager
	 * @return el valor de acc consulta criterios ant sistema DAO
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public static AccConsultaCriteriosAntSistemaDAO getAccConsultaCriteriosAntSistemaDAO(DAOManager adm_manager)
	    throws B2BException
	{
		return (AccConsultaCriteriosAntSistemaDAO)adm_manager.getDAO(AccConsultaCriteriosAntSistemaDAO.class);
	}

	/**
	 * Retorna el valor de acc convenio circulo registral DAO.
	 *
	 * @param adm_manager correspondiente al valor del tipo de objeto DAOManager
	 * @return el valor de acc convenio circulo registral DAO
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public static AccConvenioCirculoRegistralDAO getAccConvenioCirculoRegistralDAO(DAOManager adm_manager)
	    throws B2BException
	{
		return (AccConvenioCirculoRegistralDAO)adm_manager.getDAO(AccConvenioCirculoRegistralDAO.class);
	}

	/**
	 * Retorna el valor de acc convenio zona registral DAO.
	 *
	 * @param adm_manager correspondiente al valor del tipo de objeto DAOManager
	 * @return el valor de acc convenio zona registral DAO
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public static AccConvenioZonaRegistralDAO getAccConvenioZonaRegistralDAO(DAOManager adm_manager)
	    throws B2BException
	{
		return (AccConvenioZonaRegistralDAO)adm_manager.getDAO(AccConvenioZonaRegistralDAO.class);
	}

	/**
	 * Retorna el valor de acc detalle area predio DAO.
	 *
	 * @param adm_manager correspondiente al valor del tipo de objeto DAOManager
	 * @return el valor de acc detalle area predio DAO
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public static AccDetalleAreaPredioDAO getAccDetalleAreaPredioDAO(DAOManager adm_manager)
	    throws B2BException
	{
		return (AccDetalleAreaPredioDAO)adm_manager.getDAO(AccDetalleAreaPredioDAO.class);
	}

	/**
	 * Retorna el valor de acc entidad externa convenio DAO.
	 *
	 * @param adm_manager correspondiente al valor del tipo de objeto DAOManager
	 * @return el valor de acc entidad externa convenio DAO
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public static AccEntidadExternaConvenioDAO getAccEntidadExternaConvenioDAO(DAOManager adm_manager)
	    throws B2BException
	{
		return (AccEntidadExternaConvenioDAO)adm_manager.getDAO(AccEntidadExternaConvenioDAO.class);
	}

	/**
	 * Retorna el valor de acc entidad externa DAO.
	 *
	 * @param adm_manager correspondiente al valor del tipo de objeto DAOManager
	 * @return el valor de acc entidad externa DAO
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public static AccEntidadExternaDAO getAccEntidadExternaDAO(DAOManager adm_manager)
	    throws B2BException
	{
		return (AccEntidadExternaDAO)adm_manager.getDAO(AccEntidadExternaDAO.class);
	}

	/**
	 * Retorna el valor de acc entidad externa persona DAO.
	 *
	 * @param adm_manager correspondiente al valor del tipo de objeto DAOManager
	 * @return el valor de acc entidad externa persona DAO
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public static AccEntidadExternaPersonaDAO getAccEntidadExternaPersonaDAO(DAOManager adm_manager)
	    throws B2BException
	{
		return (AccEntidadExternaPersonaDAO)adm_manager.getDAO(AccEntidadExternaPersonaDAO.class);
	}

	/**
	 * Retorna el valor de acc lindero predio DAO.
	 *
	 * @param adm_manager correspondiente al valor del tipo de objeto DAOManager
	 * @return el valor de acc lindero predio DAO
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public static AccLinderoPredioDAO getAccLinderoPredioDAO(DAOManager adm_manager)
	    throws B2BException
	{
		return (AccLinderoPredioDAO)adm_manager.getDAO(AccLinderoPredioDAO.class);
	}

	/**
	 * Retorna el valor de acc liquidacion DAO.
	 *
	 * @param adm_manager correspondiente al valor del tipo de objeto DAOManager
	 * @return el valor de acc liquidacion DAO
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public static com.bachue.snr.prosnr01.dao.acc.LiquidacionDAO getAccLiquidacionDAO(DAOManager adm_manager)
	    throws B2BException
	{
		return (com.bachue.snr.prosnr01.dao.acc.LiquidacionDAO)adm_manager.getDAO(
		    com.bachue.snr.prosnr01.dao.acc.LiquidacionDAO.class
		);
	}

	/**
	 * Retorna el valor de acc liquidacion item DAO.
	 *
	 * @param adm_manager correspondiente al valor del tipo de objeto DAOManager
	 * @return el valor de acc liquidacion item DAO
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public static com.bachue.snr.prosnr01.dao.acc.LiquidacionItemDAO getAccLiquidacionItemDAO(DAOManager adm_manager)
	    throws B2BException
	{
		return (com.bachue.snr.prosnr01.dao.acc.LiquidacionItemDAO)adm_manager.getDAO(
		    com.bachue.snr.prosnr01.dao.acc.LiquidacionItemDAO.class
		);
	}

	/**
	 * Retorna el valor de acc predio registro DAO.
	 *
	 * @param adm_manager correspondiente al valor del tipo de objeto DAOManager
	 * @return el valor de acc predio registro DAO
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public static AccPredioRegistroDAO getAccPredioRegistroDAO(DAOManager adm_manager)
	    throws B2BException
	{
		return (AccPredioRegistroDAO)adm_manager.getDAO(AccPredioRegistroDAO.class);
	}

	/**
	 * Retorna el valor de acc predio segregado DAO.
	 *
	 * @param adm_manager correspondiente al valor del tipo de objeto DAOManager
	 * @return el valor de acc predio segregado DAO
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public static AccPredioSegregadoDAO getAccPredioSegregadoDAO(DAOManager adm_manager)
	    throws B2BException
	{
		return (AccPredioSegregadoDAO)adm_manager.getDAO(AccPredioSegregadoDAO.class);
	}

	/**
	 * Retorna el valor de acc salvedad anotacion DAO.
	 *
	 * @param adm_manager correspondiente al valor del tipo de objeto DAOManager
	 * @return el valor de acc salvedad anotacion DAO
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public static AccSalvedadAnotacionDAO getAccSalvedadAnotacionDAO(DAOManager adm_manager)
	    throws B2BException
	{
		return (AccSalvedadAnotacionDAO)adm_manager.getDAO(AccSalvedadAnotacionDAO.class);
	}

	/**
	 * Retorna el valor de acc salvedad predio DAO.
	 *
	 * @param adm_manager correspondiente al valor del tipo de objeto DAOManager
	 * @return el valor de acc salvedad predio DAO
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public static AccSalvedadPredioDAO getAccSalvedadPredioDAO(DAOManager adm_manager)
	    throws B2BException
	{
		return (AccSalvedadPredioDAO)adm_manager.getDAO(AccSalvedadPredioDAO.class);
	}

	/**
	 * Retorna el valor de actividad economica DAO.
	 *
	 * @param adm_manager correspondiente al valor del tipo de objeto DAOManager
	 * @return el valor de actividad economica DAO
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public static ActividadEconomicaDAO getActividadEconomicaDAO(DAOManager adm_manager)
	    throws B2BException
	{
		return (ActividadEconomicaDAO)adm_manager.getDAO(ActividadEconomicaDAO.class);
	}

	/**
	 * Retorna el valor de acto DAO.
	 *
	 * @param adm_manager correspondiente al valor del tipo de objeto DAOManager
	 * @return el valor de acto DAO
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public static ActoDAO getActoDAO(DAOManager adm_manager)
	    throws B2BException
	{
		return (ActoDAO)adm_manager.getDAO(ActoDAO.class);
	}

	/**
	 * Retorna el valor de acc acto devolucion dinero DAO.
	 *
	 * @param adm_manager correspondiente al valor del tipo de objeto DAOManager
	 * @return el valor de acc acto devolucion dinero DAO
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public static ActoDevolucionDineroDAO getActoDevolucionDineroDAO(DAOManager adm_manager)
	    throws B2BException
	{
		return (ActoDevolucionDineroDAO)adm_manager.getDAO(ActoDevolucionDineroDAO.class);
	}

	/**
	 * Retorna el valor de adm liquidacion DAO.
	 *
	 * @param adm_manager correspondiente al valor del tipo de objeto DAOManager
	 * @return el valor de adm liquidacion DAO
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public static AdmLiquidacionDAO getAdmLiquidacionDAO(DAOManager adm_manager)
	    throws B2BException
	{
		return (AdmLiquidacionDAO)adm_manager.getDAO(AdmLiquidacionDAO.class);
	}

	/**
	 * Retorna el valor de administracion componentes DAO.
	 *
	 * @param adm_manager correspondiente al valor del tipo de objeto DAOManager
	 * @return el valor de adm liquidacion DAO
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public static ComponentesDAO getAdministracionComponentesDAO(DAOManager adm_manager)
	    throws B2BException
	{
		return (ComponentesDAO)adm_manager.getDAO(ComponentesDAO.class);
	}

	/**
	 * Retorna Objeto o variable de valor afectacion prestacion servicio DAO.
	 *
	 * @param adm_manager de adm manager
	 * @return el valor de afectacion prestacion servicio DAO
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public static AfectacionPrestacionServicioDAO getAfectacionPrestacionServicioDAO(DAOManager adm_manager)
	    throws B2BException
	{
		return (AfectacionPrestacionServicioDAO)adm_manager.getDAO(AfectacionPrestacionServicioDAO.class);
	}

	/**
	 * Retorna Objeto o variable de valor alerta generada TDAO.
	 *
	 * @param adm_manager de adm manager
	 * @return el valor de alerta generada TDAO
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public static AlertaGeneradaDAO getAlertaGeneradaDAO(DAOManager adm_manager)
	    throws B2BException
	{
		return (AlertaGeneradaDAO)adm_manager.getDAO(AlertaGeneradaDAO.class);
	}

	/**
	 * Retorna Objeto o variable de valor alerta MSGDAO.
	 *
	 * @param adm_manager de adm manager
	 * @return el valor de alerta MSGDAO
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public static AlertaMSGDAO getAlertaMSGDAO(DAOManager adm_manager)
	    throws B2BException
	{
		return (AlertaMSGDAO)adm_manager.getDAO(AlertaMSGDAO.class);
	}

	/**
	 * Retorna el valor de alerta naturaleza juridica DAO.
	 *
	 * @param adm_manager correspondiente al valor del tipo de objeto DAOManager
	 * @return el valor de alerta naturaleza juridica DAO
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public static AlertaNaturalezaJuridicaDAO getAlertaNaturalezaJuridicaDAO(DAOManager adm_manager)
	    throws B2BException
	{
		return (AlertaNaturalezaJuridicaDAO)adm_manager.getDAO(AlertaNaturalezaJuridicaDAO.class);
	}

	/**
	 * Retorna Objeto o variable de valor alerta T comunidad DAO.
	 *
	 * @param adm_manager de adm manager
	 * @return el valor de alerta T comunidad DAO
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public static AlertaTComunidadDAO getAlertaTComunidadDAO(DAOManager adm_manager)
	    throws B2BException
	{
		return (AlertaTComunidadDAO)adm_manager.getDAO(AlertaTComunidadDAO.class);
	}

	/**
	 * Retorna Objeto o variable de valor alerta T inactivacion DAO.
	 *
	 * @param adm_manager de adm manager
	 * @return el valor de alerta T inactivacion DAO
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public static AlertaTInactivacionDAO getAlertaTInactivacionDAO(DAOManager adm_manager)
	    throws B2BException
	{
		return (AlertaTInactivacionDAO)adm_manager.getDAO(AlertaTInactivacionDAO.class);
	}

	/**
	 * Retorna Objeto o variable de valor alerta T predio DAO.
	 *
	 * @param adm_manager de adm manager
	 * @return el valor de alerta T predio DAO
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public static AlertaTPredioDAO getAlertaTPredioDAO(DAOManager adm_manager)
	    throws B2BException
	{
		return (AlertaTPredioDAO)adm_manager.getDAO(AlertaTPredioDAO.class);
	}

	/**
	 * Retorna Objeto o variable de valor alerta tierras DAO.
	 *
	 * @param adm_manager de adm manager
	 * @return el valor de alerta tierras DAO
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public static AlertaTierrasDAO getAlertaTierrasDAO(DAOManager adm_manager)
	    throws B2BException
	{
		return (AlertaTierrasDAO)adm_manager.getDAO(AlertaTierrasDAO.class);
	}

	/**
	 * Retorna el valor de alerta titular DAO.
	 *
	 * @param adm_manager correspondiente al valor del tipo de objeto DAOManager
	 * @return el valor de alerta titular DAO
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public static AlertaTitularDAO getAlertaTitularDAO(DAOManager adm_manager)
	    throws B2BException
	{
		return (AlertaTitularDAO)adm_manager.getDAO(AlertaTitularDAO.class);
	}

	/**
	 * Retorna el valor de alerta tramite DAO.
	 *
	 * @param adm_manager correspondiente al valor del tipo de objeto DAOManager
	 * @return el valor de alerta tramite DAO
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public static AlertaTramiteDAO getAlertaTramiteDAO(DAOManager adm_manager)
	    throws B2BException
	{
		return (AlertaTramiteDAO)adm_manager.getDAO(AlertaTramiteDAO.class);
	}

	/**
	 * Retorna el valor de alerta turno tramite DAO.
	 *
	 * @param adm_manager correspondiente al valor del tipo de objeto DAOManager
	 * @return el valor de alerta turno tramite DAO
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public static AlertaTurnoTramiteDAO getAlertaTurnoTramiteDAO(DAOManager adm_manager)
	    throws B2BException
	{
		return (AlertaTurnoTramiteDAO)adm_manager.getDAO(AlertaTurnoTramiteDAO.class);
	}

	/**
	 * Retorna el valor de anotacion cancelacion DAO.
	 *
	 * @param adm_manager correspondiente al valor del tipo de objeto DAOManager
	 * @return el valor de anotacion cancelacion DAO
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public static AnotacionCancelacionDAO getAnotacionCancelacionDAO(DAOManager adm_manager)
	    throws B2BException
	{
		return (AnotacionCancelacionDAO)adm_manager.getDAO(AnotacionCancelacionDAO.class);
	}

	/**
	 * Retorna el valor de anotacion predio ciudadano DAO.
	 *
	 * @param adm_manager correspondiente al valor del tipo de objeto DAOManager
	 * @return el valor de anotacion predio ciudadano DAO
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public static AnotacionPredioCiudadanoDAO getAnotacionPredioCiudadanoDAO(DAOManager adm_manager)
	    throws B2BException
	{
		return (AnotacionPredioCiudadanoDAO)adm_manager.getDAO(AnotacionPredioCiudadanoDAO.class);
	}

	/**
	 * Retorna el valor de anotacion predio DAO.
	 *
	 * @param adm_manager correspondiente al valor del tipo de objeto DAOManager
	 * @return el valor de anotacion predio DAO
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public static AnotacionPredioDAO getAnotacionPredioDAO(DAOManager adm_manager)
	    throws B2BException
	{
		return (AnotacionPredioDAO)adm_manager.getDAO(AnotacionPredioDAO.class);
	}

	/**
	 * Retorna el valor de ant sistema acto DAO.
	 *
	 * @param adm_manager correspondiente al valor del tipo de objeto DAOManager
	 * @return el valor de ant sistema acto DAO
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public static AntSistemaActoDAO getAntSistemaActoDAO(DAOManager adm_manager)
	    throws B2BException
	{
		return (AntSistemaActoDAO)adm_manager.getDAO(AntSistemaActoDAO.class);
	}

	/**
	 * Retorna el valor de antiguo sistema DAO.
	 *
	 * @param adm_manager correspondiente al valor del tipo de objeto DAOManager
	 * @return el valor de antiguo sistema DAO
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public static AntiguoSistemaDAO getAntiguoSistemaDAO(DAOManager adm_manager)
	    throws B2BException
	{
		return (AntiguoSistemaDAO)adm_manager.getDAO(AntiguoSistemaDAO.class);
	}

	/**
	 * Retorna la clase encargada de las interacciones con base de datos para la tabla SDB_CON_ARCHIVO del módulo de conciliaciones.
	 *
	 * @param adm_manager Contexto transaccional al que se asociara el DAO retornado
	 * @return clase encargada de las interacciones con base de datos para la tabla SDB_CON_ARCHIVO del módulo de conciliaciones
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public static ArchivoConciliacionDAO getArchivoConciliacionDAO(DAOManager adm_manager)
	    throws B2BException
	{
		return (ArchivoConciliacionDAO)adm_manager.getDAO(ArchivoConciliacionDAO.class);
	}

	/**
	 * Retorna el valor de archivo DAO.
	 *
	 * @param adm_manager correspondiente al valor del tipo de objeto DAOManager
	 * @return el valor de tipo acto prohibición DAO
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public static ArchivoDAO getArchivoDAO(DAOManager adm_manager)
	    throws B2BException
	{
		return (ArchivoDAO)adm_manager.getDAO(ArchivoDAO.class);
	}

	/**
	 * Retorna el valor de area predio DAO.
	 *
	 * @param adm_manager correspondiente al valor del tipo de objeto DAOManager
	 * @return el valor de area predio DAO
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public static AreaPredioDAO getAreaPredioDAO(DAOManager adm_manager)
	    throws B2BException
	{
		return (AreaPredioDAO)adm_manager.getDAO(AreaPredioDAO.class);
	}

	/**
	 * Retorna el valor de bitacora bloqueo DAO.
	 *
	 * @param adm_manager correspondiente al valor del tipo de objeto DAOManager
	 * @return el valor de bitacora bloqueo DAO
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public static BitacoraBloqueoDAO getBitacoraBloqueoDAO(DAOManager adm_manager)
	    throws B2BException
	{
		return (BitacoraBloqueoDAO)adm_manager.getDAO(BitacoraBloqueoDAO.class);
	}

	/**
	 * Retorna el valor de bitacora proceso DAO.
	 *
	 * @param adm_manager correspondiente al valor del tipo de objeto DAOManager
	 * @return el valor de bitacora proceso DAO
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public static BitacoraProcesoDAO getBitacoraProcesoDAO(DAOManager adm_manager)
	    throws B2BException
	{
		return (BitacoraProcesoDAO)adm_manager.getDAO(BitacoraProcesoDAO.class);
	}

	/**
	 * Retorna el valor de bng anotacion cancelacion DAO.
	 *
	 * @param adm_manager correspondiente al valor del tipo de objeto DAOManager
	 * @return el valor de bng anotacion cancelacion DAO
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public static com.bachue.snr.prosnr01.dao.bng.AnotacionCancelacionDAO getBngAnotacionCancelacionDAO(
	    DAOManager adm_manager
	)
	    throws B2BException
	{
		return (com.bachue.snr.prosnr01.dao.bng.AnotacionCancelacionDAO)adm_manager.getDAO(
		    com.bachue.snr.prosnr01.dao.bng.AnotacionCancelacionDAO.class
		);
	}

	/**
	 * Retorna la clase encargada de las interacciones con base de datos para la tabla SDB_VW_CRPS_ENC_CONCILIAR del módulo de conciliaciones.
	 *
	 * @param adm_manager Contexto transaccional al que se asociara el DAO retornado
	 * @return clase encargada de las interacciones con base de datos para la tabla SDB_VW_CRPS_ENC_CONCILIAR del módulo de conciliaciones
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public static CRPSCabeceraDAO getCRPSCabeceraDAO(DAOManager adm_manager)
	    throws B2BException
	{
		return (CRPSCabeceraDAO)adm_manager.getDAO(CRPSCabeceraDAO.class);
	}

	/**
	 * Retorna la clase encargada de las interacciones con base de datos para la tabla SDB_VW_CRPS_DET_CONCILIAR del módulo de conciliaciones.
	 *
	 * @param adm_manager Contexto transaccional al que se asociara el DAO retornado
	 * @return clase encargada de las interacciones con base de datos para la tabla SDB_VW_CRPS_DET_CONCILIAR del módulo de conciliaciones
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public static CRPSDetalleDAO getCRPSDetalleDAO(DAOManager adm_manager)
	    throws B2BException
	{
		return (CRPSDetalleDAO)adm_manager.getDAO(CRPSDetalleDAO.class);
	}

	/**
	 * Retorna el valor de calidad solicitante DAO.
	 *
	 * @param adm_manager correspondiente al valor del tipo de objeto DAOManager
	 * @return el valor de calidad solicitante DAO
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public static CalidadSolicitanteDAO getCalidadSolicitanteDAO(DAOManager adm_manager)
	    throws B2BException
	{
		return (CalidadSolicitanteDAO)adm_manager.getDAO(CalidadSolicitanteDAO.class);
	}

	/**
	 * Retorna el valor de cambio estado predio DAO.
	 *
	 * @param adm_manager correspondiente al valor del tipo de objeto DAOManager
	 * @return el valor de cambio estado predio DAO
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public static CambioEstadoPredioDAO getCambioEstadoPredioDAO(DAOManager adm_manager)
	    throws B2BException
	{
		return (CambioEstadoPredioDAO)adm_manager.getDAO(CambioEstadoPredioDAO.class);
	}

	/**
	 * Retorna el valor de campo criterio busqueda DAO.
	 *
	 * @param adm_manager correspondiente al valor del tipo de objeto DAOManager
	 * @return el valor de campo criterio busqueda DAO
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public static CampoCriterioBusquedaDAO getCampoCriterioBusquedaDAO(DAOManager adm_manager)
	    throws B2BException
	{
		return (CampoCriterioBusquedaDAO)adm_manager.getDAO(CampoCriterioBusquedaDAO.class);
	}

	/**
	 * Retorna el valor de campos certificado DAO.
	 *
	 * @param adm_manager correspondiente al valor del tipo de objeto DAOManager
	 * @return el valor de campos certificado DAO
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public static CamposCertificadoDAO getCamposCertificadoDAO(DAOManager adm_manager)
	    throws B2BException
	{
		return (CamposCertificadoDAO)adm_manager.getDAO(CamposCertificadoDAO.class);
	}

	/**
	 * Retorna el valor de campos consulta DAO.
	 *
	 * @param adm_manager correspondiente al valor del tipo de objeto DAOManager
	 * @return el valor de campos consulta DAO
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public static CamposConsultaDAO getCamposConsultaDAO(DAOManager adm_manager)
	    throws B2BException
	{
		return (CamposConsultaDAO)adm_manager.getDAO(CamposConsultaDAO.class);
	}

	/**
	 * Retorna el valor de campos correccion DAO.
	 *
	 * @param adm_manager correspondiente al valor del tipo de objeto DAOManager
	 * @return el valor de campos correccion DAO
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public static CamposCorreccionDAO getCamposCorreccionDAO(DAOManager adm_manager)
	    throws B2BException
	{
		return (CamposCorreccionDAO)adm_manager.getDAO(CamposCorreccionDAO.class);
	}

	/**
	 * Retorna el valor de canal DAO.
	 *
	 * @param adm_manager correspondiente al valor del tipo de objeto DAOManager
	 * @return el valor de canal DAO
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public static CanalDAO getCanalDAO(DAOManager adm_manager)
	    throws B2BException
	{
		return (CanalDAO)adm_manager.getDAO(CanalDAO.class);
	}

	/**
	 * Retorna el valor de canal origen servicio DAO.
	 *
	 * @param adm_manager correspondiente al valor del tipo de objeto DAOManager
	 * @return el valor de canal origen servicio DAO
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public static CanalOrigenServicioDAO getCanalOrigenServicioDAO(DAOManager adm_manager)
	    throws B2BException
	{
		return (CanalOrigenServicioDAO)adm_manager.getDAO(CanalOrigenServicioDAO.class);
	}

	/**
	 * Retorna el valor de catalogo consulta DAO.
	 *
	 * @param adm_manager correspondiente al valor del tipo de objeto DAOManager
	 * @return el valor de catalogo consulta DAO
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public static CatalogoConsultaDAO getCatalogoConsultaDAO(DAOManager adm_manager)
	    throws B2BException
	{
		return (CatalogoConsultaDAO)adm_manager.getDAO(CatalogoConsultaDAO.class);
	}

	/**
	 * Retorna el valor de pgn catastro DAO.
	 *
	 * @param adm_manager correspondiente al valor del tipo de objeto DAOManager
	 * @return el valor de pgn catastro DAO
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public static CatastroDAO getCatastroDAO(DAOManager adm_manager)
	    throws B2BException
	{
		return (CatastroDAO)adm_manager.getDAO(CatastroDAO.class);
	}

	/**
	 * Retorna el valor de causal aprobacion devolucion DAO.
	 *
	 * @param adm_manager correspondiente al valor del tipo de objeto DAOManager
	 * @return el valor de causal aprobacion devolucion DAO
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public static CausalAprobacionDevolucionDAO getCausalAprobacionDevolucionDAO(DAOManager adm_manager)
	    throws B2BException
	{
		return (CausalAprobacionDevolucionDAO)adm_manager.getDAO(CausalAprobacionDevolucionDAO.class);
	}

	/**
	 * Retorna el valor de causal correccion DAO.
	 *
	 * @param adm_manager correspondiente al valor del tipo de objeto DAOManager
	 * @return el valor de causal correccion DAO
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public static CausalCorreccionDAO getCausalCorreccionDAO(DAOManager adm_manager)
	    throws B2BException
	{
		return (CausalCorreccionDAO)adm_manager.getDAO(CausalCorreccionDAO.class);
	}

	/**
	 * Retorna el valor de causal devolucion DAO.
	 *
	 * @param adm_manager correspondiente al valor del tipo de objeto DAOManager
	 * @return el valor de causal devolucion DAO
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public static CausalDevolucionDAO getCausalDevolucionDAO(DAOManager adm_manager)
	    throws B2BException
	{
		return (CausalDevolucionDAO)adm_manager.getDAO(CausalDevolucionDAO.class);
	}

	/**
	 * Retorna el valor de causal mayor valor DAO.
	 *
	 * @param adm_manager correspondiente al valor del tipo de objeto DAOManager
	 * @return el valor de causal mayor valor DAO
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public static CausalMayorValorDAO getCausalMayorValorDAO(DAOManager adm_manager)
	    throws B2BException
	{
		return (CausalMayorValorDAO)adm_manager.getDAO(CausalMayorValorDAO.class);
	}

	/**
	 * Retorna el valor de tipo causal negacion copias DAO.
	 *
	 * @param adm_manager correspondiente al valor del tipo de objeto DAOManager
	 * @return el valor de tipo documento publico DAO
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public static CausalNegacionCopiasDAO getCausalNegacionCopiasDAO(DAOManager adm_manager)
	    throws B2BException
	{
		return (CausalNegacionCopiasDAO)adm_manager.getDAO(CausalNegacionCopiasDAO.class);
	}

	/**
	 * Retorna el valor de CausalRechazoRecursoDAO.
	 *
	 * @param adm_manager correspondiente al valor del tipo de objeto DAOManager
	 * @return el valor de CausalRechazoRecursoDAO
	 * @throws B2BException Señala que se ha generado una excepción
	 */
	public static CausalRechazoRecursoDAO getCausalRechazoRecursoDAO(DAOManager adm_manager)
	    throws B2BException
	{
		return (CausalRechazoRecursoDAO)adm_manager.getDAO(CausalRechazoRecursoDAO.class);
	}

	/**
	 * Retorna el valor de causal reimpresion DAO.
	 *
	 * @param adm_manager de adm manager
	 * @return el valor de causal reimpresion DAO
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public static CausalReimpresionDAO getCausalReimpresionDAO(DAOManager adm_manager)
	    throws B2BException
	{
		return (CausalReimpresionDAO)adm_manager.getDAO(CausalReimpresionDAO.class);
	}

	/**
	 * Retorna el valor de Circulo Act Admin DAO.
	 *
	 * @param adm_manager correspondiente al valor del tipo de objeto DAOManager
	 * @return el valor de Circulo Act Admin DAO
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public static CirculoActAdminDAO getCirculoActAdminDAO(DAOManager adm_manager)
	    throws B2BException
	{
		return (CirculoActAdminDAO)adm_manager.getDAO(CirculoActAdminDAO.class);
	}

	/**
	 * Retorna el valor de pgn circulo catastro DAO.
	 *
	 * @param adm_manager correspondiente al valor del tipo de objeto DAOManager
	 * @return el valor de pgn circulo catastro DAO
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public static CirculoCatastroDAO getCirculoCatastroDAO(DAOManager adm_manager)
	    throws B2BException
	{
		return (CirculoCatastroDAO)adm_manager.getDAO(CirculoCatastroDAO.class);
	}

	/**
	 * Retorna el valor de circulo festivo DAO.
	 *
	 * @param ldm_manager correspondiente al valor del tipo de objeto DAOManager
	 * @return el valor de circulo festivo DAO
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public static CirculoFestivoDAO getCirculoFestivoDAO(DAOManager ldm_manager)
	    throws B2BException
	{
		return (CirculoFestivoDAO)ldm_manager.getDAO(CirculoFestivoDAO.class);
	}

	/**
	 * Retorna el valor de circulo origen destino DAO.
	 *
	 * @param adm_manager correspondiente al valor del tipo de objeto DAOManager
	 * @return el valor de circulo origen destino DAO
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public static CirculoOrigenDestinoDAO getCirculoOrigenDestinoDAO(DAOManager adm_manager)
	    throws B2BException
	{
		return (CirculoOrigenDestinoDAO)adm_manager.getDAO(CirculoOrigenDestinoDAO.class);
	}

	/**
	 * Retorna el valor de circulo registral DAO.
	 *
	 * @param adm_manager correspondiente al valor del tipo de objeto DAOManager
	 * @return el valor de circulo registral DAO
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public static CirculoRegistralDao getCirculoRegistralDAO(DAOManager adm_manager)
	    throws B2BException
	{
		return (CirculoRegistralDao)adm_manager.getDAO(CirculoRegistralDao.class);
	}

	/**
	 * Retorna el valor de complementacion predio DAO.
	 *
	 * @param adm_manager correspondiente al valor del tipo de objeto DAOManager
	 * @return el valor de complementacion predio DAO
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public static ComplementacionPredioDAO getComplementacionPredioDAO(DAOManager adm_manager)
	    throws B2BException
	{
		return (ComplementacionPredioDAO)adm_manager.getDAO(ComplementacionPredioDAO.class);
	}

	/**
	 * Retorna el valor de completitud complementacion DAO.
	 *
	 * @param adm_manager correspondiente al valor del tipo de objeto DAOManager
	 * @return el valor de completitud complementacion DAO
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public static CompletitudComplementacionDAO getCompletitudComplementacionDAO(DAOManager adm_manager)
	    throws B2BException
	{
		return (CompletitudComplementacionDAO)adm_manager.getDAO(CompletitudComplementacionDAO.class);
	}

	/**
	 * Retorna el valor de componentes DAO.
	 *
	 * @param adm_manager correspondiente al valor del tipo de objeto DAOManager
	 * @return el valor de componentes DAO
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public static ComponentesDAO getComponentesDAO(DAOManager adm_manager)
	    throws B2BException
	{
		return (ComponentesDAO)adm_manager.getDAO(ComponentesDAO.class);
	}

	/**
	 * Retorna el valor de comunidades etnicas DAO.
	 *
	 * @param adm_manager correspondiente al valor del tipo de objeto DAOManager
	 * @return el valor de comunidades etnicas DAO
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public static ComunidadesEtnicasDAO getComunidadesEtnicasDAO(DAOManager adm_manager)
	    throws B2BException
	{
		return (ComunidadesEtnicasDAO)adm_manager.getDAO(ComunidadesEtnicasDAO.class);
	}

	/**
	 * Retorna el valor de con archivo DAO.
	 *
	 * @param adm_manager correspondiente al valor del tipo de objeto DAOManager
	 * @return el valor de con archivo  DAO
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public static ConArchivoDAO getConArchivoDAO(DAOManager adm_manager)
	    throws B2BException
	{
		return (ConArchivoDAO)adm_manager.getDAO(ConArchivoDAO.class);
	}

	/**
	 * Retorna el valor de zona registral DAO.
	 *
	 * @param adm_manager correspondiente al valor del tipo de objeto DAOManager
	 * @return el valor de zona registral DAO
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public static ConDocumentosDAO getConDocumentosDAO(DAOManager adm_manager)
	    throws B2BException
	{
		return (ConDocumentosDAO)adm_manager.getDAO(ConDocumentosDAO.class);
	}

	/**
	 * Retorna el valor de con imagenes DAO.
	 *
	 * @param adm_manager correspondiente al valor del tipo de objeto DAOManager
	 * @return el valor de con imagenes DAO
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public static ConImagenesDAO getConImagenesDAO(DAOManager adm_manager)
	    throws B2BException
	{
		return (ConImagenesDAO)adm_manager.getDAO(ConImagenesDAO.class);
	}

	/**
	 * Gets the con inconsistencia DAO.
	 *
	 * @param adm_manager the adm manager
	 * @return the con inconsistencia DAO
	 * @throws B2BException the b 2 B exception
	 */
	public static ConInconsistenciaDAO getConInconsistenciaDAO(DAOManager adm_manager)
	    throws B2BException
	{
		return (ConInconsistenciaDAO)adm_manager.getDAO(ConInconsistenciaDAO.class);
	}

	/**
	 * Gets the con partida ADAO.
	 *
	 * @param adm_manager the adm manager
	 * @return the con partida ADAO
	 * @throws B2BException the b 2 B exception
	 */
	public static ConPartidaADAO getConPartidaADAO(DAOManager adm_manager)
	    throws B2BException
	{
		return (ConPartidaADAO)adm_manager.getDAO(ConPartidaADAO.class);
	}

	/**
	 * Gets the con plantilla mensaje DAO.
	 *
	 * @param adm_manager the adm manager
	 * @return the con plantilla mensaje DAO
	 * @throws B2BException the b 2 B exception
	 */
	public static ConPlantillaMensajeDAO getConPlantillaMensajeDAO(DAOManager adm_manager)
	    throws B2BException
	{
		return (ConPlantillaMensajeDAO)adm_manager.getDAO(ConPlantillaMensajeDAO.class);
	}

	/**
	 * Gets the con resumen DAO.
	 *
	 * @param adm_manager the adm manager
	 * @return the con resumen DAO
	 * @throws B2BException the b 2 B exception
	 */
	public static ConResumenDAO getConResumenDAO(DAOManager adm_manager)
	    throws B2BException
	{
		return (ConResumenDAO)adm_manager.getDAO(ConResumenDAO.class);
	}

	/**
	 * Retorna Objeto o variable de valor con siif detalle DAO.
	 *
	 * @param adm_manager de adm manager
	 * @return el valor de con siif detalle DAO
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public static ConSiifDetalleDAO getConSiifDetalleDAO(DAOManager adm_manager)
	    throws B2BException
	{
		return (ConSiifDetalleDAO)adm_manager.getDAO(ConSiifDetalleDAO.class);
	}

	/**
	 * Retorna Objeto o variable de valor con siif maestro DAO.
	 *
	 * @param adm_manager de adm manager
	 * @return el valor de con siif maestro DAO
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public static ConSiifMaestroDAO getConSiifMaestroDAO(DAOManager adm_manager)
	    throws B2BException
	{
		return (ConSiifMaestroDAO)adm_manager.getDAO(ConSiifMaestroDAO.class);
	}

	/**
	 * Retorna Objeto o variable de valor con tipo documental DAO.
	 *
	 * @param adm_manager de adm manager
	 * @return el valor de con tipo documental DAO
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public static com.bachue.snr.prosnr21.dao.png.TipoDocumentalDAO getConTipoDocumentalDAO(DAOManager adm_manager)
	    throws B2BException
	{
		return (com.bachue.snr.prosnr21.dao.png.TipoDocumentalDAO)adm_manager.getDAO(
		    com.bachue.snr.prosnr21.dao.png.TipoDocumentalDAO.class
		);
	}

	/**
	 * Retorna el valor de condicion tarifa DAO.
	 *
	 * @param adm_manager correspondiente al valor del tipo de objeto DAOManager
	 * @return el valor de condicion tarifa DAO
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public static CondicionTarifaDAO getCondicionTarifaDAO(DAOManager adm_manager)
	    throws B2BException
	{
		return (CondicionTarifaDAO)adm_manager.getDAO(CondicionTarifaDAO.class);
	}

	/**
	 * Retorna el valor de constantes DAO.
	 *
	 * @param adm_manager correspondiente al valor del tipo de objeto DAOManager
	 * @return el valor de constantes DAO
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public static ConstantesDAO getConstantesDAO(DAOManager adm_manager)
	    throws B2BException
	{
		return (ConstantesDAO)adm_manager.getDAO(ConstantesDAO.class);
	}

	/**
	 * Retorna el valor de consulta criterio ant sistema DAO.
	 *
	 * @param adm_manager correspondiente al valor del tipo de objeto DAOManager
	 * @return el valor de consulta criterio ant sistema DAO
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public static ConsultaCriterioAntSistemaDAO getConsultaCriterioAntSistemaDAO(DAOManager adm_manager)
	    throws B2BException
	{
		return (ConsultaCriterioAntSistemaDAO)adm_manager.getDAO(ConsultaCriterioAntSistemaDAO.class);
	}

	/**
	 * Retorna el valor de consulta documento DAO.
	 *
	 * @param adm_manager correspondiente al valor del tipo de objeto DAOManager
	 * @return el valor de consulta documento DAO
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public static ConsultaDocumentoDAO getConsultaDocumentoDAO(DAOManager adm_manager)
	    throws B2BException
	{
		return (ConsultaDocumentoDAO)adm_manager.getDAO(ConsultaDocumentoDAO.class);
	}

	/**
	 * Retorna el valor de consulta estado predio DAO.
	 *
	 * @param adm_manager correspondiente al valor del tipo de objeto DAOManager
	 * @return el valor de consulta estado predio DAO
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public static ConsultaEstadoPredioDAO getConsultaEstadoPredioDAO(DAOManager adm_manager)
	    throws B2BException
	{
		return (ConsultaEstadoPredioDAO)adm_manager.getDAO(ConsultaEstadoPredioDAO.class);
	}

	/**
	 * Retorna el valor de consulta por criterios DAO.
	 *
	 * @param adm_manager correspondiente al valor del tipo de objeto DAOManager
	 * @return el valor de consulta por criterios DAO
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public static ConsultaPorCriteriosDAO getConsultaPorCriteriosDAO(DAOManager adm_manager)
	    throws B2BException
	{
		return (ConsultaPorCriteriosDAO)adm_manager.getDAO(ConsultaPorCriteriosDAO.class);
	}

	/**
	 * Retorna el valor de consulta reparto calificacion DAO.
	 *
	 * @param adm_manager correspondiente al valor del tipo de objeto DAOManager
	 * @return el valor de consulta reparto calificacion DAO
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public static ConsultaRepartoCalificacionDAO getConsultaRepartoCalificacionDAO(DAOManager adm_manager)
	    throws B2BException
	{
		return (ConsultaRepartoCalificacionDAO)adm_manager.getDAO(ConsultaRepartoCalificacionDAO.class);
	}

	/**
	 * Retorna el valor de consulta solicitud DAO.
	 *
	 * @param adm_manager correspondiente al valor del tipo de objeto DAOManager
	 * @return el valor de consulta solicitud DAO
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public static ConsultaSolicitudDAO getConsultaSolicitudDAO(DAOManager adm_manager)
	    throws B2BException
	{
		return (ConsultaSolicitudDAO)adm_manager.getDAO(ConsultaSolicitudDAO.class);
	}

	/**
	 * Retorna el valor de consulta solicitud reproduccion DAO.
	 *
	 * @param adm_manager correspondiente al valor del tipo de objeto DAOManager
	 * @return el valor de consulta solicitud reproduccion DAO
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public static SolicitudReproduccionDAO getConsultaSolicitudReproduccionDAO(DAOManager adm_manager)
	    throws B2BException
	{
		return (SolicitudReproduccionDAO)adm_manager.getDAO(SolicitudReproduccionDAO.class);
	}

	/**
	 * Retorna el valor de consulta trazabilidad DAO.
	 *
	 * @param adm_manager correspondiente al valor del tipo de objeto DAOManager
	 * @return el valor de consulta trazabilidad DAO
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public static ConsultaTrazabilidadDAO getConsultaTrazabilidadDAO(DAOManager adm_manager)
	    throws B2BException
	{
		return (ConsultaTrazabilidadDAO)adm_manager.getDAO(ConsultaTrazabilidadDAO.class);
	}

	/**
	 * Retorna el valor de consultas DAO.
	 *
	 * @param adm_manager correspondiente al valor del tipo de objeto DAOManager
	 * @return el valor de consultas DAO
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public static ConsultasDAO getConsultasDAO(DAOManager adm_manager)
	    throws B2BException
	{
		return (ConsultasDAO)adm_manager.getDAO(ConsultasDAO.class);
	}

	/**
	 * Retorna el valor de consultas pgn DAO.
	 *
	 * @param adm_manager correspondiente al valor del tipo de objeto DAOManager
	 * @return el valor de consultas pgn DAO
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public static com.bachue.snr.prosnr01.dao.pgn.ConsultasDAO getConsultasPgnDAO(DAOManager adm_manager)
	    throws B2BException
	{
		return (com.bachue.snr.prosnr01.dao.pgn.ConsultasDAO)adm_manager.getDAO(
		    com.bachue.snr.prosnr01.dao.pgn.ConsultasDAO.class
		);
	}

	/**
	 * Retorna el valor de consultas reportes DAO.
	 *
	 * @param adm_manager correspondiente al valor del tipo de objeto DAOManager
	 * @return el valor de consultas reportes DAO
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public static com.bachue.snr.prosnr01.dao.pgn.ConsultasDAO getConsultasReportesDAO(DAOManager adm_manager)
	    throws B2BException
	{
		return (com.bachue.snr.prosnr01.dao.pgn.ConsultasDAO)adm_manager.getDAO(
		    com.bachue.snr.prosnr01.dao.pgn.ConsultasDAO.class
		);
	}

	/**
	 * Retorna Objeto o variable de valor convenio circulo registral DAO.
	 *
	 * @param adm_manager de adm manager
	 * @return el valor de convenio circulo registral DAO
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public static ConvenioCirculoRegistralDAO getConvenioCirculoRegistralDAO(DAOManager adm_manager)
	    throws B2BException
	{
		return (ConvenioCirculoRegistralDAO)adm_manager.getDAO(ConvenioCirculoRegistralDAO.class);
	}

	/**
	 * Retorna el valor de CoordenadaDAO.
	 *
	 * @param adm_manager correspondiente al valor del tipo de objeto DAOManager
	 * @return el valor de CoordenadaDAO
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public static CoordenadaDAO getCoordenadaDAO(DAOManager adm_manager)
	    throws B2BException
	{
		return (CoordenadaDAO)adm_manager.getDAO(CoordenadaDAO.class);
	}

	/**
	 * Retorna el valor de criterio busqueda DAO.
	 *
	 * @param adm_manager correspondiente al valor del tipo de objeto DAOManager
	 * @return el valor de criterio busqueda DAO
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public static CriterioBusquedaDAO getCriterioBusquedaDAO(DAOManager adm_manager)
	    throws B2BException
	{
		return (CriterioBusquedaDAO)adm_manager.getDAO(CriterioBusquedaDAO.class);
	}

	/**
	 * Retorna el valor de cuenta analista DAO.
	 *
	 * @param adm_manager correspondiente al valor del tipo de objeto DAOManager
	 * @return el valor de tipo acto prohibición DAO
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public static CuentaAnalistaDAO getCuentaAnalistaDAO(DAOManager adm_manager)
	    throws B2BException
	{
		return (CuentaAnalistaDAO)adm_manager.getDAO(CuentaAnalistaDAO.class);
	}

	/**
	 * Retorna el valor de cuenta cupo DAO.
	 *
	 * @param adm_manager correspondiente al valor del tipo de objeto DAOManager
	 * @return el valor de cuenta cupo DAO
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public static CuentaCupoDAO getCuentaCupoDAO(DAOManager adm_manager)
	    throws B2BException
	{
		return (CuentaCupoDAO)adm_manager.getDAO(CuentaCupoDAO.class);
	}

	/**
	 * Retorna Objeto o variable de valor DRXC total BCODAO.
	 *
	 * @param adm_manager de adm manager
	 * @return el valor de DRXC total BCODAO
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public static DRXCTotalBCODAO getDRXCTotalBCODAO(DAOManager adm_manager)
	    throws B2BException
	{
		return (DRXCTotalBCODAO)adm_manager.getDAO(DRXCTotalBCODAO.class);
	}

	/**
	 * Retorna Objeto o variable de valor DRXC total CTADAO.
	 *
	 * @param adm_manager de adm manager
	 * @return el valor de DRXC total CTADAO
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public static DRXCTotalCTADAO getDRXCTotalCTADAO(DAOManager adm_manager)
	    throws B2BException
	{
		return (DRXCTotalCTADAO)adm_manager.getDAO(DRXCTotalCTADAO.class);
	}

	/**
	 * Retorna el valor de datos ant sistema DAO.
	 *
	 * @param adm_manager correspondiente al valor del tipo de objeto DAOManager
	 * @return el valor de datos ant sistema DAO
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public static DatosAntSistemaDAO getDatosAntSistemaDAO(DAOManager adm_manager)
	    throws B2BException
	{
		return (DatosAntSistemaDAO)adm_manager.getDAO(DatosAntSistemaDAO.class);
	}

	/**
	 * Retorna el valor de datos liquidacion DAO.
	 *
	 * @param adm_manager correspondiente al valor del tipo de objeto DAOManager
	 * @return el valor de datos liquidacion DAO
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public static DatosLiquidacionDAO getDatosLiquidacionDAO(DAOManager adm_manager)
	    throws B2BException
	{
		return (DatosLiquidacionDAO)adm_manager.getDAO(DatosLiquidacionDAO.class);
	}

	/**
	 * Retorna Objeto o variable de valor datos plantilla documento DAO.
	 *
	 * @param adm_manager de adm manager
	 * @return el valor de datos plantilla documento DAO
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public static DatosPlantillaDocumentoDAO getDatosPlantillaDocumentoDAO(DAOManager adm_manager)
	    throws B2BException
	{
		return (DatosPlantillaDocumentoDAO)adm_manager.getDAO(DatosPlantillaDocumentoDAO.class);
	}

	/**
	 * Retorna el valor de departamento DAO.
	 *
	 * @param adm_manager correspondiente al valor del tipo de objeto DAOManager
	 * @return el valor de departamento DAO
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public static DepartamentoDAO getDepartamentoDAO(DAOManager adm_manager)
	    throws B2BException
	{
		return (DepartamentoDAO)adm_manager.getDAO(DepartamentoDAO.class);
	}

	/**
	 * Retorna el valor de dependencia snr DAO.
	 *
	 * @param adm_manager correspondiente al valor del tipo de objeto DAOManager
	 * @return el valor de dependencia snr DAO
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public static DependenciaSNRDAO getDependenciaSNRDAO(DAOManager adm_manager)
	    throws B2BException
	{
		return (DependenciaSNRDAO)adm_manager.getDAO(DependenciaSNRDAO.class);
	}

	/**
	 * Retorna el valor de desborde DAO.
	 *
	 * @param adm_manager correspondiente al valor del tipo de objeto DAOManager
	 * @return el valor de desborde DAO
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public static DesbordeDAO getDesbordeDAO(DAOManager adm_manager)
	    throws B2BException
	{
		return (DesbordeDAO)adm_manager.getDAO(DesbordeDAO.class);
	}

	/**
	 * Retorna el valor de detalle ant sistema DAO.
	 *
	 * @param adm_manager correspondiente al valor del tipo de objeto DAOManager
	 * @return el valor de detalle ant sistema DAO
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public static DetalleAntSistemaDAO getDetalleAntSistemaDAO(DAOManager adm_manager)
	    throws B2BException
	{
		return (DetalleAntSistemaDAO)adm_manager.getDAO(DetalleAntSistemaDAO.class);
	}

	/**
	 * Retorna el valor de detalle area predio DAO.
	 *
	 * @param adm_manager correspondiente al valor del tipo de objeto DAOManager
	 * @return el valor de detalle area predio DAO
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public static DetalleAreaPredioDAO getDetalleAreaPredioDAO(DAOManager adm_manager)
	    throws B2BException
	{
		return (DetalleAreaPredioDAO)adm_manager.getDAO(DetalleAreaPredioDAO.class);
	}

	/**
	 * Retorna el valor de detalle criterio busqueda DAO.
	 *
	 * @param adm_manager correspondiente al valor del tipo de objeto DAOManager
	 * @return el valor de detalle criterio busqueda DAO
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public static DetalleCriterioBusquedaDAO getDetalleCriterioBusquedaDAO(DAOManager adm_manager)
	    throws B2BException
	{
		return (DetalleCriterioBusquedaDAO)adm_manager.getDAO(DetalleCriterioBusquedaDAO.class);
	}

	/**
	 * Retorna el valor de detalle movimiento cuenta cupo DAO.
	 *
	 * @param adm_manager correspondiente al valor del tipo de objeto DAOManager
	 * @return el valor de detalle movimiento cuenta cupo DAO
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public static DetalleMovimientoCuentaCupoDAO getDetalleMovimientoCuentaCupoDAO(DAOManager adm_manager)
	    throws B2BException
	{
		return (DetalleMovimientoCuentaCupoDAO)adm_manager.getDAO(DetalleMovimientoCuentaCupoDAO.class);
	}

	/**
	 * Retorna el valor de detalle proceso consulta DAO.
	 *
	 * @param adm_manager correspondiente al valor del tipo de objeto DAOManager
	 * @return el valor de detalle proceso consulta DAO
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public static DetalleProcesoConsultaDAO getDetalleProcesoConsultaDAO(DAOManager adm_manager)
	    throws B2BException
	{
		return (DetalleProcesoConsultaDAO)adm_manager.getDAO(DetalleProcesoConsultaDAO.class);
	}

	/**
	 * Retorna el valor de acc devolucion dinero DAO.
	 *
	 * @param adm_manager correspondiente al valor del tipo de objeto DAOManager
	 * @return el valor de acc devolucion dinero DAO
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public static DevolucionDineroDAO getDevolucionDineroDAO(DAOManager adm_manager)
	    throws B2BException
	{
		return (DevolucionDineroDAO)adm_manager.getDAO(DevolucionDineroDAO.class);
	}

	/**
	 * Retorna el valor de direccion predio acc DAO.
	 *
	 * @param adm_manager correspondiente al valor del tipo de objeto DAOManager
	 * @return el valor de direccion predio acc DAO
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public static DireccionPredioAccDAO getDireccionPredioAccDAO(DAOManager adm_manager)
	    throws B2BException
	{
		return (DireccionPredioAccDAO)adm_manager.getDAO(DireccionPredioAccDAO.class);
	}

	/**
	 * Retorna el valor de direccion predio DAO.
	 *
	 * @param adm_manager correspondiente al valor del tipo de objeto DAOManager
	 * @return el valor de direccion predio DAO
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public static DireccionPredioDAO getDireccionPredioDAO(DAOManager adm_manager)
	    throws B2BException
	{
		return (DireccionPredioDAO)adm_manager.getDAO(DireccionPredioDAO.class);
	}

	/**
	 * Retorna el valor de documento DAO.
	 *
	 * @param adm_manager correspondiente al valor del tipo de objeto DAOManager
	 * @return el valor de documento DAO
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public static DocumentoDAO getDocumentoDAO(DAOManager adm_manager)
	    throws B2BException
	{
		return (DocumentoDAO)adm_manager.getDAO(DocumentoDAO.class);
	}

	/**
	 * Retorna el valor de documentos salida DAO.
	 *
	 * @param adm_manager correspondiente al valor del tipo de objeto DAOManager
	 * @return el valor de documentos salida DAO
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public static DocumentosSalidaDAO getDocumentosSalidaDAO(DAOManager adm_manager)
	    throws B2BException
	{
		return (DocumentosSalidaDAO)adm_manager.getDAO(DocumentosSalidaDAO.class);
	}

	/**
	 * Retorna el valor de dominio naturaleza juridica DAO.
	 *
	 * @param adm_manager correspondiente al valor del tipo de objeto DAOManager
	 * @return el valor de dominio naturaleza juridica DAO
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public static DominioNaturalezaJuridicaDAO getDominioNaturalezaJuridicaDAO(DAOManager adm_manager)
	    throws B2BException
	{
		return (DominioNaturalezaJuridicaDAO)adm_manager.getDAO(DominioNaturalezaJuridicaDAO.class);
	}

	/**
	 * Retorna el valor de dominio rrr DAO.
	 *
	 * @param adm_manager correspondiente al valor del tipo de objeto DAOManager
	 * @return el valor de dominio rrr DAO
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public static DominioRrrDAO getDominioRrrDAO(DAOManager adm_manager)
	    throws B2BException
	{
		return (DominioRrrDAO)adm_manager.getDAO(DominioRrrDAO.class);
	}

	/**
	 * Retorna el valor de entidad Recaudadora DAO.
	 *
	 * @param adm_manager correspondiente al valor del tipo de objeto DAOManager
	 * @return el valor de tipo acto prohibición DAO
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public static EntidadRecaudadoraConciliacionDAO getEntidadRecaudadoraConciliacionDAO(DAOManager adm_manager)
	    throws B2BException
	{
		return (EntidadRecaudadoraConciliacionDAO)adm_manager.getDAO(EntidadRecaudadoraConciliacionDAO.class);
	}

	/**
	 * Retorna el valor de entidad recaudadora cuenta DAO.
	 *
	 * @param adm_manager correspondiente al valor del tipo de objeto DAOManager
	 * @return el valor de tipo acto prohibición DAO
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public static EntidadRecaudadoraCuentaDAO getEntidadRecaudadoraCuentaDAO(DAOManager adm_manager)
	    throws B2BException
	{
		return (EntidadRecaudadoraCuentaDAO)adm_manager.getDAO(EntidadRecaudadoraCuentaDAO.class);
	}

	/**
	 * Retorna el valor de entidad recaudadora DAO.
	 *
	 * @param adm_manager correspondiente al valor del tipo de objeto DAOManager
	 * @return el valor de entidad recaudadora DAO
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public static EntidadRecaudadoraDAO getEntidadRecaudadoraDAO(DAOManager adm_manager)
	    throws B2BException
	{
		return (EntidadRecaudadoraDAO)adm_manager.getDAO(EntidadRecaudadoraDAO.class);
	}

	/**
	 * Retorna Objeto o variable de valor entidades alerta DAO.
	 *
	 * @param adm_manager de adm manager
	 * @return el valor de entidades alerta DAO
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public static EntidadesAlertaDAO getEntidadesAlertaDAO(DAOManager adm_manager)
	    throws B2BException
	{
		return (EntidadesAlertaDAO)adm_manager.getDAO(EntidadesAlertaDAO.class);
	}

	/**
	 * Retorna Objeto o variable de valor entidades especiales DAO.
	 *
	 * @param adm_manager de adm manager
	 * @return el valor de entidades especiales DAO
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public static EntidadesEspecialesDAO getEntidadesEspecialesDAO(DAOManager adm_manager)
	    throws B2BException
	{
		return (EntidadesEspecialesDAO)adm_manager.getDAO(EntidadesEspecialesDAO.class);
	}

	/**
	 * Retorna el valor de estado actividad DAO.
	 *
	 * @param adm_manager correspondiente al valor del tipo de objeto DAOManager
	 * @return el valor de estado actividad DAO
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public static EstadoActividadDAO getEstadoActividadDAO(DAOManager adm_manager)
	    throws B2BException
	{
		return (EstadoActividadDAO)adm_manager.getDAO(EstadoActividadDAO.class);
	}

	/**
	 * Retorna el valor de estado anotacion DAO.
	 *
	 * @param adm_manager correspondiente al valor del tipo de objeto DAOManager
	 * @return el valor de estado anotacion DAO
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public static EstadoAnotacionDAO getEstadoAnotacionDAO(DAOManager adm_manager)
	    throws B2BException
	{
		return (EstadoAnotacionDAO)adm_manager.getDAO(EstadoAnotacionDAO.class);
	}

	/**
	 * Retorna el valor de estado nupre DAO.
	 *
	 * @param adm_manager correspondiente al valor del tipo de objeto DAOManager
	 * @return el valor de estado nupre DAO
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public static EstadoNupreDAO getEstadoNupreDAO(DAOManager adm_manager)
	    throws B2BException
	{
		return (EstadoNupreDAO)adm_manager.getDAO(EstadoNupreDAO.class);
	}

	/**
	 * Retorna el valor de estado predio dao.
	 *
	 * @param adm_manager correspondiente al valor del tipo de objeto DAOManager
	 * @return el valor de estado predio dao
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public static EstadoPredioDao getEstadoPredioDao(DAOManager adm_manager)
	    throws B2BException
	{
		return (EstadoPredioDao)adm_manager.getDAO(EstadoPredioDao.class);
	}

	/**
	 * Retorna el valor de estado registro DAO.
	 *
	 * @param adm_manager correspondiente al valor del tipo de objeto DAOManager
	 * @return el valor de estado registro DAO
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public static EstadoRegistroDAO getEstadoRegistroDAO(DAOManager adm_manager)
	    throws B2BException
	{
		return (EstadoRegistroDAO)adm_manager.getDAO(EstadoRegistroDAO.class);
	}

	/**
	 * Retorna el valor de estados DAO.
	 *
	 * @param adm_manager correspondiente al valor del tipo de objeto DAOManager
	 * @return el valor de estados DAO
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public static EstadosDAO getEstadosDAO(DAOManager adm_manager)
	    throws B2BException
	{
		return (EstadosDAO)adm_manager.getDAO(EstadosDAO.class);
	}

	/**
	 * Retorna el valor de etapa DAO.
	 *
	 * @param adm_manager correspondiente al valor del tipo de objeto DAOManager
	 * @return el valor de etapa DAO
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public static EtapaDAO getEtapaDAO(DAOManager adm_manager)
	    throws B2BException
	{
		return (EtapaDAO)adm_manager.getDAO(EtapaDAO.class);
	}

	/**
	 * Retorna el valor de etapa trabajo DAO.
	 *
	 * @param adm_manager correspondiente al valor del tipo de objeto DAOManager
	 * @return el valor de etapa trabajo DAO
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public static EtapaTrabajoDAO getEtapaTrabajoDAO(DAOManager adm_manager)
	    throws B2BException
	{
		return (EtapaTrabajoDAO)adm_manager.getDAO(EtapaTrabajoDAO.class);
	}

	/**
	 * Retorna Objeto o variable de valor expediente DAO.
	 *
	 * @param adm_manager de adm manager
	 * @return el valor de expediente DAO
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public static ExpedienteDAO getExpedienteDAO(DAOManager adm_manager)
	    throws B2BException
	{
		return (ExpedienteDAO)adm_manager.getDAO(ExpedienteDAO.class);
	}

	/**
	 * Retorna Objeto o variable de valor extracto banco mensual DAO.
	 *
	 * @param adm_manager de adm manager
	 * @return el valor de extracto banco mensual DAO
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public static ExtractoBancoMensualDAO getExtractoBancoMensualDAO(DAOManager adm_manager)
	    throws B2BException
	{
		return (ExtractoBancoMensualDAO)adm_manager.getDAO(ExtractoBancoMensualDAO.class);
	}

	/**
	 * Retorna Objeto o variable de valor extracto diario DAO.
	 *
	 * @param adm_manager de adm manager
	 * @return el valor de extracto diario DAO
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public static ExtractoDiarioDAO getExtractoDiarioDAO(DAOManager adm_manager)
	    throws B2BException
	{
		return (ExtractoDiarioDAO)adm_manager.getDAO(ExtractoDiarioDAO.class);
	}

	/**
	 * Retorna Objeto o variable de valor extracto mensual DAO.
	 *
	 * @param adm_manager de adm manager
	 * @return el valor de extracto mensual DAO
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public static ExtractoMensualDAO getExtractoMensualDAO(DAOManager adm_manager)
	    throws B2BException
	{
		return (ExtractoMensualDAO)adm_manager.getDAO(ExtractoMensualDAO.class);
	}

	/**
	 * Retorna el valor de fases DAO.
	 *
	 * @param adm_manager correspondiente al valor del tipo de objeto DAOManager
	 * @return el valor de fases DAO
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public static FasesDAO getFasesDAO(DAOManager adm_manager)
	    throws B2BException
	{
		return (FasesDAO)adm_manager.getDAO(FasesDAO.class);
	}

	/**
	 * Retorna el valor de festivo nacional DAO.
	 *
	 * @param adm_manager correspondiente al valor del tipo de objeto DAOManager
	 * @return el valor de festivo nacional DAO
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public static FestivoNacionalDAO getFestivoNacionalDAO(DAOManager adm_manager)
	    throws B2BException
	{
		return (FestivoNacionalDAO)adm_manager.getDAO(FestivoNacionalDAO.class);
	}

	/**
	 * Retorna el valor de firma masiva DAO.
	 *
	 * @param adm_manager correspondiente al valor del tipo de objeto DAOManager
	 * @return el valor de firma masiva DAO
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public static FirmaMasivaDAO getFirmaMasivaDAO(DAOManager adm_manager)
	    throws B2BException
	{
		return (FirmaMasivaDAO)adm_manager.getDAO(FirmaMasivaDAO.class);
	}

	/**
	 * Retorna el valor de gobernacion DAO.
	 *
	 * @param adm_manager correspondiente al valor del tipo de objeto DAOManager
	 * @return el valor de gobernacion DAO
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public static GobernacionDAO getGobernacionDAO(DAOManager adm_manager)
	    throws B2BException
	{
		return (GobernacionDAO)adm_manager.getDAO(GobernacionDAO.class);
	}

	/**
	 * Retorna el valor de grupo naturaleza juridica DAO.
	 *
	 * @param adm_manager correspondiente al valor del tipo de objeto DAOManager
	 * @return el valor de grupo naturaleza juridica DAO
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public static GrupoNaturalezaJuridicaDAO getGrupoNaturalezaJuridicaDAO(DAOManager adm_manager)
	    throws B2BException
	{
		return (GrupoNaturalezaJuridicaDAO)adm_manager.getDAO(GrupoNaturalezaJuridicaDAO.class);
	}

	/**
	 * Retorna el valor de HTR estado predio DAO.
	 *
	 * @param adm_manager correspondiente al valor del tipo de objeto DAOManager
	 * @return el valor de HTR estado predio DAO
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public static EstadoPredioDAO getHTREstadoPredioDAO(DAOManager adm_manager)
	    throws B2BException
	{
		return (EstadoPredioDAO)adm_manager.getDAO(EstadoPredioDAO.class);
	}

	/**
	 * Retorna el valor de imagenes DAO.
	 *
	 * @param adm_manager correspondiente al valor del tipo de objeto DAOManager
	 * @return el valor de imagenes DAO
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public static ImagenesDAO getImagenesDAO(DAOManager adm_manager)
	    throws B2BException
	{
		return (ImagenesDAO)adm_manager.getDAO(ImagenesDAO.class);
	}

	/**
	 * Retorna el valor de instancia consulta DAO.
	 *
	 * @param adm_manager correspondiente al valor del tipo de objeto DAOManager
	 * @return el valor de instancia consulta DAO
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public static IncAviAleMenNotDAO getIncAviAleMenNotDAO(DAOManager adm_manager)
	    throws B2BException
	{
		return (IncAviAleMenNotDAO)adm_manager.getDAO(IncAviAleMenNotDAO.class);
	}

	/**
	 * Retorna el valor de instancia consulta DAO.
	 *
	 * @param adm_manager correspondiente al valor del tipo de objeto DAOManager
	 * @return el valor de instancia consulta DAO
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public static InstanciaConsultaDAO getInstanciaConsultaDAO(DAOManager adm_manager)
	    throws B2BException
	{
		return (InstanciaConsultaDAO)adm_manager.getDAO(InstanciaConsultaDAO.class);
	}

	/**
	 * Retorna el valor de interesado documento tipo DAO.
	 *
	 * @param adm_manager correspondiente al valor del tipo de objeto DAOManager
	 * @return el valor de interesado documento tipo DAO
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public static InteresadoDocumentoTipoDAO getInteresadoDocumentoTipoDAO(DAOManager adm_manager)
	    throws B2BException
	{
		return (InteresadoDocumentoTipoDAO)adm_manager.getDAO(InteresadoDocumentoTipoDAO.class);
	}

	/**
	 * Retorna el valor de interesado natural genero DAO.
	 *
	 * @param adm_manager correspondiente al valor del tipo de objeto DAOManager
	 * @return el valor de interesado natural genero DAO
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public static InteresadoNaturalGeneroDAO getInteresadoNaturalGeneroDAO(DAOManager adm_manager)
	    throws B2BException
	{
		return (InteresadoNaturalGeneroDAO)adm_manager.getDAO(InteresadoNaturalGeneroDAO.class);
	}

	/**
	 * Retorna el valor de LetraEjeDAO.
	 *
	 * @param adm_manager correspondiente al valor del tipo de objeto DAOManager
	 * @return el valor de LetraEjeDAO
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public static LetraEjeDAO getLetraEjeDAO(DAOManager adm_manager)
	    throws B2BException
	{
		return (LetraEjeDAO)adm_manager.getDAO(LetraEjeDAO.class);
	}

	/**
	 * Retorna el valor de libro antiguo sistema DAO.
	 *
	 * @param adm_manager correspondiente al valor del tipo de objeto DAOManager
	 * @return el valor de libro antiguo sistema DAO
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public static LibroAntiguoSistemaDao getLibroAntiguoSistemaDAO(DAOManager adm_manager)
	    throws B2BException
	{
		return (LibroAntiguoSistemaDao)adm_manager.getDAO(LibroAntiguoSistemaDao.class);
	}

	/**
	 * Retorna el valor de Libro Testamento DAO.
	 *
	 * @param adm_manager correspondiente al valor del tipo de objeto DAOManager
	 * @return el valor de Libro Testamento DAO
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public static LibroTestamentoDAO getLibroTestamentoDAO(DAOManager adm_manager)
	    throws B2BException
	{
		return (LibroTestamentoDAO)adm_manager.getDAO(LibroTestamentoDAO.class);
	}

	/**
	 * Retorna el valor de lindero predio DAO.
	 *
	 * @param adm_manager correspondiente al valor del tipo de objeto DAOManager
	 * @return el valor de lindero predio DAO
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public static LinderoPredioDAO getLinderoPredioDAO(DAOManager adm_manager)
	    throws B2BException
	{
		return (LinderoPredioDAO)adm_manager.getDAO(LinderoPredioDAO.class);
	}

	/**
	 * Retorna el valor de linea produccion DAO.
	 *
	 * @param adm_manager correspondiente al valor del tipo de objeto DAOManager
	 * @return el valor de linea produccion DAO
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public static LineaProduccionDAO getLineaProduccionDAO(DAOManager adm_manager)
	    throws B2BException
	{
		return (LineaProduccionDAO)adm_manager.getDAO(LineaProduccionDAO.class);
	}

	/**
	 * Retorna el valor de liquidacion item certificado DAO.
	 *
	 * @param adm_manager correspondiente al valor del tipo de objeto DAOManager
	 * @return el valor de liquidacion item certificado DAO
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public static LiquidacionItemCertificadoDAO getLiquidacionItemCertificadoDAO(DAOManager adm_manager)
	    throws B2BException
	{
		return (LiquidacionItemCertificadoDAO)adm_manager.getDAO(LiquidacionItemCertificadoDAO.class);
	}

	/**
	 * Retorna el valor de LiquidacionItemCondicionDAO.
	 *
	 * @param adm_manager correspondiente al valor del tipo de objeto DAOManager
	 * @return el valor de LiquidacionItemCondicionDAO
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public static LiquidacionItemCondicionDAO getLiquidacionItemCondicionDAO(DAOManager adm_manager)
	    throws B2BException
	{
		return (LiquidacionItemCondicionDAO)adm_manager.getDAO(LiquidacionItemCondicionDAO.class);
	}

	/**
	 * Retorna Objeto o variable de valor liquidaciones historicos folio DAO.
	 *
	 * @param adm_manager de adm manager
	 * @return el valor de liquidaciones historicos folio DAO
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public static LiquidacionesHistoricosFolioDAO getLiquidacionesHistoricosFolioDAO(DAOManager adm_manager)
	    throws B2BException
	{
		return (LiquidacionesHistoricosFolioDAO)adm_manager.getDAO(LiquidacionesHistoricosFolioDAO.class);
	}

	/**
	 * Retorna Objeto o variable de valor liquidaciones historicos sir DAO.
	 *
	 * @param adm_manager de adm manager
	 * @return el valor de liquidaciones historicos sir DAO
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public static LiquidacionesHistoricosSirDAO getLiquidacionesHistoricosSirDAO(DAOManager adm_manager)
	    throws B2BException
	{
		return (LiquidacionesHistoricosSirDAO)adm_manager.getDAO(LiquidacionesHistoricosSirDAO.class);
	}

	/**
	 * Retorna el valor de maestro movimiento cuenta cupo DAO.
	 *
	 * @param adm_manager correspondiente al valor del tipo de objeto DAOManager
	 * @return el valor de maestro movimiento cuenta cupo DAO
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public static MaestroMovimientoCuentaCupoDAO getMaestroMovimientoCuentaCupoDAO(DAOManager adm_manager)
	    throws B2BException
	{
		return (MaestroMovimientoCuentaCupoDAO)adm_manager.getDAO(MaestroMovimientoCuentaCupoDAO.class);
	}

	/**
	 * Retorna el valor de matricula segregacion DAO.
	 *
	 * @param adm_manager correspondiente al valor del tipo de objeto DAOManager
	 * @return el valor de matricula segregacion DAO
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public static MatriculaSegregacionDAO getMatriculaSegregacionDAO(DAOManager adm_manager)
	    throws B2BException
	{
		return (MatriculaSegregacionDAO)adm_manager.getDAO(MatriculaSegregacionDAO.class);
	}

	/**
	 * Retorna el valor de medida area DAO.
	 *
	 * @param adm_manager correspondiente al valor del tipo de objeto DAOManager
	 * @return el valor de medida area DAO
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public static MedidaAreaDAO getMedidaAreaDAO(DAOManager adm_manager)
	    throws B2BException
	{
		return (MedidaAreaDAO)adm_manager.getDAO(MedidaAreaDAO.class);
	}

	/**
	 * Retorna el valor de medio recaudo DAO.
	 *
	 * @param adm_manager correspondiente al valor del tipo de objeto DAOManager
	 * @return el valor de medio recaudo DAO
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public static MedioRecaudoDAO getMedioRecaudoDAO(DAOManager adm_manager)
	    throws B2BException
	{
		return (MedioRecaudoDAO)adm_manager.getDAO(MedioRecaudoDAO.class);
	}

	/**
	 * Retorna el valor de mensaje acuse detalle DAO.
	 *
	 * @param adm_manager correspondiente al valor del tipo de objeto DAOManager
	 * @return el valor de mensaje acuse detalle DAO
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public static MensajeAcuseDetalleDAO getMensajeAcuseDetalleDAO(DAOManager adm_manager)
	    throws B2BException
	{
		return (MensajeAcuseDetalleDAO)adm_manager.getDAO(MensajeAcuseDetalleDAO.class);
	}

	/**
	 * Retorna el valor de mensaje adjunto DAO.
	 *
	 * @param adm_manager correspondiente al valor del tipo de objeto DAOManager
	 * @return el valor de mensaje adjunto DAO
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public static MensajeAdjuntoDAO getMensajeAdjuntoDAO(DAOManager adm_manager)
	    throws B2BException
	{
		return (MensajeAdjuntoDAO)adm_manager.getDAO(MensajeAdjuntoDAO.class);
	}

	/**
	 * Retorna Objeto o variable de valor mensaje comunicacion DAO.
	 *
	 * @param adm_manager correspondiente al valor del tipo de objeto DAOManager
	 * @return el valor de mensaje comunicacion DAO
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public static MensajeComunicacionDAO getMensajeComunicacionDAO(DAOManager adm_manager)
	    throws B2BException
	{
		return (MensajeComunicacionDAO)adm_manager.getDAO(MensajeComunicacionDAO.class);
	}

	/**
	 * Retorna Objeto o variable de valor mensaje comunicacion enviado DAO.
	 *
	 * @param adm_manager correspondiente al valor del tipo de objeto DAOManager
	 * @return el valor de mensaje comunicacion enviado DAO
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public static MensajeComunicacionEnviadoDAO getMensajeComunicacionEnviadoDAO(DAOManager adm_manager)
	    throws B2BException
	{
		return (MensajeComunicacionEnviadoDAO)adm_manager.getDAO(MensajeComunicacionEnviadoDAO.class);
	}

	/**
	 * Retorna el valor de mensaje DAO.
	 *
	 * @param adm_manager correspondiente al valor del tipo de objeto DAOManager
	 * @return el valor de mensaje DAO
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public static MensajeDAO getMensajeDAO(DAOManager adm_manager)
	    throws B2BException
	{
		return (MensajeDAO)adm_manager.getDAO(MensajeDAO.class);
	}

	/**
	 * Retorna el valor de mensaje respuesta DAO.
	 *
	 * @param adm_manager correspondiente al valor del tipo de objeto DAOManager
	 * @return el valor de mensaje respuesta DAO
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public static MensajeRespuestaDAO getMensajeRespuestaDAO(DAOManager adm_manager)
	    throws B2BException
	{
		return (MensajeRespuestaDAO)adm_manager.getDAO(MensajeRespuestaDAO.class);
	}

	/**
	 * Retorna el valor de mod cuenta cupo DAO.
	 *
	 * @param adm_manager correspondiente al valor del tipo de objeto DAOManager
	 * @return el valor de mod cuenta cupo DAO
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public static ModCuentaCupoDAO getModCuentaCupoDAO(DAOManager adm_manager)
	    throws B2BException
	{
		return (ModCuentaCupoDAO)adm_manager.getDAO(ModCuentaCupoDAO.class);
	}

	/**
	 * Retorna el valor de motivo tramite DAO.
	 *
	 * @param adm_manager correspondiente al valor del tipo de objeto DAOManager
	 * @return el valor de motivo tramite DAO
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public static MotivoTramiteDAO getMotivoTramiteDAO(DAOManager adm_manager)
	    throws B2BException
	{
		return (MotivoTramiteDAO)adm_manager.getDAO(MotivoTramiteDAO.class);
	}

	/**
	 * Retorna el valor de motivo tramite trabajo DAO.
	 *
	 * @param adm_manager correspondiente al valor del tipo de objeto DAOManager
	 * @return el valor de motivo tramite trabajo DAO
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public static MotivoTramiteTrabajoDAO getMotivoTramiteTrabajoDAO(DAOManager adm_manager)
	    throws B2BException
	{
		return (MotivoTramiteTrabajoDAO)adm_manager.getDAO(MotivoTramiteTrabajoDAO.class);
	}

	/**
	 * Retorna la clase encargada de las interacciones con base de datos para la tabla SDB_CON_MULTICASH_CABECERA del módulo de conciliaciones.
	 *
	 * @param adm_manager Contexto transaccional al que se asociara el DAO retornado
	 * @return clase encargada de las interacciones con base de datos para la tabla SDB_CON_MULTICASH_CABECERA del módulo de conciliaciones
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public static MulticashCabeceraDAO getMulticashCabeceraDAO(DAOManager adm_manager)
	    throws B2BException
	{
		return (MulticashCabeceraDAO)adm_manager.getDAO(MulticashCabeceraDAO.class);
	}

	/**
	 * Retorna la clase encargada de las interacciones con base de datos para la tabla SDB_CON_MULTICASH_DETALLE del módulo de conciliaciones.
	 *
	 * @param adm_manager Contexto transaccional al que se asociara el DAO retornado
	 * @return clase encargada de las interacciones con base de datos para la tabla SDB_CON_MULTICASH_DETALLE del módulo de conciliaciones
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public static MulticashDetalleDAO getMulticashDetalleDAO(DAOManager adm_manager)
	    throws B2BException
	{
		return (MulticashDetalleDAO)adm_manager.getDAO(MulticashDetalleDAO.class);
	}

	/**
	 * Retorna el valor de municipio DAO.
	 *
	 * @param adm_manager correspondiente al valor del tipo de objeto DAOManager
	 * @return el valor de municipio DAO
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public static MunicipioDAO getMunicipioDAO(DAOManager adm_manager)
	    throws B2BException
	{
		return (MunicipioDAO)adm_manager.getDAO(MunicipioDAO.class);
	}

	/**
	 * Retorna el valor de naturaleza juridica DAO.
	 *
	 * @param adm_manager correspondiente al valor del tipo de objeto DAOManager
	 * @return el valor de naturaleza juridica DAO
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public static NaturalezaJuridicaDAO getNaturalezaJuridicaDAO(DAOManager adm_manager)
	    throws B2BException
	{
		return (NaturalezaJuridicaDAO)adm_manager.getDAO(NaturalezaJuridicaDAO.class);
	}

	/**
	 * Retorna el valor de nota crédito cuenta cupo DAO.
	 *
	 * @param adm_manager de adm manager
	 * @return el valor de nota crédito cuenta cupo DAO
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public static NotaCreditoCuentaCupoDAO getNotaCreditoCuentaCupoDAO(DAOManager adm_manager)
	    throws B2BException
	{
		return (NotaCreditoCuentaCupoDAO)adm_manager.getDAO(NotaCreditoCuentaCupoDAO.class);
	}

	/**
	 * Retorna el valor de nota devolutiva DAO.
	 *
	 * @param adm_manager correspondiente al valor del tipo de objeto DAOManager
	 * @return el valor de nota devolutiva DAO
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public static NotaDevolutivaDAO getNotaDevolutivaDAO(DAOManager adm_manager)
	    throws B2BException
	{
		return (NotaDevolutivaDAO)adm_manager.getDAO(NotaDevolutivaDAO.class);
	}

	/**
	 * Retorna el valor de notificacion detalle DAO.
	 *
	 * @param adm_manager correspondiente al valor del tipo de objeto DAOManager
	 * @return el valor de notificacion detalle DAO
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public static NotificacionDetalleDAO getNotificacionDetalleDAO(DAOManager adm_manager)
	    throws B2BException
	{
		return (NotificacionDetalleDAO)adm_manager.getDAO(NotificacionDetalleDAO.class);
	}

	/**
	 * Retorna el valor de notificacion pago DAO.
	 *
	 * @param adm_manager correspondiente al valor del tipo de objeto DAOManager
	 * @return el valor de notificacion pago DAO
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public static NotificacionPagoDAO getNotificacionPagoDAO(DAOManager adm_manager)
	    throws B2BException
	{
		return (NotificacionPagoDAO)adm_manager.getDAO(NotificacionPagoDAO.class);
	}

	/**
	 * Retorna el valor de notificacion pago recibida DAO.
	 *
	 * @param adm_manager correspondiente al valor del tipo de objeto DAOManager
	 * @return el valor de notificacion pago recibida DAO
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public static NotificacionPagoRecibidaDAO getNotificacionPagoRecibidaDAO(DAOManager adm_manager)
	    throws B2BException
	{
		return (NotificacionPagoRecibidaDAO)adm_manager.getDAO(NotificacionPagoRecibidaDAO.class);
	}

	/**
	 * Retorna el valor de Numeracion Oficios circulos  DAO.
	 *
	 * @param adm_manager correspondiente al valor del tipo de objeto DAOManager
	 * @return el valor de Numeracion Oficios Circulo  DAO
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public static NumeracionOficiosCirculoDAO getNumeracionOficiosCirculoDAO(DAOManager adm_manager)
	    throws B2BException
	{
		return (NumeracionOficiosCirculoDAO)adm_manager.getDAO(NumeracionOficiosCirculoDAO.class);
	}

	/**
	 * Retorna el valor de Numeracion Resolucion circulos  DAO.
	 *
	 * @param adm_manager correspondiente al valor del tipo de objeto DAOManager
	 * @return el valor de Numeracion resolucion  Circulo  DAO
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public static NumeracionResolucionCirculoDAO getNumeracionResolucionCirculoDAO(DAOManager adm_manager)
	    throws B2BException
	{
		return (NumeracionResolucionCirculoDAO)adm_manager.getDAO(NumeracionResolucionCirculoDAO.class);
	}

	/**
	 * Retorna el valor de oficina origen DAO.
	 *
	 * @param adm_manager correspondiente al valor del tipo de objeto DAOManager
	 * @return el valor de oficina origen DAO
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public static OficinaOrigenDAO getOficinaOrigenDAO(DAOManager adm_manager)
	    throws B2BException
	{
		return (OficinaOrigenDAO)adm_manager.getDAO(OficinaOrigenDAO.class);
	}

	/**
	 * Retorna el valor de oficios texto DAO.
	 *
	 * @param adm_manager correspondiente al valor del tipo de objeto DAOManager
	 * @return el valor de oficios texto DAO
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public static OficiosTextoDAO getOficiosTextoDAO(DAOManager adm_manager)
	    throws B2BException
	{
		return (OficiosTextoDAO)adm_manager.getDAO(OficiosTextoDAO.class);
	}

	/**
	 * Retorna el valor de Opción DAO.
	 *
	 * @param adm_manager correspondiente al valor del tipo de objeto DAOManager
	 * @return el valor de Opción DAO
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public static OpcionDAO getOpcionDAO(DAOManager adm_manager)
	    throws B2BException
	{
		return (OpcionDAO)adm_manager.getDAO(OpcionDAO.class);
	}

	/**
	
	 * Retorna el valor de Opción Etapa DAO.
	 *
	 * @param adm_manager correspondiente al valor del tipo de objeto DAOManager
	 * @return el valor de Opción Etapa DAO
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public static OpcionEtapaDAO getOpcionEtapaDAO(DAOManager adm_manager)
	    throws B2BException
	{
		return (OpcionEtapaDAO)adm_manager.getDAO(OpcionEtapaDAO.class);
	}

	/**
	 * Retorna el valor de origen DAO.
	 *
	 * @param adm_manager correspondiente al valor del tipo de objeto DAOManager
	 * @return el valor de origen DAO
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public static OrigenDAO getOrigenDAO(DAOManager adm_manager)
	    throws B2BException
	{
		return (OrigenDAO)adm_manager.getDAO(OrigenDAO.class);
	}

	/**
	 * Retorna el valor de pago cuenta cupo DAO.
	 *
	 * @param adm_manager correspondiente al valor del tipo de objeto DAOManager
	 * @return el valor de pago cuenta cupo DAO
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public static PagoCuentaCupoDAO getPagoCuentaCupoDAO(DAOManager adm_manager)
	    throws B2BException
	{
		return (PagoCuentaCupoDAO)adm_manager.getDAO(PagoCuentaCupoDAO.class);
	}

	/**
	 * Retorna Objeto o variable de valor pagos folio DAO.
	 *
	 * @param adm_manager de adm manager
	 * @return el valor de pagos folio DAO
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public static PagosFolioDAO getPagosFolioDAO(DAOManager adm_manager)
	    throws B2BException
	{
		return (PagosFolioDAO)adm_manager.getDAO(PagosFolioDAO.class);
	}

	/**
	 * Retorna Objeto o variable de valor pagos sir DAO.
	 *
	 * @param adm_manager de adm manager
	 * @return el valor de pagos sir DAO
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public static PagosSirDAO getPagosSirDAO(DAOManager adm_manager)
	    throws B2BException
	{
		return (PagosSirDAO)adm_manager.getDAO(PagosSirDAO.class);
	}

	/**
	 * Retorna el valor de pais DAO.
	 *
	 * @param adm_manager correspondiente al valor del tipo de objeto DAOManager
	 * @return el valor de pais DAO
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public static PaisDAO getPaisDAO(DAOManager adm_manager)
	    throws B2BException
	{
		return (PaisDAO)adm_manager.getDAO(PaisDAO.class);
	}

	/**
	 * Retorna el valor de parametros mensaje DAO.
	 *
	 * @param adm_manager correspondiente al valor del tipo de objeto DAOManager
	 * @return el valor de parametros mensaje DAO
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public static ParametrosMensajeDAO getParametrosMensajeDAO(DAOManager adm_manager)
	    throws B2BException
	{
		return (ParametrosMensajeDAO)adm_manager.getDAO(ParametrosMensajeDAO.class);
	}

	/**
	 * Retorna el valor de parte interesada DAO.
	 *
	 * @param adm_manager correspondiente al valor del tipo de objeto DAOManager
	 * @return el valor de parte interesada DAO
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public static ParteInteresadaDAO getParteInteresadaDAO(DAOManager adm_manager)
	    throws B2BException
	{
		return (ParteInteresadaDAO)adm_manager.getDAO(ParteInteresadaDAO.class);
	}

	/**
	 * Retorna Objeto o variable de valor periodicidad DAO.
	 *
	 * @param adm_manager de adm manager
	 * @return el valor de periodicidad DAO
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public static PeriodicidadDAO getPeriodicidadDAO(DAOManager adm_manager)
	    throws B2BException
	{
		return (PeriodicidadDAO)adm_manager.getDAO(PeriodicidadDAO.class);
	}

	/**
	 * Retorna el valor de periodo corte DAO.
	 *
	 * @param adm_manager correspondiente al valor del tipo de objeto DAOManager
	 * @return el valor de tipo acto prohibición DAO
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public static PeriodoCorteDAO getPeriodoCorteDAO(DAOManager adm_manager)
	    throws B2BException
	{
		return (PeriodoCorteDAO)adm_manager.getDAO(PeriodoCorteDAO.class);
	}

	/**
	 * Retorna el valor de persona correo electronico DAO.
	 *
	 * @param adm_manager correspondiente al valor del tipo de objeto DAOManager
	 * @return el valor de persona correo electronico DAO
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public static PersonaCorreoElectronicoDAO getPersonaCorreoElectronicoDAO(DAOManager adm_manager)
	    throws B2BException
	{
		return (PersonaCorreoElectronicoDAO)adm_manager.getDAO(PersonaCorreoElectronicoDAO.class);
	}

	/**
	 * Retorna el valor de persona DAO.
	 *
	 * @param adm_manager correspondiente al valor del tipo de objeto DAOManager
	 * @return el valor de persona DAO
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public static PersonaDAO getPersonaDAO(DAOManager adm_manager)
	    throws B2BException
	{
		return (PersonaDAO)adm_manager.getDAO(PersonaDAO.class);
	}

	/**
	 * Retorna el valor de persona direccion DAO.
	 *
	 * @param adm_manager correspondiente al valor del tipo de objeto DAOManager
	 * @return el valor de persona direccion DAO
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public static PersonaDireccionDAO getPersonaDireccionDAO(DAOManager adm_manager)
	    throws B2BException
	{
		return (PersonaDireccionDAO)adm_manager.getDAO(PersonaDireccionDAO.class);
	}

	/**
	 * Retorna el valor de persona entrega DAO.
	 *
	 * @param adm_manager correspondiente al valor del tipo de objeto DAOManager
	 * @return el valor de persona entrega DAO
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public static PersonaEntregaDAO getPersonaEntregaDAO(DAOManager adm_manager)
	    throws B2BException
	{
		return (PersonaEntregaDAO)adm_manager.getDAO(PersonaEntregaDAO.class);
	}

	/**
	 * Retorna el valor de Persona Notificacion DAO.
	 *
	 * @param adm_manager correspondiente al valor del tipo de objeto DAOManager
	 * @return el valor de acc completitud documental DAO
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public static PersonaNotificacionDAO getPersonaNotificacionDAO(DAOManager adm_manager)
	    throws B2BException
	{
		return (PersonaNotificacionDAO)adm_manager.getDAO(PersonaNotificacionDAO.class);
	}

	/**
	 * Retorna el valor de persona presentada DAO.
	 *
	 * @param adm_manager correspondiente al valor del tipo de objeto DAOManager
	 * @return el valor de persona presentada DAO
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public static PersonaPresentadaDAO getPersonaPresentadaDAO(DAOManager adm_manager)
	    throws B2BException
	{
		return (PersonaPresentadaDAO)adm_manager.getDAO(PersonaPresentadaDAO.class);
	}

	/**
	 * Retorna el valor de persona telefono DAO.
	 *
	 * @param adm_manager correspondiente al valor del tipo de objeto DAOManager
	 * @return el valor de persona telefono DAO
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public static PersonaTelefonoDAO getPersonaTelefonoDAO(DAOManager adm_manager)
	    throws B2BException
	{
		return (PersonaTelefonoDAO)adm_manager.getDAO(PersonaTelefonoDAO.class);
	}

	/**
	 * Retorna Objeto o variable de valor plantilla comunicación DAO.
	 *
	 * @param adm_manager de adm manager
	 * @return el valor de plantilla notificacion DAO
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public static PlantillaComunicacionDAO getPlantillaComunicacionDAO(DAOManager adm_manager)
	    throws B2BException
	{
		return (PlantillaComunicacionDAO)adm_manager.getDAO(PlantillaComunicacionDAO.class);
	}

	/**
	 * Retorna el valor de plantilla DAO.
	 *
	 * @param adm_manager correspondiente al valor del tipo de objeto DAOManager
	 * @return el valor de plantilla DAO
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public static PlantillaDAO getPlantillaDAO(DAOManager adm_manager)
	    throws B2BException
	{
		return (PlantillaDAO)adm_manager.getDAO(PlantillaDAO.class);
	}

	/**
	 * Retorna Objeto o variable de valor plantilla notificacion DAO.
	 *
	 * @param adm_manager de adm manager
	 * @return el valor de plantilla notificacion DAO
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public static PlantillaNotificacionDAO getPlantillaNotificacionDAO(DAOManager adm_manager)
	    throws B2BException
	{
		return (PlantillaNotificacionDAO)adm_manager.getDAO(PlantillaNotificacionDAO.class);
	}

	/**
	 * Retorna el valor del objeto de tipo Predio registro DAO.
	 *
	 * @param adm_manager correspondiente al valor del tipo de objeto DAOManager
	 * @return devuelve el valor del objeto predio registro DAO
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public static PredioRegistroDAO getPredioRegistroDAO(DAOManager adm_manager)
	    throws B2BException
	{
		return (PredioRegistroDAO)adm_manager.getDAO(PredioRegistroDAO.class);
	}

	/**
	 * Retorna el valor de predio segregado DAO.
	 *
	 * @param adm_manager correspondiente al valor del tipo de objeto DAOManager
	 * @return el valor de predio segregado DAO
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public static PredioSegregadoDAO getPredioSegregadoDAO(DAOManager adm_manager)
	    throws B2BException
	{
		return (PredioSegregadoDAO)adm_manager.getDAO(PredioSegregadoDAO.class);
	}

	/**
	 * Retorna el valor de predio tipo dao.
	 *
	 * @param adm_manager correspondiente al valor del tipo de objeto DAOManager
	 * @return el valor de predio tipo dao
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public static PredioTipoDAO getPredioTipoDao(DAOManager adm_manager)
	    throws B2BException
	{
		return (PredioTipoDAO)adm_manager.getDAO(PredioTipoDAO.class);
	}

	/**
	 * Retorna Objeto o variable de valor proc ant sistema ATDAO.
	 *
	 * @param adm_manager de adm manager
	 * @return el valor de proc ant sistema ATDAO
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public static ProcAntSistemaATDAO getProcAntSistemaATDAO(DAOManager adm_manager)
	    throws B2BException
	{
		return (ProcAntSistemaATDAO)adm_manager.getDAO(ProcAntSistemaATDAO.class);
	}

	/**
	 * Retorna el valor de procedimientos DAO.
	 *
	 * @param adm_manager correspondiente al valor del tipo de objeto DAOManager
	 * @return el valor de procedimientos DAO
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public static ProcedimientosDAO getProcedimientosDAO(DAOManager adm_manager)
	    throws B2BException
	{
		return (ProcedimientosDAO)adm_manager.getDAO(ProcedimientosDAO.class);
	}

	/**
	 * Retorna el valor de proceso automatico DAO.
	 *
	 * @param adm_manager correspondiente al valor del tipo de objeto DAOManager
	 * @return el valor de proceso automatico DAO
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public static ProcesoAutomaticoDAO getProcesoAutomaticoDAO(DAOManager adm_manager)
	    throws B2BException
	{
		return (ProcesoAutomaticoDAO)adm_manager.getDAO(ProcesoAutomaticoDAO.class);
	}

	/**
	 * Retorna el valor de proceso canal DAO.
	 *
	 * @param adm_manager correspondiente al valor del tipo de objeto DAOManager
	 * @return el valor de proceso canal DAO
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public static ProcesoCanalDAO getProcesoCanalDAO(DAOManager adm_manager)
	    throws B2BException
	{
		return (ProcesoCanalDAO)adm_manager.getDAO(ProcesoCanalDAO.class);
	}

	/**
	 * Retorna la instancia del DAO proceso conciliación DAO.
	 *
	 * @param adm_manager Argumento de tipo <code>DAOManager</code> que permite realizar la conexión a la base de datos.
	 * @return Retorna la instancia del DAO proceso conciliación DAO.
	 * @throws B2BException Señala que se ha generado una excepción.
	 */
	public static ProcesoConciliacionDAO getProcesoConciliacionDAO(DAOManager adm_manager)
	    throws B2BException
	{
		return (ProcesoConciliacionDAO)adm_manager.getDAO(ProcesoConciliacionDAO.class);
	}

	/**
	 * Retorna el valor de proceso consulta DAO.
	 *
	 * @param adm_manager correspondiente al valor del tipo de objeto DAOManager
	 * @return el valor de proceso consulta DAO
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public static ProcesoConsultaDAO getProcesoConsultaDAO(DAOManager adm_manager)
	    throws B2BException
	{
		return (ProcesoConsultaDAO)adm_manager.getDAO(ProcesoConsultaDAO.class);
	}

	/**
	 * Retorna el valor de proceso DAO.
	 *
	 * @param adm_manager correspondiente al valor del tipo de objeto DAOManager
	 * @return el valor de proceso DAO
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public static ProcesoDAO getProcesoDAO(DAOManager adm_manager)
	    throws B2BException
	{
		return (ProcesoDAO)adm_manager.getDAO(ProcesoDAO.class);
	}

	/**
	 * Retorna el valor de proceso trabajo DAO.
	 *
	 * @param adm_manager correspondiente al valor del tipo de objeto DAOManager
	 * @return el valor de proceso trabajoDAO
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public static ProcesoTrabajoDAO getProcesoTrabajoDAO(DAOManager adm_manager)
	    throws B2BException
	{
		return (ProcesoTrabajoDAO)adm_manager.getDAO(ProcesoTrabajoDAO.class);
	}

	/**
	 * Retorna Objeto o variable de valor propietario DAO.
	 *
	 * @param adm_manager de adm manager
	 * @return el valor de propietario DAO
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public static PropietarioDAO getPropietarioDAO(DAOManager adm_manager)
	    throws B2BException
	{
		return (PropietarioDAO)adm_manager.getDAO(PropietarioDAO.class);
	}

	/**
	 * Retorna el valor de punto recaudo DAO.
	 *
	 * @param adm_manager correspondiente al valor del tipo de objeto DAOManager
	 * @return el valor de punto recaudo DAO
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public static PuntoRecaudoDAO getPuntoRecaudoDAO(DAOManager adm_manager)
	    throws B2BException
	{
		return (PuntoRecaudoDAO)adm_manager.getDAO(PuntoRecaudoDAO.class);
	}

	/**
	 * Retorna el valor de punto recaudo tipo recaudo DAO.
	 *
	 * @param adm_manager correspondiente al valor del tipo de objeto DAOManager
	 * @return el valor de punto recaudo tipo recaudo DAO
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public static PuntoRecaudoTipoRecaudoDAO getPuntoRecaudoTipoRecaudoDAO(DAOManager adm_manager)
	    throws B2BException
	{
		return (PuntoRecaudoTipoRecaudoDAO)adm_manager.getDAO(PuntoRecaudoTipoRecaudoDAO.class);
	}

	/**
	 * Retorna la clase encargada de las interacciones con base de datos para la tabla SDB_PGN_RPT_PROGRAMA del módulo de conciliaciones.
	 *
	 * @param adm_manager Contexto transaccional al que se asociara el DAO retornado
	 * @return clase encargada de las interacciones con base de datos para la tabla SDB_PGN_RPT_PROGRAMA del módulo de conciliaciones
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public static RPTProgramaDAO getRPTProgramaDAO(DAOManager adm_manager)
	    throws B2BException
	{
		return (RPTProgramaDAO)adm_manager.getDAO(RPTProgramaDAO.class);
	}

	/**
	 * Retorna el valor de rango cuantia DAO.
	 *
	 * @param adm_manager correspondiente al valor del tipo de objeto DAOManager
	 * @return el valor de rango cuantia DAO
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public static RangoCuantiaDAO getRangoCuantiaDAO(DAOManager adm_manager)
	    throws B2BException
	{
		return (RangoCuantiaDAO)adm_manager.getDAO(RangoCuantiaDAO.class);
	}

	/**
	 * Retorna el valor de RECURSO DAO.
	 *
	 * @param adm_manager correspondiente al valor del tipo de objeto DAOManager
	 * @return el valor de solicitud DAO
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public static RecursoDAO getRecursoDAO(DAOManager adm_manager)
	    throws B2BException
	{
		return (RecursoDAO)adm_manager.getDAO(RecursoDAO.class);
	}

	/**
	 * Retorna el valor de recurso decision DAO.
	 *
	 * @param adm_manager correspondiente al valor del tipo de objeto DAOManager
	 * @return el valor de tipo decision recurso DAO
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public static RecursoDecisionDAO getRecursoDecisionDAO(DAOManager adm_manager)
	    throws B2BException
	{
		return (RecursoDecisionDAO)adm_manager.getDAO(RecursoDecisionDAO.class);
	}

	/**
	 * Retorna el valor de regional DAO.
	 *
	 * @param adm_manager correspondiente al valor del tipo de objeto DAOManager
	 * @return el valor de regional DAO
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public static RegionalDAO getRegionalDAO(DAOManager adm_manager)
	    throws B2BException
	{
		return (RegionalDAO)adm_manager.getDAO(RegionalDAO.class);
	}

	/**
	 * Retorna el valor de registrar pago DAO.
	 *
	 * @param adm_manager correspondiente al valor del tipo de objeto DAOManager
	 * @return el valor de registrar pago DAO
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public static RegistrarPagoDAO getRegistrarPagoDAO(DAOManager adm_manager)
	    throws B2BException
	{
		return (RegistrarPagoDAO)adm_manager.getDAO(RegistrarPagoDAO.class);
	}

	/**
	 * Retorna el valor de registro anotacion prohibicion dao.
	 *
	 * @param adm_manager correspondiente al valor del tipo de objeto DAOManager
	 * @return el valor de registro anotacion prohibicion dao
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public static RegistroAnotacionProhibicionDAO getRegistroAnotacionProhibicionDao(DAOManager adm_manager)
	    throws B2BException
	{
		return (RegistroAnotacionProhibicionDAO)adm_manager.getDAO(RegistroAnotacionProhibicionDAO.class);
	}

	/**
	 * Retorna el valor de registro calificacion DAO.
	 *
	 * @param adm_manager correspondiente al valor del tipo de objeto DAOManager
	 * @return el valor de registro calificacion DAO
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public static RegistroCalificacionDAO getRegistroCalificacionDAO(DAOManager adm_manager)
	    throws B2BException
	{
		return (RegistroCalificacionDAO)adm_manager.getDAO(RegistroCalificacionDAO.class);
	}

	/**
	 * Retorna el valor de registro mayor valor DAO.
	 *
	 * @param adm_manager correspondiente al valor del tipo de objeto DAOManager
	 * @return el valor de registro mayor valor DAO
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public static com.bachue.snr.prosnr01.dao.acc.RegistroMayorValorDAO getRegistroMayorValorDAO(
	    DAOManager adm_manager
	)
	    throws B2BException
	{
		return (com.bachue.snr.prosnr01.dao.acc.RegistroMayorValorDAO)adm_manager.getDAO(
		    com.bachue.snr.prosnr01.dao.acc.RegistroMayorValorDAO.class
		);
	}

	/**
	 * Retorna el valor de registro pago DAO.
	 *
	 * @param adm_manager correspondiente al valor del tipo de objeto DAOManager
	 * @return el valor de registro pago DAO
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public static RegistroPagoDAO getRegistroPagoDAO(DAOManager adm_manager)
	    throws B2BException
	{
		return (RegistroPagoDAO)adm_manager.getDAO(RegistroPagoDAO.class);
	}

	/**
	 * Retorna el valor de RegistroPagoExceso DAO.
	 *
	 * @param adm_manager correspondiente al valor del tipo de objeto DAOManager
	 * @return el valor de RegistroPagoExceso DAO
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public static RegistroPagoExcesoDAO getRegistroPagoExcesoDAO(DAOManager adm_manager)
	    throws B2BException
	{
		return (RegistroPagoExcesoDAO)adm_manager.getDAO(RegistroPagoExcesoDAO.class);
	}

	/**
	 *  Retorna el valor de regla negocio DAO.
	 *
	 * @param adm_manager de adm manager
	 * @return el valor de regla negocio DAO
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public static ReglaNegocioDAO getReglaNegocioDAO(DAOManager adm_manager)
	    throws B2BException
	{
		return (ReglaNegocioDAO)adm_manager.getDAO(ReglaNegocioDAO.class);
	}

	/**
	 * Retorna el valor de reimpresion documentos DAO.
	 *
	 * @param adm_manager correspondiente al valor del tipo de objeto DAOManager
	 * @return el valor de reimpresion documentos DAO
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public static ReimpresionDocumentosDAO getReimpresionDocumentosDAO(DAOManager adm_manager)
	    throws B2BException
	{
		return (ReimpresionDocumentosDAO)adm_manager.getDAO(ReimpresionDocumentosDAO.class);
	}

	/**
	 * Retorna el valor de reintentos DAO.
	 *
	 * @param adm_manager correspondiente al valor del tipo de objeto DAOManager
	 * @return el valor de reintentos DAO
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public static ReintentosDAO getReintentosDAO(DAOManager adm_manager)
	    throws B2BException
	{
		return (ReintentosDAO)adm_manager.getDAO(ReintentosDAO.class);
	}

	/**
	 * Retorna el valor de relacion aprobacion DAO.
	 *
	 * @param adm_manager correspondiente al valor del tipo de objeto DAOManager
	 * @return el valor de relacion aprobacion DAO
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public static RelacionAprobacionDAO getRelacionAprobacionDAO(DAOManager adm_manager)
	    throws B2BException
	{
		return (RelacionAprobacionDAO)adm_manager.getDAO(RelacionAprobacionDAO.class);
	}

	/**
	 * Retorna el valor de reportes DAO.
	 *
	 * @param adm_manager correspondiente al valor del tipo de objeto DAOManager
	 * @return el valor de reportes DAO
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public static ReportesDAO getReportesDAO(DAOManager adm_manager)
	    throws B2BException
	{
		return (ReportesDAO)adm_manager.getDAO(ReportesDAO.class);
	}

	/**
	 * Retorna el valor de respuestas consulta DAO.
	 *
	 * @param adm_manager correspondiente al valor del tipo de objeto DAOManager
	 * @return el valor de respuestas consulta DAO
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public static RespuestasConsultaDAO getRespuestasConsultaDAO(DAOManager adm_manager)
	    throws B2BException
	{
		return (RespuestasConsultaDAO)adm_manager.getDAO(RespuestasConsultaDAO.class);
	}

	/**
	 * Retorna el valor de resultado consulta DAO.
	 *
	 * @param adm_manager correspondiente al valor del tipo de objeto DAOManager
	 * @return el valor de resultado consulta DAO
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public static ResultadoConsultaDAO getResultadoConsultaDAO(DAOManager adm_manager)
	    throws B2BException
	{
		return (ResultadoConsultaDAO)adm_manager.getDAO(ResultadoConsultaDAO.class);
	}

	/**
	 * Retorna el valor de rol DAO.
	 *
	 * @param adm_manager correspondiente al valor del tipo de objeto DAOManager
	 * @return el valor de rol DAO
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public static RolDAO getRolDAO(DAOManager adm_manager)
	    throws B2BException
	{
		return (RolDAO)adm_manager.getDAO(RolDAO.class);
	}

	/**
	 * Retorna el valor de Rol opción DAO.
	 *
	 * @param adm_manager correspondiente al valor del tipo de objeto DAOManager
	 * @return el valor de salario rol opción DAO
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public static RolOpcionDAO getRolOpcionDAO(DAOManager adm_manager)
	    throws B2BException
	{
		return (RolOpcionDAO)adm_manager.getDAO(RolOpcionDAO.class);
	}

	/**
	 * Retorna el valor de rubro DAO.
	 *
	 * @param adm_manager correspondiente al valor del tipo de objeto DAOManager
	 * @return el valor de tipo acto prohibición DAO
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public static RubroDAO getRubroDAO(DAOManager adm_manager)
	    throws B2BException
	{
		return (RubroDAO)adm_manager.getDAO(RubroDAO.class);
	}

	/**
	 * Retorna el valor de rubro homologacion  DAO.
	 *
	 * @param adm_manager correspondiente al valor del tipo de objeto DAOManager
	 * @return el valor de tipo acto prohibición DAO
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public static RubroHomologacionDAO getRubroHomologacionDAO(DAOManager adm_manager)
	    throws B2BException
	{
		return (RubroHomologacionDAO)adm_manager.getDAO(RubroHomologacionDAO.class);
	}

	/**
	 * Gets the SIIF catalogo DAO.
	 *
	 * @param adm_manager the adm manager
	 * @return the SIIF catalogo DAO
	 * @throws B2BException the b 2 B exception
	 */
	public static SIIFCatalogoDAO getSIIFCatalogoDAO(DAOManager adm_manager)
	    throws B2BException
	{
		return (SIIFCatalogoDAO)adm_manager.getDAO(SIIFCatalogoDAO.class);
	}

	/**
	 * Retorna el valor de salario minimo DAO.
	 *
	 * @param adm_manager correspondiente al valor del tipo de objeto DAOManager
	 * @return el valor de salario minimo DAO
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public static SalarioMinimoDAO getSalarioMinimoDAO(DAOManager adm_manager)
	    throws B2BException
	{
		return (SalarioMinimoDAO)adm_manager.getDAO(SalarioMinimoDAO.class);
	}

	/**
	 * Retorna el valor de salvedad anotacion DAO.
	 *
	 * @param adm_manager correspondiente al valor del tipo de objeto DAOManager
	 * @return el valor de salvedad anotacion DAO
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public static SalvedadAnotacionDAO getSalvedadAnotacionDAO(DAOManager adm_manager)
	    throws B2BException
	{
		return (SalvedadAnotacionDAO)adm_manager.getDAO(SalvedadAnotacionDAO.class);
	}

	/**
	 * Retorna el valor de salvedad predio DAO.
	 *
	 * @param adm_manager correspondiente al valor del tipo de objeto DAOManager
	 * @return el valor de salvedad predio DAO
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public static SalvedadPredioDAO getSalvedadPredioDAO(DAOManager adm_manager)
	    throws B2BException
	{
		return (SalvedadPredioDAO)adm_manager.getDAO(SalvedadPredioDAO.class);
	}

	/**
	 * Retorna el valor de salvedades DAO.
	 *
	 * @param adm_manager correspondiente al valor del tipo de objeto DAOManager
	 * @return el valor de salvedades DAO
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public static SalvedadesDAO getSalvedadesDAO(DAOManager adm_manager)
	    throws B2BException
	{
		return (SalvedadesDAO)adm_manager.getDAO(SalvedadesDAO.class);
	}

	/**
	 * Retorna el valor del objeto servicio actualizacion desde catastros DAO.
	 *
	 * @param adm_manager de adm manager
	 * @return de servicio actualizacion desde catastros DAO
	 * @throws B2BException de b 2 B exception
	 */
	public static ServicioActualizacionDesdeCatastrosDAO getServicioActualizacionDesdeCatastrosDAO(
	    DAOManager adm_manager
	)
	    throws B2BException
	{
		return (ServicioActualizacionDesdeCatastrosDAO)adm_manager.getDAO(ServicioActualizacionDesdeCatastrosDAO.class);
	}

	/**
	 * Gets de servicio actualizacion desde catastros detalle DAO.
	 *
	 * @param adm_manager de adm manager
	 * @return de servicio actualizacion desde catastros detalle DAO
	 * @throws B2BException de b 2 B exception
	 */
	public static ServicioActualizacionDesdeCatastrosDetalleDAO getServicioActualizacionDesdeCatastrosDetalleDAO(
	    DAOManager adm_manager
	)
	    throws B2BException
	{
		return (ServicioActualizacionDesdeCatastrosDetalleDAO)adm_manager.getDAO(
		    ServicioActualizacionDesdeCatastrosDetalleDAO.class
		);
	}

	/**
	 * Retorna Objeto o variable de valor servicio actualizacion hacia catastros DAO.
	 *
	 * @param adm_manager de adm manager
	 * @return el valor de servicio actualizacion hacia catastros DAO
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public static ServicioActualizacionHaciaCatastrosDAO getServicioActualizacionHaciaCatastrosDAO(
	    DAOManager adm_manager
	)
	    throws B2BException
	{
		return (ServicioActualizacionHaciaCatastrosDAO)adm_manager.getDAO(ServicioActualizacionHaciaCatastrosDAO.class);
	}

	/**
	 * Retorna Objeto o variable de valor servicio actualizacion hacia catastros detalle DAO.
	 *
	 * @param adm_manager de adm manager
	 * @return el valor de servicio actualizacion hacia catastros detalle DAO
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public static ServicioActualizacionHaciaCatastrosDetalleDAO getServicioActualizacionHaciaCatastrosDetalleDAO(
	    DAOManager adm_manager
	)
	    throws B2BException
	{
		return (ServicioActualizacionHaciaCatastrosDetalleDAO)adm_manager.getDAO(
		    ServicioActualizacionHaciaCatastrosDetalleDAO.class
		);
	}

	/**
	 * Retorna Objeto o variable de valor solicitud apoyo nacional DAO.
	 *
	 * @param adm_manager de adm manager
	 * @return el valor de solicitud apoyo nacional DAO
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public static SolicitudApoyoNacionalDAO getSolicitudApoyoNacionalDAO(DAOManager adm_manager)
	    throws B2BException
	{
		return (SolicitudApoyoNacionalDAO)adm_manager.getDAO(SolicitudApoyoNacionalDAO.class);
	}

	/**
	 * Retorna Objeto o variable de valor solicitud apoyo regional orip DAO.
	 *
	 * @param adm_manager de adm manager
	 * @return el valor de solicitud apoyo regional orip DAO
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public static SolicitudApoyoRegionalOripDAO getSolicitudApoyoRegionalOripDAO(DAOManager adm_manager)
	    throws B2BException
	{
		return (SolicitudApoyoRegionalOripDAO)adm_manager.getDAO(SolicitudApoyoRegionalOripDAO.class);
	}

	/**
	 * Retorna el valor de solicitud asociada DAO.
	 *
	 * @param adm_manager correspondiente al valor del tipo de objeto DAOManager
	 * @return el valor de solicitud asociada DAO
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public static SolicitudAsociadaDAO getSolicitudAsociadaDAO(DAOManager adm_manager)
	    throws B2BException
	{
		return (SolicitudAsociadaDAO)adm_manager.getDAO(SolicitudAsociadaDAO.class);
	}

	/**
	 * Retorna el valor de solicitud campos correccion DAO.
	 *
	 * @param adm_manager correspondiente al valor del tipo de objeto DAOManager
	 * @return el valor de solicitud campos correccion DAO
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public static SolicitudCamposCorreccionDAO getSolicitudCamposCorreccionDAO(DAOManager adm_manager)
	    throws B2BException
	{
		return (SolicitudCamposCorreccionDAO)adm_manager.getDAO(SolicitudCamposCorreccionDAO.class);
	}

	/**
	 * Retorna el valor de acc area solicitud copias DAO.
	 *
	 * @param adm_manager correspondiente al valor del tipo de objeto DAOManager
	 * @return el valor de acc solicitud copias DAO
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public static SolicitudCopiasDAO getSolicitudCopiasDAO(DAOManager adm_manager)
	    throws B2BException
	{
		return (SolicitudCopiasDAO)adm_manager.getDAO(SolicitudCopiasDAO.class);
	}

	/**
	 * Retorna Objeto o variable de valor solicitud correccion BNGDAO.
	 *
	 * @param adm_manager de adm manager
	 * @return el valor de solicitud correccion BNGDAO
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public static com.bachue.snr.prosnr01.dao.bng.SolicitudCorreccionDAO getSolicitudCorreccionBNGDAO(
	    DAOManager adm_manager
	)
	    throws B2BException
	{
		return (com.bachue.snr.prosnr01.dao.bng.SolicitudCorreccionDAO)adm_manager.getDAO(
		    com.bachue.snr.prosnr01.dao.bng.SolicitudCorreccionDAO.class
		);
	}

	/**
	 * Retorna el valor de solicitud correccion DAO.
	 *
	 * @param adm_manager correspondiente al valor del tipo de objeto DAOManager
	 * @return el valor de solicitud correccion DAO
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public static SolicitudCorreccionDAO getSolicitudCorreccionDAO(DAOManager adm_manager)
	    throws B2BException
	{
		return (SolicitudCorreccionDAO)adm_manager.getDAO(SolicitudCorreccionDAO.class);
	}

	/**
	 * Retorna el valor de solicitud DAO.
	 *
	 * @param adm_manager correspondiente al valor del tipo de objeto DAOManager
	 * @return el valor de solicitud DAO
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public static SolicitudDAO getSolicitudDAO(DAOManager adm_manager)
	    throws B2BException
	{
		return (SolicitudDAO)adm_manager.getDAO(SolicitudDAO.class);
	}

	/**
	 * Retorna el valor de solicitud direccion certificado DAO.
	 *
	 * @param adm_manager correspondiente al valor del tipo de objeto DAOManager
	 * @return el valor de solicitud direccion certificado DAO
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public static SolicitudDireccionCertificadoDAO getSolicitudDireccionCertificadoDAO(DAOManager adm_manager)
	    throws B2BException
	{
		return (SolicitudDireccionCertificadoDAO)adm_manager.getDAO(SolicitudDireccionCertificadoDAO.class);
	}

	/**
	 * Retorna el valor de solicitud interviniente DAO.
	 *
	 * @param adm_manager correspondiente al valor del tipo de objeto DAOManager
	 * @return el valor de solicitud interviniente DAO
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public static SolicitudIntervinienteDAO getSolicitudIntervinienteDAO(DAOManager adm_manager)
	    throws B2BException
	{
		return (SolicitudIntervinienteDAO)adm_manager.getDAO(SolicitudIntervinienteDAO.class);
	}

	/**
	 * Retorna el valor de solicitud matricula acto DAO.
	 *
	 * @param adm_manager correspondiente al valor del tipo de objeto DAOManager
	 * @return el valor de solicitud matricula acto DAO
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public static SolicitudMatriculaActoDAO getSolicitudMatriculaActoDAO(DAOManager adm_manager)
	    throws B2BException
	{
		return (SolicitudMatriculaActoDAO)adm_manager.getDAO(SolicitudMatriculaActoDAO.class);
	}

	/**
	 * Retorna el valor de solicitud matricula DAO.
	 *
	 * @param adm_manager correspondiente al valor del tipo de objeto DAOManager
	 * @return el valor de solicitud matricula DAO
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public static SolicitudMatriculaDAO getSolicitudMatriculaDAO(DAOManager adm_manager)
	    throws B2BException
	{
		return (SolicitudMatriculaDAO)adm_manager.getDAO(SolicitudMatriculaDAO.class);
	}

	/**
	 * Retorna el valor de consulta solicitud Visitas DAO.
	 *
	 * @param adm_manager correspondiente al valor del tipo de objeto DAOManager
	 * @return el valor de consulta solicitud Visitas DAO
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public static SolicitudVisitasDAO getSolicitudVisitasDAO(DAOManager adm_manager)
	    throws B2BException
	{
		return (SolicitudVisitasDAO)adm_manager.getDAO(SolicitudVisitasDAO.class);
	}

	/**
	 * Retorna el valor de soporte traslado DAO.
	 *
	 * @param adm_manager correspondiente al valor del tipo de objeto DAOManager
	 * @return el valor de soporte traslado DAO
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public static SoporteTrasladoDAO getSoporteTrasladoDAO(DAOManager adm_manager)
	    throws B2BException
	{
		return (SoporteTrasladoDAO)adm_manager.getDAO(SoporteTrasladoDAO.class);
	}

	/**
	 * Retorna el valor de Soporte Traslado Matricula DAO.
	 *
	 * @param adm_manager correspondiente al valor del tipo de objeto DAOManager
	 * @return el valor de Soporte Traslado Matricula DAO
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public static SoporteTrasladoMatriculaDAO getSoporteTrasladoMatriculaDAO(DAOManager adm_manager)
	    throws B2BException
	{
		return (SoporteTrasladoMatriculaDAO)adm_manager.getDAO(SoporteTrasladoMatriculaDAO.class);
	}

	/**
	 * Retorna el valor de subproceso DAO.
	 *
	 * @param adm_manager correspondiente al valor del tipo de objeto DAOManager
	 * @return el valor de subproceso DAO
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public static SubprocesoDAO getSubprocesoDAO(DAOManager adm_manager)
	    throws B2BException
	{
		return (SubprocesoDAO)adm_manager.getDAO(SubprocesoDAO.class);
	}

	/**
	 * Retorna el valor de subproceso trabajo DAO.
	 *
	 * @param adm_manager correspondiente al valor del tipo de objeto DAOManager
	 * @return el valor de subproceso trabajoDAO
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public static SubProcesoTrabajoDAO getSubprocesoTrabajoDAO(DAOManager adm_manager)
	    throws B2BException
	{
		return (SubProcesoTrabajoDAO)adm_manager.getDAO(SubProcesoTrabajoDAO.class);
	}

	/**
	 * Retorna el valor de subproceso version DAO.
	 *
	 * @param adm_manager correspondiente al valor del tipo de objeto DAOManager
	 * @return el valor de subproceso DAO
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public static SubprocesoVersionDAO getSubprocesoVersionDAO(DAOManager adm_manager)
	    throws B2BException
	{
		return (SubprocesoVersionDAO)adm_manager.getDAO(SubprocesoVersionDAO.class);
	}

	/**
	 * Retorna el valor de subproceso version trabajo DAO.
	 *
	 * @param adm_manager correspondiente al valor del tipo de objeto DAOManager
	 * @return el valor de subproceso version trabajoDAO
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public static SubProcesoVersionTrabajoDAO getSubprocesoVersionTrabajoDAO(DAOManager adm_manager)
	    throws B2BException
	{
		return (SubProcesoVersionTrabajoDAO)adm_manager.getDAO(SubProcesoVersionTrabajoDAO.class);
	}

	/**
	 * Retorna el valor de sucursal canal origen servicio DAO.
	 *
	 * @param adm_manager correspondiente al valor del tipo de objeto DAOManager
	 * @return el valor de sucursal canal origen servicio DAO
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public static SucursalCanalOrigenServicioDAO getSucursalCanalOrigenServicioDAO(DAOManager adm_manager)
	    throws B2BException
	{
		return (SucursalCanalOrigenServicioDAO)adm_manager.getDAO(SucursalCanalOrigenServicioDAO.class);
	}

	/**
	 * Retorna la instancia del DAO tag plantilla resolucion DAO.
	 *
	 * @param adm_manager Argumento de tipo <code>DAOManager</code> que permite realizar la conexión a la base de datos.
	 * @return Instancia del DAO tag plantilla resolucion DAO.
	 * @throws B2BException Señala que se ha genera una excepción
	 */
	public static TagPlantillaResolucionDAO getTagPlantillaResolucionDAO(DAOManager adm_manager)
	    throws B2BException
	{
		return (TagPlantillaResolucionDAO)adm_manager.getDAO(TagPlantillaResolucionDAO.class);
	}

	/**
	 * Retorna el valor de tarifa alerta DAO.
	 *
	 * @param adm_manager correspondiente al valor del tipo de objeto DAOManager
	 * @return el valor de tarifa alerta DAO
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public static TarifaAlertaDAO getTarifaAlertaDAO(DAOManager adm_manager)
	    throws B2BException
	{
		return (TarifaAlertaDAO)adm_manager.getDAO(TarifaAlertaDAO.class);
	}

	/**
	 * Retorna el valor de tarifa fija DAO.
	 *
	 * @param adm_manager correspondiente al valor del tipo de objeto DAOManager
	 * @return el valor de tarifa fija DAO
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public static TarifaFijaDAO getTarifaFijaDAO(DAOManager adm_manager)
	    throws B2BException
	{
		return (TarifaFijaDAO)adm_manager.getDAO(TarifaFijaDAO.class);
	}

	/**
	 * Retorna el valor de testamento DAO.
	 *
	 * @param adm_manager correspondiente al valor del tipo de objeto DAOManager
	 * @return el valor de tipo testamento DAO
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public static TestamentoDAO getTestamentoDAO(DAOManager adm_manager)
	    throws B2BException
	{
		return (TestamentoDAO)adm_manager.getDAO(TestamentoDAO.class);
	}

	/**
	 * Retorna el valor de tipo acto condicion DAO.
	 *
	 * @param adm_manager correspondiente al valor del tipo de objeto DAOManager
	 * @return el valor de tipo acto condicion DAO
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public static TipoActoCondicionDAO getTipoActoCondicionDAO(DAOManager adm_manager)
	    throws B2BException
	{
		return (TipoActoCondicionDAO)adm_manager.getDAO(TipoActoCondicionDAO.class);
	}

	/**
	 * Retorna el valor de tipo acto DAO.
	 *
	 * @param adm_manager correspondiente al valor del tipo de objeto DAOManager
	 * @return el valor de tipo acto DAO
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public static TipoActoDAO getTipoActoDAO(DAOManager adm_manager)
	    throws B2BException
	{
		return (TipoActoDAO)adm_manager.getDAO(TipoActoDAO.class);
	}

	/**
	 * Retorna el valor de tipo acto ejecutoria DAO.
	 *
	 * @param adm_manager correspondiente al valor del tipo de objeto DAOManager
	 * @return el valor de tipo acto ejecutoria DAO
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public static TipoActoEjecutoriaDAO getTipoActoEjecutoriaDAO(DAOManager adm_manager)
	    throws B2BException
	{
		return (TipoActoEjecutoriaDAO)adm_manager.getDAO(TipoActoEjecutoriaDAO.class);
	}

	/**
	 * Retorna el valor de tipo acto prohibición DAO.
	 *
	 * @param adm_manager correspondiente al valor del tipo de objeto DAOManager
	 * @return el valor de tipo acto prohibición DAO
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public static TipoActoProhibicionDAO getTipoActoProhibicionDAO(DAOManager adm_manager)
	    throws B2BException
	{
		return (TipoActoProhibicionDAO)adm_manager.getDAO(TipoActoProhibicionDAO.class);
	}

	/**
	 * Retorna el valor de tipo adquisicion DAO.
	 *
	 * @param adm_manager correspondiente al valor del tipo de objeto DAOManager
	 * @return el valor de tipo adquisicion DAO
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public static TipoAdquisicionDAO getTipoAdquisicionDAO(DAOManager adm_manager)
	    throws B2BException
	{
		return (TipoAdquisicionDAO)adm_manager.getDAO(TipoAdquisicionDAO.class);
	}

	/**
	 * Retorna el valor de tipo anotacion DAO.
	 *
	 * @param adm_manager correspondiente al valor del tipo de objeto DAOManager
	 * @return el valor de tipo anotacion DAO
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public static TipoAnotacionDAO getTipoAnotacionDAO(DAOManager adm_manager)
	    throws B2BException
	{
		return (TipoAnotacionDAO)adm_manager.getDAO(TipoAnotacionDAO.class);
	}

	/**
	 * Retorna el valor de tipo area DAO.
	 *
	 * @param adm_manager correspondiente al valor del tipo de objeto DAOManager
	 * @return el valor de tipo area DAO
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public static TipoAreaDAO getTipoAreaDAO(DAOManager adm_manager)
	    throws B2BException
	{
		return (TipoAreaDAO)adm_manager.getDAO(TipoAreaDAO.class);
	}

	/**
	 * Retorna el valor de tipo canal origen DAO.
	 *
	 * @param adm_manager correspondiente al valor del tipo de objeto DAOManager
	 * @return el valor de tipo canal origen DAO
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public static TipoCanalOrigenDAO getTipoCanalOrigenDAO(DAOManager adm_manager)
	    throws B2BException
	{
		return (TipoCanalOrigenDAO)adm_manager.getDAO(TipoCanalOrigenDAO.class);
	}

	/**
	 * Retorna el valor de tipo causal DAO.
	 *
	 * @param adm_manager correspondiente al valor del tipo de objeto DAOManager
	 * @return el valor de tipo causal DAO
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public static TipoCausalDAO getTipoCausalDAO(DAOManager adm_manager)
	    throws B2BException
	{
		return (TipoCausalDAO)adm_manager.getDAO(TipoCausalDAO.class);
	}

	/**
	 * Retorna el valor de tipo criterio busqueda DAO.
	 *
	 * @param adm_manager correspondiente al valor del tipo de objeto DAOManager
	 * @return el valor de tipo criterio busqueda DAO
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public static TipoCriterioBusquedaDAO getTipoCriterioBusquedaDAO(DAOManager adm_manager)
	    throws B2BException
	{
		return (TipoCriterioBusquedaDAO)adm_manager.getDAO(TipoCriterioBusquedaDAO.class);
	}

	/**
	 * Retorna el valor de tipo criterio busqueda pgn DAO.
	 *
	 * @param adm_manager correspondiente al valor del tipo de objeto DAOManager
	 * @return el valor de tipo criterio busqueda pgn DAO
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public static TipoCriterioBusquedaPGNDAO getTipoCriterioBusquedaPGNDAO(DAOManager adm_manager)
	    throws B2BException
	{
		return (TipoCriterioBusquedaPGNDAO)adm_manager.getDAO(TipoCriterioBusquedaPGNDAO.class);
	}

	/**
	 * Retorna el valor de tipo criterio DAO.
	 *
	 * @param adm_manager correspondiente al valor del tipo de objeto DAOManager
	 * @return el valor de tipo criterio DAO
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public static TipoCriterioDAO getTipoCriterioDAO(DAOManager adm_manager)
	    throws B2BException
	{
		return (TipoCriterioDAO)adm_manager.getDAO(TipoCriterioDAO.class);
	}

	/**
	 * Retorna el valor de tipo decision recurso DAO.
	 *
	 * @param adm_manager correspondiente al valor del tipo de objeto DAOManager
	 * @return el valor de tipo decision recurso DAO
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public static TipoDecisionRecursoDAO getTipoDecisionRecursoDAO(DAOManager adm_manager)
	    throws B2BException
	{
		return (TipoDecisionRecursoDAO)adm_manager.getDAO(TipoDecisionRecursoDAO.class);
	}

	/**
	 * Retorna el valor de tipo documental acto DAO.
	 *
	 * @param adm_manager correspondiente al valor del tipo de objeto DAOManager
	 * @return el valor de tipo documental acto DAO
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public static TipoDocumentalActoDAO getTipoDocumentalActoDAO(DAOManager adm_manager)
	    throws B2BException
	{
		return (TipoDocumentalActoDAO)adm_manager.getDAO(TipoDocumentalActoDAO.class);
	}

	/**
	 * Retorna el valor de tipo documental DAO.
	 *
	 * @param adm_manager correspondiente al valor del tipo de objeto DAOManager
	 * @return el valor de tipo documental DAO
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public static TipoDocumentalDAO getTipoDocumentalDAO(DAOManager adm_manager)
	    throws B2BException
	{
		return (TipoDocumentalDAO)adm_manager.getDAO(TipoDocumentalDAO.class);
	}

	/**
	 * Retorna el valor de tipo documento publico DAO.
	 *
	 * @param adm_manager correspondiente al valor del tipo de objeto DAOManager
	 * @return el valor de tipo documento publico DAO
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public static TipoDocumentoPublicoDAO getTipoDocumentoPublicoDAO(DAOManager adm_manager)
	    throws B2BException
	{
		return (TipoDocumentoPublicoDAO)adm_manager.getDAO(TipoDocumentoPublicoDAO.class);
	}

	/**
	 * Retorna el valor de tipo eje DAO.
	 *
	 * @param adm_manager correspondiente al valor del tipo de objeto DAOManager
	 * @return el valor de tipo eje DAO
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public static TipoEjeDAO getTipoEjeDAO(DAOManager adm_manager)
	    throws B2BException
	{
		return (TipoEjeDAO)adm_manager.getDAO(TipoEjeDAO.class);
	}

	/**
	 * Retorna el valor de tipo entidad DAO.
	 *
	 * @param adm_manager correspondiente al valor del tipo de objeto DAOManager
	 * @return el valor de tipo entidad DAO
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public static TipoEntidadDAO getTipoEntidadDAO(DAOManager adm_manager)
	    throws B2BException
	{
		return (TipoEntidadDAO)adm_manager.getDAO(TipoEntidadDAO.class);
	}

	/**
	 * Retorna el valor de tipo estado liquidacion DAO.
	 *
	 * @param adm_manager correspondiente al valor del tipo de objeto DAOManager
	 * @return el valor de tipo estado liquidacion DAO
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public static TipoEstadoLiquidacionDAO getTipoEstadoLiquidacionDAO(DAOManager adm_manager)
	    throws B2BException
	{
		return (TipoEstadoLiquidacionDAO)adm_manager.getDAO(TipoEstadoLiquidacionDAO.class);
	}

	/**
	 * Retorna el valor de tipo estado solicitud DAO.
	 *
	 * @param adm_manager correspondiente al valor del tipo de objeto DAOManager
	 * @return el valor de tipo estado solicitud DAO
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public static TipoEstadoSolicitudDAO getTipoEstadoSolicitudDAO(DAOManager adm_manager)
	    throws B2BException
	{
		return (TipoEstadoSolicitudDAO)adm_manager.getDAO(TipoEstadoSolicitudDAO.class);
	}

	/**
	 * Retorna el valor de tipo integracion gobernacion DAO.
	 *
	 * @param adm_manager correspondiente al valor del tipo de objeto DAOManager
	 * @return el valor de tipo integracion gobernacion DAO
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public static TipoIntegracionGobernacionDAO getTipoIntegracionGobernacionDAO(DAOManager adm_manager)
	    throws B2BException
	{
		return (TipoIntegracionGobernacionDAO)adm_manager.getDAO(TipoIntegracionGobernacionDAO.class);
	}

	/**
	 * Retorna el valor de tipo notificacion DAO.
	 *
	 * @param adm_manager correspondiente al valor del tipo de objeto DAOManager
	 * @return el valor de tipo notificacion DAO
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public static TipoNotificacionDAO getTipoNotificacionDAO(DAOManager adm_manager)
	    throws B2BException
	{
		return (TipoNotificacionDAO)adm_manager.getDAO(TipoNotificacionDAO.class);
	}

	/**
	 * Retorna el valor de tipo oficina DAO.
	 *
	 * @param adm_manager correspondiente al valor del tipo de objeto DAOManager
	 * @return el valor de tipo oficina DAO
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public static TipoOficinaDAO getTipoOficinaDAO(DAOManager adm_manager)
	    throws B2BException
	{
		return (TipoOficinaDAO)adm_manager.getDAO(TipoOficinaDAO.class);
	}

	/**
	 * Retorna Objeto o variable de valor tipo operacion DAO.
	 *
	 * @param adm_manager de adm manager
	 * @return el valor de tipo operacion DAO
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public static TipoOperacionDAO getTipoOperacionDAO(DAOManager adm_manager)
	    throws B2BException
	{
		return (TipoOperacionDAO)adm_manager.getDAO(TipoOperacionDAO.class);
	}

	/**
	 * Retorna el valor de tipo pago dao.
	 *
	 * @param adm_manager correspondiente al valor del tipo de objeto DAOManager
	 * @return el valor de tipo pago dao
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public static TipoPagoDAO getTipoPagoDao(DAOManager adm_manager)
	    throws B2BException
	{
		return (TipoPagoDAO)adm_manager.getDAO(TipoPagoDAO.class);
	}

	/**
	 * Retorna el valor de tipo persona DAO.
	 *
	 * @param adm_manager correspondiente al valor del tipo de objeto DAOManager
	 * @return el valor de tipo persona DAO
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public static TipoPersonaDAO getTipoPersonaDAO(DAOManager adm_manager)
	    throws B2BException
	{
		return (TipoPersonaDAO)adm_manager.getDAO(TipoPersonaDAO.class);
	}

	/**
	 * Retorna el valor de tipo recaudo DAO.
	 *
	 * @param adm_manager correspondiente al valor del tipo de objeto DAOManager
	 * @return el valor de tipo recaudo DAO
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public static TipoRecaudoDAO getTipoRecaudoDAO(DAOManager adm_manager)
	    throws B2BException
	{
		return (TipoRecaudoDAO)adm_manager.getDAO(TipoRecaudoDAO.class);
	}

	/**
	 * Retorna el valor de tipo recepcion DAO.
	 *
	 * @param adm_manager correspondiente al valor del tipo de objeto DAOManager
	 * @return el valor de tipo recepcion DAO
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public static TipoRecepcionDAO getTipoRecepcionDAO(DAOManager adm_manager)
	    throws B2BException
	{
		return (TipoRecepcionDAO)adm_manager.getDAO(TipoRecepcionDAO.class);
	}

	/**
	 * Retorna el valor de tipo recurso DAO.
	 *
	 * @param adm_manager correspondiente al valor del tipo de objeto DAOManager
	 * @return el valor de tipo recurso DAO
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public static TipoRecursoDAO getTipoRecursoDAO(DAOManager adm_manager)
	    throws B2BException
	{
		return (TipoRecursoDAO)adm_manager.getDAO(TipoRecursoDAO.class);
	}

	/**
	 * Retorna el valor de tipo requiere matricula DAO.
	 *
	 * @param adm_manager correspondiente al valor del tipo de objeto DAOManager
	 * @return el valor de tipo requiere matricula DAO
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public static TipoRequiereMatriculaDAO getTipoRequiereMatriculaDAO(DAOManager adm_manager)
	    throws B2BException
	{
		return (TipoRequiereMatriculaDAO)adm_manager.getDAO(TipoRequiereMatriculaDAO.class);
	}

	/**
	 * Retorna el valor de tipo rrr DAO.
	 *
	 * @param adm_manager correspondiente al valor del tipo de objeto DAOManager
	 * @return el valor de tipo rrr DAO
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public static TipoRrrDAO getTipoRrrDAO(DAOManager adm_manager)
	    throws B2BException
	{
		return (TipoRrrDAO)adm_manager.getDAO(TipoRrrDAO.class);
	}

	/**
	 * Retorna el valor de tipo servicio DAO.
	 *
	 * @param adm_manager correspondiente al valor del tipo de objeto DAOManager
	 * @return el valor de tipo servicio DAO
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public static TipoServicioDAO getTipoServicioDAO(DAOManager adm_manager)
	    throws B2BException
	{
		return (TipoServicioDAO)adm_manager.getDAO(TipoServicioDAO.class);
	}

	/**
	 * Retorna el valor de tipo tarifa registral dao.
	 *
	 * @param adm_manager correspondiente al valor del tipo de objeto DAOManager
	 * @return el valor de tipo tarifa registral dao
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public static TipoTarifaRegistralDAO getTipoTarifaRegistralDao(DAOManager adm_manager)
	    throws B2BException
	{
		return (TipoTarifaRegistralDAO)adm_manager.getDAO(TipoTarifaRegistralDAO.class);
	}

	/**
	 * Retorna el valor de tipo tarjeta apoderado DAO.
	 *
	 * @param adm_manager correspondiente al valor del tipo de objeto DAOManager
	 * @return el valor de tipo tarjeta apoderado DAO
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public static TipoTarjetaApoderadoDAO getTipoTarjetaApoderadoDAO(DAOManager adm_manager)
	    throws B2BException
	{
		return (TipoTarjetaApoderadoDAO)adm_manager.getDAO(TipoTarjetaApoderadoDAO.class);
	}

	/**
	 * Retorna el valor de tipo testamento DAO.
	 *
	 * @param adm_manager correspondiente al valor del tipo de objeto DAOManager
	 * @return el valor de tipo testamento DAO
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public static TipoTestamentoDAO getTipoTestamentoDAO(DAOManager adm_manager)
	    throws B2BException
	{
		return (TipoTestamentoDAO)adm_manager.getDAO(TipoTestamentoDAO.class);
	}

	/**
	 * Retorna el valor de tipo uso suelo DAO.
	 *
	 * @param adm_manager correspondiente al valor del tipo de objeto DAOManager
	 * @return el valor de tipo uso suelo DAO
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public static TipoUsoSueloDAO getTipoUsoSueloDAO(DAOManager adm_manager)
	    throws B2BException
	{
		return (TipoUsoSueloDAO)adm_manager.getDAO(TipoUsoSueloDAO.class);
	}

	/**
	 * Retorna el valor de tmp solicitud matricula acto DAO.
	 *
	 * @param adm_manager correspondiente al valor del tipo de objeto DAOManager
	 * @return el valor de tmp solicitud matricula acto DAO
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public static TmpSolicitudMatriculaActoDAO getTmpSolicitudMatriculaActoDAO(DAOManager adm_manager)
	    throws B2BException
	{
		return (TmpSolicitudMatriculaActoDAO)adm_manager.getDAO(TmpSolicitudMatriculaActoDAO.class);
	}

	/**
	 * Retorna el valor de tmp solicitud matricula DAO.
	 *
	 * @param adm_manager correspondiente al valor del tipo de objeto DAOManager
	 * @return el valor de tmp solicitud matricula DAO
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public static TmpSolicitudMatriculaDAO getTmpSolicitudMatriculaDAO(DAOManager adm_manager)
	    throws B2BException
	{
		return (TmpSolicitudMatriculaDAO)adm_manager.getDAO(TmpSolicitudMatriculaDAO.class);
	}

	/**
	 *  Retorna el valor de topología regla DAO.
	 *
	 * @param adm_manager de adm manager
	 * @return el valor de topología regla DAO.
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public static TopologiaReglaDAO getTopologiaReglaDAO(DAOManager adm_manager)
	    throws B2BException
	{
		return (TopologiaReglaDAO)adm_manager.getDAO(TopologiaReglaDAO.class);
	}

	/**
	 * Retorna Objeto o variable de valor tramite visita DAO.
	 *
	 * @param adm_manager de adm manager
	 * @return el valor de tramite visita DAO
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public static TramiteVisitaDAO getTramiteVisitaDAO(DAOManager adm_manager)
	    throws B2BException
	{
		return (TramiteVisitaDAO)adm_manager.getDAO(TramiteVisitaDAO.class);
	}

	/**
	 * Retorna Objeto o variable de valor tramite visita rol DAO.
	 *
	 * @param adm_manager de adm manager
	 * @return el valor de tramite visita rol DAO
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public static TramiteVisitaRolDAO getTramiteVisitaRolDAO(DAOManager adm_manager)
	    throws B2BException
	{
		return (TramiteVisitaRolDAO)adm_manager.getDAO(TramiteVisitaRolDAO.class);
	}

	/**
	 * Retorna Objeto o variable de valor tramite visita tipo DAO.
	 *
	 * @param adm_manager de adm manager
	 * @return el valor de tramite visita tipo DAO
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public static TramiteVisitaTipoDAO getTramiteVisitaTipoDAO(DAOManager adm_manager)
	    throws B2BException
	{
		return (TramiteVisitaTipoDAO)adm_manager.getDAO(TramiteVisitaTipoDAO.class);
	}

	/**
	 * Retorna el valor de traslado matricula DAO.
	 *
	 * @param adm_manager correspondiente al valor del tipo de objeto DAOManager
	 * @return el valor de traslado matricula DAO
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public static TrasladoMatriculaDAO getTrasladoMatriculaDAO(DAOManager adm_manager)
	    throws B2BException
	{
		return (TrasladoMatriculaDAO)adm_manager.getDAO(TrasladoMatriculaDAO.class);
	}

	/**
	 * Retorna el valor de trazabilidad turno folio DAO.
	 *
	 * @param adm_manager correspondiente al valor del tipo de objeto DAOManager
	 * @return el valor de traslado matricula DAO
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public static TrazabilidadTurnoFolioDAO getTrazabilidadTurnoFolioDAO(DAOManager adm_manager)
	    throws B2BException
	{
		return (TrazabilidadTurnoFolioDAO)adm_manager.getDAO(TrazabilidadTurnoFolioDAO.class);
	}

	/**
	 * Retorna el valor de trazabilidad turno folio nota devolutiva DAO.
	 *
	 * @param adm_manager correspondiente al valor del tipo de objeto DAOManager
	 * @return el valor de traslado matricula DAO
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public static TrazabilidadTurnoFolioNotaDevolutivaDAO getTrazabilidadTurnoFolioNotaDevolutivaDAO(
	    DAOManager adm_manager
	)
	    throws B2BException
	{
		return (TrazabilidadTurnoFolioNotaDevolutivaDAO)adm_manager.getDAO(
		    TrazabilidadTurnoFolioNotaDevolutivaDAO.class
		);
	}

	/**
	 * Retorna el valor de trazabilidad turno sir DAO.
	 *
	 * @param adm_manager correspondiente al valor del tipo de objeto DAOManager
	 * @return el valor de traslado matricula DAO
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public static TrazabilidadTurnoSirDAO getTrazabilidadTurnoSirDAO(DAOManager adm_manager)
	    throws B2BException
	{
		return (TrazabilidadTurnoSirDAO)adm_manager.getDAO(TrazabilidadTurnoSirDAO.class);
	}

	/**
	 * Retorna el valor de trazabilidad turno sir nota devolutiva DAO.
	 *
	 * @param adm_manager correspondiente al valor del tipo de objeto DAOManager
	 * @return el valor de traslado matricula DAO
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public static TrazabilidadTurnoSirNotaDevolutivaDAO getTrazabilidadTurnoSirNotaDevolutivaDAO(
	    DAOManager adm_manager
	)
	    throws B2BException
	{
		return (TrazabilidadTurnoSirNotaDevolutivaDAO)adm_manager.getDAO(TrazabilidadTurnoSirNotaDevolutivaDAO.class);
	}

	/**
	 * Retorna el valor de turno DAO.
	 *
	 * @param adm_manager correspondiente al valor del tipo de objeto DAOManager
	 * @return el valor de turno DAO
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public static TurnoDAO getTurnoDAO(DAOManager adm_manager)
	    throws B2BException
	{
		return (TurnoDAO)adm_manager.getDAO(TurnoDAO.class);
	}

	/**
	 * Retorna el valor de turno derivado DAO.
	 *
	 * @param adm_manager correspondiente al valor del tipo de objeto DAOManager
	 * @return el valor de turno derivado DAO
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public static TurnoDerivadoDAO getTurnoDerivadoDAO(DAOManager adm_manager)
	    throws B2BException
	{
		return (TurnoDerivadoDAO)adm_manager.getDAO(TurnoDerivadoDAO.class);
	}

	/**
	 * Retorna el valor de turno historia DAO.
	 *
	 * @param adm_manager correspondiente al valor del tipo de objeto DAOManager
	 * @return el valor de turno historia DAO
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public static TurnoHistoriaDAO getTurnoHistoriaDAO(DAOManager adm_manager)
	    throws B2BException
	{
		return (TurnoHistoriaDAO)adm_manager.getDAO(TurnoHistoriaDAO.class);
	}

	/**
	 * Retorna Objeto o variable de valor ubicacion salvado DAO.
	 *
	 * @param adm_manager correspondiente al valor de adm manager
	 * @return el valor de ubicacion salvado DAO
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public static UbicacionSalvadoDAO getUbicacionSalvadoDAO(DAOManager adm_manager)
	    throws B2BException
	{
		return (UbicacionSalvadoDAO)adm_manager.getDAO(UbicacionSalvadoDAO.class);
	}

	/**
	 * Retorna el valor de unidad tiempo vencimiento DAO.
	 *
	 * @param adm_manager correspondiente al valor del tipo de objeto DAOManager
	 * @return el valor de unidad tiempo vencimiento DAO
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public static UnidadTiempoVencimientoDAO getUnidadTiempoVencimientoDAO(DAOManager adm_manager)
	    throws B2BException
	{
		return (UnidadTiempoVencimientoDAO)adm_manager.getDAO(UnidadTiempoVencimientoDAO.class);
	}

	/**
	 * Retorna el valor de User Objects DAO.
	 *
	 * @param adm_manager correspondiente al valor del tipo de objeto DAOManager
	 * @return el valor de User Objects DAO.
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public static UserObjectsDAO getUserObjectsDAO(DAOManager adm_manager)
	    throws B2BException
	{
		return (UserObjectsDAO)adm_manager.getDAO(UserObjectsDAO.class);
	}

	/**
	 * Retorna el valor de usuario circulo DAO.
	 *
	 * @param adm_manager correspondiente al valor del tipo de objeto DAOManager
	 * @return el valor de usuario circulo DAO
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public static UsuarioCirculoDAO getUsuarioCirculoDAO(DAOManager adm_manager)
	    throws B2BException
	{
		return (UsuarioCirculoDAO)adm_manager.getDAO(UsuarioCirculoDAO.class);
	}

	/**
	 * Retorna el valor de usuario cuenta cupo DAO.
	 *
	 * @param adm_manager correspondiente al valor del tipo de objeto DAOManager
	 * @return el valor de usuario cuenta cupo DAO
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public static UsuarioCuentaCupoDAO getUsuarioCuentaCupoDAO(DAOManager adm_manager)
	    throws B2BException
	{
		return (UsuarioCuentaCupoDAO)adm_manager.getDAO(UsuarioCuentaCupoDAO.class);
	}

	/**
	 * Retorna el valor de usuario DAO.
	 *
	 * @param adm_manager correspondiente al valor del tipo de objeto DAOManager
	 * @return el valor de usuario DAO
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public static UsuarioDAO getUsuarioDAO(DAOManager adm_manager)
	    throws B2BException
	{
		return (UsuarioDAO)adm_manager.getDAO(UsuarioDAO.class);
	}

	/**
	 * Retorna el valor de usuario etapa DAO.
	 *
	 * @param adm_manager correspondiente al valor del tipo de objeto DAOManager
	 * @return el valor de usuario etapa DAO
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public static UsuarioEtapaDAO getUsuarioEtapaDAO(DAOManager adm_manager)
	    throws B2BException
	{
		return (UsuarioEtapaDAO)adm_manager.getDAO(UsuarioEtapaDAO.class);
	}

	/**
	 * Retorna el valor de usuario linea DAO.
	 *
	 * @param adm_manager correspondiente al valor del tipo de objeto DAOManager
	 * @return el valor de usuario linea DAO
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public static UsuarioLineaDAO getUsuarioLineaDAO(DAOManager adm_manager)
	    throws B2BException
	{
		return (UsuarioLineaDAO)adm_manager.getDAO(UsuarioLineaDAO.class);
	}

	/**
	 * Retorna el valor de usuario rol DAO.
	 *
	 * @param adm_manager correspondiente al valor del tipo de objeto DAOManager
	 * @return el valor de usuario rol DAO
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public static UsuarioRolDAO getUsuarioRolDAO(DAOManager adm_manager)
	    throws B2BException
	{
		return (UsuarioRolDAO)adm_manager.getDAO(UsuarioRolDAO.class);
	}

	/**
	 * Retorna el valor de usuarios firma DAO.
	 *
	 * @param adm_manager correspondiente al valor del tipo de objeto DAOManager
	 * @return el valor de usuarios firma DAO
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public static UsuariosFirmaDAO getUsuariosFirmaDAO(DAOManager adm_manager)
	    throws B2BException
	{
		return (UsuariosFirmaDAO)adm_manager.getDAO(UsuariosFirmaDAO.class);
	}

	/**
	 * Retorna el valor de util DAO.
	 *
	 * @param adm_manager correspondiente al valor del tipo de objeto DAOManager
	 * @return el valor de util DAO
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public static UtilDAO getUtilDAO(DAOManager adm_manager)
	    throws B2BException
	{
		return (UtilDAO)adm_manager.getDAO(UtilDAO.class);
	}

	/**
	 * Retorna el valor de vereda DAO.
	 *
	 * @param adm_manager correspondiente al valor del tipo de objeto DAOManager
	 * @return el valor de vereda DAO
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public static VeredaDAO getVeredaDAO(DAOManager adm_manager)
	    throws B2BException
	{
		return (VeredaDAO)adm_manager.getDAO(VeredaDAO.class);
	}

	/**
	 * Retorna el valor de view data report DAO.
	 *
	 * @param adm_manager correspondiente al valor del tipo de objeto DAOManager
	 * @return el valor de view data report DAO
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public static ViewDataReportDAO getViewDataReportDAO(DAOManager adm_manager)
	    throws B2BException
	{
		return (ViewDataReportDAO)adm_manager.getDAO(ViewDataReportDAO.class);
	}

	/**
	 * Retorna el valor de zona registral DAO.
	 *
	 * @param adm_manager correspondiente al valor del tipo de objeto DAOManager
	 * @return el valor de zona registral DAO
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public static ZonaRegistralDAO getZonaRegistralDAO(DAOManager adm_manager)
	    throws B2BException
	{
		return (ZonaRegistralDAO)adm_manager.getDAO(ZonaRegistralDAO.class);
	}
}
