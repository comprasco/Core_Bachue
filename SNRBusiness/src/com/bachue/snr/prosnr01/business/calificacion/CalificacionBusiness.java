package com.bachue.snr.prosnr01.business.calificacion;

import com.b2bsg.common.dataAccess2.DAOManager;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.file.ZipEntryUtil;
import com.b2bsg.common.file.ZipUtils;

import com.b2bsg.common.logging.LoggerHandler;

import com.b2bsg.common.util.BooleanUtils;
import com.b2bsg.common.util.CollectionUtils;
import com.b2bsg.common.util.DateUtils;
import com.b2bsg.common.util.NumericUtils;
import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.business.BaseBusiness;
import com.bachue.snr.prosnr01.business.utilidades.PDFGenerator;

import com.bachue.snr.prosnr01.common.constants.CamposCorreccionCommon;
import com.bachue.snr.prosnr01.common.constants.CausalCorreccionCommon;
import com.bachue.snr.prosnr01.common.constants.ConstanteCommon;
import com.bachue.snr.prosnr01.common.constants.ErrorKeys;
import com.bachue.snr.prosnr01.common.constants.EstadoCommon;
import com.bachue.snr.prosnr01.common.constants.EtapaCommon;
import com.bachue.snr.prosnr01.common.constants.ExtensionCommon;
import com.bachue.snr.prosnr01.common.constants.FormatoFechaCommon;
import com.bachue.snr.prosnr01.common.constants.IdentificadoresCommon;
import com.bachue.snr.prosnr01.common.constants.MessagesKeys;
import com.bachue.snr.prosnr01.common.constants.MotivoTramiteCommon;
import com.bachue.snr.prosnr01.common.constants.ProcesoCommon;
import com.bachue.snr.prosnr01.common.constants.RepositorioCommon;
import com.bachue.snr.prosnr01.common.constants.RolCommon;
import com.bachue.snr.prosnr01.common.constants.TipoArchivoCommon;
import com.bachue.snr.prosnr01.common.constants.TipoAreaCommon;
import com.bachue.snr.prosnr01.common.constants.TipoComplementacionCommon;
import com.bachue.snr.prosnr01.common.constants.TipoDocumentalCommon;

import com.bachue.snr.prosnr01.dao.DaoCreator;
import com.bachue.snr.prosnr01.dao.DaoManagerFactory;
import com.bachue.snr.prosnr01.dao.acc.AccAreaPredioDAO;
import com.bachue.snr.prosnr01.dao.acc.AccComplementacionPredioDAO;
import com.bachue.snr.prosnr01.dao.acc.AccConsultaCriteriosAntSistemaDAO;
import com.bachue.snr.prosnr01.dao.acc.AccDetalleAreaPredioDAO;
import com.bachue.snr.prosnr01.dao.acc.AccLinderoPredioDAO;
import com.bachue.snr.prosnr01.dao.acc.AccPredioRegistroDAO;
import com.bachue.snr.prosnr01.dao.acc.AccPredioSegregadoDAO;
import com.bachue.snr.prosnr01.dao.acc.AccSalvedadAnotacionDAO;
import com.bachue.snr.prosnr01.dao.acc.AccSalvedadPredioDAO;
import com.bachue.snr.prosnr01.dao.acc.ActoDAO;
import com.bachue.snr.prosnr01.dao.acc.AnotacionPredioCiudadanoDAO;
import com.bachue.snr.prosnr01.dao.acc.AnotacionPredioDAO;
import com.bachue.snr.prosnr01.dao.acc.BitacoraBloqueoDAO;
import com.bachue.snr.prosnr01.dao.acc.DatosAntSistemaDAO;
import com.bachue.snr.prosnr01.dao.acc.DireccionPredioAccDAO;
import com.bachue.snr.prosnr01.dao.acc.DocumentosSalidaDAO;
import com.bachue.snr.prosnr01.dao.acc.MatriculaSegregacionDAO;
import com.bachue.snr.prosnr01.dao.acc.NotaDevolutivaDAO;
import com.bachue.snr.prosnr01.dao.acc.PersonaDAO;
import com.bachue.snr.prosnr01.dao.acc.SolicitudCamposCorreccionDAO;
import com.bachue.snr.prosnr01.dao.acc.SolicitudCorreccionDAO;
import com.bachue.snr.prosnr01.dao.acc.SolicitudDAO;
import com.bachue.snr.prosnr01.dao.acc.SolicitudIntervinienteDAO;
import com.bachue.snr.prosnr01.dao.acc.SolicitudMatriculaActoDAO;
import com.bachue.snr.prosnr01.dao.acc.SolicitudMatriculaDAO;
import com.bachue.snr.prosnr01.dao.acc.SolicitudReproduccionDAO;
import com.bachue.snr.prosnr01.dao.acc.TipoActoDAO;
import com.bachue.snr.prosnr01.dao.acc.TmpSolicitudMatriculaActoDAO;
import com.bachue.snr.prosnr01.dao.acc.TmpSolicitudMatriculaDAO;
import com.bachue.snr.prosnr01.dao.acc.TurnoDAO;
import com.bachue.snr.prosnr01.dao.acc.TurnoHistoriaDAO;
import com.bachue.snr.prosnr01.dao.aut.UsuarioDAO;
import com.bachue.snr.prosnr01.dao.bgn.DocumentoDAO;
import com.bachue.snr.prosnr01.dao.bgn.ImagenesDAO;
import com.bachue.snr.prosnr01.dao.bng.ComplementacionPredioDAO;
import com.bachue.snr.prosnr01.dao.bng.PredioRegistroDAO;
import com.bachue.snr.prosnr01.dao.col.PredioTipoDAO;
import com.bachue.snr.prosnr01.dao.consulta.trazabilidad.ConsultaTrazabilidadDAO;
import com.bachue.snr.prosnr01.dao.htr.EstadoPredioDAO;
import com.bachue.snr.prosnr01.dao.pgn.CausalCorreccionDAO;
import com.bachue.snr.prosnr01.dao.pgn.ConstantesDAO;
import com.bachue.snr.prosnr01.dao.pgn.MotivoTramiteDAO;
import com.bachue.snr.prosnr01.dao.pgn.MunicipioDAO;
import com.bachue.snr.prosnr01.dao.pgn.ZonaRegistralDAO;
import com.bachue.snr.prosnr01.dao.png.EstadoPredioDao;
import com.bachue.snr.prosnr01.dao.png.NaturalezaJuridicaDAO;
import com.bachue.snr.prosnr01.dao.proc.ProcedimientosDAO;
import com.bachue.snr.prosnr01.dao.util.ConsultasUtilidades;
import com.bachue.snr.prosnr01.dao.util.UtilDAO;

import com.bachue.snr.prosnr01.model.Matricula;
import com.bachue.snr.prosnr01.model.antiguoSistema.Anotacion;
import com.bachue.snr.prosnr01.model.antiguoSistema.Apertura;
import com.bachue.snr.prosnr01.model.antiguoSistema.Complementacion;
import com.bachue.snr.prosnr01.model.antiguoSistema.DataConsultaAntSistema;
import com.bachue.snr.prosnr01.model.antiguoSistema.DataConsultaDatosDocumento;
import com.bachue.snr.prosnr01.model.antiguoSistema.DataConsultaPorCriterio;
import com.bachue.snr.prosnr01.model.antiguoSistema.DatosBasicos;
import com.bachue.snr.prosnr01.model.antiguoSistema.DireccionDelPredio;
import com.bachue.snr.prosnr01.model.antiguoSistema.UbicacionZonaRegistral;
import com.bachue.snr.prosnr01.model.aprobacion.Aprobacion;
import com.bachue.snr.prosnr01.model.calificacion.Calificacion;
import com.bachue.snr.prosnr01.model.calificacion.ComplementacionCalificacion;
import com.bachue.snr.prosnr01.model.calificacion.ConfrontacionCorrectiva;
import com.bachue.snr.prosnr01.model.calificacion.ConsultaCriteriosCalificacion;
import com.bachue.snr.prosnr01.model.calificacion.ConsultaCriteriosCalificacionAntiguoSistema;
import com.bachue.snr.prosnr01.model.calificacion.ConsultaCriteriosCalificacionDocumento;
import com.bachue.snr.prosnr01.model.calificacion.LinderoRegistroCalificacion;
import com.bachue.snr.prosnr01.model.calificacion.RegistroCalificacion;
import com.bachue.snr.prosnr01.model.calificacion.RegistroParcialCalificacion;
import com.bachue.snr.prosnr01.model.calificacion.TramiteCantidad;
import com.bachue.snr.prosnr01.model.calificacion.ValidacionAlertaTurno;
import com.bachue.snr.prosnr01.model.numeracion.NumeracionOficiosCirculo;
import com.bachue.snr.prosnr01.model.permisosCorrecciones.PanelAntSistemaSolicitud;
import com.bachue.snr.prosnr01.model.permisosCorrecciones.PanelApertura;
import com.bachue.snr.prosnr01.model.permisosCorrecciones.PanelAreaPredio;
import com.bachue.snr.prosnr01.model.permisosCorrecciones.PanelCatastral;
import com.bachue.snr.prosnr01.model.permisosCorrecciones.PanelComplementacion;
import com.bachue.snr.prosnr01.model.permisosCorrecciones.PanelDatosAnotacion;
import com.bachue.snr.prosnr01.model.permisosCorrecciones.PanelDatosDocumento;
import com.bachue.snr.prosnr01.model.permisosCorrecciones.PanelDetalleAntSistemaAnotacion;
import com.bachue.snr.prosnr01.model.permisosCorrecciones.PanelDetalleAntSistemaSolicitud;
import com.bachue.snr.prosnr01.model.permisosCorrecciones.PanelDetalleIntervinientes;
import com.bachue.snr.prosnr01.model.permisosCorrecciones.PanelDireccionPredio;
import com.bachue.snr.prosnr01.model.permisosCorrecciones.PanelEspecificacion;
import com.bachue.snr.prosnr01.model.permisosCorrecciones.PanelIntervinientes;
import com.bachue.snr.prosnr01.model.permisosCorrecciones.PanelLinderos;
import com.bachue.snr.prosnr01.model.permisosCorrecciones.PanelMatriculasAbiertas;
import com.bachue.snr.prosnr01.model.permisosCorrecciones.PanelMatriculasSegregacion;
import com.bachue.snr.prosnr01.model.permisosCorrecciones.PanelUbicacion;
import com.bachue.snr.prosnr01.model.registro.ConstanciaReproduccion;
import com.bachue.snr.prosnr01.model.registro.DataReproduccionConstancia;
import com.bachue.snr.prosnr01.model.registro.SolicitudReproduccion;
import com.bachue.snr.prosnr01.model.sdb.acc.AccAreaPredio;
import com.bachue.snr.prosnr01.model.sdb.acc.AccComplementacionPredio;
import com.bachue.snr.prosnr01.model.sdb.acc.AccConsultaCriterioAntiguoSistema;
import com.bachue.snr.prosnr01.model.sdb.acc.AccLinderoPredio;
import com.bachue.snr.prosnr01.model.sdb.acc.AccPredioRegistro;
import com.bachue.snr.prosnr01.model.sdb.acc.AccSalvedadAnotacion;
import com.bachue.snr.prosnr01.model.sdb.acc.AccSalvedadPredio;
import com.bachue.snr.prosnr01.model.sdb.acc.Acto;
import com.bachue.snr.prosnr01.model.sdb.acc.AlertaTurnoTramite;
import com.bachue.snr.prosnr01.model.sdb.acc.AnotacionPredioCiudadano;
import com.bachue.snr.prosnr01.model.sdb.acc.BitacoraBloqueo;
import com.bachue.snr.prosnr01.model.sdb.acc.CambioEstadoPredio;
import com.bachue.snr.prosnr01.model.sdb.acc.DatosAntSistema;
import com.bachue.snr.prosnr01.model.sdb.acc.DetalleAntSistema;
import com.bachue.snr.prosnr01.model.sdb.acc.DetalleAreaPredio;
import com.bachue.snr.prosnr01.model.sdb.acc.DireccionPredioAcc;
import com.bachue.snr.prosnr01.model.sdb.acc.DocumentosSalida;
import com.bachue.snr.prosnr01.model.sdb.acc.Liquidacion;
import com.bachue.snr.prosnr01.model.sdb.acc.MatriculaSegregacion;
import com.bachue.snr.prosnr01.model.sdb.acc.NotaDevolutiva;
import com.bachue.snr.prosnr01.model.sdb.acc.OficiosTexto;
import com.bachue.snr.prosnr01.model.sdb.acc.Persona;
import com.bachue.snr.prosnr01.model.sdb.acc.PersonaCorreoElectronico;
import com.bachue.snr.prosnr01.model.sdb.acc.PersonaDireccion;
import com.bachue.snr.prosnr01.model.sdb.acc.Proceso;
import com.bachue.snr.prosnr01.model.sdb.acc.RegistroPagoExceso;
import com.bachue.snr.prosnr01.model.sdb.acc.Solicitud;
import com.bachue.snr.prosnr01.model.sdb.acc.SolicitudApoyoNacional;
import com.bachue.snr.prosnr01.model.sdb.acc.SolicitudCamposCorreccion;
import com.bachue.snr.prosnr01.model.sdb.acc.SolicitudCorreccion;
import com.bachue.snr.prosnr01.model.sdb.acc.SolicitudInterviniente;
import com.bachue.snr.prosnr01.model.sdb.acc.SolicitudMatricula;
import com.bachue.snr.prosnr01.model.sdb.acc.SolicitudMatriculaActo;
import com.bachue.snr.prosnr01.model.sdb.acc.Testamento;
import com.bachue.snr.prosnr01.model.sdb.acc.TipoCausal;
import com.bachue.snr.prosnr01.model.sdb.acc.TmpSolicitudMatricula;
import com.bachue.snr.prosnr01.model.sdb.acc.TmpSolicitudMatriculaActo;
import com.bachue.snr.prosnr01.model.sdb.acc.Turno;
import com.bachue.snr.prosnr01.model.sdb.acc.TurnoDerivado;
import com.bachue.snr.prosnr01.model.sdb.acc.TurnoHistoria;
import com.bachue.snr.prosnr01.model.sdb.aut.Usuario;
import com.bachue.snr.prosnr01.model.sdb.bng.AnotacionPredio;
import com.bachue.snr.prosnr01.model.sdb.bng.AnotacionPredioDireccion;
import com.bachue.snr.prosnr01.model.sdb.bng.AreaPredio;
import com.bachue.snr.prosnr01.model.sdb.bng.ComplementacionPredio;
import com.bachue.snr.prosnr01.model.sdb.bng.DireccionPredio;
import com.bachue.snr.prosnr01.model.sdb.bng.Documento;
import com.bachue.snr.prosnr01.model.sdb.bng.DocumentoConstancia;
import com.bachue.snr.prosnr01.model.sdb.bng.Imagenes;
import com.bachue.snr.prosnr01.model.sdb.bng.PredioRegistro;
import com.bachue.snr.prosnr01.model.sdb.bng.PredioSegregado;
import com.bachue.snr.prosnr01.model.sdb.col.PredioTipo;
import com.bachue.snr.prosnr01.model.sdb.pgn.CausalCorreccion;
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
import com.bachue.snr.prosnr01.model.sdb.pgn.TipoActo;
import com.bachue.snr.prosnr01.model.sdb.pgn.TipoDocumentoPublico;
import com.bachue.snr.prosnr01.model.sdb.pgn.Vereda;
import com.bachue.snr.prosnr01.model.sdb.pgn.ZonaRegistral;
import com.bachue.snr.prosnr01.model.sdb.png.EstadoPredio;
import com.bachue.snr.prosnr01.model.sdb.png.NaturalezaJuridica;
import com.bachue.snr.prosnr01.model.ui.FechaVenceTerminosUI;
import com.bachue.snr.prosnr01.model.ui.PermisosCorreccionesUI;
import com.bachue.snr.prosnr01.model.ui.UsuarioActividadUI;

import java.io.ByteArrayInputStream;

import java.math.BigDecimal;
import java.math.BigInteger;

import java.text.DateFormat;
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
import java.util.Map;


/**
 * Clase que contiene los métodos de negocio  para la fase de calificación para el rol del calificador, por ejemplo buscar turnos para calificar,
 * obtener la matricula involucrada en el turno, obtener detalle de intervinientes, obtener la lista de causales de devolución, guardar detalles de la anotación entre otros.
 *
 * @author Nicolas Guaneme.
 *
 */
public class CalificacionBusiness extends BaseBusiness
{
	/** Constante clh_LOGGER. */
	private static final LoggerHandler clh_LOGGER = (LoggerHandler)com.bachue.snr.prosnr01.common.utils.LoggingHelper
			.getLogger(CalificacionBusiness.class);

	/**  Constante is mensaje Error. */
	private String is_mensajeError;

	/**
	 * Método  encargado de insertar información  del calificador que está asociado al usuario que ejecuta el proceso.
	 *
	 * @param as_idUsuario Variable de tipo String que contiene el usuario que realiza la acción.
	 * @param as_remoteIp Variable de tipo String que contiene la dirección ip remota desde donde se realiza la acción.
	 * @return  Retorna  un objeto de tipo Persona que coincide con los parametros de entrada.
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public synchronized Persona getDatosUsuarioCalificador(String as_idUsuario, String as_remoteIp)
	    throws B2BException
	{
		DAOManager ldm_manager;
		Persona    lp_persona;
		Usuario    lu_usuario;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		lp_persona      = new Persona();
		lu_usuario      = new Usuario();

		try
		{
			UsuarioDAO lu_DAO;

			lu_DAO = DaoCreator.getUsuarioDAO(ldm_manager);

			lu_usuario.setIdUsuario(as_idUsuario);

			lu_usuario = lu_DAO.findById(lu_usuario);

			if(lu_usuario != null)
			{
				PersonaDAO lp_DAO;

				lp_DAO = DaoCreator.getPersonaDAO(ldm_manager);

				lp_persona.setNumeroDocumento(lu_usuario.getNumeroDocumento());

				lp_persona = lp_DAO.findDataCalificador(lp_persona);

				if(lp_persona != null)
					lp_persona = lp_DAO.findById(lp_persona);
				else
				{
					String ls_idFlagPersona;

					lp_persona = new Persona();

					lp_persona.setIdDocumentoTipo(lu_usuario.getIdDocumentoTipo());
					lp_persona.setNumeroDocumento(lu_usuario.getNumeroDocumento());
					lp_persona.setIdTipoPersona(EstadoCommon.N);
					lp_persona.setPrimerNombre(lu_usuario.getPrimerNombre());
					lp_persona.setSegundoNombre(lu_usuario.getSegundoNombre());
					lp_persona.setPrimerApellido(lu_usuario.getPrimerApellido());
					lp_persona.setSegundoApellido(lu_usuario.getSegundoApellido());
					lp_persona.setIdPais(IdentificadoresCommon.INDICATIVO_PAIS_DEFAULT);
					lp_persona.setIdUsuario(lu_usuario.getIdUsuario());

					ls_idFlagPersona = marcarFlagPersona(ldm_manager, lp_persona, as_idUsuario, as_remoteIp);

					if(StringUtils.isValidString(ls_idFlagPersona))
						lp_persona = lp_DAO.findById(ls_idFlagPersona);
				}
			}
			else
				throw new B2BException(ErrorKeys.ERROR_INTERNO_SISTEMA);
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("getDatosUsuarioCalificador", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lp_persona;
	}

	/**
	 * Método encargado de consultar los campos que se modificaron en el id turno historia anterior.
	 *
	 * @param as_idTurno Variable de tipo String que contiene el id turno para un proceso determinado.
	 * @return Retorna  una lista de datos de tipo LinkedHashMap<String, Object> que coincide con los parametros de entrada.
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public synchronized Collection<Map<String, Object>> getIndicadoresByIdTurno(String as_idTurno)
	    throws B2BException
	{
		Collection<Map<String, Object>> lcmso_result;
		DAOManager                      ldm_manager;

		ldm_manager      = DaoManagerFactory.getDAOManager();
		lcmso_result     = null;

		try
		{
			Map<Integer, Object> lhmp_criterio;

			lhmp_criterio = new LinkedHashMap<Integer, Object>();

			lhmp_criterio.put(new Integer(1), as_idTurno);

			lcmso_result = DaoCreator.getUtilDAO(ldm_manager)
					                     .getCustonQuery(ConsultasUtilidades.CS_INDICADORES_CAMPOS, lhmp_criterio);
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("getIndicadoresByIdTurno", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lcmso_result;
	}

	/**
	 * Modifica el valor de MensajeError.
	 *
	 * @param as_me de as me
	 */
	public void setMensajeError(String as_me)
	{
		is_mensajeError = as_me;
	}

	/**
	 * Retorna Objeto o variable de valor mensaje error.
	 *
	 * @return el valor de mensaje error
	 */
	public String getMensajeError()
	{
		return is_mensajeError;
	}

	/**
	 * Método encargado de consultar las observaciones no tramite de un registro determinado  de la tabla SDB_ACC_TURNO_HISTORIA.
	 *
	 * @param al_idTurno Variable de tipo Long que contiene un id turno determinado.
	 * @return Retorna  una variable de tipo String que coincide con los parametros de entrada.
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public synchronized String getObservacionesNoTramite(Long al_idTurno)
	    throws B2BException
	{
		String ls_result;

		ls_result = null;

		Collection<Map<String, Object>> lm_result   = null;
		DAOManager                      ldm_manager;
		ldm_manager                                 = DaoManagerFactory.getDAOManager();

		try
		{
			Map<Integer, Object> lhmp_criterios;

			lhmp_criterios = new LinkedHashMap<Integer, Object>();

			lhmp_criterios.put(new Integer(1), al_idTurno);

			lm_result = DaoCreator.getUtilDAO(ldm_manager)
					                  .getCustonQuery(ConsultasUtilidades.CS_MOTIVO_NO_TRAMITE, lhmp_criterios);

			if(CollectionUtils.isValidCollection(lm_result))
			{
				Iterator<Map<String, Object>> limso_iterador;

				limso_iterador = lm_result.iterator();

				if(limso_iterador.hasNext())
				{
					Map<String, Object> lmso_iterator;

					lmso_iterator = limso_iterador.next();

					if(lmso_iterator != null)
						ls_result = lmso_iterator.get(IdentificadoresCommon.OBSERVACIONES_NO_TRAMITE).toString();
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("getObservacionesNoTramite", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return ls_result;
	}

	/**
	 * Método encargado de consultar los tipos de documento que existen por cada persona de una matrícula.
	 *
	 * @param al_idMatricula Variable de tipo String que contiene el id turno para un proceso determinado.
	 * @param as_idTurnoHistoria de as id turno historia
	 * @return Retorna  una lista de datos de tipo LinkedHashMap<String, Object> que coincide con los parametros de entrada.
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public synchronized Collection<String> getTipoDocPersonaByMatricula(Long al_idMatricula, String as_idTurnoHistoria)
	    throws B2BException
	{
		Collection<String> lcs_result;
		DAOManager         ldm_manager;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		lcs_result      = null;

		try
		{
			lcs_result = DaoCreator.getAccAnotacionPredioDAO(ldm_manager)
					                   .getTipoDocPersonaByMatricula(al_idMatricula, as_idTurnoHistoria);
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("getTipoDocPersonaByMatricula", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lcs_result;
	}

	/**
	 *  Método encargado de actualizar a un rango de matriculas escogido los linderos la complementación o las direcciones según lo escogido.
	 *
	 * @param apr_pr de apr pr
	 * @param as_idUsuario Variable de tipo String que contiene el usuario que realiza la acción.
	 * @param as_remoteIp Variable de tipo String que contiene la dirección ip remota desde donde se realiza la acción.
	 * @return Objeto de tipo  AccPredioRegistro que contiene información del resultado de la actualización.
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public synchronized AccPredioRegistro actualizacionMasiva(
	    AccPredioRegistro apr_pr, String as_idUsuario, String as_remoteIp
	)
	    throws B2BException
	{
		DAOManager ldm_manager;

		ldm_manager = DaoManagerFactory.getDAOManager();

		try
		{
			if(apr_pr != null)
			{
				int                                                          li_idProceso;
				Collection<com.bachue.snr.prosnr01.model.sdb.bng.AreaPredio> lcap_tmp;

				String ls_idTurno;
				Long   ll_idTurnoHistoria;

				li_idProceso           = apr_pr.getIdProceso();
				lcap_tmp               = apr_pr.getInfoMatriculas();
				ls_idTurno             = apr_pr.getIdTurno();
				ll_idTurnoHistoria     = apr_pr.getIdTurnoHistoria();

				if(
				    NumericUtils.isValidInteger(Integer.valueOf(li_idProceso))
					    && CollectionUtils.isValidCollection(lcap_tmp)
				)
				{
					AccLinderoPredioDAO         lalp_DAO;
					AccComplementacionPredioDAO lacp_DAO;
					AccPredioRegistroDAO        lpr_DAO;
					DireccionPredioAccDAO       ldpd_DAO;
					int                         li_secuencia;

					lalp_DAO         = DaoCreator.getAccLinderoPredioDAO(ldm_manager);
					lacp_DAO         = DaoCreator.getAccComplementacionPredioDAO(ldm_manager);
					lpr_DAO          = DaoCreator.getAccPredioRegistroDAO(ldm_manager);
					ldpd_DAO         = DaoCreator.getDireccionPredioAccDAO(ldm_manager);
					li_secuencia     = 0;

					for(com.bachue.snr.prosnr01.model.sdb.bng.AreaPredio lap_tmp : lcap_tmp)
					{
						if(lap_tmp != null)
						{
							AccLinderoPredio llp_linderoPredio;

							llp_linderoPredio = apr_pr.getLinderoPredio();

							if(li_idProceso == 0)
							{
								//copiar linderos
								if(llp_linderoPredio != null)
								{
									llp_linderoPredio.setIdMatricula(
									    NumericUtils.getLongWrapper(lap_tmp.getIdMatricula())
									);
									lalp_DAO.updateById(llp_linderoPredio, false);
								}

								else

									throw new B2BException(ErrorKeys.ERROR_INTERNO_SISTEMA);
							}
							else if(li_idProceso == 1)
							{
								//copiar complementacion
								ComplementacionPredio lcp_complementacionPredio;
								Complementacion       lcp_complementacion;
								String                ls_complementacion;
								String                ls_idComplementacion;
								String                ls_tipoComplementacion;

								lcp_complementacion           = apr_pr.getInfoComplementacion();
								lcp_complementacionPredio     = new ComplementacionPredio();
								ls_complementacion            = lcp_complementacion.getComplementacion();
								ls_idComplementacion          = lcp_complementacion.getIdComplementacion();
								ls_tipoComplementacion        = lcp_complementacion.getTipoComplementacion();

								if(lcp_complementacionPredio != null)
								{
									if(StringUtils.isValidString(ls_idComplementacion))
									{
										li_secuencia = NumericUtils.getInt(ls_idComplementacion);

										int li_secuenciaAcc;
										li_secuenciaAcc = lacp_DAO.findSecuence();

										if(li_secuenciaAcc > 0)
										{
											AccComplementacionPredio aacp_complementacionPredio;
											aacp_complementacionPredio = new AccComplementacionPredio();

											aacp_complementacionPredio.setIdComplementacion(
											    NumericUtils.getLongWrapper(li_secuenciaAcc)
											);
											aacp_complementacionPredio.setComplementacion(ls_complementacion);
											aacp_complementacionPredio.setIdComplementacionOriginal(
											    NumericUtils.getLongWrapper(li_secuencia)
											);
											aacp_complementacionPredio.setTipoComplementacion(
											    StringUtils.isValidString(ls_tipoComplementacion)
											    ? (ls_tipoComplementacion.equalsIgnoreCase(
											        TipoComplementacionCommon.COPIAR
											    ) ? TipoComplementacionCommon.C
											      : (ls_tipoComplementacion.equalsIgnoreCase(
											        TipoComplementacionCommon.CONSTRUIR
											    ) ? TipoComplementacionCommon.A
											      : (ls_tipoComplementacion.equalsIgnoreCase(
											        TipoComplementacionCommon.NUEVA
											    ) ? TipoComplementacionCommon.N : null))) : null
											);
											aacp_complementacionPredio.setIdUsuarioCreacion(as_idUsuario);
											aacp_complementacionPredio.setIpCreacion(as_remoteIp);
											aacp_complementacionPredio.setIdTurnoHistoria(
											    NumericUtils.getLong(ll_idTurnoHistoria)
											);
											aacp_complementacionPredio.setIdTurno(ls_idTurno);
											lacp_DAO.insert(aacp_complementacionPredio);

											{
												AccPredioRegistro lapr_pr;
												lapr_pr = new AccPredioRegistro();
												lapr_pr.setIdMatricula(
												    NumericUtils.getLongWrapper(lap_tmp.getIdMatricula())
												);
												lapr_pr.setIdCirculo(lap_tmp.getIdCirculo());
												lapr_pr = lpr_DAO.findByCirculoMatricula(lapr_pr);

												if(lapr_pr != null)
												{
													lapr_pr.setIdComplementacion(
													    NumericUtils.getLongWrapper(li_secuencia)
													);

													lpr_DAO.updateById(lapr_pr);
												}
											}
										}
										else
											throw new B2BException(ErrorKeys.COMPLEMENTACION_PREDIO_INSERT);
									}
									else
										throw new B2BException(ErrorKeys.COMPLEMENTACION_PREDIO_INSERT);
								}
							}
							else
							{
								//copiar direccion
								Collection<DireccionDelPredio> lcdp_cdp;

								lcdp_cdp = apr_pr.getDireccionesPredio();

								if(CollectionUtils.isValidCollection(lcdp_cdp))
								{
									for(DireccionDelPredio ldp_tmp : lcdp_cdp)
									{
										if(ldp_tmp != null)
										{
											if(apr_pr.isCalificacion())
											{
												DireccionPredioAcc ldp_direccion;

												ldp_direccion = ldp_tmp.getDireccionPredio();

												if(ldp_direccion != null)
												{
													DireccionPredioAcc ldpa_direccionMatriculaARecibirLaCopia;

													ldpa_direccionMatriculaARecibirLaCopia = new DireccionPredioAcc();

													ldpa_direccionMatriculaARecibirLaCopia.setIdCirculo(
													    lap_tmp.getIdCirculo()
													);
													ldpa_direccionMatriculaARecibirLaCopia.setIdMatricula(
													    NumericUtils.getLongWrapper(lap_tmp.getIdMatricula())
													);

													ldpa_direccionMatriculaARecibirLaCopia.setIdTurno(ls_idTurno);

													ldpa_direccionMatriculaARecibirLaCopia = ldpd_DAO
															.findByIdCirculoMatriculaTurno(
															    ldpa_direccionMatriculaARecibirLaCopia
															);

													if(ldpa_direccionMatriculaARecibirLaCopia != null)
													{
														DireccionPredioAcc ldpa_direccionParaCopiar;

														ldpa_direccionParaCopiar = ldpd_DAO.findById(ldp_direccion);

														if(ldpa_direccionParaCopiar != null)
														{
															ldpa_direccionParaCopiar.setIdCirculo(
															    ldpa_direccionMatriculaARecibirLaCopia.getIdCirculo()
															);
															ldpa_direccionParaCopiar.setIdMatricula(
															    ldpa_direccionMatriculaARecibirLaCopia.getIdMatricula()
															);
															ldpa_direccionParaCopiar.setIdDireccionPredio(
															    ldpa_direccionMatriculaARecibirLaCopia
																    .getIdDireccionPredio()
															);
															ldpa_direccionParaCopiar.setIdUsuarioModificacion(
															    as_idUsuario
															);
															ldpa_direccionParaCopiar.setIpModificacion(as_remoteIp);

															ldpd_DAO.updateById(ldpa_direccionParaCopiar, true);
														}
													}
												}
											}
											else
											{
												DireccionPredioAcc ldp_dp;

												ldp_dp = ldp_tmp.getDireccionPredio();

												{
													DireccionPredioAcc             ldpa_direccionActual;
													Collection<DireccionPredioAcc> lcdp_tmp;

													ldpa_direccionActual     = new DireccionPredioAcc();
													lcdp_tmp                 = new ArrayList<DireccionPredioAcc>();

													ldpa_direccionActual.setIdCirculo(lap_tmp.getIdCirculo());
													ldpa_direccionActual.setIdMatricula(
													    NumericUtils.getLongWrapper(lap_tmp.getIdMatricula())
													);
													ldpa_direccionActual.setIdTurno(ls_idTurno);

													lcdp_tmp = ldpd_DAO.findAllByIdCirculoMatriculaTurno(
														    ldpa_direccionActual
														);

													if(CollectionUtils.isValidCollection(lcdp_tmp))
													{
														for(DireccionPredioAcc ldpa_dpacc : lcdp_tmp)
														{
															if(ldpa_dpacc != null)
															{
																ldpa_dpacc.setIdComplementoDireccion(
																    ldp_dp.getIdComplementoDireccion()
																);
																ldpa_dpacc.setComplementoDireccion(
																    ldp_dp.getComplementoDireccion()
																);
																ldpa_dpacc.setDireccion(
																    actualizarDireccionPredioCompleta(
																        ldpa_dpacc, ldm_manager
																    )
																);
																ldpd_DAO.updateById(ldpa_dpacc, true);
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

			clh_LOGGER.error("actualizacionMasiva", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return apr_pr;
	}

	/**
	 * Método encargado de aperturar las matrículas para segregación.
	 *
	 * @param arc_data Objeto que contiene la información del proceso.
	 * @param as_userId Variable que contiene el id del usuario.
	 * @param as_remoteIp de as remote ip
	 * @return Objeto que contiene la información de la apertura de matrículas.
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public synchronized RegistroCalificacion aperturarMatriculasSegregacion(
	    RegistroCalificacion arc_data, String as_userId, String as_remoteIp
	)
	    throws B2BException
	{
		DAOManager ldm_manager;

		ldm_manager = DaoManagerFactory.getDAOManager();

		try
		{
			Long   ll_idTurnoHistoria;
			String ls_idTurno;

			ll_idTurnoHistoria     = arc_data.getIdTurnoHistoria();
			ls_idTurno             = arc_data.getTurno();

			if(StringUtils.isValidString(ls_idTurno) && NumericUtils.isValidLong(ll_idTurnoHistoria))
			{
				TurnoHistoria lth_turnoHistoria;

				lth_turnoHistoria = DaoCreator.getTurnoHistoriaDAO(ldm_manager).findById(ll_idTurnoHistoria);

				if(lth_turnoHistoria != null)
				{
					Collection<MatriculaSegregacion> lcms_data;
					MatriculaSegregacionDAO          lms_DAO;

					lcms_data     = arc_data.getInfoMatriculasSegregacion();
					lms_DAO       = DaoCreator.getMatriculaSegregacionDAO(ldm_manager);

					if(CollectionUtils.isValidCollection(lcms_data))
					{
						boolean                          lb_aperturaAnotacion;
						Collection<MatriculaSegregacion> lcms_matriculasExistentes;
						Long                             ll_idMatricula;
						String                           ls_idSolicitud;
						String                           ls_idCirculo;
						String                           ls_idAnotacionApertura;

						ll_idMatricula                = arc_data.getIdMatricula();
						ls_idSolicitud                = lth_turnoHistoria.getIdSolicitud();
						ls_idCirculo                  = arc_data.getIdCirculo();
						ls_idAnotacionApertura        = arc_data.getIdAnotacionApertura();
						lb_aperturaAnotacion          = StringUtils.isValidString(ls_idAnotacionApertura);
						lcms_matriculasExistentes     = lms_DAO.findByIdTurnoCirculoMatricula(
							    ls_idTurno, ls_idCirculo, ll_idMatricula, ls_idAnotacionApertura
							);

						if(!CollectionUtils.isValidCollection(lcms_matriculasExistentes))
						{
							for(MatriculaSegregacion lms_iterador : lcms_data)
							{
								if(lms_iterador != null)
								{
									lms_iterador.setIdUsuarioCreacion(as_userId);
									lms_iterador.setIpCreacion(as_remoteIp);
									lms_iterador.setIdSolicitud(ls_idSolicitud);
									lms_iterador.setIdCirculoMatriz(ls_idCirculo);
									lms_iterador.setMatriculaMatriz(ll_idMatricula);
									lms_iterador.setIdAnotacionApertura(ls_idAnotacionApertura);
									lms_iterador.setIdTurno(ls_idTurno);

									{
										Double ld_area;

										ld_area = lms_iterador.getAreaTerreno();

										lms_iterador.setAreaTerreno(
										    NumericUtils.isValidDouble(ld_area) ? ld_area
										                                        : NumericUtils.getDoubleWrapper(
										        lms_iterador.getAreaTerrenoLectura()
										    )
										);
									}

									{
										Double ld_area;

										ld_area = lms_iterador.getAreaConstruida();

										lms_iterador.setAreaConstruida(
										    NumericUtils.isValidDouble(ld_area) ? ld_area
										                                        : NumericUtils.getDoubleWrapper(
										        lms_iterador.getAreaConstruidaLectura()
										    )
										);
									}

									{
										Double ld_area;

										ld_area = lms_iterador.getAreaPrivada();

										lms_iterador.setAreaPrivada(
										    NumericUtils.isValidDouble(ld_area) ? ld_area
										                                        : NumericUtils.getDoubleWrapper(
										        lms_iterador.getAreaPrivadaLectura()
										    )
										);
									}

									{
										Double ld_coeficiente;

										ld_coeficiente = lms_iterador.getCoeficiente();

										lms_iterador.setCoeficiente(
										    NumericUtils.isValidDouble(ld_coeficiente) ? ld_coeficiente
										                                               : NumericUtils.getDoubleWrapper(
										        lms_iterador.getCoeficienteLectura()
										    )
										);
									}

									lms_DAO.insert(lms_iterador, as_userId);
								}
							}

							if(lb_aperturaAnotacion)
							{
								arc_data.setIdSolicitud(ls_idSolicitud);
								arc_data.setIdTurno(ls_idTurno);
							}

							arc_data = procMatriculasInformacion(
								    arc_data, ls_idTurno, ll_idTurnoHistoria, false, lb_aperturaAnotacion, true,
								    as_userId, as_remoteIp, ldm_manager
								);

							if((arc_data != null) && !lb_aperturaAnotacion)
							{
								Collection<AreaPredio> lcap_matriculasInfo;

								lcap_matriculasInfo = arc_data.getMatriculasInformacion();

								if(CollectionUtils.isValidCollection(lcap_matriculasInfo))
								{
									SolicitudCorreccion lsc_param;

									lsc_param = new SolicitudCorreccion();

									lsc_param.setIdCirculo(ls_idCirculo);
									lsc_param.setIdMatricula(NumericUtils.getBigInteger(ll_idMatricula));
									lsc_param.setIdCausalCorreccion(
									    NumericUtils.getBigInteger(CausalCorreccionCommon.MATRICULAS_SEGREGADAS)
									);
									lsc_param.setIdSolicitud(lth_turnoHistoria.getIdSolicitud());

									lsc_param = DaoCreator.getSolicitudCorreccionDAO(ldm_manager)
											                  .findBySolCirMatCausal(lsc_param);

									if(lsc_param != null)
									{
										SolicitudCamposCorreccionDAO lscc_DAO;
										SolicitudCamposCorreccion    lscc_datoInsertar;

										lscc_DAO              = DaoCreator.getSolicitudCamposCorreccionDAO(ldm_manager);
										lscc_datoInsertar     = new SolicitudCamposCorreccion();

										lscc_datoInsertar.setIdUsuarioCreacion(as_userId);
										lscc_datoInsertar.setIpCreacion(as_remoteIp);
										lscc_datoInsertar.setIdTurnoHistoria(ll_idTurnoHistoria);
										lscc_datoInsertar.setIdSolicitud(ls_idSolicitud);
										lscc_datoInsertar.setIdCirculo(ls_idCirculo);
										lscc_datoInsertar.setIdMatricula(ll_idMatricula);
										lscc_datoInsertar.setIdSolicitudCorreccion(
										    StringUtils.getString(lsc_param.getIdSolicitudCorreccion())
										);
										lscc_datoInsertar.setIdCausalCorreccion(
										    NumericUtils.getLongWrapper(lsc_param.getIdCausalCorreccion())
										);
										lscc_datoInsertar.setIdCamposCorreccion(
										    CamposCorreccionCommon.PMS_MATRICULAS_SEGREGADAS
										);

										for(AreaPredio lap_iterador : lcap_matriculasInfo)
										{
											if(lap_iterador != null)
											{
												lscc_datoInsertar.setIdCirculoRelacionado(lap_iterador.getIdCirculo());
												lscc_datoInsertar.setIdMatriculaRelacionado(
												    NumericUtils.getLongWrapper(lap_iterador.getIdMatricula())
												);

												lscc_DAO.insertOrUpdate(lscc_datoInsertar, true);
											}
										}
									}
								}
							}
						}
					}
					else
						throw new B2BException(ErrorKeys.ERROR_INTERNO_SISTEMA);
				}
				else
					throw new B2BException(ErrorKeys.TURNO_HISTORIA_INVALIDO);
			}
			else
				throw new B2BException(ErrorKeys.ERROR_INTERNO_SISTEMA);
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("aperturarMatriculasSegregacion", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return arc_data;
	}

	/**
	 * Método encargado de calcular area.
	 *
	 * @param arc_tmp de arc tmp
	 * @param acap_ap de acap ap
	 * @return el valor de string
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public String calcularArea(RegistroCalificacion arc_tmp, Collection<AreaPredio> acap_ap)
	    throws B2BException
	{
		String ls_return;
		ls_return = null;

		if((arc_tmp != null) && CollectionUtils.isValidCollection(acap_ap))
		{
			Collection<RegistroCalificacion> lcrc_matriculas;
			lcrc_matriculas = arc_tmp.getAllMatriculas();

			if(CollectionUtils.isValidCollection(lcrc_matriculas))
			{
				int li_cantidadMatriculas;

				li_cantidadMatriculas = lcrc_matriculas.size();

				if(li_cantidadMatriculas == 1)
				{
					try
					{
						Collection<RegistroCalificacion> lcrc_matricula;
						String                           ls_matricula;
						double                           ld_areaTerreno;
						BigDecimal                       lbd_areaTerreno;

						lcrc_matricula      = arc_tmp.getAllMatriculas();
						ls_matricula        = null;
						ld_areaTerreno      = 0.0;
						lbd_areaTerreno     = null;

						if(CollectionUtils.isValidCollection(lcrc_matricula))
						{
							Iterator<RegistroCalificacion> lirc_iterador;
							lirc_iterador = lcrc_matricula.iterator();

							if(lirc_iterador.hasNext())
							{
								RegistroCalificacion lrc_actual;

								lrc_actual = lirc_iterador.next();

								if(lrc_actual != null)
									ls_matricula = lrc_actual.getIdCirculo();
							}

							if(StringUtils.isValidString(ls_matricula))
							{
								ld_areaTerreno      = getRegistroBusiness().findAreaTerrenoByMatricula(ls_matricula);
								lbd_areaTerreno     = NumericUtils.getBigDecimal(ld_areaTerreno);
							}
						}

						Iterator<AreaPredio> liap_iterador;
						BigDecimal           lbd_sumaArea;

						liap_iterador     = acap_ap.iterator();
						lbd_sumaArea      = new BigDecimal(0);

						while(liap_iterador.hasNext())
						{
							AreaPredio lap_actual;

							lap_actual = liap_iterador.next();

							if(lap_actual != null)
							{
								BigDecimal lbd_areaActual;

								lbd_areaActual = lap_actual.getAreaPredio();

								if(NumericUtils.isValidBigDecimal(lbd_areaActual))
									lbd_sumaArea = lbd_sumaArea.add(lbd_areaActual);
							}
						}

						if(lbd_sumaArea.compareTo(lbd_areaTerreno) > 0)
							ls_return = ls_matricula;
					}
					catch(B2BException lb2be_e)
					{
						clh_LOGGER.error("calcularArea", lb2be_e);
						throw lb2be_e;
					}
				}
				else
				{
					DAOManager ldm_manager;

					ldm_manager = DaoManagerFactory.getDAOManager();

					try
					{
						String                ls_turno;
						AccPredioSegregadoDAO laps_DAO;
						boolean               lb_validador;

						ls_turno         = arc_tmp.getTurno();
						laps_DAO         = DaoCreator.getAccPredioSegregadoDAO(ldm_manager);
						lb_validador     = false;

						Iterator<RegistroCalificacion> lirc_iterator;
						lirc_iterator    = lcrc_matriculas.iterator();

						while(lirc_iterator.hasNext() && !lb_validador)
						{
							RegistroCalificacion lrc_actual;

							lrc_actual = lirc_iterator.next();

							if(lrc_actual != null)
							{
								Long                                                              ll_matricula;
								String                                                            ls_circulo;
								String                                                            ls_matricula;
								double                                                            ld_areaTerreno;
								BigDecimal                                                        lbd_areaTerreno;
								Collection<com.bachue.snr.prosnr01.model.sdb.acc.PredioSegregado> lps_matriculas;
								BigDecimal                                                        lbd_sumaArea;

								ll_matricula        = getIdMatricula(lrc_actual.getIdCirculo());
								ls_circulo          = getIdCirculo(lrc_actual.getIdCirculo());
								lbd_sumaArea        = new BigDecimal(0);
								ls_matricula        = lrc_actual.getIdCirculo();
								ld_areaTerreno      = getRegistroBusiness().findAreaTerrenoByMatricula(ls_matricula);
								lbd_areaTerreno     = NumericUtils.getBigDecimal(ld_areaTerreno);
								lps_matriculas      = laps_DAO.findByCirculoMatriculaTurno(
									    ls_circulo, ll_matricula, ls_turno
									);

								if(CollectionUtils.isValidCollection(lps_matriculas))
								{
									Iterator<com.bachue.snr.prosnr01.model.sdb.acc.PredioSegregado> lips_iterator;
									lips_iterator = lps_matriculas.iterator();

									while(lips_iterator.hasNext())
									{
										com.bachue.snr.prosnr01.model.sdb.acc.PredioSegregado lps_actual;

										lps_actual = lips_iterator.next();

										if(lps_actual != null)
										{
											Long                 ll_matriculaActual;
											Iterator<AreaPredio> liap_iterador;
											boolean              lb_indicador;

											liap_iterador          = acap_ap.iterator();
											ll_matriculaActual     = NumericUtils.getLongWrapper(
												    lps_actual.getIdMatricula1()
												);
											lb_indicador           = false;

											if(liap_iterador != null)
											{
												while(liap_iterador.hasNext() && !lb_indicador)
												{
													AreaPredio lap_actual;

													lap_actual = liap_iterador.next();

													if(lap_actual != null)
													{
														Long       ll_matriculaLoteo;
														BigDecimal lbd_area;

														ll_matriculaLoteo     = NumericUtils.getLongWrapper(
															    lap_actual.getIdMatricula()
															);
														lbd_area              = lap_actual.getAreaPredio();

														if(ll_matriculaActual.compareTo(ll_matriculaLoteo) == 0)
														{
															lbd_sumaArea     = lbd_sumaArea.add(lbd_area);
															lb_indicador     = true;
														}
													}
												}
											}
										}
									}

									if(lbd_sumaArea.compareTo(lbd_areaTerreno) > 0)
									{
										ls_return        = ls_matricula;
										lb_validador     = true;
									}
								}
							}
						}
					}
					catch(Exception lb2be_e)
					{
						ldm_manager.setRollbackOnly();
						clh_LOGGER.error("calcularArea", lb2be_e);
						throw lb2be_e;
					}
					finally
					{
						ldm_manager.close();
					}
				}
			}
		}

		return ls_return;
	}

	/**
	 * Método encargado de cargar los cheks para el proceso de correcciones.
	 *
	 * @param ascc_camposCorreccion Objeto que contiene la información para realizar la búsqueda.
	 * @param as_userId de as user id
	 * @param as_localIp de as local ip
	 * @param as_remoteIp de as remote ip
	 * @return Objeto que contiene los cheks cargados.
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public synchronized PermisosCorreccionesUI cargarCheksCorrecciones(
	    SolicitudCamposCorreccion ascc_camposCorreccion, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		DAOManager             ldm_manager;
		PermisosCorreccionesUI lpcui_return;

		ldm_manager      = DaoManagerFactory.getDAOManager();
		lpcui_return     = new PermisosCorreccionesUI();

		try
		{
			if(ascc_camposCorreccion != null)
			{
				Long ll_idTurnoHistoria;

				ll_idTurnoHistoria = ascc_camposCorreccion.getIdTurnoHistoria();

				if(NumericUtils.isValidLong(ll_idTurnoHistoria))
				{
					TurnoHistoria lth_turnoHistoria;

					lth_turnoHistoria = DaoCreator.getTurnoHistoriaDAO(ldm_manager).findById(ll_idTurnoHistoria);

					if(lth_turnoHistoria != null)
					{
						String ls_idTurno;

						ls_idTurno = lth_turnoHistoria.getIdTurno();

						if(StringUtils.isValidString(ls_idTurno))
						{
							{
								Map<String, String> lmss_salvedad;

								lmss_salvedad = generarCodigos(ConstanteCommon.TEXTO_SALVEDAD, ldm_manager);

								if(CollectionUtils.isValidCollection(lmss_salvedad))
								{
									for(Map.Entry<String, String> lmss_iterador : lmss_salvedad.entrySet())
									{
										if(lmss_iterador != null)
											lpcui_return.setComplementoSalvedad(lmss_iterador.getValue());
									}
								}
							}

							Collection<SolicitudCorreccion> lcsc_data;
							Long                            ll_idMatricula;
							String                          ls_idCirculo;
							SolicitudCorreccion             lsc_param;
							SolicitudCorreccionDAO          lscd_DAO;

							ll_idMatricula     = ascc_camposCorreccion.getIdMatricula();
							ls_idCirculo       = ascc_camposCorreccion.getIdCirculo();
							lscd_DAO           = DaoCreator.getSolicitudCorreccionDAO(ldm_manager);
							lsc_param          = new SolicitudCorreccion();

							lsc_param.setIdSolicitud(ascc_camposCorreccion.getIdSolicitud());
							lsc_param.setIdCirculo(ls_idCirculo);
							lsc_param.setIdMatricula(NumericUtils.getBigInteger(ll_idMatricula));

							lcsc_data = lscd_DAO.findBySolicitudCirculoMatricula(lsc_param, true, false);

							if(CollectionUtils.isValidCollection(lcsc_data))
							{
								AccSalvedadPredio            lps_salvedad;
								AccSalvedadPredioDAO         lsp_DAO;
								AccPredioRegistroDAO         lapr_DAO;
								boolean                      lb_anotaciones;
								SolicitudCamposCorreccionDAO lscc_DAO;

								lps_salvedad       = new AccSalvedadPredio();
								lsp_DAO            = DaoCreator.getAccSalvedadPredioDAO(ldm_manager);
								lapr_DAO           = DaoCreator.getAccPredioRegistroDAO(ldm_manager);
								lb_anotaciones     = false;
								lscc_DAO           = DaoCreator.getSolicitudCamposCorreccionDAO(ldm_manager);

								lps_salvedad.setIdTurno(ls_idTurno);
								lps_salvedad.setIdCirculo(ls_idCirculo);
								lps_salvedad.setIdMatricula(ll_idMatricula);

								for(SolicitudCorreccion lsc_iterador : lcsc_data)
								{
									if(lsc_iterador != null)
									{
										String ls_idCausal;

										ls_idCausal = StringUtils.getString(lsc_iterador.getIdCausalCorreccion());

										if(StringUtils.isValidString(ls_idCausal))
										{
											AccSalvedadPredio lsp_temp;
											Long              ll_idCausal;
											String            ls_salvedad;

											ll_idCausal     = NumericUtils.getLongWrapper(ls_idCausal);
											ls_salvedad     = null;

											if(NumericUtils.isValidLong(ll_idCausal))
											{
												lps_salvedad.setIdCausal(ll_idCausal);
												ascc_camposCorreccion.setIdCausalCorreccion(ll_idCausal);

												lsp_temp = lsp_DAO.findById(lps_salvedad);

												if(lsp_temp != null)
													ls_salvedad = lsp_temp.getDescripcion();
											}

											switch(ls_idCausal)
											{
												case CausalCorreccionCommon.AGREGAR_ANOTACION:
												{
													lpcui_return.setAgregarAnotacion(true);

													break;
												}

												case CausalCorreccionCommon.DATOS_BASICOS:
												{
													Map<String, SolicitudCamposCorreccion> lmsscc_data;
													PanelUbicacion                         lp_panel;

													lp_panel = new PanelUbicacion();

													lp_panel.setSeleccionado(true);
													lp_panel.setSalvedad(ls_salvedad);

													lmsscc_data = lscc_DAO.findMapAllByIdCausal(
														    ascc_camposCorreccion, false, false
														);

													if(CollectionUtils.isValidCollection(lmsscc_data))
													{
														lp_panel.setMunicipio(
														    lmsscc_data.containsKey(
														        CamposCorreccionCommon.PU_MUNICIPIO
														    )
														);
														lp_panel.setVereda(
														    lmsscc_data.containsKey(CamposCorreccionCommon.PU_VEREDA)
														);
														lp_panel.setEstadoPredio(
														    lmsscc_data.containsKey(
														        CamposCorreccionCommon.PU_ESTADO_PREDIO
														    )
														);
														lp_panel.setNupre(
														    lmsscc_data.containsKey(CamposCorreccionCommon.PU_NUPRE)
														);
													}

													lpcui_return.setUbicacion(lp_panel);

													break;
												}

												case CausalCorreccionCommon.INFORMACION_DE_APERTURA:
												{
													Map<String, SolicitudCamposCorreccion> lmsscc_data;
													PanelApertura                          lp_panel;

													lp_panel = new PanelApertura();

													lp_panel.setSeleccionado(true);
													lp_panel.setSalvedad(ls_salvedad);

													lmsscc_data = lscc_DAO.findMapAllByIdCausal(
														    ascc_camposCorreccion, false, false
														);

													if(CollectionUtils.isValidCollection(lmsscc_data))
													{
														lp_panel.setFechaApertura(
														    lmsscc_data.containsKey(
														        CamposCorreccionCommon.PA_FECHA_APERTURA
														    )
														);
														lp_panel.setRadicacion(
														    lmsscc_data.containsKey(
														        CamposCorreccionCommon.PA_RADICACION
														    )
														);
														lp_panel.setTipoDocumento(
														    lmsscc_data.containsKey(
														        CamposCorreccionCommon.PA_TIPO_DOCUMENTO
														    )
														);
														lp_panel.setNumeroDocumento(
														    lmsscc_data.containsKey(
														        CamposCorreccionCommon.PA_NUMERO_DOCUMENTO
														    )
														);
														lp_panel.setFechaDocumento(
														    lmsscc_data.containsKey(
														        CamposCorreccionCommon.PA_FECHA_DOCUMENTO
														    )
														);
														lp_panel.setTipoOficina(
														    lmsscc_data.containsKey(
														        CamposCorreccionCommon.PA_TIPO_OFICINA
														    )
														);
														lp_panel.setTipoEntidad(
														    lmsscc_data.containsKey(
														        CamposCorreccionCommon.PA_TIPO_ENTIDAD
														    )
														);
														lp_panel.setPais(
														    lmsscc_data.containsKey(CamposCorreccionCommon.PA_PAIS)
														);
														lp_panel.setDepartamento(
														    lmsscc_data.containsKey(
														        CamposCorreccionCommon.PA_DEPARTAMENTO
														    )
														);
														lp_panel.setMunicipio(
														    lmsscc_data.containsKey(
														        CamposCorreccionCommon.PA_MUNICIPIO
														    )
														);
														lp_panel.setOficinaOrigen(
														    lmsscc_data.containsKey(
														        CamposCorreccionCommon.PA_OFICINA_ORIGEN
														    )
														);
													}

													lpcui_return.setApertura(lp_panel);

													break;
												}

												case CausalCorreccionCommon.MATRICULAS_ABIERTAS_CON_BASE_EN_LAS_SIGUIENTES_MATRICULAS:
												{
													Map<String, SolicitudCamposCorreccion> lmsscc_data;
													Map<String, SolicitudCamposCorreccion> lmsscc_agregar;
													PanelMatriculasAbiertas                lp_panel;

													lp_panel = new PanelMatriculasAbiertas();

													ascc_camposCorreccion.setIdCamposCorreccion(
													    CamposCorreccionCommon.PMAB_MATRICULAS
													);

													lmsscc_data = lscc_DAO.findMapAllByIdCausalCirculoMatricula(
														    ascc_camposCorreccion, false, false, false, true
														);

													ascc_camposCorreccion.setIdCamposCorreccion(
													    CamposCorreccionCommon.PMAB_AGREGAR
													);

													lmsscc_agregar = lscc_DAO.findMapAllByIdCausalCirculoMatricula(
														    ascc_camposCorreccion, false, false, false
														);

													lp_panel.setSeleccionado(true);
													lp_panel.setSalvedad(ls_salvedad);
													lp_panel.setAgregar(
													    CollectionUtils.isValidCollection(lmsscc_agregar)
													);
													lp_panel.setChecks(lmsscc_data);
													lpcui_return.setMatriculasAbiertas(lp_panel);

													break;
												}

												case CausalCorreccionCommon.INFORMACION_CATASTRAL:
												{
													Map<String, SolicitudCamposCorreccion> lmsscc_data;
													PanelCatastral                         lp_panel;

													lp_panel = new PanelCatastral();

													lp_panel.setSeleccionado(true);
													lp_panel.setSalvedad(ls_salvedad);

													lmsscc_data = lscc_DAO.findMapAllByIdCausal(
														    ascc_camposCorreccion, false, false
														);

													if(CollectionUtils.isValidCollection(lmsscc_data))
													{
														lp_panel.setCodigoCatastral(
														    lmsscc_data.containsKey(
														        CamposCorreccionCommon.PIC_CODIGO_CATASTRAL
														    )
														);
														lp_panel.setCodigoCatastralAnt(
														    lmsscc_data.containsKey(
														        CamposCorreccionCommon.PIC_CODIGO_CATASTRAL_ANT
														    )
														);
													}

													lpcui_return.setCatastral(lp_panel);

													break;
												}

												case CausalCorreccionCommon.MATRICULAS_SEGREGADAS:
												{
													boolean                                ab_agregarNueva;
													Map<String, SolicitudCamposCorreccion> lmsscc_data;
													Map<String, SolicitudCamposCorreccion> lmsscc_agregarExistente;
													Map<String, SolicitudCamposCorreccion> lmsscc_agregarNueva;
													PanelMatriculasSegregacion             lp_panel;

													lp_panel = new PanelMatriculasSegregacion();

													ascc_camposCorreccion.setIdCamposCorreccion(
													    CamposCorreccionCommon.PMS_MATRICULAS_SEGREGADAS
													);

													lmsscc_data = lscc_DAO.findMapAllByIdCausalCirculoMatricula(
														    ascc_camposCorreccion, false, false, false, true
														);

													ascc_camposCorreccion.setIdCamposCorreccion(
													    CamposCorreccionCommon.PMS_AGREGAR_EXISTENTE
													);

													lmsscc_agregarExistente = lscc_DAO
															.findMapAllByIdCausalCirculoMatricula(
															    ascc_camposCorreccion, false, false, false
															);

													ascc_camposCorreccion.setIdCamposCorreccion(
													    CamposCorreccionCommon.PMS_AGREGAR_NUEVA
													);

													lmsscc_agregarNueva     = lscc_DAO
															.findMapAllByIdCausalCirculoMatricula(
															    ascc_camposCorreccion, false, false, false
															);
													ab_agregarNueva         = CollectionUtils.isValidCollection(
														    lmsscc_agregarNueva
														);

													lp_panel.setSeleccionado(true);
													lp_panel.setSalvedad(ls_salvedad);
													lp_panel.setAgregarExistente(
													    CollectionUtils.isValidCollection(lmsscc_agregarExistente)
													);
													lp_panel.setAgregarNueva(ab_agregarNueva);

													if(ab_agregarNueva)
													{
														SolicitudCamposCorreccion lscc_data;

														lscc_data = lmsscc_agregarNueva.get(
															    ls_idCirculo + IdentificadoresCommon.SIMBOLO_GUION
															    + ll_idMatricula
															);

														if(lscc_data != null)
														{
															boolean                       lb_matriculasAperturadas;
															Collection<AccPredioRegistro> lcapr_prediosApertura;

															lcapr_prediosApertura        = lapr_DAO
																	.findByTurnoMenosActual(ls_idTurno, ll_idMatricula);
															lb_matriculasAperturadas     = CollectionUtils
																	.isValidCollection(lcapr_prediosApertura);

															if(!lb_matriculasAperturadas)
															{
																Long ll_cantidad;

																ll_cantidad = lscc_data.getCantidadAperturar();

																if(NumericUtils.isValidLong(ll_cantidad, 1L))
																{
																	boolean                          lb_masivo;
																	Collection<MatriculaSegregacion> lcms_matriculas;
																	int                              li_cantidad;

																	lcms_matriculas     = new ArrayList<MatriculaSegregacion>();
																	li_cantidad         = NumericUtils.getInt(
																		    ll_cantidad
																		);
																	lb_masivo           = li_cantidad > 10;

																	if(!lb_masivo)
																	{
																		for(
																		    int li_count = 0; li_count < li_cantidad;
																			    li_count++
																		)
																			lcms_matriculas.add(
																			    new MatriculaSegregacion()
																			);
																	}

																	lp_panel.setMatriculasSegregacion(lcms_matriculas);
																	lp_panel.setSegregacionMasiva(lb_masivo);
																}

																lp_panel.setCantidad(ll_cantidad);
															}
															else
																lp_panel.setMatriculasInformacion(
																    DaoCreator.getAccPredioRegistroDAO(ldm_manager)
																	              .findMatriculasInformacion(
																	        ll_idTurnoHistoria, ls_idCirculo,
																	        ll_idMatricula, null
																	    )
																);

															lp_panel.setMatriculasAperturadas(lb_matriculasAperturadas);
														}
													}

													lp_panel.setChecks(lmsscc_data);
													lpcui_return.setMatriculasSegregacion(lp_panel);

													break;
												}

												case CausalCorreccionCommon.LINDEROS:
												{
													Map<String, SolicitudCamposCorreccion> lmsscc_data;
													PanelLinderos                          lp_panel;

													lp_panel = new PanelLinderos();

													lp_panel.setSeleccionado(true);
													lp_panel.setSalvedad(ls_salvedad);

													lmsscc_data = lscc_DAO.findMapAllByIdCausal(
														    ascc_camposCorreccion, false, false
														);

													if(CollectionUtils.isValidCollection(lmsscc_data))
														lp_panel.setLinderos(
														    lmsscc_data.containsKey(CamposCorreccionCommon.PL_LINDEROS)
														);

													lpcui_return.setLinderos(lp_panel);

													break;
												}

												case CausalCorreccionCommon.COMPLEMENTACION:
												{
													Map<String, SolicitudCamposCorreccion> lmsscc_data;
													PanelComplementacion                   lp_panel;

													lp_panel = new PanelComplementacion();

													lp_panel.setSeleccionado(true);
													lp_panel.setSalvedad(ls_salvedad);

													lmsscc_data = lscc_DAO.findMapAllByIdCausal(
														    ascc_camposCorreccion, false, false
														);

													if(CollectionUtils.isValidCollection(lmsscc_data))
														lp_panel.setComplementacion(
														    lmsscc_data.containsKey(
														        CamposCorreccionCommon.PC_COMPLEMENTACION
														    )
														);

													lpcui_return.setComplementacion(lp_panel);

													break;
												}

												case CausalCorreccionCommon.AREA_DEL_PREDIO:
												{
													Map<String, SolicitudCamposCorreccion> lmsscc_data;
													PanelAreaPredio                        lp_panel;

													lp_panel = new PanelAreaPredio();

													lp_panel.setSeleccionado(true);
													lp_panel.setSalvedad(ls_salvedad);

													lmsscc_data = lscc_DAO.findMapAllByIdCausal(
														    ascc_camposCorreccion, false, false
														);

													if(CollectionUtils.isValidCollection(lmsscc_data))
													{
														lp_panel.setAreaTerreno(
														    lmsscc_data.containsKey(
														        CamposCorreccionCommon.PAP_AREA_TERRENO
														    )
														);
														lp_panel.setAreaPrivada(
														    lmsscc_data.containsKey(
														        CamposCorreccionCommon.PAP_AREA_PRIVADA
														    )
														);
														lp_panel.setAreaConstruida(
														    lmsscc_data.containsKey(
														        CamposCorreccionCommon.PAP_AREA_CONSTRUIDA
														    )
														);
														lp_panel.setCoeficiente(
														    lmsscc_data.containsKey(
														        CamposCorreccionCommon.PAP_COEFICIENTE
														    )
														);
														lp_panel.setUsoPredio(
														    lmsscc_data.containsKey(
														        CamposCorreccionCommon.PAP_USO_PREDIO
														    )
														);
													}

													lpcui_return.setAreaPredio(lp_panel);

													break;
												}

												case CausalCorreccionCommon.DIRECCION_DEL_PREDIO:
												{
													Map<String, SolicitudCamposCorreccion> lmsscc_data;
													Map<String, SolicitudCamposCorreccion> lmsscc_agregar;
													PanelDireccionPredio                   lp_panel;

													lp_panel = new PanelDireccionPredio();

													ascc_camposCorreccion.setIdCamposCorreccion(
													    CamposCorreccionCommon.PDP_DIRECCIONES
													);

													lmsscc_data = lscc_DAO.findMapAllByIdCausalCirculoMatricula(
														    ascc_camposCorreccion, false, true, false
														);

													ascc_camposCorreccion.setIdCamposCorreccion(
													    CamposCorreccionCommon.PDP_AGREGAR
													);

													lmsscc_agregar = lscc_DAO.findMapAllByIdCausalCirculoMatricula(
														    ascc_camposCorreccion, false, false, false
														);

													if(CollectionUtils.isValidCollection(lmsscc_agregar))
													{
														if(!CollectionUtils.isValidCollection(lmsscc_data))
															lmsscc_data = lmsscc_agregar;
														else
															lmsscc_data.putAll(lmsscc_agregar);
													}

													lp_panel.setAgregar(
													    CollectionUtils.isValidCollection(lmsscc_agregar)
													);

													ascc_camposCorreccion.setIdCamposCorreccion(
													    CamposCorreccionCommon.PDP_AGREGAR
													);

													lmsscc_agregar = lscc_DAO.findMapAllByIdCausalCirculoMatricula(
														    ascc_camposCorreccion, false, true, false
														);

													lp_panel.setSeleccionado(true);
													lp_panel.setSalvedad(ls_salvedad);
													lp_panel.setChecks(lmsscc_data);
													lpcui_return.setDireccionPredio(lp_panel);

													break;
												}

												case CausalCorreccionCommon.DATOS_ANT_SISTEMA:
												{
													Map<String, SolicitudCamposCorreccion> lmsscc_data;
													PanelAntSistemaSolicitud               lp_panel;

													lp_panel     = new PanelAntSistemaSolicitud();

													lmsscc_data = lscc_DAO.findMapAllByIdCausal(
														    ascc_camposCorreccion, false, false
														);

													if(CollectionUtils.isValidCollection(lmsscc_data))
													{
														lp_panel.setCirculoRegistral(
														    lmsscc_data.containsKey(
														        CamposCorreccionCommon.PAS_CIRCULO_REGISTRAL
														    )
														);
														lp_panel.setTipoPredio(
														    lmsscc_data.containsKey(
														        CamposCorreccionCommon.PAS_TIPO_PREDIO
														    )
														);
														lp_panel.setNombrePredio(
														    lmsscc_data.containsKey(
														        CamposCorreccionCommon.PAS_NOMBRE_PREDIO
														    )
														);
														lp_panel.setPais(
														    lmsscc_data.containsKey(CamposCorreccionCommon.PAS_PAIS)
														);
														lp_panel.setDepartamento(
														    lmsscc_data.containsKey(
														        CamposCorreccionCommon.PAS_DEPARTAMENTO
														    )
														);
														lp_panel.setMunicipio(
														    lmsscc_data.containsKey(
														        CamposCorreccionCommon.PAS_MUNICIPIO
														    )
														);
														lp_panel.setCantidadCertificados(
														    lmsscc_data.containsKey(
														        CamposCorreccionCommon.PAS_CANTIDAD_CERTIFICADOS
														    )
														);
													}

													lp_panel.setSeleccionado(true);
													lp_panel.setSalvedad(ls_salvedad);
													lpcui_return.setAntSistema(lp_panel);

													break;
												}

												case CausalCorreccionCommon.DETALLE_ANT_SISTEMA:
												{
													PanelDetalleAntSistemaSolicitud lp_panel;

													lp_panel = lpcui_return.getDetalleAntSistema();

													if(lp_panel != null)
													{
														Map<String, SolicitudCamposCorreccion> lmsscc_data;
														Map<String, SolicitudCamposCorreccion> lmsscc_agregar;

														ascc_camposCorreccion.setIdCamposCorreccion(
														    CamposCorreccionCommon.PDAS_DETALLE_ANT_SISTEMA
														);

														lmsscc_data = lscc_DAO.findMapAllByIdCausalCirculoMatricula(
															    ascc_camposCorreccion, false, false, true
															);

														ascc_camposCorreccion.setIdCamposCorreccion(
														    CamposCorreccionCommon.PDAS_AGREGAR
														);

														lmsscc_agregar = lscc_DAO.findMapAllByIdCausalCirculoMatricula(
															    ascc_camposCorreccion, false, false, false
															);

														lp_panel.setChecks(lmsscc_data);
														lp_panel.setAgregar(
														    CollectionUtils.isValidCollection(lmsscc_agregar)
														);
														lp_panel.setSeleccionado(true);
														lp_panel.setSalvedad(ls_salvedad);
														lpcui_return.setDetalleAntSistema(lp_panel);
													}

													break;
												}

												case CausalCorreccionCommon.DATOS_ANOTACION:
												case CausalCorreccionCommon.DATOS_DEL_DOCUMENTO:
												case CausalCorreccionCommon.DETALLE_ANT_SISTEMA_ANOTACION:
												case CausalCorreccionCommon.ESPECIFICACION:
												case CausalCorreccionCommon.DATOS_BASICOS_DEL_INTEVINIENTES:
												{
													lb_anotaciones = true;

													break;
												}

												default:
													break;
											}
										}
									}
								}

								{
									Collection<Anotacion> lca_anotaciones;

									lca_anotaciones = ascc_camposCorreccion.getAnotacionesAgregadas();

									if(lb_anotaciones && CollectionUtils.isValidCollection(lca_anotaciones))
									{
										lcsc_data = lscd_DAO.findBySolicitudCirculoMatricula(lsc_param, true, true);

										if(CollectionUtils.isValidCollection(lcsc_data))
										{
											for(SolicitudCorreccion lsc_iterador : lcsc_data)
											{
												if(lsc_iterador != null)
												{
													String ls_idCausal;

													ls_idCausal = StringUtils.getString(
														    lsc_iterador.getIdCausalCorreccion()
														);

													if(StringUtils.isValidString(ls_idCausal))
													{
														Long ll_idCausal;

														ll_idCausal = NumericUtils.getLongWrapper(ls_idCausal);

														if(NumericUtils.isValidLong(ll_idCausal))
															ascc_camposCorreccion.setIdCausalCorreccion(ll_idCausal);

														for(Anotacion la_iterador : lca_anotaciones)
														{
															if(la_iterador != null)
															{
																ascc_camposCorreccion.setIdAnotacionRelacionado(
																    la_iterador.getIdAnotacion() + ""
																);

																switch(ls_idCausal)
																{
																	case CausalCorreccionCommon.DATOS_ANOTACION:
																	{
																		Map<String, SolicitudCamposCorreccion> lmsscc_data;
																		PanelDatosAnotacion                    lp_panel;

																		lp_panel     = la_iterador
																				.getPanelDatosAnotaciones();

																		lmsscc_data = lscc_DAO.findMapAllByIdCausal(
																			    ascc_camposCorreccion, true, false
																			);

																		if(
																		    CollectionUtils.isValidCollection(
																			        lmsscc_data
																			    )
																		)
																		{
																			lp_panel.setNumeroAnotacion(
																			    lmsscc_data.containsKey(
																			        CamposCorreccionCommon.PDA_NUMERO_ANOTACION
																			    )
																			);
																			lp_panel.setFechaAnotacion(
																			    lmsscc_data.containsKey(
																			        CamposCorreccionCommon.PDA_FECHA_ANOTACION
																			    )
																			);
																			lp_panel.setRadicacion(
																			    lmsscc_data.containsKey(
																			        CamposCorreccionCommon.PDA_RADICACION
																			    )
																			);
																			lp_panel.setEstadoAnotacion(
																			    lmsscc_data.containsKey(
																			        CamposCorreccionCommon.PDA_ESTADO_ANOTACION
																			    )
																			);
																		}

																		lp_panel.setSeleccionado(true);

																		break;
																	}

																	case CausalCorreccionCommon.DATOS_DEL_DOCUMENTO:
																	{
																		Map<String, SolicitudCamposCorreccion> lmsscc_data;
																		PanelDatosDocumento                    lp_panel;

																		lp_panel     = la_iterador
																				.getPanelDatosDocumento();

																		lmsscc_data = lscc_DAO.findMapAllByIdCausal(
																			    ascc_camposCorreccion, true, false
																			);

																		if(
																		    CollectionUtils.isValidCollection(
																			        lmsscc_data
																			    )
																		)
																		{
																			lp_panel.setTipoDocumento(
																			    lmsscc_data.containsKey(
																			        CamposCorreccionCommon.PDD_TIPO_DOCUMENTO
																			    )
																			);
																			lp_panel.setNumeroDocumento(
																			    lmsscc_data.containsKey(
																			        CamposCorreccionCommon.PDD_NUMERO_DOCUMENTO
																			    )
																			);
																			lp_panel.setFechaDocumento(
																			    lmsscc_data.containsKey(
																			        CamposCorreccionCommon.PDD_FECHA_DOCUMENTO
																			    )
																			);
																			lp_panel.setTipoOficina(
																			    lmsscc_data.containsKey(
																			        CamposCorreccionCommon.PDD_TIPO_OFICINA
																			    )
																			);
																			lp_panel.setTipoEntidad(
																			    lmsscc_data.containsKey(
																			        CamposCorreccionCommon.PDD_TIPO_ENTIDAD
																			    )
																			);
																			lp_panel.setPais(
																			    lmsscc_data.containsKey(
																			        CamposCorreccionCommon.PDD_PAIS
																			    )
																			);
																			lp_panel.setDepartamento(
																			    lmsscc_data.containsKey(
																			        CamposCorreccionCommon.PDD_DEPARTAMENTO
																			    )
																			);
																			lp_panel.setMunicipio(
																			    lmsscc_data.containsKey(
																			        CamposCorreccionCommon.PDD_MUNICIPIO
																			    )
																			);
																			lp_panel.setOficinaOrigen(
																			    lmsscc_data.containsKey(
																			        CamposCorreccionCommon.PDD_OFICINA_ORIGEN
																			    )
																			);
																		}

																		lp_panel.setSeleccionado(true);

																		break;
																	}

																	case CausalCorreccionCommon.DETALLE_ANT_SISTEMA_ANOTACION:
																	{
																		PanelDetalleAntSistemaAnotacion lp_panel;

																		lp_panel = la_iterador
																				.getPanelDetalleAntSistemaAnotacion();

																		if(lp_panel != null)
																		{
																			Map<String, SolicitudCamposCorreccion> lmsscc_data;

																			ascc_camposCorreccion.setIdCamposCorreccion(
																			    CamposCorreccionCommon.PDDASA_DETALLE_SELECCIONADO
																			);

																			lmsscc_data = lscc_DAO
																					.findMapAllByIdCausalCirculoMatricula(
																					    ascc_camposCorreccion, true,
																					    false, true
																					);

																			lp_panel.setCheks(lmsscc_data);
																			lp_panel.setSeleccionado(true);
																		}

																		break;
																	}

																	case CausalCorreccionCommon.ESPECIFICACION:
																	{
																		Map<String, SolicitudCamposCorreccion> lmsscc_data;
																		PanelEspecificacion                    lp_panel;

																		lp_panel     = la_iterador
																				.getPanelEspecificacion();

																		lmsscc_data = lscc_DAO.findMapAllByIdCausal(
																			    ascc_camposCorreccion, true, false
																			);

																		if(
																		    CollectionUtils.isValidCollection(
																			        lmsscc_data
																			    )
																		)
																		{
																			lp_panel.setCodigoActo(
																			    lmsscc_data.containsKey(
																			        CamposCorreccionCommon.PE_CODIGO_ACTO
																			    )
																			);
																			lp_panel.setDescripcionActo(
																			    lmsscc_data.containsKey(
																			        CamposCorreccionCommon.PE_DESCRIPCION_ACTO
																			    )
																			);
																			lp_panel.setValorActo(
																			    lmsscc_data.containsKey(
																			        CamposCorreccionCommon.PE_VALOR_ACTO
																			    )
																			);
																			lp_panel.setAnotacionCancela(
																			    lmsscc_data.containsKey(
																			        CamposCorreccionCommon.PE_ANOTACION_CANCELADA
																			    )
																			);
																			lp_panel.setComentario(
																			    lmsscc_data.containsKey(
																			        CamposCorreccionCommon.PE_COMENTARIOS
																			    )
																			);
																		}

																		lp_panel.setSeleccionado(true);

																		break;
																	}

																	case CausalCorreccionCommon.DATOS_BASICOS_DEL_INTEVINIENTES:
																	{
																		Collection<Anotacion> lca_intervinientes;

																		lca_intervinientes = la_iterador
																				.getIntervinientesAgregados();

																		if(
																		    CollectionUtils.isValidCollection(
																			        lca_intervinientes
																			    )
																		)
																		{
																			for(Anotacion la_actual : lca_intervinientes)
																			{
																				if(la_actual != null)
																				{
																					Persona                lp_persona;
																					SolicitudInterviniente lsi_solicitudInterviniente;

																					lsi_solicitudInterviniente     = la_actual
																							.getSolicitudInterviniente();
																					lp_persona                     = la_actual
																							.getPersona();

																					{
																						Map<String, SolicitudCamposCorreccion> lmsscc_data;
																						PanelIntervinientes                    lpi_panel;

																						lpi_panel = la_iterador
																								.getPanelIntervinientes();

																						lpi_panel.setSeleccionado(true);

																						lmsscc_data = lscc_DAO
																								.findMapAllByIdCausal(
																								    ascc_camposCorreccion,
																								    true, false
																								);

																						if(
																						    CollectionUtils
																							    .isValidCollection(lmsscc_data)
																						)
																							lpi_panel.setAgregar(
																							    lmsscc_data.containsKey(
																							        CamposCorreccionCommon.PDBI_AGREGAR
																							    )
																							);
																					}

																					if(
																					    (lp_persona != null)
																						    && (lsi_solicitudInterviniente != null)
																					)
																					{
																						String ls_rol;
																						String ls_idPersona;

																						ls_idPersona     = lp_persona
																								.getIdPersona();
																						ls_rol           = lsi_solicitudInterviniente
																								.getRolPersona();

																						if(
																						    StringUtils.isValidString(
																							        ls_idPersona
																							    )
																							    && StringUtils
																							    .isValidString(ls_rol)
																						)
																						{
																							Map<String, SolicitudCamposCorreccion> lmsscc_data;
																							PanelDetalleIntervinientes             lp_panel;

																							ascc_camposCorreccion
																								.setIdPersona(
																								    ls_idPersona
																								);
																							ascc_camposCorreccion
																								.setRolPersona(ls_rol);

																							lp_panel     = la_actual
																									.getPanelDetalleIntervinientes();

																							lmsscc_data = lscc_DAO
																									.findMapAllByIdCausal(
																									    ascc_camposCorreccion,
																									    true, true
																									);

																							if(
																							    CollectionUtils
																								    .isValidCollection(lmsscc_data)
																							)
																							{
																								boolean lb_datosPersona;
																								boolean lb_rol;
																								boolean lb_propietario;
																								boolean lb_porcentaje;
																								boolean lb_calidadInterviniente;

																								lb_datosPersona             = lmsscc_data
																										.containsKey(
																										    CamposCorreccionCommon.PDBI_DATOS_PERSONA
																										);
																								lb_rol                      = lmsscc_data
																										.containsKey(
																										    CamposCorreccionCommon.PDBI_ROL
																										);
																								lb_propietario              = lmsscc_data
																										.containsKey(
																										    CamposCorreccionCommon.PDBI_PROPIETARIO
																										);
																								lb_porcentaje               = lmsscc_data
																										.containsKey(
																										    CamposCorreccionCommon.PDBI_PORCENTAJE
																										);
																								lb_calidadInterviniente     = lmsscc_data
																										.containsKey(
																										    CamposCorreccionCommon.PDBI_CALIDAD_INTERVINIENTE
																										);

																								lp_panel.setDatosPersona(
																								    lb_datosPersona
																								);
																								lp_panel.setRol(lb_rol);
																								lp_panel.setPropietario(
																								    lb_propietario
																								);
																								lp_panel.setPorcentaje(
																								    lb_porcentaje
																								);
																								lp_panel
																									.setCalidadInterviniente(
																									    lb_calidadInterviniente
																									);
																								lp_panel
																									.setIntervinienteModificado(
																									    lb_datosPersona
																									    || lb_rol
																									    || lb_propietario
																									    || lb_porcentaje
																									    || lb_calidadInterviniente
																									);
																							}
																						}
																					}
																				}
																			}
																		}

																		break;
																	}

																	default:
																		break;
																}
															}
														}
													}
												}
											}
										}

										AccSalvedadAnotacionDAO lsa_DAO;

										lsa_DAO = DaoCreator.getAccSalvedadAnotacionDAO(ldm_manager);

										for(Anotacion la_iterador : lca_anotaciones)
										{
											if(la_iterador != null)
											{
												com.bachue.snr.prosnr01.model.sdb.acc.AnotacionPredio lap_anotacionPredio;

												lap_anotacionPredio = la_iterador.getAnotacionPredio();

												if(lap_anotacionPredio != null)
												{
													AccSalvedadAnotacion lsa_salvedad;

													lsa_salvedad = lsa_DAO.findById(
														    ll_idTurnoHistoria, ls_idTurno, ls_idCirculo, ll_idMatricula,
														    lap_anotacionPredio.getIdAnotacion()
														);

													if(lsa_salvedad != null)
														la_iterador.setSalvedad(lsa_salvedad.getDescripcion());
												}
											}
										}
									}

									lpcui_return.setAnotacionesAgregadas(lca_anotaciones);
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

			clh_LOGGER.error("cargarCheksCorecciones", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lpcui_return;
	}

	/**
	 * Método encargado de cargar la complementación para el proceso de englobes.
	 *
	 * @param arc_data Objeto que contiene la información con la cual ser hará la consulta.
	 * @return Objeto que contiene la complementación consultada.
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public synchronized ComplementacionCalificacion cargarComplementacionEnglobes(RegistroCalificacion arc_data)
	    throws B2BException
	{
		ComplementacionCalificacion lcc_return;
		DAOManager                  ldm_manager;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		lcc_return      = null;

		try
		{
			if(arc_data != null)
			{
				AccPredioRegistro lapr_predioRegistro;

				lapr_predioRegistro = DaoCreator.getAccPredioRegistroDAO(ldm_manager)
						                            .findByCirculoMatriculaTurno(
						    arc_data.getIdCirculo(), arc_data.getIdMatricula(), arc_data.getTurno()
						);

				if(lapr_predioRegistro != null)
				{
					Long ll_idComplementacion;

					lcc_return               = new ComplementacionCalificacion();
					ll_idComplementacion     = lapr_predioRegistro.getIdComplementacion();

					if(NumericUtils.isValidLong(ll_idComplementacion))
					{
						ComplementacionPredio lcp_complementacionPredio;

						lcp_complementacionPredio = new ComplementacionPredio();

						lcp_complementacionPredio.setIdComplementacion(StringUtils.getString(ll_idComplementacion));

						lcp_complementacionPredio = DaoCreator.getComplementacionPredioDAO(ldm_manager)
								                                  .findById(lcp_complementacionPredio);

						if(lcp_complementacionPredio != null)
						{
							AccComplementacionPredio lacp_complementacion;

							lacp_complementacion = new AccComplementacionPredio();

							lacp_complementacion.setIdComplementacionOriginal(ll_idComplementacion);
							lacp_complementacion.setIdTurno(arc_data.getTurno());

							lacp_complementacion = DaoCreator.getAccComplementacionPredioDAO(ldm_manager)
									                             .findByIdOriginal(lacp_complementacion);

							if(lacp_complementacion != null)
							{
								String ls_idTipoComplementacion;

								ls_idTipoComplementacion = lacp_complementacion.getTipoComplementacion();

								if(StringUtils.isValidString(ls_idTipoComplementacion))
								{
									if(ls_idTipoComplementacion.equalsIgnoreCase(TipoComplementacionCommon.N))
									{
										lcc_return.setCopiarEditar(true);
										lcc_return.setTipoComplementacion(TipoComplementacionCommon.NUEVA);
									}
									else if(ls_idTipoComplementacion.equalsIgnoreCase(TipoComplementacionCommon.C))
										lcc_return.setTipoComplementacion(TipoComplementacionCommon.COPIAR);
									else if(ls_idTipoComplementacion.equalsIgnoreCase(TipoComplementacionCommon.A))
										lcc_return.setTipoComplementacion(TipoComplementacionCommon.CONSTRUIR);
								}
							}

							lcc_return.setComplementacionPredio(lcp_complementacionPredio);
						}
					}
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("cargarComplementacionEnglobes", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lcc_return;
	}

	/**
	 * Método encargado de consultar los datos básicos para digitador masivo.
	 *
	 * @param arc_data Objeto que contiene la información del predio matriz.
	 * @return Objeto que contiene la información de los datos básicos.
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public synchronized DatosBasicos cargarDatosBasicosDigitadorMasivo(RegistroCalificacion arc_data)
	    throws B2BException
	{
		DAOManager   ldm_manager;
		DatosBasicos ldb_return;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		ldb_return      = null;

		try
		{
			if(arc_data != null)
			{
				AccPredioRegistro lapr_predioRegistro;

				lapr_predioRegistro = DaoCreator.getAccPredioRegistroDAO(ldm_manager)
						                            .findByCirculoMatriculaTurno(
						    arc_data.getIdCirculo(), arc_data.getIdMatricula(), arc_data.getIdTurno()
						);

				if(lapr_predioRegistro != null)
				{
					String ls_idZonaRegistral;

					ldb_return             = new DatosBasicos();
					ls_idZonaRegistral     = lapr_predioRegistro.getIdZonaRegistral();

					ldb_return.setAccPredioRegistro(lapr_predioRegistro);

					if(StringUtils.isValidString(ls_idZonaRegistral))
					{
						ZonaRegistral lzr_data;

						lzr_data = DaoCreator.getZonaRegistralDAO(ldm_manager).findById(ls_idZonaRegistral);

						if(lzr_data != null)
						{
							Departamento           ld_departamento;
							Municipio              lm_municipio;
							UbicacionZonaRegistral luzr_data;
							String                 ls_idPais;
							String                 ls_idDepartamento;
							String                 ls_idMunicipio;

							ld_departamento       = new Departamento();
							lm_municipio          = new Municipio();
							luzr_data             = new UbicacionZonaRegistral();
							ls_idPais             = lzr_data.getIdPais();
							ls_idDepartamento     = lzr_data.getIdDepartamento();
							ls_idMunicipio        = lzr_data.getIdMunicipio();

							ld_departamento.setIdPais(ls_idPais);
							ld_departamento.setIdDepartamento(ls_idDepartamento);
							lm_municipio.setIdPais(ls_idPais);
							lm_municipio.setIdDepartamento(ls_idDepartamento);
							lm_municipio.setIdMunicipio(ls_idMunicipio);
							luzr_data.setIdPais(ls_idPais);
							luzr_data.setDepartamento(ld_departamento);
							luzr_data.setMunicipio(lm_municipio);

							ldb_return.setUbicacion(luzr_data);
						}
					}
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("cargarDatosBasicosDigitadorMasivo", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return ldb_return;
	}

	/**
	 * Método encargado de cargar las direcciones para el proceso de englobes.
	 *
	 * @param arc_matriculas Objeto que contiene la información para realizar la consulta.
	 * @param ab_accion Variable de tipo booleana encargada de validar si se ejecuta o no una acción.
	 * @return Objeto que contiene la información del proceso.
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public synchronized RegistroCalificacion cargarDireccionesEnglobes(
	    RegistroCalificacion arc_matriculas, boolean ab_accion
	)
	    throws B2BException
	{
		DAOManager ldm_manager;

		ldm_manager = DaoManagerFactory.getDAOManager();

		try
		{
			if(arc_matriculas != null)
			{
				if(ab_accion)
				{
					Collection<RegistroCalificacion> lrc_matriculas;

					lrc_matriculas = arc_matriculas.getAllMatriculas();

					if(CollectionUtils.isValidCollection(lrc_matriculas))
					{
						Collection<DireccionPredio>    lcdp_return;
						Iterator<RegistroCalificacion> lirc_iterator;

						lcdp_return       = new ArrayList<DireccionPredio>();
						lirc_iterator     = lrc_matriculas.iterator();

						while(lirc_iterator.hasNext())
						{
							RegistroCalificacion lrc_dataMatricula;

							lrc_dataMatricula = lirc_iterator.next();

							if(lrc_dataMatricula != null)
							{
								String ls_matriculaCompleta;

								ls_matriculaCompleta = lrc_dataMatricula.getIdCirculo();

								if(StringUtils.isValidString(ls_matriculaCompleta))
								{
									int    li_inicio;
									Long   ll_idMatricula;
									String ls_idCirculo;

									li_inicio          = ls_matriculaCompleta.lastIndexOf("-");
									ll_idMatricula     = NumericUtils.getLongWrapper(
										    ls_matriculaCompleta.substring(
										        li_inicio + 1, ls_matriculaCompleta.length()
										    )
										);
									ls_idCirculo       = ls_matriculaCompleta.substring(0, li_inicio);

									if(
									    StringUtils.isValidString(ls_idCirculo)
										    && NumericUtils.isValidLong(ll_idMatricula)
									)
									{
										Collection<DireccionPredio> lcdp_direcciones;
										DireccionPredio             ldp_direccionPredio;

										ldp_direccionPredio = new DireccionPredio();

										ldp_direccionPredio.setIdCirculo(ls_idCirculo);
										ldp_direccionPredio.setIdMatricula(
										    NumericUtils.getLongWrapper(NumericUtils.getLong(ll_idMatricula))
										);

										lcdp_direcciones = DaoCreator.getDireccionPredioDAO(ldm_manager)
												                         .findByIdCirculoMatricula(ldp_direccionPredio);

										if(CollectionUtils.isValidCollection(lcdp_direcciones))
										{
											lrc_dataMatricula.setDirecciones(lcdp_direcciones);
											lcdp_return.addAll(lcdp_direcciones);
										}
									}
								}
							}
						}

						arc_matriculas.setDirecciones(lcdp_return);
					}
				}
				else
				{
					Long   ll_idMatricula;
					String ls_idCirculo;

					ll_idMatricula     = arc_matriculas.getIdMatricula();
					ls_idCirculo       = arc_matriculas.getIdCirculo();

					if(StringUtils.isValidString(ls_idCirculo) && NumericUtils.isValidLong(ll_idMatricula))
					{
						Collection<DireccionPredioAcc> lcdpa_direcciones;
						DireccionPredioAcc             ldpa_direccionPredio;
						DireccionPredioAccDAO          ldpa_DAO;

						ldpa_direccionPredio     = new DireccionPredioAcc();
						ldpa_DAO                 = DaoCreator.getDireccionPredioAccDAO(ldm_manager);

						ldpa_direccionPredio.setIdCirculo(ls_idCirculo);
						ldpa_direccionPredio.setIdMatricula(ll_idMatricula);

						lcdpa_direcciones = ldpa_DAO.findByIdCirculoMatricula(ldpa_direccionPredio);

						if(CollectionUtils.isValidCollection(lcdpa_direcciones))
						{
							Iterator<DireccionPredioAcc> lidpa_iterator;

							lidpa_iterator = lcdpa_direcciones.iterator();

							while(lidpa_iterator.hasNext())
							{
								DireccionPredioAcc ldpa_iterador;

								ldpa_iterador = lidpa_iterator.next();

								if(ldpa_iterador != null)
									ldpa_iterador.setSeleccionado(true);
							}
						}

						arc_matriculas.setDireccionesAcc(lcdpa_direcciones);
					}
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("cargarDireccionesEnglobes", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return arc_matriculas;
	}

	/**
	 * Método encargado de consultar los las direcciones del predio temporal.
	 *
	 * @param arc_data Objeto que contiene la información del predio a consultar.
	 * @return Objeto que contiene la información de la dirección.
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public RegistroCalificacion cargarDireccionesPredioTemporal(RegistroCalificacion arc_data)
	    throws B2BException
	{
		DAOManager           ldm_manager;
		RegistroCalificacion lrc_return;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		lrc_return      = null;

		if(arc_data != null)
		{
			try
			{
				AccPredioRegistro lpr_predioRegistro;
				Long              ll_idMatricula;
				String            ls_idCirculo;
				String            ls_idTurno;

				ll_idMatricula     = arc_data.getIdMatricula();
				ls_idCirculo       = arc_data.getIdCirculo();
				ls_idTurno         = arc_data.getIdTurno();

				lpr_predioRegistro = DaoCreator.getAccPredioRegistroDAO(ldm_manager)
						                           .findByCirculoMatriculaTurno(
						    ls_idCirculo, ll_idMatricula, ls_idTurno
						);

				if(lpr_predioRegistro != null)
				{
					DatosBasicos                   ldb_datosBasicos;
					DireccionPredioAcc             ldpa_param;
					Collection<DireccionPredioAcc> lcdpa_data;
					Collection<DireccionDelPredio> lcddp_return;
					PredioTipoDAO                  lpt_DAO;
					UbicacionZonaRegistral         luzrt_ubicacion;

					ldb_datosBasicos     = new DatosBasicos();
					ldpa_param           = new DireccionPredioAcc();
					lcddp_return         = null;
					lpt_DAO              = DaoCreator.getPredioTipoDao(ldm_manager);
					lrc_return           = new RegistroCalificacion();
					luzrt_ubicacion      = new UbicacionZonaRegistral();

					ldb_datosBasicos.setAccPredioRegistro(lpr_predioRegistro);

					{
						ZonaRegistral lzr_zonaRegistral;

						lzr_zonaRegistral = new ZonaRegistral();

						lzr_zonaRegistral.setIdZonaRegistral(lpr_predioRegistro.getIdZonaRegistral());

						lzr_zonaRegistral = DaoCreator.getZonaRegistralDAO(ldm_manager).findById(lzr_zonaRegistral);

						if(lzr_zonaRegistral != null)
						{
							String ls_idPais;
							String ls_idDepartamento;
							String ls_idMunicipio;
							String ls_idVereda;

							ls_idPais             = lzr_zonaRegistral.getIdPais();
							ls_idDepartamento     = lzr_zonaRegistral.getIdDepartamento();
							ls_idMunicipio        = lzr_zonaRegistral.getIdMunicipio();
							ls_idVereda           = lzr_zonaRegistral.getIdVereda();

							if(StringUtils.isValidString(ls_idPais))
							{
								Pais lp_pais;

								lp_pais = new Pais();

								lp_pais.setIdPais(ls_idPais);

								lp_pais = DaoCreator.getPaisDAO(ldm_manager).findById(lp_pais);

								if(lp_pais != null)
									luzrt_ubicacion.setIdPais(lp_pais.getIdPais());
							}

							if(StringUtils.isValidString(ls_idDepartamento))
							{
								Departamento ld_departamento;

								ld_departamento = new Departamento();

								ld_departamento.setIdPais(ls_idPais);
								ld_departamento.setIdDepartamento(ls_idDepartamento);

								ld_departamento = DaoCreator.getDepartamentoDAO(ldm_manager).findById(ld_departamento);

								if(ld_departamento != null)
									luzrt_ubicacion.setDepartamento(ld_departamento);
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
									luzrt_ubicacion.setMunicipio(lm_municipio);
							}

							if(StringUtils.isValidString(ls_idVereda))
							{
								Vereda lv_vereda;

								lv_vereda = new Vereda();

								lv_vereda.setIdPais(ls_idPais);
								lv_vereda.setIdDepartamento(ls_idDepartamento);
								lv_vereda.setIdMunicipio(ls_idMunicipio);
								lv_vereda.setIdVereda(ls_idVereda);

								lv_vereda = DaoCreator.getVeredaDAO(ldm_manager).findById(lv_vereda);

								if(lv_vereda != null)
									luzrt_ubicacion.setVereda(lv_vereda);
							}

							ldb_datosBasicos.setUbicacion(luzrt_ubicacion);
						}
					}

					lrc_return.setDatosBasicos(ldb_datosBasicos);

					ldpa_param.setIdMatricula(ll_idMatricula);
					ldpa_param.setIdCirculo(ls_idCirculo);
					ldpa_param.setIdTurno(ls_idTurno);

					lcdpa_data = DaoCreator.getDireccionPredioAccDAO(ldm_manager)
							                   .findAllByIdCirculoMatriculaTurno(ldpa_param);

					if(CollectionUtils.isValidCollection(lcdpa_data))
					{
						lcddp_return = new ArrayList<DireccionDelPredio>();

						for(DireccionPredioAcc ldpa_actual : lcdpa_data)
						{
							if(ldpa_actual != null)
							{
								DireccionDelPredio lddp_ddp;
								DatosAntSistema    ldas_datosAntiguoSistema;

								lddp_ddp                     = new DireccionDelPredio();
								ldas_datosAntiguoSistema     = new DatosAntSistema();

								if(luzrt_ubicacion != null)
								{
									ldas_datosAntiguoSistema.setIdPais(luzrt_ubicacion.getIdPais());

									{
										Departamento ld_d;

										ld_d = luzrt_ubicacion.getDepartamento();

										if(ld_d != null)
											ldas_datosAntiguoSistema.setIdDepartamento(ld_d.getIdDepartamento());
									}

									{
										Municipio lm_m;

										lm_m = luzrt_ubicacion.getMunicipio();

										if(lm_m != null)
											ldas_datosAntiguoSistema.setIdMunicipio(lm_m.getIdMunicipio());
									}

									{
										Vereda lv_v;

										lv_v = luzrt_ubicacion.getVereda();

										if(lv_v != null)
											ldas_datosAntiguoSistema.setIdVereda(lv_v.getIdVereda());
									}
								}

								{
									PredioTipo lpt_predioTipo;

									lpt_predioTipo = new PredioTipo();

									lpt_predioTipo.setIdTipoPredio(lpr_predioRegistro.getIdTipoPredio());

									lpt_predioTipo = lpt_DAO.findById(lpt_predioTipo);

									if(lpt_predioTipo != null)
										ldas_datosAntiguoSistema.setNombrePredio(lpt_predioTipo.getDescripcion());
								}

								ldas_datosAntiguoSistema.setIdTipoPredio(lpr_predioRegistro.getIdTipoPredio());

								lddp_ddp.setDatosAntiguoSistema(ldas_datosAntiguoSistema);

								lddp_ddp.setDireccionPredio(ldpa_actual);
								lddp_ddp.setDireccion(ldpa_actual.getDireccion());

								lcddp_return.add(lddp_ddp);
							}
						}
					}
				}
			}
			catch(B2BException lb2be_e)
			{
				ldm_manager.setRollbackOnly();

				clh_LOGGER.error("cargarDireccionesPredioTemporal", lb2be_e);

				throw lb2be_e;
			}
			finally
			{
				ldm_manager.close();
			}
		}

		return lrc_return;
	}

	/**
	 * Método encargado de cargar informacion del englobe para una anotacion.
	 *
	 * @param aa_anotacion Objeto que contiene la información Anotacion a consultar.
	 * @return Objeto que contiene la información de RegistroCalificacion.
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public synchronized RegistroCalificacion cargarInfoEnglobeAnotacion(Anotacion aa_anotacion)
	    throws B2BException
	{
		DAOManager           ldm_manager;
		RegistroCalificacion lrc_return;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		lrc_return      = null;

		try
		{
			if(aa_anotacion != null)
			{
				com.bachue.snr.prosnr01.model.sdb.acc.AnotacionPredio lap_anotacionPredio;

				lap_anotacionPredio = aa_anotacion.getAnotacionPredio();

				if(lap_anotacionPredio != null)
				{
					Collection<SolicitudMatricula> lcsm_matriculas;
					String                         ls_idSolicitud;

					ls_idSolicitud      = lap_anotacionPredio.getIdSolicitud();
					lcsm_matriculas     = DaoCreator.getSolicitudMatriculaDAO(ldm_manager)
							                            .findEnglobesBySolicitudCirculo(
							    lap_anotacionPredio.getIdCirculo(), ls_idSolicitud
							);

					if(CollectionUtils.isValidCollection(lcsm_matriculas))
					{
						Collection<RegistroCalificacion> lcrc_data;
						EstadoPredioDao                  lep_DAO;
						Long                             ll_idTurnoHistoria;
						Long                             ll_idMatriculaMatriz;
						PredioRegistroDAO                lpr_DAO;
						String                           ls_idTurno;
						String                           ls_idCirculoMatriz;
						ZonaRegistralDAO                 lzr_DAO;

						lcrc_data                = new ArrayList<RegistroCalificacion>();
						lep_DAO                  = DaoCreator.getEstadoPredioDao(ldm_manager);
						lpr_DAO                  = DaoCreator.getPredioRegistroDAO(ldm_manager);
						ls_idTurno               = lap_anotacionPredio.getIdTurno();
						ll_idTurnoHistoria       = NumericUtils.getLongWrapper(
							    lap_anotacionPredio.getIdTurnoHistoria()
							);
						ll_idMatriculaMatriz     = lap_anotacionPredio.getIdMatricula();
						ls_idCirculoMatriz       = lap_anotacionPredio.getIdCirculo();
						lzr_DAO                  = DaoCreator.getZonaRegistralDAO(ldm_manager);
						lrc_return               = new RegistroCalificacion();

						for(SolicitudMatricula lsm_iterador : lcsm_matriculas)
						{
							if(lsm_iterador != null)
							{
								long                 ll_idMatricula;
								PredioRegistro       lpr_predioRegistro;
								RegistroCalificacion lorc_tmp;
								String               ls_idCirculo;

								ll_idMatricula         = lsm_iterador.getIdMatricula();
								lpr_predioRegistro     = new PredioRegistro();
								lorc_tmp               = new RegistroCalificacion();
								ls_idCirculo           = lsm_iterador.getIdCirculo();

								lpr_predioRegistro.setIdCirculo(ls_idCirculo);
								lpr_predioRegistro.setIdMatricula(ll_idMatricula);
								lpr_predioRegistro.setValidMatricula(true);
								lorc_tmp.setIdCirculo(
								    ls_idCirculo + IdentificadoresCommon.SIMBOLO_GUION + ll_idMatricula
								);

								lpr_predioRegistro = lpr_DAO.findById(lpr_predioRegistro);

								if(lpr_predioRegistro != null)
								{
									String ls_idZonaRegistral;

									ls_idZonaRegistral = lpr_predioRegistro.getIdZonaRegistral();

									if(StringUtils.isValidString(ls_idZonaRegistral))
									{
										ZonaRegistral lzr_zonaRegistral;

										lzr_zonaRegistral = new ZonaRegistral();

										lzr_zonaRegistral.setIdZonaRegistral(ls_idZonaRegistral);

										lzr_zonaRegistral = lzr_DAO.findById(lzr_zonaRegistral);

										if(lzr_zonaRegistral != null)
											lorc_tmp.setZonaRegistral(lzr_zonaRegistral);
									}

									String ls_idEstadoPredio;

									ls_idEstadoPredio = lpr_predioRegistro.getIdEstadoPredio();

									if(StringUtils.isValidString(ls_idEstadoPredio))
									{
										EstadoPredio lep_estadoPredio;

										lep_estadoPredio = new EstadoPredio();

										lep_estadoPredio.setIdEstadoPredio(ls_idEstadoPredio);

										lep_estadoPredio = lep_DAO.findById(lep_estadoPredio);

										if(lep_estadoPredio != null)
											lorc_tmp.setEstadoPredio(lep_estadoPredio.getIdEstadoPredio());
									}
								}

								lcrc_data.add(lorc_tmp);
							}
						}

						{
							Collection<com.bachue.snr.prosnr01.model.sdb.acc.PredioSegregado> lcps_data;
							com.bachue.snr.prosnr01.model.sdb.acc.PredioSegregado             lps_param;

							lps_param = new com.bachue.snr.prosnr01.model.sdb.acc.PredioSegregado();

							lps_param.setIdTurno(ls_idTurno);
							lps_param.setIdMatricula(NumericUtils.getLong(ll_idMatriculaMatriz));
							lps_param.setIdCirculo(ls_idCirculoMatriz);

							lcps_data = DaoCreator.getAccPredioSegregadoDAO(ldm_manager)
									                  .findAllByCirculoMatriculaTurno(lps_param);

							if(CollectionUtils.isValidCollection(lcps_data))
							{
								boolean                                                         lb_continuar;
								Iterator<com.bachue.snr.prosnr01.model.sdb.acc.PredioSegregado> lips_iterator;

								lb_continuar      = true;
								lips_iterator     = lcps_data.iterator();

								while(lips_iterator.hasNext() && lb_continuar)
								{
									com.bachue.snr.prosnr01.model.sdb.acc.PredioSegregado lps_iterador;

									lps_iterador = lips_iterator.next();

									if(lps_iterador != null)
									{
										lrc_return.setIdMatricula(
										    NumericUtils.getLongWrapper(lps_iterador.getIdMatricula1())
										);
										lrc_return.setMatriculaSeleccionada(true);

										lb_continuar = false;
									}
								}
							}
						}

						{
							Solicitud ls_solicitud;

							ls_solicitud = DaoCreator.getSolicitudDAO(ldm_manager).findById(ls_idSolicitud);

							if(ls_solicitud != null)
							{
								String ls_idDocumento;

								ls_idDocumento = ls_solicitud.getIdDocumento();

								if(!StringUtils.isValidString(ls_idDocumento))
									ls_idDocumento = lap_anotacionPredio.getIdDocumento();

								if(StringUtils.isValidString(ls_idDocumento))
								{
									Documento ld_documento;

									ld_documento = new Documento();

									ld_documento.setIdDocumento(ls_idDocumento);

									ld_documento = DaoCreator.getDocumentoDAO(ldm_manager).findById(ld_documento);

									if(ld_documento != null)
									{
										String     ls_idOficinaOrigen;
										BigDecimal lbd_version;

										ls_idOficinaOrigen     = ld_documento.getIdOficinaOrigen();
										lbd_version            = ld_documento.getVersion();

										if(StringUtils.isValidString(ls_idOficinaOrigen) && (lbd_version != null))
										{
											OficinaOrigen loo_oficinaOrigen;

											loo_oficinaOrigen = new OficinaOrigen();

											loo_oficinaOrigen.setIdOficinaOrigen(ls_idOficinaOrigen);
											loo_oficinaOrigen.setVersion(lbd_version);

											loo_oficinaOrigen = DaoCreator.getOficinaOrigenDAO(ldm_manager)
													                          .findById(loo_oficinaOrigen);

											if(loo_oficinaOrigen != null)
											{
												ld_documento.setIdTipoOficina(loo_oficinaOrigen.getIdTipoOficina());
												ld_documento.setIdPais(loo_oficinaOrigen.getIdPais());
												ld_documento.setIdDepartamento(loo_oficinaOrigen.getIdDepartamento());
												ld_documento.setIdMunicipio(loo_oficinaOrigen.getIdMunicipio());
											}
										}

										lrc_return.setDataDocumento(ld_documento);
									}
								}
							}
						}

						lrc_return.setTurno(ls_idTurno);
						lrc_return.setIdTurnoHistoria(ll_idTurnoHistoria);
						lrc_return.setIdMatriculaMatriz(ll_idMatriculaMatriz);
						lrc_return.setIdCirculoMatriz(ls_idCirculoMatriz);
						lrc_return.setIdCirculo(ls_idCirculoMatriz);
						lrc_return.setFechaRadicacion(new Date());
						lrc_return.setRadicacion(ls_idTurno);
						lrc_return.setAllMatriculas(lcrc_data);
						lrc_return.setEnglobe(true);
					}
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("cargarInfoEnglobeAnotacion", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lrc_return;
	}

	/**
	 * Método encargado de cargar los linderos para el proceso de englobes.
	 *
	 * @param arc_data Objeto que contiene la información para realizar la consulta.
	 * @return Objeto que contiene la información del proceso.
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public synchronized LinderoRegistroCalificacion cargarLinderosEnglobes(RegistroCalificacion arc_data)
	    throws B2BException
	{
		DAOManager                  ldm_manager;
		LinderoRegistroCalificacion llrc_return;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		llrc_return     = null;

		try
		{
			if(arc_data != null)
			{
				AccLinderoPredio lalp_lindero;

				lalp_lindero = new AccLinderoPredio();

				lalp_lindero.setIdCirculo(arc_data.getIdCirculo());
				lalp_lindero.setIdMatricula(arc_data.getIdMatricula());
				lalp_lindero.setIdTurno(arc_data.getTurno());

				lalp_lindero = DaoCreator.getAccLinderoPredioDAO(ldm_manager).findByCirculoMatriculaTurno(lalp_lindero);

				if(lalp_lindero != null)
				{
					llrc_return = new LinderoRegistroCalificacion();

					llrc_return.setLindero(lalp_lindero.getLindero());
					llrc_return.setIdCirculo(lalp_lindero.getIdCirculo());
					llrc_return.setIdMatricula(lalp_lindero.getIdMatricula());
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("cargarLinderosEnglobes", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return llrc_return;
	}

	/**
	 * Método encargado de consultar la justificación del documento generado.
	 *
	 * @param as_idTurno Variable de tipo String que contiene el id del turno.
	 * @param as_motivoTramite Variable de tipo String que contiene el motivo trámite.
	 * @return String que contiene la justificación encontrada.
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see OficiosTexto
	 */
	public synchronized OficiosTexto cargarOficioTextoTraslados(String as_idTurno, String as_motivoTramite)
	    throws B2BException
	{
		DAOManager   ldm_manager;
		OficiosTexto lot_return;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		lot_return      = null;

		try
		{
			if(StringUtils.isValidString(as_idTurno) && StringUtils.isValidString(as_motivoTramite))
			{
				String ls_tipoArchivo;

				ls_tipoArchivo = null;

				if(
				    as_motivoTramite.equalsIgnoreCase(
					        StringUtils.getString(MotivoTramiteCommon.PROYECTAR_RESOLUCION_TRASLADOS)
					    )
				)
					ls_tipoArchivo = TipoArchivoCommon.RESOLUCION_TRASLADOS;
				else if(
				    as_motivoTramite.equalsIgnoreCase(
					        StringUtils.getString(MotivoTramiteCommon.NEGACION_SOLICITUD_TRASLADOS)
					    )
				)
					ls_tipoArchivo = TipoArchivoCommon.NEGACION_TRASLADOS;

				if(StringUtils.isValidString(ls_tipoArchivo))
				{
					DocumentosSalida lds_documento;

					lds_documento = DaoCreator.getDocumentosSalidaDAO(ldm_manager)
							                      .findByIdTurnoTipo(as_idTurno, ls_tipoArchivo);

					if(lds_documento != null)
					{
						Integer li_idTurnoHistoria;

						li_idTurnoHistoria = lds_documento.getIdTurnoHistoria();

						if(NumericUtils.isValidInteger(li_idTurnoHistoria))
							lot_return = DaoCreator.getOficiosTextoDAO(ldm_manager)
									                   .findByTurnoHistoria(
									    NumericUtils.getLongWrapper(li_idTurnoHistoria)
									);
					}
				}
			}
			else
				throw new B2BException(ErrorKeys.ERROR_INTERNO_SISTEMA);
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("cargarOficioTextoTraslados", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lot_return;
	}

	/**
	 * Método encargado de validar si la anotación contiene un acto de englobes y cargar los predios de la correccion.
	 *
	 * @param aap_anotacionPredio Objeto que contiene la información de la anotación a validar.
	 * @return Colección que contiene los predios de la corrección.
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public synchronized Collection<SolicitudMatricula> cargarPrediosEnglobeCorreccion(
	    com.bachue.snr.prosnr01.model.sdb.acc.AnotacionPredio aap_anotacionPredio
	)
	    throws B2BException
	{
		Collection<SolicitudMatricula> lcsc_return;
		DAOManager                     ldm_manager;

		lcsc_return     = null;
		ldm_manager     = DaoManagerFactory.getDAOManager();

		try
		{
			if((aap_anotacionPredio != null) && validarAnotacionEnglobe(aap_anotacionPredio, ldm_manager))
			{
				Turno lt_turno;

				lt_turno = DaoCreator.getTurnoDAO(ldm_manager).findById(aap_anotacionPredio.getIdTurno());

				if(lt_turno != null)
				{
					lcsc_return = DaoCreator.getSolicitudCorreccionDAO(ldm_manager)
							                    .findCirculoMatriculaBySolicitud(
							    lt_turno.getIdSolicitud(), aap_anotacionPredio.getIdCirculo()
							);

					if(CollectionUtils.isValidCollection(lcsc_return))
					{
						if(lcsc_return.size() < 2)
							throw new B2BException(ErrorKeys.ERROR_ENGLOBES_CANTIDAD_PREDIOS);
						else
						{
							Iterator<SolicitudMatricula> lism_iterator;

							lism_iterator = lcsc_return.iterator();

							if(lism_iterator != null)
							{
								boolean lb_detener;
								Long    ll_idMatricula;

								lb_detener         = false;
								ll_idMatricula     = aap_anotacionPredio.getIdMatricula();

								while(lism_iterator.hasNext() && !lb_detener)
								{
									SolicitudMatricula lsm_iterador;

									lsm_iterador = lism_iterator.next();

									if(lsm_iterador != null)
									{
										Long ll_idMatriculaIterador;

										ll_idMatriculaIterador     = NumericUtils.getLongWrapper(
											    lsm_iterador.getIdMatricula()
											);
										lb_detener                 = (ll_idMatriculaIterador != null)
												&& ll_idMatricula.equals(ll_idMatriculaIterador);

										lsm_iterador.setSeleccionado(lb_detener);
										lsm_iterador.setBloquear(lb_detener);
									}
								}
							}
						}
					}
					else
						throw new B2BException(ErrorKeys.ERROR_ENGLOBES_CANTIDAD_PREDIOS);
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("cargarPrediosEnglobeCorreccion", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lcsc_return;
	}

	/**
	 * Método encargado de validar si se debe habilitar un cierre de folio.
	 *
	 * @param acps_predios Colección que contiene los predios a validar.
	 * @return Variable tipo boolean que indica si se debe habilitar cierre de folio.
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public boolean cierreFolio(Collection<PredioSegregado> acps_predios)
	    throws B2BException
	{
		boolean    lb_return;
		boolean    lb_regla;
		DAOManager ldm_manager;

		lb_return       = false;
		lb_regla        = false;
		ldm_manager     = DaoManagerFactory.getDAOManager();

		try
		{
			if(CollectionUtils.isValidCollection(acps_predios))
			{
				com.bachue.snr.prosnr01.dao.bng.AnotacionPredioDAO lap_DAO;
				NaturalezaJuridicaDAO                              lnj_DAO;

				lap_DAO     = DaoCreator.getAnotacionPredioDAO(ldm_manager);
				lnj_DAO     = DaoCreator.getNaturalezaJuridicaDAO(ldm_manager);

				for(PredioSegregado lps_actual : acps_predios)
				{
					if((lps_actual != null) && (!lb_return || !lb_regla))
					{
						Long   ll_idAnotacion;
						Long   ll_idMatricula;
						String ls_idCirculo;

						ll_idAnotacion     = lps_actual.getIdAnotacion1();
						ll_idMatricula     = lps_actual.getIdMatricula1();
						ls_idCirculo       = lps_actual.getIdCirculo();

						if(NumericUtils.isValidLong(ll_idAnotacion))
						{
							com.bachue.snr.prosnr01.model.sdb.bng.AnotacionPredio lap_anotacionPredio;

							lap_anotacionPredio = null;

							if(lps_actual.isAnotacionesAcc())
								lap_anotacionPredio = lap_DAO.findByIdAcc(ls_idCirculo, ll_idMatricula, ll_idAnotacion);
							else
								lap_anotacionPredio = lap_DAO.findById(ls_idCirculo, ll_idMatricula, ll_idAnotacion);

							if(lap_anotacionPredio != null)
							{
								NaturalezaJuridica lnj_data;

								lnj_data = lnj_DAO.findByIdMaxVersion(lap_anotacionPredio.getIdNaturalezaJuridica());

								if(lnj_data != null)
									lb_return = lnj_data.isRequiereCierreFolio();
							}
						}
					}
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("consultarTurnoHistoriaAnterior", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lb_return && lb_regla;
	}

	/**
	 * Método encargado de confirmar la cantidad de predios a aperturar por una anotación.
	 *
	 * @param aa_anotacion Objeto que contiene la información de la anotación.
	 * @param as_userId Variable que contiene el id del usuario.
	 * @param as_remoteIp Variable que contiene la ip del usuario.
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public synchronized void confirmarCantidadAperturarAnotacion(
	    Anotacion aa_anotacion, String as_userId, String as_remoteIp
	)
	    throws B2BException
	{
		DAOManager ldm_manager;

		ldm_manager = DaoManagerFactory.getDAOManager();

		try
		{
			if(aa_anotacion != null)
			{
				int li_cantidadAperturar;

				li_cantidadAperturar = aa_anotacion.getCantidadAperturar();

				if(li_cantidadAperturar > 0)
				{
					AccPredioRegistro apr_predioRegistro;
					TurnoHistoria     lth_turnoHistoria;

					apr_predioRegistro     = aa_anotacion.getAccPredioRegistro();
					lth_turnoHistoria      = DaoCreator.getTurnoHistoriaDAO(ldm_manager)
							                               .findById(aa_anotacion.getTurnoHistoria());

					if((lth_turnoHistoria != null) && (apr_predioRegistro != null))
					{
						com.bachue.snr.prosnr01.model.sdb.acc.AnotacionPredio lap_anotacionPredio;
						AnotacionPredioDAO                                    lap_DAO;
						Long                                                  ll_idAnotacion;
						Long                                                  ll_idMatricula;
						String                                                ls_idCirculo;
						String                                                ls_idTurno;

						lap_anotacionPredio     = new com.bachue.snr.prosnr01.model.sdb.acc.AnotacionPredio();
						lap_DAO                 = DaoCreator.getAccAnotacionPredioDAO(ldm_manager);
						ll_idAnotacion          = NumericUtils.getLongWrapper(aa_anotacion.getIdAnotacion());
						ll_idMatricula          = apr_predioRegistro.getIdMatricula();
						ls_idCirculo            = apr_predioRegistro.getIdCirculo();
						ls_idTurno              = lth_turnoHistoria.getIdTurno();

						lap_anotacionPredio.setIdAnotacion(ll_idAnotacion);
						lap_anotacionPredio.setIdTurno(ls_idTurno);
						lap_anotacionPredio.setIdMatricula(ll_idMatricula);
						lap_anotacionPredio.setIdCirculo(ls_idCirculo);

						lap_anotacionPredio = lap_DAO.findByCirculoMatriculaTurnoAnotacion(lap_anotacionPredio);

						if(lap_anotacionPredio != null)
						{
							lap_anotacionPredio.setIdUsuarioModificacion(as_userId);
							lap_anotacionPredio.setIpModificacion(as_remoteIp);
							lap_anotacionPredio.setCantidadAperturar(li_cantidadAperturar);

							lap_DAO.updateCantidadAperturar(lap_anotacionPredio);
						}
					}
				}
				else
					throw new B2BException(ErrorKeys.CANTIDAD_MATRICULAS_APERTURAR_CERO);
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("confirmarCantidadAperturarAnotacion", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}
	}

	/**
	 * Método encargado de confirmar las matrículas seleccionadas para el englobe.
	 *
	 * @param aa_anotacion Objeto que contiene la información de la anotación.
	 * @param as_idCirculo Variable que contiene el id del circulo.
	 * @param al_idMatricula Variable que contiene el id de la matrícula.
	 * @param as_idTurno Variable que contiene el id del turno.
	 * @param as_userId Variable que contiene el id del usuario.
	 * @param as_remoteIp Variable que contiene la ip del usuario.
	 * @return Objeto que contiene la información de la anotación.
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public synchronized Anotacion confirmarMatriculasEnglobe(
	    String as_idCirculo, Long al_idMatricula, String as_idTurno, Anotacion aa_anotacion, String as_userId,
	    String as_remoteIp
	)
	    throws B2BException
	{
		DAOManager ldm_manager;

		ldm_manager = DaoManagerFactory.getDAOManager();

		try
		{
			if(aa_anotacion != null)
			{
				AnotacionPredioDAO                                    lap_DAO;
				com.bachue.snr.prosnr01.model.sdb.acc.AnotacionPredio lap_anotacionPredio;
				Collection<SolicitudMatricula>                        lcsm_matriculas;

				lap_DAO             = DaoCreator.getAccAnotacionPredioDAO(ldm_manager);
				lcsm_matriculas     = aa_anotacion.getMatriculasCorreccion();

				lap_anotacionPredio = lap_DAO.findById(aa_anotacion.getAnotacionPredio());

				if((lap_anotacionPredio != null) && CollectionUtils.isValidCollection(lcsm_matriculas))
				{
					AnotacionPredioCiudadanoDAO          lapc_DAO;
					boolean                              lb_intervinientes;
					Collection<AnotacionPredioCiudadano> lcapc_intervinientes;
					int                                  li_registros;
					SolicitudMatriculaDAO                lsm_DAO;

					lapc_DAO                 = DaoCreator.getAccAnotacionPredioCiudadanoDAO(ldm_manager);
					lcapc_intervinientes     = lapc_DAO.findByIdAnotacion(
						    new AnotacionPredioCiudadano(lap_anotacionPredio.getIdAnotacionPredio())
						);
					lb_intervinientes        = CollectionUtils.isValidCollection(lcapc_intervinientes);
					li_registros             = 0;
					lsm_DAO                  = DaoCreator.getSolicitudMatriculaDAO(ldm_manager);

					for(SolicitudMatricula lsm_iterador : lcsm_matriculas)
					{
						if((lsm_iterador != null) && lsm_iterador.isSeleccionado())
						{
							SolicitudMatricula lsm_temp;

							lsm_temp = lsm_DAO.findById(
								    lsm_iterador.getIdSolicitud(), lsm_iterador.getIdCirculo(),
								    lsm_iterador.getIdMatricula(), false
								);

							if(lsm_temp == null)
							{
								lsm_iterador.setIndicadorEnglobeCorrecciones(EstadoCommon.S);
								lsm_iterador.setEstado(EstadoCommon.A);
								lsm_iterador.setExpedirCertificado(EstadoCommon.N);
								lsm_iterador.setIdUsuarioCreacion(as_userId);
								lsm_iterador.setIpCreacion(as_remoteIp);

								lsm_DAO.insertOrUpdate(lsm_iterador, true);
							}
							else
							{
								lsm_temp.setIndicadorEnglobeCorrecciones(EstadoCommon.S);
								lsm_temp.setEstado(EstadoCommon.A);
								lsm_temp.setIdUsuarioModificacion(as_userId);
								lsm_temp.setIpModificacion(as_remoteIp);

								lsm_DAO.insertOrUpdate(lsm_temp, false);
							}

							if(!lsm_iterador.isBloquear())
							{
								com.bachue.snr.prosnr01.model.sdb.acc.AnotacionPredio lap_anotacionInsert;

								lap_anotacionPredio.setIdUsuarioCreacion(as_userId);
								lap_anotacionPredio.setIdUsuarioModificacion(null);
								lap_anotacionPredio.setIpCreacion(as_remoteIp);
								lap_anotacionPredio.setIpModificacion(null);
								lap_anotacionPredio.setIdCirculo(lsm_iterador.getIdCirculo());
								lap_anotacionPredio.setIdMatricula(
								    NumericUtils.getLongWrapper(lsm_iterador.getIdMatricula())
								);

								{
									int li_idAnotacion;

									li_idAnotacion = lap_DAO.consultarMaxIdAnotacion(lap_anotacionPredio);

									li_idAnotacion++;

									lap_anotacionPredio.setIdAnotacion(NumericUtils.getLongWrapper(li_idAnotacion));
								}

								lap_anotacionInsert = lap_DAO.insert(lap_anotacionPredio);

								if(lb_intervinientes && (lap_anotacionInsert != null))
								{
									for(AnotacionPredioCiudadano lapc_iterador : lcapc_intervinientes)
									{
										if(lapc_iterador != null)
										{
											lapc_iterador.setIdAnotacionPredio(
											    lap_anotacionInsert.getIdAnotacionPredio()
											);
											lapc_iterador.setIdUsuarioCreacion(as_userId);
											lapc_iterador.setIdUsuarioModificacion(null);
											lapc_iterador.setIpCreacion(as_remoteIp);
											lapc_iterador.setIpModificacion(null);

											lapc_DAO.insert(lapc_iterador);
										}
									}
								}
							}

							li_registros++;
						}
					}

					if(aa_anotacion.isCierreFolio() && StringUtils.isValidString(as_idTurno))
					{
						AccPredioRegistro    lapr_predioRegistro;
						AccPredioRegistroDAO lapr_DAO;

						lapr_DAO                = DaoCreator.getAccPredioRegistroDAO(ldm_manager);
						lapr_predioRegistro     = lapr_DAO.findByCirculoMatriculaTurno(
							    as_idCirculo, al_idMatricula, as_idTurno
							);

						if(lapr_predioRegistro != null)
						{
							CambioEstadoPredio lcep_cambioEstado;

							lcep_cambioEstado = new CambioEstadoPredio();

							lcep_cambioEstado.setIdTurno(as_idTurno);
							lcep_cambioEstado.setIdCirculo(as_idCirculo);
							lcep_cambioEstado.setIdMatricula(al_idMatricula);
							lcep_cambioEstado.setJustificacionCambio(aa_anotacion.getJustificacionCambio());
							lcep_cambioEstado.setMotivoCambioEstado(aa_anotacion.getMotivoCambioEstado());
							lcep_cambioEstado.setIdUsuarioCreacion(as_userId);
							lcep_cambioEstado.setIpCreacion(as_remoteIp);
							lapr_predioRegistro.setIdEstadoPredio(EstadoCommon.C);

							DaoCreator.getCambioEstadoPredioDAO(ldm_manager).insertOrUpdate(lcep_cambioEstado, true);
							lapr_DAO.updateById(lapr_predioRegistro);
						}
					}

					if(li_registros < 2)
						throw new B2BException(ErrorKeys.ERROR_ENGLOBES_CANTIDAD_PREDIOS);
					else
						aa_anotacion.setMatriculasEnglobeSeleccionadas(true);
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("confirmarMatriculasEnglobe", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return aa_anotacion;
	}

	/**
	 * Metodo encargado de consultar el turno historia inmediatamente anterior al criterio enviado.
	 *
	 * @param ath_turnoHistoria Argumento de tipo TurnoHistoria que contienen los criterios necesarios
	 * para realizar la busqueda.
	 * @return Objeto de tipo TurnoHistoria que contiene los datos que cumplieron con los criterios de búsqueda.
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public synchronized TurnoHistoria consultarTurnoHistoriaAnterior(TurnoHistoria ath_turnoHistoria)
	    throws B2BException
	{
		TurnoHistoria lth_datos;

		lth_datos = null;

		if(ath_turnoHistoria != null)
		{
			DAOManager ldm_manager;

			ldm_manager = DaoManagerFactory.getDAOManager();

			try
			{
				Long ll_idTurnoHistoria;

				ll_idTurnoHistoria = ath_turnoHistoria.getIdTurnoHistoria();

				if(ll_idTurnoHistoria != null)
				{
					TurnoHistoria    lth_turnoHistoria;
					TurnoHistoriaDAO lthd_DAO;

					lth_turnoHistoria     = new TurnoHistoria();
					lthd_DAO              = DaoCreator.getTurnoHistoriaDAO(ldm_manager);

					lth_turnoHistoria.setIdTurnoHistoria(ll_idTurnoHistoria);

					lth_turnoHistoria = lthd_DAO.findById(ath_turnoHistoria);

					if(lth_turnoHistoria != null)
					{
						TurnoHistoria lth_turnoHistoriaAnterior;

						lth_turnoHistoriaAnterior = new TurnoHistoria();

						lth_turnoHistoriaAnterior.setIdTurnoHistoria(
						    NumericUtils.getLongWrapper(lth_turnoHistoria.getIdAnterior())
						);

						lth_datos = lthd_DAO.findById(lth_turnoHistoriaAnterior);
					}
				}
			}
			catch(B2BException lb2be_e)
			{
				ldm_manager.setRollbackOnly();

				clh_LOGGER.error("consultarTurnoHistoriaAnterior", lb2be_e);

				throw lb2be_e;
			}
			finally
			{
				ldm_manager.close();
			}
		}

		return lth_datos;
	}

	/**
	 * Consultar turno migrado.
	 *
	 * @param ath_turnoHistoria con la información a migrar
	 * @return el valor del turno historia migrado
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public synchronized TurnoHistoria consultarTurnoMigrado(TurnoHistoria ath_turnoHistoria)
	    throws B2BException
	{
		DAOManager    ldm_manager;
		TurnoHistoria lth_turnoHistoria;

		ldm_manager           = DaoManagerFactory.getDAOManager();
		lth_turnoHistoria     = null;

		try
		{
			if(ath_turnoHistoria != null)
			{
				boolean ab_noTieneLiquidacion;

				ab_noTieneLiquidacion     = false;
				lth_turnoHistoria         = DaoCreator.getTurnoHistoriaDAO(ldm_manager).findById(ath_turnoHistoria);

				if(lth_turnoHistoria != null)
				{
					Solicitud ls_solicitud;
					ls_solicitud = DaoCreator.getSolicitudDAO(ldm_manager).findById(lth_turnoHistoria.getIdSolicitud());

					if(ls_solicitud != null)
					{
						Liquidacion ll_liquidacion;
						ll_liquidacion     = DaoCreator.getAccLiquidacionDAO(ldm_manager)
								                           .findByIdSolicitud(ls_solicitud.getIdSolicitud());

						ab_noTieneLiquidacion = ll_liquidacion == null;
					}

					Turno lt_turno;
					lt_turno = DaoCreator.getTurnoDAO(ldm_manager).findById(lth_turnoHistoria.getIdTurno());

					if(lt_turno != null)
						lth_turnoHistoria.setEsTurnoMigrado(
						    StringUtils.getStringNotNull(lt_turno.getMigrado()).equalsIgnoreCase(EstadoCommon.S)
						);

					if(lth_turnoHistoria.isEsTurnoMigrado() && ab_noTieneLiquidacion)
						lth_turnoHistoria.setEsHomologarActosLiquidacion(true);
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("consultarTurnoMigrado", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lth_turnoHistoria;
	}

	/**
	 * Consultar turnos derivados.
	 *
	 * @param ath_turnoHistoria de ath turno historia
	 * @return el valor de map
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public synchronized Map<TurnoHistoria, Boolean> consultarTurnosDerivados(TurnoHistoria ath_turnoHistoria)
	    throws B2BException
	{
		DAOManager                  ldm_manager;
		Map<TurnoHistoria, Boolean> lmso_result;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		lmso_result     = null;

		try
		{
			lmso_result = getFirmaMasivaBusiness()
					              .turnosDerivados(
					    ath_turnoHistoria, EtapaCommon.ID_ETAPA_CALIFICACION, ldm_manager, true
					);
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("consultarTurnosDerivados", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lmso_result;
	}

	/**
	 * Método encargado de eliminar Acto.
	 *
	 * @param aa_parametros Argumento de tipo Acto que contiene la información del acto que se va a eliminar.
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public synchronized void eliminarActo(Acto aa_parametros)
	    throws B2BException
	{
		DAOManager ldm_manager;

		ldm_manager = DaoManagerFactory.getDAOManager();

		try
		{
			if(aa_parametros != null)
			{
				{
					String ls_idActo;

					ls_idActo = aa_parametros.getIdActo();

					if(!StringUtils.isValidString(ls_idActo))
						throw new B2BException(ErrorKeys.ERROR_SIN_ID_ACTO);
				}

				DaoCreator.getActoDAO(ldm_manager).delete(aa_parametros);
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("deleteActo", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}
	}

	/**
	 * Método encargado de encontrar turnos relacionados con la solicitud actual.
	 *
	 * @param as_idTurnoHistoria String del id turno historia para realizar busquedas en la BD
	 * @return Retorna un string con mensaje.
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public synchronized String encontrarTurnosActuales(String as_idTurnoHistoria)
	    throws B2BException
	{
		DAOManager ldm_manager;
		String     ls_mensaje;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		ls_mensaje      = "";

		try
		{
			if(StringUtils.isValidString(as_idTurnoHistoria))
			{
				TurnoHistoria lth_turnoHistoria;
				lth_turnoHistoria = new TurnoHistoria();
				lth_turnoHistoria.setIdTurnoHistoria(NumericUtils.getLongWrapper(as_idTurnoHistoria));

				lth_turnoHistoria = DaoCreator.getTurnoHistoriaDAO(ldm_manager).findById(lth_turnoHistoria);

				if(lth_turnoHistoria != null)
				{
					Solicitud ls_solicitud;
					ls_solicitud = new Solicitud();
					ls_solicitud.setIdSolicitud(lth_turnoHistoria.getIdSolicitud());

					ls_solicitud = DaoCreator.getSolicitudDAO(ldm_manager).findById(ls_solicitud);

					if(ls_solicitud != null)
					{
						if(NumericUtils.getLong(ls_solicitud.getEstadoSolicitud()) != 5)
						{
							Turno             lt_turno;
							Collection<Turno> lct_turnos;

							lt_turno = new Turno();
							lt_turno.setIdSolicitud(ls_solicitud.getIdSolicitud());

							lct_turnos = DaoCreator.getTurnoDAO(ldm_manager).findByIdSolicitud(lt_turno);

							if(CollectionUtils.isValidCollection(lct_turnos))
							{
								int li_cont;
								li_cont = 1;

								for(Turno lt_tmp : lct_turnos)
								{
									if(lt_tmp != null)
									{
										if(li_cont == 1)
											ls_mensaje = ls_mensaje + lt_tmp.getIdTurno();
										else
											ls_mensaje = ls_mensaje + IdentificadoresCommon.SIMBOLO_COMA
												+ lt_tmp.getIdTurno();

										li_cont = li_cont + 1;
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

			clh_LOGGER.error("encontrarTurnosActuales", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return ls_mensaje;
	}

	/**
	 * Método encargado de enviar al aproador 195.
	 *
	 * @param as_observaciones de as observaciones
	 * @param as_idTurnoHistoria Variable de tipo String que contiene el id turno historia.
	 * @param ab_secuenciaAgregada de ab secuencia agregada
	 * @param as_userId Variable de tipo String que contiene el id del usuario.
	 * @param as_remoteIp Variable de tipo String que contiene la ip del usuario.
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public synchronized void enviarAprobadorRealizarCorrecciones(
	    String as_observaciones, String as_idTurnoHistoria, boolean ab_secuenciaAgregada, String as_userId,
	    String as_remoteIp
	)
	    throws B2BException
	{
		DAOManager ldm_manager;

		ldm_manager = DaoManagerFactory.getDAOManager();

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

					lth_DAO               = DaoCreator.getTurnoHistoriaDAO(ldm_manager);
					lth_turnoHistoria     = lth_DAO.findById(ll_idTurnoHistoria);

					if(lth_turnoHistoria != null)
					{
						validarSalvedades(ll_idTurnoHistoria, ldm_manager, null, true);
						generarFormularioCorrecciones(as_idTurnoHistoria, as_userId, as_remoteIp, ldm_manager, true);

						MotivoTramite lmt_motivo;

						lmt_motivo = new MotivoTramite();

						lmt_motivo.setIdEtapaOrigen(EtapaCommon.ID_ETAPA_RESPONSABLE_CORRECCIONES);
						lmt_motivo.setIdMotivo(
						    ab_secuenciaAgregada ? MotivoTramiteCommon.APROBAR_SECUENCIAS_132
						                         : MotivoTramiteCommon.REALIZAR_CORRECCIONES
						);

						lmt_motivo = DaoCreator.getMotivoTramiteDAO(ldm_manager).findById(lmt_motivo);

						if(lmt_motivo != null)
						{
							lth_turnoHistoria.setEstadoActividad(EstadoCommon.TERMINADA);

							if(
							    ab_secuenciaAgregada
								    && (!StringUtils.isValidString(as_observaciones)
								    || (as_observaciones.length() < 100))
							)
								throw new B2BException(ErrorKeys.ERROR_CANTIDAD_CARACTERES_OBSERVACION);

							lth_turnoHistoria.setObservaciones(as_observaciones);
							lth_turnoHistoria.setMotivo(lmt_motivo.getDescripcion());
							lth_turnoHistoria.setIdMotivo(NumericUtils.getLongWrapper(lmt_motivo.getIdMotivo()));
							lth_turnoHistoria.setIdUsuarioModificacion(as_userId);
							lth_turnoHistoria.setIpModificacion(as_remoteIp);

							lth_DAO.insertOrUpdate(lth_turnoHistoria, false);

							DaoCreator.getProcedimientosDAO(ldm_manager).spCreateStage(lth_turnoHistoria);
						}
					}
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("enviarAprobadorRealizarCorrecciones", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}
	}

	/**
	 * Método encargado de cambiar de etapa para el proceso de reproducción de constancia.
	 *
	 * @param ac_c Objeto de tipo Calificacion que contiene los datos determinados para realizar el cambio de etapa.
	 * @param as_userId de as user id
	 * @param as_remoteIp de as remote ip
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public synchronized void enviarAprobadorRepConstancia(Calificacion ac_c, String as_userId, String as_remoteIp)
	    throws B2BException
	{
		DAOManager ldm_manager;
		ldm_manager = DaoManagerFactory.getDAOManager();

		try
		{
			if(ac_c != null)
			{
				Long ll_idMotivo;
				Long ls_idTurnoHistoria;

				ll_idMotivo            = ac_c.getIdMotivo();
				ls_idTurnoHistoria     = ac_c.getIdTurnoHistoria();

				if(NumericUtils.isValidLong(ll_idMotivo))
				{
					MotivoTramite    lmt_motivo;
					TurnoHistoria    lth_dataTurn;
					TurnoHistoriaDAO lothd_DAO;

					lth_dataTurn     = new TurnoHistoria();
					lothd_DAO        = DaoCreator.getTurnoHistoriaDAO(ldm_manager);

					lth_dataTurn.setIdTurnoHistoria(ls_idTurnoHistoria);

					lth_dataTurn = lothd_DAO.findById(lth_dataTurn);

					if(lth_dataTurn != null)
					{
						lmt_motivo = new MotivoTramite();

						lmt_motivo.setIdMotivo(NumericUtils.getLong(ll_idMotivo));
						lmt_motivo.setIdEtapaOrigen(EtapaCommon.ID_ETAPA_CALIFICACION);

						lmt_motivo = DaoCreator.getMotivoTramiteDAO(ldm_manager).findById(lmt_motivo);

						if(lmt_motivo != null)
						{
							{
								String ls_estadoActividad;

								ls_estadoActividad = lth_dataTurn.getEstadoActividad();

								if(
								    StringUtils.isValidString(ls_estadoActividad)
									    && !ls_estadoActividad.equalsIgnoreCase(EstadoCommon.ASIGNADA)
								)
									throw new B2BException(ErrorKeys.NO_SE_PUEDE_TERMINAR_TURNO_HIST_ASG);
							}

							lth_dataTurn.setEstadoActividad(EstadoCommon.TERMINADA);
							lth_dataTurn.setMotivo(lmt_motivo.getDescripcion());
							lth_dataTurn.setIdMotivo(NumericUtils.getLongWrapper(lmt_motivo.getIdMotivo()));
							lth_dataTurn.setIdUsuarioModificacion(ac_c.getIdUsuarioModificacion());
							lth_dataTurn.setIpModificacion(ac_c.getIpModificacion());

							{
								ConstanciaReproduccion lcr_constanciaReproduccion;

								lcr_constanciaReproduccion = new ConstanciaReproduccion();

								if(lth_dataTurn != null)
								{
									Turno  lt_turno;
									String ls_turno;

									lt_turno     = new Turno();
									ls_turno     = lth_dataTurn.getIdTurno();

									if(StringUtils.isValidString(ls_turno))
									{
										lt_turno.setIdTurno(ls_turno);
										lt_turno = DaoCreator.getTurnoDAO(ldm_manager).findById(lt_turno);

										if(lt_turno != null)
										{
											String ls_idCirculo;

											ls_idCirculo = lt_turno.getIdCirculo();

											lcr_constanciaReproduccion.setTurnoHistoria(lth_dataTurn);

											if(StringUtils.isValidString(ls_idCirculo))
											{
												byte[] lba_documento;

												lcr_constanciaReproduccion.setIdCirculo(ls_idCirculo);
												lba_documento = getConstanciaReproduccionBusiness()
														                .generarConstanciaInscripcionRepConstancia(
														    lcr_constanciaReproduccion, false, as_userId, as_remoteIp
														);

												if(lba_documento != null)
												{
													Imagenes            li_imagenRelacion;
													ImagenesDAO         li_DAO;
													DocumentosSalidaDAO lds_DAO;
													long                ll_idImagen;
													Long                ll_turnoHistoria;
													String              ls_numeroSolicitud;

													li_imagenRelacion      = new Imagenes();
													li_DAO                 = DaoCreator.getImagenesDAO(ldm_manager);
													lds_DAO                = DaoCreator.getDocumentosSalidaDAO(
														    ldm_manager
														);
													ll_turnoHistoria       = lth_dataTurn.getIdTurnoHistoria();
													ls_numeroSolicitud     = lth_dataTurn.getIdSolicitud();

													li_imagenRelacion.setTipoArchivo(ExtensionCommon.PDF_MAYUS);
													li_imagenRelacion.setIdUsuarioCreacion(as_userId);
													li_imagenRelacion.setIpCreacion(as_remoteIp);
													li_imagenRelacion.setImagenBlob(lba_documento);

													ll_idImagen = li_DAO.insertOrUpdate(li_imagenRelacion, true);

													if(
													    !NumericUtils.isValidLong(
														        NumericUtils.getLongWrapper(ll_idImagen), 1
														    )
													)
														throw new B2BException(ErrorKeys.SIN_SECUENCIA_IMAGENES);

													DocumentosSalida lds_documento;
													Integer          lbi_idTurnoHistoria;

													lds_documento           = new DocumentosSalida();
													lbi_idTurnoHistoria     = NumericUtils.isValidLong(
														    ll_turnoHistoria
														) ? new Integer(ll_turnoHistoria.toString()) : null;

													lds_documento.setIdTurnoHistoria(lbi_idTurnoHistoria);
													lds_documento.setIdSolicitud(ls_numeroSolicitud);
													lds_documento.setIdTurno(ls_turno);
													lds_documento.setIdImagen(NumericUtils.getLongWrapper(ll_idImagen));
													lds_documento.setTipoArchivo(ExtensionCommon.PDF_MAYUS);
													lds_documento.setTipo(TipoArchivoCommon.CONSTANCIA_REPRODUCCION);
													lds_documento.setIdTipoDocumental(
													    TipoDocumentalCommon.CONSTANCIA_REPRODUCCION
													);
													lds_documento.setRepositorio(RepositorioCommon.TEMPORAL);
													lds_documento.setEstado(EstadoCommon.ACTIVO);
													lds_documento.setIdUsuarioCreacion(as_userId);
													lds_documento.setIpCreacion(as_remoteIp);

													lds_DAO.insertOrUpdate(lds_documento, true);
													lothd_DAO.insertOrUpdate(lth_dataTurn, false);

													DaoCreator.getProcedimientosDAO(ldm_manager)
														          .spCreateStage(lth_dataTurn);
												}
												else
													throw new B2BException(ErrorKeys.ERROR_INTERNO_SISTEMA);
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

			clh_LOGGER.error("enviarAprobadorRepConstancia", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}
	}

	/**
	 * Método encargado de cambiar de etapa para el proceso de reproducción de constancia.
	 *
	 * @param ath_th Objeto de tipo Calificacion que contiene los datos determinados para realizar el cambio de etapa.
	 * @param ab_aprobadorSecuencia correspondiente al valor de aprobador secuencia
	 * @param as_userId de as user id
	 * @param as_remoteIp de as remote ip
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public synchronized void enviarAprobadorSecuenciaOActosHomologacion(
	    TurnoHistoria ath_th, boolean ab_aprobadorSecuencia, String as_userId, String as_remoteIp
	)
	    throws B2BException
	{
		DAOManager ldm_manager;
		ldm_manager = DaoManagerFactory.getDAOManager();

		try
		{
			if(ath_th != null)
			{
				MotivoTramite    lmt_motivo;
				TurnoHistoria    lth_dataTurn;
				TurnoHistoriaDAO lothd_DAO;

				lothd_DAO        = DaoCreator.getTurnoHistoriaDAO(ldm_manager);
				lth_dataTurn     = lothd_DAO.findById(ath_th.getIdTurnoHistoria());

				if(lth_dataTurn != null)
				{
					lmt_motivo = new MotivoTramite();

					lmt_motivo.setIdMotivo(
					    ab_aprobadorSecuencia ? MotivoTramiteCommon.APROBAR_SECUENCIAS
					                          : MotivoTramiteCommon.HOMOLOGACION_ACTOS_LIQUIDACION_MAYOR_VALOR
					);
					lmt_motivo.setIdEtapaOrigen(EtapaCommon.ID_ETAPA_CALIFICACION);

					lmt_motivo = DaoCreator.getMotivoTramiteDAO(ldm_manager).findById(lmt_motivo);

					if(lmt_motivo != null)
					{
						Long                        ll_idMotivo;
						Map<TurnoHistoria, Boolean> lmthb_turnos;

						lmthb_turnos     = getFirmaMasivaBusiness()
								                   .turnosDerivados(
								    lth_dataTurn, EtapaCommon.ID_ETAPA_CALIFICACION, ldm_manager, true
								);
						ll_idMotivo      = NumericUtils.getLongWrapper(lmt_motivo.getIdMotivo());

						if(CollectionUtils.isValidCollection(lmthb_turnos))
						{
							ProcedimientosDAO lp_DAO;
							String            ls_descripcionMotivo;
							String            ls_idUsuarioModificacion;
							String            ls_ipModificacion;

							lp_DAO                       = DaoCreator.getProcedimientosDAO(ldm_manager);
							ls_descripcionMotivo         = lmt_motivo.getDescripcion();
							ls_idUsuarioModificacion     = as_userId;
							ls_ipModificacion            = as_remoteIp;

							for(Map.Entry<TurnoHistoria, Boolean> lmthb_iterador : lmthb_turnos.entrySet())
							{
								TurnoHistoria lth_actual;

								lth_actual = lmthb_iterador.getKey();

								if(lth_actual != null)
								{
									lth_actual.setObservaciones(ath_th.getObservaciones());
									lth_actual.setEstadoActividad(EstadoCommon.TERMINADA);
									lth_actual.setMotivo(ls_descripcionMotivo);
									lth_actual.setIdMotivo(ll_idMotivo);
									lth_actual.setIdUsuarioModificacion(ls_idUsuarioModificacion);
									lth_actual.setIpModificacion(ls_ipModificacion);

									lothd_DAO.insertOrUpdate(lth_actual, false);

									lp_DAO.spCreateStage(lth_actual);
								}
								else
									throw new B2BException(ErrorKeys.ERROR_INTERNO_SISTEMA);
							}
						}
						else
							throw new B2BException(ErrorKeys.ERROR_INTERNO_SISTEMA);
					}
					else
						throw new B2BException(ErrorKeys.ERROR_INTERNO_SISTEMA);
				}
				else
					throw new B2BException(ErrorKeys.ERROR_INTERNO_SISTEMA);
			}
			else
				throw new B2BException(ErrorKeys.ERROR_INTERNO_SISTEMA);
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("enviarAprobadorSecuencia", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}
	}

	/**
	 * Método encargado de cambiar de etapa para el proceso de aprobar prorroga de documentos.
	 *
	 * @param ath_th Objeto de tipo Calificacion que contiene los datos determinados para realizar el cambio de etapa.
	 * @param as_userId de as user id
	 * @param as_remoteIp de as remote ip
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public synchronized void enviarAprobarProrrogaDocumentos(
	    TurnoHistoria ath_th, String as_userId, String as_remoteIp
	)
	    throws B2BException
	{
		DAOManager ldm_manager;
		ldm_manager = DaoManagerFactory.getDAOManager();

		try
		{
			if(ath_th != null)
			{
				Long   ll_idMotivo;
				String ls_idTurnoHistoria;

				ll_idMotivo            = NumericUtils.getLongWrapper(MotivoTramiteCommon.APROBAR_PRORROGA_DOCUMENTOS);
				ls_idTurnoHistoria     = StringUtils.getString(ath_th.getIdTurnoHistoria());

				if(NumericUtils.isValidLong(ll_idMotivo))
				{
					MotivoTramite    lmt_motivo;
					TurnoHistoria    lth_dataTurn;
					TurnoHistoriaDAO lothd_DAO;

					lth_dataTurn     = new TurnoHistoria();
					lothd_DAO        = DaoCreator.getTurnoHistoriaDAO(ldm_manager);

					lth_dataTurn.setIdTurnoHistoria(NumericUtils.getLongWrapper(ls_idTurnoHistoria));

					lth_dataTurn = lothd_DAO.findById(lth_dataTurn);

					if(lth_dataTurn != null)
					{
						lmt_motivo = new MotivoTramite();

						lmt_motivo.setIdMotivo(NumericUtils.getLong(ll_idMotivo));
						lmt_motivo.setIdEtapaOrigen(EtapaCommon.ID_ETAPA_CORRECCIONES);

						lmt_motivo = DaoCreator.getMotivoTramiteDAO(ldm_manager).findById(lmt_motivo);

						if(lmt_motivo != null)
						{
							Map<TurnoHistoria, Boolean> lmthb_turnos;

							lmthb_turnos = getFirmaMasivaBusiness()
									               .turnosDerivados(
									    lth_dataTurn, EtapaCommon.ID_ETAPA_CORRECCIONES, ldm_manager, true
									);

							if(CollectionUtils.isValidCollection(lmthb_turnos))
							{
								ProcedimientosDAO lp_DAO;
								String            ls_descripcionMotivo;
								String            ls_idUsuarioModificacion;
								String            ls_ipModificacion;

								lp_DAO                       = DaoCreator.getProcedimientosDAO(ldm_manager);
								ls_descripcionMotivo         = lmt_motivo.getDescripcion();
								ls_idUsuarioModificacion     = as_userId;
								ls_ipModificacion            = as_remoteIp;

								for(Map.Entry<TurnoHistoria, Boolean> lmthb_iterador : lmthb_turnos.entrySet())
								{
									TurnoHistoria lth_actual;

									lth_actual = lmthb_iterador.getKey();

									if(lth_actual != null)
									{
										lth_actual.setObservaciones(ath_th.getObservaciones());
										lth_actual.setEstadoActividad(EstadoCommon.TERMINADA);
										lth_actual.setMotivo(ls_descripcionMotivo);
										lth_actual.setIdMotivo(ll_idMotivo);
										lth_actual.setIdUsuarioModificacion(ls_idUsuarioModificacion);
										lth_actual.setIpModificacion(ls_ipModificacion);

										lothd_DAO.insertOrUpdate(lth_actual, false);

										lp_DAO.spCreateStage(lth_actual);
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

			clh_LOGGER.error("enviarAprobarProrrogaDocumentos", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}
	}

	/**
	 * Método encargado de cambiar de etapa para el proceso de actuaciones administrativas.
	 *
	 * @param ath_turnoHistoria Argumento de tipo <code>TurnoHistoria</code> que contiene el turno historia a terminar.
	 * @param amt_motivoTramite Argumento de tipo <code>MotivoTramite</code> que contiene el motivo de trámite.
	 * @param as_userId Argumento de tipo <code>String</code> que contiene el usuario que realiza la operación.
	 * @param as_remoteIp Argumento de tipo <code>String</code> que contiene la ip de la maquina del usuario que realiza la operación.
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public synchronized void enviarResponsableActuacionesAdministrativas(
	    TurnoHistoria ath_turnoHistoria, MotivoTramite amt_motivoTramite, String as_userId, String as_remoteIp
	)
	    throws B2BException
	{
		DAOManager ldm_manager;
		ldm_manager = DaoManagerFactory.getDAOManager();

		try
		{
			if(ath_turnoHistoria != null)
			{
				{
					String ls_justificacion;

					ls_justificacion = ath_turnoHistoria.getObservaciones();

					if(!StringUtils.isValidString(ls_justificacion))
						throw new B2BException(ErrorKeys.DEBE_DIGITAR_JUSTIFICACION);

					if(ls_justificacion.length() < 100)
						throw new B2BException(ErrorKeys.DEBE_DIGITAR_JUSTIFICACION_CIERRE_FOLIO);
				}

				terminarTurnoHistoriaYCrearEtapa(
				    ath_turnoHistoria, ldm_manager, amt_motivoTramite, as_userId, as_remoteIp, EstadoCommon.TERMINADA
				);
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("enviarResponsableActuacionesAdministrativas", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}
	}

	/**
	 * Método para saber si existen anotaciones inactivas.
	 *
	 * @param as_idSolicitud de as id solicitud
	 * @return retorna <code>true</code> si se cumple la condicion, de lo contario retorna <code>false</code>
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public boolean existenAnotacionesInactivas(String as_idSolicitud)
	    throws B2BException
	{
		DAOManager         ldm_manager;
		AnotacionPredioDAO lap_DAO;
		boolean            lb_existenAnotacionesInactivas;

		ldm_manager                        = DaoManagerFactory.getDAOManager();
		lap_DAO                            = DaoCreator.getAccAnotacionPredioDAO(ldm_manager);
		lb_existenAnotacionesInactivas     = false;

		try
		{
			if(StringUtils.isValidString(as_idSolicitud))
			{
				Collection<com.bachue.snr.prosnr01.model.sdb.acc.AnotacionPredio> cap_apTemp;

				cap_apTemp                         = lap_DAO.findAnotacionesInactivasByIdSolicitud(as_idSolicitud);
				lb_existenAnotacionesInactivas     = CollectionUtils.isValidCollection(cap_apTemp);
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("existenAnotacionesInactivas", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lb_existenAnotacionesInactivas;
	}

	/**
	 * Método para saber si existen anotaciones inactivas por el turno.
	 *
	 * @param as_idTurno de as id turno
	 * @return retorna <code>true</code> si se cumple la condicion, de lo contario retorna <code>false</code>
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public boolean existenAnotacionesInactivasByTurno(String as_idTurno)
	    throws B2BException
	{
		DAOManager         ldm_manager;
		AnotacionPredioDAO lap_DAO;
		boolean            lb_existenAnotacionesInactivas;

		ldm_manager                        = DaoManagerFactory.getDAOManager();
		lap_DAO                            = DaoCreator.getAccAnotacionPredioDAO(ldm_manager);
		lb_existenAnotacionesInactivas     = false;

		try
		{
			if(StringUtils.isValidString(as_idTurno))
			{
				Collection<com.bachue.snr.prosnr01.model.sdb.acc.AnotacionPredio> cap_apTemp;

				cap_apTemp = lap_DAO.findAnotacionesInactivasByIdTurno(as_idTurno, false);

				if(CollectionUtils.isValidCollection(cap_apTemp))
					lb_existenAnotacionesInactivas = true;
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("existenAnotacionesInactivasByTurno", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lb_existenAnotacionesInactivas;
	}

	/**
	 * Metodo encargado de consultar todos los actos de una solicitud y un círculo.
	 *
	 * @param as_idSolicitud Argumento de tipo String que contiene el id solicitud para realizar la consulta.
	 * @param as_idCirculo Argumento de tipo String que contiene el id circulo para realizar la consulta.
	 * @return Colección de Actos que cumplen con los criterios de búsqueda.
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public synchronized Collection<Acto> findActosByIdSolicitudCirculo(String as_idSolicitud, String as_idCirculo)
	    throws B2BException
	{
		Collection<Acto> lca_datos;
		DAOManager       ldm_manager;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		lca_datos       = null;

		try
		{
			if(!StringUtils.isValidString(as_idSolicitud))
				throw new B2BException(ErrorKeys.ERROR_SIN_ID_SOLICITUD);

			if(!StringUtils.isValidString(as_idCirculo))
				throw new B2BException(ErrorKeys.ERROR_SIN_ID_CIRCULO);

			{
				Acto la_acto;

				la_acto = new Acto();

				la_acto.setIdSolicitud(as_idSolicitud);
				la_acto.setIdCirculo(as_idCirculo);

				lca_datos = DaoCreator.getActoDAO(ldm_manager).findByIdSolicitudCirculo(la_acto);

				if(CollectionUtils.isValidCollection(lca_datos))
				{
					TipoActoDAO ltad_DAO;

					ltad_DAO = DaoCreator.getTipoActoDAO(ldm_manager);

					for(Acto la_iterador : lca_datos)
					{
						if(la_iterador != null)
						{
							TipoActo lta_tipoActo;

							lta_tipoActo = ltad_DAO.findById(la_iterador.getIdTipoActo());

							if(lta_tipoActo != null)
								la_iterador.setTipoActo(lta_tipoActo);
						}
					}
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findActosByIdTurno", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lca_datos;
	}

	/**
	 * Metodo encargado de consultar todos los actos de un turno.
	 *
	 * @param as_idTurno Argumento de tipo String que contiene el turno a consultar.
	 * @return Colección de actos que cumplen con los criterios de busqueda
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public synchronized Collection<com.bachue.snr.prosnr01.model.registro.Acto> findActosByIdTurno(String as_idTurno)
	    throws B2BException
	{
		Collection<com.bachue.snr.prosnr01.model.registro.Acto> lca_datos;
		DAOManager                                              ldm_manager;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		lca_datos       = null;

		try
		{
			if(!StringUtils.isValidString(as_idTurno))
				throw new B2BException(ErrorKeys.ERROR_SIN_ID_TURNO);

			Turno lt_turno;

			lt_turno = DaoCreator.getTurnoDAO(ldm_manager).findById(as_idTurno);

			if(lt_turno != null)
			{
				String ls_idSolicitud;
				String ls_idCirculo;

				ls_idSolicitud     = lt_turno.getIdSolicitud();
				ls_idCirculo       = lt_turno.getIdCirculo();

				if(StringUtils.isValidString(ls_idSolicitud) && StringUtils.isValidString(ls_idCirculo))
				{
					com.bachue.snr.prosnr01.model.registro.Acto la_acto;

					la_acto = new com.bachue.snr.prosnr01.model.registro.Acto();
					la_acto.setIdSolicitud(ls_idSolicitud);
					la_acto.setIdCirculo(ls_idCirculo);

					lca_datos = DaoCreator.getActoDAO(ldm_manager).findByIdSolicitudActivoPrecRegistro(la_acto);

					if(CollectionUtils.isValidCollection(lca_datos))
					{
						TipoActoDAO ltad_DAO;

						ltad_DAO = DaoCreator.getTipoActoDAO(ldm_manager);

						for(com.bachue.snr.prosnr01.model.registro.Acto la_iterador : lca_datos)
						{
							if(la_iterador != null)
							{
								TipoActo lpta_tipoActo;

								lpta_tipoActo = ltad_DAO.findById(la_iterador.getCodigo());

								if(lpta_tipoActo != null)
								{
									la_iterador.setEspecificacion(lpta_tipoActo.getNombre());
									la_iterador.setActoSinCuantia(lpta_tipoActo.getActoSinCuantia());
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

			clh_LOGGER.error("findActosByIdTurno", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lca_datos;
	}

	/**
	 * Metodo encargado de consultar todos los actos de un turno que fueron modificados o insertados.
	 *
	 * @param as_idTurno Argumento de tipo String que contiene el turno a consultar.
	 * @return Colección de actos que cumplen con los criterios de busqueda
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public synchronized Collection<com.bachue.snr.prosnr01.model.registro.Acto> findActosByIdTurnoIndMayorValor(
	    String as_idTurno
	)
	    throws B2BException
	{
		Collection<com.bachue.snr.prosnr01.model.registro.Acto> lca_datos;
		DAOManager                                              ldm_manager;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		lca_datos       = null;

		try
		{
			if(!StringUtils.isValidString(as_idTurno))
				throw new B2BException(ErrorKeys.ERROR_SIN_ID_TURNO);

			lca_datos = DaoCreator.getActoDAO(ldm_manager).findActosByIdTurnoIndMayorValor(as_idTurno);
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findActosByIdTurnoIndMayorValor", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lca_datos;
	}

	/**
	 * Método encargado de consultar los actos asociados a un turno y matrículas de ese turno.
	 *
	 * @param as_idTurno Variable de tipo String que contiene el id turno del proceso
	 * @return Colección que contiene los actos de la consulta.
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public synchronized Collection<Acto> findActosTurnoMatriculas(String as_idTurno)
	    throws B2BException
	{
		Collection<Acto> lca_return;

		lca_return = null;

		if(StringUtils.isValidString(as_idTurno))
		{
			DAOManager ldm_manager;

			ldm_manager = DaoManagerFactory.getDAOManager();

			try
			{
				Turno lt_turno;

				lt_turno = new Turno();

				lt_turno.setIdTurno(as_idTurno);

				lt_turno = DaoCreator.getTurnoDAO(ldm_manager).findById(lt_turno);

				if(lt_turno != null)
				{
					String ls_idCirculo;
					String ls_idSolicitud;

					ls_idCirculo       = lt_turno.getIdCirculo();
					ls_idSolicitud     = lt_turno.getIdSolicitud();

					if(StringUtils.isValidString(ls_idCirculo) && StringUtils.isValidString(ls_idSolicitud))
					{
						Collection<SolicitudMatriculaActo> lcsma_matriculasActo;
						SolicitudMatriculaActo             lsma_matriculaActo;

						lsma_matriculaActo = new SolicitudMatriculaActo();

						lsma_matriculaActo.setIdSolicitud(ls_idSolicitud);
						lsma_matriculaActo.setIdCirculo(ls_idCirculo);

						lcsma_matriculasActo = DaoCreator.getSolicitudMatriculaActoDAO(ldm_manager)
								                             .findByIdSolicitudCirculo(lsma_matriculaActo, false);

						if(lcsma_matriculasActo != null)
						{
							ActoDAO                          la_DAO;
							Iterator<SolicitudMatriculaActo> lisma_iterator;

							la_DAO             = DaoCreator.getActoDAO(ldm_manager);
							lisma_iterator     = lcsma_matriculasActo.iterator();

							lca_return = new ArrayList<Acto>();

							while(lisma_iterator.hasNext())
							{
								SolicitudMatriculaActo lsma_iterador;

								lsma_iterador = lisma_iterator.next();

								if(lsma_iterador != null)
								{
									Acto la_acto;

									la_acto = new Acto();

									la_acto.setIdActo(lsma_iterador.getIdActo());

									la_acto = la_DAO.findById(la_acto);

									if(la_acto != null)
									{
										la_acto.setReferencia(
										    lsma_iterador.getIdCirculo() + "-" + lsma_iterador.getIdMatricula()
										);
										lca_return.add(la_acto);
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

				clh_LOGGER.error("findActosTurnoMatriculas", lb2be_e);

				throw lb2be_e;
			}
			finally
			{
				ldm_manager.close();
			}
		}

		if(!CollectionUtils.isValidCollection(lca_return))
			lca_return = null;

		return lca_return;
	}

	/**
	 * Método encargado de consultar las anotaciones y datos asociados a un id antiguo sistema determinado.
	 *
	 * @param acccas_consultaCriteriosAntiguoSistema Objeto de tipo ConsultaCriteriosCalificacionAntiguoSistema que contiene los datos necesarios para realizar una consulta a la tabla SDB_ACC_DATOS_ANT_SISTEMA.
	 * @return Retorna  una colección de datos de tipo  DataConsultaAntSistema que coincide con los parametros de entrada.
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public synchronized Collection<DataConsultaAntSistema> findByDatosAntSistema(
	    ConsultaCriteriosCalificacionAntiguoSistema acccas_consultaCriteriosAntiguoSistema
	)
	    throws B2BException
	{
		DAOManager                         ldm_manager;
		Collection<DataConsultaAntSistema> ll_result;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		ll_result       = new ArrayList<DataConsultaAntSistema>();

		try
		{
			if(acccas_consultaCriteriosAntiguoSistema != null)
			{
				DatosAntSistema ldas_datosAntSistema;
				AnotacionPredio lap_anotPredio;

				ldas_datosAntSistema     = acccas_consultaCriteriosAntiguoSistema.getDatosAntSistema();
				lap_anotPredio           = acccas_consultaCriteriosAntiguoSistema.getAnotacionPredio();

				if((ldas_datosAntSistema != null) && (lap_anotPredio != null))
				{
					String ls_idCirculo;
					Long   ll_libro;
					Long   ll_matricula;

					ls_idCirculo     = ldas_datosAntSistema.getIdCirculo();
					ll_libro         = ldas_datosAntSistema.getIdLibroAntSistema();

					ll_matricula = lap_anotPredio.getIdMatricula();

					if(!StringUtils.isValidString(ls_idCirculo))
						throw new B2BException(ErrorKeys.DEBE_SELECCIONAR_CIRCULO_REGISTRAL);

					if(!NumericUtils.isValidLong(ll_libro))
						throw new B2BException(ErrorKeys.DEBE_SELECCIONAR_LIBRO);

					if(!NumericUtils.isValidLong(ll_matricula))
						throw new B2BException(ErrorKeys.DEBE_DIGITAR_MATRICULA);

					Collection<DatosAntSistema> lcdas_antSistema;

					lcdas_antSistema     = new ArrayList<DatosAntSistema>();

					lcdas_antSistema = DaoCreator.getDatosAntSistemaDAO(ldm_manager)
							                         .findByDataAntSistema(ldas_datosAntSistema);

					if(!CollectionUtils.isValidCollection(lcdas_antSistema))
						throw new B2BException(ErrorKeys.NO_SE_ENCONTRARON_REGISTROS_CONSULTA);

					for(DatosAntSistema ldas_iterador : lcdas_antSistema)
					{
						DataConsultaAntSistema ldcas_datos;
						PredioRegistro         lpr_predioRegistro;
						AnotacionPredio        lap_anotacionPredio;

						lpr_predioRegistro = new PredioRegistro();

						lpr_predioRegistro.setIdMatricula(NumericUtils.getLong(ll_matricula));
						lpr_predioRegistro.setIdCirculo(ls_idCirculo);

						lap_anotacionPredio = new AnotacionPredio();

						lap_anotacionPredio.setIdMatricula(ll_matricula);
						lap_anotacionPredio.setIdCirculo(ls_idCirculo);

						ldcas_datos = new DataConsultaAntSistema();

						String ls_idDatosAntSistema;
						ls_idDatosAntSistema = ldas_iterador.getIdDatosAntSistema();

						if(StringUtils.isValidString(ls_idDatosAntSistema))
						{
							lpr_predioRegistro.setIdAntiguoSistema(ls_idDatosAntSistema);
							lpr_predioRegistro = DaoCreator.getPredioRegistroDAO(ldm_manager)
									                           .findByAntSistema(lpr_predioRegistro);

							lap_anotacionPredio.setIdDatosAntSistema(ls_idDatosAntSistema);
							lap_anotacionPredio = DaoCreator.getAnotacionPredioDAO(ldm_manager)
									                            .findByAntSistema(lap_anotacionPredio);

							if((lpr_predioRegistro != null) || (lap_anotacionPredio != null))
							{
								ldcas_datos.setDatosAntSistema(ldas_iterador);
								ldcas_datos.setDataConsultaPorCriterio(
								    DaoCreator.getConsultaPorCriteriosDAO(ldm_manager)
									              .findByDatosAntSistema(ls_idCirculo, ls_idDatosAntSistema)
								);

								Solicitud ls_solicitud;
								ls_solicitud = DaoCreator.getSolicitudDAO(ldm_manager)
										                     .findByIdDatosAntSistema(ls_idDatosAntSistema);

								if(ls_solicitud != null)
									ldcas_datos.setSolicitud(
									    DaoCreator.getSolicitudDAO(ldm_manager).findById(ls_solicitud)
									);

								ll_result.add(ldcas_datos);
							}
						}
					}

					if(!CollectionUtils.isValidCollection(ll_result))
						throw new B2BException(ErrorKeys.NO_SE_ENCONTRARON_REGISTROS_CONSULTA);
				}
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
	 * Método encargado de consultar las anotaciones, matrículas y datos asociados a un id antiguo sistema determinado.
	 *
	 * @param acccas_consultaCriteriosAntiguoSistema Objeto de tipo ConsultaCriteriosCalificacionAntiguoSistema que contiene los datos necesarios para realizar una consulta a la tabla SDB_ACC_DATOS_ANT_SISTEMA.
	 * @return Retorna  una colección de datos de tipo  DataConsultaAntSistema que coincide con los parametros de entrada.
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public synchronized Collection<DataConsultaPorCriterio> findByDatosAntSistemaCalificacion(
	    ConsultaCriteriosCalificacionAntiguoSistema acccas_consultaCriteriosAntiguoSistema
	)
	    throws B2BException
	{
		DAOManager                          ldm_manager;
		Collection<DataConsultaPorCriterio> lcdcpc_return;

		ldm_manager       = DaoManagerFactory.getDAOManager();
		lcdcpc_return     = new ArrayList<DataConsultaPorCriterio>();

		try
		{
			if(acccas_consultaCriteriosAntiguoSistema != null)
			{
				DatosAntSistema ldas_datosAntSistema;
				AnotacionPredio lap_anotPredio;

				ldas_datosAntSistema     = acccas_consultaCriteriosAntiguoSistema.getDatosAntSistema();
				lap_anotPredio           = acccas_consultaCriteriosAntiguoSistema.getAnotacionPredio();

				if((ldas_datosAntSistema != null) && (lap_anotPredio != null))
				{
					String ls_idCirculo;
					Long   ll_libro;
					Long   ll_matricula;

					ls_idCirculo     = ldas_datosAntSistema.getIdCirculo();
					ll_libro         = ldas_datosAntSistema.getIdLibroAntSistema();

					ll_matricula = lap_anotPredio.getIdMatricula();

					if(!StringUtils.isValidString(ls_idCirculo))
						throw new B2BException(ErrorKeys.DEBE_SELECCIONAR_CIRCULO_REGISTRAL);

					if(!NumericUtils.isValidLong(ll_libro))
						throw new B2BException(ErrorKeys.DEBE_SELECCIONAR_LIBRO);

					Collection<DatosAntSistema> lcdas_antSistema;

					lcdas_antSistema     = new ArrayList<DatosAntSistema>();

					lcdas_antSistema = DaoCreator.getDatosAntSistemaDAO(ldm_manager)
							                         .findByDataAntSistema(ldas_datosAntSistema);

					if(!CollectionUtils.isValidCollection(lcdas_antSistema))
						throw new B2BException(ErrorKeys.NO_SE_ENCONTRARON_REGISTROS_CONSULTA);

					for(DatosAntSistema ldas_iterador : lcdas_antSistema)
					{
						DataConsultaAntSistema ldcas_datos;
						PredioRegistro         lpr_predioRegistro;
						AnotacionPredio        lap_anotacionPredio;

						lpr_predioRegistro = new PredioRegistro();

						lpr_predioRegistro.setIdMatricula(NumericUtils.getLong(ll_matricula));
						lpr_predioRegistro.setIdCirculo(ls_idCirculo);

						lap_anotacionPredio = new AnotacionPredio();

						lap_anotacionPredio.setIdMatricula(ll_matricula);
						lap_anotacionPredio.setIdCirculo(ls_idCirculo);

						ldcas_datos = new DataConsultaAntSistema();

						String ls_idDatosAntSistema;
						ls_idDatosAntSistema = ldas_iterador.getIdDatosAntSistema();

						if(StringUtils.isValidString(ls_idDatosAntSistema))
						{
							lpr_predioRegistro.setIdAntiguoSistema(ls_idDatosAntSistema);
							lpr_predioRegistro = DaoCreator.getPredioRegistroDAO(ldm_manager)
									                           .findByDatosAntSistemaCalificacion(lpr_predioRegistro);

							lap_anotacionPredio.setIdDatosAntSistema(ls_idDatosAntSistema);
							lap_anotacionPredio = DaoCreator.getAnotacionPredioDAO(ldm_manager)
									                            .findByDatosAntSistemaCalificacion(lap_anotacionPredio);

							if((lpr_predioRegistro != null) || (lap_anotacionPredio != null))
							{
								ldcas_datos.setDatosAntSistema(ldas_iterador);

								Collection<DataConsultaPorCriterio> lcdcpc_data;

								lcdcpc_data = DaoCreator.getConsultaPorCriteriosDAO(ldm_manager)
										                    .findByDatosAntSistema(ls_idCirculo, ls_idDatosAntSistema);

								if(CollectionUtils.isValidCollection(lcdcpc_data))
								{
									LinkedHashMap<String, DataConsultaPorCriterio> llhm_data;

									llhm_data = new LinkedHashMap<String, DataConsultaPorCriterio>();

									for(DataConsultaPorCriterio ldcpc_iterador : lcdcpc_data)
									{
										if(ldcpc_iterador != null)
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

												ls_matriculaCompleta = ls_idCirculoIterador + "-"
													+ ll_idMatriculaIterador;

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
									}

									for(Map.Entry<String, DataConsultaPorCriterio> entry : llhm_data.entrySet())
										lcdcpc_return.add(entry.getValue());
								}
							}
						}
					}

					if(!CollectionUtils.isValidCollection(lcdcpc_return))
						throw new B2BException(ErrorKeys.NO_SE_ENCONTRARON_REGISTROS_CONSULTA);
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findByDatosAntSistemaCalificacion", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lcdcpc_return;
	}

	/**
	 * Método encargado de consultar el detalle de todos los documentos asociados a un id documento, id oficina origen y número.
	 *
	 * @param acccd_consultaCriteriosCalificacionDocumento Objeto que contiene los datos necesarios para ejecutar la sentencia select sobre la tabla SDB_BGN_DOCUMENTO.
	 * @return Retorna  una colección de datos de tipo  DataConsultaDatosDocumento que coincide con los parametros de entrada.
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public synchronized Collection<DataConsultaDatosDocumento> findByDatosDocumento(
	    ConsultaCriteriosCalificacionDocumento acccd_consultaCriteriosCalificacionDocumento
	)
	    throws B2BException
	{
		DAOManager                             ldm_manager;
		Collection<DataConsultaDatosDocumento> ll_result;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		ll_result       = new ArrayList<DataConsultaDatosDocumento>();

		try
		{
			if(acccd_consultaCriteriosCalificacionDocumento != null)
			{
				Documento ld_documento;
				ld_documento = acccd_consultaCriteriosCalificacionDocumento.getDocumento();

				if(ld_documento != null)
				{
					String ls_idTipoDocumento;
					String ls_numero;
					Date   ld_fechaDocumento;
					String ls_idOficinaOrigen;

					ls_idTipoDocumento     = ld_documento.getIdTipoDocumento();
					ls_numero              = ld_documento.getNumero();
					ld_fechaDocumento      = ld_documento.getFechaDocumento();
					ls_idOficinaOrigen     = ld_documento.getIdOficinaOrigen();

					if(!StringUtils.isValidString(ls_idTipoDocumento))
						throw new B2BException(ErrorKeys.DEBE_SELECCIONAR_TIPO_DOC);

					if(!StringUtils.isValidString(ls_numero))
						throw new B2BException(ErrorKeys.DOCUMENTO_INVALIDO);

					if(ld_fechaDocumento == null)
						throw new B2BException(ErrorKeys.DEBE_DIGITAR_FECHA_DOC);

					if(!StringUtils.isValidString(ls_idOficinaOrigen))
						throw new B2BException(ErrorKeys.DEBE_SELECCIONAR_OFICINA_ORIGEN);

					AnotacionPredio       lap_anotacionPredio;
					Collection<Documento> lcd_documentos;

					lap_anotacionPredio     = acccd_consultaCriteriosCalificacionDocumento.getAnotacionPredio();
					lcd_documentos          = DaoCreator.getDocumentoDAO(ldm_manager).consultarDocumentos(ld_documento);

					if(!CollectionUtils.isValidCollection(lcd_documentos))
						throw new B2BException(ErrorKeys.NO_SE_ENCONTRARON_REGISTROS_CONSULTA);

					if(lap_anotacionPredio != null)
					{
						String ls_idCirculo;
						Long   ll_idMatricula;

						ls_idCirculo     = lap_anotacionPredio.getIdCirculo();

						ll_idMatricula = lap_anotacionPredio.getIdMatricula();

						if(StringUtils.isValidString(ls_idCirculo))
						{
							for(Documento ld_iterador : lcd_documentos)
							{
								DataConsultaDatosDocumento ldccdc_datos;
								AnotacionPredio            lap_anotacion;
								PredioRegistro             lpr_predioRegistro;
								Long                       ll_version;

								lap_anotacion     = lap_anotacionPredio;

								ldccdc_datos     = new DataConsultaDatosDocumento();

								lpr_predioRegistro = new PredioRegistro();
								lpr_predioRegistro.setIdCirculo(ls_idCirculo);
								lpr_predioRegistro.setIdMatricula(NumericUtils.getLong(ll_idMatricula));
								ll_version = null;

								String ls_idDocumento;

								ls_idDocumento = ld_iterador.getIdDocumento();

								if(StringUtils.isValidString(ls_idDocumento))
								{
									ll_version = ld_iterador.getVersionDocumento();

									if(NumericUtils.isValidLong(ll_version))
										ldccdc_datos.setVersion(ll_version);

									lap_anotacion.setIdDocumento(ls_idDocumento);
									lpr_predioRegistro.setIdDocumento(ls_idDocumento);

									lap_anotacion     = DaoCreator.getAnotacionPredioDAO(ldm_manager)
											                          .findAnotacionPredio(lap_anotacion);

									lpr_predioRegistro = DaoCreator.getPredioRegistroDAO(ldm_manager)
											                           .findByDocumento(lpr_predioRegistro);

									if((lap_anotacion != null) || (lpr_predioRegistro != null))
									{
										ldccdc_datos.setDocumento(ld_iterador);

										Collection<DataConsultaPorCriterio> lcdcpc_consultaPorCriterio;
										Collection<DataConsultaPorCriterio> lcdcpc_dataCriteriosFinal;

										lcdcpc_consultaPorCriterio     = DaoCreator.getConsultaPorCriteriosDAO(
											    ldm_manager
											).findByDatosDocumento(ls_idCirculo, ls_idDocumento);
										lcdcpc_dataCriteriosFinal      = new ArrayList<DataConsultaPorCriterio>();

										if(CollectionUtils.isValidCollection(lcdcpc_consultaPorCriterio))
										{
											LinkedHashMap<String, DataConsultaPorCriterio> llhm_data;

											llhm_data = new LinkedHashMap<String, DataConsultaPorCriterio>();

											for(DataConsultaPorCriterio ldcpc_iterador : lcdcpc_consultaPorCriterio)
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

													ls_matriculaCompleta = ls_idCirculoIterador + "-"
														+ ll_idMatriculaIterador;

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
										ls_solicitud = DaoCreator.getSolicitudDAO(ldm_manager)
												                     .findByIdDocumento(ls_idDocumento);

										if(ls_solicitud != null)
											ldccdc_datos.setSolicitud(
											    DaoCreator.getSolicitudDAO(ldm_manager).findById(ls_solicitud)
											);

										ll_result.add(ldccdc_datos);
									}
								}
							}
						}
					}

					if(!CollectionUtils.isValidCollection(ll_result))
						throw new B2BException(ErrorKeys.NO_SE_ENCONTRARON_REGISTROS_CONSULTA);
				}
				else
					throw new B2BException(ErrorKeys.ERROR_INFORMACION_DOC);
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

		return ll_result;
	}

	/**
	 * Método encargado de consultar el círculo asociado a un id turno determinado.
	 *
	 * @param as_idTurno Variable de tipo Long que contiene un id turno historia determinado.
	 * @return Retorna  una variable de tipo String que coincide con los parametros de entrada.
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public synchronized String findByIdTurno(String as_idTurno)
	    throws B2BException
	{
		DAOManager ldm_manager;
		String     ls_data;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		ls_data         = null;

		try
		{
			Collection<Map<String, Object>> lm_result;
			Map<Integer, Object>            lhmp_criterios;

			lhmp_criterios = new LinkedHashMap<Integer, Object>();

			lhmp_criterios.put(new Integer(1), as_idTurno);

			lm_result = DaoCreator.getUtilDAO(ldm_manager)
					                  .getCustonQuery(ConsultasUtilidades.CS_CIRCULO_BY_ID_TURNO, lhmp_criterios);

			if(CollectionUtils.isValidCollection(lm_result))
			{
				Iterator<Map<String, Object>> limso_iterador;

				limso_iterador = lm_result.iterator();

				if(limso_iterador.hasNext())
				{
					Map<String, Object> lmso_iterator;

					lmso_iterator = limso_iterador.next();

					if(lmso_iterator != null)
					{
						{
							Object lo_inicio;
							lo_inicio = lmso_iterator.get(IdentificadoresCommon.ID_CIRCULO);

							if((lo_inicio != null) && lo_inicio instanceof String)
								ls_data = ((String)lo_inicio).toString();
						}
					}
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findByIdTurno", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return ls_data;
	}

	/**
	 * Método encargado de listar todas la causales de corrección asociadas a un id solicitud, id circulo y matrícula determinado.
	 *
	 * @param lsm_solMat  Objeto que contiene los datos necesarios para ejecutar la sentencia select sobre la tabla SDB_ACC_SOLICITUD_CORRECCION.
	 * @param as_solicitudNueva de as solicitud nueva
	 * @return Retorna  una colección de datos de tipo  CausalCorreccion que coincide con los parametros de entrada.
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public synchronized Collection<CausalCorreccion> findCausalesCorreccionCalificacion(
	    SolicitudMatricula lsm_solMat, String as_solicitudNueva
	)
	    throws B2BException
	{
		DAOManager                   ldm_manager;
		Collection<CausalCorreccion> lccc_return;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		lccc_return     = new LinkedList<CausalCorreccion>();

		try
		{
			if(lsm_solMat != null)
			{
				CausalCorreccionDAO          lcc_DAO;
				SolicitudCorreccionDAO       lsc_DAO;
				Collection<CausalCorreccion> lccc_datos;
				SolicitudCorreccion          lsc_solCorreccion;

				lcc_DAO               = DaoCreator.getCausalCorreccionDAO(ldm_manager);
				lsc_DAO               = DaoCreator.getSolicitudCorreccionDAO(ldm_manager);
				lccc_datos            = null;
				lsc_solCorreccion     = new SolicitudCorreccion();

				lsc_solCorreccion.setIdSolicitud(as_solicitudNueva);
				lsc_solCorreccion.setIdCirculo(lsm_solMat.getIdCirculo());
				lsc_solCorreccion.setIdMatricula(NumericUtils.getBigInteger(lsm_solMat.getIdMatricula()));

				Collection<SolicitudCorreccion> lcsc_correcciones;

				lccc_datos     = lcc_DAO.findAllActivo();

				lcsc_correcciones = lsc_DAO.findBySolicitudCirculoMatricula(lsc_solCorreccion);

				if(
				    CollectionUtils.isValidCollection(lcsc_correcciones)
					    && CollectionUtils.isValidCollection(lccc_datos)
				)
				{
					for(SolicitudCorreccion lsc_solCorr : lcsc_correcciones)
					{
						for(CausalCorreccion lcc_causal : lccc_datos)
						{
							BigInteger lbi_idCausal;
							lbi_idCausal = lsc_solCorr.getIdCausalCorreccion();

							if(
							    NumericUtils.isValidBigInteger(lbi_idCausal)
								    && (lbi_idCausal.intValue() == lcc_causal.getIdCausalCorreccion().intValue())
							)
							{
								lcc_causal.setObservaciones(lsc_solCorr.getObservacion());
								lcc_causal.setSeleccionado(
								    lsc_solCorr.getSeleccionado().equalsIgnoreCase(EstadoCommon.S)
								);
								lccc_return.add(lcc_causal);

								break;
							}
						}
					}
				}
				else
					lccc_return = lccc_datos;
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findCausalesCorreccionCalificacion", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lccc_return;
	}

	/**
	 *     Método encargado de consultar el detalle de un id constante determinado.
	 *
	 * @param as_param Variable de tipo String  que contiene un id constante determinado.
	 * @return Retorna  un objeto de tipo Constantes que coincide con los parametros de entrada.
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public synchronized Constantes findConstanteById(String as_param)
	    throws B2BException
	{
		DAOManager ldm_manager;
		Constantes lc_data;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		lc_data         = null;

		try
		{
			lc_data = DaoCreator.getConstantesDAO(ldm_manager).findById(as_param);
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findConstanteById", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lc_data;
	}

	/**
	 * Método encargado de consultar los turnos de la etapa 80 y un usuario determinado, además se encarga de validar si muestra o no el detalle de dicho turno.
	 *
	 * @param auaiu_usuarioActividad Objeto que contiene los parametros para realizar la consulta, tales como Etapa y Usuario, Nir y/o Turno
	 * @return Colección de turnos historia consultados para la etapa 80 y el usuario determinado.
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public synchronized Collection<UsuarioActividadUI> findDataBandeja(UsuarioActividadUI auaiu_usuarioActividad)
	    throws B2BException
	{
		Collection<UsuarioActividadUI> lcth_return;
		DAOManager                     ldm_manager;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		lcth_return     = null;

		try
		{
			if(auaiu_usuarioActividad != null)
			{
				if(
				    StringUtils.isValidString(auaiu_usuarioActividad.getIdUsuario())
					    && NumericUtils.isValidBigDecimal(auaiu_usuarioActividad.getIdEtapa())
				)
				{
					String ls_nir;
					String ls_turno;

					ls_nir       = auaiu_usuarioActividad.getNir();
					ls_turno     = auaiu_usuarioActividad.getIdTurno();

					ConstantesDAO lc_DAO;

					ConsultaTrazabilidadDAO lct_DAO;

					lct_DAO      = DaoCreator.getConsultaTrazabilidadDAO(ldm_manager);
					lcth_return  = lct_DAO.findDatosBandeja(auaiu_usuarioActividad);
					lc_DAO       = DaoCreator.getConstantesDAO(ldm_manager);

					if(CollectionUtils.isValidCollection(lcth_return))
					{
						Constantes lc_constante;
						lc_constante = new Constantes();
						lc_constante.setIdConstante(ConstanteCommon.ETAPAS_DEVOLUCION_CALIFICACION);

						lc_constante = lc_DAO.findById(lc_constante);

						if(lc_constante != null)
						{
							String[]                    lsa_etapas;
							HashMap<BigDecimal, String> lhmss_etapas;

							lsa_etapas       = lc_constante.getCaracter().split(",");
							lhmss_etapas     = new HashMap<BigDecimal, String>();

							if(CollectionUtils.isValidCollection(lsa_etapas))
							{
								for(String ls_iterador : lsa_etapas)
									lhmss_etapas.put(
									    NumericUtils.getBigDecimal(NumericUtils.getLong(ls_iterador)), ls_iterador
									);

								boolean lb_mostrarPrimero;

								lb_mostrarPrimero = false;

								for(UsuarioActividadUI luaui_iterador : lcth_return)
								{
									if(luaui_iterador != null)
									{
										BigDecimal lbd_etapa;

										lbd_etapa = luaui_iterador.getIdEtapa();

										if(NumericUtils.isValidBigDecimal(lbd_etapa))
										{
											if(lhmss_etapas.containsKey(lbd_etapa))
												luaui_iterador.setMostrar(true);
											else
											{
												if(!lb_mostrarPrimero)
												{
													luaui_iterador.setMostrar(true);
													lb_mostrarPrimero = true;
												}
											}
										}

										if(StringUtils.isValidString(ls_nir))
										{
											if(ls_nir.equals(luaui_iterador.getNir()))
											{
												lcth_return = new ArrayList<UsuarioActividadUI>();
												lcth_return.add(luaui_iterador);
											}
										}
										else if(StringUtils.isValidString(ls_turno))
										{
											if(ls_turno.equals(luaui_iterador.getIdTurno()))
											{
												lcth_return = new ArrayList<UsuarioActividadUI>();
												lcth_return.add(luaui_iterador);
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

			clh_LOGGER.error("findDataBandeja", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lcth_return;
	}

	/**
	 * Consulta en base de datos un registro de datos ant sistema por su id.
	 *
	 * @param as_idDatosAntSistema Objeto contenedor del id a utilizar como filtro en la consulta
	 * @return datos ant sistema resultante de la consulta
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public synchronized DatosAntSistema findDatosAntSistemaById(String as_idDatosAntSistema)
	    throws B2BException
	{
		DAOManager      ldm_manager;
		DatosAntSistema ldas_return;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		ldas_return     = null;

		try
		{
			if(!StringUtils.isValidString(as_idDatosAntSistema))
				throw new B2BException(ErrorKeys.ERROR_INTERNO_SISTEMA);

			ldas_return = new DatosAntSistema();

			ldas_return.setIdDatosAntSistema(as_idDatosAntSistema);

			ldas_return = DaoCreator.getDatosAntSistemaDAO(ldm_manager).findById(ldas_return);
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findDatosAntSistemaById", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return ldas_return;
	}

	/**
	 * Método encargado de realizar consultas dinámicas de acuerdo al valor del parámetro as_section.
	 *
	 * @param as_userId Variable de tipo String que contiene el usuario que realiza la acción.
	 * @param as_idTurnoHistoria Variable de tipo Long que contiene un id turno historia determinado.
	 * @param as_section Variable de tipo String que contiene el tipo de consulta(PREDIO,DOCUMENTO,ACTOS,MATRICULAS) a ejecutar.
	 * @param ad_fechaDocumento de ad fecha documento
	 * @return Retorna una lista de LinkedHashMap que coincide con los parametros de entrada.
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public synchronized List<Map<String, Object>> findDatosPredioByTurno(
	    String as_userId, String as_idTurnoHistoria, String as_section, Date ad_fechaDocumento
	)
	    throws B2BException
	{
		DAOManager                ldm_manager;
		List<Map<String, Object>> ll_result;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		ll_result       = null;

		try
		{
			if(StringUtils.isValidString(as_section))
			{
				Map<Integer, Object> lhmp_criterios;
				StringBuilder        lsb_query;

				lsb_query          = new StringBuilder();
				lhmp_criterios     = new LinkedHashMap<Integer, Object>();

				lhmp_criterios.put(Integer.valueOf(1), as_idTurnoHistoria);

				if(as_section.equals(IdentificadoresCommon.PREDIO))
					lsb_query.append(ConsultasUtilidades.CS_CONSULTA_PREDIO_DOCUMENTO);
				else if(as_section.equals(IdentificadoresCommon.DOCUMENTO))
					lsb_query.append(ConsultasUtilidades.CS_DATOS_DOCUMENTO_PREDIO);
				else if(
				    as_section.equals(IdentificadoresCommon.ACTOS)
					    || as_section.equals(IdentificadoresCommon.ACTOS_MAYOR_VALOR)
				)
					lsb_query.append(ConsultasUtilidades.CS_ACTOS_PREDIO);
				else if(as_section.equals(IdentificadoresCommon.MATRICULAS))
					lsb_query.append(ConsultasUtilidades.cs_CONSULTA_MATRICULAS_DEFINITIVAS);
				else if(as_section.equals(IdentificadoresCommon.TRASLADOS))
					lsb_query.append(ConsultasUtilidades.cs_CONSULTA_MATRICULAS_TRASLADO);
				else if(as_section.equals(IdentificadoresCommon.MATRICULAS_TEMPORALES))
				{
					Turno lt_turno;

					lt_turno = validarEtapaGrabacion(ldm_manager, as_idTurnoHistoria);

					if(lt_turno != null)
					{
						lhmp_criterios.put(Integer.valueOf(1), lt_turno.getIdSolicitud());
						lhmp_criterios.put(Integer.valueOf(2), lt_turno.getIdCirculo());
						lsb_query.append(ConsultasUtilidades.CS_CONSULTA_MATRICULAS_TEMPORALES_37);
					}
					else
						lsb_query.append(ConsultasUtilidades.CS_CONSULTA_MATRICULAS_TEMPORALES);
				}
				else if(as_section.equals(IdentificadoresCommon.TURNO_CON_TRAMITES_EN_CURSO))
				{
					String        ls_idProceso;
					TurnoHistoria lth_data;

					ls_idProceso     = null;
					lth_data         = DaoCreator.getTurnoHistoriaDAO(ldm_manager)
							                         .findById(NumericUtils.getLongWrapper(as_idTurnoHistoria));

					if(lth_data != null)
						ls_idProceso = lth_data.getIdProceso();

					if(StringUtils.isValidString(ls_idProceso))
					{
						if(ls_idProceso.equalsIgnoreCase(ProcesoCommon.ID_PROCESO_3))
							lsb_query.append(
							    ConsultasUtilidades.CS_CONSULTA_TURNOS_CON_TRAMITES_EN_CURSO_CORRECCIONES_MANTIS2651
							);
						else if(ls_idProceso.equalsIgnoreCase(ProcesoCommon.ID_PROCESO_37))
							lsb_query.append(
							    ConsultasUtilidades.CS_CONSULTA_TURNOS_CON_TRAMITES_EN_CURSO_GRABACION_MANTIS2651
							);
						else
							lsb_query.append(ConsultasUtilidades.CS_CONSULTA_TURNOS_CON_TRAMITES_EN_CURSO_MANTIS2651);
					}
					else
						lsb_query.append(ConsultasUtilidades.CS_CONSULTA_TURNOS_CON_TRAMITES_EN_CURSO_MANTIS2651);
				}
				else if(as_section.equals(IdentificadoresCommon.TURNOS_FECHAS))
				{
					TurnoHistoria             lth_data;
					Collection<TurnoHistoria> lcth_data;
					Constantes                ls_constanteEspera;
					Constantes                ls_constanteProrroga;
					boolean                   lb_turnosEspera;
					boolean                   lb_turnosEsperaProrroga;

					lth_data     = DaoCreator.getTurnoHistoriaDAO(ldm_manager)
							                     .findById(NumericUtils.getLongWrapper(as_idTurnoHistoria));

					ls_constanteEspera          = new Constantes(ConstanteCommon.ETAPAS_EN_ESPERA_DE_TIEMPOS);
					ls_constanteProrroga        = new Constantes(ConstanteCommon.ETAPAS_EN_ESPERA_DE_TIEMPOS_PRORROGA);
					lb_turnosEsperaProrroga     = false;
					lb_turnosEspera             = false;
					ls_constanteEspera          = DaoCreator.getConstantesDAO(ldm_manager).findById(ls_constanteEspera);
					ls_constanteProrroga        = DaoCreator.getConstantesDAO(ldm_manager).findById(
						    ls_constanteProrroga
						);

					if(lth_data != null)
					{
						if(ls_constanteEspera != null)
						{
							lcth_data = DaoCreator.getTurnoHistoriaDAO(ldm_manager)
									                  .findAllByIdTurno(lth_data.getIdTurno());

							if(CollectionUtils.isValidCollection(lcth_data))
							{
								for(TurnoHistoria li_iteradorTurnoHistoria : lcth_data)
								{
									String ls_caracter;
									String ls_idEtapa;
									String ls_estadoTurnoHistoria;
									ls_caracter                = ls_constanteEspera.getCaracter();
									ls_idEtapa                 = StringUtils.getString(
										    li_iteradorTurnoHistoria.getIdEtapa()
										);
									ls_estadoTurnoHistoria     = li_iteradorTurnoHistoria.getEstadoActividad();

									if(StringUtils.isValidString(ls_caracter))
									{
										Collection<String> lcs_return;
										lcs_return = separarPorSimboloComa(ls_caracter, true);

										if(CollectionUtils.isValidCollection(lcs_return))
										{
											for(String ls_iterador : lcs_return)
											{
												if(
												    StringUtils.isValidString(ls_iterador)
													    && ls_iterador.equalsIgnoreCase(ls_idEtapa)
												)
													lb_turnosEspera = true;
											}
										}
									}

									if((ls_constanteProrroga != null) && !lb_turnosEspera)
									{
										ls_caracter     = ls_constanteProrroga.getCaracter();
										ls_idEtapa      = StringUtils.getString(li_iteradorTurnoHistoria.getIdEtapa());

										if(StringUtils.isValidString(ls_caracter))
										{
											Collection<String> lcs_return;
											lcs_return = separarPorSimboloComa(ls_caracter, true);

											if(CollectionUtils.isValidCollection(lcs_return))
											{
												for(String ls_iterador : lcs_return)
												{
													if(
													    StringUtils.isValidString(ls_iterador)
														    && ls_iterador.equalsIgnoreCase(ls_idEtapa)
													)
														lb_turnosEsperaProrroga = true;
												}
											}
										}
									}

									if(lb_turnosEspera && ls_estadoTurnoHistoria.equalsIgnoreCase("TER"))
										lsb_query.append(ConsultasUtilidades.CS_TURNOS_EN_ESPERA);
									else if(
									    lb_turnosEspera
										    && (ls_estadoTurnoHistoria.equalsIgnoreCase("AUT")
										    || ls_estadoTurnoHistoria.equalsIgnoreCase("ASG"))
									)
										lsb_query.append(ConsultasUtilidades.CS_TURNOS_EN_ESPERA_AUT);
									else if(lb_turnosEsperaProrroga)
										lsb_query.append(ConsultasUtilidades.CS_TURNOS_EN_ESPERA_PRORROGA);

									lhmp_criterios.put(
									    Integer.valueOf(1), li_iteradorTurnoHistoria.getIdTurnoHistoria()
									);

									lb_turnosEspera             = false;
									lb_turnosEsperaProrroga     = false;

									if(StringUtils.isValidString(lsb_query.toString()))
									{
										ll_result     = DaoCreator.getUtilDAO(ldm_manager)
												                      .getCustonQuery(
												    lsb_query.toString(), lhmp_criterios
												);
										lsb_query     = new StringBuilder();
									}
								}
							}
						}
					}
				}
				else if(as_section.equals(IdentificadoresCommon.ACTOS_FECHAS))
				{
					lsb_query.append(ConsultasUtilidades.CS_ACTOS_ESPERA_PRORROGA);

					lhmp_criterios.put(Integer.valueOf(1), as_idTurnoHistoria);
				}

				if(as_section.equals(IdentificadoresCommon.ACTOS))
					lsb_query.append(" NULL");
				else if(as_section.equals(IdentificadoresCommon.ACTOS_MAYOR_VALOR))
					lsb_query.append(" NOT NULL");

				if(as_section.equals(IdentificadoresCommon.ACTOS_VINCULADOS))
				{
					lsb_query = new StringBuilder();

					lsb_query.append(ConsultasUtilidades.CS_ACTOS_PREDIO_VINCULADOS);

					List<Map<String, Object>> llmso_mso;

					llmso_mso = DaoCreator.getUtilDAO(ldm_manager).getCustonQuery(lsb_query.toString(), lhmp_criterios);

					if(CollectionUtils.isValidCollection(llmso_mso))
					{
						TurnoDerivado             ltd_turnosDerivado;
						Collection<TurnoDerivado> lctd_turnoDerivados;

						ltd_turnosDerivado = new TurnoDerivado();

						ltd_turnosDerivado.setIdTurnoPadre(as_idTurnoHistoria);

						lctd_turnoDerivados = DaoCreator.getTurnoDerivadoDAO(ldm_manager)
								                            .findByIdTurnoPadreVinculados(ltd_turnosDerivado);

						if(CollectionUtils.isValidCollection(lctd_turnoDerivados))
						{
							for(TurnoDerivado ltd_tmp : lctd_turnoDerivados)
							{
								lhmp_criterios = new LinkedHashMap<Integer, Object>();
								lhmp_criterios.put(Integer.valueOf(1), ltd_tmp.getIdTurnoHijo());

								ll_result = DaoCreator.getUtilDAO(ldm_manager)
										                  .getCustonQuery(lsb_query.toString(), lhmp_criterios);

								if(CollectionUtils.isValidCollection(ll_result))
									llmso_mso.addAll(ll_result);
							}

							if(CollectionUtils.isValidCollection(llmso_mso))
								ll_result = llmso_mso;
						}
					}
				}
				else if(StringUtils.isValidString(lsb_query.toString()))
					ll_result = DaoCreator.getUtilDAO(ldm_manager).getCustonQuery(lsb_query.toString(), lhmp_criterios);

				if(CollectionUtils.isValidCollection(ll_result))
				{
					if(as_section.equals(IdentificadoresCommon.PREDIO))
					{
						Map<String, Object> lmso_mso;

						lmso_mso = ll_result.get(0);

						if(lmso_mso != null)
						{
							String ls_idTurno;

							ls_idTurno = StringUtils.getString(lmso_mso.get("ID_TURNO"));

							if(StringUtils.isValidString(ls_idTurno))
							{
								Collection<SolicitudMatricula> lcsm_return;
								StringBuilder                  lsb_sb;
								lsb_sb     = new StringBuilder();

								lcsm_return = DaoCreator.getSolicitudMatriculaDAO(ldm_manager)
										                    .findMatriculasByturno(ls_idTurno);

								if(CollectionUtils.isValidCollection(lcsm_return))
								{
									for(SolicitudMatricula lsm_tmp : lcsm_return)
									{
										if(lsm_tmp != null)
											lsb_sb.append(StringUtils.getStringNotNull(lsm_tmp.getIdCirculo()) + ",");
									}
								}

								Collection<AlertaTurnoTramite> lcat_cat;

								lcat_cat = DaoCreator.getAlertaTurnoTramiteDAO(ldm_manager)
										                 .findByIdTurnoCirculoActivo(ls_idTurno, lsb_sb.toString());

								if(CollectionUtils.isValidCollection(lcat_cat))
								{
									SolicitudDAO lsd_solicitudDAO;
									TurnoDAO     ltd_turnoDAO;

									lsd_solicitudDAO     = DaoCreator.getSolicitudDAO(ldm_manager);
									ltd_turnoDAO         = DaoCreator.getTurnoDAO(ldm_manager);

									for(AlertaTurnoTramite latt_alerta : lcat_cat)
									{
										if(latt_alerta != null)
										{
											{
												Solicitud ls_solInicial;

												ls_solInicial = lsd_solicitudDAO.findById(latt_alerta.getIdSolicitud());

												if(ls_solInicial != null)
													latt_alerta.setNirAfectado(ls_solInicial.getNir());
											}

											{
												Solicitud ls_solVinculada;

												ls_solVinculada = lsd_solicitudDAO.findById(
													    latt_alerta.getIdSolicitudVinculada()
													);

												if(ls_solVinculada != null)
													latt_alerta.setNirVinculado(ls_solVinculada.getNir());
											}

											{
												String ls_idTurnoAsociado;

												ls_idTurnoAsociado = latt_alerta.getIdTurnoAsociado();

												if(!StringUtils.isValidString(ls_idTurnoAsociado))
													latt_alerta.setIdTurnoAsociado(ConstanteCommon.SIN_INFORMACION);
											}

											{
												Turno lt_turnoActual;

												lt_turnoActual = ltd_turnoDAO.findById(ls_idTurno);

												if(lt_turnoActual != null)
												{
													Collection<Turno> lct_turnosAsociados;
													String            ls_turnosAsociados;

													lct_turnosAsociados     = ltd_turnoDAO
															.findByIdSolicitudProcesoDifTurno(
															    lt_turnoActual.getIdSolicitud(),
															    lt_turnoActual.getIdProceso(), ls_idTurno
															);
													ls_turnosAsociados      = IdentificadoresCommon.SIN_INFORMACION;

													if(CollectionUtils.isValidCollection(lct_turnosAsociados))
													{
														Iterator<Turno> lit_iterador;
														StringBuilder   lsb_turnosAsociados;

														lit_iterador            = lct_turnosAsociados.iterator();
														lsb_turnosAsociados     = new StringBuilder();

														while(lit_iterador.hasNext())
														{
															Turno lt_turnoTmp;

															lt_turnoTmp = lit_iterador.next();

															if(lt_turnoTmp != null)
															{
																lsb_turnosAsociados.append(lt_turnoTmp.getIdTurno());

																if(lit_iterador.hasNext())
																	lsb_turnosAsociados.append(
																	    IdentificadoresCommon.SIMBOLO_COMA
																	);
															}
														}

														String ls_resultado;

														ls_resultado = lsb_turnosAsociados.toString();

														if(StringUtils.isValidString(ls_resultado))
															ls_turnosAsociados = ls_resultado;
													}

													latt_alerta.setTurnosAsociadosSolicitud(ls_turnosAsociados);
												}
											}

											{
												String ls_estado;

												ls_estado = latt_alerta.getActivo();

												if(StringUtils.isValidString(ls_estado))
													latt_alerta.setDescripcionEstado(
													    ls_estado.equals(EstadoCommon.S) ? EstadoCommon.ACTIVO_TXT
													                                     : EstadoCommon.INACTIVO_TXT
													);
											}
										}
									}
								}

								Map<String, Object> lmso_lmso;
								lmso_lmso = new LinkedHashMap<String, Object>();

								lmso_mso.put("ALERTAS", lcat_cat);
								ll_result.add(lmso_lmso);
							}
						}
					}
					else if(
					    (as_section.equals(IdentificadoresCommon.MATRICULAS)
						    || as_section.equals(IdentificadoresCommon.MATRICULAS_TEMPORALES))
					)
					{
						for(Map<String, Object> lhm_tmp : ll_result)
						{
							if(lhm_tmp != null)
							{
								if(lhm_tmp.containsKey(IdentificadoresCommon.MATRICULA))
								{
									Boolean lb_alerta;
									Long    ll_idNaturaleza;
									String  ls_predioInconsistente;
									Long    ll_version;
									Object  lo_idNaturaleza;
									Object  lo_version;

									lb_alerta                  = Boolean.FALSE;
									lo_idNaturaleza            = lhm_tmp.get(
										    IdentificadoresCommon.ID_NATURALEZA_JURIDICA
										);
									ls_predioInconsistente     = (lhm_tmp.get(
										    IdentificadoresCommon.PREDIO_INCONSISTENTE
										) != null) ? lhm_tmp.get(IdentificadoresCommon.PREDIO_INCONSISTENTE).toString()
										           : null;
									lo_version                 = lhm_tmp.get(IdentificadoresCommon.VERSION);
									ll_idNaturaleza            = (lo_idNaturaleza != null)
										? NumericUtils.getLongWrapper(lo_idNaturaleza.toString()) : null;
									ll_version                 = (lo_version != null)
										? NumericUtils.getLongWrapper(lo_version.toString()) : null;

									if(
									    StringUtils.isValidString(ls_predioInconsistente)
										    && ls_predioInconsistente.equalsIgnoreCase(EstadoCommon.S)
									)
									{
										String ls_estadoPredio;
										ls_estadoPredio = StringUtils.getStringNotNull(
											    lhm_tmp.get(IdentificadoresCommon.ESTADO_PREDIO).toString()
											);

										lhm_tmp.put(
										    IdentificadoresCommon.ESTADO_PREDIO,
										    ls_estadoPredio + IdentificadoresCommon.SIMBOLO_GUION_ESPACIOS
										    + EstadoCommon.INCONSISTENTE
										);
									}

									if(
									    NumericUtils.isValidLong(ll_idNaturaleza)
										    && NumericUtils.isValidLong(ll_version)
									)
										lb_alerta = Boolean.TRUE;

									lhm_tmp.put(IdentificadoresCommon.ALERTA_NATURALEZA, lb_alerta);
								}
							}
						}
					}
					else if(as_section.equals(IdentificadoresCommon.TURNO_CON_TRAMITES_EN_CURSO))
					{
						List<Map<String, Object>> llmso_llmso;

						llmso_llmso = new LinkedList<Map<String, Object>>();

						for(Map<String, Object> lhm_tmp : ll_result)
						{
							if(lhm_tmp != null)
							{
								if(lhm_tmp.containsKey(IdentificadoresCommon.ID_TURNO_BLOQUEO))
								{
									String ls_turnoBloqueo;
									String ls_matricula;

									ls_turnoBloqueo     = null;
									ls_matricula        = null;

									{
										Object lo_turnoBloqueo;
										lo_turnoBloqueo = lhm_tmp.get(IdentificadoresCommon.ID_TURNO_BLOQUEO);

										if((lo_turnoBloqueo != null) && lo_turnoBloqueo instanceof String)
											ls_turnoBloqueo = ((String)lo_turnoBloqueo);
									}

									{
										Object lo_matricula;
										lo_matricula = lhm_tmp.get(IdentificadoresCommon.MATRICULA);

										if((lo_matricula != null) && lo_matricula instanceof String)
											ls_matricula = ((String)lo_matricula);
									}

									if(CollectionUtils.isValidCollection(llmso_llmso))
									{
										List<Map<String, Object>> llmso_tmp;
										boolean                   lb_agregado;
										int                       li_contador;
										int                       li_iteracion;

										llmso_tmp        = new LinkedList<Map<String, Object>>(llmso_llmso);
										lb_agregado      = false;
										li_contador      = llmso_llmso.size();
										li_iteracion     = 0;

										Iterator<Map<String, Object>> limso_iterator;

										limso_iterator   = llmso_llmso.iterator();

										while(limso_iterator.hasNext() && !lb_agregado)
										{
											Map<String, Object> lhm_actual;

											lhm_actual = limso_iterator.next();
											li_iteracion++;

											if(lhm_actual != null)
											{
												if(lhm_actual.containsKey(IdentificadoresCommon.ID_TURNO_BLOQUEO))
												{
													String ls_turno;

													ls_turno = null;

													{
														Object lo_turnoBloqueo;
														lo_turnoBloqueo = lhm_actual.get(
															    IdentificadoresCommon.ID_TURNO_BLOQUEO
															);

														if(
														    (lo_turnoBloqueo != null)
															    && lo_turnoBloqueo instanceof String
														)
															ls_turno = ((String)lo_turnoBloqueo);
													}

													if(StringUtils.isValidString(ls_turno))
													{
														String ls_matriculaRegistrada;

														ls_matriculaRegistrada = null;

														{
															Object lo_matricula;
															lo_matricula = lhm_actual.get(
																    IdentificadoresCommon.MATRICULA
																);

															if((lo_matricula != null) && lo_matricula instanceof String)
																ls_matriculaRegistrada = ((String)lo_matricula);
														}

														if(ls_turno.equalsIgnoreCase(ls_turnoBloqueo))
														{
															StringBuilder lbs_bs;

															lbs_bs = new StringBuilder();

															if(StringUtils.isValidString(ls_matricula))
															{
																if(StringUtils.isValidString(ls_matriculaRegistrada))
																{
																	lbs_bs.append(ls_matriculaRegistrada);
																	lbs_bs.append(IdentificadoresCommon.SIMBOLO_COMA);
																}

																lbs_bs.append(ls_matricula);
															}

															ls_matricula = lbs_bs.toString();

															lhm_actual.put(
															    IdentificadoresCommon.MATRICULA, ls_matricula
															);

															lb_agregado = true;
														}
														else if(li_iteracion == li_contador)
														{
															Map<String, Object> lmso_lmso;

															lmso_lmso = new LinkedHashMap<String, Object>();

															lmso_lmso.put(
															    IdentificadoresCommon.ID_TURNO_BLOQUEO, ls_turnoBloqueo
															);
															lmso_lmso.put(
															    IdentificadoresCommon.FECHA_CREACION,
															    lhm_tmp.get(IdentificadoresCommon.FECHA_CREACION)
															);
															lmso_lmso.put(
															    IdentificadoresCommon.ESTADO_TURNO,
															    lhm_tmp.get(IdentificadoresCommon.ESTADO_TURNO)
															);
															lmso_lmso.put(
															    IdentificadoresCommon.NOMBRE_USUARIO,
															    lhm_tmp.get(IdentificadoresCommon.NOMBRE_USUARIO)
															);

															lmso_lmso.put(
															    IdentificadoresCommon.MATRICULA, ls_matricula
															);

															llmso_tmp.add(lmso_lmso);
															lb_agregado = true;
														}
													}
												}
											}
										}

										llmso_llmso     = null;
										llmso_llmso     = llmso_tmp;
									}
									else
									{
										Map<String, Object> lmso_lmso;

										lmso_lmso = new LinkedHashMap<String, Object>();

										lmso_lmso.put(IdentificadoresCommon.ID_TURNO_BLOQUEO, ls_turnoBloqueo);
										lmso_lmso.put(
										    IdentificadoresCommon.FECHA_CREACION,
										    lhm_tmp.get(IdentificadoresCommon.FECHA_CREACION)
										);
										lmso_lmso.put(
										    IdentificadoresCommon.ESTADO_TURNO,
										    lhm_tmp.get(IdentificadoresCommon.ESTADO_TURNO)
										);
										lmso_lmso.put(
										    IdentificadoresCommon.NOMBRE_USUARIO,
										    lhm_tmp.get(IdentificadoresCommon.NOMBRE_USUARIO)
										);

										lmso_lmso.put(IdentificadoresCommon.MATRICULA, ls_matricula);

										llmso_llmso.add(lmso_lmso);
									}

									{
										Collection<SolicitudApoyoNacional> lcsan_solicitudApoyoNacional;

										lcsan_solicitudApoyoNacional = DaoCreator.getSolicitudApoyoNacionalDAO(
											    ldm_manager
											).findByIdTurnoRegistro(ls_turnoBloqueo);

										if(CollectionUtils.isValidCollection(lcsan_solicitudApoyoNacional))
										{
											for(SolicitudApoyoNacional lsan_temp : lcsan_solicitudApoyoNacional)
											{
												if(lsan_temp != null)
												{
													Map<String, Object> lmso_lmso;

													lmso_lmso = new LinkedHashMap<String, Object>();

													lmso_lmso.put(
													    IdentificadoresCommon.ID_TURNO_BLOQUEO, lsan_temp.getIdTurno()
													);

													lmso_lmso.put(
													    IdentificadoresCommon.FECHA_CREACION,
													    lsan_temp.getFechaCreacion()
													);
													lmso_lmso.put(
													    IdentificadoresCommon.ESTADO_TURNO,
													    lhm_tmp.get(IdentificadoresCommon.ESTADO_TURNO)
													);
													lmso_lmso.put(
													    IdentificadoresCommon.NOMBRE_USUARIO,
													    lhm_tmp.get(IdentificadoresCommon.NOMBRE_USUARIO)
													);

													lmso_lmso.put(IdentificadoresCommon.MATRICULA, ls_matricula);

													llmso_llmso.add(lmso_lmso);
												}
											}
										}
									}
								}
							}
						}

						ll_result = llmso_llmso;
					}
					else if(as_section.equals(IdentificadoresCommon.TURNOS_FECHAS))
					{
						for(Map<String, Object> lhm_tmp : ll_result)
						{
							if(lhm_tmp != null)
							{
								TurnoHistoria lth_data;

								lth_data = DaoCreator.getTurnoHistoriaDAO(ldm_manager)
										                 .findById(NumericUtils.getLongWrapper(as_idTurnoHistoria));

								if(lth_data != null)
								{
									if(lhm_tmp.containsKey(IdentificadoresCommon.ID_PROCESO))
									{
										String ls_idProceso;
										Object lo_idProceso;

										lo_idProceso     = lhm_tmp.get(IdentificadoresCommon.ID_PROCESO);
										ls_idProceso     = null;

										if(lo_idProceso != null)
											ls_idProceso = lo_idProceso.toString();

										if(
										    StringUtils.isValidString(ls_idProceso)
											    && ls_idProceso.equalsIgnoreCase("40")
										)
											lhm_tmp.put(IdentificadoresCommon.APORTA_DOCUMENTOS, "En espera");
										else
											lhm_tmp.put(IdentificadoresCommon.APORTA_DOCUMENTOS, "NO");
									}

									if(lhm_tmp.containsKey(IdentificadoresCommon.FECHA_INICIAL))
									{
										String ls_idProceso;
										Object lo_idProceso;

										lo_idProceso     = lhm_tmp.get(IdentificadoresCommon.FECHA_INICIAL);
										ls_idProceso     = null;

										if(lo_idProceso != null)
											ls_idProceso = lo_idProceso.toString();

										if(!StringUtils.isValidString(ls_idProceso))
											lhm_tmp.put(
											    IdentificadoresCommon.FECHA_INICIAL, lth_data.getFechaCreacion()
											);
									}
								}
							}
						}
					}
					else
					{
						if(ad_fechaDocumento != null)
						{
							setMensajeError(null);

							for(Map<String, Object> lhm_tmp : ll_result)
							{
								if(lhm_tmp != null)
								{
									String  ls_idTipoActo;
									Date    ld_fechaEjecutoria;
									Date    ld_fechaVencimiento;
									boolean lb_actoEnTabla;
									Date    ld_fechaDocumento = new Date();
									ls_idTipoActo          = (String)lhm_tmp.get("ID_TIPO_ACTO");
									ld_fechaEjecutoria     = (Date)lhm_tmp.get("FECHA_EJECUTORIA");

									ld_fechaDocumento     = ad_fechaDocumento;
									lb_actoEnTabla        = getRegistroBusiness().consultaEnTabla(ls_idTipoActo);

									if(lb_actoEnTabla)
									{
										FechaVenceTerminosUI lfvtui_fecha;

										lfvtui_fecha = new FechaVenceTerminosUI();

										lfvtui_fecha.setFechaInicial(ld_fechaEjecutoria);
										lfvtui_fecha.setTipoCalendario(EstadoCommon.H);
										lfvtui_fecha.setIdCirculo(null);
										lfvtui_fecha.setDiasCalcularFecha(NumericUtils.getInteger(90));

										ld_fechaVencimiento = getReferenceBusiness()
												                      .calcularFechaVencimiento(lfvtui_fecha);

										if(ld_fechaDocumento.after(ld_fechaVencimiento))
											setMensajeError(MessagesKeys.DOCUMENTO_VENCIDO_90_DIAS_HABILES);
										else
											setMensajeError(null);
									}
								}

								Date ld_fechaVencimiento;
								Date ld_fechaCreacion;
								ld_fechaCreacion        = (Date)lhm_tmp.get("FECHA_CREACION_TURNO");
								ld_fechaVencimiento     = (Date)lhm_tmp.get("FECHA_VENCIMIENTO");

								if(
								    (ld_fechaVencimiento != null) && (ld_fechaCreacion != null)
									    && ld_fechaVencimiento.before(ld_fechaCreacion)
								)
								{
									Object[] aoa_messageArgs = new String[1];
									aoa_messageArgs[0] = lhm_tmp.get("ID_TIPO_ACTO");
									lhm_tmp.put(
									    "I_MESSAGE",
									    addMessage(
									        MessagesKeys.FECHA_INFERIOR_ACTUAL_COBRO_MORA, aoa_messageArgs, false
									    )
									);
								}
							}
						}
					}
				}

				else if(as_section.equals(IdentificadoresCommon.TURNO_CON_TRAMITES_EN_CURSO))
				{
					List<Map<String, Object>> llmso_llmso;
					TurnoHistoria             lth_turnoHistoria;

					llmso_llmso           = new LinkedList<Map<String, Object>>();
					lth_turnoHistoria     = DaoCreator.getTurnoHistoriaDAO(ldm_manager)
							                              .findById(NumericUtils.getLongWrapper(as_idTurnoHistoria));

					if(lth_turnoHistoria != null)
					{
						String ls_idTurno;

						ls_idTurno = lth_turnoHistoria.getIdTurno();

						if(StringUtils.isValidString(ls_idTurno))
						{
							Collection<SolicitudApoyoNacional> lcsan_solicitudApoyoNacional;
							Turno                              lt_turno;

							lt_turno                         = DaoCreator.getTurnoDAO(ldm_manager).findById(ls_idTurno);
							lcsan_solicitudApoyoNacional     = DaoCreator.getSolicitudApoyoNacionalDAO(ldm_manager)
									                                         .findByIdTurnoRegistro(ls_idTurno);

							if(CollectionUtils.isValidCollection(lcsan_solicitudApoyoNacional) && (lt_turno != null))
							{
								Collection<SolicitudMatricula> lcsm_solicitudMatricula;

								lcsm_solicitudMatricula = DaoCreator.getSolicitudMatriculaDAO(ldm_manager)
										                                .findByIdSolicitud(
										    lt_turno.getIdSolicitud(), false
										);

								if(CollectionUtils.isValidCollection(lcsm_solicitudMatricula))
								{
									StringBuilder lbs_bs;

									lbs_bs = new StringBuilder();

									for(SolicitudMatricula lsc_temp : lcsm_solicitudMatricula)
									{
										if(lsc_temp != null)
										{
											String ls_idMatricula;

											ls_idMatricula = StringUtils.getString(lsc_temp.getIdMatricula());

											if(StringUtils.isValidString(ls_idMatricula))
											{
												lbs_bs.append(ls_idMatricula);
												lbs_bs.append(IdentificadoresCommon.SIMBOLO_COMA);
											}
										}
									}

									for(SolicitudApoyoNacional lsan_temp : lcsan_solicitudApoyoNacional)
									{
										if(lsan_temp != null)
										{
											Map<String, Object> lmso_lmso;

											lmso_lmso = new LinkedHashMap<String, Object>();

											lmso_lmso.put(
											    IdentificadoresCommon.ID_TURNO_BLOQUEO, lsan_temp.getIdTurno()
											);

											lmso_lmso.put(
											    IdentificadoresCommon.FECHA_CREACION, lsan_temp.getFechaCreacion()
											);
											lmso_lmso.put(
											    IdentificadoresCommon.ESTADO_TURNO, lt_turno.getIdEtapaActual()
											);
											lmso_lmso.put(
											    IdentificadoresCommon.NOMBRE_USUARIO, lsan_temp.getIdUsuarioCreacion()
											);

											lmso_lmso.put(IdentificadoresCommon.MATRICULA, lbs_bs.toString());

											llmso_llmso.add(lmso_lmso);
										}
									}

									ll_result = llmso_llmso;
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

		return ll_result;
	}

	/**
	 *  Método encargado de consultar los turnos para un id usuario y id etapa determinado.
	 *
	 * @param as_userId Variable de tipo String que contiene el usuario que realiza la acción.
	 * @param as_idTurno Variable de tipo String que contiene un id turno historia determinado.
	 * @param as_nir Variable de tipo String que contiene un nir determinado.
	 * @param al_idState  Variable de tipo Long que contiene un id etapa determinado
	 * @return Retorna una lista de LinkedHashMap que coincide con los parametros de entrada.
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public synchronized List<Map<String, Object>> findDetailInbox(
	    String as_userId, String as_idTurno, String as_nir, Long al_idState
	)
	    throws B2BException
	{
		DAOManager                ldm_manager;
		List<Map<String, Object>> ll_result;
		String                    ls_idSolicitud;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		ll_result       = null;

		try
		{
			Map<Integer, Object> lhmp_criterios;

			lhmp_criterios = new LinkedHashMap<Integer, Object>();
			lhmp_criterios.put(new Integer(1), al_idState);
			lhmp_criterios.put(new Integer(2), as_userId);

			ls_idSolicitud = null;

			if(StringUtils.isValidString(as_nir))
			{
				Solicitud ls_Solicitud = new Solicitud();
				ls_Solicitud.setNir(as_nir);
				ls_Solicitud = DaoCreator.getConsultaTrazabilidadDAO(ldm_manager).findByNIR(ls_Solicitud);

				if(ls_Solicitud != null)
					ls_idSolicitud = ls_Solicitud.getIdSolicitud();
				else
					ls_idSolicitud = as_nir;
			}

			ll_result = DaoCreator.getUtilDAO(ldm_manager)
					                  .getCustonQueryTurnos(
					    ConsultasUtilidades.CS_CONSULTA_BANDEJA_TURNOS, as_idTurno, ls_idSolicitud, lhmp_criterios
					);
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
	 * Consulta en base de datos un registro de detalles ant sistema por su id.
	 *
	 * @param adas_detalle de adas detalle
	 * @return detalle ant sistema resultante de la consulta
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public synchronized DetalleAntSistema findDetalleAntSistemaById(DetalleAntSistema adas_detalle)
	    throws B2BException
	{
		DAOManager        ldm_manager;
		DetalleAntSistema ldas_return;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		ldas_return     = null;

		try
		{
			if(adas_detalle == null)
				throw new B2BException(ErrorKeys.ERROR_INTERNO_SISTEMA);

			ldas_return = DaoCreator.getDetalleAntSistemaDAO(ldm_manager).findByDetalleYDatosAntSis(adas_detalle);

			if(ldas_return != null)
			{
				String ls_idDatosAntSistema;

				ls_idDatosAntSistema = adas_detalle.getIdDatosAntSistema();

				{
					LibroAntiguoSistema lctp_datos;

					lctp_datos = new LibroAntiguoSistema();

					lctp_datos.setIdLibroAntiguoSistema(NumericUtils.getLong(ldas_return.getIdLibroAntSistema()));

					lctp_datos = DaoCreator.getLibroAntiguoSistemaDAO(ldm_manager).findById(lctp_datos);

					if(lctp_datos == null)
						lctp_datos = new LibroAntiguoSistema(ConstanteCommon.SIN_INFORMACION);

					ldas_return.setLibroAntiguoSistema(lctp_datos);
				}

				{
					if(StringUtils.isValidString(ls_idDatosAntSistema))
					{
						DatosAntSistema ldas_datos;

						ldas_datos = new DatosAntSistema();

						ldas_datos.setIdDatosAntSistema(ls_idDatosAntSistema);

						ldas_datos = DaoCreator.getDatosAntSistemaDAO(ldm_manager).findById(ldas_datos);

						if(ldas_datos != null)
						{
							{
								Municipio lm_parametros;

								lm_parametros = new Municipio();

								lm_parametros.setIdPais(ldas_datos.getIdPais());
								lm_parametros.setIdDepartamento(ldas_datos.getIdDepartamento());
								lm_parametros.setIdMunicipio(ldas_return.getIdMunicipioTomo());

								lm_parametros = DaoCreator.getMunicipioDAO(ldm_manager).findById(lm_parametros);

								if(lm_parametros == null)
									lm_parametros = new Municipio(ConstanteCommon.SIN_INFORMACION);

								ldas_return.setMunicipioTomo(lm_parametros);
							}

							{
								String ls_idCirculo;

								ls_idCirculo = ldas_datos.getIdCirculo();

								if(StringUtils.isValidString(ls_idCirculo))
								{
									CirculoRegistral lcr_cr;

									lcr_cr = new CirculoRegistral();

									lcr_cr.setIdCirculo(ls_idCirculo);

									lcr_cr = DaoCreator.getCirculoRegistralDAO(ldm_manager).findById(lcr_cr);

									if(lcr_cr == null)
										lcr_cr = new CirculoRegistral(ConstanteCommon.SIN_INFORMACION);

									ldas_return.setCirculoRegistral(lcr_cr);
								}
							}

							{
								PredioTipo lpt_datos;

								lpt_datos = new PredioTipo();

								lpt_datos.setIdTipoPredio(ldas_datos.getIdTipoPredio());

								lpt_datos = DaoCreator.getPredioTipoDao(ldm_manager).findById(lpt_datos);

								if(lpt_datos == null)
									lpt_datos = new PredioTipo(ConstanteCommon.SIN_INFORMACION);

								ldas_return.setPredioTipo(lpt_datos);
							}

							ldas_return.setNombrePredio(adas_detalle.getNombrePredio());
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

			clh_LOGGER.error("findDetalleAntSistemaById", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return ldas_return;
	}

	/**
	 * Find direccion predio by id circulo matricula turno.
	 *
	 * @param al_idMatricula de al id matricula
	 * @param as_idCirculo de as id circulo
	 * @param as_idTurno de as id turno
	 * @return el valor de direccion predio acc
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algún error controlado.
	 */
	public synchronized DireccionPredioAcc findDireccionPredioByIdCirculoMatriculaTurno(
	    Long al_idMatricula, String as_idCirculo, String as_idTurno
	)
	    throws B2BException
	{
		DireccionPredioAcc ldpa_return;

		ldpa_return = null;

		if(
		    NumericUtils.isValidLong(al_idMatricula) && StringUtils.isValidString(as_idCirculo)
			    && StringUtils.isValidString(as_idTurno)
		)
		{
			DAOManager ldm_manager;

			ldm_manager = DaoManagerFactory.getDAOManager();

			try
			{
				ldpa_return = new DireccionPredioAcc();

				ldpa_return.setIdCirculo(as_idCirculo);
				ldpa_return.setIdMatricula(al_idMatricula);
				ldpa_return.setIdTurno(as_idTurno);

				ldpa_return = DaoCreator.getDireccionPredioAccDAO(ldm_manager).findByIdCirculoMatriculaTurno(
					    ldpa_return
					);
			}
			catch(B2BException lb2be_e)
			{
				ldm_manager.setRollbackOnly();

				clh_LOGGER.error("cargarDireccionesEnglobes", lb2be_e);

				throw lb2be_e;
			}
			finally
			{
				ldm_manager.close();
			}
		}

		return ldpa_return;
	}

	/**
	 * Método encargado de  consultar el id solicitud para un id turno historia determinado.
	 *
	 * @param as_idTurnoHistoria Variable de tipo String que contiene un id turno historia determinado.
	 * @return Retorna  una variable de tipo String que coincide con los parametros de entrada.
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public synchronized String findIdSolicitudByIdTurnoHistoria(String as_idTurnoHistoria)
	    throws B2BException
	{
		DAOManager ldm_manager;
		String     ls_data;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		ls_data         = null;

		try
		{
			Collection<Map<String, Object>> lm_result;
			Map<Integer, Object>            lhmp_criterios;

			lhmp_criterios = new LinkedHashMap<Integer, Object>();
			lhmp_criterios.put(new Integer(1), as_idTurnoHistoria);

			lm_result = DaoCreator.getUtilDAO(ldm_manager)
					                  .getCustonQuery(
					    ConsultasUtilidades.CS_SOLICITUD_BY_ID_TURNO_HIST, lhmp_criterios
					);

			if(CollectionUtils.isValidCollection(lm_result))
			{
				Iterator<Map<String, Object>> limso_iterador;

				limso_iterador = lm_result.iterator();

				if(limso_iterador.hasNext())
				{
					Map<String, Object> lmso_iterator;

					lmso_iterator = limso_iterador.next();

					if(lmso_iterator != null)
					{
						{
							Object lo_inicio;
							lo_inicio = lmso_iterator.get(IdentificadoresCommon.ID_SOLICITUD);

							if((lo_inicio != null) && lo_inicio instanceof String)
								ls_data = ((String)lo_inicio).toString();
						}
					}
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findIdSolicitudByIdTurnoHistoria", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return ls_data;
	}

	/**
	 *  Método encargado de consultar la cantidad de datos tramitados para un usuario determinado.
	 *
	 * @param at_turno de at turno
	 * @param al_idMotivo de al id motivo
	 * @return Retorna  un objeto que contiene  colección de datos   de tipo  TramiteCantidad que coincide con los parametros de entrada.
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public synchronized TramiteCantidad findInboxByUserId(Turno at_turno, long al_idMotivo)
	    throws B2BException
	{
		TramiteCantidad ltc_return;
		DAOManager      ldm_manager;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		ltc_return      = null;

		try
		{
			if(at_turno != null)
			{
				TurnoHistoriaDAO            lthd_DAO;
				Collection<TramiteCantidad> lctc_ctc;
				boolean                     lb_indVinculado;
				boolean                     lb_detalleRecepcionDocumentos;
				boolean                     lb_resolucionRecurso;
				boolean                     lb_detalleProhibiciones;
				long                        ll_idEtapa;

				lctc_ctc                          = new ArrayList<TramiteCantidad>();
				lthd_DAO                          = DaoCreator.getTurnoHistoriaDAO(ldm_manager);
				lb_indVinculado                   = at_turno.isIndVinculado();
				lb_detalleRecepcionDocumentos     = at_turno.isDetalleRecepcionDocumentos();
				lb_detalleProhibiciones           = at_turno.isIndProhibicion();
				ll_idEtapa                        = NumericUtils.getLong(at_turno.getIdEtapaActual());
				lb_resolucionRecurso              = ll_idEtapa == EtapaCommon.ID_ETAPA_ANALISIS_DE_RECURSOS_RESOLUCION_DE_RECURSOS;

				if(
				    (ll_idEtapa != EtapaCommon.ID_ETAPA_CONFRONTACION_CORRECTIVA)
					    && (ll_idEtapa != EtapaCommon.ID_ETAPA_HOMOLOGACION_ACTOS_LIQUIDACION)
					    && (ll_idEtapa != EtapaCommon.ID_ETAPA_CORRECCIONES)
					    && (ll_idEtapa != EtapaCommon.ID_ETAPA_ANALISIS_DE_TRASLADOS)
					    && (ll_idEtapa != EtapaCommon.ID_ETAPA_ANALISTA_DE_TRASLADOS_OFICINA_DESTINO)
					    && (ll_idEtapa != EtapaCommon.ID_ETAPA_GRABACION_MATRICULAS)
					    && (ll_idEtapa != EtapaCommon.ID_ETAPA_RESPONSABLE_CORRECCIONES)
					    && (ll_idEtapa != EtapaCommon.ID_ETAPA_ANALISTA_DE_COPIAS)
					    && (ll_idEtapa != EtapaCommon.ID_ETAPA_380)
					    && (ll_idEtapa != EtapaCommon.ID_ETAPA_RESPONSABLE_ACTUACIONES_ADMINISTRATIVAS)
					    && (ll_idEtapa != EtapaCommon.ID_ETAPA_SUSTANCIADOR_ACTUACIONES_ADMINISTRATIVAS)
					    && (ll_idEtapa != EtapaCommon.ID_ETAPA_ASIGNACION_DE_TURNOS_MANUAL_RECHAZO_DE_RECURSOS)
					    && (ll_idEtapa != EtapaCommon.PROYECTAR_RESOLUCION_TRASLADOS)
					    && (ll_idEtapa != EtapaCommon.ID_ETAPA_RESOLUCION_CREACION_SUPRESION_MODIFICACION)
					    && (ll_idEtapa != EtapaCommon.ID_ETAPA_AUTORIZACION_FIRMA_LIBROS_A_S)
					    && (ll_idEtapa != EtapaCommon.ID_ETAPA_460) && (ll_idEtapa != EtapaCommon.ID_ETAPA_430)
					    && (ll_idEtapa != EtapaCommon.ID_ETAPA_ANALISIS_DEVOLUCIONES_DE_DINERO)
					    && (ll_idEtapa != EtapaCommon.ID_ETAPA_EMISION_ACTO_ADMINISTRATIVO) && (!lb_resolucionRecurso)
				)
					at_turno.setIdEtapaActual(NumericUtils.getLongWrapper(EtapaCommon.ID_ETAPA_CALIFICACION));

				if(lb_indVinculado)
					lctc_ctc = lthd_DAO.turnosVinculados(at_turno, al_idMotivo);
				else if(lb_detalleRecepcionDocumentos)
					lctc_ctc = lthd_DAO.bandejaDetalleCalificacionRecepcionDocumentos(at_turno, al_idMotivo);
				else if(lb_detalleProhibiciones)
					lctc_ctc = lthd_DAO.turnosProhibiciones(at_turno, al_idMotivo);
				else
					lctc_ctc = lthd_DAO.bandejaCalificacion(at_turno, al_idMotivo);

				if(CollectionUtils.isValidCollection(lctc_ctc))
				{
					ltc_return = new TramiteCantidad();

					if(al_idMotivo > 0)
					{
						Collection<TramiteCantidad> lc_dataFinal;

						lc_dataFinal = new ArrayList<TramiteCantidad>();

						for(TramiteCantidad lotc_tmp : lctc_ctc)
						{
							if(lotc_tmp != null)
							{
								String                 ls_idTurnoHistoria;
								TurnoHistoria          lth_turnoHistoria;
								Collection<Aprobacion> lca_turnosDerivados;

								ls_idTurnoHistoria     = lotc_tmp.getIdTurnoHistoria();
								lth_turnoHistoria      = new TurnoHistoria();

								if(StringUtils.isValidString(ls_idTurnoHistoria))
								{
									lth_turnoHistoria.setIdTurnoHistoria(
									    NumericUtils.getLongWrapper(ls_idTurnoHistoria)
									);
									lth_turnoHistoria = lthd_DAO.findById(lth_turnoHistoria);

									lotc_tmp.setMotivoNoTramite(lth_turnoHistoria.getMotivoNoTramite());

									if(lth_turnoHistoria != null)
									{
										TurnoHistoria lth_tmp;
										lth_tmp = new TurnoHistoria();

										lth_tmp.setIdTurnoHistoria(
										    NumericUtils.getLongWrapper(lth_turnoHistoria.getIdAnterior())
										);

										lotc_tmp.setFechaReparto(lth_turnoHistoria.getFechaReparto());

										lth_tmp = lthd_DAO.findById(lth_tmp);

										if(lth_tmp != null)
										{
											if(
											    NumericUtils.getLong(lth_tmp.getIdEtapa()) == EtapaCommon.ID_ETAPA_REANOTACION
											)
											{
												TurnoHistoria lth_temp;
												lth_temp = new TurnoHistoria();

												lth_temp.setIdTurnoHistoria(
												    NumericUtils.getLongWrapper(lth_tmp.getIdAnterior())
												);

												lth_temp = lthd_DAO.findById(lth_temp);

												if(lth_temp != null)
													lotc_tmp.setObservaciones(lth_temp.getObservaciones());
											}
											else if(ll_idEtapa == EtapaCommon.ID_ETAPA_460)
											{
												Object[] loa_args;
												Calendar lc_calendar = Calendar.getInstance();

												loa_args = new String[4];
												lc_calendar.setTime(lth_tmp.getFechaFinal());

												int li_mes;

												li_mes     = lc_calendar.get(Calendar.MONTH);

												loa_args[0]     = lotc_tmp.getNir();
												loa_args[1]     = StringUtils.getString(
													    lc_calendar.get(Calendar.DAY_OF_MONTH)
													);
												loa_args[2]     = DateUtils.getMes(li_mes + 1);
												loa_args[3]     = StringUtils.getString(lc_calendar.get(Calendar.YEAR));

												lotc_tmp.setObservaciones(
												    addMessage(
												        MessagesKeys.OBSERVACIONES_PROCESO_ANTERIOR_SEGUNDA_INSTANCIA,
												        loa_args, false
												    )
												);
											}
											else
												lotc_tmp.setObservaciones(lth_tmp.getObservaciones());
										}

										{
											FechaVenceTerminosUI lfvtui_fechaVencimiento;
											long                 ll_tiempoVencimiento;

											lfvtui_fechaVencimiento = new FechaVenceTerminosUI();

											lfvtui_fechaVencimiento.setFechaInicial(
											    lth_turnoHistoria.getFechaCreacion()
											);
											lfvtui_fechaVencimiento.setFechaFinal(
											    lth_turnoHistoria.getFechaVencimiento()
											);
											lfvtui_fechaVencimiento.setTipoCalendario(EstadoCommon.H);
											lfvtui_fechaVencimiento.setIdCirculo(
											    lth_turnoHistoria.getIdCirculoUsuario()
											);

											lotc_tmp.setFechaCreacion(lth_turnoHistoria.getFechaCreacion());
											lotc_tmp.setFechaVencimiento(lth_turnoHistoria.getFechaVencimiento());

											ll_tiempoVencimiento = DaoCreator.getUtilDAO(ldm_manager)
													                             .funcionCalcularDiasFechas(
													    lfvtui_fechaVencimiento
													);

											lotc_tmp.setTiempoVencimiento(ll_tiempoVencimiento);

											if(ll_tiempoVencimiento <= 0)
											{
												lotc_tmp.setRojoTiempoVencimiento(true);

												if(
												    NumericUtils.getLong(lth_turnoHistoria.getIdEtapa()) == EtapaCommon.ID_ETAPA_ANALISIS_DEVOLUCIONES_DE_DINERO
												)
												{
													lotc_tmp.setObservacionesRespuesta(
													    getMessages()
														        .getString(MessagesKeys.SOLICITUD_SE_ENCUENTRA_VENCIDA)
													);

													TurnoHistoria lth_turnoHistoriaTmp;

													lth_turnoHistoriaTmp = DaoCreator.getTurnoHistoriaDAO(ldm_manager)
															                             .findById(
															    NumericUtils.getLongWrapper(
															        lotc_tmp.getIdTurnoHistoria()
															    )
															);

													lth_turnoHistoriaTmp.setObservacionesNoTramite(
													    lotc_tmp.getObservacionesRespuesta()
													);

													DaoCreator.getTurnoHistoriaDAO(ldm_manager)
														          .insertOrUpdate(lth_turnoHistoriaTmp, false);
												}
											}
										}
									}
								}

								{
									String ls_idTurno;
									ls_idTurno = lotc_tmp.getIdTurno();

									if(StringUtils.isValidString(ls_idTurno))
									{
										lca_turnosDerivados = lthd_DAO.findTurnosDerivados(
											    ls_idTurno, lb_indVinculado, false
											);

										if(CollectionUtils.isValidCollection(lca_turnosDerivados))
										{
											StringBuilder lsb_tmp;
											lsb_tmp = new StringBuilder();

											for(Aprobacion loa_tmp : lca_turnosDerivados)
												lsb_tmp = lsb_tmp.append(loa_tmp.getTurnosAsociados() + ", ");

											if(StringUtils.isValidString(lsb_tmp.toString()))
												lotc_tmp.setTurnosAsociados(lsb_tmp.toString());
										}

										if(lb_indVinculado)
										{
											lca_turnosDerivados = lthd_DAO.findTurnosDerivados(
												    ls_idTurno, lb_indVinculado, true
												);

											if(CollectionUtils.isValidCollection(lca_turnosDerivados))
											{
												StringBuilder lsb_tmp;
												StringBuilder lsb_tmp_relacionados;
												lsb_tmp                  = new StringBuilder();
												lsb_tmp_relacionados     = new StringBuilder();

												for(Aprobacion loa_tmp : lca_turnosDerivados)
												{
													String ls_idProcesoHijo;
													ls_idProcesoHijo = loa_tmp.getIdProceso();

													if(ls_idProcesoHijo.equalsIgnoreCase(ProcesoCommon.ID_SUBPROCESO_1))
														lsb_tmp = lsb_tmp.append(loa_tmp.getTurnosAsociados() + ", ");
													else if(
													    ls_idProcesoHijo.equalsIgnoreCase(
														        ProcesoCommon.ID_SUBPROCESO_6
														    )
													)
														lsb_tmp_relacionados = lsb_tmp_relacionados.append(
															    loa_tmp.getTurnosAsociados() + ", "
															);
												}

												if(StringUtils.isValidString(lsb_tmp.toString()))
													lotc_tmp.setTurnosAsociados(lsb_tmp.toString());

												if(StringUtils.isValidString(lsb_tmp_relacionados.toString()))
													lotc_tmp.setTurnosRelacionados(lsb_tmp_relacionados.toString());
											}
										}
									}
								}

								lc_dataFinal.add(lotc_tmp);
							}
						}

						ltc_return.setTurnos(lc_dataFinal);
					}
					else
					{
						Acto                        la_acto;
						Collection<TramiteCantidad> lctc_turnos;
						Collection<TurnoHistoria>   lcth_th;

						la_acto         = null;
						lctc_turnos     = new ArrayList<TramiteCantidad>();
						lcth_th         = new ArrayList<TurnoHistoria>(0);

						for(TramiteCantidad lotc_tmp : lctc_ctc)
						{
							if((lotc_tmp != null) && (NumericUtils.getLong(lotc_tmp.getIdMotivo()) != 1))
								lctc_turnos.add(lotc_tmp);
						}

						lcth_th = lthd_DAO.findAllByIdTurno(at_turno.getIdTurno());

						if(CollectionUtils.isValidCollection(lcth_th))
						{
							String ls_solicitud;
							String ls_tipoActo;

							ls_solicitud     = lcth_th.iterator().next().getIdSolicitud();
							ls_tipoActo      = IdentificadoresCommon.NUM0463;

							la_acto = DaoCreator.getActoDAO(ldm_manager)
									                .findBySolicitudTipoActo(ls_solicitud, ls_tipoActo);
						}

						if(la_acto == null)
							ltc_return.setTurnos(lctc_turnos);

						ltc_return.setTurnosDesistimiento(
						    lthd_DAO.bandejaCalificacionRecepcionDocumentos(at_turno, 1L)
						);
					}
				}

				if(!CollectionUtils.isValidCollection(lctc_ctc))
					ltc_return = new TramiteCantidad();

				ltc_return.setTurnosVinculados(lthd_DAO.turnosVinculados(at_turno, al_idMotivo));

				ltc_return.setTurnosProhibicion(lthd_DAO.turnosProhibiciones(at_turno, al_idMotivo));

				{
					long ll_idEtapaActual;

					ll_idEtapaActual = NumericUtils.getLong(at_turno.getIdEtapaActual());

					if(
					    (ll_idEtapaActual == EtapaCommon.ID_ETAPA_CALIFICACION)
						    || (ll_idEtapaActual == EtapaCommon.ID_ETAPA_CORRECCIONES)
						    || (ll_idEtapaActual == EtapaCommon.ID_ETAPA_RESPONSABLE_ACTUACIONES_ADMINISTRATIVAS)
						    || (ll_idEtapaActual == EtapaCommon.ID_ETAPA_SUSTANCIADOR_ACTUACIONES_ADMINISTRATIVAS)
						    //						    || (ll_idEtapaActual == EtapaCommon.ID_ETAPA_RESOLUCION_CREACION_SUPRESION_MODIFICACION)
						    || (lb_resolucionRecurso)
					)
						ltc_return.setTurnosSuspendidos(lthd_DAO.bandejaCalificacionSuspendidos(at_turno, al_idMotivo));
				}
			}
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

		return ltc_return;
	}

	/**
	 * Método encargado de buscar intervinientes.
	 *
	 * @param al_idAnotacion de al id anotacion
	 * @param as_circulo de as circulo
	 * @param al_matricula de al matricula
	 * @return el valor de collection
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Collection<Anotacion> findIntervinientes(Long al_idAnotacion, String as_circulo, Long al_matricula)
	    throws B2BException
	{
		DAOManager            ldm_manager;
		Collection<Anotacion> ca_return;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		ca_return       = new ArrayList<Anotacion>();

		try
		{
			if(
			    NumericUtils.isValidLong(al_idAnotacion) && StringUtils.isValidString(as_circulo)
				    && NumericUtils.isValidLong(al_matricula)
			)
			{
				ca_return = cargarIntervinientes(
					    as_circulo, NumericUtils.getLong(al_matricula), NumericUtils.getLong(al_idAnotacion), false,
					    ldm_manager
					);

				if(ca_return == null)
					throw new B2BException(ErrorKeys.SIN_INTERVINIENTES_PARA_ANOTACION_SELECCIONADA);
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findIntervinientes", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return ca_return;
	}

	/**
	 * Método encargado de retornar las matriculas asociadas a una solicitud.
	 *
	 * @param as_idTurno Objeto de tipo String quer contiene IdTurno a consultar
	 * @param as_userId Variable de tipo String que contiene el usuario que realiza la acción.
	 * @param as_localIp Variable de tipo String que contiene la Ip local del servidor que realiza la acción.
	 * @param as_remoteIp Variable de tipo String que contiene la Ip remota del usuario que realiza la acción.
	 * @return el valor de collection
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public synchronized Collection<SolicitudMatricula> findMatriculasByIdSolicitud(
	    String as_idTurno, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		DAOManager                     ldm_manager;
		Collection<SolicitudMatricula> lcsm_return;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		lcsm_return     = null;

		try
		{
			lcsm_return = findMatriculasByIdSolicitud(as_idTurno, as_userId, as_localIp, as_remoteIp, ldm_manager);
		}

		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();
			clh_LOGGER.error("findMatriculasByIdSolicitud", lb2be_e);
			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lcsm_return;
	}

	/**
	 * Método encargado de consultar las observaciones para id turno historia determinado.
	 *
	 * @param as_idTurnoHistoria Variable de tipo String que contiene un id turno historia determinado.
	 * @return Retorna  una variable de tipo String que coincide con los parametros de entrada.
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public synchronized String findObservacionesByIdTurnoHistoria(String as_idTurnoHistoria)
	    throws B2BException
	{
		DAOManager ldm_manager;
		String     ls_data;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		ls_data         = null;

		try
		{
			Collection<Map<String, Object>> lm_result      = null;
			Map<Integer, Object>            lhmp_criterios;

			lhmp_criterios                                 = new LinkedHashMap<Integer, Object>();
			lhmp_criterios.put(new Integer(1), as_idTurnoHistoria);

			lm_result = DaoCreator.getUtilDAO(ldm_manager)
					                  .getCustonQuery(
					    ConsultasUtilidades.CS_OBSERVACIONES_PROCESO_ANTE, lhmp_criterios
					);

			if(CollectionUtils.isValidCollection(lm_result))
			{
				Iterator<Map<String, Object>> limso_iterador;

				limso_iterador = lm_result.iterator();

				if(limso_iterador.hasNext())
				{
					Map<String, Object> lmso_iterator;

					lmso_iterator = limso_iterador.next();

					if(lmso_iterator != null)
					{
						{
							Object lo_inicio;
							lo_inicio = lmso_iterator.get(IdentificadoresCommon.OBSERVACIONES);

							if((lo_inicio != null) && lo_inicio instanceof String)
								ls_data = ((String)lo_inicio).toString();
						}
					}
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findObservacionesByIdTurnoHistoria", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return ls_data;
	}

	/**
	 * Método para buscar los predio registro asociados a una solicitud del turno  historia.
	 *
	 * @param as_idTurnoHistoria de as id turno historia
	 * @return el valor de collection
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public synchronized Collection<PredioRegistro> findPredioRegistro(String as_idTurnoHistoria)
	    throws B2BException
	{
		DAOManager                 ldm_manager;
		Collection<PredioRegistro> lcpr_return;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		lcpr_return     = new ArrayList<PredioRegistro>();

		try
		{
			if(StringUtils.isValidString(as_idTurnoHistoria))
			{
				TurnoHistoria lth_turnoHistoria;
				lth_turnoHistoria = DaoCreator.getTurnoHistoriaDAO(ldm_manager)
						                          .findById(NumericUtils.getLongWrapper(as_idTurnoHistoria));

				if(lth_turnoHistoria != null)
				{
					Collection<SolicitudMatricula> lsm_solicitudMatriculas;
					lsm_solicitudMatriculas = DaoCreator.getSolicitudMatriculaDAO(ldm_manager)
							                                .findByIdSolicitud(
							    lth_turnoHistoria.getIdSolicitud(), true
							);

					if(CollectionUtils.isValidCollection(lsm_solicitudMatriculas))
					{
						for(SolicitudMatricula lsm_tmp : lsm_solicitudMatriculas)
						{
							if(lsm_tmp != null)
							{
								PredioRegistro lpr_predioRegistro;
								lpr_predioRegistro = DaoCreator.getPredioRegistroDAO(ldm_manager)
										                           .findByCirculoMatricula(
										    lsm_tmp.getIdCirculo(), lsm_tmp.getIdMatricula()
										);

								if(lpr_predioRegistro != null)
									lcpr_return.add(lpr_predioRegistro);
							}
						}
					}
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findPredioRegistro", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lcpr_return;
	}

	/**
	 * Consulta en base de datos un registro de datos ant sistema por su id.
	 *
	 * @param as_idTurno Objeto contenedor del id a utilizar como filtro en la consulta
	 * @return datos ant sistema resultante de la consulta
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public synchronized RegistroPagoExceso findRegistroPagoExcesoByTurno(String as_idTurno)
	    throws B2BException
	{
		DAOManager         ldm_manager;
		RegistroPagoExceso lrpe_pagoExceso;

		ldm_manager         = DaoManagerFactory.getDAOManager();
		lrpe_pagoExceso     = null;

		try
		{
			if(StringUtils.isValidString(as_idTurno))
				lrpe_pagoExceso = DaoCreator.getRegistroPagoExcesoDAO(ldm_manager).findByIdTurno(as_idTurno);
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findRegistroPagoExcesoByTurno", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lrpe_pagoExceso;
	}

	/**
	 * Metodo encargardo de consultar una solicitud por turno.
	 *
	 * @param as_idTurno Argumento de tipo String que contiene el turno para realizar la consulta.
	 * @return Objeto de tipo Solicitud que contiene los valores que coinciden con los criterios de búsqueda.
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public synchronized Solicitud findSolicitudByIdTurno(String as_idTurno)
	    throws B2BException
	{
		Solicitud  ls_solicitud;
		DAOManager ldm_manager;

		ldm_manager      = DaoManagerFactory.getDAOManager();
		ls_solicitud     = null;

		try
		{
			if(!StringUtils.isValidString(as_idTurno))
				throw new B2BException(ErrorKeys.ERROR_SIN_ID_TURNO);

			{
				Turno lt_turno;

				lt_turno = new Turno();
				lt_turno.setIdTurno(as_idTurno);

				lt_turno = DaoCreator.getTurnoDAO(ldm_manager).findById(lt_turno);

				if(lt_turno != null)
				{
					ls_solicitud = new Solicitud();
					ls_solicitud.setIdSolicitud(lt_turno.getIdSolicitud());

					ls_solicitud = DaoCreator.getSolicitudDAO(ldm_manager).findById(ls_solicitud);

					if(ls_solicitud == null)
						throw new B2BException(ErrorKeys.ERROR_SIN_ID_SOLICITUD);
				}
				else
					throw new B2BException(ErrorKeys.ERROR_SIN_ID_TURNO);
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findSolicitudByIdTurno", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return ls_solicitud;
	}

	/**
	 * Método encargado de consultar el círculo asociado a un id turno determinado.
	 *
	 * @param ath_idTurnoHistoria de ath id turno historia
	 * @return Retorna  una variable de tipo String que coincide con los parametros de entrada.
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public synchronized Solicitud findSolicitudByTH(TurnoHistoria ath_idTurnoHistoria)
	    throws B2BException
	{
		DAOManager ldm_manager;
		Solicitud  ls_data;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		ls_data         = null;

		try
		{
			if(ath_idTurnoHistoria != null)
			{
				TurnoHistoria lth_temp;
				lth_temp = DaoCreator.getTurnoHistoriaDAO(ldm_manager).findById(ath_idTurnoHistoria);

				if(lth_temp != null)
				{
					Solicitud ls_solicitud;
					ls_solicitud = new Solicitud();

					ls_solicitud.setIdSolicitud(lth_temp.getIdSolicitud());

					ls_data = DaoCreator.getSolicitudDAO(ldm_manager).findById(ls_solicitud);
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findSolicitudByTH", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return ls_data;
	}

	/**
	 * Método encargado de consultar las solicitudes asociadas a una matrícula por un id solicitud determinado.
	 *
	 * @param at_param Objeto que contiene los datos necesarios para ejecutar la sentencia select sobre la tabla SDB_ACC_SOLICITUD_MATRICULA.
	 * @return Retorna  una colección de datos de tipo SolicitudMatricula que coincide con los parametros de entrada.
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public synchronized Collection<SolicitudMatricula> findSolicitudMatricula(SolicitudMatricula at_param)
	    throws B2BException
	{
		DAOManager                     ldm_manager;
		Collection<SolicitudMatricula> ldp_datos;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		ldp_datos       = null;

		try
		{
			ldp_datos = DaoCreator.getSolicitudMatriculaDAO(ldm_manager).findByIdSolicitud(at_param, true);
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findSolicitudMatricula", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return ldp_datos;
	}

	/**
	 * Método encargado de consultar la relación de  las  solicitudes con matrícula y acto  para un id solicitud y matrícula determinado.
	 *
	 * @param at_param Objeto que contiene los datos necesarios para ejecutar la sentencia select sobre la tabla SDB_ACC_SOLICITUD_MATRICULA_ACTO.
	 * @return Retorna  una colección de datos de tipo SolicitudMatriculaActo que coincide con los parametros de entrada.
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public synchronized Collection<SolicitudMatriculaActo> findSolicitudMatriculaActo(SolicitudMatriculaActo at_param)
	    throws B2BException
	{
		DAOManager                         ldm_manager;
		Collection<SolicitudMatriculaActo> ldp_datos;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		ldp_datos       = null;

		try
		{
			ldp_datos = DaoCreator.getSolicitudMatriculaActoDAO(ldm_manager).findAllByIdSolicitud(at_param, true);
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findTmpSolicitudMatriculaActo", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return ldp_datos;
	}

	/**
	 * Método encargado de consultar la relación de  las  solicitudes con matrícula   para un id solicitud  determinado.
	 *
	 * @param at_param Objeto que contiene los datos necesarios para ejecutar la sentencia select sobre la tabla SDB_ACC_SOLICITUD_MATRICULA.
	 * @return Retorna  una colección de datos de tipo SolicitudMatricula que coincide con los parametros de entrada.
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public synchronized Collection<SolicitudMatricula> findSolicitudMatriculaEstado(SolicitudMatricula at_param)
	    throws B2BException
	{
		DAOManager                     ldm_manager;
		Collection<SolicitudMatricula> ldp_datos;
		Collection<SolicitudMatricula> ldp_return;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		ldp_datos       = null;
		ldp_return      = new ArrayList<SolicitudMatricula>();

		try
		{
			ldp_datos = DaoCreator.getSolicitudMatriculaDAO(ldm_manager).findByIdSolicitud(at_param);

			if(CollectionUtils.isValidCollection(ldp_datos))
			{
				String ls_idCirculoActual;

				ls_idCirculoActual = at_param.getIdCirculo();

				for(SolicitudMatricula lsm_actual : ldp_datos)
				{
					if(lsm_actual != null)
					{
						if(lsm_actual.getIdCirculo().contentEquals(ls_idCirculoActual))
						{
							PredioRegistro lpr_pr;

							lpr_pr = new PredioRegistro();
							lpr_pr.setIdCirculo(lsm_actual.getIdCirculo());
							lpr_pr.setIdMatricula(lsm_actual.getIdMatricula());

							lpr_pr = DaoCreator.getPredioRegistroDAO(ldm_manager).findByCirculoMatricula(lpr_pr);

							if(lpr_pr != null)
							{
								{
									DireccionPredio ldp_direccionPredio;

									ldp_direccionPredio = new DireccionPredio();

									ldp_direccionPredio.setIdCirculo(lpr_pr.getIdCirculo());
									ldp_direccionPredio.setIdMatricula(
									    NumericUtils.getLongWrapper(lpr_pr.getIdMatricula())
									);

									ldp_direccionPredio = DaoCreator.getDireccionPredioDAO(ldm_manager)
											                            .findById(ldp_direccionPredio);

									if(ldp_direccionPredio != null)
									{
										String ls_address;

										ls_address = ldp_direccionPredio.getDireccion();

										if(StringUtils.isValidString(ls_address))
											lsm_actual.setIdUsuarioCreacion(ls_address);
									}
								}

								{
									EstadoPredio lep_ep;

									lep_ep = new EstadoPredio();

									lep_ep.setIdEstadoPredio(lpr_pr.getIdEstadoPredio());

									lep_ep = DaoCreator.getEstadoPredioDao(ldm_manager).findById(lep_ep);

									if(lep_ep != null)
										lsm_actual.setEstado(lep_ep.getNombre());
								}
							}

							ldp_return.add(lsm_actual);
						}
					}
				}
			}

			if(!CollectionUtils.isValidCollection(ldp_return))
				ldp_return = null;
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findSolicitudMatricula", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return ldp_return;
	}

	/**
	 * Método encargado de  consultar los registros de la tabla SDB_ACC_SOLICITUD_MATRICULA con un id solicitud determinado.
	 *
	 * @param asm_sm Objeto de tipo SolicitudMatricula que contiene un id solicitud determinado.
	 * @return Retorna  una colección de datos de tipo SolicitudMatricula que coincide con los parametros de entrada.
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public synchronized Collection<SolicitudMatricula> findSolicitudMatriculaOrderCirculo(SolicitudMatricula asm_sm)
	    throws B2BException
	{
		DAOManager                     ldm_manager;
		Collection<SolicitudMatricula> ldp_datos;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		ldp_datos       = null;

		try
		{
			if(asm_sm != null)
			{
				SolicitudMatriculaDAO lsm_DAO;
				String                ls_idTurno;

				lsm_DAO        = DaoCreator.getSolicitudMatriculaDAO(ldm_manager);
				ls_idTurno     = asm_sm.getIdTurnoCertificado();

				if(StringUtils.isValidString(ls_idTurno))
				{
					Turno lt_turno;

					lt_turno = DaoCreator.getTurnoDAO(ldm_manager).findById(ls_idTurno);

					if(lt_turno != null)
					{
						asm_sm.setIdCirculo(lt_turno.getIdCirculo());

						ldp_datos = lsm_DAO.findByIdSolicitudOrderCirculo(asm_sm, true);
					}
				}
				else
					ldp_datos = lsm_DAO.findByIdSolicitudOrderCirculo(asm_sm, false);
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findSolicitudMatriculaOrderCirculo", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return ldp_datos;
	}

	/**
	 * Método encargado de consultar la relación de  las  solicitudes con matrícula   para un id solicitud  determinado.
	 *
	 * @param ath_turnoHistoria Objeto que contiene los datos necesarios para ejecutar la sentencia select sobre la tabla SDB_ACC_TURNO_HISTORIA.
	 * @return Retorna  una colección de datos de tipo SolicitudMatricula que coincide con los parametros de entrada.
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public synchronized Collection<SolicitudMatricula> findSolicitudMatriculasTH(TurnoHistoria ath_turnoHistoria)
	    throws B2BException
	{
		DAOManager                     ldm_manager;
		Collection<SolicitudMatricula> lcsm_matriculas;

		lcsm_matriculas     = null;
		ldm_manager         = DaoManagerFactory.getDAOManager();

		try
		{
			ath_turnoHistoria = DaoCreator.getTurnoHistoriaDAO(ldm_manager).findById(ath_turnoHistoria);

			if(ath_turnoHistoria != null)
			{
				String ls_solicitud;

				ls_solicitud = ath_turnoHistoria.getIdSolicitud();

				if(StringUtils.isValidString(ls_solicitud))
				{
					Turno lt_turno;

					lt_turno = DaoCreator.getTurnoDAO(ldm_manager).findById(ath_turnoHistoria.getIdTurno());

					if(lt_turno != null)
						lcsm_matriculas = DaoCreator.getSolicitudMatriculaDAO(ldm_manager)
								                        .findByIdSolicitudCirculo(
								    ls_solicitud, lt_turno.getIdCirculo()
								);
				}
				else
					throw new B2BException(ErrorKeys.NO_SE_ENCONTRO_SOLICITUD);
			}
			else
				throw new B2BException(ErrorKeys.NO_SE_ENCONTRO_TURNO_HISTORIA);
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findSolicitudMatriculasTH", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lcsm_matriculas;
	}

	/**
	 * Metodo para encontrar un tipo acto por id.
	 *
	 * @param as_idTipoActo de as id tipo acto
	 * @return lta_tipoActoReturn con la información consultada
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public TipoActo findTipoActoById(String as_idTipoActo)
	    throws B2BException
	{
		TipoActo   lta_tipoActoReturn;
		DAOManager ldm_manager;

		ldm_manager            = DaoManagerFactory.getDAOManager();
		lta_tipoActoReturn     = null;

		try
		{
			if(StringUtils.isValidString(as_idTipoActo))
				lta_tipoActoReturn = DaoCreator.getTipoActoDAO(ldm_manager).findTipoActoById(as_idTipoActo);
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

		return lta_tipoActoReturn;
	}

	/**
	 *  Método encargado de consultar la relación de  las  solicitudes con matrícula   temporales para un id solicitud  determinado.
	 *
	 * @param at_param Objeto que contiene los datos necesarios para ejecutar la sentencia select sobre la tabla  SDB_ACC_TMP_SOLICITUD_MATRICULA
	 * @return Retorna  una colección de datos de tipo TmpSolicitudMatricula que coincide con los parametros de entrada.
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public synchronized Collection<TmpSolicitudMatricula> findTmpSolicitudMatricula(TmpSolicitudMatricula at_param)
	    throws B2BException
	{
		DAOManager                        ldm_manager;
		Collection<TmpSolicitudMatricula> lctsm_datos;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		lctsm_datos     = null;

		try
		{
			lctsm_datos = DaoCreator.getTmpSolicitudMatriculaDAO(ldm_manager).findByIdSolicitud(at_param);

			if(CollectionUtils.isValidCollection(lctsm_datos))
			{
				for(TmpSolicitudMatricula ltsm_matriculaTMP : lctsm_datos)
				{
					if(ltsm_matriculaTMP != null)
					{
						PredioRegistro lpr_predioRegistro;

						lpr_predioRegistro = new PredioRegistro();

						lpr_predioRegistro.setIdMatricula(ltsm_matriculaTMP.getIdMatricula());
						lpr_predioRegistro.setIdCirculo(ltsm_matriculaTMP.getIdCirculo());

						lpr_predioRegistro = DaoCreator.getPredioRegistroDAO(ldm_manager).findById(lpr_predioRegistro);

						if(lpr_predioRegistro != null)
						{
							String ls_predioInconsistente;
							ls_predioInconsistente = StringUtils.getStringNotNull(
								    lpr_predioRegistro.getPredioInconsistente()
								);

							if(ls_predioInconsistente.equalsIgnoreCase(EstadoCommon.S))
								ltsm_matriculaTMP.setEsPredioInconsistente(true);
						}
					}
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findTmpSolicitudMatricula", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lctsm_datos;
	}

	/**
	 * Método encargado de consultar la relación de  las  solicitudes con matrícula y actos  temporales para un id solicitud y matrícula determinado.
	 *
	 * @param at_param Objeto que contiene los datos necesarios para ejecutar la sentencia select sobre la tabla  SDB_ACC_TMP_SOLICITUD_MATRICULA_ACTO
	 * @return Retorna  una colección de datos de tipo TmpSolicitudMatricula que coincide con los parametros de entrada.
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public synchronized Collection<TmpSolicitudMatriculaActo> findTmpSolicitudMatriculaActo(
	    TmpSolicitudMatriculaActo at_param
	)
	    throws B2BException
	{
		DAOManager                            ldm_manager;
		Collection<TmpSolicitudMatriculaActo> ldp_datos;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		ldp_datos       = null;

		try
		{
			ldp_datos = DaoCreator.getTmpSolicitudMatriculaActoDAO(ldm_manager).findAllByIdSolicitud(at_param);
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findTmpSolicitudMatriculaActo", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return ldp_datos;
	}

	/**
	 * Método encargado de consultar el detalle de un registro para un id turno historia y id etapa determinado.
	 *
	 * @param al_idTurnoHistoria Variable de tipo Long que contiene un id turno historia determinado.
	 * @param al_idEtapa Variable de tipo Long que contiene un id etapa determinado.
	 * @return Retorna  un objeto de tipo TurnoHistoria que coincide con los parametros de entrada.
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public synchronized TurnoHistoria findTurnoHistoria(Long al_idTurnoHistoria, Long al_idEtapa)
	    throws B2BException
	{
		DAOManager    ldm_manager;
		TurnoHistoria ldp_datos;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		ldp_datos       = null;

		try
		{
			ldp_datos = DaoCreator.getTurnoHistoriaDAO(ldm_manager).findDataTurn(al_idTurnoHistoria, al_idEtapa);
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findTurnoHistoria", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return ldp_datos;
	}

	/**
	 * Método encargado de consultar el turno historia anterior por su respectivo id_turno_historia.
	 *
	 * @param ath_param de ath param
	 * @return Retorna el objeto turno historia respectivo a la consulta.
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public synchronized TurnoHistoria findTurnoHistoriaAnteriorById(TurnoHistoria ath_param)
	    throws B2BException
	{
		DAOManager    ldm_manager;
		TurnoHistoria lth_datos;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		lth_datos       = null;

		try
		{
			TurnoHistoria lth_datosTMP;
			lth_datosTMP = DaoCreator.getTurnoHistoriaDAO(ldm_manager).findById(ath_param);

			if(lth_datosTMP != null)
			{
				String                         ls_matriculaMedidaCautelar;
				Collection<SolicitudMatricula> lcsm_solicitudesMatricula;

				ls_matriculaMedidaCautelar     = null;
				lcsm_solicitudesMatricula      = DaoCreator.getSolicitudMatriculaDAO(ldm_manager)
						                                       .findByIdSolicitudCirculo(
						    lth_datosTMP.getIdSolicitud(), lth_datosTMP.getIdCirculoUsuario()
						);

				if(CollectionUtils.isValidCollection(lcsm_solicitudesMatricula))
				{
					for(SolicitudMatricula lsm_solicitudMatricula : lcsm_solicitudesMatricula)
					{
						if(lsm_solicitudMatricula != null)
						{
							Collection<SolicitudMatriculaActo> lcsma_solicitudMatriculaActo;
							lcsma_solicitudMatriculaActo = DaoCreator.getSolicitudMatriculaActoDAO(ldm_manager)
									                                     .findByIdCirculoMatricula(
									    lsm_solicitudMatricula.getIdCirculo(), lsm_solicitudMatricula.getIdMatricula()
									);

							if(CollectionUtils.isValidCollection(lcsma_solicitudMatriculaActo))
							{
								for(SolicitudMatriculaActo lsma_tmp : lcsma_solicitudMatriculaActo)
								{
									if(lsma_tmp != null)
									{
										Solicitud ls_solicitudTMP;
										ls_solicitudTMP = DaoCreator.getSolicitudDAO(ldm_manager)
												                        .findById(lsma_tmp.getIdSolicitud());

										if(ls_solicitudTMP != null)
										{
											long ll_estadoSolicitud;
											ll_estadoSolicitud = NumericUtils.getLong(
												    ls_solicitudTMP.getEstadoSolicitud()
												);

											if(ll_estadoSolicitud == 1)
											{
												Collection<SolicitudMatriculaActo> lcsma_grupoNat = DaoCreator.getSolicitudMatriculaActoDAO(
													    ldm_manager
													).dataBySolicitud(lsma_tmp);

												if(CollectionUtils.isValidCollection(lcsma_grupoNat))
												{
													for(SolicitudMatriculaActo lsma_matriculaActo : lcsma_grupoNat)
													{
														String ls_grupoNat;

														ls_grupoNat = StringUtils.getStringNotNull(
															    lsma_matriculaActo.getIdGrupoJuridica()
															);

														if(ls_grupoNat.equalsIgnoreCase("0400"))
															ls_matriculaMedidaCautelar = lsma_tmp.getIdCirculo()
																+ IdentificadoresCommon.SIMBOLO_GUION
																+ lsma_tmp.getIdMatricula();
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

				lth_datos = DaoCreator.getTurnoHistoriaDAO(ldm_manager).findByIdAnterior(lth_datosTMP);

				if(StringUtils.isValidString(ls_matriculaMedidaCautelar))
				{
					Object[] aoa_messageArgs;

					aoa_messageArgs     = new String[1];

					aoa_messageArgs[0] = ls_matriculaMedidaCautelar;

					lth_datos.setObservacionesProcesoAnterior(
					    getMessages().getString(MessagesKeys.MATRICULA_MEDIDA_CAUTELAR_VIGENTE, aoa_messageArgs)
					);
				}
				else if(lth_datos != null)
				{
					long  ll_idEtapa;
					long  ll_idMotivo;
					Etapa le_etapa;

					ll_idEtapa      = NumericUtils.getLong(lth_datos.getIdEtapa());
					ll_idMotivo     = NumericUtils.getLong(lth_datos.getIdMotivo());

					le_etapa = DaoCreator.getEtapaDAO(ldm_manager).findById(ll_idEtapa);

					if(le_etapa != null)
						lth_datos.setNombreEtapa(le_etapa.getNombre());

					if(
					    (ll_idEtapa != 0)
						    && ((ll_idEtapa == EtapaCommon.ID_ETAPA_11) || (ll_idEtapa == EtapaCommon.ID_ETAPA_15)
						    || (ll_idEtapa == EtapaCommon.ID_ETAPA_18) || (ll_idEtapa == EtapaCommon.ID_ETAPA_20)
						    || ((ll_idMotivo != 0) && (ll_idMotivo == MotivoTramiteCommon.PREDIO_INCONSISTENTE)))
					)
					{
						TurnoHistoria lth_datosTMP2;
						lth_datosTMP2 = new TurnoHistoria();

						lth_datosTMP2.setIdSolicitud(lth_datosTMP.getIdSolicitud());

						if(ll_idMotivo == MotivoTramiteCommon.PREDIO_INCONSISTENTE)
						{
							if(
							    (ll_idEtapa == EtapaCommon.ID_ETAPA_ANALISTA_CERTIFICADOS_ESPECIALES)
								    || (ll_idEtapa == EtapaCommon.ID_ETAPA_RESPONSABLE_ACTUACIONES_ADMINISTRATIVAS)
							)
							{
								String ls_mensaje;
								ls_mensaje = lth_datos.getObservaciones();

								if(StringUtils.isValidString(ls_mensaje))
									lth_datosTMP2.setIdEtapa(NumericUtils.getBigDecimal(ll_idEtapa));
								else
								{
									TurnoHistoria lth_tmp;
									lth_tmp = DaoCreator.getTurnoHistoriaDAO(ldm_manager).findByIdAnterior(lth_datos);

									if(lth_tmp != null)
									{
										long ll_idEtapa2;

										ll_idEtapa2 = NumericUtils.getLong(lth_tmp.getIdEtapa());

										if(ll_idEtapa2 == EtapaCommon.ID_ETAPA_SUSTANCIADOR_ACTUACIONES_ADMINISTRATIVAS)
											lth_datosTMP2.setIdEtapa(NumericUtils.getBigDecimal(ll_idEtapa2));
										else
											lth_datosTMP2.setIdEtapa(
											    NumericUtils.getBigDecimal(EtapaCommon.ID_ETAPA_10)
											);
									}
								}
							}
							else
							{
								TurnoHistoria lth_tmp;
								lth_tmp = DaoCreator.getTurnoHistoriaDAO(ldm_manager).findByIdAnterior(lth_datos);

								if(lth_tmp != null)
								{
									long ll_idEtapa2;

									ll_idEtapa2 = NumericUtils.getLong(lth_tmp.getIdEtapa());

									if(ll_idEtapa2 == EtapaCommon.ID_ETAPA_APROBACION_ANTIGUO_SISTEMA)
										lth_datosTMP2.setIdEtapa(NumericUtils.getBigDecimal(ll_idEtapa2));
									else if(ll_idEtapa2 == EtapaCommon.ID_ETAPA_ANALISTA_CERTIFICADOS_ESPECIALES)
										lth_datosTMP2.setIdEtapa(NumericUtils.getBigDecimal(ll_idEtapa2));
									else if(
									    ll_idEtapa2 == EtapaCommon.ID_ETAPA_SUSTANCIADOR_ACTUACIONES_ADMINISTRATIVAS
									)
										lth_datosTMP2.setIdEtapa(NumericUtils.getBigDecimal(ll_idEtapa2));
									else if(ll_idEtapa2 == EtapaCommon.ID_ETAPA_CORRECCIONES)
										lth_datosTMP2.setIdEtapa(NumericUtils.getBigDecimal(ll_idEtapa2));
									else
										lth_datosTMP2.setIdEtapa(NumericUtils.getBigDecimal(EtapaCommon.ID_ETAPA_10));
								}
							}
						}
						else
							lth_datosTMP2.setIdEtapa(NumericUtils.getBigDecimal(EtapaCommon.ID_ETAPA_10));

						lth_datosTMP2 = DaoCreator.getTurnoHistoriaDAO(ldm_manager).findBySolicitudEtapa(lth_datosTMP2);

						if(lth_datosTMP2 != null)
							lth_datos.setObservacionesProcesoAnterior(lth_datosTMP2.getObservaciones());
					}
					else
						lth_datos.setObservacionesProcesoAnterior(lth_datos.getObservaciones());
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findTurnoHistoriaAnteriorById", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lth_datos;
	}

	/**
	 * Método encargado de consultar un turno historia por su respectivo id_turno_historia.
	 *
	 * @param ath_param de ath param
	 * @return Retorna el objeto turno historia respectivo a la consulta.
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public synchronized TurnoHistoria findTurnoHistoriaById(TurnoHistoria ath_param)
	    throws B2BException
	{
		DAOManager    ldm_manager;
		TurnoHistoria lth_datos;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		lth_datos       = null;

		try
		{
			lth_datos = DaoCreator.getTurnoHistoriaDAO(ldm_manager).findById(ath_param);
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

		return lth_datos;
	}

	/**
	 * Método encargado de consultar la cantidad de turnos para un usuario en un una etapa y estado activadad determinado.
	 *
	 * @param as_estado Variable de tipo String que contiene un estado determinado.
	 * @param as_idUsuario Variable de tipo String que contiene el usuario que realiza la acción.
	 * @param al_idEtapa Variable de tipo long que contiene un  id etapa determinado.
	 * @param ab_allUsuarios de ab all usuarios
	 * @return Retorna  una variable de tipo Integer que coincide con los parametros de entrada.
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public synchronized Integer findTurnosCantidad(
	    String as_estado, String as_idUsuario, long al_idEtapa, boolean ab_allUsuarios
	)
	    throws B2BException
	{
		DAOManager       ldm_manager;
		TurnoHistoriaDAO lthd_turnoHistoriaDAO;
		Integer          li_dato;

		ldm_manager               = DaoManagerFactory.getDAOManager();
		lthd_turnoHistoriaDAO     = DaoCreator.getTurnoHistoriaDAO(ldm_manager);
		li_dato                   = null;

		try
		{
			if(!ab_allUsuarios)
				li_dato = lthd_turnoHistoriaDAO.findTurnosCantidad(as_estado, as_idUsuario, al_idEtapa, null);
			else
				li_dato = lthd_turnoHistoriaDAO.findTurnosCantidadForAllUsers(as_estado, al_idEtapa);
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findTurnosCantidad", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return li_dato;
	}

	/**
	 * Método encargado de validar si el tipo acto gener segregación o es de apertura.
	 *
	 * @param aps_predio Objeto que contiene los datos del predio a validar.
	 * @param ab_accion Variable boolean que indica si el predio es segregado o con base en.
	 * @return Variable de tipo boolean que indica si el tipo acto genera segregación o es de apertura.
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public boolean generaSegregacionOApertura(PredioSegregado aps_predio, boolean ab_accion)
	    throws B2BException
	{
		DAOManager ldm_manager;
		boolean    lb_return;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		lb_return       = false;

		try
		{
			if(aps_predio != null)
			{
				Long   ll_idAnotacion;
				Long   ll_idMatricula;
				String ls_idCirculo;

				ll_idAnotacion     = ab_accion ? aps_predio.getIdAnotacion() : aps_predio.getIdAnotacion1();
				ll_idMatricula     = ab_accion ? aps_predio.getIdMatricula() : aps_predio.getIdMatricula1();
				ls_idCirculo       = ab_accion ? aps_predio.getIdCirculo() : aps_predio.getIdCirculo1();

				if(
				    NumericUtils.isValidLong(ll_idAnotacion) && NumericUtils.isValidLong(ll_idMatricula)
					    && StringUtils.isValidString(ls_idCirculo)
				)
				{
					AnotacionPredio                                    lap_anotacionPredio;
					com.bachue.snr.prosnr01.dao.bng.AnotacionPredioDAO lap_DAO;

					lap_anotacionPredio     = null;
					lap_DAO                 = DaoCreator.getAnotacionPredioDAO(ldm_manager);

					if(aps_predio.isAnotacionesAcc())
						lap_anotacionPredio = lap_DAO.findByIdAcc(ls_idCirculo, ll_idMatricula, ll_idAnotacion);
					else
						lap_anotacionPredio = lap_DAO.findById(ls_idCirculo, ll_idMatricula, ll_idAnotacion);

					if(lap_anotacionPredio != null)
						lb_return = generaSegregacionOApertura(
							    ldm_manager, lap_anotacionPredio.getIdNaturalezaJuridica()
							);
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("generaSegregacionOApertura", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lb_return;
	}

	/**
	 * Construye el formulario de correcciones.
	 *
	 * @param as_idTurnoHistoria id turno historia del tramite en proceso
	 * @param as_userId id del usuario que ejecuta la acción
	 * @param as_remoteIp de as remote ip
	 * @return arreglo de bytes correspondiente al documento generado
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public synchronized byte[] generarFormularioCorrecciones(
	    String as_idTurnoHistoria, String as_userId, String as_remoteIp
	)
	    throws B2BException
	{
		DAOManager ldm_manager;
		byte[]     lba_return;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		lba_return      = null;

		try
		{
			lba_return = generarFormularioCorrecciones(as_idTurnoHistoria, as_userId, as_remoteIp, ldm_manager, false);
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();
			clh_LOGGER.error("generarFormularioCorrecciones", lb2be_e);
			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lba_return;
	}

	/**
	 * Método encargado de generar archivo en formato pdf  utilizando  las plantillas  PLANTILLA_NOTA_DEVOLUTIVA_PARCIAL y PLANTILLA_NOTA_DEVOLUTIVA.
	 *
	 * @param atc_dataCausal Objeto de tipo TipoCausal que contiene los datos necesarios para buscar la información a incluir en la plantilla.
	 * @param arpc_dataRegistroParcial Objeto de tipo RegistroParcialCalificacion que contiene los datos necesarios para buscar la información a incluir en la plantilla.
	 * @param ab_firmaMasiva Variable de tipo boolean , si es true inserta firma masiva en la plantilla de lo contrario no lo hace.
	 * @param as_userId de as user id
	 * @param as_remoteIp de as remote ip
	 * @return Arreglo de bytes que contiene la plantilla generada.
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public synchronized byte[] generarNotaDevolutiva(
	    TipoCausal atc_dataCausal, RegistroParcialCalificacion arpc_dataRegistroParcial, boolean ab_firmaMasiva,
	    String as_userId, String as_remoteIp
	)
	    throws B2BException
	{
		byte[]     lba_notaDevolutiva;
		DAOManager ldm_manager;

		lba_notaDevolutiva     = null;
		ldm_manager            = DaoManagerFactory.getDAOManager();

		try
		{
			lba_notaDevolutiva = generarNotaDevolutiva(
				    atc_dataCausal, arpc_dataRegistroParcial, ab_firmaMasiva, as_userId, as_remoteIp, ldm_manager
				);
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("generarNotaDevolutiva", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lba_notaDevolutiva;
	}

	/**
	 * Método encargado de generar archivo en formato pdf  utilizando  las plantillas  PLANTILLA_NOTA_DEVOLUTIVA_PARCIAL y PLANTILLA_NOTA_DEVOLUTIVA.
	 *
	 * @param atc_dataCausal Objeto de tipo TipoCausal que contiene los datos necesarios para buscar la información a incluir en la plantilla.
	 * @param arpc_dataRegistroParcial Objeto de tipo RegistroParcialCalificacion que contiene los datos necesarios para buscar la información a incluir en la plantilla.
	 * @param ab_firmaMasiva Variable de tipo boolean , si es true inserta firma masiva en la plantilla de lo contrario no lo hace.
	 * @param as_userId de as user id
	 * @param as_remoteIp de as remote ip
	 * @param adm_manager Objeto de tipo DAOManager que contiene la tansaccion activa con la base de datos.
	 * @return Arreglo de bytes que contiene la plantilla generada.
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public synchronized byte[] generarNotaDevolutiva(
	    TipoCausal atc_dataCausal, RegistroParcialCalificacion arpc_dataRegistroParcial, boolean ab_firmaMasiva,
	    String as_userId, String as_remoteIp, DAOManager adm_manager
	)
	    throws B2BException
	{
		byte[] lba_notaDevolutiva;

		lba_notaDevolutiva = null;

		try
		{
			if(atc_dataCausal != null)
			{
				byte[]  lba_plantilla;
				boolean lb_accion;

				lb_accion         = arpc_dataRegistroParcial.isRegistroParcial();
				lba_plantilla     = DaoCreator.getConstantesDAO(adm_manager)
						                          .findImagenes(
						    lb_accion ? ConstanteCommon.PLANTILLA_NOTA_DEVOLUTIVA_PARCIAL
						                  : ConstanteCommon.PLANTILLA_NOTA_DEVOLUTIVA
						);

				if(lba_plantilla != null)
				{
					String ls_plantilla;

					ls_plantilla = new String(lba_plantilla);

					if(StringUtils.isValidString(ls_plantilla))
					{
						Map<String, String> lmss_datos;
						Long                ll_idTurnoHistoria;
						String              ls_turno;
						TurnoHistoria       lth_detalle;
						TurnoHistoriaDAO    lth_DAO;

						lmss_datos             = null;
						ll_idTurnoHistoria     = null;
						ls_turno               = atc_dataCausal.getIdTurno();
						lth_detalle            = new TurnoHistoria();
						lth_DAO                = DaoCreator.getTurnoHistoriaDAO(adm_manager);

						if(atc_dataCausal.isIndVinculacion())
						{
							lth_detalle.setIdTurno(ls_turno);

							ls_turno        = lth_detalle.getIdTurno();
							lth_detalle     = lth_DAO.findByIdTurno(lth_detalle, true);

							if(lth_detalle != null)
								ll_idTurnoHistoria = lth_detalle.getIdTurnoHistoria();
						}
						else
							ll_idTurnoHistoria = NumericUtils.getLongWrapper(atc_dataCausal.getIdTurnoHistoria());

						if(NumericUtils.isValidLong(ll_idTurnoHistoria))
						{
							lth_detalle.setIdTurnoHistoria(ll_idTurnoHistoria);

							lth_detalle = lth_DAO.findById(lth_detalle);

							if(lth_detalle != null)
							{
								TurnoHistoria loth_dataFile;

								ls_turno          = lth_detalle.getIdTurno();
								loth_dataFile     = DaoCreator.getNotaDevolutivaDAO(adm_manager)
										                          .dataNotaDevolutiva(ls_turno);

								if(loth_dataFile != null)
								{
									Collection<TipoCausal> lctc_causales;
									Date                   ld_fechaActual;
									DateFormat             lsf_dateFormat;
									DateFormat             lsf_formatTime;
									String                 ls_fecha;
									String                 ls_hora;
									String                 ls_turnoDerivado;
									StringBuilder          lsb_causales;

									lctc_causales      = atc_dataCausal.getListTiposCausales();
									ld_fechaActual     = new Date();
									lsf_dateFormat     = new SimpleDateFormat(FormatoFechaCommon.DIA_MES_ANIO);
									lsf_formatTime     = new SimpleDateFormat("HH:mm:ss");
									ls_fecha           = lsf_dateFormat.format(ld_fechaActual);
									ls_hora            = lsf_formatTime.format(ld_fechaActual);
									lsb_causales       = new StringBuilder();

									if(CollectionUtils.isValidCollection(lctc_causales))
									{
										for(TipoCausal lotc_otc : lctc_causales)
										{
											if(lotc_otc.isSeleccionado())
												lsb_causales.append(
												    "{\\pard\\s3\\f1\\sb200\\cf1" + "- " + lotc_otc.getNombre()
												    + "\\par}"
												);
										}
									}

									if(lb_accion)
									{
										if(ls_plantilla.contains("<TAG_MATRICULA_PARCIAL>"))
										{
											String ls_matriculaParcial;
											ls_matriculaParcial = arpc_dataRegistroParcial.getMatriculaCompleta();

											if(StringUtils.isValidString(ls_matriculaParcial))
												ls_plantilla = ls_plantilla.replace(
													    "<TAG_MATRICULA_PARCIAL>", ls_matriculaParcial
													);
										}

										if(ls_plantilla.contains("<TAG_ANOTACION_PARCIAL>"))
										{
											String ls_anotacionParcial;
											ls_anotacionParcial = StringUtils.getString(
												    arpc_dataRegistroParcial.getIdAnotacion()
												);

											if(StringUtils.isValidString(ls_anotacionParcial))
												ls_plantilla = ls_plantilla.replace(
													    "<TAG_ANOTACION_PARCIAL>", ls_anotacionParcial
													);
										}

										if(ls_plantilla.contains("<TAG_ESPECIFICACION_ANOTACION_PARCIAL>"))
										{
											String ls_especificacionParcial;
											ls_especificacionParcial = arpc_dataRegistroParcial.getEspecificacion();

											if(StringUtils.isValidString(ls_especificacionParcial))
												ls_plantilla = ls_plantilla.replace(
													    "<TAG_ESPECIFICACION_ANOTACION_PARCIAL>",
													    ls_especificacionParcial
													);
										}
									}

									if(ls_plantilla.contains("<TAG_USUARIO>"))
									{
										String ls_userId;
										ls_userId = as_userId;

										if(StringUtils.isValidString(ls_userId))
											ls_plantilla = ls_plantilla.replace("<TAG_USUARIO>", ls_userId);
									}

									if(ls_plantilla.contains("<TAG_CIRCULO_REGISTRAL>"))
									{
										String ls_circulo;
										ls_circulo = loth_dataFile.getNombreCirculo();

										if(StringUtils.isValidString(ls_circulo))
											ls_plantilla = ls_plantilla.replace("<TAG_CIRCULO_REGISTRAL>", ls_circulo);
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

									if(ls_plantilla.contains("<TAG_ID_DOCUMENTO>"))
									{
										String ls_tipoArchivo;
										ls_tipoArchivo = loth_dataFile.getNombreTipoDoc();

										if(StringUtils.isValidString(ls_tipoArchivo))
											ls_plantilla = ls_plantilla.replace("<TAG_ID_DOCUMENTO>", ls_tipoArchivo);
									}

									if(ls_plantilla.contains("<TAG_NO_DOCUMENTO>"))
									{
										Long ll_numDoc;
										ll_numDoc = loth_dataFile.getNumeroDoc();

										if(NumericUtils.isValidLong(ll_numDoc))
											ls_plantilla = ls_plantilla.replace(
												    "<TAG_NO_DOCUMENTO>", ll_numDoc.toString()
												);
									}

									if(ls_plantilla.contains("<TAG_FECHA_DOCUMENTO>"))
									{
										Date ld_fechaDocumento;
										ld_fechaDocumento = loth_dataFile.getFechaDocumento();

										if(ld_fechaDocumento != null)
										{
											ls_fecha     = lsf_dateFormat.format(ld_fechaDocumento);

											ls_plantilla = ls_plantilla.replace("<TAG_FECHA_DOCUMENTO>", ls_fecha);
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
										loth_th            = lth_DAO.findMatriculas(ll_idTurnoHistoria);

										if(CollectionUtils.isValidCollection(loth_th))
										{
											for(TurnoHistoria loth_tmp : loth_th)
												lsb_matriculas = lsb_matriculas.append(loth_tmp.getMotivo() + ",");

											ls_matriculas = lsb_matriculas.toString();

											if(StringUtils.isValidString(ls_matriculas))
												ls_plantilla = ls_plantilla.replace("<TAG_MATRICULA>", ls_matriculas);
										}
										else
											ls_plantilla = ls_plantilla.replace("<TAG_MATRICULA>", "SIN MATRICULA");
									}

									if(ls_plantilla.contains("<TURNO_DERIVADO>"))
									{
										ls_turnoDerivado = lth_DAO.findTurnoDerivado(ls_turno);

										if(StringUtils.isValidString(ls_turnoDerivado))
											ls_plantilla = ls_plantilla.replace("<TURNO_DERIVADO>", ls_turnoDerivado);
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
										String ls_observaciones;

										ls_observaciones = atc_dataCausal.getObservaciones();

										if(StringUtils.isValidString(ls_observaciones))
											ls_plantilla = ls_plantilla.replace(
												    "<TAG_OBSERVACIONES>", "- " + ls_observaciones
												);
										else
											ls_plantilla = ls_plantilla.replace("<TAG_OBSERVACIONES>", "");
									}

									{
										Constantes lc_usuarioFirma;
										String     ls_tagUsuarioFirma;
										int        li_incrX = 0;
										int        li_incrY = 0;

										lc_usuarioFirma     = new Constantes();
										ls_tagUsuarioFirma  = "<TAG_USUARIO_FIRMA_SUSPENSION>";

										lc_usuarioFirma.setIdConstante(ConstanteCommon.USUARIO_FIRMA_SUSPENSION);

										lc_usuarioFirma        = DaoCreator.getConstantesDAO(adm_manager)
												                               .findByIdWithImage(lc_usuarioFirma);
										ls_plantilla           = getFirmaMasivaBusiness()
												                         .reemplazarUsuarioFirmaCargo(
												    ls_plantilla, lc_usuarioFirma, ls_tagUsuarioFirma,
												    "<TAG_CARGO_FIRMA_SUSPENSION>"
												);
										lmss_datos             = finalizarPlantilla(
											    ls_plantilla, ll_idTurnoHistoria, adm_manager
											);
										ls_plantilla           = lmss_datos.get(IdentificadoresCommon.PLANTILLA);
										lba_notaDevolutiva     = new PDFGenerator().convertirRTFToPDF(
											    ls_plantilla.getBytes(), adm_manager
											);

										if(lba_notaDevolutiva == null)
											throw new B2BException(ErrorKeys.ERROR_GENERANDO_ARCHIVO_NOTA_DEVOLUTIVA);

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

											lba_notaDevolutiva = getFirmaMasivaBusiness()
													                     .reemplazarBookmarsFirma(
													    lba_notaDevolutiva, lba_grafo, li_incrX, li_incrY
													);
										}
									}

									{
										Imagenes li_imagen;
										long     ll_idImagen;

										li_imagen = new Imagenes();

										li_imagen.setImagenBlob(lba_notaDevolutiva);
										li_imagen.setTipoArchivo(ExtensionCommon.PDF_MAYUS);
										li_imagen.setTamano(NumericUtils.getLongWrapper(lba_notaDevolutiva.length));
										li_imagen.setIdUsuarioCreacion(as_userId);
										li_imagen.setIpCreacion(as_remoteIp);
										li_imagen.setCodigoVerificacion(
										    lmss_datos.get(IdentificadoresCommon.CODIGO_VERIFICACION)
										);

										ll_idImagen = DaoCreator.getImagenesDAO(adm_manager)
												                    .insertOrUpdate(li_imagen, true);

										if(ll_idImagen > 0)
										{
											DocumentosSalidaDAO lds_DAO;
											DocumentosSalida    lds_documentoSalida;
											String              ls_tipo;

											lds_DAO                 = DaoCreator.getDocumentosSalidaDAO(adm_manager);
											lds_documentoSalida     = new DocumentosSalida();
											ls_tipo                 = lb_accion
												? TipoArchivoCommon.NOTA_DEVOLUTIVA_PARCIAL
												: TipoArchivoCommon.NOTA_DEVOLUTIVA;

											lds_documentoSalida.setIdSolicitud(lth_detalle.getIdSolicitud());
											lds_documentoSalida.setIdTurno(ls_turno);
											lds_documentoSalida.setTipo(ls_tipo);
											lds_documentoSalida.setEstado(EstadoCommon.ACTIVO);
											lds_documentoSalida.setIdTurnoHistoria(
											    NumericUtils.getInteger(ll_idTurnoHistoria)
											);
											lds_documentoSalida.setRepositorio(
											    ab_firmaMasiva ? RepositorioCommon.FINAL : RepositorioCommon.TEMPORAL
											);
											lds_documentoSalida.setIdTipoDocumental(
											    TipoDocumentalCommon.NOTA_DEVOLUTIVA
											);
											lds_documentoSalida.setIdImagen(NumericUtils.getLongWrapper(ll_idImagen));
											lds_documentoSalida.setIdUsuarioCreacion(as_userId);
											lds_documentoSalida.setIpCreacion(as_remoteIp);

											Collection<DocumentosSalida> lcds_documentosExistentes;

											lcds_documentosExistentes = lds_DAO.findByIdTurnoHistoriaTipo(
												    lds_documentoSalida
												);

											if(!CollectionUtils.isValidCollection(lcds_documentosExistentes))
												lds_DAO.insertOrUpdate(lds_documentoSalida, true);
											else
											{
												for(DocumentosSalida lds_docs : lcds_documentosExistentes)
												{
													if(lds_docs != null)
													{
														lds_documentoSalida.setIdDocumentoSalida(
														    lds_docs.getIdDocumentoSalida()
														);
														lds_documentoSalida.setIdUsuarioModificacion(as_userId);
														lds_documentoSalida.setIpModificacion(as_remoteIp);

														lds_documentoSalida.setIdEcm(null);

														lds_DAO.insertOrUpdate(lds_documentoSalida, false);
													}
												}
											}
										}
										else
											throw new B2BException(ErrorKeys.SIN_SECUENCIA_IMAGENES);
									}
								}
								else
									throw new B2BException(ErrorKeys.ERROR_INTERNO_SISTEMA);
							}
							else
								throw new B2BException(ErrorKeys.ERROR_INTERNO_SISTEMA);
						}
						else
							throw new B2BException(ErrorKeys.ERROR_INTERNO_SISTEMA);
					}
					else
						throw new B2BException(ErrorKeys.ERROR_INTERNO_SISTEMA);
				}
				else
					throw new B2BException(ErrorKeys.ERROR_INTERNO_SISTEMA);
			}
			else
				throw new B2BException(ErrorKeys.ERROR_INTERNO_SISTEMA);
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("generarNotaDevolutiva", lb2be_e);

			throw lb2be_e;
		}

		return lba_notaDevolutiva;
	}

	/**
	 * Construye la plantilla entidad corrección si procede.
	 *
	 * @param adm_manager Conexión a la base de datos
	 * @param as_idSolicitud id de la solicitud del tramite
	 * @param as_idTurno id del turno del tramite
	 * @param as_userId id del usuario que ejecuta la acción
	 * @param al_idTurnoHistoria de al id turno historia
	 * @param as_remoteIp de as remote ip
	 * @param ab_salvar para guardar la información en base de datos
	 * @param ab_firma de ab firma
	 * @return Arreglo de bytes contenedor de la imagen de la plantilla
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 * @para ab_firma como indicador si se genera desde firma masiva
	 */
	public byte[] generarPlantillaEntidadCorreccionSiProcede(
	    DAOManager adm_manager, String as_idSolicitud, String as_idTurno, String as_userId, Long al_idTurnoHistoria,
	    String as_remoteIp, boolean ab_salvar, boolean ab_firma
	)
	    throws B2BException
	{
		byte[]        lba_return;
		String        ls_plantilla;
		TurnoHistoria lth_turnoHistoria;
		lth_turnoHistoria = new TurnoHistoria();
		lth_turnoHistoria.setIdTurnoHistoria(al_idTurnoHistoria);
		lth_turnoHistoria     = DaoCreator.getTurnoHistoriaDAO(adm_manager).findById(al_idTurnoHistoria);
		lba_return            = null;

		if(lth_turnoHistoria != null)
		{
			ls_plantilla = obtenerPlantillaDeConstante(
				    adm_manager, ConstanteCommon.PLANTILLA_OFICIO_REMISORIO_ENTIDAD_CORRECCION_SIPROCEDE
				);

			if(StringUtils.isValidString(ls_plantilla))
			{
				String ls_tag;
				String ls_idCirculo;

				ls_plantilla     = escribirTagFechaLarga(ls_plantilla);
				ls_idCirculo     = null;

				{
					Solicitud ls_solicitud;

					ls_solicitud = DaoCreator.getSolicitudDAO(adm_manager).findById(as_idSolicitud);

					if(ls_solicitud != null)
					{
						ls_tag = "<TAG_NIR>";

						if(ls_plantilla.contains(ls_tag))
							ls_plantilla = ls_plantilla.replace(
								    ls_tag, StringUtils.getStringNotNull(ls_solicitud.getNir())
								);

						{
							Persona lp_persona;

							lp_persona = DaoCreator.getPersonaDAO(adm_manager).findById(ls_solicitud.getIdPersona());

							if((lp_persona != null))
							{
								String ls_idPersona;

								ls_idPersona     = lp_persona.getIdPersona();

								ls_tag = "<TAG_NOMBRE_INTERESADO>";

								if(ls_plantilla.contains(ls_tag))
								{
									StringBuilder lsb_nombre;

									lsb_nombre = new StringBuilder();

									agregarCadenaConEspacioAStringBuilder(lsb_nombre, lp_persona.getPrimerNombre());
									agregarCadenaConEspacioAStringBuilder(lsb_nombre, lp_persona.getSegundoNombre());
									agregarCadenaConEspacioAStringBuilder(lsb_nombre, lp_persona.getPrimerApellido());
									agregarCadenaConEspacioAStringBuilder(lsb_nombre, lp_persona.getSegundoApellido());

									ls_plantilla = ls_plantilla.replace(ls_tag, lsb_nombre.toString());
								}

								ls_tag = "<TAG_CORREO_ELECTRONICO>";

								if(ls_plantilla.contains(ls_tag))
								{
									String ls_idCorreoElectronico;

									ls_idCorreoElectronico = ls_solicitud.getIdCorreoElectronico();

									if(StringUtils.isValidString(ls_idCorreoElectronico))
									{
										PersonaCorreoElectronico lpce_correo;

										lpce_correo = DaoCreator.getPersonaCorreoElectronicoDAO(adm_manager)
												                    .findById(ls_idPersona, ls_idCorreoElectronico);

										if(lpce_correo != null)
											ls_plantilla = ls_plantilla.replace(
												    ls_tag,
												    StringUtils.getStringNotNull(lpce_correo.getCorreoElectronico())
												);
									}
								}

								ls_tag = "<TAG_DIR_INTERESADO>";

								if(ls_plantilla.contains(ls_tag))
								{
									String ls_idDireccion;

									ls_idDireccion = ls_solicitud.getIdDireccion();

									if(StringUtils.isValidString(ls_idDireccion))
									{
										PersonaDireccion lpd_direccion;

										lpd_direccion = DaoCreator.getPersonaDireccionDAO(adm_manager)
												                      .findById(ls_idPersona, ls_idDireccion);

										if(lpd_direccion != null)
										{
											String ls_idPais;
											String ls_idDepartamento;
											String ls_idMunicipio;

											ls_idPais             = lpd_direccion.getIdDireccion();
											ls_idDepartamento     = lpd_direccion.getIdDepartamento();
											ls_idMunicipio        = lpd_direccion.getIdMunicipio();

											ls_plantilla     = ls_plantilla.replace(
												    ls_tag, StringUtils.getStringNotNull(lpd_direccion.getDireccion())
												);

											ls_tag = "<TAG_DEPAR_INTERESADO>";

											if(ls_plantilla.contains(ls_tag))
											{
												Departamento ld_departamento;

												ld_departamento = DaoCreator.getDepartamentoDAO(adm_manager)
														                        .findById(ls_idPais, ls_idDepartamento);

												if(ld_departamento != null)
													ls_plantilla = ls_plantilla.replace(
														    ls_tag,
														    StringUtils.getStringNotNull(ld_departamento.getNombre())
														);
											}

											ls_tag = "<TAG_MUNICIPIO_INTERESADO>";

											if(ls_plantilla.contains(ls_tag))
											{
												Municipio    lm_municipio;
												MunicipioDAO lmd_municipioDAO;

												lmd_municipioDAO     = DaoCreator.getMunicipioDAO(adm_manager);
												lm_municipio         = lmd_municipioDAO.findById(
													    ls_idPais, ls_idDepartamento, ls_idMunicipio
													);

												if(lm_municipio != null)
													ls_plantilla = ls_plantilla.replace(
														    ls_tag,
														    StringUtils.getStringNotNull(lm_municipio.getNombre())
														);
											}
										}
									}
								}
							}
						}
					}
					else
						throw new B2BException(ErrorKeys.ERROR_SIN_ID_SOLICITUD);
				}

				{
					Turno lt_turno;

					lt_turno = DaoCreator.getTurnoDAO(adm_manager).findById(as_idTurno);

					if(lt_turno != null)
					{
						ls_idCirculo = lt_turno.getIdCirculo();

						{
							CirculoRegistral lcr_circulo;

							lcr_circulo     = DaoCreator.getCirculoRegistralDAO(adm_manager).findById(ls_idCirculo);

							ls_plantilla = escribirInfoCirculoRegistral(lcr_circulo, ls_plantilla);
						}

						ls_tag = "<TAG_ACC_TUR_ID_TURNO>";

						if(ls_plantilla.contains(ls_tag))
							ls_plantilla = ls_plantilla.replace(ls_tag, lt_turno.getIdTurno());
					}
					else
						throw new B2BException(ErrorKeys.ERROR_SIN_ID_TURNO);
				}

				String ls_consecutivoOficio;
				String ls_referenciaSalida;
				Date   ld_fechaOficio;

				ls_consecutivoOficio     = null;
				ls_referenciaSalida      = null;
				ld_fechaOficio           = null;

				if(ab_firma)
				{
					{
						ls_tag = "<TAG_OFICIO>";

						if(ls_plantilla.contains(ls_tag))
						{
							NumeracionOficiosCirculo lnoc_numeracionOficios;
							lnoc_numeracionOficios = findNumeracionOficiosCirculo(
								    lth_turnoHistoria, adm_manager, as_userId, as_remoteIp
								);

							if(lnoc_numeracionOficios != null)
							{
								ls_consecutivoOficio     = lnoc_numeracionOficios.getConsecutivoGenerado();

								ls_plantilla     = ls_plantilla.replace(ls_tag, ls_consecutivoOficio);

								ld_fechaOficio = new Date();
							}
						}
					}

					{
						ls_tag = "<TAG_REFERENCIA_SALIDA>";

						if(ls_plantilla.contains(ls_tag))
						{
							ls_referenciaSalida     = generarIdCorrespondencia(lth_turnoHistoria, adm_manager, false);

							ls_plantilla = ls_plantilla.replace(ls_tag, ls_referenciaSalida);
						}
					}
				}

				ls_tag = "<TAG_MUN_OFI_ORIGEN>";

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
				}

				{
					Collection<SolicitudMatricula> lcsm_matriculas;

					lcsm_matriculas = DaoCreator.getSolicitudMatriculaDAO(adm_manager)
							                        .findByIdSolicitudCirculo(as_idSolicitud, ls_idCirculo);

					if(CollectionUtils.isValidCollection(lcsm_matriculas))
					{
						StringBuilder                lsb_matriculas;
						Iterator<SolicitudMatricula> lism_iterador;
						AccPredioRegistroDAO         laprd_predioRegistroDAO;

						lsb_matriculas              = new StringBuilder();
						lism_iterador               = lcsm_matriculas.iterator();
						laprd_predioRegistroDAO     = DaoCreator.getAccPredioRegistroDAO(adm_manager);

						while(lism_iterador.hasNext())
						{
							SolicitudMatricula lsm_data;

							lsm_data = lism_iterador.next();

							if(lsm_data != null)
							{
								long ll_idMatricula;

								ll_idMatricula = lsm_data.getIdMatricula();

								lsb_matriculas.append(ls_idCirculo);
								lsb_matriculas.append(IdentificadoresCommon.SIMBOLO_GUION);
								lsb_matriculas.append(ll_idMatricula);

								{
									AccPredioRegistro lapr_tmp;

									lapr_tmp = laprd_predioRegistroDAO.findByCirculoMatriculaTurno(
										    ls_idCirculo, NumericUtils.getLongWrapper(ll_idMatricula), as_idTurno
										);

									if(lapr_tmp != null)
									{
										String ls_nupre;

										ls_nupre = lapr_tmp.getNupre();

										if(StringUtils.isValidString(ls_nupre))
										{
											lsb_matriculas.append(IdentificadoresCommon.ESPACIO_VACIO);
											lsb_matriculas.append(IdentificadoresCommon.NUPRE);
											lsb_matriculas.append(IdentificadoresCommon.DOS_PUNTOS_ESPACIO);
											lsb_matriculas.append(ls_nupre);
										}
									}
								}

								if(lism_iterador.hasNext())
									lsb_matriculas.append(IdentificadoresCommon.SIMBOLO_COMA);
							}
						}

						ls_tag = "<TAG_SOLICITUD_MATRICULA>";

						if(ls_plantilla.contains(ls_tag))
							ls_plantilla = ls_plantilla.replace(ls_tag, lsb_matriculas.toString());
					}
				}

				ls_tag = "<TAG_USUARIO>";

				if(ls_plantilla.contains(ls_tag) && StringUtils.isValidString(as_userId))
					ls_plantilla = ls_plantilla.replace(ls_tag, as_userId);

				{
					ConstantesDAO lcd_DAO;
					lcd_DAO = DaoCreator.getConstantesDAO(adm_manager);

					Map<String, String> lmss_datos;
					Constantes          lc_usuarioFirma;
					byte[]              lba_resolucion;
					String              ls_tagUsuarioFirma;
					int                 li_incrX = 0;
					int                 li_incrY = 0;

					lc_usuarioFirma              = new Constantes();
					ls_tagUsuarioFirma           = "<TAG_USUARIO_FIRMA_SUSPENSION>";

					lc_usuarioFirma.setIdConstante(ConstanteCommon.USUARIO_FIRMA_SUSPENSION);

					lc_usuarioFirma     = lcd_DAO.findByIdWithImage(lc_usuarioFirma);
					ls_plantilla        = getFirmaMasivaBusiness()
							                      .reemplazarUsuarioFirmaCargo(
							    ls_plantilla, lc_usuarioFirma, ls_tagUsuarioFirma, "<TAG_CARGO_FIRMA_SUSPENSION>"
							);
					lmss_datos          = finalizarPlantilla(
						    ls_plantilla, lth_turnoHistoria.getIdTurnoHistoria(), adm_manager
						);
					ls_plantilla        = lmss_datos.get(IdentificadoresCommon.PLANTILLA);
					lba_resolucion      = new PDFGenerator().convertirRTFToPDF(ls_plantilla.getBytes(), adm_manager);

					if(ab_firma)
					{
						byte[] lba_grafo;

						lba_grafo = null;

						if(lc_usuarioFirma != null)
						{
							lba_grafo     = lc_usuarioFirma.getImagenes();
							li_incrX      = NumericUtils.getInt(lc_usuarioFirma.getEntero());
							li_incrY      = NumericUtils.getInt(lc_usuarioFirma.getReal());
						}

						lba_resolucion = getFirmaMasivaBusiness()
								                 .reemplazarBookmarsFirma(
								    lba_resolucion, lba_grafo, li_incrX, li_incrY
								);
					}

					if(lba_resolucion == null)
						throw new B2BException(ErrorKeys.NO_SE_GENERO_RESOLUCION);
					else
						lba_return = lba_resolucion;

					if(ab_salvar)
					{
						Imagenes    li_imagenes;
						ImagenesDAO li_DAO;
						long        ll_idImagenTemp;

						li_imagenes     = new Imagenes();
						li_DAO          = DaoCreator.getImagenesDAO(adm_manager);

						li_imagenes.setTipoArchivo(ExtensionCommon.PDF_MAYUS);
						li_imagenes.setIdUsuarioCreacion(as_userId);
						li_imagenes.setIpCreacion(as_remoteIp);
						li_imagenes.setImagenBlob(lba_return);
						li_imagenes.setCodigoVerificacion(lmss_datos.get(IdentificadoresCommon.CODIGO_VERIFICACION));

						ll_idImagenTemp = li_DAO.insertOrUpdate(li_imagenes, true);

						if(ll_idImagenTemp > 0)
						{
							DocumentosSalida    lds_documentoSalida;
							DocumentosSalidaDAO lds_DAO;
							Long                ll_idImagen;

							lds_documentoSalida     = new DocumentosSalida();
							lds_DAO                 = DaoCreator.getDocumentosSalidaDAO(adm_manager);
							ll_idImagen             = NumericUtils.getLongWrapper(ll_idImagenTemp);

							lds_documentoSalida.setIdSolicitud(as_idSolicitud);
							lds_documentoSalida.setIdTurno(as_idTurno);
							lds_documentoSalida.setTipo(TipoArchivoCommon.COMUNICADO);
							lds_documentoSalida.setEstado(EstadoCommon.ACTIVO);
							lds_documentoSalida.setReferenciaSalida(ls_referenciaSalida);

							if(ab_firma)
								lds_documentoSalida = null;
							else
								lds_documentoSalida = lds_DAO.findDocumentByTurnoTipoEstadoReferenciaSalida(
									    lds_documentoSalida
									);

							if(lds_documentoSalida != null)
							{
								lds_documentoSalida.setIdImagen(ll_idImagen);
								lds_documentoSalida.setIdUsuarioModificacion(as_userId);
								lds_documentoSalida.setIpModificacion(as_remoteIp);

								lds_DAO.updateImagen(lds_documentoSalida);
							}
							else
							{
								lds_documentoSalida = new DocumentosSalida();

								lds_documentoSalida.setIdTurno(as_idTurno);
								lds_documentoSalida.setIdSolicitud(as_idSolicitud);
								lds_documentoSalida.setIdImagen(ll_idImagen);
								lds_documentoSalida.setTipo(TipoArchivoCommon.COMUNICADO);
								lds_documentoSalida.setIdTipoDocumental(TipoDocumentalCommon.COMUNICACION);
								lds_documentoSalida.setReferenciaSalida(ls_referenciaSalida);
								lds_documentoSalida.setConsecutivoOficio(ls_consecutivoOficio);
								lds_documentoSalida.setFechaOficio(ld_fechaOficio);
								lds_documentoSalida.setRepositorio(RepositorioCommon.FINAL);
								lds_documentoSalida.setEstado(EstadoCommon.ACTIVO);
								lds_documentoSalida.setIdTurnoHistoria(NumericUtils.getInteger(al_idTurnoHistoria));
								lds_documentoSalida.setIdUsuarioCreacion(as_userId);
								lds_documentoSalida.setIpCreacion(as_remoteIp);

								lds_DAO.insertOrUpdate(lds_documentoSalida, true);
							}
						}
						else
							throw new B2BException(ErrorKeys.NO_SE_GENERO_ARCHIVO);
					}
				}
			}
		}

		return lba_return;
	}

	/**
	 * Método encargado de guardar los datos de segregación para correcciones.
	 *
	 * @param acps_prediosSegregados Colección que contiene los predios a guardar.
	 * @param as_idTurnoHistoria Variable que contiene el id del turno historia.
	 * @param as_idTurno Variable que contiene el id del turno.
	 * @param as_userId Variable que contiene el id del usuario.
	 * @param as_remoteIp Variable que contiene la ip del usuario.
	 * @param as_idCirculo Variable que contiene el id del circulo.
	 * @param al_idMatricula Variable que contiene el id de la matrícula.
	 * @param ab_segregado Variable que valida si es segregación o matrículas con base en.
	 * @return el valor de collection
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public synchronized Collection<PredioSegregado> guardarDatosSegregacion(
	    Collection<PredioSegregado> acps_prediosSegregados, String as_idTurnoHistoria, String as_idTurno,
	    String                      as_userId, String as_remoteIp, String as_idCirculo, Long al_idMatricula,
	    boolean                     ab_segregado
	)
	    throws B2BException
	{
		return guardarDatosSegregacion(
		    acps_prediosSegregados, as_idTurnoHistoria, as_idTurno, as_userId, as_remoteIp, as_idCirculo, al_idMatricula,
		    ab_segregado, true
		);
	}

	/**
	 * Método encargado de guardar las salvedades.
	 *
	 * @param asp_salvedad Objeto que contiene la información de la salvedad a guardar.
	 * @param as_userId Variable de tipo String que contiene el id del usuario.
	 * @param as_remoteIp Variable de tipo String que contiene la ip del usuario.
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public synchronized void guardarSalvedades(AccSalvedadPredio asp_salvedad, String as_userId, String as_remoteIp)
	    throws B2BException
	{
		DAOManager ldm_manager;

		ldm_manager = DaoManagerFactory.getDAOManager();

		try
		{
			if(asp_salvedad != null)
			{
				Long          ll_idTurnoHistoria;
				TurnoHistoria lth_turnoHistoria;

				ll_idTurnoHistoria     = asp_salvedad.getIdTurnoHistoria();
				lth_turnoHistoria      = DaoCreator.getTurnoHistoriaDAO(ldm_manager).findById(ll_idTurnoHistoria);

				if(lth_turnoHistoria != null)
				{
					String ls_idTurno;

					ls_idTurno = lth_turnoHistoria.getIdTurno();

					if(StringUtils.isValidString(ls_idTurno))
					{
						String ls_salvedad;

						ls_salvedad = asp_salvedad.getDescripcion();

						if(StringUtils.isValidString(ls_salvedad))
						{
							AccSalvedadPredio              lsp_salvedadTemp;
							AccSalvedadPredioDAO           lsp_DAO;
							boolean                        lb_insertar;
							Collection<SolicitudMatricula> lcsm_matriculas;
							Long                           ll_idCausal;
							String                         ls_copiar;
							String                         ls_seleccionar;

							asp_salvedad.setIdTurno(ls_idTurno);

							lsp_DAO              = DaoCreator.getAccSalvedadPredioDAO(ldm_manager);
							lsp_salvedadTemp     = lsp_DAO.findById(asp_salvedad);
							lb_insertar          = lsp_salvedadTemp == null;
							ll_idCausal          = asp_salvedad.getIdCausal();
							lcsm_matriculas      = asp_salvedad.getMatriculas();
							ls_copiar            = asp_salvedad.getCopiar();
							ls_seleccionar       = asp_salvedad.getCopiarSeleccionadas();

							if(lb_insertar)
							{
								asp_salvedad.setRadicacion(ls_idTurno);
								asp_salvedad.setIdUsuarioCreacion(as_userId);
								asp_salvedad.setIpCreacion(as_remoteIp);
							}
							else
							{
								lsp_salvedadTemp.setDescripcion(ls_salvedad);
								lsp_salvedadTemp.setIdUsuarioModificacion(as_userId);
								lsp_salvedadTemp.setIpModificacion(as_remoteIp);

								asp_salvedad = lsp_salvedadTemp;
							}

							lsp_DAO.insertOrUpdate(asp_salvedad, lb_insertar);

							if(StringUtils.isValidString(ls_copiar))
							{
								if(BooleanUtils.getBooleanValue(ls_copiar))
								{
									if(StringUtils.isValidString(ls_seleccionar))
									{
										if(CollectionUtils.isValidCollection(lcsm_matriculas))
										{
											boolean lb_seleccionada;

											lb_seleccionada = BooleanUtils.getBooleanValue(ls_seleccionar);

											for(SolicitudMatricula lsm_iterador : lcsm_matriculas)
											{
												if(lsm_iterador != null)
												{
													if(!lb_seleccionada || lsm_iterador.isSeleccionado())
													{
														Long   ll_idMatricula;
														String ls_idCirculo;

														ll_idMatricula     = NumericUtils.getLongWrapper(
															    lsm_iterador.getIdMatricula()
															);
														ls_idCirculo       = lsm_iterador.getIdCirculo();

														if(
														    StringUtils.isValidString(ls_idCirculo)
															    && NumericUtils.isValidLong(ll_idMatricula)
														)
														{
															AccSalvedadPredio lsa_salvedad;
															AccSalvedadPredio lsp_temp;

															lsa_salvedad = new AccSalvedadPredio();

															lsa_salvedad.setDescripcion(ls_salvedad);
															lsa_salvedad.setIdTurnoHistoria(ll_idTurnoHistoria);
															lsa_salvedad.setIdTurno(ls_idTurno);
															lsa_salvedad.setIdCirculo(ls_idCirculo);
															lsa_salvedad.setIdMatricula(ll_idMatricula);
															lsa_salvedad.setIdCausal(ll_idCausal);

															lsp_temp        = lsp_DAO.findById(lsa_salvedad);
															lb_insertar     = lsp_temp == null;

															if(lb_insertar)
															{
																lsa_salvedad.setRadicacion(ls_idTurno);
																lsa_salvedad.setIdUsuarioCreacion(as_userId);
																lsa_salvedad.setIpCreacion(as_remoteIp);
															}
															else
															{
																lsp_temp.setDescripcion(ls_salvedad);
																lsp_temp.setIdUsuarioModificacion(as_userId);
																lsp_temp.setIpModificacion(as_remoteIp);

																lsa_salvedad = lsp_temp;
															}

															lsp_DAO.insertOrUpdate(lsa_salvedad, lb_insertar);
														}
														else
															throw new B2BException(ErrorKeys.ERROR_INTERNO_SISTEMA);
													}
												}
												else
													throw new B2BException(ErrorKeys.ERROR_INTERNO_SISTEMA);
											}
										}
										else
											throw new B2BException(ErrorKeys.MATRICULAS_COPIAR);
									}
									else
										throw new B2BException(ErrorKeys.ERROR_COPIAR_SALVEDAD);
								}
							}
							else
								throw new B2BException(ErrorKeys.ERROR_COPIAR_SALVEDAD);
						}
						else
							throw new B2BException(ErrorKeys.ERROR_SALVEDAD);
					}
					else
						throw new B2BException(ErrorKeys.ERROR_INTERNO_SISTEMA);
				}
				else
					throw new B2BException(ErrorKeys.ERROR_INTERNO_SISTEMA);
			}
			else
				throw new B2BException(ErrorKeys.ERROR_INTERNO_SISTEMA);
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("guardarSalvedades", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}
	}

	/**
	 * Método encargado de salvar salvedades para anotaciones.
	 *
	 * @param asa_salvedad Objeto que contiene la información de salvedad a guardar.
	 * @param as_userId Variable de tipo String que contiene el id del usuario.
	 * @param as_remoteIp Variable de tipo String que contiene la ip del usuario.
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public synchronized void guardarSalvedadesAnotacion(
	    AccSalvedadAnotacion asa_salvedad, String as_userId, String as_remoteIp
	)
	    throws B2BException
	{
		DAOManager ldm_manager;

		ldm_manager = DaoManagerFactory.getDAOManager();

		try
		{
			if(asa_salvedad != null)
			{
				Long          ll_idTurnoHistoria;
				TurnoHistoria lth_turnoHistoria;

				ll_idTurnoHistoria     = asa_salvedad.getIdTurnoHistoria();
				lth_turnoHistoria      = DaoCreator.getTurnoHistoriaDAO(ldm_manager).findById(ll_idTurnoHistoria);

				if(lth_turnoHistoria != null)
				{
					String ls_idTurno;

					ls_idTurno = lth_turnoHistoria.getIdTurno();

					if(StringUtils.isValidString(ls_idTurno))
					{
						Anotacion la_anotacion;

						la_anotacion = asa_salvedad.getAnotacion();

						if(la_anotacion != null)
						{
							com.bachue.snr.prosnr01.model.sdb.acc.AnotacionPredio lap_anotacionPredio;

							lap_anotacionPredio = la_anotacion.getAnotacionPredio();

							if(lap_anotacionPredio != null)
							{
								Long   ll_idAnotacion;
								String ls_idNaturalezaJuridica;
								String ls_radicacion;

								ll_idAnotacion              = lap_anotacionPredio.getIdAnotacion();
								ls_idNaturalezaJuridica     = lap_anotacionPredio.getIdNaturalezaJuridica();
								ls_radicacion               = lap_anotacionPredio.getRadicacion();

								if(
								    NumericUtils.isValidLong(ll_idAnotacion)
									    && StringUtils.isValidString(ls_idNaturalezaJuridica)
									    && StringUtils.isValidString(ls_radicacion)
								)
								{
									String ls_salvedad;

									ls_salvedad = asa_salvedad.getDescripcion();

									if(StringUtils.isValidString(ls_salvedad))
									{
										AccSalvedadAnotacion           lsa_salvedadTemp;
										AccSalvedadAnotacionDAO        lsa_DAO;
										Collection<SolicitudMatricula> lcsm_matriculas;
										boolean                        lb_insertar;
										String                         ls_copiar;
										String                         ls_seleccionar;
										String[]                       lsa_natJuridica;

										asa_salvedad.setIdTurno(ls_idTurno);
										asa_salvedad.setIdAnotacion(ll_idAnotacion);

										lcsm_matriculas      = asa_salvedad.getMatriculas();
										ls_copiar            = asa_salvedad.getCopiar();
										ls_seleccionar       = asa_salvedad.getCopiarSeleccionadas();
										lsa_DAO              = DaoCreator.getAccSalvedadAnotacionDAO(ldm_manager);
										lsa_salvedadTemp     = lsa_DAO.findById(asa_salvedad);
										lb_insertar          = lsa_salvedadTemp == null;
										lsa_natJuridica      = StringUtils.getStringArray(
											    ls_idNaturalezaJuridica, IdentificadoresCommon.SIMBOLO_GUION
											);

										if(CollectionUtils.isValidCollection(lsa_natJuridica))
											ls_idNaturalezaJuridica = lsa_natJuridica[0];

										if(lb_insertar)
										{
											asa_salvedad.setRadicacion(ls_idTurno);
											asa_salvedad.setIdUsuarioCreacion(as_userId);
											asa_salvedad.setIpCreacion(as_remoteIp);
										}
										else
										{
											lsa_salvedadTemp.setDescripcion(asa_salvedad.getDescripcion());
											lsa_salvedadTemp.setIdUsuarioModificacion(as_userId);
											lsa_salvedadTemp.setIpModificacion(as_remoteIp);

											asa_salvedad = lsa_salvedadTemp;
										}

										lsa_DAO.insertOrUpdate(asa_salvedad, lb_insertar);

										if(StringUtils.isValidString(ls_copiar))
										{
											if(BooleanUtils.getBooleanValue(ls_copiar))
											{
												if(StringUtils.isValidString(ls_seleccionar))
												{
													if(CollectionUtils.isValidCollection(lcsm_matriculas))
													{
														AnotacionPredioDAO lap_DAO;
														boolean            lb_seleccionada;

														lap_DAO             = DaoCreator.getAccAnotacionPredioDAO(
															    ldm_manager
															);
														lb_seleccionada     = BooleanUtils.getBooleanValue(
															    ls_seleccionar
															);

														for(SolicitudMatricula lsm_iterador : lcsm_matriculas)
														{
															if(lsm_iterador != null)
															{
																if(!lb_seleccionada || lsm_iterador.isSeleccionado())
																{
																	Long   ll_idMatricula;
																	String ls_idCirculo;

																	ll_idMatricula     = NumericUtils.getLongWrapper(
																		    lsm_iterador.getIdMatricula()
																		);
																	ls_idCirculo       = lsm_iterador.getIdCirculo();

																	if(
																	    StringUtils.isValidString(ls_idCirculo)
																		    && NumericUtils.isValidLong(ll_idMatricula)
																	)
																	{
																		Object[]                                                          loa_arg;
																		Collection<com.bachue.snr.prosnr01.model.sdb.acc.AnotacionPredio> lcap_anotaciones;

																		loa_arg              = new String[1];
																		loa_arg[0]           = ls_idCirculo
																			+ IdentificadoresCommon.SIMBOLO_GUION
																			+ ll_idMatricula;
																		lcap_anotaciones     = lap_DAO
																				.findByCirculoMatriculaTurno(
																				    ls_idCirculo, ll_idMatricula,
																				    ls_idTurno
																				);

																		if(
																		    CollectionUtils.isValidCollection(
																			        lcap_anotaciones
																			    )
																		)
																		{
																			AccSalvedadAnotacion lsa_salvedad;

																			lsa_salvedad = new AccSalvedadAnotacion();

																			lsa_salvedad.setDescripcion(ls_salvedad);
																			lsa_salvedad.setIdTurnoHistoria(
																			    ll_idTurnoHistoria
																			);
																			lsa_salvedad.setIdTurno(ls_idTurno);
																			lsa_salvedad.setIdCirculo(ls_idCirculo);
																			lsa_salvedad.setIdMatricula(ll_idMatricula);

																			for(com.bachue.snr.prosnr01.model.sdb.acc.AnotacionPredio lap_iterador : lcap_anotaciones)
																			{
																				if(lap_iterador != null)
																				{
																					Long   ll_idAnotaconTemp;
																					String ls_natJuridica;

																					ll_idAnotaconTemp     = lap_iterador
																							.getIdAnotacion();
																					ls_natJuridica        = lap_iterador
																							.getIdNaturalezaJuridica();

																					if(
																					    NumericUtils.isValidLong(
																						        ll_idAnotaconTemp
																						    )
																						    && StringUtils.isValidString(
																						        ls_natJuridica
																						    )
																						    && ls_natJuridica
																						    .equalsIgnoreCase(ls_idNaturalezaJuridica)
																					)
																					{
																						AccSalvedadAnotacion lsa_temp;

																						lsa_salvedad.setIdAnotacion(
																						    ll_idAnotaconTemp
																						);

																						lsa_temp        = lsa_DAO
																								.findById(lsa_salvedad);
																						lb_insertar     = lsa_temp == null;

																						if(lb_insertar)
																						{
																							lsa_salvedad.setRadicacion(
																							    ls_idTurno
																							);
																							lsa_salvedad
																								.setIdUsuarioCreacion(
																								    as_userId
																								);
																							lsa_salvedad.setIpCreacion(
																							    as_remoteIp
																							);
																						}
																						else
																						{
																							lsa_temp.setDescripcion(
																							    asa_salvedad
																								    .getDescripcion()
																							);
																							lsa_temp
																								.setIdUsuarioModificacion(
																								    as_userId
																								);
																							lsa_temp.setIpModificacion(
																							    as_remoteIp
																							);

																							lsa_salvedad = lsa_temp;
																						}

																						lsa_DAO.insertOrUpdate(
																						    lsa_salvedad, lb_insertar
																						);
																					}
																				}
																				else
																					throw new B2BException(
																					    ErrorKeys.ERROR_INTERNO_SISTEMA
																					);
																			}
																		}
																		else
																			throw new B2BException(
																			    ErrorKeys.SALVEDAD_ANOTACIONES_COPIA,
																			    loa_arg
																			);
																	}
																	else
																		throw new B2BException(
																		    ErrorKeys.ERROR_INTERNO_SISTEMA
																		);
																}
															}
															else
																throw new B2BException(ErrorKeys.ERROR_INTERNO_SISTEMA);
														}
													}
													else
														throw new B2BException(ErrorKeys.MATRICULAS_COPIAR);
												}
												else
													throw new B2BException(ErrorKeys.ERROR_COPIAR_SALVEDAD);
											}
										}
										else
											throw new B2BException(ErrorKeys.ERROR_COPIAR_SALVEDAD);
									}
									else
										throw new B2BException(ErrorKeys.ERROR_SALVEDAD);
								}
								else
									throw new B2BException(ErrorKeys.ERROR_INTERNO_SISTEMA);
							}
							else
								throw new B2BException(ErrorKeys.ERROR_INTERNO_SISTEMA);
						}
						else
							throw new B2BException(ErrorKeys.ERROR_INTERNO_SISTEMA);
					}
					else
						throw new B2BException(ErrorKeys.ERROR_INTERNO_SISTEMA);
				}
				else
					throw new B2BException(ErrorKeys.ERROR_INTERNO_SISTEMA);
			}
			else
				throw new B2BException(ErrorKeys.ERROR_INTERNO_SISTEMA);
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("guardarSalvedadesAnotacion", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}
	}

	/**
	 * Método encargado de insertar matrículas de segregación para el proceso de reloteo en calificación.
	 *
	 * @param arc_data Objeto de tipo RegistroCalificacion que contiene los datos necesarios para realizar la inserción de las matrículas a aperturar.
	 * @param as_idUsuario Usuario encargado de ejecutar el proceso.
	 * @param as_remoteIp de as remote ip
	 * @return Objeto de tipo  RegistroCalificacion que contiene los resultados del proceso de inserción de matrículas.
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public synchronized RegistroCalificacion insertarMatriculasSegregacion(
	    RegistroCalificacion arc_data, String as_idUsuario, String as_remoteIp
	)
	    throws B2BException
	{
		DAOManager           ldm_manager;
		RegistroCalificacion lrc_rc;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		lrc_rc          = new RegistroCalificacion();

		try
		{
			if(arc_data != null)
			{
				Collection<MatriculaSegregacion> lcms_cms;

				lcms_cms = arc_data.getInfoMatriculasSegregacion();

				if(CollectionUtils.isValidCollection(lcms_cms))
				{
					MatriculaSegregacionDAO lmsd_DAO;
					String                  ls_idSolicitud;
					MatriculaSegregacion    lms_ms;

					lmsd_DAO           = DaoCreator.getMatriculaSegregacionDAO(ldm_manager);
					ls_idSolicitud     = arc_data.getIdSolicitud();
					lms_ms             = new MatriculaSegregacion();

					lms_ms.setIdSolicitud(ls_idSolicitud);
					lms_ms.setIdCirculoMatriz(arc_data.getIdCirculo());
					lms_ms.setMatriculaMatriz(arc_data.getIdMatricula());

					lmsd_DAO.deleteBySolicitudCirculoMatricula(lms_ms);

					for(MatriculaSegregacion lms_tmp : lcms_cms)
						if(lms_tmp != null)
						{
							lms_tmp.setIdSolicitud(ls_idSolicitud);
							lms_tmp.setIpCreacion(as_remoteIp);

							lmsd_DAO.insert(lms_tmp, as_idUsuario);
						}
				}
				else
					throw new B2BException(ErrorKeys.ERROR_INTERNO_SISTEMA);
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("insertarMatriculasSegregacion", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lrc_rc;
	}

	/**
	 * Método encargado de marcar las anotaciones que han sido corregidas.
	 *
	 * @param aap_anotacion Objeto que contiene la información de la anotación.
	 * @param aca_anotaciones Colección de anotaciones a modificar.
	 * @param as_userId Variable que contiene el id del usuario.
	 * @param as_localIp Variable que contiene la ip del usuario.
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void marcarAnotacionesCorreccion(
	    com.bachue.snr.prosnr01.model.sdb.acc.AnotacionPredio aap_anotacion, Collection<Anotacion> aca_anotaciones,
	    String as_userId, String as_localIp
	)
	    throws B2BException
	{
		DAOManager ldm_manager;

		ldm_manager = DaoManagerFactory.getDAOManager();

		try
		{
			if(CollectionUtils.isValidCollection(aca_anotaciones))
			{
				AnotacionPredioDAO lap_DAO;

				lap_DAO = DaoCreator.getAccAnotacionPredioDAO(ldm_manager);

				for(Anotacion la_iterador : aca_anotaciones)
				{
					if((la_iterador != null) && la_iterador.isCorreccion())
					{
						aap_anotacion.setIdAnotacion(NumericUtils.getLongWrapper(la_iterador.getIdAnotacion()));
						aap_anotacion.setIdUsuarioModificacion(as_userId);
						aap_anotacion.setIpModificacion(as_localIp);

						lap_DAO.updateAnotacionCorreccion(aap_anotacion);
					}
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("marcarAnotacionesCorreccion", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}
	}

	/**
	 * Método encargado de guardar toda la información referente al proceso de nota devolutiva.
	 *
	 * @param atc_tc Objeto de tipo TipoCausal que contiene los tipos de causales seleccionados.
	 * @param ab_accion  Variable de tipo boolean, si es true inserta nueva etapa de trazabilidad de lo contrario no lo hace.
	 * @param as_observaciones  Variable de tipo String que contiene las observaciones del proceso.
	 * @param as_userId de as user id
	 * @param as_remoteIp Variable de tipo String que contiene la dirección ip remota desde donde se realiza la acción.
	 * @return Retorna  un objeto de tipo TipoCausal que coincide con los parametros de entrada.
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public synchronized TipoCausal notaDevolutiva(
	    TipoCausal atc_tc, boolean ab_accion, String as_observaciones, String as_userId, String as_remoteIp
	)
	    throws B2BException
	{
		DAOManager ldm_manager;
		TipoCausal ldp_datos;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		ldp_datos       = null;

		try
		{
			if(atc_tc != null)
			{
				TurnoHistoriaDAO                 lothd_thd;
				TurnoHistoria                    lth_turnoHistoria;
				Long                             ll_idTurnoHistoria;
				boolean                          lb_indVinculado;
				Collection<RegistroCalificacion> lcrc_crc;
				String                           ls_tmp;

				lth_turnoHistoria      = new TurnoHistoria();
				lothd_thd              = DaoCreator.getTurnoHistoriaDAO(ldm_manager);
				lb_indVinculado        = atc_tc.isIndVinculacion();
				lcrc_crc               = atc_tc.getInfoTurnos();
				ll_idTurnoHistoria     = null;

				ls_tmp = atc_tc.getIdTurno();

				if(atc_tc.isCausalesVinculacion())
				{
					TurnoHistoria loth_detalle;
					loth_detalle = new TurnoHistoria();

					loth_detalle.setIdTurno(ls_tmp);
					loth_detalle = lothd_thd.findByIdTurno(loth_detalle, true);

					if(loth_detalle != null)
						ls_tmp = loth_detalle.getIdTurnoHistoria().toString();
				}

				if(!lb_indVinculado)
				{
					lth_turnoHistoria.setIdTurnoHistoria(NumericUtils.getLongWrapper(ls_tmp));
					ll_idTurnoHistoria = lth_turnoHistoria.getIdTurnoHistoria();
				}

				lth_turnoHistoria = lothd_thd.findById(lth_turnoHistoria);

				if(!lb_indVinculado && (lth_turnoHistoria != null))
				{
					Collection<TipoCausal> lltc_ltc;
					lltc_ltc = atc_tc.getListTiposCausales();

					if(CollectionUtils.isValidCollection(lltc_ltc))
					{
						boolean lb_b;

						lb_b = false;

						for(TipoCausal lotc_otc : lltc_ltc)
						{
							if(lotc_otc.isSeleccionado())
							{
								int li_secuence;

								lb_b            = true;
								li_secuence     = lothd_thd.findSecuenceNotaDevolutiva();

								if((li_secuence > 0))
								{
									lotc_otc.setSecuence(StringUtils.getString(li_secuence));

									lotc_otc.setUserId(as_userId);
									lothd_thd.saveTipoCausal(lotc_otc, lth_turnoHistoria);
								}
								else
									throw new B2BException(ErrorKeys.ERROR_SIN_SECUENCIA_NOTA_DEVOLUTIVA);
							}
						}

						if(!lb_b && !StringUtils.isValidString(atc_tc.getObservaciones()))
							throw new B2BException(ErrorKeys.DEBE_SELECCIONAR_TIPO_CAUSAL);
					}
				}

				if(!ab_accion)
				{
					Collection<TurnoHistoria> lcth_cth;
					TurnoHistoria             lth_dataTurn;
					long                      ll_idMotivo;

					lcth_cth         = new ArrayList<TurnoHistoria>();
					lth_dataTurn     = new TurnoHistoria();
					ll_idMotivo      = NumericUtils.getLong(atc_tc.getIdMotivo());

					if(NumericUtils.isValidLong(ll_idTurnoHistoria))
					{
						lth_dataTurn.setIdTurnoHistoria(ll_idTurnoHistoria);
						lcth_cth.add(lth_dataTurn);
					}

					if(lb_indVinculado && CollectionUtils.isValidCollection(lcrc_crc))
					{
						for(RegistroCalificacion lrc_tmp : lcrc_crc)
						{
							if(lrc_tmp != null)
							{
								TurnoHistoria loth_detalle;
								loth_detalle = new TurnoHistoria();

								loth_detalle.setIdTurno(lrc_tmp.getTurno());
								loth_detalle = lothd_thd.findByIdTurno(loth_detalle, true);

								if(loth_detalle != null)
								{
									lth_dataTurn = new TurnoHistoria();
									lth_dataTurn.setIdTurnoHistoria(loth_detalle.getIdTurnoHistoria());
									lcth_cth.add(lth_dataTurn);
								}
							}
						}
					}

					for(TurnoHistoria lth_tmp : lcth_cth)
					{
						if(lth_tmp != null)
						{
							lth_dataTurn = new TurnoHistoria();
							lth_dataTurn.setIdTurnoHistoria(lth_tmp.getIdTurnoHistoria());

							lth_dataTurn = lothd_thd.findById(lth_dataTurn);

							if(lth_dataTurn != null)
							{
								MotivoTramite lmt_motivo;

								ll_idTurnoHistoria = lth_dataTurn.getIdTurnoHistoria();

								if(atc_tc.isCancelacion())
								{
									RegistroCalificacion lorc_rc;
									lorc_rc = new RegistroCalificacion();

									lorc_rc.setSalvar(true);
									lorc_rc.setIdTurnoHistoria(ll_idTurnoHistoria);
									lorc_rc.setUserId(as_userId);
									lorc_rc.setNotaDevolutiva(true);

									getRegistroCalificacionBusiness()
										    .genereteFileCancelacion(
										    ldm_manager, lorc_rc, false, as_userId, as_remoteIp
										);

									lorc_rc.setNotaDevolutivaMedidaCautelar(true);
									lorc_rc.setNotaDevolutiva(false);
									lorc_rc.setReferencia(atc_tc.getReferencia());
									lorc_rc.setNumeroProceso(atc_tc.getNumeroProceso());
									lorc_rc.setOficinaOrigenMedidaCautelar(atc_tc.getOficinaOrigenMedidaCautelar());

									getRegistroCalificacionBusiness()
										    .genereteFileCancelacion(
										    ldm_manager, lorc_rc, false, as_userId, as_remoteIp
										);

									ll_idMotivo = MotivoTramiteCommon.CANCELACION_NOTA_DEVOLUTIVA;
								}
								else if(atc_tc.isMedidaCautelar())
								{
									RegistroCalificacion lorc_rc;
									lorc_rc = new RegistroCalificacion();

									lorc_rc.setSalvar(true);
									lorc_rc.setIdTurnoHistoria(ll_idTurnoHistoria);
									lorc_rc.setUserId(as_userId);
									lorc_rc.setNotaDevolutivaMedidaCautelar(true);
									lorc_rc.setReferencia(atc_tc.getReferencia());
									lorc_rc.setNumeroProceso(atc_tc.getNumeroProceso());
									lorc_rc.setOficinaOrigenMedidaCautelar(atc_tc.getOficinaOrigenMedidaCautelar());

									getRegistroCalificacionBusiness()
										    .genereteFileCancelacion(
										    ldm_manager, lorc_rc, false, as_userId, as_remoteIp
										);

									ll_idMotivo = MotivoTramiteCommon.NOTA_DEVOLUTIVA_MEDIDA_CAUTELAR;
								}

								if(ll_idMotivo > 0)
								{
									lmt_motivo = new MotivoTramite();

									lmt_motivo.setIdMotivo(ll_idMotivo);
									lmt_motivo.setIdEtapaOrigen(EtapaCommon.ID_ETAPA_CALIFICACION);

									lmt_motivo = DaoCreator.getMotivoTramiteDAO(ldm_manager).findById(lmt_motivo);

									if(lmt_motivo != null)
									{
										{
											String ls_estadoActividad;

											ls_estadoActividad = lth_dataTurn.getEstadoActividad();

											if(
											    StringUtils.isValidString(ls_estadoActividad)
												    && ls_estadoActividad.equalsIgnoreCase(EstadoCommon.TERMINADA)
											)
												throw new B2BException(ErrorKeys.NO_SE_PUEDE_TERMINAR_TURNO_HIST_ASG);
										}

										lth_dataTurn.setEstadoActividad(EstadoCommon.TERMINADA);
										lth_dataTurn.setMotivo(lmt_motivo.getDescripcion());
										lth_dataTurn.setIdMotivo(NumericUtils.getLongWrapper(lmt_motivo.getIdMotivo()));
										lth_dataTurn.setObservaciones(as_observaciones);
										lth_dataTurn.setIdUsuarioModificacion(as_userId);
										lth_dataTurn.setIpModificacion(as_remoteIp);

										lothd_thd.insertOrUpdate(lth_dataTurn, false);

										DaoCreator.getProcedimientosDAO(ldm_manager).spCreateStage(lth_dataTurn);
									}
								}
							}
							else
								throw new B2BException(ErrorKeys.ERROR_SIN_DATOS_ETAPA_80);
						}
					}
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("notaDevolutiva", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return ldp_datos;
	}

	/**
	 * Método encargado de  consultar el detalle de un registro  de trazabilidad para un id turno historia determinado.
	 *
	 * @param as_idTurnoHistoria Variable de tipo String que contiene un id turno historia determinado.
	 * @return Retorna  una variable de tipo BigDecimal que coincide con los parametros de entrada.
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public synchronized BigDecimal obtenerEtapaActual(String as_idTurnoHistoria)
	    throws B2BException
	{
		BigDecimal lbd_result;
		DAOManager ldm_manager;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		lbd_result      = null;

		try
		{
			TurnoHistoriaDAO lth_DAO;
			lth_DAO = DaoCreator.getTurnoHistoriaDAO(ldm_manager);

			TurnoHistoria lth_turnoHistoria;
			lth_turnoHistoria = new TurnoHistoria();

			lth_turnoHistoria.setIdTurnoHistoria(NumericUtils.getLongWrapper(as_idTurnoHistoria));

			lth_turnoHistoria = lth_DAO.findById(lth_turnoHistoria);

			if(lth_turnoHistoria != null)
				lbd_result = lth_turnoHistoria.getIdEtapa();
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("obtenerEtapaActual", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lbd_result;
	}

	/**
	 *  Método encargado de  consultar el detalle de un registro  de trazabilidad para un id turno historia determinado.
	 *
	 * @param as_idTurnoHistoria Variable de tipo String que contiene un id turno historia determinado.
	 * @return Retorna  una variable de tipo BigDecimal que coincide con los parametros de entrada.
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public synchronized BigDecimal obtenerEtapaAnterior(String as_idTurnoHistoria)
	    throws B2BException
	{
		BigDecimal lbd_result;
		DAOManager ldm_manager;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		lbd_result      = null;

		try
		{
			TurnoHistoriaDAO lth_DAO;
			TurnoHistoria    lth_turnoHistoria;
			TurnoHistoria    lth_temp;

			lth_DAO               = DaoCreator.getTurnoHistoriaDAO(ldm_manager);
			lth_turnoHistoria     = new TurnoHistoria();
			lth_temp              = new TurnoHistoria();

			lth_turnoHistoria.setIdTurnoHistoria(NumericUtils.getLongWrapper(as_idTurnoHistoria));

			lth_turnoHistoria = lth_DAO.findById(lth_turnoHistoria);

			if(lth_turnoHistoria != null)
			{
				lth_temp = lth_DAO.findByIdAnterior(lth_turnoHistoria);

				if(lth_temp != null)
					lbd_result = lth_temp.getIdEtapa();
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("obtenerEtapaAnterior", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lbd_result;
	}

	/**
	 * Busca los datos de antiguo sistema con el turno historia de la etapa actual,
	 * sólo si la etapa anterior era 101 y se encuentra en estado actividad terminada.
	 *
	 * @param as_idTurnoHistoria id turno historia para la búsqueda
	 * @return Objeto con los datos de antiguo sistema de la etapa anterior
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public synchronized DatosAntSistema obtenerInformacionAntiguoSistema(String as_idTurnoHistoria)
	    throws B2BException
	{
		DAOManager      ldm_manager;
		DatosAntSistema lccas_return;

		ldm_manager      = DaoManagerFactory.getDAOManager();
		lccas_return     = null;

		try
		{
			if(StringUtils.isValidString(as_idTurnoHistoria))
			{
				TurnoHistoriaDAO lth_DAO;
				TurnoHistoria    lth_turnoHistoria;

				lth_DAO               = DaoCreator.getTurnoHistoriaDAO(ldm_manager);
				lth_turnoHistoria     = new TurnoHistoria();

				lth_turnoHistoria.setIdTurnoHistoria(NumericUtils.getLongWrapper(as_idTurnoHistoria));

				lth_turnoHistoria = lth_DAO.findById(lth_turnoHistoria);

				if(lth_turnoHistoria != null)
				{
					TurnoHistoria lth_turnoAnterior;
					lth_turnoAnterior = new TurnoHistoria();

					Long ll_idAnterior;
					ll_idAnterior = NumericUtils.getLongWrapper(lth_turnoHistoria.getIdAnterior());

					lth_turnoAnterior.setIdTurnoHistoria(ll_idAnterior);

					lth_turnoAnterior = lth_DAO.findById(lth_turnoAnterior);

					if(lth_turnoAnterior != null)
					{
						BigDecimal lbd_etapa;
						String     ls_estadoActividad;

						lbd_etapa              = lth_turnoAnterior.getIdEtapa();
						ls_estadoActividad     = lth_turnoAnterior.getEstadoActividad();

						if(
						    NumericUtils.isValidBigDecimal(lbd_etapa) && (lbd_etapa.intValue() == 110)
							    && StringUtils.isValidString(ls_estadoActividad)
							    && ls_estadoActividad.equals(EstadoCommon.TERMINADA)
						)
						{
							TurnoHistoria lth_turno100;
							lth_turno100     = new TurnoHistoria();

							ll_idAnterior = NumericUtils.getLongWrapper(lth_turnoAnterior.getIdAnterior());

							lth_turno100.setIdTurnoHistoria(ll_idAnterior);

							lth_turno100 = lth_DAO.findById(lth_turno100);

							if(lth_turno100 != null)
							{
								lbd_etapa              = lth_turno100.getIdEtapa();
								ls_estadoActividad     = lth_turno100.getEstadoActividad();

								if(
								    NumericUtils.isValidBigDecimal(lbd_etapa) && (lbd_etapa.intValue() == 101)
									    && StringUtils.isValidString(ls_estadoActividad)
									    && ls_estadoActividad.equals(EstadoCommon.TERMINADA)
								)
								{
									AccConsultaCriterioAntiguoSistema lccas_consultaCriterio;
									lccas_consultaCriterio = new AccConsultaCriterioAntiguoSistema();

									lccas_consultaCriterio.setIdTurnoHistoria(ll_idAnterior);

									lccas_consultaCriterio = DaoCreator.getAccConsultaCriteriosAntSistemaDAO(
										    ldm_manager
										).findByIdTurnoHistoria(lccas_consultaCriterio);

									if(lccas_consultaCriterio != null)
									{
										lccas_return = new DatosAntSistema();

										lccas_return.setIdDatosAntSistema(
										    lccas_consultaCriterio.getIdDatosAntSistema()
										);

										lccas_return = DaoCreator.getDatosAntSistemaDAO(ldm_manager)
												                     .findById(lccas_return);
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

			clh_LOGGER.error("obtenerInformacionAntiguoSistema", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lccas_return;
	}

	/**
	 * Método encargado de guardar la consulta por criterios de anotación de antiguo sistema.
	 *
	 * @param accc_consultaCriteriosCalificacion Objeto de tipo ConsultaCriteriosCalificacion que contiene los datos solicitados para guardar la información del proceso antiguo sistema.
	 * @param as_userId Variable de tipo String que contiene el usuario que realiza la acción.
	 * @param as_remoteIp Variable de tipo String que contiene la dirección ip remota desde donde seque realiza la acción.
	 * @return Retorna  una variable de tipo boolean , si es true el proceso termino sin errores, de lo contrario retorna false.
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public synchronized boolean salvarAntSistema(
	    ConsultaCriteriosCalificacion accc_consultaCriteriosCalificacion, String as_userId, String as_remoteIp
	)
	    throws B2BException
	{
		DAOManager ldm_manager;
		boolean    lb_return;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		lb_return       = false;

		try
		{
			if(!StringUtils.isValidString(as_userId))
				throw new B2BException(ErrorKeys.USUARIO_INVALIDO);

			if(accc_consultaCriteriosCalificacion != null)
			{
				AnotacionPredio lap_anotacionPredioDocumento;
				AnotacionPredio lap_anotacionPredioAntSistema;
				TurnoHistoria   lth_tHistoria;
				DatosAntSistema ldas_datosAntSistema;
				Documento       ld_documento;

				lap_anotacionPredioDocumento      = accc_consultaCriteriosCalificacion.getAnotacionPredioDocumento();
				lap_anotacionPredioAntSistema     = accc_consultaCriteriosCalificacion.getAnotacionPredioAntSistema();
				lth_tHistoria                     = accc_consultaCriteriosCalificacion.getTurnoHistoria();
				ldas_datosAntSistema              = accc_consultaCriteriosCalificacion.getDatosAntSistema();
				ld_documento                      = accc_consultaCriteriosCalificacion.getDocumento();

				String ls_idCirculoDocumento      = null;
				String ls_idCirculoAntSistema     = null;
				String ls_idCirculo               = null;
				String ls_idDocumento             = null;
				String ls_idDatosAntSistema       = null;
				Long   ll_idAnotacion             = null;
				Long   ll_idMatriculaDoc          = null;
				Long   ll_idMatricula             = null;
				Long   ll_idMatriculaAntSistema   = null;
				Long   ll_idTurnoHistoria         = null;

				accc_consultaCriteriosCalificacion.setIdUsuario(as_userId);
				accc_consultaCriteriosCalificacion.setIdUsuarioCreacion(as_userId);
				accc_consultaCriteriosCalificacion.setIpCreacion(as_remoteIp);

				if((lap_anotacionPredioDocumento != null) && (ld_documento != null))
				{
					ls_idCirculoDocumento = lap_anotacionPredioDocumento.getIdCirculo();

					if(!StringUtils.isValidString(ls_idCirculoDocumento))
						throw new B2BException(ErrorKeys.DEBE_SELECCIONAR_CIRCULO_REGISTRAL);
				}

				if((lap_anotacionPredioAntSistema != null) && (ldas_datosAntSistema != null))
				{
					ll_idMatriculaAntSistema = lap_anotacionPredioAntSistema.getIdMatricula();

					if(!NumericUtils.isValidLong(ll_idMatriculaAntSistema))
						throw new B2BException(ErrorKeys.DEBE_DIGITAR_MATRICULA);
				}

				if(lth_tHistoria != null)
				{
					ll_idTurnoHistoria = lth_tHistoria.getIdTurnoHistoria();

					if(!NumericUtils.isValidLong(ll_idTurnoHistoria))
						throw new B2BException(ErrorKeys.NO_SE_ENCONTRO_ID_TURNO_HISTORIA);
				}

				if(ldas_datosAntSistema != null)
				{
					ls_idDatosAntSistema = ldas_datosAntSistema.getIdDatosAntSistema();

					if(!StringUtils.isValidString(ls_idDatosAntSistema))
					{
						DatosAntSistemaDAO ldas_DAO;
						int                li_secuencia;
						UtilDAO            lud_DAO;

						ldas_DAO         = DaoCreator.getDatosAntSistemaDAO(ldm_manager);
						lud_DAO          = DaoCreator.getUtilDAO(ldm_manager);
						li_secuencia     = lud_DAO.findSecuence(ConsultasUtilidades.CS_ACC_DATOS_ANT_SISTEMA_SEC);

						ldas_datosAntSistema.setIdDatosAntSistema(StringUtils.getString(li_secuencia));
						ldas_datosAntSistema.setIdUsuarioCreacion(as_userId);
						ldas_datosAntSistema.setIpCreacion(as_remoteIp);
						ldas_DAO.insertOrUpdate(ldas_datosAntSistema, true);

						ls_idDatosAntSistema = ldas_datosAntSistema.getIdDatosAntSistema();
					}

					ls_idCirculoAntSistema = ldas_datosAntSistema.getIdCirculo();

					if(!StringUtils.isValidString(ls_idCirculoAntSistema))
						throw new B2BException(ErrorKeys.DEBE_SELECCIONAR_CIRCULO_REGISTRAL);
				}

				if(ld_documento != null)
				{
					ls_idDocumento = ld_documento.getIdDocumento();

					if(!StringUtils.isValidString(ls_idDocumento))
					{
						DocumentoDAO ld_DAO;
						int          li_secuencia;

						ld_DAO           = DaoCreator.getDocumentoDAO(ldm_manager);
						li_secuencia     = ld_DAO.findSecuencia();

						ld_documento.setVersion(
						    obtenerVersionMaximaOficinaOrigen(ld_documento.getIdOficinaOrigen(), ldm_manager)
						);
						ld_documento.setIdUsuarioCreacion(as_userId);
						ld_documento.setIpCreacion(as_remoteIp);
						ld_documento.setIdDocumento(StringUtils.getString(li_secuencia));
						ld_DAO.insertOrUpdate(ld_documento, true);

						ls_idDocumento = ld_documento.getIdDocumento();
					}
				}

				if(
				    StringUtils.isValidString(ls_idCirculoAntSistema)
					    && StringUtils.isValidString(ls_idCirculoDocumento)
				)
				{
					if(ls_idCirculoAntSistema.equalsIgnoreCase(ls_idCirculoDocumento))
						ls_idCirculo = ls_idCirculoAntSistema;
				}
				else
				{
					if(StringUtils.isValidString(ls_idCirculoDocumento))
						ls_idCirculo = ls_idCirculoDocumento;

					if(StringUtils.isValidString(ls_idCirculoAntSistema))
						ls_idCirculo = ls_idCirculoAntSistema;
				}

				if(NumericUtils.isValidLong(ll_idMatriculaAntSistema) && NumericUtils.isValidLong(ll_idMatriculaDoc))
				{
					if(ll_idMatriculaAntSistema.compareTo(ll_idMatriculaDoc) == 0)
						ll_idMatricula = ll_idMatriculaAntSistema;
				}
				else
				{
					if(NumericUtils.isValidLong(ll_idMatriculaAntSistema))
						ll_idMatricula = ll_idMatriculaAntSistema;

					if(NumericUtils.isValidLong(ll_idMatriculaDoc))
						ll_idMatricula = ll_idMatriculaDoc;
				}

				AccConsultaCriteriosAntSistemaDAO accas_dao;
				accas_dao = DaoCreator.getAccConsultaCriteriosAntSistemaDAO(ldm_manager);

				int li_secuencia;
				li_secuencia = accas_dao.findSecuencia();

				if(li_secuencia > 0)
				{
					AccConsultaCriterioAntiguoSistema laccas_accConsultaCriterioAntiguoSistema;

					laccas_accConsultaCriterioAntiguoSistema = new AccConsultaCriterioAntiguoSistema();
					laccas_accConsultaCriterioAntiguoSistema.setIdCriterioAntSistema(
					    StringUtils.getString(li_secuencia)
					);
					laccas_accConsultaCriterioAntiguoSistema.setIdTurnoHistoria(ll_idTurnoHistoria);
					laccas_accConsultaCriterioAntiguoSistema.setIdDocumento(ls_idDocumento);
					laccas_accConsultaCriterioAntiguoSistema.setIdDatosAntSistema(ls_idDatosAntSistema);
					laccas_accConsultaCriterioAntiguoSistema.setIdAnotacion(ll_idAnotacion);
					laccas_accConsultaCriterioAntiguoSistema.setIdCirculo(ls_idCirculo);
					laccas_accConsultaCriterioAntiguoSistema.setIdMatricula(ll_idMatricula);
					laccas_accConsultaCriterioAntiguoSistema.setIdUsuarioCreacion(as_userId);
					laccas_accConsultaCriterioAntiguoSistema.setIpCreacion(as_remoteIp);

					accas_dao.insert(laccas_accConsultaCriterioAntiguoSistema);

					{
						MotivoTramite    lmt_motivo;
						MotivoTramiteDAO lmtd_DAO;
						TurnoHistoriaDAO lthd_DAO;

						TurnoHistoria lth_turnoHistoria;
						long          ll_idMotivo;

						lmt_motivo            = new MotivoTramite();
						lmtd_DAO              = DaoCreator.getMotivoTramiteDAO(ldm_manager);
						lthd_DAO              = DaoCreator.getTurnoHistoriaDAO(ldm_manager);
						lth_turnoHistoria     = lthd_DAO.findById(lth_tHistoria);
						ll_idMotivo           = MotivoTramiteCommon.ANTIGUO_SISTEMA;

						lmt_motivo.setIdEtapaOrigen(EtapaCommon.ID_ETAPA_CALIFICACION);
						lmt_motivo.setIdMotivo(ll_idMotivo);

						lmt_motivo = lmtd_DAO.findById(lmt_motivo);

						if(lmt_motivo != null)
						{
							{
								String ls_estadoActividad;

								ls_estadoActividad = lth_turnoHistoria.getEstadoActividad();

								if(
								    StringUtils.isValidString(ls_estadoActividad)
									    && !ls_estadoActividad.equalsIgnoreCase(EstadoCommon.ASIGNADA)
								)
									throw new B2BException(ErrorKeys.NO_SE_PUEDE_TERMINAR_TURNO_HIST_ASG);
							}

							lth_turnoHistoria.setEstadoActividad(EstadoCommon.TERMINADA);
							lth_turnoHistoria.setMotivo(lmt_motivo.getDescripcion());
							lth_turnoHistoria.setIdMotivo(NumericUtils.getLongWrapper(lmt_motivo.getIdMotivo()));
							lth_turnoHistoria.setObservaciones(lth_tHistoria.getObservaciones());
							lth_turnoHistoria.setIdUsuarioModificacion(as_userId);
							lth_turnoHistoria.setIpModificacion(as_remoteIp);

							lthd_DAO.insertOrUpdate(lth_turnoHistoria, false);

							DaoCreator.getProcedimientosDAO(ldm_manager).spCreateStage(lth_turnoHistoria);
						}
					}

					lb_return = true;
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("salvarAntSistema", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lb_return;
	}

	/**
	 * Método encargado de guardar y enviar el turno historia a etapa 101.
	 *
	 * @param ath_turnoHistoria Objeto que contiene los datos del turno historia que se va a guardar.
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public synchronized void salvarAntSistemaCalificacion(TurnoHistoria ath_turnoHistoria)
	    throws B2BException
	{
		DAOManager ldm_manager;

		ldm_manager = DaoManagerFactory.getDAOManager();

		try
		{
			MotivoTramite    lmt_motivo;
			MotivoTramiteDAO lmtd_DAO;
			TurnoHistoriaDAO lthd_DAO;

			TurnoHistoria lth_turnoHistoria;

			lmt_motivo            = new MotivoTramite();
			lmtd_DAO              = DaoCreator.getMotivoTramiteDAO(ldm_manager);
			lthd_DAO              = DaoCreator.getTurnoHistoriaDAO(ldm_manager);
			lth_turnoHistoria     = lthd_DAO.findById(ath_turnoHistoria);

			lmt_motivo.setIdEtapaOrigen(ath_turnoHistoria.getIdEtapaSalvar());
			lmt_motivo.setIdMotivo(ath_turnoHistoria.getIdMotivoSalvar());

			lmt_motivo = lmtd_DAO.findById(lmt_motivo);

			if(lmt_motivo != null)
			{
				{
					String ls_estadoActividad;

					ls_estadoActividad = lth_turnoHistoria.getEstadoActividad();

					if(
					    StringUtils.isValidString(ls_estadoActividad)
						    && !ls_estadoActividad.equalsIgnoreCase(EstadoCommon.ASIGNADA)
					)
						throw new B2BException(ErrorKeys.NO_SE_PUEDE_TERMINAR_TURNO_HIST_ASG);
				}

				lth_turnoHistoria.setEstadoActividad(EstadoCommon.TERMINADA);
				lth_turnoHistoria.setMotivo(lmt_motivo.getDescripcion());
				lth_turnoHistoria.setIdMotivo(NumericUtils.getLongWrapper(lmt_motivo.getIdMotivo()));
				lth_turnoHistoria.setObservaciones(ath_turnoHistoria.getObservaciones());
				lth_turnoHistoria.setIdUsuarioModificacion(ath_turnoHistoria.getIdUsuarioModificacion());
				lth_turnoHistoria.setIpModificacion(ath_turnoHistoria.getIpModificacion());

				lthd_DAO.insertOrUpdate(lth_turnoHistoria, false);

				DaoCreator.getProcedimientosDAO(ldm_manager).spCreateStage(lth_turnoHistoria);
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("salvarAntSistemaCalificacion", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}
	}

	/**
	 * Método encargado de salvar cheks seleccionados en la pantalla de permisos para correcciones.
	 *
	 * @param apc_datos Objeto de tipo <code>PermisosCorreccionesUI</code> que contiene cheks a salvar
	 * @param as_userId Variable de tipo String que contiene el usuario que realiza la acción.
	 * @param as_remoteIp Variable de tipo String que contiene la dirección ip remota desde donde se realiza la acción.
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public synchronized void salvarCheksCorrecciones(
	    PermisosCorreccionesUI apc_datos, String as_userId, String as_remoteIp
	)
	    throws B2BException
	{
		DAOManager ldm_manager;

		ldm_manager = DaoManagerFactory.getDAOManager();

		try
		{
			if((apc_datos != null) && StringUtils.isValidString(as_userId) && StringUtils.isValidString(as_remoteIp))
			{
				Long ll_idTurnoHistoria;

				ll_idTurnoHistoria = apc_datos.getIdTurnoHistoria();

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
							Collection<SolicitudCorreccion> lcsc_data;
							Long                            ll_idMatricula;
							String                          ls_idCirculo;
							SolicitudCorreccion             lsc_param;
							SolicitudCorreccionDAO          lscd_DAO;

							lscd_DAO           = DaoCreator.getSolicitudCorreccionDAO(ldm_manager);
							ll_idMatricula     = apc_datos.getIdMatricula();
							ls_idCirculo       = apc_datos.getIdCirculo();
							lsc_param          = new SolicitudCorreccion();

							lsc_param.setIdSolicitud(ls_idSolicitud);
							lsc_param.setIdCirculo(ls_idCirculo);
							lsc_param.setIdMatricula(NumericUtils.getBigInteger(ll_idMatricula));

							lcsc_data = lscd_DAO.findBySolicitudCirculoMatricula(lsc_param, true, false);

							if(CollectionUtils.isValidCollection(lcsc_data))
							{
								SolicitudCamposCorreccion    lscc_datoInsertar;
								SolicitudCamposCorreccionDAO lscc_DAO;
								boolean                      lb_datosAnotacion;
								boolean                      lb_datosDocumentos;
								boolean                      lb_especificacion;
								boolean                      lb_antSistema;
								boolean                      lb_intervinientes;

								lscc_datoInsertar      = new SolicitudCamposCorreccion();
								lscc_DAO               = DaoCreator.getSolicitudCamposCorreccionDAO(ldm_manager);
								lb_datosAnotacion      = false;
								lb_datosDocumentos     = false;
								lb_especificacion      = false;
								lb_antSistema          = false;
								lb_intervinientes      = false;

								lscc_datoInsertar.setIdUsuarioCreacion(as_userId);
								lscc_datoInsertar.setIpCreacion(as_remoteIp);
								lscc_datoInsertar.setIdTurnoHistoria(ll_idTurnoHistoria);
								lscc_datoInsertar.setIdSolicitud(ls_idSolicitud);
								lscc_datoInsertar.setIdCirculo(ls_idCirculo);
								lscc_datoInsertar.setIdMatricula(ll_idMatricula);

								lscc_DAO.delete(lscc_datoInsertar);

								for(SolicitudCorreccion lsc_iterador : lcsc_data)
								{
									if(lsc_iterador != null)
									{
										String ls_idCausal;

										ls_idCausal = StringUtils.getString(lsc_iterador.getIdCausalCorreccion());

										if(StringUtils.isValidString(ls_idCausal))
										{
											Long ll_idCausal;

											ll_idCausal = NumericUtils.getLongWrapper(ls_idCausal);

											if(NumericUtils.isValidLong(ll_idCausal))
											{
												lscc_datoInsertar.setIdSolicitudCorreccion(
												    StringUtils.getString(lsc_iterador.getIdSolicitudCorreccion())
												);
												lscc_datoInsertar.setIdCausalCorreccion(ll_idCausal);
												lscc_datoInsertar.setIdCamposCorreccion(null);
												lscc_datoInsertar.setIdCirculoRelacionado(null);
												lscc_datoInsertar.setIdMatriculaRelacionado(null);

												switch(ls_idCausal)
												{
													case CausalCorreccionCommon.AGREGAR_ANOTACION:
													{
														if(apc_datos.isAgregarAnotacion())
														{
															lscc_datoInsertar.setIdCamposCorreccion(
															    CamposCorreccionCommon.PAA_AGREGAR
															);

															lscc_DAO.insertOrUpdate(lscc_datoInsertar, true);
														}

														break;
													}

													case CausalCorreccionCommon.DATOS_BASICOS:
													{
														PanelUbicacion pl_panel;

														pl_panel = apc_datos.getUbicacion();

														if(pl_panel != null)
														{
															if(pl_panel.isMunicipio())
															{
																lscc_datoInsertar.setIdCamposCorreccion(
																    CamposCorreccionCommon.PU_MUNICIPIO
																);

																lscc_DAO.insertOrUpdate(lscc_datoInsertar, true);
															}

															if(pl_panel.isVereda())
															{
																lscc_datoInsertar.setIdCamposCorreccion(
																    CamposCorreccionCommon.PU_VEREDA
																);

																lscc_DAO.insertOrUpdate(lscc_datoInsertar, true);
															}

															if(pl_panel.isEstadoPredio())
															{
																lscc_datoInsertar.setIdCamposCorreccion(
																    CamposCorreccionCommon.PU_ESTADO_PREDIO
																);

																lscc_DAO.insertOrUpdate(lscc_datoInsertar, true);
															}

															if(pl_panel.isNupre())
															{
																lscc_datoInsertar.setIdCamposCorreccion(
																    CamposCorreccionCommon.PU_NUPRE
																);

																lscc_DAO.insertOrUpdate(lscc_datoInsertar, true);
															}
														}

														break;
													}

													case CausalCorreccionCommon.INFORMACION_DE_APERTURA:
													{
														PanelApertura lp_panel;

														lp_panel = apc_datos.getApertura();

														if(lp_panel != null)
														{
															if(lp_panel.isFechaApertura())
															{
																lscc_datoInsertar.setIdCamposCorreccion(
																    CamposCorreccionCommon.PA_FECHA_APERTURA
																);

																lscc_DAO.insertOrUpdate(lscc_datoInsertar, true);
															}

															if(lp_panel.isRadicacion())
															{
																lscc_datoInsertar.setIdCamposCorreccion(
																    CamposCorreccionCommon.PA_RADICACION
																);

																lscc_DAO.insertOrUpdate(lscc_datoInsertar, true);
															}

															if(lp_panel.isTipoDocumento())
															{
																lscc_datoInsertar.setIdCamposCorreccion(
																    CamposCorreccionCommon.PA_TIPO_DOCUMENTO
																);

																lscc_DAO.insertOrUpdate(lscc_datoInsertar, true);
															}

															if(lp_panel.isNumeroDocumento())
															{
																lscc_datoInsertar.setIdCamposCorreccion(
																    CamposCorreccionCommon.PA_NUMERO_DOCUMENTO
																);

																lscc_DAO.insertOrUpdate(lscc_datoInsertar, true);
															}

															if(lp_panel.isFechaDocumento())
															{
																lscc_datoInsertar.setIdCamposCorreccion(
																    CamposCorreccionCommon.PA_FECHA_DOCUMENTO
																);

																lscc_DAO.insertOrUpdate(lscc_datoInsertar, true);
															}

															if(lp_panel.isTipoOficina())
															{
																lscc_datoInsertar.setIdCamposCorreccion(
																    CamposCorreccionCommon.PA_TIPO_OFICINA
																);

																lscc_DAO.insertOrUpdate(lscc_datoInsertar, true);
															}

															if(lp_panel.isPais())
															{
																lscc_datoInsertar.setIdCamposCorreccion(
																    CamposCorreccionCommon.PA_PAIS
																);

																lscc_DAO.insertOrUpdate(lscc_datoInsertar, true);
															}

															if(lp_panel.isDepartamento())
															{
																lscc_datoInsertar.setIdCamposCorreccion(
																    CamposCorreccionCommon.PA_DEPARTAMENTO
																);

																lscc_DAO.insertOrUpdate(lscc_datoInsertar, true);
															}

															if(lp_panel.isMunicipio())
															{
																lscc_datoInsertar.setIdCamposCorreccion(
																    CamposCorreccionCommon.PA_MUNICIPIO
																);

																lscc_DAO.insertOrUpdate(lscc_datoInsertar, true);
															}

															if(lp_panel.isOficinaOrigen())
															{
																lscc_datoInsertar.setIdCamposCorreccion(
																    CamposCorreccionCommon.PA_OFICINA_ORIGEN
																);

																lscc_DAO.insertOrUpdate(lscc_datoInsertar, true);
															}
														}

														break;
													}

													case CausalCorreccionCommon.MATRICULAS_ABIERTAS_CON_BASE_EN_LAS_SIGUIENTES_MATRICULAS:
													{
														PanelMatriculasAbiertas lp_panel;

														lp_panel = apc_datos.getMatriculasAbiertas();

														if(lp_panel != null)
														{
															Collection<PredioSegregado> lcps_datos;

															lcps_datos = lp_panel.getMatriculas();

															if(CollectionUtils.isValidCollection(lcps_datos))
															{
																for(PredioSegregado lps_iterador : lcps_datos)
																{
																	if(lps_iterador != null)
																	{
																		lscc_datoInsertar.setIdCamposCorreccion(
																		    CamposCorreccionCommon.PMAB_MATRICULAS
																		);
																		lscc_datoInsertar.setIdCirculoRelacionado(
																		    lps_iterador.getIdCirculo()
																		);
																		lscc_datoInsertar.setIdMatriculaRelacionado(
																		    lps_iterador.getIdMatricula()
																		);

																		lscc_DAO.insertOrUpdate(
																		    lscc_datoInsertar, true
																		);
																	}
																}
															}

															if(lp_panel.isAgregar())
															{
																lscc_datoInsertar.setIdCamposCorreccion(
																    CamposCorreccionCommon.PMAB_AGREGAR
																);
																lscc_datoInsertar.setIdCirculoRelacionado(null);
																lscc_datoInsertar.setIdMatriculaRelacionado(null);

																lscc_DAO.insertOrUpdate(lscc_datoInsertar, true);
															}
														}

														break;
													}

													case CausalCorreccionCommon.INFORMACION_CATASTRAL:
													{
														PanelCatastral lp_panel;

														lp_panel = apc_datos.getCatastral();

														if(lp_panel != null)
														{
															if(lp_panel.isCodigoCatastral())
															{
																lscc_datoInsertar.setIdCamposCorreccion(
																    CamposCorreccionCommon.PIC_CODIGO_CATASTRAL
																);

																lscc_DAO.insertOrUpdate(lscc_datoInsertar, true);
															}

															if(lp_panel.isCodigoCatastralAnt())
															{
																lscc_datoInsertar.setIdCamposCorreccion(
																    CamposCorreccionCommon.PIC_CODIGO_CATASTRAL_ANT
																);

																lscc_DAO.insertOrUpdate(lscc_datoInsertar, true);
															}
														}

														break;
													}

													case CausalCorreccionCommon.MATRICULAS_SEGREGADAS:
													{
														PanelMatriculasSegregacion lp_panel;

														lp_panel = apc_datos.getMatriculasSegregacion();

														if(lp_panel != null)
														{
															Collection<PredioSegregado> lcps_datos;

															lcps_datos = lp_panel.getMatriculas();

															if(CollectionUtils.isValidCollection(lcps_datos))
															{
																for(PredioSegregado lps_iterador : lcps_datos)
																{
																	if(lps_iterador != null)
																	{
																		lscc_datoInsertar.setIdCamposCorreccion(
																		    CamposCorreccionCommon.PMS_MATRICULAS_SEGREGADAS
																		);
																		lscc_datoInsertar.setIdCirculoRelacionado(
																		    lps_iterador.getIdCirculo1()
																		);
																		lscc_datoInsertar.setIdMatriculaRelacionado(
																		    lps_iterador.getIdMatricula1()
																		);

																		lscc_DAO.insertOrUpdate(
																		    lscc_datoInsertar, true
																		);
																	}
																}
															}

															if(lp_panel.isAgregarExistente())
															{
																lscc_datoInsertar.setIdCamposCorreccion(
																    CamposCorreccionCommon.PMS_AGREGAR_EXISTENTE
																);
																lscc_datoInsertar.setIdCirculoRelacionado(null);
																lscc_datoInsertar.setIdMatriculaRelacionado(null);

																lscc_DAO.insertOrUpdate(lscc_datoInsertar, true);
															}

															if(lp_panel.isAgregarNueva())
															{
																lscc_datoInsertar.setIdCamposCorreccion(
																    CamposCorreccionCommon.PMS_AGREGAR_NUEVA
																);
																lscc_datoInsertar.setIdCirculoRelacionado(null);
																lscc_datoInsertar.setIdMatriculaRelacionado(null);
																lscc_datoInsertar.setCantidadAperturar(
																    lp_panel.getCantidad()
																);

																lscc_DAO.insertOrUpdate(lscc_datoInsertar, true);
															}
														}

														break;
													}

													case CausalCorreccionCommon.LINDEROS:
													{
														PanelLinderos lp_panel;

														lp_panel = apc_datos.getLinderos();

														if(lp_panel != null)
														{
															if(lp_panel.isLinderos())
															{
																lscc_datoInsertar.setIdCamposCorreccion(
																    CamposCorreccionCommon.PL_LINDEROS
																);

																lscc_DAO.insertOrUpdate(lscc_datoInsertar, true);
															}
														}

														break;
													}

													case CausalCorreccionCommon.COMPLEMENTACION:
													{
														PanelComplementacion lp_panel;

														lp_panel = apc_datos.getComplementacion();

														if(lp_panel != null)
														{
															if(lp_panel.isComplementacion())
															{
																lscc_datoInsertar.setIdCamposCorreccion(
																    CamposCorreccionCommon.PC_COMPLEMENTACION
																);

																lscc_DAO.insertOrUpdate(lscc_datoInsertar, true);
															}
														}

														break;
													}

													case CausalCorreccionCommon.AREA_DEL_PREDIO:
													{
														PanelAreaPredio lp_panel;

														lp_panel = apc_datos.getAreaPredio();

														if(lp_panel != null)
														{
															if(lp_panel.isAreaPrivada())
															{
																lscc_datoInsertar.setIdCamposCorreccion(
																    CamposCorreccionCommon.PAP_AREA_PRIVADA
																);

																lscc_DAO.insertOrUpdate(lscc_datoInsertar, true);
															}

															if(lp_panel.isAreaConstruida())
															{
																lscc_datoInsertar.setIdCamposCorreccion(
																    CamposCorreccionCommon.PAP_AREA_CONSTRUIDA
																);

																lscc_DAO.insertOrUpdate(lscc_datoInsertar, true);
															}

															if(lp_panel.isCoeficiente())
															{
																lscc_datoInsertar.setIdCamposCorreccion(
																    CamposCorreccionCommon.PAP_COEFICIENTE
																);

																lscc_DAO.insertOrUpdate(lscc_datoInsertar, true);
															}

															if(lp_panel.isUsoPredio())
															{
																lscc_datoInsertar.setIdCamposCorreccion(
																    CamposCorreccionCommon.PAP_USO_PREDIO
																);

																lscc_DAO.insertOrUpdate(lscc_datoInsertar, true);
															}

															if(lp_panel.isAreaTerreno())
															{
																lscc_datoInsertar.setIdCamposCorreccion(
																    CamposCorreccionCommon.PAP_AREA_TERRENO
																);

																lscc_DAO.insertOrUpdate(lscc_datoInsertar, true);
															}
														}

														break;
													}

													case CausalCorreccionCommon.DIRECCION_DEL_PREDIO:
													{
														PanelDireccionPredio lp_panel;

														lp_panel = apc_datos.getDireccionPredio();

														if(lp_panel != null)
														{
															Collection<DireccionDelPredio> lcdp_datos;

															lcdp_datos = lp_panel.getDirecciones();

															if(CollectionUtils.isValidCollection(lcdp_datos))
															{
																for(DireccionDelPredio ldp_iterador : lcdp_datos)
																{
																	if(ldp_iterador != null)
																	{
																		DireccionPredio ldp_direccion;

																		ldp_direccion = ldp_iterador
																				.getDireccionPredioBng();

																		if(ldp_direccion != null)
																		{
																			lscc_datoInsertar.setIdCamposCorreccion(
																			    CamposCorreccionCommon.PDP_DIRECCIONES
																			);
																			lscc_datoInsertar.setIdDireccion(
																			    ldp_direccion.getIdDireccion()
																			);

																			lscc_DAO.insertOrUpdate(
																			    lscc_datoInsertar, true
																			);
																		}
																	}
																}
															}

															if(lp_panel.isAgregar())
															{
																lscc_datoInsertar.setIdCamposCorreccion(
																    CamposCorreccionCommon.PDP_AGREGAR
																);

																lscc_DAO.insertOrUpdate(lscc_datoInsertar, true);
															}
														}

														break;
													}

													case CausalCorreccionCommon.DATOS_ANT_SISTEMA:
													{
														PanelAntSistemaSolicitud lp_panel;

														lp_panel = apc_datos.getAntSistema();

														if(lp_panel != null)
														{
															if(lp_panel.isTipoPredio())
															{
																lscc_datoInsertar.setIdCamposCorreccion(
																    CamposCorreccionCommon.PAS_TIPO_PREDIO
																);

																lscc_DAO.insertOrUpdate(lscc_datoInsertar, true);
															}

															if(lp_panel.isNombrePredio())
															{
																lscc_datoInsertar.setIdCamposCorreccion(
																    CamposCorreccionCommon.PAS_NOMBRE_PREDIO
																);

																lscc_DAO.insertOrUpdate(lscc_datoInsertar, true);
															}

															if(lp_panel.isMunicipio())
															{
																lscc_datoInsertar.setIdCamposCorreccion(
																    CamposCorreccionCommon.PAS_MUNICIPIO
																);

																lscc_DAO.insertOrUpdate(lscc_datoInsertar, true);
															}

															if(lp_panel.isCantidadCertificados())
															{
																lscc_datoInsertar.setIdCamposCorreccion(
																    CamposCorreccionCommon.PAS_CANTIDAD_CERTIFICADOS
																);

																lscc_DAO.insertOrUpdate(lscc_datoInsertar, true);
															}
														}

														break;
													}

													case CausalCorreccionCommon.DETALLE_ANT_SISTEMA:
													{
														PanelDetalleAntSistemaSolicitud lp_panel;

														lp_panel = apc_datos.getDetalleAntSistema();

														if(lp_panel != null)
														{
															Collection<DetalleAntSistema> lcdas_datos;

															lcdas_datos = lp_panel.getDetallesAntSistema();

															if(CollectionUtils.isValidCollection(lcdas_datos))
															{
																for(DetalleAntSistema ldas_iterador : lcdas_datos)
																{
																	if(ldas_iterador != null)
																	{
																		lscc_datoInsertar.setIdCamposCorreccion(
																		    CamposCorreccionCommon.PDAS_DETALLE_ANT_SISTEMA
																		);
																		lscc_datoInsertar.setIdDetalleAntSistema(
																		    ldas_iterador.getIdDetalleAntSistema()
																		);

																		lscc_DAO.insertOrUpdate(
																		    lscc_datoInsertar, true
																		);
																	}
																}
															}

															if(lp_panel.isAgregar())
															{
																lscc_datoInsertar.setIdCamposCorreccion(
																    CamposCorreccionCommon.PDAS_AGREGAR
																);

																lscc_DAO.insertOrUpdate(lscc_datoInsertar, true);
															}
														}

														break;
													}

													case CausalCorreccionCommon.DATOS_ANOTACION:
													{
														lb_datosAnotacion = true;

														break;
													}

													case CausalCorreccionCommon.DATOS_DEL_DOCUMENTO:
													{
														lb_datosDocumentos = true;

														break;
													}

													case CausalCorreccionCommon.ESPECIFICACION:
													{
														lb_especificacion = true;

														break;
													}

													case CausalCorreccionCommon.DETALLE_ANT_SISTEMA_ANOTACION:
													{
														lb_antSistema = true;

														break;
													}

													case CausalCorreccionCommon.DATOS_BASICOS_DEL_INTEVINIENTES:
													{
														lb_intervinientes = true;

														break;
													}

													default:
														break;
												}
											}
										}
									}
								}

								if(
								    lb_datosAnotacion || lb_datosDocumentos || lb_antSistema || lb_especificacion
									    || lb_intervinientes
								)
								{
									Collection<Anotacion> lca_anotaciones;

									lca_anotaciones = apc_datos.getAnotacionesAgregadas();

									if(CollectionUtils.isValidCollection(lca_anotaciones))
									{
										lcsc_data = lscd_DAO.findBySolicitudCirculoMatricula(lsc_param, true, true);

										if(CollectionUtils.isValidCollection(lcsc_data))
										{
											for(SolicitudCorreccion lsc_iterador : lcsc_data)
											{
												if(lsc_iterador != null)
												{
													String ls_idCausal;

													ls_idCausal = StringUtils.getString(
														    lsc_iterador.getIdCausalCorreccion()
														);

													if(StringUtils.isValidString(ls_idCausal))
													{
														Long ll_idCausal;

														ll_idCausal = NumericUtils.getLongWrapper(ls_idCausal);

														if(NumericUtils.isValidLong(ll_idCausal))
															lscc_datoInsertar.setIdCausalCorreccion(ll_idCausal);

														lscc_datoInsertar.setIdSolicitudCorreccion(
														    StringUtils.getString(
														        lsc_iterador.getIdSolicitudCorreccion()
														    )
														);

														for(Anotacion la_iterador : lca_anotaciones)
														{
															if(la_iterador != null)
															{
																lscc_datoInsertar.setIdAnotacionRelacionado(
																    la_iterador.getIdAnotacion() + ""
																);

																switch(ls_idCausal)
																{
																	case CausalCorreccionCommon.DATOS_ANOTACION:
																	{
																		PanelDatosAnotacion lp_panel;

																		lp_panel = la_iterador.getPanelDatosAnotaciones();

																		if(lp_panel != null)
																		{
																			if(lp_panel.isNumeroAnotacion())
																			{
																				lscc_datoInsertar.setIdCamposCorreccion(
																				    CamposCorreccionCommon.PDA_NUMERO_ANOTACION
																				);

																				lscc_DAO.insertOrUpdate(
																				    lscc_datoInsertar, true
																				);
																			}

																			if(lp_panel.isFechaAnotacion())
																			{
																				lscc_datoInsertar.setIdCamposCorreccion(
																				    CamposCorreccionCommon.PDA_FECHA_ANOTACION
																				);

																				lscc_DAO.insertOrUpdate(
																				    lscc_datoInsertar, true
																				);
																			}

																			if(lp_panel.isRadicacion())
																			{
																				lscc_datoInsertar.setIdCamposCorreccion(
																				    CamposCorreccionCommon.PDA_RADICACION
																				);

																				lscc_DAO.insertOrUpdate(
																				    lscc_datoInsertar, true
																				);
																			}

																			if(lp_panel.isEstadoAnotacion())
																			{
																				lscc_datoInsertar.setIdCamposCorreccion(
																				    CamposCorreccionCommon.PDA_ESTADO_ANOTACION
																				);

																				lscc_DAO.insertOrUpdate(
																				    lscc_datoInsertar, true
																				);
																			}
																		}

																		break;
																	}

																	case CausalCorreccionCommon.DATOS_DEL_DOCUMENTO:
																	{
																		PanelDatosDocumento lpdd_panel;

																		lpdd_panel = la_iterador.getPanelDatosDocumento();

																		if(lpdd_panel != null)
																		{
																			if(lpdd_panel.isTipoDocumento())
																			{
																				lscc_datoInsertar.setIdCamposCorreccion(
																				    CamposCorreccionCommon.PDD_TIPO_DOCUMENTO
																				);

																				lscc_DAO.insertOrUpdate(
																				    lscc_datoInsertar, true
																				);
																			}

																			if(lpdd_panel.isNumeroDocumento())
																			{
																				lscc_datoInsertar.setIdCamposCorreccion(
																				    CamposCorreccionCommon.PDD_NUMERO_DOCUMENTO
																				);

																				lscc_DAO.insertOrUpdate(
																				    lscc_datoInsertar, true
																				);
																			}

																			if(lpdd_panel.isFechaDocumento())
																			{
																				lscc_datoInsertar.setIdCamposCorreccion(
																				    CamposCorreccionCommon.PDD_FECHA_DOCUMENTO
																				);

																				lscc_DAO.insertOrUpdate(
																				    lscc_datoInsertar, true
																				);
																			}

																			if(lpdd_panel.isTipoOficina())
																			{
																				lscc_datoInsertar.setIdCamposCorreccion(
																				    CamposCorreccionCommon.PDD_TIPO_OFICINA
																				);

																				lscc_DAO.insertOrUpdate(
																				    lscc_datoInsertar, true
																				);
																			}

																			if(lpdd_panel.isPais())
																			{
																				lscc_datoInsertar.setIdCamposCorreccion(
																				    CamposCorreccionCommon.PDD_PAIS
																				);

																				lscc_DAO.insertOrUpdate(
																				    lscc_datoInsertar, true
																				);
																			}

																			if(lpdd_panel.isDepartamento())
																			{
																				lscc_datoInsertar.setIdCamposCorreccion(
																				    CamposCorreccionCommon.PDD_DEPARTAMENTO
																				);

																				lscc_DAO.insertOrUpdate(
																				    lscc_datoInsertar, true
																				);
																			}

																			if(lpdd_panel.isMunicipio())
																			{
																				lscc_datoInsertar.setIdCamposCorreccion(
																				    CamposCorreccionCommon.PDD_MUNICIPIO
																				);

																				lscc_DAO.insertOrUpdate(
																				    lscc_datoInsertar, true
																				);
																			}

																			if(lpdd_panel.isOficinaOrigen())
																			{
																				lscc_datoInsertar.setIdCamposCorreccion(
																				    CamposCorreccionCommon.PDD_OFICINA_ORIGEN
																				);

																				lscc_DAO.insertOrUpdate(
																				    lscc_datoInsertar, true
																				);
																			}
																		}

																		break;
																	}

																	case CausalCorreccionCommon.DETALLE_ANT_SISTEMA_ANOTACION:
																	{
																		PanelDetalleAntSistemaAnotacion lp_panel;

																		lp_panel = la_iterador
																				.getPanelDetalleAntSistemaAnotacion();

																		if(lp_panel != null)
																		{
																			Collection<DetalleAntSistema> lcdas_detalles;

																			lcdas_detalles = la_iterador
																					.getDetallesAntSistema();

																			if(
																			    CollectionUtils.isValidCollection(
																				        lcdas_detalles
																				    )
																			)
																			{
																				Iterator<DetalleAntSistema> lidas_iterator;
																				boolean                     lb_seleccionado;
																				String                      ls_detalleAntSistema;

																				lidas_iterator           = lcdas_detalles
																						.iterator();
																				lb_seleccionado          = false;
																				ls_detalleAntSistema     = null;

																				while(
																				    lidas_iterator.hasNext()
																					    && !lb_seleccionado
																				)
																				{
																					DetalleAntSistema ldas_detalle;

																					ldas_detalle = lidas_iterator.next();

																					if(ldas_detalle != null)
																					{
																						if(
																						    ldas_detalle.isSeleccionado()
																						)
																						{
																							lb_seleccionado          = true;
																							ls_detalleAntSistema     = ldas_detalle
																									.getIdDetalleAntSistema();
																						}
																					}
																				}

																				if(
																				    lb_seleccionado
																					    && StringUtils.isValidString(
																					        ls_detalleAntSistema
																					    )
																				)
																				{
																					lscc_datoInsertar
																						.setIdDetalleAntSistema(
																						    ls_detalleAntSistema
																						);
																					lscc_datoInsertar
																						.setIdCamposCorreccion(
																						    CamposCorreccionCommon.PDDASA_DETALLE_SELECCIONADO
																						);

																					lscc_DAO.insertOrUpdate(
																					    lscc_datoInsertar, true
																					);
																				}
																			}
																		}

																		break;
																	}

																	case CausalCorreccionCommon.ESPECIFICACION:
																	{
																		PanelEspecificacion lpe_panel;

																		lpe_panel = la_iterador.getPanelEspecificacion();

																		if(lpe_panel != null)
																		{
																			if(lpe_panel.isCodigoActo())
																			{
																				lscc_datoInsertar.setIdCamposCorreccion(
																				    CamposCorreccionCommon.PE_CODIGO_ACTO
																				);

																				lscc_DAO.insertOrUpdate(
																				    lscc_datoInsertar, true
																				);
																			}

																			if(lpe_panel.isDescripcionActo())
																			{
																				lscc_datoInsertar.setIdCamposCorreccion(
																				    CamposCorreccionCommon.PE_DESCRIPCION_ACTO
																				);

																				lscc_DAO.insertOrUpdate(
																				    lscc_datoInsertar, true
																				);
																			}

																			if(lpe_panel.isValorActo())
																			{
																				lscc_datoInsertar.setIdCamposCorreccion(
																				    CamposCorreccionCommon.PE_VALOR_ACTO
																				);

																				lscc_DAO.insertOrUpdate(
																				    lscc_datoInsertar, true
																				);
																			}

																			if(lpe_panel.isAnotacionCancela())
																			{
																				lscc_datoInsertar.setIdCamposCorreccion(
																				    CamposCorreccionCommon.PE_ANOTACION_CANCELADA
																				);

																				lscc_DAO.insertOrUpdate(
																				    lscc_datoInsertar, true
																				);
																			}

																			if(lpe_panel.isComentario())
																			{
																				lscc_datoInsertar.setIdCamposCorreccion(
																				    CamposCorreccionCommon.PE_COMENTARIOS
																				);

																				lscc_DAO.insertOrUpdate(
																				    lscc_datoInsertar, true
																				);
																			}
																		}

																		break;
																	}

																	case CausalCorreccionCommon.DATOS_BASICOS_DEL_INTEVINIENTES:
																	{
																		Collection<Anotacion> lca_intervinientes;

																		lca_intervinientes = la_iterador
																				.getIntervinientesAgregados();

																		if(
																		    CollectionUtils.isValidCollection(
																			        lca_intervinientes
																			    )
																		)
																		{
																			{
																				PanelIntervinientes lpi_panel;

																				lpi_panel = la_iterador
																						.getPanelIntervinientes();

																				if(lpi_panel != null)
																				{
																					if(lpi_panel.isAgregar())
																					{
																						lscc_datoInsertar
																							.setIdCamposCorreccion(
																							    CamposCorreccionCommon.PDBI_AGREGAR
																							);

																						lscc_DAO.insertOrUpdate(
																						    lscc_datoInsertar, true
																						);
																					}
																				}
																			}

																			for(Anotacion la_actual : lca_intervinientes)
																			{
																				if(la_actual != null)
																				{
																					Persona                lp_persona;
																					SolicitudInterviniente lsi_solicitudInterviniente;

																					lsi_solicitudInterviniente     = la_actual
																							.getSolicitudInterviniente();
																					lp_persona                     = la_actual
																							.getPersona();

																					if(
																					    (lp_persona != null)
																						    && (lsi_solicitudInterviniente != null)
																					)
																					{
																						String ls_rol;
																						String ls_idPersona;

																						ls_idPersona     = lp_persona
																								.getIdPersona();
																						ls_rol           = lsi_solicitudInterviniente
																								.getRolPersona();

																						if(
																						    StringUtils.isValidString(
																							        ls_idPersona
																							    )
																							    && StringUtils
																							    .isValidString(ls_rol)
																						)
																						{
																							PanelDetalleIntervinientes lp_panel;

																							lscc_datoInsertar
																								.setIdPersona(
																								    ls_idPersona
																								);
																							lscc_datoInsertar
																								.setRolPersona(ls_rol);

																							lp_panel = la_actual
																									.getPanelDetalleIntervinientes();

																							if(lp_panel != null)
																							{
																								if(
																								    lp_panel
																									    .isDatosPersona()
																								)
																								{
																									lscc_datoInsertar
																										.setIdCamposCorreccion(
																										    CamposCorreccionCommon.PDBI_DATOS_PERSONA
																										);

																									lscc_DAO
																										.insertOrUpdate(
																										    lscc_datoInsertar,
																										    true
																										);
																								}

																								if(lp_panel.isRol())
																								{
																									lscc_datoInsertar
																										.setIdCamposCorreccion(
																										    CamposCorreccionCommon.PDBI_ROL
																										);

																									lscc_DAO
																										.insertOrUpdate(
																										    lscc_datoInsertar,
																										    true
																										);
																								}

																								if(
																								    lp_panel
																									    .isPropietario()
																								)
																								{
																									lscc_datoInsertar
																										.setIdCamposCorreccion(
																										    CamposCorreccionCommon.PDBI_PROPIETARIO
																										);

																									lscc_DAO
																										.insertOrUpdate(
																										    lscc_datoInsertar,
																										    true
																										);
																								}

																								if(
																								    lp_panel
																									    .isPorcentaje()
																								)
																								{
																									lscc_datoInsertar
																										.setIdCamposCorreccion(
																										    CamposCorreccionCommon.PDBI_PORCENTAJE
																										);

																									lscc_DAO
																										.insertOrUpdate(
																										    lscc_datoInsertar,
																										    true
																										);
																								}

																								if(
																								    lp_panel
																									    .isCalidadInterviniente()
																								)
																								{
																									lscc_datoInsertar
																										.setIdCamposCorreccion(
																										    CamposCorreccionCommon.PDBI_CALIDAD_INTERVINIENTE
																										);

																									lscc_DAO
																										.insertOrUpdate(
																										    lscc_datoInsertar,
																										    true
																										);
																								}
																							}
																						}
																					}
																				}
																			}
																		}

																		break;
																	}

																	default:
																		break;
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
							else
								throw new B2BException(ErrorKeys.ERROR_SIN_CAUSAL_CORRECCION);
						}
						else
							throw new B2BException(ErrorKeys.ERROR_INTERNO_SISTEMA);

						lth_turnoHistoria.setObservaciones(apc_datos.getObservaciones());
						lth_turnoHistoria.setIdUsuarioModificacion(as_userId);
						lth_turnoHistoria.setIpModificacion(as_remoteIp);

						DaoCreator.getTurnoHistoriaDAO(ldm_manager).updateObservaciones(lth_turnoHistoria);
					}
					else
						throw new B2BException(ErrorKeys.ERROR_INTERNO_SISTEMA);
				}
				else
					throw new B2BException(ErrorKeys.ERROR_INTERNO_SISTEMA);
			}
			else
				throw new B2BException(ErrorKeys.ERROR_INTERNO_SISTEMA);
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("salvarCheksCorrecciones", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}
	}

	/**
	 * Método encargado de guardar  toda la información referente al proceso de confrontación correctiva.
	 *
	 * @param accc_ccc Variable de tipo Collection que contiene los datos de confrontacion correctiva a procesar.
	 * @param as_userId Variable de tipo String que contiene el usuario que realiza la acción.
	 * @param as_remoteIp Variable de tipo String que contiene la dirección ip remota desde donde se realiza la acción.
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public synchronized void salvarConfrontacion(
	    Collection<ConfrontacionCorrectiva> accc_ccc, String as_userId, String as_remoteIp
	)
	    throws B2BException
	{
		DAOManager ldm_manager;

		ldm_manager = DaoManagerFactory.getDAOManager();

		try
		{
			if(CollectionUtils.isValidCollection(accc_ccc))
			{
				DocumentoDAO                      ldDao_DAO;
				EstadoPredioDAO                   lep_DAO;
				PredioRegistroDAO                 lprd_DAO;
				SolicitudDAO                      ls_DAO;
				TmpSolicitudMatriculaDAO          ltsmd_DAO;
				TmpSolicitudMatriculaActoDAO      ltsmad_DAO;
				TurnoHistoriaDAO                  lothd_thd;
				Iterator<ConfrontacionCorrectiva> licc_icc;

				ldDao_DAO      = DaoCreator.getDocumentoDAO(ldm_manager);
				lep_DAO        = DaoCreator.getHTREstadoPredioDAO(ldm_manager);
				lprd_DAO       = DaoCreator.getPredioRegistroDAO(ldm_manager);
				ls_DAO         = DaoCreator.getSolicitudDAO(ldm_manager);
				ltsmd_DAO      = DaoCreator.getTmpSolicitudMatriculaDAO(ldm_manager);
				ltsmad_DAO     = DaoCreator.getTmpSolicitudMatriculaActoDAO(ldm_manager);
				lothd_thd      = DaoCreator.getTurnoHistoriaDAO(ldm_manager);
				licc_icc       = accc_ccc.iterator();

				while(licc_icc.hasNext())
				{
					ConfrontacionCorrectiva lcc_cc;

					lcc_cc = licc_icc.next();

					if(lcc_cc != null)
					{
						Apertura                           la_apertura;
						Calificacion                       lc_calificacion;
						Collection<SolicitudMatricula>     lcsm_datosInsertar;
						Collection<SolicitudMatriculaActo> lcsma_datosActosInsertar;
						Collection<SolicitudMatricula>     lcsm_datosEliminar;
						Collection<SolicitudMatriculaActo> lcsma_datosActosEliminar;
						Collection<PredioRegistro>         lcpr_datosReabrirMatricula;
						Long                               ll_idTurnoHistoria;
						String                             ls_justificacion;
						TurnoHistoria                      lth_dataTurn;

						la_apertura                    = lcc_cc.getApertura();
						lc_calificacion                = lcc_cc.getDataCalificacion();
						lcsm_datosInsertar             = lcc_cc.getDatosBandejaPrediosInsertarp();
						lcsma_datosActosInsertar       = lcc_cc.getDatosBandejaActosInsertarp();
						lcsm_datosEliminar             = lcc_cc.getDatosBandejaPrediosEliminarp();
						lcsma_datosActosEliminar       = lcc_cc.getDatosBandejaActosEliminarp();
						lcpr_datosReabrirMatricula     = lcc_cc.getDatosBandejaReabrirMatriculap();
						ll_idTurnoHistoria             = lcc_cc.getIdTurnoHistoria();
						ls_justificacion               = lcc_cc.getJustificacion();
						lth_dataTurn                   = lothd_thd.findById(new TurnoHistoria(ll_idTurnoHistoria));

						if(lth_dataTurn != null)
						{
							long    ll_idEtapa;
							boolean lb_responsableActuacionesAdmin;
							boolean lb_sustanciadorActuacionesAdmin;

							ll_idEtapa                          = NumericUtils.getLong(lth_dataTurn.getIdEtapa());
							lb_responsableActuacionesAdmin      = ll_idEtapa == EtapaCommon.ID_ETAPA_RESPONSABLE_ACTUACIONES_ADMINISTRATIVAS;
							lb_sustanciadorActuacionesAdmin     = ll_idEtapa == EtapaCommon.ID_ETAPA_SUSTANCIADOR_ACTUACIONES_ADMINISTRATIVAS;

							/**
							 * Seccion de validaciones para la pestaña Datos Basicos
							 */
							if(lc_calificacion.getTabs().containsKey(IdentificadoresCommon.CORREGIR_DATOS_BASICOS))
							{
								if(la_apertura != null)
								{
									Documento ld_documentoApertura;

									ld_documentoApertura = la_apertura.getDocumento();

									if(ld_documentoApertura != null)
									{
										Date   ld_fechaDocumento;
										Date   ld_fechaEjecutoria;
										String ls_idPais;
										String ls_idDepartamento;
										String ls_idMunicipio;
										String ls_idOficinaOrigen;
										String ls_idTipoDocumento;
										String ls_idNumeroDocumento;

										ld_fechaDocumento        = ld_documentoApertura.getFechaDocumento();
										ld_fechaEjecutoria       = ld_documentoApertura.getFechaEjecutoria();
										ls_idPais                = la_apertura.getIdPais();
										ls_idDepartamento        = la_apertura.getIdDepartamento();
										ls_idMunicipio           = la_apertura.getIdMunicipio();
										ls_idOficinaOrigen       = la_apertura.getIdOficinaOrigen();
										ls_idTipoDocumento       = ld_documentoApertura.getIdTipoDocumento();
										ls_idNumeroDocumento     = ld_documentoApertura.getNumero();

										if(!StringUtils.isValidString(ls_idPais))
											throw new B2BException(ErrorKeys.DEBE_SELECCIONAR_PAIS);

										if(!StringUtils.isValidString(ls_idTipoDocumento))
											throw new B2BException(ErrorKeys.DEBE_SELECCIONAR_TIPO);

										if(!StringUtils.isValidString(ls_idNumeroDocumento))
											throw new B2BException(ErrorKeys.DOCUMENTO_INVALIDO);

										if(ld_fechaDocumento == null)
										{
											Object[] aoa_messageArgs = new String[1];
											aoa_messageArgs[0] = " Documento ";
											throw new B2BException(
											    ErrorKeys.ERROR_CAMPO_FECHA_OBLIGATORIO, aoa_messageArgs
											);
										}

										if(ld_fechaDocumento.after(new Date()))
										{
											Object[] aoa_messageArgs = new String[1];
											aoa_messageArgs[0] = " del documento ";
											throw new B2BException(ErrorKeys.FECHA_SUPERIOR_ACTUAL, aoa_messageArgs);
										}

										if(!StringUtils.isValidString(ls_idDepartamento))
										{
											Object[] aoa_messageArgs = new String[1];
											aoa_messageArgs[0] = " un Departamento ";
											throw new B2BException(
											    ErrorKeys.ERROR_SIN_SELECCION_COMBOS, aoa_messageArgs
											);
										}

										if(!StringUtils.isValidString(ls_idMunicipio))
										{
											Object[] aoa_messageArgs = new String[1];
											aoa_messageArgs[0] = " un municipio ";
											throw new B2BException(
											    ErrorKeys.ERROR_SIN_SELECCION_COMBOS, aoa_messageArgs
											);
										}

										if(!StringUtils.isValidString(ls_idOficinaOrigen))
										{
											Object[] aoa_messageArgs = new String[1];
											aoa_messageArgs[0] = " la oficina origen ";
											throw new B2BException(
											    ErrorKeys.ERROR_SIN_SELECCION_COMBOS, aoa_messageArgs
											);
										}

										if((ld_fechaEjecutoria == null) && la_apertura.isEsActoConEjecutoria())
										{
											Object[] aoa_messageArgs = new String[1];
											aoa_messageArgs[0] = " ejecutoria ";
											throw new B2BException(
											    ErrorKeys.ERROR_CAMPO_FECHA_OBLIGATORIO, aoa_messageArgs
											);
										}

										{
											ld_documentoApertura.setNumero(ls_idNumeroDocumento);
											ld_documentoApertura.setIdTipoDocumento(ls_idTipoDocumento);
											ld_documentoApertura.setFechaDocumento(ld_fechaDocumento);
											ld_documentoApertura.setFechaEjecutoria(ld_fechaEjecutoria);
											ld_documentoApertura.setIdPais(ls_idPais);
											ld_documentoApertura.setIdDepartamento(ls_idDepartamento);
											ld_documentoApertura.setIdMunicipio(ls_idMunicipio);
											ld_documentoApertura.setIdOficinaOrigen(ls_idOficinaOrigen);
											ld_documentoApertura.setIdUsuarioCreacion(as_userId);

											{
												Documento ld_documento;
												long      ll_versionDocumento;

												ld_documento            = ldDao_DAO.findById(ld_documentoApertura);
												ll_versionDocumento     = NumericUtils.getLong(
													    ld_documentoApertura.getVersionDocumento()
													);

												if(ld_documento != null)
												{
													ld_documento.setNumero(ld_documentoApertura.getNumero());
													ld_documento.setIdTipoDocumento(
													    ld_documentoApertura.getIdTipoDocumento()
													);
													ld_documento.setFechaDocumento(
													    ld_documentoApertura.getFechaDocumento()
													);
													ld_documento.setFechaEjecutoria(
													    ld_documentoApertura.getFechaEjecutoria()
													);
													ld_documento.setIdPais(ld_documentoApertura.getIdPais());
													ld_documento.setIdDepartamento(
													    ld_documentoApertura.getIdDepartamento()
													);
													ld_documento.setIdMunicipio(ld_documentoApertura.getIdMunicipio());

													ld_documento.setIdOficinaOrigen(ls_idOficinaOrigen);
													ld_documento.setVersion(
													    obtenerVersionMaximaOficinaOrigen(
													        ls_idOficinaOrigen, ldm_manager
													    )
													);

													ld_documento.setVersionDocumento(
													    NumericUtils.getLongWrapper(++ll_versionDocumento)
													);
													ld_documento.setIdUsuarioCreacion(as_userId);
													ld_documento.setIpCreacion(as_remoteIp);

													ldDao_DAO.insertOrUpdate(ld_documento, true);
												}

												ld_documentoApertura = ldDao_DAO.findById(ld_documento);

												if(ld_documentoApertura != null)
												{
													Solicitud ls_s;

													ls_s = new Solicitud();

													ls_s.setIdSolicitud(lth_dataTurn.getIdSolicitud());

													ls_s = ls_DAO.findById(ls_s);

													if(ls_s != null)
													{
														ls_s.setIdDocumento(ld_documentoApertura.getIdDocumento());
														ls_s.setVersionDocumento(
														    ld_documentoApertura.getVersionDocumento()
														);
														ls_s.setIdUsuario(as_userId);
														ls_s.setIdUsuarioModificacion(as_userId);

														if(!StringUtils.isValidString(ls_s.getDerechoPeticion()))
															ls_s.setDerechoPeticion(EstadoCommon.N);

														ls_DAO.insertOrUpdate(ls_s, false);
													}
												}
											}
										}

										la_apertura.setDocumento(ld_documentoApertura);
									}
								}
							}

							/**
							 * Seccion de validaciones para la pestaña Insertar Predios
							 */
							if(lc_calificacion.getTabs().containsKey(IdentificadoresCommon.INSERTAR_MATRICULA))
							{
								if(CollectionUtils.isValidCollection(lcsm_datosInsertar))
								{
									if(
									    (ll_idEtapa == EtapaCommon.ID_ETAPA_CORRECCIONES)
										    || (ll_idEtapa == EtapaCommon.ID_ETAPA_ANALISTA_CERTIFICADOS_ESPECIALES)
										    || (ll_idEtapa == EtapaCommon.ID_ETAPA_460)
										    || lb_responsableActuacionesAdmin || lb_sustanciadorActuacionesAdmin
									)
										salvarPrediosConfrontacionCorrectiva(
										    lcsm_datosInsertar, lcsma_datosActosInsertar, lth_dataTurn, as_userId,
										    ldm_manager, as_remoteIp, true
										);
									else if(CollectionUtils.isValidCollection(lcsma_datosActosInsertar))
										salvarPrediosConfrontacionCorrectiva(
										    lcsm_datosInsertar, lcsma_datosActosInsertar, lth_dataTurn, as_userId,
										    ldm_manager, as_remoteIp, true
										);
								}
							}

							/**
							 * Seccion de validaciones para la pestaña eliminar Predios
							 */
							if(lc_calificacion.getTabs().containsKey(IdentificadoresCommon.ELIMINAR_MATRICULA))
							{
								if(CollectionUtils.isValidCollection(lcsm_datosEliminar))
								{
									if(
									    (ll_idEtapa == EtapaCommon.ID_ETAPA_CORRECCIONES)
										    || (ll_idEtapa == EtapaCommon.ID_ETAPA_ANALISTA_CERTIFICADOS_ESPECIALES)
										    || (ll_idEtapa == EtapaCommon.ID_ETAPA_460)
										    || lb_responsableActuacionesAdmin || lb_sustanciadorActuacionesAdmin
									)
										salvarPrediosConfrontacionCorrectiva(
										    lcsm_datosEliminar, lcsma_datosActosEliminar, lth_dataTurn, as_userId,
										    ldm_manager, as_remoteIp, false
										);
									else if(CollectionUtils.isValidCollection(lcsma_datosActosEliminar))
										salvarPrediosConfrontacionCorrectiva(
										    lcsm_datosEliminar, lcsma_datosActosEliminar, lth_dataTurn, as_userId,
										    ldm_manager, as_remoteIp, false
										);
								}
							}

							/**
							 * Seccion de validaciones para la pestaña Folios
							 */
							if(lc_calificacion.getTabs().containsKey(IdentificadoresCommon.VERIFICAR_FOLIO))
							{
								if(CollectionUtils.isValidCollection(lcpr_datosReabrirMatricula))
								{
									for(PredioRegistro lpr_actual : lcpr_datosReabrirMatricula)
									{
										if(lpr_actual != null)
										{
											PredioRegistro lsm_matriculaExist;

											lsm_matriculaExist = lprd_DAO.findByCirculoMatricula(lpr_actual);

											if(lsm_matriculaExist != null)
											{
												lpr_actual.setIdUsuarioModificacion(as_userId);
												lpr_actual.setIpModificacion(as_remoteIp);

												lprd_DAO.updateIdEstadoPredio(lpr_actual);

												com.bachue.snr.prosnr01.model.sdb.htr.EstadoPredio lep_estadopredio;

												lep_estadopredio = new com.bachue.snr.prosnr01.model.sdb.htr.EstadoPredio();

												lep_estadopredio.setIdCirculo(lpr_actual.getIdCirculo());
												lep_estadopredio.setIdMatricula(
												    NumericUtils.getBigInteger(lpr_actual.getIdMatricula())
												);
												lep_estadopredio.setIdEstadoPredioAnt(
												    lsm_matriculaExist.getIdEstadoPredio()
												);
												lep_estadopredio.setJustificacionCambio(ls_justificacion);
												lep_estadopredio.setIdUsuarioCreacion(as_userId);
												lep_estadopredio.setIpCreacion(as_remoteIp);

												lep_DAO.insert(lep_estadopredio);
											}
										}
									}
								}
							}

							if(
							    (ll_idEtapa != EtapaCommon.ID_ETAPA_CORRECCIONES)
								    && (ll_idEtapa != EtapaCommon.ID_ETAPA_ANALISTA_CERTIFICADOS_ESPECIALES)
								    && (ll_idEtapa != EtapaCommon.ID_ETAPA_460) && !lb_responsableActuacionesAdmin
								    && !lb_sustanciadorActuacionesAdmin
							)
							{
								{
									TmpSolicitudMatricula ltsm_tmp;

									ltsm_tmp = new TmpSolicitudMatricula();
									ltsm_tmp.setIdSolicitud(lth_dataTurn.getIdSolicitud());
									ltsm_tmp.setIdUsuarioModificacion(as_userId);

									ltsmd_DAO.updateAccionTmps(ltsm_tmp);
								}

								{
									TmpSolicitudMatriculaActo ltsma_tmpa;

									ltsma_tmpa = new TmpSolicitudMatriculaActo();
									ltsma_tmpa.setIdSolicitud(lth_dataTurn.getIdSolicitud());
									ltsma_tmpa.setIdUsuarioModificacion(as_userId);

									ltsmad_DAO.updateAccionTmps(ltsma_tmpa);
								}
							}

							boolean lb_esPredioInconsistente;

							lb_esPredioInconsistente = lc_calificacion.isEsPredioInconsistente()
									&& !lb_responsableActuacionesAdmin
									&& !(ll_idEtapa == EtapaCommon.ID_ETAPA_SUSTANCIADOR_ACTUACIONES_ADMINISTRATIVAS);

							if(
							    ((lc_calificacion.getTabs().containsKey(IdentificadoresCommon.VERIFICAR_FOLIO))
								    && (ll_idEtapa == EtapaCommon.ID_ETAPA_CORRECCIONES))
								    || (((ll_idEtapa != EtapaCommon.ID_ETAPA_CORRECCIONES)
								    && (ll_idEtapa != EtapaCommon.ID_ETAPA_460) && !lb_responsableActuacionesAdmin
								    && !(ll_idEtapa == EtapaCommon.ID_ETAPA_ANALISTA_CERTIFICADOS_ESPECIALES))
								    || lb_esPredioInconsistente)
							)
							{
								long          ll_idMotivo;
								MotivoTramite lm_motivoTramite;

								ll_idMotivo     = lb_esPredioInconsistente ? MotivoTramiteCommon.PREDIO_INCONSISTENTE
									                                       : ((ll_idEtapa == EtapaCommon.ID_ETAPA_CORRECCIONES)
									? MotivoTramiteCommon.MODIFICAR_MATRICULAS
									: ((ll_idEtapa == EtapaCommon.ID_ETAPA_SUSTANCIADOR_ACTUACIONES_ADMINISTRATIVAS)
									? MotivoTramiteCommon.ASOCIAR_O_DESASOCIAR_MATRICULAS
									: MotivoTramiteCommon.CONFRONTACION_CORRECTIVA));

								lm_motivoTramite     = new MotivoTramite(ll_idEtapa, ll_idMotivo);

								lm_motivoTramite = DaoCreator.getMotivoTramiteDAO(ldm_manager).findById(
									    lm_motivoTramite
									);

								if(lm_motivoTramite != null)
								{
									{
										String ls_estadoActividad;

										ls_estadoActividad = lth_dataTurn.getEstadoActividad();

										if(
										    StringUtils.isValidString(ls_estadoActividad)
											    && ls_estadoActividad.equalsIgnoreCase(EstadoCommon.TERMINADA)
										)
											throw new B2BException(ErrorKeys.NO_SE_PUEDE_TERMINAR_TURNO_HIST_ASG);
									}

									lth_dataTurn.setEstadoActividad(
									    lb_esPredioInconsistente ? EstadoCommon.TERMINADA
									                             : ((ll_idEtapa == EtapaCommon.ID_ETAPA_ANALISTA_CERTIFICADOS_ESPECIALES)
									    ? EstadoCommon.ASIGNADA : EstadoCommon.TERMINADA)
									);
									lth_dataTurn.setObservaciones(ls_justificacion);

									if(!lb_esPredioInconsistente)
										lth_dataTurn.setIndicadorDevolucion(EstadoCommon.S);

									lth_dataTurn.setMotivo(lm_motivoTramite.getDescripcion());
									lth_dataTurn.setIdMotivo(
									    NumericUtils.getLongWrapper(lm_motivoTramite.getIdMotivo())
									);

									lth_dataTurn.setIdUsuarioModificacion(as_userId);
									lth_dataTurn.setIpModificacion(as_remoteIp);

									lothd_thd.insertOrUpdate(lth_dataTurn, false);

									if(lb_esPredioInconsistente)
										lth_dataTurn.setIdUsuarioAsignacion(as_userId);

									DaoCreator.getProcedimientosDAO(ldm_manager).spCreateStage(lth_dataTurn);
								}
								else
								{
									if(
									    lc_calificacion.getTabs().containsKey(IdentificadoresCommon.VERIFICAR_FOLIO)
										    && (ll_idEtapa == EtapaCommon.ID_ETAPA_CORRECCIONES)
									)
									{
										Object[] aoa_messageArgs = new String[2];
										aoa_messageArgs[0]     = NumericUtils.getLongWrapper(
											    NumericUtils.getLong(EtapaCommon.ID_ETAPA_CORRECCIONES)
											);
										aoa_messageArgs[1]     = NumericUtils.getLongWrapper(
											    MotivoTramiteCommon.MODIFICAR_MATRICULAS
											);

										throw new B2BException(ErrorKeys.ERROR_MOTIVO_INVALIDO, aoa_messageArgs);
									}
									else
									{
										Object[] aoa_messageArgs = new String[2];
										aoa_messageArgs[0]     = NumericUtils.getLongWrapper(ll_idEtapa);
										aoa_messageArgs[1]     = NumericUtils.getLongWrapper(
											    MotivoTramiteCommon.CONFRONTACION_CORRECTIVA
											);

										throw new B2BException(ErrorKeys.ERROR_MOTIVO_INVALIDO, aoa_messageArgs);
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

			clh_LOGGER.error("salvarConfrontacion", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}
	}

	/**
	 * Método encargado de  validar las matriculas revisadas y crear nueva trazabilidad para el proceso de digitador masivo.
	 *
	 * @param ap_ap de ap ap
	 * @param al_idTurnoHistoria Variable de tipo Long que contiene un id turno historia determinado.
	 * @param ls_idTurno Variable de tipo String que contiene un id turno  determinado.
	 * @param ai_totalRevision Variable de tipo int que contiene el número de matrículas revisadas.
	 * @param ai_totalMatriculas Variable de tipo int que contiene el total de matrículas.
	 * @param as_userId Variable de tipo String que contiene el usuario que realiza la acción.
	 * @param as_localIp Variable de tipo String que contiene la dirección ip local desde donde se realiza la acción.
	 * @param as_remoteIp Variable de tipo String que contiene la dirección ip remota desde donde se realiza la acción.
	 * @param as_observaciones Variable de tipo String que contiene las observaciones del proceso.
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public synchronized void salvarDigitador(
	    AreaPredio ap_ap, Long al_idTurnoHistoria, String ls_idTurno, int ai_totalRevision, int ai_totalMatriculas,
	    String as_userId, String as_localIp, String as_remoteIp, String as_observaciones
	)
	    throws B2BException
	{
		DAOManager ldm_manager;

		ldm_manager = DaoManagerFactory.getDAOManager();

		try
		{
			MotivoTramite    lmt_motivo;
			MotivoTramiteDAO lmtd_DAO;
			long             ll_idMotivo;
			long             ll_idEtapa;

			lmt_motivo      = new MotivoTramite();
			lmtd_DAO        = DaoCreator.getMotivoTramiteDAO(ldm_manager);
			ll_idMotivo     = MotivoTramiteCommon.ENVIAR_A_CALIFICADOR_DIGITADOR_MASIVO;
			ll_idEtapa      = EtapaCommon.ID_ETAPA_DIGITADOR_MASIVO;

			lmt_motivo.setIdEtapaOrigen(ll_idEtapa);
			lmt_motivo.setIdMotivo(ll_idMotivo);

			getRegistroCalificacionBusiness()
				    .CalcularRevision(
				    ai_totalMatriculas, ai_totalRevision, ldm_manager,
				    ConstanteCommon.PORCENTAJE_REVISION_MATRICULAS_MASIVO
				);

			lmt_motivo = lmtd_DAO.findById(lmt_motivo);

			if(lmt_motivo != null)
			{
				TurnoHistoria    lth_turnoHistoria;
				TurnoHistoriaDAO lthd_DAO;

				lth_turnoHistoria     = new TurnoHistoria();
				lthd_DAO              = DaoCreator.getTurnoHistoriaDAO(ldm_manager);

				lth_turnoHistoria.setIdTurnoHistoria(al_idTurnoHistoria);

				lth_turnoHistoria = lthd_DAO.findById(lth_turnoHistoria);

				if(lth_turnoHistoria != null)
				{
					{
						String ls_estadoActividad;

						ls_estadoActividad = lth_turnoHistoria.getEstadoActividad();

						if(
						    StringUtils.isValidString(ls_estadoActividad)
							    && !ls_estadoActividad.equalsIgnoreCase(EstadoCommon.ASIGNADA)
						)
							throw new B2BException(ErrorKeys.NO_SE_PUEDE_TERMINAR_TURNO_HIST_ASG);
					}

					lth_turnoHistoria.setEstadoActividad(EstadoCommon.TERMINADA);
					lth_turnoHistoria.setMotivo(lmt_motivo.getDescripcion());
					lth_turnoHistoria.setIdMotivo(NumericUtils.getLongWrapper(lmt_motivo.getIdMotivo()));
					lth_turnoHistoria.setIdUsuarioModificacion(as_userId);
					lth_turnoHistoria.setIpModificacion(as_remoteIp);

					if(StringUtils.isValidString(as_observaciones))
						lth_turnoHistoria.setObservaciones(as_observaciones);

					lthd_DAO.insertOrUpdate(lth_turnoHistoria, false);

					DaoCreator.getProcedimientosDAO(ldm_manager).spCreateStage(lth_turnoHistoria);

					if(ap_ap != null)
					{
						Collection<AreaPredio> lcap_tmp;
						lcap_tmp = ap_ap.getMatriculasInformacion();

						if(CollectionUtils.isValidCollection(lcap_tmp))
						{
							boolean              lb_b;
							AccPredioRegistro    lapr_pr;
							AccPredioRegistroDAO lapr_DAO;

							lapr_DAO     = DaoCreator.getAccPredioRegistroDAO(ldm_manager);
							lb_b         = ap_ap.isRevisadoDigitador();

							for(AreaPredio lap_tmp : lcap_tmp)
							{
								if(lap_tmp != null)
								{
									lapr_pr = new AccPredioRegistro();

									lapr_pr.setIdUsuarioModificacion(as_userId);
									lapr_pr.setIpModificacion(as_remoteIp);
									lapr_pr.setRevision(null);
									lapr_pr.setIdPredioRegistro(lap_tmp.getIdPredioRegistro());

									lapr_DAO.actualizarRevision(lapr_pr, lb_b);
								}
							}
						}
					}
				}
			}
			else
			{
				Object[] aoa_messageArgs = new String[2];
				aoa_messageArgs[0]     = NumericUtils.getLongWrapper(ll_idEtapa);
				aoa_messageArgs[1]     = NumericUtils.getLongWrapper(ll_idMotivo);

				throw new B2BException(ErrorKeys.ERROR_MOTIVO_INVALIDO, aoa_messageArgs);
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("salvarDigitador", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}
	}

	/**
	 * Método encargado de salvar la información de Englobes.
	 *
	 * @param arc_data Objeto que contiene la información a guardar.
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public synchronized void salvarEnglobes(RegistroCalificacion arc_data)
	    throws B2BException
	{
		DAOManager ldm_manager;

		ldm_manager = DaoManagerFactory.getDAOManager();

		try
		{
			if(arc_data != null)
			{
				boolean lb_englobeAnotacion;
				boolean lb_primerEnglobe;
				Long    ll_idTurnoHistoria;
				Long    ll_idMatricula;
				String  ls_idTurno;
				String  ls_idCirculo;
				String  ls_idUsuario;
				String  ls_ipRemote;

				lb_englobeAnotacion     = arc_data.isEnglobeAnotacion();
				lb_primerEnglobe        = arc_data.isPrimerEnglobe();
				ll_idTurnoHistoria      = arc_data.getIdTurnoHistoria();
				ll_idMatricula          = arc_data.getIdMatricula();
				ls_idTurno              = arc_data.getTurno();
				ls_idCirculo            = arc_data.getIdCirculo();
				ls_idUsuario            = arc_data.getIdUsuario();
				ls_ipRemote             = arc_data.getIpAddress();

				if(StringUtils.isValidString(ls_idCirculo) && NumericUtils.isValidLong(ll_idMatricula))
				{
					HashMap<String, DireccionPredio> lhmsdp_direcciones;

					lhmsdp_direcciones = arc_data.getDireccionesSeleccionadas();

					if(CollectionUtils.isValidCollection(lhmsdp_direcciones))
					{
						DireccionPredioAccDAO ldpa_DAO;
						int                   li_idDireccion;
						boolean               lb_tipoPredioUpdated;
						PredioRegistroDAO     lpr_DAO;
						AccPredioRegistroDAO  lapr_DAO;

						ldpa_DAO                 = DaoCreator.getDireccionPredioAccDAO(ldm_manager);
						li_idDireccion           = 1;
						lb_tipoPredioUpdated     = false;
						lpr_DAO                  = DaoCreator.getPredioRegistroDAO(ldm_manager);
						lapr_DAO                 = DaoCreator.getAccPredioRegistroDAO(ldm_manager);

						if(lb_englobeAnotacion && !lb_primerEnglobe)
						{
							Collection<DireccionPredioAcc> lcdpa_data;
							DireccionPredioAcc             ldpa_param;

							ldpa_param = new DireccionPredioAcc();

							ldpa_param.setIdCirculo(ls_idCirculo);
							ldpa_param.setIdMatricula(ll_idMatricula);
							ldpa_param.setIdTurno(ls_idTurno);

							lcdpa_data = ldpa_DAO.findAllByIdCirculoMatriculaTurno(ldpa_param);

							if(CollectionUtils.isValidCollection(lcdpa_data))
								li_idDireccion = lcdpa_data.size() + 1;
						}

						for(Map.Entry<String, DireccionPredio> lhm_iterador : lhmsdp_direcciones.entrySet())
						{
							DireccionPredio ldp_direccionPredio;

							ldp_direccionPredio = lhm_iterador.getValue();

							if(ldp_direccionPredio != null)
							{
								boolean lb_insertar;

								lb_insertar = true;

								if(!lb_tipoPredioUpdated)
								{
									String ls_idCirculoTemp;
									long   ll_idMatriculaTemp;

									ls_idCirculoTemp       = ldp_direccionPredio.getIdCirculo();
									ll_idMatriculaTemp     = NumericUtils.getLong(ldp_direccionPredio.getIdMatricula());

									PredioRegistro lpr_predioR;
									lpr_predioR            = new PredioRegistro();

									lpr_predioR.setIdCirculo(ls_idCirculoTemp);
									lpr_predioR.setIdMatricula(ll_idMatriculaTemp);

									lpr_predioR = lpr_DAO.findByCirculoMatricula(lpr_predioR);

									if(lpr_predioR != null)
									{
										AccPredioRegistro lapr_prediRegistro;
										lapr_prediRegistro = new AccPredioRegistro();

										lapr_prediRegistro.setIdCirculo(ls_idCirculo);
										lapr_prediRegistro.setIdMatricula(ll_idMatricula);

										lapr_prediRegistro = lapr_DAO.findByCirculoMatricula(lapr_prediRegistro);

										if(lapr_prediRegistro != null)
										{
											lapr_prediRegistro.setIdTipoPredio(lpr_predioR.getIdTipoPredio());

											lapr_DAO.updateById(lapr_prediRegistro);

											lb_tipoPredioUpdated = true;
										}
									}
								}

								if(lb_englobeAnotacion && !lb_primerEnglobe)
								{
									DireccionPredioAcc ldpa_param;

									ldpa_param = new DireccionPredioAcc();

									ldpa_param.setIdCirculo(ls_idCirculo);
									ldpa_param.setIdMatricula(ll_idMatricula);
									ldpa_param.setIdTurno(ls_idTurno);
									ldpa_param.setIdDireccion(ldp_direccionPredio.getIdDireccion());

									lb_insertar = ldpa_DAO.findByIdCirculoMatriculaTurnoDireccion(ldpa_param) == null;
								}

								if(lb_insertar)
								{
									int li_idDireccionPredio;

									li_idDireccionPredio = ldpa_DAO.findSecuence();

									if(li_idDireccionPredio > 0)
									{
										DireccionPredioAcc ldpa_direccionTemp;

										ldpa_direccionTemp = new DireccionPredioAcc();

										ldpa_direccionTemp.setIdDireccionPredio(
										    StringUtils.getString(li_idDireccionPredio)
										);
										ldpa_direccionTemp.setIdTurnoHistoria(ll_idTurnoHistoria);
										ldpa_direccionTemp.setIdTurno(ls_idTurno);
										ldpa_direccionTemp.setIdCirculo(ls_idCirculo);
										ldpa_direccionTemp.setIdMatricula(ll_idMatricula);
										ldpa_direccionTemp.setIdDireccion(StringUtils.getString(li_idDireccion));
										ldpa_direccionTemp.setDireccion(ldp_direccionPredio.getDireccion());
										ldpa_direccionTemp.setIdTipoEjePrincipal(
										    ldp_direccionPredio.getIdTipoEjePrincipal()
										);
										ldpa_direccionTemp.setDatoEjePrincipal(
										    ldp_direccionPredio.getDatoEjePrincipal()
										);
										ldpa_direccionTemp.setIdLetraEjePrincipal(
										    ldp_direccionPredio.getIdLetraEjePrincipal()
										);
										ldpa_direccionTemp.setIdComplementoEjePrincipal(
										    ldp_direccionPredio.getIdComplementoEjePrincipal()
										);
										ldpa_direccionTemp.setIdCoordenadaEjePrincipal(
										    ldp_direccionPredio.getIdCoordenadaEjePrincipal()
										);
										ldpa_direccionTemp.setIdTipoEjeSecundario(
										    ldp_direccionPredio.getIdTipoEjeSecundario()
										);
										ldpa_direccionTemp.setDatoEjeSecundario(
										    ldp_direccionPredio.getDatoEjeSecundario()
										);
										ldpa_direccionTemp.setIdLetra1EjeSecundario(
										    ldp_direccionPredio.getIdLetra1EjeSecundario()
										);
										ldpa_direccionTemp.setIdComplemento1EjeSecundario(
										    ldp_direccionPredio.getIdComplemento1EjeSecundario()
										);
										ldpa_direccionTemp.setIdCoordenada1EjeSecundario(
										    ldp_direccionPredio.getIdCoordenada1EjeSecundario()
										);
										ldpa_direccionTemp.setDato2EjeSecundario(
										    ldp_direccionPredio.getDato2EjeSecundario()
										);
										ldpa_direccionTemp.setIdLetra2EjeSecundario(
										    ldp_direccionPredio.getIdLetra2EjeSecundario()
										);
										ldpa_direccionTemp.setIdComplemento2EjeSecundario(
										    ldp_direccionPredio.getIdComplemento2EjeSecundario()
										);
										ldpa_direccionTemp.setIdCoordenada2EjeSecundario(
										    ldp_direccionPredio.getIdCoordenada2EjeSecundario()
										);
										ldpa_direccionTemp.setIdComplementoDireccion(
										    ldp_direccionPredio.getIdComplementoDireccion()
										);
										ldpa_direccionTemp.setComplementoDireccion(
										    ldp_direccionPredio.getComplementoDireccion()
										);
										ldpa_direccionTemp.setIdUsuarioCreacion(ls_idUsuario);
										ldpa_direccionTemp.setIpCreacion(ls_ipRemote);

										ldpa_DAO.insert(ldpa_direccionTemp);

										li_idDireccion++;
									}
								}
							}
						}
					}
					else
						throw new B2BException(ErrorKeys.DEBE_AGREGAR_DIRECCION_PREDIO);
				}

				if(NumericUtils.isValidLong(ll_idTurnoHistoria) && !lb_englobeAnotacion)
				{
					MotivoTramite    lmt_motivo;
					MotivoTramiteDAO lmtd_DAO;
					long             ll_idMotivo;
					long             ll_idEtapa;

					lmt_motivo      = new MotivoTramite();
					lmtd_DAO        = DaoCreator.getMotivoTramiteDAO(ldm_manager);
					ll_idMotivo     = MotivoTramiteCommon.ENVIAR_A_CALIFICADOR_DIGITADOR_MASIVO;
					ll_idEtapa      = EtapaCommon.ID_ETAPA_DIGITADOR_MASIVO;

					lmt_motivo.setIdEtapaOrigen(ll_idEtapa);
					lmt_motivo.setIdMotivo(ll_idMotivo);

					lmt_motivo = lmtd_DAO.findById(lmt_motivo);

					if(lmt_motivo != null)
					{
						TurnoHistoria    lth_turnoHistoria;
						TurnoHistoriaDAO lthd_DAO;
						String           ls_observaciones;

						lth_turnoHistoria     = new TurnoHistoria();
						lthd_DAO              = DaoCreator.getTurnoHistoriaDAO(ldm_manager);
						ls_observaciones      = arc_data.getObservaciones();

						lth_turnoHistoria.setIdTurnoHistoria(ll_idTurnoHistoria);

						lth_turnoHistoria = lthd_DAO.findById(lth_turnoHistoria);

						if(lth_turnoHistoria != null)
						{
							{
								String ls_estadoActividad;

								ls_estadoActividad = lth_turnoHistoria.getEstadoActividad();

								if(
								    StringUtils.isValidString(ls_estadoActividad)
									    && !ls_estadoActividad.equalsIgnoreCase(EstadoCommon.ASIGNADA)
								)
									throw new B2BException(ErrorKeys.NO_SE_PUEDE_TERMINAR_TURNO_HIST_ASG);
							}

							lth_turnoHistoria.setEstadoActividad(EstadoCommon.TERMINADA);
							lth_turnoHistoria.setMotivo(lmt_motivo.getDescripcion());
							lth_turnoHistoria.setIdMotivo(NumericUtils.getLongWrapper(lmt_motivo.getIdMotivo()));
							lth_turnoHistoria.setIdUsuarioModificacion(ls_idUsuario);
							lth_turnoHistoria.setIpModificacion(ls_ipRemote);

							if(StringUtils.isValidString(ls_observaciones))
								lth_turnoHistoria.setObservaciones(ls_observaciones);

							lthd_DAO.insertOrUpdate(lth_turnoHistoria, false);

							DaoCreator.getProcedimientosDAO(ldm_manager).spCreateStage(lth_turnoHistoria);
						}
					}
					else
					{
						Object[] aoa_messageArgs = new String[2];
						aoa_messageArgs[0]     = NumericUtils.getLongWrapper(ll_idEtapa);
						aoa_messageArgs[1]     = NumericUtils.getLongWrapper(ll_idMotivo);

						throw new B2BException(ErrorKeys.ERROR_MOTIVO_INVALIDO, aoa_messageArgs);
					}
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("salvarEnglobes", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}
	}

	/**
	 * Método encargado de salvar los linderos y complementación para el proceso de englobes.
	 *
	 * @param acc_dataComplementacion Objeto que contiene la información de la complementación que se desea salvar.
	 * @param alrc_dataLindero Objeto que contiene la información de los linderos que se desean salvar.
	 * @param arc_data Objeto que contiene la información del proceso.
	 * @param as_user Variable de tipo String que contiene el id del usuario que está realizando el proceso.
	 * @param as_ip Variable de tipo String que contiene la ip del usuario que está realizando el proceso.
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public synchronized void salvarLinderosComplementacionEnglobes(
	    ComplementacionCalificacion acc_dataComplementacion, LinderoRegistroCalificacion alrc_dataLindero,
	    RegistroCalificacion arc_data, String as_user, String as_ip
	)
	    throws B2BException
	{
		DAOManager ldm_manager;

		ldm_manager = DaoManagerFactory.getDAOManager();

		try
		{
			if((acc_dataComplementacion != null) && (alrc_dataLindero != null) && (arc_data != null))
			{
				String ls_idCirculo;
				String ls_idTurno;
				Long   ll_idMatricula;
				Long   ll_idTurnoHistoria;

				ls_idCirculo           = arc_data.getIdCirculo();
				ls_idTurno             = arc_data.getTurno();
				ll_idMatricula         = arc_data.getIdMatricula();
				ll_idTurnoHistoria     = arc_data.getIdTurnoHistoria();

				{
					String ls_lindero;

					ls_lindero = alrc_dataLindero.getLindero();

					if(StringUtils.isValidString(ls_lindero))
					{
						AccLinderoPredio    lalp_lindero;
						AccLinderoPredioDAO lalp_DAO;

						lalp_lindero     = new AccLinderoPredio();
						lalp_DAO         = DaoCreator.getAccLinderoPredioDAO(ldm_manager);

						lalp_lindero.setIdCirculo(ls_idCirculo);
						lalp_lindero.setIdMatricula(ll_idMatricula);
						lalp_lindero.setIdTurno(ls_idTurno);

						lalp_lindero = lalp_DAO.findByCirculoMatriculaTurno(lalp_lindero);

						if(lalp_lindero != null)
						{
							lalp_lindero.setLindero(ls_lindero);
							lalp_lindero.setIdTurnoHistoria(ll_idTurnoHistoria);
							lalp_lindero.setIdTurno(ls_idTurno);
							lalp_lindero.setIdUsuarioModificacion(as_user);
							lalp_lindero.setIpModificacion(as_ip);

							lalp_DAO.updateById(lalp_lindero, true);
						}
						else
						{
							int li_idLinderoPredio;

							li_idLinderoPredio = lalp_DAO.findSecuencia();

							if(li_idLinderoPredio > 0)
							{
								lalp_lindero = new AccLinderoPredio();

								lalp_lindero.setIdLinderoPredio(StringUtils.getString(li_idLinderoPredio));
								lalp_lindero.setIdTurnoHistoria(ll_idTurnoHistoria);
								lalp_lindero.setIdCirculo(ls_idCirculo);
								lalp_lindero.setIdMatricula(ll_idMatricula);
								lalp_lindero.setLindero(ls_lindero);
								lalp_lindero.setIdUsuarioCreacion(as_user);
								lalp_lindero.setIpCreacion(as_ip);
								lalp_lindero.setIdTurno(ls_idTurno);

								lalp_DAO.insert(lalp_lindero);
							}
						}
					}
					else
						throw new B2BException(ErrorKeys.ERROR_LINDERO_INVALIDO);
				}

				{
					String ls_tipoComplementacion;

					ls_tipoComplementacion = acc_dataComplementacion.getTipoComplementacion();

					if(StringUtils.isValidString(ls_tipoComplementacion))
					{
						ComplementacionPredio lcp_complementacionPredio;

						lcp_complementacionPredio = acc_dataComplementacion.getComplementacionPredio();

						if(lcp_complementacionPredio != null)
						{
							String ls_complementacion;

							ls_complementacion = lcp_complementacionPredio.getComplementacion();

							if(StringUtils.isValidString(ls_complementacion))
							{
								AccComplementacionPredioDAO lacp_DAO;
								ComplementacionPredioDAO    lcp_DAO;
								Long                        ll_idComplementacion;

								lacp_DAO                 = DaoCreator.getAccComplementacionPredioDAO(ldm_manager);
								lcp_DAO                  = DaoCreator.getComplementacionPredioDAO(ldm_manager);
								ll_idComplementacion     = null;

								if(
								    ls_tipoComplementacion.equalsIgnoreCase(TipoComplementacionCommon.NUEVA)
									    || ls_tipoComplementacion.equalsIgnoreCase(TipoComplementacionCommon.CONSTRUIR)
								)
								{
									int li_idComplementacionBNG;

									li_idComplementacionBNG = lcp_DAO.findSecuence();

									if(li_idComplementacionBNG > 0)
									{
										lcp_complementacionPredio.setIdComplementacion(
										    StringUtils.getString(li_idComplementacionBNG)
										);
										ll_idComplementacion = NumericUtils.getLongWrapper(li_idComplementacionBNG);
										lcp_complementacionPredio.setIdUsuarioCreacion(as_user);
										lcp_complementacionPredio.setIpCreacion(as_ip);

										lcp_DAO.insert(lcp_complementacionPredio);

										int li_idComplementacionAcc;

										li_idComplementacionAcc = lacp_DAO.findSecuence();

										if(li_idComplementacionAcc > 0)
										{
											AccComplementacionPredio lacp_complementacionPredio;
											String                   ls_idTipoComplementacion;

											lacp_complementacionPredio     = new AccComplementacionPredio();
											ls_idTipoComplementacion       = ls_tipoComplementacion.equalsIgnoreCase(
												    TipoComplementacionCommon.NUEVA
												) ? TipoComplementacionCommon.N : TipoComplementacionCommon.A;

											lacp_complementacionPredio.setIdComplementacion(
											    NumericUtils.getLongWrapper(li_idComplementacionAcc)
											);
											lacp_complementacionPredio.setIdTurnoHistoria(
											    NumericUtils.getLong(ll_idTurnoHistoria)
											);
											lacp_complementacionPredio.setComplementacion(ls_complementacion);
											lacp_complementacionPredio.setIdUsuarioCreacion(as_user);
											lacp_complementacionPredio.setIpCreacion(as_ip);
											lacp_complementacionPredio.setIdTurno(ls_idTurno);
											lacp_complementacionPredio.setIdComplementacionOriginal(
											    NumericUtils.getLongWrapper(li_idComplementacionBNG)
											);
											lacp_complementacionPredio.setTipoComplementacion(ls_idTipoComplementacion);

											lacp_DAO.insert(lacp_complementacionPredio);
										}
									}
								}
								else if(ls_tipoComplementacion.equalsIgnoreCase(TipoComplementacionCommon.COPIAR))
								{
									if(acc_dataComplementacion.isCopiarEditar())
									{
										int li_idComplementacionBNG;

										li_idComplementacionBNG = lcp_DAO.findSecuence();

										if(li_idComplementacionBNG > 0)
										{
											lcp_complementacionPredio.setIdComplementacion(
											    StringUtils.getString(li_idComplementacionBNG)
											);
											lcp_complementacionPredio.setIdUsuarioCreacion(as_user);
											lcp_complementacionPredio.setIpCreacion(as_ip);

											lcp_DAO.insert(lcp_complementacionPredio);

											int li_idComplementacionAcc;

											li_idComplementacionAcc     = lacp_DAO.findSecuence();
											ll_idComplementacion        = NumericUtils.getLongWrapper(
												    li_idComplementacionBNG
												);

											if(li_idComplementacionAcc > 0)
											{
												AccComplementacionPredio lacp_complementacionPredio;

												lacp_complementacionPredio = new AccComplementacionPredio();

												lacp_complementacionPredio.setIdComplementacion(
												    NumericUtils.getLongWrapper(li_idComplementacionAcc)
												);
												lacp_complementacionPredio.setIdTurnoHistoria(
												    NumericUtils.getLong(ll_idTurnoHistoria)
												);
												lacp_complementacionPredio.setComplementacion(ls_complementacion);
												lacp_complementacionPredio.setIdUsuarioCreacion(as_user);
												lacp_complementacionPredio.setIpCreacion(as_ip);
												lacp_complementacionPredio.setIdTurno(ls_idTurno);
												lacp_complementacionPredio.setIdComplementacionOriginal(
												    NumericUtils.getLongWrapper(li_idComplementacionBNG)
												);
												ls_tipoComplementacion = StringUtils.isValidString(
													    ls_tipoComplementacion
													)
													? (ls_tipoComplementacion.equalsIgnoreCase(
													    TipoComplementacionCommon.COPIAR
													) ? TipoComplementacionCommon.C
													  : (ls_tipoComplementacion.equalsIgnoreCase(
													    TipoComplementacionCommon.CONSTRUIR
													) ? TipoComplementacionCommon.A
													  : (ls_tipoComplementacion.equalsIgnoreCase(
													    TipoComplementacionCommon.NUEVA
													) ? TipoComplementacionCommon.N : null))) : null;
												lacp_complementacionPredio.setTipoComplementacion(
												    ls_tipoComplementacion
												);

												lacp_DAO.insert(lacp_complementacionPredio);
											}
										}
									}
									else
									{
										int li_idComplementacionAcc;

										li_idComplementacionAcc = lacp_DAO.findSecuence();

										if(li_idComplementacionAcc > 0)
										{
											AccComplementacionPredio lacp_complementacionPredio;

											lacp_complementacionPredio     = new AccComplementacionPredio();
											ll_idComplementacion           = NumericUtils.getLongWrapper(
												    lcp_complementacionPredio.getIdComplementacion()
												);

											lacp_complementacionPredio.setIdComplementacion(
											    NumericUtils.getLongWrapper(li_idComplementacionAcc)
											);
											lacp_complementacionPredio.setIdTurnoHistoria(
											    NumericUtils.getLong(ll_idTurnoHistoria)
											);
											lacp_complementacionPredio.setComplementacion(ls_complementacion);
											lacp_complementacionPredio.setIdUsuarioCreacion(as_user);
											lacp_complementacionPredio.setIpCreacion(as_ip);
											lacp_complementacionPredio.setIdTurno(ls_idTurno);
											lacp_complementacionPredio.setIdComplementacionOriginal(
											    ll_idComplementacion
											);
											ls_tipoComplementacion = StringUtils.isValidString(ls_tipoComplementacion)
												? (ls_tipoComplementacion.equalsIgnoreCase(
												    TipoComplementacionCommon.COPIAR
												) ? TipoComplementacionCommon.C
												  : (ls_tipoComplementacion.equalsIgnoreCase(
												    TipoComplementacionCommon.CONSTRUIR
												) ? TipoComplementacionCommon.A
												  : (ls_tipoComplementacion.equalsIgnoreCase(
												    TipoComplementacionCommon.NUEVA
												) ? TipoComplementacionCommon.N : null))) : null;
											lacp_complementacionPredio.setTipoComplementacion(ls_tipoComplementacion);

											lacp_DAO.insert(lacp_complementacionPredio);
										}
									}
								}
								else
									throw new B2BException(ErrorKeys.ERROR_COMPLEMENTACION_TIPO);

								AccPredioRegistro    lapr_predioRegistro;
								AccPredioRegistroDAO lapr_DAO;

								lapr_predioRegistro     = new AccPredioRegistro();
								lapr_DAO                = DaoCreator.getAccPredioRegistroDAO(ldm_manager);

								lapr_predioRegistro.setIdCirculo(ls_idCirculo);
								lapr_predioRegistro.setIdMatricula(ll_idMatricula);

								lapr_predioRegistro = lapr_DAO.findByCirculoMatricula(lapr_predioRegistro);

								if(lapr_predioRegistro != null)
								{
									lapr_predioRegistro.setIdComplementacion(ll_idComplementacion);
									lapr_predioRegistro.setIdTurnoHistoria(ll_idTurnoHistoria);
									lapr_predioRegistro.setIdTurno(ls_idTurno);
									lapr_predioRegistro.setIdUsuarioModificacion(as_user);
									lapr_predioRegistro.setIpModificacion(as_ip);

									lapr_DAO.updateById(lapr_predioRegistro);
								}
								else
									throw new B2BException(ErrorKeys.ACC_PREDIO_REGISTRO);
							}
							else
								throw new B2BException(ErrorKeys.ERROR_COMPLEMENTACION_INVALIDO);
						}
						else
							throw new B2BException(ErrorKeys.ERROR_COMPLEMENTACION_INVALIDO);
					}
					else
						throw new B2BException(ErrorKeys.ERROR_COMPLEMENTACION_TIPO);
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("salvarLinderosComplementacionEnglobes", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}
	}

	/**
	 * Método encargado de salvar los permisos de corrección.
	 *
	 * @param as_observaciones de as observaciones
	 * @param as_idTurnoHistoria Variable de tipo String que contiene el id turno historia.
	 * @param as_userId Variable de tipo String que contiene el id del usuario.
	 * @param as_remoteIp Variable de tipo String que contiene la ip del usuario.
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public synchronized void salvarPermisos(
	    String as_observaciones, String as_idTurnoHistoria, String as_userId, String as_remoteIp
	)
	    throws B2BException
	{
		DAOManager ldm_manager;

		ldm_manager = DaoManagerFactory.getDAOManager();

		try
		{
			if(StringUtils.isValidString(as_idTurnoHistoria))
			{
				TurnoHistoriaDAO lth_DAO;
				TurnoHistoria    lth_turnoHistoria;

				lth_DAO               = DaoCreator.getTurnoHistoriaDAO(ldm_manager);
				lth_turnoHistoria     = lth_DAO.findById(NumericUtils.getLongWrapper(as_idTurnoHistoria));

				if(lth_turnoHistoria != null)
				{
					Collection<SolicitudMatricula> lcsm_return;

					lcsm_return = findMatriculasByIdSolicitud(
						    lth_turnoHistoria.getIdTurno(), as_userId, null, as_remoteIp, ldm_manager
						);

					if(CollectionUtils.isValidCollection(lcsm_return))
					{
						SolicitudCamposCorreccionDAO lscc_DAO;
						CausalCorreccionDAO          lccd_DAO;

						lscc_DAO     = DaoCreator.getSolicitudCamposCorreccionDAO(ldm_manager);
						lccd_DAO     = DaoCreator.getCausalCorreccionDAO(ldm_manager);

						for(SolicitudMatricula lsm_iterador : lcsm_return)
						{
							if(lsm_iterador != null)
							{
								Collection<SolicitudCorreccion> lcsc_data;
								Long                            ll_idMatricula;
								String                          ls_idCirculo;
								String                          ls_idSolicitud;
								SolicitudCorreccion             lsc_param;
								SolicitudCorreccionDAO          lscd_DAO;

								ll_idMatricula     = NumericUtils.getLongWrapper(lsm_iterador.getIdMatricula());
								ls_idCirculo       = lsm_iterador.getIdCirculo();
								ls_idSolicitud     = lth_turnoHistoria.getIdSolicitud();
								lscd_DAO           = DaoCreator.getSolicitudCorreccionDAO(ldm_manager);
								lsc_param          = new SolicitudCorreccion();

								lsc_param.setIdSolicitud(ls_idSolicitud);
								lsc_param.setIdCirculo(ls_idCirculo);
								lsc_param.setIdMatricula(NumericUtils.getBigInteger(ll_idMatricula));

								lcsc_data = lscd_DAO.findBySolicitudCirculoMatricula(lsc_param, true, false);

								if(CollectionUtils.isValidCollection(lcsc_data))
								{
									SolicitudCamposCorreccion lscc_param;

									lscc_param = new SolicitudCamposCorreccion();

									lscc_param.setIdSolicitud(ls_idSolicitud);
									lscc_param.setIdCirculo(ls_idCirculo);
									lscc_param.setIdMatricula(ll_idMatricula);

									for(SolicitudCorreccion lsc_iterador : lcsc_data)
									{
										if(lsc_iterador != null)
										{
											BigInteger                lbi_idCausalCorreccion;
											SolicitudCamposCorreccion lscc_data;

											lbi_idCausalCorreccion = lsc_iterador.getIdCausalCorreccion();
											lscc_param.setIdCausalCorreccion(
											    NumericUtils.getLongWrapper(lbi_idCausalCorreccion)
											);

											lscc_data = lscc_DAO.findByIdCausal(lscc_param);

											if(lscc_data == null)
											{
												CausalCorreccion lcc_causalCorreccion;

												lcc_causalCorreccion = new CausalCorreccion();

												lcc_causalCorreccion.setIdCausalCorreccion(lbi_idCausalCorreccion);

												lcc_causalCorreccion = lccd_DAO.findById(lcc_causalCorreccion);

												if(lcc_causalCorreccion != null)
												{
													Object[] loa_arg;

													loa_arg        = new String[2];
													loa_arg[0]     = ls_idCirculo + IdentificadoresCommon.SIMBOLO_GUION
														+ ll_idMatricula;
													loa_arg[1]     = StringUtils.getString(
														    lcc_causalCorreccion.getNombre()
														);

													throw new B2BException(ErrorKeys.ERROR_CAMPOS_CORRECCION, loa_arg);
												}
											}
										}
										else
											throw new B2BException(ErrorKeys.ERROR_INTERNO_SISTEMA);
									}
								}
								else
									throw new B2BException(ErrorKeys.ERROR_CAMPOS_CORRECCION_TOTAL);
							}
							else
								throw new B2BException(ErrorKeys.ERROR_INTERNO_SISTEMA);
						}
					}
					else
						throw new B2BException(ErrorKeys.ERROR_INTERNO_SISTEMA);

					MotivoTramite lmt_motivo;

					lmt_motivo = new MotivoTramite();

					lmt_motivo.setIdEtapaOrigen(EtapaCommon.ID_ETAPA_CORRECCIONES);
					lmt_motivo.setIdMotivo(MotivoTramiteCommon.PERMISOS_PARA_CORRECCIONES);

					lmt_motivo = DaoCreator.getMotivoTramiteDAO(ldm_manager).findById(lmt_motivo);

					if(lmt_motivo != null)
					{
						lth_turnoHistoria.setEstadoActividad(EstadoCommon.TERMINADA);
						lth_turnoHistoria.setMotivo(lmt_motivo.getDescripcion());
						lth_turnoHistoria.setObservaciones(as_observaciones);
						lth_turnoHistoria.setIdMotivo(NumericUtils.getLongWrapper(lmt_motivo.getIdMotivo()));
						lth_turnoHistoria.setIdUsuarioModificacion(as_userId);
						lth_turnoHistoria.setIpModificacion(as_remoteIp);

						lth_DAO.insertOrUpdate(lth_turnoHistoria, false);

						DaoCreator.getProcedimientosDAO(ldm_manager).spCreateStage(lth_turnoHistoria);
					}
					else
						throw new B2BException(ErrorKeys.ERROR_INTERNO_SISTEMA);
				}
				else
					throw new B2BException(ErrorKeys.ERROR_INTERNO_SISTEMA);
			}
			else
				throw new B2BException(ErrorKeys.ERROR_INTERNO_SISTEMA);
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("salvarPermisos", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}
	}

	/**
	 * Método encargado de salvar reproduccion constancia calificador.
	 *
	 * @param adrc_parametros de adrc parametros
	 * @param as_usuario de as usuario
	 * @param as_ipRemota de as ip remota
	 * @return el valor de data reproduccion constancia
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public synchronized DataReproduccionConstancia salvarReproduccionConstanciaCalificador(
	    DataReproduccionConstancia adrc_parametros, String as_usuario, String as_ipRemota
	)
	    throws B2BException
	{
		DAOManager ldm_manager;
		ldm_manager = DaoManagerFactory.getDAOManager();

		try
		{
			if(adrc_parametros != null)
			{
				Collection<DataReproduccionConstancia> lcdrc_dataReproduccionConstancia;
				Collection<AnotacionPredioDireccion>   lcapd_anotacionTemp;
				Solicitud                              ls_solicitud;
				ls_solicitud = new Solicitud();

				String       ls_idSolicitud;
				SolicitudDAO lsd_DAO;
				String       ls_idTurnoHistoria;

				lcdrc_dataReproduccionConstancia     = adrc_parametros.getDataReproduccionConstancia();
				lcapd_anotacionTemp                  = new ArrayList<AnotacionPredioDireccion>();
				ls_idTurnoHistoria                   = adrc_parametros.getIdTurnoHistoria();
				lsd_DAO                              = DaoCreator.getSolicitudDAO(ldm_manager);
				ls_idSolicitud                       = null;

				if(StringUtils.isValidString(ls_idTurnoHistoria))
				{
					TurnoHistoria lth_turnoHistoria;
					lth_turnoHistoria = new TurnoHistoria();
					lth_turnoHistoria.setIdTurnoHistoria(NumericUtils.getLongWrapper(ls_idTurnoHistoria));

					lth_turnoHistoria = DaoCreator.getTurnoHistoriaDAO(ldm_manager).findById(lth_turnoHistoria);

					if(lth_turnoHistoria != null)
					{
						ls_idSolicitud = lth_turnoHistoria.getIdSolicitud();

						if(!StringUtils.isValidString(ls_idSolicitud))
							throw new B2BException(ErrorKeys.ID_SOLICITUD_INVALIDO);

						ls_solicitud.setIdSolicitud(ls_idSolicitud);

						ls_solicitud = lsd_DAO.findById(ls_solicitud);

						if(ls_solicitud == null)
							throw new B2BException(ErrorKeys.ERROR_SIN_REGISTROS_PARA_LA_SOLICITUD);
					}
				}

				if(CollectionUtils.isValidCollection(lcdrc_dataReproduccionConstancia))
				{
					int li_contador;
					li_contador = 1;

					{
						SolicitudInterviniente    lsi_solicitudInterviniente;
						SolicitudIntervinienteDAO lsid_DAO;
						String                    ls_idPersona;

						lsi_solicitudInterviniente     = new SolicitudInterviniente();
						lsid_DAO                       = DaoCreator.getSolicitudIntervinienteDAO(ldm_manager);
						ls_idPersona                   = ls_solicitud.getIdPersona();

						lsi_solicitudInterviniente.setIdSolicitud(ls_idSolicitud);
						lsi_solicitudInterviniente.setIdPersona(ls_idPersona);

						lsi_solicitudInterviniente = lsid_DAO.findById(lsi_solicitudInterviniente);

						if(lsi_solicitudInterviniente == null)
						{
							lsi_solicitudInterviniente = new SolicitudInterviniente();
							lsi_solicitudInterviniente.setIdSolicitud(ls_idSolicitud);
							lsi_solicitudInterviniente.setIdPersona(ls_idPersona);
							lsi_solicitudInterviniente.setRolPersona(RolCommon.A);
							lsi_solicitudInterviniente.setIdUsuarioCreacion(as_usuario);

							lsid_DAO.insertOrUpdate(lsi_solicitudInterviniente, true);
						}
					}

					for(DataReproduccionConstancia lcdrc_iterador : lcdrc_dataReproduccionConstancia)
					{
						if(lcdrc_iterador != null)
						{
							if(li_contador == 1)
							{
								DocumentoConstancia ldc_documentoConstancia;
								ldc_documentoConstancia = lcdrc_iterador.getDocumento();

								if(ldc_documentoConstancia != null)
								{
									String ls_idDocumento;
									ls_idDocumento = ldc_documentoConstancia.getIdDocumento();

									if(StringUtils.isValidString(ls_idDocumento))
									{
										ls_solicitud.setIdDocumento(ls_idDocumento);
										ls_solicitud.setIdUsuarioModificacion(as_usuario);
										ls_solicitud.setIpModificacion(as_ipRemota);

										lsd_DAO.insertOrUpdate(ls_solicitud, false);
									}
									else
										throw new B2BException(ErrorKeys.ID_SOLICITUD_INVALIDO);
								}
							}

							Collection<AnotacionPredioDireccion> lcapd_anotacionPredioDireccion;
							lcapd_anotacionPredioDireccion = lcdrc_iterador.getAnotacionPredioDireccion();

							if(CollectionUtils.isValidCollection(lcapd_anotacionPredioDireccion))
							{
								for(AnotacionPredioDireccion lapd_iterador : lcapd_anotacionPredioDireccion)
								{
									if(lapd_iterador != null)
									{
										if(lapd_iterador.isSeleccionado())
										{
											Integer lI_cantidadCopiasReproducir;
											int     li_cantidadCopiasReproducir;

											lI_cantidadCopiasReproducir     = lapd_iterador.getCantidadCopiasReproducir();
											li_cantidadCopiasReproducir     = NumericUtils.getInt(
												    lI_cantidadCopiasReproducir
												);

											if(li_cantidadCopiasReproducir > 0)
												lcapd_anotacionTemp.add(lapd_iterador);
											else
												throw new B2BException(
												    ErrorKeys.DEBE_DIGITAR_CANTIDAD_COPIAS_REPRODUCIR
												);
										}
									}
								}
							}
						}

						li_contador++;
					}
				}
				else
					throw new B2BException(ErrorKeys.ERROR_NO_AGREGO_REGISTROS);

				if(CollectionUtils.isValidCollection(lcapd_anotacionTemp))
				{
					SolicitudMatriculaDAO                lsmd_DAO;
					SolicitudMatriculaActoDAO            lsmad_DAO;
					SolicitudReproduccionDAO             lsrd_DAO;
					String                               ls_idActo;
					Collection<AnotacionPredioDireccion> lcapd_tmp;

					ActoDAO lad_DAO;
					Acto    la_acto;

					la_acto = new Acto();
					la_acto.setIdSolicitud(ls_idSolicitud);

					lcapd_tmp     = new ArrayList<AnotacionPredioDireccion>();
					lsmd_DAO      = DaoCreator.getSolicitudMatriculaDAO(ldm_manager);
					lsmad_DAO     = DaoCreator.getSolicitudMatriculaActoDAO(ldm_manager);
					lsrd_DAO      = DaoCreator.getConsultaSolicitudReproduccionDAO(ldm_manager);
					lad_DAO       = DaoCreator.getActoDAO(ldm_manager);

					ls_idActo = null;

					for(AnotacionPredioDireccion lapd_iterador : lcapd_anotacionTemp)
					{
						if(lapd_iterador != null)
						{
							String           ls_idCirculo;
							String           ls_radicacion;
							long             ll_idMatricula;
							Collection<Acto> lca_ca;

							ls_idCirculo       = lapd_iterador.getIdCirculo();
							ls_radicacion      = lapd_iterador.getRadicacion();
							ll_idMatricula     = lapd_iterador.getIdMatricula();

							la_acto.setIdCirculo(ls_idCirculo);
							lca_ca = lad_DAO.findByIdSolicitudCirculo(la_acto);

							if(CollectionUtils.isValidCollection(lca_ca))
							{
								for(Acto la_tmp : lca_ca)
									ls_idActo = la_tmp.getIdActo();
							}

							if(ls_solicitud != null)
							{
								lcapd_tmp.add(lapd_iterador);

								{
									SolicitudMatricula lsm_solicitudMatricula;
									boolean            lb_solicitudMatricula;
									lsm_solicitudMatricula     = new SolicitudMatricula();
									lb_solicitudMatricula      = false;

									lsm_solicitudMatricula.setIdSolicitud(ls_idSolicitud);
									lsm_solicitudMatricula.setIdCirculo(ls_idCirculo);
									lsm_solicitudMatricula.setIdMatricula(ll_idMatricula);
									lsm_solicitudMatricula.setExpedirCertificado(EstadoCommon.NO);
									lsm_solicitudMatricula.setCantidadCertificados(
									    new BigDecimal(NumericUtils.DEFAULT_INT_VALUE)
									);
									lsm_solicitudMatricula.setIdUsuarioCreacion(as_usuario);
									lsm_solicitudMatricula.setIpCreacion(as_ipRemota);

									lb_solicitudMatricula = lsmd_DAO.findById(lsm_solicitudMatricula) == null;

									if(lb_solicitudMatricula)
										lsmd_DAO.insertOrUpdate(lsm_solicitudMatricula, lb_solicitudMatricula);
								}

								{
									if(StringUtils.isValidString(ls_idActo))
									{
										SolicitudMatriculaActo lsma_solicitudMatriculaActo;
										boolean                lb_solicitudMatriculaActo;
										lsma_solicitudMatriculaActo     = new SolicitudMatriculaActo();
										lb_solicitudMatriculaActo       = false;

										lsma_solicitudMatriculaActo.setIdSolicitud(ls_idSolicitud);
										lsma_solicitudMatriculaActo.setIdCirculo(ls_idCirculo);
										lsma_solicitudMatriculaActo.setIdMatricula(ll_idMatricula);
										lsma_solicitudMatriculaActo.setIdActo(ls_idActo);
										lsma_solicitudMatriculaActo.setUsuario(as_usuario);
										lsma_solicitudMatriculaActo.setIdUsuarioCreacion(as_usuario);
										lsma_solicitudMatriculaActo.setIdTurno(ls_radicacion);
										lsma_solicitudMatriculaActo.setEstado(EstadoCommon.A);
										lsma_solicitudMatriculaActo.setIpCreacion(as_ipRemota);

										lb_solicitudMatriculaActo = lsmad_DAO.findById(lsma_solicitudMatriculaActo) == null;

										if(lb_solicitudMatriculaActo)
											lsmad_DAO.insertOrUpdate(
											    lsma_solicitudMatriculaActo, lb_solicitudMatriculaActo
											);
									}
								}

								{
									SolicitudReproduccion lsr_solicitudReproduccion;
									SolicitudReproduccion lsr_tmp;
									lsr_solicitudReproduccion = new SolicitudReproduccion();

									lsr_solicitudReproduccion.setIdSolicitud(ls_idSolicitud);
									lsr_solicitudReproduccion.setIdTurnoReproduccion(ls_radicacion);
									lsr_solicitudReproduccion.setIdCirculo(ls_idCirculo);
									lsr_solicitudReproduccion.setIdUsuarioCreacion(as_usuario);
									lsr_solicitudReproduccion.setIpCreacion(as_ipRemota);

									lsr_tmp = lsrd_DAO.validarReproduccionConstancia(lsr_solicitudReproduccion);

									if(lsr_tmp == null)
										lsrd_DAO.insert(lsr_solicitudReproduccion);
								}
							}
						}
					}

					if(CollectionUtils.isValidCollection(lcapd_tmp))
					{
						Map<String, Integer> lcmsb_infoSeleccionada;

						lcmsb_infoSeleccionada = new HashMap<String, Integer>();

						for(AnotacionPredioDireccion loa_tmp : lcapd_tmp)
						{
							if(loa_tmp != null)
							{
								String  ls_idcirculo;
								Integer li_i;

								li_i             = loa_tmp.getCantidadCopiasReproducir();
								ls_idcirculo     = loa_tmp.getIdCirculo();

								if(CollectionUtils.isValidCollection(lcmsb_infoSeleccionada))
								{
									if(NumericUtils.isValidInteger(li_i))
									{
										if(lcmsb_infoSeleccionada.containsKey(ls_idcirculo))
										{
											if(lcmsb_infoSeleccionada.get(ls_idcirculo).compareTo(li_i) < 0)
												lcmsb_infoSeleccionada.put(ls_idcirculo, li_i);
										}
										else
											lcmsb_infoSeleccionada.put(ls_idcirculo, li_i);
									}
								}
								else
									lcmsb_infoSeleccionada.put(ls_idcirculo, li_i);
							}
						}

						if(CollectionUtils.isValidCollection(lcmsb_infoSeleccionada))
						{
							for(Map.Entry<String, Integer> lmsi_entry : lcmsb_infoSeleccionada.entrySet())
							{
								Acto loa_tmp;
								loa_tmp = new Acto();

								loa_tmp.setIdSolicitud(ls_idSolicitud);
								loa_tmp.setIdCirculo(lmsi_entry.getKey());
								loa_tmp.setCantidadActos(BigDecimal.valueOf(lmsi_entry.getValue().longValue()));

								loa_tmp.setIdUsuarioModificacion(as_usuario);
								loa_tmp.setIpModificacion(as_ipRemota);

								lad_DAO.updateCantidadActos(loa_tmp, false);
							}
						}
					}
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("salvarReproduccionConstanciaCalificador", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return adrc_parametros;
	}

	/**
	 * Método encargado de  insertar o actualizar causales de corrección para una matricula y un id solicitud determinado.
	 *
	 * @param accc_causales Colección de datos de tipo CausalCorreccion que contiene los parámetros  necesarios para insertar o actualizar en la tabla SDB_ACC_SOLICITUD_CORRECCION.
	 * @param asm_solMat Objeto de tipo SolicitudMatricula que contiene los datos requeridos para identificar si existe o no la causal corrección a insertar.
	 * @param as_turno  Variable de tipo Stirng que contiene un id turno  determinado.
	 * @param as_userId Variable de tipo String que contiene el usuario que realiza la acción.
	 * @param as_remoteIp Variable de tipo String que contiene la dirección ip remota desde donde se realiza la acción.
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public synchronized void saveCausalesMatriculaCorreccion(
	    Collection<CausalCorreccion> accc_causales, SolicitudMatricula asm_solMat, String as_turno, String as_userId,
	    String as_remoteIp
	)
	    throws B2BException
	{
		DAOManager ldm_manager;

		ldm_manager = DaoManagerFactory.getDAOManager();

		try
		{
			if(!CollectionUtils.isValidCollection(accc_causales) && (asm_solMat == null))
				throw new B2BException(ErrorKeys.ERROR_INTERNO_SISTEMA);

			SolicitudCorreccionDAO lsc_DAO;

			lsc_DAO = DaoCreator.getSolicitudCorreccionDAO(ldm_manager);

			for(CausalCorreccion lcc_causal : accc_causales)
			{
				if(lcc_causal != null)
				{
					SolicitudCorreccion lsc_temp;

					lsc_temp = new SolicitudCorreccion();

					lsc_temp.setIdCausalCorreccion(lcc_causal.getIdCausalCorreccion());
					lsc_temp.setIdSolicitud(asm_solMat.getIdSolicitud());
					lsc_temp.setIdCirculo(asm_solMat.getIdCirculo());
					lsc_temp.setIdMatricula(NumericUtils.getBigInteger(asm_solMat.getIdMatricula()));

					String ls_seleccionado;
					ls_seleccionado = lcc_causal.isSeleccionado() ? EstadoCommon.S : EstadoCommon.N;

					String ls_observacion;
					ls_observacion = lcc_causal.getObservaciones();

					SolicitudCorreccion lsc_solSalvar;

					lsc_solSalvar = lsc_DAO.findBySolCirMatCausal(lsc_temp);

					if(lsc_solSalvar != null)
					{
						lsc_solSalvar.setSeleccionado(ls_seleccionado);
						lsc_solSalvar.setObservacion(ls_observacion);
						lsc_solSalvar.setIdTurno(as_turno);
						lsc_solSalvar.setIdUsuarioModificacion(as_userId);
						lsc_solSalvar.setIpModificacion(as_remoteIp);

						lsc_DAO.insertOrUpdate(lsc_solSalvar, false);
					}
					else
					{
						lsc_temp.setSeleccionado(ls_seleccionado);
						lsc_temp.setObservacion(ls_observacion);
						lsc_temp.setIdTurno(as_turno);
						lsc_temp.setIdUsuarioCreacion(as_userId);
						lsc_temp.setIpCreacion(as_remoteIp);

						lsc_DAO.insertOrUpdate(lsc_temp, true);
					}
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("saveCausalesMatriculaCorreccion", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}
	}

	/**
	 * Método encargado de  insertar los tabs y la información que  se modificó  en el proceso de confrontación correctiva.
	 *
	 * @param accc_ccc Colección de tipo ConfrontacionCorrectiva que contiene los datos necesarios para salvar la informacion de confrontacion correctiva.
	 * @param as_userId Variable de tipo String que contiene el usuario que realiza la acción.
	 * @param as_ipRemote Variable de tipo String que contiene la ip que realiza la acción.
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public synchronized void saveCheks(
	    Collection<ConfrontacionCorrectiva> accc_ccc, String as_userId, String as_ipRemote
	)
	    throws B2BException
	{
		DAOManager ldm_manager;

		ldm_manager = DaoManagerFactory.getDAOManager();

		try
		{
			if(CollectionUtils.isValidCollection(accc_ccc))
			{
				Iterator<ConfrontacionCorrectiva> liccc_iccc;
				TurnoHistoriaDAO                  lothd_thd;
				MotivoTramiteDAO                  lmtd_DAO;
				ProcedimientosDAO                 lpd_DAO;
				UtilDAO                           lud_DAO;

				lud_DAO        = DaoCreator.getUtilDAO(ldm_manager);
				lpd_DAO        = DaoCreator.getProcedimientosDAO(ldm_manager);
				lothd_thd      = DaoCreator.getTurnoHistoriaDAO(ldm_manager);
				lmtd_DAO       = DaoCreator.getMotivoTramiteDAO(ldm_manager);
				liccc_iccc     = accc_ccc.iterator();

				while(liccc_iccc.hasNext())
				{
					ConfrontacionCorrectiva lcc_cc;

					lcc_cc = liccc_iccc.next();

					if(lcc_cc != null)
					{
						TurnoHistoria lth_dataTurn;
						Long          ll_idTurnoHistoria;

						ll_idTurnoHistoria     = lcc_cc.getIdTurnoHistoria();
						lth_dataTurn           = new TurnoHistoria(ll_idTurnoHistoria);

						lth_dataTurn = lothd_thd.findById(lth_dataTurn);

						if(lth_dataTurn != null)
						{
							Calificacion lc_c;

							lc_c = lcc_cc.getDataCalificacion();

							if(lc_c != null)
							{
								Map<String, Object> lmso_tabs;

								lmso_tabs = lc_c.getTabs();

								if(CollectionUtils.isValidCollection(lmso_tabs))
								{
									lc_c.setIdSolicitud(lth_dataTurn.getIdSolicitud());
									lc_c.setIdTurnoHistoria(ll_idTurnoHistoria);

									for(Map.Entry<String, Object> lme_entry : lmso_tabs.entrySet())
									{
										lc_c.setCampo(lme_entry.getKey().toString());

										lothd_thd.insertInfoCorreccion(lc_c, as_userId);
									}

									if(lmso_tabs.containsKey(IdentificadoresCommon.INSERTAR_MATRICULA))
									{
										Collection<TmpSolicitudMatricula>     lcsm_datosInsertar;
										Collection<TmpSolicitudMatriculaActo> lcsma_datosActosInsertar;

										lcsma_datosActosInsertar     = lcc_cc.getDatosBandejaActosInsertar();
										lcsm_datosInsertar           = lcc_cc.getDatosBandejaPrediosInsertar();

										if(
										    CollectionUtils.isValidCollection(lcsm_datosInsertar)
											    && CollectionUtils.isValidCollection(lcsma_datosActosInsertar)
										)
											salvarPrediosConTramite(
											    lcsm_datosInsertar, lcsma_datosActosInsertar, as_userId, ldm_manager
											);
										else
											throw new B2BException(ErrorKeys.CARGAR_FOLIOS_CON_ACTOS);
									}

									if(lmso_tabs.containsKey(IdentificadoresCommon.ELIMINAR_MATRICULA))
									{
										Collection<TmpSolicitudMatricula>     lcsm_datosEliminar;
										Collection<TmpSolicitudMatriculaActo> lcsma_datosActosEliminar;

										lcsm_datosEliminar           = lcc_cc.getDatosBandejaPrediosEliminar();
										lcsma_datosActosEliminar     = lcc_cc.getDatosBandejaActosEliminar();

										if(
										    CollectionUtils.isValidCollection(lcsm_datosEliminar)
											    && CollectionUtils.isValidCollection(lcsma_datosActosEliminar)
										)
											salvarPrediosConTramite(
											    lcsm_datosEliminar, lcsma_datosActosEliminar, as_userId, ldm_manager
											);
										else
											throw new B2BException(ErrorKeys.ERROR_SIN_MATRICULA_A_DESASOCIAR);
									}

									if(lmso_tabs.containsKey(IdentificadoresCommon.VERIFICAR_FOLIO))
									{
										String ls_observacionesVerificarFolio;

										ls_observacionesVerificarFolio = lcc_cc.getVerificaFolioCerrado();

										if(StringUtils.isValidString(ls_observacionesVerificarFolio))
										{
											Map<Integer, Object> lhmp_criterios;

											lhmp_criterios = new LinkedHashMap<Integer, Object>();

											lhmp_criterios.put(new Integer(1), ls_observacionesVerificarFolio);
											lhmp_criterios.put(new Integer(2), as_userId);
											lhmp_criterios.put(new Integer(3), ll_idTurnoHistoria);

											lud_DAO.executeQuery(
											    ConsultasUtilidades.CS_UPDATE_OBSERVACIONES_FOLIO, lhmp_criterios
											);
										}
									}

									MotivoTramite lmt_motivoTramite;

									lmt_motivoTramite     = new MotivoTramite(
										    EtapaCommon.ID_ETAPA_CALIFICACION,
										    MotivoTramiteCommon.CONFRONTACION_CORRECTIVA
										);

									lmt_motivoTramite = lmtd_DAO.findById(lmt_motivoTramite);

									if(lmt_motivoTramite != null)
									{
										{
											String ls_estadoActividad;

											ls_estadoActividad = lth_dataTurn.getEstadoActividad();

											if(
											    StringUtils.isValidString(ls_estadoActividad)
												    && ls_estadoActividad.equalsIgnoreCase(EstadoCommon.TERMINADA)
											)
												throw new B2BException(ErrorKeys.NO_SE_PUEDE_TERMINAR_TURNO_HIST_ASG);
										}

										lth_dataTurn.setEstadoActividad(EstadoCommon.TERMINADA);
										lth_dataTurn.setMotivo(lmt_motivoTramite.getDescripcion());
										lth_dataTurn.setIdMotivo(
										    NumericUtils.getLongWrapper(lmt_motivoTramite.getIdMotivo())
										);
										lth_dataTurn.setObservaciones(lc_c.getJustificacion());
										lth_dataTurn.setIdUsuarioModificacion(as_userId);
										lth_dataTurn.setIpModificacion(as_ipRemote);

										lothd_thd.insertOrUpdate(lth_dataTurn, false);
										lpd_DAO.spCreateStage(lth_dataTurn);
									}
									else
									{
										Object[] aoa_messageArgs = new String[2];
										aoa_messageArgs[0]     = NumericUtils.getLongWrapper(
											    EtapaCommon.ID_ETAPA_CALIFICACION
											);
										aoa_messageArgs[1]     = NumericUtils.getLongWrapper(
											    MotivoTramiteCommon.CONFRONTACION_CORRECTIVA
											);

										throw new B2BException(ErrorKeys.ERROR_MOTIVO_INVALIDO, aoa_messageArgs);
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

			clh_LOGGER.error("saveCheks", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}
	}

	/**
	 * Update nota devolutiva.
	 *
	 * @param ath_turnoHistoria de ath turno historia
	 * @param ab_aprobador de ab aprobador
	 * @param as_userId de as user id
	 * @param as_remoteIp de as remote ip
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void updateNotaDevolutiva(
	    TurnoHistoria ath_turnoHistoria, boolean ab_aprobador, String as_userId, String as_remoteIp
	)
	    throws B2BException
	{
		DAOManager ldm_manager;

		ldm_manager = DaoManagerFactory.getDAOManager();

		try
		{
			if(ath_turnoHistoria != null)
			{
				TurnoHistoria lth_turnoHistoria;
				lth_turnoHistoria = DaoCreator.getTurnoHistoriaDAO(ldm_manager).findById(ath_turnoHistoria);

				if(lth_turnoHistoria != null)
				{
					NotaDevolutiva             lnt_notaDevolutiva;
					NotaDevolutivaDAO          lndd_DAO;
					Collection<NotaDevolutiva> lcnt_notaDevolutiva;

					lnt_notaDevolutiva     = new NotaDevolutiva();
					lndd_DAO               = DaoCreator.getNotaDevolutivaDAO(ldm_manager);

					lnt_notaDevolutiva.setIdTurno(lth_turnoHistoria.getIdTurno());
					lcnt_notaDevolutiva = lndd_DAO.findByTurno(lnt_notaDevolutiva);

					if(CollectionUtils.isValidCollection(lcnt_notaDevolutiva))
					{
						for(NotaDevolutiva lnd_temp : lcnt_notaDevolutiva)
						{
							if(lnd_temp != null)
								lndd_DAO.updateNotaDevolutiva(lnd_temp, ab_aprobador);
						}
					}
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("updateNotaDevolutiva", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}
	}

	/**
	 * Método encargado de validar si una matricula presenta una medida cautelar vigente.
	 *
	 * @param acs_matriculasExtraidas <code>Collection<String></code> que contiene String con matriculas
	 * @param as_idTurno String que contiene turno a consultar
	 * @return String con matricula si encuentra que esta tiene una medida cautelar vigente
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public String validacionMedidaCautelar(Collection<String> acs_matriculasExtraidas, String as_idTurno)
	    throws B2BException
	{
		DAOManager ldm_manager;
		String     ls_return;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		ls_return       = null;

		try
		{
			if(CollectionUtils.isValidCollection(acs_matriculasExtraidas) && StringUtils.isValidString(as_idTurno))
			{
				TurnoDAO lt_DAO;
				Turno    lt_turno;

				lt_DAO       = DaoCreator.getTurnoDAO(ldm_manager);
				lt_turno     = new Turno();

				lt_turno = lt_DAO.findById(as_idTurno);

				if(lt_turno != null)
				{
					String ls_solicitud;

					ls_solicitud = lt_turno.getIdSolicitud();

					if(StringUtils.isValidString(ls_solicitud))
					{
						SolicitudMatriculaDAO lsm_DAO;
						boolean               lb_matriculaConMedidaCautelarEncontrada;

						lsm_DAO                                     = DaoCreator.getSolicitudMatriculaDAO(ldm_manager);
						lb_matriculaConMedidaCautelarEncontrada     = false;

						for(String ls_circuloMatricula : acs_matriculasExtraidas)
						{
							if(!lb_matriculaConMedidaCautelarEncontrada)
							{
								if(StringUtils.isValidString(ls_circuloMatricula))
								{
									Matricula lm_matricula;

									lm_matricula = Matricula.getMatricula(ls_circuloMatricula);

									if(lm_matricula != null)
									{
										String ls_idCirculo;
										Long   ls_idMatricula;

										ls_idCirculo       = lm_matricula.getCirculo();
										ls_idMatricula     = lm_matricula.getMatricula();

										if(
										    StringUtils.isValidString(ls_idCirculo)
											    && NumericUtils.isValidLong(ls_idMatricula)
										)
										{
											Collection<SolicitudMatricula> lcsm_cllSolicitudMatricula;

											lcsm_cllSolicitudMatricula = lsm_DAO.validacionMedidaCautelar(
												    ls_solicitud, ls_idCirculo, ls_idMatricula
												);

											if(CollectionUtils.isValidCollection(lcsm_cllSolicitudMatricula))
											{
												ls_return     = ls_circuloMatricula;

												lb_matriculaConMedidaCautelarEncontrada = true;
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

			clh_LOGGER.error("validacionMedidaCautelar", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return ls_return;
	}

	public synchronized void validarActo0463(Collection<Map<String, Object>> acm_matriculas)
	    throws B2BException
	{
		DAOManager ldm_manager;

		ldm_manager = DaoManagerFactory.getDAOManager();

		try
		{
			String ls_idMatricula;
			ls_idMatricula = null;

			String ls_idCirculo;
			ls_idCirculo = null;

			if(CollectionUtils.isValidCollection(acm_matriculas))
			{
				Iterator<Map<String, Object>> limso_iterador;
				BitacoraBloqueoDAO            lbb_dao;
				SolicitudMatriculaActoDAO     sma_dao;
				ActoDAO                       la_dao;
				limso_iterador     = acm_matriculas.iterator();
				lbb_dao            = DaoCreator.getBitacoraBloqueoDAO(ldm_manager);
				sma_dao            = DaoCreator.getSolicitudMatriculaActoDAO(ldm_manager);
				la_dao             = DaoCreator.getActoDAO(ldm_manager);

				if(limso_iterador.hasNext())
				{
					Map<String, Object> lmso_iterator;

					lmso_iterator = limso_iterador.next();

					if(lmso_iterator != null)
					{
						{
							Object lo_idMatricula;
							lo_idMatricula = lmso_iterator.get("ID_MATRICULA");

							if((lo_idMatricula != null) && lo_idMatricula instanceof BigDecimal)
								ls_idMatricula = lo_idMatricula.toString();

							Object lo_idCirculo;
							lo_idCirculo = lmso_iterator.get("ID_CIRCULO");

							if((lo_idCirculo != null) && lo_idCirculo instanceof String)
								ls_idCirculo = ((String)lo_idCirculo).toString();

							if(StringUtils.isValidString(ls_idMatricula) && StringUtils.isValidString(ls_idCirculo))
							{
								Collection<BitacoraBloqueo> lcbb_bitacoras;

								lcbb_bitacoras = lbb_dao.findAllTurnosBloqueoActivoDifDesbloqueo(
									    ls_idCirculo, ls_idMatricula
									);

								if(CollectionUtils.isValidCollection(lcbb_bitacoras))
								{
									for(BitacoraBloqueo libb_iterador : lcbb_bitacoras)
									{
										if(libb_iterador != null)
										{
											SolicitudMatriculaActo lsm_matriculaActo;
											String                 ls_turnoBloqueo;
											ls_turnoBloqueo       = StringUtils.getString(
												    libb_iterador.getIdTurnoBloqueo()
												);
											lsm_matriculaActo     = new SolicitudMatriculaActo();
											lsm_matriculaActo.setIdTurno(ls_turnoBloqueo);
											lsm_matriculaActo = sma_dao.findByIdTurno(lsm_matriculaActo);

											if(lsm_matriculaActo != null)
											{
												Acto   la_acto;
												String ls_tipoActo;
												la_acto = la_dao.findById(lsm_matriculaActo.getIdActo());

												if(la_acto != null)
												{
													ls_tipoActo = la_acto.getIdTipoActo();

													if(StringUtils.isValidString(ls_tipoActo))
													{
														if(ls_tipoActo.equalsIgnoreCase("0463"))
														{
															Object[] loa_args;

															loa_args     = new String[3];

															loa_args[0]     = ls_idCirculo;
															loa_args[1]     = ls_idMatricula;
															loa_args[2]     = ls_turnoBloqueo;
															throw new B2BException(
															    ErrorKeys.ERROR_MATRICULA_PROHIBICION, loa_args
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
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("validarActo0463", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}
	}

	/**
	 * Valida si un turno tiene una alerta registrada.
	 *
	 * @param al_idTurnoHistoria de al id turno historia
	 * @return true si tiene alertas registradas, false si no tiene
	 * @throws B2BException Si ocurre un error en la base de datos
	 */
	public synchronized ValidacionAlertaTurno validarAlertaTurnoTramite(Long al_idTurnoHistoria)
	    throws B2BException
	{
		ValidacionAlertaTurno lvat_return;

		lvat_return = new ValidacionAlertaTurno();

		if(NumericUtils.isValidLong(al_idTurnoHistoria))
		{
			DAOManager ldm_manager;

			ldm_manager = DaoManagerFactory.getDAOManager();

			try
			{
				TurnoHistoria lth_turnoHistoria;
				String        ls_idTurno;

				lth_turnoHistoria = DaoCreator.getTurnoHistoriaDAO(ldm_manager).findById(al_idTurnoHistoria);

				if(lth_turnoHistoria == null)
					throw new B2BException(ErrorKeys.TURNO_HISTORIA_INVALIDO);

				ls_idTurno = lth_turnoHistoria.getIdTurno();

				{
					Collection<AlertaTurnoTramite> lcatt_alerta;

					lcatt_alerta = DaoCreator.getAlertaTurnoTramiteDAO(ldm_manager).findByIdTurnoEstadoActivo(
						    ls_idTurno
						);

					if(CollectionUtils.isValidCollection(lcatt_alerta))
						lvat_return.setTurnoAfectado(true);
				}

				{
					AlertaTurnoTramite lcatt_alerta;

					lcatt_alerta = DaoCreator.getAlertaTurnoTramiteDAO(ldm_manager)
							                     .findByIdTurnoAsociadoActivo(ls_idTurno);

					if(lcatt_alerta != null)
					{
						Object[] loa_args;

						loa_args     = new String[4];

						loa_args[0] = ls_idTurno;

						{
							String ls_nombreProceso;

							ls_nombreProceso = ConstanteCommon.SIN_INFORMACION;

							{
								Proceso lp_proceso;

								lp_proceso = DaoCreator.getProcesoDAO(ldm_manager)
										                   .findByIdSolicitud(lcatt_alerta.getIdSolicitudVinculada());

								if(lp_proceso != null)
									ls_nombreProceso = lp_proceso.getNombre();
							}

							loa_args[1] = ls_nombreProceso;
						}

						{
							String ls_idTurnoAfectado;
							String ls_nombreEtapa;

							ls_idTurnoAfectado     = ConstanteCommon.SIN_INFORMACION;
							ls_nombreEtapa         = ConstanteCommon.SIN_INFORMACION;

							{
								Turno lt_turno;

								lt_turno = DaoCreator.getTurnoDAO(ldm_manager)
										                 .findById(lcatt_alerta.getIdTurnoAfectado());

								if(lt_turno != null)
								{
									ls_idTurnoAfectado = lt_turno.getIdTurno();

									Etapa le_etapa;

									le_etapa = DaoCreator.getEtapaDAO(ldm_manager)
											                 .findById(
											    NumericUtils.getLong(lth_turnoHistoria.getIdEtapa())
											);

									if(le_etapa != null)
										ls_nombreEtapa = le_etapa.getNombre();
								}
							}

							loa_args[2]     = ls_idTurnoAfectado;
							loa_args[3]     = ls_nombreEtapa;
						}

						lvat_return.setMensajeTurnoAsociado(
						    addMessage(MessagesKeys.ALERTA_TURNOS_AFECTADOS, loa_args, false)
						);
						lvat_return.setTurnoAsociado(true);
					}
				}
			}
			catch(B2BException lb2be_e)
			{
				ldm_manager.setRollbackOnly();

				clh_LOGGER.error("validarAlertaTurnoTramite", lb2be_e);

				throw lb2be_e;
			}
			finally
			{
				ldm_manager.close();
			}
		}

		return lvat_return;
	}

	/**
	 * Método encargado de validar si la anotación contiene un acto que genere apertura de matrículas.
	 *
	 * @param aap_anotacionPredio Objeto que contiene la información de la anotación a validar.
	 * @return Variable de tipo boolean que indica si la anotación contiene un acto que genere apertura de matrículas.
	 * @throws B2BException
	 */
	public synchronized boolean validarAnotacionApertura(
	    com.bachue.snr.prosnr01.model.sdb.acc.AnotacionPredio aap_anotacionPredio
	)
	    throws B2BException
	{
		boolean    lb_return;
		DAOManager ldm_manager;

		lb_return       = false;
		ldm_manager     = DaoManagerFactory.getDAOManager();

		try
		{
			lb_return = validarAnotacionApertura(aap_anotacionPredio, ldm_manager);
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("validarAnotacionApertura", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lb_return;
	}

	/**
	 * Método encargado de validar la anotación de corecciones a crear.
	 *
	 * @param aap_anotacionPredio Objeto que contiene la información de la anotación a validar.
	 * @return  Mapa que contiene los indentificadores para las validaciones.
	 * @throws B2BException  Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public synchronized Map<String, String> validarAnotacionCorreccionCrear(
	    com.bachue.snr.prosnr01.model.sdb.acc.AnotacionPredio aap_anotacionPredio
	)
	    throws B2BException
	{
		DAOManager          ldm_manager;
		Map<String, String> lmss_return;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		lmss_return     = new HashMap<String, String>();

		try
		{
			if(aap_anotacionPredio != null)
			{
				String ls_idTipoActo;

				ls_idTipoActo = aap_anotacionPredio.getIdNaturalezaJuridica();

				if(StringUtils.isValidString(ls_idTipoActo))
				{
					NaturalezaJuridicaDAO lnj_DAO;
					String[]              lsa_data;
					Map<String, String>   lmss_actos;
					Map<String, String>   lmss_englobes;

					lnj_DAO           = DaoCreator.getNaturalezaJuridicaDAO(ldm_manager);
					lmss_englobes     = generarCodigos(
						    ConstanteCommon.CODIGOS_NATURALEZA_JURIDICA_ENGLOBES, ldm_manager
						);
					lmss_actos        = DaoCreator.getAnotacionPredioDAO(ldm_manager)
							                          .findActosAnotacion(
							    aap_anotacionPredio.getIdCirculo(), aap_anotacionPredio.getIdMatricula()
							);
					lsa_data          = ls_idTipoActo.split(IdentificadoresCommon.SIMBOLO_GUION);
					ls_idTipoActo     = lsa_data[0];

					if(lmss_actos != null)
					{
						{
							Map<String, String> lmss_propiedadHorizontal;

							lmss_propiedadHorizontal = generarCodigos(
								    ConstanteCommon.CODIGOS_NATURALEZA_JURIDICA_PROPIEDAD_HORIZONTAL, ldm_manager
								);

							if(
							    (lmss_propiedadHorizontal != null) && (lmss_englobes != null)
								    && lmss_englobes.containsValue(ls_idTipoActo)
							)
							{
								for(Map.Entry<String, String> lmss_iterator : lmss_actos.entrySet())
								{
									if(lmss_iterator != null)
									{
										String ls_idTipoActoIterador;

										ls_idTipoActoIterador = lmss_iterator.getValue();

										if(lmss_propiedadHorizontal.containsValue(ls_idTipoActoIterador))
											throw new B2BException(
											    ErrorKeys.ERROR_ANOTACION_AGREGAR_PROPIEDAD_HORIZONTAL
											);
									}
								}
							}
						}

						{
							NaturalezaJuridica lnj_data;

							lnj_data = lnj_DAO.findByIdMaxVersion(ls_idTipoActo);

							if(lnj_data != null)
							{
								Map<String, String> lmss_grupo0100;
								Map<String, String> lmss_tipoActo0361;

								lmss_grupo0100        = generarCodigos(
									    ConstanteCommon.CODIGO_GRUPO_TRANSFERENCIA_DOMINIO, ldm_manager
									);
								lmss_tipoActo0361     = generarCodigos(
									    ConstanteCommon.CODIGOS_NATURALEZA_JURIDICA_0361, ldm_manager
									);

								if(
								    (lmss_grupo0100 != null)
									    && lmss_grupo0100.containsValue(lnj_data.getIdGrupoNatJur())
								)
								{
									String ls_anotacion;

									ls_anotacion = null;

									for(Map.Entry<String, String> lmss_iterator : lmss_actos.entrySet())
									{
										if((lmss_iterator != null) && !StringUtils.isValidString(ls_anotacion))
										{
											String ls_idTipoActoIterador;

											ls_idTipoActoIterador = lmss_iterator.getValue();

											if(lmss_tipoActo0361.containsValue(ls_idTipoActoIterador))
												ls_anotacion = IdentificadoresCommon.ANOTACION;
										}
									}

									if(StringUtils.isValidString(ls_anotacion))
										lmss_return.put(ls_anotacion, ls_anotacion);
								}
							}
						}
					}

					if((lmss_englobes != null) && lmss_englobes.containsValue(ls_idTipoActo))
						lmss_return.put(IdentificadoresCommon.ENGLOBE, IdentificadoresCommon.ENGLOBE);

					{
						NaturalezaJuridica lnj_data;

						lnj_data = lnj_DAO.findByIdMaxVersion(ls_idTipoActo);

						if(lnj_data != null)
						{
							if(lnj_data.isRequiereCierreFolio())
								lmss_return.put(IdentificadoresCommon.CIERRE_FOLIO, IdentificadoresCommon.CIERRE_FOLIO);
						}
					}
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("validarFechaAnotacionCrearCorrecciones", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		if(lmss_return.isEmpty())
			lmss_return = null;

		return lmss_return;
	}

	/**
	 * Método encargado de validar si la anotación contiene un acto de englobes.
	 *
	 * @param aap_anotacionPredio Objeto que contiene la información de la anotación a validar.
	 * @param ab_validarPredios Variable que indica si se debe validar la cantidad de predios para el englobe.
	 * @return Variable de tipo boolean que indica si la anotación contiene un acto de englobes.
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public synchronized boolean validarAnotacionEnglobe(
	    com.bachue.snr.prosnr01.model.sdb.acc.AnotacionPredio aap_anotacionPredio
	)
	    throws B2BException
	{
		boolean    lb_return;
		DAOManager ldm_manager;

		lb_return       = false;
		ldm_manager     = DaoManagerFactory.getDAOManager();

		try
		{
			lb_return = validarAnotacionEnglobe(aap_anotacionPredio, ldm_manager);
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("validarAnotacionEnglobe", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lb_return;
	}

	/**
	 * Método encargado de validar las anotaciones de los predios segregados a modificar en el proceso de correcciones.
	 *
	 * @param aps_predioSegregado Objeto que contiene la información de las anotaciones.
	 * @return Variable de tipo String que informa si hay alguna advertencia.
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public synchronized String validarAnotacionesPredioSegregado(PredioSegregado aps_predioSegregado)
	    throws B2BException
	{
		DAOManager ldm_manager;
		String     ls_return;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		ls_return       = null;

		try
		{
			if(aps_predioSegregado != null)
			{
				com.bachue.snr.prosnr01.dao.bng.AnotacionPredioDAO lap_DAO;
				String                                             ls_idDocumento;

				lap_DAO            = DaoCreator.getAnotacionPredioDAO(ldm_manager);
				ls_idDocumento     = null;

				{
					Long   ll_idAnotacion;
					Long   ll_idMatricula;
					String ls_idCirculo;

					ll_idAnotacion     = aps_predioSegregado.getIdAnotacion();
					ll_idMatricula     = aps_predioSegregado.getIdMatricula();
					ls_idCirculo       = aps_predioSegregado.getIdCirculo();

					if(NumericUtils.isValidLong(ll_idMatricula) && StringUtils.isValidString(ls_idCirculo))
					{
						if(NumericUtils.isValidLong(ll_idAnotacion, 1L))
						{
							AnotacionPredio lap_anotacion;

							lap_anotacion = new AnotacionPredio();

							lap_anotacion.setIdCirculo(ls_idCirculo);
							lap_anotacion.setIdMatricula(ll_idMatricula);
							lap_anotacion.setIdAnotacion(ll_idAnotacion);

							lap_anotacion = lap_DAO.findById(lap_anotacion);

							if(lap_anotacion == null)
							{
								Object[] loa_arg;

								loa_arg        = new String[3];
								loa_arg[0]     = StringUtils.getString(ll_idAnotacion);
								loa_arg[1]     = ls_idCirculo;
								loa_arg[2]     = StringUtils.getString(ll_idMatricula);

								throw new B2BException(ErrorKeys.ERROR_ANOTACION_SEGREGACION, loa_arg);
							}
							else
							{
								ls_idDocumento = lap_anotacion.getIdDocumento();

								if(!aps_predioSegregado.isNoValidarSegregacion())
								{
									if(
									    !generaSegregacionOApertura(
										        ldm_manager, lap_anotacion.getIdNaturalezaJuridica()
										    )
									)
										ls_return = EstadoCommon.S;
								}
							}
						}
					}
					else
						throw new B2BException(ErrorKeys.ERROR_INTERNO_SISTEMA);
				}

				{
					Long   ll_idAnotacion;
					Long   ll_idMatricula;
					String ls_idCirculo;

					ll_idAnotacion     = aps_predioSegregado.getIdAnotacion1();
					ll_idMatricula     = aps_predioSegregado.getIdMatricula1();
					ls_idCirculo       = aps_predioSegregado.getIdCirculo1();

					if(NumericUtils.isValidLong(ll_idMatricula) && StringUtils.isValidString(ls_idCirculo))
					{
						if(NumericUtils.isValidLong(ll_idAnotacion, 1L))
						{
							AnotacionPredio lap_anotacion;

							lap_anotacion = aps_predioSegregado.isAnotacionesAcc()
								? lap_DAO.findByIdAcc(ls_idCirculo, ll_idMatricula, ll_idAnotacion)
								: lap_DAO.findById(ls_idCirculo, ll_idMatricula, ll_idAnotacion);

							if(lap_anotacion == null)
							{
								Object[] loa_arg;

								loa_arg        = new String[3];
								loa_arg[0]     = StringUtils.getString(ll_idAnotacion);
								loa_arg[1]     = ls_idCirculo;
								loa_arg[2]     = StringUtils.getString(ll_idMatricula);

								throw new B2BException(ErrorKeys.ERROR_ANOTACION1_SEGREGACION, loa_arg);
							}
							else if(!aps_predioSegregado.isNoValidarDocumento())
							{
								if(StringUtils.isValidString(ls_idDocumento))
								{
									String ls_idDocumento1;

									ls_idDocumento1 = lap_anotacion.getIdDocumento();

									if(StringUtils.isValidString(ls_idDocumento1))
									{
										if(!ls_idDocumento.equalsIgnoreCase(ls_idDocumento1))
											ls_return = EstadoCommon.D;
									}
									else
										throw new B2BException(ErrorKeys.ERROR_ANOTACION_DOCUMENTO1);
								}
								else
									throw new B2BException(ErrorKeys.ERROR_ANOTACION_DOCUMENTO);
							}
						}
					}
					else
						throw new B2BException(ErrorKeys.ERROR_INTERNO_SISTEMA);
				}
			}
			else
				throw new B2BException(ErrorKeys.ERROR_INTERNO_SISTEMA);
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("validarAnotacionesPredioSegregado", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return ls_return;
	}

	/**
	 * Método encargado de validar el documento.
	 *
	 * @param almso_documento de almso documento
	 * @return Retorna un string con mensaje.
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public synchronized String validarDocumento(List<Map<String, Object>> almso_documento)
	    throws B2BException
	{
		DAOManager ldm_manager;
		String     ls_mensaje;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		ls_mensaje      = null;

		try
		{
			if(CollectionUtils.isValidCollection(almso_documento))
			{
				for(Map<String, Object> lmso_tmp : almso_documento)
				{
					String ls_idSolicitud;
					Date   ld_fechaSolicitud;

					ls_idSolicitud        = (String)lmso_tmp.get("ID_SOLICITUD_DOC");
					ld_fechaSolicitud     = (Date)lmso_tmp.get("FECHA_SOLICITUD");

					if((ld_fechaSolicitud != null) && (ls_idSolicitud != null))
					{
						Solicitud ls_solicitud;
						ls_solicitud = new Solicitud();

						ls_solicitud.setIdSolicitud(ls_idSolicitud);

						ls_solicitud = DaoCreator.getSolicitudDAO(ldm_manager).findById(ls_solicitud);

						if(ls_solicitud != null)
						{
							Long ll_version;
							Long ll_versionInicial;

							ll_version            = ls_solicitud.getVersionDocumento();
							ll_versionInicial     = ls_solicitud.getVersionDocumentoInicial();

							if((ll_version != null) && (ll_versionInicial != null))
							{
								if(!ll_version.equals(ll_versionInicial))
								{
									Documento ld_documento;
									ld_documento = new Documento();

									ld_documento.setIdDocumento(ls_solicitud.getIdDocumento());
									ld_documento.setVersionDocumento(ll_version);

									ld_documento = DaoCreator.getDocumentoDAO(ldm_manager)
											                     .findByIdDocumentoVersionNombres(ld_documento);

									if(ld_documento != null)
									{
										String ls_mensajeDocumento;

										if(ld_documento.getFechaEjecutoria() != null)
											ls_mensajeDocumento = ld_documento.getNombreTipoDocumento() + ", "
												+ ld_documento.getNombreOficinaOrigen() + ", con fecha de ejecutoria "
												+ ld_documento.getFechaEjecutoria();
										else
											ls_mensajeDocumento = ld_documento.getNombreTipoDocumento() + ", "
												+ ld_documento.getNombreOficinaOrigen();

										Object[] aoa_messageArgs = new String[3];
										aoa_messageArgs[0]     = StringUtils.getString(ls_mensajeDocumento);
										aoa_messageArgs[1]     = StringUtils.getString(
											    ld_documento.getIdUsuarioCreacion()
											);
										aoa_messageArgs[2]     = StringUtils.getString(ld_documento.getFechaCreacion());

										////Cambiar la forma de obtener el mensaje enviando un argumento sin usar un error
										ls_mensaje = new B2BException(
											    ErrorKeys.MODIFICADO_DATOS_SOLICITUD, aoa_messageArgs
											).getMessage();
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

			clh_LOGGER.error("validarDocumento", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return ls_mensaje;
	}

	/**
	 * Valida en base de datos un turno en proceso 37 si registros en la tabla SBD_ACC_PREDIO_REGISTRO.
	 *
	 * @param as_idTurnoHistoria Objeto contenedor del id a utilizar como filtro en la validacion
	 * @return Turno resultante de la consulta
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public synchronized Turno validarEtapaGrabacion(String as_idTurnoHistoria)
	    throws B2BException
	{
		Turno      lt_turno;
		DAOManager ldm_manager;

		lt_turno        = null;
		ldm_manager     = DaoManagerFactory.getDAOManager();

		try
		{
			lt_turno = validarEtapaGrabacion(ldm_manager, as_idTurnoHistoria);
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("validarEtapaGrabacion", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lt_turno;
	}

	/**
	 * Método encargado de validar la existencia de un documento.
	 *
	 * @param ap_parametrodoc de ap parametrodoc
	 * @return el valor de consulta criterios calificacion documento
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public synchronized ConsultaCriteriosCalificacionDocumento validarExistenciaDocumento(
	    ConsultaCriteriosCalificacionDocumento ap_parametrodoc
	)
	    throws B2BException
	{
		DAOManager ldm_manager;

		ldm_manager = DaoManagerFactory.getDAOManager();

		try
		{
			if(ap_parametrodoc != null)
			{
				AnotacionPredio laccas_datos;
				Documento       ld_documento;

				laccas_datos     = ap_parametrodoc.getAnotacionPredio();
				ld_documento     = ap_parametrodoc.getDocumento();

				if(laccas_datos != null)
				{
					{
						String ll_circulo;
						ll_circulo = laccas_datos.getIdCirculo();

						if(!StringUtils.isValidString(ll_circulo))
							throw new B2BException(ErrorKeys.DEBE_SELECCIONAR_CIRCULO_REGISTRAL);
					}

					{
						Long ls_matricula;

						ls_matricula = laccas_datos.getIdMatricula();

						if(!NumericUtils.isValidLong(ls_matricula))
							throw new B2BException(ErrorKeys.DEBE_DIGITAR_MATRICULA);
					}

					{
						Long ls_anotacion;

						ls_anotacion = laccas_datos.getIdAnotacion();

						if(!NumericUtils.isValidLong(ls_anotacion))
							throw new B2BException(ErrorKeys.DEBE_DIGITAR_ANOTACION);
					}
				}

				if(ld_documento != null)
				{
					{
						String lidd_idDocumento;
						lidd_idDocumento = ld_documento.getIdTipoDocumento();

						if(!StringUtils.isValidString(lidd_idDocumento))
							throw new B2BException(ErrorKeys.DEBE_SELECCIONAR_TIPO_DOC);
					}

					{
						String ls_numeroDocumento;
						ls_numeroDocumento = ld_documento.getNumero();

						if(!StringUtils.isValidString(ls_numeroDocumento))
							throw new B2BException(ErrorKeys.DEBE_SELECCIONAR_NUMERO_DOC);
					}

					{
						Date ld_fechaDoc;
						ld_fechaDoc = ld_documento.getFechaDocumento();

						if(ld_fechaDoc == null)
							throw new B2BException(ErrorKeys.DEBE_DIGITAR_FECHA_DOC);
					}

					{
						String ls_tipoOficina;
						ls_tipoOficina = ld_documento.getIdTipoOficina();

						if(!StringUtils.isValidString(ls_tipoOficina))
							throw new B2BException(ErrorKeys.DEBE_SELECCIONAR_TIPO_OFICINA);
					}

					{
						String ls_tipoEntidad;
						ls_tipoEntidad = ld_documento.getTipoEntidad();

						if(!StringUtils.isValidString(ls_tipoEntidad))
							throw new B2BException(ErrorKeys.DEBE_SELECCIONAR_TIPO_ENTIDAD);
					}

					{
						String ls_idpais;
						ls_idpais = ld_documento.getIdPais();

						if(!StringUtils.isValidString(ls_idpais))
							throw new B2BException(ErrorKeys.DEBE_SELECCIONAR_PAIS);
					}

					{
						String ls_idDepartamento;
						ls_idDepartamento = ld_documento.getIdDepartamento();

						if(!StringUtils.isValidString(ls_idDepartamento))
							throw new B2BException(ErrorKeys.DEBE_DIGITAR_DEPARTAMENTO);
					}

					{
						String ls_idMunicipio;
						ls_idMunicipio = ld_documento.getIdMunicipio();

						if(!StringUtils.isValidString(ls_idMunicipio))
							throw new B2BException(ErrorKeys.DEBE_SELECCIONAR_MUNICIPIO);
					}

					{
						String ls_oficinaOrigen;
						ls_oficinaOrigen = ld_documento.getIdOficinaOrigen();

						if(!StringUtils.isValidString(ls_oficinaOrigen))
							throw new B2BException(ErrorKeys.DEBE_SELECCIONAR_OFICINA_ORIGEN);
					}
				}

				ld_documento = DaoCreator.getDocumentoDAO(ldm_manager).consultaDocumento(
					    ap_parametrodoc.getDocumento()
					);

				if(ld_documento != null)
				{
					//DaoCreator.getDocumentoDAO(ldm_manager).
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("ValidacionDocumento", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return ap_parametrodoc;
	}

	/**
	 * Método encargado de validar la fecha de la anotación a crear.
	 *
	 * @param aap_anotacionPredio Objeto que contiene la información de la anotación a validar.
	 * @return
	 * @throws B2BException
	 */
	public synchronized String validarFechaAnotacionCrearCorrecciones(
	    com.bachue.snr.prosnr01.model.sdb.acc.AnotacionPredio aap_anotacionPredio
	)
	    throws B2BException
	{
		DAOManager ldm_manager;
		String     ls_return;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		ls_return       = null;

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
					Date ld_fechaRadicacion;
					int  li_max;

					ld_fechaRadicacion = aap_anotacionPredio.getFechaRadicacion();

					if(ld_fechaRadicacion == null)
						throw new B2BException(ErrorKeys.DEBE_DIGITAR_FECHA_ANOTACION);

					aap_anotacionPredio.setIdTurno(lth_turnoHistoria.getIdTurno());

					li_max = DaoCreator.getAccAnotacionPredioDAO(ldm_manager)
							               .consultarMaxIdAnotacion(aap_anotacionPredio);

					aap_anotacionPredio.setIdAnotacion(NumericUtils.getLongWrapper(li_max));

					aap_anotacionPredio = DaoCreator.getAccAnotacionPredioDAO(ldm_manager)
							                            .findByCirculoMatriculaAnotacion(aap_anotacionPredio);

					if(aap_anotacionPredio != null)
					{
						Date ld_fechaAnotacion;

						ld_fechaAnotacion = aap_anotacionPredio.getFechaRadicacion();

						if(ld_fechaAnotacion != null)
						{
							if(ld_fechaRadicacion.before(ld_fechaAnotacion))
								ls_return = IdentificadoresCommon.FECHA_ANOTACION;
						}
					}
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("validarFechaAnotacionCrearCorrecciones", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return ls_return;
	}

	/**
	 * Método encargado de validar el interviniente seleccionado.
	 *
	 * @param ap_persona Objeto que contiene la información de la persona.
	 * @return
	 * @throws B2BException
	 */
	public synchronized String validarInterviniente(
	    Persona ap_persona, com.bachue.snr.prosnr01.model.sdb.acc.AnotacionPredio aap_anotacionPredio
	)
	    throws B2BException
	{
		DAOManager ldm_manager;
		String     ls_return;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		ls_return       = null;

		try
		{
			if((ap_persona != null) && (aap_anotacionPredio != null))
			{
				String ls_idTipoActo;
				String ls_idPersona;

				ls_idTipoActo     = aap_anotacionPredio.getIdNaturalezaJuridica();
				ls_idPersona      = ap_persona.getIdPersona();

				if(!StringUtils.isValidString(ls_idTipoActo))
					throw new B2BException(ErrorKeys.ERROR_TIPO_ACTO_INVALIDO);

				if(StringUtils.isValidString(ls_idPersona))
				{
					Map<String, String> lmss_0109;
					String[]            lsa_data;

					lsa_data          = ls_idTipoActo.split(IdentificadoresCommon.SIMBOLO_GUION);
					ls_idTipoActo     = lsa_data[0];
					lmss_0109         = generarCodigos(
						    ConstanteCommon.CODIGOS_NATURALEZA_JURIDICA_SUCESION, ldm_manager
						);

					if((lmss_0109 != null) && lmss_0109.containsValue(ls_idTipoActo))
					{
						Collection<Testamento> lct_data;

						lct_data = DaoCreator.getTestamentoDAO(ldm_manager).findByIdPersona(ls_idPersona);

						if(CollectionUtils.isValidCollection(lct_data))
							ls_return = IdentificadoresCommon.INTERVINIENTE;
					}
				}
				else
					throw new B2BException(ErrorKeys.ERROR_INTERNO_SISTEMA);
			}
			else
				throw new B2BException(ErrorKeys.ERROR_INTERNO_SISTEMA);
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("validarInterviniente", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return ls_return;
	}

	/**
	 * Método encargado de validar si es la primera vez que entra al englobe.
	 *
	 * @param arc_data Objeto que contiene la información del englobe.
	 * @return Vairable tipo boolean que indica si es la primera vez que pasa por el englobe.
	 * @throws B2BException
	 */
	public synchronized boolean validarPrimerEnglobeDireccion(RegistroCalificacion arc_data)
	    throws B2BException
	{
		boolean lb_return;

		lb_return = false;

		if(arc_data != null)
		{
			DAOManager ldm_manager;

			ldm_manager = DaoManagerFactory.getDAOManager();

			try
			{
				DireccionPredioAcc lt_param;

				lt_param = new DireccionPredioAcc();

				lt_param.setIdCirculo(arc_data.getIdCirculo());
				lt_param.setIdMatricula(arc_data.getIdMatricula());
				lt_param.setIdTurno(arc_data.getIdTurno());

				lb_return = !CollectionUtils.isValidCollection(
					    DaoCreator.getDireccionPredioAccDAO(ldm_manager).findAllByIdCirculoMatriculaTurno(lt_param)
					);
			}
			catch(B2BException lb2be_e)
			{
				ldm_manager.setRollbackOnly();

				clh_LOGGER.error("validarPrimerEnglobeDireccion", lb2be_e);

				throw lb2be_e;
			}
			finally
			{
				ldm_manager.close();
			}
		}

		return lb_return;
	}

	/**
	 * Validar salvedades.
	 *
	 * @param al_idTurnoHistoria de al id turno historia
	 * @param ab_mensaje de ab mensaje
	 * @return retorna <code>true</code> si se cumple la condicion, de lo contario retorna <code>false</code>
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public boolean validarSalvedades(Long al_idTurnoHistoria, boolean ab_mensaje)
	    throws B2BException
	{
		boolean    lb_return;
		DAOManager ldm_manager;

		lb_return       = false;
		ldm_manager     = DaoManagerFactory.getDAOManager();

		try
		{
			lb_return = validarSalvedades(al_idTurnoHistoria, ldm_manager, null, ab_mensaje);
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("validarSalvedades", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lb_return;
	}

	/**
	 * Método encargado de validar la información de salvedades.
	 *
	 * @param al_idTurnoHistoria Variable de tipo Long que contiene el id del turno historia.
	 * @param asp_salvedad Objeto que contiene la información de la salvedad a guardar.
	 * @param as_observaciones Variable de tipo String que contiene las observaciones.
	 * @param as_idUsuario Variable de tipo String que contiene el id del usuario.
	 * @param as_remoteIp Variable de tipo String que contiene la ip del usuario.
	 * @param ab_validarSegregacion Variable de tipoo boolean que valida si se debe validar las segregaciones
	 * @param acps_datos Colección que contiene los predios segregados a validar.
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void validarSalvedades(
	    Long al_idTurnoHistoria, AccSalvedadPredio asp_salvedad, String as_observaciones, String as_idUsuario,
	    String as_remoteIp, boolean ab_validarSegregacion, Collection<PredioSegregado> acps_datos
	)
	    throws B2BException
	{
		DAOManager ldm_manager;

		ldm_manager = DaoManagerFactory.getDAOManager();

		try
		{
			validarSalvedades(al_idTurnoHistoria, ldm_manager, asp_salvedad, true);

			TurnoHistoriaDAO lth_DAO;
			TurnoHistoria    lth_turnoHistoria;

			lth_DAO               = DaoCreator.getTurnoHistoriaDAO(ldm_manager);
			lth_turnoHistoria     = lth_DAO.findById(al_idTurnoHistoria);

			if(lth_turnoHistoria != null)
			{
				if(asp_salvedad != null)
				{
					Long   ll_idMatricula;
					String ls_idCirculo;

					ll_idMatricula     = asp_salvedad.getIdMatricula();
					ls_idCirculo       = asp_salvedad.getIdCirculo();

					if(ab_validarSegregacion)
					{
						if(NumericUtils.isValidLong(ll_idMatricula) && StringUtils.isValidString(ls_idCirculo))
							guardarDatosSegregacion(
							    acps_datos, StringUtils.getString(al_idTurnoHistoria), lth_turnoHistoria.getIdTurno(),
							    as_idUsuario, as_remoteIp, ls_idCirculo, ll_idMatricula, true, false
							);
					}

					if(asp_salvedad.isCierreFolio())
					{
						String ls_idTurno;

						ls_idTurno = lth_turnoHistoria.getIdTurno();

						if(StringUtils.isValidString(ls_idTurno))
						{
							AccPredioRegistro    lapr_predioRegistro;
							AccPredioRegistroDAO lapr_DAO;

							lapr_DAO                = DaoCreator.getAccPredioRegistroDAO(ldm_manager);
							lapr_predioRegistro     = lapr_DAO.findByCirculoMatriculaTurno(
								    ls_idCirculo, ll_idMatricula, ls_idTurno
								);

							if(lapr_predioRegistro != null)
							{
								CambioEstadoPredio lcep_cambioEstado;

								lcep_cambioEstado = new CambioEstadoPredio();

								lcep_cambioEstado.setIdTurno(ls_idTurno);
								lcep_cambioEstado.setIdCirculo(ls_idCirculo);
								lcep_cambioEstado.setIdMatricula(ll_idMatricula);
								lcep_cambioEstado.setJustificacionCambio(asp_salvedad.getJustificacion());
								lcep_cambioEstado.setMotivoCambioEstado(asp_salvedad.getCambioEstado());
								lcep_cambioEstado.setIdUsuarioCreacion(as_idUsuario);
								lcep_cambioEstado.setIpCreacion(as_remoteIp);
								lapr_predioRegistro.setIdEstadoPredio(EstadoCommon.C);

								DaoCreator.getCambioEstadoPredioDAO(ldm_manager).insertOrUpdate(
								    lcep_cambioEstado, true
								);
								lapr_DAO.updateById(lapr_predioRegistro);
							}
						}
					}
				}

				lth_turnoHistoria.setObservaciones(as_observaciones);
				lth_turnoHistoria.setIdUsuarioModificacion(as_idUsuario);
				lth_turnoHistoria.setIpModificacion(as_remoteIp);

				lth_DAO.updateObservaciones(lth_turnoHistoria);
			}
			else
				throw new B2BException(ErrorKeys.TURNO_HISTORIA_INVALIDO);
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("validarSalvedades", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}
	}

	/**
	 * Metodo encargado de validar si un turno es migrado.
	 *
	 * @param ath_turnoHistoria Argumento de tipo String que contiene el turno a consultar.
	 * @return Variable de tipo boolean con dos valores, (true) si es un turno migrado o
	 * (false) si no es un turno migrado.
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public synchronized boolean validarTurnoMigrado(TurnoHistoria ath_turnoHistoria)
	    throws B2BException
	{
		boolean    lb_etapa165;
		boolean    lb_turnoMigrado;
		DAOManager ldm_manager;

		ldm_manager         = DaoManagerFactory.getDAOManager();
		lb_etapa165         = false;
		lb_turnoMigrado     = false;

		try
		{
			if(ath_turnoHistoria != null)
			{
				String ls_idTurno;

				ls_idTurno = ath_turnoHistoria.getIdTurno();

				if(!StringUtils.isValidString(ls_idTurno))
					throw new B2BException(ErrorKeys.TURNO_INVALIDO);

				{
					Long ll_idTurnoHistoria;

					ll_idTurnoHistoria = ath_turnoHistoria.getIdTurnoHistoria();

					if(NumericUtils.isValidLong(ll_idTurnoHistoria, 1L))
					{
						TurnoHistoriaDAO lthd_DAO;
						TurnoHistoria    lth_turnoHistoriaAnt;

						lthd_DAO                 = DaoCreator.getTurnoHistoriaDAO(ldm_manager);
						lth_turnoHistoriaAnt     = lthd_DAO.findById(ath_turnoHistoria);

						if(lth_turnoHistoriaAnt != null)
						{
							lth_turnoHistoriaAnt = lthd_DAO.findByIdAnterior(lth_turnoHistoriaAnt);

							if(lth_turnoHistoriaAnt != null)
							{
								BigDecimal lbd_idEtapa;

								lbd_idEtapa     = lth_turnoHistoriaAnt.getIdEtapa();

								lb_etapa165 = (lbd_idEtapa != null)
										&& (lbd_idEtapa.compareTo(
										    new BigDecimal(EtapaCommon.ID_ETAPA_HOMOLOGACION_ACTOS_LIQUIDACION)
										) > 0);
							}
						}
					}
				}

				if(!lb_etapa165)
				{
					Turno lt_turno;

					lt_turno = new Turno();
					lt_turno.setIdTurno(ls_idTurno);

					lt_turno = DaoCreator.getTurnoDAO(ldm_manager).findById(lt_turno);

					if(lt_turno != null)
					{
						String ls_migrado;

						ls_migrado     = lt_turno.getMigrado();

						lb_turnoMigrado = StringUtils.isValidString(ls_migrado)
								&& ls_migrado.equalsIgnoreCase(EstadoCommon.S);
					}
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("validarTurnoMigrado", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lb_turnoMigrado;
	}

	/**
	 * Método encargado de consultar los tipos de documento que existen por cada persona de una matrícula.
	 *
	 * @param acap_anotacionesPredio correspondiente al valor de anotaciones predio
	 * @param as_idTurnoHistoria correspondiente al valor de id turno historia
	 * @return Retorna  una lista de datos de tipo LinkedHashMap<String, Object> que coincide con los parametros de entrada.
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public synchronized BigDecimal valorADevolverDeLiquidacion(
	    Collection<com.bachue.snr.prosnr01.model.sdb.acc.AnotacionPredio> acap_anotacionesPredio,
	    String                                                            as_idTurnoHistoria
	)
	    throws B2BException
	{
		BigDecimal lbd_valorADevolver;
		DAOManager ldm_manager;

		ldm_manager            = DaoManagerFactory.getDAOManager();
		lbd_valorADevolver     = NumericUtils.DEFAULT_BIG_DECIMAL_VALUE;

		try
		{
			if(
			    CollectionUtils.isValidCollection(acap_anotacionesPredio)
				    && StringUtils.isValidString(as_idTurnoHistoria)
			)
			{
				TurnoHistoria lth_turnoHistoria;

				lth_turnoHistoria = DaoCreator.getTurnoHistoriaDAO(ldm_manager)
						                          .findById(NumericUtils.getLongWrapper(as_idTurnoHistoria));

				if(lth_turnoHistoria != null)
				{
					for(com.bachue.snr.prosnr01.model.sdb.acc.AnotacionPredio lap_anotacion : acap_anotacionesPredio)
					{
						Liquidacion ll_liquidacion;
						ll_liquidacion = DaoCreator.getAccLiquidacionDAO(ldm_manager)
								                       .findByIdSolicitudOne(lth_turnoHistoria.getIdSolicitud());

						if(ll_liquidacion != null)
						{
							ll_liquidacion.setIdCirculo(lap_anotacion.getIdCirculo());
							ll_liquidacion.setIdTipoActo(lap_anotacion.getIdNaturalezaJuridica());
							ll_liquidacion = DaoCreator.getAccLiquidacionItemDAO(ldm_manager)
									                       .buscarLiquidacionItemTipoActo(ll_liquidacion);

							if(ll_liquidacion != null)
								lbd_valorADevolver = lbd_valorADevolver.add(ll_liquidacion.getValorTotal());
						}
					}
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("valorADevolverDeLiquidacion", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lbd_valorADevolver;
	}

	/**
	 * Método encargado de consultar la relación de  las  solicitudes con matrícula   para un id solicitud  determinado.
	 *
	 * @param as_idTurnoHistoria Objeto que contiene los datos necesarios para ejecutar la sentencia select sobre la tabla SDB_ACC_TURNO_HISTORIA.
	 * @return Retorna  una colección de datos de tipo SolicitudMatricula que coincide con los parametros de entrada.
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public synchronized boolean verificaPropiedad(String as_idTurnoHistoria)
	    throws B2BException
	{
		DAOManager ldm_manager;
		boolean    lb_noPertenecePropiedad;

		lb_noPertenecePropiedad     = false;
		ldm_manager                 = DaoManagerFactory.getDAOManager();

		try
		{
			TurnoHistoria lth_turnoHistoria;
			lth_turnoHistoria = DaoCreator.getTurnoHistoriaDAO(ldm_manager)
					                          .findById(NumericUtils.getLongWrapper(as_idTurnoHistoria));

			if(lth_turnoHistoria != null)
			{
				Solicitud ls_solicitud;
				String    ls_idSolicitud;

				ls_idSolicitud     = lth_turnoHistoria.getIdSolicitud();
				ls_solicitud       = DaoCreator.getSolicitudDAO(ldm_manager).findById(ls_idSolicitud);

				if(ls_solicitud != null)
				{
					Collection<SolicitudMatricula> lcsm_solicitudesMatriculas;
					SolicitudMatricula             ls_solicitudMatricula;

					ls_solicitudMatricula = new SolicitudMatricula();
					ls_solicitudMatricula.setIdSolicitud(ls_idSolicitud);

					lcsm_solicitudesMatriculas = DaoCreator.getSolicitudMatriculaDAO(ldm_manager)
							                                   .findByIdSolicitud(ls_solicitudMatricula);

					if(CollectionUtils.isValidCollection(lcsm_solicitudesMatriculas))
					{
						for(SolicitudMatricula lsm_tmp : lcsm_solicitudesMatriculas)
						{
							if(lsm_tmp != null)
							{
								String ls_idCirculo;
								String ls_respuestaFuncion;
								long   ll_idMatricula;

								ls_idCirculo       = lsm_tmp.getIdCirculo();
								ll_idMatricula     = lsm_tmp.getIdMatricula();

								ls_respuestaFuncion = DaoCreator.getUtilDAO(ldm_manager)
										                            .funcionVerificaPropiedad(
										    ls_solicitud.getIdPersona(), ls_idCirculo, ll_idMatricula
										);

								if(!lb_noPertenecePropiedad)
								{
									if(StringUtils.isValidString(ls_respuestaFuncion))
									{
										if(ls_respuestaFuncion.equalsIgnoreCase(EstadoCommon.S))
											lb_noPertenecePropiedad = false;
										else if(ls_respuestaFuncion.equalsIgnoreCase(EstadoCommon.N))
											lb_noPertenecePropiedad = true;
									}
								}
							}
						}
					}
				}
				else
					throw new B2BException(ErrorKeys.NO_SE_ENCONTRO_SOLICITUD);
			}
			else
				throw new B2BException(ErrorKeys.NO_SE_ENCONTRO_TURNO_HISTORIA);
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findSolicitudMatriculasTH", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lb_noPertenecePropiedad;
	}

	/**
	 * Método encargado de validar si se muestra la bandeja de calificación completa.
	 *
	 * @return Variable de tipo boolean que define si se muestra la bandeja de calificación completa.
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public synchronized boolean visualizarBandeja()
	    throws B2BException
	{
		boolean    lb_return;
		DAOManager ldm_manager;

		lb_return       = false;
		ldm_manager     = DaoManagerFactory.getDAOManager();

		try
		{
			Constantes    lc_constante;
			ConstantesDAO lc_DAO;

			lc_constante     = new Constantes();
			lc_DAO           = DaoCreator.getConstantesDAO(ldm_manager);

			lc_constante.setIdConstante(ConstanteCommon.VISUALIZAR_DETALLE_CALIFICACION);

			lc_constante = lc_DAO.findById(lc_constante);

			if(lc_constante != null)
			{
				String ls_caracter;

				ls_caracter = lc_constante.getCaracter();

				if(StringUtils.isValidString(ls_caracter))
					lb_return = ls_caracter.equalsIgnoreCase(EstadoCommon.S);
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("visualizarBandeja", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lb_return;
	}

	/**
	 * Agrega un String a un StringBuilder y despues agrega un espacio vacio.
	 *
	 * @param asb_stringBuilder StringBuilder a agregar una cadena
	 * @param as_cadena Cadena a agregar
	 */
	private void agregarCadenaConEspacioAStringBuilder(StringBuilder asb_stringBuilder, String as_cadena)
	{
		if((asb_stringBuilder != null) && StringUtils.isValidString(as_cadena))
		{
			asb_stringBuilder.append(as_cadena);
			asb_stringBuilder.append(IdentificadoresCommon.ESPACIO_VACIO);
		}
	}

	/**
	 * Agrega a una cadena la salvedad de un causal de corrección.
	 *
	 * @param asb_cadenaSalvedad Cadena donde se almacenará la información de la salvedad
	 * @param aasp_salvedad Objeto contenedor de la información de la salvedad
	 */
	private void agregarSalvedad(StringBuilder asb_cadenaSalvedad, AccSalvedadPredio aasp_salvedad)
	{
		if(aasp_salvedad != null)
		{
			if((asb_cadenaSalvedad != null) && (aasp_salvedad != null))
			{
				if(!StringUtils.isValidString(asb_cadenaSalvedad.toString()))
				{
					asb_cadenaSalvedad.append(IdentificadoresCommon.PARRAFO_VACIO);
					asb_cadenaSalvedad.append(
					    "{\\rtlch\\fcs1 \\af1\\afs28 \\ltrch\\fcs0 \\b\\f1\\fs28\\insrsid263433 SALVEDADES:}"
					);
				}

				String ls_descripcion;

				ls_descripcion = aasp_salvedad.getDescripcion();

				if(StringUtils.isValidString(ls_descripcion))
				{
					asb_cadenaSalvedad.append(IdentificadoresCommon.PARRAFO_VACIO);
					asb_cadenaSalvedad.append(IdentificadoresCommon.ESTILO_TEXTO_NORMAL);
					asb_cadenaSalvedad.append(ls_descripcion);
					asb_cadenaSalvedad.append(IdentificadoresCommon.CERRAR_LLAVE);
					asb_cadenaSalvedad.append(IdentificadoresCommon.PARRAFO_VACIO);
				}
			}
		}
	}

	/**
	 * Escribe la información de los datos de apertura de una matrícula.
	 *
	 * @param adm_manager Conexión a la base de datos
	 * @param apr_predio Objeto contenedor de la información del predio
	 * @return Cadena de caracteres con la información hallada
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	private String escribirDatosAperturaMatricula(DAOManager adm_manager, PredioRegistro apr_predio)
	    throws B2BException
	{
		String ls_return;

		ls_return = null;

		if(apr_predio != null)
		{
			StringBuilder lsb_infoMatriculas;

			lsb_infoMatriculas = new StringBuilder();

			{
				Date   ld_fechaApertura;
				String ls_radicacion;

				ld_fechaApertura     = apr_predio.getFechaApertura();
				ls_radicacion        = apr_predio.getRadicacion();

				lsb_infoMatriculas.append(
				    "{\\rtlch\\fcs1 \\af1\\afs20 \\ltrch\\fcs0 \\b\\f1\\fs20\\insrsid1080888\\charrsid3408279 {\\*\\bkmkstart _Hlk16841143}{\\*\\bkmkend _Hlk16841082}FECHA APERTURA: }{\\rtlch\\fcs1 \\af1\\afs20 \\ltrch\\fcs0 \\f1\\fs20\\insrsid1080888\\charrsid3408279 "
				);
				lsb_infoMatriculas.append(
				    (ld_fechaApertura != null) ? StringUtils.getString(ld_fechaApertura, "dd/MM/yyyy HH:mm")
				                               : ConstanteCommon.SIN_INFORMACION
				);
				lsb_infoMatriculas.append(IdentificadoresCommon.CERRAR_LLAVE);
				lsb_infoMatriculas.append(
				    "{\\rtlch\\fcs1 \\af1\\afs20 \\ltrch\\fcs0 \\b\\f1\\fs20\\insrsid1080888\\charrsid3408279 }"
				);
				lsb_infoMatriculas.append(
				    "{\\rtlch\\fcs1 \\af1\\afs20 \\ltrch\\fcs0 \\b\\f1\\fs20\\insrsid1080888\\charrsid3408279 RADICACIÓN: }"
				);
				lsb_infoMatriculas.append(IdentificadoresCommon.ESTILO_TEXTO_NORMAL);
				lsb_infoMatriculas.append(
				    StringUtils.isValidString(ls_radicacion) ? ls_radicacion : ConstanteCommon.SIN_INFORMACION
				);
				lsb_infoMatriculas.append(IdentificadoresCommon.CERRAR_LLAVE);
			}

			{
				BigDecimal lbd_versionDoc;
				String     ls_nombreDocumento;
				String     ls_numeroDocumento;
				String     ls_oficinaOrigen;
				String     ls_departamentoOficina;
				String     ls_municipioOficina;
				Date       ld_fechaDocumento;

				lbd_versionDoc             = apr_predio.getVersionDocumento();
				ls_nombreDocumento         = ConstanteCommon.SIN_INFORMACION;
				ls_numeroDocumento         = ConstanteCommon.SIN_INFORMACION;
				ls_oficinaOrigen           = ConstanteCommon.SIN_INFORMACION;
				ls_departamentoOficina     = ConstanteCommon.SIN_INFORMACION;
				ls_municipioOficina        = ConstanteCommon.SIN_INFORMACION;
				ld_fechaDocumento          = null;

				if(NumericUtils.isValidBigDecimal(lbd_versionDoc))
				{
					Documento ld_documento;

					ld_documento = DaoCreator.getDocumentoDAO(adm_manager)
							                     .findByIdDocumentoVersion(
							    apr_predio.getIdDocumento(), NumericUtils.getLongWrapper(lbd_versionDoc)
							);

					if(ld_documento != null)
					{
						{
							OficinaOrigen loo_oficina;

							loo_oficina = DaoCreator.getOficinaOrigenDAO(adm_manager)
									                    .findById(
									    ld_documento.getIdOficinaOrigen(), ld_documento.getVersion()
									);

							if(loo_oficina != null)
							{
								String ls_nombre;
								String ls_idPais;
								String ls_idDepartamento;
								String ls_idMunicipio;

								ls_nombre             = loo_oficina.getNombre();
								ls_idPais             = loo_oficina.getIdPais();
								ls_idDepartamento     = loo_oficina.getIdDepartamento();
								ls_idMunicipio        = loo_oficina.getIdMunicipio();

								if(StringUtils.isValidString(ls_nombre))
									ls_oficinaOrigen = ls_nombre;

								{
									Departamento ld_departamento;

									ld_departamento = DaoCreator.getDepartamentoDAO(adm_manager)
											                        .findById(ls_idPais, ls_idDepartamento);

									if(ld_departamento != null)
									{
										String ls_nombreDep;

										ls_nombreDep = ld_departamento.getNombre();

										if(StringUtils.isValidString(ls_nombreDep))
											ls_departamentoOficina = ls_nombreDep;
									}
								}

								{
									Municipio lm_municipio;

									lm_municipio = DaoCreator.getMunicipioDAO(adm_manager)
											                     .findById(
											    ls_idPais, ls_idDepartamento, ls_idMunicipio
											);

									if(lm_municipio != null)
										ls_municipioOficina = lm_municipio.getNombre();
								}
							}
						}

						{
							TipoDocumentoPublico ltdp_tipoDoc;

							ltdp_tipoDoc = DaoCreator.getTipoDocumentoPublicoDAO(adm_manager)
									                     .findById(ld_documento.getIdTipoDocumento());

							if(ltdp_tipoDoc != null)
							{
								String ls_tmp;

								ls_tmp = ltdp_tipoDoc.getNombre();

								if(StringUtils.isValidString(ls_tmp))
									ls_nombreDocumento = ls_tmp;
							}
						}

						{
							String ls_tmp;

							ls_tmp = ld_documento.getNumero();

							if(StringUtils.isValidString(ls_tmp))
								ls_numeroDocumento = ls_tmp;
						}

						ld_fechaDocumento = ld_documento.getFechaDocumento();
					}
				}

				lsb_infoMatriculas.append(
				    "{\\rtlch\\fcs1 \\af1\\afs20 \\ltrch\\fcs0 \\b\\f1\\fs20\\insrsid1080888\\charrsid3408279  CON }"
				);
				lsb_infoMatriculas.append(IdentificadoresCommon.ESTILO_TEXTO_NORMAL);
				lsb_infoMatriculas.append(ls_nombreDocumento);
				lsb_infoMatriculas.append(IdentificadoresCommon.CERRAR_LLAVE);

				lsb_infoMatriculas.append(
				    "{\\rtlch\\fcs1 \\af1\\afs20 \\ltrch\\fcs0 \\b\\f1\\fs20\\insrsid1080888\\charrsid3408279 NÚMERO DE DOCUMENTO: }"
				);
				lsb_infoMatriculas.append(IdentificadoresCommon.ESTILO_TEXTO_NORMAL);
				lsb_infoMatriculas.append(ls_numeroDocumento);
				lsb_infoMatriculas.append(IdentificadoresCommon.CERRAR_LLAVE);

				lsb_infoMatriculas.append(
				    "{\\rtlch\\fcs1 \\af1\\afs20 \\ltrch\\fcs0 \\b\\f1\\fs20\\insrsid1080888\\charrsid3408279  DE FECHA: }"
				);
				lsb_infoMatriculas.append(IdentificadoresCommon.ESTILO_TEXTO_NORMAL);
				lsb_infoMatriculas.append(
				    (ld_fechaDocumento != null) ? StringUtils.getString(ld_fechaDocumento, "dd/MM/yyyy HH:mm")
				                                : ConstanteCommon.SIN_INFORMACION
				);
				lsb_infoMatriculas.append(IdentificadoresCommon.CERRAR_LLAVE);
				lsb_infoMatriculas.append(
				    "{\\rtlch\\fcs1 \\af1\\afs20 \\ltrch\\fcs0 \\b\\f1\\fs20\\insrsid1080888\\charrsid3408279  DE }"
				);
				lsb_infoMatriculas.append(IdentificadoresCommon.ESTILO_TEXTO_NORMAL);
				lsb_infoMatriculas.append(ls_oficinaOrigen);
				lsb_infoMatriculas.append(IdentificadoresCommon.CERRAR_LLAVE);
				lsb_infoMatriculas.append(
				    "{\\rtlch\\fcs1 \\af1\\afs20 \\ltrch\\fcs0 \\b\\f1\\fs20\\insrsid1080888\\charrsid3408279  DE }"
				);
				lsb_infoMatriculas.append(IdentificadoresCommon.ESTILO_TEXTO_NORMAL);
				lsb_infoMatriculas.append(ls_departamentoOficina);
				lsb_infoMatriculas.append(IdentificadoresCommon.SIMBOLO_COMA);
				lsb_infoMatriculas.append(ls_municipioOficina);
				lsb_infoMatriculas.append(IdentificadoresCommon.CERRAR_LLAVE);
			}

			lsb_infoMatriculas.append(IdentificadoresCommon.PARRAFO_VACIO);

			ls_return = lsb_infoMatriculas.toString();
		}

		return ls_return;
	}

	/**
	 * Consulta la información basica de una matrícula y le da formato.
	 *
	 * @param adm_manager Conexión a la base de datos
	 * @param as_idCirculo id círculo de al cual pertenece la matrícula
	 * @param apr_predio Objeto contenedor de la información del predio
	 * @param ab_bng true para indicar que la información debe salir de la tabla bng, false para acc
	 * @return Cadena de caracteres con la información hallada
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	private String escribirDatosBasicosMatricula(
	    DAOManager adm_manager, String as_idCirculo, PredioRegistro apr_predio, boolean ab_bng
	)
	    throws B2BException
	{
		String ls_return;

		ls_return = null;

		if(StringUtils.isValidString(as_idCirculo) && (apr_predio != null))
		{
			StringBuilder lsb_infoMatriculas;

			lsb_infoMatriculas = new StringBuilder();

			{
				CirculoRegistral lcr_circulo;
				String           ls_codigoNombre;

				lcr_circulo         = DaoCreator.getCirculoRegistralDAO(adm_manager).findById(as_idCirculo);
				ls_codigoNombre     = ConstanteCommon.SIN_INFORMACION;

				if(lcr_circulo != null)
				{
					String ls_tmp;

					ls_tmp = lcr_circulo.getCodigoNombre();

					if(StringUtils.isValidString(ls_tmp))
						ls_codigoNombre = ls_tmp;
				}

				lsb_infoMatriculas.append(
				    "{\\rtlch\\fcs1 \\af1\\afs20 \\ltrch\\fcs0 \\b\\f1\\fs20\\insrsid1080888\\charrsid3408279 {\\*\\bkmkstart _Hlk16841082}{\\*\\bkmkend _Hlk16841261}CIRCULO REGISTRAL: }"
				);
				lsb_infoMatriculas.append(IdentificadoresCommon.ESTILO_TEXTO_NORMAL);
				lsb_infoMatriculas.append(ls_codigoNombre);
				lsb_infoMatriculas.append(IdentificadoresCommon.CERRAR_LLAVE);
			}

			{
				ZonaRegistral lzr_zona;
				String        ls_departamento;
				String        ls_municipio;
				String        ls_vereda;

				lzr_zona            = DaoCreator.getZonaRegistralDAO(adm_manager)
						                            .findById(apr_predio.getIdZonaRegistral());
				ls_departamento     = ConstanteCommon.SIN_INFORMACION;
				ls_municipio        = ConstanteCommon.SIN_INFORMACION;
				ls_vereda           = ConstanteCommon.SIN_INFORMACION;

				if(lzr_zona != null)
				{
					String ls_idPais;
					String ls_idDepartamento;
					String ls_idMunicipio;
					String ls_idVereda;

					ls_idPais             = lzr_zona.getIdPais();
					ls_idDepartamento     = lzr_zona.getIdDepartamento();
					ls_idMunicipio        = lzr_zona.getIdMunicipio();
					ls_idVereda           = lzr_zona.getIdVereda();

					{
						Departamento ld_depto;

						ld_depto = DaoCreator.getDepartamentoDAO(adm_manager).findById(ls_idPais, ls_idDepartamento);

						if(ld_depto != null)
						{
							String ls_tmp;

							ls_tmp = ld_depto.getNombre();

							if(StringUtils.isValidString(ls_tmp))
								ls_departamento = ls_tmp;
						}
					}

					{
						Municipio lm_mun;

						lm_mun = DaoCreator.getMunicipioDAO(adm_manager)
								               .findById(ls_idPais, ls_idDepartamento, ls_idMunicipio);

						if(lm_mun != null)
						{
							String ls_tmp;

							ls_tmp = lm_mun.getNombre();

							if(StringUtils.isValidString(ls_tmp))
								ls_municipio = ls_tmp;
						}
					}

					{
						Vereda lv_vereda;

						lv_vereda = DaoCreator.getVeredaDAO(adm_manager)
								                  .findById(ls_idPais, ls_idDepartamento, ls_idMunicipio, ls_idVereda);

						if(lv_vereda != null)
						{
							String ls_tmp;

							ls_tmp = lv_vereda.getNombre();

							if(StringUtils.isValidString(ls_tmp))
								ls_vereda = ls_tmp;
						}
					}
				}

				lsb_infoMatriculas.append(
				    "{\\rtlch\\fcs1 \\af1\\afs20 \\ltrch\\fcs0 \\b\\f1\\fs20\\insrsid1080888\\charrsid3408279 DPTO: }"
				);
				lsb_infoMatriculas.append(IdentificadoresCommon.ESTILO_TEXTO_NORMAL);
				lsb_infoMatriculas.append(ls_departamento);
				lsb_infoMatriculas.append(IdentificadoresCommon.CERRAR_LLAVE);

				lsb_infoMatriculas.append(
				    "{\\rtlch\\fcs1 \\af1\\afs20 \\ltrch\\fcs0 \\b\\f1\\fs20\\insrsid1080888\\charrsid3408279 MUNICIPIO: }"
				);
				lsb_infoMatriculas.append(IdentificadoresCommon.ESTILO_TEXTO_NORMAL);
				lsb_infoMatriculas.append(ls_municipio);
				lsb_infoMatriculas.append(IdentificadoresCommon.CERRAR_LLAVE);

				lsb_infoMatriculas.append(
				    "{\\rtlch\\fcs1 \\af1\\afs20 \\ltrch\\fcs0 \\b\\f1\\fs20\\insrsid1080888\\charrsid3408279 VEREDA: }"
				);
				lsb_infoMatriculas.append(IdentificadoresCommon.ESTILO_TEXTO_NORMAL);
				lsb_infoMatriculas.append(ls_vereda);
				lsb_infoMatriculas.append(IdentificadoresCommon.CERRAR_LLAVE);
			}

			if(!ab_bng)
			{
				lsb_infoMatriculas.append("{\\rtlch\\fcs1 \\af1\\afs20 \\ltrch\\fcs0 \\f1\\fs20\\insrsid1080888  }");
				lsb_infoMatriculas.append(
				    "{\\rtlch\\fcs1 \\af1\\afs20 \\ltrch\\fcs0 \\b\\f1\\fs20\\insrsid5440371\\charrsid3408279 NUMERO DE IDENTIFICACIÓN NUPRE: }"
				);
				lsb_infoMatriculas.append(IdentificadoresCommon.ESTILO_TEXTO_NORMAL);

				{
					String ls_nupre;

					ls_nupre = apr_predio.getNupre();

					if(StringUtils.isValidString(ls_nupre))
						lsb_infoMatriculas.append(ls_nupre);
					else
						lsb_infoMatriculas.append(ConstanteCommon.SIN_INFORMACION);
				}

				lsb_infoMatriculas.append(IdentificadoresCommon.CERRAR_LLAVE);

				lsb_infoMatriculas.append(
				    "{\\rtlch\\fcs1 \\af1\\afs20 \\ltrch\\fcs0 \\b\\f1\\fs20\\insrsid1080888\\charrsid3408279 {\\*\\bkmkstart _Hlk16841191}Zona: }"
				);
				lsb_infoMatriculas.append(IdentificadoresCommon.ESTILO_TEXTO_NORMAL);

				{
					PredioTipo lpt_predioTipo;

					lpt_predioTipo = DaoCreator.getPredioTipoDao(adm_manager).findById(apr_predio.getIdTipoPredio());

					if(lpt_predioTipo != null)
						lsb_infoMatriculas.append(lpt_predioTipo.getDescripcion());
					else
						lsb_infoMatriculas.append(ConstanteCommon.SIN_INFORMACION);
				}

				lsb_infoMatriculas.append(IdentificadoresCommon.CERRAR_LLAVE);
			}
			else
				lsb_infoMatriculas.append(IdentificadoresCommon.PARRAFO_VACIO);

			ls_return = lsb_infoMatriculas.toString();
		}

		return ls_return;
	}

	/**
	 * Escribe las direcciones del predio para registros guardados en acc.
	 *
	 * @param adm_manager Conexión a la base de datos
	 * @param as_idCirculo id del círculo al cual pertenece la matrícula
	 * @param al_idMatricula id de la matrícula asociada al turno
	 * @param ai_maxIdDireccion id de la máxima dirección asociada a la matrícula
	 * @param as_idTurno id del turno asociado a la matrícula
	 * @param ab_ultimaDireccion true para traer solo la ultima dirección, false para todas las demas
	 * @return Cadena de caracteres con la información consultada
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	private String escribirDireccionesPredioAcc(
	    DAOManager adm_manager, String as_idCirculo, long al_idMatricula, int ai_maxIdDireccion, String as_idTurno,
	    boolean ab_ultimaDireccion
	)
	    throws B2BException
	{
		String                         ls_return;
		Collection<DireccionPredioAcc> lcdpa_direcciones;

		ls_return             = ConstanteCommon.SIN_INFORMACION;
		lcdpa_direcciones     = DaoCreator.getDireccionPredioAccDAO(adm_manager)
				                              .findByCirculoMatriculaIdDireccion(
				    as_idCirculo, al_idMatricula, StringUtils.getString(ai_maxIdDireccion), as_idTurno,
				    ab_ultimaDireccion
				);

		if(CollectionUtils.isValidCollection(lcdpa_direcciones))
		{
			Collection<DireccionPredio> lcdp_direcciones;

			lcdp_direcciones = new LinkedList<DireccionPredio>();

			for(DireccionPredioAcc ldpa_data : lcdpa_direcciones)
			{
				if(ldpa_data != null)
					lcdp_direcciones.add(ldpa_data.getDireccionPredio());
			}

			if(CollectionUtils.isValidCollection(lcdp_direcciones))
			{
				String ls_direcciones;

				ls_direcciones = escribirDireccionesPredio(adm_manager, lcdp_direcciones);

				if(StringUtils.isValidString(ls_direcciones))
					ls_return = ls_direcciones;
			}
		}

		return ls_return;
	}

	/**
	 * Escribe la información catastral de una matrícula.
	 *
	 * @param adm_manager Conexión a la base de datos
	 * @param apr_predio Objeto contenedor de la información del predio
	 * @return Cadena de caracteres con la información hallada
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	private String escribirInfoCatastralMatricula(DAOManager adm_manager, PredioRegistro apr_predio)
	    throws B2BException
	{
		String ls_return;

		ls_return = null;

		if(apr_predio != null)
		{
			StringBuilder lsb_infoMatriculas;

			lsb_infoMatriculas = new StringBuilder();

			lsb_infoMatriculas.append(
			    "{\\rtlch\\fcs1 \\af1\\afs20 \\ltrch\\fcs0 \\b\\f1\\fs20\\insrsid1080888\\charrsid3408279 {\\*\\bkmkstart _Hlk16841191}Zona: }"
			);
			lsb_infoMatriculas.append(IdentificadoresCommon.ESTILO_TEXTO_NORMAL);

			{
				PredioTipo lpt_predioTipo;
				String     ls_predioTipo;

				lpt_predioTipo     = DaoCreator.getPredioTipoDao(adm_manager).findById(apr_predio.getIdTipoPredio());
				ls_predioTipo      = ConstanteCommon.SIN_INFORMACION;

				if(lpt_predioTipo != null)
				{
					String ls_tmp;

					ls_tmp = lpt_predioTipo.getDescripcion();

					if(StringUtils.isValidString(ls_tmp))
						ls_predioTipo = ls_tmp;
				}

				lsb_infoMatriculas.append(ls_predioTipo);
			}

			lsb_infoMatriculas.append(IdentificadoresCommon.CERRAR_LLAVE);

			lsb_infoMatriculas.append("{\\rtlch\\fcs1 \\af1\\afs20 \\ltrch\\fcs0 \\f1\\fs20\\insrsid1080888  }");
			lsb_infoMatriculas.append(
			    "{\\rtlch\\fcs1 \\af1\\afs20 \\ltrch\\fcs0 \\b\\f1\\fs20\\insrsid1080888\\charrsid3408279 CODIGO CATASTRAL: }"
			);
			lsb_infoMatriculas.append(IdentificadoresCommon.ESTILO_TEXTO_NORMAL);

			{
				String ls_numeroPredial;

				ls_numeroPredial = apr_predio.getNumeroPredial();

				if(StringUtils.isValidString(ls_numeroPredial))
					lsb_infoMatriculas.append(ls_numeroPredial);
				else
					lsb_infoMatriculas.append(ConstanteCommon.SIN_INFORMACION);
			}

			lsb_infoMatriculas.append(IdentificadoresCommon.CERRAR_LLAVE);

			lsb_infoMatriculas.append(
			    "{\\rtlch\\fcs1 \\af1\\afs20 \\ltrch\\fcs0 \\b\\f1\\fs20\\insrsid1080888\\charrsid3408279 CODIGO CATASTRAL ANTERIOR: }"
			);
			lsb_infoMatriculas.append(
			    "{\\rtlch\\fcs1 \\ab\\af1\\afs20 \\ltrch\\fcs0 \\f1\\fs20\\insrsid1080888\\charrsid8405434 "
			);

			{
				String ls_numPredAnt;

				ls_numPredAnt = apr_predio.getNumeroPredialAnt();

				if(StringUtils.isValidString(ls_numPredAnt))
					lsb_infoMatriculas.append(ls_numPredAnt);
				else
					lsb_infoMatriculas.append(ConstanteCommon.SIN_INFORMACION);
			}

			lsb_infoMatriculas.append(IdentificadoresCommon.CERRAR_LLAVE);

			ls_return = lsb_infoMatriculas.toString();
		}

		return ls_return;
	}

	/**
	 * Método encargado de retornar las matriculas asociadas a una solicitud.
	 *
	 * @param as_idTurno Objeto de tipo String quer contiene IdTurno a consultar
	 * @param as_userId Variable de tipo String que contiene el usuario que realiza la acción.
	 * @param as_localIp Variable de tipo String que contiene la Ip local del servidor que realiza la acción.
	 * @param as_remoteIp Variable de tipo String que contiene la Ip remota del usuario que realiza la acción.
	 * @param ldm_manager de ldm manager
	 * @return el valor de collection
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	private synchronized Collection<SolicitudMatricula> findMatriculasByIdSolicitud(
	    String as_idTurno, String as_userId, String as_localIp, String as_remoteIp, DAOManager ldm_manager
	)
	    throws B2BException
	{
		Collection<SolicitudMatricula> lcsm_return;

		lcsm_return = null;

		if(StringUtils.isValidString(as_idTurno))
		{
			Turno lt_turno;

			lt_turno = DaoCreator.getTurnoDAO(ldm_manager).findById(as_idTurno);

			if(lt_turno != null)
			{
				String ls_idCirculo;
				String ls_idSolicitud;

				ls_idCirculo       = lt_turno.getIdCirculo();
				ls_idSolicitud     = lt_turno.getIdSolicitud();

				if(StringUtils.isValidString(ls_idCirculo) && StringUtils.isValidString(ls_idSolicitud))
					lcsm_return = DaoCreator.getSolicitudMatriculaDAO(ldm_manager)
							                    .findByIdSolicitudCirculo(ls_idSolicitud, ls_idCirculo);
			}
		}

		return lcsm_return;
	}

	/**
	 * Construye el formulario de correcciones.
	 *
	 * @param as_idTurnoHistoria id turno historia del tramite en proceso
	 * @param as_userId id del usuario que ejecuta la acción
	 * @param as_remoteIp de as remote ip
	 * @param ldm_manager DAOManager que controla las transacciones
	 * @param ab_salvar boolean que decide si el documento se debe salvar
	 * @return arreglo de bytes correspondiente al documento generado
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	private synchronized byte[] generarFormularioCorrecciones(
	    String as_idTurnoHistoria, String as_userId, String as_remoteIp, DAOManager ldm_manager, boolean ab_salvar
	)
	    throws B2BException
	{
		byte[] lba_return;

		lba_return = null;

		try
		{
			if(!StringUtils.isValidString(as_idTurnoHistoria))
				throw new B2BException(ErrorKeys.TURNO_HISTORIA_INVALIDO);

			Long          ll_idTurnoHistoria;
			TurnoHistoria lth_turnoHistoria;

			ll_idTurnoHistoria     = NumericUtils.getLongWrapper(as_idTurnoHistoria);
			lth_turnoHistoria      = DaoCreator.getTurnoHistoriaDAO(ldm_manager).findById(ll_idTurnoHistoria);

			if(lth_turnoHistoria != null)
			{
				Collection<ZipEntryUtil> lczeu_zip;
				String                   ls_idSolicitud;
				String                   ls_idTurno;
				Solicitud                ls_solicitud;

				lczeu_zip          = new ArrayList<ZipEntryUtil>();
				ls_idSolicitud     = lth_turnoHistoria.getIdSolicitud();
				ls_idTurno         = lth_turnoHistoria.getIdTurno();
				ls_solicitud       = DaoCreator.getSolicitudDAO(ldm_manager).findById(ls_idSolicitud);

				{
					byte[]       lba_formularioCorrecciones;
					ZipEntryUtil lzeu_pdf;

					lba_formularioCorrecciones = generarFormularioCorrecciones(
						    ldm_manager, ls_idSolicitud, ls_idTurno, as_idTurnoHistoria, as_userId, as_remoteIp,
						    ab_salvar
						);

					if(lba_formularioCorrecciones != null)
					{
						lzeu_pdf = new ZipEntryUtil(
							    ConstanteCommon.PLANTILLA_FORMULARIO_CORRECCIONES + ExtensionCommon.PDF_MAYUS_PUNTO,
							    new ByteArrayInputStream(lba_formularioCorrecciones)
							);

						lczeu_zip.add(lzeu_pdf);
					}
				}

				if(ls_solicitud != null)
				{
					Persona lp_persona;

					lp_persona = DaoCreator.getPersonaDAO(ldm_manager).findById(ls_solicitud.getIdPersona());

					if(lp_persona != null)
					{
						String ls_tipoDoc;

						ls_tipoDoc = StringUtils.getStringNotNull(lp_persona.getIdDocumentoTipo());

						if(ls_tipoDoc.equals(IdentificadoresCommon.NIT))
						{
							byte[]       lba_plantillaEntidadSiProcede;
							ZipEntryUtil lzeu_pdf;

							lba_plantillaEntidadSiProcede     = generarPlantillaEntidadCorreccionSiProcede(
								    ldm_manager, ls_idSolicitud, ls_idTurno, as_userId, ll_idTurnoHistoria, as_remoteIp,
								    ab_salvar
								);
							lzeu_pdf                          = new ZipEntryUtil(
								    ConstanteCommon.PLANTILLA_OFICIO_REMISORIO_ENTIDAD_CORRECCION_SIPROCEDE
								    + ExtensionCommon.PDF_MAYUS_PUNTO,
								    new ByteArrayInputStream(lba_plantillaEntidadSiProcede)
								);

							lczeu_zip.add(lzeu_pdf);
						}
					}
				}

				if(CollectionUtils.isValidCollection(lczeu_zip))
					lba_return = ZipUtils.generateZip(lczeu_zip);
			}
			else
				throw new B2BException(ErrorKeys.NO_SE_ENCONTRO_ID_TURNO_HISTORIA);
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("generarFormularioCorrecciones", lb2be_e);

			throw lb2be_e;
		}

		return lba_return;
	}

	/**
	 * Construye el formulario de correcciones.
	 *
	 * @param adm_manager Conexión a la base de datos
	 * @param as_idSolicitud id de la solicitud asociada al proceso
	 * @param as_idTurno id del turno en tramite
	 * @param as_idTurnoHistoria id del turno historia actual
	 * @param as_userId id del usuario que ejecuta la acción
	 * @param as_remoteIp de as remote ip
	 * @param ab_salvar de ab salvar
	 * @return arreglo de bytes correspondiente al documento generado
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	private byte[] generarFormularioCorrecciones(
	    DAOManager adm_manager, String as_idSolicitud, String as_idTurno, String as_idTurnoHistoria, String as_userId,
	    String as_remoteIp, boolean ab_salvar
	)
	    throws B2BException
	{
		byte[]                         lba_return;
		Collection<SolicitudMatricula> lcsm_matriculas;
		String                         ls_plantilla;

		lba_return = null;

		if(!StringUtils.isValidString(as_idSolicitud))
			throw new B2BException(ErrorKeys.ERROR_SIN_ID_SOLICITUD);

		if(!StringUtils.isValidString(as_idTurno))
			throw new B2BException(ErrorKeys.ERROR_SIN_ID_TURNO);

		ls_plantilla = obtenerPlantillaDeConstante(adm_manager, ConstanteCommon.PLANTILLA_FORMULARIO_CORRECCIONES);

		if(StringUtils.isValidString(ls_plantilla))
		{
			String ls_tag;

			{
				Turno lt_turno;

				lt_turno = DaoCreator.getTurnoDAO(adm_manager).findById(as_idTurno);

				if(lt_turno != null)
				{
					String           ls_idCirculo;
					CirculoRegistral lcr_circulo;

					ls_idCirculo     = lt_turno.getIdCirculo();
					lcr_circulo      = DaoCreator.getCirculoRegistralDAO(adm_manager).findById(ls_idCirculo);

					ls_plantilla     = escribirInfoCirculoRegistral(lcr_circulo, ls_plantilla);

					ls_tag = "<ID_TURNO>";

					if(ls_plantilla.contains(ls_tag))
						ls_plantilla = ls_plantilla.replace(ls_tag, lt_turno.getIdTurno());

					ls_tag = "<TAG_FECHA_TURNO>";

					if(ls_plantilla.contains(ls_tag))
						ls_plantilla = ls_plantilla.replace(
							    ls_tag, StringUtils.getString(lt_turno.getFechaCreacion(), "dd/MM/yyyy")
							);

					lcsm_matriculas = DaoCreator.getSolicitudMatriculaDAO(adm_manager)
							                        .findByIdSolicitudCirculo(as_idSolicitud, ls_idCirculo);
				}
				else
					throw new B2BException(ErrorKeys.ERROR_SIN_ID_TURNO);
			}

			if(CollectionUtils.isValidCollection(lcsm_matriculas))
			{
				Map<String, String>          lmss_datos;
				StringBuilder                lsb_tagSolicitudMatriculas;
				StringBuilder                lsb_infoMatriculas;
				Iterator<SolicitudMatricula> lism_iterador;

				lmss_datos                     = null;
				lsb_tagSolicitudMatriculas     = new StringBuilder();
				lsb_infoMatriculas             = new StringBuilder();
				lism_iterador                  = lcsm_matriculas.iterator();

				ls_plantilla     = escribirTagFechaHora(ls_plantilla);

				ls_tag = "<ID_NIR>";

				if(ls_plantilla.contains(ls_tag))
				{
					Solicitud ls_solicitud;

					ls_solicitud = DaoCreator.getSolicitudDAO(adm_manager).findById(as_idSolicitud);

					if(ls_solicitud != null)
						ls_plantilla = ls_plantilla.replace(
							    ls_tag, StringUtils.getStringNotNull(ls_solicitud.getNir())
							);
				}

				while(lism_iterador.hasNext())
				{
					SolicitudMatricula lsm_matricula;

					lsm_matricula = lism_iterador.next();

					if(lsm_matricula != null)
					{
						String                          ls_idCirculo;
						long                            ll_idMatricula;
						Collection<SolicitudCorreccion> lcsc_solicitudesCorreccion;
						StringBuilder                   lsb_cadenaDatosBasicos;
						StringBuilder                   lsb_cadenaDescripcionPredio;
						StringBuilder                   lsb_cadenaAnotaciones;
						PredioRegistro                  lpr_predio;
						StringBuilder                   lsb_salvedadDatosBasicos;
						StringBuilder                   lsb_salvedadDescripcionPredio;
						StringBuilder                   lsb_salvedadAnotaciones;

						ls_idCirculo                      = lsm_matricula.getIdCirculo();
						ll_idMatricula                    = lsm_matricula.getIdMatricula();
						lcsc_solicitudesCorreccion        = DaoCreator.getSolicitudCorreccionDAO(adm_manager)
								                                          .findBySolicitudCirculoMatricula(
								    as_idSolicitud, ls_idCirculo, NumericUtils.getBigInteger(ll_idMatricula), true,
								    false
								);
						lsb_cadenaDatosBasicos            = new StringBuilder();
						lsb_cadenaDescripcionPredio       = new StringBuilder();
						lsb_cadenaAnotaciones             = new StringBuilder();
						lpr_predio                        = obtenerInfoPredio(
							    adm_manager, ls_idCirculo, ll_idMatricula, as_idTurno, true
							);
						lsb_salvedadDatosBasicos          = new StringBuilder();
						lsb_salvedadDescripcionPredio     = new StringBuilder();
						lsb_salvedadAnotaciones           = new StringBuilder();

						lsb_tagSolicitudMatriculas.append(ls_idCirculo);
						lsb_tagSolicitudMatriculas.append(IdentificadoresCommon.SIMBOLO_GUION);
						lsb_tagSolicitudMatriculas.append(ll_idMatricula);

						if(lism_iterador.hasNext())
							lsb_tagSolicitudMatriculas.append(IdentificadoresCommon.SIMBOLO_COMA);

						lsb_infoMatriculas.append(
						    "\\pard\\plain \\ltrpar\\ql \\li0\\ri0\\sa160\\sl259\\slmult1\\widctlpar\\wrapdefault\\aspalpha\\aspnum\\faauto\\adjustright\\rin0\\lin0\\itap0\\pararsid13959478 \\rtlch\\fcs1 \\af31507\\afs22\\alang1025 \\ltrch\\fcs0 \\f31506\\fs22\\lang9226\\langfe1033\\cgrid\\langnp9226\\langfenp1033"
						);
						lsb_infoMatriculas.append(
						    "{\\rtlch\\fcs1 \\af1\\afs20 \\ltrch\\fcs0 \\b\\f1\\fs26\\insrsid750731 \\par }"
						);
						lsb_infoMatriculas.append(
						    "{\\rtlch\\fcs1 \\af1\\afs20 \\ltrch\\fcs0 \\b\\f1\\fs26\\insrsid750731\\charrsid3408279 {\\*\\bkmkstart _Hlk16841270}MATRICULA INMOBILIARIA: "
						);
						lsb_infoMatriculas.append(ls_idCirculo);
						lsb_infoMatriculas.append(IdentificadoresCommon.SIMBOLO_GUION);
						lsb_infoMatriculas.append(ll_idMatricula);
						lsb_infoMatriculas.append("\\par }");

						{
							String ls_datosBasicosBng;

							ls_datosBasicosBng = escribirDatosBasicosMatricula(
								    adm_manager, ls_idCirculo, lpr_predio, true
								);

							if(StringUtils.isValidString(ls_datosBasicosBng))
							{
								lsb_infoMatriculas.append(ls_datosBasicosBng);
								lsb_infoMatriculas.append(IdentificadoresCommon.PARRAFO_VACIO);
							}
						}

						{
							String ls_datosAperturaBng;

							ls_datosAperturaBng = escribirDatosAperturaMatricula(adm_manager, lpr_predio);

							if(StringUtils.isValidString(ls_datosAperturaBng))
							{
								lsb_infoMatriculas.append(ls_datosAperturaBng);
								lsb_infoMatriculas.append(IdentificadoresCommon.PARRAFO_VACIO);
							}
						}

						{
							String ls_infoCatastralBng;

							ls_infoCatastralBng = escribirInfoCatastralMatricula(adm_manager, lpr_predio);

							if(StringUtils.isValidString(ls_infoCatastralBng))
							{
								lsb_infoMatriculas.append(ls_infoCatastralBng);
								lsb_infoMatriculas.append(IdentificadoresCommon.PARRAFO_VACIO);
							}
						}

						if(CollectionUtils.isValidCollection(lcsc_solicitudesCorreccion))
						{
							PredioRegistro          lpr_accPredio;
							AccPredioSegregadoDAO   lapsd_predioSegregadoDAO;
							DireccionPredioAccDAO   ldpad_direccionPredioAccDAO;
							AccLinderoPredioDAO     lalpd_accLinderoPredioDAO;
							AccAreaPredioDAO        laapd_accAreaPredioDAO;
							AnotacionPredioDAO      lapd_accAnotacionPredioDAO;
							AccSalvedadPredioDAO    laspd_accSalvedadPredioDAO;
							AccSalvedadAnotacionDAO lasad_accSalvedadAnotacionDAO;
							boolean                 lb_datosAntEscritos;
							boolean                 lb_anotacionesEscritas;

							lpr_accPredio                     = obtenerInfoPredio(
								    adm_manager, ls_idCirculo, ll_idMatricula, as_idTurno, false
								);
							lapsd_predioSegregadoDAO          = DaoCreator.getAccPredioSegregadoDAO(adm_manager);
							ldpad_direccionPredioAccDAO       = DaoCreator.getDireccionPredioAccDAO(adm_manager);
							lalpd_accLinderoPredioDAO         = DaoCreator.getAccLinderoPredioDAO(adm_manager);
							laapd_accAreaPredioDAO            = DaoCreator.getAccAreaPredioDAO(adm_manager);
							lapd_accAnotacionPredioDAO        = DaoCreator.getAccAnotacionPredioDAO(adm_manager);
							laspd_accSalvedadPredioDAO        = DaoCreator.getAccSalvedadPredioDAO(adm_manager);
							lasad_accSalvedadAnotacionDAO     = DaoCreator.getAccSalvedadAnotacionDAO(adm_manager);
							lb_datosAntEscritos               = false;
							lb_anotacionesEscritas            = false;

							for(SolicitudCorreccion lsc_data : lcsc_solicitudesCorreccion)
							{
								if(lsc_data != null)
								{
									BigInteger lbi_idCausalCorreccion;

									lbi_idCausalCorreccion = lsc_data.getIdCausalCorreccion();

									if(NumericUtils.isValidBigInteger(lbi_idCausalCorreccion))
									{
										String            ls_idCausal;
										AccSalvedadPredio lasp_salvedad;

										ls_idCausal       = lbi_idCausalCorreccion.toString();
										lasp_salvedad     = laspd_accSalvedadPredioDAO.findById(
											    as_idTurno, ls_idCirculo, NumericUtils.getLongWrapper(ll_idMatricula),
											    NumericUtils.getLongWrapper(lbi_idCausalCorreccion)
											);

										if(ls_idCausal.equals(CausalCorreccionCommon.DATOS_BASICOS))
										{
											inicializarCadena(lsb_cadenaDatosBasicos, true, false, false);

											lsb_cadenaDatosBasicos.append(
											    "{\\rtlch\\fcs1 \\af1\\afs28 \\ltrch\\fcs0 \\b\\f1\\fs28\\insrsid263433 DATOS  BASICOS PREDIO:}"
											);
											lsb_cadenaDatosBasicos.append(IdentificadoresCommon.PARRAFO_VACIO);

											{
												String ls_datosBasicosAcc;

												ls_datosBasicosAcc = escribirDatosBasicosMatricula(
													    adm_manager, ls_idCirculo, lpr_accPredio, false
													);

												if(StringUtils.isValidString(ls_datosBasicosAcc))
													lsb_cadenaDatosBasicos.append(ls_datosBasicosAcc);
											}

											lsb_cadenaDatosBasicos.append(IdentificadoresCommon.PARRAFO_VACIO);
											lsb_cadenaDatosBasicos.append(IdentificadoresCommon.PARRAFO_VACIO);

											agregarSalvedad(lsb_salvedadDatosBasicos, lasp_salvedad);
										}
										else if(ls_idCausal.equals(CausalCorreccionCommon.INFORMACION_DE_APERTURA))
										{
											inicializarCadena(lsb_cadenaDatosBasicos, true, false, false);

											lsb_cadenaDatosBasicos.append(
											    "{\\rtlch\\fcs1 \\af1\\afs28 \\ltrch\\fcs0 \\b\\f1\\fs28\\insrsid263433 {\\*\\bkmkstart _Hlk16841153}INFORMACION DE APERTURA:}"
											);
											lsb_cadenaDatosBasicos.append(IdentificadoresCommon.PARRAFO_VACIO);

											{
												String ls_datosAperturaAcc;

												ls_datosAperturaAcc = escribirDatosAperturaMatricula(
													    adm_manager, lpr_accPredio
													);

												if(StringUtils.isValidString(ls_datosAperturaAcc))
													lsb_cadenaDatosBasicos.append(ls_datosAperturaAcc);
											}

											lsb_cadenaDatosBasicos.append(IdentificadoresCommon.PARRAFO_VACIO);
											lsb_cadenaDatosBasicos.append(IdentificadoresCommon.PARRAFO_VACIO);

											agregarSalvedad(lsb_salvedadDatosBasicos, lasp_salvedad);
										}
										else if(
										    ls_idCausal.equals(
											        CausalCorreccionCommon.MATRICULAS_ABIERTAS_CON_BASE_EN_LAS_SIGUIENTES_MATRICULAS
											    )
										)
										{
											inicializarCadena(lsb_cadenaDatosBasicos, true, false, false);

											lsb_cadenaDatosBasicos.append(
											    "{\\rtlch\\fcs1 \\ab\\af1\\afs20 \\ltrch\\fcs0 \\b\\f1\\fs28\\insrsid263433 MATRICULAS ABIERTAS CON BASE EN:}"
											);
											lsb_cadenaDatosBasicos.append(IdentificadoresCommon.PARRAFO_VACIO);

											Collection<com.bachue.snr.prosnr01.model.sdb.acc.PredioSegregado> lcps_prediosSegregados;

											lcps_prediosSegregados = lapsd_predioSegregadoDAO
													.findAllByCirculo1Matricula1(ls_idCirculo, ll_idMatricula);

											if(CollectionUtils.isValidCollection(lcps_prediosSegregados))
											{
												StringBuilder lsb_tmp;

												lsb_tmp = new StringBuilder();

												for(com.bachue.snr.prosnr01.model.sdb.acc.PredioSegregado lps_data : lcps_prediosSegregados)
												{
													if(lps_data != null)
													{
														lsb_tmp.append(IdentificadoresCommon.ESTILO_TEXTO_NORMAL);
														lsb_tmp.append(lps_data.getIdCirculo());
														lsb_tmp.append(IdentificadoresCommon.SIMBOLO_GUION);
														lsb_tmp.append(lps_data.getIdMatricula());
														lsb_tmp.append(IdentificadoresCommon.CERRAR_LLAVE);
														lsb_tmp.append(IdentificadoresCommon.PARRAFO_VACIO);
													}
												}

												lsb_cadenaDatosBasicos.append(lsb_tmp.toString());
											}
											else
											{
												lsb_cadenaDatosBasicos.append(
												    IdentificadoresCommon.ESTILO_TEXTO_NORMAL
												);
												lsb_cadenaDatosBasicos.append(ConstanteCommon.SIN_INFORMACION);
												lsb_cadenaDatosBasicos.append(IdentificadoresCommon.CERRAR_LLAVE);
											}

											lsb_cadenaDatosBasicos.append(IdentificadoresCommon.PARRAFO_VACIO);
											lsb_cadenaDatosBasicos.append(IdentificadoresCommon.PARRAFO_VACIO);

											agregarSalvedad(lsb_salvedadDatosBasicos, lasp_salvedad);
										}
										else if(ls_idCausal.equals(CausalCorreccionCommon.INFORMACION_CATASTRAL))
										{
											inicializarCadena(lsb_cadenaDatosBasicos, true, false, false);

											lsb_cadenaDatosBasicos.append(
											    "{\\rtlch\\fcs1 \\af1\\afs28 \\ltrch\\fcs0 \\b\\f1\\fs28\\insrsid263433 INFORMACION CATASTRAL:}"
											);
											lsb_cadenaDatosBasicos.append(IdentificadoresCommon.PARRAFO_VACIO);

											{
												String ls_infoCatastralAcc;

												ls_infoCatastralAcc = escribirInfoCatastralMatricula(
													    adm_manager, lpr_accPredio
													);

												if(StringUtils.isValidString(ls_infoCatastralAcc))
													lsb_cadenaDatosBasicos.append(ls_infoCatastralAcc);
											}

											lsb_cadenaDatosBasicos.append(IdentificadoresCommon.PARRAFO_VACIO);
											lsb_cadenaDatosBasicos.append(IdentificadoresCommon.PARRAFO_VACIO);

											agregarSalvedad(lsb_salvedadDatosBasicos, lasp_salvedad);
										}
										else if(ls_idCausal.equals(CausalCorreccionCommon.COMPLEMENTACION))
										{
											inicializarCadena(lsb_cadenaDescripcionPredio, false, true, false);

											lsb_cadenaDescripcionPredio.append(
											    "{\\rtlch\\fcs1 \\ab\\af1\\afs20 \\ltrch\\fcs0 \\b\\f1\\fs28\\insrsid10302282\\charrsid5440371 COMPLEMENTACIÓN:}"
											);
											lsb_cadenaDescripcionPredio.append(IdentificadoresCommon.PARRAFO_VACIO);

											if(lpr_accPredio != null)
											{
												BigDecimal lbd_idComplementacion;

												lbd_idComplementacion = lpr_accPredio.getIdComplementacion();

												if(NumericUtils.isValidBigDecimal(lbd_idComplementacion))
												{
													lsb_cadenaDescripcionPredio.append(
													    IdentificadoresCommon.ESTILO_TEXTO_NORMAL
													);
													lsb_cadenaDescripcionPredio.append(
													    escribirComplementacionPredio(
													        adm_manager, lbd_idComplementacion.toString()
													    )
													);
													lsb_cadenaDescripcionPredio.append(
													    IdentificadoresCommon.CERRAR_LLAVE
													);
													lsb_cadenaDescripcionPredio.append(
													    IdentificadoresCommon.PARRAFO_VACIO
													);
												}
											}

											lsb_cadenaDescripcionPredio.append(IdentificadoresCommon.PARRAFO_VACIO);
											lsb_cadenaDescripcionPredio.append(IdentificadoresCommon.PARRAFO_VACIO);

											agregarSalvedad(lsb_salvedadDescripcionPredio, lasp_salvedad);
										}
										else if(ls_idCausal.equals(CausalCorreccionCommon.AREA_DEL_PREDIO))
										{
											inicializarCadena(lsb_cadenaDescripcionPredio, false, true, false);

											lsb_cadenaDescripcionPredio.append(
											    "{\\rtlch\\fcs1 \\ab\\af1\\afs20 \\ltrch\\fcs0 \\b\\f1\\fs28\\insrsid10302282\\charrsid5440371 AREA PREDIO:}"
											);
											lsb_cadenaDescripcionPredio.append(IdentificadoresCommon.PARRAFO_VACIO);

											Long          ll_idMatrObj;
											AccAreaPredio laap_areaPredio;
											String        ls_areaPredio;
											String        ls_areaConstruida;
											String        ls_areaPrivada;
											String        ls_coeficiente;

											ll_idMatrObj          = NumericUtils.getLongWrapper(ll_idMatricula);
											laap_areaPredio       = laapd_accAreaPredioDAO.findByCirculoMatriculaTurno(
												    ls_idCirculo, ll_idMatrObj, as_idTurno
												);
											ls_areaPredio         = ConstanteCommon.SIN_INFORMACION;
											ls_areaConstruida     = ConstanteCommon.SIN_INFORMACION;
											ls_areaPrivada        = ConstanteCommon.SIN_INFORMACION;
											ls_coeficiente        = ConstanteCommon.SIN_INFORMACION;

											if(laap_areaPredio != null)
											{
												AccDetalleAreaPredioDAO ldapd_detalleAreaPredioDAO;
												String                  ls_idAreaPredio;
												DetalleAreaPredio       ldap_detalle;
												String                  ls_detalle;

												ldapd_detalleAreaPredioDAO     = DaoCreator.getAccDetalleAreaPredioDAO(
													    adm_manager
													);
												ls_idAreaPredio                = laap_areaPredio.getIdArea();

												ldap_detalle     = ldapd_detalleAreaPredioDAO.findByIdAreaPredioTipo(
													    ls_idAreaPredio, ls_idCirculo, ll_idMatrObj,
													    TipoAreaCommon.TERRENO
													);

												ls_detalle = obtenerAreaMedidaUnidad(adm_manager, ldap_detalle);

												if(StringUtils.isValidString(ls_detalle))
													ls_areaPredio = ls_detalle;

												ldap_detalle     = ldapd_detalleAreaPredioDAO.findByIdAreaPredioTipo(
													    ls_idAreaPredio, ls_idCirculo, ll_idMatrObj,
													    TipoAreaCommon.CONSTRUIDA
													);

												ls_detalle = obtenerAreaMedidaUnidad(adm_manager, ldap_detalle);

												if(StringUtils.isValidString(ls_detalle))
													ls_areaConstruida = ls_detalle;

												ldap_detalle     = ldapd_detalleAreaPredioDAO.findByIdAreaPredioTipo(
													    ls_idAreaPredio, ls_idCirculo, ll_idMatrObj,
													    TipoAreaCommon.PRIVADA
													);

												ls_detalle = obtenerAreaMedidaUnidad(adm_manager, ldap_detalle);

												if(StringUtils.isValidString(ls_detalle))
													ls_areaPrivada = ls_detalle;

												{
													Double ld_coeficiente;

													ld_coeficiente = laap_areaPredio.getCoeficiente();

													if(NumericUtils.isValidDouble(ld_coeficiente))
														ls_coeficiente = StringUtils.getString(ld_coeficiente);
												}
											}

											lsb_cadenaDescripcionPredio.append(
											    "{\\rtlch\\fcs1 \\af1\\afs20 \\ltrch\\fcs0 \\b\\f1\\fs20\\insrsid2293951\\charrsid3408279 AREA DEL INMUEBLE: }"
											);
											lsb_cadenaDescripcionPredio.append(
											    IdentificadoresCommon.ESTILO_TEXTO_NORMAL
											);
											lsb_cadenaDescripcionPredio.append(ls_areaPredio);
											lsb_cadenaDescripcionPredio.append(IdentificadoresCommon.CERRAR_LLAVE);

											lsb_cadenaDescripcionPredio.append(
											    "{\\rtlch\\fcs1 \\af1\\afs20 \\ltrch\\fcs0 \\b\\f1\\fs20\\insrsid2293951\\charrsid3408279 AREA CONSTRUIDA: }"
											);
											lsb_cadenaDescripcionPredio.append(
											    IdentificadoresCommon.ESTILO_TEXTO_NORMAL
											);
											lsb_cadenaDescripcionPredio.append(ls_areaConstruida);
											lsb_cadenaDescripcionPredio.append(IdentificadoresCommon.CERRAR_LLAVE);

											lsb_cadenaDescripcionPredio.append(
											    "{\\rtlch\\fcs1 \\af1\\afs20 \\ltrch\\fcs0 \\b\\f1\\fs20\\insrsid2293951\\charrsid3408279 AREA PRIVADA: }"
											);
											lsb_cadenaDescripcionPredio.append(
											    IdentificadoresCommon.ESTILO_TEXTO_NORMAL
											);
											lsb_cadenaDescripcionPredio.append(ls_areaPrivada);
											lsb_cadenaDescripcionPredio.append(IdentificadoresCommon.CERRAR_LLAVE);

											lsb_cadenaDescripcionPredio.append(
											    "{\\rtlch\\fcs1 \\af1\\afs20 \\ltrch\\fcs0 \\b\\f1\\fs20\\insrsid2293951\\charrsid3408279 COEFICIENTE DE COPROPIEDAD: }"
											);
											lsb_cadenaDescripcionPredio.append(
											    IdentificadoresCommon.ESTILO_TEXTO_NORMAL
											);
											lsb_cadenaDescripcionPredio.append(ls_coeficiente);
											lsb_cadenaDescripcionPredio.append(IdentificadoresCommon.CERRAR_LLAVE);

											lsb_cadenaDescripcionPredio.append(IdentificadoresCommon.PARRAFO_VACIO);
											lsb_cadenaDescripcionPredio.append(IdentificadoresCommon.PARRAFO_VACIO);

											agregarSalvedad(lsb_salvedadDescripcionPredio, lasp_salvedad);
										}
										else if(ls_idCausal.equals(CausalCorreccionCommon.DIRECCION_DEL_PREDIO))
										{
											inicializarCadena(lsb_cadenaDescripcionPredio, false, true, false);

											lsb_cadenaDescripcionPredio.append(
											    "{\\rtlch\\fcs1 \\ab\\af1\\afs20 \\ltrch\\fcs0 \\b\\f1\\fs28\\insrsid10302282\\charrsid5440371 DIRECCIÓN ACTUAL: }"
											);
											lsb_cadenaDescripcionPredio.append(IdentificadoresCommon.PARRAFO_VACIO);

											int li_maxIdDireccion;

											li_maxIdDireccion = ldpad_direccionPredioAccDAO.findMaxIdDireccion(
												    ls_idCirculo, NumericUtils.getLongWrapper(ll_idMatricula),
												    as_idTurno
												);

											if(li_maxIdDireccion > 0)
											{
												lsb_cadenaDescripcionPredio.append(
												    IdentificadoresCommon.ESTILO_TEXTO_NORMAL
												);
												lsb_cadenaDescripcionPredio.append(
												    escribirDireccionesPredioAcc(
												        adm_manager, ls_idCirculo, ll_idMatricula, li_maxIdDireccion,
												        as_idTurno, true
												    )
												);
												lsb_cadenaDescripcionPredio.append(IdentificadoresCommon.CERRAR_LLAVE);
											}

											lsb_cadenaDescripcionPredio.append(IdentificadoresCommon.PARRAFO_VACIO);
											lsb_cadenaDescripcionPredio.append(IdentificadoresCommon.PARRAFO_VACIO);

											agregarSalvedad(lsb_salvedadDescripcionPredio, lasp_salvedad);
										}
										else if(
										    ls_idCausal.equals(CausalCorreccionCommon.DATOS_ANOTACION)
											    || ls_idCausal.equals(CausalCorreccionCommon.DATOS_DEL_DOCUMENTO)
											    || ls_idCausal.equals(CausalCorreccionCommon.ESPECIFICACION)
											    || ls_idCausal.equals(
											        CausalCorreccionCommon.DATOS_BASICOS_DEL_INTEVINIENTES
											    ) || ls_idCausal.equals(CausalCorreccionCommon.AGREGAR_ANOTACION)
										)
										{
											if(!lb_anotacionesEscritas)
											{
												inicializarCadena(lsb_cadenaAnotaciones, false, false, true);

												lsb_cadenaAnotaciones.append(IdentificadoresCommon.PARRAFO_VACIO);

												Collection<com.bachue.snr.prosnr01.model.sdb.acc.AnotacionPredio> lcap_anotaciones;

												lcap_anotaciones = lapd_accAnotacionPredioDAO
														.findByCirculoMatriculaTurno(
														    ls_idCirculo, NumericUtils.getLongWrapper(ll_idMatricula),
														    as_idTurno
														);

												if(CollectionUtils.isValidCollection(lcap_anotaciones))
												{
													Collection<AnotacionPredio> lcap_bngAnotaciones;

													lcap_bngAnotaciones = new LinkedList<AnotacionPredio>();

													for(com.bachue.snr.prosnr01.model.sdb.acc.AnotacionPredio lap_data : lcap_anotaciones)
													{
														if(lap_data != null)
															lcap_bngAnotaciones.add(new AnotacionPredio(lap_data));
													}

													if(CollectionUtils.isValidCollection(lcap_bngAnotaciones))
													{
														String ls_anotaciones;

														ls_anotaciones = escribirAnotaciones(
															    adm_manager, lcap_bngAnotaciones, ls_idCirculo,
															    ll_idMatricula, true, false, as_idTurno
															);

														if(StringUtils.isValidString(ls_anotaciones))
															lsb_cadenaAnotaciones.append(ls_anotaciones);
													}
												}

												lb_anotacionesEscritas = true;

												lsb_cadenaAnotaciones.append(IdentificadoresCommon.PARRAFO_VACIO);
												lsb_cadenaAnotaciones.append(IdentificadoresCommon.PARRAFO_VACIO);
											}

											{
												Collection<AccSalvedadAnotacion> lcasa_salvedadAnotacion;

												lcasa_salvedadAnotacion = lasad_accSalvedadAnotacionDAO
														.findAllAnotacioneslById(
														    as_idTurno, ls_idCirculo,
														    NumericUtils.getLongWrapper(ll_idMatricula)
														);

												if(CollectionUtils.isValidCollection(lcasa_salvedadAnotacion))
												{
													lsb_salvedadAnotaciones.append(IdentificadoresCommon.PARRAFO_VACIO);
													lsb_salvedadAnotaciones.append(
													    "{\\rtlch\\fcs1 \\af1\\afs28 \\ltrch\\fcs0 \\b\\f1\\fs28\\insrsid263433 SALVEDADES ANOTACIONES:}"
													);

													for(AccSalvedadAnotacion lasa_iterador : lcasa_salvedadAnotacion)
													{
														if(lasa_iterador != null)
														{
															String ls_descripcion;

															ls_descripcion = lasa_iterador.getDescripcion();

															if(StringUtils.isValidString(ls_descripcion))
															{
																lsb_salvedadAnotaciones.append(
																    IdentificadoresCommon.PARRAFO_VACIO
																);
																lsb_salvedadAnotaciones.append(
																    IdentificadoresCommon.ESTILO_TEXTO_NORMAL
																);
																lsb_salvedadAnotaciones.append(ls_descripcion);
																lsb_salvedadAnotaciones.append(
																    IdentificadoresCommon.CERRAR_LLAVE
																);
																lsb_salvedadAnotaciones.append(
																    IdentificadoresCommon.PARRAFO_VACIO
																);
															}
														}
													}
												}
											}
										}
										else if(ls_idCausal.equals(CausalCorreccionCommon.MATRICULAS_SEGREGADAS))
										{
											inicializarCadena(lsb_cadenaDatosBasicos, true, false, false);

											lsb_cadenaDatosBasicos.append(
											    "{\\rtlch\\fcs1 \\af1\\afs20 \\ltrch\\fcs0 \\b\\f1\\fs28\\insrsid263433 SEGREGACION:}"
											);
											lsb_cadenaDatosBasicos.append(IdentificadoresCommon.PARRAFO_VACIO);

											Collection<com.bachue.snr.prosnr01.model.sdb.acc.PredioSegregado> lcps_prediosSegregados;

											lcps_prediosSegregados = lapsd_predioSegregadoDAO.findAllByCirculoMatricula(
												    ls_idCirculo, ll_idMatricula
												);

											if(CollectionUtils.isValidCollection(lcps_prediosSegregados))
											{
												StringBuilder                                                   lsb_tmp;
												Iterator<com.bachue.snr.prosnr01.model.sdb.acc.PredioSegregado> lips_iterador;

												lsb_tmp           = new StringBuilder();
												lips_iterador     = lcps_prediosSegregados.iterator();

												while(lips_iterador.hasNext())
												{
													com.bachue.snr.prosnr01.model.sdb.acc.PredioSegregado lps_data;

													lps_data = lips_iterador.next();

													if(lps_data != null)
													{
														int li_maxIdDireccion;

														li_maxIdDireccion = ldpad_direccionPredioAccDAO
																.findMaxIdDireccion(
																    ls_idCirculo,
																    NumericUtils.getLongWrapper(ll_idMatricula),
																    as_idTurno
																);

														lsb_tmp.append(IdentificadoresCommon.ESTILO_TEXTO_NORMAL);
														lsb_tmp.append(lps_data.getIdCirculo1());
														lsb_tmp.append(IdentificadoresCommon.SIMBOLO_GUION);
														lsb_tmp.append(lps_data.getIdMatricula1());

														if(li_maxIdDireccion > 0)
														{
															lsb_tmp.append(IdentificadoresCommon.SIMBOLO_COMA);
															lsb_tmp.append(
															    escribirDireccionesPredioAcc(
															        adm_manager, ls_idCirculo, ll_idMatricula,
															        li_maxIdDireccion, as_idTurno, true
															    )
															);
														}

														if(lips_iterador.hasNext())
															lsb_tmp.append(IdentificadoresCommon.PARRAFO_VACIO);
													}
												}

												{
													String ls_matriculasSegregadas;

													ls_matriculasSegregadas = lsb_tmp.toString();

													if(StringUtils.isValidString(ls_matriculasSegregadas))
														lsb_cadenaDatosBasicos.append(ls_matriculasSegregadas);
												}
											}
											else
											{
												lsb_cadenaDatosBasicos.append(
												    IdentificadoresCommon.ESTILO_TEXTO_NORMAL
												);
												lsb_cadenaDatosBasicos.append(ConstanteCommon.SIN_INFORMACION);
												lsb_cadenaDatosBasicos.append(IdentificadoresCommon.CERRAR_LLAVE);
											}

											lsb_cadenaDatosBasicos.append(IdentificadoresCommon.PARRAFO_VACIO);
											lsb_cadenaDatosBasicos.append(IdentificadoresCommon.PARRAFO_VACIO);

											agregarSalvedad(lsb_salvedadDatosBasicos, lasp_salvedad);
										}
										else if(
										    ls_idCausal.equals(CausalCorreccionCommon.DATOS_ANT_SISTEMA)
											    || ls_idCausal.equals(CausalCorreccionCommon.DETALLE_ANT_SISTEMA)
											    || ls_idCausal.equals(
											        CausalCorreccionCommon.DETALLE_ANT_SISTEMA_ANOTACION
											    )
										)
										{
											if(!lb_datosAntEscritos)
											{
												inicializarCadena(lsb_cadenaDatosBasicos, true, false, false);

												lsb_cadenaDatosBasicos.append(
												    "{\\rtlch\\fcs1 \\af1\\afs20 \\ltrch\\fcs0 \\b\\f1\\fs28\\insrsid263433 DATOS ANTIGUO SISTEMA:}"
												);
												lsb_cadenaDatosBasicos.append(IdentificadoresCommon.PARRAFO_VACIO);

												if(lpr_accPredio != null)
												{
													String ls_idDatosAntSistema;

													ls_idDatosAntSistema = lpr_accPredio.getIdAntiguoSistema();

													if(StringUtils.isValidString(ls_idDatosAntSistema))
													{
														{
															String ls_infoDatosAnt;

															ls_infoDatosAnt = escribirDatosAntSistema(
																    adm_manager, ls_idDatosAntSistema
																);

															if(StringUtils.isValidString(ls_infoDatosAnt))
															{
																lsb_cadenaDatosBasicos.append(ls_infoDatosAnt);
																lsb_cadenaDatosBasicos.append(
																    IdentificadoresCommon.PARRAFO_VACIO
																);
															}
														}

														{
															String ls_infoDetalleAnt;

															ls_infoDetalleAnt = escribirDetalleAntSistema(
																    adm_manager, ls_idDatosAntSistema
																);

															if(StringUtils.isValidString(ls_infoDetalleAnt))
															{
																lsb_cadenaDatosBasicos.append(ls_infoDetalleAnt);
																lsb_cadenaDatosBasicos.append(
																    IdentificadoresCommon.PARRAFO_VACIO
																);
															}
														}
													}
													else
													{
														lsb_cadenaDatosBasicos.append(
														    IdentificadoresCommon.ESTILO_TEXTO_NORMAL
														);
														lsb_cadenaDatosBasicos.append(ConstanteCommon.SIN_INFORMACION);
														lsb_cadenaDatosBasicos.append(
														    IdentificadoresCommon.CERRAR_LLAVE
														);
													}
												}

												lb_datosAntEscritos = true;

												lsb_cadenaDatosBasicos.append(IdentificadoresCommon.PARRAFO_VACIO);
												lsb_cadenaDatosBasicos.append(IdentificadoresCommon.PARRAFO_VACIO);
											}

											agregarSalvedad(lsb_salvedadDatosBasicos, lasp_salvedad);
										}
										else if(ls_idCausal.equals(CausalCorreccionCommon.LINDEROS))
										{
											inicializarCadena(lsb_cadenaDescripcionPredio, false, true, false);

											lsb_cadenaDescripcionPredio.append(
											    "{\\rtlch\\fcs1 \\ab\\af1\\afs20 \\ltrch\\fcs0 \\b\\f1\\fs28\\insrsid10302282\\charrsid5440371 LINDEROS:}"
											);
											lsb_cadenaDescripcionPredio.append(IdentificadoresCommon.PARRAFO_VACIO);

											AccLinderoPredio lalp_lindero;
											String           ls_lindero;

											lalp_lindero     = lalpd_accLinderoPredioDAO.findByCirculoMatricula(
												    ls_idCirculo, NumericUtils.getLongWrapper(ll_idMatricula)
												);
											ls_lindero       = ConstanteCommon.SIN_INFORMACION;

											if(lalp_lindero != null)
											{
												String ls_tmp;

												ls_tmp = lalp_lindero.getLindero();

												if(StringUtils.isValidString(ls_tmp))
													ls_lindero = ls_tmp;
											}

											lsb_cadenaDescripcionPredio.append(
											    IdentificadoresCommon.ESTILO_TEXTO_NORMAL
											);
											lsb_cadenaDescripcionPredio.append(ls_lindero);
											lsb_cadenaDescripcionPredio.append(IdentificadoresCommon.CERRAR_LLAVE);
											lsb_cadenaDescripcionPredio.append(IdentificadoresCommon.PARRAFO_VACIO);

											lsb_cadenaDescripcionPredio.append(IdentificadoresCommon.PARRAFO_VACIO);
											lsb_cadenaDescripcionPredio.append(IdentificadoresCommon.PARRAFO_VACIO);

											agregarSalvedad(lsb_salvedadDescripcionPredio, lasp_salvedad);
										}
									}
								}
							}
						}

						{
							String ls_cadenaDatosBasicos;

							ls_cadenaDatosBasicos = lsb_cadenaDatosBasicos.toString();

							if(StringUtils.isValidString(ls_cadenaDatosBasicos))
							{
								lsb_infoMatriculas.append(ls_cadenaDatosBasicos);
								lsb_infoMatriculas.append(lsb_salvedadDatosBasicos);
							}
						}

						{
							String ls_cadenaDescripcionPredio;

							ls_cadenaDescripcionPredio = lsb_cadenaDescripcionPredio.toString();

							if(StringUtils.isValidString(ls_cadenaDescripcionPredio))
							{
								lsb_infoMatriculas.append(ls_cadenaDescripcionPredio);
								lsb_infoMatriculas.append(lsb_salvedadDescripcionPredio);
							}
						}

						{
							String ls_cadenaAnotaciones;

							ls_cadenaAnotaciones = lsb_cadenaAnotaciones.toString();

							if(StringUtils.isValidString(ls_cadenaAnotaciones))
							{
								lsb_infoMatriculas.append(ls_cadenaAnotaciones);
								lsb_infoMatriculas.append(lsb_salvedadAnotaciones);
							}
						}

						lsb_infoMatriculas.append(IdentificadoresCommon.SEPARADOR_DE_MATRICULA);
					}
				}

				ls_tag = "<TAG_USUARIO>";

				if(ls_plantilla.contains(ls_tag) && StringUtils.isValidString(as_userId))
					ls_plantilla = ls_plantilla.replace(ls_tag, as_userId);

				ls_tag = "<TAG_INFO_MATRICULAS>";

				if(ls_plantilla.contains(ls_tag))
				{
					String ls_infoMatriculas;

					ls_infoMatriculas = lsb_infoMatriculas.toString();

					if(StringUtils.isValidString(ls_infoMatriculas))
						ls_plantilla = ls_plantilla.replace(ls_tag, ls_infoMatriculas);
				}

				ls_tag = "<TAG_SOLICITUD_MATRICULA>";

				if(ls_plantilla.contains(ls_tag))
				{
					String ls_solicitudMatricula;

					ls_solicitudMatricula = lsb_tagSolicitudMatriculas.toString();

					if(StringUtils.isValidString(ls_solicitudMatricula))
						ls_plantilla = ls_plantilla.replace(ls_tag, ls_solicitudMatricula);
				}

				lmss_datos = finalizarPlantilla(
					    ls_plantilla, NumericUtils.getLongWrapper(as_idTurnoHistoria), adm_manager
					);
				;
				ls_plantilla     = lmss_datos.get(IdentificadoresCommon.PLANTILLA);
				lba_return       = new PDFGenerator().convertirRTFToPDF(ls_plantilla.getBytes(), adm_manager);

				if(lba_return == null)
					throw new B2BException(ErrorKeys.NO_SE_GENERO_RESOLUCION);

				if(ab_salvar)
				{
					Imagenes    li_imagenes;
					ImagenesDAO li_DAO;
					long        ll_idImagenTemp;

					li_imagenes     = new Imagenes();
					li_DAO          = DaoCreator.getImagenesDAO(adm_manager);

					li_imagenes.setTipoArchivo(ExtensionCommon.PDF_MAYUS);
					li_imagenes.setIdUsuarioCreacion(as_userId);
					li_imagenes.setIpCreacion(as_remoteIp);
					li_imagenes.setImagenBlob(lba_return);
					li_imagenes.setCodigoVerificacion(lmss_datos.get(IdentificadoresCommon.CODIGO_VERIFICACION));

					ll_idImagenTemp = li_DAO.insertOrUpdate(li_imagenes, true);

					if(ll_idImagenTemp > 0)
					{
						DocumentosSalida    lds_documentoSalida;
						DocumentosSalidaDAO lds_DAO;
						Long                ll_idImagen;

						lds_documentoSalida     = new DocumentosSalida();
						lds_DAO                 = DaoCreator.getDocumentosSalidaDAO(adm_manager);
						ll_idImagen             = NumericUtils.getLongWrapper(ll_idImagenTemp);

						lds_documentoSalida.setIdSolicitud(as_idSolicitud);
						lds_documentoSalida.setIdTurno(as_idTurno);
						lds_documentoSalida.setTipo(TipoArchivoCommon.CERTIFICADO_CORRECCIONES);
						lds_documentoSalida.setEstado(EstadoCommon.ACTIVO);
						lds_documentoSalida.setReferenciaSalida(as_idTurno);

						lds_documentoSalida = lds_DAO.findDocumentByTurnoTipoEstadoReferenciaSalida(
							    lds_documentoSalida
							);

						if(lds_documentoSalida != null)
						{
							lds_documentoSalida.setIdImagen(ll_idImagen);
							lds_documentoSalida.setIdUsuarioModificacion(as_userId);
							lds_documentoSalida.setIpModificacion(as_remoteIp);

							lds_DAO.updateImagen(lds_documentoSalida);
						}
						else
						{
							lds_documentoSalida = new DocumentosSalida();

							lds_documentoSalida.setIdTurno(as_idTurno);
							lds_documentoSalida.setIdSolicitud(as_idSolicitud);
							lds_documentoSalida.setIdImagen(ll_idImagen);
							lds_documentoSalida.setTipo(TipoArchivoCommon.CERTIFICADO_CORRECCIONES);
							lds_documentoSalida.setIdTipoDocumental(TipoDocumentalCommon.CERTIFICACION);
							lds_documentoSalida.setRepositorio(RepositorioCommon.FINAL);
							lds_documentoSalida.setEstado(EstadoCommon.ACTIVO);
							lds_documentoSalida.setReferenciaSalida(as_idTurno);
							lds_documentoSalida.setIdTurnoHistoria(NumericUtils.getInteger(as_idTurnoHistoria));
							lds_documentoSalida.setIdUsuarioCreacion(as_userId);
							lds_documentoSalida.setIpCreacion(as_remoteIp);

							lds_DAO.insertOrUpdate(lds_documentoSalida, true);
						}
					}
					else
						throw new B2BException(ErrorKeys.NO_SE_GENERO_ARCHIVO);
				}
			}
		}

		return lba_return;
	}

	/**
	 * Construye la plantilla entidad corrección si procede.
	 *
	 * @param adm_manager Conexión a la base de datos
	 * @param as_idSolicitud id de la solicitud del tramite
	 * @param as_idTurno id del turno del tramite
	 * @param as_userId id del usuario que ejecuta la acción
	 * @param al_idTurnoHistoria de al id turno historia
	 * @param as_remoteIp de as remote ip
	 * @param ab_salvar de ab salvar
	 * @return Arreglo de bytes contenedor de la imagen de la plantilla
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	private byte[] generarPlantillaEntidadCorreccionSiProcede(
	    DAOManager adm_manager, String as_idSolicitud, String as_idTurno, String as_userId, Long al_idTurnoHistoria,
	    String as_remoteIp, boolean ab_salvar
	)
	    throws B2BException
	{
		return generarPlantillaEntidadCorreccionSiProcede(
		    adm_manager, as_idSolicitud, as_idTurno, as_userId, al_idTurnoHistoria, as_remoteIp, ab_salvar, false
		);
	}

	/**
	 * Método encargado de guardar los datos de segregación para correcciones.
	 *
	 * @param acps_prediosSegregados Colección que contiene los predios a guardar.
	 * @param as_idTurnoHistoria Variable que contiene el id del turno historia.
	 * @param as_idTurno Variable que contiene el id del turno.
	 * @param as_userId Variable que contiene el id del usuario.
	 * @param as_remoteIp Variable que contiene la ip del usuario.
	 * @param as_idCirculo Variable que contiene el id del circulo.
	 * @param al_idMatricula Variable que contiene el id de la matrícula.
	 * @param ab_segregado Variable que valida si es segregación o matrículas con base en.
	 * @param ab_salvar Variable que valida si se debe guardar la información.
	 * @return el valor de collection
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	private synchronized Collection<PredioSegregado> guardarDatosSegregacion(
	    Collection<PredioSegregado> acps_prediosSegregados, String as_idTurnoHistoria, String as_idTurno,
	    String                      as_userId, String as_remoteIp, String as_idCirculo, Long al_idMatricula,
	    boolean                     ab_segregado, boolean ab_salvar
	)
	    throws B2BException
	{
		Collection<PredioSegregado> lcps_return;
		DAOManager                  ldm_manager;

		lcps_return     = null;
		ldm_manager     = DaoManagerFactory.getDAOManager();

		try
		{
			if(CollectionUtils.isValidCollection(acps_prediosSegregados))
			{
				long          ll_idTurnoHistoria;
				TurnoHistoria lth_turnoHistoria;

				ll_idTurnoHistoria     = NumericUtils.getLong(as_idTurnoHistoria);
				lth_turnoHistoria      = DaoCreator.getTurnoHistoriaDAO(ldm_manager)
						                               .findById(NumericUtils.getLongWrapper(as_idTurnoHistoria));

				if(lth_turnoHistoria != null)
				{
					AccPredioSegregadoDAO        laps_DAO;
					SolicitudCorreccion          lsc_correccion;
					SolicitudCamposCorreccionDAO lscc_DAO;
					Iterator<PredioSegregado>    lips_iterator;

					laps_DAO           = DaoCreator.getAccPredioSegregadoDAO(ldm_manager);
					lsc_correccion     = new SolicitudCorreccion();
					lscc_DAO           = DaoCreator.getSolicitudCamposCorreccionDAO(ldm_manager);
					lips_iterator      = acps_prediosSegregados.iterator();
					lcps_return        = new ArrayList<PredioSegregado>();

					lsc_correccion.setIdSolicitud(lth_turnoHistoria.getIdSolicitud());
					lsc_correccion.setIdCirculo(as_idCirculo);
					lsc_correccion.setIdMatricula(NumericUtils.getBigInteger(al_idMatricula));
					lsc_correccion.setIdCausalCorreccion(
					    ab_segregado ? NumericUtils.getBigInteger(CausalCorreccionCommon.MATRICULAS_SEGREGADAS)
					                 : NumericUtils.getBigInteger(
					        CausalCorreccionCommon.MATRICULAS_ABIERTAS_CON_BASE_EN_LAS_SIGUIENTES_MATRICULAS
					    )
					);

					lsc_correccion = DaoCreator.getSolicitudCorreccionDAO(ldm_manager)
							                       .findBySolCirMatCausal(lsc_correccion);

					if(lsc_correccion != null)
					{
						while(lips_iterator.hasNext())
						{
							PredioSegregado lps_iterador;

							lps_iterador = lips_iterator.next();

							if(lps_iterador != null)
							{
								Long                                                  ll_idAnotacion;
								Long                                                  ll_idMatriculaIterador;
								String                                                ls_idCirculoIterador;
								com.bachue.snr.prosnr01.model.sdb.acc.PredioSegregado lps_predioSegregado;

								lps_predioSegregado        = new com.bachue.snr.prosnr01.model.sdb.acc.PredioSegregado(
									    lps_iterador
									);
								ll_idAnotacion             = lps_iterador.getIdAnotacion();
								ll_idMatriculaIterador     = ab_segregado ? lps_iterador.getIdMatricula1()
									                                      : lps_iterador.getIdMatricula();
								ls_idCirculoIterador       = ab_segregado ? lps_iterador.getIdCirculo1()
									                                      : lps_iterador.getIdCirculo();

								if(!NumericUtils.isValidLong(ll_idAnotacion))
								{
									Object[] loa_arg;

									loa_arg        = new String[2];
									loa_arg[0]     = ls_idCirculoIterador;
									loa_arg[1]     = StringUtils.getString(ll_idMatriculaIterador);

									throw new B2BException(ErrorKeys.ERROR_ANOTACION_SEGREGACION_INVALIDA, loa_arg);
								}

								if(ab_salvar)
								{
									lps_predioSegregado.setIdTurno(as_idTurno);

									if(lps_iterador.isEditar())
									{
										Object[] loa_arg;

										loa_arg        = new String[2];
										loa_arg[0]     = ls_idCirculoIterador;
										loa_arg[1]     = StringUtils.getString(ll_idMatriculaIterador);

										throw new B2BException(ErrorKeys.ERROR_EDITAR_INFORMACION_PREDIO, loa_arg);
									}
									else if(lps_iterador.isEliminar() && !lps_iterador.isAgregado())
										laps_DAO.deleteById(lps_predioSegregado);
									else if(lps_iterador.isEditado())
									{
										com.bachue.snr.prosnr01.model.sdb.acc.PredioSegregado lps_temp;

										lps_temp = laps_DAO.findByAllId(lps_predioSegregado);

										if(lps_temp == null)
										{
											SolicitudCamposCorreccion lscc_camposCorreccion;

											lscc_camposCorreccion = new SolicitudCamposCorreccion();

											lscc_camposCorreccion.setIdSolicitud(lth_turnoHistoria.getIdSolicitud());
											lscc_camposCorreccion.setIdCirculo(as_idCirculo);
											lscc_camposCorreccion.setIdMatricula(al_idMatricula);
											lscc_camposCorreccion.setIdCausalCorreccion(
											    ab_segregado
											    ? NumericUtils.getLongWrapper(
											        CausalCorreccionCommon.MATRICULAS_SEGREGADAS
											    )
											    : NumericUtils.getLongWrapper(
											        CausalCorreccionCommon.MATRICULAS_ABIERTAS_CON_BASE_EN_LAS_SIGUIENTES_MATRICULAS
											    )
											);
											lscc_camposCorreccion.setIdCamposCorreccion(
											    ab_segregado ? CamposCorreccionCommon.PMS_MATRICULAS_SEGREGADAS
											                 : CamposCorreccionCommon.PMAB_AGREGAR
											);
											lscc_camposCorreccion.setIdTurnoHistoria(
											    NumericUtils.getLongWrapper(as_idTurnoHistoria)
											);
											lscc_camposCorreccion.setIdCirculoRelacionado(ls_idCirculoIterador);
											lscc_camposCorreccion.setIdMatriculaRelacionado(ll_idMatriculaIterador);
											lscc_camposCorreccion.setIdSolicitudCorreccion(
											    StringUtils.getString(lsc_correccion.getIdSolicitudCorreccion())
											);
											lscc_camposCorreccion.setIdUsuarioCreacion(as_userId);
											lscc_camposCorreccion.setIpCreacion(as_remoteIp);

											lps_predioSegregado.setIdTurnoHistoria(ll_idTurnoHistoria);
											lps_predioSegregado.setIdUsuarioCreacion(as_userId);
											lps_predioSegregado.setIpCreacion(as_remoteIp);

											laps_DAO.insert(lps_predioSegregado);
											lscc_DAO.insertOrUpdate(lscc_camposCorreccion, true);

											lps_iterador.setAgregado(false);
										}
										else
										{
											lps_temp.setIdAnotacion(lps_predioSegregado.getIdAnotacion());
											lps_temp.setIdAnotacion1(lps_predioSegregado.getIdAnotacion1());
											lps_temp.setLote(lps_predioSegregado.getLote());
											lps_temp.setDescripcion(lps_predioSegregado.getDescripcion());
											lps_temp.setIdUsuarioModificacion(as_userId);
											lps_temp.setIpModificacion(as_remoteIp);

											laps_DAO.updateById(lps_temp);
										}

										lcps_return.add(lps_iterador);
									}
									else
										lcps_return.add(lps_iterador);
								}
							}
							else
								throw new B2BException(ErrorKeys.ERROR_INTERNO_SISTEMA);
						}
					}
				}
			}
			else
				throw new B2BException(ErrorKeys.ERROR_SIN_INFORMACION_GUARDAR_SEGREGACION);
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("guardarDatosSegregacion", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lcps_return;
	}

	/**
	 * Inicializa un objeto stringbuilder con cadenas diferentes dependiendo de la sección donde se invoque.
	 *
	 * @param asb_cadena Objeto a inicializar con una cadena predeterminada
	 * @param ab_datosBasicos true para indicar que la cadena es de datos basicos
	 * @param ab_descPredio true para indicar que la cadena es de descripción del predio
	 * @param ab_anotaciones true para indicar que la cadena es de anotaciones
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	private void inicializarCadena(
	    StringBuilder asb_cadena, boolean ab_datosBasicos, boolean ab_descPredio, boolean ab_anotaciones
	)
	    throws B2BException
	{
		if((asb_cadena != null) && !StringUtils.isValidString(asb_cadena.toString()))
		{
			asb_cadena.append(IdentificadoresCommon.PARRAFO_VACIO);
			asb_cadena.append(IdentificadoresCommon.SEPARADOR_CON_FORMATO);
			asb_cadena.append(
			    "\\pard \\ltrpar\\qc \\li0\\ri0\\sa160\\sl259\\slmult1\\widctlpar\\wrapdefault\\aspalpha\\aspnum\\faauto\\adjustright\\rin0\\lin0\\itap0\\pararsid10302282"
			);

			if(ab_datosBasicos)
				asb_cadena.append(
				    "{\\rtlch\\fcs1 \\af1\\afs20 \\ltrch\\fcs0 \\b\\f1\\fs28\\insrsid263433\\charrsid10302282 DATOS BASICOS \\par }"
				);
			else if(ab_descPredio)
				asb_cadena.append(
				    "{\\rtlch\\fcs1 \\ab\\af1\\afs20 \\ltrch\\fcs0 \\b\\f1\\fs28\\insrsid10302282\\charrsid10302282 DESCRIPCI\\'d3N DEL PREDIO \\par }"
				);
			else if(ab_anotaciones)
				asb_cadena.append(
				    "{\\rtlch\\fcs1 \\ab\\af1\\afs20 \\ltrch\\fcs0 \\b\\f1\\fs28\\insrsid10302282\\charrsid10302282 ANOTACIONES \\par }"
				);
			else
				throw new B2BException(ErrorKeys.ERROR_INTERNO_SISTEMA);
		}
	}

	/**
	 * Busca la medida y unidad de un detalle de area.
	 *
	 * @param adm_manager Conexión a la base de datos
	 * @param adap_detalle Objeto contenedor de la información del area
	 * @return Cadena de caracteres con la medida y unidad del area
	 * @throws B2BException Si ocurre un error en la base de datos
	 */
	private String obtenerAreaMedidaUnidad(DAOManager adm_manager, DetalleAreaPredio adap_detalle)
	    throws B2BException
	{
		String ls_return;

		ls_return = null;

		if(adap_detalle != null)
		{
			StringBuilder lsb_medida;
			MedidaArea    lma_medidaArea;

			lsb_medida = new StringBuilder();

			lsb_medida.append(adap_detalle.getArea());
			lsb_medida.append(IdentificadoresCommon.ESPACIO_VACIO);

			lma_medidaArea = DaoCreator.getMedidaAreaDAO(adm_manager).findById(adap_detalle.getIdUnidadMedida());

			if(lma_medidaArea != null)
				lsb_medida.append(lma_medidaArea.getDescripcion());

			ls_return = lsb_medida.toString();
		}

		return ls_return;
	}

	/**
	 * Consulta en base de datos la información de predio registro en acc o bng.
	 *
	 * @param adm_manager Conexión a la base de datos
	 * @param as_idCirculo id del círculo al cual pertenece la matrícula
	 * @param al_idMatricula id de la matrícula a utilizar como filtro en la consulta
	 * @param as_idTurno id del turno en el cual está siendo tratada la matrícula
	 * @param ab_bng true para indicar si se consulta en bng, false para acc
	 * @return Objeto resultante de la consulta
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	private PredioRegistro obtenerInfoPredio(
	    DAOManager adm_manager, String as_idCirculo, long al_idMatricula, String as_idTurno, boolean ab_bng
	)
	    throws B2BException
	{
		PredioRegistro lpr_predio;

		lpr_predio = null;

		if(ab_bng)
		{
			lpr_predio = new PredioRegistro();

			lpr_predio.setIdCirculo(as_idCirculo);
			lpr_predio.setIdMatricula(al_idMatricula);
			lpr_predio.setValidMatricula(true);

			lpr_predio = DaoCreator.getPredioRegistroDAO(adm_manager).findById(lpr_predio);
		}
		else
		{
			AccPredioRegistro lapr_predio;

			lapr_predio = DaoCreator.getAccPredioRegistroDAO(adm_manager)
					                    .findByCirculoMatriculaTurno(
					    as_idCirculo, NumericUtils.getLongWrapper(al_idMatricula), as_idTurno
					);

			if(lapr_predio != null)
				lpr_predio = new PredioRegistro(lapr_predio);
		}

		return lpr_predio;
	}

	/**
	 *  Método encargado de  realizar las validaciones para la pestaña Insertar Predios.
	 *
	 * @param acsm_csm Colección de tipo SolicitudMatricula que contiene los datos necesarios para la inserción o modificación de información en la pestaña Insertar Predios.
	 * @param acsma_sma Colección de tipo SolicitudMatriculaActo que contiene los datos necesarios para la inserción o modificación de información en la pestaña Insertar Predios.
	 * @param as_userId Variable de tipo String que contiene el usuario que realiza la acción.
	 * @param adm_manager Objeto de tipo DAOManager utilizado para crear la conexión hacia la base de datos
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	private void salvarPrediosConTramite(
	    Collection<TmpSolicitudMatricula> acsm_csm, Collection<TmpSolicitudMatriculaActo> acsma_sma, String as_userId,
	    DAOManager adm_manager
	)
	    throws B2BException
	{
		try
		{
			TmpSolicitudMatriculaDAO     lsm_DAO;
			TmpSolicitudMatriculaActoDAO lsma_DAO;

			lsm_DAO      = DaoCreator.getTmpSolicitudMatriculaDAO(adm_manager);
			lsma_DAO     = DaoCreator.getTmpSolicitudMatriculaActoDAO(adm_manager);

			if(CollectionUtils.isValidCollection(acsm_csm))
			{
				for(TmpSolicitudMatricula lsm_actual : acsm_csm)
				{
					if(lsm_actual != null)
					{
						long   ll_matricula;
						String ls_circuloRegistral;
						String ls_idSolicitud;

						ll_matricula            = lsm_actual.getIdMatricula();
						ls_circuloRegistral     = lsm_actual.getIdCirculo();
						ls_idSolicitud          = lsm_actual.getIdSolicitud();

						if(
						    StringUtils.isValidString(ls_circuloRegistral)
							    && NumericUtils.isValidLong(NumericUtils.getLongWrapper(ll_matricula), 1)
							    && NumericUtils.isValidLong(NumericUtils.getLongWrapper(ls_idSolicitud), 1)
						)
						{
							TmpSolicitudMatricula lsm_matriculaInsert;

							lsm_matriculaInsert = new TmpSolicitudMatricula();

							{
								lsm_matriculaInsert.setAccion(lsm_actual.getAccion());
								lsm_matriculaInsert.setIdSolicitud(lsm_actual.getIdSolicitud());
								lsm_matriculaInsert.setIdMatricula(ll_matricula);
								lsm_matriculaInsert.setIdCirculo(ls_circuloRegistral);
								lsm_matriculaInsert.setExpedirCertificado(lsm_actual.getExpedirCertificado());
								lsm_matriculaInsert.setCantidadCertificados(lsm_actual.getCantidadCertificados());
								lsm_matriculaInsert.setIdUsuario(lsm_actual.getIdUsuario());
								lsm_matriculaInsert.setIdUsuarioCreacion(lsm_actual.getIdUsuarioCreacion());
								lsm_matriculaInsert.setIdUsuarioModificacion(lsm_actual.getIdUsuarioModificacion());

								{
									TmpSolicitudMatricula lsm_matriculaExist;

									lsm_matriculaExist = lsm_DAO.findById(lsm_matriculaInsert);

									if(lsm_matriculaExist != null)
										lsm_DAO.insertOrUpdate(lsm_matriculaInsert, false);
									else
										lsm_DAO.insertOrUpdate(lsm_matriculaInsert, true);
								}
							}
						}
						else
						{
							Object[] loa_messageArgs = new String[2];
							loa_messageArgs[0]     = ls_circuloRegistral;
							loa_messageArgs[1]     = StringUtils.getString(ll_matricula);
							throw new B2BException(ErrorKeys.ERROR_MATRICULA_NO_VALIDA, loa_messageArgs);
						}
					}
				}

				{
					Collection<TmpSolicitudMatriculaActo> lcsma_matriculasActos;

					lcsma_matriculasActos = new ArrayList<TmpSolicitudMatriculaActo>(acsma_sma);

					if(CollectionUtils.isValidCollection(lcsma_matriculasActos))
					{
						for(TmpSolicitudMatriculaActo lsma_actual : lcsma_matriculasActos)
						{
							if(lsma_actual != null)
							{
								TmpSolicitudMatriculaActo lsma_matriculaActoExists;

								lsma_matriculaActoExists = lsma_DAO.findById(lsma_actual);

								if(lsma_matriculaActoExists != null)
									lsma_DAO.insertOrUpdate(lsma_actual, false);
								else
									lsma_DAO.insertOrUpdate(lsma_actual, true);
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
			clh_LOGGER.error("salvarPrediosConTramite", lb2be_e);

			throw lb2be_e;
		}
	}

	/**
	 * Método encargado de  realizar las validaciones para la pestaña Insertar Predios.
	 *
	 * @param acsm_csm Colección de tipo SolicitudMatricula que contiene los datos necesarios para la inserción o modificación de información en la pestaña Insertar Predios.
	 * @param acsma_sma Colección de tipo SolicitudMatriculaActo que contiene los datos necesarios para la inserción o modificación de información en la pestaña Insertar Predios.
	 * @param ath_th Objeto de tipo TurnoHistoria que contiene un id turno determinado.
	 * @param as_userId Variable de tipo String que contiene el usuario que realiza la acción.
	 * @param adm_manager Objeto de tipo DAOManager utilizado para crear la conexión hacia la base de datos
	 * @param as_remoteIp Variable de tipo String que contiene la dirección ip remota desde donde se realiza la acción.
	 * @param ab_esInsertarMatriculaCertificados de ab es insertar matricula certificados
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	private void salvarPrediosConfrontacionCorrectiva(
	    Collection<SolicitudMatricula> acsm_csm, Collection<SolicitudMatriculaActo> acsma_sma, TurnoHistoria ath_th,
	    String as_userId, DAOManager adm_manager, String as_remoteIp, boolean ab_esInsertarMatriculaCertificados
	)
	    throws B2BException
	{
		try
		{
			SolicitudMatriculaDAO        lsm_DAO;
			TmpSolicitudMatriculaDAO     ltsm_DAO;
			SolicitudMatriculaActoDAO    lsma_DAO;
			TmpSolicitudMatriculaActoDAO ltsma_DAO;

			lsm_DAO       = DaoCreator.getSolicitudMatriculaDAO(adm_manager);
			ltsm_DAO      = DaoCreator.getTmpSolicitudMatriculaDAO(adm_manager);
			lsma_DAO      = DaoCreator.getSolicitudMatriculaActoDAO(adm_manager);
			ltsma_DAO     = DaoCreator.getTmpSolicitudMatriculaActoDAO(adm_manager);

			if(CollectionUtils.isValidCollection(acsm_csm))
			{
				for(SolicitudMatricula lsm_actual : acsm_csm)
				{
					if(lsm_actual != null)
					{
						long   ll_matricula;
						String ls_circuloRegistral;
						String ls_estado;
						String ls_idSolicitud;

						ll_matricula            = lsm_actual.getIdMatricula();
						ls_circuloRegistral     = lsm_actual.getIdCirculo();
						ls_estado               = lsm_actual.getEstado();
						ls_idSolicitud          = lsm_actual.getIdSolicitud();

						if(
						    StringUtils.isValidString(ls_circuloRegistral)
							    && NumericUtils.isValidLong(NumericUtils.getLongWrapper(ll_matricula), 1)
							    && NumericUtils.isValidLong(NumericUtils.getLongWrapper(ls_idSolicitud), 1)
						)
						{
							SolicitudMatricula lsm_matriculaInsert;

							lsm_matriculaInsert = new SolicitudMatricula();

							{
								lsm_matriculaInsert.setEstado(ls_estado);
								lsm_matriculaInsert.setIdSolicitud(lsm_actual.getIdSolicitud());
								lsm_matriculaInsert.setIdMatricula(ll_matricula);
								lsm_matriculaInsert.setIdCirculo(ls_circuloRegistral);
								lsm_matriculaInsert.setExpedirCertificado(lsm_actual.getExpedirCertificado());
								lsm_matriculaInsert.setCantidadCertificados(lsm_actual.getCantidadCertificados());
								lsm_matriculaInsert.setIdUsuarioCreacion(lsm_actual.getIdUsuarioCreacion());
								lsm_matriculaInsert.setIdUsuarioModificacion(lsm_actual.getIdUsuarioModificacion());

								if(
								    (NumericUtils.getLong(ath_th.getIdEtapa()) == EtapaCommon.ID_ETAPA_ANALISTA_CERTIFICADOS_ESPECIALES)
									    && ab_esInsertarMatriculaCertificados
								)
								{
									lsm_matriculaInsert.setIdTurnoCertificado(ath_th.getIdTurno());
									lsm_matriculaInsert.setExpedirCertificado(EstadoCommon.S);
								}

								{
									SolicitudMatricula lsm_matriculaExist;

									lsm_matriculaExist = lsm_DAO.findById(lsm_matriculaInsert);

									if(lsm_matriculaExist != null)
										lsm_DAO.insertOrUpdate(lsm_matriculaInsert, false);
									else
										lsm_DAO.insertOrUpdate(lsm_matriculaInsert, true);
								}

								if(
								    (NumericUtils.getLong(ath_th.getIdEtapa()) == EtapaCommon.ID_ETAPA_ANALISTA_CERTIFICADOS_ESPECIALES)
									    && ab_esInsertarMatriculaCertificados
								)
								{
									Turno lt_turno;
									lt_turno = DaoCreator.getTurnoDAO(adm_manager).findById(ath_th.getIdTurno());

									if(lt_turno != null)
									{
										lt_turno.setIdMatriculaCertificado(StringUtils.getString(ll_matricula));
										lt_turno.setIdUsuarioCreacion(lsm_actual.getIdUsuarioCreacion());
										lt_turno.setIdUsuarioModificacion(lsm_actual.getIdUsuarioModificacion());

										DaoCreator.getTurnoDAO(adm_manager).actualizarIdMatriculaCertificado(lt_turno);
									}
								}
							}

							if(
							    !(NumericUtils.getLong(ath_th.getIdEtapa()) == EtapaCommon.ID_ETAPA_CORRECCIONES)
								    && !(NumericUtils.getLong(ath_th.getIdEtapa()) == EtapaCommon.ID_ETAPA_ANALISTA_CERTIFICADOS_ESPECIALES)
							)
							{
								TmpSolicitudMatricula ltsm_update;

								ltsm_update = new TmpSolicitudMatricula(lsm_actual, EstadoCommon.D, as_userId);

								{
									TmpSolicitudMatricula lsm_tmpMatriculaExist;

									lsm_tmpMatriculaExist = ltsm_DAO.findById(ltsm_update);

									if(lsm_tmpMatriculaExist != null)
										ltsm_DAO.insertOrUpdate(ltsm_update, false);
									else
										ltsm_DAO.insertOrUpdate(ltsm_update, true);
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
									if(ls_estado.equalsIgnoreCase(EstadoCommon.A))
										lpr_pr.setTurnoBloqueo(ath_th.getIdTurno());
									else
										lpr_pr.setTurnoBloqueo(null);

									lpr_pr.setIdUsuarioModificacion(as_userId);
									lpr_pr.setIpModificacion(as_remoteIp);

									{
										ZonaRegistral lzr_zonaRegistral;

										lzr_zonaRegistral = DaoCreator.getZonaRegistralDAO(adm_manager)
												                          .findById(lpr_pr.getIdZonaRegistral());

										if(lzr_zonaRegistral == null)
										{
											Object[] loa_arg;

											loa_arg        = new String[2];
											loa_arg[0]     = ls_circuloRegistral;
											loa_arg[1]     = StringUtils.getString(ll_matricula);

											throw new B2BException(ErrorKeys.ERROR_ZONA_REGISTRAL_PREDIO, loa_arg);
										}
									}

									lpr_DAO.insertOrUpdate(lpr_pr, false);
								}
							}
						}
						else
						{
							Object[] loa_messageArgs = new String[2];
							loa_messageArgs[0]     = ls_circuloRegistral;
							loa_messageArgs[1]     = StringUtils.getString(ll_matricula);
							throw new B2BException(ErrorKeys.ERROR_MATRICULA_NO_VALIDA, loa_messageArgs);
						}
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
								{
									SolicitudMatriculaActo lsma_matriculaActoExists;

									lsma_matriculaActoExists = lsma_DAO.findById(lsma_actual);

									if(lsma_matriculaActoExists != null)
										lsma_DAO.insertOrUpdate(lsma_actual, false);
									else
										lsma_DAO.insertOrUpdate(lsma_actual, true);
								}

								{
									TmpSolicitudMatriculaActo ltsma_update;
									TmpSolicitudMatriculaActo ltsma_matriculaActoExists;

									ltsma_update     = new TmpSolicitudMatriculaActo(
										    lsma_actual, EstadoCommon.D, as_userId
										);

									ltsma_matriculaActoExists = ltsma_DAO.findById(ltsma_update);

									if(ltsma_matriculaActoExists != null)
										ltsma_DAO.insertOrUpdate(ltsma_update, false);
									else
										ltsma_DAO.insertOrUpdate(ltsma_update, true);
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
			clh_LOGGER.error("salvarPrediosConTramite", lb2be_e);

			throw lb2be_e;
		}
	}

	/**
	 * Método encargado de validar si la anotación contiene un acto de englobes.
	 *
	 * @param aap_anotacionPredio Objeto que contiene la información de la anotación a validar.
	 * @param adm_manager Conexión a la base de datos
	 * @return Variable de tipo boolean que indica si la anotación contiene un acto de englobes.
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	private synchronized boolean validarAnotacionEnglobe(
	    com.bachue.snr.prosnr01.model.sdb.acc.AnotacionPredio aap_anotacionPredio, DAOManager adm_manager
	)
	    throws B2BException
	{
		boolean lb_return;

		lb_return = false;

		try
		{
			if(aap_anotacionPredio != null)
			{
				String ls_idTipoActo;

				ls_idTipoActo = aap_anotacionPredio.getIdNaturalezaJuridica();

				if(StringUtils.isValidString(ls_idTipoActo))
				{
					Map<String, String> lmss_englobes;
					String[]            lsa_data;

					lmss_englobes     = generarCodigos(
						    ConstanteCommon.CODIGOS_NATURALEZA_JURIDICA_ENGLOBES, adm_manager
						);
					lsa_data          = ls_idTipoActo.split(IdentificadoresCommon.SIMBOLO_GUION);
					ls_idTipoActo     = lsa_data[0];
					lb_return         = (lmss_englobes != null) && lmss_englobes.containsValue(ls_idTipoActo);
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("validarAnotacionEnglobe", lb2be_e);

			throw lb2be_e;
		}

		return lb_return;
	}

	/**
	 * Valida en base de datos un turno en proceso 37 si registros en la tabla SBD_ACC_PREDIO_REGISTRO.
	 *
	 * @param adm_manager Conexion a la base de datos
	 * @param as_idTurnoHistoria Objeto contenedor del id a utilizar como filtro en la validacion
	 * @return Turno resultante de la consulta
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	private Turno validarEtapaGrabacion(DAOManager adm_manager, String as_idTurnoHistoria)
	    throws B2BException
	{
		Turno         lt_turno;
		TurnoHistoria lth_turnoHistoria;

		lt_turno     = null;

		lth_turnoHistoria = DaoCreator.getTurnoHistoriaDAO(adm_manager)
				                          .findById(NumericUtils.getLongWrapper(as_idTurnoHistoria));

		if(lth_turnoHistoria != null)
		{
			lt_turno = DaoCreator.getTurnoDAO(adm_manager).findById(lth_turnoHistoria.getIdTurno());

			if(lt_turno != null)
			{
				String ls_proceso;

				ls_proceso = lt_turno.getIdProceso();

				if(StringUtils.isValidString(ls_proceso))
				{
					if(ls_proceso.equals(ProcesoCommon.ID_PROCESO_37))
					{
						Collection<AccPredioRegistro> lcpr_predioRegistro;
						lcpr_predioRegistro = DaoCreator.getAccPredioRegistroDAO(adm_manager)
								                            .findByTurno(lt_turno.getIdTurno(), false);

						if(CollectionUtils.isValidCollection(lcpr_predioRegistro))
							lt_turno = null;
					}
					else
						lt_turno = null;
				}
				else
					lt_turno = null;
			}
		}

		return lt_turno;
	}

	/**
	 * Método de validacin para las salvedades y anotaciones del panel.
	 *
	 * @param adm_manager de la consulta
	 * @param asp_salvedad objeto de la salvedad del predio
	 * @param lsc_param objeto de la solicitud
	 * @return String con la información del error, o de la validación
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	private String validarSalvedad(
	    DAOManager adm_manager, AccSalvedadPredio asp_salvedad, SolicitudCorreccion lsc_param
	)
	    throws B2BException
	{
		boolean lb_validacion;

		lb_validacion = true;

		String ls_return;
		ls_return = null;

		try
		{
			if((asp_salvedad != null) && (lsc_param != null))
			{
				boolean                         lb_validarAnotaciones;
				Collection<SolicitudCorreccion> lcsc_causales;
				SolicitudCorreccionDAO          lsc_DAO;

				lsc_DAO                   = DaoCreator.getSolicitudCorreccionDAO(adm_manager);
				lb_validarAnotaciones     = false;
				lcsc_causales             = lsc_DAO.findBySolicitudCirculoMatricula(lsc_param, true, false);

				if(CollectionUtils.isValidCollection(lcsc_causales))
				{
					boolean                       lb_causalesAnotacion;
					boolean                       lb_salvedadesPredio;
					Iterator<SolicitudCorreccion> lisc_iterator;
					Map<Long, AccSalvedadPredio>  lmlsp_salvedades;
					Map<String, String>           lmss_anotaciones;

					lmss_anotaciones         = generarCodigos(ConstanteCommon.CODIGOS_CAUSALES_ANOTACION, adm_manager);
					lb_causalesAnotacion     = CollectionUtils.isValidCollection(lmss_anotaciones);
					lmlsp_salvedades         = DaoCreator.getAccSalvedadPredioDAO(adm_manager)
							                                 .findCausalById(asp_salvedad);
					lb_salvedadesPredio      = CollectionUtils.isValidCollection(lmlsp_salvedades);
					lisc_iterator            = lcsc_causales.iterator();

					while(lisc_iterator.hasNext() && lb_validacion)
					{
						SolicitudCorreccion lsc_iterador;

						lsc_iterador = lisc_iterator.next();

						if(lsc_iterador != null)
						{
							Long ll_idCausal;

							ll_idCausal = NumericUtils.getLongWrapper(lsc_iterador.getIdCausalCorreccion());

							if(NumericUtils.isValidLong(ll_idCausal))
							{
								if(lb_causalesAnotacion)
								{
									if(!lmss_anotaciones.containsKey(StringUtils.getString(ll_idCausal)))
									{
										if(lb_salvedadesPredio)
											lb_validacion = lmlsp_salvedades.containsKey(ll_idCausal);
										else
											lb_validacion = false;
									}
									else
										lb_validarAnotaciones = true;
								}
								else if(lb_salvedadesPredio)
									lb_validacion = lmlsp_salvedades.containsKey(ll_idCausal);
								else
									lb_validacion = false;
							}

							if(!lb_validacion)
							{
								switch(StringUtils.getString(ll_idCausal))
								{
									case CausalCorreccionCommon.DATOS_BASICOS:
										ls_return = CausalCorreccionCommon.SALVEDAD
											+ CausalCorreccionCommon.DATOS_BASICOS_NOMBRE;

										break;

									case CausalCorreccionCommon.INFORMACION_DE_APERTURA:
										ls_return = CausalCorreccionCommon.SALVEDAD
											+ CausalCorreccionCommon.INFORMACION_DE_APERTURA_NOMBRE;

										break;

									case CausalCorreccionCommon.MATRICULAS_ABIERTAS_CON_BASE_EN_LAS_SIGUIENTES_MATRICULAS:
										ls_return = CausalCorreccionCommon.SALVEDAD
											+ CausalCorreccionCommon.MATRICULAS_ABIERTAS_CON_BASE_EN_LAS_SIGUIENTES_MATRICULAS_NOMBRE;

										break;

									case CausalCorreccionCommon.INFORMACION_CATASTRAL:
										ls_return = CausalCorreccionCommon.SALVEDAD
											+ CausalCorreccionCommon.INFORMACION_CATASTRAL_NOMBRE;

										break;

									case CausalCorreccionCommon.MATRICULAS_SEGREGADAS:
										ls_return = CausalCorreccionCommon.SALVEDAD
											+ CausalCorreccionCommon.MATRICULAS_SEGREGADAS_NOMBRE;

										break;

									case CausalCorreccionCommon.DATOS_ANT_SISTEMA:
										ls_return = CausalCorreccionCommon.SALVEDAD
											+ CausalCorreccionCommon.DATOS_ANT_SISTEMA_NOMBRE;

										break;

									case CausalCorreccionCommon.DETALLE_ANT_SISTEMA:
										ls_return = CausalCorreccionCommon.SALVEDAD
											+ CausalCorreccionCommon.DETALLE_ANT_SISTEMA_NOMBRE;

										break;

									case CausalCorreccionCommon.LINDEROS:
										ls_return = CausalCorreccionCommon.SALVEDAD
											+ CausalCorreccionCommon.LINDEROS_NOMBRE;

										break;

									case CausalCorreccionCommon.COMPLEMENTACION:
										ls_return = CausalCorreccionCommon.SALVEDAD
											+ CausalCorreccionCommon.COMPLEMENTACION_NOMBRE;

										break;

									case CausalCorreccionCommon.AREA_DEL_PREDIO:
										ls_return = CausalCorreccionCommon.SALVEDAD
											+ CausalCorreccionCommon.AREA_DEL_PREDIO_NOMBRE;

										break;

									case CausalCorreccionCommon.DIRECCION_DEL_PREDIO:
										ls_return = CausalCorreccionCommon.SALVEDAD
											+ CausalCorreccionCommon.DIRECCION_DEL_PREDIO_NOMBRE;

										break;

									case CausalCorreccionCommon.DATOS_ANOTACION:
										ls_return = CausalCorreccionCommon.SALVEDAD
											+ CausalCorreccionCommon.DATOS_ANOTACION_NOMBRE;

										break;

									case CausalCorreccionCommon.DATOS_DEL_DOCUMENTO:
										ls_return = CausalCorreccionCommon.SALVEDAD
											+ CausalCorreccionCommon.DATOS_DEL_DOCUMENTO_NOMBRE;

										break;

									case CausalCorreccionCommon.ESPECIFICACION:
										ls_return = CausalCorreccionCommon.SALVEDAD
											+ CausalCorreccionCommon.ESPECIFICACION_NOMBRE;

										break;

									case CausalCorreccionCommon.DETALLE_ANT_SISTEMA_ANOTACION:
										ls_return = CausalCorreccionCommon.SALVEDAD
											+ CausalCorreccionCommon.DETALLE_ANT_SISTEMA_ANOTACION_NOMBRE;

										break;

									case CausalCorreccionCommon.DATOS_BASICOS_DEL_INTEVINIENTES:
										ls_return = CausalCorreccionCommon.SALVEDAD
											+ CausalCorreccionCommon.DATOS_BASICOS_DEL_INTEVINIENTES_NOMBRE;

										break;

									default:
										break;
								}
							}
						}

						else
							throw new B2BException(ErrorKeys.ERROR_INTERNO_SISTEMA);
					}
				}
				else
					throw new B2BException(ErrorKeys.ERROR_INTERNO_SISTEMA);

				if(lb_validarAnotaciones && !StringUtils.isValidString(ls_return))
				{
					Long   ll_idMatricula;
					String ls_idCirculo;
					String ls_idTurno;

					ll_idMatricula     = asp_salvedad.getIdMatricula();
					ls_idCirculo       = asp_salvedad.getIdCirculo();
					ls_idTurno         = asp_salvedad.getIdTurno();

					Collection<com.bachue.snr.prosnr01.model.sdb.acc.AnotacionPredio> lcap_anotaciones;

					lcap_anotaciones   = DaoCreator.getAccAnotacionPredioDAO(adm_manager)
							                           .findByCirculoMatriculaTurno(
							    ls_idCirculo, ll_idMatricula, ls_idTurno
							);

					if(CollectionUtils.isValidCollection(lcap_anotaciones))
					{
						Map<Long, AccSalvedadAnotacion> lmlsp_salvedades;

						lmlsp_salvedades = DaoCreator.getAccSalvedadAnotacionDAO(adm_manager)
								                         .findAnotacionlById(ls_idTurno, ls_idCirculo, ll_idMatricula);

						if(CollectionUtils.isValidCollection(lmlsp_salvedades))
						{
							Iterator<com.bachue.snr.prosnr01.model.sdb.acc.AnotacionPredio> liap_iterator;

							liap_iterator = lcap_anotaciones.iterator();

							while(liap_iterator.hasNext() && lb_validacion)
							{
								com.bachue.snr.prosnr01.model.sdb.acc.AnotacionPredio lap_iterador;

								lap_iterador = liap_iterator.next();

								if((lap_iterador != null) && lap_iterador.isCorreccion())
								{
									Long ll_idAnotacion;

									ll_idAnotacion = lap_iterador.getIdAnotacion();

									if(NumericUtils.isValidLong(ll_idAnotacion))
										lb_validacion = lmlsp_salvedades.containsKey(ll_idAnotacion);
									else
										throw new B2BException(ErrorKeys.ERROR_INTERNO_SISTEMA);

									if(!lb_validacion)
										ls_return = CausalCorreccionCommon.ANOTACION
											+ StringUtils.getString(ll_idAnotacion);
								}
							}
						}
						else
							ls_return = CausalCorreccionCommon.SIN_ANOTACIONES;
					}
				}
			}
			else
				throw new B2BException(ErrorKeys.ERROR_INTERNO_SISTEMA);
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("validarSalvedades", lb2be_e);
			throw lb2be_e;
		}

		return ls_return;
	}

	/**
	 * Método encargado de validar la información de salvedades.
	 *
	 * @param al_idTurnoHistoria Variable de tipo Long que contiene el id del turno historia.
	 * @param adm_manager DAOManager que controla las transacciones.
	 * @param asp_salvedad Objeto que contiene la información de la salvedad a guardar.
	 * @param ab_mensaje de ab mensaje
	 * @return retorna <code>true</code> si se cumple la condicion, de lo contario retorna <code>false</code>
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	private synchronized boolean validarSalvedades(
	    Long al_idTurnoHistoria, DAOManager adm_manager, AccSalvedadPredio asp_salvedad, boolean ab_mensaje
	)
	    throws B2BException
	{
		boolean lb_return;

		lb_return = true;

		String ls_validador;

		ls_validador = null;

		try
		{
			if(NumericUtils.isValidLong(al_idTurnoHistoria))
			{
				TurnoHistoria lth_turnoHistoria;

				lth_turnoHistoria = DaoCreator.getTurnoHistoriaDAO(adm_manager).findById(al_idTurnoHistoria);

				if(lth_turnoHistoria != null)
				{
					String ls_idSolicitud;
					String ls_idTurno;

					ls_idSolicitud     = lth_turnoHistoria.getIdSolicitud();
					ls_idTurno         = lth_turnoHistoria.getIdTurno();

					if(StringUtils.isValidString(ls_idSolicitud) && StringUtils.isValidString(ls_idTurno))
					{
						Turno lt_turno;

						lt_turno = DaoCreator.getTurnoDAO(adm_manager).findById(ls_idTurno);

						if(lt_turno != null)
						{
							Long   ll_idMatricula;
							String ls_idCirculo;

							ll_idMatricula     = null;
							ls_idCirculo       = null;

							if(asp_salvedad == null)
							{
								Collection<SolicitudMatricula> lcsm_matriculas;

								lcsm_matriculas = DaoCreator.getSolicitudCorreccionDAO(adm_manager)
										                        .findCirculoMatriculaBySolicitud(
										    ls_idSolicitud, lt_turno.getIdCirculo()
										);

								if(CollectionUtils.isValidCollection(lcsm_matriculas))
								{
									Iterator<SolicitudMatricula> lism_iterator;

									lism_iterator = lcsm_matriculas.iterator();

									while(lism_iterator.hasNext() && lb_return)
									{
										SolicitudMatricula lsm_iterador;

										lsm_iterador = lism_iterator.next();

										if(lsm_iterador != null)
										{
											SolicitudCorreccion lsc_param;

											asp_salvedad       = new AccSalvedadPredio();
											ll_idMatricula     = NumericUtils.getLongWrapper(
												    lsm_iterador.getIdMatricula()
												);
											ls_idCirculo       = lsm_iterador.getIdCirculo();
											lsc_param          = new SolicitudCorreccion();

											asp_salvedad.setIdCirculo(ls_idCirculo);
											asp_salvedad.setIdMatricula(ll_idMatricula);
											asp_salvedad.setIdTurno(ls_idTurno);

											lsc_param.setIdCirculo(ls_idCirculo);
											lsc_param.setIdMatricula(NumericUtils.getBigInteger(ll_idMatricula));
											lsc_param.setIdSolicitud(ls_idSolicitud);

											ls_validador = validarSalvedad(adm_manager, asp_salvedad, lsc_param);

											if(StringUtils.isValidString(ls_validador))
												lb_return = false;
										}
										else
											throw new B2BException(ErrorKeys.ERROR_MATRICULA_INVALIDA);
									}
								}
								else
									throw new B2BException(ErrorKeys.ERROR_TURNO_SIN_MATRICULAS);
							}
							else
							{
								SolicitudCorreccion lsc_param;

								lsc_param          = new SolicitudCorreccion();
								ll_idMatricula     = asp_salvedad.getIdMatricula();
								ls_idCirculo       = asp_salvedad.getIdCirculo();

								asp_salvedad.setIdCirculo(ls_idCirculo);
								asp_salvedad.setIdMatricula(ll_idMatricula);
								asp_salvedad.setIdTurnoHistoria(al_idTurnoHistoria);
								asp_salvedad.setIdTurno(ls_idTurno);

								lsc_param.setIdCirculo(ls_idCirculo);
								lsc_param.setIdMatricula(NumericUtils.getBigInteger(ll_idMatricula));
								lsc_param.setIdSolicitud(ls_idSolicitud);

								ls_validador = validarSalvedad(adm_manager, asp_salvedad, lsc_param);

								if(StringUtils.isValidString(ls_validador))
									lb_return = false;
							}

							if(!lb_return && ab_mensaje)
							{
								Object[] loa_arg;

								loa_arg        = new String[3];
								loa_arg[0]     = ls_validador;
								loa_arg[1]     = ls_idCirculo;
								loa_arg[2]     = StringUtils.getString(ll_idMatricula);

								throw new B2BException(ErrorKeys.ERROR_SALVEDAD_PREDIO, loa_arg);
							}
						}
						else
							throw new B2BException(ErrorKeys.ERROR_INTERNO_SISTEMA);
					}
					else
						throw new B2BException(ErrorKeys.ERROR_INTERNO_SISTEMA);
				}
				else
					throw new B2BException(ErrorKeys.TURNO_HISTORIA_INVALIDO);
			}
			else
				throw new B2BException(ErrorKeys.TURNO_HISTORIA_INVALIDO);
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("validarSalvedades", lb2be_e);
			throw lb2be_e;
		}

		return lb_return;
	}
}
