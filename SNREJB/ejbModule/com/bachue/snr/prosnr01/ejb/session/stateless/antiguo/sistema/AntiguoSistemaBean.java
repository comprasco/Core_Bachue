package com.bachue.snr.prosnr01.ejb.session.stateless.antiguo.sistema;

import com.b2bsg.common.exception.B2BException;

import com.bachue.snr.prosnr01.business.antiguo.sistema.AntiguoSistemaBusiness;

import com.bachue.snr.prosnr01.ejb.session.stateless.Logger;

import com.bachue.snr.prosnr01.model.antiguoSistema.AmpliacionHistoriaRegistral;
import com.bachue.snr.prosnr01.model.antiguoSistema.Anotacion;
import com.bachue.snr.prosnr01.model.antiguoSistema.AntiguoSistemaData;
import com.bachue.snr.prosnr01.model.antiguoSistema.AreaPredio;
import com.bachue.snr.prosnr01.model.antiguoSistema.Complementacion;
import com.bachue.snr.prosnr01.model.antiguoSistema.ConsultaDatosDocumento;
import com.bachue.snr.prosnr01.model.antiguoSistema.DataConsultaAntSistema;
import com.bachue.snr.prosnr01.model.antiguoSistema.DataConsultaDatosDocumento;
import com.bachue.snr.prosnr01.model.antiguoSistema.DataConsultaPorCriterio;
import com.bachue.snr.prosnr01.model.antiguoSistema.DatosBasicos;
import com.bachue.snr.prosnr01.model.antiguoSistema.DireccionDelPredio;
import com.bachue.snr.prosnr01.model.antiguoSistema.DocumentoData;
import com.bachue.snr.prosnr01.model.calificacion.TramiteCantidad;
import com.bachue.snr.prosnr01.model.sdb.acc.AccComplementacionPredio;
import com.bachue.snr.prosnr01.model.sdb.acc.AccLinderoPredio;
import com.bachue.snr.prosnr01.model.sdb.acc.AccPredioRegistro;
import com.bachue.snr.prosnr01.model.sdb.acc.AnotacionPredio;
import com.bachue.snr.prosnr01.model.sdb.acc.AnotacionPredioCiudadano;
import com.bachue.snr.prosnr01.model.sdb.acc.CompletitudComplementacion;
import com.bachue.snr.prosnr01.model.sdb.acc.DatosAntSistema;
import com.bachue.snr.prosnr01.model.sdb.acc.DetalleAntSistema;
import com.bachue.snr.prosnr01.model.sdb.acc.DetalleAntSistemaUI;
import com.bachue.snr.prosnr01.model.sdb.acc.DireccionPredioAcc;
import com.bachue.snr.prosnr01.model.sdb.acc.DocumentosSalida;
import com.bachue.snr.prosnr01.model.sdb.acc.OficiosTexto;
import com.bachue.snr.prosnr01.model.sdb.acc.PredioSegregado;
import com.bachue.snr.prosnr01.model.sdb.acc.SolicitudMatricula;
import com.bachue.snr.prosnr01.model.sdb.acc.SolicitudMatriculaActo;
import com.bachue.snr.prosnr01.model.sdb.acc.TipoCausal;
import com.bachue.snr.prosnr01.model.sdb.acc.Turno;
import com.bachue.snr.prosnr01.model.sdb.acc.TurnoHistoria;
import com.bachue.snr.prosnr01.model.sdb.bng.ComplementacionPredio;
import com.bachue.snr.prosnr01.model.sdb.bng.Documento;
import com.bachue.snr.prosnr01.model.sdb.pgn.CirculoRegistral;
import com.bachue.snr.prosnr01.model.sdb.pgn.TipoActo;
import com.bachue.snr.prosnr01.model.sdb.pgn.ZonaRegistral;
import com.bachue.snr.prosnr01.model.sdb.png.NaturalezaJuridica;

import org.perf4j.StopWatch;

import java.util.Collection;
import java.util.List;
import java.util.Map;


/**
 * Clase que contiene todos las propiedades AntiguoSistemaBean.
 *
 * @author Julian Vaca
 */
@javax.ejb.Stateless(name = "AntiguoSistema", mappedName = "antiguoSistemaMappedName")
@javax.ejb.TransactionManagement(javax.ejb.TransactionManagementType.BEAN)
public class AntiguoSistemaBean implements AntiguoSistemaRemote
{
	/** Propiedad iasb business. */
	private AntiguoSistemaBusiness iasb_business;

	/* (non-Javadoc)
	 * @see com.bachue.snr.prosnr01.ejb.session.stateless.antiguo.sistema.AntiguoSistemaRemote#actualizarCompletitudComplementacion(com.bachue.snr.prosnr01.model.sdb.acc.CompletitudComplementacion, java.lang.String, java.lang.String, java.lang.String)
	 */
	public void actualizarCompletitudComplementacion(
	    CompletitudComplementacion cc_parametros, boolean ab_definitivo102, String as_usuario, String as_ipLocal,
	    String as_ipRemota
	)
	    throws B2BException
	{
		StopWatch lsw_watch;

		lsw_watch = Logger.getNewStopWatch();

		getBusiness().actualizarCompletitudComplementacion(cc_parametros, ab_definitivo102, as_usuario, as_ipRemota);

		Logger.log(
		    lsw_watch, "AntiguoSistemaBusiness", "actualizarCompletitudComplementacion", as_usuario, as_ipLocal,
		    as_ipRemota, null
		);
	}

	/**
	 * Actualizar direccion predio.
	 *
	 * @param adb_db de adb db
	 * @param as_userId de as user id
	 * @param as_localIp de as local ip
	 * @param as_remoteIp de as remote ip
	 * @return el valor de direccion del predio
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public DireccionDelPredio actualizarDireccionPredio(
	    DireccionDelPredio adb_db, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		DireccionDelPredio lddp_return;
		StopWatch          lsw_watch;

		lsw_watch     = Logger.getNewStopWatch();

		lddp_return = getBusiness().actualizarDireccionPredio(adb_db, as_userId, as_remoteIp);

		Logger.log(
		    lsw_watch, "AntiguoSistemaBusiness", "actualizarDireccionPredio", as_userId, as_localIp, as_remoteIp, null
		);

		return lddp_return;
	}

	/**
	 * Ant sistema salvar actualizar 101.
	 *
	 * @param adas_datosAntSistema de adas datos ant sistema
	 * @param as_idTurnoHistoria de as id turno historia
	 * @param as_observaciones de as observaciones
	 * @param as_userId de as user id
	 * @param as_localIp de as local ip
	 * @param as_remoteIp de as remote ip
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void antSistemaSalvarActualizar101(
	    DatosAntSistema adas_datosAntSistema, String as_idTurnoHistoria, String as_observaciones, String as_userId,
	    String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch lsw_watch;

		lsw_watch = Logger.getNewStopWatch();

		getBusiness()
			    .antSistemaSalvarActualizar101(
			    adas_datosAntSistema, as_idTurnoHistoria, as_observaciones, as_userId, as_remoteIp
			);

		Logger.log(
		    lsw_watch, "AntiguoSistemaBusiness", "antSistemaSalvarActualizar101", as_userId, as_localIp, as_remoteIp,
		    null
		);
	}

	/**
	 * Ant sistema salvar asociar matricula.
	 *
	 * @param lcsma_datosBandejaActosInsertar de lcsma datos bandeja actos insertar
	 * @param lcsm_datosBandejaPrediosInsertar de lcsm datos bandeja predios insertar
	 * @param as_userId de as user id
	 * @param as_localIpAddress de as local ip address
	 * @param as_ipRemote de as ip remote
	 * @param as_idTurnoHistoria de as id turno historia
	 * @param as_observaciones de as observaciones
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void antSistemaSalvarAsociarMatricula(
	    Collection<SolicitudMatriculaActo> lcsma_datosBandejaActosInsertar,
	    Collection<SolicitudMatricula> lcsm_datosBandejaPrediosInsertar, String as_userId, String as_localIpAddress,
	    String as_ipRemote, String as_idTurnoHistoria, String as_observaciones
	)
	    throws B2BException
	{
		StopWatch lsw_watch;

		lsw_watch = Logger.getNewStopWatch();

		getBusiness()
			    .antSistemaSalvarAsociarMatricula(
			    lcsma_datosBandejaActosInsertar, lcsm_datosBandejaPrediosInsertar, as_userId, as_ipRemote,
			    as_idTurnoHistoria, as_observaciones
			);

		Logger.log(
		    lsw_watch, "AntiguoSistemaBusiness", "antSistemaSalvarAsociarMatricula", as_userId, as_localIpAddress,
		    as_ipRemote, null
		);
	}

	/**
	 * Ant sistema salvar asociar matricula individual.
	 *
	 * @param acdcpc_data de acdcpc data
	 * @param as_userId de as user id
	 * @param as_ip de as ip
	 * @param as_turnoHistoria de as turno historia
	 * @param as_observaciones de as observaciones
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void antSistemaSalvarAsociarMatriculaIndividual(
	    Collection<DataConsultaPorCriterio> acdcpc_data, String as_userId, String as_ip, String as_turnoHistoria,
	    String as_observaciones
	)
	    throws B2BException
	{
		StopWatch lsw_watch;

		lsw_watch = Logger.getNewStopWatch();

		getBusiness()
			    .antSistemaSalvarAsociarMatriculaIndividual(
			    acdcpc_data, as_userId, as_ip, as_turnoHistoria, as_observaciones
			);

		Logger.log(
		    lsw_watch, "AntiguoSistemaBusiness", "antSistemaSalvarAsociarMatriculaIndividual", null, null, null, null
		);
	}

	/**
	 * Ant sistema salvar asociar matricula individual 101.
	 *
	 * @param amsb_archivos de amsb archivos
	 * @param ad_documento de ad documento
	 * @param acdcpc_data de acdcpc data
	 * @param as_userId de as user id
	 * @param as_localIpAddress de as local ip address
	 * @param as_ipRemote de as ip remote
	 * @param as_observaciones de as observaciones
	 * @param as_idTurnoHistoria de as id turno historia
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void antSistemaSalvarAsociarMatriculaIndividual101(
	    Map<String, byte[]> amsb_archivos, DocumentosSalida ad_documento,
	    Collection<DataConsultaPorCriterio> acdcpc_data, String as_userId, String as_localIpAddress, String as_ipRemote,
	    String as_observaciones, String as_idTurnoHistoria
	)
	    throws B2BException
	{
		StopWatch lsw_watch;

		lsw_watch = Logger.getNewStopWatch();

		getBusiness()
			    .antSistemaSalvarAsociarMatriculaIndividual101(
			    amsb_archivos, ad_documento, acdcpc_data, as_userId, as_localIpAddress, as_ipRemote, as_observaciones,
			    as_idTurnoHistoria
			);

		Logger.log(
		    lsw_watch, "AntiguoSistemaBusiness", "antSistemaSalvarAsociarMatriculaIndividual101", as_userId,
		    as_localIpAddress, as_ipRemote, null
		);
	}

	/**
	 * Ant sistema salvar crear.
	 *
	 * @param lcapr_capr de lcapr capr
	 * @param as_userId de as user id
	 * @param as_localIpAddress de as local ip address
	 * @param as_ipRemote de as ip remote
	 * @param as_idTurnoHistoria de as id turno historia
	 * @param as_observaciones de as observaciones
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void antSistemaSalvarCrear(
	    Collection<AccPredioRegistro> lcapr_capr, String as_userId, String as_localIpAddress, String as_ipRemote,
	    String as_idTurnoHistoria, String as_observaciones
	)
	    throws B2BException
	{
		StopWatch lsw_watch;

		lsw_watch = Logger.getNewStopWatch();

		getBusiness()
			    .antSistemaSalvarCrear(
			    lcapr_capr, as_userId, as_localIpAddress, as_ipRemote, as_idTurnoHistoria, as_observaciones
			);

		Logger.log(lsw_watch, "AntiguoSistemaBusiness", "antSistemaSalvarCrear", null, null, null, null);
	}

	/**
	 * Ant sistema salvar crear 101.
	 *
	 * @param amsb_archivos de amsb archivos
	 * @param ad_documento de ad documento
	 * @param acapr_capr de acapr capr
	 * @param as_userId de as user id
	 * @param as_localIpAddress de as local ip address
	 * @param as_ipRemote de as ip remote
	 * @param as_observaciones de as observaciones
	 * @param as_idTurnoHistoria de as id turno historia
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void antSistemaSalvarCrear101(
	    Map<String, byte[]> amsb_archivos, DocumentosSalida ad_documento, Collection<AccPredioRegistro> acapr_capr,
	    String as_userId, String as_localIpAddress, String as_ipRemote, String as_observaciones,
	    String as_idTurnoHistoria
	)
	    throws B2BException
	{
		StopWatch lsw_watch;

		lsw_watch = Logger.getNewStopWatch();
		getBusiness()
			    .antSistemaSalvarCrear101(
			    amsb_archivos, ad_documento, acapr_capr, as_userId, as_localIpAddress, as_ipRemote, as_observaciones,
			    as_idTurnoHistoria
			);

		Logger.log(lsw_watch, "AntiguoSistemaBusiness", "antSistemaSalvarCrear101", null, null, null, null);
	}

	/**
	 * Ant sistema salvar rechazar.
	 *
	 * @param atc_tiposCausales de atc tipos causales
	 * @param as_idTurnoHistori de as id turno histori
	 * @param aahr_ampliacionHistoriaRegistral de aahr ampliacion historia registral
	 * @param as_userId de as user id
	 * @param as_localIpAddress de as local ip address
	 * @param as_ipRemote de as ip remote
	 * @param as_observaciones de as observaciones
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void antSistemaSalvarRechazar(
	    TipoCausal atc_tiposCausales, String as_idTurnoHistori,
	    AmpliacionHistoriaRegistral aahr_ampliacionHistoriaRegistral, String as_userId, String as_localIpAddress,
	    String as_ipRemote, String as_observaciones
	)
	    throws B2BException
	{
		StopWatch lsw_watch;

		lsw_watch = Logger.getNewStopWatch();

		getBusiness()
			    .antSistemaSalvarRechazar(
			    atc_tiposCausales, as_idTurnoHistori, aahr_ampliacionHistoriaRegistral, as_userId, as_localIpAddress,
			    as_ipRemote, as_observaciones
			);

		Logger.log(lsw_watch, "AntiguoSistemaBusiness", "antSistemaSalvarRechazar", null, null, null, null);
	}

	/**
	 * Antsistema salvar informe busqueda.
	 *
	 * @param amsb_archivos de amsb archivos
	 * @param ad_documento de ad documento
	 * @param as_userId de as user id
	 * @param as_localIpAddress de as local ip address
	 * @param as_ipRemote de as ip remote
	 * @param as_observaciones de as observaciones
	 * @param as_idTurnoHistoria de as id turno historia
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void antsistemaSalvarInformeBusqueda(
	    Map<String, byte[]> amsb_archivos, DocumentosSalida ad_documento, String as_userId, String as_localIpAddress,
	    String as_ipRemote, String as_observaciones, String as_idTurnoHistoria
	)
	    throws B2BException
	{
		StopWatch lsw_watch;

		lsw_watch = Logger.getNewStopWatch();

		getBusiness()
			    .antsistemaSalvarInformeBusqueda(
			    amsb_archivos, as_idTurnoHistoria, ad_documento, as_userId, as_localIpAddress, as_ipRemote,
			    as_observaciones
			);

		Logger.log(lsw_watch, "AntiguoSistemaBusiness", "antsistemaSalvarInformeBusqueda", null, null, null, null);
	}

	public CompletitudComplementacion buscarCompletitudComplementacionByTurno(
	    CompletitudComplementacion adb_db, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch lsw_watch;

		CompletitudComplementacion lcc_completitudComplementacion;

		lsw_watch     = Logger.getNewStopWatch();

		lcc_completitudComplementacion = getBusiness()
				                                 .buscarCompletitudComplementacionByTurno(
				    adb_db, as_userId, as_remoteIp
				);

		Logger.log(
		    lsw_watch, "AntiguoSistemaBusiness", "buscarCompletitudComplementacionByTurno", as_userId, as_localIp,
		    as_remoteIp, null
		);

		return lcc_completitudComplementacion;
	}

	/**
	 * Buscar todos detalles por solicitud.
	 *
	 * @param adas_parametros de adas parametros
	 * @param as_idUsuario de as id usuario
	 * @param as_localIp de as local ip
	 * @param as_remoteIp de as remote ip
	 * @return el valor de collection
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Collection<DetalleAntSistemaUI> buscarTodosDetallesPorSolicitud(
	    DatosAntSistema adas_parametros, String as_idUsuario, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch                       lsw_watch;
		Collection<DetalleAntSistemaUI> lcdas_return;

		lsw_watch     = Logger.getNewStopWatch();

		lcdas_return = getBusiness().buscarTodosDetallesPorSolicitud(adas_parametros);

		Logger.log(
		    lsw_watch, "AntiguoSistemaBusiness", "buscarTodosDetallesPorSolicitud", as_idUsuario, as_localIp,
		    as_remoteIp, lcdas_return
		);

		return lcdas_return;
	}

	/**
	 * Cargar complementacion predio.
	 *
	 * @param as_idTurno de as id turno
	 * @param as_idTH de as id TH
	 * @return el valor de acc complementacion predio
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public AccComplementacionPredio cargarComplementacionPredio(String as_idTurno, Long as_idTH)
	    throws B2BException
	{
		AccComplementacionPredio lacp_temp;
		StopWatch                lsw_watch;

		lsw_watch     = Logger.getNewStopWatch();

		lacp_temp = getBusiness().cargarComplementacionPredio(as_idTurno, as_idTH);

		Logger.log(lsw_watch, "AntiguoSistemaBusiness", "cargarComplementacionPredio", null, null, null, null);

		return lacp_temp;
	}

	/**
	 * Cargar crear matricula.
	 *
	 * @param as_idTurnoHistoria de as id turno historia
	 * @param as_idDatosAnteSistema de as id datos ante sistema
	 * @return retorna <code>true</code> si se cumple la condicion, de lo contario retorna <code>false</code>
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public boolean cargarCrearMatricula(String as_idTurnoHistoria, String as_idDatosAnteSistema)
	    throws B2BException
	{
		boolean   lb_return;
		StopWatch lsw_watch;

		lsw_watch     = Logger.getNewStopWatch();
		lb_return     = getBusiness().cargarCrearMatricula(as_idTurnoHistoria, as_idDatosAnteSistema);

		Logger.log(lsw_watch, "AntiguoSistemaBusiness", "cargarCrearMatricula", null, null, null, null);

		return lb_return;
	}

	/**
	 * Consulta detalle ant sistema.
	 *
	 * @param acdasu_parametros de acdasu parametros
	 * @param as_idUsuario de as id usuario
	 * @param as_localIp de as local ip
	 * @param as_remoteIp de as remote ip
	 * @return el valor de collection
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Collection<DetalleAntSistemaUI> consultaDetalleAntSistema(
	    DetalleAntSistemaUI acdasu_parametros, String as_idUsuario, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch                       lsw_watch;
		Collection<DetalleAntSistemaUI> lcdas_return;

		lsw_watch     = Logger.getNewStopWatch();

		lcdas_return = getBusiness().consultaDetalleAntSistema(acdasu_parametros);

		Logger.log(
		    lsw_watch, "AntiguoSistemaBusiness", "consultaDetalleAntSistema", as_idUsuario, as_localIp, as_remoteIp,
		    lcdas_return
		);

		return lcdas_return;
	}

	/**
	 * Método encargado de consultar la max id anotacion.
	 *
	 * @param aap_anotacionPredio Contiene la información de anotación predio.
	 * @param as_userId Variable que contiene el id del usuario.
	 * @param as_localIp Variable que contiene la ip del servidor.
	 * @param as_ipRemote Variable que contiene la ip del usuario.
	 * @return Contiene la información del max id anotacion.
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public int consultarMaxIdAnotacion(
	    AnotacionPredio aap_anotacionPredio, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch lsw_watch;
		int       li_max;

		lsw_watch     = Logger.getNewStopWatch();
		li_max        = getBusiness().consultarMaxIdAnotacion(aap_anotacionPredio);

		Logger.log(
		    lsw_watch, "AntiguoSistemaBusiness", "consultarMaxIdAnotacion", as_userId, as_localIp, as_remoteIp, null
		);

		return li_max;
	}

	/**
	 * Método encargado de consultar la max orden.
	 *
	 * @param aap_anotacionPredio Contiene la información de anotación predio.
	 * @param as_userId Variable que contiene el id del usuario.
	 * @param as_localIp Variable que contiene la ip del servidor.
	 * @param as_ipRemote Variable que contiene la ip del usuario.
	 * @return Contiene la información del max orden.
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public int consultarMaxOrden(
	    AnotacionPredio aap_anotacionPredio, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch lsw_watch;
		int       li_max;

		lsw_watch     = Logger.getNewStopWatch();
		li_max        = getBusiness().consultarMaxOrden(aap_anotacionPredio);

		Logger.log(lsw_watch, "AntiguoSistemaBusiness", "consultarMaxOrden", as_userId, as_localIp, as_remoteIp, null);

		return li_max;
	}

	/**
	 * Crear detalle ant sistema.
	 *
	 * @param acdasu_parametros de acdasu parametros
	 * @param as_idUsuario de as id usuario
	 * @param as_localIp de as local ip
	 * @param as_remoteIp de as remote ip
	 * @return el valor de detalle ant sistema UI
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public DetalleAntSistemaUI crearDetalleAntSistema(
	    DetalleAntSistemaUI acdasu_parametros, String as_idUsuario, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch           lsw_watch;
		DetalleAntSistemaUI ldasu_return;

		lsw_watch     = Logger.getNewStopWatch();

		ldasu_return = getBusiness().crearDetalleAntSistema(acdasu_parametros, as_idUsuario, as_remoteIp);

		Logger.log(
		    lsw_watch, "AntiguoSistemaBusiness", "crearDetalleAntSistema", as_idUsuario, as_localIp, as_remoteIp, null
		);

		return ldasu_return;
	}

	/**
	 * Delete by circulo matricula turno.
	 *
	 * @param aps_ps de aps ps
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void deleteByCirculoMatriculaTurno(PredioSegregado aps_ps)
	    throws B2BException
	{
		StopWatch lsw_watch;

		lsw_watch = Logger.getNewStopWatch();

		getBusiness().deleteByCirculoMatriculaTurno(aps_ps);

		Logger.log(lsw_watch, "AntiguoSistemaBusiness", "deleteByCirculoMatriculaTurno", null, null, null, null);
	}

	/**
	 * Delete by id.
	 *
	 * @param aps_ps de aps ps
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void deleteById(DireccionPredioAcc aps_ps)
	    throws B2BException
	{
		StopWatch lsw_watch;

		lsw_watch = Logger.getNewStopWatch();

		getBusiness().deleteById(aps_ps);

		Logger.log(lsw_watch, "AntiguoSistemaBusiness", "deleteById", null, null, null, null);
	}

	/**
	 * Eliminar anotacio predio.
	 *
	 * @param aap_anotacionPredio de aap anotacion predio
	 * @param as_userId de as user id
	 * @param as_localIp de as local ip
	 * @param as_remoteIp de as remote ip
	 * @return el valor de collection
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Collection<AnotacionPredio> eliminarAnotacioPredio(
	    AnotacionPredio aap_anotacionPredio, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch                   lsw_watch;
		Collection<AnotacionPredio> lcap_anotaciones;

		lsw_watch     = Logger.getNewStopWatch();

		lcap_anotaciones = getBusiness().eliminarAnotacioPredio(aap_anotacionPredio, as_userId, as_remoteIp);

		Logger.log(
		    lsw_watch, "AntiguoSistemaBusiness", "eliminarAnotacioPredio", as_userId, as_localIp, as_remoteIp, null
		);

		return lcap_anotaciones;
	}

	/**
	 * Eliminar detalle ant sistema.
	 *
	 * @param adasu_parametros de adasu parametros
	 * @param as_userId de as user id
	 * @param as_localIpAddress de as local ip address
	 * @param as_remoteIpAddress de as remote ip address
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void eliminarDetalleAntSistema(
	    DetalleAntSistemaUI adasu_parametros, String as_userId, String as_localIpAddress, String as_remoteIpAddress
	)
	    throws B2BException
	{
		StopWatch lsw_watch;

		lsw_watch = Logger.getNewStopWatch();

		getBusiness().eliminarDetalleAntSistema(adasu_parametros, as_userId, as_localIpAddress, as_remoteIpAddress);

		Logger.log(
		    lsw_watch, "AntiguoSistemaBusiness", "eliminarDetalleAntSistema", as_userId, as_localIpAddress,
		    as_remoteIpAddress, null
		);
	}

	/**
	 * Eliminar matriculas asociadas.
	 *
	 * @param adas_datosAntSistema de adas datos ant sistema
	 * @param as_userId de as user id
	 * @param as_localIp de as local ip
	 * @param as_remoteIp de as remote ip
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void eliminarMatriculasAsociadas(
	    DatosAntSistema adas_datosAntSistema, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch lsw_watch;

		lsw_watch = Logger.getNewStopWatch();

		getBusiness().eliminarMatriculasAsociadas(adas_datosAntSistema, as_userId, as_remoteIp);

		Logger.log(
		    lsw_watch, "AntiguoSistemaBusiness", "eliminarMatriculasAsociadas", as_userId, as_localIp, as_remoteIp, null
		);
	}

	/**
	 * Eliminar matriculas creadas.
	 *
	 * @param as_idTurno de as id turno
	 * @param as_idDatosAntSistema de as id datos ant sistema
	 * @param as_userId de as user id
	 * @param as_localIp de as local ip
	 * @param as_remoteIp de as remote ip
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void eliminarMatriculasCreadas(
	    String as_idTurno, String as_idDatosAntSistema, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch lsw_watch;

		lsw_watch = Logger.getNewStopWatch();

		getBusiness().eliminarMatriculasCreadas(as_idTurno, as_idDatosAntSistema, as_userId, as_remoteIp);

		Logger.log(
		    lsw_watch, "AntiguoSistemaBusiness", "eliminarMatriculasCreadas", as_userId, as_localIp, as_remoteIp, null
		);
	}

	/**
	 * Método encargado de enviar al responsable de aprobacion firma libro ant sistema.
	 *
	 * @param ath_parametros correspondiente al valor de ath parametros
	 * @param al_idMotivo correspondiente al valor de al id motivo
	 * @param as_userId Variable de tipo String que contiene el id del usuario que está realizando el trámite.
	 * @param as_localIp correspondiente al valor de as local ip
	 * @param as_remoteIp Variable de tipo String que contiene la ip del usuario que está realizando el trámite.
	 * @throws B2BException Se genera cuando se presenta una excepción.
	 */
	public void enviarResponsableAntSistemaFirma(
	    TurnoHistoria ath_parametros, long al_idMotivo, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch lsw_watch;

		lsw_watch = Logger.getNewStopWatch();
		getBusiness().enviarResponsableAntSistemaFirma(ath_parametros, as_userId, as_remoteIp, al_idMotivo);

		Logger.log(
		    lsw_watch, "AntiguoSistemaBusiness", "enviarResponsableAntSistemaFirma", as_userId, as_localIp, as_remoteIp,
		    null
		);
	}

	/**
	 * Find acc direccion by id.
	 *
	 * @param adp_param de adp param
	 * @return el valor de direccion predio acc
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public DireccionPredioAcc findAccDireccionById(DireccionPredioAcc adp_param)
	    throws B2BException
	{
		StopWatch          lsw_watch;
		DireccionPredioAcc lps_data;

		lsw_watch     = Logger.getNewStopWatch();

		lps_data = getBusiness().findAccDireccionById(adp_param);

		Logger.log(lsw_watch, "AntiguoSistemaBusiness", "findAccDireccionById", null, null, null, null);

		return lps_data;
	}

	/**
	 * Find acc predio registro by turno historia.
	 *
	 * @param aapr_accPredioRegistro de aapr acc predio registro
	 * @return el valor de acc predio registro
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public AccPredioRegistro findAccPredioRegistroByTurnoHistoria(AccPredioRegistro aapr_accPredioRegistro)
	    throws B2BException
	{
		StopWatch         lsw_watch;
		AccPredioRegistro lsb_return;

		lsw_watch     = Logger.getNewStopWatch();

		lsb_return = getBusiness().findAccPredioRegistroByTurnoHistoria(aapr_accPredioRegistro);

		Logger.log(lsw_watch, "AntiguoSistemaBusiness", "findAccPredioRegistroByTurnoHistoria", null, null, null, null);

		return lsb_return;
	}

	/**
	 * Find antiguo sistema data.
	 *
	 * @param as_userId de as user id
	 * @param ldas_datosAntSistema de ldas datos ant sistema
	 * @return el valor de antiguo sistema data
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	@Override
	public AntiguoSistemaData findAntiguoSistemaData(String as_userId, DatosAntSistema ldas_datosAntSistema)
	    throws B2BException
	{
		StopWatch          lsw_watch;
		AntiguoSistemaData lasd_return;

		lsw_watch     = Logger.getNewStopWatch();

		lasd_return = getBusiness().findAntiguoSistemaData(as_userId, ldas_datosAntSistema);

		Logger.log(lsw_watch, "AntiguoSistemaBusiness", "findAntiguoSistemaData", as_userId, null, null, null);

		return lasd_return;
	}

	/**
	 * Find by circulo matricula turno.
	 *
	 * @param aps_param de aps param
	 * @return el valor de predio segregado
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public PredioSegregado findByCirculoMatriculaTurno(PredioSegregado aps_param)
	    throws B2BException
	{
		StopWatch       lsw_watch;
		PredioSegregado lps_data;

		lsw_watch     = Logger.getNewStopWatch();

		lps_data = getBusiness().findByCirculoMatriculaTurno(aps_param);

		Logger.log(lsw_watch, "AntiguoSistemaBusiness", "findByCirculoMatriculaTurno", null, null, null, null);

		return lps_data;
	}

	/**
	 * Find by circulo matricula turno anotacion.
	 *
	 * @param aap_parametros de aap parametros
	 * @param as_usuario de as usuario
	 * @param as_ipLocal de as ip local
	 * @param as_ipRemota de as ip remota
	 * @return el valor de anotacion predio
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public AnotacionPredio findByCirculoMatriculaTurnoAnotacion(
	    AnotacionPredio aap_parametros, String as_usuario, String as_ipLocal, String as_ipRemota
	)
	    throws B2BException
	{
		AnotacionPredio lap_datos;
		StopWatch       lsw_watch;

		lsw_watch     = Logger.getNewStopWatch();

		lap_datos = getBusiness().findByCirculoMatriculaTurnoAnotacion(aap_parametros);

		Logger.log(
		    lsw_watch, "AntiguoSistemaBusiness", "findByCirculoMatriculaTurnoAnotacion", as_usuario, as_ipLocal,
		    as_ipRemota, null
		);

		return lap_datos;
	}

	/**
	 * Find by datos ant sistema.
	 *
	 * @param datosAntiguoSistema de datos antiguo sistema
	 * @return el valor de collection
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Collection<DataConsultaAntSistema> findByDatosAntSistema(DatosAntSistema datosAntiguoSistema)
	    throws B2BException
	{
		StopWatch                          lsw_watch;
		Collection<DataConsultaAntSistema> lr_return;

		lsw_watch     = Logger.getNewStopWatch();

		lr_return = getBusiness().findByDatosAntSistema(datosAntiguoSistema);

		Logger.log(lsw_watch, "AntiguoSistemaBusiness", "findByDatosDocumento", null, null, null, null);

		return lr_return;
	}

	/**
	 * Find by datos documento.
	 *
	 * @param acdd_data de acdd data
	 * @return el valor de collection
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Collection<DataConsultaDatosDocumento> findByDatosDocumento(ConsultaDatosDocumento acdd_data)
	    throws B2BException
	{
		StopWatch                              lsw_watch;
		Collection<DataConsultaDatosDocumento> lr_return;

		lsw_watch     = Logger.getNewStopWatch();

		lr_return = getBusiness().findByDatosDocumento(acdd_data);

		Logger.log(lsw_watch, "AntiguoSistemaBusiness", "findByDatosDocumento", null, null, null, null);

		return lr_return;
	}

	/* (non-Javadoc)
	 * @see com.bachue.snr.prosnr01.ejb.session.stateless.antiguo.sistema.AntiguoSistemaRemote#findCodigoNombreCirculoDestino(java.lang.String)
	 */
	public CirculoRegistral findCodigoNombreCirculoDestino(String as_idCirculoDestino)
	    throws B2BException
	{
		StopWatch        lsw_watch;
		CirculoRegistral lcr_return;

		lsw_watch     = Logger.getNewStopWatch();

		lcr_return = getBusiness().findCodigoNombreCirculoDestino(as_idCirculoDestino);

		Logger.log(lsw_watch, "AntiguoSistemaBusiness", "findByDatosDocumento", null, null, null, null);

		return lcr_return;
	}

	/**
	 * Find complementacion.
	 *
	 * @param lc_complementacion de lc complementacion
	 * @return el valor de complementacion predio
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public ComplementacionPredio findComplementacion(Complementacion lc_complementacion)
	    throws B2BException
	{
		StopWatch             lsw_watch;
		ComplementacionPredio lcp_return;

		lsw_watch     = Logger.getNewStopWatch();

		lcp_return = getBusiness().findComplementacion(lc_complementacion);

		Logger.log(lsw_watch, "AntiguoSistemaBusiness", "findComplementacion", null, null, null, null);

		return lcp_return;
	}

	/**
	 * Find datos ant sistema.
	 *
	 * @param al_idTurnoHistoria de al id turno historia
	 * @return el valor de datos ant sistema
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public DatosAntSistema findDatosAntSistema(Long al_idTurnoHistoria)
	    throws B2BException
	{
		StopWatch       lsw_watch;
		DatosAntSistema lcddp_return;

		lsw_watch     = Logger.getNewStopWatch();

		lcddp_return = getBusiness().findDatosAntSistema(al_idTurnoHistoria);

		Logger.log(lsw_watch, "AntiguoSistemaBusiness", "findDatosAntSistema", null, null, null, null);

		return lcddp_return;
	}

	/**
	 * Find datos documento.
	 *
	 * @param al_turnoHistoria de al turno historia
	 * @return el valor de consulta datos documento
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public ConsultaDatosDocumento findDatosDocumento(Long al_turnoHistoria)
	    throws B2BException
	{
		StopWatch              lsw_watch;
		ConsultaDatosDocumento lcdd_return;

		lsw_watch     = Logger.getNewStopWatch();

		lcdd_return = getBusiness().findDatosDocumento(al_turnoHistoria);

		Logger.log(lsw_watch, "AntiguoSistemaBusiness", "findDatosDocumento", null, null, null, null);

		return lcdd_return;
	}

	/**
	 * Find datos predio by turno.
	 *
	 * @param as_userId de as user id
	 * @param as_idTurno de as id turno
	 * @param as_section de as section
	 * @return el valor de list
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public List<Map<String, Object>> findDatosPredioByTurno(String as_userId, String as_idTurno, String as_section)
	    throws B2BException
	{
		StopWatch                 lsw_watch;
		List<Map<String, Object>> lr_registro;

		lsw_watch     = Logger.getNewStopWatch();

		lr_registro = getBusiness().findDatosPredioByTurno(as_userId, as_idTurno, as_section);

		Logger.log(lsw_watch, "AntiguoSistemaBusiness", "findPredioDocumentoByTurno", as_idTurno, null, null, null);

		return lr_registro;
	}

	/**
	 * Find detail inbox.
	 *
	 * @param as_userId de as user id
	 * @param al_idEtapa de al id etapa
	 * @param as_idTurno de as id turno
	 * @param as_nir de as nir
	 * @return el valor de list
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public List<Map<String, Object>> findDetailInbox(
	    String as_userId, Long al_idEtapa, String as_idTurno, String as_nir
	)
	    throws B2BException
	{
		StopWatch                 lsw_watch;
		List<Map<String, Object>> lr_registro;

		lsw_watch     = Logger.getNewStopWatch();

		lr_registro = getBusiness().findDetailInbox(as_userId, al_idEtapa, as_idTurno, as_nir);

		Logger.log(lsw_watch, "AntiguoSistemaBusiness", "findDetailInbox", as_userId, null, null, null);

		return lr_registro;
	}

	/**
	 * Find documento data.
	 *
	 * @param userId de user id
	 * @param acdd_consultaDatosDocumento de acdd consulta datos documento
	 * @return el valor de documento data
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public DocumentoData findDocumentoData(String userId, ConsultaDatosDocumento acdd_consultaDatosDocumento)
	    throws B2BException
	{
		StopWatch     lsw_watch;
		DocumentoData ldd_return;

		lsw_watch     = Logger.getNewStopWatch();

		ldd_return = getBusiness().findDocumentoData(userId, acdd_consultaDatosDocumento);

		Logger.log(lsw_watch, "AntiguoSistemaBusiness", "findDocumentoData", userId, null, null, null);

		return ldd_return;
	}

	/**
	 * Find id circulo origen.
	 *
	 * @param at_turno de at turno
	 * @return el valor de turno
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Turno findIdCirculoOrigen(Turno at_turno)
	    throws B2BException
	{
		StopWatch lsw_watch;
		Turno     lcddp_return;

		lsw_watch     = Logger.getNewStopWatch();

		lcddp_return = getBusiness().findIdCirculoOrigen(at_turno);

		Logger.log(lsw_watch, "AntiguoSistemaBusiness", "findIdCirculoOrigen", null, null, null, null);

		return lcddp_return;
	}

	/**
	 * Find inbox by user id.
	 *
	 * @param as_userId de as user id
	 * @param as_idTurno de as id turno
	 * @param as_nir de as nir
	 * @param al_etapa contiene la etapa a consultar
	 * @return el valor de collection
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Collection<TramiteCantidad> findInboxByUserId(
	    String as_userId, String as_idTurno, String as_nir, long al_etapa
	)
	    throws B2BException
	{
		StopWatch                   lsw_watch;
		Collection<TramiteCantidad> lr_registro;

		lsw_watch     = Logger.getNewStopWatch();

		lr_registro = getBusiness().findInboxByUserId(as_userId, as_idTurno, as_nir, al_etapa);

		Logger.log(lsw_watch, "AntiguoSistemaBusiness", "findInboxByUserId", as_userId, null, null, null);

		return lr_registro;
	}

	/**
	 * Find informes busqueda.
	 *
	 * @param ads_documentoSalida de ads documento salida
	 * @return el valor de collection
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Collection<DocumentosSalida> findInformesBusqueda(DocumentosSalida ads_documentoSalida)
	    throws B2BException
	{
		StopWatch                    lsw_watch;
		Collection<DocumentosSalida> lcds_return;

		lsw_watch       = Logger.getNewStopWatch();
		lcds_return     = getBusiness().findInformesBusqueda(ads_documentoSalida);

		Logger.log(lsw_watch, "AntiguoSistemaBusiness", "findInformesBusqueda", null, null, null, null);

		return lcds_return;
	}

	/**
	 * Find matriculas solicitud ant sistema.
	 *
	 * @param as_idDatosAntSistemaActual de as id datos ant sistema actual
	 * @param as_idTurno de as id turno
	 * @return el valor de map
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Map<String, Collection<SolicitudMatriculaActo>> findMatriculasSolicitudAntSistema(
	    String as_idDatosAntSistemaActual, String as_idTurno
	)
	    throws B2BException
	{
		StopWatch                                       lsw_watch;
		Map<String, Collection<SolicitudMatriculaActo>> lcsma_return;

		lsw_watch        = Logger.getNewStopWatch();
		lcsma_return     = getBusiness().findMatriculasSolicitudAntSistema(as_idDatosAntSistemaActual, as_idTurno);

		Logger.log(lsw_watch, "AntiguoSistemaBusiness", "findMatriculasSolicitudAntSistema", null, null, null, null);

		return lcsma_return;
	}

	/**
	 * Find naturaleza juridica by id.
	 *
	 * @param anj_param de anj param
	 * @return el valor de naturaleza juridica
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public NaturalezaJuridica findNaturalezaJuridicaById(NaturalezaJuridica anj_param)
	    throws B2BException
	{
		StopWatch          lsw_watch;
		NaturalezaJuridica lr_registro;

		lsw_watch     = Logger.getNewStopWatch();

		lr_registro = getBusiness().findNaturalezajuridicaById(anj_param);

		Logger.log(lsw_watch, "AntiguoSistemaBusiness", "findNaturalezaJuridicaById", null, null, null, null);

		return lr_registro;
	}

	/**
	 * Find observaciones calificador.
	 *
	 * @param as_idTurnoHistoria de as id turno historia
	 * @return el valor de string
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public String findObservacionesCalificador(String as_idTurnoHistoria)
	    throws B2BException
	{
		String    ls_return;
		StopWatch lsw_watch;

		lsw_watch     = Logger.getNewStopWatch();
		ls_return     = getBusiness().findObservacionesCalificador(as_idTurnoHistoria);

		Logger.log(lsw_watch, "AntiguoSistemaBusiness", "findObservacionesCalificador", null, null, null, null);

		return ls_return;
	}

	/**
	 * Find predio registro by id.
	 *
	 * @param ap_parametros de ap parametros
	 * @param as_userId de as user id
	 * @return el valor de anotacion
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Anotacion findPredioRegistroById(Anotacion ap_parametros, String as_userId)
	    throws B2BException
	{
		StopWatch lsw_watch;
		Anotacion la_anotacion;

		lsw_watch     = Logger.getNewStopWatch();

		la_anotacion = getBusiness().findPredioRegistroById(ap_parametros, as_userId);

		Logger.log(lsw_watch, "AntiguoSistemaBusiness", "findPredioRegistroById", as_userId, null, null, null);

		return la_anotacion;
	}

	/**
	 * Find proceso antiguo sistema.
	 *
	 * @param as_idTurno de as id turno
	 * @param as_idDatosAntSistemaActual de as id datos ant sistema actual
	 * @param as_idTurnoHistoria de as id turno historia
	 * @return el valor de map
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Map<String, Boolean> findProcesoAntiguoSistema(
	    String as_idTurno, String as_idDatosAntSistemaActual, String as_idTurnoHistoria
	)
	    throws B2BException
	{
		Map<String, Boolean> lmsb_return;
		StopWatch            lsw_watch;

		lsw_watch       = Logger.getNewStopWatch();
		lmsb_return     = getBusiness()
				                  .findProcesoAntiguoSistema(
				    as_idTurno, as_idDatosAntSistemaActual, as_idTurnoHistoria
				);

		Logger.log(lsw_watch, "AntiguoSistemaBusiness", "findProcesoAntiguoSistema", null, null, null, null);

		return lmsb_return;
	}

	/**
	 * Find tipo acto by id.
	 *
	 * @param ata_parametros de ata parametros
	 * @return el valor de tipo acto
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public TipoActo findTipoActoById(TipoActo ata_parametros)
	    throws B2BException
	{
		TipoActo  lta_datos;
		StopWatch lsw_watch;

		lsw_watch     = Logger.getNewStopWatch();

		lta_datos = getBusiness().findTipoActoById(ata_parametros);

		Logger.log(lsw_watch, "AntiguoSistemaBusiness", "findTipoActoById", null, null, null, null);

		return lta_datos;
	}

	/**
	 * Find turno historia by id.
	 *
	 * @param ath_param de ath param
	 * @return el valor de turno historia
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public TurnoHistoria findTurnoHistoriaById(TurnoHistoria ath_param)
	    throws B2BException
	{
		StopWatch     lsw_watch;
		TurnoHistoria lr_registro;

		lsw_watch     = Logger.getNewStopWatch();

		lr_registro = getBusiness().findTurnoHistoriaById(ath_param);

		Logger.log(lsw_watch, "AntiguoSistemaBusiness", "findTurnoHistoriaById", null, null, null, null);

		return lr_registro;
	}

	/**
	 * Find zona registral circulo.
	 *
	 * @param azr_zonaRegistral de azr zona registral
	 * @return el valor de zona registral
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public ZonaRegistral findZonaRegistralCirculo(ZonaRegistral azr_zonaRegistral)
	    throws B2BException
	{
		ZonaRegistral lzr_return;
		StopWatch     lsw_watch;

		lsw_watch      = Logger.getNewStopWatch();
		lzr_return     = getBusiness().findZonaRegistralCirculo(azr_zonaRegistral);

		Logger.log(lsw_watch, "AntiguoSistemaBusiness", "findZonaRegistralCirculo", null, null, null, null);

		return lzr_return;
	}

	/**
	 * Firmar libro ant sistema en sdb_acc_datos_ant_sistema.
	 *
	 * @param as_idDatosAntSistema correspondiente al valor de as id datos ant sistema
	 * @param as_userId correspondiente al valor de as user id
	 * @param as_localIp correspondiente al valor de as local ip
	 * @param as_remoteIp correspondiente al valor de as remote ip
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void firmarLibroAntSistema(
	    String as_idDatosAntSistema, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		{
			StopWatch lsw_watch;

			lsw_watch = Logger.getNewStopWatch();

			getBusiness().firmarLibroAntSistema(as_idDatosAntSistema, as_userId, as_remoteIp);

			Logger.log(
			    lsw_watch, "AntiguoSistemaBusiness", "firmarLibroAntSistema", as_userId, as_localIp, as_remoteIp, null
			);
		}
	}

	/**
	 * Generar archivo rechazo.
	 *
	 * @param aotc_tc de aotc tc
	 * @param as_turnoH de as turno H
	 * @param as_idUsuario de as id usuario
	 * @param aahr_ahr de aahr ahr
	 * @param as_localIp de as local ip
	 * @param as_remoteIp de as remote ip
	 * @return el valor de byte[]
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public byte[] generarArchivoRechazo(
	    TipoCausal aotc_tc, String as_turnoH, String as_idUsuario, AmpliacionHistoriaRegistral aahr_ahr,
	    String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch lsw_watch;
		byte[]    lr_registro;

		lsw_watch     = Logger.getNewStopWatch();

		lr_registro = getBusiness().generarArchivoRechazo(
			    aotc_tc, as_turnoH, as_idUsuario, aahr_ahr, as_remoteIp, false
			);

		Logger.log(
		    lsw_watch, "AntiguoSistemaBusiness", "generarArchivoRechazo", as_idUsuario, as_localIp, as_remoteIp, null
		);

		return lr_registro;
	}

	/**
	 * Método de generación del documento de matrícula oficio para AS
	 * @param aot_oficioTextoData con los datos para generar el documento
	 * @param as_userId con el usuario de la transacción
	 * @param as_remoteIp con la ip remota del usuario de la transacción
	 * @param as_localIp con la ip local del usuario de la transacción
	 * @param al_motivo con el id motivo de la plantilla a generar
	 * @return de tipo byte con el documento generado
	 * @throws B2BException
	 */
	public byte[] generarDocumentoMatriculaOficioAS(
	    OficiosTexto aot_oficioTextoData, String as_userId, String as_remoteIp, String as_localIp, long al_motivo
	)
	    throws B2BException
	{
		byte[]    lba_return;
		StopWatch lsw_watch;

		lsw_watch     = Logger.getNewStopWatch();

		lba_return = getBusiness()
				             .generarDocumentoMatriculaOficioAS(aot_oficioTextoData, as_userId, as_remoteIp, al_motivo);

		Logger.log(
		    lsw_watch, "AntiguoSistemaBusiness", "actualizarDireccionPredio", as_userId, as_localIp, as_remoteIp, null
		);

		return lba_return;
	}

	/**
	 * Generar pdf autorizacion firma.
	 *
	 * @param alth_lth de alth lth
	 * @param ab_cargar de ab cargar
	 * @param as_idUsuario de as id usuario
	 * @param as_localIp de as local ip
	 * @param as_remoteIp de as remote ip
	 * @return el valor de byte[]
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public byte[] generarPdfAutorizacionFirma(
	    TurnoHistoria alth_lth, boolean ab_cargar, String as_idUsuario, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch lsw_watch;
		byte[]    lr_return;

		lsw_watch     = Logger.getNewStopWatch();

		lr_return = getBusiness().generarPdfAutorizacionFirma(
			    alth_lth, ab_cargar, as_idUsuario, as_localIp, as_remoteIp
			);

		Logger.log(
		    lsw_watch, "AntiguoSistemaBusiness", "generarPdfAutorizacionFirma", as_idUsuario, as_localIp, as_localIp,
		    null
		);

		return lr_return;
	}

	/**
	 * Generar pdf solicitud complementacion.
	 *
	 * @param aahr_ampliacionHistoriaRegistral de aahr ampliacion historia registral
	 * @param as_idUsuario de as id usuario
	 * @param as_localIp de as local ip
	 * @param as_remoteIp de as remote ip
	 * @return el valor de byte[]
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public byte[] generarPdfSolicitudComplementacion(
	    AmpliacionHistoriaRegistral aahr_ampliacionHistoriaRegistral, String as_idUsuario, String as_localIp,
	    String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch lsw_watch;
		byte[]    lr_return;

		lsw_watch     = Logger.getNewStopWatch();

		lr_return = getBusiness()
				            .generarPdfSolicitudComplementacion(
				    aahr_ampliacionHistoriaRegistral, as_idUsuario, as_localIp, as_remoteIp
				);

		Logger.log(
		    lsw_watch, "AntiguoSistemaBusiness", "generarPdfSolicitudComplementacion", as_idUsuario, as_localIp,
		    as_localIp, null
		);

		return lr_return;
	}

	/**
	 * Generar pdf solicitud complementacion oficina destino.
	 *
	 * @param alth_lth correspondiente al valor de alth lth
	 * @param ab_cargar correspondiente al valor de ab cargar
	 * @param as_idUsuario correspondiente al valor de as id usuario
	 * @param as_localIp correspondiente al valor de as local ip
	 * @param as_remoteIp correspondiente al valor de as remote ip
	 * @return el valor de byte[]
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public byte[] generarPdfSolicitudComplementacionOfiDestino(
	    String alth_lth, boolean ab_cargar, String as_idUsuario, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch lsw_watch;
		byte[]    lr_return;

		lsw_watch     = Logger.getNewStopWatch();

		lr_return = getBusiness()
				            .generarPdfSolicitudComplementacionOfiDestino(
				    alth_lth, ab_cargar, as_idUsuario, as_localIp
				);

		Logger.log(
		    lsw_watch, "AntiguoSistemaBusiness", "generarPdfSolicitudComplementacionOfiDestino", as_idUsuario,
		    as_localIp, as_remoteIp, null
		);

		return lr_return;
	}

	/**
	 * Generar pdf solicitud firma.
	 *
	 * @param adas_das de adas das
	 * @param amso_mso de amso mso
	 * @param ab_vieneDeEtapaAntSis de  ab viene de etapa ant sis
	 * @param as_idUsuario de as id usuario
	 * @param as_localIp de as local ip
	 * @param as_remoteIp de as remote ip
	 * @return el valor de byte[]
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public byte[] generarPdfSolicitudFirma(
	    DatosAntSistema adas_das, Map<String, Object> amso_mso, boolean ab_vieneDeEtapaAntSis, String as_idUsuario,
	    String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch lsw_watch;
		byte[]    lr_return;

		lsw_watch     = Logger.getNewStopWatch();

		lr_return = getBusiness()
				            .generarPdfSolicitudFirma(
				    adas_das, amso_mso, ab_vieneDeEtapaAntSis, as_idUsuario, as_localIp, as_remoteIp
				);

		Logger.log(
		    lsw_watch, "AntiguoSistemaBusiness", "generarPdfSolicitudFirma", as_idUsuario, as_localIp, as_localIp, null
		);

		return lr_return;
	}

	/**
	 * Guardar datos anotacion.
	 *
	 * @param aap_anotacionPredio de aap anotacion predio
	 * @param as_usuario de as usuario
	 * @param as_ipLocal de as ip local
	 * @param as_ipRemota de as ip remota
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void guardarDatosAnotacion(
	    AnotacionPredio aap_anotacionPredio, String as_usuario, String as_ipLocal, String as_ipRemota
	)
	    throws B2BException
	{
		StopWatch lsw_watch;

		lsw_watch = Logger.getNewStopWatch();

		getBusiness().guardarDatosAnotacion(aap_anotacionPredio, as_usuario, as_ipRemota);

		Logger.log(
		    lsw_watch, "AntiguoSistemaBusiness", "guardarDatosAnotacion", as_usuario, as_ipLocal, as_ipRemota, null
		);
	}

	/**
	 * Guardar datos ant sistema.
	 *
	 * @param aap_anotacionPredio de aap anotacion predio
	 * @param adas_detalleAntSistema de adas detalle ant sistema
	 * @param aa_anotacion de aa anotacion
	 * @param aa_iterador de aa iterador
	 * @param as_usuario de as usuario
	 * @param as_ipLocal de as ip local
	 * @param as_ipRemota de as ip remota
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void guardarDatosAntSistema(
	    AnotacionPredio aap_anotacionPredio, DetalleAntSistema adas_detalleAntSistema, Anotacion aa_anotacion,
	    Anotacion aa_iterador, String as_usuario, String as_ipLocal, String as_ipRemota
	)
	    throws B2BException
	{
		StopWatch lsw_watch;

		lsw_watch = Logger.getNewStopWatch();

		getBusiness()
			    .guardarDatosAntSistema(
			    aap_anotacionPredio, adas_detalleAntSistema, aa_anotacion, aa_iterador, as_usuario, as_ipRemota
			);

		Logger.log(
		    lsw_watch, "AntiguoSistemaBusiness", "guardarDatosAntSistema", as_usuario, as_ipLocal, as_ipRemota, null
		);
	}

	/**
	 * Guardar datos apertura.
	 *
	 * @param adb_datosBasicos de adb datos basicos
	 * @param as_userId de as user id
	 * @param as_localIp de as local ip
	 * @param as_remoteIp de as remote ip
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void guardarDatosApertura(
	    DatosBasicos adb_datosBasicos, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch lsw_watch;

		lsw_watch = Logger.getNewStopWatch();

		getBusiness().salvarDatosBasicosApertura(adb_datosBasicos, as_userId, as_remoteIp);

		Logger.log(
		    lsw_watch, "AntiguoSistemaBusiness", "guardarDatosApertura", as_userId, as_localIp, as_remoteIp, null
		);
	}

	/**
	 * Guardar datos catastral.
	 *
	 * @param adb_datosBasicos de adb datos basicos
	 * @param as_userId de as user id
	 * @param as_localIp de as local ip
	 * @param as_remoteIp de as remote ip
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void guardarDatosCatastral(
	    DatosBasicos adb_datosBasicos, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch lsw_watch;

		lsw_watch = Logger.getNewStopWatch();

		getBusiness().salvarDatosBasicosCatastral(adb_datosBasicos, as_userId, as_remoteIp);

		Logger.log(
		    lsw_watch, "AntiguoSistemaBusiness", "guardarDatosCatastral", as_userId, as_localIp, as_remoteIp, null
		);
	}

	/**
	 * Guardar datos direccion.
	 *
	 * @param aapm_predio de aapm predio
	 * @param as_userId de as user id
	 * @param as_localIp de as local ip
	 * @param as_remoteIp de as remote ip
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void guardarDatosDireccion(
	    AccPredioRegistro aapm_predio, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch lsw_watch;

		lsw_watch = Logger.getNewStopWatch();

		getBusiness().salvarDireccionPredio(aapm_predio, as_userId, as_remoteIp);

		Logger.log(
		    lsw_watch, "AntiguoSistemaBusiness", "guardarDatosDireccion", as_userId, as_localIp, as_remoteIp, null
		);
	}

	/**
	 * Guardar datos documento anotacion.
	 *
	 * @param ad_documento de ad documento
	 * @param as_usuario de as usuario
	 * @param as_ipLocal de as ip local
	 * @param as_ipRemota de as ip remota
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void guardarDatosDocumentoAnotacion(
	    Documento ad_documento, String as_usuario, String as_ipLocal, String as_ipRemota
	)
	    throws B2BException
	{
		StopWatch lsw_watch;

		lsw_watch = Logger.getNewStopWatch();

		getBusiness().guardarDatosDocumentoAnotacion(ad_documento, as_usuario, as_ipRemota);

		Logger.log(
		    lsw_watch, "AntiguoSistemaBusiness", "guardarDatosDocumentoAnotacion", as_usuario, as_ipLocal, as_ipRemota,
		    null
		);
	}

	/**
	 * Guardar datos especificacion anotacion.
	 *
	 * @param aap_anotacionPredio de aap anotacion predio
	 * @param al_idAnotacion de al id anotacion
	 * @param aa_anotacion de aa anotacion
	 * @param ath_turnoHistoria de ath turno historia
	 * @param as_usuario de as usuario
	 * @param as_ipLocal de as ip local
	 * @param as_ipRemota de as ip remota
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void guardarDatosEspecificacionAnotacion(
	    AnotacionPredio aap_anotacionPredio, Long al_idAnotacion, Anotacion aa_anotacion,
	    TurnoHistoria ath_turnoHistoria, String as_usuario, String as_ipLocal, String as_ipRemota
	)
	    throws B2BException
	{
		StopWatch lsw_watch;

		lsw_watch = Logger.getNewStopWatch();

		getBusiness()
			    .guardarDatosEspecificacionAnotacion(
			    aap_anotacionPredio, al_idAnotacion, aa_anotacion, ath_turnoHistoria, as_usuario, as_ipRemota
			);

		Logger.log(
		    lsw_watch, "AntiguoSistemaBusiness", "guardarDatosEspecificacionAnotacion", as_usuario, as_ipLocal,
		    as_ipRemota, null
		);
	}

	/**
	 * Guardar datos matriculas.
	 *
	 * @param adb_datosBasicos de adb datos basicos
	 * @param as_userId de as user id
	 * @param as_localIp de as local ip
	 * @param as_remoteIp de as remote ip
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void guardarDatosMatriculas(
	    DatosBasicos adb_datosBasicos, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch lsw_watch;

		lsw_watch = Logger.getNewStopWatch();

		getBusiness().salvarDatosBasicosMatriculas(adb_datosBasicos, as_userId, as_remoteIp);

		Logger.log(
		    lsw_watch, "AntiguoSistemaBusiness", "guardarDatosMatriculas", as_userId, as_localIp, as_remoteIp, null
		);
	}

	/**
	 * Guardar informe de busqueda.
	 *
	 * @param as_observaciones de as observaciones
	 * @param ad_documento de ad documento
	 * @param amsb_archivos de amsb archivos
	 * @param as_userId de as user id
	 * @param as_localIpAddress de as local ip address
	 * @param as_ipRemote de as ip remote
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void guardarInformeDeBusqueda(
	    String as_observaciones, DocumentosSalida ad_documento, Map<String, byte[]> amsb_archivos, String as_userId,
	    String as_localIpAddress, String as_ipRemote
	)
	    throws B2BException
	{
		StopWatch lsw_watch;

		lsw_watch = Logger.getNewStopWatch();

		getBusiness()
			    .guardarInformeDeBusqueda(
			    as_observaciones, amsb_archivos, ad_documento, as_userId, as_localIpAddress, as_ipRemote
			);

		Logger.log(
		    lsw_watch, "AntiguoSistemaBusiness", "guardarInformeDeBusqueda", as_userId, as_localIpAddress, as_ipRemote,
		    null
		);
	}

	/**
	 * Guardar intervinientes anotacion.
	 *
	 * @param aa_anotacion de aa anotacion
	 * @param aa_anotacionPredioCiudadano de aa anotacion predio ciudadano
	 * @param as_idSolicitud de as id solicitud
	 * @param as_idAnotacionPredio de as id anotacion predio
	 * @param as_usuario de as usuario
	 * @param as_ipLocal de as ip local
	 * @param as_ipRemota de as ip remota
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void guardarIntervinientesAnotacion(
	    Anotacion aa_anotacion, AnotacionPredioCiudadano aa_anotacionPredioCiudadano, String as_idSolicitud,
	    String as_idAnotacionPredio, String as_usuario, String as_ipLocal, String as_ipRemota
	)
	    throws B2BException
	{
		StopWatch lsw_watch;

		lsw_watch = Logger.getNewStopWatch();

		getBusiness()
			    .guardarIntervinientesAnotacion(
			    aa_anotacion, aa_anotacionPredioCiudadano, as_idSolicitud, as_idAnotacionPredio, as_usuario, as_ipRemota
			);

		Logger.log(
		    lsw_watch, "AntiguoSistemaBusiness", "guardarIntervinientesAnotacion", as_usuario, as_ipLocal, as_ipRemota,
		    null
		);
	}

	/**
	 * Inactivar informe resultados.
	 *
	 * @param as_idTurno de as id turno
	 * @param as_idDatosAntSistema de as id datos ant sistema
	 * @param as_userId de as user id
	 * @param as_localIpAddress de as local ip address
	 * @param as_ipRemote de as ip remote
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void inactivarInformeResultados(
	    String as_idTurno, String as_idDatosAntSistema, String as_userId, String as_localIpAddress, String as_ipRemote
	)
	    throws B2BException
	{
		StopWatch lsw_watch;

		lsw_watch = Logger.getNewStopWatch();

		getBusiness().inactivarInformeResultados(as_idTurno, as_idDatosAntSistema, as_userId, as_ipRemote);

		Logger.log(
		    lsw_watch, "AntiguoSistemaBusiness", "inactivarInformeResultados", as_userId, as_localIpAddress, as_ipRemote,
		    null
		);
	}

	/**
	 * Insert update datos ant sistema solicitud.
	 *
	 * @param adas_temp de adas temp
	 * @param as_idTurnoHistoria de as id turno historia
	 * @param as_observaciones de as observaciones
	 * @param as_userId de as user id
	 * @param as_localIp de as local ip
	 * @param as_remoteIp de as remote ip
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void insertUpdateDatosAntSistemaSolicitud(
	    DatosAntSistema adas_temp, String as_idTurnoHistoria, String as_observaciones, String as_userId,
	    String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch lsw_watch;

		lsw_watch = Logger.getNewStopWatch();

		getBusiness()
			    .insertUpdateDatosAntSistemaSolicitud(
			    adas_temp, as_idTurnoHistoria, as_observaciones, as_userId, as_remoteIp
			);

		Logger.log(
		    lsw_watch, "AntiguoSistemaBusiness", "insertUpdateDatosAntSistemaSolicitud", as_userId, as_localIp,
		    as_remoteIp, null
		);
	}

	/**
	 * Obtener datos ant sistema.
	 *
	 * @param as_idSolicitud de as id solicitud
	 * @param as_idUsuario de as id usuario
	 * @param as_localIp de as local ip
	 * @param as_remoteIp de as remote ip
	 * @return el valor de collection
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Collection<DatosAntSistema> obtenerDatosAntSistema(
	    String as_idSolicitud, String as_idUsuario, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch                   lsw_watch;
		Collection<DatosAntSistema> lcdas_return;

		lsw_watch     = Logger.getNewStopWatch();

		lcdas_return = getBusiness().obtenerDatosAntSistema(as_idSolicitud);

		Logger.log(
		    lsw_watch, "AntiguoSistemaBusiness", "obtenerDatosAntSistema", as_idUsuario, as_localIp, as_remoteIp,
		    lcdas_return
		);

		return lcdas_return;
	}

	/**
	 * Obtener detalles ant sistema.
	 *
	 * @param adas_dato de adas dato
	 * @param as_idUsuario de as id usuario
	 * @param as_localIp de as local ip
	 * @param as_remoteIp de as remote ip
	 * @return el valor de collection
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Collection<DetalleAntSistema> obtenerDetallesAntSistema(
	    DatosAntSistema adas_dato, String as_idUsuario, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch                     lsw_watch;
		Collection<DetalleAntSistema> lcdas_return;

		lsw_watch     = Logger.getNewStopWatch();

		lcdas_return = getBusiness().obtenerDetallesAntSistema(adas_dato);

		Logger.log(
		    lsw_watch, "AntiguoSistemaBusiness", "obtenerDetallesAntSistema", as_idUsuario, as_localIp, as_remoteIp,
		    lcdas_return
		);

		return lcdas_return;
	}

	/**
	 * Obtener documento ant sistema.
	 *
	 * @param adas_detalle de adas detalle
	 * @param as_idUsuario de as id usuario
	 * @param as_localIp de as local ip
	 * @param as_remoteIp de as remote ip
	 * @return el valor de documento
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Documento obtenerDocumentoAntSistema(
	    DetalleAntSistema adas_detalle, String as_idUsuario, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch lsw_watch;
		Documento lcdas_return;

		lsw_watch     = Logger.getNewStopWatch();

		lcdas_return = getBusiness().obtenerDocumentoAntSistema(adas_detalle);

		Logger.log(
		    lsw_watch, "AntiguoSistemaBusiness", "obtenerDocumentoAntSistema", as_idUsuario, as_localIp, as_remoteIp,
		    null
		);

		return lcdas_return;
	}

	/**
	 * Reordenar anotaciones.
	 *
	 * @param aa_anotacion de aa anotacion
	 * @param as_userId de as user id
	 * @param as_localIp de as local ip
	 * @param as_remoteIp de as remote ip
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void reordenarAnotaciones(Anotacion aa_anotacion, String as_userId, String as_localIp, String as_remoteIp)
	    throws B2BException
	{
		StopWatch lsw_watch;

		lsw_watch = Logger.getNewStopWatch();

		getBusiness().reordenarAnotaciones(aa_anotacion, as_userId, as_remoteIp);

		Logger.log(
		    lsw_watch, "AntiguoSistemaBusiness", "reordenarAnotaciones", as_userId, as_localIp, as_remoteIp, null
		);
	}

	/**
	 * Revisado ant sistema.
	 *
	 * @param adas_datosAntSistema de adas datos ant sistema
	 * @param acdasui_detallesSeleccionados de acdasui detalles seleccionados
	 * @param acsm_csm de acsm csm
	 * @param as_turnoHistoria de as turno historia
	 * @param as_turno de as turno
	 * @param as_userId de as user id
	 * @param ls_localIp de ls local ip
	 * @param as_remoteIp de as remote ip
	 * @return retorna <code>true</code> si se cumple la condicion, de lo contario retorna <code>false</code>
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public boolean revisadoAntSistema(
	    DatosAntSistema adas_datosAntSistema, Collection<DetalleAntSistemaUI> acdasui_detallesSeleccionados,
	    Collection<SolicitudMatricula> acsm_csm, String as_turnoHistoria, String as_turno, String as_userId,
	    String ls_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		boolean   lb_return;
		StopWatch lsw_watch;

		lsw_watch     = Logger.getNewStopWatch();

		lb_return = getBusiness()
				            .revisadoAntSistema(
				    adas_datosAntSistema, acdasui_detallesSeleccionados, acsm_csm, as_turnoHistoria, as_turno, as_userId,
				    as_remoteIp
				);

		Logger.log(lsw_watch, "AntiguoSistemaBusiness", "revisadoAntSistema", as_userId, ls_localIp, as_remoteIp, null);

		return lb_return;
	}

	/**
	 * Revisado ant sistema definitivo.
	 *
	 * @param adas_datosAntSistema de adas datos ant sistema
	 * @param as_turnoHistoria de as turno historia
	 * @param as_turno de as turno
	 * @param as_userId de as user id
	 * @param ls_localIp de ls local ip
	 * @param as_remoteIp de as remote ip
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void revisadoAntSistemaDefinitivo(
	    DatosAntSistema adas_datosAntSistema, String as_turnoHistoria, String as_turno, String as_userId,
	    String ls_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch lsw_watch;

		lsw_watch = Logger.getNewStopWatch();

		getBusiness()
			    .revisadoAntSistemaDefinitivo(adas_datosAntSistema, as_turnoHistoria, as_turno, as_userId, as_remoteIp);

		Logger.log(
		    lsw_watch, "AntiguoSistemaBusiness", "revisadoAntSistemaDefinitivo", as_userId, ls_localIp, as_remoteIp,
		    null
		);
	}

	/**
	 * Salvar anotaciones.
	 *
	 * @param aa_parametros de aa parametros
	 * @param as_usuario de as usuario
	 * @param as_ipLocal de as ip local
	 * @param as_ipRemota de as ip remota
	 * @return el valor de anotacion
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Anotacion salvarAnotaciones(
	    Anotacion aa_parametros, String as_usuario, String as_ipLocal, String as_ipRemota
	)
	    throws B2BException
	{
		Anotacion la_datos;
		StopWatch lsw_watch;

		lsw_watch     = Logger.getNewStopWatch();

		la_datos = getBusiness().salvarAnotaciones(aa_parametros, as_usuario, as_ipRemota);

		Logger.log(lsw_watch, "AntiguoSistemaBusiness", "salvarAnotaciones", as_usuario, as_ipLocal, as_ipRemota, null);

		return la_datos;
	}

	/**
	 * Salvar area predio.
	 *
	 * @param apr_accPredio de apr acc predio
	 * @param aap_areaPredio de aap area predio
	 * @param as_userId de as user id
	 * @param as_ipAddress de as ip address
	 * @param as_localIpAddress de as local ip address
	 * @return el valor de acc predio registro
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public AccPredioRegistro salvarAreaPredio(
	    AccPredioRegistro apr_accPredio, AreaPredio aap_areaPredio, String as_userId, String as_ipAddress,
	    String as_localIpAddress
	)
	    throws B2BException
	{
		StopWatch         lsw_watch;
		AccPredioRegistro lsb_return;

		lsw_watch     = Logger.getNewStopWatch();

		lsb_return = getBusiness()
				             .salvarAreaPredio(
				    apr_accPredio, aap_areaPredio, as_userId, as_ipAddress, as_localIpAddress
				);

		Logger.log(
		    lsw_watch, "AntiguoSistemaBusiness", "salvarAreaPredio", as_userId, as_localIpAddress, as_ipAddress, null
		);

		return lsb_return;
	}

	/**
	 * Salvar cabida Y linderos.
	 *
	 * @param llp_linderoPredio de llp lindero predio
	 * @param lacp_complementacionPredio de lacp complementacion predio
	 * @param ab_accionNueva de ab accion nueva
	 * @param lcp_complementacionPredio de lcp complementacion predio
	 * @param as_userId de as user id
	 * @param as_localIp de as local ip
	 * @param as_remoteIp de as remote ip
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void salvarCabidaYLinderos(
	    AccLinderoPredio llp_linderoPredio, AccComplementacionPredio lacp_complementacionPredio, boolean ab_accionNueva,
	    ComplementacionPredio lcp_complementacionPredio, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch lsw_watch;

		lsw_watch = Logger.getNewStopWatch();

		getBusiness()
			    .salvarCabidaYLinderos(
			    llp_linderoPredio, lacp_complementacionPredio, ab_accionNueva, lcp_complementacionPredio, as_userId,
			    as_remoteIp
			);

		Logger.log(
		    lsw_watch, "AntiguoSistemaBusiness", "salvarCabidaYLinderos", as_userId, as_localIp, as_remoteIp, null
		);
	}

	/* (non-Javadoc)
	 * @see com.bachue.snr.prosnr01.ejb.session.stateless.antiguo.sistema.AntiguoSistemaRemote#salvarCompletitudComplementacion(com.bachue.snr.prosnr01.model.sdb.acc.CompletitudComplementacion, java.lang.String, java.lang.String, java.lang.String)
	 */
	public boolean salvarCompletitudComplementacion(
	    CompletitudComplementacion cc_parametros, String as_circuloDestino, String as_usuario, String as_ipLocal,
	    String as_ipRemota
	)
	    throws B2BException
	{
		boolean   la_datos;
		StopWatch lsw_watch;

		lsw_watch     = Logger.getNewStopWatch();

		la_datos = getBusiness()
				           .salvarCompletitudComplementacion(cc_parametros, as_circuloDestino, as_usuario, as_ipRemota);

		Logger.log(
		    lsw_watch, "AntiguoSistemaBusiness", "salvarCompletitudComplementacion", as_usuario, as_ipLocal, as_ipRemota,
		    null
		);

		return la_datos;
	}

	/**
	 * Salvar datos basicos.
	 *
	 * @param adb_db de adb db
	 * @param as_userId de as user id
	 * @param as_remoteIp de as remote ip
	 * @return el valor de datos basicos
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public DatosBasicos salvarDatosBasicos(DatosBasicos adb_db, String as_userId, String as_remoteIp)
	    throws B2BException
	{
		StopWatch    lsw_watch;
		DatosBasicos lsb_return;

		lsw_watch     = Logger.getNewStopWatch();

		lsb_return = getBusiness().salvarDatosBasicos(adb_db, as_userId, as_remoteIp);

		Logger.log(lsw_watch, "AntiguoSistemaBusiness", "salvarDatosBasicos", as_userId, null, null, null);

		return lsb_return;
	}

	/**
	 * Salvar datos basicos ubicacion.
	 *
	 * @param adb_datosBasicos de adb datos basicos
	 * @param as_userId de as user id
	 * @param as_localIp de as local ip
	 * @param as_remoteIp de as remote ip
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void salvarDatosBasicosUbicacion(
	    DatosBasicos adb_datosBasicos, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch lsw_watch;

		lsw_watch = Logger.getNewStopWatch();

		getBusiness().salvarDatosBasicosUbicacion(adb_datosBasicos, as_userId, as_remoteIp);

		Logger.log(
		    lsw_watch, "AntiguoSistemaBusiness", "salvarDatosBasicosUbicacion", as_userId, as_localIp, as_remoteIp, null
		);
	}

	/**
	 * Salvar direccion predio.
	 *
	 * @param acddp_db de acddp db
	 * @param as_s de as s
	 * @param as_userId de as user id
	 * @param aapm_predio de aapm predio
	 * @param localIpAddress de local ip address
	 * @param as_ipRemote de as ip remote
	 * @return el valor de collection
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	@Override
	public Collection<DireccionDelPredio> salvarDireccionPredio(
	    Collection<DireccionDelPredio> acddp_db, String as_s, String as_userId, AccPredioRegistro aapm_predio,
	    String localIpAddress, String as_ipRemote
	)
	    throws B2BException
	{
		StopWatch                      lsw_watch;
		Collection<DireccionDelPredio> lcddp_return;

		lsw_watch     = Logger.getNewStopWatch();

		lcddp_return = getBusiness().salvarDireccionPredio(acddp_db, as_s, as_userId, aapm_predio, as_ipRemote);

		Logger.log(
		    lsw_watch, "AntiguoSistemaBusiness", "salvarDireccionPredio", null, localIpAddress, as_ipRemote, null
		);

		return lcddp_return;
	}

	/**
	 * Salvar proceso ant sistema.
	 *
	 * @param as_idTurnoHistoria de as id turno historia
	 * @param as_userId de as user id
	 * @param as_localIpAddress de as local ip address
	 * @param as_remoteIpAddress de as remote ip address
	 * @param as_observaciones de as observaciones
	 * @return retorna <code>true</code> si se cumple la condicion, de lo contario retorna <code>false</code>
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public boolean salvarProcesoAntSistema(
	    String as_idTurnoHistoria, String as_userId, String as_localIpAddress, String as_remoteIpAddress,
	    String as_observaciones
	)
	    throws B2BException
	{
		StopWatch lsw_watch;
		boolean   lcddp_return;

		lsw_watch        = Logger.getNewStopWatch();
		lcddp_return     = getBusiness()
				                   .salvarProcesoAntSistema(
				    as_idTurnoHistoria, as_userId, as_remoteIpAddress, as_observaciones
				);

		Logger.log(
		    lsw_watch, "AntiguoSistemaBusiness", "salvarProcesoAntSistema", as_userId, as_localIpAddress,
		    as_remoteIpAddress, null
		);

		return lcddp_return;
	}

	/**
	 * Salvar proceso ant sistema.
	 *
	 * @param as_idTurnoHistoria correspondiente al valor de as id turno historia
	 * @param as_userId correspondiente al valor de as user id
	 * @param as_localIpAddress correspondiente al valor de as local ip address
	 * @param as_remoteIpAddress correspondiente al valor de as remote ip address
	 * @param as_observaciones correspondiente al valor de as observaciones
	 * @param ab_solicitaComplementacionOtroCirculo correspondiente al valor de ab solicita complementacion otro circulo
	 * @return retorna <code>true</code> si se cumple la condicion, de lo contario retorna <code>false</code>
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public boolean salvarProcesoAntSistema(
	    String as_idTurnoHistoria, String as_userId, String as_localIpAddress, String as_remoteIpAddress,
	    String as_observaciones, boolean ab_solicitaComplementacionOtroCirculo
	)
	    throws B2BException
	{
		StopWatch lsw_watch;
		boolean   lcddp_return;

		lsw_watch        = Logger.getNewStopWatch();
		lcddp_return     = getBusiness()
				                   .salvarProcesoAntSistema(
				    as_idTurnoHistoria, as_userId, as_remoteIpAddress, as_observaciones,
				    ab_solicitaComplementacionOtroCirculo
				);

		Logger.log(
		    lsw_watch, "AntiguoSistemaBusiness", "salvarProcesoAntSistema", as_userId, as_localIpAddress,
		    as_remoteIpAddress, null
		);

		return lcddp_return;
	}

	/**
	 * Revisado ant sistema definitivo.
	 *
	 * @param adas_datosAntSistema de adas datos ant sistema
	 * @param as_turnoHistoria de as turno historia
	 * @param as_turno de as turno
	 * @param as_userId de as user id
	 * @param ls_localIp de ls local ip
	 * @param as_remoteIp de as remote ip
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void terminarTurnoHistoria(
	    long al_etapa, String as_idTurnoHistoria, long al_motivo, String as_userId, String as_remoteIp,
	    String ls_localIp
	)
	    throws B2BException
	{
		StopWatch lsw_watch;

		lsw_watch = Logger.getNewStopWatch();

		getBusiness().terminarTurnoHistoria(al_etapa, as_idTurnoHistoria, al_motivo, as_userId, as_remoteIp);

		Logger.log(
		    lsw_watch, "AntiguoSistemaBusiness", "terminarTurnoHistoria", as_userId, ls_localIp, as_remoteIp, null
		);
	}

	/**
	 * Update fecha inicial.
	 *
	 * @param ath_param de ath param
	 * @param as_userId de as user id
	 * @param as_localIp de as local ip
	 * @param as_remoteIp de as remote ip
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void updateFechaInicial(TurnoHistoria ath_param, String as_userId, String as_localIp, String as_remoteIp)
	    throws B2BException
	{
		StopWatch lsw_watch;

		lsw_watch = Logger.getNewStopWatch();

		getBusiness().updateFechaInicial(ath_param, as_userId, as_localIp, as_remoteIp);

		Logger.log(lsw_watch, "AntiguoSistemaBean", "updateFechaInicial", as_userId, as_localIp, as_remoteIp, null);
	}

	/**
	 * Validar acto ejecutoria.
	 *
	 * @param as_idTipoDoc de as id tipo doc
	 * @return retorna <code>true</code> si se cumple la condicion, de lo contario retorna <code>false</code>
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public boolean validarActoEjecutoria(String as_idTipoDoc)
	    throws B2BException
	{
		StopWatch lsw_watch;
		boolean   lb_boolean;

		lsw_watch     = Logger.getNewStopWatch();

		lb_boolean = getBusiness().validarActoDocumentoEjecutoria(as_idTipoDoc);

		Logger.log(lsw_watch, "AntiguoSistemaBusiness", "validarActoEjecutoria", null, null, null, null);

		return lb_boolean;
	}

	/**
	 * Validar datos ant sistema iguales.
	 *
	 * @param adas_temp de adas temp
	 * @param as_idTurnoHistoria de as id turno historia
	 * @return retorna <code>true</code> si se cumple la condicion, de lo contario retorna <code>false</code>
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public boolean validarDatosAntSistemaIguales(DatosAntSistema adas_temp, String as_idTurnoHistoria)
	    throws B2BException
	{
		boolean   lb_return;
		StopWatch lsw_watch;

		lsw_watch     = Logger.getNewStopWatch();
		lb_return     = getBusiness().validarDatosAntSistemaIguales(adas_temp, as_idTurnoHistoria);

		Logger.log(lsw_watch, "AntiguoSistemaBusiness", "validarDatosAntSistemaIguales", null, null, null, null);

		return lb_return;
	}

	/**
	 * Validar docuentos crear matricula.
	 *
	 * @param acapr_capr de acapr capr
	 * @param as_idTurno de as id turno
	 * @return el valor de map
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Map<String, Boolean> validarDocuentosCrearMatricula(
	    Collection<AccPredioRegistro> acapr_capr, String as_idTurno
	)
	    throws B2BException
	{
		Map<String, Boolean> lhm_return;
		StopWatch            lsw_watch;

		lsw_watch      = Logger.getNewStopWatch();
		lhm_return     = getBusiness().validarDocuentosCrearMatricula(acapr_capr, as_idTurno);

		Logger.log(lsw_watch, "AntiguoSistemaBusiness", "validarDocuentosCrearMatricula", null, null, null, null);

		return lhm_return;
	}

	/**
	 * Validar si es devolucion.
	 *
	 * @param as_idTurnoHistoria de as id turno historia
	 * @param as_userId de as user id
	 * @param as_localIp de as local ip
	 * @param as_remoteIp de as remote ip
	 * @return retorna <code>true</code> si se cumple la condicion, de lo contario retorna <code>false</code>
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public boolean validarSiEsDevolucion(
	    String as_idTurnoHistoria, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		boolean   lb_return;
		StopWatch lsw_watch;

		lsw_watch     = Logger.getNewStopWatch();
		lb_return     = getBusiness().validarSiEsDevolucion(as_idTurnoHistoria);

		Logger.log(
		    lsw_watch, "AntiguoSistemaBusiness", "validarSiEsDevolucion", as_userId, as_localIp, as_remoteIp, null
		);

		return lb_return;
	}

	/**
	 * Retorna el valor de business.
	 *
	 * @return el valor de business
	 */
	private AntiguoSistemaBusiness getBusiness()
	{
		if(iasb_business == null)
			iasb_business = new AntiguoSistemaBusiness();

		return iasb_business;
	}
}
