package com.bachue.snr.prosnr01.ejb.session.stateless.parameter;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.business.parameter.ParameterBusiness;

import com.bachue.snr.prosnr01.ejb.session.stateless.Logger;

import com.bachue.snr.prosnr01.model.numeracion.NumeracionOficiosCirculo;
import com.bachue.snr.prosnr01.model.registro.Archivo;
import com.bachue.snr.prosnr01.model.registro.TipoDocumentalActo;
import com.bachue.snr.prosnr01.model.sdb.acc.AccCompletitudDocumental;
import com.bachue.snr.prosnr01.model.sdb.acc.AccEntidadExterna;
import com.bachue.snr.prosnr01.model.sdb.acc.AccEntidadExternaConvenio;
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

import org.perf4j.StopWatch;

import java.io.IOException;

import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


/**
 * Clase que contiene todos las propiedades ParameterBean.
 *
 * @author Julian Vaca
 */
@javax.ejb.Stateless(name = "Parameter", mappedName = "parameterMappedName")
@javax.ejb.TransactionManagement(javax.ejb.TransactionManagementType.BEAN)
public class ParameterBean implements ParameterRemote
{
	/** Propiedad ip business. */
	private ParameterBusiness ip_business;

	/** {@inheritdoc} */
	public Map<String, String> getCaracterConstante(String as_constante)
	    throws B2BException
	{
		StopWatch           lsw_watch;
		Map<String, String> lmss_caracteres;

		lsw_watch           = Logger.getNewStopWatch();
		lmss_caracteres     = getParameterBusiness().getCaracterConstante(as_constante);

		Logger.log(lsw_watch, "ParameterBean", "getCaracterConstante", null, null, null, null);

		return lmss_caracteres;
	}

	/** {@inheritdoc} */
	public Map<String, String> getCirculosDestinoPorOrigen(String as_idCirculoDestino)
	    throws B2BException
	{
		StopWatch           lsw_watch;
		Map<String, String> lmss_return;

		lsw_watch       = Logger.getNewStopWatch();
		lmss_return     = getParameterBusiness().getCirculosDestinoPorOrigen(as_idCirculoDestino);

		Logger.log(lsw_watch, "ParameterBean", "getCirculosDestinoPorOrigen", null, null, null, null);

		return lmss_return;
	}

	/** {@inheritdoc} */
	public Collection<Archivo> accionClickArchivo(Archivo aa_archivo)
	    throws B2BException
	{
		StopWatch           lsw_watch;
		Collection<Archivo> lca_datos;

		lsw_watch     = Logger.getNewStopWatch();

		lca_datos = getParameterBusiness().accionClickArchivo(aa_archivo);

		Logger.log(lsw_watch, "ParameterBean", "accionClickArchivo", null, null, null, null);

		return lca_datos;
	}

	/** {@inheritdoc} */
	public Collection<Archivo> accionRegresarLog(Archivo aa_archivo)
	    throws B2BException
	{
		StopWatch           lsw_watch;
		Collection<Archivo> lca_datos;

		lsw_watch     = Logger.getNewStopWatch();

		lca_datos = getParameterBusiness().accionRegresarLog(aa_archivo);

		Logger.log(lsw_watch, "ParameterBean", "accionRegresarLog", null, null, null, null);

		return lca_datos;
	}

	/** {@inheritdoc} */
	public Collection<AccCompletitudDocumental> buscarDocumentosAportados(
	    String as_idSolicitud, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch                            lsw_watch;
		Collection<AccCompletitudDocumental> laa_datos;

		lsw_watch     = Logger.getNewStopWatch();
		laa_datos     = getParameterBusiness().buscarDocumentosAportados(as_idSolicitud);

		Logger.log(lsw_watch, "ParameterBean", "buscarDocumentosAportados", as_userId, as_localIp, as_remoteIp, null);

		return laa_datos;
	}

	/** {@inheritdoc} */
	public Collection<Archivo> cargarListaArchivos()
	    throws B2BException
	{
		StopWatch           lsw_watch;
		Collection<Archivo> lca_datos;

		lsw_watch     = Logger.getNewStopWatch();

		lca_datos = getParameterBusiness().cargarListaArchivos();

		Logger.log(lsw_watch, "ParameterBean", "cargarListaArchivos", null, null, null, null);

		return lca_datos;
	}

	/** {@inheritdoc} */
	public byte[] descargarLog(Archivo aa_archivo)
	    throws B2BException
	{
		StopWatch lsw_watch;
		byte[]    lba_datos;

		lsw_watch     = Logger.getNewStopWatch();

		lba_datos = getParameterBusiness().descargarLog(aa_archivo);

		Logger.log(lsw_watch, "ParameterBean", "descargarLog", null, null, null, null);

		return lba_datos;
	}

	/** {@inheritdoc} */
	public void ejecutarProcNocturno()
	    throws B2BException
	{
		{
			StopWatch lsw_watch;

			lsw_watch = Logger.getNewStopWatch();

			getParameterBusiness().ejecutarProcNocturno();

			Logger.log(lsw_watch, "ParameterBean", "ejecutarProcNocturno", null, null, null, null);
		}
	}

	/** {@inheritdoc} */
	public Collection<BitacoraProceso> filtroBitacoraProceso(
	    String as_descripcion, Date ad_fechaDesde, Date ld_fechaHasta, boolean ab_b
	)
	    throws B2BException
	{
		StopWatch                   lsw_watch;
		Collection<BitacoraProceso> lcr_datos;

		lsw_watch     = Logger.getNewStopWatch();

		lcr_datos = getParameterBusiness().filtroBitacoraProceso(as_descripcion, ad_fechaDesde, ld_fechaHasta, ab_b);

		Logger.log(lsw_watch, "ParameterBean", "filtroBitacoraProceso", null, null, null, lcr_datos);

		return lcr_datos;
	}

	/** {@inheritdoc} */
	public AccEntidadExterna findAccEntidadExternaById(AccEntidadExterna aaee_aee)
	    throws B2BException
	{
		StopWatch         lsw_watch;
		AccEntidadExterna laee_datos;

		lsw_watch     = Logger.getNewStopWatch();

		laee_datos = getParameterBusiness().findAccEntidadExternaById(aaee_aee);

		Logger.log(lsw_watch, "ParameterBean", "findAccEntidadExternaById", null, null, null, null);

		return laee_datos;
	}

	/**
	 * Método para encontrar todos los registros de la base de datos para la tabla SDB_ACC_ENTIDAD_EXTERNA_CONVENIO
	 * que coincidan con un id específico.
	 *
	 * @param aaeec_aeec de aaeec aeec
	 * @return el valor de AccEntidadExternaConvenio
	 * @throws B2BException de b 2 B exception
	 */
	public AccEntidadExternaConvenio findAccEntidadExternaConvenioById(AccEntidadExternaConvenio aaeec_aeec)
	    throws B2BException
	{
		StopWatch                 lsw_watch;
		AccEntidadExternaConvenio laeec_datos;

		lsw_watch     = Logger.getNewStopWatch();

		laeec_datos = getParameterBusiness().findAccEntidadExternaConvenioById(aaeec_aeec);

		Logger.log(lsw_watch, "ParameterBean", "findAccEntidadExternaConvenioById", null, null, null, null);

		return laeec_datos;
	}

	/** {@inheritdoc} */
	public ActividadEconomica findActividadEconomicaById(
	    ActividadEconomica aae_parametro, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch          lsw_watch;
		ActividadEconomica lae_datos;

		lsw_watch     = Logger.getNewStopWatch();
		lae_datos     = getParameterBusiness().findActividadEconomicaById(aae_parametro);

		Logger.log(lsw_watch, "ParameterBean", "findActividadEconomicaById", as_userId, as_localIp, as_remoteIp, null);

		return lae_datos;
	}

	/**
	 * Método para guardar los cambios o la inserción de UnidadTiempoVencimiento.
	 *
	 * @param saa_actuaciones de saa actuaciones
	 * @param as_userId de as user id
	 * @param as_localIp de as local ip
	 * @param as_remoteIp de as remote ip
	 * @return el valor de tag plantilla resolucion
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public TagPlantillaResolucion findActuacionesAdministrativasById(
	    String saa_actuaciones, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch              lsw_watch;
		TagPlantillaResolucion laa_datos;

		lsw_watch     = Logger.getNewStopWatch();
		laa_datos     = getParameterBusiness().findActuacionesAdministrativasById(saa_actuaciones);

		Logger.log(
		    lsw_watch, "ParameterBean", "findActuacionesAdministrativasById", as_userId, as_localIp, as_remoteIp, null
		);

		return laa_datos;
	}

	/** {@inheritdoc} */
	public AlertaNaturalezaJuridica findAlertaNaturalezaJuridicaById(AlertaNaturalezaJuridica ama_ma)
	    throws B2BException
	{
		StopWatch                lsw_watch;
		AlertaNaturalezaJuridica lcr_datos;

		lsw_watch     = Logger.getNewStopWatch();

		lcr_datos = getParameterBusiness().findAlertaNaturalezaJuridicaById(ama_ma);

		Logger.log(lsw_watch, "ParameterBean", "findAlertaNaturalezaJuridicaById", null, null, null, null);

		return lcr_datos;
	}

	/**
	 * Método para encontrar todos los registros de la base de datos para la tabla SDB_PGN_CAUSAL_ALERTA_TRAMITE
	 * que coincidan con un id específico.
	 *
	 * @param acad_cad de acad cad
	 * @return el valor de alerta tramite
	 * @throws B2BException de b 2 B exception
	 */
	public AlertaTramite findAlertaTramiteById(AlertaTramite acad_cad)
	    throws B2BException
	{
		StopWatch     lsw_watch;
		AlertaTramite lcr_datos;

		lsw_watch     = Logger.getNewStopWatch();

		lcr_datos = getParameterBusiness().findAlertaTramiteById(acad_cad);

		Logger.log(lsw_watch, "ParameterBean", "findAlertaTramiteById", null, null, null, null);

		return lcr_datos;
	}

	/**
	 * Método para consultar los datos de la tabla SDB_ACC_ENTIDAD_EXTERNA_CONVENIO.
	 *
	 * @param ab_accion objeto boolean con la accion
	 * @return devuelve el valor de Collection
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public Collection<AccEntidadExternaConvenio> findAllAccEntidadExternaConvenio(boolean ab_accion)
	    throws B2BException
	{
		StopWatch                             lsw_watch;
		Collection<AccEntidadExternaConvenio> lcaeec_datos;

		lsw_watch     = Logger.getNewStopWatch();

		lcaeec_datos = getParameterBusiness().findAllAccEntidadExternaConvenio(ab_accion);

		Logger.log(lsw_watch, "ParameterBean", "findAllAccEntidadExternaConvenio", null, null, null, lcaeec_datos);

		return lcaeec_datos;
	}

	/** {@inheritdoc} */
	public Collection<AccEntidadExterna> findAllAccEntidadExternas()
	    throws B2BException
	{
		StopWatch                     lsw_watch;
		Collection<AccEntidadExterna> lcaee_datos;

		lsw_watch     = Logger.getNewStopWatch();

		lcaee_datos = getParameterBusiness().findAllAccEntidadExternas();

		Logger.log(lsw_watch, "ParameterBean", "findAllAccEntidadExternas", null, null, null, lcaee_datos);

		return lcaee_datos;
	}

	/** {@inheritdoc} */
	public Collection<ActividadEconomica> findAllActividadEconomica()
	    throws B2BException
	{
		StopWatch                      lsw_watch;
		Collection<ActividadEconomica> lcae_datos;

		lsw_watch     = Logger.getNewStopWatch();

		lcae_datos = getParameterBusiness().findAllActividadEconomica();

		Logger.log(lsw_watch, "ParameterBean", "findAllActividadEconomica", null, null, null, lcae_datos);

		return lcae_datos;
	}

	/**
	 * Método para consultar los datos de la tabla SDB_PGN_ACTIVIDAD_ECONOMICA.
	 *
	 * @return devuelve el valor de Collection
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public Collection<ActividadEconomica> findAllActividadEconomicaActivo()
	    throws B2BException
	{
		StopWatch                      lsw_watch;
		Collection<ActividadEconomica> lcae_datos;

		lsw_watch     = Logger.getNewStopWatch();

		lcae_datos = getParameterBusiness().findAllActividadEconomicaActivo();

		Logger.log(lsw_watch, "ParameterBean", "findAllActividadEconomicaActivo", null, null, null, lcae_datos);

		return lcae_datos;
	}

	/** {@inheritdoc} */
	public Collection<TagPlantillaResolucion> findAllActuacionesAdministrativas(
	    String as_idUsuario, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch                          lsw_watch;
		Collection<TagPlantillaResolucion> lcaa_datos;

		lsw_watch     = Logger.getNewStopWatch();

		lcaa_datos = getParameterBusiness().findAllActuacionesAdministrativas();

		Logger.log(
		    lsw_watch, "ParameterBean", "findAllActuacionesAdministrativas", as_idUsuario, as_localIp, as_remoteIp,
		    lcaa_datos
		);

		return lcaa_datos;
	}

	/**
	 * Método para guardar los cambios o la inserción de UnidadTiempoVencimiento.
	 *
	 * @param as_plantilla de as plantilla
	 * @param as_tag de as tag
	 * @param as_userId de as user id
	 * @param as_localIp de as local ip
	 * @param as_remoteIp de as remote ip
	 * @return el valor de tag plantilla resolucion
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public TagPlantillaResolucion findAllActuacionesAdministrativasByIdPlantillaTag(
	    String as_plantilla, String as_tag, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch              lsw_watch;
		TagPlantillaResolucion laa_datos;

		lsw_watch     = Logger.getNewStopWatch();
		laa_datos     = getParameterBusiness().findAllActuacionesAdministrativasByIdPlantillaTag(as_plantilla, as_tag);

		Logger.log(
		    lsw_watch, "ParameterBean", "findAllActuacionesAdministrativasByIdPlantillaTag", as_userId, as_localIp,
		    as_remoteIp, null
		);

		return laa_datos;
	}

	/**
	 * Método para actualizar la version de un acto.
	 *
	 * @param as_idTipoActo de as id tipo acto
	 * @return el valor de tipo acto
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public TipoActo findAllActualizarVersionById(String as_idTipoActo)
	    throws B2BException
	{
		TipoActo  lcc_return;
		StopWatch lsw_watch;

		lsw_watch     = Logger.getNewStopWatch();

		lcc_return = getParameterBusiness().findAllActualizarVersionById(as_idTipoActo);

		Logger.log(lsw_watch, "ParameterBean", "findAllActualizarVersionById", null, null, null, null);

		return lcc_return;
	}

	/** {@inheritdoc} */
	public Collection<Componente> findAllAdministracionComponentes()
	    throws B2BException
	{
		StopWatch              lsw_watch;
		Collection<Componente> lac_datos;

		lsw_watch     = Logger.getNewStopWatch();

		lac_datos = getParameterBusiness().findAllAdministracionComponentes();

		Logger.log(lsw_watch, "ParameterBean", "findAllAdministracionComponentes", null, null, null, lac_datos);

		return lac_datos;
	}

	/** {@inheritdoc} */
	public Componente findAllAdministracionComponentesById(Componente acac_ac)
	    throws B2BException
	{
		StopWatch  lsw_watch;
		Componente lac_datos;

		lsw_watch     = Logger.getNewStopWatch();

		lac_datos = getParameterBusiness().findAllAdministracionComponentesById(acac_ac);

		Logger.log(lsw_watch, "ParameterBean", "findAllAdministracionComponentesById", null, null, null, null);

		return lac_datos;
	}

	/** {@inheritdoc} */
	public Collection<Componente> findAllAdministracionComponentesPermisos(String as_s)
	    throws B2BException
	{
		StopWatch              lsw_watch;
		Collection<Componente> lac_datos;

		lsw_watch     = Logger.getNewStopWatch();

		lac_datos = getParameterBusiness().findAllAdministracionComponentesPermisos(as_s);

		Logger.log(lsw_watch, "ParameterBean", "findAllAdministracionComponentesPermisos", null, null, null, lac_datos);

		return lac_datos;
	}

	/** {@inheritdoc} */
	public Collection<AlertaNaturalezaJuridica> findAllAlertaNaturalezaJuridica()
	    throws B2BException
	{
		StopWatch                            lsw_watch;
		Collection<AlertaNaturalezaJuridica> lcr_datos;

		lsw_watch     = Logger.getNewStopWatch();

		lcr_datos = getParameterBusiness().findAllAlertaNaturalezaJuridica();

		Logger.log(lsw_watch, "ParameterBean", "findAllAlertaNaturalezaJuridica", null, null, null, lcr_datos);

		return lcr_datos;
	}

	/** {@inheritdoc} */
	public Collection<AlertaTramite> findAllAlertaTramite()
	    throws B2BException
	{
		StopWatch                 lsw_watch;
		Collection<AlertaTramite> lcr_datos;

		lsw_watch     = Logger.getNewStopWatch();

		lcr_datos = getParameterBusiness().findAllAlertaTramite();

		Logger.log(lsw_watch, "ParameterBean", "findAllAlertaTramite", null, null, null, lcr_datos);

		return lcr_datos;
	}

	/** {@inheritdoc} */
	public Collection<TipoTarjetaApoderado> findAllApoderados()
	    throws B2BException
	{
		StopWatch                        lsw_watch;
		Collection<TipoTarjetaApoderado> lcr_datos;

		lsw_watch     = Logger.getNewStopWatch();

		lcr_datos = getParameterBusiness().findAllTipoTarjetaApoderados();

		Logger.log(lsw_watch, "ParameterBean", "findAllApoderados", null, null, null, lcr_datos);

		return lcr_datos;
	}

	/** {@inheritdoc} */
	public Collection<CamposCertificado> findAllCamposCertificado()
	    throws B2BException
	{
		StopWatch                     lsw_watch;
		Collection<CamposCertificado> lcc_datos;

		lsw_watch     = Logger.getNewStopWatch();

		lcc_datos = getParameterBusiness().findAllCamposCertificado();

		Logger.log(lsw_watch, "ParameterBean", "findAllCamposCertificado", null, null, null, lcc_datos);

		return lcc_datos;
	}

	/** {@inheritdoc} */
	public Collection<CamposConsulta> findAllCamposConsulta()
	    throws B2BException
	{
		StopWatch                  lsw_watch;
		Collection<CamposConsulta> lccc_datos;

		lsw_watch     = Logger.getNewStopWatch();

		lccc_datos = getParameterBusiness().findAllCamposConsulta();

		Logger.log(lsw_watch, "ParameterBean", "findAllCamposConsulta", null, null, null, lccc_datos);

		return lccc_datos;
	}

	/** {@inheritdoc} */
	public Collection<CamposCorreccion> findAllCamposCorreccion()
	    throws B2BException
	{
		StopWatch                    lsw_watch;
		Collection<CamposCorreccion> lcc_datos;

		lsw_watch     = Logger.getNewStopWatch();

		lcc_datos = getParameterBusiness().findAllCamposCorreccion();

		Logger.log(lsw_watch, "ParameterBean", "findAllCamposCorreccion", null, null, null, lcc_datos);

		return lcc_datos;
	}

	/**
	 * Método para encontrar todos los campos criterio de busqueda.
	 *
	 * @param as_userId de as user id
	 * @param as_localIp de as local ip
	 * @param as_remoteIp de as remote ip
	 * @return Collección de campos Consulta con la información requerida
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Collection<CamposConsulta> findAllCamposCriteriosBusqueda(
	    String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch                  lsw_watch;
		Collection<CamposConsulta> lccc_datos;

		lsw_watch     = Logger.getNewStopWatch();

		lccc_datos = getParameterBusiness().findAllCamposCriterioBusqueda();

		Logger.log(
		    lsw_watch, "ParameterBean", "findAllCamposCriterioBusqueda", as_userId, as_localIp, as_remoteIp, lccc_datos
		);

		return lccc_datos;
	}

	/**
	 * Método para consultar todos los campos criterio busqueda basado en su tipo criterio de busqueda.
	 *
	 * @param as_tipoCriterioBusquedaActual de as tipo criterio busqueda actual
	 * @return el valor de collection
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Collection<CamposConsulta> findAllCamposCriteriosBusquedaByTipoCriterioBusqueda(
	    String as_tipoCriterioBusquedaActual
	)
	    throws B2BException
	{
		Collection<CamposConsulta> lcc_return;
		StopWatch                  lsw_watch;

		lsw_watch      = Logger.getNewStopWatch();
		lcc_return     = getParameterBusiness()
				                 .findAllCampoCriterioBusquedaByTipoCriterioBusqueda(as_tipoCriterioBusquedaActual);

		Logger.log(
		    lsw_watch, "ParameterBean", "findAllCampoCriterioBusquedaByTipoCriterioBusqueda", null, null, null, null
		);

		return lcc_return;
	}

	/** {@inheritdoc} */
	public Collection<Canal> findAllCanal(boolean ab_accion)
	    throws B2BException
	{
		StopWatch         lsw_watch;
		Collection<Canal> lcc_datos;

		lsw_watch     = Logger.getNewStopWatch();

		lcc_datos = getParameterBusiness().findAllCanal(ab_accion);

		Logger.log(lsw_watch, "ParameterBean", "findAllCanal", null, null, null, lcc_datos);

		return lcc_datos;
	}

	/** {@inheritdoc} */
	public Collection<CanalOrigenServicio> findAllCanalOrigenServicio()
	    throws B2BException
	{
		StopWatch                       lsw_watch;
		Collection<CanalOrigenServicio> lcos_datos;

		lsw_watch     = Logger.getNewStopWatch();

		lcos_datos = getParameterBusiness().findAllCanalOrigenServicio();

		Logger.log(lsw_watch, "ParameterBean", "findAllCanalOrigenServicio", null, null, null, lcos_datos);

		return lcos_datos;
	}

	/** {@inheritdoc} */
	public Collection<Catastro> findAllCatastro()
	    throws B2BException
	{
		StopWatch            lsw_watch;
		Collection<Catastro> lcc_datos;

		lsw_watch     = Logger.getNewStopWatch();

		lcc_datos = getParameterBusiness().findAllCatastro();

		Logger.log(lsw_watch, "ParameterBean", "findAllCatastro", null, null, null, lcc_datos);

		return lcc_datos;
	}

	/**
	 * Método para encontrar todos los registros de la base de datos para la tabla SDB_PGN_CAUSAL_APROBACION_DEVOLUCION.
	 *
	 * @return el valor de collection
	 * @throws B2BException de b 2 B exception
	 */
	public Collection<CausalAprobacionDevolucion> findAllCausalAprobacionDevolucion()
	    throws B2BException
	{
		StopWatch                              lsw_watch;
		Collection<CausalAprobacionDevolucion> lcr_datos;

		lsw_watch     = Logger.getNewStopWatch();

		lcr_datos = getParameterBusiness().findAllCausalAprobacionDevolucion();

		Logger.log(lsw_watch, "ParameterBean", "findAllCausalAprobacionDevolucion", null, null, null, lcr_datos);

		return lcr_datos;
	}

	/**
	 * Método para encontrar todos los registros de la base de datos para la tabla SDB_PGN_CAUSAL_CORRECCION.
	 *
	 * @return el valor de collection
	 * @throws B2BException de b 2 B exception
	 */
	public Collection<CausalCorreccion> findAllCausalCorreccion()
	    throws B2BException
	{
		StopWatch                    lsw_watch;
		Collection<CausalCorreccion> lcr_datos;

		lsw_watch     = Logger.getNewStopWatch();

		lcr_datos = getParameterBusiness().findAllCausalCorreccion();

		Logger.log(lsw_watch, "ParameterBean", "findAllCausalCorreccion", null, null, null, lcr_datos);

		return lcr_datos;
	}

	/**
	 * Método para encontrar todos los registros de causal Mayor Valor.
	 *
	 * @return colleccion de causalMayorValor con todos los registros
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Collection<CausalMayorValor> findAllCausalMayorValor()
	    throws B2BException
	{
		StopWatch                    lsw_watch;
		Collection<CausalMayorValor> lcr_datos;

		lsw_watch     = Logger.getNewStopWatch();

		lcr_datos = getParameterBusiness().findAllCausalMayorValor();

		Logger.log(lsw_watch, "ParameterBean", "findAllCausalMayor", null, null, null, lcr_datos);

		return lcr_datos;
	}

	/**
	 * Método para conssultar un registro de Causal Mayor Valor basad en su identificador.
	 *
	 * @param al_idCausalMayorValor de al id causal mayor valor
	 * @return CausalMayorValor con los datos solicitados.
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public CausalMayorValor findAllCausalMayorValorById(long al_idCausalMayorValor)
	    throws B2BException
	{
		CausalMayorValor lccmv_return;
		StopWatch        lsw_watch;

		lsw_watch     = Logger.getNewStopWatch();

		lccmv_return = getParameterBusiness().findAllCausalMayorValorById(al_idCausalMayorValor);

		Logger.log(lsw_watch, "ParameterBean", "findAllCausalMayorValorById", null, null, null, null);

		return lccmv_return;
	}

	/**
	 * Método para consultar un registro de Causal Negacion Copias.
	 *
	 * @return CausalNegacionCopias con los datos solicitados.
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Collection<CausalNegacionCopias> findAllCausalNegacionCopias()
	    throws B2BException
	{
		StopWatch                        lsw_watch;
		Collection<CausalNegacionCopias> lccr_datos;

		lsw_watch     = Logger.getNewStopWatch();

		lccr_datos = getParameterBusiness().findAllCausalNegacionCopias();

		Logger.log(lsw_watch, "ParameterBean", "findAllCausalNegacionCopias", null, null, null, lccr_datos);

		return lccr_datos;
	}

	/**
	 * método de consulta de tipoDocumental para obtener todos sus registros por su indicativo.
	 *
	 * @param as_idTipoDocumental de as id tipo documental
	 * @return TipoDocumental con la información solicitada
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public CausalNegacionCopias findAllCausalNegacionCopiasById(String as_idTipoDocumental)
	    throws B2BException
	{
		CausalNegacionCopias lcnc_return;
		StopWatch            lsw_watch;

		lsw_watch     = Logger.getNewStopWatch();

		lcnc_return = getParameterBusiness().findAllCausalNegacionCopiasById(as_idTipoDocumental);

		Logger.log(lsw_watch, "ParameterBean", "findAllCausalNegacionCopiasById", null, null, null, null);

		return lcnc_return;
	}

	/** {@inheritdoc} */
	public Collection<CausalRechazoRecurso> findAllCausalRechazoRecurso(
	    String as_idUsuario, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch                        lsw_watch;
		Collection<CausalRechazoRecurso> lccrr_datos;

		lsw_watch     = Logger.getNewStopWatch();

		lccrr_datos = getParameterBusiness().findAllCausalRechazoRecurso();

		Logger.log(
		    lsw_watch, "ParameterBean", "findAllCausalRechazoRecurso", as_idUsuario, as_localIp, as_remoteIp,
		    lccrr_datos
		);

		return lccrr_datos;
	}

	/**
	 * Método para consultar un registro de Causal Reimpresion.
	 *
	 * @return CausalReimpresion con los datos solicitados.
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Collection<CausalReimpresion> findAllCausalReimpresion()
	    throws B2BException
	{
		StopWatch                     lsw_watch;
		Collection<CausalReimpresion> lccr_datos;

		lsw_watch     = Logger.getNewStopWatch();

		lccr_datos = getParameterBusiness().findAllCausalReimpresion();

		Logger.log(lsw_watch, "ParameterBean", "findAllCausalReimpresion", null, null, null, lccr_datos);

		return lccr_datos;
	}

	/** {@inheritdoc} */
	public Collection<CirculoActAdmin> findAllCirculoActAdmin()
	    throws B2BException
	{
		StopWatch                   lsw_watch;
		Collection<CirculoActAdmin> lccaa_datos;

		lsw_watch     = Logger.getNewStopWatch();

		lccaa_datos = getParameterBusiness().findAllCirculoActAdmin();

		Logger.log(lsw_watch, "ParameterBean", "findAllCirculoActAdmin", null, null, null, lccaa_datos);

		return lccaa_datos;
	}

	/** {@inheritdoc} */
	public Collection<CirculoCatastro> findAllCirculoCatastro()
	    throws B2BException
	{
		StopWatch                   lsw_watch;
		Collection<CirculoCatastro> lccc_datos;

		lsw_watch     = Logger.getNewStopWatch();

		lccc_datos = getParameterBusiness().findAllCirculoCatastro();

		Logger.log(lsw_watch, "ParameterBean", "findAllCirculoCatastro", null, null, null, lccc_datos);

		return lccc_datos;
	}

	/** {@inheritdoc} */
	public Collection<CirculoFestivo> findAllCirculoFestivo()
	    throws B2BException
	{
		StopWatch                  lsw_watch;
		Collection<CirculoFestivo> lcr_datos;

		lsw_watch     = Logger.getNewStopWatch();

		lcr_datos = getParameterBusiness().findAllCirculoFestivo();

		Logger.log(lsw_watch, "ParameterBean", "findAllMedidaArea", null, null, null, lcr_datos);

		return lcr_datos;
	}

	/** {@inheritdoc} */
	public Collection<CirculoOrigenDestino> findAllCirculoOrigenDestino()
	    throws B2BException
	{
		StopWatch                        lsw_watch;
		Collection<CirculoOrigenDestino> lccod_datos;

		lsw_watch     = Logger.getNewStopWatch();

		lccod_datos = getParameterBusiness().findAllCirculoOrigenDestino();

		Logger.log(lsw_watch, "ParameterBean", "findAllCirculoOrigenDestino", null, null, null, lccod_datos);

		return lccod_datos;
	}

	/** {@inheritdoc} */
	public Collection<CirculoRegistral> findAllCirculos(
	    boolean ab_b, String as_idUsuario, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch                    lsw_watch;
		Collection<CirculoRegistral> lcc_datos;

		lsw_watch     = Logger.getNewStopWatch();

		lcc_datos = getParameterBusiness().findAllCirculos(ab_b);

		Logger.log(lsw_watch, "ParameterBean", "findAllCirculos", as_idUsuario, as_localIp, as_remoteIp, lcc_datos);

		return lcc_datos;
	}

	/** {@inheritdoc} */
	public Collection<Componente> findAllComponenteActivo(String as_userId, String as_localIp, String as_remoteIp)
	    throws B2BException
	{
		StopWatch              lsw_watch;
		Collection<Componente> lcc_datos;

		lsw_watch     = Logger.getNewStopWatch();
		lcc_datos     = getParameterBusiness().findAllComponenteActivo();

		Logger.log(
		    lsw_watch, "ParameterBean", "findAllComponenteActivo", as_userId, as_localIp, as_remoteIp, lcc_datos
		);

		return lcc_datos;
	}

	/** {@inheritdoc} */
	public Collection<ComunidadesEtnicas> findAllComunidadesEtnicas()
	    throws B2BException
	{
		StopWatch                      lsw_watch;
		Collection<ComunidadesEtnicas> lcce_datos;

		lsw_watch     = Logger.getNewStopWatch();

		lcce_datos = getParameterBusiness().findAllComunidadesEtnicas();

		Logger.log(lsw_watch, "ParameterBean", "findAllComunidadesEtnicas", null, null, null, lcce_datos);

		return lcce_datos;
	}

	/** {@inheritdoc} */
	public Collection<CondicionTarifa> findAllCondicionTarifa()
	    throws B2BException
	{
		StopWatch                   lsw_watch;
		Collection<CondicionTarifa> lcr_datos;

		lsw_watch     = Logger.getNewStopWatch();

		lcr_datos = getParameterBusiness().findAllCondicionTarifa();

		Logger.log(lsw_watch, "ParameterBean", "findAllCondicionTarifa", null, null, null, lcr_datos);

		return lcr_datos;
	}

	/** {@inheritdoc} */
	public Collection<Constantes> findAllConstants()
	    throws B2BException
	{
		StopWatch              lsw_watch;
		Collection<Constantes> lcc_datos;

		lsw_watch     = Logger.getNewStopWatch();

		lcc_datos = getParameterBusiness().findAllConstants();

		Logger.log(lsw_watch, "ParameterBean", "findAllConstants", null, null, null, lcc_datos);

		return lcc_datos;
	}

	/** {@inheritdoc} */
	public Collection<Constantes> findAllConstantsCYN()
	    throws B2BException
	{
		StopWatch              lsw_watch;
		Collection<Constantes> lcc_datos;

		lsw_watch     = Logger.getNewStopWatch();

		lcc_datos = getParameterBusiness().findAllConstantsCYN();

		Logger.log(lsw_watch, "ParameterBean", "findAllConstantsCYN", null, null, null, lcc_datos);

		return lcc_datos;
	}

	/** {@inheritdoc} */
	public Collection<Consultas> findAllConsultas()
	    throws B2BException
	{
		StopWatch             lsw_watch;
		Collection<Consultas> lcr_datos;

		lsw_watch     = Logger.getNewStopWatch();

		lcr_datos = getParameterBusiness().findAllConsultas();

		Logger.log(lsw_watch, "ParameterBean", "findAllConsultas", null, null, null, lcr_datos);

		return lcr_datos;
	}

	/**
	 * Método para encontrar todos los registros de la base de datos para la tabla SDB_PNG_COORDENADA.
	 *
	 * @return el valor de collection
	 * @throws B2BException de b 2 B exception
	 */
	public Collection<Coordenada> findAllCoordenada()
	    throws B2BException
	{
		StopWatch              lsw_watch;
		Collection<Coordenada> lcc_datos;

		lsw_watch     = Logger.getNewStopWatch();

		lcc_datos = getParameterBusiness().findAllCoordenada();

		Logger.log(lsw_watch, "ParameterBean", "findAllCoordenada", null, null, null, lcc_datos);

		return lcc_datos;
	}

	/** {@inheritdoc} */
	public Collection<Coordenada> findAllCoordenadaActivo()
	    throws B2BException
	{
		StopWatch              lsw_watch;
		Collection<Coordenada> lcc_datos;

		lsw_watch     = Logger.getNewStopWatch();
		lcc_datos     = getParameterBusiness().findAllCoordenadaActivo();

		Logger.log(lsw_watch, "ParameterBean", "findAllCoordenadaActivo", null, null, null, lcc_datos);

		return lcc_datos;
	}

	/** {@inheritdoc} */
	public Collection<Departamento> findAllDepartamentos()
	    throws B2BException
	{
		StopWatch                lsw_watch;
		Collection<Departamento> lcc_datos;

		lsw_watch     = Logger.getNewStopWatch();

		lcc_datos = getParameterBusiness().findAllDepartamentos();

		Logger.log(lsw_watch, "ParameterBean", "findAllDepartamentos", null, null, null, lcc_datos);

		return lcc_datos;
	}

	/** {@inheritdoc} */
	public Collection<DependenciaSNR> findAllDependenciaSNR()
	    throws B2BException
	{
		StopWatch                  lsw_watch;
		Collection<DependenciaSNR> lcdsnr_datos;

		lsw_watch     = Logger.getNewStopWatch();

		lcdsnr_datos = getParameterBusiness().findAllDependenciaSNR();

		Logger.log(lsw_watch, "ParameterBean", "findAllDependenciaSNR", null, null, null, lcdsnr_datos);

		return lcdsnr_datos;
	}

	/** {@inheritdoc} */
	public Collection<Desborde> findAllDesbordes(String as_idUsuario, String as_localIp, String as_remoteIp)
	    throws B2BException
	{
		StopWatch            lsw_watch;
		Collection<Desborde> lcc_datos;

		lsw_watch     = Logger.getNewStopWatch();

		lcc_datos = getParameterBusiness().findAllDesbordes();

		Logger.log(lsw_watch, "ParameterBean", "findAllDesbordes", as_idUsuario, as_localIp, as_remoteIp, lcc_datos);

		return lcc_datos;
	}

	/** {@inheritdoc} */
	public Collection<DetalleProcesoConsulta> findAllDetalleProcesoConsulta()
	    throws B2BException
	{
		StopWatch                          lsw_watch;
		Collection<DetalleProcesoConsulta> lcdpc_datos;

		lsw_watch     = Logger.getNewStopWatch();

		lcdpc_datos = getParameterBusiness().findAllDetalleProcesoConsulta();

		Logger.log(lsw_watch, "ParameterBean", "findAllDetalleProcesoConsulta", null, null, null, lcdpc_datos);

		return lcdpc_datos;
	}

	/** {@inheritdoc} */
	public Collection<DominioRrr> findAllDominioRRR()
	    throws B2BException
	{
		StopWatch              lsw_watch;
		Collection<DominioRrr> lcdr_datos;

		lsw_watch     = Logger.getNewStopWatch();

		lcdr_datos = getParameterBusiness().findAllDominioRRR();

		Logger.log(lsw_watch, "ParameterBean", "findAllDominioRRR", null, null, null, lcdr_datos);

		return lcdr_datos;
	}

	/** {@inheritdoc} */
	public Collection<DominioNaturalezaJuridica> findAllDominiosNaturalezaJuridica(
	    String as_idUsuario, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch                             lsw_watch;
		Collection<DominioNaturalezaJuridica> lcc_datos;

		lsw_watch     = Logger.getNewStopWatch();

		lcc_datos = getParameterBusiness().findAllDominiosNaturalezaJuridica();

		Logger.log(
		    lsw_watch, "ParameterBean", "findAllDominiosNaturalezaJuridica", as_idUsuario, as_localIp, as_remoteIp,
		    lcc_datos
		);

		return lcc_datos;
	}

	/**
	 * Método para encontrar todos los registros de la base de datos para la tabla SDB_PGN_ENTIDAD_RECAUDADORA.
	 *
	 * @return el valor de collection
	 * @throws B2BException de b 2 B exception
	 */
	public Collection<EntidadRecaudadora> findAllEntidadRecaudadora()
	    throws B2BException
	{
		StopWatch                      lsw_watch;
		Collection<EntidadRecaudadora> lcer_datos;

		lsw_watch     = Logger.getNewStopWatch();

		lcer_datos = getParameterBusiness().findAllEntidadRecaudadora();

		Logger.log(lsw_watch, "ParameterBean", "findAllEntidadRecaudadora", null, null, null, lcer_datos);

		return lcer_datos;
	}

	/** {@inheritdoc} */
	public Collection<EntidadesAlerta> findAllEntidadesAlerta()
	    throws B2BException
	{
		StopWatch                   lsw_watch;
		Collection<EntidadesAlerta> lcea_datos;

		lsw_watch     = Logger.getNewStopWatch();

		lcea_datos = getParameterBusiness().findAllEntidadesAlerta();

		Logger.log(lsw_watch, "ParameterBean", "findAllEntidadesAlerta", null, null, null, lcea_datos);

		return lcea_datos;
	}

	/** {@inheritdoc} */
	public Collection<EstadoActividad> findAllEstadoActividad(boolean ab_b)
	    throws B2BException
	{
		StopWatch                   lsw_watch;
		Collection<EstadoActividad> lcc_datos;

		lsw_watch     = Logger.getNewStopWatch();

		lcc_datos = getParameterBusiness().findAllEstadoActividad(ab_b);

		Logger.log(lsw_watch, "ParameterBean", "findAllEstadoActividad", null, null, null, lcc_datos);

		return lcc_datos;
	}

	/**
	 * Método para encontrar todos los registros de la base de datos para la tabla SDB_ACC_ANOTACION_PREDIO.
	 *
	 * @return el valor de collection
	 * @throws B2BException de b 2 B exception
	 */
	public Collection<EstadoAnotacion> findAllEstadoAnotacion()
	    throws B2BException
	{
		StopWatch                   lsw_watch;
		Collection<EstadoAnotacion> lea_datos;

		lsw_watch     = Logger.getNewStopWatch();

		lea_datos = getParameterBusiness().findAllEstadoAnotacion();

		Logger.log(lsw_watch, "ParameterBean", "findAllEstadoAnotacion", null, null, null, lea_datos);

		return lea_datos;
	}

	/** {@inheritdoc} */
	public Collection<EstadoNupre> findAllEstadoNupre()
	    throws B2BException
	{
		StopWatch               lsw_watch;
		Collection<EstadoNupre> len_datos;

		lsw_watch     = Logger.getNewStopWatch();

		len_datos = getParameterBusiness().findAllEstadoNupre();

		Logger.log(lsw_watch, "ParameterBean", "findAllEstadoNupre", null, null, null, len_datos);

		return len_datos;
	}

	/** {@inheritdoc} */
	public Collection<EstadoPredio> findAllEstadoPredio()
	    throws B2BException
	{
		StopWatch                lsw_watch;
		Collection<EstadoPredio> len_datos;

		lsw_watch     = Logger.getNewStopWatch();

		len_datos = getParameterBusiness().findAllEstadoPredio();

		Logger.log(lsw_watch, "ParameterBean", "findAllEstadoPredio", null, null, null, len_datos);

		return len_datos;
	}

	/**
	 * Método para encontrar todos los registros de la base de datos para la tabla SDB_PNG_ESTADO_REGISTRO.
	 *
	 * @return el valor de collection
	 * @throws B2BException de b 2 B exception
	 */
	public Collection<EstadoRegistro> findAllEstadoRegistro()
	    throws B2BException
	{
		StopWatch                  lsw_watch;
		Collection<EstadoRegistro> ler_datos;

		lsw_watch     = Logger.getNewStopWatch();

		ler_datos = getParameterBusiness().findAllEstadoRegistro();

		Logger.log(lsw_watch, "ParameterBean", "findAllEstadoRegistro", null, null, null, ler_datos);

		return ler_datos;
	}

	/** {@inheritdoc} */
	public Collection<Estados> findAllEstados()
	    throws B2BException
	{
		StopWatch           lsw_watch;
		Collection<Estados> lce_datos;

		lsw_watch     = Logger.getNewStopWatch();

		lce_datos = getParameterBusiness().findAllEstados();

		Logger.log(lsw_watch, "ParameterBean", "findAllEstados", null, null, null, lce_datos);

		return lce_datos;
	}

	/**
	 * Método de consulta de OpcionEtapa para obtener todos sus registros asociados a una opcion.
	 *
	 * @param ao_opcion de ao opcion
	 * @return Opcion con la información solicitada
	 * @throws B2BException de b 2 B exception
	 */
	public Opcion findAllEtapaOpciones(Opcion ao_opcion)
	    throws B2BException
	{
		Opcion    lo_return;
		StopWatch lsw_watch;

		lsw_watch     = Logger.getNewStopWatch();

		lo_return = getParameterBusiness().findAllEtapaOpciones(ao_opcion);

		Logger.log(lsw_watch, "ParameterBean", "findAllEtapaOpciones", null, null, null, null);

		return lo_return;
	}

	/** {@inheritdoc} */
	public Collection<Etapa> findAllEtapas(String as_idUsuario, String as_localIp, String as_remoteIp)
	    throws B2BException
	{
		StopWatch         lsw_watch;
		Collection<Etapa> le_datos;

		lsw_watch     = Logger.getNewStopWatch();

		le_datos = getParameterBusiness().findAllEtapas();

		Logger.log(lsw_watch, "ParameterBean", "findAllEtapas", as_idUsuario, as_localIp, as_remoteIp, le_datos);

		return le_datos;
	}

	/** {@inheritdoc} */
	public Collection<Fases> findAllFases(String as_idUsuario, String as_localIp, String as_remoteIp)
	    throws B2BException
	{
		StopWatch         lsw_watch;
		Collection<Fases> lcf_datos;

		lsw_watch     = Logger.getNewStopWatch();

		lcf_datos = getParameterBusiness().findAllFases();

		Logger.log(lsw_watch, "ParameterBean", "findAllFases", as_idUsuario, as_localIp, as_remoteIp, lcf_datos);

		return lcf_datos;
	}

	/** {@inheritdoc} */
	public Collection<FestivoNacional> findAllFestivoNacional(boolean ab_b)
	    throws B2BException
	{
		StopWatch                   lsw_watch;
		Collection<FestivoNacional> lcc_datos;

		lsw_watch     = Logger.getNewStopWatch();

		lcc_datos = getParameterBusiness().findAllFestivoNacional(ab_b);

		Logger.log(lsw_watch, "ParameterBean", "findAllFestivoNacional", null, null, null, lcc_datos);

		return lcc_datos;
	}

	/** {@inheritdoc} */
	public Collection<Gobernacion> findAllGobernacion(boolean ab_b)
	    throws B2BException
	{
		StopWatch               lsw_watch;
		Collection<Gobernacion> lcc_datos;

		lsw_watch     = Logger.getNewStopWatch();

		lcc_datos = getParameterBusiness().findAllGobernacion(ab_b);

		Logger.log(lsw_watch, "ParameterBean", "findAllGobernacion", null, null, null, lcc_datos);

		return lcc_datos;
	}

	/**
	 * Método para encontrar todos los registros de la base de datos para la tabla SDB_PNG_GRUPO_NATURALEZA_JURIDICA.
	 *
	 * @return el valor de collection
	 * @throws B2BException de b 2 B exception
	 */
	public Collection<GrupoNaturalezaJuridica> findAllGrupoNaturalezaJuridica()
	    throws B2BException
	{
		StopWatch                           lsw_watch;
		Collection<GrupoNaturalezaJuridica> lgnj_datos;

		lsw_watch     = Logger.getNewStopWatch();

		lgnj_datos = getParameterBusiness().findAllGrupoNaturalezaJuridica();

		Logger.log(lsw_watch, "ParameterBean", "findAllGrupoNaturalezaJuridica", null, null, null, lgnj_datos);

		return lgnj_datos;
	}

	/** {@inheritdoc} */
	public Collection<InstanciaConsulta> findAllInstanciaConsulta(boolean ab_b)
	    throws B2BException
	{
		StopWatch                     lsw_watch;
		Collection<InstanciaConsulta> lcic_datos;

		lsw_watch     = Logger.getNewStopWatch();

		lcic_datos = getParameterBusiness().findAllInstanciaConsulta(ab_b);

		Logger.log(lsw_watch, "ParameterBean", "findAllInstanciaConsulta", null, null, null, lcic_datos);

		return lcic_datos;
	}

	/** {@inheritdoc} */
	public Collection<InteresadoDocumentoTipo> findAllInteresadoDocumentoTipos(
	    String as_idUsuario, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch                           lsw_watch;
		Collection<InteresadoDocumentoTipo> lcc_datos;

		lsw_watch     = Logger.getNewStopWatch();

		lcc_datos = getParameterBusiness().findAllInteresadoDocumentoTipos();

		Logger.log(
		    lsw_watch, "ParameterBean", "findAllInteresadoDocumentoTipos", as_idUsuario, as_localIp, as_remoteIp,
		    lcc_datos
		);

		return lcc_datos;
	}

	/** {@inheritdoc} */
	public Collection<InteresadoNaturalGenero> findAllInteresadoNaturalGenero()
	    throws B2BException
	{
		StopWatch                           lsw_watch;
		Collection<InteresadoNaturalGenero> lcing_datos;

		lsw_watch     = Logger.getNewStopWatch();

		lcing_datos = getParameterBusiness().findAllInteresadoNaturalGenero();

		Logger.log(lsw_watch, "ParameterBean", "findAllInteresadoNaturalGenero", null, null, null, lcing_datos);

		return lcing_datos;
	}

	/**
	 * Método para encontrar todos los registros de la base de datos para la tabla SDB_PNG_LETRA_EJE.
	 *
	 * @return el valor de collection
	 * @throws B2BException de b 2 B exception
	 */
	public Collection<LetraEje> findAllLetraEje()
	    throws B2BException
	{
		StopWatch            lsw_watch;
		Collection<LetraEje> lcle_datos;

		lsw_watch     = Logger.getNewStopWatch();

		lcle_datos = getParameterBusiness().findAllLetraEje();

		Logger.log(lsw_watch, "ParameterBean", "findAllLetraEje", null, null, null, lcle_datos);

		return lcle_datos;
	}

	/** {@inheritdoc} */
	public Collection<LetraEje> findAllLetraEjeActivo()
	    throws B2BException
	{
		StopWatch            lsw_watch;
		Collection<LetraEje> lcle_datos;

		lsw_watch      = Logger.getNewStopWatch();
		lcle_datos     = getParameterBusiness().findAllLetraEjeActivo();

		Logger.log(lsw_watch, "ParameterBean", "findAllLetraEjeActivo", null, null, null, lcle_datos);

		return lcle_datos;
	}

	/** {@inheritdoc} */
	public Collection<LibroAntiguoSistema> findAllLibroAntiguoSistema(boolean ab_b)
	    throws B2BException
	{
		StopWatch                       lsw_watch;
		Collection<LibroAntiguoSistema> lcc_datos;

		lsw_watch     = Logger.getNewStopWatch();

		lcc_datos = getParameterBusiness().findAllLibroAntiguoSistema(ab_b);

		Logger.log(lsw_watch, "ParameterBean", "findAllLibroAntiguoSistema", null, null, null, lcc_datos);

		return lcc_datos;
	}

	/** {@inheritdoc} */
	public Collection<LibroTestamento> findAllLibroTestamento()
	    throws B2BException
	{
		StopWatch                   lsw_watch;
		Collection<LibroTestamento> lclt_datos;

		lsw_watch     = Logger.getNewStopWatch();

		lclt_datos = getParameterBusiness().findAllLibroTestamento();

		Logger.log(lsw_watch, "ParameterBean", "findAllLibroTestamento", null, null, null, lclt_datos);

		return lclt_datos;
	}

	/** {@inheritdoc} */
	public Collection<LineaProduccion> findAllLineasProduccion()
	    throws B2BException
	{
		StopWatch                   lsw_watch;
		Collection<LineaProduccion> lclp_datos;

		lsw_watch     = Logger.getNewStopWatch();

		lclp_datos = getParameterBusiness().findAllLineasProduccion();

		Logger.log(lsw_watch, "ParameterBean", "findAllLineasProduccion", null, null, null, lclp_datos);

		return lclp_datos;
	}

	/** {@inheritdoc} */
	public Collection<MedidaArea> findAllMedidaArea()
	    throws B2BException
	{
		StopWatch              lsw_watch;
		Collection<MedidaArea> lcr_datos;

		lsw_watch     = Logger.getNewStopWatch();

		lcr_datos = getParameterBusiness().findAllMedidaArea();

		Logger.log(lsw_watch, "ParameterBean", "findAllMedidaArea", null, null, null, lcr_datos);

		return lcr_datos;
	}

	/**
	 * Método para encontrar todos los registros de la base de datos para la tabla SDB_PGN_MEDIO_RECAUDO.
	 *
	 * @return el valor de collection
	 * @throws B2BException de b 2 B exception
	 */
	public Collection<MedioRecaudo> findAllMedioRecaudo()
	    throws B2BException
	{
		StopWatch                lsw_watch;
		Collection<MedioRecaudo> lmr_datos;

		lsw_watch     = Logger.getNewStopWatch();

		lmr_datos = getParameterBusiness().findAllMedioRecaudo();

		Logger.log(lsw_watch, "ParameterBean", "findAllMedioRecaudo", null, null, null, lmr_datos);

		return lmr_datos;
	}

	/**
	 * Método para encontrar todos los registros de la base de datos para la tabla SDB_PGN_MENSAJE_RESPUESTA.
	 *
	 * @return el valor de collection
	 * @throws B2BException de b 2 B exception
	 */
	public Collection<MensajeRespuesta> findAllMensajeRespuesta()
	    throws B2BException
	{
		StopWatch                    lsw_watch;
		Collection<MensajeRespuesta> lmr_datos;

		lsw_watch     = Logger.getNewStopWatch();

		lmr_datos = getParameterBusiness().findAllMensajeRespuesta();

		Logger.log(lsw_watch, "ParameterBean", "findAllMensajeRespuesta", null, null, null, lmr_datos);

		return lmr_datos;
	}

	/** {@inheritdoc} */
	public Collection<MotivoTramite> findAllMotivoTramite()
	    throws B2BException
	{
		StopWatch                 lsw_watch;
		Collection<MotivoTramite> lcr_datos;

		lsw_watch     = Logger.getNewStopWatch();

		lcr_datos = getParameterBusiness().findAllMotivoTramite();

		Logger.log(lsw_watch, "ParameterBean", "findAllMotivoTramite", null, null, null, lcr_datos);

		return lcr_datos;
	}

	/** {@inheritdoc} */
	public Collection<Municipio> findAllMunicipios()
	    throws B2BException
	{
		StopWatch             lsw_watch;
		Collection<Municipio> lcc_datos;

		lsw_watch     = Logger.getNewStopWatch();

		lcc_datos = getParameterBusiness().findAllMunicipios();

		Logger.log(lsw_watch, "ParameterBean", "findAllMunicipios", null, null, null, lcc_datos);

		return lcc_datos;
	}

	/** {@inheritdoc} */
	public Collection<NaturalezaJuridica> findAllNaturalezaJuridica()
	    throws B2BException
	{
		StopWatch                      lsw_watch;
		Collection<NaturalezaJuridica> lcnj_datos;

		lsw_watch     = Logger.getNewStopWatch();

		lcnj_datos = getParameterBusiness().findNaturalezaJuridica();

		Logger.log(lsw_watch, "ParameterBean", "findAllNaturalezaJuridica", null, null, null, lcnj_datos);

		return lcnj_datos;
	}

	/** {@inheritdoc} */
	public Collection<NumeracionOficiosCirculo> findAllNumeracionOficiosCirculo()
	    throws B2BException
	{
		StopWatch                            lsw_watch;
		Collection<NumeracionOficiosCirculo> lcnoc_datos;

		lsw_watch     = Logger.getNewStopWatch();

		lcnoc_datos = getParameterBusiness().findAllNumeracionOficiosCirculo();

		Logger.log(lsw_watch, "ParameterBean", "findAllNumeracionOficiosCirculo", null, null, null, lcnoc_datos);

		return lcnoc_datos;
	}

	/** {@inheritdoc} */
	public Collection<OficinaOrigen> findAllOficinaOrigen()
	    throws B2BException
	{
		StopWatch                 lsw_watch;
		Collection<OficinaOrigen> lcr_datos;

		lsw_watch     = Logger.getNewStopWatch();

		lcr_datos = getParameterBusiness().findAllOficinaOrigen();

		Logger.log(lsw_watch, "ParameterBean", "findAllOficinaOrigen", null, null, null, lcr_datos);

		return lcr_datos;
	}

	/**
	 * Método para encontrar todos los datos de Opción.
	 *
	 * @param ab_b de tipo boolean
	 * @return Collección de opción conlos datos solicitados
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Collection<Opcion> findAllOpcion(boolean ab_b)
	    throws B2BException
	{
		StopWatch          lsw_watch;
		Collection<Opcion> lco_datos;

		lsw_watch     = Logger.getNewStopWatch();

		lco_datos = getParameterBusiness().findAllOpcion(ab_b);

		Logger.log(lsw_watch, "ParameterBean", "findAllOpcion", null, null, null, lco_datos);

		return lco_datos;
	}

	/** {@inheritdoc} */
	public Collection<OpcionEtapa> findAllOpcionEtapa()
	    throws B2BException
	{
		StopWatch               lsw_watch;
		Collection<OpcionEtapa> lcoe_datos;

		lsw_watch     = Logger.getNewStopWatch();

		lcoe_datos = getParameterBusiness().findAllOpcionEtapa();

		Logger.log(lsw_watch, "ParameterBean", "findAllOpcionEtapa", null, null, null, lcoe_datos);

		return lcoe_datos;
	}

	/** {@inheritdoc} */
	public Collection<Origen> findAllOrigen()
	    throws B2BException
	{
		StopWatch          lsw_watch;
		Collection<Origen> lco_datos;

		lsw_watch     = Logger.getNewStopWatch();

		lco_datos = getParameterBusiness().findAllOrigen();

		Logger.log(lsw_watch, "ParameterBean", "findAllOrigen", null, null, null, lco_datos);

		return lco_datos;
	}

	/** {@inheritdoc} */
	public Collection<Pais> findAllPaises()
	    throws B2BException
	{
		StopWatch        lsw_watch;
		Collection<Pais> lcc_datos;

		lsw_watch     = Logger.getNewStopWatch();

		lcc_datos = getParameterBusiness().findAllPaises();

		Logger.log(lsw_watch, "ParameterBean", "findAllPaises", null, null, null, lcc_datos);

		return lcc_datos;
	}

	/** {@inheritdoc} */
	public Collection<ParteInteresada> findAllParteInteresadas()
	    throws B2BException
	{
		StopWatch                   lsw_watch;
		Collection<ParteInteresada> lcc_datos;

		lsw_watch     = Logger.getNewStopWatch();

		lcc_datos = getParameterBusiness().findAllParteInteresadas();

		Logger.log(lsw_watch, "ParameterBean", "findAllParteInteresadas", null, null, null, lcc_datos);

		return lcc_datos;
	}

	/** {@inheritdoc} */
	public Collection<Plantilla> findAllPlantilla()
	    throws B2BException
	{
		StopWatch             lsw_watch;
		Collection<Plantilla> lcp_datos;

		lsw_watch     = Logger.getNewStopWatch();

		lcp_datos = getParameterBusiness().findAllPlantilla();

		Logger.log(lsw_watch, "ParameterBean", "findAllPlantilla", null, null, null, lcp_datos);

		return lcp_datos;
	}

	/** {@inheritdoc} */
	public Collection<PlantillaComunicacion> findAllPlantillaComunicacion(
	    String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch                         lsw_watch;
		Collection<PlantillaComunicacion> lcp_datos;

		lsw_watch     = Logger.getNewStopWatch();

		lcp_datos = getParameterBusiness().findAllPlantillaComunicacion();

		Logger.log(
		    lsw_watch, "ParameterBean", "findAllPlantillaComunicacion", as_userId, as_localIp, as_remoteIp, lcp_datos
		);

		return lcp_datos;
	}

	/** {@inheritdoc} */
	public Collection<PlantillaNotificacion> findAllPlantillaNotificacion(
	    String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch                         lsw_watch;
		Collection<PlantillaNotificacion> lcp_datos;

		lsw_watch     = Logger.getNewStopWatch();

		lcp_datos = getParameterBusiness().findAllPlantillaNotificacion();

		Logger.log(
		    lsw_watch, "ParameterBean", "findAllPlantillaNotificacion", as_userId, as_localIp, as_remoteIp, lcp_datos
		);

		return lcp_datos;
	}

	/**
	 * Metodo de consulta para encontrar todos los porceso canal.
	 *
	 * @return el valor de collection
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Collection<ProcesoCanal> findAllProcesoCanal()
	    throws B2BException
	{
		StopWatch                lsw_watch;
		Collection<ProcesoCanal> lcr_datos;

		lsw_watch     = Logger.getNewStopWatch();

		lcr_datos = getParameterBusiness().findAllProcesoCanal();

		Logger.log(lsw_watch, "ParameterBean", "findAllProcesoCanal", null, null, null, lcr_datos);

		return lcr_datos;
	}

	/**
	 * Método para encontrar todos los datos de Proceso Canal por su identificador.
	 *
	 * @param ls_idProceso de ls id proceso
	 * @param ls_idSubProceso de ls id sub proceso
	 * @param ls_idTipoCanalOrigen de ls id tipo canal origen
	 * @return proceso Canal con el regsitro solicitado
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public ProcesoCanal findAllProcesoCanalById(
	    String ls_idProceso, String ls_idSubProceso, String ls_idTipoCanalOrigen
	)
	    throws B2BException
	{
		ProcesoCanal lcpc_return;
		StopWatch    lsw_watch;

		lsw_watch     = Logger.getNewStopWatch();

		lcpc_return = getParameterBusiness().findAllProcesoCanalById(
			    ls_idProceso, ls_idSubProceso, ls_idTipoCanalOrigen
			);

		Logger.log(lsw_watch, "ParameterBean", "findAllProcesoCanalById", null, null, null, null);

		return lcpc_return;
	}

	/** {@inheritdoc} */
	public Collection<ProcesoConsulta> findAllProcesoConsulta()
	    throws B2BException
	{
		StopWatch                   lsw_watch;
		Collection<ProcesoConsulta> lcpc_datos;

		lsw_watch     = Logger.getNewStopWatch();

		lcpc_datos = getParameterBusiness().findAllProcesoConsulta();

		Logger.log(lsw_watch, "ParameterBean", "findAllProcesoConsulta", null, null, null, lcpc_datos);

		return lcpc_datos;
	}

	/** {@inheritdoc} */
	public Collection<ProcesoAutomatico> findAllProcesosAutomaticos(boolean ab_b)
	    throws B2BException
	{
		StopWatch                     lsw_watch;
		Collection<ProcesoAutomatico> lcc_datos;

		lsw_watch     = Logger.getNewStopWatch();

		lcc_datos = getParameterBusiness().findAllProcesosAutomaticos(ab_b);

		Logger.log(lsw_watch, "ParameterBean", "findAllProcesosAutomaticos", null, null, null, lcc_datos);

		return lcc_datos;
	}

	/**
	 * Método para encontrar todos los registros de la base de datos para la tabla SDB_PGN_PUNTO_RECAUDO.
	 *
	 * @return el valor de collection
	 * @throws B2BException de b 2 B exception
	 */
	public Collection<PuntoRecaudo> findAllPuntoRecaudo()
	    throws B2BException
	{
		StopWatch                lsw_watch;
		Collection<PuntoRecaudo> lpr_datos;

		lsw_watch     = Logger.getNewStopWatch();

		lpr_datos = getParameterBusiness().findAllPuntoRecaudo();

		Logger.log(lsw_watch, "ParameterBean", "findAllPuntoRecaudo", null, null, null, lpr_datos);

		return lpr_datos;
	}

	/**
	 * Método para encontrar todos los registros de la base de datos para la tabla SDB_PGN_PUNTO_RECAUDO_TIPO_RECAUDO.
	 *
	 * @return el valor de collection
	 * @throws B2BException de b 2 B exception
	 */
	public Collection<PuntoRecaudoTipoRecaudo> findAllPuntoRecaudoTipoRecaudo()
	    throws B2BException
	{
		StopWatch                           lsw_watch;
		Collection<PuntoRecaudoTipoRecaudo> lcprtr_datos;

		lsw_watch     = Logger.getNewStopWatch();

		lcprtr_datos = getParameterBusiness().findAllPuntoRecaudoTipoRecaudo();

		Logger.log(lsw_watch, "ParameterBean", "findAllPuntoRecaudoTipoRecaudo", null, null, null, lcprtr_datos);

		return lcprtr_datos;
	}

	/** {@inheritdoc} */
	public Collection<RangoCuantia> findAllRangoCuantia(String as_idUsuario, String as_localIp, String as_remoteIp)
	    throws B2BException
	{
		StopWatch                lsw_watch;
		Collection<RangoCuantia> lrc_datos;

		lsw_watch     = Logger.getNewStopWatch();

		lrc_datos = getParameterBusiness().findAllRangoCuantia();

		Logger.log(lsw_watch, "ParameterBean", "findAllRangoCuantia", null, null, null, lrc_datos);

		return lrc_datos;
	}

	/** {@inheritdoc} */
	public Collection<Regional> findAllRegionales()
	    throws B2BException
	{
		StopWatch            lsw_watch;
		Collection<Regional> lcc_datos;

		lsw_watch     = Logger.getNewStopWatch();

		lcc_datos = getParameterBusiness().findAllRegionales();

		Logger.log(lsw_watch, "ParameterBean", "findAllRegionales", null, null, null, lcc_datos);

		return lcc_datos;
	}

	/** {@inheritdoc} */
	public Collection<ReglaNegocio> findAllReglaNegocio(String as_userId, String as_localIp, String as_remoteIp)
	    throws B2BException
	{
		StopWatch                lsw_watch;
		Collection<ReglaNegocio> lcrn_datos;

		lsw_watch     = Logger.getNewStopWatch();

		lcrn_datos = getParameterBusiness().findAllReglaNegocio();

		Logger.log(lsw_watch, "ParameterBean", "findAllReglaNegocio", as_userId, as_localIp, as_remoteIp, lcrn_datos);

		return lcrn_datos;
	}

	/** {@inheritdoc} */
	public Collection<Reintentos> findAllReintentos()
	    throws B2BException
	{
		StopWatch              lsw_watch;
		Collection<Reintentos> lcr_datos;

		lsw_watch     = Logger.getNewStopWatch();

		lcr_datos = getParameterBusiness().findAllReintentos();

		Logger.log(lsw_watch, "ParameterBean", "findAllReintentos", null, null, null, lcr_datos);

		return lcr_datos;
	}

	/** {@inheritdoc} */
	public Collection<Reportes> findAllReportes(String as_idUsuario, String as_localIp, String as_remoteIp)
	    throws B2BException
	{
		StopWatch            lsw_watch;
		Collection<Reportes> lrc_datos;

		lsw_watch     = Logger.getNewStopWatch();

		lrc_datos = getParameterBusiness().findAllReportes();

		Logger.log(lsw_watch, "ParameterBean", "findAllReportes", null, null, null, lrc_datos);

		return lrc_datos;
	}

	/**
	 * Método para encontrar todos los datos de la RolOpcion.
	 *
	 * @return Collección de rolOpción con todos los datos solicitados
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Collection<RolOpcion> findAllRolOpcion()
	    throws B2BException
	{
		StopWatch             lsw_watch;
		Collection<RolOpcion> lcro_datos;

		lsw_watch     = Logger.getNewStopWatch();

		lcro_datos = getParameterBusiness().findAllRolOpcion();

		Logger.log(lsw_watch, "ParameterBean", "findAllRolOpcion", null, null, null, lcro_datos);

		return lcro_datos;
	}

	/** {@inheritdoc} */
	public Collection<Rol> findAllRols(boolean ab_b)
	    throws B2BException
	{
		StopWatch       lsw_watch;
		Collection<Rol> lcr_datos;

		lsw_watch     = Logger.getNewStopWatch();

		lcr_datos = getParameterBusiness().findAllRols(ab_b);

		Logger.log(lsw_watch, "ParameterBean", "findAllRols", null, null, null, lcr_datos);

		return lcr_datos;
	}

	/** {@inheritdoc} */
	public byte[] findAllRtf()
	    throws B2BException
	{
		StopWatch lsw_watch;
		byte[]    lba_datos;

		lsw_watch     = Logger.getNewStopWatch();

		lba_datos = getParameterBusiness().findAllRtf();

		Logger.log(lsw_watch, "ParameterBean", "findAllRtf", null, null, null, null);

		return lba_datos;
	}

	/**
	 * Método para encontrar todos los registros de la base de datos para la tabla SDB_PGN_SUCURSAL_CANAL_ORIGEN_SERVICIO.
	 *
	 * @return el valor de collection
	 * @throws B2BException de b 2 B exception
	 */
	public Collection<SucursalCanalOrigenServicio> findAllSucursalCanalOrigenServicio()
	    throws B2BException
	{
		StopWatch                               lsw_watch;
		Collection<SucursalCanalOrigenServicio> lcscos_datos;

		lsw_watch     = Logger.getNewStopWatch();

		lcscos_datos = getParameterBusiness().findAllSucursalCanalOrigenServicio();

		Logger.log(lsw_watch, "ParameterBean", "findAllSucursalCanalOrigenServicio", null, null, null, lcscos_datos);

		return lcscos_datos;
	}

	/** {@inheritdoc} */
	public Collection<TarifaAlerta> findAllTarifaAlertaActivo()
	    throws B2BException
	{
		StopWatch                lsw_watch;
		Collection<TarifaAlerta> lctpa_datos;

		lsw_watch     = Logger.getNewStopWatch();

		lctpa_datos = getParameterBusiness().findAllTarifaAlertaActivo();

		Logger.log(lsw_watch, "ParameterBean", "findAllTarifaAlertaActivo", null, null, null, lctpa_datos);

		return lctpa_datos;
	}

	/** {@inheritdoc} */
	public Collection<TarifaFija> findAllTarifaFija(String as_idUsuario, String as_localIp, String as_remoteIp)
	    throws B2BException
	{
		StopWatch              lsw_watch;
		Collection<TarifaFija> lrc_datos;

		lsw_watch     = Logger.getNewStopWatch();

		lrc_datos = getParameterBusiness().findAllTarifaFija();

		Logger.log(lsw_watch, "ParameterBean", "findAllTarifaFija", null, null, null, lrc_datos);

		return lrc_datos;
	}

	/** {@inheritdoc} */
	public Collection<TipoActo> findAllTipoActo(String as_idUsuario, String as_localIp, String as_remoteIp)
	    throws B2BException
	{
		StopWatch            lsw_watch;
		Collection<TipoActo> lcc_datos;

		lsw_watch     = Logger.getNewStopWatch();

		lcc_datos = getParameterBusiness().findAllTiposActo();

		Logger.log(lsw_watch, "ParameterBean", "findAllTipoActo", as_idUsuario, as_localIp, as_remoteIp, lcc_datos);

		return lcc_datos;
	}

	/** {@inheritdoc} */
	public Collection<TipoActoCondicion> findAllTipoActoCondicion(
	    String as_idUsuario, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch                     lsw_watch;
		Collection<TipoActoCondicion> lcc_datos;

		lsw_watch     = Logger.getNewStopWatch();

		lcc_datos = getParameterBusiness().findAllTipoActoCondicion();

		Logger.log(
		    lsw_watch, "ParameterBean", "findAllTipoActoCondicion", as_idUsuario, as_localIp, as_remoteIp, lcc_datos
		);

		return lcc_datos;
	}

	/** {@inheritdoc} */
	public Collection<TipoActoEjecutoria> findAllTipoActoEjecutoria(
	    String as_idUsuario, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch                      lsw_watch;
		Collection<TipoActoEjecutoria> lrc_datos;

		lsw_watch     = Logger.getNewStopWatch();

		lrc_datos = getParameterBusiness().findAllTipoActoEjecutoria();

		Logger.log(lsw_watch, "ParameterBean", "findAllTipoActoEjecutoria", null, null, null, lrc_datos);

		return lrc_datos;
	}

	/**
	 * Método de consulta para obtener todos los datos de tipo Acto prohibición.
	 *
	 * @return Collección de tipo acto prohibición con los datos solicitados.
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Collection<TipoActoProhibicion> findAllTipoActoProhibicion()
	    throws B2BException
	{
		StopWatch                       lsw_watch;
		Collection<TipoActoProhibicion> lctap_datos;

		lsw_watch     = Logger.getNewStopWatch();

		lctap_datos = getParameterBusiness().findAllTipoActoProhibicion();

		Logger.log(lsw_watch, "ParameterBean", "findAllTipoActoProhibicion", null, null, null, lctap_datos);

		return lctap_datos;
	}

	/**
	 * Método de consulta detallada para encontrar los registros de tipo acto prohibición por su indicativo.
	 *
	 * @param as_idTipoActoProhibicion de as id tipo acto prohibicion
	 * @return TipoActoProhibicion con los datos solicitados.
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public TipoActoProhibicion findAllTipoActoProhibicionById(String as_idTipoActoProhibicion)
	    throws B2BException
	{
		TipoActoProhibicion lcc_return;
		StopWatch           lsw_watch;

		lsw_watch     = Logger.getNewStopWatch();

		lcc_return = getParameterBusiness().findAllTipoActoProhibicionById(as_idTipoActoProhibicion);

		Logger.log(lsw_watch, "ParameterBean", "findAllTipoActoProhibicionById", null, null, null, null);

		return lcc_return;
	}

	/** {@inheritdoc} */
	@Override
	public Collection<TipoAdquisicion> findAllTipoAdquisicion()
	    throws B2BException
	{
		StopWatch                   lsw_watch;
		Collection<TipoAdquisicion> lcr_datos;

		lsw_watch     = Logger.getNewStopWatch();

		lcr_datos = getParameterBusiness().findAllTipoAdquisicion();

		Logger.log(lsw_watch, "ParameterBean", "findAllTipoAdquisicion", null, null, null, lcr_datos);

		return lcr_datos;
	}

	/** {@inheritdoc} */
	public Collection<TipoAdquisicion> findAllTipoAdquisicion(String as_s)
	    throws B2BException
	{
		return null;
	}

	/**
	 * Método para encontrar todos los registros de la base de datos para la tabla SDB_PGN_TIPO_AREA.
	 *
	 * @return el valor de collection
	 * @throws B2BException de b 2 B exception
	 */
	public Collection<TipoArea> findAllTipoArea()
	    throws B2BException
	{
		StopWatch            lsw_watch;
		Collection<TipoArea> lcta_datos;

		lsw_watch     = Logger.getNewStopWatch();

		lcta_datos = getParameterBusiness().findAllTipoArea();

		Logger.log(lsw_watch, "ParameterBean", "findAllTipoArea", null, null, null, lcta_datos);

		return lcta_datos;
	}

	/**
	 * Método para encontrar todos los registros de la base de datos para la tabla SDB_PGN_TIPO_CANAL_ORIGEN.
	 *
	 * @return el valor de collection
	 * @throws B2BException de b 2 B exception
	 */
	public Collection<TipoCanalOrigen> findAllTipoCanalOrigen()
	    throws B2BException
	{
		StopWatch                   lsw_watch;
		Collection<TipoCanalOrigen> ltco_datos;

		lsw_watch     = Logger.getNewStopWatch();

		ltco_datos = getParameterBusiness().findAllTipoCanalOrigen();

		Logger.log(lsw_watch, "ParameterBean", "findAllTipoCanalOrigen", null, null, null, ltco_datos);

		return ltco_datos;
	}

	/** {@inheritdoc} */
	public Collection<TipoCriterioBusquedaPGN> findAllTipoCriterioBusquedaPGN()
	    throws B2BException
	{
		StopWatch                           lsw_watch;
		Collection<TipoCriterioBusquedaPGN> lctcb_datos;

		lsw_watch     = Logger.getNewStopWatch();

		lctcb_datos = getParameterBusiness().findAllTipoCriterioBusquedaPGN();

		Logger.log(lsw_watch, "ParameterBean", "findAllTipoCriterioBusquedaPGN", null, null, null, lctcb_datos);

		return lctcb_datos;
	}

	/** {@inheritdoc} */
	public Collection<TipoDecisionRecurso> findAllTipoDecisionRecurso(
	    String as_idUsuario, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch                       lsw_watch;
		Collection<TipoDecisionRecurso> lrc_datos;

		lsw_watch     = Logger.getNewStopWatch();

		lrc_datos = getParameterBusiness().findAllTipoDecisionRecurso();

		Logger.log(lsw_watch, "ParameterBean", "findAllTipoDecisionRecurso", null, null, null, lrc_datos);

		return lrc_datos;
	}

	/**
	 * método de consulta de tipoDocumental para obtener todos sus registros.
	 *
	 * @return coleccion de TipoDocumental con la información solicitada
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Collection<TipoDocumental> findAllTipoDocumental()
	    throws B2BException
	{
		StopWatch                  lsw_watch;
		Collection<TipoDocumental> lcr_datos;

		lsw_watch     = Logger.getNewStopWatch();

		lcr_datos = getParameterBusiness().findAllTipoDocumental();

		Logger.log(lsw_watch, "ParameterBean", "findAllTipoDocumental", null, null, null, lcr_datos);

		return lcr_datos;
	}

	/**
	 * método de consulta de tipoDocumentalActo para obtener todos sus registros.
	 *
	 * @return coleccion de TipoDocumentalActo con la información solicitada
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Collection<TipoDocumentalActo> findAllTipoDocumentalActo()
	    throws B2BException
	{
		StopWatch                      lsw_watch;
		Collection<TipoDocumentalActo> lctap_datos;

		lsw_watch     = Logger.getNewStopWatch();

		lctap_datos = getParameterBusiness().findAllTipoDocumentalActo();

		Logger.log(lsw_watch, "ParameterBean", "findAllTipoActoProhibicion", null, null, null, lctap_datos);

		return lctap_datos;
	}

	/**
	 * método de consulta de tipoDocumentalActo para obtener todos sus registros por su indicativo.
	 *
	 * @param as_idTipoDocumentalActo de as id tipo documental acto
	 * @return TipoDocumentalActo con la información solicitada
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public TipoDocumentalActo findAllTipoDocumentalActoById(long as_idTipoDocumentalActo)
	    throws B2BException
	{
		TipoDocumentalActo lcc_return;
		StopWatch          lsw_watch;

		lsw_watch     = Logger.getNewStopWatch();

		lcc_return = getParameterBusiness().findAllTipoDocumentalActoById(as_idTipoDocumentalActo);

		Logger.log(lsw_watch, "ParameterBean", "findAllTipoActoProhibicionById", null, null, null, null);

		return lcc_return;
	}

	/**
	 * método de consulta de tipoDocumental para obtener todos sus registros por su indicativo.
	 *
	 * @param as_idTipoDocumental de as id tipo documental
	 * @return TipoDocumental con la información solicitada
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public TipoDocumental findAllTipoDocumentalById(String as_idTipoDocumental)
	    throws B2BException
	{
		TipoDocumental lcc_return;
		StopWatch      lsw_watch;

		lsw_watch     = Logger.getNewStopWatch();

		lcc_return = getParameterBusiness().findAllTipoDocumentalById(as_idTipoDocumental);

		Logger.log(lsw_watch, "ParameterBean", "findAllTipoDocumentalById", null, null, null, null);

		return lcc_return;
	}

	/** {@inheritdoc} */
	@Override
	public Collection<TipoDocumentoPublico> findAllTipoDocumentoPublico()
	    throws B2BException
	{
		StopWatch                        lsw_watch;
		Collection<TipoDocumentoPublico> lcr_datos;

		lsw_watch     = Logger.getNewStopWatch();

		lcr_datos = getParameterBusiness().findAllTipoDocumentoPublico();

		Logger.log(lsw_watch, "ParameterBean", "findAllTipoDocumentoPublico", null, null, null, lcr_datos);

		return lcr_datos;
	}

	/** {@inheritdoc} */
	public Collection<TipoDocumentoPublico> findAllTipoDocumentoPublico(String as_s)
	    throws B2BException
	{
		StopWatch                        lsw_watch;
		Collection<TipoDocumentoPublico> lcr_datos;

		lsw_watch     = Logger.getNewStopWatch();

		lcr_datos = getParameterBusiness().findAllTipoDocumentoPublico(as_s);

		Logger.log(lsw_watch, "ParameterBean", "findAllTipoDocumentoPublico", null, null, null, lcr_datos);

		return lcr_datos;
	}

	/**
	 * Método para encontrar todos los registros de la base de datos para la tabla SDB_PNG_TIPO_EJE.
	 *
	 * @return el valor de collection
	 * @throws B2BException de b 2 B exception
	 */
	public Collection<TipoEje> findAllTipoEje()
	    throws B2BException
	{
		StopWatch           lsw_watch;
		Collection<TipoEje> lcte_datos;

		lsw_watch     = Logger.getNewStopWatch();

		lcte_datos = getParameterBusiness().findAllTiposEje();

		Logger.log(lsw_watch, "ParameterBean", "findAllTipoEje", null, null, null, lcte_datos);

		return lcte_datos;
	}

	/** {@inheritdoc} */
	public Collection<TipoEstadoLiquidacion> findAllTipoEstadoLiquidacion()
	    throws B2BException
	{
		StopWatch                         lsw_watch;
		Collection<TipoEstadoLiquidacion> ltel_datos;

		lsw_watch     = Logger.getNewStopWatch();

		ltel_datos = getParameterBusiness().findAllTipoEstadoLiquidacion();

		Logger.log(lsw_watch, "ParameterBean", "findAllTipoEstadoLiquidacion", null, null, null, ltel_datos);

		return ltel_datos;
	}

	/** {@inheritdoc} */
	public Collection<TipoIntegracionGobernacion> findAllTipoIntegracionGobernacion(boolean ab_b)
	    throws B2BException
	{
		StopWatch                              lsw_watch;
		Collection<TipoIntegracionGobernacion> lcc_datos;

		lsw_watch     = Logger.getNewStopWatch();

		lcc_datos = getParameterBusiness().findAllTipoIntegracionGobernacion(ab_b);

		Logger.log(lsw_watch, "ParameterBean", "findAllTipoIntegracionGobernacion", null, null, null, lcc_datos);

		return lcc_datos;
	}

	/** {@inheritdoc} */
	public Collection<TipoOperacion> findAllTipoOperacion()
	    throws B2BException
	{
		StopWatch                 lsw_watch;
		Collection<TipoOperacion> lcto_datos;

		lsw_watch     = Logger.getNewStopWatch();

		lcto_datos = getParameterBusiness().findAllTipoOperacion();

		Logger.log(lsw_watch, "ParameterBean", "findAllTipoOperacion", null, null, null, lcto_datos);

		return lcto_datos;
	}

	/** {@inheritdoc} */
	public Collection<TipoPersona> findAllTipoPersona()
	    throws B2BException
	{
		StopWatch               lsw_watch;
		Collection<TipoPersona> lctp_datos;

		lsw_watch     = Logger.getNewStopWatch();

		lctp_datos = getParameterBusiness().findAllTipoPersona();

		Logger.log(lsw_watch, "ParameterBean", "findAllTipoPersona", null, null, null, lctp_datos);

		return lctp_datos;
	}

	/** {@inheritdoc} */
	public Collection<PredioTipo> findAllTipoPredio()
	    throws B2BException
	{
		StopWatch              lsw_watch;
		Collection<PredioTipo> lcpt_datos;

		lsw_watch     = Logger.getNewStopWatch();

		lcpt_datos = getParameterBusiness().findAllTipoPredio();

		Logger.log(lsw_watch, "ParameterBean", "findAllTipoSuelo", null, null, null, lcpt_datos);

		return lcpt_datos;
	}

	/** {@inheritdoc} */
	public Collection<TipoRrr> findAllTipoRRR()
	    throws B2BException
	{
		StopWatch           lsw_watch;
		Collection<TipoRrr> lctr_datos;

		lsw_watch     = Logger.getNewStopWatch();

		lctr_datos = getParameterBusiness().findAllTipoRRR();

		Logger.log(lsw_watch, "ParameterBean", "findAllTipoRRR", null, null, null, lctr_datos);

		return lctr_datos;
	}

	/**
	 * Método para encontrar todos los registros de la base de datos para la tabla SDB_PGN_TIPO_RECAUDO.
	 *
	 * @return el valor de collection
	 * @throws B2BException de b 2 B exception
	 */
	public Collection<TipoRecaudo> findAllTipoRecaudo()
	    throws B2BException
	{
		StopWatch               lsw_watch;
		Collection<TipoRecaudo> lctr_datos;

		lsw_watch     = Logger.getNewStopWatch();

		lctr_datos = getParameterBusiness().findAllTipoRecaudo();

		Logger.log(lsw_watch, "ParameterBean", "findAllTipoRecaudo", null, null, null, lctr_datos);

		return lctr_datos;
	}

	/** {@inheritdoc} */
	public Collection<TipoRecepcion> findAllTipoRecepcion()
	    throws B2BException
	{
		StopWatch                 lsw_watch;
		Collection<TipoRecepcion> lcr_datos;

		lsw_watch     = Logger.getNewStopWatch();

		lcr_datos = getParameterBusiness().findAllTipoRecepcion();

		Logger.log(lsw_watch, "ParameterBean", "findAllTipoRecepcion", null, null, null, lcr_datos);

		return lcr_datos;
	}

	/** {@inheritdoc} */
	public Collection<TipoRecepcion> findAllTipoRecepcionOrdenado()
	    throws B2BException
	{
		StopWatch                 lsw_watch;
		Collection<TipoRecepcion> lcr_datos;

		lsw_watch     = Logger.getNewStopWatch();

		lcr_datos = getParameterBusiness().findAllTipoRecepcionOrdenado();

		Logger.log(lsw_watch, "ParameterBean", "findAllTipoRecepcionOrdenado", null, null, null, lcr_datos);

		return lcr_datos;
	}

	/** {@inheritdoc} */
	public Collection<TipoRecurso> findAllTipoRecurso(String as_idUsuario, String as_localIp, String as_remoteIp)
	    throws B2BException
	{
		StopWatch               lsw_watch;
		Collection<TipoRecurso> lrc_datos;

		lsw_watch     = Logger.getNewStopWatch();

		lrc_datos = getParameterBusiness().findAllTipoRecurso();

		Logger.log(lsw_watch, "ParameterBean", "findAllTipoRecurso", null, null, null, lrc_datos);

		return lrc_datos;
	}

	/** {@inheritdoc} */
	public Collection<TipoRecurso> findAllTipoRecursoByActivo(
	    String as_activo, String as_idUsuario, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch               lsw_watch;
		Collection<TipoRecurso> lrc_datos;

		lsw_watch     = Logger.getNewStopWatch();

		lrc_datos = getParameterBusiness().findAllTipoRecursoByActivo(as_activo);

		Logger.log(
		    lsw_watch, "ParameterBean", "findAllTipoRecursoByActivo", as_idUsuario, as_localIp, as_remoteIp, lrc_datos
		);

		return lrc_datos;
	}

	/** {@inheritdoc} */
	public Collection<TipoRequiereMatricula> findAllTipoRequiereMatricula()
	    throws B2BException
	{
		StopWatch                         lsw_watch;
		Collection<TipoRequiereMatricula> ltrm_datos;

		lsw_watch     = Logger.getNewStopWatch();

		ltrm_datos = getParameterBusiness().findAllTipoRequiereMatricula();

		Logger.log(lsw_watch, "ParameterBean", "findAllTipoRequiereMatricula", null, null, null, ltrm_datos);

		return ltrm_datos;
	}

	/** {@inheritdoc} */
	public Collection<TipoUsoSuelo> findAllTipoUsoSuelo()
	    throws B2BException
	{
		StopWatch                lsw_watch;
		Collection<TipoUsoSuelo> lctus_datos;

		lsw_watch     = Logger.getNewStopWatch();

		lctus_datos = getParameterBusiness().findAllTipoUsoSuelo();

		Logger.log(lsw_watch, "ParameterBean", "findAllTipoUsoSuelo", null, null, null, lctus_datos);

		return lctus_datos;
	}

	/** {@inheritdoc} */
	public Collection<TipoActo> findAllTiposActoAplicaDesborde(
	    String as_idUsuario, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch            lsw_watch;
		Collection<TipoActo> lcc_datos;

		lsw_watch     = Logger.getNewStopWatch();

		lcc_datos = getParameterBusiness().findAllTiposActoAplicaDesborde();

		Logger.log(
		    lsw_watch, "ParameterBean", "findAllTiposActoAplicaDesborde", as_idUsuario, as_localIp, as_remoteIp,
		    lcc_datos
		);

		return lcc_datos;
	}

	/** {@inheritdoc} */
	public Collection<TipoAnotacion> findAllTiposAnotacion(String as_idUsuario, String as_localIp, String as_remoteIp)
	    throws B2BException
	{
		StopWatch                 lsw_watch;
		Collection<TipoAnotacion> lcta_datos;

		lsw_watch     = Logger.getNewStopWatch();

		lcta_datos = getParameterBusiness().findAllTiposAnotacion();

		Logger.log(
		    lsw_watch, "ParameterBean", "findAllTiposAnotacion", as_idUsuario, as_localIp, as_remoteIp, lcta_datos
		);

		return lcta_datos;
	}

	/** {@inheritdoc} */
	public Collection<TipoCausal> findAllTiposCausal(String as_idUsuario, String as_localIp, String as_remoteIp)
	    throws B2BException
	{
		StopWatch              lsw_watch;
		Collection<TipoCausal> lctc_datos;

		lsw_watch     = Logger.getNewStopWatch();

		lctc_datos = getParameterBusiness().findAllTiposCausal();

		Logger.log(lsw_watch, "ParameterBean", "findAllTiposCausal", as_idUsuario, as_localIp, as_remoteIp, lctc_datos);

		return lctc_datos;
	}

	/** {@inheritdoc} */
	public Collection<TipoEje> findAllTiposEje(String as_idUsuario, String as_localIp, String as_remoteIp)
	    throws B2BException
	{
		StopWatch           lsw_watch;
		Collection<TipoEje> lcte_datos;

		lsw_watch     = Logger.getNewStopWatch();

		lcte_datos = getParameterBusiness().findAllTiposEje();

		Logger.log(lsw_watch, "ParameterBean", "findAllTiposEje", as_idUsuario, as_localIp, as_remoteIp, lcte_datos);

		return lcte_datos;
	}

	/** {@inheritdoc} */
	public Collection<TipoEstadoSolicitud> findAllTiposEstadoSolicitud(
	    String as_idUsuario, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch                       lsw_watch;
		Collection<TipoEstadoSolicitud> lcta_datos;

		lsw_watch     = Logger.getNewStopWatch();

		lcta_datos = getParameterBusiness().findAllTiposEstadoSolicitud();

		Logger.log(
		    lsw_watch, "ParameterBean", "findAllTiposEstadoSolicitud", as_idUsuario, as_localIp, as_remoteIp, lcta_datos
		);

		return lcta_datos;
	}

	/** {@inheritdoc} */
	public Collection<TipoTestamento> findAllTiposTestamento(
	    String as_idUsuario, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch                  lsw_watch;
		Collection<TipoTestamento> lcta_datos;

		lsw_watch     = Logger.getNewStopWatch();

		lcta_datos = getParameterBusiness().findAllTiposTestamento();

		Logger.log(
		    lsw_watch, "ParameterBean", "findAllTiposTestamento", as_idUsuario, as_localIp, as_remoteIp, lcta_datos
		);

		return lcta_datos;
	}

	/** {@inheritdoc} */
	public Collection<TopologiaRegla> findAllTopologiaRegla(String as_userId, String as_localIp, String as_remoteIp)
	    throws B2BException
	{
		StopWatch                  lsw_watch;
		Collection<TopologiaRegla> lctr_datos;

		lsw_watch     = Logger.getNewStopWatch();

		lctr_datos = getParameterBusiness().findAllTopologiaRegla();

		Logger.log(lsw_watch, "ParameterBean", "findAllTopologiaRegla", as_userId, as_localIp, as_remoteIp, lctr_datos);

		return lctr_datos;
	}

	/** {@inheritdoc} */
	public Collection<UnidadTiempoVencimiento> findAllUnidadTiempoVencimiento()
	    throws B2BException
	{
		StopWatch                           lsw_watch;
		Collection<UnidadTiempoVencimiento> lcutv_datos;

		lsw_watch     = Logger.getNewStopWatch();

		lcutv_datos = getParameterBusiness().findAllUnidadTiempoVencimiento();

		Logger.log(lsw_watch, "ParameterBean", "findAllUnidadTiempoVencimiento", null, null, null, lcutv_datos);

		return lcutv_datos;
	}

	/** {@inheritdoc} */
	public Collection<UsuarioCirculo> findAllUsuarioCirculos(
	    String as_idUsuario, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch                  lsw_watch;
		Collection<UsuarioCirculo> lcuc_datos;

		lsw_watch     = Logger.getNewStopWatch();

		lcuc_datos = getParameterBusiness().findAllUsuarioCirculos();

		Logger.log(
		    lsw_watch, "ParameterBean", "findAllUsuarioCirculos", as_idUsuario, as_localIp, as_remoteIp, lcuc_datos
		);

		return lcuc_datos;
	}

	/** {@inheritdoc} */
	public Collection<UsuarioEtapa> findAllUsuarioEtapas(String as_idUsuario, String as_localIp, String as_remoteIp)
	    throws B2BException
	{
		StopWatch                lsw_watch;
		Collection<UsuarioEtapa> lcue_datos;

		lsw_watch     = Logger.getNewStopWatch();

		lcue_datos = getParameterBusiness().findAllUsuarioEtapas();

		Logger.log(
		    lsw_watch, "ParameterBean", "findAllUsuarioEtapas", as_idUsuario, as_localIp, as_remoteIp, lcue_datos
		);

		return lcue_datos;
	}

	/** {@inheritdoc} */
	public Collection<UsuarioLinea> findAllUsuarioLineas(String as_idUsuario, String as_localIp, String as_remoteIp)
	    throws B2BException
	{
		StopWatch                lsw_watch;
		Collection<UsuarioLinea> lcc_datos;

		lsw_watch     = Logger.getNewStopWatch();

		lcc_datos = getParameterBusiness().findAllUsuarioLineas();

		Logger.log(
		    lsw_watch, "ParameterBean", "findAllUsuarioLineas", as_idUsuario, as_localIp, as_remoteIp, lcc_datos
		);

		return lcc_datos;
	}

	/** {@inheritdoc} */
	public Collection<Vereda> findAllVeredas()
	    throws B2BException
	{
		StopWatch          lsw_watch;
		Collection<Vereda> lcc_datos;

		lsw_watch     = Logger.getNewStopWatch();

		lcc_datos = getParameterBusiness().findAllVeredas();

		Logger.log(lsw_watch, "ParameterBean", "findAllVeredas", null, null, null, lcc_datos);

		return lcc_datos;
	}

	/** {@inheritdoc} */
	public Collection<ZonaRegistral> findAllZonaRegistrales()
	    throws B2BException
	{
		StopWatch                 lsw_watch;
		Collection<ZonaRegistral> lcc_datos;

		lsw_watch     = Logger.getNewStopWatch();

		lcc_datos = getParameterBusiness().findAllZonaRegistrales();

		Logger.log(lsw_watch, "ParameterBean", "findAllZonaRegistrales", null, null, null, lcc_datos);

		return lcc_datos;
	}

	/**
	 * Método de transacciones con la base de datos para encontrar todos los registros activos de la tabla SDB_PGN_ZONA_REGISTRAL.
	 *
	 * @param as_idCirculo de as idCirculo
	 * @param ab_zona de ab zona
	 * @return el valor de collection
	 * @throws B2BException de b 2 B exception
	 */
	public Collection<ZonaRegistral> findAllZonaRegistralesActivos(String as_idCirculo, boolean ab_zona)
	    throws B2BException
	{
		StopWatch                 lsw_watch;
		Collection<ZonaRegistral> lcc_datos;

		lsw_watch     = Logger.getNewStopWatch();

		lcc_datos = getParameterBusiness().findAllZonaRegistralesActivos(as_idCirculo, ab_zona);

		Logger.log(lsw_watch, "ParameterBean", "findAllZonaRegistralesActivos", null, null, null, lcc_datos);

		return lcc_datos;
	}

	/**
	 * Método de transacciones con la base de datos para encontrar todos los registros activos de la tabla SDB_PGN_ZONA_REGISTRAL.
	 *
	 * @param aczr_czr de aczr czr
	 * @return el valor de collection
	 * @throws B2BException de b 2 B exception
	 */
	public Collection<ZonaRegistral> findAllZonaRegistralesAsociadas(Collection<ZonaRegistral> aczr_czr)
	    throws B2BException
	{
		StopWatch                 lsw_watch;
		Collection<ZonaRegistral> lcc_datos;

		lsw_watch     = Logger.getNewStopWatch();

		lcc_datos = getParameterBusiness().findAllZonaRegistralesAsociadas(aczr_czr);

		Logger.log(lsw_watch, "ParameterBean", "findAllZonaRegistralesAsociadas", null, null, null, lcc_datos);

		return lcc_datos;
	}

	/** {@inheritdoc} */
	public Documento findByIdDocumentoVersion(
	    String as_idDocumento, Long al_versionDoc, String as_idUsuario, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch lsw_watch;
		Documento ld_documento;

		lsw_watch     = Logger.getNewStopWatch();

		ld_documento = getParameterBusiness().findByIdDocumentoVersion(as_idDocumento, al_versionDoc);

		Logger.log(lsw_watch, "ParameterBean", "findByIdDocumentoVersion", as_idUsuario, as_localIp, as_remoteIp, null);

		return ld_documento;
	}

	/** {@inheritdoc} */
	public Collection<CalidadSolicitante> findCalidadSolicitante()
	    throws B2BException
	{
		StopWatch                      lsw_watch;
		Collection<CalidadSolicitante> lccs_datos;

		lsw_watch     = Logger.getNewStopWatch();

		lccs_datos = getParameterBusiness().findCalidadSolicitante();

		Logger.log(lsw_watch, "ParameterBean", "findCalidadSolicitante", null, null, null, lccs_datos);

		return lccs_datos;
	}

	/** {@inheritdoc} */
	public CalidadSolicitante findCalidadSolicitanteById(CalidadSolicitante accs_ccs)
	    throws B2BException
	{
		StopWatch          lsw_watch;
		CalidadSolicitante lccs_datos;

		lsw_watch     = Logger.getNewStopWatch();

		lccs_datos = getParameterBusiness().findCalidadSolicitanteById(accs_ccs);

		Logger.log(lsw_watch, "ParameterBean", "findCalidadSolicitanteById", null, null, null, null);

		return lccs_datos;
	}

	/**
	 * Método de consulta de CamposCriterioBsuqueda basado en su Identificador.
	 *
	 * @param as_tipoCriterioBusqueda de as tipo criterio busqueda
	 * @param as_campoCriterioBusqueda de as campo criterio busqueda
	 * @return el valor de campos consulta
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public CamposConsulta findCampoCriterioBusquedaById(
	    String as_tipoCriterioBusqueda, String as_campoCriterioBusqueda
	)
	    throws B2BException
	{
		CamposConsulta lcc_return;
		StopWatch      lsw_watch;

		lsw_watch     = Logger.getNewStopWatch();

		lcc_return = getParameterBusiness()
				             .findCampoCriterioBusquedaById(as_tipoCriterioBusqueda, as_campoCriterioBusqueda);

		Logger.log(lsw_watch, "ParameterBean", "findCampoCriterioBusquedaById", null, null, null, null);

		return lcc_return;
	}

	/** {@inheritdoc} */
	public CamposCertificado findCamposCertificadoById(
	    CamposCertificado acc_parametro, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch         lsw_watch;
		CamposCertificado lcc_datos;

		lsw_watch     = Logger.getNewStopWatch();
		lcc_datos     = getParameterBusiness().findCamposCertificadoById(acc_parametro);

		Logger.log(lsw_watch, "ParameterBean", "findCamposCertificadoById", as_userId, as_localIp, as_remoteIp, null);

		return lcc_datos;
	}

	/** {@inheritdoc} */
	public CamposConsulta findCamposConsultaById(CamposConsulta ama_ma)
	    throws B2BException
	{
		StopWatch      lsw_watch;
		CamposConsulta lcc_datos;

		lsw_watch     = Logger.getNewStopWatch();

		lcc_datos = getParameterBusiness().findCamposConsultaById(ama_ma);

		Logger.log(lsw_watch, "ParameterBean", "findAlertaNaturalezaJuridicaById", null, null, null, null);

		return lcc_datos;
	}

	/** {@inheritdoc} */
	public CamposCorreccion findCamposCorreccionById(
	    CamposCorreccion acc_parametro, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch        lsw_watch;
		CamposCorreccion lcc_datos;

		lsw_watch     = Logger.getNewStopWatch();
		lcc_datos     = getParameterBusiness().findCamposCorreccionById(acc_parametro);

		Logger.log(lsw_watch, "ParameterBean", "findCamposCorreccionById", as_userId, as_localIp, as_remoteIp, null);

		return lcc_datos;
	}

	/** {@inheritdoc} */
	public Canal findCanalById(Canal ac_c)
	    throws B2BException
	{
		StopWatch lsw_watch;
		Canal     lc_datos;

		lsw_watch     = Logger.getNewStopWatch();

		lc_datos = getParameterBusiness().findCanalById(ac_c);

		Logger.log(lsw_watch, "ParameterBean", "findCanalById", null, null, null, null);

		return lc_datos;
	}

	/**
	 * Método para encontrar todos los registros de la base de datos para la tabla SDB_PGN_CANAL_ORIGEN_SERVICIO
	 * que coincidan con un id específico.
	 *
	 * @param as_s de as s
	 * @return el valor de canal origen servicio
	 * @throws B2BException de b 2 B exception
	 */
	public CanalOrigenServicio findCanalOrigenServicioById(String as_s)
	    throws B2BException
	{
		StopWatch           lsw_watch;
		CanalOrigenServicio lcos_dato;

		lsw_watch     = Logger.getNewStopWatch();

		lcos_dato = getParameterBusiness().findCanalOrigenServicioById(as_s);

		Logger.log(lsw_watch, "ParameterBean", "findCanalOrigenServicioById", null, null, null, null);

		return lcos_dato;
	}

	/** {@inheritdoc} */
	public Catastro findCatastroById(Catastro ac_parametro, String as_userId, String as_localIp, String as_remoteIp)
	    throws B2BException
	{
		StopWatch lsw_watch;
		Catastro  lc_datos;

		lsw_watch     = Logger.getNewStopWatch();
		lc_datos      = getParameterBusiness().findCatastroById(ac_parametro);

		Logger.log(lsw_watch, "ParameterBean", "findCatastroById", as_userId, as_localIp, as_remoteIp, null);

		return lc_datos;
	}

	/**
	 * Método para encontrar todos los registros de la base de datos para la tabla SDB_PGN_CAUSAL_APROBACION_DEVOLUCION
	 * que coincidan con un id específico.
	 *
	 * @param acad_cad de acad cad
	 * @return el valor de causal aprobacion devolucion
	 * @throws B2BException de b 2 B exception
	 */
	public CausalAprobacionDevolucion findCausalAprobacionDevolucionById(CausalAprobacionDevolucion acad_cad)
	    throws B2BException
	{
		StopWatch                  lsw_watch;
		CausalAprobacionDevolucion lcr_datos;

		lsw_watch     = Logger.getNewStopWatch();

		lcr_datos = getParameterBusiness().findCausalAprobacionDevolucionById(acad_cad);

		Logger.log(lsw_watch, "ParameterBean", "findCausalAprobacionDevolucionById", null, null, null, null);

		return lcr_datos;
	}

	/** {@inheritdoc} */
	public CausalCorreccion findCausalCorreccionById(CausalCorreccion acad_cad)
	    throws B2BException
	{
		StopWatch        lsw_watch;
		CausalCorreccion lcr_datos;

		lsw_watch     = Logger.getNewStopWatch();

		lcr_datos = getParameterBusiness().findCausalCorreccionById(acad_cad);

		Logger.log(lsw_watch, "ParameterBean", "findCausalCorreccionById", null, null, null, null);

		return lcr_datos;
	}

	/** {@inheritdoc} */
	public CausalRechazoRecurso findCausalRechazoRecursoById(
	    CausalRechazoRecurso acrr_parametro, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch            lsw_watch;
		CausalRechazoRecurso lcrr_datos;

		lsw_watch      = Logger.getNewStopWatch();
		lcrr_datos     = getParameterBusiness().findCausalRechazoRecursoById(acrr_parametro);

		Logger.log(lsw_watch, "ParameterBean", "findCamposCorreccionById", as_userId, as_localIp, as_remoteIp, null);

		return lcrr_datos;
	}

	/** {@inheritdoc} */
	public CausalReimpresion findCausalReimpresionById(CausalReimpresion accr_ccr)
	    throws B2BException
	{
		StopWatch         lsw_watch;
		CausalReimpresion lccr_datos;

		lsw_watch     = Logger.getNewStopWatch();

		lccr_datos = getParameterBusiness().findCausalReimpresionById(accr_ccr);

		Logger.log(lsw_watch, "ParameterBean", "findCausalReimpresionById", null, null, null, null);

		return lccr_datos;
	}

	/** {@inheritdoc} */
	public Collection<CausalMayorValor> findCausalesMayorValorPorEstado(
	    String as_estado, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch                    lsw_watch;
		Collection<CausalMayorValor> lccmv_datos;

		lsw_watch     = Logger.getNewStopWatch();

		lccmv_datos = getParameterBusiness().findCausalesMayorValorPorEstado(as_estado);

		Logger.log(
		    lsw_watch, "ParameterBean", "findCausalesMayorValorPorEstado", as_userId, as_localIp, as_remoteIp,
		    lccmv_datos
		);

		return lccmv_datos;
	}

	/** {@inheritdoc} */
	public CirculoActAdmin findCirculoActAdminById(
	    CirculoActAdmin acaa_parametro, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch       lsw_watch;
		CirculoActAdmin lcaa_datos;

		lsw_watch      = Logger.getNewStopWatch();
		lcaa_datos     = getParameterBusiness().findCirculoActAdminById(acaa_parametro);

		Logger.log(lsw_watch, "ParameterBean", "findCirculoActAdminById", as_userId, as_localIp, as_remoteIp, null);

		return lcaa_datos;
	}

	/** {@inheritdoc} */
	public CirculoCatastro findCirculoCatastroById(
	    CirculoCatastro acc_parametro, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch       lsw_watch;
		CirculoCatastro lcc_datos;

		lsw_watch     = Logger.getNewStopWatch();
		lcc_datos     = getParameterBusiness().findCirculoCatastroById(acc_parametro);

		Logger.log(lsw_watch, "ParameterBean", "findCirculoCatastroById", as_userId, as_localIp, as_remoteIp, null);

		return lcc_datos;
	}

	/** {@inheritdoc} */
	public CirculoFestivo findCirculoFestivoById(String as_s)
	    throws B2BException
	{
		StopWatch      lsw_watch;
		CirculoFestivo lcr_datos;

		lsw_watch     = Logger.getNewStopWatch();

		lcr_datos = getParameterBusiness().findCirculoFestivoById(as_s);

		Logger.log(lsw_watch, "ParameterBean", "findCirculoFestivoById", null, null, null, null);

		return lcr_datos;
	}

	/** {@inheritdoc} */
	public CirculoOrigenDestino findCirculoOrigenDestinoById(CirculoOrigenDestino acod_cod)
	    throws B2BException
	{
		StopWatch            lsw_watch;
		CirculoOrigenDestino lcod_datos;

		lsw_watch     = Logger.getNewStopWatch();

		lcod_datos = getParameterBusiness().findCirculoOrigenDestinoById(acod_cod);

		Logger.log(lsw_watch, "ParameterBean", "findCirculoOrigenDestinoById", null, null, null, null);

		return lcod_datos;
	}

	/** {@inheritdoc} */
	public CirculoRegistral findCirculoRegistralById(
	    CirculoRegistral acr_circulo, String as_idUsuario, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch        lsw_watch;
		CirculoRegistral lcc_datos;

		lsw_watch     = Logger.getNewStopWatch();

		lcc_datos = getParameterBusiness().findCirculoRegistralById(acr_circulo);

		Logger.log(lsw_watch, "ParameterBean", "findCirculoRegistralById", as_idUsuario, as_localIp, as_remoteIp, null);

		return lcc_datos;
	}

	/** {@inheritdoc} */
	public Collection<UsuarioCirculo> findCirculosPorUsuario(
	    UsuarioCirculo auc_usuarioCirculo, String as_idUsuario, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch                  lsw_watch;
		Collection<UsuarioCirculo> lcc_datos;

		lsw_watch     = Logger.getNewStopWatch();

		lcc_datos = getParameterBusiness().findCirculosPorUsuario(auc_usuarioCirculo);

		Logger.log(
		    lsw_watch, "ParameterBean", "findCirculosPorUsuario", as_idUsuario, as_localIp, as_remoteIp, lcc_datos
		);

		return lcc_datos;
	}

	/** {@inheritdoc} */
	public Collection<UsuarioCirculo> findCirculosPorUsuarioActivo(
	    UsuarioCirculo auc_usuarioCirculo, String as_idUsuario, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch                  lsw_watch;
		Collection<UsuarioCirculo> lcc_datos;

		lsw_watch     = Logger.getNewStopWatch();

		lcc_datos = getParameterBusiness().findCirculosPorUsuarioActivo(auc_usuarioCirculo);

		Logger.log(
		    lsw_watch, "ParameterBean", "findCirculosPorUsuarioActivo", as_idUsuario, as_localIp, as_remoteIp, lcc_datos
		);

		return lcc_datos;
	}

	/** {@inheritdoc} */
	public ComunidadesEtnicas findComunidadesEtnicasById(int ai_i)
	    throws B2BException
	{
		StopWatch          lsw_watch;
		ComunidadesEtnicas lcce_datos;

		lsw_watch     = Logger.getNewStopWatch();

		lcce_datos = getParameterBusiness().findComunidadesEtnicasById(ai_i);

		Logger.log(lsw_watch, "ParameterBean", "findComunidadesEtnicasById", null, null, null, null);

		return lcce_datos;
	}

	/** {@inheritdoc} */
	public CondicionTarifa findCondicionTarifaById(String as_s)
	    throws B2BException
	{
		StopWatch       lsw_watch;
		CondicionTarifa lcr_datos;

		lsw_watch     = Logger.getNewStopWatch();

		lcr_datos = getParameterBusiness().findCondicionTarifaById(as_s);

		Logger.log(lsw_watch, "ParameterBean", "findCondicionTarifaById", null, null, null, null);

		return lcr_datos;
	}

	/** {@inheritdoc} */
	public Constantes findConstantById(Constantes ac_parametros)
	    throws B2BException
	{
		StopWatch  lsw_watch;
		Constantes lc_datos;

		lsw_watch     = Logger.getNewStopWatch();

		lc_datos = getParameterBusiness().findConstantById(ac_parametros);

		Logger.log(lsw_watch, "ParameterBean", "findConstantById", null, null, null, null);

		return lc_datos;
	}

	/** {@inheritdoc} */
	public Constantes findConstantByIdCYN(Constantes ac_parametros)
	    throws B2BException
	{
		StopWatch  lsw_watch;
		Constantes lc_datos;

		lsw_watch     = Logger.getNewStopWatch();

		lc_datos = getParameterBusiness().findConstantByIdCYN(ac_parametros);

		Logger.log(lsw_watch, "ParameterBean", "findConstantByIdCYN", null, null, null, null);

		return lc_datos;
	}

	/** {@inheritdoc} */
	public Consultas findConsultasById(Consultas as_s)
	    throws B2BException
	{
		StopWatch lsw_watch;
		Consultas lcr_datos;

		lsw_watch     = Logger.getNewStopWatch();

		lcr_datos = getParameterBusiness().findConsultasById(as_s);

		Logger.log(lsw_watch, "ParameterBean", "findConsultasById", null, null, null, null);

		return lcr_datos;
	}

	/**
	 * Método para encontrar todos los registros de la base de datos para la tabla SDB_ACC_ENTIDAD_EXTERNA_CONVENIO
	 * que coincidan con un id específico.
	 *
	 * @param as_numeroConvenio de as numeroConvenio
	 * @param as_nombreConvenio de as nombreConvenio
	 * @return el valor de Collection de AccEntidadExternaConvenio
	 * @throws B2BException de b 2 B exception
	 */
	public Collection<AccEntidadExternaConvenio> findConvenios(String as_numeroConvenio, String as_nombreConvenio)
	    throws B2BException
	{
		StopWatch                             lsw_watch;
		Collection<AccEntidadExternaConvenio> lcaeec_datos;

		lsw_watch     = Logger.getNewStopWatch();

		lcaeec_datos = getParameterBusiness().findConvenios(as_numeroConvenio, as_nombreConvenio);

		Logger.log(lsw_watch, "ParameterBean", "findConvenios", null, null, null, lcaeec_datos);

		return lcaeec_datos;
	}

	/**
	 * Método para encontrar todos los registros de la base de datos para la tabla SDB_PNG_COORDENADA
	 * que coincidan con un id específico.
	 *
	 * @param as_s de as s
	 * @return el valor de coordenada
	 * @throws B2BException de b 2 B exception
	 */
	public Coordenada findCoordenadaById(String as_s)
	    throws B2BException
	{
		StopWatch  lsw_watch;
		Coordenada lc_datos;

		lsw_watch     = Logger.getNewStopWatch();
		lc_datos      = getParameterBusiness().findCoordenadaById(as_s);

		Logger.log(lsw_watch, "ParameterBean", "findCoordenadaById", null, null, null, null);

		return lc_datos;
	}

	/** {@inheritdoc} */
	public Departamento findDepartamentoById(Departamento ac_parametros)
	    throws B2BException
	{
		StopWatch    lsw_watch;
		Departamento lc_datos;

		lsw_watch     = Logger.getNewStopWatch();

		lc_datos = getParameterBusiness().findDepartamentoById(ac_parametros);

		Logger.log(lsw_watch, "ParameterBean", "findDepartamentoById", null, null, null, null);

		return lc_datos;
	}

	/** {@inheritdoc} */
	public DependenciaSNR findDependenciaSNRById(
	    DependenciaSNR adsnr_parametro, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch      lsw_watch;
		DependenciaSNR ldsnr_datos;

		lsw_watch       = Logger.getNewStopWatch();
		ldsnr_datos     = getParameterBusiness().findDependenciaSNRById(adsnr_parametro);

		Logger.log(lsw_watch, "ParameterBean", "findDependenciaSNRById", as_userId, as_localIp, as_remoteIp, null);

		return ldsnr_datos;
	}

	/**
	 * Retorna el valor del objeto de DetalleAntSistema.
	 *
	 * @param as_idDetalleAntSistema correspondiente al valor de tipo String detalleAntSistema
	 * @param as_idDatosAntSistema correspondiente al valor de tipo String as_idDatosAntSistema
	 * @return el valor de DetalleAntSistema
	 * @throws B2BException de b 2 B exception
	 */
	public DetalleAntSistema findDetalleAntSistemaByDetalleYDatosAntSis(
	    String as_idDetalleAntSistema, String as_idDatosAntSistema
	)
	    throws B2BException
	{
		StopWatch         lsw_watch;
		DetalleAntSistema lcr_datos;

		lsw_watch     = Logger.getNewStopWatch();

		lcr_datos = getParameterBusiness()
				            .findDetalleAntSistemaByDetalleYDatosAntSis(as_idDetalleAntSistema, as_idDatosAntSistema);

		Logger.log(lsw_watch, "ParameterBean", "findAlertaTramiteById", null, null, null, null);

		return lcr_datos;
	}

	/** {@inheritdoc} */
	public DetalleProcesoConsulta findDetalleProcesoConsultaById(DetalleProcesoConsulta acdpc_cdpc)
	    throws B2BException
	{
		StopWatch              lsw_watch;
		DetalleProcesoConsulta lcdpc_datos;

		lsw_watch     = Logger.getNewStopWatch();

		lcdpc_datos = getParameterBusiness().findDetalleProcesoConsultaById(acdpc_cdpc);

		Logger.log(lsw_watch, "ParameterBean", "findDetalleProcesoConsultaById", null, null, null, null);

		return lcdpc_datos;
	}

	/** {@inheritdoc} */
	public DominioRrr findDominioRRRById(DominioRrr adr_parametros)
	    throws B2BException
	{
		StopWatch  lsw_watch;
		DominioRrr ldr_return;

		lsw_watch     = Logger.getNewStopWatch();

		ldr_return = getParameterBusiness().findDominioRRRById(adr_parametros);

		Logger.log(lsw_watch, "ParameterBean", "findDominioRRRById", null, null, null, null);

		return ldr_return;
	}

	/**
	 * Método para encontrar todos los registros de la base de datos para la tabla SDB_PGN_ENTIDAD_RECAUDADORA
	 * que coincidan con un id específico.
	 *
	 * @param aer_er de aer er
	 * @return el valor de entidad recaudadora
	 * @throws B2BException de b 2 B exception
	 */
	public EntidadRecaudadora findEntidadRecaudadoraById(EntidadRecaudadora aer_er)
	    throws B2BException
	{
		StopWatch          lsw_watch;
		EntidadRecaudadora lta_dato;

		lsw_watch     = Logger.getNewStopWatch();

		lta_dato = getParameterBusiness().findEntidadRecaudadoraById(aer_er);

		Logger.log(lsw_watch, "ParameterBean", "findEntidadRecaudadoraById", null, null, null, null);

		return lta_dato;
	}

	/** {@inheritdoc} */
	public EntidadesAlerta findEntidadesAlertaById(
	    EntidadesAlerta aea_parametro, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch       lsw_watch;
		EntidadesAlerta lea_datos;

		lsw_watch     = Logger.getNewStopWatch();
		lea_datos     = getParameterBusiness().findEntidadesAlertaById(aea_parametro);

		Logger.log(lsw_watch, "ParameterBean", "findEntidadesAlertaById", as_userId, as_localIp, as_remoteIp, null);

		return lea_datos;
	}

	/** {@inheritdoc} */
	public EstadoActividad findEstadoActividadById(EstadoActividad ac_parametros)
	    throws B2BException
	{
		StopWatch       lsw_watch;
		EstadoActividad lc_datos;

		lsw_watch     = Logger.getNewStopWatch();

		lc_datos = getParameterBusiness().findProcesoAutomaticoById(ac_parametros);

		Logger.log(lsw_watch, "ParameterBean", "findEstadoActividadById", null, null, null, null);

		return lc_datos;
	}

	/**
	 * Método para encontrar todos los registros de la base de datos para la tabla SDB_PNG_ESTADO_ANOTACION
	 * que coincidan con un id específico.
	 *
	 * @param aea_ea de aea ea
	 * @return el valor de estado anotacion
	 * @throws B2BException de b 2 B exception
	 */
	public EstadoAnotacion findEstadoAnotacionById(EstadoAnotacion aea_ea)
	    throws B2BException
	{
		StopWatch       lsw_watch;
		EstadoAnotacion lea_dato;

		lsw_watch     = Logger.getNewStopWatch();

		lea_dato = getParameterBusiness().findEstadoAnotacionById(aea_ea);

		Logger.log(lsw_watch, "ParameterBean", "findEstadoAnotacionById", null, null, null, null);

		return lea_dato;
	}

	/**
	 * Método para encontrar todos los registros de la base de datos para la tabla SDB_PGN_ESTADO_NUPRE
	 * que coincidan con un id específico.
	 *
	 * @param aen_en de aen en
	 * @return el valor de estado nupre
	 * @throws B2BException de b 2 B exception
	 */
	public EstadoNupre findEstadoNupreById(EstadoNupre aen_en)
	    throws B2BException
	{
		StopWatch   lsw_watch;
		EstadoNupre len_dato;

		lsw_watch     = Logger.getNewStopWatch();

		len_dato = getParameterBusiness().findEstadoNupreById(aen_en);

		Logger.log(lsw_watch, "ParameterBean", "findEstadoNupreById", null, null, null, null);

		return len_dato;
	}

	/**
	 * Método para encontrar todos los registros de la base de datos para la tabla SDB_PGN_ESTADO_PREDIO
	 * que coincidan con un id específico.
	 *
	 * @param aen_en de aen en
	 * @return el valor de estado predio
	 * @throws B2BException de b 2 B exception
	 */
	public EstadoPredio findEstadoPredioById(EstadoPredio aen_en)
	    throws B2BException
	{
		StopWatch    lsw_watch;
		EstadoPredio lep_dato;

		lsw_watch     = Logger.getNewStopWatch();

		lep_dato = getParameterBusiness().findEstadoPredioById(aen_en);

		Logger.log(lsw_watch, "ParameterBean", "findEstadoPredioById", null, null, null, null);

		return lep_dato;
	}

	/**
	 * Método para encontrar todos los registros de la base de datos para la tabla SDB_PGN_TIPO_AREA
	 * que coincidan con un id específico.
	 *
	 * @param aer_er de aer er
	 * @return el valor de estado registro
	 * @throws B2BException de b 2 B exception
	 */
	public EstadoRegistro findEstadoRegistroById(EstadoRegistro aer_er)
	    throws B2BException
	{
		StopWatch      lsw_watch;
		EstadoRegistro ler_dato;

		lsw_watch     = Logger.getNewStopWatch();

		ler_dato = getParameterBusiness().findEstadoRegistroById(aer_er);

		Logger.log(lsw_watch, "ParameterBean", "findEstadoRegistroById", null, null, null, null);

		return ler_dato;
	}

	/** {@inheritdoc} */
	public Estados findEstadosById(Estados ae_e)
	    throws B2BException
	{
		StopWatch lsw_watch;
		Estados   le_datos;

		lsw_watch     = Logger.getNewStopWatch();

		le_datos = getParameterBusiness().findEstadosById(ae_e);

		Logger.log(lsw_watch, "ParameterBean", "findEstadosById", null, null, null, null);

		return le_datos;
	}

	/** {@inheritdoc} */
	public Collection<MotivoTramite> findEtapaById()
	    throws B2BException
	{
		StopWatch                 lsw_watch;
		Collection<MotivoTramite> lcr_datos;

		lsw_watch     = Logger.getNewStopWatch();

		lcr_datos = getParameterBusiness().findEtapaById();

		Logger.log(lsw_watch, "ParameterBean", "findEtapaById", null, null, null, null);

		return lcr_datos;
	}

	/** {@inheritdoc} */
	public Etapa findEtapaById(Etapa ae_etapa, String as_idUsuario, String as_localIp, String as_remoteIp)
	    throws B2BException
	{
		StopWatch lsw_watch;
		Etapa     le_etapa;

		lsw_watch     = Logger.getNewStopWatch();

		le_etapa = getParameterBusiness().findEtapaById(ae_etapa);

		Logger.log(lsw_watch, "ParameterBean", "findEtapaById", as_idUsuario, as_localIp, as_remoteIp, null);

		return le_etapa;
	}

	/** {@inheritdoc} */
	public Collection<UsuarioEtapa> findEtapasPorUsuario(
	    UsuarioEtapa aue_usuarioEtapa, String as_idUsuario, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch                lsw_watch;
		Collection<UsuarioEtapa> lcc_datos;

		lsw_watch     = Logger.getNewStopWatch();

		lcc_datos = getParameterBusiness().findEtapasPorUsuario(aue_usuarioEtapa);

		Logger.log(
		    lsw_watch, "ParameterBean", "findEtapasPorUsuario", as_idUsuario, as_localIp, as_remoteIp, lcc_datos
		);

		return lcc_datos;
	}

	/** {@inheritdoc} */
	public Collection<UsuarioEtapa> findEtapasPorUsuarioActivo(
	    UsuarioEtapa aue_usuarioEtapa, String as_idUsuario, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch                lsw_watch;
		Collection<UsuarioEtapa> lcc_datos;

		lsw_watch     = Logger.getNewStopWatch();

		lcc_datos = getParameterBusiness().findEtapasPorUsuarioActivo(aue_usuarioEtapa);

		Logger.log(
		    lsw_watch, "ParameterBean", "findEtapasPorUsuarioActivo", as_idUsuario, as_localIp, as_remoteIp, lcc_datos
		);

		return lcc_datos;
	}

	/** {@inheritdoc} */
	public FestivoNacional findFestivoNacionalById(FestivoNacional ac_parametros)
	    throws B2BException
	{
		StopWatch       lsw_watch;
		FestivoNacional lc_datos;

		lsw_watch     = Logger.getNewStopWatch();

		lc_datos = getParameterBusiness().findFestivoNacionalById(ac_parametros);

		Logger.log(lsw_watch, "ParameterBean", "findFestivoNacionalById", null, null, null, null);

		return lc_datos;
	}

	/** {@inheritdoc} */
	public Gobernacion findGobernacionById(Gobernacion ac_parametros)
	    throws B2BException
	{
		StopWatch   lsw_watch;
		Gobernacion lc_datos;

		lsw_watch     = Logger.getNewStopWatch();

		lc_datos = getParameterBusiness().findGobernacionById(ac_parametros);

		Logger.log(lsw_watch, "ParameterBean", "findGobernacionById", null, null, null, null);

		return lc_datos;
	}

	/**
	 * Método para encontrar todos los registros de la base de datos para la tabla SDB_PNG_GRUPO_NATURALEZA_JURIDICA
	 * que coincidan con un id específico.
	 *
	 * @param agnj_gnj de agnj gnj
	 * @return el valor de grupo naturaleza juridica
	 * @throws B2BException de b 2 B exception
	 */
	public GrupoNaturalezaJuridica findGrupoNaturalezaJuridicaById(GrupoNaturalezaJuridica agnj_gnj)
	    throws B2BException
	{
		StopWatch               lsw_watch;
		GrupoNaturalezaJuridica lgnj_dato;

		lsw_watch     = Logger.getNewStopWatch();

		lgnj_dato = getParameterBusiness().findGrupoNaturalezaJuridicaById(agnj_gnj);

		Logger.log(lsw_watch, "ParameterBean", "findGrupoNaturalezaJuridicaById", null, null, null, null);

		return lgnj_dato;
	}

	/** {@inheritdoc} */
	public Constantes findImageById(Constantes ac_parametros)
	    throws B2BException
	{
		StopWatch  lsw_watch;
		Constantes lc_datos;

		lsw_watch     = Logger.getNewStopWatch();

		lc_datos = getParameterBusiness().findImageById(ac_parametros);

		Logger.log(lsw_watch, "ParameterBean", "findImageById", null, null, null, null);

		return lc_datos;
	}

	/** {@inheritdoc} */
	public InstanciaConsulta findInstanciaConsultaById(InstanciaConsulta ac_parametros)
	    throws B2BException
	{
		StopWatch         lsw_watch;
		InstanciaConsulta lc_datos;

		lsw_watch     = Logger.getNewStopWatch();

		lc_datos = getParameterBusiness().findInstanciaConsultaById(ac_parametros);

		Logger.log(lsw_watch, "ParameterBean", "findInstanciaConsultaById", null, null, null, null);

		return lc_datos;
	}

	/** {@inheritdoc} */
	public InteresadoDocumentoTipo findInteresadoDocumentoTipoById(InteresadoDocumentoTipo aidt_parametros)
	    throws B2BException
	{
		StopWatch               lsw_watch;
		InteresadoDocumentoTipo ling_datos;

		lsw_watch     = Logger.getNewStopWatch();

		ling_datos = getParameterBusiness().findInteresadoDocumentoTipoById(aidt_parametros);

		Logger.log(lsw_watch, "ParameterBean", "findInteresadoDocumentoTipoById", null, null, null, null);

		return ling_datos;
	}

	/** {@inheritdoc} */
	public InteresadoNaturalGenero findInteresadoNaturalGeneroById(InteresadoNaturalGenero aing_parametros)
	    throws B2BException
	{
		StopWatch               lsw_watch;
		InteresadoNaturalGenero ling_datos;

		lsw_watch     = Logger.getNewStopWatch();

		ling_datos = getParameterBusiness().findInteresadoNaturalGeneroById(aing_parametros);

		Logger.log(lsw_watch, "ParameterBean", "findInteresadoNaturalGeneroById", null, null, null, null);

		return ling_datos;
	}

	/** {@inheritdoc} */
	public LetraEje findLetraEjeById(String as_idLetra)
	    throws B2BException
	{
		StopWatch lsw_watch;
		LetraEje  lle_datos;

		lsw_watch     = Logger.getNewStopWatch();
		lle_datos     = getParameterBusiness().findLetraEjeById(as_idLetra);

		Logger.log(lsw_watch, "ParameterBean", "findLetraEjeById", null, null, null, null);

		return lle_datos;
	}

	/** {@inheritdoc} */
	public LibroAntiguoSistema findLibroAntiguoSistemaById(LibroAntiguoSistema ac_parametros)
	    throws B2BException
	{
		StopWatch           lsw_watch;
		LibroAntiguoSistema lc_datos;

		lsw_watch     = Logger.getNewStopWatch();

		lc_datos = getParameterBusiness().findLibroAntiguoSistemaById(ac_parametros);

		Logger.log(lsw_watch, "ParameterBean", "findLibroAntiguoSistemaById", null, null, null, null);

		return lc_datos;
	}

	/** {@inheritdoc} */
	public LibroTestamento findLibroTestamentoById(
	    LibroTestamento alt_parametro, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch       lsw_watch;
		LibroTestamento llt_datos;

		lsw_watch     = Logger.getNewStopWatch();
		llt_datos     = getParameterBusiness().findLibroTestamentoById(alt_parametro);

		Logger.log(lsw_watch, "ParameterBean", "findLibroTestamentoById", as_userId, as_localIp, as_remoteIp, null);

		return llt_datos;
	}

	/** {@inheritdoc} */
	public LineaProduccion findLineaProduccionById(LineaProduccion alp_parametros)
	    throws B2BException
	{
		StopWatch       lsw_watch;
		LineaProduccion llp_datos;

		lsw_watch     = Logger.getNewStopWatch();

		llp_datos = getParameterBusiness().findLineaProduccionById(alp_parametros);

		Logger.log(lsw_watch, "ParameterBean", "findLineaProduccionById", null, null, null, null);

		return llp_datos;
	}

	/** {@inheritdoc} */
	public Collection<UsuarioLinea> findLineasPorUsuario(
	    UsuarioLinea aul_usuarioLinea, String as_idUsuario, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch                lsw_watch;
		Collection<UsuarioLinea> lcc_datos;

		lsw_watch     = Logger.getNewStopWatch();

		lcc_datos = getParameterBusiness().findLineasPorUsuario(aul_usuarioLinea);

		Logger.log(
		    lsw_watch, "ParameterBean", "findLineasPorUsuario", as_idUsuario, as_localIp, as_remoteIp, lcc_datos
		);

		return lcc_datos;
	}

	/** {@inheritdoc} */
	public Collection<UsuarioLinea> findLineasPorUsuarioActivo(
	    UsuarioLinea aul_usuarioLinea, String as_idUsuario, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch                lsw_watch;
		Collection<UsuarioLinea> lcc_datos;

		lsw_watch     = Logger.getNewStopWatch();

		lcc_datos = getParameterBusiness().findLineasPorUsuarioActivo(aul_usuarioLinea);

		Logger.log(
		    lsw_watch, "ParameterBean", "findLineasPorUsuarioActivo", as_idUsuario, as_localIp, as_remoteIp, lcc_datos
		);

		return lcc_datos;
	}

	/** {@inheritdoc} */
	public MedidaArea findMedidaAreaById(String as_param)
	    throws B2BException
	{
		StopWatch  lsw_watch;
		MedidaArea lcr_datos;

		lsw_watch     = Logger.getNewStopWatch();

		lcr_datos = getParameterBusiness().findMedidaAreaById(as_param);

		Logger.log(lsw_watch, "ParameterBean", "findMedidaAreaById", null, null, null, null);

		return lcr_datos;
	}

	/**
	 * Método para encontrar todos los registros de la base de datos para la tabla SDB_PGN_MEDIO_RECAUDO
	 * que coincidan con un id específico.
	 *
	 * @param as_s de as s
	 * @return el valor de medio recaudo
	 * @throws B2BException de b 2 B exception
	 */
	public MedioRecaudo findMedioRecaudoById(String as_s)
	    throws B2BException
	{
		StopWatch    lsw_watch;
		MedioRecaudo lmr_dato;

		lsw_watch     = Logger.getNewStopWatch();

		lmr_dato = getParameterBusiness().findMedioRecaudoById(as_s);

		Logger.log(lsw_watch, "ParameterBean", "findMedioRecaudoById", null, null, null, null);

		return lmr_dato;
	}

	/**
	 * Método para encontrar todos los registros de la base de datos para la tabla SDB_PGN_MENSAJE_RESPUESTA
	 * que coincidan con un id específico.
	 *
	 * @param as_s de as s
	 * @return el valor de mensaje respuesta
	 * @throws B2BException de b 2 B exception
	 */
	public MensajeRespuesta findMensajeRespuestaById(String as_s)
	    throws B2BException
	{
		StopWatch        lsw_watch;
		MensajeRespuesta lmr_dato;

		lsw_watch     = Logger.getNewStopWatch();

		lmr_dato = getParameterBusiness().findMensajeRespuestaById(as_s);

		Logger.log(lsw_watch, "ParameterBean", "findMensajeRespuestaById", null, null, null, null);

		return lmr_dato;
	}

	/** {@inheritdoc} */
	public MotivoTramite findMotivoTramiteById(MotivoTramite amt_mt)
	    throws B2BException
	{
		StopWatch     lsw_watch;
		MotivoTramite lmt_datos;

		lsw_watch     = Logger.getNewStopWatch();

		lmt_datos = getParameterBusiness().findMotivoTramiteById(amt_mt);

		Logger.log(lsw_watch, "ParameterBean", "findMotivoTramiteById", null, null, null, null);

		return lmt_datos;
	}

	/** {@inheritdoc} */
	public MotivoTramite findMotivoTramiteById(String as_idMotivoTramite)
	    throws B2BException
	{
		StopWatch     lsw_watch;
		MotivoTramite lmt_datos;

		lsw_watch     = Logger.getNewStopWatch();

		lmt_datos = getParameterBusiness().findMotivoTramiteById(as_idMotivoTramite);

		Logger.log(lsw_watch, "ParameterBean", "findMotivoTramiteById", null, null, null, null);

		return lmt_datos;
	}

	/** {@inheritdoc} */
	public Municipio findMunicipioById(Municipio ac_parametros)
	    throws B2BException
	{
		StopWatch lsw_watch;
		Municipio lc_datos;

		lsw_watch     = Logger.getNewStopWatch();

		lc_datos = getParameterBusiness().findMunicipioById(ac_parametros);

		Logger.log(lsw_watch, "ParameterBean", "findMunicipioById", null, null, null, null);

		return lc_datos;
	}

	/**
	 * Método para encontrar todos los registros de la base de datos para la tabla SDB_PNG_NATURALEZA_JURIDICA
	 * que coincidan con un id específico.
	 *
	 * @param acnj_cnj de acnj cnj
	 * @return el valor de naturaleza juridica
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public NaturalezaJuridica findNaturalezaJuridicaById(NaturalezaJuridica acnj_cnj)
	    throws B2BException
	{
		StopWatch          lsw_watch;
		NaturalezaJuridica lccs_datos;

		lsw_watch     = Logger.getNewStopWatch();

		lccs_datos = getParameterBusiness().findNaturalezaJuridicaById(acnj_cnj);

		Logger.log(lsw_watch, "ParameterBean", "findNaturalezaJuridicayId", null, null, null, null);

		return lccs_datos;
	}

	/** {@inheritdoc} */
	public NotaDevolutiva findNotaDevolutivaById(NotaDevolutiva and_notaDevolutiva)
	    throws B2BException
	{
		StopWatch      lsw_watch;
		NotaDevolutiva lcr_datos;

		lsw_watch     = Logger.getNewStopWatch();

		lcr_datos = getParameterBusiness().findNotaDevolutivaById(and_notaDevolutiva);

		Logger.log(lsw_watch, "ParameterBean", "NotaDevolutiva", null, null, null, null);

		return lcr_datos;
	}

	/** {@inheritdoc} */
	public Collection<NotaDevolutiva> findNotaDevolutivaByTurno(NotaDevolutiva and_notaDevolutiva)
	    throws B2BException
	{
		StopWatch                  lsw_watch;
		Collection<NotaDevolutiva> lcr_datos;

		lsw_watch     = Logger.getNewStopWatch();

		lcr_datos = getParameterBusiness().findNotaDevolutivaByTurno(and_notaDevolutiva);

		Logger.log(lsw_watch, "ParameterBean", "NotaDevolutiva", null, null, null, null);

		return lcr_datos;
	}

	/** {@inheritdoc} */
	public NumeracionOficiosCirculo findNumeracionOficiosCirculoById(
	    NumeracionOficiosCirculo anoc_parametro, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch                lsw_watch;
		NumeracionOficiosCirculo lnoc_datos;

		lsw_watch      = Logger.getNewStopWatch();
		lnoc_datos     = getParameterBusiness().findNumeracionOficiosCirculoById(anoc_parametro);

		Logger.log(
		    lsw_watch, "ParameterBean", "findNumeracionOficiosCirculoById", as_userId, as_localIp, as_remoteIp, null
		);

		return lnoc_datos;
	}

	/** {@inheritdoc} */
	public OficinaOrigen findOficinaOrigenById(OficinaOrigen amt_mt)
	    throws B2BException
	{
		StopWatch     lsw_watch;
		OficinaOrigen lmt_datos;

		lsw_watch     = Logger.getNewStopWatch();

		lmt_datos = getParameterBusiness().findOficinaOrigenById(amt_mt);

		Logger.log(lsw_watch, "ParameterBean", "findMotivoTramiteById", null, null, null, null);

		return lmt_datos;
	}

	/**
	 * método de consulta de Opcion para obtener todos sus registros por su indicativo.
	 *
	 * @param as_idOpcion de as id opcion
	 * @return Opcion con la información solicitada
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Opcion findOpcionById(String as_idOpcion)
	    throws B2BException
	{
		Opcion    lcc_return;
		StopWatch lsw_watch;

		lsw_watch     = Logger.getNewStopWatch();

		lcc_return = getParameterBusiness().findOpcionById(as_idOpcion);

		Logger.log(lsw_watch, "ParameterBean", "findAllOpcionById", null, null, null, null);

		return lcc_return;
	}

	/**
	 * método de consulta de OpcionEtapa para obtener todos sus registros por su indicativo.
	 *
	 * @param as_idEtapa de as id etapa
	 * @param as_idOpcion de as id opcion
	 * @return OpcionEtapa con la información solicitada
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public OpcionEtapa findOpcionEtapaById(long as_idEtapa, String as_idOpcion)
	    throws B2BException
	{
		OpcionEtapa loe_return;
		StopWatch   lsw_watch;

		lsw_watch     = Logger.getNewStopWatch();

		loe_return = getParameterBusiness().findOpcionEtapaById(as_idEtapa, as_idOpcion);

		Logger.log(lsw_watch, "ParameterBean", "findAllOpcionEtapaById", null, null, null, null);

		return loe_return;
	}

	/** {@inheritdoc} */
	public Origen findOrigenById(Origen ao_o)
	    throws B2BException
	{
		StopWatch lsw_watch;
		Origen    lo_datos;

		lsw_watch     = Logger.getNewStopWatch();

		lo_datos = getParameterBusiness().findOrigenById(ao_o);

		Logger.log(lsw_watch, "ParameterBean", "findOrigenById", null, null, null, null);

		return lo_datos;
	}

	/** {@inheritdoc} */
	public Pais findPaisById(Pais ac_parametros)
	    throws B2BException
	{
		StopWatch lsw_watch;
		Pais      lc_datos;

		lsw_watch     = Logger.getNewStopWatch();

		lc_datos = getParameterBusiness().findPaisById(ac_parametros);

		Logger.log(lsw_watch, "ParameterBean", "findPaisById", null, null, null, null);

		return lc_datos;
	}

	/** {@inheritdoc} */
	public ParteInteresada findParteInteresadaById(ParteInteresada ac_parametros)
	    throws B2BException
	{
		StopWatch       lsw_watch;
		ParteInteresada lc_datos;

		lsw_watch     = Logger.getNewStopWatch();

		lc_datos = getParameterBusiness().findParteInteresadaById(ac_parametros);

		Logger.log(lsw_watch, "ParameterBean", "findParteInteresadaById", null, null, null, null);

		return lc_datos;
	}

	/** {@inheritdoc} */
	public Collection<Persona> findPersonaByEntidad(AccEntidadExterna aaee_entidadExterna)
	    throws B2BException
	{
		StopWatch           lsw_watch;
		Collection<Persona> lcp_datos;

		lsw_watch     = Logger.getNewStopWatch();

		lcp_datos = getParameterBusiness().findPersonaByEntidad(aaee_entidadExterna);

		Logger.log(lsw_watch, "ParameterBean", "findPersonaByEntidad", null, null, null, lcp_datos);

		return lcp_datos;
	}

	/** {@inheritdoc} */
	public PersonaCorreoElectronico findPersonaCorreoById(String as_idPersona)
	    throws B2BException
	{
		StopWatch                lsw_watch;
		PersonaCorreoElectronico lpce_datos;

		lsw_watch     = Logger.getNewStopWatch();

		lpce_datos = getParameterBusiness().findPersonaCorreoById(as_idPersona);

		Logger.log(lsw_watch, "ParameterBean", "findPersonaCorreoById", null, null, null, null);

		return lpce_datos;
	}

	/** {@inheritdoc} */
	public Plantilla findPlantillaById(Plantilla ap_p)
	    throws B2BException
	{
		StopWatch lsw_watch;
		Plantilla lp_datos;

		lsw_watch     = Logger.getNewStopWatch();

		lp_datos = getParameterBusiness().findPlantillaById(ap_p);

		Logger.log(lsw_watch, "ParameterBean", "findPlantillaById", null, null, null, null);

		return lp_datos;
	}

	/** {@inheritdoc} */
	public PlantillaComunicacion findPlantillaComunicacionById(PlantillaComunicacion apc_pn)
	    throws B2BException
	{
		StopWatch             lsw_watch;
		PlantillaComunicacion lmt_datos;

		lsw_watch     = Logger.getNewStopWatch();

		lmt_datos = getParameterBusiness().findPlantillaComunicacionById(apc_pn);

		Logger.log(lsw_watch, "ParameterBean", "findPlantillaComunicacionById", null, null, null, null);

		return lmt_datos;
	}

	/** {@inheritdoc} */
	public PlantillaNotificacion findPlantillaNotificacionById(PlantillaNotificacion apn_pn)
	    throws B2BException
	{
		StopWatch             lsw_watch;
		PlantillaNotificacion lmt_datos;

		lsw_watch     = Logger.getNewStopWatch();

		lmt_datos = getParameterBusiness().findPlantillaNotificacionById(apn_pn);

		Logger.log(lsw_watch, "ParameterBean", "findPlantillaNotificacionById", null, null, null, null);

		return lmt_datos;
	}

	/** {@inheritdoc} */
	public ProcesoAutomatico findProcesoAutomaticoById(ProcesoAutomatico ac_parametros)
	    throws B2BException
	{
		StopWatch         lsw_watch;
		ProcesoAutomatico lc_datos;

		lsw_watch     = Logger.getNewStopWatch();

		lc_datos = getParameterBusiness().findProcesoAutomaticoById(ac_parametros);

		Logger.log(lsw_watch, "ParameterBean", "findProcesoAutomaticoById", null, null, null, null);

		return lc_datos;
	}

	/** {@inheritdoc} */
	public Proceso findProcesoById(Proceso ap_p)
	    throws B2BException
	{
		StopWatch lsw_watch;
		Proceso   lcr_datos;

		lsw_watch     = Logger.getNewStopWatch();

		lcr_datos = getParameterBusiness().findProcesoById(ap_p);

		Logger.log(lsw_watch, "ParameterBean", "findProcesoById", null, null, null, null);

		return lcr_datos;
	}

	/** {@inheritdoc} */
	public ProcesoConsulta findProcesoConsultaById(ProcesoConsulta acpc_cpc)
	    throws B2BException
	{
		StopWatch       lsw_watch;
		ProcesoConsulta lcpc_datos;

		lsw_watch     = Logger.getNewStopWatch();

		lcpc_datos = getParameterBusiness().findProcesoConsultaById(acpc_cpc);

		Logger.log(lsw_watch, "ParameterBean", "findProcesoConsultaById", null, null, null, null);

		return lcpc_datos;
	}

	/**
	 * Método para encontrar todos los registros de la base de datos para la tabla SDB_PGN_PUNTO_RECAUDO
	 * que coincidan con un id específico.
	 *
	 * @param as_s de as s
	 * @return el valor de punto recaudo
	 * @throws B2BException de b 2 B exception
	 */
	public PuntoRecaudo findPuntoRecaudoById(String as_s)
	    throws B2BException
	{
		StopWatch    lsw_watch;
		PuntoRecaudo lpr_dato;

		lsw_watch     = Logger.getNewStopWatch();

		lpr_dato = getParameterBusiness().findPuntoRecaudoById(as_s);

		Logger.log(lsw_watch, "ParameterBean", "findPuntoRecaudoById", null, null, null, null);

		return lpr_dato;
	}

	/**
	 * Método para encontrar todos los registros de la base de datos para la tabla SDB_PGN_PUNTO_RECAUDO_TIPO_RECAUDO
	 * que coincidan con un id específico.
	 *
	 * @param aprtr_prtr de aprtr prtr
	 * @return el valor de punto recaudo tipo recaudo
	 * @throws B2BException de b 2 B exception
	 */
	public PuntoRecaudoTipoRecaudo findPuntoRecaudoTipoRecaudoById(PuntoRecaudoTipoRecaudo aprtr_prtr)
	    throws B2BException
	{
		StopWatch               lsw_watch;
		PuntoRecaudoTipoRecaudo lta_dato;

		lsw_watch     = Logger.getNewStopWatch();

		lta_dato = getParameterBusiness().findPuntoRecaudoTipoRecaudoById(aprtr_prtr);

		Logger.log(lsw_watch, "ParameterBean", "findPuntoRecaudoTipoRecaudoById", null, null, null, null);

		return lta_dato;
	}

	/** {@inheritdoc} */
	public RangoCuantia findRangoCuantiaById(RangoCuantia adr_parametros)
	    throws B2BException
	{
		StopWatch    lsw_watch;
		RangoCuantia ldr_return;

		lsw_watch     = Logger.getNewStopWatch();

		ldr_return = getParameterBusiness().findRangoCuantiaById(adr_parametros);

		Logger.log(lsw_watch, "ParameterBean", "RangoCuantiaById", null, null, null, null);

		return ldr_return;
	}

	/** {@inheritdoc} */
	public Regional findRegionalById(Regional ac_parametros)
	    throws B2BException
	{
		StopWatch lsw_watch;
		Regional  lc_datos;

		lsw_watch     = Logger.getNewStopWatch();

		lc_datos = getParameterBusiness().findRegionalById(ac_parametros);

		Logger.log(lsw_watch, "ParameterBean", "findRegionalById", null, null, null, null);

		return lc_datos;
	}

	/** {@inheritdoc} */
	public ReglaNegocio findReglaNegocioById(ReglaNegocio arn_rn)
	    throws B2BException
	{
		StopWatch    lsw_watch;
		ReglaNegocio lrn_datos;

		lsw_watch     = Logger.getNewStopWatch();

		lrn_datos = getParameterBusiness().findReglaNegocioById(arn_rn);

		Logger.log(lsw_watch, "ParameterBean", "findReglaNegocioById", null, null, null, null);

		return lrn_datos;
	}

	/** {@inheritdoc} */
	public Reintentos findReintentosById(Reintentos ar_r)
	    throws B2BException
	{
		StopWatch  lsw_watch;
		Reintentos lr_datos;

		lsw_watch     = Logger.getNewStopWatch();

		lr_datos = getParameterBusiness().findReintentosById(ar_r);

		Logger.log(lsw_watch, "ParameterBean", "findReintentosById", null, null, null, null);

		return lr_datos;
	}

	/** {@inheritdoc} */
	public Reportes findReportesById(Reportes adr_parametros)
	    throws B2BException
	{
		StopWatch lsw_watch;
		Reportes  ldr_return;

		lsw_watch     = Logger.getNewStopWatch();

		ldr_return = getParameterBusiness().findReportesById(adr_parametros);

		Logger.log(lsw_watch, "ParameterBean", "ReportesById", null, null, null, null);

		return ldr_return;
	}

	/** {@inheritdoc} */
	public ResultadoConsulta findResultadoConsultaById(ResultadoConsulta arc_rc)
	    throws B2BException
	{
		StopWatch         lsw_watch;
		ResultadoConsulta lrc_datos;

		lsw_watch     = Logger.getNewStopWatch();

		lrc_datos = getParameterBusiness().findResultadoConsultaById(arc_rc);

		Logger.log(lsw_watch, "ParameterBean", "findResultadoConsultaById", null, null, null, null);

		return lrc_datos;
	}

	/** {@inheritdoc} */
	public Rol findRolById(Rol ar_parametros)
	    throws B2BException
	{
		StopWatch lsw_watch;
		Rol       lr_datos;

		lsw_watch     = Logger.getNewStopWatch();

		lr_datos = getParameterBusiness().findRolById(ar_parametros);

		Logger.log(lsw_watch, "ParameterBean", "findRolById", null, null, null, null);

		return lr_datos;
	}

	/**
	 * Método para consultar el RolOpción.
	 *
	 * @param as_idRol de as id rol
	 * @param as_idOpcion de as id opcion
	 * @return RolOpcion con el registro solicitado.
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public RolOpcion findRolOpcionById(String as_idRol, String as_idOpcion)
	    throws B2BException
	{
		RolOpcion lcc_return;
		StopWatch lsw_watch;

		lsw_watch     = Logger.getNewStopWatch();

		lcc_return = getParameterBusiness().findRolOpcionById(as_idRol, as_idOpcion);

		Logger.log(lsw_watch, "ParameterBean", "findCampoCriterioBusquedaById", null, null, null, null);

		return lcc_return;
	}

	/** {@inheritdoc} */
	public SalarioMinimo findSalarioMinimo(
	    SalarioMinimo aottf_data, boolean ab_b, String as_idUsuario, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch     lsw_watch;
		SalarioMinimo lcc_datos;

		lsw_watch     = Logger.getNewStopWatch();

		lcc_datos = getParameterBusiness().findSalarioMinimo(aottf_data, ab_b, as_idUsuario, as_localIp, as_remoteIp);

		Logger.log(lsw_watch, "ParameterBean", "findSalarioMinimo", as_idUsuario, as_localIp, as_remoteIp, null);

		return lcc_datos;
	}

	/**
	 * Método para encontrar todos los registros de la base de datos para la tabla SDB_ACC_SUBPROCESO
	 * que coincidan con un id específico.
	 *
	 * @param acsp_csp de acsp csp
	 * @return el valor de subproceso
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Subproceso findSubProcesoById(Subproceso acsp_csp)
	    throws B2BException
	{
		StopWatch  lsw_watch;
		Subproceso lcsp_datos;

		lsw_watch     = Logger.getNewStopWatch();

		lcsp_datos = getParameterBusiness().findSubProcesoById(acsp_csp);

		Logger.log(lsw_watch, "ParameterBean", "findSubProcesoById", null, null, null, null);

		return lcsp_datos;
	}

	/** {@inheritdoc} */
	public SubprocesoVersion findSubProcesoVersionById(SubprocesoVersion acspv_cspv)
	    throws B2BException
	{
		StopWatch         lsw_watch;
		SubprocesoVersion lcspv_datos;

		lsw_watch     = Logger.getNewStopWatch();

		lcspv_datos = getParameterBusiness().findSubProcesoVersionById(acspv_cspv);

		Logger.log(lsw_watch, "ParameterBean", "findSubProcesoVersionById", null, null, null, null);

		return lcspv_datos;
	}

	/** {@inheritdoc} */
	public Collection<Subproceso> findSubprocesos(boolean ab_b)
	    throws B2BException
	{
		StopWatch              lsw_watch;
		Collection<Subproceso> lcsp_datos;

		lsw_watch     = Logger.getNewStopWatch();

		lcsp_datos = getParameterBusiness().findSubprocesos(ab_b);

		Logger.log(lsw_watch, "ParameterBean", "findSubproceso", null, null, null, lcsp_datos);

		return lcsp_datos;
	}

	/**
	 * Método para encontrar todos los registros de la base de datos para la tabla SDB_PGN_SUCURSAL_CANAL_ORIGEN_SERVICIO
	 * que coincidan con un id específico.
	 *
	 * @param ascos_scos de ascos scos
	 * @return el valor de sucursal canal origen servicio
	 * @throws B2BException de b 2 B exception
	 */
	public SucursalCanalOrigenServicio findSucursalCanalOrigenServicioById(SucursalCanalOrigenServicio ascos_scos)
	    throws B2BException
	{
		StopWatch                   lsw_watch;
		SucursalCanalOrigenServicio lta_dato;

		lsw_watch     = Logger.getNewStopWatch();

		lta_dato = getParameterBusiness().findSucursalCanalOrigenServicioById(ascos_scos);

		Logger.log(lsw_watch, "ParameterBean", "findSucursalCanalOrigenServicioById", null, null, null, null);

		return lta_dato;
	}

	/** {@inheritdoc} */
	public TarifaFija findTarifaFijaById(TarifaFija adr_parametros)
	    throws B2BException
	{
		StopWatch  lsw_watch;
		TarifaFija ldr_return;

		lsw_watch     = Logger.getNewStopWatch();

		ldr_return = getParameterBusiness().findTarifaFijaById(adr_parametros);

		Logger.log(lsw_watch, "ParameterBean", "findTarifaFijaById", null, null, null, null);

		return ldr_return;
	}

	/** {@inheritdoc} */
	public Collection<TipoActo> findTipoActoByLineaProduccion(
	    String as_lineaProduccion, String as_idUsuario, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch            lsw_watch;
		Collection<TipoActo> lcc_datos;

		lsw_watch     = Logger.getNewStopWatch();

		lcc_datos = getParameterBusiness().findTipoActoByLineaProduccion(as_lineaProduccion);

		Logger.log(
		    lsw_watch, "ParameterBean", "findTipoActoByLineaProduccion", as_idUsuario, as_localIp, as_remoteIp,
		    lcc_datos
		);

		return lcc_datos;
	}

	/** {@inheritdoc} */
	public TipoActoCondicion findTipoActoCondicionById(TipoActoCondicion adr_parametros)
	    throws B2BException
	{
		StopWatch         lsw_watch;
		TipoActoCondicion ldr_return;

		lsw_watch     = Logger.getNewStopWatch();

		ldr_return = getParameterBusiness().findTipoActoCondicionById(adr_parametros);

		Logger.log(lsw_watch, "ParameterBean", "findTipoActoCondicionById", null, null, null, null);

		return ldr_return;
	}

	/** {@inheritdoc} */
	public TipoActoEjecutoria findTipoActoEjecutoriaById(TipoActoEjecutoria adr_parametros)
	    throws B2BException
	{
		StopWatch          lsw_watch;
		TipoActoEjecutoria ldr_return;

		lsw_watch     = Logger.getNewStopWatch();

		ldr_return = getParameterBusiness().findTipoActoEjecutoriaById(adr_parametros);

		Logger.log(lsw_watch, "ParameterBean", "findTipoActoEjecutoriaById", null, null, null, null);

		return ldr_return;
	}

	/**
	 * Método para encontrar todos los registros de la base de datos para la tabla SDB_PGN_TIPO_AREA
	 * que coincidan con un id específico.
	 *
	 * @param ata_ta de ata ta
	 * @return el valor de tipo area
	 * @throws B2BException de b 2 B exception
	 */
	public TipoArea findTipoAreaById(TipoArea ata_ta)
	    throws B2BException
	{
		StopWatch lsw_watch;
		TipoArea  lta_dato;

		lsw_watch     = Logger.getNewStopWatch();

		lta_dato = getParameterBusiness().findTipoAreaById(ata_ta);

		Logger.log(lsw_watch, "ParameterBean", "findTipoAreaById", null, null, null, null);

		return lta_dato;
	}

	/**
	 * Método para encontrar todos los registros de la base de datos para la tabla SDB_PGN_TIPO_CANAL_ORIGEN
	 * que coincidan con un id específico.
	 *
	 * @param atco_tco de atco tco
	 * @return el valor de tipo canal origen
	 * @throws B2BException de b 2 B exception
	 */
	public TipoCanalOrigen findTipoCanalOrigenById(TipoCanalOrigen atco_tco)
	    throws B2BException
	{
		StopWatch       lsw_watch;
		TipoCanalOrigen ltco_dato;

		lsw_watch     = Logger.getNewStopWatch();

		ltco_dato = getParameterBusiness().findTipoCanalOrigenById(atco_tco);

		Logger.log(lsw_watch, "ParameterBean", "findTipoCanalOrigenById", null, null, null, null);

		return ltco_dato;
	}

	/** {@inheritdoc} */
	public TipoCriterioBusquedaPGN findTipoCriterioBusquedaPGNById(
	    TipoCriterioBusquedaPGN atcb_parametro, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch               lsw_watch;
		TipoCriterioBusquedaPGN ltcb_datos;

		lsw_watch      = Logger.getNewStopWatch();
		ltcb_datos     = getParameterBusiness().findTipoCriterioBusquedaPGNById(atcb_parametro);

		Logger.log(
		    lsw_watch, "ParameterBean", "findTipoCriterioBusquedaPGNById", as_userId, as_localIp, as_remoteIp, null
		);

		return ltcb_datos;
	}

	/** {@inheritdoc} */
	public TipoDecisionRecurso findTipoDecisionRecursoById(TipoDecisionRecurso adr_parametros)
	    throws B2BException
	{
		StopWatch           lsw_watch;
		TipoDecisionRecurso ldr_return;

		lsw_watch     = Logger.getNewStopWatch();

		ldr_return = getParameterBusiness().findTipoDecisionRecursoById(adr_parametros);

		Logger.log(lsw_watch, "ParameterBean", "findTipoDecisionRecursoById", null, null, null, null);

		return ldr_return;
	}

	/**
	 * Método para encontrar todos los registros de la base de datos para la tabla SDB_PNG_TIPO_EJE
	 * que coincidan con un id específico.
	 *
	 * @param as_s de as s
	 * @return el valor de tipo eje
	 * @throws B2BException de b 2 B exception
	 */
	public TipoEje findTipoEjeById(String as_s)
	    throws B2BException
	{
		StopWatch lsw_watch;
		TipoEje   lte_datos;

		lsw_watch     = Logger.getNewStopWatch();
		lte_datos     = getParameterBusiness().findTipoEjeById(as_s);

		Logger.log(lsw_watch, "ParameterBean", "findTipoEjeById", null, null, null, null);

		return lte_datos;
	}

	/** {@inheritdoc} */
	public TipoEntidad findTipoEntidad(
	    TipoEntidad aottf_data, boolean ab_b, String as_idUsuario, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch   lsw_watch;
		TipoEntidad lcc_datos;

		lsw_watch     = Logger.getNewStopWatch();

		lcc_datos = getParameterBusiness().findTipoEntidad(aottf_data, ab_b, as_idUsuario, as_localIp, as_remoteIp);

		Logger.log(lsw_watch, "ParameterBean", "findTipoEntidad", as_idUsuario, as_localIp, as_remoteIp, null);

		return lcc_datos;
	}

	/**
	 * Método para encontrar todos los registros de la base de datos para la tabla SDB_PGN_TIPO_ESTADO_LIQUIDACION
	 * que coincidan con un id específico.
	 *
	 * @param atel_tel de atel tel
	 * @return el valor de tipo estado liquidacion
	 * @throws B2BException de b 2 B exception
	 */
	public TipoEstadoLiquidacion findTipoEstadoLiquidacionById(TipoEstadoLiquidacion atel_tel)
	    throws B2BException
	{
		StopWatch             lsw_watch;
		TipoEstadoLiquidacion ltel_dato;

		lsw_watch     = Logger.getNewStopWatch();

		ltel_dato = getParameterBusiness().findTipoEstadoLiquidacionById(atel_tel);

		Logger.log(lsw_watch, "ParameterBean", "findTipoEstadoLiquidacionById", null, null, null, null);

		return ltel_dato;
	}

	/** {@inheritdoc} */
	public TipoEstadoSolicitud findTipoEstadoSolicitudById(
	    TipoEstadoSolicitud ates_parametro, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch           lsw_watch;
		TipoEstadoSolicitud ltes_datos;

		lsw_watch      = Logger.getNewStopWatch();
		ltes_datos     = getParameterBusiness().findTipoEstadoSolicitudById(ates_parametro);

		Logger.log(lsw_watch, "ParameterBean", "findTipoEstadoSolicitudById", as_userId, as_localIp, as_remoteIp, null);

		return ltes_datos;
	}

	/** {@inheritdoc} */
	public TipoIntegracionGobernacion findTipoIntegracionGobernacionById(TipoIntegracionGobernacion ac_parametros)
	    throws B2BException
	{
		StopWatch                  lsw_watch;
		TipoIntegracionGobernacion lc_datos;

		lsw_watch     = Logger.getNewStopWatch();

		lc_datos = getParameterBusiness().findTipoIntegracionGobernacionById(ac_parametros);

		Logger.log(lsw_watch, "ParameterBean", "findTipoIntegracionGobernacionById", null, null, null, null);

		return lc_datos;
	}

	/** {@inheritdoc} */
	public Collection<TipoNotificacion> findTipoNotificacion()
	    throws B2BException
	{
		StopWatch                    lsw_watch;
		Collection<TipoNotificacion> lctn_datos;

		lsw_watch     = Logger.getNewStopWatch();

		lctn_datos = getParameterBusiness().findTipoNotificacion();

		Logger.log(lsw_watch, "ParameterBean", "findTipoNotificacion", null, null, null, lctn_datos);

		return lctn_datos;
	}

	/** {@inheritdoc} */
	public TipoNotificacion findTipoNotificacionById(TipoNotificacion actn_ctn)
	    throws B2BException
	{
		StopWatch        lsw_watch;
		TipoNotificacion lctn_datos;

		lsw_watch     = Logger.getNewStopWatch();

		lctn_datos = getParameterBusiness().findTipoNotificacionById(actn_ctn);

		Logger.log(lsw_watch, "ParameterBean", "findTipoNotificacionById", null, null, null, null);

		return lctn_datos;
	}

	/** {@inheritdoc} */
	public TipoOficina findTipoOficina(
	    TipoOficina aottf_data, boolean ab_b, String as_idUsuario, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch   lsw_watch;
		TipoOficina lcc_datos;

		lsw_watch     = Logger.getNewStopWatch();

		lcc_datos = getParameterBusiness().findTipoOficina(aottf_data, ab_b, as_idUsuario, as_localIp, as_remoteIp);

		Logger.log(lsw_watch, "ParameterBean", "findTipoOficina", as_idUsuario, as_localIp, as_remoteIp, null);

		return lcc_datos;
	}

	/** {@inheritdoc} */
	public TipoOperacion findTipoOperacionById(
	    TipoOperacion ato_parametro, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch     lsw_watch;
		TipoOperacion lto_datos;

		lsw_watch     = Logger.getNewStopWatch();
		lto_datos     = getParameterBusiness().findTipoOperacionById(ato_parametro);

		Logger.log(lsw_watch, "ParameterBean", "findTipoOperacionById", as_userId, as_localIp, as_remoteIp, null);

		return lto_datos;
	}

	/** {@inheritdoc} */
	public TipoPersona findTipoPersonaById(TipoPersona atp_parametros)
	    throws B2BException
	{
		StopWatch   lsw_watch;
		TipoPersona ltp_datos;

		lsw_watch     = Logger.getNewStopWatch();

		ltp_datos = getParameterBusiness().findTipoPersonaById(atp_parametros);

		Logger.log(lsw_watch, "ParameterBean", "findTipoPersonaById", null, null, null, null);

		return ltp_datos;
	}

	/** {@inheritdoc} */
	public PredioTipo findTipoPredioById(PredioTipo apt_parametros)
	    throws B2BException
	{
		StopWatch  lsw_watch;
		PredioTipo lpt_datos;

		lsw_watch     = Logger.getNewStopWatch();

		lpt_datos = getParameterBusiness().findTipoPredioById(apt_parametros);

		Logger.log(lsw_watch, "ParameterBean", "findTipoPredioById", null, null, null, null);

		return lpt_datos;
	}

	/** {@inheritdoc} */
	public TipoRrr findTipoRRRById(TipoRrr atr_parametros)
	    throws B2BException
	{
		StopWatch lsw_watch;
		TipoRrr   ltr_datos;

		lsw_watch     = Logger.getNewStopWatch();

		ltr_datos = getParameterBusiness().findTipoRRRById(atr_parametros);

		Logger.log(lsw_watch, "ParameterBean", "findTipoRRRById", null, null, null, null);

		return ltr_datos;
	}

	/**
	 * Método para encontrar todos los registros de la base de datos para la tabla SDB_PGN_TIPO_RECAUDO
	 * que coincidan con un id específico.
	 *
	 * @param ata_ta de ata ta
	 * @return el valor de tipo recaudo
	 * @throws B2BException de b 2 B exception
	 */
	public TipoRecaudo findTipoRecaudoById(TipoRecaudo ata_ta)
	    throws B2BException
	{
		StopWatch   lsw_watch;
		TipoRecaudo lta_dato;

		lsw_watch     = Logger.getNewStopWatch();

		lta_dato = getParameterBusiness().findTipoRecaudoById(ata_ta);

		Logger.log(lsw_watch, "ParameterBean", "findTipoRecaudoById", null, null, null, null);

		return lta_dato;
	}

	/** {@inheritdoc} */
	public TipoRecepcion findTipoRecepcionById(TipoRecepcion ar_parametros)
	    throws B2BException
	{
		StopWatch     lsw_watch;
		TipoRecepcion lr_datos;

		lsw_watch     = Logger.getNewStopWatch();

		lr_datos = getParameterBusiness().findTipoRecepcionById(ar_parametros);

		Logger.log(lsw_watch, "ParameterBean", "findRolById", null, null, null, null);

		return lr_datos;
	}

	/** {@inheritdoc} */
	public TipoRecurso findTipoRecursoById(TipoRecurso adr_parametros)
	    throws B2BException
	{
		StopWatch   lsw_watch;
		TipoRecurso ldr_return;

		lsw_watch     = Logger.getNewStopWatch();

		ldr_return = getParameterBusiness().findTipoRecursoById(adr_parametros);

		Logger.log(lsw_watch, "ParameterBean", "TipoRecursoById", null, null, null, null);

		return ldr_return;
	}

	/**
	 * Método para encontrar todos los registros de la base de datos para la tabla SDB_PGN_TIPO_REQUIERE_MATRICULA
	 * que coincidan con un id específico.
	 *
	 * @param aen_en de aen en
	 * @return el valor de tipo requiere matricula
	 * @throws B2BException de b 2 B exception
	 */
	public TipoRequiereMatricula findTipoRequiereMatriculaById(TipoRequiereMatricula aen_en)
	    throws B2BException
	{
		StopWatch             lsw_watch;
		TipoRequiereMatricula ltrm_dato;

		lsw_watch     = Logger.getNewStopWatch();

		ltrm_dato = getParameterBusiness().findTipoRequiereMatriculaById(aen_en);

		Logger.log(lsw_watch, "ParameterBean", "TipoRequiereMatricula", null, null, null, null);

		return ltrm_dato;
	}

	/** {@inheritdoc} */
	public TipoTarjetaApoderado findTipoTarjetaApoderadoById(TipoTarjetaApoderado ar_parametros)
	    throws B2BException
	{
		StopWatch            lsw_watch;
		TipoTarjetaApoderado lr_datos;

		lsw_watch     = Logger.getNewStopWatch();

		lr_datos = getParameterBusiness().findTipoTarjetaApoderadoById(ar_parametros);

		Logger.log(lsw_watch, "ParameterBean", "findRolById", null, null, null, null);

		return lr_datos;
	}

	/** {@inheritdoc} */
	public TipoUsoSuelo findTipoUsoPredioById(TipoUsoSuelo atus_parametros)
	    throws B2BException
	{
		StopWatch    lsw_watch;
		TipoUsoSuelo ltus_datos;

		lsw_watch     = Logger.getNewStopWatch();

		ltus_datos = getParameterBusiness().findTipoUsoPredioById(atus_parametros);

		Logger.log(lsw_watch, "ParameterBean", "findTipoUsoPredioById", null, null, null, null);

		return ltus_datos;
	}

	/** {@inheritdoc} */
	public TipoAdquisicion findTiposAdquisicion(
	    TipoAdquisicion aottf_data, boolean ab_b, String as_idUsuario, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch       lsw_watch;
		TipoAdquisicion lcc_datos;

		lsw_watch     = Logger.getNewStopWatch();

		lcc_datos = getParameterBusiness().findTiposAdquisicion(
			    aottf_data, ab_b, as_idUsuario, as_localIp, as_remoteIp
			);

		Logger.log(lsw_watch, "ParameterBean", "findTiposAdquisicion", as_idUsuario, as_localIp, as_remoteIp, null);

		return lcc_datos;
	}

	/** {@inheritdoc} */
	public TipoPago findTiposPagos(
	    TipoPago aottf_data, boolean ab_b, String as_idUsuario, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch lsw_watch;
		TipoPago  lcc_datos;

		lsw_watch     = Logger.getNewStopWatch();

		lcc_datos = getParameterBusiness().findTiposPagos(aottf_data, ab_b, as_idUsuario, as_localIp, as_remoteIp);

		Logger.log(lsw_watch, "ParameterBean", "findTiposPagos", as_idUsuario, as_localIp, as_remoteIp, null);

		return lcc_datos;
	}

	/** {@inheritdoc} */
	public TipoTarifaRegistral findTiposTarifas(
	    TipoTarifaRegistral aottf_data, boolean ab_b, String as_idUsuario, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch           lsw_watch;
		TipoTarifaRegistral lcc_datos;

		lsw_watch     = Logger.getNewStopWatch();

		lcc_datos = getParameterBusiness().findTiposTarifas(aottf_data, ab_b, as_idUsuario, as_localIp, as_remoteIp);

		Logger.log(lsw_watch, "ParameterBean", "findTiposTarifas", as_idUsuario, as_localIp, as_remoteIp, null);

		return lcc_datos;
	}

	/** {@inheritdoc} */
	public UnidadTiempoVencimiento findUnidadTiempoVencimientoById(UnidadTiempoVencimiento acutv_cutv)
	    throws B2BException
	{
		StopWatch               lsw_watch;
		UnidadTiempoVencimiento lcutv_datos;

		lsw_watch     = Logger.getNewStopWatch();

		lcutv_datos = getParameterBusiness().findUnidadTiempoVencimientoById(acutv_cutv);

		Logger.log(lsw_watch, "ParameterBean", "findUnidadTiempoVencimientoById", null, null, null, null);

		return lcutv_datos;
	}

	/** {@inheritdoc} */
	public Usuario findUsuarioByTipoDoc(Usuario au_parametro, String as_userId, String as_localIp, String as_remoteIp)
	    throws B2BException
	{
		StopWatch lsw_watch;
		Usuario   lu_datos;

		lsw_watch     = Logger.getNewStopWatch();

		lu_datos = getParameterBusiness().findUsuarioByTipoDoc(au_parametro);

		Logger.log(lsw_watch, "ParameterBean", "findUsuarioByTipoDoc", as_userId, as_localIp, as_remoteIp, null);

		return lu_datos;
	}

	/**
	 * Método para la construccion del usuario para una persona de la tabla SDB_ACC_ENTIDAD_EXTERNA_PERSONA.
	 *
	 * @param ap_persona de ap persona
	 * @param acp_cp de acp cp
	 * @param as_idEntidad de as idEntidad
	 * @return el valor de String
	 * @throws B2BException de b 2 B exception
	 */
	public String findUsuarioPersona(Persona ap_persona, Collection<Persona> acp_cp, String as_idEntidad)
	    throws B2BException
	{
		StopWatch lsw_watch;
		String    ls_dato;

		lsw_watch     = Logger.getNewStopWatch();

		ls_dato = getParameterBusiness().findUsuarioPersona(ap_persona, acp_cp, as_idEntidad);

		Logger.log(lsw_watch, "ParameterBean", "findUsuarioPersona", null, null, null, null);

		return ls_dato;
	}

	/** {@inheritdoc} */
	public Collection<UsuarioRol> findUsuarioRolByRolLineaProduccion(
	    long al_idEtapa, String as_lineaProduccion, String as_idCirculo, String as_idRol, String as_userId,
	    String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch              lsw_watch;
		Collection<UsuarioRol> lcr_datos;

		lsw_watch     = Logger.getNewStopWatch();

		lcr_datos = getParameterBusiness()
				            .findUsuarioRolByRolLineaProduccion(al_idEtapa, as_lineaProduccion, as_idCirculo, as_idRol);

		Logger.log(
		    lsw_watch, "ParameterBean", "findUsuarioRolByRolLineaProduccion", as_userId, as_localIp, as_remoteIp,
		    lcr_datos
		);

		return lcr_datos;
	}

	/** {@inheritdoc} */
	public Vereda findVeredaById(Vereda ac_parametros)
	    throws B2BException
	{
		StopWatch lsw_watch;
		Vereda    lc_datos;

		lsw_watch     = Logger.getNewStopWatch();

		lc_datos = getParameterBusiness().findVeredaById(ac_parametros);

		Logger.log(lsw_watch, "ParameterBean", "findVeredaById", null, null, null, null);

		return lc_datos;
	}

	/** {@inheritdoc} */
	public ZonaRegistral findZonaRegistralById(ZonaRegistral ac_parametros)
	    throws B2BException
	{
		StopWatch     lsw_watch;
		ZonaRegistral lc_datos;

		lsw_watch     = Logger.getNewStopWatch();

		lc_datos = getParameterBusiness().findZonaRegistralById(ac_parametros);

		Logger.log(lsw_watch, "ParameterBean", "findZonaRegistralById", null, null, null, null);

		return lc_datos;
	}

	/** {@inheritdoc} */
	public Map<String, String> generarCodigos(String as_idConstante)
	    throws B2BException
	{
		Map<String, String> lmss_mss;
		lmss_mss = new HashMap<String, String>();

		if(StringUtils.isValidString(as_idConstante))
		{
			StopWatch lsw_watch;

			lsw_watch     = Logger.getNewStopWatch();

			lmss_mss = getParameterBusiness().generarCodigos(as_idConstante);

			Logger.log(lsw_watch, "ParameterBean", "generarCodigos", null, null, null, null);
		}

		return lmss_mss;
	}

	/** {@inheritdoc} */
	public void gestionSalarioMinimo(
	    SalarioMinimo aottf_data, String as_idUsuario, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch lsw_watch;

		lsw_watch = Logger.getNewStopWatch();

		getParameterBusiness().gestionSalarioMinimo(aottf_data, as_idUsuario, as_localIp, as_remoteIp);

		Logger.log(lsw_watch, "ParameterBean", "gestionSalarioMinimo", as_idUsuario, as_localIp, as_remoteIp, null);
	}

	/** {@inheritdoc} */
	public void gestionTipoAdquisicion(
	    TipoAdquisicion aottf_data, String as_idUsuario, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch lsw_watch;

		lsw_watch = Logger.getNewStopWatch();

		getParameterBusiness().gestionTipoAdquisicion(aottf_data, as_idUsuario, as_localIp, as_remoteIp);

		Logger.log(lsw_watch, "ParameterBean", "gestionTipoAdquisicion", as_idUsuario, as_localIp, as_remoteIp, null);
	}

	/** {@inheritdoc} */
	public void gestionTipoEntidad(TipoEntidad aottf_data, String as_idUsuario, String as_localIp, String as_remoteIp)
	    throws B2BException
	{
		StopWatch lsw_watch;

		lsw_watch = Logger.getNewStopWatch();

		getParameterBusiness().gestionTipoEntidad(aottf_data, as_idUsuario, as_localIp, as_remoteIp);

		Logger.log(lsw_watch, "ParameterBean", "gestionTipoEntidad", as_idUsuario, as_localIp, as_remoteIp, null);
	}

	/** {@inheritdoc} */
	public void gestionTipoOficina(TipoOficina aottf_data, String as_idUsuario, String as_localIp, String as_remoteIp)
	    throws B2BException
	{
		StopWatch lsw_watch;

		lsw_watch = Logger.getNewStopWatch();

		getParameterBusiness().gestionTipoOficina(aottf_data, as_idUsuario, as_localIp, as_remoteIp);

		Logger.log(lsw_watch, "ParameterBean", "gestionTipoOficina", as_idUsuario, as_localIp, as_remoteIp, null);
	}

	/** {@inheritdoc} */
	public void gestionTipoPago(TipoPago aottf_data, String as_idUsuario, String as_localIp, String as_remoteIp)
	    throws B2BException
	{
		StopWatch lsw_watch;

		lsw_watch = Logger.getNewStopWatch();

		getParameterBusiness().gestionTipoPago(aottf_data, as_idUsuario, as_localIp, as_remoteIp);

		Logger.log(lsw_watch, "ParameterBean", "gestionTipoPago", as_idUsuario, as_localIp, as_remoteIp, null);
	}

	/** {@inheritdoc} */
	public void gestionTiposTarifas(
	    TipoTarifaRegistral aottf_data, boolean ab_b, String as_idUsuario, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch lsw_watch;

		lsw_watch = Logger.getNewStopWatch();

		getParameterBusiness().gestionTiposTarifas(aottf_data, ab_b, as_idUsuario, as_localIp, as_remoteIp);

		Logger.log(lsw_watch, "ParameterBean", "gestionTiposTarifas", as_idUsuario, as_localIp, as_remoteIp, null);
	}

	/** {@inheritdoc} */
	public void insertUpdateCirculo(
	    CirculoRegistral acr_circulo, boolean ab_insert, String as_idUsuario, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch lsw_watch;

		lsw_watch = Logger.getNewStopWatch();

		getParameterBusiness().insertUpdateCirculo(acr_circulo, ab_insert, as_idUsuario, as_remoteIp);

		Logger.log(lsw_watch, "ParameterBean", "findAllCirculos", as_idUsuario, as_localIp, as_remoteIp, null);
	}

	/** {@inheritdoc} */
	public void insertUpdateDesbordes(
	    Collection<Desborde> ad_desbores, String as_idUsuario, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch lsw_watch;

		lsw_watch = Logger.getNewStopWatch();

		getParameterBusiness().insertUpdateDesbordes(ad_desbores, as_idUsuario, as_remoteIp);

		Logger.log(lsw_watch, "ParameterBean", "insertUpdateDesbordes", as_idUsuario, as_localIp, as_remoteIp, null);
	}

	/** {@inheritdoc} */
	public void insertUpdateDominioNaturalezaJuridica(
	    DominioNaturalezaJuridica adnj_dominioNatJur, boolean ab_insert, String as_idUsuario, String as_localIp,
	    String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch lsw_watch;

		lsw_watch = Logger.getNewStopWatch();

		getParameterBusiness()
			    .insertUpdateDominioNaturalezaJuridica(adnj_dominioNatJur, ab_insert, as_idUsuario, as_remoteIp);

		Logger.log(
		    lsw_watch, "ParameterBean", "insertUpdateDominioNaturalezaJuridica", as_idUsuario, as_localIp, as_remoteIp,
		    null
		);
	}

	/** {@inheritdoc} */
	public void insertUpdateEtapa(
	    Etapa ae_etapa, boolean ab_insert, String as_idUsuario, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch lsw_watch;

		lsw_watch = Logger.getNewStopWatch();

		getParameterBusiness().insertUpdateEtapa(ae_etapa, ab_insert, as_idUsuario, as_remoteIp);

		Logger.log(lsw_watch, "ParameterBean", "insertUpdateEtapa", as_idUsuario, as_localIp, as_remoteIp, null);
	}

	/** {@inheritdoc} */
	public void insertUpdateFase(
	    Fases af_fase, boolean ab_insert, String as_idUsuario, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch lsw_watch;

		lsw_watch = Logger.getNewStopWatch();

		getParameterBusiness().insertUpdateFase(af_fase, ab_insert, as_idUsuario, as_remoteIp);

		Logger.log(lsw_watch, "ParameterBean", "insertUpdateFase", as_idUsuario, as_localIp, as_remoteIp, null);
	}

	/** {@inheritdoc} */
	public void insertUpdateInteresadoDocumentoTipo(
	    InteresadoDocumentoTipo aidt_intDocTipo, boolean ab_insert, String as_idUsuario, String as_localIp,
	    String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch lsw_watch;

		lsw_watch = Logger.getNewStopWatch();

		getParameterBusiness().insertUpdateInteresadoDocumentoTipo(
		    aidt_intDocTipo, ab_insert, as_idUsuario, as_remoteIp
		);

		Logger.log(
		    lsw_watch, "ParameterBean", "insertUpdateInteresadoDocumentoTipo", as_idUsuario, as_localIp, as_remoteIp,
		    null
		);
	}

	/** {@inheritdoc} */
	public void insertUpdateRangoCuantia(
	    RangoCuantia arc_RangoCuantia, boolean ab_insert, String as_idUsuario, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch lsw_watch;

		lsw_watch = Logger.getNewStopWatch();

		getParameterBusiness().insertUpdateRangoCuantia(arc_RangoCuantia, ab_insert, as_idUsuario, as_remoteIp);

		Logger.log(lsw_watch, "ParameterBean", "insertUpdateRangoCuantia", as_idUsuario, as_localIp, as_remoteIp, null);
	}

	/** {@inheritdoc} */
	public void insertUpdateReportes(
	    Reportes ar_reportes, boolean ab_insert, String as_idUsuario, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch lsw_watch;

		lsw_watch = Logger.getNewStopWatch();

		getParameterBusiness().insertUpdateReportes(ar_reportes, ab_insert, as_idUsuario, as_remoteIp);

		Logger.log(lsw_watch, "ParameterBean", "insertUpdateReportes", as_idUsuario, as_localIp, as_remoteIp, null);
	}

	/** {@inheritdoc} */
	public void insertUpdateTarifaFija(
	    TarifaFija arc_TarifaFija, boolean ab_insert, String as_idUsuario, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch lsw_watch;

		lsw_watch = Logger.getNewStopWatch();

		getParameterBusiness().insertUpdateTarifaFija(arc_TarifaFija, ab_insert, as_idUsuario, as_remoteIp);

		Logger.log(lsw_watch, "ParameterBean", "insertUpdateTarifaFija", as_idUsuario, as_localIp, as_remoteIp, null);
	}

	/** {@inheritdoc} */
	public void insertUpdateTipoActo(
	    TipoActo ata_tipoActo, boolean ab_insert, String as_idUsuario, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch lsw_watch;

		lsw_watch = Logger.getNewStopWatch();

		getParameterBusiness().insertUpdateTiposActo(ata_tipoActo, ab_insert, as_idUsuario, as_remoteIp);

		Logger.log(lsw_watch, "ParameterBean", "insertUpdateTiposActo", as_idUsuario, as_localIp, as_remoteIp, null);
	}

	/** {@inheritdoc} */
	public void insertUpdateTipoActoCondicion(
	    TipoActoCondicion ata_tipoActoCondicion, boolean ab_insert, String as_idUsuario, String as_localIp,
	    String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch lsw_watch;

		lsw_watch = Logger.getNewStopWatch();

		getParameterBusiness().insertUpdateTipoActoCondicion(
		    ata_tipoActoCondicion, ab_insert, as_idUsuario, as_remoteIp
		);

		Logger.log(
		    lsw_watch, "ParameterBean", "insertUpdateTipoCondicionActo", as_idUsuario, as_localIp, as_remoteIp, null
		);
	}

	/** {@inheritdoc} */
	public void insertUpdateTipoActoEjecutoria(
	    TipoActoEjecutoria arc_tipoActoEjecutoria, boolean ab_insert, String as_idUsuario, String as_localIp,
	    String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch lsw_watch;

		lsw_watch = Logger.getNewStopWatch();

		getParameterBusiness()
			    .insertUpdateTipoActoEjecutoria(arc_tipoActoEjecutoria, ab_insert, as_idUsuario, as_remoteIp);

		Logger.log(
		    lsw_watch, "ParameterBean", "insertUpdateTipoActoEjecutoria", as_idUsuario, as_localIp, as_remoteIp, null
		);
	}

	/** {@inheritdoc} */
	public void insertUpdateTipoDecisionRecurso(
	    TipoDecisionRecurso ar_reportes, boolean ab_insert, String as_idUsuario, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch lsw_watch;

		lsw_watch = Logger.getNewStopWatch();

		getParameterBusiness().insertUpdateTipoDecisionRecurso(ar_reportes, ab_insert, as_idUsuario, as_remoteIp);

		Logger.log(
		    lsw_watch, "ParameterBean", "insertUpdateTipoDecisionRecurso", as_idUsuario, as_localIp, as_remoteIp, null
		);
	}

	/** {@inheritdoc} */
	public void insertUpdateTipoRecurso(
	    TipoRecurso ar_reportes, boolean ab_insert, String as_idUsuario, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch lsw_watch;

		lsw_watch = Logger.getNewStopWatch();

		getParameterBusiness().insertUpdateTipoRecurso(ar_reportes, ab_insert, as_idUsuario, as_remoteIp);

		Logger.log(lsw_watch, "ParameterBean", "insertUpdateTipoRecurso", as_idUsuario, as_localIp, as_remoteIp, null);
	}

	/** {@inheritdoc} */
	public void insertUpdateTiposAnotacion(
	    TipoAnotacion ata_tipoAnotacion, boolean ab_insert, String as_idUsuario, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch lsw_watch;

		lsw_watch = Logger.getNewStopWatch();

		getParameterBusiness().insertUpdateTiposAnotacion(ata_tipoAnotacion, ab_insert, as_idUsuario, as_remoteIp);

		Logger.log(
		    lsw_watch, "ParameterBean", "insertUpdateTiposAnotacion", as_idUsuario, as_localIp, as_remoteIp, null
		);
	}

	/** {@inheritdoc} */
	public void insertUpdateTiposCausal(
	    TipoCausal atc_tipoCausal, boolean ab_insert, String as_idUsuario, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch lsw_watch;

		lsw_watch = Logger.getNewStopWatch();

		getParameterBusiness().insertUpdateTiposCausal(atc_tipoCausal, ab_insert, as_idUsuario, as_remoteIp);

		Logger.log(lsw_watch, "ParameterBean", "insertUpdateTiposCausal", as_idUsuario, as_localIp, as_remoteIp, null);
	}

	/** {@inheritdoc} */
	public void insertUpdateTiposEje(
	    TipoEje ate_tipoEje, boolean ab_insert, String as_idUsuario, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch lsw_watch;

		lsw_watch = Logger.getNewStopWatch();

		getParameterBusiness().insertUpdateTiposEje(ate_tipoEje, ab_insert, as_idUsuario, as_remoteIp);

		Logger.log(lsw_watch, "ParameterBean", "insertUpdateTiposEje", as_idUsuario, as_localIp, as_remoteIp, null);
	}

	/** {@inheritdoc} */
	public void insertUpdateTiposEstadoSolicitud(
	    TipoEstadoSolicitud ates_tipoEstadoSolicitud, boolean ab_insert, String as_idUsuario, String as_localIp,
	    String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch lsw_watch;

		lsw_watch = Logger.getNewStopWatch();

		getParameterBusiness()
			    .insertUpdateTiposEstadoSolicitud(ates_tipoEstadoSolicitud, ab_insert, as_idUsuario, as_remoteIp);

		Logger.log(
		    lsw_watch, "ParameterBean", "insertUpdateTiposEstadoSolicitud", as_idUsuario, as_localIp, as_remoteIp, null
		);
	}

	/** {@inheritdoc} */
	public void insertUpdateTiposTestamento(
	    TipoTestamento att_tipoTestamento, boolean ab_insert, String as_idUsuario, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch lsw_watch;

		lsw_watch = Logger.getNewStopWatch();

		getParameterBusiness().insertUpdateTiposTestamento(att_tipoTestamento, ab_insert, as_idUsuario, as_remoteIp);

		Logger.log(
		    lsw_watch, "ParameterBean", "insertUpdateTiposTestamento", as_idUsuario, as_localIp, as_remoteIp, null
		);
	}

	/** {@inheritdoc} */
	public void insertUpdateUsuarioCirculo(
	    UsuarioCirculo auc_usuarioCirculo, boolean ab_insert, String as_idUsuario, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch lsw_watch;

		lsw_watch = Logger.getNewStopWatch();

		getParameterBusiness().insertUpdateUsuarioCirculo(auc_usuarioCirculo, ab_insert, as_idUsuario, as_remoteIp);

		Logger.log(
		    lsw_watch, "ParameterBean", "insertUpdateUsuarioCirculo", as_idUsuario, as_localIp, as_remoteIp, null
		);
	}

	/** {@inheritdoc} */
	public void insertUpdateUsuarioEtapa(
	    UsuarioEtapa aue_usuarioEtapa, boolean ab_insert, String as_idUsuario, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch lsw_watch;

		lsw_watch = Logger.getNewStopWatch();

		getParameterBusiness().insertUpdateUsuarioEtapa(aue_usuarioEtapa, ab_insert, as_idUsuario, as_remoteIp);

		Logger.log(lsw_watch, "ParameterBean", "insertUpdateUsuarioEtapa", as_idUsuario, as_localIp, as_remoteIp, null);
	}

	/** {@inheritdoc} */
	public String insertUpdateUsuarioLinea(
	    UsuarioLinea aul_usuarioLinea, boolean ab_insert, String as_idUsuario, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch lsw_watch;
		String    ls_return;

		lsw_watch     = Logger.getNewStopWatch();

		ls_return = getParameterBusiness()
				            .insertUpdateUsuarioLinea(aul_usuarioLinea, ab_insert, as_idUsuario, as_remoteIp);

		Logger.log(lsw_watch, "ParameterBean", "insertUpdateUsuarioLinea", as_idUsuario, as_localIp, as_remoteIp, null);

		return ls_return;
	}

	/** {@inheritdoc} */
	public void salvarAccEntidadExterna(
	    AccEntidadExterna aaee_parametros, boolean ab_insertar, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch lsw_watch;

		lsw_watch = Logger.getNewStopWatch();

		getParameterBusiness().salvarAccEntidadExterna(aaee_parametros, ab_insertar, as_userId, as_remoteIp);

		Logger.log(lsw_watch, "ParameterBean", "salvarAccEntidadExterna", as_userId, as_localIp, as_remoteIp, null);
	}

	/**
	 * Método para guardar los cambios o la inserción de AccEntidadExternaConvenio.
	 *
	 * @param aaeec_parametros de aaeec parametros
	 * @param ab_accion de ab accion
	 * @param lczr_zonaRegistral de lczr zonaRegistral
	 * @param as_userId de as user id
	 * @param as_localIp de as local ip
	 * @param as_remoteIp de as remote ip
	 * @throws B2BException de b 2 B exception
	 */
	public void salvarAccEntidadExternaConvenio(
	    AccEntidadExternaConvenio aaeec_parametros, boolean ab_accion, Collection<ZonaRegistral> lczr_zonaRegistral,
	    String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch lsw_watch;
		lsw_watch = Logger.getNewStopWatch();

		getParameterBusiness()
			    .salvarAccEntidadExternaConvenio(
			    aaeec_parametros, lczr_zonaRegistral, ab_accion, as_userId, as_remoteIp
			);

		Logger.log(
		    lsw_watch, "ParameterBean", "salvarAccEntidadExternaConvenio", as_userId, as_localIp, as_remoteIp, null
		);
	}

	/** {@inheritdoc} */
	public void salvarActividadEconomica(
	    ActividadEconomica aae_parametros, boolean ab_accion, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch lsw_watch;

		lsw_watch = Logger.getNewStopWatch();

		getParameterBusiness().salvarActividadEconomica(aae_parametros, ab_accion, as_userId, as_remoteIp);

		Logger.log(lsw_watch, "ParameterBean", "salvarActividadEconomica", as_userId, as_localIp, as_remoteIp, null);
	}

	/**
	 * Método para guardar los cambios o la inserción de UnidadTiempoVencimiento.
	 *
	 * @param aaa_parametros de aaa parametros
	 * @param ab_accion de ab accion
	 * @param as_userId de as user id
	 * @param as_localIp de as local ip
	 * @param as_remoteIp de as remote ip
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void salvarActuacionesAdministrativas(
	    TagPlantillaResolucion aaa_parametros, boolean ab_accion, String as_userId, String as_localIp,
	    String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch lsw_watch;
		lsw_watch = Logger.getNewStopWatch();

		getParameterBusiness().salvarActuacionesAdministrativas(aaa_parametros, ab_accion, as_userId, as_remoteIp);

		Logger.log(
		    lsw_watch, "ParameterBean", "salvarActuacionesAdministrativas", as_userId, as_localIp, as_remoteIp, null
		);
	}

	/**
	 * Método para guardar los cambios o la inserción de AdministracionComponentes.
	 *
	 * @param aac_parametros de aac parametros
	 * @param ab_accion de ab accion
	 * @param as_userId de as user id
	 * @param as_localIp de as local ip
	 * @param as_remoteIp de as remote ip
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void salvarAdministracionComponentes(
	    Componente aac_parametros, boolean ab_accion, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch lsw_watch;

		lsw_watch = Logger.getNewStopWatch();

		getParameterBusiness().salvarAdministracionComponentes(aac_parametros, ab_accion, as_userId, as_remoteIp);

		Logger.log(
		    lsw_watch, "ParameterBean", "salvarAdministracionComponentes", as_userId, as_localIp, as_remoteIp, null
		);
	}

	/** {@inheritdoc} */
	public void salvarAlertaNaturalezaJuridica(
	    AlertaNaturalezaJuridica atr_parametros, boolean ab_accion, String as_userId, String as_localIp,
	    String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch lsw_watch;

		lsw_watch = Logger.getNewStopWatch();

		getParameterBusiness().salvarAlertaNaturalezaJuridica(atr_parametros, ab_accion, as_userId, as_remoteIp);

		Logger.log(
		    lsw_watch, "ParameterBean", "salvarAlertaNaturalezaJuridica", as_userId, as_localIp, as_remoteIp, null
		);
	}

	/** {@inheritdoc} */
	public void salvarAlertaTramite(
	    AlertaTramite aat_parametros, boolean ab_accion, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch lsw_watch;

		lsw_watch = Logger.getNewStopWatch();

		getParameterBusiness().salvarAlertaTramite(aat_parametros, ab_accion, as_userId, as_remoteIp);

		Logger.log(lsw_watch, "ParameterBean", "salvarAlertaTramite", as_userId, as_localIp, as_remoteIp, null);
	}

	/**
	 * Método para guardar los cambios o la inserción de CalidadSolicitante.
	 *
	 * @param acs_parametros de acs parametros
	 * @param ab_accion de ab accion
	 * @param as_userId de as user id
	 * @param as_localIp de as local ip
	 * @param as_remoteIp de as remote ip
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void salvarCalidadSolicitante(
	    CalidadSolicitante acs_parametros, boolean ab_accion, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch lsw_watch;

		lsw_watch = Logger.getNewStopWatch();

		getParameterBusiness().salvarCalidadSolicitante(acs_parametros, ab_accion, as_userId, as_remoteIp);

		Logger.log(lsw_watch, "ParameterBean", "salvarCalidadsolicitante", as_userId, as_localIp, as_remoteIp, null);
	}

	/** {@inheritdoc} */
	public void salvarCampoCriterioBusqueda(
	    CamposConsulta atr_parametros, boolean ab_accion, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch lsw_watch;

		lsw_watch = Logger.getNewStopWatch();

		getParameterBusiness().salvarCampoCriterioBusqueda(atr_parametros, ab_accion, as_userId, as_remoteIp);

		Logger.log(lsw_watch, "ParameterBean", "salvarCampoCriterioBusqueda", as_userId, as_localIp, as_remoteIp, null);
	}

	/** {@inheritdoc} */
	public void salvarCamposCertificado(
	    CamposCertificado acc_parametros, boolean ab_accion, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch lsw_watch;

		lsw_watch = Logger.getNewStopWatch();

		getParameterBusiness().salvarCamposCertificado(acc_parametros, ab_accion, as_userId, as_remoteIp);

		Logger.log(lsw_watch, "ParameterBean", "salvarCamposCertificado", as_userId, as_localIp, as_remoteIp, null);
	}

	/** {@inheritdoc} */
	public void salvarCamposConsulta(
	    CamposConsulta atr_parametros, boolean ab_accion, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch lsw_watch;

		lsw_watch = Logger.getNewStopWatch();

		getParameterBusiness().salvarCamposConsulta(atr_parametros, ab_accion, as_userId, as_remoteIp);

		Logger.log(lsw_watch, "ParameterBean", "salvarCamposConsulta", as_userId, as_localIp, as_remoteIp, null);
	}

	/** {@inheritdoc} */
	public void salvarCamposCorreccion(
	    CamposCorreccion acc_parametros, boolean ab_accion, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch lsw_watch;

		lsw_watch = Logger.getNewStopWatch();

		getParameterBusiness().salvarCamposCorreccion(acc_parametros, ab_accion, as_userId, as_remoteIp);

		Logger.log(lsw_watch, "ParameterBean", "salvarCamposCorreccion", as_userId, as_localIp, as_remoteIp, null);
	}

	/** {@inheritdoc} */
	public void salvarCanal(
	    Canal ac_parametros, boolean ab_insertar, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch lsw_watch;

		lsw_watch = Logger.getNewStopWatch();

		getParameterBusiness().salvarCanal(ac_parametros, ab_insertar, as_userId, as_remoteIp);

		Logger.log(lsw_watch, "ParameterBean", "salvarCanal", as_userId, as_localIp, as_remoteIp, null);
	}

	/**
	 * Método para guardar un registro en la base de datos en la tabla SDB_PGN_CANAL_ORIGEN_SERVICIO.
	 *
	 * @param acos_parametros de acos parametros
	 * @param ab_accion de ab accion
	 * @param as_userId de as user id
	 * @param as_localIp de as local ip
	 * @param as_remoteIp de as remote ip
	 * @throws B2BException de b 2 B exception
	 */
	public void salvarCanalOrigenServicio(
	    CanalOrigenServicio acos_parametros, boolean ab_accion, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch lsw_watch;

		lsw_watch = Logger.getNewStopWatch();

		getParameterBusiness().insertUpdateCanalOrigenServicio(acos_parametros, ab_accion, as_userId, as_remoteIp);

		Logger.log(lsw_watch, "ParameterBean", "salvarCanalOrigenServicio", as_userId, as_localIp, as_remoteIp, null);
	}

	/** {@inheritdoc} */
	public void salvarCatastro(
	    Catastro ac_parametros, boolean ab_accion, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch lsw_watch;

		lsw_watch = Logger.getNewStopWatch();

		getParameterBusiness().salvarCatastro(ac_parametros, ab_accion, as_userId, as_remoteIp);

		Logger.log(lsw_watch, "ParameterBean", "salvarCatastro", as_userId, as_localIp, as_remoteIp, null);
	}

	/** {@inheritdoc} */
	public void salvarCausalAprobacionDevolucion(
	    CausalAprobacionDevolucion atr_parametros, boolean ab_accion, String as_userId, String as_localIp,
	    String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch lsw_watch;

		lsw_watch = Logger.getNewStopWatch();

		getParameterBusiness().salvarCausalAprobacionDevolucion(atr_parametros, ab_accion, as_userId, as_remoteIp);

		Logger.log(
		    lsw_watch, "ParameterBean", "salvarCausalAprobacionDevolucion", as_userId, as_localIp, as_remoteIp, null
		);
	}

	/**
	 * Método para salvar la insercion o modificacion para la tabla SDB_PGN_CAUSAL_CORRECCION.
	 *
	 * @param atr_parametros objeto para insertar o actualizar en la base de datos
	 * @param ab_accion indica si se debe insertar o actualizar
	 * @param as_userId usuario en sesion
	 * @param as_localIp ip del servidor
	 * @param as_remoteIp ip de donde se realiza la solicitud de inserción o actualización
	 * @throws B2BException de b 2 B exception
	 */
	public void salvarCausalCorreccion(
	    CausalCorreccion atr_parametros, boolean ab_accion, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch lsw_watch;

		lsw_watch = Logger.getNewStopWatch();

		getParameterBusiness().salvarCausalCorreccion(atr_parametros, ab_accion, as_userId, as_remoteIp);

		Logger.log(lsw_watch, "ParameterBean", "salvarCausalCorreccion", as_userId, as_localIp, as_remoteIp, null);
	}

	/**
	 * Método para guardar los cambios o la inserción de causal mayyor valor.
	 *
	 * @param atd_parametros de atd parametros
	 * @param ab_accion de ab accion
	 * @param as_userId de as user id
	 * @param as_localIp de as local ip
	 * @param as_remoteIp de as remote ip
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void salvarCausalMayorValor(
	    CausalMayorValor atd_parametros, boolean ab_accion, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch lsw_watch;

		lsw_watch = Logger.getNewStopWatch();

		getParameterBusiness().salvarCausalMayorValor(atd_parametros, ab_accion, as_userId, as_remoteIp);

		Logger.log(lsw_watch, "ParameterBean", "salvarCausalMayorValor", as_userId, as_localIp, as_remoteIp, null);
	}

	/** {@inheritdoc} */
	public void salvarCausalNegacionCopias(
	    CausalNegacionCopias atr_parametros, boolean ab_accion, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch lsw_watch;

		lsw_watch = Logger.getNewStopWatch();

		getParameterBusiness().salvarCausalNegacionCopias(atr_parametros, ab_accion, as_userId, as_remoteIp);

		Logger.log(lsw_watch, "ParameterBean", "salvarCausalNegacionCopias", as_userId, as_localIp, as_remoteIp, null);
	}

	/** {@inheritdoc} */
	public void salvarCausalRechazoRecurso(
	    CausalRechazoRecurso acrr_parametros, boolean ab_accion, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch lsw_watch;

		lsw_watch = Logger.getNewStopWatch();

		getParameterBusiness().salvarCausalRechazoRecurso(acrr_parametros, ab_accion, as_userId, as_remoteIp);

		Logger.log(lsw_watch, "ParameterBean", "salvarCausalRechazoRecurso", as_userId, as_localIp, as_remoteIp, null);
	}

	/**
	 * Método para guardar los cambios o la inserción de CausalReimpresion.
	 *
	 * @param acr_parametros de acr parametros
	 * @param ab_accion de ab accion
	 * @param as_userId de as user id
	 * @param as_localIp de as local ip
	 * @param as_remoteIp de as remote ip
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void salvarCausalReimpresion(
	    CausalReimpresion acr_parametros, boolean ab_accion, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch lsw_watch;

		lsw_watch = Logger.getNewStopWatch();

		getParameterBusiness().salvarCausalReimpresion(acr_parametros, ab_accion, as_userId, as_remoteIp);

		Logger.log(lsw_watch, "ParameterBean", "salvarCausalReimpresion", as_userId, as_localIp, as_remoteIp, null);
	}

	/** {@inheritdoc} */
	public void salvarCirculoActAdmin(
	    CirculoActAdmin acaa_parametros, boolean ab_accion, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch lsw_watch;

		lsw_watch = Logger.getNewStopWatch();

		getParameterBusiness().salvarCirculoActAdmin(acaa_parametros, ab_accion, as_userId, as_remoteIp);

		Logger.log(lsw_watch, "ParameterBean", "salvarCirculoActAdmin", as_userId, as_localIp, as_remoteIp, null);
	}

	/** {@inheritdoc} */
	public void salvarCirculoCatastro(
	    CirculoCatastro acc_parametros, boolean ab_accion, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch lsw_watch;

		lsw_watch = Logger.getNewStopWatch();

		getParameterBusiness().salvarCirculoCatastro(acc_parametros, ab_accion, as_userId, as_remoteIp);

		Logger.log(lsw_watch, "ParameterBean", "salvarCirculoCatastro", as_userId, as_localIp, as_remoteIp, null);
	}

	/** {@inheritdoc} */
	public void salvarCirculoFestivo(
	    CirculoFestivo atr_parametros, boolean ab_accion, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch lsw_watch;

		lsw_watch = Logger.getNewStopWatch();

		getParameterBusiness().salvarCirculoFestivo(atr_parametros, ab_accion, as_userId, as_remoteIp);

		Logger.log(lsw_watch, "ParameterBean", "salvarCirculoFestivo", as_userId, as_localIp, as_remoteIp, null);
	}

	/** {@inheritdoc} */
	public void salvarCirculoOrigenDestino(
	    CirculoOrigenDestino lpc_parametros, boolean ab_insertar, String as_userId, String as_localIpAddress,
	    String as_remoteIpAddress
	)
	    throws B2BException
	{
		StopWatch lsw_watch;

		lsw_watch = Logger.getNewStopWatch();

		getParameterBusiness().salvarCirculoOrigenDestino(lpc_parametros, ab_insertar, as_userId, as_remoteIpAddress);

		Logger.log(
		    lsw_watch, "ParameterBean", "salvarAccEntidadExterna", as_userId, as_localIpAddress, as_remoteIpAddress,
		    null
		);
	}

	/** {@inheritdoc} */
	public void salvarComunidadesEtnicas(
	    Collection<ComunidadesEtnicas> acce_parametros, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch lsw_watch;

		lsw_watch = Logger.getNewStopWatch();

		getParameterBusiness().salvarComunidadesEtnicas(acce_parametros, as_userId, as_remoteIp);

		Logger.log(lsw_watch, "ParameterBean", "salvarComunidadesEtnicas", as_userId, as_localIp, as_remoteIp, null);
	}

/**
 * Método para salvar la insercion o modificacion para la tabla SDB_PGN_CONDICION_TARIFA.
 *
 * @param atr_parametros Objeto a insertar o actualizar
 * @param ab_accion indica si se va a insertar o actualizar
 * @param as_userId usuario en sesion
 * @param as_localIp ip del servidor
 * @param as_remoteIp ip de donde se realiza la inserción o actualizacón
 * @throws B2BException de b 2 B exception
 */
	public void salvarCondicionTarifa(
	    CondicionTarifa atr_parametros, boolean ab_accion, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch lsw_watch;

		lsw_watch = Logger.getNewStopWatch();

		getParameterBusiness().salvarCondicionTarifa(atr_parametros, ab_accion, as_userId, as_remoteIp);

		Logger.log(lsw_watch, "ParameterBean", "salvarCondicionTarifa", as_userId, as_localIp, as_remoteIp, null);
	}

	/**
	 * Método para salvar la insercion o modificacion para la tabla SDB_PGN_MOTIVO_TRAMITE.
	 *
	 * @param ac_parametros de ac parametros
	 * @param ab_accion de ab accion
	 * @throws B2BException de b 2 B exception
	 */
	public void salvarConstante(Constantes ac_parametros, boolean ab_accion)
	    throws B2BException
	{
		StopWatch lsw_watch;

		lsw_watch = Logger.getNewStopWatch();

		getParameterBusiness().salvarConstantes(ac_parametros, ab_accion);

		Logger.log(lsw_watch, "ParameterBean", "salvarConstante", null, null, null, null);
	}

	/** {@inheritdoc} */
	public void salvarConstanteCYN(Constantes ac_parametros, boolean ab_accion)
	    throws B2BException
	{
		StopWatch lsw_watch;

		lsw_watch = Logger.getNewStopWatch();

		getParameterBusiness().salvarConstantesCYN(ac_parametros, ab_accion);

		Logger.log(lsw_watch, "ParameterBean", "salvarConstanteCYN", null, null, null, null);
	}

	/** {@inheritdoc} */
	public void salvarConsultas(
	    Consultas atr_parametros, boolean ab_accion, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch lsw_watch;

		lsw_watch = Logger.getNewStopWatch();

		getParameterBusiness().salvarConsultas(atr_parametros, ab_accion, as_userId, as_remoteIp);

		Logger.log(lsw_watch, "ParameterBean", "salvarConsultas", as_userId, as_localIp, as_remoteIp, null);
	}

	/**
	 * Método para salvar la insercion o modificacion para la tabla SDB_PNG_COORDENADA.
	 *
	 * @param ac_parametros Objeto a insertar o actualizar
	 * @param ab_accion indica si se va a insertar o actualizar
	 * @param as_userId usuario en sesion
	 * @param as_localIp ip del servidor
	 * @param as_remoteIp ip de donde se realiza la inserción o actualizacón
	 * @throws B2BException de b 2 B exception
	 */
	public void salvarCoordenada(
	    Coordenada ac_parametros, boolean ab_accion, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch lsw_watch;

		lsw_watch = Logger.getNewStopWatch();

		getParameterBusiness().salvarCoordenada(ac_parametros, ab_accion, as_userId, as_remoteIp);

		Logger.log(lsw_watch, "ParameterBean", "salvarCoordenada", as_userId, as_localIp, as_remoteIp, null);
	}

	/** {@inheritdoc} */
	public void salvarDepartamento(
	    Departamento ac_parametros, boolean ab_accion, String as_usuario, String as_ipLocal, String as_ipRemota
	)
	    throws B2BException
	{
		StopWatch lsw_watch;

		lsw_watch = Logger.getNewStopWatch();

		getParameterBusiness().salvarDepartamento(ac_parametros, ab_accion, as_usuario, as_ipRemota);

		Logger.log(lsw_watch, "ParameterBean", "salvarDepartamento", as_usuario, as_ipLocal, as_ipRemota, null);
	}

	/** {@inheritdoc} */
	public void salvarDependenciaSNR(
	    DependenciaSNR adsnr_parametros, boolean ab_accion, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch lsw_watch;

		lsw_watch = Logger.getNewStopWatch();

		getParameterBusiness().salvarDependenciaSNR(adsnr_parametros, ab_accion, as_userId, as_remoteIp);

		Logger.log(lsw_watch, "ParameterBean", "salvarDependenciaSNR", as_userId, as_localIp, as_remoteIp, null);
	}

	/**
	 * Método para guardar los cambios o la inserción de DetalleProcesoConsulta.
	 *
	 * @param adpc_parametros de adpc parametros
	 * @param ab_accion de ab accion
	 * @param as_userId de as user id
	 * @param as_localIp de as local ip
	 * @param as_remoteIp de as remote ip
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void salvarDetalleProcesoConsulta(
	    DetalleProcesoConsulta adpc_parametros, boolean ab_accion, String as_userId, String as_localIp,
	    String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch lsw_watch;

		lsw_watch = Logger.getNewStopWatch();

		getParameterBusiness().salvarDetalleProcesoConsulta(adpc_parametros, ab_accion, as_userId, as_remoteIp);

		Logger.log(
		    lsw_watch, "ParameterBean", "salvarDetalleProcesoConsulta", as_userId, as_localIp, as_remoteIp, null
		);
	}

	/** {@inheritdoc} */
	public void salvarDominioRRR(
	    DominioRrr ldr_parametros, boolean ab_insertar, String as_userId, String as_ipLocal, String as_ipRemote
	)
	    throws B2BException
	{
		StopWatch lsw_watch;

		lsw_watch = Logger.getNewStopWatch();

		getParameterBusiness().salvarDominioRRR(ldr_parametros, ab_insertar, as_userId, as_ipRemote);

		Logger.log(lsw_watch, "ParameterBean", "salvarDominioRRR", as_userId, as_ipLocal, as_ipRemote, null);
	}

	/**
	 * Método para guardar un registro en la base de datos en la tabla SDB_PGN_ENTIDAD_RECAUDADORA.
	 *
	 * @param aer_parametros de aer parametros
	 * @param ab_accion de ab accion
	 * @param as_userId de as user id
	 * @param as_localIp de as local ip
	 * @param as_remoteIp de as remote ip
	 * @throws B2BException de b 2 B exception
	 */
	public void salvarEntidadRecaudadora(
	    EntidadRecaudadora aer_parametros, boolean ab_accion, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch lsw_watch;

		lsw_watch = Logger.getNewStopWatch();

		getParameterBusiness().insertUpdateEntidadRecaudadora(aer_parametros, ab_accion, as_userId, as_remoteIp);

		Logger.log(lsw_watch, "ParameterBean", "salvarEntidadRecaudadora", as_userId, as_localIp, as_remoteIp, null);
	}

	/**
	 * Método para realizar la insercion para las tablas SDB_ACC_ENTIDAD_EXTERNA Y SDB_ACC_ENTIDAD_EXTERNA_PERSONA.
	 *
	 * @param aaee_parametros de aaee parametros
	 * @param ap_parametros de ap parametros
	 * @param as_userId de as user id
	 * @param as_localIp de as local ip
	 * @param as_remoteIp de as remote ip
	 * @throws B2BException de b 2 B exception
	 */
	public void salvarEntidades(
	    AccEntidadExterna aaee_parametros, Collection<Persona> ap_parametros, String as_userId, String as_localIp,
	    String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch lsw_watch;

		lsw_watch = Logger.getNewStopWatch();

		getParameterBusiness().salvarEntidades(aaee_parametros, ap_parametros, as_userId, as_remoteIp);

		Logger.log(lsw_watch, "ParameterBean", "salvarEntidades", as_userId, as_localIp, as_remoteIp, null);
	}

	/** {@inheritdoc} */
	public void salvarEntidadesAlerta(
	    EntidadesAlerta aea_parametros, boolean ab_accion, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch lsw_watch;

		lsw_watch = Logger.getNewStopWatch();

		getParameterBusiness().salvarEntidadesAlerta(aea_parametros, ab_accion, as_userId, as_remoteIp);

		Logger.log(lsw_watch, "ParameterBean", "salvarEntidadesAlerta", as_userId, as_localIp, as_remoteIp, null);
	}

	/**
	 * Método para realizar la modificacion para las tablas SDB_ACC_ENTIDAD_EXTERNA Y SDB_ACC_ENTIDAD_EXTERNA_PERSONA.
	 *
	 * @param aaee_parametros de aaee parametros
	 * @param ap_parametros de ap parametros
	 * @param as_userId de as user id
	 * @param as_localIp de as local ip
	 * @param as_remoteIp de as remote ip
	 * @throws B2BException de b 2 B exception
	 */
	public void salvarEntidadesModificadas(
	    AccEntidadExterna aaee_parametros, Collection<Persona> ap_parametros, String as_userId, String as_localIp,
	    String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch lsw_watch;

		lsw_watch = Logger.getNewStopWatch();

		getParameterBusiness().salvarEntidadesModificadas(aaee_parametros, ap_parametros, as_userId, as_remoteIp);

		Logger.log(lsw_watch, "ParameterBean", "salvarEntidadesModificadas", as_userId, as_localIp, as_remoteIp, null);
	}

	/** {@inheritdoc} */
	public void salvarEstadoActividad(
	    EstadoActividad ac_parametros, boolean ab_accion, String as_usuario, String as_ipRemota, String as_ipLocal
	)
	    throws B2BException
	{
		StopWatch lsw_watch;

		lsw_watch = Logger.getNewStopWatch();

		getParameterBusiness().salvarEstadoActividad(ac_parametros, ab_accion, as_usuario, as_ipRemota);

		Logger.log(lsw_watch, "ParameterBean", "salvarEstadoActividad", as_usuario, as_ipLocal, as_ipRemota, null);
	}

	/**
	 * Método para guardar o insertar un registro en la tabla SDB_PNG_ESTADO_ANOTACION.
	 *
	 * @param aea_parametros de aea parametros
	 * @param ab_accion de ab accion
	 * @param as_userId de as user id
	 * @param as_localIp de as local ip
	 * @param as_remoteIp de as remote ip
	 * @throws B2BException de b 2 B exception
	 */
	public void salvarEstadoAnotacion(
	    EstadoAnotacion aea_parametros, boolean ab_accion, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch lsw_watch;

		lsw_watch = Logger.getNewStopWatch();

		getParameterBusiness().insertUpdateEstadoAnotacion(aea_parametros, ab_accion, as_userId, as_remoteIp);

		Logger.log(lsw_watch, "ParameterBean", "salvarEstadoAnotacion", as_userId, as_localIp, as_remoteIp, null);
	}

	/** {@inheritdoc} */
	public void salvarEstadoNupre(
	    EstadoNupre aen_parametros, boolean ab_accion, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch lsw_watch;

		lsw_watch = Logger.getNewStopWatch();

		getParameterBusiness().insertUpdateEstadoNupre(aen_parametros, ab_accion, as_userId, as_remoteIp);

		Logger.log(lsw_watch, "ParameterBean", "salvarEstadoNupre", as_userId, as_localIp, as_remoteIp, null);
	}

	/** {@inheritdoc} */
	public void salvarEstadoPredio(
	    EstadoPredio aep_parametros, boolean ab_accion, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch lsw_watch;

		lsw_watch = Logger.getNewStopWatch();

		getParameterBusiness().insertUpdateEstadoPredio(aep_parametros, ab_accion, as_userId, as_remoteIp);

		Logger.log(lsw_watch, "ParameterBean", "salvarEstadoPredio", as_userId, as_localIp, as_remoteIp, null);
	}

	/**
	 * Método para guardar o insertar un registro en la tabla SDB_PNG_ESTADO_REGISTRO.
	 *
	 * @param aer_parametros de aer parametros
	 * @param ab_accion de ab accion
	 * @param as_userId de as user id
	 * @param as_localIp de as local ip
	 * @param as_remoteIp de as remote ip
	 * @throws B2BException de b 2 B exception
	 */
	public void salvarEstadoRegistro(
	    EstadoRegistro aer_parametros, boolean ab_accion, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch lsw_watch;

		lsw_watch = Logger.getNewStopWatch();

		getParameterBusiness().insertUpdateEstadoRegistro(aer_parametros, ab_accion, as_userId, as_remoteIp);

		Logger.log(lsw_watch, "ParameterBean", "salvarEstadoRegistro", as_userId, as_localIp, as_remoteIp, null);
	}

	/** {@inheritdoc} */
	public void salvarEstados(
	    Estados ac_parametros, boolean ab_insertar, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch lsw_watch;

		lsw_watch = Logger.getNewStopWatch();

		getParameterBusiness().salvarEstados(ac_parametros, ab_insertar, as_userId, as_remoteIp);

		Logger.log(lsw_watch, "ParameterBean", "salvarEstados", as_userId, as_localIp, as_remoteIp, null);
	}

	/** {@inheritdoc} */
	public void salvarFestivoNacional(
	    FestivoNacional ac_parametros, boolean ab_accion, String as_usuario, String as_ipRemota, String as_ipLocal
	)
	    throws B2BException
	{
		StopWatch lsw_watch;

		lsw_watch = Logger.getNewStopWatch();

		getParameterBusiness().salvarFestivoNacional(ac_parametros, ab_accion, as_usuario, as_ipRemota);

		Logger.log(lsw_watch, "ParameterBean", "salvarFestivoNacional", as_usuario, as_ipLocal, as_ipRemota, null);
	}

	/** {@inheritdoc} */
	public void salvarGobernacion(
	    Gobernacion ac_parametros, boolean ab_accion, String as_usuario, String as_ipRemota, String as_ipLocal
	)
	    throws B2BException
	{
		StopWatch lsw_watch;

		lsw_watch = Logger.getNewStopWatch();

		getParameterBusiness().salvarGobernacion(ac_parametros, ab_accion, as_usuario, as_ipRemota);

		Logger.log(lsw_watch, "ParameterBean", "salvarGobernacion", as_usuario, as_ipLocal, as_ipRemota, null);
	}

	/**
	 * Método para guardar o insertar un registro en la tabla SDB_PNG_GRUPO_NATURALEZA_JURIDICA.
	 *
	 * @param agnj_parametros de agnj parametros
	 * @param ab_accion de ab accion
	 * @param as_userId de as user id
	 * @param as_localIp de as local ip
	 * @param as_remoteIp de as remote ip
	 * @throws B2BException de b 2 B exception
	 */
	public void salvarGrupoNaturalezaJuridica(
	    GrupoNaturalezaJuridica agnj_parametros, boolean ab_accion, String as_userId, String as_localIp,
	    String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch lsw_watch;

		lsw_watch = Logger.getNewStopWatch();

		getParameterBusiness().insertUpdateGrupoNaturalezaJuridica(agnj_parametros, ab_accion, as_userId, as_remoteIp);

		Logger.log(
		    lsw_watch, "ParameterBean", "salvarGrupoNaturalezaJuridica", as_userId, as_localIp, as_remoteIp, null
		);
	}

	/** {@inheritdoc} */
	public void salvarInstanciaConsulta(
	    InstanciaConsulta ac_parametros, boolean ab_accion, String as_usuario, String as_ipRemota, String as_ipLocal
	)
	    throws B2BException
	{
		StopWatch lsw_watch;

		lsw_watch = Logger.getNewStopWatch();

		getParameterBusiness().salvarInstanciaConsulta(ac_parametros, ab_accion, as_usuario, as_ipRemota);

		Logger.log(lsw_watch, "ParameterBean", "salvarInstanciaConsulta", as_usuario, as_ipLocal, as_ipRemota, null);
	}

	/** {@inheritdoc} */
	public void salvarInteresadoNaturalGenero(
	    InteresadoNaturalGenero atp_parametros, boolean ab_accion, String as_usuario, String as_ipLocal,
	    String as_ipRemota
	)
	    throws B2BException
	{
		StopWatch lsw_watch;

		lsw_watch = Logger.getNewStopWatch();

		getParameterBusiness().salvarInteresadoNaturalGenero(atp_parametros, ab_accion, as_usuario, as_ipRemota);

		Logger.log(
		    lsw_watch, "ParameterBean", "salvarInteresadoNaturalGenero", as_usuario, as_ipLocal, as_ipRemota, null
		);
	}

	/**
	 * Método para salvar la insercion o modificacion para la tabla SDB_PNG_LETRA_EJE.
	 *
	 * @param ale_parametros Objeto a insertar o actualizar
	 * @param ab_accion indica si se va a insertar o actualizar
	 * @param as_userId usuario en sesion
	 * @param as_localIp ip del servidor
	 * @param as_remoteIp ip de donde se realiza la inserción o actualizacón
	 * @throws B2BException de b 2 B exception
	 */
	public void salvarLetraEje(
	    LetraEje ale_parametros, boolean ab_accion, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch lsw_watch;

		lsw_watch = Logger.getNewStopWatch();

		getParameterBusiness().salvarLetraEje(ale_parametros, ab_accion, as_userId, as_remoteIp);

		Logger.log(lsw_watch, "ParameterBean", "salvarLetraEje", as_userId, as_localIp, as_remoteIp, null);
	}

	/** {@inheritdoc} */
	public void salvarLibroAntiguoSistema(
	    LibroAntiguoSistema ac_parametros, boolean ab_accion, String as_usuario, String as_ipRemota, String as_ipLocal
	)
	    throws B2BException
	{
		StopWatch lsw_watch;

		lsw_watch = Logger.getNewStopWatch();

		getParameterBusiness().salvarLibroAntiguoSistema(ac_parametros, ab_accion, as_usuario, as_ipRemota);

		Logger.log(lsw_watch, "ParameterBean", "salvarLibroAntiguoSistema", as_usuario, as_ipLocal, as_ipRemota, null);
	}

	/** {@inheritdoc} */
	public void salvarLibroTestamento(
	    LibroTestamento alt_parametros, boolean ab_accion, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch lsw_watch;

		lsw_watch = Logger.getNewStopWatch();

		getParameterBusiness().salvarLibroTestamento(alt_parametros, ab_accion, as_userId, as_remoteIp);

		Logger.log(lsw_watch, "ParameterBean", "salvarLibroTestamento", as_userId, as_localIp, as_remoteIp, null);
	}

	/** {@inheritdoc} */
	public void salvarLineaProduccion(
	    LineaProduccion alp_parametros, boolean ab_accion, String as_usuario, String as_ipLocal, String as_ipRemota
	)
	    throws B2BException
	{
		StopWatch lsw_watch;

		lsw_watch = Logger.getNewStopWatch();

		getParameterBusiness().salvarLineaProduccion(alp_parametros, ab_accion, as_usuario, as_ipRemota);

		Logger.log(lsw_watch, "ParameterBean", "salvarLineaProduccion", as_usuario, as_ipLocal, as_ipRemota, null);
	}

	/** {@inheritdoc} */
	public void salvarMedidaArea(
	    MedidaArea atr_parametros, boolean ab_accion, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch lsw_watch;

		lsw_watch = Logger.getNewStopWatch();

		getParameterBusiness().salvarMedidaArea(atr_parametros, ab_accion, as_userId, as_remoteIp);

		Logger.log(lsw_watch, "ParameterBean", "salvarMedidaArea", as_userId, as_localIp, as_remoteIp, null);
	}

	/**
	 * Método para guardar un registro en la base de datos en la tabla SDB_PGN_MEDIO_RECAUDO.
	 *
	 * @param amr_parametros de amr parametros
	 * @param ab_accion de ab accion
	 * @param as_userId de as user id
	 * @param as_localIp de as local ip
	 * @param as_remoteIp de as remote ip
	 * @throws B2BException de b 2 B exception
	 */
	public void salvarMedioRecaudo(
	    MedioRecaudo amr_parametros, boolean ab_accion, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch lsw_watch;

		lsw_watch = Logger.getNewStopWatch();

		getParameterBusiness().insertUpdateMedioRecaudo(amr_parametros, ab_accion, as_userId, as_remoteIp);

		Logger.log(lsw_watch, "ParameterBean", "salvarMedioRecaudo", as_userId, as_localIp, as_remoteIp, null);
	}

	/**
	 * Método para guardar un registro en la base de datos en la tabla SDB_PGN_MENSAJE_RESPUESTA.
	 *
	 * @param amr_parametros de amr parametros
	 * @param ab_accion de ab accion
	 * @param as_userId de as user id
	 * @param as_localIp de as local ip
	 * @param as_remoteIp de as remote ip
	 * @throws B2BException de b 2 B exception
	 */
	public void salvarMensajeRespuesta(
	    MensajeRespuesta amr_parametros, boolean ab_accion, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch lsw_watch;

		lsw_watch = Logger.getNewStopWatch();

		getParameterBusiness().insertUpdateMensajeRespuesta(amr_parametros, ab_accion, as_userId, as_remoteIp);

		Logger.log(lsw_watch, "ParameterBean", "salvarMensajeRespuesta", as_userId, as_localIp, as_remoteIp, null);
	}

	/** {@inheritdoc} */
	public void salvarMotivoTramite(
	    MotivoTramite amt_parametros, boolean ab_accion, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch lsw_watch;

		lsw_watch = Logger.getNewStopWatch();

		getParameterBusiness().salvarMotivoTramite(amt_parametros, ab_accion, as_userId, as_remoteIp);

		Logger.log(lsw_watch, "ParameterBean", "salvarMotivoTramite", as_userId, as_localIp, as_remoteIp, null);
	}

	/** {@inheritdoc} */
	public void salvarMunicipio(
	    Municipio ac_parametros, boolean ab_accion, String as_usuario, String as_ipLocal, String as_ipRemota
	)
	    throws B2BException
	{
		StopWatch lsw_watch;

		lsw_watch = Logger.getNewStopWatch();

		getParameterBusiness().salvarMunicipio(ac_parametros, ab_accion, as_usuario, as_ipRemota);

		Logger.log(lsw_watch, "ParameterBean", "salvarMunicipio", as_usuario, as_ipLocal, as_ipRemota, null);
	}

	/**
	 * Método para guardar los cambios o la inserción de NaturalezJuridica.
	 *
	 * @param anj_parametros de anj parametros
	 * @param ab_accion de ab accion
	 * @param as_userId de as user id
	 * @param as_localIp de as local ip
	 * @param as_remoteIp de as remote ip
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void salvarNaturalezaJuridica(
	    NaturalezaJuridica anj_parametros, boolean ab_accion, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch lsw_watch;

		lsw_watch = Logger.getNewStopWatch();

		getParameterBusiness().salvarNaturalezaJuridica(anj_parametros, ab_accion, as_userId, as_remoteIp);

		Logger.log(lsw_watch, "ParameterBean", "salvarNaturalezaJuridica", as_userId, as_localIp, as_remoteIp, null);
	}

	/** {@inheritdoc} */
	public void salvarNumeracionOficiosCirculo(
	    NumeracionOficiosCirculo anoc_parametros, boolean ab_accion, String as_userId, String as_localIp,
	    String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch lsw_watch;

		lsw_watch = Logger.getNewStopWatch();

		getParameterBusiness().salvarNumeracionOficiosCirculo(anoc_parametros, ab_accion, as_userId, as_remoteIp);

		Logger.log(
		    lsw_watch, "ParameterBean", "salvarNumeracionOficiosCirculo", as_userId, as_localIp, as_remoteIp, null
		);
	}

	/** {@inheritdoc} */
	public void salvarOficinaOrigen(
	    OficinaOrigen amt_parametros, boolean ab_accion, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch lsw_watch;

		lsw_watch = Logger.getNewStopWatch();

		getParameterBusiness().salvarOficinaOrigen(amt_parametros, ab_accion, as_userId, as_remoteIp);

		Logger.log(lsw_watch, "ParameterBean", "salvarOficinaOrigen", as_userId, as_localIp, as_remoteIp, null);
	}

	/**
	 * Método para guardar los cambios o la inserción de OpcionEtapa.
	 *
	 * @param aoe_parametros de aoe parametros
	 * @param as_userId de as user id
	 * @param as_localIp de as local ip
	 * @param as_remoteIp de as remote ip
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void salvarOpcionEtapa(OpcionEtapa aoe_parametros, String as_userId, String as_localIp, String as_remoteIp)
	    throws B2BException
	{
		StopWatch lsw_watch;

		lsw_watch = Logger.getNewStopWatch();

		getParameterBusiness().salvarOpcionEtapa(aoe_parametros, as_userId, as_remoteIp);

		Logger.log(lsw_watch, "ParameterBean", "salvarOpcionEtapa", as_userId, as_localIp, as_remoteIp, null);
	}

	/**
	 * Método para guardar los cambios o la inserción de Opcion.
	 *
	 * @param ao_parametros de ao parametros
	 * @param as_userId de as user id
	 * @param as_localIp de as local ip
	 * @param as_remoteIp de as remote ip
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void salvarOpcionInsertar(Opcion ao_parametros, String as_userId, String as_localIp, String as_remoteIp)
	    throws B2BException
	{
		StopWatch lsw_watch;

		lsw_watch = Logger.getNewStopWatch();

		getParameterBusiness().salvarOpcionInsertar(ao_parametros, as_userId, as_remoteIp);

		Logger.log(lsw_watch, "ParameterBean", "salvarOpcionInsertar", as_userId, as_localIp, as_remoteIp, null);
	}

	/**
	 * Método para guardar los cambios o la inserción de Opcion.
	 *
	 * @param ao_parametros de ao parametros
	 * @param ace_opcionEtapa de ace opcion etapa
	 * @param as_userId de as user id
	 * @param as_localIp de as local ip
	 * @param as_remoteIp de as remote ip
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void salvarOpcionModificar(
	    Opcion ao_parametros, Collection<Etapa> ace_opcionEtapa, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch lsw_watch;

		lsw_watch = Logger.getNewStopWatch();

		getParameterBusiness().salvarOpcionModificar(ao_parametros, ace_opcionEtapa, as_userId, as_remoteIp);

		Logger.log(lsw_watch, "ParameterBean", "salvarOpcionModificar", as_userId, as_localIp, as_remoteIp, null);
	}

	/** {@inheritdoc} */
	public void salvarOrigen(
	    Origen ao_parametros, boolean ab_insertar, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch lsw_watch;

		lsw_watch = Logger.getNewStopWatch();

		getParameterBusiness().salvarOrigen(ao_parametros, ab_insertar, as_userId, as_remoteIp);

		Logger.log(lsw_watch, "ParameterBean", "salvarOrigen", as_userId, as_localIp, as_remoteIp, null);
	}

	/** {@inheritdoc} */
	public void salvarPais(
	    Pais ac_parametros, boolean ab_accion, String as_usuario, String as_ipLocal, String as_ipRemota
	)
	    throws B2BException
	{
		StopWatch lsw_watch;

		lsw_watch = Logger.getNewStopWatch();

		getParameterBusiness().salvarPais(ac_parametros, ab_accion, as_usuario, as_ipRemota);

		Logger.log(lsw_watch, "ParameterBean", "salvarPais", as_usuario, as_ipLocal, as_ipRemota, null);
	}

	/** {@inheritdoc} */
	public void salvarParteInteresada(
	    ParteInteresada ac_parametros, boolean ab_accion, String as_usuario, String as_ipLocal, String as_ipRemota
	)
	    throws B2BException
	{
		StopWatch lsw_watch;

		lsw_watch = Logger.getNewStopWatch();

		getParameterBusiness().salvarParteInteresada(ac_parametros, ab_accion, as_usuario, as_ipRemota);

		Logger.log(lsw_watch, "ParameterBean", "salvarParteInteresada", as_usuario, as_ipLocal, as_ipRemota, null);
	}

	/** {@inheritdoc} */
	public void salvarPlantilla(
	    Plantilla ap_parametros, boolean ab_insertar, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch lsw_watch;

		lsw_watch = Logger.getNewStopWatch();

		getParameterBusiness().salvarPlantilla(ap_parametros, ab_insertar, as_userId, as_remoteIp);

		Logger.log(lsw_watch, "ParameterBean", "salvarPlantilla", as_userId, as_localIp, as_remoteIp, null);
	}

	/** {@inheritdoc} */
	public void salvarPlantillaComunicacion(
	    PlantillaComunicacion ap_parametros, boolean ab_insertar, String as_userId, String as_localIp,
	    String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch lsw_watch;

		lsw_watch = Logger.getNewStopWatch();

		getParameterBusiness().salvarPlantillaComunicacion(ap_parametros, ab_insertar, as_userId, as_remoteIp);

		Logger.log(lsw_watch, "ParameterBean", "salvarPlantillaComunicacion", as_userId, as_localIp, as_remoteIp, null);
	}

	/** {@inheritdoc} */
	public void salvarPlantillaNotificacion(
	    PlantillaNotificacion ap_parametros, boolean ab_insertar, String as_userId, String as_localIp,
	    String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch lsw_watch;

		lsw_watch = Logger.getNewStopWatch();

		getParameterBusiness().salvarPlantillaNotificacion(ap_parametros, ab_insertar, as_userId, as_remoteIp);

		Logger.log(lsw_watch, "ParameterBean", "salvarPlantillaNotificacion", as_userId, as_localIp, as_remoteIp, null);
	}

	/**
	 * Método para guardar los cambios o la inserción de Proceso.
	 *
	 * @param ap_parametros de ap parametros
	 * @param ab_accion de ab accion
	 * @param as_userId de as user id
	 * @param as_localIp de as local ip
	 * @param as_remoteIp de as remote ip
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void salvarProceso(
	    Proceso ap_parametros, boolean ab_accion, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch lsw_watch;

		lsw_watch = Logger.getNewStopWatch();

		getParameterBusiness().salvarProceso(ap_parametros, ab_accion, as_userId, as_remoteIp);

		Logger.log(lsw_watch, "ParameterBean", "salvarProceso", as_userId, as_localIp, as_remoteIp, null);
	}

	/** {@inheritdoc} */
	public void salvarProcesoAutomatico(
	    ProcesoAutomatico ac_parametros, boolean ab_accion, String as_usuario, String as_ipRemota, String as_ipLocal
	)
	    throws B2BException
	{
		StopWatch lsw_watch;

		lsw_watch = Logger.getNewStopWatch();

		getParameterBusiness().salvarProcesoAutomatico(ac_parametros, ab_accion, as_usuario, as_ipRemota);

		Logger.log(lsw_watch, "ParameterBean", "salvarProcesoAutomatico", as_usuario, as_ipLocal, as_ipRemota, null);
	}

	/**
	 * Método para guardar los cambios o la inserción de ProcesoCanal.
	 *
	 * @param atr_parametros de atr parametros
	 * @param ab_accion de ab accion
	 * @param as_userId de as user id
	 * @param as_localIp de as local ip
	 * @param as_remoteIp de as remote ip
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void salvarProcesoCanal(
	    ProcesoCanal atr_parametros, boolean ab_accion, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch lsw_watch;

		lsw_watch = Logger.getNewStopWatch();

		getParameterBusiness().salvarProcesoCanal(atr_parametros, ab_accion, as_userId, as_remoteIp);

		Logger.log(lsw_watch, "ParameterBean", "salvarProcesoCanal", as_userId, as_localIp, as_remoteIp, null);
	}

	/**
	 * Método para guardar los cambios o la inserción de ProcesoConsulta.
	 *
	 * @param apc_parametros de apc parametros
	 * @param ab_accion de ab accion
	 * @param as_userId de as user id
	 * @param as_localIp de as local ip
	 * @param as_remoteIp de as remote ip
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void salvarProcesoConsulta(
	    ProcesoConsulta apc_parametros, boolean ab_accion, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch lsw_watch;

		lsw_watch = Logger.getNewStopWatch();

		getParameterBusiness().salvarProcesoConsulta(apc_parametros, ab_accion, as_userId, as_remoteIp);

		Logger.log(lsw_watch, "ParameterBean", "salvarProcesoConsulta", as_userId, as_localIp, as_remoteIp, null);
	}

	/**
	 * Método para guardar un registro en la base de datos en la tabla SDB_PGN_PUNTO_RECAUDO.
	 *
	 * @param apr_parametros de apr parametros
	 * @param ab_accion de ab accion
	 * @param as_userId de as user id
	 * @param as_localIp de as local ip
	 * @param as_remoteIp de as remote ip
	 * @throws B2BException de b 2 B exception
	 */
	public void salvarPuntoRecaudo(
	    PuntoRecaudo apr_parametros, boolean ab_accion, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch lsw_watch;

		lsw_watch = Logger.getNewStopWatch();

		getParameterBusiness().insertUpdatePuntoRecaudo(apr_parametros, ab_accion, as_userId, as_remoteIp);

		Logger.log(lsw_watch, "ParameterBean", "salvarPuntoRecaudo", as_userId, as_localIp, as_remoteIp, null);
	}

	/**
	 * Método para guardar un registro en la base de datos en la tabla SDB_PGN_PUNTO_RECAUDO_TIPO_RECAUDO.
	 *
	 * @param aprtr_parametros de aprtr parametros
	 * @param ab_accion de ab accion
	 * @param as_userId de as user id
	 * @param as_localIp de as local ip
	 * @param as_remoteIp de as remote ip
	 * @throws B2BException de b 2 B exception
	 */
	public void salvarPuntoRecaudoTipoRecaudo(
	    PuntoRecaudoTipoRecaudo aprtr_parametros, boolean ab_accion, String as_userId, String as_localIp,
	    String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch lsw_watch;

		lsw_watch = Logger.getNewStopWatch();

		getParameterBusiness().insertUpdatePuntoRecaudoTipoRecaudo(aprtr_parametros, ab_accion, as_userId, as_remoteIp);

		Logger.log(
		    lsw_watch, "ParameterBean", "salvarPuntoRecaudoTipoRecaudo", as_userId, as_localIp, as_remoteIp, null
		);
	}

	/** {@inheritdoc} */
	public void salvarRegional(
	    Regional ac_parametros, boolean ab_accion, String as_usuario, String as_ipLocal, String as_ipRemota
	)
	    throws B2BException
	{
		StopWatch lsw_watch;

		lsw_watch = Logger.getNewStopWatch();

		getParameterBusiness().salvarRegional(ac_parametros, ab_accion, as_usuario, as_ipRemota);

		Logger.log(lsw_watch, "ParameterBean", "salvarRegional", as_usuario, as_ipLocal, as_ipRemota, null);
	}

	/** {@inheritdoc} */
	public void salvarReglaNegocio(
	    ReglaNegocio rn_parametros, boolean ab_insertar, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch lsw_watch;

		lsw_watch = Logger.getNewStopWatch();

		getParameterBusiness().salvarReglaNegocio(rn_parametros, ab_insertar, as_userId, as_remoteIp);

		Logger.log(lsw_watch, "ParameterBean", "salvarReglaNegocio", as_userId, as_localIp, as_remoteIp, null);
	}

	/** {@inheritdoc} */
	public void salvarReintentos(
	    Reintentos ar_parametros, boolean ab_insertar, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch lsw_watch;

		lsw_watch = Logger.getNewStopWatch();

		getParameterBusiness().salvarReintentos(ar_parametros, ab_insertar, as_userId, as_remoteIp);

		Logger.log(lsw_watch, "ParameterBean", "salvarReintentos", as_userId, as_localIp, as_remoteIp, null);
	}

	/** {@inheritdoc} */
	public void salvarResultadoConsulta(
	    ResultadoConsulta arc_parametros, boolean ab_accion, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException, IOException
	{
		StopWatch lsw_watch;

		lsw_watch = Logger.getNewStopWatch();

		getParameterBusiness().salvarResultadoConsulta(arc_parametros, ab_accion, as_userId, as_remoteIp);

		Logger.log(lsw_watch, "ParameterBean", "salvarResultadoConsulta", as_userId, as_localIp, as_remoteIp, null);
	}

	/** {@inheritdoc} */
	public Rol salvarRol(Rol ar_parametros, boolean ab_accion, String as_userId, String as_localIp, String as_remoteIp)
	    throws B2BException
	{
		StopWatch lsw_watch;
		Rol       lr_rol;

		lsw_watch     = Logger.getNewStopWatch();

		lr_rol = getParameterBusiness().salvarRol(ar_parametros, ab_accion, as_userId, as_remoteIp);

		Logger.log(lsw_watch, "ParameterBean", "salvarRol", as_userId, as_localIp, as_remoteIp, null);

		return lr_rol;
	}

	/**
	 * Método para guardar los cambios o la inserción de RolOpcion.
	 *
	 * @param atr_parametros de atr parametros
	 * @param ab_accion de ab accion
	 * @param as_userId de as user id
	 * @param as_localIp de as local ip
	 * @param as_remoteIp de as remote ip
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void salvarRolOpcion(
	    RolOpcion atr_parametros, boolean ab_accion, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch lsw_watch;

		lsw_watch = Logger.getNewStopWatch();

		getParameterBusiness().salvarRolOpcion(atr_parametros, ab_accion, as_userId, as_remoteIp);

		Logger.log(lsw_watch, "ParameterBean", "salvarRolOpcion", as_userId, as_localIp, as_remoteIp, null);
	}

	/**
	 * Método para guardar los cambios de los permisos de un rol.
	 *
	 * @param as_rol de as rol
	 * @param acc_parametros de acc parametros
	 * @param as_userId de as user id
	 * @param as_localIp de as local ip
	 * @param as_remoteIp de as remote ip
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void salvarRolOpcionComponente(
	    String as_rol, Collection<Componente> acc_parametros, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch lsw_watch;

		lsw_watch = Logger.getNewStopWatch();

		getParameterBusiness().salvarRolOpcionComponente(as_rol, acc_parametros, as_userId, as_remoteIp);

		Logger.log(lsw_watch, "ParameterBean", "salvarRolOpcionComponente", as_userId, as_localIp, as_remoteIp, null);
	}

	/**
	 * Método para guardar los cambios o la inserción de Subproceso.
	 *
	 * @param asp_parametros de asp parametros
	 * @param ab_accion de ab accion
	 * @param as_userId de as user id
	 * @param as_localIp de as local ip
	 * @param as_remoteIp de as remote ip
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void salvarSubproceso(
	    Subproceso asp_parametros, boolean ab_accion, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch lsw_watch;

		lsw_watch = Logger.getNewStopWatch();

		getParameterBusiness().salvarSubproceso(asp_parametros, ab_accion, as_userId, as_remoteIp);

		Logger.log(lsw_watch, "ParameterBean", "salvarSubproceso", as_userId, as_localIp, as_remoteIp, null);
	}

	/**
	 * Método para guardar los cambios o la inserción de SubprocesoVersion.
	 *
	 * @param asp_parametros Argumento de tipo <code>SubprocesoVersion</code> que contiene la información de la operación.
	 * @param ab_accion Argumento de tipo <code>boolean</code> que indica el tipo de operación.
	 * @param as_userId Argumento de tipo <code>String</code> que contiene el id del usuario que realiza la operación.
	 * @param as_localIp Argumento de tipo <code>String</code> que contiene la dirección ip local del usuario que realiza la operación.
	 * @param as_remoteIp Argumento de tipo <code>String</code> que contiene la dirección ip remota del usuario que realiza la operación.
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void salvarSubprocesoVersion(
	    SubprocesoVersion asp_parametros, boolean ab_accion, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch lsw_watch;

		lsw_watch = Logger.getNewStopWatch();

		getParameterBusiness().salvarSubprocesoVersion(asp_parametros, ab_accion, as_userId, as_remoteIp);

		Logger.log(lsw_watch, "ParameterBean", "salvarSubprocesoVersion", as_userId, as_localIp, as_remoteIp, null);
	}

	/**
	 * Método para guardar un registro en la base de datos en la tabla SDB_PGN_SUCURSAL_CANAL_ORIGEN_SERVICIO.
	 *
	 * @param ascos_parametros de ascos parametros
	 * @param ab_accion de ab accion
	 * @param as_userId de as user id
	 * @param as_localIp de as local ip
	 * @param as_remoteIp de as remote ip
	 * @throws B2BException de b 2 B exception
	 */
	public void salvarSucursalCanalOrigenServicio(
	    SucursalCanalOrigenServicio ascos_parametros, boolean ab_accion, String as_userId, String as_localIp,
	    String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch lsw_watch;

		lsw_watch = Logger.getNewStopWatch();

		getParameterBusiness()
			    .insertUpdateSucursalCanalOrigenServicio(ascos_parametros, ab_accion, as_userId, as_remoteIp);

		Logger.log(
		    lsw_watch, "ParameterBean", "salvarSucursalCanalOrigenServicio", as_userId, as_localIp, as_remoteIp, null
		);
	}

	/** {@inheritdoc} */
	public void salvarTarifaAlerta(
	    Collection<TarifaAlerta> actpa_parametros, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch lsw_watch;

		lsw_watch = Logger.getNewStopWatch();

		getParameterBusiness().salvarTarifaAlerta(actpa_parametros, as_userId, as_remoteIp);

		Logger.log(lsw_watch, "ParameterBean", "salvarTarifaAlerta", as_userId, as_localIp, as_remoteIp, null);
	}

	/**
	 * Método de proceso de conclusión para guardar los cambios o la insereción de Tipo Acto Prohibición.
	 *
	 * @param atd_parametros de atd parametros
	 * @param ab_accion de ab accion
	 * @param as_userId de as user id
	 * @param as_localIp de as local ip
	 * @param as_remoteIp de as remote ip
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void salvarTipoActoProhibicion(
	    TipoActoProhibicion atd_parametros, boolean ab_accion, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch lsw_watch;

		lsw_watch = Logger.getNewStopWatch();

		getParameterBusiness().salvarTipoActoProhibicion(atd_parametros, ab_accion, as_userId, as_remoteIp);

		Logger.log(lsw_watch, "ParameterBean", "salvarTipoActoProhibicion", as_userId, as_localIp, as_remoteIp, null);
	}

	/** {@inheritdoc} */
	public void salvarTipoAdquisicion(
	    TipoAdquisicion atr_parametros, boolean ab_accion, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		// 
		StopWatch lsw_watch;

		lsw_watch = Logger.getNewStopWatch();

		getParameterBusiness().salvarTipoAdquisicion(atr_parametros, ab_accion, as_userId, as_remoteIp);

		Logger.log(lsw_watch, "ParameterBean", "salvarTipoRecepcion", as_userId, as_localIp, as_remoteIp, null);
	}

	/**
	 * Método para guardar un registro en la base de datos en la tabla SDB_PGN_TIPO_AREA.
	 *
	 * @param ata_parametros de ata parametros
	 * @param ab_accion de ab accion
	 * @param as_userId de as user id
	 * @param as_localIp de as local ip
	 * @param as_remoteIp de as remote ip
	 * @throws B2BException de b 2 B exception
	 */
	public void salvarTipoArea(
	    TipoArea ata_parametros, boolean ab_accion, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch lsw_watch;

		lsw_watch = Logger.getNewStopWatch();

		getParameterBusiness().insertUpdateTipoArea(ata_parametros, ab_accion, as_userId, as_remoteIp);

		Logger.log(lsw_watch, "ParameterBean", "salvarTipoArea", as_userId, as_localIp, as_remoteIp, null);
	}

	/** {@inheritdoc} */
	public void salvarTipoCanalOrigen(
	    TipoCanalOrigen atco_parametros, boolean ab_accion, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch lsw_watch;

		lsw_watch = Logger.getNewStopWatch();

		getParameterBusiness().insertUpdateTipoCanalOrigen(atco_parametros, ab_accion, as_userId, as_remoteIp);

		Logger.log(lsw_watch, "ParameterBean", "salvarTipoCanalOrigen", as_userId, as_localIp, as_remoteIp, null);
	}

	/** {@inheritdoc} */
	public void salvarTipoCriterioBusquedaPGN(
	    TipoCriterioBusquedaPGN atcb_parametros, boolean ab_accion, String as_userId, String as_localIp,
	    String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch lsw_watch;

		lsw_watch = Logger.getNewStopWatch();

		getParameterBusiness().salvarTipoCriterioBusquedaPGN(atcb_parametros, ab_accion, as_userId, as_remoteIp);

		Logger.log(
		    lsw_watch, "ParameterBean", "salvarTipoCriterioBusquedaPGN", as_userId, as_localIp, as_remoteIp, null
		);
	}

	/**
	 * Método para guardar los cambios o la inserción de TipoDocumental.
	 *
	 * @param atd_parametros de atd parametros
	 * @param ab_accion de ab accion
	 * @param as_userId de as user id
	 * @param as_localIp de as local ip
	 * @param as_remoteIp de as remote ip
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void salvarTipoDocumental(
	    TipoDocumental atd_parametros, boolean ab_accion, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch lsw_watch;

		lsw_watch = Logger.getNewStopWatch();

		getParameterBusiness().salvarTipoDocumental(atd_parametros, ab_accion, as_userId, as_remoteIp);

		Logger.log(lsw_watch, "ParameterBean", "salvarTipoDocumental", as_userId, as_localIp, as_remoteIp, null);
	}

	/**
	 * Método para guardar los cambios o la inserción de TipoDocumentalActo.
	 *
	 * @param atda_parametros de atda parametros
	 * @param ab_accion de ab accion
	 * @param as_userId de as user id
	 * @param as_localIp de as local ip
	 * @param as_remoteIp de as remote ip
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void salvarTipoDocumentalActo(
	    TipoDocumentalActo atda_parametros, boolean ab_accion, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch lsw_watch;

		lsw_watch = Logger.getNewStopWatch();

		getParameterBusiness().salvarTipoDocumentalActo(atda_parametros, ab_accion, as_userId, as_remoteIp);

		Logger.log(lsw_watch, "ParameterBean", "salvarTipoDocumentalActo", as_userId, as_localIp, as_remoteIp, null);
	}

	/** {@inheritdoc} */
	public void salvarTipoDocumentoPublico(
	    TipoDocumentoPublico atr_parametros, boolean ab_accion, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch lsw_watch;

		lsw_watch = Logger.getNewStopWatch();

		getParameterBusiness().salvarTipoDocumentoPublico(atr_parametros, ab_accion, as_userId, as_remoteIp);

		Logger.log(lsw_watch, "ParameterBean", "salvarTipoRecepcion", as_userId, as_localIp, as_remoteIp, null);
	}

	/** {@inheritdoc} */
	public void salvarTipoEstadoLiquidacion(
	    TipoEstadoLiquidacion atel_parametros, boolean ab_accion, String as_userId, String as_localIp,
	    String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch lsw_watch;

		lsw_watch = Logger.getNewStopWatch();

		getParameterBusiness().insertUpdateTipoEstadoLiquidacion(atel_parametros, ab_accion, as_userId, as_remoteIp);

		Logger.log(lsw_watch, "ParameterBean", "salvarTipoEstadoLiquidacion", as_userId, as_localIp, as_remoteIp, null);
	}

	/** {@inheritdoc} */
	public void salvarTipoIntegracionGobernacion(
	    TipoIntegracionGobernacion at_parametros, boolean ab_accion, String as_usuario, String as_ipRemota,
	    String as_ipLocal
	)
	    throws B2BException
	{
		StopWatch lsw_watch;

		lsw_watch = Logger.getNewStopWatch();

		getParameterBusiness().salvarTipoIntegracionGobernacion(at_parametros, ab_accion, as_usuario, as_ipRemota);

		Logger.log(
		    lsw_watch, "ParameterBean", "salvarTipoIntegracionGobernacion", as_usuario, as_ipLocal, as_ipRemota, null
		);
	}

	/**
	 * Método para guardar los cambios o la inserción de TipoNotificacion.
	 *
	 * @param atn_parametros de atn parametros
	 * @param ab_accion de ab accion
	 * @param as_userId de as user id
	 * @param as_localIp de as local ip
	 * @param as_remoteIp de as remote ip
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void salvarTipoNotificacion(
	    TipoNotificacion atn_parametros, boolean ab_accion, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch lsw_watch;

		lsw_watch = Logger.getNewStopWatch();

		getParameterBusiness().salvarTipoNotificacion(atn_parametros, ab_accion, as_userId, as_remoteIp);

		Logger.log(lsw_watch, "ParameterBean", "salvarTipoNotificacion", as_userId, as_localIp, as_remoteIp, null);
	}

	/** {@inheritdoc} */
	public void salvarTipoOperacion(
	    TipoOperacion ato_parametros, boolean ab_accion, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch lsw_watch;

		lsw_watch = Logger.getNewStopWatch();

		getParameterBusiness().salvarTipoOperacion(ato_parametros, ab_accion, as_userId, as_remoteIp);

		Logger.log(lsw_watch, "ParameterBean", "salvarTipoOperacion", as_userId, as_localIp, as_remoteIp, null);
	}

	/** {@inheritdoc} */
	public void salvarTipoPersona(
	    TipoPersona atp_parametros, boolean ab_accion, String as_usuario, String as_ipLocal, String as_ipRemota
	)
	    throws B2BException
	{
		StopWatch lsw_watch;

		lsw_watch = Logger.getNewStopWatch();

		getParameterBusiness().salvarTipoPersona(atp_parametros, ab_accion, as_usuario, as_ipRemota);

		Logger.log(lsw_watch, "ParameterBean", "salvarTipoPersona", as_usuario, as_ipLocal, as_ipRemota, null);
	}

	/** {@inheritdoc} */
	public void salvarTipoPredio(
	    PredioTipo apt_parametros, boolean ab_accion, String as_usuario, String as_ipLocal, String as_ipRemota
	)
	    throws B2BException
	{
		StopWatch lsw_watch;

		lsw_watch = Logger.getNewStopWatch();

		getParameterBusiness().salvarTipoPredio(apt_parametros, ab_accion, as_usuario, as_ipRemota);

		Logger.log(lsw_watch, "ParameterBean", "salvarTipoPredio", as_usuario, as_ipLocal, as_ipRemota, null);
	}

	/** {@inheritdoc} */
	public void salvarTipoRRR(
	    TipoRrr atr_parametros, boolean ib_query, String as_userId, String as_ipLocal, String as_ipRemote
	)
	    throws B2BException
	{
		StopWatch lsw_watch;

		lsw_watch = Logger.getNewStopWatch();

		getParameterBusiness().salvarTipoRRR(atr_parametros, ib_query, as_userId, as_ipRemote);

		Logger.log(lsw_watch, "ParameterBean", "salvarTipoRRR", as_userId, as_ipLocal, as_ipRemote, null);
	}

	/**
	 * Método para guardar un registro en la base de datos en la tabla SDB_PGN_TIPO_RECAUDO.
	 *
	 * @param atr_parametros de atr parametros
	 * @param ab_accion de ab accion
	 * @param as_userId de as user id
	 * @param as_localIp de as local ip
	 * @param as_remoteIp de as remote ip
	 * @throws B2BException de b 2 B exception
	 */
	public void salvarTipoRecaudo(
	    TipoRecaudo atr_parametros, boolean ab_accion, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch lsw_watch;

		lsw_watch = Logger.getNewStopWatch();

		getParameterBusiness().insertUpdateTipoRecaudo(atr_parametros, ab_accion, as_userId, as_remoteIp);

		Logger.log(lsw_watch, "ParameterBean", "salvarTipoRecaudo", as_userId, as_localIp, as_remoteIp, null);
	}

	/** {@inheritdoc} */
	public void salvarTipoRecepcion(
	    TipoRecepcion atr_parametros, boolean ab_accion, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch lsw_watch;

		lsw_watch = Logger.getNewStopWatch();

		getParameterBusiness().salvarTipoRecepcion(atr_parametros, ab_accion, as_userId, as_remoteIp);

		Logger.log(lsw_watch, "ParameterBean", "salvarTipoRecepcion", as_userId, as_localIp, as_remoteIp, null);
	}

	/** {@inheritdoc} */
	public void salvarTipoRequiereMatricula(
	    TipoRequiereMatricula atrm_parametros, boolean ab_accion, String as_userId, String as_localIp,
	    String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch lsw_watch;

		lsw_watch = Logger.getNewStopWatch();

		getParameterBusiness().insertUpdateTipoRequiereMatricula(atrm_parametros, ab_accion, as_userId, as_remoteIp);

		Logger.log(lsw_watch, "ParameterBean", "salvarTipoRequiereMatricula", as_userId, as_localIp, as_remoteIp, null);
	}

	/** {@inheritdoc} */
	public void salvarTipoTarjetaApoderado(TipoTarjetaApoderado ar_parametros, boolean ab_accion)
	    throws B2BException
	{
		StopWatch lsw_watch;

		lsw_watch = Logger.getNewStopWatch();

		getParameterBusiness().salvarTipoTarjetaApoderado(ar_parametros, ab_accion);

		Logger.log(lsw_watch, "ParameterBean", "salvarRol", null, null, null, null);
	}

	/** {@inheritdoc} */
	public void salvarTipoUsoSuelo(
	    TipoUsoSuelo atus_parametros, boolean ab_accion, String as_usuario, String as_ipLocal, String as_ipRemota
	)
	    throws B2BException
	{
		StopWatch lsw_watch;

		lsw_watch = Logger.getNewStopWatch();

		getParameterBusiness().salvarTipoUsoSuelo(atus_parametros, ab_accion, as_usuario, as_ipRemota);

		Logger.log(lsw_watch, "ParameterBean", "salvarTipoUsoSuelo", as_usuario, as_ipLocal, as_ipRemota, null);
	}

	/**
	 * Método para guardar los cambios o la inserción de UnidadTiempoVencimiento.
	 *
	 * @param autv_parametros de autv parametros
	 * @param ab_accion de ab accion
	 * @param as_userId de as user id
	 * @param as_localIp de as local ip
	 * @param as_remoteIp de as remote ip
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void salvarUnidadTiempoVencimiento(
	    UnidadTiempoVencimiento autv_parametros, boolean ab_accion, String as_userId, String as_localIp,
	    String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch lsw_watch;

		lsw_watch = Logger.getNewStopWatch();

		getParameterBusiness().salvarUnidadTiempoVencimiento(autv_parametros, ab_accion, as_userId, as_remoteIp);

		Logger.log(
		    lsw_watch, "ParameterBean", "salvarUnidadTiempoVencimiento", as_userId, as_localIp, as_remoteIp, null
		);
	}

	/** {@inheritdoc} */
	public void salvarVereda(
	    Vereda ac_parametros, boolean ab_accion, String as_usuario, String as_ipLocal, String as_ipRemota
	)
	    throws B2BException
	{
		StopWatch lsw_watch;

		lsw_watch = Logger.getNewStopWatch();

		getParameterBusiness().salvarVereda(ac_parametros, ab_accion, as_usuario, as_ipRemota);

		Logger.log(lsw_watch, "ParameterBean", "salvarVereda", as_usuario, as_ipLocal, as_ipRemota, null);
	}

	/** {@inheritdoc} */
	public void salvarZonaRegistral(
	    ZonaRegistral ac_parametros, boolean ab_accion, String as_usuario, String as_ipLocal, String as_ipRemota
	)
	    throws B2BException
	{
		StopWatch lsw_watch;

		lsw_watch = Logger.getNewStopWatch();

		getParameterBusiness().salvarZonaRegistral(ac_parametros, ab_accion, as_usuario, as_ipRemota);

		Logger.log(lsw_watch, "ParameterBean", "salvarZonaRegistral", as_usuario, as_ipLocal, as_ipRemota, null);
	}

	/**
	 * Método encargado de validar el excel de cargue masivo para persona entidad.
	 *
	 * @param aba_archivo de aba archivo
	 * @param as_nombreFile de as nombre file
	 * @param as_userId de as user id
	 * @param as_localIp de as local ip
	 * @param as_remoteIp de as remote ip
	 * @return el valor de collection
	 * @throws B2BException de b 2 B exception
	 * @throws IOException Señala que se ha producido una excepción de E / S.
	 */
	public Collection<Persona> validarExcelPersonaEntidad(
	    byte[] aba_archivo, String as_nombreFile, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException, IOException
	{
		{
			StopWatch           lsw_watch;
			Collection<Persona> lcp_personasAsignadas;

			lsw_watch     = Logger.getNewStopWatch();

			lcp_personasAsignadas = getParameterBusiness()
					                        .validarExcelPersonaEntidad(
					    aba_archivo, as_nombreFile, as_userId, as_localIp
					);

			Logger.log(
			    lsw_watch, "ParameterBean", "validarExcelPersonaEntidad", as_userId, as_localIp, as_remoteIp, null
			);

			return lcp_personasAsignadas;
		}
	}

	/** {@inheritdoc} */
	public boolean validarTipoDocumento(String as_tipo_documento)
	    throws B2BException
	{
		StopWatch lsw_watch;
		boolean   lb_validoTipoDocumento;

		lsw_watch     = Logger.getNewStopWatch();

		lb_validoTipoDocumento = getParameterBusiness().validarTipoDocumento(as_tipo_documento);

		Logger.log(lsw_watch, "ParameterBean", "filtroBitacoraProceso", null, null, null, null);

		return lb_validoTipoDocumento;
	}

	/**
	 * Retorna el valor de parameter business.
	 *
	 * @return el valor de parameter business
	 */
	private ParameterBusiness getParameterBusiness()
	{
		if(ip_business == null)
			ip_business = new ParameterBusiness();

		return ip_business;
	}
}
