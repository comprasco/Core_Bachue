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
import com.bachue.snr.prosnr01.business.liquidacion.LiquidacionBusiness;
import com.bachue.snr.prosnr01.business.utilidades.PDFGenerator;

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
import com.bachue.snr.prosnr01.common.constants.ProcesoCommon;
import com.bachue.snr.prosnr01.common.constants.RepositorioCommon;
import com.bachue.snr.prosnr01.common.constants.RolCommon;
import com.bachue.snr.prosnr01.common.constants.TipoActoCommon;
import com.bachue.snr.prosnr01.common.constants.TipoArchivoCommon;
import com.bachue.snr.prosnr01.common.constants.TipoAreaCommon;
import com.bachue.snr.prosnr01.common.constants.TipoComplementacionCommon;
import com.bachue.snr.prosnr01.common.constants.TipoDocumentalCommon;
import com.bachue.snr.prosnr01.common.constants.UnidadMedidaAreaCommon;
import com.bachue.snr.prosnr01.common.constants.ViewsCommon;
import com.bachue.snr.prosnr01.common.utils.ExpresionRegular;
import com.bachue.snr.prosnr01.common.utils.ListadoCodigosConstantes;

import com.bachue.snr.prosnr01.dao.DaoCreator;
import com.bachue.snr.prosnr01.dao.DaoManagerFactory;
import com.bachue.snr.prosnr01.dao.acc.AccAreaPredioDAO;
import com.bachue.snr.prosnr01.dao.acc.AccComplementacionPredioDAO;
import com.bachue.snr.prosnr01.dao.acc.AccDetalleAreaPredioDAO;
import com.bachue.snr.prosnr01.dao.acc.AccLinderoPredioDAO;
import com.bachue.snr.prosnr01.dao.acc.AccPredioRegistroDAO;
import com.bachue.snr.prosnr01.dao.acc.AccPredioSegregadoDAO;
import com.bachue.snr.prosnr01.dao.acc.ActoDAO;
import com.bachue.snr.prosnr01.dao.acc.AnotacionCancelacionDAO;
import com.bachue.snr.prosnr01.dao.acc.CambioEstadoPredioDAO;
import com.bachue.snr.prosnr01.dao.acc.DireccionPredioAccDAO;
import com.bachue.snr.prosnr01.dao.acc.DocumentosSalidaDAO;
import com.bachue.snr.prosnr01.dao.acc.MatriculaSegregacionDAO;
import com.bachue.snr.prosnr01.dao.acc.PersonaCorreoElectronicoDAO;
import com.bachue.snr.prosnr01.dao.acc.PersonaDAO;
import com.bachue.snr.prosnr01.dao.acc.PersonaDireccionDAO;
import com.bachue.snr.prosnr01.dao.acc.PersonaTelefonoDAO;
import com.bachue.snr.prosnr01.dao.acc.RegistroCalificacionDAO;
import com.bachue.snr.prosnr01.dao.acc.SolicitudDAO;
import com.bachue.snr.prosnr01.dao.acc.SolicitudIntervinienteDAO;
import com.bachue.snr.prosnr01.dao.acc.SolicitudMatriculaActoDAO;
import com.bachue.snr.prosnr01.dao.acc.TipoActoDAO;
import com.bachue.snr.prosnr01.dao.acc.TurnoDAO;
import com.bachue.snr.prosnr01.dao.acc.TurnoHistoriaDAO;
import com.bachue.snr.prosnr01.dao.bgn.DocumentoDAO;
import com.bachue.snr.prosnr01.dao.bgn.ImagenesDAO;
import com.bachue.snr.prosnr01.dao.bng.AnotacionPredioCiudadanoDAO;
import com.bachue.snr.prosnr01.dao.bng.AnotacionPredioDAO;
import com.bachue.snr.prosnr01.dao.bng.ComplementacionPredioDAO;
import com.bachue.snr.prosnr01.dao.bng.DireccionPredioDAO;
import com.bachue.snr.prosnr01.dao.bng.PredioRegistroDAO;
import com.bachue.snr.prosnr01.dao.col.InteresadoDocumentoTipoDAO;
import com.bachue.snr.prosnr01.dao.col.TipoUsoSueloDAO;
import com.bachue.snr.prosnr01.dao.pgn.CirculoRegistralDao;
import com.bachue.snr.prosnr01.dao.pgn.ConstantesDAO;
import com.bachue.snr.prosnr01.dao.pgn.DepartamentoDAO;
import com.bachue.snr.prosnr01.dao.pgn.LineaProduccionDAO;
import com.bachue.snr.prosnr01.dao.pgn.MotivoTramiteDAO;
import com.bachue.snr.prosnr01.dao.pgn.MunicipioDAO;
import com.bachue.snr.prosnr01.dao.pgn.OficinaOrigenDAO;
import com.bachue.snr.prosnr01.dao.pgn.TipoDocumentoPublicoDAO;
import com.bachue.snr.prosnr01.dao.pgn.TipoOficinaDAO;
import com.bachue.snr.prosnr01.dao.pgn.ZonaRegistralDAO;
import com.bachue.snr.prosnr01.dao.png.EstadoPredioDao;
import com.bachue.snr.prosnr01.dao.png.NaturalezaJuridicaDAO;
import com.bachue.snr.prosnr01.dao.proc.ProcedimientosDAO;
import com.bachue.snr.prosnr01.dao.util.ConsultasDAO;
import com.bachue.snr.prosnr01.dao.util.ConsultasUtilidades;
import com.bachue.snr.prosnr01.dao.view.ViewDataReportDAO;

import com.bachue.snr.prosnr01.model.Matricula;
import com.bachue.snr.prosnr01.model.antiguoSistema.Anotacion;
import com.bachue.snr.prosnr01.model.antiguoSistema.Apertura;
import com.bachue.snr.prosnr01.model.antiguoSistema.Complementacion;
import com.bachue.snr.prosnr01.model.antiguoSistema.DatosBasicos;
import com.bachue.snr.prosnr01.model.antiguoSistema.DireccionDelPredio;
import com.bachue.snr.prosnr01.model.antiguoSistema.MatriculaBase;
import com.bachue.snr.prosnr01.model.antiguoSistema.UbicacionZonaRegistral;
import com.bachue.snr.prosnr01.model.calificacion.ComplementacionAnotacion;
import com.bachue.snr.prosnr01.model.calificacion.ComplementacionCalificacion;
import com.bachue.snr.prosnr01.model.calificacion.EliminarAnotacion;
import com.bachue.snr.prosnr01.model.calificacion.LinderoRegistroCalificacion;
import com.bachue.snr.prosnr01.model.calificacion.RegistroCalificacion;
import com.bachue.snr.prosnr01.model.calificacion.RegistroParcialCalificacion;
import com.bachue.snr.prosnr01.model.numeracion.NumeracionOficiosCirculo;
import com.bachue.snr.prosnr01.model.numeracion.NumeracionResolucionCirculo;
import com.bachue.snr.prosnr01.model.registro.GravamenPendiente;
import com.bachue.snr.prosnr01.model.registro.Registro;
import com.bachue.snr.prosnr01.model.sdb.acc.AccAreaPredio;
import com.bachue.snr.prosnr01.model.sdb.acc.AccComplementacionPredio;
import com.bachue.snr.prosnr01.model.sdb.acc.AccLinderoPredio;
import com.bachue.snr.prosnr01.model.sdb.acc.AccPredioRegistro;
import com.bachue.snr.prosnr01.model.sdb.acc.Acto;
import com.bachue.snr.prosnr01.model.sdb.acc.AnotacionCancelacion;
import com.bachue.snr.prosnr01.model.sdb.acc.CambioEstadoPredio;
import com.bachue.snr.prosnr01.model.sdb.acc.DatosAntSistema;
import com.bachue.snr.prosnr01.model.sdb.acc.DetalleAreaPredio;
import com.bachue.snr.prosnr01.model.sdb.acc.DireccionPredioAcc;
import com.bachue.snr.prosnr01.model.sdb.acc.DocumentosSalida;
import com.bachue.snr.prosnr01.model.sdb.acc.MatriculaSegregacion;
import com.bachue.snr.prosnr01.model.sdb.acc.Persona;
import com.bachue.snr.prosnr01.model.sdb.acc.PersonaCorreoElectronico;
import com.bachue.snr.prosnr01.model.sdb.acc.PersonaDireccion;
import com.bachue.snr.prosnr01.model.sdb.acc.PersonaTelefono;
import com.bachue.snr.prosnr01.model.sdb.acc.PredioSegregado;
import com.bachue.snr.prosnr01.model.sdb.acc.Solicitud;
import com.bachue.snr.prosnr01.model.sdb.acc.SolicitudApoyoNacional;
import com.bachue.snr.prosnr01.model.sdb.acc.SolicitudApoyoRegionalOrip;
import com.bachue.snr.prosnr01.model.sdb.acc.SolicitudAsociada;
import com.bachue.snr.prosnr01.model.sdb.acc.SolicitudInterviniente;
import com.bachue.snr.prosnr01.model.sdb.acc.SolicitudMatricula;
import com.bachue.snr.prosnr01.model.sdb.acc.SolicitudMatriculaActo;
import com.bachue.snr.prosnr01.model.sdb.acc.Turno;
import com.bachue.snr.prosnr01.model.sdb.acc.TurnoDerivado;
import com.bachue.snr.prosnr01.model.sdb.acc.TurnoHistoria;
import com.bachue.snr.prosnr01.model.sdb.aut.Usuario;
import com.bachue.snr.prosnr01.model.sdb.bng.AnotacionPredio;
import com.bachue.snr.prosnr01.model.sdb.bng.AnotacionPredioCiudadano;
import com.bachue.snr.prosnr01.model.sdb.bng.AreaPredio;
import com.bachue.snr.prosnr01.model.sdb.bng.ComplementacionPredio;
import com.bachue.snr.prosnr01.model.sdb.bng.DireccionPredio;
import com.bachue.snr.prosnr01.model.sdb.bng.Documento;
import com.bachue.snr.prosnr01.model.sdb.bng.Imagenes;
import com.bachue.snr.prosnr01.model.sdb.bng.LinderoPredio;
import com.bachue.snr.prosnr01.model.sdb.bng.PredioRegistro;
import com.bachue.snr.prosnr01.model.sdb.col.InteresadoDocumentoTipo;
import com.bachue.snr.prosnr01.model.sdb.col.TipoUsoSuelo;
import com.bachue.snr.prosnr01.model.sdb.pgn.CirculoRegistral;
import com.bachue.snr.prosnr01.model.sdb.pgn.Constantes;
import com.bachue.snr.prosnr01.model.sdb.pgn.Departamento;
import com.bachue.snr.prosnr01.model.sdb.pgn.Etapa;
import com.bachue.snr.prosnr01.model.sdb.pgn.Fases;
import com.bachue.snr.prosnr01.model.sdb.pgn.LineaProduccion;
import com.bachue.snr.prosnr01.model.sdb.pgn.MotivoTramite;
import com.bachue.snr.prosnr01.model.sdb.pgn.Municipio;
import com.bachue.snr.prosnr01.model.sdb.pgn.OficinaOrigen;
import com.bachue.snr.prosnr01.model.sdb.pgn.TipoActo;
import com.bachue.snr.prosnr01.model.sdb.pgn.TipoDocumentoPublico;
import com.bachue.snr.prosnr01.model.sdb.pgn.TipoOficina;
import com.bachue.snr.prosnr01.model.sdb.pgn.ZonaRegistral;
import com.bachue.snr.prosnr01.model.sdb.png.EstadoAnotacion;
import com.bachue.snr.prosnr01.model.sdb.png.EstadoPredio;
import com.bachue.snr.prosnr01.model.sdb.png.NaturalezaJuridica;
import com.bachue.snr.prosnr01.model.ui.AccAreaUI;
import com.bachue.snr.prosnr01.model.ui.SolicitudApoyoNacionalUI;

import java.io.ByteArrayInputStream;

import java.math.BigDecimal;
import java.math.BigInteger;

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
 * Clase para el manejo del negocio de registro calificacion para realizar
 * registro de todas las anotaciones
 *
 * @author Alejandro Santos
 *
 */
public class RegistroCalificacionBusiness extends BaseBusiness
{
	/** Constante clh_LOGGER. */
	private static final LoggerHandler clh_LOGGER = (LoggerHandler)com.bachue.snr.prosnr01.common.utils.LoggingHelper
			.getLogger(RegistroCalificacionBusiness.class);

	/** Propiedad idd devolucionDinero. */
	private LiquidacionBusiness alb_liquidacionBusiness;

	/**
	 * Calcula que porcentaje de matriculas se deben revisar dado el caso
	 *
	 * @param ai_numero
	 *            El numero con que se multiplicara la constante
	 * @param ai_revisados
	 *            La cantidad de matriculas revisadas
	 * @param adm_manager
	 *            Conexión con el dao manager
	 * @param ab_boolean
	 *            Booleana con la cuál hallará el valor de una constante u otra
	 * @throws B2BException
	 */
	public synchronized void CalcularRevision(
	    int ai_numero, int ai_revisados, DAOManager adm_manager, String as_constante
	)
	    throws B2BException
	{
		try
		{
			int    li_valueConstante;
			double ld_respuesta;

			li_valueConstante = 0;

			ConstantesDAO lcd_DAO;
			Constantes    lc_constantes;

			lc_constantes     = new Constantes();
			lcd_DAO           = DaoCreator.getConstantesDAO(adm_manager);

			lc_constantes.setIdConstante(as_constante);

			lc_constantes = lcd_DAO.findById(lc_constantes);

			if(lc_constantes != null)
			{
				BigInteger lbi_constanteMatricula;

				lbi_constanteMatricula = lc_constantes.getEntero();

				if(NumericUtils.isValidBigInteger(lbi_constanteMatricula))
				{
					li_valueConstante     = NumericUtils.getInt(lbi_constanteMatricula);

					ld_respuesta = (ai_numero * li_valueConstante) / 100.0;

					if(ai_revisados < ld_respuesta)
					{
						Object[] loa_messageArgs;
						loa_messageArgs     = new String[1];

						loa_messageArgs[0] = StringUtils.getString(li_valueConstante);

						throw new B2BException(ErrorKeys.ERROR_SIN_REVISION_MATRICULAS_PORCENTAJE, loa_messageArgs);
					}
				}
				else
				{
					Object[] loa_messageArgs = new String[1];
					loa_messageArgs[0] = StringUtils.getString(as_constante);

					throw new B2BException(ErrorKeys.ERROR_SIN_CONSTANTE, loa_messageArgs);
				}
			}
			else
			{
				Object[] loa_messageArgs = new String[1];
				loa_messageArgs[0] = StringUtils.getString(as_constante);

				throw new B2BException(ErrorKeys.ERROR_SIN_CONSTANTE, loa_messageArgs);
			}
		}
		catch(B2BException lb2be_e)
		{
			throw new B2BException(lb2be_e.getMessage());
		}
	}

	/**
	 * Método para poder guardar la información seleccionada en la pantalla y
	 * guardarla en la base de datos
	 *
	 * @param alp_linderoPredio
	 *            Objeto para poder obtener la información del lindero
	 * @param aacp_complementacionPredio
	 *            Objeto para poder obtener la información de complementacion predio
	 * @param as_tipoComplementacion
	 *            String para poder saber el tipo de complementación
	 * @param acp_complementacionPredio
	 *            Objeto para poder guardar la información obtenida
	 * @param aapr_pr
	 *            Objeto para poder obtener la información del predio
	 * @param aaui_areaPredio
	 *            Objeto para poder obtener el area del predio
	 * @param acddp_temp
	 *            Colección de Objetos para poder obtener la dirección del predio
	 * @param as_idTurnoHistoria
	 *            String del id turno historia
	 * @param as_userId
	 *            Usuario en sesión
	 * @param as_localIp
	 *            Ip del servidor de aplicaciones
	 * @param as_remoteIp
	 *            Ip del usuario en sesión
	 * @throws B2BException
	 */
	public synchronized int accionSalvarView(
	    AccLinderoPredio alp_linderoPredio, AccComplementacionPredio aacp_complementacionPredio,
	    String as_tipoComplementacion, ComplementacionPredio acp_complementacionPredio, AccPredioRegistro aapr_pr,
	    AccAreaUI aaui_areaPredio, Collection<DireccionDelPredio> acddp_temp, String as_idTurnoHistoria,
	    String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		DAOManager ldm_manager;
		int        li_secuenciaBng;

		ldm_manager         = DaoManagerFactory.getDAOManager();
		li_secuenciaBng     = 0;

		try
		{
			// Salvar linderos y complementacion
			{
				if(alp_linderoPredio != null)
				{
					String ls_lindero;
					ls_lindero = alp_linderoPredio.getLindero();

					if(!StringUtils.isValidString(ls_lindero))
						throw new B2BException(ErrorKeys.ERROR_LINDERO_INVALIDO);
					else if(ls_lindero.trim().length() < 100)
						throw new B2BException(ErrorKeys.ERROR_LINDERO_TAM);

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
							lalp_lindero.setIdUsuarioCreacion(alp_linderoPredio.getIdUsuarioCreacion());
							lalp_lindero.setIpCreacion(alp_linderoPredio.getIpCreacion());
							lalp_lindero.setIdTurno(alp_linderoPredio.getIdTurno());

							lalp_DAO.insert(lalp_lindero);
						}
						else
							throw new B2BException(ErrorKeys.LINDERO_PREDIO_INSERT);
					}
					else
					{
						lalp_lindero.setLindero(ls_lindero);
						lalp_lindero.setIdUsuarioModificacion(alp_linderoPredio.getIdUsuarioModificacion());
						lalp_lindero.setIpModificacion(alp_linderoPredio.getIpModificacion());

						lalp_DAO.updateById(lalp_lindero, true);
					}
				}

				if(aacp_complementacionPredio != null)
				{
					String ls_complementacion;

					ls_complementacion = aacp_complementacionPredio.getComplementacion();

					if(StringUtils.isValidString(ls_complementacion))
					{
						if(ls_complementacion.trim().length() < 100)
							throw new B2BException(ErrorKeys.ERROR_COMPLEMENTACION_TAM);
					}
					else
						throw new B2BException(ErrorKeys.ERROR_COMPLEMENTACION_INVALIDO);

					AccComplementacionPredioDAO lacp_DAO;
					ComplementacionPredioDAO    lcp_DAO;

					lacp_DAO     = DaoCreator.getAccComplementacionPredioDAO(ldm_manager);
					lcp_DAO      = DaoCreator.getComplementacionPredioDAO(ldm_manager);

					if(StringUtils.isValidString(as_tipoComplementacion))
					{
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
							aacp_complementacionPredio.setTipoComplementacion(
							    StringUtils.isValidString(as_tipoComplementacion)
							    ? (as_tipoComplementacion.equalsIgnoreCase(TipoComplementacionCommon.COPIAR)
							    ? TipoComplementacionCommon.C
							    : (as_tipoComplementacion.equalsIgnoreCase(TipoComplementacionCommon.CONSTRUIR)
							    ? TipoComplementacionCommon.A
							    : (as_tipoComplementacion.equalsIgnoreCase(TipoComplementacionCommon.NUEVA)
							    ? TipoComplementacionCommon.N : null))) : null
							);
							aacp_complementacionPredio.setIdComplementacion(
							    NumericUtils.getLongWrapper(li_secuenciaAcc)
							);
							aacp_complementacionPredio.setIdComplementacionOriginal(
							    NumericUtils.getLongWrapper(acp_complementacionPredio.getIdComplementacion())
							);

							aacp_complementacionPredio.setIdUsuarioCreacion(as_userId);
							aacp_complementacionPredio.setIpCreacion(as_remoteIp);

							lacp_DAO.insert(aacp_complementacionPredio);
						}
						else
							throw new B2BException(ErrorKeys.COMPLEMENTACION_PREDIO_INSERT);
					}
					else
						throw new B2BException(ErrorKeys.ERROR_COMPLEMENTACION_TIPO);

					if(aapr_pr != null)
					{
						AccPredioRegistro    lapr_predio;
						AccPredioRegistroDAO lapr_DAO;

						lapr_predio     = new AccPredioRegistro();
						lapr_DAO        = DaoCreator.getAccPredioRegistroDAO(ldm_manager);

						if(alp_linderoPredio == null)
						{
							alp_linderoPredio = new AccLinderoPredio();
							alp_linderoPredio.setIdCirculo(aapr_pr.getIdCirculo());
							alp_linderoPredio.setIdMatricula(aapr_pr.getIdMatricula());
							alp_linderoPredio.setIdUsuarioModificacion(
							    aacp_complementacionPredio.getIdUsuarioModificacion()
							);
							alp_linderoPredio.setIpModificacion(aacp_complementacionPredio.getIpModificacion());
						}

						lapr_predio.setIdCirculo(alp_linderoPredio.getIdCirculo());
						lapr_predio.setIdMatricula(alp_linderoPredio.getIdMatricula());

						lapr_predio = lapr_DAO.findByCirculoMatricula(lapr_predio);

						if(lapr_predio != null)
						{
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
			// salvar area del predio
			{
				if(aaui_areaPredio != null)
				{
					Collection<DetalleAreaPredio> lcadap_areasTerrenoData;

					lcadap_areasTerrenoData = aaui_areaPredio.getAreasTerreno();

					if(CollectionUtils.isValidCollection(lcadap_areasTerrenoData))
					{
						AccAreaPredio     laap_areaPredioData;
						DetalleAreaPredio ladap_areaConstruidaData;
						DetalleAreaPredio ladap_areaPrivadaData;

						laap_areaPredioData          = aaui_areaPredio.getAreaPredio();
						ladap_areaConstruidaData     = aaui_areaPredio.getDetalleAreaConstruida();
						ladap_areaPrivadaData        = aaui_areaPredio.getDetalleAreaPrivada();

						if(laap_areaPredioData != null)
						{
							String ls_tipoUso;

							ls_tipoUso = laap_areaPredioData.getTipoSuelo();

							if(!StringUtils.isValidString(ls_tipoUso))
								throw new B2BException(ErrorKeys.USO_DEL_PREDIO_INVALIDO_SELECCIONAR);
						}

						{
							String ls_idCirculo;
							Long   ll_idMatricula;

							ls_idCirculo       = aaui_areaPredio.getIdCirculo();
							ll_idMatricula     = aaui_areaPredio.getIdMatricula();

							if(StringUtils.isValidString(ls_idCirculo) && NumericUtils.isValidLong(ll_idMatricula))
							{
								AccAreaPredio           laap_areaPredio;
								AccAreaPredioDAO        laap_DAO;
								AccDetalleAreaPredioDAO ladap_DAO;

								laap_areaPredio     = new AccAreaPredio();
								laap_DAO            = DaoCreator.getAccAreaPredioDAO(ldm_manager);
								ladap_DAO           = DaoCreator.getAccDetalleAreaPredioDAO(ldm_manager);

								laap_areaPredio.setIdCirculo(ls_idCirculo);
								laap_areaPredio.setIdMatricula(ll_idMatricula);

								laap_areaPredio = laap_DAO.findByCirculoMatricula(laap_areaPredio);

								if(laap_areaPredio != null)
								{
									DetalleAreaPredio                  ladap_param;
									HashMap<String, DetalleAreaPredio> lhsadap_areas;
									String                             ls_idAreaPredio;

									ladap_param         = new DetalleAreaPredio();
									ls_idAreaPredio     = laap_areaPredio.getIdArea();

									ladap_param.setIdAreaPredio(ls_idAreaPredio);
									ladap_param.setIdCirculo(laap_areaPredio.getIdCirculo());
									ladap_param.setIdMatricula(laap_areaPredio.getIdMatricula());
									ladap_param.setIdTipoArea(TipoAreaCommon.TERRENO);

									lhsadap_areas = ladap_DAO.findAllByIdAreaPredioTipoDetalle(ladap_param);

									if(CollectionUtils.isValidCollection(lhsadap_areas))
									{
										HashMap<String, DetalleAreaPredio> lhsadap_areasInsert;
										Iterator<DetalleAreaPredio>        liadap_iterator;

										lhsadap_areasInsert     = new HashMap<String, DetalleAreaPredio>();
										liadap_iterator         = lcadap_areasTerrenoData.iterator();

										while(liadap_iterator.hasNext())
										{
											DetalleAreaPredio ladap_areaTerreno;

											ladap_areaTerreno = liadap_iterator.next();

											if(ladap_areaTerreno != null)
											{
												String ls_idDetalleAreaPredio;

												ls_idDetalleAreaPredio = ladap_areaTerreno.getIdDetalleAreaPredio();

												if(StringUtils.isValidString(ls_idDetalleAreaPredio))
												{
													if(lhsadap_areas.containsKey(ls_idDetalleAreaPredio))
													{
														DetalleAreaPredio ladap_db;

														ladap_db = lhsadap_areas.get(ls_idDetalleAreaPredio);

														if(ladap_db != null)
														{
															boolean lb_update;
															Double  ld_area;
															Double  ld_areaDB;
															String  ls_unidadMedida;
															String  ls_unidadMedidaDB;

															lb_update             = false;
															ld_area               = ladap_areaTerreno.getArea();
															ld_areaDB             = ladap_db.getArea();
															ls_unidadMedida       = ladap_areaTerreno.getIdUnidadMedida();
															ls_unidadMedidaDB     = ladap_db.getIdUnidadMedida();

															if(
															    StringUtils.isValidString(ls_unidadMedida)
																    && StringUtils.isValidString(ls_unidadMedidaDB)
															)
															{
																if(!ls_unidadMedida.equalsIgnoreCase(ls_unidadMedidaDB))
																	lb_update = true;
															}

															if(
															    NumericUtils.isValidDouble(ld_area)
																    && NumericUtils.isValidDouble(ld_areaDB)
															)
															{
																if(ld_area.compareTo(ld_areaDB) != 0)
																	lb_update = true;
															}

															if(lb_update)
															{
																ladap_db.setArea(ld_area);
																ladap_db.setIdUnidadMedida(ls_unidadMedida);
																ladap_db.setIdUsuarioModificacion(as_userId);
																ladap_db.setIpModificacion(as_remoteIp);

																ladap_DAO.insertOrUpdate(ladap_db, false);
															}
														}
													}
													else
													{
														ladap_areaTerreno.setIdAreaPredio(ls_idAreaPredio);
														ladap_areaTerreno.setIdCirculo(ls_idCirculo);
														ladap_areaTerreno.setIdMatricula(ll_idMatricula);
														ladap_areaTerreno.setIdUsuarioCreacion(as_userId);
														ladap_areaTerreno.setIpCreacion(as_remoteIp);

														ladap_DAO.insertOrUpdate(ladap_areaTerreno, true);
													}

													lhsadap_areasInsert.put(ls_idDetalleAreaPredio, ladap_areaTerreno);
												}
											}
										}

										for(Map.Entry<String, DetalleAreaPredio> lhm_iterador : lhsadap_areas.entrySet())
										{
											DetalleAreaPredio ladap_detalleAreaPredioMap;
											String            ls_idDetalleAreaMap;

											ladap_detalleAreaPredioMap     = lhm_iterador.getValue();
											ls_idDetalleAreaMap            = lhm_iterador.getKey();

											if(StringUtils.isValidString(ls_idDetalleAreaMap))
											{
												if(
												    !lhsadap_areasInsert.containsKey(ls_idDetalleAreaMap)
													    && (ladap_detalleAreaPredioMap != null)
												)
													ladap_DAO.delete(ladap_detalleAreaPredioMap);
											}
										}
									}
									else
									{
										String ls_idAccAreaPredio;

										ls_idAccAreaPredio = laap_areaPredio.getIdArea();

										if(StringUtils.isValidString(ls_idAreaPredio))
										{
											Iterator<DetalleAreaPredio> liadap_iterator;

											liadap_iterator = lcadap_areasTerrenoData.iterator();

											while(liadap_iterator.hasNext())
											{
												DetalleAreaPredio ladap_areaTerreno;

												ladap_areaTerreno = liadap_iterator.next();

												if(ladap_areaTerreno != null)
												{
													ladap_areaTerreno.setIdAreaPredio(ls_idAccAreaPredio);
													ladap_areaTerreno.setIdCirculo(ls_idCirculo);
													ladap_areaTerreno.setIdMatricula(ll_idMatricula);
													ladap_areaTerreno.setIdUsuarioCreacion(as_userId);
													ladap_areaTerreno.setIpCreacion(as_remoteIp);

													ladap_DAO.insertOrUpdate(ladap_areaTerreno, true);
												}
											}

											if(NumericUtils.isValidDouble(ladap_areaConstruidaData.getArea()))
											{
												ladap_areaConstruidaData.setIdAreaPredio(ls_idAccAreaPredio);
												ladap_areaConstruidaData.setIdCirculo(ls_idCirculo);
												ladap_areaConstruidaData.setIdMatricula(ll_idMatricula);
												ladap_areaConstruidaData.setIdUsuarioCreacion(as_userId);
												ladap_areaConstruidaData.setIpCreacion(as_remoteIp);

												ladap_DAO.insertOrUpdate(ladap_areaConstruidaData, true);
											}

											if(NumericUtils.isValidDouble(ladap_areaPrivadaData.getArea()))
											{
												ladap_areaPrivadaData.setIdAreaPredio(ls_idAccAreaPredio);
												ladap_areaPrivadaData.setIdCirculo(ls_idCirculo);
												ladap_areaPrivadaData.setIdMatricula(ll_idMatricula);
												ladap_areaPrivadaData.setIdUsuarioCreacion(as_userId);
												ladap_areaPrivadaData.setIpCreacion(as_remoteIp);

												ladap_DAO.insertOrUpdate(ladap_areaPrivadaData, true);
											}
										}
									}

									{
										DetalleAreaPredio ladap_areaConstruida;

										ladap_param.setIdTipoArea(TipoAreaCommon.CONSTRUIDA);

										ladap_areaConstruida = ladap_DAO.findByIdAreaPredioTipo(ladap_param);

										if(ladap_areaConstruida != null)
										{
											boolean lb_update;
											Double  ld_area;
											Double  ld_areaDB;
											String  ls_unidadMedida;
											String  ls_unidadMedidaDB;

											lb_update             = false;
											ld_area               = ladap_areaConstruidaData.getArea();
											ld_areaDB             = ladap_areaConstruida.getArea();
											ls_unidadMedida       = ladap_areaConstruidaData.getIdUnidadMedida();
											ls_unidadMedidaDB     = ladap_areaConstruida.getIdUnidadMedida();

											if(
											    StringUtils.isValidString(ls_unidadMedida)
												    && StringUtils.isValidString(ls_unidadMedidaDB)
											)
											{
												if(!ls_unidadMedida.equalsIgnoreCase(ls_unidadMedidaDB))
													lb_update = true;
											}

											if(
											    NumericUtils.isValidDouble(ld_area)
												    && NumericUtils.isValidDouble(ld_areaDB)
											)
											{
												if(ld_area.compareTo(ld_areaDB) != 0)
													lb_update = true;
											}

											if(lb_update)
											{
												ladap_areaConstruida.setArea(ld_area);
												ladap_areaConstruida.setIdUnidadMedida(ls_unidadMedida);
												ladap_areaConstruida.setIdUsuarioModificacion(as_userId);
												ladap_areaConstruida.setIpModificacion(as_remoteIp);

												ladap_DAO.insertOrUpdate(ladap_areaConstruida, false);
											}
										}
										else
										{
											if(
											    (ladap_areaConstruidaData != null)
												    && NumericUtils.isValidDouble(ladap_areaConstruidaData.getArea())
											)
											{
												ladap_areaConstruidaData.setIdAreaPredio(
												    StringUtils.getString(ls_idAreaPredio)
												);
												ladap_areaConstruidaData.setIdCirculo(ls_idCirculo);
												ladap_areaConstruidaData.setIdMatricula(ll_idMatricula);
												ladap_areaConstruidaData.setIdUsuarioCreacion(as_userId);
												ladap_areaConstruidaData.setIpCreacion(as_remoteIp);

												ladap_DAO.insertOrUpdate(ladap_areaConstruidaData, true);
											}
										}
									}

									{
										DetalleAreaPredio ladap_areaPrivada;

										ladap_param.setIdTipoArea(TipoAreaCommon.PRIVADA);

										ladap_areaPrivada = ladap_DAO.findByIdAreaPredioTipo(ladap_param);

										if(ladap_areaPrivada != null)
										{
											boolean lb_update;
											Double  ld_area;
											Double  ld_areaDB;
											String  ls_unidadMedida;
											String  ls_unidadMedidaDB;

											lb_update             = false;
											ld_area               = ladap_areaPrivadaData.getArea();
											ld_areaDB             = ladap_areaPrivada.getArea();
											ls_unidadMedida       = ladap_areaPrivadaData.getIdUnidadMedida();
											ls_unidadMedidaDB     = ladap_areaPrivada.getIdUnidadMedida();

											if(
											    StringUtils.isValidString(ls_unidadMedida)
												    && StringUtils.isValidString(ls_unidadMedidaDB)
											)
											{
												if(!ls_unidadMedida.equalsIgnoreCase(ls_unidadMedidaDB))
													lb_update = true;
											}

											if(
											    NumericUtils.isValidDouble(ld_area)
												    && NumericUtils.isValidDouble(ld_areaDB)
											)
											{
												if(ld_area.compareTo(ld_areaDB) != 0)
													lb_update = true;
											}

											if(lb_update)
											{
												ladap_areaPrivadaData.setArea(ld_area);
												ladap_areaPrivadaData.setIdUnidadMedida(ls_unidadMedida);
												ladap_areaPrivadaData.setIdUsuarioModificacion(as_userId);
												ladap_areaPrivadaData.setIpModificacion(as_remoteIp);

												ladap_DAO.insertOrUpdate(ladap_areaPrivadaData, false);
											}
										}
										else
										{
											if(
											    (ladap_areaPrivadaData != null)
												    && NumericUtils.isValidDouble(ladap_areaPrivadaData.getArea())
											)
											{
												ladap_areaPrivadaData.setIdAreaPredio(
												    StringUtils.getString(ls_idAreaPredio)
												);
												ladap_areaPrivadaData.setIdCirculo(ls_idCirculo);
												ladap_areaPrivadaData.setIdMatricula(ll_idMatricula);
												ladap_areaPrivadaData.setIdUsuarioCreacion(as_userId);
												ladap_areaPrivadaData.setIpCreacion(as_remoteIp);

												ladap_DAO.insertOrUpdate(ladap_areaPrivadaData, true);
											}
										}
									}

									{
										AccPredioRegistroDAO lapr_dao;
										AccPredioRegistro    lpr_registro;

										lapr_dao         = DaoCreator.getAccPredioRegistroDAO(ldm_manager);
										lpr_registro     = new AccPredioRegistro();

										lpr_registro.setIdCirculo(aaui_areaPredio.getIdCirculo());
										lpr_registro.setIdMatricula(aaui_areaPredio.getIdMatricula());
										lpr_registro = lapr_dao.findByCirculoMatricula(lpr_registro);

										if(lpr_registro != null)
										{
											lpr_registro.setIdTipoUsoSuelo(laap_areaPredioData.getTipoSuelo());
											lpr_registro.setIdUsuarioModificacion(as_userId);
											lpr_registro.setIpModificacion(as_remoteIp);

											lapr_dao.updateById(lpr_registro);
										}

										laap_areaPredio.setCoeficiente(laap_areaPredioData.getCoeficiente());
										laap_areaPredio.setTipoSuelo(laap_areaPredioData.getTipoSuelo());
										laap_areaPredio.setIdTurno(aaui_areaPredio.getIdTurno());
										laap_areaPredio.setIdTurnoHistoria(aaui_areaPredio.getIdTurnoHistoria());
										laap_areaPredio.setIdUsuarioModificacion(as_userId);
										laap_areaPredio.setIpModificacion(as_remoteIp);

										laap_DAO.updateById(laap_areaPredio);
									}
								}
								else
								{
									int li_idAreaPredio;

									li_idAreaPredio = laap_DAO.findSecuencia();

									if(li_idAreaPredio > 0)
									{
										Iterator<DetalleAreaPredio> liadap_iterator;

										laap_areaPredio     = new AccAreaPredio();
										liadap_iterator     = lcadap_areasTerrenoData.iterator();

										laap_areaPredio.setIdArea(StringUtils.getString(li_idAreaPredio));
										laap_areaPredio.setIdCirculo(ls_idCirculo);
										laap_areaPredio.setIdMatricula(ll_idMatricula);
										laap_areaPredio.setCoeficiente(laap_areaPredioData.getCoeficiente());
										laap_areaPredio.setTipoSuelo(laap_areaPredioData.getTipoSuelo());
										laap_areaPredio.setIdTurno(aaui_areaPredio.getIdTurno());
										laap_areaPredio.setIdTurnoHistoria(aaui_areaPredio.getIdTurnoHistoria());
										laap_areaPredio.setIdAnotacion(NumericUtils.getLongWrapper(1L));
										laap_areaPredio.setIdUsuarioCreacion(as_userId);
										laap_areaPredio.setIpCreacion(as_remoteIp);

										laap_DAO.insert(laap_areaPredio);

										while(liadap_iterator.hasNext())
										{
											DetalleAreaPredio ladap_areaTerreno;

											ladap_areaTerreno = liadap_iterator.next();

											if(ladap_areaTerreno != null)
											{
												ladap_areaTerreno.setIdAreaPredio(
												    StringUtils.getString(li_idAreaPredio)
												);
												ladap_areaTerreno.setIdCirculo(ls_idCirculo);
												ladap_areaTerreno.setIdMatricula(ll_idMatricula);
												ladap_areaTerreno.setIdUsuarioCreacion(as_userId);
												ladap_areaTerreno.setIpCreacion(as_remoteIp);

												ladap_DAO.insertOrUpdate(ladap_areaTerreno, true);
											}
										}

										if(
										    (ladap_areaConstruidaData != null)
											    && NumericUtils.isValidDouble(ladap_areaConstruidaData.getArea())
										)
										{
											ladap_areaConstruidaData.setIdAreaPredio(
											    StringUtils.getString(li_idAreaPredio)
											);
											ladap_areaConstruidaData.setIdCirculo(ls_idCirculo);
											ladap_areaConstruidaData.setIdMatricula(ll_idMatricula);
											ladap_areaConstruidaData.setIdUsuarioCreacion(as_userId);
											ladap_areaConstruidaData.setIpCreacion(as_remoteIp);

											ladap_DAO.insertOrUpdate(ladap_areaConstruidaData, true);
										}

										if(
										    (ladap_areaPrivadaData != null)
											    && NumericUtils.isValidDouble(ladap_areaPrivadaData.getArea())
										)
										{
											ladap_areaPrivadaData.setIdAreaPredio(
											    StringUtils.getString(li_idAreaPredio)
											);
											ladap_areaPrivadaData.setIdCirculo(ls_idCirculo);
											ladap_areaPrivadaData.setIdMatricula(ll_idMatricula);
											ladap_areaPrivadaData.setIdUsuarioCreacion(as_userId);
											ladap_areaPrivadaData.setIpCreacion(as_remoteIp);

											ladap_DAO.insertOrUpdate(ladap_areaPrivadaData, true);
										}
									}
								}
							}
						}
					}
					else
						throw new B2BException(ErrorKeys.ERROR_AREA_DEL_TERRENO_AGREGAR);
				}
			}
			// Salvar direccion del predio
			{
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("accionSalvarView", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return li_secuenciaBng;
	}

	/**
	 * Método para actualizar las areas del predio
	 *
	 * @param acc_predio
	 *            Objeto para obtener información del area del predio
	 * @param adp_direccion
	 *            Objeto para obtener información de la direcon del predio
	 * @throws B2BException
	 */
	public synchronized void actualizarAreas(
	    AccAreaPredio acc_predio, DireccionPredioAcc adp_direccion, String as_userId, String as_remoteIp
	)
	    throws B2BException
	{
		DAOManager ldm_manager;

		ldm_manager = DaoManagerFactory.getDAOManager();

		try
		{
			if(acc_predio != null)
			{
				AccAreaPredioDAO      laap_dao;
				AccAreaPredio         lacc_predio;
				DireccionPredioAccDAO ldp_dao;
				DireccionPredioAcc    ldp_direccion;

				laap_dao     = DaoCreator.getAccAreaPredioDAO(ldm_manager);
				ldp_dao      = DaoCreator.getDireccionPredioAccDAO(ldm_manager);

				lacc_predio       = laap_dao.findByCirculoMatricula(acc_predio);
				ldp_direccion     = ldp_dao.findLastByIdCirculoMatricula(adp_direccion);

				if(lacc_predio != null)
				{
					AccDetalleAreaPredioDAO ladap_DAO;
					AccPredioRegistro       lpr_registro;
					AccPredioRegistroDAO    lapr_dao;
					Long                    ll_idMatricula;
					String                  ls_idCirculo;
					String                  ls_idAreaPredio;
					final String            ls_uno   = "1";
					TipoUsoSueloDAO         ltus_dao;

					ladap_DAO                        = DaoCreator.getAccDetalleAreaPredioDAO(ldm_manager);
					lapr_dao                         = DaoCreator.getAccPredioRegistroDAO(ldm_manager);
					ll_idMatricula                   = lacc_predio.getIdMatricula();
					ls_idCirculo                     = lacc_predio.getIdCirculo();
					ls_idAreaPredio                  = lacc_predio.getIdArea();
					ltus_dao                         = DaoCreator.getTipoUsoSueloDAO(ldm_manager);

					TipoUsoSuelo ltus_suelo          = null;

					lacc_predio.setCoeficiente(acc_predio.getCoeficiente());
					lacc_predio.setIdUsuarioModificacion(acc_predio.getIdUsuarioModificacion());

					if(StringUtils.isValidString(acc_predio.getTipoSuelo()))
					{
						TipoUsoSuelo tus_temp = new TipoUsoSuelo();

						tus_temp.setDescription(acc_predio.getTipoSuelo());

						ltus_suelo = ltus_dao.findByDescription(tus_temp);

						if(ltus_suelo != null)
						{
							if(StringUtils.isValidString(ltus_suelo.getIdTipoUsoSuelo()))
								acc_predio.setTipoSuelo(ltus_suelo.getIdTipoUsoSuelo());
						}
						else
							acc_predio.setTipoSuelo("");
					}

					AccPredioRegistro lapr_temp;

					lapr_temp = new AccPredioRegistro();

					lapr_temp.setIdMatricula(ll_idMatricula);
					lapr_temp.setIdCirculo(ls_idCirculo);

					lpr_registro = lapr_dao.findByCirculoMatricula(lapr_temp);

					if(lpr_registro != null)
					{
						lpr_registro.setIdTipoUsoSuelo(acc_predio.getTipoSuelo());
						lpr_registro.setIdUsuarioModificacion(as_userId);
						lpr_registro.setIpModificacion(as_remoteIp);

						lapr_dao.updateById(lpr_registro);
					}

					lacc_predio.setTipoSuelo(acc_predio.getTipoSuelo());
					lacc_predio.setIdUsuarioModificacion(as_userId);
					lacc_predio.setIpModificacion(as_remoteIp);

					laap_dao.updateById(lacc_predio);

					{
						boolean           lb_areaValida;
						DetalleAreaPredio ldap_detalleAreaPredio;
						Double            ld_area;

						ldap_detalleAreaPredio     = new DetalleAreaPredio();
						ld_area                    = acc_predio.getAreaPredio();
						lb_areaValida              = NumericUtils.isValidDouble(ld_area);

						ldap_detalleAreaPredio.setIdAreaPredio(ls_idAreaPredio);
						ldap_detalleAreaPredio.setIdTipoArea(TipoAreaCommon.TERRENO);
						ldap_detalleAreaPredio.setIdCirculo(ls_idCirculo);
						ldap_detalleAreaPredio.setIdMatricula(ll_idMatricula);

						ldap_detalleAreaPredio = ladap_DAO.findByIdAreaPredioTipo(ldap_detalleAreaPredio);

						if(ldap_detalleAreaPredio != null)
						{
							if(lb_areaValida)
							{
								ldap_detalleAreaPredio.setArea(ld_area);
								ldap_detalleAreaPredio.setIdUnidadMedida(UnidadMedidaAreaCommon.METROS_CUADRADOS);
								ldap_detalleAreaPredio.setIdUsuarioModificacion(as_userId);
								ldap_detalleAreaPredio.setIpModificacion(as_remoteIp);

								ladap_DAO.insertOrUpdate(ldap_detalleAreaPredio, false);
							}
							else
								ladap_DAO.delete(ldap_detalleAreaPredio);
						}
						else
						{
							if(lb_areaValida)
							{
								ldap_detalleAreaPredio = new DetalleAreaPredio();

								ldap_detalleAreaPredio.setIdAreaPredio(ls_idAreaPredio);
								ldap_detalleAreaPredio.setIdDetalleAreaPredio(ls_uno);
								ldap_detalleAreaPredio.setIdTipoArea(TipoAreaCommon.TERRENO);
								ldap_detalleAreaPredio.setIdUnidadMedida(UnidadMedidaAreaCommon.METROS_CUADRADOS);
								ldap_detalleAreaPredio.setArea(ld_area);
								ldap_detalleAreaPredio.setIdCirculo(ls_idCirculo);
								ldap_detalleAreaPredio.setIdMatricula(ll_idMatricula);
								ldap_detalleAreaPredio.setIdUsuarioCreacion(as_userId);
								ldap_detalleAreaPredio.setIpCreacion(as_remoteIp);

								ladap_DAO.insertOrUpdate(ldap_detalleAreaPredio, true);
							}
						}
					}

					{
						boolean           lb_areaValida;
						DetalleAreaPredio ldap_detalleAreaPredio;
						Double            ld_area;

						ldap_detalleAreaPredio     = new DetalleAreaPredio();
						ld_area                    = acc_predio.getAreaPrivadaConstruida();
						lb_areaValida              = NumericUtils.isValidDouble(ld_area);

						ldap_detalleAreaPredio.setIdAreaPredio(ls_idAreaPredio);
						ldap_detalleAreaPredio.setIdTipoArea(TipoAreaCommon.PRIVADA);
						ldap_detalleAreaPredio.setIdCirculo(ls_idCirculo);
						ldap_detalleAreaPredio.setIdMatricula(ll_idMatricula);

						ldap_detalleAreaPredio = ladap_DAO.findByIdAreaPredioTipo(ldap_detalleAreaPredio);

						if(ldap_detalleAreaPredio != null)
						{
							if(lb_areaValida)
							{
								ldap_detalleAreaPredio.setArea(ld_area);
								ldap_detalleAreaPredio.setIdUnidadMedida(UnidadMedidaAreaCommon.METROS_CUADRADOS);
								ldap_detalleAreaPredio.setIdUsuarioModificacion(as_userId);
								ldap_detalleAreaPredio.setIpModificacion(as_remoteIp);

								ladap_DAO.insertOrUpdate(ldap_detalleAreaPredio, false);
							}
							else
								ladap_DAO.delete(ldap_detalleAreaPredio);
						}
						else
						{
							if(lb_areaValida)
							{
								ldap_detalleAreaPredio = new DetalleAreaPredio();

								ldap_detalleAreaPredio.setIdAreaPredio(ls_idAreaPredio);
								ldap_detalleAreaPredio.setIdDetalleAreaPredio(ls_uno);
								ldap_detalleAreaPredio.setIdTipoArea(TipoAreaCommon.PRIVADA);
								ldap_detalleAreaPredio.setIdUnidadMedida(UnidadMedidaAreaCommon.METROS_CUADRADOS);
								ldap_detalleAreaPredio.setArea(ld_area);
								ldap_detalleAreaPredio.setIdCirculo(ls_idCirculo);
								ldap_detalleAreaPredio.setIdMatricula(ll_idMatricula);
								ldap_detalleAreaPredio.setIdUsuarioCreacion(as_userId);
								ldap_detalleAreaPredio.setIpCreacion(as_remoteIp);

								ladap_DAO.insertOrUpdate(ldap_detalleAreaPredio, true);
							}
						}
					}

					{
						boolean           lb_areaValida;
						DetalleAreaPredio ldap_detalleAreaPredio;
						Double            ld_area;

						ldap_detalleAreaPredio     = new DetalleAreaPredio();
						ld_area                    = acc_predio.getAreaConstruida();
						lb_areaValida              = NumericUtils.isValidDouble(ld_area);

						ldap_detalleAreaPredio.setIdAreaPredio(ls_idAreaPredio);
						ldap_detalleAreaPredio.setIdTipoArea(TipoAreaCommon.CONSTRUIDA);
						ldap_detalleAreaPredio.setIdCirculo(ls_idCirculo);
						ldap_detalleAreaPredio.setIdMatricula(ll_idMatricula);

						ldap_detalleAreaPredio = ladap_DAO.findByIdAreaPredioTipo(ldap_detalleAreaPredio);

						if(ldap_detalleAreaPredio != null)
						{
							if(lb_areaValida)
							{
								ldap_detalleAreaPredio.setArea(ld_area);
								ldap_detalleAreaPredio.setIdUnidadMedida(UnidadMedidaAreaCommon.METROS_CUADRADOS);
								ldap_detalleAreaPredio.setIdUsuarioModificacion(as_userId);
								ldap_detalleAreaPredio.setIpModificacion(as_remoteIp);

								ladap_DAO.insertOrUpdate(ldap_detalleAreaPredio, false);
							}
							else
								ladap_DAO.delete(ldap_detalleAreaPredio);
						}
						else
						{
							if(lb_areaValida)
							{
								ldap_detalleAreaPredio = new DetalleAreaPredio();

								ldap_detalleAreaPredio.setIdAreaPredio(ls_idAreaPredio);
								ldap_detalleAreaPredio.setIdDetalleAreaPredio(ls_uno);
								ldap_detalleAreaPredio.setIdTipoArea(TipoAreaCommon.CONSTRUIDA);
								ldap_detalleAreaPredio.setIdUnidadMedida(UnidadMedidaAreaCommon.METROS_CUADRADOS);
								ldap_detalleAreaPredio.setArea(ld_area);
								ldap_detalleAreaPredio.setIdCirculo(ls_idCirculo);
								ldap_detalleAreaPredio.setIdMatricula(ll_idMatricula);
								ldap_detalleAreaPredio.setIdUsuarioCreacion(as_userId);
								ldap_detalleAreaPredio.setIpCreacion(as_remoteIp);

								ladap_DAO.insertOrUpdate(ldap_detalleAreaPredio, true);
							}
						}
					}
				}

				if(ldp_direccion != null)
				{
					ldp_direccion.setComplementoDireccion(adp_direccion.getComplementoDireccion());
					ldp_direccion.setIdUsuarioModificacion(adp_direccion.getIdUsuarioModificacion());
					ldp_direccion.setIpModificacion(adp_direccion.getIpModificacion());

					ldp_dao.update(ldp_direccion);
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("cargarLinderos", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}
	}

	/**
	 * Método encargado actualizar una lista de intervinientes.
	 *
	 * @param ap_persona Objeto que contiene la información para actualizar la lista.
	 * @param aca_cllAnotacion Coleccion de objetos que contiene la información para actualizar la lista.
	 * @param asi_solicitudInterviniente
	 * @return aca_cllAnotacion Coleccion que contiene la información actualizada.
	 * @throws B2BException
	 */
	public synchronized Collection<Anotacion> actualizarListaIntervinientes(
	    Persona ap_persona, Collection<Anotacion> aca_cllAnotacion, SolicitudInterviniente asi_solicitudInterviniente
	)
	    throws B2BException
	{
		if(
		    (ap_persona != null) && CollectionUtils.isValidCollection(aca_cllAnotacion)
			    && (asi_solicitudInterviniente != null)
		)
		{
			for(Anotacion la_anotacion : aca_cllAnotacion)
			{
				if(la_anotacion != null)
				{
					Persona lp_personaActual;

					lp_personaActual = la_anotacion.getPersona();

					if(lp_personaActual != null)
					{
						String ls_idPersonaActual;
						String ls_idPersonaModificado;

						ls_idPersonaActual         = lp_personaActual.getIdPersona();
						ls_idPersonaModificado     = ap_persona.getIdPersona();

						if(
						    StringUtils.isValidString(ls_idPersonaActual)
							    && StringUtils.isValidString(ls_idPersonaModificado)
						)
						{
							if(ls_idPersonaActual.equalsIgnoreCase(ls_idPersonaModificado))
							{
								la_anotacion.setPersona(ap_persona);
								la_anotacion.setSolicitudInterviniente(asi_solicitudInterviniente);
							}
						}
					}
				}
			}
		}

		return aca_cllAnotacion;
	}

	/**
	 * Método encargado de actualizar la revisión para una matricula determinada.
	 *
	 * @param aottf_data Objeto de tipo AccPredioRegistro que contiene los datos necesarios para realizar la actualización.
	 * @param ab_b Variable de tipo boolean encargada de definir si se actualiza la revisión en digitador masivo o calificación
	 * @throws B2BException
	 */
	public synchronized void actualizarRevision(
	    AccPredioRegistro aottf_data, RegistroCalificacion arc_data, boolean ab_b
	)
	    throws B2BException
	{
		DAOManager ldm_manager;

		ldm_manager = DaoManagerFactory.getDAOManager();

		try
		{
			if(aottf_data != null)
				DaoCreator.getAccPredioRegistroDAO(ldm_manager).actualizarRevision(aottf_data, ab_b);

			if(arc_data != null)
			{
				Long   ll_idTurnoHistoria;
				Long   ll_idMatricula;
				String ls_idTurno;
				String ls_idCirculo;
				String ls_idUsuario;
				String ls_ipRemote;

				ll_idTurnoHistoria     = arc_data.getIdTurnoHistoria();
				ll_idMatricula         = arc_data.getIdMatricula();
				ls_idTurno             = arc_data.getIdTurno();
				ls_idCirculo           = arc_data.getIdCirculo();
				ls_idUsuario           = arc_data.getIdUsuario();
				ls_ipRemote            = arc_data.getIpAddress();

				if(StringUtils.isValidString(ls_idCirculo) && NumericUtils.isValidLong(ll_idMatricula))
				{
					HashMap<String, DireccionPredio> lhmsdp_direcciones;

					lhmsdp_direcciones = arc_data.getDireccionesSeleccionadas();

					if(CollectionUtils.isValidCollection(lhmsdp_direcciones))
					{
						DireccionPredioAccDAO ldpa_DAO;
						int                   li_idDireccion;

						li_idDireccion     = 1;
						ldpa_DAO           = DaoCreator.getDireccionPredioAccDAO(ldm_manager);

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
									ldpa_direccionTemp.setDatoEjePrincipal(ldp_direccionPredio.getDatoEjePrincipal());
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
									ldpa_direccionTemp.setDatoEjeSecundario(ldp_direccionPredio.getDatoEjeSecundario());
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

									//ldpa_DAO.insert(ldpa_direccionTemp);
									li_idDireccion++;
								}
							}
						}
					}
					else
						throw new B2BException(ErrorKeys.DEBE_AGREGAR_DIRECCION_PREDIO);
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("actualizarRevision", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}
	}

	/**
	 * Metodo encargado de agregar matriculas para el proceso de complementacion copiada de matriculas
	 * @param as_matricula Parametro con el cual se agregará la matricula a la colección de matriculas
	 * @param acmb_matriculas Parametro del cual se consultarán atributos para realizar validaciones
	 * @param acc_complementacionCalificacion Parametro del cual se consultarán atributos para realizar validaciones
	 * @param arc_registroCalificacion Parametro del cual se consultarán atributos para realizar validaciones
	 * @throws B2BException
	 */
	public synchronized Collection<MatriculaBase> agregarMatriculaComplementacion(
	    Collection<MatriculaBase>   acmb_matriculas, ComplementacionCalificacion acc_complementacionCalificacion,
	    RegistroCalificacion        arc_registroCalificacion, String as_matricula, boolean ab_primerVez
	)
	    throws B2BException
	{
		DAOManager ldm_manager;

		ldm_manager = DaoManagerFactory.getDAOManager();

		try
		{
			MatriculaBase lmb_matricula;

			lmb_matricula = new MatriculaBase();

			if(StringUtils.isValidString(as_matricula))
			{
				ComplementacionCalificacion lcc_complemetacionCalificacion;

				lcc_complemetacionCalificacion = acc_complementacionCalificacion;

				if(lcc_complemetacionCalificacion != null)
				{
					Complementacion lc_complementacion;

					lc_complementacion = lcc_complemetacionCalificacion.getComplementacion();

					if(lc_complementacion != null)
					{
						PredioRegistroDAO lprd_DAO;

						lprd_DAO = DaoCreator.getPredioRegistroDAO(ldm_manager);

						lmb_matricula.setIdCirculo(lc_complementacion.getIdCirculo());

						lmb_matricula.setIdMatricula(NumericUtils.getLongWrapper(as_matricula));

						if(CollectionUtils.isValidCollection(acmb_matriculas))
						{
							for(MatriculaBase lmb_temp : acmb_matriculas)
							{
								if(lmb_temp != null)
								{
									if(
									    lmb_temp.getIdCirculo().equalsIgnoreCase(lmb_matricula.getIdCirculo())
										    && lmb_temp.getIdMatricula().equals(lmb_matricula.getIdMatricula())
									)
										throw new B2BException(ErrorKeys.MATRICULA_INGRESADA_DUPLICADA);
									else
									{
										PredioRegistro lpr_predioRegistro;

										lpr_predioRegistro = new PredioRegistro();

										lpr_predioRegistro.setIdCirculo(lmb_matricula.getIdCirculo());
										lpr_predioRegistro.setIdMatricula(
										    NumericUtils.getLong(lmb_matricula.getIdMatricula())
										);
										lpr_predioRegistro.setValidMatricula(true);
										lpr_predioRegistro = lprd_DAO.findById(lpr_predioRegistro);

										if(lpr_predioRegistro != null)
										{
											String ls_predioInconsistente;
											ls_predioInconsistente = StringUtils.getStringNotNull(
												    lpr_predioRegistro.getPredioInconsistente()
												);

											if(ls_predioInconsistente.equalsIgnoreCase(EstadoCommon.S))
											{
												Object[] loa_arg;

												loa_arg        = new String[1];
												loa_arg[0]     = StringUtils.getStringNotNull(
													    lpr_predioRegistro.getIdCirculo()
													) + IdentificadoresCommon.SIMBOLO_GUION
													+ lpr_predioRegistro.getIdMatricula();

												throw new B2BException(
												    ErrorKeys.PREDIO_SE_ENCUENTRA_ESTADO_INCONSISTENTE, loa_arg
												);
											}
											else if(
											    !NumericUtils.isValidBigDecimal(
												        lpr_predioRegistro.getIdComplementacion()
												    )
											)
												throw new B2BException(ErrorKeys.PREDIO_SIN_COMPLEMENTACION);
										}
									}
								}
							}

							acmb_matriculas.add(lmb_matricula);
						}
						else
						{
							PredioRegistro lpr_predioRegistro;

							lpr_predioRegistro = new PredioRegistro();

							lpr_predioRegistro.setIdCirculo(lmb_matricula.getIdCirculo());
							lpr_predioRegistro.setIdMatricula(NumericUtils.getLong(lmb_matricula.getIdMatricula()));
							lpr_predioRegistro.setValidMatricula(true);
							lpr_predioRegistro = lprd_DAO.findById(lpr_predioRegistro);

							if(lpr_predioRegistro != null)
							{
								String ls_predioInconsistente;
								ls_predioInconsistente = StringUtils.getStringNotNull(
									    lpr_predioRegistro.getPredioInconsistente()
									);

								if(ls_predioInconsistente.equalsIgnoreCase(EstadoCommon.S))
								{
									Object[] loa_arg;

									loa_arg        = new String[1];
									loa_arg[0]     = StringUtils.getStringNotNull(lpr_predioRegistro.getIdCirculo())
										+ IdentificadoresCommon.SIMBOLO_GUION + lpr_predioRegistro.getIdMatricula();

									throw new B2BException(ErrorKeys.PREDIO_SE_ENCUENTRA_ESTADO_INCONSISTENTE, loa_arg);
								}
								else if(!NumericUtils.isValidBigDecimal(lpr_predioRegistro.getIdComplementacion()))
									throw new B2BException(ErrorKeys.PREDIO_SIN_COMPLEMENTACION);
								else
								{
									acmb_matriculas = new ArrayList<MatriculaBase>();
									acmb_matriculas.add(lmb_matricula);
								}
							}
							else
								throw new B2BException(ErrorKeys.NO_SE_ENCONTRARON_REGISTROS);
						}
					}
				}
			}
			else if(!ab_primerVez)
			{
				RegistroCalificacion lrc_registroCalificacion;

				lrc_registroCalificacion = arc_registroCalificacion;

				if(lrc_registroCalificacion != null)
				{
					if(arc_registroCalificacion.isBaldios())
					{
						acmb_matriculas = new ArrayList<MatriculaBase>();

						lmb_matricula.setIdCirculo(arc_registroCalificacion.getIdCirculo());
						lmb_matricula.setIdMatricula(arc_registroCalificacion.getIdMatricula());
						acmb_matriculas.add(lmb_matricula);
					}
					else
					{
						Collection<RegistroCalificacion> lcrc_allMatriculas;

						lcrc_allMatriculas = lrc_registroCalificacion.getAllMatriculas();

						if(CollectionUtils.isValidCollection(lcrc_allMatriculas))
						{
							acmb_matriculas = new ArrayList<MatriculaBase>();

							for(RegistroCalificacion lrc_temp : lcrc_allMatriculas)
							{
								if(lrc_temp != null)
								{
									Matricula lm_matricula;

									lm_matricula = Matricula.getMatricula(lrc_temp.getIdCirculo());

									if(lm_matricula != null)
									{
										lmb_matricula.setIdCirculo(lm_matricula.getCirculo());
										lmb_matricula.setIdMatricula(lm_matricula.getMatricula());
										acmb_matriculas.add(lmb_matricula);
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

			clh_LOGGER.error("agregarMatriculaComplementacion", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return acmb_matriculas;
	}

	/**
	 * Método encargado de consultas las alertas para los intervinientes de Baldios.
	 *
	 * @param as_idPersona Variable de tipo String que contiene el id de la persona a consultar.
	 * @param ab_accion Variable de tipo booleana que valida que tipo de alerta se va generar.
	 * @param as_tipoActo Vairable de tipo String que contiene el id tipo acto a validar.
	 * @return Arreglo de Strings que contiene las alertas generadas.
	 * @throws B2BException
	 */
	public synchronized Map<String, Object[]> alertasIntervinientesBaldios(
	    String as_idPersona, boolean ab_accion, String as_tipoActo
	)
	    throws B2BException
	{
		DAOManager            ldm_manager;
		Map<String, Object[]> lsa_return;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		lsa_return      = null;

		try
		{
			lsa_return = alertasIntervinientesBaldios(as_idPersona, ab_accion, as_tipoActo, ldm_manager);
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("alertasIntervinientesBaldios ", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lsa_return;
	}

	/**
	 * Guardar solicitudes apoyo regional orip.
	 *
	 * @param asanui_param de asanui param
	 * @param as_userId de as user id
	 * @param as_remoteIp de as remote ip
	 * @return el valor de solicitud apoyo nacional UI
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public synchronized SolicitudApoyoNacionalUI aprobarReasignacionApoyoNacional(
	    SolicitudApoyoNacionalUI asanui_param, String as_userId, String as_remoteIp
	)
	    throws B2BException
	{
		if(asanui_param != null)
		{
			DAOManager ldm_manager;

			ldm_manager = DaoManagerFactory.getDAOManager();

			try
			{
				TurnoHistoria lth_turnoHistoria;

				lth_turnoHistoria = DaoCreator.getTurnoHistoriaDAO(ldm_manager)
						                          .findById(
						    NumericUtils.getLongWrapper(asanui_param.getIdTurnoHistoria())
						);

				if(lth_turnoHistoria != null)
				{
					lth_turnoHistoria.setIdUsuarioModificacion(as_userId);
					lth_turnoHistoria.setIpModificacion(as_remoteIp);

					lth_turnoHistoria = DaoCreator.getProcedimientosDAO(ldm_manager)
							                          .spReasignacionApoyoNacional(lth_turnoHistoria);

					Collection<SolicitudApoyoNacional> lcsan_solicitudesApoyoNacional;
					lcsan_solicitudesApoyoNacional = DaoCreator.getSolicitudApoyoNacionalDAO(ldm_manager)
							                                       .findByIdSolicitud(
							    lth_turnoHistoria.getIdSolicitud()
							);

					if(CollectionUtils.isValidCollection(lcsan_solicitudesApoyoNacional))
					{
						SolicitudDAO        lss_dao;
						CirculoRegistralDao lcrd_dao;
						lss_dao      = DaoCreator.getSolicitudDAO(ldm_manager);
						lcrd_dao     = DaoCreator.getCirculoRegistralDAO(ldm_manager);

						for(SolicitudApoyoNacional lsan_solicitudApoyoNacional : lcsan_solicitudesApoyoNacional)
						{
							if(lsan_solicitudApoyoNacional != null)
							{
								CirculoRegistral lcr_circulo;
								Solicitud        ls_solicitud;

								lcr_circulo      = lcrd_dao.findById(
									    lsan_solicitudApoyoNacional.getIdCirculoAsignado()
									);
								ls_solicitud     = lss_dao.findById(
									    lsan_solicitudApoyoNacional.getIdSolicitudRegistro()
									);

								if(ls_solicitud != null)
									lsan_solicitudApoyoNacional.setNirRegistro(ls_solicitud.getNir());

								if(lcr_circulo != null)
									lsan_solicitudApoyoNacional.setNombreCirculo(lcr_circulo.getNombre());
							}
						}
					}

					asanui_param.setSolicitudesApoyoNacional(lcsan_solicitudesApoyoNacional);
				}
			}
			catch(B2BException lb2be_e)
			{
				ldm_manager.setRollbackOnly();

				clh_LOGGER.error("aprobarReasignacionApoyoNacional", lb2be_e);

				throw lb2be_e;
			}
			finally
			{
				ldm_manager.close();
			}
		}

		return asanui_param;
	}

	/**
	 * Método encargado de consultas las alertas para los intervinientes de Baldios.
	 *
	 * @param as_idTurnoHistoria
	 *            String para encontrar el objeto turno historia
	 * @param ab_registroParcial
	 *            Booleana para saber si es nota devolutiva parcial o normal
	 * @return
	 * @throws B2BException
	 */
	public synchronized boolean archivoGenerado(String as_idTurnoHistoria, boolean ab_registroParcial)
	    throws B2BException
	{
		DAOManager ldm_manager;
		boolean    lb_return;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		lb_return       = false;

		try
		{
			if(StringUtils.isValidString(as_idTurnoHistoria))
			{
				TurnoHistoria    lth_turnoHistoria;
				TurnoHistoriaDAO lth_DAO;

				lth_turnoHistoria     = new TurnoHistoria();
				lth_DAO               = DaoCreator.getTurnoHistoriaDAO(ldm_manager);

				lth_turnoHistoria.setIdTurnoHistoria(NumericUtils.getLongWrapper(as_idTurnoHistoria));

				lth_turnoHistoria = lth_DAO.findById(lth_turnoHistoria);

				if(lth_turnoHistoria != null)
				{
					Long ll_idTurnoHistoria;

					ll_idTurnoHistoria = lth_turnoHistoria.getIdTurnoHistoria();

					if(NumericUtils.isValidLong(ll_idTurnoHistoria))
					{
						DocumentosSalida             lds_documentoSalida;
						Collection<DocumentosSalida> lcds_documentos;
						DocumentosSalidaDAO          lds_DAO;

						lds_documentoSalida     = new DocumentosSalida();
						lds_DAO                 = DaoCreator.getDocumentosSalidaDAO(ldm_manager);

						lds_documentoSalida.setIdTurnoHistoria(NumericUtils.getInteger(ll_idTurnoHistoria));

						lcds_documentos = lds_DAO.findByIdTurnoHistoria(lds_documentoSalida);

						if(CollectionUtils.isValidCollection(lcds_documentos))
						{
							for(DocumentosSalida lds_doc : lcds_documentos)
							{
								String ls_tipo;
								String ls_archivo;

								ls_tipo        = lds_doc.getTipo();
								ls_archivo     = ab_registroParcial ? TipoArchivoCommon.NOTA_DEVOLUTIVA_PARCIAL
									                                : TipoArchivoCommon.NOTA_DEVOLUTIVA;

								if(StringUtils.isValidString(ls_tipo) && ls_tipo.equalsIgnoreCase(ls_archivo))
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

			clh_LOGGER.error("cargarComplementacion", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lb_return;
	}

	/**
	 * Método encargado de cargar los datos de la anotación que se va a crear en el proceso.
	 *
	 * @param arc_data Objeto que contiene la información de la anotación a crear.
	 * @return Colección que contiene la anotación creada.
	 * @throws B2BException
	 */
	public synchronized Collection<RegistroCalificacion> cargarAnotacionProceso(RegistroCalificacion arc_data)
	    throws B2BException
	{
		Collection<RegistroCalificacion> lcrc_return;

		lcrc_return = new ArrayList<RegistroCalificacion>();

		if(arc_data != null)
		{
			DAOManager ldm_manager;

			ldm_manager = DaoManagerFactory.getDAOManager();

			try
			{
				Long ll_idTurnoHistoria;

				ll_idTurnoHistoria = arc_data.getIdTurnoHistoria();

				if(NumericUtils.isValidLong(ll_idTurnoHistoria))
				{
					TurnoHistoria lth_turnoHitoria;

					lth_turnoHitoria = new TurnoHistoria();

					lth_turnoHitoria.setIdTurnoHistoria(ll_idTurnoHistoria);

					lth_turnoHitoria = DaoCreator.getTurnoHistoriaDAO(ldm_manager).findById(lth_turnoHitoria);

					if(lth_turnoHitoria != null)
					{
						RegistroCalificacion lrc_anotacion;
						String               ls_idSolicitud;
						String               ls_idConstante;

						lrc_anotacion      = new RegistroCalificacion();
						ls_idSolicitud     = lth_turnoHitoria.getIdSolicitud();
						ls_idConstante     = null;

						lrc_anotacion.setIdCirculo(arc_data.getIdCirculo());
						lrc_anotacion.setIdMatricula(arc_data.getIdMatricula());
						lrc_anotacion.setFechaRegistro(new Date());
						lrc_anotacion.setRadicacion(arc_data.getTurno());
						lrc_anotacion.setIdEstadoAnotacion(EstadoCommon.VALIDA);

						if(arc_data.isEnglobe())
							ls_idConstante = ConstanteCommon.CODIGOS_ACTOS_ENGLOBES;
						else if(arc_data.isDesenglobe())
							ls_idConstante = ConstanteCommon.CODIGOS_ACTOS_DESENGLOBES;
						else if(arc_data.isVentaParcial())
							ls_idConstante = ConstanteCommon.CODIGOS_ACTOS_COMPRAVENTA;

						if(StringUtils.isValidString(ls_idConstante))
						{
							Constantes lc_constante;

							lc_constante = DaoCreator.getConstantesDAO(ldm_manager).findById(ls_idConstante);

							if(lc_constante != null)
							{
								String ls_idTipoActo;

								ls_idTipoActo = lc_constante.getCaracter();

								if(StringUtils.isValidString(ls_idTipoActo))
								{
									TipoActo lta_tipoActo;

									lrc_anotacion.setCodActo(ls_idTipoActo);

									lta_tipoActo = DaoCreator.getTipoActoDAO(ldm_manager).findById(ls_idTipoActo);

									if(lta_tipoActo != null)
										lrc_anotacion.setNombreActo(lta_tipoActo.getNombre());
								}
							}
						}

						if(StringUtils.isValidString(ls_idSolicitud))
						{
							Solicitud ls_solicitud;

							ls_solicitud = new Solicitud();

							ls_solicitud.setIdSolicitud(ls_idSolicitud);

							ls_solicitud = DaoCreator.getSolicitudDAO(ldm_manager).findById(ls_solicitud);

							if(ls_solicitud != null)
							{
								String ls_idDocumento;

								ls_idDocumento = ls_solicitud.getIdDocumento();

								if(StringUtils.isValidString(ls_idDocumento))
								{
									Documento ld_documento;

									ld_documento = new Documento();

									ld_documento.setIdDocumento(ls_idDocumento);

									ld_documento = DaoCreator.getDocumentoDAO(ldm_manager).findById(ld_documento);

									if(ld_documento != null)
									{
										String     ls_idOficinaOrigen;
										String     ls_idTipoDocumento;
										BigDecimal ldb_version;

										ls_idOficinaOrigen     = ld_documento.getIdOficinaOrigen();
										ls_idTipoDocumento     = ld_documento.getIdTipoDocumento();
										ldb_version            = ld_documento.getVersion();

										lrc_anotacion.setNumero(ld_documento.getNumero());
										lrc_anotacion.setFechaDocumentoStr(
										    StringUtils.getString(
										        ld_documento.getFechaDocumento(), FormatoFechaCommon.DIA_MES_ANIO
										    )
										);

										if(StringUtils.isValidString(ls_idOficinaOrigen) && (ldb_version != null))
										{
											OficinaOrigen loo_oficinaOrigen;

											loo_oficinaOrigen = new OficinaOrigen();

											loo_oficinaOrigen.setIdOficinaOrigen(ls_idOficinaOrigen);
											loo_oficinaOrigen.setVersion(ldb_version);

											loo_oficinaOrigen = DaoCreator.getOficinaOrigenDAO(ldm_manager)
													                          .findById(loo_oficinaOrigen);

											if(loo_oficinaOrigen != null)
												lrc_anotacion.setNombreOficinaOrigen(loo_oficinaOrigen.getNombre());
										}

										if(StringUtils.isValidString(ls_idTipoDocumento))
										{
											TipoDocumentoPublico ltdp_tipoDocumento;

											ltdp_tipoDocumento = new TipoDocumentoPublico();

											ltdp_tipoDocumento.setIdTipoDocumento(ls_idTipoDocumento);

											ltdp_tipoDocumento = DaoCreator.getTipoDocumentoPublicoDAO(ldm_manager)
													                           .findById(ltdp_tipoDocumento);

											if(ltdp_tipoDocumento != null)
												lrc_anotacion.setDocumento(ltdp_tipoDocumento.getNombre());
										}
									}
								}
							}
						}

						lcrc_return.add(lrc_anotacion);
					}
				}
			}
			catch(B2BException lb2be_e)
			{
				ldm_manager.setRollbackOnly();

				clh_LOGGER.error("cargarAnotacionProceso", lb2be_e);

				throw lb2be_e;
			}
			finally
			{
				ldm_manager.close();
			}
		}

		return lcrc_return;
	}

	/**
	 * Método encargado de cargar la información de las anotaciones a heredar para englobes.
	 *
	 * @param arc_data Objeto que contiene la información de las anotaciones que se van a heredar.
	 * @return Objeto que contiene las anotaciones consultadas.
	 * @throws B2BException
	 */
	public synchronized RegistroCalificacion cargarAnotacionesEnglobes(RegistroCalificacion arc_data)
	    throws B2BException
	{
		RegistroCalificacion lrc_return;

		lrc_return = new RegistroCalificacion();

		if(arc_data != null)
		{
			DAOManager ldm_manager;

			ldm_manager = DaoManagerFactory.getDAOManager();

			try
			{
				lrc_return.setAllMatriculas(
				    DaoCreator.getRegistroCalificacionDAO(ldm_manager).matriculasAHeredar(arc_data, true)
				);
				lrc_return.setIdCirculo(arc_data.getIdCirculo());
				lrc_return.setIdMatricula(arc_data.getIdMatricula());
				lrc_return.setTurno(arc_data.getTurno());
				lrc_return.setIdTurnoHistoria(arc_data.getIdTurnoHistoria());
			}
			catch(B2BException lb2be_e)
			{
				ldm_manager.setRollbackOnly();

				clh_LOGGER.error("cargarAnotacionesEnglobes", lb2be_e);

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
	 * Método encargado de cargar la información del cierre de folio.
	 *
	 * @param arc_data Objeto que contiene la información para realizar la consulta.
	 * @return Objeto que contiene la información consultada.
	 * @throws B2BException
	 */
	public synchronized RegistroCalificacion cargarCierreFolio(RegistroCalificacion arc_data)
	    throws B2BException
	{
		if(arc_data != null)
		{
			DAOManager ldm_manager;

			ldm_manager = DaoManagerFactory.getDAOManager();

			try
			{
				CambioEstadoPredio lcep_data;

				lcep_data = new CambioEstadoPredio();

				lcep_data.setIdTurno(arc_data.getTurno());

				lcep_data = DaoCreator.getCambioEstadoPredioDAO(ldm_manager).findByIdTurno(lcep_data);

				if(lcep_data == null)
				{
					lcep_data = new CambioEstadoPredio();

					lcep_data.setMotivoCambioEstado(EstadoCommon.ENGLOBE);
				}

				arc_data.setCambioEstadoPredio(lcep_data);
			}
			catch(B2BException lb2be_e)
			{
				ldm_manager.setRollbackOnly();

				clh_LOGGER.error("cargarCierreFolio", lb2be_e);

				throw lb2be_e;
			}
			finally
			{
				ldm_manager.close();
			}
		}

		return arc_data;
	}

	/**
	 * Metodo para buscar la complementación del predio
	 *
	 * @param acp_cp
	 *            Objeto para buscar la complementacion
	 * @param as_userId
	 *            Usuario de sesión
	 * @return
	 * @throws B2BException
	 */
	public synchronized AccComplementacionPredio cargarComplementacion(
	    AccComplementacionPredio acp_cp, String as_userId
	)
	    throws B2BException
	{
		DAOManager               ldm_manager;
		AccComplementacionPredio lacp_complementacion;

		ldm_manager              = DaoManagerFactory.getDAOManager();
		lacp_complementacion     = null;

		try
		{
			AccComplementacionPredio    lacp_actual;
			AccComplementacionPredioDAO lacd_DAO;

			lacd_DAO     = DaoCreator.getAccComplementacionPredioDAO(ldm_manager);

			lacp_actual = lacd_DAO.findComplemByIdTurno(acp_cp);

			if(lacp_actual != null)
			{
				String ls_iteratorComplementacion;

				ls_iteratorComplementacion = lacp_actual.getComplementacion();

				if(StringUtils.isValidString(ls_iteratorComplementacion))
					lacp_complementacion = lacp_actual;
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("cargarComplementacion", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lacp_complementacion;
	}

	/**
	 * Metodo encargado de consultar los datos de las anotaciones a cancelar.
	 * @param adm_manager Argumento de tipo <code>DaoManager</code> que permite realizar la conexión a la base de datos.
	 * @param aac_anotacionCancelacion Argumento de tipo <code>AnotacionCancelacion</code> que contiene los criterios de búsqueda.
	 * @return Colección de <code>RegistroCalificacion</code> que contiene los datos que cumplen con los criterios de búsqueda.
	 * @throws B2BException
	 */
	public synchronized Collection<RegistroCalificacion> cargarDatosAnotacionesACancelar(
	    DAOManager adm_manager, AnotacionCancelacion aac_anotacionCancelacion
	)
	    throws B2BException
	{
		Collection<RegistroCalificacion> lcrca_registroCalificacion;

		lcrca_registroCalificacion = new ArrayList<RegistroCalificacion>();

		try
		{
			if(aac_anotacionCancelacion != null)
			{
				Map<String, Boolean> lcs_tmp;

				lcs_tmp = new HashMap<String, Boolean>();

				{
					Collection<AnotacionCancelacion> lcrc_rc;

					lcrc_rc = DaoCreator.getAnotacionCancelacionDAO(adm_manager)
							                .findAllByCirculoMatriculaByTurno(aac_anotacionCancelacion);

					if(CollectionUtils.isValidCollection(lcrc_rc))
					{
						for(AnotacionCancelacion loac_tmp : lcrc_rc)
						{
							if(loac_tmp != null)
								lcs_tmp.put(StringUtils.getString(loac_tmp.getIdAnotacion1()), Boolean.TRUE);
						}
					}
				}

				{
					String                        ls_idCirculo;
					long                          ll_idMatricula;
					Collection<GravamenPendiente> lcgp_return;

					ls_idCirculo       = aac_anotacionCancelacion.getIdCirculo();
					ll_idMatricula     = aac_anotacionCancelacion.getIdMatricula();

					lcgp_return = DaoCreator.getRegistroCalificacionDAO(adm_manager)
							                    .findGravamenPendiente(
							    ls_idCirculo, StringUtils.getString(ll_idMatricula)
							);

					if(CollectionUtils.isValidCollection(lcgp_return))
					{
						for(GravamenPendiente logp_tmp : lcgp_return)
						{
							if(logp_tmp != null)
							{
								RegistroCalificacion lorc_rctmp;

								lorc_rctmp = new RegistroCalificacion();

								lorc_rctmp.setIdAnotacion(NumericUtils.getLongWrapper((logp_tmp.getIdAnotacion())));
								lorc_rctmp.setFechaRegistro(logp_tmp.getFechaRegistro());
								lorc_rctmp.setRadicacion(logp_tmp.getRadicacion());
								lorc_rctmp.setIdEstadoAnotacion(logp_tmp.getIdEstadoAnotacion());
								lorc_rctmp.setDocumento(logp_tmp.getIdDocumento());
								lorc_rctmp.setNumero(logp_tmp.getNumero());
								lorc_rctmp.setFechaDocumentoStr(logp_tmp.getFechaDocumento());
								lorc_rctmp.setNombreOficinaOrigen(logp_tmp.getOficinaOrigen());
								lorc_rctmp.setCodActo(logp_tmp.getCodigoActo());
								lorc_rctmp.setNombreActo(logp_tmp.getDescripcionActo());
								lorc_rctmp.setIdSolicitud(aac_anotacionCancelacion.getIdSolicitud());
								lorc_rctmp.setIdCirculo(ls_idCirculo);
								lorc_rctmp.setIdMatricula(NumericUtils.getLongWrapper(ll_idMatricula));
								lorc_rctmp.setRevision(
								    BooleanUtils.getBooleanValue(
								        lcs_tmp.get(NumericUtils.getLongWrapper(logp_tmp.getIdAnotacion()).toString())
								    )
								);

								lcrca_registroCalificacion.add(lorc_rctmp);
							}
						}
					}
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("detalleIntervinientes", lb2be_e);

			throw lb2be_e;
		}

		if(lcrca_registroCalificacion.isEmpty())
			lcrca_registroCalificacion = null;

		return lcrca_registroCalificacion;
	}

	/**
	 * Metodo encargado de consultar los datos de las anotaciones a cancelar.
	 * @param aac_anotacionCancelacion Argumento de tipo <code>AnotacionCancelacion</code> que contiene los criterios de búsqueda.
	 * @return Colección de <code>RegistroCalificacion</code> que contiene los datos que cumplen con los criterios de búsqueda.
	 * @throws B2BException
	 */
	public synchronized Collection<RegistroCalificacion> cargarDatosAnotacionesACancelar(
	    AnotacionCancelacion aac_anotacionCancelacion
	)
	    throws B2BException
	{
		Collection<RegistroCalificacion> lcrc_registroCalificacion;
		DAOManager                       ldm_manager;

		lcrc_registroCalificacion     = null;
		ldm_manager                   = DaoManagerFactory.getDAOManager();

		try
		{
			if(aac_anotacionCancelacion != null)
			{
				TurnoHistoria lth_turnoHistoria;

				lth_turnoHistoria = DaoCreator.getTurnoHistoriaDAO(ldm_manager)
						                          .findById(
						    NumericUtils.getLongWrapper(aac_anotacionCancelacion.getIdTurnoHistoria())
						);

				if(lth_turnoHistoria != null)
				{
					aac_anotacionCancelacion.setIdTurno(lth_turnoHistoria.getIdTurno());

					lcrc_registroCalificacion = cargarDatosAnotacionesACancelar(ldm_manager, aac_anotacionCancelacion);
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("cargarDatosAnotacionesACancelar", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lcrc_registroCalificacion;
	}

	/**
	 * Método encargado de cargar los datos del área para Englobes.
	 *
	 * @param arc_data Objeto que contiene la información para realizar la consulta.
	 * @return Objeto que contiene la información del área consultada.
	 * @throws B2BException
	 */
	public synchronized AccAreaUI cargarDatosAreaEnglobes(RegistroCalificacion arc_data)
	    throws B2BException
	{
		AccAreaUI  laaui_return;
		DAOManager ldm_manager;

		laaui_return     = null;
		ldm_manager      = DaoManagerFactory.getDAOManager();

		try
		{
			if(arc_data != null)
			{
				String ls_idCirculo;
				String ls_idTurno;
				Long   ll_idMatricula;

				ls_idCirculo       = arc_data.getIdCirculo();
				ls_idTurno         = arc_data.getTurno();
				ll_idMatricula     = arc_data.getIdMatricula();

				if(
				    StringUtils.isValidString(ls_idTurno) && StringUtils.isValidString(ls_idCirculo)
					    && NumericUtils.isValidLong(ll_idMatricula)
				)
				{
					AccAreaPredio laap_areaPredio;

					laap_areaPredio = new AccAreaPredio();

					laap_areaPredio.setIdCirculo(ls_idCirculo);
					laap_areaPredio.setIdMatricula(ll_idMatricula);
					laap_areaPredio.setIdTurno(ls_idTurno);

					laap_areaPredio = DaoCreator.getAccAreaPredioDAO(ldm_manager)
							                        .findByCirculoMatriculaTurno(laap_areaPredio);

					if(laap_areaPredio != null)
					{
						String ls_idAreaPredio;

						ls_idAreaPredio = laap_areaPredio.getIdArea();

						if(StringUtils.isValidString(ls_idAreaPredio))
						{
							DetalleAreaPredio       ldap_param;
							AccDetalleAreaPredioDAO ladap_DAO;

							laaui_return     = new AccAreaUI();
							ldap_param       = new DetalleAreaPredio();
							ladap_DAO        = DaoCreator.getAccDetalleAreaPredioDAO(ldm_manager);

							ldap_param.setIdCirculo(ls_idCirculo);
							ldap_param.setIdMatricula(ll_idMatricula);
							ldap_param.setIdAreaPredio(ls_idAreaPredio);

							ldap_param.setIdTipoArea(TipoAreaCommon.TERRENO);
							laaui_return.setAreasTerreno(ladap_DAO.findAllByIdAreaPredioTipo(ldap_param));

							ldap_param.setIdTipoArea(TipoAreaCommon.CONSTRUIDA);
							laaui_return.setDetalleAreaConstruida(ladap_DAO.findByIdAreaPredioTipo(ldap_param));

							ldap_param.setIdTipoArea(TipoAreaCommon.PRIVADA);
							laaui_return.setDetalleAreaPrivada(ladap_DAO.findByIdAreaPredioTipo(ldap_param));

							laaui_return.setIdDetalleAreaPredio(ladap_DAO.findByMaxIdDetalleAreaPredio(ldap_param));
							laaui_return.setAreaPredio(laap_areaPredio);
							laaui_return.setIdTurno(ls_idTurno);
							laaui_return.setIdCirculo(ls_idCirculo);
							laaui_return.setIdMatricula(ll_idMatricula);
							laaui_return.setIdTurnoHistoria(arc_data.getIdTurnoHistoria());
						}
					}
					else
					{
						Turno lt_turno;

						lt_turno = new Turno();

						lt_turno.setIdTurno(ls_idTurno);

						lt_turno = DaoCreator.getTurnoDAO(ldm_manager).findById(lt_turno);

						if(lt_turno != null)
						{
							String ls_idSolicitud;

							ls_idSolicitud = lt_turno.getIdSolicitud();

							if(StringUtils.isValidString(ls_idSolicitud))
							{
								Collection<MatriculaSegregacion> lcms_matriculas;
								Collection<RegistroCalificacion> lcrc_matriculas;
								Long                             ll_idMatriculaMatriz;
								String                           ls_idCirculoMatriz;

								lcms_matriculas          = null;
								lcrc_matriculas          = arc_data.getAllMatriculas();
								ll_idMatriculaMatriz     = null;
								ls_idCirculoMatriz       = null;

								if(CollectionUtils.isValidCollection(lcrc_matriculas) && arc_data.isEnglobe())
								{
									boolean                        ab_accion;
									Iterator<RegistroCalificacion> lirc_matriculas;

									ab_accion           = true;
									lirc_matriculas     = lcrc_matriculas.iterator();

									while(lirc_matriculas.hasNext() && ab_accion)
									{
										RegistroCalificacion lrc_matricula;

										lrc_matricula = lirc_matriculas.next();

										if(lrc_matricula != null)
										{
											String ls_matricula;

											ls_matricula = lrc_matricula.getIdCirculo();

											if(StringUtils.isValidString(ls_matricula))
											{
												int    li_inicio;
												Long   ll_idMatriculaTemp;
												String ls_idCirculoTemp;

												li_inicio              = ls_matricula.lastIndexOf("-");
												ll_idMatriculaTemp     = NumericUtils.getLongWrapper(
													    ls_matricula.substring(li_inicio + 1, ls_matricula.length())
													);
												ls_idCirculoTemp       = ls_matricula.substring(0, li_inicio);

												if(
												    StringUtils.isValidString(ls_idCirculoTemp)
													    && NumericUtils.isValidLong(ll_idMatriculaTemp)
												)
												{
													MatriculaSegregacion lms_matriculaSegregacion;

													lms_matriculaSegregacion = new MatriculaSegregacion();

													lms_matriculaSegregacion.setIdSolicitud(ls_idSolicitud);
													lms_matriculaSegregacion.setIdCirculoMatriz(ls_idCirculoTemp);
													lms_matriculaSegregacion.setMatriculaMatriz(ll_idMatriculaTemp);

													lcms_matriculas = DaoCreator.getMatriculaSegregacionDAO(
														    ldm_manager
														).findByIdSolicitud(lms_matriculaSegregacion, false);

													if(CollectionUtils.isValidCollection(lcms_matriculas))
													{
														ab_accion                = false;
														ll_idMatriculaMatriz     = ll_idMatriculaTemp;
														ls_idCirculoMatriz       = ls_idCirculoTemp;
													}
												}
											}
										}
									}
								}
								else
								{
									ll_idMatriculaMatriz     = arc_data.getIdMatriculaMatriz();
									ls_idCirculoMatriz       = arc_data.getIdCirculoMatriz();
								}

								if(
								    StringUtils.isValidString(ls_idCirculoMatriz)
									    && NumericUtils.isValidLong(ll_idMatriculaMatriz)
								)
								{
									if(!CollectionUtils.isValidCollection(lcms_matriculas))
									{
										MatriculaSegregacion lms_matriculaSegregacion;

										lms_matriculaSegregacion = new MatriculaSegregacion();

										lms_matriculaSegregacion.setIdSolicitud(ls_idSolicitud);
										lms_matriculaSegregacion.setIdCirculoMatriz(ls_idCirculoMatriz);
										lms_matriculaSegregacion.setMatriculaMatriz(ll_idMatriculaMatriz);

										lcms_matriculas = DaoCreator.getMatriculaSegregacionDAO(ldm_manager)
												                        .findByIdSolicitud(
												    lms_matriculaSegregacion, false
												);
									}

									if(CollectionUtils.isValidCollection(lcms_matriculas))
									{
										boolean                        lb_accion;
										Iterator<MatriculaSegregacion> lims_iterator;
										MatriculaSegregacion           lms_matricula;

										lb_accion         = false;
										lims_iterator     = lcms_matriculas.iterator();
										lms_matricula     = null;

										while(lims_iterator.hasNext() && !lb_accion)
										{
											lms_matricula     = lims_iterator.next();
											lb_accion         = lms_matricula != null;
										}

										if(lms_matricula != null)
										{
											final String ls_uno;

											laaui_return     = new AccAreaUI();
											ls_uno           = "1";

											{
												Double ld_area;

												ld_area = lms_matricula.getAreaTerreno();

												if(NumericUtils.isValidDouble(ld_area))
												{
													Collection<DetalleAreaPredio> lcdap_areasTerreno;
													DetalleAreaPredio             ldap_area;

													lcdap_areasTerreno     = new ArrayList<DetalleAreaPredio>();
													ldap_area              = new DetalleAreaPredio();

													ldap_area.setIdUnidadMedida(
													    UnidadMedidaAreaCommon.METROS_CUADRADOS
													);
													ldap_area.setIdTipoArea(TipoAreaCommon.TERRENO);
													ldap_area.setIdDetalleAreaPredio(ls_uno);
													ldap_area.setArea(ld_area);

													lcdap_areasTerreno.add(ldap_area);
													laaui_return.setAreasTerreno(lcdap_areasTerreno);
												}
											}

											{
												Double ld_area;

												ld_area = lms_matricula.getAreaConstruida();

												if(NumericUtils.isValidDouble(ld_area))
												{
													DetalleAreaPredio ldap_area;

													ldap_area = new DetalleAreaPredio();

													ldap_area.setIdUnidadMedida(
													    UnidadMedidaAreaCommon.METROS_CUADRADOS
													);
													ldap_area.setIdTipoArea(TipoAreaCommon.CONSTRUIDA);
													ldap_area.setIdDetalleAreaPredio(ls_uno);
													ldap_area.setArea(ld_area);

													laaui_return.setDetalleAreaConstruida(ldap_area);
												}
											}

											{
												Double ld_area;

												ld_area = lms_matricula.getAreaPrivada();

												if(NumericUtils.isValidDouble(ld_area))
												{
													DetalleAreaPredio ldap_area;

													ldap_area = new DetalleAreaPredio();

													ldap_area.setIdUnidadMedida(
													    UnidadMedidaAreaCommon.METROS_CUADRADOS
													);
													ldap_area.setIdTipoArea(TipoAreaCommon.PRIVADA);
													ldap_area.setIdDetalleAreaPredio(ls_uno);
													ldap_area.setArea(ld_area);

													laaui_return.setDetalleAreaPrivada(ldap_area);
												}
											}

											laaui_return.setIdDetalleAreaPredio(BigInteger.ZERO);
											laaui_return.setIdTurno(ls_idTurno);
											laaui_return.setIdCirculo(ls_idCirculo);
											laaui_return.setIdMatricula(ll_idMatricula);
											laaui_return.setIdTurnoHistoria(arc_data.getIdTurnoHistoria());
										}
									}
									else
									{
										laaui_return = new AccAreaUI();

										laaui_return.setIdDetalleAreaPredio(BigInteger.ZERO);
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

			clh_LOGGER.error("cargarDatosAreaEnglobes", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return laaui_return;
	}

	/**
	 * Método encargado de cargar los datos del área para el proceo venta parcial
	 *
	 * @param arc_data Objeto que contiene los datos para realizar la consulta
	 * @return Objeto que contiene los datos consultados del área
	 * @throws B2BException
	 */
	public synchronized AccAreaUI cargarDatosAreaVenta(RegistroCalificacion arc_data)
	    throws B2BException
	{
		AccAreaUI  laaui_return;
		DAOManager ldm_manager;

		laaui_return     = null;
		ldm_manager      = DaoManagerFactory.getDAOManager();

		try
		{
			if(arc_data != null)
			{
				Collection<RegistroCalificacion> lcrc_matriculas;

				lcrc_matriculas = arc_data.getAllMatriculas();

				if(CollectionUtils.isValidCollection(lcrc_matriculas))
				{
					Constantes lc_constante;

					lc_constante = DaoCreator.getConstantesDAO(ldm_manager)
							                     .findById(ConstanteCommon.CODIGOS_ACTOS_DECLARACION_PARTE_RESTANTE);

					if(lc_constante != null)
					{
						String ls_ventaParcial;

						ls_ventaParcial = lc_constante.getCaracter();

						if(StringUtils.isValidString(ls_ventaParcial))
						{
							boolean                        lb_continuar;
							Iterator<RegistroCalificacion> lirc_matriculas;
							String                         ls_idTurno;

							lb_continuar        = true;
							lirc_matriculas     = lcrc_matriculas.iterator();
							ls_idTurno          = arc_data.getTurno();

							while(lirc_matriculas.hasNext() && lb_continuar)
							{
								RegistroCalificacion lrc_dataMatricula;

								lrc_dataMatricula = lirc_matriculas.next();

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
										    StringUtils.isValidString(ls_idTurno)
											    && StringUtils.isValidString(ls_idCirculo)
											    && NumericUtils.isValidLong(ll_idMatricula)
										)
										{
											com.bachue.snr.prosnr01.model.sdb.acc.AnotacionPredio lap_anotacion;

											lap_anotacion = new com.bachue.snr.prosnr01.model.sdb.acc.AnotacionPredio();

											lap_anotacion.setIdNaturalezaJuridica(ls_ventaParcial);
											lap_anotacion.setIdCirculo(ls_idCirculo);
											lap_anotacion.setIdMatricula(ll_idMatricula);

											lap_anotacion = DaoCreator.getAccAnotacionPredioDAO(ldm_manager)
													                      .findByCirculoMatriculaActo(lap_anotacion);

											if(lap_anotacion != null)
											{
												AccAreaPredio laap_areaPredio;

												laap_areaPredio = new AccAreaPredio();

												laap_areaPredio.setIdCirculo(ls_idCirculo);
												laap_areaPredio.setIdMatricula(ll_idMatricula);
												laap_areaPredio.setIdTurno(ls_idTurno);
												laap_areaPredio.setIdAnotacion(lap_anotacion.getIdAnotacion());

												laap_areaPredio = DaoCreator.getAccAreaPredioDAO(ldm_manager)
														                        .findByCirculoMatriculaTurnoAnotacion(
														    laap_areaPredio
														);

												if(laap_areaPredio != null)
												{
													String ls_idAreaPredio;

													ls_idAreaPredio = laap_areaPredio.getIdArea();

													if(StringUtils.isValidString(ls_idAreaPredio))
													{
														DetalleAreaPredio       ldap_param;
														AccDetalleAreaPredioDAO ladap_DAO;

														laaui_return     = new AccAreaUI();
														ldap_param       = new DetalleAreaPredio();
														ladap_DAO        = DaoCreator.getAccDetalleAreaPredioDAO(
															    ldm_manager
															);

														ldap_param.setIdCirculo(ls_idCirculo);
														ldap_param.setIdMatricula(ll_idMatricula);
														ldap_param.setIdAreaPredio(ls_idAreaPredio);

														ldap_param.setIdTipoArea(TipoAreaCommon.TERRENO);
														laaui_return.setAreasTerreno(
														    ladap_DAO.findAllByIdAreaPredioTipo(ldap_param)
														);

														ldap_param.setIdTipoArea(TipoAreaCommon.CONSTRUIDA);
														laaui_return.setDetalleAreaConstruida(
														    ladap_DAO.findByIdAreaPredioTipo(ldap_param)
														);

														ldap_param.setIdTipoArea(TipoAreaCommon.PRIVADA);
														laaui_return.setDetalleAreaPrivada(
														    ladap_DAO.findByIdAreaPredioTipo(ldap_param)
														);

														laaui_return.setIdDetalleAreaPredio(
														    ladap_DAO.findByMaxIdDetalleAreaPredio(ldap_param)
														);
														laaui_return.setAreaPredio(laap_areaPredio);
														laaui_return.setIdTurno(ls_idTurno);
														laaui_return.setIdCirculo(ls_idCirculo);
														laaui_return.setIdMatricula(ll_idMatricula);
														laaui_return.setIdTurnoHistoria(arc_data.getIdTurnoHistoria());

														lb_continuar = false;
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

			clh_LOGGER.error("cargarDatosAreaVenta", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return laaui_return;
	}

	/**
	 * Método encargado de cargar el tab Datos Básicos para el proceso de Englobes en calificación.
	 *
	 * @param arc_data Objeto que contiene el turno y las matrículas el preoceso.
	 * @return Objeto que contiene el turno y las matrículas el preoceso.
	 * @throws B2BException
	 */
	public synchronized RegistroCalificacion cargarDatosBasicosEnglobes(RegistroCalificacion arc_data)
	    throws B2BException
	{
		DAOManager ldm_manager;

		ldm_manager = DaoManagerFactory.getDAOManager();

		try
		{
			if(arc_data != null)
			{
				String ls_idTurno;

				ls_idTurno = arc_data.getTurno();

				if(StringUtils.isValidString(ls_idTurno))
				{
					AccPredioRegistro lapr_predioRegistro;

					lapr_predioRegistro = new AccPredioRegistro();

					lapr_predioRegistro.setIdTurno(ls_idTurno);

					lapr_predioRegistro = DaoCreator.getAccPredioRegistroDAO(ldm_manager)
							                            .findByIdTurno(lapr_predioRegistro, true);

					if(lapr_predioRegistro != null)
					{
						String ls_idZonaRegistral;

						ls_idZonaRegistral = lapr_predioRegistro.getIdZonaRegistral();

						if(StringUtils.isValidString(ls_idZonaRegistral))
						{
							Collection<RegistroCalificacion> lcrc_matriculas;

							lcrc_matriculas = arc_data.getAllMatriculas();

							if(CollectionUtils.isValidCollection(lcrc_matriculas))
							{
								boolean                        lb_matriculaSeleccionada;
								Iterator<RegistroCalificacion> lirc_iterator;

								lb_matriculaSeleccionada     = false;
								lirc_iterator                = lcrc_matriculas.iterator();

								while(lirc_iterator.hasNext() && !lb_matriculaSeleccionada)
								{
									RegistroCalificacion lrc_iterador;

									lrc_iterador = lirc_iterator.next();

									if(lrc_iterador != null)
									{
										String ls_matricula;

										ls_matricula = lrc_iterador.getIdCirculo();

										if(StringUtils.isValidString(ls_matricula))
										{
											int            li_inicio;
											PredioRegistro lpr_predioRegistro;

											li_inicio              = ls_matricula.lastIndexOf("-");
											lpr_predioRegistro     = new PredioRegistro();

											lpr_predioRegistro.setIdCirculo(ls_matricula.substring(0, li_inicio));
											lpr_predioRegistro.setIdMatricula(
											    NumericUtils.getLong(
											        ls_matricula.substring(li_inicio + 1, ls_matricula.length())
											    )
											);

											lpr_predioRegistro = DaoCreator.getPredioRegistroDAO(ldm_manager)
													                           .findByCirculoMatricula(
													    lpr_predioRegistro
													);

											if(lpr_predioRegistro != null)
											{
												String ls_idZonaRegistralIterador;

												ls_idZonaRegistralIterador = lpr_predioRegistro.getIdZonaRegistral();

												if(
												    StringUtils.isValidString(ls_idZonaRegistralIterador)
													    && ls_idZonaRegistralIterador.equalsIgnoreCase(
													        ls_idZonaRegistral
													    )
												)
												{
													lrc_iterador.setMatriculaSeleccionada(true);
													lb_matriculaSeleccionada = true;
												}
											}
										}
									}
								}
							}
						}

						{
							arc_data.setIdCirculo(lapr_predioRegistro.getIdCirculo());
							arc_data.setIdMatricula(lapr_predioRegistro.getIdMatricula());
							arc_data.setMatriculaSeleccionada(true);
						}
					}
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("cargarDatosBasicosEnglobes", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return arc_data;
	}

	/***
	 * Método encargado de generar la dirección de notificación
	 * @param as_idTurnoHistoria como parametro de búsqueda de la dirección
	 * @return de tipo Registro con los parametros requeridos
	 * @throws B2BException
	 */
	public synchronized Registro cargarDireccionNotificacion(String as_idTurnoHistoria)
	    throws B2BException
	{
		DAOManager ldm_manager;
		Registro   lr_return;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		lr_return       = new Registro();

		try
		{
			if(StringUtils.isValidString(as_idTurnoHistoria))
			{
				TurnoHistoria lth_turnoHistoria;

				lth_turnoHistoria = DaoCreator.getTurnoHistoriaDAO(ldm_manager)
						                          .findById(NumericUtils.getLongWrapper(as_idTurnoHistoria));

				if(lth_turnoHistoria != null)
				{
					Solicitud ls_solicitud;

					ls_solicitud = DaoCreator.getSolicitudDAO(ldm_manager).findById(lth_turnoHistoria.getIdSolicitud());

					if(ls_solicitud != null)
					{
						String ls_medioNotificacion;

						ls_medioNotificacion = ls_solicitud.getIdAutorizacionNotificacion();

						if(
						    StringUtils.isValidString(ls_medioNotificacion)
							    && (ls_medioNotificacion.equalsIgnoreCase(
							        MedioNotificarCommon.DIRECCION_CORRESPONDENCIA
							    ) || ls_medioNotificacion.equalsIgnoreCase(MedioNotificarCommon.DIRECCION_RESIDENCIA))
						)
						{
							long ll_idEtapa;
							ll_idEtapa = NumericUtils.getLong(lth_turnoHistoria.getIdEtapa());

							if(ll_idEtapa == EtapaCommon.ID_ETAPA_CALIFICACION)
							{
								Documento ld_documento;
								ld_documento = DaoCreator.getDocumentoDAO(ldm_manager)
										                     .findById(ls_solicitud.getIdDocumento());

								if(ld_documento != null)
								{
									OficinaOrigen loo_oficinaOrigen;
									loo_oficinaOrigen = DaoCreator.getOficinaOrigenDAO(ldm_manager)
											                          .findById(
											    ld_documento.getIdOficinaOrigen(), ld_documento.getVersion()
											);

									if(loo_oficinaOrigen != null)
									{
										String ls_notificarCorrespondencia;
										ls_notificarCorrespondencia = loo_oficinaOrigen.getNotificarCorrespondencia();

										if(
										    StringUtils.isValidString(ls_notificarCorrespondencia)
											    && ls_notificarCorrespondencia.equalsIgnoreCase("S")
										)
										{
											PersonaTelefono  lpt_telefono;
											PersonaDireccion lp_personaDireccion;
											lp_personaDireccion     = new PersonaDireccion();
											lpt_telefono            = new PersonaTelefono();
											lpt_telefono.setTelefono(loo_oficinaOrigen.getTelefono());
											lr_return.setTelefonoFijo(lpt_telefono);
											lp_personaDireccion.setComplementoDireccion(
											    loo_oficinaOrigen.getDireccion()
											);
											lp_personaDireccion.setTipoDireccion(EstadoCommon.C);
											lp_personaDireccion.setDireccion(loo_oficinaOrigen.getDireccion());
											lr_return.setDireccionCorrespondencia(lp_personaDireccion);
										}
										else
										{
											lr_return.setDireccionCorrespondencia(
											    DaoCreator.getPersonaDireccionDAO(ldm_manager)
												              .findById(
												        ls_solicitud.getIdPersona(), ls_solicitud.getIdDireccion()
												    )
											);

											{
												PersonaTelefono          lpt_telefono;
												PersonaCorreoElectronico lpce_correoElectronico;
												String                   ls_idPersona;
												int                      ls_idUnico;

												ls_idPersona               = ls_solicitud.getIdPersona();
												lpt_telefono               = new PersonaTelefono();
												lpce_correoElectronico     = new PersonaCorreoElectronico();
												lpt_telefono.setIdPersona(ls_idPersona);
												lpt_telefono.setTipoTelefono("F");

												ls_idUnico = DaoCreator.getPersonaTelefonoDAO(ldm_manager)
														                   .findMaxIdTelefonoByTipoTel(lpt_telefono);

												lpt_telefono.setIdTelefono(StringUtils.getString(ls_idUnico));
												lpt_telefono = DaoCreator.getPersonaTelefonoDAO(ldm_manager)
														                     .findById(lpt_telefono);

												if(lpt_telefono != null)
													lr_return.setTelefonoFijo(lpt_telefono);
												else
													lpt_telefono = new PersonaTelefono();

												lpt_telefono.setIdPersona(ls_idPersona);
												lpt_telefono.setTipoTelefono("M");

												ls_idUnico = DaoCreator.getPersonaTelefonoDAO(ldm_manager)
														                   .findMaxIdTelefonoByTipoTel(lpt_telefono);

												lpt_telefono.setIdTelefono(StringUtils.getString(ls_idUnico));
												lpt_telefono = DaoCreator.getPersonaTelefonoDAO(ldm_manager)
														                     .findById(lpt_telefono);

												if(lpt_telefono != null)
													lr_return.setTelefonoMovil(lpt_telefono);

												lpce_correoElectronico.setIdPersona(ls_idPersona);

												ls_idUnico = DaoCreator.getPersonaCorreoElectronicoDAO(ldm_manager)
														                   .findIdCorreoElectronico(
														    lpce_correoElectronico
														);
												lpce_correoElectronico.setIdCorreoElectronico(
												    StringUtils.getString(ls_idUnico)
												);

												lpce_correoElectronico = DaoCreator.getPersonaCorreoElectronicoDAO(
													    ldm_manager
													).findById(lpce_correoElectronico);

												if(lpce_correoElectronico != null)
													lr_return.setPersonaCorreoElectronico(lpce_correoElectronico);
											}
										}
									}
								}
							}
							else
							{
								lr_return.setDireccionCorrespondencia(
								    DaoCreator.getPersonaDireccionDAO(ldm_manager)
									              .findById(ls_solicitud.getIdPersona(), ls_solicitud.getIdDireccion())
								);

								{
									PersonaTelefono          lpt_telefono;
									PersonaCorreoElectronico lpce_correoElectronico;
									String                   ls_idPersona;
									int                      ls_idUnico;

									ls_idPersona               = ls_solicitud.getIdPersona();
									lpt_telefono               = new PersonaTelefono();
									lpce_correoElectronico     = new PersonaCorreoElectronico();
									lpt_telefono.setIdPersona(ls_idPersona);
									lpt_telefono.setTipoTelefono("F");

									ls_idUnico = DaoCreator.getPersonaTelefonoDAO(ldm_manager)
											                   .findMaxIdTelefonoByTipoTel(lpt_telefono);

									lpt_telefono.setIdTelefono(StringUtils.getString(ls_idUnico));
									lpt_telefono = DaoCreator.getPersonaTelefonoDAO(ldm_manager).findById(lpt_telefono);

									if(lpt_telefono != null)
										lr_return.setTelefonoFijo(lpt_telefono);
									else
										lpt_telefono = new PersonaTelefono();

									lpt_telefono.setIdPersona(ls_idPersona);
									lpt_telefono.setTipoTelefono("M");

									ls_idUnico = DaoCreator.getPersonaTelefonoDAO(ldm_manager)
											                   .findMaxIdTelefonoByTipoTel(lpt_telefono);

									lpt_telefono.setIdTelefono(StringUtils.getString(ls_idUnico));
									lpt_telefono = DaoCreator.getPersonaTelefonoDAO(ldm_manager).findById(lpt_telefono);

									if(lpt_telefono != null)
										lr_return.setTelefonoMovil(lpt_telefono);

									lpce_correoElectronico.setIdPersona(ls_idPersona);

									ls_idUnico = DaoCreator.getPersonaCorreoElectronicoDAO(ldm_manager)
											                   .findIdCorreoElectronico(lpce_correoElectronico);
									lpce_correoElectronico.setIdCorreoElectronico(StringUtils.getString(ls_idUnico));

									lpce_correoElectronico = DaoCreator.getPersonaCorreoElectronicoDAO(ldm_manager)
											                               .findById(lpce_correoElectronico);

									if(lpce_correoElectronico != null)
										lr_return.setPersonaCorreoElectronico(lpce_correoElectronico);
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

			clh_LOGGER.error("cargarDireccionNotificacion", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lr_return;
	}

	/**
	 * Método para poder cargar una colección de linderos
	 *
	 * @param lc_anotaciones
	 *            Coleccion de anotaciones para poder extraer la informacion para
	 *            los linderos
	 * @return
	 * @throws B2BException
	 */
	public synchronized LinderoRegistroCalificacion cargarLinderos(
	    Collection<com.bachue.snr.prosnr01.model.sdb.acc.AnotacionPredio> lc_anotaciones
	)
	    throws B2BException
	{
		DAOManager                  ldm_manager;
		LinderoRegistroCalificacion llrc_linderoRegistroCalificacion;

		ldm_manager                          = DaoManagerFactory.getDAOManager();
		llrc_linderoRegistroCalificacion     = null;

		try
		{
			if(CollectionUtils.isValidCollection(lc_anotaciones))
			{
				com.bachue.snr.prosnr01.dao.acc.AnotacionPredioDAO                lapd_anotacionPredioDAO;
				Collection<String>                                                lcs_linderoGrande;
				Collection<AccLinderoPredio>                                      lclp_linderos;
				Collection<com.bachue.snr.prosnr01.model.sdb.acc.AnotacionPredio> lc_anotacionesPredio;

				lapd_anotacionPredioDAO              = DaoCreator.getAccAnotacionPredioDAO(ldm_manager);
				llrc_linderoRegistroCalificacion     = new LinderoRegistroCalificacion();
				lcs_linderoGrande                    = new ArrayList<String>();
				lclp_linderos                        = new ArrayList<AccLinderoPredio>();
				lc_anotacionesPredio                 = null;

				for(com.bachue.snr.prosnr01.model.sdb.acc.AnotacionPredio lap_iterador : lc_anotaciones)
				{
					lc_anotacionesPredio = lapd_anotacionPredioDAO.findByTurnoHistoriaCirculoMatricula(lap_iterador);

					AccLinderoPredio llp_linderoPredio;
					long             ll_idTurnoHistoria;
					Long             lL_idTurnoHistoriaL;
					TurnoHistoria    lt_turnoHistoria;
					TurnoHistoriaDAO lth_DAO;
					String           ls_idTurno;
					String           ls_observaciones;

					llp_linderoPredio       = new AccLinderoPredio();
					ll_idTurnoHistoria      = lap_iterador.getIdTurnoHistoria();
					lL_idTurnoHistoriaL     = NumericUtils.getLongWrapper(ll_idTurnoHistoria);
					lt_turnoHistoria        = new TurnoHistoria();
					lth_DAO                 = DaoCreator.getTurnoHistoriaDAO(ldm_manager);
					ls_idTurno              = null;
					ls_observaciones        = null;

					llp_linderoPredio.setIdCirculo(lap_iterador.getIdCirculo());
					llp_linderoPredio.setIdMatricula(lap_iterador.getIdMatricula());
					llp_linderoPredio.setIdTurnoHistoria(lL_idTurnoHistoriaL);
					lt_turnoHistoria.setIdTurnoHistoria(lL_idTurnoHistoriaL);

					lt_turnoHistoria = lth_DAO.findById(lt_turnoHistoria);

					if(lt_turnoHistoria != null)
					{
						ls_idTurno           = lt_turnoHistoria.getIdTurno();
						ls_observaciones     = lt_turnoHistoria.getObservacionesNoTramite();

						if(StringUtils.isValidString(ls_idTurno))
							llp_linderoPredio.setIdTurno(ls_idTurno);

						if(StringUtils.isValidString(ls_observaciones))
							llrc_linderoRegistroCalificacion.setObservaciones(ls_observaciones);
					}

					lclp_linderos.add(llp_linderoPredio);

					if(CollectionUtils.isValidCollection(lc_anotacionesPredio))
					{
						for(com.bachue.snr.prosnr01.model.sdb.acc.AnotacionPredio lap_anotacion : lc_anotacionesPredio)
						{
							String ls_estadoAnotacion;
							String ls_idTurnoAnotacion;

							ls_estadoAnotacion      = lap_anotacion.getIdEstadoAnotacion();
							ls_idTurnoAnotacion     = lap_anotacion.getIdTurno();

							if(
							    StringUtils.isValidString(ls_estadoAnotacion)
								    && ls_estadoAnotacion.equalsIgnoreCase(EstadoCommon.V)
							)
							{
								if(
								    StringUtils.isValidString(ls_idTurnoAnotacion)
									    && StringUtils.isValidString(ls_idTurno)
									    && ls_idTurno.equalsIgnoreCase(ls_idTurnoAnotacion)
								)
								{
									String ls_idNaturalezaJuridica;
									long   ll_version;

									ls_idNaturalezaJuridica     = lap_anotacion.getIdNaturalezaJuridica();
									ll_version                  = lap_anotacion.getVersion();

									if(StringUtils.isValidString(ls_idNaturalezaJuridica) && (ll_version > 0))
									{
										Constantes    lc_constantes;
										ConstantesDAO lcd_constantesDAO;

										lc_constantes         = new Constantes();
										lcd_constantesDAO     = DaoCreator.getConstantesDAO(ldm_manager);

										lc_constantes.setIdConstante(ConstanteCommon.CODIGOS_ACTOS_GENERAR_LINDEROS);

										lc_constantes = lcd_constantesDAO.findById(lc_constantes);

										if(lc_constantes != null)
										{
											String ls_caracter;

											ls_caracter = lc_constantes.getCaracter();

											if(StringUtils.isValidString(ls_caracter))
											{
												String[] las_codigos;
												boolean  lb_continuar;

												las_codigos      = ls_caracter.split(",");
												lb_continuar     = false;

												for(String codigo : las_codigos)
												{
													if(codigo.equalsIgnoreCase(ls_idNaturalezaJuridica))
														lb_continuar = true;
												}

												if(lb_continuar)
												{
													NaturalezaJuridica    lnj_naturalezaJuridica;
													NaturalezaJuridicaDAO lnjd_naturalezaJuridicaDAO;

													lnj_naturalezaJuridica         = new NaturalezaJuridica();
													lnjd_naturalezaJuridicaDAO     = DaoCreator.getNaturalezaJuridicaDAO(
														    ldm_manager
														);

													lnj_naturalezaJuridica.setIdNaturalezaJuridica(
													    ls_idNaturalezaJuridica
													);
													lnj_naturalezaJuridica.setVersion(ll_version);

													lnj_naturalezaJuridica = lnjd_naturalezaJuridicaDAO.findById(
														    lnj_naturalezaJuridica
														);

													if(lnj_naturalezaJuridica != null)
													{
														OficinaOrigen loo_oficinaOrigen;
														String        ls_tipoDocumentoNombre;
														String        ls_oficinaOrigenNombre;

														loo_oficinaOrigen          = null;
														ls_tipoDocumentoNombre     = null;
														ls_oficinaOrigenNombre     = null;

														String       ls_idDocumento;
														DocumentoDAO ldd_documentoDAO;
														Documento    ld_documento;

														ls_idDocumento             = lap_anotacion.getIdDocumento();
														ldd_documentoDAO           = DaoCreator.getDocumentoDAO(
															    ldm_manager
															);
														ld_documento               = null;

														if(StringUtils.isValidString(ls_idDocumento))
														{
															ld_documento = new Documento();
															ld_documento.setIdDocumento(ls_idDocumento);
															ld_documento = ldd_documentoDAO.findById(ld_documento);
														}

														if(ld_documento != null)
														{
															String     ls_idTipoDocumento;
															String     ls_idOficinaOrigen;
															BigDecimal lbd_version;

															ls_idTipoDocumento     = ld_documento.getIdTipoDocumento();
															ls_idOficinaOrigen     = ld_documento.getIdOficinaOrigen();
															lbd_version            = ld_documento.getVersion();

															if(StringUtils.isValidString(ls_idTipoDocumento))
															{
																TipoDocumentoPublico    ltdp_tipoDocumentoPublico;
																TipoDocumentoPublicoDAO ltdpd_tipoDocumentoPublicoDAO;

																ltdp_tipoDocumentoPublico         = new TipoDocumentoPublico();
																ltdpd_tipoDocumentoPublicoDAO     = DaoCreator
																		.getTipoDocumentoPublicoDAO(ldm_manager);

																ltdp_tipoDocumentoPublico.setIdTipoDocumento(
																    ls_idTipoDocumento
																);

																ltdp_tipoDocumentoPublico = ltdpd_tipoDocumentoPublicoDAO
																		.findById(ltdp_tipoDocumentoPublico);

																if(ltdp_tipoDocumentoPublico != null)
																	ls_tipoDocumentoNombre = ltdp_tipoDocumentoPublico
																			.getNombre();
															}

															if(
															    StringUtils.isValidString(ls_idOficinaOrigen)
																    && (lbd_version != null)
															)
															{
																OficinaOrigenDAO lood_oficinaOrigenDAO;

																loo_oficinaOrigen         = new OficinaOrigen();
																lood_oficinaOrigenDAO     = DaoCreator
																		.getOficinaOrigenDAO(ldm_manager);

																loo_oficinaOrigen.setIdOficinaOrigen(
																    ls_idOficinaOrigen
																);

																loo_oficinaOrigen.setVersion(lbd_version);

																loo_oficinaOrigen = lood_oficinaOrigenDAO.findById(
																	    loo_oficinaOrigen
																	);

																if(loo_oficinaOrigen != null)
																	ls_oficinaOrigenNombre = loo_oficinaOrigen.getNombre();
															}
														}

														StringBuilder ls_linderoFinal;
														ls_linderoFinal = new StringBuilder();

														ls_linderoFinal.append("CONTENIDOS EN ");

														if(StringUtils.isValidString(ls_tipoDocumentoNombre))
															ls_linderoFinal.append(ls_tipoDocumentoNombre + " ");
														else
															ls_linderoFinal.append("SIN INFORMACIÓN ");

														String ls_numeroDocumento;
														ls_numeroDocumento = ld_documento.getNumero();

														if(StringUtils.isValidString(ls_numeroDocumento))
															ls_linderoFinal.append(ls_numeroDocumento + " ");
														else
															ls_linderoFinal.append("SIN INFORMACIÓN ");

														ls_linderoFinal.append("DE FECHA ");

														Date ld_fechaDocumento;
														ld_fechaDocumento = ld_documento.getFechaDocumento();

														if(ld_fechaDocumento != null)
														{
															Calendar lc_calendar;
															int      li_dia;
															int      li_mes;
															String   ls_mes;
															int      li_anio;

															lc_calendar = Calendar.getInstance();
															lc_calendar.setTime(ld_fechaDocumento);

															li_dia      = lc_calendar.get(Calendar.DAY_OF_MONTH);
															li_mes      = lc_calendar.get(Calendar.MONTH);
															ls_mes      = DateUtils.getMes(li_mes + 1);
															li_anio     = lc_calendar.get(Calendar.YEAR);

															if(
															    (li_dia > 0) && (li_anio > 0)
																    && StringUtils.isValidString(ls_mes)
															)
																ls_linderoFinal.append(
																    li_dia + " DE " + ls_mes + " DE " + li_anio + " "
																);
														}
														else
															ls_linderoFinal.append("SIN INFORMACIÓN ");

														ls_linderoFinal.append("DE ");

														if(loo_oficinaOrigen != null)
														{
															if(StringUtils.isValidString(ls_oficinaOrigenNombre))
																ls_linderoFinal.append(ls_oficinaOrigenNombre + " DE ");
															else
																ls_linderoFinal.append(
																    IdentificadoresCommon.SIN_INFORMACION
																);

															String ls_idOficinaOrigenPais;
															String ls_idOficinaOrigenDepartamento;
															String ls_idOficinaOrigenMunicipio;

															ls_idOficinaOrigenPais             = loo_oficinaOrigen
																	.getIdPais();
															ls_idOficinaOrigenDepartamento     = loo_oficinaOrigen
																	.getIdDepartamento();
															ls_idOficinaOrigenMunicipio        = loo_oficinaOrigen
																	.getIdMunicipio();

															if(
															    StringUtils.isValidString(ls_idOficinaOrigenPais)
																    && StringUtils.isValidString(
																        ls_idOficinaOrigenDepartamento
																    )
																    && StringUtils.isValidString(
																        ls_idOficinaOrigenMunicipio
																    )
															)
															{
																Municipio    lm_municipio;
																MunicipioDAO lmd_municipioDAO;

																lm_municipio         = new Municipio();
																lmd_municipioDAO     = DaoCreator.getMunicipioDAO(
																	    ldm_manager
																	);

																lm_municipio.setIdPais(ls_idOficinaOrigenPais);
																lm_municipio.setIdDepartamento(
																    ls_idOficinaOrigenDepartamento
																);
																lm_municipio.setIdMunicipio(
																    ls_idOficinaOrigenMunicipio
																);

																lm_municipio = lmd_municipioDAO.findById(lm_municipio);

																if(lm_municipio != null)
																{
																	String ls_municipioNombre;
																	ls_municipioNombre = lm_municipio.getNombre();

																	if(StringUtils.isValidString(ls_municipioNombre))
																		ls_linderoFinal.append(
																		    ls_municipioNombre + " "
																		);
																	else
																		ls_linderoFinal.append(
																		    IdentificadoresCommon.SIN_INFORMACION
																		);
																}
																else
																	ls_linderoFinal.append(
																	    IdentificadoresCommon.SIN_INFORMACION
																	);
															}
															else
																ls_linderoFinal.append(
																    IdentificadoresCommon.SIN_INFORMACION
																);
														}
														else
															ls_linderoFinal.append(
															    IdentificadoresCommon.SIN_INFORMACION
															);

														ls_linderoFinal.append(
														    "(ART.11 DEL DECRETO 1711 DE JULIO 6/1984). "
														);

														lcs_linderoGrande.add(ls_linderoFinal.toString());
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

				StringBuilder lsb_lindero;
				lsb_lindero = new StringBuilder();

				if(CollectionUtils.isValidCollection(lcs_linderoGrande))
				{
					for(String ls_lindero : lcs_linderoGrande)
						lsb_lindero.append(ls_lindero + "\n");
				}

				llrc_linderoRegistroCalificacion.setLinderoPredios(lclp_linderos);
				llrc_linderoRegistroCalificacion.setLindero(lsb_lindero.toString());
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("cargarLinderos", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return llrc_linderoRegistroCalificacion;
	}

	/**
	 * Método para cargar linderos de digitador masivo
	 *
	 * @param lc_anotaciones
	 *            Coleccion de anotaciones para extraer la información para cargar
	 *            el lindero
	 * @return
	 * @throws B2BException
	 */
	public synchronized LinderoRegistroCalificacion cargarLinderosDigitadorMasivo(
	    Collection<com.bachue.snr.prosnr01.model.sdb.acc.AnotacionPredio> lc_anotaciones
	)
	    throws B2BException
	{
		DAOManager                  ldm_manager;
		LinderoRegistroCalificacion llrc_linderoRegistroCalificacion;

		ldm_manager                          = DaoManagerFactory.getDAOManager();
		llrc_linderoRegistroCalificacion     = new LinderoRegistroCalificacion();

		try
		{
			if(CollectionUtils.isValidCollection(lc_anotaciones))
			{
				AccLinderoPredioDAO                                             lalp_DAO;
				Iterator<com.bachue.snr.prosnr01.model.sdb.acc.AnotacionPredio> lia_ia;

				lalp_DAO     = DaoCreator.getAccLinderoPredioDAO(ldm_manager);
				lia_ia       = lc_anotaciones.iterator();

				if(lia_ia.hasNext())
				{
					com.bachue.snr.prosnr01.model.sdb.acc.AnotacionPredio laap_aap;

					laap_aap = lia_ia.next();

					if(laap_aap != null)
					{
						AccLinderoPredio lalp_alp;
						String           ls_idLinderoPredio;
						String           ls_idCirculo;
						Long             ll_idMatricula;
						String           ls_idTurno;

						ls_idCirculo       = laap_aap.getIdCirculo();
						ll_idMatricula     = laap_aap.getIdMatricula();
						ls_idTurno         = laap_aap.getIdTurno();

						lalp_alp = new AccLinderoPredio();
						lalp_alp.setIdCirculo(ls_idCirculo);
						lalp_alp.setIdMatricula(ll_idMatricula);
						lalp_alp.setIdTurno(ls_idTurno);

						ls_idLinderoPredio = lalp_DAO.findMaxIdLindero(lalp_alp, false);

						if(StringUtils.isValidString(ls_idLinderoPredio))
						{
							lalp_alp.setIdLinderoPredio(ls_idLinderoPredio);

							lalp_alp = lalp_DAO.findById(lalp_alp);
						}
						else if(!StringUtils.isValidString(ls_idTurno))
						{
							ls_idLinderoPredio = lalp_DAO.findMaxIdLindero(lalp_alp, true);

							lalp_alp.setIdLinderoPredio(ls_idLinderoPredio);

							lalp_alp = lalp_DAO.findById(lalp_alp);
						}

						if(lalp_alp != null)
						{
							String lsb_lindero;

							lsb_lindero = lalp_alp.getLindero();

							if(StringUtils.isValidString(lsb_lindero))
								llrc_linderoRegistroCalificacion.setLindero(lsb_lindero);
						}

						{
							TurnoHistoria lth_turnoHistoria;

							lth_turnoHistoria = new TurnoHistoria();

							lth_turnoHistoria.setIdCirculo(ls_idCirculo);
							lth_turnoHistoria.setIdTurnoHistoria(
							    NumericUtils.getLongWrapper(laap_aap.getIdTurnoHistoria())
							);

							lth_turnoHistoria = DaoCreator.getTurnoHistoriaDAO(ldm_manager).findById(lth_turnoHistoria);

							if(lth_turnoHistoria != null)
								llrc_linderoRegistroCalificacion.setObservaciones(
								    lth_turnoHistoria.getObservacionesNoTramite()
								);
						}
					}
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("cargarLinderosDigitadorMasivo", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return llrc_linderoRegistroCalificacion;
	}

	/**
	 * Método encargado de cargar la oficina origen en el apartado de medida cautelar
	 * @param as_idTurno con el turno actual de la búsqueda
	 * @return tipo Oficina Origen con los parametros resultantes de la búsqueda
	 * @throws B2BException
	 */
	public synchronized OficinaOrigen cargarOficinaOrigenMedidaCautelar(String as_idTurno)
	    throws B2BException
	{
		DAOManager    ldm_manager;
		OficinaOrigen loo_return;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		loo_return      = null;

		try
		{
			if(!StringUtils.isValidString(as_idTurno))
				throw new B2BException(ErrorKeys.ERROR_INTERNO_SISTEMA);

			Turno lt_turno;
			lt_turno = new Turno();

			lt_turno.setIdTurno(as_idTurno);

			lt_turno = DaoCreator.getTurnoDAO(ldm_manager).findById(lt_turno);

			if(lt_turno == null)
				throw new B2BException(ErrorKeys.ERROR_SIN_ID_TURNO);

			Solicitud ls_solicitud;
			ls_solicitud = new Solicitud();

			ls_solicitud.setIdSolicitud(lt_turno.getIdSolicitud());

			ls_solicitud = DaoCreator.getSolicitudDAO(ldm_manager).findById(ls_solicitud);

			if(ls_solicitud == null)
				throw new B2BException(ErrorKeys.NO_SE_ENCONTRO_SOLICITUD);

			Documento ld_documento;
			ld_documento = new Documento();

			ld_documento.setIdDocumento(ls_solicitud.getIdDocumento());
			ld_documento.setVersionDocumento(ls_solicitud.getVersionDocumento());

			ld_documento = DaoCreator.getDocumentoDAO(ldm_manager).findByIdDocumentoVersion(ld_documento);

			if(ld_documento == null)
				throw new B2BException(ErrorKeys.ERROR_SIN_ID_DOCUMENTO);

			loo_return = new OficinaOrigen();

			loo_return.setIdOficinaOrigen(ld_documento.getIdOficinaOrigen());
			loo_return.setVersion(ld_documento.getVersion());

			loo_return = DaoCreator.getOficinaOrigenDAO(ldm_manager).findById(loo_return);
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("cargarOficinaOrigenMedidaCautelar", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return loo_return;
	}

	/**
	 * Método encargado de validar y cargar información para el proceso venta parcial.
	 *
	 * @param arc_data Objeto que contiene la información para realizar la consulta.
	 * @return Objeto que contiene la información consultada.
	 * @throws B2BException
	 */
	public synchronized RegistroCalificacion cargarVentaParcial(RegistroCalificacion arc_data)
	    throws B2BException
	{
		if(arc_data != null)
		{
			DAOManager ldm_manager;

			ldm_manager = DaoManagerFactory.getDAOManager();

			try
			{
				if(!arc_data.isCementerio())
				{
					Long   ll_idMatricula;
					String ls_idCirculo;

					ll_idMatricula     = arc_data.getIdMatricula();
					ls_idCirculo       = arc_data.getIdCirculo();

					if(StringUtils.isValidString(ls_idCirculo) && NumericUtils.isValidLong(ll_idMatricula))
					{
						com.bachue.snr.prosnr01.model.sdb.acc.AnotacionPredio             lap_anotacion;
						Collection<com.bachue.snr.prosnr01.model.sdb.acc.AnotacionPredio> lcap_anotaciones;

						lap_anotacion = new com.bachue.snr.prosnr01.model.sdb.acc.AnotacionPredio();

						lap_anotacion.setIdCirculo(ls_idCirculo);
						lap_anotacion.setIdMatricula(ll_idMatricula);

						lcap_anotaciones = DaoCreator.getAccAnotacionPredioDAO(ldm_manager)
								                         .findByCirculoMatricula(lap_anotacion);

						arc_data.setSalvarVentaParcial(CollectionUtils.isValidCollection(lcap_anotaciones));
					}
				}
			}
			catch(B2BException lb2be_e)
			{
				ldm_manager.setRollbackOnly();

				clh_LOGGER.error("cargarVentaParcial", lb2be_e);

				throw lb2be_e;
			}
			finally
			{
				ldm_manager.close();
			}
		}

		return arc_data;
	}

	/**
	 * Método para contruir complementacion por medio de las anotaciones
	 *
	 * @param lc_anotaciones
	 *            Colección de anotaciones para estraer la información para las
	 *            complementaciones
	 * @return
	 * @throws B2BException
	 */
	public synchronized Collection<ComplementacionAnotacion> construirComplementacionAnotaciones(
	    Collection<AnotacionPredio> lc_anotaciones
	)
	    throws B2BException
	{
		DAOManager ldm_manager;

		Collection<ComplementacionAnotacion> lc_complementacionAnotacionReturn;

		ldm_manager     = DaoManagerFactory.getDAOManager();

		lc_complementacionAnotacionReturn = null;

		try
		{
			if(CollectionUtils.isValidCollection(lc_anotaciones))
			{
				AnotacionPredioDAO          lapd_anotacionPredioDAO;
				Collection<AnotacionPredio> lc_anotacionesPredio;

				lapd_anotacionPredioDAO               = DaoCreator.getAnotacionPredioDAO(ldm_manager);
				lc_complementacionAnotacionReturn     = new ArrayList<ComplementacionAnotacion>();
				lc_anotacionesPredio                  = null;

				for(AnotacionPredio lap_iterador : lc_anotaciones)
				{
					lc_anotacionesPredio = lapd_anotacionPredioDAO.findByCirculoMatricula(lap_iterador, false);

					if(CollectionUtils.isValidCollection(lc_anotacionesPredio))
					{
						for(AnotacionPredio lap_anotacion : lc_anotacionesPredio)
						{
							String ls_estadoAnotacion;
							ls_estadoAnotacion = lap_anotacion.getIdEstadoAnotacion();

							if(StringUtils.isValidString(ls_estadoAnotacion))
							{
								EstadoAnotacion lea_estadoTmp;
								lea_estadoTmp = new EstadoAnotacion();
								lea_estadoTmp.setIdEstadoAnotacion(ls_estadoAnotacion);

								lea_estadoTmp = DaoCreator.getEstadoAnotacionDAO(ldm_manager).findById(lea_estadoTmp);

								if(lea_estadoTmp != null)
									lap_anotacion.setEstadoAnotacion(lea_estadoTmp.getNombre());
							}

							if(
							    StringUtils.isValidString(ls_estadoAnotacion)
								    && ls_estadoAnotacion.equalsIgnoreCase(EstadoCommon.V)
							)
							{
								String ls_idNaturalezaJuridica;
								long   ll_version;

								ls_idNaturalezaJuridica     = lap_anotacion.getIdNaturalezaJuridica();
								ll_version                  = lap_anotacion.getVersion();

								if(StringUtils.isValidString(ls_idNaturalezaJuridica) && (ll_version > 0))
								{
									NaturalezaJuridica    lnj_naturalezaJuridica;
									NaturalezaJuridicaDAO lnjd_naturalezaJuridicaDAO;

									lnj_naturalezaJuridica         = new NaturalezaJuridica();
									lnjd_naturalezaJuridicaDAO     = DaoCreator.getNaturalezaJuridicaDAO(ldm_manager);

									lnj_naturalezaJuridica.setIdNaturalezaJuridica(ls_idNaturalezaJuridica);
									lnj_naturalezaJuridica.setVersion(ll_version);

									lnj_naturalezaJuridica = lnjd_naturalezaJuridicaDAO.findById(
										    lnj_naturalezaJuridica
										);

									if(lnj_naturalezaJuridica != null)
									{
										String ls_construirComplementacion;
										ls_construirComplementacion = lnj_naturalezaJuridica.getConstruirComplementacion();

										if(
										    StringUtils.isValidString(ls_construirComplementacion)
											    && ls_construirComplementacion.equalsIgnoreCase(EstadoCommon.S)
										)
										{
											ComplementacionAnotacion lca_complementacionAnotacion;
											OficinaOrigen            loo_oficinaOrigen;
											String                   ls_tipoDocumentoNombre;

											lca_complementacionAnotacion     = new ComplementacionAnotacion();
											loo_oficinaOrigen                = null;
											ls_tipoDocumentoNombre           = null;

											lca_complementacionAnotacion.setAnotacionPredio(lap_anotacion);
											lca_complementacionAnotacion.setNaturalezaJuridica(lnj_naturalezaJuridica);

											String       ls_idDocumento;
											DocumentoDAO ldd_documentoDAO;
											Documento    ld_documento;

											ls_idDocumento       = lap_anotacion.getIdDocumento();
											ldd_documentoDAO     = DaoCreator.getDocumentoDAO(ldm_manager);
											ld_documento         = null;

											if(StringUtils.isValidString(ls_idDocumento))
											{
												ld_documento = new Documento();
												ld_documento.setIdDocumento(ls_idDocumento);
												ld_documento = ldd_documentoDAO.findById(ld_documento);
											}

											if(ld_documento != null)
											{
												lca_complementacionAnotacion.setDocumento(ld_documento);

												String     ls_idTipoDocumento;
												String     ls_idTipoOficina;
												String     ls_idOficinaOrigen;
												BigDecimal lbd_version;

												ls_idTipoOficina       = ld_documento.getIdTipoOficina();
												ls_idTipoDocumento     = ld_documento.getIdTipoDocumento();
												ls_idOficinaOrigen     = ld_documento.getIdOficinaOrigen();
												lbd_version            = ld_documento.getVersion();

												if(StringUtils.isValidString(ls_idTipoDocumento))
												{
													TipoDocumentoPublico    ltdp_tipoDocumentoPublico;
													TipoDocumentoPublicoDAO ltdpd_tipoDocumentoPublicoDAO;

													ltdp_tipoDocumentoPublico         = new TipoDocumentoPublico();
													ltdpd_tipoDocumentoPublicoDAO     = DaoCreator
															.getTipoDocumentoPublicoDAO(ldm_manager);

													ltdp_tipoDocumentoPublico.setIdTipoDocumento(ls_idTipoDocumento);

													ltdp_tipoDocumentoPublico = ltdpd_tipoDocumentoPublicoDAO.findById(
														    ltdp_tipoDocumentoPublico
														);

													if(ltdp_tipoDocumentoPublico != null)
													{
														ls_tipoDocumentoNombre = ltdp_tipoDocumentoPublico.getNombre();

														if(StringUtils.isValidString(ls_tipoDocumentoNombre))
															lca_complementacionAnotacion.setTipoDocumento(
															    ls_tipoDocumentoNombre
															);
													}
												}

												if(StringUtils.isValidString(ls_idTipoOficina))
												{
													TipoOficina    lto_tipoOficina;
													TipoOficinaDAO ltod_tipoOficinaDAO;

													lto_tipoOficina         = new TipoOficina();
													ltod_tipoOficinaDAO     = DaoCreator.getTipoOficinaDAO(ldm_manager);

													lto_tipoOficina.setIdTipoOficina(ls_idTipoOficina);

													lto_tipoOficina = ltod_tipoOficinaDAO.findById(lto_tipoOficina);

													if(lto_tipoOficina != null)
													{
														String ls_tipoOficinaNombre;
														ls_tipoOficinaNombre = lto_tipoOficina.getNombre();

														if(StringUtils.isValidString(ls_tipoOficinaNombre))
															lca_complementacionAnotacion.setTipoOficina(
															    ls_tipoOficinaNombre
															);
													}
												}

												if(
												    StringUtils.isValidString(ls_idOficinaOrigen)
													    && (lbd_version != null)
												)
												{
													OficinaOrigenDAO lood_oficinaOrigenDAO;

													loo_oficinaOrigen         = new OficinaOrigen();
													lood_oficinaOrigenDAO     = DaoCreator.getOficinaOrigenDAO(
														    ldm_manager
														);

													loo_oficinaOrigen.setIdOficinaOrigen(ls_idOficinaOrigen);
													loo_oficinaOrigen.setVersion(lbd_version);

													loo_oficinaOrigen = lood_oficinaOrigenDAO.findById(
														    loo_oficinaOrigen
														);

													if(loo_oficinaOrigen != null)
													{
														String ls_oficinaOrigenNombre;
														ls_oficinaOrigenNombre = loo_oficinaOrigen.getNombre();

														if(StringUtils.isValidString(ls_oficinaOrigenNombre))
															lca_complementacionAnotacion.setOficinaOrigen(
															    ls_oficinaOrigenNombre
															);
													}
												}
											}

											StringBuilder ls_complementacionFinal;
											ls_complementacionFinal = new StringBuilder();

											ls_complementacionFinal.append("EL ");

											Date ld_fechaAnotacion;
											Date ld_fechaActual;

											ld_fechaAnotacion     = lap_anotacion.getFechaRegistro();
											ld_fechaActual        = new Date();

											if(ld_fechaAnotacion != null)
											{
												Calendar lc_calendarAnotacion;
												Calendar lc_calendar;
												int      li_dia;
												int      li_mes;
												int      li_anio;
												int      li_anioActual;
												int      li_anioCalculado;
												String   ls_mes;

												lc_calendarAnotacion     = Calendar.getInstance();
												lc_calendar              = Calendar.getInstance();

												lc_calendarAnotacion.setTime(ld_fechaAnotacion);
												lc_calendar.setTime(ld_fechaActual);

												li_dia            = lc_calendarAnotacion.get(Calendar.DAY_OF_MONTH);
												li_mes            = lc_calendarAnotacion.get(Calendar.MONTH);
												ls_mes            = DateUtils.getMes(li_mes + 1);
												li_anio           = lc_calendarAnotacion.get(Calendar.YEAR);
												li_anioActual     = lc_calendar.get(Calendar.YEAR);

												li_anioCalculado = li_anioActual - li_anio;

												if(li_anioCalculado <= 20)
												{
													if(
													    (li_dia > 0) && (li_anio > 0)
														    && StringUtils.isValidString(ls_mes)
													)
														ls_complementacionFinal.append(
														    li_dia + " DE " + ls_mes + " DE " + li_anio + " "
														);

													Long   ll_idAnotacion;
													Long   ll_idMatricula;
													String ls_idCirculo;

													ll_idAnotacion     = lap_anotacion.getIdAnotacion();
													ll_idMatricula     = lap_anotacion.getIdMatricula();
													ls_idCirculo       = lap_anotacion.getIdCirculo();

													if(
													    NumericUtils.isValidLong(ll_idAnotacion)
														    && NumericUtils.isValidLong(ll_idMatricula)
														    && StringUtils.isValidString(ls_idCirculo)
													)
													{
														AnotacionPredioCiudadano             lapc_anotacionPredioCiudadano;
														AnotacionPredioCiudadanoDAO          lapcd_anotacionPredioCiudadanoDAO;
														Collection<AnotacionPredioCiudadano> lc_ciudadanos;

														lapc_anotacionPredioCiudadano         = new AnotacionPredioCiudadano();
														lapcd_anotacionPredioCiudadanoDAO     = DaoCreator
																.getAnotacionPredioCiudadanoDAO(ldm_manager);

														lapc_anotacionPredioCiudadano.setIdAnotacion(
														    NumericUtils.getLong(ll_idAnotacion)
														);
														lapc_anotacionPredioCiudadano.setIdMatricula(
														    NumericUtils.getLong(ll_idMatricula)
														);
														lapc_anotacionPredioCiudadano.setIdCirculo(ls_idCirculo);

														lc_ciudadanos = lapcd_anotacionPredioCiudadanoDAO
																.findByCirculoMatricula(lapc_anotacionPredioCiudadano);

														if(CollectionUtils.isValidCollection(lc_ciudadanos))
														{
															for(AnotacionPredioCiudadano lapc_ciudadano : lc_ciudadanos)
															{
																String ls_rolPersona;
																String ls_idPersona;

																ls_rolPersona     = lapc_ciudadano.getRolPersona();
																ls_idPersona      = lapc_ciudadano.getIdPersona();

																if(
																    StringUtils.isValidString(ls_rolPersona)
																	    && ls_rolPersona.equalsIgnoreCase(
																	        EstadoCommon.A
																	    ) && StringUtils.isValidString(ls_idPersona)
																)
																{
																	Persona    lp_persona;
																	PersonaDAO lpd_personaDAO;

																	lp_persona         = new Persona();
																	lpd_personaDAO     = DaoCreator.getPersonaDAO(
																		    ldm_manager
																		);

																	lp_persona.setIdPersona(ls_idPersona);
																	lp_persona = lpd_personaDAO.findById(lp_persona);

																	if(lp_persona != null)
																	{
																		String ls_personaNombre;
																		ls_personaNombre = lp_persona.getNombreCompleto();

																		if(StringUtils.isValidString(ls_personaNombre))
																			ls_complementacionFinal.append(
																			    ls_personaNombre + ", "
																			);
																	}
																}
															}
														}
														else
															ls_complementacionFinal.append("SIN INFORMACIÓN ");
													}

													ls_complementacionFinal.append("ADQUIRIO(ERON) POR ");

													String ls_naturalezaJuridicaNombre;
													ls_naturalezaJuridicaNombre = lnj_naturalezaJuridica.getNombre();

													if(StringUtils.isValidString(ls_naturalezaJuridicaNombre))
														ls_complementacionFinal.append(
														    ls_naturalezaJuridicaNombre + " "
														);
													else
														ls_complementacionFinal.append("SIN INFORMACIÓN ");

													ls_complementacionFinal.append("SEGÚN ");

													if(StringUtils.isValidString(ls_tipoDocumentoNombre))
														ls_complementacionFinal.append(ls_tipoDocumentoNombre + " ");
													else
														ls_complementacionFinal.append("SIN INFORMACIÓN ");

													String ls_numeroDocumento;
													ls_numeroDocumento = ld_documento.getNumero();

													if(StringUtils.isValidString(ls_numeroDocumento))
														ls_complementacionFinal.append(ls_numeroDocumento + " ");
													else
														ls_complementacionFinal.append("SIN INFORMACIÓN ");

													ls_complementacionFinal.append("DE FECHA ");

													Date ld_fechaDocumento;
													ld_fechaDocumento = ld_documento.getFechaDocumento();

													if(ld_fechaDocumento != null)
													{
														Calendar lc_calendarDoc;
														int      li_diaDoc;
														int      li_mesDoc;
														String   ls_mesDoc;
														int      li_anioDoc;

														lc_calendarDoc = Calendar.getInstance();
														lc_calendarDoc.setTime(ld_fechaDocumento);

														li_diaDoc      = lc_calendarDoc.get(Calendar.DAY_OF_MONTH);
														li_mesDoc      = lc_calendarDoc.get(Calendar.MONTH);
														ls_mesDoc      = DateUtils.getMes(li_mesDoc + 1);
														li_anioDoc     = lc_calendarDoc.get(Calendar.YEAR);

														if(
														    (li_diaDoc > 0) && (li_anioDoc > 0)
															    && StringUtils.isValidString(ls_mesDoc)
														)
															ls_complementacionFinal.append(
															    li_diaDoc + " DE " + ls_mesDoc + " DE " + li_anioDoc
															    + " "
															);
													}
													else
														ls_complementacionFinal.append("SIN INFORMACIÓN ");

													ls_complementacionFinal.append("DE ");

													if(loo_oficinaOrigen != null)
													{
														String ls_oficinaOrigenNombre;
														ls_oficinaOrigenNombre = loo_oficinaOrigen.getNombre();

														if(StringUtils.isValidString(ls_oficinaOrigenNombre))
															ls_complementacionFinal.append(
															    ls_oficinaOrigenNombre + " DE "
															);
														else
															ls_complementacionFinal.append("SIN INFORMACIÓN ");

														String ls_idOficinaOrigenPais;
														String ls_idOficinaOrigenDepartamento;
														String ls_idOficinaOrigenMunicipio;

														ls_idOficinaOrigenPais             = loo_oficinaOrigen.getIdPais();
														ls_idOficinaOrigenDepartamento     = loo_oficinaOrigen
																.getIdDepartamento();
														ls_idOficinaOrigenMunicipio        = loo_oficinaOrigen
																.getIdMunicipio();

														if(
														    StringUtils.isValidString(ls_idOficinaOrigenPais)
															    && StringUtils.isValidString(
															        ls_idOficinaOrigenDepartamento
															    )
															    && StringUtils.isValidString(
															        ls_idOficinaOrigenMunicipio
															    )
														)
														{
															Municipio    lm_municipio;
															MunicipioDAO lmd_municipioDAO;

															lm_municipio         = new Municipio();
															lmd_municipioDAO     = DaoCreator.getMunicipioDAO(
																    ldm_manager
																);

															lm_municipio.setIdPais(ls_idOficinaOrigenPais);
															lm_municipio.setIdDepartamento(
															    ls_idOficinaOrigenDepartamento
															);
															lm_municipio.setIdMunicipio(ls_idOficinaOrigenMunicipio);

															lm_municipio = lmd_municipioDAO.findById(lm_municipio);

															if(lm_municipio != null)
															{
																String ls_municipioNombre;
																ls_municipioNombre = lm_municipio.getNombre();

																if(StringUtils.isValidString(ls_municipioNombre))
																	ls_complementacionFinal.append(
																	    ls_municipioNombre
																	    + IdentificadoresCommon.PUNTO
																	);
																else
																	ls_complementacionFinal.append("SIN INFORMACIÓN ");
															}
															else
																ls_complementacionFinal.append("SIN INFORMACIÓN ");
														}
														else
															ls_complementacionFinal.append("SIN INFORMACIÓN ");
													}
													else
													{
														String ls_comentarioDoc;
														ls_comentarioDoc = ld_documento.getComentario();

														if(StringUtils.isValidString(ls_comentarioDoc))
															ls_complementacionFinal.append(ls_comentarioDoc + " ");
														else
															ls_complementacionFinal.append("SIN INFORMACIÓN ");
													}

													lca_complementacionAnotacion.setComplementacion(
													    ls_complementacionFinal.toString()
													);

													lc_complementacionAnotacionReturn.add(lca_complementacionAnotacion);
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

			clh_LOGGER.error("construirComplementacionAnotaciones", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lc_complementacionAnotacionReturn;
	}

	/**
	 *
	 * Método encargado de crear (precalificación) la anotación de baldios.
	 *
	 * @param aapr_predio Objeto que contiene la información de la matrícula actual para crear la anotación.
	 * @param as_userId Variable de tipo String que contiene el id del usuario que está realizando el trámite.
	 * @param as_remoteIp Variable de tipo String que contiene la ip del usuario que está realizando el trámite.
	 * @return
	 * @throws B2BException
	 */
	public synchronized Anotacion crearAnotacionBaldios(
	    AccPredioRegistro aapr_predio, String as_userId, String as_remoteIp
	)
	    throws B2BException
	{
		Anotacion  la_return;
		DAOManager ldm_manager;

		la_return       = null;
		ldm_manager     = DaoManagerFactory.getDAOManager();

		try
		{
			if(aapr_predio != null)
			{
				String ls_idTurno;

				ls_idTurno = aapr_predio.getIdTurno();

				if(StringUtils.isValidString(ls_idTurno))
				{
					Turno lt_turno;

					lt_turno = new Turno();

					lt_turno.setIdTurno(ls_idTurno);

					lt_turno = DaoCreator.getTurnoDAO(ldm_manager).findById(lt_turno);

					if(lt_turno != null)
					{
						com.bachue.snr.prosnr01.model.sdb.acc.AnotacionPredio       lap_anotacionPredio;
						com.bachue.snr.prosnr01.dao.acc.AnotacionPredioDAO          lap_DAO;
						com.bachue.snr.prosnr01.dao.acc.AnotacionPredioCiudadanoDAO lapc_DAO;
						Documento                                                   ld_documento;
						long                                                        ll_version;
						Long                                                        ll_versionDocumento;
						Long                                                        ll_idMatricula;
						String                                                      ls_idCirculo;
						String                                                      ls_idSolicitud;
						String                                                      ls_idDocumento;
						String                                                      ls_idActoBaldios;

						la_return               = new Anotacion();
						lap_anotacionPredio     = new com.bachue.snr.prosnr01.model.sdb.acc.AnotacionPredio();
						lap_DAO                 = DaoCreator.getAccAnotacionPredioDAO(ldm_manager);
						lapc_DAO                = DaoCreator.getAccAnotacionPredioCiudadanoDAO(ldm_manager);
						ld_documento            = null;
						ll_version              = 1L;
						ll_versionDocumento     = NumericUtils.getLongWrapper(ll_version);
						ll_idMatricula          = aapr_predio.getIdMatricula();
						ls_idCirculo            = aapr_predio.getIdCirculo();
						ls_idSolicitud          = lt_turno.getIdSolicitud();
						ls_idDocumento          = null;
						ls_idActoBaldios        = null;

						if(StringUtils.isValidString(ls_idSolicitud))
						{
							Map<String, String> lmss_baldios;
							Solicitud           ls_solicitud;

							lmss_baldios     = generarCodigos(ConstanteCommon.TIPOS_ACTOS_BALDIOS, ldm_manager);
							ls_solicitud     = new Solicitud();

							ls_solicitud.setIdSolicitud(ls_idSolicitud);

							ls_solicitud = DaoCreator.getSolicitudDAO(ldm_manager).findById(ls_solicitud);

							if(ls_solicitud != null)
							{
								ls_idDocumento = ls_solicitud.getIdDocumento();

								if(StringUtils.isValidString(ls_idDocumento))
								{
									ld_documento = new Documento();

									ld_documento.setIdDocumento(ls_idDocumento);

									ld_documento = DaoCreator.getDocumentoDAO(ldm_manager).findById(ld_documento);

									if(ld_documento != null)
										ll_versionDocumento = ld_documento.getVersionDocumento();
								}
							}

							if(CollectionUtils.isValidCollection(lmss_baldios))
							{
								Acto             la_param;
								Collection<Acto> lca_actos;

								la_param = new Acto();

								la_param.setIdSolicitud(ls_idSolicitud);
								la_param.setIdCirculo(ls_idCirculo);

								lca_actos = DaoCreator.getActoDAO(ldm_manager).findByIdSolicitudCirculo(la_param);

								if(CollectionUtils.isValidCollection(lca_actos))
								{
									boolean        lb_continuar;
									Iterator<Acto> lia_iterator;

									lb_continuar     = false;
									lia_iterator     = lca_actos.iterator();

									while(lia_iterator.hasNext() && !lb_continuar)
									{
										Acto la_acto;

										la_acto = lia_iterator.next();

										if(la_acto != null)
										{
											String ls_idTipoActo;

											ls_idTipoActo     = la_acto.getIdTipoActo();
											lb_continuar      = StringUtils.isValidString(ls_idTipoActo)
													&& lmss_baldios.containsKey(ls_idTipoActo);

											if(lb_continuar)
												ls_idActoBaldios = ls_idTipoActo;
										}
									}
								}
							}
						}

						lap_anotacionPredio.setIdMatricula(ll_idMatricula);
						lap_anotacionPredio.setIdCirculo(ls_idCirculo);
						lap_anotacionPredio.setIdNaturalezaJuridica(ls_idActoBaldios);

						lap_anotacionPredio = lap_DAO.findByCirculoMatriculaActo(lap_anotacionPredio);

						if(lap_anotacionPredio == null)
						{
							long   ll_idAnotacion;
							String ls_especificacion;

							lap_anotacionPredio     = new com.bachue.snr.prosnr01.model.sdb.acc.AnotacionPredio();
							ll_idAnotacion          = 1L;
							ls_especificacion       = null;

							lap_anotacionPredio.setIdAnotacion(NumericUtils.getLongWrapper(ll_idAnotacion));
							lap_anotacionPredio.setFechaRadicacion(new Date());
							lap_anotacionPredio.setRadicacion(ls_idTurno);
							lap_anotacionPredio.setIdEstadoAnotacion(EstadoCommon.V);
							lap_anotacionPredio.setIdCirculo(ls_idCirculo);
							lap_anotacionPredio.setIdMatricula(ll_idMatricula);

							{
								NaturalezaJuridica lnj_naturalezaJuridica;

								lnj_naturalezaJuridica = new NaturalezaJuridica();

								lnj_naturalezaJuridica.setIdNaturalezaJuridica(ls_idActoBaldios);

								lnj_naturalezaJuridica = DaoCreator.getNaturalezaJuridicaDAO(ldm_manager)
										                               .findByIdMaxVersion(lnj_naturalezaJuridica);

								if(lnj_naturalezaJuridica != null)
								{
									ll_version            = lnj_naturalezaJuridica.getVersion();
									ls_especificacion     = lnj_naturalezaJuridica.getNombre();
								}
							}

							lap_anotacionPredio.setIdNaturalezaJuridica(ls_idActoBaldios);
							lap_anotacionPredio.setVersion(ll_version);
							lap_anotacionPredio.setEspecificacion(ls_especificacion);
							lap_anotacionPredio.setOrden(NumericUtils.getBigDecimal(1));
							lap_anotacionPredio.setIdSolicitud(ls_idSolicitud);
							lap_anotacionPredio.setIdTurno(ls_idTurno);
							lap_anotacionPredio.setIdTurnoHistoria(
							    NumericUtils.getLong(aapr_predio.getIdTurnoHistoria())
							);
							lap_anotacionPredio.setIdDocumento(ls_idDocumento);
							lap_anotacionPredio.setVersionDocumento(NumericUtils.getLong(ll_versionDocumento));
							lap_anotacionPredio.setFechaRegistro(lt_turno.getFechaCreacion());
							lap_anotacionPredio.setIdEstadoRegistro(EstadoCommon.T);
							lap_anotacionPredio.setIdUsuarioCreacion(as_userId);
							lap_anotacionPredio.setIpCreacion(as_remoteIp);

							lap_anotacionPredio = lap_DAO.insert(lap_anotacionPredio);

							{
								Collection<SolicitudInterviniente> lcsi_intervinientes;
								SolicitudInterviniente             lsi_param;

								lsi_param = new SolicitudInterviniente();

								lsi_param.setIdSolicitud(ls_idSolicitud);

								lcsi_intervinientes = DaoCreator.getSolicitudIntervinienteDAO(ldm_manager)
										                            .findByIdSolicitud(lsi_param);

								if(CollectionUtils.isValidCollection(lcsi_intervinientes))
								{
									Collection<Anotacion> lca_intervinientes;
									PersonaDAO            lp_DAO;
									String                ls_idAnotacionPredio;

									lca_intervinientes       = new ArrayList<Anotacion>();
									lp_DAO                   = DaoCreator.getPersonaDAO(ldm_manager);
									ls_idAnotacionPredio     = lap_anotacionPredio.getIdAnotacionPredio();

									for(SolicitudInterviniente lsi_iterador : lcsi_intervinientes)
									{
										if(lsi_iterador != null)
										{
											String ls_idPersona;

											ls_idPersona = lsi_iterador.getIdPersona();

											if(StringUtils.isValidString(ls_idPersona))
											{
												Persona lp_persona;

												lp_persona = new Persona();

												lp_persona.setIdPersona(ls_idPersona);

												lp_persona = lp_DAO.findById(lp_persona);

												if(lp_persona != null)
												{
													Anotacion                                                      la_anotacionInterviniente;
													com.bachue.snr.prosnr01.model.sdb.acc.AnotacionPredioCiudadano lapc_predioCiudadano;

													la_anotacionInterviniente     = new Anotacion();
													lapc_predioCiudadano          = new com.bachue.snr.prosnr01.model.sdb.acc.AnotacionPredioCiudadano();

													la_anotacionInterviniente.setPersona(lp_persona);

													lapc_predioCiudadano.setIdAnotacionPredio(ls_idAnotacionPredio);
													lapc_predioCiudadano.setIdCirculo(ls_idCirculo);
													lapc_predioCiudadano.setIdMatricula(
													    NumericUtils.getLong(ll_idMatricula)
													);
													lapc_predioCiudadano.setIdAnotacion(ll_idAnotacion);
													lapc_predioCiudadano.setIdPersona(lsi_iterador.getIdPersona());
													lapc_predioCiudadano.setRolPersona(lsi_iterador.getRolPersona());
													lapc_predioCiudadano.setIdTurno(ls_idTurno);
													lapc_predioCiudadano.setIdUsuarioCreacion(as_userId);
													lapc_predioCiudadano.setIpCreacion(as_remoteIp);

													lapc_DAO.insert(lapc_predioCiudadano);

													la_anotacionInterviniente.setSolicitudInterviniente(lsi_iterador);
													la_anotacionInterviniente.setAnotacionPredioCiudadano(
													    lapc_predioCiudadano
													);

													lca_intervinientes.add(la_anotacionInterviniente);
												}
											}
										}
									}

									la_return.setIntervinientesAgregados(lca_intervinientes);
								}
							}
						}
						else
						{
							String ls_idAnotacionPredio;

							ls_idAnotacionPredio = lap_anotacionPredio.getIdAnotacionPredio();

							if(StringUtils.isValidString(ls_idAnotacionPredio))
							{
								com.bachue.snr.prosnr01.model.sdb.acc.AnotacionPredioCiudadano             lapc_idPredioCiudadano;
								Collection<com.bachue.snr.prosnr01.model.sdb.acc.AnotacionPredioCiudadano> lcapc_ciudadanos;

								lapc_idPredioCiudadano = new com.bachue.snr.prosnr01.model.sdb.acc.AnotacionPredioCiudadano();

								lapc_idPredioCiudadano.setIdAnotacionPredio(ls_idAnotacionPredio);

								lcapc_ciudadanos = lapc_DAO.findByIdAnotacion(lapc_idPredioCiudadano);

								if(CollectionUtils.isValidCollection(lcapc_ciudadanos))
								{
									Collection<Anotacion> lca_intervinientes;

									lca_intervinientes = new ArrayList<Anotacion>();

									for(com.bachue.snr.prosnr01.model.sdb.acc.AnotacionPredioCiudadano lapc_iterador : lcapc_ciudadanos)
									{
										if(lapc_iterador != null)
										{
											Anotacion la_anotacion;

											la_anotacion = new Anotacion();

											la_anotacion.setAnotacionPredioCiudadano(lapc_iterador);

											lca_intervinientes.add(la_anotacion);
										}
									}

									la_return.setIntervinientesAgregados(lca_intervinientes);
								}
							}
						}

						la_return.setAnotacionPredio(lap_anotacionPredio);
						la_return.setNaturalezaJuridica(ls_idActoBaldios);
					}
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("crearAnotacionBaldios", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return la_return;
	}

	/**
	 *
	 * @param aorc_rc
	 *            Objeto con información necesaria para construir el detalle de la
	 *            anotación
	 * @return
	 * @throws B2BException
	 */
	public synchronized RegistroCalificacion datalleAnotaciones(RegistroCalificacion aorc_rc)
	    throws B2BException
	{
		DAOManager                                                                 ldm_manager;
		Collection<com.bachue.snr.prosnr01.model.sdb.acc.AnotacionPredioCiudadano> lcap_dataIntervinientes;
		com.bachue.snr.prosnr01.model.sdb.acc.AnotacionPredioCiudadano             loapc_apc;
		com.bachue.snr.prosnr01.dao.acc.AnotacionPredioDAO                         loapd_dao;
		com.bachue.snr.prosnr01.model.sdb.acc.AnotacionPredio                      loap_ap;
		com.bachue.snr.prosnr01.model.sdb.acc.AnotacionPredio                      loap_anotacion;
		com.bachue.snr.prosnr01.model.sdb.acc.AnotacionPredio                      lorc_dataAnotaciones;
		Documento                                                                  lod_documento;
		DocumentoDAO                                                               lodd_dao;
		Persona                                                                    lop_persona;
		Persona                                                                    ldp_datos;
		RegistroCalificacion                                                       lorc_dataReturn;
		String                                                                     ls_idAnotacion;
		String                                                                     ls_idDocumento;
		String                                                                     ls_idPersona;
		Anotacion                                                                  loa_dataInterviniente;
		Collection<Anotacion>                                                      lca_dataIntervinientes;
		PersonaDAO                                                                 lopd_dao;

		ldm_manager                 = DaoManagerFactory.getDAOManager();
		loapc_apc                   = new com.bachue.snr.prosnr01.model.sdb.acc.AnotacionPredioCiudadano();
		loapd_dao                   = DaoCreator.getAccAnotacionPredioDAO(ldm_manager);
		lodd_dao                    = DaoCreator.getDocumentoDAO(ldm_manager);
		lcap_dataIntervinientes     = new ArrayList<com.bachue.snr.prosnr01.model.sdb.acc.AnotacionPredioCiudadano>();
		loap_anotacion              = new com.bachue.snr.prosnr01.model.sdb.acc.AnotacionPredio();
		loap_ap                     = new com.bachue.snr.prosnr01.model.sdb.acc.AnotacionPredio();
		lod_documento               = new Documento();
		lop_persona                 = new Persona();
		lorc_dataAnotaciones        = new com.bachue.snr.prosnr01.model.sdb.acc.AnotacionPredio();
		lorc_dataReturn             = new RegistroCalificacion();
		loa_dataInterviniente       = new Anotacion();
		lca_dataIntervinientes      = new ArrayList<Anotacion>();
		lopd_dao                    = DaoCreator.getPersonaDAO(ldm_manager);

		try
		{
			if(aorc_rc != null)
			{
				ls_idAnotacion = aorc_rc.getIdAnotacionPredio();

				if(StringUtils.isValidString(ls_idAnotacion))
				{
					loap_ap.setIdAnotacionPredio(ls_idAnotacion);

					lorc_dataAnotaciones = loapd_dao.findById(loap_ap);

					if(lorc_dataAnotaciones != null)
					{
						loap_anotacion.setIdAnotacion(lorc_dataAnotaciones.getIdAnotacion());
						loap_anotacion.setFechaRadicacion(lorc_dataAnotaciones.getFechaRadicacion());
						loap_anotacion.setRadicacion(lorc_dataAnotaciones.getRadicacion());
						loap_anotacion.setIdEstadoAnotacion(lorc_dataAnotaciones.getIdEstadoAnotacion());
						loap_anotacion.setEspecificacion(lorc_dataAnotaciones.getEspecificacion());
						loap_anotacion.setValor(lorc_dataAnotaciones.getValor());
						loap_anotacion.setComentario(lorc_dataAnotaciones.getComentario());
						loap_anotacion.setIdNaturalezaJuridica(lorc_dataAnotaciones.getIdNaturalezaJuridica());
						loap_anotacion.setIdDocumento(lorc_dataAnotaciones.getIdDocumento());
						loap_anotacion.setIdAnotacionPredio(lorc_dataAnotaciones.getIdAnotacionPredio());
						loap_anotacion.setIdSolicitud(lorc_dataAnotaciones.getIdSolicitud());

						/* ANOTACION CANCELACION PENDIENTE */
						ls_idDocumento = lorc_dataAnotaciones.getIdDocumento();
						lorc_dataReturn.setDataAnotacionPredio(loap_anotacion);

						if(StringUtils.isValidString(ls_idDocumento))

							if(StringUtils.isValidString(ls_idDocumento))
							{
								RegistroCalificacion lod_tmp;
								lod_tmp     = new RegistroCalificacion();

								lod_tmp = lodd_dao.detalleDocumento(ls_idDocumento);

								if(lod_tmp != null)
								{
									lod_documento.setIdTipoDocumento(lod_tmp.getIdTipoDocumento());
									lod_documento.setNumero(lod_tmp.getCodDocumento());
									lod_documento.setFechaDocumento(lod_tmp.getFechaDocumento());
									lod_documento.setIdTipoOficina(lod_tmp.getIdTipoOficina());
									lod_documento.setTipoEntidad(lod_tmp.getIdTipoEntidad());
									lod_documento.setIdPais(lod_tmp.getIdPais());
									lod_documento.setIdDepartamento(lod_tmp.getIdDepartamento());
									lod_documento.setIdMunicipio(lod_tmp.getIdMunicipio());
									lod_documento.setIdOficinaOrigen(lod_tmp.getIdOficinaOrigen());

									lorc_dataReturn.setDataDocumento(lod_documento);
								}

								lcap_dataIntervinientes = DaoCreator.getAccAnotacionPredioCiudadanoDAO(ldm_manager)
										                                .dataIntervinientes(ls_idAnotacion);

								if(CollectionUtils.isValidCollection(lcap_dataIntervinientes))    /*
								* loa_dataInterviniente = lca_dataIntervinientes
								*/
								{
									for(com.bachue.snr.prosnr01.model.sdb.acc.AnotacionPredioCiudadano loapc_tmp : lcap_dataIntervinientes)
									{
										loapc_apc.setPropietario(loapc_tmp.getPropietario());
										loapc_apc.setPorcentajeParticipacion(loapc_tmp.getPorcentajeParticipacion());
										loapc_apc.setRolPersona(loapc_tmp.getRolPersona());

										ls_idPersona = loapc_tmp.getIdPersona();

										if(StringUtils.isValidString(ls_idPersona))
										{
											ldp_datos = new Persona();
											ldp_datos.setIdPersona(ls_idPersona);

											ldp_datos = lopd_dao.findById(ldp_datos);

											if(ldp_datos != null)
											{
												lop_persona.setIdTipoPersona(ldp_datos.getIdTipoPersona());
												lop_persona.setIdDocumentoTipo(ldp_datos.getIdDocumentoTipo());
												lop_persona.setNumeroDocumento(ldp_datos.getNumeroDocumento());
												lop_persona.setIdPais(ldp_datos.getIdPais());
												lop_persona.setPrimerNombre(ldp_datos.getPrimerNombre());
												lop_persona.setSegundoNombre(ldp_datos.getSegundoNombre());
												lop_persona.setPrimerApellido(ldp_datos.getPrimerApellido());
												lop_persona.setSegundoApellido(ldp_datos.getSegundoApellido());
												lop_persona.setIdNaturalGenero(ldp_datos.getIdNaturalGenero());
												lop_persona.setRazonSocial(ldp_datos.getRazonSocial());
												lop_persona.setIdPersona(ls_idPersona);

												loa_dataInterviniente.setAnotacionPredioCiudadano(loapc_apc);
												loa_dataInterviniente.setPersona(lop_persona);
												lca_dataIntervinientes.add(loa_dataInterviniente);
												loapc_apc                 = new com.bachue.snr.prosnr01.model.sdb.acc.AnotacionPredioCiudadano();
												lop_persona               = new Persona();
												loapc_tmp                 = new com.bachue.snr.prosnr01.model.sdb.acc.AnotacionPredioCiudadano();
												loa_dataInterviniente     = new Anotacion();
											}
										}
									}

									lorc_dataReturn.setInfoIntervinientes(lca_dataIntervinientes);
								}
							}

						{
							AnotacionCancelacion lorc_rc;

							TurnoHistoria        lth_turnoHistoria;
							TurnoHistoriaDAO     lthd_DAO;
							String               ls_circulo;
							long                 ll_matricula;

							lorc_rc          = new AnotacionCancelacion();
							ls_circulo       = lorc_dataAnotaciones.getIdCirculo();
							ll_matricula     = NumericUtils.getLong(lorc_dataAnotaciones.getIdMatricula());

							lorc_rc.setIdCirculo(ls_circulo);
							lorc_rc.setIdMatricula(ll_matricula);
							lorc_rc.setIdAnotacion(lorc_dataAnotaciones.getIdAnotacion());

							lth_turnoHistoria     = new TurnoHistoria();
							lthd_DAO              = DaoCreator.getTurnoHistoriaDAO(ldm_manager);

							lth_turnoHistoria.setIdTurnoHistoria(
							    NumericUtils.getLongWrapper(lorc_dataAnotaciones.getIdTurnoHistoria())
							);

							lth_turnoHistoria = lthd_DAO.findById(lth_turnoHistoria);

							if(lth_turnoHistoria != null)
							{
								Collection<RegistroCalificacion> lcrca_rc;

								lcrca_rc = new ArrayList<RegistroCalificacion>();
								lorc_rc.setIdTurno(lth_turnoHistoria.getIdTurno());

								lcrca_rc = cargarDatosAnotacionesACancelar(ldm_manager, lorc_rc);

								if(CollectionUtils.isValidCollection(lcrca_rc))
									lorc_dataReturn.setAnotacionesCancelacion(lcrca_rc);
							}
						}
					}
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("datalleAnotaciones", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lorc_dataReturn;
	}

	/**
	 * Muted para generar el detalle de los intervinientes
	 *
	 * @param aorc_rc
	 *            Objeto para extraer información de los intervinientes
	 * @return
	 * @throws B2BException
	 */
	public synchronized RegistroCalificacion detalleIntervinientes(RegistroCalificacion aorc_rc)
	    throws B2BException
	{
		DAOManager                 ldm_manager;
		RegistroCalificacion       lorc_dataIntervinientes;
		RegistroCalificacionDAO    lorcd_rcd;
		String                     ls_idAnotacion;
		InteresadoDocumentoTipoDAO lodt_DAO;

		ldm_manager                 = DaoManagerFactory.getDAOManager();
		lorcd_rcd                   = DaoCreator.getRegistroCalificacionDAO(ldm_manager);
		lodt_DAO                    = DaoCreator.getInteresadoDocumentoTipoDAO(ldm_manager);
		lorc_dataIntervinientes     = new RegistroCalificacion();

		try
		{
			if(aorc_rc != null)
			{
				ls_idAnotacion = aorc_rc.getIdAnotacionPredio();

				if(StringUtils.isValidString(ls_idAnotacion))
				{
					Collection<RegistroCalificacion> locrc_rc;

					locrc_rc                    = null;
					lorc_dataIntervinientes     = lorcd_rcd.consultaAnotacionPredio(ls_idAnotacion);
					locrc_rc                    = lorcd_rcd.findDetalleIntervinientes(ls_idAnotacion);

					if(CollectionUtils.isValidCollection(locrc_rc))
					{
						Collection<SolicitudInterviniente> lcsi_solicitudInterviniente;

						lcsi_solicitudInterviniente = new ArrayList<SolicitudInterviniente>();

						for(RegistroCalificacion lrc_registroCalificacion : locrc_rc)
						{
							if(lrc_registroCalificacion != null)
							{
								Persona lp_persona;

								lp_persona = DaoCreator.getPersonaDAO(ldm_manager)
										                   .findById(lrc_registroCalificacion.getIdPersona());

								if(lp_persona != null)
								{
									lrc_registroCalificacion.setDataPersona(lp_persona);

									InteresadoDocumentoTipo lidt_idt;

									lidt_idt = lodt_DAO.findByIdInteresadoRRR(
										    lrc_registroCalificacion.getInteresadaRrr()
										);

									if(lidt_idt != null)
										lrc_registroCalificacion.setInteresadaRrrNombre(lidt_idt.getDescripcion());

									SolicitudInterviniente lsi_solicitudInterviniente;

									lsi_solicitudInterviniente = new SolicitudInterviniente(lrc_registroCalificacion);

									lcsi_solicitudInterviniente.add(lsi_solicitudInterviniente);
								}
							}
						}

						lorc_dataIntervinientes.setIntervinientesIngresados(lcsi_solicitudInterviniente);
					}
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("detalleIntervinientes", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lorc_dataIntervinientes;
	}

	/**
	 * Método para poder eliminar anotación
	 *
	 * @param arc_rc
	 *            Objeto con información necesaria de la anotación a eliminar
	 * @throws B2BException
	 */
	public synchronized void eliminarAnotacion(RegistroCalificacion arc_rc)
	    throws B2BException
	{
		DAOManager                                         ldm_manager;
		com.bachue.snr.prosnr01.dao.acc.AnotacionPredioDAO lapd_DAO;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		lapd_DAO        = DaoCreator.getAccAnotacionPredioDAO(ldm_manager);

		try
		{
			if(arc_rc != null)
			{
				com.bachue.snr.prosnr01.model.sdb.acc.AnotacionPredio lap_anotacionPredio;
				RegistroParcialCalificacion                           lrpc_rpc;
				String                                                ls_idAnotacionPredio;

				lap_anotacionPredio      = new com.bachue.snr.prosnr01.model.sdb.acc.AnotacionPredio();
				lrpc_rpc                 = arc_rc.getRegistroParcialCalificacion();
				ls_idAnotacionPredio     = null;

				if(lrpc_rpc != null)
				{
					ls_idAnotacionPredio = lrpc_rpc.getIdAnotacionPredio();

					if(StringUtils.isValidString(ls_idAnotacionPredio))
					{
						lap_anotacionPredio.setIdAnotacionPredio(ls_idAnotacionPredio);

						lap_anotacionPredio = lapd_DAO.findById(lap_anotacionPredio);

						if(lap_anotacionPredio != null)
						{
							String ls_idSolicitud;
							String ls_matriculaCompleta;
							String ls_idTipoActo;
							String ls_idNaturalezaJuridica;
							String ls_idCirculoActo;
							String ls_idCirculoAnotacion;
							Long   ll_idMatriculaActo;
							Long   ll_idMatriculaAnotacion;

							ls_matriculaCompleta        = lrpc_rpc.getMatriculaCompleta();
							ls_idSolicitud              = lap_anotacionPredio.getIdSolicitud();
							ls_idTipoActo               = lap_anotacionPredio.getIdNaturalezaJuridica();
							ls_idNaturalezaJuridica     = lrpc_rpc.getIdNaturalezaJuridica();
							ls_idCirculoAnotacion       = lap_anotacionPredio.getIdCirculo();
							ll_idMatriculaAnotacion     = lap_anotacionPredio.getIdMatricula();
							ls_idCirculoActo            = null;
							ll_idMatriculaActo          = null;

							if(StringUtils.isValidString(ls_matriculaCompleta))
							{
								String[] lsa_matricula;

								lsa_matricula          = ls_matriculaCompleta.split("-");
								ls_idCirculoActo       = lsa_matricula[0];
								ll_idMatriculaActo     = NumericUtils.getLongWrapper(lsa_matricula[1]);

								if(
								    StringUtils.isValidString(ls_idSolicitud)
									    && StringUtils.isValidString(ls_idNaturalezaJuridica)
									    && StringUtils.isValidString(ls_idTipoActo)
									    && StringUtils.isValidString(ls_idCirculoAnotacion)
									    && StringUtils.isValidString(ls_idCirculoActo)
									    && NumericUtils.isValidLong(ll_idMatriculaAnotacion)
								)
								{
									if(
									    ls_idTipoActo.equalsIgnoreCase(ls_idNaturalezaJuridica)
										    && ls_idCirculoAnotacion.equalsIgnoreCase(ls_idCirculoActo)
									)
									{
										if(
										    NumericUtils.isValidLong(ll_idMatriculaActo)
											    && (ll_idMatriculaActo.compareTo(ll_idMatriculaAnotacion) == 0)
										)
										{
											Acto    la_acto;
											ActoDAO la_DAO;

											la_acto     = new Acto();
											la_DAO      = DaoCreator.getActoDAO(ldm_manager);

											la_acto.setIdSolicitud(ls_idSolicitud);
											la_acto.setIdTipoActo(ls_idTipoActo);

											la_acto = la_DAO.findByIdSolicitudTipoActo(la_acto);

											if(la_acto != null)
											{
												String ls_idActo;

												ls_idActo = la_acto.getIdActo();

												if(StringUtils.isValidString(ls_idActo))
												{
													SolicitudMatriculaActo    lsma_solicitudMatriculaActo;
													SolicitudMatriculaActoDAO lsma_DAO;

													lsma_solicitudMatriculaActo     = new SolicitudMatriculaActo();
													lsma_DAO                        = DaoCreator
															.getSolicitudMatriculaActoDAO(ldm_manager);

													lsma_solicitudMatriculaActo.setIdSolicitud(ls_idSolicitud);
													lsma_solicitudMatriculaActo.setIdCirculo(ls_idCirculoAnotacion);
													lsma_solicitudMatriculaActo.setIdMatricula(
													    NumericUtils.getLong(ll_idMatriculaAnotacion)
													);
													lsma_solicitudMatriculaActo.setIdActo(ls_idActo);

													lsma_solicitudMatriculaActo = lsma_DAO.findById(
														    lsma_solicitudMatriculaActo
														);

													if(lsma_solicitudMatriculaActo != null)
													{
														lsma_solicitudMatriculaActo.setEstado(EstadoCommon.INACTIVO);
														lsma_DAO.insertOrUpdate(lsma_solicitudMatriculaActo, false);
													}

													Collection<SolicitudMatriculaActo> lcsma_matriculasActo;

													lcsma_matriculasActo = lsma_DAO.findAllByIdSolicitud(
														    lsma_solicitudMatriculaActo, true
														);

													if(CollectionUtils.isValidCollection(lcsma_matriculasActo))
													{
														for(SolicitudMatriculaActo lsma_acto : lcsma_matriculasActo)
														{
															String ls_estado;

															ls_estado = lsma_acto.getEstado();

															if(
															    (StringUtils.isValidString(ls_estado)
																    && ls_estado.equalsIgnoreCase(EstadoCommon.A))
																    || (ls_estado == null)
															)
															{
																lsma_acto.setMotivoTramite("INSCRIPCION PARCIAL");
																lsma_DAO.insertOrUpdate(lsma_acto, false);
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
				else
					ls_idAnotacionPredio = arc_rc.getIdAnotacionPredio();

				if(StringUtils.isValidString(ls_idAnotacionPredio))
				{
					com.bachue.snr.prosnr01.model.sdb.acc.AnotacionPredioCiudadano lapc_anotacionPredioCiudadano;
					com.bachue.snr.prosnr01.model.sdb.acc.AnotacionPredio          lap_anotacionPredioDelete;

					lapc_anotacionPredioCiudadano     = new com.bachue.snr.prosnr01.model.sdb.acc.AnotacionPredioCiudadano();
					lap_anotacionPredioDelete         = new com.bachue.snr.prosnr01.model.sdb.acc.AnotacionPredio();

					lap_anotacionPredioDelete.setIdAnotacionPredio(ls_idAnotacionPredio);
					lapc_anotacionPredioCiudadano.setIdAnotacionPredio(ls_idAnotacionPredio);

					lap_anotacionPredioDelete = lapd_DAO.findById(lap_anotacionPredioDelete);

					if(lap_anotacionPredioDelete != null)
					{
						Acto   la_acto;
						String ls_idCirculo;
						String ls_idSolicitud;

						la_acto            = new Acto();
						ls_idCirculo       = lap_anotacionPredioDelete.getIdCirculo();
						ls_idSolicitud     = lap_anotacionPredioDelete.getIdSolicitud();

						la_acto.setIdSolicitud(ls_idSolicitud);
						la_acto.setIdCirculo(ls_idCirculo);
						la_acto.setIdTipoActo(lap_anotacionPredioDelete.getIdNaturalezaJuridica());

						la_acto = DaoCreator.getActoDAO(ldm_manager).findBySolicitudIdCirculoTipoActo(la_acto);

						if(la_acto != null)
						{
							SolicitudMatriculaActoDAO lsma_DAO;
							SolicitudMatriculaActo    lsma_matriculaActo;

							lsma_DAO               = DaoCreator.getSolicitudMatriculaActoDAO(ldm_manager);
							lsma_matriculaActo     = new SolicitudMatriculaActo();

							lsma_matriculaActo.setIdCirculo(ls_idCirculo);
							lsma_matriculaActo.setIdMatricula(
							    NumericUtils.getLong(lap_anotacionPredioDelete.getIdMatricula())
							);
							lsma_matriculaActo.setIdSolicitud(ls_idSolicitud);
							lsma_matriculaActo.setIdActo(la_acto.getIdActo());

							lsma_matriculaActo = lsma_DAO.findById(lsma_matriculaActo);

							if(lsma_matriculaActo != null)
							{
								lsma_matriculaActo.setEstado(EstadoCommon.INACTIVO);
								lsma_DAO.insertOrUpdate(lsma_matriculaActo, false);
							}
						}

						lap_anotacionPredioDelete.setIdUsuarioModificacion(arc_rc.getIdUsuarioModificacion());
						lap_anotacionPredioDelete.setIpModificacion(arc_rc.getIpModificacion());
						lap_anotacionPredioDelete.setActivo(EstadoCommon.N);
					}

					//No elimina el registro de AnotacionPredio y AnotacionPredioCiudadano; cambia el estado Activo a N
					lapd_DAO.updateAnotacion(lap_anotacionPredioDelete, true);

					//lapcd_DAO.deleteIntervinientes(lapc_anotacionPredioCiudadano);
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("eliminarAnotacion", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}
	}

	/**
	 * Método para poder eliminar la información de una persona
	 *
	 * @param asi_dataPersona
	 *            Objeto con información necesaria de la anotacion a eliminar
	 * @return
	 * @throws B2BException
	 */
	public synchronized RegistroCalificacion eliminarDataPersona(
	    SolicitudInterviniente asi_dataPersona, String as_userId
	)
	    throws B2BException
	{
		DAOManager              ldm_manager;
		RegistroCalificacion    lorc_matriculas;
		RegistroCalificacionDAO lorcd_rcd;

		ldm_manager         = DaoManagerFactory.getDAOManager();
		lorcd_rcd           = DaoCreator.getRegistroCalificacionDAO(ldm_manager);
		lorc_matriculas     = new RegistroCalificacion();

		try
		{
			if(asi_dataPersona != null)
			{
				com.bachue.snr.prosnr01.dao.acc.AnotacionPredioCiudadanoDAO    laapc_DAO;
				String                                                         ls_idAnotacionPredioCiudadano;
				com.bachue.snr.prosnr01.model.sdb.acc.AnotacionPredioCiudadano lapc_interviniente;

				ls_idAnotacionPredioCiudadano     = asi_dataPersona.getIdAnotacionPredio();
				laapc_DAO                         = DaoCreator.getAccAnotacionPredioCiudadanoDAO(ldm_manager);
				lapc_interviniente                = new com.bachue.snr.prosnr01.model.sdb.acc.AnotacionPredioCiudadano();

				lapc_interviniente.setIdAnotacionPredioCiudadano(ls_idAnotacionPredioCiudadano);

				lapc_interviniente = laapc_DAO.findById(lapc_interviniente);

				if(lapc_interviniente != null)
				{
					Collection<com.bachue.snr.prosnr01.model.sdb.acc.AnotacionPredioCiudadano> lcapc_anotaciones;

					lcapc_anotaciones = laapc_DAO.findByIdAnotacionPersona(lapc_interviniente);

					if(CollectionUtils.isValidCollection(lcapc_anotaciones) && (lcapc_anotaciones.size() >= 2))
					{
						String ls_rolActual;
						String ls_rolOpuesto;

						ls_rolActual      = StringUtils.getStringNotNull(asi_dataPersona.getRolPersona());
						ls_rolOpuesto     = ls_rolActual.equalsIgnoreCase("De") ? "A" : "De";

						asi_dataPersona.setRolPersona(ls_rolOpuesto);
						asi_dataPersona.setIdUsuarioModificacion(as_userId);

						DaoCreator.getSolicitudIntervinienteDAO(ldm_manager).updateRol(asi_dataPersona);
					}
				}

				lorcd_rcd.deleteInterviniente(ls_idAnotacionPredioCiudadano, true);

				// lorcd_rcd.deleteIntervinienteSolicitud(aorc_rc.getIdPersona(),
				// aorc_rc.getIdSolicitud());
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("eliminarDataPersona", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lorc_matriculas;
	}

	/**
	 * Método para enviar matricula a la etapa de digitador masivo
	 *
	 * @param aorc_rc
	 *            objeto con información necesaria de la matricula
	 * @return
	 * @throws B2BException
	 */
	public synchronized RegistroCalificacion enviarADigitador(RegistroCalificacion aorc_rc)
	    throws B2BException
	{
		DAOManager           ldm_manager;
		RegistroCalificacion lorc_dataIntervinientes;

		ldm_manager                 = DaoManagerFactory.getDAOManager();
		lorc_dataIntervinientes     = new RegistroCalificacion();

		try
		{
			if(aorc_rc != null)
			{
				if(aorc_rc.isCierreFolio() && aorc_rc.isSalvarCierreFolio())
					salvarCierreFolio(aorc_rc, ldm_manager);

				Long   ll_idTurnoHistoria;
				String ls_usuario;
				String ls_ip;

				ls_usuario             = aorc_rc.getUserId();
				ls_ip                  = aorc_rc.getIpModificacion();
				ll_idTurnoHistoria     = aorc_rc.getIdTurnoHistoria();

				if(NumericUtils.isValidLong(ll_idTurnoHistoria))
				{
					TurnoHistoria    loth_dataTurn;
					TurnoHistoriaDAO lth_dataTurnDAO;

					loth_dataTurn       = new TurnoHistoria();
					lth_dataTurnDAO     = DaoCreator.getTurnoHistoriaDAO(ldm_manager);

					loth_dataTurn.setIdTurnoHistoria(ll_idTurnoHistoria);

					loth_dataTurn = lth_dataTurnDAO.findById(loth_dataTurn);

					if(loth_dataTurn != null)
					{
						MotivoTramite lmt_motivo;

						lmt_motivo = new MotivoTramite();

						if(aorc_rc.isIndSegregacion())
							CalcularRevision(
							    aorc_rc.getTotalMatriculasRevision(), aorc_rc.getTotalRevision(), ldm_manager,
							    aorc_rc.isDivisionMaterial()
							    ? ConstanteCommon.PORCENTAJE_REVISION_MATRICULAS_DIVISION_MATERIAL
							    : ConstanteCommon.PORCENTAJE_REVISION_MATRICULAS
							);

						lmt_motivo.setIdEtapaOrigen(EtapaCommon.ID_ETAPA_CALIFICACION);
						lmt_motivo.setIdMotivo(MotivoTramiteCommon.DIGITADOR_MASIVO);

						lmt_motivo = DaoCreator.getMotivoTramiteDAO(ldm_manager).findById(lmt_motivo);

						if(lmt_motivo != null)
						{
							loth_dataTurn.setEstadoActividad(EstadoCommon.TERMINADA);
							loth_dataTurn.setMotivo(lmt_motivo.getDescripcion());
							loth_dataTurn.setIdMotivo(NumericUtils.getLongWrapper(lmt_motivo.getIdMotivo()));
							loth_dataTurn.setObservaciones(aorc_rc.getObservaciones());
							loth_dataTurn.setIdUsuarioModificacion(ls_usuario);
							loth_dataTurn.setIpModificacion(ls_ip);

							lth_dataTurnDAO.insertOrUpdate(loth_dataTurn, false);

							DaoCreator.getProcedimientosDAO(ldm_manager).spCreateStage(loth_dataTurn);
						}
					}

					Collection<AreaPredio> lcap_cap;
					lcap_cap = aorc_rc.getMatriculasSegregadas();

					if(CollectionUtils.isValidCollection(lcap_cap))
					{
						AccPredioRegistroDAO lapr_DAO;
						boolean              lb_b;
						AccPredioRegistro    lapr_pr;

						lapr_DAO     = DaoCreator.getAccPredioRegistroDAO(ldm_manager);
						lb_b         = aorc_rc.isEnvioCalificador();

						for(AreaPredio lap_tmp : lcap_cap)
						{
							if(lap_tmp != null)
							{
								lapr_pr = new AccPredioRegistro();

								lapr_pr.setIdUsuarioModificacion(ls_usuario);
								lapr_pr.setIpModificacion(ls_ip);
								lapr_pr.setRevision(null);
								lapr_pr.setIdPredioRegistro(lap_tmp.getIdPredioRegistro());

								lapr_DAO.actualizarRevision(lapr_pr, lb_b);
							}
						}
					}
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("enviarADigitador", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lorc_dataIntervinientes;
	}

	/**
	 * Método encargado de enviar el proceso de desenglobes a digitador masivo.
	 *
	 * @param arc_data Objeto que contiene la información a guardar.
	 * @param as_userId Variable de tipo String que contiene el id del usuario que está realizando la acción.
	 * @param as_remoteIp Variable de tipo String que contiene la ip del usuario que está realizando la acción.
	 * @throws B2BException
	 */
	public synchronized void enviarADigitadorDesenglobe(
	    RegistroCalificacion arc_data, String as_userId, String as_remoteIp
	)
	    throws B2BException
	{
		DAOManager ldm_manager;

		ldm_manager = DaoManagerFactory.getDAOManager();

		try
		{
			if(arc_data != null)
			{
				Long ll_idTurnoHistoria;

				ll_idTurnoHistoria = arc_data.getIdTurnoHistoria();

				if(NumericUtils.isValidLong(ll_idTurnoHistoria))
				{
					TurnoHistoria    lth_turnoHistoria;
					TurnoHistoriaDAO lth_turnoHistoria_DAO;

					lth_turnoHistoria         = new TurnoHistoria();
					lth_turnoHistoria_DAO     = DaoCreator.getTurnoHistoriaDAO(ldm_manager);

					lth_turnoHistoria.setIdTurnoHistoria(ll_idTurnoHistoria);

					lth_turnoHistoria = lth_turnoHistoria_DAO.findById(lth_turnoHistoria);

					if(lth_turnoHistoria != null)
					{
						MotivoTramite lmt_motivo;

						lmt_motivo = new MotivoTramite();

						lmt_motivo.setIdEtapaOrigen(EtapaCommon.ID_ETAPA_CALIFICACION);
						lmt_motivo.setIdMotivo(MotivoTramiteCommon.REALIZAR_REGISTRO_DESENGLOBE);

						lmt_motivo = DaoCreator.getMotivoTramiteDAO(ldm_manager).findById(lmt_motivo);

						if(lmt_motivo != null)
						{
							lth_turnoHistoria.setEstadoActividad(EstadoCommon.TERMINADA);
							lth_turnoHistoria.setMotivo(lmt_motivo.getDescripcion());
							lth_turnoHistoria.setIdMotivo(NumericUtils.getLongWrapper(lmt_motivo.getIdMotivo()));
							lth_turnoHistoria.setObservaciones(arc_data.getObservaciones());
							lth_turnoHistoria.setIdUsuarioModificacion(as_userId);
							lth_turnoHistoria.setIpModificacion(as_remoteIp);

							lth_turnoHistoria_DAO.insertOrUpdate(lth_turnoHistoria, false);

							DaoCreator.getProcedimientosDAO(ldm_manager).spCreateStage(lth_turnoHistoria);
						}

						arc_data.setSalvar(true);
						arc_data.setDesenglobe(true);

						genereteFileRegistro(ldm_manager, arc_data, false, as_userId, as_remoteIp, false);
					}
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("enviarADigitadorDesenglobe", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}
	}

	/**
	 * Método encargado de terminar el proceso de calificación englobes y enviarlo a la etapa 140
	 *
	 * @param arc_data Objeto que contiene la información a guardar.
	 * @throws B2BException
	 */
	public synchronized void enviarADigitadorEnglobes(RegistroCalificacion arc_data)
	    throws B2BException
	{
		DAOManager ldm_manager;

		ldm_manager = DaoManagerFactory.getDAOManager();

		try
		{
			if(arc_data != null)
			{
				if(arc_data.isCierreFolio() && arc_data.isSalvarCierreFolio())
					salvarCierreFolio(arc_data, ldm_manager);

				Long ll_idTurnoHistoria;

				ll_idTurnoHistoria = arc_data.getIdTurnoHistoria();

				if(NumericUtils.isValidLong(ll_idTurnoHistoria))
				{
					TurnoHistoria    lth_turnoHistoria;
					TurnoHistoriaDAO lth_turnoHistoria_DAO;

					lth_turnoHistoria         = new TurnoHistoria();
					lth_turnoHistoria_DAO     = DaoCreator.getTurnoHistoriaDAO(ldm_manager);

					lth_turnoHistoria.setIdTurnoHistoria(ll_idTurnoHistoria);

					lth_turnoHistoria = lth_turnoHistoria_DAO.findById(lth_turnoHistoria);

					if(lth_turnoHistoria != null)
					{
						com.bachue.snr.prosnr01.dao.acc.AnotacionPredioDAO          lap_DAO;
						com.bachue.snr.prosnr01.dao.acc.AnotacionPredioCiudadanoDAO lapc_DAO;
						int                                                         li_idAnotacion;
						Long                                                        ll_idMatricula;
						RegistroCalificacion                                        lrc_dataAnotacionesHerdar;
						String                                                      ls_idTurno;
						String                                                      ls_idCirculo;
						String                                                      ls_ipRemote;
						String                                                      ls_idUsuario;

						lap_DAO                       = DaoCreator.getAccAnotacionPredioDAO(ldm_manager);
						lapc_DAO                      = DaoCreator.getAccAnotacionPredioCiudadanoDAO(ldm_manager);
						li_idAnotacion                = 1;
						ll_idMatricula                = arc_data.getIdMatricula();
						lrc_dataAnotacionesHerdar     = arc_data.getMatriculasAHeredar();
						ls_idTurno                    = arc_data.getTurno();
						ls_idCirculo                  = arc_data.getIdCirculo();
						ls_idUsuario                  = arc_data.getUserId();
						ls_ipRemote                   = arc_data.getIpAddress();

						if(lrc_dataAnotacionesHerdar != null)
						{
							Collection<RegistroCalificacion> lcrc_matriculasHeredar;

							lcrc_matriculasHeredar = lrc_dataAnotacionesHerdar.getAllMatriculas();

							if(CollectionUtils.isValidCollection(lcrc_matriculasHeredar))
							{
								boolean lb_devolucion;

								lb_devolucion = arc_data.isDevolucion();

								if(!StringUtils.isValidString(ls_idTurno))
									ls_idTurno = lth_turnoHistoria.getIdTurno();

								for(RegistroCalificacion lrc_tmp : lcrc_matriculasHeredar)
								{
									if(lrc_tmp.isRevision())
									{
										if(!lb_devolucion)
										{
											AnotacionPredioCiudadano                              lapc_apc;
											com.bachue.snr.prosnr01.model.sdb.acc.AnotacionPredio loap_ap;
											com.bachue.snr.prosnr01.model.sdb.acc.AnotacionPredio loap_identity;
											Collection<AnotacionPredioCiudadano>                  lcapc_apc;
											Long                                                  ll_idAnotacionHeredada;

											loap_ap                    = new com.bachue.snr.prosnr01.model.sdb.acc.AnotacionPredio();
											lapc_apc                   = new AnotacionPredioCiudadano();
											ll_idAnotacionHeredada     = lrc_tmp.getIdAnotacion();

											loap_ap.setIdTurnoHistoria(NumericUtils.getLong(ll_idTurnoHistoria));
											loap_ap.setIdEstadoRegistro(EstadoCommon.INACTIVO);
											loap_ap.setIdCirculo(ls_idCirculo);
											loap_ap.setIdMatricula(ll_idMatricula);
											loap_ap.setIdSolicitud(lth_turnoHistoria.getIdSolicitud());
											loap_ap.setIdAnotacion(NumericUtils.getLongWrapper(li_idAnotacion));
											loap_ap.setOrden(NumericUtils.getBigDecimal(li_idAnotacion));
											loap_ap.setFechaRegistro(lrc_tmp.getFechaRegistro());
											loap_ap.setIdDocumento(lrc_tmp.getIdDocumento());
											loap_ap.setIdNaturalezaJuridica(lrc_tmp.getCodActo());
											loap_ap.setVersion(1);
											loap_ap.setVersionDocumento(1);
											loap_ap.setIdTipoAnotacion(lrc_tmp.getIdTipoAnotacion());
											loap_ap.setFechaRadicacion(lrc_tmp.getFechaRadicacion());
											loap_ap.setRadicacion(lrc_tmp.getRadicacion());
											loap_ap.setIdEstadoAnotacion(lrc_tmp.getCodEstadoAnotacion());
											loap_ap.setIdUsuarioCreacion(ls_idUsuario);
											loap_ap.setIpCreacion(ls_ipRemote);
											loap_ap.setIdTurno(ls_idTurno);

											loap_identity = lap_DAO.insert(loap_ap);

											li_idAnotacion++;

											lapc_apc.setIdCirculo(lrc_tmp.getIdCirculo());
											lapc_apc.setIdMatricula(NumericUtils.getLong(lrc_tmp.getIdMatricula()));
											lapc_apc.setIdAnotacion(NumericUtils.getLong(ll_idAnotacionHeredada));

											lcapc_apc = DaoCreator.getAnotacionPredioCiudadanoDAO(ldm_manager)
													                  .consultaPredioCiudadanos(lapc_apc);

											if(CollectionUtils.isValidCollection(lcapc_apc) && (loap_identity != null))
											{
												String ls_idAnotacionPredio;

												ls_idAnotacionPredio = loap_identity.getIdAnotacionPredio();

												if(StringUtils.isValidString(ls_idAnotacionPredio))
												{
													int li_idAnotacionCiudadano;

													li_idAnotacionCiudadano = 1;

													for(AnotacionPredioCiudadano lopc_apc : lcapc_apc)
													{
														com.bachue.snr.prosnr01.model.sdb.acc.AnotacionPredioCiudadano loapc_tmp;

														loapc_tmp = new com.bachue.snr.prosnr01.model.sdb.acc.AnotacionPredioCiudadano();

														loapc_tmp.setIdCirculo(ls_idCirculo);
														loapc_tmp.setIdMatricula(NumericUtils.getLong(ll_idMatricula));
														loapc_tmp.setIdAnotacion(
														    NumericUtils.getLong(li_idAnotacionCiudadano++)
														);
														loapc_tmp.setIdPersona(lopc_apc.getIdPersona());
														loapc_tmp.setRolPersona(lopc_apc.getRolPersona());
														loapc_tmp.setPropietario(lopc_apc.getPropietario());
														loapc_tmp.setPorcentajeParticipacion(
														    lopc_apc.getPorcentajeParticipacion()
														);
														loapc_tmp.setIdInteresadaRrr(lopc_apc.getIdInteresadaRrr());
														loapc_tmp.setIdUsuarioCreacion(ls_idUsuario);
														loapc_tmp.setIpCreacion(ls_ipRemote);
														loapc_tmp.setIdTurno(ls_idTurno);
														loapc_tmp.setIdAnotacionPredio(ls_idAnotacionPredio);

														lapc_DAO.insert(loapc_tmp);
													}
												}
											}
										}
									}
								}
							}
						}

						{
							com.bachue.snr.prosnr01.model.sdb.acc.AnotacionPredio loap_ap;
							Long                                                  ll_idMatriculaMatriz;
							String                                                ls_idCirculoMatriz;
							final String                                          ls_idNaturalezaEnglobe;

							loap_ap                    = new com.bachue.snr.prosnr01.model.sdb.acc.AnotacionPredio();
							ll_idMatriculaMatriz       = arc_data.getIdMatriculaMatriz();
							ls_idCirculoMatriz         = arc_data.getIdCirculoMatriz();
							ls_idNaturalezaEnglobe     = "0919";

							loap_ap.setIdCirculo(ls_idCirculoMatriz);
							loap_ap.setIdMatricula(ll_idMatriculaMatriz);
							loap_ap.setIdNaturalezaJuridica(ls_idNaturalezaEnglobe);

							loap_ap = lap_DAO.findByCirculoMatriculaActo(loap_ap);

							if(loap_ap != null)
							{
								loap_ap.setIdUsuarioModificacion(null);
								loap_ap.setIdUsuarioCreacion(ls_idUsuario);
								loap_ap.setIpModificacion(null);
								loap_ap.setIpCreacion(ls_ipRemote);
								loap_ap.setIdCirculo(ls_idCirculo);
								loap_ap.setIdMatricula(ll_idMatricula);
								loap_ap.setIdAnotacion(NumericUtils.getLongWrapper(li_idAnotacion));
								loap_ap.setOrden(NumericUtils.getBigDecimal(li_idAnotacion));
								loap_ap.setRevisionCalificador(null);
								loap_ap.setIndicadorPredioCiudadano(null);

								loap_ap = lap_DAO.insert(loap_ap);

								if(loap_ap != null)
								{
									String ls_idAnotacionPredio;

									ls_idAnotacionPredio = loap_ap.getIdAnotacionPredio();

									if(StringUtils.isValidString(ls_idAnotacionPredio))
									{
										com.bachue.snr.prosnr01.model.sdb.acc.AnotacionPredio lap_anotacion;

										lap_anotacion = new com.bachue.snr.prosnr01.model.sdb.acc.AnotacionPredio();

										lap_anotacion.setIdCirculo(ls_idCirculoMatriz);
										lap_anotacion.setIdMatricula(ll_idMatriculaMatriz);
										lap_anotacion.setIdNaturalezaJuridica(ls_idNaturalezaEnglobe);

										lap_anotacion = lap_DAO.findByCirculoMatriculaActo(lap_anotacion);

										if(lap_anotacion != null)
										{
											com.bachue.snr.prosnr01.model.sdb.acc.AnotacionPredioCiudadano             lapc_consulta;
											Collection<com.bachue.snr.prosnr01.model.sdb.acc.AnotacionPredioCiudadano> lcapc_anotaciones;

											lapc_consulta = new com.bachue.snr.prosnr01.model.sdb.acc.AnotacionPredioCiudadano();

											lapc_consulta.setIdAnotacionPredio(lap_anotacion.getIdAnotacionPredio());

											lcapc_anotaciones = lapc_DAO.findByIdAnotacion(lapc_consulta);

											if(CollectionUtils.isValidCollection(lcapc_anotaciones))
											{
												Iterator<com.bachue.snr.prosnr01.model.sdb.acc.AnotacionPredioCiudadano> liapc_iterator;
												int                                                                      li_count;

												liapc_iterator     = lcapc_anotaciones.iterator();
												li_count           = 1;

												while(liapc_iterator.hasNext())
												{
													com.bachue.snr.prosnr01.model.sdb.acc.AnotacionPredioCiudadano lapc_iterador;

													lapc_iterador = liapc_iterator.next();

													if(lapc_iterador != null)
													{
														lapc_iterador.setIdCirculo(ls_idCirculo);
														lapc_iterador.setIdMatricula(
														    NumericUtils.getLong(ll_idMatricula)
														);
														lapc_iterador.setIdAnotacionPredio(ls_idAnotacionPredio);
														lapc_iterador.setIdAnotacion(NumericUtils.getLong(li_count++));
														lapc_iterador.setIdUsuarioCreacion(ls_idUsuario);
														lapc_iterador.setIpCreacion(ls_ipRemote);

														lapc_DAO.insert(lapc_iterador);
													}
												}
											}
										}
									}
								}
							}
						}

						{
							MotivoTramite lmt_motivo;

							lmt_motivo = new MotivoTramite();

							lmt_motivo.setIdEtapaOrigen(EtapaCommon.ID_ETAPA_CALIFICACION);
							lmt_motivo.setIdMotivo(MotivoTramiteCommon.DIGITADOR_MASIVO);

							lmt_motivo = DaoCreator.getMotivoTramiteDAO(ldm_manager).findById(lmt_motivo);

							if(lmt_motivo != null)
							{
								lth_turnoHistoria.setEstadoActividad(EstadoCommon.TERMINADA);
								lth_turnoHistoria.setMotivo(lmt_motivo.getDescripcion());
								lth_turnoHistoria.setIdMotivo(NumericUtils.getLongWrapper(lmt_motivo.getIdMotivo()));
								lth_turnoHistoria.setObservaciones(arc_data.getObservaciones());
								lth_turnoHistoria.setIdUsuarioModificacion(ls_idUsuario);
								lth_turnoHistoria.setIpModificacion(ls_ipRemote);

								lth_turnoHistoria_DAO.insertOrUpdate(lth_turnoHistoria, false);

								DaoCreator.getProcedimientosDAO(ldm_manager).spCreateStage(lth_turnoHistoria);
							}
						}
					}
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("enviarADigitadorEnglobes", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}
	}

	/**
	 * Método encargado de hacer las validaciones para el posterior envio al aprobardor.
	 *
	 * @param arc_data Objeto que contiene la información a guardar.
	 * @throws B2BException
	 */
	public synchronized void enviarADigitadorEnglobesDevolucion(
	    RegistroCalificacion arc_data, String as_userId, String as_remoteIp
	)
	    throws B2BException
	{
		if(arc_data != null)
		{
			DAOManager ldm_manager;

			ldm_manager = DaoManagerFactory.getDAOManager();

			try
			{
				if(arc_data.isCierreFolio() && arc_data.isSalvarCierreFolio())
					salvarCierreFolio(arc_data, ldm_manager);

				Long ll_idTurnoHistoria;

				ll_idTurnoHistoria = arc_data.getIdTurnoHistoria();

				if(NumericUtils.isValidLong(ll_idTurnoHistoria))
				{
					TurnoHistoria    lth_turnoHistoria;
					TurnoHistoriaDAO lth_turnoHistoria_DAO;

					lth_turnoHistoria         = new TurnoHistoria();
					lth_turnoHistoria_DAO     = DaoCreator.getTurnoHistoriaDAO(ldm_manager);

					lth_turnoHistoria.setIdTurnoHistoria(ll_idTurnoHistoria);

					lth_turnoHistoria = lth_turnoHistoria_DAO.findById(lth_turnoHistoria);

					if(lth_turnoHistoria != null)
					{
						{
							Collection<RegistroCalificacion> lcrc_anotaciones;

							lcrc_anotaciones = arc_data.getAnotacionesHeredar();

							if(CollectionUtils.isValidCollection(lcrc_anotaciones))
							{
								com.bachue.snr.prosnr01.dao.acc.AnotacionPredioDAO          laap_DAO;
								com.bachue.snr.prosnr01.dao.acc.AnotacionPredioCiudadanoDAO laapc_DAO;
								Iterator<RegistroCalificacion>                              lirc_iterator;

								laap_DAO          = DaoCreator.getAccAnotacionPredioDAO(ldm_manager);
								laapc_DAO         = DaoCreator.getAccAnotacionPredioCiudadanoDAO(ldm_manager);
								lirc_iterator     = lcrc_anotaciones.iterator();

								while(lirc_iterator.hasNext())
								{
									RegistroCalificacion lrc_iterador;

									lrc_iterador = lirc_iterator.next();

									if((lrc_iterador != null) && !lrc_iterador.isRevision())
									{
										String ls_idAnotacionPredio;

										ls_idAnotacionPredio = lrc_iterador.getIdAnotacionPredio();

										if(StringUtils.isValidString(ls_idAnotacionPredio))
										{
											laapc_DAO.deleteIntervinientes(ls_idAnotacionPredio);
											laap_DAO.deleteById(ls_idAnotacionPredio);
										}
									}
								}
							}

							{
								Collection<DireccionPredioAcc> lcdpa_direcciones;
								DireccionPredioAccDAO          ldpa_DAO;

								lcdpa_direcciones     = arc_data.getDireccionesAcc();
								ldpa_DAO              = DaoCreator.getDireccionPredioAccDAO(ldm_manager);

								if(CollectionUtils.isValidCollection(lcdpa_direcciones))
								{
									Iterator<DireccionPredioAcc> lidpa_iterator;

									lidpa_iterator = lcdpa_direcciones.iterator();

									while(lidpa_iterator.hasNext())
									{
										DireccionPredioAcc ldpa_direccion;

										ldpa_direccion = lidpa_iterator.next();

										if((ldpa_direccion != null) && !ldpa_direccion.isSeleccionado())
										{
											String ls_idDireccionPredio;

											ls_idDireccionPredio = ldpa_direccion.getIdDireccionPredio();

											if(StringUtils.isValidString(ls_idDireccionPredio))
												ldpa_DAO.deleteById(ls_idDireccionPredio);
										}
									}
								}
							}
						}

						{
							MotivoTramite lmt_motivo;

							lmt_motivo = new MotivoTramite();

							lmt_motivo.setIdEtapaOrigen(EtapaCommon.ID_ETAPA_CALIFICACION);
							lmt_motivo.setIdMotivo(MotivoTramiteCommon.REGISTRO_DIGITADOR_MASIVO);

							lmt_motivo = DaoCreator.getMotivoTramiteDAO(ldm_manager).findById(lmt_motivo);

							if(lmt_motivo != null)
							{
								lth_turnoHistoria.setEstadoActividad(EstadoCommon.TERMINADA);
								lth_turnoHistoria.setMotivo(lmt_motivo.getDescripcion());
								lth_turnoHistoria.setIdMotivo(NumericUtils.getLongWrapper(lmt_motivo.getIdMotivo()));
								lth_turnoHistoria.setObservaciones(arc_data.getObservaciones());
								lth_turnoHistoria.setIdUsuarioModificacion(arc_data.getUserId());
								lth_turnoHistoria.setIpModificacion(as_remoteIp);

								lth_turnoHistoria_DAO.insertOrUpdate(lth_turnoHistoria, false);

								DaoCreator.getProcedimientosDAO(ldm_manager).spCreateStage(lth_turnoHistoria);
							}
						}

						arc_data.setSalvar(true);
						arc_data.setEnglobe(true);
						genereteFileRegistro(ldm_manager, arc_data, false, as_userId, as_remoteIp, false);
					}
				}
			}
			catch(B2BException lb2be_e)
			{
				ldm_manager.setRollbackOnly();

				clh_LOGGER.error("enviarADigitadorEnglobesDevolucion", lb2be_e);

				throw lb2be_e;
			}
			finally
			{
				ldm_manager.close();
			}
		}
	}

	/**
	 * Método encargado de validar si el turno es un proceso de baldios.
	 *
	 * @param as_idTurno Variable de tipo String que contiene el id del turno en trámite.
	 * @return Variable booleana que identifica si el proceso corresponde a baldios.
	 * @throws B2BException
	 */
	public synchronized boolean esProcesoBaldios(String as_idTurno)
	    throws B2BException
	{
		boolean    lb_return;
		DAOManager ldm_manager;

		lb_return       = false;
		ldm_manager     = DaoManagerFactory.getDAOManager();

		try
		{
			if(StringUtils.isValidString(as_idTurno))
			{
				Turno lth_turno;

				lth_turno = new Turno();

				lth_turno.setIdTurno(as_idTurno);

				lth_turno = DaoCreator.getTurnoDAO(ldm_manager).findById(lth_turno);

				if(lth_turno != null)
				{
					String ls_idCirculo;
					String ls_idSolicitud;

					ls_idCirculo       = lth_turno.getIdCirculo();
					ls_idSolicitud     = lth_turno.getIdSolicitud();

					if(StringUtils.isValidString(ls_idCirculo) && StringUtils.isValidString(ls_idSolicitud))
					{
						Acto             la_param;
						Collection<Acto> lca_actos;

						la_param = new Acto();

						la_param.setIdCirculo(ls_idCirculo);
						la_param.setIdSolicitud(ls_idSolicitud);

						lca_actos = DaoCreator.getActoDAO(ldm_manager).findByIdSolicitudCirculo(la_param);

						if(CollectionUtils.isValidCollection(lca_actos))
						{
							Map<String, String> lmss_baldios;

							lmss_baldios = generarCodigos(ConstanteCommon.TIPOS_ACTOS_BALDIOS, ldm_manager);

							if(CollectionUtils.isValidCollection(lmss_baldios))
							{
								Iterator<Acto> lia_iterator;

								lia_iterator = lca_actos.iterator();

								while(lia_iterator.hasNext() && !lb_return)
								{
									Acto la_acto;

									la_acto = lia_iterator.next();

									if(la_acto != null)
									{
										String ls_idTipoActo;

										ls_idTipoActo     = la_acto.getIdTipoActo();
										lb_return         = StringUtils.isValidString(ls_idTipoActo)
												&& lmss_baldios.containsKey(ls_idTipoActo);
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

			clh_LOGGER.error("esProcesoBaldiosTH", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lb_return;
	}

	/**
	 *
	 * @param ath_turnoHistoria Objeto de tipo TurnoHistoria del cual se extraerán los datos para realizar las consultas a la BD
	 * @return boolean indicando si es la segunda vez en Etapa 80
	 * @throws B2BException
	 */
	public boolean esSegundaVezCalificacionLindero(TurnoHistoria ath_turnoHistoria)
	    throws B2BException
	{
		DAOManager ldm_manager;
		boolean    lb_mostrar;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		lb_mostrar      = false;

		try
		{
			if(ath_turnoHistoria != null)
			{
				TurnoHistoria    lth_turnoHistoria;
				TurnoHistoriaDAO lthd_DAO;

				lth_turnoHistoria     = new TurnoHistoria();
				lthd_DAO              = DaoCreator.getTurnoHistoriaDAO(ldm_manager);

				lth_turnoHistoria.setIdTurno(ath_turnoHistoria.getIdTurno());

				lth_turnoHistoria = lthd_DAO.findByIdTurno(lth_turnoHistoria, true);

				if(lth_turnoHistoria != null)
				{
					BigDecimal lbd_turnoHistoriaAnt;

					lbd_turnoHistoriaAnt = lth_turnoHistoria.getIdAnterior();

					if(NumericUtils.isValidBigDecimal(lbd_turnoHistoriaAnt))
					{
						lth_turnoHistoria = lthd_DAO.findByIdAnterior(lth_turnoHistoria);

						if(lth_turnoHistoria != null)
						{
							long ll_etapaTurnoHistoria;

							ll_etapaTurnoHistoria = NumericUtils.getLong(lth_turnoHistoria.getIdEtapa());

							if(
							    (ll_etapaTurnoHistoria == EtapaCommon.ID_ETAPA_APROBACION)
								    || (ll_etapaTurnoHistoria == EtapaCommon.ID_ETAPA_CONFRONTACION_CORRECTIVA)
								    || (ll_etapaTurnoHistoria == EtapaCommon.ID_ETAPA_DIGITADOR_MASIVO)
							)
								lb_mostrar = true;
						}
					}
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("mostrarNuevaLinderos", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lb_mostrar;
	}

	/**
	 * Método para encontrar la información especifica de las matriculas
	 *
	 * @param aorc_rc
	 *            Objeto con información necesaria para buscar las matriculas
	 * @return
	 * @throws B2BException
	 */
	public synchronized RegistroCalificacion findDetalleMatriculas(RegistroCalificacion aorc_rc)
	    throws B2BException
	{
		DAOManager                                         ldm_manager;
		RegistroCalificacion                               lorc_matriculas;
		RegistroCalificacion                               lorc_dataDocumento;
		LinkedHashMap<String, LinkedHashMap<Long, String>> llhm_dataParcial;
		Collection<RegistroCalificacion>                   lcrc_dataPredio;
		Collection<RegistroCalificacion>                   lcrc_dataPredioCiudadano;
		Collection<AnotacionCancelacion>                   lcrc_anotacionesCancelacion;
		Collection<RegistroCalificacion>                   lcrc_dataFinal;
		RegistroCalificacionDAO                            lorcd_rcd;
		AnotacionCancelacionDAO                            loacd_dao;
		Long                                               ll_idAnotacion;
		String                                             ls_idAnotacion;
		String                                             ls_idGrupo;
		AnotacionCancelacion                               loac_ac;
		TurnoHistoria                                      lth_turnoHistoria;
		TurnoHistoriaDAO                                   lthd_DAO;

		ldm_manager                     = DaoManagerFactory.getDAOManager();
		lorcd_rcd                       = DaoCreator.getRegistroCalificacionDAO(ldm_manager);
		loacd_dao                       = DaoCreator.getAnotacionCancelacionDAO(ldm_manager);
		lcrc_dataFinal                  = new ArrayList<RegistroCalificacion>();
		lorc_matriculas                 = new RegistroCalificacion();
		lcrc_anotacionesCancelacion     = new ArrayList<AnotacionCancelacion>();
		lthd_DAO                        = DaoCreator.getTurnoHistoriaDAO(ldm_manager);
		lcrc_dataPredio                 = null;

		try
		{
			if(aorc_rc != null)
			{
				LinkedHashMap<String, String> llhmss_data;
				Long                          ll_idTurnoHistoria;
				boolean                       lb_indVinculado;
				String                        ls_turno;

				llhmss_data            = new LinkedHashMap<String, String>();
				ll_idTurnoHistoria     = aorc_rc.getIdTurnoHistoria();
				lth_turnoHistoria      = new TurnoHistoria();
				lb_indVinculado        = aorc_rc.isIndVinculado();
				ls_turno               = null;

				if(!NumericUtils.isValidLong(ll_idTurnoHistoria))
					throw new B2BException(ErrorKeys.NO_SE_ENCONTRO_ID_TURNO_HISTORIA);

				lth_turnoHistoria.setIdTurnoHistoria(ll_idTurnoHistoria);

				lth_turnoHistoria = lthd_DAO.findById(lth_turnoHistoria);

				if(lth_turnoHistoria != null)
				{
					BigDecimal lbd_idTurnoHistoriaAnt;

					lbd_idTurnoHistoriaAnt     = lth_turnoHistoria.getIdAnterior();
					ls_turno                   = lth_turnoHistoria.getIdTurno();

					if(NumericUtils.isValidBigDecimal(lbd_idTurnoHistoriaAnt))
					{
						Long          ll_idTurnoAnterior;
						TurnoHistoria lth_turnoAnt140;

						ll_idTurnoAnterior     = NumericUtils.getLongWrapper(lbd_idTurnoHistoriaAnt.longValue());
						lth_turnoAnt140        = new TurnoHistoria();

						lth_turnoAnt140.setIdTurnoHistoria(ll_idTurnoAnterior);

						lth_turnoAnt140 = lthd_DAO.findById(lth_turnoAnt140);

						if(lth_turnoAnt140 != null)
						{
							BigDecimal lbd_idEtapa;
							BigDecimal lbd_idTurnoHistoriaAnt80;

							lbd_idEtapa                  = lth_turnoAnt140.getIdEtapa();
							lbd_idTurnoHistoriaAnt80     = lth_turnoAnt140.getIdAnterior();

							if(
							    (lbd_idEtapa.intValue() == 140)
								    && NumericUtils.isValidBigDecimal(lbd_idTurnoHistoriaAnt80)
							)
							{
								Long          ll_idTurnoAnterior80;
								TurnoHistoria lth_turnoAnt80;

								ll_idTurnoAnterior80     = NumericUtils.getLongWrapper(
									    lbd_idTurnoHistoriaAnt80.longValue()
									);
								lth_turnoAnt80           = new TurnoHistoria();

								lth_turnoAnt80.setIdTurnoHistoria(ll_idTurnoAnterior80);

								lth_turnoAnt80 = lthd_DAO.findById(lth_turnoAnt80);

								if(lth_turnoAnt80 != null)
								{
									BigDecimal lbd_idEtapa80;
									lbd_idEtapa80 = lth_turnoAnt80.getIdEtapa();

									if(lbd_idEtapa80.intValue() == 80)
										aorc_rc.setIdTurnoHistoria(lth_turnoAnt80.getIdTurnoHistoria());
								}
							}
						}
					}
				}

				if(lb_indVinculado && StringUtils.isValidString(ls_turno))
				{
					TurnoDerivado             ltd_turnosDerivado;
					Collection<TurnoDerivado> lctd_turnoDerivados;

					ltd_turnosDerivado = new TurnoDerivado();

					ltd_turnosDerivado.setIdTurnoPadre(ls_turno);

					lctd_turnoDerivados = DaoCreator.getTurnoDerivadoDAO(ldm_manager)
							                            .findByIdTurnoPadreVinculados(ltd_turnosDerivado);

					if(CollectionUtils.isValidCollection(lctd_turnoDerivados))
					{
						Iterator<TurnoDerivado> litd_itd;
						boolean                 lb_b;
						String                  ls_tunoHijo;

						litd_itd        = lctd_turnoDerivados.iterator();
						lb_b            = false;
						ls_tunoHijo     = null;

						while(litd_itd.hasNext() && !lb_b)
						{
							TurnoDerivado ltd_td;
							ltd_td = litd_itd.next();

							if(ltd_td != null)
							{
								lb_b            = true;
								ls_tunoHijo     = ltd_td.getIdTurnoHijo();
							}
						}

						if(StringUtils.isValidString(ls_tunoHijo))
						{
							lth_turnoHistoria.setIdTurno(ls_tunoHijo);

							lth_turnoHistoria = lthd_DAO.findByIdTurno(lth_turnoHistoria, true);

							if(lth_turnoHistoria != null)
								aorc_rc.setCodActo(
								    "'" + ll_idTurnoHistoria + "'," + "'" + lth_turnoHistoria.getIdTurnoHistoria()
								    + "'"
								);
						}
					}
				}

				lcrc_dataPredio     = lorcd_rcd.findDataPredio(aorc_rc);

				llhm_dataParcial = lorcd_rcd.findAnotacionesParciales(aorc_rc);

				if(llhm_dataParcial != null)
				{
					Collection<Long> lcl_dataParcial;

					lcl_dataParcial = new ArrayList<Long>();

					/**
					 * FORMATO Comentar lambda antes de formatear
					 */

					llhm_dataParcial.forEach((ls_idAnotacionPredio, v) -> v
					        .forEach((ll_anotacion, ls_estado) -> lcl_dataParcial.add(ll_anotacion)));
					llhm_dataParcial.forEach((ls_idAnotacionPredio, v) -> v
					        .forEach((ll_anotacion, ls_estado) -> llhmss_data.put(ls_idAnotacionPredio, ls_estado)));
					lorc_matriculas.setAnotacionesParciales(lcl_dataParcial);
				}

				if(CollectionUtils.isValidCollection(lcrc_dataPredio))
				{
					for(RegistroCalificacion lorc_rc : lcrc_dataPredio)
					{
						loac_ac                      = new AnotacionCancelacion();
						ls_idAnotacion               = lorc_rc.getIdAnotacionPredio();
						ls_idGrupo                   = lorc_rc.getIdNaturalezJuridica();
						lcrc_dataPredioCiudadano     = new ArrayList<RegistroCalificacion>();

						if(StringUtils.isValidString(ls_idAnotacion))
						{
							String ls_estado;

							ll_idAnotacion     = NumericUtils.getLongWrapper(ls_idAnotacion);
							ls_estado          = llhmss_data.get(ls_idAnotacion);

							if(
							    !(StringUtils.isValidString(ls_estado)
								    && ls_estado.equalsIgnoreCase(EstadoCommon.INACTIVO))
							)
							{
								lcrc_dataPredioCiudadano = lorcd_rcd.findDataPredioAnotacion(ll_idAnotacion);

								if(CollectionUtils.isValidCollection(lcrc_dataPredioCiudadano))
									lorc_rc.setAllMatriculas(lcrc_dataPredioCiudadano);

								if(StringUtils.getStringNotNull(ls_idGrupo).equalsIgnoreCase("0700"))
								{
									if(lth_turnoHistoria != null)
									{
										StringBuilder lsb_tmp;

										lsb_tmp = new StringBuilder();

										loac_ac.setIdMatricula(NumericUtils.getLong(aorc_rc.getIdMatricula()));
										loac_ac.setIdCirculo(aorc_rc.getIdCirculo());
										loac_ac.setIdTurno(lorc_rc.getTurno());
										loac_ac.setIdAnotacion(lorc_rc.getIdAnotacion());

										lcrc_anotacionesCancelacion = loacd_dao.findAllByCirculoMatriculaAnotacinoTurno(
											    loac_ac
											);

										if(CollectionUtils.isValidCollection(lcrc_anotacionesCancelacion))
										{
											for(AnotacionCancelacion loac_tmp : lcrc_anotacionesCancelacion)
												if(loac_tmp != null)
													lsb_tmp.append(loac_tmp.getIdAnotacion1() + ",");
										}

										lorc_rc.setAnotacionCancelacion(lsb_tmp.toString());
									}
								}

								lorc_dataDocumento = lorcd_rcd.findBasicosDocumento(lorc_rc.getIdTurnoHistoria());

								if(lorc_dataDocumento != null)
									lorc_rc.setDatosDocumento(lorc_dataDocumento);
								else
									lorc_rc.setDatosDocumento(lorcd_rcd.findBasicosDocumento(ll_idTurnoHistoria));

								String ls_codActo = lorc_rc.getCodActo();

								if(StringUtils.isValidString(ls_codActo) && ls_codActo.equalsIgnoreCase("0125"))
								{
									Acto                                                  la_acto;
									ActoDAO                                               la_DAO;
									com.bachue.snr.prosnr01.model.sdb.acc.AnotacionPredio lap_anotacionPredio;
									com.bachue.snr.prosnr01.dao.acc.AnotacionPredioDAO    lap_DAO;

									la_acto                 = new Acto();
									la_DAO                  = DaoCreator.getActoDAO(ldm_manager);
									lap_anotacionPredio     = new com.bachue.snr.prosnr01.model.sdb.acc.AnotacionPredio();
									lap_DAO                 = DaoCreator.getAccAnotacionPredioDAO(ldm_manager);

									la_acto.setIdTipoActo(ls_codActo);
									la_acto.setIdSolicitud(lorc_rc.getIdSolicitud());

									la_acto = la_DAO.findBySolicitudTipoActo(la_acto);

									if(la_acto != null)
									{
										String ls_idTipoAdquisicion;

										ls_idTipoAdquisicion = la_acto.getIdTipoAdquisicion();

										if(
										    StringUtils.isValidString(ls_idTipoAdquisicion)
											    && ls_idTipoAdquisicion.equalsIgnoreCase("3")
										)
										{
											lorc_rc.setComentario("NO ENAJENAR EN 2 AÑOS SIN AUTORIZACIÓN.");

											lap_anotacionPredio.setIdAnotacionPredio(lorc_rc.getIdAnotacionPredio());

											lap_anotacionPredio = lap_DAO.findById(lap_anotacionPredio);

											if(lap_anotacionPredio != null)
											{
												lap_anotacionPredio.setComentario(lorc_rc.getComentario());
												lap_anotacionPredio.setIdUsuarioModificacion(aorc_rc.getIdUsuario());
												lap_anotacionPredio.setIpModificacion(aorc_rc.getIpAddress());

												lap_DAO.modificiarAnotacionesPredio(lap_anotacionPredio, true);
											}
										}
									}
								}

								lcrc_dataFinal.add(lorc_rc);
							}
						}
					}

					if(CollectionUtils.isValidCollection(lcrc_dataFinal))
						lorc_matriculas.setAllMatriculas(lcrc_dataFinal);
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findDetalleMatriculas", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lorc_matriculas;
	}

	/**
	 * Método sobreescrito para solo traer los registros de grupo naturaleza juridica 0400
	 */
	public synchronized RegistroCalificacion findDocumentoByIdTurnoHistoria(
	    RegistroCalificacion arc_registroCalificacion
	)
	    throws B2BException
	{
		return findDocumentoByIdTurnoHistoria(arc_registroCalificacion, false);
	}

	/**
	 * Método para realizar transaccciones con la base de datos para encontrar un
	 * documento por el ID turno historia
	 *
	 * @param arc_registroCalificacion
	 *            Objeto donde se extrae el ID turno historia
	 * @return
	 * @throws B2BException
	 */
	public synchronized RegistroCalificacion findDocumentoByIdTurnoHistoria(
	    RegistroCalificacion arc_registroCalificacion, boolean ab_just0400Group
	)
	    throws B2BException
	{
		return findDocumentoByIdTurnoHistoria(arc_registroCalificacion, ab_just0400Group, false);
	}

	/**
	 * Método para realizar transaccciones con la base de datos para encontrar un
	 * documento por el ID turno historia
	 *
	 * @param arc_registroCalificacion
	 *            Objeto donde se extrae el ID turno historia
	 * @return
	 * @throws B2BException
	 */
	public synchronized RegistroCalificacion findDocumentoByIdTurnoHistoria(
	    RegistroCalificacion arc_registroCalificacion, boolean ab_just0400Group, boolean ab_anotacionCancelada
	)
	    throws B2BException
	{
		RegistroCalificacion lrc_return;
		DAOManager           ldm_manager;

		lrc_return      = null;
		ldm_manager     = DaoManagerFactory.getDAOManager();

		try
		{
			if(arc_registroCalificacion != null)
			{
				Long   ll_idTurnoHistoria;
				String ls_idSolicitud;

				ll_idTurnoHistoria     = arc_registroCalificacion.getIdTurnoHistoria();
				lrc_return             = new RegistroCalificacion();
				ls_idSolicitud         = null;

				if(NumericUtils.isValidLong(ll_idTurnoHistoria))
				{
					TurnoHistoria    lth_turnoHistoria;
					TurnoHistoriaDAO lthd_DAO;

					lth_turnoHistoria     = new TurnoHistoria();
					lthd_DAO              = DaoCreator.getTurnoHistoriaDAO(ldm_manager);

					lth_turnoHistoria.setIdTurnoHistoria(ll_idTurnoHistoria);
					lth_turnoHistoria = lthd_DAO.findById(lth_turnoHistoria);

					if(lth_turnoHistoria != null)
					{
						ls_idSolicitud = lth_turnoHistoria.getIdSolicitud();

						if(StringUtils.isValidString(ls_idSolicitud))
						{
							Solicitud    ls_solicitud;
							SolicitudDAO lsd_DAO;

							ls_solicitud     = new Solicitud();
							lsd_DAO          = DaoCreator.getSolicitudDAO(ldm_manager);

							ls_solicitud.setIdSolicitud(ls_idSolicitud);
							ls_solicitud = lsd_DAO.findById(ls_solicitud);

							if(ls_solicitud != null)
							{
								String ls_idDocumento;

								ls_idDocumento = ls_solicitud.getIdDocumento();

								if(StringUtils.isValidString(ls_idDocumento))
								{
									Documento    ld_documento;
									DocumentoDAO ldd_DAO;

									ld_documento     = new Documento();
									ldd_DAO          = DaoCreator.getDocumentoDAO(ldm_manager);

									ld_documento.setIdDocumento(ls_idDocumento);
									ld_documento = ldd_DAO.findById(ld_documento);

									if(ld_documento != null)
									{
										String     ls_idOficinaOrigen;
										BigDecimal lbd_version;

										ls_idOficinaOrigen     = ld_documento.getIdOficinaOrigen();
										lbd_version            = ld_documento.getVersion();

										if(StringUtils.isValidString(ls_idOficinaOrigen) && (lbd_version != null))
										{
											OficinaOrigen    loo_oficinaOrigen;
											OficinaOrigenDAO lood_DAO;

											loo_oficinaOrigen     = new OficinaOrigen();
											lood_DAO              = DaoCreator.getOficinaOrigenDAO(ldm_manager);

											loo_oficinaOrigen.setIdOficinaOrigen(ls_idOficinaOrigen);
											loo_oficinaOrigen.setVersion(lbd_version);

											loo_oficinaOrigen = lood_DAO.findById(loo_oficinaOrigen);

											if(loo_oficinaOrigen != null)
											{
												ld_documento.setIdDepartamento(loo_oficinaOrigen.getIdDepartamento());
												ld_documento.setIdMunicipio(loo_oficinaOrigen.getIdMunicipio());
											}
										}

										lrc_return.setDataDocumento(ld_documento);
									}
								}
							}
						}
					}
				}

				String ls_idCirculo;
				Long   ll_idMatricula;

				ls_idCirculo       = arc_registroCalificacion.getIdCirculo();
				ll_idMatricula     = arc_registroCalificacion.getIdMatricula();

				if(StringUtils.isValidString(ls_idCirculo) && NumericUtils.isValidLong(ll_idMatricula))
				{
					com.bachue.snr.prosnr01.model.sdb.acc.AnotacionPredio             lap_anotacionPredio;
					Collection<com.bachue.snr.prosnr01.model.sdb.acc.AnotacionPredio> lcap_anotacionPredio;
					com.bachue.snr.prosnr01.dao.acc.AnotacionPredioDAO                lap_DAO;

					lap_anotacionPredio      = new com.bachue.snr.prosnr01.model.sdb.acc.AnotacionPredio();
					lcap_anotacionPredio     = new ArrayList<com.bachue.snr.prosnr01.model.sdb.acc.AnotacionPredio>();
					lap_DAO                  = DaoCreator.getAccAnotacionPredioDAO(ldm_manager);

					lap_anotacionPredio.setIdCirculo(ls_idCirculo);
					lap_anotacionPredio.setIdMatricula(ll_idMatricula);

					lcap_anotacionPredio = lap_DAO.findByCirculoMatricula(lap_anotacionPredio);

					if(CollectionUtils.isValidCollection(lcap_anotacionPredio))
					{
						Long ll_idAnotacion;

						ll_idAnotacion = NumericUtils.getLongWrapper(0L);

						for(com.bachue.snr.prosnr01.model.sdb.acc.AnotacionPredio la_anotacion : lcap_anotacionPredio)
						{
							String ls_idSolicitudAnotacion;

							ls_idSolicitudAnotacion = la_anotacion.getIdSolicitud();

							if(
							    StringUtils.isValidString(ls_idSolicitudAnotacion)
								    && (ls_idSolicitudAnotacion.compareTo(ls_idSolicitud) == 0)
							)
							{
								Long ll_idAnotacionComparar;

								ll_idAnotacionComparar = la_anotacion.getIdAnotacion();

								if(NumericUtils.isValidLong(ll_idAnotacionComparar))
								{
									if(ll_idAnotacionComparar.compareTo(ll_idAnotacion) > 0)
										ll_idAnotacion = ll_idAnotacionComparar;
								}
							}
						}

						lrc_return.setTotalAnotaciones(NumericUtils.getInt(ll_idAnotacion));

						{
							Collection<GravamenPendiente>    lcgp_return;
							RegistroCalificacion             lrc_rctmp;
							Collection<RegistroCalificacion> lcrca_rc;
							lcrca_rc     = new ArrayList<RegistroCalificacion>();

							lcgp_return = DaoCreator.getRegistroCalificacionDAO(ldm_manager)
									                    .findGravamenPendiente(
									    ls_idCirculo, ll_idMatricula.toString(), ab_just0400Group
									);

							if(CollectionUtils.isValidCollection(lcgp_return))
							{
								AnotacionCancelacionDAO lac_DAO;

								lac_DAO = DaoCreator.getAnotacionCancelacionDAO(ldm_manager);

								for(GravamenPendiente lgp_tmp : lcgp_return)
								{
									lrc_rctmp = new RegistroCalificacion();

									if(lgp_tmp != null)
									{
										Long ll_idAnotacionCancelacion;

										ll_idAnotacionCancelacion = NumericUtils.getLongWrapper(
											    lgp_tmp.getIdAnotacion()
											);

										if(NumericUtils.isValidLong(ll_idAnotacionCancelacion))
										{
											if(ab_anotacionCancelada)
											{
												AnotacionCancelacion lac_anotacionCancelacion;

												lac_anotacionCancelacion = lac_DAO.findByCirMatAnotSol(
													    ls_idCirculo, NumericUtils.getLong(ll_idMatricula),
													    ll_idAnotacionCancelacion, ls_idSolicitud
													);

												if(lac_anotacionCancelacion != null)
												{
													lrc_rctmp.setIdAnotacion(ll_idAnotacionCancelacion);
													lrc_rctmp.setFechaRegistro(lgp_tmp.getFechaRegistro());
													lrc_rctmp.setRadicacion(lgp_tmp.getRadicacion());
													lrc_rctmp.setIdEstadoAnotacion(lgp_tmp.getIdEstadoAnotacion());
													lrc_rctmp.setDocumento(lgp_tmp.getIdDocumento());
													lrc_rctmp.setNumero(lgp_tmp.getNumero());
													lrc_rctmp.setFechaDocumentoStr(lgp_tmp.getFechaDocumento());
													lrc_rctmp.setNombreOficinaOrigen(lgp_tmp.getOficinaOrigen());
													lrc_rctmp.setCodActo(lgp_tmp.getCodigoActo());
													lrc_rctmp.setNombreActo(lgp_tmp.getDescripcionActo());
													lrc_rctmp.setIdCirculo(ls_idCirculo);
													lrc_rctmp.setIdMatricula(ll_idMatricula);
													lrc_rctmp.setRevision(false);

													lcrca_rc.add(lrc_rctmp);
												}
											}
											else
											{
												lrc_rctmp.setIdAnotacion(ll_idAnotacionCancelacion);
												lrc_rctmp.setFechaRegistro(lgp_tmp.getFechaRegistro());
												lrc_rctmp.setRadicacion(lgp_tmp.getRadicacion());
												lrc_rctmp.setIdEstadoAnotacion(lgp_tmp.getIdEstadoAnotacion());
												lrc_rctmp.setDocumento(lgp_tmp.getIdDocumento());
												lrc_rctmp.setNumero(lgp_tmp.getNumero());
												lrc_rctmp.setFechaDocumentoStr(lgp_tmp.getFechaDocumento());
												lrc_rctmp.setNombreOficinaOrigen(lgp_tmp.getOficinaOrigen());
												lrc_rctmp.setCodActo(lgp_tmp.getCodigoActo());
												lrc_rctmp.setNombreActo(lgp_tmp.getDescripcionActo());
												lrc_rctmp.setIdCirculo(ls_idCirculo);
												lrc_rctmp.setIdMatricula(ll_idMatricula);
												lrc_rctmp.setRevision(false);

												lcrca_rc.add(lrc_rctmp);
											}
										}
									}
								}
							}

							if(CollectionUtils.isValidCollection(lcrca_rc))
								lrc_return.setAnotacionesCancelacion(lcrca_rc);
						}
					}
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findDocumentoByIdTurnoHistoria", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lrc_return;
	}

	/**
	 * Método para realizar transaccciones con la base de datos para encontrar todos
	 * los registros que se encuentren
	 *
	 * @param alrc_linderoRegistroCalificacion
	 *            Objeto para extraer información necesarioa para el lindero
	 * @return
	 * @throws B2BException
	 */
	public synchronized String findLindero(
	    LinderoRegistroCalificacion alrc_linderoRegistroCalificacion, boolean ab_accion
	)
	    throws B2BException
	{
		DAOManager ldm_manager;
		String     ls_return;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		ls_return       = null;

		try
		{
			if(alrc_linderoRegistroCalificacion != null)
			{
				String ls_idCirculo;
				Long   ll_idMatricula;

				ls_idCirculo       = alrc_linderoRegistroCalificacion.getIdCirculo();
				ll_idMatricula     = alrc_linderoRegistroCalificacion.getIdMatricula();

				if(!NumericUtils.isValidLong(ll_idMatricula))
					throw new B2BException(ErrorKeys.ERROR_MATRICULA_INVALIDA);

				if(!StringUtils.isValidString(ls_idCirculo))
					throw new B2BException(ErrorKeys.CIRCULO_REGISTRAL_SELECCIONADO_INVALIDO);

				LinderoPredio llp_linderoPredio;

				llp_linderoPredio = new LinderoPredio();

				llp_linderoPredio.setIdCirculo(ls_idCirculo);
				llp_linderoPredio.setIdMatricula(ll_idMatricula);

				llp_linderoPredio = DaoCreator.getLinderoPredioDAO(ldm_manager).findById(llp_linderoPredio);

				if(llp_linderoPredio != null)
				{
					String ls_lindero;

					ls_lindero = llp_linderoPredio.getLindero();

					if(StringUtils.isValidString(ls_lindero))
						ls_return = ls_lindero;
				}
				else if((llp_linderoPredio == null) && ab_accion)
					throw new B2BException(ErrorKeys.NO_SE_ENCONTRARON_REGISTROS_CONSULTA);
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findLindero", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return ls_return;
	}

	/**
	 * Método para realizar transaccciones con la base de datos para encontrar todas
	 * las matriculas
	 *
	 * @param as_idTurnoHist
	 *            String del id turno historia
	 * @param as_userId
	 *            Usuario en sesión
	 * @param ab_b
	 *            Booleano para saber si hacer una cosa o no
	 * @param al_etapaP
	 *            long de etapa
	 * @return
	 * @throws B2BException
	 */
	public synchronized RegistroCalificacion findMatriculas(
	    String as_idMatricula, String as_idTurnoHist, String as_idTurno, String as_userId, String as_ipRemote,
	    boolean ab_b, long al_etapaP
	)
	    throws B2BException
	{
		DAOManager           ldm_manager;
		RegistroCalificacion lorc_matriculas;

		ldm_manager         = DaoManagerFactory.getDAOManager();
		lorc_matriculas     = new RegistroCalificacion();

		try
		{
			boolean                                                           lb_devolucion;
			Long                                                              ll_turnoHist;
			TurnoHistoriaDAO                                                  lothd_thd;
			TurnoHistoria                                                     loth_dataTurn;
			ConstantesDAO                                                     lc_DAO;
			Collection<com.bachue.snr.prosnr01.model.sdb.acc.AnotacionPredio> lap_anotacionesPredio;

			lothd_thd                 = DaoCreator.getTurnoHistoriaDAO(ldm_manager);
			lb_devolucion             = lothd_thd.etapaTurnoAnterior(as_idTurnoHist);
			ll_turnoHist              = NumericUtils.getLongWrapper(as_idTurnoHist);
			loth_dataTurn             = null;
			lc_DAO                    = DaoCreator.getConstantesDAO(ldm_manager);
			lap_anotacionesPredio     = null;

			lorc_matriculas.setDevolucion(lb_devolucion);
			lap_anotacionesPredio = DaoCreator.getAccAnotacionPredioDAO(ldm_manager)
					                              .findAnotacionesInactivasByIdTurno(as_idTurno, true);

			lorc_matriculas.setAnotacionesPredioEliminadas(lap_anotacionesPredio);

			if(ab_b)
			{
				loth_dataTurn = new TurnoHistoria();
				loth_dataTurn.setIdTurnoHistoria(ll_turnoHist);
				loth_dataTurn.setIdUsuarioModificacion(as_userId);
				loth_dataTurn.setIpModificacion(as_ipRemote);

				loth_dataTurn = DaoCreator.getProcedimientosDAO(ldm_manager).registroCalificacion(loth_dataTurn, false);
			}

			if(loth_dataTurn != null)
				throw new B2BException(loth_dataTurn.getCalificacion());
			else
			{
				boolean                   lb_baldios;
				Collection<Acto>          lca_actos;
				List<Map<String, Object>> llmso_datos;
				Map<Integer, Object>      lmso_criterios;
				Map<String, String>       lmss_codigosReloteo;
				Map<String, String>       lmss_actosBaldios;

				lb_baldios              = false;
				lca_actos               = null;
				llmso_datos             = null;
				lmss_codigosReloteo     = generarCodigos(ConstanteCommon.CODIGOS_ACTOS_RELOTEO, ldm_manager);
				lmss_actosBaldios       = generarCodigos(ConstanteCommon.TIPOS_ACTOS_BALDIOS, ldm_manager);

				lmso_criterios = new LinkedHashMap<Integer, Object>();

				if(StringUtils.isValidString(as_idMatricula) && as_idMatricula.contains("-"))
				{
					String[] las_as;

					las_as = as_idMatricula.split("-");

					if(las_as != null)
					{
						lmso_criterios.put(new Integer(1), as_idTurno);
						lmso_criterios.put(new Integer(2), las_as[0]);
						lmso_criterios.put(new Integer(3), las_as[1]);

						llmso_datos = DaoCreator.getUtilDAO(ldm_manager)
								                    .getCustonQuery(
								    ConsultasUtilidades.CS_MATRICULAS_PREDIO_BY_MATRICULA, lmso_criterios
								);
					}
				}
				else
				{
					lmso_criterios.put(new Integer(1), ll_turnoHist);

					llmso_datos = DaoCreator.getUtilDAO(ldm_manager)
							                    .getCustonQuery(
							    ConsultasUtilidades.CS_MATRICULAS_PREDIO, lmso_criterios
							);
				}

				if(llmso_datos == null)
				{
					Turno lth_turno;

					lth_turno = new Turno();

					lth_turno.setIdTurno(as_idTurno);

					lth_turno = DaoCreator.getTurnoDAO(ldm_manager).findById(lth_turno);

					if(lth_turno != null)
					{
						String ls_idCirculo;
						String ls_idSolicitud;

						ls_idCirculo       = lth_turno.getIdCirculo();
						ls_idSolicitud     = lth_turno.getIdSolicitud();

						if(StringUtils.isValidString(ls_idSolicitud) && StringUtils.isValidString(ls_idCirculo))
						{
							Acto la_param;

							la_param = new Acto();

							la_param.setIdCirculo(ls_idCirculo);
							la_param.setIdSolicitud(ls_idSolicitud);

							lca_actos = DaoCreator.getActoDAO(ldm_manager).findByIdSolicitudCirculo(la_param);

							if(
							    CollectionUtils.isValidCollection(lca_actos)
								    && CollectionUtils.isValidCollection(lmss_actosBaldios)
							)
							{
								Iterator<Acto> lia_iterator;

								lia_iterator = lca_actos.iterator();

								while(lia_iterator.hasNext() && !lb_baldios)
								{
									Acto la_acto;

									la_acto = lia_iterator.next();

									if(la_acto != null)
									{
										String ls_idTipoActo;

										ls_idTipoActo     = la_acto.getIdTipoActo();
										lb_baldios        = StringUtils.isValidString(ls_idTipoActo)
												&& lmss_actosBaldios.containsKey(ls_idTipoActo);
									}
								}

								if(lb_baldios)
								{
									Map<String, Object> lmso_data;

									lmso_data       = new LinkedHashMap<String, Object>(1);
									llmso_datos     = new LinkedList<Map<String, Object>>();

									lmso_data.put(IdentificadoresCommon.MATRICULA, ls_idCirculo + "-0");
									lmso_data.put(IdentificadoresCommon.ESTADO_PREDIO, EstadoCommon.ACTIVO_TXT);
									lmso_data.put(IdentificadoresCommon.MATRICULAS_APERTURAR, "0");

									llmso_datos.add(lmso_data);
								}
							}
						}
					}
				}

				if(CollectionUtils.isValidCollection(llmso_datos))
				{
					Collection<RegistroCalificacion> lcrc_rc;
					Iterator<Map<String, Object>>    lhm_dataQuerys;

					lcrc_rc            = new ArrayList<RegistroCalificacion>();
					lhm_dataQuerys     = llmso_datos.iterator();
					loth_dataTurn      = lothd_thd.findDataTurn(
						    NumericUtils.getLongWrapper(as_idTurnoHist), NumericUtils.getLongWrapper(al_etapaP)
						);

					if(loth_dataTurn != null)
					{
						String                         ls_idSolicitud;
						String                         ls_idTurno;
						int                            li_contadorReloteo;
						int                            li_contadorPorMatricula;
						LinkedHashMap<String, Boolean> llhm_datosParcial;
						MatriculaSegregacionDAO        lmsDAO_dao;

						li_contadorReloteo          = 0;
						li_contadorPorMatricula     = 0;
						ls_idSolicitud              = loth_dataTurn.getIdSolicitud();
						ls_idTurno                  = loth_dataTurn.getIdTurno();
						llhm_datosParcial           = new LinkedHashMap<String, Boolean>();
						lmsDAO_dao                  = DaoCreator.getMatriculaSegregacionDAO(ldm_manager);

						while(lhm_dataQuerys.hasNext())
						{
							Map<String, Object>  llhm_actual;
							RegistroCalificacion lorc_tmp;

							lorc_tmp        = null;
							llhm_actual     = lhm_dataQuerys.next();

							if(llhm_actual != null)
							{
								lorc_tmp = new RegistroCalificacion();

								String ls_matriculas;

								ls_matriculas = llhm_actual.get(IdentificadoresCommon.MATRICULA).toString();

								if(StringUtils.isValidString(ls_matriculas))
								{
									boolean            lb_revisado;
									Collection<String> lcs_revisiones;
									int                li_tmp;
									Long               ll_idMatricula;
									String             ls_idCirculo;

									lb_revisado        = true;
									ls_idCirculo       = ls_matriculas;
									li_tmp             = ls_idCirculo.indexOf("-");
									ls_idCirculo       = ls_idCirculo.substring(0, li_tmp);
									ll_idMatricula     = NumericUtils.getLongWrapper(
										    ls_matriculas.substring(li_tmp + 1)
										);

									lorc_tmp.setIdCirculo(ls_matriculas);
									lorc_tmp.setEstadoMatricula(
									    llhm_actual.get(IdentificadoresCommon.ESTADO_PREDIO).toString()
									);

									if(llhm_actual.get(IdentificadoresCommon.MATRICULAS_APERTURAR) != null)
									{
										String ls_matriculasAperturar;
										ls_matriculasAperturar = llhm_actual.get(
											    IdentificadoresCommon.MATRICULAS_APERTURAR
											).toString();

										lorc_tmp.setMatriculasAperturar(
										    NumericUtils.getLongWrapper(ls_matriculasAperturar)
										);
									}

									lcs_revisiones = DaoCreator.getRegistroCalificacionDAO(ldm_manager)
											                       .consultaRevision(
											    ls_idCirculo, ll_idMatricula, ll_turnoHist, lb_devolucion, ls_idTurno
											);

									{
										Collection<SolicitudAsociada> lcsa_data;
										SolicitudAsociada             lsa_param;

										lsa_param = new SolicitudAsociada();

										lsa_param.setIdSolicitud(ls_idSolicitud);
										lsa_param.setIndVinculado(EstadoCommon.S);

										lcsa_data = DaoCreator.getSolicitudAsociadaDAO(ldm_manager)
												                  .findByIdSolicitudIndVinculado(lsa_param);

										if(CollectionUtils.isValidCollection(lcsa_data))
										{
											for(SolicitudAsociada lsa_iterador : lcsa_data)
											{
												if(lsa_iterador != null)
												{
													String ls_idSolicitudAsociada;

													ls_idSolicitudAsociada = lsa_iterador.getIdSolicitud1();

													if(StringUtils.isValidString(ls_idSolicitudAsociada))
													{
														TurnoHistoria lth_dataBlq80;

														lth_dataBlq80 = lothd_thd.findByIdSolicitudBlq80(
															    ls_idSolicitudAsociada
															);

														if(lth_dataBlq80 != null)
														{
															Long ll_idTurnoHistoriaAsociada;

															ll_idTurnoHistoriaAsociada = lth_dataBlq80
																	.getIdTurnoHistoria();

															if(NumericUtils.isValidLong(ll_idTurnoHistoriaAsociada))
															{
																Collection<String> lcs_dataTemp;

																lcs_dataTemp = DaoCreator.getRegistroCalificacionDAO(
																	    ldm_manager
																	)
																		                     .consultaRevision(
																		    ls_idCirculo, ll_idMatricula,
																		    ll_idTurnoHistoriaAsociada, lb_devolucion,
																		    ls_idTurno
																		);

																if(CollectionUtils.isValidCollection(lcs_dataTemp))
																	lcs_revisiones.addAll(lcs_dataTemp);
															}
														}
													}
												}
											}
										}
									}

									if(CollectionUtils.isValidCollection(lcs_revisiones))
									{
										Iterator<String> lis_is;

										lis_is = lcs_revisiones.iterator();

										if(lis_is != null)
										{
											while(lis_is.hasNext() && lb_revisado)
											{
												String ls_s;
												ls_s = lis_is.next();

												if(StringUtils.isValidString(ls_s))
													lb_revisado = ls_s.equalsIgnoreCase(EstadoCommon.S);
											}
										}
									}

									if(lb_revisado)
										lorc_tmp.setTramite(EstadoCommon.ESTADO_ANOTACION_COMPLETO);
									else
										lorc_tmp.setTramite(EstadoCommon.ESTADO_ANOTACION_INCOMPLETO);

									SolicitudMatriculaActo             lsma_solicitudMatriculaActo;
									Collection<SolicitudMatriculaActo> lcsma_matriculasActo;

									lsma_solicitudMatriculaActo = new SolicitudMatriculaActo();

									lsma_solicitudMatriculaActo.setIdSolicitud(ls_idSolicitud);
									lsma_solicitudMatriculaActo.setIdMatricula(NumericUtils.getLong(ll_idMatricula));
									lsma_solicitudMatriculaActo.setIdCirculo(ls_idCirculo);

									lcsma_matriculasActo = DaoCreator.getSolicitudMatriculaActoDAO(ldm_manager)
											                             .findAllByIdSolicitud(
											    lsma_solicitudMatriculaActo, false
											);

									if(!CollectionUtils.isValidCollection(lcsma_matriculasActo) && lb_baldios)
									{
										lcsma_matriculasActo = new ArrayList<SolicitudMatriculaActo>();

										for(Acto la_acto : lca_actos)
										{
											if(la_acto != null)
											{
												String ls_idActo;
												String ls_idActoPrincipal;

												ls_idActo              = la_acto.getIdActo();
												ls_idActoPrincipal     = la_acto.getIdActoPrincipal();

												if(
												    StringUtils.isValidString(ls_idActo)
													    && !StringUtils.isValidString(ls_idActoPrincipal)
												)
												{
													SolicitudMatriculaActo lsma_data;

													lsma_data = new SolicitudMatriculaActo();

													lsma_data.setIdActo(ls_idActo);

													lcsma_matriculasActo.add(lsma_data);
												}
											}
										}
									}

									if(CollectionUtils.isValidCollection(lcsma_matriculasActo))
									{
										boolean               lb_parcial;
										boolean               lb_eliminacionRemanentes;
										PredioRegistroDAO     lpr_DAO;
										ZonaRegistralDAO      lzr_DAO;
										EstadoPredioDao       lep_DAO;
										NaturalezaJuridicaDAO lnj_DAO;
										Constantes            lc_divisionMaterial;
										Map<String, String>   lmss_actosRemanentes;

										lnj_DAO                      = DaoCreator.getNaturalezaJuridicaDAO(ldm_manager);
										lep_DAO                      = DaoCreator.getEstadoPredioDao(ldm_manager);
										lzr_DAO                      = DaoCreator.getZonaRegistralDAO(ldm_manager);
										lpr_DAO                      = DaoCreator.getPredioRegistroDAO(ldm_manager);
										lb_parcial                   = false;
										lb_eliminacionRemanentes     = false;
										lc_divisionMaterial          = lc_DAO.findById(
											    ConstanteCommon.ACTOS_DIVISION_MATERIAL
											);
										lmss_actosRemanentes         = generarCodigos(
											    ConstanteCommon.CODIGOS_ACTOS_EMBARGO_REMANENTES, ldm_manager
											);

										for(SolicitudMatriculaActo lsma_acto : lcsma_matriculasActo)
										{
											if(lsma_acto != null)
											{
												String ls_idActo;

												ls_idActo = lsma_acto.getIdActo();

												if(StringUtils.isValidString(ls_idActo))
												{
													Acto la_acto;

													la_acto = new Acto();

													la_acto.setIdActo(ls_idActo);

													la_acto = DaoCreator.getActoDAO(ldm_manager).findById(la_acto);

													if(la_acto != null)
													{
														String ls_idTipoActo;

														ls_idTipoActo = la_acto.getIdTipoActo();

														if(StringUtils.isValidString(ls_idTipoActo))
														{
															boolean lb_0919;
															boolean lb_0126;
															boolean lb_0915;

															lb_0919     = ls_idTipoActo.equalsIgnoreCase("0919");
															lb_0126     = ls_idTipoActo.equalsIgnoreCase("0126");
															lb_0915     = ls_idTipoActo.equalsIgnoreCase("0915");

															lorc_matriculas.setBaldios(
															    lmss_actosBaldios.containsKey(ls_idTipoActo)
															);

															if(lc_divisionMaterial != null)
															{
																String   ls_caracter;
																String[] lsa_codigosActo;

																ls_caracter         = StringUtils.getStringNotNull(
																	    lc_divisionMaterial.getCaracter()
																	);
																lsa_codigosActo     = ls_caracter.split(",");

																if(
																    (lsa_codigosActo != null)
																	    && ls_idTipoActo.equalsIgnoreCase(
																	        lsa_codigosActo[0]
																	    )
																)
																	lorc_matriculas.setDivisionMaterial(true);
															}

															if(lorc_matriculas.isRemanente())
															{
																Map<String, String> lmss_actos;

																lmss_actos = generarCodigos(
																	    ConstanteCommon.ACTOS_CANCELACION_CON_REMATE,
																	    ldm_manager
																	);

																lorc_matriculas.setRemanente(
																    lmss_actos.containsKey(ls_idTipoActo)
																);
															}

															if(lmss_codigosReloteo.containsKey(ls_idTipoActo))
															{
																li_contadorReloteo++;
																li_contadorPorMatricula = 2;
															}
															else
																li_contadorPorMatricula = 0;

															TipoActo lta_ta;
															lta_ta = new TipoActo();
															lta_ta.setIdTipoActo(ls_idTipoActo);
															lta_ta = DaoCreator.getTipoActoDAO(ldm_manager)
																	               .findTipoActoById(lta_ta);

															if(lta_ta != null)
															{
																lorc_matriculas.setActoSegregacion(
																    StringUtils.getStringNotNull(
																        lta_ta.getGeneraSegregacion()
																    ).equalsIgnoreCase(EstadoCommon.S)
																);

																lorc_matriculas.setInscripcionAdicional(
																    StringUtils.getStringNotNull(
																        lta_ta.getInscripcionAdicional()
																    ).equalsIgnoreCase(EstadoCommon.S)
																);

																lorc_matriculas.setAperturaMatricula(
																    StringUtils.getStringNotNull(
																        lta_ta.getAperturaMatricula()
																    ).equalsIgnoreCase(EstadoCommon.S)
																);
																lorc_matriculas.setCodActo(lta_ta.getIdTipoActo());
															}

															if(lb_0919 || lb_0126 || lb_0915)
															{
																PredioRegistro lpr_predioRegistro;

																lpr_predioRegistro = new PredioRegistro();

																lorc_matriculas.setEnglobe(lb_0919);
																lorc_matriculas.setVentaParcial(lb_0126);
																lorc_matriculas.setDesenglobe(lb_0915);

																lorc_matriculas.setIdCirculoMatriz(ls_idCirculo);
																lorc_matriculas.setIdMatriculaMatriz(ll_idMatricula);

																lpr_predioRegistro.setIdCirculo(ls_idCirculo);
																lpr_predioRegistro.setIdMatricula(
																    NumericUtils.getLong(ll_idMatricula)
																);
																lpr_predioRegistro.setValidMatricula(true);

																lpr_predioRegistro = lpr_DAO.findById(
																	    lpr_predioRegistro
																	);

																if(lpr_predioRegistro != null)
																{
																	String ls_idZonaRegistral;

																	ls_idZonaRegistral = lpr_predioRegistro
																			.getIdZonaRegistral();

																	if(StringUtils.isValidString(ls_idZonaRegistral))
																	{
																		ZonaRegistral lzr_zonaRegistral;

																		lzr_zonaRegistral = new ZonaRegistral();

																		lzr_zonaRegistral.setIdZonaRegistral(
																		    ls_idZonaRegistral
																		);

																		lzr_zonaRegistral = lzr_DAO.findById(
																			    lzr_zonaRegistral
																			);

																		if(lzr_zonaRegistral != null)
																			lorc_tmp.setZonaRegistral(
																			    lzr_zonaRegistral
																			);
																	}

																	String ls_idEstadoPredio;

																	ls_idEstadoPredio = lpr_predioRegistro
																			.getIdEstadoPredio();

																	if(StringUtils.isValidString(ls_idEstadoPredio))
																	{
																		EstadoPredio lep_estadoPredio;

																		lep_estadoPredio = new EstadoPredio();

																		lep_estadoPredio.setIdEstadoPredio(
																		    ls_idEstadoPredio
																		);

																		lep_estadoPredio = lep_DAO.findById(
																			    lep_estadoPredio
																			);

																		if(lep_estadoPredio != null)
																			lorc_tmp.setEstadoPredio(
																			    lep_estadoPredio.getIdEstadoPredio()
																			);
																	}
																}
															}

															NaturalezaJuridica lnj_naturalezaJuridica;

															lnj_naturalezaJuridica = new NaturalezaJuridica();

															lnj_naturalezaJuridica.setIdNaturalezaJuridica(
															    ls_idTipoActo
															);

															lnj_naturalezaJuridica = lnj_DAO.findByIdMaxVersion(
																    lnj_naturalezaJuridica
																);

															if(lnj_naturalezaJuridica != null)
															{
																if(lnj_naturalezaJuridica.isValidarArea())
																	lorc_tmp.setValidarArea(true);

																if(lnj_naturalezaJuridica.isRequiereCierreFolio())
																{
																	lorc_matriculas.setCierreFolio(true);

																	CambioEstadoPredio lcep_estadoPredio;
																	lcep_estadoPredio = new CambioEstadoPredio();

																	lcep_estadoPredio.setIdTurno(as_idTurno);

																	lcep_estadoPredio = DaoCreator.getCambioEstadoPredioDAO(
																		    ldm_manager
																		).findByIdTurno(lcep_estadoPredio);

																	lorc_matriculas.setCambioEstadoPredio(
																	    lcep_estadoPredio
																	);
																}
															}
														}
													}
												}

												String ls_motivoTramite;

												ls_motivoTramite = lsma_acto.getMotivoTramite();

												if(
												    StringUtils.isValidString(ls_motivoTramite)
													    && ls_motivoTramite.equalsIgnoreCase(
													        IdentificadoresCommon.INSCRIPCION_PARCIAL
													    )
												)
													lb_parcial = true;
											}
										}

										{
											Collection<SolicitudMatriculaActo> lcsma_matriculasActoAll;

											lcsma_matriculasActoAll = DaoCreator.getSolicitudMatriculaActoDAO(
												    ldm_manager
												).findAllByIdSolicitud(lsma_solicitudMatriculaActo, true);

											if(CollectionUtils.isValidCollection(lcsma_matriculasActoAll))
											{
												for(SolicitudMatriculaActo lsma_acto : lcsma_matriculasActoAll)
												{
													if(lsma_acto != null)
													{
														String ls_idActo;

														ls_idActo = lsma_acto.getIdActo();

														if(StringUtils.isValidString(ls_idActo))
														{
															Acto la_acto;

															la_acto = new Acto();

															la_acto.setIdActo(ls_idActo);

															la_acto = DaoCreator.getActoDAO(ldm_manager)
																	                .findById(la_acto);

															if(la_acto != null)
															{
																String ls_idTipoActo;

																ls_idTipoActo = la_acto.getIdTipoActo();

																if(StringUtils.isValidString(ls_idTipoActo))
																{
																	if(lmss_actosRemanentes.containsKey(ls_idTipoActo))
																	{
																		String ls_estado;

																		ls_estado = lsma_acto.getEstado();

																		if(
																		    StringUtils.isValidString(ls_estado)
																			    && ls_estado.equalsIgnoreCase(
																			        EstadoCommon.INACTIVO
																			    )
																		)
																			lb_eliminacionRemanentes = true;
																	}
																}
															}
														}
													}
												}
											}
										}

										lorc_matriculas.setRemanente(lb_eliminacionRemanentes);

										llhm_datosParcial.put(ls_matriculas, new Boolean(lb_parcial));
									}

									if(lorc_matriculas.isVentaParcial())
									{
										SolicitudMatricula lsm_solicitudMatricula;

										lsm_solicitudMatricula = new SolicitudMatricula();

										lsm_solicitudMatricula.setIdSolicitud(ls_idSolicitud);
										lsm_solicitudMatricula.setIdCirculo(ls_idCirculo);
										lsm_solicitudMatricula.setIdMatricula(NumericUtils.getLong(ll_idMatricula));

										lsm_solicitudMatricula = DaoCreator.getSolicitudMatriculaDAO(ldm_manager)
												                               .findById(lsm_solicitudMatricula);

										if(lsm_solicitudMatricula != null)
										{
											String ls_cementerio;

											ls_cementerio = lsm_solicitudMatricula.getCementerio();

											lorc_matriculas.setCementerio(
											    StringUtils.isValidString(ls_cementerio)
												    && ls_cementerio.equalsIgnoreCase(EstadoCommon.S)
											);
										}
									}

									if(lorc_tmp.isValidarArea())
									{
										AccAreaPredio laap_areaPredio;
										boolean       lb_areaValidada;
										boolean       lb_contieneReloteo;
										boolean       lb_revisionReloteo;

										laap_areaPredio        = new AccAreaPredio();
										lb_areaValidada        = false;
										lb_contieneReloteo     = li_contadorPorMatricula > 0;
										lb_revisionReloteo     = false;

										laap_areaPredio.setIdMatricula(ll_idMatricula);
										laap_areaPredio.setIdCirculo(ls_idCirculo);
										laap_areaPredio.setIdTurno(as_idTurno);

										lb_areaValidada = DaoCreator.getAccAreaPredioDAO(ldm_manager)
												                        .areaValidadaCalificacion(laap_areaPredio);

										lorc_tmp.setAreaValidada(lb_areaValidada);

										if(lb_contieneReloteo)
										{
											MatriculaSegregacion             lms_ms;
											Collection<MatriculaSegregacion> lcms_cms;
											lms_ms = new MatriculaSegregacion();

											lms_ms.setIdSolicitud(ls_idSolicitud);
											lms_ms.setIdCirculoMatriz(ls_idCirculo);
											lms_ms.setMatriculaMatriz(ll_idMatricula);

											lcms_cms               = lmsDAO_dao.findByIdSolicitud(lms_ms, false);
											lb_revisionReloteo     = CollectionUtils.isValidCollection(lcms_cms);

											if(lb_revisionReloteo && lb_revisado && lb_areaValidada)
												lorc_tmp.setTramite(EstadoCommon.ESTADO_ANOTACION_COMPLETO);
											else
												lorc_tmp.setTramite(EstadoCommon.ESTADO_ANOTACION_INCOMPLETO);
										}
										else
										{
											if(lb_revisado && lb_areaValidada)
												lorc_tmp.setTramite(EstadoCommon.ESTADO_ANOTACION_COMPLETO);
											else
												lorc_tmp.setTramite(EstadoCommon.ESTADO_ANOTACION_INCOMPLETO);
										}

										lorc_tmp.setReloteo(lb_contieneReloteo);

										lorc_tmp.setAreaRevision(lb_revisado && lb_areaValidada);
									}
								}
							}

							if(lorc_tmp != null)
								lcrc_rc.add(lorc_tmp);
						}

						if(li_contadorReloteo > 0)
							lorc_matriculas.setReloteo(true);
						else
							lorc_matriculas.setReloteo(false);

						lorc_matriculas.setDatosParcial(llhm_datosParcial);

						if(StringUtils.isValidString(ls_idSolicitud))
						{
							Solicitud ls_solicitud;

							ls_solicitud = new Solicitud();

							ls_solicitud.setIdSolicitud(ls_idSolicitud);
							lorc_matriculas.setRadicacion(ls_idTurno);

							ls_solicitud = DaoCreator.getSolicitudDAO(ldm_manager).findById(ls_solicitud);

							if(ls_solicitud != null)
							{
								String ls_idDocumento;

								ls_idDocumento = ls_solicitud.getIdDocumento();

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

										lorc_matriculas.setDataDocumento(ld_documento);
									}
								}

								lorc_matriculas.setNir(ls_solicitud.getNir());
							}
						}

						if(StringUtils.isValidString(ls_idTurno))
						{
							Solicitud los_solicitud;
							boolean   lb_tmp;

							los_solicitud     = new Solicitud();
							lb_tmp            = true;

							los_solicitud.setIdSolicitud(ls_idSolicitud);

							los_solicitud = DaoCreator.getSolicitudDAO(ldm_manager).findById(los_solicitud);

							if(los_solicitud != null)
							{
								String ls_idCirculo;
								Turno  lt_turno;

								lb_tmp           = DaoCreator.getDocumentoDAO(ldm_manager)
										                         .consultaOficinaDoc(los_solicitud.getIdDocumento());
								ls_idCirculo     = null;
								lt_turno         = new Turno();

								lt_turno.setIdTurno(ls_idTurno);

								lt_turno = DaoCreator.getTurnoDAO(ldm_manager).findById(lt_turno);

								if(lt_turno != null)
								{
									TurnoHistoria lth_turnoHistoria;

									ls_idCirculo          = lt_turno.getIdCirculo();
									lth_turnoHistoria     = new TurnoHistoria();

									lth_turnoHistoria.setIdTurno(ls_idTurno);

									lth_turnoHistoria = DaoCreator.getTurnoHistoriaDAO(ldm_manager)
											                          .findByIdTurno(lth_turnoHistoria, false);

									if(lth_turnoHistoria != null)
										lorc_matriculas.setIdTurnoHistoria(lth_turnoHistoria.getIdTurnoHistoria());

									lorc_matriculas.setIdCirculo(ls_idCirculo);

									if(lorc_matriculas.isBaldios())
									{
										AccAreaUI         laaui_area;
										AccPredioRegistro lapr_predioRegistro;
										boolean           lb_datosArea;
										DatosBasicos      ldb_datosBasicos;

										laaui_area              = null;
										lapr_predioRegistro     = new AccPredioRegistro();
										lb_datosArea            = false;
										ldb_datosBasicos        = new DatosBasicos();

										lapr_predioRegistro.setIdTurno(ls_idTurno);

										lapr_predioRegistro = DaoCreator.getAccPredioRegistroDAO(ldm_manager)
												                            .findByIdTurno(lapr_predioRegistro, true);

										if(lapr_predioRegistro == null)
										{
											DatosAntSistema ldas_datosAntSistema;

											ldas_datosAntSistema     = DaoCreator.getDatosAntSistemaDAO(ldm_manager)
													                                 .findByIdSolicitudOne(
													    ls_idSolicitud
													);
											lb_datosArea             = true;

											if(ldas_datosAntSistema != null)
											{
												CirculoRegistral       lcr_circuloRegistral;
												UbicacionZonaRegistral luzr_ubicacion;

												lapr_predioRegistro      = new AccPredioRegistro();
												lcr_circuloRegistral     = new CirculoRegistral();
												luzr_ubicacion           = new UbicacionZonaRegistral();

												lapr_predioRegistro.setNombrePredio(
												    ldas_datosAntSistema.getNombrePredio()
												);
												lapr_predioRegistro.setIdTipoPredio(
												    ldas_datosAntSistema.getIdTipoPredio()
												);
												lcr_circuloRegistral.setIdCirculo(ls_idCirculo);

												lcr_circuloRegistral = DaoCreator.getCirculoRegistralDAO(ldm_manager)
														                             .findById(lcr_circuloRegistral);

												luzr_ubicacion.setCirculoRegistral(lcr_circuloRegistral);

												{
													Departamento ld_departamento;
													EstadoPredio lep_estadoPredio;
													Municipio    lm_municipio;
													String       ls_idPais;
													String       ls_idDepartamento;

													ld_departamento       = new Departamento();
													lep_estadoPredio      = new EstadoPredio();
													lm_municipio          = new Municipio();
													ls_idPais             = ldas_datosAntSistema.getIdPais();
													ls_idDepartamento     = ldas_datosAntSistema.getIdDepartamento();

													ld_departamento.setIdPais(ls_idPais);
													ld_departamento.setIdDepartamento(ls_idDepartamento);
													lm_municipio.setIdPais(ls_idPais);
													lm_municipio.setIdDepartamento(ls_idDepartamento);
													lm_municipio.setIdMunicipio(ldas_datosAntSistema.getIdMunicipio());

													ld_departamento     = DaoCreator.getDepartamentoDAO(ldm_manager)
															                            .findById(ld_departamento);
													lm_municipio        = DaoCreator.getMunicipioDAO(ldm_manager)
															                            .findById(lm_municipio);

													luzr_ubicacion.setIdPais(ls_idPais);
													luzr_ubicacion.setDepartamento(ld_departamento);
													luzr_ubicacion.setMunicipio(lm_municipio);
													lep_estadoPredio.setIdEstadoPredio(EstadoCommon.S);
													luzr_ubicacion.setEstadoPredio(lep_estadoPredio);
												}

												ldb_datosBasicos.setUbicacion(luzr_ubicacion);
											}

											{
												Apertura  la_apertura;
												Documento ld_documento;

												la_apertura      = new Apertura();
												ld_documento     = new Documento();

												la_apertura.setFechaApertura(new Date());
												la_apertura.setRadicacion(ls_idTurno);

												ld_documento.setIdDocumento(los_solicitud.getIdDocumento());

												ld_documento = DaoCreator.getDocumentoDAO(ldm_manager)
														                     .findById(ld_documento);

												if(ld_documento != null)
												{
													OficinaOrigen loo_oficinaOrigen;
													String        ls_idOficinaOrigen;
													BigDecimal    lbd_version;

													loo_oficinaOrigen      = new OficinaOrigen();
													ls_idOficinaOrigen     = ld_documento.getIdOficinaOrigen();
													lbd_version            = ld_documento.getVersion();

													loo_oficinaOrigen.setIdOficinaOrigen(ls_idOficinaOrigen);
													loo_oficinaOrigen.setVersion(lbd_version);

													loo_oficinaOrigen = DaoCreator.getOficinaOrigenDAO(ldm_manager)
															                          .findById(loo_oficinaOrigen);

													if(loo_oficinaOrigen != null)
													{
														la_apertura.setIdTipoOficina(
														    loo_oficinaOrigen.getIdTipoOficina()
														);
														la_apertura.setIdPais(loo_oficinaOrigen.getIdPais());
														la_apertura.setIdDepartamento(
														    loo_oficinaOrigen.getIdDepartamento()
														);
														la_apertura.setIdMunicipio(loo_oficinaOrigen.getIdMunicipio());
													}

													la_apertura.setIdOficinaOrigen(ls_idOficinaOrigen);
													la_apertura.setVersion(lbd_version);
													la_apertura.setDocumento(ld_documento);
												}

												ldb_datosBasicos.setApertura(la_apertura);
											}
										}
										else
										{
											AccAreaPredio laap_areaPredio;

											laap_areaPredio = new AccAreaPredio();

											laap_areaPredio.setIdCirculo(lapr_predioRegistro.getIdCirculo());
											laap_areaPredio.setIdMatricula(lapr_predioRegistro.getIdMatricula());
											laap_areaPredio.setIdTurno(ls_idTurno);

											laap_areaPredio     = DaoCreator.getAccAreaPredioDAO(ldm_manager)
													                            .findByCirculoMatriculaTurno(
													    laap_areaPredio
													);
											lb_datosArea        = laap_areaPredio == null;
										}

										if(lb_datosArea)
										{
											MatriculaSegregacion lms_matriculaSegregacion;

											lms_matriculaSegregacion = DaoCreator.getMatriculaSegregacionDAO(
												    ldm_manager
												).findBySolicitud(ls_idSolicitud);

											if(lms_matriculaSegregacion != null)
											{
												final String ls_one = "1";

												laaui_area = new AccAreaUI();

												{
													Double ld_idArea;

													ld_idArea = lms_matriculaSegregacion.getAreaTerreno();

													if(NumericUtils.isValidDouble(ld_idArea))
													{
														Collection<DetalleAreaPredio> lcdap_areas;
														DetalleAreaPredio             ldap_detalleArea;

														lcdap_areas          = new ArrayList<DetalleAreaPredio>();
														ldap_detalleArea     = new DetalleAreaPredio();

														ldap_detalleArea.setIdTipoArea(TipoAreaCommon.TERRENO);
														ldap_detalleArea.setIdDetalleAreaPredio(ls_one);
														ldap_detalleArea.setIdUnidadMedida(
														    UnidadMedidaAreaCommon.METROS_CUADRADOS
														);
														ldap_detalleArea.setArea(ld_idArea);

														lcdap_areas.add(ldap_detalleArea);
														laaui_area.setAreasTerreno(lcdap_areas);
													}
												}

												{
													Double ld_idArea;

													ld_idArea = lms_matriculaSegregacion.getAreaConstruida();

													if(NumericUtils.isValidDouble(ld_idArea))
													{
														DetalleAreaPredio ldap_detalleArea;

														ldap_detalleArea = new DetalleAreaPredio();

														ldap_detalleArea.setIdTipoArea(TipoAreaCommon.CONSTRUIDA);
														ldap_detalleArea.setIdDetalleAreaPredio(ls_one);
														ldap_detalleArea.setIdUnidadMedida(
														    UnidadMedidaAreaCommon.METROS_CUADRADOS
														);
														ldap_detalleArea.setArea(ld_idArea);
														ldap_detalleArea.setAreaLectura(
														    StringUtils.getString(ld_idArea)
														);

														laaui_area.setDetalleAreaConstruida(ldap_detalleArea);
													}
												}

												{
													Double ld_idArea;

													ld_idArea = lms_matriculaSegregacion.getAreaPrivada();

													if(NumericUtils.isValidDouble(ld_idArea))
													{
														DetalleAreaPredio ldap_detalleArea;

														ldap_detalleArea = new DetalleAreaPredio();

														ldap_detalleArea.setIdTipoArea(TipoAreaCommon.PRIVADA);
														ldap_detalleArea.setIdDetalleAreaPredio(ls_one);
														ldap_detalleArea.setIdUnidadMedida(
														    UnidadMedidaAreaCommon.METROS_CUADRADOS
														);
														ldap_detalleArea.setArea(ld_idArea);
														ldap_detalleArea.setAreaLectura(
														    StringUtils.getString(ld_idArea)
														);

														laaui_area.setDetalleAreaPrivada(ldap_detalleArea);
													}
												}

												{
													Double ld_coeficiente;

													ld_coeficiente = lms_matriculaSegregacion.getCoeficiente();

													if(NumericUtils.isValidDouble(ld_coeficiente))
													{
														AccAreaPredio laap_areaPredio;

														laap_areaPredio = new AccAreaPredio();

														laap_areaPredio.setCoeficiente(ld_coeficiente);
														laap_areaPredio.setCoeficienteLectura(
														    StringUtils.getString(ld_coeficiente)
														);
													}
												}

												lorc_matriculas.setDatosArea(laaui_area);
											}
										}

										ldb_datosBasicos.setTurno(lt_turno);
										ldb_datosBasicos.setAccPredioRegistro(lapr_predioRegistro);
										lorc_matriculas.setDatosBasicos(ldb_datosBasicos);
									}

									{
										List<Map<String, Object>> llmso_mso;
										Map<Integer, Object>      lhmp_criterios;

										lhmp_criterios = new LinkedHashMap<Integer, Object>();
										lhmp_criterios.put(new Integer(1), as_idTurno);

										llmso_mso = DaoCreator.getUtilDAO(ldm_manager)
												                  .getCustonQuery(
												    ConsultasUtilidades.CS_CONSULTA_DATA_TURNO_VINCULADO, lhmp_criterios
												);

										if(CollectionUtils.isValidCollection(llmso_mso))
										{
											Iterator<Map<String, Object>> limso_iterador;

											limso_iterador = llmso_mso.iterator();

											if(limso_iterador.hasNext())
											{
												Map<String, Object> lmso_iterator;

												lmso_iterator = limso_iterador.next();

												if(lmso_iterator != null)
												{
													Object lo_mensaje;

													lo_mensaje = lmso_iterator.get(IdentificadoresCommon.DESCRIPCION);

													if((lo_mensaje != null) && lo_mensaje instanceof String)
														lorc_matriculas.setMensaje(((String)lo_mensaje).toString());
												}
											}
										}
									}
								}

								if(lb_tmp)
								{
									/* BUSCAR que tengan actos de cancelacion Grupo 700 */
									SolicitudMatriculaActo             losm_tmp;
									boolean                            lb_grupo;
									Collection<SolicitudMatriculaActo> lcsm_tmp;

									losm_tmp     = new SolicitudMatriculaActo();
									lb_grupo     = false;

									if(lt_turno != null)
									{
										losm_tmp.setIdSolicitud(ls_idSolicitud);
										losm_tmp.setIdCirculo(ls_idCirculo);

										lcsm_tmp = DaoCreator.getSolicitudMatriculaActoDAO(ldm_manager)
												                 .dataBySolicitud(losm_tmp);

										if(CollectionUtils.isValidCollection(lcsm_tmp))
										{
											for(SolicitudMatriculaActo losm_sma : lcsm_tmp)
											{
												if(!lb_grupo)
												{
													if(
													    StringUtils.getStringNotNull(losm_sma.getIdGrupoJuridica())
														               .equalsIgnoreCase("0700")
													)
														lb_grupo = true;
												}
											}
										}

										lorc_matriculas.setCancelacion(lb_grupo);
									}
									else
										lorc_matriculas.setCancelacion(false);
								}
							}

							lorc_matriculas.setTurno(ls_idTurno);
							lorc_matriculas.setIdSolicitud(ls_idSolicitud);
						}
					}

					if(CollectionUtils.isValidCollection(lcrc_rc))
						lorc_matriculas.setAllMatriculas(lcrc_rc);
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findMatriculas", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lorc_matriculas;
	}

	/**
	 * Método para realizar transaccciones con la base de datos para encontrar
	 * matriculas por el turno
	 *
	 * @param as_idTurno
	 *            String con el idTurno
	 * @param as_userId
	 *            String con el usuario en sesión
	 * @param as_remoteIp
	 *            Ip de usuario en sesión
	 * @return
	 * @throws B2BException
	 */
	public synchronized RegistroCalificacion findMatriculasInfByTurno(
	    String as_idTurno, String as_userId, String as_remoteIp
	)
	    throws B2BException
	{
		DAOManager           ldm_manager;
		RegistroCalificacion lorc_resumen;
		String               ls_idTurno;

		ldm_manager      = DaoManagerFactory.getDAOManager();
		ls_idTurno       = as_idTurno;
		lorc_resumen     = null;

		try
		{
			Collection<AreaPredio> acap_ap;
			AccPredioRegistroDAO   lapr_DAO;

			lapr_DAO     = DaoCreator.getAccPredioRegistroDAO(ldm_manager);

			acap_ap = lapr_DAO.findMatriculasInfByTurno(ls_idTurno);

			if(CollectionUtils.isValidCollection(acap_ap))
			{
				lorc_resumen = new RegistroCalificacion();

				lorc_resumen.setMatriculasInformacion(acap_ap);
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findMatriculasInfByTurno", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lorc_resumen;
	}

	/**
	 * Método para realizar transaccciones con la base de datos para encontrar todas
	 * las matriculas
	 *
	 * @param aorc_rc
	 *            Objeto registroCalificacion para extraer información necesaria
	 * @return
	 * @throws B2BException
	 */
	public synchronized RegistroCalificacion findMatriculasInformacion(RegistroCalificacion aorc_rc)
	    throws B2BException
	{
		DAOManager           ldm_manager;
		RegistroCalificacion lorc_resumen;

		lorc_resumen     = new RegistroCalificacion();
		ldm_manager      = DaoManagerFactory.getDAOManager();

		try
		{
			if(aorc_rc != null)
			{
				if(aorc_rc.isDevolucion())
					lorc_resumen.setMatriculasInformacion(
					    DaoCreator.getAccPredioRegistroDAO(ldm_manager).findMatriculasInfByTurno(aorc_rc.getTurno())
					);
				else
					lorc_resumen.setMatriculasInformacion(
					    DaoCreator.getAccPredioRegistroDAO(ldm_manager)
						              .findMatriculasInformacion(aorc_rc.getIdTurnoHistoria())
					);
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findMatriculasInformacion", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lorc_resumen;
	}

	/**
	 * Método para realizar transaccciones con la base de datos para encontrar la persona
	 * asociada a un turno historia
	 *
	 * @param as_idTurnoHistoria  Objeto String para extraer información necesaria
	 * @return Objeto Persona resultante de la consulta
	 * @throws B2BException
	 */
	public synchronized Persona findPersona(String as_idTurnoHistoria)
	    throws B2BException
	{
		DAOManager ldm_manager;
		Persona    lp_return;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		lp_return       = null;

		try
		{
			if(StringUtils.isValidString(as_idTurnoHistoria))
			{
				TurnoHistoria lth_turnoHistoria;
				lth_turnoHistoria = DaoCreator.getTurnoHistoriaDAO(ldm_manager)
						                          .findById(NumericUtils.getLongWrapper(as_idTurnoHistoria));

				if(lth_turnoHistoria != null)
				{
					Solicitud ls_solicitud;
					ls_solicitud = DaoCreator.getSolicitudDAO(ldm_manager).findById(lth_turnoHistoria.getIdSolicitud());

					if(ls_solicitud != null)
					{
						Persona lp_persona;
						lp_persona = DaoCreator.getPersonaDAO(ldm_manager).findById(ls_solicitud.getIdPersona());

						if(lp_persona != null)
							lp_return = lp_persona;
					}
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findTipoActos", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lp_return;
	}

	/**
	 * Método para realizar transaccciones con la base de datos para encontrar una
	 * solicitud
	 *
	 * @param as_idTurnoHistoria
	 *            String con el Id turno historia
	 * @return
	 * @throws B2BException
	 */
	public synchronized Solicitud findSolicitudByIdTurnoHistoria(String as_idTurnoHistoria)
	    throws B2BException
	{
		DAOManager ldm_manager;
		Solicitud  ls_return;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		ls_return       = null;

		try
		{
			if(StringUtils.isValidString(as_idTurnoHistoria))
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
						SolicitudDAO ls_DAO;

						ls_DAO        = DaoCreator.getSolicitudDAO(ldm_manager);
						ls_return     = new Solicitud();

						ls_return.setIdSolicitud(ls_idSolicitud);

						ls_return = ls_DAO.findById(ls_return);
					}
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findSolicitudByIdTurnoHistoria", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return ls_return;
	}

	/**
	 * Método para realizar transaccciones con la base de datos para encontrar todos
	 * los Tipos actos
	 *
	 * @param as_idTurno
	 *            String con el id turno
	 * @return
	 * @throws B2BException
	 */
	public synchronized Collection<TipoActo> findTipoActos(String as_idTurno)
	    throws B2BException
	{
		DAOManager           ldm_manager;
		Collection<TipoActo> lcta_return;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		lcta_return     = null;

		try
		{
			Turno    lt_turno;
			TurnoDAO ltd_turnoDAO;

			lt_turno         = new Turno();
			ltd_turnoDAO     = DaoCreator.getTurnoDAO(ldm_manager);

			lt_turno.setIdTurno(as_idTurno);

			lt_turno = ltd_turnoDAO.findById(lt_turno);

			if(lt_turno != null)
			{
				String ls_idSolicitud;
				String ls_idCirculo;

				ls_idSolicitud     = lt_turno.getIdSolicitud();
				ls_idCirculo       = lt_turno.getIdCirculo();

				if(StringUtils.isValidString(ls_idSolicitud) && StringUtils.isValidString(ls_idCirculo))
				{
					Collection<Acto> lca_actos;
					Acto             la_acto;
					ActoDAO          lad_actoDAO;

					la_acto = new Acto();
					la_acto.setIdSolicitud(ls_idSolicitud);
					la_acto.setIdCirculo(ls_idCirculo);

					lad_actoDAO     = DaoCreator.getActoDAO(ldm_manager);
					lca_actos       = lad_actoDAO.findByIdSolicitudCirculo(la_acto);

					if(CollectionUtils.isValidCollection(lca_actos))
					{
						lcta_return = new ArrayList<TipoActo>();

						ConstantesDAO         lc_DAO;
						NaturalezaJuridicaDAO lnj_DAO;

						lc_DAO      = DaoCreator.getConstantesDAO(ldm_manager);
						lnj_DAO     = DaoCreator.getNaturalezaJuridicaDAO(ldm_manager);

						for(Acto la_actoConsulta : lca_actos)
						{
							String     ls_idTipoActo;
							String     ls_idConstante;
							Constantes lc_constante;

							ls_idTipoActo      = la_actoConsulta.getIdTipoActo();
							ls_idConstante     = ConstanteCommon.GRUPO_NATURALEZA_JURIDICA_MEDIDA_CAUTELAR;
							lc_constante       = lc_DAO.findById(ls_idConstante);

							if(lc_constante == null)
							{
								Object[] loa_messageArgs;

								loa_messageArgs        = new String[1];
								loa_messageArgs[0]     = ls_idConstante;

								throw new B2BException(ErrorKeys.ERROR_SIN_CONSTANTE, loa_messageArgs);
							}

							NaturalezaJuridica lnj_grupoNat;
							lnj_grupoNat = new NaturalezaJuridica();

							lnj_grupoNat.setIdGrupoNatJur(lc_constante.getCaracter());
							lnj_grupoNat.setIdNaturalezaJuridica(ls_idTipoActo);

							lnj_grupoNat = lnj_DAO.findNatMedidaCautelar(lnj_grupoNat);

							if(StringUtils.isValidString(ls_idTipoActo))
							{
								TipoActo    lta_tipoActo;
								TipoActoDAO ltad_tipoActoDAO;

								lta_tipoActo         = new TipoActo();
								ltad_tipoActoDAO     = DaoCreator.getTipoActoDAO(ldm_manager);

								lta_tipoActo.setIdTipoActo(ls_idTipoActo);

								lta_tipoActo = ltad_tipoActoDAO.findActoDetailsByTipoActo(lta_tipoActo);

								if((lnj_grupoNat != null) && (lta_tipoActo != null))
								{
									lta_tipoActo.setReferencia(la_actoConsulta.getReferencia());
									lta_tipoActo.setNumeroProceso(la_actoConsulta.getNumeroProceso());
								}

								if(lta_tipoActo != null)
									lcta_return.add(lta_tipoActo);
							}
						}
					}
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findTipoActos", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lcta_return;
	}

	/**
	 * Método para realizar transaccciones con la base de datos para encontrar un
	 * turno
	 *
	 * @param as_idTurno
	 *            String con el id turno
	 * @return
	 * @throws B2BException
	 */
	public synchronized Turno findTurno(String as_idTurno)
	    throws B2BException
	{
		DAOManager ldm_manager;
		Turno      lt_turnoReturn;

		ldm_manager        = DaoManagerFactory.getDAOManager();
		lt_turnoReturn     = null;

		try
		{
			if(StringUtils.isValidString(as_idTurno))
			{
				lt_turnoReturn = DaoCreator.getTurnoDAO(ldm_manager).findById(as_idTurno);

				if(lt_turnoReturn != null)
				{
					LineaProduccionDAO lp_DAO;
					String             ls_tipoExpediente;
					String             ls_tipoExpedienteSI;

					lp_DAO                  = DaoCreator.getLineaProduccionDAO(ldm_manager);
					ls_tipoExpediente       = lt_turnoReturn.getTipoExpediente();
					ls_tipoExpedienteSI     = lt_turnoReturn.getTipoExpedienteSI();

					if(StringUtils.isValidString(ls_tipoExpediente))
					{
						LineaProduccion llp_lineaProduccion;

						llp_lineaProduccion = new LineaProduccion();

						llp_lineaProduccion.setIdLineaProduccion(ls_tipoExpediente);

						llp_lineaProduccion = lp_DAO.findById(llp_lineaProduccion);

						if(llp_lineaProduccion != null)
							lt_turnoReturn.setNombreTipoExpediente(llp_lineaProduccion.getNombre());
					}

					if(StringUtils.isValidString(ls_tipoExpedienteSI))
					{
						LineaProduccion llp_lineaProduccion;

						llp_lineaProduccion = new LineaProduccion();

						llp_lineaProduccion.setIdLineaProduccion(ls_tipoExpedienteSI);

						llp_lineaProduccion = lp_DAO.findById(llp_lineaProduccion);

						if(llp_lineaProduccion != null)
							lt_turnoReturn.setNombreTipoExpedienteSI(llp_lineaProduccion.getNombre());
					}
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findTurno", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lt_turnoReturn;
	}

	/**
	 * Método encargado de generar alertas en calificación vbajo los parametros del requerimiento.
	 *
	 * @param acrc_data Colección que contiene los actos a validar.
	 * @param lrc_data Objeto que cotniene la información para realizar las consultas.
	 * @return Mapa que contiene las alertas generadas.
	 * @throws B2BException
	 */
	public synchronized Map<String, Object[]> generarAlertas(
	    Collection<RegistroCalificacion> acrc_data, RegistroCalificacion lrc_data
	)
	    throws B2BException
	{
		DAOManager            ldm_manager;
		Map<String, Object[]> lmsaoa_return;

		ldm_manager       = DaoManagerFactory.getDAOManager();
		lmsaoa_return     = null;

		try
		{
			if(CollectionUtils.isValidCollection(acrc_data) && (lrc_data != null))
			{
				String ls_idCirculo;
				Long   ll_idMatricula;

				ls_idCirculo       = lrc_data.getIdCirculo();
				ll_idMatricula     = lrc_data.getIdMatricula();

				if(StringUtils.isValidString(ls_idCirculo) && NumericUtils.isValidLong(ll_idMatricula))
				{
					com.bachue.snr.prosnr01.dao.acc.AnotacionPredioCiudadanoDAO lapc_DAO;

					lapc_DAO = DaoCreator.getAccAnotacionPredioCiudadanoDAO(ldm_manager);

					for(RegistroCalificacion lrc_iterador : acrc_data)
					{
						if(lrc_iterador != null)
						{
							String ls_codActo;

							ls_codActo = lrc_iterador.getCodActo();

							if(StringUtils.isValidString(ls_codActo))
							{
								boolean              lb_baldios;
								boolean              lb_englobe;
								boolean              lb_patrimonioFamiliar;
								boolean              lb_afectacionFamiliar;
								Map<Integer, Object> lmio_criterios;
								String               ls_view;
								ViewDataReportDAO    vdt_dao;

								lb_baldios                = false;
								lb_englobe                = false;
								lb_patrimonioFamiliar     = false;
								lb_afectacionFamiliar     = false;
								lmio_criterios            = new HashMap<Integer, Object>();
								lmsaoa_return             = new HashMap<String, Object[]>();
								ls_view                   = ViewsCommon.VW_CIUDADANO_ANOTACION_PREDIO;
								vdt_dao                   = DaoCreator.getViewDataReportDAO(ldm_manager);

								{
									Map<String, String> lmss_codigos;

									lmss_codigos     = generarCodigos(
										    ConstanteCommon.NAT_JURIDICA_BALDIOS, ldm_manager
										);
									lb_baldios       = CollectionUtils.isValidCollection(lmss_codigos)
											&& lmss_codigos.containsKey(ls_codActo);
								}

								{
									Map<String, String> lmss_codigos;

									lmss_codigos     = generarCodigos(
										    ConstanteCommon.CODIGOS_ACTOS_ENGLOBES, ldm_manager
										);
									lb_englobe       = CollectionUtils.isValidCollection(lmss_codigos)
											&& lmss_codigos.containsKey(ls_codActo);
								}

								{
									Map<String, String> lmss_codigos;

									lmss_codigos              = generarCodigos(
										    ConstanteCommon.ACTO_PATRIMONIO_FAMILIA, ldm_manager
										);
									lb_patrimonioFamiliar     = CollectionUtils.isValidCollection(lmss_codigos)
											&& lmss_codigos.containsKey(ls_codActo);
								}

								{
									Map<String, String> lmss_codigos;

									lmss_codigos              = generarCodigos(
										    ConstanteCommon.CODIGOS_ACTOS_AFECTACION_VIVIENDA_FAMILIAR, ldm_manager
										);
									lb_afectacionFamiliar     = CollectionUtils.isValidCollection(lmss_codigos)
											&& lmss_codigos.containsKey(ls_codActo);
								}

								if(lb_baldios || lb_englobe)
								{
									Collection<Map<String, Object>> lcmso_resultados;
									BigDecimal                      lbd_idAnotacion;
									String[]                        lsa_arg;

									lbd_idAnotacion     = null;
									lsa_arg             = new String[1];

									lmio_criterios.put(new Integer(1), TipoActoCommon.BALDIOS);
									lmio_criterios.put(new Integer(2), ls_idCirculo);
									lmio_criterios.put(new Integer(3), ll_idMatricula);

									lcmso_resultados = vdt_dao.findByViewName(
										    ls_view, vdt_dao.cs_ODER_BY_AP_ID_ANOTACION_DESC,
										    vdt_dao.cs_WHERE_BALDIOS_ID_CIRCULO_MATRICULA, lmio_criterios
										);

									if(CollectionUtils.isValidCollection(lcmso_resultados))
									{
										Iterator<Map<String, Object>> limos_iterator;

										limos_iterator = lcmso_resultados.iterator();

										if(limos_iterator.hasNext())
										{
											Map<String, Object> lmso_data;

											lmso_data = limos_iterator.next();

											if(CollectionUtils.isValidCollection(lmso_data))
												lbd_idAnotacion = (BigDecimal)lmso_data.get("AP_ID_ANOTACION");
										}

										lsa_arg[0] = StringUtils.getString(lbd_idAnotacion);

										if(lb_baldios)
											lmsaoa_return.put(
											    MessagesKeys.PREDIO_BALDIO_INSCRITO_ANOTACION_BALDIOS, lsa_arg
											);

										if(lb_englobe)
											lmsaoa_return.put(
											    MessagesKeys.PREDIO_BALDIO_INSCRITO_ANOTACION_ENGLOBE, lsa_arg
											);
									}
								}

								if(lb_patrimonioFamiliar || lb_afectacionFamiliar)
								{
									String ls_idAnotacionPredio;

									ls_idAnotacionPredio = lrc_iterador.getIdAnotacionPredio();

									if(StringUtils.isValidString(ls_idAnotacionPredio))
									{
										com.bachue.snr.prosnr01.model.sdb.acc.AnotacionPredioCiudadano             lapc_idPredioCiudadano;
										Collection<com.bachue.snr.prosnr01.model.sdb.acc.AnotacionPredioCiudadano> lcapc_ciudadanos;

										lapc_idPredioCiudadano = new com.bachue.snr.prosnr01.model.sdb.acc.AnotacionPredioCiudadano();

										lapc_idPredioCiudadano.setIdAnotacionPredio(ls_idAnotacionPredio);

										lcapc_ciudadanos = lapc_DAO.findByIdAnotacion(lapc_idPredioCiudadano);

										if(CollectionUtils.isValidCollection(lcapc_ciudadanos))
										{
											for(com.bachue.snr.prosnr01.model.sdb.acc.AnotacionPredioCiudadano lapc_iterador : lcapc_ciudadanos)
											{
												if(lapc_iterador != null)
												{
													Map<String, Object[]> lmso_data;

													lmso_data = alertasIntervinientesBaldios(
														    lapc_iterador.getIdPersona(), false, ls_codActo, ldm_manager
														);

													if(CollectionUtils.isValidCollection(lmso_data))
														lmsaoa_return.putAll(lmso_data);
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

			clh_LOGGER.error("generarAlertas", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lmsaoa_return;
	}

	/**
	 * Método encargado de generar archivo en formato pdf  utilizando  las plantillas  de nota informativa.
	 *
	 * @param arc_rc Objeto de tipo RegistroCalificacion que contiene los datos necesarios para buscar la información a incluir en la plantilla.
	 * @param as_userId Variable de tipo String que contiene el usuario que realiza la acción.
	 * @param as_remoteIp Variable de tipo String que contiene la dirección ip remota desde donde seque realiza la acción.
	 * @return Arreglo de bytes que contiene la plantilla generada.
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see byte[]
	 */
	public synchronized byte[] generateFileNotaInformativa(
	    RegistroCalificacion arc_rc, String as_userId, String as_remoteIp
	)
	    throws B2BException
	{
		DAOManager ldm_manager;
		byte[]     lba_notaInformativa;

		ldm_manager             = DaoManagerFactory.getDAOManager();
		lba_notaInformativa     = null;

		try
		{
			if(arc_rc != null)
			{
				Long   ll_idTurnoHistoria;
				String ls_idTurno;

				ll_idTurnoHistoria     = arc_rc.getIdTurnoHistoria();
				ls_idTurno             = arc_rc.getTurno();

				if(StringUtils.isValidString(ls_idTurno) && NumericUtils.isValidLong(ll_idTurnoHistoria))
				{
					Turno lt_turno;

					lt_turno = new Turno();

					lt_turno.setIdTurno(ls_idTurno);

					lt_turno = DaoCreator.getTurnoDAO(ldm_manager).findById(lt_turno);

					if(lt_turno != null)
					{
						Constantes lc_constante;
						String     ls_constante;

						byte[] lba_plantilla;

						ls_constante     = ConstanteCommon.PLANTILLA_NOTA_INFORMATIVA_POR_PAGO_EN_EXCESO;
						lc_constante     = new Constantes();

						lc_constante.setIdConstante(ls_constante);

						lc_constante = DaoCreator.getConstantesDAO(ldm_manager).findImagen(lc_constante);

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
							Date                ld_fechaTurno;
							Map<String, String> lmss_datos;
							String              ls_idCirculo;
							String              ls_idSolicitud;
							String              ls_plantilla;
							String              ls_tag;
							String              ls_tag2;
							String              ls_tag3;

							ld_fechaTurno      = lt_turno.getFechaCreacion();
							lmss_datos         = null;
							ls_idCirculo       = lt_turno.getIdCirculo();
							ls_idSolicitud     = lt_turno.getIdSolicitud();
							ls_plantilla       = new String(lba_plantilla);

							{
								ls_tag           = "<TAG_FECHA_TURNO>";
								ls_plantilla     = escribirTagFechaLarga(ls_plantilla, ls_tag, ld_fechaTurno);
							}

							{
								TurnoDerivado ltd_turnoDerivado;

								ltd_turnoDerivado     = DaoCreator.getTurnoDerivadoDAO(ldm_manager)
										                              .findByIdTurnoPadreVinculado(ls_idTurno, false);
								ls_tag                = "<TAG_TURNO_DERIVADO>";

								if((ltd_turnoDerivado != null) && ls_plantilla.contains(ls_tag))
									ls_plantilla = ls_plantilla.replace(ls_tag, ltd_turnoDerivado.getIdTurnoHijo());
							}

							{
								ls_tag = "<TAG_ACC_TUR_ID_TURNO>";

								if(ls_plantilla.contains(ls_tag))
									ls_plantilla = ls_plantilla.replace(ls_tag, ls_idTurno);
							}

							ls_tag      = "<TAG_MUN_OFI_ORIGEN>";
							ls_tag2     = "<TAG_OFICINA>";

							if(
							    (ls_plantilla.contains(ls_tag) || ls_plantilla.contains(ls_tag2))
								    && StringUtils.isValidString(ls_idCirculo)
							)
							{
								Municipio lm_municipio;

								lm_municipio     = new Municipio();

								lm_municipio = DaoCreator.getMunicipioDAO(ldm_manager).findByIdCirculo(ls_idCirculo);

								if(lm_municipio != null)
								{
									String ls_munOficinaOrigen;
									ls_munOficinaOrigen = lm_municipio.getNombre();

									if(ls_plantilla.contains(ls_tag) && StringUtils.isValidString(ls_munOficinaOrigen))
									{
										if(ls_plantilla.contains(ls_tag))
											ls_plantilla = ls_plantilla.replace(ls_tag, ls_munOficinaOrigen);

										if(ls_plantilla.contains(ls_tag2))
											ls_plantilla = ls_plantilla.replace(ls_tag2, ls_munOficinaOrigen);
									}
								}
							}

							{
								ls_tag = "<TAG_USUARIO>";

								if(ls_plantilla.contains(ls_tag))
									ls_plantilla = ls_plantilla.replace(ls_tag, as_userId);
							}

							{
								ls_plantilla     = escribirTagFechaLarga(ls_plantilla);

								ls_tag = "<TAG_HORA>";

								if(ls_plantilla.contains(ls_tag))
								{
									DateFormat lsf_formatTime;
									String     ls_hora;

									lsf_formatTime     = new SimpleDateFormat(FormatoFechaCommon.HORA);
									ls_hora            = lsf_formatTime.format(new Date());
									ls_plantilla       = ls_plantilla.replace(ls_tag, ls_hora);
								}
							}

							{
								String ls_observaciones;

								ls_observaciones     = arc_rc.getObservaciones();
								ls_tag               = "<TAG_OBSERVACIONES_TURNO_HISTORIA>";

								if(StringUtils.isValidString(ls_observaciones) && ls_plantilla.contains(ls_tag))
									ls_plantilla = ls_plantilla.replace(
										    ls_tag, StringUtils.getString(ls_observaciones)
										);
							}

							ls_tag = "<TAG_MATRICULAS>";

							if(
							    ls_plantilla.contains(ls_tag) && StringUtils.isValidString(ls_idCirculo)
								    && StringUtils.isValidString(ls_idSolicitud)
							)
							{
								if(StringUtils.isValidString(ls_idSolicitud))
								{
									Collection<SolicitudMatricula> lcsm_matriculas;
									SolicitudMatricula             lsm_param;

									lsm_param = new SolicitudMatricula();

									lsm_param.setIdSolicitud(ls_idSolicitud);
									lsm_param.setIdCirculo(ls_idCirculo);

									lcsm_matriculas = DaoCreator.getSolicitudMatriculaDAO(ldm_manager)
											                        .findByIdSolicitudCirculo(lsm_param);

									if(CollectionUtils.isValidCollection(lcsm_matriculas))
									{
										Iterator<SolicitudMatricula> lism_iterator;
										StringBuilder                lsb_sb;
										String                       ls_separador;

										lism_iterator     = lcsm_matriculas.iterator();
										lsb_sb            = new StringBuilder();
										ls_separador      = "";

										while(lism_iterator.hasNext())
										{
											SolicitudMatricula lsm_iterador;

											lsm_iterador = lism_iterator.next();

											if(lsm_iterador != null)
											{
												ls_separador = lism_iterator.hasNext() ? " ," : "";
												lsb_sb.append(
												    ls_idCirculo + IdentificadoresCommon.SIMBOLO_GUION
												    + lsm_iterador.getIdMatricula() + ls_separador
												);
											}
										}

										ls_plantilla = ls_plantilla.replace(ls_tag, lsb_sb.toString());
									}
								}
							}

							ls_tag      = "<TAG_BQN_DOC_ID_TIPO_DOC>";
							ls_tag2     = "<TAG_BQN_DOC_NUMERO>";
							ls_tag3     = "<TAG_BQN_DOC_FECHA>";

							if(
							    (ls_plantilla.contains(ls_tag) || ls_plantilla.contains(ls_tag2)
								    || ls_plantilla.contains(ls_tag3)) && StringUtils.isValidString(ls_idSolicitud)
							)
							{
								Solicitud ls_solicitud;

								ls_solicitud = new Solicitud();

								ls_solicitud.setIdSolicitud(ls_idSolicitud);

								ls_solicitud = DaoCreator.getSolicitudDAO(ldm_manager).findById(ls_solicitud);

								if(ls_solicitud != null)
								{
									String ls_idDocumento;

									ls_idDocumento = ls_solicitud.getIdDocumento();

									if(StringUtils.isValidString(ls_idDocumento))
									{
										Documento ld_documento;

										ld_documento = new Documento();

										ld_documento.setIdDocumento(ls_idDocumento);

										ld_documento = DaoCreator.getDocumentoDAO(ldm_manager).findById(ld_documento);

										if(ld_documento != null)
										{
											Date   ld_fechaDocumento;
											String ls_tipoDocumento;
											String ls_numero;

											ld_fechaDocumento     = ld_documento.getFechaDocumento();
											ls_tipoDocumento      = ld_documento.getIdTipoDocumento();
											ls_numero             = ld_documento.getNumero();

											if(ld_fechaDocumento != null)
												ls_plantilla = escribirTagFechaLarga(
													    ls_plantilla, ls_tag3, ld_fechaDocumento
													);

											if(ls_plantilla.contains(ls_tag2) && StringUtils.isValidString(ls_numero))
												ls_plantilla = ls_plantilla.replace(ls_tag2, ls_numero);

											if(StringUtils.isValidString(ls_tipoDocumento))
											{
												TipoDocumentoPublico ltdp_tipoDocumento;

												ltdp_tipoDocumento = new TipoDocumentoPublico();

												ltdp_tipoDocumento.setIdTipoDocumento(ls_tipoDocumento);

												ltdp_tipoDocumento = DaoCreator.getTipoDocumentoPublicoDAO(ldm_manager)
														                           .findById(ltdp_tipoDocumento);

												if(ltdp_tipoDocumento != null)
												{
													String ls_nombre;

													ls_nombre = ltdp_tipoDocumento.getNombre();

													if(
													    ls_plantilla.contains(ls_tag)
														    && StringUtils.isValidString(ls_nombre)
													)
														ls_plantilla = ls_plantilla.replace(ls_tag, ls_nombre);
												}
											}
										}
									}
								}
							}

							lmss_datos              = finalizarPlantilla(ls_plantilla, ll_idTurnoHistoria, ldm_manager);
							ls_plantilla            = lmss_datos.get(IdentificadoresCommon.PLANTILLA);
							lba_notaInformativa     = new PDFGenerator().convertirRTFToPDF(
								    ls_plantilla.getBytes(), ldm_manager
								);

							if(lba_notaInformativa != null)
							{
								Imagenes    li_imagenes;
								ImagenesDAO li_DAO;
								long        ll_idImagenTemp;

								li_imagenes     = new Imagenes();
								li_DAO          = DaoCreator.getImagenesDAO(ldm_manager);

								li_imagenes.setTipoArchivo(ExtensionCommon.PDF_MAYUS);
								li_imagenes.setIdUsuarioCreacion(as_userId);
								li_imagenes.setIpCreacion(as_remoteIp);
								li_imagenes.setImagenBlob(lba_notaInformativa);
								li_imagenes.setCodigoVerificacion(
								    lmss_datos.get(IdentificadoresCommon.CODIGO_VERIFICACION)
								);

								ll_idImagenTemp = li_DAO.insertOrUpdate(li_imagenes, true);

								if(ll_idImagenTemp > 0)
								{
									DocumentosSalida    lds_documentoSalida;
									DocumentosSalidaDAO lds_DAO;
									Long                ll_idImagen;

									lds_documentoSalida     = new DocumentosSalida();
									lds_DAO                 = DaoCreator.getDocumentosSalidaDAO(ldm_manager);
									ll_idImagen             = NumericUtils.getLongWrapper(ll_idImagenTemp);

									lds_documentoSalida.setIdSolicitud(ls_idSolicitud);
									lds_documentoSalida.setIdTurno(ls_idTurno);
									lds_documentoSalida.setTipo(TipoArchivoCommon.DEVOLUCION_DINERO);
									lds_documentoSalida.setEstado(EstadoCommon.ACTIVO);
									lds_documentoSalida.setReferenciaSalida(ls_idTurno);

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

										lds_documentoSalida.setIdTurno(ls_idTurno);
										lds_documentoSalida.setIdSolicitud(ls_idSolicitud);
										lds_documentoSalida.setIdImagen(ll_idImagen);
										lds_documentoSalida.setTipo(TipoArchivoCommon.DEVOLUCION_DINERO);
										lds_documentoSalida.setIdTipoDocumental(
										    TipoDocumentalCommon.NOTA_INFORMATIVA_PAGO_EN_EXCESO
										);
										lds_documentoSalida.setRepositorio(RepositorioCommon.TEMPORAL);
										lds_documentoSalida.setEstado(EstadoCommon.ACTIVO);
										lds_documentoSalida.setReferenciaSalida(ls_idTurno);
										lds_documentoSalida.setIdTurnoHistoria(
										    NumericUtils.getInteger(ll_idTurnoHistoria)
										);
										lds_documentoSalida.setIdUsuarioCreacion(as_userId);
										lds_documentoSalida.setIpCreacion(as_remoteIp);

										lds_DAO.insertOrUpdate(lds_documentoSalida, true);
									}
								}
								else
									throw new B2BException(ErrorKeys.NO_SE_GENERO_ARCHIVO);
							}
							else
								throw new B2BException(ErrorKeys.NO_SE_GENERO_ARCHIVO);
						}
					}
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("generateFileNotaInformativa", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lba_notaInformativa;
	}

	/**
	 * Método para poder generar el archivo para la cancelaciones
	 *
	 * @param aotc_tc
	 *            Objeto para extraer información necesaria como el turno historia,
	 *            usuario y si es nota devolutiva
	 * @param ab_firmaMasiva
	 *            Booleano para saber si es de firma masiva
	 * @return
	 * @throws B2BException
	 */
	public synchronized byte[] genereteFileCancelacion(
	    RegistroCalificacion aotc_tc, boolean ab_firmaMasiva, String as_userId, String as_remoteIp
	)
	    throws B2BException
	{
		DAOManager ldm_manager;
		byte[]     lba_registroInscripcion;

		ldm_manager                 = DaoManagerFactory.getDAOManager();
		lba_registroInscripcion     = null;

		try
		{
			lba_registroInscripcion = genereteFileCancelacion(
				    ldm_manager, aotc_tc, ab_firmaMasiva, as_userId, as_remoteIp
				);
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("genereteFileCancelacion", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lba_registroInscripcion;
	}

	/**
	 * Método para poder generar el archivo para la cancelaciones
	 *
	 * @param aotc_tc
	 *            Objeto para extraer información necesaria como el turno historia,
	 *            usuario y si es nota devolutiva
	 * @param ab_firmaMasiva
	 *            Booleano para saber si es de firma masiva
	 * @return
	 * @throws B2BException
	 */
	public synchronized byte[] genereteFileCancelacion(
	    DAOManager adm_manager, RegistroCalificacion aotc_tc, boolean ab_firmaMasiva, String as_userId,
	    String as_remoteIp
	)
	    throws B2BException
	{
		byte[] lba_registroInscripcion;
		lba_registroInscripcion = null;

		try
		{
			String                    ls_constante;
			byte[]                    lba_plantilla;
			Constantes                loc_datos;
			Constantes                loc_plantilla;
			String                    ls_plantilla;
			TurnoHistoriaDAO          lothd_thd;
			SolicitudDAO              lsd_solicitudDAO;
			TurnoHistoria             loth_detalle;
			StringBuilder             lsb_sb;
			String                    ls_idSolicitud;
			String                    ls_idTurno;
			Long                      ll_idTurnoHistoria;
			String                    ls_idDocumento;
			String                    ls_usuario;
			TurnoDAO                  ltd_DAO;
			CirculoRegistralDao       lcrd_DAO;
			TipoDocumentoPublicoDAO   ltdpd_DAO;
			OficinaOrigenDAO          lood_DAO;
			MunicipioDAO              lomd_DAO;
			DepartamentoDAO           lodd_DAO;
			DocumentoDAO              lodmd_DAO;
			AnotacionCancelacionDAO   loacd_dao;
			SolicitudMatriculaActoDAO lsmad_DAO;
			DocumentosSalidaDAO       ldsdao_DAO;
			ActoDAO                   ladao_DAO;
			String                    ls_turnoDerivado;
			boolean                   lb_medidaCautelar;
			boolean                   lb_notaDevMedidaCautelar;

			loc_datos                    = new Constantes();
			loc_plantilla                = new Constantes();
			loth_detalle                 = new TurnoHistoria();
			ls_idTurno                   = "";
			lb_medidaCautelar            = false;
			lb_notaDevMedidaCautelar     = false;

			if((aotc_tc != null) && (adm_manager != null))
			{
				lb_medidaCautelar            = aotc_tc.isMedidaCautelar();
				lb_notaDevMedidaCautelar     = aotc_tc.isNotaDevolutivaMedidaCautelar();

				lothd_thd              = DaoCreator.getTurnoHistoriaDAO(adm_manager);
				lsd_solicitudDAO       = DaoCreator.getSolicitudDAO(adm_manager);
				ltd_DAO                = DaoCreator.getTurnoDAO(adm_manager);
				lcrd_DAO               = DaoCreator.getCirculoRegistralDAO(adm_manager);
				ltdpd_DAO              = DaoCreator.getTipoDocumentoPublicoDAO(adm_manager);
				lood_DAO               = DaoCreator.getOficinaOrigenDAO(adm_manager);
				lomd_DAO               = DaoCreator.getMunicipioDAO(adm_manager);
				lodd_DAO               = DaoCreator.getDepartamentoDAO(adm_manager);
				lodmd_DAO              = DaoCreator.getDocumentoDAO(adm_manager);
				loacd_dao              = DaoCreator.getAnotacionCancelacionDAO(adm_manager);
				lsmad_DAO              = DaoCreator.getSolicitudMatriculaActoDAO(adm_manager);
				ladao_DAO              = DaoCreator.getActoDAO(adm_manager);
				ldsdao_DAO             = DaoCreator.getDocumentosSalidaDAO(adm_manager);
				ll_idTurnoHistoria     = aotc_tc.getIdTurnoHistoria();
				ls_usuario             = aotc_tc.getUserId();

				if(aotc_tc.isNotaDevolutiva())
					ls_constante = ConstanteCommon.COMUNICADO_CANCELACIONES_NOTA_DEV;
				else if(lb_notaDevMedidaCautelar)
					ls_constante = ConstanteCommon.PLANTILLA_NOTA_DEVOLUTIVA_MEDIDA_CAUTELAR;
				else if(lb_medidaCautelar)
					ls_constante = ConstanteCommon.PLANTILLA_REGISTRO_MEDIDA_CAUTELAR;
				else
					ls_constante = ConstanteCommon.COMUNICADO_CANCELACIONES;

				loc_datos.setIdConstante(ls_constante);
				loc_plantilla = DaoCreator.getConstantesDAO(adm_manager).findImagen(loc_datos);

				if(loc_plantilla != null)
				{
					lba_plantilla = loc_plantilla.getImagenes();

					if(lba_plantilla != null)
					{
						ls_plantilla = new String(lba_plantilla);

						if(StringUtils.isValidString(ls_plantilla))
						{
							loth_detalle.setIdTurnoHistoria(ll_idTurnoHistoria);
							loth_detalle = lothd_thd.findById(loth_detalle);

							if(loth_detalle != null)
							{
								Map<String, String> lmss_datos;

								String              ls_consecutivoOficio;
								String              ls_referenciaSalida;
								String              ls_correoElectronico;
								String              ls_telefono;

								ls_consecutivoOficio     = null;
								ls_referenciaSalida      = null;
								ls_correoElectronico     = null;
								ls_telefono              = null;
								lmss_datos               = null;
								ls_idTurno               = loth_detalle.getIdTurno();
								ls_idSolicitud           = loth_detalle.getIdSolicitud();

								boolean lb_validMedidaCautelar;
								lb_validMedidaCautelar   = false;

								if(aotc_tc.isMedidaCautelar() || lb_notaDevMedidaCautelar)
									lb_validMedidaCautelar = true;

								if(StringUtils.isValidString(ls_idSolicitud))
								{
									Solicitud ls_solicitud;

									ls_solicitud = lsd_solicitudDAO.findById(ls_idSolicitud);

									if(ls_solicitud != null)
									{
										String ls_tag;

										ls_tag = "<TAG_ACC_SOLICITUD_NIR>";

										if(ls_plantilla.contains(ls_tag))
											ls_plantilla = ls_plantilla.replace(
												    ls_tag, StringUtils.getStringNotNull(ls_solicitud.getNir())
												);

										ls_idDocumento = ls_solicitud.getIdDocumento();

										if(StringUtils.isValidString(ls_idDocumento))
										{
											Documento ld_documento;
											ld_documento = new Documento();

											ld_documento.setIdDocumento(ls_idDocumento);

											ld_documento = lodmd_DAO.findById(ld_documento);

											if(ld_documento != null)
											{
												OficinaOrigen loo_oficinaOrigen;
												loo_oficinaOrigen = new OficinaOrigen();

												loo_oficinaOrigen.setIdOficinaOrigen(ld_documento.getIdOficinaOrigen());
												loo_oficinaOrigen.setVersion(ld_documento.getVersion());

												loo_oficinaOrigen = lood_DAO.findById(loo_oficinaOrigen);

												if(loo_oficinaOrigen != null)
												{
													String ls_pais;
													String ls_departamento;
													String ls_municipio;
													String ls_nombreOficinaOrigen;
													String ls_munOficinaOrigen;
													String ls_direccion;
													String ls_tipoDocumento;
													String ls_numero;
													Date   ld_fechaDoc;

													ls_direccion               = StringUtils.getStringNotNull(
														    loo_oficinaOrigen.getDireccion()
														);
													ls_pais                    = loo_oficinaOrigen.getIdPais();
													ls_departamento            = loo_oficinaOrigen.getIdDepartamento();
													ls_municipio               = loo_oficinaOrigen.getIdMunicipio();
													ls_nombreOficinaOrigen     = loo_oficinaOrigen.getNombre();
													ls_munOficinaOrigen        = "";
													lsb_sb                     = new StringBuilder(ls_direccion);
													ls_tipoDocumento           = ld_documento.getIdTipoDocumento();
													ls_numero                  = ld_documento.getNumero();
													ld_fechaDoc                = ld_documento.getFechaDocumento();

													if(ab_firmaMasiva)
													{
														ls_tag = "<TAG_OFICIO>";

														if(ls_plantilla.contains(ls_tag))
														{
															NumeracionOficiosCirculo lnoc_numeracionOficios;
															lnoc_numeracionOficios = findNumeracionOficiosCirculo(
																    loth_detalle, adm_manager, as_userId, as_remoteIp
																);

															if(lnoc_numeracionOficios != null)
															{
																ls_consecutivoOficio     = lnoc_numeracionOficios
																		.getConsecutivoGenerado();

																ls_plantilla = ls_plantilla.replace(
																	    ls_tag, ls_consecutivoOficio
																	);
															}
														}

														ls_tag = "<TAG_REFERENCIA_SALIDA>";

														if(ls_plantilla.contains(ls_tag))
														{
															ls_referenciaSalida = generarIdCorrespondencia(
																    loth_detalle, adm_manager, false
																);

															if(ls_plantilla.contains(ls_tag))
																ls_plantilla = ls_plantilla.replace(
																	    ls_tag, ls_referenciaSalida
																	);
														}

														SolicitudMatriculaActo lsma_solicitudMatriculaActo;
														lsma_solicitudMatriculaActo = new SolicitudMatriculaActo();
														lsma_solicitudMatriculaActo.setIdTurno(ls_idTurno);

														lsma_solicitudMatriculaActo = lsmad_DAO.findByIdTurno(
															    lsma_solicitudMatriculaActo
															);

														Collection<Acto>               lca_actos;
														Collection<NaturalezaJuridica> lcnj_nj;
														boolean                        lb_encontrado;
														String                         ls_referencia;
														String                         ls_numeroProceso;
														String                         ls_tipoActo;
														String                         ls_naturalezaJuridica;
														String                         ls_grupoNaturalezaJuridica;
														lcnj_nj              = DaoCreator.getNaturalezaJuridicaDAO(
															    adm_manager
															).findAllBySolicitud(loth_detalle.getIdSolicitud());
														lca_actos            = ladao_DAO.findByIdSolicitud(
															    loth_detalle.getIdSolicitud()
															);
														lb_encontrado        = false;
														ls_referencia        = null;
														ls_numeroProceso     = null;

														if(
														    CollectionUtils.isValidCollection(lca_actos)
															    && CollectionUtils.isValidCollection(lcnj_nj)
														)
														{
															for(Acto la_iterador : lca_actos)
															{
																if((la_iterador != null) && !lb_encontrado)
																{
																	for(NaturalezaJuridica inj_iterador : lcnj_nj)
																	{
																		ls_tipoActo = la_iterador.getIdTipoActo();

																		if(inj_iterador != null)
																		{
																			ls_naturalezaJuridica          = inj_iterador
																					.getIdNaturalezaJuridica();
																			ls_grupoNaturalezaJuridica     = inj_iterador
																					.getIdGrupoNatJur();

																			if(
																			    StringUtils.isValidString(
																				        ls_naturalezaJuridica
																				    )
																				    && StringUtils.isValidString(
																				        ls_grupoNaturalezaJuridica
																				    )
																				    && ls_tipoActo.equalsIgnoreCase(
																				        ls_naturalezaJuridica
																				    )
																				    && ls_grupoNaturalezaJuridica
																				    .equalsIgnoreCase("0400")
																			)
																			{
																				lb_encontrado        = true;
																				ls_referencia        = la_iterador
																						.getReferencia();
																				ls_numeroProceso     = la_iterador
																						.getNumeroProceso();
																			}
																		}
																	}
																}
															}
														}

														{
															ls_tag = "<TAG_REFERENCIA>";

															if(ls_plantilla.contains(ls_tag))
																ls_plantilla = ls_plantilla.replace(
																	    ls_tag,
																	    StringUtils.getStringNotNull(ls_referencia)
																	);

															ls_tag = "<TAG_NUMERO_PROCESO>";

															if(ls_plantilla.contains(ls_tag))
																ls_plantilla = ls_plantilla.replace(
																	    ls_tag,
																	    StringUtils.getStringNotNull(ls_numeroProceso)
																	);
														}
													}
													else
													{
														ls_tag = "<TAG_REFERENCIA>";

														if(ls_plantilla.contains(ls_tag))
															ls_plantilla = ls_plantilla.replace(
																    ls_tag,
																    StringUtils.getStringNotNull(
																        aotc_tc.getReferencia()
																    )
																);

														ls_tag = "<TAG_NUMERO_PROCESO>";

														if(ls_plantilla.contains(ls_tag))
															ls_plantilla = ls_plantilla.replace(
																    ls_tag,
																    StringUtils.getStringNotNull(
																        aotc_tc.getNumeroProceso()
																    )
																);
													}

													if(lb_medidaCautelar || lb_notaDevMedidaCautelar)
													{
														if(!ab_firmaMasiva)
														{
															OficinaOrigen loo_oficina;
															loo_oficina = aotc_tc.getOficinaOrigenMedidaCautelar();

															if(loo_oficina != null)
															{
																ls_correoElectronico = loo_oficina.getCorreoElectronico();

																String ls_tagTemp;

																ls_tagTemp = "<TAG_CORREO_ELECTRONICO>";

																if(
																    ls_plantilla.contains(ls_tagTemp)
																	    && StringUtils.isValidString(
																	        ls_correoElectronico
																	    )
																)
																	ls_plantilla = saltoDeCarroDespues(
																		    ls_plantilla, ls_tagTemp,
																		    StringUtils.getStringNotNull(
																		        ls_correoElectronico
																		    )
																		);

																ls_tagTemp = "<TAG_DIR_OFI_ORIGEN>";

																if(ls_plantilla.contains(ls_tagTemp))
																{
																	String ls_direccionOfi;
																	ls_direccionOfi = loo_oficina.getDireccion();

																	if(StringUtils.isValidString(ls_direccionOfi))
																		ls_plantilla = saltoDeCarroDespues(
																			    ls_plantilla, ls_tagTemp,
																			    ls_direccionOfi
																			);
																}

																ls_telefono     = loo_oficina.getTelefono();
																ls_tagTemp      = "<TAG_TELEFONO>";

																if(ls_plantilla.contains(ls_tagTemp))
																	ls_plantilla = ls_plantilla.replace(
																		    ls_tagTemp,
																		    StringUtils.getStringNotNull(ls_telefono)
																		);
															}
														}
														else
														{
															DocumentosSalida lds_documentoSalidaTemp;
															lds_documentoSalidaTemp = ldsdao_DAO.findByIdTurnoTipo(
																    ls_idTurno,
																    lb_validMedidaCautelar
																    ? TipoArchivoCommon.COMUNICADO_DEMANDA
																    : TipoArchivoCommon.COMUNICADO_CANCELACION
																);

															if(lds_documentoSalidaTemp != null)
															{
																String ls_tagTemp;

																ls_tagTemp               = "<TAG_CORREO_ELECTRONICO>";
																ls_correoElectronico     = lds_documentoSalidaTemp
																		.getCorreoElectronico();

																if(
																    ls_plantilla.contains(ls_tagTemp)
																	    && StringUtils.isValidString(
																	        ls_correoElectronico
																	    )
																)
																	ls_plantilla = saltoDeCarroDespues(
																		    ls_plantilla, ls_tagTemp,
																		    StringUtils.getStringNotNull(
																		        ls_correoElectronico
																		    )
																		);

																ls_tagTemp = "<TAG_DIR_OFI_ORIGEN>";

																if(ls_plantilla.contains(ls_tagTemp))
																{
																	String ls_direccionOfi;
																	ls_direccionOfi = lds_documentoSalidaTemp
																			.getDireccion();

																	if(StringUtils.isValidString(ls_direccionOfi))
																		ls_plantilla = saltoDeCarroDespues(
																			    ls_plantilla, ls_tagTemp,
																			    ls_direccionOfi
																			);
																}

																ls_tagTemp     = "<TAG_TELEFONO>";

																ls_telefono = lds_documentoSalidaTemp.getTelefono();

																if(ls_plantilla.contains(ls_tagTemp))
																	ls_plantilla = ls_plantilla.replace(
																		    ls_tagTemp,
																		    StringUtils.getStringNotNull(ls_telefono)
																		);
															}
														}
													}

													{
														String             ls_tagDe;
														String             ls_tagA;
														Collection<String> lcs_de;
														Collection<String> lcs_a;
														Collection<String> lcs_matriculas;

														ls_tagDe           = "<TAG_ANOTACION_CIUDADANO_ROL_DE>";
														ls_tagA            = "<TAG_ANOTACION_CIUDADANO_ROL_A>";
														lcs_de             = new LinkedList<String>();
														lcs_a              = new LinkedList<String>();
														lcs_matriculas     = new LinkedList<String>();

														if(
														    ls_plantilla.contains(ls_tagDe)
															    || ls_plantilla.contains(ls_tagA)
														)
														{
															long ll_idTurnoH;
															ll_idTurnoH = NumericUtils.getLong(
																    loth_detalle.getIdTurnoHistoria()
																);

															com.bachue.snr.prosnr01.dao.acc.AnotacionPredioDAO lap_DAO;
															PersonaDAO                                         lp_DAO;

															lp_DAO      = DaoCreator.getPersonaDAO(adm_manager);
															lap_DAO     = DaoCreator.getAccAnotacionPredioDAO(
																    adm_manager
																);

															Collection<com.bachue.snr.prosnr01.model.sdb.acc.AnotacionPredio> lap_anotaciones;
															lap_anotaciones = lap_DAO.findByTurnoHistoria(
																    NumericUtils.getBigDecimal(ll_idTurnoH)
																);

															if(CollectionUtils.isValidCollection(lap_anotaciones))
															{
																ConstantesDAO                                               lc_DAO;
																NaturalezaJuridicaDAO                                       lnj_DAO;
																com.bachue.snr.prosnr01.dao.acc.AnotacionPredioCiudadanoDAO lapc_DAO;
																Map<String, String>                                         lmss_personasDe;
																Map<String, String>                                         lmss_personasA;

																lc_DAO              = DaoCreator.getConstantesDAO(
																	    adm_manager
																	);
																lnj_DAO             = DaoCreator
																		.getNaturalezaJuridicaDAO(adm_manager);
																lapc_DAO            = DaoCreator
																		.getAccAnotacionPredioCiudadanoDAO(adm_manager);
																lmss_personasDe     = new HashMap<String, String>();
																lmss_personasA      = new HashMap<String, String>();

																for(com.bachue.snr.prosnr01.model.sdb.acc.AnotacionPredio lap_data : lap_anotaciones)
																{
																	if(lap_data != null)
																	{
																		String             ls_idConstante;
																		Constantes         lc_constante;
																		NaturalezaJuridica lnj_natJur;

																		ls_idConstante     = ConstanteCommon.GRUPO_NATURALEZA_JURIDICA_MEDIDA_CAUTELAR;
																		lc_constante       = lc_DAO.findById(
																			    ls_idConstante
																			);
																		lnj_natJur         = new NaturalezaJuridica();

																		if(lc_constante == null)
																		{
																			Object[] loa_messageArgs;

																			loa_messageArgs        = new String[1];
																			loa_messageArgs[0]     = ls_idConstante;

																			throw new B2BException(
																			    ErrorKeys.ERROR_SIN_CONSTANTE,
																			    loa_messageArgs
																			);
																		}

																		lnj_natJur.setIdGrupoNatJur(
																		    lc_constante.getCaracter()
																		);
																		lnj_natJur.setIdNaturalezaJuridica(
																		    lap_data.getIdNaturalezaJuridica()
																		);

																		lnj_natJur = lnj_DAO.findNatMedidaCautelar(
																			    lnj_natJur
																			);

																		if(lnj_natJur != null)
																		{
																			com.bachue.snr.prosnr01.model.sdb.acc.AnotacionPredioCiudadano lapc_temp;
																			lapc_temp = new com.bachue.snr.prosnr01.model.sdb.acc.AnotacionPredioCiudadano();

																			lapc_temp.setIdAnotacionPredio(
																			    lap_data.getIdAnotacionPredio()
																			);

																			Collection<com.bachue.snr.prosnr01.model.sdb.acc.AnotacionPredioCiudadano> lcapc_anotacionesPC;
																			lcapc_anotacionesPC = lapc_DAO
																					.findByIdAnotacion(lapc_temp);

																			if(
																			    !CollectionUtils.isValidCollection(
																				        lcapc_anotacionesPC
																				    ) && !lb_notaDevMedidaCautelar
																			)
																				lcapc_anotacionesPC = new LinkedList<com.bachue.snr.prosnr01.model.sdb.acc.AnotacionPredioCiudadano>();

																			for(com.bachue.snr.prosnr01.model.sdb.acc.AnotacionPredioCiudadano lcapc_interv : lcapc_anotacionesPC)
																			{
																				if(lcapc_interv != null)
																				{
																					String ls_cirMat;
																					ls_cirMat = lcapc_interv
																							.getIdCirculo() + "-"
																						+ StringUtils.getString(
																						    lcapc_interv.getIdMatricula()
																						);

																					if(
																					    !lcs_matriculas.contains(
																						        ls_cirMat
																						    )
																					)
																						lcs_matriculas.add(ls_cirMat);

																					Persona lp_persona;
																					lp_persona = new Persona();

																					lp_persona.setIdPersona(
																					    lcapc_interv.getIdPersona()
																					);

																					lp_persona = lp_DAO.findById(
																						    lp_persona
																						);

																					if(lp_persona != null)
																					{
																						String ls_nombrePersona;
																						String ls_numeroDocumento;
																						String ls_tipoDoc;

																						ls_nombrePersona       = "";
																						ls_numeroDocumento     = "";
																						ls_tipoDoc             = "";

																						ls_tipoDoc = lp_persona
																								.getIdDocumentoTipo();

																						String ls_tipoPersona;
																						ls_tipoPersona = StringUtils
																								.getStringNotNull(
																								    lp_persona
																								    .getIdTipoPersona()
																								);

																						if(
																						    StringUtils.isValidString(
																							        ls_tipoDoc
																							    )
																						)
																						{
																							if(
																							    ls_tipoDoc
																								    .equalsIgnoreCase(IdentificadoresCommon.NIT)
																								    && ls_tipoPersona
																								    .equals(EstadoCommon.J)
																							)
																								ls_nombrePersona = lp_persona
																										.getRazonSocial();
																							else
																							{
																								String ls_primerNombre;
																								String ls_segundoNombre;
																								String ls_primerApellido;
																								String ls_segundoApellido;

																								ls_primerNombre        = lp_persona
																										.getPrimerNombre();
																								ls_segundoNombre       = lp_persona
																										.getSegundoNombre();
																								ls_primerApellido      = lp_persona
																										.getPrimerApellido();
																								ls_segundoApellido     = lp_persona
																										.getSegundoApellido();

																								ls_primerNombre     = StringUtils
																										.isValidString(
																										    ls_primerNombre
																										)
																									? ls_primerNombre : "";

																								ls_segundoNombre     = StringUtils
																										.isValidString(
																										    ls_segundoNombre
																										)
																									? (" "
																									+ ls_segundoNombre)
																									: "";

																								ls_primerApellido     = StringUtils
																										.isValidString(
																										    ls_primerApellido
																										)
																									? (" "
																									+ ls_primerApellido)
																									: "";

																								ls_segundoApellido     = StringUtils
																										.isValidString(
																										    ls_segundoApellido
																										)
																									? (" "
																									+ ls_segundoApellido)
																									: "";

																								ls_nombrePersona = ls_primerNombre
																									+ " "
																									+ ls_segundoNombre
																									+ " "
																									+ ls_primerApellido
																									+ " "
																									+ ls_segundoApellido;

																								if(
																								    ls_tipoDoc
																									    .equalsIgnoreCase("SE")
																									    && ls_tipoPersona
																									    .equals("I")
																								)
																									ls_nombrePersona += StringUtils
																										.getStringNotNull(
																										    lp_persona
																										    .getRazonSocial()
																										);
																							}

																							ls_numeroDocumento = lp_persona
																									.getNumeroDocumento();
																						}

																						String ls_rolPersona;
																						ls_rolPersona = StringUtils
																								.getStringNotNull(
																								    lcapc_interv
																								    .getRolPersona()
																								);

																						if(
																						    ls_rolPersona.equals("De")
																							    && ls_plantilla.contains(
																							        ls_tagDe
																							    )
																							    && !lmss_personasDe
																							    .containsKey(ls_numeroDocumento)
																						)
																							lmss_personasDe.put(
																							    ls_numeroDocumento,
																							    ls_nombrePersona + " "
																							    + ls_tipoDoc + " "
																							    + ls_numeroDocumento
																							);

																						else if(
																						    ls_rolPersona.equals(
																							        EstadoCommon.A
																							    )
																							    && ls_plantilla.contains(
																							        ls_tagA
																							    )
																							    && !lmss_personasA
																							    .containsKey(ls_numeroDocumento)
																						)
																							lmss_personasA.put(
																							    ls_numeroDocumento,
																							    ls_nombrePersona + " "
																							    + ls_tipoDoc + " "
																							    + ls_numeroDocumento
																							);
																					}
																				}
																			}
																		}
																	}
																}

																if(CollectionUtils.isValidCollection(lmss_personasDe))
																{
																	for(Map.Entry<String, String> ls_entry : lmss_personasDe
																		    .entrySet())
																	{
																		if(ls_entry != null)
																		{
																			String ls_valor;

																			ls_valor = ls_entry.getValue();

																			if(StringUtils.isValidString(ls_valor))
																				lcs_de.add(ls_valor);
																		}
																	}
																}

																if(CollectionUtils.isValidCollection(lmss_personasA))
																{
																	for(Map.Entry<String, String> ls_entry : lmss_personasA
																		    .entrySet())
																	{
																		if(ls_entry != null)
																		{
																			String ls_valor;

																			ls_valor = ls_entry.getValue();

																			if(StringUtils.isValidString(ls_valor))
																				lcs_a.add(ls_valor);
																		}
																	}
																}
															}
															else
															{
																SolicitudIntervinienteDAO lsi_DAO;
																lsi_DAO = DaoCreator.getSolicitudIntervinienteDAO(
																	    adm_manager
																	);

																SolicitudInterviniente lsi_sol;
																lsi_sol = new SolicitudInterviniente();

																lsi_sol.setIdSolicitud(ls_idSolicitud);

																Collection<SolicitudInterviniente> lcsi_intervinientes;
																lcsi_intervinientes = lsi_DAO.findByIdSolicitud(
																	    lsi_sol
																	);

																if(
																    CollectionUtils.isValidCollection(
																	        lcsi_intervinientes
																	    )
																)
																{
																	for(SolicitudInterviniente lsi_interv : lcsi_intervinientes)
																	{
																		if(lsi_interv != null)
																		{
																			Persona lp_persona;
																			lp_persona = new Persona();

																			lp_persona.setIdPersona(
																			    lsi_interv.getIdPersona()
																			);

																			lp_persona = lp_DAO.findById(lp_persona);

																			if(lp_persona != null)
																			{
																				String ls_nombrePersona;
																				String ls_numeroDocumento;
																				String ls_tipoDoc;

																				ls_nombrePersona       = "";
																				ls_numeroDocumento     = "";
																				ls_tipoDoc             = "";

																				ls_tipoDoc = lp_persona
																						.getIdDocumentoTipo();

																				String ls_tipoPersona;
																				ls_tipoPersona = StringUtils
																						.getStringNotNull(
																						    lp_persona.getIdTipoPersona()
																						);

																				if(
																				    StringUtils.isValidString(
																					        ls_tipoDoc
																					    )
																				)
																				{
																					if(
																					    ls_tipoDoc.equalsIgnoreCase(
																						        IdentificadoresCommon.NIT
																						    )
																						    && ls_tipoPersona.equals(
																						        EstadoCommon.J
																						    )
																					)
																						ls_nombrePersona = lp_persona
																								.getRazonSocial();
																					else
																					{
																						String ls_primerNombre;
																						String ls_segundoNombre;
																						String ls_primerApellido;
																						String ls_segundoApellido;

																						ls_primerNombre        = lp_persona
																								.getPrimerNombre();
																						ls_segundoNombre       = lp_persona
																								.getSegundoNombre();
																						ls_primerApellido      = lp_persona
																								.getPrimerApellido();
																						ls_segundoApellido     = lp_persona
																								.getSegundoApellido();

																						ls_primerNombre     = StringUtils
																								.isValidString(
																								    ls_primerNombre
																								) ? ls_primerNombre : "";

																						ls_segundoNombre     = StringUtils
																								.isValidString(
																								    ls_segundoNombre
																								)
																							? (" " + ls_segundoNombre)
																							: "";

																						ls_primerApellido     = StringUtils
																								.isValidString(
																								    ls_primerApellido
																								)
																							? (" " + ls_primerApellido)
																							: "";

																						ls_segundoApellido     = StringUtils
																								.isValidString(
																								    ls_segundoApellido
																								)
																							? (" " + ls_segundoApellido)
																							: "";

																						ls_nombrePersona = ls_primerNombre
																							+ " " + ls_segundoNombre
																							+ " " + ls_primerApellido
																							+ " " + ls_segundoApellido;

																						if(
																						    ls_tipoDoc.equalsIgnoreCase(
																							        "SE"
																							    )
																							    && ls_tipoPersona.equals(
																							        "I"
																							    )
																						)
																							StringUtils.getStringNotNull(
																							    lp_persona
																								    .getRazonSocial()
																							);
																					}

																					ls_numeroDocumento = lp_persona
																							.getNumeroDocumento();
																				}

																				String ls_rolPersona;
																				ls_rolPersona = StringUtils
																						.getStringNotNull(
																						    lsi_interv.getRolPersona()
																						);

																				if(
																				    ls_rolPersona.equals("De")
																					    && ls_plantilla.contains(
																					        ls_tagDe
																					    )
																				)
																					lcs_de.add(
																					    ls_nombrePersona + " "
																					    + ls_tipoDoc + " "
																					    + ls_numeroDocumento
																					);
																				else if(
																				    ls_rolPersona.equals(
																					        EstadoCommon.A
																					    )
																					    && ls_plantilla.contains(
																					        ls_tagA
																					    )
																				)
																					lcs_a.add(
																					    ls_nombrePersona + " "
																					    + ls_tipoDoc + " "
																					    + ls_numeroDocumento
																					);
																			}
																		}
																	}
																}
															}
														}

														ls_tag = "<TAG_ANOTACION_CIUDADANO_ROL_DE>";

														if(ls_plantilla.contains(ls_tag))
														{
															if(CollectionUtils.isValidCollection(lcs_de))
															{
																StringBuilder lsb_temp;
																lsb_temp = new StringBuilder();

																for(String ls_de : lcs_de)
																{
																	if(StringUtils.isValidString(ls_de))
																	{
																		lsb_temp.append("{\\pard ");
																		lsb_temp.append(ls_de);
																		lsb_temp.append("\\par}");
																	}
																}

																ls_plantilla = ls_plantilla.replace(
																	    ls_tag, lsb_temp.toString()
																	);
															}
														}

														ls_tag = "<TAG_ANOTACION_CIUDADANO_ROL_A>";

														if(ls_plantilla.contains(ls_tag))
														{
															if(CollectionUtils.isValidCollection(lcs_a))
															{
																StringBuilder lsb_temp;
																lsb_temp = new StringBuilder();

																for(String ls_a : lcs_a)
																{
																	if(StringUtils.isValidString(ls_a))
																	{
																		lsb_temp.append("{\\pard ");
																		lsb_temp.append(ls_a);
																		lsb_temp.append("\\par}");
																	}
																}

																ls_plantilla = ls_plantilla.replace(
																	    ls_tag, lsb_temp.toString()
																	);
															}
														}

														ls_tag = "<TAG_MATRICULAS>";

														if(ls_plantilla.contains(ls_tag))
														{
															if(CollectionUtils.isValidCollection(lcs_matriculas))
															{
																StringBuilder lsb_temp;
																long          li_tamanoMatricula;
																long          li_posicion;
																lsb_temp               = new StringBuilder();
																li_tamanoMatricula     = 0;
																li_posicion            = 1;
																li_tamanoMatricula     = lcs_matriculas.size();

																for(String ls_mat : lcs_matriculas)
																{
																	if(StringUtils.isValidString(ls_mat))
																	{
																		lsb_temp.append(ls_mat);

																		if(li_posicion < li_tamanoMatricula)
																			lsb_temp.append(", ");

																		li_posicion++;
																	}
																}

																ls_plantilla = ls_plantilla.replace(
																	    ls_tag, lsb_temp.toString()
																	);
															}
															else
															{
																Turno lt_turno;
																lt_turno = new Turno();

																lt_turno.setIdTurno(ls_idTurno);

																lt_turno = DaoCreator.getTurnoDAO(adm_manager)
																		                 .findById(lt_turno);

																if(lt_turno != null)
																{
																	SolicitudMatriculaActo             lsma_solMatActo;
																	Collection<SolicitudMatriculaActo> lcsma_consulta;

																	lsma_solMatActo     = new SolicitudMatriculaActo();
																	lcs_matriculas      = new LinkedList<String>();

																	lsma_solMatActo.setIdSolicitud(ls_idSolicitud);
																	lsma_solMatActo.setIdCirculo(
																	    lt_turno.getIdCirculo()
																	);

																	lcsma_consulta = DaoCreator.getSolicitudMatriculaActoDAO(
																		    adm_manager
																		).findByIdSolicitudCirculo(
																		    lsma_solMatActo, true
																		);

																	if(
																	    CollectionUtils.isValidCollection(
																		        lcsma_consulta
																		    )
																	)
																	{
																		StringBuilder lsb_temp;
																		lsb_temp = new StringBuilder();

																		for(SolicitudMatriculaActo ls_mat : lcsma_consulta)
																		{
																			if(ls_mat != null)
																			{
																				lsb_temp.append(
																				    ls_mat.getIdCirculo() + "-"
																				    + ls_mat.getIdMatricula()
																				);
																				lsb_temp.append(", ");
																			}
																		}

																		ls_plantilla = ls_plantilla.replace(
																			    ls_tag, lsb_temp.toString()
																			);
																	}
																}
															}
														}
													}

													{
														Departamento ld_departamento;
														Municipio    lm_municipio;
														ld_departamento     = new Departamento();
														lm_municipio        = new Municipio();

														ld_departamento.setIdPais(ls_pais);
														ld_departamento.setIdDepartamento(ls_departamento);
														ld_departamento = lodd_DAO.findById(ld_departamento);

														lm_municipio.setIdPais(ls_pais);
														lm_municipio.setIdDepartamento(ls_departamento);
														lm_municipio.setIdMunicipio(ls_municipio);

														lm_municipio     = lomd_DAO.findById(lm_municipio);

														ls_tag = "<TAG_DEP_MUN_OFI_ORIGEN>";

														if(ls_plantilla.contains(ls_tag))
														{
															if((ld_departamento != null) && (lm_municipio != null))
															{
																String ls_nombreDep;
																ld_departamento.setNombreDepartamento(
																    ld_departamento.getNombre()
																);
																ls_nombreDep            = ld_departamento
																		.getNombreDepartamento();
																ls_munOficinaOrigen     = lm_municipio.getNombre();

																if(
																    StringUtils.isValidString(ls_nombreDep)
																	    && StringUtils.isValidString(
																	        ls_munOficinaOrigen
																	    )
																)
																	ls_plantilla = escribirDepartamentoMunicipioInteresadoUnTag(
																		    ls_plantilla, ls_munOficinaOrigen, ls_tag,
																		    ls_nombreDep
																		);
															}
														}

														ls_tag = "<TAG_DEP_OFI_ORIGEN>";

														if(ls_plantilla.contains(ls_tag) && (ld_departamento != null))
														{
															String ls_nombreDep;
															ls_nombreDep = ld_departamento.getNombre();

															if(StringUtils.isValidString(ls_nombreDep))
																if(
																    ls_plantilla.contains(ls_tag)
																	    && (ld_departamento != null)
																)
																{
																	lsb_sb.append(", ");
																	lsb_sb.append(ls_nombreDep);
																	ls_plantilla = ls_plantilla.replace(
																		    ls_tag, ls_nombreDep
																		);

																	if(StringUtils.isValidString(ls_nombreDep))
																	{
																		lsb_sb.append(", ");
																		lsb_sb.append(ls_nombreDep);
																		ls_plantilla = ls_plantilla.replace(
																			    ls_tag, ls_nombreDep
																			);
																	}
																}
														}

														ls_tag = "<TAG_MUN_OFI_ORIGEN>";

														if(ls_plantilla.contains(ls_tag))
														{
															Turno lt_turno;
															lt_turno = new Turno();

															lt_turno.setIdTurno(ls_idTurno);

															lt_turno = DaoCreator.getTurnoDAO(adm_manager)
																	                 .findById(lt_turno);

															if(lt_turno != null)
															{
																String ls_idCirculo;
																ls_idCirculo = lt_turno.getIdCirculo();

																if(StringUtils.isValidString(ls_idCirculo))
																{
																	lm_municipio     = new Municipio();

																	lm_municipio = DaoCreator.getMunicipioDAO(
																		    adm_manager
																		).findByIdCirculo(ls_idCirculo);

																	if(lm_municipio != null)
																	{
																		String ls_munOficinaOrigen2;
																		ls_munOficinaOrigen2 = lm_municipio.getNombre();

																		if(
																		    ls_plantilla.contains(ls_tag)
																			    && StringUtils.isValidString(
																			        ls_munOficinaOrigen2
																			    )
																		)
																			ls_plantilla = ls_plantilla.replace(
																				    ls_tag, ls_munOficinaOrigen2
																				);
																	}
																}
															}
														}
													}

													ls_tag = "<TAG_FECHA_TURNO>";

													if(ls_plantilla.contains(ls_tag))
													{
														Turno ls_turnoTemp;
														ls_turnoTemp = new Turno();

														ls_turnoTemp.setIdTurno(ls_idTurno);

														ls_turnoTemp = DaoCreator.getTurnoDAO(adm_manager)
																                     .findById(ls_turnoTemp);

														if(ls_turnoTemp != null)
														{
															String ls_fechaTurno;
															ls_fechaTurno = DateUtils.convertirLetrasLarga(
																    ls_turnoTemp.getFechaCreacion()
																);

															if(StringUtils.isValidString(ls_fechaTurno))
																ls_plantilla = ls_plantilla.replace(
																	    ls_tag, ls_fechaTurno
																	);
														}
													}

													ls_tag = "<TURNO_DERIVADO>";

													if(ls_plantilla.contains(ls_tag))
													{
														ls_turnoDerivado = lothd_thd.findTurnoDerivado(ls_idTurno);

														if(StringUtils.isValidString(ls_turnoDerivado))
															ls_plantilla = ls_plantilla.replace(
																    "<TURNO_DERIVADO>", ls_turnoDerivado
																);
														else
															ls_plantilla = ls_plantilla.replace(
																    "<TURNO_DERIVADO>", " "
																);
													}

													{
														ls_tag = "<TAG_FECHA_LARGA>";

														if(ls_plantilla.contains(ls_tag))
														{
															Date   ld_fecha;
															String ls_fechaActual;

															ld_fecha           = new Date();
															ls_fechaActual     = DateUtils.convertirLetrasLarga(
																    ld_fecha
																);

															if(StringUtils.isValidString(ls_fechaActual))
																ls_plantilla = ls_plantilla.replace(
																	    ls_tag, ls_fechaActual.toUpperCase()
																	);
														}
													}

													{
														ls_tag = "<TAG_ID_OFI_ORIGEN>";

														if(ls_plantilla.contains(ls_tag))
														{
															if(
															    ls_plantilla.contains(ls_tag)
																    && StringUtils.isValidString(
																        ls_nombreOficinaOrigen
																    )
															)
																ls_plantilla = ls_plantilla.replace(
																	    ls_tag,
																	    ls_nombreOficinaOrigen + " de "
																	    + ls_munOficinaOrigen
																	);
														}
													}

													ls_tag = "<TAG_DIR_OFI_ORIGEN>";

													if(
													    ls_plantilla.contains(ls_tag) && !lb_medidaCautelar
														    && !lb_notaDevMedidaCautelar
													)
														ls_plantilla = ls_plantilla.replace(ls_tag, lsb_sb.toString());

													ls_tag = "<TAG_ACC_TUR_ID_TURNO>";

													if(ls_plantilla.contains(ls_tag))
														ls_plantilla = ls_plantilla.replace(ls_tag, ls_idTurno);

													ls_tag = "<TAG_FECHA_RESOL>";

													if(ls_plantilla.contains(ls_tag))
													{
													}

													ls_tag = "<TAG_NOMBRE_ORIP>";

													if(ls_plantilla.contains(ls_tag))
													{
														Turno  lt_datosTurno;
														String ls_orip;
														lt_datosTurno = new Turno();
														lt_datosTurno.setIdTurno(ls_idTurno);

														lt_datosTurno = ltd_DAO.findById(lt_datosTurno);

														if(lt_datosTurno != null)
														{
															ls_orip = lt_datosTurno.getIdCirculo();

															if(StringUtils.isValidString(ls_orip))
															{
																CirculoRegistral lcr_circuloRegistral;

																lcr_circuloRegistral = new CirculoRegistral();
																lcr_circuloRegistral.setIdCirculo(ls_orip);

																lcr_circuloRegistral = lcrd_DAO.findById(
																	    lcr_circuloRegistral
																	);

																if(lcr_circuloRegistral != null)
																{
																	String ls_nombreOrip;
																	ls_nombreOrip = lcr_circuloRegistral.getNombre();

																	if(StringUtils.isValidString(ls_nombreOrip))
																		ls_plantilla = ls_plantilla.replace(
																			    ls_tag, ls_nombreOrip
																			);
																}
															}
														}
													}

													ls_tag = "<TAG_ANOTACION_CANCELADAS>";

													if(ls_plantilla.contains(ls_tag))
													{
														AnotacionCancelacion             loac_tmp;
														StringBuilder                    lsb_tmp;
														Collection<AnotacionCancelacion> lcac_tmp;
														loac_tmp     = new AnotacionCancelacion();
														lsb_tmp      = new StringBuilder();

														/* PENDIENTE */
														loac_tmp.setIdTurno(ls_idTurno);
														loac_tmp.setIdSolicitud(ls_idSolicitud);
														loac_tmp.setSolicitud(true);

														lcac_tmp = loacd_dao.findByCirculoMatricula(loac_tmp, false);

														if(CollectionUtils.isValidCollection(lcac_tmp))
														{
															if(CollectionUtils.isValidCollection(lcac_tmp))
															{
																for(AnotacionCancelacion loac_ac : lcac_tmp)
																	lsb_tmp.append(loac_ac.getIdAnotacion1() + ",");
															}

															ls_plantilla = ls_plantilla.replace(
																    ls_tag, lsb_tmp.toString()
																);
														}
													}

													if(StringUtils.isValidString(ls_tipoDocumento))
													{
														TipoDocumentoPublico ltdp_tipoDocPublico;
														ltdp_tipoDocPublico = new TipoDocumentoPublico();

														ltdp_tipoDocPublico.setIdTipoDocumento(ls_tipoDocumento);

														ltdp_tipoDocPublico = ltdpd_DAO.findById(ltdp_tipoDocPublico);

														if(ltdp_tipoDocPublico != null)
														{
															String ls_nombreTipoDoc;
															ls_nombreTipoDoc     = ltdp_tipoDocPublico.getNombre();

															ls_tag = "<TAG_BQN_DOC_ID_TIPO_DOC>";

															if(StringUtils.isValidString(ls_nombreTipoDoc))
																ls_plantilla = ls_plantilla.replace(
																	    ls_tag, ls_nombreTipoDoc
																	);
														}
													}

													ls_tag = "<TAG_BQN_DOC_NUMERO>";

													if(ls_plantilla.contains(ls_tag))
													{
														if(StringUtils.isValidString(ls_numero))
															ls_plantilla = ls_plantilla.replace(ls_tag, ls_numero);
													}

													ls_tag = "<TAG_BQN_DOC_FECHA>";

													if(ls_plantilla.contains(ls_tag))
													{
														String ls_fecha;

														if(aotc_tc.isMedidaCautelar() || lb_notaDevMedidaCautelar)
															ls_fecha = DateUtils.convertirLetrasLarga(ld_fechaDoc);
														else
															ls_fecha = StringUtils.getString(
																    ld_fechaDoc, FormatoFechaCommon.DIA_MES_ANIO
																);

														if(StringUtils.isValidString(ls_fecha))
															ls_plantilla = ls_plantilla.replace(ls_tag, ls_fecha);
													}

													ls_tag = "<TAG_USUARIO>";

													if(ls_plantilla.contains(ls_tag))
														ls_plantilla = ls_plantilla.replace(ls_tag, ls_usuario);
												}
											}
										}
									}
								}

								{
									Constantes lc_usuarioFirma;
									String     ls_tagUsuarioFirma;
									int        li_incrX = 0;
									int        li_incrY = 0;

									lc_usuarioFirma     = new Constantes();
									ls_tagUsuarioFirma  = "<TAG_USUARIO_FIRMA_SUSPENSION>";

									lc_usuarioFirma.setIdConstante(ConstanteCommon.USUARIO_FIRMA_SUSPENSION);

									lc_usuarioFirma             = DaoCreator.getConstantesDAO(adm_manager)
											                                    .findByIdWithImage(lc_usuarioFirma);
									ls_plantilla                = getFirmaMasivaBusiness()
											                              .reemplazarUsuarioFirmaCargo(
											    ls_plantilla, lc_usuarioFirma, ls_tagUsuarioFirma,
											    "<TAG_CARGO_FIRMA_SUSPENSION>"
											);
									lmss_datos                  = finalizarPlantilla(
										    ls_plantilla, loth_detalle.getIdTurnoHistoria(), adm_manager
										);
									ls_plantilla                = lmss_datos.get(IdentificadoresCommon.PLANTILLA);
									lba_registroInscripcion     = new PDFGenerator().convertirRTFToPDF(
										    ls_plantilla.getBytes(), adm_manager
										);

									if(lba_registroInscripcion == null)
										throw new B2BException(ErrorKeys.NO_SE_GENERO_ARCHIVO);

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

										lba_registroInscripcion = getFirmaMasivaBusiness()
												                          .reemplazarBookmarsFirma(
												    lba_registroInscripcion, lba_grafo, li_incrX, li_incrY
												);
									}
								}

								if(aotc_tc.isSalvar())
								{
									int              li_tamanoArchivo;
									DocumentosSalida lds_documento;
									Imagenes         li_imagen;
									Double           ld_kbs;
									long             ll_secuenciaImagen;

									lds_documento          = new DocumentosSalida();
									li_imagen              = new Imagenes();
									li_tamanoArchivo       = lba_registroInscripcion.length;
									ld_kbs                 = NumericUtils.getDoubleWrapper(li_tamanoArchivo * 0.001);
									ll_secuenciaImagen     = 0L;

									li_imagen.setImagenBlob(lba_registroInscripcion);
									li_imagen.setTipoArchivo(ExtensionCommon.PDF_MAYUS);
									li_imagen.setTamano(NumericUtils.getLongWrapper(ld_kbs.doubleValue()));
									li_imagen.setIdUsuarioCreacion(as_userId);
									li_imagen.setIpCreacion(as_remoteIp);
									li_imagen.setCodigoVerificacion(
									    lmss_datos.get(IdentificadoresCommon.CODIGO_VERIFICACION)
									);

									ll_secuenciaImagen = DaoCreator.getImagenesDAO(adm_manager)
											                           .insertOrUpdate(li_imagen, true);

									if(ll_secuenciaImagen <= 0L)
										throw new B2BException(ErrorKeys.SIN_SECUENCIA_IMAGENES);

									lds_documento.setIdTurnoHistoria(NumericUtils.getInteger(ll_idTurnoHistoria));
									lds_documento.setIdSolicitud(ls_idSolicitud);
									lds_documento.setIdTurno(ls_idTurno);
									lds_documento.setIdImagen(NumericUtils.getLongWrapper(ll_secuenciaImagen));

									if(lb_validMedidaCautelar)
									{
										OficinaOrigen loo_oficina;

										loo_oficina = aotc_tc.getOficinaOrigenMedidaCautelar();

										if(loo_oficina != null)
										{
											lds_documento.setDestinatario(loo_oficina.getNombre());
											lds_documento.setDireccion(loo_oficina.getDireccion());
											lds_documento.setIdPais(loo_oficina.getIdPais());
											lds_documento.setIdDepartamento(loo_oficina.getIdDepartamento());
											lds_documento.setIdMunicipio(loo_oficina.getIdMunicipio());
											lds_documento.setCorreoElectronico(loo_oficina.getCorreoElectronico());
											lds_documento.setTelefono(loo_oficina.getTelefono());
										}
									}

									lds_documento.setTelefono(ls_telefono);
									lds_documento.setCorreoElectronico(ls_correoElectronico);
									lds_documento.setTipoArchivo(ExtensionCommon.PDF_MAYUS);
									lds_documento.setTipo(
									    lb_validMedidaCautelar ? TipoArchivoCommon.COMUNICADO_DEMANDA
									                           : TipoArchivoCommon.COMUNICADO_CANCELACION
									);
									lds_documento.setIdTipoDocumental(TipoDocumentalCommon.OFICIO);
									lds_documento.setConsecutivoOficio(ls_consecutivoOficio);
									lds_documento.setReferenciaSalida(ls_referenciaSalida);
									lds_documento.setFechaOficio(ab_firmaMasiva ? new Date() : null);
									lds_documento.setRepositorio(
									    ab_firmaMasiva ? RepositorioCommon.FINAL : RepositorioCommon.TEMPORAL
									);
									lds_documento.setEstado(EstadoCommon.ACTIVO);
									lds_documento.setIdUsuarioCreacion(as_userId);
									lds_documento.setIpCreacion(as_remoteIp);

									ldsdao_DAO.insertOrUpdate(lds_documento, true);
								}
							}
						}
					}
				}
			}
		}
		catch(Exception lb2be_e)
		{
			clh_LOGGER.error("genereteFileCancelacion", lb2be_e);

			throw new B2BException(lb2be_e.getLocalizedMessage());
		}

		return lba_registroInscripcion;
	}

	/**
	 * Método generar pdf de correspondencia registro
	 *
	 * @param aotc_tc Objeto para extraer información necesaria para el pdf
	 * @param ab_firmaMasiva booleano para saber si es de firma masiva
	 * @param as_userId String de usuario en sesión
	 * @param as_remoteIp Ip de usuario en sesión
	 * @return
	 * @throws B2BException
	 */
	public synchronized byte[] genereteFileComunicadoDireccion(
	    RegistroCalificacion aotc_tc, String as_userId, String as_remoteIp, boolean ab_firmaMasiva
	)
	    throws B2BException
	{
		if(aotc_tc == null)
			throw new B2BException(ErrorKeys.ERROR_INTERNO_SISTEMA);

		DAOManager ldm_manager;
		byte[]     lba_registroInscripcion;

		lba_registroInscripcion     = null;
		ldm_manager                 = DaoManagerFactory.getDAOManager();

		try
		{
			lba_registroInscripcion = genereteFileComunicadoDireccion(
				    ldm_manager, aotc_tc, as_userId, as_remoteIp, ab_firmaMasiva
				);
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("genereteFileComunicadoDireccion", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lba_registroInscripcion;
	}

	/**
	 * Método generar pdf de correspondencia registro
	 *
	 * @param aotc_tc Objeto para extraer información necesaria para el pdf
	 * @param ab_firmaMasiva booleano para saber si es de firma masiva
	 * @param as_userId String de usuario en sesión
	 * @param as_remoteIp Ip de usuario en sesión
	 * @return
	 * @throws B2BException
	 */
	public synchronized byte[] genereteFileComunicadoDireccion(
	    DAOManager adm_manager, RegistroCalificacion aotc_tc, String as_userId, String as_remoteIp,
	    boolean ab_firmaMasiva
	)
	    throws B2BException
	{
		byte[] lb_archivo;

		lb_archivo = null;

		try
		{
			ConstantesDAO lcd_DAO;
			lcd_DAO = DaoCreator.getConstantesDAO(adm_manager);

			if(aotc_tc != null)
			{
				TurnoHistoria lth_turnoHistoria;
				lth_turnoHistoria = new TurnoHistoria();
				lth_turnoHistoria.setIdTurnoHistoria(aotc_tc.getIdTurnoHistoria());

				if(lth_turnoHistoria != null)
				{
					lth_turnoHistoria = DaoCreator.getTurnoHistoriaDAO(adm_manager).findById(lth_turnoHistoria);

					if(lth_turnoHistoria != null)
					{
						Turno lt_turno;
						lt_turno = new Turno();

						lt_turno.setIdTurno(lth_turnoHistoria.getIdTurno());

						lt_turno = DaoCreator.getTurnoDAO(adm_manager).findById(lt_turno);

						if(lt_turno != null)
						{
							String     ls_constante;
							Constantes lc_constante;

							byte[] lba_plantilla;

							StringBuilder lsb_nombre;

							ls_constante     = null;

							lsb_nombre     = new StringBuilder();

							ls_constante     = ConstanteCommon.PLANTILLA_CORRESPONDENCIA_REGISTRO;

							lc_constante = new Constantes();
							lc_constante.setIdConstante(ls_constante);

							lc_constante = lcd_DAO.findImagen(lc_constante);

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
								String                  ls_plantilla;
								TurnoDAO                ltd_DAO;
								DocumentoDAO            ldd_DAO;
								TipoDocumentoPublicoDAO ltdpd_DAO;
								SolicitudDAO            lsd_DAO;

								ls_plantilla     = new String(lba_plantilla);
								ltd_DAO          = DaoCreator.getTurnoDAO(adm_manager);
								ldd_DAO          = DaoCreator.getDocumentoDAO(adm_manager);
								ltdpd_DAO        = DaoCreator.getTipoDocumentoPublicoDAO(adm_manager);
								lsd_DAO          = DaoCreator.getSolicitudDAO(adm_manager);

								if(StringUtils.isValidString(ls_plantilla))
								{
									String ls_tag;
									Turno  lt_datosTurno;

									ls_tag            = null;
									lt_datosTurno     = ltd_DAO.findById(lt_turno);

									if(lt_datosTurno != null)
									{
										Map<String, String> lmss_datos;
										String              ls_direccioncompleta;
										String              ls_idPais;
										String              ls_idDepartamento;
										String              ls_idMunicipio;
										Solicitud           lso_solicitud1;
										OficinaOrigen       loo_oficinaOrigen;
										String              ls_notificaCorrespondenciaOficinaOrigen;

										lmss_datos                                  = null;
										lso_solicitud1                              = null;
										ls_direccioncompleta                        = null;
										ls_idPais                                   = null;
										ls_idDepartamento                           = null;
										ls_idMunicipio                              = null;
										lso_solicitud1                              = new Solicitud();
										loo_oficinaOrigen                           = new OficinaOrigen();
										ls_notificaCorrespondenciaOficinaOrigen     = null;

										lso_solicitud1.setIdSolicitud(lt_datosTurno.getIdSolicitud());

										lso_solicitud1 = lsd_DAO.findById(lso_solicitud1);

										{
											String ls_turno;
											ls_turno = lt_turno.getIdTurno();

											if(StringUtils.isValidString(ls_turno))
											{
												ls_tag = "<TAG_ACC_TUR_ID_TURNO>";

												if(ls_plantilla.contains(ls_tag))
													ls_plantilla = ls_plantilla.replace(ls_tag, ls_turno);
											}
										}

										if(lso_solicitud1 != null)
										{
											String ls_idDocumento;

											ls_idDocumento = lso_solicitud1.getIdDocumento();

											if(StringUtils.isValidString(ls_idDocumento))
											{
												Documento ld_documento;
												ld_documento = new Documento();
												ld_documento.setIdDocumento(ls_idDocumento);

												ld_documento = ldd_DAO.findById(ld_documento);

												if(ld_documento != null)
												{
													String     ls_tipoDocumento;
													String     ls_numero;
													String     ls_oficinaOrigen;
													BigDecimal lbd_version;
													Date       ld_fecha;

													ls_tipoDocumento     = ld_documento.getIdTipoDocumento();
													ls_numero            = ld_documento.getNumero();
													ls_oficinaOrigen     = ld_documento.getIdOficinaOrigen();
													lbd_version          = ld_documento.getVersion();
													ld_fecha             = ld_documento.getFechaDocumento();

													ls_tag = "<TAG_BQN_DOC_NUMERO>";

													if(ls_plantilla.contains(ls_tag))
													{
														if(StringUtils.isValidString(ls_numero))
															ls_plantilla = ls_plantilla.replace(ls_tag, ls_numero);
													}

													ls_tag = "<TAG_BQN_DOC_FECHA>";

													if(ls_plantilla.contains(ls_tag))
													{
														String ls_fecha;
														ls_fecha = StringUtils.getString(
															    ld_fecha, FormatoFechaCommon.DIA_MES_ANIO
															);

														if(StringUtils.isValidString(ls_fecha))
															ls_plantilla = ls_plantilla.replace(ls_tag, ls_fecha);
													}

													{
														loo_oficinaOrigen.setIdOficinaOrigen(ls_oficinaOrigen);
														loo_oficinaOrigen.setVersion(lbd_version);

														loo_oficinaOrigen = DaoCreator.getOficinaOrigenDAO(adm_manager)
																                          .findById(loo_oficinaOrigen);

														if(loo_oficinaOrigen != null)
														{
															ls_idPais                                   = loo_oficinaOrigen
																	.getIdPais();
															ls_idDepartamento                           = loo_oficinaOrigen
																	.getIdDepartamento();
															ls_idMunicipio                              = loo_oficinaOrigen
																	.getIdMunicipio();
															ls_notificaCorrespondenciaOficinaOrigen     = loo_oficinaOrigen
																	.getNotificarCorrespondencia();

															ls_tag = "<TAG_ID_OFI_ORIGEN>";

															if(ls_plantilla.contains(ls_tag))
															{
																String ls_nombre;
																ls_nombre = loo_oficinaOrigen.getNombre();

																if(StringUtils.isValidString(ls_nombre))
																	ls_plantilla = ls_plantilla.replace(
																		    ls_tag, ls_nombre
																		);
															}

															ls_tag = "<TAG_DIR_OFI_ORIGEN>";

															if(ls_plantilla.contains(ls_tag))
															{
																String ls_dir;
																ls_dir = loo_oficinaOrigen.getDireccion();

																if(StringUtils.isValidString(ls_dir))
																	ls_plantilla = saltoDeCarroDespues(
																		    ls_plantilla, ls_tag, ls_dir
																		);
															}
														}
													}

													if(StringUtils.isValidString(ls_tipoDocumento))
													{
														TipoDocumentoPublico ltdp_tipoDocPublico;
														ltdp_tipoDocPublico = new TipoDocumentoPublico();

														ltdp_tipoDocPublico.setIdTipoDocumento(ls_tipoDocumento);

														ltdp_tipoDocPublico = ltdpd_DAO.findById(ltdp_tipoDocPublico);

														if(ltdp_tipoDocPublico != null)
														{
															String ls_nombreTipoDoc;
															ls_nombreTipoDoc     = ltdp_tipoDocPublico.getNombre();

															ls_tag = "<TAG_BQN_DOC_ID_TIPO_DOC>";

															if(ls_plantilla.contains(ls_tag))
															{
																if(StringUtils.isValidString(ls_nombreTipoDoc))
																	ls_plantilla = ls_plantilla.replace(
																		    ls_tag, ls_nombreTipoDoc
																		);
															}
														}
													}
												}
											}

											String ls_medioNotificar;

											ls_medioNotificar     = lso_solicitud1.getIdAutorizacionNotificacion();

											ls_tag = "<TAG_NOMBRE_INTERESADO>";

											if(ls_plantilla.contains(ls_tag))
											{
												if(
												    ls_medioNotificar.equalsIgnoreCase(
													        MedioNotificarCommon.DIRECCION_CORRESPONDENCIA
													    )
													    && StringUtils.isValidString(
													        ls_notificaCorrespondenciaOficinaOrigen
													    )
													    && ls_notificaCorrespondenciaOficinaOrigen.equalsIgnoreCase(
													        "S"
													    )
												)
													lsb_nombre.append(loo_oficinaOrigen.getNombre());
												else
												{
													String ls_idPersona;
													ls_idPersona = lso_solicitud1.getIdPersona();

													if(StringUtils.isValidString(ls_idPersona))
													{
														Persona lp_persona;
														lp_persona = new Persona();

														lp_persona.setIdPersona(ls_idPersona);

														lp_persona = DaoCreator.getPersonaDAO(adm_manager)
																                   .findById(lp_persona);

														if(lp_persona != null)
														{
															String ls_idTipoDocumento;

															ls_idTipoDocumento = lp_persona.getIdDocumentoTipo();

															if(
															    StringUtils.isValidString(ls_idTipoDocumento)
																    && !ls_idTipoDocumento.contentEquals(
																        IdentificadoresCommon.NIT
																    )
															)
															{
																String ls_primerNombre;
																String ls_segundoNombre;
																String ls_primeApellido;
																String ls_segundoApellido;

																ls_primerNombre        = lp_persona.getPrimerNombre();
																ls_segundoNombre       = lp_persona.getSegundoNombre();
																ls_primeApellido       = lp_persona.getPrimerApellido();
																ls_segundoApellido     = lp_persona.getSegundoApellido();

																lsb_nombre.append(
																    (StringUtils.isValidString(ls_primerNombre)
																	    && !ls_primerNombre.equalsIgnoreCase("null"))
																    ? ls_primerNombre : ""
																);
																lsb_nombre.append(
																    (StringUtils.isValidString(ls_segundoNombre)
																	    && !ls_segundoNombre.equalsIgnoreCase("null"))
																    ? (" " + ls_segundoNombre) : ""
																);
																lsb_nombre.append(
																    (StringUtils.isValidString(ls_primeApellido)
																	    && !ls_primeApellido.equalsIgnoreCase("null"))
																    ? (" " + ls_primeApellido) : ""
																);
																lsb_nombre.append(
																    (StringUtils.isValidString(ls_segundoApellido)
																	    && !ls_segundoApellido.equalsIgnoreCase("null"))
																    ? (" " + ls_segundoApellido) : ""
																);
															}
															else
																lsb_nombre.append(lp_persona.getRazonSocial());
														}
													}
												}

												ls_plantilla = ls_plantilla.replace(
													    ls_tag, (lsb_nombre.toString()).trim()
													);
											}

											String ls_idCirculo;

											ls_tag           = "<TAG_MUN_OFI_ORIGEN>";
											ls_idCirculo     = lt_datosTurno.getIdCirculo();

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
												if(StringUtils.isValidString(ls_medioNotificar))
												{
													String ls_idPersona;

													ls_idPersona = lso_solicitud1.getIdPersona();

													if(StringUtils.isValidString(ls_idPersona))
													{
														Persona lp_persona;
														lp_persona = new Persona();

														lp_persona.setIdPersona(ls_idPersona);

														lp_persona = DaoCreator.getPersonaDAO(adm_manager)
																                   .findById(lp_persona);

														if(lp_persona != null)
														{
															if(
															    ls_medioNotificar.equalsIgnoreCase(
																        MedioNotificarCommon.DIRECCION_RESIDENCIA
																    )
																    || ls_medioNotificar.equalsIgnoreCase(
																        MedioNotificarCommon.DIRECCION_CORRESPONDENCIA
																    )
															)
															{
																PersonaDireccion lpd_pd;

																lpd_pd = new PersonaDireccion();

																lpd_pd.setIdPersona(ls_idPersona);

																lpd_pd.setIdDireccion(lso_solicitud1.getIdDireccion());

																lpd_pd = DaoCreator.getPersonaDireccionDAO(adm_manager)
																		               .findById(lpd_pd);

																if(lpd_pd != null)
																{
																	ls_tag = "<TAG_DIR_INTERESADO>";

																	if(ls_plantilla.contains(ls_tag))
																	{
																		if(
																		    ls_medioNotificar.equalsIgnoreCase(
																			        MedioNotificarCommon.DIRECCION_CORRESPONDENCIA
																			    )
																			    && StringUtils.isValidString(
																			        ls_notificaCorrespondenciaOficinaOrigen
																			    )
																			    && ls_notificaCorrespondenciaOficinaOrigen
																			    .equalsIgnoreCase("S")
																		)
																			ls_direccioncompleta = loo_oficinaOrigen
																					.getDireccion();
																		else
																			ls_direccioncompleta = lpd_pd.getDireccion();

																		if(
																		    StringUtils.isValidString(
																			        ls_direccioncompleta
																			    )
																		)
																			ls_plantilla = saltoDeCarroDespues(
																				    ls_plantilla, ls_tag,
																				    ls_direccioncompleta
																				);
																	}

																	ls_tag = "<TAG_DEPAR_INTERESADO>";

																	if(
																	    ls_plantilla.contains(ls_tag)
																		    && ls_plantilla.contains(
																		        "TAG_MUNICIPIO_INTERESADO"
																		    )
																	)
																	{
																		Departamento ld_departamento;
																		Municipio    lm_municipio;

																		ld_departamento     = new Departamento();
																		lm_municipio        = new Municipio();

																		ld_departamento.setIdPais(lpd_pd.getIdPais());
																		ld_departamento.setIdDepartamento(
																		    lpd_pd.getIdDepartamento()
																		);

																		ld_departamento = DaoCreator.getDepartamentoDAO(
																			    adm_manager
																			).findById(ld_departamento);

																		lm_municipio.setIdPais(lpd_pd.getIdPais());
																		lm_municipio.setIdDepartamento(
																		    lpd_pd.getIdDepartamento()
																		);
																		lm_municipio.setIdMunicipio(
																		    lpd_pd.getIdMunicipio()
																		);

																		lm_municipio = DaoCreator.getMunicipioDAO(
																			    adm_manager
																			).findById(lm_municipio);

																		if(
																		    (ld_departamento != null)
																			    && (lm_municipio != null)
																		)
																		{
																			String ls_nombre;
																			ld_departamento.setNombreDepartamento(
																			    ld_departamento.getNombre()
																			);
																			ls_nombre = ld_departamento
																					.getNombreDepartamento();

																			if(
																			    StringUtils.isValidString(ls_nombre)
																				    && StringUtils.isValidString(
																				        lm_municipio.getNombre()
																				    )
																			)
																				ls_plantilla = escribirDepartamentoMunicipioInteresado(
																					    ls_plantilla, "<TAG_MUNICIPIO>",
																					    lm_municipio.getNombre(), ls_tag,
																					    ls_nombre
																					);
																		}
																	}
																	else if(ls_plantilla.contains(ls_tag))
																	{
																		Departamento ld_departamento;

																		ld_departamento = new Departamento();

																		ld_departamento.setIdPais(lpd_pd.getIdPais());
																		ld_departamento.setIdDepartamento(
																		    lpd_pd.getIdDepartamento()
																		);
																		ld_departamento = DaoCreator.getDepartamentoDAO(
																			    adm_manager
																			).findById(ld_departamento);

																		if(ld_departamento != null)
																		{
																			String ls_nombre;

																			ls_nombre = ld_departamento.getNombre();

																			if(StringUtils.isValidString(ls_nombre))
																				ls_plantilla = saltoDeCarroDespues(
																					    ls_plantilla, ls_tag, ls_nombre
																					);
																		}
																	}

																	else if(
																	    ls_plantilla.contains(
																		        "<TAG_MUNICIPIO_INTERESADO>"
																		    )
																	)
																	{
																		Municipio lm_municipio;

																		lm_municipio = new Municipio();

																		lm_municipio.setIdPais(lpd_pd.getIdPais());
																		lm_municipio.setIdDepartamento(
																		    lpd_pd.getIdDepartamento()
																		);
																		lm_municipio.setIdMunicipio(
																		    lpd_pd.getIdMunicipio()
																		);

																		lm_municipio = DaoCreator.getMunicipioDAO(
																			    adm_manager
																			).findById(lm_municipio);

																		if(lm_municipio != null)
																		{
																			String ls_nombre;

																			ls_nombre = lm_municipio.getNombre();

																			if(StringUtils.isValidString(ls_nombre))
																				ls_plantilla = saltoDeCarroDespues(
																					    ls_plantilla,
																					    "<TAG_MUNICIPIO_INTERESADO>",
																					    ls_nombre
																					);
																		}
																	}
																}
															}
															else if(
															    ls_medioNotificar.equalsIgnoreCase(
																        MedioNotificarCommon.CORREO_ELECTRONICO
																    )
															)
															{
																PersonaCorreoElectronico lpce_pce;

																lpce_pce = new PersonaCorreoElectronico();

																lpce_pce.setIdPersona(ls_idPersona);

																lpce_pce.setIdCorreoElectronico(
																    lso_solicitud1.getIdCorreoElectronico()
																);

																lpce_pce = DaoCreator.getPersonaCorreoElectronicoDAO(
																	    adm_manager
																	).findById(lpce_pce);

																if(lpce_pce != null)
																{
																	ls_tag = "<TAG_CORREO_ELECTRONICO>";

																	if(ls_plantilla.contains(ls_tag))
																	{
																		String ls_email;
																		ls_email         = lpce_pce.getCorreoElectronico();
																		ls_plantilla     = saltoDeCarroDespues(
																			    ls_plantilla, ls_tag, ls_email
																			);
																	}
																}
															}
														}
													}
												}
											}
										}

										if(ls_plantilla.contains("<TAG_MATRICULAS>"))
										{
											StringBuilder                    lsb_sbMatriculas;
											Collection<RegistroCalificacion> lcrc_rc;
											String                           ls_matriculas;

											ls_matriculas        = null;
											lsb_sbMatriculas     = new StringBuilder();
											lcrc_rc              = aotc_tc.getInfoFile();

											if(CollectionUtils.isValidCollection(lcrc_rc))
											{
												int li_count;
												int li_tam;

												li_count     = 1;
												li_tam       = lcrc_rc.size();

												for(RegistroCalificacion lorc_rc : lcrc_rc)
												{
													if(lorc_rc != null)
													{
														String ls_idMatriculaCompleta;
														String ls_comaPunto;

														ls_idMatriculaCompleta     = lorc_rc.getIdCirculo();
														ls_comaPunto               = (li_count < li_tam) ? "," : "";

														lsb_sbMatriculas = lsb_sbMatriculas.append(
															    ls_idMatriculaCompleta + ls_comaPunto
															);

														li_count++;
													}
												}

												ls_matriculas = lsb_sbMatriculas.toString();

												if(StringUtils.isValidString(ls_matriculas))
													ls_plantilla = ls_plantilla.replace(
														    "<TAG_MATRICULAS>", ls_matriculas
														);
											}else 
											{
												
												Collection<SolicitudMatricula> lcsm_sm; 
												lcsm_sm = DaoCreator.getSolicitudMatriculaDAO(adm_manager).
														findByIdSolicitudCirculo(lt_turno.getIdSolicitud(), lt_turno.getIdCirculo());
												
												if(CollectionUtils.isValidCollection(lcsm_sm)) 
												{
													int           li_tam;
													int           li_count;
													StringBuilder lsb_sb;
													String        ls_espacio;
	
													li_tam         = lcsm_sm.size();
													li_count       = 1;
													lsb_sb         = new StringBuilder();
													ls_espacio     = IdentificadoresCommon.SIMBOLO_COMA
														+ IdentificadoresCommon.ESPACIO_VACIO;
	
													for(SolicitudMatricula lsm_iterador : lcsm_sm)
													{
														if(lsm_iterador != null)
														{
															lsb_sb.append(
															    lsm_iterador.getIdCirculo()
															    + IdentificadoresCommon.SIMBOLO_GUION
															    + lsm_iterador.getIdMatricula()
															);
	
															if(li_count < li_tam)
																lsb_sb.append(ls_espacio);
														}
	
														li_count++;
													}
	
													{
														ls_matriculas = lsb_sb.toString();
	
														if(StringUtils.isValidString(ls_matriculas))
															ls_plantilla = ls_plantilla.replace("<TAG_MATRICULAS>", ls_matriculas);
													}
												}
											}
										}
										
										{
											ls_tag = "<TAG_FECHA_LARGA>";

											if(ls_plantilla.contains(ls_tag))
											{
												Date   ld_fechaActual;
												String ls_fechaActual;

												ld_fechaActual     = new Date();
												ls_fechaActual     = DateUtils.convertirLetrasLarga(ld_fechaActual);

												if(StringUtils.isValidString(ls_fechaActual))
													ls_plantilla = ls_plantilla.replace(
														    ls_tag, ls_fechaActual.toUpperCase()
														);
											}
										}
										
										{
											ls_tag = "<TAG_FECHA_TURNO>";

											if(ls_plantilla.contains(ls_tag))
											{
												Turno ls_turnoTemp;
												ls_turnoTemp = new Turno();

												ls_turnoTemp.setIdTurno(lt_turno.getIdTurno());

												ls_turnoTemp = DaoCreator.getTurnoDAO(adm_manager)
														                     .findById(ls_turnoTemp);

												if(ls_turnoTemp != null)
												{
													String ls_fechaTurno;
													ls_fechaTurno = DateUtils.convertirLetrasLarga(
														    ls_turnoTemp.getFechaCreacion()
														);

													if(StringUtils.isValidString(ls_fechaTurno))
														ls_plantilla = ls_plantilla.replace(
															    ls_tag, ls_fechaTurno
															);
												}
											}
										}

										{
											ls_tag = "<TAG_NIR>";

											if(ls_plantilla.contains(ls_tag))
											{
												if(lso_solicitud1 != null)
												{
													String ls_nir;
													ls_nir = lso_solicitud1.getNir();

													if(StringUtils.isValidString(ls_nir))
														ls_plantilla = ls_plantilla.replace(ls_tag, ls_nir);
												}
											}
										}

										{
											ls_tag = "<TAG_NIR_VINCULADO>";

											if(ls_plantilla.contains(ls_tag))
											{
												SolicitudAsociada lsa_tmp;
												lsa_tmp = DaoCreator.getSolicitudAsociadaDAO(adm_manager)
														                .findByIdProceso(
														    lt_turno.getIdSolicitud(), true
														);

												if(lsa_tmp != null)
												{
													String ls_nir1;

													ls_nir1 = lsa_tmp.getNirSolicitud1();

													if(StringUtils.isValidString(ls_nir1))
														ls_plantilla = ls_plantilla.replace(ls_tag, ls_nir1);
												}
											}
										}

										String ls_consecutivoOficio;
										String ls_referenciaSalida;
										Long   ll_consecutivoResolucion;

										ls_consecutivoOficio         = null;
										ls_referenciaSalida          = null;
										ll_consecutivoResolucion     = null;

										if(ab_firmaMasiva)
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
														ls_consecutivoOficio     = lnoc_numeracionOficios
																.getConsecutivoGenerado();

														ls_plantilla = ls_plantilla.replace(
															    ls_tag, ls_consecutivoOficio
															);
													}
												}
											}

											{
												ls_tag = "<TAG_REFERENCIA_SALIDA>";

												if(ls_plantilla.contains(ls_tag))
												{
													ls_referenciaSalida     = generarIdCorrespondencia(
														    lth_turnoHistoria, adm_manager, false
														);

													ls_plantilla = ls_plantilla.replace(ls_tag, ls_referenciaSalida);
												}
											}
										}

										{
											ls_tag = "<TAG_USUARIO>";

											if(ls_plantilla.contains(ls_tag))
											{
												String ls_usuario;
												ls_usuario = as_userId;

												if(StringUtils.isValidString(ls_usuario))
													ls_plantilla = ls_plantilla.replace(ls_tag, ls_usuario);
											}
										}

										{
											Constantes lc_usuarioFirma;
											byte[]     lba_resolucion;
											String     ls_tagUsuarioFirma;
											int        li_incrX = 0;
											int        li_incrY = 0;

											lc_usuarioFirma     = new Constantes();
											ls_tagUsuarioFirma  = "<TAG_USUARIO_FIRMA_SUSPENSION>";

											if(
											    ls_plantilla.contains(ls_tagUsuarioFirma)
												    && ls_plantilla.contains("<TAG_CARGO_FIRMA_SUSPENSION>")
											)
											{
												lc_usuarioFirma.setIdConstante(
												    ConstanteCommon.USUARIO_FIRMA_SUSPENSION
												);

												lc_usuarioFirma     = lcd_DAO.findByIdWithImage(lc_usuarioFirma);
												ls_plantilla        = getFirmaMasivaBusiness()
														                      .reemplazarUsuarioFirmaCargo(
														    ls_plantilla, lc_usuarioFirma, ls_tagUsuarioFirma,
														    "<TAG_CARGO_FIRMA_SUSPENSION>"
														);
											}

											lc_usuarioFirma     = lcd_DAO.findByIdWithImage(lc_usuarioFirma);
											ls_plantilla        = getFirmaMasivaBusiness()
													                      .reemplazarUsuarioFirmaCargo(
													    ls_plantilla, lc_usuarioFirma, ls_tagUsuarioFirma,
													    "<TAG_CARGO_FIRMA_SUSPENSION>"
													);
											lmss_datos          = finalizarPlantilla(
												    ls_plantilla, lth_turnoHistoria.getIdTurnoHistoria(), adm_manager
												);
											ls_plantilla        = lmss_datos.get(IdentificadoresCommon.PLANTILLA);
											lba_resolucion      = new PDFGenerator().convertirRTFToPDF(
												    ls_plantilla.getBytes(), adm_manager
												);

											if(lba_resolucion == null)
												throw new B2BException(ErrorKeys.NO_SE_GENERO_RESOLUCION);

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

												lba_resolucion = getFirmaMasivaBusiness()
														                 .reemplazarBookmarsFirma(
														    lba_resolucion, lba_grafo, li_incrX, li_incrY
														);
											}

											lb_archivo = lba_resolucion;

											if(aotc_tc.isSalvar())
											{
												int              li_tamanoArchivo;
												DocumentosSalida lds_documento;
												Imagenes         li_imagen;
												Double           ld_kbs;
												long             ll_secuenciaImagen;

												lds_documento          = new DocumentosSalida();
												li_imagen              = new Imagenes();
												li_tamanoArchivo       = lba_resolucion.length;
												ld_kbs                 = NumericUtils.getDoubleWrapper(
													    li_tamanoArchivo * 0.001
													);
												ll_secuenciaImagen     = 0L;

												li_imagen.setImagenBlob(lba_resolucion);
												li_imagen.setTipoArchivo(ExtensionCommon.PDF_MAYUS);
												li_imagen.setTamano(NumericUtils.getLongWrapper(ld_kbs.doubleValue()));
												li_imagen.setIdUsuarioCreacion(as_userId);
												li_imagen.setIpCreacion(as_remoteIp);

												ll_secuenciaImagen = DaoCreator.getImagenesDAO(adm_manager)
														                           .insertOrUpdate(li_imagen, true);

												if(ll_secuenciaImagen <= 0L)
													throw new B2BException(ErrorKeys.SIN_SECUENCIA_IMAGENES);

												lds_documento.setIdTurnoHistoria(
												    NumericUtils.getInteger(lth_turnoHistoria.getIdTurnoHistoria())
												);
												lds_documento.setIdSolicitud(lso_solicitud1.getIdSolicitud());
												lds_documento.setIdTurno(lt_turno.getIdTurno());
												lds_documento.setIdImagen(
												    NumericUtils.getLongWrapper(ll_secuenciaImagen)
												);

												lds_documento.setDestinatario(lsb_nombre.toString());
												lds_documento.setDireccion(ls_direccioncompleta);
												lds_documento.setIdPais(ls_idPais);
												lds_documento.setIdDepartamento(ls_idDepartamento);
												lds_documento.setIdMunicipio(ls_idMunicipio);

												lds_documento.setTipoArchivo(ExtensionCommon.PDF_MAYUS);
												lds_documento.setTipo(TipoArchivoCommon.COMUNICADO);
												lds_documento.setIdTipoDocumental(TipoDocumentalCommon.COMUNICACION);
												lds_documento.setConsecutivoOficio(ls_consecutivoOficio);
												lds_documento.setConsecutivoResolucion(ll_consecutivoResolucion);
												lds_documento.setReferenciaSalida(ls_referenciaSalida);
												lds_documento.setFechaOficio(ab_firmaMasiva ? new Date() : null);
												lds_documento.setFechaResolucion(ab_firmaMasiva ? new Date() : null);
												lds_documento.setRepositorio(
												    ab_firmaMasiva ? RepositorioCommon.FINAL : RepositorioCommon.TEMPORAL
												);
												lds_documento.setEstado(EstadoCommon.ACTIVO);
												lds_documento.setIdUsuarioCreacion(as_userId);
												lds_documento.setIpCreacion(as_remoteIp);

												DaoCreator.getDocumentosSalidaDAO(adm_manager)
													          .insertOrUpdate(lds_documento, true);
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
			clh_LOGGER.error("genereteFileComunicadoDireccion", lb2be_e);

			throw lb2be_e;
		}
		catch(Exception le_e)
		{
			clh_LOGGER.error("genereteFileComunicadoDireccion", le_e);

			throw new B2BException(le_e.getLocalizedMessage());
		}

		return lb_archivo;
	}

	/**
	 * Método generar pdf de registro
	 *
	 * @param aotc_tc
	 *            Objeto para extraer información necesaria para el pdf
	 * @param ab_firmaMasiva
	 *            booleano para saber si es de firma masiva
	 * @param as_userId
	 *            String de usuario en sesión
	 * @param as_remoteIp
	 *            Ip de usuario en sesión
	 * @return
	 * @throws B2BException
	 */
	public synchronized byte[] genereteFileRegistro(
	    RegistroCalificacion aotc_tc, boolean ab_firmaMasiva, String as_userId, String as_remoteIp,
	    boolean ab_terminarEtapa
	)
	    throws B2BException
	{
		if(aotc_tc == null)
			throw new B2BException(ErrorKeys.ERROR_INTERNO_SISTEMA);

		DAOManager ldm_manager;
		byte[]     lba_registroInscripcion;

		lba_registroInscripcion     = null;
		ldm_manager                 = DaoManagerFactory.getDAOManager();

		try
		{
			if(aotc_tc.isIndVinculado())
			{
				TurnoHistoria lth_th;
				Long          ll_turnoHistoria;

				lth_th               = new TurnoHistoria();
				ll_turnoHistoria     = aotc_tc.getIdTurnoHistoria();
				lth_th.setIdTurnoHistoria(ll_turnoHistoria);

				lth_th = DaoCreator.getTurnoHistoriaDAO(ldm_manager).findById(lth_th);

				if(lth_th != null)
				{
					Collection<Long> lcl_cl;
					String           ls_turno;

					lcl_cl       = new ArrayList<Long>();
					ls_turno     = lth_th.getIdTurno();

					lcl_cl.add(ll_turnoHistoria);

					if(StringUtils.isValidString(ls_turno))
					{
						TurnoDerivado             ltd_turnosDerivado;
						Collection<TurnoDerivado> lctd_turnoDerivados;

						ltd_turnosDerivado = new TurnoDerivado();
						ltd_turnosDerivado.setIdTurnoPadre(ls_turno);

						lctd_turnoDerivados = DaoCreator.getTurnoDerivadoDAO(ldm_manager)
								                            .findByIdTurnoPadreVinculados(ltd_turnosDerivado);

						if(CollectionUtils.isValidCollection(lctd_turnoDerivados))
						{
							Iterator<TurnoDerivado> litd_td;
							boolean                 lb_b;
							String                  ls_turnoHijo;

							litd_td          = lctd_turnoDerivados.iterator();
							lb_b             = false;
							ls_turnoHijo     = null;

							while(litd_td.hasNext() && !lb_b)
							{
								TurnoDerivado ltd_td;
								ltd_td = litd_td.next();

								if(ltd_td != null)
								{
									lb_b             = true;
									ls_turnoHijo     = ltd_td.getIdTurnoHijo();
								}
							}

							if(StringUtils.isValidString(ls_turnoHijo))
							{
								lth_th = new TurnoHistoria();

								lth_th.setIdTurno(ls_turnoHijo);

								lth_th = DaoCreator.getTurnoHistoriaDAO(ldm_manager).findByIdTurno(lth_th, true);

								if(lth_th != null)
								{
									lcl_cl.add(lth_th.getIdTurnoHistoria());

									if(CollectionUtils.isValidCollection(lcl_cl))
									{
										int                      li_i;
										Collection<ZipEntryUtil> lczeu_zip;

										lczeu_zip     = new ArrayList<ZipEntryUtil>();
										li_i          = 0;

										for(Long ll_tmp : lcl_cl)
										{
											if(NumericUtils.isValidLong(ll_tmp))
											{
												aotc_tc.setIdTurnoHistoria(ll_tmp);

												byte[] lb_tmp;

												lb_tmp = genereteFileRegistro(
													    ldm_manager, aotc_tc, ab_firmaMasiva, as_userId, as_remoteIp,
													    ab_terminarEtapa
													);

												if(lb_tmp != null)
												{
													ZipEntryUtil lzeu_pdf;

													li_i     = li_i + 1;

													lzeu_pdf = new ZipEntryUtil(
														    ConstanteCommon.CONSTANCIA_INSCRIPCION + li_i
														    + ExtensionCommon.PDF_MAYUS_PUNTO,
														    new ByteArrayInputStream(lb_tmp)
														);
													lczeu_zip.add(lzeu_pdf);
												}
											}
										}

										if(CollectionUtils.isValidCollection(lczeu_zip))
											lba_registroInscripcion = ZipUtils.generateZip(lczeu_zip);
									}
								}
							}
						}
					}
				}
			}
			else
				lba_registroInscripcion = genereteFileRegistro(
					    ldm_manager, aotc_tc, ab_firmaMasiva, as_userId, as_remoteIp, ab_terminarEtapa
					);
			
			if(aotc_tc.isSalvar())
			{
				if(aotc_tc.isGenerarPDFCorrespondenciaRegistro() && !aotc_tc.isMedidaCautelar())
					genereteFileComunicadoDireccion(ldm_manager, aotc_tc, as_userId, as_remoteIp, ab_firmaMasiva);
				
				if(aotc_tc.isCancelacion())
				{
					aotc_tc.setNotaDevolutiva(false);
					aotc_tc.setMedidaCautelar(false);
					aotc_tc.setNotaDevolutivaMedidaCautelar(false);
					
					genereteFileCancelacion(ldm_manager, aotc_tc, ab_firmaMasiva, as_userId, as_remoteIp);
				}
	
				if(aotc_tc.isGenerarArchivoNotaExcesoPago())
				{
					TurnoHistoria lth_turnoHistoria;
					lth_turnoHistoria = new TurnoHistoria();
	
					lth_turnoHistoria.setIdTurnoHistoria(aotc_tc.getIdTurnoHistoria());
					lth_turnoHistoria.setNotaExcesoCalificacion(true);
	
					getLiquidacionBusiness()
						    .generarPDFNotaInformativaPorPagoEnExceso(lth_turnoHistoria, as_userId, as_remoteIp);
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("genereteFileRegistro", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lba_registroInscripcion;
	}

	/**
	 * Método generar pdf de registro
	 *
	 * @param aotc_tc
	 *            Objeto para extraer información necesaria para el pdf
	 * @param ab_firmaMasiva
	 *            booleano para saber si es de firma masiva
	 * @param as_userId
	 *            String de usuario en sesión
	 * @param as_remoteIp
	 *            Ip de usuario en sesión
	 * @return
	 * @throws B2BException
	 */
	public synchronized byte[] genereteFileRegistro(
	    DAOManager adm_manager, RegistroCalificacion aotc_tc, boolean ab_firmaMasiva, String as_userId,
	    String as_remoteIp, boolean ab_terminarEtapa
	)
	    throws B2BException
	{
		if((aotc_tc == null) || (adm_manager == null))
			throw new B2BException(ErrorKeys.ERROR_INTERNO_SISTEMA);

		byte[]                                             lba_registroInscripcion;
		boolean                                            lb_job190;
		String                                             ls_constante;
		byte[]                                             lba_plantilla;
		Constantes                                         loc_datos;
		Constantes                                         loc_plantilla;
		String                                             ls_plantilla;
		String                                             ls_direccionMatricula;
		TurnoHistoriaDAO                                   lothd_thd;
		TurnoHistoria                                      loth_detalle;
		String                                             ls_hora;
		Date                                               ld_fechaActual;
		DateFormat                                         lsf_formatTime;
		Calendar                                           lic_fecha;
		int                                                li_anho;
		int                                                li_mes;
		int                                                li_dia;
		StringBuilder                                      lsb_sbMatriculas;
		String                                             ls_matriculas;
		RegistroCalificacionDAO                            lorcd_rcd;
		ConsultasDAO                                       loc_dao;
		int                                                li_tmp;
		String                                             ls_circulo;
		Long                                               ll_Matricula;
		RegistroCalificacion                               lcrc_basicosMatricula;
		String                                             ls_idSolicitud;
		Collection<RegistroCalificacion>                   lcrc_dataPredio;
		Collection<RegistroCalificacion>                   lcrc_dataPredioCiudadano;
		RegistroCalificacion                               lorc_data;
		String                                             ls_idAnotacion;
		Long                                               ll_idAnotacion;
		RegistroCalificacion                               lorc_dataDocumento;
		String                                             lsb_detalleMatricula;
		String                                             ls_matriculaFinal;
		String                                             ls_anotacion;
		String                                             ls_documento;
		String                                             ls_cancela;
		String                                             ls_especificacion;
		String                                             ls_comentario;
		String                                             ls_text;
		StringBuilder                                      lsb_intervinientes;
		StringBuilder                                      lsb_detalleMatriculas;
		StringBuilder                                      lsb_detalleMatriculasParcial;
		String                                             ls_interviniente;
		Map<String, Boolean>                               llhm_datosMatriculas;
		String                                             ls_linea;
		String                                             ls_idTurno;
		Long                                               ll_idTurnoHistoria;
		boolean                                            lb_isActoSegregacion;
		boolean                                            lb_devolucion;
		com.bachue.snr.prosnr01.dao.acc.AnotacionPredioDAO lapd_DAO;
		int                                                li_matriculas;
		int                                                li_revision;
		StringBuilder                                      lsb_tmp;
		AnotacionCancelacionDAO                            loacd_dao;
		String                                             ls_cancelaciones;
		boolean                                            lb_naturaleza700;
		boolean                                            lb_inscripcionParcial;
		Map<String, Boolean>                               lcmsb_tmp;
		String                                             ls_consecutivoOficio;
		String                                             ls_referenciaSalida;
		Long                                               ll_consecutivoResolucion;

		lcmsb_tmp                        = new HashMap<String, Boolean>();
		lic_fecha                        = Calendar.getInstance();
		li_anho                          = lic_fecha.get(Calendar.YEAR);
		li_mes                           = lic_fecha.get(Calendar.MONTH) + 1;
		li_dia                           = lic_fecha.get(Calendar.DAY_OF_MONTH);
		loc_datos                        = new Constantes();
		loc_plantilla                    = new Constantes();
		lorc_data                        = new RegistroCalificacion();
		llhm_datosMatriculas             = aotc_tc.getDatosParcial();
		ls_constante                     = ConstanteCommon.PLANTILLA_REGISTRO;
		loth_detalle                     = new TurnoHistoria();
		lsb_sbMatriculas                 = new StringBuilder();
		lsb_detalleMatricula             = null;
		ls_direccionMatricula            = null;
		lsb_intervinientes               = new StringBuilder();
		ls_matriculas                    = null;
		ls_especificacion                = null;
		ls_cancela                       = null;
		ls_anotacion                     = null;
		ls_documento                     = null;
		ls_matriculaFinal                = null;
		lsb_detalleMatriculas            = new StringBuilder();
		lsb_detalleMatriculasParcial     = new StringBuilder();
		lsb_tmp                          = new StringBuilder();
		ls_linea                         = "===============================================================================================================";
		ls_idTurno                       = "";
		lb_naturaleza700                 = false;
		lb_inscripcionParcial            = false;
		ls_comentario                    = "";
		ls_consecutivoOficio             = null;
		ls_referenciaSalida              = null;
		ll_consecutivoResolucion         = null;

		lba_registroInscripcion     = null;
		lb_job190                   = aotc_tc.isJob190();

		lothd_thd     = DaoCreator.getTurnoHistoriaDAO(adm_manager);
		lorcd_rcd     = DaoCreator.getRegistroCalificacionDAO(adm_manager);
		loc_dao       = DaoCreator.getConsultasDAO(adm_manager);
		lapd_DAO      = DaoCreator.getAccAnotacionPredioDAO(adm_manager);
		loacd_dao     = DaoCreator.getAnotacionCancelacionDAO(adm_manager);

		if(aotc_tc.isCierreFolio() && aotc_tc.isSalvarCierreFolio())
			salvarCierreFolio(aotc_tc, adm_manager);

		if(CollectionUtils.isValidCollection(llhm_datosMatriculas))
		{
			Boolean lB_boolean;

			lB_boolean     = Boolean.TRUE;

			lb_inscripcionParcial = llhm_datosMatriculas.containsValue(lB_boolean);

			if(lb_inscripcionParcial)
				ls_constante = ConstanteCommon.PLANTILLA_REGISTRO_PARCIAL;
		}

		loc_datos.setIdConstante(ls_constante);

		loc_plantilla            = DaoCreator.getConstantesDAO(adm_manager).findImagen(loc_datos);
		ll_idTurnoHistoria       = aotc_tc.getIdTurnoHistoria();
		lb_isActoSegregacion     = aotc_tc.isActoSegregacion();
		lb_devolucion            = aotc_tc.isDevolucion();
		li_matriculas            = aotc_tc.getTotalMatriculasRevision();
		li_revision              = aotc_tc.getTotalRevision();

		if(loc_plantilla != null)
		{
			lba_plantilla = loc_plantilla.getImagenes();

			if(lba_plantilla != null)
			{
				ld_fechaActual     = new Date();
				lsf_formatTime     = new SimpleDateFormat("HH:mm:ss");
				ls_hora            = lsf_formatTime.format(ld_fechaActual);
				ls_plantilla       = new String(lba_plantilla);

				if(StringUtils.isValidString(ls_plantilla))
				{
					loth_detalle.setIdTurnoHistoria(ll_idTurnoHistoria);
					loth_detalle = lothd_thd.findById(loth_detalle);

					if(loth_detalle != null)
					{
						Collection<RegistroCalificacion> lcrc_rc;
						Map<String, String>              lmss_datos;

						lcrc_rc                = aotc_tc.getInfoFile();
						lmss_datos             = null;
						ls_idTurno             = loth_detalle.getIdTurno();
						lorc_dataDocumento     = lorcd_rcd.findBasicosDocumento(ll_idTurnoHistoria);
						ls_idSolicitud         = loth_detalle.getIdSolicitud();

						if(!StringUtils.isValidString(ls_idSolicitud))
							throw new B2BException(ErrorKeys.ID_SOLICITUD_INVALIDO);

						if(ls_plantilla.contains("<TAG_TITULO>"))
						{
							Solicitud ls_solicitud;

							ls_solicitud = new Solicitud();

							ls_solicitud.setIdSolicitud(ls_idSolicitud);

							ls_solicitud = DaoCreator.getSolicitudDAO(adm_manager).findById(ls_solicitud);

							if(ls_solicitud != null)
							{
								String ls_idSubproceso;

								ls_idSubproceso = ls_solicitud.getIdSubproceso();

								if(StringUtils.isValidString(ls_idSubproceso) && lb_inscripcionParcial)
									ls_plantilla = ls_plantilla.replace(
										    "<TAG_TITULO>", "CONSTANCIA DE INSCRIPCIÓN PARCIAL"
										);
							}
						}

						if(ls_plantilla.contains("<TAG_DIA>"))
						{
							String ls_tmp;
							ls_tmp = String.valueOf(li_dia);

							if(StringUtils.isValidString(ls_tmp))
								ls_plantilla = ls_plantilla.replace("<TAG_DIA>", ls_tmp);
						}

						if(ls_plantilla.contains("<TAG_MES>"))
						{
							String ls_tmp;
							ls_tmp = String.valueOf(li_mes);

							if(StringUtils.isValidString(ls_tmp))
								ls_plantilla = ls_plantilla.replace("<TAG_MES>", ls_tmp);
						}

						if(ls_plantilla.contains("<TAG_MES_LETRAS>"))
						{
							String ls_tmp;
							ls_tmp = DateUtils.getMes(li_mes);

							if(StringUtils.isValidString(ls_tmp))
								ls_plantilla = ls_plantilla.replace("<TAG_MES_LETRAS>", ls_tmp);
						}

						if(ls_plantilla.contains("<TAG_ANHO>"))
						{
							String ls_tmp;
							ls_tmp = String.valueOf(li_anho);

							if(StringUtils.isValidString(ls_tmp))
								ls_plantilla = ls_plantilla.replace("<TAG_ANHO>", ls_tmp);
						}

						if(ls_plantilla.contains("<TAG_HORA>"))
						{
							if(StringUtils.isValidString(ls_hora))
								ls_plantilla = ls_plantilla.replace("<TAG_HORA>", ls_hora);
						}

						if(ls_plantilla.contains("<TAG_ID_TURNO>"))
						{
							if(StringUtils.isValidString(ls_idTurno))
								ls_plantilla = ls_plantilla.replace("<TAG_ID_TURNO>", ls_idTurno);
						}

						String ls_tagReferencia;
						String ls_tagNumeroProceso;

						ls_tagReferencia        = "<TAG_REFERENCIA>";
						ls_tagNumeroProceso     = "<TAG_NUMERO_PROCESO>";

						if(ls_plantilla.contains(ls_tagReferencia) || ls_plantilla.contains(ls_tagNumeroProceso))
						{
							String ls_procesoLetras;
							String ls_referenciaLetras;

							ls_procesoLetras        = "<TAG_NUM_PROCESO_LETRAS>";
							ls_referenciaLetras     = "<TAG_REF_LETRAS>";

							if(aotc_tc.isMedidaCautelar())
							{
								if(ls_plantilla.contains(ls_procesoLetras))
									ls_plantilla = ls_plantilla.replace(ls_procesoLetras, "Número proceso:");

								if(ls_plantilla.contains(ls_referenciaLetras))
									ls_plantilla = ls_plantilla.replace(ls_referenciaLetras, "Referencia:");

								ls_plantilla     = ls_plantilla.replace(
									    ls_tagReferencia, StringUtils.getStringNotNull(aotc_tc.getReferencia())
									);
								ls_plantilla     = ls_plantilla.replace(
									    ls_tagNumeroProceso, StringUtils.getStringNotNull(aotc_tc.getNumeroProceso())
									);
							}
							else
							{
								ls_plantilla     = ls_plantilla.replace(ls_tagReferencia, "");
								ls_plantilla     = ls_plantilla.replace(ls_tagNumeroProceso, "");
								ls_plantilla     = ls_plantilla.replace(ls_procesoLetras, "");
								ls_plantilla     = ls_plantilla.replace(ls_referenciaLetras, "");
							}
						}

						if(ls_plantilla.contains("<TAG_ID_NIR>"))
						{
							String        ls_nr;
							TurnoHistoria lth_th;

							ls_nr      = aotc_tc.getNir();
							lth_th     = DaoCreator.getTurnoHistoriaDAO(adm_manager).findById(ll_idTurnoHistoria);

							if(lth_th != null)
							{
								Solicitud ls_solicitud;

								ls_solicitud = DaoCreator.getSolicitudDAO(adm_manager).findById(
									    lth_th.getIdSolicitud()
									);

								if(ls_solicitud != null)
									ls_nr = ls_solicitud.getNir();
							}

							if(StringUtils.isValidString(ls_nr))
								ls_plantilla = ls_plantilla.replace("<TAG_ID_NIR>", ls_nr);
						}

						if(ls_plantilla.contains("<TAG_ID_USUARIO>"))
						{
							if(StringUtils.isValidString(as_userId))
								ls_plantilla = ls_plantilla.replace("<TAG_ID_USUARIO>", as_userId);
						}

						if(ls_plantilla.contains("<TAG_MATRICULAS>"))
						{
							if(CollectionUtils.isValidCollection(lcrc_rc))
							{
								int li_count;
								int li_tam;

								li_count     = 1;
								li_tam       = lcrc_rc.size();

								for(RegistroCalificacion lorc_rc : lcrc_rc)
								{
									if(lorc_rc != null)
									{
										String ls_idMatriculaCompleta;
										String ls_comaPunto;

										ls_idMatriculaCompleta     = lorc_rc.getIdCirculo();
										ls_comaPunto               = (li_count < li_tam) ? "," : "";

										if(!lorc_rc.isIndDummy())
											lcmsb_tmp.put(ls_idMatriculaCompleta, Boolean.TRUE);

										lsb_sbMatriculas = lsb_sbMatriculas.append(
											    ls_idMatriculaCompleta + ls_comaPunto
											);

										li_count++;
									}
								}

								ls_matriculas = lsb_sbMatriculas.toString();

								if(StringUtils.isValidString(ls_matriculas))
									ls_plantilla = ls_plantilla.replace("<TAG_MATRICULAS>", ls_matriculas);
							}
						}

						if(
						    ls_plantilla.contains("<TAG_MATRICULA_DETALLE>")
							    || ls_plantilla.contains("<TAG_MATRICULA_DETALLE_PARCIAL>")
						)
						{
							boolean lb_matriculas;

							lb_matriculas = StringUtils.isValidString(ls_matriculas);

							if(lb_matriculas)
							{
								String[] lsa_sa;

								lsa_sa = null;

								if(lb_matriculas)
									lsa_sa = ls_matriculas.split(",");

								if(CollectionUtils.isValidCollection(lsa_sa))
								{
									for(String ls_matricula : lsa_sa)
									{
										if(StringUtils.isValidString(ls_matricula))
										{
											boolean lb_parcial;

											lcrc_basicosMatricula     = new RegistroCalificacion();
											li_tmp                    = ls_matricula.indexOf("-");
											ls_circulo                = ls_matricula.substring(0, li_tmp);
											ll_Matricula              = NumericUtils.getLongWrapper(
												    ls_matricula.substring(li_tmp + 1)
												);

											lcrc_basicosMatricula = lorcd_rcd.findBasicosMatricula(
												    ls_circulo, ll_Matricula, true
												);

											if(!lb_job190 && (lcrc_basicosMatricula == null))
												lcrc_basicosMatricula = lorcd_rcd.findBasicosMatricula(
													    ls_circulo, ll_Matricula, false
													);

											if(lcrc_basicosMatricula != null)
											{
												ls_direccionMatricula = loc_dao.findDireccionMatricula(
													    ls_circulo, ll_Matricula, true
													);

												if(!StringUtils.isValidString(ls_direccionMatricula))
													ls_direccionMatricula = loc_dao.findDireccionMatricula(
														    ls_circulo, ll_Matricula
														);

												if(lb_job190 || (!StringUtils.isValidString(ls_direccionMatricula)))
													ls_direccionMatricula = lorcd_rcd.findDireccionMatricula(
														    ls_circulo, ll_Matricula, ls_idSolicitud
														);

												ls_matriculaFinal = "{\\pard \\s10\\f1\\fs30 MATRÍCULA INMOBILIARIA:  "
													+ " {\\b\\fs30  " + " " + ls_circulo + " - "
													+ ll_Matricula.toString() + " }" + "\\par}" + "{\\pard \\s3\\f1"
													+ "{\\b CÍRCULO DE REGISTRO:} "
													+ lcrc_basicosMatricula.getIdCirculo() + " "
													+ lcrc_basicosMatricula.getNombreCirculo() + "\\par}"
													+ "{\\pard \\s3\\f1 " + "{\\b MUNICIPIO: }"
													+ lcrc_basicosMatricula.getNombreMunicipio() + " "
													+ "{\\b DEPARTAMENTO:}"
													+ lcrc_basicosMatricula.getNombreDepartamento() + " "
													+ "{\\b TIPO PREDIO:}" + lcrc_basicosMatricula.getTipoPredio()
													+ "\\par}" + "{\\pard \\s3\\f1" + " {\\b DIRECCIÓN DEL INMUEBLE: }"
													+ StringUtils.getStringNotNull(ls_direccionMatricula) + "\\par}"
													+ "{\\pard \\s3\\f1" + ls_linea + "\\par}";
											}

											lorc_data.setIdTurnoHistoria(ll_idTurnoHistoria);
											lorc_data.setIdCirculo(ls_circulo);
											lorc_data.setIdMatricula(ll_Matricula);
											lorc_data.setTurno(ls_idTurno);

											if(
											    BooleanUtils.getBooleanValue(
												        lcmsb_tmp.get(ls_circulo + "-" + ll_Matricula)
												    )
											)
												lorc_data.setMatriculaMatriz(true);

											if(lb_job190)
											{
												lorc_data.setJob190(!aotc_tc.isReproduccionConstancia());
												lcrc_dataPredio = DaoCreator.getAnotacionPredioDAO(adm_manager)
														                        .findDataPredio(lorc_data);
											}

											else
											{
												if(lb_devolucion)
													lcrc_dataPredio = lapd_DAO.findDataPredio(lorc_data, true);
												else
													lcrc_dataPredio = lorcd_rcd.findDataPredio(lorc_data);
											}

											if(CollectionUtils.isValidCollection(lcrc_dataPredio))
											{
												Collection<String> lcs_notas;
												lcs_notas = new LinkedList<String>();

												for(RegistroCalificacion lorc_rc : lcrc_dataPredio)
												{
													if(lorc_rc != null)
													{
														ls_idAnotacion     = null;
														ll_idAnotacion     = null;

														if(lb_job190)
															ll_idAnotacion = lorc_rc.getIdAnotacion();
														else
															ls_idAnotacion = lorc_rc.getIdAnotacionPredio();

														lcrc_dataPredioCiudadano     = new ArrayList<RegistroCalificacion>();
														ls_text                      = " PERSONAS QUE INTERVIENEN EN EL ACTO (X-Titular de derecho real de dominio,I-Titular de dominio incompleto)";

														lb_naturaleza700 = StringUtils.getStringNotNull(
															    lorc_rc.getIdNaturalezJuridica()
															).equalsIgnoreCase("0700");

														if(StringUtils.isValidString(ls_idAnotacion) || lb_job190)
														{
															if(!lb_job190)
																ll_idAnotacion = NumericUtils.getLongWrapper(
																	    ls_idAnotacion
																	);

															lsb_intervinientes     = new StringBuilder(
																    "{\\rtf1\\ansi\\deff0 "
																);
															ls_interviniente       = null;

															if(lb_job190 && NumericUtils.isValidLong(ll_idAnotacion))
															{
																lorc_data.setIdAnotacion(ll_idAnotacion);
																lcrc_dataPredioCiudadano = DaoCreator.getAnotacionPredioCiudadanoDAO(
																	    adm_manager
																	).findDataPredioAnotacion(lorc_data, false);
															}
															else
																lcrc_dataPredioCiudadano = lorcd_rcd
																		.findDataPredioAnotacion(ll_idAnotacion);

															NumberFormat lnf_numbreFormat;
															lnf_numbreFormat = NumberFormat.getNumberInstance(
																    Locale.getDefault()
																);

															String ls_valor;
															ls_valor     = lnf_numbreFormat.format(lorc_rc.getValor());

															ls_anotacion = "{\\pard \\s3\\f1" + " {\\b ANOTACION: Nro."
																+ "{\\fs20" + " " + lorc_rc.getIdAnotacion() + " }"
																+ "  " + " Fecha: }" + lorc_rc.getFechaRadicacion()
																+ "  " + " {\\b Radicación:}" + ls_idTurno
																+ "{\\tab\\tab " + "   {\\b VALOR ACTO :}" + " $"
																+ ls_valor + "}" + "\\par}";

															if(lb_naturaleza700)
															{
																Collection<AnotacionCancelacion> lcrc_anotacionesCancelacion;
																AnotacionCancelacion             loac_ac;

																loac_ac                         = new AnotacionCancelacion();
																lcrc_anotacionesCancelacion     = null;

																if(!lb_job190)
																{
																	loac_ac.setIdMatricula(
																	    NumericUtils.getLong(ll_Matricula)
																	);
																	loac_ac.setIdCirculo(ls_circulo);
																	loac_ac.setIdSolicitud(ls_idSolicitud);
																	loac_ac.setIdAnotacion(lorc_rc.getIdAnotacion());

																	lcrc_anotacionesCancelacion = loacd_dao
																			.findByCirculoMatriculaAnotaciones(loac_ac);

																	if(
																	    CollectionUtils.isValidCollection(
																		        lcrc_anotacionesCancelacion
																		    )
																	)
																	{
																		lsb_tmp = new StringBuilder();

																		for(AnotacionCancelacion loac_tmp : lcrc_anotacionesCancelacion)
																		{
																			if(loac_tmp != null)
																				lsb_tmp.append(
																				    loac_tmp.getIdAnotacion1() + ","
																				);
																		}
																	}
																}
																else
																{
																	com.bachue.snr.prosnr01.model.sdb.bng.AnotacionCancelacion             lac_ac;
																	Collection<com.bachue.snr.prosnr01.model.sdb.bng.AnotacionCancelacion> lcac_cac;

																	lac_ac = new com.bachue.snr.prosnr01.model.sdb.bng.AnotacionCancelacion();

																	lac_ac.setIdCirculo(ls_circulo);
																	lac_ac.setIdMatricula(
																	    NumericUtils.getLong(ll_Matricula)
																	);
																	lac_ac.setIdAnotacion(
																	    NumericUtils.getLong(lorc_rc.getIdAnotacion())
																	);

																	lcac_cac = DaoCreator.getBngAnotacionCancelacionDAO(
																		    adm_manager
																		).findByAnotaciones(lac_ac);

																	if(CollectionUtils.isValidCollection(lcac_cac))
																	{
																		lsb_tmp = new StringBuilder();

																		for(com.bachue.snr.prosnr01.model.sdb.bng.AnotacionCancelacion loac_tmp : lcac_cac)
																		{
																			if(loac_tmp != null)
																				lsb_tmp.append(
																				    loac_tmp.getIdAnotacion1() + ","
																				);
																		}
																	}
																}

																ls_cancelaciones     = lsb_tmp.toString();

																ls_cancela = "{\\pard \\s3\\f1 \\b"
																	+ " CANCELA LAS ANOTACIONES:" + " "
																	+ ls_cancelaciones + "\\par}";
															}
															else
																ls_cancela = " ";

															if(lb_devolucion)
																lorc_dataDocumento = loc_dao.findBasicosDocumento(
																	    ls_idAnotacion
																	);

															if(lorc_dataDocumento != null)
																ls_documento = "{\\pard \\s3\\f1"
																	+ " {\\b TIPO DE DOCUMENTO }"
																	+ lorc_rc.getNombreDoc() + " " + " {\\b No.}"
																	+ lorc_dataDocumento.getCodDocumento() + " "
																	+ " {\\b DE FECHA :}"
																	+ lorc_dataDocumento.getFechaDocumento() + " "
																	+ StringUtils.getStringNotNull(
																	    lorc_dataDocumento.getNombreOficina()
																	) + " " + lorc_dataDocumento.getNombreMunicipio()
																	+ "\\par}";

															ls_especificacion     = "{\\pard \\s3\\f1"
																+ "{\\b ESPECIFICACION:}"
																+ lorc_rc.getNombreGrupoActo() + " " + "{\\b" + " "
																+ lorc_rc.getCodActo() + " " + lorc_rc.getNombreActo()
																+ " " + "}" + "\\par}";

															ls_comentario     = "{\\pard \\s3\\f1"
																+ "{\\b COMENTARIO:}"
																+ StringUtils.getStringNotEmpty(
																    lorc_rc.getComentario()
																) + "\\par}";

															ls_text = "{\\pard \\s3\\f1 \\b" + ls_text + "\\par}";

															if(
															    CollectionUtils.isValidCollection(
																        lcrc_dataPredioCiudadano
																    )
															)
															{
																String ls_porcentaje;

																{
																	String ls_titulosInterviniente;

																	ls_titulosInterviniente     = null;

																	ls_titulosInterviniente     = "  \\trowd\\cellx800\\cellx4000\\cellx6000\\cellx7000\\cellx8000\\cellx12000\\intbl\\fs15{\\fonttbl {\\\\f0 sans-serif;}} "
																		+ "Rol"
																		+ " \\cell\\intbl\\fs15{\\fonttbl {\\f0 sans-serif;}}"
																		+ "Nombre"
																		+ " \\cell\\intbl\\fs15{\\fonttbl {\\f0 sans-serif;}}"
																		+ "Identificación"
																		+ "\\cell\\intbl\\fs15{\\fonttbl {\\f0 sans-serif;}} "
																		+ "Propietario"
																		+ "\\cell\\intbl\\fs15{\\fonttbl {\\f0 sans-serif;}}"
																		+ "%"
																		+ "\\cell\\intbl\\fs15{\\fonttbl {\\f0 sans-serif;}}"
																		+ "Calidad Interviniente" + "\\cell\\row ";

																	ls_titulosInterviniente = "{\\pard \\s3\\f1 \\b"
																		+ ls_titulosInterviniente + "}";

																	lsb_intervinientes.append(ls_titulosInterviniente);
																}

																for(RegistroCalificacion lorc_rci : lcrc_dataPredioCiudadano)
																{
																	if(lorc_rci != null)
																	{
																		ls_interviniente     = null;

																		ls_porcentaje = lorc_rci.getPorcentajeStr();

																		if(StringUtils.isValidString(ls_porcentaje))
																			ls_porcentaje = ls_porcentaje + "%";

																		String ls_tipoPersona;
																		String ls_tipoDocumento;
																		String ls_nombreCompleto;

																		ls_tipoPersona        = StringUtils
																				.getStringNotNull(
																				    lorc_rci.getIdTipoPersona()
																				);
																		ls_tipoDocumento      = StringUtils
																				.getStringNotNull(
																				    lorc_rci.getTipoDoc()
																				);
																		ls_nombreCompleto     = StringUtils
																				.getStringNotNull(
																				    lorc_rci.getNombrePersona()
																				);

																		String ls_nombre;

																		if(
																		    ls_tipoPersona.equals(EstadoCommon.J)
																			    && ls_tipoDocumento.equals(
																			        IdentificadoresCommon.NIT
																			    )
																		)
																			ls_nombre = lorc_rci.getRazonSocial();
																		else if(
																		    ls_tipoPersona.equals("I")
																			    && ls_tipoDocumento.equals("SE")
																		)
																			ls_nombre = ls_nombreCompleto
																				+ StringUtils.getStringNotNull(
																				    lorc_rci.getRazonSocial()
																				);
																		else
																			ls_nombre = ls_nombreCompleto;

																		ls_interviniente      = "  \\trowd\\cellx800\\cellx4000\\cellx6000\\cellx7000\\cellx8000\\cellx12000\\intbl\\fs15{\\fonttbl {\\\\f0 sans-serif;}}"
																			+ lorc_rci.getRolPersona()
																			+ " \\cell\\intbl\\fs15{\\fonttbl {\\f0 sans-serif;}}"
																			+ ls_nombre
																			+ " \\cell\\intbl\\fs15{\\fonttbl {\\f0 sans-serif;}}"
																			+ lorc_rci.getTipoDoc() + " "
																			+ lorc_rci.getDocumento()
																			+ "\\cell\\intbl\\fs15{\\fonttbl {\\f0 sans-serif;}} "
																			+ StringUtils.getStringNotNull(
																			    lorc_rci.getValuePropietario()
																			)
																			+ "\\cell\\intbl\\fs15{\\fonttbl {\\f0 sans-serif;}}"
																			+ StringUtils.getStringNotNull(
																			    ls_porcentaje
																			)
																			+ "\\cell\\intbl\\fs15{\\fonttbl {\\f0 sans-serif;}}"
																			+ StringUtils.getStringNotNull(
																			    lorc_rci.getInteresadaRrr()
																			) + "\\cell\\row ";

																		lsb_intervinientes.append(ls_interviniente);
																	}
																}

																lsb_intervinientes.append("}");
															}
														}

														String ls_notaInd;
														ls_notaInd = ls_anotacion + ls_documento + ls_especificacion
															+ ls_comentario + ls_cancela + ls_text
															+ lsb_intervinientes.toString() + ls_linea;

														lcs_notas.add(ls_notaInd);
													}
												}

												lsb_detalleMatricula = "{\\pard \\s3\\f1" + ls_matriculaFinal;

												if(CollectionUtils.isValidCollection(lcs_notas))
												{
													for(String data : lcs_notas)
														lsb_detalleMatricula += data;
												}

												lsb_detalleMatricula += "\\par}";

												if(CollectionUtils.isValidCollection(llhm_datosMatriculas))
												{
													lb_parcial = llhm_datosMatriculas.containsValue(Boolean.TRUE);

													if(lb_parcial)
														lsb_detalleMatriculasParcial = lsb_detalleMatriculasParcial
																.append(lsb_detalleMatricula);
													else
														lsb_detalleMatriculas = lsb_detalleMatriculas.append(
															    lsb_detalleMatricula
															);
												}
												else
													lsb_detalleMatriculas = lsb_detalleMatriculas.append(
														    lsb_detalleMatricula
														);
											}
										}
									}
								}

								if(StringUtils.isValidString(lsb_detalleMatriculas.toString()))
									ls_plantilla = ls_plantilla.replace(
										    "<TAG_MATRICULA_DETALLE>", lsb_detalleMatriculas.toString()
										);

								if(StringUtils.isValidString(lsb_detalleMatriculasParcial.toString()))
									ls_plantilla = ls_plantilla.replace(
										    "<TAG_MATRICULA_DETALLE_PARCIAL>", lsb_detalleMatriculasParcial.toString()
										);
							}
						}

						{
							String ls_tag;

							if(ab_firmaMasiva)
							{
								ls_tag = "<TAG_OFICIO>";

								if(ls_plantilla.contains(ls_tag))
								{
									NumeracionOficiosCirculo lnoc_numeracionOficios;
									lnoc_numeracionOficios = findNumeracionOficiosCirculo(
										    loth_detalle, adm_manager, as_userId, as_remoteIp
										);

									if(lnoc_numeracionOficios != null)
									{
										ls_consecutivoOficio = lnoc_numeracionOficios.getConsecutivoGenerado();

										if(ls_plantilla.contains(ls_tag))
											ls_plantilla = ls_plantilla.replace(ls_tag, ls_consecutivoOficio);
									}
								}

								if(ls_plantilla.contains(ls_tag))
								{
									ls_tag     = "<TAG_REFERENCIA_SALIDA>";

									ls_referenciaSalida = generarIdCorrespondencia(loth_detalle, adm_manager, true);

									if(ls_plantilla.contains(ls_tag))
										ls_plantilla = ls_plantilla.replace(ls_tag, ls_referenciaSalida);

									NumeracionResolucionCirculo lnrc_numeracionCirculo;

									lnrc_numeracionCirculo = findNumeracionResolucionCirculo(
										    loth_detalle, adm_manager, as_userId, as_remoteIp
										);

									if(lnrc_numeracionCirculo != null)
										ll_consecutivoResolucion = lnrc_numeracionCirculo.getConsecutivoResolucion();
								}
							}

							Constantes lc_usuarioFirma;
							String     ls_tagUsuarioFirma;
							int        li_incrX = 0;
							int        li_incrY = 0;

							lc_usuarioFirma     = new Constantes();
							ls_tagUsuarioFirma  = "<TAG_USUARIO_FIRMA_SUSPENSION>";

							lc_usuarioFirma.setIdConstante(ConstanteCommon.USUARIO_FIRMA_SUSPENSION);

							lc_usuarioFirma             = DaoCreator.getConstantesDAO(adm_manager)
									                                    .findByIdWithImage(lc_usuarioFirma);
							ls_plantilla                = getFirmaMasivaBusiness()
									                              .reemplazarUsuarioFirmaCargo(
									    ls_plantilla, lc_usuarioFirma, ls_tagUsuarioFirma,
									    "<TAG_CARGO_FIRMA_SUSPENSION>"
									);
							lmss_datos                  = finalizarPlantilla(
								    ls_plantilla, loth_detalle.getIdTurnoHistoria(), adm_manager
								);
							ls_plantilla                = lmss_datos.get(IdentificadoresCommon.PLANTILLA);
							lba_registroInscripcion     = new PDFGenerator().convertirRTFToPDF(
								    ls_plantilla.getBytes(), adm_manager
								);

							if(lba_registroInscripcion == null)
								throw new B2BException(ErrorKeys.NO_SE_GENERO_ARCHIVO);

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

								lba_registroInscripcion = getFirmaMasivaBusiness()
										                          .reemplazarBookmarsFirma(
										    lba_registroInscripcion, lba_grafo, li_incrX, li_incrY
										);
							}
						}

						if(aotc_tc.isSalvar())
						{
							if(aotc_tc.isCancelacion() || aotc_tc.isMedidaCautelar())
								genereteFileCancelacion(aotc_tc, false, as_userId, as_remoteIp);

							if(lb_job190)
							{
								TurnoHistoria lth_turnoHistoria;

								lth_turnoHistoria = DaoCreator.getTurnoHistoriaDAO(adm_manager)
										                          .findById(ll_idTurnoHistoria);

								if(lth_turnoHistoria != null)
								{
									BigDecimal lbd_idAnterior;

									lbd_idAnterior = lth_turnoHistoria.getIdAnterior();

									if(lbd_idAnterior != null)
									{
										DocumentosSalida lds_documentosSalida;

										lds_documentosSalida = new DocumentosSalida();

										lds_documentosSalida.setIdTurnoHistoria(
										    NumericUtils.getIntegerWrapper(lbd_idAnterior)
										);
										lds_documentosSalida.setIdTipoDocumental(
										    TipoDocumentalCommon.CONSTANCIA_DE_INSCRIPCION
										);

										{
											DocumentosSalidaDAO          ldsd_DAO;
											Collection<DocumentosSalida> lcds_documentosSalida;

											ldsd_DAO                  = DaoCreator.getDocumentosSalidaDAO(adm_manager);
											lcds_documentosSalida     = ldsd_DAO.findByIdTurnoHistoriaTipoDoc(
												    lds_documentosSalida
												);

											if(CollectionUtils.isValidCollection(lcds_documentosSalida))
											{
												lds_documentosSalida = lcds_documentosSalida.iterator().next();

												if(lds_documentosSalida != null)
												{
													lds_documentosSalida.setEstado(EstadoCommon.INACTIVO);
													lds_documentosSalida.setIdUsuarioModificacion(as_userId);
													lds_documentosSalida.setIpModificacion(as_remoteIp);

													ldsd_DAO.insertOrUpdate(lds_documentosSalida, false);
												}
											}
										}
									}
								}
							}

							{
								Imagenes li_imagen;
								long     li_image;

								li_imagen = new Imagenes();

								li_imagen.setImagenBlob(lba_registroInscripcion);
								li_imagen.setTipoArchivo(ExtensionCommon.PDF_MAYUS);
								li_imagen.setTamano(NumericUtils.getLongWrapper(lba_registroInscripcion.length));
								li_imagen.setIdUsuarioCreacion(as_userId);
								li_imagen.setIpCreacion(as_remoteIp);
								li_imagen.setCodigoVerificacion(
								    lmss_datos.get(IdentificadoresCommon.CODIGO_VERIFICACION)
								);

								if(aotc_tc.isIndSegregacion())
									CalcularRevision(
									    li_matriculas, li_revision, adm_manager,
									    ConstanteCommon.PORCENTAJE_REVISION_MATRICULAS
									);

								li_image = DaoCreator.getImagenesDAO(adm_manager).insertOrUpdate(li_imagen, true);

								if(li_image > 0)
								{
									DocumentosSalida lds_documento;

									lds_documento = new DocumentosSalida();

									lds_documento.setIdImagen(NumericUtils.getLongWrapper(li_image));
									lds_documento.setIdTurno(ls_idTurno);
									lds_documento.setIdTurnoHistoria(NumericUtils.getInteger(ll_idTurnoHistoria));
									lds_documento.setIdSolicitud(ls_idSolicitud);
									lds_documento.setTipoArchivo(ExtensionCommon.PDF_MAYUS);
									lds_documento.setIdUsuarioCreacion(as_userId);
									lds_documento.setIpCreacion(as_remoteIp);
									lds_documento.setConsecutivoOficio(ls_consecutivoOficio);
									lds_documento.setConsecutivoResolucion(ll_consecutivoResolucion);
									lds_documento.setFechaOficio(ab_firmaMasiva ? new Date() : null);
									lds_documento.setFechaResolucion(ab_firmaMasiva ? new Date() : null);

									if(
									    (!lb_job190)
										    && (lb_isActoSegregacion || aotc_tc.isDesenglobe()
										    || aotc_tc.isVentaParcial() || aotc_tc.isEnglobe())
									)
									{
										lds_documento.setEstado(EstadoCommon.ENTREGA);
										lds_documento.setRepositorio(RepositorioCommon.FINAL);
									}
									else
									{
										lds_documento.setEstado(EstadoCommon.ACTIVO);
										lds_documento.setRepositorio(
										    ab_firmaMasiva ? RepositorioCommon.FINAL : RepositorioCommon.TEMPORAL
										);
									}

									if(lb_inscripcionParcial)
										lds_documento.setTipo(TipoArchivoCommon.REGISTRO_PARCIAL);
									else
										lds_documento.setTipo(TipoArchivoCommon.REGISTRO);

									lds_documento.setIdTipoDocumental(TipoDocumentalCommon.CONSTANCIA_DE_INSCRIPCION);

									DaoCreator.getDocumentosSalidaDAO(adm_manager).insertOrUpdate(lds_documento, true);
								}
								else
									throw new B2BException(ErrorKeys.SIN_SECUENCIA_IMAGENES);
							}

							if(ab_terminarEtapa)
							{
								boolean          lb_baldios;
								MotivoTramite    lmt_motivo;
								MotivoTramiteDAO lmtd_DAO;
								long             ll_idMotivo;

								lb_baldios      = aotc_tc.isBaldios();
								ll_idMotivo     = MotivoTramiteCommon.REALIZAR_REGISTRO;

								if(aotc_tc.isIndVinculado())
								{
									Acto             la_param;
									Collection<Acto> lca_actos;

									la_param = new Acto();

									la_param.setIdSolicitud(ls_idSolicitud);

									{
										Collection<SolicitudAsociada> lcsa_solicitudesAsociadas;
										SolicitudAsociada             lsa_param;

										lsa_param = new SolicitudAsociada();

										lsa_param.setIdSolicitud(ls_idSolicitud);

										lcsa_solicitudesAsociadas = DaoCreator.getSolicitudAsociadaDAO(adm_manager)
												                                  .findAllByIdSolicitud(
												    lsa_param, true
												);

										if(CollectionUtils.isValidCollection(lcsa_solicitudesAsociadas))
										{
											for(SolicitudAsociada lsa_iterador : lcsa_solicitudesAsociadas)
											{
												if(lsa_iterador != null)
												{
													String ls_idSolicitud1;

													ls_idSolicitud1 = lsa_iterador.getIdSolicitud1();

													if(StringUtils.isValidString(ls_idSolicitud1))
														la_param.setIdSolicitud(ls_idSolicitud1);
												}
											}
										}
									}

									lca_actos = DaoCreator.getActoDAO(adm_manager).findByIdSolicitud(la_param);

									if(CollectionUtils.isValidCollection(lca_actos))
									{
										ConstantesDAO       lc_DAO;
										Map<String, String> lmss_actosLiquidacion;
										Map<String, String> lmss_actosRemate;

										lc_DAO                    = DaoCreator.getConstantesDAO(adm_manager);
										lmss_actosLiquidacion     = new HashMap<String, String>();
										lmss_actosRemate          = new HashMap<String, String>();

										{
											Constantes lc_constante;

											lc_constante = lc_DAO.findById(ConstanteCommon.ACTOS_ADJUDICACION_REMATE);

											if(lc_constante != null)
												lmss_actosRemate = ListadoCodigosConstantes.generarCodigos(
													    lc_constante.getCaracter()
													);
										}

										{
											Constantes lc_constante;

											lc_constante = lc_DAO.findById(
												    ConstanteCommon.ACTOS_ADJUDICACION_LIQUIDACION_JUDICIAL
												);

											if(lc_constante != null)
												lmss_actosLiquidacion = ListadoCodigosConstantes.generarCodigos(
													    lc_constante.getCaracter()
													);
										}

										for(Acto la_iterador : lca_actos)
										{
											if(la_iterador != null)
											{
												String ls_idCodigoActo;

												ls_idCodigoActo = la_iterador.getIdTipoActo();

												if(StringUtils.isValidString(ls_idCodigoActo))
												{
													if(lmss_actosRemate.containsKey(ls_idCodigoActo))
														ll_idMotivo = MotivoTramiteCommon.INSCRITO_ADJUDICACION_REMATE;

													if(lmss_actosLiquidacion.containsKey(ls_idCodigoActo))
														ll_idMotivo = MotivoTramiteCommon.INSCRITO_CON_ADJUDICACION_EN_LIQUIDACION_JUDICIAL;
												}
											}
										}
									}

									{
										Solicitud ls_solicitud;

										ls_solicitud = new Solicitud();

										ls_solicitud.setIdSolicitud(ls_idSolicitud);

										ls_solicitud = DaoCreator.getSolicitudDAO(adm_manager).findById(ls_solicitud);

										if(ls_solicitud != null)
										{
											String ls_idProceso;
											String ls_idSubproceso;

											ls_idProceso        = ls_solicitud.getIdProceso();
											ls_idSubproceso     = ls_solicitud.getIdSubproceso();

											if(
											    StringUtils.isValidString(ls_idProceso)
												    && StringUtils.isValidString(ls_idSubproceso)
												    && ls_idProceso.equalsIgnoreCase("6")
												    && ls_idSubproceso.equalsIgnoreCase("3")
											)
												ll_idMotivo = MotivoTramiteCommon.INSCRITO_CON_ACLARACION;
										}
									}
								}
								else
								{
									if(aotc_tc.isCancelacion())
										ll_idMotivo = MotivoTramiteCommon.CANCELACION_REGISTRO;

									if(lb_isActoSegregacion)
										ll_idMotivo = MotivoTramiteCommon.REGISTRO_DIGITADOR_MASIVO;

									if(lb_inscripcionParcial)
										ll_idMotivo = MotivoTramiteCommon.REALIZAR_REGISTRO_PARCIAL;

									if(aotc_tc.isMedidaCautelar())
										ll_idMotivo = MotivoTramiteCommon.REGISTRO_MEDIDA_CAUTELAR;

									if(lb_baldios)
										ll_idMotivo = MotivoTramiteCommon.INSCRITO_ADJUDICACION_DE_BALDIO;
								}

								{
									com.bachue.snr.prosnr01.dao.acc.AnotacionPredioDAO                laap_anotacionPredioDAO;
									Collection<com.bachue.snr.prosnr01.model.sdb.acc.AnotacionPredio> lcap_anotaciones;

									laap_anotacionPredioDAO     = DaoCreator.getAccAnotacionPredioDAO(adm_manager);
									lcap_anotaciones            = laap_anotacionPredioDAO.findByTurnoHistoria(
										    NumericUtils.getBigDecimal(NumericUtils.getLong(ll_idTurnoHistoria))
										);

									if(CollectionUtils.isValidCollection(lcap_anotaciones))
									{
										boolean                                                         lb_medidasCautelares;
										Iterator<com.bachue.snr.prosnr01.model.sdb.acc.AnotacionPredio> liap_iterator;
										NaturalezaJuridicaDAO                                           lnj_DAO;
										final String                                                    ls_idGrupoNaturalezaJuridica400;

										lb_medidasCautelares                = false;
										liap_iterator                       = lcap_anotaciones.iterator();
										lnj_DAO                             = DaoCreator.getNaturalezaJuridicaDAO(
											    adm_manager
											);
										ls_idGrupoNaturalezaJuridica400     = "0400";

										while(liap_iterator.hasNext() && !lb_medidasCautelares)
										{
											com.bachue.snr.prosnr01.model.sdb.acc.AnotacionPredio lap_anotacion;

											lap_anotacion = liap_iterator.next();

											if(lap_anotacion != null)
											{
												String ls_idNaturalezaJuridica;

												ls_idNaturalezaJuridica = lap_anotacion.getIdNaturalezaJuridica();

												if(StringUtils.isValidString(ls_idNaturalezaJuridica))
												{
													NaturalezaJuridica lnj_naturalezaJuridica;

													lnj_naturalezaJuridica = new NaturalezaJuridica();

													lnj_naturalezaJuridica.setIdNaturalezaJuridica(
													    ls_idNaturalezaJuridica
													);

													lnj_naturalezaJuridica = lnj_DAO.findByIdMaxVersion(
														    lnj_naturalezaJuridica
														);

													if(lnj_naturalezaJuridica != null)
													{
														String ls_idGrupoNaturalezaJuridica;

														ls_idGrupoNaturalezaJuridica = lnj_naturalezaJuridica
																.getIdGrupoNatJur();

														if(StringUtils.isValidString(ls_idGrupoNaturalezaJuridica))
															lb_medidasCautelares = ls_idGrupoNaturalezaJuridica
																	.equalsIgnoreCase(ls_idGrupoNaturalezaJuridica400);
													}
												}
											}
										}

										if(lb_medidasCautelares)
											ll_idMotivo = MotivoTramiteCommon.REGISTRO_MEDIDA_CAUTELAR;
									}
								}

								{
									Collection<com.bachue.snr.prosnr01.model.sdb.acc.AnotacionPredio> lcap_cllAnotacionPredioAcc;

									lcap_cllAnotacionPredioAcc = DaoCreator.getAccAnotacionPredioDAO(adm_manager)
											                                   .findAllByTurnoSolicitudEstadoN(
											    ls_idTurno, ls_idSolicitud
											);

									if(CollectionUtils.isValidCollection(lcap_cllAnotacionPredioAcc))
										ll_idMotivo = MotivoTramiteCommon.INSCRITO_MEDIDAS_CAUTELARES_CON_REGISTRO_PARCIAL;
								}

								if(aotc_tc.isGenerarArchivoNotaExcesoPago())
									ll_idMotivo = MotivoTramiteCommon.REALIZAR_REGISTRO;

								lmt_motivo     = new MotivoTramite();
								lmtd_DAO       = DaoCreator.getMotivoTramiteDAO(adm_manager);

								lmt_motivo.setIdEtapaOrigen(EtapaCommon.ID_ETAPA_CALIFICACION);
								lmt_motivo.setIdMotivo(ll_idMotivo);

								lmt_motivo = lmtd_DAO.findById(lmt_motivo);

								if(lmt_motivo != null)
								{
									TurnoHistoria    lth_turnoHistoria;
									TurnoHistoriaDAO lthd_DAO;

									lth_turnoHistoria     = new TurnoHistoria();
									lthd_DAO              = DaoCreator.getTurnoHistoriaDAO(adm_manager);

									lth_turnoHistoria.setIdTurnoHistoria(ll_idTurnoHistoria);

									lth_turnoHistoria = lthd_DAO.findById(lth_turnoHistoria);

									if(lth_turnoHistoria != null)
									{
										String ls_observaciones;

										ls_observaciones = aotc_tc.getObservaciones();

										lth_turnoHistoria.setEstadoActividad(EstadoCommon.TERMINADA);
										lth_turnoHistoria.setMotivo(lmt_motivo.getDescripcion());
										lth_turnoHistoria.setIdMotivo(
										    NumericUtils.getLongWrapper(lmt_motivo.getIdMotivo())
										);

										if(lb_baldios)
										{
											final String ls_observacionesBaldios = "CREACIÓN DE MATRICULA TEMPORAL "
												+ aotc_tc.getIdCirculo() + "-" + aotc_tc.getIdMatricula()
												+ " PARA ADJUDICACIÓN DE BALDÍO.";

											if(StringUtils.isValidString(ls_observaciones))
												ls_observaciones += (" \n" + ls_observacionesBaldios);
											else
												ls_observaciones = ls_observacionesBaldios;
										}

										lth_turnoHistoria.setObservaciones(ls_observaciones);

										lth_turnoHistoria.setIdUsuarioModificacion(as_userId);
										lth_turnoHistoria.setIpModificacion(as_remoteIp);

										lthd_DAO.insertOrUpdate(lth_turnoHistoria, false);

										DaoCreator.getProcedimientosDAO(adm_manager).spCreateStage(lth_turnoHistoria);
									}
								}

								Collection<AreaPredio> lcap_cap;
								lcap_cap = aotc_tc.getMatriculasSegregadas();

								if(CollectionUtils.isValidCollection(lcap_cap))
								{
									AccPredioRegistroDAO lapr_DAO;
									boolean              lb_b;
									AccPredioRegistro    lapr_pr;

									lapr_DAO     = DaoCreator.getAccPredioRegistroDAO(adm_manager);
									lb_b         = aotc_tc.isEnvioCalificador();

									for(AreaPredio lap_tmp : lcap_cap)
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
				}
			}
		}

		return lba_registroInscripcion;
	}

	/**
	 * Método para insertar interviniente
	 *
	 * @param aorc_idInteviniente
	 *            Objeto con información del interviniente
	 * @throws B2BException
	 */
	public synchronized void gestionPredioCiudadano(
	    RegistroCalificacion aorc_idInteviniente, String as_userId, String as_remoteIp
	)
	    throws B2BException
	{
		DAOManager ldm_manager;

		ldm_manager = DaoManagerFactory.getDAOManager();

		try
		{
			/** insertar y eliminar de predio ciudadano */
			if(aorc_idInteviniente != null)
			{
				com.bachue.snr.prosnr01.dao.acc.AnotacionPredioCiudadanoDAO    lapc_DAO;
				com.bachue.snr.prosnr01.model.sdb.acc.AnotacionPredioCiudadano lapc_anotacion;

				lapc_DAO           = DaoCreator.getAccAnotacionPredioCiudadanoDAO(ldm_manager);
				lapc_anotacion     = new com.bachue.snr.prosnr01.model.sdb.acc.AnotacionPredioCiudadano();

				String ls_idPersona;
				String ls_rol;
				Long   ll_idAnotacion;
				String ls_idAnotacionPredio;

				ls_rol             = StringUtils.getStringNotNull(aorc_idInteviniente.getRolPersona());
				ls_idPersona       = StringUtils.getStringNotNull(aorc_idInteviniente.getIdPersona());
				ll_idAnotacion     = aorc_idInteviniente.getIdAnotacion();
				ls_idAnotacionPredio = StringUtils.getStringNotNull(aorc_idInteviniente.getIdAnotacionPredio());

				lapc_anotacion.setIdAnotacion(NumericUtils.getLong(ll_idAnotacion));
				lapc_anotacion.setIdPersona(ls_idPersona);
				lapc_anotacion.setRolPersona(ls_rol);
				lapc_anotacion.setIdAnotacionPredio(ls_idAnotacionPredio);

				lapc_anotacion = lapc_DAO.findByIdAnotacionPersonaRol(lapc_anotacion);

				if(lapc_anotacion != null)
				{
					Object[] loa_messageArgs = new String[1];
					loa_messageArgs[0] = ls_rol;

					throw new B2BException(ErrorKeys.ERROR_INTERVINIENTE_REPETIDO, loa_messageArgs);
				}

				aorc_idInteviniente.setIdUsuarioCreacion(as_userId);
				aorc_idInteviniente.setIpCreacion(as_remoteIp);

				DaoCreator.getRegistroCalificacionDAO(ldm_manager).insertarIntervinientes(aorc_idInteviniente);
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("deleteInterviniente", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}
	}

	/**
	 * Método para guardar información de digitador masivo
	 *
	 * @param arc_dp
	 *            Objeto para extraer información necesaria para validarla
	 * @param as_userId
	 *            String de usuario en sesión
	 * @param as_remoteIp
	 *            String de IP de usuario en sesión
	 * @return
	 * @throws B2BException
	 */
	public synchronized RegistroCalificacion guardarInfoDigitador(
	    RegistroCalificacion arc_dp, String as_userId, String as_remoteIp
	)
	    throws B2BException
	{
		DAOManager           ldm_manager;
		DireccionDelPredio   lddp_direccionInsercion;
		RegistroCalificacion lorc_resumen;
		Long                 ll_idTurnoHistoria;
		String               ls_idTurno;

		ldm_manager      = DaoManagerFactory.getDAOManager();
		lorc_resumen     = null;

		try
		{
			if(arc_dp != null)
			{
				AccPredioRegistroDAO lapr_DAO;
				String               ls_tipoComplementacion;

				lapr_DAO     = DaoCreator.getAccPredioRegistroDAO(ldm_manager);

				ll_idTurnoHistoria         = arc_dp.getIdTurnoHistoria();
				ls_idTurno                 = arc_dp.getTurno();
				ls_tipoComplementacion     = null;
				/* Guardar informacion completitud */
				{
					ComplementacionCalificacion   lcc_complementacionCalificacion;
					AccPredioRegistro             lapr_pr;
					Collection<AccPredioRegistro> lcapr_pr;

					lapr_pr                             = new AccPredioRegistro();
					lcc_complementacionCalificacion     = arc_dp.getComplementacionCalificacion();

					lapr_pr.setIdTurno(ls_idTurno);

					lcapr_pr = lapr_DAO.findByTurno(lapr_pr, false);

					if(lcc_complementacionCalificacion != null)
					{
						AccComplementacionPredioDAO lacpd_accComplementacionPredioDAO;
						ComplementacionPredioDAO    lcpd_complementacionPredioDAO;
						ComplementacionPredio       lcp_complementacionPredio;

						lacpd_accComplementacionPredioDAO     = DaoCreator.getAccComplementacionPredioDAO(ldm_manager);
						lcpd_complementacionPredioDAO         = DaoCreator.getComplementacionPredioDAO(ldm_manager);
						lcp_complementacionPredio             = lcc_complementacionCalificacion.getComplementacionPredio();
						ls_tipoComplementacion                = lcc_complementacionCalificacion.getTipoComplementacion();

						if(lcp_complementacionPredio != null)
						{
							AccComplementacionPredio lacp_accComplementacionPredio;
							ComplementacionPredio    lcp_bngComplementacionPredio;
							String                   ls_complementacion;

							lacp_accComplementacionPredio     = new AccComplementacionPredio();
							lcp_bngComplementacionPredio      = new ComplementacionPredio();
							ls_complementacion                = lcp_complementacionPredio.getComplementacion();

							if(StringUtils.isValidString(ls_complementacion))
							{
								if(ls_tipoComplementacion.equalsIgnoreCase(TipoComplementacionCommon.NUEVA))
								{
									int li_secuenciaBng;

									li_secuenciaBng = lcpd_complementacionPredioDAO.findSecuence();

									if(li_secuenciaBng > 0)
									{
										lcp_bngComplementacionPredio.setIdComplementacion(
										    String.valueOf(li_secuenciaBng)
										);
										lcp_bngComplementacionPredio.setComplementacion(ls_complementacion);
										lcp_bngComplementacionPredio.setIdUsuarioCreacion(as_userId);
										lcp_bngComplementacionPredio.setIpCreacion(as_remoteIp);

										lcpd_complementacionPredioDAO.insert(lcp_bngComplementacionPredio);

										int li_secuenciaAcc;

										li_secuenciaAcc = lacpd_accComplementacionPredioDAO.findSecuence();

										if(li_secuenciaAcc > 0)
										{
											lacp_accComplementacionPredio.setIdComplementacion(
											    NumericUtils.getLongWrapper(li_secuenciaAcc)
											);
											lacp_accComplementacionPredio.setIdTurnoHistoria(
											    NumericUtils.getLong(ll_idTurnoHistoria)
											);
											lacp_accComplementacionPredio.setIdTurno(ls_idTurno);
											lacp_accComplementacionPredio.setComplementacion(ls_complementacion);
											lacp_accComplementacionPredio.setIdComplementacionOriginal(
											    NumericUtils.getLongWrapper(li_secuenciaBng)
											);
											lacp_accComplementacionPredio.setIdUsuarioCreacion(as_userId);
											lacp_accComplementacionPredio.setIpCreacion(as_remoteIp);

											lacpd_accComplementacionPredioDAO.insert(lacp_accComplementacionPredio);
										}
										else
											throw new B2BException(ErrorKeys.ACC_COMPLEMENTACION_PREDIO_SEQUENCE);
									}
									else
										throw new B2BException(ErrorKeys.ACC_COMPLEMENTACION_PREDIO_SEQUENCE);
								}

								if(CollectionUtils.isValidCollection(lcapr_pr))
								{
									for(AccPredioRegistro lapr_actual : lcapr_pr)
									{
										if(lapr_actual != null)
										{
											lapr_actual.setIdComplementacion(
											    NumericUtils.getLongWrapper(
											        lcp_complementacionPredio.getIdComplementacion()
											    )
											);
											lapr_actual.setIdUsuarioModificacion(as_userId);
											lapr_actual.setIpModificacion(as_remoteIp);

											lapr_DAO.updateById(lapr_actual);
										}
									}
								}
							}
						}
					}

					LinderoRegistroCalificacion llrc_linderoRegistroCalificacion;

					llrc_linderoRegistroCalificacion = arc_dp.getLinderoRegistroCalificacion();

					if(llrc_linderoRegistroCalificacion != null)
					{
						AccLinderoPredioDAO          lalp_DAO;
						Collection<AccLinderoPredio> lcalp_accLinderoPredios;
						String                       ls_lindero;

						lalp_DAO                    = DaoCreator.getAccLinderoPredioDAO(ldm_manager);
						lcalp_accLinderoPredios     = llrc_linderoRegistroCalificacion.getLinderoPredios();
						ls_lindero                  = llrc_linderoRegistroCalificacion.getLindero();

						if(CollectionUtils.isValidCollection(lcalp_accLinderoPredios))
						{
							for(AccLinderoPredio lalp_accLinderoPredio : lcalp_accLinderoPredios)
							{
								String ls_idLinderoPredio;

								ls_idLinderoPredio = lalp_DAO.findMaxIdLindero(lalp_accLinderoPredio, false);

								if(StringUtils.isValidString(ls_idLinderoPredio))
								{
									lalp_accLinderoPredio.setIdLinderoPredio(ls_idLinderoPredio);

									lalp_accLinderoPredio = lalp_DAO.findById(lalp_accLinderoPredio);
								}
								else
								{
									ls_idLinderoPredio = lalp_DAO.findMaxIdLindero(lalp_accLinderoPredio, true);

									lalp_accLinderoPredio.setIdLinderoPredio(ls_idLinderoPredio);

									lalp_accLinderoPredio = lalp_DAO.findById(lalp_accLinderoPredio);
								}

								if(lalp_accLinderoPredio != null)
								{
									lalp_accLinderoPredio.setLindero(ls_lindero);
									lalp_accLinderoPredio.setIdUsuarioModificacion(as_userId);
									lalp_accLinderoPredio.setIpModificacion(as_remoteIp);

									lalp_DAO.updateById(lalp_accLinderoPredio, true);
								}
							}
						}

						if(CollectionUtils.isValidCollection(lcapr_pr))
						{
							for(AccPredioRegistro lapr_actual : lcapr_pr)
							{
								if(lapr_actual != null)
								{
									AccLinderoPredio lalp_lp;
									String           ls_idLinderoPredio;

									lalp_lp = new AccLinderoPredio();

									lalp_lp.setIdMatricula(lapr_actual.getIdMatricula());
									lalp_lp.setIdCirculo(lapr_actual.getIdCirculo());
									lalp_lp.setIdTurno(ls_idTurno);

									ls_idLinderoPredio = lalp_DAO.findMaxIdLindero(lalp_lp, false);

									if(StringUtils.isValidString(ls_idLinderoPredio))
									{
										lalp_lp.setIdLinderoPredio(ls_idLinderoPredio);

										lalp_lp = lalp_DAO.findById(lalp_lp);
									}
									else
									{
										ls_idLinderoPredio = lalp_DAO.findMaxIdLindero(lalp_lp, true);

										lalp_lp.setIdLinderoPredio(ls_idLinderoPredio);

										lalp_lp = lalp_DAO.findById(lalp_lp);
									}

									lalp_lp = lalp_DAO.findByCirculoMatriculaTurno(lalp_lp);

									if(lalp_lp != null)
									{
										lalp_lp.setLindero(ls_lindero);
										lalp_lp.setIpModificacion(as_remoteIp);
										lalp_lp.setIdUsuarioModificacion(as_userId);

										lalp_DAO.updateById(lalp_lp, true);
									}
								}
							}
						}
					}

					/* Guardar informacion tab direccion predio */
					lddp_direccionInsercion = arc_dp.getDataDireccionPredio();

					DireccionPredioAcc ldpa_direccionPredio;
					ldpa_direccionPredio = lddp_direccionInsercion.getDireccionPredio();

					DireccionPredioAccDAO ldpDAO_accDireccion;
					ldpDAO_accDireccion = DaoCreator.getDireccionPredioAccDAO(ldm_manager);

					if(ldpa_direccionPredio == null)
						throw new B2BException(ErrorKeys.ERROR_CARGUE_DIRECCION_PREDIO);
					else
					{
						String ls_idDireccion;

						ls_idDireccion = ldpa_direccionPredio.getIdDireccion();

						if(StringUtils.isValidString(ls_idDireccion) && ls_idDireccion.equalsIgnoreCase("0"))
						{
							int li_secDireccion;
							li_secDireccion = ldpDAO_accDireccion.findMaxIdDireccion(ldpa_direccionPredio);

							if(li_secDireccion == 0)
								li_secDireccion = 1;
							else
								li_secDireccion++;

							ldpa_direccionPredio.setIdDireccion(StringUtils.getString(li_secDireccion));
							ldpa_direccionPredio.getComplementoDireccion();
							ldpa_direccionPredio.setIdUsuarioModificacion(as_userId);
							ldpa_direccionPredio.setIpModificacion(as_remoteIp);
							ldpa_direccionPredio.setIdTurnoHistoria(ll_idTurnoHistoria);

							/* Modificacion complemento direccion a matriculas hijas y matriz */
							ldpDAO_accDireccion.updateById(ldpa_direccionPredio, false);
						}
					}
				}
				// consulta a la tabla SDB_ACC_PREDIO_REGISTRO con el turno y actualiza el
				// id_complementacion_original cuando Tipo Complementacion es nueva
				{
					Collection<AreaPredio> acap_ap;

					acap_ap = lapr_DAO.findMatriculasInfByTurno(ls_idTurno);

					if(CollectionUtils.isValidCollection(acap_ap))
					{
						lorc_resumen = new RegistroCalificacion();

						lorc_resumen.setMatriculasInformacion(acap_ap);

						if(
						    StringUtils.isValidString(ls_tipoComplementacion)
							    && ls_tipoComplementacion.equalsIgnoreCase(TipoComplementacionCommon.NUEVA)
						)
						{
							AccComplementacionPredio lacp_cp;

							lacp_cp = new AccComplementacionPredio();

							lacp_cp.setIdTurno(ls_idTurno);

							lacp_cp = DaoCreator.getAccComplementacionPredioDAO(ldm_manager).findByIdTurno(lacp_cp);

							if(lacp_cp != null)
							{
								Long ll_ComplementacionOriginal;

								ll_ComplementacionOriginal = lacp_cp.getIdComplementacionOriginal();

								for(AreaPredio lap_actual : acap_ap)
								{
									if(lap_actual != null)
									{
										AccPredioRegistro lapr_predio;

										lapr_predio = new AccPredioRegistro();

										lapr_predio.setIdMatricula(
										    NumericUtils.getLongWrapper(lap_actual.getIdMatricula())
										);
										lapr_predio.setIdCirculo(lap_actual.getIdCirculo());

										lapr_predio = lapr_DAO.findByCirculoMatricula(lapr_predio);

										if(lapr_predio != null)
										{
											lapr_predio.setIdComplementacion(ll_ComplementacionOriginal);
											lapr_predio.setIdUsuarioModificacion(as_userId);
											lapr_predio.setIpModificacion(as_remoteIp);

											lapr_DAO.updateById(lapr_predio);
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

			clh_LOGGER.error("guardarInfoDigitador", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lorc_resumen;
	}

	/**
	 * Método para guardar información de los datos en registro
	 *
	 * @param arc_dp
	 *            Objeto para extraer la información y guardarla en la base de datos
	 * @param as_userId
	 *            String de usuario en sesión
	 * @param as_remoteIp
	 *            String de Ip del usuario en sesión
	 * @return
	 * @throws B2BException
	 */
	public synchronized RegistroCalificacion guardarInfoRegistro(
	    RegistroCalificacion arc_dp, String as_userId, String as_remoteIp
	)
	    throws B2BException
	{
		DAOManager           ldm_manager;
		RegistroCalificacion lorc_resumen;

		ldm_manager      = DaoManagerFactory.getDAOManager();
		lorc_resumen     = new RegistroCalificacion();

		try
		{
			if(arc_dp != null)
			{
				Long          ll_idTurnoHistoria;
				String        ls_idTurno;
				TurnoHistoria lth_loth;
				String        ls_solicitud;
				boolean       lb_devolucion;
				boolean       lb_salvarTotal;
				String        ls_idProceso;
				String        ls_idCirculo;
				Long          ll_idMatricula;
				boolean       lb_matriculaReloteo;

				lb_devolucion          = arc_dp.isDevolucion();
				ll_idTurnoHistoria     = arc_dp.getIdTurnoHistoria();
				ls_idTurno             = arc_dp.getTurno();
				ls_idProceso           = arc_dp.getProcesoAValidar();
				lb_salvarTotal         = arc_dp.isSalvarDefinitivo();
				lth_loth               = new TurnoHistoria();
				ls_solicitud           = null;
				ls_idCirculo           = arc_dp.getIdCirculoMatriz();
				ll_idMatricula         = null;

				if(StringUtils.isValidString(ls_idCirculo) && ls_idCirculo.contains("-"))
				{
					String[] las_as;
					las_as     = ls_idCirculo.split("-");

					ls_idCirculo       = las_as[0];
					ll_idMatricula     = NumericUtils.getLongWrapper(las_as[1]);
				}

				lb_matriculaReloteo = NumericUtils.isValidLong(ll_idMatricula);

				lth_loth.setIdTurnoHistoria(ll_idTurnoHistoria);

				lth_loth = DaoCreator.getTurnoHistoriaDAO(ldm_manager).findById(lth_loth);

				if(!lb_salvarTotal)
				{
					if(lth_loth != null)
						ls_solicitud = lth_loth.getIdSolicitud();

					/* Guardar informacion tab anotaciones */
					if(ls_idProceso.equalsIgnoreCase(IdentificadoresCommon.ID_T_ANOTACIONES))
					{
						/* insertar registro en acc anotacion predio y acc anotacion predio ciudadano */
						RegistroCalificacion lorc_tmp;

						lorc_tmp = arc_dp.getDatosDocumento();

						if(lorc_tmp != null)
						{
							Collection<RegistroCalificacion> lcrc_tmp;

							lcrc_tmp = lorc_tmp.getAllMatriculas();

							if(CollectionUtils.isValidCollection(lcrc_tmp))
							{
								com.bachue.snr.prosnr01.dao.acc.AnotacionPredioCiudadanoDAO loaapc_DAO;
								com.bachue.snr.prosnr01.dao.acc.AnotacionPredioDAO          lapd_DAO;
								com.bachue.snr.prosnr01.model.sdb.acc.AnotacionPredio       loap_ap;

								loap_ap        = new com.bachue.snr.prosnr01.model.sdb.acc.AnotacionPredio();
								lapd_DAO       = DaoCreator.getAccAnotacionPredioDAO(ldm_manager);
								loaapc_DAO     = DaoCreator.getAccAnotacionPredioCiudadanoDAO(ldm_manager);

								if(
								    StringUtils.isValidString(ls_idCirculo) && NumericUtils.isValidLong(ll_idMatricula)
									    && StringUtils.isValidString(ls_idTurno)
								)
								{
									com.bachue.snr.prosnr01.model.sdb.acc.AnotacionPredio             lap_ap;
									Collection<com.bachue.snr.prosnr01.model.sdb.acc.AnotacionPredio> lcap_ap;

									lap_ap = new com.bachue.snr.prosnr01.model.sdb.acc.AnotacionPredio();
									lap_ap.setIdCirculo(ls_idCirculo);
									lap_ap.setIdMatricula(ll_idMatricula);
									lap_ap.setIdTurno(ls_idTurno);

									lcap_ap = lapd_DAO.findByTurnoMatricula(lap_ap);

									if(CollectionUtils.isValidCollection(lcap_ap))
									{
										for(com.bachue.snr.prosnr01.model.sdb.acc.AnotacionPredio lap_tmp : lcap_ap)
										{
											if(lap_tmp != null)
											{
												com.bachue.snr.prosnr01.model.sdb.acc.AnotacionPredioCiudadano lapc_apc;
												lapc_apc = new com.bachue.snr.prosnr01.model.sdb.acc.AnotacionPredioCiudadano();

												lapc_apc.setIdAnotacionPredio(lap_tmp.getIdAnotacionPredio());

												loaapc_DAO.deleteIntervinientes(lapc_apc);

												lapd_DAO.deleteById(lap_tmp);
											}
										}
									}
								}

								for(RegistroCalificacion lrc_tmp : lcrc_tmp)
								{
									if(lrc_tmp != null)
									{
										if(lrc_tmp.isRevision())
										{
											if(!lb_devolucion)
											{
												AnotacionPredioCiudadano                              loapc_apc;
												Collection<AnotacionPredioCiudadano>                  lcapc_apc;
												com.bachue.snr.prosnr01.model.sdb.acc.AnotacionPredio loap_identity;
												String                                                ls_circulo;
												Long                                                  ll_matricula;

												loapc_apc        = new AnotacionPredioCiudadano();
												lcapc_apc        = new ArrayList<AnotacionPredioCiudadano>();
												ls_circulo       = lrc_tmp.getIdCirculo();
												ll_matricula     = lrc_tmp.getIdMatricula();

												if(lb_matriculaReloteo)
												{
													ls_circulo       = ls_idCirculo;
													ll_matricula     = ll_idMatricula;

													loapc_apc.setIdCirculo(ls_idCirculo);
													loapc_apc.setIdMatricula(NumericUtils.getLong(ll_idMatricula));
												}
												else
												{
													loapc_apc.setIdCirculo(loap_ap.getIdCirculo());
													loapc_apc.setIdMatricula(
													    NumericUtils.getLong(loap_ap.getIdMatricula())
													);
												}

												loapc_apc.setIdCirculo(ls_circulo);
												loapc_apc.setIdMatricula(NumericUtils.getLong(ll_matricula));
												loapc_apc.setIdAnotacion(
												    NumericUtils.getLong(lrc_tmp.getIdAnotacion())
												);

												lcapc_apc = DaoCreator.getAnotacionPredioCiudadanoDAO(ldm_manager)
														                  .consultaPredioCiudadanos(loapc_apc);

												loap_ap.setIdTurnoHistoria(NumericUtils.getLong(ll_idTurnoHistoria));
												loap_ap.setIdEstadoRegistro(EstadoCommon.INACTIVO);
												loap_ap.setIdCirculo(ls_circulo);
												loap_ap.setIdMatricula(ll_matricula);

												if(lb_matriculaReloteo)
												{
													loap_ap.setIdCirculo(ls_idCirculo);
													loap_ap.setIdMatricula(ll_idMatricula);
												}
												else
												{
													loap_ap.setIdCirculo(lrc_tmp.getIdCirculo());
													loap_ap.setIdMatricula(lrc_tmp.getIdMatricula());
												}

												loap_ap.setIdSolicitud(ls_solicitud);
												loap_ap.setIdAnotacion(lrc_tmp.getIdAnotacion());
												loap_ap.setFechaRegistro(lrc_tmp.getFechaRegistro());
												loap_ap.setIdDocumento(lrc_tmp.getIdDocumento());
												loap_ap.setIdNaturalezaJuridica(lrc_tmp.getCodActo());
												loap_ap.setVersion(1);
												loap_ap.setVersionDocumento(1);
												loap_ap.setIdTipoAnotacion(lrc_tmp.getIdTipoAnotacion());
												loap_ap.setFechaRadicacion(lrc_tmp.getFechaRadicacion());
												loap_ap.setRadicacion(lrc_tmp.getRadicacion());
												loap_ap.setIdEstadoAnotacion(lrc_tmp.getCodEstadoAnotacion());
												loap_ap.setIdUsuarioCreacion(as_userId);
												loap_ap.setIpCreacion(as_remoteIp);
												loap_ap.setIdTurno(ls_idTurno);
												loap_ap.setActivo(EstadoCommon.N);

												loap_identity = lapd_DAO.insert(loap_ap);

												if(
												    CollectionUtils.isValidCollection(lcapc_apc)
													    && (loap_identity != null)
												)
												{
													String ls_idAnotacionPredio;

													ls_idAnotacionPredio = loap_identity.getIdAnotacionPredio();

													if(StringUtils.isValidString(ls_idAnotacionPredio))
													{
														for(AnotacionPredioCiudadano lopc_apc : lcapc_apc)
														{
															if(lopc_apc != null)
															{
																com.bachue.snr.prosnr01.model.sdb.acc.AnotacionPredioCiudadano loapc_tmp;

																loapc_tmp = new com.bachue.snr.prosnr01.model.sdb.acc.AnotacionPredioCiudadano();

																if(lb_matriculaReloteo)
																{
																	loapc_tmp.setIdCirculo(ls_idCirculo);
																	loapc_tmp.setIdMatricula(
																	    NumericUtils.getLong(ll_idMatricula)
																	);
																}
																else
																{
																	loapc_tmp.setIdCirculo(lopc_apc.getIdCirculo());
																	loapc_tmp.setIdMatricula(lopc_apc.getIdMatricula());
																}

																loapc_tmp.setIdAnotacion(lopc_apc.getIdAnotacion());
																loapc_tmp.setIdPersona(lopc_apc.getIdPersona());
																loapc_tmp.setRolPersona(lopc_apc.getRolPersona());
																loapc_tmp.setPropietario(lopc_apc.getPropietario());
																loapc_tmp.setPorcentajeParticipacion(
																    lopc_apc.getPorcentajeParticipacion()
																);
																loapc_tmp.setIdInteresadaRrr(
																    lopc_apc.getIdInteresadaRrr()
																);
																loapc_tmp.setIdUsuarioCreacion(as_userId);
																loapc_tmp.setIpCreacion(as_remoteIp);
																loapc_tmp.setIdTurno(ls_idTurno);
																loapc_tmp.setIdAnotacionPredio(ls_idAnotacionPredio);

																loaapc_DAO.insert(loapc_tmp);
															}
														}
													}
												}

												loap_ap     = new com.bachue.snr.prosnr01.model.sdb.acc.AnotacionPredio();
												lrc_tmp     = new RegistroCalificacion();
											}
										}
										else if(lb_devolucion)
										{
											com.bachue.snr.prosnr01.model.sdb.acc.AnotacionPredio          loap_tmp;
											com.bachue.snr.prosnr01.model.sdb.acc.AnotacionPredioCiudadano loapc_tmp;
											loap_tmp      = new com.bachue.snr.prosnr01.model.sdb.acc.AnotacionPredio();
											loapc_tmp     = new com.bachue.snr.prosnr01.model.sdb.acc.AnotacionPredioCiudadano();

											loap_tmp.setIdAnotacionPredio(loap_ap.getIdAnotacionPredio());
											loapc_tmp.setIdAnotacionPredio(loap_ap.getIdAnotacionPredio());

											lapd_DAO.deleteById(loap_tmp);
											loaapc_DAO.deleteIntervinientes(loapc_tmp);
										}
									}
								}
							}
						}
					}

					/* Guardar informacion completitud */
					else if(ls_idProceso.equalsIgnoreCase(IdentificadoresCommon.ID_T_COMPLEMENTACION))
					{
						ComplementacionCalificacion lcc_complementacionCalificacion;
						LinderoRegistroCalificacion llrc_linderoRegistroCalificacion;
						String                      ls_observacionesComplementacion;
						String                      ls_observacionesLindero;

						lcc_complementacionCalificacion      = salvarComplementacion(
							    arc_dp, as_userId, as_remoteIp, ldm_manager, false
							);
						llrc_linderoRegistroCalificacion     = salvarLindero(
							    arc_dp, as_userId, as_remoteIp, ldm_manager
							);
						ls_observacionesComplementacion      = null;
						ls_observacionesLindero              = null;

						if(lcc_complementacionCalificacion != null)
							ls_observacionesComplementacion = lcc_complementacionCalificacion.getObservaciones();

						if(llrc_linderoRegistroCalificacion != null)
							ls_observacionesLindero = llrc_linderoRegistroCalificacion.getObservaciones();

						if(
						    StringUtils.isValidString(ls_observacionesLindero)
							    || StringUtils.isValidString(ls_observacionesComplementacion)
						)
						{
							StringBuilder lsb_observaciones;
							String        ls_observaciones;

							lsb_observaciones = new StringBuilder();

							if(StringUtils.isValidString(ls_observacionesLindero))
								lsb_observaciones.append(ls_observacionesLindero + "\n");

							if(StringUtils.isValidString(ls_observacionesComplementacion))
								lsb_observaciones.append(ls_observacionesComplementacion + "\n");

							ls_observaciones = lsb_observaciones.toString();

							if(StringUtils.isValidString(ls_observaciones))
							{
								TurnoHistoria    lth_turnoHistoria;
								TurnoHistoriaDAO lthDAO_turnoHistoriaDAO;

								lth_turnoHistoria           = new TurnoHistoria();
								lthDAO_turnoHistoriaDAO     = DaoCreator.getTurnoHistoriaDAO(ldm_manager);

								lth_turnoHistoria.setIdTurnoHistoria(arc_dp.getIdTurnoHistoria());

								lth_turnoHistoria = lthDAO_turnoHistoriaDAO.findById(lth_turnoHistoria);

								if(lth_turnoHistoria != null)
								{
									if(ls_observaciones.length() > 400)
										ls_observaciones = ls_observaciones.substring(0, 393);

									lth_turnoHistoria.setObservacionesNoTramite(ls_observaciones);
									lth_turnoHistoria.setIdUsuarioModificacion(as_userId);
									lth_turnoHistoria.setIpModificacion(as_remoteIp);

									lthDAO_turnoHistoriaDAO.insertOrUpdate(lth_turnoHistoria, false);
								}
							}
						}
					}
					else if(ls_idProceso.equalsIgnoreCase(IdentificadoresCommon.ID_T_DIRECCION_PREDIO_RC))
					{
						/* Guardar informacion tab direccion predio */
						DireccionPredioAcc    ldpa_direccionPredio;
						DireccionDelPredio    lddp_direccionInsercion;
						DireccionPredioAccDAO ldpDAO_accDireccion;

						ldpDAO_accDireccion         = DaoCreator.getDireccionPredioAccDAO(ldm_manager);
						lddp_direccionInsercion     = arc_dp.getDataDireccionPredio();
						ldpa_direccionPredio        = lddp_direccionInsercion.getDireccionPredio();

						if(ldpa_direccionPredio == null)
							throw new B2BException(ErrorKeys.ERROR_CARGUE_DIRECCION_PREDIO);

						Collection<DireccionPredioAcc> lcdpa_cdpa;
						lcdpa_cdpa = ldpDAO_accDireccion.findByIdCirculoMatricula(ldpa_direccionPredio);

						int li_secDireccion;
						li_secDireccion = NumericUtils.getInt(ldpa_direccionPredio.getIdDireccion());

						if(li_secDireccion == 0)
						{
							li_secDireccion = 1;

							if(CollectionUtils.isValidCollection(lcdpa_cdpa))
								li_secDireccion += lcdpa_cdpa.size();
						}

						ldpa_direccionPredio.setIdDireccion(StringUtils.getString(li_secDireccion));

						int li_secuencia;

						li_secuencia = ldpDAO_accDireccion.findSecuence();

						if(li_secuencia <= 0)
							throw new B2BException(ErrorKeys.SIN_SECUENCIA_DIRECCION_PREDIO);

						ldpa_direccionPredio.setIdDireccionPredio(StringUtils.getString(li_secuencia));
						ldpa_direccionPredio.setIdUsuarioCreacion(as_userId);
						ldpa_direccionPredio.setIpCreacion(as_remoteIp);
						ldpa_direccionPredio.setIdTurnoHistoria(arc_dp.getIdTurnoHistoria());

						ldpDAO_accDireccion.insert(ldpa_direccionPredio);
					}
				}

				/* Ejecucucion procedimiento */
				else
					lorc_resumen = procMatriculasInformacion(
						    lorc_resumen, ls_idTurno, ll_idTurnoHistoria, lb_devolucion, false, false, as_userId,
						    as_remoteIp, ldm_manager
						);
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("guardarInfoRegistro", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lorc_resumen;
	}

	/**
	 * Método encargado de guardar la información de medidas cautelares
	 *
	 * @param as_idTurno String con identificador de turno
	 * @param as_tipoProceso String con tipo de proceso
	 * @param as_numeroProceso String con número de proceso
	 * @param aoo_oficinaOrigen Objeto OficinaOrigen contenedor de la iformación a guardar
	 * @param as_userId de as user id
	 * @param as_remoteIp de as remote ip
	 * @throws B2BException de b 2 B exception
	 */
	public synchronized void guardarInformacionMedidaCautelar(
	    String as_idTurno, String as_tipoProceso, String as_numeroProceso, OficinaOrigen aoo_oficinaOrigen,
	    String as_userId, String as_remoteIp
	)
	    throws B2BException
	{
		DAOManager ldm_manager;

		ldm_manager = DaoManagerFactory.getDAOManager();

		try
		{
			if(aoo_oficinaOrigen != null)
			{
				aoo_oficinaOrigen.setIdUsuarioModificacion(as_userId);
				aoo_oficinaOrigen.setIpModificacion(as_remoteIp);

				DaoCreator.getDocumentosSalidaDAO(ldm_manager)
					          .updateOficinaOrigenByTurno(aoo_oficinaOrigen, as_idTurno, true);

				guardarTipoNumeroProceso(
				    as_idTurno, as_tipoProceso, as_numeroProceso, as_userId, as_remoteIp, ldm_manager
				);
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("guardarInformacionMedidaCautelar", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}
	}

	/**
	 * Método encargado de guardar la información de notificacion
	 *
	 * @param apd_direccion Objeto PersonaDireccion con la informacion de la persona direccion a guardar.
	 * @param ar_registro Objeto Registro con la informacion de registro a guardar.
	 * @param as_userId de as user id
	 * @param as_remoteIp de as remote ip
	 * @throws B2BException de b 2 B exception
	 */
	public void guardarInformacionNotificacion(
	    PersonaDireccion apd_direccion, Registro ar_registro, String as_userId, String as_remoteIp
	)
	    throws B2BException
	{
		DAOManager ldm_manager;

		ldm_manager = DaoManagerFactory.getDAOManager();

		try
		{
			if((apd_direccion != null) && (ar_registro != null))
			{
				Solicitud                ls_solicitud;
				PersonaTelefono          lpt_telFi;
				PersonaTelefono          lpt_telMo;
				PersonaCorreoElectronico lpc_correo;

				ls_solicitud     = ar_registro.getSolicitud();
				lpc_correo       = ar_registro.getPersonaCorreoElectronico();
				lpt_telFi        = ar_registro.getTelefonoFijo();
				lpt_telMo        = ar_registro.getTelefonoMovil();

				ls_solicitud = DaoCreator.getSolicitudDAO(ldm_manager).findById(ls_solicitud);

				if(ls_solicitud == null)
					throw new B2BException(ErrorKeys.ERROR_NO_ID_SOLICITUD);

				if((lpt_telFi != null) && (lpt_telMo != null) && (lpc_correo != null))
				{
					String ls_numFijo;
					String ls_numMovil;
					String ls_correoElec;

					ls_numFijo        = lpt_telFi.getTelefono();
					ls_numMovil       = lpt_telMo.getTelefono();
					ls_correoElec     = lpc_correo.getCorreoElectronico();

					if(
					    !(StringUtils.isValidString(ls_numFijo) || StringUtils.isValidString(ls_numMovil)
						    || StringUtils.isValidString(ls_correoElec))
					)
						throw new B2BException(ErrorKeys.ERROR_DATOS_CONTACTO);
					else
					{
						if(StringUtils.isValidString(ls_numFijo))
							validarTelefono(ldm_manager, lpt_telFi, true);

						if(StringUtils.isValidString(ls_numMovil))
							validarTelefono(ldm_manager, lpt_telMo, false);

						if(
						    StringUtils.isValidString(ls_correoElec)
							    && !ExpresionRegular.getExpresionRegular().esCorreoElectronico(ls_correoElec)
						)
							throw new B2BException(ErrorKeys.DEBE_DIGITAR_CORREO_ELECTRONICO_VALIDO);
					}
				}

				PersonaDireccionDAO lpdd_DAO;
				boolean             lb_crearDireccion;
				String              ls_idPersona;

				lpdd_DAO              = DaoCreator.getPersonaDireccionDAO(ldm_manager);
				lb_crearDireccion     = true;
				ls_idPersona          = apd_direccion.getIdPersona();

				if(!StringUtils.isValidString(ls_idPersona))
				{
					ls_idPersona = ls_solicitud.getIdPersona();
					apd_direccion.setIdPersona(ls_idPersona);
				}

				{
					long ll_max;

					lb_crearDireccion = true;
					apd_direccion.setFechaDesde(new Date());
					apd_direccion.setIdUsuarioCreacion(as_userId);
					apd_direccion.setIpCreacion(as_remoteIp);

					ll_max = lpdd_DAO.insertOrUpdate(apd_direccion, lb_crearDireccion);

					if(ll_max > 0)
						apd_direccion.setIdDireccion(StringUtils.getString(ll_max));
					else
						throw new B2BException(ErrorKeys.NO_SE_GENERO_ID_DIRECCION);
				}

				if((lpt_telFi != null) && StringUtils.isValidString(lpt_telFi.getTelefono()))
				{
					PersonaTelefonoDAO lptd_DAO;
					boolean            lb_crearTelefono;

					lptd_DAO             = DaoCreator.getPersonaTelefonoDAO(ldm_manager);
					lb_crearTelefono     = false;

					{
						long ll_max;

						lb_crearTelefono = true;
						lpt_telFi.setIdPersona(ls_idPersona);
						lpt_telFi.setTipoTelefono(EstadoCommon.F);
						lpt_telFi.setFechaDesde(new Date());
						lpt_telFi.setIdUsuarioCreacion(as_userId);
						lpt_telFi.setIpCreacion(as_remoteIp);

						ll_max = lptd_DAO.insertOrUpdate(lpt_telFi, lb_crearTelefono);

						if(ll_max > 0)
							lpt_telFi.setIdTelefono(StringUtils.getString(ll_max));
						else
							throw new B2BException(ErrorKeys.NO_SE_GENERO_ID_TEL);
					}
				}

				{
					if((lpt_telMo != null) && StringUtils.isValidString(lpt_telMo.getTelefono()))
					{
						PersonaTelefonoDAO lptd_DAO;
						boolean            lb_crearTelefono;

						lptd_DAO             = DaoCreator.getPersonaTelefonoDAO(ldm_manager);
						lb_crearTelefono     = true;

						{
							long ll_max;

							lb_crearTelefono = true;
							lpt_telMo.setIdPersona(ls_idPersona);
							lpt_telMo.setTipoTelefono(EstadoCommon.M);
							lpt_telMo.setFechaDesde(new Date());
							lpt_telMo.setIdUsuarioCreacion(as_userId);
							lpt_telMo.setIpCreacion(as_remoteIp);

							ll_max = lptd_DAO.insertOrUpdate(lpt_telMo, lb_crearTelefono);

							if(ll_max > 0)
								lpt_telMo.setIdTelefono(StringUtils.getString(ll_max));
							else
								throw new B2BException(ErrorKeys.NO_SE_GENERO_ID_TEL);
						}
					}

					{
						if((lpc_correo != null) && StringUtils.isValidString(lpc_correo.getCorreoElectronico()))
						{
							PersonaCorreoElectronicoDAO lpced_DAO;
							boolean                     lb_crearCorreo;

							lpced_DAO          = DaoCreator.getPersonaCorreoElectronicoDAO(ldm_manager);
							lb_crearCorreo     = false;

							{
								long ll_max;

								lb_crearCorreo = true;
								lpc_correo.setIdPersona(ls_idPersona);
								lpc_correo.setFechaDesde(new Date());
								lpc_correo.setIdUsuarioCreacion(as_userId);
								lpc_correo.setIpCreacion(as_remoteIp);

								ll_max = lpced_DAO.insertOrUpdate(lpc_correo, lb_crearCorreo);

								if(ll_max > 0)
									lpc_correo.setIdCorreoElectronico(StringUtils.getString(ll_max));
								else
									throw new B2BException(ErrorKeys.NO_SE_GENERO_ID_TEL);
							}
						}
					}

					ls_solicitud.setIdDireccion(apd_direccion.getIdDireccion());
					ls_solicitud.setIdTelefono(lpt_telMo.getIdTelefono());
					ls_solicitud.setIdCorreoElectronico(lpc_correo.getIdCorreoElectronico());
					ls_solicitud.setIpModificacion(as_remoteIp);
					ls_solicitud.setIdUsuarioModificacion(as_userId);

					DaoCreator.getSolicitudDAO(ldm_manager).insertOrUpdate(ls_solicitud, false);
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("guardarInformacionNotificacion", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}
	}

	/**
	 * Guardar solicitudes apoyo regional orip.
	 *
	 * @param asanui_param de arc data
	 * @param as_userId de as user id
	 * @param as_remoteIp de as remote ip
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public synchronized void guardarSolicitudesApoyoRegionalOrip(
	    SolicitudApoyoNacionalUI asanui_param, String as_userId, String as_remoteIp
	)
	    throws B2BException
	{
		if(asanui_param != null)
		{
			DAOManager ldm_manager;

			ldm_manager = DaoManagerFactory.getDAOManager();

			try
			{
				Collection<SolicitudApoyoRegionalOrip> lcsaro_solicitudesApoyoRegionalOrip;
				TurnoHistoria                          lth_turnoHistoria;
				int                                    li_contador;

				li_contador                             = 0;
				lcsaro_solicitudesApoyoRegionalOrip     = asanui_param.getSolicitudesRegionalOrip();
				lth_turnoHistoria                       = DaoCreator.getTurnoHistoriaDAO(ldm_manager)
						                                                .findById(
						    NumericUtils.getLongWrapper(asanui_param.getIdTurnoHistoria())
						);

				if(
				    CollectionUtils.isValidCollection(lcsaro_solicitudesApoyoRegionalOrip)
					    && (lth_turnoHistoria != null)
				)
				{
					Solicitud ls_solicitud;
					ls_solicitud = DaoCreator.getSolicitudDAO(ldm_manager).findById(lth_turnoHistoria.getIdSolicitud());

					if(ls_solicitud != null)
					{
						String           ls_idSolicitud;
						Collection<Long> lcl_orden;

						ls_idSolicitud     = ls_solicitud.getIdSolicitud();
						lcl_orden          = null;

						for(SolicitudApoyoRegionalOrip lsaro_tmp : lcsaro_solicitudesApoyoRegionalOrip)
						{
							if(lsaro_tmp != null)
							{
								{
									Long ll_orden;
									Long ll_ordenMaxima;

									ll_orden           = NumericUtils.getLongWrapper(lsaro_tmp.getOrden());
									ll_ordenMaxima     = NumericUtils.getLongWrapper(
										    lcsaro_solicitudesApoyoRegionalOrip.size()
										);

									if(ll_orden.compareTo(ll_ordenMaxima) > 0L)
									{
										Object[] loa_messageArgs;

										loa_messageArgs        = new String[2];
										loa_messageArgs[0]     = StringUtils.getString(ll_orden);
										loa_messageArgs[1]     = StringUtils.getString(ll_ordenMaxima);

										throw new B2BException(
										    ErrorKeys.ERROR_ORDEN_SUPERIOR_AL_PERMITIDO, loa_messageArgs
										);
									}

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

										throw new B2BException(ErrorKeys.ERROR_ORDEN_REPETIDO, loa_messageArgs);
									}

									lcl_orden.add(ll_orden);
								}

								String ls_habilitar;
								String ls_parametrizacionCalificadores;

								ls_habilitar                        = StringUtils.getStringNotNull(
									    lsaro_tmp.getHabilitar()
									);
								ls_parametrizacionCalificadores     = StringUtils.getStringNotNull(
									    lsaro_tmp.getParametrizacionCalificadores()
									);

								if(ls_habilitar.equalsIgnoreCase(IdentificadoresCommon.S))
								{
									li_contador = li_contador + 1;

									if(!ls_parametrizacionCalificadores.equalsIgnoreCase(IdentificadoresCommon.S))
										throw new B2BException(
										    ErrorKeys.ERROR_HABILITAR_CALIFICADOR_LINEA_APOYO_NACIONAL
										);
								}

								lsaro_tmp.setIdSolicitud(ls_idSolicitud);

								Collection<Usuario> lcu_usuarios;
								lcu_usuarios = DaoCreator.getUsuarioDAO(ldm_manager)
										                     .findAllUsersByCirculoRol(
										    lsaro_tmp.getIdCirculoRegional(), RolCommon.CB_ROL_REGISTRADOR, false
										);

								if(CollectionUtils.isValidCollection(lcu_usuarios))
								{
									Iterator<Usuario> liu_iterator;
									liu_iterator = lcu_usuarios.iterator();

									if(liu_iterator.hasNext())
									{
										Usuario lu_usuario;
										lu_usuario = liu_iterator.next();

										if(lu_usuario != null)
											lsaro_tmp.setCorreoElectronico(
											    lu_usuario.getCorreoElectronicoCorporativo()
											);
									}
								}

								lsaro_tmp.setIdUsuarioCreacion(as_userId);
								lsaro_tmp.setIpCreacion(as_remoteIp);

								DaoCreator.getSolicitudApoyoRegionalOripDAO(ldm_manager).insertOrUpdate(
								    lsaro_tmp, true
								);
							}
						}
					}
				}

				if(li_contador <= 0)
					throw new B2BException(ErrorKeys.ERROR_HABILITAR_AL_MENOS_UNA_ORIP);
			}
			catch(B2BException lb2be_e)
			{
				ldm_manager.setRollbackOnly();

				clh_LOGGER.error("guardarSolicitudesApoyoRegionalOrip", lb2be_e);

				throw lb2be_e;
			}
			finally
			{
				ldm_manager.close();
			}
		}
	}

	/**
	 * Actualiza los cambios en pantalla que tenga el campo tipo proceso y numero
	 * proceso de un acto de grupo de naturaleza juridica 0400.
	 *
	 * @param as_idTurno            numero de turno del caso
	 * @param as_tipoProceso            datos nuevos para el campo de tipo proceso
	 * @param as_numeroProceso            datos nuevos para el campo de numero proceso
	 * @param as_userId            Id del usuario que ejecuta el procedimiento
	 * @param as_remoteIpAddress            Dirección IP del cliente que ejecuta el procedimiento
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public synchronized void guardarTipoNumeroProceso(
	    String as_idTurno, String as_tipoProceso, String as_numeroProceso, String as_userId, String as_remoteIpAddress
	)
	    throws B2BException
	{
		DAOManager ldm_manager;

		ldm_manager = DaoManagerFactory.getDAOManager();

		try
		{
			guardarTipoNumeroProceso(
			    as_idTurno, as_tipoProceso, as_numeroProceso, as_userId, as_remoteIpAddress, ldm_manager
			);
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("guardarTipoNumeroProceso", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}
	}

	/**
	 * Actualiza los cambios en pantalla que tenga el campo tipo proceso y numero
	 * proceso de un acto de grupo de naturaleza juridica 0400
	 *
	 * @param as_idTurno
	 *            numero de turno del caso
	 * @param as_tipoProceso
	 *            datos nuevos para el campo de tipo proceso
	 * @param as_numeroProceso
	 *            datos nuevos para el campo de numero proceso
	 * @param as_userId
	 *            Id del usuario que ejecuta el procedimiento
	 * @param as_remoteIp
	 *            Dirección IP del cliente que ejecuta el procedimiento
	 * @param adm_manager
	 *            DAOManager Conexión con la base de datos
	 * @throws B2BException
	 */
	public synchronized void guardarTipoNumeroProceso(
	    String as_idTurno, String as_tipoProceso, String as_numeroProceso, String as_userId, String as_remoteIp,
	    DAOManager adm_manager
	)
	    throws B2BException
	{
		Turno    lt_turno;
		TurnoDAO ltd_turnoDAO;

		lt_turno         = new Turno();
		ltd_turnoDAO     = DaoCreator.getTurnoDAO(adm_manager);

		lt_turno.setIdTurno(as_idTurno);

		lt_turno = ltd_turnoDAO.findById(lt_turno);

		if(lt_turno != null)
		{
			String ls_idSolicitud;

			ls_idSolicitud = lt_turno.getIdSolicitud();

			if(StringUtils.isValidString(ls_idSolicitud))
			{
				Collection<Acto> lca_actos;
				Acto             la_acto;
				ActoDAO          lad_actoDAO;

				la_acto = new Acto();
				la_acto.setIdSolicitud(ls_idSolicitud);

				lad_actoDAO     = DaoCreator.getActoDAO(adm_manager);
				lca_actos       = lad_actoDAO.findByIdSolicitud(la_acto);

				if(CollectionUtils.isValidCollection(lca_actos))
				{
					String     ls_idConstante;
					Constantes lc_constante;

					ls_idConstante     = ConstanteCommon.GRUPO_NATURALEZA_JURIDICA_MEDIDA_CAUTELAR;
					lc_constante       = DaoCreator.getConstantesDAO(adm_manager).findById(ls_idConstante);

					if(lc_constante == null)
					{
						Object[] loa_messageArgs;

						loa_messageArgs        = new String[1];
						loa_messageArgs[0]     = ls_idConstante;

						throw new B2BException(ErrorKeys.ERROR_SIN_CONSTANTE, loa_messageArgs);
					}

					NaturalezaJuridicaDAO lnj_DAO;
					ActoDAO               la_DAO;
					String                ls_idCirculoTurno;

					lnj_DAO               = DaoCreator.getNaturalezaJuridicaDAO(adm_manager);
					la_DAO                = DaoCreator.getActoDAO(adm_manager);
					ls_idCirculoTurno     = StringUtils.getStringNotNull(lt_turno.getIdCirculo());

					for(Acto lta_data : lca_actos)
					{
						if(lta_data != null)
						{
							NaturalezaJuridica lnj_temp;
							String             ls_idCirculoActo;

							lnj_temp             = new NaturalezaJuridica();
							ls_idCirculoActo     = StringUtils.getStringNotNull(lta_data.getIdCirculo());

							lnj_temp.setIdGrupoNatJur(lc_constante.getCaracter());
							lnj_temp.setIdNaturalezaJuridica(lta_data.getIdTipoActo());

							lnj_temp = lnj_DAO.findNatMedidaCautelar(lnj_temp);

							if((lnj_temp != null) && ls_idCirculoTurno.equals(ls_idCirculoActo))
							{
								lta_data.setReferencia(as_tipoProceso);
								lta_data.setNumeroProceso(as_numeroProceso);
								lta_data.setIdUsuarioModificacion(as_userId);
								lta_data.setIpModificacion(as_remoteIp);

								la_DAO.update(lta_data);
							}
						}
					}
				}
			}
		}
	}

	/**
	 * Método de inactivación de la anotación parcial
	 * @param as_idAnotacionParcial con el ide de la anotación a inactivar
	 * @param as_userId con el usuario que va a hacer la transacción
	 * @param as_localIp con la ip de la transaccion
	 * @throws B2BException
	 */
	public void inactivarAnotacionParcial(String as_idAnotacionParcial, String as_userId, String as_localIp)
	    throws B2BException
	{
		DAOManager ldm_manager;
		ldm_manager = DaoManagerFactory.getDAOManager();

		com.bachue.snr.prosnr01.dao.acc.AnotacionPredioDAO lapd_DAO;
		lapd_DAO = DaoCreator.getAccAnotacionPredioDAO(ldm_manager);

		try
		{
			if(StringUtils.isValidString(as_idAnotacionParcial))
			{
				com.bachue.snr.prosnr01.model.sdb.acc.AnotacionPredio lap_anotacionPredio;

				lap_anotacionPredio = new com.bachue.snr.prosnr01.model.sdb.acc.AnotacionPredio();

				lap_anotacionPredio.setIdAnotacionPredio(as_idAnotacionParcial);

				lap_anotacionPredio = lapd_DAO.findById(lap_anotacionPredio);

				if(lap_anotacionPredio != null)
				{
					lap_anotacionPredio.setIdUsuarioModificacion(as_userId);
					lap_anotacionPredio.setIpModificacion(as_localIp);
					lap_anotacionPredio.setActivo(EstadoCommon.N);

					lapd_DAO.updateAnotacion(lap_anotacionPredio, true);
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("guardarTipoNumeroProceso", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}
	}

	/**
	 * Método para obtener las matriculas a heredar
	 *
	 * @param aorc_rc
	 *            Objeto para extraer información necesaria para buscar las
	 *            matrículas
	 * @return
	 * @throws B2BException
	 */
	public synchronized RegistroCalificacion matriculasAHeredar(RegistroCalificacion aorc_rc)
	    throws B2BException
	{
		boolean                          lb_devolucion;
		boolean                          lb_segregacion;
		Collection<RegistroCalificacion> locrc_rc;
		Collection<RegistroCalificacion> locrc_tmp;
		DAOManager                       ldm_manager;
		int                              li_inicio;
		RegistroCalificacion             lorc_matriculasAHeredar;
		RegistroCalificacionDAO          lorcd_rcd;
		String                           ls_matricula;
		String                           ls_turno;
		String                           ls_circuloRegistral;

		lb_segregacion              = false;
		lb_devolucion               = false;
		locrc_rc                    = new ArrayList<RegistroCalificacion>();
		locrc_tmp                   = new ArrayList<RegistroCalificacion>();
		ldm_manager                 = DaoManagerFactory.getDAOManager();
		lorc_matriculasAHeredar     = new RegistroCalificacion();
		lorcd_rcd                   = DaoCreator.getRegistroCalificacionDAO(ldm_manager);

		try
		{
			if(aorc_rc != null)
			{
				if(CollectionUtils.isValidCollection(aorc_rc.getAllMatriculas()))
				{
					ActoDAO                   la_DAO;
					String                    ls_idSolicitud;
					SolicitudMatriculaActoDAO lsma_DAO;
					TipoActoDAO               lta_DAO;
					Turno                     lt_turno;
					TurnoDAO                  lt_DAO;

					la_DAO             = DaoCreator.getActoDAO(ldm_manager);
					lb_devolucion      = aorc_rc.isDevolucion();
					ls_idSolicitud     = null;
					ls_turno           = aorc_rc.getTurno();
					lsma_DAO           = DaoCreator.getSolicitudMatriculaActoDAO(ldm_manager);
					lta_DAO            = DaoCreator.getTipoActoDAO(ldm_manager);
					lt_turno           = new Turno();
					lt_DAO             = DaoCreator.getTurnoDAO(ldm_manager);

					if(StringUtils.isValidString(ls_turno))
					{
						lt_turno.setIdTurno(ls_turno);

						lt_turno = lt_DAO.findById(lt_turno);

						if(lt_turno != null)
							ls_idSolicitud = lt_turno.getIdSolicitud();
					}

					for(RegistroCalificacion lorc_rc : aorc_rc.getAllMatriculas())
					{
						ls_matricula = lorc_rc.getIdCirculo();

						if(StringUtils.isValidString(ls_matricula))
						{
							li_inicio = ls_matricula.lastIndexOf("-");

							if(li_inicio != -1)
							{
								ls_circuloRegistral     = ls_matricula.substring(0, li_inicio);
								ls_matricula            = ls_matricula.substring(li_inicio + 1, ls_matricula.length());

								lorc_rc.setIdCirculo(ls_circuloRegistral);
								lorc_rc.setIdMatricula(Long.valueOf(ls_matricula));

								if(lb_devolucion)
									lorc_rc.setTurno(ls_turno);

								locrc_tmp = lorcd_rcd.matriculasAHeredar(lorc_rc, lb_devolucion);

								if(CollectionUtils.isValidCollection(locrc_tmp))
									locrc_rc.addAll(locrc_tmp);

								Collection<SolicitudMatriculaActo> lcsma_actos;
								SolicitudMatriculaActo             lsma_matriculaActo;

								lsma_matriculaActo = new SolicitudMatriculaActo();

								lsma_matriculaActo.setIdMatricula(NumericUtils.getLong(ls_matricula));
								lsma_matriculaActo.setIdCirculo(ls_circuloRegistral);
								lsma_matriculaActo.setIdSolicitud(ls_idSolicitud);

								lcsma_actos = lsma_DAO.findAllByIdSolicitud(lsma_matriculaActo, true);

								if(CollectionUtils.isValidCollection(lcsma_actos))
								{
									for(SolicitudMatriculaActo lsma_iterador : lcsma_actos)
									{
										if(lsma_iterador != null)
										{
											String ls_idActo;

											ls_idActo = lsma_iterador.getIdActo();

											if(StringUtils.isValidString(ls_idActo))
											{
												Acto la_acto;

												la_acto = new Acto();

												la_acto.setIdActo(ls_idActo);

												la_acto = la_DAO.findById(la_acto);

												if(la_acto != null)
												{
													String ls_idTipoActo;

													ls_idTipoActo = la_acto.getIdTipoActo();

													if(StringUtils.isValidString(ls_idTipoActo))
													{
														TipoActo lta_acto;

														lta_acto = new TipoActo();

														lta_acto.setIdTipoActo(ls_idTipoActo);

														lta_acto = lta_DAO.findById(lta_acto);

														if(lta_acto != null)
														{
															String ls_segregacion;

															ls_segregacion = lta_acto.getGeneraSegregacion();

															if(
															    StringUtils.isValidString(ls_segregacion)
																    && ls_segregacion.equalsIgnoreCase(EstadoCommon.S)
															)
																lb_segregacion = true;
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

					if(CollectionUtils.isValidCollection(locrc_rc))
						lorc_matriculasAHeredar.setAllMatriculas(locrc_rc);

					lorc_matriculasAHeredar.setActoSegregacion(lb_segregacion);
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("matriculasAHeredar", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lorc_matriculasAHeredar;
	}

	/**
	 * Método para poder modificar la anotación seleccionada
	 *
	 * @param aorc_rc
	 *            objeto con información de la anotación a modificar y la nueva
	 *            información que va a ser modificada
	 * @param ab_accion
	 *            Booleano para saber si valida los campos
	 * @return
	 * @throws B2BException
	 */
	public synchronized RegistroCalificacion modificarAnotacion(
	    RegistroCalificacion aorc_rc, boolean ab_accion, String as_userId, String as_remoteIp
	)
	    throws B2BException
	{
		DAOManager ldm_manager;

		com.bachue.snr.prosnr01.model.sdb.acc.AnotacionPredio lap_anotacionPredio;
		RegistroCalificacion lorc_dataReturn;
		TurnoHistoria lth_turnoHistoria;
		TurnoHistoriaDAO lthd_DAO;
		String     ls_idSolicitud;
		String     ls_idTurno;
		Long       ll_idTurnoHistoria;
		com.bachue.snr.prosnr01.dao.acc.AnotacionPredioDAO lapd_DAO;
		Documento  ld_documento;
		DocumentoDAO ldd_DAO;
		Collection<Anotacion> lca_intervinientes;
		Collection<RegistroCalificacion> lcrc_anotacionACancelar;
		PersonaDAO lpd_DAO;
		String     ls_anotacionPredio;
		com.bachue.snr.prosnr01.dao.acc.AnotacionPredioCiudadanoDAO lapcd_DAO;
		AnotacionCancelacionDAO loacd_DAO;
		String     ls_user;
		String     ls_ipAddress;
		Long       ll_idAnotacion;

		ldm_manager                 = DaoManagerFactory.getDAOManager();
		lap_anotacionPredio         = new com.bachue.snr.prosnr01.model.sdb.acc.AnotacionPredio();
		lorc_dataReturn             = new RegistroCalificacion();
		lth_turnoHistoria           = new TurnoHistoria();
		lthd_DAO                    = DaoCreator.getTurnoHistoriaDAO(ldm_manager);
		lapd_DAO                    = DaoCreator.getAccAnotacionPredioDAO(ldm_manager);
		loacd_DAO                   = DaoCreator.getAnotacionCancelacionDAO(ldm_manager);
		ldd_DAO                     = DaoCreator.getDocumentoDAO(ldm_manager);
		lpd_DAO                     = DaoCreator.getPersonaDAO(ldm_manager);
		lca_intervinientes          = new ArrayList<Anotacion>();
		lcrc_anotacionACancelar     = new ArrayList<RegistroCalificacion>();
		ld_documento                = new Documento();
		ls_anotacionPredio          = null;
		lapcd_DAO                   = DaoCreator.getAccAnotacionPredioCiudadanoDAO(ldm_manager);
		ll_idAnotacion              = null;

		try
		{
			if(aorc_rc != null)
			{
				lth_turnoHistoria.setIdTurnoHistoria(aorc_rc.getIdTurnoHistoria());
				ls_user          = aorc_rc.getUserId();
				ls_ipAddress     = aorc_rc.getIpAddress();

				lth_turnoHistoria = lthd_DAO.findById(lth_turnoHistoria);

				if(lth_turnoHistoria != null)
				{
					boolean lb_actualizarRrr;

					lb_actualizarRrr            = true;
					ls_idSolicitud              = lth_turnoHistoria.getIdSolicitud();
					ls_idTurno                  = lth_turnoHistoria.getIdTurno();
					ll_idTurnoHistoria          = lth_turnoHistoria.getIdTurnoHistoria();
					lap_anotacionPredio         = aorc_rc.getDataAnotacionPredio();
					ld_documento                = aorc_rc.getDataDocumento();
					lca_intervinientes          = aorc_rc.getIntervinientes();
					lcrc_anotacionACancelar     = aorc_rc.getAnotacionesCancelacion();

					if(lap_anotacionPredio != null)
					{
						ll_idAnotacion = lap_anotacionPredio.getIdAnotacion();

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

						{
							String ls_tmp;
							ls_tmp = lap_anotacionPredio.getIdNaturalezaJuridica();

							if(!StringUtils.isValidString(ls_tmp))
								throw new B2BException(ErrorKeys.ERROR_TIPO_ACTO_INVALIDO);

							{
								Collection<InteresadoDocumentoTipo> lcidt_datos;
								NaturalezaJuridica                  lnj_naturalezaJuridica;
								long                                ll_maxVersion;
								lcidt_datos     = null;

								lnj_naturalezaJuridica = new NaturalezaJuridica();
								lnj_naturalezaJuridica.setIdNaturalezaJuridica(ls_tmp);

								lnj_naturalezaJuridica = DaoCreator.getNaturalezaJuridicaDAO(ldm_manager)
										                               .findByIdMaxVersion(lnj_naturalezaJuridica);

								if(lnj_naturalezaJuridica != null)
								{
									ll_maxVersion     = lnj_naturalezaJuridica.getVersion();
									lcidt_datos       = DaoCreator.getInteresadoDocumentoTipoDAO(ldm_manager)
											                          .findRrr(ls_tmp, ll_maxVersion);

									lb_actualizarRrr = CollectionUtils.isValidCollection(lcidt_datos);
								}
							}
						}

						lap_anotacionPredio.setIdSolicitud(ls_idSolicitud);
						lap_anotacionPredio.setIdTurno(ls_idTurno);
						lap_anotacionPredio.setIdTurnoHistoria(NumericUtils.getLong(ll_idTurnoHistoria));
						lap_anotacionPredio.setIdDocumento(lap_anotacionPredio.getIdDocumento());
						lap_anotacionPredio.setVersionDocumento(1);
						lap_anotacionPredio.setIdEstadoRegistro(EstadoCommon.T);
						lap_anotacionPredio.setIdUsuarioModificacion(ls_user);
						lap_anotacionPredio.setIpModificacion(ls_ipAddress);

						lapd_DAO.modificiarAnotacionesPredio(lap_anotacionPredio, true);

						ls_anotacionPredio = lap_anotacionPredio.getIdAnotacionPredio();

						if(!lb_actualizarRrr && StringUtils.isValidString(ls_anotacionPredio))
							lapcd_DAO.modificarInteresadaRR(ls_anotacionPredio, ls_user, ls_ipAddress);
					}

					if((ld_documento != null) && ab_accion)
					{
						{
							String ls_tmp;
							ls_tmp = ld_documento.getIdTipoDocumento();

							if(!StringUtils.isValidString(ls_tmp))
								throw new B2BException(ErrorKeys.TIPO_DOCUMENTO_INVALIDO);
						}

						{
							String ls_tmp;
							ls_tmp = ld_documento.getNumero();

							if(!StringUtils.isValidString(ls_tmp))
								throw new B2BException(ErrorKeys.DEBE_DIGITAR_NUMERO_DE_DOC);
						}

						{
							String ls_tmp;
							ls_tmp = ld_documento.getIdTipoOficina();

							if(!StringUtils.isValidString(ls_tmp))
								throw new B2BException(ErrorKeys.DEBE_SELECCIONAR_TIPO_OFICINA);
						}

						{
							String ls_tmp;
							ls_tmp = ld_documento.getTipoEntidad();

							if(!StringUtils.isValidString(ls_tmp))
								throw new B2BException(ErrorKeys.DEBE_SELECCIONAR_TIPO_ENTIDAD);
						}

						{
							String ls_tmp;
							ls_tmp = ld_documento.getIdPais();

							if(!StringUtils.isValidString(ls_tmp))
								throw new B2BException(ErrorKeys.DEBE_SELECCIONAR_PAIS);
						}

						{
							String ls_tmp;
							ls_tmp = ld_documento.getIdDepartamento();

							if(!StringUtils.isValidString(ls_tmp))
								throw new B2BException(ErrorKeys.DEBE_DIGITAR_DEPARTAMENTO);
						}

						{
							String ls_tmp;
							ls_tmp = ld_documento.getIdMunicipio();

							if(!StringUtils.isValidString(ls_tmp))
								throw new B2BException(ErrorKeys.DEBE_SELECCIONAR_MUNICIPIO);
						}

						{
							String ls_tmp;
							ls_tmp = ld_documento.getIdOficinaOrigen();

							if(!StringUtils.isValidString(ls_tmp))
								throw new B2BException(ErrorKeys.DEBE_SELECCIONAR_OFICINA_ORIGEN);

							ld_documento.setVersion(obtenerVersionMaximaOficinaOrigen(ls_tmp, ldm_manager));
						}

						{
							Documento ld_tmp;
							String    ls_idDocumento;

							ld_tmp = ldd_DAO.consultaDocumento(ld_documento);

							if(ld_tmp != null)
							{
								ls_idDocumento = ld_tmp.getIdDocumento();

								if(!StringUtils.isValidString(ls_idDocumento))
									throw new B2BException(ErrorKeys.ERROR_SIN_ID_DOCUMENTO);

								ld_documento.setIdUsuarioModificacion(ls_user);
								ld_documento.setIpModificacion(ls_ipAddress);
								ld_documento.setIdDocumento(ls_idDocumento);

								ldd_DAO.insertOrUpdate(ld_documento, false);
							}
							else
							{
								int li_secuencia;

								li_secuencia = ldd_DAO.findSecuencia();

								if(li_secuencia > 0)
								{
									ld_documento.setIdDocumento(StringUtils.getString(li_secuencia));
									ld_documento.setVersionDocumento(NumericUtils.getLongWrapper(1L));
									ld_documento.setIdUsuarioCreacion(ls_user);
									ld_documento.setIpCreacion(ls_ipAddress);

									ldd_DAO.insertOrUpdate(ld_documento, true);

									if(lap_anotacionPredio != null)
									{
										lap_anotacionPredio.setIdDocumento(ld_documento.getIdDocumento());
										lap_anotacionPredio.setIdUsuarioModificacion(ls_user);
										lap_anotacionPredio.setIpCreacion(ls_ipAddress);
										lap_anotacionPredio.setIdUsuarioModificacion(ls_user);
										lap_anotacionPredio.setIpModificacion(ls_ipAddress);

										lapd_DAO.modificiarAnotacionesPredio(lap_anotacionPredio, false);
									}
								}
								else
									throw new B2BException(ErrorKeys.BGN_DOCUMENTO_INSERT);
							}
						}
					}

					if(CollectionUtils.isValidCollection(lca_intervinientes))
					{
						Persona                                                        lp_parametros;
						com.bachue.snr.prosnr01.model.sdb.acc.AnotacionPredioCiudadano loa_anotacionPredio;
						com.bachue.snr.prosnr01.model.sdb.acc.AnotacionPredioCiudadano loapc_tmp;

						lp_parametros           = new Persona();
						loa_anotacionPredio     = new com.bachue.snr.prosnr01.model.sdb.acc.AnotacionPredioCiudadano();
						loapc_tmp               = new com.bachue.snr.prosnr01.model.sdb.acc.AnotacionPredioCiudadano();

						loapc_tmp.setIdAnotacionPredio(ls_anotacionPredio);

						lapcd_DAO.deleteIntervinientes(loapc_tmp);

						for(Anotacion loa_tmp : lca_intervinientes)
						{
							lp_parametros           = loa_tmp.getPersona();
							loa_anotacionPredio     = loa_tmp.getAnotacionPredioCiudadano();

							if(lp_parametros != null)
							{
								String ls_tipoPersona;
								String ls_tipoDocumento;

								ls_tipoPersona       = lp_parametros.getIdTipoPersona();
								ls_tipoDocumento     = lp_parametros.getIdDocumentoTipo();

								if(!StringUtils.isValidString(ls_tipoPersona))
									throw new B2BException(ErrorKeys.DEBE_SELECCIONAR_TIPO_PERSONA);

								if(!StringUtils.isValidString(ls_tipoDocumento))
									throw new B2BException(ErrorKeys.DEBE_SELECCIONAR_TIPO_DOC);

								{
									String ls_documento;
									ls_documento = lp_parametros.getNumeroDocumento();

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
										{
											String ls_nacionalidad;
											ls_nacionalidad = lp_parametros.getIdPais();

											if(!StringUtils.isValidString(ls_nacionalidad))
												throw new B2BException(ErrorKeys.DEBE_SELECCIONAR_NACIONALIDAD);
										}

										{
											String ls_primerNombre;
											ls_primerNombre = lp_parametros.getPrimerNombre();

											if(!StringUtils.isValidString(ls_primerNombre))
												throw new B2BException(ErrorKeys.DEBE_DIGITAR_PRIMER_NOMBRE);
										}

										{
											String ls_primerApellido;
											ls_primerApellido = lp_parametros.getPrimerApellido();

											if(!StringUtils.isValidString(ls_primerApellido))
												throw new B2BException(ErrorKeys.DEBE_DIGITAR_PRIMER_APELLIDO);
										}

										if(loa_anotacionPredio != null)
										{
											String ls_rol;
											ls_rol = loa_anotacionPredio.getRolPersona();

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
										{
											String ls_nacionalidad;
											ls_nacionalidad = lp_parametros.getIdPais();

											if(!StringUtils.isValidString(ls_nacionalidad))
												throw new B2BException(ErrorKeys.DEBE_SELECCIONAR_NACIONALIDAD);
										}

										if(loa_anotacionPredio != null)
										{
											String ls_rol;
											ls_rol = loa_anotacionPredio.getRolPersona();

											if(!StringUtils.isValidString(ls_rol))
												throw new B2BException(ErrorKeys.DEBE_SELECCIONAR_ROL);
										}

										{
											String ls_razonSocial;
											ls_razonSocial = lp_parametros.getRazonSocial();

											if(!StringUtils.isValidString(ls_razonSocial))
												throw new B2BException(ErrorKeys.DEBE_DIGITAR_RAZON_SOCIAL);
										}
									}
									else
										throw new B2BException(ErrorKeys.DEBE_SELECCIONAR_JURIDICO);
								}

								if(ls_tipoPersona.equalsIgnoreCase(EstadoCommon.SE))
								{
									Constantes lc_constante;
									lc_constante = new Constantes();
									lc_constante.setIdConstante(ConstanteCommon.CONSECUTIVO_TIPO_DOCUMENTO_SECUENCIA);
									lc_constante = DaoCreator.getConstantesDAO(ldm_manager).findById(lc_constante);

									if(lc_constante != null)
									{
										BigInteger lbi_secuencia;
										lbi_secuencia = lc_constante.getEntero().add(BigInteger.valueOf(1));

										lc_constante.setEntero(lbi_secuencia);
										DaoCreator.getConstantesDAO(ldm_manager).insertOrUpdate(lc_constante, false);

										lp_parametros.setNumeroDocumento(StringUtils.getString(lbi_secuencia));
									}
								}
							}

							{
								com.bachue.snr.prosnr01.model.sdb.acc.AnotacionPredioCiudadano lapc_anotacionPredioCiudadano;
								com.bachue.snr.prosnr01.model.sdb.acc.AnotacionPredio          loap_tmp;
								Persona                                                        lp_persona;
								String                                                         ls_idPersona;

								lapc_anotacionPredioCiudadano     = loa_anotacionPredio;
								loap_tmp                          = new com.bachue.snr.prosnr01.model.sdb.acc.AnotacionPredio();
								lp_persona                        = new Persona();
								ls_idPersona                      = null;

								if(lp_parametros != null)
								{
									String ls_idFlagPersona;

									ls_idFlagPersona = marcarFlagPersona(
										    ldm_manager, lp_parametros, ls_user, ls_ipAddress
										);

									if(StringUtils.isValidString(ls_idFlagPersona))
									{
										lp_persona = lpd_DAO.findById(ls_idFlagPersona);

										if(lp_persona != null)
										{
											Persona lp_temp;

											lp_temp     = new Persona();

											lp_temp = lpd_DAO.findDataCalificador(lp_parametros);

											if(lp_temp != null)
											{
												lp_temp     = lpd_DAO.findById(lp_temp);

												ls_idPersona = lp_temp.getIdPersona();
											}
										}
									}
								}

								if(lapc_anotacionPredioCiudadano != null)
								{
									lapc_anotacionPredioCiudadano.setIdAnotacionPredio(ls_anotacionPredio);
									lapc_anotacionPredioCiudadano.setIdPersona(ls_idPersona);

									loap_tmp.setIdAnotacionPredio(ls_anotacionPredio);

									loap_tmp = lapd_DAO.findById(loap_tmp);

									if(loap_tmp != null)
									{
										lapc_anotacionPredioCiudadano.setIdCirculo(loap_tmp.getIdCirculo());
										lapc_anotacionPredioCiudadano.setIdMatricula(
										    NumericUtils.getLong(loap_tmp.getIdMatricula())
										);
										lapc_anotacionPredioCiudadano.setIdAnotacion(
										    NumericUtils.getLong(loap_tmp.getIdAnotacion())
										);
										lapc_anotacionPredioCiudadano.setIdUsuarioCreacion(ls_user);
										lapc_anotacionPredioCiudadano.setIpCreacion(ls_ipAddress);
										lapc_anotacionPredioCiudadano.setIdUsuarioModificacion(ls_user);
										lapc_anotacionPredioCiudadano.setIpModificacion(ls_ipAddress);
										lapc_anotacionPredioCiudadano.setIdTurno(ls_idTurno);
										lapc_anotacionPredioCiudadano.setRolPersona(
										    loa_anotacionPredio.getRolPersona()
										);

										lapcd_DAO.insert(lapc_anotacionPredioCiudadano);
									}
								}
							}

							lp_parametros           = new Persona();
							loa_anotacionPredio     = new com.bachue.snr.prosnr01.model.sdb.acc.AnotacionPredioCiudadano();
						}
					}

					{
						if(CollectionUtils.isValidCollection(lcrc_anotacionACancelar))
						{
							AnotacionCancelacion loac_tmp;
							boolean              lb_delete;
							lb_delete = false;

							for(RegistroCalificacion lorc_rc : lcrc_anotacionACancelar)
							{
								String ls_solicitud;
								long   ll_matricula;
								String ls_circulo;

								ls_solicitud     = lth_turnoHistoria.getIdSolicitud();
								ll_matricula     = NumericUtils.getLong(lorc_rc.getIdMatricula());
								ls_circulo       = lorc_rc.getIdCirculo();

								if(!lb_delete)
								{
									loac_tmp = new AnotacionCancelacion();
									loac_tmp.setIdCirculo(ls_circulo);
									loac_tmp.setIdMatricula(ll_matricula);
									loac_tmp.setIdTurno(ls_idTurno);
									loac_tmp.setIdAnotacion(ll_idAnotacion);
									loac_tmp.setIdSolicitud(ls_solicitud);

									loacd_DAO.deleteByCirculoMatriculaAnotacionTruno(loac_tmp, false);
									lb_delete = true;
								}

								loac_tmp = new AnotacionCancelacion();

								if(lorc_rc.isRevision())
								{
									loac_tmp.setIdSolicitud(ls_solicitud);
									loac_tmp.setIdMatricula(ll_matricula);
									loac_tmp.setIdCirculo(ls_circulo);
									loac_tmp.setIdAnotacion1(lorc_rc.getIdAnotacion());
									loac_tmp.setIdAnotacion(ll_idAnotacion);
									loac_tmp.setIdUsuarioCreacion(ls_user);
									loac_tmp.setIpCreacion(ls_ipAddress);
									loac_tmp.setIdTurno(ls_idTurno);

									loacd_DAO.insert(loac_tmp);
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

			clh_LOGGER.error("modificarAnotacion", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lorc_dataReturn;
	}

	/**
	 * Método para poder modificar la información de un ciudadano
	 *
	 * @param aorc_rc
	 *            Objeto para extraer información como necesaria del ciudadano
	 * @return
	 * @throws B2BException
	 */
	public synchronized void modificarCiudadano(RegistroCalificacion aorc_rc)
	    throws B2BException
	{
		DAOManager ldm_manager;

		ldm_manager = DaoManagerFactory.getDAOManager();

		try
		{
			if(aorc_rc != null)
			{
				RegistroCalificacionDAO lorcd_rcd;
				String                  ls_porcentaje;

				lorcd_rcd         = DaoCreator.getRegistroCalificacionDAO(ldm_manager);
				ls_porcentaje     = aorc_rc.getPorcentajeStr();

				if(StringUtils.isValidString(ls_porcentaje))
				{
					try
					{
						Double ld_porcentaje;

						ld_porcentaje = NumericUtils.getDoubleWrapper(ls_porcentaje);

						if(
						    (NumericUtils.getDouble(ld_porcentaje) > 0.0)
							    && (NumericUtils.getDouble(ld_porcentaje) <= 100)
						)
							aorc_rc.setPorcentaje(ld_porcentaje);
						else
							throw new B2BException(ErrorKeys.ERROR_RANGO_PORCENTAJE_INVALIDO);
					}
					catch(NumberFormatException nfe_nfe)
					{
						throw new B2BException(ErrorKeys.ERROR_VALOR_PORCENTAJE_NUMERICO);
					}
				}
				else
					aorc_rc.setPorcentaje(new Double(0));

				com.bachue.snr.prosnr01.dao.acc.AnotacionPredioCiudadanoDAO    lapc_DAO;
				com.bachue.snr.prosnr01.model.sdb.acc.AnotacionPredioCiudadano lapc_anotacion;
				String                                                         ls_idAnotacionPredioCiudadano;

				lapc_DAO                          = DaoCreator.getAccAnotacionPredioCiudadanoDAO(ldm_manager);
				lapc_anotacion                    = new com.bachue.snr.prosnr01.model.sdb.acc.AnotacionPredioCiudadano();
				ls_idAnotacionPredioCiudadano     = StringUtils.getStringNotNull(aorc_rc.getIdAnotacionPredio());

				lapc_anotacion.setIdAnotacionPredioCiudadano(ls_idAnotacionPredioCiudadano);

				lapc_anotacion = lapc_DAO.findById(lapc_anotacion);

				if(lapc_anotacion != null)
				{
					Persona    lp_persona;
					Persona    lp_personaBusqueda;
					PersonaDAO lp_DAO;
					String     ls_idPersona;

					lp_persona             = new Persona();
					lp_DAO                 = DaoCreator.getPersonaDAO(ldm_manager);
					ls_idPersona           = aorc_rc.getIdPersona();
					lp_personaBusqueda     = lp_DAO.findById(ls_idPersona);

					if(lp_personaBusqueda != null)
					{
						lp_persona.setIdNaturalGenero(lp_personaBusqueda.getIdNaturalGenero());
						lp_persona.setIdPais(lp_personaBusqueda.getIdPais());
						lp_persona.setIdEntidadExterna(lp_personaBusqueda.getIdEntidadExterna());
						lp_persona.setIdDocumentoTipo(lp_personaBusqueda.getIdDocumentoTipo());
						lp_persona.setTipoDocIdentidad(lp_personaBusqueda.getIdDocumentoTipo());
						lp_persona.setNumeroDocumento(lp_personaBusqueda.getNumeroDocumento());
						lp_persona.setIdTipoPersona(lp_personaBusqueda.getIdTipoPersona());
					}

					lp_persona.setPrimerApellido(aorc_rc.getPrimerApellido());
					lp_persona.setSegundoApellido(aorc_rc.getSegundoApellido());
					lp_persona.setPrimerNombre(aorc_rc.getPrimerNombre());
					lp_persona.setSegundoNombre(aorc_rc.getSegundoNombre());

					lp_personaBusqueda = lp_DAO.findByPersonData(
						    lp_persona, IdentificadoresCommon.TIPO_NUM_DOC_NOMBRES
						);

					if(lp_personaBusqueda != null)
						ls_idPersona = lp_personaBusqueda.getIdPersona();
					else
					{
						lp_persona.setIdUsuarioCreacion(aorc_rc.getUserId());
						lp_persona.setIpCreacion(aorc_rc.getIpAddress());

						lp_persona = lp_DAO.insertOrUpdate(lp_persona, true);

						if(lp_persona != null)
							ls_idPersona = lp_persona.getIdPersona();
						else
							throw new B2BException(ErrorKeys.ERROR_INTERNO_SISTEMA);
					}

					if(StringUtils.isValidString(ls_idPersona))
					{
						String ls_rol;

						lapc_anotacion     = new com.bachue.snr.prosnr01.model.sdb.acc.AnotacionPredioCiudadano();
						ls_rol             = StringUtils.getStringNotNull(aorc_rc.getRolPersona());

						lapc_anotacion.setIdAnotacion(lapc_anotacion.getIdAnotacion());
						lapc_anotacion.setIdAnotacionPredio(lapc_anotacion.getIdAnotacionPredio());
						lapc_anotacion.setIdPersona(ls_idPersona);
						lapc_anotacion.setRolPersona(ls_rol);
						aorc_rc.setIdPersona(ls_idPersona);

						lapc_anotacion = lapc_DAO.findByIdAnotacionPersonaRol(lapc_anotacion);

						if(lapc_anotacion != null)
						{
							String ls_idAnotacionPredioTemp;

							ls_idAnotacionPredioTemp = StringUtils.getStringNotNull(
								    lapc_anotacion.getIdAnotacionPredioCiudadano()
								);

							if(!ls_idAnotacionPredioCiudadano.equalsIgnoreCase(ls_idAnotacionPredioTemp))
							{
								Object[] loa_messageArgs;

								loa_messageArgs        = new String[1];
								loa_messageArgs[0]     = ls_rol;

								throw new B2BException(ErrorKeys.ERROR_INTERVINIENTE_REPETIDO, loa_messageArgs);
							}
						}
					}
				}

				lorcd_rcd.saveDetailAnotacion(aorc_rc);
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("modificarCiudadano", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}
	}

	/**
	 * Método para mover los IDS de AnotacionPredio y AnotacionPredioCiudadano dependiendo de la anotación que se elimine
	 *
	 * @param aea_datosEliminar
	 *            Objeto con anotación seleccionada que se va a a eliminar
	 * @return
	 * @throws B2BException
	 */
	public void modificarIDS(EliminarAnotacion aea_datosEliminar)
	    throws B2BException
	{
		DAOManager ldm_manager;

		ldm_manager = DaoManagerFactory.getDAOManager();

		try
		{
			if(aea_datosEliminar != null)
			{
				String ls_idAnotacionPredio;

				ls_idAnotacionPredio = aea_datosEliminar.getIdAnotacionPredio();

				if(StringUtils.isValidString(ls_idAnotacionPredio))
				{
					com.bachue.snr.prosnr01.model.sdb.acc.AnotacionPredio       laap_anotacionPredio;
					com.bachue.snr.prosnr01.dao.acc.AnotacionPredioDAO          laap_DAO;
					com.bachue.snr.prosnr01.dao.acc.AnotacionPredioCiudadanoDAO laapc_DAO;

					laapc_DAO                = DaoCreator.getAccAnotacionPredioCiudadanoDAO(ldm_manager);
					laap_anotacionPredio     = new com.bachue.snr.prosnr01.model.sdb.acc.AnotacionPredio();
					laap_DAO                 = DaoCreator.getAccAnotacionPredioDAO(ldm_manager);

					laap_anotacionPredio.setIdAnotacionPredio(ls_idAnotacionPredio);

					laap_anotacionPredio = laap_DAO.findById(laap_anotacionPredio);

					Collection<com.bachue.snr.prosnr01.model.sdb.acc.AnotacionPredio> lcap_anotacionesPredio;

					lcap_anotacionesPredio     = new ArrayList<com.bachue.snr.prosnr01.model.sdb.acc.AnotacionPredio>();

					lcap_anotacionesPredio = laap_DAO.findByCirculoMatriculaActive(laap_anotacionPredio);

					if(laap_anotacionPredio != null)
					{
						if(CollectionUtils.isValidCollection(lcap_anotacionesPredio))
						{
							boolean lb_reorganizacion;

							lb_reorganizacion = false;

							for(com.bachue.snr.prosnr01.model.sdb.acc.AnotacionPredio lanp_apTemp : lcap_anotacionesPredio)
							{
								if(lanp_apTemp != null)
								{
									String ls_idAnotacionPredioSelected;
									String ls_idAnotacionPredioFromCollection;

									ls_idAnotacionPredioSelected           = laap_anotacionPredio.getIdAnotacionPredio();
									ls_idAnotacionPredioFromCollection     = lanp_apTemp.getIdAnotacionPredio();

									if(
									    StringUtils.isValidString(ls_idAnotacionPredioSelected)
										    && StringUtils.isValidString(ls_idAnotacionPredioFromCollection)
									)
									{
										if(
										    !ls_idAnotacionPredioSelected.equalsIgnoreCase(
											        ls_idAnotacionPredioFromCollection
											    )
										)
										{
											if(lb_reorganizacion)
											{
												Long ll_idAnotacionSeleccionado;
												Long ll_idAnotacionAAsignar;

												ll_idAnotacionSeleccionado     = laap_anotacionPredio.getIdAnotacion();
												ll_idAnotacionAAsignar         = lanp_apTemp.getIdAnotacion();

												if(
												    NumericUtils.isValidLong(ll_idAnotacionSeleccionado)
													    && NumericUtils.isValidLong(ll_idAnotacionAAsignar)
												)
												{
													String ls_idAnotacionPredioCiudadano;

													ls_idAnotacionPredioCiudadano = lanp_apTemp.getIdAnotacionPredio();

													if(StringUtils.isValidString(ls_idAnotacionPredioCiudadano))
													{
														com.bachue.snr.prosnr01.model.sdb.acc.AnotacionPredioCiudadano lapc_anotacionPredioCiudadano;

														lapc_anotacionPredioCiudadano = new com.bachue.snr.prosnr01.model.sdb.acc.AnotacionPredioCiudadano();

														lapc_anotacionPredioCiudadano.setIdAnotacionPredio(
														    ls_idAnotacionPredioCiudadano
														);

														lapc_anotacionPredioCiudadano = laapc_DAO.findOneByIdAnotacion(
															    lapc_anotacionPredioCiudadano
															);

														if(lapc_anotacionPredioCiudadano != null)
														{
															String ls_usuarioModificacion;
															String ls_ipModificacion;

															ls_usuarioModificacion     = aea_datosEliminar
																	.getIdUsuarioModificacion();
															ls_ipModificacion          = aea_datosEliminar
																	.getIpModificacion();
															ll_idAnotacionAAsignar     = NumericUtils.getLongWrapper(
																    NumericUtils.getLong(ll_idAnotacionAAsignar) - 1
																);

															lanp_apTemp.setIdAnotacion(ll_idAnotacionAAsignar);
															lanp_apTemp.setIdUsuarioModificacion(
															    ls_usuarioModificacion
															);
															lanp_apTemp.setIpModificacion(ls_ipModificacion);

															lapc_anotacionPredioCiudadano.setIdAnotacion(
															    NumericUtils.getLong(ll_idAnotacionAAsignar)
															);
															lapc_anotacionPredioCiudadano.setIdUsuarioModificacion(
															    ls_usuarioModificacion
															);
															lapc_anotacionPredioCiudadano.setIpModificacion(
															    ls_ipModificacion
															);

															laap_DAO.updateAnotacionIds(lanp_apTemp);
															laapc_DAO.modificarAnotacionCiudadanoIds(
															    lapc_anotacionPredioCiudadano
															);
														}
													}
												}
											}
										}
										else
											lb_reorganizacion = true;
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

			clh_LOGGER.error("modificarIDS", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}
	}

	/**
	 * Método para modificar el id de la anotación
	 *
	 * @param as_idMoved
	 *            String con el Id nuevo de la anotación
	 * @param as_idAnt
	 *            String con el Id anterior de la anotación
	 * @param as_userId
	 *            String del usuario en sesión
	 * @return
	 * @throws B2BException
	 */
	public synchronized RegistroCalificacion modifyidAnotacion(String as_idMoved, String as_idAnt, String as_userId)
	    throws B2BException
	{
		DAOManager              ldm_manager;
		RegistroCalificacion    lorc_matriculas;
		RegistroCalificacion    lorc_matriculasMoved;
		RegistroCalificacion    lorc_matriculasAnt;
		RegistroCalificacionDAO lorcd_rcd;

		ldm_manager         = DaoManagerFactory.getDAOManager();
		lorcd_rcd           = DaoCreator.getRegistroCalificacionDAO(ldm_manager);
		lorc_matriculas     = new RegistroCalificacion();

		try
		{
			if(StringUtils.isValidString(as_idMoved) && StringUtils.isValidString(as_idAnt))
			{
				lorc_matriculasMoved     = lorcd_rcd.consultaAnotacion(as_idMoved);
				lorc_matriculasAnt       = lorcd_rcd.consultaAnotacion(as_idAnt);

				if((lorc_matriculas != null) && (lorc_matriculasAnt != null))
				{
					lorc_matriculas.setIdUsuarioModificacion(as_userId);

					lorc_matriculas.setIdAnotacion(lorc_matriculasAnt.getIdAnotacion());
					lorc_matriculas.setOrden(lorc_matriculasAnt.getOrden());
					lorc_matriculas.setIdAnotacionPredio(as_idMoved);

					lorcd_rcd.modificarAnotacion(lorc_matriculas, false);
					lorcd_rcd.modificarAnotacion(lorc_matriculas, true);

					lorc_matriculas.setIdAnotacion(lorc_matriculasMoved.getIdAnotacion());
					lorc_matriculas.setOrden(lorc_matriculasMoved.getOrden());
					lorc_matriculas.setIdAnotacionPredio(as_idAnt);

					lorcd_rcd.modificarAnotacion(lorc_matriculas, false);
					lorcd_rcd.modificarAnotacion(lorc_matriculas, true);
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("modifyidAnotacion", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lorc_matriculas;
	}

	/**
	 * Método para poder obtener la dirección del predio
	 *
	 * @param adp_dp
	 *            Objeto DireccionPredio
	 * @param as_userId
	 *            String usuario en sesión
	 * @param as_remoteIp
	 *            String IP de usuario en sesión
	 * @return
	 * @throws B2BException
	 */
	public synchronized DireccionDelPredio obtenerDireccionPredio(
	    DireccionPredio adp_dp, String as_userId, String as_remoteIp
	)
	    throws B2BException
	{
		DAOManager         ldm_manager;
		DireccionPredioDAO ldpDAO_bngDireccion;
		DireccionDelPredio lddp_return;

		lddp_return             = new DireccionDelPredio();
		ldm_manager             = DaoManagerFactory.getDAOManager();
		ldpDAO_bngDireccion     = DaoCreator.getDireccionPredioDAO(ldm_manager);

		try
		{
			Collection<DireccionPredio> lcdp_temp;
			DireccionPredio             ldp_direccionConsultar;

			if(adp_dp == null)
				throw new B2BException(ErrorKeys.ERROR_CARGUE_DIRECCION_PREDIO);

			ldp_direccionConsultar     = adp_dp;
			lcdp_temp                  = ldpDAO_bngDireccion.findByIdCirculoMatricula(ldp_direccionConsultar);

			if(CollectionUtils.isValidCollection(lcdp_temp))
			{
				for(DireccionPredio ldp_data : lcdp_temp)
					ldp_direccionConsultar = ldp_data;
			}

			if(ldp_direccionConsultar == null)
				throw new B2BException(ErrorKeys.ERROR_CARGUE_DIRECCION_PREDIO);

			lddp_return.setDireccionPredio(ldp_direccionConsultar.getDireccionPredioAcc());

			PredioRegistroDAO lprDAO_pr;
			PredioRegistro    lpr_predRegistro;

			lprDAO_pr            = DaoCreator.getPredioRegistroDAO(ldm_manager);
			lpr_predRegistro     = new PredioRegistro();

			lpr_predRegistro.setIdCirculo(ldp_direccionConsultar.getIdCirculo());
			lpr_predRegistro.setIdMatricula(NumericUtils.getLong(ldp_direccionConsultar.getIdMatricula()));
			lpr_predRegistro.setArgs(false);

			lpr_predRegistro = lprDAO_pr.findById(lpr_predRegistro);

			if(lpr_predRegistro == null)
				throw new B2BException(ErrorKeys.ERROR_CARGUE_DIRECCION_PREDIO);

			DatosAntSistema ldas_datosAntSistema;
			String          ls_idZonaRegistral;

			ldas_datosAntSistema     = lddp_return.getDatosAntiguoSistema();
			ls_idZonaRegistral       = lpr_predRegistro.getIdZonaRegistral();

			if(ldas_datosAntSistema == null)
				ldas_datosAntSistema = new DatosAntSistema();

			if(StringUtils.isValidString(ls_idZonaRegistral))
			{
				ZonaRegistral lzr_zonaRegistral;

				lzr_zonaRegistral = DaoCreator.getZonaRegistralDAO(ldm_manager).findById(ls_idZonaRegistral);

				if(lzr_zonaRegistral != null)
				{
					ldas_datosAntSistema.setIdPais(lzr_zonaRegistral.getIdPais());
					ldas_datosAntSistema.setIdDepartamento(lzr_zonaRegistral.getIdDepartamento());
					ldas_datosAntSistema.setIdMunicipio(lzr_zonaRegistral.getIdMunicipio());
				}
			}

			ldas_datosAntSistema.setIdTipoPredio(lpr_predRegistro.getIdTipoPredio());

			lddp_return.setDatosAntiguoSistema(ldas_datosAntSistema);
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("obtenerDireccionPredio", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lddp_return;
	}

	/**
	 * Método para poder obtener dirección del predio de digitador másivo
	 *
	 * @param adp_dp
	 *            Objeto para extraer información del predio
	 * @param as_userId
	 *            String de usuario en sesión
	 * @param as_remoteIp
	 *            String de Ip de usuario en sesión
	 * @return
	 * @throws B2BException
	 */
	public synchronized DireccionDelPredio obtenerDireccionPredioDigitadoreMasivo(
	    DireccionPredio adp_dp, String as_userId, String as_remoteIp
	)
	    throws B2BException
	{
		DAOManager            ldm_manager;
		DireccionPredioAccDAO ldp_DAO;
		DireccionDelPredio    lddp_return;

		lddp_return     = new DireccionDelPredio();
		ldm_manager     = DaoManagerFactory.getDAOManager();
		ldp_DAO         = DaoCreator.getDireccionPredioAccDAO(ldm_manager);

		try
		{
			DireccionPredioAcc ldp_direccionConsultar;

			ldp_direccionConsultar = ldp_DAO.findLastByIdCirculoMatricula(adp_dp.getDireccionPredioAcc());

			if(ldp_direccionConsultar == null)
				throw new B2BException(ErrorKeys.ERROR_CARGUE_DIRECCION_PREDIO);

			lddp_return.setDireccionPredio(ldp_direccionConsultar);

			{
				PredioRegistroDAO lprDAO_pr;
				PredioRegistro    lpr_predRegistro;

				lprDAO_pr            = DaoCreator.getPredioRegistroDAO(ldm_manager);
				lpr_predRegistro     = new PredioRegistro();

				lpr_predRegistro.setIdCirculo(ldp_direccionConsultar.getIdCirculo());
				lpr_predRegistro.setIdMatricula(NumericUtils.getLong(ldp_direccionConsultar.getIdMatricula()));
				lpr_predRegistro.setArgs(false);

				lpr_predRegistro = lprDAO_pr.findById(lpr_predRegistro);

				if(lpr_predRegistro == null)
					throw new B2BException(ErrorKeys.ERROR_CARGUE_DIRECCION_PREDIO);

				lddp_return.getDatosAntiguoSistema().setIdTipoPredio(lpr_predRegistro.getIdTipoPredio());
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("obtenerDireccionPredioDigitadoreMasivo", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lddp_return;
	}

	/**
	 * Método para precalificar anotaciones activas
	 *
	 * @throws B2BException
	 */
	public void precalificar(
	    String as_idTurnoHistoria, boolean ab_paramPreCalificar, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		DAOManager ldm_manager;

		ldm_manager = DaoManagerFactory.getDAOManager();

		try
		{
			TurnoHistoria lth_data;

			lth_data = new TurnoHistoria();

			if(StringUtils.isValidString(as_idTurnoHistoria))
			{
				lth_data.setIdTurnoHistoria(NumericUtils.getLongWrapper(as_idTurnoHistoria));
				lth_data.setIdUsuarioModificacion(as_userId);
				lth_data.setIpModificacion(as_localIp);

				DaoCreator.getProcedimientosDAO(ldm_manager).registroCalificacion(lth_data, ab_paramPreCalificar);
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("precalificar", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}
	}

	/**
	 * Metodo encargado de ejecuar procedimiento para copiar definitivo a temporal.
	 * @param ath_turnoHistoria Argumento de tipo <code>TurnoHistoria</code> que contiene los argumentos necesarios para invocar el procedimiento.
	 * @param as_userId Argumento de tipo <code>String</code> que contiene el usuario que realiza opeación.
	 * @param as_remoteIp Argumento de tipo <code>String</code> que contiene la ip desde donde se realiza opeación.
	 * @throws B2BException
	 */
	public synchronized void procCopiaDefinitivoTemporal(
	    TurnoHistoria ath_turnoHistoria, String as_userId, String as_remoteIp
	)
	    throws B2BException
	{
		DAOManager ldm_manager;

		ldm_manager = DaoManagerFactory.getDAOManager();

		try
		{
			if(ath_turnoHistoria != null)
			{
				ath_turnoHistoria = DaoCreator.getTurnoHistoriaDAO(ldm_manager).findById(ath_turnoHistoria);

				if(ath_turnoHistoria != null)
				{
					Collection<AccPredioRegistro> lcapr_predios;

					lcapr_predios = DaoCreator.getAccPredioRegistroDAO(ldm_manager)
							                      .findAllByTurnoHistoria(ath_turnoHistoria.getIdTurnoHistoria());

					if(!CollectionUtils.isValidCollection(lcapr_predios))
					{
						ath_turnoHistoria.setIdUsuarioModificacion(as_userId);
						ath_turnoHistoria.setIpModificacion(as_remoteIp);

						DaoCreator.getProcedimientosDAO(ldm_manager).procCopiaDefinitivoTemporal(ath_turnoHistoria);
					}
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("procCopiaDefinitivoTemporal", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}
	}

	/**
	 * Método encargado para ejecutar el procedimiento PROC_COPIA_ANOTACION_TEMPORAL  para copiar información de las anotaciones a las matrículas seleccionadas.
	 *
	 * @param aorc_rc  Objeto para extraer información necesaria para ejecutar el procedimiento
	 * @return
	 * @throws B2BException
	 */
	public synchronized RegistroCalificacion procCopiarAnotaciones(RegistroCalificacion aorc_rc)
	    throws B2BException
	{
		DAOManager           ldm_manager;
		RegistroCalificacion lrc_return;

		lrc_return      = new RegistroCalificacion();
		ldm_manager     = DaoManagerFactory.getDAOManager();

		try
		{
			if(aorc_rc != null)
			{
				boolean            lb_opcion;
				String             ls_idTurno;
				String             ls_idAnotacionPredio;
				ProcedimientosDAO  lop_DAO;
				AnotacionPredioDAO lap_DAO;
				TurnoHistoriaDAO   lth_DAO;

				lb_opcion                = aorc_rc.isRevision();
				ls_idTurno               = aorc_rc.getTurno();
				ls_idAnotacionPredio     = aorc_rc.getIdAnotacionPredio();
				lop_DAO                  = DaoCreator.getProcedimientosDAO(ldm_manager);
				lap_DAO                  = DaoCreator.getAnotacionPredioDAO(ldm_manager);
				lth_DAO                  = DaoCreator.getTurnoHistoriaDAO(ldm_manager);

				if(lb_opcion)
				{
					Collection<RegistroCalificacion> lcrc_rc;
					lcrc_rc = aorc_rc.getAllMatriculas();

					if(CollectionUtils.isValidCollection(lcrc_rc))
					{
						for(RegistroCalificacion lorc_tmp : lcrc_rc)
						{
							if(lorc_tmp != null)
							{
								if(lorc_tmp.isRevision())
								{
									String[] las_partes;

									lorc_tmp.setIdAnotacionPredio(ls_idAnotacionPredio);
									lorc_tmp.setTurno(ls_idTurno);
									las_partes = lorc_tmp.getIdCirculo().split("-");
									lorc_tmp.setIdCirculo(las_partes[0]);
									lorc_tmp.setIdMatricula(NumericUtils.getLongWrapper(las_partes[1]));
									lorc_tmp.setIdUsuarioModificacion(aorc_rc.getIdUsuarioModificacion());
									lorc_tmp.setIpModificacion(aorc_rc.getIpModificacion());

									lop_DAO.proceCopiarAnotacionTemporal(lorc_tmp);
								}
							}
						}
					}
				}
				else
				{
					AnotacionPredio lap_anotacionPredio;
					lap_anotacionPredio = lap_DAO.findByIdAnotacioPredio(ls_idAnotacionPredio);

					if(lap_anotacionPredio != null)
					{
						TurnoHistoria lth_turnoHistoria;
						lth_turnoHistoria = lth_DAO.findById(
							    NumericUtils.getLongWrapper(lap_anotacionPredio.getIdTurnoHistoria())
							);

						if(lth_turnoHistoria != null)
						{
							if(
							    NumericUtils.getLong(lth_turnoHistoria.getIdEtapa()) == EtapaCommon.ID_ETAPA_CALIFICACION
							)
								ls_idTurno = lap_anotacionPredio.getRadicacion();

							lrc_return.setIdAnotacionPredio(ls_idAnotacionPredio);
							lrc_return.setTurno(ls_idTurno);
							lrc_return.setIdUsuarioModificacion(aorc_rc.getIdUsuarioModificacion());
							lrc_return.setIpModificacion(aorc_rc.getIpModificacion());

							lop_DAO.proceCopiarAnotacionTemporal(lrc_return);
						}
					}
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("procCopiarAnotaciones", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lrc_return;
	}

	/**
	 * Método para salvar las anotaciones
	 *
	 * @param aa_anotacion
	 *            Objeto Anotacion para obtener la información de las anotaciones a
	 *            guardar
	 * @param as_usuario
	 *            String con usuario en sesión
	 * @param as_ipRemota
	 *            String con Ip de usuario en sesión
	 * @return
	 * @throws B2BException
	 */
	public synchronized Anotacion salvarAnotaciones(Anotacion aa_anotacion, String as_usuario, String as_ipRemota)
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

				lca_anotacionesAgregadas     = aa_anotacion.getAnotacionesAgregadas();
				lth_turnoHistoria            = aa_anotacion.getTurnoHistoria();
				lapr_accPredioRegistro       = aa_anotacion.getAccPredioRegistro();

				if(CollectionUtils.isValidCollection(lca_anotacionesAgregadas) && (lth_turnoHistoria != null))
				{
					TurnoHistoriaDAO lthd_DAO;

					lthd_DAO     = DaoCreator.getTurnoHistoriaDAO(ldm_manager);

					lth_turnoHistoria = lthd_DAO.findById(lth_turnoHistoria);

					if((lth_turnoHistoria != null) && (lapr_accPredioRegistro != null))
					{
						String ls_idSolicitud;
						String ls_idTurno;
						String ls_idCirculo;
						Long   ll_idMatricula;
						Long   ll_idMatriculaP;
						Long   ll_idTurnoHistoria;
						long   ll_idTurnoHistoriaP;

						com.bachue.snr.prosnr01.dao.acc.AnotacionPredioDAO          lapd_DAO;
						DocumentoDAO                                                ldd_DAO;
						NaturalezaJuridicaDAO                                       lnjd_DAO;
						PersonaDAO                                                  lpd_DAO;
						com.bachue.snr.prosnr01.dao.acc.AnotacionPredioCiudadanoDAO lapcd_DAO;
						AccPredioSegregadoDAO                                       lapsd_DAO;
						TipoActoDAO                                                 ltad_DAO;

						ls_idSolicitud          = lth_turnoHistoria.getIdSolicitud();
						ls_idTurno              = lth_turnoHistoria.getIdTurno();
						ll_idTurnoHistoria      = lth_turnoHistoria.getIdTurnoHistoria();
						ll_idTurnoHistoriaP     = NumericUtils.getLong(ll_idTurnoHistoria);
						ls_idCirculo            = lapr_accPredioRegistro.getIdCirculo();
						ll_idMatricula          = lapr_accPredioRegistro.getIdMatricula();

						lapd_DAO      = DaoCreator.getAccAnotacionPredioDAO(ldm_manager);
						ldd_DAO       = DaoCreator.getDocumentoDAO(ldm_manager);
						lnjd_DAO      = DaoCreator.getNaturalezaJuridicaDAO(ldm_manager);
						lpd_DAO       = DaoCreator.getPersonaDAO(ldm_manager);
						lapcd_DAO     = DaoCreator.getAccAnotacionPredioCiudadanoDAO(ldm_manager);
						lapsd_DAO     = DaoCreator.getAccPredioSegregadoDAO(ldm_manager);
						ltad_DAO      = DaoCreator.getTipoActoDAO(ldm_manager);

						if(!StringUtils.isValidString(ls_idSolicitud))
						{
							Object[] loa_messageArgs = new String[1];
							loa_messageArgs[0] = StringUtils.getString(lth_turnoHistoria.getIdTurnoHistoria());
							throw new B2BException(ErrorKeys.NO_SE_ENCONTRO_SOLICITUD_TURNO_HISTORIA, loa_messageArgs);
						}

						if(!StringUtils.isValidString(ls_idTurno))
						{
							Object[] loa_messageArgs = new String[1];
							loa_messageArgs[0] = StringUtils.getString(lth_turnoHistoria.getIdTurnoHistoria());
							throw new B2BException(ErrorKeys.NO_SE_ENCONTRO_TURNO_TURNO_HISTORIA, loa_messageArgs);
						}

						if(!StringUtils.isValidString(ls_idCirculo))
							throw new B2BException(ErrorKeys.ERROR_NO_CIRCULO_REGISTRAL);

						if(!NumericUtils.isValidLong(ll_idMatricula))
							throw new B2BException(ErrorKeys.ERROR_NO_MATRICULA);

						ll_idMatriculaP = ll_idMatricula;

						for(Anotacion la_iterador : lca_anotacionesAgregadas)
						{
							if(la_iterador != null)
							{
								com.bachue.snr.prosnr01.model.sdb.acc.AnotacionPredio lap_anotacionPredio;
								Documento                                             ld_documento;
								long                                                  ll_idAnotacion;
								String                                                ls_idAnotacionPredio;
								Collection<RegistroCalificacion>                      lcrc_anotacionesACancelar;

								lap_anotacionPredio           = la_iterador.getAnotacionPredio();
								ld_documento                  = la_iterador.getDocumento();
								ll_idAnotacion                = la_iterador.getIdAnotacion();
								lcrc_anotacionesACancelar     = la_iterador.getAnotacionACancelarNuevo();
								ls_idAnotacionPredio          = null;

								if(lap_anotacionPredio != null)
								{
									ls_idAnotacionPredio = lap_anotacionPredio.getIdAnotacionPredio();

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

								if(ld_documento != null)
								{
									{
										String ls_tmp;
										ls_tmp = ld_documento.getIdTipoDocumento();

										if(!StringUtils.isValidString(ls_tmp))
											throw new B2BException(ErrorKeys.TIPO_DOCUMENTO_INVALIDO);
									}

									{
										String ls_tmp;
										ls_tmp = ld_documento.getNumero();

										if(!StringUtils.isValidString(ls_tmp))
											throw new B2BException(ErrorKeys.DOCUMENTO_INVALIDO);
									}

									{
										Date ld_tmp;
										ld_tmp = ld_documento.getFechaDocumento();

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
										ls_tmp = ld_documento.getIdTipoOficina();

										if(!StringUtils.isValidString(ls_tmp))
											throw new B2BException(ErrorKeys.DEBE_SELECCIONAR_TIPO_OFICINA);
									}

									{
										String ls_tmp;
										ls_tmp = ld_documento.getIdPais();

										if(!StringUtils.isValidString(ls_tmp))
											throw new B2BException(ErrorKeys.DEBE_SELECCIONAR_PAIS);
									}

									{
										String ls_tmp;
										ls_tmp = ld_documento.getIdDepartamento();

										if(!StringUtils.isValidString(ls_tmp))
											throw new B2BException(ErrorKeys.DEBE_DIGITAR_DEPARTAMENTO);
									}

									{
										String ls_tmp;
										ls_tmp = ld_documento.getIdMunicipio();

										if(!StringUtils.isValidString(ls_tmp))
											throw new B2BException(ErrorKeys.DEBE_SELECCIONAR_MUNICIPIO);
									}

									{
										String ls_tmp;
										ls_tmp = ld_documento.getIdOficinaOrigen();

										if(!StringUtils.isValidString(ls_tmp))
											throw new B2BException(ErrorKeys.DEBE_SELECCIONAR_OFICINA_ORIGEN);

										ld_documento.setVersion(obtenerVersionMaximaOficinaOrigen(ls_tmp, ldm_manager));
									}

									{
										Documento ld_tmp;
										ld_tmp = ldd_DAO.consultaDocumento(ld_documento);

										if(ld_tmp != null)
										{
											String ls_idDocumento;

											ls_idDocumento = ld_tmp.getIdDocumento();

											if(!StringUtils.isValidString(ls_idDocumento))
												throw new B2BException(ErrorKeys.ERROR_SIN_ID_DOCUMENTO);

											ld_documento.setIdDocumento(ls_idDocumento);

											{
												Long ll_versionDocumento;
												ll_versionDocumento = ld_documento.getVersionDocumento();

												if(NumericUtils.isValidLong(ll_versionDocumento))
													ld_documento.setVersionDocumento(ll_versionDocumento);
												else
													ld_documento.setVersionDocumento(NumericUtils.getLongWrapper(1L));
											}
										}
										else
										{
											int li_secuencia;
											li_secuencia = ldd_DAO.findSecuencia();

											if(li_secuencia > 0)
											{
												ld_documento.setIdDocumento(StringUtils.getString(li_secuencia));
												ld_documento.setVersionDocumento(NumericUtils.getLongWrapper(1L));
											}
											else
												throw new B2BException(ErrorKeys.BGN_DOCUMENTO_INSERT);

											ld_documento.setIdUsuarioCreacion(as_usuario);
											ld_documento.setIpCreacion(as_ipRemota);

											ldd_DAO.insertOrUpdate(ld_documento, true);
										}
									}
								}

								if((lap_anotacionPredio != null) && !StringUtils.isValidString(ls_idAnotacionPredio))
								{
									lap_anotacionPredio.setIdCirculo(ls_idCirculo);
									lap_anotacionPredio.setIdMatricula(ll_idMatriculaP);

									{
										String ls_tmp;
										ls_tmp = lap_anotacionPredio.getIdNaturalezaJuridica();

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

											if(
											    StringUtils.isValidString(ls_idNaturalezaJuridica)
												    && StringUtils.isValidString(ls_version)
											)
											{
												lap_anotacionPredio.setIdNaturalezaJuridica(ls_idNaturalezaJuridica);
												lap_anotacionPredio.setVersion(NumericUtils.getLong(ls_version));

												NaturalezaJuridica lnj_parametros;
												lnj_parametros = new NaturalezaJuridica();

												lnj_parametros.setIdNaturalezaJuridica(ls_idNaturalezaJuridica);
												lnj_parametros.setVersion(NumericUtils.getLong(ls_version));

												lnj_parametros = lnjd_DAO.findById(lnj_parametros);

												if(lnj_parametros != null)
												{
													{
														TipoActo lta_tipoActo;
														lta_tipoActo = new TipoActo();

														lta_tipoActo.setIdTipoActo(ls_idNaturalezaJuridica);

														lta_tipoActo = ltad_DAO.findById(lta_tipoActo);
													}

													if(CollectionUtils.isValidCollection(lcrc_anotacionesACancelar))
													{
														AnotacionCancelacion    loac_tmp;
														AnotacionCancelacionDAO loacd_DAO;
														loacd_DAO = DaoCreator.getAnotacionCancelacionDAO(ldm_manager);

														for(RegistroCalificacion lorc_rc : lcrc_anotacionesACancelar)
														{
															if(lorc_rc != null)
															{
																loac_tmp = new AnotacionCancelacion();

																if(lorc_rc.isRevision())
																{
																	loac_tmp.setIdSolicitud(ls_idSolicitud);
																	loac_tmp.setIdMatricula(
																	    NumericUtils.getLong(ll_idMatricula)
																	);
																	loac_tmp.setIdCirculo(ls_idCirculo);
																	loac_tmp.setIdAnotacion(
																	    lap_anotacionPredio.getIdAnotacion()
																	);
																	loac_tmp.setIdAnotacion1(lorc_rc.getIdAnotacion());
																	loac_tmp.setIdUsuarioCreacion(as_usuario);
																	loac_tmp.setIpCreacion(as_ipRemota);
																	loac_tmp.setIdTurno(ls_idTurno);
																	loac_tmp.setIdTurnoHistoria(ll_idTurnoHistoriaP);

																	loacd_DAO.insert(loac_tmp);
																}
															}
														}
													}
												}
											}
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

									lap_anotacionPredio.setIdEstadoRegistro(EstadoCommon.T);
									lap_anotacionPredio.setIdUsuarioCreacion(as_usuario);
									lap_anotacionPredio.setIpCreacion(as_ipRemota);

									lap_anotacionPredio = lapd_DAO.insert(lap_anotacionPredio);

									if(lap_anotacionPredio != null)
									{
										ls_idAnotacionPredio = lap_anotacionPredio.getIdAnotacionPredio();
										lap_anotacionPredio.setIdUsuarioModificacion(as_usuario);
										lap_anotacionPredio.setIpModificacion(as_ipRemota);
										lap_anotacionPredio.setIndicadorPredioCiudadano(EstadoCommon.S);

										lapd_DAO.modificarIndicador(lap_anotacionPredio);
									}
								}

								{
									Collection<Anotacion> lca_intervinientes;

									lca_intervinientes = la_iterador.getIntervinientesAgregados();

									if(CollectionUtils.isValidCollection(lca_intervinientes))
									{
										lapcd_DAO.deleteIntervinientes(ls_idAnotacionPredio);

										for(Anotacion la_interviniente : lca_intervinientes)
										{
											if(la_interviniente != null)
											{
												Persona                lp_persona;
												SolicitudInterviniente lsi_solicitudInterviniente;

												lp_persona                     = la_interviniente.getPersona();
												lsi_solicitudInterviniente     = la_interviniente
														.getSolicitudInterviniente();

												if(lp_persona != null)
												{
													String ls_tipoPersona;
													String ls_tipoDocumento;

													ls_tipoPersona       = lp_persona.getIdTipoPersona();
													ls_tipoDocumento     = lp_persona.getIdDocumentoTipo();

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
														    !ls_tipoDocumento.equalsIgnoreCase(
															        IdentificadoresCommon.NIT
															    ) && !ls_tipoDocumento.equalsIgnoreCase("-1")
														)
														{
															{
																String ls_nacionalidad;
																ls_nacionalidad = lp_persona.getIdPais();

																if(!StringUtils.isValidString(ls_nacionalidad))
																	throw new B2BException(
																	    ErrorKeys.DEBE_SELECCIONAR_NACIONALIDAD
																	);
															}

															{
																String ls_primerNombre;
																ls_primerNombre = lp_persona.getPrimerNombre();

																if(!StringUtils.isValidString(ls_primerNombre))
																	throw new B2BException(
																	    ErrorKeys.DEBE_DIGITAR_PRIMER_NOMBRE
																	);
															}

															{
																String ls_primerApellido;
																ls_primerApellido = lp_persona.getPrimerApellido();

																if(!StringUtils.isValidString(ls_primerApellido))
																	throw new B2BException(
																	    ErrorKeys.DEBE_DIGITAR_PRIMER_APELLIDO
																	);
															}

															if(lsi_solicitudInterviniente != null)
															{
																String ls_rol;
																ls_rol = lsi_solicitudInterviniente.getRolPersona();

																if(!StringUtils.isValidString(ls_rol))
																	throw new B2BException(
																	    ErrorKeys.DEBE_SELECCIONAR_ROL
																	);
															}

															{
																String ls_genero;
																ls_genero = lp_persona.getIdNaturalGenero();

																if(!StringUtils.isValidString(ls_genero))
																	throw new B2BException(
																	    ErrorKeys.ERROR_REVISE_INTERVINIENTES_GENERO
																	);
															}
														}
														else
															throw new B2BException(ErrorKeys.DEBE_SELECCIONAR_NATURAL);
													}

													if(ls_tipoPersona.equalsIgnoreCase(EstadoCommon.J))
													{
														if(ls_tipoDocumento.equalsIgnoreCase(IdentificadoresCommon.NIT))
														{
															{
																String ls_nacionalidad;
																ls_nacionalidad = lp_persona.getIdPais();

																if(!StringUtils.isValidString(ls_nacionalidad))
																	throw new B2BException(
																	    ErrorKeys.DEBE_SELECCIONAR_NACIONALIDAD
																	);
															}

															if(lsi_solicitudInterviniente != null)
															{
																String ls_rol;
																ls_rol = lsi_solicitudInterviniente.getRolPersona();

																if(!StringUtils.isValidString(ls_rol))
																	throw new B2BException(
																	    ErrorKeys.DEBE_SELECCIONAR_ROL
																	);
															}

															{
																String ls_razonSocial;
																ls_razonSocial = lp_persona.getRazonSocial();

																if(!StringUtils.isValidString(ls_razonSocial))
																	throw new B2BException(
																	    ErrorKeys.DEBE_DIGITAR_RAZON_SOCIAL
																	);
															}
														}
														else
															throw new B2BException(ErrorKeys.DEBE_SELECCIONAR_JURIDICO);
													}

													{
														com.bachue.snr.prosnr01.model.sdb.acc.AnotacionPredioCiudadano lapc_anotacionPredioCiudadano;
														String                                                         ls_idPersona;

														ls_idPersona                      = lp_persona.getIdPersona();
														lapc_anotacionPredioCiudadano     = la_interviniente
																.getAnotacionPredioCiudadano();

														if(
														    lp_persona.getIdDocumentoTipo()
															              .equalsIgnoreCase(EstadoCommon.SE)
														)
														{
															Constantes lc_constante;
															lc_constante = new Constantes();
															lc_constante.setIdConstante(
															    ConstanteCommon.CONSECUTIVO_TIPO_DOCUMENTO_SECUENCIA
															);
															lc_constante = DaoCreator.getConstantesDAO(ldm_manager)
																	                     .findById(lc_constante);

															if(lc_constante != null)
															{
																BigInteger lbi_secuencia;
																lbi_secuencia = lc_constante.getEntero()
																		                        .add(
																		    BigInteger.valueOf(1)
																		);

																lc_constante.setEntero(lbi_secuencia);
																DaoCreator.getConstantesDAO(ldm_manager)
																	          .insertOrUpdate(lc_constante, false);

																lp_persona.setNumeroDocumento(
																    StringUtils.getString(lbi_secuencia)
																);
															}
														}

														String ls_idFlagPersona;

														ls_idFlagPersona = marcarFlagPersona(
															    ldm_manager, lp_persona, as_usuario, as_ipRemota
															);

														if(StringUtils.isValidString(ls_idFlagPersona))
															lp_persona = lpd_DAO.findById(ls_idFlagPersona);

														Persona lp_temp;

														lp_temp     = new Persona();

														lp_temp = lpd_DAO.findDataCalificador(lp_persona);

														if(lp_temp != null)
														{
															lp_temp          = lpd_DAO.findById(lp_temp);
															ls_idPersona     = lp_temp.getIdPersona();
														}

														if(
														    (lapc_anotacionPredioCiudadano != null)
															    && !StringUtils.isValidString(
															        lapc_anotacionPredioCiudadano
															        .getIdAnotacionPredioCiudadano()
															    )
														)
														{
															String ls_rol;

															ls_rol = lsi_solicitudInterviniente.getRolPersona();

															lapc_anotacionPredioCiudadano.setIdAnotacionPredio(
															    ls_idAnotacionPredio
															);
															lapc_anotacionPredioCiudadano.setIdCirculo(ls_idCirculo);
															lapc_anotacionPredioCiudadano.setIdMatricula(
															    NumericUtils.getLong(ll_idMatriculaP)
															);
															lapc_anotacionPredioCiudadano.setIdAnotacion(
															    ll_idAnotacion
															);
															lapc_anotacionPredioCiudadano.setIdPersona(ls_idPersona);
															lapc_anotacionPredioCiudadano.setRolPersona(ls_rol);

															com.bachue.snr.prosnr01.model.sdb.acc.AnotacionPredioCiudadano lapc_temp;

															lapc_temp = lapcd_DAO.findByAnotPredioCirMatAnotPerRol(
																    lapc_anotacionPredioCiudadano
																);

															if(lapc_temp != null)
															{
																Object[] loa_messageArgs = new String[1];
																loa_messageArgs[0] = ls_rol;

																throw new B2BException(
																    ErrorKeys.ERROR_INTERVINIENTE_REPETIDO,
																    loa_messageArgs
																);
															}

															lapc_anotacionPredioCiudadano.setIdUsuarioCreacion(
															    as_usuario
															);
															lapc_anotacionPredioCiudadano.setIpCreacion(as_ipRemota);
															lapc_anotacionPredioCiudadano.setIdTurno(ls_idTurno);

															lapcd_DAO.insert(lapc_anotacionPredioCiudadano);
														}
													}
												}
											}
										}
									}
								}

								{
									Collection<Anotacion> lca_matriculasSegregadas;
									lca_matriculasSegregadas = la_iterador.getMatriculasSegregadas();

									if(CollectionUtils.isValidCollection(lca_matriculasSegregadas))
									{
										for(Anotacion la_matriculas : lca_matriculasSegregadas)
										{
											if(la_matriculas != null)
											{
												PredioRegistro lpr_predioRegistro;
												lpr_predioRegistro = la_matriculas.getPredioRegistro();

												if(lpr_predioRegistro != null)
												{
													String ls_idCirculoSegregada;
													long   lbi_idMatriculaSegregada;

													ls_idCirculoSegregada        = lpr_predioRegistro.getIdCirculo();
													lbi_idMatriculaSegregada     = lpr_predioRegistro.getIdMatricula();

													{
														PredioSegregado lps_predioSegregado;
														lps_predioSegregado = new PredioSegregado();

														lps_predioSegregado.setIdCirculo(ls_idCirculo);
														lps_predioSegregado.setIdMatricula(
														    NumericUtils.getLong(ll_idMatriculaP)
														);

														lps_predioSegregado.setIdCirculo1(ls_idCirculoSegregada);
														lps_predioSegregado.setIdMatricula1(lbi_idMatriculaSegregada);

														lps_predioSegregado.setIdUsuarioCreacion(as_usuario);
														lps_predioSegregado.setIpCreacion(as_ipRemota);

														lapsd_DAO.insert(lps_predioSegregado);
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
	 * Método encargado de salvar la información de anotaciones para el proceso de englobes.
	 *
	 * @param arc_arg Objeto que contiene los datos a guardar.
	 * @throws B2BException
	 */
	public synchronized void salvarAnotacionesEnglobes(RegistroCalificacion arc_arg)
	    throws B2BException
	{
		DAOManager ldm_manager;

		ldm_manager = DaoManagerFactory.getDAOManager();

		if(arc_arg != null)
		{
			try
			{
				Long ll_idTurnoHistoria;

				ll_idTurnoHistoria = arc_arg.getIdTurnoHistoria();

				if(NumericUtils.isValidLong(ll_idTurnoHistoria))
				{
					TurnoHistoria    lth_turnoHistoria;
					TurnoHistoriaDAO lth_turnoHistoria_DAO;

					lth_turnoHistoria         = new TurnoHistoria();
					lth_turnoHistoria_DAO     = DaoCreator.getTurnoHistoriaDAO(ldm_manager);

					lth_turnoHistoria.setIdTurnoHistoria(ll_idTurnoHistoria);

					lth_turnoHistoria = lth_turnoHistoria_DAO.findById(lth_turnoHistoria);

					if(lth_turnoHistoria != null)
					{
						RegistroCalificacion lrc_dataAnotacionesHerdar;

						lrc_dataAnotacionesHerdar = arc_arg.getMatriculasAHeredar();

						if(lrc_dataAnotacionesHerdar != null)
						{
							Collection<RegistroCalificacion> lcrc_matriculasHeredar;

							lcrc_matriculasHeredar = lrc_dataAnotacionesHerdar.getAllMatriculas();

							if(CollectionUtils.isValidCollection(lcrc_matriculasHeredar))
							{
								Long   ll_idMatricula;
								String ls_idCirculo;

								ll_idMatricula     = arc_arg.getIdMatricula();
								ls_idCirculo       = arc_arg.getIdCirculo();

								if(StringUtils.isValidString(ls_idCirculo) && NumericUtils.isValidLong(ll_idMatricula))
								{
								}

								AnotacionPredioCiudadanoDAO                                 lapc_DAO;
								com.bachue.snr.prosnr01.dao.acc.AnotacionPredioDAO          lapd_DAO;
								com.bachue.snr.prosnr01.dao.acc.AnotacionPredioCiudadanoDAO loaapc_DAO;
								boolean                                                     lb_devolucion;
								Collection<AnotacionPredioCiudadano>                        lcapc_apc;
								String                                                      ls_idTurno;
								String                                                      ls_ip;
								String                                                      ls_idUsuario;

								lapc_DAO          = DaoCreator.getAnotacionPredioCiudadanoDAO(ldm_manager);
								lapd_DAO          = DaoCreator.getAccAnotacionPredioDAO(ldm_manager);
								loaapc_DAO        = DaoCreator.getAccAnotacionPredioCiudadanoDAO(ldm_manager);
								lb_devolucion     = arc_arg.isDevolucion();
								lcapc_apc         = new ArrayList<AnotacionPredioCiudadano>();
								ls_idTurno        = arc_arg.getTurno();
								ls_ip             = arc_arg.getIpAddress();
								ls_idUsuario      = arc_arg.getUserId();

								if(!StringUtils.isValidString(ls_idTurno))
									ls_idTurno = lth_turnoHistoria.getIdTurno();

								for(RegistroCalificacion lrc_tmp : lcrc_matriculasHeredar)
								{
									if(lrc_tmp != null)
									{
										com.bachue.snr.prosnr01.model.sdb.acc.AnotacionPredio loap_ap;

										loap_ap = new com.bachue.snr.prosnr01.model.sdb.acc.AnotacionPredio();

										if(lrc_tmp.isRevision())
										{
											if(!lb_devolucion)
											{
												AnotacionPredioCiudadano                              lapc_apc;
												com.bachue.snr.prosnr01.model.sdb.acc.AnotacionPredio loap_identity;

												lapc_apc = new AnotacionPredioCiudadano();

												loap_ap.setIdTurnoHistoria(NumericUtils.getLong(ll_idTurnoHistoria));
												loap_ap.setIdEstadoRegistro(EstadoCommon.INACTIVO);
												loap_ap.setIdCirculo(lrc_tmp.getIdCirculo());
												loap_ap.setIdMatricula(lrc_tmp.getIdMatricula());
												loap_ap.setIdSolicitud(lth_turnoHistoria.getIdSolicitud());
												loap_ap.setIdAnotacion(lrc_tmp.getIdAnotacion());
												loap_ap.setFechaRegistro(lrc_tmp.getFechaRegistro());
												loap_ap.setIdDocumento(lrc_tmp.getIdDocumento());
												loap_ap.setIdNaturalezaJuridica(lrc_tmp.getCodActo());
												loap_ap.setVersion(1);
												loap_ap.setVersionDocumento(1);
												loap_ap.setIdTipoAnotacion(lrc_tmp.getIdTipoAnotacion());
												loap_ap.setFechaRadicacion(lrc_tmp.getFechaRadicacion());
												loap_ap.setRadicacion(lrc_tmp.getRadicacion());
												loap_ap.setIdEstadoAnotacion(lrc_tmp.getCodEstadoAnotacion());
												loap_ap.setIdUsuarioCreacion(ls_idUsuario);
												loap_ap.setIpCreacion(ls_ip);
												loap_ap.setIdTurno(ls_idTurno);

												loap_identity = lapd_DAO.insert(loap_ap);

												lapc_apc.setIdCirculo(loap_ap.getIdCirculo());
												lapc_apc.setIdMatricula(NumericUtils.getLong(loap_ap.getIdMatricula()));
												lapc_apc.setIdAnotacion(NumericUtils.getLong(loap_ap.getIdAnotacion()));

												lcapc_apc = lapc_DAO.consultaPredioCiudadanos(lapc_apc);

												if(
												    CollectionUtils.isValidCollection(lcapc_apc)
													    && (loap_identity != null)
												)
												{
													String ls_idAnotacionPredio;

													ls_idAnotacionPredio = loap_identity.getIdAnotacionPredio();

													if(StringUtils.isValidString(ls_idAnotacionPredio))
													{
														com.bachue.snr.prosnr01.model.sdb.acc.AnotacionPredioCiudadano loapc_tmp;

														for(AnotacionPredioCiudadano lopc_apc : lcapc_apc)
														{
															loapc_tmp = new com.bachue.snr.prosnr01.model.sdb.acc.AnotacionPredioCiudadano();
															loapc_tmp.setIdCirculo(lopc_apc.getIdCirculo());
															loapc_tmp.setIdMatricula(lopc_apc.getIdMatricula());
															loapc_tmp.setIdAnotacion(lopc_apc.getIdAnotacion());
															loapc_tmp.setIdPersona(lopc_apc.getIdPersona());
															loapc_tmp.setRolPersona(lopc_apc.getRolPersona());
															loapc_tmp.setPropietario(lopc_apc.getPropietario());
															loapc_tmp.setPorcentajeParticipacion(
															    lopc_apc.getPorcentajeParticipacion()
															);
															loapc_tmp.setIdInteresadaRrr(lopc_apc.getIdInteresadaRrr());
															loapc_tmp.setIdUsuarioCreacion(ls_idUsuario);
															loapc_tmp.setIpCreacion(ls_ip);
															loapc_tmp.setIdTurno(ls_idTurno);
															loapc_tmp.setIdAnotacionPredio(ls_idAnotacionPredio);

															loaapc_DAO.insert(loapc_tmp);
														}
													}
												}
											}
										}
										else if(lb_devolucion)
										{
											com.bachue.snr.prosnr01.model.sdb.acc.AnotacionPredio          loap_tmp;
											com.bachue.snr.prosnr01.model.sdb.acc.AnotacionPredioCiudadano loapc_tmp;

											loap_tmp      = new com.bachue.snr.prosnr01.model.sdb.acc.AnotacionPredio();
											loapc_tmp     = new com.bachue.snr.prosnr01.model.sdb.acc.AnotacionPredioCiudadano();

											loap_tmp.setIdAnotacionPredio(loap_ap.getIdAnotacionPredio());
											loapc_tmp.setIdAnotacionPredio(loap_ap.getIdAnotacionPredio());

											lapd_DAO.deleteById(loap_tmp);
											loaapc_DAO.deleteIntervinientes(loapc_tmp);
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

				clh_LOGGER.error("salvarAnotacionesEnglobes", lb2be_e);

				throw lb2be_e;
			}
			finally
			{
				ldm_manager.close();
			}
		}
	}

	/**
	 * Método encargado de guardar la información del área del predio.
	 *
	 * @param aaaui_data Objeto que contiene la información del área a guardar.
	 * @param as_idUsuario Varible de tipo String que contiene el usuario que está ejecutando la acción.
	 * @param as_remoteIp Variable de tipo String que contiene la ip del usuario que está ejecutando la acción.
	 * @throws B2BException
	 */
	public synchronized void salvarAreaPredio(AccAreaUI aaaui_data, String as_idUsuario, String as_remoteIp)
	    throws B2BException
	{
		DAOManager ldm_manager;

		ldm_manager = DaoManagerFactory.getDAOManager();

		try
		{
			salvarAreaPredio(aaaui_data, as_idUsuario, as_remoteIp, ldm_manager);
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
	}

	/**
	 * Método encargado de guardar la información del área predio para Englobes.
	 *
	 * @param aaaui_data Objeto que contiene la información del área a guardar.
	 * @param as_idUsuario Varible de tipo String que contiene el usuario que está ejecutando la acción.
	 * @param as_ip Variable de tipo String que contiene la ip del usuario que está ejecutando la acción.
	 * @throws B2BException
	 */
	public synchronized void salvarAreaPredioEnglobes(AccAreaUI aaaui_data, String as_idUsuario, String as_ip)
	    throws B2BException
	{
		DAOManager ldm_manager;

		ldm_manager = DaoManagerFactory.getDAOManager();

		try
		{
			salvarAreaPredio(aaaui_data, as_idUsuario, as_ip, ldm_manager);
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("salvarAreaPredioEnglobes", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}
	}

	/**
	 * Método encargado de salvar el área del predio para el proceso de Venta parcial.
	 *
	 * @param aaaui_data Objeto que contiene la información del área a guardar.
	 * @param as_idUsuario Variable de tipo String que contiene el id del usuario que está realizando el proceso.
	 * @param as_ip Variable de tipo String que contiene la ip del usuario que está realizando el proceso.
	 * @throws B2BException
	 */
	public synchronized void salvarAreaPredioVenta(AccAreaUI aaaui_data, String as_idUsuario, String as_ip)
	    throws B2BException
	{
		DAOManager ldm_manager;

		ldm_manager = DaoManagerFactory.getDAOManager();

		try
		{
			if(aaaui_data != null)
			{
				Collection<DetalleAreaPredio> lcadap_areasTerrenoData;

				lcadap_areasTerrenoData = aaaui_data.getAreasTerreno();

				if(CollectionUtils.isValidCollection(lcadap_areasTerrenoData))
				{
					AccAreaPredio     laap_areaPredioData;
					DetalleAreaPredio ladap_areaConstruidaData;
					DetalleAreaPredio ladap_areaPrivadaData;

					laap_areaPredioData          = aaaui_data.getAreaPredio();
					ladap_areaConstruidaData     = aaaui_data.getDetalleAreaConstruida();
					ladap_areaPrivadaData        = aaaui_data.getDetalleAreaPrivada();

					if(laap_areaPredioData != null)
					{
						String ls_tipoUso;

						ls_tipoUso = laap_areaPredioData.getTipoSuelo();

						if(!StringUtils.isValidString(ls_tipoUso))
							throw new B2BException(ErrorKeys.USO_DEL_PREDIO_INVALIDO_SELECCIONAR);
					}

					{
						String ls_idCirculo;
						Long   ll_idMatricula;

						ls_idCirculo       = aaaui_data.getIdCirculo();
						ll_idMatricula     = aaaui_data.getIdMatricula();

						if(StringUtils.isValidString(ls_idCirculo) && NumericUtils.isValidLong(ll_idMatricula))
						{
							Constantes lc_constante;
							Long       ll_idAnotacion;

							lc_constante       = DaoCreator.getConstantesDAO(ldm_manager)
									                           .findById(
									    ConstanteCommon.CODIGOS_ACTOS_DECLARACION_PARTE_RESTANTE
									);
							ll_idAnotacion     = null;

							if(lc_constante != null)
							{
								String ls_ventaParcial;

								ls_ventaParcial = lc_constante.getCaracter();

								if(StringUtils.isValidString(ls_ventaParcial))
								{
									com.bachue.snr.prosnr01.model.sdb.acc.AnotacionPredio lap_anotacion;

									lap_anotacion = new com.bachue.snr.prosnr01.model.sdb.acc.AnotacionPredio();

									lap_anotacion.setIdNaturalezaJuridica(ls_ventaParcial);
									lap_anotacion.setIdCirculo(ls_idCirculo);
									lap_anotacion.setIdMatricula(ll_idMatricula);

									lap_anotacion = DaoCreator.getAccAnotacionPredioDAO(ldm_manager)
											                      .findByCirculoMatriculaActo(lap_anotacion);

									if(lap_anotacion != null)
										ll_idAnotacion = lap_anotacion.getIdAnotacion();
								}
							}

							if(!NumericUtils.isValidLong(ll_idAnotacion))
								throw new B2BException(ErrorKeys.DEBE_DIGITAR_ANOTACION_PARTE_RESTANTE);

							AccAreaPredio           laap_areaPredio;
							AccAreaPredioDAO        laap_DAO;
							AccDetalleAreaPredioDAO ladap_DAO;

							laap_areaPredio     = new AccAreaPredio();
							laap_DAO            = DaoCreator.getAccAreaPredioDAO(ldm_manager);
							ladap_DAO           = DaoCreator.getAccDetalleAreaPredioDAO(ldm_manager);

							laap_areaPredio.setIdCirculo(ls_idCirculo);
							laap_areaPredio.setIdMatricula(ll_idMatricula);
							laap_areaPredio.setIdAnotacion(ll_idAnotacion);

							laap_areaPredio = laap_DAO.findByCirculoMatriculaAnotacion(laap_areaPredio);

							if(laap_areaPredio != null)
							{
								DetalleAreaPredio                  ladap_param;
								HashMap<String, DetalleAreaPredio> lhsadap_areas;
								String                             ls_idAreaPredio;

								ladap_param         = new DetalleAreaPredio();
								ls_idAreaPredio     = laap_areaPredio.getIdArea();

								ladap_param.setIdAreaPredio(ls_idAreaPredio);
								ladap_param.setIdCirculo(laap_areaPredio.getIdCirculo());
								ladap_param.setIdMatricula(laap_areaPredio.getIdMatricula());
								ladap_param.setIdTipoArea(TipoAreaCommon.TERRENO);

								lhsadap_areas = ladap_DAO.findAllByIdAreaPredioTipoDetalle(ladap_param);

								if(CollectionUtils.isValidCollection(lhsadap_areas))
								{
									HashMap<String, DetalleAreaPredio> lhsadap_areasInsert;
									Iterator<DetalleAreaPredio>        liadap_iterator;

									lhsadap_areasInsert     = new HashMap<String, DetalleAreaPredio>();
									liadap_iterator         = lcadap_areasTerrenoData.iterator();

									while(liadap_iterator.hasNext())
									{
										DetalleAreaPredio ladap_areaTerreno;

										ladap_areaTerreno = liadap_iterator.next();

										if(ladap_areaTerreno != null)
										{
											String ls_idDetalleAreaPredio;

											ls_idDetalleAreaPredio = ladap_areaTerreno.getIdDetalleAreaPredio();

											if(StringUtils.isValidString(ls_idDetalleAreaPredio))
											{
												if(lhsadap_areas.containsKey(ls_idDetalleAreaPredio))
												{
													DetalleAreaPredio ladap_db;

													ladap_db = lhsadap_areas.get(ls_idDetalleAreaPredio);

													if(ladap_db != null)
													{
														boolean lb_update;
														Double  ld_area;
														Double  ld_areaDB;
														String  ls_unidadMedida;
														String  ls_unidadMedidaDB;

														lb_update             = false;
														ld_area               = ladap_areaTerreno.getArea();
														ld_areaDB             = ladap_db.getArea();
														ls_unidadMedida       = ladap_areaTerreno.getIdUnidadMedida();
														ls_unidadMedidaDB     = ladap_db.getIdUnidadMedida();

														if(
														    StringUtils.isValidString(ls_unidadMedida)
															    && StringUtils.isValidString(ls_unidadMedidaDB)
														)
														{
															if(!ls_unidadMedida.equalsIgnoreCase(ls_unidadMedidaDB))
																lb_update = true;
														}

														if(
														    NumericUtils.isValidDouble(ld_area)
															    && NumericUtils.isValidDouble(ld_areaDB)
														)
														{
															if(ld_area.compareTo(ld_areaDB) != 0)
																lb_update = true;
														}

														if(lb_update)
														{
															ladap_db.setArea(ld_area);
															ladap_db.setIdUnidadMedida(ls_unidadMedida);
															ladap_db.setIdUsuarioModificacion(as_idUsuario);
															ladap_db.setIpModificacion(as_ip);

															ladap_DAO.insertOrUpdate(ladap_db, false);
														}
													}
												}
												else
												{
													ladap_areaTerreno.setIdAreaPredio(ls_idAreaPredio);
													ladap_areaTerreno.setIdCirculo(ls_idCirculo);
													ladap_areaTerreno.setIdMatricula(ll_idMatricula);
													ladap_areaTerreno.setIdUsuarioCreacion(as_idUsuario);
													ladap_areaTerreno.setIpCreacion(as_ip);

													ladap_DAO.insertOrUpdate(ladap_areaTerreno, true);
												}

												lhsadap_areasInsert.put(ls_idDetalleAreaPredio, ladap_areaTerreno);
											}
										}
									}

									for(Map.Entry<String, DetalleAreaPredio> lhm_iterador : lhsadap_areas.entrySet())
									{
										DetalleAreaPredio ladap_detalleAreaPredioMap;
										String            ls_idDetalleAreaMap;

										ladap_detalleAreaPredioMap     = lhm_iterador.getValue();
										ls_idDetalleAreaMap            = lhm_iterador.getKey();

										if(StringUtils.isValidString(ls_idDetalleAreaMap))
										{
											if(
											    !lhsadap_areasInsert.containsKey(ls_idDetalleAreaMap)
												    && (ladap_detalleAreaPredioMap != null)
											)
												ladap_DAO.delete(ladap_detalleAreaPredioMap);
										}
									}
								}
								else
								{
									String ls_idAccAreaPredio;

									ls_idAccAreaPredio = laap_areaPredio.getIdArea();

									if(StringUtils.isValidString(ls_idAreaPredio))
									{
										Iterator<DetalleAreaPredio> liadap_iterator;

										liadap_iterator = lcadap_areasTerrenoData.iterator();

										while(liadap_iterator.hasNext())
										{
											DetalleAreaPredio ladap_areaTerreno;

											ladap_areaTerreno = liadap_iterator.next();

											if(ladap_areaTerreno != null)
											{
												ladap_areaTerreno.setIdAreaPredio(ls_idAccAreaPredio);
												ladap_areaTerreno.setIdCirculo(ls_idCirculo);
												ladap_areaTerreno.setIdMatricula(ll_idMatricula);
												ladap_areaTerreno.setIdUsuarioCreacion(as_idUsuario);
												ladap_areaTerreno.setIpCreacion(as_ip);

												ladap_DAO.insertOrUpdate(ladap_areaTerreno, true);
											}
										}

										if(NumericUtils.isValidDouble(ladap_areaConstruidaData.getArea()))
										{
											ladap_areaConstruidaData.setIdAreaPredio(ls_idAccAreaPredio);
											ladap_areaConstruidaData.setIdCirculo(ls_idCirculo);
											ladap_areaConstruidaData.setIdMatricula(ll_idMatricula);
											ladap_areaConstruidaData.setIdUsuarioCreacion(as_idUsuario);
											ladap_areaConstruidaData.setIpCreacion(as_ip);

											ladap_DAO.insertOrUpdate(ladap_areaConstruidaData, true);
										}

										if(NumericUtils.isValidDouble(ladap_areaPrivadaData.getArea()))
										{
											ladap_areaPrivadaData.setIdAreaPredio(ls_idAccAreaPredio);
											ladap_areaPrivadaData.setIdCirculo(ls_idCirculo);
											ladap_areaPrivadaData.setIdMatricula(ll_idMatricula);
											ladap_areaPrivadaData.setIdUsuarioCreacion(as_idUsuario);
											ladap_areaPrivadaData.setIpCreacion(as_ip);

											ladap_DAO.insertOrUpdate(ladap_areaPrivadaData, true);
										}
									}
								}

								{
									DetalleAreaPredio ladap_areaConstruida;

									ladap_param.setIdTipoArea(TipoAreaCommon.CONSTRUIDA);

									ladap_areaConstruida = ladap_DAO.findByIdAreaPredioTipo(ladap_param);

									if(ladap_areaConstruida != null)
									{
										boolean lb_update;
										Double  ld_area;
										Double  ld_areaDB;
										String  ls_unidadMedida;
										String  ls_unidadMedidaDB;

										lb_update             = false;
										ld_area               = ladap_areaConstruidaData.getArea();
										ld_areaDB             = ladap_areaConstruida.getArea();
										ls_unidadMedida       = ladap_areaConstruidaData.getIdUnidadMedida();
										ls_unidadMedidaDB     = ladap_areaConstruida.getIdUnidadMedida();

										if(
										    StringUtils.isValidString(ls_unidadMedida)
											    && StringUtils.isValidString(ls_unidadMedidaDB)
										)
										{
											if(!ls_unidadMedida.equalsIgnoreCase(ls_unidadMedidaDB))
												lb_update = true;
										}

										if(NumericUtils.isValidDouble(ld_area) && NumericUtils.isValidDouble(ld_areaDB))
										{
											if(ld_area.compareTo(ld_areaDB) != 0)
												lb_update = true;
										}

										if(lb_update)
										{
											ladap_areaConstruida.setArea(ld_area);
											ladap_areaConstruida.setIdUnidadMedida(ls_unidadMedida);
											ladap_areaConstruida.setIdUsuarioModificacion(as_idUsuario);
											ladap_areaConstruida.setIpModificacion(as_ip);

											ladap_DAO.insertOrUpdate(ladap_areaConstruida, false);
										}
									}
									else
									{
										if(
										    (ladap_areaConstruidaData != null)
											    && NumericUtils.isValidDouble(ladap_areaConstruidaData.getArea())
										)
										{
											ladap_areaConstruidaData.setIdAreaPredio(
											    StringUtils.getString(ls_idAreaPredio)
											);
											ladap_areaConstruidaData.setIdCirculo(ls_idCirculo);
											ladap_areaConstruidaData.setIdMatricula(ll_idMatricula);
											ladap_areaConstruidaData.setIdUsuarioCreacion(as_idUsuario);
											ladap_areaConstruidaData.setIpCreacion(as_ip);

											ladap_DAO.insertOrUpdate(ladap_areaConstruidaData, true);
										}
									}
								}

								{
									DetalleAreaPredio ladap_areaPrivada;

									ladap_param.setIdTipoArea(TipoAreaCommon.PRIVADA);

									ladap_areaPrivada = ladap_DAO.findByIdAreaPredioTipo(ladap_param);

									if(ladap_areaPrivada != null)
									{
										boolean lb_update;
										Double  ld_area;
										Double  ld_areaDB;
										String  ls_unidadMedida;
										String  ls_unidadMedidaDB;

										lb_update             = false;
										ld_area               = ladap_areaPrivadaData.getArea();
										ld_areaDB             = ladap_areaPrivada.getArea();
										ls_unidadMedida       = ladap_areaPrivadaData.getIdUnidadMedida();
										ls_unidadMedidaDB     = ladap_areaPrivada.getIdUnidadMedida();

										if(
										    StringUtils.isValidString(ls_unidadMedida)
											    && StringUtils.isValidString(ls_unidadMedidaDB)
										)
										{
											if(!ls_unidadMedida.equalsIgnoreCase(ls_unidadMedidaDB))
												lb_update = true;
										}

										if(NumericUtils.isValidDouble(ld_area) && NumericUtils.isValidDouble(ld_areaDB))
										{
											if(ld_area.compareTo(ld_areaDB) != 0)
												lb_update = true;
										}

										if(lb_update)
										{
											ladap_areaPrivadaData.setArea(ld_area);
											ladap_areaPrivadaData.setIdUnidadMedida(ls_unidadMedida);
											ladap_areaPrivadaData.setIdUsuarioModificacion(as_idUsuario);
											ladap_areaPrivadaData.setIpModificacion(as_ip);

											ladap_DAO.insertOrUpdate(ladap_areaPrivadaData, false);
										}
									}
									else
									{
										if(
										    (ladap_areaPrivadaData != null)
											    && NumericUtils.isValidDouble(ladap_areaPrivadaData.getArea())
										)
										{
											ladap_areaPrivadaData.setIdAreaPredio(
											    StringUtils.getString(ls_idAreaPredio)
											);
											ladap_areaPrivadaData.setIdCirculo(ls_idCirculo);
											ladap_areaPrivadaData.setIdMatricula(ll_idMatricula);
											ladap_areaPrivadaData.setIdUsuarioCreacion(as_idUsuario);
											ladap_areaPrivadaData.setIpCreacion(as_ip);

											ladap_DAO.insertOrUpdate(ladap_areaPrivadaData, true);
										}
									}
								}

								{
									laap_areaPredio.setCoeficiente(laap_areaPredioData.getCoeficiente());
									laap_areaPredio.setTipoSuelo(laap_areaPredioData.getTipoSuelo());
									laap_areaPredio.setIdTurno(aaaui_data.getIdTurno());
									laap_areaPredio.setIdTurnoHistoria(aaaui_data.getIdTurnoHistoria());
									laap_areaPredio.setIdUsuarioModificacion(as_idUsuario);
									laap_areaPredio.setIpModificacion(as_ip);
									laap_areaPredio.setIdAnotacion(ll_idAnotacion);

									laap_DAO.updateById(laap_areaPredio);
								}
							}
							else
							{
								int li_idAreaPredio;

								li_idAreaPredio = laap_DAO.findSecuencia();

								if(li_idAreaPredio > 0)
								{
									Iterator<DetalleAreaPredio> liadap_iterator;

									laap_areaPredio     = new AccAreaPredio();
									liadap_iterator     = lcadap_areasTerrenoData.iterator();

									laap_areaPredio.setIdArea(StringUtils.getString(li_idAreaPredio));
									laap_areaPredio.setIdCirculo(ls_idCirculo);
									laap_areaPredio.setIdMatricula(ll_idMatricula);
									laap_areaPredio.setCoeficiente(laap_areaPredioData.getCoeficiente());
									laap_areaPredio.setTipoSuelo(laap_areaPredioData.getTipoSuelo());
									laap_areaPredio.setIdTurno(aaaui_data.getIdTurno());
									laap_areaPredio.setIdTurnoHistoria(aaaui_data.getIdTurnoHistoria());
									laap_areaPredio.setIdUsuarioCreacion(as_idUsuario);
									laap_areaPredio.setIpCreacion(as_ip);
									laap_areaPredio.setIdAnotacion(ll_idAnotacion);

									laap_DAO.insert(laap_areaPredio);

									while(liadap_iterator.hasNext())
									{
										DetalleAreaPredio ladap_areaTerreno;

										ladap_areaTerreno = liadap_iterator.next();

										if(ladap_areaTerreno != null)
										{
											ladap_areaTerreno.setIdAreaPredio(StringUtils.getString(li_idAreaPredio));
											ladap_areaTerreno.setIdCirculo(ls_idCirculo);
											ladap_areaTerreno.setIdMatricula(ll_idMatricula);
											ladap_areaTerreno.setIdUsuarioCreacion(as_idUsuario);
											ladap_areaTerreno.setIpCreacion(as_ip);

											ladap_DAO.insertOrUpdate(ladap_areaTerreno, true);
										}
									}

									if(
									    (ladap_areaConstruidaData != null)
										    && NumericUtils.isValidDouble(ladap_areaConstruidaData.getArea())
									)
									{
										ladap_areaConstruidaData.setIdAreaPredio(
										    StringUtils.getString(li_idAreaPredio)
										);
										ladap_areaConstruidaData.setIdCirculo(ls_idCirculo);
										ladap_areaConstruidaData.setIdMatricula(ll_idMatricula);
										ladap_areaConstruidaData.setIdUsuarioCreacion(as_idUsuario);
										ladap_areaConstruidaData.setIpCreacion(as_ip);

										ladap_DAO.insertOrUpdate(ladap_areaConstruidaData, true);
									}

									if(
									    (ladap_areaPrivadaData != null)
										    && NumericUtils.isValidDouble(ladap_areaPrivadaData.getArea())
									)
									{
										ladap_areaPrivadaData.setIdAreaPredio(StringUtils.getString(li_idAreaPredio));
										ladap_areaPrivadaData.setIdCirculo(ls_idCirculo);
										ladap_areaPrivadaData.setIdMatricula(ll_idMatricula);
										ladap_areaPrivadaData.setIdUsuarioCreacion(as_idUsuario);
										ladap_areaPrivadaData.setIpCreacion(as_ip);

										ladap_DAO.insertOrUpdate(ladap_areaPrivadaData, true);
									}
								}
							}
						}
					}
				}
				else
					throw new B2BException(ErrorKeys.ERROR_AREA_DEL_TERRENO_AGREGAR);
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("salvarAreaPredioEnglobes", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}
	}

	/**
	 * Método encargado de guardar la información de la complementación.
	 *
	 * @param arc_dp Objeto que contiene la información a guardar.
	 * @param as_userId Variable de tipo String que contiene el id del usuario.
	 * @param as_remoteIp Variable de tipo String que contiene la ip del usuario.
	 * @return Objeto que contiene la información guardada.
	 * @throws B2BException
	 */
	public synchronized ComplementacionCalificacion salvarComplementacion(
	    RegistroCalificacion arc_dp, String as_userId, String as_remoteIp
	)
	    throws B2BException
	{
		DAOManager                  ldm_manager;
		ComplementacionCalificacion lcc_return;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		lcc_return      = null;

		try
		{
			if(arc_dp != null)
			{
				Collection<RegistroCalificacion> lcrc_data;

				lcrc_data = new ArrayList<RegistroCalificacion>();

				lcrc_data.add(arc_dp);

				if(!StringUtils.isValidString(arc_dp.getTurno()))
				{
					TurnoHistoria lth_turnoHistoria;

					lth_turnoHistoria = DaoCreator.getTurnoHistoriaDAO(ldm_manager).findById(
						    arc_dp.getIdTurnoHistoria()
						);

					if(lth_turnoHistoria != null)
						arc_dp.setTurno(lth_turnoHistoria.getIdTurno());
				}

				if(arc_dp.isCopiar())
				{
					boolean                        lb_seleccionar;
					Collection<SolicitudMatricula> lcsm_matriculasCopiar;

					lb_seleccionar            = arc_dp.isSeleccionar();
					lcsm_matriculasCopiar     = arc_dp.getMatriculasCopiar();

					if(CollectionUtils.isValidCollection(lcsm_matriculasCopiar))
					{
						for(SolicitudMatricula lsm_iterador : lcsm_matriculasCopiar)
						{
							if((lsm_iterador != null) && (lsm_iterador.isSeleccionado() || !lb_seleccionar))
							{
								RegistroCalificacion lrc_data;

								lrc_data = new RegistroCalificacion();

								lrc_data.setIdTurnoHistoria(arc_dp.getIdTurnoHistoria());
								lrc_data.setTurno(arc_dp.getTurno());
								lrc_data.setIdCirculoMatriz(
								    lsm_iterador.getIdCirculo() + IdentificadoresCommon.SIMBOLO_GUION
								    + lsm_iterador.getIdMatricula()
								);
								lrc_data.setComplementacionCalificacion(arc_dp.getComplementacionCalificacion());

								lcrc_data.add(lrc_data);
							}
						}
					}
				}

				if(CollectionUtils.isValidCollection(lcrc_data))
				{
					for(RegistroCalificacion lrc_data : lcrc_data)
						lcc_return = salvarComplementacion(lrc_data, as_remoteIp, as_userId, ldm_manager, true);
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("salvarComplementacion", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lcc_return;
	}

	/**
	 * Método encargado de salvar la información de datos básicos del proceso de Englobes.
	 *
	 * @param arc_data Objeto que contiene la información a guardar.
	 * @param as_idUsuario Variable de tipo String que contiene el id del usuario que está realizando el proceso.
	 * @param as_ip Variable de tipo String que contiene la ip del usuario que está realizando el proceso.
	 * @return Objeto que contiene la información del proceso.
	 * @throws B2BException
	 */
	public synchronized RegistroCalificacion salvarDatosBasicosEnglobes(
	    RegistroCalificacion arc_data, String as_idUsuario, String as_ip
	)
	    throws B2BException
	{
		DAOManager ldm_manager;

		ldm_manager = DaoManagerFactory.getDAOManager();

		try
		{
			if(arc_data != null)
			{
				AccPredioRegistroDAO lapr_DAO;
				Long                 ll_idTurnoHistoria;
				Long                 ll_idMatriculaTemporal;
				String               ls_idTurno;
				String               ls_idCirculo;

				lapr_DAO                   = DaoCreator.getAccPredioRegistroDAO(ldm_manager);
				ll_idTurnoHistoria         = arc_data.getIdTurnoHistoria();
				ll_idMatriculaTemporal     = null;
				ls_idTurno                 = arc_data.getTurno();
				ls_idCirculo               = arc_data.getIdCirculo();

				if(StringUtils.isValidString(ls_idTurno) && StringUtils.isValidString(ls_idCirculo))
				{
					AccPredioRegistro lapr_predioRegistro;
					boolean           lb_matriculaNueva;

					lapr_predioRegistro     = new AccPredioRegistro();
					lb_matriculaNueva       = false;

					lapr_predioRegistro.setIdTurno(ls_idTurno);

					lapr_predioRegistro = lapr_DAO.findByIdTurno(lapr_predioRegistro, true);

					if(lapr_predioRegistro == null)
					{
						BigInteger    lbi_entero;
						Calendar      lc_calendar;
						Constantes    lc_constantes;
						ConstantesDAO lcd_DAO;
						int           li_anio;
						String        ls_constante;

						lapr_predioRegistro     = new AccPredioRegistro();
						lbi_entero              = null;
						lc_calendar             = Calendar.getInstance();
						lc_constantes           = new Constantes();
						lcd_DAO                 = DaoCreator.getConstantesDAO(ldm_manager);
						li_anio                 = lc_calendar.get(Calendar.YEAR);
						ls_constante            = "CONSECUTIVO_" + ls_idCirculo
							+ IdentificadoresCommon.SIMBOLO_GUION_BAJO + li_anio;

						lc_constantes.setIdConstante(ls_constante);

						lc_constantes = lcd_DAO.findById(lc_constantes);

						if(lc_constantes != null)
						{
							BigInteger lbi_constanteMatricula = null;

							lbi_constanteMatricula = lc_constantes.getEntero();

							if(NumericUtils.isValidBigInteger(lbi_constanteMatricula))
							{
								try
								{
									lbi_entero = new BigInteger(
										    StringUtils.getString((lbi_constanteMatricula.intValue() + 1))
										);

									lc_constantes.setEntero(lbi_entero);
									lc_constantes.setIpModificacion(as_ip);
									lc_constantes.setIpModificacion(as_idUsuario);
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

							lbi_entero = new BigInteger(StringUtils.getString(ConstanteCommon.MATRICULA_DUMMY_DEFAULT));

							lc_constantes.setIdConstante(
							    "CONSECUTIVO_" + ls_idCirculo + IdentificadoresCommon.SIMBOLO_GUION_BAJO + li_anio
							);
							lc_constantes.setEntero(lbi_entero);
							lc_constantes.setIdUsuarioCreacion(as_idUsuario);
							lc_constantes.setIpCreacion(as_ip);

							lcd_DAO.insertOrUpdate(lc_constantes, true);
						}

						if(NumericUtils.isValidBigInteger(lbi_entero, new BigInteger("1")))
						{
							String ls_idDocumento;
							long   ll_versionDocumento;
							String ls_idSolicitud;

							ls_idDocumento          = null;
							ll_versionDocumento     = 0;
							ls_idSolicitud          = null;

							Turno lt_turno;

							lt_turno                = new Turno();

							lt_turno.setIdTurno(ls_idTurno);

							lt_turno = DaoCreator.getTurnoDAO(ldm_manager).findById(lt_turno);

							if(lt_turno != null)
							{
								lapr_predioRegistro.setRadicacion(lt_turno.getIdTurno());

								ls_idSolicitud = lt_turno.getIdSolicitud();

								if(StringUtils.isValidString(ls_idSolicitud))
								{
									Solicitud ls_solicitud;

									ls_solicitud = DaoCreator.getSolicitudDAO(ldm_manager).findById(ls_idSolicitud);

									if(ls_solicitud != null)
									{
										ls_idDocumento          = ls_solicitud.getIdDocumento();
										ll_versionDocumento     = NumericUtils.getLong(
											    ls_solicitud.getVersionDocumento()
											);
									}
								}
							}

							lbi_entero = new BigInteger(li_anio + "" + lbi_entero);

							{
								int li_secuencia;

								lb_matriculaNueva          = true;
								li_secuencia               = lapr_DAO.findSecuencia();
								ll_idMatriculaTemporal     = NumericUtils.getLongWrapper(lbi_entero);

								if(li_secuencia > 0)
								{
									String ls_idTipoPredio;
									String ls_secuencia;

									ls_idTipoPredio     = null;
									ls_secuencia        = StringUtils.getString(li_secuencia);

									{
										PredioRegistro lpr_predioRegistro;

										lpr_predioRegistro = new PredioRegistro();

										lpr_predioRegistro.setIdCirculo(arc_data.getIdCirculoMatriz());
										lpr_predioRegistro.setIdMatricula(
										    NumericUtils.getLong(arc_data.getIdMatriculaMatriz())
										);

										lpr_predioRegistro = DaoCreator.getPredioRegistroDAO(ldm_manager)
												                           .findByCirculoMatricula(lpr_predioRegistro);

										if(lpr_predioRegistro != null)
											ls_idTipoPredio = lpr_predioRegistro.getIdTipoPredio();
									}

									lapr_predioRegistro.setIdPredioRegistro(ls_secuencia);
									lapr_predioRegistro.setIdCirculo(ls_idCirculo);
									lapr_predioRegistro.setIdMatricula(ll_idMatriculaTemporal);
									lapr_predioRegistro.setIdTurno(ls_idTurno);
									lapr_predioRegistro.setIdTurnoHistoria(ll_idTurnoHistoria);
									lapr_predioRegistro.setIdTipoPredio(ls_idTipoPredio);

									{
										ZonaRegistral lzr_zona;
										String        ls_idZona;

										lzr_zona      = arc_data.getZonaRegistral();
										ls_idZona     = null;

										if(lzr_zona != null)
											ls_idZona = lzr_zona.getIdZonaRegistral();
										else
											throw new B2BException(ErrorKeys.ZONA_REGISTRAL_INVALIDO);

										if(StringUtils.isValidString(ls_idZona))
											lapr_predioRegistro.setIdZonaRegistral(ls_idZona);
										else
											throw new B2BException(ErrorKeys.ZONA_REGISTRAL_INVALIDO);
									}

									lapr_predioRegistro.setPredioDefinitivo(EstadoCommon.PREDIO_DEFINITIVO_TEMPORAL);
									lapr_predioRegistro.setTurnoBloqueo(ls_idTurno);
									lapr_predioRegistro.setVersionDocumento(
									    NumericUtils.getBigDecimal(ll_versionDocumento)
									);
									lapr_predioRegistro.setIdDocumento(ls_idDocumento);
									lapr_predioRegistro.setIdEstadoPredio(EstadoCommon.S);
									lapr_predioRegistro.setIdUsuarioCreacion(as_idUsuario);
									lapr_predioRegistro.setIpCreacion(as_ip);

									lapr_DAO.insert(lapr_predioRegistro);
									DaoCreator.getMatriculaSegregacionDAO(ldm_manager)
										          .updateIdPredioRegistroIdCirculoIdMatriculaByIdSolicitud(
										    new MatriculaSegregacion(
										        ls_secuencia, ls_idCirculo, ll_idMatriculaTemporal, ls_idSolicitud,
										        as_idUsuario, as_ip
										    )
										);
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

						lapr_actual           = lapr_predioRegistro;
						lb_matriculaNueva     = false;

						lapr_actual = lapr_DAO.findById(lapr_actual);

						if(lapr_actual != null)
						{
							lapr_actual.setIdZonaRegistral(arc_data.getZonaRegistral().getIdZonaRegistral());
							lapr_actual.setIdUsuarioModificacion(as_idUsuario);
							lapr_actual.setIpModificacion(as_ip);

							ll_idMatriculaTemporal = lapr_actual.getIdMatricula();

							lapr_DAO.updateById(lapr_actual);
						}
					}

					lapr_predioRegistro = lapr_DAO.findById(lapr_predioRegistro);

					if(lapr_predioRegistro != null)
					{
						arc_data.setIdMatricula(ll_idMatriculaTemporal);
						arc_data.setMatriculaSeleccionada(lb_matriculaNueva);

						if(lb_matriculaNueva)
						{
							Collection<RegistroCalificacion> lcrc_matriculas;

							lcrc_matriculas = arc_data.getAllMatriculas();

							if(CollectionUtils.isValidCollection(lcrc_matriculas))
							{
								if(lcrc_matriculas.size() >= 1)
								{
									AccPredioSegregadoDAO          laps_DAO;
									Iterator<RegistroCalificacion> lirc_iterator;

									laps_DAO          = DaoCreator.getAccPredioSegregadoDAO(ldm_manager);
									lirc_iterator     = lcrc_matriculas.iterator();

									while(lirc_iterator.hasNext())
									{
										RegistroCalificacion lrc_matricula;

										lrc_matricula = lirc_iterator.next();

										if(lrc_matricula != null)
										{
											String ls_matricula;

											ls_matricula = lrc_matricula.getIdCirculo();

											if(StringUtils.isValidString(ls_matricula))
											{
												int li_inicio;

												li_inicio = ls_matricula.lastIndexOf("-");

												if(li_inicio != -1)
												{
													PredioSegregado lps_predioSegregado;
													String          ls_idMatricula;

													lps_predioSegregado     = new PredioSegregado();
													ls_idMatricula          = ls_matricula.substring(
														    li_inicio + 1, ls_matricula.length()
														);

													lps_predioSegregado.setIdCirculo(ls_idCirculo);
													lps_predioSegregado.setIdCirculo1(ls_idCirculo);
													lps_predioSegregado.setIdMatricula(
													    NumericUtils.getLong(ls_idMatricula)
													);
													lps_predioSegregado.setIdMatricula1(
													    NumericUtils.getLong(lapr_predioRegistro.getIdMatricula())
													);
													lps_predioSegregado.setIdTurnoHistoria(
													    NumericUtils.getLong(ll_idTurnoHistoria)
													);
													lps_predioSegregado.setIdTurno(ls_idTurno);
													lps_predioSegregado.setIdUsuarioCreacion(as_idUsuario);
													lps_predioSegregado.setIpCreacion(as_ip);

													laps_DAO.insert(lps_predioSegregado);
												}
											}
										}
									}
								}
								else
									throw new B2BException(ErrorKeys.ERROR_ASOCIAR_MATRICULA);
							}
							else
								throw new B2BException(ErrorKeys.ERROR_ASOCIAR_MATRICULA);
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

			clh_LOGGER.error("salvarDatosBasicosEnglobes", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return arc_data;
	}

	/**
	 * Método encargado de salvar la información del proceso de desenglobe.
	 *
	 * @param arc_data Objeto que contiene la información del proceso a guardar.
	 * @param as_userId Variable de tipo String que contiene el id del usuario que está realizando el proceso.
	 * @param as_remoteIp Variable de tipo String que contiene la ip del usuario que está realizando el proceso.
	 * @return Objeto que contiene la información del proceso.
	 * @throws B2BException
	 */
	public synchronized boolean salvarDesenglobe(RegistroCalificacion arc_data, String as_userId, String as_remoteIp)
	    throws B2BException
	{
		boolean    lb_return;
		DAOManager ldm_manager;

		lb_return       = false;
		ldm_manager     = DaoManagerFactory.getDAOManager();

		if(arc_data != null)
		{
			try
			{
				Long ll_idTurnoHistoria;

				ll_idTurnoHistoria = arc_data.getIdTurnoHistoria();

				if(NumericUtils.isValidLong(ll_idTurnoHistoria))
				{
					TurnoHistoria    lth_turnoHistoria;
					TurnoHistoriaDAO lth_turnoHistoria_DAO;

					lth_turnoHistoria         = new TurnoHistoria();
					lth_turnoHistoria_DAO     = DaoCreator.getTurnoHistoriaDAO(ldm_manager);

					lth_turnoHistoria.setIdTurnoHistoria(ll_idTurnoHistoria);

					lth_turnoHistoria = lth_turnoHistoria_DAO.findById(lth_turnoHistoria);

					if(lth_turnoHistoria != null)
					{
						com.bachue.snr.prosnr01.model.sdb.acc.AnotacionPredio             lap_anotacionPredio;
						com.bachue.snr.prosnr01.dao.acc.AnotacionPredioDAO                lap_DAO;
						com.bachue.snr.prosnr01.dao.acc.AnotacionPredioCiudadanoDAO       lapc_DAO;
						Collection<com.bachue.snr.prosnr01.model.sdb.acc.AnotacionPredio> lcap_anotaciones;
						int                                                               li_idAnotacion;
						Long                                                              ll_idMatricula;
						String                                                            ls_idTurno;
						String                                                            ls_idCirculo;
						RegistroCalificacion                                              lrc_dataAnotacionesHerdar;

						lap_anotacionPredio           = new com.bachue.snr.prosnr01.model.sdb.acc.AnotacionPredio();
						lap_DAO                       = DaoCreator.getAccAnotacionPredioDAO(ldm_manager);
						lapc_DAO                      = DaoCreator.getAccAnotacionPredioCiudadanoDAO(ldm_manager);
						lrc_dataAnotacionesHerdar     = arc_data.getMatriculasAHeredar();
						li_idAnotacion                = 1;
						ll_idMatricula                = arc_data.getIdMatricula();
						ls_idTurno                    = arc_data.getTurno();
						ls_idCirculo                  = arc_data.getIdCirculo();

						lap_anotacionPredio.setIdCirculo(ls_idCirculo);
						lap_anotacionPredio.setIdMatricula(ll_idMatricula);

						lcap_anotaciones = lap_DAO.findByCirculoMatricula(lap_anotacionPredio);

						if(CollectionUtils.isValidCollection(lcap_anotaciones))
						{
							Iterator<com.bachue.snr.prosnr01.model.sdb.acc.AnotacionPredio> liap_iterator;

							liap_iterator = lcap_anotaciones.iterator();

							while(liap_iterator.hasNext())
							{
								com.bachue.snr.prosnr01.model.sdb.acc.AnotacionPredio lap_anotacion;

								lap_anotacion = liap_iterator.next();

								if(lap_anotacion != null)
								{
									String ls_idAnotacionPredio;

									ls_idAnotacionPredio = lap_anotacion.getIdAnotacionPredio();

									if(StringUtils.isValidString(ls_idAnotacionPredio))
									{
										lapc_DAO.deleteIntervinientes(ls_idAnotacionPredio);
										lap_DAO.deleteById(ls_idAnotacionPredio);
									}
								}
							}
						}

						if(lrc_dataAnotacionesHerdar != null)
						{
							Collection<RegistroCalificacion> lcrc_matriculasHeredar;

							lcrc_matriculasHeredar = lrc_dataAnotacionesHerdar.getAllMatriculas();

							if(CollectionUtils.isValidCollection(lcrc_matriculasHeredar))
							{
								boolean lb_devolucion;

								lb_devolucion = arc_data.isDevolucion();

								if(!StringUtils.isValidString(ls_idTurno))
									ls_idTurno = lth_turnoHistoria.getIdTurno();

								for(RegistroCalificacion lrc_tmp : lcrc_matriculasHeredar)
								{
									if(lrc_tmp.isRevision())
									{
										if(!lb_devolucion)
										{
											AnotacionPredioCiudadano                              lapc_apc;
											com.bachue.snr.prosnr01.model.sdb.acc.AnotacionPredio loap_ap;
											com.bachue.snr.prosnr01.model.sdb.acc.AnotacionPredio loap_identity;
											Collection<AnotacionPredioCiudadano>                  lcapc_apc;
											Long                                                  ll_idAnotacionHeredada;

											loap_ap                    = new com.bachue.snr.prosnr01.model.sdb.acc.AnotacionPredio();
											lapc_apc                   = new AnotacionPredioCiudadano();
											ll_idAnotacionHeredada     = lrc_tmp.getIdAnotacion();

											loap_ap.setIdTurnoHistoria(NumericUtils.getLong(ll_idTurnoHistoria));
											loap_ap.setIdEstadoRegistro(EstadoCommon.INACTIVO);
											loap_ap.setIdCirculo(ls_idCirculo);
											loap_ap.setIdMatricula(ll_idMatricula);
											loap_ap.setIdSolicitud(lth_turnoHistoria.getIdSolicitud());
											loap_ap.setIdAnotacion(NumericUtils.getLongWrapper(li_idAnotacion++));
											loap_ap.setFechaRegistro(lrc_tmp.getFechaRegistro());
											loap_ap.setIdDocumento(lrc_tmp.getIdDocumento());
											loap_ap.setIdNaturalezaJuridica(lrc_tmp.getCodActo());
											loap_ap.setVersion(1);
											loap_ap.setVersionDocumento(1);
											loap_ap.setIdTipoAnotacion(lrc_tmp.getIdTipoAnotacion());
											loap_ap.setFechaRadicacion(lrc_tmp.getFechaRadicacion());
											loap_ap.setRadicacion(lrc_tmp.getRadicacion());
											loap_ap.setIdEstadoAnotacion(lrc_tmp.getCodEstadoAnotacion());
											loap_ap.setIdUsuarioCreacion(as_userId);
											loap_ap.setIpCreacion(as_remoteIp);
											loap_ap.setIdTurno(ls_idTurno);

											loap_identity = lap_DAO.insert(loap_ap);

											lapc_apc.setIdCirculo(lrc_tmp.getIdCirculo());
											lapc_apc.setIdMatricula(NumericUtils.getLong(lrc_tmp.getIdMatricula()));
											lapc_apc.setIdAnotacion(NumericUtils.getLong(ll_idAnotacionHeredada));

											lcapc_apc = DaoCreator.getAnotacionPredioCiudadanoDAO(ldm_manager)
													                  .consultaPredioCiudadanos(lapc_apc);

											if(CollectionUtils.isValidCollection(lcapc_apc) && (loap_identity != null))
											{
												String ls_idAnotacionPredio;

												ls_idAnotacionPredio = loap_identity.getIdAnotacionPredio();

												if(StringUtils.isValidString(ls_idAnotacionPredio))
												{
													com.bachue.snr.prosnr01.model.sdb.acc.AnotacionPredioCiudadano loapc_tmp;

													for(AnotacionPredioCiudadano lopc_apc : lcapc_apc)
													{
														loapc_tmp = new com.bachue.snr.prosnr01.model.sdb.acc.AnotacionPredioCiudadano();
														loapc_tmp.setIdCirculo(ls_idCirculo);
														loapc_tmp.setIdMatricula(NumericUtils.getLong(ll_idMatricula));
														loapc_tmp.setIdAnotacion(lopc_apc.getIdAnotacion());
														loapc_tmp.setIdPersona(lopc_apc.getIdPersona());
														loapc_tmp.setRolPersona(lopc_apc.getRolPersona());
														loapc_tmp.setPropietario(lopc_apc.getPropietario());
														loapc_tmp.setPorcentajeParticipacion(
														    lopc_apc.getPorcentajeParticipacion()
														);
														loapc_tmp.setIdInteresadaRrr(lopc_apc.getIdInteresadaRrr());
														loapc_tmp.setIdUsuarioCreacion(as_userId);
														loapc_tmp.setIpCreacion(as_remoteIp);
														loapc_tmp.setIdTurno(ls_idTurno);
														loapc_tmp.setIdAnotacionPredio(ls_idAnotacionPredio);

														lapc_DAO.insert(loapc_tmp);
													}
												}
											}
										}
									}
								}
							}
						}

						{
							com.bachue.snr.prosnr01.model.sdb.acc.AnotacionPredio loap_ap;
							Long                                                  ll_idMatriculaMatriz;
							String                                                ls_idCirculoMatriz;
							final String                                          ls_idNaturalezaDesenglobe;

							loap_ap                       = new com.bachue.snr.prosnr01.model.sdb.acc.AnotacionPredio();
							ll_idMatriculaMatriz          = arc_data.getIdMatriculaMatriz();
							ls_idCirculoMatriz            = arc_data.getIdCirculoMatriz();
							ls_idNaturalezaDesenglobe     = "0915";

							loap_ap.setIdCirculo(ls_idCirculoMatriz);
							loap_ap.setIdMatricula(ll_idMatriculaMatriz);
							loap_ap.setIdNaturalezaJuridica(ls_idNaturalezaDesenglobe);

							loap_ap = lap_DAO.findByCirculoMatriculaActo(loap_ap);

							if(loap_ap != null)
							{
								loap_ap.setIdUsuarioModificacion(null);
								loap_ap.setIdUsuarioCreacion(as_userId);
								loap_ap.setIpModificacion(null);
								loap_ap.setIpCreacion(as_remoteIp);
								loap_ap.setIdCirculo(ls_idCirculo);
								loap_ap.setIdMatricula(ll_idMatricula);
								loap_ap.setIdAnotacion(NumericUtils.getLongWrapper(li_idAnotacion));
								loap_ap.setOrden(NumericUtils.getBigDecimal(li_idAnotacion));
								loap_ap.setRevisionCalificador(null);
								loap_ap.setIndicadorPredioCiudadano(null);

								loap_ap = lap_DAO.insert(loap_ap);

								if(loap_ap != null)
								{
									String ls_idAnotacionPredio;

									ls_idAnotacionPredio = loap_ap.getIdAnotacionPredio();

									if(StringUtils.isValidString(ls_idAnotacionPredio))
									{
										com.bachue.snr.prosnr01.model.sdb.acc.AnotacionPredio lap_anotacion;

										lap_anotacion = new com.bachue.snr.prosnr01.model.sdb.acc.AnotacionPredio();

										lap_anotacion.setIdCirculo(ls_idCirculoMatriz);
										lap_anotacion.setIdMatricula(ll_idMatriculaMatriz);
										lap_anotacion.setIdNaturalezaJuridica(ls_idNaturalezaDesenglobe);

										lap_anotacion = lap_DAO.findByCirculoMatriculaActo(lap_anotacion);

										if(lap_anotacion != null)
										{
											com.bachue.snr.prosnr01.model.sdb.acc.AnotacionPredioCiudadano             lapc_consulta;
											Collection<com.bachue.snr.prosnr01.model.sdb.acc.AnotacionPredioCiudadano> lcapc_anotaciones;

											lapc_consulta = new com.bachue.snr.prosnr01.model.sdb.acc.AnotacionPredioCiudadano();

											lapc_consulta.setIdAnotacionPredio(lap_anotacion.getIdAnotacionPredio());

											lcapc_anotaciones = lapc_DAO.findByIdAnotacion(lapc_consulta);

											if(CollectionUtils.isValidCollection(lcapc_anotaciones))
											{
												Iterator<com.bachue.snr.prosnr01.model.sdb.acc.AnotacionPredioCiudadano> liapc_iterator;
												int                                                                      li_count;

												liapc_iterator     = lcapc_anotaciones.iterator();
												li_count           = 1;

												while(liapc_iterator.hasNext())
												{
													com.bachue.snr.prosnr01.model.sdb.acc.AnotacionPredioCiudadano lapc_iterador;

													lapc_iterador = liapc_iterator.next();

													if(lapc_iterador != null)
													{
														lapc_iterador.setIdCirculo(ls_idCirculo);
														lapc_iterador.setIdMatricula(
														    NumericUtils.getLong(ll_idMatricula)
														);
														lapc_iterador.setIdAnotacionPredio(ls_idAnotacionPredio);
														lapc_iterador.setIdAnotacion(NumericUtils.getLong(li_count++));
														lapc_iterador.setIdUsuarioCreacion(as_userId);
														lapc_iterador.setIpCreacion(as_remoteIp);

														lapc_DAO.insert(lapc_iterador);
													}
												}
											}
										}
									}
								}

								lb_return = true;
							}
						}
					}
				}
			}
			catch(B2BException lb2be_e)
			{
				ldm_manager.setRollbackOnly();

				clh_LOGGER.error("salvarDesenglobe", lb2be_e);

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
	 * Eliminar direccion predio acc.
	 *
	 * @param as_idDireccionPredio the as id direccion predio
	 * @throws B2BException the b 2 B exception
	 */
	public synchronized void eliminarDireccionPredioAcc(String as_idDireccionPredio)
	    throws B2BException
	{
		if(StringUtils.isValidString(as_idDireccionPredio))
		{
			DAOManager ldm_manager;

			ldm_manager = DaoManagerFactory.getDAOManager();

			try
			{
				DaoCreator.getDireccionPredioAccDAO(ldm_manager).deleteById(as_idDireccionPredio);
			}
			catch(B2BException lb2be_e)
			{
				ldm_manager.setRollbackOnly();

				clh_LOGGER.error("eliminarDireccionPredioAcc", lb2be_e);

				throw lb2be_e;
			}
			finally
			{
				ldm_manager.close();
			}
		}
	}

	/**
	 * Salvar direccion venta.
	 *
	 * @param arc_rc de arc rc
	 * @param as_idUsuario de as id usuario
	 * @param as_ip de as ip
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public synchronized DireccionPredioAcc salvarDireccionVenta(
	    RegistroCalificacion arc_rc, String as_idUsuario, String as_ip
	)
	    throws B2BException
	{
		DireccionPredioAcc ldpa_direccion;

		ldpa_direccion = null;

		if(arc_rc != null)
		{
			DAOManager ldm_manager;

			ldm_manager = DaoManagerFactory.getDAOManager();

			try
			{
				DireccionPredio ldp_direccion;

				ldp_direccion = arc_rc.getDireccionGuardar();

				if(ldp_direccion != null)
				{
					DireccionPredioAccDAO ldp_DAO;
					boolean               lb_isCalificacion;

					ldp_DAO               = DaoCreator.getDireccionPredioAccDAO(ldm_manager);
					lb_isCalificacion     = arc_rc.isCalificacion();
					ldpa_direccion        = new DireccionPredioAcc();

					ldpa_direccion.setIdCirculo(arc_rc.getIdCirculo());
					ldpa_direccion.setIdMatricula(arc_rc.getIdMatricula());

					if(!lb_isCalificacion)
					{
						Collection<DireccionPredioAcc> lcdp_direcciones;

						lcdp_direcciones = ldp_DAO.findByIdCirculoMatricula(ldpa_direccion);

						if(CollectionUtils.isValidCollection(lcdp_direcciones))
						{
							Iterator<DireccionPredioAcc> lidp_iterator;

							lidp_iterator = lcdp_direcciones.iterator();

							while(lidp_iterator.hasNext())
							{
								DireccionPredioAcc ldp_direccionDelete;

								ldp_direccionDelete = lidp_iterator.next();

								if(ldp_direccionDelete != null)
									ldp_DAO.deleteById(ldp_direccionDelete);
							}
						}
					}

					{
						int li_idDireccionPredio;

						li_idDireccionPredio = ldp_DAO.findSecuence();

						if(li_idDireccionPredio > 0)
						{
							ldpa_direccion.setIdDireccionPredio(StringUtils.getString(li_idDireccionPredio));
							ldpa_direccion.setIdTurnoHistoria(arc_rc.getIdTurnoHistoria());
							ldpa_direccion.setIdTurno(arc_rc.getIdTurno());
							ldpa_direccion.setIdDireccion(
							    lb_isCalificacion
							    ? StringUtils.getString(ldp_DAO.findMaxIdDireccion(ldpa_direccion) + 1) : "1"
							);
							ldpa_direccion.setIdTipoEjePrincipal(ldp_direccion.getIdTipoEjePrincipal());
							ldpa_direccion.setIdTipoEjeSecundario(ldp_direccion.getIdTipoEjeSecundario());
							ldpa_direccion.setDatoEjePrincipal(ldp_direccion.getDatoEjePrincipal());
							ldpa_direccion.setDatoEjeSecundario(ldp_direccion.getDatoEjeSecundario());
							ldpa_direccion.setDireccion(ldp_direccion.getDireccion());
							ldpa_direccion.setComplementoDireccion(ldp_direccion.getComplementoDireccion());
							ldpa_direccion.setIdComplementoDireccion(ldp_direccion.getIdComplementoDireccion());
							ldpa_direccion.setIdUsuarioCreacion(as_idUsuario);
							ldpa_direccion.setIpCreacion(as_ip);

							ldp_DAO.insert(ldpa_direccion);
						}
					}
				}
			}
			catch(B2BException lb2be_e)
			{
				ldm_manager.setRollbackOnly();

				clh_LOGGER.error("salvarDireccionVenta", lb2be_e);

				throw lb2be_e;
			}
			finally
			{
				ldm_manager.close();
			}
		}

		return ldpa_direccion;
	}

	/**
	 * Método encargado de guardar la información del Lindero.
	 *
	 * @param arc_dp Objeto que contiene la información a guardar.
	 * @param as_userId Variable de tipo String que contiene el id del usuario.
	 * @param as_remoteIp Variable de tipo String que contiene la ip del usuario.
	 * @return Objeto que contiene la información guardada.
	 * @throws B2BException
	 */
	public synchronized LinderoRegistroCalificacion salvarLindero(
	    RegistroCalificacion arc_dp, String as_userId, String as_remoteIp
	)
	    throws B2BException
	{
		DAOManager                  ldm_manager;
		LinderoRegistroCalificacion llrc_return;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		llrc_return     = null;

		try
		{
			llrc_return = salvarLindero(arc_dp, as_remoteIp, as_userId, ldm_manager);
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("salvarLindero", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return llrc_return;
	}

	/**
	 * Método encargado de salvar los datos de la venta parcial
	 *
	 * @param arc_data Objeto que contiene los datos a guardar
	 * @param as_userId Variable de tipo String que contiene le id usuario del usuario que está realizando el trámite
	 * @param as_remoteIp Variable de tipo String que contiene la ip del usuario que está realizando el trámite
	 * @return Variable booleana que indica que el proceso se guardo satisfactoriamente
	 * @throws B2BException
	 */
	public synchronized boolean salvarVentaParcial(
	    RegistroCalificacion arc_data, String as_userId, String as_remoteIp, boolean ab_accion, boolean ab_salvar
	)
	    throws B2BException
	{
		boolean    lb_return;
		DAOManager ldm_manager;

		lb_return       = false;
		ldm_manager     = DaoManagerFactory.getDAOManager();

		if(arc_data != null)
		{
			try
			{
				Long ll_idTurnoHistoria;

				ll_idTurnoHistoria = arc_data.getIdTurnoHistoria();

				if(NumericUtils.isValidLong(ll_idTurnoHistoria))
				{
					TurnoHistoria    lth_turnoHistoria;
					TurnoHistoriaDAO lth_turnoHistoria_DAO;

					lth_turnoHistoria         = new TurnoHistoria();
					lth_turnoHistoria_DAO     = DaoCreator.getTurnoHistoriaDAO(ldm_manager);

					lth_turnoHistoria.setIdTurnoHistoria(ll_idTurnoHistoria);

					lth_turnoHistoria = lth_turnoHistoria_DAO.findById(lth_turnoHistoria);

					if(lth_turnoHistoria != null)
					{
						com.bachue.snr.prosnr01.dao.acc.AnotacionPredioDAO          lapd_DAO;
						com.bachue.snr.prosnr01.dao.acc.AnotacionPredioCiudadanoDAO lapc_DAO;
						int                                                         li_idAnotacion;
						Long                                                        ll_idMatricula;
						String                                                      ls_idTurno;
						String                                                      ls_idCirculo;

						lapd_DAO           = DaoCreator.getAccAnotacionPredioDAO(ldm_manager);
						lapc_DAO           = DaoCreator.getAccAnotacionPredioCiudadanoDAO(ldm_manager);
						li_idAnotacion     = 1;
						ll_idMatricula     = arc_data.getIdMatricula();
						ls_idTurno         = arc_data.getTurno();
						ls_idCirculo       = arc_data.getIdCirculo();

						if(ab_salvar)
						{
							com.bachue.snr.prosnr01.model.sdb.acc.AnotacionPredio             lap_anotacionPredio;
							Collection<com.bachue.snr.prosnr01.model.sdb.acc.AnotacionPredio> lcap_anotaciones;
							RegistroCalificacion                                              lrc_dataAnotacionesHerdar;

							lap_anotacionPredio           = new com.bachue.snr.prosnr01.model.sdb.acc.AnotacionPredio();
							lrc_dataAnotacionesHerdar     = arc_data.getMatriculasAHeredar();

							lap_anotacionPredio.setIdCirculo(ls_idCirculo);
							lap_anotacionPredio.setIdMatricula(ll_idMatricula);

							lcap_anotaciones = lapd_DAO.findByCirculoMatricula(lap_anotacionPredio);

							if(CollectionUtils.isValidCollection(lcap_anotaciones))
							{
								Iterator<com.bachue.snr.prosnr01.model.sdb.acc.AnotacionPredio> liap_iterator;

								liap_iterator = lcap_anotaciones.iterator();

								while(liap_iterator.hasNext())
								{
									com.bachue.snr.prosnr01.model.sdb.acc.AnotacionPredio lap_anotacion;

									lap_anotacion = liap_iterator.next();

									if(lap_anotacion != null)
									{
										String ls_idAnotacionPredio;

										ls_idAnotacionPredio = lap_anotacion.getIdAnotacionPredio();

										if(StringUtils.isValidString(ls_idAnotacionPredio))
										{
											lapc_DAO.deleteIntervinientes(ls_idAnotacionPredio);
											lapd_DAO.deleteById(ls_idAnotacionPredio);
										}
									}
								}
							}

							if(lrc_dataAnotacionesHerdar != null)
							{
								Collection<RegistroCalificacion> lcrc_matriculasHeredar;

								lcrc_matriculasHeredar = lrc_dataAnotacionesHerdar.getAllMatriculas();

								if(CollectionUtils.isValidCollection(lcrc_matriculasHeredar))
								{
									boolean lb_devolucion;

									lb_devolucion = arc_data.isDevolucion();

									if(!StringUtils.isValidString(ls_idTurno))
										ls_idTurno = lth_turnoHistoria.getIdTurno();

									for(RegistroCalificacion lrc_tmp : lcrc_matriculasHeredar)
									{
										if(lrc_tmp.isRevision())
										{
											if(!lb_devolucion)
											{
												AnotacionPredioCiudadano                              lapc_apc;
												com.bachue.snr.prosnr01.model.sdb.acc.AnotacionPredio loap_ap;
												com.bachue.snr.prosnr01.model.sdb.acc.AnotacionPredio loap_identity;
												Collection<AnotacionPredioCiudadano>                  lcapc_apc;
												Long                                                  ll_idAnotacionHeredada;

												loap_ap                    = new com.bachue.snr.prosnr01.model.sdb.acc.AnotacionPredio();
												lapc_apc                   = new AnotacionPredioCiudadano();
												ll_idAnotacionHeredada     = lrc_tmp.getIdAnotacion();

												loap_ap.setIdTurnoHistoria(NumericUtils.getLong(ll_idTurnoHistoria));
												loap_ap.setIdEstadoRegistro(EstadoCommon.INACTIVO);
												loap_ap.setIdCirculo(ls_idCirculo);
												loap_ap.setIdMatricula(ll_idMatricula);
												loap_ap.setIdSolicitud(lth_turnoHistoria.getIdSolicitud());
												loap_ap.setIdAnotacion(NumericUtils.getLongWrapper(li_idAnotacion++));
												loap_ap.setFechaRegistro(lrc_tmp.getFechaRegistro());
												loap_ap.setIdDocumento(lrc_tmp.getIdDocumento());
												loap_ap.setIdNaturalezaJuridica(lrc_tmp.getCodActo());
												loap_ap.setVersion(1);
												loap_ap.setVersionDocumento(1);
												loap_ap.setIdTipoAnotacion(lrc_tmp.getIdTipoAnotacion());
												loap_ap.setFechaRadicacion(lrc_tmp.getFechaRadicacion());
												loap_ap.setRadicacion(lrc_tmp.getRadicacion());
												loap_ap.setIdEstadoAnotacion(lrc_tmp.getCodEstadoAnotacion());
												loap_ap.setIdUsuarioCreacion(as_userId);
												loap_ap.setIpCreacion(as_remoteIp);
												loap_ap.setIdTurno(ls_idTurno);

												loap_identity = lapd_DAO.insert(loap_ap);

												lapc_apc.setIdCirculo(lrc_tmp.getIdCirculo());
												lapc_apc.setIdMatricula(NumericUtils.getLong(lrc_tmp.getIdMatricula()));
												lapc_apc.setIdAnotacion(NumericUtils.getLong(ll_idAnotacionHeredada));

												lcapc_apc = DaoCreator.getAnotacionPredioCiudadanoDAO(ldm_manager)
														                  .consultaPredioCiudadanos(lapc_apc);

												if(
												    CollectionUtils.isValidCollection(lcapc_apc)
													    && (loap_identity != null)
												)
												{
													String ls_idAnotacionPredio;

													ls_idAnotacionPredio = loap_identity.getIdAnotacionPredio();

													if(StringUtils.isValidString(ls_idAnotacionPredio))
													{
														com.bachue.snr.prosnr01.model.sdb.acc.AnotacionPredioCiudadano loapc_tmp;

														for(AnotacionPredioCiudadano lopc_apc : lcapc_apc)
														{
															loapc_tmp = new com.bachue.snr.prosnr01.model.sdb.acc.AnotacionPredioCiudadano();
															loapc_tmp.setIdCirculo(ls_idCirculo);
															loapc_tmp.setIdMatricula(
															    NumericUtils.getLong(ll_idMatricula)
															);
															loapc_tmp.setIdAnotacion(lopc_apc.getIdAnotacion());
															loapc_tmp.setIdPersona(lopc_apc.getIdPersona());
															loapc_tmp.setRolPersona(lopc_apc.getRolPersona());
															loapc_tmp.setPropietario(lopc_apc.getPropietario());
															loapc_tmp.setPorcentajeParticipacion(
															    lopc_apc.getPorcentajeParticipacion()
															);
															loapc_tmp.setIdInteresadaRrr(lopc_apc.getIdInteresadaRrr());
															loapc_tmp.setIdUsuarioCreacion(as_userId);
															loapc_tmp.setIpCreacion(as_remoteIp);
															loapc_tmp.setIdTurno(ls_idTurno);
															loapc_tmp.setIdAnotacionPredio(ls_idAnotacionPredio);

															lapc_DAO.insert(loapc_tmp);
														}
													}
												}
											}
										}
									}
								}
							}

							{
								com.bachue.snr.prosnr01.model.sdb.acc.AnotacionPredio loap_ap;
								Long                                                  ll_idMatriculaMatriz;
								String                                                ls_idCirculoMatriz;
								final String                                          ls_idNaturalezaCompraVentaParcial;

								loap_ap                               = new com.bachue.snr.prosnr01.model.sdb.acc.AnotacionPredio();
								ll_idMatriculaMatriz                  = arc_data.getIdMatriculaMatriz();
								ls_idCirculoMatriz                    = arc_data.getIdCirculoMatriz();
								ls_idNaturalezaCompraVentaParcial     = TipoActoCommon.COMPRAVENTA_PARCIAL;

								loap_ap.setIdCirculo(ls_idCirculoMatriz);
								loap_ap.setIdMatricula(ll_idMatriculaMatriz);
								loap_ap.setIdNaturalezaJuridica(ls_idNaturalezaCompraVentaParcial);

								loap_ap = lapd_DAO.findByCirculoMatriculaActo(loap_ap);

								if(loap_ap != null)
								{
									loap_ap.setIdUsuarioModificacion(null);
									loap_ap.setIdUsuarioCreacion(as_userId);
									loap_ap.setIpModificacion(null);
									loap_ap.setIpCreacion(as_remoteIp);
									loap_ap.setIdCirculo(ls_idCirculo);
									loap_ap.setIdMatricula(ll_idMatricula);
									loap_ap.setIdAnotacion(NumericUtils.getLongWrapper(li_idAnotacion));
									loap_ap.setOrden(NumericUtils.getBigDecimal(li_idAnotacion));
									loap_ap.setIdNaturalezaJuridica(TipoActoCommon.COMPRAVENTA);
									loap_ap.setRevisionCalificador(null);
									loap_ap.setIndicadorPredioCiudadano(null);

									loap_ap = lapd_DAO.insert(loap_ap);

									if(loap_ap != null)
									{
										String ls_idAnotacionPredio;

										ls_idAnotacionPredio = loap_ap.getIdAnotacionPredio();

										if(StringUtils.isValidString(ls_idAnotacionPredio))
										{
											com.bachue.snr.prosnr01.model.sdb.acc.AnotacionPredio lap_anotacion;

											lap_anotacion = new com.bachue.snr.prosnr01.model.sdb.acc.AnotacionPredio();

											lap_anotacion.setIdCirculo(ls_idCirculoMatriz);
											lap_anotacion.setIdMatricula(ll_idMatriculaMatriz);
											lap_anotacion.setIdNaturalezaJuridica(ls_idNaturalezaCompraVentaParcial);

											lap_anotacion = lapd_DAO.findByCirculoMatriculaActo(lap_anotacion);

											if(lap_anotacion != null)
											{
												com.bachue.snr.prosnr01.model.sdb.acc.AnotacionPredioCiudadano             lapc_consulta;
												Collection<com.bachue.snr.prosnr01.model.sdb.acc.AnotacionPredioCiudadano> lcapc_anotaciones;

												lapc_consulta = new com.bachue.snr.prosnr01.model.sdb.acc.AnotacionPredioCiudadano();

												lapc_consulta.setIdAnotacionPredio(
												    lap_anotacion.getIdAnotacionPredio()
												);

												lcapc_anotaciones = lapc_DAO.findByIdAnotacion(lapc_consulta);

												if(CollectionUtils.isValidCollection(lcapc_anotaciones))
												{
													Iterator<com.bachue.snr.prosnr01.model.sdb.acc.AnotacionPredioCiudadano> liapc_iterator;
													int                                                                      li_count;

													liapc_iterator     = lcapc_anotaciones.iterator();
													li_count           = 1;

													while(liapc_iterator.hasNext())
													{
														com.bachue.snr.prosnr01.model.sdb.acc.AnotacionPredioCiudadano lapc_iterador;

														lapc_iterador = liapc_iterator.next();

														if(lapc_iterador != null)
														{
															lapc_iterador.setIdCirculo(ls_idCirculo);
															lapc_iterador.setIdMatricula(
															    NumericUtils.getLong(ll_idMatricula)
															);
															lapc_iterador.setIdAnotacionPredio(ls_idAnotacionPredio);
															lapc_iterador.setIdAnotacion(
															    NumericUtils.getLong(li_count++)
															);
															lapc_iterador.setIdUsuarioCreacion(as_userId);
															lapc_iterador.setIpCreacion(as_remoteIp);

															lapc_DAO.insert(lapc_iterador);
														}
													}
												}
											}
										}
									}

									lb_return = true;
								}
							}
						}

						if(ab_accion)
						{
							MotivoTramite lmt_motivo;

							lmt_motivo = new MotivoTramite();

							lmt_motivo.setIdEtapaOrigen(EtapaCommon.ID_ETAPA_CALIFICACION);
							lmt_motivo.setIdMotivo(MotivoTramiteCommon.REALIZAR_REGISTRO_VENTA_PARCIAL);

							lmt_motivo = DaoCreator.getMotivoTramiteDAO(ldm_manager).findById(lmt_motivo);

							if(lmt_motivo != null)
							{
								lth_turnoHistoria.setEstadoActividad(EstadoCommon.TERMINADA);
								lth_turnoHistoria.setMotivo(lmt_motivo.getDescripcion());
								lth_turnoHistoria.setIdMotivo(NumericUtils.getLongWrapper(lmt_motivo.getIdMotivo()));
								lth_turnoHistoria.setObservaciones(arc_data.getObservaciones());
								lth_turnoHistoria.setIdUsuarioModificacion(as_userId);
								lth_turnoHistoria.setIpModificacion(as_remoteIp);

								lth_turnoHistoria_DAO.insertOrUpdate(lth_turnoHistoria, false);

								DaoCreator.getProcedimientosDAO(ldm_manager).spCreateStage(lth_turnoHistoria);
							}

							arc_data.setSalvar(true);
							arc_data.setVentaParcial(true);

							genereteFileRegistro(ldm_manager, arc_data, false, as_userId, as_remoteIp, false);
						}
					}
				}
			}
			catch(B2BException lb2be_e)
			{
				ldm_manager.setRollbackOnly();

				clh_LOGGER.error("salvarVentaParcial", lb2be_e);

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
	 * Método encargado de salvar el proceso de Venta parcial cuando el predio no es un cementerio.
	 *
	 * @param aalp_lindero Objeto que contiene la información a guardar.
	 * @param as_user Variable de tipo String que contiene el id del usuario que está realizando el proceso.
	 * @param as_remoteIp Variable de tipo String que contiene la ip del usuario que está realizando el proceso.
	 * @return Objeto que contiene la información del proceso.
	 * @throws B2BException
	 */
	public boolean salvarVentaParcialNoCementerio(AccLinderoPredio aalp_lindero, String as_user, String as_remoteIp)
	    throws B2BException
	{
		boolean lb_return;

		lb_return = false;

		if(aalp_lindero != null)
		{
			DAOManager ldm_manager;

			ldm_manager = DaoManagerFactory.getDAOManager();

			try
			{
				String ls_lindero;

				ls_lindero = aalp_lindero.getLindero();

				if(StringUtils.isValidString(ls_lindero))
				{
					AccLinderoPredio    lalp_linderoTemp;
					AccLinderoPredioDAO lalp_DAO;

					lalp_DAO     = DaoCreator.getAccLinderoPredioDAO(ldm_manager);

					lalp_linderoTemp = lalp_DAO.findByCirculoMatricula(aalp_lindero);

					if(lalp_linderoTemp != null)
					{
						lalp_linderoTemp.setLindero(ls_lindero);
						lalp_linderoTemp.setIdTurno(aalp_lindero.getIdTurno());
						lalp_linderoTemp.setIdTurnoHistoria(aalp_lindero.getIdTurnoHistoria());
						lalp_linderoTemp.setIdUsuarioModificacion(as_user);
						lalp_linderoTemp.setIpModificacion(as_remoteIp);

						lalp_DAO.updateAllById(lalp_linderoTemp);
					}
					else
					{
						int li_idLindero;

						li_idLindero = lalp_DAO.findSecuencia();

						if(li_idLindero > 0)
						{
							aalp_lindero.setIdLinderoPredio(StringUtils.getString(li_idLindero));
							aalp_lindero.setIdUsuarioCreacion(as_user);
							aalp_lindero.setIpCreacion(as_remoteIp);

							lalp_DAO.insert(aalp_lindero);
						}
					}

					lb_return = true;
				}
				else
					throw new B2BException(ErrorKeys.ERROR_LINDERO_INVALIDO);
			}
			catch(B2BException lb2be_e)
			{
				ldm_manager.setRollbackOnly();

				clh_LOGGER.error("salvarVentaParcialNoCementerio", lb2be_e);

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
	 * Método para guardar detalle anotación
	 *
	 * @param aorc_rc
	 *            Objeto con información de la anotación
	 * @return
	 * @throws B2BException
	 */
	public synchronized RegistroCalificacion saveDetailAnotacion(RegistroCalificacion aorc_rc)
	    throws B2BException
	{
		DAOManager              ldm_manager;
		RegistroCalificacion    lorc_matriculas;
		RegistroCalificacionDAO lorcd_rcd;

		ldm_manager         = DaoManagerFactory.getDAOManager();
		lorcd_rcd           = DaoCreator.getRegistroCalificacionDAO(ldm_manager);
		lorc_matriculas     = new RegistroCalificacion();

		try
		{
			if(aorc_rc != null)
			{
				lorcd_rcd.modificarRevisionAnotacion(aorc_rc);

				lorc_matriculas = DaoCreator.getAccAnotacionPredioDAO(ldm_manager)
						                        .consultaDetalleActo(aorc_rc.getIdAnotacionPredio());
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("saveDetailAnotacion", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lorc_matriculas;
	}

	/**
	 * Terminar proceso apoyo nacional aprobacion.
	 *
	 * @param asanui_param de asanui param
	 * @param as_userId de as user id
	 * @param as_remoteIp de as remote ip
	 * @return el valor de solicitud apoyo nacional UI
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public synchronized SolicitudApoyoNacionalUI terminarProcesoApoyoNacionalAprobacion(
	    SolicitudApoyoNacionalUI asanui_param, String as_userId, String as_remoteIp
	)
	    throws B2BException
	{
		if(asanui_param != null)
		{
			DAOManager ldm_manager;

			ldm_manager = DaoManagerFactory.getDAOManager();

			try
			{
				TurnoHistoria lth_turnoHistoria;

				lth_turnoHistoria = DaoCreator.getTurnoHistoriaDAO(ldm_manager)
						                          .findById(
						    NumericUtils.getLongWrapper(asanui_param.getIdTurnoHistoria())
						);

				if(lth_turnoHistoria != null)
				{
					MotivoTramite lmt_motivoTramite;

					lmt_motivoTramite = new MotivoTramite(
						    NumericUtils.getLong(lth_turnoHistoria.getIdEtapa()),
						    MotivoTramiteCommon.APROBADO_SOLICITUD_APOYO_NACIONAL
						);

					terminarTurnoHistoriaYCrearEtapa(
					    lth_turnoHistoria, ldm_manager, lmt_motivoTramite, as_userId, as_remoteIp,
					    EstadoCommon.TERMINADA
					);
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

		return asanui_param;
	}

	/**
	 * Método encargado de listar turnos hijos para un turno padre.
	 *
	 * @param arc_rc correspondiente al valor de RegistroCalificacion
	 * @return Objeto que contiene la información consultada.
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public synchronized RegistroCalificacion turnosVinculados(RegistroCalificacion arc_rc)
	    throws B2BException
	{
		DAOManager           ldm_manager;
		RegistroCalificacion lrc_return;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		lrc_return      = null;

		try
		{
			if(arc_rc != null)
			{
				TurnoDerivado             ltd_turnosDerivado;
				Collection<TurnoDerivado> lctd_turnoDerivados;
				String                    ls_turno;
				long                      ll_idEtapa;

				ltd_turnosDerivado     = new TurnoDerivado();
				ls_turno               = arc_rc.getTurno();
				ll_idEtapa             = arc_rc.getIdEtapa();

				ltd_turnosDerivado.setIdTurnoPadre(ls_turno);

				lctd_turnoDerivados = DaoCreator.getTurnoDerivadoDAO(ldm_manager)
						                            .findByIdTurnoPadreVinculados(ltd_turnosDerivado);

				if(CollectionUtils.isValidCollection(lctd_turnoDerivados))
				{
					Collection<RegistroCalificacion> lcrc_crc;
					RegistroCalificacion             lrc_rc;
					TurnoHistoriaDAO                 lthD_DAO;

					lcrc_crc     = new ArrayList<RegistroCalificacion>();
					lrc_rc       = new RegistroCalificacion();
					lthD_DAO     = DaoCreator.getTurnoHistoriaDAO(ldm_manager);

					lrc_rc.setTurno(ls_turno);
					lrc_rc.setFaseTurno(StringUtils.getStringNotNull(faseTurno(lrc_rc, ldm_manager)));
					lrc_rc.setTramite(EstadoCommon.ESTADO_ANOTACION_INCOMPLETO);
					lrc_rc.setEspecificacion(EstadoCommon.SIN_MODIFICAR);
					lrc_rc.setEstadoPredio(IdentificadoresCommon.TURNO_INICIAL);
					lrc_rc.setMatriculasAdd(true);

					{
						TurnoHistoria lth_th;

						lth_th = lthD_DAO.findByIdTurnoEtapa(ls_turno, ll_idEtapa);

						if(lth_th != null)
							lrc_rc.setIdTurnoHistoria(lth_th.getIdTurnoHistoria());
					}

					lcrc_crc.add(lrc_rc);

					for(TurnoDerivado ltd_tmp : lctd_turnoDerivados)
					{
						if((ltd_tmp != null) && ltd_tmp.isIndVinculado())
						{
							lrc_rc = new RegistroCalificacion();

							String ls_turnoHijo;
							ls_turnoHijo = ltd_tmp.getIdTurnoHijo();

							if(StringUtils.isValidString(ls_turnoHijo))
							{
								lrc_rc.setTurno(ls_turnoHijo);
								lrc_rc.setFaseTurno(StringUtils.getStringNotNull(faseTurno(lrc_rc, ldm_manager)));
								lrc_rc.setTramite(EstadoCommon.ESTADO_ANOTACION_INCOMPLETO);
								lrc_rc.setEspecificacion(EstadoCommon.SIN_MODIFICAR);
								lrc_rc.setEstadoPredio(IdentificadoresCommon.TURNO_VINCULADO);
								lrc_rc.setMatriculasAdd(false);

								{
									TurnoHistoria lth_th;

									lth_th = lthD_DAO.findByIdTurnoEtapa(ls_turnoHijo, ll_idEtapa);

									if(lth_th != null)
										lrc_rc.setIdTurnoHistoria(lth_th.getIdTurnoHistoria());
								}

								lcrc_crc.add(lrc_rc);
							}
						}
					}

					if(CollectionUtils.isValidCollection(lcrc_crc))
					{
						lrc_return = new RegistroCalificacion();
						lrc_return.setAllMatriculas(lcrc_crc);
					}
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();
			lrc_return = null;
			clh_LOGGER.error("turnosVinculados", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lrc_return;
	}

	/**
	 * Método para validar actos con cancelación
	 *
	 * @param aorc_rc
	 *            Objeto para obtener el id turno historia
	 * @return
	 * @throws B2BException
	 */
	public synchronized boolean validacionCancelacion(RegistroCalificacion aorc_rc)
	    throws B2BException
	{
		boolean lb_return;

		lb_return = false;

		if(aorc_rc != null)
		{
			DAOManager ldm_manager;

			ldm_manager = DaoManagerFactory.getDAOManager();

			try
			{
				TurnoHistoria lth_turnoHistoria;

				lth_turnoHistoria = DaoCreator.getTurnoHistoriaDAO(ldm_manager).findById(aorc_rc.getIdTurnoHistoria());

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
							boolean lb_oficinaNotificaCorrespondencia;

							lb_oficinaNotificaCorrespondencia = DaoCreator.getDocumentoDAO(ldm_manager)
									                                          .consultaOficinaDoc(
									    ls_solicitud.getIdDocumento()
									);

							if(lb_oficinaNotificaCorrespondencia)
							{
								/* BUSCAR que tengan actos de cancelacion Grupo 700 */
								Turno lt_turno;

								lt_turno = DaoCreator.getTurnoDAO(ldm_manager).findById(lth_turnoHistoria.getIdTurno());

								if(lt_turno != null)
								{
									Collection<SolicitudMatriculaActo> lcsm_tmp;

									{
										SolicitudMatriculaActo lsma_solMatActo;

										lsma_solMatActo = new SolicitudMatriculaActo();

										lsma_solMatActo.setIdSolicitud(ls_idSolicitud);
										lsma_solMatActo.setIdCirculo(lt_turno.getIdCirculo());

										lcsm_tmp = DaoCreator.getSolicitudMatriculaActoDAO(ldm_manager)
												                 .dataBySolicitud(lsma_solMatActo);
									}

									if(CollectionUtils.isValidCollection(lcsm_tmp))
									{
										for(SolicitudMatriculaActo lsma_solMatActo : lcsm_tmp)
										{
											if(
											    StringUtils.getStringNotNull(lsma_solMatActo.getIdGrupoJuridica())
												               .equalsIgnoreCase("0700")
											)
											{
												lb_return = true;

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
			catch(B2BException lb2be_e)
			{
				ldm_manager.setRollbackOnly();

				clh_LOGGER.error("validacionCancelacion", lb2be_e);

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
	 * Método encargado de verificar si se deben validar los campos de áreas para el proceso.
	 *
	 * @param as_idTurno Variable de tipo String que contiene el id del turno en trámite para consultar los actos.
	 * @return Variable booleana que indica si se deben o no validar los campos.
	 * @throws B2BException
	 */
	public synchronized boolean validarDatosArea(String as_idTurno)
	    throws B2BException
	{
		boolean    lb_return;
		DAOManager ldm_manager;

		lb_return       = false;
		ldm_manager     = DaoManagerFactory.getDAOManager();

		try
		{
			if(StringUtils.isValidString(as_idTurno))
			{
				Turno lt_turno;

				lt_turno = new Turno();

				lt_turno.setIdTurno(as_idTurno);

				lt_turno = DaoCreator.getTurnoDAO(ldm_manager).findById(lt_turno);

				if(lt_turno != null)
				{
					Acto             la_param;
					Collection<Acto> lca_actos;

					la_param = new Acto();

					la_param.setIdSolicitud(lt_turno.getIdSolicitud());
					la_param.setIdCirculo(lt_turno.getIdCirculo());

					lca_actos = DaoCreator.getActoDAO(ldm_manager).findByIdSolicitudCirculo(la_param);

					if(CollectionUtils.isValidCollection(lca_actos))
					{
						Map<String, String> lmss_actos;

						lmss_actos = ListadoCodigosConstantes.generarCodigos(
							    ConstanteCommon.CODIGOS_ACTOS_GENERAR_LINDEROS
							);

						if(CollectionUtils.isValidCollection(lmss_actos))
						{
							Iterator<Acto> lia_iterator;

							lia_iterator = lca_actos.iterator();

							while(lia_iterator.hasNext() && !lb_return)
							{
								Acto la_acto;

								la_acto = lia_iterator.next();

								if(la_acto != null)
								{
									String ls_idTipoActo;

									ls_idTipoActo     = la_acto.getIdTipoActo();
									lb_return         = StringUtils.isValidString(ls_idTipoActo)
											&& lmss_actos.containsKey(ls_idTipoActo);
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

			clh_LOGGER.error("validarDatosArea", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lb_return;
	}

	/**
	 * Método para eliminar anotación parcial
	 *
	 * @param aea_datosEliminar
	 *            Objeto con anotación a eliminar
	 * @return
	 * @throws B2BException
	 */
	public synchronized boolean validarEliminarAnotacionParcial(EliminarAnotacion aea_datosEliminar)
	    throws B2BException
	{
		boolean    lb_return;
		DAOManager ldm_manager;

		lb_return       = true;
		ldm_manager     = DaoManagerFactory.getDAOManager();

		try
		{
			if(aea_datosEliminar != null)
			{
				String ls_idAnotacionPredio;

				ls_idAnotacionPredio = aea_datosEliminar.getIdAnotacionPredio();

				if(StringUtils.isValidString(ls_idAnotacionPredio))
				{
					com.bachue.snr.prosnr01.model.sdb.acc.AnotacionPredio laap_anotacionPredio;
					com.bachue.snr.prosnr01.dao.acc.AnotacionPredioDAO    laap_DAO;

					laap_anotacionPredio     = new com.bachue.snr.prosnr01.model.sdb.acc.AnotacionPredio();
					laap_DAO                 = DaoCreator.getAccAnotacionPredioDAO(ldm_manager);

					laap_anotacionPredio.setIdAnotacionPredio(ls_idAnotacionPredio);

					laap_anotacionPredio = laap_DAO.findById(laap_anotacionPredio);

					if(laap_anotacionPredio != null)
					{
						boolean lb_grupo400;
						boolean lb_juzgado;
						String  ls_idNaturalezaJuridica;
						String  ls_idSolicitud;

						lb_grupo400                 = false;
						lb_juzgado                  = false;
						ls_idNaturalezaJuridica     = laap_anotacionPredio.getIdNaturalezaJuridica();
						ls_idSolicitud              = laap_anotacionPredio.getIdSolicitud();

						if(StringUtils.isValidString(ls_idNaturalezaJuridica))
						{
							NaturalezaJuridica    lnj_naturalezaJuridica;
							NaturalezaJuridicaDAO lnj_DAO;

							lnj_naturalezaJuridica     = new NaturalezaJuridica();
							lnj_DAO                    = DaoCreator.getNaturalezaJuridicaDAO(ldm_manager);

							lnj_naturalezaJuridica.setIdNaturalezaJuridica(ls_idNaturalezaJuridica);

							lnj_naturalezaJuridica = lnj_DAO.findByIdMaxVersion(lnj_naturalezaJuridica);

							if(lnj_naturalezaJuridica != null)
							{
								Constantes    lc_constante;
								ConstantesDAO lc_DAO;
								String        ls_idGrupoNaturalezaJuridica;

								lc_constante                     = new Constantes();
								lc_DAO                           = DaoCreator.getConstantesDAO(ldm_manager);
								ls_idGrupoNaturalezaJuridica     = lnj_naturalezaJuridica.getIdGrupoNatJur();

								lc_constante.setIdConstante(
								    ConstanteCommon.GRUPOS_NATURALEZA_JURIDICA_ELIMINACION_PARCIAL
								);

								lc_constante = lc_DAO.findById(lc_constante);

								if(lc_constante != null)
								{
									String ls_caracter;

									ls_caracter = lc_constante.getCaracter();

									if(
									    StringUtils.isValidString(ls_caracter)
										    && StringUtils.isValidString(ls_idGrupoNaturalezaJuridica)
										    && ls_idGrupoNaturalezaJuridica.equalsIgnoreCase(ls_caracter)
									)
										lb_grupo400 = true;
								}
							}
						}

						if(StringUtils.isValidString(ls_idSolicitud))
						{
							Solicitud ls_solicitud;

							ls_solicitud = new Solicitud();

							ls_solicitud.setIdSolicitud(ls_idSolicitud);

							ls_solicitud = DaoCreator.getSolicitudDAO(ldm_manager).findById(ls_solicitud);

							if(ls_solicitud != null)
							{
								String ls_idDocumento;

								ls_idDocumento = ls_solicitud.getIdDocumento();

								if(StringUtils.isValidString(ls_idDocumento))
								{
									Documento ld_documento;

									ld_documento = new Documento();

									ld_documento.setIdDocumento(ls_idDocumento);

									ld_documento = DaoCreator.getDocumentoDAO(ldm_manager).findById(ld_documento);

									if(ld_documento != null)
									{
										String ls_idTipoOficina;

										ls_idTipoOficina = ld_documento.getIdTipoOficina();

										if(!StringUtils.isValidString(ls_idTipoOficina))
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
													ls_idTipoOficina = loo_oficinaOrigen.getIdTipoOficina();
											}
										}

										if(StringUtils.isValidString(ls_idTipoOficina))
										{
											Constantes lc_constante;

											lc_constante     = new Constantes();

											lc_constante = DaoCreator.getConstantesDAO(ldm_manager)
													                     .findById(
													    ConstanteCommon.CODIGO_OFICINA_JUZGADO
													);

											if(lc_constante != null)
											{
												String ls_caracter;

												ls_caracter = lc_constante.getCaracter();

												if(
												    StringUtils.isValidString(ls_caracter)
													    && ls_caracter.equalsIgnoreCase(ls_idTipoOficina)
												)
													lb_juzgado = true;
											}
										}
									}
								}
							}
						}

						if(lb_grupo400 && lb_juzgado)
							lb_return = false;
					}
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("validacionCancelacion", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lb_return;
	}

	/**
	 * Método para validar si una matricula tiene embargos vigentes.
	 *
	 * @param as_idCirculo String de idCirculo.
	 * @param al_idMatricula String de idMatricula.
	 * @param as_idSolicitud de as id solicitud
	 * @return ab_return booleana que indica si tiene o no embargos vigentes.
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public boolean validarEmbargosVigentes(String as_idCirculo, long al_idMatricula, String as_idSolicitud)
	    throws B2BException
	{
		DAOManager ldm_manager;
		boolean    lb_return;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		lb_return       = false;

		try
		{
			if(
			    StringUtils.isValidString(as_idCirculo) && (al_idMatricula > 0)
				    && StringUtils.isValidString(as_idSolicitud)
			)
			{
				Collection<AnotacionPredio> lcanp_cllAnotacionPredio;

				lcanp_cllAnotacionPredio = DaoCreator.getAnotacionPredioDAO(ldm_manager)
						                                 .findByCirculoMatricula(
						    as_idCirculo, NumericUtils.getLongWrapper(al_idMatricula), true
						);

				if(CollectionUtils.isValidCollection(lcanp_cllAnotacionPredio))
				{
					boolean lb_deGrupoNatJuridica0400;

					lb_deGrupoNatJuridica0400 = false;

					for(AnotacionPredio lap_temp : lcanp_cllAnotacionPredio)
					{
						if((lap_temp != null) && !lb_deGrupoNatJuridica0400)
						{
							String ls_anotacionCancelada;

							ls_anotacionCancelada = lap_temp.getAnotacionCancelada();

							if(
							    StringUtils.isValidString(ls_anotacionCancelada)
								    && !ls_anotacionCancelada.equalsIgnoreCase("S")
							)
							{
								String ls_idNaturalezaJuridica;
								long   ls_version;

								ls_idNaturalezaJuridica     = lap_temp.getIdNaturalezaJuridica();
								ls_version                  = lap_temp.getVersion();

								if(StringUtils.isValidString(ls_idNaturalezaJuridica))
								{
									NaturalezaJuridica lnj_naturalezaJuridica;

									lnj_naturalezaJuridica = DaoCreator.getNaturalezaJuridicaDAO(ldm_manager)
											                               .findById(
											    ls_idNaturalezaJuridica, ls_version
											);

									if(lnj_naturalezaJuridica != null)
									{
										String ls_idGrupoNaturalezaJuridica;

										ls_idGrupoNaturalezaJuridica = lnj_naturalezaJuridica.getIdGrupoNatJur();

										if(
										    StringUtils.isValidString(ls_idGrupoNaturalezaJuridica)
											    && ls_idGrupoNaturalezaJuridica.equalsIgnoreCase("0400")
										)
											lb_deGrupoNatJuridica0400 = true;
									}
								}
							}
						}
					}

					if(lb_deGrupoNatJuridica0400)
					{
						Collection<Acto> lca_acto;

						lca_acto = DaoCreator.getActoDAO(ldm_manager)
								                 .findByIdSolicitudCirculo(as_idSolicitud, as_idCirculo);

						if(CollectionUtils.isValidCollection(lca_acto))
						{
							Constantes lc_constante;

							lc_constante = DaoCreator.getConstantesDAO(ldm_manager)
									                     .findById(ConstanteCommon.ACTOS_CANCELACION_CON_REMATE);

							if(lc_constante != null)
							{
								Collection<String> lcs_datosConstante;

								lcs_datosConstante = separarPorSimboloComa(lc_constante.getCaracter(), true);

								if(CollectionUtils.isValidCollection(lcs_datosConstante))
								{
									for(Acto la_acto : lca_acto)
									{
										if(
										    (la_acto != null) && !lb_return
											    && lcs_datosConstante.contains(la_acto.getIdTipoActo())
										)
											lb_return = true;
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

			clh_LOGGER.error("validarEmbargosVigentes", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lb_return;
	}

	/**
	 * Método para precalificar anotaciones activas
	 *
	 * @param as_idTurnoHistoria
	 * @return
	 * @throws B2BException
	 */
	public boolean validarProrrogaEntregaDePruebas(String as_idTurnoHistoria)
	    throws B2BException
	{
		DAOManager ldm_manager;
		boolean    lb_bool;

		lb_bool         = false;
		ldm_manager     = DaoManagerFactory.getDAOManager();

		try
		{
			TurnoHistoria lth_data;

			lth_data = new TurnoHistoria();

			if(StringUtils.isValidString(as_idTurnoHistoria))
			{
				lth_data = DaoCreator.getTurnoHistoriaDAO(ldm_manager)
						                 .findById(NumericUtils.getLongWrapper(as_idTurnoHistoria));

				if(lth_data != null)
				{
					Solicitud ls_solicitud;
					ls_solicitud = DaoCreator.getSolicitudDAO(ldm_manager).findById(lth_data.getIdSolicitud());

					if(ls_solicitud != null)
					{
						Collection<SolicitudAsociada> lcsa_solicitudesAsociadas;
						lcsa_solicitudesAsociadas = DaoCreator.getSolicitudAsociadaDAO(ldm_manager)
								                                  .findAllByIdSolicitud(
								    ls_solicitud.getIdSolicitud(), true
								);

						if(CollectionUtils.isValidCollection(lcsa_solicitudesAsociadas))
						{
							for(SolicitudAsociada lsa_solicitudAsociada : lcsa_solicitudesAsociadas)
							{
								if(lsa_solicitudAsociada != null)
								{
									Solicitud ls_solicitud1;
									ls_solicitud1 = DaoCreator.getSolicitudDAO(ldm_manager)
											                      .findById(lsa_solicitudAsociada.getIdSolicitud1());

									if(ls_solicitud1 != null)
									{
										String ls_proceso1;
										ls_proceso1 = ls_solicitud1.getIdProceso();

										if(ls_proceso1.equalsIgnoreCase(ProcesoCommon.ID_PROCESO_41))
											lb_bool = true;
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

			clh_LOGGER.error("validarProrrogaEntregaDePruebas", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lb_bool;
	}

	/**
	 * Método encargado de validar si se debe validar la sumatoria de las áreas.
	 *
	 * @param as_idTurnoHistoria Variable de tipo String que contiene el id del turno historia en trámite.
	 * @return Variable de tipo boolean que valida si se debe validar la sumatoria.
	 * @throws B2BException
	 */
	public synchronized boolean validarSumatoria(String as_idTurnoHistoria)
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
				Long ll_idTurnoHistoria;

				ll_idTurnoHistoria = NumericUtils.getLongWrapper(as_idTurnoHistoria);

				if(NumericUtils.isValidLong(ll_idTurnoHistoria))
				{
					TurnoHistoria lth_th;

					lth_th = DaoCreator.getTurnoHistoriaDAO(ldm_manager).findById(ll_idTurnoHistoria);

					if(lth_th != null)
					{
						String ls_idTurno;
						String ls_idSolicitud;

						ls_idTurno     = lth_th.getIdTurno();

						ls_idSolicitud = lth_th.getIdSolicitud();

						if(StringUtils.isValidString(ls_idSolicitud) && StringUtils.isValidString(ls_idTurno))
						{
							Turno lt_t;

							lt_t = DaoCreator.getTurnoDAO(ldm_manager).findById(ls_idTurno);

							if(lt_t != null)
							{
								String ls_idCirculo;

								ls_idCirculo = lt_t.getIdCirculo();

								if(StringUtils.isValidString(ls_idCirculo))
								{
									Collection<com.bachue.snr.prosnr01.model.registro.Acto> lca_actos;

									lca_actos = DaoCreator.getTipoActoDAO(ldm_manager)
											                  .findActosBySolicitud(ls_idSolicitud, ls_idCirculo);

									if(CollectionUtils.isValidCollection(lca_actos))
									{
										Map<String, String> lmss_actos;
										Map<String, String> lmss_actos2;

										lmss_actos      = generarCodigos(
											    ConstanteCommon.CODIGOS_VALIDAR_AREAS_TERRENO, ldm_manager
											);
										lmss_actos2     = generarCodigos(
											    ConstanteCommon.CODIGOS_PROPIEDAD_HORIZONTAL, ldm_manager
											);

										if(CollectionUtils.isValidCollection(lmss_actos))
										{
											Iterator<com.bachue.snr.prosnr01.model.registro.Acto> lia_iterator;

											lia_iterator = lca_actos.iterator();

											while(lia_iterator.hasNext() && !lb_return)
											{
												com.bachue.snr.prosnr01.model.registro.Acto la_acto;

												la_acto = lia_iterator.next();

												if(la_acto != null)
												{
													String ls_idTipoActo;

													ls_idTipoActo     = la_acto.getCodigo();
													lb_return         = StringUtils.isValidString(ls_idTipoActo)
															&& (lmss_actos.containsKey(ls_idTipoActo)
															|| lmss_actos2.containsKey(ls_idTipoActo));
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

			clh_LOGGER.error("validarSumatoria", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lb_return;
	}

	/**
	 * Método para obtener idgitalizacion bussines.
	 *
	 * @return el valor de digitalizacion business
	 */
	protected LiquidacionBusiness getLiquidacionBusiness()
	{
		if(alb_liquidacionBusiness == null)
			alb_liquidacionBusiness = new LiquidacionBusiness();

		return alb_liquidacionBusiness;
	}

	/**
	 * Método encargado de consultas las alertas para los intervinientes de Baldios.
	 *
	 * @param as_idPersona Variable de tipo String que contiene el id de la persona a consultar.
	 * @param ab_accion Variable de tipo booleana que valida que tipo de alerta se va generar.
	 * @param as_tipoActo Vairable de tipo String que contiene el id tipo acto a validar.
	 * @param adm_manager DaoManager que administra la conexión con la base de datos.
	 * @return Arreglo de Strings que contiene las alertas generadas.
	 * @throws B2BException
	 */
	private synchronized Map<String, Object[]> alertasIntervinientesBaldios(
	    String as_idPersona, boolean ab_accion, String as_tipoActo, DAOManager adm_manager
	)
	    throws B2BException
	{
		Map<String, Object[]> lsa_return;

		lsa_return = null;

		if(StringUtils.isValidString(as_idPersona) && StringUtils.isValidString(as_tipoActo))
		{
			Persona lp_persona;

			lp_persona = new Persona();

			lp_persona.setIdPersona(as_idPersona);

			lp_persona = DaoCreator.getPersonaDAO(adm_manager).findById(lp_persona);

			if(lp_persona != null)
			{
				boolean                         lb_consulta1;
				boolean                         lb_consulta2;
				boolean                         lb_consulta3;
				Collection<Map<String, Object>> lcmso_consulta1;
				Collection<Map<String, Object>> lcmso_consulta2;
				Collection<Map<String, Object>> lcmso_consulta3;
				Map<Integer, Object>            lmio_criterios;
				ViewDataReportDAO               lvdr_DAO;

				lb_consulta1     = false;
				lb_consulta2     = false;
				lb_consulta3     = false;
				lvdr_DAO         = DaoCreator.getViewDataReportDAO(adm_manager);

				{
					lmio_criterios = new HashMap<Integer, Object>();

					lmio_criterios.put(new Integer(1), as_tipoActo);
					lmio_criterios.put(new Integer(2), as_idPersona);
					lmio_criterios.put(new Integer(3), RolCommon.A);

					lcmso_consulta1     = lvdr_DAO.findByViewName(
						    ViewsCommon.VW_CIUDADANO_ANOTACION_PREDIO, null, lvdr_DAO.cs_WHERE_BALDIOS_ID_PERSONA_ROL,
						    lmio_criterios
						);
					lb_consulta1        = CollectionUtils.isValidCollection(lcmso_consulta1);

					lmio_criterios.put(new Integer(4), IdentificadoresCommon.X);

					lcmso_consulta2     = lvdr_DAO.findByViewName(
						    ViewsCommon.VW_CIUDADANO_ANOTACION_PREDIO, null,
						    lvdr_DAO.cs_WHERE_BALDIOS_ID_PERSONA_ROL_PROPIETARIO, lmio_criterios
						);
					lb_consulta2        = CollectionUtils.isValidCollection(lcmso_consulta2);
				}

				{
					boolean lb_apellido;
					boolean lb_nombre;
					int     li_column;
					String  ls_consulta;
					String  ls_segundoApellido;
					String  ls_segundoNombre;

					li_column              = 1;
					ls_consulta            = lvdr_DAO.cs_WHERE_BALDIOS_NOMBRE;
					lmio_criterios         = new HashMap<Integer, Object>();
					ls_segundoApellido     = lp_persona.getSegundoApellido();
					ls_segundoNombre       = lp_persona.getSegundoNombre();
					lb_apellido            = StringUtils.isValidString(ls_segundoApellido);
					lb_nombre              = StringUtils.isValidString(ls_segundoNombre);

					lmio_criterios.put(new Integer(li_column++), as_tipoActo);
					lmio_criterios.put(new Integer(li_column++), RolCommon.A);
					lmio_criterios.put(new Integer(li_column++), IdentificadoresCommon.X);
					lmio_criterios.put(new Integer(li_column++), lp_persona.getPrimerNombre());
					lmio_criterios.put(new Integer(li_column++), lp_persona.getPrimerApellido());

					if(lb_nombre || lb_apellido)
					{
						if(lb_nombre)
						{
							lmio_criterios.put(new Integer(li_column++), ls_segundoNombre);
							ls_consulta = lvdr_DAO.cs_WHERE_BALDIOS_NOMBRE_SEGUNDO_NOMBRE_ROL_PROPIETARIO;
						}

						if(lb_apellido)
						{
							lmio_criterios.put(new Integer(li_column++), ls_segundoApellido);
							ls_consulta = lvdr_DAO.cs_WHERE_BALDIOS_NOMBRE_SEGUNDO_APELLIDO_ROL_PROPIETARIO;
						}

						if(lb_nombre && lb_apellido)
							ls_consulta = lvdr_DAO.cs_WHERE_BALDIOS_NOMBRE_COMPLETO_ROL_PROPIETARIO;
					}

					lcmso_consulta3     = lvdr_DAO.findByViewName(
						    ViewsCommon.VW_CIUDADANO_ANOTACION_PREDIO, null, ls_consulta, lmio_criterios
						);
					lb_consulta3        = CollectionUtils.isValidCollection(lcmso_consulta3);
				}

				if(lb_consulta1 || lb_consulta2 || lb_consulta3)
				{
					Collection<Map<String, Object>> lcm_consulta;
					Object[]                        loa_arg;
					String                          ls_alerta;

					lcm_consulta     = null;
					loa_arg          = new String[2];
					lsa_return       = new HashMap<String, Object[]>();
					ls_alerta        = null;

					if(ab_accion)
					{
						if(as_tipoActo.equalsIgnoreCase(TipoActoCommon.BALDIOS))
							ls_alerta = ErrorKeys.ALERTA_INTERVINIENTE_BALDIOS;
						else if(as_tipoActo.equalsIgnoreCase(TipoActoCommon.AFECTACION_VIVIENDA_FAMILIAR))
							ls_alerta = ErrorKeys.ALERTA_INTERVINIENTE_AFECTACION_VIVIENDA_FAMILIAR;
						else if(as_tipoActo.equalsIgnoreCase(TipoActoCommon.CONSTITUCION_PATRIMONIO_FAMILIA))
							ls_alerta = ErrorKeys.ALERTA_INTERVINIENTE_CONSTITUCION_PATRIMONIO_FAMILIA;
					}
					else
					{
						if(as_tipoActo.equalsIgnoreCase(TipoActoCommon.BALDIOS))
							ls_alerta = MessagesKeys.ALERTA_INTERVINIENTE_BALDIOS;
						else if(as_tipoActo.equalsIgnoreCase(TipoActoCommon.AFECTACION_VIVIENDA_FAMILIAR))
							ls_alerta = MessagesKeys.ALERTA_INTERVINIENTE_AFECTACION_VIVIENDA_FAMILIAR;
						else if(as_tipoActo.equalsIgnoreCase(TipoActoCommon.CONSTITUCION_PATRIMONIO_FAMILIA))
							ls_alerta = MessagesKeys.ALERTA_INTERVINIENTE_CONSTITUCION_PATRIMONIO_FAMILIA;
					}

					if(lb_consulta1)
						lcm_consulta = lcmso_consulta1;
					else if(lb_consulta2)
						lcm_consulta = lcmso_consulta2;
					else if(lb_consulta3)
						lcm_consulta = lcmso_consulta3;

					if(CollectionUtils.isValidCollection(lcm_consulta))
					{
						boolean       lb_dataPersona;
						StringBuilder lsb_nombre;
						StringBuilder lsb_matriculas;

						lb_dataPersona     = false;
						lsb_nombre         = new StringBuilder();
						lsb_matriculas     = new StringBuilder();

						for(Map<String, Object> lm_map : lcm_consulta)
						{
							if(CollectionUtils.isValidCollection(lm_map))
							{
								if(!lb_dataPersona)
								{
									String ls_dataPNombre;
									String ls_dataSNombre;
									String ls_dataPApellido;
									String ls_dataSApellido;

									ls_dataPNombre       = (String)lm_map.get("P_PRIMER_NOMBRE");
									ls_dataSNombre       = (String)lm_map.get("P_SEGUNDO_NOMBRE");
									ls_dataPApellido     = (String)lm_map.get("P_PRIMER_APELLIDO");
									ls_dataSApellido     = (String)lm_map.get("P_SEGUNDO_APELLIDO");

									if(StringUtils.isValidString(ls_dataPNombre))
										lsb_nombre.append(" " + ls_dataPNombre);

									if(StringUtils.isValidString(ls_dataSNombre))
										lsb_nombre.append(" " + ls_dataSNombre);

									if(StringUtils.isValidString(ls_dataPApellido))
										lsb_nombre.append(" " + ls_dataPApellido);

									if(StringUtils.isValidString(ls_dataSApellido))
										lsb_nombre.append(" " + ls_dataSApellido);

									lb_dataPersona = true;
								}

								String     ls_idCirculo;
								BigDecimal lbd_idMatricula;

								ls_idCirculo        = (String)lm_map.get("AP_ID_CIRCULO");
								lbd_idMatricula     = (BigDecimal)lm_map.get("AP_ID_MATRICULA");

								if(
								    StringUtils.isValidString(ls_idCirculo)
									    && NumericUtils.isValidBigDecimal(lbd_idMatricula)
								)
									lsb_matriculas.append(ls_idCirculo + "-" + lbd_idMatricula + " ");
							}
						}

						loa_arg[0]     = lsb_nombre.toString();
						loa_arg[1]     = lsb_matriculas.toString();
					}

					lsa_return.put(ls_alerta, loa_arg);
				}
			}
		}

		return lsa_return;
	}

	/**
	 * Método encargado de devolver el nombre de la fase actual para un turno especifico
	 * @param arc_rc Objeto de tipo RegistroCalificacion que contiene los datos necesarios para realizar la consulta
	 * @param adm_dm Objeto de tipo dao manager que contiene la conexión a la base de datos.
	 * @return Nombre de la fase actual para un turno.
	 * @throws B2BException
	 */
	private String faseTurno(RegistroCalificacion arc_rc, DAOManager adm_dm)
	    throws B2BException
	{
		String ls_nombreFase;
		ls_nombreFase = null;

		if(arc_rc != null)
		{
			Turno    lt_turno;
			TurnoDAO ltd_DAO;

			lt_turno     = new Turno();
			ltd_DAO      = DaoCreator.getTurnoDAO(adm_dm);

			lt_turno.setIdTurno(arc_rc.getTurno());
			lt_turno = ltd_DAO.findById(lt_turno);

			if(lt_turno != null)
			{
				Long ll_etapaTurno;

				ll_etapaTurno = lt_turno.getIdEtapaActual();

				if(NumericUtils.isValidLong(ll_etapaTurno))
				{
					Etapa le_etapa;

					le_etapa = new Etapa();

					le_etapa.setIdEtapa(NumericUtils.getLong(ll_etapaTurno));
					le_etapa = DaoCreator.getEtapaDAO(adm_dm).findById(le_etapa);

					if(le_etapa != null)
					{
						Fases lf_fase;

						lf_fase = new Fases();

						lf_fase.setIdFase(le_etapa.getIdFase());
						lf_fase = DaoCreator.getFasesDAO(adm_dm).findById(lf_fase);

						if(lf_fase != null)
							ls_nombreFase = lf_fase.getNombre();
					}
				}
			}
		}

		return ls_nombreFase;
	}

	/**
	 * Método encargado de guardar la información del área del predio.
	 *
	 * @param aaaui_data Objeto que contiene la información del área a guardar.
	 * @param as_idUsuario Varible de tipo String que contiene el usuario que está ejecutando la acción.
	 * @param as_remoteIp Variable de tipo String que contiene la ip del usuario que está ejecutando la acción.
	 * @param adm_manager DAOManager que controla las transacciones.
	 * @throws B2BException
	 */
	private synchronized void salvarAreaPredio(
	    AccAreaUI aaaui_data, String as_idUsuario, String as_remoteIp, DAOManager adm_manager
	)
	    throws B2BException
	{
		try
		{
			if(aaaui_data != null)
			{
				Collection<DetalleAreaPredio> lcadap_areasTerrenoData;

				lcadap_areasTerrenoData = aaaui_data.getAreasTerreno();

				if(CollectionUtils.isValidCollection(lcadap_areasTerrenoData))
				{
					AccAreaPredio     laap_areaPredioData;
					DetalleAreaPredio ladap_areaConstruidaData;
					DetalleAreaPredio ladap_areaPrivadaData;
					String            ls_tipoUso;

					laap_areaPredioData          = aaaui_data.getAreaPredio();
					ladap_areaConstruidaData     = aaaui_data.getDetalleAreaConstruida();
					ladap_areaPrivadaData        = aaaui_data.getDetalleAreaPrivada();
					ls_tipoUso                   = null;

					if(laap_areaPredioData != null)
					{
						ls_tipoUso = laap_areaPredioData.getTipoSuelo();

						if(!StringUtils.isValidString(ls_tipoUso))
							throw new B2BException(ErrorKeys.USO_DEL_PREDIO_INVALIDO_SELECCIONAR);
					}

					{
						String ls_idCirculo;
						Long   ll_idMatricula;

						ls_idCirculo       = aaaui_data.getIdCirculo();
						ll_idMatricula     = aaaui_data.getIdMatricula();

						if(StringUtils.isValidString(ls_idCirculo) && NumericUtils.isValidLong(ll_idMatricula))
						{
							AccAreaPredio           laap_areaPredio;
							AccAreaPredioDAO        laap_DAO;
							AccDetalleAreaPredioDAO ladap_DAO;

							laap_areaPredio     = new AccAreaPredio();
							laap_DAO            = DaoCreator.getAccAreaPredioDAO(adm_manager);
							ladap_DAO           = DaoCreator.getAccDetalleAreaPredioDAO(adm_manager);

							laap_areaPredio.setIdCirculo(ls_idCirculo);
							laap_areaPredio.setIdMatricula(ll_idMatricula);

							laap_areaPredio = laap_DAO.findByCirculoMatricula(laap_areaPredio);

							if(laap_areaPredio != null)
							{
								DetalleAreaPredio                  ladap_param;
								HashMap<String, DetalleAreaPredio> lhsadap_areas;
								String                             ls_idAreaPredio;

								ladap_param         = new DetalleAreaPredio();
								ls_idAreaPredio     = laap_areaPredio.getIdArea();

								ladap_param.setIdAreaPredio(ls_idAreaPredio);
								ladap_param.setIdCirculo(laap_areaPredio.getIdCirculo());
								ladap_param.setIdMatricula(laap_areaPredio.getIdMatricula());
								ladap_param.setIdTipoArea(TipoAreaCommon.TERRENO);

								lhsadap_areas = ladap_DAO.findAllByIdAreaPredioTipoDetalle(ladap_param);

								if(CollectionUtils.isValidCollection(lhsadap_areas))
								{
									HashMap<String, DetalleAreaPredio> lhsadap_areasInsert;
									Iterator<DetalleAreaPredio>        liadap_iterator;

									lhsadap_areasInsert     = new HashMap<String, DetalleAreaPredio>();
									liadap_iterator         = lcadap_areasTerrenoData.iterator();

									while(liadap_iterator.hasNext())
									{
										DetalleAreaPredio ladap_areaTerreno;

										ladap_areaTerreno = liadap_iterator.next();

										if(ladap_areaTerreno != null)
										{
											String ls_idDetalleAreaPredio;

											ls_idDetalleAreaPredio = ladap_areaTerreno.getIdDetalleAreaPredio();

											if(StringUtils.isValidString(ls_idDetalleAreaPredio))
											{
												if(lhsadap_areas.containsKey(ls_idDetalleAreaPredio))
												{
													DetalleAreaPredio ladap_db;

													ladap_db = lhsadap_areas.get(ls_idDetalleAreaPredio);

													if(ladap_db != null)
													{
														boolean lb_update;
														Double  ld_area;
														Double  ld_areaDB;
														String  ls_unidadMedida;
														String  ls_unidadMedidaDB;

														lb_update             = false;
														ld_area               = ladap_areaTerreno.getArea();
														ld_areaDB             = ladap_db.getArea();
														ls_unidadMedida       = ladap_areaTerreno.getIdUnidadMedida();
														ls_unidadMedidaDB     = ladap_db.getIdUnidadMedida();

														if(
														    StringUtils.isValidString(ls_unidadMedida)
															    && StringUtils.isValidString(ls_unidadMedidaDB)
														)
														{
															if(!ls_unidadMedida.equalsIgnoreCase(ls_unidadMedidaDB))
																lb_update = true;
														}

														if(
														    NumericUtils.isValidDouble(ld_area)
															    && NumericUtils.isValidDouble(ld_areaDB)
														)
														{
															if(ld_area.compareTo(ld_areaDB) != 0)
																lb_update = true;
														}

														if(lb_update)
														{
															ladap_db.setArea(ld_area);
															ladap_db.setIdUnidadMedida(ls_unidadMedida);
															ladap_db.setIdUsuarioModificacion(as_idUsuario);
															ladap_db.setIpModificacion(as_remoteIp);

															ladap_DAO.insertOrUpdate(ladap_db, false);
														}
													}
												}
												else
												{
													ladap_areaTerreno.setIdAreaPredio(ls_idAreaPredio);
													ladap_areaTerreno.setIdCirculo(ls_idCirculo);
													ladap_areaTerreno.setIdMatricula(ll_idMatricula);
													ladap_areaTerreno.setIdUsuarioCreacion(as_idUsuario);
													ladap_areaTerreno.setIpCreacion(as_remoteIp);

													ladap_DAO.insertOrUpdate(ladap_areaTerreno, true);
												}

												lhsadap_areasInsert.put(ls_idDetalleAreaPredio, ladap_areaTerreno);
											}
										}
									}

									for(Map.Entry<String, DetalleAreaPredio> lhm_iterador : lhsadap_areas.entrySet())
									{
										DetalleAreaPredio ladap_detalleAreaPredioMap;
										String            ls_idDetalleAreaMap;

										ladap_detalleAreaPredioMap     = lhm_iterador.getValue();
										ls_idDetalleAreaMap            = lhm_iterador.getKey();

										if(StringUtils.isValidString(ls_idDetalleAreaMap))
										{
											if(
											    !lhsadap_areasInsert.containsKey(ls_idDetalleAreaMap)
												    && (ladap_detalleAreaPredioMap != null)
											)
												ladap_DAO.delete(ladap_detalleAreaPredioMap);
										}
									}
								}
								else
								{
									String ls_idAccAreaPredio;

									ls_idAccAreaPredio = laap_areaPredio.getIdArea();

									if(StringUtils.isValidString(ls_idAreaPredio))
									{
										Iterator<DetalleAreaPredio> liadap_iterator;

										liadap_iterator = lcadap_areasTerrenoData.iterator();

										while(liadap_iterator.hasNext())
										{
											DetalleAreaPredio ladap_areaTerreno;

											ladap_areaTerreno = liadap_iterator.next();

											if(ladap_areaTerreno != null)
											{
												ladap_areaTerreno.setIdAreaPredio(ls_idAccAreaPredio);
												ladap_areaTerreno.setIdCirculo(ls_idCirculo);
												ladap_areaTerreno.setIdMatricula(ll_idMatricula);
												ladap_areaTerreno.setIdUsuarioCreacion(as_idUsuario);
												ladap_areaTerreno.setIpCreacion(as_remoteIp);

												ladap_DAO.insertOrUpdate(ladap_areaTerreno, true);
											}
										}

										if(NumericUtils.isValidDouble(ladap_areaConstruidaData.getArea()))
										{
											ladap_areaConstruidaData.setIdAreaPredio(ls_idAccAreaPredio);
											ladap_areaConstruidaData.setIdCirculo(ls_idCirculo);
											ladap_areaConstruidaData.setIdMatricula(ll_idMatricula);
											ladap_areaConstruidaData.setIdUsuarioCreacion(as_idUsuario);
											ladap_areaConstruidaData.setIpCreacion(as_remoteIp);

											ladap_DAO.insertOrUpdate(ladap_areaConstruidaData, true);
										}

										if(NumericUtils.isValidDouble(ladap_areaPrivadaData.getArea()))
										{
											ladap_areaPrivadaData.setIdAreaPredio(ls_idAccAreaPredio);
											ladap_areaPrivadaData.setIdCirculo(ls_idCirculo);
											ladap_areaPrivadaData.setIdMatricula(ll_idMatricula);
											ladap_areaPrivadaData.setIdUsuarioCreacion(as_idUsuario);
											ladap_areaPrivadaData.setIpCreacion(as_remoteIp);

											ladap_DAO.insertOrUpdate(ladap_areaPrivadaData, true);
										}
									}
								}

								{
									DetalleAreaPredio ladap_areaConstruida;

									ladap_param.setIdTipoArea(TipoAreaCommon.CONSTRUIDA);

									ladap_areaConstruida = ladap_DAO.findByIdAreaPredioTipo(ladap_param);

									if(ladap_areaConstruida != null)
									{
										boolean lb_update;
										Double  ld_area;
										Double  ld_areaDB;
										String  ls_unidadMedida;
										String  ls_unidadMedidaDB;

										lb_update             = false;
										ld_area               = ladap_areaConstruidaData.getArea();
										ld_areaDB             = ladap_areaConstruida.getArea();
										ls_unidadMedida       = ladap_areaConstruidaData.getIdUnidadMedida();
										ls_unidadMedidaDB     = ladap_areaConstruida.getIdUnidadMedida();

										if(
										    StringUtils.isValidString(ls_unidadMedida)
											    && StringUtils.isValidString(ls_unidadMedidaDB)
										)
										{
											if(!ls_unidadMedida.equalsIgnoreCase(ls_unidadMedidaDB))
												lb_update = true;
										}

										if(NumericUtils.isValidDouble(ld_area) && NumericUtils.isValidDouble(ld_areaDB))
										{
											if(ld_area.compareTo(ld_areaDB) != 0)
												lb_update = true;
										}

										if(lb_update)
										{
											ladap_areaConstruida.setArea(ld_area);
											ladap_areaConstruida.setIdUnidadMedida(ls_unidadMedida);
											ladap_areaConstruida.setIdUsuarioModificacion(as_idUsuario);
											ladap_areaConstruida.setIpModificacion(as_remoteIp);

											ladap_DAO.insertOrUpdate(ladap_areaConstruida, false);
										}
									}
									else
									{
										if(
										    (ladap_areaConstruidaData != null)
											    && NumericUtils.isValidDouble(ladap_areaConstruidaData.getArea())
										)
										{
											ladap_areaConstruidaData.setIdAreaPredio(
											    StringUtils.getString(ls_idAreaPredio)
											);
											ladap_areaConstruidaData.setIdCirculo(ls_idCirculo);
											ladap_areaConstruidaData.setIdMatricula(ll_idMatricula);
											ladap_areaConstruidaData.setIdUsuarioCreacion(as_idUsuario);
											ladap_areaConstruidaData.setIpCreacion(as_remoteIp);

											ladap_DAO.insertOrUpdate(ladap_areaConstruidaData, true);
										}
									}
								}

								{
									DetalleAreaPredio ladap_areaPrivada;

									ladap_param.setIdTipoArea(TipoAreaCommon.PRIVADA);

									ladap_areaPrivada = ladap_DAO.findByIdAreaPredioTipo(ladap_param);

									if(ladap_areaPrivada != null)
									{
										boolean lb_update;
										Double  ld_area;
										Double  ld_areaDB;
										String  ls_unidadMedida;
										String  ls_unidadMedidaDB;

										lb_update             = false;
										ld_area               = ladap_areaPrivadaData.getArea();
										ld_areaDB             = ladap_areaPrivada.getArea();
										ls_unidadMedida       = ladap_areaPrivadaData.getIdUnidadMedida();
										ls_unidadMedidaDB     = ladap_areaPrivada.getIdUnidadMedida();

										if(
										    StringUtils.isValidString(ls_unidadMedida)
											    && StringUtils.isValidString(ls_unidadMedidaDB)
										)
										{
											if(!ls_unidadMedida.equalsIgnoreCase(ls_unidadMedidaDB))
												lb_update = true;
										}

										if(NumericUtils.isValidDouble(ld_area) && NumericUtils.isValidDouble(ld_areaDB))
										{
											if(ld_area.compareTo(ld_areaDB) != 0)
												lb_update = true;
										}

										if(lb_update)
										{
											ladap_areaPrivadaData.setArea(ld_area);
											ladap_areaPrivadaData.setIdUnidadMedida(ls_unidadMedida);
											ladap_areaPrivadaData.setIdUsuarioModificacion(as_idUsuario);
											ladap_areaPrivadaData.setIpModificacion(as_remoteIp);

											ladap_DAO.insertOrUpdate(ladap_areaPrivadaData, false);
										}
									}
									else
									{
										if(
										    (ladap_areaPrivadaData != null)
											    && NumericUtils.isValidDouble(ladap_areaPrivadaData.getArea())
										)
										{
											ladap_areaPrivadaData.setIdAreaPredio(
											    StringUtils.getString(ls_idAreaPredio)
											);
											ladap_areaPrivadaData.setIdCirculo(ls_idCirculo);
											ladap_areaPrivadaData.setIdMatricula(ll_idMatricula);
											ladap_areaPrivadaData.setIdUsuarioCreacion(as_idUsuario);
											ladap_areaPrivadaData.setIpCreacion(as_remoteIp);

											ladap_DAO.insertOrUpdate(ladap_areaPrivadaData, true);
										}
									}
								}

								{
									laap_areaPredio.setCoeficiente(laap_areaPredioData.getCoeficiente());
									laap_areaPredio.setTipoSuelo(laap_areaPredioData.getTipoSuelo());
									laap_areaPredio.setIdTurno(aaaui_data.getIdTurno());
									laap_areaPredio.setIdTurnoHistoria(aaaui_data.getIdTurnoHistoria());
									laap_areaPredio.setIdUsuarioModificacion(as_idUsuario);
									laap_areaPredio.setIpModificacion(as_remoteIp);

									laap_DAO.updateById(laap_areaPredio);
								}
							}
							else
							{
								int li_idAreaPredio;

								li_idAreaPredio = laap_DAO.findSecuencia();

								if(li_idAreaPredio > 0)
								{
									Iterator<DetalleAreaPredio> liadap_iterator;

									laap_areaPredio     = new AccAreaPredio();
									liadap_iterator     = lcadap_areasTerrenoData.iterator();

									laap_areaPredio.setIdArea(StringUtils.getString(li_idAreaPredio));
									laap_areaPredio.setIdCirculo(ls_idCirculo);
									laap_areaPredio.setIdMatricula(ll_idMatricula);
									laap_areaPredio.setCoeficiente(laap_areaPredioData.getCoeficiente());
									laap_areaPredio.setTipoSuelo(laap_areaPredioData.getTipoSuelo());
									laap_areaPredio.setIdTurno(aaaui_data.getIdTurno());
									laap_areaPredio.setIdTurnoHistoria(aaaui_data.getIdTurnoHistoria());
									laap_areaPredio.setIdUsuarioCreacion(as_idUsuario);
									laap_areaPredio.setIpCreacion(as_remoteIp);

									laap_DAO.insert(laap_areaPredio);

									while(liadap_iterator.hasNext())
									{
										DetalleAreaPredio ladap_areaTerreno;

										ladap_areaTerreno = liadap_iterator.next();

										if(ladap_areaTerreno != null)
										{
											ladap_areaTerreno.setIdAreaPredio(StringUtils.getString(li_idAreaPredio));
											ladap_areaTerreno.setIdCirculo(ls_idCirculo);
											ladap_areaTerreno.setIdMatricula(ll_idMatricula);
											ladap_areaTerreno.setIdUsuarioCreacion(as_idUsuario);
											ladap_areaTerreno.setIpCreacion(as_remoteIp);

											ladap_DAO.insertOrUpdate(ladap_areaTerreno, true);
										}
									}

									if(
									    (ladap_areaConstruidaData != null)
										    && NumericUtils.isValidDouble(ladap_areaConstruidaData.getArea())
									)
									{
										ladap_areaConstruidaData.setIdAreaPredio(
										    StringUtils.getString(li_idAreaPredio)
										);
										ladap_areaConstruidaData.setIdCirculo(ls_idCirculo);
										ladap_areaConstruidaData.setIdMatricula(ll_idMatricula);
										ladap_areaConstruidaData.setIdUsuarioCreacion(as_idUsuario);
										ladap_areaConstruidaData.setIpCreacion(as_remoteIp);

										ladap_DAO.insertOrUpdate(ladap_areaConstruidaData, true);
									}

									if(
									    (ladap_areaPrivadaData != null)
										    && NumericUtils.isValidDouble(ladap_areaPrivadaData.getArea())
									)
									{
										ladap_areaPrivadaData.setIdAreaPredio(StringUtils.getString(li_idAreaPredio));
										ladap_areaPrivadaData.setIdCirculo(ls_idCirculo);
										ladap_areaPrivadaData.setIdMatricula(ll_idMatricula);
										ladap_areaPrivadaData.setIdUsuarioCreacion(as_idUsuario);
										ladap_areaPrivadaData.setIpCreacion(as_remoteIp);

										ladap_DAO.insertOrUpdate(ladap_areaPrivadaData, true);
									}
								}
							}

							if(StringUtils.isValidString(ls_tipoUso))
							{
								AccPredioRegistro    lapr_predio;
								AccPredioRegistroDAO lapr_DAO;

								lapr_predio     = new AccPredioRegistro();
								lapr_DAO        = DaoCreator.getAccPredioRegistroDAO(adm_manager);

								lapr_predio.setIdCirculo(ls_idCirculo);
								lapr_predio.setIdMatricula(ll_idMatricula);

								lapr_predio = lapr_DAO.findByCirculoMatricula(lapr_predio);

								if(lapr_predio != null)
								{
									lapr_predio.setIdUsuarioModificacion(as_idUsuario);
									lapr_predio.setIpModificacion(as_remoteIp);
									lapr_predio.setIdTipoUsoSuelo(ls_tipoUso);

									lapr_DAO.updateById(lapr_predio);
								}
							}
						}
					}
				}
				else
					throw new B2BException(ErrorKeys.ERROR_AREA_DEL_TERRENO_AGREGAR);
			}
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("salvarAreaPredio", lb2be_e);
			throw lb2be_e;
		}
	}

	/**
	 * Método encargado de guardar la información del cierre del folio.
	 *
	 * @param arc_data Objeto que contiene la información del cierre del folio a guardar.
	 * @throws B2BException
	 */
	private synchronized void salvarCierreFolio(RegistroCalificacion arc_data, DAOManager adm_manager)
	    throws B2BException
	{
		if(adm_manager == null)
			throw new B2BException(ErrorKeys.ERROR_INTERNO_SISTEMA);

		if(arc_data == null)
			throw new B2BException(ErrorKeys.ERROR_INTERNO_SISTEMA);

		Collection<RegistroCalificacion> lcrc_matriculas;
		String                           ls_idTurno;

		lcrc_matriculas     = arc_data.getAllMatriculas();
		ls_idTurno          = arc_data.getTurno();

		if(StringUtils.isValidString(ls_idTurno) && CollectionUtils.isValidCollection(lcrc_matriculas))
		{
			CambioEstadoPredio lcep_data;

			lcep_data = arc_data.getCambioEstadoPredio();

			if(lcep_data != null)
			{
				CambioEstadoPredioDAO          lcep_DAO;
				Iterator<RegistroCalificacion> lirc_iterator;
				String                         ls_justificacion;
				String                         ls_userId;
				String                         ls_ip;

				lcep_DAO             = DaoCreator.getCambioEstadoPredioDAO(adm_manager);
				lirc_iterator        = lcrc_matriculas.iterator();
				ls_justificacion     = lcep_data.getJustificacionCambio();
				ls_userId            = arc_data.getUserId();
				ls_ip                = arc_data.getIpAddress();

				if(!StringUtils.isValidString(ls_justificacion))
					throw new B2BException(ErrorKeys.DEBE_DIGITAR_JUSTIFICACION_CIERRE_FOLIO);
				else if(StringUtils.isValidString(ls_justificacion) && (ls_justificacion.length() < 100))
					throw new B2BException(ErrorKeys.DEBE_DIGITAR_JUSTIFICACION_CIERRE_FOLIO);

				while(lirc_iterator.hasNext())
				{
					RegistroCalificacion lrc_temp;

					lrc_temp = lirc_iterator.next();

					if(lrc_temp != null)
					{
						String ls_matricula;

						ls_matricula = lrc_temp.getIdCirculo();

						if(StringUtils.isValidString(ls_matricula))
						{
							String[] lsa_datosMatricula;

							lsa_datosMatricula = ls_matricula.split("-");

							if(CollectionUtils.isValidCollection(lsa_datosMatricula))
							{
								String ls_idCirculo;
								String ls_idMatricula;

								ls_idCirculo       = lsa_datosMatricula[0];
								ls_idMatricula     = lsa_datosMatricula[1];

								if(StringUtils.isValidString(ls_idCirculo) && StringUtils.isValidString(ls_idMatricula))
								{
									PredioRegistro lpr_predioRegistro;
									lpr_predioRegistro = new PredioRegistro();

									lpr_predioRegistro.setIdCirculo(ls_idCirculo);
									lpr_predioRegistro.setIdMatricula(NumericUtils.getLong(ls_idMatricula));

									lpr_predioRegistro = DaoCreator.getPredioRegistroDAO(adm_manager)
											                           .findByCirculoMatricula(lpr_predioRegistro);

									if(lpr_predioRegistro != null)
									{
										CambioEstadoPredio lcep_temp;

										lcep_data.setIdTurno(ls_idTurno);
										lcep_data.setIdCirculo(ls_idCirculo);
										lcep_data.setIdMatricula(NumericUtils.getLongWrapper(ls_idMatricula));

										lcep_temp = lcep_DAO.findById(lcep_data);

										if(lcep_temp != null)
										{
											lcep_data.setIdCambioEstadoPredio(lcep_temp.getIdCambioEstadoPredio());
											lcep_data.setIdUsuarioModificacion(ls_userId);
											lcep_data.setIpModificacion(ls_ip);

											lcep_DAO.insertOrUpdate(lcep_data, false);
										}
										else
										{
											lcep_data.setIdUsuarioCreacion(ls_userId);
											lcep_data.setIpCreacion(ls_ip);

											lcep_DAO.insertOrUpdate(lcep_data, true);
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

	/**
	 * Método encargado de guardar la información de la complementación.
	 *
	 * @param arc_dp Objeto que contiene la información a guardar.
	 * @param as_userId Variable de tipo String que contiene el id del usuario.
	 * @param as_remoteIp Variable de tipo String que contiene la ip del usuario.
	 * @param adm_manager DAOManager que controla las transacciones.
	 * @return Objeto que contiene la información guardada.
	 * @throws B2BException
	 */
	private synchronized ComplementacionCalificacion salvarComplementacion(
	    RegistroCalificacion arc_dp, String as_userId, String as_remoteIp, DAOManager adm_manager, boolean ab_nueva
	)
	    throws B2BException
	{
		ComplementacionCalificacion lcc_complementacionCalificacion;

		lcc_complementacionCalificacion = null;

		try
		{
			if(arc_dp != null)
			{
				Long   ll_idTurnoHistoria;
				String ls_idTurno;
				String ls_idCirculo;
				Long   ll_idMatricula;

				ll_idTurnoHistoria     = arc_dp.getIdTurnoHistoria();
				ls_idTurno             = arc_dp.getTurno();
				ls_idCirculo           = arc_dp.getIdCirculoMatriz();
				ll_idMatricula         = null;

				if(StringUtils.isValidString(ls_idCirculo) && ls_idCirculo.contains("-"))
				{
					String[] las_as;
					las_as     = ls_idCirculo.split("-");

					ls_idCirculo       = las_as[0];
					ll_idMatricula     = NumericUtils.getLongWrapper(las_as[1]);
				}

				if(NumericUtils.isValidLong(ll_idTurnoHistoria) && StringUtils.isValidString(ls_idTurno))
				{
					lcc_complementacionCalificacion = arc_dp.getComplementacionCalificacion();

					if(lcc_complementacionCalificacion != null)
					{
						ComplementacionPredioDAO    lcpd_complementacionPredioDAO;
						AccComplementacionPredioDAO lacpd_accComplementacionPredioDAO;
						ComplementacionPredio       lcp_complementacionPredio;

						lcpd_complementacionPredioDAO         = DaoCreator.getComplementacionPredioDAO(adm_manager);
						lacpd_accComplementacionPredioDAO     = DaoCreator.getAccComplementacionPredioDAO(adm_manager);
						lcp_complementacionPredio             = lcc_complementacionCalificacion.getComplementacionPredio();

						if(lcp_complementacionPredio != null)
						{
							String ls_complementacion;

							ls_complementacion = lcp_complementacionPredio.getComplementacion();

							if(StringUtils.isValidString(ls_complementacion))
							{
								AccComplementacionPredio lacp_accComplementacionPredio;
								String                   is_tipoComplementacion;
								String                   ls_idDatosAntSistema;

								lacp_accComplementacionPredio     = new AccComplementacionPredio();
								is_tipoComplementacion            = lcc_complementacionCalificacion
										.getTipoComplementacion();
								ls_idDatosAntSistema              = arc_dp.getIdDatosAntSistema();

								lacp_accComplementacionPredio.setIdTurnoHistoria(
								    NumericUtils.getLong(ll_idTurnoHistoria)
								);
								lacp_accComplementacionPredio.setIdTurno(ls_idTurno);
								lacp_accComplementacionPredio.setComplementacion(ls_complementacion);
								lacp_accComplementacionPredio.setIdUsuarioCreacion(as_userId);
								lacp_accComplementacionPredio.setIdDatosAntSistema(ls_idDatosAntSistema);

								if(
								    is_tipoComplementacion.equalsIgnoreCase(TipoComplementacionCommon.COPIAR)
									    && !lcc_complementacionCalificacion.isCopiarEditar()
								)
								{
									AccComplementacionPredio lacp_accComplementacionPredioTemp;

									lacp_accComplementacionPredioTemp = lacpd_accComplementacionPredioDAO.findByIdTurno(
										    lacp_accComplementacionPredio
										);

									lacp_accComplementacionPredio.setIdComplementacionOriginal(
									    NumericUtils.getLongWrapper(lcp_complementacionPredio.getIdComplementacion())
									);

									lacp_accComplementacionPredio.setTipoComplementacion(TipoComplementacionCommon.C);

									if(lacp_accComplementacionPredioTemp == null)
									{
										int li_secuenciaAcc;

										li_secuenciaAcc = lacpd_accComplementacionPredioDAO.findSecuence();

										lacp_accComplementacionPredio.setIdComplementacion(
										    NumericUtils.getLongWrapper(li_secuenciaAcc)
										);
										lacp_accComplementacionPredio.setIdUsuarioCreacion(as_userId);
										lacp_accComplementacionPredio.setIpCreacion(as_remoteIp);

										lacpd_accComplementacionPredioDAO.insert(lacp_accComplementacionPredio);
									}
									else
									{
										lacp_accComplementacionPredio.setIdUsuarioModificacion(as_userId);
										lacp_accComplementacionPredio.setIpModificacion(as_remoteIp);
										lacp_accComplementacionPredio.setIdComplementacion(
										    lacp_accComplementacionPredioTemp.getIdComplementacion()
										);

										lacpd_accComplementacionPredioDAO.updateById(lacp_accComplementacionPredio);
									}
								}
								else if(
								    !is_tipoComplementacion.equalsIgnoreCase(TipoComplementacionCommon.NUEVA)
									    || arc_dp.isDatosValidosAntSistema()
								)
								{
									int li_secuenciaBng;

									li_secuenciaBng = lcpd_complementacionPredioDAO.findSecuence();

									if(li_secuenciaBng > 0)
									{
										AccComplementacionPredio lacp_accComplementacionPredioTemp;
										ComplementacionPredio    lcp_bngComplementacionPredio;

										lcp_bngComplementacionPredio          = new ComplementacionPredio();
										lacp_accComplementacionPredioTemp     = new AccComplementacionPredio();

										lcp_bngComplementacionPredio.setIdComplementacion(
										    String.valueOf(li_secuenciaBng)
										);
										lcp_bngComplementacionPredio.setComplementacion(ls_complementacion);
										lcp_bngComplementacionPredio.setIdUsuarioCreacion(as_userId);
										lcp_bngComplementacionPredio.setIpCreacion(as_remoteIp);

										lcpd_complementacionPredioDAO.insert(lcp_bngComplementacionPredio);

										lacp_accComplementacionPredioTemp.setIdTurno(ls_idTurno);
										lacp_accComplementacionPredioTemp.setIdDatosAntSistema(ls_idDatosAntSistema);

										lacp_accComplementacionPredioTemp = lacpd_accComplementacionPredioDAO
												.findByIdTurno(lacp_accComplementacionPredioTemp);

										lacp_accComplementacionPredio.setIdComplementacionOriginal(
										    NumericUtils.getLongWrapper(li_secuenciaBng)
										);

										{
											String ls_tipoComplementacion;

											if(
											    is_tipoComplementacion.equalsIgnoreCase(
												        TipoComplementacionCommon.CONSTRUIR
												    )
											)
												ls_tipoComplementacion = TipoComplementacionCommon.A;
											else if(arc_dp.isDatosValidosAntSistema())
												ls_tipoComplementacion = TipoComplementacionCommon.N;
											else
												ls_tipoComplementacion = TipoComplementacionCommon.C;

											lacp_accComplementacionPredio.setTipoComplementacion(
											    ls_tipoComplementacion
											);
										}

										if(lacp_accComplementacionPredioTemp == null)
										{
											int li_secuenciaAcc;

											li_secuenciaAcc = lacpd_accComplementacionPredioDAO.findSecuence();

											if(li_secuenciaAcc > 0)
											{
												lacp_accComplementacionPredio.setIdComplementacion(
												    NumericUtils.getLongWrapper(li_secuenciaAcc)
												);
												lacp_accComplementacionPredio.setIpCreacion(as_remoteIp);
												lacp_accComplementacionPredio.setIdUsuarioCreacion(as_userId);
												lacpd_accComplementacionPredioDAO.insert(lacp_accComplementacionPredio);
											}
											else
												throw new B2BException(ErrorKeys.ACC_COMPLEMENTACION_PREDIO_SEQUENCE);
										}
										else
										{
											lacp_accComplementacionPredio.setIdUsuarioModificacion(as_userId);
											lacp_accComplementacionPredio.setIpModificacion(as_remoteIp);
											lacp_accComplementacionPredio.setIdComplementacion(
											    lacp_accComplementacionPredioTemp.getIdComplementacion()
											);

											lacpd_accComplementacionPredioDAO.updateById(lacp_accComplementacionPredio);
										}
									}
									else
										throw new B2BException(ErrorKeys.ACC_COMPLEMENTACION_PREDIO_SEQUENCE);
								}
								else if(
								    is_tipoComplementacion.equalsIgnoreCase(TipoComplementacionCommon.NUEVA)
									    || ab_nueva
								)
								{
									int li_secuenciaBng;

									li_secuenciaBng = lcpd_complementacionPredioDAO.findSecuence();

									if(li_secuenciaBng > 0)
									{
										AccComplementacionPredio lacp_accComplementacionPredioTemp;
										ComplementacionPredio    lcp_bngComplementacionPredio;

										lcp_bngComplementacionPredio          = new ComplementacionPredio();
										lacp_accComplementacionPredioTemp     = new AccComplementacionPredio();

										lcp_bngComplementacionPredio.setIdComplementacion(
										    String.valueOf(li_secuenciaBng)
										);
										lcp_bngComplementacionPredio.setComplementacion(ls_complementacion);
										lcp_bngComplementacionPredio.setIdUsuarioCreacion(as_userId);
										lcp_bngComplementacionPredio.setIpCreacion(as_remoteIp);

										lcpd_complementacionPredioDAO.insert(lcp_bngComplementacionPredio);

										lacp_accComplementacionPredioTemp.setIdTurno(ls_idTurno);

										lacp_accComplementacionPredioTemp = lacpd_accComplementacionPredioDAO
												.findByIdTurno(lacp_accComplementacionPredioTemp);

										lacp_accComplementacionPredio.setIdComplementacionOriginal(
										    NumericUtils.getLongWrapper(li_secuenciaBng)
										);

										{
											String ls_tipoComplementacion;

											if(
											    is_tipoComplementacion.equalsIgnoreCase(
												        TipoComplementacionCommon.CONSTRUIR
												    )
											)
												ls_tipoComplementacion = TipoComplementacionCommon.A;
											else if(arc_dp.isDatosValidosAntSistema() || ab_nueva)
												ls_tipoComplementacion = TipoComplementacionCommon.N;
											else
												ls_tipoComplementacion = TipoComplementacionCommon.C;

											lacp_accComplementacionPredio.setTipoComplementacion(
											    ls_tipoComplementacion
											);
										}

										if(lacp_accComplementacionPredioTemp == null)
										{
											int li_secuenciaAcc;

											li_secuenciaAcc = lacpd_accComplementacionPredioDAO.findSecuence();

											if(li_secuenciaAcc > 0)
											{
												lacp_accComplementacionPredio.setIdComplementacion(
												    NumericUtils.getLongWrapper(li_secuenciaAcc)
												);
												lacp_accComplementacionPredio.setIpCreacion(as_remoteIp);
												lacp_accComplementacionPredio.setIdUsuarioCreacion(as_userId);
												lacpd_accComplementacionPredioDAO.insert(lacp_accComplementacionPredio);
											}
											else
												throw new B2BException(ErrorKeys.ACC_COMPLEMENTACION_PREDIO_SEQUENCE);
										}
										else
										{
											lacp_accComplementacionPredio.setIdUsuarioModificacion(as_userId);
											lacp_accComplementacionPredio.setIpModificacion(as_remoteIp);
											lacp_accComplementacionPredio.setIdComplementacion(
											    lacp_accComplementacionPredioTemp.getIdComplementacion()
											);

											lacpd_accComplementacionPredioDAO.updateById(lacp_accComplementacionPredio);
										}
									}
									else
										throw new B2BException(ErrorKeys.ACC_COMPLEMENTACION_PREDIO_SEQUENCE);
								}

								{
									AccPredioRegistro    lapr_predio;
									AccPredioRegistroDAO lapr_DAO;

									lapr_predio     = new AccPredioRegistro();
									lapr_DAO        = DaoCreator.getAccPredioRegistroDAO(adm_manager);

									lapr_predio.setIdCirculo(ls_idCirculo);
									lapr_predio.setIdMatricula(ll_idMatricula);
									lapr_predio.setIdTurno(ls_idTurno);

									lapr_predio = lapr_DAO.findByCirculoMatriculaTurno(lapr_predio);

									if(lapr_predio != null)
									{
										lapr_predio.setIdComplementacion(
										    lacp_accComplementacionPredio.getIdComplementacionOriginal()
										);
										lapr_predio.setIdUsuarioModificacion(as_userId);
										lapr_predio.setIpModificacion(as_remoteIp);

										lapr_DAO.updateById(lapr_predio);
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
			clh_LOGGER.error("salvarComplementacion", lb2be_e);
			throw lb2be_e;
		}

		return lcc_complementacionCalificacion;
	}

	/**
	 * Método encargado de guardar la información del Lindero.
	 *
	 * @param arc_dp Objeto que contiene la información a guardar.
	 * @param as_userId Variable de tipo String que contiene el id del usuario.
	 * @param as_remoteIp Variable de tipo String que contiene la ip del usuario.
	 * @param adm_manager DAOManager que controla las transacciones.
	 * @return Objeto que contiene la información guardada.
	 * @throws B2BException
	 */
	private synchronized LinderoRegistroCalificacion salvarLindero(
	    RegistroCalificacion arc_dp, String as_userId, String as_remoteIp, DAOManager adm_manager
	)
	    throws B2BException
	{
		LinderoRegistroCalificacion llrc_linderoRegistroCalificacion;

		llrc_linderoRegistroCalificacion = null;

		try
		{
			if(arc_dp != null)
			{
				Long   ll_idTurnoHistoria;
				String ls_idTurno;
				String ls_idCirculo;
				Long   ll_idMatricula;

				ll_idTurnoHistoria     = arc_dp.getIdTurnoHistoria();
				ls_idTurno             = arc_dp.getTurno();
				ls_idCirculo           = arc_dp.getIdCirculoMatriz();
				ll_idMatricula         = null;

				if(StringUtils.isValidString(ls_idCirculo) && ls_idCirculo.contains("-"))
				{
					String[] las_as;
					las_as     = ls_idCirculo.split("-");

					ls_idCirculo       = las_as[0];
					ll_idMatricula     = NumericUtils.getLongWrapper(las_as[1]);
				}

				if(NumericUtils.isValidLong(ll_idTurnoHistoria) && StringUtils.isValidString(ls_idTurno))
				{
					llrc_linderoRegistroCalificacion = arc_dp.getLinderoRegistroCalificacion();

					if(llrc_linderoRegistroCalificacion != null)
					{
						String ls_tipoLindero;

						ls_tipoLindero = llrc_linderoRegistroCalificacion.getTipoLindero();

						if(!llrc_linderoRegistroCalificacion.isNuevoLindero())
						{
							if(
							    (StringUtils.isValidString(ls_tipoLindero)
								    && !ls_tipoLindero.equalsIgnoreCase(TipoComplementacionCommon.NUEVA))
								    || arc_dp.isLinderoCargado()
							)
							{
								AccLinderoPredioDAO          lalpd_accLinderoPredioDAO;
								Collection<AccLinderoPredio> lcalp_accLinderoPredios;

								lalpd_accLinderoPredioDAO     = DaoCreator.getAccLinderoPredioDAO(adm_manager);
								lcalp_accLinderoPredios       = llrc_linderoRegistroCalificacion.getLinderoPredios();

								if(CollectionUtils.isValidCollection(lcalp_accLinderoPredios))
								{
									for(AccLinderoPredio lalp_accLinderoPredio : lcalp_accLinderoPredios)
									{
										if(lalp_accLinderoPredio != null)
										{
											AccLinderoPredio lalp_accLinderoPredioTemp;

											lalp_accLinderoPredioTemp = new AccLinderoPredio();

											lalp_accLinderoPredioTemp.setIdTurno(ls_idTurno);

											if(NumericUtils.isValidLong(ll_idMatricula))
											{
												lalp_accLinderoPredioTemp.setIdMatricula(ll_idMatricula);
												lalp_accLinderoPredioTemp.setIdCirculo(ls_idCirculo);
											}
											else
											{
												lalp_accLinderoPredioTemp.setIdMatricula(
												    lalp_accLinderoPredio.getIdMatricula()
												);
												lalp_accLinderoPredioTemp.setIdCirculo(
												    lalp_accLinderoPredio.getIdCirculo()
												);
											}

											lalp_accLinderoPredioTemp = lalpd_accLinderoPredioDAO
													.findByCirculoMatriculaTurno(lalp_accLinderoPredioTemp);

											if(lalp_accLinderoPredioTemp == null)
											{
												int li_secuenciaAccLindero;

												li_secuenciaAccLindero = lalpd_accLinderoPredioDAO.findSecuencia();

												if(li_secuenciaAccLindero > 0)
												{
													lalp_accLinderoPredio.setIdLinderoPredio(
													    StringUtils.getString(li_secuenciaAccLindero)
													);

													if(arc_dp.isLinderoCargado())
													{
														lalp_accLinderoPredio.setLindero(
														    llrc_linderoRegistroCalificacion.getLindero()
														);
														lalp_accLinderoPredio.setIdTurnoHistoria(ll_idTurnoHistoria);
														lalp_accLinderoPredio.setIdTurno(ls_idTurno);
														lalp_accLinderoPredio.setIdUsuarioCreacion(as_userId);
														lalp_accLinderoPredio.setIpCreacion(as_remoteIp);
													}

													lalpd_accLinderoPredioDAO.insert(lalp_accLinderoPredio);
												}
												else
													throw new B2BException(
													    ErrorKeys.ACC_COMPLEMENTACION_PREDIO_SEQUENCE
													);
											}
											else
											{
												lalp_accLinderoPredio.setLindero(
												    llrc_linderoRegistroCalificacion.getLindero()
												);
												lalp_accLinderoPredio.setIdUsuarioModificacion(as_userId);
												lalp_accLinderoPredio.setIpModificacion(as_remoteIp);
												lalp_accLinderoPredio.setIdLinderoPredio(
												    lalp_accLinderoPredioTemp.getIdLinderoPredio()
												);

												lalpd_accLinderoPredioDAO.updateById(lalp_accLinderoPredio, true);
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
			clh_LOGGER.error("salvarLindero", lb2be_e);
			throw lb2be_e;
		}

		return llrc_linderoRegistroCalificacion;
	}
}
