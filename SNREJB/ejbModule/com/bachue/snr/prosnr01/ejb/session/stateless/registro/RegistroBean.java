package com.bachue.snr.prosnr01.ejb.session.stateless.registro;

import com.b2bsg.common.exception.B2BException;

import com.bachue.snr.prosnr01.business.registro.CriteriosConsultaBusiness;
import com.bachue.snr.prosnr01.business.registro.RegistroBusiness;

import com.bachue.snr.prosnr01.ejb.session.stateless.Logger;

import com.bachue.snr.prosnr01.model.antiguoSistema.BuscarAntiguoSistema;
import com.bachue.snr.prosnr01.model.copias.DigitalizacionCopias;
import com.bachue.snr.prosnr01.model.integration.OWCC.DocumentoOWCC;
import com.bachue.snr.prosnr01.model.registro.DataReproduccionConstancia;
import com.bachue.snr.prosnr01.model.registro.DatosPredioRegistro;
import com.bachue.snr.prosnr01.model.registro.GravamenPendiente;
import com.bachue.snr.prosnr01.model.registro.NuevaEntrada;
import com.bachue.snr.prosnr01.model.registro.ProhibicionVPM;
import com.bachue.snr.prosnr01.model.registro.Registro;
import com.bachue.snr.prosnr01.model.registro.SolicitudTestamento;
import com.bachue.snr.prosnr01.model.registro.TramiteSolicitud;
import com.bachue.snr.prosnr01.model.sdb.acc.AccAreaPredio;
import com.bachue.snr.prosnr01.model.sdb.acc.AccCompletitudDocumental;
import com.bachue.snr.prosnr01.model.sdb.acc.AccEntidadExterna;
import com.bachue.snr.prosnr01.model.sdb.acc.Acto;
import com.bachue.snr.prosnr01.model.sdb.acc.DatosAntSistema;
import com.bachue.snr.prosnr01.model.sdb.acc.DetalleAntSistema;
import com.bachue.snr.prosnr01.model.sdb.acc.DocumentosSalida;
import com.bachue.snr.prosnr01.model.sdb.acc.MatriculaSegregacion;
import com.bachue.snr.prosnr01.model.sdb.acc.Persona;
import com.bachue.snr.prosnr01.model.sdb.acc.PersonaCorreoElectronico;
import com.bachue.snr.prosnr01.model.sdb.acc.PersonaDireccion;
import com.bachue.snr.prosnr01.model.sdb.acc.PersonaTelefono;
import com.bachue.snr.prosnr01.model.sdb.acc.RegistroAnotacionProhibicion;
import com.bachue.snr.prosnr01.model.sdb.acc.Solicitud;
import com.bachue.snr.prosnr01.model.sdb.acc.SolicitudAsociada;
import com.bachue.snr.prosnr01.model.sdb.acc.SolicitudCorreccion;
import com.bachue.snr.prosnr01.model.sdb.acc.SolicitudInterviniente;
import com.bachue.snr.prosnr01.model.sdb.acc.SolicitudMatricula;
import com.bachue.snr.prosnr01.model.sdb.acc.SoporteTraslado;
import com.bachue.snr.prosnr01.model.sdb.acc.Testamento;
import com.bachue.snr.prosnr01.model.sdb.acc.TipoAdquisicion;
import com.bachue.snr.prosnr01.model.sdb.acc.TrasladoMatricula;
import com.bachue.snr.prosnr01.model.sdb.acc.Turno;
import com.bachue.snr.prosnr01.model.sdb.bng.AnotacionPredio;
import com.bachue.snr.prosnr01.model.sdb.bng.AnotacionPredioDireccion;
import com.bachue.snr.prosnr01.model.sdb.bng.AreaPredio;
import com.bachue.snr.prosnr01.model.sdb.bng.DireccionPredio;
import com.bachue.snr.prosnr01.model.sdb.bng.Documento;
import com.bachue.snr.prosnr01.model.sdb.bng.DocumentoConstancia;
import com.bachue.snr.prosnr01.model.sdb.bng.Imagenes;
import com.bachue.snr.prosnr01.model.sdb.bng.PredioRegistro;
import com.bachue.snr.prosnr01.model.sdb.bng.ValidacionDocumento;
import com.bachue.snr.prosnr01.model.sdb.pgn.CamposConsulta;
import com.bachue.snr.prosnr01.model.sdb.pgn.CausalCorreccion;
import com.bachue.snr.prosnr01.model.sdb.pgn.CirculoRegistral;
import com.bachue.snr.prosnr01.model.sdb.pgn.Constantes;
import com.bachue.snr.prosnr01.model.sdb.pgn.CriteriosDeBusqueda;
import com.bachue.snr.prosnr01.model.sdb.pgn.OficinaOrigen;
import com.bachue.snr.prosnr01.model.sdb.pgn.TipoActo;
import com.bachue.snr.prosnr01.model.sdb.pgn.TipoDocumental;
import com.bachue.snr.prosnr01.model.sdb.pgn.TipoDocumentoPublico;
import com.bachue.snr.prosnr01.model.sdb.pgn.ZonaRegistral;
import com.bachue.snr.prosnr01.model.sdb.png.EstadoPredio;
import com.bachue.snr.prosnr01.model.ui.AccAreaUI;

import org.perf4j.StopWatch;

import java.io.IOException;

import java.sql.SQLException;

import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


/**
 * Clase que contiene todos las propiedades RegistroBean.
 *
 * @author Julian Vaca
 */
@javax.ejb.Stateless(name = "Registro", mappedName = "registroMappedName")
@javax.ejb.TransactionManagement(javax.ejb.TransactionManagementType.BEAN)
public class RegistroBean implements RegistroRemote
{
	/** Propiedad iccb business. */
	private CriteriosConsultaBusiness iccb_business;

	/** Propiedad irb business. */
	private RegistroBusiness irb_business;

	/** {@inheritdoc} */
	public Solicitud actualizaTipoReproduccion(
	    Solicitud as_parametros, String as_userId, String as_remoteIp, String as_localIp
	)
	    throws B2BException
	{
		StopWatch lsw_watch;
		Solicitud lr_registro;

		lsw_watch     = Logger.getNewStopWatch();

		lr_registro = getRegistroBusiness().actualizaTipoReproduccion(as_parametros, as_userId);

		Logger.log(
		    lsw_watch, "RegistroBean", "actualizarDescripcionSolicitud", as_userId, as_localIp, as_remoteIp, null
		);

		return lr_registro;
	}

	/** {@inheritdoc} */
	public void actualizarActoParaAntiguoSistema(Acto aa_acto, String as_userId, String as_localIp, String as_remoteIp)
	    throws B2BException
	{
		StopWatch lsw_watch;

		lsw_watch = Logger.getNewStopWatch();

		getRegistroBusiness().actualizarActoParaAntiguoSistema(aa_acto, as_userId, as_remoteIp);

		Logger.log(
		    lsw_watch, "RegistroBean", "actualizarActoParaAntiguoSistema", as_userId, as_localIp, as_remoteIp, null
		);
	}

	/** {@inheritdoc} */
	public Registro actualizarDescripcionSolicitud(Registro ar_parametros, boolean ab_0463, String as_userId)
	    throws B2BException
	{
		StopWatch lsw_watch;
		Registro  lr_registro;

		lsw_watch     = Logger.getNewStopWatch();

		lr_registro = getRegistroBusiness().actualizarDescripcionSolicitud(ar_parametros, ab_0463, as_userId);

		Logger.log(lsw_watch, "RegistroBean", "actualizarDescripcionSolicitud", as_userId, null, null, null);

		return lr_registro;
	}

	/** {@inheritdoc} */
	public Solicitud actualizarSolicitudNuevaEntrada(Solicitud as_solicitud, Turno at_turno)
	    throws B2BException
	{
		StopWatch lsw_watch;
		Solicitud ls_return;

		lsw_watch     = Logger.getNewStopWatch();

		ls_return = getRegistroBusiness().actualizarSolicitudNuevaEntrada(as_solicitud, at_turno);

		Logger.log(lsw_watch, "RegistroBean", "actualizarSolicitudNuevaEntrada", null, null, null, null);

		return ls_return;
	}

	/** {@inheritdoc} */
	public void actualizarSubProcesoSolicitud(Solicitud as_solicitud, String as_userId)
	    throws B2BException
	{
		StopWatch lsw_watch;

		lsw_watch = Logger.getNewStopWatch();

		getRegistroBusiness().actualizarSubProcesoSolicitud(as_solicitud, as_userId);

		Logger.log(lsw_watch, "RegistroBean", "findActoBy", null, null, null, null);
	}

	/** {@inheritdoc} */
	public void agregarComentario(Solicitud as_solicitud)
	    throws B2BException
	{
		StopWatch lsw_watch;

		lsw_watch = Logger.getNewStopWatch();

		getRegistroBusiness().agregarComentario(as_solicitud);

		Logger.log(lsw_watch, "RegistroBean", "agregarComentario", null, null, null, null);
	}

	/** {@inheritdoc} */
	public double areaTotalTerreno(Registro ar_registro, String as_userId, String as_localIp, String as_remoteIp)
	    throws B2BException
	{
		StopWatch lsw_watch;
		double    ld_d;

		lsw_watch     = Logger.getNewStopWatch();

		ld_d = getRegistroBusiness().areaTotalTerreno(ar_registro);

		Logger.log(lsw_watch, "RegistroBean", "areaTotalTerreno", as_userId, as_localIp, as_remoteIp, null);

		return ld_d;
	}

	/** {@inheritdoc} */
	public void borrarDatosDetallesAntSistema(
	    Collection<Collection<DatosAntSistema>> accdas_datosBorrar, String as_idSolicitud, String as_userId,
	    String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch lsw_watch;

		lsw_watch = Logger.getNewStopWatch();

		getRegistroBusiness().borrarDatosDetallesAntSistema(accdas_datosBorrar, as_idSolicitud);

		Logger.log(
		    lsw_watch, "RegistroBean", "borrarDatosDetallesAntSistema", as_userId, as_localIp, as_remoteIp, null
		);
	}

	/** {@inheritdoc} */
	public Persona buscarPersonaUsuarioLogueado(String as_userId, String as_localIp, String as_remoteIp)
	    throws B2BException
	{
		StopWatch lsw_watch;
		Persona   lp_persona;

		lsw_watch     = Logger.getNewStopWatch();

		lp_persona = getRegistroBusiness().buscarPersonaUsuarioLogueado(as_userId, as_remoteIp);

		Logger.log(lsw_watch, "RegistroBean", "buscarPersonaUsuarioLogueado", as_userId, as_remoteIp, null, null);

		return lp_persona;
	}

	/** {@inheritdoc} */
	public Registro buscarPersonasPorSolicitudInterviniente(
	    Registro ap_parametros, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch lsw_watch;
		Registro  lcp_registro;

		lsw_watch     = Logger.getNewStopWatch();

		lcp_registro = getRegistroBusiness().buscarPersonasPorSolicitudInterviniente(ap_parametros);

		Logger.log(
		    lsw_watch, "RegistroBean", "buscarPersonasPorSolicitudInterviniente", as_userId, as_localIp, as_remoteIp,
		    null
		);

		return lcp_registro;
	}

	/** {@inheritdoc} */
	public Turno buscarTurnoPorId(String as_idTurno, String as_userId, String as_ipLocal, String as_ipRemota)
	    throws B2BException
	{
		StopWatch lsw_watch;
		Turno     lt_turno;

		lsw_watch     = Logger.getNewStopWatch();

		lt_turno = getCriteriosConsultaBusiness().buscarTurnoPorId(as_idTurno);

		Logger.log(lsw_watch, "RegistroBean", "buscarTurnoPorId", as_userId, as_ipLocal, as_ipRemota, null);

		return lt_turno;
	}

	/** {@inheritdoc} */
	public Collection<CamposConsulta> camposPorCriterio(
	    Solicitud as_s, String as_userId, String as_ipLocal, String as_ipRemota
	)
	    throws B2BException
	{
		StopWatch                  lsw_watch;
		Collection<CamposConsulta> lcts_ts;

		lsw_watch     = Logger.getNewStopWatch();

		lcts_ts = getCriteriosConsultaBusiness().camposPorCriterio(as_s);

		Logger.log(lsw_watch, "RegistroBean", "camposPorCriterio", as_userId, null, null, lcts_ts);

		return lcts_ts;
	}

	/** {@inheritdoc} */
	public Collection<String> cargarDatosPredioActo(Solicitud as_solicitud)
	    throws B2BException
	{
		StopWatch          lsw_watch;
		Collection<String> lcs_return;

		lsw_watch     = Logger.getNewStopWatch();

		lcs_return = getRegistroBusiness().cargarDatosPredioActo(as_solicitud);

		Logger.log(lsw_watch, "RegistroBean", "cargarDatosPredioActo", null, null, null, null);

		return lcs_return;
	}

	/** {@inheritdoc} */
	public String cargarDireccionCompleta(
	    CamposConsulta acc_panel, String as_userId, String as_ipLocal, String as_ipRemota
	)
	    throws B2BException
	{
		StopWatch lsw_watch;
		String    ls_direccionCompleta;

		lsw_watch                = Logger.getNewStopWatch();
		ls_direccionCompleta     = getCriteriosConsultaBusiness().cargarDireccionCompleta(acc_panel);

		Logger.log(lsw_watch, "RegistroBean", "cargarDireccionCompleta", as_userId, as_ipLocal, as_ipRemota, null);

		return ls_direccionCompleta;
	}

	/** {@inheritDoc} */
	public Collection<DocumentosSalida> cargarDocumentosSolicitud(
	    String as_proceso, String as_idSolicitud, String as_userId, String as_ipLocal, String as_ipRemota
	)
	    throws B2BException
	{
		StopWatch                    lsw_watch;
		Collection<DocumentosSalida> lcds_return;

		lsw_watch       = Logger.getNewStopWatch();
		lcds_return     = getRegistroBusiness()
				                  .cargarDocumentosSolicitud(as_proceso, as_idSolicitud, as_userId, as_ipRemota);

		Logger.log(
		    lsw_watch, "RegistroBean", "cargarDocumentosSolicitud", as_userId, as_ipLocal, as_ipRemota, lcds_return
		);

		return lcds_return;
	}

	/** {@inheritDoc} */
	public Collection<CamposConsulta> cargarExcelCamposCriterios(
	    Collection<CamposConsulta> acc_camposConsulta, String as_userId, String as_ipLocal, String as_ipRemota
	)
	    throws B2BException
	{
		StopWatch                  lsw_watch;
		Collection<CamposConsulta> lccc_datos;

		lsw_watch      = Logger.getNewStopWatch();
		lccc_datos     = getCriteriosConsultaBusiness()
				                 .cargarExcelCamposCriterios(acc_camposConsulta, as_userId, as_ipRemota);

		Logger.log(
		    lsw_watch, "RegistroBean", "cargarExcelCamposCriterios", as_userId, as_ipLocal, as_ipRemota, lccc_datos
		);

		return lccc_datos;
	}

	/** {@inheritdoc} */
	public Collection<CamposConsulta> cargarExcelCamposCriteriosCopias(
	    Collection<CamposConsulta> accc_camposConsulta, boolean ab_copias, String as_userId, String as_ipLocal,
	    String as_ipRemota
	)
	    throws B2BException
	{
		StopWatch                  lsw_watch;
		Collection<CamposConsulta> lccc_datos;

		lsw_watch     = Logger.getNewStopWatch();

		lccc_datos = getCriteriosConsultaBusiness()
				             .cargarExcelCamposCriteriosCopias(accc_camposConsulta, ab_copias, as_userId, as_ipRemota);

		Logger.log(
		    lsw_watch, "RegistroBean", "cargarExcelCamposCriteriosCopias", as_userId, as_ipLocal, as_ipRemota,
		    lccc_datos
		);

		return lccc_datos;
	}

	/** {@inheritdoc} */
	public BuscarAntiguoSistema cargarInformacionAntiguoSistema(
	    Long al_idTurnoHistoria, String as_userId, String as_ipLocal, String as_ipRemota
	)
	    throws B2BException
	{
		StopWatch            lsw_watch;
		BuscarAntiguoSistema lbas_datos;

		lsw_watch     = Logger.getNewStopWatch();

		lbas_datos = getCriteriosConsultaBusiness().cargarInformacionAntiguoSistema(al_idTurnoHistoria);

		Logger.log(
		    lsw_watch, "RegistroBean", "cargarExcelCamposCriteriosCopias", as_userId, as_ipLocal, as_ipRemota, null
		);

		return lbas_datos;
	}

	/** {@inheritdoc} */
	public DigitalizacionCopias cargarInformacionDigitalizacionCopias(
	    DigitalizacionCopias adc_parametros, String as_userId, String as_ipLocal, String as_ipRemota
	)
	    throws B2BException
	{
		StopWatch            lsw_watch;
		DigitalizacionCopias ldc_datos;

		lsw_watch     = Logger.getNewStopWatch();

		ldc_datos = getCriteriosConsultaBusiness().cargarInformacionDigitalizacionCopias(adc_parametros);

		Logger.log(
		    lsw_watch, "RegistroBean", "cargarInformacionDigitalizacionCopias", as_userId, as_ipLocal, as_ipRemota, null
		);

		return ldc_datos;
	}

	/** {@inheritdoc} */
	public String cargarNir(CamposConsulta acc_panel, String as_userId, String as_ipLocal, String as_ipRemota)
	    throws B2BException
	{
		StopWatch lsw_watch;
		String    ls_direccionCompleta;

		lsw_watch                = Logger.getNewStopWatch();
		ls_direccionCompleta     = getCriteriosConsultaBusiness().cargarNir(acc_panel);

		Logger.log(lsw_watch, "RegistroBean", "cargarNir", as_userId, as_ipLocal, as_ipRemota, null);

		return ls_direccionCompleta;
	}

	/** {@inheritDoc} */
	public Registro cargarPersonaDireccion(String as_idSolicitud)
	    throws B2BException
	{
		StopWatch lsw_watch;
		Registro  lr_return;

		lsw_watch     = Logger.getNewStopWatch();
		lr_return     = getRegistroBusiness().cargarPersonaDireccion(as_idSolicitud);

		Logger.log(lsw_watch, "RegistroBean", "cargarPersonaDireccion", null, null, null, null);

		return lr_return;
	}

	/** {@inheritdoc} */
	public void compareAreas(String as_idCirculo, Long al_idMatricula, double ad_sumAreas)
	    throws B2BException
	{
		StopWatch lsw_watch;

		lsw_watch = Logger.getNewStopWatch();
		getRegistroBusiness().compareAreas(as_idCirculo, al_idMatricula, ad_sumAreas);
		Logger.log(lsw_watch, "ConsultaSolicitudBusiness", "findByIdSolicitud", as_idCirculo, null, null, null);
	}

	/** {@inheritDoc} */
	public SolicitudMatricula consulaMatriculasAsociadas(Solicitud as_solicitud)
	    throws B2BException
	{
		StopWatch          lsw_watch;
		SolicitudMatricula ls_return;

		lsw_watch     = Logger.getNewStopWatch();
		ls_return     = getRegistroBusiness().consulaMatriculasAsociadas(as_solicitud);

		Logger.log(lsw_watch, "RegistroBean", "consulaMatriculasAsociadas", null, null, null, null);

		return ls_return;
	}

	/** {@inheritdoc} */
	public AccAreaUI consultaAreaPredio(AccAreaPredio aaap_areaPredioArg, String as_userId, boolean ab_accion)
	    throws B2BException
	{
		StopWatch lsw_watch;
		AccAreaUI laaui_registro;

		lsw_watch          = Logger.getNewStopWatch();
		laaui_registro     = getRegistroBusiness().consultaAreaPredio(aaap_areaPredioArg, as_userId, ab_accion);

		Logger.log(lsw_watch, "RegistroBean", "consultaAreaPredio", as_userId, null, null, null);

		return laaui_registro;
	}

	/** {@inheritdoc} */
	public boolean consultaEnTabla(String ai_codigoActo)
	    throws B2BException
	{
		StopWatch lsw_watch;
		boolean   ls_return;

		lsw_watch     = Logger.getNewStopWatch();
		ls_return     = getRegistroBusiness().consultaEnTabla(ai_codigoActo);

		Logger.log(lsw_watch, "RegistroBean", "consultaEnTabla", null, null, null, null);

		return ls_return;
	}

	/** {@inheritdoc} */
	public Collection<TramiteSolicitud> consultaRetomarSolicitud(
	    String as_tipoDocumento, String as_numeroDocumento, String as_userId
	)
	    throws B2BException, SQLException
	{
		StopWatch                    lsw_watch;
		Collection<TramiteSolicitud> lcts_ts;

		lsw_watch     = Logger.getNewStopWatch();

		lcts_ts = getRegistroBusiness().consultaRetomarSolicitud(as_tipoDocumento, as_numeroDocumento, as_userId);

		Logger.log(lsw_watch, "RegistroBean", "consultaRetomarSolicitud", as_userId, null, null, lcts_ts);

		return lcts_ts;
	}

	/** {@inheritdoc} */
	public Collection<CriteriosDeBusqueda> consultarDetallesAgregados(
	    CriteriosDeBusqueda acdb_cdb, String as_userId, String as_ipLocal, String as_ipRemota
	)
	    throws B2BException
	{
		StopWatch                       lsw_watch;
		Collection<CriteriosDeBusqueda> lccdb_cdb;

		lsw_watch     = Logger.getNewStopWatch();

		lccdb_cdb = getCriteriosConsultaBusiness().consultarDetallesAgregados(acdb_cdb);

		Logger.log(
		    lsw_watch, "RegistroBean", "consultarDetallesAgregados", as_userId, as_ipLocal, as_ipRemota, lccdb_cdb
		);

		return lccdb_cdb;
	}

	/** {@inheritdoc} */
	public Registro consultarInfoPersonaEntidadExenta(
	    Persona ap_parametros, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch lsw_watch;
		Registro  lcp_registro;

		lsw_watch        = Logger.getNewStopWatch();
		lcp_registro     = getRegistroBusiness().consultarInfoPersonaEntidadExenta(ap_parametros);

		Logger.log(
		    lsw_watch, "RegistroBean", "consultarInfoPersonaEntidadExenta", as_userId, as_localIp, as_remoteIp, null
		);

		return lcp_registro;
	}

	/** {@inheritdoc} */
	public Collection<String> consultarMatriculasParaDetalle(
	    DetalleAntSistema adas_detalle, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		Collection<String> lcs_matriculas;
		StopWatch          lsw_watch;

		lsw_watch     = Logger.getNewStopWatch();

		lcs_matriculas = getRegistroBusiness().consultarMatriculasParaDetalle(adas_detalle);

		Logger.log(
		    lsw_watch, "RegistroBean", "consultarMatriculasParaDetalle", as_userId, as_localIp, as_remoteIp,
		    lcs_matriculas
		);

		return lcs_matriculas;
	}

	/** {@inheritdoc} */
	public Collection<DocumentoOWCC> consultarOWCCAntiguoSistemaCopias(
	    BuscarAntiguoSistema abas_parametros, String as_userId, String as_ipLocal, String as_ipRemota
	)
	    throws B2BException
	{
		StopWatch                 lsw_watch;
		Collection<DocumentoOWCC> lcdo_datos;

		lsw_watch     = Logger.getNewStopWatch();

		lcdo_datos = getCriteriosConsultaBusiness().consultarOWCCAntiguoSistemaCopias(abas_parametros);

		Logger.log(
		    lsw_watch, "RegistroBean", "consultarOWCCAntiguoSistemaCopias", as_userId, as_ipLocal, as_ipRemota,
		    lcdo_datos
		);

		return lcdo_datos;
	}

	/** {@inheritdoc} */
	public Collection<Testamento> consultarTestamentos(
	    Testamento as_s, String as_userId, String as_ipLocal, String as_ipRemota
	)
	    throws B2BException
	{
		StopWatch              lsw_watch;
		Collection<Testamento> lb_noValido;

		lsw_watch     = Logger.getNewStopWatch();

		lb_noValido = getRegistroBusiness().consultarTestamentos(as_s);

		Logger.log(
		    lsw_watch, "RegistroBean", "verificarSiActoNoValidoParaAntSistema", as_userId, as_ipLocal, as_ipRemota, null
		);

		return lb_noValido;
	}

	/** {@inheritdoc} */
	public byte[] crearPdfRegistro(Registro ar_parametros, String as_userId)
	    throws B2BException
	{
		StopWatch lsw_watch;
		byte[]    lba_archivo;

		lsw_watch     = Logger.getNewStopWatch();

		lba_archivo = getRegistroBusiness().crearPdfRegistro(ar_parametros, as_userId);

		Logger.log(lsw_watch, "RegistroBean", "crearPdfRegistro", as_userId, null, null, null);

		return lba_archivo;
	}

	/** {@inheritdoc} */
	public String crearYObtenerUrlDigitalizacion(
	    BuscarAntiguoSistema abas_parametros, String as_userId, String as_ipLocal, String as_ipRemota
	)
	    throws B2BException
	{
		StopWatch lsw_watch;
		String    ls_datos;

		lsw_watch     = Logger.getNewStopWatch();

		ls_datos = getCriteriosConsultaBusiness().crearYObtenerUrlDigitalizacion(
			    abas_parametros, as_userId, as_ipRemota
			);

		Logger.log(
		    lsw_watch, "RegistroBean", "consultarOWCCAntiguoSistemaCopias", as_userId, as_ipLocal, as_ipRemota, null
		);

		return ls_datos;
	}

	/** {@inheritdoc} */
	public void deleteActo(com.bachue.snr.prosnr01.model.registro.Acto asi_parametros)
	    throws B2BException
	{
		StopWatch lsw_watch;

		lsw_watch = Logger.getNewStopWatch();

		getRegistroBusiness().deleteActo(asi_parametros);

		Logger.log(lsw_watch, "RegistroBean", "deleteActo", null, null, null, null);
	}

	/** {@inheritdoc} */
	public void deleteActosHijos(com.bachue.snr.prosnr01.model.registro.Acto ap_parametros)
	    throws B2BException
	{
		StopWatch lsw_watch;

		lsw_watch = Logger.getNewStopWatch();

		getRegistroBusiness().deleteActosHijos(ap_parametros);

		Logger.log(lsw_watch, "RegistroBean", "deleteActosHijos", null, null, null, null);
	}

	/** {@inheritdoc} */
	public void deleteBySolicitudCirculoMatricula(
	    SolicitudMatricula asm_sm, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch lsw_watch;

		lsw_watch = Logger.getNewStopWatch();

		getRegistroBusiness().deleteBySolicitudCirculoMatricula(asm_sm, as_userId, as_remoteIp);

		Logger.log(
		    lsw_watch, "RegistroBean", "deleteBySolicitudCirculoMatricula", as_userId, as_localIp, as_remoteIp, null
		);
	}

	/** {@inheritdoc} */
	public void deleteCausalesCorreccion(SolicitudCorreccion lsc_solicitudCorreccion)
	    throws B2BException, SQLException
	{
		StopWatch lsw_watch;

		lsw_watch = Logger.getNewStopWatch();

		getRegistroBusiness().deleteCausalesCorreccion(lsc_solicitudCorreccion);

		Logger.log(lsw_watch, "RegistroBean", "deleteCausalesCorreccion", null, null, null, null);
	}

	/** {@inheritdoc} */
	public void deleteDetalle(CriteriosDeBusqueda acdb_cdb, String as_userId, String as_ipLocal, String as_ipRemota)
	    throws B2BException
	{
		StopWatch lsw_watch;

		lsw_watch = Logger.getNewStopWatch();

		getCriteriosConsultaBusiness().deleteDetalle(acdb_cdb);

		Logger.log(lsw_watch, "RegistroBean", "deleteDetalle", as_userId, as_ipLocal, as_ipRemota, null);
	}

	/** {@inheritdoc} */
	public void deleteMatriculaSegregacion(MatriculaSegregacion ams_matriculaSegregacion)
	    throws B2BException
	{
		StopWatch lsw_watch;

		lsw_watch = Logger.getNewStopWatch();

		getRegistroBusiness().deleteMatriculaSegregacion(ams_matriculaSegregacion);

		Logger.log(lsw_watch, "RegistroBean", "deleteMatriculaSegregacion", null, null, null, null);
	}

	/**
	 * Método encargado de consultar una constante dependiendo de idConstante.
	 *
	 * @param as_idConstante con el cual se realizará la búsqueda de la constante.
	 * @param as_userId            usuario que esta realizando la acción.
	 * @param as_localIp            ip local del usuario que esta realizando la acción
	 * @param as_remoteIp            ip remota del usuario que esta realizando la acción
	 * @return <code>Constantes</code> lleno con la infromación de la BD.
	 * @throws B2BException de b 2 B exception
	 */
	public Constantes descargarPlantilla(
	    String as_idConstante, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch  lsw_watch;
		Constantes lc_return;

		lsw_watch     = Logger.getNewStopWatch();
		lc_return     = getRegistroBusiness().descargarPlantilla(as_idConstante);

		Logger.log(lsw_watch, "RegistroBean", "descargarPlantilla", as_userId, as_localIp, as_remoteIp, null);

		return lc_return;
	}

	/**
	 * Método encargado de consultar una constante dependiendo del tipo criterio
	 * búsqueda de un <code>CamposConsulta</code>.
	 *
	 * @param acc_camposConsulta            <code>CamposConsulta</code> con el cual se realizará la búsqueda
	 *            de la constante.
	 * @param as_userId            usuario que esta realizando la acción.
	 * @param as_localIp            ip local del usuario que esta realizando la acción
	 * @param as_remoteIp            ip remota del usuario que esta realizando la acción
	 * @return <code>Constantes</code> lleno con la infromación de la BD.
	 * @throws B2BException de b 2 B exception
	 */
	public Constantes descargarPlantillaCargue(
	    CamposConsulta acc_camposConsulta, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch  lsw_watch;
		Constantes lc_return;

		lsw_watch     = Logger.getNewStopWatch();
		lc_return     = getRegistroBusiness().descargarPlantillaCargue(acc_camposConsulta);

		Logger.log(lsw_watch, "RegistroBean", "descargarPlantillaCargue", as_userId, as_localIp, as_remoteIp, null);

		return lc_return;
	}

	/** {@inheritdoc} */
	public Collection<Turno> detalleTurnosRecepcion(
	    Documento ad_parametros, boolean ab_consultaDocumento, String as_userId, String as_localIp, String remoteIp
	)
	    throws B2BException
	{
		StopWatch         lsw_watch;
		Collection<Turno> lct_ct;

		lsw_watch     = Logger.getNewStopWatch();

		lct_ct = getRegistroBusiness().detalleTurnosRecepcion(ad_parametros, ab_consultaDocumento, as_userId);

		Logger.log(lsw_watch, "RegistroBean", "detalleTurnosRecepcion", as_userId, as_localIp, remoteIp, lct_ct);

		return lct_ct;
	}

	/** {@inheritdoc} */
	public Registro documentoNuevaEntrada(Registro lr_registro)
	    throws B2BException
	{
		StopWatch lsw_watch;
		Registro  lr_return;

		lsw_watch     = Logger.getNewStopWatch();

		lr_return = getRegistroBusiness().documentoNuevaEntrada(lr_registro);

		Logger.log(lsw_watch, "RegistroBean", "documentoNuevaEntrada", null, null, null, null);

		return lr_return;
	}

	/** {@inheritdoc} */
	public Registro eliminarActosReproduccionConstancia(Registro ar_parametros, String as_userId)
	    throws B2BException
	{
		StopWatch lsw_watch;
		Registro  lr_registro;

		lsw_watch     = Logger.getNewStopWatch();

		lr_registro = getRegistroBusiness().eliminarActosReproduccionConstancia(ar_parametros, as_userId);

		Logger.log(lsw_watch, "RegistroBean", "eliminarActosReproduccionConstancia", as_userId, null, null, null);

		return lr_registro;
	}

	/** {@inheritdoc} */
	public Collection<DatosAntSistema> eliminarDatosAntSistema(
	    DatosAntSistema adas_param, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch                   lsw_watch;
		Collection<DatosAntSistema> lcdas_datos;

		lsw_watch     = Logger.getNewStopWatch();

		lcdas_datos = getRegistroBusiness().eliminarDatosAntSistema(adas_param, as_userId, as_remoteIp);

		Logger.log(
		    lsw_watch, "RegistroBean", "eliminarDatosAntSistema", as_userId, as_localIp, as_remoteIp, lcdas_datos
		);

		return lcdas_datos;
	}

	/** {@inheritdoc} */
	public void eliminarDetalleAntSistema(
	    DetalleAntSistema adas_param, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch lsw_watch;

		lsw_watch = Logger.getNewStopWatch();

		getRegistroBusiness().eliminarDetalleAntSistema(adas_param);

		Logger.log(lsw_watch, "RegistroBean", "eliminarDetalleAntSistema", as_userId, as_localIp, as_remoteIp, null);
	}

	/** {@inheritdoc} */
	public void eliminarInterviniente(SolicitudInterviniente asi_parametros, String as_userId)
	    throws B2BException
	{
		StopWatch lsw_watch;

		lsw_watch = Logger.getNewStopWatch();

		getRegistroBusiness().eliminarInterviniente(asi_parametros, as_userId);

		Logger.log(lsw_watch, "RegistroBean", "eliminarInterviniente", as_userId, null, null, null);
	}

	/** {@inheritdoc} */
	public void eliminarMatriculaSegregacionPorSolicitud(
	    String as_idSolicitud, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch lsw_watch;

		lsw_watch = Logger.getNewStopWatch();

		getRegistroBusiness().eliminarMatriculaSegregacionPorSolicitud(as_idSolicitud);

		Logger.log(
		    lsw_watch, "RegistroBean", "eliminarMatriculaSegregacionPorSolicitud", as_userId, as_localIp, as_remoteIp,
		    null
		);
	}

	/** {@inheritdoc} */
	public Collection<SolicitudInterviniente> enlistarPorMapaIntervinientes(
	    Collection<SolicitudInterviniente> acsi_intervinientesActuales,
	    SolicitudInterviniente             asi_intervinienteIngresado, long al_etapaActual, String as_userId,
	    String                             as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch                          lsw_watch;
		Collection<SolicitudInterviniente> lcsi_return;

		lsw_watch     = Logger.getNewStopWatch();

		lcsi_return = getRegistroBusiness()
				              .enlistarPorMapaIntervinientes(
				    acsi_intervinientesActuales, asi_intervinienteIngresado, al_etapaActual
				);

		Logger.log(
		    lsw_watch, "RegistroBean", "enlistarPorMapaIntervinientes", as_userId, as_localIp, as_remoteIp, null
		);

		return lcsi_return;
	}

	/** {@inheritdoc} */
	public AccAreaPredio findAccAreaPredio(AccAreaPredio aaap_accAreaPredio)
	    throws B2BException
	{
		StopWatch     lsw_watch;
		AccAreaPredio laap_return;

		lsw_watch     = Logger.getNewStopWatch();

		laap_return = getRegistroBusiness().findAccAreaPredio(aaap_accAreaPredio);

		Logger.log(lsw_watch, "RegistroBean", "findAccAreaPredio", null, null, null, null);

		return laap_return;
	}

	/** {@inheritdoc} */
	public Acto findActoBy(Acto aa_acto)
	    throws B2BException
	{
		StopWatch lsw_watch;
		Acto      la_return;

		lsw_watch     = Logger.getNewStopWatch();
		la_return     = getRegistroBusiness().findActoBy(aa_acto);

		Logger.log(lsw_watch, "RegistroBean", "findActoBy", null, null, null, null);

		return la_return;
	}

	/** {@inheritdoc} */
	public Acto findActoEnglobes(Acto aa_acto, HashMap<String, String> ahmss_codigos)
	    throws B2BException
	{
		StopWatch lsw_watch;
		Acto      la_return;

		lsw_watch     = Logger.getNewStopWatch();

		la_return = getRegistroBusiness().findActoEnglobes(aa_acto, ahmss_codigos);

		Logger.log(lsw_watch, "RegistroBean", "findActoEnglobes", null, null, null, null);

		return la_return;
	}

	/** {@inheritdoc} */
	public Collection<TipoActo> findActosById(
	    Acto ap_parametros, String as_userId, boolean ab_accion, boolean ab_validarEstado
	)
	    throws B2BException
	{
		StopWatch            lsw_watch;
		Collection<TipoActo> lca_actos;

		lsw_watch     = Logger.getNewStopWatch();

		lca_actos = getRegistroBusiness().findActosById(ap_parametros, as_userId, ab_accion, ab_validarEstado);

		Logger.log(lsw_watch, "RegistroBean", "findActosById", as_userId, null, null, lca_actos);

		return lca_actos;
	}

	/** {@inheritdoc} */
	public Collection<com.bachue.snr.prosnr01.model.registro.Acto> findActosBySolicitud(String idSolicitud)
	    throws B2BException
	{
		StopWatch                                               lsw_watch;
		Collection<com.bachue.snr.prosnr01.model.registro.Acto> lca_actos;

		lsw_watch     = Logger.getNewStopWatch();

		lca_actos = getRegistroBusiness().findActosBySolicitud(idSolicitud);

		Logger.log(lsw_watch, "RegistroBean", "findActosBySolicitud", null, null, null, lca_actos);

		return lca_actos;
	}

	/** {@inheritdoc} */
	public Collection<com.bachue.snr.prosnr01.model.registro.Acto> findActosBySolicitudCirculoTurno(
	    String as_idSolicitud, String as_idTurno
	)
	    throws B2BException
	{
		StopWatch                                               lsw_watch;
		Collection<com.bachue.snr.prosnr01.model.registro.Acto> lca_actos;

		lsw_watch     = Logger.getNewStopWatch();
		lca_actos     = getRegistroBusiness().findActosBySolicitudCirculoTurno(as_idSolicitud, as_idTurno);

		Logger.log(lsw_watch, "RegistroBean", "findActosBySolicitudCirculoTurno", null, null, null, lca_actos);

		return lca_actos;
	}

	/** {@inheritdoc} */
	public Collection<com.bachue.snr.prosnr01.model.registro.Acto> findActosBySolicitudIdCirculo(
	    String as_idSolicitud, String as_idCirculo, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch                                               lsw_watch;
		Collection<com.bachue.snr.prosnr01.model.registro.Acto> lca_actos;

		lsw_watch     = Logger.getNewStopWatch();

		lca_actos = getRegistroBusiness().findActosBySolicitudIdCirculo(as_idSolicitud, as_idCirculo);

		Logger.log(
		    lsw_watch, "RegistroBean", "findActosBySolicitudIdCirculo", as_userId, as_localIp, as_remoteIp, lca_actos
		);

		return lca_actos;
	}

	/** {@inheritdoc} */
	public Collection<com.bachue.snr.prosnr01.model.registro.Acto> findActosHijosById(
	    com.bachue.snr.prosnr01.model.registro.Acto ap_parametros, String as_userId
	)
	    throws B2BException
	{
		StopWatch                                               lsw_watch;
		Collection<com.bachue.snr.prosnr01.model.registro.Acto> lca_actos;

		lsw_watch     = Logger.getNewStopWatch();

		lca_actos = getRegistroBusiness().findActosHijosById(ap_parametros);

		Logger.log(lsw_watch, "RegistroBean", "findActosById", as_userId, null, null, lca_actos);

		return lca_actos;
	}

	/** {@inheritdoc} */
	public Map<String, Boolean> findActosValidarAreaTerreno()
	    throws B2BException
	{
		StopWatch            lsw_watch;
		Map<String, Boolean> llhm_return;

		lsw_watch     = Logger.getNewStopWatch();

		llhm_return = getRegistroBusiness().findActosValidarAreaTerreno();

		Logger.log(lsw_watch, "RegistroBean", "findActosValidarAreaTerreno", null, null, null, null);

		return llhm_return;
	}

	/** {@inheritdoc} */
	public Collection<CausalCorreccion> findAllCausales(SolicitudCorreccion lsc_solicitudCorreccion)
	    throws B2BException
	{
		StopWatch                    lsw_watch;
		Collection<CausalCorreccion> lccc_return;

		lsw_watch     = Logger.getNewStopWatch();

		lccc_return = getRegistroBusiness().findAllCausales(lsc_solicitudCorreccion);

		Logger.log(lsw_watch, "RegistroBean", "findAllCausales", null, null, null, null);

		return lccc_return;
	}

	/** {@inheritdoc} */
	public Collection<TipoDocumental> findAllDocumentales(
	    String as_idActo, String as_codigo, boolean ab_b, String as_idSolicitud
	)
	    throws B2BException
	{
		StopWatch                  lsw_watch;
		Collection<TipoDocumental> ldp_tiposActos;

		lsw_watch     = Logger.getNewStopWatch();

		ldp_tiposActos = getRegistroBusiness().findAllDocumentales(as_idActo, as_codigo, ab_b, as_idSolicitud);

		Logger.log(lsw_watch, "RegistroBean", "findAllDocumentales", null, null, null, null);

		return ldp_tiposActos;
	}

	/** {@inheritdoc} */
	public Collection<RegistroAnotacionProhibicion> findAllRegistroAnotacionProhibicionByCirMat(
	    Solicitud as_s, String as_idCirculo, long al_idMatricula, String as_userId, String as_localIp,
	    String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch                                lsw_watch;
		Collection<RegistroAnotacionProhibicion> lrap_rap;

		lsw_watch     = Logger.getNewStopWatch();

		lrap_rap = getRegistroBusiness()
				           .findAllRegistroAnotacionProhibicionByCirMat(
				    as_s, as_idCirculo, al_idMatricula, as_userId, as_remoteIp
				);

		Logger.log(
		    lsw_watch, "RegistroBean", "findAllRegistroAnotacionProhibicionByCirMat", as_userId, as_localIp, as_remoteIp,
		    lrap_rap
		);

		return lrap_rap;
	}

	/** {@inheritdoc} */
	public Collection<ZonaRegistral> findAllZonaRegistral()
	    throws B2BException
	{
		StopWatch                 lsw_watch;
		Collection<ZonaRegistral> ldp_tiposActos;

		lsw_watch     = Logger.getNewStopWatch();

		ldp_tiposActos = getRegistroBusiness().findAllZonaRegistral();

		Logger.log(lsw_watch, "RegistroBean", "findAllZonaRegistral", null, null, null, null);

		return ldp_tiposActos;
	}

	/** {@inheritdoc} */
	public Collection<AnotacionPredioDireccion> findAnotacionPredioByRadicacion(
	    AnotacionPredioDireccion ap_parametros, String as_userId, DocumentoConstancia documento
	)
	    throws B2BException
	{
		StopWatch                            lsw_watch;
		Collection<AnotacionPredioDireccion> lap_anotacion;

		lsw_watch     = Logger.getNewStopWatch();

		lap_anotacion = getRegistroBusiness().findAnotacionPredioByRadicacion(ap_parametros, documento);

		Logger.log(lsw_watch, "RegistroBean", "findAnotacionPredioByRadicacion", as_userId, null, null, lap_anotacion);

		return lap_anotacion;
	}

	/** {@inheritdoc} */
	public Collection<DataReproduccionConstancia> findAnotacionPredioByRadicacion(
	    AnotacionPredioDireccion asi_parametros, String as_userId
	)
	    throws B2BException
	{
		StopWatch                              lsw_watch;
		Collection<DataReproduccionConstancia> lap_anotacion;

		lsw_watch     = Logger.getNewStopWatch();

		lap_anotacion = getRegistroBusiness().findAnotacionPredioByRadicacion(asi_parametros);

		Logger.log(lsw_watch, "RegistroBean", "findAnotacionPredioByRadicacion", as_userId, null, null, lap_anotacion);

		return lap_anotacion;
	}

	/** {@inheritdoc} */
	public DatosAntSistema findAntSistema(String as_idSolicitud)
	    throws B2BException
	{
		StopWatch       lsw_watch;
		DatosAntSistema ldas_return;

		lsw_watch     = Logger.getNewStopWatch();

		ldas_return = getRegistroBusiness().findAntSistema(as_idSolicitud);

		Logger.log(lsw_watch, "RegistroBean", "findAntSistema", null, null, null, null);

		return ldas_return;
	}

	/** {@inheritdoc} */
	public AreaPredio findAreaPredio(AreaPredio aap_areaPredio)
	    throws B2BException
	{
		StopWatch  lsw_watch;
		AreaPredio lb_existe;

		lsw_watch     = Logger.getNewStopWatch();

		lb_existe = getRegistroBusiness().findAreaPredio(aap_areaPredio);

		Logger.log(lsw_watch, "RegistroBean", "findAreaPredio", null, null, null, null);

		return lb_existe;
	}

	/** {@inheritdoc} */
	public double findAreaTerrenoByMatricula(String ls_matricula)
	    throws B2BException
	{
		StopWatch lsw_watch;
		double    llhm_return;

		lsw_watch     = Logger.getNewStopWatch();

		llhm_return = getRegistroBusiness().findAreaTerrenoByMatricula(ls_matricula);

		Logger.log(lsw_watch, "RegistroBean", "findAreaTerrenoByMatricula", null, null, null, null);

		return llhm_return;
	}

	/** {@inheritdoc} */
	public Collection<DatosAntSistema> findByIdMatriculaGrabacion(
	    DatosAntSistema adas_datos, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch                   lsw_watch;
		Collection<DatosAntSistema> lca_actos;

		lsw_watch     = Logger.getNewStopWatch();

		lca_actos = getRegistroBusiness().findByIdMatriculaGrabacion(adas_datos);

		Logger.log(
		    lsw_watch, "RegistroBean", "findByIdMatriculaGrabacion", as_userId, as_localIp, as_remoteIp, lca_actos
		);

		return lca_actos;
	}

	/** {@inheritdoc} */
	public ZonaRegistral findCirculoRegistralActoAntiguoSistema(Solicitud as_solicitud)
	    throws B2BException
	{
		StopWatch     lsw_watch;
		ZonaRegistral lzr_return;

		lsw_watch     = Logger.getNewStopWatch();

		lzr_return = getRegistroBusiness().findCirculoRegistralActoAntiguoSistema(as_solicitud);

		Logger.log(lsw_watch, "RegistroBean", "findCirculoRegistralActoAntiguoSistema", null, null, null, null);

		return lzr_return;
	}

	/** {@inheritdoc} */
	public CirculoRegistral findCirculoRegistralById(CirculoRegistral ap_parametros, String as_userId)
	    throws B2BException
	{
		StopWatch        lsw_watch;
		CirculoRegistral lcr_cr;

		lsw_watch     = Logger.getNewStopWatch();

		lcr_cr = getRegistroBusiness().findCirculoRegistralById(ap_parametros, as_userId);

		Logger.log(lsw_watch, "RegistroBean", "findCirculoRegistralById", as_userId, null, null, null);

		return lcr_cr;
	}

	/** {@inheritdoc} */
	public Collection<AccCompletitudDocumental> findCompletitudDocumentalByIdTurno(
	    String as_idTurno, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch                            lsw_watch;
		Collection<AccCompletitudDocumental> adas_datos;

		lsw_watch     = Logger.getNewStopWatch();

		adas_datos = getRegistroBusiness().findCompletitudDocumentalByIdTurno(as_idTurno);

		Logger.log(
		    lsw_watch, "RegistroBean", "findByIdMatriculaGrabacion", as_userId, as_localIp, as_remoteIp, adas_datos
		);

		return adas_datos;
	}

	/** {@inheritdoc} */
	public Collection<AccCompletitudDocumental> findCompletitudDocumentalByIdTurnoSolicitud(
	    String as_idTurno, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch                            lsw_watch;
		Collection<AccCompletitudDocumental> adas_datos;

		lsw_watch     = Logger.getNewStopWatch();

		adas_datos = getRegistroBusiness().findCompletitudDocumentalByIdTurnoSolicitud(as_idTurno);

		Logger.log(
		    lsw_watch, "RegistroBean", "findByIdMatriculaGrabacion", as_userId, as_localIp, as_remoteIp, adas_datos
		);

		return adas_datos;
	}

	/** {@inheritdoc} */
	public Constantes findConstante(Constantes ac_actosSegregacion)
	    throws B2BException
	{
		StopWatch  lsw_watch;
		Constantes lc_return;

		lsw_watch     = Logger.getNewStopWatch();

		lc_return = getRegistroBusiness().findConstante(ac_actosSegregacion);

		Logger.log(lsw_watch, "RegistroBean", "findConstante", null, null, null, null);

		return lc_return;
	}

	/** {@inheritdoc} */
	public PersonaCorreoElectronico findCorreoByIdPersona(PersonaCorreoElectronico lpce_correo)
	    throws B2BException
	{
		StopWatch                lsw_watch;
		PersonaCorreoElectronico lpce_return;

		lsw_watch     = Logger.getNewStopWatch();

		lpce_return = getRegistroBusiness().findCorreoByIdPersona(lpce_correo);

		Logger.log(lsw_watch, "RegistroBean", "findCorreoByIdPersona", null, null, null, null);

		return lpce_return;
	}

	/** {@inheritdoc} */
	public ZonaRegistral findDatosZonaRegistralByCirculo(
	    String as_idCirculo, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch     lsw_watch;
		ZonaRegistral lzr_return;

		lsw_watch     = Logger.getNewStopWatch();

		lzr_return = getRegistroBusiness().findDatosZonaRegistralByCirculo(as_idCirculo);

		Logger.log(
		    lsw_watch, "RegistroBean", "findDatosZonaRegistralByCirculo", as_userId, as_localIp, as_remoteIp, null
		);

		return lzr_return;
	}

	/** {@inheritdoc} */
	public com.bachue.snr.prosnr01.model.registro.Acto findDetalleActo(
	    String as_idActoDb, com.bachue.snr.prosnr01.model.registro.Acto aoa_datosActo, String as_userId,
	    String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		com.bachue.snr.prosnr01.model.registro.Acto ls_actoId;
		StopWatch                                   lsw_watch;

		lsw_watch     = Logger.getNewStopWatch();

		ls_actoId = getRegistroBusiness().findDetalleActo(as_idActoDb, aoa_datosActo, as_userId, as_remoteIp);

		Logger.log(lsw_watch, "RegistroBean", "findDetalleActo", as_userId, as_localIp, as_remoteIp, null);

		return ls_actoId;
	}

	/** {@inheritdoc} */
	public Collection<DetalleAntSistema> findDetallesAntSistema(
	    String as_idDatosAntSis, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch                     lsw_watch;
		Collection<DetalleAntSistema> lcdas_return;

		lsw_watch     = Logger.getNewStopWatch();

		lcdas_return = getRegistroBusiness().findDetallesAntSistema(as_idDatosAntSis);

		Logger.log(lsw_watch, "RegistroBean", "findDetallesAntSistema", as_userId, as_localIp, as_remoteIp, null);

		return lcdas_return;
	}

	/** {@inheritdoc} */
	public PersonaDireccion findDireccionByIdPersona(PersonaDireccion apd_pd, String as_tipoDir)
	    throws B2BException
	{
		StopWatch        lsw_watch;
		PersonaDireccion lpd_return;

		lsw_watch     = Logger.getNewStopWatch();

		lpd_return = getRegistroBusiness().findDireccionByIdPersona(apd_pd, as_tipoDir);

		Logger.log(lsw_watch, "RegistroBean", "findDireccionByIdPersona", null, null, null, null);

		return lpd_return;
	}

	/** {@inheritdoc} */
	public DireccionPredio findDireccionPredioById(DireccionPredio ap_parametros, String as_userId)
	    throws B2BException
	{
		StopWatch       lsw_watch;
		DireccionPredio ldp_direccionPredio;

		lsw_watch     = Logger.getNewStopWatch();

		ldp_direccionPredio = getRegistroBusiness().findDireccionPredioById(ap_parametros, as_userId);

		Logger.log(lsw_watch, "RegistroBean", "findPersonByDocument", as_userId, null, null, null);

		return ldp_direccionPredio;
	}

	/** {@inheritdoc} */
	public Collection<TipoDocumental> findDocumentalesCorrecciones()
	    throws B2BException
	{
		StopWatch                  lsw_watch;
		Collection<TipoDocumental> lctd_return;

		lsw_watch       = Logger.getNewStopWatch();
		lctd_return     = getRegistroBusiness().findDocumentalesCorrecciones();

		Logger.log(lsw_watch, "RegistroBean", "findDocumentalesCorrecciones", null, null, null, null);

		return lctd_return;
	}

	/** {@inheritdoc} */
	public Documento findDocumento(String idSolicitud)
	    throws B2BException
	{
		StopWatch lsw_watch;
		Documento ld_return;

		lsw_watch     = Logger.getNewStopWatch();
		ld_return     = getRegistroBusiness().findDocumento(idSolicitud);

		Logger.log(lsw_watch, "RegistroBean", "findDocumento", null, null, null, null);

		return ld_return;
	}

	/** {@inheritdoc} */
	public Collection<DocumentoConstancia> findDocumentosConstancia(
	    DocumentoConstancia ap_parametros, String as_userId
	)
	    throws B2BException
	{
		StopWatch                       lsw_watch;
		Collection<DocumentoConstancia> lap_anotacion;

		lsw_watch     = Logger.getNewStopWatch();

		lap_anotacion = getRegistroBusiness().findDocumentosConstancia(ap_parametros);

		Logger.log(lsw_watch, "RegistroBean", "findDocumentosConstancia", as_userId, null, null, lap_anotacion);

		return lap_anotacion;
	}

	/** {@inheritdoc} */
	public Registro findEntidadExterna(
	    AccEntidadExterna aaee_param, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch lsw_watch;
		Registro  lr_return;

		lsw_watch     = Logger.getNewStopWatch();
		lr_return     = getRegistroBusiness().findEntidadExterna(aaee_param, as_userId, as_remoteIp);

		Logger.log(lsw_watch, "RegistroBean", "findEntidadExterna", as_userId, as_localIp, as_remoteIp, null);

		return lr_return;
	}

	/** {@inheritdoc} */
	public EstadoPredio findEstadoPredioById(EstadoPredio ap_parametros, String as_userId)
	    throws B2BException
	{
		StopWatch    lsw_watch;
		EstadoPredio lcr_cr;

		lsw_watch     = Logger.getNewStopWatch();

		lcr_cr = getRegistroBusiness().findEstadoPredioById(ap_parametros, as_userId);

		Logger.log(lsw_watch, "RegistroBean", "findEstadoPredioById", as_userId, null, null, null);

		return lcr_cr;
	}

	/** {@inheritdoc} */
	public Collection<GravamenPendiente> findGravamenPendiente(
	    String as_circulo, String as_matricula, String as_userId, String as_localIp, String remoteIp
	)
	    throws B2BException
	{
		StopWatch                     lsw_watch;
		Collection<GravamenPendiente> lcgp_return;

		lsw_watch     = Logger.getNewStopWatch();

		lcgp_return = getRegistroBusiness().findGravamenPendiente(as_circulo, as_matricula);

		Logger.log(lsw_watch, "RegistroBean", "findGravamenPendiente", as_userId, as_localIp, remoteIp, lcgp_return);

		return lcgp_return;
	}

	/** {@inheritdoc} */
	public String findIdCirculo(String as_nirGenerado, String as_userId, String as_ipLocal, String as_ipRemota)
	    throws B2BException
	{
		StopWatch lsw_watch;
		String    ls_idCirculo;

		lsw_watch     = Logger.getNewStopWatch();

		ls_idCirculo = getRegistroBusiness().findIdCirculo(as_nirGenerado);

		Logger.log(lsw_watch, "RegistroBean", "cargarDireccionCompleta", as_userId, as_ipLocal, as_ipRemota, null);

		return ls_idCirculo;
	}

	/** {@inheritdoc} */
	public String findIdSolicitudByIdTurno(String as_idTurno, String as_userId, String as_localIp, String as_remoteIp)
	    throws B2BException
	{
		StopWatch lsw_watch;
		String    ls_return;

		lsw_watch     = Logger.getNewStopWatch();

		ls_return = getRegistroBusiness().findIdSolicitudByIdTurno(as_idTurno);

		Logger.log(lsw_watch, "RegistroBean", "findIdSolicitudByIdTurno", as_userId, as_localIp, as_remoteIp, null);

		return ls_return;
	}

	/** {@inheritdoc} */
	public Collection<MatriculaSegregacion> findMatriculaSegregacionByIdSolicitud(
	    String as_idSolicitud, String as_idMatricula
	)
	    throws B2BException
	{
		StopWatch                        lsw_watch;
		Collection<MatriculaSegregacion> lca_actos;
		lsw_watch     = Logger.getNewStopWatch();
		lca_actos     = getRegistroBusiness().findMatriculaSegregacionByIdSolicitud(as_idSolicitud, as_idMatricula);
		Logger.log(lsw_watch, "ConsultaSolicitudBusiness", "findByIdSolicitud", as_idSolicitud, null, null, null);

		return lca_actos;
	}

	/** {@inheritdoc} */
	public OficinaOrigen findOficinaOrigen(OficinaOrigen aoo_oo)
	    throws B2BException
	{
		StopWatch     lsw_watch;
		OficinaOrigen loo_return;

		lsw_watch     = Logger.getNewStopWatch();

		loo_return = getRegistroBusiness().findOficinaOrigen(aoo_oo);

		Logger.log(lsw_watch, "RegistroBean", "findOficinaOrigen", null, null, null, null);

		return loo_return;
	}

	/** {@inheritdoc} */
	public Collection<Persona> findPersonByDocument(Persona ap_parametros, String as_userId)
	    throws B2BException
	{
		StopWatch           lsw_watch;
		Collection<Persona> lcp_persona;

		lsw_watch     = Logger.getNewStopWatch();

		lcp_persona = getRegistroBusiness().findPersonByDocument(ap_parametros, as_userId);

		Logger.log(lsw_watch, "RegistroBean", "findPersonByDocument", as_userId, null, null, lcp_persona);

		return lcp_persona;
	}

	/** {@inheritdoc} */
	public Registro findPersonByDocument(Registro ap_parametros, String as_userId)
	    throws B2BException
	{
		StopWatch lsw_watch;
		Registro  lcp_registro;

		lsw_watch        = Logger.getNewStopWatch();
		lcp_registro     = getRegistroBusiness().findPersonByDocument(ap_parametros, as_userId);

		Logger.log(lsw_watch, "RegistroBean", "findPersonByDocument", as_userId, null, null, null);

		return lcp_registro;
	}

	/** {@inheritdoc} */
	public Persona findPersonaByIdPersona(Persona lp_p, String userId)
	    throws B2BException
	{
		StopWatch lsw_watch;
		Persona   lp_persona;

		lsw_watch     = Logger.getNewStopWatch();

		lp_persona = getRegistroBusiness().findPersonaByIdPersona(lp_p, userId);

		Logger.log(lsw_watch, "RegistroBean", "findPersonaByIdPersona", userId, null, null, null);

		return lp_persona;
	}

	/** {@inheritdoc} */
	public PredioRegistro findPredioRegistroByCirculoMatricula(PredioRegistro apr_pr)
	    throws B2BException
	{
		StopWatch      lsw_watch;
		PredioRegistro lpr_return;

		lsw_watch     = Logger.getNewStopWatch();

		lpr_return = getRegistroBusiness().findPredioRegistroByCirculoMatricula(apr_pr);

		Logger.log(lsw_watch, "RegistroBean", "findPredioRegistroByCirculoMatricula", null, null, null, null);

		return lpr_return;
	}

	/** {@inheritdoc} */
	public PredioRegistro findPredioRegistroById(PredioRegistro ap_parametros, String as_userId)
	    throws B2BException
	{
		StopWatch      lsw_watch;
		PredioRegistro ldp_direccionPredio;

		lsw_watch     = Logger.getNewStopWatch();

		ldp_direccionPredio = getRegistroBusiness().findPredioRegistroById(ap_parametros, as_userId);

		Logger.log(lsw_watch, "RegistroBean", "findPredioRegistroById", as_userId, null, null, null);

		return ldp_direccionPredio;
	}

	/** {@inheritdoc} */
	public Collection<ProhibicionVPM> findProhibicionesVPM(
	    ProhibicionVPM apv_pv, String as_userId, String as_localIp, String remoteIp
	)
	    throws B2BException
	{
		StopWatch                  lsw_watch;
		Collection<ProhibicionVPM> lcpv_return;

		lsw_watch     = Logger.getNewStopWatch();

		lcpv_return = getRegistroBusiness().findProhibicionesVPM(apv_pv);

		Logger.log(lsw_watch, "RegistroBean", "findProhibicionesVPM", as_userId, as_localIp, remoteIp, lcpv_return);

		return lcpv_return;
	}

	/** {@inheritdoc} */
	public RegistroAnotacionProhibicion findRegistroAnotacionProhibicionByCirMat(
	    Solicitud as_s, String as_idCirculo, long al_idMatricula, String as_userId, String as_localIp,
	    String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch                    lsw_watch;
		RegistroAnotacionProhibicion lrap_rap;

		lsw_watch     = Logger.getNewStopWatch();

		lrap_rap = getRegistroBusiness()
				           .findRegistroAnotacionProhibicionByCirMat(
				    as_s, as_idCirculo, al_idMatricula, as_userId, as_remoteIp
				);

		Logger.log(
		    lsw_watch, "RegistroBean", "findRegistroAnotacionProhibicionByCirMat", as_userId, as_localIp, as_remoteIp,
		    null
		);

		return lrap_rap;
	}

	/** {@inheritdoc} */
	public Collection<RegistroAnotacionProhibicion> findRegistroAnotacionProhibicionByTurno(
	    Turno at_turno, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch                                lsw_watch;
		Collection<RegistroAnotacionProhibicion> lcrap_crap;

		lsw_watch     = Logger.getNewStopWatch();

		lcrap_crap = getRegistroBusiness().findRegistroAnotacionProhibicionByTurno(at_turno, as_userId, as_remoteIp);

		Logger.log(
		    lsw_watch, "RegistroBean", "findRegistroAnotacionProhibicionByTurno", as_userId, as_localIp, as_remoteIp,
		    null
		);

		return lcrap_crap;
	}

	/** {@inheritdoc} */
	public SolicitudAsociada findSolicitudAsociada(Solicitud as_solicitud)
	    throws B2BException
	{
		StopWatch         lsw_watch;
		SolicitudAsociada lsa_return;

		lsw_watch     = Logger.getNewStopWatch();

		lsa_return = getRegistroBusiness().findSolicitudAsociada(as_solicitud);

		Logger.log(lsw_watch, "RegistroBean", "findSolicitudAsociada", null, null, null, null);

		return lsa_return;
	}

	/** {@inheritdoc} */
	public SolicitudAsociada findSolicitudAsociadaProcesoSubProceso(
	    Solicitud as_solicitud, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch         lsw_watch;
		SolicitudAsociada lsa_return;

		lsw_watch     = Logger.getNewStopWatch();

		lsa_return = getRegistroBusiness().findSolicitudAsociadaProcesoSubProceso(as_solicitud);

		Logger.log(
		    lsw_watch, "RegistroBean", "findSolicitudAsociadaProcesoSubProceso", as_userId, as_localIp, as_remoteIp,
		    null
		);

		return lsa_return;
	}

	/** {@inheritdoc} */
	public Solicitud findSolicitudById(Solicitud ap_parametros, String as_userId)
	    throws B2BException
	{
		StopWatch lsw_watch;
		Solicitud lcp_registro;

		lsw_watch     = Logger.getNewStopWatch();

		lcp_registro = getRegistroBusiness().findSolicitudById(ap_parametros);

		Logger.log(lsw_watch, "RegistroBean", "findPersonByDocument", as_userId, null, null, null);

		return lcp_registro;
	}

	/** {@inheritdoc} */
	public Collection<SolicitudInterviniente> findSolicitudIntervinienteBySolicitud(
	    SolicitudInterviniente asi_parametros, String as_userId
	)
	    throws B2BException
	{
		StopWatch                          lsw_watch;
		Collection<SolicitudInterviniente> lcsi_datos;

		lsw_watch     = Logger.getNewStopWatch();

		lcsi_datos = getRegistroBusiness().findSolicitudIntervinienteBySolicitud(asi_parametros);

		Logger.log(
		    lsw_watch, "RegistroBean", "findSolicitudIntervinienteBySolicitud", as_userId, null, null, lcsi_datos
		);

		return lcsi_datos;
	}

	/** {@inheritdoc} */
	public PersonaTelefono findTelefonoByIdPersona(PersonaTelefono lpt_telFijo, String tipoTel)
	    throws B2BException
	{
		StopWatch       lsw_watch;
		PersonaTelefono lpt_return;

		lsw_watch     = Logger.getNewStopWatch();

		lpt_return = getRegistroBusiness().findTelefonoByIdPersona(lpt_telFijo, tipoTel);

		Logger.log(lsw_watch, "RegistroBean", "findTelefonoByIdPersona", null, null, null, null);

		return lpt_return;
	}

	/** {@inheritdoc} */
	public Testamento findTestamentoByDocumento(
	    DocumentoConstancia adc_param, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch  lsw_watch;
		Testamento lca_actos;

		lsw_watch     = Logger.getNewStopWatch();

		lca_actos = getRegistroBusiness().findTestamentoByDocumento(adc_param);

		Logger.log(lsw_watch, "RegistroBean", "findByIdMatriculaGrabacion", as_userId, as_localIp, as_remoteIp, null);

		return lca_actos;
	}

	/** {@inheritdoc} */
	public TipoActo findTipoActoById(TipoActo ata_parametros)
	    throws B2BException
	{
		TipoActo  lta_datos;
		StopWatch lsw_watch;

		lsw_watch     = Logger.getNewStopWatch();

		lta_datos = getRegistroBusiness().findTipoActoById(ata_parametros);

		Logger.log(lsw_watch, "RegistroBean", "findTipoActoById", null, null, null, null);

		return lta_datos;
	}

	/** {@inheritdoc} */
	public Collection<TipoAdquisicion> findTipoAdqui()
	    throws B2BException
	{
		StopWatch                   lsw_watch;
		Collection<TipoAdquisicion> ldp_tiposActos;

		lsw_watch     = Logger.getNewStopWatch();

		ldp_tiposActos = getRegistroBusiness().findTipoAdqui();

		Logger.log(lsw_watch, "RegistroBean", "findTipoAdqui", null, null, null, null);

		return ldp_tiposActos;
	}

	/** {@inheritdoc} */
	public Collection<TipoDocumentoPublico> findTipoDocumentoPublicoByConstante()
	    throws B2BException
	{
		StopWatch                        lsw_watch;
		Collection<TipoDocumentoPublico> lcidt_datos;

		lsw_watch       = Logger.getNewStopWatch();
		lcidt_datos     = getRegistroBusiness().findTipoDocumentoPublicoByConstante();

		Logger.log(lsw_watch, "ReferenceBean", "findTipoDocumentoPublicoByConstante", null, null, null, lcidt_datos);

		return lcidt_datos;
	}

	/** {@inheritdoc} */
	public Collection<TipoDocumentoPublico> findTipoDocumentoPublicoTraslado()
	    throws B2BException
	{
		StopWatch                        lsw_watch;
		Collection<TipoDocumentoPublico> lctdp_documentos;

		lsw_watch            = Logger.getNewStopWatch();
		lctdp_documentos     = getRegistroBusiness().findTipoDocumentoPublicoTraslado();

		Logger.log(lsw_watch, "RegistroBean", "findTipoDocumentoPublicoTraslado", null, null, null, lctdp_documentos);

		return lctdp_documentos;
	}

	/** {@inheritdoc} */
	public Collection<Turno> findTurnoByNir(Turno at_turno)
	    throws B2BException
	{
		StopWatch         lsw_watch;
		Collection<Turno> lca_actos;

		lsw_watch     = Logger.getNewStopWatch();

		lca_actos = getRegistroBusiness().findTurnoByNir(at_turno);

		Logger.log(lsw_watch, "RegistroBean", "findTurnoByNir", null, null, null, lca_actos);

		return lca_actos;
	}

	/** {@inheritdoc} */
	public Collection<String> findTurnosBloqueoPredio(PredioRegistro lpr_predioR)
	    throws B2BException
	{
		StopWatch          lsw_watch;
		Collection<String> lcs_return;

		lsw_watch     = Logger.getNewStopWatch();

		lcs_return = getRegistroBusiness().findTurnosBloqueoPredio(lpr_predioR);

		Logger.log(lsw_watch, "RegistroBean", "findTurnosBloqueoPredio", null, null, null, null);

		return lcs_return;
	}

	/** {@inheritdoc} */
	public Collection<String> findTurnosBloqueoPredio(DatosAntSistema lpr_predioR)
	    throws B2BException
	{
		StopWatch          lsw_watch;
		Collection<String> lcs_return;

		lsw_watch     = Logger.getNewStopWatch();

		lcs_return = getRegistroBusiness().findTurnosBloqueoPredio(lpr_predioR);

		Logger.log(lsw_watch, "RegistroBean", "findTurnosBloqueoPredio", null, null, null, null);

		return lcs_return;
	}

	/** {@inheritdoc} */
	public ZonaRegistral findZonaRegistralById(ZonaRegistral ap_parametros, String as_userId)
	    throws B2BException
	{
		StopWatch     lsw_watch;
		ZonaRegistral lcr_cr;

		lsw_watch     = Logger.getNewStopWatch();

		lcr_cr = getRegistroBusiness().findZonaRegistralById(ap_parametros, as_userId);

		Logger.log(lsw_watch, "RegistroBean", "findZonaRegistralById", as_userId, null, null, null);

		return lcr_cr;
	}

	/** {@inheritdoc} */
	public Registro generarReciboCaja(
	    Registro ap_parametros, boolean ab_b, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch lsw_watch;
		Registro  lcp_registro;

		lsw_watch     = Logger.getNewStopWatch();

		lcp_registro = getRegistroBusiness().generarReciboCaja(ap_parametros, ab_b, as_userId, as_localIp, as_remoteIp);

		Logger.log(lsw_watch, "RegistroBean", "generarReciboCaja", as_userId, as_localIp, as_remoteIp, null);

		return lcp_registro;
	}

	/** {@inheritdoc} */
	public Registro generarReciboLiquidacion(
	    Registro ap_parametros, boolean ab_b, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch lsw_watch;
		Registro  lcp_registro;

		lsw_watch     = Logger.getNewStopWatch();

		lcp_registro = getRegistroBusiness()
				               .generarReciboLiquidacion(ap_parametros, ab_b, as_userId, as_localIp, as_remoteIp);

		Logger.log(lsw_watch, "RegistroBean", "generarReciboLiquidacion", as_userId, as_localIp, as_remoteIp, null);

		return lcp_registro;
	}

	/** {@inheritdoc} */
	public Registro generarSolicitudGrabacionMatriculasCorrecciones(
	    Registro ar_data, String as_userId, String as_remoteIp, String as_localIp
	)
	    throws B2BException, IOException
	{
		StopWatch lsw_watch;
		Registro  lr_return;

		lsw_watch     = Logger.getNewStopWatch();
		lr_return     = getRegistroBusiness()
				                .generarSolicitudGrabacionMatriculasCorrecciones(ar_data, as_userId, as_remoteIp);

		Logger.log(
		    lsw_watch, "RegistroBean", "generarSolicitudGrabacionMatriculasCorrecciones", as_userId, as_localIp,
		    as_remoteIp, null
		);

		return lr_return;
	}

	/** {@inheritdoc} */
	public CriteriosDeBusqueda guardarCriterios(
	    CriteriosDeBusqueda acb_cb, String as_userId, String as_ipLocal, String as_ipRemota
	)
	    throws B2BException
	{
		StopWatch           lsw_watch;
		CriteriosDeBusqueda lcts_ts;

		lsw_watch     = Logger.getNewStopWatch();

		lcts_ts = getCriteriosConsultaBusiness().guardarCriterios(acb_cb, as_userId, as_ipRemota);

		Logger.log(lsw_watch, "RegistroBean", "guardarCriterios", as_userId, as_ipLocal, as_ipRemota, null);

		return lcts_ts;
	}

	/** {@inheritdoc} */
	public void guardarCriteriosCampos(
	    CamposConsulta acc_camposConsulta, String as_userId, String as_ipLocal, String as_ipRemota
	)
	    throws B2BException
	{
		StopWatch lsw_watch;

		lsw_watch = Logger.getNewStopWatch();

		getCriteriosConsultaBusiness().guardarCriteriosCampos(acc_camposConsulta, as_userId, as_ipRemota);

		Logger.log(lsw_watch, "RegistroBean", "guardarCriteriosCampos", as_userId, as_ipLocal, as_ipRemota, null);
	}

	/** {@inheritdoc} */
	public CamposConsulta guardarCriteriosYConsultarCopias(
	    Collection<CamposConsulta> acc_camposConsulta, Solicitud as_solicitud, boolean ab_guardarCriterios,
	    boolean ab_traerSolicitudCopias, String as_userId, String as_ipLocal, String as_ipRemota
	)
	    throws B2BException
	{
		StopWatch      lsw_watch;
		CamposConsulta lcc_camposConsulta;

		lsw_watch              = Logger.getNewStopWatch();
		lcc_camposConsulta     = getCriteriosConsultaBusiness()
				                         .guardarCriteriosYConsultarCopias(
				    acc_camposConsulta, as_solicitud, ab_guardarCriterios, ab_traerSolicitudCopias, as_userId,
				    as_ipRemota
				);

		Logger.log(
		    lsw_watch, "RegistroBean", "guardarCriteriosYConsultarCopias", as_userId, as_ipLocal, as_ipRemota, null
		);

		return lcc_camposConsulta;
	}

	/** {@inheritdoc} */
	public DatosAntSistema guardarDatosAntSistema(
	    DatosAntSistema adas_param, String as_userId, String as_localIp, String as_remoteIp, boolean ab_guardar
	)
	    throws B2BException
	{
		StopWatch       lsw_watch;
		DatosAntSistema ldas_data;

		lsw_watch     = Logger.getNewStopWatch();

		ldas_data = getRegistroBusiness().guardarDatosAntSistema(adas_param, as_userId, as_remoteIp, ab_guardar);

		Logger.log(lsw_watch, "RegistroBean", "guardarDatosAntSistema", as_userId, as_localIp, as_remoteIp, null);

		return ldas_data;
	}

	/** {@inheritdoc} */
	public DetalleAntSistema guardarDetallesAntSistema(
	    DetalleAntSistema adas_param, String as_userId, String as_localIp, String as_remoteIp, boolean ab_guardar
	)
	    throws B2BException
	{
		StopWatch         lsw_watch;
		DetalleAntSistema ldas_return;

		lsw_watch     = Logger.getNewStopWatch();

		ldas_return = getRegistroBusiness().guardarDetallesAntSistema(adas_param, as_userId, as_remoteIp, ab_guardar);

		Logger.log(lsw_watch, "RegistroBean", "guardarDetallesAntSistema", as_userId, as_localIp, as_remoteIp, null);

		return ldas_return;
	}

	/** {@inheritdoc} */
	public void guardarFechaEjecutoriaActo(
	    String as_idSolicitud, Date ad_fechaEjecutoria, String as_userId, String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch lsw_watch;

		lsw_watch = Logger.getNewStopWatch();

		getRegistroBusiness().guardarFechaEjecutoriaActo(as_idSolicitud, ad_fechaEjecutoria, as_userId, as_remoteIp);

		Logger.log(lsw_watch, "RegistroBean", "guardarFechaEjecutoriaActo", as_userId, null, as_remoteIp, null);
	}

	/** {@inheritdoc} */
	public void guardarMatriculasTraslado(
	    Collection<TrasladoMatricula> actm_matriculas, Collection<SoporteTraslado> acst_soportes, Solicitud as_solicitud,
	    String as_userId, String as_ipLocal, String as_ipRemota
	)
	    throws B2BException
	{
		StopWatch lsw_watch;

		lsw_watch = Logger.getNewStopWatch();
		getRegistroBusiness()
			    .guardarMatriculasTraslado(actm_matriculas, acst_soportes, as_solicitud, as_userId, as_ipRemota);

		Logger.log(lsw_watch, "RegistroBean", "guardarMatriculasTraslado", as_userId, as_ipLocal, as_ipRemota, null);
	}

	/** {@inheritdoc} */
	public void guardarSolicitudCopiasPreliquidar(
	    BuscarAntiguoSistema abas_parametros, String as_userId, String as_ipLocal, String as_ipRemota
	)
	    throws B2BException
	{
		StopWatch lsw_watch;

		lsw_watch = Logger.getNewStopWatch();
		getCriteriosConsultaBusiness().guardarSolicitudCopiasPreliquidar(abas_parametros, as_userId, as_ipRemota);

		Logger.log(
		    lsw_watch, "RegistroBean", "guardarCriteriosYConsultarCopias", as_userId, as_ipLocal, as_ipRemota, null
		);
	}

	/** {@inheritdoc} */
	public Testamento guardarTestamento(
	    SolicitudTestamento at_testamento, String as_userId, String as_ipLocal, String as_ipRemota
	)
	    throws B2BException
	{
		StopWatch  lsw_watch;
		Testamento lt_testamento;
		lsw_watch     = Logger.getNewStopWatch();

		lt_testamento = getRegistroBusiness().guardarTestamento(at_testamento, as_userId, as_ipRemota);

		Logger.log(lsw_watch, "RegistroBean", "guardarTestamento", as_userId, as_ipLocal, as_ipRemota, null);

		return lt_testamento;
	}

	/** {@inheritdoc} */
	public Turno guardarTiposDocImpuestoGobernacion(
	    Turno at_turno, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch lsw_watch;
		Turno     lt_turno;

		lsw_watch     = Logger.getNewStopWatch();

		lt_turno = getRegistroBusiness().guardarTiposDocImpuestoGobernacion(at_turno, as_userId, as_remoteIp);

		Logger.log(
		    lsw_watch, "RegistroBean", "guardarTiposDocImpuestoGobernacion", as_userId, as_localIp, as_remoteIp, null
		);

		return lt_turno;
	}

	/** {@inheritdoc} */
	public void guardarTiposDocumentales(
	    String as_idSolicitud, Collection<TipoDocumental> acctd_td, String alid_acto, String as_userId, String as_codigo,
	    boolean ab_procesoCorrecciones
	)
	    throws B2BException
	{
		StopWatch lsw_watch;

		lsw_watch = Logger.getNewStopWatch();

		getRegistroBusiness()
			    .guardarTiposDocumentales(
			    as_idSolicitud, acctd_td, alid_acto, as_userId, as_codigo, ab_procesoCorrecciones
			);

		Logger.log(lsw_watch, "RegistroBean", "guardarTiposDocumentales", as_userId, null, null, null);
	}

	/** {@inheritdoc} */
	public void guardarTiposDocumentales(
	    Collection<AccCompletitudDocumental> aci_completitudDocumental, Solicitud aso_solicitud,
	    String as_constanteApoderado, String as_constanteTercero, String as_localIp, String as_remoteIp,
	    String as_userId
	)
	    throws B2BException
	{
		StopWatch lsw_watch;

		lsw_watch = Logger.getNewStopWatch();

		getRegistroBusiness()
			    .guardarTiposDocumentales(
			    aci_completitudDocumental, aso_solicitud, as_constanteApoderado, as_constanteTercero, as_localIp,
			    as_remoteIp, as_userId
			);

		Logger.log(lsw_watch, "RegistroBean", "guardarTiposDocumentales", as_userId, as_localIp, as_remoteIp, null);
	}

	/** {@inheritdoc} */
	public void ingresarIntervinientesSolicitudMasivo(
	    Collection<SolicitudInterviniente> acsi_intervinientes, Solicitud as_solicitud, String as_userId,
	    String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch lsw_watch;

		lsw_watch = Logger.getNewStopWatch();

		getRegistroBusiness()
			    .ingresarIntervinientesSolicitudMasivo(acsi_intervinientes, as_solicitud, as_userId, as_remoteIp);

		Logger.log(
		    lsw_watch, "RegistroBean", "actualizarActoParaAntiguoSistema", as_userId, as_localIp, as_remoteIp, null
		);
	}

	/** {@inheritdoc} */
	public void insertarDatosAntSistemaGrabacionMatricula(
	    DatosAntSistema ldas_datosAntSistema, String as_userId, String as_ipLocal, String as_ipRemota
	)
	    throws B2BException
	{
		StopWatch lsw_watch;

		lsw_watch = Logger.getNewStopWatch();

		getRegistroBusiness().insertarDatosAntSistemaGrabacionMatricula(ldas_datosAntSistema, as_userId, as_ipRemota);

		Logger.log(
		    lsw_watch, "RegistroBean", "insertarDatosAntSistemaGrabacionMatricula", as_userId, as_ipLocal, as_ipRemota,
		    null
		);
	}

	/** {@inheritdoc} */
	public Registro insertarImagenesConsultaIndices(
	    Collection<Imagenes> aci_ci, Solicitud as_s, String as_localIp, String as_remoteIp, String as_userId
	)
	    throws B2BException
	{
		StopWatch lsw_watch;
		Registro  lcp_registro;

		lsw_watch     = Logger.getNewStopWatch();

		lcp_registro = getRegistroBusiness()
				               .insertarImagenesConsultaIndices(aci_ci, as_s, as_localIp, as_remoteIp, as_userId);

		Logger.log(
		    lsw_watch, "RegistroBean", "insertarImagenesConsultaIndices", as_userId, as_localIp, as_remoteIp, null
		);

		return lcp_registro;
	}

	/** {@inheritdoc} */
	public void insertarIntervinienteCopia(SolicitudInterviniente acsi_interviniente)
	    throws B2BException
	{
		StopWatch lsw_watch;

		lsw_watch = Logger.getNewStopWatch();

		getRegistroBusiness().insertarIntervinienteCopia(acsi_interviniente);

		Logger.log(lsw_watch, "RegistroBean", "insertarIntervinienteCopia", null, null, null, null);
	}

	/** {@inheritdoc} */
	public void limpiarDatosDelPredio(Solicitud as_solicitud)
	    throws B2BException
	{
		StopWatch lsw_watch;

		lsw_watch = Logger.getNewStopWatch();

		getRegistroBusiness().limpiarDatosDelPredio(as_solicitud);

		Logger.log(lsw_watch, "RegistroBean", "limpiarDatosDelPredio", null, null, null, null);
	}

	/** {@inheritdoc} */
	public long maxConsecutivoConsultaDetalle(
	    CamposConsulta acb_camposConsulta, String as_userId, String as_ipLocal, String as_ipRemota
	)
	    throws B2BException
	{
		StopWatch lsw_watch;
		long      ll_maximo;

		lsw_watch     = Logger.getNewStopWatch();

		ll_maximo = getCriteriosConsultaBusiness().maxConsecutivoConsultaDetalle(acb_camposConsulta);

		Logger.log(
		    lsw_watch, "RegistroBean", "maxConsecutivoConsultaDetalle", as_userId, as_ipLocal, as_ipRemota, null
		);

		return ll_maximo;
	}

	/** {@inheritdoc} */
	public boolean mensajeEmbargoVigente(
	    String as_idActoColumn, String as_idCirculoPrincipal, Long al_idMatriculaPrincipal,
	    boolean ab_anotacionCancelada, String as_userId, String as_localIpAddress, String as_remoteIpAddress
	)
	    throws B2BException
	{
		StopWatch lsw_watch;
		boolean   lb_pertenece;

		lsw_watch        = Logger.getNewStopWatch();
		lb_pertenece     = getRegistroBusiness()
				                   .mensajeEmbargoVigente(
				    as_idActoColumn, as_idCirculoPrincipal, al_idMatriculaPrincipal, ab_anotacionCancelada
				);

		Logger.log(
		    lsw_watch, "RegistroBean", "mensajeEmbargoVigente", as_userId, as_localIpAddress, as_remoteIpAddress, null
		);

		return lb_pertenece;
	}

	/** {@inheritdoc} */
	public void modificarDatosAntiguoSistema(
	    BuscarAntiguoSistema abas_parametros, String as_userId, String as_ipLocal, String as_ipRemota
	)
	    throws B2BException
	{
		StopWatch lsw_watch;

		lsw_watch = Logger.getNewStopWatch();

		getCriteriosConsultaBusiness().modificarDatosAntiguoSistema(abas_parametros, as_userId, as_ipRemota);

		Logger.log(lsw_watch, "RegistroBean", "modificarDatosAntiguoSistema", as_userId, as_ipLocal, as_ipRemota, null);
	}

	/** {@inheritdoc} */
	public boolean mostrarCargueIntervinientes(
	    String as_idNaturalezaJuridica, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch lsw_watch;
		boolean   lb_masDeUnInterviniente;

		lsw_watch     = Logger.getNewStopWatch();

		lb_masDeUnInterviniente = getRegistroBusiness().mostrarCargueIntervinientes(as_idNaturalezaJuridica);

		Logger.log(lsw_watch, "RegistroBean", "mostrarCargueIntervinientes", as_userId, as_localIp, as_remoteIp, null);

		return lb_masDeUnInterviniente;
	}

	/** {@inheritdoc} */
	public Testamento obtenerTestamentoAnterior(Collection<Testamento> as_s)
	    throws B2BException
	{
		StopWatch  lsw_watch;
		Testamento lb_noValido;

		lsw_watch     = Logger.getNewStopWatch();

		lb_noValido = getRegistroBusiness().obtenerTestamentoAnterior(as_s);

		Logger.log(lsw_watch, "RegistroBean", "verificarSiActoNoValidoParaAntSistema", null, null, null, null);

		return lb_noValido;
	}

	/** {@inheritdoc} */
	public boolean perteneceActoCancelacion(String as_idActo, String as_userId, String as_localIp, String as_remoteIp)
	    throws B2BException
	{
		StopWatch lsw_watch;
		boolean   lb_pertenece;

		lsw_watch     = Logger.getNewStopWatch();

		lb_pertenece = getRegistroBusiness().perteneceActoCancelacion(as_idActo);

		Logger.log(lsw_watch, "RegistroBean", "perteneceActoCancelacion", as_userId, as_localIp, as_remoteIp, null);

		return lb_pertenece;
	}

	/** {@inheritdoc} */
	public boolean perteneceActoMedidaCautelar(
	    String as_idActo, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch lsw_watch;
		boolean   lb_pertenece;

		lsw_watch     = Logger.getNewStopWatch();

		lb_pertenece = getRegistroBusiness().perteneceActoMedidaCautelar(as_idActo);

		Logger.log(lsw_watch, "RegistroBean", "perteneceActoMedidaCautelar", as_userId, as_localIp, as_remoteIp, null);

		return lb_pertenece;
	}

	/** {@inheritdoc} */
	public Solicitud recibosImpuesto(Solicitud aos_os, String as_userId, String as_localIp, String as_remoteIp)
	    throws B2BException
	{
		StopWatch lsw_watch;
		Solicitud los_os;

		lsw_watch     = Logger.getNewStopWatch();

		los_os = getRegistroBusiness().recibosImpuesto(aos_os);

		Logger.log(lsw_watch, "RegistroBean", "recibosImpuesto", as_userId, as_localIp, as_remoteIp, null);

		return los_os;
	}

	/** {@inheritdoc} */
	public boolean requiereCancelacionEmbargo(
	    AnotacionPredio aap_ap, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch lsw_watch;
		boolean   lb_pertenece;

		lsw_watch     = Logger.getNewStopWatch();

		lb_pertenece = getRegistroBusiness().requiereCancelacionEmbargo(aap_ap);

		Logger.log(lsw_watch, "RegistroBean", "requiereCancelacionEmbargo", as_userId, as_localIp, as_remoteIp, null);

		return lb_pertenece;
	}

	/** {@inheritdoc} */
	public String revisionDesistimiento(
	    Collection<Turno> act_ct, String as_idProceso, Solicitud as_solicitud, String as_userId, String as_localIp,
	    String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch lsw_watch;
		String    ls_s;

		lsw_watch     = Logger.getNewStopWatch();
		ls_s          = getRegistroBusiness()
				                .revisionDesistimiento(act_ct, as_idProceso, as_userId, as_remoteIp, as_solicitud);

		Logger.log(lsw_watch, "RegistroBean", "revisionDesistimiento", as_userId, as_localIp, as_remoteIp, null);

		return ls_s;
	}

	/** {@inheritdoc} */
	public void salvarCausalesCorreccion(
	    Collection<SolicitudCorreccion> acsc_correcciones, String as_idSolicitud, String as_matricula,
	    boolean ab_segregacion
	)
	    throws B2BException
	{
		StopWatch lsw_watch;

		lsw_watch = Logger.getNewStopWatch();

		getRegistroBusiness().salvarCausalesCorreccion(acsc_correcciones, as_idSolicitud, as_matricula, ab_segregacion);

		Logger.log(lsw_watch, "RegistroBean", "salvarCausalesCorreccion", null, null, null, null);
	}

	/** {@inheritdoc} */
	public boolean salvarDatosCopias(
	    CamposConsulta acc_camposConsulta, Solicitud as_solicitud, String as_userId, String as_localIp,
	    String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch lsw_watch;
		boolean   lb_sinInformacion;

		lsw_watch             = Logger.getNewStopWatch();
		lb_sinInformacion     = getRegistroBusiness()
				                        .salvarDatosCopias(acc_camposConsulta, as_solicitud, as_userId, as_remoteIp);

		Logger.log(lsw_watch, "RegistroBean", "salvarDatosCopias", as_userId, as_localIp, as_remoteIp, null);

		return lb_sinInformacion;
	}

	/** {@inheritdoc} */
	public Documento salvarDocumento(
	    Documento ad_parametros, Solicitud aso_solicitud, String as_userId, String as_ipLocal, String as_ipRemota
	)
	    throws B2BException
	{
		StopWatch lsw_watch;
		Documento ld_documento;

		lsw_watch     = Logger.getNewStopWatch();

		ld_documento = getRegistroBusiness().salvarDocumento(ad_parametros, aso_solicitud, as_userId, as_ipRemota);

		Logger.log(lsw_watch, "RegistroBean", "salvarDocumento", as_userId, as_ipLocal, as_ipRemota, null);

		return ld_documento;
	}

	/** {@inheritdoc} */
	public Registro salvarInteresado(Registro ar_parametros, String as_userId)
	    throws B2BException
	{
		StopWatch lsw_watch;
		Registro  lr_registro;

		lsw_watch     = Logger.getNewStopWatch();

		lr_registro = getRegistroBusiness().salvarInteresado(ar_parametros, as_userId);

		Logger.log(lsw_watch, "RegistroBean", "salvarInteresado", as_userId, null, null, null);

		return lr_registro;
	}

	/** {@inheritdoc} */
	public Registro salvarInterviniente(
	    Registro ar_parametros, String as_userId, boolean ab_siNotificaCorr, String as_localIp, String as_remoteIp,
	    boolean ab_vieneDeRegistro
	)
	    throws B2BException
	{
		StopWatch lsw_watch;
		Registro  lr_registro;

		lsw_watch     = Logger.getNewStopWatch();

		lr_registro = getRegistroBusiness()
				              .salvarInterviniente(
				    ar_parametros, as_userId, ab_siNotificaCorr, as_remoteIp, ab_vieneDeRegistro
				);

		Logger.log(lsw_watch, "RegistroBean", "salvarInterviniente", as_userId, as_localIp, as_remoteIp, null);

		return lr_registro;
	}

	/** {@inheritdoc} */
	public PredioRegistro salvarPredioRegistro(Collection<PredioRegistro> lca_tmp, String string)
	    throws B2BException
	{
		return null;
	}

	/** {@inheritdoc} */
	public void salvarPrediosConTramite(
	    Collection<DatosPredioRegistro> acdpr_predioRegistro, boolean ab_validacionActos700,
	    Collection<GravamenPendiente> acgp_gravamenes, Collection<ProhibicionVPM> acpv_prohibiciones, String as_userId,
	    Solicitud as_solicitud, Map<String, String> amss_actosParciales, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch lsw_watch;

		lsw_watch = Logger.getNewStopWatch();

		getRegistroBusiness()
			    .salvarPrediosConTramite(
			    acdpr_predioRegistro, ab_validacionActos700, acgp_gravamenes, acpv_prohibiciones, as_userId,
			    as_solicitud, amss_actosParciales, as_remoteIp
			);

		Logger.log(lsw_watch, "RegistroBean", "salvarPrediosConTramite", as_userId, as_localIp, as_remoteIp, null);
	}

	/** {@inheritdoc} */
	public DataReproduccionConstancia salvarReproduccionConstancia(
	    DataReproduccionConstancia adrc_parametros, String as_usuario, String as_ipLocal, String as_ipRemota
	)
	    throws B2BException
	{
		StopWatch                  lsw_watch;
		DataReproduccionConstancia ldrc_datos;

		lsw_watch     = Logger.getNewStopWatch();

		ldrc_datos = getRegistroBusiness().salvarReproduccionConstancia(adrc_parametros, as_usuario, as_ipRemota);

		Logger.log(
		    lsw_watch, "RegistroBean", "salvarReproduccionConstancia", as_usuario, as_ipLocal, as_ipRemota, null
		);

		return ldrc_datos;
	}

	/** {@inheritdoc} */
	public void salvarSolicitudMatricula(
	    SolicitudMatricula asr_solicitudMatricula, String as_userId, String as_localIpAddress, String as_remoteIpAddress
	)
	    throws B2BException
	{
		StopWatch lsw_watch;

		lsw_watch = Logger.getNewStopWatch();

		getRegistroBusiness()
			    .salvarSolicitudMatricula(asr_solicitudMatricula, as_userId, as_localIpAddress, as_remoteIpAddress);

		Logger.log(
		    lsw_watch, "RegistroBean", "salvarSolicitudMatricula", as_userId, as_localIpAddress, as_remoteIpAddress,
		    null
		);
	}

	/** {@inheritdoc} */
	public void salvarTiposDocumentalesCorrecciones(
	    Collection<TipoDocumental> acacd_documentalesCorrecciones, Solicitud as_solicitud, String as_userId,
	    String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch lsw_watch;

		lsw_watch = Logger.getNewStopWatch();

		getRegistroBusiness()
			    .salvarTiposDocumentalesCorrecciones(
			    acacd_documentalesCorrecciones, as_solicitud, as_userId, as_remoteIp
			);

		Logger.log(
		    lsw_watch, "RegistroBean", "salvarTiposDocumentalesCorrecciones", as_userId, as_localIp, as_remoteIp, null
		);
	}

	/** {@inheritdoc} */
	public Registro salvarTramite(Registro ar_parametros, String as_userId, String as_ipLocal, String as_ipRemota)
	    throws B2BException
	{
		StopWatch lsw_watch;
		Registro  lr_registro;

		lsw_watch     = Logger.getNewStopWatch();

		lr_registro = getRegistroBusiness().salvarTramite(ar_parametros, as_userId, as_ipLocal);

		Logger.log(lsw_watch, "RegistroBean", "salvarTramite", as_userId, as_ipLocal, as_ipRemota, null);

		return lr_registro;
	}

	/** {@inheritdoc} */
	public String saveActoConstancia(
	    com.bachue.snr.prosnr01.model.registro.Acto aoa_oa, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		String    ls_actoId;
		StopWatch lsw_watch;

		lsw_watch     = Logger.getNewStopWatch();

		ls_actoId = getRegistroBusiness().saveActoConstancia(aoa_oa, as_userId, as_remoteIp);

		Logger.log(lsw_watch, "RegistroBean", "saveActoConstancia", as_userId, as_localIp, as_remoteIp, null);

		return ls_actoId;
	}

	/** {@inheritdoc} */
	public com.bachue.snr.prosnr01.model.registro.Acto saveInfoAll(
	    com.bachue.snr.prosnr01.model.registro.Acto aoa_oa, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		com.bachue.snr.prosnr01.model.registro.Acto la_acto;
		StopWatch                                   lsw_watch;

		lsw_watch     = Logger.getNewStopWatch();
		la_acto       = getRegistroBusiness().saveInfoAll(aoa_oa, as_userId, as_remoteIp);

		Logger.log(lsw_watch, "RegistroBean", "saveInfoAll", as_userId, as_localIp, as_remoteIp, null);

		return la_acto;
	}

	/** {@inheritdoc} */
	public Collection<com.bachue.snr.prosnr01.model.registro.Acto> saveInfoAll(
	    Collection<com.bachue.snr.prosnr01.model.registro.Acto> aca_oa, String as_userId, String as_localIp,
	    String                                                  as_remoteIp, boolean ab_embargos
	)
	    throws B2BException
	{
		Collection<com.bachue.snr.prosnr01.model.registro.Acto> lca_return;
		StopWatch                                               lsw_watch;

		lsw_watch      = Logger.getNewStopWatch();
		lca_return     = getRegistroBusiness().saveInfoAll(aca_oa, as_userId, as_remoteIp, ab_embargos);

		Logger.log(lsw_watch, "RegistroBean", "saveInfoAll", as_userId, as_localIp, as_remoteIp, lca_return);

		return lca_return;
	}

	/** {@inheritdoc} */
	public Registro terminarProceso(
	    Registro ap_parametros, Solicitud as_solicitudCorreccion, String as_userId, boolean ab_siNotificaCorr,
	    boolean as_isConstanciaRep, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch lsw_watch;
		Registro  lcp_registro;

		lsw_watch     = Logger.getNewStopWatch();

		lcp_registro = getRegistroBusiness()
				               .terminarProceso(
				    ap_parametros, as_solicitudCorreccion, as_userId, ab_siNotificaCorr, as_isConstanciaRep, as_remoteIp
				);

		Logger.log(lsw_watch, "RegistroBean", "terminarProceso", as_userId, as_localIp, as_remoteIp, null);

		return lcp_registro;
	}

	/** {@inheritdoc} */
	public CriteriosDeBusqueda terminarProcesoConsultas(
	    CriteriosDeBusqueda acdb_criteriosDeBusqueda, String as_userId, String as_ipLocal, String as_ipRemota
	)
	    throws B2BException
	{
		StopWatch lsw_watch;

		lsw_watch     = Logger.getNewStopWatch();

		acdb_criteriosDeBusqueda = getCriteriosConsultaBusiness()
				                           .terminarProcesoConsultas(acdb_criteriosDeBusqueda, as_userId, as_ipRemota);

		Logger.log(lsw_watch, "RegistroBean", "guardarCriteriosCampos", as_userId, as_ipLocal, as_ipRemota, null);

		return acdb_criteriosDeBusqueda;
	}

	/** {@inheritdoc} */
	public void terminarProcesoCopiasAntSistema(
	    BuscarAntiguoSistema abas_parametros, String as_userId, String as_ipLocal, String as_ipRemota
	)
	    throws B2BException
	{
		StopWatch lsw_watch;

		lsw_watch = Logger.getNewStopWatch();

		getCriteriosConsultaBusiness().terminarProcesoCopiasAntSistema(abas_parametros, as_userId, as_ipRemota);

		Logger.log(
		    lsw_watch, "RegistroBean", "terminarProcesoCopiasAntSistema", as_userId, as_ipLocal, as_ipRemota, null
		);
	}

	/** {@inheritdoc} */
	public Registro terminarProcesoGrabacionMatriculas(
	    Registro ar_registro, boolean ab_notificarCorrespondencia, String as_userId, String as_localIp,
	    String as_remoteIp
	)
	    throws B2BException, IOException
	{
		StopWatch lsw_watch;
		Registro  lcp_registro;

		lsw_watch     = Logger.getNewStopWatch();

		lcp_registro = getRegistroBusiness()
				               .terminarProcesoGrabacionMatriculas(
				    ar_registro, ab_notificarCorrespondencia, as_userId, as_remoteIp
				);

		Logger.log(
		    lsw_watch, "RegistroBean", "terminarProcesoGrabacionMatriculas", as_userId, as_localIp, as_remoteIp, null
		);

		return lcp_registro;
	}

	/** {@inheritdoc} */
	public Registro terminarProcesoRecepcion(
	    Collection<AccCompletitudDocumental> acacd_acd, Solicitud as_s, String as_localIp, String as_remoteIp,
	    String as_userId
	)
	    throws B2BException
	{
		StopWatch lsw_watch;
		Registro  lcp_registro;

		lsw_watch     = Logger.getNewStopWatch();

		lcp_registro = getRegistroBusiness()
				               .terminarProcesoRecepcion(acacd_acd, as_s, as_localIp, as_remoteIp, as_userId);

		Logger.log(lsw_watch, "RegistroBean", "terminarProcesoRecepcion", as_userId, as_localIp, as_remoteIp, null);

		return lcp_registro;
	}

	/** {@inheritdoc} */
	public boolean tieneSegregacion(Acto la_acto)
	    throws B2BException
	{
		StopWatch lsw_watch;
		boolean   la_return;

		lsw_watch     = Logger.getNewStopWatch();

		la_return = getRegistroBusiness().tieneSegregacion(la_acto);

		Logger.log(lsw_watch, "RegistroBean", "tieneSegregacion", null, null, null, null);

		return la_return;
	}

	/** {@inheritdoc} */
	public void updateActoHijo(
	    com.bachue.snr.prosnr01.model.registro.Acto aa_acto, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch lsw_watch;

		lsw_watch = Logger.getNewStopWatch();

		getRegistroBusiness().updateActoHijo(aa_acto, as_userId, as_remoteIp);

		Logger.log(lsw_watch, "RegistroBean", "updateActoHijo", as_userId, as_localIp, as_remoteIp, null);
	}

	/** {@inheritdoc} */
	public void updateCirculoActosHijos(Acto aa_actoUdate, String as_userId, String as_remoteIp)
	    throws B2BException
	{
		StopWatch lsw_watch;

		lsw_watch = Logger.getNewStopWatch();

		getRegistroBusiness().updateCirculoActosHijos(aa_actoUdate, as_userId, as_remoteIp);

		Logger.log(lsw_watch, "RegistroBean", "updateCirculoActosHijos", as_userId, null, as_remoteIp, null);
	}

	/** {@inheritdoc} */
	public void updateSolicitud(Solicitud ap_parametros, String as_userId)
	    throws B2BException
	{
		StopWatch lsw_watch;

		lsw_watch = Logger.getNewStopWatch();

		getRegistroBusiness().updateSolicitud(ap_parametros, as_userId);

		Logger.log(lsw_watch, "RegistroBean", "updateSolicitud", as_userId, null, null, null);
	}

	/** {@inheritdoc} */
	public Testamento updateTestamento(
	    Testamento at_testamento, String as_userId, String as_ipLocal, String as_ipRemota
	)
	    throws B2BException
	{
		StopWatch  lsw_watch;
		Testamento lt_testamento;
		lsw_watch     = Logger.getNewStopWatch();

		lt_testamento = getRegistroBusiness().updateTestamento(at_testamento, as_userId, as_ipRemota);

		Logger.log(lsw_watch, "RegistroBean", "salvarDocumento", as_userId, as_ipLocal, as_ipRemota, null);

		return lt_testamento;
	}

	/** {@inheritdoc} */
	public boolean validaModificacionDatosAntiguoSistema(
	    BuscarAntiguoSistema abas_parametros, String as_userId, String as_ipLocal, String as_ipRemota
	)
	    throws B2BException
	{
		StopWatch lsw_watch;
		boolean   lb_modificado;

		lsw_watch     = Logger.getNewStopWatch();

		lb_modificado = getCriteriosConsultaBusiness().validaModificacionDatosAntiguoSistema(abas_parametros);

		Logger.log(
		    lsw_watch, "RegistroBean", "validaModificacionDatosAntiguoSistema", as_userId, as_ipLocal, as_ipRemota, null
		);

		return lb_modificado;
	}

	/** {@inheritdoc} */
	public boolean validacionActo0315(Solicitud as_s, String as_userId, String as_localIp, String remoteIp)
	    throws B2BException
	{
		StopWatch lsw_watch;
		boolean   lb_b;

		lsw_watch     = Logger.getNewStopWatch();

		lb_b = getRegistroBusiness().validacionActo0315(as_s, as_userId);

		Logger.log(lsw_watch, "RegistroBean", "validacionActo0315", as_userId, as_localIp, remoteIp, null);

		return lb_b;
	}

	/** {@inheritdoc} */
	public boolean validacionActosContraConstante(String as_idSolicitud, String as_constante, String as_userId)
	    throws B2BException
	{
		StopWatch lsw_watch;
		boolean   lb_masDeUnInterviniente;

		lsw_watch     = Logger.getNewStopWatch();

		lb_masDeUnInterviniente = getRegistroBusiness().validacionActosContraConstante(as_idSolicitud, as_constante);

		Logger.log(lsw_watch, "RegistroBean", "validacionActosContraConstante", as_userId, null, null, null);

		return lb_masDeUnInterviniente;
	}

	/** {@inheritdoc} */
	public boolean validacionActosContraConstante(
	    String as_idSolicitud, String as_idAnotacionPredio, String as_constante, String as_userId
	)
	    throws B2BException
	{
		StopWatch lsw_watch;
		boolean   lb_masDeUnInterviniente;

		lsw_watch     = Logger.getNewStopWatch();

		lb_masDeUnInterviniente = getRegistroBusiness()
				                          .validacionActosContraConstante(
				    as_idSolicitud, as_idAnotacionPredio, as_constante
				);

		Logger.log(lsw_watch, "RegistroBean", "validacionActosContraConstante", as_userId, null, null, null);

		return lb_masDeUnInterviniente;
	}

	/** {@inheritdoc} */
	public boolean validarActoEjecutoria(String as_idTipoDoc)
	    throws B2BException
	{
		StopWatch lsw_watch;
		boolean   lb_boolean;

		lsw_watch     = Logger.getNewStopWatch();

		lb_boolean = getRegistroBusiness().validarActoDocumentoEjecutoria(as_idTipoDoc);

		Logger.log(lsw_watch, "RegistroBean", "validarActoEjecutoria", null, null, null, null);

		return lb_boolean;
	}

	/** {@inheritdoc} */
	public boolean validarActoGrupoCancelacion(String as_idSolicitud, String as_userId)
	    throws B2BException
	{
		StopWatch lsw_watch;
		boolean   lb_masDeUnInterviniente;

		lsw_watch     = Logger.getNewStopWatch();

		lb_masDeUnInterviniente = getRegistroBusiness().validarActoGrupoCancelacion(as_idSolicitud);

		Logger.log(lsw_watch, "RegistroBean", "validarActoGrupoCancelacion", as_userId, null, null, null);

		return lb_masDeUnInterviniente;
	}

	/** {@inheritdoc} */
	public boolean validarActoNatJuridicaActoMigrado(
	    String as_idTipoActo, String as_cuantia, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch lsw_watch;
		boolean   lb_return;

		lsw_watch     = Logger.getNewStopWatch();

		lb_return = getRegistroBusiness().validarActoNatJuridicaActoMigrado(as_idTipoActo, as_cuantia);

		Logger.log(
		    lsw_watch, "RegistroBean", "validarActoNatJuridicaActoMigrado", as_userId, as_localIp, as_remoteIp, null
		);

		return lb_return;
	}

	/**
	 * Método que recibe una colección de actos y los valida contra la constante
	 * CODIGOS_CANCELACION_PROVIDENCIA.
	 *
	 * @param as_actos de as actos
	 * @param ll_idMatricula            Long de matrícula a consultar
	 * @param as_idCirculo            String de circulo a consultar
	 * @param as_userId de as user id
	 * @param as_localIp de as local ip
	 * @param as_remoteIp de as remote ip
	 * @return String con matricula de acto ingresada si se encontro en constante
	 * @throws B2BException de b 2 B exception
	 */
	public String validarActosCancelacionProvidencia(
	    String as_actos, Long ll_idMatricula, String as_idCirculo, String as_userId, String as_localIp,
	    String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch lsw_watch;
		String    ls_matriculaCancelacionProvidencia;

		lsw_watch     = Logger.getNewStopWatch();

		ls_matriculaCancelacionProvidencia = getRegistroBusiness()
				                                     .validarActosCancelacionProvidencia(
				    as_actos, ll_idMatricula, as_idCirculo
				);

		Logger.log(
		    lsw_watch, "RegistroBean", "validarActosCancelacionProvidencia", as_userId, as_localIp, as_remoteIp, null
		);

		return ls_matriculaCancelacionProvidencia;
	}

	/** {@inheritdoc} */
	public boolean validarActosDeMedidaCautelar(
	    String as_idTurno, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch lsw_watch;
		boolean   lb_pertenece;

		lsw_watch     = Logger.getNewStopWatch();

		lb_pertenece = getRegistroBusiness().validarActosDeMedidaCautelar(as_idTurno);

		Logger.log(lsw_watch, "RegistroBean", "validarActosDeMedidaCautelar", as_userId, as_localIp, as_remoteIp, null);

		return lb_pertenece;
	}

	/** {@inheritdoc} */
	public boolean validarActosMayorValor(String as_idSolicitud)
	    throws B2BException
	{
		StopWatch lsw_watch;
		boolean   lb_return;

		lsw_watch     = Logger.getNewStopWatch();
		lb_return     = getRegistroBusiness().validarActosMayorValor(as_idSolicitud);

		Logger.log(lsw_watch, "RegistroBean", "validarActosMayorValor", null, null, null, null);

		return lb_return;
	}

	/** {@inheritdoc} */
	public void validarActosParcial(Solicitud as_solicitud)
	    throws B2BException
	{
		StopWatch lsw_watch;

		lsw_watch = Logger.getNewStopWatch();

		getRegistroBusiness().validarActosParcial(as_solicitud);

		Logger.log(lsw_watch, "RegistroBean", "validarActosParcial", null, null, null, null);
	}

	/** {@inheritdoc} */
	public boolean validarActosVencimientoDiasHabiles(String as_idTipoDoc)
	    throws B2BException
	{
		StopWatch lsw_watch;
		boolean   lb_boolean;

		lsw_watch     = Logger.getNewStopWatch();

		lb_boolean = getRegistroBusiness().validarActosVencimientoDiasHabiles(as_idTipoDoc);

		Logger.log(lsw_watch, "RegistroBean", "validarActosVencimientoDiasHabiles", null, null, null, null);

		return lb_boolean;
	}

	/** {@inheritdoc} */
	public boolean validarAnexos(com.bachue.snr.prosnr01.model.registro.Acto aca_actos)
	    throws B2BException
	{
		return getRegistroBusiness().validarAnexos(aca_actos);
	}

	/** {@inheritdoc} */
	public boolean validarAnotacionLoteo(AnotacionPredio aap_anotacionPredio)
	    throws B2BException
	{
		StopWatch lsw_watch;
		boolean   lb_return;

		lsw_watch     = Logger.getNewStopWatch();
		lb_return     = getRegistroBusiness().validarAnotacionLoteo(aap_anotacionPredio);

		Logger.log(lsw_watch, "RegistroBean", "validarAnotacionLoteo", null, null, null, null);

		return lb_return;
	}

	/** {@inheritdoc} */
	public void validarCantidadDetalleCriterioBusqueda(
	    CriteriosDeBusqueda acdb_criteriosDeBusqueda, String as_userId, String as_ipLocal, String as_ipRemota
	)
	    throws B2BException
	{
		StopWatch lsw_watch;

		lsw_watch = Logger.getNewStopWatch();

		getCriteriosConsultaBusiness().validarCantidadDetalleCriterioBusqueda(acdb_criteriosDeBusqueda);

		Logger.log(
		    lsw_watch, "RegistroBean", "validarCantidadDetalleCriterioBusqueda", as_userId, as_ipLocal, as_ipRemota,
		    null
		);
	}

	/** {@inheritdoc} */
	public boolean validarCertificadoObligatorio(
	    String ata_parametros, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch lsw_watch;
		boolean   lta_return;

		lsw_watch     = Logger.getNewStopWatch();

		lta_return = getRegistroBusiness().validarCertificadoObligatorio(ata_parametros);

		Logger.log(
		    lsw_watch, "RegistroBean", "validarCertificadoObligatorio", as_userId, as_localIp, as_remoteIp, null
		);

		return lta_return;
	}

	/** {@inheritdoc} */
	public boolean validarCirculoSNRUsuario(String as_userId, String as_localIp, String as_remoteIp)
	    throws B2BException
	{
		StopWatch lsw_watch;
		boolean   lb_noValido;

		lsw_watch     = Logger.getNewStopWatch();

		lb_noValido = getRegistroBusiness().validarCirculoSNRUsuario(as_userId);

		Logger.log(lsw_watch, "RegistroBean", "validarCirculoSNRUsuario", as_userId, as_localIp, as_remoteIp, null);

		return lb_noValido;
	}

	/** {@inheritdoc} */
	public boolean validarCriterioPorTipoCriterio(
	    Long al_idTurnoHistoria, String idTipoCriterioBusqueda, String as_userId, String as_remoteIp, String as_localIp
	)
	    throws B2BException
	{
		StopWatch lsw_watch;
		boolean   lb_criterioExiste;

		lsw_watch     = Logger.getNewStopWatch();

		lb_criterioExiste = getCriteriosConsultaBusiness()
				                    .validarCriterioPorTipoCriterio(al_idTurnoHistoria, idTipoCriterioBusqueda);

		Logger.log(
		    lsw_watch, "RegistroBean", "validarCriterioPorTipoCriterio", as_userId, as_localIp, as_remoteIp, null
		);

		return lb_criterioExiste;
	}

	/** {@inheritdoc} */
	public Collection<SolicitudInterviniente> validarExcelIntervinientesMasivos(
	    byte[] aba_archivo, String as_solicitud, String as_nombreFile, String as_userId, String as_localIp,
	    String as_remoteIp
	)
	    throws B2BException, IOException
	{
		{
			StopWatch                          lsw_watch;
			Collection<SolicitudInterviniente> lci_cintervinientesProcesados;

			lsw_watch     = Logger.getNewStopWatch();

			lci_cintervinientesProcesados = getRegistroBusiness()
					                                .validarExcelIntervinientesMasivos(
					    aba_archivo, as_solicitud, as_nombreFile, as_userId, as_localIp
					);

			Logger.log(
			    lsw_watch, "RegistroBean", "validarExcelIntervinientesMasivos", as_userId, as_localIp, as_remoteIp, null
			);

			return lci_cintervinientesProcesados;
		}
	}

	/** {@inheritdoc} */
	public ValidacionDocumento validarExistenciaDocumento(Documento ad_parametrodoc)
	    throws B2BException
	{
		StopWatch           lsw_watch;
		ValidacionDocumento lb_existe;

		lsw_watch     = Logger.getNewStopWatch();

		lb_existe = getRegistroBusiness().validarExistenciaDocumento(ad_parametrodoc);

		Logger.log(lsw_watch, "RegistroBean", "validarExistenciaDocumento", null, null, null, null);

		return lb_existe;
	}

	/** {@inheritdoc} */
	public ValidacionDocumento validarExistenciaDocumentoConstancia(DocumentoConstancia ap_parametrodoc)
	    throws B2BException
	{
		StopWatch           lsw_watch;
		ValidacionDocumento lb_existe;

		lsw_watch     = Logger.getNewStopWatch();

		lb_existe = getRegistroBusiness().validarExistenciaDocumentoConstancia(ap_parametrodoc);

		Logger.log(lsw_watch, "RegistroBean", "validarExistenciaDocumentoConstancia", null, null, null, null);

		return lb_existe;
	}

	/** {@inheritdoc} */
	public Documento validarExistenciaDocumentoEjecutoria(Documento ad_parametrodoc)
	    throws B2BException
	{
		StopWatch lsw_watch;
		Documento ld_existe;

		lsw_watch     = Logger.getNewStopWatch();

		ld_existe = getRegistroBusiness().validarExistenciaDocumentoEjecutoria(ad_parametrodoc);

		Logger.log(lsw_watch, "RegistroBean", "validarExistenciaDocumentoEjecutoria", null, null, null, null);

		return ld_existe;
	}

	/** {@inheritdoc} */
	public boolean validarInvocacionCapture(
	    Solicitud as_idSolicitud, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch lsw_watch;
		boolean   lb_return;

		lsw_watch     = Logger.getNewStopWatch();
		lb_return     = getRegistroBusiness().validarInvocacionCapture(as_idSolicitud);

		Logger.log(lsw_watch, "RegistroBean", "validarInvocacionCapture", as_userId, as_localIp, as_remoteIp, null);

		return lb_return;
	}

	/** {@inheritdoc} */
	public boolean validarNotificarCorrespondencia(
	    Solicitud as_parametros, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch lsw_watch;
		boolean   lb_return;

		lsw_watch     = Logger.getNewStopWatch();

		lb_return = getRegistroBusiness().validarNotificarCorrespondencia(as_parametros);

		Logger.log(
		    lsw_watch, "RegistroBean", "validarNotificarCorrespondencia", as_userId, as_localIp, as_remoteIp, null
		);

		return lb_return;
	}

	/** {@inheritdoc} */
	public NuevaEntrada validarNuevaEntrada(NuevaEntrada ane_nuevaEntrada)
	    throws B2BException
	{
		StopWatch    lsw_watch;
		NuevaEntrada lne_return;

		lsw_watch     = Logger.getNewStopWatch();

		lne_return = getRegistroBusiness().validarNuevaEntrada(ane_nuevaEntrada);

		Logger.log(lsw_watch, "RegistroBean", "validarNuevaEntrada", null, null, null, null);

		return lne_return;
	}

	/**
	 * Método que valida los actos de grabación para validar roles de intervinientes
	 *
	 * @param acgp_gravamenesPendientes de
	 * @param as_userId de as user id
	 * @return booleana de validación
	 * @throws B2BException de b 2 B exception
	 */
	public boolean validarRolGravamenesPendientes(
	    Collection<GravamenPendiente> acgp_gravamenesPendientes, String as_userId
	)
	    throws B2BException
	{
		StopWatch lsw_watch;
		boolean   lb_masDeUnInterviniente;

		lsw_watch     = Logger.getNewStopWatch();

		lb_masDeUnInterviniente = getRegistroBusiness().validarRolGravamenesPendientes(acgp_gravamenesPendientes);

		Logger.log(lsw_watch, "RegistroBean", "validarRolGravamenesPendientes", as_userId, null, null, null);

		return lb_masDeUnInterviniente;
	}

	/**
	 * Método que valida la segunda recepción de documentops
	 *
	 * @param Collection<Turno> act_ct
	 * @param as_idProceso de as_idProceso
	 * @param Solicitud de as_solicitud
	 * @param String de as_userId
	 * @param String de as_localIp
	 * @param String de as_remoteIp
	 * @return String validarSegundaRecepcionDocumentos
	 * @throws B2BException de b 2 B exception
	 */
	public String validarSegundaRecepcionDocumentos(
	    Collection<Turno> act_ct, String as_idProceso, Solicitud as_solicitud, String as_userId, String as_localIp,
	    String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch lsw_watch;
		String    ls_s;

		lsw_watch     = Logger.getNewStopWatch();
		ls_s          = getRegistroBusiness()
				                .validarSegundaRecepcionDocumentos(
				    act_ct, as_idProceso, as_userId, as_remoteIp, as_solicitud
				);

		Logger.log(
		    lsw_watch, "RegistroBean", "validarSegundaRecepcionDocumentos", as_userId, as_localIp, as_remoteIp, null
		);

		return ls_s;
	}

	/* (non-Javadoc)
	 * @see com.bachue.snr.prosnr01.ejb.session.stateless.registro.RegistroRemote#validarSubProceso(java.lang.String)
	 */
	public boolean validarSubProceso(String idSolicitud)
	    throws B2BException
	{
		StopWatch lsw_watch;
		boolean   lb_return;

		lsw_watch     = Logger.getNewStopWatch();

		lb_return = getRegistroBusiness().validarSubProceso(idSolicitud);

		Logger.log(lsw_watch, "RegistroBean", "validarSubProceso", null, null, null, null);

		return lb_return;
	}

	/** {@inheritdoc} */
	public boolean validarSumaAreas(Collection<String> acs_matriculas, Double ad_area, boolean ab_sumatoria)
	    throws B2BException
	{
		StopWatch lsw_watch;
		boolean   lb_return;

		lsw_watch     = Logger.getNewStopWatch();
		lb_return     = getRegistroBusiness().validarSumaAreas(acs_matriculas, ad_area, ab_sumatoria);

		Logger.log(lsw_watch, "RegistroBean", "validarSumaAreas", null, null, null, null);

		return lb_return;
	}

	/** {@inheritdoc} */
	public boolean validarTipoActoBaldio(String as_tipoActo)
	    throws B2BException
	{
		StopWatch lsw_watch;
		boolean   lb_boolean;

		lsw_watch     = Logger.getNewStopWatch();

		lb_boolean = getRegistroBusiness().validarTipoActoBaldio(as_tipoActo);

		Logger.log(lsw_watch, "RegistroBean", "validarTipoActoBaldio", null, null, null, null);

		return lb_boolean;
	}

	/** {@inheritdoc} */
	public boolean validarTurnoCertificado(String as_idTurno, String as_idSolicitud, boolean ab_accion)
	    throws B2BException
	{
		boolean   lb_return;
		StopWatch lsw_watch;

		lsw_watch     = Logger.getNewStopWatch();

		lb_return = getRegistroBusiness().validarTurnoCertificado(as_idTurno, as_idSolicitud, ab_accion);

		Logger.log(lsw_watch, "RegistroBean", "validarTurnoCertificado", null, null, null, null);

		return lb_return;
	}

	/** {@inheritdoc} */
	public NuevaEntrada validarVerificarTurno(NuevaEntrada ane_nuevaEntrada)
	    throws B2BException
	{
		StopWatch    lsw_watch;
		NuevaEntrada lne_return;

		lsw_watch     = Logger.getNewStopWatch();

		lne_return = getRegistroBusiness().validarVerificarTurno(ane_nuevaEntrada);

		Logger.log(lsw_watch, "RegistroBean", "validarVerificarTurno", null, null, null, null);

		return lne_return;
	}

	/** {@inheritdoc} */
	public boolean verificarSiActoNoValidoParaAntSistema(
	    Collection<String> acs_idActo, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch lsw_watch;
		boolean   lb_noValido;

		lsw_watch     = Logger.getNewStopWatch();

		lb_noValido = getRegistroBusiness().verificarSiActoNoValidoParaAntSistema(acs_idActo);

		Logger.log(
		    lsw_watch, "RegistroBean", "verificarSiActoNoValidoParaAntSistema", as_userId, as_localIp, as_remoteIp, null
		);

		return lb_noValido;
	}

	/**
	 * Retorna el valor de criterios consulta business.
	 *
	 * @return el valor de criterios consulta business
	 */
	private CriteriosConsultaBusiness getCriteriosConsultaBusiness()
	{
		if(iccb_business == null)
			iccb_business = new CriteriosConsultaBusiness();

		return iccb_business;
	}

	/**
	 * Retorna el valor de registro business.
	 *
	 * @return el valor de registro business
	 */
	private RegistroBusiness getRegistroBusiness()
	{
		if(irb_business == null)
			irb_business = new RegistroBusiness();

		return irb_business;
	}
}
