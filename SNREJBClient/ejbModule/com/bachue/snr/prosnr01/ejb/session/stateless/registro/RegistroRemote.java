package com.bachue.snr.prosnr01.ejb.session.stateless.registro;

import com.b2bsg.common.exception.B2BException;

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

import java.io.IOException;

import java.sql.SQLException;

import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.ejb.Remote;


/**
 * Interface que contiene todos las propiedades RegistroRemote.
 *
 * @author  Julián David Vaca Rodriguez
 * Fecha de Creacion: 20/02/2019
 */
@Remote
public interface RegistroRemote
{
	/**
	 * Actualizar descripcion solicitud.
	 *
	 * @param as_parametros de as parametros
	 * @param as_userId de as user id
	 * @param as_remoteIp de as remote ip
	 * @param as_localIp de as local ip
	 * @return el valor de registro
	 * @throws B2BException de b 2 B exception
	 */
	public Solicitud actualizaTipoReproduccion(
	    Solicitud as_parametros, String as_userId, String as_remoteIp, String as_localIp
	)
	    throws B2BException;

	/**
	 * Actualizar acto para antiguo sistema.
	 *
	 * @param aa_acto de aa acto
	 * @param as_userId de as user id
	 * @param as_localIp de as local ip
	 * @param as_remoteIp de as remote ip
	 * @throws B2BException de b 2 B exception
	 */
	public void actualizarActoParaAntiguoSistema(Acto aa_acto, String as_userId, String as_localIp, String as_remoteIp)
	    throws B2BException;

	/**
	 * Actualizar descripcion solicitud.
	 *
	 * @param ar_parametros de ar parametros
	 * @param as_userId de as user id
	 * @return el valor de registro
	 * @throws B2BException de b 2 B exception
	 */
	public Registro actualizarDescripcionSolicitud(Registro ar_parametros, boolean as_acto0463, String as_userId)
	    throws B2BException;

	/**
	 * Actualizar solicitud nueva entrada.
	 *
	 * @param as_solicitud de as solicitud
	 * @param at_turno de at turno
	 * @return el valor de solicitud
	 * @throws B2BException de b 2 B exception
	 */
	public Solicitud actualizarSolicitudNuevaEntrada(Solicitud as_solicitud, Turno at_turno)
	    throws B2BException;

	/**
	 * Actualizar sub proceso solicitud.
	 *
	 * @param solicitud de solicitud
	 * @param userId de user id
	 * @throws B2BException de b 2 B exception
	 */
	public void actualizarSubProcesoSolicitud(Solicitud solicitud, String userId)
	    throws B2BException;

	/**
	 * Agregar comentario.
	 *
	 * @param as_solicitud de as solicitud
	 * @throws B2BException de b 2 B exception
	 */
	public void agregarComentario(Solicitud as_solicitud)
	    throws B2BException;

	/**
	 * Area total terreno.
	 *
	 * @param ar_registro de ar registro
	 * @param as_userId de as user id
	 * @param as_localIp de as local ip
	 * @param as_remoteIp de as remote ip
	 * @return el valor de double
	 * @throws B2BException de b 2 B exception
	 */
	public double areaTotalTerreno(Registro ar_registro, String as_userId, String as_localIp, String as_remoteIp)
	    throws B2BException;

	/**
	 * Borrar datos detalles ant sistema.
	 *
	 * @param accdas_datosBorrar de accdas datos borrar
	 * @param as_idSolicitud de as id solicitud
	 * @param as_userId de as user id
	 * @param as_localIp de as local ip
	 * @param as_remoteIp de as remote ip
	 * @throws B2BException de b 2 B exception
	 */
	public void borrarDatosDetallesAntSistema(
	    Collection<Collection<DatosAntSistema>> accdas_datosBorrar, String as_idSolicitud, String as_userId,
	    String as_localIp, String as_remoteIp
	)
	    throws B2BException;

	/**
	 * Buscar persona usuario logueado.
	 *
	 * @param as_userId de as user id
	 * @param as_localIp de as local ip
	 * @param as_remoteIp de as remote ip
	 * @return el valor de persona
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Persona buscarPersonaUsuarioLogueado(String as_userId, String as_localIp, String as_remoteIp)
	    throws B2BException;

	/**
	 * Buscar personas por solicitud interviniente.
	 *
	 * @param ap_parametros de ap parametros
	 * @param as_userId de as user id
	 * @param as_localIp de as local ip
	 * @param as_remoteIp de as remote ip
	 * @return el valor de Registro
	 * @throws B2BException de b 2 B exception
	 */
	public Registro buscarPersonasPorSolicitudInterviniente(
	    Registro ap_parametros, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException;

	/**
	 * Metodo que se encarga de consultar el turno por id.
	 *
	 * @param as_idTurno Argumento de tipo <code>String</code> que contiene el id del turno a consultar.
	 * @param as_userId Argumento de tipo <code>String</code> que contiene el id del usuario que realiza la operación.
	 * @param as_ipLocal Argumento de tipo <code>String</code> que contiene la ip local del usuario que realiza la operación.
	 * @param as_ipRemota Argumento de tipo <code>String</code> que contiene la ip remota del usuario que realiza la operación.
	 * @return Retorna un objeto de tipo <code>Turno</code> que contiene los resultados que coincidieron con los criterios de búsqueda.
	 * @throws B2BException Señala que se ha generado una excepción.
	 */
	public Turno buscarTurnoPorId(String as_idTurno, String as_userId, String as_ipLocal, String as_ipRemota)
	    throws B2BException;

	/**
	 * Campos por criterio.
	 *
	 * @param as_s de as s
	 * @param as_userId de as user id
	 * @param as_ipLocal de as ip local
	 * @param as_ipRemota de as ip remota
	 * @return el valor de collection
	 * @throws B2BException de b 2 B exception
	 */
	public Collection<CamposConsulta> camposPorCriterio(
	    Solicitud as_s, String as_userId, String as_ipLocal, String as_ipRemota
	)
	    throws B2BException;

	/**
	 * Cargar datos predio acto.
	 *
	 * @param as_solicitud de as solicitud
	 * @return el valor de collection
	 * @throws B2BException de b 2 B exception
	 */
	public Collection<String> cargarDatosPredioActo(Solicitud as_solicitud)
	    throws B2BException;

	/**
	 * Cargar direccion completa.
	 *
	 * @param acc_panel de acc panel
	 * @param as_userId de as user id
	 * @param as_ipLocal de as ip local
	 * @param as_ipRemota de as ip remota
	 * @return el valor de string
	 * @throws B2BException de b 2 B exception
	 */
	public String cargarDireccionCompleta(
	    CamposConsulta acc_panel, String as_userId, String as_ipLocal, String as_ipRemota
	)
	    throws B2BException;

	/**
	 * Método encargado de cargar los documentos de la solicitud.
	 *
	 * @param as_proceso String que contiene el nombre del proceso.
	 * @param as_idSolicitud String que contiene el id de la solicitud.
	 * @param as_userId String que contiene el id del usuario.
	 * @param as_ipLocal String que contiene la ip del servidor.
	 * @param as_ipRemota String que contiene la ip del usuario.
	 * @return Colección que contiene los documentos consultados.
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Collection<DocumentosSalida> cargarDocumentosSolicitud(
	    String as_proceso, String as_idSolicitud, String as_userId, String as_ipLocal, String as_ipRemota
	)
	    throws B2BException;

	/**
	 * Cargar excel campos criterios.
	 *
	 * @param acc_camposConsulta de acc campos consulta
	 * @param as_userId de as user id
	 * @param as_ipLocal de as ip local
	 * @param as_ipRemota de as ip remota
	 * @return el valor de collection
	 * @throws B2BException de b 2 B exception
	 */
	public Collection<CamposConsulta> cargarExcelCamposCriterios(
	    Collection<CamposConsulta> acc_camposConsulta, String as_userId, String as_ipLocal, String as_ipRemota
	)
	    throws B2BException;

	/**
	 * Retorna el valor del objeto de Collection para cargar los campos en excel.
	 *
	 * @param accc_camposConsulta correspondiente al valor del tipo de objeto Collection<CamposConsulta>
	 * @param ab_copias Argumento de tipo <code>boolean</code> que indica si es un proceso de copias <code>true</code> de la contrario <code>false</code>.
	 * @param as_userId de as user id
	 * @param as_ipLocal correspondiente al valor del tipo de objeto String
	 * @param as_ipRemota de as ip remota
	 * @return devuelve el valor de Collection de CamposConsulta
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public Collection<CamposConsulta> cargarExcelCamposCriteriosCopias(
	    Collection<CamposConsulta> accc_camposConsulta, boolean ab_copias, String as_userId, String as_ipLocal,
	    String as_ipRemota
	)
	    throws B2BException;

	/**
	 * Metodo encargado de cargar la información capturada en expedicón de copias para antiguo sistema en buscar antiguo sistema.
	 *
	 * @param al_idTurnoHistoria Argumento de tipo <code>Long</code> que contiene el id_turno_historia de buscar antiguo sistema.
	 * @param as_userId Argumento de tipo <code>String</code> que contiene el id del usuario que realiza la operación.
	 * @param as_ipLocal de as ip local
	 * @param as_ipRemota de as ip remota
	 * @return Objeto de <code>Long</code> que contiene los valores a cargar en buscar antiguo sistema.
	 * @throws B2BException Señala que se ha generado una excepción.
	 */
	public BuscarAntiguoSistema cargarInformacionAntiguoSistema(
	    Long al_idTurnoHistoria, String as_userId, String as_ipLocal, String as_ipRemota
	)
	    throws B2BException;

	/**
	 * Metodo de transacciones con la base de datos con el fin de encontrar los campos a presentar de acuerdo a los criterios de busqueda en la tabla CRITERIO BUSQUEDA y DETALLE CRITERIO_BUSQUEDA.
	 *
	 * @param adc_parametros Argumento de tipo <code>DigitalizacionCopias</code> que contiene los criterios necesarios para realizar la consulta.
	 * @param as_userId Argumento de tipo <code>String</code> que contiene el id del usuario que realiza la operación.
	 * @param as_ipLocal de as ip local
	 * @param as_ipRemota de as ip remota
	 * @return Retorna la información cargada para digitalización de copias.
	 * @throws B2BException Señala que se ha generado una excepción
	 */
	public DigitalizacionCopias cargarInformacionDigitalizacionCopias(
	    DigitalizacionCopias adc_parametros, String as_userId, String as_ipLocal, String as_ipRemota
	)
	    throws B2BException;

	/**
	 * Metodo encargado de cargar el nir de un turno digitado por pantalla.
	 *
	 * @param acc_panel Argumento de tipo CamposConsulta que contiene los campos a concatenar.
	 * @param as_userId de as user id
	 * @param as_ipLocal de as ip local
	 * @param as_ipRemota de as ip remota
	 * @return Variable de tipo <code>String</code> que contiene el nir de un turno digitado por pantalla.
	 * @throws B2BException Señala que se ha generado una excepción
	 */
	public String cargarNir(CamposConsulta acc_panel, String as_userId, String as_ipLocal, String as_ipRemota)
	    throws B2BException;

	/**
	 * Método encargado de cargar las direcciones de residencia y correspondencia.
	 *
	 * @param as_idSolicitud Variable de tipo String que contiene el id de la solicitud actual.
	 * @return Objeto que contiene las direcciones consultadas.
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Registro cargarPersonaDireccion(String as_idSolicitud)
	    throws B2BException;

	/**
	 * Compare areas.
	 *
	 * @param as_idCirculo de as id circulo
	 * @param al_idMatricula de al id matricula
	 * @param ad_sumAreas de ad sum areas
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void compareAreas(String as_idCirculo, Long al_idMatricula, double ad_sumAreas)
	    throws B2BException;

	/**
	 * Consula matriculas asociadas.
	 *
	 * @param as_solicitud de as solicitud
	 * @return el valor de solicitud matricula
	 * @throws B2BException de b 2 B exception
	 */
	public SolicitudMatricula consulaMatriculasAsociadas(Solicitud as_solicitud)
	    throws B2BException;

	/**
	 * Consulta area predio.
	 *
	 * @param aaap_areaPredioArg de aaap area predio arg
	 * @param as_userId de as user id
	 * @param ab_accion de ab accion
	 * @return el valor de acc area UI
	 * @throws B2BException de b 2 B exception
	 */
	public AccAreaUI consultaAreaPredio(AccAreaPredio aaap_areaPredioArg, String as_userId, boolean ab_accion)
	    throws B2BException;

	/**
	 * Consulta en tabla.
	 *
	 * @param as_codigoActo de as codigo acto
	 * @return verdadero, si se cumple la condicion, de lo contario retorna falso
	 * @throws B2BException de b 2 B exception
	 */
	public boolean consultaEnTabla(String as_codigoActo)
	    throws B2BException;

	/**
	 * Consulta retomar solicitud.
	 *
	 * @param as_tipoDocumento de as tipo documento
	 * @param as_numeroDocumento de as numero documento
	 * @param as_userId de as user id
	 * @return el valor de collection
	 * @throws B2BException de b 2 B exception
	 * @throws SQLException de SQL exception
	 */
	public Collection<TramiteSolicitud> consultaRetomarSolicitud(
	    String as_tipoDocumento, String as_numeroDocumento, String as_userId
	)
	    throws B2BException, SQLException;

	/**
	 * Consultar detalles agregados.
	 *
	 * @param acdb_cdb de acdb cdb
	 * @param as_userId de as user id
	 * @param as_ipLocal de as ip local
	 * @param as_ipRemota de as ip remota
	 * @return el valor de collection
	 * @throws B2BException de b 2 B exception
	 */
	public Collection<CriteriosDeBusqueda> consultarDetallesAgregados(
	    CriteriosDeBusqueda acdb_cdb, String as_userId, String as_ipLocal, String as_ipRemota
	)
	    throws B2BException;

	/**
	 * Consultar info persona entidad exenta.
	 *
	 * @param ap_parametros de ap parametros
	 * @param as_userId de as user id
	 * @param as_localIp de as local ip
	 * @param as_remoteIp de as remote ip
	 * @return el valor de registro
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Registro consultarInfoPersonaEntidadExenta(
	    Persona ap_parametros, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException;

	/**
	 * Consultar matriculas para detalle.
	 *
	 * @param adas_detalle de adas detalle
	 * @param as_userId de as user id
	 * @param as_localIp de as local ip
	 * @param as_remoteIp de as remote ip
	 * @return el valor de collection
	 * @throws B2BException de b 2 B exception
	 */
	public Collection<String> consultarMatriculasParaDetalle(
	    DetalleAntSistema adas_detalle, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException;

	/**
	 * Metodo encargado de consultar documentos del OWCC para antiguo sistema.
	 *
	 * @param abas_parametros Argumento de tipo <code>BuscarAntiguoSistema</code> que contiene los criterios a consultar.
	 * @param as_userId Argumento de tipo <code>String</code> que contiene el id del usuario que realiza la operación.
	 * @param as_ipLocal de as ip local
	 * @param as_ipRemota de as ip remota
	 * @return el valor de collection
	 * @throws B2BException Señala que se ha generado una excepción
	 */
	public Collection<DocumentoOWCC> consultarOWCCAntiguoSistemaCopias(
	    BuscarAntiguoSistema abas_parametros, String as_userId, String as_ipLocal, String as_ipRemota
	)
	    throws B2BException;

	/**
	 * Testamentos por documento.
	 *
	 * @param as_s de as s
	 * @param as_userId de as user id
	 * @param as_ipLocal de as ip local
	 * @param as_ipRemota de as ip remota
	 * @return el valor de collection
	 * @throws B2BException de b 2 B exception
	 */
	public Collection<Testamento> consultarTestamentos(
	    Testamento as_s, String as_userId, String as_ipLocal, String as_ipRemota
	)
	    throws B2BException;

	/**
	 * Crear pdf registro.
	 *
	 * @param ar_parametros de ar parametros
	 * @param as_userId de as user id
	 * @return el valor de byte[]
	 * @throws B2BException de b 2 B exception
	 */
	public byte[] crearPdfRegistro(Registro ar_parametros, String as_userId)
	    throws B2BException;

	/**
	 * Metodo encargado de consultar documentos del OWCC para antiguo sistema.
	 *
	 * @param abas_parametros Argumento de tipo <code>BuscarAntiguoSistema</code> que contiene los criterios a consultar.
	 * @param as_userId de as user id
	 * @param as_ipLocal Argumento de tipo <code>String</code> que contiene la ip local del usuario que realiza la operación.
	 * @param as_ipRemota de as ip remota
	 * @return el valor de string
	 * @throws B2BException Señala que se ha generado una excepción
	 */
	public String crearYObtenerUrlDigitalizacion(
	    BuscarAntiguoSistema abas_parametros, String as_userId, String as_ipLocal, String as_ipRemota
	)
	    throws B2BException;

	/**
	 * Delete acto.
	 *
	 * @param asi_parametros de asi parametros
	 * @throws B2BException de b 2 B exception
	 */
	public void deleteActo(com.bachue.snr.prosnr01.model.registro.Acto asi_parametros)
	    throws B2BException;

	/**
	 * Delete actos hijos.
	 *
	 * @param ap_parametros de ap parametros
	 * @throws B2BException de b 2 B exception
	 */
	public void deleteActosHijos(com.bachue.snr.prosnr01.model.registro.Acto ap_parametros)
	    throws B2BException;

	/**
	 * Delete by solicitud circulo matricula.
	 *
	 * @param asm_sm de asm sm
	 * @param as_userId de as user id
	 * @param as_localIp de as local ip
	 * @param as_remoteIp de as remote ip
	 * @throws B2BException de b 2 B exception
	 */
	public void deleteBySolicitudCirculoMatricula(
	    SolicitudMatricula asm_sm, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException;

	/**
	 * Delete causales correccion.
	 *
	 * @param lsc_solicitudCorreccion de lsc solicitud correccion
	 * @throws B2BException de b 2 B exception
	 * @throws SQLException de SQL exception
	 */
	public void deleteCausalesCorreccion(SolicitudCorreccion lsc_solicitudCorreccion)
	    throws B2BException, SQLException;

	/**
	 * Delete detalle.
	 *
	 * @param acdb_cdb de acdb cdb
	 * @param as_userId de as user id
	 * @param as_ipLocal de as ip local
	 * @param as_ipRemota de as ip remota
	 * @throws B2BException de b 2 B exception
	 */
	public void deleteDetalle(CriteriosDeBusqueda acdb_cdb, String as_userId, String as_ipLocal, String as_ipRemota)
	    throws B2BException;

	/**
	 * Delete matricula segregacion.
	 *
	 * @param ams_matriculaSegregacion de ams matricula segregacion
	 * @throws B2BException de b 2 B exception
	 */
	public void deleteMatriculaSegregacion(MatriculaSegregacion ams_matriculaSegregacion)
	    throws B2BException;

	/**
	 * Método para buscar plantilla por idConstante.
	 *
	 * @param as_idConstante Constante a buscar
	 * @param userId de user id
	 * @param localIpAddress de local ip address
	 * @param remoteIpAddress de remote ip address
	 * @return Constantes contenedora de plantilla
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Constantes descargarPlantilla(
	    String as_idConstante, String userId, String localIpAddress, String remoteIpAddress
	)
	    throws B2BException;

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
	    throws B2BException;

	/**
	 * Detalle turnos recepcion.
	 *
	 * @param ad_parametros de ad parametros
	 * @param ab_consultaDocumento de ab consulta documento
	 * @param as_userId de as user id
	 * @param as_localIp de as local ip
	 * @param remoteIp de remote ip
	 * @return el valor de collection
	 * @throws B2BException de b 2 B exception
	 */
	public Collection<Turno> detalleTurnosRecepcion(
	    Documento ad_parametros, boolean ab_consultaDocumento, String as_userId, String as_localIp, String remoteIp
	)
	    throws B2BException;

	/**
	 * Documento nueva entrada.
	 *
	 * @param lr_registro de lr registro
	 * @return el valor de registro
	 * @throws B2BException de b 2 B exception
	 */
	public Registro documentoNuevaEntrada(Registro lr_registro)
	    throws B2BException;

	/**
	 * Eliminar actos reproduccion constancia.
	 *
	 * @param ar_parametros de ar parametros
	 * @param as_userId de as user id
	 * @return el valor de registro
	 * @throws B2BException de b 2 B exception
	 */
	public Registro eliminarActosReproduccionConstancia(Registro ar_parametros, String as_userId)
	    throws B2BException;

	/**
	 * Eliminar datos ant sistema.
	 *
	 * @param adas_parametros de adas parametros
	 * @param as_userId de as user id
	 * @param as_ipLocal de as ip local
	 * @param as_ipRemota de as ip remota
	 * @return el valor de collection
	 * @throws B2BException de b 2 B exception
	 */
	public Collection<DatosAntSistema> eliminarDatosAntSistema(
	    DatosAntSistema adas_parametros, String as_userId, String as_ipLocal, String as_ipRemota
	)
	    throws B2BException;

	/**
	 * Eliminar detalle ant sistema.
	 *
	 * @param adas_parametros de adas parametros
	 * @param as_userId de as user id
	 * @param as_ipLocal de as ip local
	 * @param as_ipRemota de as ip remota
	 * @throws B2BException de b 2 B exception
	 */
	public void eliminarDetalleAntSistema(
	    DetalleAntSistema adas_parametros, String as_userId, String as_ipLocal, String as_ipRemota
	)
	    throws B2BException;

	/**
	 * Eliminar interviniente.
	 *
	 * @param asi_parametros de asi parametros
	 * @param as_userId de as user id
	 * @throws B2BException de b 2 B exception
	 */
	public void eliminarInterviniente(SolicitudInterviniente asi_parametros, String as_userId)
	    throws B2BException;

	/**
	 * Eliminar matricula segregacion por solicitud.
	 *
	 * @param as_idSolicitud de as id solicitud
	 * @param as_userId de as user id
	 * @param as_localIp de as local ip
	 * @param as_remoteIp de as remote ip
	 * @throws B2BException de b 2 B exception
	 */
	public void eliminarMatriculaSegregacionPorSolicitud(
	    String as_idSolicitud, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException;

	/**
	 * Enlistar por mapa intervinientes.
	 *
	 * @param acsi_intervinientesActuales de acsi intervinientes actuales
	 * @param asi_intervinienteIngresado de asi interviniente ingresado
	 * @param as_userId de as user id
	 * @param as_localIp de as local ip
	 * @param as_remoteIp de as remote ip
	 * @return el valor de collection
	 * @throws B2BException de b 2 B exception
	 */
	public Collection<SolicitudInterviniente> enlistarPorMapaIntervinientes(
	    Collection<SolicitudInterviniente> acsi_intervinientesActuales,
	    SolicitudInterviniente             asi_intervinienteIngresado, long al_etapaActual, String as_userId,
	    String                             as_localIp, String as_remoteIp
	)
	    throws B2BException;

	/**
	 * Find acc area predio.
	 *
	 * @param aaap_accAreaPredio de aaap acc area predio
	 * @return el valor de acc area predio
	 * @throws B2BException de b 2 B exception
	 */
	public AccAreaPredio findAccAreaPredio(AccAreaPredio aaap_accAreaPredio)
	    throws B2BException;

	/**
	 * Find acto by.
	 *
	 * @param aa_acto de aa acto
	 * @return el valor de acto
	 * @throws B2BException de b 2 B exception
	 */
	public Acto findActoBy(Acto aa_acto)
	    throws B2BException;

	/**
	 * Find acto englobes.
	 *
	 * @param aa_acto de aa acto
	 * @param ahmss_codigos de ahmss codigos
	 * @return el valor de acto
	 * @throws B2BException de b 2 B exception
	 */
	public Acto findActoEnglobes(Acto aa_acto, HashMap<String, String> ahmss_codigos)
	    throws B2BException;

	/**
	 * Find actos by id.
	 *
	 * @param ap_parametros de ap parametros
	 * @param as_userId de as user id
	 * @param ab_accion de ab accion
	 * @param ab_idCirculo de ab id circulo
	 * @return el valor de collection
	 * @throws B2BException de b 2 B exception
	 */
	public Collection<TipoActo> findActosById(
	    Acto ap_parametros, String as_userId, boolean ab_accion, boolean ab_idCirculo
	)
	    throws B2BException;

	/**
	 * Find actos by solicitud.
	 *
	 * @param idSolicitud de id solicitud
	 * @return el valor de collection
	 * @throws B2BException de b 2 B exception
	 */
	public Collection<com.bachue.snr.prosnr01.model.registro.Acto> findActosBySolicitud(String idSolicitud)
	    throws B2BException;

	/**
	 * Find actos by solicitud circulo turno.
	 *
	 * @param as_idSolicitud de id solicitud
	 * @param as_idTurno de id turno
	 * @return el valor de collection
	 * @throws B2BException de b 2 B exception
	 */
	public Collection<com.bachue.snr.prosnr01.model.registro.Acto> findActosBySolicitudCirculoTurno(
	    String as_idSolicitud, String as_idTurno
	)
	    throws B2BException;

	/**
	 * Find actos by solicitud id circulo.
	 *
	 * @param as_idSolicitud de as id solicitud
	 * @param as_idCirculo de as id circulo
	 * @param as_userId de as user id
	 * @param as_localIp de as local ip
	 * @param as_remoteIp de as remote ip
	 * @return el valor de collection
	 * @throws B2BException de b 2 B exception
	 */
	public Collection<com.bachue.snr.prosnr01.model.registro.Acto> findActosBySolicitudIdCirculo(
	    String as_idSolicitud, String as_idCirculo, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException;

	/**
	 * Find actos hijos by id.
	 *
	 * @param ap_parametros de ap parametros
	 * @param as_userId de as user id
	 * @return el valor de collection
	 * @throws B2BException de b 2 B exception
	 */
	public Collection<com.bachue.snr.prosnr01.model.registro.Acto> findActosHijosById(
	    com.bachue.snr.prosnr01.model.registro.Acto ap_parametros, String as_userId
	)
	    throws B2BException;

	/**
	 * Find actos validar area terreno.
	 *
	 * @return el valor de linked hash map
	 * @throws B2BException de b 2 B exception
	 */
	public Map<String, Boolean> findActosValidarAreaTerreno()
	    throws B2BException;

	/**
	 * Find all causales.
	 *
	 * @param lsc_solicitudCorreccion de lsc solicitud correccion
	 * @return el valor de collection
	 * @throws B2BException de b 2 B exception
	 */
	public Collection<CausalCorreccion> findAllCausales(SolicitudCorreccion lsc_solicitudCorreccion)
	    throws B2BException;

	/**
	 * Find all documentales.
	 *
	 * @param as_idActo de as id acto
	 * @param as_codigo de as codigo
	 * @param ab_b de ab b
	 * @param as_idSolicitud de as id solicitud
	 * @return el valor de collection
	 * @throws B2BException de b 2 B exception
	 */
	public Collection<TipoDocumental> findAllDocumentales(
	    String as_idActo, String as_codigo, boolean ab_b, String as_idSolicitud
	)
	    throws B2BException;

	/**
	 * Find all registro anotacion prohibicion by cir mat.
	 *
	 * @param as_s de as s
	 * @param as_idCirculo de as id circulo
	 * @param al_idMatricula de al id matricula
	 * @param as_userId de as user id
	 * @param as_localIp de as local ip
	 * @param as_remoteIp de as remote ip
	 * @return el valor de registro anotacion prohibicion
	 * @throws B2BException de b 2 B exception
	 */
	public Collection<RegistroAnotacionProhibicion> findAllRegistroAnotacionProhibicionByCirMat(
	    Solicitud as_s, String as_idCirculo, long al_idMatricula, String as_userId, String as_localIp,
	    String as_remoteIp
	)
	    throws B2BException;

	/**
	 * Find all zona registral.
	 *
	 * @return el valor de collection
	 * @throws B2BException de b 2 B exception
	 */
	public Collection<ZonaRegistral> findAllZonaRegistral()
	    throws B2BException;

	/**
	 * Find anotacion predio by radicacion.
	 *
	 * @param asi_parametros de asi parametros
	 * @param as_userId de as user id
	 * @return el valor de collection
	 * @throws B2BException de b 2 B exception
	 */
	public Collection<DataReproduccionConstancia> findAnotacionPredioByRadicacion(
	    AnotacionPredioDireccion asi_parametros, String as_userId
	)
	    throws B2BException;

	/**
	 * Find anotacion predio by radicacion.
	 *
	 * @param ap_parametros de ap parametros
	 * @param as_userId de as user id
	 * @param documento de documento
	 * @return el valor de collection
	 * @throws B2BException de b 2 B exception
	 */
	public Collection<AnotacionPredioDireccion> findAnotacionPredioByRadicacion(
	    AnotacionPredioDireccion ap_parametros, String as_userId, DocumentoConstancia documento
	)
	    throws B2BException;

	/**
	 * Find ant sistema.
	 *
	 * @param idSolicitud de id solicitud
	 * @return el valor de datos ant sistema
	 * @throws B2BException de b 2 B exception
	 */
	public DatosAntSistema findAntSistema(String idSolicitud)
	    throws B2BException;

	/**
	 * Find area predio.
	 *
	 * @param aap_areaPredio de aap area predio
	 * @return el valor de area predio
	 * @throws B2BException de b 2 B exception
	 */
	public AreaPredio findAreaPredio(AreaPredio aap_areaPredio)
	    throws B2BException;

	/**
	 * Find area terreno by matricula.
	 *
	 * @param ls_matricula de ls matricula
	 * @return el valor de double
	 * @throws B2BException de b 2 B exception
	 */
	public double findAreaTerrenoByMatricula(String ls_matricula)
	    throws B2BException;

	/**
	 * Find by id matricula grabacion.
	 *
	 * @param adas_datos de adas datos
	 * @param as_userId de as user id
	 * @param as_localIp de as local ip
	 * @param as_remoteIp de as remote ip
	 * @return el valor de collection
	 * @throws B2BException de b 2 B exception
	 */
	public Collection<DatosAntSistema> findByIdMatriculaGrabacion(
	    DatosAntSistema adas_datos, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException;

	/**
	 * Find circulo registral acto antiguo sistema.
	 *
	 * @param as_solicitud de as solicitud
	 * @return el valor de zona registral
	 * @throws B2BException de b 2 B exception
	 */
	public ZonaRegistral findCirculoRegistralActoAntiguoSistema(Solicitud as_solicitud)
	    throws B2BException;

	/**
	 * Find circulo registral by id.
	 *
	 * @param ap_parametros de ap parametros
	 * @param as_userId de as user id
	 * @return el valor de circulo registral
	 * @throws B2BException de b 2 B exception
	 */
	public CirculoRegistral findCirculoRegistralById(CirculoRegistral ap_parametros, String as_userId)
	    throws B2BException;

	/**
	 * Find completitud documental by id turno.
	 *
	 * @param as_idTurno de as id turno
	 * @param as_userId de as user id
	 * @param as_localIp de as local ip
	 * @param as_remoteIp de as remote ip
	 * @return el valor de collection
	 * @throws B2BException de b 2 B exception
	 */
	public Collection<AccCompletitudDocumental> findCompletitudDocumentalByIdTurno(
	    String as_idTurno, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException;

	/**
	 * Find completitud documental by id turno.
	 *
	 * @param as_idTurno de as id turno
	 * @param as_userId de as user id
	 * @param as_localIp de as local ip
	 * @param as_remoteIp de as remote ip
	 * @return el valor de collection
	 * @throws B2BException de b 2 B exception
	 */
	public Collection<AccCompletitudDocumental> findCompletitudDocumentalByIdTurnoSolicitud(
	    String as_idTurno, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException;

	/**
	 * Find constante.
	 *
	 * @param ac_actosSegregacion de ac actos segregacion
	 * @return el valor de constantes
	 * @throws B2BException de b 2 B exception
	 */
	public Constantes findConstante(Constantes ac_actosSegregacion)
	    throws B2BException;

	/**
	 * Find correo by id persona.
	 *
	 * @param lpce_correo de lpce correo
	 * @return el valor de persona correo electronico
	 * @throws B2BException de b 2 B exception
	 */
	public PersonaCorreoElectronico findCorreoByIdPersona(PersonaCorreoElectronico lpce_correo)
	    throws B2BException;

	/**
	 * Find datos zona registral by circulo.
	 *
	 * @param as_idCirculo de as id circulo
	 * @param as_userId de as user id
	 * @param as_localIp de as local ip
	 * @param as_remoteIp de as remote ip
	 * @return el valor de zona registral
	 * @throws B2BException de b 2 B exception
	 */
	public ZonaRegistral findDatosZonaRegistralByCirculo(
	    String as_idCirculo, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException;

	/**
	 * Find detalle acto.
	 *
	 * @param as_idActoDb de as id acto db
	 * @param aoa_datosActo de aoa datos acto
	 * @param as_userId de as user id
	 * @param as_localIp de as local ip
	 * @param as_remoteIp de as remote ip
	 * @return el valor de com.bachue.snr.prosnr 01 .model.registro. acto
	 * @throws B2BException de b 2 B exception
	 */
	public com.bachue.snr.prosnr01.model.registro.Acto findDetalleActo(
	    String as_idActoDb, com.bachue.snr.prosnr01.model.registro.Acto aoa_datosActo, String as_userId,
	    String as_localIp, String as_remoteIp
	)
	    throws B2BException;

	/**
	 * Find detalles ant sistema.
	 *
	 * @param as_idDatosAntSis de as id datos ant sis
	 * @param as_userId de as user id
	 * @param as_localIp de as local ip
	 * @param as_remoteIp de as remote ip
	 * @return el valor de collection
	 * @throws B2BException de b 2 B exception
	 */
	public Collection<DetalleAntSistema> findDetallesAntSistema(
	    String as_idDatosAntSis, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException;

	/**
	 * Find direccion by id persona.
	 *
	 * @param apd_pd de apd pd
	 * @param as_tipoDir de as tipo dir
	 * @return el valor de persona direccion
	 * @throws B2BException de b 2 B exception
	 */
	public PersonaDireccion findDireccionByIdPersona(PersonaDireccion apd_pd, String as_tipoDir)
	    throws B2BException;

	/**
	 * Find direccion predio by id.
	 *
	 * @param ap_parametros de ap parametros
	 * @param as_userId de as user id
	 * @return el valor de direccion predio
	 * @throws B2BException de b 2 B exception
	 */
	public DireccionPredio findDireccionPredioById(DireccionPredio ap_parametros, String as_userId)
	    throws B2BException;

	/**
	 * Find documentales correcciones.
	 *
	 * @return el valor de collection
	 * @throws B2BException de b 2 B exception
	 */
	public Collection<TipoDocumental> findDocumentalesCorrecciones()
	    throws B2BException;

	/**
	 * Find documento.
	 *
	 * @param idSolicitud de id solicitud
	 * @return el valor de documento
	 * @throws B2BException de b 2 B exception
	 */
	public Documento findDocumento(String idSolicitud)
	    throws B2BException;

	/**
	 * Find documentos constancia.
	 *
	 * @param ap_parametros de ap parametros
	 * @param as_userId de as user id
	 * @return el valor de collection
	 * @throws B2BException de b 2 B exception
	 */
	public Collection<DocumentoConstancia> findDocumentosConstancia(
	    DocumentoConstancia ap_parametros, String as_userId
	)
	    throws B2BException;

	/**
	 * find entidad externa.
	 *
	 * @param aaee_param Objeto que contiene la información para realizar la consulta.
	 * @param as_userId Variable que contiene el id del usuario que realiza la consulta.
	 * @param as_localIp Variable que contiene la ip del servidor.
	 * @param as_remoteIp Variable que contiene la ip del usuario que realiza la consulta.
	 * @return Objeto que contiene los datos consultados.
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Registro findEntidadExterna(
	    AccEntidadExterna aaee_param, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException;

	/**
	 * Find estado predio by id.
	 *
	 * @param ap_parametros de ap parametros
	 * @param as_userId de as user id
	 * @return el valor de estado predio
	 * @throws B2BException de b 2 B exception
	 */
	public EstadoPredio findEstadoPredioById(EstadoPredio ap_parametros, String as_userId)
	    throws B2BException;

	/**
	 * Find gravamen pendiente.
	 *
	 * @param as_circulo de as circulo
	 * @param as_matricula de as matricula
	 * @param as_userId de as user id
	 * @param as_localIp de as local ip
	 * @param remoteIp de remote ip
	 * @return el valor de collection
	 * @throws B2BException de b 2 B exception
	 */
	public Collection<GravamenPendiente> findGravamenPendiente(
	    String as_circulo, String as_matricula, String as_userId, String as_localIp, String remoteIp
	)
	    throws B2BException;

	/**
	 * Find id circulo.
	 *
	 * @param as_nirGenerado de as nir generado
	 * @param as_userId de as user id
	 * @param as_ipLocal de as ip local
	 * @param as_ipRemota de as ip remota
	 * @return el valor de string
	 * @throws B2BException de b 2 B exception
	 */
	public String findIdCirculo(String as_nirGenerado, String as_userId, String as_ipLocal, String as_ipRemota)
	    throws B2BException;

	/**
	 * Find id solicitud by id turno.
	 *
	 * @param as_idTurno de as id turno
	 * @param as_userId de as user id
	 * @param as_localIp de as local ip
	 * @param as_remoteIp de as remote ip
	 * @return el valor de string
	 * @throws B2BException de b 2 B exception
	 */
	public String findIdSolicitudByIdTurno(String as_idTurno, String as_userId, String as_localIp, String as_remoteIp)
	    throws B2BException;

	/**
	 * Find matricula segregacion by id solicitud.
	 *
	 * @param as_idSolicitud de as id solicitud
	 * @param ls_idMatricula de ls id matricula
	 * @return el valor de collection
	 * @throws B2BException de b 2 B exception
	 */
	public Collection<MatriculaSegregacion> findMatriculaSegregacionByIdSolicitud(
	    String as_idSolicitud, String ls_idMatricula
	)
	    throws B2BException;

	/**
	 * Find oficina origen.
	 *
	 * @param aoo_oo de aoo oo
	 * @return el valor de oficina origen
	 * @throws B2BException de b 2 B exception
	 */
	public OficinaOrigen findOficinaOrigen(OficinaOrigen aoo_oo)
	    throws B2BException;

	/**
	 * Find person by document.
	 *
	 * @param ap_parametros de ap parametros
	 * @param as_userId de as user id
	 * @return el valor de registro
	 * @throws B2BException de b 2 B exception
	 */
	public Registro findPersonByDocument(Registro ap_parametros, String as_userId)
	    throws B2BException;

	/**
	 * Find person by document.
	 *
	 * @param ap_parametros de ap parametros
	 * @param as_userId de as user id
	 * @return el valor de collection
	 * @throws B2BException de b 2 B exception
	 */
	public Collection<Persona> findPersonByDocument(Persona ap_parametros, String as_userId)
	    throws B2BException;

	/**
	 * Find persona by id persona.
	 *
	 * @param lp_p de lp p
	 * @param userId de user id
	 * @return el valor de persona
	 * @throws B2BException de b 2 B exception
	 */
	public Persona findPersonaByIdPersona(Persona lp_p, String userId)
	    throws B2BException;

	/**
	 * Find predio registro by circulo matricula.
	 *
	 * @param apr_pr de apr pr
	 * @return el valor de predio registro
	 * @throws B2BException de b 2 B exception
	 */
	public PredioRegistro findPredioRegistroByCirculoMatricula(PredioRegistro apr_pr)
	    throws B2BException;

	/**
	 * Find predio registro by id.
	 *
	 * @param ap_parametros de ap parametros
	 * @param as_userId de as user id
	 * @return el valor de predio registro
	 * @throws B2BException de b 2 B exception
	 */
	public PredioRegistro findPredioRegistroById(PredioRegistro ap_parametros, String as_userId)
	    throws B2BException;

	/**
	 * Find prohibiciones VPM.
	 *
	 * @param apv_pv de apv pv
	 * @param as_userId de as user id
	 * @param as_localIp de as local ip
	 * @param remoteIp de remote ip
	 * @return el valor de collection
	 * @throws B2BException de b 2 B exception
	 */
	public Collection<ProhibicionVPM> findProhibicionesVPM(
	    ProhibicionVPM apv_pv, String as_userId, String as_localIp, String remoteIp
	)
	    throws B2BException;

	/**
	 * Find registro anotacion prohibicion by cir mat.
	 *
	 * @param as_s de as s
	 * @param as_idCirculo de as id circulo
	 * @param al_idMatricula de al id matricula
	 * @param as_userId de as user id
	 * @param as_localIp de as local ip
	 * @param as_remoteIp de as remote ip
	 * @return el valor de registro anotacion prohibicion
	 * @throws B2BException de b 2 B exception
	 */
	public RegistroAnotacionProhibicion findRegistroAnotacionProhibicionByCirMat(
	    Solicitud as_s, String as_idCirculo, long al_idMatricula, String as_userId, String as_localIp,
	    String as_remoteIp
	)
	    throws B2BException;

	/**
	 * Find registro anotacion prohibicion by turno.
	 *
	 * @param at_turno de at turno
	 * @param as_userId de as user id
	 * @param as_localIp de as local ip
	 * @param as_remoteIp de as remote ip
	 * @return el valor de collection
	 * @throws B2BException de b 2 B exception
	 */
	public Collection<RegistroAnotacionProhibicion> findRegistroAnotacionProhibicionByTurno(
	    Turno at_turno, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException;

	/**
	 * Find solicitud asociada.
	 *
	 * @param as_solicitud de as solicitud
	 * @return el valor de solicitud asociada
	 * @throws B2BException de b 2 B exception
	 */
	public SolicitudAsociada findSolicitudAsociada(Solicitud as_solicitud)
	    throws B2BException;

	/**
	 * Método encargado de consultar una solicitud asociada con base a un id solicitud, proceso y subproceso.
	 *
	 * @param as_solicitud Argumento de tipo <code>Solicitud</code> que contiene los criterios necesarios para realizar la consulta.
	 * @param as_userId Argumento de tipo <code>String</code> que contiene el id del usuario que realiza la consulta.
	 * @param as_localIp Argumento de tipo <code>String</code> que contiene la ip local del usuario que realiza la consulta.
	 * @param as_remoteIp Argumento de tipo <code>String</code> que contiene la ip remota del usuario que realiza la consulta.
	 * @return el valor de solicitud asociada
	 * @throws B2BException Se genera cuando se produce una excepción.
	 */
	public SolicitudAsociada findSolicitudAsociadaProcesoSubProceso(
	    Solicitud as_solicitud, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException;

	/**
	 * Find solicitud by id.
	 *
	 * @param ap_parametros de ap parametros
	 * @param as_userId de as user id
	 * @return el valor de solicitud
	 * @throws B2BException de b 2 B exception
	 */
	public Solicitud findSolicitudById(Solicitud ap_parametros, String as_userId)
	    throws B2BException;

	/**
	 * Find solicitud interviniente by solicitud.
	 *
	 * @param asi_parametros de asi parametros
	 * @param as_userId de as user id
	 * @return el valor de collection
	 * @throws B2BException de b 2 B exception
	 */
	public Collection<SolicitudInterviniente> findSolicitudIntervinienteBySolicitud(
	    SolicitudInterviniente asi_parametros, String as_userId
	)
	    throws B2BException;

	/**
	 * Find telefono by id persona.
	 *
	 * @param lpt_telFijo de lpt tel fijo
	 * @param tipoTel de tipo tel
	 * @return el valor de persona telefono
	 * @throws B2BException de b 2 B exception
	 */
	public PersonaTelefono findTelefonoByIdPersona(PersonaTelefono lpt_telFijo, String tipoTel)
	    throws B2BException;

	/**
	 * find Testamento By Documento.
	 *
	 * @param adc_param de adc param
	 * @param as_userId de as user id
	 * @param as_localIp de as local ip
	 * @param as_remoteIp de as remote ip
	 * @return el valor de direccion predio
	 * @throws B2BException de b 2 B exception
	 */
	public Testamento findTestamentoByDocumento(
	    DocumentoConstancia adc_param, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException;

	/**
	 * Find tipo acto by id.
	 *
	 * @param ata_parametros de ata parametros
	 * @return el valor de tipo acto
	 * @throws B2BException de b 2 B exception
	 */
	public TipoActo findTipoActoById(TipoActo ata_parametros)
	    throws B2BException;

	/**
	 * Find tipo adqui.
	 *
	 * @return el valor de collection
	 * @throws B2BException de b 2 B exception
	 */
	public Collection<TipoAdquisicion> findTipoAdqui()
	    throws B2BException;

	/**
	 * Find tipo documento publico by constante.
	 *
	 * @return el valor de collection
	 * @throws B2BException de b 2 B exception
	 */
	public Collection<TipoDocumentoPublico> findTipoDocumentoPublicoByConstante()
	    throws B2BException;

	/**
	 * Método encargado de consultar los tipos documento para traslado.
	 *
	 * @return Colección de documentos.
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Collection<TipoDocumentoPublico> findTipoDocumentoPublicoTraslado()
	    throws B2BException;

	/**
	 * Find turno by nir.
	 *
	 * @param at_turno de at turno
	 * @return el valor de collection
	 * @throws B2BException de b 2 B exception
	 */
	public Collection<Turno> findTurnoByNir(Turno at_turno)
	    throws B2BException;

	/**
	 * Find turnos bloqueo predio.
	 *
	 * @param lpr_predioR de lpr predio R
	 * @return el valor de collection
	 * @throws B2BException de b 2 B exception
	 */
	public Collection<String> findTurnosBloqueoPredio(PredioRegistro lpr_predioR)
	    throws B2BException;

	/**
	 * Find turnos bloqueo predio.
	 *
	 * @param lpr_predioR de lpr predio R
	 * @return el valor de collection
	 * @throws B2BException de b 2 B exception
	 */
	public Collection<String> findTurnosBloqueoPredio(DatosAntSistema lpr_predioR)
	    throws B2BException;

	/**
	 * Find zona registral by id.
	 *
	 * @param ap_parametros de ap parametros
	 * @param as_userId de as user id
	 * @return el valor de zona registral
	 * @throws B2BException de b 2 B exception
	 */
	public ZonaRegistral findZonaRegistralById(ZonaRegistral ap_parametros, String as_userId)
	    throws B2BException;

	/**
	 * Generar recibo caja.
	 *
	 * @param ap_parametros de ap parametros
	 * @param ab_b de ab b
	 * @param as_userId de as user id
	 * @param as_localIp de as local ip
	 * @param as_remoteIp de as remote ip
	 * @return el valor de registro
	 * @throws B2BException de b 2 B exception
	 */
	public Registro generarReciboCaja(
	    Registro ap_parametros, boolean ab_b, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException;

	/**
	 * Generar recibo liquidacion.
	 *
	 * @param ap_parametros de ap parametros
	 * @param ab_b de ab b
	 * @param as_userId de as user id
	 * @param as_localIp de as local ip
	 * @param as_remoteIp de as remote ip
	 * @return el valor de registro
	 * @throws B2BException de b 2 B exception
	 */
	public Registro generarReciboLiquidacion(
	    Registro ap_parametros, boolean ab_b, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException;

	/**
	 * Método encargado de generar la solicitud de grabación de matrículas para correcciones.
	 *
	 * @param ar_data Objeto que contiene la información del proceso.
	 * @param as_userId Variable que contiene el id del usuario.
	 * @param as_remoteIp Variable que contiene la ip del usuario.
	 * @param as_localIp Variable que contiene la ip del servidor.
	 * @return la información de la solicitud creada.
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 * @throws IOException Objeto de tipo IOException, se produce cuando se encuentra algun error de tipo I/O.
	 */
	public Registro generarSolicitudGrabacionMatriculasCorrecciones(
	    Registro ar_data, String as_userId, String as_remoteIp, String as_localIp
	)
	    throws B2BException, IOException;

	/**
	 * Guardar criterios.
	 *
	 * @param acb_cb de acb cb
	 * @param as_userId de as user id
	 * @param as_ipLocal de as ip local
	 * @param as_ipRemota de as ip remota
	 * @return el valor de criterios de busqueda
	 * @throws B2BException de b 2 B exception
	 */
	public CriteriosDeBusqueda guardarCriterios(
	    CriteriosDeBusqueda acb_cb, String as_userId, String as_ipLocal, String as_ipRemota
	)
	    throws B2BException;

	/**
	 * Guardar criterios campos.
	 *
	 * @param acb_camposConsulta de acb campos consulta
	 * @param as_userId de as user id
	 * @param as_ipLocal de as ip local
	 * @param as_ipRemota de as ip remota
	 * @throws B2BException de b 2 B exception
	 */
	public void guardarCriteriosCampos(
	    CamposConsulta acb_camposConsulta, String as_userId, String as_ipLocal, String as_ipRemota
	)
	    throws B2BException;

	/**
	 * Metodo encargado de guardar los criterios seleccionados y consultar documentos del OWCC.
	 *
	 * @param acc_camposConsulta Argumento de tipo Collection que contiene los criterios a guardar.
	 * @param ab_guardarCriterios Argumento de tipo boolean que determina si se debe guardar los criterios de búsqueda o no.
	 * @param as_userId Argumento de tipo String que contiene el usuario que realiza la operación.
	 * @param as_ipLocal de as ip local
	 * @param as_ipRemota Argumento de tipo String que contiene el la ip desde donde se realiza la operación.
	 * @return el valor de campos consulta
	 * @throws B2BException Señala que se ha generado una excepción
	 */
	public CamposConsulta guardarCriteriosYConsultarCopias(
	    Collection<CamposConsulta> acc_camposConsulta, Solicitud as_solicitud, boolean ab_guardarCriterios,
	    boolean ab_traerSolicitudCopias, String as_userId, String as_ipLocal, String as_ipRemota
	)
	    throws B2BException;

	/**
	 * Guardar datos ant sistema.
	 *
	 * @param adas_param de adas param
	 * @param as_userId de as user id
	 * @param as_localIp de as local ip
	 * @param as_remoteIp de as remote ip
	 * @param ab_guardar de ab guardar
	 * @return el valor de datos ant sistema
	 * @throws B2BException de b 2 B exception
	 */
	public DatosAntSistema guardarDatosAntSistema(
	    DatosAntSistema adas_param, String as_userId, String as_localIp, String as_remoteIp, boolean ab_guardar
	)
	    throws B2BException;

	/**
	 * Guardar detalles ant sistema.
	 *
	 * @param adas_param de adas param
	 * @param as_userId de as user id
	 * @param as_localIp de as local ip
	 * @param as_remoteIp de as remote ip
	 * @param ab_guardar de ab guardar
	 * @return el valor de detalle ant sistema
	 * @throws B2BException de b 2 B exception
	 */
	public DetalleAntSistema guardarDetallesAntSistema(
	    DetalleAntSistema adas_param, String as_userId, String as_localIp, String as_remoteIp, boolean ab_guardar
	)
	    throws B2BException;

	/**
	 * Guardar fecha ejecutoria acto.
	 *
	 * @param as_idSolicitud de as id solicitud
	 * @param ad_fechaEjecutoria de ad fecha ejecutoria
	 * @param as_userId de as user id
	 * @param as_remoteIp de as remote ip
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void guardarFechaEjecutoriaActo(
	    String as_idSolicitud, Date ad_fechaEjecutoria, String as_userId, String as_remoteIp
	)
	    throws B2BException;

	/**
	 * Método encargado de guardar la información de las matrículas a trasladar.
	 *
	 * @param actm_matriculas Contiene la información de las matrículas a guardar.
	 * @param acst_soportes Contiene la información de los documentos a guardar.
	 * @param as_solicitud Contiene la información de la solicitud.
	 * @param as_userId Contiene la información del usuario.
	 * @param as_localIp Contiene la información de la ip servidor.
	 * @param as_remoteIp Contiene la información de la ip del usuario.
	 * @throws B2BException Señala que se ha generado una excepción.
	 */
	public void guardarMatriculasTraslado(
	    Collection<TrasladoMatricula> actm_matriculas, Collection<SoporteTraslado> acst_soportes, Solicitud as_solicitud,
	    String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException;

	/**
	 * Metodo encargado de guardar en solicitud copias y preliquidar copias para antiguo sistema.
	 *
	 * @param abas_parametros Argumento de tipo <code>BuscarAntiguoSistema</code> que contiene los criterios a guardar y liquidar.
	 * @param as_userId Argumento de tipo <code>String</code> que contiene el id del usuario que realiza la operación.
	 * @param as_ipLocal Argumento de tipo <code>String</code> que contiene la ip local del usuario que realiza la operación.
	 * @param as_ipRemota de as ip remota
	 * @throws B2BException Señala que se ha generado una excepción
	 */
	public void guardarSolicitudCopiasPreliquidar(
	    BuscarAntiguoSistema abas_parametros, String as_userId, String as_ipLocal, String as_ipRemota
	)
	    throws B2BException;

	/**
	 * Salvar Testamento.
	 *
	 * @param ast_testamentom de ast testamentom
	 * @param as_userId de as user id
	 * @param as_ipLocal de as ip local
	 * @param as_ipRemota de as ip remota
	 * @return el valor de documento
	 * @throws B2BException de b 2 B exception
	 */
	public Testamento guardarTestamento(
	    SolicitudTestamento ast_testamentom, String as_userId, String as_ipLocal, String as_ipRemota
	)
	    throws B2BException;

	/**
	 * Guardar los tipos documentales para cada acto del circulo especificaco.
	 *
	 * @param at_turno de turno con la informacion a guardar
	 * @param as_userId de as user id
	 * @param as_localIp de as local ip
	 * @param as_remoteIp de as remote ip
	 * @return el valor de turno
	 * @throws B2BException de b 2 B exception
	 */
	public Turno guardarTiposDocImpuestoGobernacion(
	    Turno at_turno, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException;

	/**
	 * Metodo que se encarga de guardar los tipos documentales enviados como argumento.
	 *
	 * @param aci_completitudDocumental Colección de imagenes que contienen los tipos documentales que deben ser salvadas en las tablas de negocio.
	 * @param aso_solicitud Objeto que contiene los datos de la solicitud creada.
	 * @param as_constanteApoderado de as constante apoderado
	 * @param as_constanteTercero de as constante tercero
	 * @param as_localIp Ip publica del equipo donde se ejecuta el proceso.
	 * @param as_remoteIp Ip del equipo donde se ejecuta el proceso.
	 * @param as_userId Usuario que realiza las acciones en la aplicación.
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see Registro
	 */
	public void guardarTiposDocumentales(
	    Collection<AccCompletitudDocumental> aci_completitudDocumental, Solicitud aso_solicitud,
	    String as_constanteApoderado, String as_constanteTercero, String as_localIp, String as_remoteIp,
	    String as_userId
	)
	    throws B2BException;

	/**
	 * Guardar tipos documentales.
	 *
	 * @param as_idSolicitud de as id solicitud
	 * @param acctd_td de acctd td
	 * @param alid_acto de alid acto
	 * @param as_userId de as user id
	 * @param as_codigo de as codigo
	 * @param ab_procesoCorrecciones Argumento de tipo <code>boolean</code> que determina si es un proceso de correcciones.
	 * @throws B2BException de b 2 B exception
	 */
	public void guardarTiposDocumentales(
	    String as_idSolicitud, Collection<TipoDocumental> acctd_td, String alid_acto, String as_userId, String as_codigo,
	    boolean ab_procesoCorrecciones
	)
	    throws B2BException;

	/**
	 * Ingresar intervinientes solicitud masivo.
	 *
	 * @param acsi_intervinientes de acsi intervinientes
	 * @param as_solicitud de as solicitud
	 * @param as_userId de as user id
	 * @param as_localIp de as local ip
	 * @param as_remoteIp de as remote ip
	 * @throws B2BException de b 2 B exception
	 */
	public void ingresarIntervinientesSolicitudMasivo(
	    Collection<SolicitudInterviniente> acsi_intervinientes, Solicitud as_solicitud, String as_userId,
	    String as_localIp, String as_remoteIp
	)
	    throws B2BException;

	/**
	 * Insertar datos ant sistema grabacion matricula.
	 *
	 * @param ldas_datosAntSistema de ldas datos ant sistema
	 * @param as_userId de as user id
	 * @param as_ipLocal de as ip local
	 * @param as_ipRemota de as ip remota
	 * @throws B2BException de b 2 B exception
	 */
	public void insertarDatosAntSistemaGrabacionMatricula(
	    DatosAntSistema ldas_datosAntSistema, String as_userId, String as_ipLocal, String as_ipRemota
	)
	    throws B2BException;

	/**
	 * Insertar imagenes consulta indices.
	 *
	 * @param aci_ci de aci ci
	 * @param as_s de as s
	 * @param as_localIp de as local ip
	 * @param as_remoteIp de as remote ip
	 * @param as_userId de as user id
	 * @return el valor de registro
	 * @throws B2BException de b 2 B exception
	 */
	public Registro insertarImagenesConsultaIndices(
	    Collection<Imagenes> aci_ci, Solicitud as_s, String as_localIp, String as_remoteIp, String as_userId
	)
	    throws B2BException;

	/**
	 * Insertar interviniente copia.
	 *
	 * @param acsi_interviniente de acsi interviniente
	 * @throws B2BException de b 2 B exception
	 */
	public void insertarIntervinienteCopia(SolicitudInterviniente acsi_interviniente)
	    throws B2BException;

	/**
	 * Limpiar datos del predio.
	 *
	 * @param as_solicitud de as solicitud
	 * @throws B2BException de b 2 B exception
	 */
	public void limpiarDatosDelPredio(Solicitud as_solicitud)
	    throws B2BException;

	/**
	 * Max consecutivo consulta detalle.
	 *
	 * @param acb_camposConsulta de acb campos consulta
	 * @param as_userId de as user id
	 * @param as_ipLocal de as ip local
	 * @param as_ipRemota de as ip remota
	 * @return el valor de long
	 * @throws B2BException de b 2 B exception
	 */
	public long maxConsecutivoConsultaDetalle(
	    CamposConsulta acb_camposConsulta, String as_userId, String as_ipLocal, String as_ipRemota
	)
	    throws B2BException;

	/**
	 * Mensaje embargo vigente.
	 *
	 * @param as_idActoColumn de as id acto column
	 * @param as_idCirculoPrincipal de as id circulo principal
	 * @param al_idMatriculaPrincipal de al id matricula principal
	 * @param ab_anotacionCancelada de ab anotacion cancelada
	 * @param as_localIpAddress de as local ip address
	 * @param as_remoteIpAddress de as remote ip address
	 * @param as_idConstante de as id constante
	 * @return verdadero, si se cumple la condicion, de lo contario retorna falso
	 * @throws B2BException de b 2 B exception
	 */
	public boolean mensajeEmbargoVigente(
	    String as_idActoColumn, String as_idCirculoPrincipal, Long al_idMatriculaPrincipal,
	    boolean ab_anotacionCancelada, String as_localIpAddress, String as_remoteIpAddress, String as_idConstante
	)
	    throws B2BException;

	/**
	 * Metodo encargado de validar si la información capturada en expedicón de copias es diferente en buscar antiguo sistema.
	 *
	 * @param abas_parametros Argumento de tipo <code>BuscarAntiguoSistema</code> que contiene los criterios a validar.
	 * @param as_userId Argumento de tipo <code>String</code> que contiene el id del usuario que realiza la operación.
	 * @param as_ipLocal Argumento de tipo <code>String</code> que contiene la ip local desde donde se realiza la operación.
	 * @param as_ipRemota de as ip remota
	 * @throws B2BException Señala que se ha generado una excepción.
	 */
	public void modificarDatosAntiguoSistema(
	    BuscarAntiguoSistema abas_parametros, String as_userId, String as_ipLocal, String as_ipRemota
	)
	    throws B2BException;

	/**
	 * Mostrar cargue intervinientes.
	 *
	 * @param as_idNaturalezaJuridica de as id naturaleza juridica
	 * @param as_userId de as user id
	 * @param as_localIp de as local ip
	 * @param as_remoteIp de as remote ip
	 * @return verdadero, si se cumple la condicion, de lo contario retorna falso
	 * @throws B2BException de b 2 B exception
	 */
	public boolean mostrarCargueIntervinientes(
	    String as_idNaturalezaJuridica, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException;

	/**
	 * Testamentos por documento.
	 *
	 * @param as_s de as s
	 * @return el valor del testamento
	 * @throws B2BException de b 2 B exception
	 */
	public Testamento obtenerTestamentoAnterior(Collection<Testamento> as_s)
	    throws B2BException;

	/**
	 * Pertenece acto cancelacion.
	 *
	 * @param as_idActo de as id acto
	 * @param as_userId de as user id
	 * @param as_localIp de as local ip
	 * @param as_remoteIp de as remote ip
	 * @return verdadero, si se cumple la condicion, de lo contario retorna falso
	 * @throws B2BException de b 2 B exception
	 */
	public boolean perteneceActoCancelacion(String as_idActo, String as_userId, String as_localIp, String as_remoteIp)
	    throws B2BException;

	/**
	 * Pertenece acto medida cautelar.
	 *
	 * @param as_idActo de as id acto
	 * @param as_userId de as user id
	 * @param as_localIp de as local ip
	 * @param as_remoteIp de as remote ip
	 * @return verdadero, si se cumple la condicion, de lo contario retorna falso
	 * @throws B2BException de b 2 B exception
	 */
	public boolean perteneceActoMedidaCautelar(
	    String as_idActo, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException;

	/**
	 * Recibos impuesto.
	 *
	 * @param aos_os de aos os
	 * @param as_userId de as user id
	 * @param as_localIp de as local ip
	 * @param as_remoteIp de as remote ip
	 * @return el valor de solicitud
	 * @throws B2BException de b 2 B exception
	 */
	public Solicitud recibosImpuesto(Solicitud aos_os, String as_userId, String as_localIp, String as_remoteIp)
	    throws B2BException;

	/**
	 * Requiere cancelacion embargo.
	 *
	 * @param aap_ap de aap ap
	 * @param as_userId de as user id
	 * @param as_localIp de as local ip
	 * @param as_remoteIp de as remote ip
	 * @return verdadero, si se cumple la condicion, de lo contario retorna falso
	 * @throws B2BException de b 2 B exception
	 */
	public boolean requiereCancelacionEmbargo(
	    AnotacionPredio aap_ap, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException;

	/**
	 * Revision desistimiento.
	 *
	 * @param act_ct de act ct
	 * @param as_idProceso de as id proceso
	 * @param as_solicitud de as solicitud
	 * @param as_userId de as user id
	 * @param as_localIp de as local ip
	 * @param remoteIp de remote ip
	 * @return el valor de string
	 * @throws B2BException de b 2 B exception
	 */
	public String revisionDesistimiento(
	    Collection<Turno> act_ct, String as_idProceso, Solicitud as_solicitud, String as_userId, String as_localIp,
	    String remoteIp
	)
	    throws B2BException;

	/**
	 * Salvar causales correccion.
	 *
	 * @param acsc_correcciones de acsc correcciones
	 * @param as_idSolicitud de as id solicitud
	 * @param as_matricula de as matricula
	 * @param ab_segregacion de ab segregacion
	 * @throws B2BException de b 2 B exception
	 */
	public void salvarCausalesCorreccion(
	    Collection<SolicitudCorreccion> acsc_correcciones, String as_idSolicitud, String as_matricula,
	    boolean ab_segregacion
	)
	    throws B2BException;

	/**
	 * Método encargado de salvar los datos de copias para una solicitud.
	 *
	 * @param acc_camposConsulta Argumento de tipo <code>CamposConsulta</code> que contiene los criterios necesarios para salvar el proceso de copias.
	 * @param as_solicitud objeto necesario para realizar la operación de copias para una solicitud.
	 * @param as_userId correspondiente al valor del tipo de objeto String
	 * @param as_localIp de as local ip
	 * @param as_remoteIp correspondiente al valor del tipo de objeto String
	 * @return retorna <code>true</code> si se cumple la condicion, de lo contario retorna <code>false</code>
	 * @throws B2BException error si ocurre una excepción
	 */
	public boolean salvarDatosCopias(
	    CamposConsulta acc_camposConsulta, Solicitud as_solicitud, String as_userId, String as_localIp,
	    String as_remoteIp
	)
	    throws B2BException;

	/**
	 * Salvar documento.
	 *
	 * @param ad_parametros de ad parametros
	 * @param aso_solicitud de aso solicitud
	 * @param as_userId de as user id
	 * @param as_ipLocal de as ip local
	 * @param as_ipRemota de as ip remota
	 * @return el valor de documento
	 * @throws B2BException de b 2 B exception
	 */
	public Documento salvarDocumento(
	    Documento ad_parametros, Solicitud aso_solicitud, String as_userId, String as_ipLocal, String as_ipRemota
	)
	    throws B2BException;

	/**
	 * Salvar interesado.
	 *
	 * @param ar_parametros de ar parametros
	 * @param as_userId de as user id
	 * @return el valor de registro
	 * @throws B2BException de b 2 B exception
	 */
	public Registro salvarInteresado(Registro ar_parametros, String as_userId)
	    throws B2BException;

	/**
	 * Salvar interviniente.
	 *
	 * @param ar_parametros de ar parametros
	 * @param as_userId de as user id
	 * @param ab_siNotificaCorr de ab si notifica corr
	 * @param as_localIp de as local ip
	 * @param as_remoteIp de as remote ip
	 * @param ab_vieneDeRegistro de ab viene de registro
	 * @return el valor de registro
	 * @throws B2BException de b 2 B exception
	 */
	public Registro salvarInterviniente(
	    Registro ar_parametros, String as_userId, boolean ab_siNotificaCorr, String as_localIp, String as_remoteIp,
	    boolean ab_vieneDeRegistro
	)
	    throws B2BException;

	/**
	 * Salvar predio registro.
	 *
	 * @param lca_tmp de lca tmp
	 * @param string de string
	 * @return el valor de predio registro
	 * @throws B2BException de b 2 B exception
	 */
	public PredioRegistro salvarPredioRegistro(Collection<PredioRegistro> lca_tmp, String string)
	    throws B2BException;

	/**
	 * Salvar predios con tramite.
	 *
	 * @param acdpr_predioRegistro de acdpr predio registro
	 * @param ab_validacionActos700 de ab validacion actos 700
	 * @param acgp_gravamenes de acgp gravamenes
	 * @param acpv_prohibiciones de acpv prohibiciones
	 * @param as_userId de as user id
	 * @param as_solicitud de as solicitud
	 * @param amss_actosParciales de amss actos parciales
	 * @param as_localIp de as local ip
	 * @param as_remoteIp de as remote ip
	 * @throws B2BException de b 2 B exception
	 */
	public void salvarPrediosConTramite(
	    Collection<DatosPredioRegistro> acdpr_predioRegistro, boolean ab_validacionActos700,
	    Collection<GravamenPendiente> acgp_gravamenes, Collection<ProhibicionVPM> acpv_prohibiciones, String as_userId,
	    Solicitud as_solicitud, Map<String, String> amss_actosParciales, String as_localIp, String as_remoteIp
	)
	    throws B2BException;

	/**
	 * Salvar reproduccion constancia.
	 *
	 * @param adrc_parametros de adrc parametros
	 * @param as_usuario de as usuario
	 * @param as_ipLocal de as ip local
	 * @param as_ipRemota de as ip remota
	 * @return el valor de data reproduccion constancia
	 * @throws B2BException de b 2 B exception
	 */
	public DataReproduccionConstancia salvarReproduccionConstancia(
	    DataReproduccionConstancia adrc_parametros, String as_usuario, String as_ipLocal, String as_ipRemota
	)
	    throws B2BException;

	/**
	 * Salvar solicitud matricula.
	 *
	 * @param asr_solicitudMatricula de asr solicitud matricula
	 * @param as_userId de as user id
	 * @param as_localIpAddress de as local ip address
	 * @param as_remoteIpAddress de as remote ip address
	 * @throws B2BException de b 2 B exception
	 */
	public void salvarSolicitudMatricula(
	    SolicitudMatricula asr_solicitudMatricula, String as_userId, String as_localIpAddress, String as_remoteIpAddress
	)
	    throws B2BException;

	/**
	 * Salvar tipos documentales correcciones.
	 *
	 * @param acacd_documentalesCorrecciones de acacd documentales correcciones
	 * @param as_solicitud de as solicitud
	 * @param as_userId de as user id
	 * @param as_localIp de as local ip
	 * @param as_remoteIp de as remote ip
	 * @throws B2BException de b 2 B exception
	 */
	public void salvarTiposDocumentalesCorrecciones(
	    Collection<TipoDocumental> acacd_documentalesCorrecciones, Solicitud as_solicitud, String as_userId,
	    String as_localIp, String as_remoteIp
	)
	    throws B2BException;

	/**
	 * Salvar tramite.
	 *
	 * @param ar_parametros de ar parametros
	 * @param as_userId de as user id
	 * @param as_ipLocal de as ip local
	 * @param as_ipRemota de as ip remota
	 * @return el valor de registro
	 * @throws B2BException de b 2 B exception
	 */
	public Registro salvarTramite(Registro ar_parametros, String as_userId, String as_ipLocal, String as_ipRemota)
	    throws B2BException;

	/**
	 * Save acto constancia.
	 *
	 * @param aoa_oa de aoa oa
	 * @param as_userId de as user id
	 * @param as_localIp de as local ip
	 * @param as_remoteIp de as remote ip
	 * @return el valor de string
	 * @throws B2BException de b 2 B exception
	 */
	public String saveActoConstancia(
	    com.bachue.snr.prosnr01.model.registro.Acto aoa_oa, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException;

	/**
	 * Save info all.
	 *
	 * @param aoa_oa de aoa oa
	 * @param as_userId de as user id
	 * @param as_localIp de as local ip
	 * @param as_remoteIp de as remote ip
	 * @return el valor de com.bachue.snr.prosnr 01 .model.registro. acto
	 * @throws B2BException de b 2 B exception
	 */
	public com.bachue.snr.prosnr01.model.registro.Acto saveInfoAll(
	    com.bachue.snr.prosnr01.model.registro.Acto aoa_oa, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException;

	/**
	 * Save info all.
	 *
	 * @param aca_oa de aca oa
	 * @param as_userId de as user id
	 * @param as_localIp de as local ip
	 * @param as_remoteIp de as remote ip
	 * @param ab_embargos de ab embargos
	 * @return el valor de collection
	 * @throws B2BException de b 2 B exception
	 */
	public Collection<com.bachue.snr.prosnr01.model.registro.Acto> saveInfoAll(
	    Collection<com.bachue.snr.prosnr01.model.registro.Acto> aca_oa, String as_userId, String as_localIp,
	    String                                                  as_remoteIp, boolean ab_embargos
	)
	    throws B2BException;

	/**
	 * Terminar proceso.
	 *
	 * @param ap_parametros de ap parametros
	 * @param as_solicitudCorreccion de as solicitud correccion
	 * @param as_userId de as user id
	 * @param ab_siNotificaCorr de ab si notifica corr
	 * @param as_isConstanciaRep de as is constancia rep
	 * @param as_localIp de as local ip
	 * @param as_remoteIp de as remote ip
	 * @return el valor de registro
	 * @throws B2BException de b 2 B exception
	 * @throws Exception de exception
	 */
	public Registro terminarProceso(
	    Registro ap_parametros, Solicitud as_solicitudCorreccion, String as_userId, boolean ab_siNotificaCorr,
	    boolean as_isConstanciaRep, String as_localIp, String as_remoteIp
	)
	    throws B2BException;

	/**
	 * Terminar proceso consultas.
	 *
	 * @param acdb_criteriosDeBusqueda de acdb criterios de busqueda
	 * @param as_userId de as user id
	 * @param as_ipLocal de as ip local
	 * @param as_ipRemota de as ip remota
	 * @return el valor de criterios de busqueda
	 * @throws B2BException de b 2 B exception
	 */
	public CriteriosDeBusqueda terminarProcesoConsultas(
	    CriteriosDeBusqueda acdb_criteriosDeBusqueda, String as_userId, String as_ipLocal, String as_ipRemota
	)
	    throws B2BException;

	/**
	 * Metodo encargado de terminar etapa de buscar antiguo sistema y liquidar.
	 *
	 * @param abas_parametros Argumento de tipo <code>BuscarAntiguoSistema</code> que contiene los criterios a terminar y liquidar.
	 * @param as_userId de as user id
	 * @param as_ipLocal Argumento de tipo <code>String</code> que contiene la ip local del usuario que realiza la operación.
	 * @param as_ipRemota de as ip remota
	 * @throws B2BException Señala que se ha generado una excepción
	 */
	public void terminarProcesoCopiasAntSistema(
	    BuscarAntiguoSistema abas_parametros, String as_userId, String as_ipLocal, String as_ipRemota
	)
	    throws B2BException;

	/**
	 * Terminar proceso grabacion matriculas.
	 *
	 * @param ar_registro de ar registro
	 * @param ab_notificarCorrespondencia de ab notificar correspondencia
	 * @param as_userId de as user id
	 * @param as_localIp de as local ip
	 * @param as_remoteIp de as remote ip
	 * @return el valor de registro
	 * @throws B2BException de b 2 B exception
	 * @throws IOException Señala que se ha producido una excepción de E / S.
	 */
	public Registro terminarProcesoGrabacionMatriculas(
	    Registro ar_registro, boolean ab_notificarCorrespondencia, String as_userId, String as_localIp,
	    String as_remoteIp
	)
	    throws B2BException, IOException;

	/**
	 * Terminar proceso recepcion.
	 *
	 * @param acacd_acd de acacd acd
	 * @param as_s de as s
	 * @param as_localIp de as local ip
	 * @param as_remoteIp de as remote ip
	 * @param as_userId de as user id
	 * @return el valor de registro
	 * @throws B2BException de b 2 B exception
	 */
	public Registro terminarProcesoRecepcion(
	    Collection<AccCompletitudDocumental> acacd_acd, Solicitud as_s, String as_localIp, String as_remoteIp,
	    String as_userId
	)
	    throws B2BException;

	/**
	 * Tiene segregacion.
	 *
	 * @param la_a de la a
	 * @return verdadero, si se cumple la condicion, de lo contario retorna falso
	 * @throws B2BException de b 2 B exception
	 */
	public boolean tieneSegregacion(Acto la_a)
	    throws B2BException;

	/**
	 * Update acto hijo.
	 *
	 * @param aa_acto de aa acto
	 * @param as_userId de as user id
	 * @param as_localIp de as local ip
	 * @param as_remoteIp de as remote ip
	 * @throws B2BException de b 2 B exception
	 */
	public void updateActoHijo(
	    com.bachue.snr.prosnr01.model.registro.Acto aa_acto, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException;

	/**
	 * Método encargado de actualizar el id circulo de los actos hijos de un acto principal.
	 *
	 * @param aa_actoUdate Objeto que contiene la información del acto.
	 * @param as_userId Variable que contiene el id del usuario.
	 * @param as_remoteIp Variable que contiene la ip del usuario.
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void updateCirculoActosHijos(Acto aa_actoUdate, String as_userId, String as_remoteIp)
	    throws B2BException;

	/**
	 * Update solicitud.
	 *
	 * @param ap_parametros de ap parametros
	 * @param as_userId de as user id
	 * @throws B2BException de b 2 B exception
	 */
	public void updateSolicitud(Solicitud ap_parametros, String as_userId)
	    throws B2BException;

	/**
	 * Salvar Testamento.
	 *
	 * @param at_testamentom de at testamentom
	 * @param as_userId de as user id
	 * @param as_ipLocal de as ip local
	 * @param as_ipRemota de as ip remota
	 * @return el valor de documento
	 * @throws B2BException de b 2 B exception
	 */
	public Testamento updateTestamento(
	    Testamento at_testamentom, String as_userId, String as_ipLocal, String as_ipRemota
	)
	    throws B2BException;

	/**
	 * Metodo encargado de validar si la información capturada en expedicón de copias es diferente en buscar antiguo sistema.
	 *
	 * @param abas_parametros Argumento de tipo <code>BuscarAntiguoSistema</code> que contiene los criterios a validar.
	 * @param as_userId Argumento de tipo <code>String</code> que contiene el id del usuario que realiza la consulta.
	 * @param as_ipLocal de as ip local
	 * @param as_ipRemota de as ip remota
	 * @return Variable de tipo <code>boolean</code> que determina si se modificó algún valor o no.
	 * @throws B2BException Señala que se ha generado una excepción.
	 */
	public boolean validaModificacionDatosAntiguoSistema(
	    BuscarAntiguoSistema abas_parametros, String as_userId, String as_ipLocal, String as_ipRemota
	)
	    throws B2BException;

	/**
	 * Validacion acto 0315.
	 *
	 * @param as_s de as s
	 * @param as_userId de as user id
	 * @param as_localIp de as local ip
	 * @param remoteIp de remote ip
	 * @return verdadero, si se cumple la condicion, de lo contario retorna falso
	 * @throws B2BException de b 2 B exception
	 */
	public boolean validacionActo0315(Solicitud as_s, String as_userId, String as_localIp, String remoteIp)
	    throws B2BException;

	/**
	 * Método para extraer actos de una constante.
	 *
	 * @param as_idSolicitud de as id solicitud
	 * @param as_constante de as constante
	 * @param as_userId de as user id
	 * @return verdadero, si se cumple la condicion, de lo contario retorna falso
	 * @throws B2BException de b 2 B exception
	 */
	public boolean validacionActosContraConstante(String as_idSolicitud, String as_constante, String as_userId)
	    throws B2BException;

	/**
	 * Validacion actos contra constante.
	 *
	 * @param as_idSolicitud de as id solicitud
	 * @param as_idAnotacionPredio de as id anotacion predio
	 * @param as_constante de as constante
	 * @param as_userId de as user id
	 * @return verdadero, si se cumple la condicion, de lo contario retorna falso
	 * verdadero, si se cumple la condicion, de lo contario retorna falso
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public boolean validacionActosContraConstante(
	    String as_idSolicitud, String as_idAnotacionPredio, String as_constante, String as_userId
	)
	    throws B2BException;

	/**
	 * Validar acto ejecutoria.
	 *
	 * @param as_idTipoDoc de as id tipo doc
	 * @return verdadero, si se cumple la condicion, de lo contario retorna falso
	 * @throws B2BException de b 2 B exception
	 */
	public boolean validarActoEjecutoria(String as_idTipoDoc)
	    throws B2BException;

	/**
	 * Validar acto grupo cancelacion.
	 *
	 * @param as_idSolicitud de as id solicitud
	 * @param as_userId de as user id
	 * @return verdadero, si se cumple la condicion, de lo contario retorna falso
	 * @throws B2BException de b 2 B exception
	 */
	public boolean validarActoGrupoCancelacion(String as_idSolicitud, String as_userId)
	    throws B2BException;

	/**
	 * Validar acto nat juridica acto migrado.
	 *
	 * @param as_idTipoActo de as id tipo acto
	 * @param as_userId de as user id
	 * @param as_localIp de as local ip
	 * @param as_remoteIp de as remote ip
	 * @param as_cuantia de as cuantia
	 * @return retorna <code>true</code> si se cumple la condicion, de lo contario retorna <code>false</code>
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public boolean validarActoNatJuridicaActoMigrado(
	    String as_idTipoActo, String as_userId, String as_localIp, String as_remoteIp, String as_cuantia
	)
	    throws B2BException;

	/**
	 * Validar actos cancelacion providencia.
	 *
	 * @param as_acto de as acto
	 * @param al_idMatricula de al id matricula
	 * @param as_idCirculo de as id circulo
	 * @param as_userId de as user id
	 * @param as_localIp de as local ip
	 * @param as_remoteIp de as remote ip
	 * @return el valor de string
	 * @throws B2BException de b 2 B exception
	 */
	public String validarActosCancelacionProvidencia(
	    String as_acto, Long al_idMatricula, String as_idCirculo, String as_userId, String as_localIp,
	    String as_remoteIp
	)
	    throws B2BException;

	/**
	 * Validar actos de medida cautelar.
	 *
	 * @param as_idTurno de as id turno
	 * @param as_userId de as user id
	 * @param as_localIp de as local ip
	 * @param as_remoteIp de as remote ip
	 * @return verdadero, si se cumple la condicion, de lo contario retorna falso
	 * @throws B2BException de b 2 B exception
	 */
	public boolean validarActosDeMedidaCautelar(
	    String as_idTurno, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException;

	/**
	 * Método encargado de validar si ya se han ingresado actos para mayor valor.
	 *
	 * @param as_idSolicitud Variable de tipo String que contiene el id de la solicitud.
	 * @return Boolean que valida si ya se han ingresado actos para mayor valor.
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public boolean validarActosMayorValor(String as_idSolicitud)
	    throws B2BException;

	/**
	 * Validar actos parcial.
	 *
	 * @param ls_solicitud de ls solicitud
	 * @throws B2BException de b 2 B exception
	 */
	public void validarActosParcial(Solicitud ls_solicitud)
	    throws B2BException;

	/**
	 * Validar actos vencimiento dias habiles.
	 *
	 * @param as_idTipoDoc de as id tipo doc
	 * @return verdadero, si se cumple la condicion, de lo contario retorna falso
	 * @throws B2BException de b 2 B exception
	 */
	public boolean validarActosVencimientoDiasHabiles(String as_idTipoDoc)
	    throws B2BException;

	/**
	 * Validar anexos.
	 *
	 * @param aca_actos de aca actos
	 * @return verdadero, si se cumple la condicion, de lo contario retorna falso
	 * @throws B2BException de b 2 B exception
	 */
	public boolean validarAnexos(com.bachue.snr.prosnr01.model.registro.Acto aca_actos)
	    throws B2BException;

	/**
	 * Validar anotacion loteo.
	 *
	 * @param aap_anotacionPredio de aap anotacion predio
	 * @return verdadero, si se cumple la condicion, de lo contario retorna falso
	 * @throws B2BException de b 2 B exception
	 */
	public boolean validarAnotacionLoteo(AnotacionPredio aap_anotacionPredio)
	    throws B2BException;

	/**
	 * Validar cantidad detalle criterio busqueda.
	 *
	 * @param acdb_criteriosDeBusqueda de acdb criterios de busqueda
	 * @param as_userId de as user id
	 * @param as_ipLocal de as ip local
	 * @param as_ipRemota de as ip remota
	 * @throws B2BException de b 2 B exception
	 */
	public void validarCantidadDetalleCriterioBusqueda(
	    CriteriosDeBusqueda acdb_criteriosDeBusqueda, String as_userId, String as_ipLocal, String as_ipRemota
	)
	    throws B2BException;

	/**
	 * Validar certificado obligatorio.
	 *
	 * @param as_tipoActo de as tipo acto
	 * @param as_userId de as user id
	 * @param as_localIp de as local ip
	 * @param as_remoteIp de as remote ip
	 * @return verdadero, si se cumple la condicion, de lo contario retorna falso
	 * @throws B2BException de b 2 B exception
	 */
	public boolean validarCertificadoObligatorio(
	    String as_tipoActo, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException;

	/**
	 * Método de validación del circulo del usuario
	 * @param as_userId con el usuario a validar
	 * @param as_localIp con la ip del usuario
	 * @param as_remoteIp con la ip del usuario
	 * @return de tipo boolean con la respuesta
	 * @throws B2BException en caso de error
	 */
	public boolean validarCirculoSNRUsuario(String as_userId, String as_localIp, String as_remoteIp)
	    throws B2BException;

	/**
	 * Metodo encargado de validar si un tipo de criterio búsqueda existe en criterios de busqueda.
	 *
	 * @param al_idTurnoHistoria Argumento de tipo <code>Long</code> que contiene el id del turno historia a consultar.
	 * @param idTipoCriterioBusqueda de id tipo criterio busqueda
	 * @param as_userId Argumento de tipo <code>String</code> que contiene el id del usuario que realiza la operación.
	 * @param as_remoteIp de as remote ip
	 * @param as_localIp de as local ip
	 * @return Retorna una variable de tipo <code>boolean</code> que determina si se encontraron resultados para la búsqueda.
	 * @throws B2BException Señala que se ha generado una excepción.
	 */
	public boolean validarCriterioPorTipoCriterio(
	    Long al_idTurnoHistoria, String idTipoCriterioBusqueda, String as_userId, String as_remoteIp, String as_localIp
	)
	    throws B2BException;

	/**
	 * Validar excel intervinientes masivos.
	 *
	 * @param aba_archivo de aba archivo
	 * @param as_nombreFile de as nombre file
	 * @param as_userId de as user id
	 * @param as_localIp de as local ip
	 * @param as_remoteIp de as remote ip
	 * @param as_solicitud de as solicitud
	 * @return el valor de collection
	 * @throws B2BException de b 2 B exception
	 * @throws IOException Señala que se ha producido una excepción de E / S.
	 */
	public Collection<SolicitudInterviniente> validarExcelIntervinientesMasivos(
	    byte[] aba_archivo, String as_nombreFile, String as_userId, String as_localIp, String as_remoteIp,
	    String as_solicitud
	)
	    throws B2BException, IOException;

	/**
	 * Validar existencia documento.
	 *
	 * @param ap_parametrodoc de ap parametrodoc
	 * @return el valor de validacion documento
	 * @throws B2BException de b 2 B exception
	 */
	public ValidacionDocumento validarExistenciaDocumento(Documento ap_parametrodoc)
	    throws B2BException;

	/**
	 * Validar existencia documento constancia.
	 *
	 * @param ap_parametrodoc de ap parametrodoc
	 * @return el valor de validacion documento
	 * @throws B2BException de b 2 B exception
	 */
	public ValidacionDocumento validarExistenciaDocumentoConstancia(DocumentoConstancia ap_parametrodoc)
	    throws B2BException;

	/**
	 * Validar existencia documento ejecutoria.
	 *
	 * @param ad_parametrodoc de ad parametrodoc
	 * @return el valor de documento
	 * @throws B2BException de b 2 B exception
	 */
	public Documento validarExistenciaDocumentoEjecutoria(Documento ad_parametrodoc)
	    throws B2BException;

	/**
	 * Método encargado de validar si existen documentos en la tabla de completitud documental.
	 *
	 * @param as_idSolicitud de as id solicitud
	 * @param as_userId de as user id
	 * @param as_localIp de as local ip
	 * @param as_remoteIp de as remote ip
	 * @return retorna <code>true</code> si se cumple la condicion, de lo contario retorna <code>false</code>
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public boolean validarInvocacionCapture(
	    Solicitud as_idSolicitud, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException;

	/**
	 * Validar notificar correspondencia.
	 *
	 * @param as_parametros de as parametros
	 * @param as_userId de as user id
	 * @param as_localIp de as local ip
	 * @param as_remoteIp de as remote ip
	 * @return verdadero, si se cumple la condicion, de lo contario retorna falso
	 * @throws B2BException de b 2 B exception
	 */
	public boolean validarNotificarCorrespondencia(
	    Solicitud as_parametros, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException;

	/**
	 * Validar nueva entrada.
	 *
	 * @param ane_nuevaEntrada de ane nueva entrada
	 * @return el valor de nueva entrada
	 * @throws B2BException de b 2 B exception
	 */
	public NuevaEntrada validarNuevaEntrada(NuevaEntrada ane_nuevaEntrada)
	    throws B2BException;

	/**
	 * Validar gravamenes pendientes para validación de roles en intervinientes.
	 *
	 * @param lcgp_gravamenesPendientes Colección de gravamenes pendientes a validar
	 * @param as_userId de as user id
	 * @return verdadero, si se cumple la condicion, de lo contario retorna falso
	 * @throws B2BException de b 2 B exception
	 */
	public boolean validarRolGravamenesPendientes(
	    Collection<GravamenPendiente> lcgp_gravamenesPendientes, String as_userId
	)
	    throws B2BException;

	/**
	 * Validar segunda recepcion documentos.
	 *
	 * @param act_turnos de act turnos
	 * @param as_idProceso de as id proceso
	 * @param aso_solicitud de aso solicitud
	 * @param as_userId de as user id
	 * @param as_localIpAddress de as local ip address
	 * @param as_remoteIpAddress de as remote ip address
	 * @return el valor de string
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public String validarSegundaRecepcionDocumentos(
	    Collection<Turno> act_turnos, String as_idProceso, Solicitud aso_solicitud, String as_userId,
	    String as_localIpAddress, String as_remoteIpAddress
	)
	    throws B2BException;

	/**
	 * Validar sub proceso.
	 *
	 * @param idSolicitud correspondiente al valor de id solicitud
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public boolean validarSubProceso(String idSolicitud)
	    throws B2BException;

	/**
	 * Validar suma areas.
	 *
	 * @param acs_matriculas de acs matriculas
	 * @param ad_area de ad area
	 * @param ab_sumatoria de ab sumatoria
	 * @return verdadero, si se cumple la condicion, de lo contario retorna falso
	 * @throws B2BException de b 2 B exception
	 */
	public boolean validarSumaAreas(Collection<String> acs_matriculas, Double ad_area, boolean ab_sumatoria)
	    throws B2BException;

	/**
	 * Validar tipo acto baldio.
	 *
	 * @param as_tipoActo de as tipo acto
	 * @return verdadero, si se cumple la condicion, de lo contario retorna falso
	 * @throws B2BException de b 2 B exception
	 */
	public boolean validarTipoActoBaldio(String as_tipoActo)
	    throws B2BException;

	/**
	 * Validar turno certificado.
	 *
	 * @param as_idTurno de as id turno
	 * @param as_idSolicitud de as id solicitud
	 * @param ab_accion de ab accion
	 * @return verdadero, si se cumple la condicion, de lo contario retorna falso
	 * @throws B2BException de b 2 B exception
	 */
	public boolean validarTurnoCertificado(String as_idTurno, String as_idSolicitud, boolean ab_accion)
	    throws B2BException;

	/**
	 * Validar verificar turno.
	 *
	 * @param ane_nuevaEntrada de ane nueva entrada
	 * @return el valor de nueva entrada
	 * @throws B2BException de b 2 B exception
	 */
	public NuevaEntrada validarVerificarTurno(NuevaEntrada ane_nuevaEntrada)
	    throws B2BException;

	/**
	 * Verificar si acto no valido para ant sistema.
	 *
	 * @param acs_idActo de acs id acto
	 * @param as_userId de as user id
	 * @param as_localIp de as local ip
	 * @param as_remoteIp de as remote ip
	 * @return verdadero, si se cumple la condicion, de lo contario retorna falso
	 * @throws B2BException de b 2 B exception
	 */
	public boolean verificarSiActoNoValidoParaAntSistema(
	    Collection<String> acs_idActo, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException;
}
